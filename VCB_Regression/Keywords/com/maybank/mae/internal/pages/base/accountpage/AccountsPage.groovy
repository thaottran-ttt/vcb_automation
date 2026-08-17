package com.maybank.mae.internal.pages.base.accountpage

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit

import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testobject.TestObject
import com.maybank.assertions.BaseAssert
import com.maybank.common.MobileDriver
import com.maybank.mae.internal.pages.base.BasePage
import com.maybank.mae.internal.pages.base.CommonPage
import com.maybank.mae.internal.pages.base.accountpage.AccountDetailPage
import com.maybank.mae.internal.pages.base.transferpage.TransferPage
import com.maybank.utils.StringUtils
import com.maybank.mae.internal.pages.base.HomePage

import internal.GlobalVariable

public abstract class AccountsPage extends BasePage{
	abstract TestObject fraScrollTab()
	abstract TestObject btnTab(String tapName)
	abstract TestObject btnGotIt()
	abstract TestObject btnSearch()
	abstract TestObject btnStick(boolean isG1A)
	abstract TestObject btnBack()
	abstract TestObject btnFirstWatchList()
	abstract TestObject btnPayBills()
	abstract TestObject fraAccountList()
	abstract TestObject cbxSetFutureOrder()

	public static CommonPage commonPage = getPage(CommonPage)
	public static AccountDetailPage accountDetailPage = getPage(AccountDetailPage)
	public static TransferPage transferPage = getPage(TransferPage)
	public static HomePage homePage = getPage(HomePage)



	static final String NO_INFORMATION = 'NO INFORMATION'
	static final String NOT_AUTHORIZED = 'NOT AUTHORIZED'
	static final String MAE_Name = 'MAE Wallet'
	static final String APPLY_FILTER = 'Apply Filter'
	static final String ADD_CURRENCY = 'Add currency'
	static final String SUCCESSFULLY_ADDED = "You've successfully added"
	static final String SUCCESSFULLY_REMOVED = "You've successfully removed"
	static final String EXCHANGE_RATE = "Exchange rate"
	static final String MANAGE = "MANAGE"
	static final String FX = "FX"
	static final String WATCHLIST = "Watchlist"
	static final String CURRENCY_CALCULATOR = "Currency Calculator"
	static final String CALCULATE = "Calculate"
	static final String RECOMMENDED_WATCHLIST = "Recommended Watchlist"
	static final String REMOVE_CURRENCY = "Remove Currency"
	static final String CONVERT_CURRENCY = "Convert Currency"
	static final String SELECT_CURRENCY = "Select currency"
	static final String CONFIRM = "Confirm"
	static final String TRANSFER_FROM = "Transfer from"
	static final String TRANSFER_TO = "Transfer to"
	static final String GET_QUOTE = "Get Quote"
	static final String PLACE_ORDER = "Place Order"
	static final String EDIT = "Edit"
	static final String VIEW_TRANSACTIONS = "View Transactions"
	static final String M2U_HISTORY = 'M2U History'
	static final String TERMS_AND_CONDITIONS = 'Terms & Conditions'
	static final String AGREE_AND_CONFIRM = 'Agree & Confirm'
	static final String lblSetFutureOrder = 'Set future order'
	static final String lblSetFutureOrderErrorMsg = 'Sorry, Set Future Order is temporarily unavailable. Please try again later.'
	static final String UIKillSwitch = 'Kill Switch'
	static final String lblCurrencyCalculatorErrorMsg = 'Sorry, Currency Calculator is temporarily unavailable. Please try again later.'
	static final String SHOW_BALANCE = 'Show Balance on Dashboard'
	static final String HIDE_BALANCE = 'Hide Balance on Dashboard'

	//UI text transaction
	String UIReferenceID = 'Reference ID'
	String UILblTransfer = 'Transfer'
	String UISecure2uAapproved = 'Your Secure2u is approved'
	String UIFutureOrderSuccessfull = 'Future order successfully set up'
	String UIAutoSweepSuccessfull = 'Auto sweep successfully activated'
	String UIAutoSweepSuccessfullDeactivated = 'Auto sweep successfully deactivated'
	String UITransactionSuccessful = 'Transaction Successful'
	String UITransactionUnsuccessful = 'Transaction Unsuccessful'
	String UITotalCurrencyBalance = 'Total Currency Balance'

	String UIAuthorisationFailed = 'Authorisation failed'
	String UIShareReceipt = 'Share Receipt'
	String UIDoneBtn = 'Done'
	String UIStepOneOfTwo = 'Step 1 of 2'
	String UIWatchListOne = 'Watchlist 1'
	String UIEditWatchlist = 'Edit Watchlist'
	String UITestEdit = 'Test Edit'
	String UIResetToDefault = 'Reset To Default Name'
	String UIAmountToReceive = 'Amount to receive'
	String UIIHaveRead = 'I have read,'
	String UITABUNG_CTA = 'Tabung'
	String UILinkOverSeas = 'Link Overseas Account'
	String UILinkM2UAccess = 'Link or Switch M2U Access'
	String UIAccount = 'Account'
	
	static final String MASKED_CARD_REGEX = /^(\*{4}\s){3}\d{4}$/
	static final String FULL_CARD_REGEX   = /^(\d{4}\s){3}\d{4}$/
	static final String EXPIRY_DATE_REGEX = /^(0[1-9]|1[0-2])\/\d{2}$/

