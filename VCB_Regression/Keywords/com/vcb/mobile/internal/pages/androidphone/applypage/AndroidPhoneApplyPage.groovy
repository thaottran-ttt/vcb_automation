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
import com.vcb.mobile.internal.pages.base.applypage.ApplyPage as ApplyPage

import internal.GlobalVariable

public class AndroidPhoneApplyPage extends ApplyPage{

	@Override
	public TestObject lblCurrentStep(String stepNo) {
		return findTestObject('Object Repository/Mobile/VCB/Android/Apply Screen/lblCurrentStep',[('text') : stepNo])
	}

	@Override
	public TestObject rdProductType(String productType) {
		return findTestObject('Object Repository/Mobile/VCB/Android/Apply Screen/rdFDAccountType',[('text') : productType])
	}

	@Override
	public TestObject rdSurveyAnswer(String label, String value) {
		return findTestObject('Object Repository/Mobile/VCB/Android/Apply Screen/rdSurveyAnswer',[('label') : label, ('value') : value])
	}

	@Override
	public TestObject btnTick() {
		return findTestObject('Object Repository/Mobile/VCB/Android/Common/Dropdown/lblDropdownValue')
	}

	@Override
	public TestObject txtAmount() {
		return findTestObject('Object Repository/Mobile/VCB/Android/Common/Dropdown/lblDropdownValue')
	}

	@Override
	public TestObject btnConfirm() {
		return findTestObject('Object Repository/Mobile/VCB/Android/Common/Dropdown/lblDropdownValue')
	}

	@Override
	public TestObject lblBalanceAmount() {
		return findTestObject('Object Repository/Mobile/VCB/Android/Common/Dropdown/lblDropdownValue')
	}

	@Override
	public TestObject rdTermConditions(String text) {
		return findTestObject('Object Repository/Mobile/VCB/Android/Apply Screen/rdTermConditions',[('text') : text])
	}

	@Override
	public TestObject lblConfirmInfo(String confirmInfo, String value) {
		return findTestObject('Object Repository/Mobile/VCB/Android/Apply Screen/lblConfirmInfo',[('label') : confirmInfo, ('value') : value])
	}

	//	@Override
	//	public TestObject txtSiblingDynamic(String fieldName) {
	//		return findTestObject('Object Repository/Mobile/VCB/Dynamic/Android/txtSiblingDynamic',[('lable/') : fieldName])
	//	}
}
