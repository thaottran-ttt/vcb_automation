package com.maybank.mae.internal.pages.base.transferpage

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
import com.maybank.mae.internal.pages.base.BasePage
import com.maybank.mae.internal.pages.base.CommonPage
import com.maybank.common.MobileDriver
import internal.GlobalVariable

public abstract class EnterASNBPage extends BasePage{

	//UI text
	String UIlblEnterAmount = 'Enter amount'
	String UITransaction = 'Transaction Authorisation Code'
	String UIEnterRecipientReference = 'Enter recipient reference'
	String UILblInputAmount = '0.00'
	String UIMaxLimit = '999,999.99'
	String UIRecipientInValid = 'Please remove invalid special characters.'
	String UIDrdConvertTo = 'Please select'
	String UILblMalaysian = 'Malaysian'
	String UILblNonMalaysian = 'Non-Malaysian'
	String UILblImportant = 'Important'
	String UILblConfirm = 'Confirm'
	String UILblNext = 'Next'
	String UIDrdBankName = 'Please select'
	String UIDrdCountry = 'Please select'
	String UILblSelectCountry = 'Select Country'
	String UIDrdPurposeOfTransfer = 'Please select'
	String UIDrdSubPurpose = 'Please select'
	String UILblSubPurpose = 'Sub purpose'
	String UIDrdRelationship = 'Please select'
	String UILblRelationshipStatus = 'Relationship'
	String UILblIWillPay = 'I will pay'
	String UILblRecipientWillPay = 'Recipient will pay'
	String UILblConfirmAndSubmit = 'Confirm and Submit'
	String UILblAgreeAndConfirm = 'Agree and Confirm'
	String UILblTransferNow = 'Transfer Now'
	String UILblProceed = 'Proceed'
	String UILblTransferFailed = 'Transfer failed'
	String UILblSelectAccount = 'Select Account'
	String UILblSelectDebitingAccount = 'Select debiting account'
	String UILblTransferOverseas = 'Transfer Overseas'
	String UILblEnterAmount = 'Enter amount'
	String UILblConvertTo = 'Convert to'
	String UILblTypeCurrency = 'RM'
	String UILblCancel = 'Cancel'
	String UILblIndicativeExchangeRate  = 'Indicative exchange rate'
	String UILblDailyTransferLimit  = 'Daily transfer limit'
	String UILblServiceFee  = 'Service fee'
	String UILblAgentBeneficiaryBankFee =  'Agent/Beneficiary\nBank fee'
	String UILblReceiveMethod  = 'Receive method'
	String UILblTransferDuration = 'Transfer duration'
	String UILblDailyTransferLimitInfo = 'Cumulative limit per day for all Maybank Overseas transfer and Foreign Telegraphic transactions.'
	String UILblAgentBeneficiaryBankFeeInfo = 'You may pay the agent/beneficiary bank fee or let your recipient pay it, and the fee will be deducted from the transfer amount. The fee amount will be displayed on your transfer confirmation page later.'
	String UILblTransferDurationInfo = 'The transfer duration in number of working days.'
	String UILblForeignTelegraphicTransfer = 'Foreign Telegraphic Transfer'
	String UILblPleaseFillInYourDetails = 'Please fill in your details'
	String UILblNationality = 'Nationality'
	String UILblAddressLine1 = 'Address line 1'
	String UILblAddressLine2 = 'Address line 2'
	String UILblPostCode = 'Postcode'
	String UILblCountry = 'Country'
	String UIPageStep1Of4 = 'Step 1 of 4'
	String UILblRecipientBankDetails = 'Recipient\'s bank details:'
	String UILblBankNameBankCodeAndAddress = '●   Bank name, bank code and address'
	String UILblAccountNumber = '●   Account number'
	String UILblICPassportNumber = '●   IC/Passport number'
	String UILblNationalityAndAddress = '●   Nationality and address'
	String UILblFullName = '●   Full name'
	String UILblPhoneNumber = '●   Phone number'
	String UILblPleaseFillInRecipientBankDetails = 'Please fill in recipient\'s bank details'
	String UILblBranchAddress = 'Branch address'
	String UILblCity = 'City'
	String UILblBranchAddressInfo = 'You can proceed to make the transfer as long as the address is identifiable'
	String UILblPleaseFillInRecipientDetails = 'Please fill in recipient\'s details'
	String UILblName = 'Name'
	String UILblICPassportNumberBizRegistrationNo = 'IC / Passport number / Biz registration No.'
	String UILblMobileNumber = 'Mobile number'
	String UILblEmailAddress = 'Email address (Optional)'
	String UILblPleaseFillInTransferDetails = 'Please fill in transfer details'
	String UILblAdditionalDetails = 'Additional details (Optional)'
	
	String UILblProductName = 'Product Name'
	String UIDrdTextSelect = 'Select'
	String UILblIdTypeForASNBMember = 'ID type for ASNB member'
	String UILblIdNumberForASNBMember = 'ID number for ASNB member'
	String UILblMembershipNumber = 'Membership Number'
	String UILblRelationship = 'Relationship'
	String UILblPurposeOfTransfer = 'Purpose of transfer'
	String UIDone = 'Done'
	String UITextContinue = 'Continue'
	String UITextAgreeASNBConsent = "I've read and agreed to the ASNB Fund Price/Sales Charge and Terms & Conditions."
	String UIBtnTextTransferNow = 'Transfer Now'
	String UITextASNBConsentLine1 = "RM 1.00 service charge applies for ASNB Fixed Fund transactions"
	String UITextASNBConsentLine2 = "Any ASNB Variable Forward Price Funds (Local) transaction after 04:00PM will be processed on the next business day."
	String UITextASNBConsentLine3 = "The number of units of ASNB Variable Forward Price Funds (Foreign) in which the fund has foreign exposure is determined by the price per unit of the respective funds. It will be available within (2) business days for transactions before 04:00PM or up to (3) business days for transactions after 04:00PM."
	String UITextASNBConsentLine4 = "Money withdrawn from your insured deposit(s) is no longer protected by PIDM if transfered to non PIDM members and products."
	String UITextASNBErrorPopup = "Please agree to the Terms & Conditions to continue."
	
	//Approve Status
	String UIApprove = 'Approve'
	String UILblSecurityPurposes = 'For security purposes, you will be able to use this feature to withdraw cash 24 hours after your first set up. Please try again later.'

