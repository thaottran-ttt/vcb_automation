package com.vcb.mobile.internal.pages.androidphone.moviesandleisure

import com.kms.katalon.core.testobject.TestObject
import com.vcb.mobile.internal.pages.base.moviesandleisure.MoviesAndLeisurePage
import com.vcb.mobile.internal.pages.base.accountpage.UpliftFDPage
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject


public class AndroidPhoneMoviesAndLeisurePage extends MoviesAndLeisurePage {
	@Override
	public TestObject lstItemByIndex(int index = 1) {
		return findTestObject('Object Repository/Mobile/VCB/Android/Movies And Leisure/lstItemByIndex', [('index') : index]);
	}

	@Override
	public TestObject lblSeatIndex(String text, int index) {
		return findTestObject('Object Repository/Mobile/VCB/Android/Movies And Leisure/lblSeatIndex', ['text': text, 'index': index]);
	}

	@Override
	public TestObject imgIndulge() {
		return findTestObject('Object Repository/Mobile/VCB/Android/Movies And Leisure/imgIndulge');
	}
}