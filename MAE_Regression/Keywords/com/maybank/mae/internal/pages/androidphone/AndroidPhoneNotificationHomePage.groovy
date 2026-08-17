package com.maybank.mae.internal.pages.androidphone

import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.testobject.TestObject
import com.maybank.mae.internal.pages.base.CommonPage
import com.maybank.mae.internal.pages.base.NotificationHomePage

public class AndroidPhoneNotificationHomePage extends NotificationHomePage{

	CommonPage commonPage = CommonPage.getPage(CommonPage)

	@Override
	public TestObject fraGroupItem() {
		return findTestObject('Object Repository/Mobile/MAE/Android/Notification Home Screen/fraGroupItem');
	}
}
