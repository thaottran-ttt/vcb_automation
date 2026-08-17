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
import com.vcb.mobile.internal.pages.base.accountpage.AccountsPage
import com.vcb.mobile.internal.pages.base.BasePage
import com.vcb.mobile.internal.pages.base.HomePage
import com.vcb.mobile.internal.pages.base.accountpage.WealthPage

import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys
import com.vcb.common.MobileDriver

HomePage homePage = BasePage.getPage(HomePage)
AccountsPage accountsPage = BasePage.getPage(AccountsPage)
WealthPage wealthPage = BasePage.getPage(WealthPage)

WebUI.callTestCase(findTestCase('Mobile/VCB/Common/Login'), [('username') : username, ('password') : password, ('pinCode') : GlobalVariable.G_pinCode
        , ('yourName') : GlobalVariable.G_yourName, ('accountName') : accountName, ('flowControl') : com.vcb.common.FlowControlEnum.STOP
        , ('failStopTestSuite') : false], FailureHandling.STOP_ON_FAILURE)

'Navigate to Accounts Screen'
homePage.tapBtnAccounts()

'Navigate to Wealth Tab'
MobileDriver.takeScreenshot()
accountsPage.selectTab(tabName)

'Verify Tabung Haji item show on Wealth dashboard'
MobileDriver.takeScreenshot()
wealthPage.verifyTabungHajiCardOnWealthDashboard()

'Tap Tabung Haji item'
wealthPage.tapItemInListView(itemName)

'Verify Tabung Haji Dashboard - Self tab'
wealthPage.verifyTabungHajiDashboardDetail(selfTab)

'Tap Dependent tab'
wealthPage.tapTabungHajiTab(dependentTab)

'Verify Tabung Haji Dashboard - Dependent tab'
wealthPage.verifyTabungHajiDashboardDetail(dependentTab)

