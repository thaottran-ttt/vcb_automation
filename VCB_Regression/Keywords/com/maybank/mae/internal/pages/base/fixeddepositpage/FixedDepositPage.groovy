package com.maybank.mae.internal.pages.base.fixeddepositpage

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

import internal.GlobalVariable
import com.maybank.common.MobileDriver
import com.maybank.common.MobileDriver.ScrollDirectionEnum
import com.maybank.mae.internal.pages.base.BasePage
import com.maybank.mae.internal.pages.base.CommonPage
import com.maybank.mae.internal.pages.base.Secure2uAuthorisationPage
import com.maybank.mae.internal.pages.base.Secure2uAuthorisationPage.S2UStatusEnum
import com.maybank.common.Android
import org.openqa.selenium.WebElement as WebElement
import io.appium.java_client.AppiumDriver as AppiumDriver
import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory as MobileDriverFactory
import org.openqa.selenium.By
import com.maybank.utils.*
import com.kms.katalon.core.testobject.ConditionType
import com.maybank.assertions.*

public abstract class FixedDepositPage extends BasePage {
	CommonPage commonPage = getPage(CommonPage)

	abstract TestObject btnApplyNow()
	abstract TestObject lblCurrentStep(String stepNo)
	abstract TestObject rdProductType(String productType)
	abstract TestObject txtField(String fieldName)
	abstract TestObject rdValue(String question, String value)
	abstract TestObject lblTotalReposit()
	abstract TestObject cardItem(String accountType, String accountNo)
	abstract TestObject lbldepositAmount(String accountType, String accountNo)
	abstract TestObject lblNoCert(String accountType, String accountNo)
	abstract TestObject btnMakeAPlacement()
	abstract TestObject lblFDDetailInfo(String text)
	abstract TestObject divFDCard(String fdType, String fdAccountNo, String noCerts, String amount)
	abstract TestObject frmDashboardListView()

	BaseAssert Assert = new BaseAssert()

	String noFDSubTitle = "No Fixed Deposits Available"
	String noFDDesciiption = "You don't have any fixed deposit account at the moment."
	String suitabilityAssessment = "General Investment Account-i is a type of Mudarabah Investment Account, wehre your profit is tied to the performance of underlying assets."

	String conventionalFD = "Maybank Conventional Fixed Deposit"
	String islamicFD = "Islamic Fixed Deposit"
	String GIAi = "General Investment Account-i (GIA-i)"
	String PNAi = "Prosperous Now! Account-i (PNA-i)"
	String totalDeposits = 'Total Deposits'
	String next = 'Next'
	String fixedDeposits = 'Fixed Deposits'
	
	static final String UPLIFT_FD = 'Uplift FD'
	static final String CANCEL_UPLIFTMENT = 'Cancel Upliftment'
	
	Secure2uAuthorisationPage secure2uAuthorisationPage = BasePage.getPage(Secure2uAuthorisationPage)

	StringUtils stringUtils = new StringUtils()
	String monetaryPattern = 'RM ((\\d{1,3}(,\\d{3})+)|(\\d{1,3}))\\.\\d{0,2}'

	abstract List<List<String>> getDashboardListView()

	def verifyDepositFormat(List<List> lstFD, int timeout = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		for (List<List> i : lstFD) {
			String accountType = i[0]
			String accountNo = i[1]
			String noCerts = i[2]
			String amount = i[3]
			Mobile.comment(i.toString())

			'Verify Account Type is not empty'
			BaseAssert.notEquals(accountType, '', 'Verify Account Type')
			'Verify Certs is not empty'
			BaseAssert.notEquals(noCerts, '', 'Verify Account noCerts')
			'Verify Amount is money format'
			String money = StringUtils.extractMoney(amount)
			boolean resultFormatMoney = verifyFormatMoney([money])
			BaseAssert.equals(resultFormatMoney, true, 'Verify Format M')
			'Verify Account Number is account number format'
			BaseAssert.equals(verifyFormatAccountNumber([accountNo]), true, 'Verify Format AccountNumber')
		}
	}

	def getCardDetailInfo(String fieldName, int timeout = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		'Swipe to Card'
		commonPage.swipeToObject(commonPage.lblSiblingDynamic(fieldName), timeout, FailureHandling.OPTIONAL)
		'Verify card exist'
		Mobile.verifyElementExist(commonPage.lblSiblingDynamic(fieldName), timeout, flowControl)
		'Get Card Detail Info'
		String infoValue = Mobile.getText(commonPage.lblSiblingDynamic(fieldName), timeout, flowControl)
		Mobile.comment(infoValue)
		return infoValue
	}

	void verifyCardDetailInfo(String fieldName, String expValue, int timeout = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling){
		'Verify Card Detail Info'
		def infoValue = getCardDetailInfo(fieldName, timeout, flowControl)
		'Verify Card Detail Info'
		Assert.equals( expValue == infoValue, true, "Value ${expValue} and ${infoValue} is matched", flowControl)
	}

