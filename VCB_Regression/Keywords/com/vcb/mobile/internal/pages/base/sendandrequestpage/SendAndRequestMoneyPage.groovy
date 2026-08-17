package com.vcb.mobile.internal.pages.base.sendandrequestpage
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.vcb.common.MobileDriver
import com.vcb.mobile.internal.pages.base.BasePage
import com.vcb.mobile.internal.pages.base.CommonPage
import com.vcb.mobile.internal.pages.base.NotificationHomePage
import com.vcb.mobile.internal.pages.base.transferpage.TransferPage
import com.vcb.mobile.internal.pages.base.transferpage.RecipientReferencePage
import internal.GlobalVariable

public abstract class SendAndRequestMoneyPage extends BasePage{
	String requestViaField = 'Request via'
	String SEND_MONEY = "Send\nMoney"
	String REQUEST_MONEY = "Request\nMoney"
	String NOTES = 'Notes'
	String SEND_NOW = 'Send Now'
	String SEND_REQUEST = 'Send Request'
	String ERROR_NOTE = 'Note*'
	String YES = 'Yes'
	String OUTGOING = 'Outgoing'
	String INVITE_NOW = 'Invite Now'
	String NOT_MAE = 'Not on VCB?'
	String ERROR_MESS = 'Please remove invalid special characters'
	String UIlblEnterAmount = 'Enter amount'
	String UIConfirmation = 'Confirmation'
	String MAX_AMOUNT = '99999999'
	String REQUEST_SUCCESSFUL = 'Request successful'
	String REFERENCE_ID = 'Reference ID'
	String MARK_AS_PAID = 'Mark as Paid'
	String CANCEL_REQUEST = 'Cancel Request'
	String NOTE_MARK_AS_PAID = 'Request marked as paid.'
	String ERROR_LIMIT_AMT = 'Amount exceeds maximum transfer limit.'
	String editAmount = ""

	public static RecipientReferencePage recipientReferencePage = getPage(RecipientReferencePage)
	public static CommonPage commonPage = getPage(CommonPage)
	public static NotificationHomePage notificationPage = getPage(NotificationHomePage)
	public static TransferPage transferPage = getPage(TransferPage)


	/**
	 * Select tab
	 */
	void selectTab(String tabName, boolean isVisible = false, FailureHandling flowControl = defaultFailureHandling) {
		if(isVisible) {
			MobileDriver.tap(commonPage.lblDynamic(tabName), GlobalVariable.G_timeoutLong, flowControl)
			MobileDriver.takeScreenshot()
			return
		}
		commonPage.selectTab(commonPage.frmHorizontalScrollNo(1), tabName, GlobalVariable.G_timeoutMedium, flowControl)
		MobileDriver.takeScreenshot()
	}
	void processSendMoney(String mobileNumber, String amount, String note, FailureHandling flowControl = defaultFailureHandling) {
		'Tap to send money'
		MobileDriver.tap(commonPage.lblDynamic(SEND_MONEY), GlobalVariable.G_timeoutMedium, flowControl)
		MobileDriver.tap(commonPage.lblDynamic(mobileNumber), GlobalVariable.G_timeoutMedium, flowControl)
		MobileDriver.takeScreenshot()
		if(Mobile.waitForElementPresent(commonPage.lblDynamic(YES), GlobalVariable.G_timeoutMedium)) {
			MobileDriver.tap(commonPage.lblDynamic(YES), GlobalVariable.G_timeoutMedium, flowControl)
		}
		'enter limit amount and verify'
		commonPage.enterNO(MAX_AMOUNT)
		MobileDriver.verifyElementVisible(commonPage.lblDynamic(ERROR_LIMIT_AMT), GlobalVariable.G_timeoutShort)
		commonPage.tapBack()
		delay(GlobalVariable.G_timeoutShort)
		'tap to mobile again'
		MobileDriver.tap(commonPage.lblDynamic(mobileNumber), GlobalVariable.G_timeoutMedium, flowControl)
		'enter right amt'
		commonPage.enterNO(amount)
		'Recipient Reference'
		recipientReferencePage.inputRecipientReference()
		'edit and validation at confirm screen'
		verifyFieldValidationConfirmation(amount)
		MobileDriver.verifyElementVisible(commonPage.lblDynamic(NOTES), GlobalVariable.G_timeoutShort)
		delay(GlobalVariable.G_timeoutShort)
		'Get edited amount'
		editAmount = MobileDriver.getText(commonPage.lblRMNo(1), GlobalVariable.G_timeoutShort, flowControl).split(" ")[1]
		'validate special character'
		MobileDriver.setText(commonPage.txtEditNo(1), ERROR_NOTE, GlobalVariable.G_timeoutLong, flowControl)
		MobileDriver.tap(commonPage.lblDynamic(SEND_NOW), GlobalVariable.G_timeoutMedium, flowControl)
		MobileDriver.verifyElementVisible(commonPage.lblContainDynamic(ERROR_MESS), GlobalVariable.G_timeoutShort)
		delay(GlobalVariable.G_timeoutShort)
		MobileDriver.setText(commonPage.txtEditNo(1), note, GlobalVariable.G_timeoutLong, flowControl)
		MobileDriver.tap(commonPage.lblDynamic(SEND_NOW), GlobalVariable.G_timeoutMedium, flowControl)
	}

