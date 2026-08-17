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
import com.vcb.utils.*
import java.time.LocalDate
import internal.GlobalVariable
import java.time.format.DateTimeFormatter
import com.vcb.utils.DateUtils.Periods
import com.vcb.assertions.*
import com.vcb.mobile.internal.pages.base.BasePage
import com.vcb.mobile.internal.pages.base.CommonPage

public abstract class ApplyPage extends BasePage{

	abstract TestObject lblCurrentStep(String stepNo)
	abstract TestObject rdProductType(String productType)
	abstract TestObject rdSurveyAnswer(String question, String value)
	abstract TestObject btnTick()
	abstract TestObject txtAmount()
	abstract TestObject btnConfirm()
	abstract TestObject lblBalanceAmount()
	abstract TestObject rdTermConditions(String text)
	abstract TestObject lblConfirmInfo(String confirmInfo, String value)

	CommonPage commonPage = getPage(CommonPage)
	StringUtils stringUtils = new StringUtils()
	DateUtils dateUtils = new DateUtils()
	BaseAssert Assert = new BaseAssert()
	String datePattern = "dd MMM yyyy"
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern(datePattern)

	String FixedDepositAccount = 'Fixed Deposit Account'
	String FDDescription = 'Kindly choose your Fixed Deposit product type.'
	String IslamicFDTitle = 'Islamic Fixed Deposit'
	String conventionalFD = "Vcb Conventional Fixed Deposit"
	String islamicFD = "Islamic Fixed Deposit"
	String GIAi = "General Investment Account-i (GIA-i)"
	String PNAi = "Prosperous Now! Account-i (PNA-i)"
	String UIDoneBtn = 'Done'
	String UIReject = 'Reject'
	String UIApprove = 'Approve'
	String MY_FINANCIAL_OBJECTIVE = 'My financial objective'
	//Insufficient Amount Status
	List<String> UIStaticInsufficientAmountStatus = ['Transfer failed']

	List<String> IslamicFDStatic = [
		'A Shariah-compliant certless Fixed Deposit that can be placed and withdrawn online',
		'Profit rates are fixed upfront and paid upon maturity'
	]
	List<String> GIAiFDStatic = [
		'A Shariah-compliant certless Fixed Deposit that can be placed and withdrawn online',
		'Profit rates are fixed upfront and paid upfront'
	]
	List<String> PNAiFDStatic = [
		'A Mudarabah Investment Account',
		'Profit sharing partnership between the Bank and GIA-i account holders'
	]
	List<String> ConventionalFDStatic = [
		'Earn fixed rate with fixed deposit that can be managed online',
		'Offer a fixed profit return upon maturity'
	]

	//Suitability assessment
	List<String> lstSurveyQuestions = [
		'I have dealt in securities and/or derivatives i.e. Shares, Unit Trust etc.',
		'I have relevant knowledge or experience to understand the risks associated with investment products',
		'I have past investment experience',
		'I understand the nature of the investment account product',
		'I understand the terms of the investment account product'
	]
	String saHeader = "Suitability assessment"
	String saDescription = "General Investment Account-i is a type of Mudarabah Investment Account, wehre your profit is tied to the performance of underlying assets."
	String saTootip = "These assessment questions serve to guide you in choosing a Mudarabah Investment Account that suits your investment objective(s), risk tolerance, financial profile and investment experience."
	String saNotes = [
		"1.  I have read and understand the Product Disclosure Sheet (PDS)  and other relevant documents of General Investment Account-i.",
		"2.   I acknowledge all information provided and disclosed by me is true, complete and accurate and I understand that this may affect my suitability assessment."
	]
	//general
	String UINext = 'Next'
	//Terms & Conditions
	String yesTnC = "Yes, I/we expressly agree to Vcb Group and/or Other Entities processing my/our personal data and to be contacted for promotional and marketing purposes."
	String noTnC = "No, I/we do not agree to Vcb Group and/or Other Entities processing my/our personal data or to be contacted for promotional and marketing purposes."
	List<String> lstTermsConditions = [
		"Terms & Conditions",
		"I have read the PIDM’s DIS brochure and understand this product is protected by PIDM up to RM 250,000 for each depositor.",
		"I agree with this Statement and accept the Terms & Conditions for this product.",
		yesTnC,
		noTnC,
		"I have read, agree and accept the terms of the The Vcb Group Personal Data Privacy Statement. For marketing of products and services by Vcb Group/Other Entities referred to in the PDPA Form for Individual Customers.",
		"With regards to promotional and marketing materials:"
	]

	/**
	 * Selects a tab by its name
	 * isVisible: true if need not swipe
	 * @return
	 */
	void selectTab(String tabName, boolean isVisible = false, FailureHandling flowControl = defaultFailureHandling) {
		delay(GlobalVariable.G_timeoutShort)
		if(isVisible) {
			MobileDriver.tap(commonPage.lblDynamic(tabName), GlobalVariable.G_timeoutLong, flowControl)
			MobileDriver.takeScreenshot()
			return
		}
		commonPage.selectTab(commonPage.frmHorizontalScrollNo(1), tabName, GlobalVariable.G_timeoutMedium, flowControl)
		MobileDriver.takeScreenshot()
	}

	/**
	 * create Fixed Deposit Account
	 * @return
	 */
	void createFixedDepositAccount(status) {
		Mobile.tap(commonPage.lblDynamic(FixedDepositAccount), GlobalVariable.G_timeoutLong)
		switch(status) {
			case 'islamic':
				'Verify static UI: description, islamicFD'
				verifyElements([FDDescription], commonPage.lblDynamicPath(), GlobalVariable.G_timeoutLong)
				verifyElements(IslamicFDStatic, commonPage.lblDynamicPath(), GlobalVariable.G_timeoutLong)
				Mobile.takeScreenshot()
				Mobile.tap(commonPage.lblNormalizeSpace(IslamicFDTitle), GlobalVariable.G_timeoutLong)
				Mobile.tap(commonPage.lblDynamic(UINext), GlobalVariable.G_timeoutLong)
		}
	}

