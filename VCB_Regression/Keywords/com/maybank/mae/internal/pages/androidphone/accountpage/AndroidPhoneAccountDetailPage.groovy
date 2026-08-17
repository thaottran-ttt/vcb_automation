package com.maybank.mae.internal.pages.androidphone.accountpage

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

import com.maybank.mae.internal.pages.base.accountpage.AccountDetailPage

import internal.GlobalVariable

public class AndroidPhoneAccountDetailPage extends AccountDetailPage{

	@Override
	public TestObject lblRecordAccount(int index) {
		return findTestObject('Object Repository/Mobile/MAE/Android/Account Screen/lblRecordAccount', ['index': index]);
	}

	@Override
	public TestObject btnViewTransactions() {
		return findTestObject('Object Repository/Mobile/MAE/Android/Account Screen/btnViewTransactions');
	}

	@Override
	public TestObject drpTransactionHistory() {
		return findTestObject('Object Repository/Mobile/MAE/Android/Account Screen/ddTransactionHistory');
	}

	@Override
	public TestObject lblDynamicHistory(String text) {
		return findTestObject('Object Repository/Mobile/MAE/Android/Account Screen/lblDynamicHistory', ['text': text]);
	}

	@Override
	public TestObject lblStartSpending() {
		return findTestObject('Object Repository/Mobile/MAE/Android/Account Screen/lblStartSpending');
	}

	@Override
	public TestObject btnClose() {
		return findTestObject('Object Repository/Mobile/MAE/Android/Account Screen/btnClose');
	}

	@Override
	public TestObject btnBack() {
		return findTestObject('Object Repository/Mobile/MAE/Android/Account Screen/btnBack');
	}

	@Override
	public TestObject lblTransactionsAmount() {
		return findTestObject('Object Repository/Mobile/MAE/Android/Transactions Screen/lblTransactionsAmount');
	}

	@Override
	public TestObject lblTransactionsDetail() {
		return findTestObject('Object Repository/Mobile/MAE/Android/Transactions Screen/lblTransactionsDetail');
	}

	@Override
	public TestObject btnFilter() {
		return findTestObject('Object Repository/Mobile/MAE/Android/Transactions Screen/btnFilter');
	}

	@Override
	public TestObject rbtn30Days() {
		return findTestObject('Object Repository/Mobile/MAE/Android/Transactions Screen/rbtn30Days');
	}

	@Override
	public TestObject rbtn60Days() {
		return findTestObject('Object Repository/Mobile/MAE/Android/Transactions Screen/rbtn60Days');
	}

	@Override
	public TestObject rbtn90Days() {
		return findTestObject('Object Repository/Mobile/MAE/Android/Transactions Screen/rbtn90Days');
	}
	@Override
	public TestObject btnApplyFilter() {
		return findTestObject('Object Repository/Mobile/MAE/Android/Transactions Screen/btnApplyFilter');
	}

	@Override
	public TestObject btnNextPage() {
		return findTestObject('Object Repository/Mobile/MAE/Android/Transactions Screen/btnNextPage');
	}

	@Override
	public TestObject lblPageNumber() {
		return findTestObject('Object Repository/Mobile/MAE/Android/Transactions Screen/lblPageNumber');
	}

	@Override
	public TestObject btnDone() {
		return findTestObject('Object Repository/Mobile/MAE/Android/Transactions Screen/btnDone');
	}

	@Override
	public TestObject lblDateLastRecord() {
		return findTestObject('Object Repository/Mobile/MAE/Android/Transactions Screen/lblDateLastRecord');
	}

	@Override
	public TestObject lblAmountLastRecord() {
		return findTestObject('Object Repository/Mobile/MAE/Android/Transactions Screen/lblAmountLastRecord');
	}

	@Override
	public TestObject btnThreeDots(String text) {
		return findTestObject('Object Repository/Mobile/MAE/Android/Accounts Screen/btnThreeDots', ['text': text])
	}

	@Override
	public TestObject txtContactBank() {
		return findTestObject('Object Repository/Mobile/MAE/Android/Accounts Screen/txtContactBank')
	}

	@Override
	public TestObject lbl90Days(String txt) {
		return findTestObject('Object Repository/Mobile/MAE/Dynamic/lblAnyText', ['Verify': txt]);
	}

	@Override
	public TestObject lblAnyText(String txt) {
		return findTestObject('Object Repository/Mobile/MAE/Dynamic/lblAnyText', ['Verify': txt]);
	}

	@Override
	public TestObject lblContainDynamic(String txt) {
		return findTestObject('Object Repository/Mobile/MAE/Dynamic/lblContainDynamic', ['text': txt]);
	}

	@Override
	public TestObject lblStartsWith(String text) {
		return findTestObject('Object Repository/Mobile/MAE/Dynamic/lblStartsWith', ['Verify': text]);
	}

	@Override
	public TestObject lblBalance() {
		return findTestObject('Object Repository/Mobile/MAE/Android/Account Screen/lblBalance');
	}

	@Override
	public TestObject btnShare() {
		return findTestObject('Object Repository/Mobile/MAE/Android/Account Screen/btnShare');
	}

	@Override
	public TestObject fraTransactionGroupItem() {
		return findTestObject('Object Repository/Mobile/MAE/Android/Account Screen/ShareReceipt/transactionGroupItem');
	}

	@Override
	public TestObject lblShareReceiptFirstItem() {
		return findTestObject('Object Repository/Mobile/MAE/Android/Transactions Screen/lblShareReceiptFirstItem');
	}

	@Override
	public TestObject lblShareReceiptDetailDate() {
		return findTestObject('Object Repository/Mobile/MAE/Android/Account Screen/ShareReceipt/lblShareReceiptDetailDate');
	}

	@Override
	public TestObject lblItemTransaction(String groupName, String itemName) {
		return findTestObject('Object Repository/Mobile/MAE/Android/Transactions Screen/lblItemTransaction', ['groupName': groupName, 'text': itemName]);
	}

	@Override
	void verifyAllShareReceiptItems(TestObject groupsTO, FailureHandling flowControl = defaultFailureHandling){
		def prevPageSource = ""
		while(true) {
			this.verifyOnAScreen([
				'//android.widget.TextView[1]'
			], groupsTO, flowControl)
			commonPage.swipe(5, 1000, 5, 10)
			def currentPageSource = driver.getPageSource()
			if(prevPageSource.equals(currentPageSource)) {
				break
			}
			prevPageSource = currentPageSource
		}

		return
	}

	@Override
	public TestObject btnCloseMeetballMenu() {
		return findTestObject('Object Repository/Mobile/MAE/Android/Account Screen/btnCloseMeetballMenu');
	}
}
