package com.vcb.mobile.internal.pages.base

import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.configuration.RunConfiguration
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.keyword.excel.ExcelKeywords
import com.vcb.assertions.BaseAssert
import com.vcb.common.MobileDriver
import com.vcb.utils.DateUtils
import com.vcb.utils.StringUtils
import com.vcb.utils.TouchActionUtils

import internal.GlobalVariable
import io.appium.java_client.AppiumDriver
//import io.appium.java_client.PushesFiles
import groovy.json.JsonSlurperClassic


public abstract class CommonPage extends BasePage {

	abstract boolean scrollToTestObject(TestObject grid, TestObject findObj, ScrollDirectionEnum direction, int timeout, FailureHandling flowControl, boolean flexibleDeivce = false)
	public static AppiumDriver driver = MobileDriverFactory.getDriver()
	private static final String INDEX_DROPDOWN = '_INDEX_'
	//any text
	abstract TestObject lblDynamic(String text)
	abstract TestObject lblTextViewDynamic(String text, def index = 1)
	abstract TestObject lblTextViewContain(String text, def index = 1)
	abstract TestObject lblDynamicTextLast(String text)
	abstract TestObject lblDynamicNo(String text, int index = 1)
	abstract TestObject lblContainDynamic(String text)
	abstract TestObject lblContainDynamicNo(String text, int index = 1)
	abstract TestObject lblDynamicList(List text)
	abstract TestObject btnTick()
	abstract TestObject btnRemove()
	abstract TestObject txtPasswordRegister()
	abstract TestObject lblNormalizeSpace(String text)
	abstract TestObject lblDropDownSelect(String text, def index = 1)
	abstract String lblNormalizeSpacePath()
	abstract TestObject lblTextMatches(String text)
	abstract TestObject lblTextIgnoreCaseNo(String text, def index = 1)
	abstract TestObject lblRMNo(def index)
	abstract TestObject lblSiblingOneGroup(String text)
	abstract TestObject frmHorizontalScrollNo(def text)
	abstract TestObject frmScrollView(def index = 1)
	abstract TestObject frmWebViewScrollNo(int index)
	abstract TestObject btnRadioNo(int index)
	abstract TestObject btnRadioImageViewStyle(String text)

	abstract TestObject lblSiblingDynamic(String text, int index = 1)
	abstract TestObject txtEditNo(int index = 1)
	abstract TestObject imgNoDynamic(def index)
	abstract TestObject imgNoSimpleDynamic(def index)
	abstract TestObject lblTextNo(int index = 1)
	abstract TestObject lblStartsWith(String text)
	abstract TestObject lblAnyText(String text)
	abstract TestObject imgLoading()
	abstract TestObject btnDynamic(String text)
	abstract TestObject btnTextDynamic(String text)
	abstract TestObject txtSiblingDynamic(String text)
	abstract TestObject txtEditTextDynamic(String text)
	abstract TestObject txtEditText(String text)
	abstract TestObject	imgSiblingGroup(String text)
	abstract TestObject imgArrowDownDropdown(String text, Object index)
	abstract TestObject	imgSibling(String text)
	abstract TestObject txtSiblingGroup(String label)
	abstract TestObject lblLineValue()
	abstract TestObject frmScrollViewGroupNo(int scrollIndex, int groupNo)
	abstract TestObject grpPrecedingSibling(String text)
	abstract TestObject frmScrollDropdown()
	abstract TestObject lblHomeOrAgree()
	abstract TestObject lblOtherRMNo(int index)
	abstract TestObject lblSiblingGroup(String lable)
	abstract TestObject grpContainsPrecedingSibling(String text)
	abstract TestObject lblDynamicId(String id)

	//UI
	String UIDonebtn = 'Done'
	String UIEnterYourPassword = 'Enter your password'
	String UI6DigitPIN = '6-digit PIN'
	String UI6DigitBioPIN = '6-digit VCB app PIN'
	String UIContinue = 'Continue'
	String UINext = 'Next'
	String UISaveAndContinue = 'Save and Continue'
	String UIRESTART = 'RESTART'
	String UIViewAllAccounts = 'View All Accounts'
	String UINetworkLogs = 'NETWORK LOGS'
	String UICancel = 'Cancel'
	String CLEAR_DATA_CACHE = 'CLEAR DATA & CACHE'
	String CONFIRMATION = 'Confirmation'
	String YES = 'YES'
	String TypeToSearch = 'Type to search'
	String UISubmit = 'Submit'
	static final String DEV_OPTIONS = 'DEV OPTIONS'
	static final String SWITCH_ENVIRONMENT = 'SWITCH ENVIRONMENT'
	static final String SWITCH = 'Switch'
	static final String AGREE = 'Agree'
	static final String HOME = 'Home'
	static final String HELLO = 'Hello,'
	static final String FAVOURITE = 'Favourites'
	static final String FILTER_URL = 'Filter URLs'

	String lblDynamicPath() {
		return 'Object Repository/Mobile/VCB/Dynamic/lblDynamic'
	}

	String lblTextMatchesPath() {
		return 'Object Repository/Mobile/VCB/Dynamic/lblTextMatches'
	}

	String lblContainDynamicPath() {
		return 'Object Repository/Mobile/VCB/Dynamic/lblContainDynamic'
	}

	String lblContainDynamicNoPath() {
		return 'Object Repository/Mobile/VCB/Dynamic/lblContainDynamicNo'
	}

	/**
	 * getXpathTextViewByNo: get xpath text view
	 * @return example //android.widget.TextView[1}
	 */
	abstract String getXpathTextViewByNo(def index)

	/**
	 * tapText: tap text
	 * @return
	 */
	void tapText(String text, int timeout, FailureHandling flowControl = defaultFailureHandling) {
		tap(this.lblDynamic(text), timeout, flowControl)
	}

	/**
	 * getText: get text
	 * @return
	 */
	String getText(String text, int timeout, FailureHandling flowControl = defaultFailureHandling) {
		getText(this.lblDynamic(text), timeout, flowControl)
	}

	/**
	 * verifyTextVisible
	 * @return
	 */
	boolean verifyTextVisible(String ui, int timeout, FailureHandling flowControl = defaultFailureHandling) {
		return MobileDriver.verifyElementVisible(this.lblDynamic(ui), GlobalVariable.G_timeoutLong, flowControl)
	}

	/**
	 * getTextBySubText: Retrieves the text of an element that contains the specified subtext.
	 * @return
	 */
	String getTextBySubText(String text) {
		if(verifyElementVisible(this.lblContainDynamic(text), GlobalVariable.G_timeoutMedium)) {
			return getText(this.lblContainDynamic(text), GlobalVariable.G_timeoutShort)
		}
	}

	/**
	 * enterNO: enter Number in number screen
	 * @return
	 */
	void enterNO(String pinNO, boolean isClick = true, String verifyText = '', int delayAfterTick = GlobalVariable.G_timeoutShort, FailureHandling flowControl = FailureHandling.STOP_ON_FAILURE) {
		info("PIN: ${pinNO}")
		if (verifyText.isEmpty() || verifyElementVisible(this.lblDynamic(verifyText), GlobalVariable.G_timeoutLong, FailureHandling.OPTIONAL)) {
			info("verifyText: ${verifyText}")
			def length = pinNO.length()

			for (def i = 0; i < length; i++) {

				def tapNo = pinNO[i]

				MobileDriver.tap(this.lblDynamic(tapNo), GlobalVariable.G_timeoutLong, flowControl)
			}
		}

		Mobile.takeScreenshot()
		if(isClick) {
			MobileDriver.tap(this.btnTick(), GlobalVariable.G_timeoutLong, flowControl)
		}
		if(delayAfterTick > 0) {
			Mobile.delay(delayAfterTick)
		}
	}

