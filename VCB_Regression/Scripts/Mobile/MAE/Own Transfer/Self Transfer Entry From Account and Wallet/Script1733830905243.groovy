import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase

import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.maybank.mae.internal.pages.base.BasePage
import com.maybank.mae.internal.pages.base.CommonPage
import com.maybank.mae.internal.pages.base.HomePage
import com.maybank.mae.internal.pages.base.Secure2uAuthorisationPage
import com.maybank.mae.internal.pages.base.accountpage.AccountsPage
import com.maybank.mae.internal.pages.base.transferpage.TransferPage

import internal.GlobalVariable as GlobalVariable

WebUI.callTestCase(findTestCase('Mobile/MAE/Common/Login'), [('idNumber') : idNumber, ('PAN') : PAN, ('password') : GlobalVariable.G_password
        , ('username') : username, ('accountName') : '', ('flowControl') : com.maybank.common.FlowControlEnum.STOP, ('failStopTestSuite') : false
        , ('yourName') : 'Test'], FailureHandling.STOP_ON_FAILURE)

HomePage homePage = BasePage.getPage(HomePage)

CommonPage commonPage = BasePage.getPage(CommonPage)

TransferPage transferPage = BasePage.getPage(TransferPage)

Secure2uAuthorisationPage s2u = BasePage.getPage(Secure2uAuthorisationPage)

String balance = homePage.getBalance()

AccountsPage accountsPage = BasePage.getPage(AccountsPage)

String accountName = homePage.getAccountName()

'Tap Accounts'
homePage.tapBtnAccounts()

'Verify account Dashboard'
accountsPage.selectAccount(wallet)

'Select transfer tab on the accounts page'
accountsPage.selectTab(tabName)

'Verify Transfer Title'
commonPage.verifyTextVisible(title, GlobalVariable.G_timeoutMedium)

'Back to Home'
commonPage.backToHome()

'Tap to wallet'
commonPage.tapText(accountName, GlobalVariable.G_timeoutMedium)

'Verify account Dashboard'
accountsPage.selectAccount(wallet)

'Select transfer tab on the accounts page'
accountsPage.selectTab(tabName)

'Verify Transfer Title'
commonPage.verifyTextVisible(title, GlobalVariable.G_timeoutMedium)