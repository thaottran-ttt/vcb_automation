package com.maybank.m2u.internal.pages.base.paytransfer

import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.maybank.common.WebUIKeyword
import com.maybank.m2u.internal.pages.base.HTMLTag
import com.maybank.m2u.internal.pages.base.WebBasePage
import com.maybank.m2u.internal.pages.base.WebCommonPage
import com.maybank.utils.StringUtils

import internal.GlobalVariable

public class WebTransfer extends WebBasePage{

	WebCommonPage webCommonPage = getPage(WebCommonPage)
	final String PAYMENT_INPUT = 'paymentInput'
	final String TRANSFER_AMOUNT = 'Transfer Amount'
	final String TRANSFER = 'TRANSFER'
	final String RM = 'RM'
	final String CALCULATE = 'CALCULATE'
	final String PROCEED = 'PROCEED'
	final String ADD_EDIT_ANOTHER_TRANSFER = 'ADD / EDIT ANOTHER TRANSFER'
	final String DECIMAL_AMOUNT = "1.11"
	final String SINGLE_AMOUNT = "1"
	final String COUNTRY = "Country"
	final String SINGLE_DECIMAL_AMOUNT = "1.00"
	final String FOUR_DIGIT_AMOUNT = "1000"
	final String FOUR_DIGIT_DECIMAL_AMOUNT = "1,000.00"
	final String BLANK_AMOUNT_MSG = 'Please do not leave field blank'
	final String ADD_NEW_FAVOURITE = 'Add New Favourite'
	final String ADD = 'ADD'
	final String PLEASE_SELECT = 'Please Select'
	final String RECIPIENT_BANK_DETAILS = "RECIPIENT'S BANK DETAILS"
	final String DEBIT_FROM_MFCA = "DEBIT FROM MFCA(Optional)"
	final String SENDDER_DETAILS = "SENDER'S DETAILS"
	final String RECIPIENT_DETAILS = "RECIPIENT'S DETAILS"
	final String TRANSFER_DETAILS = "TRANSFER DETAILS"
	final String lblUITransfer = 'Transfer Duration'
	final String ADDITIONAL_DETAILS = "ADDITIONAL DETAILS"
	final String NATIONALITY = "Nationality"


	final String REMOVE_FAVOURITE = 'REMOVE FAVOURITE'
	final String WESTERN_UNION_CASH = 'Western Union (Cash)'
	final String FOREIGN_TELEGRAPHIC_TRANSFER = 'Foreign Telegraphic Transfer'

	final String MALAYSIAN = 'Malaysian'
	final String BENEID = 'beneID'
	final String ADD_NEW_FAVOURITE_OVERSEAS = 'Add New Favourite Overseas Transfer'
	final String lblUICompare = "Compare"
	final String lblUIPostcode = 'Postcode'

	//	final String lblUICompare
	final String RECENT_TRANSFERS = 'Recent Transfers'
	final String TOTAL_AMOUNT = "Total Amount"

	final String COMPARE = 'Compare'
	final String ENTER_CARD_NUMBER = 'Enter Card Number'
	final String VISA_DIRECT = 'VISA Direct'
	final String SUBMIT = 'Submit'
	final String CARD_NUMBER = 'Card Number'
	final String NICK_NAME = 'Nickname'

	//Class
	final String RECURRING_CLASS = 'Toggle---container---3i-fe'
	final String EDIT_PENCIL_ICON_CLASS = 'Tooltip---labelIcon'
	final String RADIO_TERM_ClASS = 'TransferModal---radioButton'
	final String OWN_ACC_RECIPIENT_CLASS = 'OwnAccRecipient'
	final String TRANSFER_MODAL_CLASS = 'TransferModal---pay-btn-text'
	final String TRANSFER_MODAL_OVERSEAS_CLASS = 'TransferModal---pay-btn-overseas'
	final String OVERSEAS_FAVORITE_NAME_CLASS = 'Recipient---favoriteName'
	final String MULTI_TRANSACTION_STICKY_CLASS = 'MultiTransactionSticky'
	final String RENDER_SEARCHABLE_DROPDOWN_CLASS = 'RenderSearchableDropdown---Highlighted---'
	final String PAYMENT_INPUT_CLASS = 'RenderSearchableDropdown---paymentInput---'
	final String RENDER_DESKTOP_DROPDOWN_CLASS = 'TransferModal---selectedItem--'
	final String CURRENCY_AMOUNT_CLASSES_CLASS = 'TransferModal---currencyAmountClasses---'
	final String PAYMENT_INPUT_TRANSFER_COUNTRY_NAME = 'PayFromToContainer---paymentInput---'
	final String PAYMENT_INPUT_TRANSFER_COUNTRY_NAME_Highlighted = 'PayFromToContainer---Highlighted---'
	final String CUSTOMINPUTGROUP_AMOUNT = 'CustomInputGroup---textInput---'
	final String TRANSFER_OVERSEA_PANEL = 'TransferOverseaPanel---buttonTextWrap---'
	final String CUSTOM_INPUTGROUP_CLASS= 'CustomInputGroup---textInput--'
	final String PAY_BTN_OVERSEAS= 'TransferModal---pay-btn-overseas'
	final String RIGHT_INDICATOR= 'TransferOverseaTypeContainer---rightIndicator---'

