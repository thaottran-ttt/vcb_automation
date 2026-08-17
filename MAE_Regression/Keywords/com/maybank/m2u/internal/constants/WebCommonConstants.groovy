package com.maybank.m2u.internal.constants

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

public class WebCommonConstants {
	static final TOAST_MESSAGE_TITLE_CLASS = 'notification-title'
	static final TOAST_MESSAGE_TITLE_BLANK_FIELD = 'Please do not leave field blank'
	static final TOAST_MESSAGE_TITLE_AGREE_TERMS = 'Please agree to the terms and conditions.'
	static final TOAST_MESSAGE_MESSAGE_CLASS = 'notification-message'
	static final TOAST_MESSAGE_CONTAINER_CLASS = 'notification-error'
	static final TOAST_MESSAGE_CONTAINER_BACKGROUND_COLOR = 'rgba(218, 71, 71, 1)'
	static final TOAST_MESSAGE_CONTAINER_CSS_CHECK = 'background-color'
	static final TOAST_MESSAGE_CONTAINER_CLOSE_CLASS = 'notification-dismiss'

	static final INPUT_TOGGLE_TEXT_CLASS = 'toggleplaceholder'
	
	static final BTN_CLASS = 'btn'
}
