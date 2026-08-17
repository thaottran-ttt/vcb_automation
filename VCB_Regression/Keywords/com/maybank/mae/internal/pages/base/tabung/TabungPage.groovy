package com.maybank.mae.internal.pages.base.tabung

import java.util.regex.Matcher
import java.util.regex.Pattern

import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testobject.TestObject
import com.maybank.assertions.BaseAssert
import com.maybank.common.MobileDriver
import com.maybank.mae.internal.pages.base.BasePage
import com.maybank.mae.internal.pages.base.CommonPage
import com.maybank.mae.internal.pages.base.Secure2uAuthorisationPage
import com.maybank.mae.internal.pages.base.StatusScreenPage
import com.maybank.utils.StringUtils

import internal.GlobalVariable

public abstract class TabungPage extends BasePage {

	CommonPage commonPage = getPage(CommonPage)
	Secure2uAuthorisationPage secure2uAuthorisationPage = BasePage.getPage(Secure2uAuthorisationPage)
	StatusScreenPage statusScreenPage = BasePage.getPage(StatusScreenPage)

	abstract TestObject lblDropDownDefaultValue()
	abstract TestObject btnTick()
	abstract TestObject btnToggel(String text)


	static String SPARE_CHANGE = 'Spare Change'
	static String GUILTY_PLEASURE = 'Guilty Pleasure'
	static String SCAN_SAVE = 'Scan & Save'
	static String ENTERTAINMENT = 'Entertainment'
	static String BOOSTER_SUMMARY = 'Booster summary:'
	static String RM_DEFAULT = 'RM 1.00'
	static String RM_5 = 'RM  5.00'
	static String RM_1 = 'RM  1.00'
	static String LEARN_MORE = 'Learn More'
	static String SAVE_CHANGES = 'Save Changes'
	static String CONFIRM = 'Confirm'
	static String DAILY_LIMIT = 'Set daily limit'
	static String REMOVE = 'Remove'
	static String REMOVE_TABUNG = 'Remove Tabung'
	static String TABUNG = 'Tabung'
	static String CREATE_TABUNG = 'Create Tabung'
	static String SAVING_WITH = 'Saving with...'
	static String GOT_IT = 'Got It'
	static String ADD = 'Add'
	static String SELECT_ACCOUNT = 'Select account'
	static String TAKE_A_PHOTO = 'Take a photo'
	static String VIEW_PAST_TABUNG = "View Past Tabung";
	static String PAST_TABUNG = "Past Tabung";
	static String TABUNG_DELETED_MESSAGE = "Tabung summary successfully deleted.";
	static String CANCELLED = 'Cancelled'
	static final String REFERENCE_ID_7_9 = '^([0-9]{7}|[0-9]{9}M)$'
	static String KEEP = 'Keep'
	static String WITHDRAW = 'Withdraw'

	List<List<String>> getTabungListView() {
		return commonPage.getAllItemsListView([
			"//android.widget.TextView[1]",
			"//android.widget.TextView[2]",
			"//android.widget.TextView[3]",
			"//android.widget.TextView[4]"
		], commonPage.frmScrollViewGroupNo(1, 2))
	}
	void processSpareChange(FailureHandling flowControl = defaultFailureHandling) {
		MobileDriver.tap(commonPage.lblDynamic(SPARE_CHANGE), GlobalVariable.G_timeoutMedium, flowControl)
		MobileDriver.tap(commonPage.lblRMNo(1), GlobalVariable.G_timeoutMedium, flowControl)
		commonPage.delay(GlobalVariable.G_timeoutMiniShort)
		MobileDriver.verifyElementVisible(commonPage.lblDynamic(SPARE_CHANGE), GlobalVariable.G_timeoutMedium, flowControl)
		MobileDriver.verifyElementVisible(commonPage.imgNoDynamic(2), GlobalVariable.G_timeoutMedium, flowControl)
		TestObject valueObject = this.lblDropDownDefaultValue()
		String currentValue = getText(valueObject, GlobalVariable.G_timeoutMedium, flowControl)
		MobileDriver.tap(commonPage.imgNoDynamic(2), GlobalVariable.G_timeoutMedium, flowControl)
		if (RM_DEFAULT == currentValue) {
			MobileDriver.tap(commonPage.lblDynamic(RM_5), GlobalVariable.G_timeoutMedium, flowControl)
		}else {
			MobileDriver.tap(commonPage.lblDynamic(RM_1), GlobalVariable.G_timeoutMedium, flowControl)
		}
		commonPage.tapDoneBtn()
		commonPage.tapContinueBtn()
		commonPage.delay(GlobalVariable.G_timeoutMiniShort)
		learnMore()
		MobileDriver.verifyElementVisible(commonPage.lblDynamic(BOOSTER_SUMMARY), GlobalVariable.G_timeoutMedium, flowControl)
		MobileDriver.tap(commonPage.lblDynamic(SAVE_CHANGES), GlobalVariable.G_timeoutMedium, flowControl)
	}

