package com.vcb.m2u.internal.pages.safari

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import org.openqa.selenium.Keys

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
import com.vcb.m2u.internal.pages.base.WebLoginPage

import internal.GlobalVariable

public class SafariWebLoginPage extends WebLoginPage {
	
	@Override
	void login(String username, String password, int timeout, FailureHandling flowControl = defaultFailureHandling) {
		setText(this.txtUsername(), username, flowControl)

		WebUI.delay(GlobalVariable.G_timeoutShort)

		WebUI.sendKeys(this.txtUsername(), Keys.chord(Keys.ENTER))

		WebUIKeyword.delay(GlobalVariable.G_timeoutShort)
		
		WebUI.verifyElementPresent(this.lblHello(), timeout, flowControl)

		String lblHello = WebUI.getText(this.lblHello(), flowControl)

		WebUIKeyword.click(this.btnYes())

		WebUIKeyword.setEncryptedText(this.txtPassword(), password, flowControl)

		WebUIKeyword.click(this.btnLogin(), flowControl)
	}
}
