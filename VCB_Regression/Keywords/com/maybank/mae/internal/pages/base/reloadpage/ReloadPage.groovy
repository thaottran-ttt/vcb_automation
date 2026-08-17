package com.maybank.mae.internal.pages.base.reloadpage

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

import internal.GlobalVariable

public abstract class ReloadPage extends BasePage {

	CommonPage commonPage = getPage(CommonPage)
	static ReloadPage instance = getPage(ReloadPage)
	
	/*
	 * selectProvider: select provider
	 * 
	 */
	void selectProvider(String provider, FailureHandling flowControl = defaultFailureHandling){
		'Tap icon search'
		MobileDriver.tap(commonPage.imgNoDynamic(2), GlobalVariable.G_timeoutMedium, flowControl)

		'Set text provider'
		MobileDriver.setText(commonPage.txtEditNo(), provider, GlobalVariable.G_timeoutMedium, flowControl)

		'Tap Provider'
		MobileDriver.tap(commonPage.lblTextIgnoreCaseNo(provider, 2), GlobalVariable.G_timeoutLong, flowControl)
		
		'Tap again provider'
		if(MobileDriver.waitForElementPresent(commonPage.lblTextIgnoreCaseNo(provider, 2), GlobalVariable.G_timeoutShort, FailureHandling.OPTIONAL)) {
			MobileDriver.tap(commonPage.lblTextIgnoreCaseNo(provider, 2), GlobalVariable.G_timeoutMedium, flowControl)
		}
	}

	/*
	 * enterPhoneNumber: Enter Phone Number
	 *
	 */
	void enterPhoneNumber(String phoneNumber, FailureHandling flowControl = defaultFailureHandling) {
		commonPage.enterNO(phoneNumber, false, '', 0, flowControl)
		
		String inputPhoneNumber = MobileDriver.getText(commonPage.txtEditNo(1), GlobalVariable.G_timeoutMedium, flowControl)
		String inputPhoneNumberFormat = inputPhoneNumber.replaceAll(" ", "")
		if(inputPhoneNumberFormat != phoneNumber) {
			commonPage.enterNO(phoneNumber, true, '', GlobalVariable.G_timeoutMiniShort, flowControl)
		}else {
			commonPage.tapTickBtn()
			MobileDriver.delay(GlobalVariable.G_timeoutMiniShort)
		}
	}
	
	/*
	 * selectAmount: Enter Amount
	 *
	 */
	void selectAmount(String amount, FailureHandling flowControl = defaultFailureHandling) {
		MobileDriver.tap(commonPage.lblDynamic(amount), GlobalVariable.G_timeoutMedium, flowControl)

		commonPage.tapContinueBtn()
		
		commonPage.waitLoading()
	}
	
	/*
	 * editAmount: Edit Amount
	 *
	 */
	void editAmount(String amount, FailureHandling flowControl = defaultFailureHandling) {
		commonPage.tapBack()
		'Enter Amount'
		this.selectAmount(amount)
	}
}
