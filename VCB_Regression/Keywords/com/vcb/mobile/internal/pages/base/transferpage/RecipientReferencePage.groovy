package com.vcb.mobile.internal.pages.base.transferpage

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
import com.vcb.assertions.BaseAssert
import com.vcb.common.MobileDriver
import com.vcb.mobile.internal.pages.base.BasePage
import com.vcb.mobile.internal.pages.base.CommonPage
import internal.GlobalVariable

public class RecipientReferencePage extends BasePage{
	//Init Data
	String EnterRecipientReference = 'Enter recipient reference'
	String Continue = 'Continue'
	String RecipientInValid = 'Please remove invalid special characters.'
	String PleaseEnterRecipientRef = 'Please enter a recipient reference.'
	CommonPage commonPage = getPage(CommonPage)
	
	static final String RECIPIENT_REFERENCE_SPECIAL_CHARS = '#123*@'

	String inputRecipientReference(String recipientReference = '', FailureHandling flowControl = defaultFailureHandling) {
		'Verify Recipient Reference Visible'
		commonPage.verifyTextVisible(EnterRecipientReference, GlobalVariable.G_timeoutLong, flowControl)
		if(recipientReference.equals('')) {
			'Create Recipient Reference'
			Random rand = new Random()
			recipientReference = ('Recipient Ref ' + Integer.toString(rand.nextInt(1000)))
		}
		'Enter Recipient Reference Number'
		MobileDriver.setText(commonPage.txtEditNo(1), recipientReference, GlobalVariable.G_timeoutLong, flowControl)
		Mobile.takeScreenshot()
		'Select Continue Button'
		commonPage.tapText(Continue, GlobalVariable.G_timeoutLong, flowControl)
		return recipientReference
	}

	void verifyRecipientReference(FailureHandling flowControl = defaultFailureHandling) {
		Mobile.delay(1)
		'Verify Recipient Reference Visible'
		commonPage.verifyTextVisible(EnterRecipientReference, GlobalVariable.G_timeoutLong, flowControl)
		'Enter Recipient Reference'
		setText(commonPage.txtEditNo(1), '#123*@', GlobalVariable.G_timeoutLong, flowControl)
		'Select Continue Button'
		tap(commonPage.lblDynamic(Continue), GlobalVariable.G_timeoutLong, flowControl)

		if(MobileDriver.waitForElementPresent(commonPage.lblDynamic(RecipientInValid), GlobalVariable.G_timeoutShort, FailureHandling.OPTIONAL)) {
			'Verify Recipient InValid'
			commonPage.verifyTextVisible(RecipientInValid, GlobalVariable.G_timeoutLong, flowControl)
		}else {
			'Verify Please Enter Recipient Ref Visible'
			commonPage.verifyTextVisible(PleaseEnterRecipientRef, GlobalVariable.G_timeoutLong, flowControl)
		}
		MobileDriver.takeScreenshot()
		'Enter Recipient Reference'
		setText(commonPage.txtEditNo(1), 'Recipient Reference Up To 20', GlobalVariable.G_timeoutLong, flowControl)
		'Assign Data'
		String inputRecipient = MobileDriver.getText(commonPage.txtEditNo(1), GlobalVariable.G_timeoutLong, flowControl)
		'Compare Recipient'
		BaseAssert.equals(inputRecipient.length() <= 20, true, 'Recipient Reference less than or equal to 20')
		MobileDriver.takeScreenshot()
	}
	
	void verifySpecialCaseRecipientReference(String specialCharsMsg, FailureHandling flowControl = defaultFailureHandling) {
		'Enter Recipient Reference'
		setText(commonPage.txtEditNo(1), RECIPIENT_REFERENCE_SPECIAL_CHARS, GlobalVariable.G_timeoutLong, flowControl)
		'Select Continue Button'
		commonPage.tapContinueBtn()
		
		'Verify enter disallowed special chars'
		commonPage.verifyTextVisible(specialCharsMsg, GlobalVariable.G_timeoutLong, flowControl)
		MobileDriver.takeScreenshot()
		
		'Enter Recipient Reference'
		setText(commonPage.txtEditNo(1), 'Recipient Reference Up To 20', GlobalVariable.G_timeoutLong, flowControl)
		'Assign Data'
		String inputRecipient = MobileDriver.getText(commonPage.txtEditNo(1), GlobalVariable.G_timeoutLong, flowControl)
		
		'Verify Recipient Reference - up to 20'
		BaseAssert.equals(inputRecipient.length() <= 20, true, 'Recipient Reference less than or equal to 20')
		MobileDriver.takeScreenshot()
		
		'Select Continue Button'
		commonPage.tapContinueBtn()
	}
}
