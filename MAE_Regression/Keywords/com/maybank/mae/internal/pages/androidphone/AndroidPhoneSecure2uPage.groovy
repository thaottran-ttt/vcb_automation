package com.maybank.mae.internal.pages.androidphone

import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.testobject.TestObject
import com.maybank.mae.internal.pages.base.Secure2uPage

public class AndroidPhoneSecure2uPage extends Secure2uPage{
	@Override
	public TestObject lblSecureTacNumber(String lblName) {
		return findTestObject('Object Repository/Mobile/MAE/Dynamic/lblDynamic', [('text') : lblName]);
	}

	@Override
	public TestObject btnBack() {
		return findTestObject('Object Repository/Mobile/MAE/Android/Secure2u Screen/btnBack');
	}

	@Override
	public TestObject btnRegisterSecure2u(String lblName) {
		return findTestObject('Object Repository/Mobile/MAE/Dynamic/lblDynamic', [('text') : lblName]);
	}

	@Override
	public TestObject btnRequestOtp(String lblName) {
		return findTestObject('Object Repository/Mobile/MAE/Dynamic/lblDynamic', [('text') : lblName]);
	}

	@Override
	public TestObject lblDeviceName(String lblName) {
		return findTestObject('Object Repository/Mobile/MAE/Dynamic/lblDynamic', [('text') : lblName]);
	}

	@Override
	public TestObject btnNext(String lblName) {
		return findTestObject('Object Repository/Mobile/MAE/Dynamic/lblDynamic', [('text') : lblName]);
	}

	@Override
	public TestObject btnDynamic(String lblName) {
		return findTestObject('Object Repository/Mobile/MAE/Dynamic/lblAnyText', [('Verify') : lblName]);
	}

	@Override
	public TestObject lblRegisterStatus(String lblName) {
		return findTestObject('Object Repository/Mobile/MAE/Dynamic/lblDynamic', [('text') : lblName]);
	}

	@Override
	public TestObject txtYourIdNumber() {
		return findTestObject('Object Repository/Mobile/MAE/Android/Secure2u Screen/txtYourIdNumber');
	}

	@Override
	public TestObject btnContinue(String lblName) {
		return findTestObject('Object Repository/Mobile/MAE/Dynamic/lblAnyText', [('Verify') : lblName]);
	}
}
