package com.vcb.mobile.internal.pages.base.applypage

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
import java.time.format.DateTimeFormatter;
import com.vcb.mobile.internal.pages.base.BasePage
import com.vcb.mobile.internal.pages.base.CommonPage
import com.vcb.mobile.internal.pages.base.CommonPage.DropDownTypeEnum
import com.vcb.mobile.internal.pages.base.StatusScreenPage
import internal.GlobalVariable
import java.time.LocalDate

public class ApplyPageInsuranceMotorcycle extends BasePage {

	CommonPage commonPage = getPage(CommonPage)
	StatusScreenPage statusScreenPage = getPage(StatusScreenPage)

	static final String NEXT = 'NEXT'
	static final String PROCEED = 'Proceed'
	static final String AGREE_AND_CONFIRM = 'Agree and Confirm'
	static final String UPLOAD_LATER = 'Upload Later'
	static final String TERMS_CONDITIONS = 'Terms & Conditions'
	static final String SAVE_CHANGES = 'Save Changes'
	static final String APPROVE = 'Approve'
	static final String REJECT = 'Reject'
	static final String APPLY = 'Apply Now'
	static final String APPLY_INSURANCE = 'Apply Insurance'
	static final String SELECT_COUNTRY = 'Select Country'
	static final String SELECT = 'Select...'
	static final String SELECT_ETHNICITY = 'Ethnicity'
	static final String GET_QUOTE = 'Get Quote Now'
	static final String TO = 'to'
	static final String BUY = 'Buy'
	static final String EDIT = 'EDIT'
	static final String NRIC_NUMBER = 'NRIC Number'
	static final String FULLNAME = 'Full Name'
	static final String EMAIL = 'Email'
	static final String MOBILE = 'Mobile Number'
	static final String BUILDING_NAME = 'Building Name/Unit No/Street Name'
	static final String AREA = 'Area/Sub-Area Name'
	static final String POST_CODE = 'Postcode (Motorcycle Location)'
	static final String CONFIRM_DETAILS = 'Confirm details'
	static final String PLATE_NUMBER = 'Motorcycle Registration Number (Plate Number)'
	static final String GET_A_QUOTE = 'Let’s get a quote for your motorcycle'
	static final String CONFIRM_CHECKBOX = 'I confirm that I have read and agree'



	/*
	 * Select Insurance Type base on insurance name
	 */
	void selectInsuranceType(String insurnameName, FailureHandling flowControl = defaultFailureHandling) {
		commonPage.scrollToTestObject(commonPage.frmHorizontalScrollNo(2), commonPage.lblNormalizeSpace(insurnameName), ScrollDirectionEnum.DOWN, GlobalVariable.G_timeoutShort, flowControl)
		MobileDriver.tap(commonPage.lblNormalizeSpace(insurnameName), GlobalVariable.G_timeoutMedium, flowControl)
		commonPage.waitLoading()
		MobileDriver.takeScreenshot()
	}

