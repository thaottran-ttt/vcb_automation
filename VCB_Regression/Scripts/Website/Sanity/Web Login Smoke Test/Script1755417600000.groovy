import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import com.vcb.common.WebUIKeyword
import com.vcb.m2u.internal.pages.base.WebBasePage
import com.vcb.m2u.internal.pages.base.WebSanityLoginPage

import internal.GlobalVariable as GlobalVariable

'Browser is already opened and navigated to G_url (https://acme-test.uipath.com/login under the sanity-web profile) by TestListener @BeforeTestCase'
WebSanityLoginPage webSanityLoginPage = WebBasePage.getPage(WebSanityLoginPage)

try {
	'Fill in dummy credentials and submit - login is expected to fail, that is fine, we only want to exercise the framework end to end'
	String errorMessage = webSanityLoginPage.login(email, password)
	WebUI.comment("Login page responded with: ${errorMessage}")
} catch (Exception ex) {
	'Something in the framework/page interaction failed - capture a screenshot for evidence, then rethrow'
	WebUIKeyword.takeScreenshot()
	throw ex
}
