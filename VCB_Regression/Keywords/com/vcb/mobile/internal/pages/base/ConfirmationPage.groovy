package com.vcb.mobile.internal.pages.base

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
import com.vcb.assertions.BaseAssert
import com.vcb.common.MobileDriver
import com.vcb.mobile.internal.pages.base.transferpage.RecipientReferencePage
import com.vcb.utils.DateUtils
import com.vcb.utils.StringUtils

import internal.GlobalVariable

public abstract class ConfirmationPage extends BasePage {

	abstract TestObject imgV()
	abstract TestObject frmScrollAccount()
	abstract TestObject lblDefaultAccountName()
	String Confirmation = 'Confirmation'
	String TransferFrom = 'Transfer from'
	String UIToday = 'Today'
	String Done = 'Done'
	String StartDate = 'Start date'
	String EndDate = 'End date'
	String TransferNow = 'Transfer Now'
	String TransferTypeData = 'One-Off'
	String Recurring = 'Recurring'
	String RecipientReference = 'Recipient reference'
	String UIDate = 'Date'
	String UIPayFrom = 'Pay from'
	String PayNow = 'Pay Now'
	String IDType = 'ID type'
	String ServiceFee = 'Service fee'
	String RM = "RM"
	String balance = ''
	String TermAndCondition = "I have read and agree to the BNM Foreign"
	String Confirm = "Confirm"

	//FD
	static final String DATE = 'Date'
	static final String CERTIFICATE_NUMBER = 'Certificate number'
	static final String MATURITY_DATE = 'Maturity date'
	static final String MATURITY_INSTRUCTION = 'Maturity instruction'
	static final String CREDITING_ACCOUNT = 'Crediting account'
	static final String TENURE = 'Tenure'
	static final String PAYMENT_FREQUENCY = 'Payment frequency'
	static final String UPDATE_PLACEMENT = 'Update Placement'
	static final List AMOUNT_EDITABLE = [
		'Amount editable',
		'Editable',
		'Not Editable'
	]
	static final List NOTIFY_VIA_EMAIL = ['Notify via email']
	static final String INSUFFICIENT_FUND = 'Your account balance is insufficient.'


	CommonPage commonPage = getPage(CommonPage)
	EnterAmountPage enterAmountPage = getPage(EnterAmountPage)
	RecipientReferencePage recipientReferencePage = getPage(RecipientReferencePage)

	/**
	 * waitLoadScreen: wait for Confirmation title display
	 * @return
	 */
	boolean waitLoadScreen() {
		return MobileDriver.waitForElementPresent(commonPage.lblDynamic(Confirmation), GlobalVariable.G_timeoutLong, FailureHandling.OPTIONAL)
	}

	/**
	 * verifyHasLandedOnConfirmationScreen: verify UI static Confirmation Screen
	 * @return
	 */
	void verifyHasLandedOnConfirmationScreen(String accNo = '', FailureHandling flowControl = defaultFailureHandling) {
		verifyElements([Confirmation, TransferFrom], commonPage.lblDynamicPath(), GlobalVariable.G_timeoutLong)
		MobileDriver.verifyElementVisible(this.imgV(), GlobalVariable.G_timeoutLong)
		MobileDriver.takeScreenshot()
	}

	/**
	 * futureFlow: change today to tomorrow
	 * @return
	 */
	void futureFlow(FailureHandling flowControl = defaultFailureHandling) {
		MobileDriver.tap(commonPage.lblDynamic(UIToday), GlobalVariable.G_timeoutLong, flowControl)
		Mobile.takeScreenshot()
		Mobile.delay(1)
		commonPage.tomorrowDatePicker()
	}

	/**
	 * recurringFlow: change transferType to Recurring Value
	 * @return
	 */
	void recurringFlow(FailureHandling flowControl = defaultFailureHandling) {
		MobileDriver.tap(commonPage.lblContainDynamic(TransferTypeData), GlobalVariable.G_timeoutLong, flowControl)
		verifyElements([Done], commonPage.lblDynamicPath(), GlobalVariable.G_timeoutLong)
		MobileDriver.tap(commonPage.lblContainDynamic(Recurring), GlobalVariable.G_timeoutLong, flowControl)
		Mobile.takeScreenshot()
		commonPage.tapText(Done, GlobalVariable.G_timeoutLong, flowControl)

		'Verify UI after changing transfer type'
		verifyElements(['Transfer type', 'Recurring'], commonPage.lblDynamicPath(), GlobalVariable.G_timeoutLong)
	}

