import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.vcb.mobile.internal.pages.base.BasePage as BasePage
import com.vcb.mobile.internal.pages.base.CommonPage as CommonPage
import com.vcb.mobile.internal.pages.base.ConfirmationPage as ConfirmationPage
import com.vcb.mobile.internal.pages.base.HomePage as HomePage
import com.vcb.mobile.internal.pages.base.accountpage.AccountDetailPage as AccountDetailPage
import com.vcb.mobile.internal.pages.base.accountpage.AccountsPage as AccountsPage
import internal.GlobalVariable as GlobalVariable

HomePage homePage = HomePage.getPage(HomePage)

CommonPage commonPage = BasePage.getPage(CommonPage)

AccountsPage accountsPage = BasePage.getPage(AccountsPage)

ConfirmationPage confirmationPage = BasePage.getPage(ConfirmationPage)

AccountDetailPage accountDetailPage = BasePage.getPage(AccountDetailPage)

WebUI.callTestCase(findTestCase('Mobile/VCB/Common/Login Secure2u'), [('idNumber') : idNumber, ('PAN') : PAN, ('password') : GlobalVariable.G_password
        , ('username') : username, ('accountName') : '', ('flowControl') : com.vcb.common.FlowControlEnum.STOP, ('failStopTestSuite') : false], 
    FailureHandling.STOP_ON_FAILURE)

'Select Accounts button'
homePage.tapBtnAccounts()

'Tap account'
accountsPage.selectAccount(accountName)

'View share receipt'
accountDetailPage.viewReprintReceipt(transactionType)

commonPage.backToHome()