package com.vcb.mobile.internal.pages.base.applypage

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import java.security.KeyStore.Entry
import java.time.LocalDate

import org.apache.commons.collections4.map.HashedMap

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
import com.vcb.mobile.internal.pages.base.LoginPage
import com.vcb.mobile.internal.pages.base.OtpPage
import com.vcb.mobile.internal.pages.base.applypage.ApplyCASAPage

import com.vcb.utils.StringUtils

import internal.GlobalVariable

public abstract class ApplyAccountPage extends BasePage{

	static CommonPage commonPage = getPage(CommonPage)
	static ApplyCASAPage applyCASAPage = BasePage.getPage(ApplyCASAPage)

	static final String GO_TO_ACCOUNT = 'Go To Account'
	static final String SUITABILITY_ASSESSMENT = 'Suitability assessment'
	static final String IN_PROGRESS = "What's your email address and invite code (if any)?"
	static final String APPLY_NOW = 'Apply Now'
	static final String AGREE_AND_CONFIRM = 'Agree & Confirm'
	static final String READY = "I'm Ready"
	static final String SNAP_PHOTO = "Snap Photo"
	static final String CONFIRM = 'Confirm'
	static final String EDIT = 'Edit'
	static final String M2UPREMIER_EXIST = 'Sorry , you have existing zest-i or m2upremier account.'
	static final String SAVING_ACCOUNT_I_EXIST = 'You may only own one account for this product [OC02]'
	static final String MYKAD_NUMBER = "MyKad number"
	static final String STATE = "State"
	static final String PASSPORT_NUMBER = "Passport number"
	static final String DATE_OF_BIRTH = "Date of Birth"
	static final String REQUEST_OTP = "Request OTP"
	static final String APPLY_FOR_PMA = "Apply for PMA-i Account"
	static final String APPLY_FOR_PREMIER = "Apply For Premier 1 Account"
	static final String PREMIER_MUDHARABAH_ACCOUNT_I = "Premier Mudharabah Account-i"
	static final String PREMIER_1_ACCOUNT = "Premier 1 Account"
	static final String YOUR_OTP = "Your Otp"
	static final String HI_THERE = "Hi there,"
	static final String FRONT = "Front"
	static final String NATIONALITY = 'Nationality'
	static final String PASSPORT_EXD = "Passport expiry date"
	static final String NEXT = 'Next'
	static final String ZESTI_TITLE = "Zest-i Account Application"
	static final String OCCUPATION = "Occupation"
	static final String EMPLOYER_NAME = "Employer Name"
	static final String EXAMPLE_NAME = "e.g Vcb"
	static final String SECTOR = "Sector"
	static final String EMPLOYMENT_TYPE = "Employment type"
	static final String MONTHLY_INCOME = "Monthly income"
	static final String SOURCE_INCOME = "Source of income"
	static final String UNEMPLOYED  = "Unemployed/Outside Labour Force"
	static final String OUT = "Out Of The Workforce"
	static final String MALAYSIA = "Malaysia"
	static final String PERSONAL_DETAILS = "Personal Details"
	static final String RESIDENTIAL_DETAILS = "Residential Details"
	static final String DEBIT_CARD_DELIVERY_ADDRESS = "Debit Card Delivery Address"
	static final String EMPLOYMENT_DETAILS = 'Employment Details'
	static final String PREFERRED_BRANCH_DETAILS = 'Preferred Branch Details'
	static final String PREFER_ANOTHER_BRANCH = 'I Prefer Another Branch'
	static final String ADDITIONAL_DETAILS = 'Additional Details'
	static final String ADDITIONAL_INCOME_DETAILS = 'Additional Income Details'
	static final String PRIMARY_INCOME = 'Primary income'
	static final String PRIMARY_SOURCE_OF_WEALTH = 'Primary source of wealth'
	static final String SHOW_PASSWORD = 'Show password'
	static final String TITLE = 'Title'
	static final String GENDER = 'Gender'
	static final String RACE = 'Race'
	static final String PEP = 'PEP'
	static final String BRANCH = 'Branch'
	static final String REGISTER_M2U_ACCESS = 'Register M2U Access'
	static final String DEFAULT_PASS = 'Vcb@1'
	static final String TAKE_PHOTO = 'TAKE PHOTO'
	static final String SKIP = "Skip"
	static final String SUBMIT = "Submit"
	
	StringUtils stringUtil = new StringUtils()
	OtpPage PAGE_OBJECT = BasePage.getPage(OtpPage)
	LoginPage loginPage = BasePage.getPage(LoginPage)

	abstract TestObject imgSiblingGroup(String label, int index=1)
	abstract TestObject lblSiblingGroup(String label)
	abstract TestObject txtQuestion(String text, String answer = '1')
	abstract TestObject txtSiblingGroup(String label)

	/*
	 * Select Card Type base on cardName
	 */
	void selectAccountType(String cardName, FailureHandling flowControl = defaultFailureHandling) {
		commonPage.scrollToTestObject(commonPage.frmHorizontalScrollNo(2), commonPage.lblNormalizeSpace(cardName), ScrollDirectionEnum.DOWN, GlobalVariable.G_timeoutShort, flowControl)
		delay(GlobalVariable.G_timeoutMiniShort)
		MobileDriver.tap(commonPage.lblNormalizeSpace(cardName), GlobalVariable.G_timeoutMedium, flowControl)
		commonPage.waitLoading()
		MobileDriver.takeScreenshot()
	}

	/*
	 * verify First Screen and Apply Now Button
	 */
	void tapApplyNow(FailureHandling flowControl = defaultFailureHandling) {
		MobileDriver.tap(commonPage.lblDynamic(APPLY_NOW), GlobalVariable.G_timeoutLong, flowControl)
	}

	/*
	 * verifyAgreeAndConfirm
	 */
	void verifyAgreeAndConfirm(List agreeAndConfirmUIData, String agreeRadio, FailureHandling flowControl = defaultFailureHandling) {
		'Verify UI Agree And Confirm'
		verifyElements(agreeAndConfirmUIData, commonPage.lblContainDynamicPath(), GlobalVariable.G_timeoutMedium, flowControl)

		commonPage.swipeByW3CAction(5, 1500, 5, 100)
		Mobile.scrollToText(agreeRadio)

		'Tap Agree Radio'
		MobileDriver.tap(commonPage.lblDynamic(agreeRadio), GlobalVariable.G_timeoutMedium, flowControl)

		'Tap Agree And Confirm'
		MobileDriver.tap(commonPage.lblDynamic(AGREE_AND_CONFIRM), GlobalVariable.G_timeoutMedium, flowControl)
	}

	/*-------------------------------------------------- VCB Wallet --------------------------------------------------*/

	/*
	 * verifyMAEWalletScreenOne
	 */

	boolean checkMAEWalletExist() {
		return MobileDriver.waitForElementPresent(commonPage.lblDynamic(GO_TO_ACCOUNT), GlobalVariable.G_timeoutShort, FailureHandling.OPTIONAL)
	}
	boolean checkMAEWalletInProgress() {
		return MobileDriver.waitForElementPresent(commonPage.lblDynamic(IN_PROGRESS), GlobalVariable.G_timeoutShort, FailureHandling.OPTIONAL)
	}

	boolean checkVcb2uPremierExist() {
		return MobileDriver.waitForElementPresent(commonPage.lblDynamic(M2UPREMIER_EXIST), GlobalVariable.G_timeoutMedium, FailureHandling.OPTIONAL)
	}

	boolean checkSavingAccountIExist() {
		return MobileDriver.waitForElementPresent(commonPage.lblDynamic(SAVING_ACCOUNT_I_EXIST), GlobalVariable.G_timeoutMedium, FailureHandling.OPTIONAL)
	}
	boolean checkSAScreenExist() {
		return MobileDriver.waitForElementPresent(commonPage.lblDynamic(SUITABILITY_ASSESSMENT), GlobalVariable.G_timeoutShort, FailureHandling.OPTIONAL)
	}
	void verifyMAEWalletScreenExist(List MAEWalletExistData, FailureHandling flowControl = defaultFailureHandling) {
		verifyElements(MAEWalletExistData, commonPage.lblDynamicPath(), GlobalVariable.G_timeoutMedium, flowControl)

		'Tap Go To Account'
		MobileDriver.tap(commonPage.lblDynamic(GO_TO_ACCOUNT), GlobalVariable.G_timeoutMedium, flowControl)
	}

