package com.vcb.mobile.internal.pages.base.transferpage
import com.vcb.mobile.internal.pages.base.accountpage.AccountDetailPage

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.model.FailureHandling.CONTINUE_ON_FAILURE
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import java.text.DecimalFormat

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
import com.vcb.mobile.internal.pages.base.BasePage
import com.vcb.mobile.internal.pages.base.CommonPage
import com.vcb.utils.DateUtils
import com.vcb.utils.StringUtils
import com.vcb.assertions.BaseAssert
import com.vcb.common.MobileDriver
import internal.GlobalVariable
import com.vcb.mobile.internal.pages.base.ShareReceiptPage

public abstract class EnterOverseasPage extends BasePage{
	abstract TestObject drpIDType()
	abstract TestObject recipientDetailEditBtn()
	abstract TestObject lblParentViewGrSiblingDynamic(String text, int index=2)
	abstract TestObject lblSiblingGroupDynamic(String text, int index=1)
	abstract TestObject imgLabelDynamic(String text)
	
	AccountDetailPage accountDetailPage = BasePage.getPage(AccountDetailPage)
	ShareReceiptPage shareReceiptPage = BasePage.getPage(ShareReceiptPage)
	
	//UI text
	String UITextContinue = 'Continue'
	String UIBtnTextTransferNow = 'Transfer Now'
	String UIlblEnterAmount = 'Enter amount'
	String UITransaction = 'Transaction Authorisation Code'
	String UIEnterRecipientReference = 'Enter recipient reference'
	String UILblInputAmount = '0.00'
	String UIMaxLimit = '999,999.99'
	String UIRecipientInValid = 'Please remove invalid special characters.'
	String UIDrdConvertTo = 'Please select'
	String UIDone = 'Done'
	String UITextTranserRM = 'Transfer RM'
	String UILblMalaysian = 'Malaysian'
	String UILblNonMalaysian = 'Non-Malaysian'
	String UILblNationalID = 'National ID'
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
	String UILblConfirmAndSubmit = 'Confirm and Submit'
	String UILblAgreeAndConfirm = 'Agree and Confirm'
	String UILblAgree = "Agree"
	String UILblTransferNow = 'Transfer Now'
	String UILblAddFavorites = 'Add to Favourites'
	String UILblExchangeRate = 'Exchange Rate'
	String UILblProceed = 'Proceed'
	String UILblBakong = 'Bakong'
	String UILblVisaDirect = 'Visa Direct'
	String UILblEnterYourReceipientVisaCard = 'Enter your recipient`s Visa card number to view transfer details'
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
	String UILblServiceFee  = 'Service Fee'
	String UILblAgentBankFee= 'Agent/Beneficiary bank fee'
	String UILblAgentBeneficiaryBankFee =  'Agent/Beneficiary\nBank fee'
	String UILblReceiveMethod  = 'Receive method'
	String UILblTransferDuration = 'Transfer duration'
	String UILblDailyTransferLimitInfo = 'Cumulative limit per day for all Vcb Overseas transfer and Foreign Telegraphic transactions.'
	String UILblAgentBeneficiaryBankFeeInfo = 'You may pay the agent/beneficiary bank fee or let your recipient pay it, and the fee will be deducted from the transfer amount. The fee amount will be displayed on your transfer confirmation page later.'
	String UILblTransferDurationInfo = 'The transfer duration in number of working days.'
	String UILblForeignTelegraphicTransfer = 'Foreign Telegraphic Transfer'
	String UILblPleaseFillInYourDetails = 'Please fill in your details'
	String UILblNationality = 'Nationality'
	String UILblAddressLine1 = 'Address line 1'
	String UILblAddressLine2 = 'Address line 2'
	String UILblVisaDirectTextBox = 'e.g. 2233445566'
	String UILblPostCode = 'Postcode'
	String UIState = 'State'
	String UILblCountry = 'Country'
	String UIPageStep1Of4 = 'Step 1 of 4'
	String UILblRecipientBankDetails = 'Recipient\'s bank details:'
	String UILblBankNameBankCodeAndAddress = '●   Bank name, bank code and address'
	String UILblAccountNumber = '●   Account number'
	String UILblICPassportNumber = '●   IC/Passport number'
	String UILblNationalityAndAddress = '●   Nationality and address'
	String UILblEnterReceipientCardNumber = 'Recipient Card Number'
	String UILblRecipientCardDetails = 'Recipient\'s Details'
	String UILblCardNumber = '●   Card number'
	String UILblCardHolderlastname = '●   Cardholder\'s last name'
	String UILblCardHolderfirstname = '●   Cardholder\'s first name'
	String UILblOverseasBankName = '●   Oversea\'s bank name'
	String UILblFullName = '●   Full name'
	String UILblPhoneNumber = '●   Phone number'
	String UILblPleaseFillInRecipientBankDetails = 'Please fill in recipient\'s bank details'
	String UILblPleaseFillInRecipientCardDetails = 'Please fill in recipient\'s card details'
	String UILblRecipientCardNumber = 'Recipient card number'
	String UILblCardHolderLastName = 'Cardholder\'s last name'
	String UILblCardHolderFirstName = 'Cardholder\'s first name'
	String UILblRecipientOverseaBankName = 'Recipient oversea\'s bank name'
	String UILblBranchAddress = 'Branch address'
	String UILblCity = 'City'
	String UILblBranchAddressInfo = 'You can proceed to make the transfer as long as the address is identifiable'
	String UILblPleaseFillInRecipientDetails = 'Please fill in recipient\'s details'
	String UILblName = 'Name'
	String UILblICPassportNumberBizRegistrationNo = 'IC / Passport number / Biz registration No.'
	String UILblICPassportNumberInput = 'IC / Passport number'
	String UILblMobileNumber = 'Mobile number'
	String UILblEmailAddress = 'Email address (Optional)'
	String UILblPleaseFillInTransferDetails = 'Please fill in transfer details'
	String UILblPurposeOfTransfer = 'Purpose of transfer'
	String UILblAdditionalDetails = 'Additional details (Optional)'
	String UILblAdditionalInfo = 'Additional info'
	String UILblTransferAmount = 'Transfer amount'
	String UILblDateOfBirth = 'Date of birth'
	String UILblTransferDetails = "Transfer Details"
	String UILblEnter = "Enter"
	String UILblEdit = "Edit"

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

	//SST Variables
	String UILblIncludeSST = '(Includes 8% SST)'
	String UILblServiceFeeSSTInfo = "The service fee (inclusive of 8% SST) will be paid from your local accounts during the transfer details later."
	String UILblBankFeeSSTInfo = 'If you choose to pay this fee, the fee amount (inclusive of 8% SST) will be displayed on your confirmation page later.\n\nIf you choose to let your recipient pay this fee, the fee amount will be deducted from the transfer amount.'
	String UILblMOTBankFeeSSTInfo = 'No fee for transfers to beneficiary accounts in Vcb Singapore Limited (MSL) and Malayan Banking Berhad, Singapore Branch (MBS).However, the fee (inclusive of 8% SST) applies for transfers to beneficiary accounts in other banks in Singapore.'
	List<String> UILblSSTInfoList = [
		// Bank Fee SST Info (FTT/WU)
		'If you choose to pay this fee, the fee amount (inclusive of 8% SST) will be displayed on your confirmation page later.',
		'If you choose to let your recipient pay this fee, the fee amount will be deducted from the transfer amount.',
	]
	List<String> UILblSSTMOTInfoList = [
		// Bank Fee SST Info (MOT)
		'No fee for transfers to beneficiary accounts in Vcb Singapore Limited (MSL) and Malayan Banking Berhad, Singapore Branch (MBS).',
		'However, the fee (inclusive of 8% SST) applies for transfers to beneficiary accounts in other banks in Singapore.',
	]
	List<String> UILblSSTWUInfoList = [
		// Bank Fee SST Info (WU)
		'If an agent/beneficiary bank fee is applicable, the fee (inclusive of 8% SST) will be paid from your local accounts during the transfer details later.',
	]
	String UILblServiceSST= '8% SST on service fee'
	String UILblBankFeeSST= '8% SST on agent/beneficiary bank fee'
	

	static CommonPage commonPage = getPage(CommonPage)

	/**
	 * Confirm Transaction With Pin
	 */
	void confirmTransactionWithPin(FailureHandling flowControl = defaultFailureHandling) {
		commonPage.checkEnterPIN(GlobalVariable.G_timeoutShort, flowControl)
	}

	/**
	 * Select Favorite
	 */
	void selectFavorite(String favName, FailureHandling flowControl = defaultFailureHandling) {
		'Select Favorite'
		MobileDriver.takeScreenshot()
		Mobile.tap(commonPage.lblDynamic(favName), GlobalVariable.G_timeoutMedium, flowControl)
		Mobile.delay(GlobalVariable.G_timeoutMiniShort)
		MobileDriver.takeScreenshot()
	}

	/**
	 * Select Country Name In Overseas
	 */
	void selectCountryNameInOverseas(String moduleName, String countryName, FailureHandling flowControl = defaultFailureHandling) {
		'Select Module Name'
		MobileDriver.tapEleByTestObject(commonPage.lblDynamic(moduleName))
		Mobile.delay(GlobalVariable.G_timeoutMiniShort)
		MobileDriver.takeScreenshot()
		'Select Search Button'
		MobileDriver.tap(commonPage.imgNoDynamic(2), GlobalVariable.G_timeoutMedium, flowControl);
		'Input Country Name'
		if(waitForElementNotPresent(commonPage.txtEditNo(1),  GlobalVariable.G_timeoutMedium, FailureHandling.OPTIONAL)) {
			'Select Search Button'
			MobileDriver.tap(commonPage.imgNoDynamic(2), GlobalVariable.G_timeoutMedium, FailureHandling.OPTIONAL);
		}
		MobileDriver.setText(commonPage.txtEditNo(1), countryName, GlobalVariable.G_timeoutLong, flowControl)
		Mobile.delay(GlobalVariable.G_timeoutMiniShort)
		MobileDriver.takeScreenshot()
		'Select Country Name'
		MobileDriver.tap(commonPage.imgNoDynamic(3), GlobalVariable.G_timeoutMedium, flowControl);
		if(Mobile.waitForElementPresent(commonPage.imgNoDynamic(3), GlobalVariable.G_timeoutMedium,FailureHandling.OPTIONAL)) {
			MobileDriver.tap(commonPage.imgNoDynamic(3), GlobalVariable.G_timeoutMedium, FailureHandling.OPTIONAL);
		}
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
		MobileDriver.takeScreenshot()
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
		MobileDriver.takeScreenshot()
	}
	/**
	 * Verify UI Transfer Overseas
	 */
	void verifyUITransferOverseasPage(FailureHandling flowControl = defaultFailureHandling) {
		UILblTypeCurrency = 'RM'
		Mobile.delay(GlobalVariable.G_timeoutShort)
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
		MobileDriver.takeScreenshot()
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
		MobileDriver.takeScreenshot()
	}

	/**
	 * Verify UI Transfer Overseas
	 */
	void verifyUITransferOverseasPageVD(String currency, FailureHandling flowControl = defaultFailureHandling) {
		UILblTypeCurrency = 'RM'
		Mobile.delay(GlobalVariable.G_timeoutShort)
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
		Mobile.verifyElementExist(commonPage.lblDynamic(currency), GlobalVariable.G_timeoutMedium)
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
		'Select UI Dropdown Convert To'
		MobileDriver.tap(commonPage.imgSibling(UIDrdConvertTo), GlobalVariable.G_timeoutLong, flowControl)
		if(Mobile.waitForElementNotPresent(commonPage.lblDynamic(UIDone), GlobalVariable.G_timeoutShort)) {
			MobileDriver.tap(commonPage.imgSibling(UIDrdConvertTo), GlobalVariable.G_timeoutLong, flowControl)
		}
		'Srcoll to currency'
//		commonPage.selectDropdownScrollOneLine(currency, GlobalVariable.G_timeoutLong, flowControl)
		'Select Done Button'
		MobileDriver.tap(commonPage.lblDynamic(UIDone), GlobalVariable.G_timeoutLong, flowControl)
		Mobile.delay(GlobalVariable.G_timeoutMiniShort)
		MobileDriver.takeScreenshot()
	}
	/**
	 * Select Dropdown Country
	 */
	void selectDropdownCountry(String country, FailureHandling flowControl = defaultFailureHandling) {
		'Select UI Dropdown Convert To'
		MobileDriver.tap(commonPage.imgSibling(UIDrdConvertTo), GlobalVariable.G_timeoutLong, flowControl)
		if(Mobile.waitForElementNotPresent(commonPage.lblDynamic(UIDone), GlobalVariable.G_timeoutMedium)) {
			MobileDriver.tap(commonPage.imgSibling(UIDrdConvertTo), GlobalVariable.G_timeoutLong, flowControl)
		}
		'Srcoll to currency'
		commonPage.selectDropdownScrollOneLine(country, GlobalVariable.G_timeoutLong, flowControl)
		'Select Done Button'
		MobileDriver.tap(commonPage.lblDynamic(UIDone), GlobalVariable.G_timeoutLong, flowControl)
		Mobile.delay(GlobalVariable.G_timeoutMiniShort)
		Mobile.takeScreenshot()
	}
	/**
	 * Verify UI Foreign Telegraphic Transfer
	 */
	Map<String, String> verifyUIForeignTelegraphicTransfer(String amount, FailureHandling flowControl = defaultFailureHandling) {
		Map<String, String> fttInfo = [:]

		'Verify transfer amount'
		String amountWithRM = 'RM ' + String.format("%.2f", Double.parseDouble(amount)/100)
		String displayedAmount = MobileDriver.getText(lblParentViewGrSiblingDynamic(UILblForeignTelegraphicTransfer, 2), GlobalVariable.G_timeoutMedium, flowControl)
		BaseAssert.equals(amountWithRM, displayedAmount, 'Verify transfer amount', flowControl)

		String foreignAmount = MobileDriver.getText(lblParentViewGrSiblingDynamic(UILblForeignTelegraphicTransfer, 3), GlobalVariable.G_timeoutMedium, flowControl)
		fttInfo[UILblTransferAmount] = amountWithRM + '\n= ' + foreignAmount

		'Verify UI Label Indicative Exchange Rate'
		Mobile.verifyElementExist(commonPage.lblDynamic(UILblIndicativeExchangeRate), GlobalVariable.G_timeoutMedium)
		// TODO: get Exchange Rate and store to fttInfo, and transform 'RM 4.5250 ≈ USD 1' to 'RM 4.5250 = USD 1'

		'Verify UI Label Daily Transfer Limit'
		Mobile.verifyElementExist(commonPage.lblDynamic(UILblDailyTransferLimit), GlobalVariable.G_timeoutMedium)

		'Verify UI Label Service Fee'
		Mobile.verifyElementExist(commonPage.lblDynamic(UILblServiceFee), GlobalVariable.G_timeoutMedium)
		fttInfo[UILblServiceFee] = MobileDriver.getText(lblParentViewGrSiblingDynamic(UILblServiceFee), GlobalVariable.G_timeoutMedium, flowControl)

		'Verify UI Label Agent Beneficiary Bank Fee'
		Mobile.verifyElementExist(commonPage.lblDynamic(UILblAgentBeneficiaryBankFee), GlobalVariable.G_timeoutMedium)
		'Verify UI Label Receive Method'
		Mobile.verifyElementExist(commonPage.lblDynamic(UILblReceiveMethod), GlobalVariable.G_timeoutMedium)

		'Verify UI Label Transfer Duration'
		Mobile.verifyElementExist(commonPage.lblDynamic(UILblTransferDuration), GlobalVariable.G_timeoutMedium)
		fttInfo[UILblTransferDuration] = MobileDriver.getText(lblParentViewGrSiblingDynamic(UILblTransferDuration), GlobalVariable.G_timeoutMedium, flowControl)

		'Select Daily Transfer Limit Info'
		MobileDriver.tap(commonPage.imgNoDynamic(2), GlobalVariable.G_timeoutMedium, flowControl)
		'Verify UI Label Daily Transfer Limit Info'
		Mobile.verifyElementExist(commonPage.lblDynamic(UILblDailyTransferLimitInfo), GlobalVariable.G_timeoutMedium)
		MobileDriver.takeScreenshot()
		'Select Close Button'
		MobileDriver.tap(commonPage.imgNoDynamic(1), GlobalVariable.G_timeoutMedium, flowControl)
		'Select UI Label Agent Beneficiary Bank Fee Info'
		MobileDriver.tap(commonPage.imgNoDynamic(3), GlobalVariable.G_timeoutMedium, flowControl)
		'Verify UI Label Agent Beneficiary Bank Fee Info Info'
		Mobile.verifyElementExist(commonPage.lblDynamic(UILblAgentBeneficiaryBankFeeInfo), GlobalVariable.G_timeoutMedium)
		MobileDriver.takeScreenshot()
		'Select Close Button'
		MobileDriver.tap(commonPage.imgNoDynamic(1), GlobalVariable.G_timeoutMedium, flowControl)
		'Select UI Label Transfer Duration Info'
		MobileDriver.tap(commonPage.imgNoDynamic(4), GlobalVariable.G_timeoutMedium, flowControl)
		'Verify UI Label Transfer Duration Info'
		Mobile.verifyElementExist(commonPage.lblDynamic(UILblTransferDurationInfo), GlobalVariable.G_timeoutMedium)
		MobileDriver.takeScreenshot()
		'Select Close Button'
		MobileDriver.tap(commonPage.imgNoDynamic(1), GlobalVariable.G_timeoutMedium, flowControl)
		MobileDriver.takeScreenshot()

		return fttInfo
	}

	/**
	 * Select Visa Direct Transfer
	 */
	void selectVisaDirectTransfer(String typeTransfer, String visaDirectCard, String currency, FailureHandling flowControl = defaultFailureHandling) {
		Mobile.delay(GlobalVariable.G_timeoutMiniShort)
		Mobile.scrollToText(typeTransfer)
		for (int i = 0; i < 1; i++) {
			commonPage.swipeByW3CAction(500, 1500, 500, 800)
		}
		Mobile.takeScreenshot()
		'Select Continue Button'
		if(MobileDriver.waitForElementPresent(commonPage.lblContainDynamic(UITextContinue), GlobalVariable.G_timeoutMedium)) {
			MobileDriver.tap(commonPage.lblContainDynamic(UITextContinue), GlobalVariable.G_timeoutMedium, flowControl)
		}
		'Verify UI Label Visa Direct'
		Mobile.verifyElementExist(commonPage.lblDynamic(UILblEnterReceipientCardNumber), GlobalVariable.G_timeoutMedium)
		'Input Visa Direct Card Number'
		MobileDriver.tap(commonPage.lblContainDynamic(UILblVisaDirectTextBox), GlobalVariable.G_timeoutMedium, flowControl)
		Mobile.setText(findTestObject('Object Repository/Mobile/VCB/Android/Overseas Transfer Screen/txtVisaDrectTextBox'), '4761340007021117', 0)
		Mobile.hideKeyboard(FailureHandling.OPTIONAL)
		Mobile.takeScreenshot()
		'Select Enter'
		Mobile.scrollToText(typeTransfer)
		Mobile.takeScreenshot()
		MobileDriver.tap(commonPage.lblDynamic(UILblEnter), GlobalVariable.G_timeoutMedium, flowControl)
		Mobile.delay(GlobalVariable.G_timeoutMiniShort)
		'Select Type Transfer'
		Mobile.scrollToText(typeTransfer)
		for (int i = 0; i < 2; i++) {
			commonPage.swipeByW3CAction(500, 1500, 500, 800)
		}
		Mobile.delay(5)
		Mobile.takeScreenshot()
		Mobile.delay(20)
		Mobile.tap(findTestObject('Object Repository/Mobile/VCB/Android/Overseas Transfer Screen/txtVisa Direct'), 0)
		MobileDriver.tap(commonPage.lblDynamic(typeTransfer), GlobalVariable.G_timeoutMedium, flowControl)
		'Select Transfer RM Button'
		String txtTranserRM = 'Transfer RM '
		Mobile.tap(commonPage.lblContainDynamic(txtTranserRM), GlobalVariable.G_timeoutMedium, flowControl)
		if(Mobile.waitForElementPresent(commonPage.lblDynamic(UILblProceed), GlobalVariable.G_timeoutMedium)) {
			'Select UI Label Proceed'
			MobileDriver.tap(commonPage.lblDynamic(UILblProceed), GlobalVariable.G_timeoutLong, flowControl)
		}
	}

	/**
	 * Select BakongTransfer
	 */
	void selectTheBakongTransfer(String typeTransfer, FailureHandling flowControl = defaultFailureHandling) {
		Mobile.delay(GlobalVariable.G_timeoutMiniShort)
		Mobile.takeScreenshot()
		'Select Continue Button'
		if(MobileDriver.waitForElementPresent(commonPage.lblContainDynamic(UITextContinue), GlobalVariable.G_timeoutMedium)) {
			MobileDriver.tap(commonPage.lblContainDynamic(UITextContinue), GlobalVariable.G_timeoutMedium, flowControl)
		}
		'Select Type Transfer'
		MobileDriver.tap(commonPage.lblContainDynamic(typeTransfer), GlobalVariable.G_timeoutMedium, flowControl)
		'Select Transfer RM Button'
		String txtTranserRM = 'Transfer RM '
		Mobile.tap(commonPage.lblContainDynamic(txtTranserRM), GlobalVariable.G_timeoutMedium, flowControl)
		Mobile.delay(GlobalVariable.G_timeoutShort)
		if(Mobile.waitForElementPresent(commonPage.lblDynamic(UILblProceed), GlobalVariable.G_timeoutMedium)) {
			'Select UI Label Proceed'
			MobileDriver.tap(commonPage.lblDynamic(UILblProceed), GlobalVariable.G_timeoutLong, flowControl)
		}
	}

	/**
	 * Enter Bakong Number
	 */
	void enterBakongMobile(String mobileNumber, FailureHandling flowControl = defaultFailureHandling) {
		Mobile.delay(GlobalVariable.G_timeoutMiniShort)
		Mobile.takeScreenshot()
		'Select Continue Button'
		if(MobileDriver.waitForElementPresent(commonPage.lblContainDynamic(UITextContinue), GlobalVariable.G_timeoutMedium)) {
			MobileDriver.tap(commonPage.lblContainDynamic(UITextContinue), GlobalVariable.G_timeoutMedium, flowControl)
		}
		'Verify UI Label Bakong'
		Mobile.verifyElementExist(commonPage.lblDynamic(UILblBakong), GlobalVariable.G_timeoutMedium)
		'Enter Mobile Number'
		MobileDriver.setText(commonPage.txtEditNo(1), mobileNumber, GlobalVariable.G_timeoutLong, FailureHandling.STOP_ON_FAILURE)
		Mobile.hideKeyboard(FailureHandling.OPTIONAL)
		Mobile.takeScreenshot()
	}

