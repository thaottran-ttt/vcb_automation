package com.vcb.m2u.internal.pages.base.modulesettings

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
import org.apache.commons.lang.RandomStringUtils
import com.vcb.assertions.StringAssert

public class WebSettingsPage extends WebBasePage {

	private static final String SETTINGS = 'SETTINGS'

	private static final String PERSONAL_INFO = 'Personal Info'

	private static final String SETTING_CONTAINER_TEXT_FIELD = 'SettingContainer---textField'

	private static final String PHONE_REGEX = '.*\\*\\*\\*\\*.*'

	private static final String SETTING_CONTAINER_THEME_LABEL = 'SettingContainer---themeLabel'

	private static final String SAVE = 'SAVE'

	private static final String CONFIRM = 'CONFIRM'

	private static final String YOUR_THEME_INFO_HAS_BEEN_UPDATED = 'Your Theme Info Has Been Updated'

	private static final String PROCEED = 'PROCEED'

	private static final String DYNAMIC_FIELDS_ALIGN = 'DynamicFields---align'

	private static final String LANGUAGE = 'Language'

	private static final String CURRENT_PASSWORD = 'Current Password'

	private static final String NEW_PASSWORD = 'New Password'

	private static final String CONFIRM_NEW_PASSWORD = 'Confirm New Password'

	private static final String NEW_SECURITY_PHRASE = 'New Security Phrase'

	private static final String SAVE_CHANGES = 'SAVE CHANGES'

	private static final String PLEASE_SELECT = 'Please select'

	private static final String WHAT_IS_THE_NAME_OF_YOUR_FAVORITE_UNCLE = 'What is the name of your favorite uncle?'

	private static final String SECURE2U_FEATURE = 'Secure2u Feature'

	private static final String DISABLE = 'DISABLE'

	private static final String BLOCK_CREDIT_CARD = 'Block Credit Card'

	private static final String DEACTIVATE_ACCOUNTS = 'Deactivate Accounts'

	private static final String DEACTIVATE_M2U_ACCESS = 'Deactivate M2U Access'

	private static final String ASNB_ACCOUNTS = 'ASNB Accounts'

	private static final String ACCOUNT_DETAILS = 'Account Details'

	private static final String UNLINK_ALL_ASNB_ACCOUNTS = 'UNLINK ALL ASNB ACCOUNTS'

	private static final String MANAGE_YOUR_VCB2U_PAY_ACCOUNT_DETAILS = 'Manage your Vcb2u pay account details'

	private static final String BLOG_SHOP_NAME = 'BlogShop Name'

	private static final String BUSINESS_EMAIL = 'Business Email'

	private static final String NAME = 'Name'

	private static final String ACCOUNT_FOR_M2U_PAY = 'Account for M2U Pay'

	private static final String ADDRESS = 'Address'

	private static final String POST_CODE = 'Post Code'

	private static final String STATE = 'State'

	private static final String CITY = 'City'

	private static final String MOBILE_NUMBER = 'Mobile Number'

	private static final String BUSINESS_URL = 'Business URL'

	private static final String CATEGORY = 'Category'

	private static final String EMAIL = 'EMAIL'

	private static final String ADD_EMAIL = 'ADD EMAIL'

	private static final String ACCOUNT_HOLDER_NUMBER = 'ACCOUNT HOLDER & NUMBER'

	private static final String NUMBER = '123456000000001'

	private static final String CDS_ACCOUNTS = 'CDS ACCOUNTS'

	private static final String LINK_NEW_TABUNG_HAJI_ACCOUNT = 'LINK NEW TABUNG HAJI ACCOUNT +'

	private static final String TABUNG_HAJI_ACCOUNT_MESSAGE = 'This service is temporarily unavailable on Vcb2u. You may still link your Tabung Haji account(s) with Vcb2u via our Automated Teller Machines (ATM) as usual.'

	private static final String UNIT_TRUST_MESSAGE = 'Your risk profile : Moderate Conservative (as of 02 Jul 2025 ) Click here to view your recommended funds based on your risk profile.'

	private static final String UNIT_TRUST_RISK_PROFILE = 'Unit Trust Risk Profile'

	private static final String UNIT_TRUST_CLASS = 'col-xs-12 col-sm-12 clsBody'

	private static final String ACCOUNT_NAME = 'ACCOUNT NAME'

