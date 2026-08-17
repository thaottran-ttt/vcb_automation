import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase

import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.maybank.mae.internal.pages.base.BasePage
import com.maybank.mae.internal.pages.base.CommonPage
import com.maybank.mae.internal.pages.base.HomePage
import com.maybank.mae.internal.pages.base.Secure2uAuthorisationPage
import com.maybank.mae.internal.pages.base.accountpage.AccountsPage
import com.maybank.mae.internal.pages.base.transferpage.TransferPage
import com.maybank.mae.internal.pages.base.ConfirmationPage
import com.maybank.mae.internal.pages.base.transferpage.EnterOverseasPage
import internal.GlobalVariable as GlobalVariable
import com.maybank.utils.StringUtils

'Login MAE Secure2u'
WebUI.callTestCase(findTestCase('Mobile/MAE/Common/Login Secure2u'), [('idNumber') : idNumber, ('PAN') : PAN, ('password') : password
		, ('username') : username, ('accountName') : accountName], FailureHandling.STOP_ON_FAILURE)

HomePage homePage = BasePage.getPage(HomePage)

CommonPage commonPage = BasePage.getPage(CommonPage)

TransferPage transferPage = BasePage.getPage(TransferPage)

Secure2uAuthorisationPage s2u = BasePage.getPage(Secure2uAuthorisationPage)

AccountsPage accountsPage = BasePage.getPage(AccountsPage)

ConfirmationPage confirmationPage = BasePage.getPage(ConfirmationPage)
EnterOverseasPage enterOverseasPage = BasePage.getPage(EnterOverseasPage)

'Get Initial balance'
String balance = homePage.getBalance()
def balanceBigInt = StringUtils.convertBalanceAmountNumber(balance)
def amount = amount

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

'Select Debiting Account Name'
enterOverseasPage.selectDebitingAccount(debitingAccountName)

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

'Enter Your Details'
enterOverseasPage.enterYourDetails()

'Enter Receipient Details'
enterOverseasPage.enterReceipientDetails()

'Enter Recipient Bank Details'
enterOverseasPage.enterRecipientBankDetails(bankName,accountNumber, bankAddress, city)

'Enter Recipient Details'
enterOverseasPage.enterRecipientDetails(nationality, name, passportNumber, addressLine1, addressLine2, country, mobileNumber, emailAddress)

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

'Verify Status'
enterOverseasPage.verifyStatus(status)

'Back to Home Page'
commonPage.backToHome()
