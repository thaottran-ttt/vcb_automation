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
import com.vcb.mobile.internal.pages.base.HomePage
import internal.GlobalVariable

public class AndroidPhoneHomePage extends HomePage {
	@Override
	public TestObject btnMenu(String lblName) {
		return findTestObject('Object Repository/Mobile/VCB/Dynamic/lblDynamic', ['text': lblName]);
	}

	@Override
	public TestObject lblAccountBalance() {
		return findTestObject('Object Repository/Mobile/VCB/Android/Home Screen/lblAccountBalance');
	}

	@Override
	public TestObject lblAccountName() {
		return findTestObject('Object Repository/Mobile/VCB/Android/Home Screen/lblAccountName');
	}

	@Override
	public TestObject lblAccountNo() {
		return findTestObject('Object Repository/Mobile/VCB/Android/Home Screen/lblAccountNo');
	}

	@Override
	public TestObject fraScrollQuickAction() {
		return findTestObject('Object Repository/Mobile/VCB/Android/Home Screen/fraScrollQuickAction');
	}

	@Override
	public TestObject btnHome() {
		return findTestObject('Object Repository/Mobile/VCB/Android/Home Screen/btnHome');
	}

	@Override
	public TestObject lblDynamic(String text) {
		return findTestObject('Object Repository/Mobile/VCB/Dynamic/lblDynamic', ['text' : text]);
	}

	@Override
	public TestObject btnViewAllAccounts() {
		return findTestObject('Object Repository/Mobile/VCB/Android/Vcb2u Screen/btnViewAllAccounts');
	}

	@Override
	public TestObject btnQRPayScan() {
		return findTestObject('Object Repository/Mobile/VCB/Android/Home Screen/btnQRPayScan');
	}

	@Override
	public TestObject btnApply() {
		return findTestObject('Object Repository/Mobile/VCB/Android/Home Screen/btnApply');
	}

	@Override
	public TestObject btnExpenses() {
		return findTestObject('Object Repository/Mobile/VCB/Android/Home Screen/btnExpenses');
	}

	@Override
	public TestObject lblSecure2u(String lblName) {
		return findTestObject('Object Repository/Mobile/VCB/Dynamic/lblDynamic', [('text') : lblName]);
	}

	@Override
	public TestObject btnLogout(String lblName) {
		return findTestObject('Object Repository/Mobile/VCB/Dynamic/lblDynamic', [('text') : lblName]);
	}

	@Override
	public TestObject btnBackToDashboard(String lblName) {
		return findTestObject('Object Repository/Mobile/VCB/Dynamic/lblAnyText', [('Verify') : lblName]);
	}

	@Override
	public TestObject btnSetting() {
		return findTestObject('Object Repository/Mobile/VCB/Android/Vcb2u Screen/btnSetting');
	}

	@Override
	public TestObject btnScan() {
		return findTestObject('Object Repository/Mobile/VCB/Android/Home Screen/btnScan');
	}

	@Override
	public TestObject btnEye() {
		return findTestObject('Object Repository/Mobile/VCB/Android/Home Screen/btnEye');
	}

	@Override
	public TestObject btnNotification() {
		return findTestObject('Object Repository/Mobile/VCB/Android/Home Screen/btnNotification');
	}

	@Override
	public TestObject txtQuickActions() {
		return findTestObject('Object Repository/Mobile/VCB/Dynamic/lblDynamic', [('text') : 'Quick Actions']);
	}

	@Override
	public TestObject btnCutomise() {
		return findTestObject('Object Repository/Mobile/VCB/Dynamic/lblDynamic', [('text') : 'Customise']);
	}

	@Override
	public TestObject btnSamaSamaLocal() {
		return findTestObject('Object Repository/Mobile/VCB/Dynamic/lblDynamic', [('text') : 'Order Food & More']);
	}

	@Override
	public TestObject btnTabung() {
		return findTestObject('Object Repository/Mobile/VCB/Dynamic/lblDynamic', [('text') : 'Tabung']);
	}

	@Override
	public TestObject btnDealsForYou() {
		return findTestObject('Object Repository/Mobile/VCB/Dynamic/lblDynamic', [('text') : 'Deals For You']);
	}

	@Override
	public TestObject btnTopReads() {
		return findTestObject('Object Repository/Mobile/VCB/Dynamic/lblDynamic', [('text') : 'Top Reads']);
	}

