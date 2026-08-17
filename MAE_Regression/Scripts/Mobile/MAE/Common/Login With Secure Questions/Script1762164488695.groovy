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
import com.maybank.mae.internal.pages.base.LoginPage as LoginPage
import com.maybank.mae.internal.pages.base.BasePage
import com.maybank.mae.internal.pages.base.HomePage
import com.maybank.mae.internal.pages.base.HomePage as BasePage

HomePage homePage = BasePage.getPage(HomePage)

'Check if account is logged in'
if(!skipAccountLoginCheck && homePage.isUserLoggedIn()) {
	return true
}

if(homePage.isUserLoggedOut()) {
	WebUI.callTestCase(findTestCase('Mobile/MAE/Common/CheckInputOTPPasswordSettings'), [('password') : password], FailureHandling.STOP_ON_FAILURE)
	return true
}

'Navigate To Login'
CustomKeywords.'com.maybank.mae.pages.Onboarding.navigateToLogin'()
homePage.clickMaybeLater()

'Login to the applcation'
CustomKeywords.'com.maybank.mae.pages.Login.login'(username, password)

'Enter Pin'
CustomKeywords.'com.maybank.mae.pages.Pin.enterPin'()

'Set Biometric'
CustomKeywords.'com.maybank.mae.pages.Biometric.setup'()

'Enter Otp'
CustomKeywords.'com.maybank.mae.pages.Otp.enterOtp'()

Mobile.delay(5)

'Enter Answer For Security Question'
CustomKeywords.'com.maybank.mae.pages.SecurityQuestion.inputAnswer'(securityanswer)

'Enter Your Name'
CustomKeywords.'com.maybank.mae.pages.WelcomeOnboard.enterYourName'(yourName, accountName)

'VerifyElement the respective user' 
homePage.verifyRespectiveUser(yourName)

GlobalVariable.G_s2uSucessful = false

return false
