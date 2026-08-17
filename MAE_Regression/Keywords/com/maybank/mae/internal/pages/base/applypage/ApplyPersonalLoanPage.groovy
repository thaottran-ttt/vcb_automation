package com.maybank.mae.internal.pages.base.applypage

import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testobject.TestObject
import com.maybank.common.MobileDriver
import com.maybank.mae.internal.pages.base.BasePage
import com.maybank.mae.internal.pages.base.CommonPage
import com.maybank.mae.internal.pages.base.HomePage
import com.maybank.mae.internal.pages.base.OnboardingPage
import com.maybank.mae.internal.pages.base.applypage.ApplyPage as ApplyPage

import internal.GlobalVariable

public abstract class ApplyPersonalLoanPage extends BasePage{
	abstract TestObject otpPrecedingSiblingImageView(String startWithText)
	abstract TestObject otpPrecedingSiblingViewGroup(String startWithText)
	
	TestObject imageTick() {
		return findTestObject('Object Repository/Mobile/MAE/Android/Apply Personal Loan Screen/imageTick')
	}
	
	

	public static CommonPage commonPage = BasePage.getPage(CommonPage)
	public static HomePage homePage = BasePage.getPage(HomePage)
	public static ApplyPage applyPage = BasePage.getPage(ApplyPage)
	public static OnboardingPage onboardingPage = BasePage.getPage(OnboardingPage)
	

	// Navigate Personal loan
	String UI_LOAN_FINANCING = "LOAN/FINANCING"
	String UI_PERSONAL_LOAN = "Personal Loan"
	String UI_COUNTINUE_PERSONAL_LOAN = "Continue your Personal Loan / Financing-i application here"
	String UI_AGREE_CONFIRM = "Agree & Confirm"
	String UI_APPLY_NOW = "Apply Now"
	String UI_APPLY_LOAN = "Apply Loan"

	// Input value for Personal Loan step 1
	String UI_MONTHLY_GROSS_INCOME = "Monthly Gross Income"

	// Input value for Personal Loan step 3
	String UI_TYPE_OF_LOAN = "Type of Loan/Financing"
	String UI_AMOUNT_I_NEED = "Amount I Need"
	String UI_MINIMUM_AMOUNT_I_NEED = "Minimum Amount I Need"
	String UI_TENURE = "Tenure"
	String UI_PURPOSE_OF_LOAN = "Purpose of Loan/Financing"
	String UI_YOUR_LOAN_FINANCING = "Once your loan/financing is approved, the amount will be paid out to your selected account."
	String UI_SAVE_NEXT = "Save & Next"

	// Input value for Personal Loan step 4
	String UI_PROCEED = "Proceed"

	// Input value for Personal Loan step 5
	String UI_MARITAL = "Marital Status"
	String UI_EDUCATION = "Education"
	String UI_RESIDENTIAL_STATUS = "Residential Status"
	String UI_MOBILE_NUMBER = "Mobile Number"
	String UI_HOME_ADDRESS_LINE_1 = "Home Address Line 1"
	String UI_HOME_ADDRESS_LINE_2 = "Home Address Line 2"
	String UI_HOME_ADDRESS_LINE_3 = "Home Address Line 3"

	// Input value for Personal Loan step 6
	String UI_EMPLOYER_NAME = "Employer Name"
	String UI_BUSINESS_CLASSIFICATION = "Business Classification"
	String UI_LENGTH_OF_SERVICE_YEARS = "Length of Service (Years)"
	String UI_LENGTH_OF_SERVICE_MONTHS = "Length of Service (months)"
	String UI_TERMS_OF_EMPLOYMENT = "Terms of Employment"

	// Input value for Personal Loan step 7
	String UI_OFFICE_ADDRESS_LINE_1 = "Office Address Line 1"
	String UI_OFFICE_ADDRESS_LINE_2 = "Office Address Line 2"
	String UI_OFFICE_ADDRESS_LINE_3 = "Office Address Line 3"
	String UI_POSTCODE = "Postcode"
	String UI_CITY = "City"
	String UI_STATE = "State"
	String UI_OFFICE_PHONE_NUMBER = "Office Phone Number"
	String UI_PREFERRED_MAILING_ADDRESS = "Preferred Mailing Address"