	// attribute class
	final String SENDER_CITY = 'senderCity'

	//Xpath
	final String IMAGE_XPATH = '*//img'

	//Text Group
	static TestObject drpSelect(String text, def index = 1 ) {
		return findTestObject('Object Repository/Website/M2U/Browsers/Transfer Page/drpSelect', ['text': text , 'index' : index])
	}
	static TestObject lblTransferType(String text, def index = 1 ) {
		return findTestObject('Object Repository/Website/M2U/Browsers/Transfer Page/lblTransferType', ['text': text , 'index' : index])
	}

	TestObject lblDropdownItem(String label, String item) {
		return findTestObject('Object Repository/Website/M2U/Browsers/Transfer Page/lblDropDownItem', [('label') : label, 'item' : item])
	}

	TestObject lblDropdown(String text) {
		return findTestObject('Object Repository/Website/M2U/Browsers/Transfer Page/dropDown', [('text') : text])
	}

	//Radio button
	static TestObject btnRadio(String classText, def index = 1 ) {
		return findTestObject('Object Repository/Website/M2U/Browsers/Transfer Page/btnRadio', ['text': classText , 'index' : index])
	}

	void clickTransferButton(FailureHandling flowControl = defaultFailureHandling) {
		WebUIKeyword.click(webCommonPage.lblContainClassAndNormalizeSpace(TRANSFER_MODAL_CLASS, TRANSFER), flowControl)
	}

	void clickAddEditAnotherTransferButton(FailureHandling flowControl = defaultFailureHandling) {
		WebUIKeyword.click(webCommonPage.lblContainClassAndText(webCommonPage.BTN_CLASS, ADD_EDIT_ANOTHER_TRANSFER), flowControl)
	}