	void processScanAndSave(FailureHandling flowControl = defaultFailureHandling) {
		MobileDriver.tap(commonPage.lblDynamic(SCAN_SAVE), GlobalVariable.G_timeoutMedium, flowControl)
		learnMore()
		MobileDriver.tap(commonPage.lblDynamic(SAVE_CHANGES), GlobalVariable.G_timeoutMedium, flowControl)
	}
	void processGuiltyPleasure(String limitAmt = '1000', String fundAmt = '1', FailureHandling flowControl = defaultFailureHandling) {
		MobileDriver.tap(commonPage.lblDynamic(GUILTY_PLEASURE), GlobalVariable.G_timeoutMedium, flowControl)
		commonPage.delay(GlobalVariable.G_timeoutMiniShort)
		//check if already added Catalog
		if (deleteGuity()) {
			're-enter'
			MobileDriver.tap(commonPage.lblDynamic(GUILTY_PLEASURE), GlobalVariable.G_timeoutMedium, flowControl)
			commonPage.delay(GlobalVariable.G_timeoutMiniShort)
		}
		learnMore()
		MobileDriver.verifyElementVisible(commonPage.lblDynamic(ENTERTAINMENT), GlobalVariable.G_timeoutMedium, flowControl)
		MobileDriver.tap(commonPage.lblDynamic(ENTERTAINMENT), GlobalVariable.G_timeoutMedium, flowControl)
		commonPage.enterNO(limitAmt)
		commonPage.enterNO(fundAmt)
		MobileDriver.tap(commonPage.lblRMNo(1), GlobalVariable.G_timeoutMedium, flowControl)
		commonPage.enterNO(fundAmt)
		MobileDriver.tap(commonPage.lblDynamic(CONFIRM), GlobalVariable.G_timeoutMedium, flowControl)
		commonPage.delay(GlobalVariable.G_timeoutMiniShort)
	}

	boolean deleteGuity(FailureHandling flowControl = defaultFailureHandling) {
		if(waitForElementPresent(commonPage.lblDynamic(DAILY_LIMIT), GlobalVariable.G_timeoutMiniShort, FailureHandling.OPTIONAL)) {
			'Remove'
			MobileDriver.tap(commonPage.imgNoDynamic(3), GlobalVariable.G_timeoutMedium, flowControl)
			MobileDriver.tap(commonPage.lblDynamic(REMOVE), GlobalVariable.G_timeoutMedium, flowControl)
			commonPage.delay(GlobalVariable.G_timeoutMedium)
			MobileDriver.tap(commonPage.lblDynamic(SAVE_CHANGES), GlobalVariable.G_timeoutMedium, flowControl)
			commonPage.delay(GlobalVariable.G_timeoutMiniShort)
			return true
		}
		return false
	}

	void learnMore(FailureHandling flowControl = defaultFailureHandling) {
		MobileDriver.tap(commonPage.lblDynamic(LEARN_MORE), GlobalVariable.G_timeoutMedium, flowControl)
		commonPage.delay(GlobalVariable.G_timeoutMiniShort)
		MobileDriver.verifyElementVisible(commonPage.imgNoDynamic(2), GlobalVariable.G_timeoutMedium, flowControl)
		MobileDriver.tap(commonPage.imgNoDynamic(1), GlobalVariable.G_timeoutMedium, flowControl)
	}



