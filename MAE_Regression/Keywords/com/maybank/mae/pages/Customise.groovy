package com.maybank.mae.pages

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
import com.maybank.mae.internal.pages.base.BasePage
import com.maybank.mae.internal.pages.base.CustomisePage

import internal.GlobalVariable

public class Customise {

	CustomisePage pajeObj = BasePage.getPage(CustomisePage)

	@Keyword
	public void clickViewAllButton(flowControl) {
		pajeObj.clickViewAllButton(flowControl)
	}

	@Keyword
	public void verifyApplyButtonExist(flowControl) {
		pajeObj.verifyApplyButtonExist(flowControl)
	}

	@Keyword
	public void verifyTabungButtonExist(flowControl) {
		pajeObj.verifyTabungButtonExist(flowControl)
	}

	@Keyword
	public void verifyInvestmentButtonExist(flowControl) {
		pajeObj.verifyInvestmentButtonExist(flowControl)
	}

	@Keyword
	public void verifyPromotionsButtonExist(flowControl) {
		pajeObj.verifyPromotionsButtonExist(flowControl)
	}

	@Keyword
	public void verifyZakatButtonExist(flowControl) {
		pajeObj.verifyZakatButtonExist(flowControl)
	}

	@Keyword
	public void verifySamaSamaLocalButtonExist(flowControl) {
		pajeObj.verifySamaSamaLocalButtonExist(flowControl)
	}

	@Keyword
	public void verifyMoviesLesuireButtonExist(flowControl) {
		pajeObj.verifyMoviesLesuireButtonExist(flowControl)
	}

	@Keyword
	public void verifyPropertyButtonExist(flowControl) {
		pajeObj.verifyPropertyButtonExist(flowControl)
	}

	@Keyword
	public void verifyJuniorButtonExist(flowControl) {
		pajeObj.verifyJuniorButtonExist(flowControl)
	}

	@Keyword
	public void verifySecure2UButtonExist(flowControl) {
		pajeObj.verifySecure2UButtonExist(flowControl)
	}

	@Keyword
	public void verifyTapTrackWinButtonExist(flowControl) {
		pajeObj.verifyTapTrackWinButtonExist(flowControl)
	}

	@Keyword
	public void verifyPNotificationsButtonExist(flowControl) {
		pajeObj.verifyPNotificationsButtonExist(flowControl)
	}

	@Keyword
	public void SwipeToNextScreen(flowControl) {
		pajeObj.SwipeToNextScreen(flowControl)
	}

	@Keyword
	public void verifyKillSwitchButtonExist(flowControl) {
		pajeObj.verifyKillSwitchButtonExist(flowControl)
	}

	@Keyword
	public void verifyTravelDealsButtonExist(flowControl) {
		pajeObj.verifyTravelDealsButtonExist(flowControl)
	}

	@Keyword
	public void verifyERLTicketsButtonExist(flowControl) {
		pajeObj.verifyERLTicketsButtonExist(flowControl)
	}

	@Keyword
	public void verifyFlightTicketsButtonExist(flowControl) {
		pajeObj.verifyFlightTicketsButtonExist(flowControl)
	}

	@Keyword
	public void verifyLoyaltyButtonExist(flowControl) {
		pajeObj.verifyLoyaltyButtonExist(flowControl)
	}

	@Keyword
	public void verifySavedButtonExist(flowControl) {
		pajeObj.verifySavedButtonExist(flowControl)
	}

	@Keyword
	public void verifyReferAFriendButtonExist(flowControl) {
		pajeObj.verifyReferAFriendButtonExist(flowControl)
	}

	@Keyword
	public void verifyMaybankezyQButtonExist(flowControl) {
		pajeObj.verifyMaybankezyQButtonExist(flowControl)
	}

	@Keyword
	public void verifyArticlesButtonExist(flowControl) {
		pajeObj.verifyArticlesButtonExist(flowControl)
	}

	@Keyword
	public void verifyFoodButtonExist(flowControl) {
		pajeObj.verifyFoodButtonExist(flowControl)
	}

	@Keyword
	public void verifyTravelInsuranceButtonExist(flowControl) {
		pajeObj.verifyTravelInsuranceButtonExist(flowControl)
	}

	@Keyword
	public void verifyATMCashOutButtonExist(flowControl) {
		pajeObj.verifyATMCashOutButtonExist(flowControl)
	}

	@Keyword
	public void clickBackButton(flowControl) {
		pajeObj.clickBackButton(flowControl)
	}

	@Keyword
	public void verifyDefaulItemsCustomiseScreen(flowControl) {
		pajeObj.verifyDefaulItemsCustomiseScreen(flowControl)
	}
	
	@Keyword
	public void clickCustomiseButton(flowControl) {
		pajeObj.clickCustomiseButton(flowControl)
	}
	
	@Keyword
	public void verifyManageQucikActionsText(flowControl) {
		pajeObj.verifyManageQucikActionsText(flowControl)
	}
	
	@Keyword
	public void verifyHoldAndDragDescription(flowControl) {
		pajeObj.verifyHoldAndDragDescription(flowControl)
	}
	
	@Keyword
	public void verifyCurrentFavoritesText(flowControl) {
		pajeObj.verifyCurrentFavoritesText(flowControl)
	}
}
