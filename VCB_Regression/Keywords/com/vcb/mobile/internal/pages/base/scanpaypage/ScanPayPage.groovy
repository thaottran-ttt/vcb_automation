package com.vcb.mobile.internal.pages.base.scanpaypage

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
import com.vcb.common.MobileDriver
import com.vcb.mobile.internal.pages.base.BasePage
import com.vcb.mobile.internal.pages.base.CommonPage
import com.vcb.mobile.internal.pages.base.ShareReceiptPage
import com.vcb.mobile.internal.pages.base.Secure2uAuthorisationPage
import internal.GlobalVariable

public abstract class ScanPayPage extends BasePage {
	abstract TestObject sliderDailyLimit()
	abstract TestObject sliderCumulativeLimit()
	abstract TestObject sliderDailyLimitPoint()
	abstract TestObject sliderCumulativeLimitPoint()
	abstract TestObject btnFlashLight()
	abstract TestObject lblImagePicker(def index)

	CommonPage commonPage = getPage(CommonPage)
	public static final ShareReceiptPage shareReceiptPage = getPage(ShareReceiptPage)
	public static final Secure2uAuthorisationPage secure2uAuthorisationPage = getPage(Secure2uAuthorisationPage)

	//UI
	String UIbtnNext = 'Next'
	String UIScanAndPage = 'Scan & Pay'
	String UIConfirm = 'Confirm'
	String UIRequestOTP = 'Request OTP'
	String UIEnterAmount = 'Enter amount'

	//UI Limit adjustment
	List<String> UIStaticLimitAdjustment = [
		'Scan & Pay',
		'Daily Limit',
		'The maximum amount for your daily Scan & Pay transactions.',
		'Quick Scan & Pay Limit',
		'The total amount for your daily Scan & Pay transactions without biometrics verification.',
		'Note: As a safety measure, you\'ll need biometrics verification for transaction amounts above the Quick Scan & Pay limit.',
		'Terms & Conditions',
		'Confirm'
	]

	//UI Activation Successful
	List<String> UIStaticActivationSuccessful = [
		'Activation Successful',
		'You may now make payments or receive money via any accepted QR codes, including DuitNow QR codes.',
		'Preferred account',
		'Daily limit',
		'Quick Scan & Pay Limit'
	]
	//UIScanQRCode
	String UIScanQRCodeFromGallery = 'Scan QR Code from Gallery'
	String UILblGoCashless = 'Go cashless with Scan & Pay'
	//UI Pay Now
	String UIPayNowBtn = 'Pay Now'
	String UIRM = 'RM'
	//UI Pay Now Merchant
	String UIPromo = 'Promo'
	String UIEnterPromo = 'Enter Promo'
	String UIApplyPromo = 'Apply Promo'
	String UIInvalidPromo = 'Invalid Promo Code'
	//UI Status Screen
	String UIPaymentSuccessful = 'Payment successful'
	String UIReferenceID = 'Reference ID'
	String UIDateAndTime = 'Date & time'
	List<String> UIStaticStatusScreen = [
		'Transaction Type',
		'Scan & Pay'
	]
	//UI Share Receipt
	String UIShareReceipt = 'Share Receipt'
	String UIDoneBtn = 'Done'
	//UI Share Receipt P2P
	List<String> UIstaticTextP2P = [
		'Reference ID',
		'Recipient Name',
		'Recipient Account Number',
		'Amount',
		'Successful'
	]
	//UI Share Receipt P2P
	List<String> UIstaticTextMerchant = [
		'Reference ID',
		'Merchant Name',
		'Amount',
		'Successful'
	]
	//UI Invalid status
	String UImsgInvalid = 'Invalid QR Code. Please try again. [MB02]'
	String UImsgUnableVerifyAcccountNo = 'Unable to verify account number. Please check details again.'
	//UI Timeout status
	String UImsgTimeout = 'QR Timeout'
	//UI RECEIVE QR
	String UIReceive = 'RECEIVE'
	List<String> UIStaticReceiveQR = ['Share', 'Enter Amount']
	String UIShare = 'Share'
	//UI ShareQRCode
	List<String> UIStaticShareQRCode = [
		'Share QR Code',
		'Share',
		'Save'
	]
	String UISave = 'Save'
	String UIQRSaved = 'QR Saved'
	//UI Daily Limit Exceeded
	String UIDailyLimitExceededMsg = 'Maximum limit per transfer is up to RM1000'

