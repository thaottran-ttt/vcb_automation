package com.vcb.mobile.internal.pages.base.transferpage

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
import com.vcb.common.MobileDriver
import com.vcb.mobile.internal.pages.base.BasePage
import com.vcb.mobile.internal.pages.base.CommonPage

import internal.GlobalVariable

public abstract class OverseasTransferPage extends BasePage {

	public static final String UITextContinue = 'Continue'
	public static final String RM = 'RM'
	public static final String UIDoneBtn = 'Done'
	public static final String UIEnterBtn = 'Enter'
	public static final String UIConfirmBtn = 'Confirm'
	public static final String UIProceedBtn = 'Proceed'
	public static final String TRANSFERRING_AFTER_WORKING_HOURS = 'Transferring after working hours'
	public static final String FOREIGN_TELEGRAPHIC_TRANSFER = 'Foreign Telegraphic Transfer'
	public static final String TRANSFER_USD = 'Transfer USD'
	public static final String BANK_NAME = 'Bank name'
	public static final String ACCOUNT_NUMBER = 'Account number'
	public static final String BRANCH_ADDRESS = 'Branch address'
	public static final String CITY = 'City'
	public static final String NAME = 'Name'
	public static final String IC_PASSPORT_BIZNO = 'IC / Passport number / Biz registration No.'
	public static final String ADDRESS_LINE1 = 'Address line 1'
	public static final String ADDRESS_LINE2 = 'Address line 2'
	public static final String COUNTRY = 'Country'
	public static final String MOBILE_NUMBER = 'Mobile number'
	public static final String PURPOSE_OF_TRANSFER = 'Purpose of transfer'
	public static final String SUB_PURPOSE = 'Sub purpose'

	public static CommonPage commonPage = getPage(CommonPage)

	void searchRecipientNation(String recipientNation, flowControl = defaultFailureHandling) {
		Mobile.tap(commonPage.imgNoDynamic(2), GlobalVariable.G_timeoutLong, flowControl)
		MobileDriver.setText(commonPage.txtEditNo(1), recipientNation, GlobalVariable.G_timeoutLong, flowControl)
		Mobile.tap(commonPage.lblTextNo(2), GlobalVariable.G_timeoutLong, flowControl)
		Mobile.tap(commonPage.lblTextNo(2), GlobalVariable.G_timeoutLong, flowControl)
	}

	void chooseTransferAccount(String accountName, flowControl = defaultFailureHandling) {
		Mobile.scrollToText(accountName)
		Mobile.tap(commonPage.lblDynamic(accountName), GlobalVariable.G_timeoutLong, flowControl)
		Mobile.tap(commonPage.lblDynamic(UITextContinue), GlobalVariable.G_timeoutLong, flowControl)
	}

	void chooseCurrency(String currency, flowControl = defaultFailureHandling) {
		Mobile.tap(commonPage.lblDynamic(RM), GlobalVariable.G_timeoutLong, flowControl)
		commonPage.selectDropdownScrollOneLine(currency, GlobalVariable.G_timeoutLong, flowControl)
		MobileDriver.tap(commonPage.lblDynamic(UIDoneBtn), GlobalVariable.G_timeoutLong, flowControl);
	}

	void setTransferAmount(String transferAmount, flowControl = defaultFailureHandling) {
		Mobile.tap(commonPage.txtEditNo(), GlobalVariable.G_timeoutLong, flowControl)
		commonPage.enterNO(transferAmount)
		Mobile.tap(commonPage.lblDynamic(UITextContinue), GlobalVariable.G_timeoutLong, flowControl)
	}

	void setRecipientMobileNumber(String recipientMobile, flowControl = defaultFailureHandling) {
		MobileDriver.setText(commonPage.txtEditNo(1), recipientMobile, GlobalVariable.G_timeoutLong, flowControl)
		Mobile.tap(commonPage.lblDynamic(UIEnterBtn), GlobalVariable.G_timeoutLong, flowControl)
		Mobile.tap(commonPage.lblDynamic(UIEnterBtn), GlobalVariable.G_timeoutLong, flowControl)
	}

	void selectForeignTelegraphicTransfer(flowControl = defaultFailureHandling) {
		MobileDriver.tap(commonPage.lblDynamic(FOREIGN_TELEGRAPHIC_TRANSFER), GlobalVariable.G_timeoutMedium, flowControl)
		MobileDriver.tap(commonPage.lblContainDynamic(TRANSFER_USD), GlobalVariable.G_timeoutMedium, flowControl)

		if (waitForElementPresent(commonPage.lblContainDynamic(TRANSFERRING_AFTER_WORKING_HOURS), GlobalVariable.G_timeoutShort, FailureHandling.OPTIONAL)) {
			MobileDriver.tap(commonPage.lblContainDynamic(UIProceedBtn), GlobalVariable.G_timeoutMedium, flowControl)
		}
	}

	void verifyStep1(String nationality, List<String> step1InputScreenLbls, List<String> step1InfoScreen, List<String> step1PopupTxts, flowControl = defaultFailureHandling) {
		MobileDriver.tap(commonPage.lblDynamic(nationality), GlobalVariable.G_timeoutMedium, flowControl)
		scrollToAndVerifyTextInList(step1InputScreenLbls, commonPage.lblContainDynamicPath(), GlobalVariable.G_timeoutMedium)
		Mobile.tap(commonPage.lblDynamic(UITextContinue), GlobalVariable.G_timeoutLong, flowControl)
		verifyElements(step1InfoScreen, commonPage.lblContainDynamicPath(), GlobalVariable.G_timeoutMedium)
		Mobile.tap(commonPage.lblDynamic(UITextContinue), GlobalVariable.G_timeoutLong, flowControl)
		verifyElements(step1PopupTxts, commonPage.lblContainDynamicPath(), GlobalVariable.G_timeoutMedium)
		Mobile.tap(commonPage.lblDynamic(UIConfirmBtn), GlobalVariable.G_timeoutLong, flowControl)
	}
	
