package com.maybank.mae.internal.pages.base.zakat

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

import com.maybank.common.MobileDriver
import com.maybank.mae.internal.pages.base.BasePage
import com.maybank.mae.internal.pages.base.CommonPage
import com.maybank.mae.internal.pages.base.HomePage
import com.maybank.mae.internal.pages.base.LoginPage
import com.maybank.mae.internal.pages.base.OtpPage
import com.maybank.mae.internal.pages.base.Secure2uAuthorisationPage
import com.maybank.mae.internal.pages.base.StatusScreenPage
import com.maybank.mae.internal.pages.base.accountpage.AccountsPage
import com.maybank.mae.internal.pages.base.accountpage.AccountDetailPage
import com.maybank.utils.StringUtils
import com.maybank.utils.TouchActionUtils
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.exception.StepFailedException

import com.maybank.mae.internal.pages.base.PushNotificationPage
import java.text.SimpleDateFormat

import io.appium.java_client.AppiumDriver as AppiumDriver
import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory

import org.openqa.selenium.By
import org.openqa.selenium.WebElement as WebElement


import internal.GlobalVariable

public abstract class ZakatPage extends BasePage {
	
	abstract TestObject lblDropdownC(String Anchor, String optionText)
	abstract TestObject lblContainsTxt(String text)
	abstract TestObject txtSiblingContains(String text)
	abstract TestObject closeBtn()
	
	CommonPage commonPage = getPage(CommonPage)
	HomePage homePage = getPage(HomePage)
	Secure2uAuthorisationPage S2U = getPage(Secure2uAuthorisationPage)
	StatusScreenPage statusPage = getPage(StatusScreenPage)
	PushNotificationPage pushNotificationPage = getPage(PushNotificationPage)
	AccountDetailPage AccountDetailpage = BasePage.getPage(AccountDetailPage)
	AccountsPage accountsPage = BasePage.getPage(AccountsPage)
	
	String ZAKATUI = "Zakat"
	String ZADUI = "Auto Debit for Zakat"
	String SETUPNOWZAD ="Set Up Auto Debit for Zakat"
	String ZakatSimANDPelaburUI = "Zakat Simpanan & Pelaburan"
	String PAYFROMUI = "Pay from"
	String ZAKATBODYUI = "Zakat body"
	String NUMBERUI = "Mobile number"
	
	String SETUPNOWbtn = "Set Up Now"
	String DONEbtn = "Done"
	String NEXTbtn = "Next"
	String SUBMITbtn = "Submit"
	String EDITbtn = "Edit"
	String RESTARTbtn = "RESTART"
	
	
/**
* Method to normalize text
* @return
*/
	String normalizeText(String text) {

		if (!text) return text
		
			return text
				// normalize smart quotes
				.replace("\u00A0", " ")
				.replace("’", "'")
				.replace("‘", "'")
				.replace("“", "\"")
				.replace("”", "\"")
		
				// normalize space and /n
				.replace("\n", " ")
				.replaceAll("\\s+", " ")
				.trim()
		//return text.replace("\n", " ").replaceAll("\\s+", " ").trim()
	}
	
	/**
 * Format mobile number for UI verification
 * @param rawMobile - raw number, e.g., "0154581234"
 * @param showFull - optional, default true. If false, mask last 4 digits
 * @return formatted string for UI, e.g., "+60 15 458 1234" or "+60 15 458 ****"
 */
	String formatMobileForUI(String rawMobile, boolean showFull = true) {

    if (rawMobile.startsWith("0")) {rawMobile = rawMobile.substring(1)}

    String country = "+60"
    String prefix  = rawMobile.substring(0, 2)    // 19
    String middle  = rawMobile.substring(2, 6)    // 1234
    String lastPart
    if (showFull) {
        lastPart = rawMobile.substring(6)         // 5678
    } else {
        lastPart = "****"
    }
    return "${country} ${prefix} ${middle} ${lastPart}"
}
	
	String formatMobileForS2U(String rawMobile) {
    String part1 = "+60 " + rawMobile.substring(0, 2)
    String part2 = rawMobile.substring(2, 5)
    String part3 = rawMobile.substring(5)

    return part1 + " " + part2 + " " + part3
}
/**
 * Scroll up until element is visible on screen (no tap)
 */
		boolean scrollUntilElementVisible(TestObject obj,int maxSwipes,int timeout = GlobalVariable.G_timeoutShort,flowControl = FailureHandling.OPTIONAL) {

			int screenWidth  = Mobile.getDeviceWidth()
			int screenHeight = Mobile.getDeviceHeight()

			int startX = screenWidth / 2
			int startY = (int)(screenHeight * 0.8)   
			int endY   = (int)(screenHeight * 0.5)   
			
			'Scroll down'
			for (int i = 0; i < maxSwipes; i++) {
				if (Mobile.waitForElementPresent(obj, 1, FailureHandling.OPTIONAL)) {
					return true
				}
				Mobile.swipe(startX, startY, startX, endY)
				Mobile.delay(1)
			}
			
			'Scroll up'
			for (int i = 0; i < maxSwipes; i++) {
				if (Mobile.waitForElementPresent(obj, 1, FailureHandling.OPTIONAL)) {
					return true
				}
				// Reverse swipe direction (swipe up)
				Mobile.swipe(startX, endY, startX, startY)
				Mobile.delay(1)
			}
			KeywordUtil.markWarning("❌ Element not visible after scrolling")
			return false
			}	
/**
* Scroll up until element is visible, then tap it (dropdown / bottom sheet)
*/
			void scrollUntilElementVisibleAndTap(TestObject obj,int maxSwipes = 6,int timeout = GlobalVariable.G_timeoutShort,FailureHandling flowControl = FailureHandling.CONTINUE_ON_FAILURE) {
			
				boolean found = scrollUntilElementVisible(obj, maxSwipes, timeout, flowControl)
			
				if (found) {
					Mobile.takeScreenshot()
					Mobile.tap(obj, timeout, flowControl)
				} else {
					KeywordUtil.markWarning("❌ Unable to find element to tap")
					
				}
			}
/**
* Dont use, used @verifyTextList
* Method to verify text with partial match
* @return
*/
	void verifyTextFlexible(String expectedText, TestObject obj, int timeout = GlobalVariable.G_timeoutShort,FailureHandling flowControl = FailureHandling.OPTIONAL) {
		
		String actualText = normalizeText(Mobile.getText(obj, timeout,FailureHandling.CONTINUE_ON_FAILURE))
		String normalizedExpected = normalizeText(expectedText)
	
		// Check if expected text is contained in actual text
		if(actualText.contains(normalizedExpected)) {
			KeywordUtil.logInfo("✅ Text verified: " + normalizedExpected)
		} else {
			KeywordUtil.markWarning("❌ Text mismatch. Expected: '" + normalizedExpected + "', Actual: '" + actualText + "'")
		}
	}
	
/**
* Method to verify text LIST
* @return
*/
	void verifyTextList(List<String> expectedTexts, int timeout = GlobalVariable.G_timeoutShort,FailureHandling flowControl = FailureHandling.OPTIONAL) {
		for(String expectedText : expectedTexts) {

			String locatorKey = getLocatorKey(expectedText)
			TestObject obj = commonPage.lblContainDynamic(locatorKey)

			boolean visible = Mobile.verifyElementVisible(obj, timeout, FailureHandling.OPTIONAL)
			
					try {
						visible = this.scrollUntilElementVisible(obj, 5, timeout, flowControl)
					} catch (Exception e) {
						KeywordUtil.markWarning("Scroll error for text: '${expectedText}' → ${e.message}")
					}
			
					if (!visible) {

						if (flowControl == FailureHandling.STOP_ON_FAILURE) {
							KeywordUtil.markFailedAndStop("Text NOT visible after scrolling: '${expectedText}'")
						} else {
							KeywordUtil.markFailed("Text NOT visible after scrolling: '${expectedText}'")
							continue  
						}
					}
			
			this.verifyTextFlexible(expectedText, obj, timeout, FailureHandling.CONTINUE_ON_FAILURE)
			}
		}
		
		void verifySingleText(String text,int timeout = GlobalVariable.G_timeoutShort,FailureHandling flowControl = FailureHandling.CONTINUE_ON_FAILURE) {
			TestObject obj = commonPage.lblContainDynamic(getLocatorKey(text))
			
			boolean visible = this.scrollUntilElementVisible(obj, 5, timeout, flowControl)

			if (!visible) {
				KeywordUtil.markFailed("Text NOT visible after scrolling: '${text}'")
			}
			
			this.verifyTextFlexible(text, obj, timeout, flowControl)
		 }
		
/**
* Verify multiple values on Submit / Confirmation Page
* You just pass a map of field -> expected value
*/
		String getLocatorKey(String text) {
			
			String full = normalizeText(text)
			
				// Try full text first
				TestObject fullObj = commonPage.lblContainDynamic(full)
			
				if (Mobile.waitForElementPresent(fullObj, 2, FailureHandling.OPTIONAL)) {
					return full
				}
			
			// --- fallback to shortened key ---
			String[] words = text.split(" ")

			if (words.size() >= 4) {
				return words[0] + " " + words[1] + " " + words[2]   // first 3 words
			} else if (words.size() == 2) {
				return words[0] + " " + words[1]                  // first 2 words
			} else {
				return words[0]                                   // single word
			}
		}
		
		void verifyDynamicText(Map<String, String> expectedMap, int timeout = GlobalVariable.G_timeoutShort,flowControl = FailureHandling.CONTINUE_ON_FAILURE) {
			expectedMap.each { key, expectedValue ->

        	try {
            	String locatorKey = getLocatorKey(expectedValue)
				TestObject obj = commonPage.lblContainDynamic(locatorKey)
            	this.scrollUntilElementVisible(obj,5, timeout, FailureHandling.OPTIONAL)

            	String actualText = normalizeText(Mobile.getText(obj, timeout, FailureHandling.CONTINUE_ON_FAILURE))
            	String expectedText = normalizeText(expectedValue)

            	if (actualText.contains(expectedText)) {
                	KeywordUtil.logInfo("✅ Verified [" + key + "]: " + expectedText)
            	} else {
                	KeywordUtil.markWarning("❌ Mismatch for [" + key + "]. Expected: " + expectedText + " | Actual: " + actualText)
                	if (flowControl == FailureHandling.STOP_ON_FAILURE) {
                    	throw new StepFailedException("Verification failed for: " + key)
                	}
            	}

        	} catch (Exception e) {
            	KeywordUtil.markWarning("❌ Exception verifying [" + key + "]: " + e.getMessage())
				if (flowControl == FailureHandling.STOP_ON_FAILURE) {
				throw e
            	}
        	}
    	}
		}
		
/**
* Verify Date & Time format: "dd MMMM yyyy, hh:mm AM/PM"
*/
				 String getDate = "Date"

				 void verifyDateTime(int timeout = GlobalVariable.G_timeoutShort,FailureHandling flowControl = FailureHandling.CONTINUE_ON_FAILURE) {

					 TestObject obj = this.txtSiblingContains(getDate)
					 this.scrollUntilElementVisible(obj, 5, timeout, FailureHandling.OPTIONAL)

					 String dateText = normalizeText(Mobile.getText(obj, timeout, FailureHandling.OPTIONAL))

					 String expectedPattern = "\\d{2} [A-Za-z]+ \\d{4}, \\d{2}:\\d{2} (AM|PM)"

					 if (dateText ==~ expectedPattern) {
						 KeywordUtil.logInfo("✅ Date & Time format is correct: " + dateText)
					 } else {
						 KeywordUtil.markWarning("❌ Date & Time format mismatch: " + dateText)
						 if (flowControl == FailureHandling.STOP_ON_FAILURE) {
							 throw new StepFailedException("Date & Time format is invalid: " + dateText)
						 }
					 }
					 Mobile.comment(dateText)
				 }
/**
* Verify Reference ID format: "MC<number>M"
* Example: 254817056M, MC254817056M
*/
				 String getREFID = "Reference"

				 void verifyRefID(int timeout = GlobalVariable.G_timeoutShort, FailureHandling flowControl = FailureHandling.CONTINUE_ON_FAILURE) {

					 TestObject obj = this.txtSiblingContains(getREFID)
					 this.scrollUntilElementVisible(obj, 5, timeout, FailureHandling.OPTIONAL)	

					 String refText = normalizeText(Mobile.getText(obj, timeout, FailureHandling.OPTIONAL))

					 String expectedPattern = "^(MC)?\\d+M\$"

					 if (refText ==~ expectedPattern) {
						 KeywordUtil.logInfo("✅ Reference ID format is correct: " + refText)
					 } else {
						 KeywordUtil.markWarning("❌ Reference ID format mismatch: " + refText)
						 if (flowControl == FailureHandling.STOP_ON_FAILURE) {
							 throw new StepFailedException("Reference ID format is invalid: " + refText)
						 }
					 }	
					 Mobile.comment(refText)
				 }
				 