	private static final String ACCOUNT_NUMBER = 'ACCOUNT NUMBER'

	private static final String ADD_NEW_FAVOURITE = 'ADD NEW FAVOURITE +'

	private static final String TRANSFER_LIMIT_CLASS = 'SettingContainer---coolingPeriodNote'

	private static final String TRANSFER_LIMIT = 'Transfer Limit'

	private static final String TRANSFER_LIMIT_MESSAGE = 'If you increase a Transfer Limit from its current limit, the new limit will be updated after 12 hours as a security measure. Decreasing a Transfer Limit from its current limit will be reflected immediately.'

	private static final String TRANSFER_LIMIT_ONE_AT_A_TIME = 'You can only update your Transfer Limit one at a time.'

	private static final String _3RD_PARTY_FUND_TRANSFER_VCB_TO_VCB = '3rd Party Fund Transfer (Vcb to Vcb)'

	private static final String INTERBANK_TRANSFER = 'Interbank Transfer (DuitNow Transfer, DuitNow Request and GIRO)'

	private static final String OVERSEAS_TRANSFER_SLMTDL = 'Overseas Transfer SLMTDL'

	private static final String MIGA_I_TRANSFER = 'MIGA-i Transfer'

	private static final String BAKONG_TRANSFER_LIMIT = 'Bakong Transfer Limit'

	private static final String FPX_AND_DUIT_NOW_ONLINE_BANKING_WALLETS = 'FPX and DuitNow Online Banking/Wallets'

	private static final String ASNB_OWN_ACCOUNT_TRANSFER = 'ASNB Own Account Transfer'

	private static final String ASNB_3RD_PARTY_TRANSFER = 'ASNB 3rd Party Transfer'

	private static final String CELCOM_PREPAID_XPAX = '019/013 Celcom Prepaid (Xpax)'

	private static final String PAYEE = 'PAYEE'

	private static final String PAY_LIMIT = 'PAY LIMIT'

	private static final String ADD_NEW_PAY_LIMIT = 'ADD NEW PAY LIMIT'

	private static final String SAVED_ADDRESS_MESSAGE = 'These are your current saved addresses.'

	private static final String TRANSACTION_DATE = 'TRANSACTION DATE'

	private static final String TRANSACTION_DETAILS = 'TRANSACTION DETAILS'

	private static final String AMOUNT_REFERENCE = 'AMOUNT & REFERENCE'

	private static final String PAYMENT_DETAILS = 'PAYMENT DETAILS'

	private static final String DEBIT_MANDATE = 'DEBIT MANDATE'

	private static final String DUIT_NOW = 'DuitNow'

	private static final String ACCOUNT_DETAILS_CAP = 'ACCOUNT DETAILS'

	private static final String STATUS = 'STATUS'

	private static final String DUIT_NOW_AUTO_DEBIT_BLOCKED_LIST = 'DuitNow AutoDebit Blocked List'

	private static final String MERCHANT_ID = 'MERCHANT ID'

	private static final String TYPE = 'TYPE'

	private static final String DUIT_NOW_BLOCKED_LIST = 'DuitNow Blocked List'

	private static final String DUITNOW_ID = 'DUITNOW ID'

	private static final String LINKED_ACCOUNTS = 'Linked Accounts'

	private static final String ACCOUNT = 'ACCOUNT'

	private static final String OTHER_PARTNER_SERVICES = 'Other Partner Services'

	private static final String REFERENCE = 'REFERENCE'

	private static final String FROM = 'From'

	private static final String TO = 'To'

	private static final String TYPE_OF_ZAKAT = 'Type of Zakat'

	private static final String AMOUNT = 'Amount'

	private static final String VIEW_PAST_PAYMENTS = 'View Past Payments'

	private static final String TRANSFER_DETAILS = 'TRANSFER DETAILS'

	private static final String BILL_DETAILS = 'BILL DETAILS'

	private static final String ADD_NEW_BILLER = 'ADD NEW BILLER'

	private static final String STANDING_INSTRUCTIONS = 'Standing Instructions'

	private static final String PLEASE_SELECT2 = 'Please Select'

	WebCommonPage webCommonPage = getPage(WebCommonPage);

	void clickSettings() {
		WebUIKeyword.click(webCommonPage.lblNormalizeSpace(SETTINGS, 1, HTMLTag.span));
	}