	void enterTransferData(Map transferData, FailureHandling flowControl = defaultFailureHandling) {
		commonPage.inputDropdownMapDefault(transferData, flowControl)
		'Tap Continue'
		commonPage.tapContinueBtn()
	}

	void enterRecipientReference(String recipientReferenceData, FailureHandling flowControl = defaultFailureHandling) {
		'Input Recipient Reference'
		MobileDriver.setText(commonPage.txtEditNo(), recipientReferenceData, GlobalVariable.G_timeoutMedium, flowControl)

		'Tap Continue'
		commonPage.tapContinueBtn()
	}

	void enterOtherTabungHaji(String AccNo, String NRIC, FailureHandling flowControl = defaultFailureHandling) {
		'Input NRIC number'
		MobileDriver.setText(commonPage.txtEditNo(), AccNo, GlobalVariable.G_timeoutMedium, flowControl)

		'Input tabung Haji account number'
		MobileDriver.setText(commonPage.txtEditNo(2), NRIC, GlobalVariable.G_timeoutMedium, flowControl)

		'Tap Continue'
		commonPage.tapContinueBtn()
		MobileDriver.delay(GlobalVariable.G_timeoutMiniShort)
		MobileDriver.takeScreenshot()
	}

	void verifyAccountNotLinkedToTabungHaji(List accountNotLinkedToTabungHajiData, FailureHandling flowControl = defaultFailureHandling) {
		verifyElements(accountNotLinkedToTabungHajiData, commonPage.lblDynamicPath(), GlobalVariable.G_timeoutMedium, flowControl)
	}

	void createTabung(Map<String, String> tabungData,int timeout =  GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		waitForElementPresent(commonPage.lblDynamic(TABUNG), timeout, flowControl)
		MobileDriver.tap(commonPage.btnDynamic(CREATE_TABUNG), timeout, flowControl)
		delay(GlobalVariable.G_timeoutMiniShort)
		chooseTabungCategory(tabungData.get('category'))
		enterTabungName(tabungData.get('categoryName'))
		commonPage.tapContinueBtn()
		commonPage.enterNO(tabungData.get('amount'))
		commonPage.waitLoading()
		chooseDateStartAndEndDateForTabung()
		chooseDateStartAndEndDateForTabung()
		delay(GlobalVariable.G_timeoutMiniShort)
	}
	void chooseTabungCategory(String tabungCate, int timeout =  GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		Mobile.scrollToText(tabungCate, flowControl)
		MobileDriver.tap(commonPage.lblDynamic(tabungCate), timeout, flowControl)
	}
	void enterTabungName(String tabungName,int timeout =  GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		waitForElementPresent(commonPage.txtEditNo(1), timeout, flowControl)
		MobileDriver.setText(commonPage.txtEditNo(1), tabungName, timeout, flowControl)
		delay(GlobalVariable.G_timeoutMiniShort)
	}
	void chooseDateStartAndEndDateForTabung(int timeout =  GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		waitForElementPresent(commonPage.txtEditNo(1), timeout, flowControl)
		MobileDriver.tap(commonPage.txtEditNo(1), timeout, flowControl)
		waitForElementPresent(commonPage.lblDynamic(commonPage.UIDonebtn), timeout, flowControl)
		commonPage.tapDoneBtn()
		delay(GlobalVariable.G_timeoutMiniShort)
		waitForElementPresent(commonPage.lblDynamic(commonPage.UIContinue), timeout, flowControl)
		commonPage.tapContinueBtn()
		commonPage.waitLoading()
	}
	void createGroup(List<String> mobileNumberShareBill,int timeout =  GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		waitForElementPresent(commonPage.lblSiblingGroup(SAVING_WITH), timeout, flowControl)
		MobileDriver.tap(commonPage.lblSiblingGroup(SAVING_WITH), timeout, flowControl)
		MobileDriver.tap(commonPage.lblDynamic(GOT_IT),  GlobalVariable.G_timeoutMedium, FailureHandling.OPTIONAL)
		commonPage.waitLoading()
		for (String mobiNumber : mobileNumberShareBill) {
			MobileDriver.tap(commonPage.lblDynamic(mobiNumber), GlobalVariable.G_timeoutLong, flowControl)
		}
		MobileDriver.tap(commonPage.lblDynamic(ADD), timeout, flowControl)
		commonPage.waitLoading()
		commonPage.tapContinueBtn()
		delay(GlobalVariable.G_timeoutMiniShort)
	}
	void selectAccount(int timeout =  GlobalVariable.G_timeoutLong, FailureHandling flowControl = defaultFailureHandling ) {
		waitForElementPresent(commonPage.lblDynamic(SELECT_ACCOUNT), timeout, flowControl)
		MobileDriver.tap(commonPage.lblDynamic(SELECT_ACCOUNT), timeout, FailureHandling.OPTIONAL)
		waitForElementPresent(commonPage.lblDynamic(commonPage.UIDonebtn), timeout, flowControl)
		commonPage.tapDoneBtn()
		commonPage.waitLoading()
	}
	
