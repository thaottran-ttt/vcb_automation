package com.vcb.m2u.internal.pages.base

import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.common.WebUiCommonHelper
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.vcb.common.WebUIKeyword
import org.openqa.selenium.Keys
import java.util.Arrays
import org.openqa.selenium.WebElement

import internal.GlobalVariable

public class WebSauceDemoPage extends WebBasePage {

	public static TestObject txtUsername() {
		return findTestObject('Object Repository/Website/SauceDemo/Login/txtUsername')
	}

	public static TestObject txtPassword() {
		return findTestObject('Object Repository/Website/SauceDemo/Login/txtPassword')
	}

	public static TestObject btnLogin() {
		return findTestObject('Object Repository/Website/SauceDemo/Login/btnLogin')
	}

	public static TestObject lblErrorMessage() {
		return findTestObject('Object Repository/Website/SauceDemo/Login/lblErrorMessage')
	}

	public static TestObject lblProductsTitle() {
		return findTestObject('Object Repository/Website/SauceDemo/Inventory/lblProductsTitle')
	}

	public static TestObject btnAddBackpack() {
		return findTestObject('Object Repository/Website/SauceDemo/Inventory/btnAddBackpack')
	}

	public static TestObject btnAddBikeLight() {
		return findTestObject('Object Repository/Website/SauceDemo/Inventory/btnAddBikeLight')
	}

	public static TestObject btnRemoveBikeLight() {
		return findTestObject('Object Repository/Website/SauceDemo/Inventory/btnRemoveBikeLight')
	}

	public static TestObject lblCartBadge() {
		return findTestObject('Object Repository/Website/SauceDemo/Common/lblCartBadge')
	}

	public static TestObject btnCart() {
		return findTestObject('Object Repository/Website/SauceDemo/Common/btnCart')
	}

	public static TestObject lblBackpackInCart() {
		return findTestObject('Object Repository/Website/SauceDemo/Cart/lblBackpackInCart')
	}

	public static TestObject btnRemoveBackpack() {
		return findTestObject('Object Repository/Website/SauceDemo/Cart/btnRemoveBackpack')
	}

	public static TestObject btnCheckout() {
		return findTestObject('Object Repository/Website/SauceDemo/Checkout/btnCheckout')
	}

	public static TestObject txtFirstName() {
		return findTestObject('Object Repository/Website/SauceDemo/Checkout/txtFirstName')
	}

	public static TestObject txtLastName() {
		return findTestObject('Object Repository/Website/SauceDemo/Checkout/txtLastName')
	}

	public static TestObject txtPostalCode() {
		return findTestObject('Object Repository/Website/SauceDemo/Checkout/txtPostalCode')
	}

	public static TestObject btnContinue() {
		return findTestObject('Object Repository/Website/SauceDemo/Checkout/btnContinue')
	}

	public static TestObject btnFinish() {
		return findTestObject('Object Repository/Website/SauceDemo/Checkout/btnFinish')
	}

	public static TestObject lblCompleteHeader() {
		return findTestObject('Object Repository/Website/SauceDemo/Checkout/lblCompleteHeader')
	}

	void openLoginPage(String url = GlobalVariable.G_url, FailureHandling flowControl = defaultFailureHandling) {
		WebUI.navigateToUrl(url)
		waitForElementVisible(this.txtUsername(), GlobalVariable.G_timeoutMedium, flowControl)
	}

	void login(String username, String password, FailureHandling flowControl = defaultFailureHandling) {
		setText(this.txtUsername(), username, flowControl)
		setText(this.txtPassword(), password, flowControl)
		WebUIKeyword.click(this.btnLogin(), flowControl)
	}

	void loginAsStandardUser(FailureHandling flowControl = defaultFailureHandling) {
		openLoginPage(GlobalVariable.G_url, flowControl)
		login('standard_user', 'secret_sauce', flowControl)
		waitForElementVisible(this.lblProductsTitle(), GlobalVariable.G_timeoutMedium, flowControl)
	}

	String loginAndGetErrorMessage(String username, String password, FailureHandling flowControl = defaultFailureHandling) {
		openLoginPage(GlobalVariable.G_url, flowControl)
		login(username, password, flowControl)
		waitForElementVisible(this.lblErrorMessage(), GlobalVariable.G_timeoutMedium, flowControl)
		return getText(this.lblErrorMessage(), flowControl)
	}

