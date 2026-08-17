package com.maybank.mae.internal.pages.base.settingpage

import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.maybank.common.MobileDriver
import com.maybank.mae.internal.pages.base.BasePage
import com.maybank.mae.internal.pages.base.CommonPage
import com.maybank.mae.internal.pages.base.HomePage
import com.maybank.mae.internal.pages.base.LoginPage
import com.maybank.mae.internal.pages.base.OtpPage
import com.maybank.mae.internal.pages.base.Secure2uAuthorisationPage
import com.maybank.mae.internal.pages.base.StatusScreenPage
import com.maybank.utils.StringUtils

import io.appium.java_client.AppiumDriver as AppiumDriver
import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory

import org.openqa.selenium.By
import org.openqa.selenium.WebElement as WebElement



import internal.GlobalVariable

public abstract class SettingPage extends BasePage {
	abstract TestObject lblMaybank2u(String lblName)
	abstract TestObject lblSecure2u(String lblName)
	abstract TestObject lblDisableSecure2u(String lblName)
	abstract TestObject btnCancel(String lblName)
	abstract TestObject btnConfirm(String lblName)
	abstract TestObject btnBack()
	abstract TestObject lblSecure2uDisabled(String lblName)
	abstract TestObject lblNotifications(String lblName)
	abstract TestObject imgCameraIcon()
	abstract TestObject btnCameraClick()
	abstract TestObject btnCameraTick()
	abstract TestObject textProfileUpdatedSuccessfully()
	abstract TestObject btnTick()
	abstract TestObject inputPhoneNumber()
	abstract TestObject inputEmailId()
	abstract TestObject txtCurrentDuitNowAccount()
	abstract TestObject txtSiblingDynamic(String text)
	HomePage homePage = BasePage.getPage(HomePage)
	Secure2uAuthorisationPage secure2uAuthorisationPage = BasePage.getPage(Secure2uAuthorisationPage)
	StatusScreenPage statusScreenPage = BasePage.getPage(StatusScreenPage)

	String UIProfile = 'Profile'
	String UIMaybank2u = 'Security'
	String UISecure2u = 'Secure2u'
	String UIDisableSecure2u = 'Disable Secure2u'
	String UICancel = 'Cancel'
	String UIConfirm = 'Confirm'
	String UISecure2uDisabled = 'Secure2u disabled.'
	String UINotifications = 'Notifications'
	String ToAccount = null;
	String UIPersonalDetails = 'Personal Details'
	String UIReferralCode = 'Referral code'
	String UIYourReferralCode = 'Your Referral Code'
	String UIDefaultView = 'Default View'
	String UIDefaultAppView = 'Default App View'
	String UIMobileNumber = 'Mobile number'
	String UIMAEMobileNumber = 'MAE mobile number'
	String UISaveChanges  = 'Save Changes'
	String UIEmailAddress  = 'Email address'

	String UIAgreeAndSubmit = 'Agree & Submit'
	String UIDone = 'Done'
	String UIContinue = 'Continue'
	String UIAgreeAndConfirm = 'Agree and Confirm'

	CommonPage commonPage = getPage(CommonPage)
	LoginPage loginPage = getPage(LoginPage)
	OtpPage otpPage = getPage(OtpPage)
	Secure2uAuthorisationPage secure2uAutorisationPage = getPage(Secure2uAuthorisationPage)

	String UILblAtmCashout = 'ATM Cash-out'
	String UILblUnLinkAtmCashout = 'Unlink ATM Cash-out'
	String UILblNoteAtmCashout = 'Are you sure want to disable this feature? This means that you will not be able to withdraw money via app'
	String UILblDisableAtmCashout = 'ATM Cash-out successfully disabled.'
	String referalCodeCopyMessage = 'Referral code copied to clipboard.'
	String defaultViewMessage = 'Default view updated successfully to'
	static final String NAME = 'Name'
	String UIlblDeleteM2UAccess = 'By deleting your M2U access, you will no longer be able to perform online banking transaction online.'
	String UIlblReadAndAgree = 'I have read and agree'
	String UIlblATMCashoutConfirm = 'I confirm that I have read'
	String UIATMCashoutMsg = 'You will need an active debit card to register this feature.'
	String UIAbout = 'About'
	String UIAboutDescription = 'About MAE • Version'
	String UIAboutUs = 'About Us'
	String UISupport = 'Support'
	String UISupportDescription = 'Access customer service'
	String UICards = 'Cards'
	String UIViewCards = 'View Cards'
	String UIPayNTransfer = 'Pay & Transfer'
	String UIScanNPayLimit = 'Scan & Pay Limit'
	String UIPay = 'Pay'
	String UITransfer = 'Transfer'
	String UIDuitNowServices = 'DuitNow Services'
	String UIAutoDebitForZakat = 'Auto Debit for Zakat'
	String UIAccounts = 'Accounts'
	String UILinkedAccounts = 'Linked Accounts'
	String UILinkaccount = 'Link account'
	String UIOverseasAccounts = 'Overseas Accounts'
	String UIM2UAccess = 'M2U Access'
	String UIUnlinkAccessFromMAE = 'Unlink Access from MAE'
	String UIDeleteM2UAccess = 'Delete M2U Access'
	String UILinkM2UAccess = 'Link M2U Access'
	String UILinkNow = 'Link Now'
	String UIATMCashout = 'ATM Cash-out'
	String UIDuitNowFirstTimeRegistrationMsg = 'Link your Maybank account to an ID for DuitNow'
	String UIDeregisterDuitNow = 'Deregister DuitNow'
	String UISwitchAccount = 'Switch account'
	String UIDisableTemporarily = 'Disable Temporarily'
	String UIDuitNowAutoDebit = 'DuitNow AutoDebit'
	String UIDuitNowBlockedList = 'DuitNow Blocked List'
	String successfulMessage = ''
	String deregisterMessage = ''
	String ProfileUpdatedMessage = 'Profile successfully updated.'
	String UISecurity = 'Security'
	String UIChangeAppPIN = "Change App PIN"
	String UIChangePassword = "Change Password"
	String UISecurityQuestions = "Security Questions"
	String UIEInvoice = "e-Invoice (LHDN)"
	
	/**
	 * Tap Maybank2u
	 */
	public void tapLblMaybank2u( String lblName = UISecurity, int timeOut = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		Mobile.tap(this.lblMaybank2u(lblName), timeOut, flowControl)
	}

	/**
	 * Tap Secure2u
	 */
	public void tapLblSecure2u( String lblName = UISecure2u, int timeOut = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		if (!Mobile.waitForElementPresent(this.lblSecure2u(lblName), GlobalVariable.G_timeoutShort, FailureHandling.STOP_ON_FAILURE)) {
			this.tapLblSecurity()
			Mobile.delay(timeOut)
		}
		
		Mobile.tap(this.lblSecure2u(lblName), timeOut, flowControl)
	}