	void tabungConfirmaiton(int timeout =  GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		delay(GlobalVariable.G_timeoutMiniShort)
		waitForElementPresent(commonPage.lblDynamic(commonPage.CONFIRMATION), timeout, flowControl)
		Mobile.waitForElementPresent(commonPage.lblDynamic(CREATE_TABUNG), GlobalVariable.G_timeoutLong, FailureHandling.STOP_ON_FAILURE)
		MobileDriver.tap(commonPage.lblDynamic(CREATE_TABUNG), timeout, FailureHandling.OPTIONAL)
		commonPage.waitLoading()
	}
	void selectAccountAndConfirm(int timeout =  GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		selectAccount()
		commonPage.tapContinueBtn()
		commonPage.waitLoading()
	}

	void verifyTabungContent(String amount, int timeout =  GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		try {
			def listView = getTabungListView()
			def tabungAmount = StringUtils.extractValuesListView(listView, 2).get(0)
			info("Tabung amount: "+ tabungAmount)
			def tabungTimeLine = StringUtils.extractValuesListView(listView, 3).get(0)
			info("Tabung time line: "+ tabungTimeLine)
			BaseAssert.equals(tabungAmount.toString().contains(amount), true , "Verify contains money", flowControl)
			BaseAssert.equals(containsTimeUnits(tabungTimeLine), true , "Verify Time to go", flowControl)
		}catch (Exception ex) {
			//avoid can not navigate to DEV OPTION screen
			//back to home when fail
			commonPage.backToHome()
			FailureHandling.STOP_ON_FAILURE
		}
	}
	static boolean containsTimeUnits(String text) {
		//regex to check the date, month, year
		String regex = "\\b\\d+\\s+(day|days|month|months|year|years)\\b";

		//compile regex and create matcher
		Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(text);

		//check if any element matches
		return matcher.find();
	}

