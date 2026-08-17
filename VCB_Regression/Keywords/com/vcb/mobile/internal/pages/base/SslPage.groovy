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
import com.vcb.mobile.internal.pages.base.HomePage

import com.vcb.common.MobileDriver

import internal.GlobalVariable

public abstract class SslPage extends BasePage{
	
	abstract TestObject imgArrowDropdown()
	
	//Action
	static final String NEXT_BUTTON = 'Next'
	static final String GET_STARTED_BUTTON = 'Get Started'
	static final String UNDERSTAND_BUTTON = 'I Understand'
	static final String SEARCH_BOX = 'Food, drinks, groceries...'
	static final String DONE = 'Done'
	static final String ADD_TO_CART = 'to Cart'
	static final String VIEW_CART = 'View Cart'
	static final String ENTER_PICK_UP = 'Enter Pickup Details'
	static final String CHECKOUT = 'Checkout'
	static final String PAY = 'Pay'
	static final String SAVE = "Save"
	static final String CONFIRM = "Confirm"
	static final String ADD_BOOK = "Address Book"
	
	//Text
	static final String FIRST_TEXT = 'Support local, order from Lokal'
	static final String SECOND_TEXT = '0% commission charges'
	static final String THIRD_TEXT = 'Secure and Reliable'
	static final String DISCLAIMER = 'Disclaimer'
	static final String MERCHANT_NAME = ''
	static final String SELECT_LOCATION = 'Select your location'
	static final String ADD_NEW_ADDRESS = 'Add new address'
	static final String ENTER_LOCATION = 'Enter location'
	
	CommonPage commonPage = getPage(CommonPage)
	HomePage homePage = getPage(HomePage)
	
	void sslFirstScreen(int timeout = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		if(MobileDriver.verifyElementExist(commonPage.lblDynamic(FIRST_TEXT), timeout, FailureHandling.OPTIONAL)) {
			'Verify first screen'
			MobileDriver.tap(commonPage.lblDynamic(NEXT_BUTTON), timeout, flowControl)
			
			'Verify second screen'
			MobileDriver.verifyElementExist(commonPage.lblDynamic(SECOND_TEXT), timeout, flowControl)
			MobileDriver.tap(commonPage.lblDynamic(NEXT_BUTTON), timeout, flowControl)
			
			'Verify third screen and get started'
			MobileDriver.verifyElementExist(commonPage.lblDynamic(THIRD_TEXT), timeout, flowControl)
			MobileDriver.tap(commonPage.lblDynamic(GET_STARTED_BUTTON), timeout, flowControl)
			
			'Understand Disclaimer'
			MobileDriver.takeScreenshot()
			MobileDriver.verifyElementExist(commonPage.lblDynamic(DISCLAIMER), timeout, flowControl)
			MobileDriver.tap(commonPage.lblDynamic(UNDERSTAND_BUTTON), timeout, flowControl)
		}
		
	}
	
	void searchMerchant(String merchant, int timeout = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		if(MobileDriver.waitForElementPresent(commonPage.lblDynamic(SEARCH_BOX), GlobalVariable.G_timeoutShort, flowControl)) {
			MobileDriver.tap(commonPage.lblDynamic(SEARCH_BOX),timeout, flowControl)
			MobileDriver.setText(commonPage.txtEditNo(1), merchant, timeout, flowControl)
		}
	}
	
	void selectMerchant(String productType, String merchant, int timeout = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		'Select Product Type'
		def found = commonPage.scrollToTestObject(homePage.fraScrollQuickAction(), commonPage.lblDynamic(productType), ScrollDirectionEnum.RIGHT, GlobalVariable.G_timeoutMedium, flowControl)
		if(found) {
			tap(commonPage.lblDynamic(productType), GlobalVariable.G_timeoutShort, flowControl)
			MobileDriver.delay(GlobalVariable.G_timeoutShort)
		}
		'Select Merchant'
		commonPage.scrollToTestObject(commonPage.frmScrollView(), commonPage.lblDynamic(merchant), ScrollDirectionEnum.DOWN, GlobalVariable.G_timeoutMedium, flowControl)
		MobileDriver.tap(commonPage.lblDynamic(merchant), timeout, flowControl)
	}
	
	void selectDeliveryOption(String option, int timeout = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		'Choose open the dropdown'
		MobileDriver.tap(this.imgArrowDropdown(), timeout, flowControl)
		'Select Delivery Option'
		MobileDriver.tap(commonPage.lblDynamic(option), timeout, flowControl)
		MobileDriver.tap(commonPage.lblDynamic(DONE), timeout, flowControl)
	}
	