	/**
	 * Select Fav Visa Direct
	 */
	void selectFavVisaDirectTransfer(String typeTransfer, FailureHandling flowControl = defaultFailureHandling) {
		Mobile.delay(GlobalVariable.G_timeoutMiniShort)
		Mobile.takeScreenshot()
		'Select Type Transfer'
		commonPage.scrollUpDynamic(500)
		Mobile.takeScreenshot()
		Mobile.delay(GlobalVariable.G_timeoutMedium)
		Mobile.tap(findTestObject('Object Repository/Mobile/VCB/Android/Overseas Transfer Screen/txtVisa Direct'), flowControl)
		MobileDriver.tap(commonPage.lblDynamic(typeTransfer), GlobalVariable.G_timeoutMedium, flowControl)
		'Select Transfer RM Button'
		String txtTranserRM = 'Transfer RM '
		Mobile.tap(commonPage.lblContainDynamic(txtTranserRM), GlobalVariable.G_timeoutMedium, flowControl)
		Mobile.delay(GlobalVariable.G_timeoutMedium)
		Mobile.takeScreenshot()
		if(Mobile.waitForElementPresent(commonPage.lblDynamic(UILblProceed), GlobalVariable.G_timeoutMedium)) {
			'Select UI Label Proceed'
			MobileDriver.tap(commonPage.lblDynamic(UILblProceed), GlobalVariable.G_timeoutLong, flowControl)
		}
	}
	
	/**
	 * Select Foreign Telegraphic Transfer
	 */
	void selectForeignTelegraphicTransfer(String typeTransfer, String currency, FailureHandling flowControl = defaultFailureHandling) {
		Mobile.delay(GlobalVariable.G_timeoutMiniShort)
		MobileDriver.takeScreenshot()
		'Select Continue Button'
		if(MobileDriver.waitForElementPresent(commonPage.lblContainDynamic(UITextContinue), GlobalVariable.G_timeoutMiniShort, FailureHandling.OPTIONAL)) {
			MobileDriver.tap(commonPage.lblContainDynamic(UITextContinue), GlobalVariable.G_timeoutMiniShort, FailureHandling.OPTIONAL)
		}
		'Select Type Transfer'
		Mobile.scrollToText(typeTransfer)
		MobileDriver.tap(commonPage.lblContainDynamic(typeTransfer), GlobalVariable.G_timeoutMedium, flowControl)
		'Select Transfer RM Button'
		MobileDriver.waitForElementPresent(commonPage.lblContainDynamic(UITextTranserRM), GlobalVariable.G_timeoutMedium, flowControl)
		Mobile.tap(commonPage.lblContainDynamic(UITextTranserRM), GlobalVariable.G_timeoutMedium, flowControl)
		Mobile.tap(commonPage.lblContainDynamic(UITextTranserRM), GlobalVariable.G_timeoutMedium, FailureHandling.OPTIONAL)
		if(Mobile.waitForElementPresent(commonPage.lblContainDynamic(UITextTranserRM), GlobalVariable.G_timeoutMedium, FailureHandling.OPTIONAL)) {
			'Select UI Label Transfer RM'
			Mobile.tap(commonPage.lblContainDynamic(UITextTranserRM), GlobalVariable.G_timeoutMedium, FailureHandling.OPTIONAL)
		}
		if(Mobile.waitForElementPresent(commonPage.lblDynamic(UILblProceed), GlobalVariable.G_timeoutMedium)) {
			'Select UI Label Proceed'
			MobileDriver.tap(commonPage.lblDynamic(UILblProceed), GlobalVariable.G_timeoutLong, flowControl)
		}
	}

	Map<String, String> selectAndTransferOverseaDetails(String transferType, String amount, FailureHandling flowControl = defaultFailureHandling) {
		Map<String, String> transferInfo = [:]
		scrollToText(transferType)
		'Verify transfer amount'
		double value = Double.parseDouble(amount) / 100;
		DecimalFormat df = new DecimalFormat(StringUtils.DECIMAL_FORMAT);
		String amountWithRM = 'RM ' + df.format(value);
		String displayedAmount = MobileDriver.getText(lblParentViewGrSiblingDynamic(transferType, 2), GlobalVariable.G_timeoutMedium, flowControl)
		BaseAssert.equals(amountWithRM, displayedAmount, 'Verify transfer amount', flowControl)

		String foreignAmount = MobileDriver.getText(lblParentViewGrSiblingDynamic(transferType, 3), GlobalVariable.G_timeoutMedium, flowControl)
		transferInfo[UILblTransferAmount] = amountWithRM + '\n= ' + foreignAmount

		'Verify UI Label Indicative Exchange Rate'
		Mobile.verifyElementExist(commonPage.lblDynamic(UILblIndicativeExchangeRate), GlobalVariable.G_timeoutMedium)
		// TODO: get Exchange Rate and store to fttInfo, and transform 'RM 4.5250 ≈ USD 1' to 'RM 4.5250 = USD 1'

		'Verify UI Label Daily Transfer Limit'
		Mobile.verifyElementExist(commonPage.lblDynamic(UILblDailyTransferLimit), GlobalVariable.G_timeoutMedium)

		'Verify UI Label Service Fee'
		Mobile.verifyElementExist(commonPage.lblDynamic(UILblServiceFee), GlobalVariable.G_timeoutMedium)
		transferInfo[UILblServiceFee] = MobileDriver.getText(lblParentViewGrSiblingDynamic(UILblServiceFee), GlobalVariable.G_timeoutMedium, flowControl)

		'Verify UI Label Agent Beneficiary Bank Fee'
		Mobile.verifyElementExist(commonPage.lblDynamic(UILblAgentBeneficiaryBankFee), GlobalVariable.G_timeoutMedium)
		'Verify UI Label Receive Method'
		Mobile.verifyElementExist(commonPage.lblDynamic(UILblReceiveMethod), GlobalVariable.G_timeoutMedium)
		transferInfo[UILblReceiveMethod] = MobileDriver.getText(lblParentViewGrSiblingDynamic(UILblReceiveMethod), GlobalVariable.G_timeoutMedium, flowControl)

		'Verify UI Label Transfer Duration'
		Mobile.verifyElementExist(commonPage.lblDynamic(UILblTransferDuration), GlobalVariable.G_timeoutMedium)
		transferInfo[UILblTransferDuration] = MobileDriver.getText(lblParentViewGrSiblingDynamic(UILblTransferDuration), GlobalVariable.G_timeoutMedium, flowControl)

		return transferInfo
	}

	/**
	 * Verify UI Your Details
	 */
	void verifyUIYourDetails(FailureHandling flowControl = defaultFailureHandling) {
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
		MobileDriver.takeScreenshot()
		'Select UI Label Continue'
		Mobile.tap(commonPage.lblDynamic(UITextContinue), GlobalVariable.G_timeoutMedium, flowControl)
	}

	/**
	 * Enter Visa Direct Details
	 */
	void enterRecipientVisaDirectDetails(String firstName, String lastName, FailureHandling flowControl = defaultFailureHandling) {
		Mobile.delay(GlobalVariable.G_timeoutMiniShort)
		'Input Cardholder first and last name'
		MobileDriver.setText(commonPage.txtEditNo(2), lastName, GlobalVariable.G_timeoutLong, FailureHandling.STOP_ON_FAILURE)
		Mobile.hideKeyboard(FailureHandling.OPTIONAL)
		MobileDriver.setText(commonPage.txtEditNo(3), firstName, GlobalVariable.G_timeoutLong, FailureHandling.STOP_ON_FAILURE)
		Mobile.hideKeyboard(FailureHandling.OPTIONAL)
		Mobile.takeScreenshot()
		'Select UI Label Continue'
		Mobile.tap(commonPage.lblDynamic(UITextContinue), GlobalVariable.G_timeoutMedium, flowControl)
	}

	Map<String, String> fillAndExtractYourDetails(String yourNationality, FailureHandling flowControl = defaultFailureHandling) {
		Map<String, String> yourDetails = [:]
		MobileDriver.tap(commonPage.lblDynamic(UILblNonMalaysian), GlobalVariable.G_timeoutMedium, flowControl);

		yourDetails['yourNationality'] = yourNationality

		String addressLine1 = MobileDriver.getText(commonPage.txtSiblingDynamic(UILblAddressLine1), GlobalVariable.G_timeoutMedium)
		yourDetails[UILblAddressLine1] = addressLine1

		String addressLine2 = MobileDriver.getText(commonPage.txtSiblingDynamic(UILblAddressLine2), GlobalVariable.G_timeoutMedium)
		yourDetails[UILblAddressLine2] = addressLine2

		String postcode = MobileDriver.getText(commonPage.txtSiblingDynamic(UILblPostCode), GlobalVariable.G_timeoutMedium)
		yourDetails[UILblPostCode] = postcode

		'Select UI Label Continue'
		Mobile.tap(commonPage.lblDynamic(UITextContinue), GlobalVariable.G_timeoutMedium, flowControl)

		return yourDetails
	}

	Map<String, String> fillAndExtractSenderDetailsWesternUnion(Map<String, String> details, List<String> extractList, FailureHandling flowControl = defaultFailureHandling) {
		Map<String, String> detailsCopy = details.clone()
		detailsCopy.each { key, value ->
			Mobile.scrollToText(key)
			commonPage.swipeByW3CAction(500, 1500, 500, 1300)
			delay(GlobalVariable.G_timeoutMiniShort-2)
			if (waitForElementNotPresent(commonPage.txtSiblingGroup(key), GlobalVariable.G_timeoutMiniShort, FailureHandling.OPTIONAL)) {
				MobileDriver.tap(commonPage.lblDynamic(key), GlobalVariable.G_timeoutLong, flowControl)
				commonPage.swipeByW3CAction(100, 1500, 100, 1100)
			}
			MobileDriver.setText(commonPage.txtSiblingGroup(key), value, GlobalVariable.G_timeoutLong, FailureHandling.STOP_ON_FAILURE)
			MobileDriver.tap(commonPage.lblDynamic(key), GlobalVariable.G_timeoutLong, flowControl)
		}
		commonPage.scrollToTop()
		Map<String, String> extractDetails = [:]
		extractList.each { extractLabel ->
			Mobile.scrollToText(extractLabel)
			delay(GlobalVariable.G_timeoutMiniShort-1)
			if (waitForElementNotPresent(commonPage.txtSiblingDynamic(extractLabel), GlobalVariable.G_timeoutMiniShort, FailureHandling.OPTIONAL)) {
				MobileDriver.tap(commonPage.lblDynamic(extractLabel), GlobalVariable.G_timeoutLong, flowControl)
				commonPage.swipeByW3CAction(100, 1500, 100, 1200)
			}
			String text = MobileDriver.getText(commonPage.txtSiblingDynamic(extractLabel), GlobalVariable.G_timeoutMedium)
			extractDetails[extractLabel] = text
		}

		// convert to suitable phone format for validating confirmation screen
		if (extractDetails.containsKey(UILblMobileNumber)) {
			extractDetails[UILblMobileNumber] = extractDetails[UILblMobileNumber].replaceAll(/(\+60\d{2})(\d{4})(\d{3})/, '$1 $2 $3')
		}

		return extractDetails
	}

	Map<String, String> fillAndExtractSenderTemporaryAddress(Map<String, String> details, List<String> extractList, FailureHandling flowControl = defaultFailureHandling) {
		commonPage.swipeByW3CAction(50, 1500, 50, 1100)
		Map<String, String> detailsCopy = details.clone()
		if (detailsCopy.isEmpty()) {
			MobileDriver.tap(commonPage.lblContainDynamic('My temporary address is the same as my primary address.'), GlobalVariable.G_timeoutLong, flowControl)
		}

		detailsCopy.each { key, value ->
			commonPage.scrollToText(key, 100, 1500, 100, 1200, false, GlobalVariable.G_timeoutMiniShort)
			MobileDriver.setText(commonPage.txtSiblingGroup(key), value, GlobalVariable.G_timeoutLong, FailureHandling.STOP_ON_FAILURE)
			MobileDriver.tap(commonPage.lblDynamic(key), GlobalVariable.G_timeoutLong, flowControl)
		}

		if (detailsCopy.containsKey(UILblCountry)) {
			commonPage.swipeByW3CAction(100, 1500, 100, 1100)
			commonPage.selectDropdownSiblingGroupItem(UILblCountry, detailsCopy[detailsCopy], GlobalVariable.G_timeoutMiniShort)
		}

		Map<String, String> extractDetails = [:]
		extractList.each { extractLabel ->
			commonPage.scrollToText(extractLabel, 100, 1500, 100, 1300, false, GlobalVariable.G_timeoutMiniShort)
			String text = MobileDriver.getText(commonPage.txtSiblingDynamic(extractLabel), GlobalVariable.G_timeoutMedium)
			extractDetails[extractLabel] = text
		}
		extractDetails[UILblCountry] = MobileDriver.getText(commonPage.lblDynamic("MALAYSIA"), GlobalVariable.G_timeoutMedium)
		return extractDetails
	}

	Map<String, String> fillAndExtractSenderIDAndEmploymentDetails(Map<String, String> details, List<String> extractList, FailureHandling flowControl = defaultFailureHandling) {
		Map<String, String> extractDetails = [:]

		details.each { key, value ->
			commonPage.scrollToText(key, 100, 1500, 100, 1000, false, GlobalVariable.G_timeoutMiniShort)
			Mobile.delay(GlobalVariable.G_timeoutMiniShort)
			commonPage.selectDropdownSiblingGroupItem(key, value, GlobalVariable.G_timeoutMiniShort)
		}
		commonPage.scrollToTop()

		extractList.each { label ->
			String text = ''
			if (['ID number', UILblDateOfBirth].contains(label)) {
				text = MobileDriver.getText(commonPage.txtSiblingDynamic(label), GlobalVariable.G_timeoutMedium,FailureHandling.OPTIONAL)
			} else {
				commonPage.scrollToText(label, 100, 1500, 100, 1200, false, GlobalVariable.G_timeoutMiniShort)
				text = MobileDriver.getText(lblSiblingGroupDynamic(label), GlobalVariable.G_timeoutMedium,FailureHandling.OPTIONAL)
			}
			extractDetails[label] = text
		}

		// convert to suitable date format for validating confirmation screen
		if (extractDetails.containsKey(UILblDateOfBirth)) {
			extractDetails[UILblDateOfBirth] = DateUtils.convertToFormat(extractDetails[UILblDateOfBirth], 'dd MMM yyyy', 'dd/MM/yyyy')
		}

		return extractDetails
	}

	Map<String, String> fillSenderDetails(Map<String, String> senderDetails, FailureHandling flowControl = defaultFailureHandling) {
		Mobile.delay(GlobalVariable.G_timeoutMiniShort)
		Map<String, String> senderDetailsCopy = senderDetails.clone()

		if (senderDetailsCopy.containsKey(UILblNationality)) {
			MobileDriver.tap(commonPage.lblDynamic(senderDetailsCopy.remove(UILblNationality)), GlobalVariable.G_timeoutMedium, flowControl);
		}

		if (senderDetailsCopy.containsKey(UILblCountry)) {
			String country = senderDetailsCopy.remove(UILblCountry)
			commonPage.swipeByW3CAction(500, 1500, 500, 1000)
			MobileDriver.tap(commonPage.imgSiblingGroup(UILblCountry), GlobalVariable.G_timeoutLong, flowControl)
			if(!MobileDriver.waitForElementPresent(commonPage.lblDynamic("Select Country"), GlobalVariable.G_timeoutShort, flowControl)) {
				MobileDriver.tap(commonPage.imgSiblingGroup(UILblCountry), GlobalVariable.G_timeoutLong, flowControl)
			}
			Mobile.delay(GlobalVariable.G_timeoutMiniShort)
			'Select Search Button'
			MobileDriver.tap(commonPage.imgNoDynamic(2), GlobalVariable.G_timeoutMedium, flowControl);
			'Input Country Name'
			MobileDriver.setText(commonPage.txtEditNo(1), country, GlobalVariable.G_timeoutLong, flowControl)
			Mobile.delay(GlobalVariable.G_timeoutMiniShort)
			'Select Country Name'
			MobileDriver.tap(commonPage.imgNoDynamic(3), GlobalVariable.G_timeoutMedium, flowControl)
			if(Mobile.waitForElementPresent(commonPage.imgNoDynamic(3), GlobalVariable.G_timeoutShort)) {
				MobileDriver.tap(commonPage.imgNoDynamic(3), GlobalVariable.G_timeoutMedium, flowControl);
			}
		}

		senderDetailsCopy.each { key, value ->
			MobileDriver.scrollToText(key)
			MobileDriver.setText(commonPage.txtSiblingGroup(key), value, GlobalVariable.G_timeoutLong, FailureHandling.STOP_ON_FAILURE)
			MobileDriver.tap(commonPage.lblDynamic(key), GlobalVariable.G_timeoutLong, flowControl)
		}
	}

	/**
	 * Verify UI Visa Direct Recipient Details
	 */
	void verifyUIVisaDirectRecipientDetails(String visaDirectCard, String receivingBank, FailureHandling flowControl = defaultFailureHandling) {
		Mobile.takeScreenshot()
		'Verify Recipient card number value'
		String uiCard = Mobile.getText(commonPage.txtEditNo(1), GlobalVariable.G_timeoutMedium)
		uiCard = uiCard.replaceAll('\\s+', '')
		Mobile.verifyMatch(uiCard, visaDirectCard, false)
		'Verify Recipient bank name value'
		String uiCard1 = Mobile.getText(commonPage.txtEditNo(4), GlobalVariable.G_timeoutMedium)
		Mobile.verifyMatch(uiCard1, receivingBank, false)
	}

	/**
	 * Verify UI Receipient's Bank Details & Receipient's details
	 */
	void verifyUIReceipientDetails(FailureHandling flowControl = defaultFailureHandling) {
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
		MobileDriver.takeScreenshot()
	}

	/**
	 * Verify UI Receipient's Bank Details & Receipient's details
	 */
	void verifyUIReceipientDetailsVD(FailureHandling flowControl = defaultFailureHandling) {
		'Verify UI Label Recipient Details'
		Mobile.verifyElementExist(commonPage.lblDynamic(UILblRecipientCardDetails), GlobalVariable.G_timeoutMedium)
		'Verify UI Label Bank Name Bank Code And Address'
		Mobile.verifyElementExist(commonPage.lblDynamic(UILblCardNumber), GlobalVariable.G_timeoutMedium)
		'Verify UI Label Account Number'
		Mobile.verifyElementExist(commonPage.lblDynamic(UILblCardHolderlastname), GlobalVariable.G_timeoutMedium)
		'Verify UI Label IC Passport Number'
		Mobile.verifyElementExist(commonPage.lblDynamic(UILblCardHolderfirstname), GlobalVariable.G_timeoutMedium)
		'Verify UI Label Nationality And Address'
		Mobile.verifyElementExist(commonPage.lblDynamic(UILblOverseasBankName), GlobalVariable.G_timeoutMedium)
		Mobile.takeScreenshot()
	}

