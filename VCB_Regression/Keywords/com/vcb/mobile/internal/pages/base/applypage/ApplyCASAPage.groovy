package com.vcb.mobile.internal.pages.base.applypage

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import java.nio.file.*
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle

import org.apache.commons.lang.RandomStringUtils
import org.apache.poi.ss.usermodel.*
import org.apache.poi.xssf.usermodel.*
import org.openqa.selenium.By
import org.openqa.selenium.WebElement

import com.kms.katalon.core.configuration.RunConfiguration
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.util.KeywordUtil
import com.vcb.common.MobileDriver
import com.vcb.mobile.internal.pages.base.BasePage
import com.vcb.mobile.internal.pages.base.BiometricPage
import com.vcb.mobile.internal.pages.base.CommonPage
import com.vcb.mobile.internal.pages.base.CommonPage.TestObjectTypeEnum
import com.vcb.mobile.internal.pages.base.HomePage
import com.vcb.mobile.internal.pages.base.OtpPage
import com.vcb.mobile.internal.pages.base.PinPage
import com.vcb.mobile.internal.pages.base.Secure2uPage
import com.vcb.mobile.internal.pages.base.WelcomeOnboardPage
import com.vcb.mobile.internal.pages.base.OnboardingPage
import com.vcb.mobile.internal.pages.base.LoginPage
import com.vcb.utils.StringUtils
import com.vcb.utils.TouchActionUtils

import internal.GlobalVariable
import io.appium.java_client.android.AndroidDriver

public abstract class ApplyCASAPage extends BasePage{

	CommonPage commonPage = getPage(CommonPage)
	HomePage homePage = BasePage.getPage(HomePage)
	ApplyAccountPage applyAccountPage = BasePage.getPage(ApplyAccountPage)
	OtpPage PAGE_OBJECT = BasePage.getPage(OtpPage)
	PinPage pinPage = BasePage.getPage(PinPage)
	BiometricPage biometricPage = BasePage.getPage(BiometricPage)
	OtpPage otpPage = BasePage.getPage(OtpPage)
	WelcomeOnboardPage welcomeOnboardPage = BasePage.getPage(WelcomeOnboardPage)
	Secure2uPage secure2uPage = BasePage.getPage(Secure2uPage)
	OnboardingPage onboardingPage = BasePage.getPage(OnboardingPage)
	LoginPage loginPage = BasePage.getPage(LoginPage)
	StringUtils stringUtils = new StringUtils()

	abstract TestObject imgDropDownSiblingGroup(String label, int index=1)
	abstract TestObject followingSiblingOption(String key, String value)
	abstract TestObject lblFollowingSibling(String key)
	abstract TestObject btnSiblingGroup(String label, String text)	
	abstract TestObject txtFieldWithIndex(String index)
	abstract TestObject lblNormalizeFollowingSibling(String text)
	abstract TestObject btnConfirm()
	abstract TestObject btnEkycPassport()

	/*
	 * Verify UI Quick Action at HomePage
	 */
	boolean quickActions() {
		MobileDriver.verifyElementVisible(commonPage.lblDynamic('Quick Actions'), GlobalVariable.G_timeoutShort, FailureHandling.OPTIONAL)
	}
	
	/*
	 * Select Account To Apply
	 */
	void selectAccountType(String cardName, FailureHandling flowControl = defaultFailureHandling) {
		'Scroll to account'
		commonPage.scrollToTestObject(commonPage.frmHorizontalScrollNo(2), commonPage.lblNormalizeSpace(cardName), ScrollDirectionEnum.DOWN, GlobalVariable.G_timeoutShort, flowControl)
		
		'Delay'
		MobileDriver.delay(GlobalVariable.G_timeoutMiniShort)
		
		'Tap Account'
		MobileDriver.tap(commonPage.lblNormalizeSpace(cardName), GlobalVariable.G_timeoutMedium, flowControl)
		
		'Take Screenshot'
		commonPage.waitLoading()
		MobileDriver.takeScreenshot()
	}
	
	/*
	 * Selects ID Type and Enter MyKad Number
	 */
	String chooseIdTypeandMykad(String idType, String myKadNumber, boolean runValidations = false, int timeout = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		'Select ID Type'
		chooseIdType(idType)

		'Enter Mykad Number'
		myKadNumber = enterMykadNumber(myKadNumber, runValidations)
		
		'Tap Next'
		MobileDriver.takeScreenshot()
		commonPage.tapNextBtn()
		
		return myKadNumber
	}

	/*
	 * Selects ID Type and Enter Passport Number
	 */
	Map chooseIdTypeandPassport(String idType, Map<String,String> passportDetails,  boolean runValidations = false, int timeout = GlobalVariable.G_timeoutMedium,  FailureHandling flowControl = defaultFailureHandling) {
		'Select ID Type'
		chooseIdType(idType)

		'Enter Passport Number and Other Informations'
		Map passportInfo = fillPassportInformation(passportDetails, runValidations)
			
		'Tap Next'
		MobileDriver.takeScreenshot()
		commonPage.tapNextBtn()
		
		return passportInfo
	}
	
	/*
	 * Suitability Assessment Page
	 */
	String SA = 'Suitability assessment'
	List<String> infoSA = [
		'Premier Mudharabah Account-i is an account where its profit is tied to the performance of the underlying assets',
		'Zest-i Account is an account where its profit is tied to the performance of the underlying assets'
		]
	
	boolean fillSuitabilityAssessment(Map<String, String> suitabilityAssessment, int timeout = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		if (!MobileDriver.verifyElementVisible(commonPage.lblDynamic(SA), timeout, FailureHandling.OPTIONAL)) {
			return false
		}
	
		verifyElements(infoSA, commonPage.lblContainDynamicPath(), GlobalVariable.G_timeoutMiniShort, FailureHandling.OPTIONAL)
	
		List<String> keyList = getKeyInMap(suitabilityAssessment)
		boolean hasScrolled = false
		int index = 0
	
		suitabilityAssessment.each { key, value ->
			'Scroll down'
			if (!hasScrolled && index >= keyList.size() / 2) {
				delay(GlobalVariable.G_timeoutMiniShort)
				commonPage.swipe(5, 1500, 5, 100)
				MobileDriver.delay(GlobalVariable.G_timeoutMiniShort)
				hasScrolled = true
			}
	
			switch (key) {
				case "My financial objective":
					applyAccountPage.selectItemDropDownList(key, value);
					break
	
				default:
					MobileDriver.doubleTap(followingSiblingOption(key, value), timeout, flowControl)
				
					break
			}
			index++
		}
	
		'Tap Next'
		commonPage.tapNextBtn()
		return true
	}

	/*
	 * Sign Up VCB Code Page
	 */
	String maeCode = 'Have a Sign-up Code?'
	
	boolean signUpCodeMaePage(int timeout = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		if (!MobileDriver.verifyElementVisible(commonPage.lblDynamic(maeCode), timeout, FailureHandling.OPTIONAL)) {
			return false
		}
	
		if (MobileDriver.verifyElementVisible(commonPage.lblDynamic("Skip"), timeout, FailureHandling.OPTIONAL)) {
			MobileDriver.tap(commonPage.lblDynamic("Skip"), timeout, flowControl)
		}	
		return true
	}

	/*
	 * eKYC Bypass Page
	 */	
	String FRONT = 'Front'
	
	List<String> eKYCIntroPage = [
		'Hi there,',
		"We need to know if it's really you. Please take a selfie and capture your ID.",
		'We will extract your personal information from your ID to simplify your experience',
		'Find a place with good lighting',
		'Ensure your ID is clear and readable',
		'Ensure your face fits in the frame and all your facial features can be seen clearly'
		]
		
	List<String> eKYCMyKadPage = [
		'Verification Needed',
		"Let's begin by scanning the front and back of your MyKad"
		]
		
	void eKYCBypass(boolean runValidations = false, int timeout = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		'Delay'
		MobileDriver.delay(GlobalVariable.G_timeoutMiniShort)
		
		if (runValidations) {
			'Verify eKYC intro page'
			verifyElements(eKYCIntroPage, commonPage.lblContainDynamicPath(), GlobalVariable.G_timeoutMedium, flowControl)
		}

		'Tap Next'
		commonPage.tapNextBtn()

		if (runValidations) {
			'Verify eKYC MyKad page'
			verifyElements(eKYCMyKadPage, commonPage.lblContainDynamicPath(), GlobalVariable.G_timeoutMedium, flowControl)
		}
		
		'Verify eKYC bypass'
		MobileDriver.verifyElementVisible(commonPage.lblDynamic(FRONT), GlobalVariable.G_timeoutMedium, flowControl)

		'Tap eKYC bypass'
		MobileDriver.tap(commonPage.lblDynamic(FRONT), GlobalVariable.G_timeoutShort, flowControl)
		
		'Take Screenshot'
		MobileDriver.takeScreenshot()

		'Tap Next'
		MobileDriver.delay(GlobalVariable.G_timeoutShort)
		commonPage.tapNextBtn()
	}

	/*
	 * eKYC Bypass Page
	 * Passport
	 */
	List<String> eKYCPassportPage = [
		'Verification Needed',
		'Passport Verification',
		'Snap a photo of your passport biodata page.'
		]
		
	void eKYCBypassPassport(boolean runValidations = false, int timeout = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		'Delay'
		MobileDriver.delay(GlobalVariable.G_timeoutMiniShort)
		
		if (runValidations) {
			'Verify eKYC intro page'
			verifyElements(eKYCIntroPage, commonPage.lblContainDynamicPath(), GlobalVariable.G_timeoutMedium, flowControl)
		}

		'Tap Next'
		commonPage.tapNextBtn()

		if (runValidations) {
			'Verify eKYC MyKad page'
			verifyElements(eKYCPassportPage , commonPage.lblContainDynamicPath(), GlobalVariable.G_timeoutMedium, flowControl)
		}

		'Tap eKYC bypass'
		MobileDriver.tap(this.btnEkycPassport(), GlobalVariable.G_timeoutShort, flowControl)
		
		'Take Screenshot'
		MobileDriver.takeScreenshot()

		'Tap Next'
		MobileDriver.delay(GlobalVariable.G_timeoutShort)
		commonPage.tapNextBtn()
	}

	/*
	 * Personal Details Page
	 * NRIC
	 */
	List<String> getKeyInMap(Map<String, String> mapData) {
		List<String> keyList = new ArrayList<>();
		for (Map.Entry<String, String> entry : mapData.entrySet()) {
			keyList.add(entry.getKey());
		}
		return keyList
	}
	
	String fullName
	String mobileNumber
	
