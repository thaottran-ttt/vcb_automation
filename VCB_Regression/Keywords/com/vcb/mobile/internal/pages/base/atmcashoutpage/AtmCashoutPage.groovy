package com.vcb.mobile.internal.pages.base.atmcashoutpage

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
import com.vcb.mobile.internal.pages.base.BasePage
import com.vcb.common.MobileDriver
import com.vcb.mobile.internal.pages.base.CommonPage
import com.vcb.mobile.internal.pages.base.HomePage
import internal.GlobalVariable

public abstract class AtmCashoutPage extends BasePage{
	abstract TestObject lblATMCashout(String lblName)
	abstract TestObject lblYourSmartphone()
	abstract TestObject lblNote()
	abstract TestObject rdbtnConfirm()
	abstract TestObject btnConfirm(String lblName)
	abstract TestObject lblATMCashoutSuccessful(String lblName)
	abstract TestObject lblATMCashoutSuccessfulNote1(String lblName)
	abstract TestObject lblATMCashoutSuccessfulNote2(String lblName)
	abstract TestObject lblYourATMCashout(String lblName)
	abstract TestObject lblATMCashoutActivated(String lblName)
	abstract TestObject lblTip(String lblName)
	abstract TestObject lblSecurityPurposes(String lblName)
	abstract TestObject lblAuthorisation(String lblName)
	abstract TestObject lblReferenceID(String lblName)
	abstract TestObject lblDateTime(String lblName)
	abstract TestObject lblTransactionType(String lblName)
	abstract TestObject lblYourSecureVerification(String lblName)
	abstract TestObject btnClose()
	abstract TestObject btnTick()
	abstract TestObject btnRemove()
	// ATM Cashout
	String UIlblATMCashout = 'ATM Cash-out'
	String UIbtnConfirm = 'Confirm'
	String UILblQuickActions = 'Quick Actions'
	//UI Share Receipt
	String UIShareReceipt = 'Share Receipt'
	String UIDoneBtn = 'Done'

	//UI Approve
	String UIApprove = 'Approve'
	String UIATMCashoutSuccessful = 'ATM Cash-out set up successful'
	String UILblATMCashoutSuccessfulNote1 = 'For security purposes, you\'ll have to wait 24 hours before you can use this feature.'
	String UILblATMCashoutSuccessfulNote2 = 'Your daily withdrawal limit remains the same and can be changed at an ATM.'
	String UILblSecurityPurposes = 'For security purposes, you will be able to use this feature to withdraw cash 24 hours after your first set up. Please try again later.'
	String UILblYourATMCashout = 'Your ATM Cash-out is set up and will be activated within 24 hours.'
	String UILblATMCashoutActivated = 'How to use ATM Cash-out once activated: '
	String UILblTip = 'Tips: \nFor faster future withdrawals, add your preferred withdrawal amounts before or after you withdraw.'

	//Reject Status
	String UIReject = 'Reject'
	String UILblAuthorisation = 'Authorisation failed'
	String UILblReferenceID = 'Reference ID'
	String UILblDateTime = 'Date & time'
	String UILblTransactionType = 'Transaction type'
	String UILblYourSecureVerification = 'Your Secure Verification authorisation was rejected.'
	String UILblNotInterestedInThis = 'Not Interested in This'
	//Expired Status
	String UILblYourSecureExpired = 'Your Secure Verification authorisation has expired. Please perform the transaction again.'

	//Add Preferred Amount
	String UILblYourPreferredAmount  = 'Select your preferred amount'
	String UILblAddAmount  = 'Add Amount'
	String UILblOrEnterAmount  = 'Or enter amount'
	String UILblMustBeInMultiples  = 'Must be in multiples of RM 50, up to RM 1,500'
	String UILblHowToUseATMCashOut = "How to use ATM Cash-out?"
	String UILblNowYouCanWithdraw = 'Now you can withdraw cash using your smartphone!'

	//Set Amount
	String UILblSetAmount  = 'Set Amount'
	String UILblAddPreferredAmount  = 'Add preferred amount'
	String UILblNoAmountYet  = 'No amount yet'
	String UITxtAmount = '0.00'
	String UITxtNumberOne = '1'
	String UIAdd = 'Add'
	String UILblPreferredAmountSuccessfullyAdded = 'Preferred amount successfully added.'
	String UILblAlreadyAddedThisAmount = 'You have already added this amount as your preferred amount. Please enter a different amount.'
	String UILblScanToWithdraw = 'Scan To Withdraw'
	String UILblScanPay = 'Scan & Pay'