	//Reject Status
	String UIReject = 'Reject'
	String UILblAuthorisation = 'Authorisation failed'
	String UILblReferenceID = 'Reference ID'
	String UILblDateTime = 'Date & time'
	String UILblTransactionType = 'Transaction type'
	String UILblYourSecureVerification = 'Your Secure Verification authorisation was rejected.'
	String UILblNotInterestedInThis = 'Not Interested in This'

	//Expired Status
	String UILblYourSecureExpired = 'Your Secure Verification authorisation has expired. Please perform the transaction again.'

	static CommonPage commonPage = getPage(CommonPage)

	/**
	 * Select Product Name
	 */
	void selectProductName(String productName, FailureHandling flowControl = defaultFailureHandling) {
		'Select UI Dropdown Product Name'
		if(Mobile.waitForElementPresent(commonPage.imgArrowDownDropdown(UILblProductName, 1), GlobalVariable.G_timeoutShort)) {
			MobileDriver.tap(commonPage.imgArrowDownDropdown(UILblProductName, 1), GlobalVariable.G_timeoutLong, flowControl)
			if(Mobile.waitForElementNotPresent(commonPage.lblDynamic(UIDone), GlobalVariable.G_timeoutShort)) {
				MobileDriver.tap(commonPage.imgArrowDownDropdown(UILblProductName, 1), GlobalVariable.G_timeoutLong, flowControl)
			}
			Mobile.delay(GlobalVariable.G_timeoutMiniShort)
			'Scroll To Product Name'
			Mobile.scrollToText(productName)
			'Select Product Name'
			MobileDriver.tap(commonPage.lblDynamic(productName), GlobalVariable.G_timeoutLong, flowControl)
			'Select Done Button'
			MobileDriver.tap(commonPage.lblDynamic(UIDone), GlobalVariable.G_timeoutLong, flowControl)
			Mobile.delay(1)
		}
	}
	
	/**
	 * Select ID Type For ASNB member
	 */
	void selectIdTypeForASNBMember(String type, FailureHandling flowControl = defaultFailureHandling) {
		'Select UI Dropdown ID Type For ASNB member'
		if(Mobile.waitForElementPresent(commonPage.imgArrowDownDropdown(UILblIdTypeForASNBMember, 1), GlobalVariable.G_timeoutShort)) {
			MobileDriver.tap(commonPage.imgArrowDownDropdown(UILblIdTypeForASNBMember, 1), GlobalVariable.G_timeoutLong, flowControl)
			if(Mobile.waitForElementNotPresent(commonPage.lblDynamic(UIDone), GlobalVariable.G_timeoutShort)) {
				MobileDriver.tap(commonPage.imgArrowDownDropdown(UILblIdTypeForASNBMember, 1), GlobalVariable.G_timeoutLong, flowControl)
			}
			Mobile.delay(GlobalVariable.G_timeoutMiniShort)
			'Scroll To Product Name'
			Mobile.scrollToText(type)
			'Select Product Name'
			MobileDriver.tap(commonPage.lblDynamic(type), GlobalVariable.G_timeoutLong, flowControl)
			'Select Done Button'
			MobileDriver.tap(commonPage.lblDynamic(UIDone), GlobalVariable.G_timeoutLong, flowControl)
			Mobile.delay(1)
		}
	}
	
	/**
	 * Enter ID number for ASNB member
	 */
	void enterIdNumberForASNBMember(String number, FailureHandling flowControl = defaultFailureHandling) {
		MobileDriver.setText(commonPage.txtEditNo(1), number, GlobalVariable.G_timeoutLong, flowControl)
		Mobile.delay(GlobalVariable.G_timeoutMiniShort)
	}
	
	/**
	 * Enter Membership number
	 */
	void enterMembershipNumber(String number, FailureHandling flowControl = defaultFailureHandling) {
		MobileDriver.setText(commonPage.txtEditNo(2), number, GlobalVariable.G_timeoutLong, flowControl)
		Mobile.delay(GlobalVariable.G_timeoutMiniShort)
	}
	
	/**
	 * Select Relationship
	 */
	void selectRelationship(String relationship, FailureHandling flowControl = defaultFailureHandling) {
		'Select relationship'
		Mobile.scrollToText(UILblPurposeOfTransfer)
		if(Mobile.waitForElementPresent(commonPage.imgArrowDownDropdown(UILblRelationship, 1), GlobalVariable.G_timeoutShort)) {
			MobileDriver.tap(commonPage.imgArrowDownDropdown(UILblRelationship, 1), GlobalVariable.G_timeoutLong, flowControl)
			if(Mobile.waitForElementNotPresent(commonPage.lblDynamic(UIDone), GlobalVariable.G_timeoutShort)) {
				MobileDriver.tap(commonPage.imgArrowDownDropdown(UILblRelationship, 1), GlobalVariable.G_timeoutLong, flowControl)
			}
			Mobile.delay(GlobalVariable.G_timeoutMiniShort)
			'Scroll To relationship'
			Mobile.scrollToText(relationship)
			'Select relationship'
			MobileDriver.tap(commonPage.lblDynamic(relationship), GlobalVariable.G_timeoutLong, flowControl)
			'Select Done Button'
			MobileDriver.tap(commonPage.lblDynamic(UIDone), GlobalVariable.G_timeoutLong, flowControl)
			Mobile.delay(1)
		}
	}
	
	/**
	 * Select Purpose Of Transfer
	 */
	void selectPurposeOfTransfer(String purpose, FailureHandling flowControl = defaultFailureHandling) {
		'Select relationship'
		Mobile.scrollToText(UILblPurposeOfTransfer)
		if(Mobile.waitForElementPresent(commonPage.imgArrowDownDropdown(UILblPurposeOfTransfer, 1), GlobalVariable.G_timeoutShort)) {
			MobileDriver.tap(commonPage.imgArrowDownDropdown(UILblPurposeOfTransfer, 1), GlobalVariable.G_timeoutLong, flowControl)
			if(Mobile.waitForElementNotPresent(commonPage.lblDynamic(UIDone), GlobalVariable.G_timeoutShort)) {
				MobileDriver.tap(commonPage.imgArrowDownDropdown(UILblPurposeOfTransfer, 1), GlobalVariable.G_timeoutLong, flowControl)
			}
			Mobile.delay(GlobalVariable.G_timeoutMiniShort)
			'Scroll To relationship'
			Mobile.scrollToText(purpose)
			'Select relationship'
			MobileDriver.tap(commonPage.lblDynamic(purpose), GlobalVariable.G_timeoutLong, flowControl)
			'Select Done Button'
			MobileDriver.tap(commonPage.lblDynamic(UIDone), GlobalVariable.G_timeoutLong, flowControl)
			Mobile.delay(1)
		}
	}
	