	/**
	 * Tap LblAccounts
	 */
	public void tapLblAccounts( String lblName = UIAccounts, int timeOut = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		Mobile.tap(this.lblSecure2u(lblName), timeOut, flowControl)
	}
	/**
	 * Tap UILinkedAccounts
	 */
	public void tapUILinkedAccounts( String lblName = UILinkedAccounts, int timeOut = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		Mobile.tap(this.lblSecure2u(lblName), timeOut, flowControl)
	}
	/**
	 * Tap UIM2UAccess
	 */
	public void tapUIM2UAccess( String lblName = UIM2UAccess, int timeOut = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		Mobile.tap(this.lblSecure2u(lblName), timeOut, flowControl)
	}
	/**
	 * Tap UIOverseasAccounts
	 */
	public void tapUIOverseasAccounts( String lblName = UIOverseasAccounts, int timeOut = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		Mobile.tap(this.lblSecure2u(lblName), timeOut, flowControl)
	}
	
	/**
	 * Tap UIEInvoice
	 */
	public void tapUIEInvoice( String lblName = UIEInvoice, int timeOut = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		Mobile.tap(this.lblSecure2u(lblName), timeOut, flowControl)
	}
	
	/**
	 * Verify Disable Secure2u exist
	 * @return true if Disable Secure2u exist, false if not exist
	 */
	public boolean verifyLblDisableSecure2uExist(String lblName = UIDisableSecure2u, int timeOut = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		return Mobile.verifyElementExist(this.lblDisableSecure2u(lblName), timeOut, flowControl)
	}

	/**
	 * Tap Cancel button
	 */
	public void tapBtnCancel( String lblName = UICancel, int timeOut = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		Mobile.tap(this.btnCancel(lblName), timeOut, flowControl)
	}

	/**
	 * Tap Confirm button
	 */
	public void tapBtnConfirm( String lblName = UIConfirm, int timeOut = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		Mobile.tap(this.btnConfirm(lblName), timeOut, flowControl)
	}

	/**
	 * Tap Back button
	 */
	public void tapBtnBack( int timeOut = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		Mobile.tap(this.btnBack(), timeOut, flowControl)
	}
	
	/**
	 * Tap Change App PIN
	 */
	public void tapChangeAppPIN(int timeOut = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		Mobile.tap(commonPage.lblDynamic(UIChangeAppPIN), timeOut, flowControl)
	}
	
	/**
	 * Tap Change Password
	 */
	public void tapChangePassword(int timeOut = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		Mobile.tap(commonPage.lblDynamic(UIChangePassword), timeOut, flowControl)
	}
	
	/**
	 * Tap UISecurity Questions
	 */
	public void tapSecurityQuestions(int timeOut = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		Mobile.tap(commonPage.lblDynamic(UISecurityQuestions), timeOut, flowControl)
		Mobile.delay(GlobalVariable.G_timeoutMiniShort)
	}
	
	/**
	 * Verify Secure2u Disabled exist
	 * @return true if Secure2u Disabled exist, false if not exist
	 */
	public boolean verifyLblSecure2uDisabledExist(String lblName = UISecure2uDisabled, int timeOut = GlobalVariable.G_timeoutLong, FailureHandling flowControl = defaultFailureHandling) {
		Mobile.waitForElementPresent(this.lblSecure2uDisabled(lblName), timeOut, flowControl)

		return Mobile.verifyElementExist(this.lblSecure2uDisabled(lblName), timeOut, flowControl)
	}

	/**
	 * Tap Maybank2u item
	 */
	public boolean tapItemMaybank2u(String itemName, FailureHandling flowControl = defaultFailureHandling) {
		'Select Maybank2u'
		MobileDriver.tap(commonPage.lblDynamic(UIMaybank2u), GlobalVariable.G_timeoutLong, flowControl)
		'Select item'
		MobileDriver.tap(commonPage.lblDynamic(itemName), GlobalVariable.G_timeoutLong, flowControl)
	}

	/**
	 * Tap Notifications
	 */
	public void tapLblNotifications( String lblName = UINotifications, int timeOut = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		MobileDriver.scrollToText(lblName)
		MobileDriver.tap(this.lblNotifications(lblName), timeOut, flowControl)
	}

	/**
	 * Check Enter PIN and Password
	 */
	public void checkEnterPINPassword(String password, FailureHandling flowControl = defaultFailureHandling) {
		'Select Maybank2u'
		Mobile.tap(this.lblMaybank2u(UISecurity), GlobalVariable.G_timeoutLong, flowControl)
		'If Enter Password exist, enter password'
		commonPage.checkEnterPassword(password)
		Mobile.delay(1)
		MobileDriver.verifyElementVisible(commonPage.lblDynamic(UISecurity), GlobalVariable.G_timeoutMedium, flowControl)
		'Select Profile'
		Mobile.tap(commonPage.lblDynamic(UIProfile), GlobalVariable.G_timeoutLong, flowControl)
		'If Enter PIN exist, enter PIN'
		commonPage.checkEnterPIN()
		'Back'
		Mobile.tap(commonPage.imgNoDynamic(1), GlobalVariable.G_timeoutLong, flowControl)
		Mobile.delay(1)
		'Back'
		Mobile.tap(commonPage.imgNoDynamic(1), GlobalVariable.G_timeoutLong, flowControl)
	}

	/**
	 * Check Enter Password
	 */
	public void checkEnterPassword(String password, FailureHandling flowControl = defaultFailureHandling) {
		'Select Maybank2u'
		Mobile.tap(this.lblMaybank2u(UISecurity), GlobalVariable.G_timeoutLong, flowControl)
		'If Enter Password exist, enter password'
		commonPage.checkEnterPassword(password)
		MobileDriver.delay(GlobalVariable.G_timeoutMiniShort)
		MobileDriver.verifyElementVisible(commonPage.lblDynamic(UISecurity), GlobalVariable.G_timeoutLong, flowControl)

		'Back to home'
		commonPage.backToHome()
	}

	/**
	 * Verify Profiles exist
	 */
	void verifyProfilesExist(flowControl) {
		Mobile.verifyElementExist(this.commonPage.lblDynamic('Profile'), GlobalVariable.G_timeoutMedium, flowControl)
	}

	/**
	 * Verify Maybank2u exist
	 */
	void verifyMaybank2uExist(flowControl) {
		Mobile.verifyElementExist(this.commonPage.lblDynamic('Maybank2u'), GlobalVariable.G_timeoutMedium, flowControl)
	}

	/**
	 * Verify Security exist
	 */
	void verifySecirityExist(flowControl) {
		Mobile.verifyElementExist(this.commonPage.lblDynamic('Security'), GlobalVariable.G_timeoutMedium, flowControl)
	}

	/**
	 * Verify Notifications exist
	 */
	void verifyNotificationsExist(flowControl) {
		Mobile.verifyElementExist(this.commonPage.lblDynamic('Notifications'), GlobalVariable.G_timeoutMedium, flowControl)
	}

