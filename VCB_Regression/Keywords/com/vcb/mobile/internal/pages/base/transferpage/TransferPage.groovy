package com.vcb.mobile.internal.pages.base.transferpage
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import com.kms.katalon.core.util.KeywordUtil

import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testobject.TestObject
import com.vcb.assertions.BaseAssert
import com.vcb.common.MobileDriver
import com.vcb.mobile.internal.pages.base.BasePage
import com.vcb.mobile.internal.pages.base.CommonPage
import com.vcb.mobile.internal.pages.base.ConfirmationPage
import com.vcb.mobile.internal.pages.base.Secure2uAuthorisationPage

import internal.GlobalVariable

public abstract class TransferPage extends BasePage{

	abstract TestObject fraScrollTab()
	//Others
	abstract TestObject imgBanks()
	abstract TestObject imgOthers()
	abstract TestObject imgOverSeas()
	abstract TestObject imgSearch()
	abstract TestObject txtSearch()
	abstract TestObject lblFavoriteItem(String favName)
	abstract TestObject imgBack()
	abstract TestObject lblSearchItem(String search, String item)
	//recipient reference
	abstract TestObject txtRecipient()

	//Account number
	abstract TestObject txtAccountNo()

	//Transaction
	abstract TestObject lblTransferSummaryDateTimeTitle()
	abstract TestObject lblMessage()
	abstract TestObject lblSpecialAddFav()
	abstract TestObject btnMainCloseFavPopUp()

	//Confirmation
	abstract TestObject lblRM()
	abstract TestObject imgV()
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
	String UILblMalaysian = 'Malaysian'
	String UILblNonMalaysian = 'Non-Malaysian'
	String UILblImportant = 'Important'
	String UILblConfirm = 'Confirm'
	String UILblBanks = 'Banks'
	String UIDuitNowTransfer = 'DuitNow Transfer'
	//UI Set Account No
	String UIMsgTransfer = 'Account number entered is invalid.'
	//UI Confirmation
	String UIToday = 'Today'
	String UIOptional = 'Optional'
	String UIConfirmation = 'Confirmation'
	String UIRecipientReference = 'Recipient reference'
	//UI Approve
	String UISecure2uAuthorisation = 'Secure2u authorisation'
	String UIApprove = 'Approve'
	List<String> UIStaticApprove = [
		'Secure2u authorisation',
		'To',
		'From',
		'Transaction Type',
		'Transfer'
	]

	List<String> UIStaticApproveV5 = [
		'Secure2u authorisation',
		'To',
		'From',
		'Transaction Type',
		'Transfer',
		'S2u Version',
		'V5'
	]
	List<String> G1AUIStaticApprove = [
		'Secure2u authorisation',
		'To',
		'From',
		'Transaction type'
	]

	//UI text transaction
	String UIReferenceID = 'Reference ID'
	String UIAddToFavourites = 'Add to Favourites'
	//UI Share Receipt
	String UIShareReceipt = 'Share Receipt'
	String UIDoneBtn = 'Done'
	List<String> UIstaticText = [
		'Reference ID',
		'Recipient reference',
		'Amount'
	]
	String UIPending = 'Pending'
	String UISuccessful = 'Successful'

	//Add Favourite
	String MsgExistAddFavourite = 'Record already exists.'
	String MsgSuccessAddFavourite = 'Successful'
	String MsgSpecialAddFavourite = 'Nickname can\'t include numbers and special characters except space, left single quote and -/@&'

	//Status
	String UITransferScheduled = 'Transfer scheduled'
	String UITransferSuccessful = 'Transfer successful'
	String UITransferAccepted = 'Transfer accepted'
	String UITransferFailed = 'Transfer failed'

	//Reject Status
	String UITransactionDeclined = 'Transaction declined'
	String UIAuthorisationFailed = 'Authorisation failed'
	String UIReject = 'Reject'

	//Expired Status
	List<String> UIStaticExpiredStatus = [
		'Secure2u has expired. Please perform the transaction again.'
	]
	String UIS2uExpired = 'Secure2u has expired. Please perform the transaction again.'
	String UIS2uAuthorisationExpired = 'Your Secure Verification authorisation has expired. Please perform the transaction again.'

	//Insufficient Amount Status
	List<String> UIStaticInsufficientAmountStatus = ['Transfer failed',]
	String UIStaticInsufficient = 'You have insufficient balance in your account.'
	String date =''
	static final String DATE = 'Date'
	String UIExceedsMaxLimit = 'Amount exceeds maximum transaction limit.'

	String UINote = 'Money withdrawn from your insured deposit(s)'
	String UIGlobalAccessAccount = 'Global Access Account-i'
	String UIOverseas = 'Overseas'

	boolean isAccepted = false;

	public static CommonPage commonPage = getPage(CommonPage)
	public static Secure2uAuthorisationPage secure2uAuthorisationPage = getPage(Secure2uAuthorisationPage)
	public static ConfirmationPage confirmationPage = getPage(ConfirmationPage)

	/**
	 * Select tab
	 */
	void selectTab(String tabName, boolean isVisible = false, int timeout =  GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		//		commonPage.waitLoading()
		'If tab is visible, select tab'
		if(isVisible) {
			MobileDriver.tapEleByTestObject(commonPage.lblDynamic(tabName))
			MobileDriver.takeScreenshot()
			return
		}
		'If tab is not visible, swipe to tab and select tab'
		commonPage.selectTab(this.fraScrollTab(), tabName, GlobalVariable.G_timeoutLong, flowControl)
		MobileDriver.takeScreenshot()
	}
	/**
	 * Select Transfer To Tab
	 */
	void selectTransferToTab(String tabName, FailureHandling flowControl = defaultFailureHandling) {
		int attempts = 0
		int maxAttempts = 3
		while(attempts < maxAttempts) {
			'Select Transfer To Tab'
			if(Mobile.waitForElementPresent(commonPage.lblDynamic(tabName), GlobalVariable.G_timeoutMiniShort)) {
				MobileDriver.tap(commonPage.lblDynamic(tabName), GlobalVariable.G_timeoutMiniShort)
				break
			}else {
				'Scroll to Left'
				commonPage.swipeByW3CAction(850, 360, 150, 360)
			}
			attempts++
		}
	}
	void selectNewTransfer(String newTransferName, FailureHandling flowControl = defaultFailureHandling) {
		'Tap New Transfer Type'
		MobileDriver.tapEleByTestObject(commonPage.lblDynamic(newTransferName))
		commonPage.waitLoading()
		MobileDriver.delay(GlobalVariable.G_timeoutMiniShort)
	}

