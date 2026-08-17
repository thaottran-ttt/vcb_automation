import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase

import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.vcb.assertions.BaseAssert
import com.vcb.common.MobileDriver
import com.vcb.mobile.internal.pages.base.BasePage
import com.vcb.mobile.internal.pages.base.CommonPage
import com.vcb.mobile.internal.pages.base.ConfirmationPage
import com.vcb.mobile.internal.pages.base.FavouritesPage
import com.vcb.mobile.internal.pages.base.HomePage
import com.vcb.mobile.internal.pages.base.PushNotificationPage
import com.vcb.mobile.internal.pages.base.Secure2uAuthorisationPage
import com.vcb.mobile.internal.pages.base.Secure2uAuthorisationPage.S2UStatusEnum
import com.vcb.mobile.internal.pages.base.ShareReceiptPage
import com.vcb.mobile.internal.pages.base.StatusScreenPage
import com.vcb.mobile.internal.pages.base.accountpage.AccountDetailPage
import com.vcb.mobile.internal.pages.base.accountpage.AccountsPage
import com.vcb.mobile.internal.pages.base.paybill.PayBillPage
import com.vcb.mobile.internal.pages.base.paybill.PayBillPage.PayBillTestCaseType
import com.vcb.mobile.internal.pages.base.transferpage.TransferPage
import com.vcb.utils.StringUtils

import internal.GlobalVariable as GlobalVariable


WebUI.callTestCase(findTestCase('Mobile/VCB/Common/Login Secure2u'), [('idNumber') : idNumber, ('PAN') : PAN, ('password') : password
		, ('username') : username, ('accountName') : accountName, ('flowControl') : com.vcb.common.FlowControlEnum.STOP
		, ('failStopTestSuite') : false], FailureHandling.STOP_ON_FAILURE)

HomePage homePage = HomePage.getPage(HomePage)
PayBillPage payBillPage = BasePage.getPage(PayBillPage)
CommonPage commonPage = BasePage.getPage(CommonPage)
TransferPage transferPage = BasePage.getPage(TransferPage)
ConfirmationPage confirmationPage = BasePage.getPage(ConfirmationPage)
Secure2uAuthorisationPage secure2uAuthorisationPage = BasePage.getPage(Secure2uAuthorisationPage)
StatusScreenPage statusScreenPage = BasePage.getPage(StatusScreenPage)
ShareReceiptPage shareReceiptPage = BasePage.getPage(ShareReceiptPage)
FavouritesPage favouritesPage = BasePage.getPage(FavouritesPage)
PushNotificationPage pushNotificationPage = BasePage.getPage(PushNotificationPage)
AccountsPage accountsPage = BasePage.getPage(AccountsPage)
AccountDetailPage accountDetailPage = BasePage.getPage(AccountDetailPage)

String amount = amount

homePage.tapBtnAccounts()
accountsPage.selectTab(tabName)
accountsPage.selectAccount(cardName)
commonPage.delay(GlobalVariable.G_timeoutMiniShort)
'Get balance'
String balance = accountDetailPage.getAccountBalanceAmountValue()

'Check UI is Bayar bill or Pay bill'
if (commonPage.waitForElementNotPresent(commonPage.lblDynamic(tranferName), GlobalVariable.G_timeoutShort)) {
	tranferName = 'Pay Bills'
}

MobileDriver.scrollToText(tranferName)
commonPage.tapButton(tranferName, GlobalVariable.G_timeoutLong)

'Check flow Fav'
if (isRunFav) {
	payBillPage.selectFavourites(payBillName)
} else {
	'Tap Pay Bill Name at PayBills Module'
	payBillPage.tapPayBill()

	'Search Pay Bill Name'
	payBillPage.searchPayBill(payBillName)

	'Enter Biller can input 1 or 2 field'
	payBillPage.setBiller([billerID, membershipType])
}

if (isInsufficientAmount) {
	'Case Insufficient Amount'
	BigInteger balanceNumber = commonPage.convertAmountToNumber(balance)

	BigInteger insufficientAmount = balanceNumber + 100000

	amount = insufficientAmount.toString()
}

if (testCaseType == PayBillTestCaseType.EditMinMaxAmount.toString()) {
	'Verify Edit Min Max Amount'
	payBillPage.verifyEditMinMaxAmount(maxAmount, minAmount, errorMsgMinAmount)

	return null
}

'Enter Amount'
commonPage.enterNO(amount)

if (isFuture) {
	'Case Future Transfer'

	'Select Future Date'
	confirmationPage.futureFlow()
}

'Verify Confirmation Page'
confirmationPage.verifyConfirmation(confirmationData, textNextPageConfirmation)

'S2u'
secure2uAuthorisationPage.authorize(status)

'Verify Status Screen'
String ref = statusScreenPage.verifyStatusScreen(statusScreenTitle, statusScreenDescription)
Mobile.comment(ref)

'Check Status'
if ((status == S2UStatusEnum.Approve.toString()) && (testCaseType == PayBillTestCaseType.Default.toString())) {
	'Only Case: Open, Fav, Future with status: Approve; special case not running'
	'Share Receipt'
	shareReceiptPage.shareReceipts(verifyShareReceiptData)

	'Add to Favourites'
	if (!(isRunFav)) {
		favouritesPage.addToFavourites(favName)
	}
	
	'Tap Done Button'
	commonPage.tapDoneBtn()
	
	'Back to home'
	commonPage.backToHome()

	if (!(isFuture)) {
		'Verify notification'
		String pushNotificationDescription = StringUtils.generateMessage(pushNotificationDescription, [StringUtils.divideBy100(
					amount), ref])

		pushNotificationPage.verifyPushNotification(pushNotificationTitle, pushNotificationDescription)

		'Verify Balance after transfer'
		homePage.tapBtnAccounts()
		accountsPage.selectTab(tabName)
		accountsPage.selectAccount(accountName)
		commonPage.delay(GlobalVariable.G_timeoutMiniShort)
		String newBalance = accountDetailPage.getAccountBalanceAmountValue()

		BaseAssert.notEquals(balance, newBalance, 'Verify Balance after transfer')
	}
} else {
	'Tap Done Button'
	commonPage.tapDoneBtn()
}

