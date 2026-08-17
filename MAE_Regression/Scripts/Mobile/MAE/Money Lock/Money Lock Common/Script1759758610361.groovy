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
import com.maybank.mae.internal.pages.base.BasePage as BasePage
import com.maybank.mae.internal.pages.base.moneylock.MoneyLock as MoneyLock
import com.maybank.mae.internal.pages.base.CommonPage as CommonPage
import com.maybank.mae.internal.pages.base.PushNotificationPage
import com.maybank.mae.internal.pages.base.StatusScreenPage as StatusScreenPage
import com.maybank.mae.internal.pages.base.HomePage
import com.maybank.mae.internal.pages.base.accountpage.AccountDetailPage as AccountDetailPage
import com.maybank.common.MobileDriver
import com.kms.katalon.core.util.KeywordUtil

import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

MoneyLock moneyLock = BasePage.getPage(MoneyLock)
CommonPage commonPage = BasePage.getPage(CommonPage)
PushNotificationPage pushNotificationPage = BasePage.getPage(PushNotificationPage)
StatusScreenPage statusScreenPage = BasePage.getPage(StatusScreenPage)
HomePage homePage = BasePage.getPage(HomePage)
AccountDetailPage accountDetailPage = BasePage.getPage(AccountDetailPage)

WebUI.callTestCase(findTestCase('Mobile/MAE/Common/Login Secure2u'), [('idNumber') : idNumber, ('PAN') : PAN, ('password') : password
        , ('username') : username, ('accountName') : accountName], FailureHandling.STOP_ON_FAILURE)

Map accountInfo = WebUI.callTestCase(findTestCase('Mobile/MAE/Money Lock/Entry Point'), [('entryFlow') : entryFlow, ('functionName') : functionName
        , ('selectAccountName') : accountName], FailureHandling.STOP_ON_FAILURE)

def initialBalance = accountInfo.initialBalance
def accountName    = accountInfo.accountName
def accountNo      = accountInfo.accountNo
def lockedAmount   = accountInfo.lockedAmount

BigDecimal lockedAmountValue = new BigDecimal(lockedAmount.toString().replaceAll('[^\\d.]', ''))
BigDecimal availableBalanceValue = new BigDecimal(initialBalance.toString().replaceAll('[^\\d.]', ''))

KeywordUtil.logInfo("availableBalanceValue: ${availableBalanceValue}")

boolean increaseMoneyLock = moneyLock.getCurrentLockedAmount()

'Verify Money Lock Page'
moneyLock.verifyIncreaseMoneyLockPage(increaseMoneyLock, fieldValidation)

'Enter Amount'
moneyLock.verifyAmount(fieldValidation, increaseMoneyLock, amount)

'Verify Balance Inline Message'
finalBalance = moneyLock.verifyBalanceinlineMessage(amount, availableBalanceValue)

'Tap Next'
commonPage.tapNextBtn()

'Pop Up'
moneyLock.verifyPopupMoneyLock(fieldValidation)

'Confirmation Page'
String transactionType = increaseMoneyLock ? 'Increase Money Lock Amount' : 'Money Lock'
formattedEditAmount = moneyLock.confirmationPage(increaseMoneyLock, [accountName, accountNo, *(increaseMoneyLock ? [lockedAmount] : []), 'RM ' + finalBalance, transactionType], amount, editAmount, editNewAmount)

'Secure2u'
String availableBalanceAfterLocking = editNewAmount ? formattedEditAmount : finalBalance
moneyLock.authorize(status, otherVerifyData + [accountNo, accountName, 'RM ' + availableBalanceAfterLocking, transactionType])

'Status Screen'
List<String> statusText = (status == 'Approve') ? (increaseMoneyLock ? ['Amount increased', 'Total locked amount'] : ['Locked Amount']) : ['Transaction Type', 'Money Lock']
String ref = statusScreenPage.verifyStatusScreen(titleData, statusDescription, statusScreenPage.REFERENCE_ID_9_M, statusText)

'Verify Push Notification'
if (status == 'Approve') {
	pushNotificationPage.verifyPushNotification('Maybank2u: Money Lock','Your money lock is successful. Not you? Call Maybank now.', FailureHandling.CONTINUE_ON_FAILURE)
}

'Tap Done'
commonPage.tapDoneBtn()

'Back to home'
moneyLock.tapBackUntilHome()

if (status == 'Approve') {
	'Get New Balance from Dashboard'
	String newBalance = homePage.getBalance()
	
	'Verify Balance Deduction'
	String finalAmount = editNewAmount ? editAmount : amount
	commonPage.verifyNewBalance(String.valueOf(availableBalanceValue), newBalance.replaceAll('[^\\d.]', ''), finalAmount)
	
	'Get Money Lock-ed'
	String newMoneyLockAmount = homePage.getMoneyLockAmount()
	
	'Verify Money Lock amount'
	commonPage.verifyNewBalance(String.valueOf(lockedAmountValue), newMoneyLockAmount.replaceAll('[^\\d.]', ''), finalAmount)
	
	'Get formatted amount'
	String m2uHistoryAmount = '-RM ' + String.format('%.2f', ((editNewAmount ? editAmount : amount).toString() as BigDecimal) / 100)
	
	if (MobileDriver.verifyElementExist(homePage.txtQuickActions(), GlobalVariable.G_timeoutMedium, FailureHandling.OPTIONAL)) {
		'Navigate to Account Detail'
		homePage.navigateToAccountDetail()
	}
	
	'Verify M2U History'
	accountDetailPage.verifyM2UHistoryFirstItemToday('Money Lock', m2uHistoryAmount)
	
	'Back to home'
	moneyLock.tapBackUntilHome()
}