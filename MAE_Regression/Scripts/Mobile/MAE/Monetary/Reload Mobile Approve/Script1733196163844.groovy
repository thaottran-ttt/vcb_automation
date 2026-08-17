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
import com.maybank.common.BaseKeyword
import com.maybank.common.MobileDriver
import com.maybank.mae.internal.pages.base.BasePage as BasePage
import com.maybank.mae.internal.pages.base.CommonPage as CommonPage
import com.maybank.mae.internal.pages.base.ConfirmationPage as ConfirmationPage
import com.maybank.mae.internal.pages.base.HomePage as HomePage
import com.maybank.mae.internal.pages.base.PushNotificationPage as PushNotificationPage
import com.maybank.mae.internal.pages.base.Secure2uAuthorisationPage as Secure2uAuthorisationPage
import com.maybank.mae.internal.pages.base.ShareReceiptPage as ShareReceiptPage
import com.maybank.mae.internal.pages.base.StatusScreenPage as StatusScreenPage
import com.maybank.mae.internal.pages.base.reloadpage.ReloadPage as ReloadPage
import com.maybank.utils.ListUtils as ListUtils
import com.maybank.utils.StringUtils as StringUtils
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

HomePage homePage = BasePage.getPage(HomePage)
ConfirmationPage confirmationPage = BasePage.getPage(ConfirmationPage)
Secure2uAuthorisationPage s2u = BasePage.getPage(Secure2uAuthorisationPage)
StatusScreenPage statusScreenPage = BasePage.getPage(StatusScreenPage)
ShareReceiptPage shareReceiptPage = BasePage.getPage(ShareReceiptPage)
PushNotificationPage pushNotificationPage = BasePage.getPage(PushNotificationPage)
CommonPage commonPage = BasePage.getPage(CommonPage)

List verifyUIConfirmationPage = ListUtils.parseObjectToList(verifyUIConfirmationPage)
List statusTitle = ListUtils.parseObjectToList(statusTitle)
List statusDescription = ListUtils.parseObjectToList(statusDescription)
List verifyUIShareReceipt = ListUtils.parseObjectToList(verifyUIShareReceipt)

WebUI.callTestCase(findTestCase('Mobile/MAE/Common/Login Secure2u'), [('idNumber') : idNumber, ('PAN') : PAN, ('password') : password
        , ('username') : username, ('accountName') : accountName], BaseKeyword.defaultFailureHandling)


WebUI.callTestCase(findTestCase('Mobile/MAE/Common/Entry point'), [('accessFlow') : accessFlow, ('actionName') : actionName
        , ('accountName') : accountName, ('menuName') : actionName], BaseKeyword.defaultFailureHandling)

'select provider'
ReloadPage.instance.selectProvider(provider)

'Enter Mobile'
Mobile.delay(5)
ReloadPage.instance.enterPhoneNumber(phoneNumber)

'Choose Reload Amount'
ReloadPage.instance.selectAmount(amount)

'Confirmation'
confirmationPage.verifyConfirmation(verifyUIConfirmationPage, nextConfirmationPage, accountName)
MobileDriver.delay(GlobalVariable.G_timeoutMiniShort)
MobileDriver.takeScreenshot()

'Authorize'
String statusType = s2u.authorizeS2UV5BioPin(approveStatus)

if (statusType.contains(Secure2uAuthorisationPage.AuthorisationType.TAC.toString())) {
	MobileDriver.delay(GlobalVariable.G_timeoutShort)

	'Wait Loading'
	commonPage.waitLoading()

	if (!(statusScreenPage.waitloadStatusScreen())) {
	}
}


'Status Screen'
def RefID = statusScreenPage.verifyStatusScreen(statusTitle, statusDescription)
statusScreenPage.backToEntryPoint()

'Check M2U and Transaction History'
List<String> historyVerificationList = [
	RefID,
	provider,
	phoneNumber,
	receiptAmount,
]

Mobile.comment(historyVerificationList.toString())

WebUI.callTestCase(findTestCase('Mobile/MAE/Common/Reprint Receipt Verification'), [('m2uHistoryProduct') : m2uHistoryProduct
		, ('listShareReceiptVerification') : historyVerificationList], FailureHandling.CONTINUE_ON_FAILURE)

'Verify Push Notification'
WebUI.callTestCase(findTestCase('Mobile/MAE/Common/Verify Push Notification'), [('titlePN') : titlePushNotification, ('startMessage') : 'Welcome to the MAE app.'
		, ('messagePN') : messagePushNotification
		, ('ref') : RefID, ('amount') : pushNotificationAmount], FailureHandling.STOP_ON_FAILURE)
