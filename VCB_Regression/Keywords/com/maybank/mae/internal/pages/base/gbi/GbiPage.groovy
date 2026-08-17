package com.maybank.mae.internal.pages.base.gbi

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

import com.maybank.common.MobileDriver
import com.maybank.mae.internal.pages.base.BasePage
import com.maybank.mae.internal.pages.base.CommonPage
import com.maybank.mae.internal.pages.base.HomePage
import com.maybank.mae.internal.pages.base.LoginPage
import com.maybank.mae.internal.pages.base.OtpPage
import com.maybank.mae.internal.pages.base.Secure2uAuthorisationPage
import com.maybank.mae.internal.pages.base.zakat.ZakatPage

import com.maybank.mae.internal.pages.base.StatusScreenPage
import com.maybank.mae.internal.pages.base.accountpage.AccountsPage
import com.maybank.utils.StringUtils
import com.maybank.utils.TouchActionUtils

import com.maybank.mae.internal.pages.base.PushNotificationPage
import java.text.SimpleDateFormat

import io.appium.java_client.AppiumDriver as AppiumDriver
import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory

import org.openqa.selenium.By
import org.openqa.selenium.WebElement as WebElement


import internal.GlobalVariable


public abstract class GbiPage extends BasePage {
	
	abstract TestObject sliderInitialInvest()
	abstract TestObject sliderMonthlyInvest()
	abstract TestObject closeBtnTermsConditions()
	abstract TestObject closeBtnDelete()
	
	abstract boolean sliderToDistance(TestObject sliderPoint, int distance, FailureHandling flowControl)

	CommonPage commonPage = getPage(CommonPage)
	ZakatPage zakatPage = BasePage.getPage(ZakatPage)
	HomePage homePage = getPage(HomePage)
	AccountsPage accountsPage = BasePage.getPage(AccountsPage)
	StatusScreenPage statusScreenPage = BasePage.getPage(StatusScreenPage)
	Secure2uAuthorisationPage secure2uAuthorisationPage = BasePage.getPage(Secure2uAuthorisationPage)
	
	
	String GBIUI = "Goal-Based Investment"
	String RISKUI = "Understand Your Risk"
	String PROCEEDbtn = "Proceed"
	String SETUPbtn = "Set Up Now"
	String NEXTbtn = "Next"
	String DONEbtn = "Done"
	String SUBMITbtn = "Submit"
	String Iunderstandbtn = "I understand"
	String CONFIRMbtn = "Confirm"
	String TOPUPbtn = "Top Up Goal"
	
	void ViewAllGBIEntry(timeout = GlobalVariable.G_timeoutShort,flowControl = FailureHandling.CONTINUE_ON_FAILURE) {
		
		 if (Mobile.waitForElementPresent(homePage.btnViewAll(), timeout, FailureHandling.OPTIONAL)) {

			Mobile.tap(homePage.btnViewAll(), timeout, FailureHandling.STOP_ON_FAILURE)
			Mobile.scrollToText(GBIUI)
			Mobile.tap(commonPage.lblDynamic(GBIUI), timeout, FailureHandling.STOP_ON_FAILURE)
		} 
		
	}
	
	void firstTimeRegister (timeout = GlobalVariable.G_timeoutShort,flowControl = FailureHandling.CONTINUE_ON_FAILURE) {
		
		if (Mobile.waitForElementPresent(commonPage.lblContainDynamic("Create Goal"), timeout, FailureHandling.OPTIONAL)) {
			
			Mobile.tap(commonPage.lblContainDynamic("Create Goal"),GlobalVariable.G_timeoutShort,FailureHandling.CONTINUE_ON_FAILURE)
			}
	}
	
	void RiskPopUp(timeout = GlobalVariable.G_timeoutShort,flowControl = FailureHandling.CONTINUE_ON_FAILURE) {
		
		Mobile.waitForElementPresent(commonPage.lblDynamic(RISKUI), timeout, FailureHandling.OPTIONAL)
		Mobile.takeScreenshot()
		
		List<String> RISKPOPUP_TEXT = [
			"Understand Your Risk",
			"You may proceed or retake the suitability assessment to match your latest risk appetite.",
			"Proceed",
			"Retake"
			]
		"Verify Static Text"
		zakatPage.verifyTextList(RISKPOPUP_TEXT,timeout,flowControl)
		
		if (Mobile.waitForElementPresent(commonPage.lblDynamic(PROCEEDbtn), timeout, FailureHandling.OPTIONAL)) {
			Mobile.tap(commonPage.lblDynamic(PROCEEDbtn),timeout,FailureHandling.STOP_ON_FAILURE)
		}else {
			Mobile.tap(commonPage.lblDynamic(SETUPbtn),timeout,flowControl)
			
			this. suitabilityAssessment()
		}
	
	}
	
	void selectDropdownOption(String text, String value ,timeout = GlobalVariable.G_timeoutShort,flowControl = FailureHandling.CONTINUE_ON_FAILURE) {
		Mobile.tap(commonPage.lblSiblingOneGroup(text),timeout, flowControl)
		Mobile.tap(commonPage.lblContainDynamic(value),timeout,flowControl)
		Mobile.tap(commonPage.lblDynamic(DONEbtn),timeout,FailureHandling.STOP_ON_FAILURE)
	}
	
	String assessment1 ="Suitability Assessment"
	String LetBegin = "Let's Begin"
	String Question3 = "3. In the case of an emergency, how easily can you handle it without closing your investment for cash?"
	String Question4 = "4. How long can you leave most of your money in investments before you would need it (assuming you already have plans to deal with emergencies)?"
	String Question6 = "Portfolio E"
	String Question7 = "Unit Trust / Islamic Unit Trust"
	String Question8 = "Structured Product/ Islamic Structured Product (Principal Protected, i.e. Minimum Redemption Investment, Fund Linked and etc.)"
	String Question9 = "Structured Product / Islamic Structured Product (Non-Principal Protected, i.e. Equity Linked Investment, Dual Currency Linked Investment (DCI) / Islamic Dual Currency Linked Investment (IDCI) and etc.)"
	String Question10 = "Retail Bond / Sukuk"
	
