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
import org.openqa.selenium.Keys as Keys
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import com.vcb.m2u.internal.pages.base.WebBasePage
import com.vcb.common.WebUIKeyword
import com.vcb.m2u.internal.pages.base.HTMLTag
import com.vcb.m2u.internal.pages.base.WebCommonPage
import com.kms.katalon.core.webui.common.WebUiCommonHelper
import org.openqa.selenium.WebElement
import java.util.Arrays
import internal.GlobalVariable

public class WebWealthGenericPage extends WebBasePage {

	private static final String CREATE_GOAL = 'Create Goal'

	private static final String PROCEED = 'PROCEED'

	private static final String RETIREMENT_GOAL = 'Retirement Goal'

	private static final String NEXT = 'NEXT'

	private static final String START_INVESTING = 'START INVESTING'

	private static final String PLEASE_SELECT = 'Please Select'

	private static final String PERSONAL_CURRENT_ACCOUNT = 'Personal Current Account'

	private static final String MAMG_GLOBAL_DIVIDEND_FUND_MYR_CLASS = 'MAMG GLOBAL DIVIDEND FUND (MYR CLASS)'

	private static final String INVEST_NOW = 'INVEST NOW'

	private static final String CONFIRM = 'CONFIRM'

	private static final String AGREE_CONFIRM = 'AGREE & CONFIRM'

	private static final String OVERVIEW = 'Overview'

	private static final String TOTAL_ASSETS = 'Total Assets'

	private static final String TOTAL_LIABILITIES = 'Total Liabilities'

	private static final String ASSET_CLASSIFICATION = 'Asset classification'

	private static final String CASH = 'Cash'

	private static final String ALTERNATIVE = 'Alternative'

	private static final String EQUITY = 'Equity'

	private static final String FIXED_INCOME = 'Fixed Income'

	private static final String GOAL_BASED_INVESTMENT = 'Goal-Based Investment'

	private static final String FUND_XPATH = "(//*[contains(@class, 'col-xs-2 col-sm-1')])[2]/div/img"


	WebCommonPage webCommonPage = getPage(WebCommonPage);

	void navigateToGoalBasedInvestment() {
		WebUIKeyword.delay(GlobalVariable.G_timeoutMedium);
		WebUIKeyword.scrollToTestObject(webCommonPage.lblNormalizeSpace(CREATE_GOAL, 1, HTMLTag.span));
		WebUIKeyword.delay(GlobalVariable.G_timeoutMiniShort);
		WebUIKeyword.click(webCommonPage.lblNormalizeSpace(CREATE_GOAL, 1, HTMLTag.span));
		WebUIKeyword.delay(GlobalVariable.G_timeoutLong);
		WebUIKeyword.scrollToTestObject(webCommonPage.lblNormalizeSpace(PROCEED, 1, HTMLTag.button));
		WebUIKeyword.delay(GlobalVariable.G_timeoutMiniShort);
		WebUIKeyword.click(webCommonPage.lblNormalizeSpace(PROCEED, 1, HTMLTag.button));
	}
	
	void clickRetirementGoal() {
		WebUIKeyword.click(webCommonPage.lblNormalizeSpace(RETIREMENT_GOAL, 1, HTMLTag.span));
	}
	
	void clickNext() {
		WebUIKeyword.delay(GlobalVariable.G_timeoutMedium);
		WebUIKeyword.scrollToTestObject(webCommonPage.lblNormalizeSpace(NEXT, 1, HTMLTag.button));
		WebUIKeyword.delay(GlobalVariable.G_timeoutMiniShort);
		WebUIKeyword.click(webCommonPage.lblNormalizeSpace(NEXT, 1, HTMLTag.button));
	}
	
	void clickStartInvesting() {
		WebUIKeyword.delay(GlobalVariable.G_timeoutMedium);
		WebUIKeyword.scrollToTestObject(webCommonPage.lblNormalizeSpace(START_INVESTING, 1, HTMLTag.button));
		WebUIKeyword.delay(GlobalVariable.G_timeoutMiniShort);
		WebUIKeyword.click(webCommonPage.lblNormalizeSpace(START_INVESTING, 1, HTMLTag.button));
	}

