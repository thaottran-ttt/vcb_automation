package com.vcb.mobile.internal.pages.base.paybill

import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testobject.TestObject
import com.vcb.common.MobileDriver
import com.vcb.mobile.internal.pages.base.BasePage
import com.vcb.mobile.internal.pages.base.CommonPage
import com.vcb.mobile.internal.pages.base.NotificationHomePage
import com.vcb.mobile.internal.pages.base.ShareReceiptPage
import com.vcb.utils.DateUtils

import internal.GlobalVariable

public abstract class JomPayPage extends BasePage{
	abstract TestObject imgV()
	abstract TestObject lblMessage()
	abstract TestObject lblDynamicText(String text)
	abstract TestObject txtReferenceIdValue()

	public static CommonPage commonPage = getPage(CommonPage)
	public static NotificationHomePage notificationPage = getPage(NotificationHomePage)
	public static final ShareReceiptPage SHARE_RECEIPT_PAGE = getPage(ShareReceiptPage)

	String UIBillerCode = 'Biller Code'
	String UIReference1 = 'Reference 1'
	String UIReference2 = 'Reference 2 (Optional)'
	String UIContinue = 'Continue'
	String UIPayNow = 'Pay Now'
	String UIToday = 'Today'
	String UIDoneBtn = 'Done'
	String UIReject = 'Reject'
	String UIApprove = 'Approve'
	String UIJOMPAY = 'JOMPAY'
	String UIJomPAY = 'JomPAY'
	String UIShareReceipt = 'Share Receipt'
	String UIReferenceID = 'Reference ID'
	String UIAddToFavourites = 'Add to Favourites'
	String MsgExistAddFavourite = 'Record already exists.'
	String MsgSuccessAddFavourite = 'Added to Favourites'
	String UITransactionFailed = 'Transaction Failed'
	String UIPaymentFailed = 'Payment failed'
	String UIFailed = 'Failed'
	String MsgInsufficient = 'You have insufficient balance in your account.'
	String MsgMaxlimit = 'Daily transaction limit exceeded. Please log in to M2U web and go to Settings to change your limit.'
	String MsgReject = 'Transaction is rejected'
	String SuccessAddFavourite = 'successfully added to Favourites.'

	void selectLblJomPAY(int timeout = GlobalVariable.G_timeoutLong, FailureHandling flowControl = defaultFailureHandling) {
		'Select JOMPAY'
		MobileDriver.tap(commonPage.lblDynamic(UIJOMPAY), timeout, flowControl)
		Mobile.delay(5)
		'Select JomPAY'
		MobileDriver.tap(commonPage.lblDynamic(UIJomPAY), timeout, flowControl)
	}
	
	void selectJomPAY(int timeout = GlobalVariable.G_timeoutLong, FailureHandling flowControl = defaultFailureHandling) {
		'Select JOMPAY'
		MobileDriver.tap(commonPage.lblDynamic(UIJOMPAY), timeout, flowControl)
	}

	boolean verifyJomPayScreen(int timeout = GlobalVariable.G_timeoutShort, FailureHandling flowControl = defaultFailureHandling) {
		'Select JomPAY'
		this.selectLblJomPAY()

		'Verify Biller Code exist'
		MobileDriver.verifyElementExist(commonPage.lblDynamic(UIBillerCode), timeout, flowControl)

		'Verify Reference 1 exist'
		MobileDriver.verifyElementExist(commonPage.lblDynamic(UIReference1), timeout, flowControl)

		'Verify Reference 2 (Optional) exist'
		MobileDriver.verifyElementExist(commonPage.lblDynamic(UIReference2), timeout, flowControl)
	}
	
	void selectFavourite(String favName, FailureHandling flowControl = defaultFailureHandling) {
		commonPage.scrollToTextDefault(favName)
		'Verify Favourite is Dispay'
		MobileDriver.verifyElementVisible(commonPage.lblDynamic(favName), GlobalVariable.G_timeoutMedium, flowControl)
		'Verify can Tap Favourite'
		MobileDriver.tap(commonPage.lblDynamic(favName), GlobalVariable.G_timeoutMedium, flowControl)
		'Wating Next Screen'
		commonPage.waitLoading()
	}