	/**
	 * Verify Support exist
	 */
	void verifySupportExist(flowControl) {
		Mobile.verifyElementExist(this.commonPage.lblDynamic('Support'), GlobalVariable.G_timeoutMedium, flowControl)
	}

	/**
	 * Verify Default View exist
	 */
	void verifyDefaultViewExist(flowControl) {
		Mobile.verifyElementExist(this.commonPage.lblDynamic('Default View'), GlobalVariable.G_timeoutMedium, flowControl)
	}

	/**
	 * Verify About exist
	 */
	void verifyAboutSettingsExist(flowControl) {
		Mobile.verifyElementExist(this.commonPage.lblDynamic('About'), GlobalVariable.G_timeoutMedium, flowControl)
	}

	/**
	 * Select Maybank2u
	 */
	void clickMaybank2uExist(flowControl) {
		Mobile.tap(this.commonPage.lblDynamic('Maybank2u'), GlobalVariable.G_timeoutMedium, flowControl)
	}

	/**
	 * Select Scan & Pay
	 */
	void tapScanAndPay(flowControl) {
		Mobile.tap(this.commonPage.lblDynamic('Scan & Pay'), GlobalVariable.G_timeoutMedium, flowControl)
	}
	
	void tapPaynTransfer(flowControl) {
		Mobile.verifyElementExist(this.commonPage.lblDynamic("Pay & Transfer"), GlobalVariable.G_timeoutMedium, flowControl)
		Mobile.tap(this.commonPage.lblDynamic("Pay & Transfer"), GlobalVariable.G_timeoutMedium, flowControl)
	}

	/**
	 * Verify Daily Limit exist
	 */
	void verifyDailyLimitText(flowControl) {
		Mobile.verifyElementExist(this.commonPage.lblDynamic('Daily Limit'), GlobalVariable.G_timeoutMedium, flowControl)
	}

	/**
	 * Verify Cumulative Limit exist
	 */
	void verifyCumulativeLimitText(flowControl) {
		Mobile.verifyElementExist(this.commonPage.lblDynamic('Cumulative Limit'), GlobalVariable.G_timeoutMedium, flowControl)
	}
	
	void decreaseLimitTransfer(flowControl) {
		Mobile.tap(this.commonPage.lblContainDynamic("RM"), GlobalVariable.G_timeoutMedium, flowControl)
		Mobile.delay(3)
		this.commonPage.scrollDownDynamic(25)
		Mobile.delay(3)
		Mobile.tap(this.commonPage.lblDynamic("Done"), GlobalVariable.G_timeoutMedium, flowControl)
		Mobile.delay(3)
		Mobile.tap(this.commonPage.lblDynamic("Done"), GlobalVariable.G_timeoutMedium, flowControl)
		
	}
	
	void increaseLimitTransfer(flowControl) {
		Mobile.delay(3)
		Mobile.tap(this.commonPage.lblContainDynamic("RM"), GlobalVariable.G_timeoutMedium, flowControl)
		Mobile.delay(3)
		this.commonPage.scrollUpDynamic()
		Mobile.delay(3)
		Mobile.tap(this.commonPage.lblDynamic("Done"), GlobalVariable.G_timeoutMedium, flowControl)
		Mobile.delay(3)
		Mobile.tap(this.commonPage.lblDynamic("Done"), GlobalVariable.G_timeoutMedium, flowControl)
		
	}

	void clickSaveAndChange(flowControl) {
		Mobile.delay(3)
		this.commonPage.waitForElementPresent(this.commonPage.lblDynamic("Save Changes"), GlobalVariable.G_timeoutMedium)
		Mobile.tap(this.commonPage.lblDynamic("Save Changes"), GlobalVariable.G_timeoutMedium, flowControl)
	}

	/**
	 * Verify Terms & Conditions exist
	 */
	void verifyTermsAndConditionsText(flowControl) {
		Mobile.verifyElementExist(this.commonPage.lblDynamic('Terms&Conditions'), GlobalVariable.G_timeoutMedium, flowControl)
	}

	/**
	 * Select Confirm button
	 */
	void tapConfirmButton(flowControl) {
		Mobile.tap(this.commonPage.lblDynamic('Confirm'), GlobalVariable.G_timeoutMedium, flowControl)
	}

	/**
	 * Enter pasword
	 */
	void enterPassword(String password, flowControl) {
		MobileDriver.setEncryptedText(loginPage.txtPasswordRegister(), password, GlobalVariable.G_timeoutLong, flowControl)
	}

	/**
	 * Select Profile
	 */
	void clickProfileButton(flowControl) {
		Mobile.delay(GlobalVariable.G_timeoutMiniShort)
		MobileDriver.tap(this.commonPage.lblDynamic('Profile'), GlobalVariable.G_timeoutLong, flowControl)
	}

	/**
	 * Verify Update Profile exist
	 */
	void verifyTextUpdateProfile(flowControl) {
		'L3'
		commonPage.tapBack()
		Mobile.tap(this.lblMaybank2u(UISecurity), GlobalVariable.G_timeoutLong, flowControl)
		'If Enter Password exist, enter password'
		commonPage.checkEnterPassword(GlobalVariable.G_password)
		Mobile.delay(1)
		MobileDriver.verifyElementVisible(commonPage.lblDynamic(UISecurity), GlobalVariable.G_timeoutMedium, flowControl)
		commonPage.tapBack()
		MobileDriver.tap(this.commonPage.lblDynamic('Profile'), GlobalVariable.G_timeoutLong, flowControl)
		'Tap Personal Details'
		MobileDriver.waitForElementPresent(commonPage.lblDynamic(UIPersonalDetails), GlobalVariable.G_timeoutMedium, flowControl)
		MobileDriver.tap(commonPage.lblDynamic(UIPersonalDetails), GlobalVariable.G_timeoutMedium, flowControl)
		'Verify Header Update Profile'
		MobileDriver.verifyElementExist(commonPage.lblDynamic('Update Profile'), GlobalVariable.G_timeoutLong, flowControl)
	}

	/**
	 * Verify Update Profile exist
	 */
	void verifyPhoneNumber(flowControl) {
		MobileDriver.verifyElementExist(this.commonPage.lblDynamic('Update Profile'), GlobalVariable.G_timeoutLong, flowControl)
	}

	/**
	 * Verify update profile picture successfully
	 */
	void verifyProfilePictureUpdatedSuccessfully(flowControl) {		
		'Select Camera icon'
		MobileDriver.tap(this.imgCameraIcon(), GlobalVariable.G_timeoutLong, flowControl)
		'Select Take a photo'
		MobileDriver.tap(this.commonPage.lblDynamic('Take a photo'), GlobalVariable.G_timeoutLong, flowControl)
		'Take a photo'
		MobileDriver.tap(commonPage.imgNoDynamic(3), GlobalVariable.G_timeoutLong, flowControl)
		delay(GlobalVariable.G_timeoutMiniShort)
		'Take a photo again'		
		MobileDriver.tap(commonPage.btnTextDynamic(''), GlobalVariable.G_timeoutLong, flowControl)
		'Verify text - Profile successfully updated.'
		MobileDriver.verifyElementVisible(this.textProfileUpdatedSuccessfully(), GlobalVariable.G_timeoutLong, flowControl)
		turnOffSuccessMessage()
	}

