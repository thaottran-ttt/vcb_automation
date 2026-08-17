package com.vcb.mobile.internal.pages.base

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
import com.vcb.assertions.BaseAssert
import internal.GlobalVariable

public abstract class NotificationHomePage extends BasePage{

	abstract TestObject fraGroupItem()

	CommonPage commonPage = getPage(CommonPage)

	//UI Static
	String UIGotIt = 'Got It'
	List<String> UITransferType = [
		'DuitNow',
		'GIRO',
		'IBFT',
		'3rd party'
	]
	String UIVerifyText = "You've received a"

	public verifyNotification(FailureHandling flowControl = defaultFailureHandling) {
		'Select Got It Button'
		if(MobileDriver.waitForElementPresent(commonPage.lblDynamic(UIGotIt), GlobalVariable.G_timeoutMedium, FailureHandling.OPTIONAL)) {
			MobileDriver.tap(commonPage.lblDynamic(UIGotIt), GlobalVariable.G_timeoutLong, flowControl)
		}
		'Wait Transfer Type Present'
		for(int i=0; i<UITransferType.size(); i++) {
			if(MobileDriver.waitForElementPresent(commonPage.lblStartsWith("You've received a " + UITransferType[i]), GlobalVariable.G_timeoutShort, FailureHandling.OPTIONAL)) {
				return true
			}
		}
		return false
	}

	boolean verifyNotification(String title, String startMessage, String message, FailureHandling flowControl = defaultFailureHandling) {
		'Select Got It Button'
		if(MobileDriver.waitForElementPresent(commonPage.lblDynamic(UIGotIt), GlobalVariable.G_timeoutMedium, FailureHandling.OPTIONAL)) {
			MobileDriver.tap(commonPage.lblDynamic(UIGotIt), GlobalVariable.G_timeoutLong, flowControl)
		}

		Mobile.delay(1)
		'Verify notification title'
		MobileDriver.verifyElementExist(commonPage.lblAnyText(title), GlobalVariable.G_timeoutLong)

		'Verify notification message'
		MobileDriver.verifyElementExist(commonPage.lblDynamic(message), GlobalVariable.G_timeoutMedium, flowControl)
	}

	public backToHome(FailureHandling flowControl = defaultFailureHandling) {
		'Select Got It Button'
		if(MobileDriver.waitForElementPresent(commonPage.lblDynamic(UIGotIt), GlobalVariable.G_timeoutMedium, FailureHandling.OPTIONAL)) {
			MobileDriver.tap(commonPage.lblDynamic(UIGotIt), GlobalVariable.G_timeoutLong, flowControl)
		}
		Mobile.delay(1)
		MobileDriver.tap(commonPage.imgNoDynamic(1), GlobalVariable.G_timeoutLong, flowControl)
	}
}
