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
import com.maybank.common.BaseKeyword
import com.maybank.mae.internal.pages.base.BasePage
import com.maybank.mae.internal.pages.base.HomePage
import internal.GlobalVariable


public class Home extends BaseKeyword {
	HomePage pageObj = BasePage.getPage(HomePage)
	@Keyword
	public void clickHomeButton(FailureHandling flowControl = defaultFailureHandling) {
		pageObj.clickHomeButton(flowControl)
	}

	@Keyword
	public void verifyProfileName(String text, FailureHandling flowControl = defaultFailureHandling) {
		pageObj.verifyProfileName(text, flowControl)
	}
	
	@Keyword
	public void verifyMaskingPhoneNumber(FailureHandling flowControl = defaultFailureHandling) {
		pageObj.verifyMaskingPhoneNumber(flowControl)
	}
	
	@Keyword
	public void verifyReferralCode(String text, FailureHandling flowControl = defaultFailureHandling) {
		pageObj.verifyReferralCode(text, flowControl)
	}
	
	@Keyword
	public void verifyViewAllAccountsButtonVisible(FailureHandling flowControl = defaultFailureHandling) {
		pageObj.verifyViewAllAccountsButton(flowControl)
	}

	public void verifyQRPayScanButtonVisible(FailureHandling flowControl = defaultFailureHandling) {
		pageObj.verifyQRPayScanButton(flowControl)
	}

	public void verifyExpensessButtonVisible(FailureHandling flowControl = defaultFailureHandling) {
		pageObj.verifyApplyButton(flowControl)
	}

	public void verifyApplyButtonVisible(FailureHandling flowControl = defaultFailureHandling) {
		pageObj.verifyExpensesButton(flowControl)
	}

	@Keyword
	public void verifyNotificationButtonVisible(FailureHandling flowControl = defaultFailureHandling) {
		pageObj.verifyNotificationButton(flowControl)
	}

	@Keyword
	public void verifyQuickActionstextVisible(FailureHandling flowControl = defaultFailureHandling){
		pageObj.verifyQuickActionstext(flowControl)
	}

	@Keyword
	public void verifyCustomiseButtonVisible(FailureHandling flowControl = defaultFailureHandling) {
		pageObj.verifyCustomiseButton(flowControl)
	}

	@Keyword
	public void verifySamaSamaLocalVisible(FailureHandling flowControl = defaultFailureHandling) {
		pageObj.verifySamaSamaLocalButton(flowControl)
	}

	@Keyword
	public void verifyTabungVisible(FailureHandling flowControl = defaultFailureHandling) {
		pageObj.verifyTabungButton(flowControl)
	}

	@Keyword
	public void verifyDealsForYouVisible(FailureHandling flowControl = defaultFailureHandling)  {
		pageObj.verifyDealsForYouButton(flowControl)
	}

	@Keyword
	public void verifyTopReadsVisible(FailureHandling flowControl = defaultFailureHandling)  {
		pageObj.verifyTopReadsButton(flowControl)
	}

	@Keyword
	public void verifyManageWidgetsVisible(FailureHandling flowControl = defaultFailureHandling)  {
		pageObj.verifyManageWidgetsButton(flowControl)
	}

	@Keyword
	public void verifySpendingSummaryVisible(FailureHandling flowControl = defaultFailureHandling)  {
		pageObj.verifySpendingSummaryButton(flowControl)
	}

	@Keyword
	public void verifySurveyBannerButtonVisible(FailureHandling flowControl = defaultFailureHandling)  {
		pageObj.verifySurveyBannerButton(flowControl)
	}

	@Keyword
	public void clickLogout(FailureHandling flowControl = defaultFailureHandling) {
		pageObj.clickLogout(flowControl)
	}

	@Keyword
	public void clickEyeButton(FailureHandling flowControl = defaultFailureHandling) {
		pageObj.clickEyeButton(flowControl)
	}

	@Keyword
	public void clickCloseButton(FailureHandling flowControl = defaultFailureHandling) {
		pageObj.clickCloseButton(flowControl)
	}

	@Keyword
	public void hideBalance(FailureHandling flowControl){
		pageObj.hideBalance(flowControl)
	}

	@Keyword
	public void clickViewAllAccountsButton(FailureHandling flowControl){
		pageObj.clickViewAllAccountsButton(flowControl)
	}

	@Keyword
	public void verifytextWelcomeBack(FailureHandling flowControl){
		pageObj.verifytextWelcomeBack(flowControl)
	}

	@Keyword
	public void clickLoginButton(FailureHandling flowControl){
		pageObj.clickLoginButton(flowControl)
	}

	@Keyword
	public void verifyAccountBalanceNotDisplayed(FailureHandling flowControl){
		pageObj.verifyAccountBalanceNotDisplayed(flowControl)
	}

	@Keyword
	public void verifyTotalSpendingVisible(FailureHandling flowControl){
		pageObj.verifyTotalSpendingVisible(flowControl)
	}

	@Keyword
	public void verifySpendingSummaryIsNotDisplayed(FailureHandling flowControl){
		pageObj.verifySpendingSummaryIsNotDisplayed(flowControl)
	}

	@Keyword
	public void verifyTextExpensesVisible(FailureHandling flowControl){
		pageObj.verifyTextExpensesVisible(flowControl)
	}

	@Keyword
	public void verifyCurrentMonthVisible(FailureHandling flowControl){
		pageObj.verifyCurrentMonthVisible(flowControl)
	}

	@Keyword
	public void verifyTextSpentSoFar(FailureHandling flowControl){
		pageObj.verifyTextSpentSoFar(flowControl)
	}

	@Keyword
	public void verifySpendingSummaryIsDisplayed(FailureHandling flowControl){
		pageObj.verifySpendingSummaryIsDisplayed(flowControl)
	}

	@Keyword
	public void verifyTotalMonthlySpendingVisible(FailureHandling flowControl){
		pageObj.verifyTotalMonthlySpendingVisible(flowControl)
	}

	@Keyword
	public void tapTopReadsLink(FailureHandling flowControl){
		pageObj.tapTopReadsLink(flowControl)
	}

	@Keyword
	public void tapHeartSymbolButton(FailureHandling flowControl){
		pageObj.tapHeartSymbolButton(flowControl)
	}
	@Keyword
	public void readLikeCount(FailureHandling flowControl){
		pageObj.readLikeCount(flowControl)
	}

	@Keyword
	public void verifyLikeCountIsIncreamentedbyOne(FailureHandling flowControl){
		pageObj.verifyLikeCountIsIncreamentedbyOne(flowControl)
	}

	@Keyword
	public void verifyLikeCountIsIncreamentedbyOneInHomePageTopReads(flowControl){
		pageObj.verifyLikeCountIsIncreamentedbyOneInHomePageTopReads(flowControl)
	}

	@Keyword
	public void clickAccountsButton(flowControl) {
		pageObj.clickAccountsButton(flowControl)
	}

	@Keyword
	public void clickSettingsButton(flowControl) {
		pageObj.clickSettingsButton(flowControl)
	}

}
