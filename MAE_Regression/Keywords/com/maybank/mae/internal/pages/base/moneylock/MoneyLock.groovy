package com.maybank.mae.internal.pages.base.moneylock

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
import com.maybank.common.MobileDriver
import com.maybank.mae.internal.pages.base.BasePage
import com.maybank.mae.internal.pages.base.CommonPage
import com.maybank.mae.internal.pages.base.HomePage
import com.maybank.mae.internal.pages.base.Secure2uAuthorisationPage as Secure2uAuthorisationPage
import com.maybank.mae.internal.pages.base.StatusScreenPage as StatusScreenPage

import internal.GlobalVariable

public abstract class MoneyLock extends BasePage{
	
	CommonPage commonPage = BasePage.getPage(CommonPage)
	HomePage homePage = BasePage.getPage(HomePage)
	Secure2uAuthorisationPage secure2uAuthorisationPage = BasePage.getPage(Secure2uAuthorisationPage)
	StatusScreenPage statusScreenPage = BasePage.getPage(StatusScreenPage)
	
	/*
	 * Verify Whether Money Lock Introduction Page Exist
	 */
	String introduce = 'Protect your funds with Money Lock'
	
	boolean isIncreaseMoneyLock() {
		if(MobileDriver.waitForElementPresent(commonPage.lblDynamic(introduce), GlobalVariable.G_timeoutMedium, FailureHandling.OPTIONAL)) {
			return true
		}
		
		return false
	}
	
	/*
	 * Verify UI Current Locked Amount
	 */
	String currentLockedAmount = 'Current locked amount'
	
	boolean getCurrentLockedAmount() {
		if(MobileDriver.waitForElementPresent(commonPage.lblDynamic(currentLockedAmount), GlobalVariable.G_timeoutMedium, FailureHandling.OPTIONAL)) {
			return true
		}
		
		return false
	}
	
	/*
	 * Verify Introduction Screen
	 */	
	List<String> uiIntroductionScreen = [
		'Money Lock',
		'Protect your funds with Money Lock',
		'Safeguard your funds from scams! Lock your money to ensure only you can access it.',
		'Note:',
		'To avoid late fees and payment disruptions, kindly ensure there is enough available balance to cover bank charges, recurring and future payments.'
	]
	
	void verifyIntroductionScreen(FailureHandling flowControl = defaultFailureHandling) {
		'Verify static UI'
		scrollToAndVerifyTextInList(uiIntroductionScreen, commonPage.lblDynamicPath(), GlobalVariable.G_timeoutMedium, flowControl)
		
		'Scroll to the end'
		commonPage.scrollToEnd()
		
		'Tap Set Money Lock or Back if visible'
		['Set Money Lock', 'Back'].each { label ->
			if (MobileDriver.verifyElementExist(commonPage.lblDynamic(label), GlobalVariable.G_timeoutShort, FailureHandling.OPTIONAL)) {
				MobileDriver.tap(commonPage.lblDynamic(label), GlobalVariable.G_timeoutMedium, flowControl)
				return
			}
		}	
	}
	
	/*
	 * Select Account
	 */
	List<String> uiMoneyLockAccountPage = [
		'Money Lock',
		'Select an account to lock your funds.'
		]

	void clickSetMoneyLock(FailureHandling flowControl = defaultFailureHandling) {
		'Verify static UI'
		scrollToAndVerifyTextInList(uiIntroductionScreen, commonPage.lblDynamicPath(), GlobalVariable.G_timeoutMedium, flowControl)

		'Scroll to the end'
		commonPage.scrollToEnd()

		'Tap Set Money Lock'
		if (MobileDriver.verifyElementExist(commonPage.lblDynamic('Set Money Lock'), GlobalVariable.G_timeoutShort, FailureHandling.OPTIONAL)) {
			MobileDriver.tap(commonPage.lblDynamic(label), GlobalVariable.G_timeoutMedium, flowControl)
			return
		}
	}
		