	void addBackpackToCart(FailureHandling flowControl = defaultFailureHandling) {
		waitForElementVisible(this.btnAddBackpack(), GlobalVariable.G_timeoutMedium, flowControl)
		waitForElementClickable(this.btnAddBackpack(), GlobalVariable.G_timeoutMedium, flowControl)
		WebUI.scrollToElement(this.btnAddBackpack(), GlobalVariable.G_timeoutShort)
		WebUI.enhancedClick(this.btnAddBackpack(), FailureHandling.OPTIONAL)

		boolean isBackpackAdded = WebUI.waitForElementVisible(this.btnRemoveBackpack(), GlobalVariable.G_timeoutShort, FailureHandling.OPTIONAL)
		if (!isBackpackAdded) {
			WebElement addBackpackElement = WebUiCommonHelper.findWebElement(this.btnAddBackpack(), GlobalVariable.G_timeoutShort)
			WebUI.executeJavaScript('arguments[0].click();', Arrays.asList(addBackpackElement))
			isBackpackAdded = WebUI.waitForElementVisible(this.btnRemoveBackpack(), GlobalVariable.G_timeoutShort, FailureHandling.OPTIONAL)
		}

		boolean isBadgeVisible = WebUI.waitForElementVisible(this.lblCartBadge(), GlobalVariable.G_timeoutShort, FailureHandling.OPTIONAL)
		if (!isBackpackAdded && !isBadgeVisible) {
			handleError('Unable to add Sauce Labs Backpack to cart. Inventory state did not change after enhanced and JavaScript click.', flowControl)
		}
	}

	void addBikeLightToCart(FailureHandling flowControl = defaultFailureHandling) {
		waitForElementVisible(this.btnAddBikeLight(), GlobalVariable.G_timeoutMedium, flowControl)
		waitForElementClickable(this.btnAddBikeLight(), GlobalVariable.G_timeoutMedium, flowControl)
		WebUI.scrollToElement(this.btnAddBikeLight(), GlobalVariable.G_timeoutShort)
		WebUI.enhancedClick(this.btnAddBikeLight(), FailureHandling.OPTIONAL)

		boolean isBikeLightAdded = WebUI.waitForElementVisible(this.btnRemoveBikeLight(), GlobalVariable.G_timeoutShort, FailureHandling.OPTIONAL)
		if (!isBikeLightAdded) {
			WebElement addBikeLightElement = WebUiCommonHelper.findWebElement(this.btnAddBikeLight(), GlobalVariable.G_timeoutShort)
			WebUI.executeJavaScript('arguments[0].click();', Arrays.asList(addBikeLightElement))
			isBikeLightAdded = WebUI.waitForElementVisible(this.btnRemoveBikeLight(), GlobalVariable.G_timeoutShort, FailureHandling.OPTIONAL)
		}

		boolean isBadgeVisible = WebUI.waitForElementVisible(this.lblCartBadge(), GlobalVariable.G_timeoutShort, FailureHandling.OPTIONAL)
		if (!isBikeLightAdded && !isBadgeVisible) {
			handleError('Unable to add Sauce Labs Bike Light to cart. Inventory state did not change after enhanced and JavaScript click.', flowControl)
		}
	}

	void verifyCartBadge(String expected, FailureHandling flowControl = defaultFailureHandling) {
		waitForElementVisible(this.lblCartBadge(), GlobalVariable.G_timeoutMedium, flowControl)
		WebUI.verifyElementText(this.lblCartBadge(), expected, flowControl)
	}

	void goToCart(FailureHandling flowControl = defaultFailureHandling) {
		waitForElementVisible(this.btnCart(), GlobalVariable.G_timeoutMedium, flowControl)
		waitForElementClickable(this.btnCart(), GlobalVariable.G_timeoutMedium, flowControl)
		WebUI.scrollToElement(this.btnCart(), GlobalVariable.G_timeoutShort)
		WebUI.enhancedClick(this.btnCart(), FailureHandling.OPTIONAL)

		boolean isInCartPage = WebUI.waitForElementVisible(this.btnCheckout(), GlobalVariable.G_timeoutShort, FailureHandling.OPTIONAL)
		if (!isInCartPage) {
			WebElement cartElement = WebUiCommonHelper.findWebElement(this.btnCart(), GlobalVariable.G_timeoutShort)
			WebUI.executeJavaScript('arguments[0].click();', Arrays.asList(cartElement))
			isInCartPage = WebUI.waitForElementVisible(this.btnCheckout(), GlobalVariable.G_timeoutMedium, FailureHandling.OPTIONAL)
		}

		if (!isInCartPage) {
			handleError('Unable to navigate to cart page. Checkout button is not visible after enhanced and JavaScript click on cart icon.', flowControl)
		}
	}

