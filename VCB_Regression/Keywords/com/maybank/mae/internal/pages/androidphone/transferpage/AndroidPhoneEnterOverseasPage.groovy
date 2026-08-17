package com.maybank.mae.internal.pages.androidphone.transferpage

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
import com.maybank.mae.internal.pages.base.transferpage.EnterOverseasPage
import internal.GlobalVariable

public class AndroidPhoneEnterOverseasPage extends EnterOverseasPage{
	@Override
	public TestObject drpIDType() {
		return findTestObject('Object Repository/Mobile/MAE/Android/EnterDuitnowProxy/drpIdType');
	}

	@Override
	public TestObject recipientDetailEditBtn() {
		return findTestObject('Object Repository/Mobile/MAE/Android/Transfer/Overseas/recipientDetailEditBtn');
	}

	@Override
	public TestObject lblParentViewGrSiblingDynamic(String text, int index=2) {
		return findTestObject('Object Repository/Mobile/MAE/Android/Transfer/Overseas/lblParentViewGrSiblingDynamic', [('text') : text, 'index': index.toString()]);
	}
	
	@Override
	public TestObject imgLabelDynamic(String text) {
		return findTestObject('Object Repository/Mobile/MAE/Dynamic/Android/imgLabelDynamic', [('text') : text]);
	}

	@Override
	public TestObject lblSiblingGroupDynamic(String text, int index=1) {
		return findTestObject('Object Repository/Mobile/MAE/Android/Transfer/Overseas/lblSiblingGroupDynamic', [('text') : text, 'index': index.toString()]);
	}
}
