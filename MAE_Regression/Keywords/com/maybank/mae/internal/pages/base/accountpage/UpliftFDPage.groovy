package com.maybank.mae.internal.pages.base.accountpage

import com.kms.katalon.core.configuration.RunConfiguration
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testobject.TestObject
import com.maybank.common.MobileDriver
import com.maybank.mae.internal.pages.base.BasePage
import com.maybank.mae.internal.pages.base.CommonPage
import internal.GlobalVariable

public abstract class UpliftFDPage extends BasePage {
	CommonPage commonPage = getPage(CommonPage)


	abstract TestObject btnDropDown(int index, String label)
	abstract TestObject lblNormalizeSpace(String text)
	abstract TestObject imgReceiptIcon()
	abstract TestObject frmTransactionHistory()

	/**
	 * Select dropdown value
	 */
	void tapDropDownButton(String label, String value, int index ,FailureHandling flowControl = defaultFailureHandling ) {
		'Open dropdown'
		MobileDriver.tap(this.btnDropDown(index, label), GlobalVariable.G_timeoutLong, FailureHandling.OPTIONAL)
		'If value is not empty, scroll to value and tap'
		'Else select first value'
		if(value != "") {
			scrollToValue(value, GlobalVariable.G_timeoutLong)
		}
		'Select Done'
		commonPage.tapButton("Done")
	}

	/**
	 * Scroll to value and tap
	 */
	void scrollToValue(String value, int timeout = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		'If value is not present, scroll to value'
		if(!(MobileDriver.waitForElementPresent(lblNormalizeSpace(value), timeout, FailureHandling.OPTIONAL))){
			info("Scroll to ${value}")
			Mobile.scrollToText(value, FailureHandling.OPTIONAL)
		}
		info("Tap on ${value}")
		'Select value'
		MobileDriver.tap(lblNormalizeSpace(value), timeout, flowControl)
	}

	/**
	 * Confirmation transaction
	 */
	void confirmTransaction(FailureHandling flowControl = defaultFailureHandling) {
		commonPage.waitLoading(GlobalVariable.G_timeoutMedium, flowControl)
		'Select Proceed'
		MobileDriver.tapAndLog(commonPage.btnDynamic("Proceed"), GlobalVariable.G_timeoutLong, "Click Proceed", flowControl)
		'If Confirmation is present, tap Uplift Placement'
		if(MobileDriver.waitForElementPresent(commonPage.lblDynamic("Confirmation"), GlobalVariable.G_timeoutLong,  FailureHandling.OPTIONAL)) {
			MobileDriver.tapAndLog(commonPage.btnDynamic("Uplift Placement"), GlobalVariable.G_timeoutLong, "Click Uplift Placement", flowControl)
		}
	}
	/**
	 * View receipt
	 */
	void viewReceipt(FailureHandling flowControl = defaultFailureHandling) {
		'If Receipt Icon is present, tap Share Receipt'
		if(MobileDriver.waitForElementPresent(commonPage.btnDynamic("Share Receipt"), GlobalVariable.G_timeoutLong,  FailureHandling.OPTIONAL)) {
			MobileDriver.tapAndLog(commonPage.btnDynamic("Share Receipt"), GlobalVariable.G_timeoutLong, "Click Share Receipt", flowControl)
		}
	}
	/**
	 * Verify transaction
	 */
	void verifyTransaction(FailureHandling flowControl = defaultFailureHandling) {
		verifyElementExist(commonPage.lblContainDynamic("RM"), GlobalVariable.G_timeoutLong, flowControl)
	}
	/**
	 * Verify transaction fail
	 */
	void verifyTransactionFail(FailureHandling flowControl = defaultFailureHandling) {
		verifyElementExist(commonPage.lblContainDynamic("failed"), GlobalVariable.G_timeoutLong, flowControl)
	}
}
