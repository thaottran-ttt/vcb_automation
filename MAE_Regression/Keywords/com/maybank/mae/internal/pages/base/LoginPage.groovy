package com.maybank.mae.internal.pages.base

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.model.FailureHandling.CONTINUE_ON_FAILURE
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
import com.maybank.common.MobileDriver
import com.maybank.mae.internal.pages.base.OnboardingPage

import internal.GlobalVariable

public abstract class LoginPage extends BasePage{
	/*** Init Data */
	abstract TestObject btnLogInNow()
	abstract TestObject btnLogInHere()
	abstract TestObject txtUsername()
	abstract TestObject btnContinue()
	abstract TestObject btnYesItsMine()
	abstract TestObject txtPasswordRegister()
	abstract TestObject btnProceed()
	abstract TestObject txtPassword()
	abstract TestObject txtArrLoginDetailedText()
	abstract TestObject btnARRClose()
	abstract TestObject btnARRGotIt()

	String arrLoginText = 'Some Features Are Temporarily Unavailable'
	String UILblCustomerInfoErrorMsg = 'Customer Info is not available at the moment'
	String UILblYesItsMine = 'Yes,'
	String UILblPasswordErrorMsg = 'Your username and password do not match'
	String UILblPassword3TimeErrorMsg = "Invalid username/password. To reset, go to 'Forgot Login Details' (web) or 'Forgot Password' (app)"

	/*
	 * The logics for login are the same on both ios/android devices, so we define here
	 */
	CommonPage commonPage = getPage(CommonPage)
	OnboardingPage onboardingPage = getPage(OnboardingPage)

	public void signin(String username, String password, FailureHandling flowControl = defaultFailureHandling) {
		Mobile.delay(GlobalVariable.G_timeoutMiniShort)
		'Select Log In Now button'
		if(MobileDriver.waitForElementPresent(this.btnLogInNow(), GlobalVariable.G_timeoutMedium, FailureHandling.OPTIONAL)) {
			MobileDriver.tap(this.btnLogInNow(), GlobalVariable.G_timeoutLong, flowControl)
		}
		
		'Select Log In Here button'
		MobileDriver.tap(this.btnLogInHere(), GlobalVariable.G_timeoutLong, flowControl)
		Mobile.delay(GlobalVariable.G_timeoutShort)
		'Input Username'
		MobileDriver.setText(this.txtUsername(), username, GlobalVariable.G_timeoutLong, flowControl)
		'Select Continue button'
		if(MobileDriver.waitForElementPresent(this.btnContinue(), GlobalVariable.G_timeoutShort)) {
			MobileDriver.tap(this.btnContinue(), GlobalVariable.G_timeoutLong, flowControl)
		}
		'Select Yes It is Mine button'
		if(Mobile.waitForElementPresent(this.btnYesItsMine(), GlobalVariable.G_timeoutShort)) {
			MobileDriver.tap(this.btnYesItsMine(), GlobalVariable.G_timeoutLong, flowControl)	
		}else{
			MobileDriver.tap(commonPage.lblContainDynamic(UILblYesItsMine), GlobalVariable.G_timeoutLong, flowControl)
		}
		'Input Password Register'
		MobileDriver.setEncryptedText(this.txtPasswordRegister(), password, GlobalVariable.G_timeoutLong, flowControl)
		'Select Continue button'
		MobileDriver.tap(this.btnContinue(), GlobalVariable.G_timeoutLong, flowControl)
		Mobile.delay(GlobalVariable.G_timeoutMiniShort)
		'Check Customer Info Error Message exists'
		if(Mobile.waitForElementPresent(commonPage.lblDynamic(UILblCustomerInfoErrorMsg), GlobalVariable.G_timeoutShort)){
			'Handle Exception'
			handleError('System Exceptiop:(App Issue) - ' + UILblCustomerInfoErrorMsg, FailureHandling.STOP_ON_FAILURE)
		}
		'Wait for Proceed button present'
		if(MobileDriver.waitForElementPresent(this.btnProceed(), GlobalVariable.G_timeoutShort)) {
			'Select Proceed button'
			MobileDriver.tap(this.btnProceed(), GlobalVariable.G_timeoutLong, flowControl)
		}
	}
	