	void suitabilityAssessment(timeout = GlobalVariable.G_timeoutShort,flowControl = FailureHandling.CONTINUE_ON_FAILURE) {
		
		if (Mobile.waitForElementPresent(commonPage.lblDynamic(assessment1), timeout, FailureHandling.OPTIONAL)){
			Mobile.takeScreenshot()
			Mobile.tap(commonPage.lblDynamic(LetBegin),timeout,FailureHandling.STOP_ON_FAILURE)
		}
		
		'Step 1 of 5'
		Mobile.waitForElementPresent(commonPage.lblDynamic("Step 1 of 5"), timeout, FailureHandling.OPTIONAL)
		Mobile.takeScreenshot()
		
		'Question 1'
		Mobile.tap(commonPage.grpContainsPrecedingSibling("to be very"),timeout, flowControl)
		commonPage.scrollToEnd()
		
		'Question 2'
		Mobile.tap(commonPage.grpContainsPrecedingSibling("year to year"),timeout, flowControl)
		Mobile.tap(commonPage.lblDynamic(NEXTbtn),timeout,FailureHandling.STOP_ON_FAILURE)
		
		'Step 2 of 5'
		Mobile.waitForElementPresent(commonPage.lblDynamic("Step 2 of 5"), timeout, FailureHandling.OPTIONAL)
		Mobile.takeScreenshot()
		
		'Question 3'
		Mobile.tap(commonPage.lblSiblingOneGroup(Question3),timeout, FailureHandling.STOP_ON_FAILURE)	
		Mobile.tap(commonPage.lblContainDynamic("It would be difficult"),timeout,flowControl)
		Mobile.tap(commonPage.lblContainDynamic("It would be very difficult"),timeout,flowControl)
		Mobile.tap(commonPage.lblDynamic(DONEbtn),timeout,FailureHandling.STOP_ON_FAILURE)
		
		'Question 4'
		this.selectDropdownOption(Question4, "6 to 10 years")
		commonPage.scrollToEnd()
		
		'Question 5'
		Mobile.tap(commonPage.grpContainsPrecedingSibling("(losses or gains)"),timeout, FailureHandling.STOP_ON_FAILURE)
		Mobile.tap(commonPage.lblDynamic(NEXTbtn),timeout,FailureHandling.STOP_ON_FAILURE)
		
		'Step 3 of 5'
		Mobile.waitForElementPresent(commonPage.lblDynamic("Step 3 of 5"), timeout, FailureHandling.OPTIONAL)
		Mobile.takeScreenshot()
		
		'Question 6'
		accountsPage.selectTab(Question6)
		Mobile.tap(commonPage.lblDynamic(Question6),timeout,FailureHandling.STOP_ON_FAILURE)
		Mobile.tap(commonPage.lblDynamic(NEXTbtn),timeout,FailureHandling.STOP_ON_FAILURE)
		
		'Step 4 of 5'
		Mobile.waitForElementPresent(commonPage.lblDynamic("Step 4 of 5"), timeout, FailureHandling.OPTIONAL)
		Mobile.takeScreenshot()
		
		'Question 7'
		this.selectDropdownOption(Question7, "3 to 6 years")
		
		'Question 8'
		this.selectDropdownOption(Question8, "3 to 6 years")
		
		'Question 9'
		this.selectDropdownOption(Question9, "3 to 6 years")
		commonPage.scrollToEnd()
		
		'Question 10'
		this.selectDropdownOption(Question10, "3 to 6 years")

		Mobile.tap(commonPage.lblDynamic(NEXTbtn),timeout,FailureHandling.STOP_ON_FAILURE)
		
		'Step 5 of 5'
		Mobile.waitForElementPresent(commonPage.lblDynamic("Step 5 of 5"), timeout, FailureHandling.OPTIONAL)
		Mobile.takeScreenshot()
		commonPage.scrollToEnd()
		
		Mobile.tap(commonPage.grpContainsPrecedingSibling("High-Net-Worth Investor"),timeout, FailureHandling.STOP_ON_FAILURE)
		Mobile.takeScreenshot()
		Mobile.tap(commonPage.lblDynamic(SUBMITbtn),timeout,FailureHandling.STOP_ON_FAILURE)
		
	}
	
	void RiskProfilePage(Boolean Retake , timeout = GlobalVariable.G_timeoutShort,flowControl = FailureHandling.CONTINUE_ON_FAILURE) {
		Mobile.waitForElementPresent(commonPage.lblContainDynamic("risk profile"), timeout, FailureHandling.OPTIONAL)
		Mobile.takeScreenshot()
		
		if (Retake == false) {
			Mobile.tap(commonPage.lblDynamic(NEXTbtn),timeout,FailureHandling.STOP_ON_FAILURE)
			} else {
				Mobile.tap(commonPage.lblDynamic("Retake Assessment"),timeout,FailureHandling.STOP_ON_FAILURE)
				
				this. suitabilityAssessment()
			}
	}
	
	void getStartedPage (Boolean RetakeAssessment, timeout = GlobalVariable.G_timeoutShort,flowControl = FailureHandling.CONTINUE_ON_FAILURE) {
		
		if (Mobile.waitForElementPresent(commonPage.lblContainDynamic("A beginner-friendly"), timeout, FailureHandling.OPTIONAL)) {
			
			Mobile.tap(commonPage.lblContainDynamic("Get Started"),GlobalVariable.G_timeoutShort,FailureHandling.CONTINUE_ON_FAILURE)
			}
			else {
				
			}
			
		if(Mobile.waitForElementPresent(commonPage.lblContainDynamic("No Goals"), timeout, FailureHandling.OPTIONAL)) {
			Mobile.tap(commonPage.lblContainDynamic("Create A"),GlobalVariable.G_timeoutShort,FailureHandling.CONTINUE_ON_FAILURE)
			this.RiskPopUp()
			this.RiskProfilePage(RetakeAssessment)
		}
	}
	
