package com.vcb.assertions

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

public class NumberAssert extends BaseAssert{
	/**
	 * Evaluate a number is greater than another
	 * @param a number on the left side
	 * @param b number on the right side
	 * @param description detail of evaluation
	 */
	@Keyword
	static void isGreaterThan(Number a, Number b, String description, FailureHandling flowControl = null) {
		def message = "is greater than '$b'"
		def result = a > b
		verifyResult(a, message, result, description, flowControl)
	}

	/**
	 * Evaluate a number is greater than or equal another
	 * @param a number on the left side
	 * @param b number on the right side
	 * @param description detail of evaluation
	 */
	@Keyword
	static void isGreaterThanOrEqual(Number a, Number b, String description, FailureHandling flowControl = null) {
		def message = "is less than or equal to '$b'"
		def result = a >= b
		verifyResult(a, message, result, description, flowControl)
	}

	/**
	 * Evaluate a number is greater than another
	 * @param a number on the left side
	 * @param b number on the right side
	 * @param description detail of evaluation
	 */
	@Keyword
	static void isLessThan(Number a, Number b, String description, FailureHandling flowControl = null) {
		def message = "is less than '$b'"
		def result = a < b
		verifyResult(a, message, result, description, flowControl)
	}

	/**
	 * Evaluate a number is less than or equal another
	 * @param a number on the left side
	 * @param b number on the right side
	 * @param description detail of evaluation
	 */
	@Keyword
	static void isLessThanOrEqual(Number a, Number b, String description, FailureHandling flowControl = null) {
		def message = "is less than or equal to '$b'"
		def result = a <= b
		verifyResult(a, message, result, description, flowControl)
	}
}