	void switchTab(tabName, scroll = false) {
		if (scroll) {
			WebUIKeyword.scrollToTestObject(webCommonPage.lblNormalizeSpace(tabName, 1, HTMLTag.span));
			WebUIKeyword.delay(GlobalVariable.G_timeoutMiniShort);
		}
		WebUIKeyword.click(webCommonPage.lblNormalizeSpace(tabName, 1, HTMLTag.span));
	}

	void switchSettings(settingName) {
		WebUIKeyword.delay(GlobalVariable.G_timeoutShort);
		WebUIKeyword.click(webCommonPage.lblNormalizeSpace(settingName, 1, HTMLTag.div));
	}

	void verifyPersonalInfo(username) {
		WebUIKeyword.delay(GlobalVariable.G_timeoutMiniShort);
		WebUIKeyword.verifyElementPresent(webCommonPage.lblNormalizeSpace(PERSONAL_INFO, 5, HTMLTag.span));
		WebUI.verifyElementAttributeValue(webCommonPage.lblContainClass(SETTING_CONTAINER_TEXT_FIELD, 1), 'value', username, GlobalVariable.G_timeoutMiniShort);
		String phone = WebUI.getAttribute(webCommonPage.lblContainClass(SETTING_CONTAINER_TEXT_FIELD, 3), 'value');
		WebUI.verifyMatch(phone, PHONE_REGEX, true);
	}

	void verifyEmailAddress(email) {
		WebUIKeyword.verifyElementPresent(webCommonPage.lblNormalizeSpace(email, 1, HTMLTag.span));
	}

	void switchTheme() {
		int randomIndex = (new Random().nextInt(4)) + 1;
		WebUIKeyword.click(webCommonPage.lblContainClass(SETTING_CONTAINER_THEME_LABEL, randomIndex));
		WebUIKeyword.click(webCommonPage.lblNormalizeSpace(SAVE, 1, HTMLTag.button));
		WebUIKeyword.delay(GlobalVariable.G_timeoutMiniShort);
		if (WebUI.verifyElementPresent(webCommonPage.lblNormalizeSpace(CONFIRM, 1, HTMLTag.span), GlobalVariable.G_timeoutMiniShort)) {
			WebUIKeyword.click(webCommonPage.lblNormalizeSpace(CONFIRM, 1, HTMLTag.span));
		} else {
			randomIndex = (new Random().nextInt(4)) + 1;
			WebUIKeyword.click(webCommonPage.lblContainClass(SETTING_CONTAINER_THEME_LABEL, randomIndex));
			WebUIKeyword.click(webCommonPage.lblNormalizeSpace(SAVE, 1, HTMLTag.button));
			WebUIKeyword.delay(GlobalVariable.G_timeoutMiniShort);
			WebUIKeyword.click(webCommonPage.lblNormalizeSpace(CONFIRM, 1, HTMLTag.span));
		}
	}

	void verifyThemeUpdatedSuccessfully() {
		WebUIKeyword.verifyElementPresent(webCommonPage.lblNormalizeSpace(YOUR_THEME_INFO_HAS_BEEN_UPDATED, 1, HTMLTag.span));
	}

	void clickProceed() {
		WebUIKeyword.click(webCommonPage.lblNormalizeSpace(PROCEED, 1, HTMLTag.button));
	}
	
	void verifyAddressPage(address) {
		WebUIKeyword.verifyElementPresent(webCommonPage.lblNormalizeSpace(SAVED_ADDRESS_MESSAGE, 1, HTMLTag.span));
		WebUIKeyword.verifyElementPresent(webCommonPage.lblNormalizeSpace(address, 1, HTMLTag.p));
	}

	void verifyContactNumber() {
		if (WebUIKeyword.waitForElementPresent(webCommonPage.lblContainClass(DYNAMIC_FIELDS_ALIGN, 2), GlobalVariable.G_timeoutMiniShort)) {
			String phone = WebUIKeyword.getText(webCommonPage.lblContainClass(DYNAMIC_FIELDS_ALIGN, 2));
			WebUI.verifyMatch(phone, PHONE_REGEX, true);
		}
	}

	void verifyNoLanguageSetting() {
		WebUI.verifyElementNotPresent(webCommonPage.lblNormalizeSpace(LANGUAGE, 1, HTMLTag.span), 0);
	}