	void selectAccountLock(String accountName, FailureHandling flowControl = defaultFailureHandling) {
		'Verify Static UI'
		verifyElements(uiMoneyLockAccountPage, commonPage.lblDynamicPath(), GlobalVariable.G_timeoutMedium, flowControl)
		
		'Tap Account type'
		MobileDriver.tap(commonPage.lblDynamic(accountName), GlobalVariable.G_timeoutMedium, flowControl)
		
		'Tap next'
		commonPage.tapNextBtn()
	}
	
	/*
	 * Get Money Lock Amount
	 */
	String getMoneyLockAmount(FailureHandling flowControl = defaultFailureHandling) {
		'Wait for Account Balance present'
		if(MobileDriver.waitForElementPresent(commonPage.lblDynamic(homePage.UIRMHide), GlobalVariable.G_timeoutShort, FailureHandling.OPTIONAL)) {
			'Select Eye Button'
			MobileDriver.tap(homePage.btnEye(), GlobalVariable.G_timeoutLong, flowControl)
		}

		MobileDriver.tap(homePage.lblAccountBalance(), GlobalVariable.G_timeoutLong, flowControl)

		String amount = "0"
		if(MobileDriver.waitForElementPresent(homePage.lblMoneyLock(), GlobalVariable.G_timeoutMedium, FailureHandling.OPTIONAL)) {
			amount = MobileDriver.getText(homePage.lblMoneyLock(), GlobalVariable.G_timeoutMedium, FailureHandling.OPTIONAL)
		}

		return amount
	}
	
	/*
	 * Verify Money Lock Page for Increase Money Lock
	 */
	List<String> uiIncreaseMoneyLockPage = [
		'Available Balance',
		'This is the balance that you can use in this account',
		'For example, if your available balance now is RM 10,000 in total and you have decided to lock RM 4,000, then your new available balance will be RM 6,000.'
		]
		
	void verifyIncreaseMoneyLockPage(boolean increaseMoneyLock, boolean fieldValidation = false, FailureHandling flowControl = defaultFailureHandling) {
		'Verify Page'
		if (increaseMoneyLock) {
			verifyElements(['Money Lock', 'Available balance', 'Current locked amount', 'Increase locked amount by', 'What is Money Lock?'], commonPage.lblContainDynamicPath(), GlobalVariable.G_timeoutMedium, flowControl)
		} else {
			verifyElements(['Money Lock', 'Available balance', 'Enter amount to lock'], commonPage.lblContainDynamicPath(), GlobalVariable.G_timeoutMedium, flowControl)
		}
			
		if (fieldValidation) {			
			'Tap Tool Tip'
			MobileDriver.tap(commonPage.imgNoDynamic(4), GlobalVariable.G_timeoutMedium, flowControl)
			
			'Delay'
			MobileDriver.delay(GlobalVariable.G_timeoutMiniShort)
			
			'Take Screenshot'
			MobileDriver.takeScreenshot()
			
			'Verify Static UI'
			scrollToAndVerifyTextInList(uiIncreaseMoneyLockPage, commonPage.lblContainDynamicPath(), GlobalVariable.G_timeoutMedium, flowControl)
			
			'Tap Back'
			MobileDriver.tap(commonPage.imgNoDynamic(1), GlobalVariable.G_timeoutShort, flowControl)
			
			if (increaseMoneyLock) {
				'Tap What is Money Lock?'
				MobileDriver.tap(commonPage.lblDynamic("What is Money Lock?"), GlobalVariable.G_timeoutMedium, flowControl)
				
				'Verify Page'
				verifyIntroductionScreen()
			}		
		}
	}
	
	/*
	 * Verify Money Lock Page for New Money Lock
	 */
	String verifyUI = 'If you have any recurring transactions for this account, kindly ensure you have sufficient available balance.'
		
