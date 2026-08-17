package com.vcb.mobile.internal.pages.androidphone

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
import com.vcb.mobile.internal.pages.base.OnboardingPage
import internal.GlobalVariable

public class AndroidPhoneOnboardingPage extends OnboardingPage{
	@Override
	TestObject btnNext() {
		return findTestObject('Object Repository/Mobile/VCB/Android/Onboard Screen/btnNext')
	}

	@Override
	TestObject btnGotIt() {
		return findTestObject('Object Repository/Mobile/VCB/Android/Onboard Screen/btnGotIt')
	}

	@Override
	TestObject btnAgree() {
		return findTestObject('Object Repository/Mobile/VCB/Android/Onboard Screen/btnAgree')
	}

	@Override
	TestObject lblOurAntiMalware() {
		return findTestObject('Object Repository/Mobile/VCB/Android/Onboard Screen/lblOurAntiMalware')
	}

	@Override
	TestObject btnProceedAnyway() {
		return findTestObject('Object Repository/Mobile/VCB/Android/Onboard Screen/btnProceedAnyway')
	}

	@Override
	TestObject btnAgreeAndProceed() {
		return findTestObject('Object Repository/Mobile/VCB/Android/Onboard Screen/btnAgreeAndProceed')
	}

	@Override
	TestObject btnClose() {
		return findTestObject('Object Repository/Mobile/VCB/Android/Onboard Screen/btnClose')
	}
	@Override
	TestObject btnDisagree() {
		return findTestObject('Object Repository/Mobile/VCB/Android/Onboard Screen/btnDisagree')
	}
}