	/**
	 * Tap Continue Button
	 */
	void tapContinueButton(int timeout = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		Mobile.tap(commonPage.lblDynamic(UITextContinue), timeout, flowControl)
	}
	
	/**
	 * Tab to agree ASBN consent
	 */
	void tapAgreeASNBConsent(int timeout = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		Mobile.scrollToText(UITextAgreeASNBConsent)
		Mobile.tap(commonPage.imgNoDynamic(3), timeout, flowControl)
		MobileDriver.delay(2)
	}

	/**
	 * Tap Transfer Now
	 */
	void tapTransferNow(int timeout = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		Mobile.tap(commonPage.lblDynamic(UIBtnTextTransferNow), timeout, flowControl)
	}
	
	/**
	 * Verify ASNB Consent displayed correctly
	 */
	void verifyASNBConsentDisplayed(int timeout = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		Mobile.scrollToText(UITextAgreeASNBConsent)
		MobileDriver.delay(1)
		'Verify ASNB Consent line 1 displayed correctly'
		Mobile.verifyElementExist(commonPage.lblDynamic(UITextASNBConsentLine1), timeout, flowControl)
		'Verify ASNB Consent line 2 displayed correctly'
		Mobile.verifyElementExist(commonPage.lblDynamic(UITextASNBConsentLine2), timeout, flowControl)
		'Verify ASNB Consent line 3 displayed correctly'
		Mobile.verifyElementExist(commonPage.lblDynamic(UITextASNBConsentLine3), timeout, flowControl)
		'Verify ASNB Consent line 4 displayed correctly'
		Mobile.verifyElementExist(commonPage.lblDynamic(UITextASNBConsentLine4), timeout, flowControl)
	}
	
	void verifyASNBConsentErrorDisplayed(int timeout = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		Mobile.verifyElementExist(commonPage.lblDynamic(UITextASNBErrorPopup), timeout, flowControl)
	}
	
	void verifyHomeBalanceIsCorrectAfterASNBTrasfer(String balanceBefore, String balanceAfter, String amountTransfer, int timeout = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		String stringBalanceBefore = balanceBefore.replace("RM ", "").replace(",", "").trim()
		String stringBalanceAfter = balanceAfter.replace("RM ", "").replace(",", "").trim()
		String stringAmountTransfer = amountTransfer.replace(",", "").trim()
		
		// Parse to float
		Float beforeValue = Float.parseFloat(stringBalanceBefore)
		Float afterValue = Float.parseFloat(stringBalanceAfter)
		Float transferValue = Float.parseFloat(stringAmountTransfer)
		
		//NOTE: 1RM is the fee for ASNB transfer
		Float expectedValue = afterValue + transferValue + 1 
		
		Mobile.verifyEqual(beforeValue, expectedValue)
	}
	
	void verifyHomeBalanceIsCorrectAfterNoASNBTrasfer(String balanceBefore, String balanceAfter, int timeout = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		Mobile.verifyEqual(balanceBefore, balanceAfter)
	}

