package com.vcb.mobile.internal.pages.base.paybill

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
import com.vcb.common.MobileDriver
import com.vcb.mobile.internal.pages.base.BasePage
import com.vcb.mobile.internal.pages.base.CommonPage
import internal.GlobalVariable

public abstract class ZakatPage extends BasePage {

	CommonPage commonPage = getPage(CommonPage)
	abstract TestObject lblFirstItemSearch()

	//UI
	static final String CONTINUE = 'Continue'
	static final String FAVOURITES = 'Favourites'

	/**
	 * Select tab name
	 */
	void selectTabName(String tabName, FailureHandling flowControl = defaultFailureHandling) {
		'Select tab name'
		MobileDriver.tap(commonPage.lblDynamic(tabName), GlobalVariable.G_timeoutLong, flowControl)
		MobileDriver.delay(GlobalVariable.G_timeoutMiniShort)
	}

	/**
	 * Select new payment
	 */
	void selectNewPayment(String paymentName, FailureHandling flowControl = defaultFailureHandling) {
		'Select new payment'
		MobileDriver.tap(commonPage.lblDynamic(paymentName), GlobalVariable.G_timeoutLong, flowControl)
	}

	/**
	 * Select favourite
	 */
	void selectFavourite(String tabName, String favName, FailureHandling flowControl = defaultFailureHandling) {
		'Select tab name'
		this.selectTabName(tabName)
		'Select input favourite'
		MobileDriver.tap(commonPage.imgSiblingGroup(FAVOURITES), GlobalVariable.G_timeoutLong, flowControl)

		'Input favourite name'
		MobileDriver.setText(commonPage.txtEditNo(1), favName, GlobalVariable.G_timeoutLong, flowControl)
		MobileDriver.delay(GlobalVariable.G_timeoutMiniShort)
		//		MobileDriver.tap(commonPage.lblDynamicNo(favName, 2), GlobalVariable.G_timeoutLong, flowControl)
		'Select first item search'
		MobileDriver.tap(this.lblFirstItemSearch(), GlobalVariable.G_timeoutLong, flowControl)
		'Wait for first item search'
		if(MobileDriver.waitForElementPresent(this.lblFirstItemSearch(), GlobalVariable.G_timeoutShort, FailureHandling.OPTIONAL)) {
			'Select first item search'
			MobileDriver.tap(this.lblFirstItemSearch(), GlobalVariable.G_timeoutLong, flowControl)
		}
	}

	/**
	 * Input zakat
	 */
	void inputZakat(Map inputZakat, FailureHandling flowControl = defaultFailureHandling) {
		'Input zakats information'
		inputZakat.each { key, value ->
			'Select value in dropdown for each key'
			commonPage.selectDropdownSiblingGroupItem(key, value, GlobalVariable.G_timeoutMedium, flowControl)
		}

		'Select continue button'
		MobileDriver.tap(commonPage.lblDynamic(CONTINUE), GlobalVariable.G_timeoutLong, flowControl)
	}

	/**
	 * Enter mobile number
	 */
	void enterMobileNumber(String mobileNumber, FailureHandling flowControl = defaultFailureHandling) {
		'Input mobile number'
		MobileDriver.setText(commonPage.txtEditNo(1), mobileNumber, GlobalVariable.G_timeoutLong, flowControl)

		'Select continue button'
		MobileDriver.tap(commonPage.lblDynamic(CONTINUE), GlobalVariable.G_timeoutLong, flowControl)
	}
}
