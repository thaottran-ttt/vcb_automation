package com.maybank.mae.internal.pages.base.splitbill

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
import com.maybank.assertions.BaseAssert
import com.maybank.common.MobileDriver
import com.maybank.mae.internal.pages.base.BasePage
import com.maybank.mae.internal.pages.base.CommonPage
import com.maybank.mae.internal.pages.base.StatusScreenPage
import com.maybank.utils.StringUtils

import internal.GlobalVariable

public abstract class SplitBillPage extends BasePage{

	abstract TestObject imgSearch()
	abstract TestObject imgV()
	abstract TestObject plusIcon()	
	abstract TestObject syncContactBtn()
	CommonPage commonPage = getPage(CommonPage)
	StatusScreenPage statusScreenPage = getPage(StatusScreenPage)

	static final CONTACTS = 'Contacts'
	static final SAVE_CONTACT_TO = 'Save contact to'
	static final ALLOW = 'Allow'
	static final PHONE = 'Phone'
	static final SPLIT_AMOUNT = 'Split amount'
	static final SPLIT_NOW = 'Split Now'
	static final REMOVE_RECEIPT = 'REMOVE RECEIPT'
	static final SAVE = 'Save'
	static final VI_SAVE = 'Lưu'
	static final PARTICIPANTS = 'Participants'
	static final GROUP_NAME = 'Group Name'
	static final EVENLY = 'Evenly'
	static final GROUP = 'GROUP'
	static final GROUPS = 'GROUPS'
	static final PAST = 'PAST'
	static final CREATE_GROUP = 'Create Group'
	static final NEW_GROUP = 'New Group'
	static final ENTER_NAME = 'Enter Name'
	static final SPLIT_BILL = 'Split a Bill'
	static final TAKE_A_PHOTO = 'TAKE PHOTO'
	static final RM_DEFAULT = 'RM 0.00'
	static final SHARE_BILL = 'Hi, looks like'
	static final DEFAULT_AMT = '60'
	static final AMT_INVALID = '99'
	static final ADD_GROUP = 'Add Group as Favourite'
	static final DELETE_GROUP = 'Delete Group'
	static final YES = 'Yes'
	static final PLUS_AMT = '200'
	static final SEND_REMINDER_ERROR = 'You may only send out one reminder'
	static final ERROR_AMT = 'Amount should fall between RM1.00 to RM999,999.99.'

	//Wrong Bill
	String UIPaymentUnsuccessful = 'Payment unsuccessful'
	String UIInvalidPayeeDetail = 'Invalid Payee details'

	//General
	String UIContinute = 'Continue'

	void splitBillDetails(String type, String billName, String note, String amount, FailureHandling flowControl) {
		MobileDriver.waitForElementPresent(commonPage.lblDynamic(SPLIT_BILL), GlobalVariable.G_timeoutLong, flowControl)
		MobileDriver.delay(GlobalVariable.G_timeoutMiniShort)
		
		'Tap Split a Bill'
		MobileDriver.tap(commonPage.lblDynamic(SPLIT_BILL), GlobalVariable.G_timeoutShort, flowControl)
		'Set BillName'
		MobileDriver.setText(commonPage.txtEditNo(1), billName, GlobalVariable.G_timeoutMedium, flowControl)
		'Set Note'
		MobileDriver.setText(commonPage.txtEditNo(2), note, GlobalVariable.G_timeoutMedium, flowControl)
		'Tap type a Bill'
		MobileDriver.tap(commonPage.lblDynamic(type), GlobalVariable.G_timeoutLong, flowControl)
		MobileDriver.tap(commonPage.lblDynamic(type), GlobalVariable.G_timeoutLong, flowControl)
		commonPage.tapContinueBtn()
		if (type == EVENLY) {
			'Enter wrong amount to share'
			commonPage.enterNO(AMT_INVALID, false)
			'Tap to stick button'
			MobileDriver.tap(commonPage.imgNoDynamic(6), GlobalVariable.G_timeoutLong, flowControl)
			'verify error'
			MobileDriver.verifyElementVisible(commonPage.lblDynamic(ERROR_AMT), GlobalVariable.G_timeoutShort)
			'reset data'
			for (int i = 0; i < 3; i++) {
				MobileDriver.tap(commonPage.imgNoDynamic(4), GlobalVariable.G_timeoutLong, flowControl);
			}

			'Enter amount to share'
			commonPage.enterNO(amount, false)
			'use calculator plus 200'
			MobileDriver.tap(commonPage.imgNoDynamic(8), GlobalVariable.G_timeoutLong, flowControl)
			commonPage.enterNO(PLUS_AMT, false)
			'Tap to stick button'
			MobileDriver.tap(commonPage.imgNoDynamic(6), GlobalVariable.G_timeoutLong, flowControl)
		}
	}

