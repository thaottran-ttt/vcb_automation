package com.maybank.mae.internal.pages.androidphone

import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.testobject.TestObject
import com.maybank.mae.internal.pages.base.NotificationSettingPage



public class AndroidPhoneNotificationSettingPage extends NotificationSettingPage{
	@Override
	public TestObject togglePromotions() {
		return findTestObject('Object Repository/Mobile/MAE/Android/Notification Setting Screen/togglePromotions');
	}

	@Override
	public TestObject toggleMoneyReceived() {
		return findTestObject('Object Repository/Mobile/MAE/Android/Notification Setting Screen/toggleMoneyReceived');
	}

	@Override
	public TestObject toggleTapTrackWinNotifications() {
		return findTestObject('Object Repository/Mobile/MAE/Android/Notification Setting Screen/toggleTapTrackWinNotifications');
	}

	@Override
	public TestObject lblDynamicText(String text) {
		return findTestObject('Object Repository/Mobile/MAE/Dynamic/lblDynamic', [('text') : text]);
	}
}
