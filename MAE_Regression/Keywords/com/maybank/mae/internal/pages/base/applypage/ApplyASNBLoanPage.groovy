package com.maybank.mae.internal.pages.base.applypage

import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.maybank.common.MobileDriver
import com.maybank.mae.internal.pages.base.BasePage
import com.maybank.mae.internal.pages.base.CommonPage
import com.maybank.mae.internal.pages.base.HomePage
import com.maybank.mae.internal.pages.base.applypage.ApplyPage as ApplyPage

import internal.GlobalVariable

public abstract class ApplyASNBLoanPage extends BasePage{

	public static CommonPage commonPage = BasePage.getPage(CommonPage)
	public static HomePage homePage = BasePage.getPage(HomePage)
	public static ApplyPage applyPage = BasePage.getPage(ApplyPage)

	// Navigate ASNB loan
	String UI_LOAN_FINANCING = "LOAN/FINANCING"
	String UI_ASB_FINANCING_I = "ASB Financing-i"
	String UI_AGREE_CONFIRM = "Agree & Confirm"
	String UI_COUNTINUE_ASB_LOAN = "Continue your"
	String APPLY_FOR_ASB = "Apply for an ASB"
	String UI_APPLY_NOW = "Apply Now"
	String UI_APPLICATION_PENDING = "Application Pending"
	String UI_OKAY = "Okay"
	String UI_WE_RECEIVED_YOUR_DOCUMENTS = "We have received your documents"
	String UI_GOT_IT = "Okay, got it."

	/**
	 * Navigate to ASNB Loan
	 */
	void navigateAsnbLoan(String password, int timeout = GlobalVariable.G_timeoutLong, FailureHandling flowControl = defaultFailureHandling) {
		'Select Apply'
		homePage.tapApply()

		'Select LOAN/FINANCING'
		applyPage.selectTab(UI_LOAN_FINANCING)

		Mobile.delay(GlobalVariable.G_timeoutShort)

		'Verify ASB Financing-i'
		Mobile.verifyElementExist(commonPage.lblStartsWith(UI_ASB_FINANCING_I), timeout)

		Mobile.delay(GlobalVariable.G_timeoutMedium)

		'Select ASB Financing-i'
		MobileDriver.tap(commonPage.lblStartsWith(UI_ASB_FINANCING_I), timeout)

		'Verify Apply for an ASB...'
		Mobile.verifyElementExist(commonPage.lblStartsWith(APPLY_FOR_ASB), timeout)

		Mobile.delay(GlobalVariable.G_timeoutShort)

		commonPage.scrollToEnd()

		'Select Apply Now'
		MobileDriver.tap(commonPage.lblDynamic(UI_APPLY_NOW), timeout)

		commonPage.checkEnterPassword(password, timeout)

		if (MobileDriver.waitForElementPresent(commonPage.lblDynamic(UI_APPLICATION_PENDING), timeout)) {
			'Select Okay'
			MobileDriver.tap(commonPage.lblStartsWith(UI_OKAY), timeout)

			'Select Okay, got it.'
			MobileDriver.tap(commonPage.lblDynamic(UI_GOT_IT), timeout)
		} else {
			//TODO
		}
	}

	/**
	 * Navigate to ASNB Loan
	 */
	void navigateResumeAsnbLoan(String password, int timeout = GlobalVariable.G_timeoutLong, FailureHandling flowControl = defaultFailureHandling) {
		'Select Apply'
		homePage.tapApply()

		'Select LOAN/FINANCING'
		applyPage.selectTab(UI_LOAN_FINANCING)

		Mobile.delay(GlobalVariable.G_timeoutShort)

		commonPage.scrollToEnd()

		'Verify Countinue ASB Loan'
		Mobile.verifyElementExist(commonPage.lblStartsWith(UI_COUNTINUE_ASB_LOAN), timeout)

		'Select Continue your ASB Financing application here'
		MobileDriver.tap(commonPage.lblStartsWith(UI_COUNTINUE_ASB_LOAN), timeout)

		commonPage.checkEnterPassword(password, timeout)

		'Select Okay'
		MobileDriver.tap(commonPage.lblStartsWith(UI_OKAY), timeout)

		'Select Okay, got it.'
		MobileDriver.tap(commonPage.lblDynamic(UI_GOT_IT), timeout)
	}
}