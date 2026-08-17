package com.vcb.m2u.internal.pages.base.paytransfer

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
import com.vcb.common.WebUIKeyword
import com.vcb.m2u.internal.pages.base.HTMLTag
import com.vcb.m2u.internal.pages.base.WebBasePage
import com.vcb.m2u.internal.pages.base.WebCommonPage

import internal.GlobalVariable

public class WebTabungHajiPage extends WebBasePage {
	
	WebCommonPage webCommonPage = getPage(WebCommonPage)
	
	final String RELOAD = 'RELOAD'
	final String PAYMENT_INPUT = 'paymentInput'
	final String ADD_NEW_FAVOURITE = 'Add New Favourite'
	final String ADD = 'ADD'
	final String ADD_EDIT_ANOTHER_TRANSFER = 'ADD / EDIT ANOTHER TRANSFER'
	final String REMOVE_FAVOURITE = 'REMOVE FAVOURITE'

	//class
	final String PAY_BTN_TEXT_CLASS = 'pay-btn-text'


	void clickReloadButton(FailureHandling flowControl = defaultFailureHandling) {
		WebUIKeyword.click(webCommonPage.lblContainClassAndNormalizeSpace(webCommonPage.BTN_CLASS, RELOAD), flowControl)
	}

	void selectFavouriteName(String favouriteName, FailureHandling flowControl = defaultFailureHandling) {
		WebUIKeyword.click(webCommonPage.lblIgnoreCase(favouriteName, HTMLTag.empty, 'last()'), flowControl)
	}

	void inputPopupTransfer(Map inputPopupTransferData, Map inputDropdownPopupTransferData = [:], FailureHandling flowControl = defaultFailureHandling) {
		'Wait show Popup'
		webCommonPage.waitModelLoading()
		Mobile.delay(1)
		'input data'
		webCommonPage.inputTextMap(inputPopupTransferData, flowControl)
		'input data'
		webCommonPage.inputDropdownMap(inputDropdownPopupTransferData, flowControl)
		WebUIKeyword.takeScreenshot()
	}

	void selectNewTransfer(FailureHandling flowControl = defaultFailureHandling) {
		'Select New Transfer'
		WebUIKeyword.click(webCommonPage.lblContainClass(PAYMENT_INPUT), flowControl)
		'Wait loading'
		webCommonPage.waitLoading()
		WebUIKeyword.takeScreenshot()
	}

	void clickAddNewFavouriteButton(FailureHandling flowControl = defaultFailureHandling) {
		WebUIKeyword.click(webCommonPage.lblContainClassAndText(webCommonPage.BTN_CLASS, ADD_NEW_FAVOURITE), flowControl)
	}

	void addNewFavourite(Map drpAddNewFavourite, Map inputAddNewFavourite, FailureHandling flowControl = defaultFailureHandling) {
		webCommonPage.waitModelLoading()
		'input data'
		webCommonPage.inputDropdownMap(drpAddNewFavourite, flowControl)

		WebUIKeyword.delay(GlobalVariable.G_timeoutMiniShort)
		webCommonPage.waitLoading()

		webCommonPage.inputTextMap(inputAddNewFavourite, flowControl)

		'Add button'
		WebUIKeyword.click(webCommonPage.lblContainClassAndText(webCommonPage.BTN_CLASS, ADD), flowControl)

		webCommonPage.waitLoading()
	}

	void clickShowFavouriteMenu(String favouriteName, FailureHandling flowControl = defaultFailureHandling) {
		WebUIKeyword.click(webCommonPage.fraSiblingElementsByText(favouriteName, 3, '', '*', true), flowControl)
	}

	void selectMenuCTAName(String ctaName, FailureHandling flowControl = defaultFailureHandling) {
		WebUIKeyword.click(webCommonPage.lblDynamic(ctaName), flowControl)
	}

	void editFavourite(Map editFavouriteData, FailureHandling flowControl = defaultFailureHandling) {
		editFavouriteData.each { key, value ->
			WebUIKeyword.setTextWithClear(webCommonPage.txtForLabel(key), value, false, flowControl)
		}
		webCommonPage.clickSaveButton()
	}

	void clickAddEditAnotherTransferButton(FailureHandling flowControl = defaultFailureHandling) {
		WebUIKeyword.click(webCommonPage.lblContainClassAndText(webCommonPage.BTN_CLASS, ADD_EDIT_ANOTHER_TRANSFER), flowControl)
	}

	void removeFavourite(FailureHandling flowControl = defaultFailureHandling) {
		WebUIKeyword.click(webCommonPage.lblContainClassAndText(PAY_BTN_TEXT_CLASS, REMOVE_FAVOURITE), flowControl)
	}
}
