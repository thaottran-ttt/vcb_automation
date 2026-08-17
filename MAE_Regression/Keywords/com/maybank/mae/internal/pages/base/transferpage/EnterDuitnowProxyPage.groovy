package com.maybank.mae.internal.pages.base.transferpage

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import org.checkerframework.checker.guieffect.qual.UIType

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
import com.maybank.common.MobileDriver
import com.maybank.mae.internal.pages.base.BasePage
import com.maybank.mae.internal.pages.base.CommonPage
import internal.GlobalVariable

public abstract class EnterDuitnowProxyPage extends BasePage{

	abstract TestObject drpIDType()
	String IDType = 'ID type'
	String Done = 'Done'
	String Continue = 'Continue'

	static CommonPage commonPage = getPage(CommonPage)

	/**
	 * Choose proxy type
	 */
	void chooseProxyType(String product, FailureHandling flowControl = defaultFailureHandling) {
		'Verify ID Type is visible'
		MobileDriver.verifyElementVisible(commonPage.lblDynamic(IDType), GlobalVariable.G_timeoutLong, flowControl)
		MobileDriver.delay(GlobalVariable.G_timeoutMiniShort)

		'Choose ID Type'
		MobileDriver.tap(this.drpIDType(), GlobalVariable.G_timeoutLong, flowControl)
		MobileDriver.delay(1)
		MobileDriver.takeScreenshot()

		'Verify button Done is visible'
		MobileDriver.verifyElementVisible(commonPage.lblDynamic(Done), GlobalVariable.G_timeoutLong, flowControl)

		'Scroll to proxy type'
		Mobile.scrollToText(product)

		'Select proxy type'
		MobileDriver.tap(commonPage.lblDynamic(product), GlobalVariable.G_timeoutLong, flowControl)
		MobileDriver.takeScreenshot()

		'Select Done'
		MobileDriver.tap(commonPage.lblDynamic(Done), GlobalVariable.G_timeoutLong, flowControl)
		Mobile.delay(GlobalVariable.G_timeoutMiniShort)
	}

	/**
	 * Enter proxy number
	 */
	void enterProxyNumber(String product, List<String> data, FailureHandling flowControl = defaultFailureHandling) {
		'Verify ID Type and product are visible'
		verifyElements([IDType, product], commonPage.lblDynamicPath(), GlobalVariable.G_timeoutLong)

		switch (product) {
			case 'Mobile Number':
				'Enter Mobile Number'
				MobileDriver.setText(commonPage.txtEditNo(1), data[0], GlobalVariable.G_timeoutLong, flowControl)
				break
			case 'NRIC Number':
				'Enter NRIC Number'
				MobileDriver.setText(commonPage.txtEditNo(1), data[0], GlobalVariable.G_timeoutLong, flowControl)
				break
			case 'Passport Number':
				'Verify Issuing Country is visible'
				verifyElements(['Issuing Country'], commonPage.lblDynamicPath(), GlobalVariable.G_timeoutLong)
				'Enter Passport Number'
				MobileDriver.setText(commonPage.txtEditNo(1), data[0], GlobalVariable.G_timeoutLong, flowControl)
				'Select Select Country'
				commonPage.tapText('Select Country', GlobalVariable.G_timeoutLong, flowControl)

				'Scroll to country'
				int startY = Mobile.getDeviceHeight() - 200
				int endY = startY - 500
				commonPage.scrollToText(data[1], 100, startY, 100, endY, false, GlobalVariable.G_timeoutMiniShort)
				'Select country'
				commonPage.tapText(data[1], GlobalVariable.G_timeoutLong, flowControl)
				Mobile.delay(3)
				'Select Done'
				commonPage.tapText(Done, GlobalVariable.G_timeoutLong, flowControl)
				Mobile.takeScreenshot()
				break
			case 'Army/Police ID':
				'Enter Army/Police ID'
				MobileDriver.setText(commonPage.txtEditNo(1), data[0], GlobalVariable.G_timeoutLong, flowControl)
				break
			case 'Business Registration Number':
				'Enter Business Registration Number'
				MobileDriver.setText(commonPage.txtEditNo(1), data[0], GlobalVariable.G_timeoutLong, flowControl)
				break
		}
		MobileDriver.takeScreenshot()
		'Select Continue'
		commonPage.tapText(Continue, GlobalVariable.G_timeoutLong, flowControl)

		'Wait next page'
		commonPage.waitLoading(GlobalVariable.G_timeoutMedium)
		MobileDriver.takeScreenshot()
	}

	//	boolean selectFav(String txt, FailureHandling flowControl = defaultFailureHandling) {
	//		MobileDriver.takeScreenshot()
	//		MobileDriver.tapEleByTestObject(this.imgSearch())
	//		MobileDriver.setTextNoObjAppium(txt)
	//		MobileDriver.takeScreenshot()
	//		MobileDriver.tapEleByTestObject(this.txtFirstList())
	//		MobileDriver.delay(10)
	//	}
}
