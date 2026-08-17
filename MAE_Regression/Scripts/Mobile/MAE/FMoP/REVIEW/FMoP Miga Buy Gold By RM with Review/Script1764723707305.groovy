import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase

import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.maybank.mae.internal.pages.base.BasePage
import com.maybank.mae.internal.pages.base.CommonPage
import com.maybank.mae.internal.pages.base.CommonPage.TestObjectTypeEnum
import com.maybank.mae.internal.pages.base.HomePage
import com.maybank.mae.internal.pages.base.PushNotificationPage
import com.maybank.mae.internal.pages.base.Secure2uAuthorisationPage
import com.maybank.mae.internal.pages.base.ShareReceiptPage
import com.maybank.mae.internal.pages.base.StatusScreenPage
import com.maybank.mae.internal.pages.base.accountpage.AccountDetailPage
import com.maybank.mae.internal.pages.base.accountpage.AccountsPage
import com.maybank.mae.internal.pages.base.accountpage.WealthPage
import com.maybank.utils.MapUtils

import internal.GlobalVariable as GlobalVariable

WebUI.callTestCase(findTestCase('Mobile/MAE/Common/Login Secure2u With Secure Question'), [('idNumber') : idNumber, ('PAN') : PAN, ('password') : password
        , ('username') : username, ('accountName') : accountName, ('flowControl') : com.maybank.common.FlowControlEnum.STOP
        , ('failStopTestSuite') : false, ('yourName') : GlobalVariable.G_yourName], FailureHandling.STOP_ON_FAILURE)

HomePage homePage = BasePage.getPage(HomePage)
AccountsPage accountsPage = BasePage.getPage(AccountsPage)
WealthPage wealthPage = BasePage.getPage(WealthPage)
CommonPage commonPage = BasePage.getPage(CommonPage)
Secure2uAuthorisationPage secure2uAuthorisationPage = BasePage.getPage(Secure2uAuthorisationPage)
StatusScreenPage statusScreenPage = BasePage.getPage(StatusScreenPage)
PushNotificationPage pushNotificationPage = BasePage.getPage(PushNotificationPage)
ShareReceiptPage shareReceiptPage = BasePage.getPage(ShareReceiptPage)
AccountDetailPage accountDetailPage = BasePage.getPage(AccountDetailPage)

Map transactionMap = MapUtils.parseObjectToMap(transactionMap)

'Navigate to Accounts Screen'
homePage.tapBtnAccounts()

'Navigate to Wealth Tab'
accountsPage.selectTab(tabName)

commonPage.delay(GlobalVariable.G_timeoutMiniShort)

'Tap to Miga'
wealthPage.tapItemInListView(itemName)

commonPage.waitLoading()

'Get Total purchased and current goal to verify'
wealthPage.verifyDashboard()
wealthPage.firstBalance = wealthPage.getBalance()

'Buy Gole'
wealthPage.buyGold(transactionMap, isTransferByGold)

'Enter Answer For Security Question'
CustomKeywords.'com.maybank.mae.pages.SecurityQuestion.inputAnswer'(securityanswer)

'Authorisation'
String statusType = secure2uAuthorisationPage.authorizeS2UV5BioPinWithoutScrolling(status)

if(statusType.contains(Secure2uAuthorisationPage.AuthorisationType.TAC.toString())){
	MobileDriver.delay(GlobalVariable.G_timeoutShort)
	'Wait Loading'
	commonPage.waitLoading()
	if(!statusScreenPage.waitloadStatusScreen()) {
		return
	}
}

'verify status screen'
statusScreenPage.verifyStatusScreen(statusTitle, statusDescription)