	void verifyNewMoneyLockPage(boolean fieldValidation = false, FailureHandling flowControl = defaultFailureHandling) {
		'Verify Page'
		verifyElements(['Money Lock', 'Available balance', 'Enter amount to lock'], commonPage.lblContainDynamicPath(), GlobalVariable.G_timeoutMedium, flowControl)
		
		if (fieldValidation) {
			'Tap Tool Tip'
			MobileDriver.tap(commonPage.imgNoDynamic(3), GlobalVariable.G_timeoutShort, flowControl)
			
			'Take Screenshot'
			MobileDriver.takeScreenshot()
			
			'Verify Static UI'
			scrollToAndVerifyTextInList(uiIncreaseMoneyLockPage, commonPage.lblContainDynamicPath(), GlobalVariable.G_timeoutMedium, flowControl)
			
			'Tap Back'
			MobileDriver.tap(commonPage.imgNoDynamic(1), GlobalVariable.G_timeoutShort, flowControl)
			
			'Verify UI'
			MobileDriver.verifyElementExist(commonPage.lblContainDynamic(verifyUI), GlobalVariable.G_timeoutMedium, flowControl)
		}
	}
	
	/*
	 * Verify Minimum And Maximum and Expected Amount
	 */
	String termCondition = "I have read and agree to the Money Lock Terms & Conditions"
	String minErrorMsgIncrease = 'Your increased locked amount must be at least RM 10.00.'
	String minErrorMsgNew = 'Your locked amount must be at least RM 10.00.'
	String maxErrorMsg = 'Your locked amount must be less than your available balance.'
	
	void verifyAmount(boolean fieldValidation = false, boolean increaseMoneyLock = false, String minAmount = '100', String maxAmount = '1000000000', String amount, FailureHandling flowControl = defaultFailureHandling) {
		if (fieldValidation) {
			'Tap Amount and Input Min Amount'
			MobileDriver.tap(commonPage.txtEditNo(), GlobalVariable.G_timeoutMedium, flowControl)
			commonPage.enterNO(minAmount)
	
			'Agree to Terms if present'
			if (MobileDriver.waitForElementPresent(commonPage.grpPrecedingSibling(termCondition), GlobalVariable.G_timeoutShort, FailureHandling.OPTIONAL)) {
				MobileDriver.tap(commonPage.grpPrecedingSibling(termCondition), GlobalVariable.G_timeoutMedium, flowControl)
			}
	
			'Tap Next and verify Min Error Message'
			commonPage.tapNextBtn()
			String expectedMsg = increaseMoneyLock ? minErrorMsgIncrease : minErrorMsgNew
			MobileDriver.verifyElementVisible(commonPage.lblDynamic(expectedMsg), GlobalVariable.G_timeoutMedium, flowControl)
	
			'Clear Min Amount'
			MobileDriver.tap(commonPage.txtEditNo(), GlobalVariable.G_timeoutMedium, flowControl)
			commonPage.removeEnterNo(minAmount.length())
		
			'Input Max Amount and Verify Error Message'
			MobileDriver.tap(commonPage.txtEditNo(), GlobalVariable.G_timeoutMedium, flowControl)
			commonPage.enterNO(maxAmount)
			commonPage.tapNextBtn()
			MobileDriver.verifyElementVisible(commonPage.lblDynamic(maxErrorMsg), GlobalVariable.G_timeoutMedium, flowControl)
			
			'Clear Max Amount'
			MobileDriver.tap(commonPage.txtEditNo(), GlobalVariable.G_timeoutMedium, flowControl)
			commonPage.removeEnterNo(maxAmount.length())	
			
	       'Tap Amount and Input Amount'
	        MobileDriver.tap(commonPage.txtEditNo(), GlobalVariable.G_timeoutMedium, flowControl)
	        commonPage.enterNO(amount)
			
		} else {
	       'Tap Amount and Input Amount'
	        MobileDriver.tap(commonPage.txtEditNo(), GlobalVariable.G_timeoutMedium, flowControl)
	        commonPage.enterNO(amount)

			'Agree to Terms if present'
			if (MobileDriver.waitForElementPresent(commonPage.grpPrecedingSibling(termCondition), GlobalVariable.G_timeoutShort, FailureHandling.OPTIONAL)) {
				MobileDriver.tap(commonPage.grpPrecedingSibling(termCondition), GlobalVariable.G_timeoutMedium, flowControl)
			}
		}
	}
	