	void selectSearchedBank(String bankName, String searchResult, FailureHandling flowControl = defaultFailureHandling) {
		'Select UI Label Banks'
		if(MobileDriver.waitForElementPresent(commonPage.lblDynamic(UILblBanks), GlobalVariable.G_timeoutShort)) {
			MobileDriver.tapEleByTestObject(commonPage.lblDynamic(UILblBanks))
		}
		delay(GlobalVariable.G_timeoutMiniShort)
		'Tap Search'
		MobileDriver.tapAndLog(commonPage.imgNoDynamic(2), GlobalVariable.G_timeoutMedium, 'Icon Search', flowControl)
		delay(GlobalVariable.G_timeoutMiniShort)
		int maxRetry = 5
		int attempt = 0
		boolean success = false
		while (attempt < maxRetry && !success) {
			try {
				MobileDriver.setText(commonPage.txtEditNo(), bankName, GlobalVariable.G_timeoutMedium, flowControl)
				success = true
			} catch (Exception e) {
				attempt++
				println("⚠️ Lỗi khi set text (lần thứ ${attempt}): ${e.message}")
				if (attempt < maxRetry) {
					Mobile.delay(2)
				} else {
					KeywordUtil.markFailed("Cannot find element")
				}
			}
		}
		MobileDriver.tap(commonPage.lblDynamic(searchResult), GlobalVariable.G_timeoutMedium, flowControl)
		delay(GlobalVariable.G_timeoutMiniShort)
	}

	void selectBankName(String bankName, FailureHandling flowControl = defaultFailureHandling) {
		'Select UI Label Banks'
		if(MobileDriver.waitForElementPresent(commonPage.lblDynamic(UILblBanks), GlobalVariable.G_timeoutShort)) {
			MobileDriver.tapEleByTestObject(commonPage.lblDynamic(UILblBanks))
		}
		delay(GlobalVariable.G_timeoutMiniShort)
		'Tap Search'
		MobileDriver.tapAndLog(commonPage.imgNoDynamic(2), GlobalVariable.G_timeoutMedium, 'Icon Search', flowControl)
		'Search'
		MobileDriver.setText(commonPage.txtEditNo(), bankName, GlobalVariable.G_timeoutMedium, flowControl)
		'Select Bank'
		MobileDriver.tap(this.lblSearchItem(bankName, bankName), GlobalVariable.G_timeoutMedium, flowControl)
		delay(GlobalVariable.G_timeoutMiniShort)
		'Check Select Bank'
		if(MobileDriver.waitForElementPresent(this.lblSearchItem(bankName, bankName), GlobalVariable.G_timeoutShort, FailureHandling.OPTIONAL)) {
			MobileDriver.tap(this.lblSearchItem(bankName, bankName), GlobalVariable.G_timeoutMedium, flowControl)
		}
	}

	/**
	 * enterAccountOtherBack
	 */
	void enterAccountOtherBack(Map transferInputData, Map transferDropdownData, FailureHandling flowControl = defaultFailureHandling) {
		'Input Account number'
		commonPage.inputTextListGroup(transferInputData)
		'Select Dropdown Transfer Data'
		commonPage.inputDropdownMapDefault(transferDropdownData)
		'Tap continue'
		commonPage.tapContinueBtn()
		'Wait to next page'
		commonPage.waitLoading()
	}

	/**
	 * enterAccountOtherBack
	 */
	void inputRecipientName(String recipientName, FailureHandling flowControl = defaultFailureHandling) {
		'Input RecipientName'
		MobileDriver.setText(commonPage.txtEditNo(1), recipientName, GlobalVariable.G_timeoutMedium, flowControl)
		'Tap continue'
		commonPage.tapContinueBtn()
		'Wait to next page'
		commonPage.waitLoading()
	}

	/**
	 * Verify click new transfer others
	 */
	void verifyClickNewTransferOthers(FailureHandling flowControl = defaultFailureHandling) {
		'Select Banks image'
		MobileDriver.tap(this.imgBanks(), GlobalVariable.G_timeoutMedium, flowControl)
		'Select Back'
		MobileDriver.tap(this.imgBack(), GlobalVariable.G_timeoutMedium, flowControl)

		'Select Others image'
		MobileDriver.tap(this.imgOthers(), GlobalVariable.G_timeoutMedium, flowControl)
		'Select Back'
		MobileDriver.tap(this.imgBack(), GlobalVariable.G_timeoutMedium, flowControl)

		'Select OverSeas image'
		MobileDriver.tap(this.imgOverSeas(), GlobalVariable.G_timeoutMedium, flowControl)
		'Select Back'
		MobileDriver.tap(this.imgBack(), GlobalVariable.G_timeoutMedium, flowControl)

		'Verify Others image is visible'
		MobileDriver.verifyElementVisible(this.imgOverSeas(), GlobalVariable.G_timeoutMedium, flowControl)
	}

