import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.mobile.keyword.internal.MobileAbstractKeyword
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import com.maybank.common.MobileDriver
import com.maybank.mae.internal.pages.base.BasePage
import com.maybank.mae.internal.pages.base.CommonPage
import com.maybank.mae.internal.pages.base.HomePage
import com.maybank.mae.internal.pages.base.PinPage
import com.maybank.mae.internal.pages.base.Secure2uPage
import com.maybank.mae.pages.Secure2u
import com.maybank.utils.ListUtils

import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

CommonPage commonPage = BasePage.getPage(CommonPage)
HomePage homePage = BasePage.getPage(HomePage)
Secure2uPage secure2uPage = BasePage.getPage(Secure2uPage)
PinPage pinPage = BasePage.getPage(PinPage)

boolean isLoggedInBefore = WebUI.callTestCase(findTestCase('Mobile/MAE/Common/Login'), [('appId') : GlobalVariable.G_appPath, ('username') : username
		, ('password') : password, ('pinCode') : '111111', ('yourName') : 'Test', ('accountName') : accountName, ('skipAccountLoginCheck') : false],
	FailureHandling.STOP_ON_FAILURE)

if(isLoggedInBefore) {
	commonPage.backToHome()
}

'Select Secure2u'
homePage.tapSecure2u()

if (!MobileDriver.waitForElementPresent(commonPage.lblDynamic('Pending Secure2u Activation'), GlobalVariable.G_timeoutShort, FailureHandling.OPTIONAL)) {
	
	'Select Register Secure2u Now'
	secure2uPage.tapRegisterSecure2uOrActiveNow()
	MobileDriver.takeScreenshot()
	
	'Registered for Secure2u'
	secure2uPage.registerForS2u()
	
	'Select Request OTP'
	secure2uPage.tapBtnRequestOtp()
	
	'Enter OTP'
	CustomKeywords.'com.maybank.mae.pages.Otp.enterOtp'()
	
	'Enter Id number'
	CustomKeywords.'com.maybank.mae.pages.Secure2u.enterIdNumber'(idNumber)
	
	'wait to next Page'
	commonPage.waitLoading()
	
	'Verify Device name '
	secure2uPage.verifyLblDeviceNameExist()
	
	'Select next button'
	secure2uPage.tapBtnNextOrContinue()
	
	'Finish'
	if (MobileDriver.waitForElementPresent(commonPage.lblDynamic('Call Us Now'), GlobalVariable.G_timeoutShort, FailureHandling.OPTIONAL)) {
		secure2uPage.tapBtnDynamic('Later')
	} else if (MobileDriver.waitForElementPresent(commonPage.lblDynamic('Okay'), GlobalVariable.G_timeoutShort, FailureHandling.OPTIONAL)) {
		secure2uPage.tapBtnDynamic('Okay')
	} else if (MobileDriver.verifyElementExist(commonPage.lblDynamic('Activate via ATM/Call'), GlobalVariable.G_timeoutShort, FailureHandling.OPTIONAL)) {
		secure2uPage.tapBtnDynamic('Activate Now')
	} else if (MobileDriver.waitForElementPresent(commonPage.lblDynamic('Done'), GlobalVariable.G_timeoutShort, FailureHandling.OPTIONAL)){
		MobileDriver.tap(commonPage.lblDynamic('Done'), GlobalVariable.G_timeoutMedium, FailureHandling.OPTIONAL)
	}else if (MobileDriver.waitForElementPresent(commonPage.lblNormalizeSpace('Okay'), GlobalVariable.G_timeoutShort, FailureHandling.OPTIONAL)){
		MobileDriver.tap(commonPage.lblNormalizeSpace('Okay'), GlobalVariable.G_timeoutMedium, FailureHandling.OPTIONAL)
	}
	
	MobileDriver.delay(GlobalVariable.G_timeoutShort)
} else {
	commonPage.backToHome()
}

WS.sendRequest(findTestObject('Object Repository/Postman/Activate S2U ATM', [('userId') : PAN]))
'Wait for server activate ATM'
MobileDriver.delay(GlobalVariable.G_timeoutLong)

homePage.clickLogout()
homePage.tapBtnLogin()
pinPage.verifyPINPageShow()

pinPage.tapForgotPIN(password)
pinPage.verifyResetPINPageShow()