	/**
	 * enterNO: enter Number in number screen
	 * @return
	 */
	void enterNOByIndex(String pinNO, boolean isClick = true, String verifyText = '', int delayAfterTick = GlobalVariable.G_timeoutShort, FailureHandling flowControl = FailureHandling.STOP_ON_FAILURE) {
		info("PIN: ${pinNO}")
		if (verifyText.isEmpty() || verifyElementVisible(this.lblDynamic(verifyText), GlobalVariable.G_timeoutLong, FailureHandling.OPTIONAL)) {
			info("verifyText: ${verifyText}")
			def length = pinNO.length()
			for (def i = 0; i < length; i++) {
				def tapNo = pinNO[i]
				MobileDriver.tap(this.lblDynamicNo(tapNo,2), GlobalVariable.G_timeoutLong, flowControl)
			}
		}
		Mobile.takeScreenshot()
		if(isClick) {
			MobileDriver.tap(this.btnTick(), GlobalVariable.G_timeoutLong, flowControl)
		}
		if(delayAfterTick > 0) {
			Mobile.delay(delayAfterTick)
		}
	}

	/**
	 * removeEnterNo: Clear all values
	 * @return
	 */
	void removeEnterNo(int noTimes) {
		for(int i = 0; i< noTimes; i++) {
			MobileDriver.tap(this.btnRemove(), GlobalVariable.G_timeoutLong)
		}
	}

	/**
	 * tapDoneBtn: Tap Done Button
	 * @return
	 */
	void tapDoneBtn(FailureHandling flowControl = defaultFailureHandling) {
		if(Mobile.waitForElementPresent(this.lblDynamic(UIDonebtn), GlobalVariable.G_timeoutLong)) {
			MobileDriver.tap(this.lblDynamic(UIDonebtn), GlobalVariable.G_timeoutShort, flowControl)
		}
	}

	/**
	 * tapCancelBtn: Tap Cancel Button
	 * @return
	 */
	void tapCancelBtn(FailureHandling flowControl = defaultFailureHandling) {
		MobileDriver.tap(this.lblDynamic(UICancel), GlobalVariable.G_timeoutShort, flowControl)
	}

	/**
	 * tapContinueBtn: Tap Continue Button
	 * @return
	 */
	void tapContinueBtn(FailureHandling flowControl = defaultFailureHandling) {
		MobileDriver.tap(this.lblDynamic(UIContinue), GlobalVariable.G_timeoutShort, flowControl)
	}

	/**
	 * tapNextBtn: Tap Next Button
	 * @return
	 */
	void tapNextBtn(FailureHandling flowControl = defaultFailureHandling) {
		MobileDriver.tap(this.lblDynamic(UINext), GlobalVariable.G_timeoutShort, flowControl)
	}

	/**
	 * tapSubmitButton: Tap Submit Button
	 * @return
	 */
	void tapSubmitBtn(FailureHandling flowControl = defaultFailureHandling) {
		MobileDriver.tap(this.lblDynamic(UISubmit), GlobalVariable.G_timeoutShort, flowControl)
	}

	/**
	 * tapSaveAndContinue: Tap Save And Continue Button
	 * @return
	 */
	void tapSaveAndContinue(FailureHandling flowControl = defaultFailureHandling) {
		MobileDriver.tap(this.lblDynamic(UISaveAndContinue), GlobalVariable.G_timeoutShort, flowControl)
	}

	/**
	 * tapTickBtn: Tap Tick Button
	 * @return
	 */
	void tapTickBtn() {
		MobileDriver.tap(this.btnTick(), GlobalVariable.G_timeoutShort)
	}

	/**
	 * selectTab: select name at screen has tab
	 * @return
	 */
	void selectTab(TestObject fraScrollTab, String name, int timeout, FailureHandling flowControl = defaultFailureHandling) {
		boolean findResult = this.scrollToTestObject(fraScrollTab, lblDynamic(name), ScrollDirectionEnum.RIGHT, timeout, flowControl)

		if(findResult) {
			MobileDriver.tap(lblDynamic(name), timeout, flowControl)
		}
	}

	/**
	 * verifyFavouriteExist: verify Favourite Exist
	 * @return
	 */
	void verifyFavouriteExist(String favouriteName, FailureHandling flowControl = defaultFailureHandling) {
		'Select Search'
		MobileDriver.tapEleByTestObject(this.imgSiblingGroup(FAVOURITE))
		'Enter text'
		MobileDriver.setTextNoObjAppium(favouriteName)
		MobileDriver.takeScreenshot()
		'Verify Favourite Exisit'
		MobileDriver.verifyElementVisible(this.lblDynamicNo(favouriteName, 2), GlobalVariable.G_timeoutMedium)
	}

	/**
	 * swipe: the same katalon Mobile.swipe
	 * @return
	 */
	abstract boolean swipe(int startX, int startY, int endX, int endY)

	/**
	 * swipe: by touch Action
	 * @return
	 */
	abstract boolean swipeByW3CAction(int startX, int startY, int endX, int endY)

	/**
	 * slider: change value slider
	 * @return
	 */
	abstract boolean slider(TestObject slider, TestObject sliderPoint, int min, int max, int value)

	/**
	 * sliderToDistance: change value to Distance
	 * @return
	 */
	abstract boolean sliderToDistance(TestObject sliderPoint, int distance)

	/**
	 * swipeText: swipe from txtStart to txtEnd
	 * @return
	 */
	void swipeText(String txtStart,String txtEnd, FailureHandling flowControl = defaultFailureHandling) {
		this.swipeByW3CAction(
				Mobile.getElementLeftPosition(this.lblDynamic(txtStart), GlobalVariable.G_timeoutLong, flowControl),
				Mobile.getElementTopPosition(this.lblDynamic(txtStart), GlobalVariable.G_timeoutLong, flowControl),
				Mobile.getElementLeftPosition(this.lblDynamic(txtEnd), GlobalVariable.G_timeoutLong, flowControl),
				Mobile.getElementTopPosition(this.lblDynamic(txtEnd), GlobalVariable.G_timeoutLong, flowControl))
	}

	/**
	 * tomorrowDatePicker: select tomorrow value in DatePicker
	 * @return
	 */
	def tomorrowDatePicker(FailureHandling flowControl = defaultFailureHandling) {
		def tomorrowDate = DateUtils.getCurrentDateFormat()
		String[] tomorrow = tomorrowDate.split(' ')

		if(tomorrow[0] == '1') {
			this.swipeText('S', 'M')
		}

		// Check if tomorrow[0] is less than or equal to 15
		if (Integer.parseInt(tomorrow[0]) <= 15) {
			Mobile.tap(this.lblDynamicNo(tomorrow[0], 1), GlobalVariable.G_timeoutLong, flowControl)
		} else {
			Mobile.tap(this.lblDynamicTextLast(tomorrow[0]), GlobalVariable.G_timeoutLong, flowControl)
		}


		MobileDriver.tap(this.lblDynamic(UIDonebtn), GlobalVariable.G_timeoutLong, flowControl)
	}


