import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase

import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.mobile.keyword.internal.MobileAbstractKeyword
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.vcb.mobile.internal.pages.base.BasePage
import com.vcb.mobile.internal.pages.base.CommonPage
import com.vcb.mobile.internal.pages.base.HomePage
import com.vcb.mobile.internal.pages.base.CommonPage.TestObjectTypeEnum
import com.vcb.mobile.internal.pages.base.Secure2uAuthorisationPage
import com.vcb.mobile.internal.pages.base.accountpage.AccountsPage
import com.vcb.mobile.internal.pages.base.transferpage.TransferPage
import com.vcb.mobile.internal.pages.base.ConfirmationPage
import com.vcb.mobile.internal.pages.base.transferpage.EnterOverseasPage
import com.vcb.mobile.internal.pages.base.PushNotificationPage
import com.vcb.mobile.internal.pages.base.accountpage.AccountDetailPage
import com.vcb.mobile.internal.pages.base.ShareReceiptPage
import com.vcb.mobile.internal.pages.base.StatusScreenPage
import internal.GlobalVariable as GlobalVariable
import com.vcb.utils.StringUtils
import com.vcb.utils.ListUtils
import com.vcb.utils.MapUtils

'Login VCB Secure2u'
WebUI.callTestCase(findTestCase('Mobile/VCB/Common/Login Secure2u'), [('idNumber') : idNumber, ('PAN') : PAN, ('password') : password
		, ('username') : username, ('accountName') : accountName], FailureHandling.STOP_ON_FAILURE)

HomePage homePage = BasePage.getPage(HomePage)

CommonPage commonPage = BasePage.getPage(CommonPage)

TransferPage transferPage = BasePage.getPage(TransferPage)

Secure2uAuthorisationPage s2u = BasePage.getPage(Secure2uAuthorisationPage)

AccountsPage accountsPage = BasePage.getPage(AccountsPage)

ConfirmationPage confirmationPage = BasePage.getPage(ConfirmationPage)

EnterOverseasPage enterOverseasPage = BasePage.getPage(EnterOverseasPage)

PushNotificationPage pushNotificationPage = BasePage.getPage(PushNotificationPage)

ShareReceiptPage shareReceiptPage = BasePage.getPage(ShareReceiptPage)

AccountDetailPage accountDetailPage = BasePage.getPage(AccountDetailPage)

StatusScreenPage statusScreenPage = BasePage.getPage(StatusScreenPage)


List statusTitle = ListUtils.parseObjectToList(statusTitle)
List statusDescription = ListUtils.parseObjectToList(statusDescription)

String amount = amount

String newBalance = ''

List<String> verifyList = new ArrayList()

'Tap Button Accounts'
homePage.tapBtnAccounts()

'Check And Tap Got It'
accountsPage.checkAndTapGotIt()

'Choose An Other Account'
confirmationPage.chooseAnotherAccount(accountName)

'Extract current balance'
String balance = accountDetailPage.getAccountBalanceAmountValue()

'Select Transfer Button'
accountsPage.selectTransferButton()

'Select OVERSEAS Tab'
transferPage.selectTransferToTab(transferTab)

'Select MOT Favourite'
enterOverseasPage.selectMOTFav()

'Verify UI Debiting Account'
enterOverseasPage.verifyUIDebitingAccount()

'Select Debiting Account Name'
enterOverseasPage.selectDebitingAccount(debitingAccountName)

'Select Continue Button'
enterOverseasPage.selectContinueButton()

'Select Input Amount'
enterOverseasPage.selectInputAmount()

'Enter Amount'
if (status == 'Insufficient') {
	long insufficientAmountCents = Math.round(Double.parseDouble(balance) * 100) + 300
	commonPage.enterNO(insufficientAmountCents.toString())
} else {
	commonPage.enterNO(amount)
}

'Select Dropdown Convert To'
enterOverseasPage.selectDropdownConvertTo(currency)

'SST 1 Here'
if (verifySST) {
	enterOverseasPage.verifySSTTransferType(typeTransfer)
}