	/**
	 * Verify search others
	 */
	boolean verifySearchOthers(String txt, boolean isExist = true, FailureHandling flowControl = defaultFailureHandling) {
		'Select Search'
		MobileDriver.tap(this.imgSearch(), GlobalVariable.G_timeoutMedium, flowControl)
		'Enter text'
		MobileDriver.setText(this.txtSearch(), txt, GlobalVariable.G_timeoutLong, flowControl)

		'Verify text is exist'
		if(isExist) {
			String result =  MobileDriver.getText(this.lblFavoriteItem(txt), GlobalVariable.G_timeoutMedium).trim()
			BaseAssert.equals(result, txt, 'Verify result search is vaild')
			return true
		}
		'Verify text is not exist'
		MobileDriver.verifyElementNotVisible(this.lblFavoriteItem(txt), GlobalVariable.G_timeoutLong, flowControl)
		return true
	}

	/**
	 * Select favourite
	 */
	boolean selectFav(String txt, int timeout = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		MobileDriver.takeScreenshot()
		'Select Search'
		MobileDriver.tapEleByTestObject(this.imgSearch())
		'Enter text'
		MobileDriver.setTextNoObjAppium(txt)
		MobileDriver.takeScreenshot()
		'Select first list'
		MobileDriver.tapEleByTestObject(this.lblFavoriteItem(txt))
		MobileDriver.delay(10)
	}

	/**
	 * Select favourite
	 */
	boolean selectFavourite(String txt, FailureHandling flowControl = defaultFailureHandling) {
		MobileDriver.takeScreenshot()
		'Select Search'
		MobileDriver.tapEleByTestObject(this.imgSearch())
		'Enter text'
		MobileDriver.setTextNoObjAppium(txt)
		MobileDriver.takeScreenshot()
		if (waitForElementPresent(this.lblFavoriteItem(txt), GlobalVariable.G_timeoutMedium)) {
			'Select first list'
			MobileDriver.tapEleByTestObject(this.lblFavoriteItem(txt))
			MobileDriver.delay(GlobalVariable.G_timeoutShort)
		}
		else {
			handleError('Bussiness Error:  Not Found fav', flowControl)
		}
	}

	/**
	 * Select Transfer Now
	 */
	void confirmation(String text, FailureHandling flowControl = defaultFailureHandling) {
		tap(commonPage.lblDynamic(UIBtnTextTransferNow), GlobalVariable.G_timeoutShort, flowControl)
	}

	/**
	 * Verify field validation enter amount
	 */
	void verifyFieldValidationEnterAmount(String maxLimit = "999999999", String minLimit = "1", FailureHandling flowControl = defaultFailureHandling) {
		'Enter Max Limit'
		commonPage.enterNO(maxLimit, false)
		'Get amount inputed'
		String txtInput = MobileDriver.getText(commonPage.txtEditNo(1), GlobalVariable.G_timeoutLong, flowControl)
		'Verify amount inputed is equal 999,999.99'
		Mobile.verifyEqual(txtInput, UIMaxLimit)
		MobileDriver.takeScreenshot()
		'Select tick button'
		commonPage.tapTickBtn()
		'Verify Enter Recipient Reference is visible'
		MobileDriver.verifyElementVisible(commonPage.lblDynamic(UIEnterRecipientReference), GlobalVariable.G_timeoutLong, flowControl)

		'Back'
		MobileDriver.tap(commonPage.imgNoDynamic(1), GlobalVariable.G_timeoutLong, flowControl)

		'Remove Max Limit'
		MobileDriver.verifyElementVisible(commonPage.lblDynamic(UIlblEnterAmount), GlobalVariable.G_timeoutLong, flowControl)
		'Clear amount'
		commonPage.removeEnterNo(maxLimit.length())

		'Enter min limit'
		commonPage.enterNO(minLimit)
		MobileDriver.takeScreenshot()
		'Verify Enter Recipient Reference is visible'
		MobileDriver.verifyElementVisible(commonPage.lblDynamic(UIEnterRecipientReference), GlobalVariable.G_timeoutLong, flowControl)
	}

	/**
	 * Enter amount
	 */
	void enterAmount(String amount, FailureHandling flowControl = defaultFailureHandling) {
		commonPage.enterNO(amount, true, UIlblEnterAmount, 5, flowControl	)
	}

	/**
	 * Verify recipient reference
	 */
	void recipientReference(String text, FailureHandling flowControl = defaultFailureHandling) {
		Mobile.delay(1)
		'Verify Recipient Reference is visible'
		verifyElementVisible(this.txtRecipient(), GlobalVariable.G_timeoutLong, flowControl)
		'Enter Recipient Reference'
		setText(this.txtRecipient(), text, GlobalVariable.G_timeoutLong, flowControl)
		MobileDriver.takeScreenshot()
		'Select Continue'
		tap(commonPage.lblDynamic(UITextContinue), GlobalVariable.G_timeoutLong, flowControl)
	}

	/**
	 * Verify recipient reference
	 */
	void verifyRecipientReference(String specialRecipientReferenceData = '#123*@', String maxRecipientData = 'Recipient Reference Up To 20', FailureHandling flowControl = defaultFailureHandling) {
		Mobile.delay(1)
		'Enter special RR'
		verifyElementVisible(this.txtRecipient(), GlobalVariable.G_timeoutLong, flowControl)
		setText(this.txtRecipient(), specialRecipientReferenceData, GlobalVariable.G_timeoutLong, flowControl)
		'Select Continue'
		tap(commonPage.lblDynamic(UITextContinue), GlobalVariable.G_timeoutLong, flowControl)
		'Verify Please remove invalid special characters.'
		verifyElements([UIRecipientInValid], commonPage.lblDynamicPath(), GlobalVariable.G_timeoutLong)
		MobileDriver.takeScreenshot()

		'Enter Max length RR'
		setText(this.txtRecipient(), maxRecipientData, GlobalVariable.G_timeoutLong, flowControl)
		String inputRecipient = MobileDriver.getText(this.txtRecipient(), GlobalVariable.G_timeoutLong, flowControl)
		'Verify Recipient Reference less than or equal to 20'
		BaseAssert.equals(inputRecipient.length() <= 20, true, 'Recipient Reference less than or equal to 20')
		MobileDriver.takeScreenshot()
	}