	//Edit Amount
	String UILblRemovePreferredAmount = 'Remove preferred amount'
	String UILblRemoveThisPreferred = 'Are you sure you want to remove this preferred withdrawal amount?'
	String UICancel = 'Cancel'
	String UIConfirm = 'Confirm'
	String UIPreferredAmountRemoved = 'Preferred amount removed.'

	public static CommonPage commonPage = getPage(CommonPage)

	public boolean verifyUIlblATMCashout(String lblName = UIlblATMCashout, int timeOut = GlobalVariable.G_timeoutShort, FailureHandling flowControl = FailureHandling.OPTIONAL) {
		return Mobile.verifyElementExist(this.lblATMCashout(lblName), timeOut, flowControl)
	}

	public boolean verifyLblYourSmartphone(int timeOut = GlobalVariable.G_timeoutShort, FailureHandling flowControl = FailureHandling.OPTIONAL) {
		return Mobile.verifyElementExist(this.lblYourSmartphone(), timeOut, flowControl)
	}

	public boolean verifyLblNote(int timeOut = GlobalVariable.G_timeoutShort, FailureHandling flowControl = FailureHandling.OPTIONAL) {
		return Mobile.verifyElementExist(this.lblNote(), timeOut, flowControl)
	}

	public void tapRdbtnConfirm(int timeOut = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		MobileDriver.tap(this.rdbtnConfirm(), timeOut, flowControl)
	}

	public void tapBtnConfirm( String lblName = UIbtnConfirm, int timeOut = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		MobileDriver.tap(this.btnConfirm(lblName), timeOut, flowControl)
		Mobile.delay(GlobalVariable.G_timeoutMiniShort)
	}

	public void selectStatus(String status, FailureHandling flowControl = defaultFailureHandling) {
		switch(status) {
			case 'Expired':
				MobileDriver.waitForElementPresent(commonPage.lblDynamic(UIDoneBtn), 60 + GlobalVariable.G_timeoutLong, flowControl)
				break
			case 'Reject':
				MobileDriver.tap(commonPage.lblDynamic(UIReject), 60 + GlobalVariable.G_timeoutLong, flowControl)
				break
			default:
				MobileDriver.tap(commonPage.lblDynamic(UIApprove), GlobalVariable.G_timeoutLong, flowControl)
				info(status + ' is tapped.')
				break
		}
	}

