package com.vcb.mobile.pages

import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.vcb.common.BaseKeyword
import com.vcb.common.MobileDriver
import com.vcb.mobile.internal.pages.base.BasePage
import com.vcb.mobile.internal.pages.base.CommonPage
import com.vcb.mobile.internal.pages.base.HomePage
import com.vcb.mobile.internal.pages.base.LoginPage
import com.vcb.mobile.internal.pages.base.PushNotificationPage
import com.vcb.mobile.internal.pages.base.Secure2uPage
import com.vcb.mobile.internal.pages.base.settingpage.SettingPage

import internal.GlobalVariable

public class Secure2u extends BaseKeyword {
	Secure2uPage secure2uPage = BasePage.getPage(Secure2uPage)
	HomePage homePage = BasePage.getPage(HomePage)
	SettingPage settingPage = BasePage.getPage(SettingPage)
	LoginPage loginPage = BasePage.getPage(LoginPage)
	CommonPage commonPage = BasePage.getPage(CommonPage)
	PushNotificationPage pushNotificationPage = BasePage.getPage(PushNotificationPage)

	String UISecure2uEnabled = 'Secure2u enabled'
	String UICallUsNow = 'Call Us Now'
	String UIAtTheNearestATMNow = 'at the nearest ATM now'
	String UIActivateViaATMCall = 'Activate via ATM/Call'
	String UIContinue = 'Continue'
	String UILater = 'Later'
	String UIOkay = 'Okay'
	String UIActivateNow = 'Activate Now'
	String UIDone = 'Done'

	@Keyword
	public void navigateHomePage(String PAN, String password, FailureHandling flowControl = defaultFailureHandling) {
		if (MobileDriver.waitForElementPresent(commonPage.lblDynamic(UISecure2uEnabled), GlobalVariable.G_timeoutMedium, FailureHandling.OPTIONAL)) {
			secure2uPage.tapBtnDynamic(UIContinue)
		} else {
			if (MobileDriver.waitForElementPresent(commonPage.lblDynamic(UICallUsNow), GlobalVariable.G_timeoutShort, FailureHandling.OPTIONAL)) {
				secure2uPage.tapBtnDynamic(UILater)
			} else if (MobileDriver.waitForElementPresent(commonPage.lblDynamic(UIOkay), GlobalVariable.G_timeoutShort, FailureHandling.OPTIONAL)) {
				secure2uPage.tapBtnDynamic(UIOkay)
			} else if (MobileDriver.verifyElementExist(commonPage.lblDynamic(UIActivateViaATMCall), GlobalVariable.G_timeoutShort, FailureHandling.OPTIONAL)) {
				secure2uPage.tapBtnDynamic(UIActivateNow)
			} else if (MobileDriver.waitForElementPresent(commonPage.lblDynamic(UIDone), GlobalVariable.G_timeoutShort, FailureHandling.OPTIONAL)){
				MobileDriver.tap(commonPage.lblDynamic(UIDone), GlobalVariable.G_timeoutMedium, flowControl)
			}else if (MobileDriver.waitForElementPresent(commonPage.lblNormalizeSpace(UIOkay), GlobalVariable.G_timeoutShort, FailureHandling.OPTIONAL)){
				MobileDriver.tap(commonPage.lblNormalizeSpace(UIOkay), GlobalVariable.G_timeoutMedium, flowControl)
			}

			activeSecure2uAtm(PAN, password)
		}
	}

	@Keyword
	public void enterIdNumber(String idNumber, FailureHandling flowControl = defaultFailureHandling) {
		secure2uPage.enterTxtYourIdNumber(idNumber)

		secure2uPage.tapBtnContinue()
	}

	@Keyword
	public void activeSecure2uAtm(String PAN, String password, FailureHandling flowControl = defaultFailureHandling) {
		info("PAN: " + PAN)
		'Call API to activate ATM'
		def activateResponse = WS.sendRequest(findTestObject('Object Repository/Postman/Activate S2U ATM', [('userId') : PAN]))
		WS.verifyResponseStatusCode(activateResponse, 200)
		def coolingoffResponse = WS.sendRequest(findTestObject('Object Repository/Postman/Uplift S2U Coolingoff', [('userId') : PAN]))
		WS.verifyResponseStatusCode(coolingoffResponse, 200)
		info("End Call API Acctive S2U")

		'Wait for server activate ATM'
		MobileDriver.delay(GlobalVariable.G_timeoutLong)

		'Close Notification if it displayed'
		pushNotificationPage.turnOffNotifcationIfDisplayed()

		'Click on Logout button'
		homePage.tapBtnLogout()

		'Click on Back to Dashboard button'
		homePage.tapBtnBackToDashboard()

		'Turn off notification if it displays'
		pushNotificationPage.turnOffNotifcationIfDisplayed()

		'Click on Setting button'
		homePage.tapBtnSetting()
		
		'Check enter PIN'
		commonPage.checkEnterS2UV5PIN()

		'Click on Security button'
		settingPage.tapLblSecurity()

		'Click on enter password'
		loginPage.setTxtPassword(password)

		'Click on Continue button'
		loginPage.tapBtnContinue()

		'Click on Secure2u button'
		settingPage.tapLblSecure2u()

		'Verify lblDisableSecure2u exist'
		settingPage.verifyLblDisableSecure2uExist()

		'Click on Cancel button'
		settingPage.tapBtnCancel()

		Mobile.delay(1)

		'Click on Back button'
		settingPage.tapBtnBack()
	}
}
