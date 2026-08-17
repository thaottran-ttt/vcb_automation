package com.maybank.assertions

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
import com.maybank.common.BaseKeyword

import internal.GlobalVariable

public class BaseAssert extends BaseKeyword{

	/**
	 * Evaluate a value is null or empty
	 * @param actualValue the value need to evaluate
	 * @param description
	 */
	@Keyword
	static void isNull(Object actualValue, String description, FailureHandling flowControl = FailureHandling.STOP_ON_FAILURE) {
		String verifyMessage = "is null"
		def result = !actualValue ? true : false
		verifyResult actualValue, verifyMessage, result, description, flowControl
	}

	/**
	 * Evaluate a value is not null and not empty
	 * @param actualValue
	 * @param description short description
	 */
	@Keyword
	static void isNotNull(Object actualValue, String description, FailureHandling flowControl = FailureHandling.STOP_ON_FAILURE) {
		String expectedMsg = "is not null"
		def result = actualValue ? true : false
		verifyResult actualValue, expectedMsg, result, description, flowControl
	}

	/**
	 * Evaluate 2 values are equals
	 * @param expectedValue expected value
	 * @param actualValue actual value
	 * @param description
	 */
	@Keyword
	static void equals(Object expectedValue, Object actualValue, String description, FailureHandling flowControl = defaultFailureHandling) {
		String expectedMsg = "equals to ${expectedValue}"
		def result = expectedValue == actualValue
		verifyResult actualValue, expectedMsg, result, description, flowControl
	}

	/**
	 * Evaluate 2 values are not equals
	 * @param expectedValue expected value
	 * @param actualValue actual value
	 * @param description
	 */
	@Keyword
	static void notEquals(Object expectedValue, Object actualValue, String description, FailureHandling flowControl = FailureHandling.STOP_ON_FAILURE) {
		String expectedMsg = "not equals to ${expectedValue}"
		def result = expectedValue != actualValue
		verifyResult actualValue, expectedMsg, result, description, flowControl
	}

	static void verifyResult(Object actualValue, Object expectedResult, boolean result, String description, FailureHandling flowControl = null) {
		String message = "Evaluate: <$description>. Expected: <$expectedResult>. Actual: <$actualValue>"
		if (!result){
			handleError(message, flowControl)
		}
		else{
			info message
		}
	}
}