	/**
	 * verifyConfirmationScreenUICorrect: verify UI base on futuretype
	 * @return
	 */
	void verifyConfirmationScreenUICorrect(String futuretype = '', flowControl = defaultFailureHandling) {
		switch(futuretype) {
			case 'Recurring':
				verifyElements([StartDate, EndDate], commonPage.lblContainDynamicPath(), GlobalVariable.G_timeoutMedium)
				break
			default:
				verifyElements([UIDate, IDType], commonPage.lblDynamicPath(), GlobalVariable.G_timeoutMedium)
				commonPage.swipe(5, 1000, 5, 10)
				verifyElements([ServiceFee], commonPage.lblDynamicPath(), GlobalVariable.G_timeoutMedium)
		}
	}

	/**
	 * proceedToAuthorisation: tap Transfer Now
	 * @return
	 */
	void proceedToAuthorisation(flowControl = defaultFailureHandling) {
		Mobile.takeScreenshot()
		commonPage.tapText(TransferNow, GlobalVariable.G_timeoutLong, flowControl)
		Mobile.delay(GlobalVariable.G_timeoutMiniShort)
	}

	/**
	 * verifyFieldValidationConfirmation: Edit amount, edit Recipient Reference
	 * @return
	 */
	void verifyFieldValidationConfirmation(String editAmount, String editRecipientReference, FailureHandling flowControl = defaultFailureHandling) {
		MobileDriver.takeScreenshot()
		Mobile.scrollToText(TransferFrom)
		'1. Edit Amount'
		this.editAmount(editAmount)
		'2. Edit Recipient Reference'
		this.editRecipientReference(editRecipientReference)
	}

	/**
	 * verifyFieldValidationConfirmation: Edit amount
	 * @return
	 */
	void editAmount(String editAmount, FailureHandling flowControl = defaultFailureHandling) {
		MobileDriver.tap(commonPage.lblRMNo(1), GlobalVariable.G_timeoutLong, flowControl)
		enterAmountPage.editEnterAmount(editAmount)
		MobileDriver.verifyElementVisible(commonPage.lblDynamic(Confirmation), GlobalVariable.G_timeoutLong, flowControl)
		Mobile.scrollToText(RecipientReference)
	}

	/**
	 * verifyFieldValidationConfirmation: Recipient Reference
	 * @return
	 */
	private void editRecipientReference(String editRecipientReference, FailureHandling flowControl = defaultFailureHandling) {
		commonPage.swipe(5, 1500, 5, 100)
		MobileDriver.tap(commonPage.lblSiblingOneGroup(RecipientReference), GlobalVariable.G_timeoutLong, flowControl)
		recipientReferencePage.inputRecipientReference(editRecipientReference)
		Mobile.delay(1)
		Mobile.scrollToText(editRecipientReference)
		MobileDriver.verifyElementVisible(commonPage.lblDynamic(editRecipientReference), GlobalVariable.G_timeoutLong, flowControl)
		MobileDriver.takeScreenshot()
	}
	/**
	 * verifyFieldValidationConfirmation: Recipient Reference
	 * @return
	 */
	void editRecipientReferenceSpecialCase(String editRecipientReference, FailureHandling flowControl = defaultFailureHandling) {
		MobileDriver.scrollToEnd()
		MobileDriver.tap(commonPage.lblSiblingOneGroup(RecipientReference), GlobalVariable.G_timeoutLong, flowControl)
		recipientReferencePage.inputRecipientReference(editRecipientReference)
		Mobile.delay(1)
		Mobile.scrollToText(editRecipientReference)
		MobileDriver.verifyElementVisible(commonPage.lblDynamic(editRecipientReference), GlobalVariable.G_timeoutLong, flowControl)
		MobileDriver.takeScreenshot()
	}