	Map fillPersonalDetails(Map<String,String> personalDetails, boolean runValidations = false, int timeout = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		MobileDriver.delay(GlobalVariable.G_timeoutShort)
		List<String> keyList = getKeyInMap(personalDetails)
		int index;	
	
		'Use existing value or generate if no value'
		fullName = personalDetails['Full name']?.trim() ? personalDetails['Full name'] : "AutoUser ${RandomStringUtils.randomAlphabetic(5)}"
		mobileNumber = personalDetails['Mobile Number']?.trim() ? personalDetails['Mobile Number'] : "17000${RandomStringUtils.randomNumeric(4)}"
	
		personalDetails.each { key, value ->
			switch(key) {
				case "Full name":
					commonPage.setText(commonPage.txtSiblingGroup(key), fullName, timeout, flowControl)
					break
				case "Mobile Number":
					commonPage.setText(commonPage.txtSiblingGroup(key), mobileNumber, timeout, flowControl)
					break
				case "Email address":
					commonPage.setText(commonPage.txtSiblingGroup(key), personalDetails.get(key), timeout, flowControl);
					break
				default:
					if(index >= keyList.size()/2) {
						delay(GlobalVariable.G_timeoutMiniShort)
						commonPage.swipe(5, 1500, 5, 100)
					}
					applyAccountPage.selectItemDropDownList(key, value);
			}
		}

		'Tap No To PEP'
		MobileDriver.delay(GlobalVariable.G_timeoutMiniShort)
		tapNo()
		
		if (runValidations) {
			'Verify PEP Tooltip'
			verifyPEPTooltip()
		}
		
		'Tap Next'
		MobileDriver.takeScreenshot()
		commonPage.tapNextBtn()
		
		return [
			'Full name'     : fullName,
			'Mobile Number' : mobileNumber
		]
	}

	/*
	 * Personal Details Page
	 * Passport
	 */
	String fillPersonalDetailsPassport(Map<String,String> personalDetails, String accountType, int timeout = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		List<String> keyList = getKeyInMap(personalDetails)
		int index = 0
	
		'Use existing value or generate if no value'
		mobileNumber = personalDetails['Mobile Number']?.trim() ? personalDetails['Mobile Number'] : "17000${RandomStringUtils.randomNumeric(4)}"
		
		personalDetails.each { key, value ->
			switch(key) {
				case "Mobile Number":
					commonPage.setText(commonPage.txtSiblingGroup(key), mobileNumber, timeout, flowControl)
					break
	
				case "Email address":
					commonPage.setText(commonPage.txtSiblingGroup(key), personalDetails.get(key), timeout, flowControl)
					break
	
				case "Gender":
					MobileDriver.tap(commonPage.grpPrecedingSibling(value), timeout,  flowControl)
					break
	
				default:
					if(index >= keyList.size()/2) {
						delay(GlobalVariable.G_timeoutMiniShort)
						commonPage.swipe(5, 1500, 5, 100)
					}
					applyAccountPage.selectItemDropDownList(key, value);
			}
			index++
		}
	
		'Tap No To PEP'
		commonPage.swipe(5, 1500, 5, 100)
		MobileDriver.delay(GlobalVariable.G_timeoutMiniShort)
		tapNo()
	
		'Tap Next / Confirm (for G1A)'
		MobileDriver.takeScreenshot()
		(accountType == "Global Access Account-i") ? tapConfirmBtn() : commonPage.tapNextBtn()

		return mobileNumber
	}

	/*
	 * Employment Details Page
	 */
	void fillEmploymentDetails(Map<String,String> employmentDetails, int timeout = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		'Verify Page'
		MobileDriver.waitForElementPresent(commonPage.lblContainDynamic("Please fill in your employment details"), GlobalVariable.G_timeoutMedium, flowControl)
		
		List<String> keyList = getKeyInMap(employmentDetails)
		boolean hasScrolled = false

		employmentDetails.each { key, value ->
			'Scroll before reaching "Monthly income" if not already done'
			if (!hasScrolled && key == "Monthly income") {
				delay(GlobalVariable.G_timeoutMiniShort)
				commonPage.swipe(5, 1500, 5, 100)
				hasScrolled = true
			}

			switch(key) {
				case "Occupation":
				case "Sector":
				case "Employment type":
				case "Monthly income":
				case "Source of income":
					applyAccountPage.selectItemDropDownList(key, value);

					break
				case "Employer Name":
					commonPage.setText(commonPage.txtSiblingGroup(key), employmentDetails.get(key), timeout, flowControl);
					break
				default:
					if (!hasScrolled) {
						delay(GlobalVariable.G_timeoutMiniShort)
						MobileDriver.takeScreenshot()
						commonPage.swipe(5, 1500, 5, 100)
						hasScrolled = true
					}
			}
		}

		'Tap Next'
		MobileDriver.takeScreenshot()
		commonPage.tapNextBtn()
	}
	
	/*
	 * Preferred Branch
	 * Passport
	 */
	void preferredBranch(Map<String, String> branchDetails, int timeout = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		'Select Branch Details'
		List<String> keyList = getKeyInMap(branchDetails);

		branchDetails.each { key, value ->
			switch(key) {
				case "State":
				case "District":
				case "Branch":
					applyAccountPage.selectItemDropDownList(key, value);
					break;
			}
		}

		'Tap Next'
		commonPage.tapNextBtn()
	}
	
	/*
	 * Apply Debit Card
	 */
	List<String> debitCardPage = [
		'Debit Card Application',
		'Select the right debit card for you',
		'Note: You will be charged RM 8.00 upon successful card application.'
		]

	List<String> debitCardG1APage = [
		'Debit Card',
		'Here is the pre-selected debit card design for you',
		'Note: RM8.00 annual fee will be charged upon successful application to activate unlimited ATM withdrawal.'
		]
		
	void applyDebitCard(boolean applyCardNow, String debitCard, String accountType, FailureHandling flowControl = defaultFailureHandling) {
		'Verify Page'
		def page = (accountType == "Global Access Account-i") ? debitCardG1APage : debitCardPage
		verifyElements(page, commonPage.lblContainDynamicPath(), GlobalVariable.G_timeoutMedium, flowControl)
		MobileDriver.takeScreenshot()

		if (!applyCardNow) {
			'Tap Apply Later'
			MobileDriver.tap(commonPage.lblContainDynamic("Apply Later"), GlobalVariable.G_timeoutMedium, flowControl)
		} else {
			'Tap The Type of Debit Card'
			MobileDriver.tap(commonPage.lblDynamic(debitCard), GlobalVariable.G_timeoutMedium, flowControl)
	
			'Tap Next'
			MobileDriver.takeScreenshot()
			commonPage.tapNextBtn()
		}
	}

	/*
	 * Delivery Address Details Page
	 */
	List<String> deliveryDetailPage = [
		'Debit card application',
		"We’ve pre-filled your delivery address. Kindly edit as you see fit"
		]
	
	List<String> deliveryDetailG1APage = [
		'Debit Card Delivery Details',
		"We’ve pre-filled your delivery address. Kindly note that P.O. Box addresses will not be accepted."
		]
			
	void fillDeliveryCardDetails(Map<String,String> residentialDetails, String accountType, int timeout = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		'Verify Page'
		def deliveryPage = (accountType == "Global Access Account-i") ? deliveryDetailG1APage : deliveryDetailPage
		verifyElements(deliveryPage, commonPage.lblContainDynamicPath(), GlobalVariable.G_timeoutMedium, flowControl)
		MobileDriver.takeScreenshot()
		
		List<String> keyList = getKeyInMap(residentialDetails)
		boolean hasScrolled = false

		residentialDetails.each { key, value ->
			'Scroll before reaching "State" if not already done'
			if (!hasScrolled && key == "State") {
				delay(GlobalVariable.G_timeoutMiniShort)
				commonPage.swipe(5, 1500, 5, 100)
				hasScrolled = true
			}
			switch(key) {
				case "Address line 1":
				case "Address line 2":
				case "Postcode":
				case "City":
					commonPage.setText(commonPage.txtSiblingGroup(key), residentialDetails.get(key), timeout, flowControl);
					break
				case "State":
					MobileDriver.delay(GlobalVariable.G_timeoutMiniShort)
					applyAccountPage.selectItemDropDownList(key, value);
					break
			}
		}

		'Terms and Conditions'
		MobileDriver.delay(GlobalVariable.G_timeoutMiniShort)
		tapTermsAndNext()
	}
	
	/*
	 * Terms and Conditions
	 */
	void tapTermsAndNext(FailureHandling flowControl = defaultFailureHandling) {
		TestObject termsLabel = commonPage.lblContainDynamic("I have read and agree")
		'Try tap'
		commonPage.tap(termsLabel, GlobalVariable.G_timeoutMedium, flowControl)
		commonPage.tapNextBtn()
		if (MobileDriver.waitForElementPresent(commonPage.lblContainDynamic("Declaration"), GlobalVariable.G_timeoutShort, FailureHandling.OPTIONAL)) {
			return
		}
	
		'Try double tap'
		commonPage.doubleTap(termsLabel, GlobalVariable.G_timeoutMedium)
		commonPage.tapNextBtn()
		if (MobileDriver.waitForElementPresent(commonPage.lblContainDynamic("Declaration"), GlobalVariable.G_timeoutShort, FailureHandling.OPTIONAL)) {
			return
		}
	
		'Try tap and hold'
		Mobile.tapAndHold(termsLabel, 2, GlobalVariable.G_timeoutMiniShort, flowControl)
		commonPage.tapNextBtn()
		if (MobileDriver.waitForElementPresent(commonPage.lblContainDynamic("Declaration"), GlobalVariable.G_timeoutShort, FailureHandling.OPTIONAL)) {
			return
		}
		
		'Try tap at the circle'
		MobileDriver.tap(commonPage.imgNoDynamic(4), GlobalVariable.G_timeoutMiniShort, flowControl)
		commonPage.tapNextBtn()	
	}
	
	/*
	 * Residential Details Page
	 */
	void fillResidentialDetails(Map<String,String> residentialDetails, int timeout = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		'Verify Page'
		MobileDriver.waitForElementPresent(commonPage.lblContainDynamic("Please fill in your residential details"), GlobalVariable.G_timeoutMedium, flowControl)
		MobileDriver.takeScreenshot()
		
		List<String> keyList = getKeyInMap(residentialDetails)
		boolean hasScrolled = false

		residentialDetails.each { key, value ->
			'Scroll before reaching "State" if not already done'
			if (!hasScrolled && key == "State") {
				delay(GlobalVariable.G_timeoutMiniShort)
				commonPage.swipe(5, 1500, 5, 100)
				hasScrolled = true
			}
			switch(key) {
				case "Address line 1":
				case "Address line 2":
				case "Postcode":
				case "City":
					commonPage.setText(commonPage.txtSiblingGroup(key), residentialDetails.get(key), timeout, flowControl);
					break
				case "State":
					MobileDriver.delay(GlobalVariable.G_timeoutMiniShort)
					applyAccountPage.selectItemDropDownList(key, value);
					break
			}
		}

		'Tap Next'
		MobileDriver.takeScreenshot()
		commonPage.tapNextBtn()
	}
	
	
	/*
	 * Declaration Page
	 */
	List<String> declarationPage = [
		'I have read and agree to be bound by the Terms and Conditions and Product Disclosure Sheet(s).',
		"I understand that this account is protected by PIDM up to RM250,000 for each depositor. I have read PIDM's DIS Brochure.",
		'I hereby confirm, consent and declare that:',
		'I am a Non-US Person with no US Indicia',
		'I am a Tax Resident in Malaysia',
		'I shall notify Vcb within 30 calendar days of any changes of the above information',
		'I have read, agree and accept the terms of The Vcb Group Privacy Statement. For marketing or products and services by Vcb Group/other entities referred to in the Privacy Statement:',
		'Yes, I expressly agree to be contacted',
		'No, I do not agree to be contacted',
		'I hereby agree to accept this product/service and that the use of electronic messages and electronic acceptance for all matters related to this product/service shall be binding in accordance with the Electronic Commerce Act 2006.'
		]
		
