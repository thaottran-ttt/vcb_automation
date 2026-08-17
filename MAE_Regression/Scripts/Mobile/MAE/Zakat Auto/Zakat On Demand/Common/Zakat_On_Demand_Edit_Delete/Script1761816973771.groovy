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
import com.maybank.assertions.BaseAssert as BaseAssert
import com.maybank.common.MobileDriver as MobileDriver
import com.maybank.mae.internal.pages.base.BasePage as BasePage
import com.maybank.mae.internal.pages.base.CommonPage as CommonPage
import com.maybank.mae.internal.pages.base.zakat.ZakatPage as ZakatPage
import com.maybank.mae.internal.pages.base.HomePage as HomePage
import com.maybank.mae.internal.pages.base.accountpage.AccountDetailPage

import com.maybank.utils.ListUtils as ListUtils
import com.maybank.utils.StringUtils as StringUtils
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

ZakatPage zakatPage = BasePage.getPage(ZakatPage)
CommonPage commonPage = BasePage.getPage(CommonPage)
HomePage homePage = BasePage.getPage(HomePage)
AccountDetailPage AccountDetailPage = BasePage.getPage(AccountDetailPage)

'Onboard'
WebUI.callTestCase(findTestCase('Mobile/MAE/Common/Login Secure2u'), [('idNumber') : idNumber, ('PAN') : PAN, ('password') : GlobalVariable.G_password
	, ('username') : username, ('accountName') : accountName], FailureHandling.STOP_ON_FAILURE)

'Entry from Quick Action'
zakatPage.tapZakatInViewAll()

commonPage.checkEnterPIN(GlobalVariable.G_timeoutMiniShort)

'Tap Calculate & Pay Zakat Tile'
Mobile.tap(commonPage.lblContainDynamic("Calculate & Pay Zakat"), GlobalVariable.G_timeoutMiniShort, FailureHandling.CONTINUE_ON_FAILURE)

'Tap on Saved Calculation'
zakatPage.tapSavedCalculation(ZakatTypes, GlobalVariable.G_timeoutMiniShort, FailureHandling.STOP_ON_FAILURE)

'Edit/Delete the Calculation'
zakatPage.EditSavedCalculation(ZakatBody, editFlow, flowType, Verify)