	/**
	 * confirmLoan: Verify UI Static of confirmation Loan screen 
	 * @return
	 */
	void confirmLoan(FailureHandling flowControl = defaultFailureHandling) {
		verifyElements([UIDate, UIPayFrom, UIToday], commonPage.lblDynamicPath(), GlobalVariable.G_timeoutLong)
		MobileDriver.verifyElementVisible(commonPage.lblRMNo(1), GlobalVariable.G_timeoutLong, flowControl)

		MobileDriver.tap(commonPage.lblDynamic(PayNow), GlobalVariable.G_timeoutLong, flowControl)
	}

	/**
	 * confirmManageFD: Verify UI Static of confirmation ManageFD screen
	 * @return
	 */
	void confirmManageFD(FailureHandling flowControl = defaultFailureHandling) {
		MobileDriver.verifyElementVisible(commonPage.lblRMNo(1), GlobalVariable.G_timeoutLong, flowControl)

		verifyElements([
			DATE,
			CERTIFICATE_NUMBER,
			MATURITY_DATE,
			MATURITY_INSTRUCTION,
			CREDITING_ACCOUNT,
			TENURE,
			PAYMENT_FREQUENCY
		], commonPage.lblDynamicPath(), GlobalVariable.G_timeoutLong, flowControl)

		String date = MobileDriver.getText(commonPage.lblSiblingOneGroup(DATE), GlobalVariable.G_timeoutLong, flowControl)
		String maturityDate = MobileDriver.getText(commonPage.lblSiblingOneGroup(MATURITY_DATE), GlobalVariable.G_timeoutLong, flowControl)
		BaseAssert.equals(verifyFormatDate([date, maturityDate]), true, 'Verify Format Date')

		MobileDriver.tap(commonPage.lblDynamic(UPDATE_PLACEMENT), GlobalVariable.G_timeoutLong, flowControl)
	}

	/**
	 * confirmSpecialCase: edit Amount
	 * @return
	 */
	void confirmSpecialCase(String editAmount, FailureHandling flowControl = defaultFailureHandling) {
		this.waitLoadScreen()
		MobileDriver.verifyElementVisible(this.imgV(), GlobalVariable.G_timeoutLong)
		MobileDriver.tap(commonPage.lblRMNo(1), GlobalVariable.G_timeoutLong, flowControl)
		enterAmountPage.editEnterAmount(editAmount)
	}

	/**
	 * confirm: verify UI dynamic confirmation page base on moduleName, verifyData need to validation
	 * @return
	 */
	void confirm(String moduleName, Map verifyData, FailureHandling flowControl = defaultFailureHandling) {
		MobileDriver.verifyElementVisible(commonPage.lblRMNo(1), GlobalVariable.G_timeoutLong, flowControl)
		MobileDriver.verifyElementVisible(commonPage.lblDynamic(UIPayFrom), GlobalVariable.G_timeoutLong, flowControl)

		MobileDriver.verifyElementVisible(this.imgV(), GlobalVariable.G_timeoutLong)
		switch(moduleName) {
			case 'Zakat':
				MobileDriver.verifyElementVisible(commonPage.lblDynamic(UIDate), GlobalVariable.G_timeoutLong, flowControl)
				commonPage.verifyDataAndUI(verifyData)
				MobileDriver.tap(commonPage.lblDynamic(PayNow), GlobalVariable.G_timeoutLong, flowControl)
				break
		}
	}

	/**
	 * verifyConfirmation: verify UI dynamic confirmation page base on moduleName, verifyData need to validation
	 * @return
	 */
	void verifyConfirmation(List confirmationData, String nextPage, String accountName = '', FailureHandling flowControl = defaultFailureHandling) {
		'Verify RM xx'
		MobileDriver.verifyElementVisible(commonPage.lblRMNo(1), GlobalVariable.G_timeoutLong, flowControl)
		'Verify selected Account'
		MobileDriver.verifyElementVisible(this.imgV(), GlobalVariable.G_timeoutLong)
		'Verify UI Confirmation Page'
		verifyElements(confirmationData, commonPage.lblNormalizeSpacePath(), GlobalVariable.G_timeoutMedium, flowControl)

		'Select another account if exist else select the default one'
		if(accountName != '' && accountName != "''") {
			chooseAnotherAccount(accountName)
		}else {
			MobileDriver.tap(this.lblDefaultAccountName(), GlobalVariable.G_timeoutMedium, FailureHandling.OPTIONAL)
		}

		'Tap Next Page'
		MobileDriver.waitForElementPresent(commonPage.lblDynamic(nextPage), GlobalVariable.G_timeoutLong, FailureHandling.OPTIONAL)
		MobileDriver.tap(commonPage.lblDynamic(nextPage), GlobalVariable.G_timeoutLong, flowControl)
	}

