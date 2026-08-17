import com.kms.katalon.core.model.FailureHandling
import com.maybank.common.MobileDriver
import com.maybank.mae.internal.pages.base.BasePage
import com.maybank.mae.internal.pages.base.CommonPage
import com.maybank.mae.internal.pages.base.HomePage
import com.maybank.mae.internal.pages.base.Secure2uPage

import internal.GlobalVariable

HomePage homePage = BasePage.getPage(HomePage)

Secure2uPage secure2uPage = BasePage.getPage(Secure2uPage)
CommonPage commonPage = BasePage.getPage(CommonPage)

if(!homePage.waitSecure2U() && homePage.waitForElementPresent(commonPage.lblHomeOrAgree(), GlobalVariable.G_timeoutShort, FailureHandling.OPTIONAL)) {
	return false
}

'Select Secure2u'
homePage.tapSecure2u()

MobileDriver.delay(GlobalVariable.G_timeoutMiniShort)
MobileDriver.takeScreenshot()

'Verify label Your Secure TAC Number exist: if true -> secure2u is actived already.'
if (secure2uPage.waitForSecureTacNumberPresent()) {
	secure2uPage.backToHome()
	
	'Wait for Account Balance present'
	if(MobileDriver.waitForElementPresent(commonPage.lblDynamic(homePage.UIRMHide), GlobalVariable.G_timeoutShort, FailureHandling.OPTIONAL)) {
		'Select Eye Button'
		MobileDriver.tap(homePage.btnEye(), GlobalVariable.G_timeoutLong, FailureHandling.OPTIONAL)
	}
	MobileDriver.takeScreenshot()
	
} else {
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
	
	'Enter Answer For Security Question'
	CustomKeywords.'com.maybank.mae.pages.SecurityQuestion.inputAnswer'(securityanswer)

    'Navigate to home page'
    CustomKeywords.'com.maybank.mae.pages.Secure2u.navigateHomePage'(PAN, password)
	
	'Wait for Account Balance present'
	if(MobileDriver.waitForElementPresent(commonPage.lblDynamic(homePage.UIRMHide), GlobalVariable.G_timeoutShort, FailureHandling.OPTIONAL)) {
		'Select Eye Button'
		MobileDriver.tap(homePage.btnEye(), GlobalVariable.G_timeoutLong, FailureHandling.OPTIONAL)
	}
	MobileDriver.takeScreenshot()
}

return true