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
import com.vcb.mobile.internal.pages.base.accountpage.ASNBPage

import internal.GlobalVariable

public class AndroidPhoneASNBPage extends ASNBPage {

	@Override
	public TestObject frmDashboardListView() {
		return findTestObject('Object Repository/Mobile/VCB/Android/ASNB Screen/frmDashboardListView');
	}

	@Override
	List<List<String>> getASNBListView() {
		'Get item list(Account name, account number, RM) in wealth screen'
		return commonPage.getAllItemsListView([
			"//android.widget.TextView[1]",
			"//android.widget.TextView[2]",
			"//android.widget.TextView[3]"
		],
		this.frmDashboardListView())
	}

	/**
	 * Retrieves a list of list of strings in Wealth screen
	 *
	 * @return A list of list of strings where each sublist contains text from specific elements in the list view.
	 */
	@Override
	List<List<String>> getSingelElement() {
		'Get item list(Account name) in wealth screen'
		return commonPage.getAllItemsListView([
			"//android.widget.TextView[1]"
		], this.frmDashboardListView())
	}
}
