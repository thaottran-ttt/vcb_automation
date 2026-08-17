import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase

import com.kms.katalon.core.model.FailureHandling
import com.vcb.mobile.internal.pages.base.accountpage.AccountDetailPage
import com.vcb.mobile.internal.pages.base.accountpage.AccountsPage
import com.vcb.mobile.internal.pages.base.BasePage
import com.vcb.mobile.internal.pages.base.CommonPage
import com.vcb.mobile.internal.pages.base.fixeddepositpage.FixedDepositPage
import com.vcb.mobile.internal.pages.base.HomePage

import internal.GlobalVariable

import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

HomePage homePage = HomePage.getPage(HomePage)

AccountDetailPage accountDetailPage = BasePage.getPage(AccountDetailPage)


WebUI.callTestCase(findTestCase('Mobile/VCB/Common/Login'), [('username') : username, ('password') : password
        , ('pinCode') : GlobalVariable.G_pinCode, ('yourName') : GlobalVariable.G_yourName, ('accountName') : '', ('flowControl') : com.vcb.common.FlowControlEnum.STOP
        , ('failStopTestSuite') : false], FailureHandling.STOP_ON_FAILURE)


'Tap View All Accounts'
homePage.navigateToAccountDetail()

'Uplift Transaction History'
accountDetailPage.verifyTransactionHistory(transactionType)
