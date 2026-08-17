package com.vcb.mobile.internal.pages.base.accountpage

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import java.text.DecimalFormat
import java.util.regex.Matcher
import java.util.regex.Pattern
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.By

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
import com.vcb.assertions.BaseAssert
import com.vcb.common.MobileDriver
import com.vcb.mobile.internal.pages.base.BasePage
import com.vcb.mobile.internal.pages.base.CommonPage
import com.vcb.mobile.internal.pages.base.ShareReceiptPage
import com.vcb.mobile.internal.pages.base.zakat.ZakatPage as ZakatPage
import com.vcb.utils.StringUtils
import com.vcb.utils.DateUtils

import internal.GlobalVariable

public abstract class WealthPage extends BasePage {

	abstract TestObject frmDashboardListView()
	abstract TestObject lblGroupSiblingDynamic(String text, int index)
	abstract TestObject lblDynamicContainTwoText(String text, String text2)
	abstract TestObject imgSibling(String text)
	abstract TestObject btnDynamic(String text)
	abstract TestObject lblPrecedingDynamic(String text, String text2)
	abstract TestObject lblListDataItems()
	abstract TestObject lblTooltipTitle()
	abstract TestObject lblContainSibling(String text)
	abstract TestObject dropdownDynamic(String text)
	abstract TestObject lblSiblingDynamic(String text, int index = 1)
	abstract TestObject lblSiblingGold(String text)

	CommonPage commonPage = getPage(CommonPage)
	AccountDetailPage accountDetailPage = getPage(AccountDetailPage)
	ShareReceiptPage shareReceiptPage = getPage(ShareReceiptPage)
	ZakatPage zakatPage = BasePage.getPage(ZakatPage)

	//Wealth Dashboard
	static final List<String> DASHBOARD_UI = [
		'Total Investments',
		'View Wealth Portfolio'
	]
	static final String TOTAL_INVESTMENTS = 'Total Investments'
	static final GOLD_INVESTMENT = 'Gold'
	static final VCB_GOLD_INVESTMENT = 'Vcb Gold Investment'
	static final SILVER_INVESTMENT = 'Vcb Silver Investment'
	static final ASNB_ACCOUNTS = 'ASNB Accounts'
	static final FOREIGN_CURRENCY = 'Foreign Currency'
	static final TABUNG_HAJI = 'Tabung Haji'
	static final SELF = 'SELF'
	static final DEPENDENT = 'DEPENDENT'
	static final String TOTAL_AMOUNT = 'Total Amount'
	static final UNIT_TRUST = 'Unit Trust'
	static final BUY_NOW = 'Buy Now'
	static final SELL_NOW = 'Sell Now'
	static final I_WANT_TO_BUY = 'I want to buy'
	static final GRAM = 'Gram'
	static final BUY = 'Buy'
	static final SELL = 'Sell'
	static final RM = 'RM'
	static final VIEW_MORE = 'View More'
	static final NO_OF_ACCOUNT = 'No. of accounts'
	static final enum AmountCheckType {
		NONE,
		GREATER_THAN_ZERO,
		EQUAL_ZERO
	}

	//Detail
	static final VIEW_TRANSACTION = 'View Transaction'
	static final VIEW_TRANSACTIONS = 'View Transactions'
	static final TRANSACTIONS = 'Transactions'
	static final TRANSACTIONS_HISTORY_UNAVAILABLE = 'Transaction History Unavailable'
	static final TRANSACTIONS_HISTORY_NOTHING = 'Nothing to see here. Come back later when there\'s something to show!'
	static final TRANSACTIONS_HISTORY_TRANSFER = 'Transfer'

	//Gold
	static final String GOLD_ACCOUNT_I_TITLE = 'MIGA-i'
	static final String GOLD_ACCOUNT_I_ACCOUNT_NAME = 'Vcb Islamic Gold Account-i'
	static final String GOLD_ACCOUNT_I_PREFERENTIAL_PRICE = 'Preferential Price'
	static final String GOLD_ACCOUNT_I_BUY_DEAL = ' above 100g to get'
	static final String GOLD_ACCOUNT_I_INDICATIVE_PRICE = 'Indicative price as at '
	static final String GOLD_ACCOUNT_I_TOTAL_PURCHASED_TIP = 'This is the total amount that you’ve spent to purchase gold. You have a positive return if your current gold value is more than the total purchased.'

	//Unit Trust
	static final String UNIT_HELD = 'Unit Held'
	static final String CURRENCY = 'Currency'
	static final String LATEST_NAV_PER_UNIT = 'Latest NAV per unit'
	static final String CURRENT_VALUE = 'Current Value**'
	static final String CURRENT_VALUE_IN_RM = 'Current Value** in RM'
	static final String UNREALISED_GAINS = 'Unrealised Gains/Losses in RM'

	//Silver
	static final String TOTAL_UNITS = 'Total Units'

	//MFCA
	static final String INVESTMENT_MASTER_FOREIGN_CURRENCY_ACCOUNT = 'Investment Master Foreign Currency Account'
	static final String TOTAL_CURRENCY = 'Total Currency'
	static final String VALUE_IN_RM = 'Value in RM'
	static final String TODAY_PRICE_IN_RM = 'Today Price in RM'
	static final String YOUR_ACCOUNTS = 'Your Accounts'
	static final String CONVERT_CURRENCY = 'Convert Currency'

	//Digital Wealth
	static final String PORTFOLIO = 'Portfolio'
	static final String VIEW_WEALTH_PORTFOLIO = 'View Wealth Portfolio'
	static final String OVERVIEW = 'OVERVIEW'
	static final String MY_ASSETS = 'My Assets'
	static final String My_LIABILITIES = 'My Liabilities'
	static final String ACCOUNTS = 'Accounts'
	static final String INVESTMENTS = 'Investments'
	static final String ASSET_CLASSIFICATION = 'ASSET CLASSIFICATION'
	static final String ASSET_CLASSIFICATION_LOWER = 'Asset Classification'
	static final String GOLD_WEIGHT = 'Gold weight'
	static final String AMOUNT = 'Amount'
	static final String TOTAL_PURCHASED = 'Total purchased'
	static final String CURRENT_GOLD_VALUE = 'Current gold value'
	static final String AVAILABLE_GOLD = 'Available gold'
	static final String CURRENT_PRICE = 'Current price*'
	static final String AVG_PURCHASE_PRICE = 'Avg. purchase price'
	static final String DATE = 'Date'

	String goldAmount = ''
	String UILblCurrency = 'MYR'
	String UILblPercent = '100.00%'
	String cost = ''
	def firstBalance = ''
	def lastBalance = ''
	String buyPrice = ''
	String sellPrice = ''
	String currentPrice = ''
	String date =''
	List<String> migaList = new ArrayList()


	List<List<String>> getWealthListView() {
		return commonPage.getAllItemsListView([
			"//android.widget.TextView[1]"
		], this.frmDashboardListView())
	}

	/**
	 * Verify Wealth Dashboard
	 */
	void verifyWealthDashBoardTotalBalance(FailureHandling flowControl = defaultFailureHandling) {
		'Verify Total Balance'
		MobileDriver.waitForElementPresent(commonPage.lblDynamic(TOTAL_INVESTMENTS), GlobalVariable.G_timeoutLong, FailureHandling.OPTIONAL)
		MobileDriver.takeScreenshot()

		'Get Balance'
		String balance = MobileDriver.getText(commonPage.lblRMNo(1), GlobalVariable.G_timeoutLong, flowControl)

		'Extract balance to money format'
		String balanceNo = StringUtils.extractMoney(balance)

		'Verify balance format'
		MobileDriver.takeScreenshot()
		BaseAssert.equals(verifyFormatMoney([balanceNo]), true, 'Verify balance format', flowControl)

		'Verify UI'
		verifyElements(DASHBOARD_UI, commonPage.lblDynamicPath(), GlobalVariable.G_timeoutLong, flowControl)

		'Verify board have Wealth Item'
		MobileDriver.takeScreenshot()
		List<List<String>> itemList = []
		itemList = this.getListViewInWealthItem()
		BaseAssert.equals(itemList.size() > 0, true, 'Verify board have Wealth Item', flowControl)
	}

	String verifyWealthDashBoardInvestmentItemShow(String accountType = "Gold", AmountCheckType amountCheck = AmountCheckType.NONE, FailureHandling flowControl = defaultFailureHandling) {
		'Verify Account Investment Show'
		MobileDriver.waitForElementPresent(commonPage.lblContainDynamic(accountType), GlobalVariable.G_timeoutLong, flowControl)

		'Verify Account Number Show'
		MobileDriver.takeScreenshot()

		'Tap Account Type to reveal full account number'
		MobileDriver.tap(commonPage.lblContainDynamic(accountType), GlobalVariable.G_timeoutLong, FailureHandling.OPTIONAL)
		MobileDriver.delay(GlobalVariable.G_timeoutMiniShort)
		
		'Tap Account Type again'
		if(MobileDriver.waitForElementPresent(commonPage.lblContainDynamic("Vcb"), GlobalVariable.G_timeoutShort, FailureHandling.OPTIONAL)) {
			MobileDriver.tap(commonPage.lblContainDynamic("Vcb"), GlobalVariable.G_timeoutLong, FailureHandling.OPTIONAL)
			MobileDriver.delay(GlobalVariable.G_timeoutMiniShort)
		}
		
		String amountRaw = MobileDriver.getText(commonPage.lblContainDynamic(RM),GlobalVariable.G_timeoutShort,flowControl)
		String amount = StringUtils.extractMoney(amountRaw)

		'Extract amount to money format'
		amount = amount.replaceFirst(/\*$/, "")

		'Verify amount format'
		BaseAssert.equals(verifyFormatMoney([amount]), true, 'Verify amount format', flowControl)

		'Verify amount greater than zero'
		MobileDriver.takeScreenshot()
		this.checkAmountValue(amount, amountCheck, flowControl)

		return 
	}
	