	/**
	 * Verify UI Debiting Account
	 */
	void verifyUIDebitingAccount(FailureHandling flowControl = defaultFailureHandling) {
		Mobile.delay(GlobalVariable.G_timeoutMiniShort)
		'Verify UI Label Select Account'
		Mobile.verifyElementExist(commonPage.lblDynamic(UILblSelectAccount), GlobalVariable.G_timeoutMedium)
		'Verify UI Label Select Debiting Account'
		Mobile.verifyElementExist(commonPage.lblDynamic(UILblSelectDebitingAccount), GlobalVariable.G_timeoutMedium)
		Mobile.takeScreenshot()
	}
	/**
	 * Select Debiting Account
	 */
	void selectDebitingAccount(String debitingAccountName, FailureHandling flowControl = defaultFailureHandling) {
		Mobile.delay(GlobalVariable.G_timeoutMiniShort)
		'Check Debiting Account Name'
		commonPage.swipe(500, 1500, 500, 1230)
		if(Mobile.waitForElementPresent(commonPage.lblDynamic(debitingAccountName), GlobalVariable.G_timeoutMedium)) {
			'Select Debiting Account Name'
			Mobile.tap(commonPage.lblDynamic(debitingAccountName), GlobalVariable.G_timeoutMedium, flowControl)
			'Select Continue Button'
			Mobile.tap(commonPage.lblDynamic(UITextContinue), GlobalVariable.G_timeoutMedium, flowControl)
		}else {
			'Swipe Up'
			commonPage.swipe(500, 1500, 500, 500)
			Mobile.tap(commonPage.lblDynamic(debitingAccountName), GlobalVariable.G_timeoutMedium, flowControl)
		}
		Mobile.takeScreenshot()
	}
	/**
	 * Verify UI Transfer Overseas
	 */
	void verifyUITransferOverseasPage(FailureHandling flowControl = defaultFailureHandling) {
		Mobile.delay(GlobalVariable.G_timeoutMiniShort)
		'Verify UI Label Transfer Overseas'
		Mobile.verifyElementExist(commonPage.lblDynamic(UILblTransferOverseas), GlobalVariable.G_timeoutMedium)
		'Verify UI Label Enter Amount'
		Mobile.verifyElementExist(commonPage.lblDynamic(UILblEnterAmount), GlobalVariable.G_timeoutMedium)
		'Verify UI Label Convert To'
		Mobile.verifyElementExist(commonPage.lblDynamic(UILblConvertTo), GlobalVariable.G_timeoutMedium)
		'Verify UI Label Type Currency'
		Mobile.verifyElementExist(commonPage.lblDynamic(UILblTypeCurrency), GlobalVariable.G_timeoutMedium)
		'Select UI Label Type Currency'
		Mobile.tap(commonPage.lblDynamic(UILblTypeCurrency), GlobalVariable.G_timeoutMedium, flowControl)
		Mobile.takeScreenshot()
		'Verify UI Label Done'
		Mobile.verifyElementExist(commonPage.lblDynamic(UIDone), GlobalVariable.G_timeoutMedium)
		'Verify UI Label Cancel'
		Mobile.verifyElementExist(commonPage.lblDynamic(UILblCancel), GlobalVariable.G_timeoutMedium)
		'Verify UI Label Type Currency'
		UILblTypeCurrency = 'JPY'
		Mobile.verifyElementExist(commonPage.lblDynamic(UILblTypeCurrency), GlobalVariable.G_timeoutMedium)
		UILblTypeCurrency = 'USD'
		Mobile.verifyElementExist(commonPage.lblDynamic(UILblTypeCurrency), GlobalVariable.G_timeoutMedium)
		'Select UI Label Done'
		Mobile.tap(commonPage.lblDynamic(UIDone), GlobalVariable.G_timeoutMedium, flowControl)
		Mobile.delay(GlobalVariable.G_timeoutMiniShort)
		Mobile.takeScreenshot()
	}
	/**
	 * Select Input Amount
	 */
	void selectInputAmount(FailureHandling flowControl = defaultFailureHandling) {
		'Select UI Label Input Amount'
		Mobile.tap(commonPage.lblDynamic(UILblInputAmount), GlobalVariable.G_timeoutMedium, flowControl)
	}
	/**
	 * Select Dropdown Convert To
	 */
	void selectDropdownConvertTo(String currency, FailureHandling flowControl = defaultFailureHandling) {
		Mobile.delay(GlobalVariable.G_timeoutMiniShort)
		'Select UI Dropdown Convert To'
		MobileDriver.tap(commonPage.imgSibling(UIDrdConvertTo), GlobalVariable.G_timeoutLong, flowControl)
		if(Mobile.waitForElementNotPresent(commonPage.lblDynamic(UIDone), GlobalVariable.G_timeoutMedium)) {
			MobileDriver.tap(commonPage.imgSibling(UIDrdConvertTo), GlobalVariable.G_timeoutLong, flowControl)
		}
		'Srcoll to currency'
		commonPage.selectDropdownScrollOneLine(currency, GlobalVariable.G_timeoutLong, flowControl)
		'Select Done Button'
		MobileDriver.tap(commonPage.lblDynamic(UIDone), GlobalVariable.G_timeoutLong, flowControl)
		Mobile.delay(GlobalVariable.G_timeoutMiniShort)
		Mobile.takeScreenshot()
	}
	/**
	 * Verify UI Foreign Telegraphic Transfer
	 */
	void verifyUIForeignTelegraphicTransfer(FailureHandling flowControl = defaultFailureHandling) {
		Mobile.delay(GlobalVariable.G_timeoutMiniShort)
		'Verify UI Label Indicative Exchange Rate'
		Mobile.verifyElementExist(commonPage.lblDynamic(UILblIndicativeExchangeRate), GlobalVariable.G_timeoutMedium)
		'Verify UI Label Daily Transfer Limit'
		Mobile.verifyElementExist(commonPage.lblDynamic(UILblDailyTransferLimit), GlobalVariable.G_timeoutMedium)
		'Verify UI Label Service Fee'
		Mobile.verifyElementExist(commonPage.lblDynamic(UILblServiceFee), GlobalVariable.G_timeoutMedium)
		'Verify UI Label Agent Beneficiary Bank Fee'
		Mobile.verifyElementExist(commonPage.lblDynamic(UILblAgentBeneficiaryBankFee), GlobalVariable.G_timeoutMedium)
		'Verify UI Label Receive Method'
		Mobile.verifyElementExist(commonPage.lblDynamic(UILblReceiveMethod), GlobalVariable.G_timeoutMedium)
		'Verify UI Label Transfer Duration'
		Mobile.verifyElementExist(commonPage.lblDynamic(UILblTransferDuration), GlobalVariable.G_timeoutMedium)
		'Select Daily Transfer Limit Info'
		MobileDriver.tap(commonPage.imgNoDynamic(2), , GlobalVariable.G_timeoutMedium, flowControl)
		'Verify UI Label Daily Transfer Limit Info'
		Mobile.verifyElementExist(commonPage.lblDynamic(UILblDailyTransferLimitInfo), GlobalVariable.G_timeoutMedium)
		Mobile.takeScreenshot()
		'Select Close Button'
		MobileDriver.tap(commonPage.imgNoDynamic(1), , GlobalVariable.G_timeoutMedium, flowControl)
		'Select UI Label Agent Beneficiary Bank Fee Info'
		MobileDriver.tap(commonPage.imgNoDynamic(3), , GlobalVariable.G_timeoutMedium, flowControl)
		'Verify UI Label Agent Beneficiary Bank Fee Info Info'
		Mobile.verifyElementExist(commonPage.lblDynamic(UILblAgentBeneficiaryBankFeeInfo), GlobalVariable.G_timeoutMedium)
		Mobile.takeScreenshot()
		'Select Close Button'
		MobileDriver.tap(commonPage.imgNoDynamic(1), , GlobalVariable.G_timeoutMedium, flowControl)
		'Select UI Label Transfer Duration Info'
		MobileDriver.tap(commonPage.imgNoDynamic(4), , GlobalVariable.G_timeoutMedium, flowControl)
		'Verify UI Label Transfer Duration Info'
		Mobile.verifyElementExist(commonPage.lblDynamic(UILblTransferDurationInfo), GlobalVariable.G_timeoutMedium)
		Mobile.takeScreenshot()
		'Select Close Button'
		MobileDriver.tap(commonPage.imgNoDynamic(1), , GlobalVariable.G_timeoutMedium, flowControl)
		Mobile.takeScreenshot()
	}

