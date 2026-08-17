package com.maybank.mae.internal.pages.androidphone.gbi

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

import com.maybank.mae.internal.pages.base.CommonPage
import com.maybank.mae.internal.pages.base.gbi.GbiPage
import internal.GlobalVariable

public class AndroidPhoneGbiPage extends GbiPage {
	
	@Override
	public TestObject sliderInitialInvest() {
		return findTestObject('Object Repository/Mobile/MAE/Android/GBI/sliderInitialInvest');
	}
	@Override
	public TestObject sliderMonthlyInvest() {
		return findTestObject('Object Repository/Mobile/MAE/Android/GBI/sliderMonthlyInvest');
	}
	@Override
	public TestObject closeBtnTermsConditions() {
		return findTestObject('Object Repository/Mobile/MAE/Android/GBI/closeBtnTermsConditions');
	}
	@Override
	public TestObject closeBtnDelete() {
		return findTestObject('Object Repository/Mobile/MAE/Android/GBI/closeBtnDelete');
	}
	@Override
	public boolean sliderToDistance(TestObject sliderPoint, int distance, FailureHandling flowControl) {

    try {
        int left = Mobile.getElementLeftPosition(sliderPoint, GlobalVariable.G_timeoutLong, flowControl)
        int top  = Mobile.getElementTopPosition(sliderPoint, GlobalVariable.G_timeoutLong, flowControl)

        commonPage.swipe(left, top, left + distance, top)
        return true

    } catch (Exception e) {
        
        return false
    }
	}
}
