package com.maybank.mae.internal.pages.base

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import com.maybank.assertions.BaseAssert
import com.maybank.common.MobileDriver
import internal.GlobalVariable

public abstract class OtpPage extends BasePage {
	abstract TestObject lblYourOtp()
	abstract TestObject btnTick()
	abstract TestObject lblPINMessage()
	CommonPage commonPage = getPage(CommonPage)

	public void enterOtp(FailureHandling flowControl = defaultFailureHandling) {
		Mobile.delay(GlobalVariable.G_timeoutMiniShort)
		'Select Your Otp'
		MobileDriver.tap(this.lblYourOtp(), GlobalVariable.G_timeoutLong, flowControl)
		'Select Tick Button'
		MobileDriver.tap(this.btnTick(), GlobalVariable.G_timeoutLong, flowControl)
	}

	void enterPINNO(String pinNO, FailureHandling flowControl = defaultFailureHandling) {
		'Wait Pin Message Present'
		if (MobileDriver.waitForElementPresent(this.lblPINMessage(), GlobalVariable.G_timeoutShort, FailureHandling.OPTIONAL)) {
			def length = pinNO.length()

			for (def i = 0; i < length; i++) {

				def tapNo = pinNO[i]
				'Select Pin Message'
				Mobile.tap(commonPage.lblDynamic(tapNo), GlobalVariable.G_timeoutShort)
			}

			Mobile.delay(4)
			Mobile.takeScreenshot()
		}
	}

	void tapTickBtn(FailureHandling flowControl = defaultFailureHandling) {
		'Select Tick Button'
		MobileDriver.tap(this.btnTick(), GlobalVariable.G_timeoutShort, flowControl)
	}


	boolean verifyResetOTP(String text, String subTextVerify, FailureHandling flowControl = defaultFailureHandling) {
		'Verify Otp Message Visible'
		MobileDriver.verifyElementVisible(commonPage.lblDynamic(text), GlobalVariable.G_timeoutLong, flowControl)
		'Get Otp'
		String initOTP = commonPage.getTextBySubText(subTextVerify)
		'Select Otp Text'
		commonPage.tapText(text, GlobalVariable.G_timeoutLong, flowControl)
		Mobile.delay(3)
		'Get Otp After'
		String afterOTP = commonPage.getTextBySubText(subTextVerify)
		'Verify Reset OTP'
		BaseAssert.notEquals(initOTP, afterOTP, 'Verify Reset OTP')
	}
}
