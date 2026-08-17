import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.vcb.mobile.internal.pages.base.BasePage as BasePage
import com.vcb.mobile.internal.pages.base.CommonPage as CommonPage
import com.vcb.mobile.internal.pages.base.HomePage as HomePage
import com.vcb.mobile.internal.pages.base.accountpage.AccountsPage as AccountsPage
import com.vcb.mobile.internal.pages.base.ConfirmationPage as ConfirmationPage

'Login VCB Secure2u'
WebUI.callTestCase(findTestCase('Mobile/VCB/Common/Login Secure2u'), [('idNumber') : idNumber, ('PAN') : PAN, ('password') : password
        , ('username') : username, ('accountName') : accountName], FailureHandling.STOP_ON_FAILURE)

HomePage homePage = BasePage.getPage(HomePage)

CommonPage commonPage = BasePage.getPage(CommonPage)

AccountsPage accountsPage = BasePage.getPage(AccountsPage)

ConfirmationPage confirmationPage = BasePage.getPage(ConfirmationPage)

'Tap Button Accounts'
homePage.tapBtnAccounts()

'Choose Account'
confirmationPage.chooseAnotherAccount(recipientAccount)

'Verify Watchlist'
accountsPage.navigateToFx()

accountsPage.verifyWatchList()

'Back to Home Page'
commonPage.backToHome()