	void pickCategory(String Category, timeout = GlobalVariable.G_timeoutShort,flowControl = FailureHandling.CONTINUE_ON_FAILURE) {
		Mobile.waitForElementPresent(commonPage.lblContainDynamic(GBIUI), timeout, FailureHandling.OPTIONAL)
		Mobile.takeScreenshot()
		
		List<String> GBIPRODUCT_TEXT = [
			"Goal-Based Investment",
			"Pick a category to start",
			"Retirement",
			"For your future living expenses",
			"Education",
			"For you or your child's education aspirations",
			"Grow Wealth",
			"For you to grow your money"
			]
		"Verify Static Text"
		zakatPage.verifyTextList(GBIPRODUCT_TEXT,timeout,flowControl)
		
		switch (Category) {
			case "Retirement":
			Mobile.tap(commonPage.lblDynamic("Retirement"),timeout,FailureHandling.STOP_ON_FAILURE)
			break
			
			case "Education Myself":
			Mobile.tap(commonPage.lblDynamic("Education"),timeout,FailureHandling.STOP_ON_FAILURE)
			break
			
			case "Education Child":
			Mobile.tap(commonPage.lblDynamic("Education"),timeout,FailureHandling.STOP_ON_FAILURE)
			break
			
			case "Grow Wealth":
			Mobile.tap(commonPage.lblDynamic("Grow Wealth"),timeout,FailureHandling.STOP_ON_FAILURE)
			break
		}
		
	}
	
	void RetirementGoal(timeout = GlobalVariable.G_timeoutShort,flowControl = FailureHandling.CONTINUE_ON_FAILURE) {
		
		'Step 1 of 3'
		Mobile.waitForElementPresent(commonPage.lblContainDynamic("Step 1 of 3"), timeout, FailureHandling.OPTIONAL)
		List<String> RETIREMENT1_TEXT = [
			"Retirement Goal",
			"At what age would you like to retire?"
			]
		"Verify Static Text"
		zakatPage.verifyTextList(RETIREMENT1_TEXT,timeout,flowControl)
		
		Mobile.takeScreenshot()
		commonPage.tapTickBtn()
		
		'Step 2 of 3'
		Mobile.waitForElementPresent(commonPage.lblContainDynamic("Step 2 of 3"), timeout, FailureHandling.OPTIONAL)
		List<String> RETIREMENT2_TEXT = [
			"Retirement Goal",
			"How much is your estimated monthly expenses after retirement?"
			]
		"Verify Static Text"
		zakatPage.verifyTextList(RETIREMENT2_TEXT,timeout,flowControl)
		
		Mobile.takeScreenshot()
		Mobile.tap(commonPage.lblDynamic("0"),timeout,FailureHandling.OPTIONAL)
		commonPage.tapTickBtn()
		
		'Step 3 of 3'
		Mobile.waitForElementPresent(commonPage.lblContainDynamic("Step 3 of 3"), timeout, FailureHandling.OPTIONAL)
		List<String> RETIREMENT3_TEXT = [
			"Retirement Goal",
			"How many years do you expect your money to last after retirement?"
			]
		"Verify Static Text"
		zakatPage.verifyTextList(RETIREMENT3_TEXT,timeout,flowControl)
		
		Mobile.takeScreenshot()
		commonPage.tapTickBtn()
	}
	
	void growWealthGoal(timeout = GlobalVariable.G_timeoutShort,flowControl = FailureHandling.CONTINUE_ON_FAILURE) {
		
		'Step 1 of 2'
		Mobile.waitForElementPresent(commonPage.lblContainDynamic("Step 1 of 2"), timeout, FailureHandling.OPTIONAL)
		
		List<String> GROWWEALTH1_TEXT = [
			"Grow Wealth",
			"Please fill in the details",
			"How much money do you want to accumulate at the end of this goal?"
			]
		"Verify Static Text"
		zakatPage.verifyTextList(GROWWEALTH1_TEXT,timeout,flowControl)
		
		Mobile.takeScreenshot()
		commonPage.tapTickBtn()
		
		'Step 2 of 2'
		Mobile.waitForElementPresent(commonPage.lblContainDynamic("Step 2 of 2"), timeout, FailureHandling.OPTIONAL)
		
		List<String> GROWWEALTH2_TEXT = [
			"Grow Wealth",
			"Please fill in the details",
			"At what age would you like to withdraw this sum?"
			]
		"Verify Static Text"
		zakatPage.verifyTextList(GROWWEALTH2_TEXT,timeout,flowControl)
		
		Mobile.takeScreenshot()
		commonPage.tapTickBtn()
		
	}
	
