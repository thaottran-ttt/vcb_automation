package com.maybank.mae.internal.pages.base

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import static org.mockito.Mockito.RETURNS_DEEP_STUBS

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

import groovy.json.StringEscapeUtils
import internal.GlobalVariable

public abstract class ShareReceiptPage extends BasePage {
	CommonPage commonPage = getPage(CommonPage)

	static final String SHARE_RECEIPT = 'Share Receipt'
	static final String SUCCESSFUL = 'Successful'
	static final String PENDING = 'Pending'
	static final String COMPLETED = 'Completed'

	static final String DONE_BTN = 'Done'

	abstract TestObject fraShareReceiptWebView()

	String UILblForeignCurrencyAmount = 'Foreign currency amount'

	/**
	 * Tap share icon
	 */
	void tapShareIcon(FailureHandling flowControl = defaultFailureHandling) {
		'Tap share icon'
		MobileDriver.tapAndLog(commonPage.imgNoDynamic(2), GlobalVariable.G_timeoutLong, 'Icon Share', flowControl)
		MobileDriver.delay(3)
		'Select back'
		Mobile.pressBack()
		MobileDriver.delay(3)
		MobileDriver.takeScreenshot()
	}

	/**
	 * Share receipts
	 */
	void shareReceipts(List<String> verifyTexts, String UIBack = DONE_BTN, FailureHandling flowControl = defaultFailureHandling) {
		'Select Share receipt'
		commonPage.tapText(SHARE_RECEIPT, GlobalVariable.G_timeoutOneMinute, flowControl)

		'Wait for Share Receipt WebView not present'
		MobileDriver.waitForElementNotPresent(this.fraShareReceiptWebView(), GlobalVariable.G_timeoutShort, FailureHandling.OPTIONAL)

		commonPage.delay(GlobalVariable.G_timeoutMiniShort - 1)

		'Verify texts in verifyTexts exist'
		commonPage.verifyElementsAndScrolling(verifyTexts, commonPage.lblContainDynamicPath(), GlobalVariable.G_timeoutLong, this.fraShareReceiptWebView())
		MobileDriver.takeScreenshot()

		'Select share icon'
		this.tapShareIcon(flowControl)
		'Tap back'
		MobileDriver.tapAndLog(commonPage.imgNoDynamic(1), GlobalVariable.G_timeoutLong, 'Icon Back',flowControl)

		'If UIBack is not null, then verify UIBack'
		if (UIBack != null) {
			verifyElements([UIBack], commonPage.lblDynamicPath(), GlobalVariable.G_timeoutLong, FailureHandling.OPTIONAL)
		}
	}

	/**
	 * Get Foreign Currency Amount
	 */
	public String getForeignCurrencyAmount(FailureHandling flowControl = defaultFailureHandling) {
		MobileDriver.delay(GlobalVariable.G_timeoutMiniShort)
		'Select Share receipt'
		if (MobileDriver.waitForElementPresent(commonPage.lblDynamic(SHARE_RECEIPT), GlobalVariable.G_timeoutOneMinute)) {
			commonPage.tapText(SHARE_RECEIPT, GlobalVariable.G_timeoutLong, flowControl)
		}
		MobileDriver.delay(GlobalVariable.G_timeoutMiniShort)
		commonPage.swipe(500, 1500, 500, 500)
		'Get Text Foreign Currency Amount'
		String txtForeignCurrencyAmount = MobileDriver.getText(commonPage.lblSiblingDynamic(UILblForeignCurrencyAmount), GlobalVariable.G_timeoutLong)
		'Tap back'
		MobileDriver.tapAndLog(commonPage.imgNoDynamic(1), GlobalVariable.G_timeoutLong, 'Icon Back',flowControl)
		return txtForeignCurrencyAmount
	}
}
