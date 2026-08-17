package com.vcb.mobile.internal.pages.androidphone.splitbill

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
import com.vcb.mobile.internal.pages.base.splitbill.SplitBillPage

import internal.GlobalVariable

public class AndroidPhoneSplitBillPage extends SplitBillPage {

	@Override
	public TestObject imgSearch() {
		return findTestObject('Object Repository/Mobile/VCB/Android/PayBill Screen/imgSearch');
	}

	@Override
	public TestObject imgV() {
		// TODO Auto-generated method stub
		return findTestObject('Object Repository/Mobile/VCB/Android/PayBill Screen/imgV');
	}
	@Override
	public TestObject plusIcon() {
		// TODO Auto-generated method stub
		return findTestObject('Object Repository/Mobile/VCB/Android/SplitBill Screen/plusIcon');
	}
	
	@Override 
	public TestObject syncContactBtn() {
		return findTestObject('Object Repository/Mobile/VCB/Android/SplitBill Screen/syncContactBtn');
	}
	
}
