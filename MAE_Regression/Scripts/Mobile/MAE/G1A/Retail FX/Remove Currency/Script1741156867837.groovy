import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase

import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.maybank.mae.internal.pages.base.BasePage
import com.maybank.mae.internal.pages.base.CommonPage
import com.maybank.mae.internal.pages.base.ConfirmationPage
import com.maybank.mae.internal.pages.base.HomePage
import com.maybank.mae.internal.pages.base.accountpage.AccountsPage as AccountsPage

'Login MAE Secure2u'
WebUI.callTestCase(findTestCase('Mobile/MAE/Common/Login Secure2u'), [('idNumber') : idNumber, ('PAN') : PAN, ('password') : password
        , ('username') : username, ('accountName') : accountName], FailureHandling.STOP_ON_FAILURE)

HomePage homePage = BasePage.getPage(HomePage)

CommonPage commonPage = BasePage.getPage(CommonPage)

AccountsPage accountsPage = BasePage.getPage(AccountsPage)

ConfirmationPage confirmationPage = BasePage.getPage(ConfirmationPage)

'Tap Button Accounts'
homePage.tapBtnAccounts()

'Choose Account'
confirmationPage.chooseAnotherAccount(recipientAccount)

'Select remove currency'
accountsPage.removeCurrency(currency)

'Back to Home Page'
commonPage.backToHome()