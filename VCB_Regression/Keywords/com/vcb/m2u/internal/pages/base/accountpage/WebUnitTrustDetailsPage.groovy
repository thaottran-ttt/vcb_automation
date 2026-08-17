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

public class WebUnitTrustDetailsPage extends WebBasePage {

	private static final String UNIT_TRUST = 'Unit Trust*';

	private static final String ONE_TIME_SUBSCRIPTION = 'ONE TIME SUBSCRIPTION';

	private static final String VIEW_ALL_INVESTMENT_TYPES = 'VIEW ALL INVESTMENT TYPES';
	
	private static final String REGULAR_SAVINGS_PLAN = 'REGULAR SAVINGS PLAN'

	private static final String PLEASE_SELECT = 'Please Select'

	private static final String PERSONAL_CURRENT_ACCOUNT = 'Personal Current Account'

	private static final String CONTACT_NUMBER = 'Contact Number'

	private static final String TOP_UP = 'top up'

	private static final String REDEEM_UNITS = 'Redeem Units'

	private static final String REDEEM = 'Redeem'

	private static final String TOP_UP_OPTION = 'Top-up'

	private static final String UNITS_TO_SWITCH = 'Units to Switch'

	private static final String SWITCH = 'Switch'

	private static final String HWANG_CHINA_SELECT_FUND = 'HWANG CHINA SELECT FUND'

	private static final String TABLE_CLASS = 'AccountTable---wealthTable'

	private static final String CHECKBOX_CLASS = 'col-xs-1 col-sm-1 undefined'

	private static final String DOT_CLASS = 'PopOver---clsToolBar'

	private static final String DYNO_TABLE_TABLE = 'DynoTable---table';
	
	
	WebCommonPage webCommonPage = getPage(WebCommonPage);
	
	void clickUnitTrust() {
		WebUIKeyword.scrollToTestObject(webCommonPage.lblNormalizeSpace(UNIT_TRUST, 1, HTMLTag.h6));
		WebUIKeyword.delay(GlobalVariable.G_timeoutMiniShort);
		WebUIKeyword.click(webCommonPage.lblNormalizeSpace(UNIT_TRUST, 1, HTMLTag.h6));
	}
	
	void verifyUnitTrustPage(unitTrust) {
		WebUIKeyword.verifyElementVisible(webCommonPage.lblNormalizeSpace(unitTrust, 1, HTMLTag.span));
	}
	
	void clickViewAllInvestmentTypes() {
		WebUIKeyword.click(webCommonPage.lblNormalizeSpace(VIEW_ALL_INVESTMENT_TYPES, 1, HTMLTag.span));
		WebUIKeyword.delay(GlobalVariable.G_timeoutMiniShort);
	}
		
	void clickOneTimeSubscription() {
		WebUIKeyword.click(webCommonPage.lblNormalizeSpace(ONE_TIME_SUBSCRIPTION, 1, HTMLTag.span));

	}
	
	void clickRegularSavingsPlan() {
		WebUIKeyword.scrollToTestObject(webCommonPage.lblNormalizeSpace(REGULAR_SAVINGS_PLAN, 1, HTMLTag.span));
		WebUIKeyword.click(webCommonPage.lblNormalizeSpace(REGULAR_SAVINGS_PLAN, 1, HTMLTag.span));
	}
	
	void verifyAccountTable() {
		WebUIKeyword.verifyElementVisible(webCommonPage.lblContainClass(DYNO_TABLE_TABLE, 1));
	}
	
	void clickTopup() {
		WebUIKeyword.delay(GlobalVariable.G_timeoutMedium);
		WebUIKeyword.scrollToTestObject(webCommonPage.lblNormalizeSpace(VIEW_ALL_INVESTMENT_TYPES, 1, HTMLTag.span));
    	WebUIKeyword.delay(GlobalVariable.G_timeoutMiniShort);
		WebUIKeyword.click(webCommonPage.lblContainClass(DOT_CLASS, 1));
		WebUIKeyword.click(webCommonPage.lblNormalizeSpace(TOP_UP_OPTION, 1, HTMLTag.span));
	}
	
	void clickRedeem() {
		WebUIKeyword.delay(GlobalVariable.G_timeoutMedium);
		WebUIKeyword.scrollToTestObject(webCommonPage.lblNormalizeSpace(VIEW_ALL_INVESTMENT_TYPES, 1, HTMLTag.span));
    	WebUIKeyword.delay(GlobalVariable.G_timeoutMiniShort);
		WebUIKeyword.click(webCommonPage.lblContainClass(DOT_CLASS, 1));
		WebUIKeyword.click(webCommonPage.lblNormalizeSpace(REDEEM, 1, HTMLTag.span));
	}
	
	void clickSwitch() {		
		WebUIKeyword.delay(GlobalVariable.G_timeoutMedium);
		WebUIKeyword.scrollToTestObject(webCommonPage.lblNormalizeSpace(VIEW_ALL_INVESTMENT_TYPES, 1, HTMLTag.span));
    	WebUIKeyword.delay(GlobalVariable.G_timeoutMiniShort);
		WebUIKeyword.click(webCommonPage.lblContainClass(DOT_CLASS, 1));
		WebUIKeyword.click(webCommonPage.lblNormalizeSpace(SWITCH, 1, HTMLTag.a));
	}
	 
	void fillFormTopUp(contactNumber) {
    	WebUIKeyword.delay(GlobalVariable.G_timeoutOneMinute);
		WebUIKeyword.click(webCommonPage.lblNormalizeSpace(PLEASE_SELECT, 3, HTMLTag.span));
		WebUIKeyword.click(webCommonPage.lblNormalizeSpace(PERSONAL_CURRENT_ACCOUNT, 1, HTMLTag.span));
		webCommonPage.inputTextForLabel(CONTACT_NUMBER, contactNumber);
		WebUIKeyword.click(webCommonPage.lblContainClass(CHECKBOX_CLASS, 1));
		WebUIKeyword.click(webCommonPage.lblContainClass(CHECKBOX_CLASS, 2));
		WebUIKeyword.click(webCommonPage.lblNormalizeSpace(TOP_UP, 1, HTMLTag.button));
	}
	
	void fillFormRedeem(contactNumber) {
		WebUIKeyword.delay(GlobalVariable.G_timeoutOneMinute);
		webCommonPage.inputTextForLabel(REDEEM_UNITS, '1');
		webCommonPage.inputTextForLabel(CONTACT_NUMBER, contactNumber);
		WebUIKeyword.click(webCommonPage.lblContainClass(CHECKBOX_CLASS, 1));
		WebUIKeyword.click(webCommonPage.lblNormalizeSpace(REDEEM, 1, HTMLTag.button));
	}
	
	void fillFormSwitch(contactNumber) {
		WebUIKeyword.delay(GlobalVariable.G_timeoutOneMinute);
		webCommonPage.inputTextForLabel(UNITS_TO_SWITCH, '1');
		WebUIKeyword.click(webCommonPage.lblNormalizeSpace(PLEASE_SELECT, 3, HTMLTag.span));
		WebUIKeyword.click(webCommonPage.lblNormalizeSpace(HWANG_CHINA_SELECT_FUND, 1, HTMLTag.span));
		webCommonPage.inputTextForLabel(CONTACT_NUMBER, contactNumber);
		WebUIKeyword.click(webCommonPage.lblContainClass(CHECKBOX_CLASS, 1));
		WebUIKeyword.click(webCommonPage.lblNormalizeSpace(SWITCH, 1, HTMLTag.button));
	}
}
