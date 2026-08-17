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
import com.maybank.common.BaseKeyword
import com.maybank.common.MobileDriver
import com.maybank.mae.internal.pages.base.BasePage
import com.maybank.mae.internal.pages.base.CommonPage
import com.maybank.mae.internal.pages.base.HomePage
import com.maybank.mae.internal.pages.base.Secure2uPage

import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys
if(GlobalVariable.G_isTacM2U) {
	return
}
HomePage homePage = BasePage.getPage(HomePage)
Secure2uPage secure2uPage = BasePage.getPage(Secure2uPage)
CommonPage commonPage = BasePage.getPage(CommonPage)


'Login'
boolean isLoggedInBefore = WebUI.callTestCase(findTestCase('Mobile/MAE/Common/Login'), [('appId') : GlobalVariable.G_appPath, ('username') : username
		, ('password') : password, ('pinCode') : '111111', ('yourName') : yourName, ('accountName') : accountName, ('skipAccountLoginCheck') : false],
	BaseKeyword.defaultFailureHandling)

if(!GlobalVariable.G_s2uSucessful) {
	'Register S2U'
	WebUI.callTestCase(findTestCase('Mobile/MAE/S2U Activation/Secure2U Registration'), [('idNumber') : idNumber, ('password') : password
			, ('PAN') : PAN], BaseKeyword.defaultFailureHandling)
	
	GlobalVariable.G_s2uSucessful = true
	
	commonPage.backToHome()
}