	void tapNext(FailureHandling flowControl = defaultFailureHandling) {
		MobileDriver.tap(commonPage.lblDynamic(UIbtnNext), GlobalVariable.G_timeoutLong, flowControl)
		Mobile.delay(5)
		MobileDriver.takeScreenshot()
	}

	void limitAdjustment(boolean isChangeSlider = true, FailureHandling flowControl = defaultFailureHandling) {
		MobileDriver.takeScreenshot()
		'Verify Static Limit Adjustment'
		scrollToAndVerifyTextInList(UIStaticLimitAdjustment, commonPage.lblDynamicPath(), GlobalVariable.G_timeoutLong)
		if(isChangeSlider) {
			'Swipe Screen'
			commonPage.sliderToDistance(this.sliderDailyLimitPoint(), 100)
			'Swipe Screen'
			commonPage.sliderToDistance(this.sliderCumulativeLimitPoint(), -200)
		}
		'Select Confirm Button'
		MobileDriver.tap(commonPage.lblDynamic(UIConfirm), GlobalVariable.G_timeoutLong, flowControl)
		Mobile.delay(6)
	}

	void verifyLimitAdjustmentSetting(List limitAdjustmentUIData, String status='Approve', FailureHandling flowControl = defaultFailureHandling) {
		scrollToAndVerifyTextInList(limitAdjustmentUIData, commonPage.lblContainDynamicPath(), GlobalVariable.G_timeoutLong)

		'Verify Daily limit slider'
		commonPage.sliderToDistance(this.sliderDailyLimitPoint(), 100)
		commonPage.sliderToDistance(this.sliderDailyLimitPoint(), -100)

		'Verify Quick Scan & Pay Limit slider'
		commonPage.sliderToDistance(this.sliderCumulativeLimitPoint(), -200)
		commonPage.sliderToDistance(this.sliderCumulativeLimitPoint(), 200)

		MobileDriver.tap(commonPage.lblDynamic(UIConfirm), GlobalVariable.G_timeoutLong, flowControl)
		Mobile.delay(GlobalVariable.G_timeoutShort)

		secure2uAuthorisationPage.authorize(status)
		MobileDriver.tap(commonPage.lblDynamic(UIDoneBtn), GlobalVariable.G_timeoutLong)

		if(commonPage.waitForElementPresent(commonPage.lblDynamic(UIScanQRCodeFromGallery), GlobalVariable.G_timeoutShort, FailureHandling.OPTIONAL)) {
			delay(GlobalVariable.G_timeoutMiniShort)
			'Tap close btn'
			MobileDriver.tap(commonPage.imgNoDynamic(2), GlobalVariable.G_timeoutLong)
		}
	}

	void registerQRCode(String status='Approve', boolean isChangeSlider = true, FailureHandling flowControl = defaultFailureHandling) {
		'Wait Scan QR Code From Gallery Present'
		if(waitForElementPresent(commonPage.lblDynamic(UIScanQRCodeFromGallery), GlobalVariable.G_timeoutShort, FailureHandling.OPTIONAL)) {
			return
		}
		'Select Next Button'
		this.tapNext()
		'Select Limit Adjustment'
		this.limitAdjustment(isChangeSlider)
		//		this.authorise(status)
		MobileDriver.takeScreenshot()
		'Check Status Autorize'
		secure2uAuthorisationPage.authorize(status)
		'Verify Scan And Pay Successful'
		this.verifyScanAndPaySuccessful()
		'Select Done Button'
		MobileDriver.tap(commonPage.lblDynamic(UIDoneBtn), GlobalVariable.G_timeoutLong, flowControl)
	}