	/**
	 * convertAmountToNumber: convert Amount to Number (RM 3,222,33 -> 322233)
	 * @return
	 */
	static BigInteger convertAmountToNumber(String amount, boolean includeFraction = true) {
		if(includeFraction) {
			return (amount.replaceAll('RM','').replaceAll(' ','').replaceAll(',','').replaceAll('\\.','')).toBigInteger()
		}
		return ((amount.replaceAll('RM','').split('\\.'))[0].replaceAll(' ','').replaceAll(',','')).toBigInteger()
	}

	/**
	 * checkEnterPassword: enter password if password screen is display
	 * @return
	 */
	public boolean checkEnterPassword(String password, int timeout = GlobalVariable.G_timeoutShort, FailureHandling flowControl = defaultFailureHandling) {
		if(waitForElementPresent(this.lblDynamic(UIEnterYourPassword), timeout, FailureHandling.OPTIONAL)) {
			waitForElementPresent(this.txtEditNo(1), GlobalVariable.G_timeoutShort, FailureHandling.OPTIONAL)
			MobileDriver.setEncryptedText(this.txtEditNo(1), password, GlobalVariable.G_timeoutLong)
			MobileDriver.tap(this.lblDynamic(UIContinue), GlobalVariable.G_timeoutLong)
			'wait login'
			this.waitLoading(GlobalVariable.G_timeoutLong)
		}
	}

	/**
	 * checkEnterPIN: enter PIN if password screen is display
	 * @return
	 */
	public void checkEnterPIN(int timeout = GlobalVariable.G_timeoutShort, FailureHandling flowControl = defaultFailureHandling) {
		if(waitForElementPresent(this.lblDynamic(UI6DigitBioPIN), timeout, FailureHandling.OPTIONAL)) {
			this.enterNO(GlobalVariable.G_pinCode, false)
			Mobile.delay(GlobalVariable.G_timeoutShort)
		}
	}

	/**
	 * checkEnterPIN: enter 6-PIN Digit for S2U V5
	 * @return
	 */
	public void checkEnterS2UV5PIN(int timeout = GlobalVariable.G_timeoutShort, FailureHandling flowControl = defaultFailureHandling) {
		if(waitForElementPresent(this.lblContainDynamic(UI6DigitBioPIN), timeout, FailureHandling.OPTIONAL)) {
			this.enterNO(GlobalVariable.G_pinCode, false)
			Mobile.takeScreenshot()			
			Mobile.delay(GlobalVariable.G_timeoutShort)
		}
	}
	/**
	 * Enter PIN: enter 6-PIN Digit for S2U V6
	 * @return
	 */
	public void enterS2UV6PIN(int timeout = GlobalVariable.G_timeoutShort, FailureHandling flowControl = defaultFailureHandling) {
		Mobile.takeScreenshot()
		if(waitForElementPresent(this.lblContainDynamic(UI6DigitBioPIN), timeout, FailureHandling.OPTIONAL)) {
			this.enterNOByIndex(GlobalVariable.G_pinCode, false, "")
		}
		Mobile.delay(GlobalVariable.G_timeoutShort)
	}
	/**
	 * openDevOption: Open DevOption
	 * @return
	 */
	private boolean openDevOption(String name) {
		TouchActionUtils.tapTwoFingerW3CAction()
		try {
			'Not use wait ele slow script'
			MobileDriver.tap(this.lblDynamic(name), GlobalVariable.G_timeoutLong, FailureHandling.STOP_ON_FAILURE) //not change flowControl
		}catch(ex) {
			if(MobileDriver.waitForElementNotPresent(this.lblDynamic(DEV_OPTIONS), GlobalVariable.G_timeoutMedium, FailureHandling.OPTIONAL)) {
				'Close Popup after open devoption'
				if(MobileDriver.waitForElementPresent(this.lblDynamic(UICancel), GlobalVariable.G_timeoutMedium, FailureHandling.OPTIONAL)) {
					MobileDriver.tap(this.lblDynamic(UICancel), GlobalVariable.G_timeoutMedium, FailureHandling.OPTIONAL)
				}else {
					MobileDriver.tapAndLog(this.imgNoDynamic(1), GlobalVariable.G_timeoutMedium, 'Icon Back' ,FailureHandling.OPTIONAL)
				}
				TouchActionUtils.tapTwoFingerW3CAction()
			}
			if(MobileDriver.waitForElementPresent(this.lblDynamic(DEV_OPTIONS), GlobalVariable.G_timeoutLong, FailureHandling.OPTIONAL)) {
				MobileDriver.tap(this.lblDynamic(name), GlobalVariable.G_timeoutLong, FailureHandling.STOP_ON_FAILURE)
				return true
			}
			return false
		}
		return true
	}
	/**
	 * selectSwitchEnv: Select Switch Environment
	 * @return
	 */
	private boolean selectSwitchEnv(String name) {
		try {
			'Not use wait ele slow script'
			MobileDriver.tap(this.lblDynamic(name), GlobalVariable.G_timeoutLong, FailureHandling.STOP_ON_FAILURE) //not change flowControl
		}catch(ex) {
			if(MobileDriver.waitForElementNotPresent(this.lblDynamic(DEV_OPTIONS), GlobalVariable.G_timeoutMedium, FailureHandling.OPTIONAL)) {
				'Close Popup after open devoption'
				if(MobileDriver.waitForElementPresent(this.lblDynamic(UICancel), GlobalVariable.G_timeoutMedium, FailureHandling.OPTIONAL)) {
					MobileDriver.tap(this.lblDynamic(UICancel), GlobalVariable.G_timeoutMedium, FailureHandling.OPTIONAL)
				}else {
					MobileDriver.tapAndLog(this.imgNoDynamic(1), GlobalVariable.G_timeoutMedium, 'Icon Back' ,FailureHandling.OPTIONAL)
				}
				TouchActionUtils.tapTwoFingerW3CAction()
			}
			if(MobileDriver.waitForElementPresent(this.lblDynamic(DEV_OPTIONS), GlobalVariable.G_timeoutLong, FailureHandling.OPTIONAL)) {
				MobileDriver.tap(this.lblDynamic(name), GlobalVariable.G_timeoutLong, FailureHandling.STOP_ON_FAILURE)
				return true
			}
			return false
		}
		return true
	}
	boolean isFirstScreen() {
		if(MobileDriver.waitForElementPresent(this.lblDynamic(AGREE), GlobalVariable.G_timeoutShort, FailureHandling.OPTIONAL)) {
			return true
		}
		return false
	}

	boolean isHomeOrOnboarding() {
		if(MobileDriver.waitForElementPresent(this.lblHomeOrAgree(), GlobalVariable.G_timeoutShort, FailureHandling.OPTIONAL)) {
			String viewAllAccounts = MobileDriver.getText(this.lblTextNo(7), GlobalVariable.G_timeoutShort, FailureHandling.OPTIONAL)
			if(viewAllAccounts == UIViewAllAccounts) {
				return true
			}

			String xml = MobileDriver.driver.getPageSource()
			String agreeXpath = MobileDriver.getLocator(this.lblDynamic(AGREE))

			if(MobileDriver.findElementByXml(agreeXpath, xml)) {
				return true
			}

			info('Exception Page Include Home Page')
			return false
		}
		return false
	}
	/**
	 * Switch Environment on Vcb
	 * @return Environment Name
	 */
	public String getEnvNameOnMae(String projectName, String fileName, FailureHandling flowControl = defaultFailureHandling) {
		String filePath = System.getProperty('user.dir') + File.separator + fileName
		def sheet = ExcelKeywords.getExcelSheet(filePath, 0)
		int lastRow = sheet.getLastRowNum()
		Console.println('lastRow: ' +  lastRow)
		for (int i = 1; i <= lastRow; i++) {
			def index = i + 1;
			String vProjectName = ExcelKeywords.getCellValueByAddress(sheet, 'B' + index.toString())
			if(vProjectName == projectName) {
				String vMaeEnvironment = ExcelKeywords.getCellValueByAddress(sheet, 'C' + index.toString())
				return vMaeEnvironment
			}
		}
		return null
	}
	/**
	 * restartApp: Click Restart in DevOption
	 * @return
	 */
	public void restartApp(FailureHandling flowControl = defaultFailureHandling) {
		if(GlobalVariable.G_isProduction) {
			return
		}
		if(this.isHomeOrOnboarding()) {
			return
		}
		'Restart App'
		openDevOption(UIRESTART)
		MobileDriver.delay(10)
		'Wait HomePage is display'
		this.waitHomePagePresentAfterRestart()
	}

