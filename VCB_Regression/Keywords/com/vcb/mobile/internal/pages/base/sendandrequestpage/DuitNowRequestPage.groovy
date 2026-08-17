package com.vcb.mobile.internal.pages.base.sendandrequestpage
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import static org.assertj.core.api.Assertions.assertThatIllegalStateException

import javax.swing.text.MaskFormatter
import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testobject.TestObject
import com.vcb.mobile.internal.pages.base.BasePage
import com.vcb.mobile.internal.pages.base.CommonPage
import com.vcb.mobile.internal.pages.base.NotificationHomePage
import com.vcb.mobile.internal.pages.base.paybill.JomPayPage
import com.vcb.mobile.internal.pages.base.transferpage.TransferPage
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import com.vcb.assertions.BaseAssert
import com.vcb.common.Android
import com.vcb.common.MobileDriver
import internal.GlobalVariable

public abstract class DuitNowRequestPage extends BasePage{
	String requestViaField = 'Request via'
	static String ForwardRequestViaField = 'Forward request via'
	String mobileNumberFiled = 'Mobile Number'
	String enterRecipientReferenceField = 'Recipient reference'
	String continueField = 'Continue'
	String requestAmountField = 'Request amount'
	String enterAmountField = 'Enter amount'
	String sendNowField = 'Send Now'
	String approveField = 'Approve'
	String rejectField = 'Reject'
	String doneField = 'Done'
	final static String PAY_NOW = 'Pay Now'
	final static String FORWARD_NOW = 'Forward Now'
	final static String CONFIRM = 'Confirm'
	final static String CANCEL = 'Cancel'

	String transferSuccessfulField = 'Request successful'
	String authorisationFailedField = 'Authorisation failed'
	String statusExpiredField = 'Secure2u has expired. Please perform the transaction again.'
	String UIAddToFavourites = 'Add to Favourites'
	String MsgExistAddFavourite = 'Record already exists.'
	String SuccessAddFavourite = 'successfully added to Favourites.'

	public static CommonPage commonPage = getPage(CommonPage)
	public static NotificationHomePage notificationPage = getPage(NotificationHomePage)
	public static TransferPage transferPage = getPage(TransferPage)
	public static JomPayPage jomPayPage = BasePage.getPage(JomPayPage)
	abstract TestObject drpRequestVia()
	abstract TestObject imgSearchFav()
	abstract TestObject txtSearchFav()
	abstract TestObject btnViewAllDuitNowRequest()
	abstract TestObject itemDuitNowRequest()

	void tapTapNow(FailureHandling flowControl = defaultFailureHandling) {
		MobileDriver.tap(commonPage.lblDynamic(PAY_NOW), GlobalVariable.G_timeoutLong, flowControl)
		delay(GlobalVariable.G_timeoutMiniShort)
	}

	void tapForwardNow(FailureHandling flowControl = defaultFailureHandling) {
		MobileDriver.tap(commonPage.lblDynamic(FORWARD_NOW), GlobalVariable.G_timeoutLong, flowControl)
	}
	/**
	 * selectRequestVia: Select request Via Option
	 * @return
	 */
	void selectRequestVia(String requestViaOpt, int timeout = GlobalVariable.G_timeoutLong, FailureHandling flowControl = defaultFailureHandling) {
		Mobile.waitForElementPresent(commonPage.lblDynamic(requestViaField), timeout, flowControl)
		Mobile.delay(5)
		MobileDriver.verifyElementVisible(commonPage.lblDynamic(requestViaField), GlobalVariable.G_timeoutLong, flowControl)
		MobileDriver.tap(this.drpRequestVia(), GlobalVariable.G_timeoutLong, flowControl)
		MobileDriver.verifyElementVisible(commonPage.lblDynamic(doneField), GlobalVariable.G_timeoutLong, flowControl)
		Mobile.scrollToText(requestViaOpt)
		MobileDriver.tap(commonPage.lblDynamic(requestViaOpt), GlobalVariable.G_timeoutLong, flowControl)
		MobileDriver.tap(commonPage.lblDynamic(doneField), GlobalVariable.G_timeoutLong, flowControl)
		Mobile.delay(3)
	}


