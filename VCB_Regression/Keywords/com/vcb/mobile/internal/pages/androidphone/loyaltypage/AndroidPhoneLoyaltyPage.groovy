package com.vcb.mobile.internal.pages.androidphone.loyaltypage

import com.kms.katalon.core.testobject.TestObject
import com.vcb.mobile.internal.pages.base.loyaltypage.LoyaltyPage
import com.vcb.mobile.internal.pages.base.accountpage.UpliftFDPage
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject


public class AndroidPhoneLoyaltyPage extends LoyaltyPage{
	@Override
	public TestObject imgCameraIcon() {
		return findTestObject('Object Repository/Mobile/VCB/Android/Loyalty Card/imgCameraIcon');
	}
	@Override
	public TestObject imgBarCode() {
		return findTestObject("Object Repository/Mobile/VCB/Android/Loyalty Card/imgBarCode");
	}
	@Override
	public TestObject lblColorPicker(String fieldName) {
		return findTestObject('Object Repository/Mobile/VCB/Android/Loyalty Card/lblColorPicker', [('label') : fieldName]);
	}
	@Override
	public TestObject txtDescription(String text) {
		return findTestObject('Object Repository/Mobile/VCB/Android/Loyalty Card/txtDescription', [('text') : text]);
	}
}