	void verifyWealthDashBoardInvestmentASNBItemShow(FailureHandling flowControl = defaultFailureHandling) {
		'Verify ASNB Investment Show'
		MobileDriver.verifyElementVisible(commonPage.lblDynamic(ASNB_ACCOUNTS), GlobalVariable.G_timeoutLong, flowControl)
		MobileDriver.takeScreenshot()

		'Verify View More Show'
		MobileDriver.verifyElementVisible(this.lblSiblingDynamic(ASNB_ACCOUNTS), GlobalVariable.G_timeoutLong, flowControl)
		String viewMoreText = MobileDriver.getText(this.lblSiblingDynamic(ASNB_ACCOUNTS), GlobalVariable.G_timeoutLong, flowControl)
		MobileDriver.takeScreenshot()
		BaseAssert.equals(viewMoreText, VIEW_MORE, 'Verify View More Show Below ASNB Account')
	}
	void verifyWealthDashBoardInvestmentTabungHajiItemShow(FailureHandling flowControl = defaultFailureHandling) {
		'Scroll to find Tabung Haji first'
		commonPage.scrollToText(TABUNG_HAJI)
		
		'Verify Tabung Haji Investment Show'
		MobileDriver.verifyElementVisible(commonPage.lblDynamic(TABUNG_HAJI), GlobalVariable.G_timeoutLong, flowControl)
		MobileDriver.takeScreenshot()

		'Small scroll up to reveal RM amount below Tabung Haji'
		commonPage.scrollUpDynamic(300)
		
		'Verify View More Show'
		MobileDriver.verifyElementVisible(this.lblSiblingDynamic(TABUNG_HAJI), GlobalVariable.G_timeoutLong, flowControl)
		String viewMoreText = MobileDriver.getText(this.lblSiblingDynamic(TABUNG_HAJI), GlobalVariable.G_timeoutLong, flowControl)
		MobileDriver.takeScreenshot()

		'Verify RM Show'
		MobileDriver.verifyElementVisible(this.lblDynamicContainTwoText(TABUNG_HAJI, RM), GlobalVariable.G_timeoutLong, flowControl)
		String amount = MobileDriver.getText(this.lblDynamicContainTwoText(TABUNG_HAJI, RM), GlobalVariable.G_timeoutLong, flowControl)

		'Extract amount to money format'
		amount = amount.replaceFirst(/\*$/, "")
		String amountNo = StringUtils.extractMoney(amount)

		'Verify amount format'
		MobileDriver.takeScreenshot()
		BaseAssert.equals(verifyFormatMoney([amountNo]), true, 'Verify amount format', flowControl)
	}
	/**
	 * Verify Tabung Haji card visible on Wealth dashboard with correct label and RM amount
	 */
	void verifyTabungHajiCardOnWealthDashboard(FailureHandling flowControl = defaultFailureHandling) {
		'Scroll to find Tabung Haji first'
		commonPage.scrollToText(TABUNG_HAJI)

		'Verify Tabung Haji label visible'
		MobileDriver.verifyElementVisible(commonPage.lblDynamic(TABUNG_HAJI), GlobalVariable.G_timeoutLong, flowControl)
		MobileDriver.takeScreenshot()

		'Small scroll up to reveal content below Tabung Haji'
		commonPage.scrollUpDynamic(300)

		'Verify RM amount visible'
		MobileDriver.verifyElementVisible(this.lblDynamicContainTwoText(TABUNG_HAJI, RM), GlobalVariable.G_timeoutLong, flowControl)
		String amount = MobileDriver.getText(this.lblDynamicContainTwoText(TABUNG_HAJI, RM), GlobalVariable.G_timeoutLong, flowControl)

		'Extract and verify RM amount format'
		amount = amount.replaceFirst(/\*$/, "")
		String amountNo = StringUtils.extractMoney(amount)
		MobileDriver.takeScreenshot()
		BaseAssert.equals(verifyFormatMoney([amountNo]), true, 'Verify RM amount format on Tabung Haji card', flowControl)
	}
	/**
	 * Tap SELF or DEPENDENT tab on Tabung Haji dashboard
	 */
	void tapTabungHajiTab(String tabType, FailureHandling flowControl = defaultFailureHandling) {
		MobileDriver.tap(commonPage.lblDynamic(tabType), GlobalVariable.G_timeoutMedium, flowControl)
		MobileDriver.delay(GlobalVariable.G_timeoutMiniShort)
	}
	/**
	 * Verify Tabung Haji dashboard detail - Self or Dependent tab
	 */
	void verifyTabungHajiDashboardDetail(String tabType = SELF, FailureHandling flowControl = defaultFailureHandling) {
		'Delay shortly to avoid stale UI elements'
		commonPage.delay(GlobalVariable.G_timeoutMiniShort)
		MobileDriver.takeScreenshot()

		'Verify Tabung Haji page title'
		MobileDriver.waitForElementPresent(commonPage.lblDynamic(TABUNG_HAJI), GlobalVariable.G_timeoutLong, flowControl)

		'Verify SELF and DEPENDENT tab toggles visible'
		MobileDriver.verifyElementVisible(commonPage.lblDynamic(SELF), GlobalVariable.G_timeoutMiniShort, flowControl)
		MobileDriver.verifyElementVisible(commonPage.lblDynamic(DEPENDENT), GlobalVariable.G_timeoutMiniShort, flowControl)

		'Verify Total Amount label visible'
		MobileDriver.verifyElementVisible(commonPage.lblDynamic(TOTAL_AMOUNT), GlobalVariable.G_timeoutMiniShort, flowControl)
		MobileDriver.takeScreenshot()

		'Verify RM amount is displayed in blue card'
		MobileDriver.verifyElementVisible(commonPage.lblContainDynamic(RM), GlobalVariable.G_timeoutMiniShort, flowControl)
		MobileDriver.takeScreenshot()
	}
	/**
	 * Select ASNB Account
	 */
	void tapASNBAccount(String account = ASNB_ACCOUNTS, FailureHandling flowControl = defaultFailureHandling) {
		MobileDriver.tap(commonPage.lblDynamic(account), GlobalVariable.G_timeoutLong, flowControl)
	}

	/**
	 * Select item in list view
	 */
	void tapItemInListView(String itemName, FailureHandling flowControl = defaultFailureHandling) {
		
		'Scroll to find item in list'
		commonPage.scrollToTextDefault(itemName, [], true, GlobalVariable.G_timeoutShort, FailureHandling.OPTIONAL)
		
		'Select item'
		MobileDriver.tap(commonPage.lblContainDynamic(itemName), GlobalVariable.G_timeoutLong, flowControl)
	}
	
	
	String tabCardItem(String name, int accountNumberLocatorIndex = 1,FailureHandling flowControl = defaultFailureHandling) {
		MobileDriver.delay(GlobalVariable.G_timeoutMiniShort)
		MobileDriver.waitForElementPresent(this.lblListDataItems(), GlobalVariable.G_timeoutMedium, flowControl)
		String cardNum = MobileDriver.getText(this.lblSiblingDynamic(name, accountNumberLocatorIndex), GlobalVariable.G_timeoutLong, flowControl)
		MobileDriver.tap(this.lblSiblingDynamic(name, accountNumberLocatorIndex), GlobalVariable.G_timeoutLong, flowControl)

		return cardNum
	}

	/**
	 * View Transaction
	 */
	private void viewTransaction(FailureHandling flowControl = defaultFailureHandling) {
		'Select View Transaction'
		MobileDriver.tap(commonPage.lblDynamic(VIEW_TRANSACTIONS), GlobalVariable.G_timeoutMedium, flowControl)
		MobileDriver.delay(GlobalVariable.G_timeoutMiniShort)
		commonPage.waitLoading(GlobalVariable.G_timeoutLong + GlobalVariable.G_timeoutMedium)
		Mobile.delay(GlobalVariable.G_timeoutMiniShort)
		'Back'
		MobileDriver.tapAndLog(commonPage.imgNoDynamic(1), GlobalVariable.G_timeoutLong, 'Back Icon', flowControl)
	}

	/**
	 * Get List View in Wealth Item
	 */
	private List<List<String>> getListViewInWealthItem() {
		return commonPage.getAllItemsListView([
			'//android.widget.TextView[1]',
			'//android.widget.TextView[2]'
		], commonPage.frmScrollViewGroupNo(1, 2), 1, [100, 1500, 100, 10])
	}