	// Input value for Personal Loan step 8
	String UI_MONTHLY_NET_INCOME = "Monthly Net Income"
	String UI_TOTAL_MONTHLY_COMMITMENT = "If you have loan/financing with any non-bank organisations, please enter your total monthly commitment."

	// Input value for Personal Loan step 9
	String UI_TERMS_CONDITIONS = "Terms & Conditions"
	List<String> UI_LIST_TERM_CONDITITONS = [
		'I have read and agree to be bound', 
		'I confirm that I do not meet the definition',
		'I confirm that I am not a tax resident',
		'I have read, agree and accept the terms',
		'Yes, I expressly agree to be contacted; or',
		'I hereby agree to accept this product'
	]

	// Confirm Personal Loan
	String UI_CONFIRMATION = "Please confirm the details below before submission"
	String UI_SUBMIT = "Submit"
	String UI_APPROVE = "Approve"
	String UI_UPLOAD_NOW = "Upload Now"
	String UI_UPLOAD_LATER = "Upload Later"
	String UI_ENTER_TAC = "Enter TAC sent to you"

	// Upload file Step 
	List<String> UI_LIST_UPLOAD_DOCUMENTS = [
		'Upload Documents',
		'Upload your supporting documents to complete your application.',
		'Identity verification',
		'MyKad',
		'Income verification (upload any one)',
		'Latest Salary Slip',
		'Latest EA form',
		'Latest BE form  with official tax receipt',
		'Latest 3 month’s savings account activity/current statements (optional)'
	]
	
	String UI_GOT_IT = "Got It"
	
	List<String> UI_MYKAD_VERIFICATION = [
		'MyKad Verification', 
		'Let’s begin by taking a photo of the front and back of your MyKad'
	]
	
	static final String REFERENCE_ID_REGEX = '^[0-9]{10}$'
	String ReferenceIDUI = 'Reference ID'
	String DateTimeUI = 'Date & time'
	List<String> UI_APPLICATION = [
		'Your application has been approved in principle.',
		'Kindly allow up to 3 working days for us to review your application and supporting documents. We\'ll get in touch with you soon.'
	]
	void navigatePersonalLoanNTB (String password, int timeout = GlobalVariable.G_timeoutLong, FailureHandling flowControl = defaultFailureHandling) {
		'Select Apply'
		homePage.tapApply()
		'Select LOAN/FINANCING'
		applyPage.selectTab(UI_LOAN_FINANCING)
		Mobile.delay(GlobalVariable.G_timeoutMedium)
		
		if (MobileDriver.waitForElementPresent(commonPage.lblStartsWith(UI_PERSONAL_LOAN), timeout)) {
			'Select to Personal Loan'
			MobileDriver.tap(commonPage.lblStartsWith(UI_PERSONAL_LOAN), timeout)
			MobileDriver.takeScreenshot()
			'Verify web screen'
			MobileDriver.verifyElementVisible(commonPage.lblDynamic(UI_APPLY_LOAN), GlobalVariable.G_timeoutShort)
			MobileDriver.verifyElementVisible(commonPage.lblTextViewDynamic("You must be Malaysian aged 21-60"), GlobalVariable.G_timeoutShort)
			
			MobileDriver.takeScreenshot()
			'Scroll to end'
			commonPage.scrollToEnd()
			MobileDriver.takeScreenshot()
		}else {
			commonPage.scrollToEnd()

			'Verify Countinue your Personal Loan...'
			Mobile.verifyElementExist(commonPage.lblNormalizeSpace(UI_COUNTINUE_PERSONAL_LOAN), timeout)

			'Select to Countinue your Personal Loan...'
			MobileDriver.tap(commonPage.lblNormalizeSpace(UI_COUNTINUE_PERSONAL_LOAN), timeout)

			MobileDriver.takeScreenshot()
		}
	}
	