	/**
	 * getAllAccountItem
	 * @return
	 */
	abstract List<List<String>> getAllAccountItem()

	/**
	 * checkAndTapGotIt
	 * @return
	 */
	void checkAndTapGotIt(FailureHandling flowControl = defaultFailureHandling) {
		if(MobileDriver.waitForElementPresent(this.btnGotIt(), GlobalVariable.G_timeoutShort)) {
			MobileDriver.tap(this.btnGotIt(), GlobalVariable.G_timeoutShort, flowControl)
			Mobile.delay(GlobalVariable.G_timeoutShort, flowControl)
		}
	}

	/**
	 * selectTab: Select name on tab item list
	 * @return
	 */
	void selectTab(String tabName, FailureHandling flowControl = defaultFailureHandling) {
		this.checkAndTapGotIt(flowControl)

		boolean findResult = commonPage.scrollToTestObject(this.fraScrollTab(), this.btnTab(tabName), ScrollDirectionEnum.RIGHT, GlobalVariable.G_timeoutShort, flowControl)

		if(findResult) {
			MobileDriver.tap(this.btnTab(tabName), GlobalVariable.G_timeoutLong, flowControl)
		}
	}
	
	/**
	 * selectTab: Select name on tab item list
	 * @return
	 */
	void selectCard(String cardName, FailureHandling flowControl = defaultFailureHandling) {
		MobileDriver.verifyElementExist(commonPage.lblDynamic(cardName), GlobalVariable.G_timeoutLong, flowControl)
		MobileDriver.tap(commonPage.lblDynamic(cardName), GlobalVariable.G_timeoutLong, flowControl)
	}
	
	
	/**
	 * verifyCardInfo: verify card number format, get expired date
	 */
	List<String> verifyCardInfo(
        String cardRegex = MASKED_CARD_REGEX,
        String expiryRegex = EXPIRY_DATE_REGEX,
        FailureHandling flowControl = defaultFailureHandling) {

	    String cardNumber = Mobile.getText(commonPage.lblTextNo(2), GlobalVariable.G_timeoutMedium, flowControl).trim()
	    String expiryDate = Mobile.getText(commonPage.lblTextNo(3), GlobalVariable.G_timeoutMedium, flowControl).trim()
	
	    Mobile.verifyMatch(cardNumber, cardRegex, true, flowControl)
	    Mobile.verifyMatch(expiryDate, expiryRegex, true, flowControl)
	
	    return [cardNumber, expiryDate]
	}
	

	/**
	 * Tap Kill Switch
	 */
	public void selectKillSwitch(int timeOut = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		MobileDriver.tap(commonPage.lblDynamic(UIKillSwitch), timeOut, flowControl)
	}

	/**
	 * navigateToPayBillsScreen
	 * @return
	 */
	void navigateToPayBillsScreen(String accountName, String password) {
		'Enter PIN'
		commonPage.checkEnterPIN()

		'Select account name'
		this.selectTab(accountName)

		'Select PayBills button'
		this.selectPayBillsButton()

		'Enter password'
		commonPage.checkEnterPassword(password)
	}

	/**
	 * selectTransferButton
	 * @return
	 */
	void selectTransferButton(FailureHandling flowControl = defaultFailureHandling) {
		Mobile.delay(GlobalVariable.G_timeoutMiniShort)
		Mobile.takeScreenshot()
		if(MobileDriver.waitForElementPresent(commonPage.lblDynamic(UILblTransfer), GlobalVariable.G_timeoutMedium)) {
			'Select Transfer Button'
			MobileDriver.tap(commonPage.lblDynamic(UILblTransfer), GlobalVariable.G_timeoutMedium, flowControl)
		}else {
			'Swipe Up'
			commonPage.swipeByW3CAction(500, 1200, 500, 300)
			'Select Transfer Button'
			MobileDriver.tap(commonPage.lblDynamic(UILblTransfer), GlobalVariable.G_timeoutMedium, flowControl)
		}
		Mobile.delay(GlobalVariable.G_timeoutMiniShort)
	}

	void navigateToFx(FailureHandling flowControl = defaultFailureHandling) {
		commonPage.scrollUpDynamic(300)
		Mobile.delay(GlobalVariable.G_timeoutMiniShort)
		'Select FX Button'
		MobileDriver.tap(commonPage.lblDynamic(FX), GlobalVariable.G_timeoutMedium, flowControl)
		if(MobileDriver.waitForElementNotPresent(commonPage.lblDynamic(CURRENCY_CALCULATOR), GlobalVariable.G_timeoutMedium)) {
			commonPage.scrollUpDynamic(300)
			Mobile.delay(GlobalVariable.G_timeoutMiniShort)
			MobileDriver.tap(commonPage.lblDynamic(FX), GlobalVariable.G_timeoutMedium, flowControl)
		}
		commonPage.scrollUpDynamic(800)
	}

	String getG1ABalance(FailureHandling flowControl = defaultFailureHandling) {
		'Swipe Down'
		commonPage.swipeByW3CAction(500, 500, 500, 1500)
		String amount = MobileDriver.getText(commonPage.lblSiblingDynamic(UITotalCurrencyBalance), GlobalVariable.G_timeoutShort, FailureHandling.OPTIONAL)
	}

