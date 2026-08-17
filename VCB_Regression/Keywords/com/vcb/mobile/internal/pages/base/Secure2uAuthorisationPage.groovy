package com.vcb.mobile.internal.pages.base


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
import com.vcb.assertions.BaseAssert
import com.vcb.common.MobileDriver
import com.kms.katalon.core.logging.KeywordLogger

import internal.GlobalVariable

public abstract class Secure2uAuthorisationPage extends BasePage {

	//Init Data
	static final String TRANSACTION_AUTHORISATION_CODE = 'Transaction Authorisation Code'
	static final String SECURE2U_AUTHORISATION = 'Secure2u authorisation'
	static final String OTP_FAIL = '222222'
	static final String S2UMESSAGE = 'Secure2u is currently unavailable. TAC will be used instead.'
	//Approve, Reject, Expired
	String UIDoneBtn = 'Done'
	String UIReject = 'Reject'
	String UIApprove = 'Approve'
	String UIRequestOTP = 'Request OTP'

	List<String> UIStaticApprove = [
		'Secure2u authorisation',
		'Transfer to',
		'Transfer from',
		'Transaction type',
		'Date & time'
	]

	List<String> UIStaticApproveV5 = ['S2u Version', 'V5']

	//UI TAC
	abstract TestObject lblYourTAC()
	abstract TestObject lblTACForM2U()
	abstract TestObject btnApprove()
	abstract TestObject btnReject()
	String UIEnterTac = 'Enter TAC sent to you'
	String UIWrongTac = 'Wrong TAC entered.'
	List<String> UIStaticExpired = [
		'TAC timeout. ',
		'Resend TAC?'
	]

	//UI OTP
	abstract TestObject lblYourOTP()
	String UIEnterOTP = 'Enter OTP sent to you'
	List<String> UIStaticExpiredOTP = [
		'OTP timeout. ',
		'Resend OTP?'
	]

	CommonPage commonPage = getPage(CommonPage)

	void performApproveRejectOrExpired(String status, FailureHandling flowControl = defaultFailureHandling) {
		'Check Status'
		boolean isButtonDisplayed = false
		int i = 0

		switch(status) {
			case 'Expired':
				'Wait Done Button Present'
				MobileDriver.waitForElementPresent(commonPage.lblDynamic(UIDoneBtn), 60 + GlobalVariable.G_timeoutLong, flowControl)
				break
			case 'Reject':
				'Select Reject Button'
				MobileDriver.tap(commonPage.lblDynamic(UIReject), GlobalVariable.G_timeoutLong)
				if(Mobile.waitForElementPresent(commonPage.lblDynamic(UIReject), GlobalVariable.G_timeoutLong)) {
					MobileDriver.tap(commonPage.lblDynamic(UIReject), GlobalVariable.G_timeoutLong)
				}
				break
			default:
				'Select Approve Button'
				MobileDriver.tap(commonPage.lblDynamic(UIApprove), GlobalVariable.G_timeoutLong)
				if(Mobile.waitForElementPresent(commonPage.lblDynamic(UIApprove), GlobalVariable.G_timeoutLong)) {
					MobileDriver.tap(commonPage.lblDynamic(UIApprove), GlobalVariable.G_timeoutLong)
				}
				break
		}
		'Log Status'
		info(status + ' is passed.')
	}