	/**
	 * Verify Gold Investment Detail
	 */
	void verifyGoldOrSilverInvestmentDetail(String title = VCB_GOLD_INVESTMENT,AmountCheckType amountCheck = AmountCheckType.NONE, FailureHandling flowControl = defaultFailureHandling) {
		'Delay shortly avoid framework capture old lacator to check'
		commonPage.delay(GlobalVariable.G_timeoutMiniShort)
		MobileDriver.takeScreenshot()

		'Verify Gold Investment Title Show'
		MobileDriver.waitForElementPresent(commonPage.lblDynamic(title), GlobalVariable.G_timeoutLong, flowControl)

		'Verify Account ID Show'
		String id = MobileDriver.getText(this.lblSiblingDynamic(title), GlobalVariable.G_timeoutMiniShort, flowControl)
		BaseAssert.equals(verifyFormatAccountNumber([id]), true, 'Verify Format AccountNumber')

		'Verify RM Show'
		MobileDriver.verifyElementVisible(commonPage.lblContainDynamic(RM), GlobalVariable.G_timeoutMiniShort, flowControl)

		'Verify RM Amount'
		MobileDriver.takeScreenshot()
		String rmValue = MobileDriver.getText(commonPage.lblContainDynamic(RM), GlobalVariable.G_timeoutMiniShort, flowControl)
		String money = StringUtils.extractMoney(rmValue)
		BaseAssert.equals(verifyFormatMoney([money]), true, 'Verify RM format', flowControl)
		this.checkAmountValue(money, amountCheck, flowControl)

		'Wait for View Transaction'
		MobileDriver.verifyElementVisible(commonPage.lblDynamic(VIEW_TRANSACTIONS), GlobalVariable.G_timeoutMiniShort, flowControl)

		'Verify Total Units visible'
		MobileDriver.verifyElementVisible(commonPage.lblDynamic(TOTAL_UNITS), GlobalVariable.G_timeoutMiniShort, flowControl)

		'Verify Total Unit Value'
		MobileDriver.takeScreenshot()
		String totalValue = MobileDriver.getText(this.lblSiblingDynamic(TOTAL_UNITS), GlobalVariable.G_timeoutMiniShort, flowControl)
		BaseAssert.equals(totalValue.contains('g'), true, 'Verify total value format', flowControl)
		this.checkAmountValue(totalValue.replace(' g', ''), amountCheck, flowControl)
	}
	void verifyGoldInvestmentDetailForMIGAAccount(AmountCheckType amountCheck = AmountCheckType.NONE, FailureHandling flowControl = defaultFailureHandling) {
		info('Verify Gold Investment Detail For MIGA Account')
		'MIGA-i title'
		MobileDriver.verifyElementVisible(commonPage.lblDynamic(GOLD_ACCOUNT_I_TITLE), GlobalVariable.G_timeoutLong, flowControl)
		MobileDriver.takeScreenshot()

		'Check Account UI Show: Name, id, RM'
		this.verifyWealthDashBoardInvestmentItemShow(GOLD_ACCOUNT_I_ACCOUNT_NAME, amountCheck, flowControl)

		'Check View Transactions Link Show'
		MobileDriver.verifyElementExist(commonPage.lblDynamic(VIEW_TRANSACTIONS), GlobalVariable.G_timeoutMiniShort, flowControl)

		'Price quote panel'
		'Buy Price'
		MobileDriver.takeScreenshot()
		this.verifyPriceQuotePanel(BUY)
		'Sell Price'
		MobileDriver.takeScreenshot()
		this.verifyPriceQuotePanel(SELL)

		'Indicative price'
		'Check Text Show'
		MobileDriver.verifyElementExist(commonPage.lblContainDynamic(GOLD_ACCOUNT_I_INDICATIVE_PRICE), GlobalVariable.G_timeoutMiniShort, flowControl)
		'Check Valid Date'
		String indicativePrice = MobileDriver.getText(commonPage.lblContainDynamic(GOLD_ACCOUNT_I_INDICATIVE_PRICE), GlobalVariable.G_timeoutMiniShort, flowControl)
		String indicativePriceDate = indicativePrice.replace(GOLD_ACCOUNT_I_INDICATIVE_PRICE, "")
		BaseAssert.equals(DateUtils.isValidDate(indicativePriceDate, "dd MMM yyyy hh:mm a"), true, 'Check Indicative Price Date Format')
		MobileDriver.takeScreenshot()

		'Total purchased'
		'Verify Title Show'
		MobileDriver.verifyElementExist(commonPage.lblDynamic(TOTAL_PURCHASED), GlobalVariable.G_timeoutMiniShort, flowControl)
		String totalPurchaseValue = MobileDriver.getText(this.lblSiblingDynamic(TOTAL_PURCHASED), GlobalVariable.G_timeoutMiniShort, flowControl)
		BaseAssert.equals(totalPurchaseValue.contains(RM), true, 'Check Total Purchase Contain RM')
		'Verify amount format'
		String amountMoney = StringUtils.extractMoney(totalPurchaseValue)
		BaseAssert.equals(verifyFormatMoney([amountMoney]), true, 'Verify amount format', flowControl)
		'Verify Total Purchased Greater Than 0'
		this.checkAmountValue(amountMoney, amountCheck, flowControl)
		'Verify Tool Tip'
		MobileDriver.tap(this.imgSibling(TOTAL_PURCHASED), GlobalVariable.G_timeoutMiniShort, flowControl)
		MobileDriver.delay(GlobalVariable.G_timeoutMiniShort)
		MobileDriver.takeScreenshot()
		'Check Tooltip title'
		MobileDriver.waitForElementPresent(this.lblTooltipTitle(), GlobalVariable.G_timeoutMedium, flowControl)
		String tooltipTitle = MobileDriver.getText(this.lblTooltipTitle(), GlobalVariable.G_timeoutMedium, flowControl)
		BaseAssert.equals(tooltipTitle, TOTAL_PURCHASED, 'Check Tooltip title', flowControl)
		MobileDriver.verifyElementVisible(commonPage.lblDynamic(GOLD_ACCOUNT_I_TOTAL_PURCHASED_TIP), GlobalVariable.G_timeoutMiniShort, flowControl)
		MobileDriver.tap(commonPage.imgNoDynamic(1), GlobalVariable.G_timeoutMiniShort, flowControl)

		'Current Gold value'
		MobileDriver.verifyElementExist(commonPage.lblDynamic(CURRENT_GOLD_VALUE), GlobalVariable.G_timeoutMiniShort, flowControl)
		MobileDriver.takeScreenshot()
		commonPage.scrollToEnd()

		String currentGoldValue = MobileDriver.getText(this.lblSiblingDynamic(CURRENT_GOLD_VALUE), GlobalVariable.G_timeoutMiniShort, flowControl)
		BaseAssert.equals(currentGoldValue.contains(RM), true, 'Check Current Gold Value Contain RM')
		'Verify amount format'
		BaseAssert.equals(verifyFormatMoney([
			StringUtils.extractMoney(currentGoldValue)
		]), true, 'Verify amount format', flowControl)
		'Verify Current Gold Greater Than 0'
		this.checkAmountValue(StringUtils.extractMoney(currentGoldValue), amountCheck, flowControl)

		'Avg. purchase price'
		MobileDriver.verifyElementExist(commonPage.lblDynamic(AVG_PURCHASE_PRICE), GlobalVariable.G_timeoutMiniShort, flowControl)
		String avgPrice = MobileDriver.getText(this.lblSiblingDynamic(AVG_PURCHASE_PRICE), GlobalVariable.G_timeoutMiniShort, flowControl)
		BaseAssert.equals(avgPrice.contains(RM), true, 'Check Avg. purchase Contain RM')
		BaseAssert.equals(avgPrice.contains('/g'), true, 'Check Avg. purchase Contain Gram')
		'Verify amount format'
		String avgPurchaseMoney = StringUtils.extractMoney(avgPrice.replace('/g',''))
		this.checkAmountValue(avgPurchaseMoney, amountCheck, flowControl)

		'Available gold'
		MobileDriver.verifyElementExist(commonPage.lblDynamic(AVAILABLE_GOLD), GlobalVariable.G_timeoutMiniShort, flowControl)
		String availableGoldPrice = MobileDriver.getText(this.lblSiblingDynamic(AVAILABLE_GOLD), GlobalVariable.G_timeoutMiniShort, flowControl)
		BaseAssert.equals(availableGoldPrice.contains('g'), true, 'Check Available Gold Value Contain Gram')
		'Verify gold format'
		String availableGoldMoney = availableGoldPrice.replace(' g','')
		this.checkAmountValue(availableGoldMoney, amountCheck, flowControl)

		'Sell button'
		MobileDriver.takeScreenshot()
		MobileDriver.verifyElementExist(this.btnDynamic(BUY), GlobalVariable.G_timeoutMiniShort, flowControl)

		'Buy button'
		MobileDriver.verifyElementExist(this.btnDynamic(SELL), GlobalVariable.G_timeoutMiniShort, flowControl)
	}
	void verifyPriceQuotePanel(String type = BUY, FailureHandling flowControl = defaultFailureHandling) {
		'Check Title Show'
		MobileDriver.verifyElementVisible(this.lblDynamicContainTwoText(type, RM), GlobalVariable.G_timeoutMiniShort, flowControl)

		'Check Gram Format'
		String buyRmPrice = MobileDriver.getText(this.lblDynamicContainTwoText(type, RM), GlobalVariable.G_timeoutMiniShort, flowControl)
		BaseAssert.equals(buyRmPrice.contains('/g'), true, 'Check gram format')
		info(StringUtils.extractMoney(buyRmPrice.replace('/g', '')))
		BaseAssert.equals(verifyFormatMoney([
			StringUtils.extractMoney(buyRmPrice.replace('/g', ''))
		]), true, 'Check Price format', flowControl)

		'Check Preferential Price'
		'Check Text Show'
		MobileDriver.verifyElementVisible(this.lblGroupSiblingDynamic(type, 1), GlobalVariable.G_timeoutMiniShort, flowControl)
		String preferentialText = MobileDriver.getText(this.lblGroupSiblingDynamic(type, 1), GlobalVariable.G_timeoutMiniShort, flowControl)
		BaseAssert.equals(preferentialText, GOLD_ACCOUNT_I_PREFERENTIAL_PRICE, 'Check Prefential Text Show')
		String preferentialNoteText = MobileDriver.getText(this.lblGroupSiblingDynamic(type, 2), GlobalVariable.G_timeoutMiniShort, flowControl)
		BaseAssert.equals(preferentialNoteText.contains(type + GOLD_ACCOUNT_I_BUY_DEAL), true, 'Check Prefential Deal Show')
		'Check Price Format'
		String preferentialPrice = preferentialNoteText.replaceFirst('/g', "")
		info(StringUtils.extractMoney(preferentialPrice))
		BaseAssert.equals(verifyFormatMoney([
			StringUtils.extractMoney(preferentialPrice)
		]), true, 'Check Price format', flowControl)
	}