	void selectProduct(String product, int timeout = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		commonPage.scrollToTestObject(commonPage.frmScrollView(), commonPage.lblDynamic(product), ScrollDirectionEnum.DOWN, GlobalVariable.G_timeoutMedium, flowControl)
		MobileDriver.tap(commonPage.lblDynamic(product), timeout, flowControl)
	}
	
	void addToCart(int timeout = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		MobileDriver.tap(commonPage.lblContainDynamic(ADD_TO_CART), timeout, flowControl)
	}
	
	void viewCart(int timeout = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		MobileDriver.tap(commonPage.lblContainDynamic(VIEW_CART), timeout, flowControl)
	}
	
	void verifyCartOptions(List items, int timeout = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		items.each { item ->
			MobileDriver.verifyElementExist(commonPage.lblDynamic(item), timeout, flowControl)
		}
	}
	
	void enterPickUpDetails(Map info, int timeout = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		MobileDriver.tap(commonPage.lblContainDynamic(ENTER_PICK_UP), timeout, flowControl)
		commonPage.inputTextListGroup(info)
		MobileDriver.tap(commonPage.lblDynamic(DONE), timeout, flowControl)
	}
	
	void checkoutAndPay(int timeout = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		MobileDriver.tap(commonPage.lblContainDynamic(CHECKOUT), timeout, flowControl)
		Mobile.delay(GlobalVariable.G_timeoutMiniShort)
		MobileDriver.takeScreenshot()
		MobileDriver.tap(commonPage.lblContainDynamic(PAY), timeout, flowControl)
	}
	
	void PaymentConfirm(List paymentScreen, int timeout = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		paymentScreen.each { item ->
			MobileDriver.verifyElementExist(commonPage.lblDynamic(item), timeout, flowControl)
		}
		MobileDriver.takeScreenshot()
		MobileDriver.tap(commonPage.lblContainDynamic(DONE), timeout, flowControl)
	}
	
	void goToLocationScreen(int timeout = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		commonPage.verifyElementVisible(commonPage.lblDynamic(SELECT_LOCATION), timeout)
		MobileDriver.tap(commonPage.lblDynamic(SELECT_LOCATION), timeout, flowControl)
	}
	
	void tapAddNewAddress(int timeout = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		commonPage.verifyElementVisible(commonPage.lblDynamic(ADD_NEW_ADDRESS), timeout)
		MobileDriver.tap(commonPage.lblDynamic(ADD_NEW_ADDRESS), timeout, flowControl)
		Mobile.delay(3)
		MobileDriver.tap(commonPage.lblDynamic(ADD_NEW_ADDRESS), timeout, flowControl)
	}
	
	void enterLocation(String location, int timeout = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		commonPage.verifyElementVisible(commonPage.lblDynamic(ENTER_LOCATION), timeout)
		commonPage.setText(commonPage.lblDynamic(ENTER_LOCATION), location, timeout)
		Mobile.delay(3)

		commonPage.verifyElementVisible(commonPage.lblContainDynamicNo(location, 2), timeout, flowControl)
		MobileDriver.tap(commonPage.lblContainDynamicNo(location, 2), timeout, flowControl)
		
	}
	
	void fillTheLocationForm(Map<String, String> formData, FailureHandling flowControl = defaultFailureHandling) {
		commonPage.fillTheForm(formData, flowControl)
	}
	
	void tapSave(int timeout = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		MobileDriver.tap(commonPage.lblDynamic(SAVE), timeout, flowControl)
	}
	
	void tapConfirm(int timeout = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		MobileDriver.tap(commonPage.lblDynamic(CONFIRM), timeout, flowControl)
	}
	
	void verifyTabs(List<String> tabs, int timeout = GlobalVariable.G_timeoutMedium) {
		tabs.each {
			item ->
			commonPage.selectTab(commonPage.frmHorizontalScrollNo(1), item, timeout)
			MobileDriver.takeScreenshot()
		}
	}
	
	void goToAddBook(String add_name, int timeout = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		commonPage.tap(commonPage.lblDynamic(add_name),timeout, flowControl)
		commonPage.tap(commonPage.lblDynamic(ADD_BOOK),timeout, flowControl)
		Mobile.delay(3)
		commonPage.tap(commonPage.lblDynamic(ADD_BOOK),timeout, FailureHandling.OPTIONAL)
	}
}
