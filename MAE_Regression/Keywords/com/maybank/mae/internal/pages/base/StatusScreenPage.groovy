package com.maybank.mae.internal.pages.base

import org.openqa.selenium.By

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
import com.maybank.assertions.BaseAssert
import com.maybank.common.MobileDriver
import com.maybank.utils.RegexUtils

import internal.GlobalVariable

public abstract class StatusScreenPage extends BasePage {

	static final String REFERENCE_ID_9_M = '[0-9]{9}M'
	abstract TestObject fraShareReceiptWebView()
	String Done = 'Done'
	String ShareReceipt = 'Share Receipt'
	String AddToFavourites = 'Add to Favourites'
	String AddedToFavourites = 'Added to Favourites'
	String TransferSuccessful = 'Transfer successful'
	String TransferScheduled = 'Transfer Scheduled'
	String TransferScheduledlowercase = 'Transfer scheduled'
	String TransferAccepted = 'Transfer accepted'
	String TransferDeclined = 'Transfer Declined'
	String TransferFailed = 'Transfer failed'
	String TransactionDeclined = 'Transaction Declined'
	String WrongTACEntered = 'Wrong TAC entered'
	String AuthorisationFailed = 'Authorisation failed'

	//Payment
	String PaymentSuccessful = 'Payment successful'

	//Transaction
	String TransactionSuccessful = 'Transaction successful'

	String subStatusRecurringDetails = 'You can view and manage your ‘Transfer Future & Recurring’ in M2U web’s Settings.'
	String subStatusExpired = 'Secure2u has expired. Please perform the transaction again.'
	String subStatusExpiredTwoMsg = 'Your Secure Verification authorisation has expired. Please perform the transaction again.'
	String subStatusFailAmountExceedsMaximunTransaction = 'Amount exceeds maximun transaction limit.'

	String ReferenceIDUI = 'Reference ID'
	String Date = 'Date'
	String DateTime = 'Date & Time'
	String Datetime = 'Date & time'
	String Successful = 'Successful'
	String Pending = 'Pending'

	String RecordAlreadyExist = 'Record already exist'

	CommonPage commonPage = getPage(CommonPage)

	public boolean waitloadStatusScreen() {
		return MobileDriver.waitForElementPresent(commonPage.lblDynamic(Done), GlobalVariable.G_timeoutLong, FailureHandling.OPTIONAL)
	}

	/**
	 * Verify Duitnow status UI
	 * return Reference ID
	 */
	def verifyDuitnowStatusUI(String status, String futuretype, FailureHandling flowControl = defaultFailureHandling) {
		'Verify Done button is visible'
		this.StatusScreenHasLoaded()
		'Get Reference ID'
		def RefID = this.referenceID()
		'Verify Date and Time'
		this.dateAndTime()
		Mobile.takeScreenshot()
		'Verify status'
		'If status is Approve and future type is Future. Then verify Transfer Scheduled'
		'If status is Approve and future type is Recurring. Then verify Recurring Details'
		'If status is Approve and future type is not Future or Recurring. Then verify Transfer Successful or Transfer Accepted'
		'If status is Reject. Then verify Authorisation Failed'
		'If status is Expired. Then verify Secure2u Expired'
		'If status is Insufficient Amount. Then verify Transfer Failed'
		switch (status) {
			case 'Approve':
				switch (futuretype) {
					case 'Future':
					this.verifyTransferScheduled()
					break
					case 'Recurring':
					this.verifyTransferScheduled()
					this.RecurringDetails()
					break
					default:
					if(waitForElementPresent(commonPage.lblDynamic(TransferSuccessful), GlobalVariable.G_timeoutMiniShort, FailureHandling.OPTIONAL)) {
						this.verifyTransferSuccessful()
					}else{
						this.verifyTransferAccepted()
					}
					break
				}
				break
			case 'Reject':
				if(MobileDriver.waitForElementPresent(commonPage.lblDynamic(AuthorisationFailed), GlobalVariable.G_timeoutMiniShort, FailureHandling.OPTIONAL)) {
					MobileDriver.verifyElementVisible(commonPage.lblDynamic(AuthorisationFailed), GlobalVariable.G_timeoutMiniShort, flowControl)
				}
				break
			case 'Expired':
				this.Secure2uExpired()
				break
			case 'Insufficient Amount':
				this.verifyTransferFailed()
				this.verifyDescriptionTransferFailedApproveStatus()
				break
		}

		return RefID
	}

