import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase

import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.maybank.mae.internal.pages.base.BasePage
import com.maybank.mae.internal.pages.base.CommonPage
import com.maybank.mae.internal.pages.base.HomePage
import com.maybank.mae.internal.pages.base.accountpage.AccountsPage

import internal.GlobalVariable


WebUI.callTestCase(findTestCase('Mobile/MAE/Common/Login'), [('username') : username, ('password') : password, ('pinCode') : GlobalVariable.G_pinCode
        , ('yourName') : GlobalVariable.G_yourName, ('accountName') : '', ('flowControl') : com.maybank.common.FlowControlEnum.STOP
        , ('failStopTestSuite') : false], FailureHandling.STOP_ON_FAILURE)

HomePage homePage = BasePage.getPage(HomePage)
AccountsPage accountsPage = BasePage.getPage(AccountsPage)

'Navigate To Maybank 2u'
homePage.tapBtnAccounts()

'Select Accounts Button'
accountsPage.selectTab(tabName)

'Get Account Info'
CustomKeywords.'com.maybank.mae.pages.Account.getAccountInfo'()
