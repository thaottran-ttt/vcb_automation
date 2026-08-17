package com.maybank.mae.internal.pages.base

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
import com.maybank.common.MobileDriver
import com.maybank.utils.StringUtils

import internal.GlobalVariable

public abstract class EnterAmountPage extends BasePage{

	abstract TestObject closeToastMsg()

	String EnterAmount = 'Enter amount'

	String EnterRecipientReference = 'Enter recipient reference'

	CommonPage commonPage = getPage(CommonPage)

	/**
	 * Enter string amount
	 */
	void enterAmount(String amount, FailureHandling flowControl = defaultFailureHandling) {
		'Verify Enter Amount is visible'
		commonPage.verifyTextVisible(EnterAmount, GlobalVariable.G_timeoutMedium, flowControl)

		'Enter amount'
		commonPage.enterNO(amount, true, 'Enter amount')
	}

	/**
	 * Edit string amount
	 */
	void editEnterAmount(String amount, FailureHandling flowControl = defaultFailureHandling) {
		'Verify Enter Amount is visible'
		commonPage.verifyTextVisible(EnterAmount, GlobalVariable.G_timeoutMedium, flowControl)

		'Get old amount'
		String oldAmount = MobileDriver.getText(commonPage.txtEditNo(1), GlobalVariable.G_timeoutLong, defaultFailureHandling)

		'Remove old amount'
		commonPage.removeEnterNo(oldAmount.length())

		'Enter new amount'
		commonPage.enterNO(amount, true, 'Enter amount')
	}

	/**
	 * Enter string amount for DuitNow
	 */
	void enterAmountDuitNow(String amount, FailureHandling flowControl = defaultFailureHandling) {
		'Verify Enter Amount is visible'
		commonPage.verifyTextVisible(EnterAmount, GlobalVariable.G_timeoutMedium, flowControl)

		'Close toast message'
		MobileDriver.tap(this.closeToastMsg(), GlobalVariable.G_timeoutMedium, flowControl)

		'Enter amount'
		commonPage.enterNO(amount, true, 'Enter amount')
	}

	/**
	 * Verify field validation Enter Amount
	 */
	void verifyFieldValidationEnterAmount(String maxLimit = "99999999", String minLimit = "1", FailureHandling flowControl = defaultFailureHandling) {
		'Verify Enter Amount is visible'
		MobileDriver.waitForElementPresent(commonPage.lblDynamic(EnterAmount), GlobalVariable.G_timeoutMedium, FailureHandling.OPTIONAL)
		MobileDriver.verifyElementVisible(commonPage.lblDynamic(EnterAmount), GlobalVariable.G_timeoutMedium, flowControl)
		
		'Close toast message'
		MobileDriver.tap(this.closeToastMsg(), GlobalVariable.G_timeoutMedium, flowControl)

		'Enter max limit'
		commonPage.enterNO(maxLimit, false)

		'Get amount inputed'
		String txtInput = MobileDriver.getText(commonPage.txtEditNo(1), GlobalVariable.G_timeoutLong, flowControl)

		'Format maxLimit to money format'
		String formatAmount = StringUtils.formatToMoney(maxLimit)

		'Check amount inputed and maxLimit is equal'
		Mobile.verifyEqual(txtInput, formatAmount)
		MobileDriver.takeScreenshot()
		commonPage.tapTickBtn()

		'Verify Enter Recipient Reference is visible'
		MobileDriver.verifyElementVisible(commonPage.lblDynamic(EnterRecipientReference), GlobalVariable.G_timeoutLong, flowControl)

		'Tap back button'
		MobileDriver.tap(commonPage.imgNoDynamic(1), GlobalVariable.G_timeoutLong, flowControl)

		'Verify Enter Amount is visible'
		MobileDriver.verifyElementVisible(commonPage.lblDynamic(EnterAmount), GlobalVariable.G_timeoutLong, flowControl)

		'Remove old amount'
		commonPage.removeEnterNo(maxLimit.length() + 1)

		'Enter min limit'
		commonPage.enterNO(minLimit)
		MobileDriver.takeScreenshot()

		'Verify Enter Amount is visible'
		MobileDriver.verifyElementVisible(commonPage.lblDynamic(EnterRecipientReference), GlobalVariable.G_timeoutLong, flowControl)

		'Tap back button'
		MobileDriver.tapAndLog(commonPage.imgNoDynamic(1), GlobalVariable.G_timeoutLong, 'Back to Enter Amount')
	}
}
