package com.maybank.mae.internal.pages.base.transferpage

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
import com.maybank.assertions.BaseAssert
import com.maybank.common.MobileDriver
import com.maybank.mae.internal.pages.base.BasePage
import com.maybank.mae.internal.pages.base.CommonPage

import internal.GlobalVariable

public abstract class TabungHajiPage extends BasePage {
	
	CommonPage commonPage = getPage(CommonPage)
	
	void tapTabungHajiButton(String moduleName, FailureHandling flowControl = defaultFailureHandling) {
		MobileDriver.tapEleByTestObject(commonPage.lblDynamic(moduleName))
	}
	
	void enterTransferData(Map transferData, FailureHandling flowControl = defaultFailureHandling) {
		commonPage.inputDropdownMapDefault(transferData, flowControl)
		Mobile.takeScreenshot()
		'Tap Continue'
		commonPage.tapContinueBtn()
	}
	
	void enterRecipientReference(String recipientReferenceData, FailureHandling flowControl = defaultFailureHandling) {
		'Input Recipient Reference'
		MobileDriver.setText(commonPage.txtEditNo(), recipientReferenceData, GlobalVariable.G_timeoutMedium, flowControl)
		
		'Tap Continue'
		commonPage.tapContinueBtn()
	}
	
	void enterOtherTabungHaji(String AccNo, String NRIC, FailureHandling flowControl = defaultFailureHandling) {
		'Input NRIC number'
		MobileDriver.setText(commonPage.txtEditNo(), AccNo, GlobalVariable.G_timeoutMedium, flowControl)
		
		'Input tabung Haji account number'
		MobileDriver.setText(commonPage.txtEditNo(2), NRIC, GlobalVariable.G_timeoutMedium, flowControl)
		
		'Tap Continue'
		commonPage.tapContinueBtn()
		MobileDriver.delay(GlobalVariable.G_timeoutMiniShort)
		MobileDriver.takeScreenshot()
	}
	
	void verifyAccountNotLinkedToTabungHaji(List accountNotLinkedToTabungHajiData, FailureHandling flowControl = defaultFailureHandling) {
		verifyElements(accountNotLinkedToTabungHajiData, commonPage.lblDynamicPath(), GlobalVariable.G_timeoutMedium, flowControl)
	}
}