	String authorize(String status, List otherVerifyData = [], FailureHandling flowControl = defaultFailureHandling) {
		MobileDriver.waitForElementPresent(commonPage.lblDynamicList([
			TRANSACTION_AUTHORISATION_CODE,
			UIRequestOTP,
			SECURE2U_AUTHORISATION
		]), GlobalVariable.G_timeoutLong, flowControl)

		if(waitForElementPresent(commonPage.lblDynamic(SECURE2U_AUTHORISATION), GlobalVariable.G_timeoutLong, FailureHandling.OPTIONAL)) {
			'Take Screenshot'
			MobileDriver.takeScreenshot()
			'Perform Approval'
			performApproveRejectOrExpired(status)
			commonPage.waitLoading()
			if(!otherVerifyData.isEmpty()) {
				'Veirfy authorization Other UI'
				scrollToAndVerifyTextInList(otherVerifyData, commonPage.lblDynamicPath(), GlobalVariable.G_timeoutMedium, flowControl)
			}
		}
		else if(MobileDriver.waitForElementPresent(commonPage.lblDynamic(TRANSACTION_AUTHORISATION_CODE), GlobalVariable.G_timeoutShort, FailureHandling.OPTIONAL)) {
			'Take Screenshot'
			MobileDriver.takeScreenshot()
			return this.authorizeWithTacOrOTP(status,AuthorisationType.TAC)
		}
		else if(MobileDriver.waitForElementPresent(commonPage.lblDynamic(UIRequestOTP), GlobalVariable.G_timeoutShort, FailureHandling.OPTIONAL)) {
			commonPage.waitLoading()
			MobileDriver.delay(GlobalVariable.G_timeoutShort)
			MobileDriver.tap(commonPage.lblDynamic(UIRequestOTP), GlobalVariable.G_timeoutMedium, flowControl)
			return this.authorizeWithTacOrOTP(status, AuthorisationType.OTP)
		}else {
			MobileDriver.takeScreenshot()
			handleError("System Error: The Secure2u or TAC page does not appear", flowControl)
		}
		'Check Enter PIN'
		
		return status
	}

	String authorizeS2UV5BioPin (String status, List otherVerifyData = [], FailureHandling flowControl = defaultFailureHandling) {
		MobileDriver.waitForElementPresent(commonPage.lblDynamicList([
			TRANSACTION_AUTHORISATION_CODE,
			UIRequestOTP,
			SECURE2U_AUTHORISATION
		]), GlobalVariable.G_timeoutLong, flowControl)

		if(waitForElementPresent(commonPage.lblDynamic(SECURE2U_AUTHORISATION), GlobalVariable.G_timeoutShort, FailureHandling.OPTIONAL)) {
			'Take Screenshot'
			MobileDriver.takeScreenshot()
			if(!otherVerifyData.isEmpty()) {
				'Verify authorization Other UI'
				scrollToAndVerifyTextInList(otherVerifyData, commonPage.lblDynamicPath(), GlobalVariable.G_timeoutMedium, flowControl)
			}
			'Perform Approval'
			performApproveRejectOrExpired(status)
			MobileDriver.takeScreenshot()
			'Enters PIN for S2U V5'
			commonPage.checkEnterS2UV5PIN(GlobalVariable.G_timeoutMiniShort)
			commonPage.waitLoading()
		}
		else if(MobileDriver.waitForElementPresent(commonPage.lblDynamic(TRANSACTION_AUTHORISATION_CODE), GlobalVariable.G_timeoutShort, FailureHandling.OPTIONAL)) {
			'Take Screenshot'
			MobileDriver.takeScreenshot()
			return this.authorizeWithTacOrOTP(status,AuthorisationType.TAC)
		}
		else if(MobileDriver.waitForElementPresent(commonPage.lblDynamic(UIRequestOTP), GlobalVariable.G_timeoutShort, FailureHandling.OPTIONAL)) {
			commonPage.waitLoading()
			MobileDriver.delay(GlobalVariable.G_timeoutShort)
			MobileDriver.tap(commonPage.lblDynamic(UIRequestOTP), GlobalVariable.G_timeoutMedium, flowControl)
			return this.authorizeWithTacOrOTP(status, AuthorisationType.OTP)
		}else {
			MobileDriver.takeScreenshot()
			handleError("System Error: The Secure2u or TAC page does not appear", flowControl)
		}
		return status
	}

