package com.vcb.common

import org.openqa.selenium.By
import org.openqa.selenium.Keys
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.interactions.Actions

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testobject.SelectorMethod
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.common.WebUiCommonHelper
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.vcb.helper.TestCloudHelper

import internal.GlobalVariable

public class WebUIKeyword extends BaseKeyword {

	@Keyword
	static void openBrowser(url) {
		WebUI.openBrowser(url)
		WebUI.maximizeWindow()
	}

	@Keyword
	static void closeBrowser() {
		TestCloudHelper.closeTestCloudBrowser()
	}

	@Keyword
	static boolean waitForElementPresent(TestObject obj, int timeout, FailureHandling flowControl = defaultFailureHandling) {
		info "Wait for test object ${toStringAndLocatorTestObject(obj)}"
		return WebUI.waitForElementPresent(obj, timeout, flowControl)
	}

	@Keyword
	static boolean waitForElementClickable(TestObject obj, int timeout, FailureHandling flowControl = defaultFailureHandling) {
		info "Wait for test object clickable ${toStringAndLocatorTestObject(obj)}"
		return WebUI.waitForElementClickable(obj, timeout, flowControl)
	}

	@Keyword
	static boolean waitForElementVisible(TestObject obj, int timeout, FailureHandling flowControl = defaultFailureHandling) {
		info "Wait for test object visible ${toStringAndLocatorTestObject(obj)}"
		return WebUI.waitForElementVisible(obj, timeout, flowControl)
	}

	@Keyword
	static boolean waitForElementNotPresent(TestObject obj, int timeout, FailureHandling flowControl = defaultFailureHandling) {
		info "Wait Not Present for test object ${toStringAndLocatorTestObject(obj)}"
		return WebUI.waitForElementNotPresent(obj, timeout, flowControl)
	}

	static String toStringAndLocatorTestObject(TestObject obj) {
		String xpath = obj.selectorCollection.get(SelectorMethod.XPATH)
		return obj.toString() + xpath.equals('') ? xpath : obj.findPropertyValue("xpath");
	}

	@Keyword
	static def click(TestObject obj, FailureHandling flowControl = defaultFailureHandling) {
		info "Click for test object ${toStringAndLocatorTestObject(obj)}"
		try {
			WebUI.click(obj)
		} catch (Exception ex) {
			handleError("Unable to click ${toStringAndLocatorTestObject(obj)}, ${ex.toString()}", flowControl)
		}
	}

	@Keyword
	static void setText(TestObject obj, String textContent, FailureHandling flowControl = defaultFailureHandling) {
		info "Set text for test object ${toStringAndLocatorTestObject(obj)}"
		try{
			WebUI.setText(obj, textContent, flowControl)
		}
		catch(ex){
			handleError(ex, flowControl)
		}
	}

	@Keyword
	static void scrollToTestObject(TestObject obj, int timeout = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		info "scroll to test object ${toStringAndLocatorTestObject(obj)}"
		try{
			WebUI.scrollToElement(obj, timeout)
		}
		catch(ex){
			handleError(ex, flowControl)
		}
	}

	@Keyword
	static void setTextWithClear(TestObject obj, String textContent, boolean isFastClearEnabled = true, FailureHandling flowControl = defaultFailureHandling) {
		info "Set text for test object ${toStringAndLocatorTestObject(obj)}"
		try{
			if(isFastClearEnabled) {
				WebUI.clearText(obj)
			}else {
				String oldText = this.getAttributeValue(obj)
				for(int i = 0; i< oldText.length(); i++) {
					WebUI.sendKeys(obj, Keys.BACK_SPACE.toString())
				}
			}

			WebUI.setText(obj, textContent, flowControl)
		}
		catch(ex){
			handleError(ex, flowControl)
		}
	}

	@Keyword
	static void setEncryptedText(TestObject obj, String textContent, FailureHandling flowControl = defaultFailureHandling) {
		info "Set encrypted text for test object ${obj}"
		try{
			WebUI.setEncryptedText(obj, textContent)
		}
		catch(ex){
			handleError(ex, flowControl)
		}
	}

	@Keyword
	static public String getText(TestObject obj, FailureHandling flowControl = defaultFailureHandling){
		info("Get text for test object ${toStringAndLocatorTestObject(obj)}")
		try{
			return WebUI.getText(obj, flowControl)
		}
		catch(ex){
			handleError(ex, flowControl)
		}
	}

	@Keyword
	static public String getAttributeValue(TestObject obj, String att = 'value', FailureHandling flowControl = defaultFailureHandling){
		info("Get Attribute for test object ${obj}")
		try{
			return WebUI.getAttribute(obj, att, flowControl)
		}
		catch(ex){
			handleError(ex, flowControl)
		}
	}

	@Keyword
	static public void verifyElementVisible(TestObject obj, FailureHandling flowControl = defaultFailureHandling){
		info("verifyElementVisible for test object ${toStringAndLocatorTestObject(obj)}")
		try{
			WebUI.verifyElementVisible(obj, flowControl)
		}
		catch(ex){
			handleError("Unable to verifyElementVisible ${toStringAndLocatorTestObject(obj)}, ${ex.toString()}", flowControl)
		}
	}