	void educationMyselfGoal(timeout = GlobalVariable.G_timeoutShort,flowControl = FailureHandling.CONTINUE_ON_FAILURE) {
		
		'Step 1 of 3'
		Mobile.waitForElementPresent(commonPage.lblContainDynamic("Step 1 of 3"), timeout, FailureHandling.OPTIONAL)
		Mobile.tap(commonPage.grpContainsPrecedingSibling("Myself"),timeout, flowControl)
		
		List<String> MYSELFDUCATION1_TEXT = [
			"Education Fund",
			"I am creating this education fund for",
			"Myself",
			"My Child"
			]
		"Verify Static Text"
		zakatPage.verifyTextList(MYSELFDUCATION1_TEXT,timeout,flowControl)
		
		Mobile.takeScreenshot()
		Mobile.tap(commonPage.lblDynamic(NEXTbtn),timeout,FailureHandling.STOP_ON_FAILURE)
		
		'Step 2 of 3'
		Mobile.waitForElementPresent(commonPage.lblContainDynamic("Step 2 of 3"), timeout, FailureHandling.OPTIONAL)
		
		List<String> MYSELFDUCATION2_TEXT = [
			"Education Fund",
			"When do you need the fund by?",
			"I need the fund by"
			]
		"Verify Static Text"
		zakatPage.verifyTextList(MYSELFDUCATION2_TEXT,timeout,flowControl)
		
		Mobile.takeScreenshot()
		Mobile.tap(commonPage.lblDynamic(NEXTbtn),timeout,FailureHandling.STOP_ON_FAILURE)
		
		'Step 3 of 3'
		Mobile.waitForElementPresent(commonPage.lblContainDynamic("Step 3 of 3"), timeout, FailureHandling.OPTIONAL)
		
		List<String> MYSELFDUCATION3_TEXT = [
			"Education Fund",
			"When do you need the fund by?",
			"Intended education level",
			"Intended area of study",
			"Intended country of education",
			"Goal target amount"
			]
		"Verify Static Text"
		zakatPage.verifyTextList(MYSELFDUCATION3_TEXT,timeout,flowControl)
		
		Mobile.takeScreenshot()
		Mobile.tap(commonPage.lblDynamic(NEXTbtn),timeout,FailureHandling.STOP_ON_FAILURE)
	}
	
	void educationMyChildGoal(timeout = GlobalVariable.G_timeoutShort,flowControl = FailureHandling.CONTINUE_ON_FAILURE) {
		
		'Step 1 of 3'
		Mobile.waitForElementPresent(commonPage.lblContainDynamic("Step 1 of 3"), timeout, FailureHandling.OPTIONAL)
		Mobile.tap(commonPage.grpContainsPrecedingSibling("My Child"),timeout, flowControl)
		
		List<String> CHILDEDUCATION1_TEXT = [
			"Education Fund",
			"I am creating this education fund for",
			"Myself",
			"My Child"
			]
		"Verify Static Text"
		zakatPage.verifyTextList(CHILDEDUCATION1_TEXT,timeout,flowControl)
		
		Mobile.takeScreenshot()
		Mobile.tap(commonPage.lblDynamic(NEXTbtn),timeout,FailureHandling.STOP_ON_FAILURE)
		
		'Step 2 of 3'
		Mobile.waitForElementPresent(commonPage.lblContainDynamic("Step 2 of 3"), timeout, FailureHandling.OPTIONAL)
		
		List<String> CHILDEDUCATION2_TEXT = [
			"Education Fund",
			"Please enter your child's details",
			"What's the name of your child?",
			"How old is your child?"
			]
		"Verify Static Text"
		zakatPage.verifyTextList(CHILDEDUCATION2_TEXT,timeout,flowControl)
		
		Mobile.setText(commonPage.txtSiblingDynamic("What's the name of your child?"), "Auto", timeout,FailureHandling.STOP_ON_FAILURE)
		Mobile.takeScreenshot()
		Mobile.pressBack()
		Mobile.tap(commonPage.lblDynamic(NEXTbtn),timeout,FailureHandling.OPTIONAL)
		
		'Step 3 of 3'
		Mobile.waitForElementPresent(commonPage.lblContainDynamic("Step 3 of 3"), timeout, FailureHandling.OPTIONAL)
		Mobile.delay(5)
		List<String> CHILDEDUCATION3_TEXT = [
			"Education Fund",
			"When do you need the fund by?",
			"Intended education level",
			"Intended area of study",
			"Intended country of education",
			"Goal target amount"
			]
		"Verify Static Text"
		zakatPage.verifyTextList(CHILDEDUCATION3_TEXT,timeout,flowControl)
		
		Mobile.takeScreenshot()
		Mobile.tap(commonPage.lblDynamic(NEXTbtn),timeout,FailureHandling.OPTIONAL)
		
	}
	
	String SimulationUI = "Simulation"
	String STARTinvestingbtn = "Start Investing"
	
	void SimulationPage(timeout = GlobalVariable.G_timeoutShort,flowControl = FailureHandling.CONTINUE_ON_FAILURE) {
		
		Mobile.waitForElementPresent(commonPage.lblContainDynamic(SimulationUI), timeout, FailureHandling.OPTIONAL)
		commonPage.scrollToEnd()
		
		List<String> SIMULATION_GBITEXT = [
			"With the information you've provided, the above is the estimated outcome of your goal.",
			"How is this calculated?",
			"Initial Investment",
			"Lump sum amount you can deposit now.",
			"Monthly Investment",
			"Subsequent amount you can deposit every month.",
			"Risk level"
			]
		"Verify Static Text"
		zakatPage.verifyTextList(SIMULATION_GBITEXT,timeout,flowControl)
		
		'Slide Monthly Investment'
		this.sliderToDistance(this.sliderMonthlyInvest(), -800, FailureHandling.OPTIONAL)
		Mobile.takeScreenshot()
		
		Mobile.tap(commonPage.lblDynamic(STARTinvestingbtn),timeout,FailureHandling.STOP_ON_FAILURE)
	}
	
	String DISCLAIMERUI = "Disclaimer"
	
	
	void investmentDisclaimer(timeout = GlobalVariable.G_timeoutShort,flowControl = FailureHandling.CONTINUE_ON_FAILURE) {
		Mobile.waitForElementPresent(commonPage.lblContainDynamic(SimulationUI), timeout, FailureHandling.OPTIONAL)
		
		Mobile.tap(commonPage.lblDynamic(Iunderstandbtn),timeout,FailureHandling.STOP_ON_FAILURE)
	}
	