	/**
	 * Navigate to Personal Loan
	 */
	void navigatePersonalLoan (String password, int timeout = GlobalVariable.G_timeoutLong, FailureHandling flowControl = defaultFailureHandling) {
		'Select Apply'
		homePage.tapApply()

		'Select LOAN/FINANCING'
		applyPage.selectTab(UI_LOAN_FINANCING)

		Mobile.delay(GlobalVariable.G_timeoutMedium)

		if (MobileDriver.waitForElementPresent(commonPage.lblStartsWith(UI_PERSONAL_LOAN), timeout)) {
			'Select to Personal Loan'
			MobileDriver.tap(commonPage.lblStartsWith(UI_PERSONAL_LOAN), timeout)

			MobileDriver.takeScreenshot()

			'Verify Apply Now'
			Mobile.verifyElementExist(commonPage.lblStartsWith(UI_APPLY_NOW), timeout)

			MobileDriver.doubleTap(commonPage.lblDynamic(UI_APPLY_NOW), GlobalVariable.G_timeoutShort)

			MobileDriver.takeScreenshot()

			'Check Enter Password'
			commonPage.checkEnterPassword(password, GlobalVariable.G_timeoutMedium)
		}
		else {
			commonPage.scrollToEnd()

			'Verify Countinue your Personal Loan...'
			Mobile.verifyElementExist(commonPage.lblNormalizeSpace(UI_COUNTINUE_PERSONAL_LOAN), timeout)

			'Select to Countinue your Personal Loan...'
			MobileDriver.tap(commonPage.lblNormalizeSpace(UI_COUNTINUE_PERSONAL_LOAN), timeout)

			MobileDriver.takeScreenshot()
		}

		Mobile.delay(GlobalVariable.G_timeoutLong);

		'Verify select on Personal Loan dont have error'
		MobileDriver.verifyElementNotVisible(commonPage.lblNormalizeSpace(UI_COUNTINUE_PERSONAL_LOAN), GlobalVariable.G_timeoutShort)
		MobileDriver.verifyElementNotVisible(commonPage.lblStartsWith(UI_PERSONAL_LOAN), GlobalVariable.G_timeoutShort)
		MobileDriver.verifyElementNotVisible(commonPage.lblDynamic(UI_APPLY_LOAN), GlobalVariable.G_timeoutShort)
		MobileDriver.verifyElementNotVisible(commonPage.lblDynamic(UI_APPLY_NOW), GlobalVariable.G_timeoutShort)
	}

	/**
	 * Get step
	 */
	String getStep (int timeout = GlobalVariable.G_timeoutLong, FailureHandling flowControl = defaultFailureHandling) {
		return MobileDriver.getText(commonPage.lblTextNo(1), timeout)
	}

	/**
	 * Input value for Personal Loan step 1
	 */
	void inputValueStep1 (String income = '4000', int timeout = GlobalVariable.G_timeoutLong, FailureHandling flowControl = defaultFailureHandling) {

		'Verify Monthly Gross Income'
		Mobile.verifyElementExist(commonPage.lblDynamic(UI_MONTHLY_GROSS_INCOME), timeout)
		Mobile.delay(GlobalVariable.G_timeoutShort)

		'Enter Monthly Gross Income'
		commonPage.enterTextFieldValue(UI_MONTHLY_GROSS_INCOME, income, timeout)

		'Select Agree & Confirm'
		MobileDriver.tap(commonPage.lblDynamic(UI_AGREE_CONFIRM), timeout)
	}


