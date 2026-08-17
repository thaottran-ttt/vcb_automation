package com.vcb.mobile.internal.pages.base

import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testobject.TestObject
import com.vcb.common.MobileDriver

import internal.GlobalVariable

public abstract class Secure2uPage extends BasePage   {
	abstract TestObject lblSecureTacNumber(String lblName)
	abstract TestObject btnBack()
	abstract TestObject btnRegisterSecure2u(String lblName)
	abstract TestObject btnRequestOtp(String lblName)
	abstract TestObject lblDeviceName(String lblName)
	abstract TestObject btnNext(String lblName)
	abstract TestObject btnContinue(String lblName)
	abstract TestObject btnDynamic(String lblName)
	abstract TestObject lblRegisterStatus(String lblName)
	abstract TestObject txtYourIdNumber()

	String UIYourSecureTACNumber = 'Your Secure TAC Number'
	String UIRegisterSecure2uNow = 'Register Secure2u Now'
	String UIRegister = 'Register'
	String UIRequestOTP = 'Request OTP'
	String UIRequest = 'Request'
	String UIDeviceName = 'Device Name'
	String UINext = 'Next'
	String UIContinue = 'Continue'
	String UIHome = 'Home'
	String UIYES = 'Yes'
	CommonPage commonPage = getPage(CommonPage)

	static final String ACTIVATE_NOW = 'Activate Now'
	static final String YOUR_SECURE_TAC_NUMBER = 'Your Secure TAC Number'

	/**
	 * Wait for Secure TAC Number present
	 * @return true if Secure TAC Number present, false if not present
	 */
	public boolean waitForSecureTacNumberPresent(String lblName = UIYourSecureTACNumber, int timeOut = GlobalVariable.G_timeoutShort, FailureHandling flowControl = defaultFailureHandling) {
		'Wait for Secure TAC Number present'
		if(MobileDriver.waitForElementByXpathTestObject(this.lblSecureTacNumber(lblName))) {
			return true
		}else {
			'Wait for Secure TAC Number present'
			return MobileDriver.waitForElementPresent(this.lblSecureTacNumber(lblName), timeOut, FailureHandling.OPTIONAL)
		}
	}

	/**
	 * Verify Secure TAC Number exist
	 * @return true if Secure TAC Number exist, false if not exist
	 */
	public boolean verifySecureTacNumberExist(String lblName = UIYourSecureTACNumber, int timeOut = GlobalVariable.G_timeoutShort, FailureHandling flowControl = defaultFailureHandling) {
		'Verify Secure TAC Number exist'
		return MobileDriver.verifyElementVisible(this.lblSecureTacNumber(lblName), timeOut, FailureHandling.OPTIONAL)
	}

	/**
	 * Tap Back button
	 */
	public void tapBtnBack(int timeOut = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		'Select Back button'
		MobileDriver.tapEleByTestObject(this.btnBack())
	}

	/**
	 * Verify Register Secure2u button exist
	 * @return true if Register Secure2u button exist, false if not exist
	 */
	public boolean verifyBtnRegisterSecure2uExist( String lblName = UIRegisterSecure2uNow, int timeOut = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		'Verify Register Secure2u button exist'
		return MobileDriver.verifyElementExist(this.btnRegisterSecure2u(lblName), timeOut, flowControl)
	}

	/**
	 * Tap Register Secure2u button
	 */
	public void tapBtnRegisterSecure2u( String lblName = UIRegisterSecure2uNow, int timeOut = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		MobileDriver.tap(this.btnRegisterSecure2u(lblName), timeOut, flowControl)
	}

	/**
	 * Tap Register Secure2u button or Active Now button
	 */
	public void tapRegisterSecure2uOrActiveNow(FailureHandling flowControl = defaultFailureHandling) {
		'If Register Secure2u button exist, tap Register Secure2u button, else tap Active Now button'
		if(MobileDriver.waitForElementPresent(this.btnRegisterSecure2u(UIRegisterSecure2uNow), GlobalVariable.G_timeoutShort, FailureHandling.OPTIONAL)) {
			MobileDriver.tap(this.btnRegisterSecure2u(UIRegisterSecure2uNow), GlobalVariable.G_timeoutMedium, flowControl)
		}else if(MobileDriver.waitForElementPresent(commonPage.lblContainDynamic(UIRegister), GlobalVariable.G_timeoutShort, FailureHandling.OPTIONAL)) {
			MobileDriver.tap(commonPage.lblContainDynamic(UIRegister), GlobalVariable.G_timeoutMedium, flowControl)
		}
		else {
			MobileDriver.tap(commonPage.lblDynamic(ACTIVATE_NOW), GlobalVariable.G_timeoutMedium, flowControl)
		}
	}

