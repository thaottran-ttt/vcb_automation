package com.vcb.assertions

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import java.util.regex.Matcher
import java.util.regex.Pattern

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

public class StringAssert extends BaseAssert{
	/**
	 * Evaluate a string contains another string
	 * @param sourceStr source string
	 * @param subStr substring
	 * @param description detail of evaluation
	 */
	@Keyword
	static void contains(String sourceStr, String subStr, boolean caseSensitive = true, String description, FailureHandling flowControl = FailureHandling.STOP_ON_FAILURE) {
		String expectedMsg = "contains '$subStr' case-sensitive $caseSensitive"
		String newSourceStr = sourceStr
		String newSubStr = subStr
		if(!caseSensitive){
			newSourceStr = newSourceStr.toLowerCase()
			newSubStr = newSubStr.toLowerCase()
		}
		verifyResult(sourceStr, expectedMsg, newSourceStr.contains(newSubStr), description, flowControl)
	}

	/**
	 * Evaluate a string does not contain another string
	 * @param sourceStr source string
	 * @param subStr substring
	 * @param description detail of evaluation
	 */
	@Keyword
	static void notContains(String sourceStr, String subStr, boolean caseSensitive = true, String description, FailureHandling flowControl = FailureHandling.STOP_ON_FAILURE) {
		String expectedMsg = "contains '$subStr' case-sensitive $caseSensitive"
		String newSourceStr = sourceStr
		String newSubStr = subStr
		if(!caseSensitive){
			newSourceStr = newSourceStr.toLowerCase()
			newSubStr = newSubStr.toLowerCase()
		}
		verifyResult(sourceStr, expectedMsg, !newSourceStr.contains(newSubStr), description, flowControl)
	}

	/**
	 * Evaluate a string equals to another string
	 * @param sourceStr source string
	 * @param subStr substring
	 * @param description detail of evaluation
	 */
	@Keyword
	static void equals(String sourceStr, String subStr, boolean caseSensitive = true, String description, FailureHandling flowControl = FailureHandling.STOP_ON_FAILURE) {
		String expectedMsg = "contains '$subStr' case-sensitive $caseSensitive"
		String newSourceStr = sourceStr
		String newSubStr = subStr
		if(!caseSensitive){
			newSourceStr = newSourceStr.toLowerCase()
			newSubStr = newSubStr.toLowerCase()
		}
		verifyResult(sourceStr, expectedMsg, newSourceStr.equals(newSubStr), description, flowControl)
	}

	/**
	 * Evaluate a string starts with to a prefix
	 * @param sourceStr source string
	 * @param prefix
	 * @param description detail of evaluation
	 */
	@Keyword
	static void startsWith(String sourceStr, String prefix, boolean caseSensitive = true, String description, FailureHandling flowControl = FailureHandling.STOP_ON_FAILURE) {
		String expectedMsg = "starts with '$prefix' case-sensitive $caseSensitive"
		String newSourceStr = sourceStr
		String newPrefix = prefix
		if(!caseSensitive){
			newSourceStr = newSourceStr.toLowerCase()
			newPrefix = newPrefix.toLowerCase()
		}
		verifyResult(sourceStr, expectedMsg, newSourceStr.startsWith(newPrefix), description, flowControl)
	}

	/**
	 * Evaluate a string ends with to a prefix
	 * @param sourceStr source string
	 * @param suffix
	 * @param description detail of evaluation
	 */
	@Keyword
	static void endsWith(String sourceStr, String suffix, boolean caseSensitive = true, String description, FailureHandling flowControl = FailureHandling.STOP_ON_FAILURE) {
		String expectedMsg = "ends with '$suffix' case-sensitive $caseSensitive"

		String newSourceStr = sourceStr
		String newSuffix = suffix
		if(!caseSensitive){
			newSourceStr = newSourceStr.toLowerCase()
			newSuffix = newSuffix.toLowerCase()
		}
		verifyResult(sourceStr, expectedMsg, newSourceStr.endsWith(newSuffix), description, flowControl)
	}

	/**
	 * Evaluate a string expression matches a regular expression.
	 * @param sourceStr source string
	 * @param description
	 * @param regex
	 */
	@Keyword
	static void matches(String sourceStr, String regex, String description, FailureHandling flowControl = FailureHandling.STOP_ON_FAILURE) {
		def expectedMsg = "matches regular expression:'$regex'"
		Pattern p = Pattern.compile(regex)
		Matcher m = p.matcher(sourceStr)
		verifyResult(sourceStr, expectedMsg, !m.find(), description, flowControl)
	}
}