	/**
	 * Confirm transfer for G1A
	 */
	void confirmG1A(String accountNumber, boolean isFuture = false, FailureHandling flowControl = defaultFailureHandling) {
		'Wait for Global Access Account is visible'
		waitForElementPresent(commonPage.lblDynamic(UIGlobalAccessAccount), GlobalVariable.G_timeoutLong, flowControl)
		Mobile.scrollToText(UIGlobalAccessAccount)
		'Wait for Transfer Now is visible'
		waitForElementPresent(commonPage.lblDynamic(UIBtnTextTransferNow), GlobalVariable.G_timeoutLong, flowControl)
		'Wait for checked icon (v) is visible'
		waitForElementPresent(this.imgV(), GlobalVariable.G_timeoutLong, flowControl)
		'If is future, select tomorrow today'
		if(isFuture) {
			Mobile.scrollToText(UIToday)
			MobileDriver.tap(commonPage.lblDynamic(UIToday), GlobalVariable.G_timeoutLong, flowControl)
			Mobile.takeScreenshot()
			Mobile.delay(GlobalVariable.G_timeoutMiniShort)
			commonPage.tomorrowDatePicker()
		}
		Mobile.delay(GlobalVariable.G_timeoutMiniShort)
		MobileDriver.takeScreenshot()
		'Select Transfer Now'
		tap(commonPage.lblDynamic(UIBtnTextTransferNow), GlobalVariable.G_timeoutLong, flowControl)
		Mobile.delay(GlobalVariable.G_timeoutMiniShort)
	}

	/**
	 * Confirm transfer
	 */
	void confirm(boolean isFuture = false, FailureHandling flowControl = defaultFailureHandling) {
		'Wait for Transfer Now is visible'
		waitForElementPresent(commonPage.lblDynamic(UIBtnTextTransferNow), GlobalVariable.G_timeoutLong, flowControl)
		'Wait for checked icon (v) is visible'
		waitForElementPresent(this.imgV(), GlobalVariable.G_timeoutLong, flowControl)
		'If is future, select tomorrow today'
		if(isFuture) {
			MobileDriver.tap(commonPage.lblDynamic(UIToday), GlobalVariable.G_timeoutLong, flowControl)
			Mobile.takeScreenshot()
			Mobile.delay(1)
			commonPage.tomorrowDatePicker()
		}
		commonPage.waitLoading()
		getDateTransfer()
		Mobile.delay(5)
		MobileDriver.takeScreenshot()
		'Select Transfer Now'
		tap(commonPage.lblDynamic(UIBtnTextTransferNow), GlobalVariable.G_timeoutLong, flowControl)
		Mobile.delay(5)
	}

	/**
	 * Open new transfer others
	 */
	void openNewTransferOthers(String moduleName, String bankingName, FailureHandling flowControl = defaultFailureHandling) {
		'Select moduleName'
		MobileDriver.tapEleByTestObject(commonPage.lblDynamic(moduleName))
		Mobile.delay(GlobalVariable.G_timeoutMiniShort)
		MobileDriver.takeScreenshot()
		'Select bankingName'
		MobileDriver.tapEleByTestObject(commonPage.lblDynamic(bankingName))
	}
	/**
	 * Select UI Text Continue
	 */
	void selectContinueButton(FailureHandling flowControl = defaultFailureHandling) {
		'Select UI Label Continue'
		Mobile.tap(commonPage.lblDynamic(UITextContinue), GlobalVariable.G_timeoutMedium, flowControl)
	}

	/**
	 * Receipient's Bank Details & Receipient's details
	 */
	void verifyImportant(FailureHandling flowControl = defaultFailureHandling) {
		Mobile.delay(GlobalVariable.G_timeoutMiniShort)
		'Select UI Label Continue'
		Mobile.verifyElementVisible(commonPage.lblDynamic(UILblImportant), GlobalVariable.G_timeoutMedium)
		Mobile.tap(commonPage.lblDynamic(UITextContinue), GlobalVariable.G_timeoutMedium, flowControl)
	}
	/**
	 * Set account number
	 */
	void setAccountNumber(accountNo, FailureHandling flowControl = defaultFailureHandling) {
		'Enter Account Number'
		setText(this.txtAccountNo(), accountNo, GlobalVariable.G_timeoutLong, flowControl)
		MobileDriver.takeScreenshot()
		'Select Continue'
		tap(commonPage.lblDynamic(UITextContinue), GlobalVariable.G_timeoutLong, flowControl)
		MobileDriver.delay(7)
		MobileDriver.takeScreenshot()
		'Verify Enter Amount is visible'
		Mobile.waitForElementPresent(commonPage.lblDynamic(UIlblEnterAmount), GlobalVariable.G_timeoutLong, flowControl)
	}

	/**
	 * Set account number
	 */
	void setAccountNumberAndTransferType(String accountNo, String transferType, String transferMode, FailureHandling flowControl = defaultFailureHandling) {
		'Enter Account Number'
		setText(this.txtAccountNo(), accountNo, GlobalVariable.G_timeoutLong, flowControl)
		MobileDriver.takeScreenshot()
		'Select transfer type'
		MobileDriver.tap(commonPage.lblDynamic('Transfer type'), GlobalVariable.G_timeoutMedium, flowControl)
		MobileDriver.tap(commonPage.imgArrowDownDropdown('Transfer type', 1), GlobalVariable.G_timeoutMedium, flowControl)
		MobileDriver.tap(commonPage.lblDynamic(transferType), GlobalVariable.G_timeoutMedium, flowControl)
		MobileDriver.tap(commonPage.lblDynamic(UIDoneBtn), GlobalVariable.G_timeoutLong, flowControl);
		'Select Transfer Mode'
		MobileDriver.tap(commonPage.lblDynamic(UIDuitNowTransfer), GlobalVariable.G_timeoutLong, flowControl);
		if(MobileDriver.waitForElementNotPresent(commonPage.lblDynamic(UIDoneBtn), GlobalVariable.G_timeoutMedium)) {
			MobileDriver.tap(commonPage.lblDynamic(UIDuitNowTransfer), GlobalVariable.G_timeoutLong, flowControl);
		}
		MobileDriver.tap(commonPage.lblDynamic(transferMode), GlobalVariable.G_timeoutLong, flowControl);
		MobileDriver.tap(commonPage.lblDynamic(UIDoneBtn), GlobalVariable.G_timeoutLong, flowControl);
		'Select Continue'
		tap(commonPage.lblDynamic(UITextContinue), GlobalVariable.G_timeoutLong, flowControl)
		MobileDriver.delay(GlobalVariable.G_timeoutMiniShort)
		MobileDriver.takeScreenshot()
		'Verify Enter Amount is visible'
		Mobile.waitForElementPresent(commonPage.lblDynamic(UIlblEnterAmount), GlobalVariable.G_timeoutLong, flowControl)
	}

