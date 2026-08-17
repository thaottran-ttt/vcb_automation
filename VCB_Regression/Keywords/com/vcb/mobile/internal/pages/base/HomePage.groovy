package com.vcb.mobile.internal.pages.base

import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testobject.TestObject
import com.vcb.assertions.BaseAssert
import com.vcb.assertions.StringAssert
import com.vcb.common.Android
import com.vcb.common.MobileDriver
import com.vcb.mobile.internal.pages.base.accountpage.AccountsPage
import com.vcb.utils.DateUtils
import com.vcb.utils.RegexUtils
import com.vcb.mobile.internal.pages.base.CustomisePage

import internal.GlobalVariable

public abstract class HomePage extends BasePage{
	abstract TestObject btnMenu(String lblName)
	abstract TestObject lblAccountBalance()
	abstract TestObject lblAccountName()
	abstract TestObject lblAccountNo()
	abstract TestObject fraScrollQuickAction()
	abstract TestObject btnHome()
	abstract TestObject lblDynamic(String text)
	abstract TestObject lblSiblingDynamicContains(String text, String text2)
	abstract TestObject	btnViewAllAccounts()
	abstract TestObject btnQRPayScan()
	abstract TestObject	btnApply()
	abstract TestObject	btnExpenses()
	abstract TestObject lblSecure2u(String lblName)
	abstract TestObject btnLogout(String lblName)
	abstract TestObject btnBackToDashboard(String lblName)
	abstract TestObject btnSetting()
	abstract TestObject btnScan()
	abstract TestObject btnEye()
	abstract TestObject btnNotification()
	abstract TestObject txtQuickActions()
	abstract TestObject btnCutomise()
	abstract TestObject btnSamaSamaLocal()
	abstract TestObject btnTabung()
	abstract TestObject btnDealsForYou()
	abstract TestObject btnTopReads()
	abstract TestObject btnManageWidgets()
	abstract TestObject btnSpendingSummary()
	abstract TestObject btnSurveyBanner()
	abstract TestObject fraScrollTab()
	abstract TestObject btnlogout()
	abstract TestObject btnBackToDashboard()
	abstract TestObject btnClose()
	abstract TestObject btnLogin()
	abstract TestObject txtWelcomeBack()
	abstract TestObject txtTotalSpending()
	abstract TestObject txtExpenses()
	abstract TestObject txtSpentSoFar()
	abstract TestObject txtThisMonth()
	abstract TestObject txtSpentSofarValue()
	abstract TestObject txtTotalMonthlySpending()
	abstract TestObject txtTopReads()
	abstract TestObject btnHeartSymbol()
	abstract TestObject txtTopReadsCount()
	abstract TestObject btnBackTopRead()
	abstract TestObject btnAccount()
	abstract TestObject btnSettings()
	abstract TestObject btnAccounts()
	abstract TestObject	btnViewAll()
	abstract TestObject lblMoneyLock()
	abstract TestObject closeToastMsg()

	String UISecure2u = 'Secure2u'
	String UIBackToDashboard = 'Back to Dashboard'
	String UILogout = 'Logout'
	String UIRMHide = 'RM ****'
	String UISummaryHide = 'RM****'
	String countTopReads= null
	int countTopReadsInt;
	int countTopReadsAfterLikeInt;
	String UIRM = 'RM'
	String UIViewAllAccounts = 'View All Accounts'
	String UITotalBalance = 'Total Balance'
	String UIApply = 'Apply'
	String UIApplyNow = 'Apply Now'
	String UILblAccount = 'Account'
	String UIlblATMCashout = 'ATM Cash-out'
	String UILblAccounts = 'Accounts'
	String UIMaeMobileNumber = 'VCB mobile number'
	String UIReferralCode = 'Referral code'
	String SPENDING_SUMMARY = 'Spending Summary'
	static final String HOME_UI = 'Home'
	static final String LOGIN = 'Login'
	static final String MAYBE_LATER = 'Maybe Later'
	static final String KILL_SWITCH = 'Kill Switch'
	static final String CUSTOMISE = 'Customise'
	static final String SEARCH_ITEM = 'Tabung'
	static final String TOTAL_SPENDING = 'Total spending:'
	static final String TAP_TO_VIEW = 'Tap to View'
	static final String VIEW_ALL = 'View All'
	static final String NEW_TABUNG = 'New Tabung'
	static final String TOP_READ_CONTAIN = 'View More Content'

	CommonPage commonPage = getPage(CommonPage)
	AccountsPage accountsPage = getPage(AccountsPage)
	CustomisePage customizePage = getPage(CustomisePage)

	public tapMenu(String lblName, FailureHandling flowControl = defaultFailureHandling) {
		'Select Menu'
		MobileDriver.tap(this.btnMenu(lblName), GlobalVariable.G_timeoutShort, flowControl)
	}

	public tapNotification(FailureHandling flowControl = defaultFailureHandling) {
		'Select Notification Button'
		MobileDriver.tap(this.btnNotification(), GlobalVariable.G_timeoutShort, flowControl)
	}

