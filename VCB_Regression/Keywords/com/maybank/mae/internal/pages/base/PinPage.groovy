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
import com.maybank.common.MobileDriver
import internal.GlobalVariable

public abstract class PinPage extends BasePage{
	abstract TestObject btnTick()
	abstract TestObject lblYourPin()
	abstract TestObject textEnterPin()

	CommonPage commonPage = getPage(CommonPage)
	static final String FORGOT_PIN = 'Forgot PIN'
	static final String VIEW_ALL_ACCOUNTS = 'View All Accounts'
	static final String RESET_MAE_APP_PIN = 'Reset MAE App PIN'
	static final String ENTERED_WRONG_PIN = "You've entered the wrong PIN. Please try again."
	static final String EXCEED_THREE_TIME = "You've exceed the number of tries. Reset PIN by clicking 'Forgot PIN'"
	
	public void enterPin(FailureHandling flowControl = defaultFailureHandling) {
		'Select Your PIN'
		MobileDriver.tap(this.lblYourPin(), GlobalVariable.G_timeoutLong, flowControl)
		'Select Tick Button'
		MobileDriver.tap(this.btnTick(), GlobalVariable.G_timeoutLong, flowControl)
		'Confirm 6-digit PIN'
		'Select Your PIN'
		MobileDriver.tap(this.lblYourPin(), GlobalVariable.G_timeoutLong, flowControl)
		'Select Tick Button'
		MobileDriver.tap(this.btnTick(), GlobalVariable.G_timeoutLong, flowControl)
	}
	
	/*
	 * Enter 3 times wrong PIN
	 */
	void verifyThreeTimeWrongPIN(String wrongPIN, FailureHandling flowControl = defaultFailureHandling) {
		commonPage.enterNO(wrongPIN, false, '', GlobalVariable.G_timeoutMedium, flowControl)
		MobileDriver.verifyElementVisible(commonPage.lblDynamic(ENTERED_WRONG_PIN), GlobalVariable.G_timeoutMiniShort, flowControl)
		commonPage.enterNO(wrongPIN, false, '', GlobalVariable.G_timeoutMedium, flowControl)
		MobileDriver.verifyElementVisible(commonPage.lblDynamic(ENTERED_WRONG_PIN), GlobalVariable.G_timeoutMiniShort, flowControl)
		commonPage.enterNO(wrongPIN, false, '', GlobalVariable.G_timeoutMedium, flowControl)
		MobileDriver.verifyElementVisible(commonPage.lblDynamic(EXCEED_THREE_TIME), GlobalVariable.G_timeoutMiniShort, flowControl)
		Mobile.takeScreenshot()
	}
	
	/*
	 * Enter wrong PIN
	 */
	void verifyEnterWrongPIN(String wrongPIN, String wrongToastMessage, FailureHandling flowControl = defaultFailureHandling) {
		'Enter Wrong PIN'
		commonPage.enterNO(wrongPIN, false, '', 0, flowControl)
		Mobile.takeScreenshot()
		'Verify Wrong Toast Message'
		MobileDriver.verifyElementVisible(commonPage.lblDynamic(wrongToastMessage), GlobalVariable.G_timeoutMedium, flowControl)
	}
	
	void enterPINValid(String strPIN, FailureHandling flowControl = defaultFailureHandling) {
		commonPage.enterNO(strPIN, false, '', 0, flowControl)
	}
	/*
	 * tap Forgot PIN
	 */
	void tapForgotPIN(String password, FailureHandling flowControl = defaultFailureHandling){
		'Tap Forgot Password'
		MobileDriver.tap(commonPage.lblDynamic(FORGOT_PIN), GlobalVariable.G_timeoutMedium, flowControl)
		
		'Check Enter Password'
		commonPage.checkEnterPassword(password, GlobalVariable.G_timeoutMedium, flowControl)	
	}
	
	/*
	 * Create New PIN
	 */
	void createYourPIN(String newPIN, List verifyUICreatePINData, List verifyUIConfirmPINData, FailureHandling flowControl = defaultFailureHandling) {
		'Create your PIN for the app'
		verifyElements(verifyUICreatePINData, commonPage.lblDynamicPath(), GlobalVariable.G_timeoutMedium, flowControl)
		commonPage.enterNO(newPIN)
		'Confirm your new 6-digit PIN'
		verifyElements(verifyUIConfirmPINData, commonPage.lblDynamicPath(), GlobalVariable.G_timeoutMedium, flowControl)
		commonPage.enterNO(newPIN)
	}
	
	/*
	 * verify Login With New PIN
	 */
	void verifyLoginWithNewPIN(String newPIN, FailureHandling flowControl = defaultFailureHandling) {
		'Create your PIN for the app'
		commonPage.enterNO(newPIN, false)
		
		'Verify Wrong Toast Message'
		MobileDriver.verifyElementVisible(commonPage.lblDynamic(VIEW_ALL_ACCOUNTS), GlobalVariable.G_timeoutMedium, flowControl)
		Mobile.takeScreenshot()
	}
	
	/*
	 * Verify PIN page Show
	 */
	void verifyPINPageShow(FailureHandling flowControl = defaultFailureHandling) {
		MobileDriver.waitForElementPresent(commonPage.lblDynamic(FORGOT_PIN), GlobalVariable.G_timeoutMedium, flowControl)
		MobileDriver.takeScreenshot()
		MobileDriver.verifyElementExist(commonPage.lblDynamic(FORGOT_PIN), GlobalVariable.G_timeoutMiniShort, flowControl)
	}
	void verifyResetPINPageShow(FailureHandling flowControl = defaultFailureHandling) {
		MobileDriver.waitForElementPresent(commonPage.lblDynamic(RESET_MAE_APP_PIN), GlobalVariable.G_timeoutMedium, flowControl)
		MobileDriver.takeScreenshot()
		MobileDriver.verifyElementExist(commonPage.lblDynamic(RESET_MAE_APP_PIN), GlobalVariable.G_timeoutMiniShort, flowControl)
	}
}
