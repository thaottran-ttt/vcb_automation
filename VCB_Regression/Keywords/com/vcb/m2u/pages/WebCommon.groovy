package com.vcb.m2u.pages

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions
import org.openqa.selenium.remote.CapabilityType
import org.openqa.selenium.safari.SafariDriver
import org.openqa.selenium.safari.SafariOptions

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import com.vcb.common.BaseKeyword
import com.vcb.common.BrowserTypeEnum
import com.vcb.common.MobileDriver
import com.vcb.common.WebUIKeyword
import com.vcb.helper.TestCloudHelper
import com.vcb.m2u.internal.pages.base.WebBasePage
import com.vcb.m2u.internal.pages.base.WebCommonPage

import internal.GlobalVariable

public class WebCommon extends BaseKeyword{

	WebCommonPage webCommonPage = WebBasePage.getPage(WebCommonPage)

	static void openSafariBrowser() {
		SafariOptions options = new SafariOptions()

		// Disable certificate verification
		options.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true)

		// Start Safari with the custom options
		WebDriver driver = new SafariDriver(options)

		// Navigate to the URL
		driver.get(GlobalVariable.G_url)
		DriverFactory.changeWebDriver(driver)
	}

	static void openBrowser(url) {
		if(WebBasePage.executionWebSetting.browserType == BrowserTypeEnum.SAFARI_DRIVER) {
			this.openSafariBrowser()
		}else {
			WebUI.openBrowser(GlobalVariable.G_url)
		}
		WebUI.maximizeWindow()
	}

	@Keyword
	void openBrowserAndBypassSecure(String url, boolean isRunWebsiteAndMobile) {
		if(this.runFromTestCloud()) {
			if(isRunWebsiteAndMobile && !GlobalVariable.G_isTacM2U) {
				TestCloudHelper.createTestCloudWindowsChromeDriver()
				KeywordUtil.logInfo("Web Execution Session ID: " + this.getExecutionSessionIdRemoteWeb())
				WebUI.navigateToUrl(url)
			}else {
				this.openBrowser(url)
			}
		}else {
			if(GlobalVariable.G_shouldRunSingleBrowser) {
				this.connectToRunningClient()
				return
			}else {
				this.openBrowser(url)
			}
		}
		webCommonPage.bypassSecureURL(GlobalVariable.G_timeoutShort)
	}
}
