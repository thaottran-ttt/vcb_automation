import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.annotation.AfterTestCase
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
import com.vcb.common.MobileDriver
import com.vcb.mobile.internal.pages.base.BasePage as BasePage
import com.vcb.mobile.internal.pages.base.CommonPage as CommonPage
import com.vcb.mobile.internal.pages.base.HomePage as HomePage
import com.vcb.mobile.internal.pages.base.Secure2uAuthorisationPage as Secure2uAuthorisationPage
import com.vcb.mobile.internal.pages.base.settingpage.securitypage.ChangePasswordPage as ChangePasswordPage
import com.vcb.mobile.internal.pages.base.settingpage.securitypage.SecurityPage as SecurityPage
import com.vcb.mobile.internal.pages.base.settingpage.SettingPage as SettingPage
import com.vcb.mobile.internal.pages.base.StatusScreenPage as StatusScreenPage
import com.vcb.mobile.internal.pages.base.transferpage.TransferPage as TransferPage
import com.vcb.mobile.internal.pages.base.transferpage.EnterASNBPage as EnterASNBPage

import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

HomePage homePage = HomePage.getPage(HomePage)

CommonPage commonPage = BasePage.getPage(CommonPage)

Secure2uAuthorisationPage secure2uAuthorisationPage = BasePage.getPage(Secure2uAuthorisationPage)

ChangePasswordPage changePasswordPage = BasePage.getPage(ChangePasswordPage)

SettingPage settingPage = BasePage.getPage(SettingPage)

SecurityPage securityPage = BasePage.getPage(SecurityPage)

StatusScreenPage statusScreenPage = BasePage.getPage(StatusScreenPage)

TransferPage transferPage = BasePage.getPage(TransferPage)

EnterASNBPage enterASNBPage = BasePage.getPage(EnterASNBPage)

WebUI.callTestCase(findTestCase('Mobile/VCB/Common/Login Secure2u'), [('idNumber') : idNumber, ('PAN') : PAN, ('password') : password
        , ('username') : username, ('accountName') : accountName, ('flowControl') : com.vcb.common.FlowControlEnum.STOP
        , ('failStopTestSuite') : false, ('yourName') : yourName], FailureHandling.STOP_ON_FAILURE)

balanceBeForeTransfer = homePage.getBalance()

homePage.selectQuickActions(actionName)

transferPage.selectTab(transferTab, true)

commonPage.waitLoading()

if(isFav) {
	transferPage.selectFav(favName)
	
}else {
	transferPage.selectNewTransfer(newTransferName)
	
	enterASNBPage.selectProductName(productName)
	
	enterASNBPage.selectIdTypeForASNBMember(idType)
	
	enterASNBPage.enterIdNumberForASNBMember(idNumberReceiver)
	
	enterASNBPage.enterMembershipNumber(membershipNumber)
}

enterASNBPage.selectRelationship(relationship)

enterASNBPage.selectPurposeOfTransfer(purpose)

enterASNBPage.tapContinueButton()

commonPage.waitLoading()

transferPage.enterAmount(amount)

'Verify Agree ASNB consent displayed correctly'
enterASNBPage.verifyASNBConsentDisplayed()

enterASNBPage.tapTransferNow()

'Verify error message if do not agree ASNB consent'
enterASNBPage.verifyASNBConsentErrorDisplayed()

'Wait for popup auto disappears'
commonPage.delay(3)

'Agree ASNB consent'
enterASNBPage.tapAgreeASNBConsent()

enterASNBPage.tapTransferNow()

commonPage.waitLoading()

transferPage.authorize(status)

commonPage.waitLoading()

statusScreenPage.verifyStatusScreen(statusTitle, statusDescription)

commonPage.tapDoneBtn()

commonPage.waitLoading()

commonPage.tapBack()

balanceAfterTransfer = homePage.getBalance()

if(status.equals('Approve')) {
	'Verify home balance displayed correctly after ASNB transfer'
	enterASNBPage.verifyHomeBalanceIsCorrectAfterASNBTrasfer(balanceBeForeTransfer, balanceAfterTransfer, amount)
}else {
	'Verify home balance is still the same'
	enterASNBPage.verifyHomeBalanceIsCorrectAfterNoASNBTrasfer(balanceBeForeTransfer, balanceAfterTransfer)
}