	void verifyScanAndPaySuccessful(FailureHandling flowControl = defaultFailureHandling) {
		'Verify Static Activation Successful'
		verifyElements(UIStaticActivationSuccessful, commonPage.lblDynamicPath(), GlobalVariable.G_timeoutLong)
	}

	void selectQRCode(String QRCodefileName, FailureHandling flowControl = defaultFailureHandling) {
		List<String> qrList = [
			'P2P.png',
			'merchant.png',
			'invalid.png',
			'timeout.png',
			'out.png'
		]

		// Get index of Image file base on the order of pushed file on device
		int pushIndex = qrList.indexOf(QRCodefileName)
		if (pushIndex == -1) {
			info("QR not found: ${QRCodefileName}")
			return
		}

		'Select Scan QR Code From Gallery'
		MobileDriver.tap(commonPage.lblDynamic(UIScanQRCodeFromGallery), GlobalVariable.G_timeoutLong, flowControl)
		MobileDriver.takeScreenshot()

		'Select QR Code File Name'
		int pickerIndex = qrList.size() - pushIndex
		info("pickerIndex: " + pickerIndex)

		String accessWarningTxt = "Dismiss"

		if (MobileDriver.waitForElementPresent(commonPage.lblDynamic(accessWarningTxt), GlobalVariable.G_timeoutLong, FailureHandling.OPTIONAL)){
			pickerIndex += 1
		}

		MobileDriver.tap(this.lblImagePicker(pickerIndex), GlobalVariable.G_timeoutLong, flowControl)
	}

	void selectFirstQRCode(FailureHandling flowControl = defaultFailureHandling) {
		'Select Scan QR Code From Gallery'
		MobileDriver.tap(commonPage.lblDynamic(UIScanQRCodeFromGallery), GlobalVariable.G_timeoutLong, flowControl)
		MobileDriver.takeScreenshot()
		'Select first image from gallery'
		MobileDriver.tap(commonPage.imgNoDynamic(2), GlobalVariable.G_timeoutLong, flowControl)
	}
	void scanQRCode(String fileImageName, FailureHandling flowControl = defaultFailureHandling) {
		'Select Scan QR Code From Gallery'
		MobileDriver.tap(commonPage.lblDynamic(UIScanQRCodeFromGallery), GlobalVariable.G_timeoutLong, flowControl)
		MobileDriver.takeScreenshot()
		'Select image from gallery'
		MobileDriver.tap(commonPage.lblDynamic(fileImageName), GlobalVariable.G_timeoutLong, flowControl)
	}

	void enterAmount(String amount, FailureHandling flowControl = defaultFailureHandling) {
		'Enter Amount'
		commonPage.enterNO(amount, true, UIEnterAmount)
	}

	void enterAmountQuickTap(String amount, FailureHandling flowControl = defaultFailureHandling) {
		'Enter Amount'
		commonPage.enterNO(amount, false, UIEnterAmount)
		'Selct Tick Button'
		MobileDriver.tapAtPosition(commonPage.btnTick(), GlobalVariable.G_timeoutLong, flowControl)
	}