	void verifyMAEWalletPersonalData(List data, String agreeRadio, String option, FailureHandling flowControl = defaultFailureHandling) {
		verifyElements(data, commonPage.lblDynamicPath(), GlobalVariable.G_timeoutMedium, flowControl)

		'Tap Yes option'
		MobileDriver.tap(commonPage.grpPrecedingSibling(agreeRadio), GlobalVariable.G_timeoutMedium, flowControl)
		Mobile.scrollToText(agreeRadio)

		'Tap Agree Radio'
		commonPage.swipe(5, 1500, 5, 100)
		MobileDriver.delay(2)
		MobileDriver.tap(commonPage.grpPrecedingSibling(option), GlobalVariable.G_timeoutMedium, flowControl)
		commonPage.tapContinueBtn()
	}
	
	void verifyMAESuccessPage(List statusScreenSuccess, int timeout = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		'Wait to next Page'
		commonPage.waitLoading()

		'Verify Status Screen'
		verifySomeElementsMatchInList(statusScreenSuccess, commonPage.lblContainDynamicPath(), timeout, FailureHandling.OPTIONAL)
		
		'Take Screenshot'
		MobileDriver.takeScreenshot()
	}
	
	void verifySuccessPBA(List data, FailureHandling flowControl = defaultFailureHandling) {
		verifyElements(data, commonPage.lblDynamicPath(), GlobalVariable.G_timeoutMedium, flowControl)

		MobileDriver.tap(commonPage.lblDynamic(REGISTER_M2U_ACCESS), GlobalVariable.G_timeoutMedium, flowControl)
		
	}
	
	void createVcb2u(Map inputData,Map drpData, FailureHandling flowControl = defaultFailureHandling) {
		'Create Vcb2u'
		String name = StringUtils.generateRandomNameWithNumber()
		MobileDriver.setTextSimply(commonPage.txtEditNo(), name, 0)
		commonPage.tapContinueBtn()
		delay(GlobalVariable.G_timeoutShort);
		'Enter Pass'
		MobileDriver.setTextSimply(commonPage.txtEditNo(), DEFAULT_PASS, 0)
		MobileDriver.setTextSimply(commonPage.txtEditNo(2), DEFAULT_PASS, 0)
		commonPage.tapContinueBtn()
		delay(GlobalVariable.G_timeoutMedium);
		'Input image'
		commonPage.tapContinueBtn()
		commonPage.tapContinueBtn()
		MobileDriver.tap(commonPage.imgNoDynamic(3), GlobalVariable.G_timeoutMedium, flowControl)
		MobileDriver.tap(commonPage.lblDynamic(TAKE_PHOTO), GlobalVariable.G_timeoutMedium, flowControl)
		'capture'
		MobileDriver.tap(commonPage.imgNoDynamic(4), GlobalVariable.G_timeoutMedium, flowControl)
		delay(GlobalVariable.G_timeoutShort);
		commonPage.tapContinueBtn()
		delay(GlobalVariable.G_timeoutShort);
		String securityPhrase = StringUtils.generateRandomNameWithNumber(15)
		MobileDriver.setTextSimply(commonPage.txtEditNo(), securityPhrase, GlobalVariable.G_timeoutShort)
		Mobile.hideKeyboard(FailureHandling.OPTIONAL)
		commonPage.tapContinueBtn()
		delay(GlobalVariable.G_timeoutMedium);
		commonPage.inputDropdownMapDefault(drpData)
		fillInformationMAEAccount(inputData)
		commonPage.tapContinueBtn()
	}

	/*-------------------------------------------------- End VCB Wallet --------------------------------------------------*/

	/*-------------------------------------------------- Savings Account-i --------------------------------------------------*/
	void verifySavingAccountExist(List savingAccountExistData, FailureHandling flowControl = defaultFailureHandling) {
		verifyElements(savingAccountExistData, commonPage.lblDynamicPath(), GlobalVariable.G_timeoutMedium, flowControl)

		'Back to Apply Accounts'
		MobileDriver.tapAndLog(commonPage.imgNoDynamic(2), GlobalVariable.G_timeoutMedium, 'Back To Apply Accounts', flowControl)
	}
	
	/*-------------------------------------------------- End Savings Account-i --------------------------------------------------*/
	/*-------------------------------------------------- Zest-i --------------------------------------------------*/
	/*
	 * verifyZestIConfirmation
	 */
	void verifyZestIConfirmation(List confirmUIData, FailureHandling flowControl = defaultFailureHandling) {
		'Verify UI Confirm'
		verifyElements(confirmUIData, commonPage.lblContainDynamicPath(), GlobalVariable.G_timeoutMedium, flowControl)

		'Tap Agree And Confirm'
		MobileDriver.tap(commonPage.lblDynamic(AGREE_AND_CONFIRM), GlobalVariable.G_timeoutMedium, flowControl)
	}

	/*-------------------------------------------------- End Zest-i --------------------------------------------------*/


	/*-------------------------------------------------- Premier Mudharabah Account-i --------------------------------------------------*/
	void verifyPMAConfirmation(List confirmUIData, Map drpEditBranchData = [:], FailureHandling flowControl = defaultFailureHandling) {
		'Verify UI Confirm'
		scrollToAndVerifyTextInList(confirmUIData, commonPage.lblContainDynamicPath(), GlobalVariable.G_timeoutMedium, flowControl)
		
		if (drpEditBranchData) {
			'Edit Branch Default'
			MobileDriver.scrollToEnd()
			MobileDriver.tap(commonPage.lblDynamicTextLast(EDIT), GlobalVariable.G_timeoutMedium)
			MobileDriver.tap(commonPage.lblDynamic(PREFER_ANOTHER_BRANCH), GlobalVariable.G_timeoutMedium)
			commonPage.inputDropdownMapDefault(drpEditBranchData)
			MobileDriver.tap(commonPage.lblDynamic(CONFIRM), GlobalVariable.G_timeoutMedium, flowControl)
			delay(GlobalVariable.G_timeoutMiniShort);
		}
		
		'Tap Agree And Confirm'
		MobileDriver.tap(commonPage.lblDynamic(CONFIRM), GlobalVariable.G_timeoutMedium, flowControl)
	}
	void verifySAScreen(List<String> questions, Map inputDrpData, String btnText, int timeout = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		commonPage.inputDropdownMapDefault(inputDrpData)
		for(int i = 0 ; i < questions.size() ; i++) {
			if(i == 3) {
				commonPage.swipeByW3CAction(5, 1500, 5, 100)
				delay(GlobalVariable.G_timeoutMiniShort);
			}
			waitForElementPresent(commonPage.lblSiblingOneGroup(questions[i]), timeout, flowControl);
			MobileDriver.tap(commonPage.lblSiblingOneGroup(questions[i]),  timeout, FailureHandling.STOP_ON_FAILURE)
		}
		MobileDriver.tap(commonPage.lblDynamic(btnText),  timeout, FailureHandling.STOP_ON_FAILURE)
	}
	
	void verifyNewSAScreen(Map inputDrpData, String option, String btnText,int timeout = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		commonPage.inputDropdownMapDefault(inputDrpData)
		for(int i = 1 ; i <= 5 ; i++) {
			waitForElementPresent(commonPage.lblDynamicNo(option, i), timeout, flowControl);
			MobileDriver.tap(commonPage.lblDynamicNo(option, i),  timeout, FailureHandling.STOP_ON_FAILURE)
			if(i == 3) {
				commonPage.swipeByW3CAction(5, 700, 5, 200)
				delay(GlobalVariable.G_timeoutMiniShort);
			}
		}
		MobileDriver.tap(commonPage.lblDynamic(btnText),  timeout, FailureHandling.STOP_ON_FAILURE)
	}
	/*-------------------------------------------------- End Premier Mudharabah Account-i --------------------------------------------------*/