	String getBalance(boolean isFromAccountDetail = false, FailureHandling flowControl = defaultFailureHandling) {
		'Wait for Account Balance present'
		if(MobileDriver.waitForElementPresent(commonPage.lblDynamic(UIRMHide), GlobalVariable.G_timeoutShort, FailureHandling.OPTIONAL)) {
			'Select Eye Button'
			MobileDriver.tap(this.btnEye(), GlobalVariable.G_timeoutLong, flowControl)
		}
		Mobile.delay(5)
		MobileDriver.takeScreenshot()
		'If isFromAccountDetail is true, then get balance from Account Detail'
		if(isFromAccountDetail) {
			'Select Account Balance: RM'
			MobileDriver.tap(this.lblAccountBalance(), GlobalVariable.G_timeoutLong, flowControl)
			Mobile.delay(GlobalVariable.G_timeoutMiniShort)
			'Get Balance'
			String balance =  MobileDriver.getText(commonPage.lblStartsWith(UIRM), GlobalVariable.G_timeoutLong, flowControl)
			'Select Back'
			MobileDriver.tap(commonPage.imgNoDynamic(1), GlobalVariable.G_timeoutLong, flowControl)
			MobileDriver.takeScreenshot()
			MobileDriver.info("Balance: " + balance)
			return balance
		}
		'Get Balance'
		return MobileDriver.getText(this.lblAccountBalance(), GlobalVariable.G_timeoutLong, flowControl)
	}

	String getBalanceG1A(boolean isFromAccountDetail = false, FailureHandling flowControl = defaultFailureHandling) {
		'Wait for Account Balance present'
		if(MobileDriver.waitForElementPresent(commonPage.lblDynamic(UIRMHide), GlobalVariable.G_timeoutShort, FailureHandling.OPTIONAL)) {
			'Select Eye Button'
			MobileDriver.tap(this.btnEye(), GlobalVariable.G_timeoutLong, flowControl)
		}
		Mobile.delay(GlobalVariable.G_timeoutMiniShort)
		MobileDriver.takeScreenshot()
		'If isFromAccountDetail is true, then get balance from Account Detail'
		if(isFromAccountDetail) {
			'Select Account Balance: RM'
			MobileDriver.tap(this.lblAccountBalance(), GlobalVariable.G_timeoutLong, flowControl)
			Mobile.delay(GlobalVariable.G_timeoutMiniShort)
			'Get Balance'
			String balance =  MobileDriver.getText(commonPage.lblStartsWith(UIRM), GlobalVariable.G_timeoutLong, flowControl)
			'Select Back'
			MobileDriver.tap(commonPage.imgNoDynamic(2), GlobalVariable.G_timeoutLong, flowControl)
			MobileDriver.takeScreenshot()
			MobileDriver.info("Balance: " + balance)
			return balance
		}
		'Get Balance'
		return MobileDriver.getText(this.lblAccountBalance(), GlobalVariable.G_timeoutLong, flowControl)
	}

	void navigateToAccountDetail(FailureHandling flowControl = defaultFailureHandling) {
		'Wait for RM **** present'
		if(MobileDriver.waitForElementPresent(commonPage.lblDynamic(UIRMHide), GlobalVariable.G_timeoutShort, FailureHandling.OPTIONAL)) {
			'Select Eye Button'
			MobileDriver.tap(this.btnEye(), GlobalVariable.G_timeoutLong, flowControl)
		}
		Mobile.delay(GlobalVariable.G_timeoutMiniShort)
		'Select Account Balance: RM'
		MobileDriver.tap(this.lblAccountBalance(), GlobalVariable.G_timeoutShort)
	}

	public hideBalance(flowControl) {
		'Select Eye Button'
		MobileDriver.tap(this.btnEye(), GlobalVariable.G_timeoutLong)
		'Verify RM **** is visible'
		MobileDriver.verifyElementVisible(commonPage.lblDynamic(UIRMHide), GlobalVariable.G_timeoutShort, FailureHandling.OPTIONAL)
	}

	public getAccountData(FailureHandling flowControl = defaultFailureHandling) {
		'Get account balance'
		String balance = MobileDriver.getText(this.lblAccountBalance(), GlobalVariable.G_timeoutShort, flowControl)
		'Get account name'
		String accountName = MobileDriver.getText(this.lblAccountName(), GlobalVariable.G_timeoutShort, flowControl)
		'Get account no'
		String accountNo = MobileDriver.getText(this.lblAccountNo(), GlobalVariable.G_timeoutShort, flowControl)

		return [
			balance,
			accountName,
			accountNo
		]
	}

	public String getAccountName(boolean shouldCheckAccountNamePresent = true, FailureHandling flowControl = defaultFailureHandling) {
		if(shouldCheckAccountNamePresent && MobileDriver.waitForElementPresent(commonPage.lblDynamic(UIRMHide), GlobalVariable.G_timeoutShort, FailureHandling.OPTIONAL)) {
			'Select Eye Button'
			MobileDriver.tap(this.btnEye(), GlobalVariable.G_timeoutLong, flowControl)
		}

		return MobileDriver.getText(this.lblAccountName(), GlobalVariable.G_timeoutShort, flowControl)
	}