	void selectRequestViaDropdown(String requestViaOpt, FailureHandling flowControl = defaultFailureHandling) {
		commonPage.inputDropdownMapDefault([(this.ForwardRequestViaField): requestViaOpt], flowControl)
	}

	/**
	 * inputNumberRequestVia: input Number Request via
	 * @return
	 */
	void inputNumberRequestVia(String requestNumber, int timeout = GlobalVariable.G_timeoutLong, FailureHandling flowControl = defaultFailureHandling) {
		Mobile.waitForElementPresent(commonPage.lblDynamic(mobileNumberFiled), timeout, flowControl)
		commonPage.verifyTextVisible(mobileNumberFiled, GlobalVariable.G_timeoutLong, flowControl)
		MobileDriver.setText(commonPage.txtEditNo(1), requestNumber, GlobalVariable.G_timeoutLong, flowControl)
	}

	/**
	 * inputRecipientReference: input Recipient Reference
	 * @return
	 */
	String inputRecipientReference(String recipientReference = '', int timeout = GlobalVariable.G_timeoutLong, FailureHandling flowControl = defaultFailureHandling) {
		Mobile.waitForElementPresent(commonPage.lblDynamic(enterRecipientReferenceField), timeout, flowControl)
		commonPage.verifyTextVisible(enterRecipientReferenceField, GlobalVariable.G_timeoutLong, flowControl)
		'If recipientReference is empty then input ramdon'
		if(recipientReference.equals('')) {
			Random rand = new Random()
			recipientReference = ('Recipient Ref ' + Integer.toString(rand.nextInt(1000)))
		}

		MobileDriver.setText(commonPage.txtEditNo(2), recipientReference, GlobalVariable.G_timeoutLong, flowControl)
		Mobile.takeScreenshot()
		return recipientReference
	}

	/**
	 * tapContinueButton: tap Continue Button
	 * @return
	 */
	void tapContinueButton(FailureHandling flowControl = defaultFailureHandling) {
		Mobile.waitForElementPresent(commonPage.lblDynamic(continueField), GlobalVariable.G_timeoutLong, flowControl)
		commonPage.tapText(continueField, GlobalVariable.G_timeoutLong, flowControl)
		delay(GlobalVariable.G_timeoutMiniShort)
	}

	/**
	 * inputRequestAmount: Input Amount
	 * @return
	 */
	void inputRequestAmount(String amount, FailureHandling flowControl = defaultFailureHandling) {
		Mobile.waitForElementPresent(commonPage.lblDynamic(requestAmountField), GlobalVariable.G_timeoutLong, flowControl)
		commonPage.verifyTextVisible(requestAmountField, GlobalVariable.G_timeoutMedium, flowControl)
		Mobile.delay(5)
		commonPage.enterNO(amount)
	}

	/**
	 * tapSendNowButton: Tap Send Now Button
	 * @return
	 */
	void tapSendNowButton(FailureHandling flowControl = defaultFailureHandling) {
		Mobile.waitForElementPresent(commonPage.lblDynamic(sendNowField), GlobalVariable.G_timeoutLong, flowControl)
		commonPage.tapText(sendNowField, GlobalVariable.G_timeoutLong, flowControl)
	}

	/**
	 * selectApproval: Select Authorize(Approve, Reject, Expired)
	 * @return
	 */
	void selectApproval(String status, int timeout = GlobalVariable.G_timeoutLong, FailureHandling flowControl = defaultFailureHandling) {
		Mobile.waitForElementPresent(commonPage.lblDynamic(approveField), timeout)

		Mobile.delay(5)

		switch(status) {
			case 'Expired':
				MobileDriver.waitForElementPresent(commonPage.lblDynamic(doneField), 60 + GlobalVariable.G_timeoutLong, flowControl)
				break
			case 'Reject':
				MobileDriver.tap(commonPage.lblDynamic(rejectField), 60 + GlobalVariable.G_timeoutLong, flowControl)
				break
			default:
				MobileDriver.tap(commonPage.lblDynamic(approveField), GlobalVariable.G_timeoutLong, flowControl)
				info(status + ' is tapped.')
				break
		}
		MobileDriver.delay(5)
	}