	void processRequestMoney(String mobileNumber, String amount, String note, FailureHandling flowControl = defaultFailureHandling) {
		'Tap to request money'
		MobileDriver.tap(commonPage.lblDynamic(REQUEST_MONEY), GlobalVariable.G_timeoutMedium, flowControl)
		MobileDriver.tap(commonPage.lblDynamic(mobileNumber), GlobalVariable.G_timeoutMedium, flowControl)
		MobileDriver.takeScreenshot()
		'enter limit amount and verify'
		commonPage.enterNO(MAX_AMOUNT)
		MobileDriver.verifyElementVisible(commonPage.lblDynamic(ERROR_LIMIT_AMT), GlobalVariable.G_timeoutShort)
		commonPage.tapBack()
		delay(GlobalVariable.G_timeoutShort)
		'tap to mobile again'
		MobileDriver.tap(commonPage.lblDynamic(mobileNumber), GlobalVariable.G_timeoutMedium, flowControl)
		'enter right amt'
		commonPage.enterNO(amount)
		'edit and validation at confirm screen'
		verifyFieldValidationConfirmation(amount)
		MobileDriver.verifyElementVisible(commonPage.lblDynamic(NOTES), GlobalVariable.G_timeoutShort)
		delay(GlobalVariable.G_timeoutShort)
		'validate special character'
		MobileDriver.setText(commonPage.txtEditNo(1), ERROR_NOTE, GlobalVariable.G_timeoutLong, flowControl)
		MobileDriver.tap(commonPage.lblDynamic(SEND_REQUEST), GlobalVariable.G_timeoutMedium, flowControl)
		MobileDriver.verifyElementVisible(commonPage.lblContainDynamic(ERROR_MESS), GlobalVariable.G_timeoutShort)
		delay(GlobalVariable.G_timeoutShort)
		'enter valid note'
		MobileDriver.setText(commonPage.txtEditNo(1), note, GlobalVariable.G_timeoutLong, flowControl)
		MobileDriver.tap(commonPage.lblDynamic(SEND_REQUEST), GlobalVariable.G_timeoutMedium, flowControl)
	}
	void processSendMoneyNonMAE(String mobileNumber, FailureHandling flowControl = defaultFailureHandling) {
		MobileDriver.tap(commonPage.lblDynamic(SEND_MONEY), GlobalVariable.G_timeoutMedium, flowControl)
		MobileDriver.tap(commonPage.lblDynamic(mobileNumber), GlobalVariable.G_timeoutMedium, flowControl)
		if (waitForElementPresent(commonPage.lblDynamic(YES), GlobalVariable.G_timeoutShort)) {
			MobileDriver.tap(commonPage.lblDynamic(YES), GlobalVariable.G_timeoutMedium, flowControl)
			if (waitForElementPresent(commonPage.lblDynamic(INVITE_NOW), GlobalVariable.G_timeoutShort)) {
				MobileDriver.tap(commonPage.lblDynamic(INVITE_NOW), GlobalVariable.G_timeoutMedium, flowControl)
				delay(GlobalVariable.G_timeoutShort)
				MobileDriver.verifyElementVisible(commonPage.lblContainDynamic(NOT_MAE), GlobalVariable.G_timeoutShort)
				Mobile.pressBack()
			}
		}
	}

