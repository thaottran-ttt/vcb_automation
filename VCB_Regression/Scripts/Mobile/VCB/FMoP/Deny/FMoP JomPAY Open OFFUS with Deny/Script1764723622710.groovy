import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase

import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.vcb.common.MobileDriver
import com.vcb.mobile.internal.pages.base.accountpage.AccountDetailPage
import com.vcb.mobile.internal.pages.base.accountpage.AccountsPage
import com.vcb.mobile.internal.pages.base.BasePage
import com.vcb.mobile.internal.pages.base.CommonPage
import com.vcb.mobile.internal.pages.base.HomePage
import com.vcb.mobile.internal.pages.base.paybill.JomPayPage
import com.vcb.mobile.internal.pages.base.StatusScreenPage
import com.vcb.utils.StringUtils
import com.vcb.mobile.internal.pages.base.Secure2uAuthorisationPage
import internal.GlobalVariable as GlobalVariable

MobileDriver.info("Start " + TC_name)
CommonPage commonPage = BasePage.getPage(CommonPage)
StatusScreenPage statusScreenPage = BasePage.getPage(StatusScreenPage)
HomePage homePage = BasePage.getPage(HomePage)
JomPayPage jomPayPage = BasePage.getPage(JomPayPage)
AccountsPage accountsPage = BasePage.getPage(AccountsPage)
AccountDetailPage accountDetailPage = BasePage.getPage(AccountDetailPage)
Secure2uAuthorisationPage secure2uAuthorisationPage = BasePage.getPage(Secure2uAuthorisationPage)

WebUI.callTestCase(findTestCase('Mobile/VCB/Common/Login Secure2u'), [('idNumber') : idNumber, ('PAN') : PAN, ('password') : password
        , ('username') : username, ('accountName') : accountName, ('flowControl') : com.vcb.common.FlowControlEnum.STOP
        , ('failStopTestSuite') : false, ('yourName') : GlobalVariable.G_yourName], FailureHandling.STOP_ON_FAILURE)

if (status == 'Insufficient') {
	String balance = homePage.getBalance()
	int balanceNumber = commonPage.convertAmountToNumber(balance)
	int insufficientAmount = balanceNumber + 1
	amount = insufficientAmount.toString()
}

WebUI.callTestCase(findTestCase('Test Cases/Mobile/VCB/JomPay/Access JomPay Section'), [('userName') : username, ('password') : password, runFavorite:runFavorite, shouldCheckLogin: false], 
    FailureHandling.STOP_ON_FAILURE)

if(runFavorite) {
	'Select JomPAY'
	jomPayPage.selectJomPAY()
	jomPayPage.selectFavourite(favoriteName)
}else {
	'Enter PayBills Info'
	jomPayPage.enterJomPayInfo(billerCode, reference1, reference2)
}

'Enter Amount'
commonPage.enterNO(amount)

'Confirm to pay'
jomPayPage.confirm(Boolean.parseBoolean(isFuture))


if (status != 'Max Limit') {
	'Select approval'
	secure2uAuthorisationPage.authorize(status)
}

'Status Screen'
String ref = statusScreenPage.verifyStatusScreenJomPay(statusTitle, statusDescription)
