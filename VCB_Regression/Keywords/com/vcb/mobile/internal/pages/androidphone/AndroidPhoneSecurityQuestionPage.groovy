package com.vcb.mobile.internal.pages.androidphone

import com.kms.katalon.core.testobject.TestObject
import com.vcb.mobile.internal.pages.base.SecurityQuestionPage
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

public class AndroidPhoneSecurityQuestionPage extends SecurityQuestionPage {

	@Override
    TestObject inputAnswer() {
        return findTestObject('Object Repository/Mobile/VCB/Android/SecurityQuestion/txtSecurityAnswer')
    }

    @Override
    TestObject buttonConfirm() {
        return findTestObject('Object Repository/Mobile/VCB/Android/SecurityQuestion/btnSubmit')
    }
}