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
import com.vcb.mobile.internal.pages.base.BasePage
import com.vcb.mobile.internal.pages.base.CommonPage
import com.vcb.mobile.internal.pages.base.CommonPage.DropDownTypeEnum
import com.vcb.mobile.internal.pages.base.StatusScreenPage
import com.vcb.mobile.internal.pages.base.applypage.ApplyAccountPage as ApplyAccountPage
import com.vcb.mobile.internal.pages.base.applypage.ApplyCASAPage
import com.vcb.utils.StringUtils
import org.apache.commons.lang.RandomStringUtils

import internal.GlobalVariable

public class ApplyCardPage extends BasePage {

	CommonPage commonPage = getPage(CommonPage)
	StatusScreenPage statusScreenPage = getPage(StatusScreenPage)
	ApplyAccountPage applyAccountPage = BasePage.getPage(ApplyAccountPage)
	ApplyCASAPage applyCASAPage = BasePage.getPage(ApplyCASAPage)
	StringUtils stringUtils = new StringUtils()

	static final String NEXT = 'NEXT'
	static final String PROCEED = 'Proceed'
	static final String AGREE_AND_CONFIRM = 'Agree and Confirm'
	static final String UPLOAD_LATER = 'Upload Later'
	static final String TERMS_CONDITIONS = 'Terms & Conditions'
	static final String UPLOAD_DOCUMENTS = 'Upload Documents'
	static final String APPROVE = 'Approve'
	static final String REJECT = 'Reject'
	static final String EXPIRED = 'Expired'
	static final String UIReferenceID = 'Reference ID'


	/*
	 * Select Card Type base on cardName
	 */
	void selectCardType(String cardName, FailureHandling flowControl = defaultFailureHandling) {
		commonPage.scrollToTestObject(commonPage.frmHorizontalScrollNo(2), commonPage.lblNormalizeSpace(cardName), ScrollDirectionEnum.DOWN, GlobalVariable.G_timeoutShort, flowControl)
		Mobile.delay(GlobalVariable.G_timeoutMiniShort)
		MobileDriver.tap(commonPage.lblNormalizeSpace(cardName), GlobalVariable.G_timeoutMedium, flowControl)
		MobileDriver.takeScreenshot()
	}

	/* --------------------------------------------------- Credit Card -----------------------------------------------*/
	/**ETB Flow**/
	
	/*
	 * Verify UI Credit Card Application Step One Screen
	 */
	void verifyCreditCardApplicationStepOne(List creditCardApplicationData, FailureHandling flowControl = defaultFailureHandling) {
		verifyElements(creditCardApplicationData, commonPage.lblDynamicPath(), GlobalVariable.G_timeoutMedium, flowControl)

		MobileDriver.tap(commonPage.lblDynamic(NEXT), GlobalVariable.G_timeoutMedium, flowControl)
		commonPage.waitLoading()
	}

	/*
	 * Verify UI Credit Card Application Step Two Screen
	 */
	void verifyCreditCardApplicationStepTwo(List creditCardApplicationData, String cardTypeName, FailureHandling flowControl = defaultFailureHandling) {
		verifyElements(creditCardApplicationData, commonPage.lblDynamicPath(), GlobalVariable.G_timeoutMedium, flowControl)

		MobileDriver.tap(commonPage.lblContainDynamic(cardTypeName), GlobalVariable.G_timeoutMedium, flowControl)
	}

	/*
	 * Verify Get Started Screen
	 */
	void verifyGetStarted(List getStartedData, FailureHandling flowControl = defaultFailureHandling) {
		verifyElements(getStartedData, commonPage.lblDynamicPath(), GlobalVariable.G_timeoutMedium, flowControl)

		MobileDriver.tap(commonPage.lblDynamic(PROCEED), GlobalVariable.G_timeoutMedium, flowControl)
	}

	/*
	 * fill Details Step One
	 */
	void fillDetailsStepOne(Map drpStepOneData, FailureHandling flowControl = defaultFailureHandling) {
		'Input Dropdown Step One'
		commonPage.inputDropdownMapDefault(drpStepOneData, flowControl)
		
		'Tap Continue'
		commonPage.tapContinueBtn(flowControl)
		commonPage.waitLoading()
	}

