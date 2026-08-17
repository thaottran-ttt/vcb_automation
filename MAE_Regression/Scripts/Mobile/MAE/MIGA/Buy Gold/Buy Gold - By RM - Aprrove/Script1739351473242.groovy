import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase

import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.maybank.mae.internal.pages.base.BasePage
import com.maybank.mae.internal.pages.base.CommonPage
import com.maybank.mae.internal.pages.base.CommonPage.TestObjectTypeEnum
import com.maybank.mae.internal.pages.base.HomePage
import com.maybank.mae.internal.pages.base.PushNotificationPage
import com.maybank.mae.internal.pages.base.Secure2uAuthorisationPage
import com.maybank.mae.internal.pages.base.ShareReceiptPage
import com.maybank.mae.internal.pages.base.StatusScreenPage
import com.maybank.mae.internal.pages.base.accountpage.AccountDetailPage
import com.maybank.mae.internal.pages.base.accountpage.AccountsPage
import com.maybank.mae.internal.pages.base.accountpage.WealthPage
import com.maybank.utils.MapUtils

import internal.GlobalVariable as GlobalVariable

WebUI.callTestCase(findTestCase('Mobile/MAE/Common/Login Secure2u'), [('idNumber') : idNumber, ('PAN') : PAN, ('username') : username
        , ('password') : password, ('pinCode') : GlobalVariable.G_pinCode, ('yourName') : GlobalVariable.G_yourName, ('accountName') : accountName
        , ('flowControl') : com.maybank.common.FlowControlEnum.STOP, ('failStopTestSuite') : false], FailureHandling.STOP_ON_FAILURE)

HomePage homePage = BasePage.getPage(HomePage)

AccountsPage accountsPage = BasePage.getPage(AccountsPage)

WealthPage wealthPage = BasePage.getPage(WealthPage)

CommonPage commonPage = BasePage.getPage(CommonPage)

Secure2uAuthorisationPage secure2uAuthorisationPage = BasePage.getPage(Secure2uAuthorisationPage)

StatusScreenPage statusScreenPage = BasePage.getPage(StatusScreenPage)

PushNotificationPage pushNotificationPage = BasePage.getPage(PushNotificationPage)

ShareReceiptPage shareReceiptPage = BasePage.getPage(ShareReceiptPage)

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
wealthPage.firstBalance = wealthPage.getBalance()

'Buy Gole'
wealthPage.buyGold(transactionMap, isTransferByGold)

'authorisation'
secure2uAuthorisationPage.authorize(status)

'verify status screen'
String refID = statusScreenPage.verifyStatusScreen(statusTitle, statusDescription)

'Add refID to list and verify receipt'
wealthPage.migaList = new ArrayList()
wealthPage.migaList.add(refID.replace("M", "").trim())
if (status == 'Approve') {
	'Verify receipt and get data for validation'
    wealthPage.verifyReceipt()

    'Generate notification'
    String notificationDescription = wealthPage.generateNotification(transactionMap.get('type')) 
	
	'Verify notification'
	pushNotificationPage.verifyPushNotification(notificationTitle, notificationDescription, BasePage.defaultFailureHandling, TestObjectTypeEnum.CONTAINS)
	
	'Verify share receipt'
	wealthPage.shareMigaReceipts(wealthPage.migaList)
	commonPage.tapDoneBtn()
	
	'Verify after making transaction'
	wealthPage.lastBalance = wealthPage.getBalance()
	
	Mobile.comment("Balance - Before: ${wealthPage.firstBalance} | After: ${wealthPage.lastBalance}")
	wealthPage.verifyBalanceAfterBuyingGold(wealthPage.firstBalance, wealthPage.lastBalance, transactionMap.get("amount"))
	
	'Verify Transaction'
	wealthPage.verifyTransactionHistory(transactionMap.get("type"))
	
	'Back to Home Page and navigate to account'
	commonPage.backToHome()
	homePage.tapBtnAccounts()
	
	'Navigate to Account Detail'
	homePage.navigateToAccountDetail()
	
	'Verify M2U Transaction'
	accountDetailPage.verifyReprintReceiptWithData(transactionMap.get("type"), wealthPage.migaList)
}