	/**
	 * Verify Gold Investment
	 */
	void verifyGoldItemForMultipleGoldAccountsMIGA(FailureHandling flowControl = defaultFailureHandling) {
		List<List<String>> itemList = []

		'Get list view in gold item and verify detail'
		itemList = this.getListViewInWealthItem()
		MobileDriver.takeScreenshot()

		'Check Gold Investment title'
		MobileDriver.waitForElementPresent(commonPage.lblDynamic(GOLD_INVESTMENT), GlobalVariable.G_timeoutMedium, flowControl)

		'Check List Item more than 1 account'
		int listItemSize = itemList.size()
		BaseAssert.equals(listItemSize > 1, true, 'Check List Item more than 1 account', flowControl)

		for(int i = 0; i < listItemSize; i++) {
			String cardNumber = itemList.get(i).get(1)
			info('Verify Gold Item for Account: ' + cardNumber)
			'Scroll to card number'
			Mobile.scrollToText(cardNumber)
			MobileDriver.takeScreenshot()

			'Verify Card Item Show Correctly'
			MobileDriver.verifyElementExist(commonPage.lblDynamic(cardNumber), GlobalVariable.G_timeoutShort, flowControl)
			MobileDriver.verifyElementExist(this.lblPrecedingDynamic(cardNumber, GOLD_ACCOUNT_I_ACCOUNT_NAME), GlobalVariable.G_timeoutShort, flowControl)
			String gram = MobileDriver.getText(this.lblSiblingDynamic(cardNumber, 1), GlobalVariable.G_timeoutMiniShort, flowControl)
			String rm = MobileDriver.getText(this.lblSiblingDynamic(cardNumber, 2), GlobalVariable.G_timeoutMiniShort, flowControl)
			'Gram Should contain g'
			BaseAssert.equals(gram.contains('g'), true, 'Gram Should contain g', flowControl)
			'RM Should contain RM and correct amount format'
			BaseAssert.equals(rm.contains(RM), true, 'RM Should contain RM', flowControl)
			BaseAssert.equals(verifyFormatMoney([StringUtils.extractMoney(rm)]), true, 'Check RM format', flowControl)

			'Select card number'
			MobileDriver.tap(commonPage.lblDynamic(cardNumber), GlobalVariable.G_timeoutLong, flowControl)
			MobileDriver.takeScreenshot()

			'Verify Gold Investment Detail'
			this.verifyGoldInvestmentDetailForMIGAAccount(AmountCheckType.NONE, flowControl)

			'Select Back Icon'
			MobileDriver.tapAndLog(commonPage.imgNoDynamic(1), GlobalVariable.G_timeoutLong, 'Back Icon', flowControl)
		}
	}

	/**
	 * Verify Silver Detail
	 */
	private void verifySilverDetail(FailureHandling flowControl = defaultFailureHandling) {
		info('Verify Silver Investment Title Show')
		MobileDriver.waitForElementPresent(commonPage.lblContainDynamic(SILVER_INVESTMENT), GlobalVariable.G_timeoutLong, flowControl)

		info('Wait for View Transaction')
		MobileDriver.waitForElementPresent(commonPage.lblDynamic(VIEW_TRANSACTIONS), GlobalVariable.G_timeoutMedium, flowControl)

		info('Verify Total Units')
		MobileDriver.verifyElementVisible(commonPage.lblDynamic(TOTAL_UNITS), GlobalVariable.G_timeoutLong, flowControl)

		info('Verify RM show')
		MobileDriver.waitForElementPresent(commonPage.lblContainDynamic(RM), GlobalVariable.G_timeoutMedium, flowControl)

		info('Verify Transaction Empty')
		this.verifyTransactionEmpty(flowControl)
	}

	/**
	 * Verify Unit Trust Detail
	 */
	void verifyUnitTrustDetail(List verifyDetailData, List verifyDataFund, FailureHandling flowControl = defaultFailureHandling) {
		'Wait for Unit Trust present'
		MobileDriver.waitForElementPresent(commonPage.lblDynamic(UNIT_TRUST), GlobalVariable.G_timeoutLong, FailureHandling.OPTIONAL)

		'Verify Detail'
		verifyElements(verifyDetailData, commonPage.lblContainDynamicPath(), GlobalVariable.G_timeoutLong, flowControl)

		'Verify Fund'
		MobileDriver.tap(commonPage.imgNoDynamic(2), GlobalVariable.G_timeoutLong, flowControl)

		'Verify Fund Detail'
		verifyElements(verifyDataFund, commonPage.lblContainDynamicPath(), GlobalVariable.G_timeoutLong, flowControl)
		'Select Cancel'
		commonPage.tapCancelBtn()
	}
	void checkUnitTrustDetailTitle(String cardNumber, AmountCheckType amountCheckType = AmountCheckType.NONE, FailureHandling flowControl = defaultFailureHandling) {
		MobileDriver.delay(GlobalVariable.G_timeoutMiniShort)
		MobileDriver.takeScreenshot()
		MobileDriver.waitForElementPresent(commonPage.lblDynamic(UNIT_TRUST), GlobalVariable.G_timeoutMedium, flowControl)
		MobileDriver.verifyElementExist(commonPage.lblDynamic(cardNumber), GlobalVariable.G_timeoutShort, flowControl)
		MobileDriver.verifyElementExist(commonPage.lblRMNo(1), GlobalVariable.G_timeoutShort, flowControl)
		String rmAmount = MobileDriver.getText(commonPage.lblRMNo(1), GlobalVariable.G_timeoutShort, flowControl)
		MobileDriver.takeScreenshot()
		switch (amountCheckType) {
			case AmountCheckType.GREATER_THAN_ZERO:
				BaseAssert.equals(StringUtils.convertBalanceAmountNumber(rmAmount) > 0, true, 'Verify amount greater than zero', flowControl)
				break
			case AmountCheckType.EQUAL_ZERO:
				BaseAssert.equals(StringUtils.convertBalanceAmountNumber(rmAmount) == 0, true, 'Verify amount equal zero', flowControl)
				break
			default:
				break
		}
	}
	void checkUnitTrustDashBoardDetail(String cardNumber, FailureHandling flowControl = defaultFailureHandling) {
		MobileDriver.takeScreenshot()
		MobileDriver.waitForElementPresent(commonPage.lblDynamic(cardNumber), GlobalVariable.G_timeoutLong, flowControl)
		MobileDriver.verifyElementVisible(commonPage.lblDynamic(UNIT_TRUST), GlobalVariable.G_timeoutLong, flowControl)
		MobileDriver.verifyElementVisible(commonPage.lblContainDynamic(RM), GlobalVariable.G_timeoutMiniShort, flowControl)
		'Verify RM Amount'
		String rmValue = MobileDriver.getText(commonPage.lblContainDynamic(RM), GlobalVariable.G_timeoutMiniShort, flowControl)
		String money = StringUtils.extractMoney(rmValue.replace('*', ''))
		BaseAssert.equals(verifyFormatMoney([money]), true, 'Verify RM format', flowControl)

		'Check Unit Held and value'
		MobileDriver.takeScreenshot()
		MobileDriver.verifyElementVisible(commonPage.lblDynamic(UNIT_HELD), GlobalVariable.G_timeoutMiniShort, flowControl)
		String unitHeldValue = MobileDriver.getText(this.lblSiblingDynamic(UNIT_HELD), GlobalVariable.G_timeoutMiniShort, flowControl)
		BaseAssert.equals(verifyFormatMoney([unitHeldValue]), true, 'Verify Unit Held value format', flowControl)

		'Check Currency and it type'
		MobileDriver.verifyElementVisible(commonPage.lblDynamic(CURRENCY), GlobalVariable.G_timeoutMiniShort, flowControl)
		String currencyValue = MobileDriver.getText(this.lblSiblingDynamic(CURRENCY), GlobalVariable.G_timeoutMiniShort, flowControl)
		BaseAssert.equals(currencyValue, 'MYR', 'Verify Currency Value', flowControl)

		'Check Latest NAV per unit'
		MobileDriver.takeScreenshot()
		MobileDriver.verifyElementVisible(commonPage.lblDynamic(LATEST_NAV_PER_UNIT), GlobalVariable.G_timeoutMiniShort, flowControl)
		String latestNav = MobileDriver.getText(this.lblSiblingDynamic(LATEST_NAV_PER_UNIT), GlobalVariable.G_timeoutMiniShort, flowControl)
		BaseAssert.equals(latestNav.isNumber(), true, 'Verify Latest Nav Value is Number', flowControl)

		'Check current value'
		MobileDriver.verifyElementVisible(commonPage.lblDynamic(CURRENT_VALUE), GlobalVariable.G_timeoutMiniShort, flowControl)
		String currentValue = MobileDriver.getText(this.lblSiblingDynamic(CURRENT_VALUE), GlobalVariable.G_timeoutMiniShort, flowControl)
		BaseAssert.equals(verifyFormatMoney([currentValue]), true, 'Verify Current value format', flowControl)

		'Check current value in RM'
		MobileDriver.takeScreenshot()
		MobileDriver.verifyElementVisible(commonPage.lblDynamic(CURRENT_VALUE_IN_RM), GlobalVariable.G_timeoutMiniShort, flowControl)
		String currentValueRM = MobileDriver.getText(this.lblSiblingDynamic(CURRENT_VALUE_IN_RM), GlobalVariable.G_timeoutMiniShort, flowControl)
		BaseAssert.equals(verifyFormatMoney([currentValueRM]), true, 'Verify Current value in RM format', flowControl)

		'Check Unrealised Gains/Losses in RM'
		MobileDriver.verifyElementVisible(commonPage.lblDynamic(UNREALISED_GAINS), GlobalVariable.G_timeoutMiniShort, flowControl)
		String unrealisedNumber = MobileDriver.getText(this.lblSiblingDynamic(UNREALISED_GAINS), GlobalVariable.G_timeoutMiniShort, flowControl)
		BaseAssert.equals(verifyFormatMoney([
			unrealisedNumber.replace('-', '').replace('+', '')
		]), true, 'Verify Unrealised value in amount format', flowControl)
	}