	/*-------------------------------------------------- Yippie-i Account Application --------------------------------------------------*/
	/*
	 * verifyYippieStepFour: Verify Yippie-i Account Application Step 4
	 */
	void verifyYippieStepFour(List stepFourUIData, Map inputStepFourData, String confirmStepFour, FailureHandling flowControl = defaultFailureHandling) {
		'Verify UI Step Four'
		verifyElements(stepFourUIData, commonPage.lblContainDynamicPath(), GlobalVariable.G_timeoutLong, flowControl)

		'Input'
		commonPage.inputTextListGroup(inputStepFourData)

		'Tap Confirm Radio'
		MobileDriver.tap(commonPage.grpPrecedingSibling(confirmStepFour), GlobalVariable.G_timeoutMedium, flowControl)

		'Tap Next'
		commonPage.tapNextBtn()
	}
	void verifyYippieStepFive(Map inputStepFiveData, String gender, Map drpstepFiveData, FailureHandling flowControl = defaultFailureHandling) {

		'Input'
		commonPage.inputTextListGroup(inputStepFiveData)

		'Tap Confirm Radio'
		MobileDriver.tap(commonPage.grpPrecedingSibling(gender), GlobalVariable.G_timeoutMedium, flowControl)
		commonPage.inputDropdownMapDefault(drpstepFiveData)

		'Tap Next'
		commonPage.tapNextBtn()
	}	
	void skipTheReferral(int timeout = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		MobileDriver.tap(commonPage.lblDynamic(SKIP), timeout, flowControl)
	}
	void verifySecondaryAccountHolder(List secondaryAccountHolderInfo, int timeout = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		'Verify UI Step Four'
		verifyElements(secondaryAccountHolderInfo, commonPage.lblContainDynamicPath(), GlobalVariable.G_timeoutMedium, flowControl)
		'Tap Next'
		commonPage.tapNextBtn()
	}
	void readyForChildsBirthCeritificate(int timeout = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		MobileDriver.tap(commonPage.lblDynamic(READY), timeout, flowControl)
	}
	/*-------------------------------------------------- End Yippie-i Account Application --------------------------------------------------*/

	/*-------------------------------------------------- Vcb2u Premier Account --------------------------------------------------*/
	void verifyMPAConfirmation(List confirmUIData, FailureHandling flowControl = defaultFailureHandling) {
		'Verify UI Confirm'
		verifyElements(confirmUIData, commonPage.lblContainDynamicPath(), GlobalVariable.G_timeoutMedium, flowControl)

		'Tap Agree And Confirm'
		MobileDriver.tap(commonPage.lblDynamic(AGREE_AND_CONFIRM), GlobalVariable.G_timeoutMedium, flowControl)
	}
	/*-------------------------------------------------- End Vcb2u Premier Account --------------------------------------------------*/

	/*-------------------------------------------------- Yippie-i Account Application --------------------------------------------------*/
	/*
	 * verifyYippieSavingStepFour: Verify Yippie Savings Account Application Step 4
	 */
	void verifyYippieSavingStepFour(Map inputStepFourData, String confirmStepFour, FailureHandling flowControl = defaultFailureHandling) {

		'Input'
		commonPage.inputTextListGroup(inputStepFourData)
		MobileDriver.tap(commonPage.lblContainDynamic(confirmStepFour), GlobalVariable.G_timeoutMedium, flowControl)

		'Tap Confirm Radio'
		MobileDriver.tap(commonPage.btnRadioImageViewStyle(confirmStepFour), GlobalVariable.G_timeoutMedium, flowControl)

		'Tap Next'
		commonPage.tapNextBtn()
	}
	/*
	 * verifyYippieSavingStepFour: Verify Yippie Savings Account Application Step 5
	 */
	void verifyYippieSavingStepFive(Map inputStepFiveData, String gender, Map drpstepFiveData, FailureHandling flowControl = defaultFailureHandling) {

		'Input'
		commonPage.inputTextListGroup(inputStepFiveData)

		'Tap Confirm Radio'
		MobileDriver.tap(commonPage.btnRadioImageViewStyle(gender), GlobalVariable.G_timeoutMedium, flowControl)
		commonPage.inputDropdownMapDefault(drpstepFiveData)

		'Tap Next'
		commonPage.tapNextBtn()
	}

	void verifyChildBirthCertificate(List stepSixUIData, FailureHandling flowControl = defaultFailureHandling) {
		'Verify UI Agree And Confirm'
		verifyElements(stepSixUIData, commonPage.lblContainDynamicPath(), GlobalVariable.G_timeoutMedium, flowControl)


		'Tap Im ready'
		MobileDriver.tap(commonPage.lblDynamic(READY), GlobalVariable.G_timeoutMedium, flowControl)
		commonPage.delay(GlobalVariable.G_timeoutMiniShort)

		'Tap Snap Photo'
		MobileDriver.tap(commonPage.lblDynamic(SNAP_PHOTO), GlobalVariable.G_timeoutMedium, flowControl)
		commonPage.delay(GlobalVariable.G_timeoutMiniShort)

		'Tap Next'
		commonPage.tapSubmitBtn()
		commonPage.delay(GlobalVariable.G_timeoutMedium)
		
		'Tap Confirm'
		MobileDriver.tap(commonPage.lblDynamic(CONFIRM), GlobalVariable.G_timeoutMedium, flowControl)
	}
	/*-------------------------------------------------- End Yippie Savings Account Application --------------------------------------------------*/

	/*-------------------------------------------------- Global Access Account-i Application --------------------------------------------------*/
	
	void agreeAndAcceptTnC(String radioText,
		int timeout = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		Mobile.scrollToText(radioText)
		commonPage.delay(GlobalVariable.G_timeoutMiniShort)
		'Tap agree Radio'
		MobileDriver.tap(commonPage.grpPrecedingSibling(radioText), GlobalVariable.G_timeoutMedium, flowControl)

		'Tap Next'
		MobileDriver.tap(commonPage.lblDynamic(NEXT), GlobalVariable.G_timeoutMedium, flowControl)
	}
	
	
	/*-------------------------------------------------- End Global Access Account-i Application --------------------------------------------------*/

	
	/*-------------------------------------------------- Savings Account-i Application --------------------------------------------------*/

	void verifyStep1SavingAccounti(List<String> step1UIData, Map step1InputData, FailureHandling flowControl = defaultFailureHandling) {
		scrollToAndVerifyTextInList(step1UIData, commonPage.lblDynamicPath(), GlobalVariable.G_timeoutMedium)
		scrollToTop()
		'Input'
		commonPage.inputTextListGroup(step1InputData)

		'Select state'
		Mobile.scrollToText(STATE)
		MobileDriver.tap(commonPage.lblDynamic(STATE), GlobalVariable.G_timeoutMedium, flowControl)
		MobileDriver.tap(commonPage.imgArrowDownDropdown(STATE, 1), GlobalVariable.G_timeoutMedium, flowControl)

		commonPage.tapDoneBtn()
		commonPage.tapNextBtn()
	}

	/*-------------------------------------------------- End Savings Account-i Account Application --------------------------------------------------*/

	void fillInformationMAEAccount(Map<String, String> maeAccountData, FailureHandling flowControl = defaultFailureHandling) {
		for (Map.Entry<String, String> entry : maeAccountData.entrySet()) {
			MobileDriver.scrollToText(entry.getKey())
			//			MobileDriver.tap(commonPage.lblDynamic(entry.getKey()), GlobalVariable.G_timeoutMedium, flowControl)
			commonPage.setTextSimply(commonPage.txtSiblingGroup(entry.getKey()), entry.getValue(), GlobalVariable.G_timeoutMedium, flowControl)
		}
	}