	/**
	 * clearDataAndCache: Click DataAndCache in DevOption
	 * @return
	 */
	public void clearDataAndCache(FailureHandling flowControl = defaultFailureHandling) {
		if(GlobalVariable.G_isProduction) {
			return
		}
		if(waitForElementPresent(this.lblDynamic(AGREE), GlobalVariable.G_timeoutShort, FailureHandling.OPTIONAL)) {
			return
		}
		try {
			'Open Dev option and select CLEAR_DATA_CACHE'
			openDevOption(CLEAR_DATA_CACHE)
		}catch(ex) {
			'If cannot open Dev option:'
			if(MobileDriver.waitForElementPresent(this.lblDynamic(UICancel), GlobalVariable.G_timeoutMedium, FailureHandling.OPTIONAL)) {
				'Select Cacncel'
				MobileDriver.tap(this.lblDynamic(UICancel), GlobalVariable.G_timeoutMedium, FailureHandling.OPTIONAL)
			}else {
				'Select Back'
				MobileDriver.tapAndLog(this.imgNoDynamic(1), GlobalVariable.G_timeoutMedium, 'Icon Back' ,FailureHandling.OPTIONAL)
			}
			try {
				'Open Dev option and select CLEAR_DATA_CACHE'
				openDevOption(CLEAR_DATA_CACHE)
			}catch(e) {
				handleError(e, FailureHandling.OPTIONAL)
			}
		}
		'Verify CONFIRMATION is exist'
		MobileDriver.verifyElementExist(this.lblDynamic(this.CONFIRMATION), GlobalVariable.G_timeoutMedium)
		'Select YES'
		MobileDriver.tap(this.lblDynamic(this.YES), GlobalVariable.G_timeoutMedium)
		MobileDriver.delay(10)
	}

	/**
	 * networkLogs: take screenshot NetworkLogs in DevOption
	 * @return
	 */
	public void networkLogs() {
		if(GlobalVariable.G_isProduction) {
			return
		}
		try {
			'wait to loading done'
			this.waitLoading(GlobalVariable.G_timeoutLong, FailureHandling.OPTIONAL)
		}catch(ex) {
			'Ignore error wait hide loading Icon'
		}
		'open networklog and screenshort'
		openDevOption(UINetworkLogs)

		MobileDriver.takeScreenshot()
	}

	/**
	 * networkLogs: take screenshot NetworkLogs and restart in DevOption
	 * @return
	 */
	public void networkLogsAndRestartApp(FailureHandling flowControl = defaultFailureHandling) {
		'Take screenshort NetworkLogs'
		this.networkLogs()
		'Back Network Logs'
		MobileDriver.tapAndLog(this.imgNoDynamic(1), GlobalVariable.G_timeoutLong, 'Back Icon', FailureHandling.OPTIONAL)
		'Tap Restart'
		MobileDriver.tap(this.lblDynamic(UIRESTART), GlobalVariable.G_timeoutLong, FailureHandling.OPTIONAL)
		MobileDriver.delay(10)
		'Wait HomePage is display'
		this.waitHomePagePresentAfterRestart()
	}

	/**
	 * switchEnv: 
	 * @return
	 */
	public void switchEnvironment(String envName, FailureHandling flowControl = defaultFailureHandling) {
		if(GlobalVariable.G_isProduction) {
			return
		}
		'Tap Two Finger'
		TouchActionUtils.tapTwoFingerW3CAction()
		'Get Current Environment Name'
		'Select Switch Environment'
		selectSwitchEnv(SWITCH_ENVIRONMENT)
		'Select Environment Name'
		if (envName) {
			MobileDriver.tap(this.lblDynamic(envName), GlobalVariable.G_timeoutMedium, flowControl)
			Mobile.delay(GlobalVariable.G_timeoutMiniShort)
		}

		'Select Switch Button'
		MobileDriver.tap(this.lblDynamic(SWITCH), GlobalVariable.G_timeoutMedium, flowControl)
		'Verify Agree'
		if(MobileDriver.waitForElementPresent(this.lblDynamic(AGREE), GlobalVariable.G_timeoutMedium, FailureHandling.OPTIONAL)) {
			MobileDriver.tap(this.lblDynamic(AGREE), GlobalVariable.G_timeoutLong)
			Mobile.delay(GlobalVariable.G_timeoutMiniShort)
			this.waitLoading();
			'Tap Two Finger'
			TouchActionUtils.tapTwoFingerW3CAction()
		}
		'Take Screenshot Environment Changed'
		Mobile.takeScreenshot()
		if(MobileDriver.waitForElementPresent(this.lblDynamic(DEV_OPTIONS), GlobalVariable.G_timeoutShort, FailureHandling.OPTIONAL)) {
			MobileDriver.tap(this.imgNoDynamic(3), GlobalVariable.G_timeoutMedium, flowControl)
		}
	}

	/**
	 * switchEnv:
	 * @return
	 */
	public void switchEnvironmentInTestListener(String envName, FailureHandling flowControl = defaultFailureHandling) {
		if(MobileDriver.waitForElementPresent(this.lblDynamic(AGREE), GlobalVariable.G_timeoutLong)) {
			MobileDriver.tap(this.lblDynamic(AGREE), GlobalVariable.G_timeoutLong)
			Mobile.delay(GlobalVariable.G_timeoutMiniShort)
			this.waitLoading();
		}
		this.switchEnvironment(envName)
	}

	/**
	 * waitHomePagePresentAfterRestart: Wait Home screen display(Already logged in)
	 * @return
	 */
	void waitHomePagePresentAfterRestart(FailureHandling flowControl = defaultFailureHandling) {
		if(!MobileDriver.waitForElementPresent(this.lblDynamic(UIViewAllAccounts), GlobalVariable.G_timeoutLong, FailureHandling.OPTIONAL)) {
			'Active App if restart App is crashed'
			MobileDriver.driver.activateApp(GlobalVariable.G_appId)
			MobileDriver.delay(GlobalVariable.G_timeoutShort + GlobalVariable.G_timeoutMiniShort)
			MobileDriver.tap(this.lblDynamic(HOME), GlobalVariable.G_timeoutShort)
		}
	}

