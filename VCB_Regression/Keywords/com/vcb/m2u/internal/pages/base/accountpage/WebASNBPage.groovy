package com.vcb.m2u.internal.pages.base.accountpage

import org.openqa.selenium.Keys

import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.vcb.common.WebUIKeyword
import com.vcb.m2u.internal.pages.base.HTMLTag
import com.vcb.m2u.internal.pages.base.WebBasePage
import com.vcb.m2u.internal.pages.base.WebCommonPage

import internal.GlobalVariable

public class WebASNBPage extends WebBasePage {

	private static final String ASNB = 'ASNB'
	private static final String TRANSFER_TO = 'Transfer To'

	private static final String PRODUCT = 'Product'

	private static final String ASNB_NUMBER = 'asnbNumber'

	private static final String ID_TYPE = 'ID Type'

	private static final String MEMBER_S_ID_NUMBER = "Member's ID Number"

	private static final String MEMBER_S_ID_NUMBER_DOT = "Member's ID Number."

	private static final String TRANSFER_AMOUNT = "Transfer Amount"

	private static final String RELATIONSHIP = 'Relationship'

	private static final String PLEASE_SELECT = 'Please Select'

	private static final String SAVINGS = 'Savings'

	private static final String MEMBER_S_ID = "Member's ID"

	private static final String ADD = 'ADD'

	private static final String REMOVE_FAVOURITE = 'Remove Favourite'

	private static final String RECIPIENT_MENU_CLASS = 'Recipient---menu'

	private static final String CHECKBOX_CLASS = 'TransferModal---radioButton'

	private static final String BUTTON_CLASS = 'TransferModal---pay-btn-text'

	private static final String PAYMENT_INPUT_CLASS = 'PayFromToContainer---paymentInput'
		
	WebCommonPage webCommonPage = getPage(WebCommonPage)

	void clickASNB() {
		WebUIKeyword.scrollToTestObject(webCommonPage.lblNormalizeSpace(ASNB, 1, HTMLTag.h6));
		WebUIKeyword.delay(GlobalVariable.G_timeoutMiniShort);
		WebUIKeyword.clickWithoutScrollW3C(webCommonPage.lblNormalizeSpace(ASNB, 1, HTMLTag.h6));
	}

	void verifyASNBPage(full_ASNB) {
		WebUIKeyword.verifyElementVisible(webCommonPage.lblNormalizeSpace(full_ASNB, 1, HTMLTag.span));
	}

	void selectASNBAccounts(asnbAccount) {
		webCommonPage.selectDropdown(TRANSFER_TO, asnbAccount)
	}
	
	void clickNewTransfer() {
		WebUIKeyword.click(webCommonPage.lblContainClass(PAYMENT_INPUT_CLASS, 1))
	}
	
	void fillForm(product, asnbNumber, idType, memberId, transferAmount, relationship) {
		WebUIKeyword.delay(GlobalVariable.G_timeoutMiniShort)
		webCommonPage.selectDropdown(PRODUCT, product)
		WebUI.setText(webCommonPage.getTestObjectById(ASNB_NUMBER), asnbNumber)
		webCommonPage.selectDropdown(ID_TYPE, idType)
		webCommonPage.inputTextForLabel(MEMBER_S_ID_NUMBER, memberId)
		webCommonPage.inputTextForLabel(TRANSFER_AMOUNT, transferAmount)
		webCommonPage.selectDropdown(RELATIONSHIP, relationship)
		WebUIKeyword.click(webCommonPage.lblNormalizeSpace(PLEASE_SELECT, 3, HTMLTag.span))
		WebUIKeyword.click(webCommonPage.lblNormalizeSpace(SAVINGS, 1, HTMLTag.span))
		WebUIKeyword.click(webCommonPage.lblContainClass(CHECKBOX_CLASS, 1))
		WebUIKeyword.click(webCommonPage.lblContainClass(BUTTON_CLASS, 1))
	}

	void clickButtonAddNewASNBFavourite(addFaroriteButton) {
		WebUIKeyword.click(webCommonPage.lblContainClassAndText(webCommonPage.BTN_CLASS, addFaroriteButton))
	}

	void clickButtonAddavourite() {
		WebUIKeyword.click(webCommonPage.lblContainClassAndText(webCommonPage.BTN_CLASS, ADD))
	}

	void fillFavoriteForm(nickname, product, idType, memberId, asnbNumber) {
		WebUIKeyword.delay(GlobalVariable.G_timeoutMiniShort)
		webCommonPage.inputTextForLabel('Nickname', nickname)
		webCommonPage.selectDropdown(PRODUCT, product)
		webCommonPage.selectDropdown(MEMBER_S_ID, idType)
		webCommonPage.inputTextForLabel(MEMBER_S_ID_NUMBER_DOT, memberId)
		WebUI.setText(webCommonPage.getTestObjectById(ASNB_NUMBER), asnbNumber)
	}

	void verifyAddFavoriteSuccessfully(successfulMessage) {
		WebUIKeyword.verifyElementVisible(webCommonPage.lblNormalizeSpace(successfulMessage, 1, HTMLTag.h4))
	}
	
	
	void clickRemoveFavourite() {
		WebUI.sendKeys(null, Keys.chord(Keys.PAGE_DOWN))
		WebUIKeyword.delay(GlobalVariable.G_timeoutMiniShort);
		WebUIKeyword.clickWithoutScrollW3C(webCommonPage.lblContainClass(RECIPIENT_MENU_CLASS, 1))
		WebUIKeyword.delay(GlobalVariable.G_timeoutMiniShort);
		WebUIKeyword.clickWithoutScrollW3C(webCommonPage.lblNormalizeSpace(REMOVE_FAVOURITE, 1, HTMLTag.a))
		WebUIKeyword.delay(GlobalVariable.G_timeoutMiniShort);
		WebUIKeyword.clickWithoutScrollW3C(webCommonPage.lblContainClassAndText(BUTTON_CLASS, 'REMOVE FAVOURITE'))
		WebUIKeyword.delay(GlobalVariable.G_timeoutMiniShort);
		WebUIKeyword.takeScreenshot()
	}
}
