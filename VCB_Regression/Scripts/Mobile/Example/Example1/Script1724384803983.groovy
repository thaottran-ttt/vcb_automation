import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import com.maybank.common.MobileDriver
import com.maybank.mae.internal.pages.base.BasePage
import com.maybank.mae.internal.pages.base.CommonPage
import com.maybank.mae.internal.pages.base.HomePage
import com.maybank.mae.internal.pages.base.transferpage.TransferPage

import internal.GlobalVariable
import io.appium.java_client.AppiumDriver

import org.openqa.selenium.Keys as Keys

////CommonPage commonPage = BasePage.getPage(CommonPage)
//////commonPage.swipe(5, 1000, 5, 100)
////commonPage.backToHome()
////'Navigate To Login'
//////CustomKeywords.'com.maybank.mae.pages.Onboarding.navigateToLogin'()
////
////String xpath = "(//*[@class = 'android.widget.ImageView'])[3]"
////MobileDriver.tapElemtByXml(xpath)
//
////MobileDriver.setFailureHandling(flowControl)
////MobileDriver.testFlowControl()
//
////KeywordUtil.markFailedAndStop('ERROR: The previous test fail')
//
//CommonPage commonPage = BasePage.getPage(CommonPage)
//commonPage.restartApp()
//
//AppiumDriver<?> driver = MobileDriverFactory.getDriver()
//
//Mobile.comment(driver.getPageSource())
////MobileDriver.tap(commonPage.lblDynamic("RESTART"), GlobalVariable.G_timeoutLong)
//
////MobileDriver.tapEleByTestObject(commonPage.lblDynamic("RESTART"))
//
//Mobile.startExistingApplication(GlobalVariable.G_appId)
//AppiumDriver<?> driver = MobileDriverFactory.getDriver()
//println(driver.getPageSource())
//Mobile.comment(driver.getPageSource())

//MobileDriver.testFlowControl()

//Mobile.waitForElementPresent(findTestObject('Object Repository/Mobile/MAE/Android/Biometric Screen/btnDiffNo'), 10, FailureHandling.STOP_ON_FAILURE)
//Mobile.waitForElementPresent(findTestObject('Object Repository/Mobile/MAE/Android/Biometric Screen/btnDiffNo'), 10, FailureHandling.STOP_ON_FAILURE)

BasePage basePage = BasePage.getPage(TransferPage)
String title = basePage.getValueUI("HomePage.title")
Mobile.comment(title)


