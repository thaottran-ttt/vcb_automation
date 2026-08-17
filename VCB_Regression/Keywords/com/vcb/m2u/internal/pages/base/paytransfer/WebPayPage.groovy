package com.vcb.m2u.internal.pages.base.paytransfer

import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.webui.keyword.builtin.WaitForElementClickableKeyword
import com.vcb.common.WebUIKeyword
import com.vcb.m2u.internal.pages.base.HTMLTag
import com.vcb.m2u.internal.pages.base.WebBasePage
import com.vcb.m2u.internal.pages.base.WebCommonPage
import com.vcb.utils.StringUtils
import com.vcb.m2u.internal.pages.base.ElementVerificationType



import internal.GlobalVariable

public class WebPayPage extends WebBasePage{

	WebCommonPage webCommonPage = getPage(WebCommonPage)
	final String PAYMENT_INPUT = 'paymentInput'
	final String SET_RECURRING = 'Set Recurring'
	final String EFFECTIVE_DATE = 'Effective Date'

	final String TRANSFER_AMOUNT = 'Transfer Amount'
	final String TRANSFER = 'TRANSFER'
	final String ADD_EDIT_ANOTHER_TRANSFER = 'ADD / EDIT ANOTHER PAYMENT'
	final String DECIMAL_AMOUNT = "1.11"
	final String SINGLE_AMOUNT = "1"
	final String SINGLE_DECIMAL_AMOUNT = "1.00"
	final String FOUR_DIGIT_AMOUNT = "1000"
	final String FOUR_DIGIT_DECIMAL_AMOUNT = "1,000.00"
	final String BLANK_AMOUNT_MSG = 'Please do not leave field blank'
	final String ADD_NEW_FAVOURITE = 'Add New Favourite'
	final String ADD = 'ADD'
	final String PAY = 'PAY'
	final String REMOVE_FAVOURITE = 'REMOVE FAVOURITE'

	//Class
	final String RECURRING_CLASS = 'Toggle---container---3i-fe'
	final String EDIT_PENCIL_ICON_CLASS = 'Tooltip---labelIcon'
	final String RADIO_TERM_ClASS = 'TransferModal---radioButton'
	final String OWN_ACC_RECIPIENT_CLASS = 'OwnAccRecipient'
	final String TRANSFER_MODAL_CLASS = 'TransferModal---pay-btn-text'
	final String MULTI_TRANSACTION_STICKY_CLASS = 'MultiTransactionSticky'
	final String DATEPICKER_CONTAINER_CLASS = 'Datepicker---container'
	
	

	//Xpath
	final String IMAGE_XPATH = '*//img'

	void clickTransferButton(FailureHandling flowControl = defaultFailureHandling) {
		WebUIKeyword.click(webCommonPage.lblContainClassAndNormalizeSpace(webCommonPage.BTN_CLASS, TRANSFER), flowControl)
	}
	void clickPayButton(FailureHandling flowControl = defaultFailureHandling) {
		webCommonPage.click(webCommonPage.lblNormalizeSpace(PAY, 1, HTMLTag.span), flowControl)
	}

	void clickAddEditAnotherTransferButton(FailureHandling flowControl = defaultFailureHandling) {
		WebUIKeyword.click(webCommonPage.lblContainClassAndText(webCommonPage.BTN_CLASS, ADD_EDIT_ANOTHER_TRANSFER), flowControl)
	}

	void clickAddNewFavouriteButton(FailureHandling flowControl = defaultFailureHandling) {
		WebUIKeyword.click(webCommonPage.lblContainClassAndText(webCommonPage.BTN_CLASS, ADD_NEW_FAVOURITE), flowControl)
	}

	void clickMultiTransferButton(FailureHandling flowControl = defaultFailureHandling) {
		WebUIKeyword.click(webCommonPage.lblContainClassAndText(MULTI_TRANSACTION_STICKY_CLASS, TRANSFER), flowControl)
	}

	void clickEditIcon(FailureHandling flowControl = defaultFailureHandling) {
		WebUIKeyword.click(webCommonPage.lblContainClass(EDIT_PENCIL_ICON_CLASS), flowControl)
	}

	void selectTransfer(Map fromToData) {
		webCommonPage.inputDropdownMap(fromToData)
	}