	public String getAccountNumber(boolean shouldCheckAccountNamePresent = true, FailureHandling flowControl = defaultFailureHandling) {
		if(shouldCheckAccountNamePresent && MobileDriver.waitForElementPresent(commonPage.lblDynamic(UIRMHide), GlobalVariable.G_timeoutShort, FailureHandling.OPTIONAL)) {
			'Select Eye Button'
			MobileDriver.tap(this.btnEye(), GlobalVariable.G_timeoutLong, flowControl)
			delay(GlobalVariable.G_timeoutMiniShort)
		}
		return MobileDriver.getText(this.lblAccountNo(), GlobalVariable.G_timeoutShort, flowControl)
	}

	public clickViewAllAccountsButton(FailureHandling flowControl = defaultFailureHandling) {
		'Click View All Accounts Button'
		MobileDriver.tap(this.btnViewAllAccounts(), GlobalVariable.G_timeoutMedium, flowControl)
	}

	public verifyNotificationButton(FailureHandling flowControl = defaultFailureHandling) {
		'Verify Notification Button'
		MobileDriver.verifyElementExist(this.btnNotification(), GlobalVariable.G_timeoutMedium, flowControl)
	}

	public verifyQuickActionstext(FailureHandling flowControl = defaultFailureHandling) {
		'Verify Quick Actions text'
		MobileDriver.verifyElementExist(this.txtQuickActions(), GlobalVariable.G_timeoutMedium, flowControl)
	}

	public verifyCustomiseButton(FailureHandling flowControl = defaultFailureHandling) {
		'Verify Customise Button'
		if(waitForElementPresent(commonPage.lblDynamic(CUSTOMISE), GlobalVariable.G_timeoutShort, FailureHandling.OPTIONAL)) {
			MobileDriver.verifyElementExist(commonPage.lblDynamic(CUSTOMISE), GlobalVariable.G_timeoutShort, flowControl)
			MobileDriver.delay(GlobalVariable.G_timeoutShort)
			return true
		}

		def found = commonPage.scrollToTestObject(this.fraScrollQuickAction(), commonPage.lblDynamic(CUSTOMISE), ScrollDirectionEnum.RIGHT, GlobalVariable.G_timeoutMedium, flowControl)
		if(found) {
			MobileDriver.verifyElementExist(commonPage.lblDynamic(CUSTOMISE), GlobalVariable.G_timeoutShort, flowControl)
			MobileDriver.delay(GlobalVariable.G_timeoutShort)
			return true
		}
	}

	public verifySamaSamaLocalButton(FailureHandling flowControl = defaultFailureHandling) {
		'Verify Sama Sama Local Button'
		commonPage.swipeByW3CAction(5, 1000, 5, 100)
		MobileDriver.verifyElementExist(this.btnSamaSamaLocal(), GlobalVariable.G_timeoutMedium, flowControl)
	}

	public verifyDealsForYouButton(FailureHandling flowControl = defaultFailureHandling) {
		'Verify Deals For You Button'
		def found = commonPage.scrollToTestObject(commonPage.frmScrollView(), this.btnDealsForYou(), ScrollDirectionEnum.DOWN, GlobalVariable.G_timeoutMedium, flowControl)
		if(found) {
			MobileDriver.verifyElementExist(this.btnDealsForYou(), GlobalVariable.G_timeoutShort, flowControl)
			MobileDriver.delay(GlobalVariable.G_timeoutShort)
		}

		'Back To Home'
		MobileDriver.tap(commonPage.imgNoDynamic(1), countTopReadsAfterLikeInt, flowControl)
	}

	public verifyTabungButton(FailureHandling flowControl = defaultFailureHandling) {
		'Verify Tabung Button'
		def found = commonPage.scrollToTestObject(commonPage.frmScrollView(), this.btnTabung(), ScrollDirectionEnum.DOWN, GlobalVariable.G_timeoutMedium, flowControl)
		if(found) {
			MobileDriver.verifyElementExist(this.btnTabung(), GlobalVariable.G_timeoutMedium, flowControl)
		}
	}

	public tapToViewTabung(FailureHandling flowControl = defaultFailureHandling) {
		'Tap New Tabung'
		def found = commonPage.scrollToTestObject(commonPage.frmScrollView(), commonPage.lblDynamic(NEW_TABUNG), ScrollDirectionEnum.DOWN, GlobalVariable.G_timeoutMedium, flowControl)
		if(found) {
			MobileDriver.tap(commonPage.lblDynamic(NEW_TABUNG), GlobalVariable.G_timeoutMedium, flowControl)
		}
	}

	public verifyAllTabungList(FailureHandling flowControl = defaultFailureHandling) {
		'TapView All'
		commonPage.swipeByW3CAction(5, 500, 5, 1000)
		MobileDriver.tap(commonPage.lblDynamic(VIEW_ALL), countTopReadsAfterLikeInt, flowControl)
		Mobile.delay(GlobalVariable.G_timeoutMiniShort)
	}