	/**
	 * verifyDeviceNotification: Verify Title, description in Push Notification
	 * @return
	 */
	boolean verifyDeviceNotification(String title, String startMessage, String message, int timeOut = GlobalVariable.G_timeoutShort, FailureHandling fCtrl = defaultFailureHandling) {
		Mobile.openNotifications()
		Mobile.delay(GlobalVariable.G_timeoutMiniShort)
		try {
			Mobile.takeScreenshot()
			'Verify notification title'
			MobileDriver.verifyElementExist(this.lblAnyText(title), GlobalVariable.G_timeoutLong)

			'Get notification message'
			String bodyPN = Mobile.getText(this.lblStartsWith(startMessage), GlobalVariable.G_timeoutLong)

			'Verify notification message'
			Mobile.verifyMatch(bodyPN, message, true)
		}catch(ex) {
			Mobile.pressBack()
			handleError(ex, fCtrl)
		}
		Mobile.pressBack()
	}

	/**
	 * tapBack: tap Back Button
	 * @return
	 */
	void tapBack(int timeout = GlobalVariable.G_timeoutMedium, FailureHandling failureHandling = defaultFailureHandling) {
		MobileDriver.tap(this.imgNoDynamic(1), timeout, failureHandling)
	}

	/**
	 * waitLoading: Wait until the loading icon disappears
	 * @return
	 */
	void waitLoading(int timeout = GlobalVariable.G_timeoutLong, FailureHandling flowControl = defaultFailureHandling) {
		info "Wait Loading"
		int i = 0
		while(verifyElementExist(this.imgLoading(), GlobalVariable.G_timeoutShort, FailureHandling.OPTIONAL)) {
			println 'element load exist'
			i = i +1
			delay(1)
			if (i > 10) {
				break
			}
		}
	}

	/**
	 * tapButton: tapButton by text
	 * @return
	 */
	void tapButton(String UIText, int timeout = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		Mobile.verifyElementVisible(btnDynamic(UIText), timeout, FailureHandling.OPTIONAL)
		Mobile.tap(btnDynamic(UIText), timeout)
	}

	/**
	 * swipeToObject: swipe to TestObject
	 * @return
	 */
	void swipeToObject(TestObject to, int timeout = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		while(!(MobileDriver.waitForElementPresent(to, timeout, FailureHandling.OPTIONAL))){
			int w = Mobile.getDeviceWidth()
			int h = Mobile.getDeviceHeight()

			int startX = (w/2) as int
			int startY = (h/2) as int
			int endY = (h/5) as int
			info("Swipt from (${startX.toString()}, ${startY.toString()}) to (${startX.toString()}, ${endY.toString()})")
			swipeByW3CAction(startX, startY, startX, endY)
		}
	}

	/**
	 * swipeToTestObject: swipe to TestObject
	 * scrollPosition is swipe position include 4 value
	 * @return
	 */
	boolean swipeToTestObject(TestObject to, List<Integer> scrollPosition = [], boolean isBothDirections = true, int timeout = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		int startX
		int startY
		int endX
		int endY

		if(waitForElementPresent(to, timeout, FailureHandling.OPTIONAL)) {
			return true;
		}
		if(scrollPosition.isEmpty()) {
			scrollPosition = MobileDriver.getPositionScrollDownDefault()
		}

		startX = scrollPosition.get(0)
		startY = scrollPosition.get(1)
		endX = scrollPosition.get(2)
		endY = scrollPosition.get(3)
		'Scroll down'
		def prevPageSource = MobileDriver.driver.getPageSource()
		def currentPageSource = ""
		while(true) {
			this.swipeByW3CAction(startX, startY, endX, endY)
			currentPageSource = MobileDriver.driver.getPageSource()
			if(prevPageSource.equals(currentPageSource)) {
				break
			}
			prevPageSource = currentPageSource

			if(waitForElementPresent(to, timeout)) {
				return true;
			}
		}

		if(!isBothDirections) {
			handleError("System Error: Unable to scroll to " + MobileDriver.toStringAndLocatorTestObject(to), flowControl)
			return false
		}

		'Scroll up'
		prevPageSource = MobileDriver.driver.getPageSource()
		currentPageSource = ""
		while(true) {
			this.swipeByW3CAction(endX, endY, startX, startY)
			currentPageSource = MobileDriver.driver.getPageSource()
			if(prevPageSource.equals(currentPageSource)) {
				break
			}
			prevPageSource = currentPageSource

			if(waitForElementPresent(to, timeout)) {
				return true;
			}
		}

		handleError("System Error: Unable to scroll to" + MobileDriver.toStringAndLocatorTestObject(to), flowControl)
		return false
	}

	/**
	 * scrollToTop
	 * @return
	 */
	abstract void scrollToTop(int startX, int startY, int endX, int endY)

	/**
	 * getAllItemsListView: get all items in listView
	 * @return
	 */
	abstract public List<Object> getAllItemsListView(List<String> xpaths, TestObject groupsTO, int indexCheckDuplicate = 0, List<Integer> scrollPosition = [5, 1000, 5, 10])

	/**
	 * enterTextFieldValue: enter text in input base on label
	 * @return
	 */
	void enterTextFieldValue(String fieldName, String value, int timeout = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		if(!value.isBlank()) {
			swipeToObject(txtSiblingDynamic(fieldName), timeout, FailureHandling.OPTIONAL)
			Mobile.verifyElementExist(txtSiblingDynamic(fieldName), timeout, FailureHandling.CONTINUE_ON_FAILURE)
			Mobile.clearText(txtSiblingDynamic(fieldName), timeout, FailureHandling.OPTIONAL)
			Mobile.setText(txtSiblingDynamic(fieldName), value, timeout, FailureHandling.STOP_ON_FAILURE)
			Mobile.hideKeyboard(FailureHandling.OPTIONAL)
		}
	}

	/**
	 * selectDropdownSiblingGroupItem: select value in dropdown
	 * @return
	 */
	void selectDropdownSiblingGroupItem(String fieldName, String value, int timeout = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		swipeToTestObject(imgArrowDownDropdown(fieldName, 1), [], false, timeout, flowControl)
		if(value.isBlank() || value == "" || value == 'defaultValue'){
			selectDropdownSiblingGroupItemWithDefaultValue(fieldName, timeout, flowControl)
			return
		}
		Mobile.verifyElementExist(imgSiblingGroup(fieldName), timeout, FailureHandling.CONTINUE_ON_FAILURE)
		info("Tap on drop-down ${fieldName}")
		Mobile.tap(imgSiblingGroup(fieldName), timeout, flowControl)
		if(!MobileDriver.waitForElementPresent(this.lblDynamic(UIDonebtn), GlobalVariable.G_timeoutShort, flowControl)) {
			info("Tap on drop-down again ${fieldName}")
			MobileDriver.tap(imgSiblingGroup(fieldName), timeout, FailureHandling.OPTIONAL)
		}

		info("Search for ${value}")
		if(!(MobileDriver.waitForElementPresent(lblNormalizeSpace(value), timeout, FailureHandling.OPTIONAL))){
			info("Scroll to ${value}")
			Mobile.scrollToText(value, FailureHandling.OPTIONAL)
		}

		info("Tap on ${value}")
		Mobile.tap(lblNormalizeSpace(value), timeout, flowControl)
		info("Tap Done to close drop-down")
		Mobile.tap(lblDynamic(UIDonebtn), GlobalVariable.G_timeoutLong)
	}

	/**
	 * selectDropdownSiblingGroupItemWithDefaultValue: select default value in Dropdown
	 * @return
	 */
	void selectDropdownSiblingGroupItemWithDefaultValue(String fieldName, int timeout = GlobalVariable.G_timeoutLong, FailureHandling flowControl = defaultFailureHandling) {
		Mobile.verifyElementExist(imgSiblingGroup(fieldName), timeout, flowControl)
		info("Tap on drop-down ${fieldName}")
		Mobile.tap(imgSiblingGroup(fieldName), timeout, flowControl)

		info("Tap Done to close drop-down")
		MobileDriver.tap(lblDynamic(UIDonebtn), timeout, flowControl)
	}

