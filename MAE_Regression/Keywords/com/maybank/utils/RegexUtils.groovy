package com.maybank.utils

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
import java.util.regex.Pattern;
import java.util.regex.Matcher;

import internal.GlobalVariable

public class RegexUtils {

	static final def DATE_OR_TIME_REGEX = ~/\d{1,2} \w{3} \d{4}(, \d{1,2}:\d{2}\s?[AP]M)?/
	static final def DATE_TIME_REGEX_STRING = '\\d{1,2} \\w{3} \\d{4}, \\d{1,2}:\\d{2}\\s?[AP]M'

	@Keyword
	public static boolean patternChecking(String testString) {
		String regex = '^RM\\s\\d{1,3}(?:,\\d{3})*(?:\\.\\d{2})';
		Pattern pattern = Pattern.compile(regex);

		Matcher matcher = pattern.matcher(testString);
		System.out.println("Test String \"" + testString + "\" matches: " + matcher.matches());
		return matcher
	}
}
