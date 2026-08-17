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
import com.vcb.common.MobileDriver
import com.vcb.mobile.internal.pages.base.BasePage
import com.vcb.mobile.internal.pages.base.CommonPage
import com.vcb.mobile.internal.pages.base.HomePage
import com.vcb.mobile.internal.pages.base.settingpage.SettingPage

import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

HomePage homePage = BasePage.getPage(HomePage)
SettingPage settingPage = BasePage.getPage(SettingPage)
CommonPage commonPage = BasePage.getPage(CommonPage)

settingPage.UIVcb2u = security;

if(commonPage.waitForElementPresent(commonPage.lblDynamic(loginWithPin), GlobalVariable.G_timeoutMedium)) {
	'Enter pin'
	commonPage.enterNO(pinCode, false)
	'Back to home'
	commonPage.backToHome()
}

'if skip restart App(DevOption Screen) After execute Test Case 1 then Test case 2 doesnt need to check login again'
if (!(GlobalVariable.G_restartAppAfterTestCase)) {
    GlobalVariable.G_restartAppAfterTestCase = !(GlobalVariable.G_restartAppAfterTestCase)

    return null
}

'Login'
boolean isLoggedInBefore = WebUI.callTestCase(findTestCase('Mobile/VCB/Common/Login'), [('appId') : GlobalVariable.G_appPath, ('username') : username
		, ('password') : password, ('pinCode') : pinCode, ('yourName') : yourName, ('accountName') : accountName, ('skipAccountLoginCheck') : false],
	FailureHandling.STOP_ON_FAILURE)

if(isLoggedInBefore) {
	commonPage.backToHome()
}

if(!GlobalVariable.G_s2uSucessful) {
	'Register S2U'
	WebUI.callTestCase(findTestCase('Mobile/VCB/S2U Activation/Secure2U Registration'), [('idNumber') : idNumber, ('password') : password
			, ('PAN') : PAN], FailureHandling.STOP_ON_FAILURE)
	
	GlobalVariable.G_s2uSucessful = true
	
	commonPage.backToHome()
}

'Tap to setting'
homePage.tapBtnSetting()