	/**
	 * Verify Payment status UI
	 * return Reference ID
	 */
	def verifyPaymentStatusUI(String status, String futuretype, FailureHandling flowControl = defaultFailureHandling) {
		'Verify Done button is visible'
		this.StatusScreenHasLoaded()
		'Get Reference ID'
		def RefID = this.referenceID()
		'Verify Date and Time'
		this.dateAndTime()
		Mobile.takeScreenshot()
		'Verify status'
		'If status is Approve and future type is Future. TODO'
		'If status is Approve and future type is not Future. Then verify Payment Successful'
		'If status is Reject. Then verify Authorisation Failed'
		'If status is Expired. Then verify Secure2uExpired'
		switch (status) {
			case 'Approve':
				switch (futuretype) {
					case 'Future':
					break
					default:
					if(waitForElementPresent(commonPage.lblDynamic(PaymentSuccessful), GlobalVariable.G_timeoutMiniShort, FailureHandling.OPTIONAL)) {
						this.verifyPaymentSuccessful()
					}
					break
				}
				break
			case 'Reject':
				if(MobileDriver.waitForElementPresent(commonPage.lblDynamic(AuthorisationFailed), GlobalVariable.G_timeoutMiniShort, FailureHandling.OPTIONAL)) {
					MobileDriver.verifyElementVisible(commonPage.lblDynamic(AuthorisationFailed), GlobalVariable.G_timeoutMiniShort, flowControl)
				}else {
					this.verifyTransferDeclined()
				}
				break
			case 'Expired':
				this.Secure2uExpired()
				break
		}

		return RefID
	}

	/**
	 * Verify Transaction status UI
	 * return Reference ID
	 */
	def verifyTransactionStatus(String status, String futuretype, FailureHandling flowControl = defaultFailureHandling) {
		'Verify Done button is visible'
		this.StatusScreenHasLoaded()
		'Get Reference ID'
		def refID = this.referenceID()
		'Verify Date and Time'
		this.dateAndTime()
		Mobile.takeScreenshot()
		'Verify status'
		'If status is Approve and future type is Future. TODO'
		'If status is Approve and future type is not Future. Then verify Transaction Successful'
		switch (status) {
			case 'Approve':
				switch (futuretype) {
					case 'Future':
					break
					default:
					if(waitForElementPresent(commonPage.lblDynamic(TransactionSuccessful), GlobalVariable.G_timeoutMiniShort, FailureHandling.OPTIONAL)) {
						commonPage.verifyTextVisible(TransactionSuccessful, GlobalVariable.G_timeoutLong, flowControl)
					}else {
						commonPage.verifyTextVisible(TransactionSuccessful, GlobalVariable.G_timeoutLong, flowControl)
					}
					break
				}
				break
		}

		return refID
	}

	/**
	 * Verify Status Screen
	 * return Reference ID
	 */
	def verifyStatusScreen(List titleData, List descriptionData = [], String referenceIDRegexString = REFERENCE_ID_9_M, List otherVerifyData = [], FailureHandling flowControl = FailureHandling.CONTINUE_ON_FAILURE) {
		'Verify Done button is visible'
		this.StatusScreenHasLoaded()
		Mobile.takeScreenshot()
		'Get Reference ID'
		def refID = this.referenceID(referenceIDRegexString)
		'Verify Date and Time'
		this.dateAndTime()
		'Verify title and description'
		verifySomeElementsMatchInList(titleData, commonPage.lblContainDynamicPath(), GlobalVariable.G_timeoutShort)
		verifySomeElementsMatchInList(descriptionData, commonPage.lblContainDynamicPath(), GlobalVariable.G_timeoutShort)
		if(!otherVerifyData.isEmpty()) {
			'Verify Other UI Status Screen'
			verifyElements(otherVerifyData, commonPage.lblDynamicPath(), GlobalVariable.G_timeoutMedium, flowControl)
		}
		return refID
	}
	
