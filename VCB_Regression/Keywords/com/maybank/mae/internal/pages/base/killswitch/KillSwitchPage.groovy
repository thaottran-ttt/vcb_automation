package com.maybank.mae.internal.pages.base.killswitch

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

import internal.GlobalVariable

public abstract class KillSwitchPage extends BasePage {

	static final String DEACTIVATE_ACCOUNTS = 'Deactivate Accounts'
	static final String UILblQuicklyDeactivate = 'Quickly deactivate your accounts in case of fraud'
	static final String NEXT = 'NEXT'
	static final String DONE = 'Done'
	static final String UILblConfirm = 'Confirm'
	static final String UILblCancel = 'Cancel'
	static final String UILblDeactivateTheSelectedAccount = 'Are you sure you want to deactivate the selected account(s)? '
	static final String UILblOurCustomerCareHotline = 'You will need to call our Customer Care hotline or visit any nearby branch to reactivate the account(s).'
	String UIErrorMessage = "No active current account to deactivate."
	CommonPage commonPage = getPage(CommonPage)

	abstract TestObject drpAccountType()

	void deactivateAccounts(String accountType, String accountName, FailureHandling flowControl = defaultFailureHandling) {
		'Select Deactivate Accounts'
		MobileDriver.tap(commonPage.lblDynamic(DEACTIVATE_ACCOUNTS), GlobalVariable.G_timeoutLong, flowControl)
		MobileDriver.verifyElementVisible(commonPage.lblDynamic(UILblQuicklyDeactivate), GlobalVariable.G_timeoutLong)
		MobileDriver.takeScreenshot()
		'Select Next Button'
		MobileDriver.tap(commonPage.lblDynamic(NEXT), GlobalVariable.G_timeoutLong, flowControl)
		'Select Account Type'
		MobileDriver.tap(drpAccountType(), GlobalVariable.G_timeoutLong, flowControl)
		Mobile.delay(GlobalVariable.G_timeoutMiniShort)
		'Select Account Type'
		MobileDriver.tap(commonPage.lblDynamic(accountType), GlobalVariable.G_timeoutLong, flowControl)
		'Select Done Button'
		MobileDriver.tap(commonPage.lblDynamic(DONE), GlobalVariable.G_timeoutLong, flowControl)
		if(MobileDriver.waitForElementPresent(commonPage.lblDynamic(UIErrorMessage), GlobalVariable.G_timeoutMedium)) {
			Mobile.comment(UIErrorMessage)
		}else {
			if(MobileDriver.waitForElementPresent(commonPage.lblDynamic(accountName), GlobalVariable.G_timeoutLong)) {
				MobileDriver.tap(commonPage.lblDynamic(accountName), GlobalVariable.G_timeoutLong, flowControl)
				'Select Next Button'
				MobileDriver.tap(commonPage.lblDynamic(NEXT), GlobalVariable.G_timeoutLong, flowControl)
			}
			'Verify UI Label Deactivate The Selected Account'
			MobileDriver.verifyElementVisible(commonPage.lblDynamic(UILblDeactivateTheSelectedAccount), GlobalVariable.G_timeoutLong)
			MobileDriver.takeScreenshot()
			'Verify UI Label Our Customer Care Hotline'
			MobileDriver.verifyElementVisible(commonPage.lblDynamic(UILblOurCustomerCareHotline), GlobalVariable.G_timeoutLong)
			MobileDriver.takeScreenshot()
			'Select Cancel Button'
			MobileDriver.tap(commonPage.lblDynamic(UILblCancel), GlobalVariable.G_timeoutLong, flowControl)
		}
	}
}
