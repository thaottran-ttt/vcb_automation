package com.vcb.m2u.internal.pages.base.fixeddeposit

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
import com.vcb.m2u.internal.pages.base.WebCommonPage
import com.vcb.m2u.internal.pages.base.WebBasePage
import com.vcb.m2u.internal.pages.base.HTMLTag
import com.vcb.common.WebUIKeyword
import internal.GlobalVariable

public class WebFixedDepositPage extends WebBasePage {
	private static final String APPLY_FD_ACCOUNT = "APPLY FD ACCOUNT"
	private static final String APPLY_FIXED_DEPOSIT = "APPLY FIXED DEPOSIT"
	private static final String APPLICATION_FOR_FIXED_DEPOSIT = 'Application For Fixed Deposit'
	private static final String CHOOSE_YOUR_ACCOUNT_TYPE = 'Choose Your Account Type'
	private static final String CONTINUE = 'CONTINUE'
	private static final String SAVE_NEXT = 'SAVE & NEXT'
	private static final String MAKE_PLACEMENT = 'MAKE PLACEMENT'
	private static final String PRINCIPAL_AMOUNT = 'Principal Amount'

	private static final WebCommonPage webCommonPage = WebBasePage.getPage(WebCommonPage)

	public static final String RADIO_BTN_CLASS = 'ApplicationPanel---radioBtn'
	public static final String DOT_CLASS = 'Card---dot'
	public static final String MENU_CLASS = 'FixedDepositsContainer---menu'

	public static enum Dropdown {
		PRODUCT_TYPE("Product Type"),
		TENURE('Tenure'),
		INTEREST_PAYMENT_FREQUENCY('Interest Payment Frequency'),
		INTEREST_PAYMENT_MODE('Interest Payment Mode'),
		MATURITY_INSTRUCTION('Maturity Instruction'),
		TERMS('Terms')

		final String value

		Dropdown(String value) {
			this.value = value
		}

		String getValue() {
			return value
		}
	}

	void clickApplyFDAccount() {
		webCommonPage.clickTestObjectWithWait(webCommonPage.btnNormalizeSpaceDynamic(APPLY_FD_ACCOUNT))
	}

	void clickApplyFD() {
		webCommonPage.clickTestObjectWithWait(webCommonPage.btnNormalizeSpaceDynamic(APPLY_FIXED_DEPOSIT))
	}

	void selectDropdown(Dropdown dropdown, value) {
		webCommonPage.selectDropdown(dropdown.getValue(), value)
	}

	void clickSaveNext() {
		TestObject btnSaveNext = webCommonPage.btnNormalizeSpaceDynamic(SAVE_NEXT)
		WebUIKeyword.scrollToTestObject(btnSaveNext)
		WebUIKeyword.click(btnSaveNext)
	}

	void fillForm(tenure, principalAmount, paymentFrequency, paymentMode, maturityInstruction) {
		selectDropdown(Dropdown.TENURE, tenure)
		webCommonPage.inputTextForLabel(PRINCIPAL_AMOUNT, principalAmount)
		selectDropdown(Dropdown.INTEREST_PAYMENT_FREQUENCY, paymentFrequency)
		selectDropdown(Dropdown.INTEREST_PAYMENT_MODE, paymentMode)
		selectDropdown(Dropdown.MATURITY_INSTRUCTION, maturityInstruction)
		WebUIKeyword.click(webCommonPage.lblContainClass(RADIO_BTN_CLASS, 1))
		WebUIKeyword.click(webCommonPage.lblContainClass(RADIO_BTN_CLASS, 2))
		WebUIKeyword.click(webCommonPage.btnNormalizeSpaceDynamic(MAKE_PLACEMENT))
	}

	void verifyFDPage() {
		WebUIKeyword.waitForElementPresent(webCommonPage.lblNormalizeSpace(APPLICATION_FOR_FIXED_DEPOSIT, 1, HTMLTag.h1), GlobalVariable.G_timeoutShort);
		WebUIKeyword.verifyElementPresent(webCommonPage.lblNormalizeSpace(APPLICATION_FOR_FIXED_DEPOSIT, 1, HTMLTag.h1));
		WebUIKeyword.verifyElementPresent(webCommonPage.lblNormalizeSpace(CHOOSE_YOUR_ACCOUNT_TYPE, 1, HTMLTag.h3));
		WebUIKeyword.verifyElementPresent(webCommonPage.btnNormalizeSpaceDynamic(CONTINUE));
	}
}
