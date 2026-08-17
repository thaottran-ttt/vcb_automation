package com.vcb.mobile.internal.pages.androidphone.accountpage

import com.kms.katalon.core.testobject.TestObject
import com.vcb.mobile.internal.pages.base.accountpage.UpliftFDPage
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject


public class AndroidPhoneUpliftFDPage extends UpliftFDPage{
	@Override
	public TestObject btnDropDown(int index, String label) {
		return findTestObject('Object Repository/Mobile/VCB/Android/UpliftFD/drpUplift', ['label': label, 'index': index]);
	}
	@Override
	public TestObject lblNormalizeSpace(String text) {
		return findTestObject('Object Repository/Mobile/VCB/Dynamic/lblNormalizeSpace', ['text': text]);	
	}
	@Override
	public TestObject imgReceiptIcon() {	
		return findTestObject("Object Repository/Mobile/VCB/Android/UpliftFD/imgReceiptIcon");
	}
	@Override
	public TestObject frmTransactionHistory() {		
		return findTestObject("Object Repository/Mobile/VCB/Android/UpliftFD/frmTransactionHistory");;
	}
	
}