	/**
	 * tapFixedDepositAccount
	 * @return
	 */
	void tapFixedDepositAccount() {
		Mobile.tap(commonPage.lblDynamic(FixedDepositAccount), GlobalVariable.G_timeoutLong)
	}

	/**
	 * tap Next Button
	 * @return
	 */
	void tapNext(FailureHandling flowControl = defaultFailureHandling) {
		if(Mobile.waitForElementPresent(commonPage.lblDynamic('Next'), GlobalVariable.G_timeoutMedium, FailureHandling.OPTIONAL)) {
			Mobile.verifyElementVisible(commonPage.lblDynamic('Next'), GlobalVariable.G_timeoutLong, flowControl)
		}
		Mobile.tap(commonPage.lblDynamic('Next'), GlobalVariable.G_timeoutLong)
	}

	/**
	 * Verify Current Step Title
	 * @return
	 */
	Boolean verifyCurrentStep(String stepNo, int timeout = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		Boolean isCurrentStep = Mobile.waitForElementPresent(this.lblCurrentStep(stepNo), timeout, FailureHandling.OPTIONAL) ? true : false
		return isCurrentStep
	}

	/**
	 * Verify Product Type List
	 * @return
	 */
	void verifyProductTypeList(int timeout = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		Mobile.verifyElementExist(commonPage.lblAnyText("Fixed Deposit Application"), timeout, FailureHandling.CONTINUE_ON_FAILURE)
		Mobile.verifyElementExist(commonPage.lblAnyText("Kindly choose your Fixed Deposit product type"), timeout, FailureHandling.CONTINUE_ON_FAILURE)

		List<String> products = [
			islamicFD,
			PNAi,
			conventionalFD,
			GIAi
		]
		'Iterate over each product type in the list: verify UI product'
		for(String productType : products) {
			'swipe to proudct type'
			commonPage.swipeToObject(rdProductType(productType), timeout, flowControl)
			verifyElementExist(rdProductType(productType), timeout, flowControl)
			commonPage.verifyElementExist(rdProductType(islamicFD), timeout, flowControl)
			commonPage.verifyElements(IslamicFDStatic, commonPage.lblDynamicPath(), GlobalVariable.G_timeoutLong)
			'Verify UI product'
			switch(productType) {
				case islamicFD:
					verifyElements(IslamicFDStatic, commonPage.lblDynamicPath(), GlobalVariable.G_timeoutLong)
					Mobile.takeScreenshot()
					break
				case conventionalFD:
					verifyElements(ConventionalFDStatic, commonPage.lblDynamicPath(), GlobalVariable.G_timeoutLong)
					Mobile.takeScreenshot()
					break
				case GIAi:
					verifyElements(GIAiFDStatic, commonPage.lblDynamicPath(), GlobalVariable.G_timeoutLong)
					Mobile.takeScreenshot()
					break
				case PNAi:
					verifyElements(PNAiFDStatic, commonPage.lblDynamicPath(), GlobalVariable.G_timeoutLong)
					Mobile.takeScreenshot()
					break
			}
		}
		int w = Mobile.getDeviceWidth()
		int h = Mobile.getDeviceHeight()

		int startX = (w*4/5) as int
		int startY = (h/2) as int
		int endY = (h/5) as int
		commonPage.swipe(startX, startY, startX, endY)
	}
	/**
	 * select FD base on Account Type
	 * @return
	 */
	void selectFDAccountType(String productType, int timeout = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		while(!(Mobile.verifyElementVisible(rdProductType(productType), timeout, FailureHandling.OPTIONAL))){
			int w = Mobile.getDeviceWidth()
			int h = Mobile.getDeviceHeight()

			int startX = (w*3/5) as int
			int startY = (h/2) as int
			int endY = (h/5) as int
			info("Swipt from (${startX.toString()}, ${startY.toString()}) to (${startX.toString()}, ${endY.toString()})")
			commonPage.swipe(startX, startY, startX, endY)
		}
		Mobile.tap(rdProductType(productType), GlobalVariable.G_timeoutLong, flowControl)
		'Verify radio proudct type is checked'
		Boolean isChecked = Mobile.getAttribute(rdProductType(productType), 'checked', timeout, flowControl)
		Mobile.verifyEqual(isChecked, true, flowControl)
	}