	/**
	 * Select Foreign Telegraphic Transfer
	 */
	void selectForeignTelegraphicTransfer(String typeTransfer, FailureHandling flowControl = defaultFailureHandling) {
		Mobile.delay(GlobalVariable.G_timeoutMiniShort)
		Mobile.takeScreenshot()
		'Select FTT Radio Button'
		MobileDriver.tap(commonPage.lblContainDynamic(typeTransfer), , GlobalVariable.G_timeoutMedium, flowControl);
		'Select Transfer RM Button'
		String txtTranserRM = 'Transfer RM'
		Mobile.tap(commonPage.lblContainDynamic(txtTranserRM), GlobalVariable.G_timeoutMedium, flowControl)
		if(Mobile.waitForElementPresent(commonPage.lblDynamic(UILblProceed), GlobalVariable.G_timeoutMedium)) {
			'Select UI Label Proceed'
			MobileDriver.tap(commonPage.lblDynamic(UILblProceed), GlobalVariable.G_timeoutLong, flowControl)
		}
	}
	/**
	 * Verify UI Your Details
	 */
	void verifyUIYourDetails(FailureHandling flowControl = defaultFailureHandling) {
		Mobile.delay(GlobalVariable.G_timeoutMiniShort)
		'Verify UI Label Page Step 1 Of 4'
		Mobile.verifyElementExist(commonPage.lblDynamic(UIPageStep1Of4), GlobalVariable.G_timeoutMedium)
		'Verify UI Label Foreign Telegraphic Transfer'
		Mobile.verifyElementExist(commonPage.lblDynamic(UILblForeignTelegraphicTransfer), GlobalVariable.G_timeoutMedium)
		'Verify UI Label Malaysian'
		Mobile.verifyElementExist(commonPage.lblDynamic(UILblPleaseFillInYourDetails), GlobalVariable.G_timeoutMedium)
		'Verify UI Label Malaysian'
		Mobile.verifyElementExist(commonPage.lblDynamic(UILblNationality), GlobalVariable.G_timeoutMedium)
		'Verify UI Label Malaysian'
		Mobile.verifyElementExist(commonPage.lblDynamic(UILblMalaysian), GlobalVariable.G_timeoutMedium)
		'Verify UI Label Non Malaysian'
		Mobile.verifyElementExist(commonPage.lblDynamic(UILblNonMalaysian), GlobalVariable.G_timeoutMedium)
		'Verify UI Label Address Line 1'
		Mobile.verifyElementExist(commonPage.lblDynamic(UILblAddressLine1), GlobalVariable.G_timeoutMedium)
		'Verify UI Label Address Line 2'
		Mobile.verifyElementExist(commonPage.lblDynamic(UILblAddressLine2), GlobalVariable.G_timeoutMedium)
		'Verify UI Label Post Code'
		Mobile.verifyElementExist(commonPage.lblDynamic(UILblPostCode), GlobalVariable.G_timeoutMedium)
		'Verify UI Label Country'
		Mobile.scrollToText(UILblCountry)
		Mobile.verifyElementExist(commonPage.lblDynamic(UILblCountry), GlobalVariable.G_timeoutMedium)
		Mobile.scrollToText(UILblForeignTelegraphicTransfer)
	}
	/**
	 * Enter Your Details
	 */
	void enterYourDetails(FailureHandling flowControl = defaultFailureHandling) {
		Mobile.delay(GlobalVariable.G_timeoutMiniShort)
		'Select UI Label Non Malaysian'
		MobileDriver.tap(commonPage.lblContainDynamic(UILblNonMalaysian), GlobalVariable.G_timeoutMedium, flowControl);
		Mobile.takeScreenshot()
		'Select UI Label Continue'
		Mobile.tap(commonPage.lblDynamic(UITextContinue), GlobalVariable.G_timeoutMedium, flowControl)
	}
	/**
	 * Verify UI Receipient's Bank Details & Receipient's details
	 */
	void verifyUIReceipientDetails(FailureHandling flowControl = defaultFailureHandling) {
		Mobile.delay(GlobalVariable.G_timeoutMiniShort)
		'Verify UI Label Recipient Bank Details'
		Mobile.verifyElementExist(commonPage.lblDynamic(UILblRecipientBankDetails), GlobalVariable.G_timeoutMedium)
		'Verify UI Label Bank Name Bank Code And Address'
		Mobile.verifyElementExist(commonPage.lblDynamic(UILblBankNameBankCodeAndAddress), GlobalVariable.G_timeoutMedium)
		'Verify UI Label Account Number'
		Mobile.verifyElementExist(commonPage.lblDynamic(UILblAccountNumber), GlobalVariable.G_timeoutMedium)
		'Verify UI Label IC Passport Number'
		Mobile.verifyElementExist(commonPage.lblDynamic(UILblICPassportNumber), GlobalVariable.G_timeoutMedium)
		'Verify UI Label Nationality And Address'
		Mobile.verifyElementExist(commonPage.lblDynamic(UILblNationalityAndAddress), GlobalVariable.G_timeoutMedium)
		'Verify UI Label Full Name'
		Mobile.verifyElementExist(commonPage.lblDynamic(UILblFullName), GlobalVariable.G_timeoutMedium)
		'Verify UI Label Phone Number'
		Mobile.verifyElementExist(commonPage.lblDynamic(UILblPhoneNumber), GlobalVariable.G_timeoutMedium)
		Mobile.takeScreenshot()
	}
	/**
	 * Receipient's Bank Details & Receipient's details
	 */
	void enterReceipientDetails(FailureHandling flowControl = defaultFailureHandling) {
		Mobile.delay(GlobalVariable.G_timeoutMiniShort)
		'Select UI Label Continue'
		if(Mobile.waitForElementPresent(commonPage.lblDynamic(UITextContinue), GlobalVariable.G_timeoutMiniShort)) {
			MobileDriver.tap(commonPage.lblDynamic(UITextContinue), GlobalVariable.G_timeoutLong, flowControl)
		}
		'Select UI Label Continue'
		if(Mobile.waitForElementPresent(commonPage.lblDynamic(UILblConfirm), GlobalVariable.G_timeoutMiniShort)) {
			Mobile.tap(commonPage.lblDynamic(UILblConfirm), GlobalVariable.G_timeoutMedium, flowControl)
		}
		Mobile.takeScreenshot()
	}
	/**
	 * Verify UI Recipient Bank Details
	 */
	void verifyUIRecipientBankDetails(FailureHandling flowControl = defaultFailureHandling) {
		Mobile.delay(GlobalVariable.G_timeoutMiniShort)
		'Verify UI Label Please Fill In Recipient Bank Details'
		Mobile.verifyElementExist(commonPage.lblDynamic(UILblPleaseFillInRecipientBankDetails), GlobalVariable.G_timeoutMedium)
		'Verify UI Label Branch Address'
		Mobile.verifyElementExist(commonPage.lblDynamic(UILblBranchAddress), GlobalVariable.G_timeoutMedium)
		'Verify UI Label City'
		Mobile.verifyElementExist(commonPage.lblDynamic(UILblCity), GlobalVariable.G_timeoutMedium)
		'Verify UI Label Branch Address Info'
		MobileDriver.tap(commonPage.imgNoDynamic(3), , GlobalVariable.G_timeoutMedium, flowControl)
		Mobile.verifyElementExist(commonPage.lblDynamic(UILblBranchAddressInfo), GlobalVariable.G_timeoutMedium)
		Mobile.takeScreenshot()
		'Select Close Button'
		MobileDriver.tap(commonPage.imgNoDynamic(1), , GlobalVariable.G_timeoutMedium, flowControl)
		Mobile.takeScreenshot()
	}
	/**
	 * Enter Recipient Bank Details
	 */
	void enterRecipientBankDetails(String bankName, String accountNumber,String bankAddress, String city, FailureHandling flowControl = defaultFailureHandling) {
		Mobile.delay(GlobalVariable.G_timeoutMiniShort)
		'Select UI Dropdown Country'
		MobileDriver.tap(commonPage.imgSibling(UIDrdBankName), GlobalVariable.G_timeoutLong, flowControl)
		if(Mobile.waitForElementNotPresent(commonPage.lblDynamic(UIDone), GlobalVariable.G_timeoutMedium)) {
			MobileDriver.tap(commonPage.imgSibling(UIDrdBankName), GlobalVariable.G_timeoutLong, flowControl)
		}
		'Scroll To Text Currency'
		Mobile.scrollToText(bankName)
		'Select Currency'
		MobileDriver.tap(commonPage.lblDynamic(bankName), GlobalVariable.G_timeoutLong, flowControl)
		'Select Done Button'
		MobileDriver.tap(commonPage.lblDynamic(UIDone), GlobalVariable.G_timeoutLong, flowControl)
		'Enter Account Number'
		MobileDriver.setText(commonPage.txtEditNo(1), accountNumber, GlobalVariable.G_timeoutLong, FailureHandling.STOP_ON_FAILURE)
		'Enter Bank Address'
		MobileDriver.setText(commonPage.txtEditNo(2), bankAddress, GlobalVariable.G_timeoutLong, FailureHandling.STOP_ON_FAILURE)
		'Enter City'
		MobileDriver.setText(commonPage.txtEditNo(3), city, GlobalVariable.G_timeoutLong, FailureHandling.STOP_ON_FAILURE)
		Mobile.takeScreenshot()
		'Select Continue Button'
		MobileDriver.tap(commonPage.lblDynamic(UITextContinue), GlobalVariable.G_timeoutLong, flowControl)
		if(Mobile.waitForElementPresent(commonPage.lblDynamic(UITextContinue), GlobalVariable.G_timeoutShort)) {
			MobileDriver.tap(commonPage.lblDynamic(UITextContinue), GlobalVariable.G_timeoutLong, flowControl)
		}
	}
	/**
	 * Verify UI Recipient Details
	 */
	void verifyUIRecipientDetails(FailureHandling flowControl = defaultFailureHandling) {
		Mobile.delay(GlobalVariable.G_timeoutMiniShort)
		'Verify UI Label Please Fill In Recipient Details'
		Mobile.verifyElementExist(commonPage.lblDynamic(UILblPleaseFillInRecipientDetails), GlobalVariable.G_timeoutMedium)
		'Verify UI Label Nationality'
		Mobile.verifyElementExist(commonPage.lblDynamic(UILblNationality), GlobalVariable.G_timeoutMedium)
		'Verify UI Label Malaysian'
		Mobile.verifyElementExist(commonPage.lblDynamic(UILblMalaysian), GlobalVariable.G_timeoutMedium)
		'Verify UI Label Non Malaysian'
		Mobile.verifyElementExist(commonPage.lblDynamic(UILblNonMalaysian), GlobalVariable.G_timeoutMedium)
		'Verify UI Label Name'
		Mobile.verifyElementExist(commonPage.lblDynamic(UILblName), GlobalVariable.G_timeoutMedium)
		'Verify UI Label IC Passport Number Biz Registration No'
		Mobile.verifyElementExist(commonPage.lblDynamic(UILblICPassportNumberBizRegistrationNo), GlobalVariable.G_timeoutMedium)
		'Verify UI Label Address Line 1'
		Mobile.verifyElementExist(commonPage.lblDynamic(UILblAddressLine1), GlobalVariable.G_timeoutMedium)
		'Verify UI Label Address Line 2'
		Mobile.verifyElementExist(commonPage.lblDynamic(UILblAddressLine2), GlobalVariable.G_timeoutMedium)
		'Verify UI Label Mobile Number'
		commonPage.swipe(500, 1500, 500, 500)
		Mobile.verifyElementExist(commonPage.lblDynamic(UILblMobileNumber), GlobalVariable.G_timeoutMedium)
		'Verify UI Label Email Address'
		commonPage.swipe(500, 1500, 500, 500)
		Mobile.verifyElementExist(commonPage.lblDynamic(UILblEmailAddress), GlobalVariable.G_timeoutMedium)
		commonPage.swipe(500, 500, 500, 1500)
		Mobile.takeScreenshot()
	}
	/**
	 * Enter Recipient Details
	 */
	void enterRecipientDetails(String nationality, String name, String passportNumber,String addressLine1, String addressLine2, String country, String mobileNumber, String emailAddress, FailureHandling flowControl = defaultFailureHandling) {
		Mobile.delay(GlobalVariable.G_timeoutMiniShort)
		'Select Nationality'
		MobileDriver.tap(commonPage.lblDynamic(nationality), GlobalVariable.G_timeoutLong, flowControl)
		'Enter Account Number'
		MobileDriver.setText(commonPage.txtEditNo(1), name, GlobalVariable.G_timeoutLong, FailureHandling.STOP_ON_FAILURE)
		'Enter Passport Number'
		MobileDriver.setText(commonPage.txtEditNo(2), passportNumber, GlobalVariable.G_timeoutLong, FailureHandling.STOP_ON_FAILURE)
		'Enter Address Line 1'
		MobileDriver.setText(commonPage.txtEditNo(3), addressLine1, GlobalVariable.G_timeoutLong, FailureHandling.STOP_ON_FAILURE)
		'Enter Address Line 2'
		MobileDriver.setText(commonPage.txtEditNo(4), addressLine2, GlobalVariable.G_timeoutLong, FailureHandling.STOP_ON_FAILURE)
		'Scroll To Continue Button'
		commonPage.swipe(500, 1500, 500, 500)
		'Select UI Dropdown Country'
		MobileDriver.tap(commonPage.imgSibling(UIDrdCountry), GlobalVariable.G_timeoutLong, flowControl)
		if(Mobile.waitForElementNotPresent(commonPage.lblDynamic(UILblSelectCountry), GlobalVariable.G_timeoutShort)) {
			MobileDriver.tap(commonPage.imgSibling(UIDrdCountry), GlobalVariable.G_timeoutLong, flowControl)
		}
		Mobile.delay(GlobalVariable.G_timeoutMiniShort)
		'Select Search Button'
		MobileDriver.tap(commonPage.imgNoDynamic(2), , GlobalVariable.G_timeoutMedium, flowControl);
		'Input Country Name'
		MobileDriver.setText(commonPage.txtEditNo(1), country, GlobalVariable.G_timeoutLong, flowControl)
		Mobile.delay(GlobalVariable.G_timeoutMiniShort)
		'Select Country Name'
		MobileDriver.tap(commonPage.imgNoDynamic(3), , GlobalVariable.G_timeoutMedium, flowControl);
		if(Mobile.waitForElementPresent(commonPage.imgNoDynamic(3), GlobalVariable.G_timeoutMedium)) {
			MobileDriver.tap(commonPage.imgNoDynamic(3), , GlobalVariable.G_timeoutMedium, flowControl);
		}
		'Enter Account Number'
		MobileDriver.setText(commonPage.txtEditNo(4), mobileNumber, GlobalVariable.G_timeoutLong, FailureHandling.STOP_ON_FAILURE)
		'Enter Bank Address'
		MobileDriver.setText(commonPage.txtEditNo(5), emailAddress, GlobalVariable.G_timeoutLong, FailureHandling.STOP_ON_FAILURE)
		Mobile.takeScreenshot()
		'Select Continue Button'
		MobileDriver.tap(commonPage.lblDynamic(UITextContinue), GlobalVariable.G_timeoutLong, flowControl)
		if(Mobile.waitForElementPresent(commonPage.lblDynamic(UITextContinue), GlobalVariable.G_timeoutShort)) {
			MobileDriver.tap(commonPage.lblDynamic(UITextContinue), GlobalVariable.G_timeoutLong, flowControl)
		}
	}
	/**
	 * Verify UI Recipient Details
	 */
	void verifyUITransferDetails(FailureHandling flowControl = defaultFailureHandling) {
		Mobile.delay(GlobalVariable.G_timeoutMiniShort)
		'Verify UI Label Please Fill In Transfer Details'
		Mobile.verifyElementExist(commonPage.lblDynamic(UILblPleaseFillInTransferDetails), GlobalVariable.G_timeoutMedium)
		'Verify UI Label Purpose Of Transfer'
		Mobile.verifyElementExist(commonPage.lblDynamic(UILblPurposeOfTransfer), GlobalVariable.G_timeoutMedium)
		'Verify UI Label Additional Details'
		Mobile.verifyElementExist(commonPage.lblDynamic(UILblAdditionalDetails), GlobalVariable.G_timeoutMedium)
		'Select UI Label Agent Beneficiary Bank Fee Info'
		MobileDriver.tap(commonPage.imgNoDynamic(3), , GlobalVariable.G_timeoutMedium, flowControl)
		Mobile.takeScreenshot()
		'Select Close Button'
		MobileDriver.tap(commonPage.imgNoDynamic(1), , GlobalVariable.G_timeoutMedium, flowControl)
		Mobile.takeScreenshot()
	}
	/**
	 * Enter Transfer Details
	 */
	void enterTransferDetails(String purposeOfTransfer, String subPurpose, String relationship, String additionalDetails, FailureHandling flowControl = defaultFailureHandling) {
		Mobile.delay(GlobalVariable.G_timeoutMiniShort)
		'Select UI Dropdown Purpose Of Transfer'
		MobileDriver.tap(commonPage.imgSibling(UIDrdPurposeOfTransfer), GlobalVariable.G_timeoutLong, flowControl)
		if(Mobile.waitForElementNotPresent(commonPage.lblDynamic(UIDone), GlobalVariable.G_timeoutShort)) {
			MobileDriver.tap(commonPage.imgSibling(UIDrdPurposeOfTransfer), GlobalVariable.G_timeoutLong, flowControl)
		}
		Mobile.delay(GlobalVariable.G_timeoutMiniShort)
		'Scroll To Text Purpose Of Transfer'
		Mobile.scrollToText(purposeOfTransfer)
		'Select Purpose Of Transfer'
		MobileDriver.tap(commonPage.lblDynamic(purposeOfTransfer), GlobalVariable.G_timeoutLong, flowControl)
		'Select Done Button'
		MobileDriver.tap(commonPage.lblDynamic(UIDone), GlobalVariable.G_timeoutLong, flowControl)
		'Select UI Dropdown Sub Purpose'
		if(Mobile.waitForElementPresent(commonPage.lblDynamic(UILblSubPurpose), GlobalVariable.G_timeoutShort)) {
			MobileDriver.tap(commonPage.imgSibling(UIDrdSubPurpose), GlobalVariable.G_timeoutLong, flowControl)
			if(Mobile.waitForElementNotPresent(commonPage.lblDynamic(UIDone), GlobalVariable.G_timeoutShort)) {
				MobileDriver.tap(commonPage.imgSibling(UIDrdSubPurpose), GlobalVariable.G_timeoutLong, flowControl)
			}
			Mobile.delay(GlobalVariable.G_timeoutMiniShort)
			'Scroll To Text Purpose Of Transfer'
			Mobile.scrollToText(subPurpose)
			'Select Purpose Of Transfer'
			MobileDriver.tap(commonPage.lblDynamic(subPurpose), GlobalVariable.G_timeoutLong, flowControl)
			'Select Done Button'
			MobileDriver.tap(commonPage.lblDynamic(UIDone), GlobalVariable.G_timeoutLong, flowControl)
		}
		'Select UI Drodown Relationship'
		if(Mobile.waitForElementPresent(commonPage.lblDynamic(UILblRelationshipStatus), GlobalVariable.G_timeoutShort)) {
			MobileDriver.tap(commonPage.imgSibling(UIDrdRelationship), GlobalVariable.G_timeoutLong, flowControl)
			if(Mobile.waitForElementNotPresent(commonPage.lblDynamic(UIDone), GlobalVariable.G_timeoutShort)) {
				MobileDriver.tap(commonPage.imgSibling(UIDrdRelationship), GlobalVariable.G_timeoutLong, flowControl)
			}
			Mobile.delay(GlobalVariable.G_timeoutMiniShort)
			'Scroll To Text Purpose Of Transfer'
			Mobile.scrollToText(relationship)
			'Select Purpose Of Transfer'
			MobileDriver.tap(commonPage.lblDynamic(relationship), GlobalVariable.G_timeoutLong, flowControl)
			'Select Done Button'
			MobileDriver.tap(commonPage.lblDynamic(UIDone), GlobalVariable.G_timeoutLong, flowControl)
		}
		'Select UI Label I Will Pay'
		MobileDriver.tap(commonPage.lblContainDynamic(UILblIWillPay), GlobalVariable.G_timeoutLong, flowControl)
		commonPage.swipe(500, 1500, 500, 500)
		'Enter Additional Details'
		MobileDriver.setText(commonPage.txtEditNo(1), additionalDetails, GlobalVariable.G_timeoutLong, FailureHandling.STOP_ON_FAILURE)
		Mobile.takeScreenshot()
		'Select Continue Button'
		Mobile.scrollToText(UITextContinue)
		MobileDriver.tap(commonPage.lblDynamic(UITextContinue), GlobalVariable.G_timeoutLong, flowControl)
		if(Mobile.waitForElementPresent(commonPage.lblDynamic(UITextContinue), GlobalVariable.G_timeoutShort)) {
			MobileDriver.tap(commonPage.lblDynamic(UITextContinue), GlobalVariable.G_timeoutLong, flowControl)
		}
	}
	/**
	 * Enter Confirmation
	 */
	void enterConfirmation(FailureHandling flowControl = defaultFailureHandling) {
		Mobile.delay(GlobalVariable.G_timeoutMiniShort)
		Mobile.takeScreenshot()
		'Select UI Label Confirm And Submit'
		Mobile.scrollToText(UILblConfirmAndSubmit)
		MobileDriver.tap(commonPage.lblDynamic(UILblConfirmAndSubmit), GlobalVariable.G_timeoutLong, flowControl)
		if(Mobile.waitForElementPresent(commonPage.lblDynamic(UILblConfirmAndSubmit), GlobalVariable.G_timeoutShort)) {
			MobileDriver.tap(commonPage.lblDynamic(UILblConfirmAndSubmit), GlobalVariable.G_timeoutLong, flowControl)
		}
	}
	/**
	 * Enter Terms And Conditions
	 */
	void enterTermsAndConditions(FailureHandling flowControl = defaultFailureHandling) {
		Mobile.delay(GlobalVariable.G_timeoutMiniShort)
		Mobile.takeScreenshot()
		'Select UI Label Agree And Confirm'
		Mobile.scrollToText(UILblAgreeAndConfirm)
		MobileDriver.tap(commonPage.lblDynamic(UILblAgreeAndConfirm), GlobalVariable.G_timeoutLong, flowControl)
		if(Mobile.waitForElementPresent(commonPage.lblDynamic(UILblAgreeAndConfirm), GlobalVariable.G_timeoutShort)) {
			MobileDriver.tap(commonPage.lblDynamic(UILblAgreeAndConfirm), GlobalVariable.G_timeoutLong, flowControl)
		}
	}
	/**
	 * Enter Transfer From
	 */
	void enterTransferFrom(FailureHandling flowControl = defaultFailureHandling) {
		Mobile.delay(GlobalVariable.G_timeoutMiniShort)
		Mobile.takeScreenshot()
		'Select Continue Button'
		Mobile.scrollToText(UILblTransferNow)
		if(Mobile.waitForElementPresent(commonPage.lblDynamic(UILblTransferNow), GlobalVariable.G_timeoutLong)) {
			MobileDriver.tap(commonPage.lblDynamic(UILblTransferNow), GlobalVariable.G_timeoutLong, flowControl)
			Mobile.delay(GlobalVariable.G_timeoutMiniShort)
			if(Mobile.waitForElementPresent(commonPage.lblDynamic(UILblTransferNow), GlobalVariable.G_timeoutShort)) {
				MobileDriver.tap(commonPage.lblDynamic(UILblTransferNow), GlobalVariable.G_timeoutLong, flowControl)
			}
		}
		Mobile.delay(GlobalVariable.G_timeoutShort)
	}
	/**
	 * Verify Transaction
	 */
	public void verifyTransaction(FailureHandling flowControl = defaultFailureHandling) {
		Mobile.delay(GlobalVariable.G_timeoutMiniShort)
		'Check UI Label Transfer Failed'
		if(Mobile.waitForElementPresent(commonPage.lblDynamic(UILblTransferFailed), GlobalVariable.G_timeoutShort)) {
			MobileDriver.tap(commonPage.lblDynamic(UILblTransferFailed), GlobalVariable.G_timeoutLong, flowControl)
		}
		Mobile.takeScreenshot()
	}
	/**
	 * Select Status
	 */
	public void selectStatus(String status, FailureHandling flowControl = defaultFailureHandling) {
		Mobile.delay(GlobalVariable.G_timeoutMiniShort)
		switch(status) {
			case 'Expired':
				MobileDriver.waitForElementPresent(commonPage.lblDynamic(UIDone), 60 + GlobalVariable.G_timeoutLong, flowControl)
				break
			case 'Reject':
				MobileDriver.tap(commonPage.lblDynamic(UIReject), 60 + GlobalVariable.G_timeoutLong, flowControl)
				break
			default:
				'Select Approve Button'
				if(Mobile.waitForElementPresent(commonPage.lblDynamic(UIApprove), GlobalVariable.G_timeoutLong)) {
					MobileDriver.tap(commonPage.lblDynamic(UIApprove), GlobalVariable.G_timeoutLong, flowControl)
					Mobile.delay(GlobalVariable.G_timeoutMiniShort)
					if(Mobile.waitForElementPresent(commonPage.lblDynamic(UIApprove), GlobalVariable.G_timeoutLong)) {
						MobileDriver.tap(commonPage.lblDynamic(UIApprove), GlobalVariable.G_timeoutLong, flowControl)
					}
				}
				info(status + ' is tapped.')
				break
		}
	}
	/**
	 * Verify Status
	 */
	public void verifyStatus(String status, FailureHandling flowControl = defaultFailureHandling) {
		switch(status) {
			case 'Expired':
				Mobile.delay(GlobalVariable.G_timeoutMiniShort)
				if(Mobile.waitForElementPresent(commonPage.lblDynamic(UIDone), GlobalVariable.G_timeoutLong)) {
					'Select Done Button'
					MobileDriver.tap(commonPage.lblDynamic(UIDone), GlobalVariable.G_timeoutLong, flowControl)
				}
				break
			case 'Reject':
				Mobile.delay(GlobalVariable.G_timeoutMiniShort)
				if(Mobile.waitForElementPresent(commonPage.lblDynamic(UIDone), GlobalVariable.G_timeoutLong)) {
					'Select Done Button'
					MobileDriver.tap(commonPage.lblDynamic(UIDone), GlobalVariable.G_timeoutLong, flowControl)
				}
				break
			default:
				Mobile.delay(GlobalVariable.G_timeoutMiniShort)
				if(Mobile.waitForElementPresent(commonPage.lblDynamic(UIDone), GlobalVariable.G_timeoutLong)) {
					'Select Done Button'
					MobileDriver.tap(commonPage.lblDynamic(UIDone), GlobalVariable.G_timeoutLong, flowControl)
				}
				break
		}
	}
}