	/**
	 * Tap Yes button
	 */
	public void registerForS2u(FailureHandling flowControl = defaultFailureHandling) {
		'If Yes button exist, tap Yes button'
		if(MobileDriver.waitForElementPresent(commonPage.lblDynamic(UIYES), GlobalVariable.G_timeoutShort, FailureHandling.OPTIONAL)) {
			MobileDriver.tap(commonPage.lblDynamic(UIYES), GlobalVariable.G_timeoutMedium, flowControl)
		}
	}

	/**
	 * Tap Request OTP button
	 */
	public void tapBtnRequestOtp( String lblName = UIRequestOTP, int timeOut = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		'Select Request OTP button'
		if(Mobile.waitForElementPresent(this.btnRequestOtp(lblName), GlobalVariable.G_timeoutMiniShort)) {
			MobileDriver.tap(this.btnRequestOtp(lblName), timeOut, flowControl)
		}else {
			MobileDriver.tap(commonPage.lblContainDynamic(UIRequest), timeOut, flowControl)
		}
		MobileDriver.delay(GlobalVariable.G_timeoutMiniShort)
		MobileDriver.takeScreenshot()
	}

	/**
	 * Verify Device Name exist
	 * @return true if Device Name exist, false if not exist
	 */
	public boolean verifyLblDeviceNameExist(String lblName = UIDeviceName, int timeOut = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		return MobileDriver.verifyElementExist(this.lblDeviceName(lblName), timeOut, flowControl)
	}

	/**
	 * Tap Next button
	 */
	public void tapBtnNext( String lblName = UINext, int timeOut = 0, FailureHandling flowControl = defaultFailureHandling) {
		MobileDriver.tap(this.btnNext(lblName), timeOut, flowControl)
	}

	/**
	 * Tap Next button or Continue button
	 */
	public void tapBtnNextOrContinue(FailureHandling flowControl = defaultFailureHandling) {
		'If Next button exist, tap Next button, else tap Continue button'
		if(MobileDriver.waitForElementPresent(this.btnRegisterSecure2u(UINext), GlobalVariable.G_timeoutShort, FailureHandling.OPTIONAL)) {
			MobileDriver.tap(this.btnRegisterSecure2u(UINext), GlobalVariable.G_timeoutMedium, flowControl)
		}else {
			MobileDriver.tap(commonPage.lblDynamic(UIContinue), GlobalVariable.G_timeoutMedium, flowControl)
		}
	}

	/**
	 * Verify Register Status exist
	 * @return true if Register Status exist, false if not exist
	 */
	public boolean verifyLblRegisterStatusExist(String lblName, int timeOut = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = FailureHandling.OPTIONAL) {
		return MobileDriver.verifyElementExist(this.lblRegisterStatus(lblName), timeOut, flowControl)
	}

	/**
	 * Tap Dynamic button by label name
	 */
	public void tapBtnDynamic( String lblName, int timeOut = 0, FailureHandling flowControl = defaultFailureHandling) {
		MobileDriver.tap(this.btnDynamic(lblName), timeOut, flowControl)
	}

	/**
	 * Enter Your ID Number
	 */
	public void enterTxtYourIdNumber( String idNumber, int timeOut = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		MobileDriver.setText(this.txtYourIdNumber(), idNumber, timeOut, flowControl)
	}

	/**
	 * Tap Continue button
	 */
	public void tapBtnContinue( String lblName = UIContinue, int timeOut = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		MobileDriver.tap(this.btnContinue(lblName), timeOut, flowControl)
	}

	/**
	 * Tap back to Home
	 */
	public void backToHome(FailureHandling flowControl = defaultFailureHandling) {
		MobileDriver.tapEleByTestObject(commonPage.imgNoDynamic(1))
		//if tap xpath not working

		'If Home label exist, return, else tap back to Home'
		if(MobileDriver.waitForElementPresent(commonPage.lblDynamic(UIHome), GlobalVariable.G_timeoutShort, FailureHandling.OPTIONAL)){
			return
		}else {
			MobileDriver.tap(commonPage.imgNoDynamic(1), GlobalVariable.G_timeoutLong, flowControl)
		}
	}

	String getTACNumber(FailureHandling flowControl = defaultFailureHandling) {
		return MobileDriver.getText(commonPage.lblSiblingDynamic(YOUR_SECURE_TAC_NUMBER), GlobalVariable.G_timeoutMedium, flowControl)
	}
}
