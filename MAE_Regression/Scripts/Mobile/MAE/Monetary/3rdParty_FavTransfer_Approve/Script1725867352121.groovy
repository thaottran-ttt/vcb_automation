import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import java.awt.datatransfer.Transferable

import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import com.maybank.common.MobileDriver
import com.maybank.mae.internal.pages.base.BasePage
import com.maybank.mae.internal.pages.base.CommonPage
import com.maybank.mae.internal.pages.base.HomePage
import com.maybank.mae.internal.pages.base.transferpage.TransferPage
import com.maybank.mae.internal.pages.base.NotificationHomePage
import com.maybank.mae.internal.pages.base.PushNotificationPage

import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

HomePage homePage = HomePage.getPage(HomePage)
TransferPage transferPage = BasePage.getPage(TransferPage)
CommonPage commonPage = BasePage.getPage(CommonPage)
NotificationHomePage notificationHomePage = BasePage.getPage(NotificationHomePage)
PushNotificationPage pushNotificationPage = BasePage.getPage(PushNotificationPage)

WebUI.callTestCase(findTestCase('Mobile/MAE/Common/Login Secure2u'), [('idNumber') : idNumber, ('PAN') : PAN, ('password') : password
        , ('username') : username, ('accountName') : accountName], FailureHandling.STOP_ON_FAILURE)

'Select Transfer Module at QuickActions'
homePage.selectQuickActions(tranferName)

'Select Others Tab'
transferPage.selectTab(transferTab, true)

'Search and Select Favourites'
transferPage.selectFav(favName)

'Enter Amount'
transferPage.enterAmount(amount)

'Recipient Reference'
transferPage.recipientReference(recipientReference)

'Confirmation'
transferPage.confirm()
MobileDriver.tap(commonPage.lblDynamic(transferPage.UIApprove), GlobalVariable.G_timeoutShort, FailureHandling.OPTIONAL)
if (MobileDriver.waitForElementPresent(commonPage.lblContainDynamic(commonPage.UI6DigitBioPIN), GlobalVariable.G_timeoutShort, FailureHandling.OPTIONAL)) {
	commonPage.checkEnterS2UV5PIN(GlobalVariable.G_timeoutMiniShort)
}

'Status screen'
String RefID = transferPage.statusScreen(status, false, false, false)

'Verify notification'
String acc4Digit = accountNo.substring(accountNo.length() - 4)
String formattedAmount = String.format("%.2f", Double.parseDouble(amount)/100)
String messagePN =
"You've transferred RM ${formattedAmount} to (.+)'s MAYBANK account ending \\*{4}${acc4Digit}\\. REF: ${RefID}"
commonPage.verifyDeviceNotification("Maybank2u: Transfer", "You've transferred", messagePN)

'Back To Home Page'
commonPage.backToHome()

'Verify Reprint'
List<String> historyVerificationList = [
	RefID,
	m2uHistoryAmount
]
WebUI.callTestCase(findTestCase('Mobile/MAE/Common/Reprint Receipt Verification'), [('m2uHistoryProduct') : m2uHistoryProduct
	, ('listShareReceiptVerification') : historyVerificationList], FailureHandling.STOP_ON_FAILURE)

'Back To Home Page'
commonPage.backToHome()

'Tap bell Icon'
homePage.tapNotification()

'Verify Receive Notfication'
notificationHomePage.verifyNotification("Maybank2u: Transfer", "You've transferred", messagePN)