	void fillChangePasswordForm(currentPass, newPass) {
		webCommonPage.inputTextForLabel(CURRENT_PASSWORD, currentPass);
		webCommonPage.inputTextForLabel(NEW_PASSWORD, newPass);
		webCommonPage.inputTextForLabel(CONFIRM_NEW_PASSWORD, newPass);
	}

	void clickSaveSecurityPhrase() {
		WebUIKeyword.click(webCommonPage.lblNormalizeSpace(SAVE_CHANGES, 1, HTMLTag.button));
	}

	void verifySecure2uPage() {
		WebUIKeyword.verifyElementPresent(webCommonPage.lblNormalizeSpace(SECURE2U_FEATURE, 1, HTMLTag.label));
		WebUIKeyword.verifyElementPresent(webCommonPage.lblNormalizeSpace(DISABLE, 1, HTMLTag.span));
	}

	void verifyKillSwitchPage() {
		WebUIKeyword.verifyElementPresent(webCommonPage.lblNormalizeSpace(BLOCK_CREDIT_CARD, 1, HTMLTag.a));
		WebUIKeyword.verifyElementPresent(webCommonPage.lblNormalizeSpace(DEACTIVATE_ACCOUNTS, 1, HTMLTag.a));
		WebUIKeyword.verifyElementPresent(webCommonPage.lblNormalizeSpace(DEACTIVATE_M2U_ACCESS, 1, HTMLTag.a));
	}

	void verifyASNBAccountPage() {
		WebUIKeyword.verifyElementPresent(webCommonPage.lblNormalizeSpace(ASNB_ACCOUNTS, 1, HTMLTag.span));
		WebUIKeyword.waitForElementVisible(webCommonPage.lblNormalizeSpace(ACCOUNT_DETAILS, 1, HTMLTag.th), GlobalVariable.G_timeoutMiniShort);
		WebUIKeyword.verifyElementPresent(webCommonPage.lblNormalizeSpace(ACCOUNT_DETAILS, 1, HTMLTag.th));
		WebUIKeyword.verifyElementPresent(webCommonPage.lblNormalizeSpace(UNLINK_ALL_ASNB_ACCOUNTS, 1, HTMLTag.button));
	}

	void verifyVcb2uPayPage() {
		WebUIKeyword.verifyElementPresent(webCommonPage.lblNormalizeSpace(MANAGE_YOUR_VCB2U_PAY_ACCOUNT_DETAILS, 1, HTMLTag.label));
		WebUIKeyword.verifyElementPresent(webCommonPage.lblNormalizeSpace(BLOG_SHOP_NAME, 1, HTMLTag.label));
		WebUIKeyword.verifyElementPresent(webCommonPage.lblNormalizeSpace(BUSINESS_EMAIL, 1, HTMLTag.label));
		WebUIKeyword.verifyElementPresent(webCommonPage.lblNormalizeSpace(NAME, 1, HTMLTag.label));
		WebUIKeyword.verifyElementPresent(webCommonPage.lblNormalizeSpace(ACCOUNT_FOR_M2U_PAY, 1, HTMLTag.label));
		WebUIKeyword.verifyElementPresent(webCommonPage.lblNormalizeSpace(ADDRESS, 1, HTMLTag.label));
		WebUIKeyword.verifyElementPresent(webCommonPage.lblNormalizeSpace(POST_CODE, 1, HTMLTag.label));
		WebUIKeyword.verifyElementPresent(webCommonPage.lblNormalizeSpace(STATE, 1, HTMLTag.label));
		WebUIKeyword.verifyElementPresent(webCommonPage.lblNormalizeSpace(CITY, 1, HTMLTag.label));
		WebUIKeyword.verifyElementPresent(webCommonPage.lblNormalizeSpace(MOBILE_NUMBER, 1, HTMLTag.label));
		WebUIKeyword.verifyElementPresent(webCommonPage.lblNormalizeSpace(BUSINESS_URL, 1, HTMLTag.label));
		WebUIKeyword.verifyElementPresent(webCommonPage.lblNormalizeSpace(CATEGORY, 1, HTMLTag.label));
	}

