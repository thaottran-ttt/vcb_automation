package com.vcb.m2u.internal.pages.base.accountpage

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
import com.vcb.assertions.BaseAssert
import com.vcb.common.WebUIKeyword
import com.vcb.m2u.internal.pages.base.WebBasePage
import com.vcb.m2u.internal.pages.base.WebCommonPage

import internal.GlobalVariable

public class WebAccountsPage extends WebBasePage {
	WebCommonPage webCommonPage = getPage(WebCommonPage)

	final String CREATE_GOAL = 'Create Goal'
	final String START = 'START'
	
	//class
	final String GOALS_PROGRESS_CLASS = 'GoalsProgress'

	TestObject imgBalanceToggle() {
		return findTestObject('Object Repository/Website/M2U/Browsers/Accounts Page/imgToggle')
	}

	TestObject lblAccount() {
		return findTestObject('Object Repository/Website/M2U/Browsers/Accounts Page/lblAccountsTotalBalance')
	}

	void clickToggle(FailureHandling flowControl = defaultFailureHandling) {
		WebUIKeyword.verifyElementVisible(this.imgBalanceToggle(), flowControl)
		WebUIKeyword.click(this.imgBalanceToggle(), flowControl)
	}

	/*
	 * verify Balance after click Toggle Button
	 * 
	 * verifyBalanceUI: a string representing the expected state or appearance of the balance UI to validate
	 */
	void verifyBalanceToggle(String verifyBalanceUI, FailureHandling flowControl = defaultFailureHandling){
		String text = WebUI.getText(this.lblAccount(), flowControl)
		boolean result = text.contains(verifyBalanceUI)
		BaseAssert.equals(result, true, "Verify balance is display")
	}

	void clickCreateGoal(FailureHandling flowControl = defaultFailureHandling) {
		WebUIKeyword.click(webCommonPage.btnNormalizeSpaceDynamic(this.CREATE_GOAL), flowControl)
	}

	/**
	 * Creates a goal using the provided dropdown and text input data.
	 *
	 * @param inputDropdown  a map containing dropdown selections required for creating the goal
	 * @param inputText      a map containing text inputs required for creating the goal
	 * @return void
	 */
	void createGoal(Map inputDropdown, Map inputText, FailureHandling flowControl = defaultFailureHandling) {
		this.clickCreateGoal(flowControl)

		webCommonPage.inputDropdownMap(inputDropdown)

		webCommonPage.inputTextMap(inputText)

		WebUIKeyword.click(webCommonPage.btnNormalizeSpaceDynamic(START))
	}

	void clickGoalsProgressShowMore(FailureHandling flowControl = defaultFailureHandling) {
		WebUIKeyword.click(webCommonPage.lblContainClass(GOALS_PROGRESS_CLASS), flowControl)
	}

	/**
	 * Verifies function TopUpAmount
	 *
	 * @param flowControl (optional) defines the flow control behavior in case of failure,
	 *                    defaulting to the global `defaultFailureHandling`
	 *
	 * @return void
	 */
	void verifyTopUpAmount(FailureHandling flowControl = defaultFailureHandling) {
		clickGoalsProgressShowMore()
		WebUIKeyword.click(webCommonPage.lblContainClass(GOALS_PROGRESS_CLASS), flowControl)
	}
}