	/**
	 * Input value for Personal Loan step 3
	 */
	void inputValueStep3 (
			String typeOfLoan = '',
			String amountINeed = '16000',
			String minimumAmountINeed = '8000',
			String tenure = '',
			String purposeOfLoan = '',
			String yourLoanFinancing = '',
			int timeout = GlobalVariable.G_timeoutLong, FailureHandling flowControl = defaultFailureHandling) {
		'Verify Type of Loan/Financing'
		MobileDriver.verifyElementExist(commonPage.lblDynamic(UI_TYPE_OF_LOAN), timeout)

		'Select Type of Loan/Financing'
		commonPage.selectDropdownSiblingGroupItem(UI_TYPE_OF_LOAN,typeOfLoan)
		'Select Amount I Need'
		commonPage.enterTextFieldValue(UI_AMOUNT_I_NEED, amountINeed)
		'Select Minimum Amount I Need'
		commonPage.enterTextFieldValue(UI_MINIMUM_AMOUNT_I_NEED, minimumAmountINeed)
		'Focus on Tenure after setting text'
		commonPage.tapAtPosition(commonPage.lblDynamic(UI_TENURE), timeout, flowControl)
		'Select Tenure'
		commonPage.selectDropdownSiblingGroupItem(UI_TENURE,tenure)
		'Select Purpose of Loan/Financing'
		commonPage.selectDropdownSiblingGroupItem(UI_PURPOSE_OF_LOAN,purposeOfLoan)

		commonPage.scrollToEnd()
		'Select Once your loan/financing is approved, the amount will be paid out to your selected account.'
		commonPage.selectDropdownSiblingGroupItem(UI_YOUR_LOAN_FINANCING,yourLoanFinancing)
		'Select Save & Next'
		MobileDriver.tap(commonPage.lblDynamic(UI_SAVE_NEXT), timeout)
	}

	/**
	 * Input value for Personal Loan step 4
	 */
	void inputValueStep4 (int timeout = GlobalVariable.G_timeoutLong, FailureHandling flowControl = defaultFailureHandling) {
		'Select Procced'
		MobileDriver.tap(commonPage.lblDynamic(UI_PROCEED), timeout)
	}

	/**
	 * Input value for Personal Loan step 5
	 */
	void inputValueStep5(
			String marital = '',
			String education = '',
			String residentialStatus = '',
			String mobileNumber = '179995050',
			String homeAddressLine1 = 'Home Address 1',
			String homeAddressLine2 = 'Home Address 2',
			String homeAddressLine3 = 'Home Address 3',
			int timeout = GlobalVariable.G_timeoutLong, FailureHandling flowControl = defaultFailureHandling) {
		'Select Marital'
		commonPage.selectDropdownSiblingGroupItem(UI_MARITAL,marital)
		'Select Education'
		commonPage.selectDropdownSiblingGroupItem(UI_EDUCATION,education)
		'Select Residential Status'
		commonPage.selectDropdownSiblingGroupItem(UI_RESIDENTIAL_STATUS,residentialStatus)
		'Enter Mobile Number'
		commonPage.enterTextFieldValue(UI_MOBILE_NUMBER, mobileNumber)
		'Enter Home Address Line 1'
		commonPage.enterTextFieldValue(UI_HOME_ADDRESS_LINE_1, homeAddressLine1)
		'Enter Home Address Line 2'
		commonPage.enterTextFieldValue(UI_HOME_ADDRESS_LINE_2, homeAddressLine2)
		'Enter Home Address Line 3'
		commonPage.enterTextFieldValue(UI_HOME_ADDRESS_LINE_3, homeAddressLine3)
		'Select Save & Next'
		MobileDriver.tap(commonPage.lblDynamic(UI_SAVE_NEXT), timeout)
	}

