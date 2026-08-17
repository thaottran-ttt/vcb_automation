package com.vcb.mobile.internal.pages.androidphone.transferpage

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
import com.vcb.mobile.internal.pages.base.transferpage.TransferPage

import internal.GlobalVariable

public class AndroidPhoneTransferPage extends TransferPage{

	@Override
	public TestObject imgBanks() {
		return findTestObject('Object Repository/Mobile/VCB/Android/Transfer/Others/imgBanks');
	}

	@Override
	public TestObject imgOthers() {
		return findTestObject('Object Repository/Mobile/VCB/Android/Transfer/Others/imgOthers');
	}

	@Override
	public TestObject imgOverSeas() {
		return findTestObject('Object Repository/Mobile/VCB/Android/Transfer/Others/imgOverseas');
	}

	@Override
	public TestObject imgSearch() {
		return findTestObject('Object Repository/Mobile/VCB/Android/Transfer/Others/imgSearch');
	}

	@Override
	public TestObject fraScrollTab() {
		return findTestObject('Object Repository/Mobile/VCB/Android/Transfer/fraScrollTab');
	}

	@Override
	public TestObject txtSearch() {
		return findTestObject('Object Repository/Mobile/VCB/Android/Transfer/Favourites Search/txtSearch');
	}

	@Override
	public TestObject lblFavoriteItem(String favName) {
		return findTestObject('Object Repository/Mobile/VCB/Android/Transfer/Favourites Search/lblFavoriteItem', ['text': favName]);
	}

	@Override
	public TestObject imgBack() {
		return findTestObject('Object Repository/Mobile/VCB/Android/Transfer/imgBack');
	}

	@Override
	public TestObject txtRecipient() {
		return findTestObject('Object Repository/Mobile/VCB/Android/Transfer/txtRecipient');
	}

	@Override
	public TestObject txtAccountNo() {
		return findTestObject('Object Repository/Mobile/VCB/Android/Transfer/txtAccountNo');
	}

	@Override
	public TestObject lblTransferSummaryDateTimeTitle() {
		return findTestObject('Object Repository/Mobile/VCB/Android/Transfer/Transaction/lblTransferSummaryDateTimeTitle');
	}

	@Override
	public TestObject lblMessage() {
		return findTestObject('Object Repository/Mobile/VCB/Android/Transfer/Transaction/lblMessage');
	}

	@Override
	public TestObject lblSpecialAddFav() {
		return findTestObject('Object Repository/Mobile/VCB/Android/Transfer/Transaction/lblSpecialAddFav');
	}

	@Override
	public TestObject btnMainCloseFavPopUp() {
		return findTestObject('Object Repository/Mobile/VCB/Android/Transfer/Favourist/btnMainCloseFavPopUp');
	}

	@Override
	public TestObject lblRM() {
		return findTestObject('Object Repository/Mobile/VCB/Android/Transfer/Confirmation/lblRM');
	}

	@Override
	public TestObject imgV() {
		return findTestObject('Object Repository/Mobile/VCB/Android/Transfer/Confirmation/imgV');
	}
	@Override
	public TestObject lblSearchItem(String search, String item) {
		return findTestObject('Object Repository/Mobile/VCB/Android/Transfer/lblSearchItem', [search: search, item: item]);
	}
}