	void finalizeSplitBill(List<String> mobileNumberShareBill, String type, String amt1st, FailureHandling flowControl) {
		'Select a contact to share'
		for (String mobiNumber : mobileNumberShareBill) {
			int maxRetry = 3
			boolean found = false
		
			for (int attempt = 1; attempt <= maxRetry; attempt++) {
				info("Checking contact '${mobiNumber}' - attempt ${attempt}")
		
				if (MobileDriver.waitForElementPresent(commonPage.lblDynamic(mobiNumber), GlobalVariable.G_timeoutMiniShort, FailureHandling.OPTIONAL)) {
					MobileDriver.tap(commonPage.lblDynamic(mobiNumber), GlobalVariable.G_timeoutLong, flowControl)
					info("Found and tapped contact: ${mobiNumber}")
					found = true
					break
				} else {
					info("Contact ${mobiNumber} not found - trying Sync (${attempt}/${maxRetry})")
		
					if (MobileDriver.waitForElementPresent(syncContactBtn(), GlobalVariable.G_timeoutShort)) {
						MobileDriver.tap(syncContactBtn(), GlobalVariable.G_timeoutShort)
						info("Sync contact button tapped")
					} else {
						info("Sync contact button not visible")
					}
		
					MobileDriver.delay(GlobalVariable.G_timeoutShort) 
				}
			}
		}
		

		commonPage.tapDoneBtn()
		commonPage.tapContinueBtn()
		MobileDriver.waitForElementPresent(commonPage.lblDynamic(SPLIT_AMOUNT), GlobalVariable.G_timeoutShort)
		if (type != EVENLY) {
			while (MobileDriver.waitForElementPresent(commonPage.lblDynamic(RM_DEFAULT), GlobalVariable.G_timeoutShort)) {
				MobileDriver.scrollToEnd()
				'Tap to input'
				MobileDriver.tap(commonPage.lblDynamicTextLast(RM_DEFAULT), GlobalVariable.G_timeoutLong, flowControl)
				'Enter amount to share'
				commonPage.enterNO(amt1st, false)
				'Tap to stick button'
				MobileDriver.tap(commonPage.imgNoDynamic(6), GlobalVariable.G_timeoutLong, flowControl)
			}
		}
		Mobile.delay(GlobalVariable.G_timeoutMiniShort)
		processCapture()
	}
	void finalizeSplitBillByGroup(String grpName, FailureHandling flowControl) {
		selectTab(GROUPS)
		selectGroupName(grpName)
		commonPage.tapContinueBtn()
		Mobile.delay(GlobalVariable.G_timeoutMiniShort)
		processCapture()
	}
	void processCapture(FailureHandling flowControl = defaultFailureHandling) {
		'Select Capture Image'
		MobileDriver.tap(commonPage.imgNoDynamic(3), GlobalVariable.G_timeoutLong, flowControl)
		'Tap Take a Photo'
		MobileDriver.tap(commonPage.lblDynamic(TAKE_A_PHOTO), GlobalVariable.G_timeoutLong, flowControl)
		Mobile.delay(GlobalVariable.G_timeoutMiniShort)
		'Tap capture'
		MobileDriver.tap(commonPage.imgNoDynamic(2), GlobalVariable.G_timeoutLong, flowControl)
		Mobile.delay(GlobalVariable.G_timeoutMiniShort)
		'Tap to save image'
		MobileDriver.tap(commonPage.imgNoDynamic(4), GlobalVariable.G_timeoutLong, flowControl)
		Mobile.delay(GlobalVariable.G_timeoutShort)
		'Select Capture Image again to remove'
		MobileDriver.tap(commonPage.imgNoDynamic(3), GlobalVariable.G_timeoutLong, flowControl)
		'Select Remove'
		MobileDriver.tap(commonPage.lblDynamic(REMOVE_RECEIPT), GlobalVariable.G_timeoutShort)
		Mobile.delay(GlobalVariable.G_timeoutMedium)
		MobileDriver.tap(commonPage.lblDynamic(SPLIT_NOW), GlobalVariable.G_timeoutShort)
		if(MobileDriver.waitForElementPresent(commonPage.lblStartsWith(SHARE_BILL), GlobalVariable.G_timeoutShort)) {
			Mobile.pressBack()
		}
	}