'Select Vcb Overseas Transfer type'
enterOverseasPage.selectForeignTelegraphicTransfer(typeTransfer, currencyFrom)

'Step 3 of 3 - Transfer Details (Steps 1-2 are pre-filled from saved favourite)'
enterOverseasPage.ovhMOTStep3TransferDetails(purposeOfTransfer, subPurpose, relationship, additionalDetails)

'Confirmation screen - verify all details'
enterOverseasPage.ovhMOTConfirm(
	receivingBank,
	beneficiaryAccountNumber,
	recipientNationality,
	beneficiaryName,
	passportNumber,
	addressLine1,
	addressLine2,
	country,
	mobileNumber,
	emailAddress,
	purposeOfTransfer,
	subPurpose,
	relationship,
	additionalDetails
)

'Enter Terms And Conditions'
enterOverseasPage.enterTermsAndConditions()

'Enter Transfer From'
enterOverseasPage.enterTransferFrom(verifySST)
commonPage.delay(10)
'Authorisation'
s2u.authorize(status)
commonPage.delay(GlobalVariable.G_timeoutMedium)

'Status screen'
String RefID = statusScreenPage.verifyStatusScreen(statusTitle, statusDescription)

String formattedAmount = String.format("%.2f", Double.parseDouble(amount)/100)
verifyList.add(RefID)
verifyList.add(formattedAmount)

if(status == "Approve") {
	'Get Foreign Currency Amount'
	String foreignCurrencyAmount = shareReceiptPage.getForeignCurrencyAmount()
	notificationDescription = transferPage.constructNotificationTemplateFTT(formattedAmount, foreignCurrencyAmount, beneficiaryName, receivingBank2, beneficiaryAccountNumber, RefID)
	Mobile.comment(notificationDescription)
	Mobile.delay(GlobalVariable.G_timeoutMiniShort)
	'Verify receipt and get total amount (including fees)'
	String totalAmountCents = enterOverseasPage.ovhVerifyReceiptAndGetTotal(verifyList, BasePage.defaultFailureHandling, verifySST)

	commonPage.tapDoneBtn(FailureHandling.OPTIONAL)
	Mobile.comment(notificationTitle)
	Mobile.comment(notificationDescription)

	'Verify notification'
	pushNotificationPage.verifyPushNotification(notificationTitle, notificationDescription, BasePage.defaultFailureHandling, TestObjectTypeEnum.TEXT)

	'Back to Home Page and navigate to account'
	commonPage.backToHome()

	'Tap to Account'
	homePage.tapBtnAccounts()
	'Select Account'
	if (accountName != '') {
		'Tap to account'
		accountsPage.selectAccount(accountName)

		'Get new balance'
		commonPage.delay(GlobalVariable.G_timeoutMiniShort)
		newBalance = accountDetailPage.getAccountBalanceAmountValue()

		'Verify balance after transaction'
		BasePage.verifyBalanceAfterSpending(balance, newBalance, totalAmountCents, 'Verify balance after transaction', FailureHandling.OPTIONAL)

		'Verify Transaction History'
		enterOverseasPage.ovhVerifyTransactionHistory(typeTransfer, verifyList, FailureHandling.OPTIONAL)
	} else {
		'Navigate to Account Detail'
		homePage.navigateToAccountDetail()
	}
} else {
	Mobile.tap(commonPage.Done, FailureHandling.OPTIONAL)
	'Status is Reject/Expire/Insufficient - verify balance has NOT changed'
	commonPage.backToHome()

	'Tap to Account'
	homePage.tapBtnAccounts()
	'Select Account'
	if (accountName != '') {
		accountsPage.selectAccount(accountName)

		'Get new balance after rejected/expired transaction'
		commonPage.delay(GlobalVariable.G_timeoutMiniShort)
		newBalance = homePage.getBalance()

		'Verify balance is unchanged (no deduction should have occurred)'
		BasePage.verifyBalanceAfterSpending(balance, newBalance, '0', 'Verify balance unchanged after ' + status, FailureHandling.OPTIONAL)
	} else {
		homePage.navigateToAccountDetail()
	}
}
