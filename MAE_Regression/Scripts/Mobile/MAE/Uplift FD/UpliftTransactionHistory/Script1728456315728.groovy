import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase

import com.kms.katalon.core.model.FailureHandling
import com.maybank.mae.internal.pages.base.accountpage.AccountDetailPage
import com.maybank.mae.internal.pages.base.accountpage.AccountsPage
import com.maybank.mae.internal.pages.base.BasePage
import com.maybank.mae.internal.pages.base.CommonPage
import com.maybank.mae.internal.pages.base.fixeddepositpage.FixedDepositPage
import com.maybank.mae.internal.pages.base.HomePage

import internal.GlobalVariable

import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

HomePage homePage = HomePage.getPage(HomePage)

AccountDetailPage accountDetailPage = BasePage.getPage(AccountDetailPage)


WebUI.callTestCase(findTestCase('Mobile/MAE/Common/Login'), [('username') : username, ('password') : password
        , ('pinCode') : GlobalVariable.G_pinCode, ('yourName') : GlobalVariable.G_yourName, ('accountName') : '', ('flowControl') : com.maybank.common.FlowControlEnum.STOP
        , ('failStopTestSuite') : false], FailureHandling.STOP_ON_FAILURE)


'Tap View All Accounts'
homePage.navigateToAccountDetail()

'Uplift Transaction History'
accountDetailPage.verifyTransactionHistory(transactionType)
