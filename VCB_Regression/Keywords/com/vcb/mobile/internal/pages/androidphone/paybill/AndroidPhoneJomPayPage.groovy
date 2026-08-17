package com.vcb.mobile.internal.pages.androidphone.paybill

import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.testobject.TestObject
import com.vcb.mobile.internal.pages.base.paybill.JomPayPage

public class AndroidPhoneJomPayPage extends JomPayPage{
	@Override
	public TestObject imgV() {
		return findTestObject('Object Repository/Mobile/VCB/Android/Transfer/Confirmation/imgV');
	}

	@Override
	public TestObject lblMessage() {
		return findTestObject('Object Repository/Mobile/VCB/Android/Transfer/Transaction/lblMessage');
	}

	@Override
	public TestObject lblDynamicText(String text) {
		return findTestObject('Object Repository/Mobile/VCB/Dynamic/lblDynamic', [('text') : text]);
	}

	@Override
	public TestObject txtReferenceIdValue() {
		return findTestObject('Object Repository/Mobile/VCB/Android/PayBill Screen/txtReferenceIdValue');
	}
}