	void declarationPage(boolean runValidations = false, String declarationText, int timeout = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		'Verify Page'
		MobileDriver.waitForElementPresent(commonPage.lblContainDynamic("Declaration"), GlobalVariable.G_timeoutMedium, flowControl)
		
		if (runValidations) {
			'Verify Static UI'
			scrollToAndVerifyTextInList(declarationPage, commonPage.lblDynamicPath(), GlobalVariable.G_timeoutMedium, flowControl)
		}
		
		'Tap on Declaration Text'
		MobileDriver.tap(commonPage.lblDynamic(declarationText), timeout, flowControl);
		MobileDriver.takeScreenshot()
		
		'Tap Agree And Confirm'
		MobileDriver.tap(commonPage.lblDynamic(applyAccountPage.AGREE_AND_CONFIRM), GlobalVariable.G_timeoutMedium, flowControl)
	}

	/*
	 * Confirmation Page
	 * 
	 */ 
	String EDIT = 'Edit'
	String PREFERRED_BRANCH_DETAILS = 'Preferred Branch Details'
	
	void confirmationPage(boolean applyCardNow = false, String idType, Map<String, String> personalDetails, Map<String, String> residentialDetails, Map<String, String> employmentDetails, Map<String, String> branchDetails,
			boolean editBranchDetails = false, int timeout = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {

		if (applyCardNow) {
			'Verify Personal Details'
			verifyPersonalDetails(idType, personalDetails)
	
			'Verify Delivery Address Details'
			verifyDeliveryAddressDetails(residentialDetails)
	
			'Verify Employment Details'
			verifyEmploymentDetails(employmentDetails)
	
			'Edit Preferred Branch Details'
			if (editBranchDetails) {
				if (MobileDriver.waitForElementPresent(btnSiblingGroup(PREFERRED_BRANCH_DETAILS, EDIT), GlobalVariable.G_timeoutMedium, FailureHandling.OPTIONAL)) {
					MobileDriver.tap(btnSiblingGroup(PREFERRED_BRANCH_DETAILS, EDIT), GlobalVariable.G_timeoutMedium, flowControl)
				
					'Tap on I Prefer Another Branch'
					MobileDriver.waitForElementPresent(commonPage.lblContainDynamic("We've pre-selected your nearest branch"), GlobalVariable.G_timeoutMedium, flowControl)
					MobileDriver.tap(commonPage.lblDynamic("I Prefer Another Branch"), GlobalVariable.G_timeoutMedium, flowControl)
	
					'Edit Branch Details'
					List<String> keyList = getKeyInMap(branchDetails);
	
					branchDetails.each { key, value ->
						switch(key) {
							case "State":
							case "District":
							case "Branch":
								applyAccountPage.selectItemDropDownList(key, value);
								break;
						}
					}
	
					'Tap Confirm at Edit Branch Page'
					MobileDriver.takeScreenshot()
					tapConfirmBtn()
				}
			}
			
			'Tap Confirm at Confirmation Page'
			commonPage.scrollToEnd()
			MobileDriver.takeScreenshot()
			tapConfirmBtn()
			
		} else {
			'Verify Personal Details'
			verifyPersonalDetails(idType, personalDetails)
			MobileDriver.takeScreenshot()
	
			'Verify Employment Details'
			commonPage.scrollToEnd()
			verifyEmploymentDetails(employmentDetails)
	
			'Tap Confirm'
			MobileDriver.takeScreenshot()
			tapConfirmBtn()
		}	
	}
	
	/*
	 * Request OTP Page
	 */
	String REQUEST_OTP = "Request OTP"
	String YOUR_OTP = "Your Otp"
	
	void verifyOtpPage(int timeout = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		MobileDriver.verifyElementVisible(commonPage.lblContainDynamic("****"), timeout)

		'Click Request OTP'
		clickRequestOTP()

		'Get OTP'
		MobileDriver.verifyElementVisible(commonPage.lblContainDynamic(YOUR_OTP), timeout)
		String otp = commonPage.getText(commonPage.lblContainDynamic(YOUR_OTP), timeout, flowControl)
		String pinNo = otp.substring(Math.max(otp.length() - 6, 0));
		info("pinNo value: ${pinNo}");
		MobileDriver.takeScreenshot()

		'Enter the OTP'
		PAGE_OBJECT.enterPINNO(pinNo, flowControl)

		'Tap Tick'
		PAGE_OBJECT.tapTickBtn()
	}

	/*
	 * Verify Success Page
	 */
	String M2U = "Register M2U Access"
	
	TestObject m2uLabel = commonPage.lblContainDynamic("Register M2U Access")
	
	void verifySuccessPage(List statusScreenSuccess, int timeout = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		'Wait to next Page'
		commonPage.waitLoading()

		'Verify Status Screen'
		verifySomeElementsMatchInList(statusScreenSuccess, commonPage.lblContainDynamicPath(), timeout, FailureHandling.OPTIONAL)

		'Tap on Register M2U Access'
		MobileDriver.takeScreenshot()
		MobileDriver.verifyElementVisible(commonPage.lblDynamic(M2U), timeout)
		commonPage.tap(commonPage.lblDynamic("Register M2U Access"), GlobalVariable.G_timeoutMedium)
		MobileDriver.delay(GlobalVariable.G_timeoutMiniShort)
		if (MobileDriver.verifyElementVisible(commonPage.lblDynamic('Register M2U Access'), GlobalVariable.G_timeoutMiniShort, FailureHandling.OPTIONAL)) {
			commonPage.tap(commonPage.lblTextViewDynamic("Register M2U Access", 2), GlobalVariable.G_timeoutMedium)
		}
			
	}

	/*
	 * Verify Success Page for G1A
	 */
	String M2UIDG1APage = "Create Vcb2u ID"
	
	void verifyG1ASuccessPage(List statusScreenSuccess, int timeout = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		'Wait to next Page'
		commonPage.waitLoading()

		'Verify Status Screen'
		verifySomeElementsMatchInList(statusScreenSuccess, commonPage.lblContainDynamicPath(), timeout, FailureHandling.OPTIONAL)

		'Tap on Create Vcb2u ID'
		MobileDriver.takeScreenshot()
		MobileDriver.verifyElementVisible(commonPage.lblDynamic(M2UIDG1APage), timeout)
		commonPage.tap(commonPage.lblDynamic("Create Vcb2u ID"), GlobalVariable.G_timeoutMedium)
		if (MobileDriver.verifyElementVisible(commonPage.lblDynamic('Create Vcb2u ID'), GlobalVariable.G_timeoutMiniShort, FailureHandling.OPTIONAL)) {
			commonPage.tap(commonPage.lblTextViewDynamic("Create Vcb2u ID", 2), GlobalVariable.G_timeoutMedium)
		}
			
	}

	/*
	 * M2U ID Registration Page
	 */
	String M2UID = "Create a Vcb2u ID"
	String SHOW_PASSWORD = "Show password"
	
	String M2UIDPage(String m2uUsername, String m2uPassword, int timeout = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		'Verify Page'
		MobileDriver.verifyElementVisible(commonPage.lblDynamic(M2UID), timeout)
		
		'If username is empty/null, use Full name from personalDetails'
		if (!m2uUsername?.trim()) {
			m2uUsername = fullName.replace(" ", "")
		}

		'Enter Username'
		commonPage.setText(commonPage.txtSiblingGroup(M2UID), m2uUsername, timeout, flowControl)

		'Tap on Continue'
		commonPage.tapContinueBtn()
		MobileDriver.delay(GlobalVariable.G_timeoutMiniShort)
		
		'Enter Password'
		MobileDriver.waitForElementPresent(commonPage.lblDynamic(M2UID), GlobalVariable.G_timeoutShort)
		MobileDriver.waitForElementPresent(commonPage.lblDynamic(SHOW_PASSWORD), GlobalVariable.G_timeoutShort)
		commonPage.setEncryptedText(commonPage.txtEditNo(1), m2uPassword, GlobalVariable.G_timeoutMedium, flowControl)
		commonPage.setEncryptedText(commonPage.txtEditNo(2), m2uPassword, GlobalVariable.G_timeoutMedium, flowControl)

		'Delay'
		MobileDriver.delay(GlobalVariable.G_timeoutMiniShort)

		'Tap on Show Password'
		MobileDriver.tap(commonPage.lblDynamic(SHOW_PASSWORD), timeout, flowControl)
		MobileDriver.tap(commonPage.grpPrecedingSibling(SHOW_PASSWORD), timeout, flowControl)
		MobileDriver.takeScreenshot()

		'Tap on Continue'
		commonPage.tapContinueBtn()
		MobileDriver.delay(GlobalVariable.G_timeoutMiniShort)
		
		return m2uUsername
	}

	/*
	 * Push Notification
	 */
	void verifyPushNotification(Map<String, String> pushNotificationMessage, FailureHandling flowControl = FailureHandling.STOP_ON_FAILURE, TestObjectTypeEnum testObjectType = TestObjectTypeEnum.TEXT, boolean isG1A = false) {
		'Wait to next Page'
		commonPage.waitLoading()
		
		'Verify Page'
		MobileDriver.waitForElementPresent(commonPage.lblDynamic('Upload a security image'), GlobalVariable.G_timeoutLong, FailureHandling.OPTIONAL)
		
		String titlePN = pushNotificationMessage.get("titlePN")
		String messagePN = pushNotificationMessage.get("messagePN")
	
		'Open Notification'
		Mobile.openNotifications()
	
		try {
			MobileDriver.delay(GlobalVariable.G_timeoutMiniShort)
			MobileDriver.takeScreenshot()
	
			Mobile.comment("Verifying push notification with title: ${titlePN}")
			
			String bodyPN = commonPage.getText(commonPage.lblContainDynamic(messagePN), GlobalVariable.G_timeoutLong, flowControl)
			
			KeywordUtil.logInfo("messagePN: ${messagePN}")
			KeywordUtil.logInfo("bodyPN: ${bodyPN}")
	
			switch (testObjectType) {
				case TestObjectTypeEnum.TEXT:
					Mobile.verifyMatch(bodyPN, messagePN, false)
					break
				case TestObjectTypeEnum.CONTAINS:
					if (isG1A) {
						assert bodyPN.contains(messagePN) : "Expected message not found: successfully set up a future order to convert"
					} else {
						assert bodyPN.contains(messagePN) : "Expected notification not found in body"
					}
					break
			}
		} catch (Exception ex) {
			Mobile.pressBack()
			handleError(ex, flowControl)
			return
		}
		
		Mobile.pressBack()
	}
	
	/*
	 * M2U Security Page (image and phrase) 
	 */
	void securityImageAndPhrase(String securityPhrase, int timeout = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		'Verify Page'
		MobileDriver.verifyElementVisible(commonPage.lblDynamic(M2UID), timeout)

		'Tap TAKE PHOTO'
		MobileDriver.tap(commonPage.imgSiblingGroup('Upload a security image'), timeout, flowControl)
		MobileDriver.tap(commonPage.lblDynamic('TAKE PHOTO'), timeout, flowControl)
		MobileDriver.tap(imgDropDownSiblingGroup('Place your photo in the circle', 2), timeout, flowControl)
		
		'Delay'
		MobileDriver.delay(GlobalVariable.G_timeoutShort)

		'Tap on Continue'
		MobileDriver.takeScreenshot()
		commonPage.tapContinueBtn()

		'Enter Security Phrase'
		commonPage.setText(commonPage.txtSiblingGroup(M2UID), securityPhrase, timeout, flowControl)

		'Tap on Continue'
		MobileDriver.takeScreenshot()
		commonPage.tapContinueBtn()
		
		'Delay'
		MobileDriver.delay(GlobalVariable.G_timeoutShort)
	}

	/*
	 * M2U Security Questions
	 */
	String SECURITY = "Set up security questions"
	
	void fillSecurityQuestions(Map<String, String> securityQuestions, int timeout = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		'Verify Page'
		MobileDriver.waitForElementPresent(commonPage.lblDynamic(SECURITY), GlobalVariable.G_timeoutLong)
		if (MobileDriver.verifyElementVisible(commonPage.lblDynamic(SECURITY), timeout)) {
			'Delay'
			MobileDriver.delay(GlobalVariable.G_timeoutShort)
			
			'Fill in Security Questions and Answers'
			SecurityQuestions(securityQuestions)
			MobileDriver.takeScreenshot()

			'Tap on Continue'
			commonPage.tapContinueBtn()
		}
	}

	/*
	 * FPX Flow
	 */
	String ACTIVATE_ACCOUNT = "Activate your account now"
	
	List<String> fpxFirstPage = [
		'You can continue to activate your account now or at any of our branches within 10 business days.',
		'To activate now, you will be required to:',
		'Make a transfer from your other bank account into your new Vcb account'
		]	
		
	List<String> fpxSecondPage = [
		'Make a minimum transfer of RM10.00 from another bank account registered to your own name and MyKad number to your account to activate it',
		'Note: We will not activate your account if the money was transferred from an account that does not belong to you.',
		'In the event the first-party transfer fails, refund will be initiated within 7 business days.'
		]
		
	void fpxFlow(boolean runValidations = false, String bank, int timeout = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		'Verify First Page'
		MobileDriver.waitForElementPresent(commonPage.lblDynamic(ACTIVATE_ACCOUNT), GlobalVariable.G_timeoutShort)
		if (runValidations) {
			verifyElements(fpxFirstPage, commonPage.lblContainDynamicPath(), GlobalVariable.G_timeoutMedium, flowControl)
		}
		
		'Take Screenshot'
		MobileDriver.takeScreenshot()

		'Tap Activate Now'
		MobileDriver.tap(commonPage.lblDynamic('Activate Now'), GlobalVariable.G_timeoutMedium, flowControl)
		MobileDriver.delay(GlobalVariable.G_timeoutShort)

		'Verify Second Page'
		MobileDriver.delay(GlobalVariable.G_timeoutMiniShort)
		MobileDriver.waitForElementPresent(commonPage.lblContainDynamic(ACTIVATE_ACCOUNT), GlobalVariable.G_timeoutShort)
		if (runValidations) {
			verifyElements(fpxSecondPage, commonPage.lblContainDynamicPath(), GlobalVariable.G_timeoutMedium, flowControl)
		}
		
		'Take Screenshot'
		MobileDriver.takeScreenshot()

		'Tap Next'
		MobileDriver.delay(GlobalVariable.G_timeoutMiniShort)
		commonPage.tapNextBtn()

		'Scroll To Find Bank'
		Mobile.scrollToText(bank, FailureHandling.OPTIONAL)
		MobileDriver.takeScreenshot()

		'Tap Bank'
		MobileDriver.tap(commonPage.lblDynamic(bank), GlobalVariable.G_timeoutMedium, flowControl);

		'Enter User ID'
		MobileDriver.verifyElementVisible(commonPage.lblDynamic('User Id'), timeout)
		MobileDriver.takeScreenshot()
		MobileDriver.tap(commonPage.txtEditNo(1), timeout)
		MobileDriver.setText(commonPage.txtEditNo(1), "1234", timeout)

		'Enter Password'
		MobileDriver.verifyElementVisible(commonPage.lblDynamic('Password'), timeout)
		MobileDriver.tap(commonPage.txtEditNo(2), timeout)
		MobileDriver.setText(commonPage.txtEditNo(2), "1234", timeout)
		MobileDriver.takeScreenshot()

		'Tap on Sign In Button'
		MobileDriver.tap(commonPage.lblDynamic('Sign in'), GlobalVariable.G_timeoutMedium, flowControl)

		'Tap Confirm'
		MobileDriver.verifyElementVisible(commonPage.lblContainDynamic('Confirm'), GlobalVariable.G_timeoutMedium, FailureHandling.OPTIONAL)
		MobileDriver.delay(GlobalVariable.G_timeoutMiniShort)
		MobileDriver.takeScreenshot()
		tapConfirm()

		'Delay'
		MobileDriver.delay(10)
	}
	
	/*
	 * Tap Confirm at FPX
	 */
	void tapConfirm(FailureHandling flowControl = defaultFailureHandling) {
		TestObject termsLabel = commonPage.lblContainDynamic("Confirm")
		'Try tap'
		commonPage.tap(termsLabel, GlobalVariable.G_timeoutMedium, flowControl)
		if (MobileDriver.waitForElementPresent(commonPage.lblContainDynamic("Transaction Status"), GlobalVariable.G_timeoutShort, FailureHandling.OPTIONAL)) {
			return
		}
	
		'Try double tap'
		commonPage.doubleTap(termsLabel, GlobalVariable.G_timeoutMedium)
		if (MobileDriver.waitForElementPresent(commonPage.lblContainDynamic("Transaction Status"), GlobalVariable.G_timeoutShort, FailureHandling.OPTIONAL)) {
			return
		}
		
		'Try tap using specific xpath'
		MobileDriver.tap(btnConfirm(), GlobalVariable.G_timeoutMedium)
		if (MobileDriver.waitForElementPresent(commonPage.lblContainDynamic("Transaction Status"), GlobalVariable.G_timeoutShort, FailureHandling.OPTIONAL)) {
			return
		}
	
		'Try tap and hold'
		Mobile.tapAndHold(termsLabel, 2, GlobalVariable.G_timeoutMiniShort, flowControl)
	}

	/*
	 * Activation Account Success Page
	 */
	List<String> activationPageWithDebitCard = [
		'Activation Successful',
		'Account type',
		'Account number',
		'Debit card fee',
		'Date & time'
		]
		
	List<String> activationPage = [
		'Activation Successful',
		'Account type',
		'Account number',
		'Date & time'
		]

	Map verifyActivationSuccessPage(boolean applyCardNow, int timeout = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		'Verify Page'
		verifyElements(applyCardNow ? activationPageWithDebitCard : activationPage, commonPage.lblContainDynamicPath(), timeout, flowControl)
		MobileDriver.takeScreenshot()
		
		'Get Account Type and Account Number'
		String accountType = MobileDriver.getText(lblNormalizeFollowingSibling("Account type"), GlobalVariable.G_timeoutMedium, flowControl)
		String accountNumber = MobileDriver.getText(lblNormalizeFollowingSibling("Account number"), GlobalVariable.G_timeoutMedium, flowControl)
			
		'Tap Set Up VCB or Done'
		boolean isSetUpMAEVisible = MobileDriver.verifyElementVisible(commonPage.lblDynamic("Set Up VCB"), timeout, FailureHandling.OPTIONAL)
		
		String label = isSetUpMAEVisible ? "Set Up VCB" : "Done"
		
		MobileDriver.waitForElementPresent(commonPage.lblDynamic(label), GlobalVariable.G_timeoutShort, FailureHandling.OPTIONAL)
		MobileDriver.tap(commonPage.lblDynamic(label), timeout, flowControl)
		
		return [
        'label'        : label,
        'accountType'  : accountType,
        'accountNumber': accountNumber
		]
	}

	/*
	 * Verify M2U Successfully Registered
	 * Passport
	 */
	String M2U_REGISTERED = "Your M2U access has been registered"
	
	List<String> m2uAccessRegisteredPage = [
		'You are almost there! Next, you will only need to',
		'Visit branch to activate account',
		'Make an initial deposit'
		]
		
	void verifym2uRegistered(boolean runValidations = false, int timeout = GlobalVariable.G_timeoutLong, FailureHandling flowControl = defaultFailureHandling) {
		'Verification'
		MobileDriver.waitForElementPresent(commonPage.lblDynamic(M2U_REGISTERED), timeout)
		MobileDriver.takeScreenshot()
		if (runValidations) {
			verifyElements(m2uAccessRegisteredPage, commonPage.lblContainDynamicPath(), GlobalVariable.G_timeoutMedium, flowControl)
		}

		'Tap Done'
		commonPage.tapDoneBtn()
	}

	/*
	 * Back to Home and Restart
	 */
	void Homepage(int timeout = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		'Tap Home'
		homePage.tapHomeMenu()
		MobileDriver.delay(2)

		'Restart App'
		openDevOption(commonPage.UIRESTART)
		MobileDriver.delay(2)
	}

	/*
	 * Unhide Balance on Homepage Dashboard
	 */
	static final String UIRMHide = 'RM ****'
	
	public unhideBalance(flowControl) {
		'Verify RM **** is visible'
		if (MobileDriver.verifyElementVisible(commonPage.lblDynamic(UIRMHide), GlobalVariable.G_timeoutShort, FailureHandling.OPTIONAL)) {
			'Select Eye Button'
			MobileDriver.tap(homePage.btnEye(), GlobalVariable.G_timeoutLong)
			MobileDriver.delay(GlobalVariable.G_timeoutMiniShort)
		}
		MobileDriver.takeScreenshot()
	}

	/*
	 * Enter ID Number Method For Login
	 */
	public void enterIdNumber(String idNumber, FailureHandling flowControl = defaultFailureHandling) {
		secure2uPage.enterTxtYourIdNumber(idNumber)
		secure2uPage.tapBtnContinue()
	}
	
	/*
	 * Tap Activate Secure2u For Login
	 */
	String setUp = "Set Up Successful"
	String Secure2U = "Activate Secure2u"
	void tapRegisterSecure2u(FailureHandling flowControl = defaultFailureHandling) {
		if(MobileDriver.waitForElementPresent(commonPage.lblDynamic(setUp), GlobalVariable.G_timeoutMedium, FailureHandling.OPTIONAL)) {
			MobileDriver.takeScreenshot()
			MobileDriver.tap(commonPage.lblDynamic(Secure2U), GlobalVariable.G_timeoutMedium, flowControl)
		}
	}

	/*
	 * Login upon Creating Account
	 */
	public void login(String yourName, String accountName, String idNumber, FailureHandling flowControl = defaultFailureHandling) {
		'Enter Pin'
		pinPage.enterPin()

		'Set Biometric'
		biometricPage.setup()

		'Enter Otp'
		otpPage.enterOtp()

		'Enter Your Name'
		welcomeOnboardPage.enterYourName(yourName, accountName)

		'Activate Secure2u'
		this.tapRegisterSecure2u()

		'Select Register Secure2u Now'
		secure2uPage.tapRegisterSecure2uOrActiveNow()
		MobileDriver.takeScreenshot()

		'Select Request OTP'
		secure2uPage.tapBtnRequestOtp()

		'Enter Otp'
		otpPage.enterOtp()

		'Enter Id number'
		this.enterIdNumber(idNumber)

		'Wait to next Page'
		commonPage.waitLoading()

		'Verify Device name '
		secure2uPage.verifyLblDeviceNameExist()

		'Select next button'
		secure2uPage.tapBtnNextOrContinue()
		MobileDriver.tap(commonPage.lblDynamic("Later"), GlobalVariable.G_timeoutMedium)
		this.unhideBalance()
		MobileDriver.takeScreenshot()
	}

	/*
	 * Excel File
	 */
	int writeDataToExcel(Map<String, Object> data, String filePath, String fileName, String sheetName, Integer rowIndex = null) {
		String sourcePath = RunConfiguration.getProjectDir() + filePath + fileName
		String reportPath = RunConfiguration.getReportFolder() + File.separator + fileName
	
		File sourceFile = new File(sourcePath)
		sourceFile.getParentFile().mkdirs()  // Ensure parent dir exists
	
		XSSFWorkbook workbook
		XSSFSheet sheet
	
		'Open existing file or create new workbook'
		if (sourceFile.exists()) {
			FileInputStream fis = new FileInputStream(sourceFile)
			workbook = new XSSFWorkbook(fis)
			fis.close()
		} else {
			workbook = new XSSFWorkbook()
		}
	
		'Use existing sheet or create one'
		sheet = workbook.getSheet(sheetName)
		if (sheet == null) {
			sheet = workbook.createSheet(sheetName)
		}
	
		'Get or create header row'
		Row headerRow = sheet.getRow(0)
		if (headerRow == null) {
			headerRow = sheet.createRow(0)
			data.keySet().eachWithIndex { key, idx ->
				headerRow.createCell(idx).setCellValue(key)
			}
		}
	
		'Build header index map'
		Map<String, Integer> columnIndexMap = [:]
		int lastCellIndex = headerRow.getLastCellNum() ?: 0
	
		for (int i = 0; i < lastCellIndex; i++) {
			Cell cell = headerRow.getCell(i)
			if (cell) columnIndexMap[cell.getStringCellValue()] = i
		}
	
		'Add any missing headers'
		data.keySet().each { key ->
			if (!columnIndexMap.containsKey(key)) {
				columnIndexMap[key] = lastCellIndex
				headerRow.createCell(lastCellIndex++).setCellValue(key)
			}
		}
	
		'Determine which row to write into'
		int targetRowIndex
		if (rowIndex == null) {
			targetRowIndex = sheet.getLastRowNum() == 0 && sheet.getRow(0) == null ? 1 : sheet.getLastRowNum() + 1
		} else {
			targetRowIndex = rowIndex
		}
	
		'Get or create the target row'
		Row targetRow = sheet.getRow(targetRowIndex)
		if (targetRow == null) {
			targetRow = sheet.createRow(targetRowIndex)
		}
	
		'Write data into the target row'
		data.each { key, value ->
			int colIdx = columnIndexMap[key]
			Cell cell = targetRow.getCell(colIdx)
			if (cell == null) {
				cell = targetRow.createCell(colIdx)
			}
			cell.setCellValue(value?.toString() ?: "")
		}
	
		'Save to source file and copy to report folder'
		FileOutputStream fos = new FileOutputStream(sourceFile)
		try {
			workbook.write(fos)
			File reportFile = new File(reportPath)
			reportFile.getParentFile().mkdirs()
			Files.copy(sourceFile.toPath(), reportFile.toPath(), StandardCopyOption.REPLACE_EXISTING)
			Mobile.comment("✅ Excel updated and copied to report folder: ${fileName}")
		} catch (Exception e) {
			Mobile.comment("❌ Failed to write Excel file: ${e.message}")
		} finally {
			fos.close()
			workbook.close()
		}
		
		'return the row index for next update'
		return targetRowIndex 
	}
	/*-----------------------------------------------------------------  End  -------------------------------------------------------------------------- */
	/*-------------------------------------------------------------  VCB Wallet Flow  -------------------------------------------------------------------*/
	/*
	 * Personal Details VCB Page
	 * NRIC
	 */		
	Map fillMAEPersonalDetails(Map<String,String> personalDetails, boolean runValidations = false, int timeout = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		MobileDriver.delay(GlobalVariable.G_timeoutShort)
		List<String> keyList = getKeyInMap(personalDetails)
		int index;
	
		'Use existing value or generate if no value'
		fullName = personalDetails['Full name as per NRIC']?.trim() ? personalDetails['Full name as per NRIC'] : "AutoUser ${RandomStringUtils.randomAlphabetic(5)}"
		mobileNumber = personalDetails['Mobile Number']?.trim() ? personalDetails['Mobile Number'] : "17000${RandomStringUtils.randomNumeric(4)}"
	
		personalDetails.each { key, value ->
			switch(key) {
				case "Full name as per NRIC":
					commonPage.setText(commonPage.txtSiblingGroup(key), fullName, timeout, flowControl)
					break
				case "Mobile Number":
					commonPage.setText(commonPage.txtSiblingGroup(key), mobileNumber, timeout, flowControl)
					break
				case "Email address":
					commonPage.setText(commonPage.txtSiblingGroup(key), personalDetails.get(key), timeout, flowControl);
					break
				default:
					if(index >= keyList.size()/2) {
						delay(GlobalVariable.G_timeoutMiniShort)
						commonPage.swipe(5, 1500, 5, 100)
					}
					applyAccountPage.selectItemDropDownList(key, value);
			}
		}

		'Tap No To PEP'
		commonPage.swipe(5, 1500, 5, 100)
		MobileDriver.delay(GlobalVariable.G_timeoutMiniShort)
		tapNo()
		
		if (runValidations) {
			'Verify PEP Tooltip'
			verifyPEPTooltip()
		}
		
		return [
			'Full name'     : fullName,
			'Mobile Number' : mobileNumber
		]
	}
	
	/*
	 * Residential Details Page
	 */
	List<String> residentialDetailPage = [
		'Residential Details',
		"We’ve pre-filled your residential address. Kindly edit as you see fit."
		]
			
	void fillMAEResidentialDetails(String idType, Map<String,String> residentialDetails, int timeout = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		if (idType == "MyKad") {
			'Tap Edit'
			MobileDriver.tap(btnSiblingGroup("Residential address", EDIT), GlobalVariable.G_timeoutMedium, flowControl)
			
			'Verify Page'
			verifyElements(residentialDetailPage, commonPage.lblContainDynamicPath(), GlobalVariable.G_timeoutMedium, flowControl)
			MobileDriver.takeScreenshot()
		}
		
		List<String> keyList = getKeyInMap(residentialDetails)
		boolean hasScrolled = false

		residentialDetails.each { key, value ->
			'Scroll before reaching "State/Province" if not already done'
			if (!hasScrolled && key == "State/Province") {
				delay(GlobalVariable.G_timeoutMiniShort)
				commonPage.swipe(5, 1500, 5, 100)
				hasScrolled = true
			}
			switch(key) {
				case "Address line 1":
				case "Address line 2":
				case "Address line 3 ":
				case "Postcode":
				case "City":
					commonPage.setText(commonPage.txtSiblingGroup(key), residentialDetails.get(key), timeout, flowControl);
					break
				case "State/Province":
					MobileDriver.delay(GlobalVariable.G_timeoutMiniShort)
					applyAccountPage.selectItemDropDownList(key, value);
					break
			}
		}

		'Tap Save & Update (NRIC Flow) / Next (Passport Flow)'
		MobileDriver.delay(GlobalVariable.G_timeoutMiniShort)	
		MobileDriver.takeScreenshot()
		
		if (MobileDriver.verifyElementVisible(commonPage.lblContainDynamic("Save & Update"), GlobalVariable.G_timeoutMiniShort, FailureHandling.OPTIONAL)) {
			MobileDriver.tap(commonPage.lblContainDynamic("Save & Update"), timeout, flowControl)
		} else {
			commonPage.tapNextBtn()
		}
	}
	
	/*
	 * Declaration Page
	 */
	List<String> maeDeclarationPage = [
		'I have read and agree to be bound by the Terms and Conditions.',
		"I understand that this account is not protected by PIDM. I have read PIDM's DIS Brochure.",
		'I have read, understood and agree to be bound by the terms of the Vcb Group Privacy Statement and Disclosure Clause.',
		'I am interested in receiving product-related updates and personalised offers from Vcb Group and its partners (as listed in the Disclosure Clause).',
		'Yes, I want to be contacted',
		'No, I do not want to be contacted',
		'I agree to accept this product/service. I understand that the use of electronic messages and electronic acceptance related to this product/service is legally binding, in line with the Electronic Commerce Act 2006.'
		]
		
	void maeDeclarationPage(boolean runValidations = false, String declarationText, int timeout = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		'Verify Page'
		MobileDriver.waitForElementPresent(commonPage.lblContainDynamic("Declaration"), GlobalVariable.G_timeoutMedium, flowControl)
		
		if (runValidations) {
			'Verify Static UI'
			scrollToAndVerifyTextInList(declarationPage, commonPage.lblDynamicPath(), GlobalVariable.G_timeoutMedium, flowControl)
		}
		
		'Tap on Declaration Text'
		MobileDriver.tap(commonPage.lblDynamic(declarationText), timeout, flowControl);
		MobileDriver.takeScreenshot()
		
		'Tap Agree And Confirm'
		MobileDriver.tap(commonPage.lblDynamic(applyAccountPage.AGREE_AND_CONFIRM), GlobalVariable.G_timeoutMedium, flowControl)
	}
	
	/*
	 * App Pin Setup
	 */
	public void pinSetupForMAE(String idNumber, FailureHandling flowControl = defaultFailureHandling) {
		'Enter Pin'
		pinPage.enterPin()

		'Set Biometric'
		if(Mobile.waitForElementPresent(biometricPage.btnSetUpLater(), GlobalVariable.G_timeoutShort)) {
			MobileDriver.tap(biometricPage.btnSetUpLater(), GlobalVariable.G_timeoutLong, flowControl)
		}

		'Select Register Secure2u Now'
		secure2uPage.tapRegisterSecure2uOrActiveNow()
		MobileDriver.takeScreenshot()

		'Select Request OTP'
		secure2uPage.tapBtnRequestOtp()

		'Enter Otp'
		otpPage.enterOtp()

		'Enter Id number'
		this.enterIdNumber(idNumber)

		'Wait to next Page'
		commonPage.waitLoading()

		'Verify Device name '
		secure2uPage.verifyLblDeviceNameExist()

		'Select next button'
		secure2uPage.tapBtnNextOrContinue()
		MobileDriver.tap(commonPage.lblDynamic("Later"), GlobalVariable.G_timeoutMedium)
		this.unhideBalance()
		MobileDriver.takeScreenshot()
	}
	
	/*
	 * Login VCB
	 */
	public void loginMAE(String username, String password, String yourName, String accountName) {
		'Navigate To Login'
		onboardingPage.navigateToLogin()
		homePage.clickMaybeLater()
		
		'Login to the application'
		loginPage.signin(username, password)
		
		'Enter Pin'
		pinPage.enterPin()
		
		'Set Biometric'
		biometricPage.setup()
		
		'Enter Otp'
		otpPage.enterOtp()
		
		'Enter Your Name'
		welcomeOnboardPage.enterYourName(yourName, accountName)
	}
	
	/*
	 * Enter Passport Number and Other Informations For VCB Wallet
	 */	
	Map fillMAEPassportInformation(Map<String, String> passportDetails, boolean runValidations = false, int timeout = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		List<String> keyList = getKeyInMap(passportDetails)
		
		String passportNumber = ''
		String passportExpDate = ''
		String dateOfBirth = ''
		
		passportDetails.each { key, value ->
			switch (key) {

				case "Passport Number":
					passportNumber = enterPassportNumber(passportDetails.get(key), runValidations)
					break
	
				case "Passport expiry date":
					MobileDriver.tap(applyAccountPage.txtSiblingGroup(PASSPORT_EXD), timeout, flowControl)
					chooseExpiryDatePassport(passportDetails)
					MobileDriver.delay(GlobalVariable.G_timeoutMiniShort)
					commonPage.swipe(5, 1500, 5, 100)
					MobileDriver.delay(GlobalVariable.G_timeoutMiniShort)
					passportExpDate = MobileDriver.getText(applyAccountPage.txtSiblingGroup(PASSPORT_EXD), GlobalVariable.G_timeoutMedium, flowControl)
					break
	
				case "Date of Birth":
					MobileDriver.tap(applyAccountPage.txtSiblingGroup(DATE_OF_BIRTH), timeout, flowControl)
					chooseBirthdate(passportDetails)
					MobileDriver.delay(GlobalVariable.G_timeoutMiniShort)
					dateOfBirth = MobileDriver.getText(applyAccountPage.txtSiblingGroup(DATE_OF_BIRTH), GlobalVariable.G_timeoutMedium, flowControl)
					break
	
				case "Nationality":
					'Select Nationality'
					applyAccountPage.selectItemDropDownList(key, value);
					break
			}
		}
		return [
			"Passport Number"     : passportNumber,
			"Passport Expiry Date": passportExpDate,
			"Date of Birth"		  : dateOfBirth
		]
	}

	/*
	 * Selects ID Type and Enter Passport Number For VCB Wallet
	 */
	Map chooseIdTypeandPassportMAE(String idType, Map<String,String> passportDetails,  boolean runValidations = false, int timeout = GlobalVariable.G_timeoutMedium,  FailureHandling flowControl = defaultFailureHandling) {
		'Select ID Type'
		chooseIdType(idType)

		'Enter Passport Number and Other Informations'
		Map passportInfo = fillMAEPassportInformation(passportDetails, runValidations)
			
		'Tap Next'
		MobileDriver.takeScreenshot()
		commonPage.tapNextBtn()
		
		return passportInfo
	}
	
	/*
	 * Personal Details Page For VCB Wallet
	 * Passport
	 */
	Map fillPersonalDetailsPassportMAE(Map<String,String> personalDetails, int timeout = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		List<String> keyList = getKeyInMap(personalDetails)
		int index = 0
	
		'Use existing value or generate if no value'
		fullName = personalDetails['Full name']?.trim() ? personalDetails['Full name'] : "AutoUser ${RandomStringUtils.randomAlphabetic(5)}"
		mobileNumber = personalDetails['Mobile Number']?.trim() ? personalDetails['Mobile Number'] : "17000${RandomStringUtils.randomNumeric(4)}"
		
		personalDetails.each { key, value ->
			switch(key) {
				case "Full name":
					commonPage.setText(commonPage.txtSiblingGroup(key), fullName, timeout, flowControl)
					break
				
				case "Mobile Number":
					commonPage.setText(commonPage.txtSiblingGroup(key), mobileNumber, timeout, flowControl)
					break
	
				case "Email address":
					commonPage.setText(commonPage.txtSiblingGroup(key), personalDetails.get(key), timeout, flowControl)
					break
	
				case "Gender":
					MobileDriver.doubleTap(commonPage.grpPrecedingSibling(value), timeout,  flowControl)
					break
	
				default:
					if(index >= keyList.size()/2) {
						delay(GlobalVariable.G_timeoutMiniShort)
						commonPage.swipe(5, 1500, 5, 100)
					}
					applyAccountPage.selectItemDropDownList(key, value);
			}
			index++
		}
	
		'Tap No To PEP'
		commonPage.swipe(5, 1500, 5, 100)
		MobileDriver.delay(GlobalVariable.G_timeoutMiniShort)
		tapNo()
	
		'Tap Next'
		MobileDriver.takeScreenshot()
		commonPage.tapNextBtn()

		return [
			'Full name'     	: fullName,
			'Mobile Number'     : mobileNumber,
		]
	}
	/*-----------------------------------------------------------------  End  -------------------------------------------------------------------------- */
	/*---------------------------------------------------- Additional Methods For MyKad/NRIC ------------------------------------------------------------*/
	/*
	 * Choose Id Type (MyKad or Passport)
	 */
	void chooseIdType(String idType, int timeout = GlobalVariable.G_timeoutMedium,  FailureHandling flowControl = defaultFailureHandling) {
		if(waitForElementPresent(commonPage.grpPrecedingSibling(idType), GlobalVariable.G_timeoutMedium, flowControl)) {
			MobileDriver.tap(commonPage.grpPrecedingSibling(idType), GlobalVariable.G_timeoutMedium,  flowControl)
		} else {
			MobileDriver.tap(commonPage.lblDynamic(idType), GlobalVariable.G_timeoutMedium, flowControl)
		}
	}
	
	/*
	 * Enter MyKad
	 */
	String MYKAD_NUMBER = "MyKad number"
	
	List<String> idTypePage = [
		'Tell us your ID type and number',
		'Select your ID type'
		]
		
	String enterMykadNumber(String myKadNumber, boolean runValidations = false, int timeout = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		if (runValidations) {
			verifyElements(idTypePage, commonPage.lblContainDynamicPath(), GlobalVariable.G_timeoutMedium, flowControl)
		}
		
		'Generate or use provided MyKad Number'
		if (!myKadNumber?.trim()) {
			'Generate MyKad Number'
			myKadNumber = stringUtils.generateRandomValidNRIC()
		}
		
		'Enter MyKad Number'
		commonPage.setText(commonPage.txtSiblingGroup(MYKAD_NUMBER), myKadNumber, timeout, flowControl)
		MobileDriver.takeScreenshot()
	
		return myKadNumber
	}

	/*
	 * Select Dropdown Method
	 */
	String UIDonebtn = 'Done'
	
	void selectItemDropDownList(String fieldName, String value, int timeout = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		commonPage.swipeToTestObject(commonPage.imgArrowDownDropdown(fieldName, 1), [], false, timeout, flowControl)
	
		'If no value'
		if (value.isBlank() || value == "" || value == "defaultValue") {
			Mobile.verifyElementExist(commonPage.imgSiblingGroup(fieldName), timeout, flowControl)
			info("Tap on drop-down ${fieldName}")
			Mobile.tap(commonPage.imgSiblingGroup(fieldName), timeout, flowControl)
			
			if (!MobileDriver.waitForElementPresent(commonPage.lblDynamic(UIDonebtn), GlobalVariable.G_timeoutShort, flowControl)) {
				info("Tap on drop-down again ${fieldName}")
				Mobile.tap(commonPage.imgSiblingGroup(fieldName), timeout, FailureHandling.OPTIONAL)
			}
	
			info("Tap Done to close drop-down")
			MobileDriver.tap(commonPage.lblDynamic(UIDonebtn), timeout, flowControl)
			return
		}
	
		'If there is value'
		Mobile.verifyElementExist(commonPage.imgSiblingGroup(fieldName), timeout, FailureHandling.CONTINUE_ON_FAILURE)
		info("Tap on drop-down ${fieldName}")
		Mobile.tap(commonPage.imgSiblingGroup(fieldName), timeout, flowControl)
	
		if (!MobileDriver.waitForElementPresent(commonPage.lblDynamic(UIDonebtn), GlobalVariable.G_timeoutShort, flowControl)) {
			info("Tap on drop-down again ${fieldName}")
			Mobile.tap(commonPage.imgSiblingGroup(fieldName), timeout, FailureHandling.OPTIONAL)
		}
	
		info("Search for ${value}")
		if (!MobileDriver.waitForElementPresent(commonPage.lblNormalizeSpace(value), timeout, FailureHandling.OPTIONAL)) {
			info("Scroll to ${value}")
			Mobile.scrollToText(value, FailureHandling.OPTIONAL)
		}
	
		'Retry scroll in loop if value not found yet'
		int maxScrollAttempts = 10
		int attempt = 0
		while (!MobileDriver.waitForElementPresent(commonPage.lblNormalizeSpace(value), timeout, FailureHandling.OPTIONAL) && attempt < maxScrollAttempts) {
			info("Scrolling to find ${value} (Attempt ${attempt + 1})")
			Mobile.scrollToText(value, FailureHandling.OPTIONAL)
			attempt++
		}
	
		if (attempt == maxScrollAttempts) {
			info("Max scroll attempts reached. ${value} might not be in the list.")
		}
	
		info("Tap on ${value}")
		Mobile.waitForElementPresent(commonPage.lblNormalizeSpace(value), timeout, FailureHandling.OPTIONAL)
		Mobile.delay(1)
		Mobile.tap(commonPage.lblNormalizeSpace(value), timeout, flowControl)
	
		info("Tap Done to close drop-down")
		Mobile.tap(commonPage.lblDynamic(UIDonebtn), GlobalVariable.G_timeoutMedium, FailureHandling.OPTIONAL)
	
		'Final verification after tapping Done'
		boolean valueVisible = MobileDriver.waitForElementPresent(commonPage.lblNormalizeSpace(value), timeout, FailureHandling.OPTIONAL)
	
		if (!valueVisible) {
			info("Selected value '${value}' not visible after tapping Done. Re-opening dropdown to scroll one line.")
			Mobile.tap(commonPage.imgSiblingGroup(fieldName), timeout, FailureHandling.OPTIONAL)
			commonPage.selectDropdownScrollOneLine(value, GlobalVariable.G_timeoutLong, flowControl)
	
			if (Mobile.waitForElementPresent(commonPage.lblDynamic(UIDonebtn), GlobalVariable.G_timeoutShort, FailureHandling.OPTIONAL)) {
				info("Tap Done again after scroll")
				Mobile.tap(commonPage.lblDynamic(UIDonebtn), GlobalVariable.G_timeoutLong, FailureHandling.OPTIONAL)
			}
	
			valueVisible = MobileDriver.waitForElementPresent(commonPage.lblNormalizeSpace(value), timeout, FailureHandling.OPTIONAL)
			if (!valueVisible) {
				KeywordUtil.markWarning("Selected value '${value}' is still not visible after scroll fallback.")
			} else {
				info("Confirmed value '${value}' is visible after scroll fallback.")
			}
		} else {
			info("Confirmed selected value '${value}' is visible after first Done tap.")
		}
	}
	
	/*
	 * Tap Confirm Button
	 */
	void tapConfirmBtn(FailureHandling flowControl = defaultFailureHandling) {
		MobileDriver.tap(commonPage.lblDynamic("Confirm"), GlobalVariable.G_timeoutShort, flowControl)
	}

	/*
	 * Tap No
	 */
	static final String NO = 'No'
	
	void tapNo(FailureHandling flowControl = defaultFailureHandling) {
		MobileDriver.doubleTap(commonPage.grpPrecedingSibling(NO), GlobalVariable.G_timeoutMedium, flowControl)
		MobileDriver.delay(GlobalVariable.G_timeoutMiniShort)
	}
	
	/*
	 * Verify PEP ToolTip
	 */
	List<String> pepToolTip = [
		'What is PEP?',
		'Individuals with a high-profile political role or entrusted with a prominent public function in Malaysia, or anywhere in the world. E.g. Prime Minister, Deputy Minister, Ambassador etc.',
		'What is RCA?',
		'Immediate family members, next-of-kin, close friend, business associate of a PEP. E.g. parents, siblings, spouses, children, In-Law, Bodyguard, Political Secretary, Business Partner, Personal Advisor etc.'
		]
		
	void verifyPEPTooltip(int index = 4, FailureHandling flowControl = defaultFailureHandling) {
		'Tap on tool tip (i)'
		MobileDriver.tap(commonPage.imgNoDynamic(index), GlobalVariable.G_timeoutMedium, flowControl)
	
		'Verify PEP Content'
		verifyElements(pepToolTip, commonPage.lblContainDynamicPath(), GlobalVariable.G_timeoutMedium, flowControl)
		MobileDriver.takeScreenshot()
	
		'Back'
		MobileDriver.tap(commonPage.imgNoDynamic(1), GlobalVariable.G_timeoutMedium, flowControl)
		MobileDriver.delay(GlobalVariable.G_timeoutMiniShort)
	}

	/*
	 * Verify Values Method 
	 */
	void verifyValues(Map<String, String> detailsMap, List<String> keysToCheck) {
		keysToCheck.each { key ->
			'Find the matching key in the map (ignoring case)'
			String matchedKey = detailsMap.keySet().find { it.equalsIgnoreCase(key) }
	
			if (!matchedKey) {
				println "⚠️ Skipped: '${key}' not available in map"
				return
			}
	
			String expectedValue = detailsMap[matchedKey]
	
			'🔹 Add "+60" to the Mobile Number before checking'
			if (matchedKey.equalsIgnoreCase("Mobile number")) {
				expectedValue = "+60" + expectedValue
			}
	
			'Check if element exists'
			if (MobileDriver.verifyElementExist(lblFollowingSibling(key), GlobalVariable.G_timeoutMedium)) {
				String actualValue = Mobile.getText(lblFollowingSibling(key), GlobalVariable.G_timeoutMedium).trim()
	
				if (actualValue.equalsIgnoreCase(expectedValue.trim())) {
					println "✅ Verified: '${key}' -> Expected '${expectedValue}', Found '${actualValue}'"
				} else {
					KeywordUtil.markFailed("❌ MISMATCH: '${key}' -> Expected '${expectedValue}', Found '${actualValue}'")
				}
			} else {
				KeywordUtil.markFailed("❌ ELEMENT NOT FOUND: '${key}' on the confirmation screen")
			}
		}
	}

	/*
	 * Verify Personal Details at Confirmation Page
	 */	
	void verifyPersonalDetails(String idType, Map<String, String> personalDetails) {
		List<String> personalKeys = []
	
		switch (idType?.toLowerCase()) {
			case 'MyKad':
				personalKeys = [
					"Full name",
					"Race",
					"Mobile number",
					"Email address"
				]
				break
	
			case 'Passport':
				personalKeys = [
					"Title",
					"Gender",
					"Mobile number",
					"Email address"
				]
				break
		}
	
		'Use class-level details if missing'
		if (!personalDetails['Full name']?.trim() && fullName?.trim()) {
			personalDetails['Full name'] = fullName
		}
	
		if (!personalDetails['Mobile number']?.trim() && mobileNumber?.trim()) {
			personalDetails['Mobile number'] = mobileNumber
		}
	
		'Verify'
		verifyValues(personalDetails, personalKeys)
	}

	/*
	 * Verify Debit Card Delivery Address at Confirmation Page
	 */
	void verifyDeliveryAddressDetails(Map<String, String> residentialDetails) {
		List<String> residentialKeys = [
			"Address line 1",
			"Address line 2",
			"Postcode"
		]
		
		'Scroll to end'
		commonPage.scrollToEnd()
		if(MobileDriver.waitForElementPresent(commonPage.lblDynamic('Debit Card Delivery Address'), GlobalVariable.G_timeoutShort, FailureHandling.OPTIONAL)) {
			
			'Verify'
			verifyValues(residentialDetails, residentialKeys)
		}
	}

	/*
	 * Verify Employment Details at Confirmation Page
	 */
	void verifyEmploymentDetails(Map<String, String> employmentDetails) {
		List<String> employmentKeys = [
			"Employer name",
			"Occupation",
			"Sector",
			"Employment type",
			"Monthly income",
			"Source of income"
		]
		
		'Verify'
		verifyValues(employmentDetails, employmentKeys)
	}
	
	/*
	 * Click on Request OTP 
	 */
	void clickRequestOTP(int timeout = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		waitForElementPresent(commonPage.lblDynamic(REQUEST_OTP), timeout, flowControl)
		MobileDriver.tap(commonPage.btnDynamic(REQUEST_OTP), timeout, flowControl)
	}

	/*
	 * Click on Maybe Later
	 */
	void clickMaybeLater(int timeout = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		MobileDriver.verifyElementVisible(commonPage.lblDynamic('Maybe Later'), timeout)
		MobileDriver.tap(commonPage.btnDynamic('Maybe Later'), timeout, flowControl)
	}

	/*
	 * Click on Back To Home
	 */
	void clickBackToHome(int timeout = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		MobileDriver.verifyElementVisible(commonPage.lblDynamic('Back to Home'), timeout)
		MobileDriver.takeScreenshot()
		MobileDriver.tap(commonPage.btnDynamic('Back to Home'), timeout, flowControl)
		MobileDriver.takeScreenshot()
	}
	
	/*
	 * PBA/PBAi Page
	 */
	List<String> pbaPage = [
		'Unlock the exclusive privileges of Private Banking Account (PBA)',
		'Thank you for your interest in Private Banking Account!',
		'Before applying for PBA, you must:',
		'Open a  Premier 1 Account. This account offers valuable benefits and is a stepping stone to PBA.',
		'Our dedicated Relationship Manager will contact you once you have opened a Premier 1 account to discuss your eligibility for upgrading to PBA.',
		'Apply For Premier 1 Account'
	]
		
	List<String> pbaiPage = [
		'Unlock the exclusive privileges of Private Banking Account-i (PBA-i)',
		'Thank you for your interest in Private Banking Account-i!',
		'Before applying for PBA-i, you must:',
		'Open a  Premier Mudharabah Account-i (PMA-i). This account offers valuable benefits and is a stepping stone to PBA-i.',
		'Our dedicated Relationship Manager will contact you once you have opened a PMA-i to discuss your eligibility for upgrading to PBA-i.',
		'Apply for PMA-i Account'
	]
		
	void verifyPage(String account, boolean runValidations = false, int timeout = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		'Delay'
		MobileDriver.delay(GlobalVariable.G_timeoutMiniShort)
		MobileDriver.takeScreenshot()
		
		switch (account) {
			case "Private Banking Account":
				if (runValidations) {
					'Verify PBA intro page'
					verifyElements(pbaPage, commonPage.lblContainDynamicPath(), GlobalVariable.G_timeoutMedium, flowControl)
				}
				
				'Tap Apply For Premier 1 Account'
				MobileDriver.tap(commonPage.lblDynamic("Apply For Premier 1 Account"), GlobalVariable.G_timeoutMedium, flowControl)
				break
				
			case "Private Banking Account-i":
				if (runValidations) {
					'Verify PBAi intro page'
					verifyElements(pbaiPage, commonPage.lblContainDynamicPath(), GlobalVariable.G_timeoutMedium, flowControl)
				}
				
				'Tap Apply for PMA-i Account'
				MobileDriver.tap(commonPage.lblDynamic("Apply for PMA-i Account"), GlobalVariable.G_timeoutMedium, flowControl)
				break
		}
	}
	
	/*
	 * Open DevOption
	 */
	private boolean openDevOption(String name) {
		TouchActionUtils.tapTwoFingerW3CAction()
		try {
			'Not use wait ele slow script'
			MobileDriver.tap(commonPage.lblDynamic(name), GlobalVariable.G_timeoutLong, FailureHandling.STOP_ON_FAILURE) //not change flowControl
		}catch(ex) {
			if(MobileDriver.waitForElementNotPresent(commonPage.lblDynamic(commonPage.DEV_OPTIONS), GlobalVariable.G_timeoutMedium, FailureHandling.OPTIONAL)) {
				'Close Popup after open devoption'
				if(MobileDriver.waitForElementPresent(commonPage.lblDynamic(commonPage.UICancel), GlobalVariable.G_timeoutMedium, FailureHandling.OPTIONAL)) {
					MobileDriver.tap(commonPage.lblDynamic(commonPage.UICancel), GlobalVariable.G_timeoutMedium, FailureHandling.OPTIONAL)
				}else {
					MobileDriver.tapAndLog(commonPage.imgNoDynamic(1), GlobalVariable.G_timeoutMedium, 'Icon Back' ,FailureHandling.OPTIONAL)
				}
				TouchActionUtils.tapTwoFingerW3CAction()
			}
			if(MobileDriver.waitForElementPresent(commonPage.lblDynamic(commonPage.DEV_OPTIONS), GlobalVariable.G_timeoutLong, FailureHandling.OPTIONAL)) {
				MobileDriver.tap(commonPage.lblDynamic(name), GlobalVariable.G_timeoutLong, FailureHandling.STOP_ON_FAILURE)
				return true
			}
			return false
		}
		return true
	}

	/*
	 * Fill in Security Questions Method
	 */
	void SecurityQuestions(Map<String, String> securityQuestions, int timeout = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		List<String> questionKeys = [
			"Security question 1",
			"Security question 2",
			"Security question 3"
		]
	
		List<String> answerKeys = [
			"Your Answer 1",
			"Your Answer 2",
			"Your Answer 3"
		]
	
		for (int i = 0; i < questionKeys.size(); i++) {
			String question = securityQuestions[questionKeys[i]] ?: "defaultValue"
			String answer = securityQuestions[answerKeys[i]]
	
			'Select Dropdown'
			selectItemDropDownList(questionKeys[i], question, timeout, flowControl)
	
			'Set Text'
			commonPage.setText(txtFieldWithIndex((i + 1).toString()), securityQuestions[answerKeys[i]], timeout, flowControl)
		}
	}
	
	/*
	 * Verify Apply Account
	 */
	String APPLY_PMAi = 'Apply for PMA-i Account'
	String APPLY_PM1 = 'Apply For Premier 1 Account'
	
	boolean verifyApplyAccount(String accountLabel, int timeout = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		if (MobileDriver.verifyElementVisible(commonPage.lblDynamic(accountLabel), timeout, FailureHandling.OPTIONAL)) {
			'Tap the account option'
			MobileDriver.takeScreenshot()
			MobileDriver.tap(commonPage.lblDynamic(accountLabel), timeout, flowControl)
	
			'Wait for "Apply Now" to appear'
			if (MobileDriver.verifyElementVisible(commonPage.lblDynamic("Apply Now"), timeout, FailureHandling.OPTIONAL)) {
				MobileDriver.takeScreenshot()
				applyAccountPage.tapApplyNow()
			}
			return true
		}
		return false
	}
	
	/*-----------------------------------------------------------------  End  -------------------------------------------------------------------------- */
	
	/*----------------------------------------------------- Additional Methods For Passport -------------------------------------------------------------*/
	/*
	 * Enter Passport Number
	 */
	String PASSPORT_NUMBER = "Passport Number"
	
	String enterPassportNumber(String passportNumber, boolean runValidations = false, int timeout = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		if (runValidations) {
			'Run validation scenarios for Passport Number'
			verifyElements(idTypePage, commonPage.lblContainDynamicPath(), GlobalVariable.G_timeoutMedium, flowControl)
		}
	
		'Generate or use provided Passport Number'
		if (!passportNumber?.trim()) {
			'Generate Passport Number'
			passportNumber = stringUtils.generateRandomPassport(8)
		}
	
		'Enter Passport Number'
		commonPage.setText(commonPage.txtSiblingGroup(PASSPORT_NUMBER), passportNumber, timeout, flowControl)
		MobileDriver.takeScreenshot()
	
		return passportNumber
	}
	
	/*
	 * Enter Passport Number and Other Informations
	 */
	String PASSPORT_EXD = "Passport expiry date"
	String DATE_OF_BIRTH = "Date of Birth"
	
	Map fillPassportInformation(Map<String, String> passportDetails, boolean runValidations = false, int timeout = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		List<String> keyList = getKeyInMap(passportDetails)
		
		fullName = passportDetails['Full name']?.trim() ? passportDetails['Full name'] : "AutoUser ${RandomStringUtils.randomAlphabetic(5)}"
		String passportNumber = ''
		String passportExpDate = ''
		String dateOfBirth = ''
		
		passportDetails.each { key, value ->
			switch (key) {
	
				case "Full name":
					commonPage.setText(commonPage.txtSiblingGroup(key), fullName, timeout, flowControl)
					break
	
				case "Passport Number":
					passportNumber = enterPassportNumber(passportDetails.get(key), runValidations)
					break
	
				case "Passport expiry date":
					MobileDriver.tap(applyAccountPage.txtSiblingGroup(PASSPORT_EXD), timeout, flowControl)
					chooseExpiryDatePassport(passportDetails)
					MobileDriver.delay(GlobalVariable.G_timeoutMiniShort)
					commonPage.swipe(5, 1500, 5, 100)
					MobileDriver.delay(GlobalVariable.G_timeoutMiniShort)
					passportExpDate = MobileDriver.getText(applyAccountPage.txtSiblingGroup(PASSPORT_EXD), GlobalVariable.G_timeoutMedium, flowControl)
					break
	
				case "Date of Birth":
					MobileDriver.tap(applyAccountPage.txtSiblingGroup(DATE_OF_BIRTH), timeout, flowControl)
					chooseBirthdate(passportDetails)
					MobileDriver.delay(GlobalVariable.G_timeoutMiniShort)
					dateOfBirth = MobileDriver.getText(applyAccountPage.txtSiblingGroup(DATE_OF_BIRTH), GlobalVariable.G_timeoutMedium, flowControl)
					break
	
				case "Nationality":
					'Select Nationality'
					applyAccountPage.selectItemDropDownList(key, value);
					break
			}
		}
		return [
			'Full name'     	  : fullName,
			"Passport Number"     : passportNumber,
			"Passport Expiry Date": passportExpDate,
			"Date of Birth"		  : dateOfBirth
		]
	}

	/*
	 * Select Year by 1 Year Method
	 */
	void chooseAfterYear(int timeout = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		int currentYear = LocalDate.now().getYear();
		MobileDriver.tap(commonPage.lblDynamic(currentYear.toString()), timeout, flowControl)
		MobileDriver.tap(commonPage.lblDynamic((currentYear + 1).toString()), timeout, flowControl)
		MobileDriver.tap(commonPage.lblDynamic(commonPage.UIDonebtn), timeout, flowControl)
	}
	
	/*
	 * Passport Expiry Date
	 */
	void chooseExpiryDatePassport(Map<String, String> passportDetails, int timeout = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		'Get expiry date from the map'
		String expiryDate = passportDetails.get('Passport expiry date')?.trim()
	
		if (expiryDate) {
			'Convert "07 APR 2027" → "07 Apr 2027" to match formatter expectations'
			expiryDate = expiryDate.toLowerCase().split(" ").collect { it.capitalize() }.join(" ")
	
			'Formatter for "dd MMM yyyy"'
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy", Locale.ENGLISH)
			LocalDate parsedDate = LocalDate.parse(expiryDate, formatter)
	
			'Extract values to tap in UI'
			String year = parsedDate.getYear().toString()
			String month = parsedDate.getMonth().getDisplayName(TextStyle.SHORT, Locale.ENGLISH).toUpperCase()
			String day = parsedDate.getDayOfMonth().toString()
	
			'Select Year'
			String currentYear = LocalDate.now().getYear().toString()
			MobileDriver.tap(commonPage.lblDynamic(currentYear), timeout, flowControl)
			MobileDriver.tap(commonPage.lblDynamic(year), timeout, flowControl)
	
			'Select Month'
			String currentMonth = LocalDate.now().getMonth().getDisplayName(TextStyle.SHORT, Locale.ENGLISH).toUpperCase()
			MobileDriver.tap(commonPage.lblDynamic(currentMonth), timeout, flowControl)
			MobileDriver.tap(commonPage.lblDynamic(month), timeout, flowControl)
	
			'Select Day'
			MobileDriver.tap(commonPage.lblDynamic(day), timeout, flowControl)
	
			'Tap done button'
			MobileDriver.tap(commonPage.lblDynamic(commonPage.UIDonebtn), timeout, flowControl)
		} else {
			'If expiry date is missing → select next year automatically'
			chooseAfterYear(timeout, flowControl)
		}
	}

	/*
	 * Date of Birth
	 */
	void chooseBirthdate(Map<String, String> passportDetails, int timeout = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = FailureHandling.STOP_ON_FAILURE) {
		String birthDate = passportDetails.get('Date of Birth')?.trim()
	
		if (!birthDate) {
			'If Date of Birth is missing → tap field and Done'
			MobileDriver.tap(applyAccountPage.txtSiblingGroup(DATE_OF_BIRTH), timeout, flowControl)
			commonPage.tapDoneBtn()
			return
		}
	
		'Convert and normalize date format (e.g., "07 APR 2027" → "07 Apr 2027")'
		birthDate = birthDate.toLowerCase().split(" ").collect { it.capitalize() }.join(" ")
	
		'Parse and extract date components'
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy", Locale.ENGLISH)
		LocalDate parsedDate = LocalDate.parse(birthDate, formatter)
	
		String year = parsedDate.getYear().toString()
		String month = parsedDate.getMonth().getDisplayName(TextStyle.SHORT, Locale.ENGLISH).toUpperCase()
		String day = parsedDate.getDayOfMonth().toString()
	
		'Tap the displayed year in the picker'
		AndroidDriver driver = MobileDriverFactory.getDriver()
		List<WebElement> yearElements = driver.findElements(By.xpath("//*[starts-with(@text, '20')]"))
		if (!yearElements.isEmpty()) {
			yearElements[0].click()
		} else {
			KeywordUtil.markFailed("No year element starting with '20' was found.")
		}
	
		'Tap the arrow (if needed)'
		int retries = 2
		boolean clicked = false
	
		for (int i = 0; i <= retries; i++) {
			List<WebElement> arrows = driver.findElements(By.xpath("//*[@class='android.widget.ImageView' and (@text='' or .='')]"))
			if (arrows.size() > 4) {
				arrows[4].click()
				clicked = true
				break
			}
		}
		if (!clicked) {
			KeywordUtil.markFailed("Bottom arrow not found after 3 attempts.")
		}
	
		'Tap birth year, month, and day'
		MobileDriver.tap(commonPage.lblDynamic(year), timeout, flowControl)
	
		String currentMonth = LocalDate.now().getMonth().getDisplayName(TextStyle.SHORT, Locale.ENGLISH).toUpperCase()
		MobileDriver.tap(commonPage.lblDynamic(currentMonth), timeout, flowControl)
		MobileDriver.tap(commonPage.lblDynamic(month), timeout, flowControl)
		MobileDriver.tap(commonPage.lblDynamic(day), timeout, flowControl)
	
		'Done'
		MobileDriver.tap(commonPage.lblDynamic(commonPage.UIDonebtn), timeout, flowControl)
	}
}