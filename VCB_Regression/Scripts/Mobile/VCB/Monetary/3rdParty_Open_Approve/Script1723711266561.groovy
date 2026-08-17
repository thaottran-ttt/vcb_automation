import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
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
import com.vcb.mobile.internal.pages.base.BasePage as BasePage
import com.vcb.mobile.internal.pages.base.CommonPage as CommonPage
import com.vcb.mobile.internal.pages.base.HomePage as HomePage
import com.vcb.mobile.internal.pages.base.transferpage.TransferPage
import com.vcb.mobile.internal.pages.base.NotificationHomePage
import com.vcb.mobile.internal.pages.base.PushNotificationPage
import com.vcb.utils.StringUtils
import com.vcb.common.MobileDriver

import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

HomePage homePage = HomePage.getPage(HomePage)
TransferPage transferPage = BasePage.getPage(TransferPage)
CommonPage commonPage = BasePage.getPage(CommonPage)
NotificationHomePage notificationHomePage = BasePage.getPage(NotificationHomePage)
PushNotificationPage pushNotificationPage = BasePage.getPage(PushNotificationPage)

WebUI.callTestCase(findTestCase('Mobile/VCB/Common/Login Secure2u'), [('idNumber') : idNumber, ('PAN') : PAN, ('password') : password
        , ('username') : username, ('accountName') : accountName], FailureHandling.STOP_ON_FAILURE)

'Get Balance'
String balance = homePage.getBalance(true)

def balanceBigInt = StringUtils.convertBalanceAmountNumber(balance)

'Select Transfer Module at QuickActions'
homePage.selectQuickActions(tranferName)

'Select Others Tab'
transferPage.selectTab(transferTab, true)

'Select Banks -> select VCB'
transferPage.openNewTransferOthers('Banks', 'VCB')

'Account Number'
transferPage.setAccountNumber(accountNo)

'Enter Amount'
commonPage.enterNO(amount)

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
"You've transferred RM ${formattedAmount} to (.+)'s VCB account ending \\*{4}${acc4Digit}\\. REF: ${RefID}"
commonPage.verifyDeviceNotification("Vcb2u: Transfer", "You've transferred", messagePN)

'Check amount'
String newBalance = homePage.getBalance(true)
def newBalanceBigInt = StringUtils.convertBalanceAmountNumber(newBalance)
CustomKeywords.'com.vcb.assertions.BaseAssert.equals'((balanceBigInt -  newBalanceBigInt).toString(), amount.toString(), 'Check Amount')

'Back To Home Page'
commonPage.backToHome()

'Verify Reprint'
List<String> historyVerificationList = [
	RefID,
	m2uHistoryAmount
]
WebUI.callTestCase(findTestCase('Mobile/VCB/Common/Reprint Receipt Verification'), [('m2uHistoryProduct') : m2uHistoryProduct
	, ('listShareReceiptVerification') : historyVerificationList], FailureHandling.STOP_ON_FAILURE)

'Back To Home Page'
commonPage.backToHome()

'Tap bell Icon'
homePage.tapNotification()

'Verify Receive Notfication'
notificationHomePage.verifyNotification("Vcb2u: Transfer", "You've transferred", messagePN)
