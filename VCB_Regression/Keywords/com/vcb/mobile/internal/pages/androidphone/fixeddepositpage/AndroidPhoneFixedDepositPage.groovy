package com.vcb.mobile.internal.pages.androidphone.fixeddepositpage

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
import com.vcb.mobile.internal.pages.base.fixeddepositpage.FixedDepositPage

import internal.GlobalVariable

public class AndroidPhoneFixedDepositPage extends FixedDepositPage{
	@Override
	public TestObject btnApplyNow() {
		return findTestObject('Object Repository/Mobile/VCB/Android/Apply Screen/lblCurrentStep')
	}

	@Override
	public TestObject lblCurrentStep(String stepNo) {
		return findTestObject('Object Repository/Mobile/VCB/Android/Apply Screen/lblCurrentStep',[('text') : stepNo])
	}

	@Override
	public TestObject rdProductType(String productType) {
		return findTestObject('Object Repository/Mobile/VCB/Android/Apply Screen/rdFDAccountType',[('text') : productType])
	}

	@Override
	public TestObject txtField(String fieldName) {
		return findTestObject('Object Repository/Mobile/VCB/Android/Apply Screen/txtEnterFdDetail',[('Verify') : fieldName])
	}

	@Override
	public TestObject rdValue(String question, String value) {
		return findTestObject('Object Repository/Mobile/VCB/Android/Common/Dropdown/lblDropdownValue',[('section') : question, ('text') : value])
	}

	@Override
	public TestObject lblTotalReposit() {
		return findTestObject('Object Repository/Mobile/VCB/Android/Fixed Deposits Screen/lblTotalDeposit')
	}

	@Override
	public TestObject cardItem(String accountType, String accountNo) {
		return findTestObject('Object Repository/Mobile/VCB/Android/Common/Dropdown/lblDropdownValue',[('accountType') : accountType, ('accountNo') : accountNo])
	}

	@Override
	public TestObject lbldepositAmount(String accountType, String accountNo) {
		return findTestObject('Object Repository/Mobile/VCB/Android/Fixed Deposits Screen/lblFDAmount',[('accountType') : accountType, ('accountNo') : accountNo])
	}

	@Override
	public TestObject lblNoCert(String accountType, String accountNo) {
		return findTestObject('Object Repository/Mobile/VCB/Android/Common/Dropdown/lblDropdownValue',[('accountType') : accountType, ('accountNo') : accountNo])
	}

	@Override
	public TestObject btnMakeAPlacement() {
		return commonPage.btnDynamic("Make A Placement")
	}

	@Override
	public TestObject lblFDDetailInfo(String text) {
		return null
	}

	@Override
	public TestObject divFDCard(String fdType, String fdAccountNo, String noCerts, String amount) {
		return findTestObject('Object Repository/Mobile/VCB/Android/Fixed Deposits Screen/divFdCard',[
			('fdType') : fdType, ('fdAccountNo') : fdAccountNo, ('noCerts') : noCerts, ('amount') : amount])
	}

	@Override
	public TestObject frmDashboardListView() {
		return findTestObject('Object Repository/Mobile/VCB/Android/Fixed Deposits Screen/frmDashboardListView');
	}

	@Override
	public List<List<String>> getDashboardListView() {
		return commonPage.getAllItemsListView([
			"//android.widget.TextView[1]",
			"//android.widget.TextView[2]",
			"//android.widget.TextView[3]",
			"//android.widget.TextView[4]"
		], this.frmDashboardListView(), 1)
	}
	
}