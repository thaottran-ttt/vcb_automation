package com.vcb.mobile.internal.pages.base.paybill

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
import com.vcb.mobile.internal.pages.base.BasePage
import com.vcb.mobile.internal.pages.base.CommonPage
import com.vcb.utils.StringUtils

import internal.GlobalVariable

public abstract class PayBillPage extends BasePage{

	abstract TestObject imgSearch()
	abstract TestObject imgV()
	CommonPage commonPage = getPage(CommonPage)

	static final FAVOURITES = 'Favourites'
	String UIPayBills = 'Pay Bills'

	//Wrong Bill
	String UIPaymentUnsuccessful = 'Payment unsuccessful'
	String UIInvalidPayeeDetail = 'Invalid Payee details'

	//General
	String UIContinute = 'Continue'

	void tapPayBill(FailureHandling flowControl = defaultFailureHandling) {
		'Select Pay Bills Tab'
		MobileDriver.tap(commonPage.lblDynamicTextLast(UIPayBills), GlobalVariable.G_timeoutLong, flowControl)
		'Wait Loading'
		commonPage.waitLoading()
	}

	void selectFavourites(String favName, FailureHandling flowControl = defaultFailureHandling) {
		'Tap Icon Search'
		MobileDriver.tap(commonPage.imgSiblingGroup(FAVOURITES), GlobalVariable.G_timeoutMedium, flowControl)

		'Search Favourite'
		MobileDriver.setText(commonPage.txtEditNo(1), favName, GlobalVariable.G_timeoutMedium, flowControl)

		Mobile.delay(1)

		'Tap First Favourites'
		MobileDriver.tap(commonPage.lblTextIgnoreCaseNo(favName, 2), GlobalVariable.G_timeoutMedium, flowControl)

		if(MobileDriver.waitForElementPresent(commonPage.lblDynamic(FAVOURITES), GlobalVariable.G_timeoutShort, FailureHandling.OPTIONAL)) {
			'Tap First Favourites Again'
			MobileDriver.tap(commonPage.lblTextIgnoreCaseNo(favName, 2), GlobalVariable.G_timeoutMedium, flowControl)
		}
	}

	void searchPayBill(String text, FailureHandling flowControl = defaultFailureHandling) {
		Mobile.delay(1)
		'Select Search Image'
		MobileDriver.tap(commonPage.imgNoDynamic(2), GlobalVariable.G_timeoutLong, flowControl)
		'Enter Pay Bill Numner'
		MobileDriver.setText(commonPage.txtEditNo(1), text, GlobalVariable.G_timeoutLong, flowControl)
		'Select Dynamic Text'
		MobileDriver.tap(commonPage.lblTextIgnoreCaseNo(text, 2), GlobalVariable.G_timeoutLong, flowControl)
		
		if(MobileDriver.waitForElementPresent(commonPage.lblTextIgnoreCaseNo(text, 2), GlobalVariable.G_timeoutShort, FailureHandling.OPTIONAL)) {
			MobileDriver.tap(commonPage.lblTextIgnoreCaseNo(text, 2), GlobalVariable.G_timeoutLong, flowControl)
		}
		Mobile.delay(GlobalVariable.G_timeoutMiniShort)
	}

	/*
	 * verifyWrongBiller: verify Wrong Biller
	 */
	void verifyWrongBiller(List<String> input) {
		'Enter Biller Number 1'
		MobileDriver.setText(commonPage.txtEditNo(1), input[0], GlobalVariable.G_timeoutLong)
		'Enter Biller Number 2'
		MobileDriver.setText(commonPage.txtEditNo(2), input[1], GlobalVariable.G_timeoutLong)
		'Select Continue Button'
		MobileDriver.tap(commonPage.lblDynamic(UIContinute), GlobalVariable.G_timeoutLong)
	}

	/*
	 * setBiller
	 */
	void setBiller(List<String> input) {
		'Enter Biller Number 1'
		MobileDriver.setText(commonPage.txtEditNo(1), input[0], GlobalVariable.G_timeoutLong)
		if(MobileDriver.waitForElementPresent(commonPage.txtEditNo(2), GlobalVariable.G_timeoutShort, FailureHandling.OPTIONAL)) {
			'Enter Biller Number 2'
			MobileDriver.setText(commonPage.txtEditNo(2), input[1], GlobalVariable.G_timeoutLong)
		}
		'Select Continue Button'
		MobileDriver.tap(commonPage.lblDynamic(UIContinute), GlobalVariable.G_timeoutLong)
	}

	/*
	 * verifyMinAmount: Verify Enter Amount
	 */
	void verifyMinAmount(String minAmount, String errorMessage, FailureHandling flowControl = defaultFailureHandling) {
		'Enter Min Amount'
		commonPage.enterNO(minAmount, true, '', 0)
		'Verify Text - The minimum payment for this transaction is RM 1'
		MobileDriver.verifyElementVisible(commonPage.lblDynamic(errorMessage), GlobalVariable.G_timeoutLong, flowControl)
		
		'Tap Toast Msg to Close'
		MobileDriver.tap(commonPage.lblDynamic(errorMessage), GlobalVariable.G_timeoutLong, flowControl)
		
		'Take Screenshot'
		MobileDriver.takeScreenshot()
	}

	/*
	 * verifyMaxAmount: Verify Max Amount
	 */
	void verifyMaxAmount(String maxAmount, FailureHandling flowControl = defaultFailureHandling) {
		'Enter Max Amount'
		commonPage.enterNO(maxAmount, false)
		
		'Get Text UI Amount'
		String amount = MobileDriver.getText(commonPage.txtEditNo(1), GlobalVariable.G_timeoutLong, flowControl)
		'Verify Max Amount'
		BaseAssert.equals(amount, StringUtils.formatToMoney(maxAmount), 'Verify Enter Max Amount')	
		
		'Take Screenshot'
		Mobile.takeScreenshot()
	}

	/*
	 * verifyEditMinMaxAmount: Verify Enter Min Amount And Enter Max Amount
	 */
	void verifyEditMinMaxAmount(String maxAmount, String minAmount, String errorMinAmountMsg, FailureHandling flowControl = defaultFailureHandling) {
		'Verify Enter MinAmount'
		this.verifyMinAmount(minAmount, errorMinAmountMsg, flowControl)
		
		'Back to Enter Amount'
		commonPage.removeEnterNo(minAmount.length())

		'Verify Enter Max Amount'
		this.verifyMaxAmount(maxAmount, flowControl)
	}

	/*
	 * Default: include Open (Approve, Reject, Expired), Fav, Future
	 * 
	 */
	enum PayBillTestCaseType{
		Default, WrongBiller, InsufficientAmount, EditMinMaxAmount
	}
}