	/**
	 * Verify Receipient's Bank Details & Receipient's details
	 */
	void verifyReceipientDetailsVD(String visaDirectCardSpace, String lastName, String firstName, String receivingBank, FailureHandling flowControl = defaultFailureHandling) {
		Mobile.takeScreenshot()
		Mobile.tap(findTestObject('Object Repository/Mobile/VCB/Android/Overseas Transfer Screen/txtEdit'), 0, FailureHandling.OPTIONAL)
		'Verify Card number'
		String uiCard1 = Mobile.getText(commonPage.txtEditNo(1), GlobalVariable.G_timeoutMedium)
		Mobile.verifyMatch(uiCard1, visaDirectCardSpace, false)
		'Verify last name'
		String uiCard2 = Mobile.getText(commonPage.txtEditNo(2), GlobalVariable.G_timeoutMedium)
		Mobile.verifyMatch(uiCard2, lastName, false)
		'Verify first name'
		String uiCard3 = Mobile.getText(commonPage.txtEditNo(3), GlobalVariable.G_timeoutMedium)
		Mobile.verifyMatch(uiCard3, firstName, false)
		'Verify bank name'
		String uiCard4 = Mobile.getText(commonPage.txtEditNo(4), GlobalVariable.G_timeoutMedium)
		Mobile.verifyMatch(uiCard4, receivingBank, false)
		Mobile.takeScreenshot()
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
	 * Receipient's Bank Details & Receipient's details
	 */
	void enterReceipientDetails(FailureHandling flowControl = defaultFailureHandling) {
		'Select UI Label Continue'
		if(Mobile.waitForElementPresent(commonPage.lblDynamic(UITextContinue), GlobalVariable.G_timeoutMiniShort)) {
			MobileDriver.tap(commonPage.lblDynamic(UITextContinue), GlobalVariable.G_timeoutLong, flowControl)
		}
		'Select UI Label Continue'
		if(Mobile.waitForElementPresent(commonPage.lblDynamic(UILblConfirm), GlobalVariable.G_timeoutMiniShort)) {
			Mobile.tap(commonPage.lblDynamic(UILblConfirm), GlobalVariable.G_timeoutMedium, flowControl)
		}
		MobileDriver.takeScreenshot()
	}
	/**
	 * Verify UI Recipient Bank Details
	 */
	void verifyUIRecipientBankDetails(FailureHandling flowControl = defaultFailureHandling) {
		'Verify UI Label Please Fill In Recipient Bank Details'
		Mobile.verifyElementExist(commonPage.lblDynamic(UILblPleaseFillInRecipientBankDetails), GlobalVariable.G_timeoutMedium)
		'Verify UI Label Branch Address'
		Mobile.verifyElementExist(commonPage.lblDynamic(UILblBranchAddress), GlobalVariable.G_timeoutMedium)
		'Verify UI Label City'
		Mobile.verifyElementExist(commonPage.lblDynamic(UILblCity), GlobalVariable.G_timeoutMedium)
		'Verify UI Label Branch Address Info'
		MobileDriver.tap(commonPage.imgNoDynamic(3), GlobalVariable.G_timeoutMedium, flowControl)
		Mobile.verifyElementExist(commonPage.lblDynamic(UILblBranchAddressInfo), GlobalVariable.G_timeoutMedium)
		MobileDriver.takeScreenshot()
		'Select Close Button'
		MobileDriver.tap(commonPage.imgNoDynamic(1), GlobalVariable.G_timeoutMedium, flowControl)
		MobileDriver.takeScreenshot()
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
		MobileDriver.takeScreenshot()
		'Select Continue Button'
		MobileDriver.tap(commonPage.lblDynamic(UITextContinue), GlobalVariable.G_timeoutLong, flowControl)
		if(Mobile.waitForElementPresent(commonPage.lblDynamic(UITextContinue), GlobalVariable.G_timeoutShort)) {
			MobileDriver.tap(commonPage.lblDynamic(UITextContinue), GlobalVariable.G_timeoutLong, flowControl)
		}
	}

	void fillRecipientBankDetails(Map<String, String> recipientBankDetails, FailureHandling flowControl = defaultFailureHandling) {
		Map<String, String> recipientBankDetailsCopy = recipientBankDetails.clone()

		'Select UI Dropdown Bank name'
		if (recipientBankDetailsCopy.containsKey('Bank name')) {
			String bankName = recipientBankDetailsCopy.remove('Bank name')
			MobileDriver.tap(commonPage.imgSibling(UIDrdBankName), GlobalVariable.G_timeoutLong, flowControl)
			if(Mobile.waitForElementNotPresent(commonPage.lblDynamic(UIDone), GlobalVariable.G_timeoutMedium)) {
				MobileDriver.tap(commonPage.imgSibling(UIDrdBankName), GlobalVariable.G_timeoutLong, flowControl)
			}
			Mobile.scrollToText(bankName)
			MobileDriver.tap(commonPage.lblDynamic(bankName), GlobalVariable.G_timeoutLong, flowControl)
			MobileDriver.tap(commonPage.lblDynamic(UIDone), GlobalVariable.G_timeoutLong, flowControl)
		}

		recipientBankDetailsCopy.each { key, value ->
			MobileDriver.scrollToText(key)
			MobileDriver.setText(commonPage.txtSiblingGroup(key), value, GlobalVariable.G_timeoutLong, FailureHandling.STOP_ON_FAILURE)
			MobileDriver.tap(commonPage.lblDynamic(key), GlobalVariable.G_timeoutLong, flowControl)
		}
	}

	/**
	 * Verify UI Recipient Details
	 */
	void verifyUIRecipientDetails(FailureHandling flowControl = defaultFailureHandling) {
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
		commonPage.swipeByW3CAction(500, 1500, 500, 500)
		Mobile.verifyElementExist(commonPage.lblDynamic(UILblMobileNumber), GlobalVariable.G_timeoutMedium)
		'Verify UI Label Email Address'
		scrollToText(UILblEmailAddress, flowControl)
		Mobile.verifyElementExist(commonPage.lblDynamic(UILblEmailAddress), GlobalVariable.G_timeoutMedium)
		commonPage.swipeByW3CAction(500, 500, 500, 1500)
		scrollToTop()
		MobileDriver.takeScreenshot()
	}
	/**
	 * Enter Recipient Details
	 */
	void enterRecipientDetails(String nationality, String name, String passportNumber,String addressLine1, String addressLine2, String country, String mobileNumber, String emailAddress, FailureHandling flowControl = defaultFailureHandling) {
		Mobile.delay(GlobalVariable.G_timeoutMiniShort)
		scrollToText(nationality)
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
		MobileDriver.tap(commonPage.imgNoDynamic(2), GlobalVariable.G_timeoutMedium, flowControl);
		'Input Country Name'
		MobileDriver.setText(commonPage.txtEditNo(1), country, GlobalVariable.G_timeoutLong, flowControl)
		Mobile.delay(GlobalVariable.G_timeoutMiniShort)
		'Select Country Name'
		MobileDriver.tap(commonPage.imgNoDynamic(3), GlobalVariable.G_timeoutMedium, flowControl);
		if(Mobile.waitForElementPresent(commonPage.imgNoDynamic(3), GlobalVariable.G_timeoutMedium)) {
			MobileDriver.tap(commonPage.imgNoDynamic(3), GlobalVariable.G_timeoutMedium, flowControl);
		}
		'Enter Account Number'
		MobileDriver.setText(commonPage.txtEditNo(4), mobileNumber, GlobalVariable.G_timeoutLong, FailureHandling.STOP_ON_FAILURE)
		'Enter Bank Address'
		MobileDriver.setText(commonPage.txtEditNo(5), emailAddress, GlobalVariable.G_timeoutLong, FailureHandling.STOP_ON_FAILURE)
		MobileDriver.takeScreenshot()
		'Select Continue Button'
		MobileDriver.tap(commonPage.lblDynamic(UITextContinue), GlobalVariable.G_timeoutLong, flowControl)
		if(Mobile.waitForElementPresent(commonPage.lblDynamic(UITextContinue), GlobalVariable.G_timeoutShort)) {
			MobileDriver.tap(commonPage.lblDynamic(UITextContinue), GlobalVariable.G_timeoutLong, flowControl)
		}
	}

	void fillRecipientDetails(Map<String, String> recipientDetails, FailureHandling flowControl = defaultFailureHandling) {
		Map recipientDetailsCopy = recipientDetails.clone()

		'Select Nationality'
		if (recipientDetailsCopy.containsKey(UILblNationality)) {
			MobileDriver.tap(commonPage.lblDynamic(recipientDetailsCopy.remove(UILblNationality)), GlobalVariable.G_timeoutLong, flowControl)
		}

		'Select UI Dropdown Country'
		if (recipientDetailsCopy.containsKey(UILblCountry)) {
			commonPage.swipeByW3CAction(500, 1500, 500, 1200)
			selectCountryDropdownWithSearchBtn(recipientDetailsCopy.remove(UILblCountry), flowControl)
		}

		recipientDetailsCopy.each { key, value ->
			MobileDriver.scrollToText(key)
			MobileDriver.setText(commonPage.txtSiblingGroup(key), value, GlobalVariable.G_timeoutLong, FailureHandling.STOP_ON_FAILURE)
			MobileDriver.tap(commonPage.lblDynamic(key), GlobalVariable.G_timeoutLong, flowControl)
		}

		MobileDriver.takeScreenshot()
	}

	void selectCountryDropdownWithSearchBtn(String country, FailureHandling flowControl = defaultFailureHandling) {
		MobileDriver.tap(commonPage.imgSibling(UIDrdCountry), GlobalVariable.G_timeoutLong, flowControl)
		if(Mobile.waitForElementNotPresent(commonPage.lblDynamic(UILblSelectCountry), GlobalVariable.G_timeoutShort)) {
			MobileDriver.tap(commonPage.imgSibling(UIDrdCountry), GlobalVariable.G_timeoutLong, flowControl)
		}
		Mobile.delay(GlobalVariable.G_timeoutMiniShort)
		'Select Search Button'
		MobileDriver.tap(commonPage.imgNoDynamic(2), GlobalVariable.G_timeoutMedium, flowControl);
		'Input Country Name'
		MobileDriver.setText(commonPage.txtEditNo(1), country, GlobalVariable.G_timeoutLong, flowControl)
		Mobile.delay(GlobalVariable.G_timeoutMiniShort)
		'Select Country Name'
		MobileDriver.tap(commonPage.imgNoDynamic(3), GlobalVariable.G_timeoutMedium, flowControl);
		if(Mobile.waitForElementPresent(commonPage.imgNoDynamic(3), GlobalVariable.G_timeoutMedium)) {
			MobileDriver.tap(commonPage.imgNoDynamic(3), GlobalVariable.G_timeoutMedium, flowControl);
		}
	}

	void fillRecipientDetailsWesternUnion(Map<String, String> recipientDetails, FailureHandling flowControl = defaultFailureHandling) {
		Map recipientDetailsCopy = recipientDetails.clone()

//			selectCountryDropdownWithSearchBtn(recipientDetailsCopy.remove('Recipient country & code'))
				MobileDriver.tap(commonPage.lblContainDynamic("Please select"), 0,FailureHandling.OPTIONAL)
				MobileDriver.tap(commonPage.lblContainDynamic("Malaysia"), 0,FailureHandling.OPTIONAL)
				Mobile.delay(3)

//			selectCountryDropdownWithSearchBtn(recipientDetailsCopy.remove(UILblCountry))
			Mobile.scrollToText(UILblCountry)
			Mobile.swipe(500, 1500, 500, 400)
			MobileDriver.tap(commonPage.lblContainDynamic("Please select"), 0)
			MobileDriver.tap(commonPage.lblContainDynamic("Malaysia"), 0)
		

//			selectCountryDropdownWithSearchBtn(recipientDetailsCopy.remove('ID Type (Optional)'))
			Mobile.scrollToText("ID Type (Optional)")
			commonPage.swipeByW3CAction(500, 1500, 500, 1300)
			MobileDriver.tap(commonPage.lblContainDynamic("Please select"), 0)
			MobileDriver.tap(commonPage.lblContainDynamic("National ID Card"), 0)
			MobileDriver.tap(commonPage.lblDynamic("Done"),0)
			
			MobileDriver.setText(commonPage.txtSiblingGroup("ID number (Optional)"), "012345678", GlobalVariable.G_timeoutLong, FailureHandling.STOP_ON_FAILURE)
		

		commonPage.scrollToTop()

		recipientDetailsCopy.each { key, value ->
			Mobile.scrollToText(key)
			MobileDriver.setText(commonPage.txtSiblingGroup(key), value, GlobalVariable.G_timeoutLong, FailureHandling.STOP_ON_FAILURE)
			MobileDriver.tap(commonPage.lblDynamic(key), GlobalVariable.G_timeoutLong, flowControl)
			Mobile.swipe(500, 1500, 500, 900)
			
		}
		MobileDriver.tap(commonPage.txtSiblingGroup(UILblDateOfBirth), GlobalVariable.G_timeoutMedium, flowControl)
		if(waitForElementPresent(commonPage.txtSiblingGroup(UILblDateOfBirth), GlobalVariable.G_timeoutMedium)) {
			MobileDriver.tap(commonPage.txtSiblingGroup(UILblDateOfBirth), GlobalVariable.G_timeoutMedium, flowControl)
			Mobile.delay(GlobalVariable.G_timeoutMiniShort)
			MobileDriver.tap(commonPage.lblDynamic("Done"),0)
		}

		if (recipientDetails.containsKey(UILblMobileNumber)) {
			recipientDetails[UILblMobileNumber] = recipientDetails[UILblMobileNumber].replaceAll(/(\d{4})(\d{4})/, '$1 $2')
		}
	}

	/**
	 * Verify UI Recipient Details
	 */
	void verifyUITransferDetails(FailureHandling flowControl = defaultFailureHandling) {
		'Verify UI Label Please Fill In Transfer Details'
		Mobile.verifyElementExist(commonPage.lblDynamic(UILblPleaseFillInTransferDetails), GlobalVariable.G_timeoutMedium)
		'Verify UI Label Purpose Of Transfer'
		Mobile.verifyElementExist(commonPage.lblDynamic(UILblPurposeOfTransfer), GlobalVariable.G_timeoutMedium)
		'Verify UI Label Additional Details'
		Mobile.verifyElementExist(commonPage.lblDynamic(UILblAdditionalDetails), GlobalVariable.G_timeoutMedium)
		'Select UI Label Agent Beneficiary Bank Fee Info'
		MobileDriver.tap(commonPage.imgNoDynamic(3), GlobalVariable.G_timeoutMedium, flowControl)
		MobileDriver.takeScreenshot()
		'Select Close Button'
		MobileDriver.tap(commonPage.imgNoDynamic(1), GlobalVariable.G_timeoutMedium, flowControl)
		MobileDriver.takeScreenshot()
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

		MobileDriver.takeScreenshot()

		'Select Continue Button'
		Mobile.scrollToText(UITextContinue)
		MobileDriver.tap(commonPage.lblDynamic(UITextContinue), GlobalVariable.G_timeoutLong, flowControl)
		if(Mobile.waitForElementPresent(commonPage.lblDynamic(UITextContinue), GlobalVariable.G_timeoutShort)) {
			MobileDriver.tap(commonPage.lblDynamic(UITextContinue), GlobalVariable.G_timeoutLong, flowControl)
		}
	}

	/**
	 * Enter Bakong Transfer Details
	 */
	void enterBakongTransferDetails(String purposeOfTransfer, String subPurpose, String additionalDetails, FailureHandling flowControl = defaultFailureHandling) {
		Mobile.delay(GlobalVariable.G_timeoutMiniShort)
		Mobile.takeScreenshot()
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
			Mobile.takeScreenshot()
			Mobile.delay(GlobalVariable.G_timeoutMiniShort)
			'Scroll To Text Purpose Of Transfer'
			Mobile.scrollToText(subPurpose)
			'Select Purpose Of Transfer'
			MobileDriver.tap(commonPage.lblDynamic(subPurpose), GlobalVariable.G_timeoutLong, flowControl)
			'Select Done Button'
			MobileDriver.tap(commonPage.lblDynamic(UIDone), GlobalVariable.G_timeoutLong, flowControl)
		}
		'Enter Additional Details'
		MobileDriver.setText(commonPage.txtEditNo(1), additionalDetails, GlobalVariable.G_timeoutLong, FailureHandling.STOP_ON_FAILURE)
		Mobile.takeScreenshot()
		'Select Continue Button'
		MobileDriver.tap(commonPage.lblDynamic(UITextContinue), GlobalVariable.G_timeoutLong, flowControl)
		if(Mobile.waitForElementPresent(commonPage.lblDynamic(UITextContinue), GlobalVariable.G_timeoutShort, FailureHandling.OPTIONAL)) {
			MobileDriver.tap(commonPage.lblDynamic(UITextContinue), GlobalVariable.G_timeoutLong, FailureHandling.OPTIONAL)
		}
		Mobile.takeScreenshot()
	}

	void selectTransferDetails(Map<String, String> transferDetails, FailureHandling flowControl = defaultFailureHandling) {
		Map<String, String> transferDetailsCopy = transferDetails.clone()

		if (transferDetailsCopy.containsKey('bank fee')) {
			MobileDriver.tap(commonPage.lblContainDynamic(transferDetailsCopy.remove('bank fee')), GlobalVariable.G_timeoutLong, flowControl)
		}

		if (transferDetailsCopy.containsKey(UILblAdditionalDetails)) {
			commonPage.swipe(500, 1500, 500, 500)
			MobileDriver.setText(commonPage.txtEditNo(1), transferDetailsCopy.remove(UILblAdditionalDetails), GlobalVariable.G_timeoutLong, FailureHandling.STOP_ON_FAILURE)
			MobileDriver.tap(commonPage.lblContainDynamic(UILblAdditionalDetails), GlobalVariable.G_timeoutLong, flowControl)
		}

		transferDetailsCopy.each { key, value ->
			MobileDriver.scrollToText(key)
			selectDropdown(key, value)
		}
	}

	void selectDropdown(String label, String value, int timeout = GlobalVariable.G_timeoutShort, FailureHandling flowControl = defaultFailureHandling) {
		MobileDriver.tap(commonPage.imgSiblingGroup(label), GlobalVariable.G_timeoutLong, flowControl)
		if(Mobile.waitForElementNotPresent(commonPage.lblDynamic(UIDone), GlobalVariable.G_timeoutShort)) {
			MobileDriver.tap(commonPage.imgSiblingGroup(label), GlobalVariable.G_timeoutLong, flowControl)
		}
		Mobile.scrollToText(value)
		MobileDriver.tap(commonPage.lblDynamic(value), GlobalVariable.G_timeoutLong, flowControl)
		MobileDriver.tap(commonPage.lblDynamic(UIDone), GlobalVariable.G_timeoutLong, flowControl)
	}

	/**
	 * Enter Confirmation
	 */
	void enterConfirmation(FailureHandling flowControl = defaultFailureHandling) {
		Mobile.delay(GlobalVariable.G_timeoutMiniShort)
		MobileDriver.takeScreenshot()
		'Select UI Label Confirm And Submit'
		Mobile.scrollToText(UILblConfirmAndSubmit)
		MobileDriver.tap(commonPage.lblDynamic(UILblConfirmAndSubmit), GlobalVariable.G_timeoutLong, flowControl)
		if(Mobile.waitForElementPresent(commonPage.lblDynamic(UILblConfirmAndSubmit), GlobalVariable.G_timeoutShort)) {
			MobileDriver.tap(commonPage.lblDynamic(UILblConfirmAndSubmit), GlobalVariable.G_timeoutLong, flowControl)
		}
	}

	void verifyConfirmationFTT(Map<String, String> senderDetails, Map<String, String> recipientBankDetails, Map<String, String> recipientDetails, Map<String, String> transferDetails,
			FailureHandling flowControl = defaultFailureHandling) {
		List<Map<String, String>> confirmationMaps = [
			senderDetails,
			recipientBankDetails,
			recipientDetails,
			transferDetails
		]

		for (Map<String, String> confirmationMap : confirmationMaps) {
			scrollToAndVerifyTextInList(confirmationMap.collect { entry -> entry.value }, commonPage.lblContainDynamicPath(), GlobalVariable.G_timeoutMedium)
		}
	}

	void verifyEditConfirmationFTT(Map<String, String> edittedSenderDetails, Map<String, String> edittedRecipientBankDetails, Map<String, String> edittedRecipientDetails, Map<String, String> edittedTransferDetails,
			FailureHandling flowControl = defaultFailureHandling) {
		scrollToTop()

		if (!edittedSenderDetails.isEmpty()) {
			MobileDriver.tap(commonPage.lblSiblingGroup("Sender's Details"), GlobalVariable.G_timeoutLong, flowControl)
			fillSenderDetails(edittedSenderDetails)
			commonPage.tapContinueBtn()
		}

		if (!edittedRecipientBankDetails.isEmpty()) {
			commonPage.swipeByW3CAction(500, 1500, 500, 1000)
			MobileDriver.tap(commonPage.lblSiblingGroup("Recipient’s Bank Details"), GlobalVariable.G_timeoutLong, flowControl)
			enterReceipientDetails()
			fillRecipientBankDetails(edittedRecipientBankDetails)
			commonPage.tapContinueBtn()
		}

		if (!edittedRecipientDetails.isEmpty()) {
			commonPage.swipeByW3CAction(500, 1500, 500, 1000)
			MobileDriver.tap(recipientDetailEditBtn(), GlobalVariable.G_timeoutLong, flowControl)
			fillRecipientDetails(edittedRecipientDetails)
			commonPage.tapContinueBtn()
		}

		if (!edittedTransferDetails.isEmpty()) {
			commonPage.swipeByW3CAction(500, 1500, 500, 1000)
			MobileDriver.tap(commonPage.lblSiblingGroup(UILblTransferDetails), GlobalVariable.G_timeoutLong, flowControl)
			selectTransferDetails(edittedTransferDetails)
			commonPage.tapContinueBtn()
		}

		scrollToTop()
		verifyConfirmationFTT(edittedSenderDetails, edittedRecipientBankDetails, edittedRecipientDetails, edittedTransferDetails)
		MobileDriver.scrollToText(UILblConfirmAndSubmit)
		MobileDriver.tap(commonPage.lblDynamic(UILblConfirmAndSubmit), GlobalVariable.G_timeoutLong, flowControl)
	}

	void verifyConfirmationWesternUnion(Map<String, String> senderDetails1, Map<String, String> senderDetails2, Map<String, String> senderDetails3,
			Map<String, String> recipientDetails, Map<String, String> transferDetails) {
		List<Map<String, String>> confirmationMaps = [
			senderDetails1,
			senderDetails2,
			senderDetails3,
			recipientDetails,
			transferDetails
		]

		for (Map<String, String> confirmationMap : confirmationMaps) {
			scrollToAndVerifyTextInList(confirmationMap.collect { entry -> entry.value }, commonPage.lblContainDynamicPath(), GlobalVariable.G_timeoutMedium)
		}
	}

	void verifyEditConfirmationWesternUnion(Map<String, String> edittedSenderDetails1, Map<String, String> edittedSenderDetails2, Map<String, String> edittedSenderDetails3,
			Map<String, String> edittedRecipientDetails, Map<String, String> edittedTransferDetails, FailureHandling flowControl = defaultFailureHandling) {
		scrollToTop()

		Map<String, String> senderDetails1 = [:]
		Map<String, String> senderDetails2 = [:]
		Map<String, String> senderDetails3 = [:]
		if (!edittedSenderDetails1.isEmpty() || !edittedSenderDetails2.isEmpty() || !edittedSenderDetails3.isEmpty()) {
			MobileDriver.tap(commonPage.lblSiblingGroup("Sender’s Details"), GlobalVariable.G_timeoutLong, flowControl)
			senderDetails1 = fillAndExtractSenderDetailsWesternUnion(edittedSenderDetails1, edittedSenderDetails1.collect { entry -> entry.key })
			commonPage.tapContinueBtn()
			commonPage.waitLoading()
			senderDetails2 = fillAndExtractSenderTemporaryAddress(edittedSenderDetails2, edittedSenderDetails2.collect { entry -> entry.key })
			commonPage.tapContinueBtn()
			commonPage.waitLoading()
			senderDetails3 = fillAndExtractSenderIDAndEmploymentDetails(edittedSenderDetails3, edittedSenderDetails3.collect { entry -> entry.key })
			commonPage.tapContinueBtn()
			commonPage.waitLoading()
		}

		if (!edittedRecipientDetails.isEmpty()) {
			commonPage.swipeByW3CAction(500, 1500, 500, 1000)
			MobileDriver.tap(recipientDetailEditBtn(), GlobalVariable.G_timeoutLong, flowControl)
			fillRecipientDetailsWesternUnion(edittedRecipientDetails)
			commonPage.tapContinueBtn()
			commonPage.waitLoading()
		}

		if (!edittedTransferDetails.isEmpty()) {
			commonPage.swipeByW3CAction(500, 1500, 500, 1000)
			MobileDriver.tap(commonPage.lblSiblingGroup(UILblTransferDetails), GlobalVariable.G_timeoutLong, flowControl)
			selectTransferDetails(edittedTransferDetails)
			commonPage.tapContinueBtn()
		}

		scrollToTop()
		verifyConfirmationWesternUnion(senderDetails1, senderDetails2, senderDetails3, edittedRecipientDetails, edittedTransferDetails)
		MobileDriver.scrollToText(UILblConfirmAndSubmit)
		MobileDriver.tap(commonPage.lblDynamic(UILblConfirmAndSubmit), GlobalVariable.G_timeoutLong, flowControl)
	}

	/**
	 * Enter Terms And Conditions
	 */
	void enterTermsAndConditions(FailureHandling flowControl = defaultFailureHandling) {
		Mobile.delay(GlobalVariable.G_timeoutMedium)
		MobileDriver.takeScreenshot()
		'Select UI Label Agree And Confirm - retry to handle stale element'
		int retries = 3
		boolean tapped = false
		while (retries-- > 0 && !tapped) {
			try {
				if (Mobile.waitForElementPresent(commonPage.lblContainDynamicNo(UILblAgree,1), GlobalVariable.G_timeoutShort)) {
					MobileDriver.tap(commonPage.lblContainDynamicNo(UILblAgree,1), GlobalVariable.G_timeoutLong, flowControl)
					tapped = true
				}
			} catch (Exception e) {
				Mobile.delay(GlobalVariable.G_timeoutShort)
			}
		}
	}
	/**
	 * Enter Transfer From
	 */
	void enterTransferFrom(boolean verifySST = false, FailureHandling flowControl = defaultFailureHandling) {
		Mobile.delay(GlobalVariable.G_timeoutMedium)
		MobileDriver.takeScreenshot()
		if (verifySST) {
			verifyServiceFeeSST()
			verifyBankFeeSST()
		}
		commonPage.swipeByW3CAction(500, 1500, 500, 500)
		MobileDriver.takeScreenshot()
		Mobile.delay(GlobalVariable.G_timeoutShort)
		
		MobileDriver.tap(commonPage.lblContainDynamic("Now"), GlobalVariable.G_timeoutShort,FailureHandling.OPTIONAL)
		MobileDriver.tap(commonPage.lblContainDynamic("Now"), GlobalVariable.G_timeoutShort,FailureHandling.OPTIONAL)
		Mobile.delay(GlobalVariable.G_timeoutShort)
	}

