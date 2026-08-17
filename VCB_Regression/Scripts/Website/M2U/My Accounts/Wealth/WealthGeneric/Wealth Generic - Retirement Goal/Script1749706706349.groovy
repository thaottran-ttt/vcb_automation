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
import com.maybank.common.BaseKeyword
import com.maybank.m2u.internal.pages.base.WebBasePage
import com.maybank.m2u.internal.pages.base.accountpage.WebWealthGenericPage

WebWealthGenericPage webWealthGenericPage = WebBasePage.getPage(WebWealthGenericPage)
WebSecure2uAuthorisationPage webSecure2uAuthorisationPage = WebBasePage.getPage(WebSecure2uAuthorisationPage)

WebUI.callTestCase(findTestCase('Test Cases/Website/M2U/My Accounts/Wealth/WealthGeneric/common/NavigateToGoaBasedInvestment'), [('username') : username, ('password') : password], BaseKeyword.defaultFailureHandling)

'Navigate to Goal Based Investment'
webWealthGenericPage.navigateToGoalBasedInvestment()

'Click Retirement Goal'
webWealthGenericPage.clickRetirementGoal()

'Click Next'
webWealthGenericPage.clickNext()

'Start Investing'
webWealthGenericPage.clickStartInvesting()

'Select Fund'
webWealthGenericPage.selectFund()

'Click Next'
webWealthGenericPage.clickNext()
WebUIKeyword.delay(GlobalVariable.G_timeoutShort)

'Click Invest Now'
webWealthGenericPage.clickInvestNow()

'Select Transfer From and Confirm'
webWealthGenericPage.selectTransferFromAndConfirm()

'Agree and Confirm'
webWealthGenericPage.agreeAndConfirm()
WebUIKeyword.delay(GlobalVariable.G_timeoutShort)

WebUIKeyword.delay(GlobalVariable.G_timeoutShort)

'Verify S2U'
webSecure2uAuthorisationPage.authorize(status)