	public void verifyStatus(String status, FailureHandling flowControl = defaultFailureHandling) {
		switch(status) {
			case 'Expired':
				Mobile.delay(GlobalVariable.G_timeoutMiniShort)
				Mobile.verifyElementExist(this.lblAuthorisation(UILblAuthorisation), GlobalVariable.G_timeoutMedium, flowControl)
				Mobile.verifyElementExist(this.lblReferenceID(UILblReferenceID), GlobalVariable.G_timeoutMedium, flowControl)
				Mobile.verifyElementExist(this.lblDateTime(UILblDateTime), GlobalVariable.G_timeoutMedium, flowControl)
				Mobile.verifyElementExist(this.lblTransactionType(UILblTransactionType), GlobalVariable.G_timeoutMedium, flowControl)
				Mobile.verifyElementExist(this.lblYourSecureVerification(UILblYourSecureExpired), GlobalVariable.G_timeoutMedium, flowControl)
				MobileDriver.tap(commonPage.lblDynamic(UIDoneBtn), GlobalVariable.G_timeoutLong, flowControl)
				break
			case 'Reject':
				Mobile.delay(GlobalVariable.G_timeoutMiniShort)
				Mobile.verifyElementExist(this.lblAuthorisation(UILblAuthorisation), GlobalVariable.G_timeoutMedium, flowControl)
				Mobile.verifyElementExist(this.lblReferenceID(UILblReferenceID), GlobalVariable.G_timeoutMedium, flowControl)
				Mobile.verifyElementExist(this.lblDateTime(UILblDateTime), GlobalVariable.G_timeoutMedium, flowControl)
				Mobile.verifyElementExist(this.lblTransactionType(UILblTransactionType), GlobalVariable.G_timeoutMedium, flowControl)
				Mobile.verifyElementExist(this.lblYourSecureVerification(UILblYourSecureVerification), GlobalVariable.G_timeoutMedium, flowControl)
				MobileDriver.tap(commonPage.lblDynamic(UIDoneBtn), GlobalVariable.G_timeoutLong, flowControl)
				break
			default:
				Mobile.delay(GlobalVariable.G_timeoutMiniShort)
				Mobile.verifyElementExist(this.lblATMCashoutSuccessful(UIATMCashoutSuccessful), GlobalVariable.G_timeoutMedium, flowControl)
				Mobile.verifyElementExist(this.lblATMCashoutSuccessfulNote1(UILblATMCashoutSuccessfulNote1), GlobalVariable.G_timeoutMedium, flowControl)
				Mobile.verifyElementExist(this.lblATMCashoutSuccessfulNote2(UILblATMCashoutSuccessfulNote2), GlobalVariable.G_timeoutMedium, flowControl)
				MobileDriver.tap(commonPage.lblDynamic(UIDoneBtn), GlobalVariable.G_timeoutLong, flowControl)
				Mobile.delay(GlobalVariable.G_timeoutMiniShort)
				Mobile.verifyElementExist(this.lblSecurityPurposes(UILblSecurityPurposes), GlobalVariable.G_timeoutMedium, flowControl)
				Mobile.verifyElementExist(this.lblYourATMCashout(UILblYourATMCashout), GlobalVariable.G_timeoutMedium, flowControl)
				Mobile.verifyElementExist(this.lblATMCashoutActivated(UILblATMCashoutActivated), GlobalVariable.G_timeoutMedium, flowControl)
				Mobile.verifyElementExist(this.lblTip(UILblTip), GlobalVariable.G_timeoutMedium, flowControl)
				MobileDriver.tap(this.btnClose(), GlobalVariable.G_timeoutLong, flowControl)
				break
		}
	}