	/**
	 * Input value for Personal Loan step 6
	 */
	void inputValueStep6(
			String employerName = 'Maybank',
			String businessClassification = '',
			String lengthOfServiceYears = '2',
			String lengthOfServiceMonths = '0',
			String termsOfEmployment = '',
			int timeout = GlobalVariable.G_timeoutLong, FailureHandling flowControl = defaultFailureHandling) {
		'Enter employer name'
		commonPage.enterTextFieldValue(UI_EMPLOYER_NAME, employerName)
		'Scroll to end'
		commonPage.scrollToEnd()
		'Select Business Classification'
		commonPage.selectDropdownSiblingGroupItem(UI_BUSINESS_CLASSIFICATION, businessClassification)
		'Select Length of Service (Years)'
		commonPage.selectDropdownSiblingGroupItem(UI_LENGTH_OF_SERVICE_YEARS, lengthOfServiceYears)
		'Select Length of Service (months)'
		commonPage.selectDropdownSiblingGroupItem(UI_LENGTH_OF_SERVICE_MONTHS, lengthOfServiceMonths)
		'Select Terms of Employment'
		commonPage.selectDropdownSiblingGroupItem(UI_TERMS_OF_EMPLOYMENT, termsOfEmployment)
		'Select Save & Next'
		MobileDriver.tap(commonPage.lblDynamic(UI_SAVE_NEXT), timeout)
	}

	/**
	 * Input value for Personal Loan step 7
	 */
	void inputValueStep7(
			String officeAddressLine1 = 'Address 1',
			String officeAddressLine2 = 'Address 2',
			String officeAddressLine3 = 'Address 3',
			String postcode = '50000',
			String city = 'City',
			String state = '',
			String officePhoneNumber = '145522383',
			String preferredMailingAddress = '',
			int timeout = GlobalVariable.G_timeoutLong, FailureHandling flowControl = defaultFailureHandling) {
		'Verify Office Address Line 1'
		MobileDriver.verifyElementExist(commonPage.lblDynamic(UI_OFFICE_ADDRESS_LINE_1), timeout)
		'Select Office Address Line 1'
		commonPage.enterTextFieldValue(UI_OFFICE_ADDRESS_LINE_1, officeAddressLine1)
		'Select Office Address Line 2'
		commonPage.enterTextFieldValue(UI_OFFICE_ADDRESS_LINE_2, officeAddressLine2)
		'Select Office Address Line 3'
		commonPage.enterTextFieldValue(UI_OFFICE_ADDRESS_LINE_3, officeAddressLine3)
		'Select Postcode'
		commonPage.enterTextFieldValue(UI_POSTCODE, postcode)
		'Select City'
		commonPage.enterTextFieldValue(UI_CITY, city)

		commonPage.scrollToEnd()

		'Select State'
		commonPage.selectDropdownSiblingGroupItem(UI_STATE, state)
		'Select Office Phone Number'
		commonPage.enterTextFieldValue(UI_OFFICE_PHONE_NUMBER, officePhoneNumber)
		'Select Preferred Mailing Address'
		commonPage.selectDropdownSiblingGroupItem(UI_PREFERRED_MAILING_ADDRESS, preferredMailingAddress)

		'Select Save & Next'
		MobileDriver.tap(commonPage.lblDynamic(UI_SAVE_NEXT), timeout)
	}

	/**
	 * Input value for Personal Loan step 8
	 */
	void inputValueStep8(String monthlyNetIncome = '3800', String totalMonthlyCommitment = '3900',
			int timeout = GlobalVariable.G_timeoutLong, FailureHandling flowControl = defaultFailureHandling) {
		'Verify Monthly Net Income'
		MobileDriver.verifyElementExist(commonPage.lblDynamic(UI_MONTHLY_NET_INCOME), timeout)
		'Select Monthly Net Income'
		commonPage.enterTextFieldValue(UI_MONTHLY_NET_INCOME, monthlyNetIncome)
		'Select If you have loan/financing with any non-bank organisations, please enter your total monthly commitment.'
		commonPage.enterTextFieldValue(UI_TOTAL_MONTHLY_COMMITMENT, totalMonthlyCommitment)

		'Select Save & Next'
		MobileDriver.tap(commonPage.lblDynamic(UI_SAVE_NEXT), timeout)
	}
	/**
	 * Input value for Personal Loan step 9
	 */
	void inputValueStep9( int timeout = GlobalVariable.G_timeoutLong, FailureHandling flowControl = defaultFailureHandling) {
		'Verify Terms & Conditions'
		Mobile.verifyElementExist(commonPage.lblDynamic(UI_TERMS_CONDITIONS), timeout)

		Mobile.delay(GlobalVariable.G_timeoutShort)
		
		for (item in UI_LIST_TERM_CONDITITONS) {
			commonPage.scrollToText(item)
			if (item == UI_LIST_TERM_CONDITITONS.last()) {
				commonPage.scrollToEnd()
				Mobile.delay(GlobalVariable.G_timeoutShort)
			}
			MobileDriver.tap(commonPage.grpContainsPrecedingSibling(item), GlobalVariable.G_timeoutShort)
		}
		
		'Select Agree & Confirm'
		Mobile.delay(GlobalVariable.G_timeoutShort)
		MobileDriver.tap(commonPage.lblDynamic(UI_AGREE_CONFIRM), timeout)
	}

