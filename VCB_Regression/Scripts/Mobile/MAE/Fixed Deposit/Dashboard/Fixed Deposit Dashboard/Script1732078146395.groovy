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

import com.maybank.mae.internal.pages.base.HomePage
import com.maybank.mae.internal.pages.base.CommonPage
import com.maybank.mae.internal.pages.base.fixeddepositpage.FixedDepositPage
import com.maybank.mae.internal.pages.base.applypage.ApplyPage
import com.maybank.mae.internal.pages.base.accountpage.AccountsPage
import com.maybank.mae.internal.pages.base.BasePage
import com.maybank.utils.*
import com.maybank.assertions.*

HomePage homePage = HomePage.getPage(HomePage)

CommonPage commonPage = BasePage.getPage(CommonPage)

AccountsPage accountsPage = BasePage.getPage(AccountsPage)

FixedDepositPage fixedDepositPage = BasePage.getPage(FixedDepositPage)

'Login MAE'
WebUI.callTestCase(findTestCase('Mobile/MAE/Common/Login'), [('username') : username, ('password') : password, ('pinCode') : GlobalVariable.G_pinCode
        , ('yourName') : GlobalVariable.G_yourName, ('accountName') : '', ('flowControl') : com.maybank.common.FlowControlEnum.STOP
        , ('failStopTestSuite') : false, ('skipAccountLoginCheck') : false], FailureHandling.STOP_ON_FAILURE)

'Tap View All Accounts'
homePage.tapBtnAccounts()

accountsPage.selectTab(itemTabName)

'VP: Verify FD Listing detail'
List<List> lstFD = fixedDepositPage.verifyDashboardCards()

'VP: Verify Amount format'
fixedDepositPage.verifyDepositFormat(lstFD, GlobalVariable.G_timeoutMedium)

'VP: Verify button "Make a placement"'
fixedDepositPage.verifyElementExist(fixedDepositPage.btnMakeAPlacement(), GlobalVariable.G_timeoutMedium)

