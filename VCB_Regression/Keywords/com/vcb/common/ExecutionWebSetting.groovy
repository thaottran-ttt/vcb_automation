package com.vcb.common

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.configuration.RunConfiguration
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows

import internal.GlobalVariable

public class ExecutionWebSetting {
	public BrowserTypeEnum browserType

	static String getBrowserDriverName(String browserName) {
		switch(browserName) {
			case 'chrome':
				return BrowserTypeEnum.CHROME_DRIVER.toString()
			case 'firefox':
				return BrowserTypeEnum.FIREFOX_DRIVER.toString()
			case 'safari':
				return BrowserTypeEnum.SAFARI_DRIVER.toString()
			default:
				return BrowserTypeEnum.CHROME_DRIVER.toString()
		}
	}
	
	public ExecutionWebSetting(){

		Map<String, Object> references = RunConfiguration.getDriverPreferencesProperties()
		String driverName = references.keySet()[0]
		Map refInfo = references.getAt(driverName)
		if(BaseKeyword.runFromTestCloud()) {
			this.browserType = this.getBrowserDriverName(GlobalVariable.G_browserName)
		}else {
			this.browserType = DriverFactory.getExecutedBrowser()?.getName() ?: "CHROME_DRIVER"
		}
	}
}

public enum BrowserTypeEnum{
	CHROME_DRIVER, FIREFOX_DRIVER, SAFARI_DRIVER, EDGE_CHROMIUM_DRIVER, REMOTE_WEB_DRIVER
}

