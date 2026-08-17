import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.vcb.mobile.internal.pages.base.BasePage as BasePage
import com.vcb.mobile.internal.pages.base.CommonPage as CommonPage
import com.vcb.mobile.internal.pages.base.HomePage as HomePage
import com.vcb.mobile.internal.pages.base.Secure2uAuthorisationPage as Secure2uAuthorisationPage
import com.vcb.mobile.internal.pages.base.transferpage.RecipientReferencePage as RecipientReferencePage
import com.vcb.mobile.internal.pages.base.transferpage.TransferPage as TransferPage
import com.vcb.mobile.internal.pages.base.StatusScreenPage
import com.vcb.mobile.internal.pages.base.ConfirmationPage as ConfirmationPage
import internal.GlobalVariable as GlobalVariable
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint

import com.vcb.common.MobileDriver

import com.vcb.utils.StringUtils as StringUtils

HomePage homePage = BasePage.getPage(HomePage)
CommonPage commonPage = BasePage.getPage(CommonPage)
TransferPage transferPage = BasePage.getPage(TransferPage)
Secure2uAuthorisationPage s2u = BasePage.getPage(Secure2uAuthorisationPage)
StatusScreenPage statusScreenPage = BasePage.getPage(StatusScreenPage)
ConfirmationPage confirmationPage = BasePage.getPage(ConfirmationPage)


WebUI.callTestCase(findTestCase('Mobile/VCB/Common/Login Secure2u'), [('idNumber') : idNumber, ('PAN') : PAN, ('password') : GlobalVariable.G_password
        , ('username') : username, ('accountName') : '', ('flowControl') : com.vcb.common.FlowControlEnum.STOP, ('failStopTestSuite') : false
        , ('yourName') : 'Test'], FailureHandling.STOP_ON_FAILURE)


def amount = amount
Double amountDouble = Double.parseDouble(amount) / 100
String parsedAmount = String.format('%.2f', amountDouble)

'Tap Accounts'
homePage.selectQuickActions(tabName)

'Select accounts transfer to'
transferPage.selectAccountTransferto(accountName)

'Enter Amount'
transferPage.enterAmount(amount)

'Verify Recipient Reference'
transferPage.verifyRecipientReference()

'Recipient Reference'
transferPage.recipientReference(recipientReference)

'Confirmation'
//transferPage.confirm(isFuture)
'Confirmation'
confirmationPage.verifyConfirmation(verifyUIConfirmationPage, nextConfirmationPage)
MobileDriver.delay(GlobalVariable.G_timeoutMiniShort)
MobileDriver.takeScreenshot()

//s2u.authorize(status)
'Authorisation'
String statusType = s2u.authorizeS2UV5BioPin(status)

if (statusType.contains(Secure2uAuthorisationPage.AuthorisationType.TAC.toString())) {
	MobileDriver.delay(GlobalVariable.G_timeoutShort)

	'Wait Loading'
	commonPage.waitLoading()

	if (!(statusScreenPage.waitloadStatusScreen())) {
	}
}

'Status screen'
String RefID = transferPage.statusScreen(status, false, isFuture)
statusScreenPage.backToEntryPoint()

List<String> historyVerificationList = [
	RefID,
	historyAmount
]

WebUI.callTestCase(findTestCase('Mobile/VCB/Common/Reprint Receipt Verification'), [('m2uHistoryProduct') : m2uHistoryProduct
        , ('listShareReceiptVerification') : historyVerificationList], FailureHandling.CONTINUE_ON_FAILURE)

'Verify Push Notification'
WebUI.callTestCase(findTestCase('Mobile/VCB/Common/Verify Push Notification'), [('titlePN') : titlePushNotification, ('startMessage') : startMessage
        , ('messagePN') : messagePushNotification
        , ('ref') : RefID, ('amount') : amount], FailureHandling.STOP_ON_FAILURE)