	/**
	 * Verify Phone Number updated successfully
	 */
	void verifyPhoneNumberUpdatedSuccessfully(flowControl) {
		'Select Mobile number'
		MobileDriver.tap(this.commonPage.lblDynamic(UIMAEMobileNumber), GlobalVariable.G_timeoutLong, flowControl)
		'Generate random number'
		int randomInteger = MobileDriver.randomInteger(163456714, 163456739)
		'Covnert random number to string'
		String phoneNumber = String.valueOf(randomInteger)
		'Enter phone number'
		MobileDriver.enterPinNo(phoneNumber)
		'Select Tick button'
		MobileDriver.tap(commonPage.btnTick(), GlobalVariable.G_timeoutLong, flowControl)
	}

	/**
	 * Verify Email Id updated successfully
	 */
	void verifyEmailIdUpdatedSuccessfully(flowControl) {
		'Select Email address'
		MobileDriver.tap(commonPage.lblDynamic(UIEmailAddress), GlobalVariable.G_timeoutLong, flowControl)
		'Clear text'
		Mobile.clearText(this.txtSiblingDynamic(UIEmailAddress), GlobalVariable.G_timeoutShort)
		'Generate random number'
		int randomInteger = MobileDriver.randomInteger(11, 99)
		'Covnert random number to string'
		String emailId = String.valueOf(randomInteger)
		'Convert emailId to email format'
		emailId = 'test'+emailId+'@gmail.com'
		'Enter email'
		MobileDriver.setText(this.txtSiblingDynamic(UIEmailAddress), emailId, GlobalVariable.G_timeoutShort, flowControl)
		'Select Save Changes'
		MobileDriver.tap(commonPage.lblDynamic(UISaveChanges), GlobalVariable.G_timeoutLong, flowControl)
		'Verify text - Profile successfully updated.'
		MobileDriver.verifyElementVisible(this.textProfileUpdatedSuccessfully(), GlobalVariable.G_timeoutLong, flowControl)
	}

	/**
	 * Select DuitNow button
	 */
	void clickDuitNowButton(flowControl) {
		'Select DuitNow'
		MobileDriver.tap(this.commonPage.lblDynamic('DuitNow'), GlobalVariable.G_timeoutLong, flowControl)
		Mobile.delay(GlobalVariable.G_timeoutLong)
		'Verify text - Your DuitNow IDs exist'
		MobileDriver.verifyElementVisible(this.commonPage.lblDynamic('Your DuitNow IDs'), GlobalVariable.G_timeoutLong, flowControl)
	}

	/**
	 * Verify NRIC Number text
	 */
	void verifyNRICNumbertext(flowControl) {
		'Select NRIC Number'
		MobileDriver.tap(this.commonPage.lblDynamic('NRIC Number'), GlobalVariable.G_timeoutLong, flowControl)
		'Verify text - ACTIVE exist'
		MobileDriver.verifyElementExist(this.commonPage.lblDynamic('ACTIVE'), GlobalVariable.G_timeoutLong, flowControl)
	}

	/**
	 * Switch Account for NRIC Number
	 */
	void switchAccountForNricNumber (flowControl) {
		'Get Receiving Account number'
		String txtCurrentDuitNowAccount = MobileDriver.getText(this.txtCurrentDuitNowAccount(), GlobalVariable.G_timeoutLong, flowControl)
		'Remove * from the account number'
		txtCurrentDuitNowAccount = txtCurrentDuitNowAccount.replace("*", "")
		'Select Switch account'
		MobileDriver.tap(this.commonPage.lblDynamic('Switch account'), GlobalVariable.G_timeoutLong, flowControl)
		AppiumDriver driver = MobileDriverFactory.getDriver()
		List<WebElement> elements = driver.findElements(By.xpath("(//android.view.ViewGroup//android.view.ViewGroup//android.widget.ImageView//following-sibling::android.widget.TextView[2])"))
		'Get the account number which is not equal to the current account number'
		for(WebElement account : elements) {
			ToAccount = account.getText()
			'If the account number is not equal to the current account number, select the account number. Then, break the loop'
			if(!ToAccount.contains(txtCurrentDuitNowAccount)) {
				account.click()
				break
			}
		}
		'Click Continue Button'
		MobileDriver.tap(this.commonPage.lblDynamic('Continue'), GlobalVariable.G_timeoutLong, flowControl)
		'Verify text - Please confirm your DuitNow details'
		MobileDriver.verifyElementExist(this.commonPage.lblDynamic('Please confirm your DuitNow details'), GlobalVariable.G_timeoutLong, flowControl)
		/*'Verify the account number selected for DuitNow is displaed in the next screen'
		 MobileDriver.verifyElementExist(this.commonPage.lblDynamic(ToAccount), GlobalVariable.G_timeoutLong, flowControl)*/
		'Tap - Agree and Confirm'
		MobileDriver.tap(this.commonPage.lblDynamic('Agree and Confirm'), GlobalVariable.G_timeoutLong, flowControl)

		ToAccount
		String secure2U = 'ee'
	}

	/**
	 * Verify status for Secure2u for DuitNow Settings
	 */
	void secure2uforDuitNowSettings(String status, flowControl) {

		//MobileDriver.verifyElementExist(this.commonPage.lblDynamic('Secure2u athorization'), GlobalVariable.G_timeoutLong, flowControl)
		MobileDriver.verifyElementExist(this.commonPage.lblDynamic('Switch DuitNow Account'), GlobalVariable.G_timeoutLong, flowControl)
		//MobileDriver.verifyElementExist(this.commonPage.lblDynamic(ToAccount), GlobalVariable.G_timeoutLong, flowControl)

		'If status is Approve, verify DuitNow account successfully switched'
		'If status is Reject, verify Authorisation failed'
		'If status is Expire, wait for 32 seconds and verify Authorisation failed'
		switch(status) {

			case('Approve') :
				MobileDriver.tap(this.commonPage.lblDynamic('Approve'), GlobalVariable.G_timeoutLong, flowControl)
				MobileDriver.verifyElementExist(this.commonPage.lblDynamic('DuitNow account successfully switched'), GlobalVariable.G_timeoutLong, flowControl)
				MobileDriver.tap(this.commonPage.lblDynamic('Done'), GlobalVariable.G_timeoutLong, flowControl)
				break;

			case('Reject') :
				MobileDriver.tap(this.commonPage.lblDynamic('Reject'), GlobalVariable.G_timeoutLong, flowControl)
				MobileDriver.verifyElementExist(this.commonPage.lblDynamic('Authorisation failed'), GlobalVariable.G_timeoutLong, flowControl)
				MobileDriver.tap(this.commonPage.lblDynamic('Done'), GlobalVariable.G_timeoutLong, flowControl)
				break;

			case('Expire') :
				Mobile.delay(32)
				MobileDriver.verifyElementExist(this.commonPage.lblDynamic('Authorisation failed'), GlobalVariable.G_timeoutLong, flowControl)
				MobileDriver.tap(this.commonPage.lblDynamic('Done'), GlobalVariable.G_timeoutLong, flowControl)
				break;
		}
	}

