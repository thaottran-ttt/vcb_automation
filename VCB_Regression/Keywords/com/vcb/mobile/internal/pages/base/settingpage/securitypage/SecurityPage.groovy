package com.vcb.mobile.internal.pages.base.settingpage.securitypage

import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.vcb.common.MobileDriver
import com.vcb.mobile.internal.pages.base.BasePage
import com.vcb.mobile.internal.pages.base.CommonPage
import com.vcb.mobile.internal.pages.base.LoginPage
import com.vcb.mobile.internal.pages.base.OtpPage
import io.appium.java_client.AppiumDriver as AppiumDriver
import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory

import org.openqa.selenium.By
import org.openqa.selenium.WebElement as WebElement



import internal.GlobalVariable

public abstract class SecurityPage extends BasePage {

	CommonPage commonPage = getPage(CommonPage)

	String UISecure2u = 'Secure2u'
	String UIAppBiometricLogin = 'App Biometric Login'
	String UIChangeAppPin = 'Change App PIN'
	String UIChangePassword = 'Change Password'
	String UISecurityQuestions = 'Security Questions'
	String UIKillSwitch = 'Kill Switch'

	String UIDisableSecure2u = 'Disable Secure2u'
	String UICancel = 'Cancel'
	String UIConfirm = 'Confirm'
	String UIPinUpdatedSuccessfully = 'PIN successfully updated.'

	/**
	 * Tap Secure2u
	 */
	public void tapLblSecure2u(int timeOut = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		MobileDriver.tap(commonPage.lblDynamic(UISecure2u), timeOut, flowControl)
	}

	/**
	 * Tap App Biometric Login
	 */
	public void tapLblAppBiometricLogin(int timeOut = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		MobileDriver.tap(commonPage.lblDynamic(UIAppBiometricLogin), timeOut, flowControl)
	}

	/**
	 * Tap Change App Pin
	 */
	public void tapLblChangeAppPin(int timeOut = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		MobileDriver.tap(commonPage.lblDynamic(UIChangeAppPin), timeOut, flowControl)
	}

	/**
	 * Tap Change Password
	 */
	public void tapLblChangePassword(int timeOut = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		MobileDriver.scrollToText(UIChangePassword)
		MobileDriver.tap(commonPage.lblDynamic(UIChangePassword), timeOut, flowControl)
	}

	/**
	 * Tap Security Questions
	 */
	public void tapLblSecurityQuestions(int timeOut = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		MobileDriver.tap(commonPage.lblDynamic(UISecurityQuestions), timeOut, flowControl)
	}

	/**
	 * Tap Kill Switch
	 */
	public void tapLblKillSwitch(int timeOut = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		MobileDriver.tap(commonPage.lblDynamic(UIKillSwitch), timeOut, flowControl)
	}

	/**
	 * Tap Cancel button
	 */
	public void tapBtnCancel(int timeOut = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		MobileDriver.tap(commonPage.btnDynamic(UICancel), timeOut, flowControl)
	}

	/**
	 * Tap Confirm button
	 */
	public void tapBtnConfirm(int timeOut = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		MobileDriver.tap(commonPage.btnDynamic(UIConfirm), timeOut, flowControl)
	}

	/**
	 * Verify Disable Secure2u exist
	 * @return true if Disable Secure2u exist, false if not exist
	 */
	public boolean verifyLblDisableSecure2uExist(int timeOut = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		return MobileDriver.verifyElementExist(commonPage.lblDynamic(UIDisableSecure2u), timeOut, flowControl)
	}

	/**
	 * Verify Pin Updated Successfully Message exist
	 * @return true if Pin Updated Successfully Message exist, false if not exist
	 */
	public boolean verifyLblPinUpdatedSuccessfullyMessageExist(int timeOut = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		return MobileDriver.verifyElementExist(commonPage.lblDynamic(UIPinUpdatedSuccessfully), timeOut, flowControl)
	}
}
