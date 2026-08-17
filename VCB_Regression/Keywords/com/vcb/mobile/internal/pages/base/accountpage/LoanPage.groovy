package com.vcb.mobile.internal.pages.base.accountpage

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
import com.vcb.assertions.BaseAssert
import com.vcb.common.MobileDriver
import com.vcb.mobile.internal.pages.base.BasePage
import com.vcb.mobile.internal.pages.base.CommonPage
import com.vcb.utils.StringUtils

import internal.GlobalVariable

public abstract class LoanPage extends BasePage{

	abstract TestObject lblRMOutstandingBalance()
	abstract TestObject fraCardListView()

	CommonPage commonPage = getPage(CommonPage)

	String UIOutstandingBalance = 'Outstanding Balance'
	//Loan Details
	List<String> UILoanDetail = [
		'Account Name',
		'Outstanding Balance',
		'Payment Due Date',
		'Monthly Instalment', 
		'Instalment Due', 
		'Payment in Advance', 
		'Profit Rate', 
		'Payment Frequency', 
		'Outstanding GPP', 
		'Misc Charges'
	]

	//loan type
	String UIAnynAmount = 'Any\nAmount'
	String UIMonthlyInstalmentAmount = 'Monthly\nInstalment\nAmount'
	String UIMonthlyInstalmentDue = 'Monthly\nInstalment\nDue'

	//Confirmation
	String UIConfirmation = 'Confirmation'

	def getAllCardListView() {
		List<Object> items = commonPage.getAllItemsListView([
			"//android.widget.TextView[2]",
			"//android.widget.TextView[1]",
			"//android.widget.TextView[3]"
		], this.fraCardListView())
		println(items)
		return items
	}

	private boolean verifyFormatMoneyLoan(FailureHandling flowControl = defaultFailureHandling) {
		'Get total balance'
		String balance = MobileDriver.getText(this.lblRMOutstandingBalance(), GlobalVariable.G_timeoutLong, flowControl)

		'Verify total balance format money'
		boolean result = verifyFormatMoney([
			StringUtils.extractMoney(balance)
		])
		BaseAssert.equals(result, true, 'Verify total balance')
		return result
	}

	boolean verifyAllCardLoan(boolean skipVerifyCardList) {
		'If skip verify card list is true, then skip verify card list'
		if(skipVerifyCardList) return
			'Get all card list view'
		def cardLists = this.getAllCardListView()
		'Get all money list'
		List<String> moneyList = cardLists.collect(){it[2]}
		'Extract money number'
		List<String> moneyNumberList = moneyList.collect { StringUtils.extractMoney(it) }
		print(moneyNumberList)
		'Get all account number list'
		List<String> accountNumberList = cardLists.collect { it[0] }
		print(accountNumberList)
		'Verify format money'
		boolean formatMoneyResult = verifyFormatMoney(moneyNumberList)
		BaseAssert.equals(formatMoneyResult, true, 'Format Money All Card is correct')

		'Verify format account number'
		boolean formatAccountNoResult = verifyFormatAccountNumber(accountNumberList)
		BaseAssert.equals(formatAccountNoResult, true, 'Format Account Number All Card is correct')
	}


	boolean verifyLoanDashboard(String product, FailureHandling flowControl = defaultFailureHandling) {
		'Scroll to top'
		commonPage.scrollToTop(5, 500, 5, 1500)
		'Wait for Outstanding Balance'
		MobileDriver.waitForElementPresent(commonPage.lblDynamic(UIOutstandingBalance), GlobalVariable.G_timeoutLong, FailureHandling.OPTIONAL)
		'Verify Outstanding Balance format money'
		this.verifyFormatMoneyLoan(flowControl)
		'Verify Outstanding Balance is visible'
		MobileDriver.verifyElementVisible(commonPage.lblDynamic(UIOutstandingBalance), GlobalVariable.G_timeoutLong, flowControl)

		'Scroll to product'
		commonPage.scrollToText(product, 5, 1500, 5, 500)
		'Select product'
		MobileDriver.tap(commonPage.lblDynamic(product), GlobalVariable.G_timeoutLong, flowControl)
	}

	String getPayBtnName(String product, FailureHandling flowControl = defaultFailureHandling){
		String payLabel
		'If product contains financing, then Pay financing, else Pay loan'
		if(product.toLowerCase().contains('financing')) {
			payLabel = 'Pay Financing'
		}else {
			payLabel = 'Pay Loan'
		}

		return payLabel
	}

	void loanDetails(String product, FailureHandling flowControl = defaultFailureHandling) {
		'Get pay label'
		String payLabel = getPayBtnName(product)
		'Wait for pay label present'
		MobileDriver.waitForElementPresent(commonPage.lblDynamic(payLabel), GlobalVariable.G_timeoutLong, FailureHandling.OPTIONAL)
		'Verify pay label is visible'
		MobileDriver.verifyElementVisible(commonPage.lblDynamic(product), GlobalVariable.G_timeoutLong, flowControl)
		'Verify ...RM is visible'
		MobileDriver.verifyElementVisible(commonPage.lblRMNo(1), GlobalVariable.G_timeoutLong, flowControl)

		'Verify Account Name, Outstanding Balance, Payment Due Date is visible'
		verifyElements(UILoanDetail, commonPage.lblDynamicPath(), GlobalVariable.G_timeoutLong)

		'Select pay label'
		MobileDriver.tap(commonPage.lblDynamic(payLabel), GlobalVariable.G_timeoutLong, flowControl)
	}

