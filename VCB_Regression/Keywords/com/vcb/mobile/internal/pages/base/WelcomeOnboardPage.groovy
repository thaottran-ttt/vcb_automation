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

public abstract class WelcomeOnboardPage extends BasePage{
	abstract TestObject txtYourName()
	abstract TestObject btnContinue()
	abstract TestObject btnWallet()
	abstract TestObject btnNext()
	abstract TestObject btnClose()

	static CommonPage commonPage = CommonPage.getPage(CommonPage)

	/**
	 * Enter your name
	 */
	public void enterYourName(String yourName, String accountName = '', FailureHandling flowControl = defaultFailureHandling) {
		'Select Agree Button'
		MobileDriver.setText(this.txtYourName(), yourName, GlobalVariable.G_timeoutLong, flowControl)
		Mobile.delay(GlobalVariable.G_timeoutMiniShort)
		'Select Continue Button'
		if(Mobile.waitForElementPresent(this.btnContinue(), GlobalVariable.G_timeoutLong, flowControl)) {
			MobileDriver.tap(this.btnContinue(), GlobalVariable.G_timeoutLong, flowControl)
		}
		'If account name is not empty, then scroll to account name and select it'
		if(!(accountName == '' || accountName == "''")) {
			'Scroll to Account Name'
			Mobile.scrollToText(accountName, FailureHandling.OPTIONAL)
			'Select Account Name'
			if (Mobile.waitForElementPresent(commonPage.lblDynamic(accountName), GlobalVariable.G_timeoutMedium, flowControl)) {
				MobileDriver.tap(commonPage.lblDynamic(accountName), GlobalVariable.G_timeoutMedium, flowControl)
			}else {
				'Select first Account'
				MobileDriver.tap(commonPage.lblRMNo(1), GlobalVariable.G_timeoutLong, flowControl)
			}
		}else {
			'Select first Account'
			MobileDriver.tap(commonPage.lblRMNo(1), GlobalVariable.G_timeoutLong, flowControl)
		}
		'Select Next Button'
		MobileDriver.tap(this.btnNext(), GlobalVariable.G_timeoutLong, flowControl)
		Mobile.delay(GlobalVariable.G_timeoutLong, flowControl)
		'Select Close Button'
		MobileDriver.tap(this.btnClose(), GlobalVariable.G_timeoutLong, flowControl)
	}
}