 /**
* Restart the app
*/
	boolean restartApp(int timeout = GlobalVariable.G_timeoutShort,FailureHandling flowControl = FailureHandling.CONTINUE_ON_FAILURE) {
  
	  TouchActionUtils.tapTwoFingerW3CAction()
		try {
			'Not use wait ele slow script'
			MobileDriver.tap(commonPage.lblContainDynamic("RESTART"), GlobalVariable.G_timeoutLong, FailureHandling.STOP_ON_FAILURE) //not change flowControl
		}catch(ex) {
			if(MobileDriver.waitForElementNotPresent(commonPage.lblContainDynamic("DEV OPTIONS"), GlobalVariable.G_timeoutMedium, FailureHandling.OPTIONAL)) {
				'Close Popup after open devoption'
				if(MobileDriver.waitForElementPresent(commonPage.lblContainDynamic("Cancel"), GlobalVariable.G_timeoutMedium, FailureHandling.OPTIONAL)) {
					MobileDriver.tap(commonPage.lblContainDynamic("Cancel"), GlobalVariable.G_timeoutMedium, FailureHandling.OPTIONAL)
				}else {
					MobileDriver.tapAndLog(commonPage.imgNoDynamic(1), GlobalVariable.G_timeoutMedium, 'Icon Back' ,FailureHandling.OPTIONAL)
				}
				TouchActionUtils.tapTwoFingerW3CAction()
			}
			if(MobileDriver.waitForElementPresent(commonPage.lblContainDynamic("DEV OPTIONS"), GlobalVariable.G_timeoutLong, FailureHandling.OPTIONAL)) {
				MobileDriver.tap(commonPage.lblContainDynamic("RESTART"), GlobalVariable.G_timeoutLong, FailureHandling.STOP_ON_FAILURE)
				return true
			}
			return false
		}
		return true
		Mobile.delay(5)
	  commonPage.byPassOS()
	  Mobile.delay(5)
  }

/**
* Enter dashboard based on entry type
*/
		void enterDashboard(String entry, Boolean RegisterTC, boolean Verify = true, timeout = GlobalVariable.G_timeoutShort, flowControl = FailureHandling.STOP_ON_FAILURE) {
			if (entry == "ZakatDB") {
				WebUI.callTestCase(findTestCase('Mobile/MAE/Zakat Auto/Reuseable/Entry-Zakat Dashboard'),[('ZakatBox') : "Zakat Auto Debit", ('Verify') : Verify],FailureHandling.STOP_ON_FAILURE)
				} 
				else if (entry == "Setting") {
					WebUI.callTestCase(findTestCase('Mobile/MAE/Zakat Auto/Zakat Auto Debit/Common/ZAD-Setting_Entry'),[('RegisterTC'): RegisterTC],FailureHandling.STOP_ON_FAILURE)
					 }
			}

/****************************************************************** ZAKAT AUTO DEBIT (START) *************************************************************************/
	
/**
* Method to tap Zakat from View All
* @return
*/
		void tapZakatInViewAll( FailureHandling flowControl = defaultFailureHandling) {
			Mobile.waitForElementPresent(homePage.btnViewAll(), GlobalVariable.G_timeoutShort, FailureHandling.OPTIONAL)
			Mobile.tap(homePage.btnViewAll(), GlobalVariable.G_timeoutShort, flowControl)
			Mobile.tap(commonPage.lblDynamic(ZAKATUI),GlobalVariable.G_timeoutShort,FailureHandling.STOP_ON_FAILURE)
		}
	
/**
* Enter 6 digit PIN
* @return
*/
		void digitPin(timeout = GlobalVariable.G_timeoutShort,flowControl = FailureHandling.CONTINUE_ON_FAILURE) {
			if(Mobile.waitForElementPresent(commonPage.lblDynamic("6-digit PIN"), timeout, FailureHandling.OPTIONAL)) {
				commonPage.checkEnterPIN()
				}
			}
		
/**
* verify & tap on Next on Pop Up
* @return
*/
		static String ZakatPopOut = "Track all your zakat payments in one place"
		List <String> ZAKATPOPUPPAGE1 = [
			"Track all your zakat payments in one place",
			"Fulfil your zakat obligations effortlessly with a tracker of all your contributions."
			]

		List <String> ZAKATPOPUPPAGE2 = [
			"Conveniently calculate and pay your zakat",
			"Know precisely how much zakat you need to pay with our zakat calculator."
			]
		List <String> ZAKATPOPUPPAGE3 = [
			"Complete zakat obligations with the right niat and doa",
			"Simulate paying zakat to an amil and read out your niat while making payment, then play the doa after paying zakat."
			]
		void CheckZakatPopOut(boolean Verify = true, timeout = GlobalVariable.G_timeoutShort, flowControl = FailureHandling.CONTINUE_ON_FAILURE) {
			if(Mobile.waitForElementPresent(commonPage.lblDynamic(ZakatPopOut), timeout, FailureHandling.OPTIONAL)) {
				
				'Verify Pop Up Page 1'
				if (Verify) { this.verifyTextList(ZAKATPOPUPPAGE1,timeout,flowControl) }
				'Tap Next'
				Mobile.tap(commonPage.lblDynamic(NEXTbtn),timeout,FailureHandling.STOP_ON_FAILURE)
				'Verify Pop Up Page 2'
				if (Verify) { this.verifyTextList(ZAKATPOPUPPAGE2,timeout,flowControl) }
				'Tap Next'
				Mobile.tap(commonPage.lblDynamic(NEXTbtn),timeout,FailureHandling.STOP_ON_FAILURE)
				'Verify Pop Up Page 3'
				if (Verify) { this.verifyTextList(ZAKATPOPUPPAGE3,timeout,flowControl) }
				'Tap Next'
				Mobile.tap(commonPage.lblDynamic(DONEbtn),timeout,FailureHandling.STOP_ON_FAILURE)
			}
			Mobile.takeScreenshot()
		}
		
/**
* Verify Zakat Dashbaord
* @return
*/
		List <String> ZAKATDASHBOARD = [
			"Zakat Payments",
			"Year: 2026",
			"Paid Zakat: 0",
			"Zakat paid this year*",
			"Calculate & Pay Zakat",
			"Pay Zakat Fitrah",
			"Auto Debit for Zakat",
			"Recent Payments*",
			"No Zakat Payments Yet",
			"Zakat is not just an obligation but an opportunity to make a meaningful difference. Start your Zakat journey today!"
		]
		void VerifyZakatDashboard(boolean Verify = true, timeout = GlobalVariable.G_timeoutShort, flowControl = FailureHandling.CONTINUE_ON_FAILURE) {
			Mobile.waitForElementPresent(commonPage.lblDynamic("Zakat"), timeout, FailureHandling.OPTIONAL)
			if (Verify) { this.verifyTextList(ZAKATDASHBOARD,timeout,flowControl) }
		}
		
/**
* verify & tap on ZAD from Zakat Dashboard
* @return
*/
		void tapZADTile(timeout = GlobalVariable.G_timeoutShort,flowControl = FailureHandling.CONTINUE_ON_FAILURE) {
			
			Mobile.tap(commonPage.lblContainDynamic("Auto Debit"), timeout, FailureHandling.STOP_ON_FAILURE)
			
		}
		
/**
* Reset Zakat Auto Debit state before registration
* @return true  -> user WAS registered (and we cancelled)
*         false -> user was NOT registered (fresh)
*/
		boolean resetZakatRegistrationIfNeeded(int timeout = GlobalVariable.G_timeoutShort) {
			Mobile.waitForElementPresent(commonPage.lblContainDynamic("Enjoy"), timeout, FailureHandling.OPTIONAL)
			Mobile.takeScreenshot()
			
			// If Set Up Now is already visible, nothing to do
			if (Mobile.waitForElementPresent(commonPage.lblDynamic(SETUPNOWbtn), timeout, FailureHandling.OPTIONAL)) {
				return false
			}
		
			// If Edit exists, user is already registered → cancel it
			if (Mobile.waitForElementPresent(commonPage.lblDynamic("ACTIVE"), timeout, FailureHandling.OPTIONAL)) {
		
				Mobile.tap(commonPage.lblDynamic("Cancel Auto Debit"), timeout,FailureHandling.OPTIONAL)
				Mobile.tap(commonPage.lblDynamic("Confirm"), timeout,FailureHandling.OPTIONAL)

				Mobile.waitForElementPresent(commonPage.lblDynamic("Secure2u authorisation"), timeout, FailureHandling.OPTIONAL)
				Mobile.tap(commonPage.lblDynamic("Approve"), timeout,FailureHandling.OPTIONAL)
				
				this.tapSetUp()

				return true
			}

			return false
		}
		
/**
* Tap on Set Up button in ZAD
* @return
*/
		void tapSetUp(timeout = GlobalVariable.G_timeoutShort,flowControl = FailureHandling.CONTINUE_ON_FAILURE) {
			Mobile.waitForElementPresent(commonPage.lblDynamic(SETUPNOWZAD), GlobalVariable.G_timeoutShort, FailureHandling.OPTIONAL)
			Mobile.takeScreenshot()
			Mobile.tap(commonPage.lblDynamic(SETUPNOWbtn), GlobalVariable.G_timeoutShort, FailureHandling.OPTIONAL)
			
		}
		
/**
* Select a value from a dropdown that appears at the bottom of the mobile screen.
* Scrolls from bottom to top until the value is visible and clicks it.
*
* @param findValue The exact text value to select
* @param timeout Timeout for element operations
* @param flowControl Failure handling mode
*/
		public void selectDropdownScrollandTap(String findValue, int timeout = GlobalVariable.G_timeoutShort, FailureHandling flowControl = defaultFailureHandling) {
			int screenWidth = Mobile.getDeviceWidth()
			int screenHeight = Mobile.getDeviceHeight()

			int startX = screenWidth / 2
			int startY = (int) (screenHeight * 0.8)
			int endY = (int) (screenHeight * 0.5) 
			
			int scrollAttempts = 0
			int maxScrollAttempts = 15
			def prevPageSource = MobileDriver.getPageSource()
			
			while (scrollAttempts < maxScrollAttempts) {

				TestObject valueObj = commonPage.lblContainDynamic(findValue)
				if (Mobile.waitForElementPresent(valueObj, 1, FailureHandling.OPTIONAL)) {
					Mobile.takeScreenshot()
					Mobile.tap(valueObj, timeout, flowControl)
					KeywordUtil.logInfo("✅ Found and tapped dropdown value: " + findValue)
					return
				}
				
				Mobile.swipe(startX, startY, startX, endY)
				Mobile.delay(GlobalVariable.G_timeoutMiniShort)
				
				// check if page changed; increment scrollAttempts if not
				def currentPageSource = MobileDriver.getPageSource()
				if (prevPageSource.equals(currentPageSource)) {
					scrollAttempts++
				}
				prevPageSource = currentPageSource
			}
			
			// handle error if value not found
			handleError("Dropdown value not found: " + findValue, flowControl)
			Mobile.tap(commonPage.lblDynamic("Done"), GlobalVariable.G_timeoutLong, flowControl)
		}
/**
* Verify and set up Register
* @return
*/
	String SetUpPage1 = "Please select your debiting bank account and preferred zakat body"	
	List<String> SETUPPAGE = [
		"Auto Debit for Zakat",
		"Zakat Simpanan & Pelaburan",
		"Please select your debiting bank account and preferred zakat body",
		"Pay from",
		"Zakat body",
		"Mobile no."
		]
		
		void setUpZakatPage(String ZakatBody1, String Payfrom1, String MobileNo1, boolean Verify = true, timeout = GlobalVariable.G_timeoutShort, flowControl = FailureHandling.CONTINUE_ON_FAILURE) {
			
			Mobile.waitForElementPresent(commonPage.lblContainDynamic("Zakat"), timeout, FailureHandling.OPTIONAL)
			if (Verify) { this.verifyTextList(SETUPPAGE,timeout,flowControl) }
			
				if (Verify) {
					'Verify Field Validation'
					Mobile.tap(commonPage.lblSiblingGroup("Mobile no."),timeout,FailureHandling.OPTIONAL)
					commonPage.tapTickBtn()
					this.verifySingleText("Please enter a valid mobile number in order to continue.",timeout,flowControl)
					Mobile.takeScreenshot()
				}
			Mobile.waitForElementPresent(commonPage.lblDynamic(ZAKATBODYUI), GlobalVariable.G_timeoutShort, FailureHandling.OPTIONAL)
			Mobile.tap(commonPage.lblSiblingOneGroup(ZAKATBODYUI), GlobalVariable.G_timeoutLong,FailureHandling.STOP_ON_FAILURE)	
			this.selectDropdownScrollOneLine(ZakatBody1,GlobalVariable.G_timeoutLong, FailureHandling.OPTIONAL)
			Mobile.tap(commonPage.lblDynamic(DONEbtn), GlobalVariable.G_timeoutLong, flowControl)
			Mobile.takeScreenshot()
			Mobile.delay(5)
			
			Mobile.tap(this.lblDropdownC(PAYFROMUI,"Please select"), timeout,FailureHandling.OPTIONAL)
			Mobile.tap(commonPage.lblContainDynamic(Payfrom1), timeout, flowControl)
			Mobile.tap(commonPage.lblDynamic(DONEbtn), timeout, flowControl)
			Mobile.delay(5)
			
			'Mobile No'
			Mobile.tap(commonPage.lblSiblingGroup("Mobile no."),timeout)
			for (def i = 0; i < 10; i++) {
				
				def tapNo = MobileNo1[i]
				MobileDriver.tap(commonPage.lblDynamic(tapNo), GlobalVariable.G_timeoutLong, flowControl)
			}
			commonPage.tapTickBtn()
			
			Mobile.takeScreenshot()
			Mobile.tap(commonPage.lblDynamic(NEXTbtn), GlobalVariable.G_timeoutShort, FailureHandling.OPTIONAL)
			Mobile.delay(5)
			
		}
	
/**
* Verify Declaration Page 
* @return
*/
		List<String> DECLARATIONPAGE1 = [
			"I have read and agree to be bound by the Terms & Conditions and Product Disclosure Sheet",
			"I hereby consent Maybank Islamic to calculate and automatically debit my account for my Zakat Simpanan & Pelaburan, based on the total savings available in my deposit and/or investment account with Maybank Islamic. The auto debit will start from this year and subsequent years to my preferred zakat body under the terms & conditions applied until I cancel this service via Maybank2u or by giving notice to Maybank Islamic.",
			"I hereby agree to accept this product/service and that the use of electronic messages and electronic acceptance for all matters related to this product/service shall be binding in accordance with the Electronic Commerce Act 2006.",
			"I have read, agree and accept the terms of the Maybank Group Privacy Notice. For marketing or products and services by Maybank Group/other entities referred to in the Privacy Notice.",
			"Yes, I expressly agree to be contacted",
			"No, I do not agree to be contacted",
			"Agree & Confirm"
			]

		String AGREE1 = "Yes, I expressly agree to be contacted"
		String NOTAGREE1 = "No, I do not agree to be contacted"
		String AGREEbtn = "Agree & Confirm"
		
