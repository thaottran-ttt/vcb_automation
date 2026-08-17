package com.maybank.mae.internal.pages.base.moviesandleisure

import static org.assertj.core.api.InstanceOfAssertFactories.INTEGER

import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testobject.TestObject
import com.maybank.common.MobileDriver
import com.maybank.mae.internal.pages.base.BasePage
import com.maybank.mae.internal.pages.base.CommonPage
import com.maybank.mae.internal.pages.base.ConfirmationPage
import com.maybank.mae.internal.pages.base.HomePage

import internal.GlobalVariable
import com.kms.katalon.core.util.KeywordUtil

public abstract class MoviesAndLeisurePage extends BasePage {
	CommonPage commonPage = getPage(CommonPage)
	HomePage homePage = BasePage.getPage(HomePage)

	// Define abstract TestObject
	abstract TestObject lstItemByIndex(int index)
	abstract TestObject lblSeatIndex(String text, int index)
	abstract TestObject imgIndulge()

	// Define static final String
	static final String TAB_MOVIES = 'Movies'
	static final String NOW_SHOWING_TAB = "Now Showing"
	static final String BUY_NOW_BUTTON = "BUY NOW"
	static final String SCREEN_VELOCITY = "SCREEN"
	static final String PAY_NOW = "Pay Now"

	ConfirmationPage confirmationPage = getPage(ConfirmationPage)

	// Define function
	/** 
	 * Select Wetix tab
	 */
	void selectWetixTab(String label, FailureHandling flowControl = defaultFailureHandling) {
		'Select Wetix tab'
		MobileDriver.tap(commonPage.lblDynamic(label), GlobalVariable.G_timeoutShort, flowControl)
	}

	/**
	 * Select Movies in Now Showing tab
	 */
	List<String> buyTicket(String accountName = '', FailureHandling flowControl = defaultFailureHandling) {
		'Verify exist Now Showing tab'
		commonPage.waitForElementPresent(commonPage.lblDynamic(NOW_SHOWING_TAB), GlobalVariable.G_timeoutMedium, flowControl)

		'Select first showing movie'
		commonPage.tap(this.lstItemByIndex(1), GlobalVariable.G_timeoutMedium, flowControl)

		if (commonPage.waitForElementPresent(commonPage.lblDynamic(BUY_NOW_BUTTON), GlobalVariable.G_timeoutMedium, FailureHandling.OPTIONAL)) {
			commonPage.tap(commonPage.lblDynamic(BUY_NOW_BUTTON), GlobalVariable.G_timeoutMedium, flowControl)
		}

		Mobile.delay(GlobalVariable.G_timeoutMiniShort)

		'Select first available Time'
		Mobile.tapAtPosition(182, 1328)

		'Select a random seat'
		boolean seatSelected = this.selectAvailableSeat()

		if (seatSelected) {
			Mobile.comment("Seat selected: " + seatSelected)
		} else {
			Mobile.takeScreenshot()
			KeywordUtil.markFailed("Failed to select seat")
		}

		MobileDriver.delay(GlobalVariable.G_timeoutShort)

		commonPage.tap(commonPage.lblContainDynamic("CONTINUE"), GlobalVariable.G_timeoutLong, flowControl)
		Mobile.delay(GlobalVariable.G_timeoutMiniShort)

		List<String> ticketData = [
			// cinema
			MobileDriver.getText(commonPage.lblTextNo(3), GlobalVariable.G_timeoutMiniShort),
			// movie
			MobileDriver.getText(commonPage.lblTextNo(4), GlobalVariable.G_timeoutMiniShort),
			// time
			MobileDriver.getText(commonPage.lblTextNo(6), GlobalVariable.G_timeoutMiniShort),
			// location
			MobileDriver.getText(commonPage.lblTextNo(7), GlobalVariable.G_timeoutMiniShort),
			// seat
			MobileDriver.getText(commonPage.lblTextNo(8), GlobalVariable.G_timeoutMiniShort),
		]

		commonPage.tap(commonPage.lblContainDynamic("NEXT"), GlobalVariable.G_timeoutLong, flowControl)
		Mobile.delay(GlobalVariable.G_timeoutMiniShort)

		if (commonPage.waitForElementPresent(commonPage.lblContainDynamic("NEXT"), GlobalVariable.G_timeoutMedium, FailureHandling.OPTIONAL)) {
			commonPage.tap(commonPage.lblContainDynamic("NEXT"), GlobalVariable.G_timeoutLong, flowControl)
		}

		if (commonPage.waitForElementPresent(commonPage.lblContainDynamic("EACH TICKET COMES WITH A"), GlobalVariable.G_timeoutMedium, FailureHandling.OPTIONAL)) {
			commonPage.tap(imgIndulge(), GlobalVariable.G_timeoutLong, flowControl)
		}

		String totalBalance = ""
		if (commonPage.waitForElementPresent(commonPage.lblContainDynamic("CONFIRM"), GlobalVariable.G_timeoutLong, FailureHandling.OPTIONAL)) {
			totalBalance = MobileDriver.getText(commonPage.lblSiblingDynamic("Total", 1), GlobalVariable.G_timeoutMedium, flowControl).replaceAll("\\s+", "")
			Mobile.comment("TOTAL: " + totalBalance)


			if (commonPage.waitForElementPresent(commonPage.lblContainDynamic("CONFIRM"), GlobalVariable.G_timeoutMedium, FailureHandling.OPTIONAL)) {
				commonPage.tap(commonPage.lblContainDynamic("CONFIRM"), GlobalVariable.G_timeoutLong, flowControl)
			}
		}

		'Verify Confirmation'
		confirmationPage.verifyConfirmation([], PAY_NOW, accountName, flowControl)

		ticketData.add(totalBalance)

		return ticketData
	}