	void loanTypeOfPayment(String product, FailureHandling flowControl = defaultFailureHandling) {
		'Verify Any Amount, Monthly Instalment Amount, Monthly Instalment Due is visible'
		verifyElements([
			UIAnynAmount,
			product,
			UIMonthlyInstalmentAmount,
			UIMonthlyInstalmentDue
		], commonPage.lblDynamicPath(), GlobalVariable.G_timeoutMedium)
		'Select Any Amount'
		MobileDriver.tap(commonPage.lblDynamic(UIAnynAmount), GlobalVariable.G_timeoutLong, flowControl)
		Mobile.takeScreenshot()
	}

	void enterAmount(String amount = null) {
		'If amount is null, then generate random amount'
		if(!amount) {
			Random rand = new Random()
			amount = Integer.toString(rand.nextInt(100) + 100)
		}

		'Enter amount'
		commonPage.enterNO(amount, true, 'Enter amount')
	}

	void verifyNoLoanFinancing(String loanCode = null, int timeout = GlobalVariable.G_timeoutMedium , FailureHandling flowControl = defaultFailureHandling) {
		'If loan code is null, then verify no loan/financing available and you dont have any loan or financing accounts at the moment'
		if(!loanCode) {
			commonPage.verifyElementExist(commonPage.lblAnyText("No Loan/Financing Available"), timeout, flowControl)
			commonPage.verifyElementExist(commonPage.lblAnyText("You don't have any loan or financing accounts at the moment."), timeout, flowControl)
		}
		else {
			//TODO: Add more loan code
			switch (loanCode) {
				case 'term loan':
				case 'hire purchase':
				case 'personal loan':
				case 'submitted loan':
				case 'overseas loan':
					break
			}
		}
	}

	def getOutStandingBalance(FailureHandling flowControl = defaultFailureHandling) {
		'Scroll to top'
		commonPage.scrollToTop(5, 500, 5, 1500)
		'Wait for Outstanding Balance'
		MobileDriver.waitForElementPresent(commonPage.lblDynamic(UIOutstandingBalance), GlobalVariable.G_timeoutLong, FailureHandling.OPTIONAL)
		'Get Outstanding Balance'
		String lblBalance = MobileDriver.getText(this.lblRMOutstandingBalance(), GlobalVariable.G_timeoutLong, flowControl)
		Mobile.comment(lblBalance)

		'Format money'
		String getBalanceValue = lblBalance.replaceAll("[a-zA-Z]+",'').replaceAll(",",'').replaceAll("\\s+",'').trim()
		Mobile.comment(getBalanceValue)
		'Parse balance to double'
		Double outStandingBalance = Double.parseDouble(getBalanceValue)
		Mobile.comment(outStandingBalance.toString())
		return outStandingBalance
	}


	def getTotalAggregatedOutstandingBalances() {
		'Scroll to top'
		commonPage.scrollToTop(5, 500, 5, 1500)
		'Get all card list view'
		def cardLists = this.getAllCardListView()
		'Get all money list'
		List<String> moneyList = cardLists.collect(){it[2]}
		'Format money'
		List<String> moneyNumberList = moneyList.collect { it.replaceAll("[a-zA-Z]+",'').replaceAll(",",'').replaceAll("\\s+",'').trim() }
		print(moneyNumberList)
		Double cardsTotal = 0
		for(String cardAmount : moneyNumberList) {
			Mobile.comment(cardAmount.toString())
			'Parse amount to double'
			cardsTotal += Double.parseDouble(cardAmount)
			Mobile.comment("Total cards amount is " +cardsTotal.toString())
		}
		Mobile.comment("Total cards amount is " +cardsTotal.toString())

		return cardsTotal
	}

	Boolean validateLoanDashboardInfo(List<Object> cardLists, String loanType, String accountNo, String loanAmount, FailureHandling flowControl = defaultFailureHandling) {
		Mobile.comment(cardLists.size().toString())
		Boolean isExisted = false
		for (def card : cardLists) {
			'Get account no'
			String getAccountNo = card[0]
			Mobile.comment(getAccountNo)
			'Get loan type'
			String getLoanType = card[1]
			Mobile.comment(getLoanType)
			'Get loan amount'
			String getLoanAmount = card[2]
			Mobile.comment(getLoanAmount)
			'If loan type, account no, loan amount is existed, then return true'
			if(getLoanType == loanType && getAccountNo == accountNo && getLoanAmount == loanAmount) {
				isExisted = true
				Mobile.comment(isExisted.toString())
				break
			}
		}
		Mobile.comment(isExisted.toString())
		return isExisted
	}
}