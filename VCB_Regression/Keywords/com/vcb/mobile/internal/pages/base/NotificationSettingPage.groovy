package com.vcb.mobile.internal.pages.base

import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testobject.TestObject
import com.vcb.common.MobileDriver

import internal.GlobalVariable

public abstract class NotificationSettingPage extends BasePage   {
	abstract TestObject togglePromotions()
	abstract TestObject toggleMoneyReceived()
	abstract TestObject toggleTapTrackWinNotifications()
	abstract TestObject lblDynamicText(String text)

	String UIPromotionsToastMessageON = 'Notification preferences successfully saved.'
	String UIPromotionsToastMessageOFF = 'Notifications successfully disabled.'
	String UIMoneyReceivedON = 'Money is in! This is our special tone to let you know you have received money.'
	String UIMoneyReceivedOFF = 'Custom alert disabled.'
	String UITapTrackWinNotificationsON = 'Notification enabled.'
	String UITapTrackWinNotificationsOFF = 'Notification disabled.'


	public void tapTogglePromotions(int timeOut = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		'Select Toggle Promotions'
		MobileDriver.tap(this.togglePromotions(), timeOut, flowControl)
	}

	public void tapToggleMoneyReceived(int timeOut = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		'Select Toggle Received'
		MobileDriver.tap(this.toggleMoneyReceived(), timeOut, flowControl)
	}

	public void tapToggleTapTrackWinNotifications(int timeOut = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		'Select Toggle Tap Track Win Notifications'
		MobileDriver.tap(this.toggleTapTrackWinNotifications(), timeOut, flowControl)
	}

	public boolean verifyPromotionsToastMessageOFF( String text = UIPromotionsToastMessageOFF, int timeOut = GlobalVariable.G_timeoutShort, FailureHandling flowControl = defaultFailureHandling) {
		'Verify Promotions Toast Message OFF'
		return MobileDriver.verifyElementExist(this.lblDynamicText(text), 3)
	}

	public boolean verifyPromotionsToastMessageON( String text = UIPromotionsToastMessageON, int timeOut = GlobalVariable.G_timeoutShort, FailureHandling flowControl = defaultFailureHandling) {
		'Verify Promotions Toast Message On'
		return MobileDriver.verifyElementExist(this.lblDynamicText(text), timeOut, flowControl)
	}

	public boolean verifyMoneyReceivedToastMessageON( String text = UIMoneyReceivedON, int timeOut = GlobalVariable.G_timeoutShort, FailureHandling flowControl = defaultFailureHandling) {
		'Verify Money Received Toast Message On'
		return MobileDriver.verifyElementExist(this.lblDynamicText(text), timeOut, flowControl)
	}

	public boolean verifyMoneyReceivedToastMessageOFF( String text = UIMoneyReceivedOFF, int timeOut = GlobalVariable.G_timeoutShort, FailureHandling flowControl = defaultFailureHandling) {
		'Verify Money Received Toast Message Off'
		return MobileDriver.verifyElementExist(this.lblDynamicText(text), timeOut, flowControl)
	}

	public boolean veriyTapTrackWinNotificationsToastMessageON( String text = UITapTrackWinNotificationsON, int timeOut = GlobalVariable.G_timeoutShort, FailureHandling flowControl = defaultFailureHandling) {
		'Veriy Tap Track Win Notifications Toast Message On'
		return MobileDriver.verifyElementExist(this.lblDynamicText(text), timeOut, flowControl)
	}

	public boolean verifyTapTrackWinNotificationsToastMessageOFF( String text = UITapTrackWinNotificationsOFF, int timeOut = GlobalVariable.G_timeoutShort, FailureHandling flowControl = defaultFailureHandling) {
		'Verify Tap Track Win Notifications Toast Message Off'
		return MobileDriver.verifyElementExist(this.lblDynamicText(text), timeOut, flowControl)
	}
}
