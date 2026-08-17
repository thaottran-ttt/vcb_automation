package com.maybank.mae.internal.pages.base.sendandrequestpage
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import static org.assertj.core.api.Assertions.assertThatIllegalStateException
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
import com.maybank.assertions.BaseAssert
import com.maybank.common.MobileDriver
import com.maybank.mae.internal.pages.base.BasePage
import com.maybank.mae.internal.pages.base.CommonPage
import internal.GlobalVariable

public abstract class SendAndRequestPage extends BasePage{
	String duitNowRequest = 'DuitNow Request'
	abstract TestObject btnDuitNowRequest();
	public static CommonPage commonPage = getPage(CommonPage)

	/**
	 * Tap DuitNow Request
	 */
	void tapDuitNowRequest(FailureHandling flowControl = defaultFailureHandling) {
		Mobile.waitForElementPresent(commonPage.lblDynamic(duitNowRequest), GlobalVariable.G_timeoutLong, flowControl)
		MobileDriver.tap(this.btnDuitNowRequest(), GlobalVariable.G_timeoutMedium, flowControl)
	}
}
