package com.vcb.mobile.internal.pages.base

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObject
import com.vcb.common.MobileDriver
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import internal.GlobalVariable

public abstract class SecurityQuestionPage extends BasePage {

	abstract TestObject inputAnswer()
	abstract TestObject buttonConfirm()
	CommonPage commonPage = BasePage.getPage(CommonPage)
	

	void inputAnswer(String answer, FailureHandling flowControl = defaultFailureHandling) {
		Mobile.setText(this.inputAnswer(), answer, GlobalVariable.G_timeoutMedium)
		Mobile.hideKeyboard(FailureHandling.OPTIONAL)
		commonPage.tap(commonPage.lblDynamic("No"), GlobalVariable.G_timeoutLong, FailureHandling.OPTIONAL)
		MobileDriver.takeScreenshot()
		MobileDriver.waitForElementPresent(this.buttonConfirm(), 10)
		MobileDriver.tap(this.buttonConfirm(), GlobalVariable.G_timeoutShort, flowControl)
	}
	
}