	def verifyDashboardCardInfo(String fdAccountType, String accountNo, String noCerts, String  amount, int timeout = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		info("Set Object")
		TestObject cardItem = divFDCard(fdAccountType, accountNo, noCerts, amount)
		'Swipe to Card item'
		commonPage.swipeToObject(cardItem, timeout, flowControl)
		info("verify Object")
		Mobile.verifyElementExist(cardItem, timeout, flowControl)
	}

	void verifyFDDetailDashboard(FailureHandling flowControl = defaultFailureHandling) {
		List<List<String>> listView = getDashboardListView()

		for(int i = 0; i< listView.size(); i++) {
			String fdType = listView.get(i).get(0)
			commonPage.swipeToTestObject(commonPage.lblDynamic(fdType))
			MobileDriver.tap(commonPage.lblDynamic(fdType), GlobalVariable.G_timeoutMedium, flowControl)

			commonPage.waitLoading()
			'Verify FD details box - green color'
			this.verifyFDCardDetailHeader(listView.get(i).get(0), listView.get(i).get(1), listView.get(i).get(3), GlobalVariable.G_timeoutLong, flowControl)

			'Verify buttons'
			this.verifyFDCardDetailButtons(GlobalVariable.G_timeoutMedium)

			'Back Icon'
			commonPage.tapBack()

			Mobile.delay(GlobalVariable.G_timeoutShort)
		}
	}

	def verifyDashboardCards(FailureHandling flowControl = defaultFailureHandling) {
		List<List<String>> listView = getDashboardListView()

		return listView
	}

	void verifyFDCardDetailHeader(String fdAccountType, String fdAccountNo, String fdAmount,int timeout = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		commonPage.verifyElementExist(commonPage.lblNormalizeSpace(fdAccountType?.trim()), timeout, flowControl)
		commonPage.verifyElementExist(commonPage.lblNormalizeSpace(fdAccountNo), timeout, flowControl)
		commonPage.verifyElementExist(commonPage.lblNormalizeSpace(fdAmount), timeout, flowControl)
	}

	void verifyFDCardDetailButtons(int timeout = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling){
		commonPage.verifyElementExist(commonPage.btnDynamic("Make a Placement"), timeout, flowControl)
		commonPage.verifyElementExist(commonPage.btnDynamic("Manage FD"), timeout, flowControl)
		commonPage.verifyElementExist(commonPage.btnDynamic("Uplift FD"), timeout, flowControl)
		commonPage.verifyElementExist(commonPage.btnDynamic("View Statement"), timeout, flowControl)
	}