	String viewTransactionG1A(String refID, int timeout = GlobalVariable.G_timeoutMedium ,FailureHandling flowControl = defaultFailureHandling) {
		MobileDriver.tap(commonPage.lblDynamic(VIEW_TRANSACTIONS), GlobalVariable.G_timeoutLong, flowControl)
		delay(GlobalVariable.G_timeoutShort)
		'tap to dropdown'
		MobileDriver.tap(commonPage.imgNoDynamic(2), GlobalVariable.G_timeoutLong, flowControl)
		commonPage.selectDropdownScrollOneLine(M2U_HISTORY, GlobalVariable.G_timeoutLong, flowControl)
		commonPage.tapDoneBtn()
		delay(GlobalVariable.G_timeoutMiniShort)
		'Verify share receipt'
		if (MobileDriver.waitForElementPresent(accountDetailPage.lblShareReceiptFirstItem(), timeout)) {
			MobileDriver.tap(accountDetailPage.lblShareReceiptFirstItem(), timeout, flowControl)
			String text = MobileDriver.getText(accountDetailPage.lblShareReceiptDetailDate(), timeout)
			Mobile.comment("History Date: " + text)
			StringUtils.verifyDateTimeFormat(text)
			MobileDriver.verifyElementVisible(commonPage.lblDynamic(refID), GlobalVariable.G_timeoutMedium, flowControl)
		}
		if (MobileDriver.waitForElementPresent(accountDetailPage.lblShareReceiptFirstItem(), timeout)) {
			MobileDriver.tap(accountDetailPage.lblShareReceiptFirstItem(), timeout, flowControl)
			String text = MobileDriver.getText(accountDetailPage.lblShareReceiptDetailDate(), timeout)
			Mobile.comment("History Date: " + text)
			StringUtils.verifyDateTimeFormat(text)
			MobileDriver.verifyElementVisible(commonPage.lblDynamic(refID), GlobalVariable.G_timeoutMedium, flowControl)
		}
	}

	void calculateCurrency(String currencyFrom, currencyTo, String amount,FailureHandling flowControl = defaultFailureHandling) {
		delay(GlobalVariable.G_timeoutMiniShort)
		commonPage.scrollUpDynamic(300)
		MobileDriver.tap(commonPage.lblDynamic(CURRENCY_CALCULATOR), GlobalVariable.G_timeoutMedium, flowControl)
		if(MobileDriver.waitForElementPresent(commonPage.lblDynamic(lblCurrencyCalculatorErrorMsg), GlobalVariable.G_timeoutShort)) {
			Mobile.takeScreenshot()
			handleError('System Error: Currency Calculator is unavailable.', flowControl)
		}
		delay(GlobalVariable.G_timeoutShort)
		verifyElements([CURRENCY_CALCULATOR], commonPage.lblDynamicPath(), GlobalVariable.G_timeoutMedium)
		'select from currency'
		if (!currencyFrom.isEmpty()) {
			MobileDriver.tap(commonPage.imgNoSimpleDynamic(2), GlobalVariable.G_timeoutLong, flowControl)
			selectCurrency(currencyFrom)
		}
		'select to currency'
		MobileDriver.tap(commonPage.lblDynamic(SELECT_CURRENCY), GlobalVariable.G_timeoutMedium, flowControl)
		selectCurrency(currencyTo)
		'input amount to convert'
		MobileDriver.tap(commonPage.txtEditNo(), GlobalVariable.G_timeoutMedium, flowControl)
		commonPage.enterNO(amount, false)
		MobileDriver.tap(commonPage.imgNoSimpleDynamic(8), GlobalVariable.G_timeoutLong, flowControl)
		MobileDriver.tap(commonPage.lblDynamic(CALCULATE), GlobalVariable.G_timeoutMedium, flowControl)
		'verify calculator successfull'
		MobileDriver.verifyElementVisible(commonPage.lblDynamic(UIAmountToReceive), GlobalVariable.G_timeoutMedium, flowControl)
		MobileDriver.tap(btnBack(), GlobalVariable.G_timeoutMedium, flowControl)
	}
	void verifyWatchList(FailureHandling flowControl = defaultFailureHandling) {
		MobileDriver.tap(commonPage.lblDynamic(WATCHLIST), GlobalVariable.G_timeoutMedium, flowControl)
		if(MobileDriver.waitForElementPresent(commonPage.lblDynamicNo(TERMS_AND_CONDITIONS,2), GlobalVariable.G_timeoutShort)) {
			'tap to term and condition'
			MobileDriver.tap(commonPage.lblDynamicNo(TERMS_AND_CONDITIONS,2), GlobalVariable.G_timeoutMedium, flowControl)
			delay(GlobalVariable.G_timeoutShort)
			commonPage.tapBack()
			MobileDriver.tap(commonPage.lblContainDynamic(UIIHaveRead), GlobalVariable.G_timeoutMedium, flowControl)
			MobileDriver.tap(commonPage.lblDynamic(AGREE_AND_CONFIRM), GlobalVariable.G_timeoutMedium, flowControl)
		}
		verifyElements([
			WATCHLIST,
			RECOMMENDED_WATCHLIST
		], commonPage.lblDynamicPath(), GlobalVariable.G_timeoutMedium)
		MobileDriver.tap(btnFirstWatchList(), GlobalVariable.G_timeoutMedium, flowControl)
		'verify navigate to convert currency screen'
		MobileDriver.verifyElementVisible(commonPage.lblContainDynamic(UIStepOneOfTwo), GlobalVariable.G_timeoutMedium, flowControl)
		MobileDriver.tap(btnBack(), GlobalVariable.G_timeoutMedium, flowControl)
		'check another watchlist and verify edit'
		verifyElements([
			WATCHLIST,
			RECOMMENDED_WATCHLIST
		], commonPage.lblDynamicPath(), GlobalVariable.G_timeoutMedium)
		MobileDriver.tap(commonPage.lblDynamicNo(RECOMMENDED_WATCHLIST,1), GlobalVariable.G_timeoutMedium, flowControl)
		MobileDriver.tap(commonPage.lblDynamic(UIWatchListOne), GlobalVariable.G_timeoutMedium, flowControl)
		delay(GlobalVariable.G_timeoutMiniShort)
		commonPage.tapDoneBtn()
		MobileDriver.verifyElementVisible(commonPage.lblDynamic(EDIT), GlobalVariable.G_timeoutMedium, flowControl)
		MobileDriver.tap(commonPage.lblDynamic(EDIT), GlobalVariable.G_timeoutMedium, flowControl)
		MobileDriver.verifyElementVisible(commonPage.lblDynamic(UIEditWatchlist), GlobalVariable.G_timeoutMedium, flowControl)
		if (MobileDriver.waitForElementPresent(commonPage.txtEditNo(), GlobalVariable.G_timeoutMedium, flowControl)) {
			'Change watch list name'
			MobileDriver.setText(commonPage.txtEditNo(),UITestEdit , GlobalVariable.G_timeoutMedium, flowControl)
			'reset name'
			MobileDriver.tap(commonPage.lblDynamic(UIResetToDefault), GlobalVariable.G_timeoutMedium, flowControl)
		}


		MobileDriver.tap(btnBack(), GlobalVariable.G_timeoutMedium, flowControl)
		MobileDriver.tap(btnBack(), GlobalVariable.G_timeoutMedium, FailureHandling.OPTIONAL)
	}
	void addCurrency(String currency, FailureHandling flowControl = defaultFailureHandling) {
		boolean findResult = commonPage.scrollToTestObject(this.fraScrollTab(), commonPage.lblDynamic(ADD_CURRENCY), ScrollDirectionEnum.RIGHT, GlobalVariable.G_timeoutShort, flowControl)

		if(findResult) {
			MobileDriver.tap(commonPage.lblDynamic(ADD_CURRENCY), GlobalVariable.G_timeoutLong, flowControl)
		}
		boolean findCurrency = commonPage.scrollToTestObject(commonPage.frmScrollView(), commonPage.lblContainDynamic(currency), ScrollDirectionEnum.DOWN, GlobalVariable.G_timeoutShort, FailureHandling.OPTIONAL)

		if(findResult) {
			MobileDriver.tap(commonPage.lblContainDynamic(currency), GlobalVariable.G_timeoutMedium, flowControl)
			'verify add success'
			MobileDriver.verifyElementVisible(commonPage.lblContainDynamic(SUCCESSFULLY_ADDED), GlobalVariable.G_timeoutMedium, flowControl)
		}else {
			info('Currency already existed')
		}
	}

