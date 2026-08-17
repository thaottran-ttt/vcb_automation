package com.vcb.mobile.internal.pages.androidphone.atmcashoutpage

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
import com.vcb.mobile.internal.pages.base.atmcashoutpage.AtmCashoutPage
import internal.GlobalVariable

public class AndroidPhoneAtmCashoutPage extends AtmCashoutPage{
	@Override
	public TestObject lblATMCashout(String lblName) {
		return findTestObject('Object Repository/Mobile/VCB/Dynamic/lblDynamic', [('text') : lblName]);
	}

	@Override
	public TestObject lblYourSmartphone() {
		return findTestObject('Object Repository/Mobile/VCB/Android/ATM Cashout Screen/lblYourSmartphone');
	}

	@Override
	public TestObject lblNote() {
		return findTestObject('Object Repository/Mobile/VCB/Android/ATM Cashout Screen/lblNote');
	}

	@Override
	public TestObject rdbtnConfirm() {
		return findTestObject('Object Repository/Mobile/VCB/Android/ATM Cashout Screen/rdbtnConfirm');
	}

	@Override
	public TestObject btnConfirm(String lblName) {
		return findTestObject('Object Repository/Mobile/VCB/Dynamic/lblAnyText', [('Verify') : lblName]);
	}

	@Override
	public TestObject lblATMCashoutSuccessful(String lblName) {
		return findTestObject('Object Repository/Mobile/VCB/Dynamic/lblDynamic', [('text') : lblName]);
	}

	@Override
	public TestObject lblATMCashoutSuccessfulNote1(String lblName) {
		return findTestObject('Object Repository/Mobile/VCB/Dynamic/lblDynamic', [('text') : lblName]);
	}

	@Override
	public TestObject lblATMCashoutSuccessfulNote2(String lblName) {
		return findTestObject('Object Repository/Mobile/VCB/Dynamic/lblDynamic', [('text') : lblName]);
	}

	@Override
	public TestObject lblYourATMCashout(String lblName) {
		return findTestObject('Object Repository/Mobile/VCB/Dynamic/lblDynamic', [('text') : lblName]);
	}

	@Override
	public TestObject lblATMCashoutActivated(String lblName) {
		return findTestObject('Object Repository/Mobile/VCB/Dynamic/lblDynamic', [('text') : lblName]);
	}

	@Override
	public TestObject lblTip(String lblName) {
		return findTestObject('Object Repository/Mobile/VCB/Dynamic/lblDynamic', [('text') : lblName]);
	}

	@Override
	public TestObject lblSecurityPurposes(String lblName) {
		return findTestObject('Object Repository/Mobile/VCB/Dynamic/lblDynamic', [('text') : lblName]);
	}

	@Override
	public TestObject lblAuthorisation(String lblName) {
		return findTestObject('Object Repository/Mobile/VCB/Dynamic/lblDynamic', [('text') : lblName]);
	}

	@Override
	public TestObject lblReferenceID(String lblName) {
		return findTestObject('Object Repository/Mobile/VCB/Dynamic/lblDynamic', [('text') : lblName]);
	}

	@Override
	public TestObject lblDateTime(String lblName) {
		return findTestObject('Object Repository/Mobile/VCB/Dynamic/lblDynamic', [('text') : lblName]);
	}

	@Override
	public TestObject lblTransactionType(String lblName) {
		return findTestObject('Object Repository/Mobile/VCB/Dynamic/lblDynamic', [('text') : lblName]);
	}

	@Override
	public TestObject lblYourSecureVerification(String lblName) {
		return findTestObject('Object Repository/Mobile/VCB/Dynamic/lblDynamic', [('text') : lblName]);
	}

	@Override
	public TestObject btnClose() {
		return findTestObject('Object Repository/Mobile/VCB/Android/ATM Cashout Screen/btnClose');
	}
	@Override
	public TestObject btnTick() {
		return findTestObject('Object Repository/Mobile/VCB/Android/ATM Cashout Screen/btnTick');
	}
	@Override
	public TestObject btnRemove() {
		return findTestObject('Object Repository/Mobile/VCB/Android/ATM Cashout Screen/btnRemove');
	}
}
