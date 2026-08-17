package com.maybank.mae.internal.pages.base.applypage

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
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import com.maybank.common.MobileDriver
import java.time.format.DateTimeFormatter;
import com.maybank.mae.internal.pages.base.BasePage
import com.maybank.mae.internal.pages.base.CommonPage
import com.maybank.mae.internal.pages.base.CommonPage.DropDownTypeEnum
import com.maybank.mae.internal.pages.base.ShareReceiptPage
import com.maybank.mae.internal.pages.base.StatusScreenPage
import internal.GlobalVariable
import java.time.LocalDate

public abstract class ApplyPageMiga extends BasePage {

	CommonPage commonPage = getPage(CommonPage)
	StatusScreenPage statusScreenPage = getPage(StatusScreenPage)
	ShareReceiptPage shareReceiptPage = getPage(ShareReceiptPage)
	
	abstract TestObject imgPrecedingSibling(String text)
	abstract TestObject txtSibling(String text)
	abstract TestObject btnSibling(String text)
	
	static final String APPLY_NOW = 'Apply Now'
	static final String AGREE_AND_CONFIRM = 'Agree & Confirm'
	static final String CONFIRM_AND_SUBMIT = 'Confirm & Submit'
	static final String EDIT = 'Edit'
	static final String STATE = "State"
	static final String ACTIVATE_NOW = 'Activate Now'
	
	String userName = ''
	String password = ''
	String pan = ''
	String idNumber = ''
	
	
	/*
	 * tap to Apply Now Button
	 */
	void tapApplyNow(FailureHandling flowControl = defaultFailureHandling) {
		delay(GlobalVariable.G_timeoutMiniShort)
		MobileDriver.tap(commonPage.lblDynamic(APPLY_NOW), GlobalVariable.G_timeoutLong, flowControl)
		commonPage.waitLoading()
	}
	
	void navigateToDeclaration() {
		commonPage.tapNextBtn()
		delay(GlobalVariable.G_timeoutMiniShort)
	}
	
	void agreeAndConfirm(String agreeRadio, FailureHandling flowControl = defaultFailureHandling) {

		commonPage.swipeByW3CAction(5, 1500, 5, 100)

		'Tap Agree Radio'
		MobileDriver.tap(this.imgPrecedingSibling(agreeRadio), GlobalVariable.G_timeoutMedium, flowControl)

		'Tap Agree And Confirm'
		MobileDriver.tap(commonPage.lblDynamic(AGREE_AND_CONFIRM), GlobalVariable.G_timeoutMedium, flowControl)
		delay(GlobalVariable.G_timeoutMiniShort)
	}
	
	void confirmation(Map<String, String> addressMap, int timeout = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		commonPage.scrollToTop()
		'Tap Confirm And Submit'
		MobileDriver.tap(commonPage.lblDynamic(CONFIRM_AND_SUBMIT), GlobalVariable.G_timeoutMedium, flowControl)
		delay(GlobalVariable.G_timeoutMiniShort)
		if(waitForElementPresent(commonPage.lblDynamic(CONFIRM_AND_SUBMIT), timeout)) {
			editAddressLine2(addressMap)
			delay(GlobalVariable.G_timeoutMiniShort)
			MobileDriver.tap(commonPage.lblDynamic(CONFIRM_AND_SUBMIT), GlobalVariable.G_timeoutMedium, flowControl)
		}
	}
	void editAddressLine2(Map<String, String> addressMap, int timeout = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		def filteredMap = addressMap.findAll { key, value -> key != 'state' }
		MobileDriver.tap(commonPage.lblDynamicNo(EDIT, 1), timeout, flowControl)
		delay(GlobalVariable.G_timeoutMiniShort)
		for (Map.Entry<String, String> entry : filteredMap.entrySet()) {
			scrollTo(this.txtSibling(entry.getKey()))
			commonPage.setText(this.txtSibling(entry.getKey() ), entry.getValue(), GlobalVariable.G_timeoutMedium, flowControl)
		}
		commonPage.swipe(5, 1500, 5, 100)
		selectDropdownSiblingGroupItem(STATE, addressMap.remove('state'));
		commonPage.tapNextBtn()
	}
	void selectDropdownSiblingGroupItem(String fieldName, String value, int timeout = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {		
		Mobile.verifyElementExist(btnSibling(fieldName), timeout, FailureHandling.CONTINUE_ON_FAILURE)
		info("Tap on drop-down ${fieldName}")
		Mobile.tap(btnSibling(fieldName), timeout, flowControl)
		info("Search for ${value}")
		if(!(MobileDriver.waitForElementPresent(commonPage.lblNormalizeSpace(value), timeout, FailureHandling.OPTIONAL))){
			info("Scroll to ${value}")
			Mobile.scrollToText(value, FailureHandling.OPTIONAL)
		}

		info("Tap on ${value}")
		Mobile.tap(commonPage.lblNormalizeSpace(value), timeout, flowControl)
		delay(GlobalVariable.G_timeoutMiniShort)
	}
	
	void selectAccountFromDataFile() {
		TestData testData = findTestData("Data Files/KMS/MIGA/MigaAccount")
		List<String> columnNames = testData.getColumnNames()
		def validCount = 0
		for (int i = 1; i <= testData.getRowNumbers(); i++) {
			Boolean isValid = Boolean.valueOf(testData.getValue("isValid", i))
			if(isValid) {
				userName = testData.getValue("username", i)
				password = testData.getValue("password", i)
				pan = testData.getValue("PAN", i)
				idNumber = testData.getValue("idNumber", i) 
				validCount ++
				break;
			}	
		}
		if(validCount == 0) {
			KeywordUtil.markFailedAndStop("There is not valid data to apply Miga, please check")
		}
	}
	
	void updateIsValidColumn() {
    TestData testData = findTestData("Data Files/KMS/MIGA/MigaAccount")
    String filePath = testData.getSourceUrl() 

    List<String> lines = new File(filePath).readLines()

    List<String> updatedLines = []
    boolean isUpdated = false 

    lines.eachWithIndex { line, index ->
        if (index == 0) {
            updatedLines.add(line)
        } else {

            String[] columns = line.split(',')
            
            if (!isUpdated && columns[columns.length - 1].toLowerCase() == 'true') {
                columns[columns.length - 1] = 'FALSE' 
                isUpdated = true 
            }
            updatedLines.add(columns.join(','))
        }
    }

	    new File(filePath).withWriter { writer ->
	        updatedLines.each { writer.writeLine(it) }
	    }
	}
	void tapToActiveNow(int timeout = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		commonPage.swipeByW3CAction(5, 1500, 5, 100)
		if(verifyElementExist(commonPage.lblDynamic(ACTIVATE_NOW), timeout, FailureHandling.STOP_ON_FAILURE)) {
			MobileDriver.tap(commonPage.lblDynamic(ACTIVATE_NOW), timeout, flowControl)
		}
		
	}

}