	/**
	 * Verify Unit Trust Item
	 */
	void verifyUnitTrustItemForMultipleAccounts(FailureHandling flowControl = defaultFailureHandling) {
		MobileDriver.takeScreenshot()
		List<List<String>> itemList = []

		'Get list view in unit item and verify detail'
		itemList = this.getListViewInWealthItem()

		'Check Gold Investment title'
		MobileDriver.takeScreenshot()
		MobileDriver.waitForElementPresent(commonPage.lblDynamic(UNIT_TRUST), GlobalVariable.G_timeoutMedium, flowControl)

		'Check List Item more than 1 account'
		int listItemSize = itemList.size()
		BaseAssert.equals(listItemSize > 1, true, 'Check List Item more than 1 account', flowControl)

		commonPage.swipeText(itemList.get(1).get(1) ,itemList.get(itemList.size() - 1).get(1))
		MobileDriver.takeScreenshot()
		for(int i = 0; i < listItemSize; i++) {
			String cardNumber = itemList.get(i).get(1)
			info('Verify Unit Item for Account: ' + cardNumber)
			'Scroll to card number'
			Mobile.scrollToText(cardNumber)

			'Verify Card Item Show Correctly'
			MobileDriver.takeScreenshot()
			MobileDriver.verifyElementExist(commonPage.lblDynamic(cardNumber), GlobalVariable.G_timeoutShort, flowControl)
			MobileDriver.verifyElementExist(this.lblPrecedingDynamic(cardNumber, UNIT_TRUST), GlobalVariable.G_timeoutShort, flowControl)
			MobileDriver.verifyElementVisible(this.lblSiblingDynamic(cardNumber, 2), GlobalVariable.G_timeoutMiniShort, flowControl)
			String rm = MobileDriver.getText(this.lblSiblingDynamic(cardNumber, 2), GlobalVariable.G_timeoutMiniShort, flowControl)
			info(rm)
			'RM Should contain RM and correct amount format'
			BaseAssert.equals(rm.contains(RM), true, 'RM Should contain RM', flowControl)
			BaseAssert.equals(verifyFormatMoney([StringUtils.extractMoney(rm)]), true, 'Check RM format', flowControl)

			'Select card number'
			Mobile.scrollToText(cardNumber)
			MobileDriver.takeScreenshot()
			MobileDriver.tap(commonPage.lblDynamic(cardNumber), GlobalVariable.G_timeoutLong, flowControl)

			'Verify Unit Trust Detail'
			MobileDriver.takeScreenshot()
			this.checkUnitTrustDetailTitle(cardNumber)

			'Select Back Icon'
			MobileDriver.tapAndLog(commonPage.imgNoDynamic(1), GlobalVariable.G_timeoutLong, 'Back Icon', flowControl)
		}
	}
	void verifyUnitTrustItemForOneAccount(String itemName, AmountCheckType amountCheckType = AmountCheckType.NONE, FailureHandling flowControl = defaultFailureHandling) {
		MobileDriver.waitForElementPresent(commonPage.lblDynamic(UNIT_TRUST), GlobalVariable.G_timeoutShort, FailureHandling.OPTIONAL)
		MobileDriver.takeScreenshot()
		String cardNumber = MobileDriver.getText(this.lblSiblingDynamic(UNIT_TRUST), GlobalVariable.G_timeoutShort, flowControl)
		BaseAssert.equals(verifyFormatAccountNumber([cardNumber]), true, 'Verify Format AccountNumber')

		this.tapItemInListView(itemName, flowControl)
		MobileDriver.takeScreenshot()
		this.checkUnitTrustDetailTitle(cardNumber, amountCheckType, flowControl)
	}
	void verifyUnitTrustDropdown(FailureHandling flowControl = defaultFailureHandling) {
		MobileDriver.takeScreenshot()
		MobileDriver.waitForElementPresent(commonPage.lblSiblingGroup(UNIT_TRUST), GlobalVariable.G_timeoutMiniShort, flowControl)
		MobileDriver.tap(commonPage.lblSiblingGroup(UNIT_TRUST), GlobalVariable.G_timeoutMedium, flowControl)
		'Check Dropdown show'
		MobileDriver.takeScreenshot()
		MobileDriver.waitForElementPresent(commonPage.lblDynamic(commonPage.UIDonebtn), GlobalVariable.G_timeoutMiniShort, flowControl)
		MobileDriver.waitForElementPresent(commonPage.lblDynamic(commonPage.UICancel), GlobalVariable.G_timeoutMiniShort, flowControl)
		'Check Dropdown items list show'
		MobileDriver.takeScreenshot()
		List<WebElement> elements = MobileDriver.driver.findElements(By.xpath("//*[@class='android.widget.TextView' and (@text = 'Done' or . = 'Done')]/ancestor::*[@class='android.view.ViewGroup'][3]/following-sibling::*[@class='android.view.ViewGroup']/*[@class='android.widget.ScrollView']//*[@class='android.widget.TextView']"))
		info('List Size: ' + elements.size())
		info('Item 1: ' + elements.get(0).getText())
		BaseAssert.equals(elements.size() > 1, true, 'Check Unit Trust Dropdown List Have Items')

		'Select Done'
		commonPage.tapDoneBtn()
	}

	/**
	 * Verify MFCA Detail
	 */
	void verifyMFCADetailTitleShow(String cardNumber, FailureHandling flowControl = defaultFailureHandling) {
		'Wait for Total Currency present'
		MobileDriver.takeScreenshot()
		MobileDriver.waitForElementPresent(commonPage.lblDynamic(INVESTMENT_MASTER_FOREIGN_CURRENCY_ACCOUNT), GlobalVariable.G_timeoutMedium, flowControl)

		'Check card number show'
		MobileDriver.waitForElementPresent(commonPage.lblDynamic(cardNumber), GlobalVariable.G_timeoutMiniShort, flowControl)

		'Check RM Format'
		MobileDriver.takeScreenshot()
		String rmText = MobileDriver.getText(commonPage.lblRMNo(1), GlobalVariable.G_timeoutMiniShort, flowControl)
		BaseAssert.equals(verifyFormatMoney([
			StringUtils.extractMoney(rmText)
		]), true, 'Check RM format', flowControl)
	}
	void verifyMFCADetail(FailureHandling flowControl = defaultFailureHandling) {
		'Verify Detail'
		MobileDriver.takeScreenshot()
		MobileDriver.waitForElementPresent(commonPage.lblDynamic(TOTAL_CURRENCY), GlobalVariable.G_timeoutMiniShort, flowControl)
		MobileDriver.waitForElementPresent(commonPage.lblDynamic(VALUE_IN_RM), GlobalVariable.G_timeoutMiniShort, flowControl)
		MobileDriver.waitForElementPresent(this.lblSiblingDynamic(VALUE_IN_RM), GlobalVariable.G_timeoutMiniShort, flowControl)
		MobileDriver.waitForElementPresent(commonPage.lblDynamic(TODAY_PRICE_IN_RM), GlobalVariable.G_timeoutMiniShort, flowControl)
		MobileDriver.waitForElementPresent(this.lblSiblingDynamic(TODAY_PRICE_IN_RM), GlobalVariable.G_timeoutMiniShort, flowControl)
	}
	void verifyMFCADropdown(FailureHandling flowControl = defaultFailureHandling) {
		'Check Dropdown show'
		MobileDriver.takeScreenshot()
		MobileDriver.tap(commonPage.lblContainDynamic("Foreign"), GlobalVariable.G_timeoutLong, flowControl)
		MobileDriver.waitForElementPresent(this.dropdownDynamic(FOREIGN_CURRENCY), GlobalVariable.G_timeoutMedium, flowControl)
		MobileDriver.takeScreenshot()
		MobileDriver.tap(this.dropdownDynamic(FOREIGN_CURRENCY), GlobalVariable.G_timeoutMiniShort, flowControl)
		MobileDriver.waitForElementPresent(this.dropdownDynamic(commonPage.UIDonebtn), GlobalVariable.G_timeoutMiniShort, flowControl)
		MobileDriver.waitForElementPresent(this.dropdownDynamic(commonPage.UICancel), GlobalVariable.G_timeoutMiniShort, flowControl)
		'Select Cancel'
		commonPage.tapCancelBtn()
	}

