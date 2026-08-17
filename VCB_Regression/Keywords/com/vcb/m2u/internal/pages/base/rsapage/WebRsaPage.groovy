package com.vcb.m2u.internal.pages.base.rsapage

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
import com.vcb.common.WebUIKeyword
import com.vcb.m2u.internal.pages.base.WebCommonPage
import com.vcb.m2u.internal.pages.base.WebBasePage

import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.util.KeywordUtil
import org.openqa.selenium.Keys

import internal.GlobalVariable

public abstract class WebRsaPage extends WebBasePage{

	abstract TestObject txtSecurityAnswer()
	abstract TestObject btnConfirm()
	abstract TestObject lblStatus()
	WebCommonPage webCommonPage = getPage(WebCommonPage)
	static final String REQUEST = 'REQUEST'

	void inputAnswer (String answer, FailureHandling flowControl = defaultFailureHandling) {
		WebUIKeyword.takeScreenshot()
		'input answer'
		WebUI.setText(this.txtSecurityAnswer(), answer, flowControl)
	}

	void confirmAnswer(){
		'click on Submit button'
		WebUI.click(this.btnConfirm())
	}

	void verifyAnswerSuccessfully(){
		'Verify the transaction history in M2U after successful RSA CQ'
		WebUI.delay(GlobalVariable.G_timeoutShort)
		WebUI.verifyElementVisible(lblStatus(),  defaultFailureHandling)
	}

	void verifyAnswerBlocked(){
		'Verify account blocked when input invalid CQ 3 times'
	}

	void clickRequestButton(){
		'Click Request Button'
		WebUIKeyword.click(webCommonPage.btnNormalizeSpaceDynamic(REQUEST));
		WebUIKeyword.delay(GlobalVariable.G_timeoutMiniShort);
		webCommonPage.waitLoading();
	}
}