	public void invalidSignin(String username, String password, FailureHandling flowControl = defaultFailureHandling) {
		Mobile.delay(GlobalVariable.G_timeoutMiniShort)
		'Select Log In Now button'
		if(MobileDriver.waitForElementPresent(this.btnLogInNow(), GlobalVariable.G_timeoutMedium, FailureHandling.OPTIONAL)) {
			MobileDriver.tap(this.btnLogInNow(), GlobalVariable.G_timeoutLong, flowControl)
		}
		
		'Select Log In Here button'
		MobileDriver.tap(this.btnLogInHere(), GlobalVariable.G_timeoutLong, flowControl)
		Mobile.delay(GlobalVariable.G_timeoutShort)
		'Input Username'
		MobileDriver.setText(this.txtUsername(), username, GlobalVariable.G_timeoutLong, flowControl)
		'Select Continue button'
		if(MobileDriver.waitForElementPresent(this.btnContinue(), GlobalVariable.G_timeoutShort)) {
			MobileDriver.tap(this.btnContinue(), GlobalVariable.G_timeoutLong, flowControl)
		}
		'Select Yes It is Mine button'
		MobileDriver.tap(this.btnYesItsMine(), GlobalVariable.G_timeoutLong, flowControl)
		'Input Password Register'
		MobileDriver.setEncryptedText(this.txtPasswordRegister(), password, GlobalVariable.G_timeoutLong, flowControl)
		'Select Continue button'
		MobileDriver.tap(this.btnContinue(), GlobalVariable.G_timeoutLong, flowControl)
		Mobile.delay(GlobalVariable.G_timeoutMiniShort)
		'Verify the error message'
		Mobile.verifyElementExist(commonPage.lblDynamic(UILblPasswordErrorMsg), 0, flowControl)
		Mobile.takeScreenshot()
		
		'Verify error when attempt 3 times'
		2.times {
			'Input Password Register'
			Mobile.delay(GlobalVariable.G_timeoutShort)
			MobileDriver.setEncryptedText(this.txtPasswordRegister(), password, GlobalVariable.G_timeoutLong, flowControl)
			Mobile.delay(GlobalVariable.G_timeoutShort)
			'Select Continue button'
			MobileDriver.tap(this.btnContinue(), GlobalVariable.G_timeoutLong, flowControl)
		}
		Mobile.waitForElementPresent(commonPage.lblDynamic(UILblPassword3TimeErrorMsg), GlobalVariable.G_timeoutMiniShort, FailureHandling.CONTINUE_ON_FAILURE)
		Mobile.takeScreenshot()
		
		'Close the onboarding'
		if(MobileDriver.waitForElementPresent(onboardingPage.btnClose(), GlobalVariable.G_timeoutMedium, FailureHandling.OPTIONAL)) {
			MobileDriver.tap(onboardingPage.btnClose(), GlobalVariable.G_timeoutLong, flowControl)
		}
	}

	public void signinARR(flowControl) {
		'Verify Some Features Are Temporarily Unavailable exist'
		MobileDriver.verifyElementVisible(commonPage.lblContainDynamic(arrLoginText), GlobalVariable.G_timeoutMedium, flowControl)
		'Verify You can still view your balances... exist'
		MobileDriver.verifyElementVisible(txtArrLoginDetailedText(), GlobalVariable.G_timeoutMedium, flowControl)
		'Verify Close button exist'
		MobileDriver.verifyElementVisible(btnARRClose(), GlobalVariable.G_timeoutMedium, flowControl)
		'Select Got It button'
		MobileDriver.tap(this.btnARRGotIt(), GlobalVariable.G_timeoutMedium, flowControl)
	}

	public void setTxtPassword(String txtPassword, int timeOut = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		MobileDriver.setEncryptedText(this.txtPassword(), txtPassword, timeOut)
	}

	public void tapBtnContinue(int timeOut = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		MobileDriver.tap(this.btnContinue(), timeOut, flowControl)
	}
}
