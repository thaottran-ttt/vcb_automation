import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase

import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.maybank.common.MobileDriver
import com.maybank.mae.internal.pages.base.BasePage
import com.maybank.mae.internal.pages.base.CommonPage
import com.maybank.mae.internal.pages.base.CommonPage.TestObjectTypeEnum
import com.maybank.mae.internal.pages.base.ConfirmationPage
import com.maybank.mae.internal.pages.base.FavouritesPage
import com.maybank.mae.internal.pages.base.HomePage
import com.maybank.mae.internal.pages.base.PushNotificationPage
import com.maybank.mae.internal.pages.base.Secure2uAuthorisationPage
import com.maybank.mae.internal.pages.base.ShareReceiptPage
import com.maybank.mae.internal.pages.base.StatusScreenPage
import com.maybank.mae.internal.pages.base.accountpage.AccountDetailPage
import com.maybank.mae.internal.pages.base.accountpage.AccountsPage
import com.maybank.mae.internal.pages.base.paybill.PayBillPage
import com.maybank.mae.internal.pages.base.transferpage.TransferPage
import com.maybank.mae.pages.Account
import com.maybank.utils.StringUtils

import internal.GlobalVariable as GlobalVariable


CommonPage commonPage = BasePage.getPage(CommonPage)
HomePage homePage = HomePage.getPage(HomePage)
AccountsPage accountsPage = BasePage.getPage(AccountsPage)
AccountDetailPage accountDetailPage = BasePage.getPage(AccountDetailPage)
PayBillPage payBillPage = BasePage.getPage(PayBillPage)
TransferPage transferPage = BasePage.getPage(TransferPage)
ConfirmationPage confirmationPage = BasePage.getPage(ConfirmationPage)
Secure2uAuthorisationPage secure2uAuthorisationPage = BasePage.getPage(Secure2uAuthorisationPage)
StatusScreenPage statusScreenPage = BasePage.getPage(StatusScreenPage)
ShareReceiptPage shareReceiptPage = BasePage.getPage(ShareReceiptPage)
FavouritesPage favouritesPage = BasePage.getPage(FavouritesPage)
PushNotificationPage pushNotificationPage = BasePage.getPage(PushNotificationPage)

WebUI.callTestCase(findTestCase('Mobile/MAE/Common/Login Secure2u'), [('idNumber') : idNumber, ('PAN') : PAN, ('password') : password
		, ('username') : username, ('accountName') : accountName, ('flowControl') : com.maybank.common.FlowControlEnum.STOP
		, ('failStopTestSuite') : false], FailureHandling.STOP_ON_FAILURE)


String formattedPayAmount = 'RM ' + StringUtils.divideBy100(amount)

Map cardInfo = Account.getAccountInfoFromHomePage(cardsTab, cardName)

Map accountInfo = Account.getAccountInfoFromHomePage(accountsTab, accountName)
'Get account number'
String accountNumber = accountInfo['accountNo']
'Get account balance after transaction'
String accountBalanceBefore = accountInfo['balance']