	String NEWFUNDbtn = "Add New Funds"
	String EXISTINGFUNDbtn = "View Available Portfolios"
	String CONFIRMPortfolios = "Confirm Portfolio"
	String MODELPortfolios = "Model Portfolios"
	String ISLAMICPortfolios = "Islamic Portfolio"
	String CONVPortfolios = "Conventional Portfolio"
	String FOCUSFUNDS = "FOCUS FUNDS"
	String Fund1 = "Ta European Equity Fund"
	String Fund2 = "Ta Global Technology Fund"
	String FundAllocation = "Fund Allocation"
	String EQUITYui = "Equity"
	
	void FundAllocationPage(String Fund, timeout = GlobalVariable.G_timeoutShort,flowControl = FailureHandling.CONTINUE_ON_FAILURE) {
		Mobile.waitForElementPresent(commonPage.lblContainDynamic(FundAllocation), timeout, FailureHandling.OPTIONAL)
		Mobile.takeScreenshot()
		
		List<String> FUNDALLOCATION1_GBITEXT = [
			"Fund Allocation",
			"Choose from our available portfolios or customise your own by picking your preferred funds."
			]
		"Verify Static Text"
		zakatPage.verifyTextList(FUNDALLOCATION1_GBITEXT,timeout,flowControl)
		
		if (Fund == "New") {
			Mobile.tap(commonPage.lblDynamic(NEWFUNDbtn),timeout,FailureHandling.STOP_ON_FAILURE)
			
			List<String> FUNDALLOCATION2_GBITEXT = [
				"Type to search",
				"Customise Your Portfolio",
				"You may pick up to 5 funds for your investment plan.",
				"ALL",
				"FOCUS FUNDS"
				]
			"Verify Static Text"
			zakatPage.verifyTextList(FUNDALLOCATION2_GBITEXT,timeout,flowControl)
			
			Mobile.waitForElementPresent(commonPage.lblContainDynamic(FOCUSFUNDS), timeout, FailureHandling.OPTIONAL)
			Mobile.tap(commonPage.lblContainDynamic("search"),timeout,FailureHandling.OPTIONAL)
			Mobile.setText(commonPage.lblContainDynamic("search"), "Ta", GlobalVariable.G_timeoutMedium,FailureHandling.OPTIONAL)
			Mobile.delay(5)
			Mobile.tap(commonPage.lblContainDynamic("ALL"),timeout,FailureHandling.OPTIONAL)
			
			TestObject obj1 = commonPage.lblContainDynamic(zakatPage.getLocatorKey(Fund1))
			zakatPage.scrollUntilElementVisibleAndTap(obj1,6,timeout,FailureHandling.OPTIONAL)
			
			Mobile.tap(commonPage.lblDynamic(FOCUSFUNDS),timeout,FailureHandling.STOP_ON_FAILURE)
			
			TestObject obj2 = commonPage.lblContainDynamic(zakatPage.getLocatorKey(Fund2))
			zakatPage.scrollUntilElementVisibleAndTap(obj2,6,timeout,FailureHandling.OPTIONAL)
			Mobile.takeScreenshot()
			
			Mobile.tap(commonPage.lblDynamic(DONEbtn),timeout,FailureHandling.STOP_ON_FAILURE)
			
			'Allocation Page'
			Mobile.waitForElementPresent(commonPage.lblContainDynamic(FundAllocation), timeout, FailureHandling.OPTIONAL)
			Mobile.tap(commonPage.lblDynamic(EQUITYui),timeout,FailureHandling.STOP_ON_FAILURE)
			commonPage.scrollToEnd()
			
			'Set Fund'
			Mobile.tap(commonPage.lblSiblingOneGroup(Fund1),timeout,FailureHandling.OPTIONAL)		
			Mobile.setText(commonPage.txtSiblingDynamic(Fund1), "50", timeout,FailureHandling.STOP_ON_FAILURE)
			
			Mobile.tap(commonPage.lblContainDynamic("Allocate"),timeout,FailureHandling.OPTIONAL)	
			Mobile.delay(5)
			Mobile.tap(commonPage.lblContainDynamic("Allocate"),timeout,FailureHandling.OPTIONAL)
			Mobile.setText(commonPage.txtSiblingDynamic(Fund2), "50", timeout,FailureHandling.STOP_ON_FAILURE)
			Mobile.tap(commonPage.lblDynamic(Fund2),timeout,FailureHandling.STOP_ON_FAILURE)
			Mobile.takeScreenshot()
			
			Mobile.tap(commonPage.lblDynamic(CONFIRMPortfolios),timeout,FailureHandling.STOP_ON_FAILURE)
						
		}else {
			Mobile.tap(commonPage.lblDynamic(EXISTINGFUNDbtn),timeout,FailureHandling.STOP_ON_FAILURE)
			
			Mobile.waitForElementPresent(commonPage.lblContainDynamic(MODELPortfolios), timeout, FailureHandling.OPTIONAL)
			Mobile.takeScreenshot()
			
			Mobile.tap(commonPage.lblDynamic(ISLAMICPortfolios),timeout,FailureHandling.STOP_ON_FAILURE)
			Mobile.tap(commonPage.lblDynamic(NEXTbtn),timeout,FailureHandling.STOP_ON_FAILURE)
			
			Mobile.tap(commonPage.lblDynamic(CONFIRMPortfolios),timeout,FailureHandling.STOP_ON_FAILURE)
			
		}
		
	}
	
	String DEPOSITPLANui = "Deposit Plan"
	String ONEDEPOSIT = "One-Time Deposit"
	String AUTODEPOui = "Auto-Deposit"
	
