package com.maybank.mae.internal.pages.base.accountpage

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

public abstract class KillSwitchPage extends BasePage {

	CommonPage commonPage = getPage(CommonPage)

	String UINext = 'NEXT'
	String UICancel = 'Cancel'
	String UIDeactivateNow = 'Deactivate Now'
	String UIBlockCreditCard = 'Block Credit Card'
	String UIDeactivateAccounts = 'Deactivate Accounts'
	String UIDeactivateM2UAccess = 'Deactivate M2U Access'
	String UINoActiveCreditCard = 'No active credit card to block.'
	String UINoActiveAccount = 'No active savings account to deactivate.'

	public void tapLblBlockCreditCard(int timeOut = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		MobileDriver.tap(commonPage.lblDynamic(UIBlockCreditCard), timeOut, flowControl)
	}

	public void tapLblDeactivateAccounts(int timeOut = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		MobileDriver.tap(commonPage.lblDynamic(UIDeactivateAccounts), timeOut, flowControl)
	}

	public void tapLblDeactivateM2UAccess(int timeOut = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		MobileDriver.tap(commonPage.lblDynamic(UIDeactivateM2UAccess), timeOut, flowControl)
	}

	public void tapBtnNext(int timeOut = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		MobileDriver.tap(commonPage.btnDynamic(UINext), timeOut, flowControl)
	}

	public void tapBtnDeactivateNow(int timeOut = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		MobileDriver.tap(commonPage.btnDynamic(UIDeactivateNow), timeOut, flowControl)
	}

	public void tapBtnCancel(int timeOut = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		MobileDriver.tap(commonPage.btnDynamic(UICancel), timeOut, flowControl)
	}

	/**
	 * Verify No Active Credit Card Message exist
	 * @return true if No Active Credit Card Message exist, false if not exist
	 */
	public boolean verifyLblNoActiveCreditCardMessageExist(int timeOut = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		return MobileDriver.verifyElementExist(commonPage.lblDynamic(UINoActiveCreditCard), timeOut, flowControl)
	}

	/**
	 * Verify No Active Account Message exist
	 * @return true if No Active Account Message exist, false if not exist
	 */
	public boolean verifyLblNoActiveAccountExist(int timeOut = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		return MobileDriver.verifyElementExist(commonPage.lblDynamic(UINoActiveAccount), timeOut, flowControl)
	}
}
