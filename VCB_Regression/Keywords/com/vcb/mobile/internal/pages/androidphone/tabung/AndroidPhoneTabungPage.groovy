package com.vcb.mobile.internal.pages.androidphone.tabung

import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.testobject.TestObject
import com.vcb.mobile.internal.pages.base.tabung.TabungPage



public class AndroidPhoneTabungPage extends TabungPage {
	
	@Override
	public TestObject lblDropDownDefaultValue() {
		return findTestObject('Object Repository/Mobile/VCB/Android/Tabung Screen/lblDropDownDefaultValue');
	}


	@Override
	public TestObject btnTick() {	
		return findTestObject('Object Repository/Mobile/VCB/Android/Tabung Screen/btnTick');
	}

	@Override
	public TestObject btnToggel(String text) {
		// TODO Auto-generated method stub
		return findTestObject('Object Repository/Mobile/VCB/Android/Tabung Screen/btnToggel', ['text': text]);
	}
}
