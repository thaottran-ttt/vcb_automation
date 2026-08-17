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

public abstract class ChangePasswordPage extends BasePage {
	
	String UIShowPassword = 'Show password'
	CommonPage commonPage = getPage(CommonPage)

	void enterCurrentPassword(String currentPassword, FailureHandling flowControl = defaultFailureHandling) {
		MobileDriver.setEncryptedText(commonPage.txtEditNo(1), currentPassword, GlobalVariable.G_timeoutMedium, flowControl)
	}

	void enterNewPassword(String newPassword, FailureHandling flowControl = defaultFailureHandling) {
		MobileDriver.setEncryptedText(commonPage.txtEditNo(2), newPassword, GlobalVariable.G_timeoutMedium, flowControl)
	}

	void enterConfirmPassword(String confirmPassword, FailureHandling flowControl = defaultFailureHandling) {
		MobileDriver.setEncryptedText(commonPage.txtEditNo(3), confirmPassword, GlobalVariable.G_timeoutMedium, flowControl)
	}

	void tapShowPassword(FailureHandling flowControl = defaultFailureHandling) {
		MobileDriver.tapAtPositionWithOffset(commonPage.lblDynamic(UIShowPassword), GlobalVariable.G_timeoutMedium, -60, 5,flowControl)
	}
	
	public void changePassword(String currentPassword, String newPassword, String confirmPassword, FailureHandling flowControl = defaultFailureHandling) {
		enterCurrentPassword(currentPassword)
		enterNewPassword(newPassword)
		enterConfirmPassword(confirmPassword)
		Mobile.takeScreenshot()
	}
}