		void declarationPage(boolean Verify = true, timeout = GlobalVariable.G_timeoutShort, flowControl = FailureHandling.CONTINUE_ON_FAILURE) {
			Mobile.waitForElementPresent(commonPage.lblContainDynamic("Declaration"), GlobalVariable.G_timeoutShort, FailureHandling.OPTIONAL)
			Mobile.takeScreenshot()
			
			if (Verify) { this.verifyTextList(DECLARATIONPAGE1,timeout,flowControl) }
			
			commonPage.scrollToEnd()
			
			Mobile.tap(commonPage.grpContainsPrecedingSibling(AGREE1),GlobalVariable.G_timeoutShort, FailureHandling.OPTIONAL)
			
			Mobile.takeScreenshot()
			Mobile.tap(commonPage.lblDynamic(AGREEbtn), timeout, FailureHandling.OPTIONAL)
		}
		
/**
* Verify Submit Page in Registration Flow
* @return
*/
		List<String> SUBMITPAGE_LABELS = [
			"Confirmation",
			"Please confirm the details below",
			"Personal Details",
			"Edit",
			"Zakat type",
			"Zakat Simpanan & Pelaburan",
			PAYFROMUI,
			ZAKATBODYUI,
			NUMBERUI
			]
			
		void verifyConfirmationPage(String ZakatBody1,String Payfrom1 ,String MobileNo1, int timeout = GlobalVariable.G_timeoutShort, FailureHandling flowControl = FailureHandling.CONTINUE_ON_FAILURE) {
			
			Mobile.waitForElementPresent(commonPage.lblContainDynamic("Confirmation"), timeout, flowControl)
			Mobile.takeScreenshot()
				
			'Verify Static Text'
			this.verifyTextList(SUBMITPAGE_LABELS,timeout,flowControl)
			
			'Verify Dynamic text'
			String formattedMobile = this.formatMobileForS2U(MobileNo1)
			
			this.verifyDynamicText([
			   "Zakat Body" : ZakatBody1,
			   "Pay From"   : Payfrom1,
			   "Mobile No"  : formattedMobile
			],timeout,flowControl)

		}
		
/**
* Tap Submit button
* @return
*/
		void tapSubmit(int timeout = GlobalVariable.G_timeoutShort, FailureHandling flowControl = FailureHandling.CONTINUE_ON_FAILURE) {
			'Tap Submit'
			Mobile.waitForElementPresent(commonPage.lblDynamic("Confirmation"), timeout, FailureHandling.OPTIONAL)
			Mobile.takeScreenshot()
			Mobile.tap(commonPage.lblDynamic(SUBMITbtn), timeout, FailureHandling.STOP_ON_FAILURE)
			}

/**
* Tap Submit button
* @return
*/
		 void tapEdit(int timeout = GlobalVariable.G_timeoutShort, FailureHandling flowControl = FailureHandling.CONTINUE_ON_FAILURE) {
			   'Tap Edit'
				Mobile.waitForElementPresent(commonPage.lblDynamic("Confirmation"), timeout, FailureHandling.OPTIONAL)
				Mobile.takeScreenshot()
				Mobile.tap(commonPage.lblDynamic("Edit"), timeout, FailureHandling.STOP_ON_FAILURE)
				}
		
/**
* Verify S2U Page in Registration Flow
* @return
*/
		String APPROVEbtn ="Approve"
		String REJECTbtn = "Reject"

		void registerS2UPage(String status,String ZakatBody1,String Payfrom1 ,String MobileNo1 , timeout = GlobalVariable.G_timeoutMiniShort,flowControl = FailureHandling.CONTINUE_ON_FAILURE) {
			
			Mobile.waitForElementPresent(commonPage.lblContainDynamic("Secure2u authorisation"), GlobalVariable.G_timeoutMedium, flowControl)
			"Verify Static Text"
			Mobile.verifyElementExist(commonPage.lblContainDynamic("Set Up Auto Debit For Zakat"), timeout, FailureHandling.CONTINUE_ON_FAILURE)
			Mobile.takeScreenshot()
			
			'Verify Dynamic text'			
			this.verifyDynamicText([
			   "Zakat Body" : ZakatBody1,
			],timeout,flowControl)
			
			switch(status) {
				case 'Approve':
					'Select Approve Button'
					MobileDriver.tap(commonPage.lblDynamic(APPROVEbtn), timeout)
					break
				case 'Reject':
					'Select Reject Button'
					MobileDriver.tap(commonPage.lblDynamic(REJECTbtn), timeout)
					break
				case 'Expired':
					'Wait Done Button Present'
					MobileDriver.waitForElementPresent(commonPage.lblDynamic(DONEbtn),60, flowControl)
					break
			}
		}
		

/**
* Verify Acknowledgement Page in Registration Flow
* @return
*/
		List<String> REGISTER_APPROVE_LABELS = [
			"Set up successful!",
			"A reminder will be sent to your mobile when the zakat amount is calculated and auto debited from your account.",
			"Your zakat statement will be ready for you to view/download on the Maybank2u website.",
			"You may cancel or edit this auto debit anytime through your Maybank2u Settings."
			]
		List<String> REGISTER_REJECT_LABELS = [
			"Set up unsuccessful",
			"Your Secure Verification authorisation was rejected."
			]
		List<String> REGISTER_EXPIRED_LABELS = [
			"Set up unsuccessful",
			"Your Secure Verification authorisation has expired. Please perform the transaction again."
			]
		void registerstatusPage (String status,String ZakatBody1,String Payfrom1 ,String MobileNo1 , timeout = GlobalVariable.G_timeoutShort,flowControl = FailureHandling.CONTINUE_ON_FAILURE) {
			
			Mobile.waitForElementPresent(commonPage.lblContainDynamic("Set up"), timeout, flowControl)
			Mobile.takeScreenshot()
			switch (status) {
				case "Approve":

				'Verify Static text'
				this.verifyTextList(REGISTER_APPROVE_LABELS,timeout,flowControl)
				
				commonPage.scrollToEnd()
				'Verify Dynamic text'
				String formattedMobile = this.formatMobileForS2U(MobileNo1)
				Mobile.comment(formattedMobile)
				this.verifyDynamicText([
				   "Zakat Body" : ZakatBody1,
				   "Pay From"   : Payfrom1,
				   "Mobile number"  : formattedMobile
				],timeout,FailureHandling.OPTIONAL)
					
				Mobile.takeScreenshot()
				'Verify Ref ID'
				this.verifyRefID()
					
				'Verify Date Time'
				this.verifyDateTime()
					
					break
			
				case "Reject":

				'Verify Static text'
				this.verifyTextList(REGISTER_REJECT_LABELS,timeout,flowControl)
				
				'Verify Ref ID'
				this.verifyRefID()
					
				'Verify Date Time'
				this.verifyDateTime()
				
					break
			
				case "Expired":
				
				'Verify Static text'
				this.verifyTextList(REGISTER_EXPIRED_LABELS,timeout,flowControl)

				'Verify Ref ID'
				this.verifyRefID()
					
				'Verify Date Time'
				this.verifyDateTime()
					break
			}
		}
		
/**
* Verify Zakat registration status on page
* Supports dynamic fields and status-based verification
*/
		void verifyRegistered(String status,String ZakatBody1, String Payfrom1, String MobileNo1, int timeout = GlobalVariable.G_timeoutShort, FailureHandling flowControl = FailureHandling.CONTINUE_ON_FAILURE) {
			
			String formattedMobile = formatMobileForUI(MobileNo1, false)
			
			switch (status) {
			
				case "Approve":
						List<String> REGISTER_APPROVE_ELEMENTS = [
							ZADUI,
							ZakatSimANDPelaburUI,
							"Amount",
							"To be calculated on every 31st Oct",
							"Auto Debit",
							"ACTIVE",
							"Switch account",
							"Switch body",
							"Edit",
							"Cancel Auto Debit"
						]

						this.verifyTextList(REGISTER_APPROVE_ELEMENTS, timeout, flowControl)

						verifyDynamicText([
							"Pay From"  : Payfrom1,
							"Zakat Body": ZakatBody1,
							"Mobile No" : formattedMobile
						], timeout, flowControl)
			
						Mobile.takeScreenshot()
						break
			
					case "Reject":
					case "Expired":

						Mobile.verifyElementVisible(commonPage.lblDynamic(SETUPNOWbtn), timeout, flowControl)
						Mobile.takeScreenshot()
						break

				}
			}
		
	
		/********************************************************* ZAKAT AUTO DEBIT REGISTER (END) *************************************************************************/
		
		
		/********************************************************* SETTINGS (START) *************************************************************************/
/**
* Select dropdown 
* 
*/
			public void selectDropdownScrollOneLine(String findValue, int timeout = GlobalVariable.G_timeoutShort, FailureHandling flowControl = FailureHandling.CONTINUE_ON_FAILURE) {

				String locatorKey = getLocatorKey(findValue)
				TestObject valueObj = commonPage.lblContainDynamic(locatorKey)

				int screenWidth = Mobile.getDeviceWidth()
				int screenHeight = Mobile.getDeviceHeight()
				int startX = screenWidth / 2
				int startY = (int)(screenHeight * 0.8)
				int endY = (int)(screenHeight * 0.5)

				boolean isVisible = false
				int maxSwipes = 10
				int swipes = 0

				while (swipes < maxSwipes) {
					if (Mobile.waitForElementPresent(valueObj, 1, FailureHandling.OPTIONAL)) {
						isVisible = true
						break
					}
					Mobile.swipe(startX, startY, startX, endY)
					Mobile.delay(1)
					swipes++
				}

				if (!isVisible) {
					handleError("Dropdown value not found after scrolling: " + findValue, flowControl)
					return
				}

				String actualText = normalizeText(Mobile.getText(valueObj, timeout,FailureHandling.CONTINUE_ON_FAILURE))
				String expectedText = normalizeText(findValue)

				if (actualText.contains(expectedText)) {
					Mobile.takeScreenshot()
					Mobile.tap(valueObj, timeout, flowControl)
					KeywordUtil.logInfo("✅ Found and tapped dropdown value: " + expectedText)
				} else {
					KeywordUtil.markWarning("❌ Text mismatch in dropdown. Expected: " + expectedText + ", Actual: " + actualText)
					if (flowControl == FailureHandling.STOP_ON_FAILURE) {
						throw new StepFailedException("Dropdown selection failed for: " + findValue)
					}
				}
			}
	
/**
* Tap "Switch body" button and change Zakat Body flow
*
*/
		String PREFERREDBODY = "Select your preferred zakat body"
		String SWITCHBODYbtn = "Switch body"
		
		void switchBody(String ZakatBody2, boolean Verify = true, timeout = GlobalVariable.G_timeoutShort, flowControl = FailureHandling.CONTINUE_ON_FAILURE) {
			
			Mobile.waitForElementPresent(commonPage.lblContainDynamic("ACTIVE"), timeout, FailureHandling.OPTIONAL)
			Mobile.takeScreenshot()
			Mobile.tap(commonPage.lblContainDynamic(SWITCHBODYbtn), timeout, FailureHandling.STOP_ON_FAILURE)
			
			Mobile.waitForElementPresent(commonPage.lblDynamic(PREFERREDBODY), timeout, flowControl)
			
			List<String> SWITCHBODY_PAGE = [
				PREFERREDBODY,
				"Zakat body",
				"Continue"
				]
			if (Verify) { this.verifyTextList(SWITCHBODY_PAGE,timeout,FailureHandling.CONTINUE_ON_FAILURE) }
			'Before Edit'
			Mobile.takeScreenshot()
			Mobile.tap(commonPage.lblSiblingOneGroup(ZAKATBODYUI), GlobalVariable.G_timeoutLong,FailureHandling.STOP_ON_FAILURE)	
			this.selectDropdownScrollOneLine(ZakatBody2,GlobalVariable.G_timeoutLong, FailureHandling.OPTIONAL)
			Mobile.tap(commonPage.lblDynamic(DONEbtn), GlobalVariable.G_timeoutLong, flowControl)
			'After Edit'
			Mobile.takeScreenshot()
			
		}
		
/**
* Tap "Switch account" button and switch Account flow
*
*/
		String SWITCHACCbtn = "Switch account"
		String PREFERREDACC = "Select your preferred Maybank account for auto debiting"

		void switchAccount(String payFrom2, boolean Verify = true, timeout = GlobalVariable.G_timeoutShort, flowControl = FailureHandling.CONTINUE_ON_FAILURE) {
			
			Mobile.waitForElementPresent(commonPage.lblContainDynamic("ACTIVE"), timeout, FailureHandling.OPTIONAL)
			Mobile.takeScreenshot()
			Mobile.tap(commonPage.lblContainDynamic(SWITCHACCbtn), timeout, FailureHandling.STOP_ON_FAILURE)
			
			Mobile.waitForElementPresent(commonPage.lblDynamic(PREFERREDACC), timeout, flowControl)
			
			List<String> SWITCHACC_PAGE = [
				PREFERREDACC,
				"Continue"
				]
			if (Verify) { this.verifyTextList(SWITCHACC_PAGE,timeout,FailureHandling.CONTINUE_ON_FAILURE) }
			
			'Before Edit'
			Mobile.takeScreenshot()
			Mobile.tap(this.lblContainsTxt(payFrom2), timeout, flowControl)
			'After Edit'
			Mobile.takeScreenshot()
			
		}
		
/**
* Tap "Edit" button and change Mobile number flow
*
*/
		String PREFERREDNO = "Receive notifications when your zakat is calculated and auto debited."
		String UPDATEbtn = "Update"
		
