import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase

import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.vcb.mobile.internal.pages.base.BasePage
import com.vcb.mobile.internal.pages.base.CommonPage
import com.vcb.mobile.internal.pages.base.HomePage
import com.vcb.mobile.internal.pages.base.Secure2uAuthorisationPage
import com.vcb.mobile.internal.pages.base.accountpage.AccountsPage
import com.vcb.mobile.internal.pages.base.transferpage.TransferPage
import com.vcb.mobile.internal.pages.base.ConfirmationPage
import internal.GlobalVariable as GlobalVariable
import com.vcb.utils.StringUtils

//'Login VCB Secure2u'
//WebUI.callTestCase(findTestCase('Mobile/VCB/Common/Login Secure2u'), [('idNumber') : idNumber, ('PAN') : PAN, ('password') : password
//		, ('username') : username, ('accountName') : accountName], FailureHandling.STOP_ON_FAILURE)

HomePage homePage = BasePage.getPage(HomePage)

CommonPage commonPage = BasePage.getPage(CommonPage)

TransferPage transferPage = BasePage.getPage(TransferPage)

Secure2uAuthorisationPage s2u = BasePage.getPage(Secure2uAuthorisationPage)

AccountsPage accountsPage = BasePage.getPage(AccountsPage)

ConfirmationPage confirmationPage = BasePage.getPage(ConfirmationPage)

'Get Initial balance'
String balance = homePage.getBalance()
def balanceBigInt = StringUtils.convertBalanceAmountNumber(balance)
def amount = amount

'Tap Button Accounts'
homePage.tapBtnAccounts()

'Choose An Other Account'
confirmationPage.chooseAnOtherAccount(accountName)

'Select Transfer Button'
accountsPage.selectTransferButton()

'Select Others Tab'
transferPage.selectTab(transferTab, true)

'Select Banks -> select VCB'
transferPage.openNewTransferOthers('Banks', 'VCB')

'Account Number'
transferPage.setAccountNumber(accountNo)

'Enter Amount'
commonPage.enterNO(amount)

'Recipient Reference'
transferPage.recipientReference(recipientReference)

'Confirmation'
transferPage.confirm()

'Authorisation'
transferPage.authorize(status)

'Status screen'
String RefID = transferPage.statusScreen(status, true, false, true)
String[] approvalStatus =  RefID.split(",");
String statusApproval = '';
if(approvalStatus.length > 1) {
	RefID = approvalStatus[0];
	statusApproval = approvalStatus[1];
}
Mobile.comment(RefID)
Mobile.comment(amount)

'Back to Home Page'
commonPage.backToHome()

//'Verify notification'
//transferPage.verifyTransferNotificationMessage(accountNo, RefID, Double.parseDouble(amount)/100)

'Check amount'
String newBalance = homePage.getBalance(true)
def newBalanceBigInt = StringUtils.convertBalanceAmountNumber(newBalance)

//if(statusApproval.contains('accepted')) {
//	amount = 0
//}

'Verify Amount'
CustomKeywords.'com.vcb.assertions.BaseAssert.equals'((balanceBigInt -  newBalanceBigInt).toString(), amount.toString(), 'Check Amount')