	/**
	 * Confirm Personal Loan
	 */
	void comfirmationStep( int timeout = GlobalVariable.G_timeoutLong, FailureHandling flowControl = defaultFailureHandling) {
		'Verify Please confirm the details below before submission'
		Mobile.verifyElementExist(commonPage.lblDynamic(UI_CONFIRMATION), timeout)

		'Verify Submit'
		Mobile.verifyElementExist(commonPage.lblDynamic(UI_SUBMIT), timeout)

		Mobile.delay(GlobalVariable.G_timeoutMedium)

		'Select Submit'
		MobileDriver.tap(commonPage.lblDynamic(UI_SUBMIT), timeout)
		
		'Verify TAC'
		if(MobileDriver.waitForElementPresent(commonPage.lblDynamic(UI_ENTER_TAC), GlobalVariable.G_timeoutLong, FailureHandling.OPTIONAL)) {
			this.tapTAC()
		}
		
		'Verify Upload Now'
		Mobile.verifyElementExist(commonPage.lblDynamic(UI_UPLOAD_NOW), timeout)
		MobileDriver.tap(commonPage.lblDynamic(UI_UPLOAD_NOW), GlobalVariable.G_timeoutMedium)

		//TODO
	}
	
	/**
	 * Upload file and get Ref ID
	 */
	void uploadFileStep(int timeout = GlobalVariable.G_timeoutLong, FailureHandling flowControl = defaultFailureHandling) {
		'Verify Upload Document Step'
		scrollToAndVerifyTextInList(UI_LIST_UPLOAD_DOCUMENTS, commonPage.lblDynamicPath(), GlobalVariable.G_timeoutLong)
		
		'ADD MyKad'
		MobileDriver.tap(commonPage.imgNoDynamic(1), GlobalVariable.G_timeoutLong, flowControl)
		
		'Click Got It'
		MobileDriver.tap(commonPage.lblDynamic(UI_GOT_IT), GlobalVariable.G_timeoutLong, flowControl)
		
		'Verify MyKad Verification'
		scrollToAndVerifyTextInList(UI_MYKAD_VERIFICATION, commonPage.lblDynamicPath(), GlobalVariable.G_timeoutLong)
		
		'Upload MyKad image Front'
		if(MobileDriver.waitForElementPresent(commonPage.lblDynamic("Front"), GlobalVariable.G_timeoutMedium, FailureHandling.OPTIONAL)) {
			'Upload MyKad image Front'
			MobileDriver.tap(commonPage.lblDynamic("Front"), GlobalVariable.G_timeoutMiniShort, flowControl)
			MobileDriver.delay(GlobalVariable.G_timeoutMiniShort)
			processCapture()
		}
		
		'Upload MyKad image Back'
		if(MobileDriver.waitForElementPresent(commonPage.lblDynamic("Back"), GlobalVariable.G_timeoutMedium, FailureHandling.OPTIONAL)) {
			'Upload MyKad image Front'
			MobileDriver.tap(commonPage.lblDynamic("Back"), GlobalVariable.G_timeoutMiniShort, flowControl)
			MobileDriver.delay(GlobalVariable.G_timeoutMiniShort)
			processCapture()
		}
		
		'Click Continue Button'
		MobileDriver.tap(commonPage.lblDynamic("Continue"), GlobalVariable.G_timeoutLong, flowControl)
		
		'ADD Income Verification'
		MobileDriver.delay(GlobalVariable.G_timeoutMedium)
		MobileDriver.tap(commonPage.imgNoDynamic(2), GlobalVariable.G_timeoutLong, flowControl)
		
		'Click Got It'
		MobileDriver.tap(commonPage.lblDynamic(UI_GOT_IT), GlobalVariable.G_timeoutLong, flowControl)
		processCapture()
		
		'Click Submit Button'
		MobileDriver.tap(commonPage.lblDynamic("Submit"), GlobalVariable.G_timeoutLong, flowControl)
	}
	