	/**
	 * selectDropdownSiblingGroupItemHasIndex: select value in Dropdown has Index Element
	 * @return
	 */
	void selectDropdownTestObject(TestObject to, String value, FailureHandling flowControl = defaultFailureHandling) {
		swipeToTestObject(to, [], false, GlobalVariable.G_timeoutMedium, flowControl)

		info("Tap on drop-down ${to}")
		Mobile.tap(to, GlobalVariable.G_timeoutMedium, flowControl)
		if(!MobileDriver.waitForElementPresent(this.lblDynamic(UIDonebtn), GlobalVariable.G_timeoutShort, flowControl)) {
			info("Tap on drop-down again ${to}")
			this.swipeByW3CAction(40, 500, 40, 200)
			MobileDriver.tap(to, GlobalVariable.G_timeoutMedium, FailureHandling.OPTIONAL)
		}

		if(value.isBlank() || value == ""){
			info'Dropdown default value'
			info("Tap Done to close drop-down")
			MobileDriver.tap(lblDynamic(UIDonebtn), GlobalVariable.G_timeoutMedium, flowControl)
			return
		}

		info("Search for ${value}")
		if(!(MobileDriver.waitForElementPresent(lblDropDownSelect(value), GlobalVariable.G_timeoutShort, FailureHandling.OPTIONAL))){
			info("Scroll to ${value}")
			boolean isFound = this.scrollToTestObject(this.frmScrollDropdown(), lblDropDownSelect(value), ScrollDirectionEnum.DOWN, GlobalVariable.G_timeoutShort, FailureHandling.OPTIONAL)
			if(!isFound) {
				this.scrollToTestObject(this.frmScrollDropdown(), lblDropDownSelect(value), ScrollDirectionEnum.UP, GlobalVariable.G_timeoutShort, flowControl)
			}
		}
		info("Tap on ${value}")
		MobileDriver.tap(lblDropDownSelect(value), GlobalVariable.G_timeoutMedium, flowControl)
		info("Tap Done to close drop-down")
		Mobile.tap(lblDynamic(UIDonebtn), GlobalVariable.G_timeoutLong)
	}

	/**
	 * selectDropdownTestObjectSearchType: select value in Dropdown Search Type
	 * @return
	 */
	void selectDropdownTestObjectSearchType(TestObject to, String value, FailureHandling flowControl = defaultFailureHandling) {
		swipeToTestObject(to, [], false, GlobalVariable.G_timeoutMedium, flowControl)

		info("Tap on drop-down ${to}")
		Mobile.tap(to, GlobalVariable.G_timeoutMedium, flowControl)
		if(!MobileDriver.waitForElementPresent(this.lblDynamic(TypeToSearch), GlobalVariable.G_timeoutShort, flowControl)) {
			info("Tap on drop-down again ${to}")
			this.swipeByW3CAction(40, 500, 40, 200)
			MobileDriver.tap(to, GlobalVariable.G_timeoutMedium, FailureHandling.OPTIONAL)
		}

		if(value.isBlank() || value == ""){
			Mobile.delay(GlobalVariable.G_timeoutMiniShort)
			info'Dropdown default value'
			MobileDriver.tap(lblTextNo(2), GlobalVariable.G_timeoutMedium, flowControl)
			return
		}

		info("Search for ${value}")
		if(!(MobileDriver.waitForElementPresent(lblNormalizeSpace(value), GlobalVariable.G_timeoutMedium, FailureHandling.OPTIONAL))){
			info("Scroll to ${value}")
			Mobile.scrollToText(value, FailureHandling.OPTIONAL)
		}
		info("Tap on ${value}")
		Mobile.tap(lblNormalizeSpace(value), GlobalVariable.G_timeoutMedium, flowControl)
	}

	/**
	 * selectDropdownScrollOneLine: select value in dropdown each scroll only 1 item
	 * @return
	 */
	public void selectDropdownScrollOneLine(String findValue, int timeout, FailureHandling flowControl = defaultFailureHandling) {
		TestObject valueObject = this.lblLineValue()
		int left = MobileDriver.getElementLeftPosition(valueObject, timeout, flowControl)
		info("Left: " + left)
		int top = MobileDriver.getElementTopPosition(valueObject, timeout, flowControl)
		info("Top: " + top)
		int heightElement = Mobile.getElementHeight(valueObject, timeout, flowControl)
		info("HeightElement: " + heightElement)

		'scroll dropdown to top'
		while(true) {
			String currentValue = getText(valueObject, timeout, flowControl)
			//scroll a little bit distance to move 1 item
			this.swipeByW3CAction(left, top, left, top + heightElement * 3 + 40)
			Mobile.delay(GlobalVariable.G_timeoutMiniShort)
			String firstValue = getText(valueObject, timeout, flowControl)
			if(currentValue == firstValue) {
				//after scroll prevValue and curValue the same then break
				break;
			}
		}

		'find value in dropdown'
		boolean isScrollingDown = true;
		def prevPageSource = MobileDriver.getPageSource();
		int scrollAttempts = 0;
		int maxScrollAttempts = 10;

		while (scrollAttempts < maxScrollAttempts) {
			String currentValue = MobileDriver.getText(valueObject, timeout, flowControl);

			if (currentValue == findValue) {
				break;
			}

			if (isScrollingDown) {
				this.swipeByW3CAction(left, top + heightElement*2, left, top);
			} else {
				this.swipeByW3CAction(left, top, left, top + heightElement*2);
			}

			Mobile.delay(GlobalVariable.G_timeoutMiniShort);

			def currentPageSource = MobileDriver.getPageSource();
			if (prevPageSource.equals(currentPageSource)) {
				if (isScrollingDown) {
					isScrollingDown = false; // Switch to scrolling up
				} else {
					scrollAttempts++;
					isScrollingDown = true; // Switch to scrolling down for another attempt
				}
			}
			prevPageSource = currentPageSource;

			currentValue = MobileDriver.getText(valueObject, timeout, flowControl);

			if (currentValue == findValue) {
				break;
			}
		}

		if (scrollAttempts >= maxScrollAttempts) {
			// If not found after max attempts, handle error
			handleError("Dropdown: not found value: " + findValue, flowControl);
			MobileDriver.tap(this.lblDynamic(UICancel), GlobalVariable.G_timeoutLong, flowControl);
		}
	}

	/**
	 * enterTextSiblingGroup: set Text base on label with Item EditText has xpath ..Group/..EditText
	 * @return
	 */
	void enterTextSiblingGroup(String label, String value, int timeout = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		if(!value.isBlank() || value != "") {
			'Swipe to field'
			swipeToObject(txtSiblingGroup(label), timeout, FailureHandling.OPTIONAL)
			Mobile.verifyElementExist(txtSiblingGroup(label), timeout, FailureHandling.CONTINUE_ON_FAILURE)
			Mobile.delay(GlobalVariable.G_timeoutMiniShort)
			'Enter value'
			Mobile.clearText(txtSiblingGroup(label), timeout, FailureHandling.OPTIONAL)
			Mobile.setText(txtSiblingGroup(label), value, timeout, flowControl)
			'Hide keyboard'
			Mobile.hideKeyboard(FailureHandling.OPTIONAL)
		}
	}

