package com.maybank.common

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
import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows

import internal.GlobalVariable
import io.appium.java_client.AppiumDriver

public class ExecutionSetting {
	private DeviceTypeEnum deviceType
	private PlatformMobileEnum platform

	private int DEVICE_WIDTH_IPAD = 768
	private int DEVICE_WIDTH_TABLET = 1080

	public ExecutionSetting(){
		int deviceWidth = Mobile.getDeviceWidth()
		AppiumDriver driver = MobileDriverFactory.getDriver()
		String platform = driver.getCapabilities().getCapability("platformName").toString().toLowerCase()
		if(platform == 'ios') {
			if(deviceWidth > DEVICE_WIDTH_IPAD) {
				this.deviceType = DeviceTypeEnum.iPad
			}else {
				this.deviceType = DeviceTypeEnum.iPhone
			}
			this.platform = PlatformMobileEnum.IOS
		}else if(platform == 'android') {
			this.deviceType = DeviceTypeEnum.AndroidPhone
			this.platform = PlatformMobileEnum.ANDROID
		}
	}
}

public enum DeviceTypeEnum{
	iPad, iPhone, AndroidPhone, AndroidTablet
}

public enum PlatformMobileEnum{
	ANDROID, IOS
}