	void verifyPayNow(status='ALL', FailureHandling flowControl = defaultFailureHandling) {
		'Verify Pay Now Button'
		verifyElements([UIPayNowBtn], commonPage.lblDynamicPath(), GlobalVariable.G_timeoutLong)
		'Verify RM Visible'
		MobileDriver.verifyElementVisible(commonPage.lblStartsWith(UIRM), GlobalVariable.G_timeoutLong, flowControl)
		switch(status) {
			case 'MerchantON':
				'Verify Enter Promo'
				verifyElements([UIEnterPromo], commonPage.lblDynamicPath(), GlobalVariable.G_timeoutLong)
				'Enter Wrong Promo'
				MobileDriver.setText(commonPage.txtEditNo(1), 'WrongPromo', GlobalVariable.G_timeoutLong, flowControl)
				'Verify Apply Promo'
				verifyElements([UIApplyPromo], commonPage.lblDynamicPath(), GlobalVariable.G_timeoutLong)
				'Select Apply Promo'
				Mobile.tap(commonPage.lblDynamic(UIApplyPromo), GlobalVariable.G_timeoutLong, flowControl)
				'Verify Invalid Promo'
				Mobile.verifyElementVisible(commonPage.lblDynamic(UIInvalidPromo), GlobalVariable.G_timeoutMedium)
				'Clear Text'
				Mobile.clearText(commonPage.txtEditNo(1), GlobalVariable.G_timeoutLong, flowControl)
				MobileDriver.delay(GlobalVariable.G_timeoutMiniShort)
				break
		}
		'Select Pay Now Button'
		Mobile.delay(GlobalVariable.G_timeoutShort)
		MobileDriver.waitForElementPresent(commonPage.lblDynamic(UIPayNowBtn), GlobalVariable.G_timeoutLong, flowControl)
		MobileDriver.tap(commonPage.lblDynamic(UIPayNowBtn), GlobalVariable.G_timeoutLong, flowControl)
		Mobile.delay(GlobalVariable.G_timeoutShort)
	}

	def statusScreen(String status, FailureHandling flowControl = defaultFailureHandling) {
		'Assign Data'
		String dateTime = MobileDriver.getText(commonPage.lblSiblingDynamic(UIDateAndTime), GlobalVariable.G_timeoutLong, flowControl)
		'Verify Date Time'
		if(Mobile.verifyMatch(dateTime, '(0?[1-9]|[12][0-9]|3[01]) [A-Z]{1}[a-z]{2} [0-9]{4}, (0?[0-9]|1[0-9]|2[0-3]):[0-9]{2} [A|P]M',true)) {
			info('Date follows the format DD MMM YYYY, HH:MM (A|P)M')
		}
		'Assign Ref ID Data'
		String RefID = MobileDriver.getText(commonPage.lblSiblingDynamic(UIReferenceID), GlobalVariable.G_timeoutLong, flowControl)
		'Check Status'
		switch(status) {
			case 'MerchantOFF':
				'Verify Ref ID'
				if(Mobile.verifyMatch(RefID, '[0-9]{8}', true)) {
					info('Reference ID follows the format 8 characters is numbers')
				}
				break
			case 'P2P':
			case 'MerchantON':
				'Verify Ref ID'
				if(Mobile.verifyMatch(RefID, '[0-9]{9}Q', true)) {
					info('Reference ID follows the format 10 characters with first 9 is numbers and end with Q')
				}
				break
		}
		'Verify Static Status Screen'
		verifyElements(UIStaticStatusScreen, commonPage.lblDynamicPath(), GlobalVariable.G_timeoutLong)
		return [RefID]
	}

	void shareReceipt(List<String> verify, String status, FailureHandling flowControl = defaultFailureHandling) {
		'Select Share Receipt'
		MobileDriver.tap(commonPage.lblDynamic(UIShareReceipt), GlobalVariable.G_timeoutLong, flowControl)
		Mobile.delay(GlobalVariable.G_timeoutMiniShort)
		'Check Status'
		switch(status) {
			case 'P2P':
				'Verify Static Text P2P'
				shareReceiptPage.shareReceipts(UIstaticTextP2P + verify)
				break
			case 'MerchantOFF':
			case 'MerchantON ':
				'Verify Static Text Merchant'
				shareReceiptPage.shareReceipts(UIstaticTextMerchant + verify)
				break
		}
	}

	void verifyMsgStatus(String status, FailureHandling flowControl = defaultFailureHandling) {
		'Check Status'
		switch(status) {
			case 'InvalidQR':
				'Verify Msg Invalid'
				MobileDriver.verifyElementVisible(commonPage.lblDynamic(UImsgInvalid), GlobalVariable.G_timeoutLong, flowControl)
				break
			case 'Timeout':
				'Verify Msg Timeout'
				MobileDriver.verifyElementVisible(commonPage.lblDynamic(UImsgTimeout), GlobalVariable.G_timeoutLong, flowControl)
				break
			case 'MerchantOFF':
				'Verify Msg Unable Verify Acccount No'
				MobileDriver.verifyElementVisible(commonPage.lblDynamic(UImsgUnableVerifyAcccountNo), GlobalVariable.G_timeoutLong, flowControl)
				break
		}
	}