	void verifyFieldValidationConfirmation(String editAmount, FailureHandling flowControl = defaultFailureHandling) {
		MobileDriver.delay(GlobalVariable.G_timeoutShort)
		MobileDriver.takeScreenshot()
		'1. Edit Amount'
		MobileDriver.tap(commonPage.lblRMNo(1), GlobalVariable.G_timeoutLong, flowControl)
		'Verify Enter Amount is visible'
		Mobile.verifyElementVisible(commonPage.lblDynamic(UIlblEnterAmount), GlobalVariable.G_timeoutLong, flowControl)
		'Enter Amount'
		commonPage.enterNO(editAmount)
		'Verify Confirmation is visible'
		MobileDriver.verifyElementVisible(commonPage.lblDynamic(UIConfirmation), GlobalVariable.G_timeoutLong, flowControl)
	}

	void verifyStatusScreen(String mobile, FailureHandling flowControl = defaultFailureHandling) {
		MobileDriver.delay(GlobalVariable.G_timeoutMiniShort)
		MobileDriver.takeScreenshot()
		'Verify request success screen'
		Mobile.verifyElementVisible(commonPage.lblDynamic(REQUEST_SUCCESSFUL), GlobalVariable.G_timeoutLong, flowControl)
		MobileDriver.verifyElementVisible(commonPage.lblDynamic(mobile), GlobalVariable.G_timeoutLong, flowControl)
		commonPage.tapDoneBtn()
	}

	void maskAsPaid(FailureHandling flowControl = defaultFailureHandling) {
		MobileDriver.delay(GlobalVariable.G_timeoutMiniShort)
		MobileDriver.takeScreenshot()
		'Chose 1st item'
		MobileDriver.tap(commonPage.lblDynamicNo(OUTGOING,1), GlobalVariable.G_timeoutMedium, flowControl)
		MobileDriver.verifyElementVisible(commonPage.lblDynamic(REFERENCE_ID), GlobalVariable.G_timeoutMedium, flowControl)
		'Tap to ...'
		MobileDriver.tap(commonPage.imgNoDynamic(2), GlobalVariable.G_timeoutMedium, flowControl)
		MobileDriver.tap(commonPage.lblDynamic(MARK_AS_PAID), GlobalVariable.G_timeoutMedium, flowControl)
		MobileDriver.verifyElementVisible(commonPage.lblDynamic(NOTE_MARK_AS_PAID), GlobalVariable.G_timeoutMedium, flowControl)
	}

	void cancelRequest(FailureHandling flowControl = defaultFailureHandling) {
		MobileDriver.delay(GlobalVariable.G_timeoutMiniShort)
		MobileDriver.takeScreenshot()
		'Chose 1st item'
		MobileDriver.tap(commonPage.lblDynamicNo(OUTGOING,1), GlobalVariable.G_timeoutMedium, flowControl)
		MobileDriver.verifyElementVisible(commonPage.lblDynamic(REFERENCE_ID), GlobalVariable.G_timeoutMedium, flowControl)
		'Tap to ...'
		MobileDriver.tap(commonPage.imgNoDynamic(2), GlobalVariable.G_timeoutMedium, flowControl)
		MobileDriver.tap(commonPage.lblDynamic(CANCEL_REQUEST), GlobalVariable.G_timeoutMedium, flowControl)
	}
}
