package com.maybank.mae.pages

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
import com.maybank.mae.internal.pages.base.PinPage
import com.maybank.mae.internal.pages.base.OtpPage
import com.maybank.common.BaseKeyword
import com.maybank.mae.internal.pages.base.BasePage
import internal.GlobalVariable

public class Otp extends BaseKeyword {
	static final OtpPage PAGE_OBJECT = BasePage.getPage(OtpPage)

	@Keyword
	public static void enterOtp(FailureHandling flowControl = defaultFailureHandling) {
		PAGE_OBJECT.enterOtp(flowControl)
	}

	@Keyword
	public static void enterPINNo(String pinNO, FailureHandling flowControl = defaultFailureHandling) {
		PAGE_OBJECT.enterPINNO(pinNO, flowControl)
	}
}
