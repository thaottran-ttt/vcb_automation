package com.vcb.mobile.internal.pages.androidphone

import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.testobject.TestObject
import com.vcb.mobile.internal.pages.base.CommonPage
import com.vcb.mobile.internal.pages.base.NotificationHomePage

public class AndroidPhoneNotificationHomePage extends NotificationHomePage{

	CommonPage commonPage = CommonPage.getPage(CommonPage)

	@Override
	public TestObject fraGroupItem() {
		return findTestObject('Object Repository/Mobile/VCB/Android/Notification Home Screen/fraGroupItem');
	}
}
