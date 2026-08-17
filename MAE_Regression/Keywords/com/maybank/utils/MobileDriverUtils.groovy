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
import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import com.maybank.common.Android
import com.maybank.common.MobileDriver
import com.maybank.mae.internal.pages.base.BasePage

import internal.GlobalVariable
import io.appium.java_client.AppiumDriver

public class MobileDriverUtils {

	static AppiumDriver driver = MobileDriverFactory.getDriver()

	static void setDriverMobile() {
		if(this.driver?.getSessionId() != MobileDriverFactory.getDriver().getSessionId()) {
			this.driver = MobileDriverFactory.getDriver()
			TouchActionUtils.driver = this.driver
			MobileDriver.driver = this.driver
			Android.driver = this.driver
		}
	}
}