	String authorizeM2UTransaction(String status, AuthorisationType type, List otherVerifyData = [], Boolean isCloseButton = false, FailureHandling flowControl = defaultFailureHandling) {
		switch(type) {
			case AuthorisationType.Secure2U:
				'Take Screenshot'
				MobileDriver.takeScreenshot()
				'Perform Approval'
				performApproveRejectOrExpired(status)
				Mobile.delay(GlobalVariable.G_timeoutShort)
				MobileDriver.takeScreenshot()
				'Enters PIN for S2U V5'
				commonPage.checkEnterPIN(GlobalVariable.G_timeoutMiniShort)
				commonPage.waitLoading()
				
				if(isCloseButton) {
					if(MobileDriver.waitForElementPresent(commonPage.lblDynamic(UIDoneBtn), GlobalVariable.G_timeoutMedium, FailureHandling.OPTIONAL)) {
						MobileDriver.takeScreenshot()
						MobileDriver.tap(commonPage.lblDynamic(UIDoneBtn), GlobalVariable.G_timeoutMedium, flowControl)
					}
				}
				break
			case AuthorisationType.TAC:
				try {
					Mobile.openNotifications()
					'Assign Data To Msg'
					String tacRaw = Mobile.getText(this.lblTACForM2U(), GlobalVariable.G_timeoutLong, FailureHandling.OPTIONAL)
					println(tacRaw)
					Mobile.pressBack()
					def tacMatcher = (tacRaw =~ /\b\d{6}\b/)
					return tacMatcher.find() ? tacMatcher[0] : null
				}catch(ex) {
					handleError(ex.toString(), flowControl)
				}
				break
		}
	}

	String authorizeS2UV5M2UTransaction(String status, AuthorisationType type, List otherVerifyData = [], Boolean isCloseButton = false, FailureHandling flowControl = defaultFailureHandling) {
		switch(type) {
			case AuthorisationType.Secure2U:
				MobileDriver.waitForElementPresent(commonPage.lblDynamic(SECURE2U_AUTHORISATION), GlobalVariable.G_timeoutShort, FailureHandling.OPTIONAL)
				'Take Screenshot'
				MobileDriver.takeScreenshot()
			//				scrollToAndVerifyTextInList(UIStaticApproveV5, commonPage.lblDynamicPath(), GlobalVariable.G_timeoutMiniShort, FailureHandling.CONTINUE_ON_FAILURE)

				try {
					scrollToAndVerifyTextInList(UIStaticApproveV5, commonPage.lblDynamicPath(), GlobalVariable.G_timeoutMiniShort, FailureHandling.CONTINUE_ON_FAILURE)
				} catch (Exception e) {
					WebUI.comment("No S2U V5: ${e.message}")
				}


				if(!otherVerifyData.isEmpty()) {
					'Verify authorization Other UI'
					scrollToAndVerifyTextInList(otherVerifyData, commonPage.lblDynamicPath(), GlobalVariable.G_timeoutMedium, flowControl)
				}
				'Perform Approval'
				performApproveRejectOrExpired(status)
				Mobile.delay(GlobalVariable.G_timeoutShort)
				MobileDriver.takeScreenshot()
				'Enters PIN for S2U V5'
				commonPage.checkEnterPIN(GlobalVariable.G_timeoutMiniShort)
				commonPage.waitLoading()

				if(isCloseButton) {
					if(MobileDriver.waitForElementPresent(commonPage.lblDynamic(UIDoneBtn), GlobalVariable.G_timeoutMedium, FailureHandling.OPTIONAL)) {
						MobileDriver.takeScreenshot()
						MobileDriver.tap(commonPage.lblDynamic(UIDoneBtn), GlobalVariable.G_timeoutMedium, flowControl)
					}
				}
				break
			case AuthorisationType.TAC:
				try {
					Mobile.openNotifications()
					'Assign Data To Msg'
					String tacRaw = Mobile.getText(this.lblTACForM2U(), GlobalVariable.G_timeoutLong, FailureHandling.OPTIONAL)
					println(tacRaw)
					Mobile.pressBack()
					def tacMatcher = (tacRaw =~ /\b\d{6}\b/)
					return tacMatcher.find() ? tacMatcher[0] : null
				}catch(ex) {
					handleError(ex.toString(), flowControl)
				}
				break
		}
	}