	void clickAddNewFavouriteButton(FailureHandling flowControl = defaultFailureHandling) {
		WebUIKeyword.click(webCommonPage.lblContainClassAndText(webCommonPage.BTN_CLASS, ADD_NEW_FAVOURITE), flowControl)
	}
	void clickAddNewFavouriteOverSeasButton(FailureHandling flowControl = defaultFailureHandling) {
		WebUIKeyword.click(webCommonPage.lblNormalizeSpace(ADD_NEW_FAVOURITE_OVERSEAS, 1, HTMLTag.button), flowControl)
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

	void selectTextDynamic(String favouriteName, FailureHandling flowControl = defaultFailureHandling) {
		WebUIKeyword.click(webCommonPage.lblContainClassAndContainText(OWN_ACC_RECIPIENT_CLASS, favouriteName), flowControl)
	}

	void selectOtherTransfer(Map fromToData, FailureHandling flowControl = defaultFailureHandling) {
		selectTransfer(fromToData)
	}

	// Input
	TestObject inputField(String id) {
		return findTestObject('Object Repository/Website/M2U/Browsers/Transfer Page/inputById', [('id') : id])
	}


	void selectOtherBank(String otherBank, String filter = '', FailureHandling flowControl = defaultFailureHandling) {
		webCommonPage.waitLoading()
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

	void transferToMobile(Map drpData, Map inputData, FailureHandling flowControl = defaultFailureHandling) {
		webCommonPage.waitLoading()
		'Select New Transfer'
		WebUIKeyword.click(webCommonPage.lblContainClass(PAYMENT_INPUT), FailureHandling.OPTIONAL)
		WebUIKeyword.delay(GlobalVariable.G_timeoutMiniShort)
		'input data'
		webCommonPage.inputDropdownMap(drpData, flowControl)

		webCommonPage.inputTextMap(inputData, flowControl)

		WebUIKeyword.click(webCommonPage.lblContainClass(RADIO_TERM_ClASS), flowControl)
		clickTransferButton()
	}


	void inputAmtAndTypeTransfer(String amt, String type = FOREIGN_TELEGRAPHIC_TRANSFER, FailureHandling flowControl = defaultFailureHandling) {
		'input amount'
		webCommonPage.waitLoading()
		WebUIKeyword.setText(webCommonPage.lblContainClass(CUSTOM_INPUTGROUP_CLASS),amt, flowControl)
		WebUIKeyword.click(webCommonPage.lblNormalizeSpace(COMPARE, 1, HTMLTag.span), flowControl)
		webCommonPage.waitLoading()
		scrollToTestObject(webCommonPage.lblContainClass(TRANSFER_OVERSEA_PANEL,3))
		WebUIKeyword.waitForElementClickable(lblTransferType(type), GlobalVariable.G_timeoutMedium)
		WebUIKeyword.clickWithoutScrollW3C(lblTransferType(type), flowControl)
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

	void selectDropdownPopupTransfer(Map dropdownPopupTransferData, FailureHandling flowControl = defaultFailureHandling) {
		webCommonPage.inputDropdownMap(dropdownPopupTransferData, flowControl)
		WebUIKeyword.takeScreenshot()
	}

	void inputTomorrow(String label, FailureHandling flowControl = defaultFailureHandling) {
		WebUIKeyword.click(webCommonPage.fraSiblingElementsByText(label, 1), flowControl)
		WebUIKeyword.delay(1)
		webCommonPage.selectTomorrowDate()
	}

	void setRecurring(String label, FailureHandling flowControl = defaultFailureHandling) {
		WebUIKeyword.click(webCommonPage.fraPrecedingElementsByText(label), flowControl)
	}

	void clickShowFavouriteMenu(String favouriteName, FailureHandling flowControl = defaultFailureHandling) {
		scrollToTestObject(webCommonPage.fraSiblingElementsByText(favouriteName, 3, '', IMAGE_XPATH))
		scrollToTestObject(webCommonPage.fraSiblingElementsByText(favouriteName, 3, '', IMAGE_XPATH))
		WebUIKeyword.clickWithoutScrollW3C(webCommonPage.fraSiblingElementsByText(favouriteName, 3, '', IMAGE_XPATH), flowControl)
	}

	void selectMenuCTAName(String ctaName, FailureHandling flowControl = defaultFailureHandling) {
		WebUIKeyword.click(webCommonPage.lblDynamic(ctaName), flowControl)
	}

	void editFavourite(Map editFavouriteData, FailureHandling flowControl = defaultFailureHandling) {
		webCommonPage.inputTextMap(editFavouriteData)
		webCommonPage.clickSaveButton()
	}
	void editFavouriteTransferOverSeas(Map drpAddNewFavourite, FailureHandling flowControl = defaultFailureHandling) {
		webCommonPage.inputDropdownMap(drpAddNewFavourite, flowControl)
		webCommonPage.clickSaveButton()
	}

	void clickOnRecipientDetails(FailureHandling flowControl = defaultFailureHandling) {
		WebUIKeyword.click(webCommonPage.lblDynamic(RECIPIENT_DETAILS))
	}

	void editFavouriteVisaDirect(Map inputDropdownData, Map inputTextData, FailureHandling flowControl = defaultFailureHandling) {
		webCommonPage.inputDropdownMap(inputDropdownData, flowControl)
		webCommonPage.inputTextMap(inputTextData, flowControl)
		webCommonPage.clickSaveButton()
	}

	void removeFavourite(FailureHandling flowControl = defaultFailureHandling) {
		WebUIKeyword.click(webCommonPage.lblContainClassAndText(TRANSFER_MODAL_CLASS, REMOVE_FAVOURITE), flowControl)
	}

	def updateNicknameToRandom(Map map, int length = 6) {
		if (map.containsKey('Nickname')) {
			map['Nickname'] = map['Nickname']+StringUtils.createStrRandom(length)
		}
		return map
	}

	void addNewFavourite(Map drpAddNewFavourite, Map inputAddNewFavourite, FailureHandling flowControl = defaultFailureHandling) {
		webCommonPage.waitModelLoading()
		'input data'
		webCommonPage.inputDropdownMap(drpAddNewFavourite, flowControl)

		webCommonPage.inputTextMap(inputAddNewFavourite, flowControl)

		'Add button'
		WebUIKeyword.click(webCommonPage.lblContainClassAndText(TRANSFER_MODAL_CLASS, ADD), flowControl)

		webCommonPage.waitLoading()
	}

	void addNewFavouriteWithFilter(String filterDrp, Map inputAddNewFavourite, Map drpAddNewFavourite, FailureHandling flowControl = defaultFailureHandling) {
		webCommonPage.waitModelLoading()
		WebUIKeyword.setText(webCommonPage.lblContainClass(PAYMENT_INPUT_CLASS), filterDrp)
		WebUIKeyword.click(webCommonPage.lblContainClassAndText(RENDER_SEARCHABLE_DROPDOWN_CLASS, filterDrp))
		webCommonPage.waitLoading()
		'input data'
		webCommonPage.inputDropdownMap(drpAddNewFavourite, flowControl)

		WebUIKeyword.setText(drpSelect(COUNTRY),filterDrp)
		WebUIKeyword.click(webCommonPage.lblContainClassAndText(RENDER_SEARCHABLE_DROPDOWN_CLASS, filterDrp, 2))

		webCommonPage.inputTextMap(inputAddNewFavourite, flowControl)
	}

	void addNewFavouriteOverSeas(String filterDrp, Map inputAddNewFavourite, Map drpAddNewFavourite, FailureHandling flowControl = defaultFailureHandling) {
		webCommonPage.waitModelLoading()
		WebUIKeyword.setText(webCommonPage.lblContainClass(PAYMENT_INPUT_CLASS), filterDrp)
		WebUIKeyword.click(webCommonPage.lblContainClassAndText(RENDER_SEARCHABLE_DROPDOWN_CLASS, filterDrp))
		webCommonPage.waitModelLoading()
		WebUI.delay(5)
		'input data'
		webCommonPage.inputDropdownMap(drpAddNewFavourite, flowControl)
		webCommonPage.inputTextMap(inputAddNewFavourite, flowControl)
		'Add button'
		WebUIKeyword.click(webCommonPage.lblContainClassAndText(TRANSFER_MODAL_CLASS, ADD), FailureHandling.OPTIONAL)
		webCommonPage.waitLoading()
	}

	void addNewFavouriteMOTWithFilter(String filterDrp, Map inputAddNewFavourite, Map drpAddNewFavourite, FailureHandling flowControl = defaultFailureHandling) {
		webCommonPage.waitModelLoading()
		WebUIKeyword.setText(webCommonPage.lblContainClass(PAYMENT_INPUT_CLASS), filterDrp)
		WebUIKeyword.click(webCommonPage.lblContainClassAndText(RENDER_SEARCHABLE_DROPDOWN_CLASS, filterDrp))
		webCommonPage.waitLoading()
		'input data'
		webCommonPage.inputDropdownMap(drpAddNewFavourite, flowControl)
		webCommonPage.inputTextMap(inputAddNewFavourite, flowControl)
		'Add button'
		WebUIKeyword.click(webCommonPage.lblContainClassAndText(TRANSFER_MODAL_CLASS, ADD), FailureHandling.OPTIONAL)
		webCommonPage.waitLoading()
	}

	

	void selectFavouriteName(String favouriteName, FailureHandling flowControl = defaultFailureHandling) {
		scrollToTestObject(webCommonPage.lblDynamic(favouriteName))
		WebUIKeyword.delay(GlobalVariable.G_timeoutShort)
		WebUIKeyword.waitForElementClickable(webCommonPage.lblDynamic(favouriteName), GlobalVariable.G_timeoutLong)
		WebUIKeyword.delay(GlobalVariable.G_timeoutShort)
		WebUIKeyword.click(webCommonPage.lblDynamic(favouriteName), flowControl)
	}

	void processPayFavouriteOverSeas(String amount, Map drpAddNewFavourite, Map drpTransferDetailData, FailureHandling flowControl = defaultFailureHandling) {
		delay(GlobalVariable.G_timeoutMedium)
		WebUIKeyword.setText(webCommonPage.lblContainClass(CURRENCY_AMOUNT_CLASSES_CLASS), amount)
		WebUIKeyword.click(webCommonPage.lblNormalizeSpace(CALCULATE, 1, HTMLTag.span), flowControl)
		WebUIKeyword.click(webCommonPage.lblNormalizeSpace(PROCEED, 1, HTMLTag.span), FailureHandling.OPTIONAL)
		webCommonPage.inputDropdownMap(drpAddNewFavourite, flowControl)
		addTransferDetail(drpTransferDetailData)
	}

	void processPayFavouriteMOT(String amount, Map inputAdditionalDetailData, Map drpAdditionalDetailData, FailureHandling flowControl = defaultFailureHandling) {

		delay(GlobalVariable.G_timeoutShort)
		WebUIKeyword.setText(webCommonPage.lblContainClass(CURRENCY_AMOUNT_CLASSES_CLASS), amount)
		WebUIKeyword.click(webCommonPage.lblNormalizeSpace(CALCULATE, 1, HTMLTag.span), flowControl)
		'Add additional detail'
		addAdditionalDetail(inputAdditionalDetailData, drpAdditionalDetailData)
	}
	void addTransferDetail(Map inputData, FailureHandling flowControl = defaultFailureHandling) {
		scrollToTestObject(webCommonPage.lblNormalizeSpace(TRANSFER_DETAILS))
		WebUIKeyword.click(webCommonPage.lblNormalizeSpace(TRANSFER_DETAILS))
		webCommonPage.inputDropdownMap(inputData, flowControl)
		WebUIKeyword.click(btnRadio(RADIO_TERM_ClASS, 'last()'))
		WebUIKeyword.click(webCommonPage.lblContainClassAndNormalizeSpace(TRANSFER_MODAL_CLASS, TRANSFER), flowControl)
	}
	void addTransferDetailOpen(Map inputData, FailureHandling flowControl = defaultFailureHandling) {
		scrollToTestObject(webCommonPage.lblNormalizeSpace(TRANSFER_DETAILS))
		WebUIKeyword.click(webCommonPage.lblNormalizeSpace(TRANSFER_DETAILS))
		webCommonPage.inputDropdownMap(inputData, flowControl)
		WebUIKeyword.click(btnRadio(RADIO_TERM_ClASS, 'last()'))
		if(WebUIKeyword.waitForElementClickable(webCommonPage.lblNormalizeSpace(RM, 1, HTMLTag.span), GlobalVariable.G_timeoutShort)) {
			WebUIKeyword.click(webCommonPage.lblNormalizeSpace(RM, 1 , HTMLTag.span), flowControl)
		}else {
			WebUIKeyword.click(webCommonPage.lblNormalizeSpace(TRANSFER, 2, HTMLTag.span), flowControl)
		}
	}
	void addAdditionalDetail(Map inputData, Map inputDrpData, FailureHandling flowControl = defaultFailureHandling) {
		scrollToTestObject(webCommonPage.lblNormalizeSpace(ADDITIONAL_DETAILS))
		WebUIKeyword.click(webCommonPage.lblNormalizeSpace(ADDITIONAL_DETAILS))
		webCommonPage.inputDropdownMap(inputDrpData, flowControl)
		webCommonPage.inputTextMap(inputData, flowControl)
		WebUIKeyword.click(btnRadio(RADIO_TERM_ClASS, 'last()'))
		if(WebUIKeyword.waitForElementClickable(webCommonPage.lblNormalizeSpace(RM, 1, HTMLTag.span), GlobalVariable.G_timeoutShort)) {
			WebUIKeyword.click(webCommonPage.lblNormalizeSpace(RM, 1 , HTMLTag.span), flowControl)
		}else {
			WebUIKeyword.click(webCommonPage.lblNormalizeSpace(TRANSFER, 2, HTMLTag.span), flowControl)
		}
	}
	/* Process OverSeas FTT WU Cash
	 * */
	void processOverSeasFTTWUCash(String countryName, String amount, FailureHandling flowControl = defaultFailureHandling) {
		'Input Country Name'
		WebUIKeyword.setText(webCommonPage.lblContainClass(PAYMENT_INPUT_TRANSFER_COUNTRY_NAME), countryName)
		WebUIKeyword.click(webCommonPage.lblContainClassAndText(PAYMENT_INPUT_TRANSFER_COUNTRY_NAME_Highlighted, countryName))
		webCommonPage.waitLoading()
		WebUIKeyword.setText(webCommonPage.lblContainClass(CUSTOMINPUTGROUP_AMOUNT), amount)
		webCommonPage.waitLoading()
		WebUIKeyword.click(webCommonPage.lblDynamic(lblUICompare),flowControl)
		webCommonPage.waitLoading()
		scrollToTestObject(webCommonPage.lblContainClass(TRANSFER_OVERSEA_PANEL,3))
		scrollToTestObject(webCommonPage.lblContainClass(TRANSFER_OVERSEA_PANEL,3))
		webCommonPage.waitLoading()
		WebUIKeyword.click(webCommonPage.lblContainClass(TRANSFER_OVERSEA_PANEL,3),flowControl)
		'Scroll to lblUIPostcode'
		scrollToTestObject(webCommonPage.lblDynamic(lblUIPostcode))
		scrollToTestObject(webCommonPage.lblDynamic(lblUIPostcode))
	}
	void addReceiptBankDetail(Map inputData, Map drpData, FailureHandling flowControl = defaultFailureHandling) {
		scrollToTestObject(webCommonPage.lblNormalizeSpace(RECIPIENT_BANK_DETAILS))
		WebUIKeyword.click(webCommonPage.lblNormalizeSpace(RECIPIENT_BANK_DETAILS))
		webCommonPage.waitLoading()
		'input data'
		webCommonPage.inputDropdownMap(drpData, flowControl)

		webCommonPage.inputTextMap(inputData, FailureHandling.OPTIONAL)
	}

	void addDebitFromMFCA(Map drpData, FailureHandling flowControl = defaultFailureHandling) {
		scrollToTestObject(webCommonPage.lblNormalizeSpace(DEBIT_FROM_MFCA))
		WebUIKeyword.click(webCommonPage.lblNormalizeSpace(DEBIT_FROM_MFCA))
		webCommonPage.waitLoading()
		'input data'
		webCommonPage.inputDropdownMap(drpData, flowControl)
	}

	void addSenderDetail(String country, Map inputData, FailureHandling flowControl = defaultFailureHandling) {

		WebUIKeyword.setText(drpSelect(COUNTRY),country)
		WebUIKeyword.click(webCommonPage.lblContainClassAndText(RENDER_SEARCHABLE_DROPDOWN_CLASS, country, 'last()'))

		webCommonPage.inputTextMap(inputData, FailureHandling.OPTIONAL)
	}

	void addReceiptDetail(Map inputData, Map drpData, FailureHandling flowControl = defaultFailureHandling) {
		scrollToTestObject(webCommonPage.lblNormalizeSpace(RECIPIENT_DETAILS))
		WebUIKeyword.click(webCommonPage.lblNormalizeSpace(RECIPIENT_DETAILS))
		webCommonPage.waitLoading()
		'input data'
		click(webCommonPage.lblContainClassAndText(RENDER_DESKTOP_DROPDOWN_CLASS, PLEASE_SELECT, 2))
		click(webCommonPage.lblContainClassAndText(RENDER_DESKTOP_DROPDOWN_CLASS,MALAYSIAN))
		webCommonPage.inputDropdownMap(drpData, flowControl)
		WebUIKeyword.setText(webCommonPage.getTestObjectById(BENEID), StringUtils.createStrRandom(10))
		webCommonPage.inputTextMap(inputData, FailureHandling.OPTIONAL)
		'Add button'
		if(WebUIKeyword.waitForElementClickable(webCommonPage.lblContainClassAndText(TRANSFER_MODAL_CLASS, ADD), GlobalVariable.G_timeoutShort)) {
			WebUIKeyword.click(webCommonPage.lblContainClassAndText(TRANSFER_MODAL_CLASS, ADD), FailureHandling.OPTIONAL)
		}

		webCommonPage.waitLoading()
	}

	void addReceiptMFCADetail(Map inputData, Map drpData, FailureHandling flowControl = defaultFailureHandling) {
		scrollToTestObject(webCommonPage.lblNormalizeSpace(RECIPIENT_DETAILS))
		WebUIKeyword.click(webCommonPage.lblNormalizeSpace(RECIPIENT_DETAILS))
		webCommonPage.waitLoading()
		'input data'
		webCommonPage.selectDropdownByTestObject(webCommonPage.drpForLabelNo(NATIONALITY, 'last()'), MALAYSIAN)
		webCommonPage.inputDropdownMap(drpData, flowControl)
		WebUIKeyword.setText(webCommonPage.getTestObjectById(BENEID), StringUtils.createStrRandom(10))
		webCommonPage.inputTextMap(inputData, flowControl)
		'Add button'
		if(WebUIKeyword.waitForElementClickable(webCommonPage.lblContainClassAndText(TRANSFER_MODAL_CLASS, ADD), GlobalVariable.G_timeoutShort)) {
			WebUIKeyword.click(webCommonPage.lblContainClassAndText(TRANSFER_MODAL_CLASS, ADD), FailureHandling.OPTIONAL)
		}

		webCommonPage.waitLoading()
	}

	/*
	 * Click Radio Term
	 * 
	 */
	void clickAgreeTerm(FailureHandling flowControl = defaultFailureHandling) {
		WebUIKeyword.click(webCommonPage.lblContainClass(RADIO_TERM_ClASS), flowControl)
	}
	
	void selectOtherPillar(FailureHandling flowControl = defaultFailureHandling) {
		WebUIKeyword.click(webCommonPage.lblContainClass(RIGHT_INDICATOR), flowControl)
	}

	void inputAmountForVisaDirect(String amount, String cardNumber, FailureHandling flowControl = defaultFailureHandling) {
		'input amount'
		webCommonPage.waitLoading()
		WebUIKeyword.setText(webCommonPage.lblContainClass(CUSTOM_INPUTGROUP_CLASS),amount, flowControl)
		WebUIKeyword.click(webCommonPage.lblNormalizeSpace(COMPARE, 1, HTMLTag.span), flowControl)
		WebUIKeyword.delay(GlobalVariable.G_timeoutMedium)
		WebUIKeyword.waitForElementClickable(webCommonPage.lblDynamic(ENTER_CARD_NUMBER), GlobalVariable.G_timeoutMedium)
		WebUIKeyword.scrollToTestObject(webCommonPage.lblContainClass(RIGHT_INDICATOR))
		selectOtherPillar()
		WebUIKeyword.click(webCommonPage.lblDynamic(ENTER_CARD_NUMBER), flowControl)
		webCommonPage.inputTextForLabel(CARD_NUMBER, cardNumber, flowControl)
		WebUIKeyword.waitForElementClickable(webCommonPage.btnNormalizeSpaceDynamic(SUBMIT), GlobalVariable.G_timeoutMedium)
		WebUIKeyword.click(webCommonPage.btnNormalizeSpaceDynamic(SUBMIT), flowControl)
		scrollToTestObject(lblTransferType(VISA_DIRECT))
		WebUIKeyword.waitForElementClickable(lblTransferType(VISA_DIRECT), GlobalVariable.G_timeoutMedium)
		WebUIKeyword.click(lblTransferType(VISA_DIRECT), flowControl)
	}

	void agreeTermForVisaDirect(FailureHandling flowControl = defaultFailureHandling) {
		WebUIKeyword.click(webCommonPage.lblDynamic(TRANSFER_DETAILS), flowControl)
		WebUIKeyword.waitForElementClickable(webCommonPage.lblContainClass(RADIO_TERM_ClASS), GlobalVariable.G_timeoutMedium)
		WebUIKeyword.click(webCommonPage.lblContainClass(RADIO_TERM_ClASS), flowControl)
	}

	void inputVisaDirectFavoriteDetails(Map inputDropdownData, Map inputTextData, FailureHandling flowControl = defaultFailureHandling) {
		webCommonPage.inputDropdownMap(inputDropdownData, flowControl)
		webCommonPage.inputTextMap(inputTextData, flowControl)
		WebUIKeyword.waitForElementClickable(webCommonPage.btnNormalizeSpaceDynamic(ADD), GlobalVariable.G_timeoutMedium)
		WebUIKeyword.click(webCommonPage.btnNormalizeSpaceDynamic(ADD), flowControl)
	}

	void clickDetailsSection(String text, int index = 1, FailureHandling flowControl = defaultFailureHandling) {
		scrollToTestObject(webCommonPage.lblDynamic(text, index))
		WebUIKeyword.click(webCommonPage.lblDynamic(text, index), flowControl)
	}

	void clickOverseasTransferButton(String classText = TRANSFER_MODAL_OVERSEAS_CLASS, FailureHandling flowControl = defaultFailureHandling) {
		WebUIKeyword.click(webCommonPage.lblContainClassAndContainText(classText, TRANSFER), flowControl)
	}

	void selectDropdown(String label, String value, FailureHandling flowControl = defaultFailureHandling, boolean checkCurrentValue = false) {
		if(checkCurrentValue && WebUIKeyword.waitForElementPresent(this.lblDropdownItem(label, value), GlobalVariable.G_timeoutMiniShort, FailureHandling.OPTIONAL)) {
			info("No Select Dropdown")
			return
		}
		scrollToTestObject(this.lblDropdown(label))
		WebUIKeyword.click(this.lblDropdown(label), flowControl)
		Mobile.delay(GlobalVariable.G_timeoutMiniShort)
		if (!value.blank) {
			scrollToTestObject(this.lblDropdownItem(label, value))
			WebUIKeyword.waitForElementClickable(this.lblDropdownItem(label, value), GlobalVariable.G_timeoutShort, FailureHandling.OPTIONAL)
			WebUIKeyword.click(this.lblDropdownItem(label,value), flowControl)
			'Wait for loading data'
		}else {
			'click to first item of lable as default'
			WebUIKeyword.waitForElementClickable(webCommonPage.lblDropdownFirstItem(label), GlobalVariable.G_timeoutShort, FailureHandling.OPTIONAL)
			WebUIKeyword.click(webCommonPage.lblDropdownFirstItem(label), flowControl)
		}
	}

	boolean inputDropdownTransferMap(Map data, FailureHandling flowControl = defaultFailureHandling){
		data.each { key, value ->
			this.selectDropdown(key, value, flowControl)
		}
	}

	/**
	 * inputData
	 */
	void inputDataTransfer(Map inputData, Map inputDropdownData = [:],FailureHandling flowControl = defaultFailureHandling) {
		'Input data'
		webCommonPage.inputTextMap(inputData, flowControl)
		'Select dropdown data'
		this.inputDropdownTransferMap(inputDropdownData, flowControl)
		'Take ScreenShort'
		WebUIKeyword.takeScreenshot()
	}

	void inputCity(String city) {
		WebUIKeyword.scrollToTestObject(inputField(SENDER_CITY))
		delay(2)
		WebUIKeyword.setText(inputField(SENDER_CITY), city)
	}

	void clicOverseasFavoriteAgreeTerm(FailureHandling flowControl = defaultFailureHandling) {
		WebUIKeyword.click(webCommonPage.lblContainClass(RADIO_TERM_ClASS,3), flowControl)
	}

	void InputWUCashFavourite(String amount, FailureHandling flowControl = defaultFailureHandling) {
		delay(GlobalVariable.G_timeoutMedium)
		WebUIKeyword.setText(webCommonPage.lblContainClass(CURRENCY_AMOUNT_CLASSES_CLASS), amount)
		WebUIKeyword.click(webCommonPage.lblNormalizeSpace(CALCULATE, 1, HTMLTag.span), flowControl)
	}

	void selectRecentTrx(String recentTrxName, FailureHandling flowControl = defaultFailureHandling) {
		scrollToTestObject(webCommonPage.lblNormalizeSpace(RECENT_TRANSFERS))
		scrollToTestObject(webCommonPage.lblDynamic(recentTrxName, 'last()'))
		scrollToTestObject(webCommonPage.lblDynamic(recentTrxName, 'last()'))
		WebUIKeyword.click(webCommonPage.lblDynamic(recentTrxName, 'last()'), flowControl)
	}

	void processRecentTransferOverSeas(String amount, Map drpAddNewFavourite, Map drpTransferDetailData, FailureHandling flowControl = defaultFailureHandling) {
		delay(GlobalVariable.G_timeoutShort)
		WebUIKeyword.setText(webCommonPage.lblContainClass(CURRENCY_AMOUNT_CLASSES_CLASS), amount)
		WebUIKeyword.click(webCommonPage.lblNormalizeSpace(CALCULATE, 1, HTMLTag.span), flowControl)
		webCommonPage.inputDropdownMap(drpAddNewFavourite, flowControl)
		scrollToTestObject(webCommonPage.lblNormalizeSpace(ADDITIONAL_DETAILS))
		WebUIKeyword.click(webCommonPage.lblNormalizeSpace(ADDITIONAL_DETAILS))
		webCommonPage.inputDropdownMap(drpTransferDetailData, flowControl)
		WebUIKeyword.click(btnRadio(RADIO_TERM_ClASS, 3))
		WebUIKeyword.click(webCommonPage.lblNormalizeSpace(TRANSFER, 1, HTMLTag.button), flowControl)
	}

	Double extractTransferOverseasTotalAmount() {
		scrollToTestObject(webCommonPage.fraSiblingElementsByText(TOTAL_AMOUNT))
		String amountStr = WebUIKeyword.getText(webCommonPage.fraSiblingElementsByText(TOTAL_AMOUNT))
		return Double.parseDouble(amountStr.replaceAll("[^\\d.]", ""))
	}
}
