package com.maybank.mae.internal.pages.base.applypage

import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.maybank.common.MobileDriver
import com.maybank.mae.internal.pages.base.BasePage
import com.maybank.mae.internal.pages.base.HomePage
import com.maybank.mae.internal.pages.base.CommonPage
import com.maybank.mae.internal.pages.base.applypage.ApplyPage as ApplyPage

import internal.GlobalVariable

public abstract class ApplyHomeLoanPage extends BasePage{

	public static CommonPage commonPage = BasePage.getPage(CommonPage)
	public static HomePage homePage = BasePage.getPage(HomePage)
	public static ApplyPage applyPage = BasePage.getPage(ApplyPage)

	// Navigate home loan
	String UI_LOAN_FINANCING = "LOAN/FINANCING"
	String UI_HOME_LOAN = "Home Loan"
	String UI_AGREE_CONFIRM = "Agree & Confirm"

	// Input value step 1
	String UI_PROPERTY_NAME = "What is the property name?"
	String UI_SAVE_NEXT = "Save & Next"

	// Input value step 2
	String UI_PROPERTY_PRICE = "Property price"

	// Input value step 3
	String UI_FINANCING_PERIOD = "How long would you like your financing period to be?"

	// Input value step 4
	String UI_PERSONAL_DETAILS = "Please share with us your personal details"
	String UI_TITLE = "Title"
	String UI_RESIDENT_STATUS = "Resident Status"
	String UI_EDUCATION = "Education"
	String UI_MARITAL_STATUS = "Marital status"
	String UI_RELIGION = "Religion"
	String UI_EMPLOYMENT_STATUS = "Employment status"
	String UI_EMPLOYMENT_BUSINESS_TYPE = "Employment business type"
	String UI_OCCUPATION = "Occupation"

	// Input value step 5
	String UI_MONTHLY_GROSS_INCOME = "Monthly Gross Income"
	String UI_YES = "Yes"

	// Input value step 6
	String UI_CHECK_ELIGIBILITY = "Check Financial Eligibility"
	String UI_NO = "No"
	String UI_CONFIRM = "Confirm"
	String UI_PROCEED_WITH_APPLICATION = "Proceed with Application"

	// Input value confirm apply financing page
	String UI_ELIGIBILITY_DETAILS = "Eligibility details"

	// Input value after confirm page 2
	String UI_EMPLOYMENT_DETAILS = "Please review and update your employment details"
	String UI_YEARS = "Years"
	String UI_MONTHS = "Months"
	String UI_DONE = "Done"

	// Input value after confirm page 3
	String UI_HOME_ADDRESS = "Please check and confirm your current address"
	String UI_HOME_ADDRESS_LINE_1 = "Home address line 1"
	String UI_HOME_ADDRESS_LINE_2 = "Home address line 2"
	String UI_HOME_ADDRESS_LINE_3 = "Home address line 3"
	String UI_CITY = "City"
	String UI_POSTCODE = "Postcode"
	String UI_STATE = "State"
	String UI_CONTACT_NUMBER = "Contact number"

	// Input value term conditions
	String UI_TERMS_CONDITIONS = "Terms & Conditions"
	String UI_YES_AGREE ="Yes, I expressly agree to be contacted"

	// Input value confirmation
	String UI_CONFIRM_SUBMIT = "Confirm & Submit"
	String UI_GREAT_NEWS = "Great news, We have successfully received your application."
	String UI_PERSONAL_INFORMATION = "Please fill in your personal information"
	String UI_MONTHLY_NON_BANK_COMMITMENTS = 'Monthly non-bank commitments'
	
	// Input value after confirm page 1
	String UI_OFFICE_ADDRESS = "Please review and update your office address"
	String UI_COUNTRY = "Country"
	String UI_ADDRESS_LINE_1 = "Address Line 1"
	String UI_ADDRESS_LINE_2 = "Address Line 2"
	String UI_ADDRESS_LINE_3 = "Address Line 3"
	
	String View_Application = "View application"
	
	/**
	 * Navigate to Home Loan
	 */
	void navigateHomeLoan(int timeout = GlobalVariable.G_timeoutLong, FailureHandling flowControl = defaultFailureHandling) {
		'Select Apply'
		homePage.tapApply()

		'Select LOAN/FINANCING'
		applyPage.selectTab(UI_LOAN_FINANCING)

		'Verify Home Loan'
		Mobile.verifyElementExist(commonPage.lblStartsWith(UI_HOME_LOAN), timeout)

		Mobile.delay(GlobalVariable.G_timeoutMedium)

		'Navigate to Home Loan'
		MobileDriver.takeScreenshot()
		MobileDriver.tap(commonPage.lblStartsWith(UI_HOME_LOAN), timeout)

		'Check Enter PIN'
		commonPage.checkEnterPIN()

		'Check Agree & Confirm'
		if (MobileDriver.waitForElementPresent(commonPage.lblDynamic(UI_AGREE_CONFIRM), timeout)) {
			MobileDriver.takeScreenshot()
			MobileDriver.tap(commonPage.lblDynamic(UI_AGREE_CONFIRM), timeout)
		}
	}

