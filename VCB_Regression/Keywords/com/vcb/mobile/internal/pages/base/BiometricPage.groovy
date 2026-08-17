package com.vcb.mobile.internal.pages.base

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
import internal.GlobalVariable

public abstract class BiometricPage extends BasePage{

	abstract TestObject btnSetUpLater()
	abstract TestObject btnYesProceed()
	abstract TestObject btnDiffNo()
	
	String UIYesProcess = "Yes,"
	
	CommonPage commonPage = getPage(CommonPage)
	
	/**
	 * setup: verify UI, tap Proceed Button
	 * @return
	 */
	public void setup(FailureHandling flowControl = defaultFailureHandling) {
		'Select Set Up Later button'
		if(Mobile.waitForElementPresent(this.btnSetUpLater(), GlobalVariable.G_timeoutShort)) {
			MobileDriver.tap(this.btnSetUpLater(), GlobalVariable.G_timeoutLong, flowControl)
		}
		'Select Yes Proceed button'
		if(Mobile.waitForElementPresent(this.btnYesProceed(), GlobalVariable.G_timeoutShort)) {
			MobileDriver.tap(this.btnYesProceed(), GlobalVariable.G_timeoutLong, flowControl)
		}else {
			MobileDriver.tap(commonPage.lblContainDynamic(UIYesProcess), GlobalVariable.G_timeoutLong, flowControl)
		}
		
	}

	/**
	 * setup: verify UI, tap Different Account Button
	 * @return
	 */
	public void setupOtherNo(FailureHandling flowControl = defaultFailureHandling) {
		'Select Set Up Later button'
		if(Mobile.waitForElementPresent(this.btnSetUpLater(), GlobalVariable.G_timeoutLong)) {
			MobileDriver.tap(this.btnSetUpLater(), GlobalVariable.G_timeoutLong, flowControl)
		}
		Mobile.delay(GlobalVariable.G_timeoutMiniShort)
		'Other Phone Number'
		MobileDriver.tap(this.btnDiffNo(), GlobalVariable.G_timeoutLong, flowControl)
	}
}