	void splitBill(String type = EVENLY, String billName, String note, String amount, List<String> mobileNumberShareBill, String amt1st = DEFAULT_AMT, FailureHandling flowControl = defaultFailureHandling) {
		splitBillDetails(type, billName, note, amount, flowControl)
		finalizeSplitBill(mobileNumberShareBill, type, amt1st, flowControl)
	}
	void splitBillByGroup(String type = EVENLY, String billName, String note, String amount, String groupName, FailureHandling flowControl = defaultFailureHandling) {
		splitBillDetails(type, billName, note, amount, flowControl)
		finalizeSplitBillByGroup(groupName, flowControl)
	}

	void createGroup(List<String> mobileNumberShareBill, String groupName, String tabName, FailureHandling flowControl = defaultFailureHandling) {
		selectTab(GROUP)
		'Select Create Group'
		if(MobileDriver.waitForElementPresent(commonPage.lblDynamic(CREATE_GROUP), GlobalVariable.G_timeoutShort, FailureHandling.OPTIONAL)) {
			MobileDriver.tap(commonPage.lblDynamic(CREATE_GROUP), GlobalVariable.G_timeoutShort)
		}else if (MobileDriver.waitForElementPresent(commonPage.lblDynamic(NEW_GROUP), GlobalVariable.G_timeoutShort, flowControl)) {
			MobileDriver.tap(commonPage.lblDynamic(NEW_GROUP), GlobalVariable.G_timeoutShort)
		}
		'Select a contact'
		for (String mobiNumber : mobileNumberShareBill) {
			MobileDriver.tap(commonPage.lblDynamic(mobiNumber), GlobalVariable.G_timeoutLong, flowControl)
		}
		commonPage.tapDoneBtn()
		commonPage.tapContinueBtn()
		'Set Group Name'
		MobileDriver.setText(commonPage.txtEditNo(1), groupName, GlobalVariable.G_timeoutMedium, flowControl)
		commonPage.tapDoneBtn()
		boolean findResult = commonPage.scrollToTestObject(commonPage.frmHorizontalScrollNo(1), commonPage.lblDynamic(tabName), ScrollDirectionEnum.LEFT, GlobalVariable.G_timeoutShort, flowControl)
		if(findResult) {
			MobileDriver.tap(commonPage.lblDynamic(tabName), GlobalVariable.G_timeoutShort, flowControl)
		}
	}
	
