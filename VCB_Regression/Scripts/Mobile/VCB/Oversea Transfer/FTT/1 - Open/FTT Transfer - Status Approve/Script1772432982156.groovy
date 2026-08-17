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
Map recipientDetails = MapUtils.parseObjectToMap(recipientDetails)

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

'Select Others Tab'
transferPage.selectTransferToTab(transferTab)

'Select tab New Transfer -> select country name'
enterOverseasPage.selectCountryNameInOverseas(tabNewTransfer, countryName)

'Verify UI Debiting Account'
enterOverseasPage.verifyUIDebitingAccount()

'Select Debiting Account Name'
enterOverseasPage.selectDebitingAccount(debitingAccountName)

'Select Continue Button'
enterOverseasPage.selectContinueButton()

'Verify UI Debiting Account'
enterOverseasPage.verifyUITransferOverseasPage()

'Select Input Amount'
enterOverseasPage.selectInputAmount()

'Enter Amount'
commonPage.enterNO(amount)

'Select Dropdown Convert To'
enterOverseasPage.selectDropdownConvertTo(currency)

'Select Continue Button'
enterOverseasPage.selectContinueButton()

'SST 1 Here'
if (verifySST) {
	enterOverseasPage.verifySSTTransferType(typeTransfer)
}

'Select Foreign Telegraphic Transfer'
enterOverseasPage.selectForeignTelegraphicTransfer(typeTransfer, currencyFrom)

'Select Continue Button'
enterOverseasPage.selectContinueButton(FailureHandling.OPTIONAL)

'Select Continue Button'
enterOverseasPage.selectContinueButton(FailureHandling.OPTIONAL)

'Step 1 of 4 - Sender Details (select nationality; address fields are pre-populated from profile)'
enterOverseasPage.ovhFTTStep1SenderDetails(nationality)
Mobile.delay(GlobalVariable.G_timeoutMiniShort)

'Select Continue Button using element coordinates'
def continueBtn = commonPage.lblContainDynamic("Continue")
int btnX = Mobile.getElementLeftPosition(continueBtn, GlobalVariable.G_timeoutShort)
int btnY = Mobile.getElementTopPosition(continueBtn, GlobalVariable.G_timeoutShort)
Mobile.tapAtPosition(btnX, btnY)

Mobile.tap(commonPage.lblContainDynamic("Continue"), GlobalVariable.G_timeoutMedium,FailureHandling.OPTIONAL)

Mobile.tap(commonPage.lblDynamic("Confirm"), GlobalVariable.G_timeoutMedium,FailureHandling.OPTIONAL)

'Step 2 of 4 - Recipient Bank Details'
enterOverseasPage.ovhFTTStep2RecipientBankDetails(receivingBank, beneficiaryAccountNumber, bankAddress, city)

'Step 3 of 4 - Recipient Details'
enterOverseasPage.ovhFTTStep3RecipientDetails(recipientNationality, beneficiaryName, passportNumber, addressLine1, addressLine2, country, mobileNumber, emailAddress)

'Step 4 of 4 - Transfer Details'
enterOverseasPage.ovhFTTStep4TransferDetails(purposeOfTransfer, subPurpose, relationship, bankFee, additionalDetails)

'SST 2 Here'
'Confirmation screen - verify all details'
enterOverseasPage.ovhFTTConfirm(
	nationality,
	receivingBank,
	beneficiaryAccountNumber,
	bankAddress,
	city,
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
	bankFee,
	additionalDetails,
	verifySST
)

'Enter Terms And Conditions'
enterOverseasPage.enterTermsAndConditions()

'SST 3'
'Enter Transfer From'
enterOverseasPage.enterTransferFrom(verifySST)
Mobile.delay(GlobalVariable.G_timeoutMiniShort)

'Authorisation'
s2u.authorizeS2UV5BioPin(status, [], FailureHandling.OPTIONAL)

'Status screen'
String RefID = statusScreenPage.verifyStatusScreen(statusTitle, statusDescription)

String formattedAmount = String.format("%.2f", Double.parseDouble(amount)/100)
verifyList.add(RefID)
verifyList.add(formattedAmount)

if(status == "Approve") {
	'Get Foreign Currency Amount'
	String foreignCurrencyAmount = shareReceiptPage.getForeignCurrencyAmount()
	notificationDescription = transferPage.constructNotificationTemplateFTT(formattedAmount, foreignCurrencyAmount, beneficiaryName, receivingBank, beneficiaryAccountNumber, RefID)
	Mobile.comment(notificationDescription)
	Mobile.delay(GlobalVariable.G_timeoutMiniShort)
	
	'SST 4 - Verify receipt and SST amounts'
	String totalAmountCents = enterOverseasPage.ovhVerifyReceiptAndGetTotal(verifyList, BasePage.defaultFailureHandling, verifySST)
	
	if(addFav) {
		enterOverseasPage.ovhAddToFavourites(typeTransfer)
	}

	commonPage.tapDoneBtn(FailureHandling.OPTIONAL)
	Mobile.comment(notificationTitle)
	Mobile.comment(notificationDescription)
	'Verify notification'
	pushNotificationPage.verifyPushNotification(notificationTitle, notificationDescription, FailureHandling.CONTINUE_ON_FAILURE, TestObjectTypeEnum.TEXT)
	
	enterOverseasPage.tapDone()
	
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
	
	enterOverseasPage.tapDone()
	
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
