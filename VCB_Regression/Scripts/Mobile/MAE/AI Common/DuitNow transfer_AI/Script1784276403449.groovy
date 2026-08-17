import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase

import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.maybank.common.MobileDriver
import com.maybank.mae.internal.pages.base.BasePage
import com.maybank.mae.internal.pages.base.CommonPage
import com.maybank.mae.internal.pages.base.ConfirmationPage
import com.maybank.mae.internal.pages.base.EnterAmountPage
import com.maybank.mae.internal.pages.base.HomePage
import com.maybank.mae.internal.pages.base.PushNotificationPage
import com.maybank.mae.internal.pages.base.Secure2uAuthorisationPage
import com.maybank.mae.internal.pages.base.StatusScreenPage
import com.maybank.mae.internal.pages.base.accountpage.AccountDetailPage
import com.maybank.mae.internal.pages.base.transferpage.EnterDuitnowProxyPage
import com.maybank.mae.internal.pages.base.transferpage.RecipientReferencePage
import com.maybank.mae.internal.pages.base.transferpage.TransferPage

import internal.GlobalVariable as GlobalVariable

HomePage homePage = BasePage.getPage(HomePage)
TransferPage transferPage = BasePage.getPage(TransferPage)
CommonPage commonPage = BasePage.getPage(CommonPage)
EnterDuitnowProxyPage enterDuitnowProxyPage = BasePage.getPage(EnterDuitnowProxyPage)
EnterAmountPage enterAmountPage = BasePage.getPage(EnterAmountPage)
RecipientReferencePage recipientReferencePage = BasePage.getPage(RecipientReferencePage)
ConfirmationPage confirmationPage = BasePage.getPage(ConfirmationPage)
Secure2uAuthorisationPage secure2uAuthorisationPage = BasePage.getPage(Secure2uAuthorisationPage)
StatusScreenPage statusScreenPage = BasePage.getPage(StatusScreenPage)
PushNotificationPage pushNotificationPage = BasePage.getPage(PushNotificationPage)
AccountDetailPage accountDetailPage = BasePage.getPage(AccountDetailPage)

'Login MAE Secure2u'
WebUI.callTestCase(findTestCase('Mobile/MAE/Common/Login Secure2u'), [('idNumber') : idNumber, ('PAN') : PAN, ('password') : password
	, ('username') : username, ('accountName') : accountName], FailureHandling.STOP_ON_FAILURE)

'Select Transfer at Quick Actions'
homePage.selectQuickActions(tranferName)

'Select DuitNow Tab'
transferPage.selectTab(transferTab, true)

'Select Others - Open DuitNow flow'
transferPage.openDuitnowFlow()
MobileDriver.delay(GlobalVariable.G_timeoutMiniShort)

'Select ID type and enter proxy number'
enterDuitnowProxyPage.chooseProxyType(product)
enterDuitnowProxyPage.enterProxyNumber(product, [productData, productData2])

'Enter Amount'
enterAmountPage.enterAmountDuitNow(amount)

'Enter Recipient Reference'
recipientReferencePage.inputRecipientReference()

'Confirmation Page'
confirmationPage.verifyHasLandedOnConfirmationScreen()
confirmationPage.proceedToAuthorisation()

'Authorisation'
secure2uAuthorisationPage.authorize(status)

'Status Screen'
def RefID = statusScreenPage.verifyDuitnowStatusUI(status, futuretype)

if (status.equals('Approve')) {
	statusScreenPage.verifyShareReceipt(futuretype)
	statusScreenPage.addFavoritesDuitnow(favName, isOnUs)
}

statusScreenPage.backToEntryPoint()

if (status.equals('Approve')) {
	'Push Notification'
	pushNotificationPage.verifyTransferNotificationDuitNow(product, productData, RefID, Double.parseDouble(amount) / 100)
}

'Tap View All Accounts'
homePage.navigateToAccountDetail()

'Verify transaction history'
String parsedAmount = String.format("%.2f", Double.parseDouble(amount) / 100)
accountDetailPage.verifyM2UHistoryFirstItemToday(history, '-RM ' + parsedAmount)