	/**
	 * scrollToText: scroll To Text base on position start, end
	 * @return
	 */
	boolean scrollToText(String text, int startX, int startY, int endX, int endY, boolean isBothDirections = true, int timeout = GlobalVariable.G_timeoutShort, FailureHandling flowControl = defaultFailureHandling, String pathObject = lblDynamicPath()) {
		TestObject to = findTestObject(pathObject, [('text') : text])
		if(waitForElementPresent(to, timeout, FailureHandling.OPTIONAL)) {
			return true;
		}

		'Scroll down'
		def prevPageSource = driver.getPageSource()
		def currentPageSource = ""
		while(true) {
			this.swipeByW3CAction(startX, startY, endX, endY)
			currentPageSource = driver.getPageSource()
			if(prevPageSource.equals(currentPageSource)) {
				break
			}
			prevPageSource = currentPageSource

			if(waitForElementPresent(to, timeout)) {
				return true;
			}
		}

		if(!isBothDirections) {
			handleError("System Error: Unable to scroll to text " + text, flowControl)
			return false
		}
		'Scroll up'
		prevPageSource = driver.getPageSource()
		currentPageSource = ""
		while(true) {
			this.swipeByW3CAction(startX, startY, endX, endY)
			currentPageSource = driver.getPageSource()
			if(prevPageSource.equals(currentPageSource)) {
				break
			}
			prevPageSource = currentPageSource

			if(waitForElementPresent(to, timeout)) {
				return true;
			}
		}

		handleError("System Error: Unable to scroll to text " + text, flowControl)
		return false
	}

	/**
	 * scrollToTextDefault: scroll To Text base on position start, end
	 * @return
	 */
	boolean scrollToTextDefault(String text, List scrollPosition=[], boolean isBothDirections = true, int timeout = GlobalVariable.G_timeoutShort, FailureHandling flowControl = defaultFailureHandling, String pathObject = lblDynamicPath()) {
		if(scrollPosition.isEmpty()) {
			scrollPosition = MobileDriver.getPositionScrollDownDefault()
		}

		int startX = scrollPosition.get(0)
		int startY = scrollPosition.get(1)
		int endX = scrollPosition.get(2)
		int endY = scrollPosition.get(3)

		this.scrollToText(text, startX, startY, endX, endY, isBothDirections, timeout, flowControl, pathObject)
	}

	/**
	 * clearText: scroll To label and clear text
	 * @return
	 */
	void clearText(String label,boolean isDynamic = true, int timeout = GlobalVariable.G_timeoutShort, FailureHandling flowControl = defaultFailureHandling) {
		info("Scroll to ${label}")
		Mobile.scrollToText(label, FailureHandling.OPTIONAL)
		if(isDynamic) {
			Mobile.clearText(txtSiblingDynamic(label), timeout, flowControl)
		}
		else {
			Mobile.clearText(txtSiblingGroup(label), timeout, flowControl)
		}
	}

	/**
	 * verifyDataAndUI: Verify data file and UI have match
	 * @return
	 */
	boolean verifyDataAndUI(Map data, FailureHandling flowControl = defaultFailureHandling){
		data.each { key, value ->
			String uiValue = MobileDriver.getText(this.lblSiblingOneGroup(key), GlobalVariable.G_timeoutLong, flowControl)
			BaseAssert.equals(uiValue, value, 'Verify property: ' + key)
		}
	}

	/**
	 * inputDropdownMap: Input Many Dropdown
	 * @return
	 */
	boolean inputDropdownMap(Map data, DropDownTypeEnum drowpdownType, FailureHandling flowControl = defaultFailureHandling){
		switch(drowpdownType) {
			case DropDownTypeEnum.SEARCH:
				data.each { key, value ->
					this.selectDropdownTestObjectSearchType(this.imgArrowDownDropdown(key, 1), value, flowControl)
				}
				break
			default:
				data.each { key, value ->
					if(key.toString().contains(INDEX_DROPDOWN)) {
						String keyStr = key.toString()
						def index = keyStr.indexOf(INDEX_DROPDOWN)
						'Multiple dropdowns on the same line'
						this.selectDropdownTestObject(this.imgArrowDownDropdown(keyStr.substring(0, index), keyStr.substring(index + INDEX_DROPDOWN.length())), value, flowControl)
					}else {
						'Select Dropdown'
						this.selectDropdownTestObject(this.imgArrowDownDropdown(key, 1), value, flowControl)
					}
				}
		}
	}

	/**
	 * inputDropdownMapDefault: Input Many Dropdown with default type
	 * @return
	 */
	boolean inputDropdownMapDefault(Map data, FailureHandling flowControl = defaultFailureHandling){
		this.inputDropdownMap(data, DropDownTypeEnum.DEFAULT, flowControl)
	}

	boolean inputDropdownMapSearch(Map data, FailureHandling flowControl = defaultFailureHandling){
		this.inputDropdownMap(data, DropDownTypeEnum.SEARCH, flowControl)
	}

	/**
	 * inputTextListGroup: Set Text base on label
	 * @return
	 */
	boolean inputTextListGroup(Map data, FailureHandling flowControl = defaultFailureHandling){
		data.each { key, value ->
			if(!MobileDriver.waitForElementPresent(this.lblDynamic(key), GlobalVariable.G_timeoutShort, FailureHandling.OPTIONAL)) {
				this.scrollToTextDefault(key, [], false, GlobalVariable.G_timeoutShort, flowControl)
			}
			MobileDriver.setText(this.txtSiblingGroup(key), value, GlobalVariable.G_timeoutMedium, flowControl)
		}
	}

	/**
	 * inputTextAndSelectDropdown: Input Text and Select Dropdown
	 * @return
	 */
	void inputTextAndSelectDropdown(Map inputData, Map drpData, FailureHandling flowControl = defaultFailureHandling) {
		'Input Text'
		this.inputTextListGroup(inputData, flowControl)
		this.scrollToTop()
		'select Dropdown'
		this.inputDropdownMapDefault(drpData, flowControl)
	}

	/**
	 * VerifyElementsAndScrolling: Verify Elements if not found then scrolling search
	 * @return
	 */
	def verifyElementsAndScrolling(List<String> lst, String pathObject, int timeout, TestObject frmScroll = this.frmHorizontalScrollNo(1), FailureHandling flowControl = defaultFailureHandling) {
		lst.each { item ->
			def variable = item
			TestObject to = findTestObject(pathObject, [('text') : variable])
			info("Verify element ${to} param: {text: ${variable}} ")
			if(!MobileDriver.waitForElementPresent(to, timeout, FailureHandling.OPTIONAL)) {
				this.scrollToTestObject(frmScroll, to, ScrollDirectionEnum.DOWN, GlobalVariable.G_timeoutShort, flowControl)
				delay(GlobalVariable.G_timeoutShort)
				verifyElementVisible(to, timeout, flowControl)
			}
		}
	}
	void backToHome(int imgIndex = 1,int timeout = GlobalVariable.G_timeoutShort, FailureHandling failureHandling = FailureHandling.OPTIONAL) {
		int maxAttempts = 5;
		int attempt = 0;

		while(attempt < maxAttempts) {
			// Check if the Home screen is visible
			if(MobileDriver.waitForElementPresent(this.lblDynamic(HOME), timeout, failureHandling)) {
				MobileDriver.tap(this.lblDynamic(HOME), GlobalVariable.G_timeoutMedium, failureHandling);
				delay(GlobalVariable.G_timeoutMiniShort)
				break;  // Exit the loop if the Home screen is found
			}

			// If Home is not visible, tap the back button
			MobileDriver.tap(this.imgNoDynamic(imgIndex), GlobalVariable.G_timeoutMedium, failureHandling);
			attempt++;
		}
	}

