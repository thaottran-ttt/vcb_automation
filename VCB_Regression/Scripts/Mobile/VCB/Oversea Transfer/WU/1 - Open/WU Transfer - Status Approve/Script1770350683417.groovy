import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase

import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.vcb.mobile.internal.pages.base.BasePage
import com.vcb.mobile.internal.pages.base.CommonPage
import com.vcb.mobile.internal.pages.base.CommonPage.TestObjectTypeEnum
import com.vcb.mobile.internal.pages.base.ConfirmationPage
import com.vcb.mobile.internal.pages.base.HomePage
import com.vcb.mobile.internal.pages.base.PushNotificationPage
import com.vcb.mobile.internal.pages.base.ShareReceiptPage
import com.vcb.mobile.internal.pages.base.Secure2uAuthorisationPage
import com.vcb.mobile.internal.pages.base.StatusScreenPage
import com.vcb.mobile.internal.pages.base.accountpage.AccountDetailPage
import com.vcb.mobile.internal.pages.base.accountpage.AccountsPage
import com.vcb.mobile.internal.pages.base.transferpage.EnterOverseasPage
import com.vcb.mobile.internal.pages.base.transferpage.TransferPage
import com.vcb.utils.ListUtils
import internal.GlobalVariable as GlobalVariable

'Login VCB Secure2u'
WebUI.callTestCase(findTestCase('Mobile/VCB/Common/Login Secure2u'), [('idNumber') : idNumber, ('PAN') : PAN, ('password') : password
		, ('username') : username, ('accountName') : primaryAccount], FailureHandling.STOP_ON_FAILURE)

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

'Choose Account'
confirmationPage.chooseAnotherAccount(accountName)

'Extract current balance'
String balance = accountDetailPage.getAccountBalanceAmountValue()

'Select Transfer Button'
accountsPage.selectTransferButton()

'Select OVERSEAS Tab'
transferPage.selectTransferToTab(transferTab)

'Select tab New Transfer -> select country name'
enterOverseasPage.selectCountryNameInOverseas(tabNewTransfer, countryName)

'Verify UI Debiting Account'
enterOverseasPage.verifyUIDebitingAccount()

'Select Debiting Account Name'
enterOverseasPage.selectDebitingAccount(debitingAccountName)

'Select Continue Button'
enterOverseasPage.selectContinueButton()
commonPage.waitLoading()

'Select Input Amount'
enterOverseasPage.selectInputAmount()

'Enter Amount'
commonPage.enterNO(amount)

'Select Dropdown Convert To'
enterOverseasPage.selectDropdownConvertTo(currency)

'Select Continue Button'
enterOverseasPage.selectContinueButton(FailureHandling.OPTIONAL)
commonPage.waitLoading()

'SST 1 Here'
if (verifySST) {
	enterOverseasPage.verifySSTTransferType(typeTransfer)
}

enterOverseasPage.selectForeignTelegraphicTransfer(typeTransfer, 'RM')

'Select Continue Button'
enterOverseasPage.selectContinueButton()
commonPage.waitLoading()

'Select Continue Button'
enterOverseasPage.selectContinueButton()
commonPage.waitLoading()

'Step 2 of 5 - Temporary Address'
enterOverseasPage.ovhWUStep2TempAddress(sameAsPrimary)
commonPage.waitLoading()

'Step 3 of 5 - Sender ID & Employment Details'
enterOverseasPage.ovhWUStep3SenderIDEmployment('', '', '', '', occupation, employmentPositionLevel, relationshipToBeneficiary, '', '', sourceOfFunds)
commonPage.waitLoading()

'Step 4 of 5 - Recipient Details'
enterOverseasPage.ovhWUStep4RecipientDetails(firstName, lastName, receiverCountryCode, recipientMobileNumber, streetLine1, streetLine2, recipientPostCode, city, recipientState, recipientCountry)
commonPage.waitLoading()

'Step 5 of 5 - Transfer Details'
enterOverseasPage.ovhWUStep5TransferDetails(purposeOfTransfer, subPurpose)
commonPage.waitLoading()

'Confirmation screen - verify all details and tap Confirm and Submit'
enterOverseasPage.ovhWUConfirm(
	citizenship,
	promoCode,
	senderEmailAddress,
	occupation,
	employmentPositionLevel,
	relationshipToBeneficiary,
	sourceOfFunds,
	firstName,
	lastName,
	receiverCountryCode,
	recipientMobileNumber,
	streetLine1,
	streetLine2,
	purposeOfTransfer,
	subPurpose
)
commonPage.waitLoading()

'Enter Terms And Conditions (Agree & Confirm)'
enterOverseasPage.enterTermsAndConditions()

//'Enter Transfer From (Transfer Now)'
//enterOverseasPage.enterTransferFrom(verifySST)
//commonPage.waitLoading()

'Authorisation - S2U'
s2u.authorize(status, [], FailureHandling.OPTIONAL)
commonPage.delay(GlobalVariable.G_timeoutMedium)

'Verify Status screen'
String RefID = statusScreenPage.verifyStatusScreen(statusTitle, statusDescription)

String formattedAmount = String.format("%.2f", Double.parseDouble(amount) / 100)
verifyList.add(RefID)
verifyList.add(formattedAmount)

if (status == 'Approve') {
	'Get Foreign Currency Amount'
	String foreignCurrencyAmount = shareReceiptPage.getForeignCurrencyAmount()
	notificationDescription = transferPage.constructNotificationTemplateFTT(
		formattedAmount, foreignCurrencyAmount, firstName + ' ' + lastName, typeTransfer, recipientMobileNumber, RefID)
	Mobile.comment(notificationDescription)
	Mobile.delay(GlobalVariable.G_timeoutMiniShort)

	'Verify receipt and get total amount (including fees)'
	String totalAmountCents = enterOverseasPage.ovhVerifyReceiptAndGetTotal(verifyList, BasePage.defaultFailureHandling, verifySST)

	if(addFav) {
		enterOverseasPage.ovhAddToFavourites(typeTransfer)
	}

	commonPage.tapDoneBtn(FailureHandling.OPTIONAL)

	'Verify push notification'
	Mobile.comment(notificationTitle)
	Mobile.comment(notificationDescription)
	pushNotificationPage.verifyPushNotification(notificationTitle, notificationDescription, BasePage.defaultFailureHandling, TestObjectTypeEnum.TEXT)

	'Back to Home Page'
	commonPage.backToHome()

	'Tap to Account'
	homePage.tapBtnAccounts()

	if (accountName != '') {
		'Select Account'
		accountsPage.selectAccount(accountName)

		'Get new balance'
		commonPage.delay(GlobalVariable.G_timeoutMiniShort)
		newBalance = homePage.getBalance()

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
	}
}