	def verifyStatusScreenJomPay(List titleData, List descriptionData = [], String referenceIDRegexString = REFERENCE_ID_9_M, List otherVerifyData = [], FailureHandling flowControl = defaultFailureHandling) {
		'Verify Done button is visible'
		this.StatusScreenHasLoaded()
		Mobile.takeScreenshot()
		'Verify title and description'
		verifySomeElementsMatchInList(titleData, commonPage.lblContainDynamicPath(), GlobalVariable.G_timeoutShort)
		verifySomeElementsMatchInList(descriptionData, commonPage.lblContainDynamicPath(), GlobalVariable.G_timeoutShort)
		if(!otherVerifyData.isEmpty()) {
			'Verify Other UI Status Screen'
			verifyElements(otherVerifyData, commonPage.lblDynamicPath(), GlobalVariable.G_timeoutMedium, flowControl)
		}
		return null
	}

	/**
	 * Add Favorites Duitnow
	 */
	void addFavoritesDuitnow(String favName, boolean isOnUs) {
		'Create favorite name'
		String addFav = favName + ' ' + (isOnUs ? 'ON': 'OFF')
		'Add to favorites'
		addToFavourites(addFav)
	}

	/**
	 * Add Favorites Payment
	 */
	void verifyShareReceipt(String futureType, FailureHandling flowControl = defaultFailureHandling) {
		'Select Share Receipt'
		commonPage.tapText(ShareReceipt, GlobalVariable.G_timeoutLong, flowControl)
		Mobile.delay(GlobalVariable.G_timeoutMiniShort)

		'Wait for Share Receipt WebView'
		if(Mobile.waitForElementNotPresent(this.fraShareReceiptWebView(), GlobalVariable.G_timeoutShort, FailureHandling.STOP_ON_FAILURE)) {
			'Verify Share Receipt exist'
			verifyElements([ShareReceipt], commonPage.lblDynamicPath(), GlobalVariable.G_timeoutLong)
			'If future type is Future or Recurring. Then verify Pending'
			'Else verify Successful'
			futureType == 'Future'?
					verifyElements([Pending], commonPage.lblDynamicPath(), GlobalVariable.G_timeoutLong):
					futureType =='Recurring'?
					verifyElements([Pending], commonPage.lblDynamicPath(), GlobalVariable.G_timeoutLong):
					verifyElements([Successful], commonPage.lblDynamicPath(), GlobalVariable.G_timeoutLong)
			Mobile.takeScreenshot()
			'Swipe down'
			commonPage.swipe(5, 1000, 5, 10)
		}

		'Select Icon Share'
		MobileDriver.tapAndLog(commonPage.imgNoDynamic(2), GlobalVariable.G_timeoutLong, 'Icon Share',flowControl)
		Mobile.delay(3)
		'Select Back'
		Mobile.pressBack()
		Mobile.delay(3)
		Mobile.takeScreenshot()
		'Select Back'
		MobileDriver.tapAndLog(commonPage.imgNoDynamic(1), GlobalVariable.G_timeoutLong, 'Icon Back',flowControl)
		'Verify Done button is visible'
		this.StatusScreenHasLoaded()
	}

	/**
	 * Select Done button
	 */
	void backToEntryPoint(FailureHandling flowControl = defaultFailureHandling)  {
		MobileDriver.tap(commonPage.lblDynamic(Done), GlobalVariable.G_timeoutLong, flowControl)
	}

	/**
	 * Verify Transfer Successful visible
	 */
	void verifyTransferSuccessful(FailureHandling flowControl = defaultFailureHandling)  {
		commonPage.verifyTextVisible(TransferSuccessful, GlobalVariable.G_timeoutLong, flowControl)
	}

	/**
	 * Verify Transfer Scheduled visible
	 */
	void verifyTransferScheduled(FailureHandling flowControl = defaultFailureHandling) {
		'If Transfer Scheduled is not visible. Then verify Transfer scheduled'
		if(MobileDriver.waitForElementPresent(commonPage.lblDynamic(TransferScheduled), GlobalVariable.G_timeoutShort, FailureHandling.OPTIONAL)) {
			commonPage.verifyTextVisible(TransferScheduled, GlobalVariable.G_timeoutLong, flowControl)
		}else {
			commonPage.verifyTextVisible(TransferScheduledlowercase, GlobalVariable.G_timeoutLong, flowControl)
		}
	}

