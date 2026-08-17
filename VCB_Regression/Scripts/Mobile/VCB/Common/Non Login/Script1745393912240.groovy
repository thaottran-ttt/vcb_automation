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
import com.kms.katalon.util.CryptoUtil as CryptoUtil
import groovy.time.BaseDuration as CryptoUtil
import com.vcb.mobile.internal.pages.base.LoginPage as LoginPage
import com.vcb.mobile.internal.pages.base.BasePage
import com.vcb.mobile.internal.pages.base.HomePage
import com.vcb.mobile.internal.pages.base.HomePage as BasePage

HomePage homePage = BasePage.getPage(HomePage)

//'Check if account is logged in'
//if(!skipAccountLoginCheck && homePage.isUserLoggedIn()) {
//	return true
//}
//
//if(homePage.isUserLoggedOut()) {
//	WebUI.callTestCase(findTestCase('Mobile/VCB/Common/CheckInputOTPPasswordSettings'), [('password') : password], FailureHandling.STOP_ON_FAILURE)
//	return true
//}

'Navigate To Login'
CustomKeywords.'com.vcb.mobile.pages.Onboarding.navigateToLogin'()
homePage.clickMaybeLater()