	public verifyTabungList(List tabungs, FailureHandling flowControl = defaultFailureHandling) {
		'verify Tabung list'
		tabungs.each{ item ->
			def found = commonPage.scrollToTestObject(commonPage.frmScrollView(), commonPage.lblDynamic(item), ScrollDirectionEnum.DOWN, GlobalVariable.G_timeoutMedium, flowControl)
			if(found) {
				MobileDriver.verifyElementExist(commonPage.lblDynamic(item), GlobalVariable.G_timeoutShort, flowControl)
				MobileDriver.delay(GlobalVariable.G_timeoutShort)
			}
		}

		'Tap Back button'
		MobileDriver.tap(commonPage.imgNoDynamic(1), countTopReadsAfterLikeInt, flowControl)
	}

	public verifyTopReadsButton(FailureHandling flowControl = defaultFailureHandling) {
		MobileDriver.delay(GlobalVariable.G_timeoutShort)
    commonPage.swipeByW3CAction(5, 1000, 5, 100)
		'Verify Top Reads Button'
		def found = commonPage.scrollToTestObject(commonPage.frmScrollView(), this.btnTopReads(), ScrollDirectionEnum.DOWN, GlobalVariable.G_timeoutMedium, flowControl)
		if(found) {
			MobileDriver.verifyElementExist(this.btnTopReads(), GlobalVariable.G_timeoutShort, flowControl)
			MobileDriver.delay(GlobalVariable.G_timeoutShort)
		}
	}

	public verifyViewMoreTopReadsContains(FailureHandling flowControl = defaultFailureHandling) {
		def found = commonPage.scrollToTestObject(commonPage.frmScrollView(), commonPage.lblDynamic(TOP_READ_CONTAIN), ScrollDirectionEnum.DOWN, GlobalVariable.G_timeoutMedium, flowControl)
		if(found) {
			MobileDriver.verifyElementExist(commonPage.lblDynamic(TOP_READ_CONTAIN), GlobalVariable.G_timeoutShort, flowControl)
			MobileDriver.delay(GlobalVariable.G_timeoutShort)
		}
	}

	public verifyManageWidgetsButton(FailureHandling flowControl = defaultFailureHandling) {
		MobileDriver.scrollToEnd()
		'Verify Manage Widgets Button'
		MobileDriver.verifyElementExist(this.btnManageWidgets(), GlobalVariable.G_timeoutMedium, flowControl)
		'Open Manage Widgets'
		MobileDriver.tap(this.btnManageWidgets(), GlobalVariable.G_timeoutMedium, flowControl)
		Mobile.delay(GlobalVariable.G_timeoutMiniShort)
		'Back to Dashboard'
		MobileDriver.tap(this.btnBackTopRead(), GlobalVariable.G_timeoutMedium, flowControl)
	}

	public verifySpendingSummaryButton(FailureHandling flowControl = defaultFailureHandling) {
		def found = commonPage.scrollToTestObject(commonPage.frmScrollView(), commonPage.lblDynamic(SPENDING_SUMMARY), ScrollDirectionEnum.DOWN, GlobalVariable.G_timeoutMedium, flowControl)
		if(found) {
			MobileDriver.verifyElementExist(this.btnSpendingSummary(), GlobalVariable.G_timeoutMedium, flowControl)
		}
	}

	public verifyTotalSpending(FailureHandling flowControl = defaultFailureHandling) {
		def found = commonPage.scrollToTestObject(commonPage.frmScrollView(), commonPage.lblContainDynamic(TOTAL_SPENDING), ScrollDirectionEnum.DOWN, GlobalVariable.G_timeoutMedium, flowControl)
		if(found) {
			MobileDriver.verifyElementExist(commonPage.lblContainDynamic(TOTAL_SPENDING), GlobalVariable.G_timeoutMedium, flowControl)
		}
	}

	public verifySurveyBannerButton(FailureHandling flowControl = defaultFailureHandling) {
		commonPage.swipeByW3CAction(5, 1000, 5, 100)
		'Verify Survey Banner Button'
		MobileDriver.verifyElementExist(this.btnSurveyBanner(), GlobalVariable.G_timeoutMedium, flowControl)
	}

	public verifytextWelcomeBack(FailureHandling flowControl = defaultFailureHandling) {
		'Verify Welcome Back text'
		MobileDriver.verifyElementExist(this.txtWelcomeBack(), GlobalVariable.G_timeoutMedium, flowControl)
	}

	public clickLoginButton(FailureHandling flowControl = defaultFailureHandling) {
		'Click Login Button'
		MobileDriver.verifyElementExist(this.btnLogin(), GlobalVariable.G_timeoutMedium, flowControl)
	}

