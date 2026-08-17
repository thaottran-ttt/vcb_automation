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
import com.maybank.common.MobileDriver
import com.maybank.mae.internal.pages.base.BasePage
import com.maybank.mae.internal.pages.base.CommonPage
import com.maybank.mae.internal.pages.base.ConfirmationPage
import com.maybank.mae.internal.pages.base.EnterAmountPage
import com.maybank.mae.internal.pages.base.transferpage.EnterDuitnowProxyPage
import com.maybank.mae.internal.pages.base.HomePage
import com.maybank.mae.internal.pages.base.PushNotificationPage
import com.maybank.mae.internal.pages.base.transferpage.RecipientReferencePage
import com.maybank.mae.internal.pages.base.Secure2uAuthorisationPage
import com.maybank.mae.internal.pages.base.StatusScreenPage
import com.maybank.mae.internal.pages.base.transferpage.TransferPage

import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

WebUI.callTestCase(findTestCase('Mobile/MAE/Common/Login Secure2u'), [('idNumber') : idNumber, ('PAN') : PAN, ('password') : password
	, ('username') : username, ('accountName') : accountName], FailureHandling.STOP_ON_FAILURE)

HomePage homePage = BasePage.getPage(HomePage)
TransferPage transferPage = BasePage.getPage(TransferPage)
CommonPage commonPage = BasePage.getPage(CommonPage)
EnterDuitnowProxyPage enterDuitnowProxyPage = BasePage.getPage(EnterDuitnowProxyPage)
EnterAmountPage enterAmountPage = BasePage.getPage(EnterAmountPage)
RecipientReferencePage recipientReferencePage = BasePage.getPage(RecipientReferencePage)
ConfirmationPage confirmationPage = BasePage.getPage(ConfirmationPage)
Secure2uAuthorisationPage secure2uAuthorisationPage = BasePage.getPage(Secure2uAuthorisationPage)
StatusScreenPage statusScreenPage = BasePage.getPage(StatusScreenPage)
PushNotificationPage pushNotificationPage = BasePage.getPage(PushNotificationPage)

'Favourite'
homePage.selectQuickActions(tranferName)
transferPage.selectTab(transferTab, true)
transferPage.selectFavourite(favName)

'Enter Amount'
enterAmountPage.enterAmountDuitNow(amount)

'Recipient Reference'
recipientReferencePage.inputRecipientReference()

'Confirmation Page'
confirmationPage.verifyHasLandedOnConfirmationScreen()
confirmationPage.verifyConfirmationScreenUICorrect()
confirmationPage.proceedToAuthorisation()

'Authorisation'
//secure2uAuthorisationPage.authorize(status)
'Authorisation'
String statusType = secure2uAuthorisationPage.authorizeS2UV5BioPin(status)

if (statusType.contains(Secure2uAuthorisationPage.AuthorisationType.TAC.toString())) {
	MobileDriver.delay(GlobalVariable.G_timeoutShort)

	'Wait Loading'
	commonPage.waitLoading()

	if (!(statusScreenPage.waitloadStatusScreen())) {
	}
}


'Status Screen'
'Status Screen'
def RefID = statusScreenPage.verifyDuitnowStatusUI(status, futuretype)
statusScreenPage.backToEntryPoint()

'Check M2U and Transaction History'
List<String> historyVerificationList = [RefID]

Mobile.comment(historyVerificationList.toString())

WebUI.callTestCase(findTestCase('Mobile/MAE/Common/Reprint Receipt Verification'), [('m2uHistoryProduct') : m2uHistoryProduct
		, ('listShareReceiptVerification') : historyVerificationList], FailureHandling.CONTINUE_ON_FAILURE)

'Verify Push Notification'
WebUI.callTestCase(findTestCase('Mobile/MAE/Common/Verify Push Notification'), [('titlePN') : titlePushNotification, ('startMessage') : 'Welcome to the MAE app.'
		, ('messagePN') : messagePushNotification
		, ('ref') : RefID, ('amount') : amount], FailureHandling.STOP_ON_FAILURE)