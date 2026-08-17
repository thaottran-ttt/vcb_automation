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

WebUI.callTestCase(findTestCase('Mobile/MAE/Common/Login'), [('username') : username, ('password') : password, ('pinCode') : GlobalVariable.G_pinCode
	, ('yourName') : GlobalVariable.G_yourName, ('accountName') : accountName, ('flowControl') : com.maybank.common.FlowControlEnum.STOP
	, ('failStopTestSuite') : false], FailureHandling.STOP_ON_FAILURE)

'Verify M2U history'
homePage.tapBtnAccounts()
accountsPage.selectTab(cardsTab)
accountsPage.selectAccount(cardName)
commonPage.delay(GlobalVariable.G_timeoutMiniShort)

'Select Pay Card tab'
MobileDriver.scrollToText(tranferName)
commonPage.tapButton(tranferName, GlobalVariable.G_timeoutLong)

'Select card to pay'
MobileDriver.scrollToText(cardName)
commonPage.tapButton(cardName, GlobalVariable.G_timeoutLong)

commonPage.waitLoading()

'Select any amount'
commonPage.tapButton(payCardType, GlobalVariable.G_timeoutLong)

'Enter Amount'
commonPage.enterNO(amount, false)