	void depositPlanPage(String DepoPlan,timeout = GlobalVariable.G_timeoutShort,flowControl = FailureHandling.CONTINUE_ON_FAILURE) {
		
		Mobile.waitForElementPresent(commonPage.lblContainDynamic(DEPOSITPLANui), timeout, FailureHandling.OPTIONAL)
		
		List<String> DEPOSITPLAN_GBITEXT = [
			"How would you like to start funding your goal?",
			"Auto-Deposit",
			"Make an initial investment and set up a monthly deposit so you never have to worry about forgetting. You can pause or cancel your auto-deposits at any time.",
			"One-Time Deposit",
			"Start with a lump sum initial investment and manually deposit more money later."
			]
		"Verify Static Text"
		zakatPage.verifyTextList(DEPOSITPLAN_GBITEXT,timeout,flowControl)
		
		if(DepoPlan == "OneTime") {
			Mobile.tap(commonPage.lblDynamic(ONEDEPOSIT),timeout,FailureHandling.STOP_ON_FAILURE)
		}else {
			Mobile.tap(commonPage.lblDynamic(AUTODEPOui),timeout,FailureHandling.STOP_ON_FAILURE)
		}
		
		Mobile.takeScreenshot()
		Mobile.tap(commonPage.lblDynamic(NEXTbtn),timeout,FailureHandling.STOP_ON_FAILURE)
	
	}
	
	void autoDepositPage(timeout = GlobalVariable.G_timeoutShort,flowControl = FailureHandling.CONTINUE_ON_FAILURE) {
		
		if(Mobile.waitForElementPresent(commonPage.lblContainDynamic(NEXTbtn), timeout, FailureHandling.OPTIONAL)) {
			Mobile.tap(commonPage.lblDynamic(NEXTbtn),timeout,FailureHandling.STOP_ON_FAILURE)
		}
		
		Mobile.waitForElementPresent(commonPage.lblContainDynamic(DEPOSITPLANui), timeout, FailureHandling.OPTIONAL)
		Mobile.takeScreenshot()
		Mobile.tap(commonPage.lblDynamic(NEXTbtn),timeout,FailureHandling.STOP_ON_FAILURE)
		
	}
	
	void oneTimeDepositPage(timeout = GlobalVariable.G_timeoutShort,flowControl = FailureHandling.CONTINUE_ON_FAILURE) {
		
		if(Mobile.waitForElementPresent(commonPage.lblContainDynamic(NEXTbtn), timeout, FailureHandling.OPTIONAL)) {
			Mobile.tap(commonPage.lblDynamic(NEXTbtn),timeout,FailureHandling.STOP_ON_FAILURE)
		}		
		
		List<String> OTPPLAN_GBITEXT = [
			"One-Time Deposit",
			"To confirm, here's how much you want to invest into your portfolio"
			]
		"Verify Static Text"
		zakatPage.verifyTextList(OTPPLAN_GBITEXT,timeout,flowControl)
		
		Mobile.waitForElementPresent(commonPage.lblContainDynamic(ONEDEPOSIT), timeout, FailureHandling.OPTIONAL)
		Mobile.takeScreenshot()
		commonPage.tapTickBtn()
		
	}
	
	void emailPage(timeout = GlobalVariable.G_timeoutShort,flowControl = FailureHandling.CONTINUE_ON_FAILURE) {
		Mobile.waitForElementPresent(commonPage.lblContainDynamic("email"), timeout, FailureHandling.OPTIONAL)
		
		List<String> EMAILPAGE_GBITEXT = [
			"Please ensure your email address is correct and current.",
			"We'll send you an email confirmation once you've completed your transaction."
			]
		"Verify Static Text"
		zakatPage.verifyTextList(EMAILPAGE_GBITEXT,timeout,flowControl)
		
		Mobile.setText(commonPage.txtEditNo(1), "abc@mail.com", timeout,FailureHandling.STOP_ON_FAILURE)
		Mobile.pressBack() 
		Mobile.takeScreenshot()
		Mobile.tap(commonPage.lblDynamic(NEXTbtn),timeout,FailureHandling.STOP_ON_FAILURE)
	}
	
	
	String CREATEGOALui = "Create Goal"
	
	void createConfirmation(String category, timeout = GlobalVariable.G_timeoutShort,flowControl = FailureHandling.CONTINUE_ON_FAILURE) {
		Mobile.waitForElementPresent(commonPage.lblContainDynamic(CREATEGOALui), timeout, FailureHandling.OPTIONAL)
		
		switch (category) {
			case "Retirement":
			Mobile.verifyElementExist(commonPage.lblContainDynamic("My Retirement Goal"),timeout, flowControl)
			break
			
			case "Education Myself":
			Mobile.verifyElementExist(commonPage.lblContainDynamic("Education Goal"),timeout, flowControl)
			break
			
			case "Education Child":
			Mobile.verifyElementExist(commonPage.lblContainDynamic("Auto's"),timeout, flowControl)
			break
			
			case "Grow Wealth":
			Mobile.verifyElementExist(commonPage.lblContainDynamic("Grow My Wealth"),timeout, flowControl)
			break
		}
		commonPage.scrollToEnd()
		
		List<String> CONFIRMATION_CREATEGBI = [
			"Initial investment",
			"One-off sales charge",
			"Email",
			"I have read the prospectus and fully understood its contents.",
			"I agree to the Terms & Conditions."
			]
		"Verify Static Text"
		zakatPage.verifyTextList(CONFIRMATION_CREATEGBI,timeout,flowControl)
		
		
		if(Mobile.waitForElementPresent(commonPage.lblContainDynamic("Send me"), timeout, FailureHandling.OPTIONAL)) {
			Mobile.tap(commonPage.grpContainsPrecedingSibling("Send me"),timeout, FailureHandling.OPTIONAL)
		}
		Mobile.tap(commonPage.grpContainsPrecedingSibling("I have read"),timeout, flowControl)
		Mobile.tap(commonPage.grpContainsPrecedingSibling("I agree"),timeout, flowControl)
		
		Mobile.tap(commonPage.lblDynamic("I agree to the Terms & Conditions."),timeout, flowControl)
		Mobile.tap(this.closeBtnTermsConditions(),timeout, flowControl)
		Mobile.takeScreenshot()
		
		Mobile.tap(commonPage.lblDynamic(CONFIRMbtn),timeout, flowControl)	
	}
	
