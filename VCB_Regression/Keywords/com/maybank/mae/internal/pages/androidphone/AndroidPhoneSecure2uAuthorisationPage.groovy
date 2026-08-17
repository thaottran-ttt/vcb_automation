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
import com.maybank.mae.internal.pages.base.Secure2uAuthorisationPage

import internal.GlobalVariable

public class AndroidPhoneSecure2uAuthorisationPage extends Secure2uAuthorisationPage {
	@Override
	public TestObject lblYourTAC() {
		return findTestObject('Object Repository/Mobile/MAE/Android/Transfer/TAC/lblYourTAC');
	}

	@Override
	public TestObject lblYourOTP() {
		return findTestObject('Object Repository/Mobile/MAE/Android/Common/OTP/lblYourOTP');
	}

	@Override
	public TestObject lblTACForM2U() {
		return findTestObject('Object Repository/Mobile/MAE/Android/Secure2u Screen/lblTACForM2U')
	}
	
	@Override
	public TestObject btnApprove() {
		return findTestObject('Object Repository/Mobile/MAE/Android/Secure2uAuthorisation Screen/btnApprove')
	}
	
	@Override
	public TestObject btnReject() {
		return findTestObject('Object Repository/Mobile/MAE/Android/Secure2uAuthorisation Screen/btnReject')
	}
}