	boolean hasMsgStatusMerchantOFF(FailureHandling flowControl = defaultFailureHandling) {
		'Wait Msg Unable Verify Acccount No Present'
		if(MobileDriver.waitForElementPresent(commonPage.lblDynamic(UImsgUnableVerifyAcccountNo), GlobalVariable.G_timeoutLong, flowControl)) {
			return true
		}
		return false
	}

	void tapBtnDone(FailureHandling flowControl = defaultFailureHandling) {
		'Select Done Button'
		MobileDriver.tap(commonPage.lblDynamic(UIDoneBtn), GlobalVariable.G_timeoutLong, flowControl)
	}

	boolean verifyTransferNotificationMessage(String refId, String amount, FailureHandling flowControl = defaultFailureHandling) {
		'Assign Data'
		double amountNo = Double.parseDouble(amount)/100
		String formattedAmount = String.format("%.2f", amountNo)
		String messagePN="You've transferred RM ${formattedAmount} to (.+). REF: ${refId}."
		'Verify Message PN'
		commonPage.verifyDeviceNotification("Vcb2u: Scan & Pay", "You've transferred", messagePN)
	}

	void saveShareReceiveQR(FailureHandling flowControl = defaultFailureHandling) {
		'Select Flash Light Button'
		MobileDriver.tap(this.btnFlashLight(), GlobalVariable.G_timeoutLong, flowControl) //Turns Flashlight ON
		Mobile.delay(1)
		Mobile.takeScreenshot()
		'Select Flash Light Button'
		MobileDriver.tap(this.btnFlashLight(), GlobalVariable.G_timeoutLong, flowControl) //Turn Flashlight Off
		Mobile.delay(1)
		Mobile.takeScreenshot()
		'Select Receive'
		MobileDriver.tap(commonPage.lblDynamic(UIReceive), GlobalVariable.G_timeoutLong, flowControl)
		'Verify Static Receive QR'
		verifyElements(UIStaticReceiveQR, commonPage.lblDynamicPath(), GlobalVariable.G_timeoutLong)

		'Tap share'
		'Select Share Button'
		MobileDriver.tap(commonPage.lblDynamic(UIShare), GlobalVariable.G_timeoutLong, flowControl)
		MobileDriver.delay(GlobalVariable.G_timeoutMiniShort)
		MobileDriver.takeScreenshot()
		'Verify Static Share QR Code'
		verifyElements(UIStaticShareQRCode, commonPage.lblDynamicPath(), GlobalVariable.G_timeoutLong)
		'Tap save'
		'Select Save Button'
		MobileDriver.tap(commonPage.lblDynamic(UISave), GlobalVariable.G_timeoutLong, flowControl)
		Mobile.delay(1)
		'Take Screenshot'
		Mobile.takeScreenshot()
		'Select QR Saved Button'
//		MobileDriver.verifyElementVisible(commonPage.lblDynamic(UIQRSaved), GlobalVariable.G_timeoutMedium, defaultFailureHandling)
		Mobile.delay(3)
		'Select Share Button'
		MobileDriver.tap(commonPage.lblDynamic(UIShare), GlobalVariable.G_timeoutLong, flowControl)
		MobileDriver.delay(GlobalVariable.G_timeoutShort)
		'Select Back'
		Mobile.pressBack()
	}

	void verifyDailyLimitExceeded(FailureHandling flowControl = defaultFailureHandling) {
		'Verify Daily Limit Exceeded Msg'
		MobileDriver.verifyElementVisible(commonPage.lblDynamic(UIDailyLimitExceededMsg), GlobalVariable.G_timeoutLong, flowControl)
	}
}
