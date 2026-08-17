import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

import com.maybank.m2u.internal.constants.WebCommonConstants as WCC
import com.maybank.m2u.internal.pages.base.WebBasePage
import com.maybank.m2u.internal.pages.base.WebCommonPage as WCP

'Verify container and background color'
WebUI.verifyElementVisible(WCP.lblContainClass(WCC.TOAST_MESSAGE_CONTAINER_CLASS))
assert WebUI.getCSSValue(WCP.lblContainClass(WCC.TOAST_MESSAGE_CONTAINER_CLASS), WCC.TOAST_MESSAGE_CONTAINER_CSS_CHECK) == WCC.TOAST_MESSAGE_CONTAINER_BACKGROUND_COLOR

'Verify title string'
if (titleString != '') {
	WebUI.verifyElementVisible(WCP.lblContainClass(WCC.TOAST_MESSAGE_TITLE_CLASS))
	assert WebUI.getText(WCP.lblContainClass(WCC.TOAST_MESSAGE_TITLE_CLASS)) == titleString
}

'Verify message string'
if (messageString != '') {
	WebUI.verifyElementVisible(WCP.lblContainClass(WCC.TOAST_MESSAGE_MESSAGE_CLASS))
	assert WebUI.getText(WCP.lblContainClass(WCC.TOAST_MESSAGE_MESSAGE_CLASS)) == messageString
}

'Verify close button'
WebUI.click(WCP.lblContainClass(WCC.TOAST_MESSAGE_CONTAINER_CLOSE_CLASS))
WebUI.verifyElementNotPresent(WCP.lblContainClass(WCC.TOAST_MESSAGE_CONTAINER_CLASS), GlobalVariable.G_timeoutMiniShort)