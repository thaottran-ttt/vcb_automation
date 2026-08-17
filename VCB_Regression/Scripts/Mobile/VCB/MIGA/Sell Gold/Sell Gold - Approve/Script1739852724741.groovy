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
import com.vcb.mobile.internal.pages.base.accountpage.AccountDetailPage
import com.vcb.mobile.internal.pages.base.accountpage.AccountsPage
import com.vcb.common.BaseKeyword
import com.vcb.common.MobileDriver
import com.vcb.mobile.internal.pages.base.BasePage
import com.vcb.mobile.internal.pages.base.CommonPage
import com.vcb.mobile.internal.pages.base.CommonPage.TestObjectTypeEnum
import com.vcb.mobile.internal.pages.base.HomePage
import com.vcb.mobile.internal.pages.base.PushNotificationPage
import com.vcb.mobile.internal.pages.base.Secure2uAuthorisationPage
import com.vcb.mobile.internal.pages.base.ShareReceiptPage
import com.vcb.mobile.internal.pages.base.StatusScreenPage
import com.vcb.mobile.internal.pages.base.accountpage.WealthPage
import com.vcb.utils.MapUtils
import com.vcb.utils.StringUtils

import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

WebUI.callTestCase(findTestCase('Mobile/VCB/Common/Login Secure2u'), [('idNumber') : idNumber, ('PAN') : PAN, ('username') : username, ('password') : password, ('pinCode') : GlobalVariable.G_pinCode
        , ('yourName') : GlobalVariable.G_yourName, ('accountName') : accountName, ('flowControl') : com.vcb.common.FlowControlEnum.STOP
        , ('failStopTestSuite') : false], FailureHandling.STOP_ON_FAILURE)

HomePage homePage = BasePage.getPage(HomePage)
AccountsPage accountsPage = BasePage.getPage(AccountsPage)
WealthPage wealthPage = BasePage.getPage(WealthPage)
CommonPage commonPage = BasePage.getPage(CommonPage)
Secure2uAuthorisationPage secure2uAuthorisationPage = BasePage.getPage(Secure2uAuthorisationPage)
StatusScreenPage statusScreenPage = BasePage.getPage(StatusScreenPage)
ShareReceiptPage shareReceiptPage = BasePage.getPage(ShareReceiptPage)
PushNotificationPage pushNotificationPage = BasePage.getPage(PushNotificationPage)
AccountDetailPage accountDetailPage = BasePage.getPage(AccountDetailPage)


Map transactionMap = MapUtils.parseObjectToMap(transactionMap)

'Navigate to Accounts Screen'
homePage.tapBtnAccounts()

'Navigate to Wealth Tab'
accountsPage.selectTab(tabName)
commonPage.delay(GlobalVariable.G_timeoutMiniShort)

'Tap to Miga'
wealthPage.tapItemInListView(itemName)
commonPage.waitLoading()

'Get Total purchased and current goal to verify'
wealthPage.verifyDashboard()
wealthPage.firstBalance = wealthPage.getGold()

'Buy Gole'
wealthPage.sellGold(transactionMap)

'authorisation'
secure2uAuthorisationPage.authorize(status)

'verify status screen'
String refID = statusScreenPage.verifyStatusScreen(statusTitle, statusDescription)

'Add refID to list and verify receipt'
wealthPage.migaList = new ArrayList()
wealthPage.migaList.add(refID)
if(status == 'Approve') {
	wealthPage.verifyReceipt()
	commonPage.waitLoading()
	
	'Generate notification'
	String notificationDescription = wealthPage.generateNotification(transactionMap.get("type"))
	
	'Verify notification'
	pushNotificationPage.verifyPushNotification(notificationTitle, notificationDescription, BasePage.defaultFailureHandling, TestObjectTypeEnum.CONTAINS)
	
	'Verify receipt'
	wealthPage.shareMigaReceipts(wealthPage.migaList)
	commonPage.tapDoneBtn()
	
	'Verify after making transaction'
	wealthPage.lastBalance = wealthPage.getGold()
	wealthPage.verifyGoldAfterSell(wealthPage.firstBalance, wealthPage.lastBalance, transactionMap.get("amount"))
	
	'Verify Transaction'
	wealthPage.verifyTransactionHistory(transactionMap.get("type"))
	
	'Back to Home Page and navigate to account'
	commonPage.backToHome()
	homePage.navigateToAccountDetail()
	
	'Verify M2U History'
	accountDetailPage.verifyM2UHistoryFirstItemToday(transactionMap.get("type"))
	
}