	/*
	 * fill Details Step Two
	 */
	void fillDetailsStepTwo(Map drpStepTwoData, Map inputStepTwoData, FailureHandling flowControl = defaultFailureHandling) {
		'Input Dropdown Step Two'
		commonPage.inputDropdownMapDefault(drpStepTwoData, flowControl)

		commonPage.scrollToTop()
		
		'Input Text Step Two'
		commonPage.inputTextListGroup(inputStepTwoData)

		'Tap Continue'
		commonPage.tapContinueBtn(flowControl)
	}

	/*
	 * fill Details Step Three
	 */
	void fillDetailsStepThree(Map drpStepThreeData, Map intputStepThreeData, FailureHandling flowControl = defaultFailureHandling) {

		'Input Text Step Three'
		commonPage.inputTextListGroup(intputStepThreeData)

		commonPage.swipeByW3CAction(5, 1500, 5, 100)

		'Input Dropdown Step Three'
		Mobile.delay(2)
		commonPage.inputDropdownMapDefault(drpStepThreeData, flowControl)

		'Tap Save And Continue'
		commonPage.tapSaveAndContinue(flowControl)
	}

	/*
	 * fill Details Step Four
	 */
	void fillDetailsStepFour(Map drpStepFourData, Map drpTypeSearchStepFourData, FailureHandling flowControl = defaultFailureHandling) {

		'Input Text Step Four'
		commonPage.inputDropdownMap(drpTypeSearchStepFourData, DropDownTypeEnum.SEARCH, flowControl)

		'Input Dropdown Step Four'
		commonPage.inputDropdownMapDefault(drpStepFourData, flowControl)
		
		'Tap Continue'
		commonPage.tapContinueBtn()
		commonPage.waitLoading()
	}

	/*
	 * fill Details Step Five
	 */
	void fillDetailsStepFive(Map drpStepFiveData, Map inputStepFiveData, FailureHandling flowControl = defaultFailureHandling) {
		'Input Text Step Five'
		inputStepFiveData.each { key, value ->
			commonPage.scrollToTestObject(commonPage.frmScrollView(), commonPage.txtSiblingGroup(key), ScrollDirectionEnum.DOWN, GlobalVariable.G_timeoutMedium, flowControl)
			MobileDriver.setText(commonPage.txtSiblingGroup(key), value, GlobalVariable.G_timeoutMedium, flowControl)
		}

		'Input Dropdown Step Five'
		drpStepFiveData.each { key, value ->
			applyAccountPage.selectItemDropDownList(key, value)
		}	

		'Tap Save And Continue'
		commonPage.tapSaveAndContinue(flowControl)
		Mobile.delay(GlobalVariable.G_timeoutMiniShort)
	}

	/*
	 * fill Details Step Six
	 */
	void fillDetailsStepSix(Map drpStepSixData, Map inputStepSixData, FailureHandling flowControl = defaultFailureHandling) {
		'Input Text Step Six'
		commonPage.inputTextListGroup(inputStepSixData, flowControl)

		'Input Dropdown Step Six'
		commonPage.inputDropdownMapDefault(drpStepSixData, flowControl)

		'Tap Save And Continue'
		commonPage.tapSaveAndContinue(flowControl)
		commonPage.waitLoading()
	}

	/*
	 * agreeAndConfirm
	 */
	void agreeAndConfirm(String agreeRadioLabel, FailureHandling flowControl = defaultFailureHandling) {
		Mobile.scrollToText(agreeRadioLabel)

		'Tap agree Radio'
		MobileDriver.tap(commonPage.grpPrecedingSibling(agreeRadioLabel), GlobalVariable.G_timeoutMedium, flowControl)

		'Tap Continue'
		MobileDriver.takeScreenshot()
		MobileDriver.tap(commonPage.lblDynamic(AGREE_AND_CONFIRM), GlobalVariable.G_timeoutMedium, flowControl)
	}

