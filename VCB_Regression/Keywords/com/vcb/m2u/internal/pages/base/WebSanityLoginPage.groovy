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
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import com.vcb.common.WebUIKeyword

import internal.GlobalVariable

/**
 * Page object for the external ACME demo login page (https://acme-test.uipath.com/login),
 * used only by Test Cases/Website/Sanity/Web Login Smoke Test to prove the framework runs.
 */
public class WebSanityLoginPage extends WebBasePage {

	public static TestObject txtEmail() {
		return findTestObject('Object Repository/Website/Sanity/Login/txtEmail')
	}
	public static TestObject txtPassword() {
		return findTestObject('Object Repository/Website/Sanity/Login/txtPassword')
	}
	public static TestObject btnLogin() {
		return findTestObject('Object Repository/Website/Sanity/Login/btnLogin')
	}
	public static TestObject lblErrorMessage() {
		return findTestObject('Object Repository/Website/Sanity/Login/lblErrorMessage')
	}

	/**
	 * Fill in the login form and submit it.
	 * @param email dummy email, login is expected to fail
	 * @param password dummy password, login is expected to fail
	 * @return the validation message shown on the page after submit
	 */
	String login(String email, String password, FailureHandling flowControl = defaultFailureHandling) {
		setText(this.txtEmail(), email, flowControl)
		setText(this.txtPassword(), password, flowControl)

		WebUIKeyword.click(this.btnLogin(), flowControl)

		return WebUIKeyword.getText(this.lblErrorMessage(), flowControl)
	}
}