	String actionWithTabung(String tabungName, String action, Map tabungMap, int timeout = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		//Making action with activated Tabung following passed in parameter
		String refID = '';

		if (action == null || action.isEmpty()) {
			throw new IllegalArgumentException("Action cannot be null or empty");
		}
		if (tabungMap == null || tabungMap.isEmpty()) {
			throw new IllegalArgumentException("TabungMap cannot be null or empty");
		}

		//find tabung name and tap
		Mobile.scrollToText(tabungName)
		tapAndWait(commonPage.lblDynamic(tabungName));

		//tap to open action list then tap to action
		tapAndWait(commonPage.imgNoDynamic(2));
		delay(GlobalVariable.G_timeoutMiniShort);
		tapAndWait(commonPage.lblContainDynamic(action), timeout, FailureHandling.OPTIONAL);

		//use switch case to handle for each case of action
		switch (action) {
			case 'Edit':
				handleEditAction(tabungMap.get('result'),timeout, flowControl);
				break;
			case 'Fund Tabung':
				commonPage.enterNO(tabungMap.get('amount'));
				commonPage.waitLoading();
				MobileDriver.tap(commonPage.lblContainDynamic(action), timeout, flowControl);
				return actionWithS2uTabung(tabungMap , tabungMap.get("regex"));
			case 'Rename':
				commonPage.waitLoading();
				enterTabungName(tabungMap.get('name'));
				MobileDriver.tap(commonPage.lblDynamic(CONFIRM), timeout, flowControl)
				delay(GlobalVariable.G_timeoutMiniShort)
				verifyElementVisible(commonPage.lblDynamic(tabungMap.get("result")), timeout, flowControl);
				break;
			case 'Withdraw':
				MobileDriver.verifyElementVisible(commonPage.lblDynamic(action +" "+ TABUNG), timeout, flowControl)
				MobileDriver.tap(commonPage.btnDynamic(action), timeout, flowControl)
				delay(GlobalVariable.G_timeoutShort)
				commonPage.enterNO(tabungMap.get('amount'))
				if(waitForElementPresent(commonPage.lblDynamic(commonPage.CONFIRMATION), timeout)) {
					MobileDriver.tap(commonPage.lblContainDynamic(WITHDRAW), timeout, flowControl)
					commonPage.waitLoading()
					refID = actionWithS2uTabung(tabungMap);
				}
				return refID;
			case 'Remove':
				MobileDriver.verifyElementVisible(commonPage.lblDynamic(action +" "+ TABUNG), timeout, flowControl)
				if(tabungMap.get('status') == secure2uAuthorisationPage.UIApprove) {
					MobileDriver.tap(commonPage.btnDynamic(action), timeout, flowControl)
					delay(GlobalVariable.G_timeoutMiniShort)
					commonPage.tapContinueBtn()
				}
				else {
					MobileDriver.tap(commonPage.btnDynamic(KEEP), timeout, flowControl)
					return
				}
				commonPage.waitLoading()
				if(waitForElementPresent(commonPage.lblDynamic(commonPage.CONFIRMATION), GlobalVariable.G_timeoutShort)) {
					MobileDriver.tap(commonPage.lblContainDynamic(WITHDRAW), timeout, flowControl)
					commonPage.waitLoading()
					
					break;
				}
				
				secure2uAuthorisationPage.authorizeS2UV5BioPin(tabungMap.get('status'))
				refID = statusScreenPage.verifyStatusScreen(tabungMap.get('title'), tabungMap.get('description'), tabungMap.get('regex'))
				tapAndWait(commonPage.lblDynamic(commonPage.UIDonebtn))
				break;
			case 'ON OFF BOOSTER':
				handleOnOffBooster(tabungMap, timeout, flowControl);
				break;
			default:
				throw new IllegalArgumentException("Unsupported action: " + action);
		}

		return refID;
	}
	
	void joinGroupTabung(
		String invitationFrom, 
		String tabungName,
		FailureHandling flowControl = defaultFailureHandling) {
		
		'Select invitation'
		Mobile.tap(commonPage.lblContainDynamic('pending invitations'), GlobalVariable.G_timeoutMedium, FailureHandling.STOP_ON_FAILURE)
		commonPage.waitLoading()
		
		'Verify Pending Invitation Page'
		verifyElementVisible(commonPage.lblDynamic(
			"${invitationFrom} invited you to join the '${tabungName}' Tabung."),
			GlobalVariable.G_timeoutMedium,
			FailureHandling.STOP_ON_FAILURE
		)
		
		Mobile.tap(commonPage.lblSiblingDynamic(
			"${invitationFrom} invited you to join the '${tabungName}' Tabung."
			), GlobalVariable.G_timeoutMedium, FailureHandling.STOP_ON_FAILURE)
		
		'Verify confirmation page'
		verifyElements(
			[ invitationFrom,
				' has invited you', 
				'to join a Tabung!' ]
			, commonPage.lblDynamicPath(), GlobalVariable.G_timeoutLong)
		
		String getTabungName = MobileDriver.getText(commonPage.lblSiblingDynamic('Name'), GlobalVariable.G_timeoutLong, flowControl)
		Mobile.verifyMatch(getTabungName, tabungName, true, flowControl)	
		
	}
	
