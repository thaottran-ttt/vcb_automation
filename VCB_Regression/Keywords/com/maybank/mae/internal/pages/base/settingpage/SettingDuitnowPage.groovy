package com.maybank.mae.internal.pages.base.settingpage

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
import com.maybank.common.MobileDriver
import com.maybank.mae.internal.pages.base.BasePage
import com.maybank.mae.internal.pages.base.CommonPage

import internal.GlobalVariable

public class SettingDuitnowPage extends BasePage {

	final static String DUITNOW_BLOCKED_LIST = 'DuitNow Blocked List'
	final static String CONFIRM = 'Confirm'
	final static String UNLOCK = 'Unblock'

	CommonPage commonPage = getPage(CommonPage)

	void tapDuitNowBlockedList(FailureHandling flowControl = defaultFailureHandling) {
		MobileDriver.tap(commonPage.lblDynamic(DUITNOW_BLOCKED_LIST), GlobalVariable.G_timeoutMedium, flowControl)
	}

	/**
	 * Unlocks the DuitNow transfer for a specified user.
	 *
	 * @param name          The name of the user for whom DuitNow is to be unlocked.
	 * @param uiPopupData   (Optional) A list of UI-related popup data used for verify.
	 *                      Defaults to an empty list if not provided.
	 * @param flowControl   (Optional) Specifies how to handle failures during the process.
	 *                      Defaults to the system's default failure handling mechanism.
	 */
	void unlockDuitNow(String name, List uiPopupData = [], FailureHandling flowControl = defaultFailureHandling) {
		if(MobileDriver.waitForElementPresent(commonPage.lblDynamic(name), GlobalVariable.G_timeoutShort, flowControl)) {
			MobileDriver.tap(commonPage.lblDynamic(name), GlobalVariable.G_timeoutMedium, flowControl)
		}else {
			MobileDriver.tap(commonPage.lblDynamic(UNLOCK), GlobalVariable.G_timeoutMedium, flowControl)
		}

		if(uiPopupData.size() > 0) {
			verifyElements(uiPopupData, commonPage.lblDynamicPath(), GlobalVariable.G_timeoutMedium, flowControl)
		}

		MobileDriver.tap(commonPage.lblDynamic(CONFIRM), GlobalVariable.G_timeoutMedium, flowControl)
	}
}
