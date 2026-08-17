import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.vcb.mobile.internal.pages.base.BasePage as BasePage
import com.vcb.mobile.internal.pages.base.CommonPage as CommonPage
import com.vcb.mobile.internal.pages.base.ConfirmationPage as ConfirmationPage
import com.vcb.mobile.internal.pages.base.HomePage as HomePage
import com.vcb.mobile.internal.pages.base.Secure2uAuthorisationPage as Secure2uAuthorisationPage
import com.vcb.mobile.internal.pages.base.accountpage.AccountsPage as AccountsPage

'Login VCB Secure2u'
WebUI.callTestCase(findTestCase('Mobile/VCB/Common/Login Secure2u'), [('idNumber') : idNumber, ('PAN') : PAN, ('password') : password
        , ('username') : username, ('accountName') : accountName], FailureHandling.STOP_ON_FAILURE)

HomePage homePage = BasePage.getPage(HomePage)

CommonPage commonPage = BasePage.getPage(CommonPage)

Secure2uAuthorisationPage s2u = BasePage.getPage(Secure2uAuthorisationPage)

AccountsPage accountsPage = BasePage.getPage(AccountsPage)

ConfirmationPage confirmationPage = BasePage.getPage(ConfirmationPage)

'Tap Button Accounts'
homePage.tapBtnAccounts()

'Choose Account'
confirmationPage.chooseAnotherAccount(recipientAccount)

'Get Initial balance'
String balance = accountsPage.getG1ABalance()

'Select Convert Currency'
accountsPage.navigateToFx()

accountsPage.convertCurrency(currencyFrom, currencyTo, amount)

'Confirmation'
confirmationPage.verifyRetailFxConfirmScreen(transactionType)

'Authorisation'
s2u.authorize(status)

'Status screen'
String RefID = accountsPage.statusScreen(status, false, isFuture)

'Check new balance'
commonPage.scrollToTop()

String newBalance = accountsPage.getG1ABalance()

if (!(balance.equals(newBalance))) {
    Mobile.comment((('Balance has changed. Initial: ' + balance) + ', New: ') + newBalance)
} else {
    Mobile.comment('Balance remains the same: ' + balance)
}

'Back to Home Page'
commonPage.backToHome()