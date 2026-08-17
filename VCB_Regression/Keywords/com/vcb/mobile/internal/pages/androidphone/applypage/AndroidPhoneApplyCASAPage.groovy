package com.vcb.mobile.internal.pages.androidphone.applypage

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
import com.vcb.mobile.internal.pages.base.applypage.ApplyAccountPage
import com.vcb.mobile.internal.pages.base.applypage.ApplyCASAPage
import internal.GlobalVariable
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.util.KeywordUtil

public class AndroidPhoneApplyCASAPage extends ApplyCASAPage {
	@Override
	TestObject imgDropDownSiblingGroup(String label, int index = 1) {
		return findTestObject('Object Repository/Mobile/VCB/Android/Apply STP/imgDropDownSiblingGroup', ['label': label, 'index': index]);
	}
	@Override
	TestObject followingSiblingOption(String key, String value) {
		return findTestObject('Object Repository/Mobile/VCB/Android/Apply STP/followingSiblingOption', ['key': key, 'value': value]);
	}
	@Override
	TestObject lblFollowingSibling(String key) {
		return findTestObject('Object Repository/Mobile/VCB/Android/Apply STP/lblFollowingSibling', ['key': key]);
	}
	@Override
	TestObject btnSiblingGroup(String label, String text) {
		return findTestObject('Object Repository/Mobile/VCB/Android/Apply STP/btnSiblingGroup', ['label': label, 'text': text]);
	}
	@Override
	TestObject txtFieldWithIndex(String index) {
		return findTestObject('Object Repository/Mobile/VCB/Android/Apply STP/txtFieldWithIndex', ['index': index]);
	}
	@Override
	TestObject lblNormalizeFollowingSibling(String text) {
		return findTestObject('Object Repository/Mobile/VCB/Android/Apply STP/lblNormalizeFollowingSibling', ['text': text]);
	}
	@Override
	public TestObject btnConfirm() {
		return findTestObject('Object Repository/Mobile/VCB/Android/Apply STP/btnConfirm')
	}

	public TestObject btnEkycPassport() {
		return findTestObject('Object Repository/Mobile/VCB/Android/Apply STP/btnEkycPassport')
	}
}