	void verifyDFCardDetail(String certificate, String fdAmount, String tenure, String profitRate, String maturityDate,
			String profitPaymentFrequency, String profitPaymentMode, String primaryAccountHolder, int timeout = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {

		verifyElementExist(commonPage.imgSibling(certificate), timeout, flowControl)

		'Verify Pricipal amount'
		verifyCardDetailInfo("Principal amount", "RM ${fdAmount}", timeout, flowControl)

		'Verify Tenure'
		verifyCardDetailInfo("Tenure", tenure, timeout, flowControl)

		'Verify Profit rate'
		verifyCardDetailInfo("Profit rate", profitRate, timeout, flowControl)

		'Verify Manturity date'
		verifyCardDetailInfo("Maturity date", maturityDate, timeout, flowControl)

		'Verify Profit payment frequency'
		verifyCardDetailInfo("Profit payment frequency", profitPaymentFrequency, timeout, flowControl)

		'Verify Profit payment mode'
		verifyCardDetailInfo("Profit payment mode", profitPaymentMode, timeout, flowControl)

		'Verify Primary account holder'
		verifyCardDetailInfo("Primary account holder", primaryAccountHolder, timeout, flowControl)
	}

	void selectAccount(String label, String accountName, String AccountNo, int timeout = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		commonPage.swipeToObject(commonPage.imgSiblingGroup(label), timeout, FailureHandling.OPTIONAL)
		Mobile.verifyElementExist(commonPage.imgSiblingGroup(label), timeout, FailureHandling.CONTINUE_ON_FAILURE)
		info("Tap on drop-down ${label}")
		Mobile.delay(3)
		Mobile.tap(commonPage.imgSiblingGroup(label), timeout, flowControl)
		Mobile.delay(5)
		Mobile.tap(commonPage.imgSiblingGroup(label), timeout, FailureHandling.OPTIONAL)
		info("Search for ${accountName}\n${AccountNo}")
		'If account name and account number is not present, then scroll to account name and account number'
		while(!(Mobile.verifyElementExist(commonPage.lblNormalizeSpace("${accountName} ${AccountNo}"), timeout, FailureHandling.OPTIONAL))){
			info("Scroll to ${accountName}\n${AccountNo}")
			Mobile.scrollToText("${accountName}\n${AccountNo}", FailureHandling.OPTIONAL)
		}
		info("Tap on ${accountName}\n${AccountNo}")
		Mobile.tap(commonPage.lblNormalizeSpace("${accountName} ${AccountNo}"), timeout, flowControl)
		info("Tap Done to close drop-down")
		Mobile.tap(commonPage.lblDynamic(commonPage.UIDonebtn), GlobalVariable.G_timeoutLong)
	}

	void tapFDName(String fdName, FailureHandling flowControl = defaultFailureHandling) {
		'Scroll to FD Name'
		commonPage.scrollToText(fdName, 5, 1500, 5, 500, false, GlobalVariable.G_timeoutShort, flowControl)
		MobileDriver.tap(commonPage.lblDynamic(fdName), GlobalVariable.G_timeoutLong, flowControl)
	}

	void selectMenu(String menuFD, FailureHandling flowControl = defaultFailureHandling) {
		MobileDriver.tap(commonPage.lblNormalizeSpace(menuFD), GlobalVariable.G_timeoutLong, flowControl)
	}
	
	void selectFDType(String fdType, FailureHandling flowControl = defaultFailureHandling) {
		commonPage.scrollToTextDefault(fdType, [], false, GlobalVariable.G_timeoutMedium, flowControl, commonPage.lblContainDynamicPath())		
		MobileDriver.tap(commonPage.lblContainDynamic(fdType), GlobalVariable.G_timeoutLong, flowControl)
		
		'Wait to navigate'
		commonPage.waitLoading()
	}
	
	void cancelUpliftment(String fdType, FailureHandling flowControl = defaultFailureHandling) {
		if(!MobileDriver.waitForElementPresent(commonPage.lblNormalizeSpace(UPLIFT_FD), GlobalVariable.G_timeoutShort, FailureHandling.OPTIONAL)) {
			Mobile.scrollToText(CANCEL_UPLIFTMENT)
			'Cancel Uplift'
			MobileDriver.tap(commonPage.lblDynamic(CANCEL_UPLIFTMENT), GlobalVariable.G_timeoutLong, flowControl)
			'Wait Navigate'
			commonPage.waitLoading()
			'Confirm cancel Uplift'
			MobileDriver.tap(commonPage.lblDynamic(CANCEL_UPLIFTMENT), GlobalVariable.G_timeoutLong, flowControl)
			'Approve'
			secure2uAuthorisationPage.authorize(S2UStatusEnum.Approve.toString())
			'tap Done'
			commonPage.tapDoneBtn()
			
			if(!MobileDriver.waitForElementPresent(commonPage.lblNormalizeSpace(UPLIFT_FD), GlobalVariable.G_timeoutShort, FailureHandling.OPTIONAL)) {
				commonPage.tapBack()
				'wait next Page'
				commonPage.waitLoading()
				'Select FD again'
				this.selectFDType(fdType)
				'wait next Page'
				commonPage.waitLoading()
			}
		}
	}

	void manageFD(String fdName, String fdMenu, Map dropDownMap, FailureHandling flowControl = defaultFailureHandling) {
		'Wait for Total Deposits present'
		MobileDriver.waitForElementPresent(commonPage.lblDynamic(totalDeposits), GlobalVariable.G_timeoutLong, flowControl)

		'Select FD Name'
		this.tapFDName(fdName, flowControl)
		'Wait for FD Menu present'
		MobileDriver.waitForElementPresent(commonPage.lblNormalizeSpace(fdMenu), GlobalVariable.G_timeoutLong, flowControl)
		'Select FD Menu'
		this.selectMenu(fdMenu, flowControl)
		'Wait for Next button present'
		MobileDriver.waitForElementPresent(commonPage.lblDynamic(this.next), GlobalVariable.G_timeoutLong, flowControl)
		'Select Drop Down'
		dropDownMap.each { key, value ->
			commonPage.selectDropdownSiblingGroupItem(key, value, GlobalVariable.G_timeoutMedium)
		}

		'Click Next'
		MobileDriver.tap(commonPage.lblDynamic(this.next), GlobalVariable.G_timeoutLong, flowControl)
	}

	void detailFDBackToFD(FailureHandling flowControl = defaultFailureHandling) {
		MobileDriver.waitForElementPresent(commonPage.lblDynamic(fixedDeposits), GlobalVariable.G_timeoutLong, flowControl)
		'Back to FD'
		MobileDriver.tap(commonPage.imgNoDynamic(1), GlobalVariable.G_timeoutLong, flowControl)
	}

	void enterEmailAddress(String emailAddress, int timeout = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		'Enter Email Address'
		commonPage.enterTextSiblingGroup("Email address", emailAddress, timeout, flowControl)
	}
}
