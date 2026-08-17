package com.maybank.mae.internal.pages.androidphone

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
import com.maybank.mae.internal.pages.base.WelcomeOnboardPage
import internal.GlobalVariable

public class AndroidPhoneWelcomeOnboardPage extends WelcomeOnboardPage{
	@Override
	TestObject txtYourName() {
		return findTestObject('Object Repository/Mobile/MAE/Android/Welcome Onboard Screen/txtYourName')
	}

	@Override
	TestObject btnContinue() {
		return findTestObject('Object Repository/Mobile/MAE/Android/Welcome Onboard Screen/btnContinue')
	}

	@Override
	public TestObject btnWallet() {
		return findTestObject('Object Repository/Mobile/MAE/Android/Welcome Onboard Screen/btnWallet');
	}

	@Override
	public TestObject btnNext() {
		return findTestObject('Object Repository/Mobile/MAE/Android/Welcome Onboard Screen/btnNext');
	}

	@Override
	public TestObject btnClose() {
		return findTestObject('Object Repository/Mobile/MAE/Android/Welcome Onboard Screen/btnClose');
	}
}