	void verifyEmailStatementDeliveryPage(xpath) {
		WebUIKeyword.delay(GlobalVariable.G_timeoutMiniShort);
		WebUIKeyword.verifyElementPresent(webCommonPage.lblNormalizeSpace(ACCOUNT_DETAILS_CAP, 1, HTMLTag.th));
		WebUIKeyword.click(findTestObject('Object Repository/Website/M2U/Browsers/Dynamic/dynamic', ['text': xpath.replace('val','Vcb Investment Bank Berhad')]));
		WebUIKeyword.verifyElementPresent(webCommonPage.lblNormalizeSpace(EMAIL, 1, HTMLTag.th));
		WebUIKeyword.click(findTestObject('Object Repository/Website/M2U/Browsers/Dynamic/dynamic', ['text': xpath.replace('val','ha*****@mail.com')]));
		WebUIKeyword.verifyElementPresent(webCommonPage.lblNormalizeSpace(ADD_EMAIL, 1, HTMLTag.button));
	}

	void verifyCDSAccountPage() {
		WebUIKeyword.delay(GlobalVariable.G_timeoutMiniShort);
		WebUIKeyword.verifyElementPresent(webCommonPage.lblNormalizeSpace(ACCOUNT_HOLDER_NUMBER, 1, HTMLTag.th));
		WebUIKeyword.verifyElementPresent(webCommonPage.lblNormalizeSpace(NUMBER, 1, HTMLTag.div));
		WebUIKeyword.verifyElementPresent(webCommonPage.lblNormalizeSpace(CDS_ACCOUNTS, 1, HTMLTag.button));
	}
	
	void clickLinkAccount() {
		WebUIKeyword.click(webCommonPage.lblNormalizeSpace(LINK_NEW_TABUNG_HAJI_ACCOUNT, 1, HTMLTag.button));
	}
	
	void verifyTabungHajiAccountPage() {
		WebUIKeyword.verifyElementPresent(webCommonPage.lblNormalizeSpace(TABUNG_HAJI_ACCOUNT_MESSAGE, 1, HTMLTag.span));
	}
	
	void verifyUnitTrustRiskProfilePage() {
		String message = WebUIKeyword.getText(webCommonPage.lblContainClass(UNIT_TRUST_CLASS,1));
		StringAssert.equals(message.replace('\n', ' '), 
			UNIT_TRUST_MESSAGE, 
			true, UNIT_TRUST_RISK_PROFILE)
	}
	
	void verifyMIGAPage() {
		WebUIKeyword.verifyElementPresent(webCommonPage.lblNormalizeSpace(ACCOUNT_NAME, 1, HTMLTag.th));
		WebUIKeyword.verifyElementPresent(webCommonPage.lblNormalizeSpace(ACCOUNT_NUMBER, 1, HTMLTag.th));
		WebUIKeyword.verifyElementPresent(webCommonPage.lblNormalizeSpace(ADD_NEW_FAVOURITE, 1, HTMLTag.button));
	}
	
	void verifyTransferLimitPage() {
		String message = WebUIKeyword.getText(webCommonPage.lblContainClass(TRANSFER_LIMIT_CLASS,1));
		StringAssert.equals(message.replace('\n', ' '),
			TRANSFER_LIMIT_MESSAGE,
			true, TRANSFER_LIMIT);
		message = WebUIKeyword.getText(webCommonPage.lblContainClass('SettingContainer---divBgBlue',1));
		StringAssert.equals(message.replace('\n', ' '),
			TRANSFER_LIMIT_ONE_AT_A_TIME,true, TRANSFER_LIMIT);
		WebUIKeyword.verifyElementPresent(webCommonPage.lblNormalizeSpace(_3RD_PARTY_FUND_TRANSFER_VCB_TO_VCB, 1, HTMLTag.span));
		WebUIKeyword.verifyElementPresent(webCommonPage.lblNormalizeSpace(INTERBANK_TRANSFER, 1, HTMLTag.span));
		WebUIKeyword.verifyElementPresent(webCommonPage.lblNormalizeSpace(OVERSEAS_TRANSFER_SLMTDL, 1, HTMLTag.span));
		WebUIKeyword.verifyElementPresent(webCommonPage.lblNormalizeSpace(MIGA_I_TRANSFER, 1, HTMLTag.span));
		WebUIKeyword.verifyElementPresent(webCommonPage.lblNormalizeSpace(BAKONG_TRANSFER_LIMIT, 1, HTMLTag.span));
		WebUIKeyword.verifyElementPresent(webCommonPage.lblNormalizeSpace(FPX_AND_DUIT_NOW_ONLINE_BANKING_WALLETS, 1, HTMLTag.span));
		WebUIKeyword.verifyElementPresent(webCommonPage.lblNormalizeSpace(ASNB_OWN_ACCOUNT_TRANSFER, 1, HTMLTag.span));
		WebUIKeyword.verifyElementPresent(webCommonPage.lblNormalizeSpace(ASNB_3RD_PARTY_TRANSFER, 1, HTMLTag.span));
		WebUIKeyword.verifyElementPresent(webCommonPage.lblNormalizeSpace(SAVE, 1, HTMLTag.button));
	}
	