	void enterJomPayInfo(String billerCode, String reference1, String reference2, int timeout = GlobalVariable.G_timeoutShort, FailureHandling flowControl = defaultFailureHandling) {
		'Enter Biller Code'
		MobileDriver.setText(commonPage.txtEditNo(1), billerCode, timeout, flowControl)

		'Enter Reference 1'
		MobileDriver.setText(commonPage.txtEditNo(2), reference1, timeout, flowControl)
		Mobile.delay(1)

		'Enter Reference 2 (Optional)'
		MobileDriver.setText(commonPage.txtEditNo(3), reference2, timeout, flowControl)

		'Select Continue'
		MobileDriver.tap(commonPage.lblDynamic(UIContinue), timeout, flowControl)
	}

	void checkInvalidBiller(String billerCode, String reference1, String reference2, int timeout = GlobalVariable.G_timeoutShort, FailureHandling flowControl = defaultFailureHandling) {
		'Enter wrong Biller Code'
		MobileDriver.setText(commonPage.txtEditNo(1), 'Wrong', timeout, flowControl)

		'Enter Reference 1'
		MobileDriver.setText(commonPage.txtEditNo(2), reference1, timeout, flowControl)

		'Select Continue'
		MobileDriver.tap(commonPage.lblDynamic(UIContinue), timeout, flowControl)

		String msg = MobileDriver.getText(commonPage.lblStartsWith('Invalid Biller Code'), timeout);

		'Verify message'
		Mobile.verifyEqual(msg, 'Invalid Biller Code. Please re-enter a valid Biller Code. ')

		'Enter Biller Code'
		MobileDriver.setText(commonPage.txtEditNo(1), billerCode, timeout, flowControl)

		'Enter Reference 2 (Optional)'
		MobileDriver.setText(commonPage.txtEditNo(3), reference2, timeout, flowControl)

		'Select Continue'
		MobileDriver.tap(commonPage.lblDynamic(UIContinue), timeout, flowControl)
	}

	void checkMinimumAmount(String validAmount, String invalidAmount, int timeout = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		'Enter invalid amount'
		commonPage.enterNO(invalidAmount, true, '', 0)

		'Get message'
		String msg = MobileDriver.getText(commonPage.lblStartsWith('Invalid Amount'), timeout);

		'Verify message'
		Mobile.verifyEqual(msg, 'Invalid Amount. Please enter a larger Amount.')

		Mobile.delay(5)

		'Enter valid amount'
		commonPage.enterNO(validAmount)
	}


	void confirm(boolean isFuture = false,int timeout =GlobalVariable.G_timeoutLong,  FailureHandling flowControl = defaultFailureHandling) {
		waitForElementPresent(commonPage.lblDynamic(UIPayNow), timeout, flowControl)
		waitForElementPresent(this.imgV(), timeout, flowControl)
		if(isFuture) {
			'Select today'
			MobileDriver.tap(commonPage.lblDynamic(UIToday), timeout, flowControl)

			Mobile.delay(1)

			'Get 29 days after date'
			def tomorrowDate = DateUtils.getCurrentDateFormat('d MMM yyyy', 29)
			String[] tomorrow = tomorrowDate.split(' ')

			'Swipe from S to M'
			commonPage.swipeText('S', 'M')

			'Select date'
			Mobile.tap(commonPage.lblDynamicTextLast(tomorrow[0]), GlobalVariable.G_timeoutLong, flowControl)
			'Select Done'
			MobileDriver.tap(commonPage.lblDynamic(commonPage.UIDonebtn), GlobalVariable.G_timeoutLong, flowControl)
		}
		Mobile.delay(5)

		'Select Pay Now'
		tap(commonPage.lblDynamic(UIPayNow), timeout, flowControl)
	}

