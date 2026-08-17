package com.vcb.mobile.internal.pages.androidphone.scanpaypage

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
import com.vcb.mobile.internal.pages.base.scanpaypage.ScanPayPage


import internal.GlobalVariable

public class AndroidPhoneScanPayPage extends ScanPayPage{

	@Override
	public TestObject sliderDailyLimit() {
		return findTestObject('Object Repository/Mobile/VCB/Android/ScanPage/LimitAdjestment/sliderDailyLimit');
	}

	@Override
	public TestObject sliderCumulativeLimit() {
		return findTestObject('Object Repository/Mobile/VCB/Android/ScanPage/LimitAdjestment/sliderCumulativeLimit');
	}

	@Override
	public TestObject sliderDailyLimitPoint() {
		return findTestObject('Object Repository/Mobile/VCB/Android/ScanPage/LimitAdjestment/sliderDailyLimitPoint');
	}

	@Override
	public TestObject sliderCumulativeLimitPoint() {
		return findTestObject('Object Repository/Mobile/VCB/Android/ScanPage/LimitAdjestment/sliderCumulativeLimitPoint');
	}

	@Override
	public TestObject btnFlashLight() {
		return findTestObject('Object Repository/Mobile/VCB/Android/ScanPage/btnFlashLight');
	}
	
	@Override
	public TestObject lblImagePicker(def index) {
		return findTestObject('Object Repository/Mobile/VCB/Android/ScanPage/lblImagePicker', ['index': index]);
	}
}
