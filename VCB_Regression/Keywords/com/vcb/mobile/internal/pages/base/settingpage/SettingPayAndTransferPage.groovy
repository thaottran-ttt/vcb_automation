package com.vcb.mobile.internal.pages.base.settingpage

import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.vcb.common.MobileDriver
import com.vcb.mobile.internal.pages.base.BasePage
import com.vcb.mobile.internal.pages.base.CommonPage
import com.vcb.mobile.internal.pages.base.HomePage
import com.vcb.mobile.internal.pages.base.LoginPage
import com.vcb.mobile.internal.pages.base.OtpPage
import com.vcb.mobile.internal.pages.base.Secure2uAuthorisationPage
import com.vcb.mobile.internal.pages.base.StatusScreenPage

import internal.GlobalVariable





public abstract class SettingPayAndTransferPage extends BasePage {

	CommonPage commonPage = getPage(CommonPage)
	LoginPage loginPage = getPage(LoginPage)
	OtpPage otpPage = getPage(OtpPage)
	HomePage homePage = BasePage.getPage(HomePage)
	Secure2uAuthorisationPage secure2uAuthorisationPage = getPage(Secure2uAuthorisationPage)
	StatusScreenPage statusScreenPage = getPage(StatusScreenPage)


	String UIAutoDebitForZakat = 'Auto Debit for Zakat'
	String UISwitchAccount = 'Switch account'
	String UISwitchBody = 'Switch body'
	String UIEdit = 'Edit'
	String UIZakatBody = 'Zakat body'
	String UIPlus60 = '+60'
	String UIUpdate = 'Update'
	String UICanceAutolDebit = 'Cancel Auto Debit'
	String UIConfirm = 'Confirm'
	String UISetUpNow = 'Set Up Now'
	String UIPayFrom = 'Pay from'
	

	String switchAccountMessage = 'Debiting bank account for zakat was successfully switched.'
	String editPhoneNumbertMessage = 'Mobile number successfully updated.'
	String cancelMessage = 'cancelled your auto debit for Zakat Simpanan & Pelaburan'
	static final String AGREE_AND_CONFIRM = 'Agree & Confirm'
	static final String SUBMIT = 'Submit'

	void switchAccount(String account, List<String> statusTitle, List<String> statusDes = [], int timeOut = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		tapToAutoDebitForZakat()
		waitForElementPresent(commonPage.lblDynamic(UISwitchAccount), timeOut, flowControl)
		MobileDriver.tap(commonPage.lblDynamic(UISwitchAccount), timeOut, flowControl)
		commonPage.waitLoading()
		MobileDriver.tap(commonPage.lblContainDynamic(account), timeOut, flowControl)
		commonPage.tapContinueBtn()
		commonPage.waitLoading()
		secure2uAuthorisationPage.authorize(secure2uAuthorisationPage.UIApprove)
		statusScreenPage.verifyStatusScreen(statusDes, statusDes)
		commonPage.tapDoneBtn()
		commonPage.waitLoading()
		MobileDriver.verifyElementVisible(commonPage.lblContainDynamic(switchAccountMessage), GlobalVariable.G_timeoutLong, flowControl)
		if(waitForElementPresent(commonPage.lblContainDynamic(switchAccountMessage), GlobalVariable.G_timeoutLong)) {
			MobileDriver.tap(commonPage.imgNoDynamic("last()"), timeOut, flowControl)
		}
		delay(GlobalVariable.G_timeoutMiniShort)
	}
	void tapToAutoDebitForZakat(int timeOut = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		waitForElementPresent(commonPage.lblDynamic(UIAutoDebitForZakat), timeOut, flowControl)
		MobileDriver.tap(commonPage.lblDynamic(UIAutoDebitForZakat), timeOut, flowControl)
		commonPage.waitLoading()
	}
	void switchBody(String account, List<String> statusTitle, List<String> statusDes = [], int timeOut = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		waitForElementPresent(commonPage.lblDynamic(UISwitchBody), timeOut, flowControl)
		MobileDriver.tap(commonPage.lblDynamic(UISwitchBody), timeOut, flowControl)
		commonPage.waitLoading()
		commonPage.selectDropdownSiblingGroupItem(UIZakatBody, account, timeOut)
		commonPage.tapContinueBtn()
		commonPage.waitLoading()
		secure2uAuthorisationPage.authorize(secure2uAuthorisationPage.UIApprove)
		statusScreenPage.verifyStatusScreen(statusDes, statusDes)
		commonPage.tapDoneBtn()
		delay(GlobalVariable.G_timeoutMiniShort)
	}
	
