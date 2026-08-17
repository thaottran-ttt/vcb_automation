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
import com.vcb.mobile.internal.pages.base.ConfirmationPage as ConfirmationPage
import com.vcb.mobile.internal.pages.base.EnterAmountPage as EnterAmountPage
import com.vcb.mobile.internal.pages.base.FavouritesPage as FavouritesPage
import com.vcb.mobile.internal.pages.base.HomePage as HomePage
import com.vcb.mobile.internal.pages.base.PushNotificationPage as PushNotificationPage
import com.vcb.mobile.internal.pages.base.Secure2uAuthorisationPage as Secure2uAuthorisationPage
import com.vcb.mobile.internal.pages.base.ShareReceiptPage as ShareReceiptPage
import com.vcb.mobile.internal.pages.base.StatusScreenPage as StatusScreenPage
import com.vcb.mobile.internal.pages.base.transferpage.TransferPage as TransferPage
import com.vcb.mobile.internal.pages.base.NotificationHomePage
import com.vcb.utils.StringUtils as StringUtils
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

CommonPage commonPage = BasePage.getPage(CommonPage)
HomePage homePage = BasePage.getPage(HomePage)
NotificationHomePage notificationHomePage = BasePage.getPage(NotificationHomePage)

String ref = WebUI.callTestCase(findTestCase('Mobile/VCB/Zakat/Common/TC Zakat Common'), [('username') : username, ('password') : password
        , ('idNumber') : idNumber, ('PAN') : PAN, ('accountName') : accountName, ('actionName') : actionName, ('tabName') : tabName
        , ('paymentName') : paymentName, ('inputZakat') : inputZakat, ('mobileNumber') : mobileNumber, ('amount') : amount
        , ('verifyConfirmData') : verifyConfirmData, ('verifyStatusScreenTitleData') : verifyStatusScreenTitleData, ('verifyStatusScreenDescriptionData') : verifyStatusScreenDescriptionData
        , ('status') : status, ('shareReceiptsData') : shareReceiptsData, ('favName') : favName, ('titlePN') : titlePN, ('product') : product
        , ('isRunFav') : isRunFav], FailureHandling.STOP_ON_FAILURE)

List<String> historyVerificationList = [
	ref,
	payBillName,
	billerID,
	receiptRM
]

WebUI.callTestCase(findTestCase('Mobile/VCB/Common/Reprint Receipt Verification'), [('m2uHistoryProduct') : product
		, ('listShareReceiptVerification') : historyVerificationList], FailureHandling.STOP_ON_FAILURE)

'Back To Home Page'
commonPage.backToHome()

'Tap bell Icon'
homePage.tapNotification()

'Verify Receive Notfication'
String amt = StringUtils.convertNoStringToAmount(amount)
'Messge descript in Push Notification'
String messagePN = "Successful payment of $amt to $product. REF:$ref"
notificationHomePage.verifyNotification(titlePN, 'Successful payment of', messagePN)
