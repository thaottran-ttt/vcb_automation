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
import com.vcb.assertions.BaseAssert
import com.vcb.common.MobileDriver
import com.vcb.mobile.internal.pages.base.CommonPage.TestObjectTypeEnum

import internal.GlobalVariable

public abstract class PushNotificationPage extends BasePage {

	CommonPage commonPage = getPage(CommonPage)
	abstract TestObject lblPushNotificationDescription(String text)
	abstract TestObject lblPushNotificationDesc(String text)
	String VCB2U_SECURE2U = 'Vcb2u: Secure2u'

	private boolean verifyDeviceNotification(String title, String startMessage, String message, int timeOut = GlobalVariable.G_timeoutShort, FailureHandling flowControl = defaultFailureHandling) {
		Mobile.openNotifications()
		try {
			Mobile.delay(1)
			MobileDriver.takeScreenshot()
			'Verify notification title'
			MobileDriver.verifyElementExist(commonPage.lblDynamic(title), GlobalVariable.G_timeoutLong)
			'Get notification message'
			String bodyPN = MobileDriver.getText(commonPage.lblStartsWith(startMessage), GlobalVariable.G_timeoutLong)
			Mobile.comment(bodyPN)
			'Verify notification message'
			Mobile.verifyMatch(bodyPN, message, true)
		}catch(ex) {
			Mobile.pressBack()
			handleError(ex, flowControl)
		}
		Mobile.pressBack()
	}

	boolean verifyTransferNotificationDuitNow(String product, String accountNumber, String refId, double amount, FailureHandling flowControl = defaultFailureHandling) {
		'Init Data'
		String acc4Digit = accountNumber.substring(accountNumber.length() - 4)
		String formattedAmount = String.format("%.2f", amount)
		String title = "Vcb2u: Transfer"
		String startMessage = "DuitNow of RM"
		'Replace Text'
		if(product.contains('/')) {
			product = product.replaceAll("/", " / ")
		}
		'Assign Data'
		String messagePN ="DuitNow of RM ${formattedAmount} to (.+) REF: ${refId}"
		'Verify Device Notification'
		this.verifyDeviceNotification(title, startMessage, messagePN)
		return
	}

	void verifyNotificationManageFD(FailureHandling flowControl = defaultFailureHandling) {
		'Init Data'
		String title = "Vcb2u: Fixed Deposits"
		String startMessage = "Your Islamic FD"
		String messagePN ="Your Islamic FD placement has been successfully updated."

		'Verify Device Notification'
		this.verifyDeviceNotification(title, startMessage, messagePN, GlobalVariable.G_timeoutLong, flowControl)
	}

	void verifyPushNotification(String titlePN, String messagePN, FailureHandling flowControl = defaultFailureHandling, TestObjectTypeEnum testObjectType = TestObjectTypeEnum.TEXT, boolean isG1A = false) {
		'Open Notifications'
		Mobile.openNotifications()
		try {
			Mobile.delay(GlobalVariable.G_timeoutMiniShort)
			MobileDriver.takeScreenshot()
			'Verify notification title'
			'Get notification message'
			String bodyPN = MobileDriver.getText(this.lblPushNotificationDescription(titlePN), GlobalVariable.G_timeoutLong)
			Mobile.comment(bodyPN)
			info("messagePN: ${messagePN}")
			info("bodyPN: ${bodyPN}")
			switch(testObjectType) {
				case TestObjectTypeEnum.TEXT:
					'Verify notification message'
					Mobile.verifyMatch(bodyPN, messagePN, false)
					break
				case TestObjectTypeEnum.CONTAINS:
					if (isG1A) {
						BaseAssert.equals(bodyPN.contains(messagePN), true, "successfully set up a future order to convert")
					}else {
						BaseAssert.equals(bodyPN.contains(messagePN), true, "Verify Notification")
					}

					break
			}
		}catch(ex) {
			Mobile.pressBack()
			handleError(ex, flowControl)
			return
		}
		Mobile.pressBack()
	}

