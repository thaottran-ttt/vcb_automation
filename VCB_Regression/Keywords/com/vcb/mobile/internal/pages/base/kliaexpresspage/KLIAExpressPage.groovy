package com.vcb.mobile.internal.pages.base.kliaexpresspage

import static org.assertj.core.api.InstanceOfAssertFactories.INTEGER

import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testobject.TestObject
import com.vcb.common.MobileDriver
import com.vcb.mobile.internal.pages.base.BasePage
import com.vcb.mobile.internal.pages.base.CommonPage
import com.vcb.mobile.internal.pages.base.ConfirmationPage
import com.vcb.mobile.internal.pages.base.HomePage

import internal.GlobalVariable

public abstract class KLIAExpressPage extends BasePage {
	CommonPage commonPage = getPage(CommonPage)
	HomePage homePage = BasePage.getPage(HomePage)

	abstract TestObject imgDrogDown(String label)
	abstract TestObject txtTravelDate(String label)


	static final String ADULT = "Adult"
	static final String CHILD = "Child"
	static final String CONFIRMATION = "Confirmation"
	static final String BUY_TICKETS = "Buy Tickets"
	static final String KLIA_EXPRESS = "KLIA Ekspres"
	static final String DONE = "Done"
	static final String SUMMARY = "Summary"
	static final String ENTER_DATE = "Enter date"
	static final String FROM = "From"
	static final String TO = "To"
	static final String CONTINUE = "Continue"
	static final String PAY_NOW = "Pay Now"
	static final String PAYMENT_SUCCESSFUL = "Payment successful"
	static final String PAYMENT_FAIL = "Failed"
	static final String RETURN_TRIP = "Return Trip"
	static final String INSUFFICIENT_BALANCE = "insufficient balance"

	ConfirmationPage confirmationPage = getPage(ConfirmationPage)

	void verifyPage(String value, FailureHandling flowControl = defaultFailureHandling) {
		'Wait for KLIA page present'
		waitForElementPresent(commonPage.lblDynamic(value), GlobalVariable.G_timeoutMedium, flowControl)

		'Verify KLIA page present'
		verifyElementExist(commonPage.lblDynamic(value), GlobalVariable.G_timeoutMedium, flowControl)
	}
	void buyTicket(Map ticketData, String accountName = '', FailureHandling flowControl = defaultFailureHandling) {
		'Select Buy Tickets'
		tapBuyTickets()
		'Select From in ticketData'
		chooseDestination(FROM ,ticketData)
		'Verify Page KLIA Express'
		verifyPage(KLIA_EXPRESS)
		'Select To in ticketData'
		chooseDestination(TO ,ticketData)
		'Select Continue'
		MobileDriver.tap(commonPage.btnDynamic(CONTINUE), GlobalVariable.G_timeoutLong, flowControl)
		'Choose Travel Date'
		chooseTravelDate(ticketData)
		'Select Continue'
		MobileDriver.tap(commonPage.btnDynamic(CONTINUE), GlobalVariable.G_timeoutLong, flowControl)
		'Verify Page Summary'
		verifyPage(SUMMARY)
		'Select Continue'
		MobileDriver.tap(commonPage.btnDynamic(CONTINUE), GlobalVariable.G_timeoutLong, flowControl)
		commonPage.waitLoading()
		'Verify Confirmation'
		confirmationPage.verifyConfirmation([], PAY_NOW, accountName, flowControl)
	}
	void tapBuyTickets(FailureHandling flowControl = defaultFailureHandling) {
		'Wait for Buy Tickets button present'
		waitForElementPresent(commonPage.btnDynamic(BUY_TICKETS), GlobalVariable.G_timeoutMedium, flowControl)
		'Verify Buy Tickets button present'
		verifyElementExist(commonPage.btnDynamic(BUY_TICKETS), GlobalVariable.G_timeoutMedium, flowControl)
		'Select Buy Tickets'
		MobileDriver.tapAndLog(commonPage.btnDynamic(BUY_TICKETS), GlobalVariable.G_timeoutMedium, "Tap to Buy Tickets button", flowControl)
	}
	void chooseDestination(String lable, Map ticketData, FailureHandling flowControl = defaultFailureHandling ) {
		'Wait for ${lable} present'
		MobileDriver.tap(this.imgDrogDown(lable), GlobalVariable.G_timeoutLong, FailureHandling.OPTIONAL)

		'If ${lable} is not empty, then scroll to ${lable}, else select default value'
		if(ticketData.get(lable) != "") {
			scrollToValue(ticketData.get(lable), GlobalVariable.G_timeoutLong)
		}
		commonPage.tapButton(DONE)
	}