	void verifyStep2(Map step2Data, flowControl = defaultFailureHandling) {
		'Select Bank name'
		MobileDriver.tap(commonPage.imgArrowDownDropdown(BANK_NAME, 1), GlobalVariable.G_timeoutMedium, flowControl)
		MobileDriver.tap(commonPage.lblDynamic(step2Data['bankName']), GlobalVariable.G_timeoutMedium, flowControl)
		MobileDriver.tap(commonPage.lblDynamic(UIDoneBtn), GlobalVariable.G_timeoutLong, flowControl);
		
		'Set account number'
		MobileDriver.setText(commonPage.txtEditNo(1), step2Data['accountNumber'], GlobalVariable.G_timeoutMedium, FailureHandling.STOP_ON_FAILURE)
		
		'Set branch address'
		MobileDriver.setText(commonPage.txtEditNo(2), step2Data['branchAddress'], GlobalVariable.G_timeoutMedium, FailureHandling.STOP_ON_FAILURE)
		
		'Set city'
		MobileDriver.setText(commonPage.txtEditNo(3), step2Data['city'], GlobalVariable.G_timeoutMedium, FailureHandling.STOP_ON_FAILURE)
		
		Mobile.tap(commonPage.lblDynamic(UITextContinue), GlobalVariable.G_timeoutLong, flowControl)
		Mobile.tap(commonPage.lblDynamic(UITextContinue), GlobalVariable.G_timeoutLong, flowControl)
	}
	
	void verifyStep3(Map step3InputData, flowControl = defaultFailureHandling) {
		'Select nationality'
		MobileDriver.tap(commonPage.lblDynamic(step3InputData['nationality']), GlobalVariable.G_timeoutMedium, flowControl)
		
		'Set name'
		MobileDriver.setText(commonPage.txtEditTextDynamic(NAME), step3InputData['name'], GlobalVariable.G_timeoutMedium, flowControl)
		
		'Set passport number'
		MobileDriver.setText(commonPage.txtEditTextDynamic(IC_PASSPORT_BIZNO), step3InputData['passportNumber'], GlobalVariable.G_timeoutMedium, flowControl)
		
		'Set address line 1'
		MobileDriver.setText(commonPage.txtEditTextDynamic(ADDRESS_LINE1), step3InputData['addressLine1'], GlobalVariable.G_timeoutMedium, flowControl)
		
		'Set address line 2'
		MobileDriver.setText(commonPage.txtEditTextDynamic(ADDRESS_LINE2), step3InputData['addressLine2'], GlobalVariable.G_timeoutMedium, flowControl)
		
		scrollToEnd()
		
		'Select country'
		MobileDriver.tap(commonPage.imgArrowDownDropdown(COUNTRY, 1), GlobalVariable.G_timeoutMedium, flowControl)
		delay(2)
		MobileDriver.tap(commonPage.lblTextNo(2), GlobalVariable.G_timeoutMedium, flowControl)
		
		'Set mobile number'
		MobileDriver.setText(commonPage.txtEditTextDynamic(MOBILE_NUMBER), step3InputData['mobileNumber'], GlobalVariable.G_timeoutMedium, flowControl)
		
		Mobile.tap(commonPage.lblDynamic(UITextContinue), GlobalVariable.G_timeoutLong, flowControl)
		Mobile.tap(commonPage.lblDynamic(UITextContinue), GlobalVariable.G_timeoutLong, flowControl)
	}
	
	void verifyStep4(Map step4InputData, flowControl = defaultFailureHandling) {
		MobileDriver.tap(commonPage.imgArrowDownDropdown(PURPOSE_OF_TRANSFER, 1), GlobalVariable.G_timeoutMedium, flowControl)
		MobileDriver.tap(commonPage.lblDynamic(step4InputData['purpose']), GlobalVariable.G_timeoutMedium, flowControl)
		MobileDriver.tap(commonPage.lblDynamic(UIDoneBtn), GlobalVariable.G_timeoutLong, flowControl);
		
		if (waitForElementPresent(commonPage.lblDynamic(SUB_PURPOSE), GlobalVariable.G_timeoutMiniShort, FailureHandling.OPTIONAL)) {
			MobileDriver.tap(commonPage.imgArrowDownDropdown(SUB_PURPOSE, 1), GlobalVariable.G_timeoutMedium, flowControl)
			MobileDriver.tap(commonPage.lblDynamic(step4InputData['subPurpose']), GlobalVariable.G_timeoutMedium, flowControl)
			MobileDriver.tap(commonPage.lblDynamic(UIDoneBtn), GlobalVariable.G_timeoutLong, flowControl);
		}
		
		Mobile.tap(commonPage.lblContainDynamic(step4InputData['bankFee']), GlobalVariable.G_timeoutLong, flowControl)
		scrollToEnd()
		Mobile.tap(commonPage.lblDynamic(UITextContinue), GlobalVariable.G_timeoutLong, flowControl)
	}
}
