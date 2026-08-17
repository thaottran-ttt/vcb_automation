package com.vcb.mobile.internal.pages.androidphone.accountpage

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
import com.vcb.mobile.internal.pages.base.accountpage.WealthPage

import internal.GlobalVariable

public class AndroidPhoneWealthPage extends WealthPage {

	@Override
	public TestObject frmDashboardListView() {
		return findTestObject('Object Repository/Mobile/VCB/Android/Wealth Screen/frmDashboardListView');
	}

	@Override
	public TestObject lblGroupSiblingDynamic(String text, int index = 1) {
		return findTestObject('Object Repository/Mobile/VCB/Android/Wealth Screen/lblGroupSiblingDynamic', ['text': text, 'index': index]);
	}

	@Override
	public TestObject lblDynamicContainTwoText(String text, String text2) {
		return findTestObject('Object Repository/Mobile/VCB/Android/Wealth Screen/lblDynamicContainTwoText', ['text': text, 'text2': text2]);
	}

	@Override
	public TestObject imgSibling(String text) {
		return findTestObject('Object Repository/Mobile/VCB/Android/Wealth Screen/imgSibling', ['text': text]);
	}

	@Override
	public TestObject btnDynamic(String text) {
		return findTestObject('Object Repository/Mobile/VCB/Android/Wealth Screen/btnDynamic', ['text': text]);
	}

	@Override
	public TestObject lblPrecedingDynamic(String text, String text2) {
		return findTestObject('Object Repository/Mobile/VCB/Android/Wealth Screen/lblPrecedingDynamic', ['text': text, 'text2': text2]);
	}
	
	@Override
	public TestObject lblListDataItems() {
		return findTestObject('Object Repository/Mobile/VCB/Android/Wealth Screen/lblListDataItems');
	}
	
	@Override
	public TestObject lblTooltipTitle() {
		return findTestObject('Object Repository/Mobile/VCB/Android/Wealth Screen/lblTooltipTitle');
	}
	
	@Override
	public TestObject lblContainSibling(String text) {
		return findTestObject('Object Repository/Mobile/VCB/Android/Wealth Screen/lblContainSibling', ['text': text]);
	}
	
	@Override
	public TestObject dropdownDynamic(String text) {
		return findTestObject('Object Repository/Mobile/VCB/Android/Wealth Screen/dropdownDynamic', ['text': text]);
	}
	
	@Override
	public TestObject lblSiblingDynamic(String text, int index = 1) {
		return findTestObject('Object Repository/Mobile/VCB/Android/Wealth Screen/lblSiblingDynamic', ['text': text, 'index': index]);
	}
	
	@Override
	public TestObject lblSiblingGold(String text) {
		return findTestObject('Object Repository/Mobile/VCB/Android/Wealth Screen/lblSiblingGold', ['text': text]);
	}
	
}