	/**
	 * Verify set account number
	 */
	void verifySetAccountNumber(String invalidNo = '111111111112', FailureHandling flowControl = defaultFailureHandling) {
		MobileDriver.takeScreenshot()
		'Enter invalid Account Number'
		setText(this.txtAccountNo(), invalidNo, GlobalVariable.G_timeoutLong, flowControl)
		'Select Continue'
		tap(commonPage.lblDynamic(UITextContinue), GlobalVariable.G_timeoutLong, flowControl)
		'Verify error message'
		MobileDriver.verifyElementVisible(commonPage.lblDynamic(UIMsgTransfer), GlobalVariable.G_timeoutLong, flowControl)
		MobileDriver.takeScreenshot()
		Mobile.delay(5)
	}

	/**
	 * Verify field validation confirmation
	 */
	void verifyFieldValidationConfirmation(String editAmount, String oldRecipientReference, String editRecipientReference, String editOption, FailureHandling flowControl = defaultFailureHandling) {
		MobileDriver.delay(10)
		MobileDriver.takeScreenshot()
		'1. Edit Amount'
		MobileDriver.tap(commonPage.lblRMNo(1), GlobalVariable.G_timeoutLong, flowControl)
		'Verify Enter Amount is visible'
		Mobile.verifyElementVisible(commonPage.lblDynamic(UIlblEnterAmount), GlobalVariable.G_timeoutLong, flowControl)
		'Enter Amount'
		commonPage.enterNO(editAmount)
		'Verify Confirmation is visible'
		MobileDriver.verifyElementVisible(commonPage.lblDynamic(UIConfirmation), GlobalVariable.G_timeoutLong, flowControl)
		'Scroll to Recipient Reference'
		Mobile.scrollToText(UIRecipientReference)
		'2. Edit Recipient Reference'
		MobileDriver.tap(commonPage.lblDynamic(oldRecipientReference), GlobalVariable.G_timeoutLong, flowControl)
		'Verify Edit Recipient Reference is visible'
		this.recipientReference(editRecipientReference)
		Mobile.delay(GlobalVariable.G_timeoutMiniShort)
		MobileDriver.verifyElementVisible(commonPage.lblDynamic(editRecipientReference), GlobalVariable.G_timeoutLong, flowControl)
		Mobile.delay(10)
		MobileDriver.takeScreenshot()
		'3. Add payment details'
		'Scroll down'
		commonPage.swipe(5, 1000, 5, 100)
		'Scroll to Optional'
		Mobile.scrollToText(UIOptional)
		'Select Optional'
		MobileDriver.tap(commonPage.lblDynamic(UIOptional), GlobalVariable.G_timeoutLong, flowControl)
		MobileDriver.verifyElementVisible(commonPage.lblDynamic(UITextContinue), GlobalVariable.G_timeoutLong, flowControl)
		Mobile.delay(3)
		'Input text'
		MobileDriver.setText(commonPage.txtEditNo(2), editOption, GlobalVariable.G_timeoutLong, flowControl)
		'Select Continue'
		Mobile.tap(commonPage.lblDynamic(UITextContinue), GlobalVariable.G_timeoutLong, flowControl)
		'Verify Confirmation is visible'
		Mobile.verifyElementVisible(commonPage.lblDynamic(UIConfirmation), GlobalVariable.G_timeoutLong, flowControl)
		'Scroll down'
		commonPage.swipe(5, 1000, 5, 100)
		'Verify Edit option is visible'
		MobileDriver.verifyElementVisible(commonPage.lblDynamic(editOption), GlobalVariable.G_timeoutLong, flowControl)
		MobileDriver.takeScreenshot()
		return
	}

	/**
	 * Verify authorisation
	 */
	String authorize(String approval, FailureHandling flowControl = defaultFailureHandling) {
		'If Transaction Authorisation Code is visible, get and return TAC'
		if(MobileDriver.waitForElementPresent(commonPage.lblDynamic(UITransaction), GlobalVariable.G_timeoutShort, FailureHandling.OPTIONAL)) {
			return secure2uAuthorisationPage.authorizeWithTacOrOTP(approval)
		}else {
			Mobile.delay(GlobalVariable.G_timeoutShort)
			'If Secure2u Authorisation is visible, perform approval'
			if(waitForElementPresent(commonPage.lblDynamic(UISecure2uAuthorisation), GlobalVariable.G_timeoutShort, FailureHandling.OPTIONAL)) {
				verifyElements(UIStaticApprove, commonPage.lblTextMatchesPath(), GlobalVariable.G_timeoutLong)
				MobileDriver.takeScreenshot()
				selectApproval(approval)
			}
		}
		return approval
	}

