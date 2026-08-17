package com.vcb.mobile.internal.pages.base.loyaltypage

import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testobject.TestObject
import com.vcb.common.MobileDriver
import com.vcb.mobile.internal.pages.base.BasePage
import com.vcb.mobile.internal.pages.base.CommonPage
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile

import internal.GlobalVariable

public abstract class LoyaltyPage extends BasePage {
	CommonPage commonPage = getPage(CommonPage)

	abstract TestObject imgCameraIcon()
	abstract TestObject imgBarCode()
	abstract TestObject lblColorPicker(String fieldName)
	abstract TestObject txtDescription(String text)

	String cardNumber;

	static final String PROCEED = "Proceed"
	static final String LOYALTY = "Loyalty"
	static final String CONFIRM = "Confirm"
	static final String ADD_CARD = "Add Card"
	static final String ADD_CARD_TITLE = "Add Loyalty Card"
	static final String EDIT_CARD_TITLE = "Edit Loyalty Card"
	static final String CARD_NAME = "Card Name"
	static final String CARD_NUMBER = "Card number"
	static final String EXPIRY_DATE = "Expiry Date"
	static final String CARD_PHOTO = "Card Photo"
	static final String FRONT = "Front"
	static final String BACK = "Back"
	static final String CONTINUE = "Continue"
	static final String ADD_IMAGE = "Add Card Image"
	static final String VIEW_PHOTO = "View Card Photo"
	static final String REMOVE = "Remove"
	static final String EDIT_FUNCTION = "Edit Card"
	static final String OPTIONAL = "Optional"
	static final String CARD_PHOTO_LABEL = "Card photo"
	static final String INFO1 = "Never Save Your Sensitive Info"
	static final String INFO2 = "As a safety precaution, please do not add bank cards and/or documents containing sensitive information here. This is to keep you safe from ID theft and fraud."
	static final String INFO3 = "Choosing to proceed means that you are agreeable to not sharing any sensitive information in this section."
	static final String DESC1 = "Less cards, lighter wallet"
	static final String DESC2 = "Add your loyalty cards so you can use them on the go. Never worry about leaving them behind again."
	static final String DESC3 = "Note: Please do not add bank cards and/or documents containing sensitive information here."
	static final String ADD_CARD_IMAGE_FRONT = "Add Card Image (Front)"
	static final String ADD_CARD_IMAGE_BACK = "Add Card Image (Back)"
	static final String ADD_CARD_IMAGE_DESC = "Place your card in the area and capture when ready"
	

	void verifyPage(FailureHandling flowControl = defaultFailureHandling) {
		
		commonPage.waitLoading()
		Mobile.takeScreenshot()
		'Wait for Proceed button available'
		if(waitForElementPresent(commonPage.lblDynamic(PROCEED), GlobalVariable.G_timeoutMedium,FailureHandling.OPTIONAL)) {
			Mobile.takeScreenshot()
			'Verify Loyalty Card Info Banner'
			verifyElementExist(commonPage.lblDynamic(INFO1), GlobalVariable.G_timeoutMedium, flowControl)
			verifyElementExist(commonPage.lblContainDynamic(INFO2), GlobalVariable.G_timeoutMedium, flowControl)
			verifyElementExist(commonPage.lblContainDynamic(INFO3), GlobalVariable.G_timeoutMedium, flowControl)
			'Click Proceed button'
			MobileDriver.tap(commonPage.lblDynamic(PROCEED), GlobalVariable.G_timeoutMedium)
		}
		Mobile.takeScreenshot()
		'Verify Header exist'
		verifyElementExist(commonPage.lblDynamic(LOYALTY), GlobalVariable.G_timeoutMedium, flowControl)
		'Verify Loyalty Card Description'
		if(waitForElementPresent(commonPage.lblDynamic(DESC1), GlobalVariable.G_timeoutMedium, flowControl)) {
			Mobile.takeScreenshot()
			verifyElementExist(commonPage.lblDynamic(DESC2), GlobalVariable.G_timeoutMedium, flowControl)
			verifyElementExist(commonPage.lblDynamic(DESC3), GlobalVariable.G_timeoutMedium, flowControl)
		}
		delay(GlobalVariable.G_timeoutMiniShort)
	}
	void addCard(Map cardData,FailureHandling flowControl = defaultFailureHandling) {
		'Click Add Card button'
		tapAddCard()
		'Enter Card data'
		enterCardValue(cardData)
		'View Card Photo'
		viewCardPhoto()
		'Verify Bar Code'
		verifyBarCode()
		'Select Confirm'
		MobileDriver.tap(commonPage.btnDynamic(CONFIRM), GlobalVariable.G_timeoutLong, flowControl)
		commonPage.waitLoading()
		Mobile.takeScreenshot()
	}