	void verifyAccountBalanceNotDisplayed(FailureHandling flowControl = defaultFailureHandling) {
		'Verify RM **** is exist'
		MobileDriver.verifyElementExist(commonPage.lblDynamic(UIRMHide), GlobalVariable.G_timeoutMedium, flowControl)
	}
	void verifyAccountNameDisplayed(String accountName, FailureHandling flowControl = defaultFailureHandling) {
		MobileDriver.verifyElementVisible(commonPage.lblDynamic(accountName), GlobalVariable.G_timeoutLong, flowControl)
	}
	void verifyAccountDashboard(FailureHandling flowControl = defaultFailureHandling) {
		MobileDriver.verifyElementVisible(commonPage.lblDynamic(UITotalBalance), GlobalVariable.G_timeoutLong, flowControl)
	}
		
	void verifySpendingSummaryIsNotDisplayed(FailureHandling flowControl = defaultFailureHandling) {
		'Verify RM**** is exist'
		MobileDriver.verifyElementExist(commonPage.lblDynamic(UISummaryHide), GlobalVariable.G_timeoutMedium, flowControl)
	}

	void tapToViewButton(FailureHandling flowControl = defaultFailureHandling) {
		MobileDriver.tap(commonPage.lblDynamic(TAP_TO_VIEW), GlobalVariable.G_timeoutShort, flowControl)
	}

	void tapToViewAll(FailureHandling flowControl = defaultFailureHandling) {
		MobileDriver.tap(commonPage.lblDynamic(VIEW_ALL), GlobalVariable.G_timeoutShort, flowControl)
		Mobile.takeScreenshot()
	}

	void clickLogout(FailureHandling flowControl = defaultFailureHandling) {
		'Click Logout Button'
		MobileDriver.tap(this.btnlogout(), GlobalVariable.G_timeoutShort, flowControl)
		'Verify Welcome Back text'
		MobileDriver.waitForElementPresent(this.btnBackToDashboard(), GlobalVariable.G_timeoutShort, flowControl)
		'Click Back to Dashboard Button'
		MobileDriver.tap(this.btnBackToDashboard(), GlobalVariable.G_timeoutShort, flowControl)
	}

	void clickEyeButton(flowControl) {
		'Click Eye Button'
		MobileDriver.tap(this.btnEye(), GlobalVariable.G_timeoutShort, flowControl)
		Mobile.delay(1)
	}

	void clickCloseButton(flowControl) {
		'Click Close Button'
		MobileDriver.tap(commonPage.imgNoDynamic(1), GlobalVariable.G_timeoutShort, flowControl)
	}

	void verifyTotalSpendingVisible(flowControl) {
		'Verify Total Spending is visible'
		MobileDriver.verifyElementExist(this.txtTotalSpending(), GlobalVariable.G_timeoutMedium, flowControl)
	}

	void verifyTotalMonthlySpendingVisible(flowControl) {
		'Verify Total Monthly Spending is visible'
		MobileDriver.verifyElementExist(this.txtTotalMonthlySpending(), GlobalVariable.G_timeoutMedium, flowControl)
	}

	public clickHomeButton(FailureHandling flowControl = defaultFailureHandling) {
		'Click Home Button'
		MobileDriver.tap(this.btnHome(), GlobalVariable.G_timeoutShort, flowControl)
	}


	public verifyProfileName(String text, FailureHandling flowControl = defaultFailureHandling) {
		'Verify Profile Name'
		MobileDriver.verifyElementExist(this.lblDynamic(text), GlobalVariable.G_timeoutMedium, flowControl)
	}
	
	public verifyMaskingPhoneNumber(FailureHandling flowControl = defaultFailureHandling) {
		'Verify VCB Mobile Number label and masked mobile number value'
		MobileDriver.verifyElementExist(this.lblSiblingDynamicContains(UIMaeMobileNumber, "****"), GlobalVariable.G_timeoutMedium, flowControl)
	}
	
	public verifyReferralCode(String text, FailureHandling flowControl = defaultFailureHandling) {
		'Verify Referral Code'		
		MobileDriver.verifyElementExist(this.lblDynamic(UIReferralCode), GlobalVariable.G_timeoutMedium, flowControl)
		MobileDriver.verifyElementExist(this.lblDynamic(text), GlobalVariable.G_timeoutMedium, flowControl)
	}

	public verifyViewAllAccountsButton(FailureHandling flowControl = defaultFailureHandling) {
		'Verify View All Accounts Button'
		MobileDriver.verifyElementExist(this.btnViewAllAccounts(), GlobalVariable.G_timeoutMedium, flowControl)
	}

	public verifyQRPayScanButton(FailureHandling flowControl = defaultFailureHandling) {
		'Verify QR Pay Scan Button'
		MobileDriver.verifyElementExist(this.btnQRPayScan(), GlobalVariable.G_timeoutMedium, flowControl)
	}

	public verifyApplyButton(FailureHandling flowControl = defaultFailureHandling) {
		'Verify Apply Button'
		MobileDriver.verifyElementExist(this.btnApply(), GlobalVariable.G_timeoutMedium, flowControl)
	}