	/*
	 * uploadDocument
	 */
	void uploadDocument(FailureHandling flowControl = defaultFailureHandling) {
		'Tap Upload Later'
		MobileDriver.tap(commonPage.lblDynamic(UPLOAD_LATER), GlobalVariable.G_timeoutMedium, flowControl)
	}

	/*
	 * uploadDocumentStatus
	 */
	String uploadDocumentStatus(List uploadDocumentStatusData, FailureHandling flowControl = defaultFailureHandling) {
		'Verify UI Status'
		commonPage.verifyElements(uploadDocumentStatusData, commonPage.lblContainDynamicPath(), GlobalVariable.G_timeoutMedium, flowControl)
		MobileDriver.takeScreenshot()
		
		'Get Reference ID'
		String RefID = ""
		if(MobileDriver.waitForElementPresent(commonPage.lblSiblingDynamic(UIReferenceID), GlobalVariable.G_timeoutLong, FailureHandling.OPTIONAL)) {
			RefID = MobileDriver.getText(commonPage.lblSiblingDynamic(UIReferenceID), GlobalVariable.G_timeoutLong, flowControl)
		}

		'Tap Done'
		commonPage.tapDoneBtn()
		
		return RefID
	}
	
	/**NTB Flow**/
	
	/*
	 * Fill Details Step One
	 * NTB
	 */
	List<String> getKeyInMap(Map<String, String> mapData) {
		return new ArrayList<>(mapData.keySet())
	}
	
	String fullName
	String mobileNumber
	String dateOfBirth
	String idNumber
	
	String DATE_OF_BIRTH = "Date of birth"
	
	Map<String, String> fillDetailsStepOneNTB(Map<String,String> stepOneData, int timeout = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		MobileDriver.waitForElementPresent(commonPage.lblDynamic("Step 1 of 6"), timeout, flowControl)	
		boolean hasScrolled = false
	
		fullName = stepOneData['Name as per ID']?.trim() ? stepOneData['Name as per ID'] : "AutoCC ${RandomStringUtils.randomAlphabetic(5)}"
	
		mobileNumber = stepOneData['Mobile number']?.trim() ? stepOneData['Mobile number'] : "000${RandomStringUtils.randomNumeric(4)}"
	
		stepOneData.each { key, value ->
	
			if (!hasScrolled && key == "Email address") {
				MobileDriver.delay(GlobalVariable.G_timeoutMiniShort)
				commonPage.swipe(5, 1500, 5, 100)
				hasScrolled = true
			}
	
			switch(key) {
	
				case "Name as per ID":
					commonPage.setText(commonPage.txtSiblingGroup(key), fullName, timeout, flowControl)
					break
	
				case "Date of birth":
					MobileDriver.tap(applyCASAPage.lblNormalizeFollowingSibling(DATE_OF_BIRTH), timeout, flowControl)
					commonPage.tapDoneBtn()
					MobileDriver.delay(GlobalVariable.G_timeoutMiniShort)
					dateOfBirth = MobileDriver.getText(commonPage.lblSiblingOneGroup(DATE_OF_BIRTH), timeout, flowControl)
					break
	
				case "ID number":
					idNumber = stringUtils.generateRandomValidNRIC()
					commonPage.setText(commonPage.txtSiblingGroup(key), idNumber, timeout, flowControl)
					break
	
				case "Mobile number":
					commonPage.setText(commonPage.txtSiblingGroup(key), mobileNumber, timeout, flowControl)
					break
	
				case "Email address":
					commonPage.setText(commonPage.txtSiblingGroup(key), value, timeout, flowControl)
					break
	
				default:
					applyAccountPage.selectItemDropDownList(key, value)
					break
			}
		}
		'Tap Continue'
		MobileDriver.takeScreenshot()
		commonPage.tapContinueBtn()
	
		return [
				'Full name'     : fullName,
				'Mobile Number' : mobileNumber,
				'Date of birth' : dateOfBirth,
				'ID number'     : idNumber
		]
	}
	
	/*
	 * Fill Details Step Two
	 * NTB
	 */