		void editNo(String MobileNo2, boolean Verify = true, timeout = GlobalVariable.G_timeoutShort, flowControl = FailureHandling.CONTINUE_ON_FAILURE) {
			
			Mobile.waitForElementPresent(commonPage.lblContainDynamic("ACTIVE"), timeout, FailureHandling.OPTIONAL)
			Mobile.takeScreenshot()
			Mobile.tap(commonPage.lblContainDynamic(EDITbtn), timeout, FailureHandling.STOP_ON_FAILURE)
			
				Mobile.waitForElementPresent(commonPage.lblDynamic(PREFERREDACC), timeout, flowControl)
			
			List<String> EDITNO_PAGE = [
				PREFERREDNO,
				"Update"
				]
			if (Verify) { this.verifyTextList(EDITNO_PAGE,timeout,FailureHandling.CONTINUE_ON_FAILURE) }
			
			'Before Edit'
			Mobile.takeScreenshot()
			
			if (Verify) {
				'Verify Field Validation'
				Mobile.tap(commonPage.lblSiblingGroup("Mobile number"),timeout,FailureHandling.STOP_ON_FAILURE)
				if(Mobile.waitForElementPresent(commonPage.lblContainDynamic(DONEbtn), timeout, flowControl)) {
					Mobile.tap(commonPage.lblContainDynamic(DONEbtn), timeout, flowControl)
				}
				commonPage.tapTickBtn()
				this.verifySingleText("Please enter a valid mobile number in order to continue.",timeout,flowControl)
				Mobile.takeScreenshot()
			}
			
			Mobile.tap(commonPage.lblSiblingGroup("Mobile number"),timeout)
			for (def i = 0; i < 10; i++) {
				def tapNo = MobileNo2[i]
				MobileDriver.tap(commonPage.lblDynamic(tapNo), GlobalVariable.G_timeoutLong, flowControl)
			}
			commonPage.tapTickBtn()
			'After Edit'
			Mobile.takeScreenshot()
			
			Mobile.tap(commonPage.lblDynamic(UPDATEbtn), timeout, flowControl)

		}
		
/**
* Tap "Cancel Auto Debit" button and cancel ZAD flow
*
*/
		String CANCELZADbtn = "Cancel Auto Debit"
		String CONFIRMbtn = "Confirm"
		List<String> CANCELPOPUP = [
			"Are you sure you want to cancel this auto debit?",
			"Cancel Auto Debit"
			]
		
		void cancelPopUp(timeout = GlobalVariable.G_timeoutShort,flowControl = FailureHandling.CONTINUE_ON_FAILURE) {
			
			Mobile.waitForElementPresent(commonPage.lblContainDynamic(CANCELZADbtn), timeout, FailureHandling.OPTIONAL)
			Mobile.tap(commonPage.lblContainDynamic(CANCELZADbtn), timeout, FailureHandling.STOP_ON_FAILURE)
			Mobile.takeScreenshot()
				
			this.verifyTextList(CANCELPOPUP,timeout,flowControl)
			
			Mobile.tap(commonPage.lblDynamic(CONFIRMbtn), timeout, flowControl)			
		}
			
/**
* Verify S2U Page for all Edit flow. Ex: Switch Zakat Body, Switch Account, Edit Mobile number and Cancel ZAD
* Tap S2U based on Status
*/
		void settingS2U(String status ,String EditType, String changeData,timeout = GlobalVariable.G_timeoutShort,flowControl = FailureHandling.CONTINUE_ON_FAILURE) {
			
			Mobile.waitForElementPresent(commonPage.lblDynamic("Secure2u authorisation"), timeout, flowControl)
			Mobile.takeScreenshot()
		
			switch (EditType) {
				case "Body":
					
					List<String> ZAKATBODYS2UPAGE_LABELS = [
						"Secure2u authorisation",
						"Auto Debit For Zakat - Switch Zakat Body",
						"Switch to",
						changeData
						]
					"Verify Static Text"
					this.verifyTextList(ZAKATBODYS2UPAGE_LABELS,timeout,flowControl)
					
					commonPage.scrollToEnd()
					'Verify Date Time'
					this.verifyDateTime()
					
					break
			
				case "Account":
					List<String> ACCOUNTS2UPAGE_LABELS = [
						"Secure2u authorisation",
						"Auto Debit For Zakat - Switch Account",
						"Switch to",
						changeData
					]
					"Verify Static Text"
					this.verifyTextList(ACCOUNTS2UPAGE_LABELS,timeout,flowControl)
										
					'Verify Date Time'
					this.verifyDateTime()
					break
			
				case "Number":
					List<String> ACCOUNTS2UPAGE_LABELS = [
						"Secure2u authorisation",
						"Auto Debit For Zakat- Change Mobile Number",
						"Switch to"
					]
					
					'Verify Dynamic text'
					String formattedMobile = this.formatMobileForS2U(changeData)
					this.verifyDynamicText(["Mobile No"  : formattedMobile],timeout,flowControl)
					
					'Verify Date Time'
					this.verifyDateTime()
					break
				
				case "Cancel":
					
					List<String> CANCELS2UPAGE_LABELS = [
					"Secure2u authorisation",
					"Cancel Auto Debit for Zakat",
					"Pay from",
					"Type of Zakat",
					"Zakat body"
					]
					this.verifyTextList(CANCELS2UPAGE_LABELS,timeout,flowControl)
					
					commonPage.scrollToEnd()
					Mobile.takeScreenshot()
					
					break
			}
			
			switch(status) {
				case 'Expired':
					'Wait Done Button Present'
					MobileDriver.waitForElementPresent(commonPage.lblDynamic(DONEbtn), 60, flowControl)
					break
				case 'Reject':
					'Select Reject Button'
					MobileDriver.tap(commonPage.lblDynamic(REJECTbtn), timeout)
					break
				default:
					'Select Approve Button'
					MobileDriver.tap(commonPage.lblDynamic(APPROVEbtn), timeout)
					break
			}
		}
		
		
/**
* Verify Acknowledgement Page in Each Edit Flow. Ex: Switch Zakat Body, Switch Account, Edit Mobile number and Cancel ZAD
* 
*/
		void settingStatusPage (String editType,String changeData, String status, timeout = GlobalVariable.G_timeoutShort,flowControl = FailureHandling.CONTINUE_ON_FAILURE) {
			Mobile.delay(5)
			Mobile.takeScreenshot()
			
			switch (status) {
				case "Approve":
					this.verifyApproveStatus(editType, changeData, timeout, flowControl)
					return
			
				case "Reject":
					this.verifyRejectStatus(editType,timeout, flowControl)
					return
			
				case "Expired":
					this.verifyExpiredStatus(editType, timeout, flowControl)
					return
			}
		}
		
	private void verifyApproveStatus(String editType,String changeData,int timeout,FailureHandling flowControl) {
		
		switch (editType) {
			case "Body":
			
				List<String> STATUSBODY_APPROVELABELS = [
					"Zakat body switch successful",
					"Transaction Type",
					"Date and Time",
					"Switch to",
					"Auto Debit for Zakat - Switch Zakat Body",
					changeData
					]
				this.verifyTextList(STATUSBODY_APPROVELABELS,timeout,FailureHandling.CONTINUE_ON_FAILURE)
				
				verifyDateTime()

				return
		
			case "Number":
				
				List<String> STATUSNUMBER_APPROVELABELS = [
					"Mobile number edit successful",
					"Switch to",
					"Transaction Type",	
					"Date and Time",
					"Auto Debit for Zakat - Edit Mobile Number"
					]
					this.verifyTextList(STATUSNUMBER_APPROVELABELS,timeout,FailureHandling.CONTINUE_ON_FAILURE)
			
					'Verify Dynamic text'
					String formattedMobile = this.formatMobileForS2U(changeData)
					this.verifyDynamicText("Mobile No"  : formattedMobile,timeout,flowControl)
					verifyDateTime()
				return
		
			case "Account":
				
				List<String> STATUSACCOUNT_APPROVELABELS = [
					"Account switch successful",
					"Switch to",
					"Transaction Type",
					"Date and Time",
					changeData,
					"Auto Debit for Zakat - Switch Account",
					]
					this.verifyTextList(STATUSACCOUNT_APPROVELABELS,timeout,FailureHandling.CONTINUE_ON_FAILURE)
			
					verifyDateTime()
				return
			case "Cancel":
				Mobile.verifyElementVisible(commonPage.lblDynamic(SETUPNOWbtn),timeout,flowControl)
				return
		}
	}
	
	private void verifyRejectStatus(String editType,int timeout,FailureHandling flowControl) {
		List<String> GENERAL_REJECTLABELS = [
			"Set up unsuccessful",
			"Your Secure Verification authorisation was rejected."
			]
			this.verifyTextList(GENERAL_REJECTLABELS,timeout,FailureHandling.CONTINUE_ON_FAILURE)
			
			verifyDateTime()
			verifyRefID()
		
		switch (editType) {
			case "Body":
			
				List<String> STATUSZAKATBODY_REJECTLABELS = [
					"Date and Time",
					"Auto Debit for Zakat Switch Body"
					]
					this.verifyTextList(STATUSZAKATBODY_REJECTLABELS,timeout,FailureHandling.CONTINUE_ON_FAILURE)

				return
		
			case "Number":
				
				List<String> STATUSNUMBER_REJECTLABELS = [
					"Date and Time",
					"Auto Debit for Zakat Edit Mobile No"
					]
					this.verifyTextList(STATUSNUMBER_REJECTLABELS,timeout,FailureHandling.CONTINUE_ON_FAILURE)
	
				return
		
			case "Account":
				
				List<String> STATUSACCOUNT_REJECTLABELS = [
					"Date and Time",
					"Auto Debit for Zakat Switch Account",
					]
					this.verifyTextList(STATUSACCOUNT_REJECTLABELS,timeout,FailureHandling.CONTINUE_ON_FAILURE)

				return
			case "Cancel":
				List<String> STATUSCANCEL_REJECTLABELS = [
					"Date and Time",
					"Auto Debit for Zakat Cancel Auto Debit",
					]
					this.verifyTextList(STATUSCANCEL_REJECTLABELS,timeout,FailureHandling.CONTINUE_ON_FAILURE)
				return
		}
	}
	
	private void verifyExpiredStatus(String editType,int timeout,FailureHandling flowControl) {
		List<String> GENERAL_EXPIREDLABELS = [
			"Set up unsuccessful",
			"Your Secure Verification authorisation has expired. Please perform the transaction again."
			]
			this.verifyTextList(GENERAL_EXPIREDLABELS,timeout,FailureHandling.CONTINUE_ON_FAILURE)
			
			verifyDateTime()
			verifyRefID()
		
		switch (editType) {
			case "Body":
			
				List<String> STATUSZAKATBODY_REJECTLABELS = [
					"Date and Time",
					"Auto Debit for Zakat Switch Body"
					]
					this.verifyTextList(STATUSZAKATBODY_REJECTLABELS,timeout,FailureHandling.CONTINUE_ON_FAILURE)

				return
		
			case "Number":
				
				List<String> STATUSNUMBER_REJECTLABELS = [
					"Date and Time",
					"Auto Debit for Zakat Edit Mobile No"
					]
					this.verifyTextList(STATUSNUMBER_REJECTLABELS,timeout,FailureHandling.CONTINUE_ON_FAILURE)
	
				return
		
			case "Account":
				
				List<String> STATUSACCOUNT_REJECTLABELS = [
					"Date and Time",
					"Auto Debit for Zakat Switch Account",
					]
					this.verifyTextList(STATUSACCOUNT_REJECTLABELS,timeout,FailureHandling.CONTINUE_ON_FAILURE)

				return
			case "Cancel":
				List<String> STATUSCANCEL_REJECTLABELS = [
					"Date and Time",
					"Auto Debit for Zakat Cancel Auto Debit",
					]
					this.verifyTextList(STATUSCANCEL_REJECTLABELS,timeout,FailureHandling.CONTINUE_ON_FAILURE)
				return
		}
	}

/**
* Verify Change in Each Edit Flow. Ex: Switch Zakat Body, Switch Account, Edit Mobile number and Cancel ZAD
* in Zakat Auto Debit Dashboard
* Status handling:
 *  - Approve  → use editValue
 *  - Reject / Expired / Cancel → use existingValue
*/
		void verifyChangesEdit(String EditType,String status, String editValue, String existingValue, timeout = GlobalVariable.G_timeoutShort,flowControl = FailureHandling.CONTINUE_ON_FAILURE) {
			
			 String expectedValue

			 'Decide expected value based on S2U result'
			 if (status == "Approve") {
				 commonPage.tapDoneBtn()
				 expectedValue = editValue
			 } else {
				 this.restartApp()
				 this.enterDashboard("ZakatDB",false)
				 expectedValue = existingValue
			 }
			
			Mobile.waitForElementPresent(commonPage.lblContainDynamic(ZADUI), timeout, flowControl)
			boolean isMatched = false
			
			switch (EditType) {
						case "Body":
							isMatched = this.verifyDynamicText(["Zakat Body": expectedValue],timeout,flowControl)
							break
				
						case "Number":
							String formattedMobile = this.formatMobileForUI(expectedValue,false)
							isMatched = this.verifyDynamicText(["Mobile No": formattedMobile],timeout,flowControl)
							break
				
						case "Account":
							isMatched = this.verifyDynamicText(["Pay From": expectedValue],timeout,flowControl)
							break
				
						case "Cancel":
						if (status == "Approve") {
							'Cancel approved → ZAD removed, Setup Now button shown'
							Mobile.verifyElementVisible(commonPage.lblDynamic(SETUPNOWbtn),timeout,flowControl)
							Mobile.takeScreenshot()
							isMatched = true
							} 
							else {
								'Cancel rejected / expired → ZAD still ACTIVE'
									isMatched = this.verifyDynamicText(["Status": "ACTIVE"],timeout,flowControl)
								}
							break
							return
					}
		}
		
/**
* Tap Pay & Transfer and check Password
* @return
*/
		String SETTINGbtn = "Settings"
		String PAYTRANSFERbtn = "Pay & Transfer"
		String PASSWORDPAGE = "Enter your password"
		void tapPayTransfer( timeout = GlobalVariable.G_timeoutShort,flowControl = FailureHandling.CONTINUE_ON_FAILURE) {
			
			Mobile.waitForElementPresent(commonPage.lblContainDynamic(SETTINGbtn), timeout, flowControl)
			MobileDriver.tap(commonPage.lblDynamic(PAYTRANSFERbtn), timeout, flowControl)
			Mobile.takeScreenshot()
			Mobile.delay(5)
			
			if(Mobile.waitForElementPresent(commonPage.lblDynamic(PASSWORDPAGE), timeout, FailureHandling.OPTIONAL)) {
				
				Mobile.setText(commonPage.txtSiblingDynamic(PASSWORDPAGE), "Maybank@1", timeout)
				Mobile.hideKeyboard()
				Mobile.tap(commonPage.lblDynamic("Continue"), timeout, flowControl)
				
				}
				Mobile.tap(commonPage.lblDynamic(ZADUI), timeout, flowControl)
			}

/**
 * verify & tap on Zakat Auto Debit Entry in Setting
 * @return
 */	
		List<String> SETUPSETTINGPAGE = [
			"You don't have any active auto debit for Zakat.",
			"Set one up today!"
			]
			
