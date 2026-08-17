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

import groovy.json.JsonSlurper
import internal.GlobalVariable

public class MapUtils {
	
	/**
	 * Convert a JSON-like string to a Map
	 * @param jsonString The JSON string
	 * @return Map object
	 */
	static Map parseJsonToMap(String jsonString) {
		return new JsonSlurper().parseText(jsonString)
	}
	
	/**
     * Convert a custom-formatted string with parentheses and quotes to a Map
     * @param customString The custom string with key-value pairs in parentheses
     * @return Map object
     */
    static Map parseMapStringToMap(String customString) {
//        // Remove square brackets and parentheses
//        customString = customString.replaceAll("[\\[\\]()]", "")
//
//        // Split the string into key-value pairs
//        customString.split(',').collectEntries { entry ->
//            def (key, value) = entry.split(':').collect { it.trim().replaceAll("'", "") }
//            [(key): value]
//        }
		
		return Eval.me(customString)
    }
	
	static Map parseObjectToMap(def object) {
		if(object instanceof Map) {
			return object
		}else if(object instanceof String) {
			return Eval.me(object)
		}
	   return null
	}
	
}
