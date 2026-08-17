package com.maybank.mae.internal.pages.base

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import static org.assertj.core.api.Assertions.setExtractBareNamePropertyMethods
import static org.assertj.core.api.InstanceOfAssertFactories.STRING

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

import internal.GlobalVariable

public abstract class OnboardingPage extends BasePage{
	abstract TestObject btnNext()
	abstract TestObject btnGotIt()
	abstract TestObject btnAgree()
	abstract TestObject lblOurAntiMalware()
	abstract TestObject btnProceedAnyway()
	abstract TestObject btnAgreeAndProceed()
	abstract TestObject btnClose()
	abstract TestObject btnDisagree()

	static final String WHILE_USING_THE_APP = 'While using the app'
	static final String ALLOW = 'Allow'
	String UIDEVOPTIONS = 'DEV OPTIONS'
	String UIOpenYourAccount = 'Open your account online'
	
	static CommonPage commonPage = getPage(CommonPage)

	public void navigateToLogin(FailureHandling flowControl = defaultFailureHandling) {
		'Select Agree Button'
		if(MobileDriver.waitForElementPresent(this.btnAgree(), GlobalVariable.G_timeoutShort, FailureHandling.OPTIONAL)) {
			MobileDriver.tap(this.btnAgree(), GlobalVariable.G_timeoutShort, flowControl)
		}
		'Check Popup Permission'
		if(BasePage.runFromTestCloud()) {
			if(MobileDriver.waitForElementPresent(commonPage.lblDynamic(WHILE_USING_THE_APP), GlobalVariable.G_timeoutShort, FailureHandling.OPTIONAL)) {
				'Allow Permission'
				MobileDriver.tap(commonPage.lblDynamic(WHILE_USING_THE_APP), GlobalVariable.G_timeoutMedium, flowControl)
				MobileDriver.tap(commonPage.lblDynamic(WHILE_USING_THE_APP), GlobalVariable.G_timeoutMedium, flowControl)
				MobileDriver.tap(commonPage.lblDynamic(ALLOW), GlobalVariable.G_timeoutMedium, flowControl)
				MobileDriver.tap(commonPage.lblDynamic(ALLOW), GlobalVariable.G_timeoutMedium, flowControl)
			}
		}
		if(Mobile.waitForElementPresent(this.lblOurAntiMalware(), GlobalVariable.G_timeoutMedium, FailureHandling.OPTIONAL)) {
			String text = 'Proceed Anyway'
			Mobile.scrollToText(text)
			'Select Proceed Anyway Button'
			MobileDriver.tap(this.btnProceedAnyway(), GlobalVariable.G_timeoutLong, flowControl)
			'Select Agree And Proceed Button'
			MobileDriver.tap(this.btnAgreeAndProceed(), GlobalVariable.G_timeoutLong, flowControl)
		}
		'Select Close Button'
		if(MobileDriver.waitForElementPresent(this.btnClose(), GlobalVariable.G_timeoutMedium, FailureHandling.OPTIONAL)) {
			MobileDriver.tap(this.btnClose(), GlobalVariable.G_timeoutLong, flowControl)
		}
		if(MobileDriver.waitForElementPresent(commonPage.lblDynamic(UIDEVOPTIONS), GlobalVariable.G_timeoutMedium, FailureHandling.OPTIONAL)) {
			MobileDriver.tap(commonPage.imgNoDynamic(2), GlobalVariable.G_timeoutMedium, flowControl)
		}
		'Check Popup Open Your Account Online'
		if(MobileDriver.waitForElementPresent(commonPage.lblDynamic(UIOpenYourAccount), GlobalVariable.G_timeoutMedium, FailureHandling.OPTIONAL)) {
			MobileDriver.tap(commonPage.imgNoDynamic(2), GlobalVariable.G_timeoutMedium, flowControl)
		}

	}

	public void disagreeDataClosure(FailureHandling flowControl = defaultFailureHandling) {
		'Select Agree Button'
		if(MobileDriver.waitForElementPresent(this.btnDisagree(), GlobalVariable.G_timeoutShort, FailureHandling.OPTIONAL)) {
			MobileDriver.tap(this.btnDisagree(), GlobalVariable.G_timeoutShort, flowControl)
		}
		Mobile.takeScreenshot()
	}

	public void navigateToNTBLogin(FailureHandling flowControl = defaultFailureHandling) {
		'Select Agree Button'
		if(MobileDriver.waitForElementPresent(this.btnAgree(), GlobalVariable.G_timeoutShort, FailureHandling.OPTIONAL)) {
			MobileDriver.tap(this.btnAgree(), GlobalVariable.G_timeoutShort, flowControl)
		}
		'Check Popup Permission'
		if(BasePage.runFromTestCloud()) {
			if(MobileDriver.waitForElementPresent(commonPage.lblDynamic(WHILE_USING_THE_APP), GlobalVariable.G_timeoutShort, FailureHandling.OPTIONAL)) {
				'Allow Permission'
				MobileDriver.tap(commonPage.lblDynamic(WHILE_USING_THE_APP), GlobalVariable.G_timeoutMedium, flowControl)
				MobileDriver.tap(commonPage.lblDynamic(WHILE_USING_THE_APP), GlobalVariable.G_timeoutMedium, flowControl)
				MobileDriver.tap(commonPage.lblDynamic(ALLOW), GlobalVariable.G_timeoutMedium, flowControl)
				MobileDriver.tap(commonPage.lblDynamic(ALLOW), GlobalVariable.G_timeoutMedium, flowControl)
			}
		}
		if(Mobile.waitForElementPresent(this.lblOurAntiMalware(), GlobalVariable.G_timeoutMedium, FailureHandling.OPTIONAL)) {
			String text = 'Proceed Anyway'
			Mobile.scrollToText(text)
			'Select Proceed Anyway Button'
			MobileDriver.tap(this.btnProceedAnyway(), GlobalVariable.G_timeoutLong, flowControl)
			'Select Agree And Proceed Button'
			MobileDriver.tap(this.btnAgreeAndProceed(), GlobalVariable.G_timeoutLong, flowControl)
		}
	}
}