	/**
	 * Select Secure2u
	 */
	public void tapSecure2u(flowControl) {
		String lblName = UISecure2u
		int timeOut = GlobalVariable.G_timeoutMedium
		'Select Secure2u'
		Mobile.tap(this.lblSecure2u(lblName), timeOut, flowControl)
	}

	/**
	 * Select Disable Secure2u
	 */
	public void disableSecure2U(flowControl) {
		'Verify Disable Secure2u exist'
		verifyLblDisableSecure2uExist()
		'Select Confirm button'
		tapBtnConfirm()
		'Verify Secure2u Disabled exist'
		verifyLblSecure2uDisabledExist()
	}

	/**
	 * Select Disable ATM Cashout
	 */

	public void tapLblAtmCashout(int timeOut = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		Mobile.verifyElementExist(this.commonPage.lblDynamic(UILblAtmCashout), GlobalVariable.G_timeoutMedium, flowControl)
		Mobile.tap(commonPage.lblDynamic(UILblAtmCashout), timeOut, flowControl)
		Mobile.delay(GlobalVariable.G_timeoutMiniShort)
	}

	public void verifyUIAtmCashout(int timeOut = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		'Verify UI Label UnLink Atm Cashout'
		Mobile.verifyElementExist(this.commonPage.lblDynamic(UILblUnLinkAtmCashout), GlobalVariable.G_timeoutMedium, flowControl)
		'Verify UI Cancel'
		Mobile.verifyElementExist(this.commonPage.lblDynamic(UICancel), GlobalVariable.G_timeoutMedium, flowControl)
		'Verify UI Confirm'
		Mobile.verifyElementExist(this.commonPage.lblDynamic(UIConfirm), GlobalVariable.G_timeoutMedium, flowControl)
		'Verify UI Cancel'
		Mobile.tap(commonPage.lblDynamic(UICancel), timeOut, flowControl)
		'Verify UI Label Atm Cashout'
		Mobile.verifyElementExist(this.commonPage.lblDynamic(UILblAtmCashout), GlobalVariable.G_timeoutMedium, flowControl)
		'Select UI Label Atm Cashout'
		Mobile.tap(commonPage.lblDynamic(UILblAtmCashout), timeOut, flowControl)
		'Verify UILbl Note Atm Cashout'
		Mobile.verifyElementExist(this.commonPage.lblDynamic(UILblNoteAtmCashout), GlobalVariable.G_timeoutMedium, flowControl)
		'Verify UI Confirm'
		Mobile.tap(commonPage.lblDynamic(UIConfirm), timeOut, flowControl)
		'Verify UI Label Disable Atm Cashout'
		Mobile.tap(commonPage.lblDynamic(UILblDisableAtmCashout), timeOut, flowControl)
	}


	/**
	 * Verify update profile picture successfully
	 */
	void verifyNameUpdatedSuccessfully(flowControl) {
		'Tap to Name'
		MobileDriver.tap(commonPage.lblDynamic(NAME), GlobalVariable.G_timeoutLong, flowControl)
		'Clear text'
		Mobile.clearText(this.txtSiblingDynamic(NAME), GlobalVariable.G_timeoutShort)
		int randomInteger = MobileDriver.randomInteger(0, 9)
		'Set name but lower case'
		MobileDriver.setText(this.txtSiblingDynamic(NAME), NAME.toLowerCase() + " ", GlobalVariable.G_timeoutLong, flowControl)
		delay(GlobalVariable.G_timeoutMiniShort)
		'Select Save Changes'
		commonPage.tapAtPosition(this.commonPage.lblDynamic(UISaveChanges), GlobalVariable.G_timeoutLong, flowControl)
		MobileDriver.tap(commonPage.lblDynamic(UISaveChanges), GlobalVariable.G_timeoutLong, flowControl)
		'Verify text - Profile successfully updated.'
		MobileDriver.verifyElementVisible(this.textProfileUpdatedSuccessfully(), GlobalVariable.G_timeoutLong, flowControl)
		turnOffSuccessMessage()
	}

	/**
	 * Verify Able To Click Referal Code
	 */
	void verifyAbleToClickReferalCode(int timeOut = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		MobileDriver.tap(commonPage.lblContainDynamic(UIReferralCode), timeOut, flowControl)
		delay(GlobalVariable.G_timeoutMiniShort)
		MobileDriver.tap(commonPage.lblContainDynamic(UIYourReferralCode), timeOut, flowControl)
		verifyElementExist(commonPage.lblNormalizeSpace(referalCodeCopyMessage), timeOut, flowControl)
		commonPage.tapBack()
		delay(GlobalVariable.G_timeoutMiniShort)
	}

	/**
	 * click Default App View Button
	 */
	void clickDefaultAppViewButton(flowControl) {
		Mobile.delay(GlobalVariable.G_timeoutMiniShort)
		MobileDriver.tap(this.commonPage.lblDynamic(UIDefaultAppView), GlobalVariable.G_timeoutLong, flowControl)
	}


	/**
	 * Verify When Click Back
	 */
	void verifyWhenClickBack(int timeOut = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		clickDefaultAppViewButton(flowControl)
		waitForElementPresent(commonPage.lblDynamic(UIDefaultView), timeOut, flowControl)
		commonPage.tapBack()
		verifyProfilesExist(flowControl)
	}

