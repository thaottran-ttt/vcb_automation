package com.maybank.mae.internal.pages.base.accountpage

import java.time.Duration

import org.openqa.selenium.By
import org.openqa.selenium.WebElement

import com.kms.katalon.core.configuration.RunConfiguration
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.util.KeywordUtil
import com.maybank.common.Android
import com.maybank.common.MobileDriver
import com.maybank.mae.internal.pages.base.BasePage
import com.maybank.mae.internal.pages.base.CommonPage
import com.maybank.mae.internal.pages.base.ShareReceiptPage
import com.maybank.utils.DateUtils
import com.maybank.utils.StringUtils

import internal.GlobalVariable
import io.appium.java_client.AppiumDriver

public abstract class AccountDetailPage extends BasePage{
	abstract TestObject lblRecordAccount(int index)
	abstract TestObject btnViewTransactions()
	abstract TestObject drpTransactionHistory()
	abstract TestObject lblDynamicHistory(String text)
	abstract TestObject lblStartSpending()
	abstract TestObject btnClose()
	abstract TestObject btnBack()
	abstract TestObject lblTransactionsDetail()
	abstract TestObject lblTransactionsAmount()
	abstract TestObject btnFilter()
	abstract TestObject rbtn30Days()
	abstract TestObject rbtn60Days()
	abstract TestObject rbtn90Days()
	abstract TestObject lbl90Days(String txt)
	abstract TestObject btnApplyFilter()
	abstract TestObject btnNextPage()
	abstract TestObject lblPageNumber()
	abstract TestObject btnDone()
	abstract TestObject lblDateLastRecord()
	abstract TestObject lblAmountLastRecord()
	abstract TestObject btnThreeDots(String text)
	abstract TestObject txtContactBank()
	abstract TestObject lblBalance()
	abstract TestObject lblStartsWith(String text)
	abstract TestObject lblAnyText(String text)
	abstract TestObject lblContainDynamic(String text)
	abstract TestObject btnShare()
	abstract TestObject fraTransactionGroupItem()
	abstract TestObject lblShareReceiptFirstItem()
	abstract TestObject lblShareReceiptDetailDate()
	abstract TestObject lblItemTransaction(String groupName, String itemName)
	abstract TestObject btnCloseMeetballMenu()

	public static CommonPage commonPage = getPage(CommonPage)
	public static 	ShareReceiptPage shareReceiptPage = getPage(ShareReceiptPage)
	public static final ShareReceiptPage SHARE_RECEIPT_PAGE = getPage(ShareReceiptPage)
	static AppiumDriver driver = MobileDriverFactory.getDriver();
	static final String PATTERN_ACCOUNT_NUMBER = /^\d{4} \d{4} \d{4}$/

	String UI90Days = '90 days'
	List<String> clicked = []
	String UISorryRequest = "Sorry, we are unable to retrieve the information you've requested."
	String UIShareReceipt = 'Share Receipt'
	String UITransaction = 'Transactions'
	String UImsgPrimaryHasBeenSet = 'Your primary account has been updated.'
	static final String VIEW_TRANSACTIONS_UI = 'View Transactions'
	static final String TRANSACTIONS_UI = 'Transactions'
	static final String TRANSACTION_HISTORY_UI = 'Transaction History'
	static final String MSG_COPY_ACCOUNT_NUMBER_UI = 'Account number copied to clipboard.'
	static final String COPY_ACCOUNT_NUMBER_UI = 'Copy Account Number'
	static final List<String> SHARE_RECEIPT_VERIFY_TEXTS = ['Reference ID']
	static final String M2U_HISTORY = 'M2U History'
	static final String TODAY = 'Today'
	static final String SHARE_RECEIPT = 'Share Receipt'
	static final String MONEY_TYPE = 'Money'
	static final String DATE_TYPE = 'Date'
	static final String UILblManage = 'MANAGE'
	static final String UILblSetAsPrimary = 'Set as Primary'
	static final String UILblConfirm = 'Confirm'
	static final String UILblChangePrimaryAccount = 'Change Primary Account'
	static final String UILblEnableAutoSweep = 'Auto Sweep'
	static final String UILblDeactivate = 'Deactivate'


