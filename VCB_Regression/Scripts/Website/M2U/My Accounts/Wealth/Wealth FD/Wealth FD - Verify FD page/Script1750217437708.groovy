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
import com.maybank.m2u.internal.pages.base.accountpage.WebWealthFDPage

WebWealthFDPage webWealthFDPage = WebBasePage.getPage(WebWealthFDPage)
WebSecure2uAuthorisationPage webSecure2uAuthorisationPage = WebBasePage.getPage(WebSecure2uAuthorisationPage)

WebUI.callTestCase(findTestCase('Test Cases/Website/M2U/My Accounts/Wealth/Wealth FD/common/NavigateToWealth'), [('username') : username, ('password') : password], BaseKeyword.defaultFailureHandling)

'Verify Fixed Deposit'
webWealthFDPage.verifyFixedDeposit();

'Go to Fixed Deposit page'
webWealthFDPage.gotToFixedDeposit();

'Verify Fixed Deposit page'
webWealthFDPage.verifyFDPage();


