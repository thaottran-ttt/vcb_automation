import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase

import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.maybank.mae.internal.pages.base.BasePage
import com.maybank.mae.internal.pages.base.CommonPage
import com.maybank.mae.internal.pages.base.CommonPage.TestObjectTypeEnum
import com.maybank.mae.internal.pages.base.ConfirmationPage
import com.maybank.mae.internal.pages.base.HomePage
import com.maybank.mae.internal.pages.base.PushNotificationPage
import com.maybank.mae.internal.pages.base.ShareReceiptPage
import com.maybank.mae.internal.pages.base.Secure2uAuthorisationPage
import com.maybank.mae.internal.pages.base.StatusScreenPage
import com.maybank.mae.internal.pages.base.accountpage.AccountDetailPage
import com.maybank.mae.internal.pages.base.accountpage.AccountsPage
import com.maybank.mae.internal.pages.base.transferpage.EnterOverseasPage
import com.maybank.mae.internal.pages.base.transferpage.TransferPage
import com.maybank.utils.ListUtils
import internal.GlobalVariable as GlobalVariable

'Login MAE Secure2u'
WebUI.callTestCase(findTestCase('Mobile/MAE/Common/Login Secure2u'), [('idNumber') : idNumber, ('PAN') : PAN, ('password') : password, ('username') : username, ('accountName') : primaryAccount], FailureHandling.STOP_ON_FAILURE)

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

'Select WU Favourite'
enterOverseasPage.selectWUFav()

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
if (status == 'Insufficient') {
	long insufficientAmountCents = Math.round(Double.parseDouble(balance) * 100) + 300
	commonPage.enterNO(insufficientAmountCents.toString())
} else {
	commonPage.enterNO(amount)
}

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

'Step 5 of 5 - Transfer Details (Steps 1-4 pre-filled for Favourite)'
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

'Enter Transfer From (Transfer Now)'
enterOverseasPage.enterTransferFrom(verifySST)
commonPage.waitLoading()

'Authorisation - S2U'
//s2u.authorize(status)
//commonPage.delay(GlobalVariable.G_timeoutMedium)
s2u.authorizeS2UV5BioPin(status, [], FailureHandling.OPTIONAL)
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
	Mobile.tap(commonPage.UIDonebtn, FailureHandling.OPTIONAL)
	'Status is Reject/Expire/Insufficient - verify balance has NOT changed'
	commonPage.tapDoneBtn(FailureHandling.OPTIONAL)
//
//	'Tap to Account'
//	homePage.tapBtnAccounts()
//	'Select Account'
//	if (accountName != '') {
//		accountsPage.selectAccount(accountName)
//
//		'Get new balance after rejected/expired transaction'
//		commonPage.delay(GlobalVariable.G_timeoutMiniShort)
//		newBalance = homePage.getBalance()
//
//		'Verify balance is unchanged (no deduction should have occurred)'
//		BasePage.verifyBalanceAfterSpending(balance, newBalance, '0', 'Verify balance unchanged after ' + status, FailureHandling.OPTIONAL)
//	} else {
//		homePage.navigateToAccountDetail()
//	}
}