	void verifyBackpackInCart(FailureHandling flowControl = defaultFailureHandling) {
		verifyElementPresent(this.lblBackpackInCart(), flowControl)
	}

	void removeBackpackFromCart(FailureHandling flowControl = defaultFailureHandling) {
		waitForElementVisible(this.btnRemoveBackpack(), GlobalVariable.G_timeoutMedium, flowControl)
		WebUIKeyword.click(this.btnRemoveBackpack(), flowControl)
	}

	void verifyBackpackNotInCart(FailureHandling flowControl = defaultFailureHandling) {
		waitForElementNotPresent(this.lblBackpackInCart(), GlobalVariable.G_timeoutMedium, flowControl)
	}

	private void enterTextAndVerify(TestObject field, String value, String fieldName, FailureHandling flowControl = defaultFailureHandling) {
		waitForElementVisible(field, GlobalVariable.G_timeoutMedium, flowControl)
		waitForElementClickable(field, GlobalVariable.G_timeoutMedium, flowControl)
		WebUI.scrollToElement(field, GlobalVariable.G_timeoutShort)
		WebUI.click(field, FailureHandling.OPTIONAL)
		WebUI.clearText(field, FailureHandling.OPTIONAL)
		WebUI.setText(field, value, FailureHandling.OPTIONAL)
		WebUI.sendKeys(field, Keys.TAB.toString(), FailureHandling.OPTIONAL)

		String actualValue = WebUI.getAttribute(field, 'value', FailureHandling.OPTIONAL)
		if ((actualValue ?: '').trim() != value) {
			WebElement inputElement = WebUiCommonHelper.findWebElement(field, GlobalVariable.G_timeoutShort)
			WebUI.executeJavaScript("const element = arguments[0]; const newValue = arguments[1]; const setter = Object.getOwnPropertyDescriptor(window.HTMLInputElement.prototype, 'value').set; setter.call(element, newValue); element.dispatchEvent(new Event('input', { bubbles: true })); element.dispatchEvent(new Event('change', { bubbles: true })); element.dispatchEvent(new Event('blur', { bubbles: true }));", Arrays.asList(inputElement, value))
			actualValue = WebUI.getAttribute(field, 'value', FailureHandling.OPTIONAL)
		}

		if ((actualValue ?: '').trim() != value) {
			handleError("Unable to set value for ${fieldName}. Expected '${value}', but actual value is '${actualValue}'.", flowControl)
		}
	}

	private void clickAndVerifyNextState(TestObject button, TestObject nextStateObject, String errorMessage, FailureHandling flowControl = defaultFailureHandling) {
		waitForElementVisible(button, GlobalVariable.G_timeoutMedium, flowControl)
		waitForElementClickable(button, GlobalVariable.G_timeoutMedium, flowControl)
		WebUI.scrollToElement(button, GlobalVariable.G_timeoutShort)
		WebUI.enhancedClick(button, FailureHandling.OPTIONAL)

		boolean stateChanged = WebUI.waitForElementVisible(nextStateObject, GlobalVariable.G_timeoutShort, FailureHandling.OPTIONAL)
		if (!stateChanged) {
			WebElement buttonElement = WebUiCommonHelper.findWebElement(button, GlobalVariable.G_timeoutShort)
			WebUI.executeJavaScript('arguments[0].click();', Arrays.asList(buttonElement))
			stateChanged = WebUI.waitForElementVisible(nextStateObject, GlobalVariable.G_timeoutMedium, FailureHandling.OPTIONAL)
		}

		if (!stateChanged) {
			handleError(errorMessage, flowControl)
		}
	}

	void completeCheckout(String firstName, String lastName, String postalCode, FailureHandling flowControl = defaultFailureHandling) {
		clickAndVerifyNextState(
			this.btnCheckout(),
			this.txtFirstName(),
			'Unable to navigate to checkout information page. First name field is not visible after enhanced and JavaScript click on checkout button.',
			flowControl
		)

		enterTextAndVerify(this.txtFirstName(), firstName, 'First Name', flowControl)
		enterTextAndVerify(this.txtLastName(), lastName, 'Last Name', flowControl)
		enterTextAndVerify(this.txtPostalCode(), postalCode, 'Postal Code', flowControl)

		clickAndVerifyNextState(
			this.btnContinue(),
			this.btnFinish(),
			'Unable to navigate to checkout overview page. Finish button is not visible after enhanced and JavaScript click on continue button.',
			flowControl
		)

		clickAndVerifyNextState(
			this.btnFinish(),
			this.lblCompleteHeader(),
			'Unable to complete checkout. Complete header is not visible after enhanced and JavaScript click on finish button.',
			flowControl
		)
	}
}