	String authorizeS2UV5BioPin(String approval, FailureHandling flowControl = defaultFailureHandling) {
		'S2U V5 and Bio pin authorization'
		'If Transaction Authorisation Code is visible, get and return TAC'
		if(MobileDriver.waitForElementPresent(commonPage.lblDynamic(UITransaction), GlobalVariable.G_timeoutShort, FailureHandling.OPTIONAL)) {
			return secure2uAuthorisationPage.authorizeWithTacOrOTP(approval)
		}else {
			Mobile.delay(GlobalVariable.G_timeoutShort)
			'If Secure2u Authorisation is visible, perform approval'
			if(waitForElementPresent(commonPage.lblDynamic(UISecure2uAuthorisation), GlobalVariable.G_timeoutShort, FailureHandling.OPTIONAL)) {
				verifyElements(UIStaticApprove, commonPage.lblTextMatchesPath(), GlobalVariable.G_timeoutLong)
				MobileDriver.takeScreenshot()
				selectApproval(approval)
			}
		}
		MobileDriver.takeScreenshot()
		commonPage.checkEnterS2UV5PIN(GlobalVariable.G_timeoutMiniShort)
		return approval
	}

	String g1AAuthorize(String approval, FailureHandling flowControl = defaultFailureHandling) {
		'If Transaction Authorisation Code is visible, get and return TAC'
		if(MobileDriver.waitForElementPresent(commonPage.lblDynamic(UITransaction), GlobalVariable.G_timeoutShort, FailureHandling.OPTIONAL)) {
			return secure2uAuthorisationPage.authorizeWithTacOrOTP(approval)
		}else {
			Mobile.delay(GlobalVariable.G_timeoutMiniShort)
			'If Secure2u Authorisation is visible, perform approval'
			if(waitForElementPresent(commonPage.lblDynamic(UISecure2uAuthorisation), GlobalVariable.G_timeoutShort, FailureHandling.OPTIONAL)) {
				scrollToAndVerifyTextInList(G1AUIStaticApprove, commonPage.lblTextMatchesPath(), GlobalVariable.G_timeoutLong)
				MobileDriver.takeScreenshot()
				selectApproval(approval)
			}
		}
		return approval
	}

	/**
	 * Select approval
	 */
	void selectApproval(String status, FailureHandling flowControl = defaultFailureHandling) {
		'If status is Expired, wait for Done button is visible'
		'If status is Reject, select Reject'
		'If status is Approve, select Approve'
		switch(status) {
			case 'Expired':
				MobileDriver.waitForElementPresent(commonPage.lblDynamic(UIDoneBtn), 60 + GlobalVariable.G_timeoutLong, flowControl)
				break
			case 'Reject':
				MobileDriver.tap(commonPage.lblDynamic(UIReject), 60 + GlobalVariable.G_timeoutLong, flowControl)
				break
			default:
				MobileDriver.tap(commonPage.lblDynamic(UIApprove), GlobalVariable.G_timeoutLong, flowControl)
				info(status + ' is tapped.')
				break
		}
	}

	/**
	 * Add to favourites
	 */
	void addToFavourites(favName = 'Third Party', String specialFavourites = '!@#$%^&*12', FailureHandling flowControl = defaultFailureHandling) {
		'Add Favourites'
		MobileDriver.tap(commonPage.lblDynamic(UIAddToFavourites), GlobalVariable.G_timeoutLong, flowControl)
		Mobile.delay(2)

		'Enter special Favourites'
		MobileDriver.setText(commonPage.txtEditNo(1), specialFavourites, GlobalVariable.G_timeoutLong, flowControl)
		MobileDriver.takeScreenshot()
		MobileDriver.tap(commonPage.lblDynamic(UIAddToFavourites), GlobalVariable.G_timeoutLong, flowControl)

		Mobile.verifyEqual(MobileDriver.getText(this.lblSpecialAddFav(), GlobalVariable.G_timeoutLong, flowControl), MsgSpecialAddFavourite)

		MobileDriver.takeScreenshot()
		'Verify Special Favourites'
		MobileDriver.tap(this.lblSpecialAddFav(), GlobalVariable.G_timeoutLong, flowControl)

		'Enter Favourites'
		MobileDriver.setText(commonPage.txtEditNo(1), favName, GlobalVariable.G_timeoutLong, flowControl)
		MobileDriver.takeScreenshot()
		MobileDriver.tap(commonPage.lblDynamic(UIAddToFavourites), GlobalVariable.G_timeoutLong, flowControl)
		Mobile.verifyElementExist(commonPage.lblDynamic(UIDoneBtn), GlobalVariable.G_timeoutLong, flowControl)
		String msg = MobileDriver.getText(this.lblMessage(), GlobalVariable.G_timeoutLong, flowControl)
		MobileDriver.takeScreenshot()
		'If transfer already added to favourites, verify Record already exists.'
		'Else verify Successful'
		if(msg != null) {
			if (msg == MsgExistAddFavourite) {
				Mobile.verifyEqual(msg, MsgExistAddFavourite)
			}else {
				BaseAssert.equals(msg.contains(MsgSuccessAddFavourite), true, "Verify add to favorite message")
			}
		}
		MobileDriver.delay(GlobalVariable.G_timeoutMiniShort)
		MobileDriver.takeScreenshot()
	}

