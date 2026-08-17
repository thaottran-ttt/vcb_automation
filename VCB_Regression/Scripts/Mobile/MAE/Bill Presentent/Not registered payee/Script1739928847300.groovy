import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase

import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.maybank.common.MobileDriver
import com.maybank.mae.internal.pages.base.accountpage.AccountDetailPage
import com.maybank.mae.internal.pages.base.accountpage.AccountsPage
import com.maybank.mae.internal.pages.base.BasePage
import com.maybank.mae.internal.pages.base.CommonPage
import com.maybank.mae.internal.pages.base.HomePage
import com.maybank.mae.internal.pages.base.paybill.JomPayPage

import internal.GlobalVariable

import com.maybank.mae.internal.pages.base.Secure2uAuthorisationPage


CommonPage commonPage = BasePage.getPage(CommonPage)

HomePage homePage = BasePage.getPage(HomePage)

JomPayPage jomPayPage = BasePage.getPage(JomPayPage)

AccountsPage accountsPage = BasePage.getPage(AccountsPage)

AccountDetailPage accountDetailPage = BasePage.getPage(AccountDetailPage)

Secure2uAuthorisationPage secure2uAuthorisationPage = BasePage.getPage(Secure2uAuthorisationPage)

WebUI.callTestCase(findTestCase('Mobile/MAE/Common/Login'), [('idNumber') : idNumber, ('PAN') : PAN, ('password') : password
            , ('username') : userName, ('accountName') : accountName], FailureHandling.STOP_ON_FAILURE)


WebUI.callTestCase(findTestCase('Test Cases/Mobile/MAE/JomPay/Access JomPay Section'), [('userName') : userName, ('password') : password, runFavorite:runFavorite, shouldCheckLogin: false], 
    FailureHandling.STOP_ON_FAILURE)

'Enter PayBills Info'
jomPayPage.enterJomPayInfo(billerCode, reference1, reference2)


commonPage.verifyElementVisible(commonPage.lblNormalizeSpace(message), GlobalVariable.G_timeoutLong, FailureHandling.STOP_ON_FAILURE)