	void addContactsSamsungS22(List<String> mobiNumbers, FailureHandling flowControl = defaultFailureHandling) {
		if (MobileDriver.waitForElementPresent(commonPage.lblDynamic(ALLOW), GlobalVariable.G_timeoutShort)) {
			MobileDriver.tap(commonPage.lblDynamic(ALLOW), GlobalVariable.G_timeoutShort)
		}
		boolean firstCheck = false
		for (String mobiNumber : mobiNumbers) {
			'Select Add Contacts'
			MobileDriver.waitForElementPresent(commonPage.lblDynamic(CONTACTS), GlobalVariable.G_timeoutShort)
			MobileDriver.tap(plusIcon(), GlobalVariable.G_timeoutLong, flowControl)

			if (MobileDriver.waitForElementPresent(commonPage.lblAnyText('Allow'), GlobalVariable.G_timeoutMiniShort) &&  !firstCheck) {
				Mobile.tap(commonPage.lblAnyText('Allow'), GlobalVariable.G_timeoutMiniShort);
				firstCheck = true
			}
			
			if (MobileDriver.waitForElementPresent(commonPage.lblAnyText('Save contact to'), GlobalVariable.G_timeoutMiniShort)) {
				Mobile.tap(commonPage.lblAnyText('Phone'), GlobalVariable.G_timeoutMiniShort);
			}
			
			'Set Name'
			MobileDriver.setText(commonPage.txtEditNo(1), mobiNumber, GlobalVariable.G_timeoutMedium, flowControl)

			'Set Number'
			MobileDriver.setText(commonPage.txtEditNo(2), mobiNumber, GlobalVariable.G_timeoutMedium, flowControl)

			'Save Contact'
			if(Mobile.waitForElementPresent(commonPage.lblDynamic(SAVE), GlobalVariable.G_timeoutShort)) {
				MobileDriver.tap(commonPage.lblDynamic(SAVE), GlobalVariable.G_timeoutShort)
			}else if(Mobile.waitForElementPresent(commonPage.lblDynamic(VI_SAVE),GlobalVariable.G_timeoutShort)){
				MobileDriver.tap(commonPage.lblDynamic(VI_SAVE), GlobalVariable.G_timeoutShort)
			}
			MobileDriver.takeScreenshot()

			'Return to Add Contacts Screen (if necessary)'
			Mobile.pressBack()
		}
	}
	
	void addContactsSamsungS24(List<String> mobiNumbers, FailureHandling flowControl = defaultFailureHandling) {
		if (MobileDriver.waitForElementPresent(commonPage.lblDynamic(ALLOW), GlobalVariable.G_timeoutShort)) {
			MobileDriver.takeScreenshot()
			MobileDriver.tap(commonPage.lblDynamic(ALLOW), GlobalVariable.G_timeoutShort)
		}
		boolean firstCheck = false
		
		for (String mobiNumber : mobiNumbers) {
			'Select Add Contacts'
			MobileDriver.waitForElementPresent(commonPage.lblDynamic(CONTACTS), GlobalVariable.G_timeoutShort)
			MobileDriver.takeScreenshot()
			MobileDriver.tap(plusIcon(), GlobalVariable.G_timeoutLong, flowControl)
			if (MobileDriver.waitForElementPresent(commonPage.lblDynamic(SAVE_CONTACT_TO), GlobalVariable.G_timeoutMiniShort)) {
				MobileDriver.takeScreenshot()
				'Tap on Phone from Contact List'
				MobileDriver.tap(commonPage.lblDynamicNo(PHONE,1), GlobalVariable.G_timeoutShort)
				
			} else {
				String profileTextId = "com.samsung.android.app.contacts:id/account_title_text"
				String profileSelectedId = "com.samsung.android.app.contacts:id/selected_account_title"
				
				MobileDriver.takeScreenshot()
			
				if (MobileDriver.verifyElementExist(commonPage.lblDynamicId(profileTextId), GlobalVariable.G_timeoutShort, FailureHandling.OPTIONAL)) {
					'Get Profile Text'
					String profileText = MobileDriver.getText(commonPage.lblDynamicId(profileTextId), GlobalVariable.G_timeoutShort)
					info("Profile Title: " + profileText)
			
				} else if (MobileDriver.verifyElementExist(commonPage.lblDynamicId(profileSelectedId), GlobalVariable.G_timeoutShort)) {
					'Get Selected Profile Text'
					String profileText = MobileDriver.getText(commonPage.lblDynamicId(profileSelectedId), GlobalVariable.G_timeoutShort)
					info("Profile Selected Title: " + profileText)
			
					if (profileText == PHONE) {
						info("Profile Selected is already Phone — skip selecting profile")
					} else {
						'Tap on Profile title'
						MobileDriver.tap(commonPage.lblDynamicId(profileSelectedId), GlobalVariable.G_timeoutShort)
			
						'Wait for Profile list to be visible -> contain Phone'
						MobileDriver.waitForElementPresent(commonPage.lblDynamic(SAVE_CONTACT_TO), GlobalVariable.G_timeoutShort)
						MobileDriver.takeScreenshot()
			
						'Select Profile Phone'
						MobileDriver.tap(commonPage.lblDynamicNo(PHONE, 1), GlobalVariable.G_timeoutShort, flowControl)
						MobileDriver.delay(GlobalVariable.G_timeoutMiniShort)
					}
			
				} else {
					info("No profile element found — skip selecting profile")
				}
			}

			'Wait for Profile list to be not visible'
			MobileDriver.waitForElementNotPresent(commonPage.lblDynamic(SAVE_CONTACT_TO), GlobalVariable.G_timeoutShort)
			MobileDriver.takeScreenshot()
			
			'Set Name'
			MobileDriver.setText(commonPage.txtEditNo(1), mobiNumber, GlobalVariable.G_timeoutMedium, flowControl)

			'Set Number'
			MobileDriver.setText(commonPage.txtEditNo(2), mobiNumber, GlobalVariable.G_timeoutMedium, flowControl)
			MobileDriver.takeScreenshot()

			'Save Contact'
			MobileDriver.tap(commonPage.lblDynamic(SAVE), GlobalVariable.G_timeoutShort)

			MobileDriver.takeScreenshot()
			
			'Return to Add Contacts Screen (if necessary)'
			Mobile.pressBack()
		}
		MobileDriver.takeScreenshot()
	}