	/**
	 * Share receipts
	 */
	void shareReceipts(String RefID, boolean isFuture, FailureHandling flowControl = defaultFailureHandling) {
		'Verify Share Receipts is visible'
		verifyElements([UIShareReceipt], commonPage.lblDynamicPath(), GlobalVariable.G_timeoutLong)
		'Select Share Receipts'
		MobileDriver.tap(commonPage.lblDynamic(UIShareReceipt), GlobalVariable.G_timeoutLong, flowControl)
		Mobile.delay(3)
		'If is future, verify Pending'
		'Else verify Successful'
		if(isFuture) {
			MobileDriver.verifyElementVisible(commonPage.lblNormalizeSpace(UIPending), GlobalVariable.G_timeoutLong, flowControl)
		}else {
			if(MobileDriver.waitForElementPresent(commonPage.lblDynamic(UISuccessful), GlobalVariable.G_timeoutShort, flowControl)) {
				Mobile.verifyElementVisible(commonPage.lblDynamic(UISuccessful), GlobalVariable.G_timeoutLong, flowControl)
			}
		}
		'Verify Reference ID is visible'
		if(waitForElementPresent(commonPage.lblDynamic(UIReferenceID), GlobalVariable.G_timeoutMedium, FailureHandling.OPTIONAL)) {
			scrollToAndVerifyTextInList(UIstaticText + [RefID], commonPage.lblDynamicPath(), GlobalVariable.G_timeoutLong)
		}
		MobileDriver.takeScreenshot()
		'Select share'
		Mobile.tap(commonPage.imgNoDynamic(2), GlobalVariable.G_timeoutLong, flowControl)
		Mobile.delay(3)
		Mobile.pressBack()
		Mobile.delay(1)

		'Back'
		Mobile.tap(commonPage.imgNoDynamic(1), GlobalVariable.G_timeoutLong, flowControl)
		verifyElements([UIDoneBtn], commonPage.lblDynamicPath(), GlobalVariable.G_timeoutLong)
	}

	/**
	 * Verify status screen
	 */
	String statusScreen(String status, boolean isAddFavourites, boolean isFuture, boolean shouldGetStatusForApproval = false, FailureHandling flowControl = defaultFailureHandling) {
		'If status is TAC, return'
		if(status.contains('TAC')) {
			return
		}

		'Get Reference ID'
		String RefID = ""
		if(MobileDriver.waitForElementPresent(commonPage.lblSiblingDynamic(UIReferenceID), GlobalVariable.G_timeoutLong, FailureHandling.OPTIONAL)) {
			RefID = MobileDriver.getText(commonPage.lblSiblingDynamic(UIReferenceID), GlobalVariable.G_timeoutLong, flowControl)
		} else {
			MobileDriver.takeScreenshot()
			MobileDriver.tap(commonPage.lblDynamic(UIDoneBtn), GlobalVariable.G_timeoutMedium, FailureHandling.STOP_ON_FAILURE)
		}

		'Verify Reference ID format'
		if(Mobile.verifyMatch(RefID, '[0-9]{9}M', true)) {
			info('Reference ID follows the format 10 characters with first 9 is numbers and end with M')
		}

		'If is not future, verify Date & time'
		if(!isFuture) {
			'2. Date & time'
			String dateName = MobileDriver.getText(this.lblTransferSummaryDateTimeTitle(), GlobalVariable.G_timeoutLong, flowControl)
			String dateTime = Mobile.getText(commonPage.lblSiblingDynamic(dateName), 1)
			if(Mobile.verifyMatch(dateTime, '(0?[1-9]|[12][0-9]|3[01]) [A-Z]{1}[a-z]{2} [0-9]{4}, (0?[0-9]|1[0-9]|2[0-3]):[0-9]{2} [A|P]M',true)) {
				info('Date follows the format DD MMM YYYY, HH:MM (A|P)M')
			}
		}
		MobileDriver.takeScreenshot()
		'Verify status screen'
		'If status is Approve and is future, verify Transfer Scheduled'
		'If status is Approve and is not future, verify Transfer Successful or Transfer Accepted. And then add to Favourites and verify Share Receipts'
		'If status is Reject, verify Transaction Declined or Authorisation Failed or Transfer Failed or Transfer Declined'
		'If status is Expired, verify Authorisation Failed or Transfer Failed or Transaction Declined,...'
		switch(status) {
			case 'Approve':
				if(isFuture) {
					verifyElements([UITransferScheduled], commonPage.lblDynamicPath(), GlobalVariable.G_timeoutLong)
				}else {
					if(MobileDriver.waitForElementPresent(commonPage.lblDynamic(UITransferSuccessful), GlobalVariable.G_timeoutMedium, FailureHandling.OPTIONAL)) {
						'Add to Favourites'
						if (isAddFavourites) {
							this.addToFavourites()
						}
					}else {
						isAccepted = true;
						if(MobileDriver.waitForElementPresent(commonPage.lblDynamic(UITransferAccepted), GlobalVariable.G_timeoutMedium, FailureHandling.OPTIONAL)) {
							MobileDriver.verifyElementVisible(commonPage.lblDynamic(UITransferAccepted), GlobalVariable.G_timeoutLong, defaultFailureHandling)
							if (isAddFavourites) {
								this.addToFavourites()
							}
						}else {
							handleError('System Error: Transfer Failed', flowControl)
						}
						MobileDriver.takeScreenshot()
					}
				}
				'Share Receipts'
				this.shareReceipts(RefID, isFuture)
				break
			case 'Reject':
				String failedMsg = Mobile.getText(commonPage.lblTextNo(1), GlobalVariable.G_timeoutLong, flowControl)
				info(failedMsg)
				if(MobileDriver.waitForElementPresent(commonPage.lblDynamic(UIAuthorisationFailed), GlobalVariable.G_timeoutMedium, FailureHandling.OPTIONAL)) {
					MobileDriver.verifyElementVisible(commonPage.lblDynamic(UIAuthorisationFailed), GlobalVariable.G_timeoutMedium, flowControl)
				}else if(MobileDriver.waitForElementPresent(commonPage.lblDynamic(UITransferFailed), GlobalVariable.G_timeoutShort, FailureHandling.OPTIONAL)) {
					MobileDriver.verifyElementVisible(commonPage.lblDynamic(UITransferFailed), GlobalVariable.G_timeoutMedium, flowControl)
				}else {
					MobileDriver.verifyElementVisible(commonPage.lblDynamic(UITransactionDeclined), GlobalVariable.G_timeoutMedium, flowControl)
				}
				MobileDriver.takeScreenshot()
				break
			case 'Expired':
				'verify titile'
				if(MobileDriver.waitForElementPresent(commonPage.lblDynamic(UIAuthorisationFailed), GlobalVariable.G_timeoutMedium, FailureHandling.OPTIONAL)) {
					MobileDriver.verifyElementVisible(commonPage.lblDynamic(UIAuthorisationFailed), GlobalVariable.G_timeoutMedium, flowControl)
				}else if(MobileDriver.waitForElementPresent(commonPage.lblDynamic(UITransferFailed), GlobalVariable.G_timeoutShort, FailureHandling.OPTIONAL)) {
					MobileDriver.verifyElementVisible(commonPage.lblDynamic(UITransferFailed), GlobalVariable.G_timeoutMedium, flowControl)
				}else if(MobileDriver.waitForElementPresent(commonPage.lblDynamic(UITransactionDeclined), GlobalVariable.G_timeoutShort, FailureHandling.OPTIONAL)){
					MobileDriver.verifyElementVisible(commonPage.lblDynamic(UITransactionDeclined), GlobalVariable.G_timeoutMedium, flowControl)
				}else {
					'Not title'
					MobileDriver.verifyElementVisible(commonPage.lblDynamic(UIS2uExpired), GlobalVariable.G_timeoutMedium, flowControl)
				}
				'verify description'
				if(MobileDriver.waitForElementPresent(commonPage.lblDynamic(UIS2uExpired), GlobalVariable.G_timeoutMedium, FailureHandling.OPTIONAL)) {
					MobileDriver.verifyElementVisible(commonPage.lblDynamic(UIS2uExpired), GlobalVariable.G_timeoutMedium, flowControl)
				}else if(MobileDriver.waitForElementPresent(commonPage.lblDynamic(UIS2uAuthorisationExpired), GlobalVariable.G_timeoutMedium, FailureHandling.OPTIONAL)) {
					MobileDriver.verifyElementVisible(commonPage.lblDynamic(UIS2uAuthorisationExpired), GlobalVariable.G_timeoutMedium, flowControl)
				}

				break
			case 'Insufficient Amount':
				try {
					verifyElements(UIStaticInsufficientAmountStatus, commonPage.lblDynamicPath(), GlobalVariable.G_timeoutLong)
					verifySomeElementsMatchInList([
						UIStaticInsufficient,
						UIExceedsMaxLimit
					], commonPage.lblDynamicPath(), GlobalVariable.G_timeoutLong)
					break
				} finally {
					MobileDriver.takeScreenshot()
					'Select Done'
					Mobile.tap(commonPage.lblDynamic(UIDoneBtn), GlobalVariable.G_timeoutLong, FailureHandling.STOP_ON_FAILURE)
				}
		}
		MobileDriver.takeScreenshot()
		'Select Done'
		Mobile.tap(commonPage.lblDynamic(UIDoneBtn), GlobalVariable.G_timeoutLong, flowControl)
		if (shouldGetStatusForApproval  && isAccepted) {
			RefID = RefID + ','+ UITransferAccepted;
		}
		return RefID
	}

