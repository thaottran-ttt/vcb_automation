import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase

import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.maybank.mae.internal.pages.base.BasePage
import com.maybank.mae.internal.pages.base.CommonPage
import com.maybank.mae.internal.pages.base.CommonPage.TestObjectTypeEnum
import com.maybank.mae.internal.pages.base.ConfirmationPage
import com.maybank.mae.internal.pages.base.HomePage
import com.maybank.mae.internal.pages.base.PushNotificationPage
import com.maybank.mae.internal.pages.base.Secure2uAuthorisationPage
import com.maybank.mae.internal.pages.base.accountpage.AccountsPage as AccountsPage
import com.maybank.mae.internal.pages.base.accountpage.AccountDetailPage

'Login MAE Secure2u'
WebUI.callTestCase(findTestCase('Mobile/MAE/Common/Login Secure2u'), [('idNumber') : idNumber, ('PAN') : PAN, ('password') : password
        , ('username') : username, ('accountName') : accountName], FailureHandling.STOP_ON_FAILURE)

HomePage homePage = BasePage.getPage(HomePage)

CommonPage commonPage = BasePage.getPage(CommonPage)

Secure2uAuthorisationPage s2u = BasePage.getPage(Secure2uAuthorisationPage)

AccountsPage accountsPage = BasePage.getPage(AccountsPage)

AccountDetailPage accountDetailPage = BasePage.getPage(AccountDetailPage)

ConfirmationPage confirmationPage = BasePage.getPage(ConfirmationPage)

PushNotificationPage pushNotificationPage = BasePage.getPage(PushNotificationPage)

'Tap Button Accounts'
homePage.tapBtnAccounts()

'Choose Account'
confirmationPage.chooseAnotherAccount(recipientAccount)

'Get Initial balance'
String balance = accountsPage.getG1ABalance()

'Select Convert Currency'
accountsPage.navigateToFx()

accountsPage.convertCurrency(currencyFrom, currencyTo, amount, true)

'Confirmation'
confirmationPage.verifyRetailFxConfirmScreen(transactionType)

'Authorisation'
s2u.authorize(status)

'Status screen'
String refID = accountsPage.statusScreen(status, false, isFuture)

'Check new balance'
commonPage.scrollToTop()

String newBalance = accountsPage.getG1ABalance()

if (!(balance.equals(newBalance)) || (isTransferFuture)) {
    Mobile.comment((('Balance has changed. Initial: ' + balance) + ', New: ') + newBalance)
} else {
    Mobile.comment('Balance remains the same: ' + balance)
}
'Back to Home Page'
commonPage.backToHome()