	void verifyPushNotificationSamsung24(String titlePN, String messagePN, FailureHandling flowControl = defaultFailureHandling, TestObjectTypeEnum testObjectType = TestObjectTypeEnum.TEXT, boolean isG1A = false) {
		'Open Notifications'
		Mobile.openNotifications()
		try {
			Mobile.delay(GlobalVariable.G_timeoutMiniShort)
			MobileDriver.takeScreenshot()
			'Verify notification title'
			'Get notification message'
			String bodyPN = MobileDriver.getText(this.lblPushNotificationDesc(titlePN), GlobalVariable.G_timeoutLong)
			Mobile.comment(bodyPN)
			info("messagePN: ${messagePN}")
			info("bodyPN: ${bodyPN}")
			switch(testObjectType) {
				case TestObjectTypeEnum.TEXT:
					'Verify notification message'
					Mobile.verifyMatch(bodyPN, messagePN, false)
					break
				case TestObjectTypeEnum.CONTAINS:
					if (isG1A) {
						BaseAssert.equals(bodyPN.contains(messagePN), true, "successfully set up a future order to convert")
					}else {
						BaseAssert.equals(bodyPN.contains(messagePN), true, "Verify Notification")
					}

					break
			}
		}catch(ex) {
			Mobile.pressBack()
			handleError(ex, flowControl)
			return
		}
		Mobile.pressBack()
	}

	void verifyPushNotificationMatchInList(List<String> messagePN, FailureHandling flowControl = defaultFailureHandling) {
		'Open Notifications'
		Mobile.openNotifications()
		try {
			Mobile.delay(GlobalVariable.G_timeoutMiniShort)
			MobileDriver.takeScreenshot()
			'Verify notification message'
			commonPage.verifySomeElementsMatchInList(messagePN, commonPage.lblDynamicPath(), GlobalVariable.G_timeoutLong)
		}catch(ex) {
			Mobile.pressBack()
			handleError(ex, flowControl)
			return
		}
		Mobile.pressBack()
	}

	void verifyPushNotificationScanPaySetting(String firstTitle, String secondTitle, String messagePN, FailureHandling flowControl = defaultFailureHandling) {
		'Open Notifications'
		Mobile.openNotifications()
		try {
			Mobile.delay(GlobalVariable.G_timeoutMiniShort)
			MobileDriver.takeScreenshot()

			'Verify notification title'
			String notificationTitle = firstTitle
			if (waitForElementNotPresent(commonPage.lblDynamic(firstTitle), GlobalVariable.G_timeoutShort)) {
				MobileDriver.verifyElementExist(commonPage.lblDynamic(secondTitle), GlobalVariable.G_timeoutLong)
				notificationTitle = secondTitle
			}

			'Get notification message'
			String bodyPN = MobileDriver.getText(this.lblPushNotificationDescription(notificationTitle), GlobalVariable.G_timeoutLong)
			info("messagePN: ${messagePN}")
			info("bodyPN: ${bodyPN}")
			BaseAssert.equals(bodyPN.contains(messagePN), true, "Verify Notification")
		}catch(ex) {
			Mobile.pressBack()
			handleError(ex, flowControl)
			return
		}
		Mobile.pressBack()
	}
	void TapAndVerifyPushNotification(String titlePN, String messagePN, FailureHandling flowControl = defaultFailureHandling, TestObjectTypeEnum testObjectType = TestObjectTypeEnum.TEXT) {
		'Open Notifications'
		Mobile.openNotifications()
		try {
			Mobile.delay(1)
			MobileDriver.takeScreenshot()
			MobileDriver.tap(this.lblPushNotificationDescription(titlePN), GlobalVariable.G_timeoutLong)
			String bodyPN = MobileDriver.getText(this.lblPushNotificationDescription(titlePN), GlobalVariable.G_timeoutLong)
			info("messagePN: ${messagePN}")
			info("bodyPN: ${bodyPN}")
			switch(testObjectType) {
				case TestObjectTypeEnum.TEXT:
					'Verify notification message'
					Mobile.verifyMatch(bodyPN, messagePN, false)
					break
				case TestObjectTypeEnum.CONTAINS:
					BaseAssert.equals(bodyPN.contains(messagePN), true, "Verify Notification")
					break
			}
		}catch(ex) {
			Mobile.pressBack()
			handleError(ex, flowControl)
			return
		}
		commonPage.tapBack()
	}
	void turnOffNotifcationIfDisplayed(FailureHandling flowControl = defaultFailureHandling) {
		if(MobileDriver.waitForElementPresent(commonPage.lblContainDynamic(VCB2U_SECURE2U), GlobalVariable.G_timeoutShort, FailureHandling.OPTIONAL)) {
			Mobile.openNotifications()
			Mobile.pressBack()
		}
	}
}