	void changePersonalDetail(List<String> statusTitle, List<String> statusDescription, List<String> toolTip,int timeOut = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		'Tap to Profile'
		clickProfileButton(flowControl)
		delay(GlobalVariable.G_timeoutMiniShort)

		'Verify Profile'
		verifyProfilesExist(flowControl)

		'Tap to Personal Details'
		MobileDriver.tap(commonPage.lblDynamic(UIPersonalDetails), timeOut, flowControl)
		commonPage.waitLoading()

		'Verify Update Profile'
		verifyTextUpdateProfile(flowControl)

		'Verify Update Name'
		verifyNameUpdatedSuccessfully(flowControl)

		'Verify tool tip'
		verifyToolTipMobilePhone(toolTip)

		'Change Profile Picture'
		verifyProfilePictureUpdatedSuccessfully(flowControl)

		'Change Phone Number'
		verifyPhoneNumberUpdatedWithS2USuccessfully(statusTitle, statusDescription,flowControl)

		'Change Email'
		verifyEmailIdUpdatedSuccessfully(flowControl)

		'Change able to click Referal code'
		verifyAbleToClickReferalCode()
	}
	void turnOffSuccessMessage(int timeOut = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		if(waitForElementPresent(this.textProfileUpdatedSuccessfully(), GlobalVariable.G_timeoutLong)) {
			MobileDriver.tap(commonPage.imgNoDynamic("last()"), timeOut, flowControl)
		}
	}
	void verifyAccessWithPin(String pinCode, int timeOut = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		'Back to home'
		commonPage.backToHome()
		'Click logout'
		homePage.tapBtnLogout()
		'Click Back to Dashboard'
		homePage.tapBtnBackToDashboard()
		'Click to setting'
		homePage.tapBtnSetting()
		'Click to Profile'
		clickProfileButton(flowControl)
		'Enter pin'
		commonPage.enterNO(pinCode, false)
		'Verify Profiles Title'
		verifyProfilesExist(flowControl)
	}
	/**
	 * Verify Phone Number updated successfully
	 */
	void verifyPhoneNumberUpdatedWithS2USuccessfully(List<String> statusTitle, List<String> statusDescription, flowControl) {
		'Select Mobile number'
		MobileDriver.tap(this.commonPage.lblDynamic(UIMAEMobileNumber), GlobalVariable.G_timeoutLong, flowControl)
		'Generate random number'
		int randomInteger = MobileDriver.randomInteger(163456714, 163456739)
		'Covnert random number to string'
		String phoneNumber = String.valueOf(randomInteger)
		'Enter phone number'
		MobileDriver.enterPinNo(phoneNumber)
		'Select Tick button'
		MobileDriver.tap(this.btnTick(), GlobalVariable.G_timeoutLong, flowControl)
		commonPage.waitLoading()
		'Select Approve'
		secure2uAuthorisationPage.authorize(secure2uAuthorisationPage.UIApprove)
		if(waitForElementNotPresent(commonPage.lblNormalizeSpace(ProfileUpdatedMessage), GlobalVariable.G_timeoutShort)) {
			'Verify text - Profile successfully updated.'
			statusScreenPage.verifyStatusScreen(statusTitle, statusDescription)
			commonPage.tapDoneBtn()
		}
	}
	void verifyChangeDeFaultAppView(List<String> viewType, int timeOut = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		clickDefaultAppViewButton(flowControl)
		for(int i = 0 ; i < viewType.size(); i++) {
			waitForElementPresent(commonPage.lblDynamic(viewType[i]), timeOut, flowControl)
			MobileDriver.tap(commonPage.lblDynamic(viewType[i]), timeOut, flowControl)
			MobileDriver.tap(this.commonPage.lblDynamic(UISaveChanges), GlobalVariable.G_timeoutLong, flowControl)
			'Verify text successfully updated.'
			MobileDriver.verifyElementExist(commonPage.lblContainDynamic(defaultViewMessage + '  ' + viewType[i] + '.'), GlobalVariable.G_timeoutLong, flowControl)
			if(waitForElementPresent(commonPage.lblContainDynamic(defaultViewMessage + '  ' + viewType[i]+ '.'), GlobalVariable.G_timeoutLong)) {
				MobileDriver.tap(commonPage.imgNoDynamic("last()"), timeOut, flowControl)
			}
		}
	}

	void verifyAllItemInSetting(List<String> listItem,int timeOut = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		for(String item: listItem) {
			Mobile.scrollToText(item)
			MobileDriver.tap(commonPage.lblDynamic(item), timeOut, flowControl)
			delay(GlobalVariable.G_timeoutMiniShort)
			verifyElementExist(commonPage.lblContainDynamic(item), timeOut, flowControl)
			commonPage.tapBack()
		}
	}

	void tapMenu(String tabName, int timeOut = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		Mobile.scrollToText(tabName)
		MobileDriver.tap(commonPage.lblDynamic(tabName), timeOut, flowControl)
		delay(GlobalVariable.G_timeoutMiniShort)
	}
	public void goToSettingsWithPasswordVerification(String tabName, String password, FailureHandling flowControl = defaultFailureHandling) {
		commonPage.backToHome()
		homePage.clickLogout()
		homePage.tapBtnSetting()
		Mobile.scrollToText(tabName)
		MobileDriver.tap(commonPage.lblDynamic(tabName), GlobalVariable.G_timeoutMedium, flowControl)
		commonPage.checkEnterPassword(password)
	}

	public void verifyLinkedAccounts(String username, FailureHandling flowControl = defaultFailureHandling) {
		MobileDriver.tap(commonPage.lblDynamic(UILinkedAccounts), GlobalVariable.G_timeoutMedium, flowControl)

		'Verify M2U Access'
		verifyM2UAccess(flowControl, username)

		'Tap Overseas Accounts -> Link account -> Agree & Submit -> Close'
		verifyOverseasAccount(flowControl)
	}

	private verifyOverseasAccount(FailureHandling flowControl = defaultFailureHandling) {
		MobileDriver.tap(commonPage.lblDynamic(UIOverseasAccounts), GlobalVariable.G_timeoutMedium, flowControl)
		MobileDriver.tap(commonPage.lblDynamic(UILinkaccount), GlobalVariable.G_timeoutMedium, flowControl)
		MobileDriver.tap(commonPage.grpContainsPrecedingSibling(UIlblReadAndAgree), GlobalVariable.G_timeoutMedium, flowControl)
		MobileDriver.tap(commonPage.lblDynamic(UIAgreeAndSubmit), GlobalVariable.G_timeoutMedium, flowControl)
		delay(GlobalVariable.G_timeoutShort)
		commonPage.swipeByW3CAction(5, 700, 5, 750)
		commonPage.swipeByW3CAction(5, 750, 5, 700)
		while(true) {
			'Tap back btn'
			MobileDriver.tap(commonPage.imgNoDynamic(1), GlobalVariable.G_timeoutLong, flowControl)
			if(waitForElementPresent(commonPage.lblDynamic(UIATMCashout), GlobalVariable.G_timeoutShort)) {
				break
			}
		}
	}