	/**
	 * Verify Transfer Accepted visible
	 */
	void verifyTransferAccepted(FailureHandling flowControl = defaultFailureHandling)  {
		commonPage.verifyTextVisible(TransferAccepted, GlobalVariable.G_timeoutLong, flowControl)
	}

	/**
	 * Verify Transfer Failed visible
	 */
	void verifyTransferFailed(FailureHandling flowControl = defaultFailureHandling) {
		commonPage.verifyTextVisible(TransferFailed, GlobalVariable.G_timeoutLong, flowControl)
	}

	/**
	 * Verify Amount exceeds maximun transaction limit. visible
	 */
	void verifyDescriptionTransferFailedApproveStatus(FailureHandling flowControl = defaultFailureHandling) {
		if(MobileDriver.waitForElementPresent(commonPage.lblDynamic(subStatusFailAmountExceedsMaximunTransaction), GlobalVariable.G_timeoutShort, FailureHandling.OPTIONAL)) {
			commonPage.verifyTextVisible(subStatusFailAmountExceedsMaximunTransaction, GlobalVariable.G_timeoutLong, flowControl)
		}
	}

	/**
	 * Verify Transfer Declined visible
	 */
	void verifyTransferDeclined(FailureHandling flowControl = defaultFailureHandling)  {
		'If Transfer Declined is not visible. Then verify Transaction Declined'
		if(MobileDriver.waitForElementPresent(commonPage.lblDynamic(TransferDeclined), GlobalVariable.G_timeoutShort, FailureHandling.OPTIONAL)) {
			commonPage.verifyTextVisible(TransferDeclined, GlobalVariable.G_timeoutLong, flowControl)
		}else {
			commonPage.verifyTextVisible(TransactionDeclined, GlobalVariable.G_timeoutLong, flowControl)
		}
	}

	/**
	 * Verify Payment Successful visible
	 */
	void verifyPaymentSuccessful(FailureHandling flowControl = defaultFailureHandling)  {
		commonPage.verifyTextVisible(PaymentSuccessful, GlobalVariable.G_timeoutLong, flowControl)
	}

	/**
	 * Get Reference ID
	 */
	String referenceID(String regexString = REFERENCE_ID_9_M, FailureHandling flowControl = defaultFailureHandling)  {
		'Wait for Reference ID visible'
		if(MobileDriver.waitForElementPresent(commonPage.lblSiblingDynamic(ReferenceIDUI), GlobalVariable.G_timeoutShort)) {
			'Get Reference ID'
			String refID = MobileDriver.getText(commonPage.lblSiblingDynamic(ReferenceIDUI), GlobalVariable.G_timeoutLong, flowControl)

			'If Reference ID contains Date. Then get Reference ID again'
			if(refID.toLowerCase().contains(this.Date.toLowerCase())) {
				refID = MobileDriver.getText(commonPage.lblSiblingOneGroup(ReferenceIDUI), GlobalVariable.G_timeoutLong, flowControl)
			}

			'Verify Format Reference ID'
			Mobile.verifyMatch(refID, regexString, true, flowControl.OPTIONAL)

			return refID
		}

		return null
	}

	/**
	 * Get Date Value Test Object
	 */
	TestObject getDateValueTestObject(String dateText, FailureHandling flowControl = defaultFailureHandling) {
		'Wait for Date visible'
		if(MobileDriver.waitForElementPresent(commonPage.lblSiblingDynamic(dateText), GlobalVariable.G_timeoutShort, FailureHandling.OPTIONAL)) {
			TestObject valueTO = commonPage.lblSiblingDynamic(dateText)
			'Get Date value'
			String date = MobileDriver.getText(commonPage.lblSiblingDynamic(dateText), GlobalVariable.G_timeoutLong, flowControl)
			def datePattern = RegexUtils.DATE_OR_TIME_REGEX
			'If Date match the pattern. Then return Date value'
			'Else get Date value again'
			if(date ==~ datePattern) {
				return valueTO
			}else {
				return commonPage.lblSiblingOneGroup(dateText)
			}
		}

		return commonPage.lblSiblingOneGroup(dateText)
	}

