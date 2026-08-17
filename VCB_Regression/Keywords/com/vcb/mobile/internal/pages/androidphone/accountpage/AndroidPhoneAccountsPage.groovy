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
import com.vcb.mobile.internal.pages.base.accountpage.AccountsPage

import internal.GlobalVariable

public class AndroidPhoneAccountsPage extends AccountsPage{

	@Override
	public TestObject fraScrollTab() {
		return findTestObject('Object Repository/Mobile/VCB/Android/Accounts Screen/fraScrollTab');
	}

	@Override
	public TestObject btnTab(String tabName) {
		return findTestObject('Object Repository/Mobile/VCB/Dynamic/lblDynamic', ['text': tabName]);
	}

	@Override
	public TestObject btnGotIt() {
		return findTestObject('Object Repository/Mobile/VCB/Dynamic/lblDynamic', ['text': 'Got It']);
	}

	@Override
	public TestObject btnPayBills() {
		return findTestObject('Object Repository/Mobile/VCB/Dynamic/lblDynamic', ['text': 'Pay Bills']);
	}

	@Override
	public TestObject fraAccountList() {
		return findTestObject('Object Repository/Mobile/VCB/Android/Accounts Screen/fraAccountList');
	}

	@Override
	public TestObject btnSearch() {
		return findTestObject('Object Repository/Mobile/VCB/Android/Accounts Screen/btnSearch');
	}
	@Override
	public TestObject btnStick(boolean isG1A=false) {
		if(isG1A) {
			return findTestObject('Object Repository/Mobile/VCB/Android/Accounts Screen/btnStickG1A');
		} else {
			return findTestObject('Object Repository/Mobile/VCB/Android/Accounts Screen/btnStick');
		}
		
	}
	@Override
	public TestObject btnBack() {
		return findTestObject('Object Repository/Mobile/VCB/Android/Accounts Screen/btnBack');
	}

	@Override
	public TestObject btnFirstWatchList() {
		return findTestObject('Object Repository/Mobile/VCB/Android/Accounts Screen/btnFirstWatchList');
	}

	@Override
	public TestObject cbxSetFutureOrder() {
		return findTestObject('Object Repository/Mobile/VCB/Android/Accounts Screen/cbxSetFutureOrder');
	}
	@Override
	public List<List<String>> getAllAccountItem() {
		return commonPage.getAllItemsListView([
			"//android.widget.TextView[2]",
			"//android.widget.TextView[1]",
			"//android.widget.TextView[3]"
		], this.fraAccountList())
	}
}