	public verifyAccountsButton(FailureHandling flowControl = defaultFailureHandling) {
		'Verify Accounts Button'
		MobileDriver.verifyElementExist(commonPage.lblDynamic(UILblAccounts), GlobalVariable.G_timeoutMedium, flowControl)
	}

	public verifyExpensesButton(FailureHandling flowControl = defaultFailureHandling) {
		'Verify Expenses Button'
		MobileDriver.verifyElementExist(this.btnExpenses(), GlobalVariable.G_timeoutMedium, flowControl)
	}

	boolean selectQuickActions(String text, String password = GlobalVariable.G_password, FailureHandling flowControl = defaultFailureHandling) {
		if(waitForElementPresent(commonPage.lblDynamic(text), GlobalVariable.G_timeoutShort, FailureHandling.OPTIONAL)) {
			tap(commonPage.lblDynamic(text), GlobalVariable.G_timeoutShort, flowControl)
			MobileDriver.delay(GlobalVariable.G_timeoutShort)
			return true
		}

		def found = commonPage.scrollToTestObject(this.fraScrollQuickAction(), commonPage.lblDynamic(text), ScrollDirectionEnum.RIGHT, GlobalVariable.G_timeoutMedium, flowControl)
		if(found) {
			tap(commonPage.lblDynamic(text), GlobalVariable.G_timeoutShort, flowControl)
			MobileDriver.delay(GlobalVariable.G_timeoutShort)
			return true
		}else {
			'Select View All Button'
			if (Mobile.waitForElementPresent(this.btnViewAll(), GlobalVariable.G_timeoutShort)) {
				tap(this.btnViewAll(), GlobalVariable.G_timeoutShort, flowControl)
				found = commonPage.scrollToTestObject(commonPage.frmScrollView(), commonPage.lblDynamic(text), ScrollDirectionEnum.DOWN, GlobalVariable.G_timeoutMedium, flowControl)
				if(found) {
					tap(commonPage.lblDynamic(text), GlobalVariable.G_timeoutShort, flowControl)
					MobileDriver.delay(GlobalVariable.G_timeoutShort)
					return true
				}
			}
		}
		return false
	}

	void verifyQuickActionItem(List items, FailureHandling flowControl = defaultFailureHandling) {
		if (Mobile.waitForElementPresent(this.btnViewAll(), GlobalVariable.G_timeoutShort)) {
			tap(this.btnViewAll(), GlobalVariable.G_timeoutShort, flowControl)
			items.each{ item ->
				def found = commonPage.scrollToTestObject(commonPage.frmScrollView(), commonPage.lblDynamic(item), ScrollDirectionEnum.DOWN, GlobalVariable.G_timeoutMedium, flowControl)
				if(found) {
					tap(commonPage.lblDynamic(item), GlobalVariable.G_timeoutShort, flowControl)
					MobileDriver.delay(GlobalVariable.G_timeoutShort)
				}
			}
		}

		'Click Search Button'
		MobileDriver.tap(commonPage.imgNoDynamic(2), GlobalVariable.G_timeoutShort, flowControl)
		MobileDriver.setText(commonPage.lblContainDynamic("Type to search"), SEARCH_ITEM, GlobalVariable.G_timeoutMedium,FailureHandling.OPTIONAL)
		MobileDriver.verifyElementExist(commonPage.lblDynamic(SEARCH_ITEM), GlobalVariable.G_timeoutMedium, FailureHandling.OPTIONAL)
		MobileDriver.tap(commonPage.imgNoDynamic(1), GlobalVariable.G_timeoutShort, flowControl)

		'Back to home'
		MobileDriver.tap(commonPage.imgNoDynamic(1), GlobalVariable.G_timeoutShort, flowControl)
		Mobile.delay(GlobalVariable.G_timeoutMiniShort)
		if(MobileDriver.waitForElementPresent(commonPage.imgNoDynamic(1), GlobalVariable.G_timeoutShort, flowControl)) {
			MobileDriver.tap(commonPage.imgNoDynamic(1), GlobalVariable.G_timeoutShort, flowControl)
		}
	}

	void selectQuickActionKillSwitch(FailureHandling flowControl = defaultFailureHandling) {
		'Select View All Button'
		if (Mobile.waitForElementPresent(this.btnViewAll(), GlobalVariable.G_timeoutShort)) {
			tap(this.btnViewAll(), GlobalVariable.G_timeoutShort, flowControl)
			def found = commonPage.scrollToTestObject(commonPage.frmScrollView(), commonPage.lblDynamic(KILL_SWITCH), ScrollDirectionEnum.DOWN, GlobalVariable.G_timeoutMedium, flowControl)
			if(found) {
				tap(commonPage.lblDynamic(KILL_SWITCH), GlobalVariable.G_timeoutShort, flowControl)
				MobileDriver.delay(GlobalVariable.G_timeoutShort)
			}
		}
	}

	boolean waitSecure2U() {
		'Wait for Secure2u present'
		return MobileDriver.waitForElementPresent(this.lblSecure2u(UISecure2u), GlobalVariable.G_timeoutMedium, FailureHandling.OPTIONAL)
	}