	void fillDetailsStepTwoNTB(Map stepTwoData, int timeout = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		MobileDriver.waitForElementPresent(commonPage.lblDynamic("Step 2 of 6"), timeout, flowControl)		
		boolean hasScrolled = false
		
		String houseNumber = stepTwoData['House phone number']?.trim() ? stepTwoData['House phone number'] : "5000${RandomStringUtils.randomNumeric(4)}"
		
		stepTwoData.each { key, value ->
	
			if (!hasScrolled && key == "Nationality") {
				MobileDriver.delay(GlobalVariable.G_timeoutMiniShort)
				commonPage.swipe(5, 1500, 5, 100)
				hasScrolled = true
			}
		
			switch(key) {
				case "Name on card":
					commonPage.setText(commonPage.txtSiblingGroup(key), fullName, timeout, flowControl)
					break
		
				case "House phone number":
					commonPage.setText(commonPage.txtSiblingGroup(key), houseNumber, timeout, flowControl)
					break
		
				case "Gender":
					MobileDriver.tap(commonPage.grpPrecedingSibling(value), timeout,  flowControl)
					break
		
				case "Nationality":
					commonPage.setText(commonPage.txtSiblingGroup(key), value, timeout, flowControl)
					break
		
				default:
					applyAccountPage.selectItemDropDownList(key, value)
					break
			}
		}
		'Tap Continue'
		MobileDriver.takeScreenshot()
		commonPage.tapContinueBtn()
	}
	
	/*
	 * Fill Details Step Three
	 * NTB
	 */
	void fillDetailsStepThreeNTB(Map stepThreeData, int timeout = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		MobileDriver.waitForElementPresent(commonPage.lblDynamic("Step 3 of 6"), timeout, flowControl)
		boolean hasScrolled = false
	
		List<String> textFields = [
			"Home address line 1",
			"Home address line 2",
			"Home address line 3",
			"Postcode",
			"City"
		]
	
		stepThreeData.each { key, value ->
	
			if (!hasScrolled && key == "State") {
				MobileDriver.delay(GlobalVariable.G_timeoutMiniShort)
				commonPage.swipe(5, 1500, 5, 100)
				MobileDriver.delay(2)
				hasScrolled = true
			}
	
			if (textFields.contains(key)) {
				commonPage.setText(commonPage.txtSiblingGroup(key), value, timeout, flowControl)
			} else {
				applyAccountPage.selectItemDropDownList(key, value)
			}
		}
		'Tap Save And Continue'
		MobileDriver.takeScreenshot()
		commonPage.tapSaveAndContinue(flowControl)
	}
	
	/*
	 * Fill Details Step Four
	 * NTB
	 */
	void fillDetailsStepFourNTB(Map stepFourData, int timeout = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		MobileDriver.waitForElementPresent(commonPage.lblDynamic("Step 4 of 6"), timeout, flowControl)
		boolean hasScrolled = false
	
		stepFourData.each { key, value ->
	
			if (!hasScrolled && key == "Employment Type") {
				MobileDriver.delay(GlobalVariable.G_timeoutMiniShort)
				commonPage.swipe(5, 1500, 5, 100)
				MobileDriver.delay(2)
				hasScrolled = true
			}
	
			switch(key) {
				case "Employer name":
					commonPage.setText(commonPage.txtSiblingGroup(key), value, timeout, flowControl)
					break
	
				case "Occupation":
				case "Sector":
					commonPage.inputDropdownMapSearch([(key): value], flowControl)
					break
	
				default:
					commonPage.inputDropdownMapDefault([(key): value], flowControl)
					break
			}
		}	
		'Tap Continue'
		MobileDriver.takeScreenshot()
		commonPage.tapContinueBtn()
	}
		
