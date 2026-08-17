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
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys


import com.vcb.common.MobileDriver as MobileDriver
import com.vcb.mobile.internal.pages.base.accountpage.AccountsPage as AccountsPage
import com.vcb.mobile.internal.pages.base.BasePage as BasePage
import com.vcb.mobile.internal.pages.base.ConfirmationPage as ConfirmationPage
import com.vcb.mobile.internal.pages.base.EnterAmountPage as EnterAmountPage
import com.vcb.mobile.internal.pages.base.HomePage as HomePage
import com.vcb.mobile.internal.pages.base.accountpage.LoanPage as LoanPage
import com.vcb.mobile.internal.pages.base.Secure2uAuthorisationPage as Secure2uAuthorisationPage
import com.vcb.mobile.internal.pages.base.StatusScreenPage as StatusScreenPage
import com.vcb.assertions.*

'Login VCB Secure2u'
WebUI.callTestCase(findTestCase('Mobile/VCB/Common/Login Secure2u'), [('idNumber') : idNumber, ('PAN') : PAN, ('password') : password
	, ('username') : username, ('accountName') : accountName, ('yourName') : cusName], FailureHandling.STOP_ON_FAILURE)

HomePage homePage = BasePage.getPage(HomePage)

AccountsPage accountsPage = BasePage.getPage(AccountsPage)

LoanPage loanPage = BasePage.getPage(LoanPage)

BaseAssert baseAssert = new BaseAssert()

'Tap Accounts'
homePage.tapMenu(menu)

'Tap Loan'
accountsPage.selectTab(tabName)

'Get Outstanding Balance'
def outstandingBalance = loanPage.getOutStandingBalance(FailureHandling.STOP_ON_FAILURE)

'Get total Outstanding Balance only for all loan/financing'
def aggregatedBalances = loanPage.getTotalAggregatedOutstandingBalances()

Boolean isEqual = outstandingBalance == aggregatedBalances ? true : false

Mobile.comment(isEqual.toString())

baseAssert.equals(true, isEqual, 'Verify Outstanding Balance', FailureHandling.STOP_ON_FAILURE)