	/**
	 * Input value for Home Loan step 1
	 */
	void inputValueStep1(String propertyName ='', int timeout = GlobalVariable.G_timeoutLong, FailureHandling flowControl = defaultFailureHandling) {
		'Generate random property name if not provided'
		if (propertyName =='') {
			Random rand = new Random()
			propertyName = ('Property ' + Integer.toString(rand.nextInt(1000)))
		}

		'Verify What is the property name?'
		MobileDriver.verifyElementExist(commonPage.lblDynamic(UI_PROPERTY_NAME), timeout)

		'Input property name'
		commonPage.enterTextFieldValue(UI_PROPERTY_NAME, propertyName)
		MobileDriver.takeScreenshot()

		'Click Save & Next'
		MobileDriver.tap(commonPage.lblDynamic(UI_SAVE_NEXT), timeout)
	}

	/**
	 * Input value for Home Loan step 2
	 */
	void inputValueStep2(String price = '9999999', String percentage='1000', int timeout = GlobalVariable.G_timeoutLong, FailureHandling flowControl = defaultFailureHandling) {
		'Select property price'
		MobileDriver.tap(commonPage.lblDynamic(UI_PROPERTY_PRICE), timeout)

		'Enter property price'
		commonPage.enterNO(price)

		'Select %'
		MobileDriver.tap(commonPage.lblDynamic("%"), timeout)

		'Enter percentage'
		commonPage.enterNO(percentage)

		'Click Continue'
		MobileDriver.takeScreenshot()
		commonPage.tapContinueBtn()
	}

	/**
	 * Input value for Home Loan step 3
	 */
	void inputValueStep3(int timeout = GlobalVariable.G_timeoutLong, FailureHandling flowControl = defaultFailureHandling) {
		'Wait How long would you like...'
		'Some time it contain in step 2'
		if (MobileDriver.waitForElementPresent(commonPage.lblDynamic(UI_FINANCING_PERIOD), timeout)) {
			Mobile.delay(GlobalVariable.G_timeoutShort)

			'Select Continue'
			commonPage.tapContinueBtn()
		}
		if(MobileDriver.waitForElementPresent(commonPage.lblNormalizeSpace(UI_PERSONAL_INFORMATION),GlobalVariable.G_timeoutShort)) {
			
			'Select title'
			commonPage.selectDropdownSiblingGroupItem(UI_TITLE,'')
			'Select Resident Status'
			commonPage.selectDropdownSiblingGroupItem(UI_RESIDENT_STATUS,'')
			'Select Education'
			commonPage.selectDropdownSiblingGroupItem(UI_EDUCATION,'')
			'Select Marital status'
			commonPage.selectDropdownSiblingGroupItem(UI_MARITAL_STATUS,'')
	
			commonPage.scrollToEnd()
	
			'Select Religion'
			commonPage.selectDropdownSiblingGroupItem(UI_RELIGION,'')
			'Select Save & Next'
			MobileDriver.takeScreenshot()
			MobileDriver.tap(commonPage.lblDynamic(UI_SAVE_NEXT), timeout)
			commonPage.waitLoading()
		}
	}

	/**
	 * Input value for Home Loan step 4
	 */
	void inputValueStep4(int timeout = GlobalVariable.G_timeoutLong, FailureHandling flowControl = defaultFailureHandling) {

		'Select Employment status'
		commonPage.selectDropdownSiblingGroupItem(UI_EMPLOYMENT_STATUS,'')
		'Select Employment business type'
		commonPage.selectDropdownSiblingGroupItem(UI_EMPLOYMENT_BUSINESS_TYPE,'')
		'Select Occupation'
		commonPage.selectDropdownSiblingGroupItem(UI_OCCUPATION,'')

		'Select Save & Next'
		MobileDriver.takeScreenshot()
		MobileDriver.tap(commonPage.lblDynamic(UI_SAVE_NEXT), timeout)
	}