	public void addPreferredAmount(int timeOut = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		'ATM Cashout Screen Title'
		Mobile.delay(GlobalVariable.G_timeoutMiniShort)
		if(Mobile.waitForElementPresent(commonPage.lblDynamic(UILblYourPreferredAmount), GlobalVariable.G_timeoutLong)) {
			'Verify UI Label Your Preferred Amount'
			Mobile.verifyElementExist(commonPage.lblDynamic(UILblYourPreferredAmount), GlobalVariable.G_timeoutMedium, flowControl)
			'Verify UI Label Add Amount'
			Mobile.verifyElementExist(commonPage.lblDynamic(UILblAddAmount), GlobalVariable.G_timeoutMedium, flowControl)
			'Verify UI Label Or Enter Amount'
			Mobile.verifyElementExist(commonPage.lblDynamic(UILblOrEnterAmount), GlobalVariable.G_timeoutMedium, flowControl)
			'Verify UI Label Must Be InMultiples'
			Mobile.verifyElementExist(commonPage.lblDynamic(UILblMustBeInMultiples), GlobalVariable.G_timeoutMedium, flowControl)
			Mobile.takeScreenshot()
			'Select 3 Dot Button'
			MobileDriver.tap(commonPage.imgNoDynamic(2), GlobalVariable.G_timeoutLong, flowControl)
			if (Mobile.verifyElementExist(commonPage.lblDynamic(UILblHowToUseATMCashOut), GlobalVariable.G_timeoutLong, flowControl)) {
				'Verify UI Label How To Use ATM Cash Out'
				Mobile.verifyElementExist(commonPage.lblDynamic(UILblHowToUseATMCashOut), GlobalVariable.G_timeoutMedium, flowControl)
				'Select UI Labl How To Use ATM Cash Out'
				MobileDriver.tap(commonPage.lblDynamic(UILblHowToUseATMCashOut), GlobalVariable.G_timeoutLong, flowControl)
				'Verify UI Label Now You Can Withdraw'
				Mobile.verifyElementExist(commonPage.lblDynamic(UILblNowYouCanWithdraw), GlobalVariable.G_timeoutMedium, flowControl)
				'Select 3 Dot Button'
				MobileDriver.tap(commonPage.imgNoDynamic(2), GlobalVariable.G_timeoutLong, flowControl)
				'Verify UI Label Not Interested In This'
				Mobile.verifyElementExist(commonPage.lblDynamic(UILblNotInterestedInThis), GlobalVariable.G_timeoutMedium, flowControl)
				'Select Close Button'
				MobileDriver.tap(commonPage.imgNoDynamic(1), GlobalVariable.G_timeoutLong, flowControl)
				'Verify UI Label Now You Can Withdraw'
				Mobile.verifyElementExist(commonPage.lblDynamic(UILblNowYouCanWithdraw), GlobalVariable.G_timeoutMedium, flowControl)
				'Select Back Button'
				MobileDriver.tap(commonPage.imgNoDynamic(1), GlobalVariable.G_timeoutLong, flowControl)
			}
			'Verify UI Label Your Preferred Amount'
			Mobile.verifyElementExist(commonPage.lblDynamic(UILblYourPreferredAmount), GlobalVariable.G_timeoutMedium, flowControl)
			'Select Add Amount'
			MobileDriver.tap(commonPage.lblDynamic(UILblAddAmount), GlobalVariable.G_timeoutLong, flowControl)
			Mobile.delay(GlobalVariable.G_timeoutMiniShort)
			'Verify UI Label Set Amount'
			Mobile.verifyElementExist(commonPage.lblDynamic(UILblSetAmount), GlobalVariable.G_timeoutMedium, flowControl)
			'Verify UI Label Add Preferred Amount'
			Mobile.verifyElementExist(commonPage.lblDynamic(UILblAddPreferredAmount), GlobalVariable.G_timeoutMedium, flowControl)
			'Verify UILbl Must Be In Multiples'
			Mobile.verifyElementExist(commonPage.lblDynamic(UILblMustBeInMultiples), GlobalVariable.G_timeoutMedium, flowControl)
			'No amount yet?'
			if (Mobile.verifyElementExist(commonPage.lblDynamic(UILblNoAmountYet), GlobalVariable.G_timeoutMedium, flowControl)) {
				'Verify UI Label No Amount Yet'
				Mobile.verifyElementExist(commonPage.lblDynamic(UILblNoAmountYet), GlobalVariable.G_timeoutMedium, flowControl)
			} else {
				'Log info: User doesnt have amount added prior'
				info('User doesnt have amount added prior')
			}
			Mobile.takeScreenshot()
			'Select UI TextAmount'
			MobileDriver.tap(commonPage.lblDynamic(UITxtAmount), GlobalVariable.G_timeoutLong, flowControl)
			'Enter Amount'
			commonPage.enterNO('1')
			'Verify UI Label Must Be In Multiples'
			Mobile.verifyElementExist(commonPage.lblDynamic(UILblMustBeInMultiples), GlobalVariable.G_timeoutMedium, flowControl)
			'Verify UIText Amount'
			UITxtAmount = '0.01'
			Mobile.verifyElementExist(commonPage.lblDynamic(UITxtAmount), GlobalVariable.G_timeoutMedium, flowControl)
			'Verify UI Text Number One Not Exist'
			Mobile.verifyElementNotExist(commonPage.lblDynamic(UITxtNumberOne), GlobalVariable.G_timeoutMedium, flowControl)
			'Select UI TextAmount'
			MobileDriver.tap(commonPage.lblDynamic(UITxtAmount), GlobalVariable.G_timeoutLong, flowControl)
			'Select Remove'
			MobileDriver.tap(this.btnRemove(), GlobalVariable.G_timeoutLong, flowControl)
			'Enter Amount'
			UITxtAmount = '5000'
			commonPage.enterNO(UITxtAmount)
			'Verify Add Button'
			Mobile.verifyElementExist(commonPage.lblDynamic(UIAdd), GlobalVariable.G_timeoutMedium, flowControl)
			'Select Add Button'
			MobileDriver.tap(commonPage.lblDynamic(UIAdd), GlobalVariable.G_timeoutLong, flowControl)
			'Verify UI Label Preferred Amount Successfully Added'
			Mobile.verifyElementExist(commonPage.lblDynamic(UILblPreferredAmountSuccessfullyAdded), GlobalVariable.G_timeoutMedium, flowControl)
			Mobile.takeScreenshot()
			'Verify Amount'
			UITxtAmount = '50.00'
			Mobile.verifyElementNotExist(commonPage.lblDynamic(UITxtAmount), GlobalVariable.G_timeoutMedium, flowControl)
			'Select UI Label Set Amount'
			MobileDriver.tap(commonPage.lblDynamic(UILblSetAmount), GlobalVariable.G_timeoutMedium, flowControl)
			'Verify UI Label Add Preferred Amount'
			Mobile.verifyElementExist(commonPage.lblDynamic(UILblAddPreferredAmount), GlobalVariable.G_timeoutMedium, flowControl)
			'Select Amount'
			UITxtAmount = '0.00'
			MobileDriver.tap(commonPage.lblDynamic(UITxtAmount), GlobalVariable.G_timeoutMedium, flowControl)
			'Enter Amount'
			UITxtAmount = '5000'
			commonPage.enterNO(UITxtAmount)
			'Verify UI Label Already Added This Amount'
			Mobile.verifyElementExist(commonPage.lblDynamic(UILblAlreadyAddedThisAmount), GlobalVariable.G_timeoutMedium, flowControl)
			Mobile.takeScreenshot()
			'Select Back Button'
			MobileDriver.tap(commonPage.imgNoDynamic(1), GlobalVariable.G_timeoutLong, flowControl)
			'Verify UI Label Set Amount'
			Mobile.verifyElementExist(commonPage.lblDynamic(UILblSetAmount), GlobalVariable.G_timeoutMedium, flowControl)
			Mobile.delay(GlobalVariable.G_timeoutMiniShort)
			'Select Amount'
			UITxtAmount = 'RM 50.00'
			MobileDriver.tap(commonPage.lblDynamic(UITxtAmount), GlobalVariable.G_timeoutMedium, flowControl)
			'Verify UI Label Scan To Withdraw'
			Mobile.verifyElementExist(commonPage.lblDynamic(UILblScanToWithdraw), GlobalVariable.G_timeoutMedium, flowControl)
			'Select UI Label Scan To Withdraw'
			MobileDriver.tap(commonPage.lblDynamic(UILblScanToWithdraw), GlobalVariable.G_timeoutMedium, flowControl)
			'Verify UI Label Scan Pay'
			Mobile.verifyElementExist(commonPage.lblDynamic(UILblScanPay), GlobalVariable.G_timeoutMedium, flowControl)
			Mobile.takeScreenshot()
			'Select Close Button'
			MobileDriver.tap(commonPage.imgNoDynamic(2), GlobalVariable.G_timeoutLong, flowControl)
			'Verify UI Label Set Amount'
			Mobile.verifyElementExist(commonPage.lblDynamic(UILblSetAmount), GlobalVariable.G_timeoutMedium, flowControl)
			'Select Back Button'
			MobileDriver.tap(commonPage.imgNoDynamic(1), GlobalVariable.G_timeoutLong, flowControl)
		}else if(Mobile.waitForElementPresent(commonPage.lblDynamic(UILblYourATMCashout), GlobalVariable.G_timeoutLong)) {
			'ATM Cash-out is set up and will be activated within 24 hours'
			Mobile.comment('ATM Cash-out is set up and will be activated within 24 hours')
			Mobile.takeScreenshot()
		}
	}