	/*
	 * Verify Inline Message
	 */
	String DisplayBalanceMsg = 'After locking this amount, you will have an available balance of RM'
	
	def verifyBalanceinlineMessage(String amount, BigDecimal availableBalanceValue, FailureHandling flowControl = defaultFailureHandling) {
		'Convert amount from cents to currency units (e.g., 1000 -> 10.00)'
		double enteredAmount = (amount.toString().toInteger().toDouble())/100
		
		'Calculate remaining balance'
		BigDecimal fDisplayBalance = availableBalanceValue - enteredAmount
	
		'Format as currency string'
		String finalBalance = String.format('%,.2f', fDisplayBalance)
		System.out.println('The final balance is: ' + finalBalance)
		
		'Construct the message'
		String dynamicText = ((DisplayBalanceMsg + ' ') + finalBalance) + '.'

		'Verify the inline Message'
		MobileDriver.verifyElementVisible(commonPage.lblContainDynamic(dynamicText), GlobalVariable.G_timeoutMedium)
		MobileDriver.takeScreenshot()
		
		return finalBalance
	}

	void inputAmount(String amount, FailureHandling flowControl = defaultFailureHandling) {
		'Tap Amount'
		MobileDriver.tap(commonPage.txtEditNo(), GlobalVariable.G_timeoutMedium, flowControl)

		'Input Amount'
		commonPage.enterNO(amount)

		'Agree to Terms if present'
		if (MobileDriver.waitForElementPresent(commonPage.grpPrecedingSibling(termCondition), GlobalVariable.G_timeoutShort, FailureHandling.OPTIONAL)) {
			MobileDriver.tap(commonPage.grpPrecedingSibling(termCondition), GlobalVariable.G_timeoutMedium, flowControl)
		}
	}
	
	/*
	 * Verify Popup Money Lock
	 */
	String yes = 'Yes'
	
	List<String> uiPopUp = [
		'Money Lock',
		'As a measure to safeguard your locked funds, you can only unlock or decrease it in person at any Maybank Branch or ATM.',
		'Would you like to proceed?',
		'No',
		'Yes'
	]
	
	void verifyPopupMoneyLock(boolean fieldValidation = false, FailureHandling flowControl = defaultFailureHandling) {
		'Delay'
		MobileDriver.delay(GlobalVariable.G_timeoutMiniShort)
		
		if (fieldValidation) {
			'Verify Page'
			verifyElements(uiPopUp, commonPage.lblContainDynamicPath(), GlobalVariable.G_timeoutMedium, flowControl)
		}
		
		'Agree'
		MobileDriver.tap(commonPage.grpPrecedingSibling(yes), GlobalVariable.G_timeoutMedium, flowControl)
	}
	
	/*
	 * Confirmation Page
	 */	
	String confirm = 'Confirm'
	
	List<String> uiConfirmationIncreaseML = [
		'Confirmation',
		'Date',
		'Current locked amount',
		'Total locked amount',
		'Available balance\n(after locking)',
		'Transaction type'
	]
	
	List<String> uiConfirmationNewML = [
		'Confirmation',
		'Date',
		'Available balance\n(after locking)',
		'Transaction type'
	]
	
	void verifyStaticUI(boolean increaseMoneyLock, FailureHandling flowControl = defaultFailureHandling) {
		if (increaseMoneyLock) {
			verifyElements(uiConfirmationIncreaseML, commonPage.lblContainDynamicPath(), GlobalVariable.G_timeoutMedium, flowControl)
		} else {
			verifyElements(uiConfirmationNewML, commonPage.lblContainDynamicPath(), GlobalVariable.G_timeoutMedium, flowControl)
		}
	}
	
