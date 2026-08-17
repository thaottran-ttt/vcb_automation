import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.maybank.common.MobileDriver
import com.maybank.mae.internal.pages.base.accountpage.AccountsPage as AccountsPage
import com.maybank.mae.internal.pages.base.BasePage as BasePage
import com.maybank.mae.internal.pages.base.CommonPage as CommonPage
import com.maybank.mae.internal.pages.base.HomePage as HomePage
import com.maybank.mae.internal.pages.base.paybill.JomPayPage as JomPayPage
import internal.GlobalVariable as GlobalVariable

MobileDriver.info("Start " + TC_name)

AccountsPage accountsPage = BasePage.getPage(AccountsPage)

CommonPage commonPage = BasePage.getPage(CommonPage)

HomePage homePage = BasePage.getPage(HomePage)

JomPayPage jomPayPage = BasePage.getPage(JomPayPage)

if(shouldCheckLogin) {
	'Login flow'
	WebUI.callTestCase(findTestCase('Mobile/MAE/Common/Login'), [('appId') : GlobalVariable.G_appPath, ('username') : userName
			, ('password') : password], FailureHandling.STOP_ON_FAILURE)
}

switch (accessFlow) {
    case 'Quick Action':
        homePage.selectQuickActions('Pay Bills')

        commonPage.checkEnterPassword(password)

        break
    case 'MAE Wallet':
        'Navigate To View All Accounts'
        CustomKeywords.'com.maybank.mae.pages.Maybank2u.viewAllAccounts'()

        'Navigate to PayBills screen'
        accountsPage.navigateToPayBillsScreen(accountName, password)

        break
    case 'Accounts Icon':
        'Select Accounts button'
        homePage.tapBtnAccounts()

        'Navigate to PayBills screen'
        accountsPage.navigateToPayBillsScreen(accountName, password)

        break
}

if(!runFavorite) {
	'Verify JomPay Screen'
	jomPayPage.verifyJomPayScreen()
}