	@Keyword
	static public void verifyElementPresent(TestObject obj, FailureHandling flowControl = defaultFailureHandling){
		info("verifyElementVisible for test object ${toStringAndLocatorTestObject(obj)}")
		try{
			WebUI.verifyElementPresent(obj, GlobalVariable.G_timeoutShort)
		}
		catch(ex){
			handleError("Unable to verifyElementPresent ${toStringAndLocatorTestObject(obj)}, ${ex.toString()}", flowControl)
		}
	}

	@Keyword
	static public void takeScreenshot(){
		WebUI.takeScreenshot(FailureHandling.OPTIONAL)
	}

	@Keyword
	static public void delay(Number timeout){
		info("Delay: " + timeout)
		WebUI.delay(timeout)
	}

	/**
	 * Get list of text values from elements identified by XPath
	 * @param xpath - The XPath of the elements to be found
	 * @return List of strings containing the text of each element
	 */
	@Keyword
	static def getListOfTextByXPath(String xpath) {
		WebDriver driver = DriverFactory.getWebDriver()
		List<WebElement> elements = driver.findElements(By.xpath(xpath))
		List<String> elementTexts = new ArrayList<>()
		for (WebElement element : elements) {
			elementTexts.add(element.getText())
		}
		return elementTexts
	}

	/**
	 * getTextsByXpaths
	 * get all item in list view
	 * testObject: list View Item
	 * xpaths is xpath list of one item 
	 */
	@Keyword
	static List getTextsByXpaths(TestObject testObject, List xpaths, int timeout, FailureHandling flowControl = defaultFailureHandling) {
		List<WebElement> eleList = MobileDriver.driver.findElements(By.xpath(MobileDriver.getLocator(testObject)))
		if(eleList.size() == 0) return null
		List result = []
		for(WebElement ele : eleList) {
			List objectItem = []
			for(String xpath: xpaths) {
				String value = ele.findElement(By.xpath(".${xpath}")).getText()
				objectItem.add(value)
			}
			result.add(objectItem)
		}

		return result
	}

	@Keyword
	static def clickWithoutScrollW3C(TestObject obj, FailureHandling flowControl = defaultFailureHandling) {
		info "Clicking without scrolling using W3C Actions for test object ${toStringAndLocatorTestObject(obj)}"
		try {
			// Get the WebElement from TestObject
			WebElement element = WebUiCommonHelper.findWebElement(obj, 10)

			// Get the WebDriver instance
			def driver = DriverFactory.getWebDriver()

			// Create an Actions object using W3C Actions
			def actions = new Actions(driver)

			// Directly click on the element using W3C actions
			actions.moveToElement(element).click().build().perform()
		} catch (Exception ex) {
			handleError("Unable to click ${toStringAndLocatorTestObject(obj)}, ${ex.toString()}", flowControl)
		}
	}

	private static boolean clickWithFallback(TestObject button, FailureHandling flowControl = defaultFailureHandling, int timeout = GlobalVariable.G_timeoutMedium) {
		waitForElementVisible(button, timeout, flowControl)
		waitForElementClickable(button, timeout, flowControl)
		WebUI.scrollToElement(button, GlobalVariable.G_timeoutShort)
		WebUI.enhancedClick(button, FailureHandling.OPTIONAL)
		return true
	}

	@Keyword
	static boolean clickAndVerifyObjectAppears(TestObject button, TestObject expectedObject, String errorMessage,
			FailureHandling flowControl = defaultFailureHandling) {
		clickWithFallback(button, flowControl)

		boolean isAppeared = WebUI.waitForElementVisible(expectedObject, GlobalVariable.G_timeoutShort, FailureHandling.OPTIONAL)
		if (!isAppeared) {
			WebElement buttonElement = WebUiCommonHelper.findWebElement(button, GlobalVariable.G_timeoutShort)
			WebUI.executeJavaScript('arguments[0].click();', java.util.Arrays.asList(buttonElement))
			isAppeared = WebUI.waitForElementVisible(expectedObject, GlobalVariable.G_timeoutMedium, FailureHandling.OPTIONAL)
		}

		if (!isAppeared) {
			handleError(errorMessage, flowControl)
		}

		return isAppeared
	}

	@Keyword
	static boolean clickAndVerifyClickedObjectChanged(TestObject clickedObject, TestObject changedObject, String errorMessage,
			FailureHandling flowControl = defaultFailureHandling) {
		return clickAndVerifyObjectAppears(clickedObject, changedObject, errorMessage, flowControl)
	}

	@Keyword
	static boolean clickAndVerifyUrlChanged(TestObject button, String expectedUrlPart, String errorMessage,
			FailureHandling flowControl = defaultFailureHandling) {
		clickWithFallback(button, flowControl)

		boolean isUrlChanged = (WebUI.getUrl(FailureHandling.OPTIONAL) ?: '').contains(expectedUrlPart)
		if (!isUrlChanged) {
			WebElement buttonElement = WebUiCommonHelper.findWebElement(button, GlobalVariable.G_timeoutShort)
			WebUI.executeJavaScript('arguments[0].click();', java.util.Arrays.asList(buttonElement))
			WebUI.delay(1)
			isUrlChanged = (WebUI.getUrl(FailureHandling.OPTIONAL) ?: '').contains(expectedUrlPart)
		}

		if (!isUrlChanged) {
			handleError(errorMessage, flowControl)
		}

		return isUrlChanged
	}
}