	void scrollDownDynamic(int distance = 200) {
		// Get device dimensions (width and height)
		int deviceWidth = Mobile.getDeviceWidth()
		int deviceHeight = Mobile.getDeviceHeight()

		// Calculate start and end points for the swipe
		int startX = deviceWidth / 2  // Center X of the device
		int startY = deviceHeight / 2  // Center Y of the device
		int endX = startX
		int endY = startY + distance  // Swipe 200 pixels down
		this.swipeByW3CAction(startX, startY, endX, endY)
		delay(GlobalVariable.G_timeoutMiniShort)
	}

	void scrollUpDynamic(int distance = 200) {
		// Get device dimensions (width and height)
		int deviceWidth = Mobile.getDeviceWidth()
		int deviceHeight = Mobile.getDeviceHeight()

		// Calculate start and end points for the swipe
		int startX = deviceWidth / 2  // Center X of the device
		int startY = deviceHeight / 2  // Center Y of the device
		int endX = startX
		int endY = startY - distance  // Swipe 200 pixels down
		this.swipeByW3CAction(startX, startY, endX, endY)
		delay(GlobalVariable.G_timeoutMiniShort)
	}

	void verifyNewBalance(String oldBalance, String newBalance, String amount) {
		def balanceBigInt = StringUtils.convertBalanceAmountNumber(oldBalance)
		def newBalanceBigInt = StringUtils.convertBalanceAmountNumber(newBalance)
		'Verify New Balance'
		BaseAssert.equals(((balanceBigInt -  newBalanceBigInt).abs()).toString(), amount.toString(), 'Check Amount')
	}


	/**
	 * This method searches for a specific string in a list, removes any whitespace from that string, and updates the list with the modified string if it is found.
	 * If the item appears multiple times in the list, only modify the first one.
	 * 
	 * ***/
	List<String> replaceWhiteSpaceForAnItemInList(List<String> verifyList, String item) {
		// Get the index of the item
		int itemIndex = verifyList.indexOf(item);

		item = item.replaceAll("\\s+", "");

		// If item present in the list change the value of the item in list
		if (itemIndex != -1) {
			verifyList.set(itemIndex, item);
		}
		return verifyList;
	}

	public enum DropDownTypeEnum{
		DEFAULT, SEARCH
	}

	public enum TestObjectTypeEnum{
		TEXT, CONTAINS
	}

	/**
	 * Upload Image File To Device
	 */
	public void uploadImageFileToDevice(String fileName, FailureHandling flowControl = defaultFailureHandling) {
		String folderPath = '/Data Files/Image/'
		String localPath1 = new File(RunConfiguration.getProjectDir() + folderPath + fileName).getCanonicalPath()
		String filePath1 = localPath1
		AppiumDriver driver = MobileDriverFactory.getDriver()
		// Get platform name from driver capabilities
		String platformName = driver.getCapabilities().getCapability("platformName").toString().toLowerCase()

		if (platformName == "android") {
			filePath1 = '/sdcard/Pictures/' +  fileName
		} else if (platformName == "ios") {
			filePath1 = "@/Documents/" + fileName
		}

		// Check if driver supports file pushing
		//		if(driver instanceof PushesFiles) {
		////			new com.katalon.testcloud.FileExecutor().pushFileToDevice(filePath1, localPath1)
		//			Mobile.comment("File pushed successfully to device at: $localPath1")
		//		} else {
		//			Mobile.comment("Current driver does not support file pushing.")
		//			KeywordUtil.markFailedAndStop("Current driver does not support file pushing.")
		//		}
	}
	/**
	 * Swipe up until found
	 */
	boolean swipeUntilFound(TestObject obj, int maxSwipe = 5) {
		for (int i = 0; i < maxSwipe; i++) {
			if (Mobile.waitForElementPresent(obj, 1, FailureHandling.OPTIONAL)) {
				KeywordUtil.logInfo("Found element after swipe: " + i)
				return true
			}
			swipeByW3CAction(500, 1200, 500, 300)
			Mobile.delay(0.4)
		}
		return false
	}

	void byPassOS(){
		if(MobileDriver.verifyElementExist(this.lblContainDynamic("operating system"), GlobalVariable.G_timeoutMedium,FailureHandling.OPTIONAL)) {
			MobileDriver.delay(GlobalVariable.G_timeoutShort)
			MobileDriver.tap(this.imgNoDynamic(2), GlobalVariable.G_timeoutMedium, FailureHandling.OPTIONAL)
		}
	}

	public void fillTheForm(Map<String, String> formData, FailureHandling flowControl = defaultFailureHandling) {
		formData.each { key, value ->
			info("Filling field: " + key + " with value: " + value)
			this.scrollToTextDefault(key, [], false, GlobalVariable.G_timeoutShort, flowControl)
			MobileDriver.setText(this.txtSiblingDynamic(key), value, GlobalVariable.G_timeoutMedium, flowControl)
			Mobile.hideKeyboard(FailureHandling.OPTIONAL)
		}
	}

	void dragAndDropObj(TestObject fromObj, TestObject toObj, int timeout = GlobalVariable.G_timeoutShort, FailureHandling failureHandling = FailureHandling.OPTIONAL) {
		Mobile.dragAndDrop(fromObj, toObj, timeout)
	}
	
	
	/**
	 * networkLogs: Open network logs, search api, get attribute value from response body
	 * parameters:
	 * * apiKeyword: api string used to search url
	 * * expectedValue: attribute name from json string (ex: pan, DebtCardNo...)
	 * @return
	 */
	public String searchNetworkLogs(String apiKeyword, FailureHandling flowControl = FailureHandling.OPTIONAL) {	
		openDevOption(UINetworkLogs)
		
		'Select Legacy View tab'
		Mobile.tap(this.lblDynamic("Legacy View"), GlobalVariable.G_timeoutMedium, FailureHandling.STOP_ON_FAILURE)
		
		'Search keyword'
		MobileDriver.waitForElementPresent(txtEditText(FILTER_URL), GlobalVariable.G_timeoutMedium, FailureHandling.OPTIONAL)
		setText(txtEditText(FILTER_URL), apiKeyword, GlobalVariable.G_timeoutMedium)
	
		MobileDriver.waitForElementPresent(lblTextViewContain(apiKeyword, 1), GlobalVariable.G_timeoutLong, FailureHandling.STOP_ON_FAILURE)
		MobileDriver.tap(lblTextViewContain(apiKeyword, 1), GlobalVariable.G_timeoutMedium)
		
		Mobile.delay(GlobalVariable.G_timeoutMedium)
		Mobile.waitForElementPresent(this.lblContainDynamic(apiKeyword), GlobalVariable.G_timeoutMedium, FailureHandling.OPTIONAL)
		String fullApiString = Mobile.getText(this.lblContainDynamic(apiKeyword), GlobalVariable.G_timeoutLong, FailureHandling.STOP_ON_FAILURE)
		Mobile.comment("Full API String: " + fullApiString)
		Mobile.takeScreenshot()
		
		'Close Network Logs'
		MobileDriver.tap(imgNoDynamic(2), GlobalVariable.G_timeoutMedium, FailureHandling.OPTIONAL)
		
		return fullApiString
	}

}