	void scrollToValue(String value, int timeout = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		'If value is not present, then scroll to value'
		if(!(MobileDriver.waitForElementPresent(commonPage.lblNormalizeSpace(value), timeout, FailureHandling.OPTIONAL))){
			info("Scroll to ${value}")
			Mobile.scrollToText(value, FailureHandling.OPTIONAL)
		}
		info("Tap on ${value}")
		'Select value'
		MobileDriver.tap(commonPage.lblNormalizeSpace(value), timeout, flowControl)
	}
	void chooseTravelDate(Map ticketData, FailureHandling flowControl = defaultFailureHandling) {
		'Wait for Enter date present'
		verifyElementExist(commonPage.lblDynamic(ENTER_DATE), GlobalVariable.G_timeoutLong, flowControl)
		'Select Enter date'
		MobileDriver.tap(commonPage.lblDynamic(ENTER_DATE), GlobalVariable.G_timeoutLong, flowControl)
		'Select Done'
		commonPage.tapButton(DONE)
		'Verify Adult'
		verifyElementExist(this.imgDrogDown(ADULT), GlobalVariable.G_timeoutLong, flowControl)
		int adultNumber = ticketData.get(ADULT)
		'Select Adult'
		for(int i = 1; i < adultNumber; i++ ) {
			MobileDriver.tap(this.imgDrogDown(ADULT), GlobalVariable.G_timeoutLong, flowControl);
		}
		'Verify Child'
		verifyElementExist(this.imgDrogDown(CHILD), GlobalVariable.G_timeoutLong, flowControl)
		'Select Child'
		MobileDriver.tap(this.imgDrogDown(CHILD), GlobalVariable.G_timeoutLong, flowControl)
		'Scroll to Return Trip'
		Mobile.scrollToText(RETURN_TRIP, flowControl)
		'Verify Trip Type in ticketData'
		verifyElementExist(commonPage.lblContainDynamic(ticketData.get("TripType")), GlobalVariable.G_timeoutLong, flowControl)
		'Select Trip Type in ticketData'
		MobileDriver.tap(commonPage.lblContainDynamic(ticketData.get("TripType")), GlobalVariable.G_timeoutLong, flowControl)
	}
	void verifyTransaction(String status, int timeout = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		commonPage.waitLoading()
		'If status is Approve, then verify Payment successful, else verify Failed'
		if(status == 'Approve') {
			'Wait for Payment successful present'
			waitForElementPresent(commonPage.lblDynamic(PAYMENT_SUCCESSFUL), timeout, flowControl)
			'Verify Payment successful present'
			verifyElementExist(commonPage.lblContainDynamic(PAYMENT_SUCCESSFUL), timeout, flowControl)
		}else {
			'Wait for Payment Fail present'
			waitForElementPresent(commonPage.lblContainDynamic(PAYMENT_FAIL), timeout, flowControl)
			'Verify Payment Fail present'
			verifyElementExist(commonPage.lblContainDynamic(PAYMENT_FAIL), timeout, flowControl)
		}
		MobileDriver.tap(commonPage.btnDynamic(DONE), timeout, flowControl)
	}
	void verifyTransactionFailed(int timeout = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		commonPage.waitLoading()
		'Wait for Payment Fail present'
		waitForElementPresent(commonPage.lblContainDynamic(PAYMENT_FAIL), timeout, flowControl)
		'Verify Payment Fail present'
		verifyElementExist(commonPage.lblContainDynamic(PAYMENT_FAIL), timeout, flowControl)
		'Verify Insufficient Balance present'
		verifyElementExist(commonPage.lblContainDynamic(INSUFFICIENT_BALANCE), timeout, flowControl)
		'Select Done'
		MobileDriver.tap(commonPage.btnDynamic(DONE), timeout, flowControl)
	}
}