	void convertCurrency(String currencyFrom, currencyTo, String amount, boolean isFO = false, FailureHandling flowControl = defaultFailureHandling) {
		'Select CONVERT CURRENCY'
		MobileDriver.waitForElementPresent(commonPage.lblDynamic(CONVERT_CURRENCY), GlobalVariable.G_timeoutMedium, flowControl)
		MobileDriver.tap(commonPage.lblDynamic(CONVERT_CURRENCY), GlobalVariable.G_timeoutMedium, flowControl)
		if(MobileDriver.waitForElementPresent(commonPage.lblDynamicNo(TERMS_AND_CONDITIONS,2), GlobalVariable.G_timeoutMedium)) {
			'tap to term and condition'
			MobileDriver.tap(commonPage.lblDynamicNo(TERMS_AND_CONDITIONS,2), GlobalVariable.G_timeoutMedium, flowControl)
			delay(GlobalVariable.G_timeoutShort)
			commonPage.tapBack()
			MobileDriver.tap(commonPage.lblContainDynamic(UIIHaveRead), GlobalVariable.G_timeoutMedium, flowControl)
			MobileDriver.tap(commonPage.lblDynamic(AGREE_AND_CONFIRM), GlobalVariable.G_timeoutMedium, flowControl)
		}
		if(MobileDriver.waitForElementPresent(commonPage.lblDynamic(CONVERT_CURRENCY), GlobalVariable.G_timeoutShort, flowControl)){
			MobileDriver.tap(commonPage.lblDynamic(CONVERT_CURRENCY), GlobalVariable.G_timeoutMedium, flowControl)
		}
		MobileDriver.waitForElementPresent(commonPage.lblDynamic(SELECT_CURRENCY), GlobalVariable.G_timeoutLong, flowControl)
		'select from currency'
		if (!currencyFrom.isEmpty()) {
			MobileDriver.tap(commonPage.imgNoDynamic(3), GlobalVariable.G_timeoutLong, flowControl)
			selectCurrency(currencyFrom)
		}
		'Select To Currency'
		MobileDriver.tap(commonPage.lblDynamic(SELECT_CURRENCY), GlobalVariable.G_timeoutMedium, flowControl)
		selectCurrency(currencyTo)
		MobileDriver.verifyElementVisible(commonPage.lblContainDynamic(EXCHANGE_RATE), GlobalVariable.G_timeoutMedium, flowControl)
		'Select Amount'
		MobileDriver.tap(commonPage.txtEditNo(2), GlobalVariable.G_timeoutMedium, flowControl)
		'Input Amount To Convert'
		commonPage.enterNO(amount, false)
		'Select Stick Button'
		MobileDriver.tap(btnStick(true), GlobalVariable.G_timeoutMedium)
		Mobile.delay(GlobalVariable.G_timeoutMiniShort)

		'Select Checkbox Set Future Order'
		if(isFO) {
			MobileDriver.tap(cbxSetFutureOrder(), GlobalVariable.G_timeoutMedium)
			Mobile.takeScreenshot()
			if(MobileDriver.waitForElementPresent(commonPage.lblDynamic(lblSetFutureOrderErrorMsg), GlobalVariable.G_timeoutShort)) {
				handleError('System Error: Future Order is unavailable.', flowControl)
			}
		}

		'Select Next Button'
		commonPage.tapNextBtn()
		'step 2 of 2'
		MobileDriver.verifyElementVisible(commonPage.lblContainDynamic(TRANSFER_FROM), GlobalVariable.G_timeoutMedium, flowControl)
		MobileDriver.verifyElementVisible(commonPage.lblContainDynamic(TRANSFER_TO), GlobalVariable.G_timeoutMedium, flowControl)
		if(isFO) {
			MobileDriver.tap(commonPage.lblDynamic(PLACE_ORDER), GlobalVariable.G_timeoutMedium, flowControl)
		} else {
			MobileDriver.tap(commonPage.lblDynamic(GET_QUOTE), GlobalVariable.G_timeoutMedium, flowControl)
		}
		Mobile.delay(GlobalVariable.G_timeoutMiniShort)
	}

