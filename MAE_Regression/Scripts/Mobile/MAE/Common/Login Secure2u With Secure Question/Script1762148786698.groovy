import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.maybank.common.MobileDriver as MobileDriver
import com.maybank.mae.internal.pages.base.BasePage as BasePage
import com.maybank.mae.internal.pages.base.CommonPage as CommonPage
import com.maybank.mae.internal.pages.base.HomePage as HomePage
import com.maybank.mae.internal.pages.base.Secure2uPage as Secure2uPage
import internal.GlobalVariable as GlobalVariable

HomePage homePage = BasePage.getPage(HomePage)

Secure2uPage secure2uPage = BasePage.getPage(Secure2uPage)
CommonPage commonPage = BasePage.getPage(CommonPage)

'if skip restart App(DevOption Screen) After execute Test Case 1 then Test case 2 doesnt need to check login again'
if (!(GlobalVariable.G_restartAppAfterTestCase)) {
    GlobalVariable.G_restartAppAfterTestCase = !(GlobalVariable.G_restartAppAfterTestCase)

    return null
}

'Login'
boolean isLoggedInBefore = WebUI.callTestCase(findTestCase('Mobile/MAE/Common/Login With Secure Questions'), [('appId') : GlobalVariable.G_appPath, ('username') : username
		, ('password') : password, ('pinCode') : '111111', ('yourName') : yourName, ('accountName') : accountName, ('skipAccountLoginCheck') : false],
	FailureHandling.STOP_ON_FAILURE)

if(isLoggedInBefore) {
	commonPage.backToHome()
}

if(!GlobalVariable.G_s2uSucessful) {
	'Register S2U'
	WebUI.callTestCase(findTestCase('Mobile/MAE/S2U Activation/Secure2U Registration With Secure Question'), [('idNumber') : idNumber, ('password') : password
			, ('PAN') : PAN], FailureHandling.STOP_ON_FAILURE)
	
	GlobalVariable.G_s2uSucessful = true
	
	commonPage.backToHome()
}