	String confirmationPage(boolean increaseMoneyLock, List uiVerificationData, String amount, String editAmount, boolean editNewAmount = false, FailureHandling flowControl = defaultFailureHandling) {
		'Verify static UI before edit amount'
		verifyStaticUI(increaseMoneyLock)
		MobileDriver.takeScreenshot()
		
		'Verify dynamic UI before edit amont'
		verifyElements(uiVerificationData, commonPage.lblDynamicPath(), GlobalVariable.G_timeoutMedium, flowControl)
		
		String formattedEditAmount = ''
		
		if (editNewAmount) {
			'Edit amount'
			editAmountAtConfirmationPage(amount, editAmount)
			
			'Verify edit amount is displayed'
			formattedEditAmount = String.format('%.2f', editAmount.toBigDecimal() / 100)
			String displayAmount = 'RM ' + formattedEditAmount
			MobileDriver.verifyElementVisible(commonPage.lblContainDynamic(displayAmount), GlobalVariable.G_timeoutMedium, flowControl)
			MobileDriver.takeScreenshot()
			
		}
		
		'Tap confirm'
		MobileDriver.tap(commonPage.lblDynamic(confirm), GlobalVariable.G_timeoutMedium, flowControl)
		
		return formattedEditAmount
	}
	
	/*
	 * Edit Amount at Confirmation Page
	 */
	void editAmountAtConfirmationPage(String amount, String editamount, FailureHandling flowControl = defaultFailureHandling) {
			'Tap Amount'
			MobileDriver.tap(commonPage.lblRMNo(1), GlobalVariable.G_timeoutLong, flowControl)
		
			'Clear Amount'
			MobileDriver.tap(commonPage.txtEditNo(), GlobalVariable.G_timeoutMedium, flowControl)
			commonPage.removeEnterNo(amount.length())
			
			'Input Amount'
			commonPage.enterNO(editamount)
			MobileDriver.takeScreenshot()
			
			'Tap Next'
			commonPage.tapNextBtn()
			
			'Pop Up'
			verifyPopupMoneyLock()
	}
	
	/*
	 * Secure2u Page
	 */
	String authorize(String status, List otherVerifyData = [], FailureHandling flowControl = defaultFailureHandling) {
		MobileDriver.waitForElementPresent(commonPage.lblDynamicList([
			secure2uAuthorisationPage.TRANSACTION_AUTHORISATION_CODE,
			secure2uAuthorisationPage.UIRequestOTP,
			secure2uAuthorisationPage.SECURE2U_AUTHORISATION
		]), GlobalVariable.G_timeoutLong, flowControl)

		if(waitForElementPresent(commonPage.lblDynamic(secure2uAuthorisationPage.SECURE2U_AUTHORISATION), GlobalVariable.G_timeoutShort, FailureHandling.OPTIONAL)) {
			'Take Screenshot'
			MobileDriver.takeScreenshot()
			if(!otherVerifyData.isEmpty()) {
				'Verify authorization Other UI'
				scrollToAndVerifyTextInList(otherVerifyData, commonPage.lblContainDynamicPath(), GlobalVariable.G_timeoutMedium, flowControl)
			}
			'Verify Date and Time'
			statusScreenPage.dateAndTime()
			'Perform Approval'
			secure2uAuthorisationPage.performApproveRejectOrExpired(status)
			commonPage.waitLoading()

		}
		else if(MobileDriver.waitForElementPresent(commonPage.lblDynamic(secure2uAuthorisationPage.TRANSACTION_AUTHORISATION_CODE), GlobalVariable.G_timeoutShort, FailureHandling.OPTIONAL)) {
			'Take Screenshot'
			MobileDriver.takeScreenshot()
			return secure2uAuthorisationPage.authorizeWithTacOrOTP(status,AuthorisationType.TAC)
		}
		else if(MobileDriver.waitForElementPresent(commonPage.lblDynamic(secure2uAuthorisationPage.UIRequestOTP), GlobalVariable.G_timeoutShort, FailureHandling.OPTIONAL)) {
			commonPage.waitLoading()
			MobileDriver.delay(GlobalVariable.G_timeoutShort)
			MobileDriver.tap(commonPage.lblDynamic(secure2uAuthorisationPage.UIRequestOTP), GlobalVariable.G_timeoutMedium, flowControl)
			return secure2uAuthorisationPage.authorizeWithTacOrOTP(status, AuthorisationType.OTP)
		}else {
			MobileDriver.takeScreenshot()
			handleError("System Error: The Secure2u or TAC page does not appear", flowControl)
		}
		return status
	}
	
