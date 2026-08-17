package com.maybank.m2u.internal.pages.base

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
import com.maybank.common.WebUIKeyword

import internal.GlobalVariable

public class WebHomePage extends WebBasePage {

	WebCommonPage webCommonPage = getPage(WebCommonPage)

	String amountAvailableClass = "amountAvailable---"
	String transSummaryClass = "TransactionSummary---availableBalance---"
	String LOG_OUT = "LOGOUT"
	String LOG_OUT_TEXT = "You have logged out!"

	TestObject lnkNavigationBar(String text) {
		return findTestObject('Object Repository/Website/M2U/Browsers/Home Page/lnkNavigationBar', [('text') : text])
	}

	TestObject imgClosePopup() {
		return findTestObject('Object Repository/Website/M2U/Browsers/Home Page/imgClosePopup')
	}

	TestObject tabNavigationName(String text) {
		return findTestObject('Object Repository/Website/M2U/Browsers/Home Page/tabNavigationName', [('text'): text])
	}

	/**
	 * Navigates to a specific tab or navigation item within the application.
	 *
	 * @param navItem     the name of the navigation item to select
	 * @param tabName     the name of the tab to open within the selected navigation item
	 * @param flowControl (optional) defines the flow control behavior in case of failure,
	 *                    defaulting to the global `defaultFailureHandling`
	 *
	 * @return void
	 */
	void navigate(String navItem, String tabName, FailureHandling flowControl = defaultFailureHandling) {
		WebUIKeyword.click(this.lnkNavigationBar(navItem), flowControl)
		WebUIKeyword.delay(GlobalVariable.G_timeoutShort)

		Boolean isDisplayed = WebUIKeyword.waitForElementPresent(this.imgClosePopup(), GlobalVariable.G_timeoutMedium, FailureHandling.OPTIONAL)
		if(isDisplayed) {
			WebUIKeyword.delay(GlobalVariable.G_timeoutShort)
			WebUIKeyword.click(this.imgClosePopup(), flowControl)
		}

		if(tabName) {
			WebUIKeyword.click(this.tabNavigationName(tabName), flowControl)
		}

		webCommonPage.waitLoading()
	}

	Double getBalance(FailureHandling flowControl = FailureHandling.CONTINUE_ON_FAILURE) {
		String rawText = WebUIKeyword.getText(webCommonPage.lblContainClass(amountAvailableClass,3), flowControl)
		WebUI.comment(rawText)
		webCommonPage.waitLoading()

		String cleaned = rawText.replaceAll("[^\\d.]", "")
		return Double.parseDouble(cleaned)
	}

	Double selectAccount(String accNum, FailureHandling flowControl = FailureHandling.CONTINUE_ON_FAILURE) {
		WebUIKeyword.click(webCommonPage.lblContainClass(amountAvailableClass,3))
		WebUIKeyword.click(webCommonPage.lblNormalizeSpace(accNum, 2))
		webCommonPage.waitLoading()
	}

	Double getTransSummary(FailureHandling flowControl = FailureHandling.CONTINUE_ON_FAILURE) {
		String rawText = WebUIKeyword.getText(webCommonPage.lblContainClass(transSummaryClass), flowControl)
		WebUI.comment(rawText)
		webCommonPage.waitLoading()
		String cleaned = rawText.replaceAll("[^\\d.]", "")
		return Double.parseDouble(cleaned)
	}

	void logOut() {
		WebUIKeyword.click(webCommonPage.lblContainsDynamic(LOG_OUT))
		WebUIKeyword.verifyElementVisible(webCommonPage.lblContainsDynamic(LOG_OUT_TEXT), defaultFailureHandling)
	}

	void navigateSideBarMenu(String sideBarItem) {
		WebUIKeyword.click(webCommonPage.lblContainsDynamic(sideBarItem))
		webCommonPage.waitLoading()
	}
}