	String CONGRATS = "Congratulations!"
	List<String> CONGRATSPAGE = [
		"Congratulations!"
	]
	
	void createAcknowledgementPage(timeout = GlobalVariable.G_timeoutShort,flowControl = FailureHandling.CONTINUE_ON_FAILURE) {
		Mobile.waitForElementPresent(commonPage.lblContainDynamic(CREATEGOALui), timeout, FailureHandling.OPTIONAL)
		Mobile.takeScreenshot()
		
		"Verify Static Text"
		zakatPage.verifyTextList(CONGRATSPAGE,timeout,flowControl)
		commonPage.scrollToEnd()
		Mobile.takeScreenshot()
		
		Mobile.verifyElementExist(commonPage.lblContainDynamic("Create Goal"),timeout, flowControl)
		zakatPage.verifyRefID()
	}
	
	void verifyCreatedGoal(String category, timeout = GlobalVariable.G_timeoutShort,flowControl = FailureHandling.CONTINUE_ON_FAILURE) {
		
		Mobile.waitForElementPresent(commonPage.lblContainDynamic(GBIUI), timeout, FailureHandling.OPTIONAL)
		
		switch (category) {
			case "Retirement":
			Mobile.tap(commonPage.lblDynamic("My Retirement Goal"),timeout, flowControl)
			break
			case "Education Myself":
			Mobile.tap(commonPage.lblDynamic("My Education Goal"),timeout, flowControl)
			break
			case "Education Child":
			Mobile.tap(commonPage.lblDynamic("My Retirement Goal"),timeout, flowControl)
			break
			case "Grow Wealth":
			Mobile.tap(commonPage.lblDynamic("Grow My Wealth"),timeout, flowControl)
			break
		}
		Mobile.verifyElementExist(commonPage.lblContainDynamic("please allow few days"),timeout, flowControl)
		Mobile.takeScreenshot()
		
	}
	
/**********************************************************************************( TOP UP GOAL )**********************************************************************************/
	
	void navigateTopUpGoal(String category, timeout = GlobalVariable.G_timeoutShort,flowControl = FailureHandling.CONTINUE_ON_FAILURE) {
		
		Mobile.waitForElementPresent(commonPage.lblContainDynamic(GBIUI), timeout, FailureHandling.OPTIONAL)
		
		switch (category) {
			case "Retirement":
			Mobile.tap(commonPage.lblDynamic("My Retirement Goal"),timeout, flowControl)
			break
			case "Education Myself":
			Mobile.tap(commonPage.lblDynamic("My Education Goal"),timeout, flowControl)
			break
			case "Education Child":
			commonPage.swipe(5, 1000, 5, 100)

			Mobile.tap(commonPage.lblContainDynamic("Terawih's"),timeout, flowControl)
			break
			case "Grow Wealth":
			Mobile.tap(commonPage.lblDynamic("Grow My Wealth"),timeout, flowControl)
			break
		}
		Mobile.verifyElementExist(commonPage.lblContainDynamic(TOPUPbtn),timeout, flowControl)
		Mobile.takeScreenshot()
		
	}
	
	void tapTopUpGoal(timeout = GlobalVariable.G_timeoutShort,flowControl = FailureHandling.CONTINUE_ON_FAILURE) {
		'Tap Top Up Goal'
		Mobile.tap(commonPage.lblDynamic(TOPUPbtn),timeout, flowControl)
		
		if (Mobile.waitForElementPresent(commonPage.lblDynamic("One-Time Deposit"), timeout, FailureHandling.OPTIONAL)){
			Mobile.takeScreenshot()
			Mobile.tap(commonPage.lblDynamic(PROCEEDbtn),timeout,FailureHandling.STOP_ON_FAILURE)
		}
	}
	
	void amountTopUpGoal(timeout = GlobalVariable.G_timeoutShort,flowControl = FailureHandling.CONTINUE_ON_FAILURE) {
		Mobile.waitForElementPresent(commonPage.lblContainDynamic(TOPUPbtn), timeout, FailureHandling.OPTIONAL)
		
		List<String> AMOUNT_TOPUPGBI = [
			"Top Up Goal",
			"How much would you like to deposit"
			]
		"Verify Static Text"
		zakatPage.verifyTextList(AMOUNT_TOPUPGBI,timeout,flowControl)
		
		Mobile.tap(commonPage.lblContainDynamic("Enter"),timeout, FailureHandling.STOP_ON_FAILURE)

		String number = "20100"

			for (int i = 0; i < number.length(); i++) {
			String digit = number[i]  
  
			Mobile.tap(commonPage.lblDynamic(digit), timeout, flowControl)
			}
			
		Mobile.takeScreenshot()
		commonPage.tapTickBtn()
		
		Mobile.waitForElementPresent(commonPage.lblContainDynamic("Fund"), timeout, FailureHandling.OPTIONAL)
		Mobile.tap(commonPage.lblDynamic(CONFIRMPortfolios),timeout, flowControl)
		
	}
	
	
	