	/**
	 * verifyDuitNowRequestStatusUI: Verify status screen 
	 * @return
	 */
	void verifyDuitNowRequestStatusUI(String status, FailureHandling flowControl = defaultFailureHandling) {
		Mobile.takeScreenshot()
		Mobile.waitForElementPresent(commonPage.lblDynamic(doneField), GlobalVariable.G_timeoutLong, flowControl)
		Mobile.delay(GlobalVariable.G_timeoutShort)
		switch (status) {
			case 'Approve':
				verifyDuitNowRequestSuccessful();
				break
			case 'Reject':
				verifyDuitNowRequestRejected()
				break
			case 'Expired':
				verifyDuitNowRequestDeclined()
				break
		}
	}

	/**
	 * verifyDuitNowRequestSuccessful: Verify status screen is Successful
	 * @return
	 */
	void verifyDuitNowRequestSuccessful(FailureHandling flowControl = defaultFailureHandling)  {
		Mobile.waitForElementPresent(commonPage.lblDynamic(transferSuccessfulField), GlobalVariable.G_timeoutLong, flowControl)
		commonPage.verifyTextVisible(transferSuccessfulField, GlobalVariable.G_timeoutLong, flowControl)
	}

	/**
	 * verifyDuitNowRequestDeclined: Verify status screen is Expired
	 * @return
	 */
	void verifyDuitNowRequestDeclined(FailureHandling flowControl = defaultFailureHandling)  {
		Mobile.waitForElementPresent(commonPage.lblDynamic(statusExpiredField), GlobalVariable.G_timeoutLong, flowControl)
		commonPage.verifyTextVisible(statusExpiredField, GlobalVariable.G_timeoutLong, flowControl)
	}

	/**
	 * verifyDuitNowRequestRejected: Verify status screen is Rejected
	 * @return
	 */
	void verifyDuitNowRequestRejected(FailureHandling flowControl = defaultFailureHandling)  {
		Mobile.waitForElementPresent(commonPage.lblDynamic(authorisationFailedField), GlobalVariable.G_timeoutLong, flowControl)
		commonPage.verifyTextVisible(authorisationFailedField, GlobalVariable.G_timeoutLong, flowControl)
	}

	/**
	 * backToEntryPoint: tap Done Button and back to Home
	 * @return
	 */
	void backToEntryPoint(FailureHandling flowControl = defaultFailureHandling)  {
		Mobile.waitForElementPresent(commonPage.lblDynamic(doneField), GlobalVariable.G_timeoutLong, flowControl)
		Mobile.delay(GlobalVariable.G_timeoutShort)
		MobileDriver.tap(commonPage.lblDynamic(doneField), GlobalVariable.G_timeoutLong, flowControl)
	}

	/**
	 * enterRequestData: enter data of Request Screen base on type
	 * @return
	 */
	void enterRequestData(String requestType, String dropdownValue, String inputTextValue, int timeout = GlobalVariable.G_timeoutLong, FailureHandling flowControl = defaultFailureHandling) {
		switch (requestType) {
			case 'Bank/e-Wallet Account Number':
				MobileDriver.tap(commonPage.lblDynamic('Please Select'), GlobalVariable.G_timeoutLong, flowControl)
				Mobile.scrollToText(dropdownValue)
				MobileDriver.tap(commonPage.lblDynamic(dropdownValue), timeout)
				MobileDriver.tap(commonPage.lblDynamic(doneField), timeout, flowControl)
				break
			case 'Passport Number':
				commonPage.selectDropdownSiblingGroupItem("Country", dropdownValue, timeout, flowControl)
				break
		}
		MobileDriver.setText(commonPage.txtEditNo(1), inputTextValue, timeout, flowControl)
		MobileDriver.takeScreenshot()
	}