		void tapSetUpSetting(timeout = GlobalVariable.G_timeoutShort,flowControl = FailureHandling.OPTIONAL) {
			
			if(Mobile.verifyElementVisible(commonPage.lblDynamic(SETUPNOWbtn),timeout, flowControl)) {
				Mobile.takeScreenshot()
				
				this.verifyTextList(SETUPSETTINGPAGE,timeout,FailureHandling.OPTIONAL)
				Mobile.tap(commonPage.lblDynamic(SETUPNOWbtn), timeout, FailureHandling.OPTIONAL)
			}else {
				
			}
	
		}

/**
* verify & tap on Zakat Auto Debit Entry in Apply Services 
* @return
*/
		List<String> SETUPAPPLYZAKATPAGE = [
			"Auto Debit for Zakat Simpanan & Pelaburan",
			"Automate your zakat payments every year."
			]
			
		List <String> ZADSETUPNOWPAGE1 = [
			"Set Up Auto Debit for Zakat",
			"Enjoy hassle-free zakat payments and get notified when it's time to pay every year!",
			"Upon setup, we will:",
			"Auto calculate your zakat amount on 31 October every year",
			"Based on 2.5% of your total wealth with Maybank Islamic and subject to the nisab amount of your preferred zakat body",
			"Notify you of the final zakat amount via push notification by 6 November every year",
			"You can also view and download your zakat statement through Maybank2u.",
			"Auto debit the zakat from your account on 7 November every year",
			]
		
		void verifyApplyServices(String password, timeout = GlobalVariable.G_timeoutShort,flowControl = FailureHandling.CONTINUE_ON_FAILURE) {
			
			Mobile.doubleTap(commonPage.lblContainDynamic("SERVICES"),timeout, flowControl)
			
			Mobile.waitForElementPresent(commonPage.lblContainDynamic("Zakat"), timeout, FailureHandling.OPTIONAL)
			Mobile.takeScreenshot()
		
			this.verifyTextList(SETUPAPPLYZAKATPAGE)
			
			'Tap Tiles'
			Mobile.tap(commonPage.lblContainDynamic(ZADUI),timeout, flowControl)
			
			'Check Log In button'
			Mobile.waitForElementPresent(commonPage.lblContainDynamic("Enter your"), timeout, FailureHandling.OPTIONAL)
			
			Mobile.delay(2)
			'Tap X in log in'
			try {
				Mobile.tap(commonPage.imgNoDynamic(4), GlobalVariable.G_timeoutMedium, FailureHandling.STOP_ON_FAILURE)
			} catch (Exception e) {
				Mobile.tap(commonPage.imgNoDynamic(9), GlobalVariable.G_timeoutMedium, FailureHandling.STOP_ON_FAILURE)
			}
			
			'Tap Tiles'
			Mobile.tap(commonPage.lblContainDynamic(ZADUI),timeout, flowControl)
			
			'Enter password'
			commonPage.checkEnterPassword(password)
			
			Mobile.verifyElementVisible(commonPage.lblDynamic(SETUPNOWbtn),timeout, flowControl)
			Mobile.takeScreenshot()
			
		}
		
/**
* verify Zakat Auto Debit message pop out
* @return
*/
		void VerifyExceptionHandling (String ErrorMessage, timeout = GlobalVariable.G_timeoutShort,flowControl = FailureHandling.CONTINUE_ON_FAILURE) {
			List <String> UserNotCitizen = [
				ErrorMessage
				]
			
			this.verifyTextList(UserNotCitizen)
			Mobile.comment(ErrorMessage)
		}
		
/******************************************************ZAKAT ON DEMAND (START)**************************************************************************************************
 *********************************************************************************************************************************************************************************/
		
		String CALCULATETILESui = "Calculate"
		String ZAKATCALCULATORui = "Zakat Calculator"
		
		String CALANDPAY = "Calculate & Pay"
		String ZAKATTYPE = "Zakat type"
		String CALCULATEbtn = "Calculate"

/**
* verify & tap Zakat On Demand
* Tap Tiles name "Calculate & Pay Zakat" and "Calculate & Pay"
* @return
*/
		void tapZODTile (timeout = GlobalVariable.G_timeoutShort,flowControl = FailureHandling.CONTINUE_ON_FAILURE) {
	
			Mobile.waitForElementPresent(commonPage.lblContainDynamic(CALCULATETILESui), timeout, FailureHandling.OPTIONAL)
			Mobile.tap(commonPage.lblContainDynamic(CALCULATETILESui), timeout, FailureHandling.STOP_ON_FAILURE)
			
		}
		
/**
* verify & tap Zakat On Demand
* Tap Tiles name "Calculate & Pay Zakat" and "Calculate & Pay"
* @return
*/
		void tapCalculate(String ZakatType, boolean Verify = true, timeout = GlobalVariable.G_timeoutShort, flowControl = FailureHandling.CONTINUE_ON_FAILURE) {
	
			Mobile.waitForElementPresent(commonPage.lblContainDynamic(ZAKATCALCULATORui), timeout, FailureHandling.OPTIONAL)
			
			List<String> ZAKATCALCULATOR_PAGE1 = [
				ZAKATCALCULATORui,
				"New Calculation",
				CALANDPAY,
				"View All",
				"Saved Calculations"
				]
				
			'Verify All Text'
			if (Verify) { this.verifyTextList(ZAKATCALCULATOR_PAGE1) }
			Mobile.takeScreenshot()
			
			'Tap "Calculate & Pay" tile'
			Mobile.tap(commonPage.lblContainDynamic(CALANDPAY), timeout, FailureHandling.OPTIONAL)
			
			
			Mobile.waitForElementPresent(commonPage.lblDynamic("Zakat type"), timeout, flowControl)
			List<String> ZAKATCALCULATOR_PAGE2 = [
				ZAKATCALCULATORui,
				"Select a zakat and we’ll help calculate how much you’ll need to pay based on the latest nisab and zakat rate",
				"Zakat type",
				"Please select",
				"Pay Zakat Fitrah",
				"Have you fulfilled your Zakat Fitrah this Ramadhan season? Tap to pay now."
				]
				
			'Verify All Text'
			if (Verify) { this.verifyTextList(ZAKATCALCULATOR_PAGE2) }
			
			'Screenshot Before Select Dropdown'
			Mobile.takeScreenshot()
			info("Select Zakat Type")
			commonPage.selectDropdownSiblingGroupItem(ZAKATTYPE, ZakatType, timeout, flowControl)
			Mobile.takeScreenshot()
	
			info("Tap Next")
			Mobile.tap(commonPage.lblDynamic(NEXTbtn), timeout, flowControl)
		}
	
/**
* verify & tap Zakat AUto Debit pop up in Zakat On Demand
* @return
*/
		public boolean checkZADPopup(boolean Verify = true, int timeout = GlobalVariable.G_timeoutShort, flowControl = FailureHandling.CONTINUE_ON_FAILURE) {
			if(waitForElementPresent(commonPage.lblContainDynamic("Auto Debit"), timeout, FailureHandling.OPTIONAL)) {
				
				List<String> ZAKATAUTODEBIT_POPUP1 = [
					"Auto Debit already set up",
					"You already have Auto Debit for Zakat Simpanan & Pelaburan set up. Are you sure you want to proceed with this additional payment",
					"Cancel",
					"Yes, Proceed"
					]
					
				'Verify All Text'
				if (Verify) { this.verifyTextList(ZAKATAUTODEBIT_POPUP1, timeout, flowControl) }
				Mobile.takeScreenshot()
				MobileDriver.tap(commonPage.lblContainDynamic("Yes"), GlobalVariable.G_timeoutLong)
				return true
			}
			
			KeywordUtil.logInfo("Zakat Auto Debit popup not present.")
			return false
		}

/**
* verify & tap Zakat Body
* @return
*/
		void selectZakatBody(String ZakatBody, boolean Verify = true, timeout = GlobalVariable.G_timeoutShort, flowControl = FailureHandling.CONTINUE_ON_FAILURE) {
	
			Mobile.waitForElementPresent(commonPage.lblDynamic(ZAKATCALCULATORui), timeout, FailureHandling.OPTIONAL)
			
			List<String> ZAKATCALCULATOR_PAGE3 = [
				ZAKATCALCULATORui,
				"Please select the zakat body that you would like to contribute to.",
				"Lembaga Zakat Selangor",
				"Majlis Agama Islam Kelantan - MAIK",
				"Pusat Pungutan Zakat - MAIWP",
				"Lembaga Zakat Negeri Kedah"
				]
				
			'Verify All Text'
			if (Verify) { this.verifyTextList(ZAKATCALCULATOR_PAGE3) }
			
			if (ZakatBody == "Lembaga Zakat Negeri Kedah") {
				commonPage.scrollToEnd()
			}
			
			info("Choose Zakat Body")
			Mobile.tap(commonPage.lblDynamic(ZakatBody), timeout, flowControl)
			Mobile.takeScreenshot()
			
			info("Tap Next")
			Mobile.tap(commonPage.lblDynamic(NEXTbtn), timeout, flowControl)
		}
		
/**
* check for Pop Up when Calculation already existed
* @return
*/
		public boolean checkExistedCalculationPopup(int timeout = GlobalVariable.G_timeoutShort, flowControl = FailureHandling.CONTINUE_ON_FAILURE) {
			if(waitForElementPresent(commonPage.lblContainDynamic("Already Exists"), timeout, FailureHandling.OPTIONAL)) {
				Mobile.takeScreenshot()
				MobileDriver.tap(commonPage.lblContainDynamic("Create New"), GlobalVariable.G_timeoutLong)
				return true
			}
			
			KeywordUtil.logInfo("Popup not present.")
			return false
		}
	
/**
* check for Pop Up when Nisab Not Met
* @return
*/
		String NISABNOTMET = "Nisab Amount Not Met"
		String PAYZAKATANYWAY = "Pay Zakat Anyway"
		
		void NisabNotMet(timeout = GlobalVariable.G_timeoutShort, flowControl = FailureHandling.OPTIONAL) {
			
			Mobile.waitForElementPresent(commonPage.lblContainDynamic(NISABNOTMET), timeout, flowControl)
			
			List<String> NISABNOTMET_POPUP = [
				"Nisab Amount Not Met",
				"Done",
				"Pay Zakat Anyway"
				]
			
			'Verify All Text'
			this.verifyTextList(NISABNOTMET_POPUP,timeout,flowControl)
			
			Mobile.takeScreenshot()
			Mobile.tap(commonPage.lblContainDynamic(PAYZAKATANYWAY), timeout, flowControl)
		}
		
/**
* Verify and tap on the Zakat Pendapatan Flow 
* Have 2 page
* @return
*/
		String STEP1 = "Step 1"
		String STEP2 = "Step 2"
		String STEP3 = "Step 3"
		
		String EPFui = "EPF Contributions"
		