	void selectFavouriteName(String favouriteName, FailureHandling flowControl = defaultFailureHandling) {
		scrollToTestObject(webCommonPage.lblDynamic(favouriteName))
		scrollToTestObject(webCommonPage.lblDynamic(favouriteName))
		WebUIKeyword.clickWithoutScrollW3C(webCommonPage.lblDynamic(favouriteName), flowControl)
	}

	void selectTextDynamic(String favouriteName, FailureHandling flowControl = defaultFailureHandling) {
		WebUIKeyword.click(webCommonPage.lblContainClassAndContainText(OWN_ACC_RECIPIENT_CLASS, favouriteName), flowControl)
	}

	void selectOtherTransfer(Map fromToData, FailureHandling flowControl = defaultFailureHandling) {
		selectTransfer(fromToData)
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
	void selectOtherBank(String otherBank, String filter = '', FailureHandling flowControl = defaultFailureHandling) {
		'Select New Transfer'
		WebUIKeyword.click(webCommonPage.lblContainClass(PAYMENT_INPUT), flowControl)
		WebUIKeyword.delay(GlobalVariable.G_timeoutMiniShort)
		'Select Other Bank'
		if (!filter.blank) {
			WebUIKeyword.setText(webCommonPage.lblContainClass(PAYMENT_INPUT),filter, flowControl)
			otherBank = filter
		}
		WebUIKeyword.waitForElementClickable(webCommonPage.lblContainParentClassAndText('dropdown-menu', otherBank, 'last()'), GlobalVariable.G_timeoutMedium)
		WebUIKeyword.click(webCommonPage.lblContainParentClassAndText('dropdown-menu', otherBank, 'last()'), flowControl)
	}

	void payToFav(String otherBank, String filter = '', FailureHandling flowControl = defaultFailureHandling) {
		'Select New Transfer'
		WebUIKeyword.click(webCommonPage.lblContainClass(PAYMENT_INPUT), flowControl)
		WebUIKeyword.delay(GlobalVariable.G_timeoutMiniShort)
		'Select Other Bank'
		if (!filter.blank) {
			WebUIKeyword.setText(webCommonPage.lblContainClass(PAYMENT_INPUT),filter, flowControl)
		}
		WebUIKeyword.waitForElementClickable(webCommonPage.lblContainParentClassAndText('dropdown-menu', filter, 'last()'), GlobalVariable.G_timeoutMedium)
		WebUIKeyword.click(webCommonPage.lblContainParentClassAndText('dropdown-menu', filter, 'last()'), flowControl)
	}

	void verifyAmount(FailureHandling flowControl = defaultFailureHandling) {
		'Verify Empty'
		WebUI.clearText(webCommonPage.txtForLabel(TRANSFER_AMOUNT), flowControl)
		this.clickTransferButton(flowControl)
		WebUIKeyword.delay(1)
		WebUIKeyword.verifyElementVisible(webCommonPage.lblDynamic(BLANK_AMOUNT_MSG), flowControl)

		'Verify Single'
		webCommonPage.inputTextForLabel(TRANSFER_AMOUNT, SINGLE_AMOUNT, flowControl)
		this.clickTransferButton(flowControl)
		WebUIKeyword.delay(1)
		WebUIKeyword.verifyElementVisible(webCommonPage.lblDynamic(SINGLE_DECIMAL_AMOUNT), flowControl)
		WebUIKeyword.delay(1)

		'Verify decimal Amount'
		this.clickEditIcon()
		WebUIKeyword.delay(1)
		webCommonPage.inputTextForLabel(TRANSFER_AMOUNT, DECIMAL_AMOUNT, flowControl)
		this.clickTransferButton(flowControl)
		WebUIKeyword.verifyElementVisible(webCommonPage.lblDynamic(DECIMAL_AMOUNT), flowControl)
		WebUIKeyword.delay(1)

		'Verify 4 digit'
		this.clickEditIcon()
		WebUI.delay(1)
		webCommonPage.inputTextForLabel(TRANSFER_AMOUNT, FOUR_DIGIT_AMOUNT, flowControl)
		this.clickTransferButton(flowControl)
		WebUIKeyword.verifyElementVisible(webCommonPage.lblDynamic(FOUR_DIGIT_DECIMAL_AMOUNT), flowControl)
	}

	void inputPopupPayTo(Map inputPopupData, boolean recurring = false, boolean setFutureDate = false,  FailureHandling flowControl = defaultFailureHandling) {
		'Wait show Popup'
		webCommonPage.waitModelLoading()
		Mobile.delay(1)
		'input data'
		webCommonPage.inputTextMap(inputPopupData, flowControl)
		'input data'
		WebUIKeyword.takeScreenshot()
		if (recurring) {
			setRecurring(SET_RECURRING)
		}
		if (setFutureDate) {
			inputTomorrow(EFFECTIVE_DATE)
		}
		webCommonPage.click(webCommonPage.lblNormalizeSpace(PAY, 1, HTMLTag.button), flowControl)
	}

	void selectDropdownPopupTransfer(Map dropdownPopupTransferData, FailureHandling flowControl = defaultFailureHandling) {
		webCommonPage.inputDropdownMap(dropdownPopupTransferData, flowControl)
		WebUIKeyword.takeScreenshot()
	}

	void inputTomorrow(String label, FailureHandling flowControl = defaultFailureHandling) {
		WebUIKeyword.click(webCommonPage.lblContainClass(DATEPICKER_CONTAINER_CLASS),flowControl)
		WebUIKeyword.delay(1)
		webCommonPage.selectTomorrowDate()
		WebUIKeyword.click(webCommonPage.lblContainClass(DATEPICKER_CONTAINER_CLASS, 'last()'), flowControl)
		WebUIKeyword.delay(1)
		webCommonPage.selectNextMonthDate()
	}

	void setRecurring(String label, FailureHandling flowControl = defaultFailureHandling) {
		WebUIKeyword.click(webCommonPage.fraPrecedingElementsByText(label), flowControl)
	}

	void clickShowFavouriteMenu(String favouriteName, FailureHandling flowControl = defaultFailureHandling) {
		scrollToTestObject(webCommonPage.fraSiblingElementsByText(favouriteName, 3, '', IMAGE_XPATH))
		scrollToTestObject(webCommonPage.fraSiblingElementsByText(favouriteName, 3, '', IMAGE_XPATH))
		WebUIKeyword.click(webCommonPage.fraSiblingElementsByText(favouriteName, 3, '', IMAGE_XPATH), flowControl)
	}

	void selectMenuCTAName(String ctaName, FailureHandling flowControl = defaultFailureHandling) {
		WebUIKeyword.click(webCommonPage.lblDynamic(ctaName), flowControl)
	}

	void editFavourite(Map editFavouriteData, FailureHandling flowControl = defaultFailureHandling) {
		webCommonPage.inputTextMap(editFavouriteData)
		webCommonPage.clickSaveButton()
	}

	void removeFavourite(FailureHandling flowControl = defaultFailureHandling) {
		WebUIKeyword.click(webCommonPage.lblNormalizeSpace(REMOVE_FAVOURITE,1,HTMLTag.button), flowControl)
	}

	def updateNicknameToRandom(Map map) {
		if (map.containsKey('Nickname')) {
			map['Nickname'] = StringUtils.createStrRandom(6)
		}
		return map
	}

	void addNewFavourite(Map drpAddNewFavourite, Map inputAddNewFavourite, FailureHandling flowControl = defaultFailureHandling) {
		webCommonPage.waitModelLoading()
		'input data'
		webCommonPage.inputDropdownMap(drpAddNewFavourite, flowControl)

		webCommonPage.inputTextMap(inputAddNewFavourite, flowControl)

		'Add button'
		webCommonPage.click(webCommonPage.lblNormalizeSpace(ADD, 1, HTMLTag.span), flowControl)

		webCommonPage.waitLoading()
	}

	/*
	 * Click Radio Term
	 * 
	 */
	void clickAgreeTerm(FailureHandling flowControl = defaultFailureHandling) {
		WebUIKeyword.click(webCommonPage.lblContainClass(RADIO_TERM_ClASS), flowControl)
	}
	
	void verifyStatusScreen(List verifyUIData, ElementVerificationType type = ElementVerificationType.Contains, FailureHandling flowControl = defaultFailureHandling) {
		WebUIKeyword.takeScreenshot()
		'Verify UI'
		webCommonPage.verifyAnyElementListVisible(verifyUIData, GlobalVariable.G_timeoutMedium, flowControl, type)
	}
}