	void selectFund() {
		WebUIKeyword.delay(GlobalVariable.G_timeoutOneMinute);
		WebUIKeyword.scrollToTestObject(findTestObject('Object Repository/Website/M2U/Browsers/Dynamic/dynamic', ['text': FUND_XPATH]));
		WebUIKeyword.delay(GlobalVariable.G_timeoutMiniShort);
		WebUIKeyword.click(findTestObject('Object Repository/Website/M2U/Browsers/Dynamic/dynamic', ['text': FUND_XPATH]));
	}
	
	void clickInvestNow() {
		WebUIKeyword.delay(GlobalVariable.G_timeoutMedium);
		WebUIKeyword.scrollToTestObject(webCommonPage.lblNormalizeSpace(INVEST_NOW, 1, HTMLTag.button));
		WebUIKeyword.delay(GlobalVariable.G_timeoutMiniShort);
		webCommonPage.inputTextForLabel(MAMG_GLOBAL_DIVIDEND_FUND_MYR_CLASS, '100');
		WebUIKeyword.click(webCommonPage.lblNormalizeSpace(INVEST_NOW, 1, HTMLTag.button));
	}
	
	void selectTransferFromAndConfirm() {
		WebUIKeyword.delay(GlobalVariable.G_timeoutLong);
		WebUIKeyword.scrollToTestObject(webCommonPage.lblNormalizeSpace(PLEASE_SELECT, 3, HTMLTag.span));
		WebUIKeyword.delay(GlobalVariable.G_timeoutMiniShort);
		WebUIKeyword.click(webCommonPage.lblNormalizeSpace(PLEASE_SELECT, 3, HTMLTag.span));
		WebUIKeyword.click(webCommonPage.lblNormalizeSpace(PERSONAL_CURRENT_ACCOUNT, 1, HTMLTag.span));
		WebUIKeyword.scrollToTestObject(webCommonPage.lblNormalizeSpace(CONFIRM, 1, HTMLTag.button));
		WebUIKeyword.delay(GlobalVariable.G_timeoutMiniShort);
		WebUIKeyword.click(webCommonPage.lblNormalizeSpace(CONFIRM, 1, HTMLTag.button));
	}
	
	void agreeAndConfirm() {
		WebUIKeyword.delay(GlobalVariable.G_timeoutMedium);
		WebUIKeyword.click(webCommonPage.lblContainClass('col-xs-2 col-sm-1', 2));
		WebUIKeyword.click(webCommonPage.lblContainClass('col-xs-2 col-sm-1', 3));
		WebUIKeyword.click(webCommonPage.lblContainClass('col-xs-2 col-sm-1', 4));
		WebUIKeyword.click(webCommonPage.lblNormalizeSpace(AGREE_CONFIRM, 1, HTMLTag.button));
	}

	void verifyOverviewTab() {
		WebUIKeyword.verifyElementPresent(webCommonPage.lblNormalizeSpace(OVERVIEW, 1, HTMLTag.a));
		WebUIKeyword.verifyElementPresent(webCommonPage.lblNormalizeSpace(TOTAL_ASSETS, 1, HTMLTag.label));
		WebUIKeyword.verifyElementPresent(webCommonPage.lblNormalizeSpace(TOTAL_LIABILITIES, 1, HTMLTag.label));
	}
	
	void verifyAssetTab() {
		WebUIKeyword.click(webCommonPage.lblNormalizeSpace(ASSET_CLASSIFICATION, 1, HTMLTag.a));
		WebUIKeyword.verifyElementPresent(webCommonPage.lblNormalizeSpace(ASSET_CLASSIFICATION, 1, HTMLTag.a));
		WebUIKeyword.verifyElementPresent(webCommonPage.lblNormalizeSpace(CASH, 1, HTMLTag.label));
		WebUIKeyword.verifyElementPresent(webCommonPage.lblNormalizeSpace(ALTERNATIVE, 1, HTMLTag.label));
		WebUIKeyword.verifyElementPresent(webCommonPage.lblNormalizeSpace(EQUITY, 1, HTMLTag.label));
		WebUIKeyword.verifyElementPresent(webCommonPage.lblNormalizeSpace(FIXED_INCOME, 1, HTMLTag.label));
	}
	
	void verifyGoalBasedInvestment() {
		WebUIKeyword.verifyElementPresent(webCommonPage.lblNormalizeSpace(GOAL_BASED_INVESTMENT, 1, HTMLTag.h3));
	}
}
