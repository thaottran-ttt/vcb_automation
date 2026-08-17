package com.vcb.mobile.pages

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
import com.vcb.common.BaseKeyword
import com.vcb.mobile.internal.pages.base.BasePage
import com.vcb.mobile.internal.pages.base.HomePage
import com.vcb.mobile.internal.pages.base.LoginPage

import internal.GlobalVariable

public class Login extends BaseKeyword {
	
	LoginPage pageObj = BasePage.getPage(LoginPage)
	
	@Keyword
	public void login(String username, String password, FailureHandling flowControl = defaultFailureHandling) {
		pageObj.signin(username, password, flowControl)
	}
	
	//void clickongotitbuttonifpopdislay()
	@Keyword
	public void loginARR(FailureHandling flowControl = defaultFailureHandling) {
		pageObj.signinARR(flowControl)
	}
	
	@Keyword
	public void invalidSignin(String username, String password, FailureHandling flowControl = defaultFailureHandling) {
		pageObj.invalidSignin(username, password, flowControl)
	}
		
}