	private verifyM2UAccess(FailureHandling flowControl = defaultFailureHandling, String username) {
		MobileDriver.tap(commonPage.lblContainDynamic(UIM2UAccess), GlobalVariable.G_timeoutMedium, flowControl)

		String maskedAccount = maskString(username)
		MobileDriver.verifyElementVisible(commonPage.lblDynamic(maskedAccount), GlobalVariable.G_timeoutMedium, flowControl)

		'Tap Unlink Access from MAE, verify account and click back'

		'Tap 3 dots btn'
		MobileDriver.tap(commonPage.imgNoDynamic(2), GlobalVariable.G_timeoutLong, flowControl)
		MobileDriver.tap(commonPage.lblDynamic(UIUnlinkAccessFromMAE), GlobalVariable.G_timeoutLong, flowControl)
		delay(1)
		'Verify M2U access to unlink'
		MobileDriver.verifyElementVisible(commonPage.lblDynamic(maskedAccount), GlobalVariable.G_timeoutMedium, flowControl)
		'Tap back btn'
		MobileDriver.tap(commonPage.imgNoDynamic(1), GlobalVariable.G_timeoutLong, flowControl)

		'Tap on Delete M2U Access & tap cancel'

		MobileDriver.tap(commonPage.lblDynamic(UIDeleteM2UAccess), GlobalVariable.G_timeoutLong, flowControl)
		MobileDriver.verifyElementVisible(commonPage.lblContainDynamic(UIlblDeleteM2UAccess), GlobalVariable.G_timeoutMedium, flowControl)
		MobileDriver.tap(commonPage.lblDynamic(UICancel), GlobalVariable.G_timeoutLong, flowControl)

		'Tap Link M2U Access and tap back btn'

		MobileDriver.tap(commonPage.lblContainDynamic(UILinkM2UAccess), GlobalVariable.G_timeoutLong, flowControl)
		MobileDriver.tap(commonPage.lblDynamic(UILinkNow), GlobalVariable.G_timeoutLong, flowControl)
		delay(3)
		'Tap back btn'
		MobileDriver.tap(commonPage.imgNoDynamic(1), GlobalVariable.G_timeoutLong, flowControl)
		delay(1)
		'Tap close btn'
		MobileDriver.tap(commonPage.imgNoDynamic(1), GlobalVariable.G_timeoutLong, flowControl)
		delay(1)
		'Tap back btn'
		MobileDriver.tap(commonPage.imgNoDynamic(1), GlobalVariable.G_timeoutLong, flowControl)
	}

	public void verifyATMCashOut(FailureHandling flowControl = defaultFailureHandling) {
		MobileDriver.tap(commonPage.lblDynamic(UIATMCashout), GlobalVariable.G_timeoutMedium, flowControl)
		delay(GlobalVariable.G_timeoutMiniShort)

		MobileDriver.tap(commonPage.grpContainsPrecedingSibling(UIlblATMCashoutConfirm), GlobalVariable.G_timeoutMedium, flowControl)
		MobileDriver.tap(commonPage.lblDynamic(UIConfirm), GlobalVariable.G_timeoutMedium, flowControl)
		MobileDriver.verifyElementVisible(commonPage.lblContainDynamic(UIATMCashoutMsg), GlobalVariable.G_timeoutMedium, flowControl)
		delay(GlobalVariable.G_timeoutMiniShort)

		'Tap back btn'
		MobileDriver.tap(commonPage.imgNoDynamic(1), GlobalVariable.G_timeoutLong, flowControl)
	}

	private String maskString(String input) {
		if (input.length() <= 4) {
			return input
		}
		String firstTwo = input[0..1]
		String lastTwo = input[-2..-1]
		String maskedPart = '*' * (input.length() - 4)
		return firstTwo + maskedPart + lastTwo
	}

	public void verifyRegisterDuitNowFirstTime(String registerAccount, String status='Approve', FailureHandling flowControl = defaultFailureHandling) {
		MobileDriver.tap(commonPage.lblTextMatches(UIMobileNumber), GlobalVariable.G_timeoutMedium)
		if (waitForElementNotPresent(commonPage.lblDynamic(UIDuitNowFirstTimeRegistrationMsg), GlobalVariable.G_timeoutShort)) {
			'Tap back btn'
			MobileDriver.tap(commonPage.imgNoDynamic(1), GlobalVariable.G_timeoutLong, flowControl)
		}
		else {
			MobileDriver.tap(commonPage.lblDynamic(registerAccount), GlobalVariable.G_timeoutMedium)
			MobileDriver.tap(commonPage.lblDynamic(UIContinue), GlobalVariable.G_timeoutMedium)
			MobileDriver.tap(commonPage.lblDynamic(UIAgreeAndConfirm), GlobalVariable.G_timeoutMedium)
			commonPage.waitLoading()
			verifyElementExist(commonPage.lblContainDynamic(successfulMessage), GlobalVariable.G_timeoutMedium, flowControl)
			MobileDriver.tap(commonPage.lblDynamic(UIDone), GlobalVariable.G_timeoutMedium)
		}
	}

	public void verifyDeregisterExistingDuitNow(String status="Approve", FailureHandling flowControl = defaultFailureHandling) {
		MobileDriver.tap(commonPage.lblTextMatches(UIMobileNumber), GlobalVariable.G_timeoutMedium)
		MobileDriver.tap(commonPage.lblDynamic(UIDeregisterDuitNow), GlobalVariable.G_timeoutMedium)
		MobileDriver.tap(commonPage.lblDynamic(UIConfirm), GlobalVariable.G_timeoutMedium)
		secure2uAutorisationPage.authorize(status)
		commonPage.waitLoading()
		verifyElementExist(commonPage.lblContainDynamic(deregisterMessage), GlobalVariable.G_timeoutMedium, flowControl)
		MobileDriver.tap(commonPage.lblDynamic(UIDone), GlobalVariable.G_timeoutMedium)
	}

	public void verifySwitchAccount(String switchAccountName, String status='Reject', FailureHandling flowControl = defaultFailureHandling) {
		MobileDriver.tap(commonPage.lblTextMatches(UIMobileNumber), GlobalVariable.G_timeoutMedium)
		MobileDriver.tap(commonPage.lblDynamic(UISwitchAccount), GlobalVariable.G_timeoutMedium)
		MobileDriver.tap(commonPage.lblDynamic(switchAccountName), GlobalVariable.G_timeoutMedium)
		MobileDriver.tap(commonPage.lblDynamic(UIContinue), GlobalVariable.G_timeoutMedium)
		MobileDriver.tap(commonPage.lblDynamic(UIAgreeAndConfirm), GlobalVariable.G_timeoutMedium)
		commonPage.waitLoading(GlobalVariable.G_timeoutShort)
		secure2uAutorisationPage.authorize(status)
		MobileDriver.tap(commonPage.lblDynamic(UIDone), GlobalVariable.G_timeoutMedium)
	}

	public void verifyDisableTemporarily(String status='Reject', FailureHandling flowControl = defaultFailureHandling) {
		MobileDriver.tap(commonPage.lblTextMatches(UIMobileNumber), GlobalVariable.G_timeoutMedium)
		MobileDriver.tap(commonPage.lblDynamic(UIDisableTemporarily), GlobalVariable.G_timeoutMedium)
		MobileDriver.tap(commonPage.lblDynamic(UIConfirm), GlobalVariable.G_timeoutMedium)
		secure2uAutorisationPage.authorize(status)
		MobileDriver.tap(commonPage.lblDynamic(UIDone), GlobalVariable.G_timeoutMedium)
	}

	public void verifyDuitNowAutoDebit(FailureHandling flowControl = defaultFailureHandling) {
		MobileDriver.tap(commonPage.lblDynamic(UIDuitNowAutoDebit), GlobalVariable.G_timeoutMedium)
		delay(GlobalVariable.G_timeoutShort)
		'Tap back btn'
		MobileDriver.tap(commonPage.imgNoDynamic(1), GlobalVariable.G_timeoutLong, flowControl)
		commonPage.waitLoading()
	}