	void tapSecure2u(String lblName = UISecure2u, int timeOut = GlobalVariable.G_timeoutLong, FailureHandling flowControl = defaultFailureHandling) {
		'Wait for Secure2u present'
		MobileDriver.waitForElementPresent(this.lblSecure2u(lblName), GlobalVariable.G_timeoutMedium, FailureHandling.OPTIONAL)
		'Select Secure2u'
		Mobile.tap(this.lblSecure2u(lblName), timeOut)
	}

	void tapBtnLogout(String lblName = UILogout, int timeOut = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		'Select Logout'
		Mobile.tap(this.btnLogout(lblName), timeOut)
	}

	void tapBtnLogin(FailureHandling flowControl = defaultFailureHandling) {
		'Select Login'
		Mobile.tap(this.btnLogout(LOGIN), GlobalVariable.G_timeoutMedium, flowControl)
	}

	void tapBtnBackToDashboard( String lblName = UIBackToDashboard, int timeOut = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		Mobile.tap(this.btnBackToDashboard(lblName), timeOut)
	}

	void tapBtnSetting(int timeOut = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		Mobile.tap(this.btnSetting(), timeOut)
	}

	boolean verifyBtnScanExist(int timeOut = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		return Mobile.verifyElementExist(this.btnScan(), timeOut, flowControl)
	}

	void tapScanBtn(FailureHandling flowControl = defaultFailureHandling) {
		Mobile.tap(this.btnScan(), GlobalVariable.G_timeoutLong, flowControl)
		Mobile.delay(GlobalVariable.G_timeoutMiniShort)
	}

	boolean checkIsAccountLogin(FailureHandling flowControl = defaultFailureHandling) {
		if(waitForElementPresent(commonPage.lblDynamic(UIViewAllAccounts), GlobalVariable.G_timeoutShort, flowControl)) {
			return true
		}

		return false
	}

	boolean isUserLoggedIn() {
		if(waitForElementPresent(commonPage.lblDynamic(UILogout), GlobalVariable.G_timeoutShort, FailureHandling.OPTIONAL)) {
			return true
		}

		return false
	}

	boolean isUserLoggedOut() {
		if(waitForElementPresent(commonPage.lblDynamic(LOGIN), GlobalVariable.G_timeoutShort, FailureHandling.OPTIONAL)) {
			return true
		}

		return false
	}

	void tapBtnAccounts(int timeOut = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		MobileDriver.tap(this.btnAccounts(), timeOut, flowControl)
		accountsPage.checkAndTapGotIt();
	}

	void tapHomeMenu(FailureHandling flowControl = defaultFailureHandling) {
		MobileDriver.tap(commonPage.lblDynamic(HOME_UI), GlobalVariable.G_timeoutMedium, flowControl)
	}

	void tapApply(FailureHandling flowControl = defaultFailureHandling) {
		Mobile.tap(this.btnBackToDashboard(UIApply), GlobalVariable.G_timeoutLong, flowControl)
	}

	void tapApplyNow(FailureHandling flowControl = defaultFailureHandling) {
		Mobile.tap(this.btnBackToDashboard(UIApplyNow), GlobalVariable.G_timeoutLong, flowControl)
	}

	void tapViewAllAccounts(FailureHandling flowControl = defaultFailureHandling) {
		Mobile.verifyElementExist(this.btnViewAllAccounts(), GlobalVariable.G_timeoutLong)
		Mobile.tap(this.btnViewAllAccounts(), GlobalVariable.G_timeoutLong, flowControl)
	}

	public verifyTextExpensesVisible(flowControl) {
		MobileDriver.verifyElementExist(this.txtExpenses(), GlobalVariable.G_timeoutMedium, flowControl)
	}

	public verifyCurrentMonthVisible(flowControl) {
		'Get current month'
		String currentMonth = DateUtils.getCurrentMonth()
		'Get current month from application'
		String currentMonthApplication = MobileDriver.getText(this.txtThisMonth(), GlobalVariable.G_timeoutMedium, flowControl)
		'Verify current month'
		StringAssert.equals(currentMonth, currentMonthApplication, false, 'Current Month Expenses')
	}

	public verifyTextSpentSoFar(flowControl) {
		MobileDriver.verifyElementExist(this.txtSpentSoFar(), GlobalVariable.G_timeoutMedium, flowControl)
	}

	public verifySpendingSummaryIsDisplayed(flowControl) {
		'Get Spending Summary'
		String spendingSummary = MobileDriver.getText(this.txtSpentSofarValue(), GlobalVariable.G_timeoutMedium, flowControl)
		'Verify Spending Summary format RM ...'
		boolean status = RegexUtils.patternChecking(spendingSummary)
		Mobile.verifyMatch(true, status, true)
	}

	void tapTopReadsLink(flowControl) {
		Mobile.tap(this.txtTopReads(), GlobalVariable.G_timeoutMedium, flowControl)
	}