	void chooseAnotherAccount(String accountName, FailureHandling flowControl = defaultFailureHandling) {
		commonPage.scrollToTestObject(this.frmScrollAccount(), commonPage.lblDynamic(accountName), ScrollDirectionEnum.RIGHT, GlobalVariable.G_timeoutMedium , flowControl)
		MobileDriver.tap(commonPage.lblDynamic(accountName),  GlobalVariable.G_timeoutLong, flowControl)
	}

	void selectAmountEditableType(boolean isEdit=true, FailureHandling flowControl = defaultFailureHandling) {
		MobileDriver.tap(commonPage.lblSiblingOneGroup(AMOUNT_EDITABLE[0]), GlobalVariable.G_timeoutMedium, flowControl)
		if(isEdit) {
			MobileDriver.tap(commonPage.lblDynamic(AMOUNT_EDITABLE[1]), GlobalVariable.G_timeoutMedium, flowControl)
		}else {
			MobileDriver.tap(commonPage.lblDynamic(AMOUNT_EDITABLE[2]), GlobalVariable.G_timeoutMedium, flowControl)
		}
		commonPage.tapDoneBtn()
	}

	void inputNotifyViaEmail(List notifyViaEmailData, FailureHandling flowControl = defaultFailureHandling) {
		MobileDriver.setText(commonPage.txtEditNo(2), notifyViaEmailData[0], GlobalVariable.G_timeoutMedium, flowControl)
	}

	void verifyNotifyViaEmail(List notifyViaEmailData, FailureHandling flowControl = defaultFailureHandling) {
		MobileDriver.verifyElementVisible(commonPage.lblDynamic(notifyViaEmailData[1]), GlobalVariable.G_timeoutMedium, flowControl)
	}

	void verifyRetailFxConfirmScreen(String transactionType, FailureHandling flowControl = defaultFailureHandling) {
		'Verify Confirmation'
		MobileDriver.verifyElementVisible(commonPage.lblDynamic(Confirmation), GlobalVariable.G_timeoutMedium, flowControl)
		'Verify Transaction Type'
		MobileDriver.verifyElementVisible(commonPage.lblDynamic(transactionType), GlobalVariable.G_timeoutMedium, flowControl)
		'Scroll to Term And Condition'
		commonPage.swipeByW3CAction(500, 1500, 500, 500)
		Mobile.delay(GlobalVariable.G_timeoutMiniShort)
		'Select Term And Condition'
		MobileDriver.tap(commonPage.lblContainDynamic(TermAndCondition), GlobalVariable.G_timeoutMedium, flowControl)
		'Select Confirm'
		MobileDriver.tap(commonPage.lblDynamic(Confirm), GlobalVariable.G_timeoutMedium, flowControl)
	}
	String getBalanceAndSendAccountNumber(String accountName, FailureHandling flowControl = defaultFailureHandling) {
		String accountNumber = Mobile.getText(commonPage.lblSiblingDynamic(accountName), GlobalVariable.G_timeoutMedium, FailureHandling.STOP_ON_FAILURE)
		balance = Mobile.getText(commonPage.lblSiblingDynamic(accountNumber), GlobalVariable.G_timeoutMedium, FailureHandling.STOP_ON_FAILURE).split(" ")[1]
		return accountNumber
	}
	boolean verifyInsufficientMessage() {
		return this.verifyElementVisible(commonPage.lblContainDynamic(INSUFFICIENT_FUND), GlobalVariable.G_timeoutLong, FailureHandling.OPTIONAL)
	}
}