	boolean selectCurrency(String currency, FailureHandling flowControl = defaultFailureHandling) {
		MobileDriver.takeScreenshot()
		'Select Search'
		MobileDriver.tapAtPositionWithOffset(btnSearch(), GlobalVariable.G_timeoutShort, 100)
		'Enter text'
		MobileDriver.setTextNoObjAppium(currency)
		MobileDriver.takeScreenshot()
		'Select first list'
		MobileDriver.tapEleByTestObject(transferPage.lblFavoriteItem(currency))
		MobileDriver.delay(GlobalVariable.G_timeoutMiniShort)
	}
	void removeCurrency(String currency, FailureHandling flowControl = defaultFailureHandling) {
		boolean findResult = commonPage.scrollToTestObject(this.fraScrollTab(), commonPage.lblContainDynamic(currency), ScrollDirectionEnum.RIGHT, GlobalVariable.G_timeoutShort, FailureHandling.OPTIONAL)

		if(findResult) {
			MobileDriver.tap(commonPage.lblContainDynamic(currency), GlobalVariable.G_timeoutLong, flowControl)
			'Swipe Up'
			commonPage.swipeByW3CAction(500, 1000, 500, 500)
			MobileDriver.tap(commonPage.lblDynamic(MANAGE), GlobalVariable.G_timeoutLong, flowControl)
			MobileDriver.tap(commonPage.lblDynamic(REMOVE_CURRENCY), GlobalVariable.G_timeoutLong, flowControl)
			MobileDriver.tap(commonPage.lblDynamic(CONFIRM), GlobalVariable.G_timeoutLong, flowControl)
			'verify remove success'
			MobileDriver.verifyElementVisible(commonPage.lblContainDynamic(SUCCESSFULLY_REMOVED), GlobalVariable.G_timeoutMedium, flowControl)
		}
	}

	/**
	 * selectPayBillsButton
	 * @return
	 */
	void selectPayBillsButton(int timeout = GlobalVariable.G_timeoutShort, FailureHandling flowControl = defaultFailureHandling) {
		MobileDriver.tap(this.btnPayBills(), timeout, flowControl)
	}

	/**
	 * verifyScroll: scroll and scroll to 'Deactivate account'
	 * @return
	 */
	void verifyScroll(FailureHandling flowControl = defaultFailureHandling) {
		commonPage.swipe(500, 1500, 500, 500)
		commonPage.swipe(500, 500, 500, 1500)
		commonPage.scrollToTestObject(commonPage.frmHorizontalScrollNo(1), commonPage.lblNormalizeSpace('Deactivate account'), ScrollDirectionEnum.RIGHT, GlobalVariable.G_timeoutShort,flowControl)
	}

	/**
	 * verifyAccountDashboard: verify accountNumber, click item account, verify account detail
	 * @return
	 */
	void verifyAccountDashboard(FailureHandling flowControl = defaultFailureHandling) {
		List<List<String>> accountList = this.getAllAccountItem()
		println(accountList)
		commonPage.scrollToTop(5, 500, 5, 1500)
		for(int i = 0; i < accountList.size(); i++) {
			String accountNumber = accountList.get(i).get(0)
			if(!StringUtils.isAccountNumber(accountNumber) || accountList.get(i).get(2) == NO_INFORMATION) {
				continue
			}
			commonPage.scrollToText(accountNumber, 500, 1500, 500, 500)
			MobileDriver.tap(commonPage.lblDynamic(accountNumber), GlobalVariable.G_timeoutLong, flowControl)
			accountDetailPage.verifyAccountDashbaord()

			if(i == 0) {
				this.verifyScroll()
			}
			'Back to account list'
			MobileDriver.tap(commonPage.imgNoDynamic(1), GlobalVariable.G_timeoutLong, flowControl)
			MobileDriver.delay(GlobalVariable.G_timeoutShort)
			MobileDriver.waitForElementPresent(commonPage.lblRMNo(1), GlobalVariable.G_timeoutLong, flowControl)
			MobileDriver.takeScreenshot()
		}
	}
	