		void PendapatanSteps(String ZakatBody, String flowType, boolean Verify = true, timeout = GlobalVariable.G_timeoutShort, flowControl = FailureHandling.CONTINUE_ON_FAILURE) {
			
		String annualIncome
		String expenseValue
		String epfValue
	
		if (flowType == "positive") {
			annualIncome = "5000000"   // above nisab
			expenseValue = "200000"    // example for expenses
			epfValue     = "200"    // example for EPF
		} else {
			annualIncome = "1600000"      // below nisab
			expenseValue = "150000"       // lower value
			epfValue     = "800"        // lower value
		}
			
			'STEP 1'
			Mobile.waitForElementPresent(commonPage.lblContainDynamic(STEP1), timeout, flowControl)
	
			switch (ZakatBody) {
	
			case "Lembaga Zakat Selangor":
					
					'STEP 1'
					List<String> PENDAPATANSELANGOR_PAGE1 = [
						ZAKATCALCULATORui,
						"Kindly enter your income details",
						"Lembaga Zakat Selangor",
						"Annual remuneration and wages",
						"Annual independent and professional income (Optional)",
						"Al-mustaghallat income (Optional)",
						"Donation or gifts (Optional)"
						]
					
					'Verify All Text'
					if (Verify) { this.verifyTextList(PENDAPATANSELANGOR_PAGE1,timeout,flowControl) }
				
					'Edit Annual remuneration'
					Mobile.setText(commonPage.txtSiblingGroup("Annual remuneration and wages"), annualIncome, timeout)
					
					Mobile.tap(commonPage.lblContainDynamic("Annual"), timeout)
					if(Mobile.waitForElementPresent(commonPage.lblContainDynamic(DONEbtn), timeout, flowControl)) {
						Mobile.tap(commonPage.lblContainDynamic(DONEbtn), timeout, flowControl)
					}
					Mobile.takeScreenshot()
		
					info("Tap Next in Step 1")
					Mobile.tap(commonPage.lblDynamic(NEXTbtn), timeout, flowControl)
					
					Mobile.waitForElementPresent(commonPage.lblContainDynamic(STEP2), timeout, flowControl)
					Mobile.takeScreenshot()
					
					'STEP 2'
					List<String> PENDAPATANSELANGOR_PAGE2 = [
						ZAKATCALCULATORui,
						"Kindly enter your household details to calculate deductions",
						"Lembaga Zakat Selangor",
						"Head of family",
						"Working adults (above 18)",
						"Unemployed adults (above 18)",
						"Dependants studying in higher education"
						]
					
					'Verify All Text'
					if (Verify) { this.verifyTextList(PENDAPATANSELANGOR_PAGE2,timeout,flowControl) }
					
					commonPage.scrollToEnd()
					List<String> PENDAPATANSELANGOR_PAGE22 = [
						"Dependents aged 7 - 17 years old",
						"Dependents aged below 6 years old"
						]
					
					'Verify All Text'
					if (Verify) { this.verifyTextList(PENDAPATANSELANGOR_PAGE22,timeout,flowControl) }
					
					info("Tap Next in Step 2")
					Mobile.tap(commonPage.lblDynamic(NEXTbtn), timeout, flowControl)
	
					'STEP 3'
					Mobile.waitForElementPresent(commonPage.lblContainDynamic(STEP3), timeout, flowControl)
					Mobile.takeScreenshot()

					List<String> PENDAPATANSELANGOR_PAGE3 = [
						"Zakat Calculator",
						"Kindly enter details for additional deductions",
						"Lembaga Zakat Selangor",
						"Children with disabilities",
						"Dependents with chronic disease",
						"Child care costs",
						"Annual Tabung Haji savings (if any)"
						]
					
					'Verify All Text'
					if (Verify) { this.verifyTextList(PENDAPATANSELANGOR_PAGE3,timeout,flowControl) }

					commonPage.scrollToEnd()
					'Edit EPF'
					Mobile.setText(commonPage.txtSiblingGroup("EPF contributions"), epfValue, timeout)
					
					Mobile.tap(commonPage.lblContainDynamic("EPF"), timeout)
					if(Mobile.waitForElementPresent(commonPage.lblContainDynamic(DONEbtn), timeout, flowControl)) {
						Mobile.tap(commonPage.lblContainDynamic(DONEbtn), timeout, flowControl)
					}
					Mobile.takeScreenshot()
	
					info("Tap Calculate")
					Mobile.tap(commonPage.lblDynamic(CALCULATEbtn), timeout, flowControl)
	
					break
	
			case "Majlis Agama Islam Kelantan - MAIK":
					
					'STEP 1'
					List<String> PENDAPATANKELANTAN_PAGE1 = [
						"Zakat Calculator",
						"Kindly enter your income details",
						"Majlis Agama Islam Kelantan - MAIK",
						"Annual salary",
						"Annual bonus (Optional)",
						"Other sources of income (Optional)"
						]
					
					'Verify All Text'
					if (Verify) { this.verifyTextList(PENDAPATANKELANTAN_PAGE1,timeout,flowControl) }
					
					'Edit Annual salary'
					Mobile.setText(commonPage.txtSiblingGroup("Annual salary"), annualIncome, timeout)
					Mobile.tap(commonPage.lblContainDynamic("Annual"), timeout)
					if(Mobile.waitForElementPresent(commonPage.lblContainDynamic(DONEbtn), timeout, flowControl)) {
						Mobile.tap(commonPage.lblContainDynamic(DONEbtn), timeout, flowControl)
					}
					
					Mobile.takeScreenshot()
	
					info("Tap Next in Step 1")
					Mobile.tap(commonPage.lblDynamic(NEXTbtn), timeout, flowControl)
				
					Mobile.waitForElementPresent(commonPage.lblContainDynamic(STEP2), timeout, flowControl)
					'STEP 2'
	
					List<String> PENDAPATANKELANTAN_PAGE2 = [
						"Zakat Calculator",
						"Kindly enter details for deductions",
						"Majlis Agama Islam Kelantan - MAIK",
						"Own expenses",
						"Annual nafkah to wife (Optional)",
						"Children in primary/secondary school",
						"Children in higher education"
						]
					
					'Verify All Text'
					if (Verify) { this.verifyTextList(PENDAPATANKELANTAN_PAGE2,timeout,flowControl) }
					
					'Edit Own Expenses'
					Mobile.setText(commonPage.txtSiblingGroup("Own expenses"), expenseValue, timeout)
					Mobile.tap(commonPage.lblContainDynamic("Own"), timeout)
					if(Mobile.waitForElementPresent(commonPage.lblContainDynamic(DONEbtn), timeout, flowControl)) {
						Mobile.tap(commonPage.lblContainDynamic(DONEbtn), timeout, flowControl)
					}
					Mobile.takeScreenshot()
	
					info("Tap Next in Step 2")
					Mobile.tap(commonPage.lblDynamic(NEXTbtn), timeout, flowControl)
	
					'STEP 3'
	
					List<String> PENDAPATANKELANTAN_PAGE3 = [
						"Zakat Calculator",
						"Kindly enter details for additional deductions",
						"Majlis Agama Islam Kelantan - MAIK",
						"Total allowance to parents (Optional)",
						"Annual medical costs (Optional)",
						"Total Tabung Haji deductions (Optional)",
						"EPF contributions",
						"Other expenses (Optional)"
						]
					
					'Verify All Text'
					if (Verify) { this.verifyTextList(PENDAPATANKELANTAN_PAGE3,timeout,flowControl) }
					
					Mobile.waitForElementPresent(commonPage.lblContainDynamic(STEP3), timeout, flowControl)
	
					'Edit EPF'
					Mobile.setText(commonPage.txtSiblingGroup("EPF contributions"), epfValue, timeout)
					Mobile.tap(commonPage.lblContainDynamic("EPF"), timeout)
					if(Mobile.waitForElementPresent(commonPage.lblContainDynamic(DONEbtn), timeout, flowControl)) {
						Mobile.tap(commonPage.lblContainDynamic(DONEbtn), timeout, flowControl)
					}
					Mobile.takeScreenshot()
	
					info("Tap Calculate")
					Mobile.tap(commonPage.lblDynamic(CALCULATEbtn), timeout, flowControl)
	
					break
	
			case "Pusat Pungutan Zakat - MAIWP":
					
					'STEP 1'
					List<String> PENDAPATANKL_PAGE1 = [
						"Zakat Calculator",
						"Kindly enter your income details",
						"Pusat Pungutan Zakat - MAIWP",
						"Annual income",
						"Other sources of income (Optional)"
						]
				
					'Verify All Text'
					if (Verify) { this.verifyTextList(PENDAPATANKL_PAGE1,timeout,flowControl) }
					
					'Edit Annual income'
					Mobile.setText(commonPage.txtSiblingGroup("Annual income"), annualIncome, timeout)
					Mobile.tap(commonPage.lblContainDynamic("Annual"), timeout)
					if(Mobile.waitForElementPresent(commonPage.lblContainDynamic(DONEbtn), timeout, flowControl)) {
						Mobile.tap(commonPage.lblContainDynamic(DONEbtn), timeout, flowControl)
					}
					Mobile.takeScreenshot()
	
					info("Tap Next IN STEP 1")
					Mobile.tap(commonPage.lblDynamic(NEXTbtn), timeout, flowControl)
					
					'STEP 2'
					Mobile.waitForElementPresent(commonPage.lblContainDynamic(STEP2), timeout, flowControl)
					List<String> PENDAPATANKL_PAGE2 = [
						"Zakat Calculator",
						"Kindly enter your household details to calculate deductions",
						"Pusat Pungutan Zakat - MAIWP",
						"Own Expenses",
						"Wife"
						]
				
					'Verify All Text'
					if (Verify) { this.verifyTextList(PENDAPATANKL_PAGE2,timeout,flowControl) }
					commonPage.scrollToEnd()
					
					List<String> PENDAPATANKL_PAGE22 = [
						"Children (above 18)",
						"Parents' expenses (Optional)",
						"EPF Contributions",
						"Own education costs (Optional)",
						"Contributions to institutions that already contribute zakat (Optional)"
						]
					
					'Verify All Text'
					if (Verify) { this.verifyTextList(PENDAPATANKL_PAGE22,timeout,flowControl) }
					
					'Edit EPF'
					Mobile.setText(commonPage.txtSiblingGroup("EPF Contributions"), epfValue, timeout)
					Mobile.tap(commonPage.lblContainDynamic("EPF"), timeout)
					if(Mobile.waitForElementPresent(commonPage.lblContainDynamic(DONEbtn), timeout, flowControl)) {
						Mobile.tap(commonPage.lblContainDynamic(DONEbtn), timeout, flowControl)
					}
					Mobile.takeScreenshot()
	
					info("Tap Calculate")
					Mobile.tap(commonPage.lblDynamic(CALCULATEbtn), timeout, flowControl)
	
					break
	
				case "Lembaga Zakat Negeri Kedah":
					
					'STEP 1'
					
					'Edit Annual income'
					Mobile.setText(commonPage.txtSiblingGroup("Annual gross income from all sources"), annualIncome, timeout)
					Mobile.tap(commonPage.lblContainDynamic("Annual"), timeout)
					if(Mobile.waitForElementPresent(commonPage.lblContainDynamic(DONEbtn), timeout, flowControl)) {
						Mobile.tap(commonPage.lblContainDynamic(DONEbtn), timeout, flowControl)
					}
					Mobile.takeScreenshot()
	
					info("Tap Next")
					Mobile.tap(commonPage.lblDynamic(NEXTbtn), timeout, flowControl)
					
					Mobile.waitForElementPresent(commonPage.lblContainDynamic(STEP2), timeout, flowControl)
					
					'Step 2'
					List<String> PENDAPATANEDAH_PAGE1 = [
						"Zakat Calculator",
						"Kindly enter your household details to calculate deductions",
						"Lembaga Zakat Negeri Kedah",
						"Own expenses",
						"Wife",
						"Children",
						"EPF Contributions"
						]
				
					'Verify All Text'
					if (Verify) { this.verifyTextList(PENDAPATANEDAH_PAGE1,timeout,flowControl) }
					
					'Edit EPF'
					Mobile.setText(commonPage.txtSiblingGroup("EPF Contributions"), epfValue, timeout)
					Mobile.tap(commonPage.lblContainDynamic("EPF"), timeout)
					if(Mobile.waitForElementPresent(commonPage.lblContainDynamic(DONEbtn), timeout, flowControl)) {
						Mobile.tap(commonPage.lblContainDynamic(DONEbtn), timeout, flowControl)
					}
					Mobile.takeScreenshot()
					
					info("Tap Calculate")
					Mobile.tap(commonPage.lblDynamic(CALCULATEbtn), timeout, flowControl)
	
					break
			}
			
				if ("negative".equals(flowType)) {
					info("Nisab is Not Met")

					try {
						this.NisabNotMet()
					} catch (Exception e) {
						KeywordUtil.markFailed("NisabNotMet flow failed: " + e.message)
						Mobile.tap(commonPage.lblContainDynamic(PAYZAKATANYWAY), timeout, FailureHandling.OPTIONAL)
					}

				} else {
					info("Nisab is Met")
					if (Mobile.waitForElementPresent(commonPage.lblContainDynamic(PAYZAKATANYWAY), 2, FailureHandling.OPTIONAL)) {
						KeywordUtil.markWarning("Unexpected Nisab popup on positive flow")
						Mobile.tap(commonPage.lblContainDynamic(PAYZAKATANYWAY), timeout, FailureHandling.OPTIONAL)
					}
				}
		}
	
/**
* Verify and tap on the Zakat Simpanan Flow
* Have 2 page
* @return
*/
		void SimpananSteps(String ZakatBody, String flowType, boolean Verify = true, timeout = GlobalVariable.G_timeoutShort, flowControl = FailureHandling.CONTINUE_ON_FAILURE) {
	
			String savingValue
			String homeValue
			String bankValue
		
			if (flowType == "positive") {
				savingValue = "3800000"    // above nisab
				homeValue   = "1000000"
				bankValue   = "2900000"
			} else {
				savingValue = "100"       // below nisab
				homeValue   = "1000"
				bankValue   = "10000"
			}
			
			Mobile.waitForElementPresent(commonPage.lblContainDynamic(STEP1), timeout, flowControl)
	
			switch (ZakatBody) {
				case "Lembaga Zakat Selangor":
	
					'STEP 1'
					List<String> SIMPANANSELANGOR_PAGE1 = [
						"Zakat Calculator",
						"Kindly enter your savings details",
						ZakatBody,
						"Savings, current and, foreign account",
						"Fixed/structured deposits (Optional)",
						"Total KWSP balance (Optional)",
						"Other cash (Optional)"
						]
				
					'Verify All Text'
					this.verifyTextList(SIMPANANSELANGOR_PAGE1,timeout,flowControl)
					
					'Set Text'
					Mobile.setText(commonPage.txtSiblingGroup("Savings, current and, foreign account"), savingValue, timeout)
					Mobile.tap(commonPage.lblContainDynamic("Savings"), timeout)
					if(Mobile.waitForElementPresent(commonPage.lblContainDynamic(DONEbtn), timeout, flowControl)) {
						Mobile.tap(commonPage.lblContainDynamic(DONEbtn), timeout, flowControl)
					}
					Mobile.takeScreenshot()
					
					info("Tap Next")
					Mobile.tap(commonPage.lblDynamic(NEXTbtn), timeout, flowControl)
	
					'STEP 2'
					Mobile.waitForElementPresent(commonPage.lblContainDynamic(STEP2), timeout, flowControl)
					List<String> SIMPANANSELANGOR_PAGE2 = [
						"Zakat Calculator",
						"Kindly enter any necessary adjustments to deduct",
						ZakatBody,
						"Savings that you have already paid zakat for (Optional)",
						"Earnings that were not Shariah-compliant (Optional)"
						]
				
					'Verify All Text'
					this.verifyTextList(SIMPANANSELANGOR_PAGE2,timeout,flowControl)
					Mobile.takeScreenshot()
	
					info("Tap Calculate")
					Mobile.tap(commonPage.lblDynamic(CALCULATEbtn), timeout, flowControl)
	
					break
	
				case "Majlis Agama Islam Kelantan - MAIK":
	
					'STEP 1'
					List<String> SIMPANANKELANTAN_PAGE1 = [
						"Zakat Calculator",
						"Kindly enter your savings details",
						ZakatBody,
						"Lowest saving amount in the past year"
						]
				
					'Verify All Text'
					this.verifyTextList(SIMPANANKELANTAN_PAGE1,timeout,flowControl)
					Mobile.setText(commonPage.txtSiblingGroup("Lowest saving amount in the past year"), savingValue, timeout)
					Mobile.tap(commonPage.lblContainDynamic("saving"), timeout)
					if(Mobile.waitForElementPresent(commonPage.lblContainDynamic(DONEbtn), timeout, flowControl)) {
						Mobile.tap(commonPage.lblContainDynamic(DONEbtn), timeout, flowControl)
					}
					Mobile.takeScreenshot()
	
					info("Tap Calculate")
					Mobile.tap(commonPage.lblDynamic(CALCULATEbtn), timeout, flowControl)
	
					break
	
				case "Pusat Pungutan Zakat - MAIWP":
	
					'STEP 1'
					List<String> SIMPANANKL_PAGE1 = [
						"Zakat Calculator",
						"Kindly enter your savings details",
						ZakatBody,
						"Savings, current and, foreign account",
						"Fixed/structured deposits (Optional)",
						"Savings in takaful/insurance (Optional)",
						"Total KWSP balance (Optional)",
						"Other cash (Optional)"
						]
				
					'Verify All Text'
					this.verifyTextList(SIMPANANKL_PAGE1,timeout,flowControl)
					
					'Set Text'
					Mobile.setText(commonPage.txtSiblingGroup("Savings, current and, foreign account"), savingValue, timeout)
					Mobile.tap(commonPage.lblContainDynamic("Savings"), timeout)
					if(Mobile.waitForElementPresent(commonPage.lblContainDynamic(DONEbtn), timeout, flowControl)) {
						Mobile.tap(commonPage.lblContainDynamic(DONEbtn), timeout, flowControl)
					}
					Mobile.takeScreenshot()
	
					info("Tap Next")
					Mobile.tap(commonPage.lblDynamic(NEXTbtn), timeout, flowControl)
	
					'STEP 2'
					Mobile.waitForElementPresent(commonPage.lblContainDynamic(STEP2), timeout, flowControl)
					List<String> SIMPANANKL_PAGE2 = [
						"Zakat Calculator",
						"Kindly enter any necessary adjustments to deduct",
						ZakatBody,
						"Savings that you have already paid zakat for (Optional)",
						"Earnings that were not Shariah-compliant (Optional)"
						]
				
					'Verify All Text'
					this.verifyTextList(SIMPANANKL_PAGE2,timeout,flowControl)
					Mobile.takeScreenshot()
	
					info("Tap Calculate")
					Mobile.tap(commonPage.lblDynamic(CALCULATEbtn), timeout, flowControl)
	
					break
	
				case "Lembaga Zakat Negeri Kedah":
	
					'STEP 1'
					List<String> SIMPANANKEDAH_PAGE1 = [
						"Zakat Calculator",
						"Kindly enter your savings details",
						ZakatBody,
						"Savings kept in home",
						"Savings kept in bank",
						"Savings in Tabung Haji (Optional)"
						]
				
					'Verify All Text'
					this.verifyTextList(SIMPANANKEDAH_PAGE1,timeout,flowControl)
					
					'Set Text'
					Mobile.setText(commonPage.txtSiblingGroup("Savings kept in home"), homeValue, timeout)
					Mobile.tap(commonPage.lblContainDynamic("home"), timeout)
					if(Mobile.waitForElementPresent(commonPage.lblContainDynamic(DONEbtn), timeout, flowControl)) {
						Mobile.tap(commonPage.lblContainDynamic(DONEbtn), timeout, flowControl)
					}
					
					Mobile.setText(commonPage.txtSiblingGroup("Savings kept in bank"), bankValue, timeout)
					Mobile.tap(commonPage.lblContainDynamic("home"), timeout)
					if(Mobile.waitForElementPresent(commonPage.lblContainDynamic(DONEbtn), timeout, flowControl)) {
						Mobile.tap(commonPage.lblContainDynamic(DONEbtn), timeout, flowControl)
					}
					Mobile.takeScreenshot()

					info("Tap Calculate")
					Mobile.tap(commonPage.lblDynamic(CALCULATEbtn), timeout, flowControl)
	
					break
			}
			if ("negative".equals(flowType)) {
				info("Nisab is Not Met")

				try {
					this.NisabNotMet()
				} catch (Exception e) {
					KeywordUtil.markFailed("NisabNotMet flow failed: " + e.message)
						Mobile.tap(commonPage.lblContainDynamic(PAYZAKATANYWAY), timeout, FailureHandling.OPTIONAL)
				}

			} else {
				info("Nisab is Met")
				if (Mobile.waitForElementPresent(commonPage.lblContainDynamic(PAYZAKATANYWAY), 2, FailureHandling.OPTIONAL)) {
					KeywordUtil.markWarning("Unexpected Nisab popup on positive flow")
					Mobile.tap(commonPage.lblContainDynamic(PAYZAKATANYWAY), timeout, FailureHandling.OPTIONAL)
				}
			}
		}
	
/**
* Verify and tap on the Zakat Pelaburan Flow
* Have 2 page
* @return
*/
		String STOCKS = "Total stocks and general investments"
		
