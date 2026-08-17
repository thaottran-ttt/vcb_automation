import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase

import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
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

'Choose An Other Account'
confirmationPage.chooseAnotherAccount(accountName)

'Get Initial balance'
String balance = accountsPage.getG1ABalance()

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
transferPage.selectContinueButton()

'Verify UI Debiting Account'
enterOverseasPage.verifyUITransferOverseasPage()

'Select Input Amount'
enterOverseasPage.selectInputAmount()

'Enter Amount'
commonPage.enterNO(amount)

'Select Dropdown Convert To'
enterOverseasPage.selectDropdownConvertTo(currency)

'Select Continue Button'
transferPage.selectContinueButton()

'Select Foreign Telegraphic Transfer'
enterOverseasPage.selectForeignTelegraphicTransfer(typeTransfer, currency)

'Select Continue Button'
transferPage.selectContinueButton()

'Verify UI Your Details'
enterOverseasPage.verifyUIYourDetails()

'Enter Your Details'
enterOverseasPage.enterYourDetails()

'Verify UI Receipient Details'
enterOverseasPage.verifyUIReceipientDetails()

'Enter Receipient Details'
enterOverseasPage.enterReceipientDetails()

'Verify Recipient Bank Details'
enterOverseasPage.verifyUIRecipientBankDetails()

'Enter Recipient Bank Details'
enterOverseasPage.enterRecipientBankDetails(receivingBank,beneficiaryAccountNumber, bankAddress, city)

'Verify UI Recipient Details'
enterOverseasPage.verifyUIRecipientDetails()

'Enter Recipient Details'
enterOverseasPage.enterRecipientDetails(nationality, beneficiaryName, passportNumber, addressLine1, addressLine2, country, mobileNumber, emailAddress)

'Verify UI Transfer Details'
enterOverseasPage.verifyUITransferDetails()

'Enter Transfer Details'
enterOverseasPage.enterTransferDetails(purposeOfTransfer, subPurpose, relationship, additionalDetails)

'Enter Confirmation'
enterOverseasPage.enterConfirmation()

'Enter Terms And Conditions'
enterOverseasPage.enterTermsAndConditions()

'Enter Transfer From'
enterOverseasPage.enterTransferFrom()

'Authorisation'
s2u.authorize(status)
commonPage.delay(GlobalVariable.G_timeoutMedium)

'Status screen'
String RefID = statusScreenPage.verifyStatusScreen(statusTitle, statusDescription)

String formattedAmount = String.format("%.2f", Double.parseDouble(amount)/100)
verifyList.add(RefID)
verifyList.add(formattedAmount)

'Get Foreign Currency Amount'
String foreignCurrencyAmount = shareReceiptPage.getForeignCurrencyAmount()

notificationDescription = transferPage.constructNotificationTemplateFTT(formattedAmount, foreignCurrencyAmount, beneficiaryName, 
    receivingBank, beneficiaryAccountNumber, RefID)

Mobile.comment(notificationDescription)

'Add refID to list and verify receipt'
shareReceiptPage.shareReceipts(verifyList)

commonPage.tapDoneBtn()

Mobile.comment(notificationTitle)

Mobile.comment(notificationDescription)

'Verify notification'
pushNotificationPage.verifyPushNotification(notificationTitle, notificationDescription, BasePage.defaultFailureHandling, 
    TestObjectTypeEnum.TEXT)
'Check new balance'

commonPage.scrollToTop()

newBalance = accountsPage.getG1ABalance()

if (!(balance.equals(newBalance)) || (isFuture)) {
	Mobile.comment((('Balance has changed. Initial: ' + balance) + ', New: ') + newBalance)
} else {
	Mobile.comment('Balance remains the same: ' + balance)
}