	/**
	 * Add Favorites
	 */
	void addFavoriteVisaDirect(FailureHandling flowControl = defaultFailureHandling) {
		Mobile.delay(GlobalVariable.G_timeoutMiniShort)
		'Select Add to Favorites'
		MobileDriver.tap(commonPage.lblDynamic(UILblAddFavorites), GlobalVariable.G_timeoutLong, flowControl)
		Mobile.delay(GlobalVariable.G_timeoutMiniShort)
		Mobile.takeScreenshot()
		Mobile.delay(GlobalVariable.G_timeoutMiniShort)
		MobileDriver.tap(commonPage.lblDynamic(UILblAddFavorites), GlobalVariable.G_timeoutLong, flowControl)
		Mobile.delay(GlobalVariable.G_timeoutShort)
		Mobile.takeScreenshot()
		commonPage.scrollUpDynamic(500)
		Mobile.delay(GlobalVariable.G_timeoutMiniShort)
		Mobile.takeScreenshot()
		if(Mobile.waitForElementPresent(commonPage.lblDynamic(UILblTransferNow), GlobalVariable.G_timeoutLong,FailureHandling.OPTIONAL)) {
			MobileDriver.tap(commonPage.lblDynamic(UILblTransferNow), GlobalVariable.G_timeoutLong, FailureHandling.OPTIONAL)
			Mobile.delay(GlobalVariable.G_timeoutShort)
			if(Mobile.waitForElementPresent(commonPage.lblDynamic(UILblTransferNow), GlobalVariable.G_timeoutShort,FailureHandling.OPTIONAL)) {
				MobileDriver.tap(commonPage.lblDynamic(UILblTransferNow), GlobalVariable.G_timeoutLong, FailureHandling.OPTIONAL)
			}
		}
		Mobile.delay(GlobalVariable.G_timeoutShort)
	}

