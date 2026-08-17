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
import com.vcb.utils.MapUtils

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

WebUI.callTestCase(findTestCase('Mobile/VCB/Common/Login Secure2u With Secure Question'), [('idNumber') : idNumber, ('PAN') : PAN, ('password') : password
        , ('username') : username, ('accountName') : accountName, ('flowControl') : com.vcb.common.FlowControlEnum.STOP
        , ('failStopTestSuite') : false, ('yourName') : GlobalVariable.G_yourName], FailureHandling.STOP_ON_FAILURE)

'Tap Button Accounts'
homePage.tapBtnAccounts()

'Check And Tap Got It'
accountsPage.checkAndTapGotIt()

'Choose An Other Account'
confirmationPage.chooseAnotherAccount(accountName)

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
enterOverseasPage.selectForeignTelegraphicTransfer(typeTransfer, currencyFrom)

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
enterOverseasPage.enterRecipientDetailsFTT(recipientDetails,country,mobileNumber)

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

'Authorize Page'
s2u.authorize(status)

'Enter Answer For Security Question'
CustomKeywords.'com.vcb.mobile.pages.SecurityQuestion.inputAnswer'(securityanswer)

//'Authorisation'
//s2u.authorize(status)
//commonPage.delay(GlobalVariable.G_timeoutMedium)

'Status screen'
statusScreenPage.verifyStatusScreen(statusTitle, statusDescription)
