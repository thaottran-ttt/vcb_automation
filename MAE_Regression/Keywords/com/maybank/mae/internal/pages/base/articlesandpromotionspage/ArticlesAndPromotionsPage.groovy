package com.maybank.mae.internal.pages.base.articlesandpromotionspage

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.checkpoint.CheckpointFactory
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testcase.TestCaseFactory
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testdata.TestDataFactory
import com.kms.katalon.core.testobject.ObjectRepository
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords
import com.maybank.mae.internal.pages.base.BasePage
import com.maybank.mae.internal.pages.base.CommonPage
import com.maybank.mae.internal.pages.base.ConfirmationPage
import com.maybank.mae.internal.pages.base.HomePage

import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory
import io.appium.java_client.android.AndroidDriver
import io.appium.java_client.android.nativekey.AndroidKey
import io.appium.java_client.android.nativekey.KeyEvent


import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import internal.GlobalVariable

public abstract class ArticlesAndPromotionsPage extends BasePage {
	
	CommonPage commonPage = getPage(CommonPage)
	HomePage homePage = BasePage.getPage(HomePage)

	// Define abstract TestObject
	abstract TestObject lblTitleBySection(String text, int index)
	abstract TestObject lblTitleLatestSection(int index)
	
	abstract TestObject lblArticlesTitleBySection(String text, int index)
	abstract TestObject lblNewsLikeByIndex(int index)
	
	void searchAndAccessPromotions(String searchKeyword, FailureHandling flowControl = defaultFailureHandling) {
		'Click Search icon'
		commonPage.tap(commonPage.imgNoDynamic(2), GlobalVariable.G_timeoutMedium, flowControl)
		
		'Search'
		commonPage.setTextAndDone(commonPage.txtEditNo(1), searchKeyword, GlobalVariable.G_timeoutMedium, flowControl)
	}
	
}