	void selectApproval(String status, int timeout =GlobalVariable.G_timeoutLong, FailureHandling flowControl = defaultFailureHandling) {
		switch(status) {
			case 'Expired':
				'Wait until expired'
				MobileDriver.waitForElementPresent(commonPage.lblDynamic(UIDoneBtn), 60 + timeout, flowControl)
				break
			case 'Reject':

				'Select Reject'
				MobileDriver.tap(commonPage.lblDynamic(UIReject), 60 + timeout, flowControl)
				break
			default:
				'Select Approve'
				MobileDriver.tap(commonPage.lblDynamic(UIApprove), timeout, flowControl)
				info(status + ' is tapped.')
				break
		}
	}

	public String viewShareReceipt(boolean clickDone = true, int timeout = GlobalVariable.G_timeoutLong, FailureHandling flowControl = defaultFailureHandling) {
		'Get Reference ID'
		String RefID = MobileDriver.getText(this.txtReferenceIdValue(), GlobalVariable.G_timeoutLong, flowControl)

		'Select Share Receipt'
		MobileDriver.tap(commonPage.lblDynamic(UIShareReceipt), timeout, flowControl)
		Mobile.delay(5)

		'Share Receipt'
		SHARE_RECEIPT_PAGE.shareReceipts([UIReferenceID])

		if (clickDone) {
			'Select Done'
			MobileDriver.tap(commonPage.lblDynamic(UIDoneBtn), GlobalVariable.G_timeoutLong, flowControl)
		}

		return RefID
	}

	void addToFavourites(String favName ='Jom Pay Fav',FailureHandling flowControl = defaultFailureHandling) {
		'Add to Favourites'
		MobileDriver.tap(commonPage.lblDynamic(UIAddToFavourites), GlobalVariable.G_timeoutLong, flowControl)
		Mobile.delay(2)
		'Enter Favourite Name'
		MobileDriver.setText(commonPage.txtEditNo(1), favName, GlobalVariable.G_timeoutLong, flowControl)

		'Select Add to Favourites'
		MobileDriver.tap(commonPage.lblDynamic(UIAddToFavourites), GlobalVariable.G_timeoutLong, flowControl)

		'Get message'
		String msg = MobileDriver.getText(this.lblMessage(), GlobalVariable.G_timeoutLong, flowControl)

		'If already added to favourite, then message is Record already exists. else Added to Favourites'
		if(msg != null) {
			if (msg == MsgExistAddFavourite) {
				Mobile.verifyEqual(msg, MsgExistAddFavourite)
			}else {
				Mobile.verifyEqual(msg, favName + ' ' + MsgSuccessAddFavourite)
			}
		}
		'Verify Add to Favourites disappear'
		Mobile.verifyElementNotExist(commonPage.lblDynamic(UIAddToFavourites), GlobalVariable.G_timeoutShort)
	}

	boolean verifyNotificationMessage(String reference1, String refId, double amount, String title, String startMessage, FailureHandling flowControl = defaultFailureHandling) {
		String formattedAmount = String.format("%.2f", amount)
		String messagePN="${startMessage} of RM ${formattedAmount} to .+ \\(${reference1}\\). REF: ${refId}"

		'Verify devide Notification'
		commonPage.verifyDeviceNotification(title, startMessage, messagePN)

		'Verify Notification'
		notificationPage.verifyNotification(title, startMessage, messagePN)
	}

	boolean verifyTransactionFailed(String status, boolean isFuture, int timeout =GlobalVariable.G_timeoutLong, FailureHandling flowControl = defaultFailureHandling) {
		switch(status) {
			case 'Reject':
				MobileDriver.verifyElementExist(commonPage.lblDynamic(UITransactionFailed), timeout)
				MobileDriver.verifyElementExist(commonPage.lblDynamic(MsgReject), timeout)
				break

			case 'Expired':
				MobileDriver.verifyElementExist(commonPage.lblDynamic(UIFailed), timeout)
				break

			case 'Insufficient':
				'If isFuture, then Payment scheduled, else Payment Failed'
				if (isFuture) {
					MobileDriver.verifyElementExist(commonPage.lblDynamic("Payment scheduled"), timeout)
				}
				else {
					MobileDriver.verifyElementExist(commonPage.lblDynamic("Payment Failed"), timeout)
					MobileDriver.verifyElementExist(commonPage.lblDynamic(MsgInsufficient), timeout)
				}
				break
		}
	}
}
