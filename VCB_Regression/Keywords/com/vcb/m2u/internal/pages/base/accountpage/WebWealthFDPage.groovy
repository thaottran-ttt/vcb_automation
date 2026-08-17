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

public class WebWealthFDPage extends WebBasePage {

	private static final String FIXED_DEPOSIT = 'Fixed deposit'

	private static final String WEALTH_ACCORDION_CONTENT_ACCOUNT_NAME = 'WealthAccordionContent---accountName'

	private static final String VCB_CONVENTIONAL_FIXED_DEPOSIT = 'Vcb Conventional Fixed Deposit'

	private static final String TOTAL_AMOUNT = 'Total Amount'

	private static final String ACCOUNT_TYPE = 'Account Type'

	private static final String ACCOUNT_TABLE_TABLE = 'AccountTable---table'

	WebCommonPage webCommonPage = getPage(WebCommonPage);

	void verifyFixedDeposit() {
		WebUIKeyword.delay(GlobalVariable.G_timeoutMedium);
		WebUIKeyword.scrollToTestObject(webCommonPage.lblNormalizeSpace(FIXED_DEPOSIT, 1, HTMLTag.h6));
		WebUIKeyword.verifyElementPresent(webCommonPage.lblNormalizeSpace(FIXED_DEPOSIT, 1, HTMLTag.h6));
	}
	
	void gotToFixedDeposit() {
		WebUIKeyword.delay(GlobalVariable.G_timeoutMiniShort);
		WebUIKeyword.click(webCommonPage.lblNormalizeSpace(FIXED_DEPOSIT, 1, HTMLTag.h6));
		WebUIKeyword.delay(GlobalVariable.G_timeoutMiniShort);
		WebUIKeyword.click(webCommonPage.lblContainClass(WEALTH_ACCORDION_CONTENT_ACCOUNT_NAME, 1));
	}
	
	void verifyFDPage() {
		WebUIKeyword.verifyElementPresent(webCommonPage.lblNormalizeSpace(VCB_CONVENTIONAL_FIXED_DEPOSIT, 1, HTMLTag.div));
		WebUIKeyword.verifyElementPresent(webCommonPage.lblNormalizeSpace(TOTAL_AMOUNT, 1, HTMLTag.span));
		WebUIKeyword.verifyElementPresent(webCommonPage.lblNormalizeSpace(ACCOUNT_TYPE, 1, HTMLTag.span));
		WebUIKeyword.verifyElementPresent(webCommonPage.lblContainClass(ACCOUNT_TABLE_TABLE, 1));
	}

}