	void chooseIdType(String idType, int timeout = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		if(waitForElementPresent(commonPage.grpPrecedingSibling(idType), timeout)) {
			MobileDriver.tap(commonPage.grpPrecedingSibling(idType), timeout, flowControl)
		}
		else {
			MobileDriver.tap(commonPage.lblDynamic(idType), timeout, flowControl)
		}
	}
	void generateMykadNumber( int timeout = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		String myKadNumber = stringUtil.generateRandomValidNRIC()
		commonPage.setTextSimply(commonPage.txtSiblingGroup(MYKAD_NUMBER), myKadNumber, timeout, flowControl)
	}
	void checkAndCreatePMAAccount( int timeout = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		if(waitForElementPresent(commonPage.lblDynamic(APPLY_FOR_PMA),timeout)) {
			MobileDriver.tap(commonPage.lblDynamic(APPLY_FOR_PMA), timeout, flowControl)
			MobileDriver.verifyElementVisible(commonPage.lblDynamic(PREMIER_MUDHARABAH_ACCOUNT_I), timeout);
			delay(GlobalVariable.G_timeoutShort)
			tapApplyNow()
		}
	}
	void checkAndCreatePremierAccount( int timeout = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		if(waitForElementPresent(commonPage.lblDynamic(APPLY_FOR_PREMIER),timeout)) {
			MobileDriver.tap(commonPage.lblDynamic(APPLY_FOR_PREMIER), timeout, flowControl)
			MobileDriver.verifyElementVisible(commonPage.lblDynamic(PREMIER_1_ACCOUNT), timeout);
			delay(GlobalVariable.G_timeoutShort)
			tapApplyNow()
		}
	}
	void byPassEkyc( int timeout = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		if(waitForElementPresent(commonPage.lblDynamic(HI_THERE),timeout)) {
			commonPage.tapNextBtn()
			MobileDriver.tap(commonPage.lblDynamic(FRONT), timeout, flowControl)
			commonPage.tapNextBtn()
		}
	}

	void generateAndFillInformationResidential(Map<String, String> maeAccountData, String state, int timeout = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		fillInformationMAEAccount(maeAccountData)
		commonPage.swipeByW3CAction(5, 1500, 5, 100);
		MobileDriver.tap(imgSiblingGroup(STATE), timeout, flowControl)
		commonPage.selectDropdownScrollOneLine(state, timeout, flowControl);
		commonPage.tapDoneBtn()
		delay(GlobalVariable.G_timeoutMiniShort)
	}
	void selectDropDownValue(Map<String, String> mapData, int timeout = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		for (Map.Entry<String, String> entry : mapData.entrySet()) {
			MobileDriver.tap(imgSiblingGroup(entry.getKey() ), timeout, flowControl)
			commonPage.selectDropdownScrollOneLine(entry.getValue(), timeout);
			commonPage.tapDoneBtn()
		}
	}
	void fillInEmploymentData(Map<String, String> maeEmploymentData, Map<String, String> maeAccountData) {
		selectDropDownValue(maeEmploymentData)
		fillInformationMAEAccount(maeAccountData)
	}
	void navigateToMyKadVerification(Map<String, String> maeAccountData, int timeout = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		MobileDriver.tap(commonPage.btnDynamic(CONFIRM), timeout, flowControl)
		fillInformationMAEAccount(maeAccountData)
		commonPage.tapContinueBtn()
		commonPage.tapNextBtn()
	}
	String generatePassportNumber() {
		return stringUtil.generateRandomPassport(18)
	}
	void generateAndFillPassportInfo(Map<String,String> passportData,int timeout = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		delay(GlobalVariable.G_timeoutMiniShort)
		commonPage.setText(commonPage.txtSiblingGroup(PASSPORT_NUMBER), generatePassportNumber(), timeout, flowControl)
		if(waitForElementPresent(this.lblSiblingGroup(DATE_OF_BIRTH),timeout)) {
			MobileDriver.tap(this.lblSiblingGroup(DATE_OF_BIRTH), timeout, flowControl)
		}
		MobileDriver.tap(commonPage.lblDynamic(commonPage.UIDonebtn), timeout, flowControl)
		selectDropDownValue(passportData)
	}
	void selectItemDropDownList(String fieldName, String value, int timeout = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		if(value.isBlank() || value == ""){
			flowControl
		}
		commonPage.swipeToObject(imgSiblingGroup(fieldName), timeout, FailureHandling.OPTIONAL)
		Mobile.verifyElementExist(imgSiblingGroup(fieldName), timeout, FailureHandling.CONTINUE_ON_FAILURE)
		info("Tap on drop-down ${fieldName}")
		Mobile.tap(imgSiblingGroup(fieldName), timeout, flowControl)
		if(!MobileDriver.waitForElementPresent(commonPage.lblDynamic("Done"), GlobalVariable.G_timeoutShort, flowControl)) {
			info("Tap on drop-down again ${fieldName}")
			Mobile.tap(imgSiblingGroup(fieldName), timeout, FailureHandling.OPTIONAL)
		}
		info("Search for ${value}")
		if(!(MobileDriver.waitForElementPresent(commonPage.lblNormalizeSpace(value), timeout, FailureHandling.OPTIONAL))){
			info("Scroll to ${value}")
			Mobile.scrollToText(value, FailureHandling.OPTIONAL)
		}
		info("Tap on ${value}")
		Mobile.tap(commonPage.lblNormalizeSpace(value), timeout, FailureHandling.OPTIONAL)
		info("Tap Done to close drop-down")
		Mobile.tap(commonPage.lblDynamic("Done"), GlobalVariable.G_timeoutLong,FailureHandling.OPTIONAL)
	}
	void chooseRadionButtonValue(Map<String, String> questions, int timeout = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		int index = 0;
		questions.each { question, answer ->
			if (index == 3) {
				commonPage.swipeByW3CAction(5, 1500, 5, 100)
				delay(GlobalVariable.G_timeoutMiniShort);
			}
			waitForElementPresent(this.txtQuestion(question), timeout, flowControl);
			MobileDriver.tap(this.txtQuestion(question, answer), timeout, FailureHandling.STOP_ON_FAILURE);
			index++;
		}
	}
	void handlePEPQuestion(String PEP, int timeout = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		MobileDriver.scrollToEnd();
		delay(GlobalVariable.G_timeoutMiniShort)
		waitForElementPresent(this.txtQuestion(PEP), timeout, flowControl);
		MobileDriver.tap(this.txtQuestion(PEP), timeout, flowControl);
	}
	void chooseTypeAndFillInformation(Map<String,String> maeAccountData, String idType) {
		chooseIdType(idType)
		generateMykadNumber()
		fillInformationMAEAccount(maeAccountData)
		commonPage.tapContinueBtn()
	}
	void fillSuitabilityAssessment(Map<String, String> suitabilityMap, Map<String, String> questionList) {
		selectItemDropDownList(suitabilityMap.get(TITLE.toLowerCase()), suitabilityMap.get('value'))
		chooseRadionButtonValue(questionList)
		commonPage.tapNextBtn()
	}
	
	void fillInStepOneZestI(Map<String, String> personalDetails, int timeout = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		selectItemDropDownList(personalDetails.remove(TITLE.toLowerCase()), personalDetails.remove('titleValue'))
		MobileDriver.tap(commonPage.lblDynamic(personalDetails.remove(GENDER.toLowerCase())), timeout, flowControl)
		Map personalDetailsCopy = personalDetails.clone()
		selectItemDropDownList(RACE, personalDetailsCopy.remove(RACE))
		handlePEPQuestion(personalDetailsCopy.remove(PEP))
		delay(GlobalVariable.G_timeoutMiniShort)
		fillInformationMAEAccount(personalDetails)
		fillInformationMAEAccount(personalDetailsCopy)
		commonPage.tapNextBtn()
	}
	
	void fillInStepOneZestIPremierAccount(Map<String, String> personalDetails, int timeout = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		selectItemDropDownList(personalDetails.remove(TITLE.toLowerCase()), personalDetails.remove('titleValue'))
		MobileDriver.tap(commonPage.lblDynamic(personalDetails.remove(GENDER.toLowerCase())), timeout, flowControl)
		Map personalDetailsCopy = personalDetails.clone()
		selectItemDropDownList(RACE, personalDetailsCopy.remove(RACE.toLowerCase()))
		handlePEPQuestion(personalDetailsCopy.remove(PEP))
		delay(GlobalVariable.G_timeoutMiniShort)
		personalDetailsCopy.remove('valueSelect')
		fillInformationMAEAccount(personalDetailsCopy)
		commonPage.tapContinueBtn()
	}

	String generateRandomMobileNumber() {
		def now = new Date()
		def rand = new Random()

		// Start with either 19 or 17
		def randomRegionCode = rand.nextBoolean() ? "19" : "17"

		// Extract time components
		def cal = Calendar.instance
		cal.time = now

		def yearLastDigit = (cal.get(Calendar.YEAR) % 10).toString()
		def monthLastDigit = ((cal.get(Calendar.MONTH) + 1) % 10).toString()
		def date = String.format("%02d", cal.get(Calendar.DAY_OF_MONTH))
		def hour = String.format("%02d", cal.get(Calendar.HOUR_OF_DAY))
		def second = String.format("%02d", cal.get(Calendar.SECOND))

		def randomMobileNumber = randomRegionCode + yearLastDigit + monthLastDigit + date + hour + second

		return randomMobileNumber
	}

