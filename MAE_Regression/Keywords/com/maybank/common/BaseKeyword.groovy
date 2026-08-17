package com.maybank.common

import org.openqa.selenium.Capabilities
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.appium.driver.AppiumDriverManager
import com.kms.katalon.core.configuration.RunConfiguration
import com.kms.katalon.core.exception.StepFailedException
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webui.driver.DriverFactory
import com.maybank.helper.TestCloudHelper

import internal.GlobalVariable

public class BaseKeyword {

	public static FailureHandling defaultFailureHandling = RunConfiguration.getDefaultFailureHandling()
	/**
	 * Exception Handling
	 * */
	@Keyword
	public static void handleError(String message, FailureHandling flowControl){
		boolean isMobileStarted = false
		try {
			isMobileStarted = MobileDriverFactory.getDriver() != null
		} catch (Exception e) {
			isMobileStarted = false
		}
		if(!flowControl)
			throw new StepFailedException(message)

		switch(flowControl){
			case FailureHandling.STOP_ON_FAILURE:
				if(isMobileStarted) {
					MobileDriver.takeScreenshot()
				}else {
					WebUIKeyword.takeScreenshot()
				}
				if(GlobalVariable.G_isRunWebsiteAndMobile && isMobileStarted) {
					'Delay the mobile thread to finish the test case.'
					MobileThread.setMobileThreadDelay()
					sleep(2000)
				}

				KeywordUtil.markFailed(message)
				throw new Exception(message)
				break
			case FailureHandling.CONTINUE_ON_FAILURE:
				KeywordUtil.markFailed(message)
				break
			case FailureHandling.OPTIONAL:
				KeywordUtil.markWarning(message)
				break
			default:
				KeywordUtil.markFailedAndStop(message)
				break
		}
	}

	@Keyword
	public static void handleError(Exception ex, FailureHandling flowControl = defaultFailureHandling){
		handleError(ex.message, flowControl)
	}

	/**
	 * Log Info
	 * */
	@Keyword
	public static void info(message){
		KeywordUtil.logInfo(message)
	}

	@Keyword
	public static void markPassed(message){
		KeywordUtil.markPassed(message)
	}

	@Keyword
	public static boolean runFromTestCloud() {
		Map<String, Object> references = RunConfiguration.getDriverPreferencesProperties()

		String driverName = references.keySet()[0]
		Map refInfo = references.getAt(driverName)
		Map options = refInfo.getAt("katalon:option") ?: refInfo.getAt("katalon:options")
		boolean runFromTestCloud = options?.getAt("runFromTestCloud")
		boolean runKRE = options?.getAt("organizationId")
		return runFromTestCloud || runKRE
	}

	/**
	 * Retrieves the execution session ID for a remote web driver.
	 *
	 * This method fetches the capabilities of the remote web driver using the `TestCloudHelper.remoteWebDriver` instance.
	 * It then accesses the "katalon:options" capability to extract the "executionSessionId" property.
	 *
	 * @return The execution session ID as a string, or null if the capability or property is unavailable.
	 */
	public static String getExecutionSessionIdRemoteWeb() {
		Capabilities capabilities = TestCloudHelper.remoteWebDriver?.getCapabilities()
		def katalonOptions = capabilities.getCapability("katalon:options")
		return katalonOptions?.getAt("executionSessionId")
	}

	/**
	 * Connects to an already running Chrome instance using the Chrome DevTools Protocol (CDP).
	 *
	 * This method allows interaction with a running Chrome browser instance by specifying its remote debugging port.
	 * It uses Katalon's ChromeDriver to establish the connection and updates Katalon's WebDriver instance to use the new driver.
	 *
	 * @param port The port of the running Chrome instance (default: 9222).
	 * @return A WebDriver instance connected to the running Chrome instance.
	 */
	@Keyword
	public static WebDriver connectToRunningClient(int port = 9222) {
		// Get path to Katalon's chromedriver
		String chromeDriverPath = DriverFactory.getChromeDriverPath()

		// Set up the chromedriver path
		System.setProperty("webdriver.chrome.driver", chromeDriverPath)

		// Set the host and port of the running chrome instance
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--remote-allow-origins=*")
		options.setExperimentalOption("debuggerAddress", "localhost:$port")

		// Create the chromedriver
		WebDriver driver = new ChromeDriver(options)

		// Change Katalon's default chromedriver to this one
		DriverFactory.changeWebDriver(driver)

		return driver
	}

	static boolean isAppiumServerRunning() {
		try {
			return AppiumDriverManager.isAppiumServerStarted(0)
		}catch(ex) {
			return true
		}
	}

	static void startApplication() {
		if (runFromTestCloud()) {
			Mobile.startApplication(GlobalVariable.G_appIDTestCloud, false)
		} else {
			Mobile.startExistingApplication(GlobalVariable.G_appId)
		}
	}


	@Keyword
	public static void setFailureHandling(String flowControl){
		switch(flowControl) {
			case 'STOP':
				this.defaultFailureHandling = FailureHandling.STOP_ON_FAILURE
				break
			case 'CONTINUE':
				this.defaultFailureHandling = FailureHandling.CONTINUE_ON_FAILURE
				break
			case 'OPTION':
				this.defaultFailureHandling = FailureHandling.OPTIONAL
				break
			default:
				this.defaultFailureHandling = RunConfiguration.getDefaultFailureHandling()
				break
		}
	}

	@Keyword
	public static void testFlowControl(FailureHandling flowControl = defaultFailureHandling){
		Mobile.comment(flowControl.toString())
	}
}

public enum FlowControlEnum{
	STOP, CONTINUE, OPTION
}

public enum PlatformEnum{
	WEBSITE, MOBILE
}