	String joinTabungConfirm(
		String status, 
		List statusTitle, 
		List statusDescription, 
		String regex, 
		FailureHandling flowControl = defaultFailureHandling
		) {
		String refID = ''
		'Accept or Reject invitation'
		switch(status) {
			case 'Approve':
				Mobile.tap(commonPage.lblDynamic('Join'), GlobalVariable.G_timeoutMedium, FailureHandling.STOP_ON_FAILURE)
				'Select account and navigate to Confirmation screen'
				this.selectAccountAndConfirm()
				
				'Confirm create tabung'
				Mobile.waitForElementPresent(commonPage.lblDynamic('Join Tabung'), GlobalVariable.G_timeoutLong, FailureHandling.STOP_ON_FAILURE)
				MobileDriver.tap(commonPage.lblDynamic('Join Tabung'), GlobalVariable.G_timeoutLong, FailureHandling.OPTIONAL)
				commonPage.waitLoading()
				
				'Enters PIN for S2U V5'
				commonPage.checkEnterS2UV5PIN(GlobalVariable.G_timeoutMiniShort)
					
				'Authorisation'
				secure2uAuthorisationPage.authorizeS2UV5BioPin(status)
				
				refID = statusScreenPage.verifyStatusScreen(statusTitle, statusDescription, regex)
				
				'Tap Done'
				commonPage.tapDoneBtn()
				
				return refID
				
			case 'Reject':
				Mobile.tap(commonPage.lblDynamic('Reject'), GlobalVariable.G_timeoutMedium, FailureHandling.STOP_ON_FAILURE)
				
			default:
			throw new IllegalArgumentException("Unsupported action: " + status);
		}
	}

	//for some actions need S2U to confirm and verify status screen
	String actionWithS2uTabung(Map tabungMap , String regex = REFERENCE_ID_7_9) {
		String refId;
		commonPage.waitLoading()
		'Enters PIN for S2U V5'
		commonPage.checkEnterS2UV5PIN(GlobalVariable.G_timeoutMiniShort)
		
		secure2uAuthorisationPage.authorize(tabungMap.get('status'))
		//verify status screen and reference ID
		refId = statusScreenPage.verifyStatusScreen(tabungMap.get('title'), tabungMap.get('description'), regex)
		tapAndWait(commonPage.lblDynamic(commonPage.UIDonebtn));
		return refId;
	}

	void handleEditAction(String result, int timeout, FailureHandling flowControl) {
		MobileDriver.tap(commonPage.lblDynamic(TAKE_A_PHOTO), timeout, flowControl);
		delay(GlobalVariable.G_timeoutMiniShort);
		MobileDriver.tap(commonPage.imgNoDynamic(3), timeout, flowControl);
		delay(GlobalVariable.G_timeoutMiniShort);
		MobileDriver.tap(this.btnTick(), timeout, flowControl);
		verifyElementVisible(commonPage.lblContainDynamic(result), timeout, flowControl);
	}