	void editPhoneNumber(List<String> statusTitle, List<String> statusDescription = [], int timeOut = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		tapToAutoDebitForZakat()
		MobileDriver.tap(commonPage.lblDynamic(UIEdit), timeOut, flowControl)
		MobileDriver.tap(commonPage.lblDynamic(UIPlus60), timeOut, flowControl)
		'Generate random number'
		int randomInteger = MobileDriver.randomInteger(163456714, 163456739)
		'Covnert random number to string'
		String phoneNumber = String.valueOf(randomInteger)
		'Enter phone number'
		commonPage.enterNO(phoneNumber)
		'Tap update'
		MobileDriver.tap(commonPage.lblDynamic(UIUpdate), timeOut, flowControl)
		commonPage.waitLoading()
		'Select Approve'
		secure2uAuthorisationPage.authorize(secure2uAuthorisationPage.UIApprove)
		'Verify successfully updated.'
		statusScreenPage.verifyStatusScreen(statusTitle, statusDescription)
		commonPage.tapDoneBtn()
		commonPage.waitLoading()
		MobileDriver.verifyElementVisible(commonPage.lblContainDynamic(editPhoneNumbertMessage), GlobalVariable.G_timeoutLong, flowControl)
		if(waitForElementPresent(commonPage.lblContainDynamic(editPhoneNumbertMessage), GlobalVariable.G_timeoutLong)) {
			MobileDriver.tap(commonPage.imgNoDynamic("last()"), timeOut, flowControl)
		}
		delay(GlobalVariable.G_timeoutMiniShort)
	}
	void cancelAutoDebit(int timeOut = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		MobileDriver.tap(commonPage.lblDynamic(UICanceAutolDebit), timeOut, flowControl)
		waitForElementPresent(commonPage.lblDynamic(UIConfirm), timeOut, flowControl)
		MobileDriver.tap(commonPage.lblDynamic(UIConfirm), timeOut, flowControl)
		commonPage.waitLoading()
		secure2uAuthorisationPage.authorize(secure2uAuthorisationPage.UIApprove)	
		MobileDriver.verifyElementVisible(commonPage.lblContainDynamic(cancelMessage), GlobalVariable.G_timeoutLong, flowControl)
		if(waitForElementPresent(commonPage.lblContainDynamic(cancelMessage), GlobalVariable.G_timeoutLong)) {
			MobileDriver.tap(commonPage.imgNoDynamic("last()"), timeOut, flowControl)
		}
		MobileDriver.verifyElementVisible(commonPage.lblContainDynamic(UISetUpNow), GlobalVariable.G_timeoutLong, flowControl)
	}
	void setupAutoDebit(String agreeRadio,int timeOut = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		tapToAutoDebitForZakat()
		MobileDriver.tap(commonPage.lblDynamic(UISetUpNow), timeOut, flowControl)
		delay(GlobalVariable.G_timeoutMiniShort)
		MobileDriver.tap(commonPage.lblDynamic(UISetUpNow), timeOut, flowControl)
		commonPage.waitLoading()
		commonPage.selectDropdownSiblingGroupItemWithDefaultValue(UIPayFrom)
		commonPage.selectDropdownSiblingGroupItemWithDefaultValue(UIZakatBody)
		commonPage.tapNextBtn()
		delay(GlobalVariable.G_timeoutMiniShort)
		commonPage.swipeByW3CAction(5, 1500, 5, 100)
		delay(GlobalVariable.G_timeoutMiniShort)
		MobileDriver.tap(commonPage.grpPrecedingSibling(agreeRadio), GlobalVariable.G_timeoutMedium, flowControl)
		MobileDriver.tap(commonPage.lblDynamic(AGREE_AND_CONFIRM), GlobalVariable.G_timeoutMedium, flowControl)
		MobileDriver.tap(commonPage.lblDynamic(SUBMIT), GlobalVariable.G_timeoutMedium, flowControl)
		commonPage.waitLoading()
		secure2uAuthorisationPage.authorize(secure2uAuthorisationPage.UIApprove)
		turnOffNotification()
		delay(GlobalVariable.G_timeoutMiniShort)
		commonPage.tapDoneBtn()
		homePage.tapBtnSetting()
	}
	void turnOffNotification() {
		Mobile.openNotifications()
		Mobile.pressBack()
	}
}
