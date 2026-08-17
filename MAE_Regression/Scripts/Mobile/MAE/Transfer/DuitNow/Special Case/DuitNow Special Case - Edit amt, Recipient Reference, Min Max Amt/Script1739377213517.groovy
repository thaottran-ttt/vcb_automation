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
import com.maybank.mae.internal.pages.base.BasePage
import com.maybank.mae.internal.pages.base.CommonPage
import com.maybank.mae.internal.pages.base.ConfirmationPage
import com.maybank.mae.internal.pages.base.EnterAmountPage
import com.maybank.mae.internal.pages.base.HomePage
import com.maybank.mae.internal.pages.base.PushNotificationPage
import com.maybank.mae.internal.pages.base.Secure2uAuthorisationPage
import com.maybank.mae.internal.pages.base.ShareReceiptPage
import com.maybank.mae.internal.pages.base.StatusScreenPage
import com.maybank.mae.internal.pages.base.transferpage.RecipientReferencePage
import com.maybank.mae.internal.pages.base.transferpage.TransferPage
import com.maybank.utils.ListUtils
import com.maybank.utils.MapUtils
import com.maybank.utils.StringUtils

import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

Map transferInputData = MapUtils.parseObjectToMap(transferInputData)
Map transferDropdownData = MapUtils.parseObjectToMap(transferDropdownData)
List confirmationData = ListUtils.parseObjectToList(confirmationData)

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

WebUI.callTestCase(findTestCase('Mobile/MAE/Common/Login Secure2u'), [('idNumber') : idNumber, ('PAN') : PAN, ('password') : password
        , ('username') : username, ('accountName') : accountName, ('flowControl') : com.maybank.common.FlowControlEnum.STOP
        , ('failStopTestSuite') : false, ('yourName') : GlobalVariable.G_yourName], FailureHandling.STOP_ON_FAILURE)

'Select Transfer Module at QuickActions'
homePage.selectQuickActions(moduleName)

'Select Tab'
transferPage.selectTab(transferTabName, true)

'select new Transfer'
transferPage.selectNewTransfer(newTransferName)

'Select Bank'
transferPage.selectBankName(bankName)

'Input Transfer Data'
transferPage.enterAccountOtherBack(transferInputData, transferDropdownData)

'enter Amount Min Limit - RM 0.01'
enterAmountPage.enterAmount(minAmount)

'Verify Recipient Reference up to 20, special character'
recipientReferencePage.verifyRecipientReference()
commonPage.tapContinueBtn()

'Verify Edit Amount Max limit'
'Verify Edit Recipient'
confirmationPage.verifyFieldValidationConfirmation(maxAmount, newRecipientReference)