	/**
	 * Input value for Home Loan step 5
	 */
	void inputValueStep5(String grossIncome = '1000000', int timeout = GlobalVariable.G_timeoutLong, FailureHandling flowControl = defaultFailureHandling) {
		'Verify Monthly Gross Income'
		MobileDriver.tap(commonPage.lblDynamic(UI_MONTHLY_GROSS_INCOME), timeout)

		'Enter gross income'
		commonPage.enterNO(grossIncome)

		'Select Yes'
		MobileDriver.tap(commonPage.lblDynamic(UI_YES), timeout)
		
		'Select Yes'
		MobileDriver.tap(commonPage.lblDynamicNo(UI_YES, 2), timeout)
		
		
		MobileDriver.tap(commonPage.lblDynamic(UI_MONTHLY_NON_BANK_COMMITMENTS), timeout, flowControl)

		'Enter gross income'
		commonPage.enterNO(grossIncome.substring(0, 3))
		
		'Select Save & Next'
		MobileDriver.takeScreenshot()
		MobileDriver.tap(commonPage.lblDynamic(UI_SAVE_NEXT), timeout)
		commonPage.waitLoading()
	}

	/**
	 * Input value for Home Loan step 6
	 */
	void inputValueStep6(String password, int timeout = GlobalVariable.G_timeoutLong, FailureHandling flowControl = defaultFailureHandling) {
		'Verify Check eligibility'
		MobileDriver.verifyElementExist(commonPage.lblDynamic(UI_CHECK_ELIGIBILITY), timeout)

		'Select No'
		MobileDriver.tap(commonPage.lblDynamic(UI_NO), timeout)

		'Select Confirm'
		MobileDriver.takeScreenshot()
		MobileDriver.tap(commonPage.lblDynamic(UI_CONFIRM), timeout)

		'Select Proceed with Application'
		commonPage.waitLoading()
		commonPage.scrollToEnd()
		MobileDriver.takeScreenshot()
		MobileDriver.tap(commonPage.lblDynamic(UI_PROCEED_WITH_APPLICATION), timeout)

		'Check Enter Password'
		commonPage.checkEnterPassword(password)
	}

	/**
	 * Input value for Home Loan confirm apply financing page
	 */
	void inputValueApplyFinanacingPage(int timeout = GlobalVariable.G_timeoutLong, FailureHandling flowControl = defaultFailureHandling) {
		'Verify Eligibility details'
		MobileDriver.verifyElementExist(commonPage.lblDynamic(UI_ELIGIBILITY_DETAILS), timeout)

		'Scroll to end'
		commonPage.scrollToEnd()

		'Select Confirm'
		MobileDriver.takeScreenshot()
		MobileDriver.tap(commonPage.lblDynamic(UI_CONFIRM), timeout)

	}

	/**
	 * Input value after confirm step 2
	 */
	void inputValueAfterConfirmStep2(String years='1 year', String months = '2 months', int timeout = GlobalVariable.G_timeoutLong, FailureHandling flowControl = defaultFailureHandling) {
		'Verify Employment details'
		MobileDriver.verifyElementExist(commonPage.lblDynamic(UI_EMPLOYMENT_DETAILS), timeout)

		'Scroll to end'
		commonPage.scrollToEnd()

		'Select Years'
		MobileDriver.tap(commonPage.lblDynamic(UI_YEARS), timeout)

		Mobile.scrollToText(years)

		MobileDriver.tap(commonPage.lblDynamic(years), timeout)

		MobileDriver.tap(commonPage.lblDynamic(UI_DONE), timeout)

		'Select Months'
		MobileDriver.tap(commonPage.lblDynamic(UI_MONTHS), timeout)

		Mobile.scrollToText(months)

		MobileDriver.tap(commonPage.lblDynamic(months), timeout)

		MobileDriver.tap(commonPage.lblDynamic(UI_DONE), timeout)

		'Select Save & Next'
		MobileDriver.takeScreenshot()
		MobileDriver.tap(commonPage.lblDynamic(UI_SAVE_NEXT), timeout)
	}

	/**
	 * Input value after confirm step 3
	 */
	void inputValueAfterConfirmStep3(
			String country='',
			String add1 ='Address 1',
			String add2 ="Address 2",
			String add3 ="Address 3",
			String city= "City",
			String state = '',
			String postcode = '50000',
			String contactNumber='145522383',
			int timeout = GlobalVariable.G_timeoutLong,
			FailureHandling flowControl = defaultFailureHandling) {

		MobileDriver.verifyElementExist(commonPage.lblDynamic(UI_OFFICE_ADDRESS), timeout)
		'Enter office address'
		commonPage.selectDropdownSiblingGroupItem(UI_COUNTRY,country)
		commonPage.enterTextFieldValue(UI_ADDRESS_LINE_1, add1)
		commonPage.enterTextFieldValue(UI_ADDRESS_LINE_2, add2)
		commonPage.enterTextFieldValue(UI_ADDRESS_LINE_3, add3)

		commonPage.scrollToEnd()

		commonPage.enterTextFieldValue(UI_CITY, city)
		commonPage.enterTextFieldValue(UI_POSTCODE, postcode)
		
		MobileDriver.tap(commonPage.lblDynamic(UI_STATE), timeout)
		commonPage.selectDropdownSiblingGroupItem(UI_STATE,state)
		commonPage.enterTextFieldValue(UI_CONTACT_NUMBER, contactNumber)

		'Select Save & Next'
		MobileDriver.takeScreenshot()
		MobileDriver.tap(commonPage.lblDynamic(UI_SAVE_NEXT), timeout)
	}

