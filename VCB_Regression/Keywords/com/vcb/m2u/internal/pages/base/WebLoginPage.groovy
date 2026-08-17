package com.vcb.m2u.internal.pages.base

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
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import com.vcb.common.WebUIKeyword

import internal.GlobalVariable

public class WebLoginPage extends WebBasePage{

	WebCommonPage webCommonPage = getPage(WebCommonPage)

	final String MY_ACCOUNTS = "MY ACCOUNTS"
	final String FORGOT_LOGIN_DETAILS = "Forgot Login Details"
	final String FORGOT_TEXT_CLASS = "LoginUsername---forgot-text"
	final String MODAL_HEADER = "modal-header"
	final String CARD_TYPE_CLASS = "VerifyAccessNumberPin"
	final String PIN_FIELD = "PIN"
	final String ACCESS_NUMBER_TYPE = "Access Number"
	final String DROPDOWN = "RenderDesktopDropdown"
	
	public static TestObject txtUsername() {
		return findTestObject('Website/M2U/Browsers/Login Page/txtUsername')
	}
	public static TestObject txtPassword() {
		return findTestObject('Website/M2U/Browsers/Login Page/txtPassword')
	}
	public static TestObject lblHello() {
		return findTestObject('Website/M2U/Browsers/Login Page/lblHelloText')
	}
	public static TestObject btnLogin() {
		return findTestObject('Website/M2U/Browsers/Login Page/btnLogin')
	}
	public static TestObject btnYes() {
		return findTestObject('Website/M2U/Browsers/Login Page/btnYes')
	}

	/**
	 * Logs into the application with the specified credentials.
	 *
	 * Steps:
	 * 1. Check if a greeting message appears after clicking the login button.
	 *    - If it does, click "Yes".
	 *    - If not, proceed directly to the password entry step.
	 *
	 * @param username    the username to be used for login
	 * @param password    the password associated with the username
	 * @param timeout     the maximum time (in seconds) to wait for the login process to complete
	 * @param flowControl (optional) defines the flow control behavior in case of failure,
	 *                    defaulting to the global `defaultFailureHandling`
	 *
	 * @return void
	 */
	void login(String username, String password, int timeout, FailureHandling flowControl = defaultFailureHandling) {
		setText(this.txtUsername(), username, flowControl)

		WebUI.delay(GlobalVariable.G_timeoutShort)

		WebUIKeyword.click(this.btnLogin(), flowControl)

		webCommonPage.waitLoading()

		if(webCommonPage.waitForElementPresent(this.lblHello(), GlobalVariable.G_timeoutMedium, FailureHandling.OPTIONAL)) {

			String lblHello = WebUI.getText(this.lblHello(), flowControl)

			WebUIKeyword.click(this.btnYes())
		}

		WebUIKeyword.setEncryptedText(this.txtPassword(), password, flowControl)

		WebUIKeyword.click(this.btnLogin(), flowControl)

		webCommonPage.waitLoading(GlobalVariable.G_timeoutLong)

		WebUIKeyword.takeScreenshot()
	}

	boolean isLoggedIn() {
		return WebUIKeyword.waitForElementPresent(webCommonPage.lblDynamic(MY_ACCOUNTS), GlobalVariable.G_timeoutShort, FailureHandling.OPTIONAL)
	}
	
	void verifyForgotLoginLink() {
		WebUIKeyword.click(webCommonPage.lblContainClassAndContainText(FORGOT_TEXT_CLASS, FORGOT_LOGIN_DETAILS))
		WebUI.delay(GlobalVariable.G_timeoutShort)
		WebUIKeyword.verifyElementVisible(webCommonPage.lblContainClass(MODAL_HEADER), defaultFailureHandling)
		WebUIKeyword.verifyElementVisible(webCommonPage.lblDynamic(PIN_FIELD, 2), defaultFailureHandling)
		WebUIKeyword.click(webCommonPage.lblContainClass(DROPDOWN))
		WebUIKeyword.verifyElementVisible(webCommonPage.lblContainClassAndContainText(CARD_TYPE_CLASS, ACCESS_NUMBER_TYPE), defaultFailureHandling)
	}
}