	void verifyTabungCTA(FailureHandling flowControl = defaultFailureHandling) {
		commonPage.scrollToText(UITABUNG_CTA, 500, 1500, 500, 500)
		MobileDriver.verifyElementExist(commonPage.lblDynamic(UITABUNG_CTA), GlobalVariable.G_timeoutShort, flowControl)
	}
	
	void verify3Dot(FailureHandling flowControl = defaultFailureHandling) {
		'Click on 3 dot button'
		MobileDriver.tap(commonPage.imgNoDynamic(1), GlobalVariable.G_timeoutLong, flowControl)
		MobileDriver.delay(GlobalVariable.G_timeoutMiniShort)
		MobileDriver.verifyElementExist(commonPage.lblDynamic(UIKillSwitch), GlobalVariable.G_timeoutShort, flowControl)
		MobileDriver.verifyElementExist(commonPage.lblDynamic(UILinkOverSeas), GlobalVariable.G_timeoutShort, flowControl)
		MobileDriver.verifyElementExist(commonPage.lblDynamic(UILinkM2UAccess), GlobalVariable.G_timeoutShort, flowControl)
		Mobile.takeScreenshot()
	}
	void hideAccountBalance(String accountNumber, FailureHandling flowControl = defaultFailureHandling) {
		'Select Account'
		commonPage.scrollToText(accountNumber, 500, 1500, 500, 500)
		MobileDriver.tap(commonPage.lblDynamic(accountNumber), GlobalVariable.G_timeoutLong, flowControl)
		
		'Tap on Option'
		MobileDriver.delay(GlobalVariable.G_timeoutShort)
		MobileDriver.tap(commonPage.imgNoDynamic(2), GlobalVariable.G_timeoutLong, flowControl)
		
		'Select Hide Balance'
		MobileDriver.delay(GlobalVariable.G_timeoutShort)
		MobileDriver.tap(commonPage.lblDynamic(HIDE_BALANCE), GlobalVariable.G_timeoutLong, flowControl)
		
		'Back to Account Dashboard'
		MobileDriver.delay(GlobalVariable.G_timeoutShort)
		MobileDriver.tap(homePage.btnBackTopRead(), GlobalVariable.G_timeoutLong, flowControl)
		MobileDriver.delay(GlobalVariable.G_timeoutShort)
		selectTab('Home')
		MobileDriver.verifyElementExist(commonPage.lblDynamic(UIAccount), GlobalVariable.G_timeoutLong, flowControl)
	}
	
	void showAccountBalance(String accountNumber, FailureHandling flowControl = defaultFailureHandling) {
		'Select Account'
		commonPage.scrollToText(accountNumber, 500, 1500, 500, 500)
		MobileDriver.tap(commonPage.lblDynamic(accountNumber), GlobalVariable.G_timeoutLong, flowControl)
		
		'Tap on Option'
		MobileDriver.delay(GlobalVariable.G_timeoutShort)
		MobileDriver.tap(commonPage.imgNoDynamic(2), GlobalVariable.G_timeoutLong, flowControl)
		
		'Select Show Balance'
		MobileDriver.delay(GlobalVariable.G_timeoutShort)
		MobileDriver.tap(commonPage.lblDynamic(SHOW_BALANCE), GlobalVariable.G_timeoutLong, flowControl)
		
		'Back to Account Dashboard'
		MobileDriver.delay(GlobalVariable.G_timeoutShort)
		MobileDriver.tap(homePage.btnBackTopRead(), GlobalVariable.G_timeoutLong, flowControl)
		MobileDriver.delay(GlobalVariable.G_timeoutShort)
		selectTab('Home')
		MobileDriver.verifyElementExist(commonPage.lblDynamic(accountNumber), GlobalVariable.G_timeoutLong, flowControl)
	}
	
	/**
	 * verifyCardDashboard: verify cardNumber, click card item, verify card detail
	 * @return
	 */
	void verifyCardDashboard(Map cardDetailsUIData, FailureHandling flowControl = defaultFailureHandling) {
		List<List<String>> accountList = this.getAllAccountItem()
		println(accountList)
		commonPage.scrollToTop(5, 500, 5, 1500)
		for(int i = 0; i < accountList.size(); i++) {
			String cardNumber = accountList.get(i).get(0)
			if(!StringUtils.isAccountNumber(cardNumber, '\\*\\*\\*\\* \\*\\*\\*\\* \\*\\*\\*\\* \\d{4}')
					|| [
						NOT_AUTHORIZED,
						NO_INFORMATION
					].contains(accountList.get(i).get(2))) {
				continue
			}
			commonPage.scrollToText(cardNumber, 500, 1500, 500, 500)
			MobileDriver.tap(commonPage.lblDynamic(cardNumber), GlobalVariable.G_timeoutLong, flowControl)
			accountDetailPage.verifyCardDashboard(cardNumber, accountList.get(i).get(1), accountList.get(i).get(2), cardDetailsUIData)

			if(i == 0) {
				this.verifyScroll()
			}
			'Back to account list'
			MobileDriver.tap(commonPage.imgNoDynamic(1), GlobalVariable.G_timeoutLong, flowControl)
			MobileDriver.delay(GlobalVariable.G_timeoutShort)
			if(Mobile.waitForElementPresent(commonPage.lblRMNo(1), GlobalVariable.G_timeoutLong)) {
				MobileDriver.waitForElementPresent(commonPage.lblRMNo(1), GlobalVariable.G_timeoutLong, flowControl)
			}
			MobileDriver.takeScreenshot()
		}
	}