	void tapAddCard(FailureHandling flowControl = defaultFailureHandling) {
		'Verify Add Card button exist'
		verifyElementExist(commonPage.lblContainDynamic(ADD_CARD), GlobalVariable.G_timeoutMedium, flowControl)
		Mobile.takeScreenshot()
		'Click Add Card button'
		MobileDriver.tap(commonPage.lblContainDynamic(ADD_CARD), GlobalVariable.G_timeoutMedium, flowControl)
		delay(GlobalVariable.G_timeoutMiniShort)
		Mobile.takeScreenshot()
	}
	void verifyAddPageTitle(FailureHandling flowControl = defaultFailureHandling) {
		'Wait for Add Loyalty Card available'
		waitForElementPresent(commonPage.lblDynamic(ADD_CARD_TITLE), GlobalVariable.G_timeoutMedium, flowControl)
		'Verify Add Loyalty Card exist'
		verifyElementExist(commonPage.lblContainDynamic(ADD_CARD_TITLE), GlobalVariable.G_timeoutMedium, flowControl)
	}
	
	void verifyEditPageTitle(FailureHandling flowControl = defaultFailureHandling) {
		'Wait for Add Loyalty Card available'
		waitForElementPresent(commonPage.lblDynamic(ADD_CARD_TITLE), GlobalVariable.G_timeoutMedium, flowControl)
		'Verify Add Loyalty Card exist'
		verifyElementExist(commonPage.lblContainDynamic(ADD_CARD_TITLE), GlobalVariable.G_timeoutMedium, flowControl)
	}
	void enterCardValue(Map cardData, FailureHandling flowControl = defaultFailureHandling) {
		
		'Verify Add Loyalty Card exist'
		verifyAddPageTitle()
		Mobile.takeScreenshot()
		'Enter Card name'
		commonPage.enterTextFieldValue(CARD_NAME, cardData.get("cardName"), GlobalVariable.G_timeoutLong, flowControl)
		Mobile.takeScreenshot()
		'Enter Card Number'
		commonPage.enterTextFieldValue(CARD_NUMBER, cardData.get("cardNumber"), GlobalVariable.G_timeoutLong, flowControl)		
		Mobile.takeScreenshot()
		'Enter Expiry Date'
		commonPage.enterTextFieldValue(EXPIRY_DATE, cardData.get("cardEx"), GlobalVariable.G_timeoutLong, flowControl)
		Mobile.takeScreenshot()
		'Enter Description'
		MobileDriver.setText(this.txtDescription(OPTIONAL), cardData.get("cardDes"), GlobalVariable.G_timeoutLong, flowControl)
		Mobile.takeScreenshot()
		'Scroll to card photo'
		Mobile.scrollToText(CARD_PHOTO_LABEL)
		'Select Camera icon'
		if (waitForElementPresent(commonPage.imgSiblingGroup(CARD_PHOTO_LABEL), GlobalVariable.G_timeoutMedium)) {
			Mobile.takeScreenshot()
			MobileDriver.tap(commonPage.imgSiblingGroup(CARD_PHOTO_LABEL), GlobalVariable.G_timeoutLong, flowControl)
		}
		Mobile.takeScreenshot()
		'Verify Card Photo'
		verifyElementExist(commonPage.lblContainDynamic(CARD_PHOTO), GlobalVariable.G_timeoutLong, flowControl)
		'Select Front'
		MobileDriver.tap(commonPage.lblDynamic(FRONT), GlobalVariable.G_timeoutLong, flowControl)
		verifyElementExist(commonPage.lblContainDynamic(ADD_CARD_IMAGE_FRONT), GlobalVariable.G_timeoutMedium, flowControl)
		verifyElementExist(commonPage.lblContainDynamic(ADD_CARD_IMAGE_DESC), GlobalVariable.G_timeoutMedium, flowControl)
		Mobile.takeScreenshot()
		'Take Picture'
		takePicure()
		MobileDriver.delay(GlobalVariable.G_timeoutShort)
		Mobile.takeScreenshot()
		'Select Back'
		MobileDriver.tap(commonPage.lblDynamic(BACK), GlobalVariable.G_timeoutLong, flowControl)
		verifyElementExist(commonPage.lblContainDynamic(ADD_CARD_IMAGE_BACK), GlobalVariable.G_timeoutMedium, flowControl)
		verifyElementExist(commonPage.lblContainDynamic(ADD_CARD_IMAGE_DESC), GlobalVariable.G_timeoutMedium, flowControl)
		Mobile.takeScreenshot()
		'Take Picture'
		takePicure()
		MobileDriver.delay(GlobalVariable.G_timeoutShort)
		Mobile.takeScreenshot()
		'Select Done'
		commonPage.tapDoneBtn(flowControl)
		'Verify Add Loyalty Card exist'
		verifyAddPageTitle()
		Mobile.takeScreenshot()
		'Select Continue'
		MobileDriver.tap(commonPage.lblDynamic(CONTINUE), GlobalVariable.G_timeoutLong, flowControl)
	}
	void takePicure(FailureHandling flowControl = defaultFailureHandling) {
		'Verify Add Card Image exist'
		verifyElementExist(commonPage.lblContainDynamic(ADD_IMAGE), GlobalVariable.G_timeoutShort, flowControl)
		verifyElementExist(commonPage.imgNoDynamic(4), GlobalVariable.G_timeoutShort, flowControl)
		MobileDriver.tap(commonPage.imgNoDynamic(4), GlobalVariable.G_timeoutLong, flowControl)
	}
	void viewCardPhoto(FailureHandling flowControl = defaultFailureHandling) {
		MobileDriver.delay(GlobalVariable.G_timeoutShort)
		'Verify View Card Photo exist'
		verifyElementExist(commonPage.lblDynamic(VIEW_PHOTO), GlobalVariable.G_timeoutLong, flowControl)
		Mobile.takeScreenshot()
		'Select View Card Photo'
		MobileDriver.tap(commonPage.lblDynamic(VIEW_PHOTO), GlobalVariable.G_timeoutLong, flowControl)
		Mobile.takeScreenshot()
		'Select Done'
		commonPage.tapDoneBtn(flowControl)
	}
	void verifyBarCode(FailureHandling flowControl = defaultFailureHandling) {
		MobileDriver.delay(GlobalVariable.G_timeoutShort)
		'Verify Bar Code exist'
		verifyElementExist(this.imgBarCode(), GlobalVariable.G_timeoutLong, flowControl)
		Mobile.takeScreenshot()
	}
	void editLoyaltyCard(String edit, Map dataCardEdit, FailureHandling flowControl = defaultFailureHandling) {
		'Verify old Card Exist'
		verifyElementExist(commonPage.lblContainDynamic(dataCardEdit.get("oldCardNumber")), GlobalVariable.G_timeoutMedium, flowControl)
		Mobile.takeScreenshot()
		MobileDriver.tap(commonPage.lblDynamic(dataCardEdit.get("oldCardNumber")), GlobalVariable.G_timeoutLong, flowControl)		
		delay(GlobalVariable.G_timeoutMiniShort)
		Mobile.takeScreenshot()
		MobileDriver.tap(commonPage.imgNoDynamic(2), GlobalVariable.G_timeoutLong, flowControl)
		'Verify Action exist'
		verifyElementExist(commonPage.lblDynamic(edit),GlobalVariable.G_timeoutLong, flowControl)
		Mobile.takeScreenshot()
		'Select Action'
		MobileDriver.tap(commonPage.lblDynamic(edit), GlobalVariable.G_timeoutLong, flowControl)
		Mobile.takeScreenshot()
		commonPage.waitLoading()
		editValue(dataCardEdit)

	}
	void editValue(Map dataCardEdit, FailureHandling flowControl = defaultFailureHandling) {
		
		'Wait for Edit Loyalty Card available'
		waitForElementPresent(commonPage.lblDynamic(EDIT_CARD_TITLE), GlobalVariable.G_timeoutMedium, flowControl)
		'Verify Edit Loyalty Card exist'
		verifyElementExist(commonPage.lblContainDynamic(EDIT_CARD_TITLE), GlobalVariable.G_timeoutMedium, flowControl)
		Mobile.takeScreenshot()
		'Enter Card name'
		commonPage.enterTextFieldValue(CARD_NAME, dataCardEdit.get("cardName"), GlobalVariable.G_timeoutLong, flowControl)
		Mobile.takeScreenshot()
		'Enter Card Number'
		commonPage.enterTextFieldValue(CARD_NUMBER, dataCardEdit.get("cardNumber"), GlobalVariable.G_timeoutLong, flowControl)
		Mobile.takeScreenshot()
		'Enter Expiry Date'
		commonPage.enterTextFieldValue(EXPIRY_DATE, dataCardEdit.get("cardEx"), GlobalVariable.G_timeoutLong, flowControl)
		Mobile.takeScreenshot()
		'Enter Description'
		MobileDriver.setText(commonPage.txtEditNo(4), dataCardEdit.get("cardDes"), GlobalVariable.G_timeoutLong, flowControl)
		Mobile.takeScreenshot()
		'Scroll to card photo'
		Mobile.scrollToText(CARD_PHOTO_LABEL)
		'Select Camera icon'
		if (waitForElementPresent(commonPage.imgSiblingGroup(CARD_PHOTO_LABEL), GlobalVariable.G_timeoutMedium)) {
			Mobile.takeScreenshot()
			MobileDriver.tap(commonPage.imgSiblingGroup(CARD_PHOTO_LABEL), GlobalVariable.G_timeoutLong, flowControl)
		}
		Mobile.takeScreenshot()
		'Verify Card Photo'
		verifyElementExist(commonPage.lblContainDynamic(CARD_PHOTO), GlobalVariable.G_timeoutLong, flowControl)
		'Select Front'
		waitForElementPresent(commonPage.imgNoDynamic(3), GlobalVariable.G_timeoutLong)
		MobileDriver.doubleTap(commonPage.imgNoDynamic(3), GlobalVariable.G_timeoutLong, flowControl)
		verifyElementExist(commonPage.lblContainDynamic(ADD_CARD_IMAGE_FRONT), GlobalVariable.G_timeoutMedium, flowControl)
		verifyElementExist(commonPage.lblContainDynamic(ADD_CARD_IMAGE_DESC), GlobalVariable.G_timeoutMedium, flowControl)
		Mobile.takeScreenshot()
		'Take Picture'
		takePicure()
		MobileDriver.delay(GlobalVariable.G_timeoutShort)
		Mobile.takeScreenshot()
		'Select Back'
		waitForElementPresent(commonPage.imgNoDynamic(5), GlobalVariable.G_timeoutLong)
		MobileDriver.doubleTap(commonPage.imgNoDynamic(5), GlobalVariable.G_timeoutLong, flowControl)
		verifyElementExist(commonPage.lblContainDynamic(ADD_CARD_IMAGE_BACK), GlobalVariable.G_timeoutMedium, flowControl)
		verifyElementExist(commonPage.lblContainDynamic(ADD_CARD_IMAGE_DESC), GlobalVariable.G_timeoutMedium, flowControl)
		Mobile.takeScreenshot()
		'Take Picture'
		takePicure()
		MobileDriver.delay(GlobalVariable.G_timeoutShort)
		Mobile.takeScreenshot()
		'Select Done'
		commonPage.tapDoneBtn(flowControl)
		Mobile.takeScreenshot()
		'Select Confirm'
		MobileDriver.tap(commonPage.btnDynamic(CONFIRM), GlobalVariable.G_timeoutLong, flowControl)
		MobileDriver.delay(GlobalVariable.G_timeoutShort)
	}
	void removeCard(String remove, Map dataCardEdit, FailureHandling flowControl = defaultFailureHandling) {
		'Verify old Card Exist'
		verifyElementExist(commonPage.lblContainDynamic(dataCardEdit.get("cardNumber")), GlobalVariable.G_timeoutMedium, flowControl)
		Mobile.takeScreenshot()
		MobileDriver.tap(commonPage.lblDynamic(dataCardEdit.get("cardNumber")), GlobalVariable.G_timeoutLong, flowControl)
		delay(GlobalVariable.G_timeoutMiniShort)
		Mobile.takeScreenshot()
		MobileDriver.tap(commonPage.imgNoDynamic(2), GlobalVariable.G_timeoutLong, flowControl)
		'Verify Action exist'
		verifyElementExist(commonPage.lblDynamic(remove),GlobalVariable.G_timeoutLong, flowControl)
		Mobile.takeScreenshot()
		'Select Action'
		MobileDriver.tap(commonPage.lblDynamic(remove), GlobalVariable.G_timeoutLong, flowControl)
		Mobile.takeScreenshot()
		MobileDriver.tap(commonPage.btnDynamic(CONFIRM), GlobalVariable.G_timeoutLong, flowControl)
		commonPage.waitLoading()
		'Verify Card Number not exist'
		!verifyElementExist(commonPage.lblContainDynamic(dataCardEdit.get("cardNumber")), GlobalVariable.G_timeoutLong,flowControl)
	}
	void verifyCardExist(Map editCardData, FailureHandling flowControl = defaultFailureHandling) {
		'Verify Card Number exist'
		verifyElementExist(commonPage.lblContainDynamic(editCardData.get("cardName")), GlobalVariable.G_timeoutLong,flowControl)
		Mobile.takeScreenshot()
	}
	void verifyAddedCard(Map cardData, FailureHandling flowControl = defaultFailureHandling) {
		commonPage.waitLoading()
		commonPage.scrollToTextDefault(cardData.get("cardName"))
		verifyElementExist(commonPage.lblContainDynamic(cardData.get("cardName")), GlobalVariable.G_timeoutLong,flowControl)
		Mobile.takeScreenshot()
		'Tap On Added Card'
		MobileDriver.tap(commonPage.lblDynamic(cardData.get("cardName")), GlobalVariable.G_timeoutLong, flowControl)
		commonPage.waitLoading()
		Mobile.takeScreenshot()
		'Verify Card name'
		verifyElementExist(commonPage.lblContainDynamic(cardData.get("cardName")), GlobalVariable.G_timeoutMedium, flowControl)
		Mobile.takeScreenshot()
		'Enter Card Number'
		verifyElementExist(commonPage.lblContainDynamic(cardData.get("cardNumber")), GlobalVariable.G_timeoutMedium, flowControl)		
		Mobile.takeScreenshot()
		'Enter Expiry Date'
		verifyElementExist(commonPage.lblContainDynamic(cardData.get("cardEx")), GlobalVariable.G_timeoutMedium, flowControl)
		Mobile.takeScreenshot()
		'Enter Description'
		verifyElementExist(commonPage.lblContainDynamic(cardData.get("cardDes")), GlobalVariable.G_timeoutMedium, flowControl)
		Mobile.takeScreenshot()
	}
	void verifyEditedCard(Map dataCardEdit, FailureHandling flowControl = defaultFailureHandling) {
		commonPage.waitLoading()
		commonPage.scrollToTextDefault(dataCardEdit.get("cardName"))
		verifyElementExist(commonPage.lblContainDynamic(dataCardEdit.get("cardName")), GlobalVariable.G_timeoutLong,flowControl)
		Mobile.takeScreenshot()
		'Tap On Edited Card'
		MobileDriver.tap(commonPage.lblDynamic(dataCardEdit.get("cardName")), GlobalVariable.G_timeoutLong, flowControl)
		commonPage.waitLoading()
		'Verify Card name'
		verifyElementExist(commonPage.lblContainDynamic(dataCardEdit.get("cardName")), GlobalVariable.G_timeoutMedium, flowControl)
		Mobile.takeScreenshot()
		'Enter Card Number'
		verifyElementExist(commonPage.lblContainDynamic(dataCardEdit.get("cardNumber")), GlobalVariable.G_timeoutMedium, flowControl)
		Mobile.takeScreenshot()
		'Enter Expiry Date'
		verifyElementExist(commonPage.lblContainDynamic(dataCardEdit.get("cardEx")), GlobalVariable.G_timeoutMedium, flowControl)
		Mobile.takeScreenshot()
		'Enter Description'
		verifyElementExist(commonPage.lblContainDynamic(dataCardEdit.get("cardDes")), GlobalVariable.G_timeoutMedium, flowControl)
		Mobile.takeScreenshot()
	}
}