		void PelaburanSteps(String ZakatBody, String flowType, boolean Verify = true, timeout = GlobalVariable.G_timeoutShort, flowControl = FailureHandling.CONTINUE_ON_FAILURE) {
			
			String Expenses
				if (flowType == "positive") {
					Expenses   = "3800000"
				} else {
					Expenses  = "200"
				}
			
			'STEP 1'
			Mobile.waitForElementPresent(commonPage.lblContainDynamic(STEP1), timeout, flowControl)
			List<String> PELABURAN_PAGE1 = [
				"Zakat Calculator",
				"Kindly enter your investment details",
				ZakatBody,
				"Total stocks and general investments",
				"Other investments in capital markets and financial markets (Optional)",
				"Amount in institutions that already contributes zakat (Optional)"
				]
			
			'Verify All Text'
			this.verifyTextList(PELABURAN_PAGE1,timeout,flowControl)
			
			Mobile.setText(commonPage.txtSiblingGroup(STOCKS), Expenses, timeout)
			Mobile.tap(commonPage.lblContainDynamic(STOCKS), timeout)
			if(Mobile.waitForElementPresent(commonPage.lblContainDynamic(DONEbtn), timeout, flowControl)) {
				Mobile.tap(commonPage.lblContainDynamic(DONEbtn), timeout, flowControl)
			}
			Mobile.takeScreenshot()
	
			info("Tap Next")
			Mobile.tap(commonPage.lblDynamic(NEXTbtn), timeout, flowControl)
	
			'STEP 2'
			Mobile.waitForElementPresent(commonPage.lblContainDynamic(STEP2), timeout, flowControl)
			List<String> PELABURAN_PAGE2 = [
				"Zakat Calculator",
				"Kindly enter any necessary adjustments to deduct",
				ZakatBody,
				"Earnings, including dividend, or investments, that you have already paid zakat for (Optional)",
				"Earnings that were not Shariah-compliant (Optional)"
				]
			
			'Verify All Text'
			this.verifyTextList(PELABURAN_PAGE2,timeout,flowControl)
			Mobile.takeScreenshot()
	
			info("Tap Calculate")
			Mobile.tap(commonPage.lblDynamic(CALCULATEbtn), timeout, flowControl)
					
			if ("negative".equals(flowType)) {
					info("Nisab is Not Met")

					try {
						this.NisabNotMet()
					} catch (Exception e) {
						KeywordUtil.markFailed("NisabNotMet flow failed: " + e.message)
						Mobile.tap(commonPage.lblContainDynamic(PAYZAKATANYWAY), timeout, FailureHandling.OPTIONAL)
					}

				} else {
					info("Nisab is Met")
					if (Mobile.waitForElementPresent(commonPage.lblContainDynamic(PAYZAKATANYWAY), 2, FailureHandling.OPTIONAL)) {
						KeywordUtil.markWarning("Unexpected Nisab popup on positive flow")
						Mobile.tap(commonPage.lblContainDynamic(PAYZAKATANYWAY), timeout, FailureHandling.OPTIONAL)
					}
				}
		}
	
/**
* Verify and tap on the Zakat Emas Flow
* Have 1 page
* @return
*/
		String WEARING = "Gold intended for wearing"
		String TRADING = "Gold intended for trading or saving"
		
		void EmasSteps(String ZakatBody, String flowType, boolean Verify = true, timeout = GlobalVariable.G_timeoutShort, flowControl = FailureHandling.CONTINUE_ON_FAILURE) {
			
			String wearing
			String saving
				if (flowType == "positive") {
					wearing   = "85001"
					saving   = "85001"
				} else {
					wearing  = "10"
					saving   = "10"
				}
				
			Mobile.waitForElementPresent(commonPage.lblContainDynamic(STEP1), GlobalVariable.G_timeoutMedium, flowControl)
			List<String> EMAS_PAGE1 = [
				"Zakat Calculator",
				"Kindly enter your gold details",
				ZakatBody,
				]
			
			'Verify All Text'
			this.verifyTextList(EMAS_PAGE1,timeout,flowControl)
	
			if(Mobile.verifyElementExist(commonPage.lblDynamic(WEARING), timeout, FailureHandling.OPTIONAL)) {
				
				Mobile.setText(commonPage.txtSiblingGroup(WEARING), wearing, timeout)
				Mobile.tap(commonPage.lblContainDynamic("Zakat"), timeout, flowControl)
				if(Mobile.waitForElementPresent(commonPage.lblContainDynamic(DONEbtn), timeout, flowControl)) {
					Mobile.tap(commonPage.lblContainDynamic(DONEbtn), timeout, flowControl)
				}
				
				Mobile.delay(5)
				Mobile.takeScreenshot()
			}
	
			Mobile.setText(commonPage.txtSiblingGroup(TRADING), saving, timeout)
			Mobile.tap(commonPage.lblContainDynamic("Gold"), timeout, flowControl)
			if(Mobile.waitForElementPresent(commonPage.lblContainDynamic(DONEbtn), timeout, flowControl)) {
						Mobile.tap(commonPage.lblContainDynamic(DONEbtn), timeout, flowControl)
					}		
			Mobile.takeScreenshot()
	
			info("Tap Calculate")
			Mobile.tap(commonPage.lblDynamic(CALCULATEbtn), timeout, flowControl)
			
			if ("negative".equals(flowType)) {
					info("Nisab is Not Met")

					try {
						this.NisabNotMet()
					} catch (Exception e) {
						KeywordUtil.markFailed("NisabNotMet flow failed: " + e.message)
						Mobile.tap(commonPage.lblContainDynamic(PAYZAKATANYWAY), timeout, FailureHandling.OPTIONAL)
					}

				} else {
					info("Nisab is Met")
					if (Mobile.waitForElementPresent(commonPage.lblContainDynamic(PAYZAKATANYWAY), 2, FailureHandling.OPTIONAL)) {
						KeywordUtil.markWarning("Unexpected Nisab popup on positive flow")
						Mobile.tap(commonPage.lblContainDynamic(PAYZAKATANYWAY), timeout, FailureHandling.OPTIONAL)
					}
				}
		}
		
/**
* Verify and tap on the Zakat KWSP Flow
* Have 1 page except Selangor have 2 page
* @return
*/
		String KWSPRadio1 = "Below 55 years old"
		String KWSPAmount = "Withdrawal amount"
		
		void KWSPSteps(String ZakatBody, String flowType, boolean Verify = true, timeout = GlobalVariable.G_timeoutShort, flowControl = FailureHandling.CONTINUE_ON_FAILURE) {
	
			String expenses
				if (flowType == "positive") {
					expenses   = "3000000"
	
				} else {
					expenses  = "1000"
				}
			
			Mobile.waitForElementPresent(commonPage.lblContainDynamic(STEP1), timeout, flowControl)
			
			List<String> KWSP_PAGE1 = [
				"Zakat Calculator",
				ZakatBody
				]
			
			'Verify All Text'
			this.verifyTextList(KWSP_PAGE1,timeout,flowControl)
			
			if(ZakatBody =="Lembaga Zakat Selangor") {
				
				List<String> KWSP_PAGE2 = [
					"Zakat Calculator",
					"Kindly select your age range",
					ZakatBody,
					"Below 55 years old",
					"55 years old and above"
					]
				
				'Verify All Text'
				this.verifyTextList(KWSP_PAGE2,timeout,flowControl)
				
				Mobile.tap(commonPage.lblDynamic(KWSPRadio1), timeout, flowControl)
	
				info("Tap Next")
				Mobile.tap(commonPage.lblDynamic(NEXTbtn), timeout, flowControl)
			}
				
				List<String> KWSP_PAGE3 = [
					"Kindly enter your KWSP (EPF) details",
					KWSPAmount
					]
				
				'Verify All Text'
				if (Verify) { this.verifyTextList(KWSP_PAGE3,timeout,flowControl) }
	
				Mobile.setText(commonPage.txtSiblingGroup(KWSPAmount), expenses, timeout)
				Mobile.tap(commonPage.lblContainDynamic(KWSPAmount), timeout)
				if(Mobile.waitForElementPresent(commonPage.lblContainDynamic(DONEbtn), timeout, flowControl)) {
					Mobile.tap(commonPage.lblContainDynamic(DONEbtn), timeout, flowControl)
				}
				Mobile.takeScreenshot()
	
			info("Tap Calculate")
			Mobile.tap(commonPage.lblDynamic(CALCULATEbtn), timeout, flowControl)
			
			if ("negative".equals(flowType)) {
					info("Nisab is Not Met")

					try {
						this.NisabNotMet()
					} catch (Exception e) {
						KeywordUtil.markFailed("NisabNotMet flow failed: " + e.message)
						Mobile.tap(commonPage.lblContainDynamic(PAYZAKATANYWAY), timeout, FailureHandling.OPTIONAL)
					}

				} else {
					info("Nisab is Met")
					if (Mobile.waitForElementPresent(commonPage.lblContainDynamic(PAYZAKATANYWAY), 2, FailureHandling.OPTIONAL)) {
						KeywordUtil.markWarning("Unexpected Nisab popup on positive flow")
						Mobile.tap(commonPage.lblContainDynamic(PAYZAKATANYWAY), timeout, FailureHandling.OPTIONAL)
					}
				}
		}
		
/**
* verify Zakat On Demand Calculation Page
* verify text for Below Nisab and Above Nisab
* @return
*/
		String PAYZAKATNOWbtn = "Pay Zakat Now"
		String SAVEPAYLATERbtn = "Save & Pay Later"
		
