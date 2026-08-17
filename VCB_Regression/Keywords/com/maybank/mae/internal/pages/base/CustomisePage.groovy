package com.maybank.mae.internal.pages.base

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
import internal.GlobalVariable
import com.maybank.common.Android

public abstract class CustomisePage extends BasePage{
	CommonPage commonPage = getPage(CommonPage)

	abstract TestObject btnBack()
	abstract TestObject btnCustomise()
	abstract TestObject txtCurrentFavorites()
	abstract TestObject txtHoldAndDragDescription()
	abstract TestObject txtManageQucikActions()

	public clickViewAllButton(flowControl) {
		if(MobileDriver.verifyElementExist(commonPage.lblDynamic('View All'), GlobalVariable.G_timeoutMedium, FailureHandling.OPTIONAL)) {
			MobileDriver.tap(commonPage.lblDynamic('View All'), GlobalVariable.G_timeoutLong)
		}
	}

	public verifyApplyButtonExist(flowControl) {
		MobileDriver.verifyElementExist(commonPage.lblDynamic('Apply'), GlobalVariable.G_timeoutMedium, flowControl)
	}

	public verifyTabungButtonExist(flowControl) {
		MobileDriver.verifyElementExist(commonPage.lblDynamic('Tabung'), GlobalVariable.G_timeoutMedium, flowControl)
	}

	public verifyInvestmentButtonExist(flowControl) {
		MobileDriver.verifyElementExist(commonPage.lblDynamic('Goal-Based Investment'), GlobalVariable.G_timeoutMedium, flowControl)
	}

	public verifyPromotionsButtonExist(flowControl) {
		MobileDriver.verifyElementExist(commonPage.lblDynamic('Promotions'), GlobalVariable.G_timeoutMedium, flowControl)
	}

	public verifyZakatButtonExist(flowControl) {
		MobileDriver.verifyElementExist(commonPage.lblDynamic('Zakat'), GlobalVariable.G_timeoutMedium, flowControl)
	}

	public verifySamaSamaLocalButtonExist(flowControl) {
		MobileDriver.verifyElementExist(commonPage.lblDynamic('Sama-Sama Lokal'), GlobalVariable.G_timeoutMedium, flowControl)
	}

	public verifyMoviesLesuireButtonExist(flowControl) {
		MobileDriver.verifyElementExist(commonPage.lblDynamic('Movies & Leisure'), GlobalVariable.G_timeoutMedium, flowControl)
	}

	public verifyPropertyButtonExist(flowControl) {
		MobileDriver.verifyElementExist(commonPage.lblDynamic('Property'), GlobalVariable.G_timeoutMedium, flowControl)
	}

	public verifyJuniorButtonExist(flowControl) {
		MobileDriver.verifyElementExist(commonPage.lblDynamic('Junior'), GlobalVariable.G_timeoutMedium, flowControl)
	}

	public verifySecure2UButtonExist(flowControl) {
		MobileDriver.verifyElementExist(commonPage.lblDynamic('Secure2u'), GlobalVariable.G_timeoutMedium, flowControl)
	}

	public verifyTapTrackWinButtonExist(flowControl) {
		MobileDriver.verifyElementExist(commonPage.lblDynamic('Tap, Track, Win'), GlobalVariable.G_timeoutMedium, flowControl)
	}

	public verifyPNotificationsButtonExist(flowControl) {
		MobileDriver.verifyElementExist(commonPage.lblDynamic('Notifications'), GlobalVariable.G_timeoutMedium, flowControl)
	}

	public SwipeToNextScreen(flowControl) {
		//Android.swipe(0.10, 0.90)
		Android.scrollToNotDirection( 900, 1300, 300, 1300)
	}

	public verifyKillSwitchButtonExist(flowControl) {
		MobileDriver.verifyElementExist(commonPage.lblDynamic('Kill Switch'), GlobalVariable.G_timeoutMedium, flowControl)
	}