	/**
	 * Verify MFCA Item
	 */
	String verifyMFCAItem(boolean manyAccounts = false, FailureHandling flowControl = defaultFailureHandling) {
		'Verify MFCA title Show'
		MobileDriver.takeScreenshot()
		MobileDriver.waitForElementPresent(commonPage.lblDynamic(FOREIGN_CURRENCY), GlobalVariable.G_timeoutLong, flowControl)

		'Check no of account show if user have many MFCA accounts'
		String accountNumber = ''
		if (manyAccounts) {
			MobileDriver.takeScreenshot()
			MobileDriver.waitForElementPresent(this.lblDynamicContainTwoText(FOREIGN_CURRENCY, NO_OF_ACCOUNT), GlobalVariable.G_timeoutShort, flowControl)
			String noOfAccount = MobileDriver.getText(this.lblDynamicContainTwoText(FOREIGN_CURRENCY, NO_OF_ACCOUNT), GlobalVariable.G_timeoutShort, flowControl)

			String regex = "\\d+"
			Pattern pattern = Pattern.compile(regex)
			Matcher matcher = pattern.matcher(noOfAccount)
			boolean hasNumber = matcher.find()
			info("Found number: " + hasNumber)
			BaseAssert.equals(hasNumber, true, 'Verify number exists in account string')
			info('Number: ' + Integer.parseInt(matcher.group()))
			BaseAssert.equals(Integer.parseInt(matcher.group()) > 1, true, 'Verify number of accounts > 1')
		} else {
			MobileDriver.takeScreenshot()
			'Verify Account Number is account number format'
			MobileDriver.tap(commonPage.lblDynamic(FOREIGN_CURRENCY), GlobalVariable.G_timeoutShort, flowControl)
			accountNumber = MobileDriver.getText(this.lblSiblingDynamic(FOREIGN_CURRENCY), GlobalVariable.G_timeoutShort, flowControl)
			BaseAssert.equals(verifyFormatAccountNumber([accountNumber]), true, 'Verify Format AccountNumber')
		}

		'Verify RM Show'
		MobileDriver.takeScreenshot()
		MobileDriver.verifyElementVisible(this.lblDynamicContainTwoText(FOREIGN_CURRENCY, RM), GlobalVariable.G_timeoutShort, flowControl)
		String amount = MobileDriver.getText(this.lblDynamicContainTwoText(FOREIGN_CURRENCY, RM), GlobalVariable.G_timeoutShort, flowControl)

		'Extract amount to money format'
		amount = amount.replaceFirst(/\*$/, "")
		String amountNo = StringUtils.extractMoney(amount)

		'Verify amount format'
		MobileDriver.takeScreenshot()
		BaseAssert.equals(verifyFormatMoney([amountNo]), true, 'Verify amount format', flowControl)

		return accountNumber
	}
	void verifyMFCAItems(FailureHandling flowControl = defaultFailureHandling) {
		List<List<String>> itemList = []

		'Verify Your Accounts List Title Show'
		MobileDriver.verifyElementVisible(commonPage.lblDynamic(YOUR_ACCOUNTS), GlobalVariable.G_timeoutShort)

		'Get list view in wealth item and verify detail'
		itemList = this.getListViewInWealthItem()
		for(int i = 0; i<itemList.size(); i++) {
			String cardNumber = itemList.get(i).get(1)
			'Scroll to card number'
			Mobile.scrollToText(cardNumber)

			MobileDriver.waitForElementPresent(this.lblDynamicContainTwoText(FOREIGN_CURRENCY, cardNumber), GlobalVariable.G_timeoutMiniShort, flowControl)
			MobileDriver.waitForElementPresent(this.lblDynamicContainTwoText(cardNumber, RM), GlobalVariable.G_timeoutMiniShort, flowControl)

			String amount = MobileDriver.getText(this.lblDynamicContainTwoText(cardNumber, RM), GlobalVariable.G_timeoutShort, flowControl)

			'Extract amount to money format'
			amount = amount.replaceFirst(/\*$/, "")
			String amountNo = StringUtils.extractMoney(amount)

			'Verify amount format'
			BaseAssert.equals(verifyFormatMoney([amountNo]), true, 'Verify amount format', flowControl)

			MobileDriver.tap(commonPage.lblDynamic(cardNumber), GlobalVariable.G_timeoutLong, flowControl)

			'Verify MFCA Detail Title'
			this.verifyMFCADetailTitleShow(cardNumber)

			'Select Back Icon'
			MobileDriver.tapAndLog(commonPage.imgNoDynamic(1), GlobalVariable.G_timeoutLong, 'Back Icon', flowControl)
		}
	}

	/**
	 * Verify Digital Wealth Dashboard
	 */
	void verifyDigitalWealthDashboard(List totalInvestmentData, FailureHandling flowControl = defaultFailureHandling) {
		MobileDriver.waitForElementPresent(commonPage.lblDynamic(TOTAL_INVESTMENTS), GlobalVariable.G_timeoutLong, FailureHandling.OPTIONAL)
		'Tap tooltip Total Investments'
		MobileDriver.tap(commonPage.imgNoDynamic(2), GlobalVariable.G_timeoutLong, flowControl)
		'Verify Total Investments detail'
		verifyElements(totalInvestmentData, commonPage.lblContainDynamicPath(), GlobalVariable.G_timeoutLong, flowControl)
		MobileDriver.takeScreenshot()
		'Select Back Icon'
		MobileDriver.tap(commonPage.imgNoDynamic(1), GlobalVariable.G_timeoutLong, flowControl)
	}