	void fillPersonalDetailsZestiNTB(Map<String, String> personalDetails, int timeout = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		Map personalDetailsCopy = personalDetails.clone()
		if (personalDetailsCopy.containsKey(TITLE)) {
			selectItemDropDownList(TITLE, personalDetailsCopy.remove(TITLE))
		}
		if (personalDetailsCopy.containsKey(GENDER)) {
			MobileDriver.tap(commonPage.lblDynamic(personalDetailsCopy.remove(GENDER)), timeout, flowControl)
		}
		if (personalDetailsCopy.containsKey(RACE)) {
			selectItemDropDownList(RACE, personalDetailsCopy.remove(RACE))
		}
		if (personalDetailsCopy.containsKey(PEP)) {
			handlePEPQuestion(personalDetailsCopy.remove(PEP))
		}
		delay(GlobalVariable.G_timeoutMiniShort)
		fillInformationMAEAccount(personalDetailsCopy)
	}

	void zestiMyKadScanning(int timeout = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		MobileDriver.tap(commonPage.lblDynamic('Front'), timeout, flowControl)
		commonPage.waitLoading()
		commonPage.tapNextBtn()
	}

	void generateAndFillInformationResidential(Map<String,String> residentialInfo, int timeout = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		fillResidentialInfo(residentialInfo);
		commonPage.tapContinueBtn()
	}

	void fillResidentialInfo(Map residentialInfo) {
		Map residentialInfoCopy = residentialInfo.clone()
		def filteredMap = residentialInfo.findAll { key, value -> key != 'state' }
		fillInformationMAEAccount(filteredMap)
		delay(GlobalVariable.G_timeoutMiniShort)
		commonPage.swipeByW3CAction(5, 1500, 5, 100)
		delay(GlobalVariable.G_timeoutMiniShort)

		if (residentialInfo.containsKey('state')) {
			selectItemDropDownList(STATE, residentialInfo['state'])
		}
	}

	void zestiNTBFillEmploymentDetail(Map<String,String> employmentDetails, int timeout = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		if (employmentDetails.containsKey(OCCUPATION)) {
			selectItemDropDownListWithSearchButton(OCCUPATION, employmentDetails[OCCUPATION]);
		}

		if (employmentDetails.containsKey(EMPLOYER_NAME)) {
			commonPage.setText(commonPage.txtSiblingGroup(EMPLOYER_NAME), employmentDetails.get(EMPLOYER_NAME), timeout, flowControl);
		}

		commonPage.swipeByW3CAction(5, 1500, 5, 1000)
		if (employmentDetails.containsKey(SECTOR)) {
			MobileDriver.tap(commonPage.lblDynamic(SECTOR), timeout, flowControl)
			selectItemDropDownListWithSearchButton(SECTOR, employmentDetails[SECTOR]);
		}


		if (employmentDetails.containsKey(EMPLOYMENT_TYPE)) {
			selectItemDropDownList(EMPLOYMENT_TYPE, employmentDetails[EMPLOYMENT_TYPE])
		}

		commonPage.swipeByW3CAction(5, 1500, 5, 1000)
		if (employmentDetails.containsKey(MONTHLY_INCOME)) {
			selectItemDropDownList(MONTHLY_INCOME, employmentDetails[MONTHLY_INCOME])
		}

		if (employmentDetails.containsKey(SOURCE_INCOME)) {
			selectItemDropDownList(SOURCE_INCOME, employmentDetails[SOURCE_INCOME])
		}
	}

	void verifyEmploymentDetailPage(List<String> occupation, String sector, List<String> employmentType, String occupationStudent,String occupationHouse,String monthlyIncome,
			int timeout = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		// Verify required elements are visible
		MobileDriver.verifyElementExist(commonPage.lblDynamic(ZESTI_TITLE), timeout, flowControl);
		MobileDriver.verifyElementVisible(commonPage.lblDynamic(NEXT), timeout);

		// Loop through each occupation and select it from the dropdown list
		for (int i = 0; i < occupation.size(); i++) {
			selectItemDropDownListWithSearchButton(OCCUPATION, occupation.get(i));
		}
		verifyElementExist(commonPage.lblDynamic(EMPLOYER_NAME), timeout, flowControl);
		verifyElementExist(commonPage.lblDynamic(EXAMPLE_NAME), timeout, flowControl);
		selectItemDropDownListWithSearchButton(SECTOR, sector);
		// Loop through each employee type and select it from the dropdown list
		for (int i = 0; i < employmentType.size(); i++) {
			selectItemDropDownList(EMPLOYMENT_TYPE, employmentType[i]);
		}

		selectItemDropDownListWithSearchButton(OCCUPATION, occupationStudent);
		MobileDriver.verifyElementVisible(commonPage.lblDynamic(UNEMPLOYED), timeout)
		MobileDriver.verifyElementVisible(commonPage.lblDynamic(OUT), timeout)
		selectItemDropDownListWithSearchButton(OCCUPATION, occupationHouse);
		MobileDriver.verifyElementVisible(commonPage.lblDynamic(UNEMPLOYED), timeout)
		MobileDriver.verifyElementVisible(commonPage.lblDynamic(OUT), timeout)
		selectItemDropDownList(MONTHLY_INCOME, monthlyIncome);
		delay(GlobalVariable.G_timeoutMiniShort);
		commonPage.swipeByW3CAction(5, 1500, 5, 100);
		selectItemDropDownList(SOURCE_INCOME, MALAYSIA);
	}

	void selectItemDropDownListWithSearchButton(String fieldName, String value, int timeout = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		if(value.isBlank() || value == ""){
			flowControl
		}
		commonPage.swipeToObject(imgSiblingGroup(fieldName), timeout, FailureHandling.OPTIONAL)
		Mobile.verifyElementExist(imgSiblingGroup(fieldName), timeout, FailureHandling.CONTINUE_ON_FAILURE)
		info("Tap on drop-down ${fieldName}")
		MobileDriver.tap(imgSiblingGroup(fieldName), timeout, flowControl)

		delay(GlobalVariable.G_timeoutMiniShort)
		waitForElementPresent(commonPage.imgNoDynamic(3), timeout, flowControl)
		MobileDriver.tap(commonPage.imgNoDynamic(3), timeout, flowControl)
		Mobile.setText(commonPage.txtEditNo(1),value, timeout, flowControl)
		delay(GlobalVariable.G_timeoutMiniShort)
		info("Search for ${value}")
		if(!(MobileDriver.waitForElementPresent(commonPage.lblNormalizeSpace(value), timeout, FailureHandling.OPTIONAL))){
			info("Scroll to ${value}")
			MobileDriver.scrollToText(value, FailureHandling.CONTINUE_ON_FAILURE)
		}
		info("Tap on ${value}")
		if(!verifyElementVisible(commonPage.lblDynamicNo(value,2), GlobalVariable.G_timeoutMiniShort, FailureHandling.CONTINUE_ON_FAILURE)) {
			MobileDriver.tap(commonPage.imgNoDynamic(1), timeout, flowControl)
		}
		else {
			MobileDriver.tap(commonPage.lblDynamicNo(value,2), timeout, flowControl)
			//first tap to out focus search input
			MobileDriver.tap(commonPage.lblDynamicNo(value,2), timeout, flowControl)
		}
	}