	/**
	 * verifySentRequestNotificationMessage: Verify title, description request to pay in Notification(Device Sent Request)
	 * @return
	 */
	boolean verifySentRequestNotificationMessage(String reference, double amount, String title, String startMessage, FailureHandling flowControl = defaultFailureHandling) {
		String formattedAmount = String.format("%.2f", amount)
		String messagePN = "${startMessage} to .+ \\(RM ${formattedAmount} for ${reference}\\). Did not perform\\? Call .+. REF: .+"

		'Verify devide Notification'
		commonPage.verifyDeviceNotification(title, startMessage, messagePN)

		'Verify Notification'
		notificationPage.verifyNotification(title, startMessage, messagePN)
	}

	/**
	 * verifyReceivedRequestNotificationMessage: Verify title, description request to pay in Notification(Device Received Request)
	 * @return
	 */
	boolean verifyReceivedRequestNotificationMessage(String title, String startMessage, FailureHandling flowControl = defaultFailureHandling) {
		String messagePN = "${startMessage} .+"

		'Verify Notification'
		notificationPage.verifyNotification(title, startMessage, messagePN)
	}

	/**
	 * verifySearchFavourites: Search Favourites
	 * @return
	 */
	boolean verifySearchFavourites(String txt, boolean isExist = true, int timeout = GlobalVariable.G_timeoutLong, FailureHandling flowControl = defaultFailureHandling) {
		'Select search'
		MobileDriver.tap(this.imgSearchFav(), timeout, flowControl)

		'Enter text'
		MobileDriver.setText(commonPage.txtEditNo(1), txt, GlobalVariable.G_timeoutMedium, flowControl)

		Mobile.delay(GlobalVariable.G_timeoutShort)
		String formatedTxt = formatOrKeepOriginal(txt)
		'Check Favourites is exist'
		if(isExist) {
			MobileDriver.verifyElementVisible(transferPage.lblSearchItem(txt, formatedTxt), timeout, flowControl)
			return true
		}

		MobileDriver.verifyElementNotVisible(transferPage.lblSearchItem(txt, formatedTxt), timeout, flowControl)
		return true
	}

	/**
	 * tapFirstSearchValue: tap item after searching favourite
	 * @return
	 */
	void tapFirstSearchValue(String password, String searchValue , int timeout = GlobalVariable.G_timeoutLong, FailureHandling flowControl = defaultFailureHandling) {
		String formattedString = formatOrKeepOriginal(searchValue)
		'Select first search result'
		MobileDriver.tap(transferPage.lblSearchItem(searchValue, formattedString), timeout, flowControl)

		if(MobileDriver.waitForElementPresent(transferPage.lblSearchItem(searchValue, formattedString), GlobalVariable.G_timeoutShort, FailureHandling.OPTIONAL)) {
			MobileDriver.tap(transferPage.lblSearchItem(searchValue, formattedString), timeout, flowControl)
		}

		Mobile.delay(GlobalVariable.G_timeoutShort)

		'Check enter password'
		if(waitForElementPresent(commonPage.lblDynamic(commonPage.UIEnterYourPassword), GlobalVariable.G_timeoutShort, FailureHandling.OPTIONAL)) {
			MobileDriver.setEncryptedText(commonPage.txtEditNo(2), password, GlobalVariable.G_timeoutLong)
			MobileDriver.tap(commonPage.lblDynamic(commonPage.UIContinue), GlobalVariable.G_timeoutLong)
			Mobile.delay(GlobalVariable.G_timeoutShort)
		}
	}

	/**
	 * enterAmount: enter amount
	 * @return
	 */
	void enterAmount(String amount, FailureHandling flowControl = defaultFailureHandling) {
		'Wait for Enter amount present'
		Mobile.waitForElementPresent(commonPage.lblDynamic(enterAmountField), GlobalVariable.G_timeoutLong, flowControl)
		commonPage.verifyTextVisible(enterAmountField, GlobalVariable.G_timeoutMedium, flowControl)
		Mobile.delay(GlobalVariable.G_timeoutShort)

		'Enter amount'
		commonPage.enterNO(amount)
	}

	/**
	 * tapViewAllDuitNowRequest: tap View All Duit Now Request Button
	 * @return
	 */
	void tapViewAllDuitNowRequest(int timeout = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		if(MobileDriver.waitForElementPresent(this.btnViewAllDuitNowRequest(), GlobalVariable.G_timeoutMedium)) {
			'Select view all DuitNow Request'
			MobileDriver.tap(this.btnViewAllDuitNowRequest(), timeout, flowControl)
		}
	}