	/**
	 * verifyEnterDetailInfo: verify static page at detail page screen
	 * @return
	 */
	void verifyEnterDetailInfo(String productType, int timeout = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {

		switch(productType) {
			case islamicFD:
			case conventionalFD:
			case PNAi:
				Mobile.verifyElementExist(commonPage.lblNormalizeSpace(productType), timeout, FailureHandling.CONTINUE_ON_FAILURE)
				Mobile.verifyElementExist(commonPage.lblAnyText("Please fill in your details"), timeout, FailureHandling.CONTINUE_ON_FAILURE)
				Mobile.verifyElementExist(commonPage.lblSiblingDynamic("Email address"), timeout, FailureHandling.CONTINUE_ON_FAILURE)
				Mobile.verifyElementExist(commonPage.txtSiblingDynamic("Email address"), timeout, FailureHandling.CONTINUE_ON_FAILURE)
				Mobile.verifyElementExist(commonPage.lblSiblingDynamic("Address line 1"), timeout, FailureHandling.CONTINUE_ON_FAILURE)
				Mobile.verifyElementExist(commonPage.txtSiblingDynamic("Address line 1"), timeout, FailureHandling.CONTINUE_ON_FAILURE)
				Mobile.verifyElementExist(commonPage.lblSiblingDynamic("Address line 2"), timeout, FailureHandling.CONTINUE_ON_FAILURE)
				Mobile.verifyElementExist(commonPage.txtSiblingDynamic("Address line 2"), timeout, FailureHandling.CONTINUE_ON_FAILURE)
				Mobile.verifyElementExist(commonPage.lblSiblingDynamic("Address line 3 (optional)"), timeout, FailureHandling.CONTINUE_ON_FAILURE)
				Mobile.verifyElementExist(commonPage.txtSiblingDynamic("Address line 3 (optional)"), timeout, FailureHandling.CONTINUE_ON_FAILURE)
				Mobile.verifyElementExist(commonPage.lblSiblingDynamic("Postcode"), timeout, FailureHandling.CONTINUE_ON_FAILURE)
				Mobile.verifyElementExist(commonPage.txtSiblingDynamic("Postcode"), timeout, FailureHandling.CONTINUE_ON_FAILURE)
				Mobile.verifyElementExist(commonPage.lblSiblingDynamic("City"), timeout, FailureHandling.CONTINUE_ON_FAILURE)
				Mobile.verifyElementExist(commonPage.txtSiblingDynamic("City"), timeout, FailureHandling.CONTINUE_ON_FAILURE)
				Mobile.verifyElementExist(commonPage.lblSiblingDynamic("Country"), timeout, FailureHandling.CONTINUE_ON_FAILURE)
				Mobile.verifyElementExist(commonPage.imgSiblingGroup("Country"), timeout, FailureHandling.CONTINUE_ON_FAILURE)
				Mobile.verifyElementExist(commonPage.lblSiblingDynamic("State"), timeout, FailureHandling.CONTINUE_ON_FAILURE)
				Mobile.verifyElementExist(commonPage.imgSiblingGroup("State"), timeout, FailureHandling.CONTINUE_ON_FAILURE)
				break
			case GIAi:
				Mobile.verifyElementExist(commonPage.lblNormalizeSpace("Suitability assessment"), timeout, FailureHandling.CONTINUE_ON_FAILURE)
				Mobile.verifyElementExist(commonPage.lblAnyText(saDescription), timeout, FailureHandling.CONTINUE_ON_FAILURE)
				Mobile.verifyElementExist(commonPage.lblSiblingDynamic("My financial objective"), timeout, FailureHandling.CONTINUE_ON_FAILURE)
				Mobile.verifyElementExist(commonPage.imgSiblingGroup("My financial objective"), timeout, FailureHandling.CONTINUE_ON_FAILURE)
				for(String item : lstSurveyQuestions) {
					Mobile.verifyElementExist(commonPage.lblAnyText(item), timeout, FailureHandling.CONTINUE_ON_FAILURE)
					Mobile.verifyElementExist(rdSurveyAnswer(item, 'Yes'), timeout, FailureHandling.CONTINUE_ON_FAILURE)
					Mobile.verifyElementExist(rdSurveyAnswer(item, 'No'), timeout, FailureHandling.CONTINUE_ON_FAILURE)
				}
				break
		}
	}

	/**
	 * enterTextFieldValue: enter fieldName
	 * @return
	 */
	void enterTextFieldValue(String fieldName, String value, int timeout = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		if(!value.isBlank() || value != "") {
			commonPage.swipeToObject(commonPage.txtSiblingDynamic(fieldName), timeout, FailureHandling.OPTIONAL)
			Mobile.verifyElementExist(commonPage.lblSiblingDynamic(fieldName), timeout, FailureHandling.CONTINUE_ON_FAILURE)
			Mobile.verifyElementExist(commonPage.txtSiblingDynamic(fieldName), timeout, FailureHandling.CONTINUE_ON_FAILURE)
			Mobile.clearText(commonPage.txtSiblingDynamic(fieldName), timeout, FailureHandling.OPTIONAL)
			Mobile.setText(commonPage.txtSiblingDynamic(fieldName), value, timeout, FailureHandling.STOP_ON_FAILURE)
			Mobile.hideKeyboard(FailureHandling.OPTIONAL)
		}
	}

	/**
	 * verifyTextFieldInfo
	 * @return
	 */
	void verifyTextFieldInfo(String fieldName, String expValue, int timeout = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		String getValue = Mobile.getText(commonPage.txtSiblingDynamic(fieldName), timeout, flowControl)
		Mobile.verifyMatch(getValue, expValue, false, flowControl)
	}

	/**
	 * enterDepositAmount
	 * @return
	 */
	void enterDepositAmount(String amount, int timeout = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		Mobile.comment(amount)
		def depositAmount = amount.replaceAll('\\D+', '')
		Mobile.comment(depositAmount)
		def length = depositAmount.length()
		Mobile.comment(length.toString())
		Mobile.delay(2)
		'input each number in the list'
		for (def i = 0; i < length; i++) {
			def tapNo = depositAmount[i]
			Mobile.comment(tapNo.toString())
			Mobile.tap(commonPage.lblDynamic(tapNo), GlobalVariable.G_timeoutShort)
			Mobile.delay(2)
		}
		'Select Tick Button'
		MobileDriver.tap(commonPage.btnTick(), GlobalVariable.G_timeoutLong, flowControl)
	}

	/**
	 * verifyConfirmationInfo
	 * @return
	 */
	void verifyConfirmationInfo(String confirmInfo, String value, int timeout = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		commonPage.swipeToObject(commonPage.lblNormalizeSpace(value), timeout, FailureHandling.OPTIONAL)
		commonPage.verifyElementExist(lblConfirmInfo(confirmInfo, value), timeout, FailureHandling.CONTINUE_ON_FAILURE)
	}

	/**
	 * verifyTermsnConditions
	 * @return
	 */
	void verifyTermsnConditions(String FDAccountType, int timeout = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		'Verify UI each item in lstTermsConditions'
		for(String item : lstTermsConditions) {
			commonPage.verifyElementExist(commonPage.lblNormalizeSpace(item), timeout, flowControl)
		}
		commonPage.verifyElementExist(commonPage.lblNormalizeSpace(FDAccountType), timeout, flowControl)
	}

	/**
	 * verifyTermsnConditions
	 * @return
	 */
	void selectFinancialObjective(String fdAccountType = GIAi, String financialObjective, String stateObjective = '', int timeout = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling){
		if(fdAccountType == GIAi) {
			if(!(financialObjective.isBlank())|| financialObjective != "") {
				commonPage.selectDropdownSiblingGroupItem("My financial objective", financialObjective, timeout, flowControl)
				Mobile.tap(commonPage.lblDynamic(commonPage.UIDonebtn), GlobalVariable.G_timeoutLong)
			}
			if(financialObjective == "Others") {
				commonPage.enterTextSiblingGroup("State the objective", stateObjective, timeout, FailureHandling.STOP_ON_FAILURE)
			}
		}
	}

	/**
	 * setSuitabilityAssessment: input Suitability Assessment
	 * @return
	 */
	void setSuitabilityAssessment(String fdAccountType = GIAi, List<String> lstAnswers, int timeout = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		if(fdAccountType == GIAi) {
			'Verify UI Survey Question and tap Survey Questions'
			for(int i = 0; i < (lstSurveyQuestions).size(); i++) {
				Mobile.comment(lstSurveyQuestions[i])
				int w = Mobile.getDeviceWidth()
				int h = Mobile.getDeviceHeight()
				int startX = w/2 as int
				int startY = (h/2) as int
				int endY = (h/5) as int

				if (!(Mobile.verifyElementExist(rdSurveyAnswer(lstSurveyQuestions[i+1], lstAnswers[i+1]), timeout, FailureHandling.OPTIONAL))){
					info("Swipt from (${startX.toString()}, ${startY.toString()}) to (${startX.toString()}, ${endY.toString()})")
					commonPage.swipe(startX, startY, startX, endY)
				}

				Mobile.tap(rdSurveyAnswer(lstSurveyQuestions[i], lstAnswers[i]), timeout, flowControl)
			}
		}
	}

	/**
	 * enterUserDetail: input data in User Detai screen
	 * @return
	 */
	void enterUserDetail(String emailAddress, String address1, String address2,
			String address3, String postcode = '5600', String city = 'KUALA LUMPUR', String country = 'Malaysia',
			String state = 'WP Kuala Lumpur', int timeout = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		if(Mobile.verifyElementExist(commonPage.txtSiblingGroup("Email address"), GlobalVariable.G_timeoutMedium, FailureHandling.OPTIONAL)) {
			info("Enter Email Address")
			commonPage.enterTextSiblingGroup("Email address", emailAddress, timeout, flowControl)
			info("Enter Address 1")
			commonPage.enterTextSiblingGroup("Address line 1", address1, timeout, flowControl)
			info("Enter Address 2")
			commonPage.enterTextSiblingGroup("Address line 2", address2, timeout, flowControl)
			info("Enter Address 3 (optional)")
			commonPage.enterTextSiblingGroup("Address line 3 (optional)", address3, timeout, flowControl)
			info("Enter Postcode")
			commonPage.enterTextSiblingGroup("Postcode", postcode, timeout, flowControl)
			info("Enter City")
			commonPage.enterTextSiblingGroup("City", city, timeout, flowControl)
			info("Select Country")
			commonPage.selectDropdownSiblingGroupItem("Country", country, timeout, flowControl)
			info("Select State")
			commonPage.selectDropdownSiblingGroupItem("State", state, timeout, flowControl)
			'Tap on Next'
			commonPage.tapButton("Next", timeout, FailureHandling.STOP_ON_FAILURE)
		}
	}

	/**
	 * enterEmploymentDetail: input data in Employment Detail screen
	 * @return
	 */
	void enterEmploymentDetail(String employmentType, String occupation, String employerName, String sector,
			String monthlyIncome, String srcOfIncome, int timeout = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		if(Mobile.verifyElementExist(commonPage.imgSiblingGroup("Employment Type"), GlobalVariable.G_timeoutMedium, FailureHandling.OPTIONAL)) {
			info("Select Employment Type")
			commonPage.selectDropdownSiblingGroupItem("Employment Type", employmentType, timeout, flowControl)
			info("Select Occupation")
			commonPage.selectDropdownSiblingGroupItem("Occupation", occupation, timeout, flowControl)
			info("Enter Employer’s name")
			commonPage.enterTextSiblingGroup("Employer’s name", employerName, timeout, flowControl)
			info("Select Sector")
			commonPage.selectDropdownSiblingGroupItem("Sector", sector, timeout, flowControl)
			info("Select Monthly income")
			commonPage.selectDropdownSiblingGroupItem("Monthly income", monthlyIncome, timeout, flowControl)
			info("Select Source of income")
			commonPage.selectDropdownSiblingGroupItem("Source of income", srcOfIncome, timeout, flowControl)
			'Tap on Next'
			commonPage.tapButton("Next", timeout, FailureHandling.STOP_ON_FAILURE)
		}
	}

	/**
	 * selectAccountNumber
	 * @return
	 */
	void selectAccountNumber(String accountNo, int timeout = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling){
		commonPage.selectDropdownSiblingGroupItem("Account Number", accountNo, timeout, flowControl)
		commonPage.tapButton("Continue", timeout, FailureHandling.STOP_ON_FAILURE)
	}

	/**
	 * selectBankTransferFrom
	 * @return
	 */
	void selectBankTransferFrom(String bankTransferFrom, int timeout = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling){
		info("Select a bank to transfer from")
		commonPage.selectDropdownSiblingGroupItem("Select a bank to transfer from", bankTransferFrom, timeout, flowControl)
		info("Tap Continue")

		Mobile.delay(GlobalVariable.G_timeoutShort)

		commonPage.tapButton("Continue", timeout, flowControl)
	}

	/**
	 * enterFixedDepositDetail
	 * @return
	 */
	void enterFixedDepositDetail(String fdAccountType, String accountFrom, String campaign, String tenure,
			int timeout = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		info("Select a account transfer")
		commonPage.selectDropdownSiblingGroupItem("From Account", accountFrom, timeout, flowControl)
		
		if(Mobile.waitForElementPresent(commonPage.lblDynamic("Campaign"), GlobalVariable.G_timeoutShort, FailureHandling.OPTIONAL )) {
		info("Select Campaign")
		commonPage.selectDropdownSiblingGroupItem("Campaign", campaign, timeout, flowControl)
		}
		info("Select Tenure")
		commonPage.selectDropdownSiblingGroupItem("Tenure", tenure, timeout, flowControl)
	}

	/**
	 * getProfitSharingRatio only type GIAi
	 * @return
	 */
	def getProfitSharingRatio(String fdAccountType, int timeout = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling){
		String profitSharingRatio = ""
		if(fdAccountType == GIAi) {
			info("Get Profit Sharing Ratio")
			profitSharingRatio = Mobile.getText(commonPage.lblSiblingOneGroup("Profit Sharing Ratio"), timeout, flowControl)
			Mobile.comment(profitSharingRatio)
			commonPage.verifyElementExist(commonPage.lblAnyText("The rate displayed represents your share of the returns based on the profit sharing ratio above."), timeout, FailureHandling.CONTINUE_ON_FAILURE)
		}
		return profitSharingRatio
	}

	/**
	 * enterPaymentInfo: input data Payment Info screen
	 * @return
	 */
	void enterPaymentInfo(String profitPaymentFrequency, String profitPaymentMode, String maturityInstruction, String creditingAccount,
			int timeout = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		if (MobileDriver.waitForElementPresent(commonPage.lblDynamic("Profit payment frequency"), timeout, FailureHandling.OPTIONAL)) {
			info("Select Profit payment frequency")
			commonPage.selectDropdownSiblingGroupItem("Profit payment frequency", profitPaymentFrequency, timeout, flowControl)

			info("Select Profit payment mode")
			commonPage.selectDropdownSiblingGroupItem("Profit payment mode", profitPaymentMode, timeout, flowControl)

			info("Select Maturity instruction")
			commonPage.selectDropdownSiblingGroupItem("Maturity instruction", maturityInstruction, timeout, flowControl)

			if (commonPage.waitForElementPresent(commonPage.lblDynamic('Crediting Account'), GlobalVariable.G_timeoutShort, FailureHandling.OPTIONAL)) {
				info("Select Crediting Account")
				commonPage.selectDropdownSiblingGroupItem("Crediting Account", creditingAccount, timeout, flowControl)
			}
		}
		else {
			info("Select Profit payment frequency")
			commonPage.selectDropdownSiblingGroupItem("Interest payment frequency", profitPaymentFrequency, timeout, flowControl)

			info("Select Profit payment mode")
			commonPage.selectDropdownSiblingGroupItem("Interest payment mode", profitPaymentMode, timeout, flowControl)

			info("Select Maturity instruction")
			commonPage.selectDropdownSiblingGroupItem("Maturity instruction", maturityInstruction, timeout, flowControl)

			if (commonPage.waitForElementPresent(commonPage.lblDynamic('Crediting Account'), GlobalVariable.G_timeoutShort, FailureHandling.OPTIONAL)) {
				info("Select Crediting Account")
				commonPage.selectDropdownSiblingGroupItem("Crediting Account", creditingAccount, timeout, flowControl)
			}
		}


		info("Tap Next")
		commonPage.tapButton("Next", timeout, flowControl)
	}

	/**
	 * verifyTermsConditions: verify UI terms Conditions screen
	 * @return
	 */
	void verifyTermsConditions(String fdAccountType, int timeout = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		commonPage.verifyElementExist(commonPage.lblNormalizeSpace("Terms & Conditions"), timeout, flowControl)

		switch(fdAccountType) {
			case islamicFD:
				commonPage.verifyElementExist(commonPage.lblNormalizeSpace("I have read the PIDM’s DIS brochure and understand this product is protected by PIDM up to RM 250,000 for each depositor."), timeout, flowControl)
				commonPage.verifyElementExist(commonPage.lblNormalizeSpace("I agree with the Statement and accept the Terms & Conditions for this product."), timeout, flowControl)
				commonPage.verifyElementExist(commonPage.lblNormalizeSpace("Islamic Fixed Deposit Rates Apply"), timeout, FailureHandling.CONTINUE_ON_FAILURE)

				break
			case PNAi:
				commonPage.verifyElementExist(commonPage.lblNormalizeSpace("I have read the PIDM’s DIS brochure and understand this product is protected by PIDM up to RM 250,000 for each depositor."), timeout, flowControl)
				commonPage.verifyElementExist(commonPage.lblNormalizeSpace("I agree with the Statement and accept the Terms & Conditions for this product."), timeout, flowControl)
				commonPage.verifyElementExist(commonPage.lblNormalizeSpace("Prosperous Now! Account-i (PNA-i) Rates Applies"), timeout, flowControl)
				break
			case GIAi:
				commonPage.verifyElementExist(commonPage.lblNormalizeSpace("I have read the PIDM’s DIS brochure and understand that this product is not protected by Perbadanan Insuran Deposit Malaysia (PIDM)"), timeout, flowControl)
				commonPage.verifyElementExist(commonPage.lblNormalizeSpace("I have read and agreed with General Investment Account-i (GIA-i) Terms & Conditions, This Statement and Product Disclosure Sheet."), timeout, flowControl)
				commonPage.verifyElementExist(commonPage.lblNormalizeSpace("GIA-i Rates Applies"), timeout, FailureHandling.CONTINUE_ON_FAILURE)
				break
			case conventionalFD:
				commonPage.verifyElementExist(commonPage.lblNormalizeSpace("I have read the PIDM’s DIS brochure and understand this product is protected by PIDM up to RM 250,000 for each depositor."), timeout, flowControl)
				commonPage.verifyElementExist(commonPage.lblNormalizeSpace("I agree with the Terms & Conditions for this product."), timeout, FailureHandling.CONTINUE_ON_FAILURE)
				commonPage.verifyElementExist(commonPage.lblNormalizeSpace("Vcb Conventional Fixed Deposit Rates Apply"), timeout, FailureHandling.CONTINUE_ON_FAILURE)
				break
		}

		commonPage.verifyElementExist(commonPage.lblNormalizeSpace("I have read, agree and accept the terms of the The Vcb Group Personal Data Privacy Statement. For marketing of products and services by Vcb Group/Other Entities referred to in the PDPA Form for Individual Customers."), timeout, flowControl)
		commonPage.verifyElementExist(commonPage.lblNormalizeSpace("With regards to promotional and marketing materials:"), timeout, flowControl)
		commonPage.verifyElementExist(commonPage.lblNormalizeSpace(yesTnC), timeout, flowControl)
		commonPage.verifyElementExist(commonPage.lblNormalizeSpace(noTnC), timeout, flowControl)
	}

	/**
	 * checkTermAndCondition: agree TermAndCondition
	 * @return
	 */
	void checkTermAndCondition(Boolean isConfirmed = true, int timeout = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		if(isConfirmed) {
			info("Select Yes")
			commonPage.tap(rdTermConditions(yesTnC), timeout, flowControl)
		}
		else {
			info("Select No")
			commonPage.tap(rdTermConditions(noTnC), timeout, flowControl)
		}
		info("Tap Agree & Confirm")
		commonPage.tapButton("Agree & Confirm", timeout, FailureHandling.STOP_ON_FAILURE)
	}

	/**
	 * verifyConfirmInfo: Verify UI Confirm Info screen
	 * @return
	 */
	void verifyConfirmInfo(String fdAccountType, String amount, String accountFrom, String profitPaymentMode, String maturityInstruction,
			String creditingAccount, String campaign, String tenure, String profitPaymentFrequency, String profitSharingRatio,
			int timeout = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		info("Confirm Account Type")
		commonPage.verifyElementExist(commonPage.lblNormalizeSpace(fdAccountType), timeout, FailureHandling.CONTINUE_ON_FAILURE)
		info("Confirm Amount")
		commonPage.verifyElementExist(commonPage.lblNormalizeSpace("RM $amount"), timeout, FailureHandling.CONTINUE_ON_FAILURE)
		info("Confirm Transfer from")
		verifyConfirmationInfo('Transfer from', accountFrom, timeout, FailureHandling.CONTINUE_ON_FAILURE)

		info("Confirm Maturity instruction")
		verifyConfirmationInfo('Maturity instruction', maturityInstruction, timeout, FailureHandling.CONTINUE_ON_FAILURE)

		int months = getMaturityMonths(tenure)
		LocalDate currentDate = LocalDate.now()
		String formattedCurrentDate = currentDate.format(formatter)

		info("Confirm Transaction Date")
		verifyConfirmationInfo('Date', formattedCurrentDate, timeout, FailureHandling.CONTINUE_ON_FAILURE)

		String muturityDate = dateUtils.getDateAfter(Periods.MONTH, months, currentDate, datePattern)

		info("Confirm Maturity Date")
		verifyConfirmationInfo('Maturity Date', muturityDate, timeout, FailureHandling.OPTIONAL)

		if ((profitPaymentMode == 'Credit to Account') || (maturityInstruction == 'Credit to Account')) {
			info("Confirm Crediting account")
			verifyConfirmationInfo('Crediting account', creditingAccount, timeout, FailureHandling.CONTINUE_ON_FAILURE)
		}

		info("Confirm Campaign if type is Prosperous Now! Account-i (PNA-i)")
		if (fdAccountType != PNAi) {
			verifyConfirmationInfo('Campaign', campaign, timeout, FailureHandling.CONTINUE_ON_FAILURE)
		}

		info("Confirm Tenure")
		verifyConfirmationInfo('Tenure', tenure, timeout, FailureHandling.CONTINUE_ON_FAILURE)

		info("Confirm Payment frequency")
		verifyConfirmationInfo('Payment frequency', profitPaymentFrequency, timeout, FailureHandling.CONTINUE_ON_FAILURE)

		info("Confirm Payment mode")
		verifyConfirmationInfo('Payment mode', profitPaymentMode, timeout, FailureHandling.CONTINUE_ON_FAILURE)

		if (fdAccountType == GIAi) {
			info("Confirm Profit sharing ratio (Customer:Bank)")
			verifyConfirmationInfo('Profit sharing ratio (Customer:Bank)', profitSharingRatio, timeout, FailureHandling.CONTINUE_ON_FAILURE)
		}
	}

	/**
	 * submitMakePlacement: submit make Placement s2u (Approve, Reject)
	 * @return
	 */
	void submitMakePlacement(Boolean hasApproved = true, int timeout = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		if(hasApproved)
			commonPage.tapButton("Approve", timeout, flowControl)
		else
			commonPage.tapButton("Reject", timeout, flowControl)
		commonPage.waitLoading(GlobalVariable.G_timeoutLong, FailureHandling.OPTIONAL)
	}

	/**
	 * verifySubmitMakePlacement: Verify UI authorize screen S2u Make Placement
	 * @return
	 */
	void verifySubmitMakePlacement(String fdAccountType, String accountTransfer, String amount, String dateTime, int timeout = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling){
		info("Check Title")
		commonPage.verifyElementExist(commonPage.lblNormalizeSpace("Secure2u authorisation"), timeout, flowControl)
		info("Check Amount")
		commonPage.verifyElementExist(commonPage.lblNormalizeSpace("RM ${amount}"), timeout, flowControl)
		info("Check Account Transfer")
		commonPage.verifyElementExist(commonPage.lblNormalizeSpace(accountTransfer), timeout, flowControl)
		info("Check Account Type")
		commonPage.verifyElementExist(commonPage.lblContainDynamic(fdAccountType), timeout, flowControl)
		info("Verify Date Time")
		Assert.equals(true, stringUtils.verifyDateTimeFormat(dateTime), "Date follows the format DD MMM YYYY, HH:MM (A|P)M", FailureHandling.CONTINUE_ON_FAILURE)
	}

	/**
	 * verifyTransactionResult: Verify UI status screen
	 * @return
	 */
	void verifyTransactionResult(Boolean hasApproved = true, String fdAccountType, String accountTransfer, String amount, def getDatetime,
			int timeout = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		if(hasApproved) {
			info("Verify Successful Transaction")
			commonPage.verifyElementExist(commonPage.lblNormalizeSpace("Transaction successful"), timeout, flowControl)
			commonPage.verifyElementExist(commonPage.lblNormalizeSpace("RM ${amount}"), timeout, flowControl)
			commonPage.verifyElementExist(commonPage.lblNormalizeSpace(accountTransfer), timeout, flowControl)
			commonPage.verifyElementExist(commonPage.lblContainDynamic(fdAccountType), timeout, flowControl)
			commonPage.verifyElementExist(commonPage.lblNormalizeSpace(getDatetime), timeout, flowControl)
		}
		info("Tap Done to back to Accounts screen")
		commonPage.tapButton("Done", timeout, flowControl)
		commonPage.waitLoading(GlobalVariable.G_timeoutLong, FailureHandling.OPTIONAL)
	}

	/**
	 * verifyTransactionResult: Verify UI status screen
	 * @return
	 */
	void verifyTransactionResult(String status,
			int timeout = GlobalVariable.G_timeoutLong, FailureHandling flowControl = defaultFailureHandling) {
		switch(status) {
			case 'Approve':
				info("Verify Successful Transaction")
				MobileDriver.verifyElementExist(commonPage.lblNormalizeSpace("Transaction successful"), timeout, flowControl)
				break
			case 'Reject':
				info("Verify Rejected Transaction")
				verifySomeElementsMatchInList(['Authorisation failed', 'Transaction unsuccessful'], commonPage.lblContainDynamicPath(), GlobalVariable.G_timeoutShort)	
				break
			case 'Expired':
				info("Verify Expired Transaction")
				verifySomeElementsMatchInList(['Authorisation failed', 'Transaction unsuccessful'], commonPage.lblContainDynamicPath(), GlobalVariable.G_timeoutShort)
		}

		info("Tap Done to back to Accounts screen")
		commonPage.tapButton("Done", timeout, FailureHandling.STOP_ON_FAILURE)
	}
	/**
	 * getMaturityMonths: get Maturity Months
	 * @return
	 */
	def getMaturityMonths(String tenure) {
		def strArray = tenure.split(" ") as List<String>
		def getMaturityMonths = strArray[0]
		Mobile.comment(getMaturityMonths.toString())
		int maturityMonth = Integer.parseInt(getMaturityMonths)
		return maturityMonth
	}

	/**
	 * getTransactionDateTime: get Transaction Date Time
	 * @return
	 */
	def getTransactionDateTime(String dateTimeLabel= "Date & time", int timeout = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		def actValue = Mobile.getText(commonPage.lblSiblingDynamic(dateTimeLabel), timeout, flowControl)
		return actValue
	}

	/**
	 * selectApproval: approve s2u
	 * @return
	 */
	void selectApproval(String status, int timeout =GlobalVariable.G_timeoutLong, FailureHandling flowControl = defaultFailureHandling) {
		switch(status) {
			case 'Expired':
				'Wait until expired'
				MobileDriver.waitForElementPresent(commonPage.lblDynamic(UIDoneBtn), 60 + timeout, flowControl)
				break
			case 'Reject':

				'Select Reject'
				MobileDriver.tap(commonPage.lblDynamic(UIReject), 60 + timeout, flowControl)
				break
			default:
				'Select Approve'
				MobileDriver.tap(commonPage.lblDynamic(UIApprove), timeout, flowControl)
				info(status + ' is tapped.')
				break
		}
	}

	/**
	 * checkInsufficient: check Insufficient balance
	 * @return
	 */
	void checkInsufficient(String amount, int timeout =GlobalVariable.G_timeoutLong, FailureHandling flowControl = defaultFailureHandling) {
		'Enter Amount'
		commonPage.enterNO(amount, true, '', 0)

		String msg = MobileDriver.getText(commonPage.lblStartsWith('You have insufficient'), 3);

		Mobile.verifyEqual(msg, 'You have insufficient balance. Please change the amount or select another account to transfer from.')
	}

	/**
	 * enterEmailAddress: enter Email Address
	 * @return
	 */
	void enterEmailAddress(String emailAddress, int timeout = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		'Enter Email Address'
		commonPage.enterTextSiblingGroup("Email address", emailAddress, timeout, flowControl)
	}

	/**
	 * enterAddressLine: enter Address Line
	 * @return
	 */
	void enterAddressLine(String label, String addressLine, int timeout = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		'Enter Address Line'
		commonPage.enterTextSiblingGroup(label, addressLine, timeout, flowControl)
	}

	/**
	 * enterPostcode: enter Postcode
	 * @return
	 */
	void enterPostcode(String postcode, int timeout = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		'Enter Postcode'
		commonPage.enterTextSiblingGroup("Postcode", postcode, timeout, flowControl)
		info("Enter Postcode")
		commonPage.enterTextSiblingGroup("Postcode", postcode, timeout, flowControl)
	}

	/**
	 * enterCity: enter City
	 * @return
	 */
	void enterCity(String city, int timeout = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		'Enter City'
		commonPage.enterTextSiblingGroup("City", city, timeout, flowControl)
	}

	/**
	 * enterUserDetailInfo: enter User Detail Info
	 * @return
	 */
	void enterUserDetailInfo(String label, String value, int timeout = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		info("Enter for ${label}")
		'Enter City'
		commonPage.enterTextSiblingGroup(label, value, timeout, flowControl)
	}

	/**
	 * verifyApplyTransactionSuccessful: verify UI Apply Transaction Successful screen
	 * @return
	 */
	def verifyApplyTransactionSuccessful(String fdAccountType, String amount, int timeout = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		Mobile.verifyElementExist(commonPage.lblDynamic("Transaction successful"), timeout, FailureHandling.STOP_ON_FAILURE)
		GlobalVariable.G_referenceId = Mobile.getText(commonPage.lblSiblingDynamic("Reference ID"), timeout, FailureHandling.STOP_ON_FAILURE)
		GlobalVariable.G_certificateNumber = Mobile.getText(commonPage.lblSiblingDynamic("Certificate number"), timeout, FailureHandling.STOP_ON_FAILURE)
		'Get Transaction Amount'
		String transactionAmount = Mobile.getText(commonPage.lblSiblingDynamic("Amount"), timeout, FailureHandling.STOP_ON_FAILURE)
		Mobile.verifyMatch(transactionAmount.replace('RM ','').trim(), amount, false, FailureHandling.CONTINUE_ON_FAILURE)
		'Get Transaction Type'
		String transactionType = Mobile.getText(commonPage.lblSiblingDynamic("Transaction type"), timeout, FailureHandling.STOP_ON_FAILURE)
		Boolean isContain = transactionType.contains(fdAccountType)
		Assert.equals(true, isContain, 'Verify FD Account type', FailureHandling.CONTINUE_ON_FAILURE)
	}

	/**
	 * verifyApplyTransactionRejected: verify UI Apply Transaction Rejected screen
	 * @return
	 */
	def verifyApplyTransactionRejected(String fdAccountType, int timeout = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		Mobile.verifyElementExist(commonPage.lblDynamic("Authorisation failed"), timeout, FailureHandling.STOP_ON_FAILURE)
		Mobile.verifyElementExist(commonPage.lblDynamic("Your Secure Verification authorisation was rejected."), timeout, FailureHandling.STOP_ON_FAILURE)
		'Get Transaction Type'
		String transactionType = Mobile.getText(commonPage.lblSiblingDynamic("Transaction type"), timeout, FailureHandling.STOP_ON_FAILURE)
		Boolean isContain = transactionType.contains(fdAccountType)
		Assert.equals(true, isContain, 'Verify FD Account type', FailureHandling.CONTINUE_ON_FAILURE)
	}

	/**
	 * verifyApplyTransactionExpired: verify UI Apply Transaction Expired screen
	 * @return
	 */
	def verifyApplyTransactionExpired(String fdAccountType, int timeout = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		Mobile.waitForElementPresent(commonPage.btnDynamic("Done"), GlobalVariable.G_timeoutLong*2,FailureHandling.CONTINUE_ON_FAILURE)
		Mobile.verifyElementExist(commonPage.lblDynamic("Authorisation failed"), timeout, FailureHandling.STOP_ON_FAILURE)
		Mobile.verifyElementExist(commonPage.lblDynamic("Your Secure Verification authorisation has expired. Please perform the transaction again."), timeout, FailureHandling.STOP_ON_FAILURE)
		'Get Transaction Type'
		String transactionType = Mobile.getText(commonPage.lblSiblingDynamic("Transaction type"), timeout, FailureHandling.STOP_ON_FAILURE)
		Boolean isContain = transactionType.contains(fdAccountType)
		Assert.equals(true, isContain, 'Verify FD Account type', FailureHandling.CONTINUE_ON_FAILURE)
	}
	
	/**
	 * choose account type for every single page in apply
	 */
	void chooseAccountType(String accountType, int timeout = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		waitForElementPresent(commonPage.lblNormalizeSpace(accountType), timeout, flowControl)
		commonPage.tap(commonPage.lblNormalizeSpace(accountType), timeout, flowControl)
		commonPage.waitLoading()
	}
}