	void processCapture(FailureHandling flowControl = defaultFailureHandling) {
		'Select Take a photo from dropdown'
		MobileDriver.tap(commonPage.lblDynamic("Take a photo"), GlobalVariable.G_timeoutLong, flowControl)
		MobileDriver.delay(GlobalVariable.G_timeoutMiniShort)
		
		'Click to take picture'
		MobileDriver.tap(commonPage.imgNoDynamic(3), GlobalVariable.G_timeoutLong, flowControl)
		MobileDriver.delay(GlobalVariable.G_timeoutMiniShort)
			
		'Click Check icon'
		MobileDriver.tap(this.imageTick(), GlobalVariable.G_timeoutMedium, flowControl)
		MobileDriver.delay(GlobalVariable.G_timeoutShort)
	}
	
	/**
	 * Get Reference ID
	 */
	String verifyAndGetReferenceID(String regexString = REFERENCE_ID_REGEX, FailureHandling flowControl = defaultFailureHandling)  {
		'Verify Ref'
		MobileDriver.takeScreenshot()
		scrollToAndVerifyTextInList(UI_APPLICATION, commonPage.lblDynamicPath(), GlobalVariable.G_timeoutLong)
		
		'Verify Date Time'
		String dateAndTime = MobileDriver.getText(commonPage.lblSiblingDynamic(DateTimeUI), GlobalVariable.G_timeoutLong, flowControl)
		Mobile.comment("Date and Time: " + dateAndTime)
		
		'Wait for Reference ID visible'
		if(MobileDriver.waitForElementPresent(commonPage.lblSiblingDynamic(ReferenceIDUI), GlobalVariable.G_timeoutShort)) {
			'Get Reference ID'
			String refID = MobileDriver.getText(commonPage.lblSiblingDynamic(ReferenceIDUI), GlobalVariable.G_timeoutLong, flowControl)
			
			Mobile.comment("Reference ID: " + refID)

			'Verify Format Reference ID'
			Mobile.verifyMatch(refID, regexString, true, flowControl)

			'Click Done button'
			MobileDriver.tap(commonPage.lblDynamic("Done"), GlobalVariable.G_timeoutLong, flowControl)
			
			return refID
		}
		
		'Click Done button'
		MobileDriver.tap(commonPage.lblDynamic("Done"), GlobalVariable.G_timeoutLong, flowControl)

		return null
	}
	
	void tapTAC() {
		Mobile.takeScreenshot()
		if(commonPage.waitForElementPresent(commonPage.lblStartsWith("Your TAC no. is"), GlobalVariable.G_timeoutMedium, FailureHandling.OPTIONAL)) {
		String TAC = commonPage.getText(commonPage.lblStartsWith("Your TAC no. is"), GlobalVariable.G_timeoutMedium)
		Mobile.comment("TAC is: " + TAC)
			
		'Get 6 digits from TAc'
		String TAC6Digits = TAC.takeRight(6)
		
		'Enter 6 digits'
		commonPage.enterNO(TAC6Digits)
		} else {
			MobileDriver.delay(GlobalVariable.G_timeoutMedium)
			MobileDriver.tap(commonPage.btnTick(), GlobalVariable.G_timeoutLong, GlobalVariable.G_timeoutMedium)
		}
		
		
	}
	
}