	enum S2UStatusEnum{
		Approve, Reject, Expired
	}

	enum AuthorisationType{
		Secure2U, TAC, OTP
	}
	
	/*
	 * Tap Back Button Until HomePage
	 */
	void tapBackUntilHome(FailureHandling flowControl = defaultFailureHandling) {
		int maxAttempts = 5
		int attempt = 0
	
		while (attempt < maxAttempts) {
			if (MobileDriver.waitForElementPresent(commonPage.lblDynamic(homePage.HOME_UI), GlobalVariable.G_timeoutShort, FailureHandling.OPTIONAL)) {
				'Home UI found — tap Home Menu'
				homePage.tapHomeMenu(flowControl)
				return
			}
	
			'Tap Back button'
			MobileDriver.tap(commonPage.imgNoDynamic(1), GlobalVariable.G_timeoutShort, flowControl)
			MobileDriver.delay(1)
			attempt++
		}
	}

	List<String> uiLearnMoreScreen = [
		'What is Money Lock?',
		'Benefits',
		'How to lock your funds'
	]
	
	void verifyLearnMoreScreen(FailureHandling flowControl = defaultFailureHandling) {
		'Verify static UI'
		scrollToAndVerifyTextInList(uiIntroductionScreen, commonPage.lblDynamicPath(), GlobalVariable.G_timeoutMedium, flowControl)

		'Scroll to the end'
		commonPage.scrollToEnd()

		'Tap Learn more and Verify'
		['Learn More'].each { label ->
			if (MobileDriver.verifyElementExist(commonPage.lblDynamic(label), GlobalVariable.G_timeoutShort, FailureHandling.OPTIONAL)) {
				MobileDriver.tap(commonPage.lblDynamic(label), GlobalVariable.G_timeoutMedium, flowControl)
				MobileDriver.takeScreenshot()
				scrollToAndVerifyTextInList(uiLearnMoreScreen, commonPage.lblDynamicPath(), GlobalVariable.G_timeoutMedium, flowControl)
			}
		}
	}

	String terms_text = "Terms & Conditions"

	void clickTerms(FailureHandling flowControl = defaultFailureHandling) {
		TestObject radio_button = commonPage.grpPrecedingSibling(termCondition)
		MobileDriver.waitForElementPresent(radio_button, GlobalVariable.G_timeoutShort, FailureHandling.STOP_ON_FAILURE)

		int left   = Mobile.getElementLeftPosition(radio_button, 0)
		int top    = Mobile.getElementTopPosition(radio_button, 0)
		int width  = Mobile.getElementWidth(radio_button, 0)
		int height = Mobile.getElementHeight(radio_button, 0)

		int centerY = (top + (height / 2)) + 5
		int startX = left + width
		for (int i = 0; i < 2; i++) {
			int offsetX = startX + 110
			Mobile.tapAtPosition(offsetX, centerY)
			Mobile.delay(0.3)
		}

		MobileDriver.waitForElementPresent(commonPage.lblDynamic(terms_text), GlobalVariable.G_timeoutMedium, FailureHandling.STOP_ON_FAILURE)
		MobileDriver.takeScreenshot()
	}
}
