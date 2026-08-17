package com.maybank.mae.internal.pages.androidphone.applypage

import com.maybank.mae.internal.pages.base.applypage.ApplyPersonalLoanPage
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.testobject.TestObject

public class AndroidPhoneApplyPersonalLoanPage extends ApplyPersonalLoanPage{
	@Override
	public TestObject otpPrecedingSiblingImageView(String startWithText) {
		return findTestObject('Object Repository/Mobile/MAE/Android/Apply Personal Loan Screen/optPrecedingSiblingImageView', ['startWithText': startWithText]);
	}

	@Override
	public TestObject otpPrecedingSiblingViewGroup(String startWithText) {
		return findTestObject('Object Repository/Mobile/MAE/Android/Apply Personal Loan Screen/optPrecedingSiblingViewGroup', ['startWithText': startWithText]);
	}
}