	/**
	 * verifySingleCardDashboard: verify a specific card by cardName
	 * @param cardName
	 * @param cardDetailsUIData
	 * @param flowControl
	 */
	void verifySingleCardDashboard(String cardName, Map cardDetailsUIData, FailureHandling flowControl = defaultFailureHandling) {
		List<List<String>> accountList = this.getAllAccountItem()
		def targetItem = accountList.find { it[1] == cardName }

		String cardNumber = targetItem.get(0)
		String outstandingBalance = targetItem.get(2)
		Mobile.comment(cardNumber + ", " + outstandingBalance)

		boolean isNegative = outstandingBalance.trim().startsWith("-")
		String numericPart = outstandingBalance.replace("RM", "").replace("-", "").trim()

		String formattedBalance
		if (isNegative) {
			formattedBalance = "RM -" + numericPart
		} else {
			formattedBalance = "RM " + numericPart
		}

		Mobile.comment(cardNumber + ", " + formattedBalance)

		MobileDriver.tap(commonPage.lblDynamic(cardNumber), GlobalVariable.G_timeoutLong, flowControl)
		accountDetailPage.verifyCardDashboard(cardNumber, cardName, formattedBalance, cardDetailsUIData)

		'Back to account list'
		MobileDriver.tap(commonPage.imgNoDynamic(1), GlobalVariable.G_timeoutLong, flowControl)
		MobileDriver.delay(GlobalVariable.G_timeoutShort)
		MobileDriver.waitForElementPresent(commonPage.lblRMNo(1), GlobalVariable.G_timeoutLong, flowControl)
		MobileDriver.takeScreenshot()
	}

	/**
	 * selectAccount: find account in account list and click account
	 * @return
	 */
	void selectAccount(String accountName, FailureHandling flowControl = defaultFailureHandling) {
		'Check popup GotIT and close it'
		this.checkAndTapGotIt(flowControl)
		commonPage.scrollToText(accountName, 5, 1500, 5, 500)
		MobileDriver.tap(commonPage.lblDynamic(accountName), GlobalVariable.G_timeoutLong, flowControl)
	}

	void selectCreditAccount(String accountName, FailureHandling flowControl = defaultFailureHandling) {
		'Check popup GotIT and close it'
		this.checkAndTapGotIt(flowControl)
		commonPage.swipeByW3CAction(5, 1500, 5, 200)
		commonPage.swipeByW3CAction(5, 1500, 5, 200)
		MobileDriver.tap(commonPage.lblDynamic(accountName), GlobalVariable.G_timeoutLong, flowControl)
	}


	void filterReceipt(String dateFilter,FailureHandling flowControl = defaultFailureHandling) {
		MobileDriver.tap(commonPage.imgNoDynamic(3),GlobalVariable.G_timeoutLong, flowControl)
		MobileDriver.tap(commonPage.lblContainDynamic(dateFilter),GlobalVariable.G_timeoutLong, flowControl)
		MobileDriver.tap(commonPage.lblDynamic(APPLY_FILTER), GlobalVariable.G_timeoutLong, flowControl)
		LocalDate now = LocalDate.now();
		String previousMonth = now.minusMonths(1).format(DateTimeFormatter.ofPattern("MMM"));
		delay(GlobalVariable.G_timeoutMiniShort)
		int  i = 1
		String pageNumber = 1
		while(i.toString().equals(pageNumber)) {
			i++
			MobileDriver.tap(commonPage.imgNoDynamic('last()'),GlobalVariable.G_timeoutLong, flowControl)
			pageNumber = MobileDriver.getText(commonPage.lblDynamicTextLast(i.toString()), GlobalVariable.G_timeoutShort, FailureHandling.OPTIONAL)
		}
		commonPage.swipeByW3CAction(5, 1500, 5, 200)
		delay(GlobalVariable.G_timeoutMiniShort)
		String date = MobileDriver.getText(commonPage.lblContainDynamic(previousMonth), GlobalVariable.G_timeoutLong, flowControl)
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy", Locale.ENGLISH);
		LocalDate parsedDate = LocalDate.parse(date, formatter);
		LocalDate today = LocalDate.now();
		long daysDifference = ChronoUnit.DAYS.between(parsedDate, today);


		BaseAssert.equals(daysDifference >= 0 && daysDifference <= dateFilter.toInteger(), true, "Verify date time", flowControl)
	}