	public void verifyDuitNowBlockedList(FailureHandling flowControl = defaultFailureHandling) {
		MobileDriver.tap(commonPage.lblDynamic(UIDuitNowBlockedList), GlobalVariable.G_timeoutMedium)
		commonPage.waitLoading(GlobalVariable.G_timeoutShort)
		MobileDriver.tap(commonPage.imgNoDynamic(1), GlobalVariable.G_timeoutLong, flowControl)
	}

	public void tapNotifications(FailureHandling flowControl = defaultFailureHandling) {
		Mobile.scrollToText(UINotifications)
		MobileDriver.tap(commonPage.lblDynamic(UINotifications), GlobalVariable.G_timeoutMedium, flowControl)
	}

	public void verifyPayAndTransferOveralltSetting(FailureHandling flowControl = defaultFailureHandling) {
		'Tap Pay'
		MobileDriver.tap(commonPage.lblDynamic(UIPay), GlobalVariable.G_timeoutMedium, flowControl)
		verifyElementVisible(commonPage.lblDynamic(UIScanNPayLimit), GlobalVariable.G_timeoutMedium, flowControl)

		'Tap Transfer'
		MobileDriver.tap(commonPage.lblDynamic(UITransfer), GlobalVariable.G_timeoutMedium, flowControl)
		verifyElementVisible(commonPage.lblDynamic(UIDuitNowServices), GlobalVariable.G_timeoutMedium, flowControl)

		'Tap Auto Debit for Zakat'
		commonPage.verifyElementVisible(commonPage.lblDynamic(UIAutoDebitForZakat), GlobalVariable.G_timeoutMedium)
	}

	public void verifyAboutSettingPage(List<String> items, FailureHandling flowControl = defaultFailureHandling) {
		commonPage.scrollToEnd()
		'Verify About'
		commonPage.verifyElementVisible(commonPage.lblDynamic(UIAbout), GlobalVariable.G_timeoutMedium, flowControl)
		commonPage.verifyElementVisible(commonPage.lblContainDynamic(UIAboutDescription), GlobalVariable.G_timeoutMedium, flowControl)
		Mobile.takeScreenshot()
		MobileDriver.tap(commonPage.lblDynamic(UIAbout), GlobalVariable.G_timeoutMedium, flowControl)
		commonPage.delay(1)
		Mobile.takeScreenshot()
		for (String item : items) {
			"Tap About item: ${item}"
			waitForElementPresent(commonPage.lblDynamic(item), GlobalVariable.G_timeoutLong, flowControl)
			MobileDriver.tap(commonPage.lblDynamic(item), GlobalVariable.G_timeoutLong, flowControl)
			commonPage.delay(GlobalVariable.G_timeoutMiniShort)

			"Verify sub-page"
			commonPage.delay(GlobalVariable.G_timeoutMiniShort)
			Mobile.takeScreenshot()

			'Tap back to About page'
			commonPage.tapBack()
			commonPage.delay(GlobalVariable.G_timeoutMiniShort)
		}
	}

	public void goToScanAndPayLimitSetting(FailureHandling flowControl = defaultFailureHandling) {
		'Tap Pay'
		MobileDriver.tap(commonPage.lblDynamic(UIPay), GlobalVariable.G_timeoutMedium, flowControl)

		'Tap Scan & Pay Limit'
		MobileDriver.tap(commonPage.lblDynamic(UIScanNPayLimit), GlobalVariable.G_timeoutMedium, flowControl)
	}

	public void goToCardsSettingWithPINVerification(FailureHandling flowControl = defaultFailureHandling) {
		commonPage.backToHome()
		homePage.clickLogout()
		homePage.tapBtnSetting()
		MobileDriver.tap(commonPage.lblDynamic(UICards), GlobalVariable.G_timeoutMedium, flowControl)
		MobileDriver.tap(commonPage.lblDynamic(UIViewCards), GlobalVariable.G_timeoutMedium, flowControl)
		commonPage.checkEnterPIN()
		commonPage.waitLoading(GlobalVariable.G_timeoutShort)
	}

	public void goToSupportSetting(FailureHandling flowControl = defaultFailureHandling) {
		commonPage.scrollToEnd()
		'Verify Support'
		commonPage.verifyElementVisible(commonPage.lblContainDynamic(UISupport), GlobalVariable.G_timeoutMedium, flowControl)
		commonPage.verifyElementVisible(commonPage.lblContainDynamic(UISupportDescription), GlobalVariable.G_timeoutMedium, flowControl)
		Mobile.takeScreenshot()
		MobileDriver.tap(commonPage.lblDynamic(UISupport), GlobalVariable.G_timeoutMedium, flowControl)
	}

	public void goToDuiNowServicesSetting(FailureHandling flowControl = defaultFailureHandling) {
		'Tap Transfer'
		MobileDriver.tap(commonPage.lblDynamic(UITransfer), GlobalVariable.G_timeoutMedium, flowControl)

		'Tap DuitNow Services'
		MobileDriver.tap(commonPage.lblDynamic(UIDuitNowServices), GlobalVariable.G_timeoutMedium, flowControl)
	}
	void verifyToolTipMobilePhone(List<String> textData,int timeOut = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		MobileDriver.tap(commonPage.imgSiblingGroup(UIMAEMobileNumber) , timeOut, flowControl)
		delay(GlobalVariable.G_timeoutMiniShort - 2)
		verifyElements(textData, commonPage.lblNormalizeSpacePath(), timeOut, flowControl)
		commonPage.tapBack()
	}

	/**
	 * Tap Security
	 */
	public void tapLblSecurity( String lblName = UISecurity, int timeOut = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		Mobile.tap(commonPage.lblDynamic(lblName), timeOut, flowControl)
	}
	
	/*
	 * verify UI Settings
	 */
	void verifyUISettings(List verifyUISettingsData, FailureHandling flowControl = FailureHandling.OPTIONAL) {
		'Scroll to Text'
		for (String text : verifyUISettingsData) {
		    MobileDriver.scrollToText(text)
			'Verify UI Settings'
			verifyElementExist(commonPage.lblContainDynamic(text), GlobalVariable.G_timeoutMedium, flowControl)
			Mobile.takeScreenshot()
			}
	}

	void verifySupportPageItems(List<String> items, FailureHandling flowControl = defaultFailureHandling) {
		for (String item : items) {
			"Tap Support item: ${item}"
			waitForElementPresent(commonPage.lblDynamic(item), GlobalVariable.G_timeoutLong, flowControl)
			MobileDriver.tap(commonPage.lblDynamic(item), GlobalVariable.G_timeoutLong, flowControl)
			commonPage.delay(GlobalVariable.G_timeoutMiniShort)

			"Verify sub-page"
			commonPage.delay(GlobalVariable.G_timeoutMiniShort)
			Mobile.takeScreenshot()

			'Tap back to Support page'
			commonPage.tapBack()
			commonPage.delay(GlobalVariable.G_timeoutMiniShort)
		}
	}
}