	/**
	 * Verify Digital Wealth Detail
	 */
	void verifyDigitalWealthOverview(List verifydataOverview, FailureHandling flowControl = defaultFailureHandling) {
		'Select View Wealth Portfolio'
		MobileDriver.tap(commonPage.lblDynamic(VIEW_WEALTH_PORTFOLIO), GlobalVariable.G_timeoutLong, flowControl)
		MobileDriver.verifyElementVisible(commonPage.lblDynamic(PORTFOLIO), GlobalVariable.G_timeoutLong, flowControl)
		MobileDriver.delay(GlobalVariable.G_timeoutMiniShort)
		'Select My Assets'
		MobileDriver.tap(commonPage.lblDynamic(MY_ASSETS), GlobalVariable.G_timeoutLong, flowControl)
		'Verify content of overview'
		verifyElements(verifydataOverview, commonPage.lblDynamicPath(), GlobalVariable.G_timeoutLong, flowControl)
		'Get amount of account, investment, my assets, my liabilities'
		String accountAmt = MobileDriver.getText(this.lblSiblingDynamic(ACCOUNTS), GlobalVariable.G_timeoutLong, flowControl)
		String investmentAmt = MobileDriver.getText(this.lblSiblingDynamic(INVESTMENTS), GlobalVariable.G_timeoutLong, flowControl)
		String myAssetsAmt = MobileDriver.getText(this.lblSiblingDynamic(MY_ASSETS), GlobalVariable.G_timeoutLong, flowControl)
		String myLiabilitiesAmt = MobileDriver.getText(this.lblSiblingDynamic(My_LIABILITIES), GlobalVariable.G_timeoutLong, flowControl)
		boolean verifyAmt = verifyFormatMoneyRM([
			accountAmt,
			investmentAmt,
			myAssetsAmt,
			myLiabilitiesAmt
		])
		MobileDriver.takeScreenshot()
		BaseAssert.equals(verifyAmt, true, 'Verify Format Money of Overview Tab')
		'Select Account'
		MobileDriver.tap(commonPage.lblSiblingOneGroup('Accounts'), GlobalVariable.G_timeoutLong, flowControl)
	}
	/**
	 * Verify Cash Detail
	 */
	void verifyWealthAssetClassification(List verifyDataAssetClassification,List cashList, List verifyAssetTooltip, String accountType, FailureHandling flowControl = defaultFailureHandling) {
		'Select View Wealth Portfolio'
		MobileDriver.tap(commonPage.lblDynamic(VIEW_WEALTH_PORTFOLIO), GlobalVariable.G_timeoutLong, flowControl)
		MobileDriver.verifyElementVisible(commonPage.lblDynamic(PORTFOLIO), GlobalVariable.G_timeoutLong, flowControl)
		MobileDriver.delay(GlobalVariable.G_timeoutMiniShort)
		'Select Asset Classification'
		MobileDriver.tap(commonPage.lblDynamic(ASSET_CLASSIFICATION), GlobalVariable.G_timeoutLong, flowControl)
		'Verify content of asset classification'
		'Verify Asset Classification Detail'
		verifyElements(verifyDataAssetClassification, commonPage.lblDynamicPath(), GlobalVariable.G_timeoutLong, flowControl)
		cashList.each{item ->
			'Select item'
			MobileDriver.tap(commonPage.lblNormalizeSpace(item), GlobalVariable.G_timeoutLong, flowControl)
			Mobile.delay(GlobalVariable.G_timeoutMiniShort)
			'Verify Item Detail'
			MobileDriver.verifyElementExist(commonPage.lblContainDynamic(item), GlobalVariable.G_timeoutLong, flowControl)
			'Select Back Icon'
			MobileDriver.tapAndLog(commonPage.imgNoDynamic(1), GlobalVariable.G_timeoutLong, 'Back Icon', flowControl)
		}
		MobileDriver.takeScreenshot()
		'Verify Asset Classification Tooltip'
		MobileDriver.tap(commonPage.imgNoDynamic(4), GlobalVariable.G_timeoutLong, flowControl)
		verifyElements(verifyAssetTooltip, commonPage.lblContainDynamicPath(), GlobalVariable.G_timeoutLong, flowControl)
		MobileDriver.tap(commonPage.imgNoDynamic(1), GlobalVariable.G_timeoutLong, flowControl)
		Mobile.delay(GlobalVariable.G_timeoutMiniShort)
		'Verify Up and Down Button'
		MobileDriver.tap(commonPage.imgNoDynamic(3), GlobalVariable.G_timeoutLong, flowControl)
		Mobile.delay(GlobalVariable.G_timeoutMiniShort)
		MobileDriver.tap(commonPage.imgNoDynamic(5), GlobalVariable.G_timeoutLong, flowControl)
		'Select Current Account'
		MobileDriver.tap(commonPage.lblDynamic(accountType), GlobalVariable.G_timeoutLong, flowControl)
		'Verify Elements Exist'
		MobileDriver.verifyElementVisible(commonPage.lblDynamic(UILblCurrency), GlobalVariable.G_timeoutShort)
		MobileDriver.verifyElementVisible(commonPage.lblDynamic(UILblPercent), GlobalVariable.G_timeoutShort)
	}
	void buyGold(Map<String,String> transactionMap, boolean isTransferByGold = false, int timeout = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		tapToTransaction(transactionMap.get("type"))
		if(isTransferByGold) {
			changeTransferType()
		}
		enterAmountToBuy(transactionMap.get("amount"))
		commonPage.tapNextBtn()
		commonPage.waitLoading()
		MobileDriver.delay(GlobalVariable.G_timeoutMiniShort)
		getAmountForBuy()
		if(isTransferByGold) {
			transactionMap.put("amount", StringUtils.extractNumber(cost))
		}
		MobileDriver.tap(commonPage.lblDynamic(BUY_NOW), timeout, flowControl)
		commonPage.waitLoading()
	}
	void tapToTransaction(String transactionType, int timeout = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		commonPage.swipe(5, 1500, 5, 100)
		delay(GlobalVariable.G_timeoutMiniShort)
		MobileDriver.tap(commonPage.lblDynamicTextLast(transactionType), timeout, flowControl)
		commonPage.waitLoading()
	}

	void enterAmountToBuy(String amount, int timeout = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		MobileDriver.tap(commonPage.txtEditNo(), timeout, flowControl)
		commonPage.enterNO(amount, false)
		commonPage.tapTickBtn()
	}
	void getAmountForBuy(int timeout = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		cost = MobileDriver.getText(commonPage.lblRMNo(1), timeout, flowControl).split(" ")[1]
		Mobile.scrollToText(GOLD_WEIGHT)
		goldAmount = MobileDriver.getText(this.lblSiblingDynamic(GOLD_WEIGHT), timeout, flowControl)
		currentPrice = MobileDriver.getText(commonPage.lblSiblingGroup(CURRENT_PRICE), timeout, flowControl)
		date = MobileDriver.getText(commonPage.lblSiblingGroup(DATE), timeout, flowControl)
	}
	void verifyReceipt(int timeout = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		String goldReceipt = MobileDriver.getText(this.lblSiblingGold(GOLD_WEIGHT), timeout, flowControl)
		String amount = MobileDriver.getText(this.lblSiblingGold(AMOUNT), timeout, flowControl).split(" ")[1]
		info("amout: "+  amount)
		info("goldReceipt: "+  goldReceipt)
		goldAmount = amount
		cost = goldReceipt
		addToMigaList()
	}
	void changeTransferType(int timeout = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		MobileDriver.tap(commonPage.lblSiblingGroup(I_WANT_TO_BUY), timeout, flowControl)
		delay(GlobalVariable.G_timeoutMiniShort)
		commonPage.selectDropdownScrollOneLine(GRAM, timeout, flowControl)
		commonPage.tapDoneBtn()
	}
	List<List<String>> getMigaDetails() {
		return commonPage.getAllItemsListView([
			"//android.widget.TextView[3]",
		], commonPage.frmScrollView())
	}
	String getBalance(int timeout = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		Mobile.scrollToText(TOTAL_PURCHASED)
		return MobileDriver.getText(this.lblSiblingDynamic(TOTAL_PURCHASED), timeout, flowControl).split(" ").last()
	}
	void verifyDashboard(int timeout = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		def migaDetails = getMigaDetails()
		String currentGoldValue =  MobileDriver.getText(this.lblSiblingDynamic(CURRENT_GOLD_VALUE), timeout, flowControl).split(" ").last()
		String currentGoldValueMiga =  StringUtils.extractValuesListView(migaDetails, 0).get(0).split(" ").last()
		BaseAssert.equals(currentGoldValue, currentGoldValueMiga, "Verify current gold value")

		String regex = "RM \\d+(\\.\\d{1,2})?/g";
		buyPrice = MobileDriver.getText(this.lblSiblingDynamic(BUY), timeout, flowControl)
		sellPrice = MobileDriver.getText(this.lblSiblingDynamic(SELL), timeout, flowControl)
		info('Verify buy price in dashboard')
		Mobile.verifyMatch(buyPrice, regex, true)
		info('Verify sell price in dashboard')
		Mobile.verifyMatch(sellPrice, regex, true)
	}
	void sellGold(Map<String,String> transactionMap, int timeout = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		tapToTransaction(transactionMap.get("type"))
		enterAmountToBuy(transactionMap.get("amount"))
		commonPage.tapNextBtn()
		commonPage.waitLoading()
		MobileDriver.delay(GlobalVariable.G_timeoutMiniShort)
		getAmountForBuy()
		MobileDriver.tap(commonPage.lblDynamic(SELL_NOW), timeout, flowControl)
		commonPage.waitLoading()
	}
	float getGold(int timeout = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		Mobile.scrollToText(AVAILABLE_GOLD)
		String goldString = MobileDriver.getText(this.lblSiblingDynamic(AVAILABLE_GOLD), timeout, flowControl).split(" ").first()
		Float goldValue = Float.valueOf(goldString);
		return goldValue
	}

	void verifyGoldAfterSell(float oldGold, float newGold, String amount, String description = 'Check Gold', FailureHandling flowControl = defaultFailureHandling) {
		float calculatedGold = (oldGold - newGold)
		BaseAssert.equals(convertGramToString(calculatedGold).toString(), amount, description, flowControl)
	}

	/**
	 * verifyBalanceAfterBuyingGold - Verify balance increased after buying gold
	 * For Buy Gold, Total Purchased amount increases (newBalance > oldBalance)
	 */
	void verifyBalanceAfterBuyingGold(String oldBalance, String newBalance, String amount, String description = 'Check Amount', FailureHandling flowControl = defaultFailureHandling) {
		def newBalanceBigInt = StringUtils.convertBalanceAmountNumber(newBalance)
		def oldBalanceBigInt = StringUtils.convertBalanceAmountNumber(oldBalance)
		BaseAssert.equals((newBalanceBigInt - oldBalanceBigInt).toString(), amount, description, flowControl)
	}

	int convertGramToString(float value) {
		DecimalFormat df = new DecimalFormat("#.###");
		int formatedGram = Integer.parseInt(df.format(value).replace(".", ""))
		return formatedGram
	}

	String generateNotification(String type) {
		String transactionType = type.equals('Buy') ? 'bought' : 'sold'
		String text = "You've successfully " + transactionType + " RM " + goldAmount + " worth of gold for " + currentPrice
		return text
	}

	void addToMigaList() {
		migaList.add(goldAmount)
		migaList.add(cost)
	}

