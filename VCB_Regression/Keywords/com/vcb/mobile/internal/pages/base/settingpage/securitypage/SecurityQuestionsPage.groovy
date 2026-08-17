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

public abstract class SecurityQuestionsPage extends BasePage {

	CommonPage commonPage = getPage(CommonPage)

	String UIQuestion1 = 'Security question 1'
	String UIQuestion2 = 'Security question 2'
	String UIQuestion3 = 'Security question 3'

	void selectFirstQuestionForQuestion1(FailureHandling flowControl = defaultFailureHandling) {
		commonPage.selectDropdownSiblingGroupItemWithDefaultValue(UIQuestion1, GlobalVariable.G_timeoutMedium, flowControl)
	}

	void selectFirstQuestionForQuestion2(FailureHandling flowControl = defaultFailureHandling) {
		commonPage.selectDropdownSiblingGroupItemWithDefaultValue(UIQuestion2, GlobalVariable.G_timeoutMedium, flowControl)
	}

	void selectFirstQuestionForQuestion3(FailureHandling flowControl = defaultFailureHandling) {
		commonPage.selectDropdownSiblingGroupItemWithDefaultValue(UIQuestion3, GlobalVariable.G_timeoutMedium, flowControl)
	}

	void enterAnswerForQuestion1(String answer, FailureHandling flowControl = defaultFailureHandling) {
		MobileDriver.setText(commonPage.txtEditNo(1), answer, GlobalVariable.G_timeoutMedium, flowControl)
	}

	void enterAnswerForQuestion2(String answer, FailureHandling flowControl = defaultFailureHandling) {
		MobileDriver.setText(commonPage.txtEditNo(2), answer, GlobalVariable.G_timeoutMedium, flowControl)
	}

	void enterAnswerForQuestion3(String answer, FailureHandling flowControl = defaultFailureHandling) {
		MobileDriver.setText(commonPage.txtEditNo(3), answer, GlobalVariable.G_timeoutMedium, flowControl)
	}


	void changeSecurityQuestionsAndAnswer(String answer1, String answer2, String answer3, FailureHandling flowControl = defaultFailureHandling) {
		selectFirstQuestionForQuestion1()
		commonPage.delay(1)
		selectFirstQuestionForQuestion2()
		commonPage.delay(1)
		selectFirstQuestionForQuestion3()
		commonPage.delay(1)
		enterAnswerForQuestion1(answer1)
		commonPage.delay(1)
		enterAnswerForQuestion2(answer2)
		commonPage.delay(1)
		enterAnswerForQuestion3(answer3)
		Mobile.takeScreenshot()
		commonPage.tapContinueBtn()
	}
}