	/* --------------------------------------------------- Credit Card -----------------------------------------------*/
	/*
	 * Verify UI Motor Insurance Step One Screen
	 */
	void verifyStepOne(List data, FailureHandling flowControl = defaultFailureHandling) {
		verifyElements(data, commonPage.lblDynamicPath(), GlobalVariable.G_timeoutMedium, flowControl)

		MobileDriver.tap(commonPage.lblDynamic(APPLY), GlobalVariable.G_timeoutMedium, flowControl)
		commonPage.waitLoading()
	}
	/*
	 * Verify UI Motor Insurance Step Two Screen
	 */
	void verifyStepTwo(List data, String plateNumber, String postcode, String nric, FailureHandling flowControl = defaultFailureHandling) {
		commonPage.scrollDownDynamic()
		verifyElements(data, commonPage.lblDynamicPath(), GlobalVariable.G_timeoutMedium, flowControl)

		commonPage.scrollToTestObject(commonPage.frmWebViewScrollNo(2), commonPage.lblNormalizeSpace(GET_A_QUOTE), ScrollDirectionEnum.DOWN, GlobalVariable.G_timeoutShort, flowControl)

		MobileDriver.setText(commonPage.txtEditTextDynamic(PLATE_NUMBER), plateNumber, GlobalVariable.G_timeoutMedium, FailureHandling.STOP_ON_FAILURE)
		MobileDriver.setText(commonPage.txtEditTextDynamic(POST_CODE), postcode, GlobalVariable.G_timeoutMedium, FailureHandling.STOP_ON_FAILURE)
		MobileDriver.setText(commonPage.txtEditTextDynamic(NRIC_NUMBER), nric, GlobalVariable.G_timeoutMedium, FailureHandling.STOP_ON_FAILURE)
		MobileDriver.tap(commonPage.lblContainDynamic(GET_QUOTE), GlobalVariable.G_timeoutMedium, flowControl)
	}
	/*
	 * Verify UI Motor Insurance Step 3 Screen
	 */
	void verifyTripCare360ChoosePlan(List data, FailureHandling flowControl = defaultFailureHandling) {
		verifyElements(data, commonPage.lblDynamicPath(), GlobalVariable.G_timeoutMedium, flowControl)

		MobileDriver.tap(commonPage.lblContainDynamic(BUY), GlobalVariable.G_timeoutMedium, flowControl)
		delay(GlobalVariable.G_timeoutMiniShort)
	}
	/*
	 * Verify UI Motor Insurance Personal Detail Screen
	 */
	void verifyTripCare360PersonalDetail(List data, String nricNumber, String fullName, String ethnicity, String email, String mobiNumber,
			String buildingName, String area, String postcode,
			FailureHandling flowControl = defaultFailureHandling) {
		verifyElements(data, commonPage.lblDynamicPath(), GlobalVariable.G_timeoutMedium, flowControl)

		MobileDriver.tap(commonPage.lblDynamic(EDIT), GlobalVariable.G_timeoutMedium, flowControl)
		delay(GlobalVariable.G_timeoutMiniShort)
		fillPersonalDetail(nricNumber, fullName, ethnicity, email, mobiNumber, buildingName, area, postcode)
		//verify again
		verifyElements(data, commonPage.lblDynamicPath(), GlobalVariable.G_timeoutMedium, flowControl)
		commonPage.scrollToEnd();


		waitForElementPresent(commonPage.btnTextDynamic(CONFIRM_DETAILS), GlobalVariable.G_timeoutShort, FailureHandling.OPTIONAL)
		MobileDriver.tapAtPositionWithOffset(commonPage.btnTextDynamic(CONFIRM_DETAILS), GlobalVariable.G_timeoutMedium, -10,0, flowControl)
		delay(GlobalVariable.G_timeoutMiniShort)
	}
	/*
	 * Verify UI Motor Insurance Payment Detail Screen
	 */
	void verifyTripCare360PaymentDetail(List data,
			FailureHandling flowControl = defaultFailureHandling) {
		verifyElements(data, commonPage.lblDynamicPath(), GlobalVariable.G_timeoutMedium, flowControl)

		delay(GlobalVariable.G_timeoutMiniShort)

		commonPage.scrollToTestObject(commonPage.frmWebViewScrollNo(2), commonPage.lblNormalizeSpace(SELECT_PAYMENT), ScrollDirectionEnum.DOWN, GlobalVariable.G_timeoutShort, flowControl)


		waitForElementPresent(commonPage.btnRadioNo(1), GlobalVariable.G_timeoutShort, FailureHandling.OPTIONAL)
		MobileDriver.tap(commonPage.btnRadioNo(1), GlobalVariable.G_timeoutMedium, flowControl)

		commonPage.scrollToTestObject(commonPage.frmWebViewScrollNo(2), commonPage.lblContainDynamic(CONFIRM_CHECKBOX), ScrollDirectionEnum.DOWN, GlobalVariable.G_timeoutShort, flowControl)
		MobileDriver.tap(commonPage.lblContainDynamic(CONFIRM_CHECKBOX), GlobalVariable.G_timeoutMedium, flowControl)
		commonPage.scrollToEnd();

		waitForElementPresent(commonPage.btnTextDynamic(PROCEED_PAYMENT), GlobalVariable.G_timeoutShort, FailureHandling.OPTIONAL)
		MobileDriver.tapAtPositionWithOffset(commonPage.btnTextDynamic(PROCEED_PAYMENT), GlobalVariable.G_timeoutMedium, -10,0, flowControl)
		delay(GlobalVariable.G_timeoutMedium)
	}
	/*
	 * Select Day
	 */
	void selectDay(FailureHandling flowControl = FailureHandling.CONTINUE_ON_FAILURE) {
		// Get current date and add one day to get the next day
		LocalDate currentDate = LocalDate.now()
		LocalDate nextDate = currentDate.plusDays(1)
		MobileDriver.tap(commonPage.lblDynamicTextLast(TO), GlobalVariable.G_timeoutMedium, flowControl)
		// Format the next date as required by your UI (e.g., "d" for day-only)
		String nextDayFormatted = nextDate.format(DateTimeFormatter.ofPattern("d"))

		MobileDriver.tap(commonPage.lblDynamic(nextDayFormatted), GlobalVariable.G_timeoutMedium, flowControl)
		delay(GlobalVariable.G_timeoutMiniShort)
		MobileDriver.tap(commonPage.lblDynamic(nextDayFormatted), GlobalVariable.G_timeoutMedium, flowControl)
	}