	/**
	 * Verify transfer notification message
	 */
	boolean verifyTransferNotificationMessage(String accountNumber, String refId, double amount, FailureHandling flowControl = defaultFailureHandling) {
		'Get last 4 digit account number'
		String acc4Digit = accountNumber.substring(accountNumber.length() - 4)
		'Format amount to float string'
		String formattedAmount = String.format("%.2f", amount)
		String messagePN="You've transferred RM ${formattedAmount} to (.+)'s VCB account ending [*]{4}${acc4Digit}. REF: ${refId}"

		'Verify notification message'
		commonPage.verifyDeviceNotification("Vcb2u: Transfer", "You've transferred", messagePN)
	}

	/**
	 * Open Duitnow flow
	 */
	void openDuitnowFlow(FailureHandling flowControl = defaultFailureHandling) {
		'Select Others'
		MobileDriver.tapEleByTestObject(commonPage.lblDynamic('Others'))
	}
	/**
	 * Select account to transfer
	 */
	void selectAccountTransferto(String accountName, FailureHandling flowControl = defaultFailureHandling) {
		MobileDriver.tap(commonPage.lblContainDynamic(accountName),  GlobalVariable.G_timeoutLong, flowControl)
		delay( GlobalVariable.G_timeoutMiniShort)
	}

	void selectAccountTranferFromNo(String accountName, int no = 1, FailureHandling flowControl = defaultFailureHandling) {
		MobileDriver.tap(commonPage.lblContainDynamicNo(accountName, no),  GlobalVariable.G_timeoutLong, flowControl)
		delay( GlobalVariable.G_timeoutMiniShort)
	}

	void openOverseasTransferFlow(flowControl = defaultFailureHandling) {
		Mobile.tap(commonPage.lblDynamic(UIOverseas), GlobalVariable.G_timeoutLong, flowControl)
	}

	String constructNotificationTemplate(String accountNumber, String refId, String amount, int timeout = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		'Get last 4 digit account number'
		String acc4Digit = accountNumber.substring(accountNumber.length() - 4)
		String messagePN="You've transferred RM ${amount} to your own account ending ${acc4Digit}. Did not perform this? Call Vcb now. REF:${refId}"
	}
	String constructNotificationTemplateFTT(String amount, String foreignCurrencyAmount, String beneficiaryName, String receivingBank, String beneficiaryAccountNumber ,  String refId, int timeout = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		'Get last 4 digit account number'
		String acc4Digit = beneficiaryAccountNumber.substring(beneficiaryAccountNumber.length() - 4)
		String messagePN="You've transferred RM ${amount} (${foreignCurrencyAmount}) to ${beneficiaryName}'s ${receivingBank} ending ****${acc4Digit}. REF: ${refId}"
	}

	void getDateTransfer(int timeout = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		date = MobileDriver.getText(commonPage.lblSiblingGroup(DATE), timeout, flowControl)
		if(date.equals("Today")) {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMM yyyy");
			date = LocalDate.now().format(formatter);
		}
	}
}
