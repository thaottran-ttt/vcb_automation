package com.vcb.mobile.internal.pages.base.accountpage

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.helper.MobileElementCommonHelper
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.testobject.TestObjectXpath
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import com.vcb.assertions.BaseAssert
import com.vcb.common.MobileDriver
import com.vcb.mobile.internal.pages.base.CommonPage
import com.vcb.mobile.internal.pages.base.BasePage
import com.vcb.utils.StringUtils

import internal.GlobalVariable

public abstract class ASNBPage extends BasePage {

	abstract TestObject frmDashboardListView()
	CommonPage commonPage = getPage(CommonPage)

	static final String TOTAL_INVESTMENTS = 'Total Amount'
	static final String NEW_TRANSFER = 'New Transfer'


	/**
	 * Retrieves a list of list of strings in Wealth screen
	 * 
	 * @return A list of list of strings where each sublist contains text from specific elements in the list view.
	 */
	abstract List<List<String>> getASNBListView()

	/**
	 * Retrieves a list of list of strings in Wealth screen
	 *
	 * @return A list of list of strings where each sublist contains text from specific elements in the list view.
	 */
	abstract List<List<String>> getSingelElement()

	/**
	 * Verify ASNB DashBoard: format Amount, format Account Number, balance, display text(TOTAL_INVESTMENTS)
	 *
	 * @return
	 */
	void verifyASNBDashBoard(FailureHandling flowControl = defaultFailureHandling) {
		MobileDriver.waitForElementPresent(commonPage.lblDynamic(TOTAL_INVESTMENTS), GlobalVariable.G_timeoutLong, FailureHandling.OPTIONAL)

		'Verify Balance'
		String balance = MobileDriver.getText(commonPage.lblRMNo(1), GlobalVariable.G_timeoutLong, flowControl)
		String balanceNo = StringUtils.extractMoney(balance)
		verifyFormatMoney([balanceNo])

		List<List<String>> listView = getASNBListView()
		def funName = StringUtils.extractValuesListView(listView, 0)
		def accountNum = StringUtils.extractValuesListView(listView, 1)
		def amount = StringUtils.extractValuesListView(listView, 2)
		'Loops through all items in listview (in wealth screen) verify account no, balance'
		for(int i = 0 ; i < listView.size(); i ++) {
			String balanceChild = StringUtils.extractMoney(amount[i].toString())
			verifyFormatMoney([balanceChild])
			if(StringUtils.isAccountNumber(accountNum[i].toString())) {
				verifyFormatAccountNumber([accountNum[i].toString()])
			}
		}
	}

	/**
	 * Verify Transaction History
	 *
	 * @return
	 */
	void verifyTransactionHistory(FailureHandling flowControl = defaultFailureHandling) {
		List<List<String>> listView = getSingelElement()
		def funName = StringUtils.extractValuesListView(listView, 0)

		'Loops through all items in listview (in ASNB Account) verify transaction history'
		for(int i = 0; i < listView.size(); i++) {
			'Tap Item'
			MobileDriver.tap(commonPage.lblDynamic(funName[i].toString()), GlobalVariable.G_timeoutLong, flowControl)
			'Wait Loading'
			commonPage.waitLoading()
			'Check Transaction'
			checkTransaction(funName[i].toString(), flowControl)
		}
	}

	/**
	 * Verify Transaction: display funName
	 *
	 * @return
	 */
	void checkTransaction(String funName, FailureHandling flowControl = defaultFailureHandling) {
		verifyElementExist(commonPage.lblContainDynamic(funName), GlobalVariable.G_timeoutLong, flowControl)
		verifyElementExist(commonPage.imgNoDynamic(1), GlobalVariable.G_timeoutLong, flowControl)
		MobileDriver.tapAndLog(commonPage.imgNoDynamic(1), GlobalVariable.G_timeoutLong, 'Icon Back', flowControl)
	}
	
	void tapNewTransfer(int timeout = GlobalVariable.G_timeoutLong , FailureHandling flowControl = defaultFailureHandling) {
		MobileDriver.tap(commonPage.lblDynamic(NEW_TRANSFER), timeout, flowControl)
	}
	
	void closeASNBdashboard(int timeout = GlobalVariable.G_timeoutLong , FailureHandling flowControl = defaultFailureHandling) {
		MobileDriver.tapAndLog(commonPage.imgNoDynamic(1), GlobalVariable.G_timeoutLong, 'Icon Back', flowControl)
	}
}