	/*
	 * fill Personal Detail
	 */
	void fillPersonalDetail(String nricNumber, String fullName, String ethnicity, String email, mobiNumber,
			String buildingName, String area, String postcode,
			FailureHandling flowControl = defaultFailureHandling) {
		MobileDriver.setText(commonPage.txtEditTextDynamic(NEW_NRIC), nricNumber, GlobalVariable.G_timeoutMedium, FailureHandling.STOP_ON_FAILURE)
		delay(GlobalVariable.G_timeoutMiniShort)
		MobileDriver.setText(commonPage.txtEditTextDynamic(FULLNAME), fullName, GlobalVariable.G_timeoutMedium, FailureHandling.STOP_ON_FAILURE)
		MobileDriver.tap(commonPage.lblDynamicTextLast(SELECT_ETHNICITY), GlobalVariable.G_timeoutMedium, flowControl)

		MobileDriver.tap(commonPage.lblDynamic(ethnicity), GlobalVariable.G_timeoutMedium, flowControl)
		delay(GlobalVariable.G_timeoutMiniShort)
		MobileDriver.setText(commonPage.txtEditTextDynamic(EMAIL), email, GlobalVariable.G_timeoutMedium, FailureHandling.STOP_ON_FAILURE)
		MobileDriver.setText(commonPage.txtEditTextDynamic(MOBILE), mobiNumber, GlobalVariable.G_timeoutMedium, FailureHandling.STOP_ON_FAILURE)
		MobileDriver.setText(commonPage.txtEditTextDynamic(BUILDING_NAME), buildingName, GlobalVariable.G_timeoutMedium, FailureHandling.STOP_ON_FAILURE)
		MobileDriver.setText(commonPage.txtEditTextDynamic(AREA), area, GlobalVariable.G_timeoutMedium, FailureHandling.STOP_ON_FAILURE)
		MobileDriver.setText(commonPage.txtEditTextDynamic(POST_CODE), postcode, GlobalVariable.G_timeoutMedium, FailureHandling.STOP_ON_FAILURE)
		waitForElementPresent(commonPage.lblDynamic(SAVE_CHANGES), GlobalVariable.G_timeoutShort, FailureHandling.OPTIONAL)
		MobileDriver.tap(commonPage.lblDynamic(SAVE_CHANGES), GlobalVariable.G_timeoutMedium, flowControl)
		delay(GlobalVariable.G_timeoutMiniShort)
	}
}