	public void editPreferredAmount(int timeOut = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		'ATM Cashout Screen Title'
		Mobile.delay(GlobalVariable.G_timeoutMiniShort)
		if(Mobile.waitForElementPresent(commonPage.lblDynamic(UILblYourPreferredAmount), GlobalVariable.G_timeoutLong)) {
			'Verify UI Label Your Preferred Amount'
			Mobile.verifyElementExist(commonPage.lblDynamic(UILblYourPreferredAmount), GlobalVariable.G_timeoutMedium, flowControl)
			'Select UI Label Set Amount'
			MobileDriver.tap(commonPage.lblDynamic(UILblSetAmount), GlobalVariable.G_timeoutMedium, flowControl)
			'Verify UI Label Add Preferred Amount'
			Mobile.verifyElementExist(commonPage.lblDynamic(UILblAddPreferredAmount), GlobalVariable.G_timeoutMedium, flowControl)
			Mobile.takeScreenshot()
			'Select Remove Record Amount'
			MobileDriver.tap(commonPage.imgNoDynamic(3), GlobalVariable.G_timeoutLong, flowControl)
			'Verify UI Label Remove Preferred Amount'
			Mobile.verifyElementExist(commonPage.lblDynamic(UILblRemovePreferredAmount), GlobalVariable.G_timeoutMedium, flowControl)
			'Verify UI Label Remove This Preferred'
			Mobile.verifyElementExist(commonPage.lblDynamic(UILblRemoveThisPreferred), GlobalVariable.G_timeoutMedium, flowControl)
			Mobile.takeScreenshot()
			'Select Cancel Button'
			MobileDriver.tap(commonPage.lblDynamic(UICancel), GlobalVariable.G_timeoutLong, flowControl)
			'Verify Amount'
			UITxtAmount = 'RM 50.00'
			Mobile.verifyElementExist(commonPage.lblDynamic(UITxtAmount), GlobalVariable.G_timeoutMedium, flowControl)
			Mobile.takeScreenshot()
			'Select Remove Record Amount'
			MobileDriver.tap(commonPage.imgNoDynamic(3), GlobalVariable.G_timeoutLong, flowControl)
			'Verify UI Label Remove Preferred Amount'
			Mobile.verifyElementExist(commonPage.lblDynamic(UILblRemovePreferredAmount), GlobalVariable.G_timeoutMedium, flowControl)
			'Verify UI Label Remove This Preferred'
			Mobile.verifyElementExist(commonPage.lblDynamic(UILblRemoveThisPreferred), GlobalVariable.G_timeoutMedium, flowControl)
			'Select Close Button'
			MobileDriver.tap(commonPage.imgNoDynamic(1), GlobalVariable.G_timeoutLong, flowControl)
			'Verify Amount'
			UITxtAmount = 'RM 50.00'
			Mobile.verifyElementExist(commonPage.lblDynamic(UITxtAmount), GlobalVariable.G_timeoutMedium, flowControl)
			Mobile.takeScreenshot()
			'Select Remove Record Amount'
			MobileDriver.tap(commonPage.imgNoDynamic(3), GlobalVariable.G_timeoutLong, flowControl)
			'Select Confirm Button'
			MobileDriver.tap(commonPage.lblDynamic(UIConfirm), GlobalVariable.G_timeoutLong, flowControl)
			'Verify UI Preferred Amount Removed'
			Mobile.verifyElementExist(commonPage.lblDynamic(UIPreferredAmountRemoved), GlobalVariable.G_timeoutMedium, flowControl)
			'Verify Amount Not Exist'
			UITxtAmount = 'RM 50.00'
			Mobile.verifyElementNotExist(commonPage.lblDynamic(UITxtAmount), GlobalVariable.G_timeoutLong, flowControl)
			Mobile.takeScreenshot()
			'Select Back Button'
			MobileDriver.tap(commonPage.imgNoDynamic(1), GlobalVariable.G_timeoutLong, flowControl)
			'Verify UI Label Or Enter Amount'
			Mobile.verifyElementExist(commonPage.lblDynamic(UILblOrEnterAmount), GlobalVariable.G_timeoutMedium, flowControl)
			Mobile.takeScreenshot()
			'Select Back Button'
			MobileDriver.tap(commonPage.imgNoDynamic(1), GlobalVariable.G_timeoutLong, flowControl)
			'Verify UI Label Quick Actions'
			Mobile.verifyElementExist(commonPage.lblDynamic(UILblQuickActions), GlobalVariable.G_timeoutMedium, flowControl)
		}else if(Mobile.waitForElementPresent(commonPage.lblDynamic(UILblYourATMCashout), GlobalVariable.G_timeoutLong)) {
			'ATM Cash-out is set up and will be activated within 24 hours'
			Mobile.comment('ATM Cash-out is set up and will be activated within 24 hours')
			Mobile.takeScreenshot()
		}
	}
	
	public void backToHomePage(int timeOut = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		'ATM Cashout Screen Title'
		Mobile.delay(GlobalVariable.G_timeoutMiniShort)
		if(Mobile.waitForElementPresent(commonPage.lblDynamic(UIlblATMCashout), GlobalVariable.G_timeoutMedium)) {
			'Select Back Button'
			MobileDriver.tap(commonPage.imgNoDynamic(1), GlobalVariable.G_timeoutLong, flowControl)
		}
	}
}
