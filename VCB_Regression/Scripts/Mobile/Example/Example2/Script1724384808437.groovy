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
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import com.maybank.assertions.BaseAssert
import com.maybank.common.Android
import com.maybank.common.MobileDriver
import com.maybank.common.MobileDriver.ScrollDirectionEnum
import com.maybank.mae.internal.pages.base.accountpage.AccountsPage as AccountsPage
import com.maybank.mae.internal.pages.base.BasePage
import com.maybank.mae.internal.pages.base.CommonPage
import com.maybank.utils.StringUtils

import internal.GlobalVariable
import io.appium.java_client.android.AndroidDriver

import org.openqa.selenium.Keys as Keys


CommonPage commonPage = BasePage.getPage(CommonPage)
//commonPage.networkLogsAndRestartApp()
//MobileDriver.driver.activateApp(GlobalVariable.G_appId)

//Mobile.delay(5)
//((AndroidDriver) (MobileDriver.driver)).resetApp()
//Mobile.delay(5)

//Android.killApp(GlobalVariable.G_appId)


//CommonPage commonPage = BasePage.getPage(CommonPage)
//commonPage.selectDropdownScrollOneLine('M2U History', 0)
//def x = commonPage.isHomeOrOnboarding()
Mobile.comment('test')

//MobileDriver.handleError("a", FailureHandling.STOP_ON_FAILURE)