import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.maybank.mae.internal.pages.base.accountpage.AccountDetailPage
import com.maybank.mae.internal.pages.base.accountpage.AccountsPage
import com.maybank.mae.internal.pages.base.BasePage
import com.maybank.mae.internal.pages.base.CommonPage
import com.maybank.mae.internal.pages.base.HomePage
import internal.GlobalVariable

Mobile.comment('Start TC: ' + TC_name)

AccountsPage accountsPage = BasePage.getPage(AccountsPage)

AccountDetailPage accountDetailPage = BasePage.getPage(AccountDetailPage)

CommonPage commonPage = BasePage.getPage(CommonPage)

HomePage homePage = BasePage.getPage(HomePage)

'Check account login'
if (!homePage.checkIsAccountLogin()) {
    'Login flow'
    WebUI.callTestCase(findTestCase('Mobile/MAE/Common/Login'), [('appId') : GlobalVariable.G_appPath, ('username') : userName
            , ('password') : password, ('pinCode') : '111111', ('yourName') : 'Test'], FailureHandling.STOP_ON_FAILURE)
}

'Navigate To View All Accounts'
CustomKeywords.'com.maybank.mae.pages.Maybank2u.viewAllAccounts'()

'Enter PIN'
commonPage.checkEnterPIN()

'Select account name'
accountsPage.selectTab(accountName)

'View share receipt'
accountDetailPage.viewShareReceipt(receiptTitle, transactionType, filterType)