	/**
	 * verifyTransactionHistory
	 * @return
	 */
	void verifyTransactionHistory(String transactionType, FailureHandling flowControl = defaultFailureHandling) {
		
		commonPage.scrollToTop()
		'Select view transactions'
		MobileDriver.tap(accountDetailPage.btnViewTransactions(), GlobalVariable.G_timeoutLong, flowControl)
		MobileDriver.delay(GlobalVariable.G_timeoutMiniShort)
		
		String dateMadeTransaction =  Mobile.getText(commonPage.lblTextNo(2), GlobalVariable.G_timeoutLong, FailureHandling.STOP_ON_FAILURE)
		BaseAssert.equals(dateMadeTransaction.equals(date), true, "Verify date in transaction")

		String transaction = Mobile.getText(commonPage.lblTextNo(3), GlobalVariable.G_timeoutLong, FailureHandling.STOP_ON_FAILURE)
		info("amout: "+  goldAmount)
		BaseAssert.equals(transaction.contains(transactionType), true, "Verify transactionType in transaction")
		commonPage.verifyElementExist(commonPage.lblContainDynamic(goldAmount), GlobalVariable.G_timeoutShort, flowControl)

		String money = Mobile.getText(this.lblSiblingDynamic(transaction), GlobalVariable.G_timeoutLong, FailureHandling.STOP_ON_FAILURE)
		commonPage.verifyElementExist(commonPage.lblContainDynamic(cost), GlobalVariable.G_timeoutShort, flowControl)
	}

	void verifyTransactionHistoryUI(FailureHandling flowControl = defaultFailureHandling) {
		'Select view transactions'
		if(MobileDriver.waitForElementPresent(accountDetailPage.btnViewTransactions(), GlobalVariable.G_timeoutShort, FailureHandling.OPTIONAL)) {
			MobileDriver.tap(accountDetailPage.btnViewTransactions(), GlobalVariable.G_timeoutShort, flowControl)
			MobileDriver.takeScreenshot()
		}
		else {
			MobileDriver.tap(commonPage.lblContainDynamic("Gold"), GlobalVariable.G_timeoutShort, flowControl)
			MobileDriver.takeScreenshot()
			
			MobileDriver.waitForElementPresent(accountDetailPage.btnViewTransactions(), GlobalVariable.G_timeoutLong, flowControl)
			MobileDriver.tap(accountDetailPage.btnViewTransactions(), GlobalVariable.G_timeoutShort, flowControl)
			MobileDriver.takeScreenshot()
		}
		

		'Verify Transaction Title Show or Transaction History Unavailable'
		if (MobileDriver.waitForElementPresent(commonPage.lblDynamic(TRANSACTIONS_HISTORY_UNAVAILABLE), GlobalVariable.G_timeoutShort, FailureHandling.OPTIONAL)) {
			info('Transaction History Unavailable - No history to display')
			MobileDriver.verifyElementVisible(commonPage.lblDynamic(TRANSACTIONS_HISTORY_UNAVAILABLE), GlobalVariable.G_timeoutMedium, flowControl)

			MobileDriver.takeScreenshot()
			return
		}
		
		else {
		MobileDriver.verifyElementVisible(commonPage.lblContainDynamic(TRANSACTIONS), GlobalVariable.G_timeoutLong, flowControl)

		'Verify Transaction History Item Show'
		MobileDriver.verifyElementVisible(this.lblListDataItems(), GlobalVariable.G_timeoutShort, flowControl)
		MobileDriver.takeScreenshot()

		'Check Transactions Date and Data'
		List<WebElement> elements = MobileDriver.driver.findElements(By.xpath('//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup//android.widget.TextView'))
		info('List Size: ' + elements.size())
		BaseAssert.equals(elements.size() > 1, true, 'Check Transaction History Have Item')
		MobileDriver.verifyElementVisible(this.lblListDataItems(), GlobalVariable.G_timeoutShort, flowControl)
		'Check Transactions Date'
		String transactionDate = elements.get(0).getText()
		info('Check Transaction Date: ' + transactionDate)
		BaseAssert.equals(DateUtils.isValidDate(transactionDate, "dd MMM yyyy"), true, 'Check Transaction Date Format')
		MobileDriver.takeScreenshot()
		'If have Transfer data'
		if (MobileDriver.verifyElementVisible(commonPage.lblContainDynamic(TRANSACTIONS_HISTORY_TRANSFER), GlobalVariable.G_timeoutLong, FailureHandling.OPTIONAL)) {
			String transferTxt = MobileDriver.getText(commonPage.lblContainDynamic(TRANSACTIONS_HISTORY_TRANSFER), GlobalVariable.G_timeoutLong, flowControl)
			BaseAssert.equals(transferTxt.contains('gms'), true, 'Check Transfer Data Format')
		}

		'If have Buy data'
		if (MobileDriver.verifyElementVisible(commonPage.lblContainDynamic(BUY), GlobalVariable.G_timeoutLong, FailureHandling.OPTIONAL)) {
			String buyTxt = MobileDriver.getText(commonPage.lblContainDynamic(BUY), GlobalVariable.G_timeoutLong, flowControl)
			BaseAssert.equals(buyTxt.contains('gms'), true, 'Check Buy Data Format')
			String buyGoldValue = MobileDriver.getText(this.lblContainSibling(BUY), GlobalVariable.G_timeoutLong, flowControl)
			BaseAssert.equals(verifyFormatMoneyRM([buyGoldValue]), true, 'Check Buy Amount Format')
		}
		}
	}

	/**
	 * verifyTransactionEmpty
	 * @return
	 */
	void verifyTransactionEmpty(FailureHandling flowControl = defaultFailureHandling) {
		info('Select View Transaction')
		MobileDriver.tap(commonPage.lblDynamic(VIEW_TRANSACTIONS), GlobalVariable.G_timeoutMedium, flowControl)
		MobileDriver.takeScreenshot()

		info('Verify Unavailable text title and content show')
		MobileDriver.verifyElementVisible(commonPage.lblDynamic(TRANSACTIONS_HISTORY_UNAVAILABLE), GlobalVariable.G_timeoutMedium, flowControl)
		MobileDriver.verifyElementVisible(commonPage.lblDynamic(TRANSACTIONS_HISTORY_NOTHING), GlobalVariable.G_timeoutMedium, flowControl)
		MobileDriver.takeScreenshot()

		info('Back')
		MobileDriver.tapAndLog(commonPage.imgNoDynamic(1), GlobalVariable.G_timeoutLong, 'Back Icon', flowControl)
	}

	void checkAmountValue(String amount, AmountCheckType amountCheck = AmountCheckType.NONE, FailureHandling flowControl = defaultFailureHandling) {
		switch (amountCheck) {
			case AmountCheckType.GREATER_THAN_ZERO:
				BaseAssert.equals(StringUtils.convertBalanceAmountNumber(amount) > 0, true, 'Verify amount greater than zero', flowControl)
				break
			case AmountCheckType.EQUAL_ZERO:
				BaseAssert.equals(StringUtils.convertBalanceAmountNumber(amount) == 0, true, 'Verify amount equal zero', flowControl)
				break
			default:
				break
		}
	}

	/**
	 * shareMigaReceipts - Share receipts with bidirectional scrolling for MIGA
	 * This method verifies elements by scrolling both DOWN and UP directions
	 */
	void shareMigaReceipts(List<String> verifyTexts, String UIBack = ShareReceiptPage.DONE_BTN, FailureHandling flowControl = defaultFailureHandling) {
		'Select Share receipt'
		commonPage.tapText(ShareReceiptPage.SHARE_RECEIPT, GlobalVariable.G_timeoutOneMinute, flowControl)

		'Wait for Share Receipt WebView not present'
		MobileDriver.waitForElementNotPresent(shareReceiptPage.fraShareReceiptWebView(), GlobalVariable.G_timeoutShort, FailureHandling.OPTIONAL)

		commonPage.delay(GlobalVariable.G_timeoutMiniShort - 1)

		'Verify texts with bidirectional scrolling'
		verifyElementsWithBidirectionalScroll(verifyTexts, commonPage.lblContainDynamicPath(), GlobalVariable.G_timeoutLong, shareReceiptPage.fraShareReceiptWebView(), flowControl)
		MobileDriver.takeScreenshot()

		'Select share icon'
		shareReceiptPage.tapShareIcon(flowControl)
		'Tap back'
		MobileDriver.tapAndLog(commonPage.imgNoDynamic(1), GlobalVariable.G_timeoutLong, 'Icon Back', flowControl)

		'If UIBack is not null, then verify UIBack'
		if (UIBack != null) {
			verifyElements([UIBack], commonPage.lblDynamicPath(), GlobalVariable.G_timeoutLong)
		}
	}

	/**
	 * verifyElementsWithBidirectionalScroll - Verify elements with both DOWN and UP scroll directions
	 */
	void verifyElementsWithBidirectionalScroll(List<String> lst, String pathObject, int timeout, TestObject frmScroll = commonPage.frmHorizontalScrollNo(1), FailureHandling flowControl = defaultFailureHandling) {
		lst.each { item ->
			def variable = item
			TestObject to = findTestObject(pathObject, [('text') : variable])
			info("Verify element ${to} param: {text: ${variable}} ")
			if (!MobileDriver.waitForElementPresent(to, timeout, FailureHandling.OPTIONAL)) {
				'Try scrolling DOWN first'
				boolean found = commonPage.scrollToTestObject(frmScroll, to, CommonPage.ScrollDirectionEnum.DOWN, GlobalVariable.G_timeoutShort, FailureHandling.OPTIONAL)
				if (!found) {
					'If not found, try scrolling UP'
					found = commonPage.scrollToTestObject(frmScroll, to, CommonPage.ScrollDirectionEnum.UP, GlobalVariable.G_timeoutShort, FailureHandling.OPTIONAL)
				}
				delay(GlobalVariable.G_timeoutShort)
				verifyElementVisible(to, timeout, flowControl)
			}
		}
	}
}
