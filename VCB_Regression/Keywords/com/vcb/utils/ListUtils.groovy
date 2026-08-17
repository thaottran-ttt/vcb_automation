package com.vcb.utils

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import static org.assertj.core.api.InstanceOfAssertFactories.ARRAY

import java.util.stream.Collectors
import java.util.stream.Stream

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

public class ListUtils {

	static List parseObjectToList(def object) {
		if(object instanceof List) {
			return object
		}else if(object instanceof String) {
			return Eval.me(object)
		}
		return null
	}

	static boolean isStringInList(List list, String str) {
		return list.any { str.contains(it) }
	}

	/**
	 * Using for converting String to List.
	 * @param input a,b
	 * @return message is a list [a,b]
	 * if input is already a list it will return input
	 */
	static List parseObjectMessageToList(def input) {
		if (input == null || input == []) {
			return new ArrayList<>();
		}
		if(input.getClass() == List) {
			return input
		}
		List<String> messages = Stream.of(input.toString().split(",", -1))
				.collect(Collectors.toList());

		return messages
	}
}