	void verifyStatusScreen(String title, FailureHandling flowControl = defaultFailureHandling) {
		'verify screen'
		MobileDriver.verifyElementVisible(commonPage.lblDynamic(title), GlobalVariable.G_timeoutShort)
		'Verify Date and Time'
		statusScreenPage.dateAndTime()
		commonPage.tapDoneBtn()
		Mobile.delay(GlobalVariable.G_timeoutMiniShort)
	}

	void handleBill(String billName, FailureHandling flowControl = defaultFailureHandling) {
		MobileDriver.verifyElementVisible(commonPage.lblDynamic(billName), GlobalVariable.G_timeoutShort)
		'select bill'
		MobileDriver.tap(commonPage.lblDynamicNo(billName, 1), GlobalVariable.G_timeoutShort)
		MobileDriver.verifyElementVisible(commonPage.lblDynamic(PARTICIPANTS), GlobalVariable.G_timeoutShort)
		while (MobileDriver.waitForElementPresent(commonPage.imgNoDynamic(4), GlobalVariable.G_timeoutShort)) {
			'tap icon arrow'
			MobileDriver.tap(commonPage.imgNoDynamic(4), GlobalVariable.G_timeoutLong, flowControl)
			Mobile.delay(GlobalVariable.G_timeoutMiniShort)
			commonPage.swipeInTestObject(commonPage.frmScrollView(), ScrollDirectionEnum.DOWN)
			commonPage.tapDoneBtn()
			if (MobileDriver.waitForElementPresent(commonPage.lblContainDynamic(SEND_REMINDER_ERROR), GlobalVariable.G_timeoutShort)) {
				MobileDriver.tap(commonPage.imgNoDynamic(4), GlobalVariable.G_timeoutLong, flowControl)
				commonPage.tapDoneBtn()
				Mobile.delay(GlobalVariable.G_timeoutMedium)
			}else {
				Mobile.delay(GlobalVariable.G_timeoutMiniShort)
			}
		}
		commonPage.tapBack();
	}

	void addAndDeleteFavGrp(String billName, String favGrp = "FavGrp", FailureHandling flowControl = defaultFailureHandling) {
		MobileDriver.verifyElementVisible(commonPage.lblDynamicNo(billName, 1), GlobalVariable.G_timeoutShort)
		'select bill'
		MobileDriver.tap(commonPage.lblDynamicNo(billName, 1), GlobalVariable.G_timeoutShort)
		MobileDriver.verifyElementVisible(commonPage.lblDynamic(PARTICIPANTS), GlobalVariable.G_timeoutShort)
		MobileDriver.tap(commonPage.imgNoDynamic(2), GlobalVariable.G_timeoutShort)
		MobileDriver.tap(commonPage.lblDynamic(ADD_GROUP), GlobalVariable.G_timeoutShort)
		MobileDriver.setText(commonPage.txtEditNo(1), favGrp, GlobalVariable.G_timeoutMedium, flowControl)
		commonPage.tapDoneBtn()
		Mobile.delay(GlobalVariable.G_timeoutShort)
		deleteGrp(favGrp)
	}

