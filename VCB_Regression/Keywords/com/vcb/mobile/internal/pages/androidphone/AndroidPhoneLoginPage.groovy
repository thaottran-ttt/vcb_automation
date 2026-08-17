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
import com.vcb.mobile.internal.pages.base.LoginPage

import internal.GlobalVariable

public class AndroidPhoneLoginPage extends LoginPage{

	@Override
	TestObject btnLogInNow() {
		return findTestObject('Object Repository/Mobile/VCB/Android/Login Screen/btnLogInNow')
	}

	@Override
	TestObject btnLogInHere() {
		return findTestObject('Object Repository/Mobile/VCB/Android/Login Screen/btnLogInHere')
	}

	@Override
	TestObject txtUsername() {
		findTestObject('Object Repository/Mobile/VCB/Android/Login Screen/txtUsername')
	}

	@Override
	TestObject btnContinue() {
		return findTestObject('Object Repository/Mobile/VCB/Android/Login Screen/btnContinue')
	}
	@Override
	TestObject btnYesItsMine() {
		return findTestObject('Object Repository/Mobile/VCB/Android/Login Screen/btnYesItsMine')
	}

	@Override
	TestObject txtPasswordRegister() {
		return findTestObject('Object Repository/Mobile/VCB/Android/Login Screen/txtPasswordRegister')
	}

	@Override
	TestObject btnProceed() {
		return findTestObject('Object Repository/Mobile/VCB/Android/Login Screen/btnProceed')
	}

	@Override
	TestObject txtPassword(){
		return findTestObject('Object Repository/Mobile/VCB/Android/Login Screen/txtPassword')
	}

	@Override
	TestObject txtArrLoginDetailedText(){
		return findTestObject('Object Repository/Mobile/VCB/Android/Login Screen/txtArrLoginDetailedText')
	}

	@Override
	TestObject btnARRClose(){
		return findTestObject('Object Repository/Mobile/VCB/Android/Login Screen/btnARRClose')
	}
	
	@Override
	TestObject btnARRGotIt(){
		return findTestObject('Object Repository/Mobile/VCB/Android/Login Screen/btnARRGotIt')
	}
}
