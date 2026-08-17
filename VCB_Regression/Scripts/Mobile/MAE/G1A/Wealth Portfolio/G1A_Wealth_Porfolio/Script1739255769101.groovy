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
import com.maybank.mae.internal.pages.base.BasePage
import com.maybank.mae.internal.pages.base.HomePage
import com.maybank.mae.internal.pages.base.accountpage.AccountsPage
import com.maybank.mae.internal.pages.base.accountpage.WealthPage
import com.maybank.utils.ListUtils
import com.maybank.mae.internal.pages.base.CommonPage
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

List totalInvestmentData = ListUtils.parseObjectToList(totalInvestmentData)
List verifydataOverview = ListUtils.parseObjectToList(verifydataOverview)
List verifyDataAssetClassification = ListUtils.parseObjectToList(verifyDataAssetClassification)
List verifyAssetTooltip = ListUtils.parseObjectToList(verifyAssetTooltip)
List cashList = ListUtils.parseObjectToList(cashList)
HomePage homePage = BasePage.getPage(HomePage)
AccountsPage accountsPage = BasePage.getPage(AccountsPage)
WealthPage wealthPage = BasePage.getPage(WealthPage)
CommonPage commonPage = BasePage.getPage(CommonPage)

'Login MAE Secure2u'
WebUI.callTestCase(findTestCase('Mobile/MAE/Common/Login Secure2u'), [('idNumber') : icNumber, ('PAN') : PAN, ('password') : password
		, ('username') : username, ('accountName') : accountName], FailureHandling.STOP_ON_FAILURE)

'Navigate to Accounts Screen'
homePage.tapBtnAccounts()

'Navigate to Wealth Tab'
accountsPage.selectTab(tabName)

'Verify Digital Wealth Dashboard'
wealthPage.verifyDigitalWealthDashboard(totalInvestmentData)

'verify Digital Wealth Overview'
wealthPage.verifyDigitalWealthOverview(verifydataOverview)

'Navigate to Wealth Tab'
accountsPage.selectTab(tabName)

'Verify Cash Detail'
wealthPage.verifyWealthAssetClassification(verifyDataAssetClassification, cashList, verifyAssetTooltip, accountType)

'Back to Home Page'
commonPage.backToHome()
