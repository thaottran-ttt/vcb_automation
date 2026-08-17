package com.maybank.m2u.internal.pages.base.paytransfer

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import java.util.concurrent.ConcurrentHashMap.KeySetView

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
import com.maybank.m2u.internal.pages.base.WebBasePage
import internal.GlobalVariable

import com.maybank.m2u.internal.pages.base.WebCommonPage as WCP
import com.maybank.m2u.internal.constants.WebCommonConstants as WCC
import org.openqa.selenium.Keys as Keys


public class WebPayPageJomPAY extends WebPayPage {

	/* Constants */
	protected final DATE_PICKER_FORMAT = 'd MMMM,  yyyy'
	protected final BILLER_CODE_PLACEHOLDER = 'Enter Biller Code'
	protected final BILLER_CODE_INPUT_TOGGLE_TEXT = 'Press Enter'
	protected final JOMPAY_ICON_CLASS = 'jompayLogo'
	protected final JOMPAY_ICON_INPUT_SRC = 'grid.svg'
	protected final TOAST_MESSAGE_MESSAGE_EMPTY_BILLERCODE = 'Biller Code should not be empty'
	protected final BILLER_CODE_INPUT_TOGGLE_TEXT_CLASS = 'PayFromToContainer---toggleplaceholder'
	protected final TOAST_MESSAGE_INVALID_REF1 = 'Invalid Ref 1. Please re-enter a valid Ref 1.'
	protected final TOAST_MESSAGE_REF2_REQUIRED = 'Ref 2 is required. Please enter Ref 2 (second Reference).'
	protected final TOAST_MESSAGE_AMOUNT_LIMIT = 'Pay Amount should not be greater than 999999.99'
	protected final LABEL_REF1 = 'Ref-1'
	protected final LABEL_REF2 = 'Ref-2'
	protected final LABEL_PAY_AMOUNT = 'Pay Amount'

	protected final EFFECIVE_DATE_LABEL = 'Effective Date'
	protected final DAY_DATE_PICKER_CSS_CHECK = 'color'
	protected final DAY_DATE_PICKER_COLOR = 'rgba(255, 200, 61, 1)'
	protected final PAY_TO_MODAL_PAY_BUTTON_CLASS = 'PaymentModal---pay-btn---'
	protected final RADIO_BUTTON_CLASS = 'PaymentModal---radioButton'
	protected final PAYMENT_MODAL_TERMS_CLASS = 'PaymentModal---terms'
	protected final PAY_TO_MODAL_TITLE_CLASS = 'PaymentModal---title---'
	protected final PAY_TO_MODAL_CLOSE_CLASS = 'PaymentModal---close'
	protected final PAY_TO_MODAL_TERM_BTN_SRC = 'radio_button.svg'
	protected final PAY_TO_MODAL_TERM_SELECTED_BTN_SRC = 'radio_selected.svg'

	protected final ADD_NEW_FAVOURITE_BTN_CSS_CHECK = 'background-color'
	protected final ADD_NEW_FAVOURITE_BTN_BACKGROUND_COLOR = 'rgba(56, 154, 110, 1)'
	protected final FAVOURITE_MODAL_TITLE_CLASS = 'FavoriteModal---title'
	protected final FAVOURITE_MODAL_CLOSE_CLASS = 'FavoriteModal---close'
	protected final FAVOURITE_MODAL_ADD_CLASS = 'FavoriteModal---pay-btn'
	protected final LABEL_NICKNAME = 'Nickname'
	protected final LABEL_BILLER_CODE = 'Biller Code'
	protected final TOAST_MESSAGE_NICKNAME_FORMAT = "Nickname can't include numbers and special characters except space, left single quote and - / @ &"
	protected final TOAST_MESSAGE_INVALID_BILLERCODE = 'Invalid Biller Code. Please re-enter a valid Biller Code.'

	protected btnAddNewFavourite = WCP.lblContainClassAndContainText(WCC.BTN_CLASS, ADD_NEW_FAVOURITE)

	protected btnFavouriteModalAdd = WCP.lblContainClass(FAVOURITE_MODAL_ADD_CLASS)
	protected lblFavouriteModalTitle = WCP.lblContainClassAndContainText(FAVOURITE_MODAL_TITLE_CLASS, ADD_NEW_FAVOURITE)
	protected btnFavouriteModalClose = WCP.lblContainClass(FAVOURITE_MODAL_CLOSE_CLASS)

	protected lblPayToModalTitle = WCP.lblContainClass(PAY_TO_MODAL_TITLE_CLASS)
	protected btnPayToModalClose = WCP.lblContainClass(PAY_TO_MODAL_CLOSE_CLASS)
	protected btnPayToModalPay = WCP.lblContainClass(PAY_TO_MODAL_PAY_BUTTON_CLASS)

	protected lblBillerCodeInputToggle = WCP.lblContainClass(BILLER_CODE_INPUT_TOGGLE_TEXT_CLASS)

	protected imgJomPAYIcon = WCP.lblContainClass(JOMPAY_ICON_CLASS)
	protected imgPayToModalTermEmpty = WCP.imgSrc(PAY_TO_MODAL_TERM_BTN_SRC)
	protected imgPayToModalTermSelected = WCP.imgSrc(PAY_TO_MODAL_TERM_SELECTED_BTN_SRC)
	protected imgJomPAYIconInput = WCP.imgSrc(JOMPAY_ICON_INPUT_SRC)
	
	protected lblNextDatev2 = findTestObject('Object Repository/Website/M2U/Browsers/Common/DatePicker/lblNextDatev2')

	protected void enterBillerCode(code) {
		WebUI.setText(WCP.inputPlaceholder(BILLER_CODE_PLACEHOLDER), code)
		WebUI.sendKeys(WCP.inputPlaceholder(BILLER_CODE_PLACEHOLDER), Keys.chord(Keys.ENTER))
	}

	protected void enterPayToData(Map inputPayToData, boolean recurring = false, boolean setFutureData = false, FailureHandling flowControl = defaultFailureHandling) {
		inputPayToData.each { k, v ->
			WCP.inputTextForLabel(k, v)
		}
		if (recurring) {
			setRecurring(SET_RECURRING)
		}
		if (setFutureData) {
			WebUI.click(webCommonPage.fraSiblingElementsByText(EFFECTIVE_DATE, 1), flowControl)
			WebUI.delay(GlobalVariable.G_timeoutMiniShort)
			WebUI.click(lblNextDatev2)
		}
	}

	protected void enterRowPayToData(String label, String value) {
		WCP.inputTextForLabel(label, value)
	}

	protected void requestPayTo(Map inputPayToData) {
		enterPayToData(inputPayToData)
		WebUI.click(WCP.imgSrc(PAY_TO_MODAL_TERM_BTN_SRC))
	}

	protected String parsePayToModalTitle(String billerCode) {
		return 'Pay To JomPAY (' + billerCode + ')'
	}

	protected String parseEmtpyFieldToastMessage(String field) {
		return field + ' should not be empty'
	}
	
	protected void addNewJompayFavourite(Map inputFavouriteData, FailureHandling flowControl = defaultFailureHandling) {
		webCommonPage.inputTextMap(inputFavouriteData, flowControl)
		click(webCommonPage.btnChildLabel('ADD'))
		webCommonPage.waitLoading()
	}
}
