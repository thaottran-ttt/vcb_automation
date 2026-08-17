package com.maybank.mae.internal.pages.androidphone.settingpage

import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.testobject.TestObject
import com.maybank.mae.internal.pages.base.Secure2uPage
import com.maybank.mae.internal.pages.base.settingpage.SettingPage

public class AndroidPhoneSettingPage extends SettingPage{
	@Override
	public TestObject lblMaybank2u(String lblName) {
		return findTestObject('Object Repository/Mobile/MAE/Dynamic/lblDynamic', [('text') : lblName]);
	}

	@Override
	public TestObject lblSecure2u(String lblName) {
		return findTestObject('Object Repository/Mobile/MAE/Dynamic/lblDynamic', [('text') : lblName]);
	}

	@Override
	public TestObject lblDisableSecure2u(String lblName) {
		return findTestObject('Object Repository/Mobile/MAE/Dynamic/lblDynamic', [('text') : lblName]);
	}

	@Override
	public TestObject btnCancel(String lblName) {
		return findTestObject('Object Repository/Mobile/MAE/Dynamic/lblDynamic', [('text') : lblName]);
	}

	@Override
	public TestObject btnConfirm(String lblName) {
		return findTestObject('Object Repository/Mobile/MAE/Dynamic/lblDynamic', [('text') : lblName]);
	}

	@Override
	public TestObject btnBack() {
		return findTestObject('Object Repository/Mobile/MAE/Android/Setting Screen/btnBack');
	}

	@Override
	public TestObject lblSecure2uDisabled(String lblName) {
		return findTestObject('Object Repository/Mobile/MAE/Dynamic/lblAnyText', [('Verify') : lblName]);
	}

	@Override
	public TestObject lblNotifications(String lblName) {
		return findTestObject('Object Repository/Mobile/MAE/Dynamic/lblDynamic', [('text') : lblName]);
	}

	@Override
	public TestObject btnCameraClick() {
		return findTestObject('Object Repository/Mobile/MAE/Android/Setting Screen/btnCameraClick');
	}

	@Override
	public TestObject btnCameraTick() {
		return findTestObject('Object Repository/Mobile/MAE/Android/Setting Screen/btnCameraTick');
	}

	@Override
	public TestObject imgCameraIcon() {
		return findTestObject('Object Repository/Mobile/MAE/Android/Setting Screen/imgCameraIcon');
	}

	@Override
	public TestObject textProfileUpdatedSuccessfully() {
		return findTestObject('Object Repository/Mobile/MAE/Android/Setting Screen/txtProfileUpdatedSuccessfully');
	}

	@Override
	public TestObject btnTick() {
		return findTestObject('Object Repository/Mobile/MAE/Android/Setting Screen/btnTick');
	}

	@Override
	public TestObject inputPhoneNumber() {
		return findTestObject('Object Repository/Mobile/MAE/Android/Setting Screen/inputPhoneNumber');
	}

	@Override
	public TestObject inputEmailId() {
		return findTestObject('Object Repository/Mobile/MAE/Android/Setting Screen/inputEmailId');
	}

	@Override
	public TestObject txtCurrentDuitNowAccount() {
		return findTestObject('Object Repository/Mobile/MAE/Android/Setting Screen/txtCurrentDuitNowAccount');
	}
	@Override
	public TestObject txtSiblingDynamic(String fieldName) {
		return findTestObject('Object Repository/Mobile/MAE/Android/Setting Screen/txtSiblingDynamic',[('text') : fieldName])
	}
}
