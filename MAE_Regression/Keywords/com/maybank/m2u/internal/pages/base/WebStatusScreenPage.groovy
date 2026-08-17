package com.maybank.m2u.internal.pages.base

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
import com.maybank.common.WebUIKeyword
import com.maybank.mae.internal.pages.base.Secure2uAuthorisationPage
import com.maybank.m2u.internal.constants.WebCommonConstants as WCC

import internal.GlobalVariable

public class WebStatusScreenPage extends WebBasePage {

	WebCommonPage webCommonPage = getPage(WebCommonPage)
	WebHomePage webHomePage = WebBasePage.getPage(WebHomePage)
	WebSecure2uAuthorisationPage webSecure2uAuthorisationPage = getPage(WebSecure2uAuthorisationPage)

	static final String ADD_TO_FAVOURITE = 'Add to Favourite'
	static final String ADD = 'ADD'
	static final String ADD_NEW_FAVOURITE = 'ADD NEW FAVOURITE'
	static final String ADD_FAVOURITE = 'Add Favourite'
	static final String CONFIRM = 'CONFIRM'
	static final String APPROVE_STATUS = 'Approve'
	static final String TABNAME = 'ACCOUNTS'
	static final String NAVITEM = 'MY ACCOUNTS'

	static TestObject lblConfirmInfo(String text, def index = 1) {
		return findTestObject('Object Repository/Website/M2U/Browsers/Common/lblConfirmInfo', ['text': text, 'index': index.toString()])
	}

	void clickConfirmButton(FailureHandling flowControl = defaultFailureHandling) {
		WebUIKeyword.waitForElementPresent(webCommonPage.btnNormalizeSpaceDynamic(CONFIRM), GlobalVariable.G_timeoutLong, flowControl)
		'Click Request Button'
		WebUIKeyword.click(webCommonPage.btnNormalizeSpaceDynamic(CONFIRM), flowControl)
	}

	/**
	 * Verify Status Screen
	 */
	void verifyStatusScreen(List verifyUIData, ElementVerificationType type = ElementVerificationType.Contains, FailureHandling flowControl = defaultFailureHandling) {
		WebUIKeyword.takeScreenshot()
		'Verify UI'
		webCommonPage.verifyAnyElementListVisible(verifyUIData, GlobalVariable.G_timeoutMedium, flowControl, type)
	}

	void clickDoneButton() {
		click(webCommonPage.btnChildLabel('DONE'))
	}
	
	void verifyTxnHistory(String type = "IC/Passport No./Biz Registration No.", String accountType, String dynamicText = '', FailureHandling flowControl = defaultFailureHandling) {
	    // Close toast message if run this method after adding a favorite
		if (WebUI.waitForElementVisible(webCommonPage.lblContainClass(WCC.TOAST_MESSAGE_CONTAINER_CLOSE_CLASS), GlobalVariable.G_timeoutShort)) {
		click(webCommonPage.lblContainClass(WCC.TOAST_MESSAGE_CONTAINER_CLOSE_CLASS))
	    }
		
		// Get text of type variable
	    String confirmInfo = getText(lblConfirmInfo(type), FailureHandling.OPTIONAL)
		
		// Navigate to My Accounts - Personal Current Account wallet
	    webCommonPage.scrollToTestObject(webHomePage.lnkNavigationBar(NAVITEM))
	    webHomePage.navigate(NAVITEM, '')
	    WebUIKeyword.click(webCommonPage.lblNormalizeSpace(accountType, 1, HTMLTag.span), flowControl)
		
		// Verify transaction history includes confirmInfo or/and dynamicText  
	    boolean confirmInfoPassed = false
	    boolean dynamicTextPassed = false
	
	    // Try verify confirmInfo
	    try {
	        if (confirmInfo) {
	            verifyElementVisible(webCommonPage.lblIgnoreCaseContains(confirmInfo, HTMLTag.span), FailureHandling.OPTIONAL)
	            confirmInfoPassed = true
				WebUI.comment("✅ the value of ${type} is found: " + confirmInfo)
	        }
	    } catch (Exception e) {
	        // ignore, will check dynamicText next
	    }
	
	    // Try verify dynamicText
	    try {
	        if (dynamicText) {
	            verifyElementVisible(webCommonPage.lblIgnoreCaseContains(dynamicText, HTMLTag.span), FailureHandling.OPTIONAL)
	            dynamicTextPassed = true
				WebUI.comment("✅ the value of dynamicText is found: " + dynamicText)
	        }
	    } catch (Exception e) {
	        // ignore
	    }
	
	    if (!(confirmInfoPassed || dynamicTextPassed)) {
	        handleError('❌ Neither confirmInfor nor dynamicText is visible on the screen', flowControl)
	    }
	}

	/**
	 * Adds an item to the favorites list using the provided data and verifies the resulting toast messages.
	 *
	 * @param addToFavouriteData  a map containing the data required to add the item to favorites
	 * @param toastMessageAfterAdd (optional) a list of expected toast messages to validate after adding the item,
	 *                             defaults to an empty list
	 * @param flowControl          (optional) defines the flow control behavior in case of failure,
	 *                             defaulting to the global `defaultFailureHandling`
	 *
	 * @return void
	 */
	void addToFavourite(Map addToFavouriteData, List toastMessageAfterAdd = [], String s2uStatus = '', FailureHandling flowControl = defaultFailureHandling) {
		'Add Favourite Exisit'
		if(!WebUI.waitForElementPresent(webCommonPage.btnNormalizeSpaceDynamic(ADD_TO_FAVOURITE), GlobalVariable.G_timeoutShort, FailureHandling.OPTIONAL)) {
			return
		}
		'Click Add To Favourite Button'
		WebUIKeyword.click(webCommonPage.btnNormalizeSpaceDynamic(ADD_TO_FAVOURITE), flowControl)
		WebUIKeyword.delay(GlobalVariable.G_timeoutMiniShort)
		'input data'
		webCommonPage.inputTextMap(addToFavouriteData)
		'Add Click Button'
		WebUIKeyword.click(webCommonPage.lblDynamicList([
			ADD,
			ADD_NEW_FAVOURITE,
			ADD_FAVOURITE
		]), flowControl)
		WebUIKeyword.delay(GlobalVariable.G_timeoutMiniShort)

		if(s2uStatus != '') {
			webSecure2uAuthorisationPage.authorize(s2uStatus)
		}else {
			'Click Confirm Button'
			this.clickConfirmButton(flowControl)
		}
		WebUIKeyword.delay(GlobalVariable.G_timeoutMiniShort)
		'Verify Toast Msg'
		webCommonPage.verifyAnyElementListVisible(toastMessageAfterAdd, GlobalVariable.G_timeoutShort, flowControl, ElementVerificationType.Contains)
		WebUIKeyword.delay(GlobalVariable.G_timeoutMiniShort)
		webCommonPage.clickDoneButton()
	}

	/**
	 * Method to verify transaction success based on balance comparison.
	 *
	 * @param originAmt The original balance before transaction
	 * @param newAmt The balance after transaction
	 */
	def verifyTransactionSuccess(Double originAmt, Double newAmt) {
		// Assert to verify that balance has been deducted
		assert originAmt > newAmt : "Transaction failed or balance was not deducted."

		// If assertion passes, log success message
		WebUI.comment("Transaction success. Balance was deducted.")
	}
}