	/**
	 * Verify status screen
	 */
	String statusScreen(String status, isFuture, boolean shouldGetStatusForApproval = false, FailureHandling flowControl = defaultFailureHandling) {
		String RefID = ''
		'If status is TAC, return'
		if(status.contains('TAC')) {
			return
		}
		'Verify Reference ID format'
		RefID = MobileDriver.getText(commonPage.lblSiblingOneGroup(UIReferenceID), GlobalVariable.G_timeoutLong, flowControl)
		if(Mobile.verifyMatch(RefID, '[0-9]{9}M', true)) {
			info('Reference ID follows the format 10 characters with first 9 is numbers and end with M')
		}
		MobileDriver.takeScreenshot()
		'Verify status screen'
		switch(status) {
			case 'Approve':
				if(MobileDriver.waitForElementPresent(commonPage.lblDynamic(UISecure2uAapproved), GlobalVariable.G_timeoutMedium, FailureHandling.OPTIONAL)) {
					MobileDriver.verifyElementVisible(commonPage.lblDynamic(UISecure2uAapproved), GlobalVariable.G_timeoutMedium, defaultFailureHandling)
					commonPage.tapDoneBtn()
					MobileDriver.verifyElementVisible(commonPage.lblDynamic(UITransactionSuccessful), GlobalVariable.G_timeoutMedium, defaultFailureHandling)
					MobileDriver.takeScreenshot()
					'Share Receipts'
					shareReceipts(RefID)
				}else if(MobileDriver.waitForElementPresent(commonPage.lblDynamic(UIFutureOrderSuccessfull), GlobalVariable.G_timeoutMedium, FailureHandling.OPTIONAL)){
					MobileDriver.verifyElementVisible(commonPage.lblDynamic(UIFutureOrderSuccessfull), GlobalVariable.G_timeoutMedium, defaultFailureHandling)
					MobileDriver.takeScreenshot()
					'Share Receipts'
					shareReceipts(RefID)
					commonPage.tapDoneBtn()
					break
				}else if(MobileDriver.waitForElementPresent(commonPage.lblDynamic(UIAutoSweepSuccessfull), GlobalVariable.G_timeoutShort, FailureHandling.OPTIONAL)){
					MobileDriver.verifyElementVisible(commonPage.lblDynamic(UIAutoSweepSuccessfull), GlobalVariable.G_timeoutMedium, defaultFailureHandling)
					MobileDriver.takeScreenshot()
					commonPage.tapDoneBtn()
					break
				}else if(MobileDriver.waitForElementPresent(commonPage.lblDynamic(UIAutoSweepSuccessfullDeactivated), GlobalVariable.G_timeoutShort, FailureHandling.OPTIONAL)){
					MobileDriver.verifyElementVisible(commonPage.lblDynamic(UIAutoSweepSuccessfullDeactivated), GlobalVariable.G_timeoutMedium, defaultFailureHandling)
					MobileDriver.takeScreenshot()
					commonPage.tapDoneBtn()
					break
				}else {
					handleError('System Error: Transfer Failed', flowControl)
				}
				if(MobileDriver.waitForElementPresent(commonPage.lblDynamic(UISecure2uAapproved), GlobalVariable.G_timeoutLong, FailureHandling.OPTIONAL)) {
					MobileDriver.verifyElementVisible(commonPage.lblDynamic(UISecure2uAapproved), GlobalVariable.G_timeoutLong, defaultFailureHandling)
					commonPage.tapDoneBtn()
					MobileDriver.verifyElementVisible(commonPage.lblDynamic(UITransactionSuccessful), GlobalVariable.G_timeoutLong, defaultFailureHandling)
				}else {
					handleError('System Error: Transfer Failed', flowControl)
				}
				MobileDriver.takeScreenshot()
				'Share Receipts'
				shareReceipts(RefID)
				break
			case 'Reject':
				String failedMsg = MobileDriver.getText(commonPage.lblTextNo(1), GlobalVariable.G_timeoutLong, flowControl)
				info(failedMsg)
				MobileDriver.verifyElementVisible(commonPage.lblDynamic(UIAuthorisationFailed), GlobalVariable.G_timeoutLong, flowControl)
				MobileDriver.takeScreenshot()
				commonPage.tapDoneBtn()
				MobileDriver.verifyElementVisible(commonPage.lblDynamic(UITransactionUnsuccessful), GlobalVariable.G_timeoutLong, defaultFailureHandling)
				MobileDriver.takeScreenshot()
				break
			case 'Expired':
				'verify titile'
				MobileDriver.verifyElementVisible(commonPage.lblDynamic(UIAuthorisationFailed), GlobalVariable.G_timeoutLong, flowControl)
				MobileDriver.takeScreenshot()
				commonPage.tapDoneBtn()
				MobileDriver.verifyElementVisible(commonPage.lblDynamic(UITransactionUnsuccessful), GlobalVariable.G_timeoutLong, defaultFailureHandling)
				break
		}
		'Select Done'
		commonPage.tapDoneBtn()
		return RefID
	}
	/**
	 * Share receipts
	 */
	void shareReceipts(String RefID, FailureHandling flowControl = defaultFailureHandling) {
		'Verify Share Receipts is visible'
		verifyElements([UIShareReceipt], commonPage.lblDynamicPath(), GlobalVariable.G_timeoutLong)
		'Select Share Receipts'
		MobileDriver.tap(commonPage.lblDynamic(UIShareReceipt), GlobalVariable.G_timeoutLong, flowControl)
		Mobile.delay(3)
		'Verify Reference ID is visible'
		MobileDriver.verifyElementVisible(commonPage.lblDynamic(RefID), GlobalVariable.G_timeoutMedium, flowControl)
		MobileDriver.takeScreenshot()
		'Select share'
		MobileDriver.tap(commonPage.imgNoDynamic(2), GlobalVariable.G_timeoutLong, flowControl)
		Mobile.delay(3)
		Mobile.pressBack()
		Mobile.delay(1)

		'Back'
		MobileDriver.tap(commonPage.imgNoDynamic(1), GlobalVariable.G_timeoutLong, flowControl)
		verifyElements([UIDoneBtn], commonPage.lblDynamicPath(), GlobalVariable.G_timeoutLong)
	}
}
