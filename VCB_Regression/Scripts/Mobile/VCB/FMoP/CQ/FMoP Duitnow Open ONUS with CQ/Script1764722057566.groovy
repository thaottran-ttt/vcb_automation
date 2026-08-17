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
import com.vcb.common.BaseKeyword
import com.vcb.common.MobileDriver
import com.vcb.mobile.internal.pages.base.BasePage
import com.vcb.mobile.internal.pages.base.CommonPage
import com.vcb.mobile.internal.pages.base.ConfirmationPage
import com.vcb.mobile.internal.pages.base.EnterAmountPage
import com.vcb.mobile.internal.pages.base.HomePage
import com.vcb.mobile.internal.pages.base.PushNotificationPage
import com.vcb.mobile.internal.pages.base.Secure2uAuthorisationPage
import com.vcb.mobile.internal.pages.base.ShareReceiptPage
import com.vcb.mobile.internal.pages.base.StatusScreenPage
import com.vcb.mobile.internal.pages.base.transferpage.RecipientReferencePage
import com.vcb.mobile.internal.pages.base.transferpage.TransferPage
import com.vcb.mobile.internal.pages.base.SecurityQuestionPage
import com.vcb.utils.ListUtils
import com.vcb.utils.MapUtils
import com.vcb.utils.StringUtils

import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

Map transferInputData = MapUtils.parseObjectToMap(transferInputData)
Map transferDropdownData = MapUtils.parseObjectToMap(transferDropdownData)
Map favTransferDropdownData = MapUtils.parseObjectToMap(favTransferDropdownData)
List confirmationData = ListUtils.parseObjectToList(confirmationData)
List statusTitle = ListUtils.parseObjectToList(statusTitle)
List statusDescription = ListUtils.parseObjectToList(statusDescription)
List verifyUIShareReceipt = ListUtils.parseObjectToList(verifyUIShareReceipt)
List messagePushNotification = ListUtils.parseObjectMessageToList(messagePushNotification)

CommonPage commonPage = BasePage.getPage(CommonPage)
HomePage homePage = BasePage.getPage(HomePage)
TransferPage transferPage = BasePage.getPage(TransferPage)
RecipientReferencePage recipientReferencePage = BasePage.getPage(RecipientReferencePage)
EnterAmountPage enterAmountPage = BasePage.getPage(EnterAmountPage)
ConfirmationPage confirmationPage = BasePage.getPage(ConfirmationPage)
Secure2uAuthorisationPage secure2uAuthorisationPage = BasePage.getPage(Secure2uAuthorisationPage)
StatusScreenPage statusScreenPage = BasePage.getPage(StatusScreenPage)
ShareReceiptPage shareReceiptPage = BasePage.getPage(ShareReceiptPage)
PushNotificationPage pushNotificationPage = BasePage.getPage(PushNotificationPage)

WebUI.callTestCase(findTestCase('Mobile/VCB/Common/Login Secure2u With Secure Question'), [('idNumber') : idNumber, ('PAN') : PAN, ('password') : password
        , ('username') : username, ('accountName') : accountName, ('flowControl') : com.vcb.common.FlowControlEnum.STOP
        , ('failStopTestSuite') : false, ('yourName') : GlobalVariable.G_yourName], FailureHandling.STOP_ON_FAILURE)

String balance = homePage.getBalance(true)

'Select Transfer Module at QuickActions'
homePage.selectQuickActions(moduleName)

'Select Tab'
transferPage.selectTab(transferTabName, true)

if(shouldValidationFavoriteTransfer) {
	transferPage.selectFavourite(favName)
}else {
	'select new Transfer'
	transferPage.selectNewTransfer(newTransferName)
	
	'Select Bank'
	transferPage.selectSearchedBank(bankName,searchResult)
	
	'Input Transfer Data'
	transferPage.enterAccountOtherBack(transferInputData, transferDropdownData)
}

'enter Amount'
enterAmountPage.enterAmount(amount)

'Recipient Reference'
recipientReferencePage.inputRecipientReference()

if(isFuture) {
	confirmationPage.waitLoadScreen()
	'Future'
	confirmationPage.futureFlow()
}

if(shouldValidationFavoriteTransfer) {
	transferPage.enterAccountOtherBack([:], favTransferDropdownData)
}

'Verify Confirmation'
confirmationPage.verifyConfirmation(confirmationData, nextConfirmationPage, insuffAccountName)

'S2u'
secure2uAuthorisationPage.authorize(status)

'Enter Answer For Security Question'
CustomKeywords.'com.vcb.mobile.pages.SecurityQuestion.inputAnswer'(securityanswer)

'Status Screen'
String ref = statusScreenPage.verifyStatusScreen(statusTitle, statusDescription)

'Tap Done Button'
commonPage.tapDoneBtn()