	@Override
	public TestObject btnManageWidgets() {
		return findTestObject('Object Repository/Mobile/VCB/Dynamic/lblDynamic', [('text') : 'Manage Widgets']);
	}

	@Override
	public TestObject btnSpendingSummary() {
		return findTestObject('Object Repository/Mobile/VCB/Dynamic/lblContainDynamic', [('text') : 'Spending Summary']);
	}

	@Override
	public TestObject btnSurveyBanner() {
		return findTestObject('Object Repository/Mobile/VCB/Android/Home Screen/btnSurveyBanner');
	}

	@Override
	public TestObject fraScrollTab() {
		return findTestObject('Object Repository/Mobile/VCB/Android/Accounts Screen/fraScrollTab');
	}

	@Override
	public TestObject btnlogout() {
		return findTestObject('Object Repository/Mobile/VCB/Android/Home Screen/btnLogout');
	}

	@Override
	public TestObject btnBackToDashboard() {
		return findTestObject('Object Repository/Mobile/VCB/Android/Home Screen/btnBacktoDashboard')
	}

	@Override
	public TestObject btnClose() {
		return findTestObject('Object Repository/Mobile/VCB/Android/PIN Screen/btnClose')
	}

	@Override
	public TestObject btnLogin() {
		return findTestObject('Object Repository/Mobile/VCB/Android/Logout Screen/btnLogin')
	}

	@Override
	public TestObject txtWelcomeBack() {
		return findTestObject('Object Repository/Mobile/VCB/Android/Logout Screen/txtWelcomeBack')
	}

	@Override
	public TestObject txtTotalSpending() {
		return findTestObject('Object Repository/Mobile/VCB/Android/Home Screen/txtTotalSpending')
	}

	@Override
	public TestObject txtExpenses() {
		return findTestObject('Object Repository/Mobile/VCB/Android/Home Screen/btnExpenses')
	}

	@Override
	public TestObject txtSpentSoFar() {
		return findTestObject('Object Repository/Mobile/VCB/Android/Home Screen/txtSpentSoFar')
	}

	@Override
	public TestObject txtThisMonth() {
		return findTestObject('Object Repository/Mobile/VCB/Android/ExpensesScreen/txtThisMonth')
	}

	@Override
	public TestObject txtSpentSofarValue() {
		return findTestObject('Object Repository/Mobile/VCB/Android/ExpensesScreen/txtSpentSofarValue')
	}

	@Override
	public TestObject txtTotalMonthlySpending() {
		return findTestObject('Object Repository/Mobile/VCB/Android/Home Screen/txtTotalMonthlySpending')
	}

	@Override
	public TestObject txtTopReads() {
		return findTestObject('Object Repository/Mobile/VCB/Android/Home Screen/btnTopReads')
	}

	@Override
	public TestObject btnHeartSymbol() {
		return findTestObject('Object Repository/Mobile/VCB/Android/TopReadsScreen/btnHeartSymbol')
	}

	@Override
	public TestObject txtTopReadsCount() {
		return findTestObject('Object Repository/Mobile/VCB/Android/TopReadsScreen/txtTopReadCount')
	}

	@Override
	public TestObject btnBackTopRead() {
		return findTestObject('Object Repository/Mobile/VCB/Android/TopReadsScreen/btnBackTopRead')
	}

	@Override
	public TestObject btnAccount() {
		return findTestObject('Object Repository/Mobile/VCB/Android/Home Screen/btnAccounts')
	}

	@Override
	public TestObject btnSettings() {
		return findTestObject('Object Repository/Mobile/VCB/Android/Home Screen/btnSettings')
	}
	@Override
	public TestObject btnAccounts() {
		return findTestObject('Object Repository/Mobile/VCB/Dynamic/lblDynamic', [('text') : 'Accounts']);
	}
	@Override
	public TestObject btnViewAll() {
		return findTestObject('Object Repository/Mobile/VCB/Android/Home Screen/btnViewAll');
	}

	@Override
	public TestObject lblMoneyLock() {
		return findTestObject('Object Repository/Mobile/VCB/Android/Account Screen/lblMoneyLock');
	}
	
	@Override
	public TestObject closeToastMsg() {
	return findTestObject('Object Repository/Mobile/VCB/Android/Enter Amount Screen/btnCloseToastMsg');
	}

	@Override
	public TestObject lblSiblingDynamicContains(String text, String text2) {
		return findTestObject('Object Repository/Mobile/VCB/Dynamic/Android/lblSiblingDynamicContains', [('text'): text, ('text2'): text2]);
	}
}

