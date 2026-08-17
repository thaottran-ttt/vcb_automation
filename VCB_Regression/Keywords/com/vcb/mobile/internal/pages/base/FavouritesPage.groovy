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
import com.vcb.common.MobileDriver

import internal.GlobalVariable

public abstract class FavouritesPage extends BasePage{

	CommonPage commonPage = getPage(CommonPage)

	abstract TestObject lblSpecialAddFav()
	abstract TestObject lblMessage()

	public static final String ADD_TO_FAVOURITES = 'Add to Favourites'
	public static final String DONE = 'Done'
	public static final String MSG_EXIST_ADD_FAVOURITE =  'Record already exists.'
	public static final String MSG_SUCCESS_ADD_FAVOURITE= 'Successful'
	public static final String SOMETHING_IS_WRONG = 'Something is wrong'
	public static final String SPECIAL_TEXT = '!@#$%^&*12'

	/**
	 * Add to favourites with favourite name
	 */
	void addToFavourites(String favName, FailureHandling flowControl = defaultFailureHandling) {
		'Scroll to Add to Favourites'
		MobileDriver.tap(commonPage.lblDynamic(ADD_TO_FAVOURITES), GlobalVariable.G_timeoutLong, flowControl)
		Mobile.delay(2)

		'Set favourite name !@#$%^&*12'
		MobileDriver.setText(commonPage.txtEditNo(1), SPECIAL_TEXT, GlobalVariable.G_timeoutLong, flowControl)
		MobileDriver.takeScreenshot()

		'Select Add to Favourites'
		MobileDriver.tap(commonPage.lblDynamic(ADD_TO_FAVOURITES), GlobalVariable.G_timeoutLong, flowControl)

		'Verify error message is displayed'
		MobileDriver.verifyElementVisible(this.lblSpecialAddFav(), GlobalVariable.G_timeoutLong, flowControl)

		MobileDriver.takeScreenshot()
		'Select error message'
		MobileDriver.tap(this.lblSpecialAddFav(), GlobalVariable.G_timeoutLong, flowControl)
		'Set favourite favName'
		MobileDriver.setText(commonPage.txtEditNo(1), favName, GlobalVariable.G_timeoutLong, flowControl)
		MobileDriver.takeScreenshot()
		'Select Add to Favourites'
		MobileDriver.tap(commonPage.lblDynamic(ADD_TO_FAVOURITES), GlobalVariable.G_timeoutLong, flowControl)
		MobileDriver.delay(GlobalVariable.G_timeoutMiniShort)
		MobileDriver.takeScreenshot()
		'Verify toast message is displayed'
		String msg = MobileDriver.getText(this.lblMessage(), GlobalVariable.G_timeoutLong, flowControl)
		MobileDriver.takeScreenshot()
		if(msg != null) {
			if (msg == MSG_EXIST_ADD_FAVOURITE) {
				'Verify Record already exists is displayed'
				Mobile.verifyEqual(msg, MSG_EXIST_ADD_FAVOURITE)
			}else if(msg == MSG_SUCCESS_ADD_FAVOURITE) {
				'Verify Successful message is displayed'
				Mobile.verifyEqual(msg, MSG_SUCCESS_ADD_FAVOURITE)
			}else if(msg == SOMETHING_IS_WRONG){
				'Verify Favourites is exist'
				Mobile.verifyEqual(msg, SOMETHING_IS_WRONG)
				'Back to Status Screen'
				commonPage.tapBack()
			}else {
				info(msg)
			}
		}
		MobileDriver.delay(GlobalVariable.G_timeoutMiniShort)
		MobileDriver.takeScreenshot()
	}
}