		void ZODcalculationPage(String ZakatType, String flowType, boolean Verify = true, timeout = GlobalVariable.G_timeoutShort, flowControl = FailureHandling.CONTINUE_ON_FAILURE) {
			
			if (flowType == "negative") {
				
				List<String> BELOWNISAB_CALCULATIONPAGE = [
					"You are NOT obligated to pay zakat",
					"You do not meet the nisab/minimum amount for Zakat",
					ZakatType,
					"Payable amount"
					]
				
				'Verify All Text'
				if (Verify) { this.verifyTextList(BELOWNISAB_CALCULATIONPAGE,timeout,flowControl) }
				
			}else {
				List<String> NISABMET_CALCULATIONPAGE = [
					"Based on the details provided, we’ve calculated how much you need to pay. Below is the breakdown.",
					"Payable amount",
					ZakatType
					]
				
				'Verify All Text'
				if (Verify) { this.verifyTextList(NISABMET_CALCULATIONPAGE,timeout,flowControl) }
			}

			commonPage.scrollToEnd()
			Mobile.verifyElementExist(commonPage.lblDynamic(SAVEPAYLATERbtn), timeout, flowControl)
			Mobile.takeScreenshot()
			Mobile.tap(commonPage.lblDynamic(PAYZAKATNOWbtn), timeout, flowControl)
			
		}
		
/**
* verify Zakat On Demand Confirmation Page
* tap "Pay Now"
* @return
*/
		String PAYNOWbtn = "Pay Now"
		void ZODConfirmationPage(String ZakatBody, String ZakatType, boolean Verify = true, timeout = GlobalVariable.G_timeoutShort, flowControl = FailureHandling.CONTINUE_ON_FAILURE) {
	
			Mobile.waitForElementPresent(commonPage.lblDynamic("Confirmation"), timeout, flowControl)
	
			List<String> CONFIRMATION_ZOD1 = [
				ZakatBody,
				ZakatType,
				"Pay from",
				"Date",
				"Today",
				"Mobile Number",
				"Pay Now"
				]
				
			'Verify All Text'
			if (Verify) { this.verifyTextList(CONFIRMATION_ZOD1) }
			Mobile.takeScreenshot()
	
			info("Tap Pay Now")
			Mobile.tap(commonPage.lblDynamic(PAYNOWbtn), timeout, flowControl)
		}
		
/**
* verify Niat Pop Up
* @return
*/	
		String NIATPOPUPui = "Niat "
		public boolean checkNiatPopup(String ZakatType, int timeout = GlobalVariable.G_timeoutShort, flowControl = FailureHandling.CONTINUE_ON_FAILURE) {
			if(waitForElementPresent(commonPage.lblContainDynamic("Niat"), timeout, FailureHandling.OPTIONAL)) {
				Mobile.takeScreenshot()
				Mobile.verifyElementExist(commonPage.lblContainDynamic(NIATPOPUPui + ZakatType), timeout, flowControl)
				MobileDriver.tap(commonPage.lblDynamic("Confirm"), GlobalVariable.G_timeoutLong)
				return true
			}
			return false
		}
	
/**
* verify Zakat On Demand S2U page
* tap based on status
* @return
*/
		void ZODS2UPage(String status, String ZakatBody, String ZakatType, timeout = GlobalVariable.G_timeoutShort, flowControl = FailureHandling.CONTINUE_ON_FAILURE) {
	
			info("Verify Zakat Body & Type")
			
			List<String> S2U_ZOD1 = [
				"Secure2u authorisation",
				ZakatBody,
				ZakatType
				]
				
			'Verify All Text'
			this.verifyTextList(S2U_ZOD1)
			Mobile.takeScreenshot()
	
			switch(status) {
				case 'Expired':
					'Wait Done Button Present'
					MobileDriver.waitForElementPresent(commonPage.lblDynamic(DONEbtn), 60, flowControl)
					break
				case 'Reject':
					'Select Reject Button'
					MobileDriver.tap(commonPage.lblDynamic(REJECTbtn), timeout)
					break
				default:
					'Select Approve Button'
					MobileDriver.tap(commonPage.lblDynamic(APPROVEbtn), timeout)
					break
			}
		}
		
/**
* verify Zakat On Demand status page
* based on status
* @return
*/
		void ZODStatusPage(String status, timeout = GlobalVariable.G_timeoutShort, flowControl = FailureHandling.CONTINUE_ON_FAILURE) {
	
			Mobile.waitForElementPresent(commonPage.lblDynamic(DONEbtn), timeout, flowControl)
			Mobile.takeScreenshot()
			
			switch(status) {
				case 'Expired':
				
					List <String> ZODEXPIRED_STATUSPAGE = [
						"Zakat unsuccessful",
						"Your Secure Verification authorisation has expired. Please perform the transaction again.",
						"Transaction Type",
						"Pay Zakat"
					]

					'Verify All Text'
					this.verifyTextList(ZODEXPIRED_STATUSPAGE)
				
					'Verify Ref ID'
					this.verifyRefID()
					'Verify Date Time'
					this.verifyDateTime()
	
					break
				case 'Reject':
	
					List <String> ZODREJECT_STATUSPAGE = [
						"Zakat unsuccessful",
						"Transaction rejected",
						"Transaction Type",
						"Pay Zakat"
					]
	
					'Verify All Text'
					this.verifyTextList(ZODREJECT_STATUSPAGE)
					
					'Verify Ref ID'
					this.verifyRefID()
					'Verify Date Time'
					this.verifyDateTime()
	
					break
	
				case 'Approve':
					
					commonPage.scrollToEnd()
					List <String> ZODAPPROVE_STATUSPAGE = [
						"Zakat Successful",
						"Kindly note that Zakat payments will take up to 1 day to reflect in the Zakat dashboard.",
						"Complete your zakat with a doa. Tap to listen.",
						"Transaction Type",
						"Pay Zakat",
						"Calculate Another Zakat"
					]
	
					'Verify All Text'
					this.verifyTextList(ZODAPPROVE_STATUSPAGE,timeout, FailureHandling.STOP_ON_FAILURE)
					
					'Verify Ref ID'
					this.verifyRefID()
					'Verify Date Time'
					this.verifyDateTime()
					
					break
			}
			
			MobileDriver.tap(commonPage.lblDynamic(DONEbtn), timeout)
		}

/**
* verify Mark Calculation page
* pop up after Approve status page
* Tap "Mark As Completed"
* @return
*/
		public boolean checkMarkCalculationPopup(int timeout = GlobalVariable.G_timeoutShort, flowControl = FailureHandling.CONTINUE_ON_FAILURE) {

			if(waitForElementPresent(commonPage.lblContainDynamic("Mark calculation"), timeout, FailureHandling.OPTIONAL)) {
				
				List <String> ZODAPPROVE_POPUP = [
					"Mark calculation as Completed?",
					"Your calculated Zakat will be saved to your dashboard. Would you like to move it to the ‘Completed’ status?",
					"Keep Status As 'Unpaid'"
				]

				'Verify All Text'
				this.verifyTextList(ZODAPPROVE_POPUP)
				Mobile.takeScreenshot()
				MobileDriver.tap(commonPage.lblContainDynamic("Mark As"), GlobalVariable.G_timeoutLong)
				
				return true
			}
			
				KeywordUtil.logInfo("Mark calculation popup not present.")
				return false
			
		}
		
/**
 * Tap on Saved Calculation based Zakat Type
 * @return
*/
		void tapSavedCalculation (String ZakatType,timeout = GlobalVariable.G_timeoutShort, flowControl = FailureHandling.CONTINUE_ON_FAILURE) {
			
			def zakatObj = commonPage.lblContainDynamic(ZakatType)

			boolean visible = this.scrollUntilElementVisible(zakatObj, 5, timeout, FailureHandling.OPTIONAL)
	
			if (visible) {
				Mobile.tap(zakatObj, timeout, flowControl)
				return
			}

			KeywordUtil.markWarning("ZakatType '${ZakatType}' not found, tapping first available card")
			def fallbackObj = commonPage.lblContainDynamic("Payable amount")
			if (Mobile.waitForElementPresent(fallbackObj, timeout, FailureHandling.OPTIONAL)) {
				Mobile.tap(fallbackObj, timeout, flowControl)
			} else {
				KeywordUtil.markFailed("Failed to Tap on Saved Calculation")
			}
		
		}
		
/**
 * Verify Saved Calculation based Zakat Type and Zakat Body
 * Tap on 3 dot button
 * go to flow based on Edit Flow
* @return
*/
		String ZODDELETEbtn = "Delete Calculation"
		String ZODEDITbtn = "Edit Calculation"
		String ZODEDITWORD = "Calculation has been saved successfully!"
		
		void EditSavedCalculation (String ZakatBody, String editFlow, String flowType, boolean Verify = true, timeout = GlobalVariable.G_timeoutShort, flowControl = FailureHandling.CONTINUE_ON_FAILURE) {
			
			Mobile.delay(4)
			Mobile.waitForElementPresent(commonPage.lblDynamic("Pay Additional Zakat"), timeout, FailureHandling.STOP_ON_FAILURE)
			MobileDriver.tap(commonPage.imgNoDynamic(2), GlobalVariable.G_timeoutMedium, flowControl)
			Mobile.takeScreenshot()
			
			switch(editFlow) {
				case 'Edit':
	
					Mobile.waitForElementPresent(commonPage.lblDynamic(ZODEDITbtn), timeout, flowControl)
					Mobile.tap(commonPage.lblDynamic(ZODEDITbtn), timeout, flowControl)
					
					info("Tap Next button")
					int count = 0
					int maxNext = 3
					
					while (Mobile.verifyElementVisible(commonPage.lblContainDynamic(NEXTbtn), timeout, FailureHandling.OPTIONAL)) {
						Mobile.tap(commonPage.lblContainDynamic(NEXTbtn), timeout, FailureHandling.OPTIONAL)
						count++
						Mobile.delay(1)
						
						if (count >= maxNext) {
							break
						}
					}
					
					if (Mobile.verifyElementVisible(commonPage.lblContainDynamic(CALCULATEbtn), timeout, FailureHandling.OPTIONAL)) {
						Mobile.tap(commonPage.lblContainDynamic(CALCULATEbtn), timeout, flowControl)
					} else {
						KeywordUtil.markFailed("Calculate button not found")
						if (flowControl == FailureHandling.STOP_ON_FAILURE) {
							throw new StepFailedException("Calculate button not visible")
						}
					}
					
					this.NisabNotMet()					
					commonPage.scrollToEnd()

					Mobile.takeScreenshot()
					Mobile.tap(commonPage.lblDynamic(SAVEPAYLATERbtn), timeout, flowControl)
					
					Mobile.verifyElementExist(commonPage.lblContainDynamic(ZODEDITWORD), timeout, flowControl)
					Mobile.takeScreenshot()

					break
				case 'Delete':
					
					Mobile.waitForElementPresent(commonPage.lblDynamic(ZODDELETEbtn), timeout, flowControl)
				
					MobileDriver.tap(commonPage.lblDynamic(ZODDELETEbtn), timeout)
					
					Mobile.waitForElementPresent(commonPage.lblContainDynamic("Delete Calculation"), timeout, flowControl)
					this.verifySingleText("Are you sure you want to delete this saved calculation?", timeout, flowControl)
					Mobile.takeScreenshot()
					
					MobileDriver.tap(commonPage.lblContainDynamic("Yes"), timeout)
					Mobile.delay(4)
					Mobile.takeScreenshot()

					break
				}
		}

/**
* verify Dynamic Push Notification
* get PN from the PN Title (PNtitle need to be Static)
* need to create regex based on the PNmessage. 
* @return
*/
		void verifyPushNotificationDynamic(String pnTitle,String messageRegex,int timeout = GlobalVariable.G_timeoutLong,FailureHandling flowControl = FailureHandling.CONTINUE_ON_FAILURE) {
	
		Mobile.openNotifications()
	
		try {
			Mobile.delay(GlobalVariable.G_timeoutMiniShort)
			MobileDriver.takeScreenshot()
	
			TestObject pnObj = pushNotificationPage.lblPushNotificationDescription(pnTitle)
	
			String actualMessage = MobileDriver.getText(pnObj, timeout)
	
			Mobile.comment(actualMessage)
			KeywordUtil.logInfo("PN Title  : ${pnTitle}")
			KeywordUtil.logInfo("PN Message: ${actualMessage}")
			KeywordUtil.logInfo("Regex     : ${messageRegex}")
	
			if (!(actualMessage ==~ messageRegex)) {
				String errMsg =
						"❌ Push Notification message mismatch\n" +
						"Expected pattern: ${messageRegex}\n" +
						"Actual message  : ${actualMessage}"
	
				if (flowControl == FailureHandling.STOP_ON_FAILURE) {
					throw new StepFailedException(errMsg)
				} else {
					KeywordUtil.markWarning(errMsg)
				}
			} else {
				KeywordUtil.logInfo("✅ Push Notification verified successfully")
			}
	
		} catch (Exception ex) {
			Mobile.pressBack()
			handleError(ex, flowControl)
			return
		}
	
		Mobile.pressBack()
	}
	
/**
* verify M2U History after S2U Approve
* verify PN and restart APP
* navigate to Transaction History -> M2U History
* @return
*/
	void verifyM2UHistoryAfterS2UApprove(String status,String PNtitle,String PNregex,String account,String m2uHistoryProduct,String m2uHistoryAmount) {

    if (status != "Approve") {
        KeywordUtil.logInfo("Status is not Approve. Skip S2U verification.")
        return
    }

    'Verify Push Notification'
    this.verifyPushNotificationDynamic(PNtitle, PNregex)

    'Restart App'
    this.restartApp()

    'Tap Accounts'
	homePage.tapBtnAccounts()
	Mobile.delay(5)

    'Enter PIN'
    commonPage.checkEnterPIN(GlobalVariable.G_timeoutMiniShort)

    'Handle Got It (if any)'
    accountsPage.checkAndTapGotIt()

    'Tap Account'
    commonPage.tap(commonPage.lblContainDynamic(account),GlobalVariable.G_timeoutMiniShort)

    'Verify M2U History'
    AccountDetailpage.verifyM2UHistoryFirstItemToday(m2uHistoryProduct,m2uHistoryAmount)
	}
	
	void Test (timeout = GlobalVariable.G_timeoutShort, flowControl = FailureHandling.CONTINUE_ON_FAILURE) {
		'3. Click <-: Back to Previous Page'
		'4. Click X: Popup Leave Calculation will be appear'
		'4.1. Click X on Popup: Popup will be closed'
		'4.2. Click Cancel: Popup will be closed'
		'4.3. Click Leave: Back to Zakat Dashboard Page'
	}
}