	/**
	 * verifyOnAScreen: get all item in transaction history(only screen not scroll) and click in item then verify shareRecept screen
	 *
	 * @return
	 */
	protected boolean verifyOnAScreen(List<String> xpaths, TestObject groupsTO, FailureHandling f = defaultFailureHandling) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(RunConfiguration.getTimeOut()))
		'Get all elements(transaction item) in transaction history'
		List<WebElement> elements = driver.findElements(By.xpath(MobileDriver.getLocator(groupsTO)))
		for (WebElement element : elements) {
			try {
				'Get title of transaction'
				String value = element.findElement(xpaths[0]).getText()
				if(!clicked.contains(value)) {
					'Click title'
					clicked.add(value)
					MobileDriver.info(value)
					element.findElement(xpaths[0]).click()
					'Verify share receipts screen then click title'
					if(waitForElementPresent(commonPage.lblDynamic(UIShareReceipt), GlobalVariable.G_timeoutShort, FailureHandling.OPTIONAL)){
						info('click share Receipts: ' + value)
						SHARE_RECEIPT_PAGE.shareReceipts([], UITransaction)
					}else {
						MobileDriver.takeScreenshot()
						handleError('Business exeption: cant click ' + value, f)
					}
				}
			}catch(e) {
				'ignore if find elements is not found'
			}
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(RunConfiguration.getTimeOut()))

		return true
	}

	/**
	 * verifyAllShareReceiptItems: get all item in transaction history(have scroll) and click in item then verify shareRecept screen
	 *
	 * @return
	 */
	abstract void verifyAllShareReceiptItems(TestObject groupsTO, FailureHandling flowControl = defaultFailureHandling)

	/**
	 * viewShareReceipt
	 *
	 * @return
	 */
	public void viewShareReceipt(String receiptTitle, String transactionType, String filterType, FailureHandling flowControl = defaultFailureHandling) {
		'Select view transactions'
		MobileDriver.tap(this.btnViewTransactions(), GlobalVariable.G_timeoutLong, flowControl)

		'Select transaction history'
		MobileDriver.tap(this.drpTransactionHistory(),GlobalVariable.G_timeoutLong, flowControl)

		'Srcoll to transactionType'
		commonPage.selectDropdownScrollOneLine(transactionType, GlobalVariable.G_timeoutLong, flowControl)

		'Select Done'
		MobileDriver.tap(this.btnDone(), GlobalVariable.G_timeoutLong, flowControl)

		if(Mobile.waitForElementPresent(this.btnFilter(), GlobalVariable.G_timeoutLong, flowControl)){
			'Select filter button'
			MobileDriver.tap(this.btnFilter(), GlobalVariable.G_timeoutLong, flowControl)

			'Select filterType'
			if (filterType.equalsIgnoreCase('60 days')) {
				MobileDriver.tap(this.rbtn60Days(), GlobalVariable.G_timeoutLong, flowControl)
			}
			else if (filterType.equalsIgnoreCase('90 days')) {
				MobileDriver.tap(this.rbtn90Days(), GlobalVariable.G_timeoutLong, flowControl)
			}

			'Select Apply button'
			MobileDriver.tap(this.btnApplyFilter(), GlobalVariable.G_timeoutLong, flowControl)

			String prePage = ''
			boolean receiptFound = false
			Mobile.delay(GlobalVariable.G_timeoutShort)
			Mobile.waitForElementPresent(this.lblPageNumber(), GlobalVariable.G_timeoutLong, flowControl)
			String currentPage = MobileDriver.getText(this.lblPageNumber(), GlobalVariable.G_timeoutLong, flowControl)

			while (prePage != currentPage) {
				'verify share receiptItems'
				verifyAllShareReceiptItems(this.fraTransactionGroupItem(), flowControl)
				receiptFound = true

				prePage = currentPage

				'Select next page'
				MobileDriver.tap(this.btnNextPage(), GlobalVariable.G_timeoutLong, flowControl)
				Mobile.waitForElementPresent(this.lblPageNumber(), GlobalVariable.G_timeoutLong, flowControl)

				currentPage = MobileDriver.getText(this.lblPageNumber(), GlobalVariable.G_timeoutLong, flowControl);
			}
			if (!receiptFound) {
				KeywordUtil.markFailed("Receipt not found")
			}
		}
	}

	public void viewReprintReceipt(String transactionType, FailureHandling flowControl = defaultFailureHandling) {
		'Select view transactions'
		MobileDriver.tap(this.btnViewTransactions(), GlobalVariable.G_timeoutShort, flowControl)

		'Select transaction history'
		MobileDriver.tap(this.drpTransactionHistory(),GlobalVariable.G_timeoutShort, flowControl)

		'Srcoll to transactionType'
		commonPage.selectDropdownScrollOneLine(transactionType, GlobalVariable.G_timeoutShort, flowControl)

		'Select Done'
		MobileDriver.tap(this.btnDone(), GlobalVariable.G_timeoutShort, flowControl)

		if(Mobile.waitForElementPresent(commonPage.imgNoDynamic(4), GlobalVariable.G_timeoutShort, flowControl)){
			'Select download button'
			MobileDriver.tap(commonPage.imgNoDynamic(4), GlobalVariable.G_timeoutShort, flowControl)

			MobileDriver.verifyElementVisible(commonPage.lblDynamic(SHARE_RECEIPT), GlobalVariable.G_timeoutShort, flowControl)

			MobileDriver.verifyElementVisible(commonPage.imgNoDynamic(2), GlobalVariable.G_timeoutShort, flowControl)
		}
	}

	/**
	 * viewRecentlyShareReceipt: verify title, transaction type
	 *
	 * @return
	 */
	public void viewRecentlyShareReceipt(String receiptTitle, String transactionType, FailureHandling flowControl = defaultFailureHandling) {
		'Select view transactions'
		MobileDriver.tap(this.btnViewTransactions(), GlobalVariable.G_timeoutLong, flowControl)

		'Select transaction history'
		MobileDriver.tap(this.drpTransactionHistory(),GlobalVariable.G_timeoutLong, flowControl)

		'Srcoll to transactionType'
		commonPage.selectDropdownScrollOneLine(transactionType, GlobalVariable.G_timeoutLong, flowControl)

		'Select Done'
		MobileDriver.tap(this.btnDone(), GlobalVariable.G_timeoutLong, flowControl)


		MobileDriver.tap(this.lblContainDynamic(receiptTitle), GlobalVariable.G_timeoutLong, flowControl)

		Mobile.delay(10)

		SHARE_RECEIPT_PAGE.shareReceipts(['Reference ID'], null)
	}

	/**
	 * getAccountInfo: verify transaction history: filter 30days, 60days, Verify share receipts, ValidateDate
	 *
	 * @return
	 */
	public void getAccountInfo(String historyType, int timeout = GlobalVariable.G_timeoutLong, FailureHandling flowControl = defaultFailureHandling) {
		'Init Data'
		int index=1
		int totalRecords = 11
		int totalAccounts = 0
		String transactionsDetail
		String transactionsAmount
		String transactionHistoryName = "M2U History"
		String lastRecord = ""
		def pagePosition = ""
		def msgValidateDate = ""
		int plusDay = 90
		String msgCheckDate = ""
		int skipAccountNo = 0

		'Count Total Account'
		for (int i=1; i < totalRecords; i++) {
			if (Mobile.waitForElementPresent(this.lblRecordAccount(i), GlobalVariable.G_timeoutMedium)) {
				totalAccounts = totalAccounts + 1
			}else {
				break
			}
		}
		Mobile.comment("Total Accounts: " + totalAccounts)

		'Get Account Transaction'
		for (int i=1; i <= totalAccounts; i++) {
			if (Mobile.waitForElementPresent(this.lblRecordAccount(i), GlobalVariable.G_timeoutLong, flowControl)) {
				MobileDriver.tap(this.lblRecordAccount(i), GlobalVariable.G_timeoutLong, flowControl)
				Mobile.delay(GlobalVariable.G_timeoutMiniShort)
				if(!MobileDriver.waitForElementPresent(this.btnViewTransactions(), GlobalVariable.G_timeoutMedium, FailureHandling.OPTIONAL)) {
					skipAccountNo++
					break
				}
				MobileDriver.tap(this.btnViewTransactions(), GlobalVariable.G_timeoutLong, flowControl)
				'Transactions Process'
				if(Mobile.waitForElementPresent(this.btnFilter(), GlobalVariable.G_timeoutMedium)) {
					'Get Last Page'
					pagePosition = naviateLastPage()
					Mobile.comment("Last Page:" + pagePosition)
					Mobile.delay(GlobalVariable.G_timeoutMiniShort)
					'Press Filter Button'
					MobileDriver.tap(this.btnFilter(), GlobalVariable.G_timeoutLong, flowControl)
					'Press 60 Days Radio Button'
					MobileDriver.tap(this.rbtn60Days(), GlobalVariable.G_timeoutLong, flowControl)
					'Press Apply Filter Button'
					MobileDriver.tap(this.btnApplyFilter(), GlobalVariable.G_timeoutLong, flowControl)
					Mobile.delay(GlobalVariable.G_timeoutMedium)
				}

				MobileDriver.tap(this.drpTransactionHistory(),GlobalVariable.G_timeoutLong, flowControl)
				Mobile.delay(GlobalVariable.G_timeoutMiniShort)
				commonPage.selectDropdownScrollOneLine(commonPage.lblDynamic(historyType),GlobalVariable.G_timeoutLong,flowControl)
				MobileDriver.tap(this.btnDone(), GlobalVariable.G_timeoutLong, flowControl)
				if(Mobile.waitForElementPresent(this.btnFilter(), GlobalVariable.G_timeoutLong, flowControl)){
					'Verify share receipt'
					if (Mobile.waitForElementPresent(this.lblShareReceiptFirstItem(), timeout)) {
						MobileDriver.tap(lblShareReceiptFirstItem(), timeout, flowControl)
						String text = Mobile.getText(this.lblShareReceiptDetailDate(), timeout)
						Mobile.comment("History Date: " + text)
						StringUtils.verifyDateTimeFormat(text)
						SHARE_RECEIPT_PAGE.shareReceipts(SHARE_RECEIPT_VERIFY_TEXTS, null)
					}

					'Get Last Page'
					pagePosition = naviateLastPage()
					Mobile.comment("Last Page:" + pagePosition)
					'Get Date'
					lastRecord = naviateLastRecord()
					Mobile.comment("Last Date: " + lastRecord)
					plusDay = -30
					msgCheckDate = "30 days"
					'Validate Date'
					msgValidateDate = DateUtils.dateValidator(lastRecord, "30 days", -30)
					Mobile.comment(msgValidateDate)
					'Press Filter Button'
					Mobile.delay(GlobalVariable.G_timeoutMiniShort)
					//MobileDriver.tap(this.btnFilter(), GlobalVariable.G_timeoutLong, flowControl)
					Mobile.delay(GlobalVariable.G_timeoutMiniShort)
				}

				if(Mobile.waitForElementPresent(this.btnFilter(), GlobalVariable.G_timeoutLong, flowControl)){
					MobileDriver.tap(this.btnFilter(), GlobalVariable.G_timeoutLong, flowControl)
					Mobile.delay(GlobalVariable.G_timeoutMiniShort)
					'Press 60 Days Radio Button'
					MobileDriver.tap(this.rbtn60Days(), GlobalVariable.G_timeoutLong, flowControl)
					'Press Apply Filter Button'
					MobileDriver.tap(this.btnApplyFilter(), GlobalVariable.G_timeoutLong, flowControl)
					'Get Last Page'
					pagePosition = naviateLastPage()
					Mobile.comment("Last Page:" + pagePosition)
					'Get Date'
					lastRecord = naviateLastRecord().toString()
					Mobile.comment("Last Date: " + lastRecord)
					plusDay = -60
					msgCheckDate = "60 days"
					'Validate Date'
					msgValidateDate = DateUtils.dateValidator(lastRecord, msgCheckDate, plusDay)
					Mobile.comment(msgValidateDate)
				}
				'Press Filter Button'
				Mobile.delay(GlobalVariable.G_timeoutMiniShort)
				if(Mobile.waitForElementPresent(this.lbl90Days(UI90Days), GlobalVariable.G_timeoutLong, flowControl)){
					MobileDriver.tap(this.btnFilter(), GlobalVariable.G_timeoutLong, flowControl)
					Mobile.delay(GlobalVariable.G_timeoutMiniShort)
					'Press 90 Days Radio Button'
					MobileDriver.tap(this.rbtn90Days(), GlobalVariable.G_timeoutLong, flowControl)
					'Press Apply Filter Button'
					MobileDriver.tap(this.btnApplyFilter(), GlobalVariable.G_timeoutLong, flowControl)
					'Get Last Page'
					pagePosition = naviateLastPage()
					Mobile.comment("Last Page:" + pagePosition)
					'Get Date'
					lastRecord = naviateLastRecord().toString()
					Mobile.comment("Last Date: " + lastRecord)
					plusDay = -90
					msgCheckDate = "90 days"
					'Validate Date'
					msgValidateDate = DateUtils.dateValidator(lastRecord, msgCheckDate, plusDay)
					Mobile.comment(msgValidateDate)
				}
				Mobile.delay(GlobalVariable.G_timeoutMiniShort)
				'Click Close Button'
				MobileDriver.tap(this.btnClose(), GlobalVariable.G_timeoutLong, flowControl)
				Mobile.delay(GlobalVariable.G_timeoutMiniShort)
				'Click Back Button'
				MobileDriver.tap(commonPage.imgNoDynamic(1), GlobalVariable.G_timeoutLong, flowControl)
			}
			Mobile.delay(GlobalVariable.G_timeoutMiniShort)
		}

		if(totalAccounts == skipAccountNo) {
			handleError('System Error: Account Click Action was not successful.', flowControl)
		}
	}

	/**
	 * getCreditAccountInfo: verify transaction history
	 *
	 * @return
	 */
	public void getCreditAccountInfo(int timeout = GlobalVariable.G_timeoutLong, FailureHandling flowControl = defaultFailureHandling) {
		'Init Data'
		if (Mobile.waitForElementPresent(this.btnViewTransactions(), GlobalVariable.G_timeoutMedium, FailureHandling.OPTIONAL)) {
			MobileDriver.tap(this.btnViewTransactions(), GlobalVariable.G_timeoutLong, flowControl)
			Mobile.delay(GlobalVariable.G_timeoutMiniShort)

			'Transactions Process'
			MobileDriver.tap(this.drpTransactionHistory(), GlobalVariable.G_timeoutLong, flowControl)
			Mobile.delay(GlobalVariable.G_timeoutMiniShort)
			commonPage.selectDropdownScrollOneLine(this.TRANSACTION_HISTORY_UI, GlobalVariable.G_timeoutLong, flowControl)
			MobileDriver.tap(this.btnDone(), GlobalVariable.G_timeoutLong, flowControl)

			'Click Close Button'
			MobileDriver.tap(this.btnClose(), GlobalVariable.G_timeoutLong, flowControl)
			Mobile.delay(GlobalVariable.G_timeoutMiniShort)
			'Click Back Button'
			MobileDriver.tap(commonPage.imgNoDynamic(1), GlobalVariable.G_timeoutLong, flowControl)
		} else {
			handleError('System Error: View Transactions button not found.', flowControl)
		}
	}

	/**
	 * Get Last Page
	 *
	 * @return
	 */
	public naviateLastPage(FailureHandling flowControl = defaultFailureHandling) {
		int curPage = 1
		int initPage = 1
		int totalPage = 11
		'Get Init Page'
		for(int i=1; i <= totalPage; i++) {
			if (Mobile.waitForElementPresent(btnNextPage(), GlobalVariable.G_timeoutShort, FailureHandling.OPTIONAL)) {
				MobileDriver.tap(btnNextPage(), GlobalVariable.G_timeoutMedium, FailureHandling.OPTIONAL)
			}
			Mobile.delay(GlobalVariable.G_timeoutMiniShort)
			if (Mobile.waitForElementPresent(this.lblPageNumber(), GlobalVariable.G_timeoutLong, flowControl)) {
				String pageText = MobileDriver.getText(this.lblPageNumber(), GlobalVariable.G_timeoutLong, flowControl)
			}else {
				curPage = 1
			}
			if (initPage == curPage) {
				break
			}else {
				initPage = curPage
			}
		}
		curPage = 1
		return curPage
	}

	/**
	 * Get Last Record
	 *
	 * @return
	 */
	public naviateLastRecord(FailureHandling flowControl = defaultFailureHandling) {
		String initAmountLastRecord = ""
		int timeout = 1
		String curAmountLastRecord = ""
		String dateLastRecord = "2025"
		String year = "2025"
		int[] nCoordinates = [560, 1769, 560, 600]
		'Get Current Amount Last Record'
		curAmountLastRecord = MobileDriver.getText(this.lblAmountLastRecord(), GlobalVariable.G_timeoutLong, flowControl)
		Mobile.comment("curAmountLastRecord: " + curAmountLastRecord)
		while (initAmountLastRecord != curAmountLastRecord) {
			initAmountLastRecord = curAmountLastRecord
			//scroll
			int duration = 1000
			int x1 = nCoordinates[0]   //left
			int y1 = nCoordinates[1]
			int x2 =  nCoordinates[2]  //left
			int y2 =  nCoordinates[3]  //top + heightElement * 3
			Android.scrollToNotDirection(x1, y1, x2, y2)
			Mobile.delay(timeout)
			Android.scrollToNotDirection(x1, y1, x2, y2)
			'Get Current Amount Last Record'
			curAmountLastRecord = MobileDriver.getText(this.lblAmountLastRecord(), GlobalVariable.G_timeoutLong, flowControl)
			Mobile.comment("curAmountLastRecord_1: " + curAmountLastRecord)
			if(curAmountLastRecord == initAmountLastRecord) {
				Mobile.scrollToText(year)
			}
		}

		'Get Date Last Record'
		dateLastRecord = MobileDriver.getText(this.lblDateLastRecord(), GlobalVariable.G_timeoutLong, flowControl)

		return dateLastRecord
	}

	void clickThreeDotsButton(String text, flowControl) {
		Mobile.tap(this.btnThreeDots(text), GlobalVariable.G_timeoutMedium, flowControl)
	}

	void verifyContactBankText(flowControl) {
		Mobile.verifyElementExist(this.txtContactBank(), GlobalVariable.G_timeoutMedium, flowControl)
	}

	/**
	 * selectMenu: select module in menu
	 *
	 * @return
	 */
	public void selectMenu(String text, String password = GlobalVariable.G_password, FailureHandling flowControl = defaultFailureHandling) {
		if(waitForElementPresent(commonPage.lblDynamic(text), GlobalVariable.G_timeoutMedium, flowControl)) {
			tap(commonPage.lblDynamic(text), GlobalVariable.G_timeoutLong, flowControl)
			MobileDriver.delay(GlobalVariable.G_timeoutShort)
		}else {
			'Select item if need swipe'
			commonPage.selectTab(commonPage.frmHorizontalScrollNo(1), text, GlobalVariable.G_timeoutLong, flowControl)
		}
	}

	/**
	 * verifyAccountDashbaord: copy account number, CTA, transaction UI
	 *
	 * @return
	 */
	void verifyAccountDashbaord(FailureHandling flowControl = defaultFailureHandling) {
		MobileDriver.waitForElementPresent(commonPage.lblDynamic(VIEW_TRANSACTIONS_UI), GlobalVariable.G_timeoutLong, flowControl)

		MobileDriver.verifyElementVisible(commonPage.lblTextMatches(PATTERN_ACCOUNT_NUMBER), GlobalVariable.G_timeoutLong, flowControl)
		MobileDriver.verifyElementVisible(commonPage.lblTextNo(1), GlobalVariable.G_timeoutLong, flowControl)
		MobileDriver.verifyElementVisible(commonPage.lblRMNo(1), GlobalVariable.G_timeoutLong, flowControl)

		'verify copy account number'
		MobileDriver.tap(commonPage.lblRMNo(1), GlobalVariable.G_timeoutLong, flowControl)
		MobileDriver.verifyElementVisible(commonPage.lblDynamic(MSG_COPY_ACCOUNT_NUMBER_UI), GlobalVariable.G_timeoutLong, flowControl)
		MobileDriver.takeScreenshot()

		'verify copy account number CTA'
		MobileDriver.tap(commonPage.imgNoDynamic(2), GlobalVariable.G_timeoutLong, flowControl)
		MobileDriver.tap(commonPage.lblDynamic(COPY_ACCOUNT_NUMBER_UI), GlobalVariable.G_timeoutLong, flowControl)

		'Verify view and tap transaction ui'
		MobileDriver.tap(commonPage.lblDynamic(VIEW_TRANSACTIONS_UI), GlobalVariable.G_timeoutLong, flowControl)
		MobileDriver.verifyElementVisible(commonPage.lblDynamic(TRANSACTIONS_UI), GlobalVariable.G_timeoutLong, flowControl)
		MobileDriver.tap(commonPage.imgNoDynamic(1), GlobalVariable.G_timeoutLong, flowControl)
		MobileDriver.takeScreenshot()
	}

	void verifyCardDashboard(String cardNumber, String cardName, String outstandingBalance, Map<String, String> cardDetailsUIData, FailureHandling flowControl = defaultFailureHandling) {
		if(Mobile.waitForElementPresent(commonPage.lblDynamic(VIEW_TRANSACTIONS_UI), GlobalVariable.G_timeoutShort)) {
			MobileDriver.verifyElementVisible(commonPage.lblDynamic(VIEW_TRANSACTIONS_UI), GlobalVariable.G_timeoutLong, flowControl)

			MobileDriver.verifyElementVisible(commonPage.lblDynamic(cardNumber), GlobalVariable.G_timeoutLong, flowControl)
			MobileDriver.verifyElementVisible(commonPage.lblDynamic(cardName), GlobalVariable.G_timeoutLong, flowControl)
			MobileDriver.verifyElementVisible(commonPage.lblDynamic(outstandingBalance), GlobalVariable.G_timeoutLong, flowControl)

			'Verify view and tap transaction ui'
			MobileDriver.tap(commonPage.lblDynamic(VIEW_TRANSACTIONS_UI), GlobalVariable.G_timeoutLong, flowControl)
			MobileDriver.verifyElementVisible(commonPage.lblDynamic(TRANSACTIONS_UI), GlobalVariable.G_timeoutLong, flowControl)
			MobileDriver.verifyElementVisible(commonPage.lblDynamic(TRANSACTION_HISTORY_UI), GlobalVariable.G_timeoutLong, flowControl)
			MobileDriver.tap(commonPage.imgNoDynamic(1), GlobalVariable.G_timeoutLong, flowControl)
			MobileDriver.takeScreenshot()

			cardDetailsUIData.each { detail, detailFormat ->
				MobileDriver.scrollToText(detail)
				MobileDriver.verifyElementVisible(commonPage.lblSiblingDynamic(detail), GlobalVariable.G_timeoutLong, flowControl)
				String detailValue = MobileDriver.getText(commonPage.lblSiblingDynamic(detail), GlobalVariable.G_timeoutLong, flowControl)
				switch(detailFormat) {
					case MONEY_TYPE:
						commonPage.verifyFormatMoneyRM([detailValue])
						break
					case DATE_TYPE:
						commonPage.verifyFormatDate([detailValue])
						break
					default:
						break
				}
			}
		}
	}

	/**
	 * getAccountBalanceAmountValue: verify amount, balance
	 * @return
	 */
	def getAccountBalanceAmountValue(int timeout = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		delay(GlobalVariable.G_timeoutMiniShort)
		'Get balanace amount'
		String strAmount = Mobile.getText(lblBalance(), timeout, flowControl)
		Mobile.comment("Balance mount is ${strAmount}")
		String balanceAmount = strAmount.replace("RM ", "").replace(",", "").replace("*", "").trim()
		Mobile.comment("Balance mount is ${balanceAmount}")
		return balanceAmount
	}

	/**
	 * verifyTransactionHistory
	 * @return
	 */
	void verifyTransactionHistory(String transactionType, FailureHandling flowControl = defaultFailureHandling) {
		'Select view transactions'
		if(MobileDriver.verifyElementExist(this.btnViewTransactions(), GlobalVariable.G_timeoutLong, FailureHandling.OPTIONAL)) {
			MobileDriver.tap(this.btnViewTransactions(), GlobalVariable.G_timeoutLong, flowControl)
		}

		'Select transaction history'
		MobileDriver.tap(this.drpTransactionHistory(),GlobalVariable.G_timeoutLong, flowControl)

		'Srcoll to transactionType'
		commonPage.selectDropdownScrollOneLine(transactionType, GlobalVariable.G_timeoutLong, flowControl)

		'Select Done'
		MobileDriver.tap(this.btnDone(), GlobalVariable.G_timeoutLong, flowControl)
		Mobile.takeScreenshot()
	}

	/**
	 * verifyReprintReceipt: verify product, status, Reprint Receipt
	 * @return
	 */
	void verifyReprintReceipt(String product, Boolean isG1A = false, FailureHandling flowControl = defaultFailureHandling)  {
		'Select view transactions'
		MobileDriver.tap(this.btnViewTransactions(), GlobalVariable.G_timeoutLong, flowControl)

		'Select Dropdown Transaction'
		MobileDriver.tap(this.drpTransactionHistory(),GlobalVariable.G_timeoutLong, flowControl)

		'Select M2U History'
		commonPage.selectDropdownScrollOneLine(M2U_HISTORY, GlobalVariable.G_timeoutLong, flowControl)

		'Select Dropdown Done'
		MobileDriver.tap(this.btnDone(), GlobalVariable.G_timeoutLong, flowControl)

		'Verify product Item'
		MobileDriver.verifyElementVisible(this.lblItemTransaction(TODAY, product?.trim()), GlobalVariable.G_timeoutLong, flowControl)

		'Tap Reprint Receipt'
		MobileDriver.tap(this.lblItemTransaction(TODAY, product), GlobalVariable.G_timeoutLong, flowControl)

		'Wait Load Share Receipt Screen'
		commonPage.waitLoading(GlobalVariable.G_timeoutMedium)

		if (isG1A) {
			'Verify Share Receipt Screen'
			SHARE_RECEIPT_PAGE.tapShareIcon()
		}
	}


	/**
	 * navigateToM2UHistory: 
	 * @return
	 */
	void navigateToM2UHistory(FailureHandling flowControl = defaultFailureHandling) {
		'Select view transactions'
		MobileDriver.tap(this.btnViewTransactions(), GlobalVariable.G_timeoutLong, flowControl)

		'Select Dropdown Transaction'
		MobileDriver.tap(this.drpTransactionHistory(),GlobalVariable.G_timeoutLong, flowControl)

		'Select M2U History'
		commonPage.selectDropdownScrollOneLine(M2U_HISTORY, GlobalVariable.G_timeoutLong, flowControl)

		'Select Dropdown Done'
		MobileDriver.tap(this.btnDone(), GlobalVariable.G_timeoutLong, flowControl)
	}

	/**
	 * verifyM2UHistoryFirstItemToday: first item today
	 * @return
	 */
	void verifyM2UHistoryFirstItemToday(String product, String amount = '', FailureHandling flowControl = defaultFailureHandling)  {
		'Navigate to M2U History'
		this.navigateToM2UHistory()
		'Verify product Item'
		MobileDriver.verifyElementVisible(this.lblItemTransaction(TODAY, product?.trim()), GlobalVariable.G_timeoutLong, flowControl)
		if(amount) {
			'Verify product Item'
			MobileDriver.verifyElementVisible(this.lblItemTransaction(TODAY, amount), GlobalVariable.G_timeoutLong, flowControl)
		}
	}

	/**
	 * verifyG1ATransactEntryPoints: verify entry points in TRANSACT tab
	 * @return
	 */
	void verifyG1ATransactEntryPoints(List<String> entryPoints, FailureHandling flowControl = defaultFailureHandling) {
		delay(GlobalVariable.G_timeoutMiniShort)
		int startY = Mobile.getDeviceHeight()/2
		int endY = startY - 500
		commonPage.swipeByW3CAction(0, startY, 0, endY)
		delay(GlobalVariable.G_timeoutMiniShort)

		// partition entryPoints into lists of 8 entries per view
		List<List<String>> entryPointsPerViews = entryPoints.collate(8)
		entryPointsPerViews.each { entryView ->
			commonPage.verifyElements(entryView, commonPage.lblNormalizeSpacePath(), GlobalVariable.G_timeoutMedium)
		}
		MobileDriver.takeScreenshot()
		scrollToTop()
		'Click Back Button'
		MobileDriver.tap(commonPage.imgNoDynamic(2), GlobalVariable.G_timeoutLong, flowControl)
	}

	/**
	 * changePrimaryAccount: set account as primary account
	 * @return
	 */
	void changePrimaryAccount(List<String> lstUILblDashboardMYRSummary, FailureHandling flowControl = defaultFailureHandling) {
		delay(GlobalVariable.G_timeoutMiniShort)
		'Select Three Dots Button'
		clickThreeDotsButton('Accounts',flowControl)
		'Verify List UI Label Dashboard MYR Summary'
		verifyElements(lstUILblDashboardMYRSummary, commonPage.lblContainDynamicPath(), GlobalVariable.G_timeoutMedium)
		'Select UI Label Change Primary Account'
		MobileDriver.tap(commonPage.lblDynamic(UILblChangePrimaryAccount), GlobalVariable.G_timeoutLong, flowControl)
		'Select Confirm Button'
		MobileDriver.tap(commonPage.lblDynamic(UILblConfirm), GlobalVariable.G_timeoutLong, flowControl)
		'Verify primary account update Msg'
		MobileDriver.verifyElementVisible(commonPage.lblDynamic(UImsgPrimaryHasBeenSet), GlobalVariable.G_timeoutLong, flowControl)
	}
	/**
	 * selectActionItemInHamburgerMenuBar
	 * TODO: This method can be use to replace Kill Switch method in Account  page if G1A project merged to MAE regression
	 * @return
	 */
	void selectActionItemInHamburgerMenuBar(List<String> lstUILblAccountsMenu, String itemName, FailureHandling flowControl = defaultFailureHandling) {
		'Select Three Dots Button'
		clickThreeDotsButton('Maybank2u',flowControl)
		'Verify List UI Label Accounts Menu'
		verifyElements(lstUILblAccountsMenu, commonPage.lblContainDynamicPath(), GlobalVariable.G_timeoutMedium)
		MobileDriver.takeScreenshot()
		'Select UI Label Kill Switch'
		MobileDriver.tap(commonPage.lblDynamic(itemName), GlobalVariable.G_timeoutLong, flowControl)
		MobileDriver.takeScreenshot()
	}
	/**
	 * setAsPrimary: set account as primary account
	 * @return
	 */
	void setAsPrimary(String accountName, List<String> lstUILblDashboardMYRSummary, FailureHandling flowControl = defaultFailureHandling) {
		delay(GlobalVariable.G_timeoutMiniShort)
		int startY = Mobile.getDeviceHeight()/2
		int endY = startY - 500
		commonPage.swipeByW3CAction(100, startY, 100, endY)
		delay(GlobalVariable.G_timeoutMiniShort)
		'Select Manage Tab'
		MobileDriver.tap(commonPage.lblDynamic(UILblManage), GlobalVariable.G_timeoutLong, flowControl)
		'Select Set As Primary Button'
		MobileDriver.tap(commonPage.lblContainDynamic(UILblSetAsPrimary), GlobalVariable.G_timeoutLong, flowControl)
		Mobile.scrollToText(accountName)
		MobileDriver.tap(commonPage.lblDynamic(accountName), GlobalVariable.G_timeoutLong, flowControl)
		MobileDriver.tap(commonPage.lblDynamic(UILblConfirm), GlobalVariable.G_timeoutLong, flowControl)
		'Verify primary account update Msg'
		MobileDriver.verifyElementVisible(commonPage.lblDynamic(UImsgPrimaryHasBeenSet), GlobalVariable.G_timeoutLong, flowControl)
		MobileDriver.takeScreenshot()
		scrollToTop()
		MobileDriver.verifyElementVisible(commonPage.lblDynamic(accountName), GlobalVariable.G_timeoutLong, flowControl)
		MobileDriver.takeScreenshot()
		'Select Three Dots Button'
		clickThreeDotsButton('Accounts',flowControl)
		verifyElements(lstUILblDashboardMYRSummary, commonPage.lblContainDynamicPath(), GlobalVariable.G_timeoutMedium)
		MobileDriver.tap(commonPage.imgNoDynamic(1), GlobalVariable.G_timeoutLong, flowControl)
		delay(GlobalVariable.G_timeoutMiniShort)
		'Click Back Button'
		MobileDriver.tap(commonPage.imgNoDynamic(1), GlobalVariable.G_timeoutLong, flowControl)
	}

	/**
	 * enableAutoSweep: enable auto sweep module
	 * @return
	 */
	void enableAutoSweep(FailureHandling flowControl = defaultFailureHandling) {
		delay(GlobalVariable.G_timeoutMiniShort)
		int startY = Mobile.getDeviceHeight()/2
		int endY = startY - 500
		commonPage.swipeByW3CAction(100, startY, 100, endY)
		delay(GlobalVariable.G_timeoutMiniShort)
		'Select Manage Tab'
		MobileDriver.tap(commonPage.lblDynamic(UILblManage), GlobalVariable.G_timeoutLong, flowControl)
		'Select Auto Sweep Button'
		MobileDriver.tap(commonPage.lblContainDynamic(UILblEnableAutoSweep), GlobalVariable.G_timeoutLong, flowControl)
	}
	/**
	 * deactivateAutoSweep: deactivate auto sweep module
	 * @return
	 */
	void deactivateAutoSweep(FailureHandling flowControl = defaultFailureHandling) {
		'Select UI Label Deactivate'
		MobileDriver.tap(commonPage.lblDynamicNo(UILblDeactivate,1), GlobalVariable.G_timeoutLong, flowControl)
		Mobile.delay(GlobalVariable.G_timeoutMiniShort)
		if(MobileDriver.waitForElementPresent(commonPage.lblDynamicNo(UILblDeactivate,1), GlobalVariable.G_timeoutShort)) {
			MobileDriver.tap(commonPage.lblDynamicNo(UILblDeactivate,1), GlobalVariable.G_timeoutLong, flowControl)
		}
	}
	void verifyTransactEntry(String transactEntry, FailureHandling flowControl = defaultFailureHandling) {
		MobileDriver.tap(commonPage.lblNormalizeSpace(transactEntry), GlobalVariable.G_timeoutLong, flowControl)
		delay(GlobalVariable.G_timeoutMiniShort)
		switch(transactEntry) {
			case 'Transfer':
				'Verify page title'
				MobileDriver.verifyElementVisible(commonPage.lblDynamic(TRANSFER_TO), GlobalVariable.G_timeoutLong, flowControl)
				'Click Back Button'
				MobileDriver.tap(commonPage.imgNoDynamic(1), GlobalVariable.G_timeoutLong, flowControl)
				break;
			case 'Scan & Pay':
				if (commonPage.waitForElementNotPresent(commonPage.lblDynamic(GO_CASHLESS_WITH_SCAN_PAY), GlobalVariable.G_timeoutShort)) {
					'Verify page title'
					MobileDriver.verifyElementVisible(commonPage.lblDynamic(SCANPAY_TITLE), GlobalVariable.G_timeoutLong, flowControl)
					'Click Close Button'
					MobileDriver.tap(commonPage.imgNoDynamic(2), GlobalVariable.G_timeoutLong, flowControl)
				} else {
					'Click Back Button'
					MobileDriver.tap(commonPage.imgNoDynamic(2), GlobalVariable.G_timeoutLong, flowControl)
				}
				break;
			case 'View Statements':
				'Verify page title'
				MobileDriver.verifyElementVisible(commonPage.lblDynamic(STATEMENTS), GlobalVariable.G_timeoutLong, flowControl)
				'Click Back Button'
				MobileDriver.tap(commonPage.imgNoDynamic(1), GlobalVariable.G_timeoutLong, flowControl)
				break;
		}
	}

	/**
	 * verifyReprintReceipt: verify product, verifyData , status, Reprint Receipt
	 * @return
	 */
	void verifyReprintReceiptWithData(String product, List<String> verifyData ,FailureHandling flowControl = defaultFailureHandling)  {
		'Select view transactions'
		MobileDriver.tap(this.btnViewTransactions(), GlobalVariable.G_timeoutLong, flowControl)

		'Select Dropdown Transaction'
		MobileDriver.tap(this.drpTransactionHistory(),GlobalVariable.G_timeoutLong, flowControl)

		'Select M2U History'
		commonPage.selectDropdownScrollOneLine(M2U_HISTORY, GlobalVariable.G_timeoutLong, flowControl)

		'Tap Done'
		MobileDriver.tap(this.btnDone(), GlobalVariable.G_timeoutLong, flowControl)

		'Verify today Item'
		MobileDriver.verifyElementVisible(this.lblItemTransaction(TODAY, product?.trim()), GlobalVariable.G_timeoutLong, flowControl)

		'Tap to view product item'
		MobileDriver.tap(this.lblItemTransaction(TODAY, product), GlobalVariable.G_timeoutLong, flowControl)

		'Wait loading'
		commonPage.waitLoading()

		'Verify List Data'
		commonPage.verifyElementsAndScrolling(verifyData, commonPage.lblContainDynamicPath(), GlobalVariable.G_timeoutMedium, shareReceiptPage.fraShareReceiptWebView())

		'Wait Load Share Receipt Screen'
		commonPage.waitLoading(GlobalVariable.G_timeoutMedium)

		'Share Receipt Screen'
		SHARE_RECEIPT_PAGE.tapShareIcon()
	}
}