	void confirmationTopUpGoal(timeout = GlobalVariable.G_timeoutShort,flowControl = FailureHandling.CONTINUE_ON_FAILURE) {
		Mobile.delay(5)
		Mobile.waitForElementPresent(commonPage.lblContainDynamic("Confirmation"), timeout, FailureHandling.OPTIONAL)
		commonPage.takeScreenshot()
		
		List<String> TOPUPConfirmPage = [
			"Date",
			"Investment amount",
			"Sales Charges"
		]
		"Verify Static Text"
		zakatPage.verifyTextList(TOPUPConfirmPage,timeout,flowControl)
		Mobile.tap(commonPage.lblDynamic("Top Up Now"),timeout, FailureHandling.STOP_ON_FAILURE)
		
	}
	
	
	
	void topupAcknowledgementPage(timeout = GlobalVariable.G_timeoutShort,flowControl = FailureHandling.CONTINUE_ON_FAILURE) {
		Mobile.waitForElementPresent(commonPage.lblContainDynamic(DONEbtn), timeout, FailureHandling.OPTIONAL)
		
		List<String> TOPUPPage = [
			"Transaction successful",
			"Please allow up to 2 business days for your deposit to be reflected.",
			"Top Up",
			"Share Receipt"
		]
		"Verify Static Text"
		zakatPage.verifyTextList(TOPUPPage,timeout,flowControl)
		
		zakatPage.verifyRefID()
		zakatPage.verifyDateTime()
		
		commonPage.takeScreenshot()
	}
	
	List<String> WithdrawPage = [
		"Transaction successful",
		"Please allow up to 2 business days for your transfer to be reflected.",
		"Withdraw Funds"
	]
	
	void withdrawAcknowledgementPage(timeout = GlobalVariable.G_timeoutShort,flowControl = FailureHandling.CONTINUE_ON_FAILURE) {
		Mobile.waitForElementPresent(commonPage.lblContainDynamic(DONEbtn), timeout, FailureHandling.OPTIONAL)
		
		"Verify Static Text"
		zakatPage.verifyTextList(WithdrawPage,timeout,flowControl)
		
		zakatPage.verifyRefID()
		zakatPage.verifyDateTime()
		
		commonPage.takeScreenshot()
	}
	
	void topUpScenarios(String password, String status, timeout = GlobalVariable.G_timeoutShort,flowControl = FailureHandling.CONTINUE_ON_FAILURE) {
		
		this.tapTopUpGoal()
		
		commonPage.checkEnterPassword(password)
		
		this.amountTopUpGoal()
		
		this.confirmationTopUpGoal()
		
		secure2uAuthorisationPage.authorize(status)
		
		if(status == "Approve") {
		this.topupAcknowledgementPage()
		}
		commonPage.takeScreenshot()
	}
	
	void tap3DotsBtn(timeout = GlobalVariable.G_timeoutShort,flowControl = FailureHandling.CONTINUE_ON_FAILURE) {
		MobileDriver.tap(commonPage.imgNoDynamic(2), GlobalVariable.G_timeoutMedium, FailureHandling.STOP_ON_FAILURE)
		
	}
	
	String REASONwriting = "We'd like to know why you're withdrawing from this goal"
	String REASON2 = "Withdrawing my capital"
	
	void withdrawScenarios(String password, String status, timeout = GlobalVariable.G_timeoutShort,flowControl = FailureHandling.CONTINUE_ON_FAILURE) {
		
		this.tap3DotsBtn()
		Mobile.tap(commonPage.lblDynamic("Withdraw Funds"),timeout, FailureHandling.STOP_ON_FAILURE)
		commonPage.checkEnterPassword(password)
		
		Mobile.waitForElementPresent(commonPage.lblContainDynamic("Withdraw Funds"), timeout, FailureHandling.OPTIONAL)
		commonPage.takeScreenshot()
		Mobile.tap(commonPage.lblDynamic(CONFIRMbtn),timeout, FailureHandling.STOP_ON_FAILURE)
		
		Mobile.waitForElementPresent(commonPage.lblContainDynamic("Withdraw Funds"), timeout, FailureHandling.OPTIONAL)
		
		String number = "20000"
		
					for (int i = 0; i < number.length(); i++) {
					String digit = number[i]
		  
					Mobile.tap(commonPage.lblDynamic(digit), timeout, flowControl)
					}
		commonPage.takeScreenshot()
		commonPage.tapTickBtn()
		
		commonPage.takeScreenshot()
		Mobile.tap(commonPage.lblDynamic(CONFIRMbtn),timeout, FailureHandling.STOP_ON_FAILURE)
		
		commonPage.selectDropdownSiblingGroupItem(REASONwriting, REASON2, timeout, flowControl)
		commonPage.takeScreenshot()
		Mobile.tap(commonPage.lblDynamic(NEXTbtn),timeout, FailureHandling.STOP_ON_FAILURE)
		
		Mobile.waitForElementPresent(commonPage.lblDynamic("Confirmation"), timeout, FailureHandling.OPTIONAL)
		commonPage.takeScreenshot()
		Mobile.tap(commonPage.lblDynamic("Withdraw Now"),timeout, FailureHandling.STOP_ON_FAILURE)
		
		secure2uAuthorisationPage.authorize(status)
		
		if(status == "Approve") {
		this.withdrawAcknowledgementPage()
		}
		commonPage.takeScreenshot()
		
	}
	
	void RemoveScenarios(String password,timeout = GlobalVariable.G_timeoutShort,flowControl = FailureHandling.CONTINUE_ON_FAILURE) {
		
		this.tap3DotsBtn()
		commonPage.takeScreenshot()
		Mobile.tap(commonPage.lblDynamic("Remove Goal"),timeout, FailureHandling.STOP_ON_FAILURE)
		commonPage.checkEnterPassword(password)
		
		Mobile.verifyElementExist(commonPage.lblContainDynamic("Delete This Goal"),timeout, flowControl)
		commonPage.takeScreenshot()
		MobileDriver.tap(this.closeBtnDelete(), GlobalVariable.G_timeoutMedium, FailureHandling.OPTIONAL)
	}
	
}