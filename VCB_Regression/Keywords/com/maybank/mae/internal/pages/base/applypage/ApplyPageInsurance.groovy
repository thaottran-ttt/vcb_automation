package com.maybank.mae.internal.pages.base.applypage

import java.time.LocalDate
import java.time.format.DateTimeFormatter;

import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testobject.TestObject
import com.maybank.common.MobileDriver
import com.maybank.mae.internal.pages.base.BasePage
import com.maybank.mae.internal.pages.base.CommonPage

import internal.GlobalVariable

public abstract class ApplyPageInsurance extends BasePage {

	CommonPage commonPage = getPage(CommonPage)

	static final String APPLY_NOW = 'Apply Now'
	
	/*
	 * Select Sub tab
	 */
	void selectSubTab(String subTabName, boolean isVisible = false, FailureHandling flowControl = defaultFailureHandling) {
		delay(GlobalVariable.G_timeoutShort)
		commonPage.selectTab(commonPage.frmHorizontalScrollNo(3), subTabName, GlobalVariable.G_timeoutMedium, flowControl)
		MobileDriver.takeScreenshot()
	}
	
	/*
	 * Select Home
	 */
	void selectHomeSubTab(String subTabName, boolean isVisible = false, FailureHandling flowControl = defaultFailureHandling) {
		delay(GlobalVariable.G_timeoutShort)
		commonPage.scrollToTestObject(commonPage.frmHorizontalScrollNo(3), commonPage.lblNormalizeSpace("Motorcycle"), ScrollDirectionEnum.RIGHT, GlobalVariable.G_timeoutShort, flowControl)
		MobileDriver.tap(commonPage.lblContainDynamicNo(subTabName,1), GlobalVariable.G_timeoutShort, FailureHandling.OPTIONAL)
		MobileDriver.takeScreenshot()
	}

	/*
	 * Select Insurance Type based on insurance name
	 */
	void selectInsuranceType(String insuranceName, FailureHandling flowControl = defaultFailureHandling) {
		commonPage.scrollToTestObject(commonPage.frmHorizontalScrollNo(2), commonPage.lblNormalizeSpace(insuranceName), ScrollDirectionEnum.DOWN, GlobalVariable.G_timeoutShort, flowControl)
		commonPage.tapButton(insuranceName)
		commonPage.waitLoading()
		MobileDriver.takeScreenshot()
	}

	/*
	 * Verify Intro Page UI
	 */
	void verifyIntroPage(List data, boolean runValidation = false, FailureHandling flowControl = defaultFailureHandling) {
		
		'Verify Page'
		scrollToAndVerifyTextInList(data, commonPage.lblDynamicPath(), GlobalVariable.G_timeoutMedium, flowControl)
		MobileDriver.takeScreenshot()
		
		if (runValidation) {
			
			'List of text links'
			List textLinks = [
				'eBrochure',
				'Product Disclosure Sheet',
				'Policy'
			]
			
			for (String link : textLinks) {
				
				'Verify Text Links'
				scrollToAndVerifyTextInList(textLinks, commonPage.lblContainDynamicPath(), GlobalVariable.G_timeoutMedium, flowControl)
				
				'Tap text link'
				MobileDriver.tap(commonPage.lblContainDynamic(link), GlobalVariable.G_timeoutMedium, flowControl)
				
				'Delay'
				MobileDriver.delay(GlobalVariable.G_timeoutShort)
				
				'Take Screenshot'
				MobileDriver.takeScreenshot()
				
				'Tap Back'
				commonPage.tapBack()
			}
		}
		
		'Tap Apply Now'
		MobileDriver.tap(commonPage.lblDynamic(APPLY_NOW), GlobalVariable.G_timeoutMedium, flowControl)
	}	
	
	/*
	 * Verify Etiqa Page UI
	 */
	void verifyEtiqaPage(String text, int timeout = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		'Verify Page'
		MobileDriver.waitForElementPresent(commonPage.lblContainDynamic(text), timeout, flowControl)
		
		'Take Screenshot'
		MobileDriver.takeScreenshot()	
	}
}