	void deleteGrp(String grpName, FailureHandling flowControl = defaultFailureHandling) {
		info "Delete Group"
		selectGroupName(grpName)
		MobileDriver.verifyElementVisible(commonPage.lblDynamic(GROUP_NAME), GlobalVariable.G_timeoutShort)
		'delete a item'
		MobileDriver.tap(commonPage.imgNoDynamic(3), GlobalVariable.G_timeoutShort)
		Mobile.delay(GlobalVariable.G_timeoutShort)
		'delete a group'
		MobileDriver.tap(commonPage.imgNoDynamic(2), GlobalVariable.G_timeoutShort)
		MobileDriver.tap(commonPage.lblDynamic(DELETE_GROUP), GlobalVariable.G_timeoutShort)
		'confirm Yes'
		MobileDriver.tap(commonPage.lblDynamic(YES), GlobalVariable.G_timeoutShort)
		Mobile.delay(GlobalVariable.G_timeoutMedium)
	}

	void handleBillSucess(String billName, FailureHandling flowControl = defaultFailureHandling) {
		MobileDriver.verifyElementVisible(commonPage.lblDynamic(billName), GlobalVariable.G_timeoutShort)
		'select bill'
		MobileDriver.tap(commonPage.lblDynamicNo(billName, 1), GlobalVariable.G_timeoutShort)
		MobileDriver.verifyElementVisible(commonPage.lblDynamic(PARTICIPANTS), GlobalVariable.G_timeoutShort)
		while (MobileDriver.waitForElementPresent(commonPage.imgNoDynamic(4), GlobalVariable.G_timeoutShort)) {
			'tap icon arrow'
			MobileDriver.tap(commonPage.imgNoDynamic(4), GlobalVariable.G_timeoutLong, flowControl)
			commonPage.tapDoneBtn()
			Mobile.delay(GlobalVariable.G_timeoutMedium)
		}
		commonPage.tapBack();
	}
	void addSucessBillToFav(String billName, String favGrp = "FavGrpSucess", FailureHandling flowControl = defaultFailureHandling) {
		selectTab(PAST)
		MobileDriver.verifyElementVisible(commonPage.lblDynamic(billName), GlobalVariable.G_timeoutShort)
		'select bill'
		MobileDriver.tap(commonPage.lblDynamicNo(billName, 1), GlobalVariable.G_timeoutShort)
		MobileDriver.verifyElementVisible(commonPage.lblDynamic(PARTICIPANTS), GlobalVariable.G_timeoutShort)
		MobileDriver.tap(commonPage.imgNoDynamic(2), GlobalVariable.G_timeoutShort)
		MobileDriver.tap(commonPage.lblDynamic(ADD_GROUP), GlobalVariable.G_timeoutShort)
		MobileDriver.setText(commonPage.txtEditNo(1), favGrp, GlobalVariable.G_timeoutMedium, flowControl)
		commonPage.tapDoneBtn()
		Mobile.delay(GlobalVariable.G_timeoutShort)
		deleteGrp(favGrp)
	}


	/**
	 * Select tab
	 */
	void selectTab(String tabName, boolean isVisible = false, FailureHandling flowControl = defaultFailureHandling) {
		MobileDriver.delay(GlobalVariable.G_timeoutShort)
		if(isVisible) {
			MobileDriver.tap(commonPage.lblDynamic(tabName), GlobalVariable.G_timeoutLong, flowControl)
			MobileDriver.takeScreenshot()
			return
		}
		commonPage.selectTab(commonPage.frmHorizontalScrollNo(1), tabName, GlobalVariable.G_timeoutMedium, flowControl)
		MobileDriver.takeScreenshot()
	}
	void selectGroupName(String grpName, flexibledDevice = true, FailureHandling flowControl = defaultFailureHandling) {
		commonPage.scrollToTestObject(commonPage.frmHorizontalScrollNo(2), commonPage.lblDynamicNo(grpName, 1), ScrollDirectionEnum.DOWN, GlobalVariable.G_timeoutShort, flowControl, flexibledDevice)
		MobileDriver.tap(commonPage.lblDynamicNo(grpName, 1), GlobalVariable.G_timeoutMedium, flowControl)
		MobileDriver.takeScreenshot()
	}
}
