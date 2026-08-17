package com.maybank.mae.internal.pages.androidphone.paybill

import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.testobject.TestObject
import com.maybank.mae.internal.pages.base.paybill.JomPayPage

public class AndroidPhoneJomPayPage extends JomPayPage{
	@Override
	public TestObject imgV() {
		return findTestObject('Object Repository/Mobile/MAE/Android/Transfer/Confirmation/imgV');
	}

	@Override
	public TestObject lblMessage() {
		return findTestObject('Object Repository/Mobile/MAE/Android/Transfer/Transaction/lblMessage');
	}

	@Override
	public TestObject lblDynamicText(String text) {
		return findTestObject('Object Repository/Mobile/MAE/Dynamic/lblDynamic', [('text') : text]);
	}

	@Override
	public TestObject txtReferenceIdValue() {
		return findTestObject('Object Repository/Mobile/MAE/Android/PayBill Screen/txtReferenceIdValue');
	}
}
