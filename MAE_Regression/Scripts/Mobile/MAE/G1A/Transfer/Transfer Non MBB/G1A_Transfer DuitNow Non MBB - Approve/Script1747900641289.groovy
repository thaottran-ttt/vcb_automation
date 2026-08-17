import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase

import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.maybank.common.MobileDriver
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.maybank.mae.internal.pages.base.BasePage
import com.maybank.mae.internal.pages.base.CommonPage
import com.maybank.mae.internal.pages.base.CommonPage.TestObjectTypeEnum
import com.maybank.mae.internal.pages.base.HomePage
import com.maybank.mae.internal.pages.base.PushNotificationPage
import com.maybank.mae.internal.pages.base.StatusScreenPage
import com.maybank.mae.internal.pages.base.accountpage.AccountDetailPage
import com.maybank.mae.internal.pages.base.transferpage.TransferPage


WebUI.callTestCase(findTestCase('Mobile/MAE/Common/Login Secure2u'), [('idNumber') : idNumber, ('PAN') : PAN, ('password') : password
	, ('username') : username, ('accountName') : accountName], FailureHandling.STOP_ON_FAILURE)

HomePage homePage = HomePage.getPage(HomePage)
TransferPage transferPage = BasePage.getPage(TransferPage)
CommonPage commonPage = BasePage.getPage(CommonPage)
StatusScreenPage statusScreenPage = BasePage.getPage(StatusScreenPage)
PushNotificationPage pushNotificationPage = BasePage.getPage(PushNotificationPage)
AccountDetailPage accountDetailPage = AccountDetailPage.getPage(AccountDetailPage)

'Get Balance and Account Number'
String balance = homePage.getBalance()
String accountNumber = homePage.getAccountNumber()

'Select Transfer Module at QuickActions'
homePage.selectQuickActions(tranferName)

'Select Others Tab'
transferPage.selectTab(transferTab, true)

'Select Banks -> select recipientBank'
transferPage.selectBankName(recipientBank)

'Account Number'
transferPage.setAccountNumberAndTransferType(recipientAccountNo, transferType, transferMode)

'Enter Amount'
commonPage.enterNO(amount)

'Recipient Reference'
transferPage.recipientReference(recipientReference)

'Confirmation'
transferPage.confirmG1A(accountNumber, true)

'Authorisation'
transferPage.g1AAuthorize(status)

'Status screen'
transferPage.statusScreen(status, true, true)
String parsedAmount = String.format("%.2f", Double.parseDouble(amount)/100)

'Push Notification'
pushNotificationPage.verifyPushNotification("Maybank2u: Transfer", "You've transferred RM ${parsedAmount} to",
		FailureHandling.STOP_ON_FAILURE, TestObjectTypeEnum.CONTAINS, isG1A)

'Tap View All Accounts'
homePage.navigateToAccountDetail()

'Verify transaction history'
accountDetailPage.verifyM2UHistoryFirstItemToday(history, '-RM ' + parsedAmount)
