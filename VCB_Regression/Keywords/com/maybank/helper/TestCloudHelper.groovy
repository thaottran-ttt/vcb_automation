package com.maybank.helper

import org.openqa.selenium.WebDriver
import org.openqa.selenium.remote.RemoteWebDriver

import com.katalon.testcloud.TestCloudFactory
import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.webui.driver.DriverFactory

import internal.GlobalVariable

public class TestCloudHelper {
	public static RemoteWebDriver remoteWebDriver
	public static TestCloudFactory testCloudFactory  = new TestCloudFactory()
	
	/**
	 * Creates a TestCloud Windows ChromeDriver instance.
	 *
	 * @param apiKey         The API key for authenticating with TestCloud.
	 * @param orgId          The organization ID associated with TestCloud.
	 * @param browserVersion The desired browser version (default: "131").
	 * @return A configured WebDriver instance.
	 * @throws Exception If an error occurs while initializing the driver.
	 */
	@Keyword
static public WebDriver createTestCloudWindowsChromeDriver() {
    // Decide browser version: if null/blank -> latest, else use profile value (e.g., "138")
    String browserVersion = GlobalVariable.G_browserVersion
    if (browserVersion == null || browserVersion.toString().trim().isEmpty()) {
        browserVersion = 'latest'
    }

    // Open a browser instance on TestCloud
    remoteWebDriver = testCloudFactory.openBrowser(
        GlobalVariable.G_browserPlatformName,
        GlobalVariable.G_browserName,
        browserVersion,
        false
    )

    // Switch Katalon to use the remote driver
    DriverFactory.changeWebDriver(remoteWebDriver)

    // Optional: keep global variable consistent with what we requested
    GlobalVariable.G_browserVersion = browserVersion

    return remoteWebDriver
}
	
	@Keyword
	static public WebDriver closeTestCloudBrowser() {
		testCloudFactory.closeBrowser()
	}
}