	/**
	 * verifyDuitNowRequestItemDetail: Verify UI DuitNow Request Item Detail Screen
	 * @return
	 */
	void verifyDuitNowRequestItemDetail(int timeout = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		'Verify Requested on is exist'
		MobileDriver.verifyElementExist(commonPage.lblDynamic("Requested on"), timeout)

		'Verify Recipient reference is exist'
		MobileDriver.verifyElementExist(commonPage.lblDynamic("Recipient reference"), timeout)
	}


	void selectItemInThreeDot(String itemName, FailureHandling flowControl = defaultFailureHandling) {
		MobileDriver.tap(commonPage.imgNoDynamic(2), GlobalVariable.G_timeoutMedium, flowControl)
		MobileDriver.tap(commonPage.lblDynamic(itemName), GlobalVariable.G_timeoutMedium, flowControl)
	}

	/**
	 * addToFavourites: Add Favourites in Favourites screen
	 * @return
	 */
	void addToFavourites(String favName ='Fav',FailureHandling flowControl = defaultFailureHandling) {
		'Add to Favourites'
		MobileDriver.tap(commonPage.lblDynamic(UIAddToFavourites), GlobalVariable.G_timeoutLong, flowControl)
		Mobile.delay(GlobalVariable.G_timeoutShort)
		'Enter Favourite Name'
		MobileDriver.setText(commonPage.txtEditNo(1), favName, GlobalVariable.G_timeoutLong, flowControl)

		'Select Add to Favourites'
		MobileDriver.tap(commonPage.lblDynamic(UIAddToFavourites), GlobalVariable.G_timeoutLong, flowControl)

		'Get message'
		String msg = MobileDriver.getText(jomPayPage.lblMessage(), GlobalVariable.G_timeoutLong, flowControl)

		if(msg != null) {
			if (msg == MsgExistAddFavourite) {
				'Verify msg equal MsgExistAddFavourite'
				Mobile.verifyEqual(msg, MsgExistAddFavourite)
			}else {
				'Verify msg equal SuccessAddFavourite'
				Mobile.verifyEqual(msg, favName + ' ' + SuccessAddFavourite)
			}
		}
		'Verify Add to Favourites disappear'
		Mobile.verifyElementNotExist(commonPage.lblDynamic(UIAddToFavourites), GlobalVariable.G_timeoutShort)
	}

	public static String formatOrKeepOriginal(String input) {
		try {
			MaskFormatter formatter = new MaskFormatter("AAA AAAA AAA");
			formatter.setValueContainsLiteralCharacters(false);
			return formatter.valueToString(input);
		} catch (Exception e) {
			return input;
		}
	}

	/*
	 * List view item click one item
	 * duitnowType: incoming,..
	 */
	void selectDuitRequestItem(String duitnowType, FailureHandling flowControl = defaultFailureHandling) {
		this.tapViewAllDuitNowRequest(GlobalVariable.G_timeoutMedium, FailureHandling.OPTIONAL)
		Mobile.scrollToText(duitnowType)
		MobileDriver.tap(commonPage.lblDynamic(duitnowType), GlobalVariable.G_timeoutMedium, flowControl)
	}
	/*
	 * verify Block Requestor
	 */
	void verifyPopupThreeDot(List popupData, String successMsg = '', FailureHandling flowControl = defaultFailureHandling) {
		verifyElements(popupData, commonPage.lblDynamicPath(), GlobalVariable.G_timeoutMedium, flowControl)

		'Tap Confirm'
		MobileDriver.tap(commonPage.lblDynamic(CONFIRM), GlobalVariable.G_timeoutMedium, flowControl)
		if(successMsg) {
			'Verify Successful'
			MobileDriver.verifyElementVisible(commonPage.lblDynamic(successMsg), GlobalVariable.G_timeoutMedium, flowControl)
		}else {
			MobileDriver.tap(commonPage.lblDynamic(CANCEL), GlobalVariable.G_timeoutMedium, flowControl)
		}
	}
}