	public verifyTravelDealsButtonExist(flowControl) {
		MobileDriver.verifyElementExist(commonPage.lblDynamic('Travel Deals'), GlobalVariable.G_timeoutMedium, flowControl)
	}

	public verifyERLTicketsButtonExist(flowControl) {
		MobileDriver.verifyElementExist(commonPage.lblDynamic('ERL Tickets'), GlobalVariable.G_timeoutMedium, flowControl)
	}

	public verifyFlightTicketsButtonExist(flowControl) {
		MobileDriver.verifyElementExist(commonPage.lblDynamic('Flight Tickets'), GlobalVariable.G_timeoutMedium, flowControl)
	}

	public verifyLoyaltyButtonExist(flowControl) {
		MobileDriver.verifyElementExist(commonPage.lblDynamic('Loyalty'), GlobalVariable.G_timeoutMedium, flowControl)
	}

	public verifySavedButtonExist(flowControl) {
		MobileDriver.verifyElementExist(commonPage.lblDynamic('Saved'), GlobalVariable.G_timeoutMedium, flowControl)
	}

	public verifyReferAFriendButtonExist(flowControl) {
		MobileDriver.verifyElementExist(commonPage.lblDynamic('Refer a Friend'), GlobalVariable.G_timeoutMedium, flowControl)
	}

	public verifyMaybankezyQButtonExist(flowControl) {
		MobileDriver.verifyElementExist(commonPage.lblDynamic('Maybank EzyQ'), GlobalVariable.G_timeoutMedium, flowControl)
	}

	public verifyArticlesButtonExist(flowControl) {
		MobileDriver.verifyElementExist(commonPage.lblDynamic('Articles'), GlobalVariable.G_timeoutMedium, flowControl)
	}

	public verifyFoodButtonExist(flowControl) {
		MobileDriver.verifyElementExist(commonPage.lblDynamic('Food'), GlobalVariable.G_timeoutMedium, flowControl)
	}

	public verifyTravelInsuranceButtonExist(flowControl) {
		MobileDriver.verifyElementExist(commonPage.lblDynamic('Travel Insurance'), GlobalVariable.G_timeoutMedium, flowControl)
	}

	public verifyATMCashOutButtonExist(flowControl) {
		MobileDriver.verifyElementExist(commonPage.lblDynamic('ATM Cash-out'), GlobalVariable.G_timeoutMedium, flowControl)
	}

	public clickBackButton(flowControl) {
		MobileDriver.tap(this.btnBack(), GlobalVariable.G_timeoutMedium, flowControl)
	}

	public clickCustomiseButton(flowControl) {
		MobileDriver.tap(this.btnCustomise(), GlobalVariable.G_timeoutMedium, flowControl)
	}

	public verifyManageQucikActionsText(flowControl) {
		MobileDriver.verifyElementExist(this.txtManageQucikActions(), GlobalVariable.G_timeoutMedium, flowControl)
	}

	public verifyHoldAndDragDescription(flowControl) {
		MobileDriver.verifyElementExist(this.txtHoldAndDragDescription(), GlobalVariable.G_timeoutMedium, flowControl)
	}

	public verifyCurrentFavoritesText(flowControl) {
		MobileDriver.verifyElementExist(this.txtCurrentFavorites(), GlobalVariable.G_timeoutMedium, flowControl)
	}

	public verifyDefaulItemsCustomiseScreen(flowControl) {
		MobileDriver.verifyElementExist(commonPage.lblDynamic('Secure2u'), GlobalVariable.G_timeoutMedium, flowControl)
//		MobileDriver.verifyElementExist(commonPage.lblDynamic('ATM Cash-out'), GlobalVariable.G_timeoutMedium, flowControl)
//		MobileDriver.verifyElementExist(commonPage.lblDynamic('Tabung'), GlobalVariable.G_timeoutMedium, flowControl)
//		MobileDriver.verifyElementExist(commonPage.lblDynamic('Sama-Sama Lokal'), GlobalVariable.G_timeoutMedium, flowControl)
		MobileDriver.takeScreenshot()
	}
}