	/**
	 * Verify Date and Time
	 */
	void dateAndTime(FailureHandling flowControl = defaultFailureHandling)  {
		'Get Date label'
		String dateLabel = MobileDriver.getText(commonPage.lblTextMatches(Date), GlobalVariable.G_timeoutLong, flowControl)
		'If Data label is Date & Time. Then verify Date & Time format'
		'Else If Data label is Datee. Then verify Date format'
		switch(dateLabel) {
			case DateTime:
				MobileDriver.verifyMatch(getDateValueTestObject(DateTime), RegexUtils.DATE_TIME_REGEX_STRING, GlobalVariable.G_timeoutLong)
				break
			case Datetime:
			//				'(0?[1-9]|[12][0-9]|3[01]) [A-Z]{1}[a-z]{2} [0-9]{4}, (0?[0-9]|1[0-9]|2[0-3]):[0-9]{2} [A|P]M'
				MobileDriver.verifyMatch(getDateValueTestObject(Datetime), RegexUtils.DATE_TIME_REGEX_STRING, GlobalVariable.G_timeoutLong)
				break
			case Date:
				MobileDriver.verifyMatch(getDateValueTestObject(Date),'(0?[1-9]|[12][0-9]|3[01])\\s(?:Jan|Feb|Mar|Apr|May|Jun|Jul|Aug|Sep|Oct|Nov|Dec)\\s\\d{4}', GlobalVariable.G_timeoutLong)
				break
		}
	}

	/**
	 * Verify Secure2u Expired visible
	 */
	void Secure2uExpired(FailureHandling flowControl = defaultFailureHandling)  {
		'If Secure2u has expired... not visible.'
		'Then verify Your Secure... visible'
		if(MobileDriver.waitForElementPresent(commonPage.lblDynamic(subStatusExpired), GlobalVariable.G_timeoutShort, FailureHandling.OPTIONAL)) {
			commonPage.verifyTextVisible(subStatusExpired, GlobalVariable.G_timeoutLong, flowControl)
		}else {
			commonPage.verifyTextVisible(subStatusExpiredTwoMsg, GlobalVariable.G_timeoutLong, flowControl)
		}
	}

	/**
	 * Verify You can view... visible
	 */
	void RecurringDetails(FailureHandling flowControl = defaultFailureHandling)  {
		commonPage.verifyTextVisible(subStatusRecurringDetails, GlobalVariable.G_timeoutLong, flowControl)
	}

	/**
	 * Verify Status Screen has loaded
	 */
	void StatusScreenHasLoaded(FailureHandling flowControl = defaultFailureHandling)  {
		commonPage.verifyTextVisible(Done, GlobalVariable.G_timeoutLong, flowControl)
	}

	/**
	 * Add to Favourites
	 */
	void addToFavourites(String string, FailureHandling flowControl = defaultFailureHandling) {
		'Wait for Add to Favourites visible'
		if(MobileDriver.waitForElementPresent(commonPage.lblDynamic(AddToFavourites), GlobalVariable.G_timeoutShort, FailureHandling.OPTIONAL)) {
			'Select Add to Favourites'
			MobileDriver.tap(commonPage.lblDynamic(AddToFavourites), GlobalVariable.G_timeoutMiniShort, flowControl)
			'Enter Favourites name'
			commonPage.setText(commonPage.txtEditNo(1), string, GlobalVariable.G_timeoutLong, flowControl)
			MobileDriver.takeScreenshot()
			'Select Add to Favourites'
			MobileDriver.tap(commonPage.lblDynamic(AddToFavourites), GlobalVariable.G_timeoutMiniShort, flowControl)

			'Wait for toast message visible'
			'If Record already exist visible. Then verify Record already exist'
			'Else verify Added to Favourites'
			if(MobileDriver.waitForElementPresent(commonPage.lblContainDynamic(RecordAlreadyExist), GlobalVariable.G_timeoutShort, FailureHandling.OPTIONAL)) {
				MobileDriver.verifyElementVisible(commonPage.lblContainDynamic(RecordAlreadyExist), GlobalVariable.G_timeoutLong, flowControl)
			}

			'Close toast message'
			MobileDriver.tapAndLog(commonPage.imgNoDynamic('last()'), GlobalVariable.G_timeoutMedium, 'Close toast message', flowControl)
		}
	}
}