	/*
	 * Fill Details Step Five
	 * NTB
	 */
	void fillDetailsStepFiveNTB(Map stepFiveData, int timeout = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {	
			MobileDriver.waitForElementPresent(commonPage.lblDynamic("Step 5 of 6"), timeout, flowControl)	
			boolean hasScrolled = false
		
			stepFiveData.each { key, value ->
		
				if (!hasScrolled && key == "City") {
					MobileDriver.delay(GlobalVariable.G_timeoutMiniShort)
					commonPage.swipe(5, 1500, 5, 100)
					hasScrolled = true
				}
		
				if (key == "State") {
					applyAccountPage.selectItemDropDownList(key, value)
				} else {
					commonPage.setText(commonPage.txtSiblingGroup(key), value, timeout, flowControl)
				}
			}	
			'Tap Save and Continue'
			MobileDriver.takeScreenshot()
			commonPage.tapSaveAndContinue(flowControl)
		}
	
	/*
	 * Fill Details Step Six
	 * NTB
	 */
	void fillDetailsStepSixNTB(Map stepSixData, int timeout = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		MobileDriver.waitForElementPresent(commonPage.lblDynamic("Step 6 of 6"), timeout, flowControl)
		boolean hasScrolled = false
	
		stepSixData.each { key, value ->
	
			if (!hasScrolled && key == "Card collection area/district") {
				MobileDriver.delay(GlobalVariable.G_timeoutMiniShort)
				commonPage.swipe(5, 1500, 5, 100)
				MobileDriver.delay(2)
				hasScrolled = true
			}
	
			switch(key) {
	
				case ["Monthly net income", "Other monthly commitments"]:
					commonPage.setText(commonPage.txtSiblingGroup(key), value, timeout, flowControl)
					break
	
				default:
					if (!value?.trim()) {
						'empty → use default dropdown'
						commonPage.inputDropdownMapDefault([(key): value], flowControl)
					} else {
						'value exists → select specific value'
						applyAccountPage.selectItemDropDownList(key, value)
					}
					break
			}
		}
		'Tap Save And Continue'
		MobileDriver.takeScreenshot()
		commonPage.tapSaveAndContinue(flowControl)
	}

	/* --------------------------------------------------- End Credit Card -----------------------------------------------*/
	/* --------------------------------------------------- VCB Card -----------------------------------------------*/
	
	/*
	 * Verify VCB Wallet Do Not Exist
	 */
	String maeWalletErrorMsg = 'Sorry, you need to have VCB Wallet before you can apply for card. Please apply for VCB Wallet under ACCOUNTS and try again.'
	
	boolean verifyMAEWalletDoNotExist() {
		return MobileDriver.waitForElementPresent(commonPage.lblDynamic(maeWalletErrorMsg), GlobalVariable.G_timeoutShort, FailureHandling.OPTIONAL)
	}
	
	/*
	 * requestCardStepOne
	 */
	void requestCardStepOne(List requestCardData, FailureHandling flowControl = defaultFailureHandling) {
		'Verify UI Request Card'
		verifyElements(requestCardData, commonPage.lblDynamicPath(), GlobalVariable.G_timeoutMedium, flowControl)
		'Tap Continue'
		commonPage.tapContinueBtn()
		commonPage.waitLoading()
	}

	/*
	 * requestCardStepTwo
	 */
	void requestCardStepTwo(Map inputRequestCardStepTwoData, String agreeData, FailureHandling flowControl = defaultFailureHandling) {
		'Input Request Card Step Two'
		commonPage.inputTextListGroup(inputRequestCardStepTwoData, flowControl)
		Mobile.hideKeyboard(FailureHandling.OPTIONAL)
		Mobile.scrollToText(agreeData)
		commonPage.swipe(5, 1000, 5, 10)
		MobileDriver.tap(commonPage.lblDynamic(TERMS_CONDITIONS), GlobalVariable.G_timeoutMedium, flowControl)

		'Tap Agree Radio'
		MobileDriver.tap(commonPage.grpPrecedingSibling(agreeData), GlobalVariable.G_timeoutMedium, flowControl)

		'Tap Continue'
		commonPage.tapContinueBtn()

		commonPage.waitLoading()
	}

	/* --------------------------------------------------- End VCB Card -----------------------------------------------*/

	/* --------------------------------------------------- Resume Credit Card Application -----------------------------------------------*/

	/*
	 * verifyResumeCCApplication: verify UI Resume Credit Card Application
	 */
	void verifyResumeCCApplication(List resumeData, String firstItemTap, FailureHandling flowControl = defaultFailureHandling) {
		'Verify UI Resume Credit Card Application'
		verifyElements(resumeData, commonPage.lblDynamicPath(), GlobalVariable.G_timeoutMedium, flowControl)

		'Tap first Item'
		MobileDriver.takeScreenshot()
		MobileDriver.tap(commonPage.lblDynamic(firstItemTap), GlobalVariable.G_timeoutMedium, flowControl)
	}

	/*
	 * verifyResumeCCApplication: verify UI Resume Credit Card Application
	 */
	String continueResumeCCApplication(List continueResumeData, FailureHandling flowControl = defaultFailureHandling) {
		'Continue at screen Upload Document'
		if(!waitForElementPresent(commonPage.lblDynamic(UPLOAD_DOCUMENTS), GlobalVariable.G_timeoutShort, FailureHandling.OPTIONAL)) {
			return
		}
		this.uploadDocument()
		String RefID = this.uploadDocumentStatus(continueResumeData)
		
		return RefID
	}
	/* --------------------------------------------------- End Credit Card Application -----------------------------------------------*/
	/* ---------------------------------------------------Supplementary Credit Card Application -----------------------------------------------*/
	
	/*
	 * Verify Supplementary CC Application (First Page)
	 */
	Map supplementaryCCApplicationStepOne(Map stepOneData, int timeout = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		'Verify Page'
		MobileDriver.waitForElementPresent(commonPage.lblDynamic("Step 1 of 2"), timeout, flowControl)
	
		boolean hasScrolled = false
		String supplementaryIDNumber = ""
		String supplementaryDateOfBirth = ""
	
		stepOneData.each { key, value ->
	
			if (!hasScrolled && key == "Supplementary card ID number") {
				MobileDriver.delay(GlobalVariable.G_timeoutMiniShort)
				commonPage.swipe(5, 1500, 5, 100)
				hasScrolled = true
			}
	
			switch(key) {
	
				case "Tell us about the supplementary cardholder":
					MobileDriver.tap(commonPage.imgNoDynamic(5), timeout, flowControl)
					break
	
				case "Principal email address":
					commonPage.setText(commonPage.txtSiblingGroup(key), value, timeout, flowControl)
					break
	
				case "Supplementary card ID number":
					supplementaryIDNumber = stringUtils.generateRandomValidNRIC()
					commonPage.setText(commonPage.txtSiblingGroup(key), supplementaryIDNumber, timeout, flowControl)
					break
	
				case "Supplementary date of birth":
					supplementaryDateOfBirth = MobileDriver.getText(commonPage.lblSiblingOneGroup("Supplementary date of birth"), timeout, flowControl)
					break
	
				default:
					applyAccountPage.selectItemDropDownList(key, value)
					break
			}
		}
	
		'Tap Save And Continue'
		MobileDriver.takeScreenshot()
		commonPage.tapSaveAndContinue(flowControl)
	
		return [
			'Supplementary ID Number'		: supplementaryIDNumber,
			'Supplementary date of birth'   : supplementaryDateOfBirth,
		]
	}
		
	/*
	 * Verify Supplementary CC Application (Second Page)
	 */
	Map supplementaryCCApplicationStepTwo(Map<String,String> stepTwoData, int timeout = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		'Verify Page'
		MobileDriver.waitForElementPresent(commonPage.lblDynamic("Step 2 of 2"), timeout, flowControl)
		boolean hasScrolled = false
	
		String fullName = stepTwoData['Name']?.trim() ?: "AutoSupCC ${RandomStringUtils.randomAlphabetic(5)}"
		String mobileNumber = stepTwoData['Mobile number']?.trim() ?: "001${RandomStringUtils.randomNumeric(4)}"
	
		def textFields = ["Name", "Name on card", "Email address", "Mobile number", "Nationality",
						  "Home address line 1","Home address line 2","Home address line 3",
						  "Postcode","City","Monthly income","Employer name"]
		def dropdownSearchFields = ["Occupation", "Sector"]
	
		stepTwoData.each { key, value ->
	
			'Scroll if element not visible'
			if (!hasScrolled && ["Mobile number", "Sector", "Postcode", "Source of income", "Card collection state"].contains(key)) {
				MobileDriver.delay(GlobalVariable.G_timeoutMiniShort)
				commonPage.swipe(5, 1400, 5, 100)
			}
	
			if (textFields.contains(key)) {
				def inputValue = (key in ["Name", "Name on card"]) ? fullName :
								 (key == "Mobile number") ? mobileNumber : value
				commonPage.setText(commonPage.txtSiblingGroup(key), inputValue, timeout, flowControl)
				
				'Special handling ONLY for Mobile number'
				if (key == "Mobile number") {
					MobileDriver.tap(applyCASAPage.lblNormalizeFollowingSibling("Mobile number"), timeout, flowControl)
					commonPage.tapDoneBtn()
					MobileDriver.delay(GlobalVariable.G_timeoutMiniShort)
				}
			} else if (dropdownSearchFields.contains(key)) {
				commonPage.inputDropdownMapSearch([(key): value], flowControl)
			} else if (key == "Gender") {
				MobileDriver.tap(commonPage.grpPrecedingSibling(value), timeout, flowControl)
			} else {
				if (!value?.trim()) {
					'empty → use default dropdown'
					commonPage.inputDropdownMapDefault([(key): value], flowControl)
				} else {
					'value exists → select specific value'
					applyAccountPage.selectItemDropDownList(key, value)
				}
			}
		}	
		'Tap Save And Continue'
		MobileDriver.takeScreenshot()
		commonPage.tapSaveAndContinue(flowControl)
	
		return [
			'Name'     : fullName,
			'Mobile number' : mobileNumber
		]
	}
	/* --------------------------------------------------- End Credit Card Application -----------------------------------------------*/
	/* --------------------------------------------------- Debit Card Application -----------------------------------------------*/
	/*
	 * Verify UI Debit Card Application Step One Screen
	 */
	void verifyDebitCardApplicationStepOne(List debitCardApplicationStepOneData, String debitCardType, FailureHandling flowControl = defaultFailureHandling) {
		'Verify UI Debit Card Application Step One'
		verifyElements(debitCardApplicationStepOneData, commonPage.lblDynamicPath(), GlobalVariable.G_timeoutMedium, flowControl)

		'Select Card Type'
		MobileDriver.tap(commonPage.lblDynamic(debitCardType), GlobalVariable.G_timeoutMedium, flowControl)

		'Tap Next Button'
		commonPage.tapNextBtn()
	}

	/*
	 * Verify UI Debit Card Application Step Two Screen
	 */
	void verifyDebitCardApplicationStepTwo(String agreeTermAndConditions, FailureHandling flowControl = defaultFailureHandling) {
		'Hide Keyboard'
		Mobile.hideKeyboard(FailureHandling.OPTIONAL)
		
		'Scroll To Terms And Conditions'
		Mobile.scrollToText(agreeTermAndConditions)
		commonPage.swipe(5, 1000, 5, 10)
		
		'Tap Agree Radio'
		MobileDriver.tap(commonPage.grpPrecedingSibling(agreeTermAndConditions), GlobalVariable.G_timeoutMedium, flowControl)

		'Tap Next Button'
		commonPage.tapNextBtn()
	}

	/* --------------------------------------------------- End Debit Card Application -----------------------------------------------*/
	/* ------------------------------------------------------- Card Activation ------------------------------------------------------*/
	/*
	 * Enter Pin
	 */
	void enterPIN(String pageText, String pin, int timeout = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		'Verify Page'
		MobileDriver.waitForElementPresent(commonPage.lblContainDynamic(pageText), timeout, flowControl)
		
		'Enter Pin'
		commonPage.enterNO(pin)
	}
	
	/*
	 * Select Card at Card Tab
	 */
	void selectCard(String cardName, int timeout = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		'Select Card'		
		MobileDriver.tap(commonPage.lblContainDynamic(cardName), timeout, flowControl)
		
		'Verify Page'
		MobileDriver.waitForElementPresent(commonPage.lblDynamic("Card Management"), timeout, flowControl)
		MobileDriver.takeScreenshot()
	}
}
