import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import com.vcb.mobile.internal.pages.base.BasePage
import com.vcb.mobile.internal.pages.base.HomePage
import com.vcb.mobile.internal.pages.base.moneylock.MoneyLock as MoneyLock
import com.vcb.mobile.internal.pages.base.accountpage.AccountDetailPage
import com.vcb.mobile.internal.pages.base.accountpage.AccountsPage
import com.vcb.common.MobileDriver

import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

HomePage homePage = BasePage.getPage(HomePage)
MoneyLock moneyLock = BasePage.getPage(MoneyLock)
AccountDetailPage accountDetailPage = BasePage.getPage(AccountDetailPage)
AccountsPage accountsPage = BasePage.getPage(AccountsPage)

def initialBalance
def accountName
def accountNo
def lockedAmount

def getAccountInfo = {
	initialBalance = homePage.getBalance()
	accountName    = homePage.getAccountName()
	accountNo      = homePage.getAccountNumber()
}

def verifyIntroIfNeeded = {
	if (moneyLock.isIncreaseMoneyLock()) {
		'Verify Introduction Screen'
		moneyLock.verifyIntroductionScreen()
	}
}

switch (entryFlow) {
	case 'Introduce screen':
	    'Tap Money Lock'
	    homePage.selectQuickActions(functionName)
		break
		
	case 'Quick Action':
		'Get Account infos'
		getAccountInfo()

		'Tap Money Lock'
		homePage.selectQuickActions(functionName)

		'Verify Introduction Screen (if applicable)'
		verifyIntroIfNeeded()

		'Get Locked Amount'
		lockedAmount = moneyLock.getMoneyLockAmount()

		'Tap Account'
		moneyLock.selectAccountLock(selectAccountName)
		break

	case 'Wallet':
		'Get Account infos'
		getAccountInfo()

		'Select Account Balance: RM'
		homePage.navigateToAccountDetail()

		'Get Locked Amount'
		lockedAmount = moneyLock.getMoneyLockAmount()

		'Tap Money Lock'
		accountDetailPage.selectMenu(functionName)

		'Verify Introduction Screen (if applicable)'
		verifyIntroIfNeeded()
		break

	case 'Account Detail':
		'Select Accounts button'
		homePage.tapBtnAccounts()
		MobileDriver.takeScreenshot()

		'Tap account'
		accountsPage.selectAccount(selectAccountName)

		'Get Account infos'
		getAccountInfo()
		lockedAmount = moneyLock.getMoneyLockAmount()

		'Select menu'
		accountDetailPage.selectMenu(functionName)

		'Verify Introduction Screen (if applicable)'
		verifyIntroIfNeeded()
		break

	case 'View All Accounts':
		'Get Account infos'
		getAccountInfo()
		
		'Tap View All Accounts button'
		homePage.tapViewAllAccounts()

		'Tap account'
		accountsPage.selectAccount(selectAccountName)

		'Get Locked Amount'
		lockedAmount = moneyLock.getMoneyLockAmount()

		'Select menu'
		accountDetailPage.selectMenu(functionName)
		MobileDriver.takeScreenshot()

		'Verify Introduction Screen (if applicable)'
		verifyIntroIfNeeded()
		break
}

return [
	initialBalance: initialBalance,
	accountName   : accountName,
	accountNo     : accountNo,
	lockedAmount  : lockedAmount
]