	void tapHeartSymbolButton(flowControl) {
		Mobile.longPress(this.btnHeartSymbol(), GlobalVariable.G_timeoutMedium, flowControl)
	}

	void readLikeCount(flowControl) {
		'Get Top Reads Count'
		countTopReads = MobileDriver.getText(txtTopReadsCount(), GlobalVariable.G_timeoutMedium, flowControl)
		'Convert Top Reads Count to Integer'
		countTopReadsInt = Integer.parseInt(countTopReads)
	}

	void verifyLikeCountIsIncreamentedbyOne(flowControl) {
		'Increment Top Reads Count by 1'
		countTopReadsInt = countTopReadsInt+1;
		'Get Top Reads Count after Like'
		String countTopReadsAfterLike = MobileDriver.getText(txtTopReadsCount(), GlobalVariable.G_timeoutMedium, flowControl)
		'Convert Top Reads Count after Like to Integer'
		countTopReadsAfterLikeInt = Integer.parseInt(countTopReadsAfterLike)
		'Verify Top Reads Count is incremented by 1'
		Mobile.verifyEqual(countTopReadsAfterLikeInt, countTopReadsInt, flowControl)
	}

	void verifyLikeCountIsIncreamentedbyOneInHomePageTopReads(flowControl) {
		Mobile.tap(this.btnBackTopRead(), GlobalVariable.G_timeoutMedium, flowControl)
		Mobile.verifyEqual(countTopReadsAfterLikeInt, countTopReadsInt, flowControl)
	}

	void clickAccountsButton(flowControl) {
		Mobile.tap(this.btnAccount(), GlobalVariable.G_timeoutMedium, flowControl)
	}

	void clickSettingsButton(flowControl) {
		Mobile.tap(this.btnSettings(), GlobalVariable.G_timeoutMedium, flowControl)
	}
	void verifyRespectiveUser(String yourName, FailureHandling flowControl = defaultFailureHandling) {
		String hello = 'Hello,'
		String nameOnScreen = MobileDriver.getText(commonPage.lblSiblingDynamic(hello), GlobalVariable.G_timeoutMedium, flowControl)
		BaseAssert.equals(nameOnScreen.equals(yourName), true, "Verify Name", flowControl)

		'Wait for Account Balance present'
		if(MobileDriver.waitForElementPresent(commonPage.lblDynamic(UIRMHide), GlobalVariable.G_timeoutShort, FailureHandling.OPTIONAL)) {
			'Select Eye Button'
			MobileDriver.tap(this.btnEye(), GlobalVariable.G_timeoutLong, flowControl)
		}
		MobileDriver.takeScreenshot()
	}
	void clickMaybeLater(FailureHandling flowControl = defaultFailureHandling) {
		if(waitForElementPresent(commonPage.lblDynamic(MAYBE_LATER), GlobalVariable.G_timeoutShort, FailureHandling.OPTIONAL)) {
			MobileDriver.tap(commonPage.lblDynamic(MAYBE_LATER), GlobalVariable.G_timeoutShort, flowControl)
		}
	}
	String getMoneyLockAmount(FailureHandling flowControl = defaultFailureHandling) {
		'Wait for Account Balance present'
		if(MobileDriver.waitForElementPresent(commonPage.lblDynamic(UIRMHide), GlobalVariable.G_timeoutShort, FailureHandling.OPTIONAL)) {
			'Select Eye Button'
			MobileDriver.tap(this.btnEye(), GlobalVariable.G_timeoutLong, flowControl)
		}

		MobileDriver.tap(this.lblAccountBalance(), GlobalVariable.G_timeoutLong, flowControl)

		String amount = "0"
		if(MobileDriver.waitForElementPresent(this.lblMoneyLock(), GlobalVariable.G_timeoutMedium, FailureHandling.OPTIONAL)) {
			amount = MobileDriver.getText(this.lblMoneyLock(), GlobalVariable.G_timeoutMedium, FailureHandling.OPTIONAL)
		}

		'Select Back'
		MobileDriver.tap(commonPage.imgNoDynamic(1), GlobalVariable.G_timeoutLong, flowControl)

		return amount
	}

	void verifyAccountName(String accountName, FailureHandling flowControl = defaultFailureHandling) {
		if(MobileDriver.waitForElementPresent(commonPage.lblDynamic(UIRMHide), GlobalVariable.G_timeoutShort, FailureHandling.OPTIONAL)) {
			'Select Eye Button'
			MobileDriver.tap(this.btnEye(), GlobalVariable.G_timeoutLong, flowControl)
		}
		MobileDriver.verifyElementVisible(commonPage.lblDynamic(accountName), GlobalVariable.G_timeoutLong, flowControl)
		MobileDriver.takeScreenshot()
	}

	void clickBtnClose(FailureHandling flowControl = defaultFailureHandling) {
		if(waitForElementPresent(this.closeToastMsg(), GlobalVariable.G_timeoutShort, FailureHandling.OPTIONAL)) {
			MobileDriver.tap(this.closeToastMsg(), GlobalVariable.G_timeoutShort, flowControl)
		}
	}
}