	void verifyPayLimitPage() {
		WebUIKeyword.verifyElementPresent(webCommonPage.lblNormalizeSpace(CELCOM_PREPAID_XPAX, 1, HTMLTag.span));
		WebUIKeyword.verifyElementPresent(webCommonPage.lblNormalizeSpace(PAYEE, 1, HTMLTag.th));
		WebUIKeyword.verifyElementPresent(webCommonPage.lblNormalizeSpace(PAY_LIMIT, 1, HTMLTag.th));
		WebUIKeyword.verifyElementPresent(webCommonPage.lblNormalizeSpace(ADD_NEW_PAY_LIMIT, 1, HTMLTag.button));
	}
	
	void verifyWesternUnionPage() {
		WebUIKeyword.verifyElementPresent(webCommonPage.lblNormalizeSpace(TRANSACTION_DATE, 1, HTMLTag.th));
		WebUIKeyword.verifyElementPresent(webCommonPage.lblNormalizeSpace(TRANSACTION_DETAILS, 1, HTMLTag.th));
		WebUIKeyword.verifyElementPresent(webCommonPage.lblNormalizeSpace(AMOUNT_REFERENCE, 1, HTMLTag.th));
	}
	
	void verifyPayFutureRecurringPage() {
		WebUIKeyword.verifyElementPresent(webCommonPage.lblNormalizeSpace(PAYMENT_DETAILS, 1, HTMLTag.th));
		WebUIKeyword.verifyElementPresent(webCommonPage.lblNormalizeSpace(AMOUNT_REFERENCE, 1, HTMLTag.th));
		WebUIKeyword.verifyElementPresent(webCommonPage.lblNormalizeSpace(DEBIT_MANDATE, 1, HTMLTag.th));
	}
	
	void verifyDuitNowPage() {
		WebUIKeyword.waitForElementPresent(webCommonPage.lblNormalizeSpace(DUIT_NOW, 5, HTMLTag.span), GlobalVariable.G_timeoutMedium);
		WebUIKeyword.verifyElementPresent(webCommonPage.lblNormalizeSpace(DUIT_NOW, 5, HTMLTag.span));
		WebUIKeyword.verifyElementPresent(webCommonPage.lblNormalizeSpace(ACCOUNT_DETAILS_CAP, 1, HTMLTag.th));
		WebUIKeyword.verifyElementPresent(webCommonPage.lblNormalizeSpace(STATUS, 1, HTMLTag.th));
		WebUIKeyword.verifyElementPresent(webCommonPage.lblNormalizeSpace(DUIT_NOW_AUTO_DEBIT_BLOCKED_LIST, 1, HTMLTag.span));
		WebUIKeyword.verifyElementPresent(webCommonPage.lblNormalizeSpace(NAME, 1, HTMLTag.th));
		WebUIKeyword.verifyElementPresent(webCommonPage.lblNormalizeSpace(MERCHANT_ID, 1, HTMLTag.th));
		WebUIKeyword.verifyElementPresent(webCommonPage.lblNormalizeSpace(TYPE, 1, HTMLTag.th));
		WebUIKeyword.verifyElementPresent(webCommonPage.lblNormalizeSpace(DUIT_NOW_BLOCKED_LIST, 1, HTMLTag.span));
		WebUIKeyword.verifyElementPresent(webCommonPage.lblNormalizeSpace(NAME, 1, HTMLTag.th));
		WebUIKeyword.verifyElementPresent(webCommonPage.lblNormalizeSpace(DUITNOW_ID, 1, HTMLTag.th));
		WebUIKeyword.verifyElementPresent(webCommonPage.lblNormalizeSpace(TYPE, 2, HTMLTag.th));
	}
	