	void fillEmploymentDetail(Map<String,String> employmentDetails, int timeout = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		List<String> keyList = getKeyInMap(employmentDetails)
		int index;
		employmentDetails.each{key, value ->
			if (key.contains("Name")) {
				commonPage.setText(commonPage.txtSiblingGroup(key), employmentDetails.get(key), timeout, flowControl);
			} else {
				if(index >= keyList.size()/2) {
					delay(GlobalVariable.G_timeoutMiniShort)
					commonPage.swipeByW3CAction(5, 1500, 5, 100)
				}
				selectItemDropDownList(key, value);
			}
			index ++
		}
		commonPage.tapContinueBtn()
	}
	List<String> getKeyInMap(Map<String, String> mapData) {
		List<String> keyList = new ArrayList<>();
		for (Map.Entry<String, String> entry : mapData.entrySet()) {
			keyList.add(entry.getKey());
		}
		return keyList;
	}
	void fillBranchDetail(Map<String,String> branchDetails){
		branchDetails.each { key, value ->
			selectItemDropDownList(key, value)
		}

		if (waitForElementPresent(commonPage.lblDynamic(NEXT), GlobalVariable.G_timeoutMiniShort, FailureHandling.OPTIONAL)) {
			commonPage.tapNextBtn()
		} else {
			commonPage.tapContinueBtn()
		}
		
		delay(GlobalVariable.G_timeoutMiniShort)
	}

	void fillAdditionalIncomeDetails(Map<String, String> incomeDetails, int timeout = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		if (incomeDetails.containsKey(PRIMARY_INCOME)) {
			selectItemDropDownList(PRIMARY_INCOME, incomeDetails[PRIMARY_INCOME])
		}

		if (incomeDetails.containsKey('Primary source of wealth')) {
			commonPage.swipeToObject(imgSiblingGroup(PRIMARY_SOURCE_OF_WEALTH, 2), GlobalVariable.G_timeoutLong, FailureHandling.OPTIONAL)
			info("Tap on drop-down ${PRIMARY_SOURCE_OF_WEALTH}")
			Mobile.tap(imgSiblingGroup(PRIMARY_SOURCE_OF_WEALTH, 2), timeout, flowControl)
			MobileDriver.scrollToText(incomeDetails[PRIMARY_SOURCE_OF_WEALTH])
			Mobile.tap(commonPage.lblDynamic(incomeDetails[PRIMARY_SOURCE_OF_WEALTH]), timeout, flowControl)
			commonPage.tapDoneBtn()
		}
	}

	void goThroughDeclarationPage(String radioText,
			int timeout = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		// Verify and tap on radioText and AGREE_CONFIRM elements
		commonPage.scrollToText(radioText, 5, 1500, 5, 100)
		MobileDriver.verifyElementVisible(commonPage.lblDynamic(radioText), timeout);
		MobileDriver.tap(commonPage.lblDynamic(radioText), timeout, flowControl);
		'Tap Agree And Confirm'
		MobileDriver.tap(commonPage.lblDynamic(AGREE_AND_CONFIRM), GlobalVariable.G_timeoutMedium, flowControl)
	}

