package com.vcb.mobile.pages

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
import com.vcb.mobile.internal.pages.base.BasePage
import com.vcb.mobile.internal.pages.base.settingpage.SettingPage


import internal.GlobalVariable

public class Setting {

	SettingPage pageObj = BasePage.getPage(SettingPage)


	@Keyword
	public void verifyProfilesExist(flowControl) {
		pageObj.verifyProfilesExist(flowControl)
	}

	@Keyword
	public void verifyVcb2uExist(flowControl) {
		pageObj.verifyVcb2uExist(flowControl)
	}

	@Keyword
	public void verifySecirityExist(flowControl) {
		pageObj.verifySecirityExist(flowControl)
	}

	@Keyword
	public void verifyNotificationsExist(flowControl) {
		pageObj.verifyNotificationsExist(flowControl)
	}

	@Keyword
	public void verifySupportExist(flowControl) {
		pageObj.verifySupportExist(flowControl)
	}

	@Keyword
	public void verifyDefaultViewExist(flowControl) {
		pageObj.verifyDefaultViewExist(flowControl)
	}

	@Keyword
	public void verifyAboutSettingsExist(flowControl) {
		pageObj.verifyAboutSettingsExist(flowControl)
	}

	@Keyword
	public void clickVcb2uExist(flowControl) {
		pageObj.clickVcb2uExist(flowControl)
	}

	@Keyword
	public void tapScanAndPay(flowControl) {
		pageObj.tapScanAndPay(flowControl)
	}

	@Keyword
	public void tapPaynTransfer(flowControl) {
		pageObj.tapPaynTransfer(flowControl)
	}

	@Keyword
	public void verifyDailyLimitText(flowControl) {
		pageObj.verifyDailyLimitText(flowControl)
	}

	@Keyword
	public void verifyCumulativeLimitText(flowControl) {
		pageObj.verifyCumulativeLimitText(flowControl)
	}

	@Keyword
	public void verifyTermsAndConditionsText(flowControl) {
		pageObj.verifyTermsAndConditionsText(flowControl)
	}

	@Keyword
	public void tapConfirmButton(flowControl) {
		pageObj.tapConfirmButton(flowControl)
	}

	@Keyword
	public void enterPassword(password, flowControl) {
		pageObj.enterPassword(password, flowControl)
	}

	@Keyword
	public void clickProfileButton(flowControl) {
		pageObj.clickProfileButton(flowControl)
	}

	@Keyword
	public void verifyTextUpdateProfile(flowControl) {
		pageObj.verifyTextUpdateProfile(flowControl)
	}

	@Keyword
	public void verifyPhoneNumber(flowControl) {
		pageObj.verifyPhoneNumber(flowControl)
	}

	@Keyword
	public void verifyProfilePictureUpdatedSuccessfully(flowControl) {
		pageObj.verifyProfilePictureUpdatedSuccessfully(flowControl)
	}

	@Keyword
	public void verifyPhoneNumberUpdatedSuccessfully(flowControl) {
		pageObj.verifyPhoneNumberUpdatedSuccessfully(flowControl)
	}

	@Keyword
	public void verifyEmailIdUpdatedSuccessfully(flowControl) {
		pageObj.verifyEmailIdUpdatedSuccessfully(flowControl)
	}

	@Keyword
	public void clickDuitNowButton(flowControl) {
		pageObj.clickDuitNowButton(flowControl)
	}

	@Keyword
	public void verifyNRICNumbertext(flowControl) {
		pageObj.verifyNRICNumbertext(flowControl)
	}

	@Keyword
	public void switchAccountForNricNumber(flowControl) {
		pageObj.switchAccountForNricNumber(flowControl)
	}

	@Keyword
	public void secure2uforDuitNowSettings(status, flowControl) {
		pageObj.secure2uforDuitNowSettings(status, flowControl)
	}

	@Keyword
	public void tapSecure2u(flowControl) {
		pageObj.tapSecure2u(flowControl)
	}

	@Keyword
	public void disableSecure2U(flowControl) {
		pageObj.disableSecure2U(flowControl)
	}
}