	void verifyAndAuthorizeForApplyAccount(String status, FailureHandling flowControl = defaultFailureHandling) {
		verifyElementVisible(commonPage.lblDynamic(SECURE2U_AUTHORISATION), GlobalVariable.G_timeoutShort, flowControl)
		// verify amount format
		MobileDriver.verifyMatch(commonPage.lblSiblingDynamic(SECURE2U_AUTHORISATION), '^RM\\s\\d{1,3}(?:,\\d{3})*(?:\\.\\d{2})', GlobalVariable.G_timeoutLong, flowControl)
		scrollToAndVerifyTextInList(UIStaticApprove, commonPage.lblDynamicPath(), GlobalVariable.G_timeoutLong)
		MobileDriver.takeScreenshot()
		performApproveRejectOrExpired(status)
	}

	String authorizeWithTacOrOTP(String status, AuthorisationType authorisationType = AuthorisationType.TAC, FailureHandling flowControl = defaultFailureHandling) {
		'Take Screenshot'
		Mobile.takeScreenshot()
		String msgTACorOTP
		TestObject lbl = (authorisationType == AuthorisationType.TAC) ? this.lblYourTAC() : this.lblYourOTP()

		msgTACorOTP = MobileDriver.getText(lbl, GlobalVariable.G_timeoutMedium, FailureHandling.OPTIONAL)
		if (msgTACorOTP == null) {
			'Open then close it to receive TAC/OTP'
			Mobile.openNotifications()
			'Assign Data To Msg'
			msgTACorOTP = MobileDriver.getText(lbl, GlobalVariable.G_timeoutMedium, FailureHandling.OPTIONAL)
			Mobile.pressBack()
		}
		Mobile.comment("Message Tac:" + msgTACorOTP)

		Mobile.delay(3)
		'Verify Enter'
		verifySomeElementsMatchInList([UIEnterTac, UIEnterOTP], commonPage.lblDynamicPath(), GlobalVariable.G_timeoutMedium)
		'Assign Data'
		def matcher = (msgTACorOTP =~ /\d+/)
		def tacNumber = matcher ? matcher[0] : null
		'Check Number'
		if(!tacNumber) {
			if(status == 'Approve') {
				'Select Tick Button'
				commonPage.tapTickBtn()
				Mobile.delay(5)
				return status + 'TAC Or OTP'
			}else {
				handleError('Tac' + tacNumber, FailureHandling.OPTIONAL)
			}
		}

		if(MobileDriver.waitForElementPresent(commonPage.lblDynamic(S2UMESSAGE), GlobalVariable.G_timeoutShort, FailureHandling.OPTIONAL)){
			MobileDriver.tap(commonPage.imgNoDynamic('last()'),  GlobalVariable.G_timeoutShort, FailureHandling.OPTIONAL)
		}
		switch(status) {
			case 'Approve':
				'Enter Number'
				commonPage.enterNO(tacNumber)
				break
			case 'Reject':
				'Remove Number'
				commonPage.removeEnterNo(6)
				commonPage.enterNO(OTP_FAIL)
				break
			case 'Expired':
				MobileDriver.delay(180)
				'Verify Static Expired'
				verifyElements(UIStaticExpired, commonPage.lblDynamicPath(), GlobalVariable.G_timeoutLong)
				'Remove Number'
				commonPage.removeEnterNo(6)
				'Enter Number'
				commonPage.enterNO(OTP_FAIL)
				break
			default:
				'Enter Number'
				commonPage.enterNO(tacNumber)
				break
		}

		return status + 'TAC Or OTP'
	}

	enum S2UStatusEnum{
		Approve, Reject, Expired
	}

	enum AuthorisationType{
		Secure2U, TAC, OTP
	}
}