	boolean selectAvailableSeat(FailureHandling flowControl = defaultFailureHandling) {

		List<Integer> seatIndexes = [
			4,
			5,
			7,
			8,
			9,
			10,
			11,
			12,
			13,
			14,
			15,
			16,
			17,
			18
		]

		for (int index : seatIndexes) {
			Mobile.comment("Trying seat index: " + index)

			if (!Mobile.waitForElementPresent(this.lblSeatIndex(SCREEN_VELOCITY, index), GlobalVariable.G_timeoutShort, FailureHandling.OPTIONAL)) {
				Mobile.comment("Seat index ${index} not found")
				continue
			}

			Mobile.tap(this.lblSeatIndex(SCREEN_VELOCITY, index), 2, FailureHandling.OPTIONAL)
			Mobile.delay(1)

			if (isContinueEnabled()) {
				Mobile.comment("Seat selected successfully: index " + index)
				return true
			}
		}
		return false
	}

	boolean isContinueEnabled() {
		TestObject continueBtn = commonPage.lblDynamic("CONTINUE")

		if (!Mobile.waitForElementPresent(continueBtn, GlobalVariable.G_timeoutShort, FailureHandling.OPTIONAL)) {
			Mobile.comment("Continue button not found")
			return false
		}

		String enabled = Mobile.getAttribute(
				continueBtn,
				"enabled",
				GlobalVariable.G_timeoutMiniShort,
				FailureHandling.OPTIONAL
				)

		return enabled?.toBoolean()
	}


	/**
	 * Verify ticket information
	 * - Click Done button 
	 * - Close loading 
	 * - Verify Ticket information from list data 
	 * - Click Done button 
	 */

	void verifyTicketInformation(List<String> ticketInfo, FailureHandling flowControl = defaultFailureHandling) {
		Mobile.delay(GlobalVariable.G_timeoutMiniShort)
		if (Mobile.waitForElementPresent(commonPage.lblDynamic("Close"), GlobalVariable.G_timeoutMedium, FailureHandling.OPTIONAL)) {
			Mobile.tap(commonPage.lblDynamic("Close"), GlobalVariable.G_timeoutShort, flowControl)
		}
		scrollToAndVerifyTextInList(ticketInfo, commonPage.lblDynamicPath(), GlobalVariable.G_timeoutMedium, flowControl)

		Mobile.tap(commonPage.lblDynamic("DONE"), GlobalVariable.G_timeoutLong, flowControl)

		Mobile.tap(commonPage.imgNoDynamic(1), GlobalVariable.G_timeoutMedium, flowControl)
	}
}
