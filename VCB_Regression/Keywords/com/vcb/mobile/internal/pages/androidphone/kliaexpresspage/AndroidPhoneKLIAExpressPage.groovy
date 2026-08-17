package com.vcb.mobile.internal.pages.androidphone.kliaexpresspage

import com.kms.katalon.core.testobject.TestObject
import com.vcb.mobile.internal.pages.base.kliaexpresspage.KLIAExpressPage
import com.vcb.mobile.internal.pages.base.accountpage.UpliftFDPage
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject


public class AndroidPhoneKLIAExpressPage extends KLIAExpressPage{
	@Override
	public TestObject imgDrogDown(String fieldName) {
		return findTestObject('Object Repository/Mobile/VCB/Android/KLIA Express/imgDropDown', [('label') : fieldName]);
	}

	@Override
	public TestObject txtTravelDate(String label) {
		return findTestObject('Object Repository/Mobile/VCB/Android/KLIA Express/txtTravelDate', [('text') : label]);
	}
}