	void verifyLinkedAccountsPage() {
		WebUIKeyword.verifyElementPresent(webCommonPage.lblNormalizeSpace(LINKED_ACCOUNTS, 1, HTMLTag.div));
		WebUIKeyword.verifyElementPresent(webCommonPage.lblNormalizeSpace(ACCOUNT, 1, HTMLTag.th));
		WebUIKeyword.verifyElementPresent(webCommonPage.lblNormalizeSpace(STATUS, 1, HTMLTag.th));
		WebUIKeyword.verifyElementPresent(webCommonPage.lblNormalizeSpace(OTHER_PARTNER_SERVICES, 1, HTMLTag.div));
		WebUIKeyword.verifyElementPresent(webCommonPage.lblNormalizeSpace(ACCOUNT, 2, HTMLTag.th));
		WebUIKeyword.verifyElementPresent(webCommonPage.lblNormalizeSpace(STATUS, 2, HTMLTag.th));
	}
	
	void verifyPayToCardsPage() {
		WebUIKeyword.verifyElementPresent(webCommonPage.lblNormalizeSpace(TRANSACTION_DETAILS, 1, HTMLTag.th));
		WebUIKeyword.verifyElementPresent(webCommonPage.lblNormalizeSpace(REFERENCE, 1, HTMLTag.th));
	}
	
	void verifyAutoDebitForZakatPage() {
		WebUIKeyword.waitForElementPresent(webCommonPage.lblNormalizeSpace(FROM, 1, HTMLTag.th), GlobalVariable.G_timeoutMedium);
		WebUIKeyword.verifyElementPresent(webCommonPage.lblNormalizeSpace(FROM, 1, HTMLTag.th));
		WebUIKeyword.verifyElementPresent(webCommonPage.lblNormalizeSpace(TO, 1, HTMLTag.th));
		WebUIKeyword.verifyElementPresent(webCommonPage.lblNormalizeSpace(TYPE_OF_ZAKAT, 1, HTMLTag.th));
		WebUIKeyword.verifyElementPresent(webCommonPage.lblNormalizeSpace(AMOUNT, 1, HTMLTag.th));
		WebUIKeyword.verifyElementPresent(webCommonPage.lblNormalizeSpace(VIEW_PAST_PAYMENTS, 1, HTMLTag.button));
	}
	
	void verifyTransferFutureRecurringPage() {
		WebUIKeyword.waitForElementPresent(webCommonPage.lblNormalizeSpace(TRANSFER_DETAILS, 1, HTMLTag.th), GlobalVariable.G_timeoutMedium);
		WebUIKeyword.verifyElementPresent(webCommonPage.lblNormalizeSpace(TRANSFER_DETAILS, 1, HTMLTag.th));
		WebUIKeyword.verifyElementPresent(webCommonPage.lblNormalizeSpace(AMOUNT_REFERENCE, 1, HTMLTag.th));
	}
	
	void verifyManageBillsPage() {
		WebUIKeyword.verifyElementPresent(webCommonPage.lblNormalizeSpace(BILL_DETAILS, 1, HTMLTag.th));
		WebUIKeyword.verifyElementPresent(webCommonPage.lblNormalizeSpace(STATUS, 1, HTMLTag.th));
		WebUIKeyword.verifyElementPresent(webCommonPage.lblNormalizeSpace(ADD_NEW_BILLER, 1, HTMLTag.button));
	}
	
	void verifyStandingInstructionsPage() {
		WebUIKeyword.verifyElementPresent(webCommonPage.lblNormalizeSpace(STANDING_INSTRUCTIONS, 3, HTMLTag.span));
		WebUIKeyword.verifyElementPresent(webCommonPage.lblNormalizeSpace(PLEASE_SELECT, 1, HTMLTag.span));
	}
	
	void verifyMIGAiFutureOrderPage() {
		WebUIKeyword.verifyElementPresent(webCommonPage.lblNormalizeSpace(PLEASE_SELECT2, 1, HTMLTag.span));
		WebUIKeyword.verifyElementPresent(webCommonPage.lblNormalizeSpace(TRANSFER_DETAILS, 1, HTMLTag.th));
		WebUIKeyword.verifyElementPresent(webCommonPage.lblNormalizeSpace(AMOUNT, 1, HTMLTag.th));
	}
}
