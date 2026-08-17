package com.maybank.mae.internal.pages.base.settingpage.securitypage

import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.maybank.common.MobileDriver
import com.maybank.mae.internal.pages.base.BasePage
import com.maybank.mae.internal.pages.base.CommonPage
import com.maybank.mae.internal.pages.base.LoginPage
import com.maybank.mae.internal.pages.base.OtpPage
import io.appium.java_client.AppiumDriver as AppiumDriver
import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory

import org.openqa.selenium.By
import org.openqa.selenium.WebElement as WebElement



import internal.GlobalVariable

public abstract class BiometricPage extends BasePage {

	CommonPage commonPage = getPage(CommonPage)

	String UIBiometricDescription = 'Enable biometric ID for instant access'
	String UIEnable = 'Enable Now'
	String UISetupLater = 'Set Up Later'

	/**
	 * Verify Biometric Description exist
	 * @return true if Biometric Description exist, false if not exist
	 */
	public boolean verifyLblBiometricDescriptionExist(int timeOut = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		return MobileDriver.verifyElementExist(commonPage.lblDynamic(UIBiometricDescription), timeOut, flowControl)
	}

	/**
	 * Tap Enable Now button
	 */
	public void tapBtnEnableNow(int timeOut = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		MobileDriver.tap(commonPage.btnDynamic(UIEnable), timeOut, flowControl)
	}


	/**
	 * Tap Set Up Later button
	 */
	public void tapBtnSetUpLater(int timeOut = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		MobileDriver.tap(commonPage.btnDynamic(UISetupLater), timeOut, flowControl)
	}
}