	void verifyZestiMykadNTBConfirmation(Map<String, String> personalDetails, Map<String, String> residentialDetails,
			Map<String, String> debitCardDeliveryAddr, Map<String, String> employmentDetails, Map<String, String> preferredBranchDetails,
			int timeout = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {

		Map<String, String> personalDetailsCopy = personalDetails.clone()
		if (!personalDetailsCopy.isEmpty() && personalDetailsCopy.containsKey(PEP)) {
			personalDetailsCopy[PEP] = 'Yes'
		}

		List<Map<String, String>> confimationMaps = [
			personalDetailsCopy,
			residentialDetails,
			debitCardDeliveryAddr,
			employmentDetails
		]
		if(!preferredBranchDetails.isEmpty()) {
			Map<String, String> preferredBranch = [BRANCH : preferredBranchDetails[BRANCH]]
			confimationMaps.add(preferredBranch)
		}
		for (Map<String, String> confirmationMap : confimationMaps) {
			verifyElements(confirmationMap.collect { entry -> entry.value }, commonPage.lblContainDynamicPath(), timeout)
			commonPage.swipeByW3CAction(5, 1500, 5, 200);
		}
	}

	void verifyEditZestiMykadNTBConfirmation(Map<String, String> edittedPersonalDetails, Map<String, String> edittedResidentialDetails, Map<String, String> edittedDebitCardDelivery,
			Map<String, String> edittedEmploymentDetails, Map<String, String> edittedPreferredBranchDetails, int timeout = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		'Verify edit personal details'
		if (!edittedPersonalDetails.isEmpty()) {
			Mobile.scrollToText(PERSONAL_DETAILS)
			MobileDriver.tap(commonPage.lblSiblingGroup(PERSONAL_DETAILS), GlobalVariable.G_timeoutMedium, flowControl)
			fillPersonalDetailsZestiNTB(edittedPersonalDetails)
			MobileDriver.tap(commonPage.btnDynamic(CONFIRM), timeout, flowControl)
		}

		'Verify edit residential details'
		if (!edittedResidentialDetails.isEmpty()) {
			Mobile.scrollToText(RESIDENTIAL_DETAILS)
			MobileDriver.tap(commonPage.lblSiblingGroup(RESIDENTIAL_DETAILS), GlobalVariable.G_timeoutMedium, flowControl)
			fillResidentialInfo(edittedResidentialDetails)
			MobileDriver.tap(commonPage.btnDynamic(CONFIRM), timeout, flowControl)
		}

		'Verify edit debit card delivery address'
		if (!edittedDebitCardDelivery.isEmpty()) {
			Mobile.scrollToText(DEBIT_CARD_DELIVERY_ADDRESS)
			MobileDriver.tap(commonPage.lblSiblingGroup(DEBIT_CARD_DELIVERY_ADDRESS), GlobalVariable.G_timeoutMedium, flowControl)
			fillResidentialInfo(edittedDebitCardDelivery)
			MobileDriver.tap(commonPage.btnDynamic(CONFIRM), timeout, flowControl)
		}

		'Verify edit employment details'
		if (!edittedEmploymentDetails.isEmpty()) {
			Mobile.scrollToText(EMPLOYMENT_DETAILS)
			MobileDriver.tap(commonPage.lblSiblingGroup(EMPLOYMENT_DETAILS), GlobalVariable.G_timeoutMedium, flowControl)
			zestiNTBFillEmploymentDetail(edittedEmploymentDetails)
			MobileDriver.tap(commonPage.btnDynamic(CONFIRM), timeout, flowControl)
		}

		'Verify edit prefer branch'
		if (!edittedPreferredBranchDetails.isEmpty()) {
			Mobile.scrollToText(PREFERRED_BRANCH_DETAILS)
			MobileDriver.tap(commonPage.lblSiblingGroup(PREFERRED_BRANCH_DETAILS), GlobalVariable.G_timeoutMedium, flowControl)
			editPreferredBranch(edittedPreferredBranchDetails)
			MobileDriver.tap(commonPage.btnDynamic(CONFIRM), timeout, flowControl)
		}

		scrollToTop()
		verifyZestiMykadNTBConfirmation(edittedPersonalDetails, edittedResidentialDetails, edittedDebitCardDelivery, edittedEmploymentDetails, edittedPreferredBranchDetails)
	}

	void verifyZestiConfirmation(Map<String, String> personalDetails, Map<String, String> residentialDetails,
			Map<String, String> employmentDetails, Map<String, String> additionalDetails, Map<String, String> additionalIncomeDetail,
			int timeout = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		Map<String, String> personalDetailsCopy = personalDetails.clone()
		if (!personalDetailsCopy.isEmpty() && personalDetailsCopy.containsKey(PEP)) {
			personalDetailsCopy[PEP] = 'Yes'
		}

		List<Map<String, String>> confimationMaps = [
			personalDetailsCopy,
			residentialDetails,
			employmentDetails,
			additionalDetails,
			additionalIncomeDetail
		]

		for (Map<String, String> confirmationMap : confimationMaps) {
			scrollToAndVerifyTextInList(confirmationMap.collect { entry -> entry.value }, commonPage.lblContainDynamicPath(), timeout)
		}
	}

	void verifyEditZestiPassportNTBConfirmation(Map<String, String> edittedPersonalDetails, Map<String, String> edittedResidentialDetails,
			Map<String, String> edittedEmploymentDetails, Map<String, String> edittedAdditionalDetails,
			int timeout = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		'Verify edit personal details'
		if (!edittedPersonalDetails.isEmpty()) {
			MobileDriver.scrollToText(PERSONAL_DETAILS)
			MobileDriver.tap(commonPage.lblSiblingGroup(PERSONAL_DETAILS), GlobalVariable.G_timeoutMedium, flowControl)
			fillPersonalDetailsZestiNTB(edittedPersonalDetails)
			MobileDriver.tap(commonPage.btnDynamic(CONFIRM), timeout, flowControl)
		}

		'Verify edit residential details'
		if (!edittedResidentialDetails.isEmpty()) {
			MobileDriver.scrollToText(RESIDENTIAL_DETAILS)
			MobileDriver.tap(commonPage.lblSiblingGroup(RESIDENTIAL_DETAILS), GlobalVariable.G_timeoutMedium, flowControl)
			fillResidentialInfo(edittedResidentialDetails)
			MobileDriver.tap(commonPage.btnDynamic(CONFIRM), timeout, flowControl)
		}

		'Verify edit employment details'
		if (!edittedEmploymentDetails.isEmpty()) {
			MobileDriver.scrollToText(EMPLOYMENT_DETAILS)
			MobileDriver.tap(commonPage.lblSiblingGroup(EMPLOYMENT_DETAILS), GlobalVariable.G_timeoutMedium, flowControl)
			zestiNTBFillEmploymentDetail(edittedEmploymentDetails)
			MobileDriver.tap(commonPage.btnDynamic(CONFIRM), timeout, flowControl)
		}

		'Verify edit additional details'
		if (!edittedAdditionalDetails.isEmpty()) {
			delay(GlobalVariable.G_timeoutMiniShort)
			MobileDriver.scrollToEnd();
			MobileDriver.tap(commonPage.lblSiblingGroup(ADDITIONAL_DETAILS), GlobalVariable.G_timeoutMedium, flowControl)
			edittedAdditionalDetails.each { key, value ->
				selectItemDropDownList(key, value)
			}
			MobileDriver.tap(commonPage.btnDynamic(CONFIRM), timeout, flowControl)
		}

		scrollToTop()
		verifyZestiConfirmation(edittedPersonalDetails, edittedResidentialDetails, edittedEmploymentDetails, edittedAdditionalDetails, [:])
	}

	void verifyEditZestiETBConfirmation(Map<String, String> edittedPersonalDetails, Map<String, String> edittedEmploymentDetails,
			Map<String, String> edittedAdditionalDetails, Map<String, String> edittedAdditionalIncomeDetails,
			int timeout = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		'Verify edit personal details'
		if (!edittedPersonalDetails.isEmpty()) {
			MobileDriver.scrollToText(PERSONAL_DETAILS)
			MobileDriver.tap(commonPage.lblSiblingGroup(PERSONAL_DETAILS), GlobalVariable.G_timeoutMedium, flowControl)
			fillResidentialInfo(edittedPersonalDetails)
			MobileDriver.tap(commonPage.btnDynamic(CONFIRM), timeout, flowControl)
		}

		'Verify edit employment details'
		if (!edittedEmploymentDetails.isEmpty()) {
			MobileDriver.scrollToText(EMPLOYMENT_DETAILS)
			MobileDriver.tap(commonPage.lblSiblingGroup(EMPLOYMENT_DETAILS), GlobalVariable.G_timeoutMedium, flowControl)
			zestiNTBFillEmploymentDetail(edittedEmploymentDetails)
			MobileDriver.tap(commonPage.btnDynamic(CONFIRM), timeout, flowControl)
		}

		'Verify edit additional income details'
		if (!edittedAdditionalIncomeDetails.isEmpty()) {
			MobileDriver.scrollDownToEnd()
			MobileDriver.tap(commonPage.lblSiblingGroup(ADDITIONAL_INCOME_DETAILS), GlobalVariable.G_timeoutMiniShort, FailureHandling.OPTIONAL)
			fillAdditionalIncomeDetails(edittedAdditionalIncomeDetails)
			MobileDriver.tap(commonPage.btnDynamic(CONFIRM), timeout, flowControl)
		}

		'Verify edit additional details'
		if (!edittedAdditionalDetails.isEmpty()) {
			MobileDriver.scrollToText(ADDITIONAL_DETAILS)
			MobileDriver.tap(commonPage.lblSiblingGroup(ADDITIONAL_DETAILS), GlobalVariable.G_timeoutMedium, flowControl)
			edittedAdditionalDetails.each { key, value ->
				selectItemDropDownList(key, value)
			}
			MobileDriver.tap(commonPage.btnDynamic(CONFIRM), timeout, flowControl)
		}

		scrollToTop()
		verifyZestiConfirmation([:], edittedPersonalDetails, edittedEmploymentDetails, edittedAdditionalDetails, edittedAdditionalIncomeDetails)
	}

	void editPreferredBranch(Map<String, String> preferredBranchDetails, int timeout = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		MobileDriver.tap(commonPage.lblDynamic(PREFER_ANOTHER_BRANCH), GlobalVariable.G_timeoutMedium, flowControl)
		preferredBranchDetails.each { key, value->
			selectItemDropDownList(key, value)
		}
	}

	void goThroughConfirmationPage(
			int timeout = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		delay(GlobalVariable.G_timeoutMiniShort)
		MobileDriver.waitForElementPresent(commonPage.lblDynamic(AGREE_AND_CONFIRM), timeout);
		'Tap Agree And Confirm'
		MobileDriver.tap(commonPage.lblDynamic(AGREE_AND_CONFIRM), GlobalVariable.G_timeoutMedium, flowControl)
	}
	void clickRequestOTP(int timeout = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		waitForElementPresent(commonPage.lblDynamic(REQUEST_OTP), timeout, flowControl)
		MobileDriver.tap(commonPage.btnDynamic(REQUEST_OTP), timeout, flowControl)
	}
	void verifyOtpPage(
			int timeout = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		//			MobileDriver.verifyElementVisible(commonPage.lblDynamic(ZESTI_TITLE), GlobalVariable.G_timeoutMiniShort);
		MobileDriver.verifyElementVisible(commonPage.lblContainDynamic("****"), timeout);
		clickRequestOTP()
		MobileDriver.verifyElementVisible(commonPage.lblContainDynamic(YOUR_OTP), timeout);
		String otp = commonPage.getText(commonPage.lblContainDynamic(YOUR_OTP), timeout, flowControl)

		String pinNo = otp.substring(Math.max(otp.length() - 6, 0));
		info("pinNo value: ${pinNo}");
		PAGE_OBJECT.enterPINNO(pinNo, flowControl)
		PAGE_OBJECT.tapTickBtn()
	}
	void fillPassportInformation(Map<String,String> passportData, Map<String, String> passportDate, int timeout = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		passportData.each{key ,value ->
			if(key.equals("Passport Number")) {
				commonPage.setText(commonPage.txtSiblingGroup(key), generatePassportNumber(), timeout, flowControl)
			}
			else {
				fillInformationMAEAccount(passportData)
			}
		}
		if(waitForElementPresent(this.txtSiblingGroup(PASSPORT_EXD),timeout)) {
			MobileDriver.tap(commonPage.lblDynamic(PASSPORT_EXD), timeout, flowControl)
			MobileDriver.tap(this.txtSiblingGroup(PASSPORT_EXD), timeout, flowControl)
		}
		chooseAfterYear()
		delay(GlobalVariable.G_timeoutMiniShort)
		commonPage.swipeByW3CAction(5, 1500, 5, 100);
		MobileDriver.tap(this.txtSiblingGroup(DATE_OF_BIRTH), timeout, flowControl)
		if(waitForElementPresent(this.txtSiblingGroup(DATE_OF_BIRTH),timeout)) {
			MobileDriver.tap(this.txtSiblingGroup(DATE_OF_BIRTH), timeout, flowControl)
		}
		MobileDriver.tap(commonPage.lblDynamic(commonPage.UIDonebtn), timeout, flowControl)

		commonPage.swipeToObject(imgSiblingGroup(NATIONALITY), GlobalVariable.G_timeoutLong, FailureHandling.OPTIONAL)
		Mobile.tap(imgSiblingGroup(NATIONALITY), timeout, flowControl)
		String nationality = passportDate.remove(NATIONALITY)
		int startY = Mobile.getDeviceHeight() - 100
		int endY = startY - 500
		commonPage.scrollToText(nationality, 100, startY, 100, endY, false, GlobalVariable.G_timeoutMiniShort)
		Mobile.tap(commonPage.lblDynamic(nationality), timeout, flowControl)
		MobileDriver.tap(commonPage.lblDynamic(commonPage.UIDonebtn), timeout, flowControl)
	}
	void chooseAfterYear(int timeout = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		int currentYear = LocalDate.now().getYear();
		MobileDriver.tap(commonPage.lblDynamic(currentYear.toString()), timeout, flowControl)
		MobileDriver.tap(commonPage.lblDynamic((currentYear + 1).toString()), timeout, flowControl)
		MobileDriver.tap(commonPage.lblDynamic(commonPage.UIDonebtn), timeout, flowControl)
	}
	void chooseTypeAndFillInformationPassport(Map<String,String> maeAccountData, Map<String, String> passportData, String idType) {
		chooseIdType(idType)
		fillPassportInformation(maeAccountData, passportData)

		if (waitForElementPresent(commonPage.lblDynamic(NEXT), GlobalVariable.G_timeoutMiniShort, FailureHandling.OPTIONAL)) {
			commonPage.tapNextBtn()
		} else {
			commonPage.tapContinueBtn()
		}
	}
	void verifySuccessPage(String successText,
			int timeout = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		MobileDriver.verifyElementVisible(commonPage.lblDynamic(successText), timeout);
	}

	void generateAndFillUsername(String usernamePrefix = 'u', int timeout = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		long timestamp = System.currentTimeMillis()
		String username = "${usernamePrefix}${timestamp}"
		commonPage.setText(commonPage.txtSiblingGroup("Let's create a Vcb2u username and password to access your account."), username, timeout, flowControl)
	}

	void verifyCreatePassword(String password, int timeout = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		commonPage.delay(GlobalVariable.G_timeoutMiniShort)
		commonPage.setText(commonPage.txtEditNo(1), password, timeout, flowControl)
		MobileDriver.tap(commonPage.lblDynamic(SHOW_PASSWORD), timeout, flowControl)
		commonPage.setText(commonPage.txtEditNo(2), password, timeout, flowControl)
		MobileDriver.tap(commonPage.lblDynamic(SHOW_PASSWORD), timeout, flowControl)

		MobileDriver.tap(commonPage.grpPrecedingSibling(SHOW_PASSWORD), timeout, flowControl)

		'Verify Show password button'
		String newPasswordTxt = MobileDriver.getText(commonPage.txtEditNo(1), timeout)
		BaseAssert.equals(password, newPasswordTxt, 'Verify password display correctly when tapping on Show password button')
		String confirmNewPasswordTxt = MobileDriver.getText(commonPage.txtEditNo(2), timeout)
		BaseAssert.equals(password, confirmNewPasswordTxt, 'Verify password display correctly when tapping on Show password button')
	}

	void uploadSecurityImage(int timeout = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		MobileDriver.tap(imgSiblingGroup('Upload a security image'), timeout, flowControl)
		MobileDriver.tap(commonPage.lblDynamic('TAKE PHOTO'), timeout, flowControl)
		MobileDriver.tap(imgSiblingGroup('Place your photo in the circle', 2), timeout, flowControl)
		delay(GlobalVariable.G_timeoutShort)
	}

	void enterSecurityPhrase(String securityPhrase, int timeout = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		commonPage.setText(commonPage.txtEditNo(1), securityPhrase, timeout, flowControl)
	}

	void setupSecurityQuestions(Map<String, String> securityQuestions, int timeout = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		String currentQuestionLabel = ""
		securityQuestions.each { key, value ->
			if (key.contains("answer")) {
				commonPage.setText(commonPage.txtSiblingGroup(currentQuestionLabel), value, timeout, flowControl)
			} else {
				currentQuestionLabel = key
				commonPage.swipeByW3CAction(5, 1500, 5, 1200)
				MobileDriver.tap(commonPage.lblDynamic(key), timeout, flowControl)
				delay(GlobalVariable.G_timeoutShort)
				MobileDriver.tap(commonPage.lblDynamic(key), timeout, flowControl)
				MobileDriver.tap(imgSiblingGroup(key), timeout, flowControl)
				commonPage.selectDropdownScrollOneLine(value, GlobalVariable.G_timeoutMiniShort)
				commonPage.tapDoneBtn()
			}
		}
	}
	
	/*-------------------------------------------------- Resume Flow -----------------------------------------------------*/	
	/*
	 * Resume Page
	 */
	void resumeNTB(String idNumber,int timeout = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		'Verify Page'
		MobileDriver.waitForElementPresent(commonPage.lblContainDynamic("Please enter your MyKad number to activate your Account "), GlobalVariable.G_timeoutMedium, flowControl)
		
		'Input Set Text'
		commonPage.setText(commonPage.txtSiblingGroup("MyKad number"), idNumber, timeout, flowControl)
		
		'Tap Continue'
		commonPage.tapContinueBtn()
	}
	
	/*
	 * Login for Resume Flow NTB
	 */
	public void signin(String username, String password, FailureHandling flowControl = defaultFailureHandling) {
		MobileDriver.delay(GlobalVariable.G_timeoutMiniShort)

		'Input Username'
		MobileDriver.setText(loginPage.txtUsername(), username, GlobalVariable.G_timeoutLong, flowControl)
		'Select Continue button'
		if(MobileDriver.waitForElementPresent(loginPage.btnContinue(), GlobalVariable.G_timeoutShort)) {
			MobileDriver.tap(loginPage.btnContinue(), GlobalVariable.G_timeoutLong, flowControl)
		}
		'Select Yes It is Mine button'
		if(Mobile.waitForElementPresent(loginPage.btnYesItsMine(), GlobalVariable.G_timeoutShort)) {
			MobileDriver.tap(loginPage.btnYesItsMine(), GlobalVariable.G_timeoutLong, flowControl)	
		}else{
			MobileDriver.tap(commonPage.lblContainDynamic(loginPage.UILblYesItsMine), GlobalVariable.G_timeoutLong, flowControl)
		}
		'Input Password Register'
		MobileDriver.setEncryptedText(loginPage.txtPasswordRegister(), password, GlobalVariable.G_timeoutLong, flowControl)
		'Select Continue button'
		MobileDriver.tap(loginPage.btnContinue(), GlobalVariable.G_timeoutLong, flowControl)
		Mobile.delay(GlobalVariable.G_timeoutMiniShort)
		'Check Customer Info Error Message exists'
		if(Mobile.waitForElementPresent(commonPage.lblDynamic(loginPage.UILblCustomerInfoErrorMsg), GlobalVariable.G_timeoutShort)){
			'Handle Exception'
			handleError('System Exceptiop:(App Issue) - ' + loginPage.UILblCustomerInfoErrorMsg, FailureHandling.STOP_ON_FAILURE)
		}
		'Wait for Proceed button present'
		if(MobileDriver.waitForElementPresent(loginPage.btnProceed(), GlobalVariable.G_timeoutShort)) {
			'Select Proceed button'
			MobileDriver.tap(loginPage.btnProceed(), GlobalVariable.G_timeoutLong, flowControl)
		}
	}
	
	/*
	 * Activation Page
	 */
	void activationPage(int timeout = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		'Verify Page'
		MobileDriver.waitForElementPresent(commonPage.lblContainDynamic("Account activation"), timeout, flowControl)
		
		'Take Screenshot'
		MobileDriver.takeScreenshot()
		
		'Tap Continue'
		commonPage.tapContinueBtn()
	}
	
	/*
	 * FPX Flow
	 */
	String ACTIVATE_ACCOUNT = "Activate your account now"
		
	List<String> fpxPage = [
		'Make a minimum transfer of RM10.00 from another bank account registered to your own name and MyKad number to your account to activate it',
		'Note: We will not activate your account if the money was transferred from an account that does not belong to you.',
		'In the event the first-party transfer fails, refund will be initiated within 7 business days.'
		]
		
	void fpxFlow(boolean runValidations = false, String bank, int timeout = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		'Verify Page'
		MobileDriver.delay(GlobalVariable.G_timeoutMiniShort)
		MobileDriver.waitForElementPresent(commonPage.lblContainDynamic(ACTIVATE_ACCOUNT), GlobalVariable.G_timeoutShort)
		if (runValidations) {
			verifyElements(fpxPage, commonPage.lblContainDynamicPath(), GlobalVariable.G_timeoutMedium, flowControl)
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
		applyCASAPage.tapConfirm()

		'Delay'
		MobileDriver.delay(10)
	}
	
}
