package com.vcb.m2u.internal.pages.base.paytransfer

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import org.openqa.selenium.Keys

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
import com.vcb.m2u.internal.pages.base.WebBasePage
import com.vcb.m2u.internal.pages.base.WebCommonPage
import com.vcb.mobile.internal.pages.base.BasePage

import internal.GlobalVariable

public abstract class WebReloadPage extends WebBasePage {

	WebCommonPage webCommonPage = WebCommonPage.getPage(WebCommonPage)
	final String RELOAD = 'RELOAD'
	final String PAYMENT_INPUT = 'paymentInput'
	final String ADD_NEW_FAVOURITE = 'Add New Favourite'
	final String ADD_NEW_FAVOURITE_POPUP = 'ADD NEW FAVOURITE'
	final String ADD_EDIT_ANOTHER_TRANSFER = 'ADD / EDIT ANOTHER TRANSFER'
	final String REMOVE_FAVOURITE = 'REMOVE FAVOURITE'

	//class
	final String PAY_BTN_TEXT_CLASS = 'pay-btn-text'


	void clickReloadButton(FailureHandling flowControl = defaultFailureHandling) {
		WebUIKeyword.click(webCommonPage.lblContainClassAndNormalizeSpace(webCommonPage.BTN_CLASS, RELOAD), flowControl)
	}

	void selectFavouriteName(String favouriteName, FailureHandling flowControl = defaultFailureHandling) {
		WebUIKeyword.click(webCommonPage.lblIgnoreCase(favouriteName), flowControl)
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

	void selectOtherBank(String otherBank, FailureHandling flowControl = defaultFailureHandling) {
		'Select New Transfer'
		WebUIKeyword.click(webCommonPage.lblContainClass(PAYMENT_INPUT), flowControl)
		WebUIKeyword.delay(GlobalVariable.G_timeoutMiniShort)
		'Select Other Bank'
		WebUIKeyword.click(webCommonPage.lblContainParentClassAndText('dropdown-menu', otherBank, 'last()'), flowControl)
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
		WebUIKeyword.click(webCommonPage.lblContainClassAndText(webCommonPage.BTN_CLASS, ADD_NEW_FAVOURITE_POPUP), flowControl)

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
	void selectCountry(String country, String otherBank, FailureHandling flowControl = defaultFailureHandling) {
		WebUIKeyword.click(webCommonPage.lblContainClass(PAYMENT_INPUT, 1), flowControl)
		webCommonPage.waitLoading()
		WebUIKeyword.click(webCommonPage.lblContainParentClassAndText('dropdown-menu', country, 'last()'), flowControl)
		webCommonPage.waitLoading()
		
		WebUIKeyword.click(webCommonPage.lblContainClass(PAYMENT_INPUT, 2), flowControl)
		webCommonPage.waitLoading()
		WebUIKeyword.click(webCommonPage.lblContainParentClassAndText('dropdown-menu', otherBank, 'last()'), flowControl)
		webCommonPage.waitLoading()
	}
	void inputPopupInternationalTransfer(Map inputPopupTransferData, Map inputDropdownPopupTransferData = [:], FailureHandling flowControl = defaultFailureHandling) {
		'Wait show Popup'
		webCommonPage.waitModelLoading()
		WebUIKeyword.delay(GlobalVariable.G_timeoutMiniShort)
		'input data'
		webCommonPage.inputTextMapNo(inputPopupTransferData, 2, flowControl)
		'input data'
		webCommonPage.inputDropdownMap(inputDropdownPopupTransferData, flowControl)
		WebUIKeyword.takeScreenshot()
	}
	
	void addNewInternationalFavourite(Map drpAddNewFavourite, Map inputAddNewFavourite, FailureHandling flowControl = defaultFailureHandling) {
		webCommonPage.waitModelLoading()
		'input data'
		webCommonPage.inputDropdownMap(drpAddNewFavourite, flowControl)
		
		WebUIKeyword.delay(GlobalVariable.G_timeoutShort)
		webCommonPage.waitLoading()

		WebUIKeyword.setText(webCommonPage.txtForLabel('Nickname', 1), inputAddNewFavourite.remove('Nickname'), flowControl)
		
		'input data'
		webCommonPage.inputTextMapNo(inputAddNewFavourite, 2, flowControl)
		
		'Add button'
		WebUIKeyword.click(webCommonPage.lblContainClassAndText(webCommonPage.BTN_CLASS, ADD_NEW_FAVOURITE_POPUP), flowControl)

		webCommonPage.waitLoading()
	}
}