	/**
	 * Input value for Home Loan term conditions
	 */
	void inputValueTermConditions(int timeout = GlobalVariable.G_timeoutLong, FailureHandling flowControl = defaultFailureHandling) {
		'Verify Terms & Conditions'
		MobileDriver.verifyElementExist(commonPage.lblDynamic(UI_TERMS_CONDITIONS), timeout)

		commonPage.scrollToEnd()

		'Verify Yes, I expressly agree to be contacted'
		MobileDriver.tap(commonPage.lblDynamic(UI_YES_AGREE), timeout)
		MobileDriver.takeScreenshot()

		'Select Agree & Confirm'
		MobileDriver.tap(commonPage.lblDynamic(UI_AGREE_CONFIRM), timeout)

		'Verify Eligibility details'
		MobileDriver.verifyElementExist(commonPage.lblDynamic(UI_ELIGIBILITY_DETAILS), timeout)
	}

	/**
	 * Input value for Home Loan confirmation
	 */
	void inputValueConfirmation(int timeout = GlobalVariable.G_timeoutLong, FailureHandling flowControl = defaultFailureHandling) {
		MobileDriver.takeScreenshot()
		commonPage.scrollToEnd()

		'Select Confirm & Submit'
		MobileDriver.takeScreenshot()
		MobileDriver.tap(commonPage.lblDynamic(UI_CONFIRM_SUBMIT), timeout)

		'Verify Great news, We have successfully received your application.'
		MobileDriver.verifyElementExist(commonPage.lblDynamic(UI_GREAT_NEWS), timeout)
		MobileDriver.takeScreenshot()
	}
	/**
	 * Input value after confirm step 3
	 */
	void inputValueAfterConfirmStep1(
			String country='',
			String add1 ='Address 1',
			String add2 ="Address 2",
			String add3 ="Address 3",
			String city= "City",
			String state = '',
			String postcode = '50000',
			String contactNumber='145522383',
			int timeout = GlobalVariable.G_timeoutLong,
			FailureHandling flowControl = defaultFailureHandling) {

		MobileDriver.verifyElementExist(commonPage.lblDynamic(UI_HOME_ADDRESS), timeout)
		'Enter office address'
		commonPage.selectDropdownSiblingGroupItem(UI_COUNTRY,country)
		commonPage.enterTextFieldValue(UI_HOME_ADDRESS_LINE_1, add1)
		commonPage.enterTextFieldValue(UI_HOME_ADDRESS_LINE_2, add2)
		commonPage.enterTextFieldValue(UI_HOME_ADDRESS_LINE_3, add3)

		commonPage.scrollToEnd()

		commonPage.enterTextFieldValue(UI_CITY, city)
		commonPage.enterTextFieldValue(UI_POSTCODE, postcode)
		
		MobileDriver.tap(commonPage.lblDynamic(UI_STATE), timeout)
		commonPage.selectDropdownSiblingGroupItem(UI_STATE,state)

		'Select Save & Next'
		MobileDriver.takeScreenshot()
		MobileDriver.tap(commonPage.lblDynamic(UI_SAVE_NEXT), timeout)
	}
	void viewApplication(int timeout = GlobalVariable.G_timeoutLong, FailureHandling flowControl = defaultFailureHandling) {
		'Verify Great news, We have successfully received your application.'
		MobileDriver.verifyElementExist(commonPage.lblDynamic(UI_GREAT_NEWS), timeout)

		'Select Confirm'
		MobileDriver.verifyElementExist(commonPage.lblDynamic(View_Application), timeout)
		MobileDriver.tap(commonPage.lblDynamic(View_Application), timeout)
		MobileDriver.takeScreenshot()
		Mobile.delay(GlobalVariable.G_timeoutMedium)
		commonPage.scrollToEnd()
		
		'Tap Back icon'
		commonPage.tapBack()
		
		'Tap close'
		MobileDriver.tapAndLog(commonPage.imgNoDynamic(2), GlobalVariable.G_timeoutMedium, 'close page' ,FailureHandling.OPTIONAL)
		MobileDriver.takeScreenshot()
		
	}
}