package com.vcb.mobile.internal.pages.androidphone.pfmpage

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
import com.vcb.mobile.internal.pages.base.pfmpage.PFMPage

import internal.GlobalVariable

public class AndroidPhonePFMPage extends PFMPage{
	@Override
	public TestObject txtSiblingGroup(String label) {
		return findTestObject('Object Repository/Mobile/VCB/Android/PFM/txtSiblingGroup',[('label') : label])
	}
	
	@Override
	public TestObject imgSiblingGroup(String fieldName) {
		return findTestObject('Object Repository/Mobile/VCB/Android/PFM/imgSiblingGroup',[('label') : fieldName])
	}
	
	@Override
	public TestObject frmExpensesList() {
		return findTestObject('Object Repository/Mobile/VCB/Android/PFM/expensesList')
	}
	
	@Override
	public TestObject txtExpenseCate(String text, int index) {
		return findTestObject('Object Repository/Mobile/VCB/Android/PFM/txtExpenseCate', ['text': text, 'index': index])
	}

	@Override
	public TestObject lblPFMPaymentDropDown() {
		return findTestObject('Object Repository/Mobile/VCB/Android/PFM/PFMPaymentDropDown')
	}

}