	void handleOnOffBooster(Map<String, String> tabungMap, int timeout = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		MobileDriver.tap(commonPage.imgNoDynamic(1), timeout, flowControl);
		commonPage.scrollToEnd()
		for (Map.Entry<String, String> entry : tabungMap.entrySet()) {
			Mobile.scrollToText(entry.getValue());
			MobileDriver.tap(this.btnToggel(entry.getValue()), timeout, flowControl);
			commonPage.waitLoading()
		}
	}
	void tapAndWait(TestObject element, int timeout =  GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		MobileDriver.tap(element, timeout, flowControl);
		commonPage.waitLoading();
	}
	String verifyStatusForTabungSolo(String status ,List statusTitle, List statusDescription, String regex, int timeout = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		switch(status) {
			case 'Approve':
				'Verify status screen'
				String ref = statusScreenPage.verifyStatusScreen(statusTitle, statusDescription, regex)
				'Tap Done'
				commonPage.tapDoneBtn()
				return ref;
			case 'Reject':
				verifyElementExist(commonPage.lblDynamic(statusTitle[0]), timeout, flowControl)
				commonPage.tapBack()
				break
		}
	}
	void verifyAndDeletePastTabung(String tabungName, Map tabungActionMap, int timeout = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		delay(GlobalVariable.G_timeoutShort)
		Mobile.swipe(100, 1500, 150, 0)
		Mobile.swipe(100, 1500, 150, 0)
		MobileDriver.scrollToEnd()
		MobileDriver.tap(commonPage.lblContainDynamic(VIEW_PAST_TABUNG), timeout, flowControl)
		delay(GlobalVariable.G_timeoutMiniShort)
		waitForElementPresent(commonPage.lblDynamic(PAST_TABUNG), timeout, flowControl)
		MobileDriver.scrollToEnd()
		
		String redfId = this.actionWithTabung(tabungName, 'Remove', tabungActionMap)

		
	}

	void scrollToPastTabung(String tabungName, int timeout = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		while(true) {
			if(waitForElementPresent(commonPage.lblContainDynamic(tabungName), timeout) && waitForElementPresent(commonPage.lblContainDynamic(CANCELLED), timeout))
				Mobile.scrollToText(tabungName)
		}
	}
	void turnOnOffToggleBoosters(String tabungName, int timeout = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		Mobile.scrollToText(tabungName);
		MobileDriver.tap(this.btnToggel(tabungName), timeout, flowControl);
	}
	void handleToggleBoosters(String tabungName,int timeout = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		MobileDriver.tap(commonPage.lblDynamic(SPARE_CHANGE), GlobalVariable.G_timeoutMedium, flowControl)
		commonPage.delay(GlobalVariable.G_timeoutMiniShort)
		MobileDriver.verifyElementVisible(commonPage.lblDynamic(SPARE_CHANGE), GlobalVariable.G_timeoutMedium, flowControl)
		Mobile.scrollToText(tabungName);
		MobileDriver.tap(this.btnToggel(tabungName), timeout, flowControl);
		commonPage.waitLoading()
		MobileDriver.tap(commonPage.lblDynamic(SAVE_CHANGES), GlobalVariable.G_timeoutMedium, flowControl)
		commonPage.waitLoading()

		MobileDriver.tap(commonPage.lblDynamic(GUILTY_PLEASURE), GlobalVariable.G_timeoutMedium, flowControl)
		commonPage.delay(GlobalVariable.G_timeoutMiniShort)
		Mobile.scrollToText(tabungName);
		MobileDriver.tap(this.btnToggel(tabungName), timeout, flowControl);
		commonPage.waitLoading()
		MobileDriver.tap(commonPage.lblDynamic(SAVE_CHANGES), GlobalVariable.G_timeoutMedium, flowControl)
		commonPage.waitLoading()

		Mobile.scrollToText(SCAN_SAVE);
		MobileDriver.tap(commonPage.lblDynamic(SCAN_SAVE), GlobalVariable.G_timeoutMedium, flowControl)
		Mobile.scrollToText(tabungName);
		MobileDriver.tap(this.btnToggel(tabungName), timeout, flowControl);
		MobileDriver.tap(commonPage.lblDynamic(SAVE_CHANGES), GlobalVariable.G_timeoutMedium, flowControl)
		commonPage.waitLoading()
	}

	void tapBackButton(int timeout = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		MobileDriver.tap(commonPage.imgNoDynamic(1), GlobalVariable.G_timeoutShort, flowControl)
		if(MobileDriver.waitForElementPresent(commonPage.imgNoDynamic(1), GlobalVariable.G_timeoutShort, flowControl)) {
			MobileDriver.tap(commonPage.imgNoDynamic(1), GlobalVariable.G_timeoutShort, flowControl)
		}
	}
}