	Map<String, String> verifyAndExtractTransferDetailsFTT(Map<String, String> transferVerificationInfo, FailureHandling flowControl = defaultFailureHandling) {
		Mobile.delay(GlobalVariable.G_timeoutShort)
		Map<String, String> fftInfo = [:]

		commonPage.swipeByW3CAction(500, 1500, 500, 500)
		transferVerificationInfo.each { key, value ->
			String transferConfirmationValue = MobileDriver.getText(commonPage.lblSiblingDynamic(key), GlobalVariable.G_timeoutLong, flowControl)
			BaseAssert.equals(value, transferConfirmationValue, "Verify ${key}", flowControl)
			fftInfo[key] = value
		}

		String transferAmountRM, transferAmountForeign
		(transferAmountRM, transferAmountForeign) = fftInfo[UILblTransferAmount].split('\\n=\\s')
		fftInfo['Foreign currency amount'] = transferAmountForeign
		String bankFee = MobileDriver.getText(commonPage.lblSiblingDynamic('Agent/Beneficiary bank fee'), GlobalVariable.G_timeoutLong, flowControl)
		fftInfo['Agent/Beneficiary bank fee'] = bankFee
		String totalAmount = MobileDriver.getText(commonPage.lblSiblingDynamic('Total amount'), GlobalVariable.G_timeoutLong, flowControl)
		fftInfo['Total amount'] = totalAmount

		'Verify total amount'
		Double totalAmountDouble = Double.parseDouble(totalAmount.replace("RM ", "").replace(",", "").trim())
		Double serviceFeeDouble = Double.parseDouble(fftInfo['Service fee'].replace("RM ", "").replace(",", "").trim())
		Double bankFeeDouble = Double.parseDouble(bankFee.replace("RM ", "").replace(",", "").trim())
		Double transferAmountDouble = Double.parseDouble(transferAmountRM.replace("RM ", "").replace(",", "").trim())
		BaseAssert.equals(String.format("%.2f", (serviceFeeDouble + bankFeeDouble + transferAmountDouble)), String.format("%.2f", totalAmountDouble), 'Verify total amount', flowControl)

		return fftInfo
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
		MobileDriver.takeScreenshot()
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

	void fillRecipientBankAndAccount(Map<String, String> recipientBankDetails, FailureHandling flowControl = defaultFailureHandling) {
		Map<String, String> recipientBankDetailsCopy = recipientBankDetails.clone()

		'Select UI Dropdown Bank name'
		if (recipientBankDetailsCopy.containsKey('Bank name')) {
			String bankName = recipientBankDetailsCopy.remove('Bank name')
			MobileDriver.tap(commonPage.imgSibling(UIDrdBankName), GlobalVariable.G_timeoutLong, flowControl)
			if(Mobile.waitForElementNotPresent(commonPage.lblDynamic(UIDone), GlobalVariable.G_timeoutMedium)) {
				MobileDriver.tap(commonPage.imgSibling(UIDrdBankName), GlobalVariable.G_timeoutLong, flowControl)
			}
			Mobile.scrollToText(bankName)
			MobileDriver.tap(commonPage.lblDynamic(bankName), GlobalVariable.G_timeoutLong, flowControl)
			MobileDriver.tap(commonPage.lblDynamic(UIDone), GlobalVariable.G_timeoutLong, flowControl)
		}
		recipientBankDetailsCopy.each { key,value ->
			commonPage.enterTextSiblingGroup(key, value)
		}
		commonPage.tapContinueBtn()

		delay(GlobalVariable.G_timeoutMiniShort-1)
	}

	void fillRecipientDetail(Map<String, String> recipientBankDetails, FailureHandling flowControl = defaultFailureHandling) {
		MobileDriver.tap(commonPage.lblDynamic(recipientBankDetails.remove("Nationality")), GlobalVariable.G_timeoutShort, flowControl)

		recipientBankDetails.each { key,value ->
			commonPage.enterTextSiblingGroup(key, value)
		}
		scrollToEnd()
		commonPage.tapContinueBtn()
		commonPage.waitLoading()
	}

	void verifyConfirmationMOT(Map<String, String> details1, Map<String, String> details2, Map<String, String> details3, FailureHandling flowControl = defaultFailureHandling) {
		List<Map<String, String>> confirmationMaps = [
			details1,
			details2,
			details3
		]

		for (Map<String, String> confirmationMap : confirmationMaps) {
			scrollToAndVerifyTextInList(confirmationMap.collect { entry -> entry.value }, commonPage.lblNormalizeSpacePath(), GlobalVariable.G_timeoutMedium)
		}
		MobileDriver.tap(commonPage.lblContainDynamic(UILblConfirmAndSubmit), GlobalVariable.G_timeoutLong, flowControl)
		commonPage.waitLoading()
	}
	void enterRecipientDetailsFTT(Map<String, String> recipientBankDetails, String country, String mobileNumber, FailureHandling flowControl = defaultFailureHandling) {
		MobileDriver.tap(commonPage.lblDynamic(recipientBankDetails.remove("Nationality")), GlobalVariable.G_timeoutShort, flowControl)

		recipientBankDetails.each { key,value ->
			MobileDriver.scrollToText(key)
			commonPage.enterTextSiblingGroup(key, value)
		}
		'Input Mobile Number'
		commonPage.swipeByW3CAction(500, 1500, 500, 300)
		Mobile.clearText(commonPage.txtSiblingGroup(UILblMobileNumber), GlobalVariable.G_timeoutLong, FailureHandling.OPTIONAL)
		MobileDriver.setText(commonPage.txtSiblingGroup(UILblMobileNumber), mobileNumber, GlobalVariable.G_timeoutLong, flowControl)
		'Select UI Dropdown Country'
		MobileDriver.tap(commonPage.imgSibling(UIDrdCountry), GlobalVariable.G_timeoutLong, flowControl)
		if(Mobile.waitForElementNotPresent(commonPage.lblDynamic(UILblSelectCountry), GlobalVariable.G_timeoutShort)) {
			MobileDriver.tap(commonPage.imgSibling(UIDrdCountry), GlobalVariable.G_timeoutLong, flowControl)
		}
		Mobile.delay(GlobalVariable.G_timeoutMiniShort)
		'Select Search Button'
		MobileDriver.tap(commonPage.imgNoDynamic(2), GlobalVariable.G_timeoutMedium, flowControl);
		'Input Country Name'
		MobileDriver.setText(commonPage.txtEditNo(1), country, GlobalVariable.G_timeoutLong, flowControl)
		Mobile.delay(GlobalVariable.G_timeoutMiniShort)
		'Select Country Name'
		MobileDriver.tap(commonPage.imgNoDynamic(3), GlobalVariable.G_timeoutMedium, flowControl);
		'Select Continue Button'
		if(Mobile.waitForElementPresent(commonPage.lblDynamic(UITextContinue), GlobalVariable.G_timeoutShort)) {
			MobileDriver.tap(commonPage.lblDynamic(UITextContinue), GlobalVariable.G_timeoutLong, flowControl)
		}
		commonPage.waitLoading()
	}
	
	//New Overseas Keywords (Non-KMS)
	void fillInWesternUnionDetails() {
		MobileDriver.waitForElementPresent(commonPage.lblDynamic("Occupation"), 0)
		
		MobileDriver.tap(commonPage.lblDynamic("Please select"), GlobalVariable.G_timeoutMiniShort)
		MobileDriver.delay(GlobalVariable.G_timeoutMiniShort)
		MobileDriver.tap(commonPage.lblDynamic("Done"), GlobalVariable.G_timeoutMiniShort)
		MobileDriver.delay(GlobalVariable.G_timeoutMiniShort)
		
		MobileDriver.tap(commonPage.lblDynamic("Please select"), GlobalVariable.G_timeoutMiniShort)
		MobileDriver.delay(GlobalVariable.G_timeoutMiniShort)
		MobileDriver.tap(commonPage.lblDynamic("Done"), GlobalVariable.G_timeoutMiniShort)
		MobileDriver.delay(GlobalVariable.G_timeoutMiniShort)
		
		commonPage.scrollToEnd()
		
		MobileDriver.tap(commonPage.lblDynamic("Please select"), GlobalVariable.G_timeoutMiniShort)
		MobileDriver.delay(GlobalVariable.G_timeoutMiniShort)
		MobileDriver.tap(commonPage.lblDynamic("Done"), GlobalVariable.G_timeoutMiniShort)
		MobileDriver.delay(GlobalVariable.G_timeoutMiniShort)
		
		MobileDriver.tap(commonPage.lblDynamic("Please select"), GlobalVariable.G_timeoutMiniShort)
		MobileDriver.delay(GlobalVariable.G_timeoutMiniShort)
		MobileDriver.tap(commonPage.lblDynamic("Done"), GlobalVariable.G_timeoutMiniShort)
		MobileDriver.delay(GlobalVariable.G_timeoutMiniShort)
	}
	
	void fillInRecipientWesternUnionDetails() {
		MobileDriver.tap(commonPage.lblDynamic("Please select"), GlobalVariable.G_timeoutMiniShort)
		MobileDriver.tap(commonPage.lblContainDynamic("Malaysia"), GlobalVariable.G_timeoutMiniShort)
		Mobile.delay(GlobalVariable.G_timeoutMiniShort)
		
		MobileDriver.setText(commonPage.lblContainDynamic("Danial"), "Danial", GlobalVariable.G_timeoutMiniShort)
		MobileDriver.setText(commonPage.lblContainDynamic("Ariff"), "Ariff", GlobalVariable.G_timeoutMiniShort)
		MobileDriver.setText(commonPage.lblContainDynamic("3456"), "01234556789", GlobalVariable.G_timeoutMiniShort)
		MobileDriver.setText(commonPage.lblContainDynamic("Floor"), "Streetname1", GlobalVariable.G_timeoutMiniShort)
		MobileDriver.setText(commonPage.lblContainDynamic("Street name"), "Streetname2", GlobalVariable.G_timeoutMiniShort)
		
		Mobile.delay(GlobalVariable.G_timeoutMiniShort)
		Mobile.swipe(500, 1500, 500, 200)

		MobileDriver.setText(commonPage.lblContainDynamic("52200"), "52200", GlobalVariable.G_timeoutMiniShort)
		MobileDriver.setText(commonPage.lblContainDynamic("Kuala Lumpur"), "Kuala Lumpur",GlobalVariable.G_timeoutMiniShort)
		MobileDriver.setText(commonPage.lblContainDynamic("Persekutuan"), "Persekutuan", GlobalVariable.G_timeoutMiniShort)
		MobileDriver.setText(commonPage.lblContainDynamic("910102"), "910102035678", GlobalVariable.G_timeoutMiniShort)
		
		MobileDriver.tap(commonPage.lblContainDynamic("Jan"), GlobalVariable.G_timeoutMiniShort)
		MobileDriver.delay(GlobalVariable.G_timeoutMiniShort)
		MobileDriver.tap(commonPage.lblDynamic("Done"), GlobalVariable.G_timeoutMiniShort)
		MobileDriver.delay(GlobalVariable.G_timeoutMiniShort)
		
		MobileDriver.tap(commonPage.lblDynamic("Please select"), GlobalVariable.G_timeoutMiniShort)
		MobileDriver.delay(GlobalVariable.G_timeoutMiniShort)
		MobileDriver.tap(commonPage.lblContainDynamic("Malaysia"), GlobalVariable.G_timeoutMiniShort)
		MobileDriver.delay(GlobalVariable.G_timeoutMiniShort)
		
		MobileDriver.tap(commonPage.lblDynamic("Please select"), GlobalVariable.G_timeoutMiniShort)
		MobileDriver.delay(GlobalVariable.G_timeoutMiniShort)
		MobileDriver.tap(commonPage.lblContainDynamic("National ID Card"), GlobalVariable.G_timeoutMiniShort)
		MobileDriver.tap(commonPage.lblDynamic("Done"), GlobalVariable.G_timeoutMiniShort)
		MobileDriver.delay(GlobalVariable.G_timeoutMiniShort)
		
	}
	
	void selectFTTFav() {
		MobileDriver.tap(commonPage.lblContainDynamic("FTT"), GlobalVariable.G_timeoutMedium)
	}
	
	void selectMOTFav() {
		MobileDriver.tap(commonPage.lblDynamic("MOT"), GlobalVariable.G_timeoutMedium)
	}
	
	void selectWUFav() {
		MobileDriver.tap(commonPage.lblDynamic("WU"), GlobalVariable.G_timeoutMedium)
	}

	// ================================================
	// OVERSEAS OVERHAUL - NEW KEYWORDS
	// Rewrite for improved stability. Do not modify old code above this line.
	// ================================================

	/**
	 * [OVERHAUL] FTT Step 1 of 4 - Sender's details.
	 * Only selects nationality radio — address fields are pre-populated from user profile.
	 * Corresponds to: iPhone 13 mini - 12 (Step 1 of 4).
	 */
	void ovhFTTStep1SenderDetails(String nationality, FailureHandling flowControl = defaultFailureHandling) {
		'Wait for Step 1 page to load'
		MobileDriver.waitForElementPresent(commonPage.lblDynamic(UILblForeignTelegraphicTransfer), GlobalVariable.G_timeoutLong, flowControl)
		MobileDriver.waitForElementPresent(commonPage.lblDynamic(UILblPleaseFillInYourDetails), GlobalVariable.G_timeoutMedium, flowControl)
		MobileDriver.takeScreenshot()
		
		Mobile.delay(GlobalVariable.G_timeoutShort)

		'Select Nationality radio button'
		MobileDriver.tap(commonPage.lblContainDynamic(nationality), GlobalVariable.G_timeoutMedium, flowControl)
		Mobile.delay(GlobalVariable.G_timeoutMiniShort)
		MobileDriver.tap(commonPage.lblContainDynamic(nationality), GlobalVariable.G_timeoutMedium, flowControl)
		
		MobileDriver.takeScreenshot()

		'Tap Continue button'
		MobileDriver.tap(commonPage.lblDynamic(this.UITextContinue), GlobalVariable.G_timeoutLong, flowControl)
	}

	/**
	 * [OVERHAUL] FTT Step 2 of 4 - Fill in recipient's bank details.
	 * Fields: Bank name, Account number, Branch address, City.
	 * Corresponds to: iPhone 13 mini - 253 (Step 2 of 4).
	 */
	void ovhFTTStep2RecipientBankDetails(String bankName, String accountNumber, String branchAddress, String city, FailureHandling flowControl = defaultFailureHandling) {
		'Wait for Step 2 page to load'
		MobileDriver.waitForElementPresent(commonPage.lblDynamic(UILblPleaseFillInRecipientBankDetails), GlobalVariable.G_timeoutLong, flowControl)
		MobileDriver.takeScreenshot()

		'Fill Bank name - tap Please select dropdown, scroll to bank, tap Done'
		MobileDriver.tap(commonPage.imgSibling(UIDrdBankName), GlobalVariable.G_timeoutLong, flowControl)
		if (Mobile.waitForElementNotPresent(commonPage.lblDynamic(UIDone), GlobalVariable.G_timeoutMedium)) {
			MobileDriver.tap(commonPage.imgSibling(UIDrdBankName), GlobalVariable.G_timeoutLong, flowControl)
		}
		Mobile.scrollToText(bankName)
		MobileDriver.tap(commonPage.lblDynamic(bankName), GlobalVariable.G_timeoutLong, flowControl)
		MobileDriver.tap(commonPage.lblDynamic(UIDone), GlobalVariable.G_timeoutLong, flowControl)

		'Fill Account number'
		MobileDriver.scrollToText('Account number')
		MobileDriver.setText(commonPage.txtSiblingGroup('Account number'), accountNumber, GlobalVariable.G_timeoutLong, FailureHandling.STOP_ON_FAILURE)

		'Fill Branch address'
		MobileDriver.scrollToText(UILblBranchAddress)
		MobileDriver.setText(commonPage.txtSiblingGroup(UILblBranchAddress), branchAddress, GlobalVariable.G_timeoutLong, FailureHandling.STOP_ON_FAILURE)

		'Fill City'
		MobileDriver.scrollToText(UILblCity)
		MobileDriver.setText(commonPage.txtSiblingGroup(UILblCity), city, GlobalVariable.G_timeoutLong, FailureHandling.STOP_ON_FAILURE)
		MobileDriver.takeScreenshot()

		MobileDriver.tap(commonPage.lblContainDynamic(UITextContinue), GlobalVariable.G_timeoutMedium, FailureHandling.OPTIONAL)
		MobileDriver.tap(commonPage.lblContainDynamic(UITextContinue), GlobalVariable.G_timeoutMedium, FailureHandling.OPTIONAL)
		MobileDriver.tap(commonPage.lblContainDynamic(UITextContinue), GlobalVariable.G_timeoutMedium, FailureHandling.OPTIONAL)
		
	}

	/**
	 * [OVERHAUL] FTT Step 3 of 4 - Fill in recipient's details.
	 * Fields: Nationality, Name, IC/Passport/Biz Reg No., Address line 1, Address line 2, Country (dropdown), Mobile number, Email address (Optional).
	 * Corresponds to: iPhone 13 mini - 254 (Step 3 of 4).
	 */
	void ovhFTTStep3RecipientDetails(String nationality, String name, String icPassport, String addressLine1, String addressLine2, String country, String mobileNumber, String emailAddress = '', FailureHandling flowControl = defaultFailureHandling) {
		'Wait for Step 3 page to load'
		MobileDriver.waitForElementPresent(commonPage.lblDynamic(UILblPleaseFillInRecipientDetails), GlobalVariable.G_timeoutLong, flowControl)
		MobileDriver.takeScreenshot()

		'Select Nationality'
		MobileDriver.tap(commonPage.lblDynamic(nationality), GlobalVariable.G_timeoutMedium, flowControl)

		'Fill Name'
		MobileDriver.scrollToText(UILblName)
		MobileDriver.setText(commonPage.txtSiblingGroup(UILblName), name, GlobalVariable.G_timeoutLong, FailureHandling.STOP_ON_FAILURE)
		MobileDriver.tap(commonPage.lblDynamic(UILblName), GlobalVariable.G_timeoutMedium, flowControl)

		'Fill IC / Passport number / Biz registration No.'
		MobileDriver.scrollToText(UILblICPassportNumberBizRegistrationNo)
		MobileDriver.setText(commonPage.txtSiblingGroup(UILblICPassportNumberBizRegistrationNo), icPassport, GlobalVariable.G_timeoutLong, FailureHandling.STOP_ON_FAILURE)
		MobileDriver.tap(commonPage.lblDynamic(UILblICPassportNumberBizRegistrationNo), GlobalVariable.G_timeoutMedium, flowControl)

		'Fill Address line 1'
		MobileDriver.scrollToText(UILblAddressLine1)
		MobileDriver.setText(commonPage.txtSiblingGroup(UILblAddressLine1), addressLine1, GlobalVariable.G_timeoutLong, FailureHandling.STOP_ON_FAILURE)
		MobileDriver.tap(commonPage.lblDynamic(UILblAddressLine1), GlobalVariable.G_timeoutMedium, flowControl)

		'Fill Address line 2'
		MobileDriver.scrollToText(UILblAddressLine2)
		MobileDriver.setText(commonPage.txtSiblingGroup(UILblAddressLine2), addressLine2, GlobalVariable.G_timeoutLong, FailureHandling.STOP_ON_FAILURE)
		Mobile.delay(1)
		MobileDriver.tap(commonPage.lblDynamic(UILblAddressLine2), GlobalVariable.G_timeoutMedium, flowControl)

		'Select Country dropdown'
		commonPage.swipeByW3CAction(500, 1500, 500, 1000)
		MobileDriver.tap(commonPage.imgSiblingGroup(UILblCountry), GlobalVariable.G_timeoutLong, flowControl)
		if (!MobileDriver.waitForElementPresent(commonPage.lblDynamic('Select Country'), GlobalVariable.G_timeoutShort, flowControl)) {
			MobileDriver.tap(commonPage.imgSiblingGroup(UILblCountry), GlobalVariable.G_timeoutLong, flowControl)
		}
		Mobile.delay(GlobalVariable.G_timeoutMiniShort)
		MobileDriver.tap(commonPage.imgNoDynamic(2), GlobalVariable.G_timeoutMedium, flowControl)
		MobileDriver.setText(commonPage.txtEditNo(1), country, GlobalVariable.G_timeoutLong, flowControl)
		Mobile.delay(GlobalVariable.G_timeoutMiniShort)
		MobileDriver.tap(commonPage.imgNoDynamic(3), GlobalVariable.G_timeoutMedium, flowControl)
		if (Mobile.waitForElementPresent(commonPage.imgNoDynamic(3), GlobalVariable.G_timeoutMedium)) {
			MobileDriver.tap(commonPage.imgNoDynamic(3), GlobalVariable.G_timeoutMedium, flowControl)
		}

		'Fill Mobile number'
		MobileDriver.scrollToText(UILblMobileNumber)
		commonPage.scrollToEnd()
		Mobile.clearText(commonPage.txtSiblingGroup(UILblMobileNumber), GlobalVariable.G_timeoutLong, FailureHandling.OPTIONAL)
		MobileDriver.setText(commonPage.txtSiblingGroup(UILblMobileNumber), mobileNumber, GlobalVariable.G_timeoutLong, FailureHandling.STOP_ON_FAILURE)
		MobileDriver.tap(commonPage.lblDynamic(UILblMobileNumber), GlobalVariable.G_timeoutMedium, flowControl)

		'Fill Email address (Optional)'
		if (emailAddress != null && !emailAddress.isEmpty()) {
			MobileDriver.scrollToText(UILblEmailAddress)
			MobileDriver.setText(commonPage.txtSiblingGroup(UILblEmailAddress), emailAddress, GlobalVariable.G_timeoutLong, FailureHandling.STOP_ON_FAILURE)
			Mobile.delay(1)
			MobileDriver.tap(commonPage.lblDynamic(UILblEmailAddress), GlobalVariable.G_timeoutMedium, flowControl)
		}
		MobileDriver.takeScreenshot()

		'Tap Continue button'
		MobileDriver.scrollToText(UITextContinue)
		MobileDriver.tap(commonPage.lblContainDynamic(UITextContinue), GlobalVariable.G_timeoutMedium, FailureHandling.OPTIONAL)
		MobileDriver.tap(commonPage.lblContainDynamic(UITextContinue), GlobalVariable.G_timeoutMedium, FailureHandling.OPTIONAL)
		MobileDriver.tap(commonPage.lblContainDynamic(UITextContinue), GlobalVariable.G_timeoutMedium, FailureHandling.OPTIONAL)
		
	}

	/**
	 * [OVERHAUL] FTT Step 4 of 4 - Fill in transfer details.
	 * Fields: Purpose of transfer (dropdown), Sub-Purpose (dropdown, conditional), Relationship (dropdown, conditional),
	 *         Agent/Beneficiary bank fee (radio), Additional info (Optional).
	 * Corresponds to: iPhone 13 mini - 256 (Step 4 of 4).
	 */
	void ovhFTTStep4TransferDetails(String purposeOfTransfer, String subPurpose = '', String relationship = '', String bankFee = 'I will pay', String additionalInfo = '', FailureHandling flowControl = defaultFailureHandling) {
		'Wait for Step 4 page to load'
		MobileDriver.waitForElementPresent(commonPage.lblDynamic(UILblPleaseFillInTransferDetails), GlobalVariable.G_timeoutLong, flowControl)
		MobileDriver.takeScreenshot()
		Mobile.delay(GlobalVariable.G_timeoutMedium)

		'Select Purpose of transfer dropdown'
		MobileDriver.tap(commonPage.imgSiblingGroup(UILblPurposeOfTransfer), GlobalVariable.G_timeoutLong, flowControl)
		if (Mobile.waitForElementNotPresent(commonPage.lblDynamic(UIDone), GlobalVariable.G_timeoutShort)) {
			MobileDriver.tap(commonPage.imgSiblingGroup(UILblPurposeOfTransfer), GlobalVariable.G_timeoutMiniShort, FailureHandling.OPTIONAL)
		}
		Mobile.scrollToText(purposeOfTransfer)
		MobileDriver.tap(commonPage.lblDynamic(purposeOfTransfer), GlobalVariable.G_timeoutLong, flowControl)
		MobileDriver.tap(commonPage.lblDynamic(UIDone), GlobalVariable.G_timeoutLong, flowControl)
		Mobile.delay(GlobalVariable.G_timeoutMedium)

		'Select Sub-Purpose dropdown (conditional)'
		if (waitForElementPresent(commonPage.lblDynamic(UILblSubPurpose), GlobalVariable.G_timeoutMiniShort, FailureHandling.OPTIONAL)) {
			MobileDriver.scrollToText(UILblSubPurpose)
			MobileDriver.tap(commonPage.imgSiblingGroup(UILblSubPurpose), GlobalVariable.G_timeoutLong, flowControl)
			if (Mobile.waitForElementNotPresent(commonPage.lblDynamic(UIDone), GlobalVariable.G_timeoutShort)) {
				MobileDriver.tap(commonPage.imgSiblingGroup(UILblSubPurpose), GlobalVariable.G_timeoutLong, flowControl)
			}
			Mobile.scrollToText(subPurpose)
			MobileDriver.tap(commonPage.lblDynamic(subPurpose), GlobalVariable.G_timeoutLong, flowControl)
			MobileDriver.tap(commonPage.lblDynamic(UIDone), GlobalVariable.G_timeoutLong, flowControl)
			Mobile.delay(GlobalVariable.G_timeoutMedium)
		}

		'Select Relationship dropdown (conditional)'
		if (waitForElementPresent(commonPage.lblDynamic(UILblRelationshipStatus), GlobalVariable.G_timeoutMiniShort, FailureHandling.OPTIONAL)) {
			MobileDriver.scrollToText(UILblRelationshipStatus)
			MobileDriver.tap(commonPage.imgSiblingGroup(UILblRelationshipStatus), GlobalVariable.G_timeoutLong, flowControl)
			if (Mobile.waitForElementNotPresent(commonPage.lblDynamic(UIDone), GlobalVariable.G_timeoutShort)) {
				MobileDriver.tap(commonPage.imgSiblingGroup(UILblRelationshipStatus), GlobalVariable.G_timeoutLong, flowControl)
			}
			Mobile.scrollToText(relationship)
			MobileDriver.tap(commonPage.lblDynamic(relationship), GlobalVariable.G_timeoutLong, flowControl)
			MobileDriver.tap(commonPage.lblDynamic(UIDone), GlobalVariable.G_timeoutLong, flowControl)
		}
		Mobile.delay(GlobalVariable.G_timeoutMedium)

		'Select Agent/Beneficiary bank fee radio button'
		commonPage.swipeByW3CAction(500, 1500, 500, 1000)
		MobileDriver.tap(commonPage.lblContainDynamic(bankFee), GlobalVariable.G_timeoutLong, flowControl)
		MobileDriver.takeScreenshot()
		Mobile.delay(GlobalVariable.G_timeoutMedium)

		'Tap Continue button'
		MobileDriver.tap(commonPage.lblContainDynamic(UITextContinue), GlobalVariable.G_timeoutMedium, FailureHandling.OPTIONAL)
		MobileDriver.tap(commonPage.lblContainDynamic(UITextContinue), GlobalVariable.G_timeoutMedium, FailureHandling.OPTIONAL)
		MobileDriver.tap(commonPage.lblContainDynamic(UITextContinue), GlobalVariable.G_timeoutMedium, FailureHandling.OPTIONAL)
		
	}

	/**
	 * [OVERHAUL] FTT Confirmation screen - verify all sections and tap Confirm.
	 * Sections: Sender's Details, Recipient's Bank Details, Recipient's Details, Transfer Details.
	 * Corresponds to: Form/Confirmation screen. Button is 'Confirm' (not 'Confirm and Submit').
	 * 
	 * @param nationality Sender nationality (for verification)
	 * @param bankName Recipient bank name (for verification)
	 * @param accountNumber Recipient account number (for verification)
	 * @param bankAddress Recipient bank branch address (for verification)
	 * @param city Recipient bank city (for verification)
	 * @param recipientNationality Recipient nationality (for verification)
	 * @param recipientName Recipient name (for verification)
	 * @param icPassport Recipient IC/Passport number (for verification)
	 * @param addressLine1 Recipient address line 1 (for verification)
	 * @param addressLine2 Recipient address line 2 (for verification)
	 * @param country Recipient country (for verification)
	 * @param mobileNumber Recipient mobile number (for verification)
	 * @param emailAddress Recipient email (optional, for verification)
	 * @param purposeOfTransfer Purpose of transfer (for verification)
	 * @param subPurpose Sub-purpose of transfer (optional, for verification)
	 * @param relationship Relationship to recipient (optional, for verification)
	 * @param bankFee Bank fee option (for verification)
	 * @param additionalInfo Additional information (optional, for verification)
	 */
	void ovhFTTConfirm(
		String nationality = '',
		String bankName = '',
		String accountNumber = '',
		String bankAddress = '',
		String city = '',
		String recipientNationality = '',
		String recipientName = '',
		String icPassport = '',
		String addressLine1 = '',
		String addressLine2 = '',
		String country = '',
		String mobileNumber = '',
		String emailAddress = '',
		String purposeOfTransfer = '',
		String subPurpose = '',
		String relationship = '',
		String bankFee = '',
		String additionalInfo = '',
		boolean verifySST = false,
		FailureHandling flowControl = defaultFailureHandling) {
		
		'Wait for Confirmation page to load'
		MobileDriver.waitForElementPresent(commonPage.lblDynamic('Confirmation'), GlobalVariable.G_timeoutLong, flowControl)
		MobileDriver.waitForElementPresent(commonPage.lblContainDynamic('Please confirm the below details'), GlobalVariable.G_timeoutMedium, flowControl)
		MobileDriver.takeScreenshot()

		'Verify Sender\'s Details section'
		MobileDriver.waitForElementPresent(commonPage.lblDynamic("Sender's Details"), GlobalVariable.G_timeoutMedium, flowControl)
		if (nationality) {
			MobileDriver.waitForElementPresent(commonPage.lblDynamic(nationality), GlobalVariable.G_timeoutShort, flowControl)
		}
		commonPage.swipeByW3CAction(500, 1500, 500, 1000)
		MobileDriver.takeScreenshot()
		
		'Verify Recipient\'s Bank Details section'
		MobileDriver.waitForElementPresent(commonPage.lblDynamic("Recipient's Bank Details"), GlobalVariable.G_timeoutMedium, flowControl)
		if (bankName) {
			MobileDriver.waitForElementPresent(commonPage.lblDynamic(bankName), GlobalVariable.G_timeoutShort, flowControl)
		}
		if (accountNumber) {
			MobileDriver.waitForElementPresent(commonPage.lblDynamic(accountNumber), GlobalVariable.G_timeoutShort, flowControl)
		}
		if (bankAddress) {
			MobileDriver.waitForElementPresent(commonPage.lblDynamic(bankAddress), GlobalVariable.G_timeoutShort, flowControl)
		}
		if (city) {
			MobileDriver.waitForElementPresent(commonPage.lblDynamic(city), GlobalVariable.G_timeoutShort, flowControl)
		}
		commonPage.swipeByW3CAction(500, 1500, 500, 1000)
		MobileDriver.takeScreenshot()
		
		'Verify Recipient\'s Details section'
		MobileDriver.waitForElementPresent(commonPage.lblDynamic("Recipient's Details"), GlobalVariable.G_timeoutMedium, flowControl)
		if (recipientNationality) {
			MobileDriver.waitForElementPresent(commonPage.lblDynamic(recipientNationality), GlobalVariable.G_timeoutShort, flowControl)
		}
		if (recipientName) {
			MobileDriver.waitForElementPresent(commonPage.lblDynamic(recipientName), GlobalVariable.G_timeoutShort, flowControl)
		}
		if (icPassport) {
			MobileDriver.waitForElementPresent(commonPage.lblDynamic(icPassport), GlobalVariable.G_timeoutShort, flowControl)
		}
		if (addressLine1) {
			MobileDriver.waitForElementPresent(commonPage.lblDynamic(addressLine1), GlobalVariable.G_timeoutShort, flowControl)
		}
		if (addressLine2) {
			MobileDriver.waitForElementPresent(commonPage.lblDynamic(addressLine2), GlobalVariable.G_timeoutShort, flowControl)
		}
		if (country) {
			MobileDriver.waitForElementPresent(commonPage.lblDynamic(country), GlobalVariable.G_timeoutShort, flowControl)
		}
		if (mobileNumber) {
			MobileDriver.waitForElementPresent(commonPage.lblDynamic(mobileNumber), GlobalVariable.G_timeoutShort, flowControl)
		}
		if (emailAddress) {
			MobileDriver.waitForElementPresent(commonPage.lblDynamic(emailAddress), GlobalVariable.G_timeoutShort, flowControl)
		}
		commonPage.swipeByW3CAction(500, 1500, 500, 1000)
		MobileDriver.takeScreenshot()
		
		'Verify Transfer Details section'
		MobileDriver.waitForElementPresent(commonPage.lblDynamic(UILblTransferDetails), GlobalVariable.G_timeoutMedium, flowControl)
		if (purposeOfTransfer) {
			MobileDriver.waitForElementPresent(commonPage.lblDynamic(purposeOfTransfer), GlobalVariable.G_timeoutShort, flowControl)
		}
		if (subPurpose) {
			MobileDriver.waitForElementPresent(commonPage.lblDynamic(subPurpose), GlobalVariable.G_timeoutShort, flowControl)
		}
		if (relationship) {
			MobileDriver.waitForElementPresent(commonPage.lblDynamic(relationship), GlobalVariable.G_timeoutShort, flowControl)
		}
		if (bankFee) {
			MobileDriver.waitForElementPresent(commonPage.lblDynamic(bankFee), GlobalVariable.G_timeoutShort, flowControl)
		}
		if (additionalInfo) {
			MobileDriver.waitForElementPresent(commonPage.lblDynamic(additionalInfo), GlobalVariable.G_timeoutShort, flowControl)
		}
		MobileDriver.takeScreenshot()
		commonPage.swipeByW3CAction(500, 1500, 500, 1000)
		
		if (verifySST) {
			verifyServiceFeeSST()
			verifyBankFeeSST()
		}
		
		'Tap Confirm button'
		MobileDriver.tap(commonPage.lblContainDynamic("Submit"), GlobalVariable.G_timeoutMedium, FailureHandling.OPTIONAL)
		MobileDriver.tap(commonPage.lblContainDynamic("Submit"), GlobalVariable.G_timeoutMedium, FailureHandling.OPTIONAL)
		MobileDriver.tap(commonPage.lblContainDynamic("Submit"), GlobalVariable.G_timeoutMedium, FailureHandling.OPTIONAL)
	}

	// ============================================================
	// VCB OVERSEAS TRANSFER (MOT) - OVERHAUL KEYWORDS
	// ============================================================

	/**
	 * [OVERHAUL] MOT Step 1 of 3 - Recipient Bank Details.
	 * Simpler than FTT - only bank name and account number.
	 * Bank name is a dropdown interaction (tap, scroll, select, Done).
	 * 
	 * @param bankName Bank name to select from dropdown
	 * @param accountNumber Recipient account number
	 */
	void ovhMOTStep1RecipientBankDetails(String bankName, String accountNumber, FailureHandling flowControl = defaultFailureHandling) {
		'Wait for Step 1 - Recipient Bank Details page to load'
		MobileDriver.waitForElementPresent(commonPage.lblDynamic('Vcb Overseas Transfer'), GlobalVariable.G_timeoutLong, flowControl)
		MobileDriver.waitForElementPresent(commonPage.lblContainDynamic('Please enter your recipient details'), GlobalVariable.G_timeoutMedium, flowControl)
		MobileDriver.takeScreenshot()

		'Select Bank Name from dropdown'
		MobileDriver.tap(commonPage.imgSibling(UIDrdBankName), GlobalVariable.G_timeoutMedium, flowControl)
		if (!Mobile.waitForElementPresent(commonPage.lblDynamic(bankName), GlobalVariable.G_timeoutShort)) {
			Mobile.scrollToText(bankName)
		}
		MobileDriver.tap(commonPage.lblDynamic(bankName), GlobalVariable.G_timeoutMedium, flowControl)
		MobileDriver.tap(commonPage.lblDynamic('Done'), GlobalVariable.G_timeoutMedium, flowControl)

		'Enter Account Number'
		MobileDriver.setText(commonPage.txtSiblingGroup("Account number"), accountNumber, GlobalVariable.G_timeoutMedium, flowControl)
		MobileDriver.takeScreenshot()

		'Tap Continue'
		MobileDriver.tap(commonPage.lblContainDynamic(UITextContinue), GlobalVariable.G_timeoutMedium, FailureHandling.OPTIONAL)
		MobileDriver.tap(commonPage.lblContainDynamic(UITextContinue), GlobalVariable.G_timeoutMedium, FailureHandling.OPTIONAL)
	}

	/**
	 * [OVERHAUL] MOT Step 2 of 3 - Recipient Details.
	 * Fields: nationality (radio), name, IC/Passport, address line 1&2, country (dropdown with search), mobile, email (optional).
	 * 
	 * @param nationality 'Malaysian' or 'Non-Malaysian'
	 * @param name Recipient name
	 * @param icPassport IC/Passport number
	 * @param addressLine1 Address line 1
	 * @param addressLine2 Address line 2
	 * @param country Country name (dropdown with search)
	 * @param mobileNumber Mobile number
	 * @param emailAddress Email address (optional)
	 */
	void ovhMOTStep2RecipientDetails(
		String nationality,
		String name,
		String icPassport,
		String addressLine1,
		String addressLine2,
		String postCode,
		String country,
		String mobileNumber,
		String emailAddress = '',
		FailureHandling flowControl = defaultFailureHandling) {
		
		'Wait for Step 2 - Recipient Details page to load'
		MobileDriver.waitForElementPresent(commonPage.lblDynamic('Vcb Overseas Transfer'), GlobalVariable.G_timeoutLong, flowControl)
		MobileDriver.waitForElementPresent(commonPage.lblContainDynamic('Please enter your recipient details'), GlobalVariable.G_timeoutMedium, flowControl)
		MobileDriver.takeScreenshot()

		'Select Nationality'
		MobileDriver.tap(commonPage.lblDynamic(nationality), GlobalVariable.G_timeoutMedium, flowControl)

		'Fill Name'
		MobileDriver.scrollToText(UILblName)
		MobileDriver.setText(commonPage.txtSiblingGroup(UILblName), name, GlobalVariable.G_timeoutLong, FailureHandling.STOP_ON_FAILURE)
		MobileDriver.tap(commonPage.lblDynamic(UILblName), GlobalVariable.G_timeoutMedium, flowControl)

		'Fill IC / Passport number / Biz registration No.'
		MobileDriver.scrollToText(UILblICPassportNumberInput)
		MobileDriver.setText(commonPage.txtSiblingGroup(UILblICPassportNumberInput), icPassport, GlobalVariable.G_timeoutLong, FailureHandling.STOP_ON_FAILURE)
		MobileDriver.tap(commonPage.lblDynamic(UILblICPassportNumberInput), GlobalVariable.G_timeoutMedium, flowControl)

		'Fill Address line 1'
		MobileDriver.scrollToText(UILblAddressLine1)
		MobileDriver.setText(commonPage.txtSiblingGroup(UILblAddressLine1), addressLine1, GlobalVariable.G_timeoutLong, FailureHandling.STOP_ON_FAILURE)
		MobileDriver.tap(commonPage.lblDynamic(UILblAddressLine1), GlobalVariable.G_timeoutMedium, flowControl)

		'Fill Address line 2'
		MobileDriver.scrollToText(UILblAddressLine2)
		MobileDriver.setText(commonPage.txtSiblingGroup(UILblAddressLine2), addressLine2, GlobalVariable.G_timeoutLong, FailureHandling.STOP_ON_FAILURE)
		MobileDriver.tap(commonPage.lblDynamic(UILblAddressLine2), GlobalVariable.G_timeoutMedium, flowControl)

		'Fill Postcode'
		MobileDriver.scrollToText(UILblPostCode)
		MobileDriver.setText(commonPage.txtSiblingGroup(UILblPostCode), postCode, GlobalVariable.G_timeoutLong, FailureHandling.STOP_ON_FAILURE)
		MobileDriver.tap(commonPage.lblDynamic(UILblPostCode), GlobalVariable.G_timeoutMedium, flowControl)

		'Select Country dropdown'
		commonPage.swipeByW3CAction(500, 1400, 500, 700)
		MobileDriver.scrollToText(UILblCountry)
		MobileDriver.tap(commonPage.imgSiblingGroup(UILblCountry), GlobalVariable.G_timeoutLong, flowControl)
		if (!MobileDriver.waitForElementPresent(commonPage.lblDynamic('Select Country'), GlobalVariable.G_timeoutShort, flowControl)) {
			MobileDriver.tap(commonPage.imgSiblingGroup(UILblCountry), GlobalVariable.G_timeoutLong, flowControl)
		}
		Mobile.delay(GlobalVariable.G_timeoutMiniShort)
		MobileDriver.tap(commonPage.imgNoDynamic(2), GlobalVariable.G_timeoutMedium, flowControl)
		MobileDriver.setText(commonPage.txtEditNo(1), country, GlobalVariable.G_timeoutLong, flowControl)
		Mobile.delay(GlobalVariable.G_timeoutMiniShort)
		MobileDriver.tap(commonPage.imgNoDynamic(3), GlobalVariable.G_timeoutMedium, flowControl)
		if (Mobile.waitForElementPresent(commonPage.imgNoDynamic(3), GlobalVariable.G_timeoutMedium)) {
			MobileDriver.tap(commonPage.imgNoDynamic(3), GlobalVariable.G_timeoutMedium, flowControl)
		}

		'Fill Mobile number'
		MobileDriver.scrollToText(UILblMobileNumber)
		Mobile.clearText(commonPage.txtSiblingGroup(UILblMobileNumber), GlobalVariable.G_timeoutLong, FailureHandling.OPTIONAL)
		MobileDriver.setText(commonPage.txtSiblingGroup(UILblMobileNumber), mobileNumber, GlobalVariable.G_timeoutLong, FailureHandling.STOP_ON_FAILURE)
		MobileDriver.tap(commonPage.lblDynamic(UILblMobileNumber), GlobalVariable.G_timeoutMedium, flowControl)

		'Fill Email address (Optional)'
		if (emailAddress != null && !emailAddress.isEmpty()) {
			MobileDriver.scrollToText(UILblEmailAddress)
			MobileDriver.setText(commonPage.txtSiblingGroup(UILblEmailAddress), emailAddress, GlobalVariable.G_timeoutLong, FailureHandling.STOP_ON_FAILURE)
			MobileDriver.tap(commonPage.lblDynamic(UILblEmailAddress), GlobalVariable.G_timeoutMedium, flowControl)
		}
		MobileDriver.takeScreenshot()
		commonPage.swipeByW3CAction(500, 1500, 500, 1000)
		
		'Tap Continue button'
		MobileDriver.tap(commonPage.lblContainDynamic(UITextContinue), GlobalVariable.G_timeoutMedium, FailureHandling.OPTIONAL)
		MobileDriver.tap(commonPage.lblContainDynamic(UITextContinue), GlobalVariable.G_timeoutMedium, FailureHandling.OPTIONAL)
	}

	/**
	 * [OVERHAUL] MOT Step 3 of 3 - Transfer Details.
	 * Fields: purpose of transfer (dropdown), optional sub-purpose, optional relationship, optional additional info.
	 * Sub-purpose and relationship are conditional based on main purpose selection.
	 * 
	 * @param purposeOfTransfer Main purpose dropdown selection
	 * @param subPurpose Sub-purpose dropdown (appears conditionally, optional)
	 * @param relationship Relationship dropdown (appears conditionally, optional)
	 * @param additionalInfo Additional information text field (optional)
	 */
	void ovhMOTStep3TransferDetails(
		String purposeOfTransfer,
		String subPurpose = '',
		String relationship = '',
		String additionalInfo = '',
		FailureHandling flowControl = defaultFailureHandling) {
		
		'Wait for Step 3 - Transfer Details page to load'
		MobileDriver.waitForElementPresent(commonPage.lblDynamic('Vcb Overseas Transfer'), GlobalVariable.G_timeoutLong, flowControl)
		MobileDriver.waitForElementPresent(commonPage.lblContainDynamic('Please fill in transfer details'), GlobalVariable.G_timeoutMedium, flowControl)
		MobileDriver.takeScreenshot()

		'Select Purpose of Transfer'
		MobileDriver.tap(commonPage.lblDynamic(UIDrdPurposeOfTransfer), GlobalVariable.G_timeoutMedium, flowControl)
		Mobile.scrollToText(purposeOfTransfer)
		MobileDriver.tap(commonPage.lblDynamic(purposeOfTransfer), GlobalVariable.G_timeoutMedium, flowControl)
		MobileDriver.tap(commonPage.lblDynamic('Done'), GlobalVariable.G_timeoutMedium, flowControl)

		'Select Sub-purpose (if provided and field appears)'
		if (subPurpose) {
			Mobile.scrollToText(UIDrdSubPurpose)
			MobileDriver.tap(commonPage.lblDynamic(UIDrdSubPurpose), GlobalVariable.G_timeoutMedium, flowControl)
			Mobile.scrollToText(subPurpose)
			MobileDriver.tap(commonPage.lblDynamic(subPurpose), GlobalVariable.G_timeoutMedium, flowControl)
			MobileDriver.tap(commonPage.lblDynamic('Done'), GlobalVariable.G_timeoutMedium, flowControl)
		}

		'Select Relationship (if provided and field appears)'
		if (relationship) {
			Mobile.scrollToText(UIDrdRelationship)
			MobileDriver.tap(commonPage.imgSiblingGroup(UIDrdRelationship), GlobalVariable.G_timeoutMedium, flowControl)
			Mobile.scrollToText(relationship)
			MobileDriver.tap(commonPage.lblDynamic(relationship), GlobalVariable.G_timeoutMedium, flowControl)
			MobileDriver.tap(commonPage.lblDynamic('Done'), GlobalVariable.G_timeoutMedium, flowControl)
		}

		'Enter Additional info (optional)'
		if (additionalInfo) {
			Mobile.scrollToText(UILblAdditionalInfo)
			MobileDriver.tap(commonPage.txtSiblingGroup(UILblAdditionalInfo), GlobalVariable.G_timeoutMedium, flowControl)
			MobileDriver.setText(commonPage.txtSiblingGroup(UILblAdditionalInfo), additionalInfo, GlobalVariable.G_timeoutMedium, flowControl)
		}
		MobileDriver.takeScreenshot()

		'Tap Continue'
		MobileDriver.tap(commonPage.lblContainDynamic(UITextContinue), GlobalVariable.G_timeoutMedium, FailureHandling.OPTIONAL)
		MobileDriver.tap(commonPage.lblContainDynamic(UITextContinue), GlobalVariable.G_timeoutMedium, FailureHandling.OPTIONAL)
	}

	/**
	 * [OVERHAUL] MOT Confirmation screen - verify all sections and tap Agree & Confirm.
	 * Sections: Recipient's Bank Details, Recipient's Details, Transfer Details (3 sections, no Sender's Details).
	 * Button text is 'Agree & Confirm' (different from FTT's 'Confirm').
	 * 
	 * @param bankName Bank name (for verification)
	 * @param accountNumber Account number (for verification)
	 * @param nationality Recipient nationality (for verification)
	 * @param recipientName Recipient name (for verification)
	 * @param icPassport IC/Passport number (for verification)
	 * @param addressLine1 Address line 1 (for verification)
	 * @param addressLine2 Address line 2 (for verification)
	 * @param country Country (for verification)
	 * @param mobileNumber Mobile number (for verification)
	 * @param emailAddress Email address (optional, for verification)
	 * @param purposeOfTransfer Purpose of transfer (for verification)
	 * @param subPurpose Sub-purpose (optional, for verification)
	 * @param relationship Relationship (optional, for verification)
	 * @param additionalInfo Additional info (optional, for verification)
	 */
	void ovhMOTConfirm(
		String bankName = '',
		String accountNumber = '',
		String nationality = '',
		String recipientName = '',
		String icPassport = '',
		String addressLine1 = '',
		String addressLine2 = '',
		String country = '',
		String mobileNumber = '',
		String emailAddress = '',
		String purposeOfTransfer = '',
		String subPurpose = '',
		String relationship = '',
		String additionalInfo = '',
		FailureHandling flowControl = defaultFailureHandling) {
		
		'Wait for Confirmation page to load'
		MobileDriver.waitForElementPresent(commonPage.lblDynamic('Confirmation'), GlobalVariable.G_timeoutLong, flowControl)
		MobileDriver.waitForElementPresent(commonPage.lblContainDynamic('Please confirm the details below'), GlobalVariable.G_timeoutMedium, flowControl)
		MobileDriver.takeScreenshot()

		'Verify Recipient\'s Bank Details section'
		MobileDriver.waitForElementPresent(commonPage.lblDynamic("Recipient's Bank Details"), GlobalVariable.G_timeoutMedium, flowControl)
		if (bankName) {
			MobileDriver.waitForElementPresent(commonPage.lblDynamic(bankName), GlobalVariable.G_timeoutShort, flowControl)
		}
		if (accountNumber) {
			MobileDriver.waitForElementPresent(commonPage.lblDynamic(accountNumber), GlobalVariable.G_timeoutShort, flowControl)
		}
		commonPage.swipeByW3CAction(500, 1500, 500, 1000)
		MobileDriver.takeScreenshot()
		
		'Verify Recipient\'s Details section'
		MobileDriver.waitForElementPresent(commonPage.lblDynamic("Recipient's Details"), GlobalVariable.G_timeoutMedium, flowControl)
		if (nationality) {
			MobileDriver.waitForElementPresent(commonPage.lblDynamic(nationality), GlobalVariable.G_timeoutShort, flowControl)
		}
		if (recipientName) {
			MobileDriver.waitForElementPresent(commonPage.lblDynamic(recipientName), GlobalVariable.G_timeoutShort, flowControl)
		}
		if (icPassport) {
			MobileDriver.waitForElementPresent(commonPage.lblDynamic(icPassport), GlobalVariable.G_timeoutShort, flowControl)
		}
		if (addressLine1) {
			MobileDriver.waitForElementPresent(commonPage.lblDynamic(addressLine1), GlobalVariable.G_timeoutShort, flowControl)
		}
		if (addressLine2) {
			MobileDriver.waitForElementPresent(commonPage.lblDynamic(addressLine2), GlobalVariable.G_timeoutShort, flowControl)
		}
		if (country) {
			MobileDriver.waitForElementPresent(commonPage.lblDynamic(country), GlobalVariable.G_timeoutShort, flowControl)
		}
		if (mobileNumber) {
			MobileDriver.waitForElementPresent(commonPage.lblDynamic(mobileNumber), GlobalVariable.G_timeoutShort, flowControl)
		}
		if (emailAddress) {
			MobileDriver.waitForElementPresent(commonPage.lblDynamic(emailAddress), GlobalVariable.G_timeoutShort, flowControl)
		}
		commonPage.swipeByW3CAction(500, 1500, 500, 1000)
		MobileDriver.takeScreenshot()
		
		'Verify Transfer Details section'
		MobileDriver.waitForElementPresent(commonPage.lblDynamic(UILblTransferDetails), GlobalVariable.G_timeoutMedium, flowControl)
		if (purposeOfTransfer) {
			MobileDriver.waitForElementPresent(commonPage.lblDynamic(purposeOfTransfer), GlobalVariable.G_timeoutShort, flowControl)
		}
		if (subPurpose) {
			MobileDriver.waitForElementPresent(commonPage.lblDynamic(subPurpose), GlobalVariable.G_timeoutShort, flowControl)
		}
		if (relationship) {
			MobileDriver.waitForElementPresent(commonPage.lblDynamic(relationship), GlobalVariable.G_timeoutShort, flowControl)
		}
		if (additionalInfo) {
			MobileDriver.waitForElementPresent(commonPage.lblDynamic(additionalInfo), GlobalVariable.G_timeoutShort, flowControl)
		}
		MobileDriver.takeScreenshot()
		commonPage.swipeByW3CAction(500, 1500, 500, 1000)

		'Tap Agree & Confirm button'
		MobileDriver.tap(commonPage.lblContainDynamic('Confirm and Submit'), GlobalVariable.G_timeoutMedium, FailureHandling.OPTIONAL)
		MobileDriver.tap(commonPage.lblContainDynamic('Confirm and Submit'), GlobalVariable.G_timeoutMedium, FailureHandling.OPTIONAL)
		MobileDriver.tap(commonPage.lblContainDynamic('Confirm and Submit'), GlobalVariable.G_timeoutMedium, FailureHandling.OPTIONAL)
		
	}

	// ============================================================
	// WESTERN UNION (WU) - OVERHAUL KEYWORDS
	// ============================================================

	/**
	 * [OVERHAUL] WU Step 1 of 5 - Sender's Details.
	 * Most fields (Sender name, Address, Postcode, City, State, Country, Mobile) are pre-populated from user profile.
	 * Only Citizenship/Permanent resident (radio), Promo code (Optional), and Email address (Optional) need input.
	 * 
	 * @param citizenship 'Malaysian' or 'Non-Malaysian'
	 * @param promoCode Promo code (optional)
	 * @param emailAddress Sender email address (optional)
	 */
	void ovhWUStep1SenderDetails(
		String citizenship,
		String promoCode = '',
		String emailAddress = '',
		FailureHandling flowControl = defaultFailureHandling) {

		'Wait for Step 1 - Sender Details page to load'
		MobileDriver.waitForElementPresent(commonPage.lblDynamic('Western Union'), GlobalVariable.G_timeoutLong, flowControl)
		MobileDriver.waitForElementPresent(commonPage.lblContainDynamic('Please fill in your details'), GlobalVariable.G_timeoutMedium, flowControl)
		MobileDriver.takeScreenshot()

		'Fill Promo code (Optional)'
		if (promoCode) {
			MobileDriver.scrollToText('Promo code (Optional)')
			MobileDriver.setText(commonPage.txtSiblingGroup('Promo code (Optional)'), promoCode, GlobalVariable.G_timeoutLong, FailureHandling.STOP_ON_FAILURE)
			MobileDriver.tap(commonPage.lblDynamic('Promo code (Optional)'), GlobalVariable.G_timeoutMedium, flowControl)
		}

		'Select Citizenship/Permanent resident'
		MobileDriver.scrollToText('Citizenship/Permanent resident')
		MobileDriver.tap(commonPage.lblDynamic(citizenship), GlobalVariable.G_timeoutMedium, flowControl)

		'Fill Email address (Optional)'
		if (emailAddress) {
			MobileDriver.scrollToText(UILblEmailAddress)
			MobileDriver.setText(commonPage.txtSiblingGroup(UILblEmailAddress), emailAddress, GlobalVariable.G_timeoutLong, FailureHandling.STOP_ON_FAILURE)
			MobileDriver.tap(commonPage.lblDynamic(UILblEmailAddress), GlobalVariable.G_timeoutMedium, flowControl)
		}
		MobileDriver.takeScreenshot()

		'Tap Next button'
		MobileDriver.tap(commonPage.lblContainDynamic(UILblNext), GlobalVariable.G_timeoutMedium, FailureHandling.OPTIONAL)
		MobileDriver.tap(commonPage.lblContainDynamic(UILblNext), GlobalVariable.G_timeoutMedium, FailureHandling.OPTIONAL)
	}

	/**
	 * [OVERHAUL] WU Step 2 of 5 - Temporary Address.
	 * If sameAsPrimary is true, taps the "same as primary address" checkbox and skips all address fields.
	 * If false, fills temporary address fields.
	 * 
	 * @param sameAsPrimary Whether temporary address is same as primary (default: true)
	 * @param tempAddressLine1 Temporary address line 1
	 * @param tempAddressLine2 Temporary address line 2
	 * @param tempPostCode Temporary postcode
	 * @param tempCity Temporary city
	 * @param tempStateProvince Temporary state/province
	 */
	void ovhWUStep2TempAddress(
		boolean sameAsPrimary = true,
		String tempAddressLine1 = '',
		String tempAddressLine2 = '',
		String tempPostCode = '',
		String tempCity = '',
		String tempStateProvince = '',
		FailureHandling flowControl = defaultFailureHandling) {

		'Wait for Step 2 - Temporary Address page to load'
		MobileDriver.waitForElementPresent(commonPage.lblDynamic('Western Union'), GlobalVariable.G_timeoutLong, flowControl)
		MobileDriver.waitForElementPresent(commonPage.lblContainDynamic('Please fill in your details'), GlobalVariable.G_timeoutMedium, flowControl)
		MobileDriver.takeScreenshot()

		if (sameAsPrimary) {
			'Tap same as primary address checkbox'
			MobileDriver.tap(commonPage.lblContainDynamic('My temporary address is the same as my primary address'), GlobalVariable.G_timeoutLong, flowControl)
		} else {
			'Fill Temporary address line 1'
			MobileDriver.scrollToText('Temporary address line 1')
			MobileDriver.setText(commonPage.txtSiblingGroup('Temporary address line 1'), tempAddressLine1, GlobalVariable.G_timeoutLong, FailureHandling.STOP_ON_FAILURE)
			MobileDriver.tap(commonPage.lblDynamic('Temporary address line 1'), GlobalVariable.G_timeoutMedium, flowControl)

			'Fill Temporary address line 2'
			MobileDriver.scrollToText('Temporary address line 2')
			MobileDriver.setText(commonPage.txtSiblingGroup('Temporary address line 2'), tempAddressLine2, GlobalVariable.G_timeoutLong, FailureHandling.STOP_ON_FAILURE)
			MobileDriver.tap(commonPage.lblDynamic('Temporary address line 2'), GlobalVariable.G_timeoutMedium, flowControl)

			'Fill Postcode'
			MobileDriver.scrollToText(UILblPostCode)
			MobileDriver.setText(commonPage.txtSiblingGroup(UILblPostCode), tempPostCode, GlobalVariable.G_timeoutLong, FailureHandling.STOP_ON_FAILURE)
			MobileDriver.tap(commonPage.lblDynamic(UILblPostCode), GlobalVariable.G_timeoutMedium, flowControl)

			'Fill City'
			MobileDriver.scrollToText(UILblCity)
			MobileDriver.setText(commonPage.txtSiblingGroup(UILblCity), tempCity, GlobalVariable.G_timeoutLong, FailureHandling.STOP_ON_FAILURE)
			MobileDriver.tap(commonPage.lblDynamic(UILblCity), GlobalVariable.G_timeoutMedium, flowControl)

			'Fill State/Province'
			MobileDriver.scrollToText('State/Province')
			MobileDriver.setText(commonPage.txtSiblingGroup('State/Province'), tempStateProvince, GlobalVariable.G_timeoutLong, FailureHandling.STOP_ON_FAILURE)
			MobileDriver.tap(commonPage.lblDynamic('State/Province'), GlobalVariable.G_timeoutMedium, flowControl)
		}
		MobileDriver.takeScreenshot()

		'Tap Next button'
		MobileDriver.tap(commonPage.lblContainDynamic(UITextContinue), GlobalVariable.G_timeoutMedium, FailureHandling.OPTIONAL)
		MobileDriver.tap(commonPage.lblContainDynamic(UITextContinue), GlobalVariable.G_timeoutMedium, FailureHandling.OPTIONAL)
	}

	/**
	 * [OVERHAUL] WU Step 3 of 5 - Sender ID & Employment Details.
	 * ID type/number/issuing country/date of birth are pre-populated from user profile; pass empty string to skip.
	 * Occupation, employment level, relationship and source of funds are required dropdowns.
	 * 
	 * @param idType ID type dropdown (optional, pre-filled from profile if empty)
	 * @param idNumber ID number text field (optional, pre-filled from profile if empty)
	 * @param idIssuingCountry ID issuing country dropdown (optional)
	 * @param dateOfBirth Date of birth — taps field and accepts pre-filled picker value if empty
	 * @param occupation Occupation dropdown
	 * @param employmentPositionLevel Employment Position Level dropdown
	 * @param relationshipToBeneficiary Relationship to beneficiary dropdown
	 * @param countryOfBirth Country of birth dropdown (optional, pre-filled if empty)
	 * @param countryOfCitizenship Country of citizenship dropdown (optional, pre-filled if empty)
	 * @param sourceOfFunds Source of funds dropdown
	 */
	void ovhWUStep3SenderIDEmployment(
		String idType = '',
		String idNumber = '',
		String idIssuingCountry = '',
		String dateOfBirth = '',
		String occupation = '',
		String employmentPositionLevel = '',
		String relationshipToBeneficiary = '',
		String countryOfBirth = '',
		String countryOfCitizenship = '',
		String sourceOfFunds = '',
		FailureHandling flowControl = defaultFailureHandling) {

		'Wait for Step 3 - Sender ID & Employment page to load'
		MobileDriver.waitForElementPresent(commonPage.lblDynamic('Western Union'), GlobalVariable.G_timeoutLong, flowControl)
		MobileDriver.waitForElementPresent(commonPage.lblContainDynamic('Please fill in your details'), GlobalVariable.G_timeoutMedium, flowControl)
		MobileDriver.takeScreenshot()

		'Select ID type dropdown (if provided)'
		if (idType) {
			MobileDriver.scrollToText('ID type')
			MobileDriver.tap(commonPage.imgSiblingGroup('ID type'), GlobalVariable.G_timeoutLong, flowControl)
			if (Mobile.waitForElementNotPresent(commonPage.lblDynamic(UIDone), GlobalVariable.G_timeoutShort, FailureHandling.OPTIONAL)) {
				MobileDriver.tap(commonPage.imgSiblingGroup('ID type'), GlobalVariable.G_timeoutLong, flowControl)
			}
			Mobile.scrollToText(idType)
			MobileDriver.tap(commonPage.lblDynamic(idType), GlobalVariable.G_timeoutLong, flowControl)
			MobileDriver.tap(commonPage.lblDynamic(UIDone), GlobalVariable.G_timeoutLong, flowControl)
		}

		'Fill ID number (if provided)'
		if (idNumber) {
			MobileDriver.scrollToText('ID number')
			MobileDriver.setText(commonPage.txtSiblingGroup('ID number'), idNumber, GlobalVariable.G_timeoutLong, FailureHandling.STOP_ON_FAILURE)
			MobileDriver.tap(commonPage.lblDynamic('ID number'), GlobalVariable.G_timeoutMedium, flowControl)
		}

		'Select ID issuing country dropdown (if provided)'
		if (idIssuingCountry) {
			MobileDriver.scrollToText('ID issuing country')
			MobileDriver.tap(commonPage.imgSiblingGroup('ID issuing country'), GlobalVariable.G_timeoutLong, flowControl)
			if (Mobile.waitForElementNotPresent(commonPage.lblDynamic(UIDone), GlobalVariable.G_timeoutShort, FailureHandling.OPTIONAL)) {
				MobileDriver.tap(commonPage.imgSiblingGroup('ID issuing country'), GlobalVariable.G_timeoutLong, flowControl)
			}
			Mobile.scrollToText(idIssuingCountry)
			MobileDriver.tap(commonPage.lblDynamic(idIssuingCountry), GlobalVariable.G_timeoutLong, flowControl)
			MobileDriver.tap(commonPage.lblDynamic(UIDone), GlobalVariable.G_timeoutLong, flowControl)
		}

		'Accept date of birth (tap and dismiss picker)'
		MobileDriver.scrollToText(UILblDateOfBirth)
		MobileDriver.tap(commonPage.txtSiblingGroup(UILblDateOfBirth), GlobalVariable.G_timeoutMedium, FailureHandling.OPTIONAL)
		if (Mobile.waitForElementPresent(commonPage.lblDynamic(UIDone), GlobalVariable.G_timeoutShort)) {
			MobileDriver.tap(commonPage.lblDynamic(UIDone), GlobalVariable.G_timeoutMedium, flowControl)
		}

		'Select Occupation dropdown'
		if (occupation) {
			MobileDriver.scrollToText('Occupation')
			commonPage.swipeByW3CAction(500, 1500, 500, 1200)
			MobileDriver.tap(commonPage.imgSiblingGroup('Occupation'), GlobalVariable.G_timeoutLong, flowControl)
			if (!Mobile.waitForElementPresent(commonPage.lblDynamic(UIDone), GlobalVariable.G_timeoutMedium, FailureHandling.OPTIONAL)) {
				MobileDriver.scrollToText('Occupation')
				MobileDriver.tap(commonPage.imgSiblingGroup('Occupation'), GlobalVariable.G_timeoutLong, flowControl)
				if (!Mobile.waitForElementPresent(commonPage.lblDynamic(UIDone), GlobalVariable.G_timeoutMedium, FailureHandling.OPTIONAL)) {
					MobileDriver.tap(commonPage.lblContainDynamic('select'), GlobalVariable.G_timeoutMedium, FailureHandling.OPTIONAL)
					Mobile.waitForElementPresent(commonPage.lblDynamic(UIDone), GlobalVariable.G_timeoutMedium, FailureHandling.OPTIONAL)
				}
			}
			if (!Mobile.waitForElementPresent(commonPage.lblDynamic(occupation), GlobalVariable.G_timeoutShort, FailureHandling.OPTIONAL)) {
				Mobile.scrollToText(occupation)
			}
			MobileDriver.tap(commonPage.lblDynamic(occupation), GlobalVariable.G_timeoutLong, flowControl)
			MobileDriver.tap(commonPage.lblDynamic(UIDone), GlobalVariable.G_timeoutLong, flowControl)
		}

		'Select Employment Position Level dropdown'
		if (employmentPositionLevel) {
			MobileDriver.scrollToText('Employment Position Level')
			MobileDriver.tap(commonPage.imgSiblingGroup('Employment Position Level'), GlobalVariable.G_timeoutLong, flowControl)
			if (!Mobile.waitForElementPresent(commonPage.lblDynamic(UIDone), GlobalVariable.G_timeoutMedium, FailureHandling.OPTIONAL)) {
				MobileDriver.scrollToText('Employment Position Level')
				MobileDriver.tap(commonPage.imgSiblingGroup('Employment Position Level'), GlobalVariable.G_timeoutLong, flowControl)
				if (!Mobile.waitForElementPresent(commonPage.lblDynamic(UIDone), GlobalVariable.G_timeoutMedium, FailureHandling.OPTIONAL)) {
					MobileDriver.tap(commonPage.lblContainDynamic('select'), GlobalVariable.G_timeoutMedium, FailureHandling.OPTIONAL)
					Mobile.waitForElementPresent(commonPage.lblDynamic(UIDone), GlobalVariable.G_timeoutMedium, FailureHandling.OPTIONAL)
				}
			}
			if (!Mobile.waitForElementPresent(commonPage.lblDynamic(employmentPositionLevel), GlobalVariable.G_timeoutShort, FailureHandling.OPTIONAL)) {
				Mobile.scrollToText(employmentPositionLevel)
			}
			MobileDriver.tap(commonPage.lblDynamic(employmentPositionLevel), GlobalVariable.G_timeoutLong, flowControl)
			MobileDriver.tap(commonPage.lblDynamic(UIDone), GlobalVariable.G_timeoutLong, flowControl)
		}

		'Select Relationship to beneficiary dropdown'
		if (relationshipToBeneficiary) {
			MobileDriver.scrollToText('Relationship to beneficiary')
			MobileDriver.tap(commonPage.imgSiblingGroup('Relationship to beneficiary'), GlobalVariable.G_timeoutLong, flowControl)
			if (!Mobile.waitForElementPresent(commonPage.lblDynamic(UIDone), GlobalVariable.G_timeoutMedium, FailureHandling.OPTIONAL)) {
				MobileDriver.scrollToText('Relationship to beneficiary')
				MobileDriver.tap(commonPage.imgSiblingGroup('Relationship to beneficiary'), GlobalVariable.G_timeoutLong, flowControl)
				if (!Mobile.waitForElementPresent(commonPage.lblDynamic(UIDone), GlobalVariable.G_timeoutMedium, FailureHandling.OPTIONAL)) {
					MobileDriver.tap(commonPage.lblContainDynamic('select'), GlobalVariable.G_timeoutMedium, FailureHandling.OPTIONAL)
					Mobile.waitForElementPresent(commonPage.lblDynamic(UIDone), GlobalVariable.G_timeoutMedium, FailureHandling.OPTIONAL)
				}
			}
			if (!Mobile.waitForElementPresent(commonPage.lblDynamic(relationshipToBeneficiary), GlobalVariable.G_timeoutShort, FailureHandling.OPTIONAL)) {
				Mobile.scrollToText(relationshipToBeneficiary)
			}
			MobileDriver.tap(commonPage.lblDynamic(relationshipToBeneficiary), GlobalVariable.G_timeoutLong, flowControl)
			MobileDriver.tap(commonPage.lblDynamic(UIDone), GlobalVariable.G_timeoutLong, flowControl)
		}

		'Select Country of birth dropdown (if provided)'
		if (countryOfBirth) {
			MobileDriver.scrollToText('Country of birth')
			MobileDriver.tap(commonPage.imgSiblingGroup('Country of birth'), GlobalVariable.G_timeoutLong, flowControl)
			if (!Mobile.waitForElementPresent(commonPage.lblDynamic(UIDone), GlobalVariable.G_timeoutMedium, FailureHandling.OPTIONAL)) {
				MobileDriver.scrollToText('Country of birth')
				MobileDriver.tap(commonPage.imgSiblingGroup('Country of birth'), GlobalVariable.G_timeoutLong, flowControl)
				if (!Mobile.waitForElementPresent(commonPage.lblDynamic(UIDone), GlobalVariable.G_timeoutMedium, FailureHandling.OPTIONAL)) {
					MobileDriver.tap(commonPage.lblContainDynamic('select'), GlobalVariable.G_timeoutMedium, FailureHandling.OPTIONAL)
					Mobile.waitForElementPresent(commonPage.lblDynamic(UIDone), GlobalVariable.G_timeoutMedium, FailureHandling.OPTIONAL)
				}
			}
			if (!Mobile.waitForElementPresent(commonPage.lblDynamic(countryOfBirth), GlobalVariable.G_timeoutShort, FailureHandling.OPTIONAL)) {
				Mobile.scrollToText(countryOfBirth)
			}
			MobileDriver.tap(commonPage.lblDynamic(countryOfBirth), GlobalVariable.G_timeoutLong, flowControl)
			MobileDriver.tap(commonPage.lblDynamic(UIDone), GlobalVariable.G_timeoutLong, flowControl)
		}

		'Select Country of citizenship dropdown (if provided)'
		if (countryOfCitizenship) {
			MobileDriver.scrollToText('Country of citizenship')
			MobileDriver.tap(commonPage.imgSiblingGroup('Country of citizenship'), GlobalVariable.G_timeoutLong, flowControl)
			if (!Mobile.waitForElementPresent(commonPage.lblDynamic(UIDone), GlobalVariable.G_timeoutMedium, FailureHandling.OPTIONAL)) {
				MobileDriver.scrollToText('Country of citizenship')
				MobileDriver.tap(commonPage.imgSiblingGroup('Country of citizenship'), GlobalVariable.G_timeoutLong, flowControl)
				if (!Mobile.waitForElementPresent(commonPage.lblDynamic(UIDone), GlobalVariable.G_timeoutMedium, FailureHandling.OPTIONAL)) {
					MobileDriver.tap(commonPage.lblContainDynamic('select'), GlobalVariable.G_timeoutMedium, FailureHandling.OPTIONAL)
					Mobile.waitForElementPresent(commonPage.lblDynamic(UIDone), GlobalVariable.G_timeoutMedium, FailureHandling.OPTIONAL)
				}
			}
			if (!Mobile.waitForElementPresent(commonPage.lblDynamic(countryOfCitizenship), GlobalVariable.G_timeoutShort, FailureHandling.OPTIONAL)) {
				Mobile.scrollToText(countryOfCitizenship)
			}
			MobileDriver.tap(commonPage.lblDynamic(countryOfCitizenship), GlobalVariable.G_timeoutLong, flowControl)
			MobileDriver.tap(commonPage.lblDynamic(UIDone), GlobalVariable.G_timeoutLong, flowControl)
		}
		
		Mobile.delay(GlobalVariable.G_timeoutMiniShort)

		'Select Source of funds dropdown'
		if (sourceOfFunds) {
			commonPage.swipeByW3CAction(500, 1500, 500, 1000)
			MobileDriver.tap(commonPage.imgSiblingGroup('Source of funds'), GlobalVariable.G_timeoutLong, flowControl)
			if (!Mobile.waitForElementPresent(commonPage.lblDynamic(UIDone), GlobalVariable.G_timeoutMedium, FailureHandling.OPTIONAL)) {
				MobileDriver.scrollToText('Source of funds')
				MobileDriver.tap(commonPage.imgSiblingGroup('Source of funds'), GlobalVariable.G_timeoutLong, flowControl)
				if (!Mobile.waitForElementPresent(commonPage.lblDynamic(UIDone), GlobalVariable.G_timeoutMedium, FailureHandling.OPTIONAL)) {
					MobileDriver.tap(commonPage.lblContainDynamic('select'), GlobalVariable.G_timeoutMedium, FailureHandling.OPTIONAL)
					Mobile.waitForElementPresent(commonPage.lblDynamic(UIDone), GlobalVariable.G_timeoutMedium, FailureHandling.OPTIONAL)
				}
			}
			if (!Mobile.waitForElementPresent(commonPage.lblDynamic(sourceOfFunds), GlobalVariable.G_timeoutShort, FailureHandling.OPTIONAL)) {
				Mobile.scrollToText(sourceOfFunds)
			}
			MobileDriver.tap(commonPage.lblDynamic(sourceOfFunds), GlobalVariable.G_timeoutLong, flowControl)
			MobileDriver.tap(commonPage.lblDynamic(UIDone), GlobalVariable.G_timeoutLong, flowControl)
		}

		MobileDriver.takeScreenshot()

		'Tap Next button'
		MobileDriver.tap(commonPage.lblContainDynamic(UITextContinue), GlobalVariable.G_timeoutMedium, FailureHandling.OPTIONAL)
		MobileDriver.tap(commonPage.lblContainDynamic(UITextContinue), GlobalVariable.G_timeoutMedium, FailureHandling.OPTIONAL)
	}

	/**
	 * [OVERHAUL] WU Step 4 of 5 - Recipient's Details.
	 * Fields: First name, Last name, Receiver country & code (dropdown), Mobile number,
	 *         Street line 1, Street line 2, Postcode, City (Destination), State, Country (dropdown),
	 *         Email address (Optional), ID type Optional (dropdown), ID number (Optional), Date of birth (Optional).
	 * 
	 * @param firstName Recipient first name
	 * @param lastName Recipient last name
	 * @param receiverCountryCode Receiver country & code dropdown value (e.g. 'Malaysia')
	 * @param mobileNumber Recipient mobile number
	 * @param streetLine1 Street line 1
	 * @param streetLine2 Street line 2
	 * @param postCode Postcode
	 * @param city City (Destination)
	 * @param state State
	 * @param country Country dropdown value
	 * @param emailAddress Email address (optional)
	 * @param idTypeOptional ID type (optional dropdown)
	 * @param idNumberOptional ID number (optional text)
	 * @param dateOfBirth Date of birth (optional)
	 */
	void ovhWUStep4RecipientDetails(
		String firstName,
		String lastName,
		String receiverCountryCode,
		String mobileNumber,
		String streetLine1,
		String streetLine2,
		String postCode,
		String city,
		String state,
		String country,
		String emailAddress = '',
		String idTypeOptional = '',
		String idNumberOptional = '',
		String dateOfBirth = '',
		FailureHandling flowControl = defaultFailureHandling) {

		'Wait for Step 4 - Recipient Details page to load'
		MobileDriver.waitForElementPresent(commonPage.lblDynamic('Western Union'), GlobalVariable.G_timeoutLong, flowControl)
		MobileDriver.waitForElementPresent(commonPage.lblContainDynamic("Please fill in recipient's details"), GlobalVariable.G_timeoutMedium, flowControl)
		MobileDriver.takeScreenshot()

		'Fill First name'
		MobileDriver.scrollToText('First Name')
		MobileDriver.setText(commonPage.lblContainDynamic('Danial'), firstName, GlobalVariable.G_timeoutLong, FailureHandling.STOP_ON_FAILURE)
		MobileDriver.tap(commonPage.lblDynamic('First Name'), GlobalVariable.G_timeoutMedium, flowControl)

		'Fill Last name'
		MobileDriver.scrollToText('Last Name')
		MobileDriver.setText(commonPage.lblContainDynamic('Ariff'), lastName, GlobalVariable.G_timeoutLong, FailureHandling.STOP_ON_FAILURE)
		MobileDriver.tap(commonPage.lblDynamic('Last Name'), GlobalVariable.G_timeoutMedium, flowControl)

		'Select Receiver country & code - opens Select Country search screen'
		MobileDriver.scrollToText("Recipient's country & code")
		MobileDriver.tap(commonPage.imgSiblingGroup("Recipient's country & code"), GlobalVariable.G_timeoutLong, flowControl)
		MobileDriver.waitForElementPresent(commonPage.lblDynamic(UILblSelectCountry), GlobalVariable.G_timeoutLong, flowControl)
		'Tap matching country result'
		MobileDriver.tap(commonPage.lblContainDynamic(receiverCountryCode), GlobalVariable.G_timeoutLong, flowControl)

		'Fill Mobile number'
		MobileDriver.scrollToText(UILblMobileNumber)
		MobileDriver.setText(commonPage.lblContainDynamic("3456"), mobileNumber, GlobalVariable.G_timeoutLong, FailureHandling.STOP_ON_FAILURE)
		MobileDriver.tap(commonPage.lblDynamic(UILblMobileNumber), GlobalVariable.G_timeoutMedium, flowControl)

		'Fill Street line 1'
		MobileDriver.scrollToText('Street line 1')
		MobileDriver.setText(commonPage.lblContainDynamic('Floor'), streetLine1, GlobalVariable.G_timeoutLong, FailureHandling.STOP_ON_FAILURE)
		MobileDriver.tap(commonPage.lblDynamic('Street line 1'), GlobalVariable.G_timeoutMedium, flowControl)

		'Fill Street line 2'
		MobileDriver.scrollToText('Street line 2')
		MobileDriver.setText(commonPage.lblContainDynamic('Street name'), streetLine2, GlobalVariable.G_timeoutLong, FailureHandling.STOP_ON_FAILURE)
		MobileDriver.tap(commonPage.lblDynamic('Street line 2'), GlobalVariable.G_timeoutMedium, flowControl)

		'Fill Postal code'
		MobileDriver.scrollToText(UIState)
		MobileDriver.setText(commonPage.lblContainDynamic('52200'), postCode, GlobalVariable.G_timeoutLong, FailureHandling.STOP_ON_FAILURE)
		MobileDriver.tap(commonPage.lblDynamic(UILblPostCode), GlobalVariable.G_timeoutMedium, flowControl)

		'Fill City (Destination)'
		MobileDriver.scrollToText('City (Destination)')
		MobileDriver.setText(commonPage.lblContainDynamic('Kuala'), city, GlobalVariable.G_timeoutLong, FailureHandling.STOP_ON_FAILURE)
		MobileDriver.tap(commonPage.lblDynamic('City (Destination)'), GlobalVariable.G_timeoutMedium, flowControl)

		'Fill State'
		MobileDriver.scrollToText('State')
		MobileDriver.setText(commonPage.lblContainDynamic('Persekutuan'), state, GlobalVariable.G_timeoutLong, FailureHandling.STOP_ON_FAILURE)
		MobileDriver.tap(commonPage.lblDynamic('State'), GlobalVariable.G_timeoutMedium, flowControl)

		'Select Country - opens Select Country search screen'
		commonPage.swipeByW3CAction(500, 1500, 500, 1000)
		MobileDriver.tap(commonPage.imgSiblingGroup(UILblCountry), GlobalVariable.G_timeoutLong, flowControl)
		MobileDriver.waitForElementPresent(commonPage.lblDynamic(UILblSelectCountry), GlobalVariable.G_timeoutLong, flowControl)
		'Tap matching country result'
		MobileDriver.tap(commonPage.lblContainDynamic(country), GlobalVariable.G_timeoutLong, flowControl)

		'Select ID type (Optional) dropdown'
		if (idTypeOptional) {
			MobileDriver.scrollToText('ID type (Optional)')
			MobileDriver.tap(commonPage.imgSiblingGroup('ID type (Optional)'), GlobalVariable.G_timeoutLong, flowControl)
			if (Mobile.waitForElementNotPresent(commonPage.lblDynamic(UIDone), GlobalVariable.G_timeoutShort)) {
				MobileDriver.tap(commonPage.imgSiblingGroup('ID type (Optional)'), GlobalVariable.G_timeoutLong, flowControl)
			}
			Mobile.scrollToText(idTypeOptional)
			MobileDriver.tap(commonPage.lblDynamic(idTypeOptional), GlobalVariable.G_timeoutLong, flowControl)
			MobileDriver.tap(commonPage.lblDynamic(UIDone), GlobalVariable.G_timeoutLong, flowControl)
		}

		'Fill ID number (Optional)'
		if (idNumberOptional) {
			MobileDriver.scrollToText('ID number (Optional)')
			MobileDriver.setText(commonPage.lblContainDynamic('5678'), idNumberOptional, GlobalVariable.G_timeoutLong, FailureHandling.STOP_ON_FAILURE)
			MobileDriver.tap(commonPage.lblDynamic('ID number (Optional)'), GlobalVariable.G_timeoutMedium, flowControl)
		}

		'Accept date of birth (optional, tap picker and dismiss)'
		MobileDriver.scrollToText(UILblDateOfBirth)
		MobileDriver.tap(commonPage.txtSiblingGroup(UILblDateOfBirth), GlobalVariable.G_timeoutMedium, FailureHandling.OPTIONAL)
		if (Mobile.waitForElementPresent(commonPage.lblDynamic(UIDone), GlobalVariable.G_timeoutShort)) {
			MobileDriver.tap(commonPage.lblDynamic(UIDone), GlobalVariable.G_timeoutMedium, flowControl)
		}
		

		MobileDriver.takeScreenshot()

		'Tap Next button'
		MobileDriver.tap(commonPage.lblContainDynamic(UITextContinue), GlobalVariable.G_timeoutMedium, FailureHandling.OPTIONAL)
		MobileDriver.tap(commonPage.lblContainDynamic(UITextContinue), GlobalVariable.G_timeoutMedium, FailureHandling.OPTIONAL)
	}

	/**
	 * [OVERHAUL] WU Step 5 of 5 - Transfer Details.
	 * Fields: Purpose of transfer (dropdown), Sub purpose (dropdown, conditional).
	 * 
	 * @param purposeOfTransfer Purpose of transfer dropdown value
	 * @param subPurpose Sub purpose dropdown value (optional, appears conditionally)
	 */
	void ovhWUStep5TransferDetails(
		String purposeOfTransfer,
		String subPurpose = '',
		FailureHandling flowControl = defaultFailureHandling) {

		'Wait for Step 5 - Transfer Details page to load'
		MobileDriver.waitForElementPresent(commonPage.lblDynamic('Western Union'), GlobalVariable.G_timeoutLong, flowControl)
		MobileDriver.waitForElementPresent(commonPage.lblContainDynamic('Please fill in transfer details'), GlobalVariable.G_timeoutMedium, flowControl)
		MobileDriver.takeScreenshot()

		'Select Purpose of transfer dropdown'
		MobileDriver.tap(commonPage.imgSiblingGroup(UILblPurposeOfTransfer), GlobalVariable.G_timeoutLong, flowControl)
		if (Mobile.waitForElementNotPresent(commonPage.lblDynamic(UIDone), GlobalVariable.G_timeoutShort)) {
			MobileDriver.tap(commonPage.imgSiblingGroup(UILblPurposeOfTransfer), GlobalVariable.G_timeoutLong, flowControl)
		}
		Mobile.scrollToText(purposeOfTransfer)
		MobileDriver.tap(commonPage.lblDynamic(purposeOfTransfer), GlobalVariable.G_timeoutLong, flowControl)
		MobileDriver.tap(commonPage.lblDynamic(UIDone), GlobalVariable.G_timeoutLong, flowControl)

		'Select Sub purpose dropdown (if provided and field appears)'
		if (subPurpose) {
			if (waitForElementPresent(commonPage.lblDynamic(UILblSubPurpose), GlobalVariable.G_timeoutMiniShort, FailureHandling.OPTIONAL)) {
				MobileDriver.scrollToText(UILblSubPurpose)
				MobileDriver.tap(commonPage.imgSiblingGroup(UILblSubPurpose), GlobalVariable.G_timeoutLong, flowControl)
				if (Mobile.waitForElementNotPresent(commonPage.lblDynamic(UIDone), GlobalVariable.G_timeoutShort)) {
					MobileDriver.tap(commonPage.imgSiblingGroup(UILblSubPurpose), GlobalVariable.G_timeoutLong, flowControl)
				}
				Mobile.scrollToText(subPurpose)
				MobileDriver.tap(commonPage.lblDynamic(subPurpose), GlobalVariable.G_timeoutLong, flowControl)
				MobileDriver.tap(commonPage.lblDynamic(UIDone), GlobalVariable.G_timeoutLong, flowControl)
			}
		}

		MobileDriver.takeScreenshot()

		'Tap Next button'
		MobileDriver.tap(commonPage.lblContainDynamic(UITextContinue), GlobalVariable.G_timeoutMedium, FailureHandling.OPTIONAL)
		MobileDriver.tap(commonPage.lblContainDynamic(UITextContinue), GlobalVariable.G_timeoutMedium, FailureHandling.OPTIONAL)
	}

	/**
	 * [OVERHAUL] WU Confirmation screen - verify all sections and tap Confirm and Submit.
	 * Sections: Sender's Details, Recipient's Details, Transfer Details.
	 * 
	 * @param citizenship Sender citizenship (for verification)
	 * @param promoCode Promo code (optional, for verification)
	 * @param senderEmailAddress Sender email (optional, for verification)
	 * @param occupation Occupation (for verification)
	 * @param employmentPositionLevel Employment position level (for verification)
	 * @param relationshipToBeneficiary Relationship to beneficiary (for verification)
	 * @param sourceOfFunds Source of funds (for verification)
	 * @param firstName Recipient first name (for verification)
	 * @param lastName Recipient last name (for verification)
	 * @param receiverCountryCode Receiver country code (for verification)
	 * @param recipientMobileNumber Recipient mobile number (for verification)
	 * @param streetLine1 Street line 1 (for verification)
	 * @param streetLine2 Street line 2 (for verification)
	 * @param purposeOfTransfer Purpose of transfer (for verification)
	 * @param subPurpose Sub purpose (optional, for verification)
	 */
	void ovhWUConfirm(
		String citizenship = '',
		String promoCode = '',
		String senderEmailAddress = '',
		String occupation = '',
		String employmentPositionLevel = '',
		String relationshipToBeneficiary = '',
		String sourceOfFunds = '',
		String firstName = '',
		String lastName = '',
		String receiverCountryCode = '',
		String recipientMobileNumber = '',
		String streetLine1 = '',
		String streetLine2 = '',
		String purposeOfTransfer = '',
		String subPurpose = '',
		FailureHandling flowControl = defaultFailureHandling) {

		'Wait for Confirmation page to load'
		MobileDriver.waitForElementPresent(commonPage.lblDynamic('Confirmation'), GlobalVariable.G_timeoutLong, flowControl)
		MobileDriver.waitForElementPresent(commonPage.lblContainDynamic('Please confirm the details below'), GlobalVariable.G_timeoutMedium, flowControl)
		MobileDriver.takeScreenshot()

		"Verify Sender's Details section"
		MobileDriver.waitForElementPresent(commonPage.lblDynamic("Sender's Details"), GlobalVariable.G_timeoutMedium, flowControl)
		if (promoCode) {
			MobileDriver.waitForElementPresent(commonPage.lblDynamic(promoCode), GlobalVariable.G_timeoutShort, flowControl)
		}
		if (citizenship) {
			MobileDriver.waitForElementPresent(commonPage.lblDynamic(citizenship), GlobalVariable.G_timeoutShort, flowControl)
		}
		if (senderEmailAddress) {
			MobileDriver.waitForElementPresent(commonPage.lblDynamic(senderEmailAddress), GlobalVariable.G_timeoutShort, flowControl)
		}
		commonPage.swipeByW3CAction(500, 1500, 500, 1000)
		MobileDriver.takeScreenshot()

		if (occupation) {
			MobileDriver.waitForElementPresent(commonPage.lblDynamic(occupation), GlobalVariable.G_timeoutShort, flowControl)
		}
		if (employmentPositionLevel) {
			MobileDriver.waitForElementPresent(commonPage.lblDynamic(employmentPositionLevel), GlobalVariable.G_timeoutShort, flowControl)
		}
		if (relationshipToBeneficiary) {
			MobileDriver.waitForElementPresent(commonPage.lblDynamic(relationshipToBeneficiary), GlobalVariable.G_timeoutShort, flowControl)
		}
		if (sourceOfFunds) {
			MobileDriver.waitForElementPresent(commonPage.lblDynamic(sourceOfFunds), GlobalVariable.G_timeoutShort, flowControl)
		}
		commonPage.swipeByW3CAction(500, 1500, 500, 1000)
		commonPage.swipeByW3CAction(500, 1500, 500, 1000)
		MobileDriver.takeScreenshot()

		"Verify Recipient's Details section"
		MobileDriver.waitForElementPresent(commonPage.lblDynamic("Recipient's Details"), GlobalVariable.G_timeoutMedium, flowControl)
		if (firstName) {
			MobileDriver.waitForElementPresent(commonPage.lblDynamic(firstName), GlobalVariable.G_timeoutShort, flowControl)
		}
		if (lastName) {
			MobileDriver.waitForElementPresent(commonPage.lblDynamic(lastName), GlobalVariable.G_timeoutShort, flowControl)
		}
		if (receiverCountryCode) {
			MobileDriver.waitForElementPresent(commonPage.lblDynamic(receiverCountryCode), GlobalVariable.G_timeoutShort, flowControl)
		}
		if (recipientMobileNumber) {
			MobileDriver.waitForElementPresent(commonPage.lblDynamic(recipientMobileNumber), GlobalVariable.G_timeoutShort, flowControl)
		}
		if (streetLine1) {
			MobileDriver.waitForElementPresent(commonPage.lblDynamic(streetLine1), GlobalVariable.G_timeoutShort, flowControl)
		}
		if (streetLine2) {
			MobileDriver.waitForElementPresent(commonPage.lblDynamic(streetLine2), GlobalVariable.G_timeoutShort, flowControl)
		}
		commonPage.swipeByW3CAction(500, 1500, 500, 1000)
		MobileDriver.takeScreenshot()

		'Verify Transfer Details section'
		MobileDriver.waitForElementPresent(commonPage.lblDynamic(UILblTransferDetails), GlobalVariable.G_timeoutMedium, flowControl)
		if (purposeOfTransfer) {
			MobileDriver.waitForElementPresent(commonPage.lblDynamic(purposeOfTransfer), GlobalVariable.G_timeoutShort, flowControl)
		}
		if (subPurpose) {
			MobileDriver.waitForElementPresent(commonPage.lblDynamic(subPurpose), GlobalVariable.G_timeoutShort, flowControl)
		}
		MobileDriver.takeScreenshot()
		commonPage.swipeByW3CAction(500, 1500, 500, 1000)

		'Tap Confirm and Submit button'
		MobileDriver.tap(commonPage.lblContainDynamic(UILblConfirmAndSubmit), GlobalVariable.G_timeoutMedium, FailureHandling.OPTIONAL)
		MobileDriver.tap(commonPage.lblContainDynamic(UILblConfirmAndSubmit), GlobalVariable.G_timeoutMedium, FailureHandling.OPTIONAL)
		MobileDriver.tap(commonPage.lblContainDynamic(UILblConfirmAndSubmit), GlobalVariable.G_timeoutMedium, FailureHandling.OPTIONAL)
	}

	//==========================================================================
	// SST Keywords
	//==========================================================================

	void verifySSTTransferType(String typeTransfer, FailureHandling flowControl = defaultFailureHandling) {
		scrollToText(typeTransfer)
		
		'Verify SST Text below Service Fee'
		MobileDriver.verifyElementExist(commonPage.lblDynamic(UILblIncludeSST), GlobalVariable.G_timeoutMedium)
		
		'Verify SST Text below Bank Fee'
		String BankFeeSST = Mobile.getText(commonPage.lblSiblingDynamic(UILblAgentBeneficiaryBankFee), GlobalVariable.G_timeoutMedium)
		Mobile.comment(BankFeeSST)
		
		Mobile.verifyMatch(BankFeeSST, UILblIncludeSST, false, FailureHandling.OPTIONAL)
		MobileDriver.takeScreenshot()
		
		'Verify Service Fee SST Popup'
		MobileDriver.tap(commonPage.imgNoDynamic(3), GlobalVariable.G_timeoutMedium, flowControl)
		MobileDriver.verifyElementExist(commonPage.lblContainDynamic("Service Fee"), GlobalVariable.G_timeoutMedium)
		MobileDriver.verifyElementExist(commonPage.lblDynamic(UILblServiceFeeSSTInfo), GlobalVariable.G_timeoutMedium)
		MobileDriver.takeScreenshot()
		MobileDriver.tap(commonPage.imgNoDynamic(1), GlobalVariable.G_timeoutMedium, flowControl)
		
		if(typeTransfer=="Vcb Overseas Transfer") {
			
			'Verify Bank Fee SST Popup'
			MobileDriver.tap(commonPage.imgNoDynamic(4), GlobalVariable.G_timeoutMedium, flowControl)
			MobileDriver.verifyElementExist(commonPage.lblContainDynamic("Agent/Beneficiary Bank Fee"), GlobalVariable.G_timeoutMedium)
			for (item in UILblSSTMOTInfoList) {
				MobileDriver.verifyElementExist(commonPage.lblContainDynamic(item), GlobalVariable.G_timeoutMedium)
			}
			MobileDriver.takeScreenshot()
			MobileDriver.tap(commonPage.imgNoDynamic(1), GlobalVariable.G_timeoutMedium, flowControl)
			
		} else if(typeTransfer=="Western Union") {
			
			'Verify Bank Fee SST Popup'
			MobileDriver.tap(commonPage.imgNoDynamic(4), GlobalVariable.G_timeoutMedium, flowControl)
			MobileDriver.verifyElementExist(commonPage.lblContainDynamic("Agent/Beneficiary Bank Fee"), GlobalVariable.G_timeoutMedium)
			for (item in UILblSSTWUInfoList) {
				MobileDriver.verifyElementExist(commonPage.lblContainDynamic(item), GlobalVariable.G_timeoutMedium)
			}
			MobileDriver.takeScreenshot()
			MobileDriver.tap(commonPage.imgNoDynamic(1), GlobalVariable.G_timeoutMedium, flowControl)
			
		} else {
		
		'Verify Bank Fee SST Popup'
		MobileDriver.tap(commonPage.imgNoDynamic(4), GlobalVariable.G_timeoutMedium, flowControl)
		MobileDriver.verifyElementExist(commonPage.lblDynamic("Agent/Beneficiary Bank Fee"), GlobalVariable.G_timeoutMedium)
		for (item in UILblSSTInfoList) {
			MobileDriver.verifyElementExist(commonPage.lblContainDynamic(item), GlobalVariable.G_timeoutMedium)
		}
		MobileDriver.takeScreenshot()
		MobileDriver.tap(commonPage.imgNoDynamic(1), GlobalVariable.G_timeoutMedium, flowControl)
		}
	}
	
	void verifySSTConfirmation() {
		scrollToText(UILblBankFeeSST)
		MobileDriver.verifyElementExist(commonPage.lblDynamic(UILblServiceSST), 0)		
		verifyServiceFeeSST()
		MobileDriver.takeScreenshot()
		verifyBankFeeSST()
		
	}
	
	void verifySSTConfirmationFTT() {
//		commonPage.scrollToEnd()
		scrollToText(UILblBankFeeSST)
		MobileDriver.verifyElementExist(commonPage.lblDynamic(UILblServiceSST), 0)
		MobileDriver.verifyElementExist(commonPage.lblDynamic(UILblBankFeeSST), 0)
		
		verifyServiceFeeSST()
		verifyBankFeeSST()
//		commonPage.scrollToTop()
	}
	
	void verifyServiceFeeSST() {
		'Resolve label — app shows "Service Fee" or "Service fee" depending on screen'
		String serviceFeeLabel = MobileDriver.waitForElementPresent(commonPage.lblDynamic('Service Fee'), GlobalVariable.G_timeoutShort, FailureHandling.OPTIONAL) ? 'Service Fee' : 'Service fee'

		'Extract Service Fee and Calculate expected SST based off Service Fee'
		String actualServiceFee = MobileDriver.getText(commonPage.lblSiblingDynamic(serviceFeeLabel), 0)
		String numericValueServiceFee = actualServiceFee.replaceAll("[^0-9.]", "")
		Double sstServiceValue = Double.parseDouble(numericValueServiceFee)
		Double calculatedServiceFeeSST = sstServiceValue * 0.08
		String formattedExpectedCalculatedSST = String.format("%.2f", calculatedServiceFeeSST)
		Mobile.comment("Calculated Service Fee SST: " + formattedExpectedCalculatedSST)

		'Extract actual SST value from screen'
		String actualServiceFeeSST = MobileDriver.getText(commonPage.lblSiblingDynamic(UILblServiceSST), 0)
		String numericValueServiceFeeSST = actualServiceFeeSST.replaceAll("[^0-9.]", "")
		String formattedActualServiceFeeSSTValue = String.format("%.2f", Double.parseDouble(numericValueServiceFeeSST))
		Mobile.comment("Actual Service Fee SST: " + formattedActualServiceFeeSSTValue)

		'Compare both values'
		if (formattedExpectedCalculatedSST == formattedActualServiceFeeSSTValue) {
			Mobile.comment("✅ Service SST value is correct.")
		} else {
			Mobile.comment("❌ Service SST value mismatch. Expected: " + formattedExpectedCalculatedSST + ", Actual: " + formattedActualServiceFeeSSTValue)
		}

//		// ALTERNATIVE: verify computed value exists on screen instead of reading sibling
//		String expectedSST = String.format("%.2f", Double.parseDouble(actualServiceFee.replaceAll("[^0-9.]", "")) * 0.08)
//		MobileDriver.verifyElementExist(commonPage.lblDynamic(UILblServiceSST), GlobalVariable.G_timeoutShort, FailureHandling.OPTIONAL)
//		if (MobileDriver.waitForElementPresent(commonPage.lblContainDynamic(expectedSST), GlobalVariable.G_timeoutShort, FailureHandling.OPTIONAL)) {
//			Mobile.comment("✅ Service Fee SST RM " + expectedSST + " verified on screen.")
//		} else {
//			Mobile.comment("❌ Service Fee SST RM " + expectedSST + " not found on screen.")
//		}
	}
	
	void verifyBankFeeSST() {
		'Resolve label — app shows "Agent/Beneficiary bank fee" or "Agent/Beneficiary Bank Fee" depending on screen'
		String bankFeeLabel = MobileDriver.waitForElementPresent(commonPage.lblDynamic('Agent/Beneficiary bank fee'), GlobalVariable.G_timeoutShort, FailureHandling.OPTIONAL) ? 'Agent/Beneficiary bank fee' : 'Agent/Beneficiary Bank Fee'

		'Extract Bank Fee and Calculate expected SST based off Bank Fee'
		String actualBankFee = MobileDriver.getText(commonPage.lblSiblingDynamic(bankFeeLabel), 0)
		String numericValueBankFee = actualBankFee.replaceAll("[^0-9.]", "")
		Double sstBankValue = Double.parseDouble(numericValueBankFee)
		Double calculatedBankFeeSST = sstBankValue * 0.08
		String formattedExpectedCalculatedSST = String.format("%.2f", calculatedBankFeeSST)
		Mobile.comment("Calculated Bank Fee SST: " + formattedExpectedCalculatedSST)

		'Extract actual SST value from screen'
		String actualBankFeeSST = MobileDriver.getText(commonPage.lblSiblingDynamic(UILblBankFeeSST), 0)
		String numericValueBankFeeSST = actualBankFeeSST.replaceAll("[^0-9.]", "")
		String formattedActualBankFeeSSTValue = String.format("%.2f", Double.parseDouble(numericValueBankFeeSST))
		Mobile.comment("Actual Bank Fee SST: " + formattedActualBankFeeSSTValue)

		'Compare both values'
		if (formattedExpectedCalculatedSST == formattedActualBankFeeSSTValue) {
			Mobile.comment("✅ Bank SST value is correct.")
		} else {
			Mobile.comment("❌ Bank SST value mismatch. Expected: " + formattedExpectedCalculatedSST + ", Actual: " + formattedActualBankFeeSSTValue)
		}

//		// ALTERNATIVE: verify computed value exists on screen instead of reading sibling
//		String expectedSST = String.format("%.2f", Double.parseDouble(actualBankFee.replaceAll("[^0-9.]", "")) * 0.08)
//		MobileDriver.verifyElementExist(commonPage.lblDynamic(UILblBankFeeSST), GlobalVariable.G_timeoutShort, FailureHandling.OPTIONAL)
//		if (MobileDriver.waitForElementPresent(commonPage.lblContainDynamic(expectedSST), GlobalVariable.G_timeoutShort, FailureHandling.OPTIONAL)) {
//			Mobile.comment("✅ Bank Fee SST RM " + expectedSST + " verified on screen.")
//		} else {
//			Mobile.comment("❌ Bank Fee SST RM " + expectedSST + " not found on screen.")
//		}
	}
	
	void verifyTransactionHistorySST(typeTransfer) {
		MobileDriver.tap(commonPage.lblDynamic("View Transactions"), 0)
//		MobileDriver.verifyElementExist(commonPage.lblContainDynamic(typeTransfer), 0)
		MobileDriver.takeScreenshot()
		
		MobileDriver.tap(commonPage.lblDynamic("Transaction History"),GlobalVariable.G_timeoutLong, defaultFailureHandling)
		commonPage.selectDropdownScrollOneLine(accountDetailPage.M2U_HISTORY, GlobalVariable.G_timeoutLong, defaultFailureHandling)

//		MobileDriver.verifyElementExist(commonPage.lblContainDynamic("8% SST on Agent/Beneficiary Bank Fee"), 0)
//		MobileDriver.verifyElementExist(commonPage.lblContainDynamic("8% SST on Service Fee"), 0)
		MobileDriver.takeScreenshot()
		MobileDriver.verifyElementExist(commonPage.lblContainDynamic(typeTransfer), 0)
		
		
	}

	String ovhVerifyReceiptAndGetTotal(List<String> verifyList = [], FailureHandling flowControl = defaultFailureHandling, boolean verifySST = false) {
		MobileDriver.delay(GlobalVariable.G_timeoutMiniShort)
		'Open Share Receipt'
		if (MobileDriver.waitForElementPresent(commonPage.lblDynamic(shareReceiptPage.SHARE_RECEIPT), GlobalVariable.G_timeoutOneMinute)) {
			commonPage.tapText(shareReceiptPage.SHARE_RECEIPT, GlobalVariable.G_timeoutLong, flowControl)
		}
		'Wait for Share Receipt WebView'
		MobileDriver.waitForElementNotPresent(shareReceiptPage.fraShareReceiptWebView(), GlobalVariable.G_timeoutShort, FailureHandling.OPTIONAL)
		commonPage.delay(GlobalVariable.G_timeoutMiniShort - 1)

		'Verify texts in receipt'
		if (verifyList) {
			commonPage.verifyElementsAndScrolling(verifyList, commonPage.lblContainDynamicPath(), GlobalVariable.G_timeoutLong, shareReceiptPage.fraShareReceiptWebView(), FailureHandling.OPTIONAL)
		}

		'Scroll down to Total amount'
		commonPage.swipe(500, 1500, 500, 500)
		commonPage.swipe(500, 1500, 500, 500)
		
		'Get Total amount text (WU uses "Total (incl. fees)", MOT/FTT uses "Total amount")'
		String totalLabel = MobileDriver.waitForElementPresent(commonPage.lblSiblingOneGroup('Total (incl. fees)'), GlobalVariable.G_timeoutShort, FailureHandling.OPTIONAL) ? 'Total (incl. fees)' : 'Total amount'
		String txtTotalAmount = MobileDriver.getText(commonPage.lblSiblingOneGroup(totalLabel), GlobalVariable.G_timeoutLong)

		'Verify SST amounts on receipt (if requested)'
		if (verifySST) {
			if (MobileDriver.waitForElementPresent(commonPage.lblDynamic(UILblServiceSST), GlobalVariable.G_timeoutShort, FailureHandling.OPTIONAL)) {
				verifyServiceFeeSST()
			}
			if (MobileDriver.waitForElementPresent(commonPage.lblDynamic(UILblBankFeeSST), GlobalVariable.G_timeoutShort, FailureHandling.OPTIONAL)) {
				verifyBankFeeSST()
			}
		}
		MobileDriver.takeScreenshot()

		'Tap share icon'
		shareReceiptPage.tapShareIcon(flowControl)
		'Tap back'
		MobileDriver.tapAndLog(commonPage.imgNoDynamic(1), GlobalVariable.G_timeoutLong, 'Icon Back', flowControl)
		'Verify Done button'
		verifyElements([shareReceiptPage.DONE_BTN], commonPage.lblDynamicPath(), GlobalVariable.G_timeoutLong)

		return String.valueOf((long)(Double.parseDouble(txtTotalAmount.replace('RM ', '').replace(',', '').trim()) * 100))
	}

	void ovhVerifyTransactionHistory(String typeTransfer, List<String> verifyList = [], FailureHandling flowControl = defaultFailureHandling) {
		String shortName = typeTransfer == 'Vcb Overseas Transfer' ? 'MOT' :
		                   typeTransfer == 'Foreign Telegraphic Transfer' ? 'FTT' :
		                   typeTransfer == 'Western Union' ? 'WU' : typeTransfer
						   
		'Tap View Transactions'
		MobileDriver.tap(commonPage.lblDynamic('View Transactions'), GlobalVariable.G_timeoutLong, flowControl)
		MobileDriver.takeScreenshot()
		
		'Tap Transaction History dropdown'
		MobileDriver.tap(commonPage.lblDynamic('Transaction History'), GlobalVariable.G_timeoutLong, flowControl)

		'Select M2U History and tap Done'
		commonPage.selectDropdownScrollOneLine(accountDetailPage.M2U_HISTORY, GlobalVariable.G_timeoutLong, flowControl)
		MobileDriver.takeScreenshot()
		MobileDriver.tap(commonPage.lblDynamic(UIDone), 0)

		'Tap the latest transaction row matching transfer type'
		MobileDriver.tap(commonPage.lblContainDynamic(shortName), GlobalVariable.G_timeoutLong, flowControl)
		MobileDriver.takeScreenshot()

		'Verify receipt'
		shareReceiptPage.shareReceipts(verifyList,shareReceiptPage.DONE_BTN, FailureHandling.OPTIONAL)
	}
	
	void verifySSTShareReceipts(List<String> verifyTexts, String UIBack = shareReceiptPage.DONE_BTN, FailureHandling flowControl = defaultFailureHandling) {
		'Select Share receipt'
		commonPage.tapText(shareReceiptPage.SHARE_RECEIPT, GlobalVariable.G_timeoutOneMinute, flowControl)
		
		commonPage.delay(GlobalVariable.G_timeoutMiniShort - 1)
		commonPage.scrollToEnd()
		'Wait for Share Receipt WebView not present'
		MobileDriver.waitForElementNotPresent(shareReceiptPage.fraShareReceiptWebView(), GlobalVariable.G_timeoutShort, FailureHandling.OPTIONAL)
		
		commonPage.delay(GlobalVariable.G_timeoutMiniShort - 1)
		
		'Verify texts in verifyTexts exist'
		commonPage.verifyElementsAndScrolling(verifyTexts, commonPage.lblContainDynamicPath(), GlobalVariable.G_timeoutLong, shareReceiptPage.fraShareReceiptWebView())
		MobileDriver.takeScreenshot()
		
		commonPage.scrollToEnd()
		
		if(MobileDriver.verifyElementExist(commonPage.lblDynamic(UILblServiceFee), 0)) {
			this.verifyServiceFeeSST()
			
		}
		
		if(MobileDriver.verifyElementExist(commonPage.lblDynamic(UILblAgentBankFee), 0)) {
			this.verifyBankFeeSST()
			
		}
		
		'Select share icon'
		shareReceiptPage.tapShareIcon(flowControl)
		'Tap back'
		MobileDriver.tapAndLog(commonPage.imgNoDynamic(1), GlobalVariable.G_timeoutLong, 'Icon Back',flowControl)

		'If UIBack is not null, then verify UIBack'
		if (UIBack != null) {
			verifyElements([UIBack], commonPage.lblDynamicPath(), GlobalVariable.G_timeoutLong)
		}
	}

	void ovhAddToFavourites(String typeTransfer, FailureHandling flowControl = defaultFailureHandling) {
		'Derive fav name from transfer type'
		String favName
		if (typeTransfer == 'Vcb Overseas Transfer') {
			favName = 'MOT'
		} else if (typeTransfer == 'Foreign Telegraphic Transfer') {
			favName = 'FTT'
		} else if (typeTransfer == 'Western Union') {
			favName = 'WU'
		} else {
			favName = "Fav"
		}

		'Tap Add to Favourites link on success screen'
		MobileDriver.tap(commonPage.lblContainDynamic('Favourites'), GlobalVariable.G_timeoutMedium, flowControl)
		MobileDriver.takeScreenshot()

		'Clear and set fav name'
		MobileDriver.tap(commonPage.txtEditNo(1), GlobalVariable.G_timeoutMedium, FailureHandling.OPTIONAL)
		Mobile.clearText(commonPage.txtEditNo(1), GlobalVariable.G_timeoutMedium, FailureHandling.OPTIONAL)
		MobileDriver.setText(commonPage.txtEditNo(1), favName, GlobalVariable.G_timeoutMedium, flowControl)
		Mobile.hideKeyboard()
		MobileDriver.takeScreenshot()

		'Tap Add to Favourites button'
		MobileDriver.tap(commonPage.lblDynamic('Add to Favourites'), GlobalVariable.G_timeoutMedium, flowControl)
		commonPage.waitLoading()
		MobileDriver.takeScreenshot()

		'Handle toast message — success, already exists, or something wrong'
		if (MobileDriver.waitForElementPresent(commonPage.lblContainDynamic('record already exists'), GlobalVariable.G_timeoutShort, FailureHandling.OPTIONAL)) {
			Mobile.comment('Favourite already exists — skipping')
		} else if (MobileDriver.waitForElementPresent(commonPage.lblContainDynamic('Something is wrong'), GlobalVariable.G_timeoutShort, FailureHandling.OPTIONAL)) {
			Mobile.comment('Something is wrong toast appeared after tapping Add to Favourites')
			commonPage.tapBack()
		} else {
			Mobile.comment('Favourite added successfully')
		}

		Mobile.delay(GlobalVariable.G_timeoutMiniShort)
		MobileDriver.takeScreenshot()
	}
	
	void tapDone(FailureHandling flowControl = FailureHandling.OPTIONAL) {
		MobileDriver.takeScreenshot()
		
		int left = Mobile.getElementLeftPosition(commonPage.lblDynamic(UIDone), GlobalVariable.G_timeoutLong, flowControl)
		int top = Mobile.getElementTopPosition(commonPage.lblDynamic(UIDone), GlobalVariable.G_timeoutLong, flowControl)
		Mobile.tapAtPosition(left, top, flowControl)
	}

	void tapContinue(FailureHandling flowControl = FailureHandling.OPTIONAL) {
		MobileDriver.takeScreenshot()
		
		int left = Mobile.getElementLeftPosition(commonPage.lblDynamic(UITextContinue), GlobalVariable.G_timeoutLong, flowControl)
		int top = Mobile.getElementTopPosition(commonPage.lblDynamic(UITextContinue), GlobalVariable.G_timeoutLong, flowControl)
		Mobile.tapAtPosition(left, top, flowControl)
		Mobile.tapAtPosition(left, top, flowControl)
		

	}

	void selectContinueButton(FailureHandling flowControl = defaultFailureHandling) {
		Mobile.delay(GlobalVariable.G_timeoutMiniShort)
		'Select UI Label Continue'
		Mobile.tap(commonPage.lblDynamic(UITextContinue), GlobalVariable.G_timeoutMedium, flowControl)
	}
}
