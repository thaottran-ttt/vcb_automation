import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import com.maybank.common.WebUIKeyword
import com.maybank.m2u.internal.pages.base.WebSecure2uAuthorisationPage
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
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys
import com.maybank.m2u.internal.pages.base.WebBasePage
import com.maybank.m2u.internal.pages.base.WebCommonPage
import com.maybank.m2u.internal.pages.base.HTMLTag
import com.maybank.m2u.internal.pages.base.fixeddeposit.WebFixedDepositPage

import com.maybank.m2u.internal.pages.base.WebBasePage
import com.maybank.m2u.internal.pages.base.WebCommonPage

WebCommonPage webCommonPage = WebBasePage.getPage(WebCommonPage)
WebFixedDepositPage webFixedDepositPage = WebBasePage.getPage(WebFixedDepositPage)
WebSecure2uAuthorisationPage webSecure2uAuthorisationPage = WebBasePage.getPage(WebSecure2uAuthorisationPage)

WebUI.callTestCase(findTestCase('Test Cases/Website/M2U/My Accounts/FixedDeposit/Common/NavigateToFD'), [('username') : username
	, ('password') : password, ('idNumber') : idNumber, ('PAN') : PAN, ('navItem') : navItem, ('tabName') : tabName, ('accountType') : accountType
	, ('goldGram') : goldGram, ('status') : status
	, ('statusScreenData') : statusScreenData, ('isBuy') : isBuy, ('isRunWebsiteAndMobile') : isRunWebsiteAndMobile],
FailureHandling.STOP_ON_FAILURE)

'Click APPLY FIXED DEPOSIT'
webFixedDepositPage.clickApplyFD()

'Select Product Type'
webFixedDepositPage.selectDropdown(WebFixedDepositPage.Dropdown.PRODUCT_TYPE, productType)

'Click CONTINUE'
WebUIKeyword.click(webCommonPage.btnNormalizeSpaceDynamic(CONTINUE))

'Click Save & Next'
webFixedDepositPage.clickSaveNext()

'Fill form'
webFixedDepositPage.fillForm(tenure, principalAmount, paymentFrequency, paymentMode, maturityInstruction)
WebUIKeyword.delay(GlobalVariable.G_timeoutMedium)

'Verify S2U'
webSecure2uAuthorisationPage.setID_NUMBER(idNumber)
webSecure2uAuthorisationPage.authorize(status)

WebUIKeyword.delay(GlobalVariable.G_timeoutMedium)

'Click Done'
WebUIKeyword.click(webCommonPage.btnNormalizeSpaceDynamic(done))