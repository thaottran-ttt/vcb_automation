package com.vcb.helper

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import com.google.gson.JsonObject
import com.google.gson.JsonParser
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
import com.vcb.common.BaseKeyword

import internal.GlobalVariable

public class RestAPIHelper extends BaseKeyword{

	@Keyword
	public static JsonObject getJsonObject(TestObject request, FailureHandling controlFlow = FailureHandling.STOP_ON_FAILURE) {
		def res = WS.sendRequest(request) // Send the request and receive the response
		def responseData = res.getResponseText() // Get the response text

		try {
			// Convert the response text to a JSON object
			JsonParser parser = new JsonParser()
			JsonObject jsonObject = parser.parse(responseData).getAsJsonObject()
			return jsonObject
		} catch (Exception e) {
			handleError("Error parsing JSON: " + e.getMessage(), controlFlow)
		}
	}
}
