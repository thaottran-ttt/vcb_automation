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
import com.maybank.mae.internal.pages.base.Secure2uAuthorisationPage
import com.maybank.mae.internal.pages.base.accountpage.AccountDetailPage
import com.maybank.mae.internal.pages.base.accountpage.AccountsPage
import com.maybank.mae.internal.pages.base.transferpage.TransferPage


'Login MAE Secure2u'
WebUI.callTestCase(findTestCase('Mobile/MAE/Common/Login Secure2u'), [('idNumber') : idNumber, ('PAN') : PAN, ('password') : password
		, ('username') : username, ('accountName') : accountName], FailureHandling.STOP_ON_FAILURE)

HomePage homePage = BasePage.getPage(HomePage)

CommonPage commonPage = BasePage.getPage(CommonPage)

TransferPage transferPage = BasePage.getPage(TransferPage)

Secure2uAuthorisationPage s2u = BasePage.getPage(Secure2uAuthorisationPage)

AccountsPage accountsPage = BasePage.getPage(AccountsPage)

ConfirmationPage confirmationPage = BasePage.getPage(ConfirmationPage)

PushNotificationPage pushNotificationPage = BasePage.getPage(PushNotificationPage)

AccountDetailPage accountDetailPage = AccountDetailPage.getPage(AccountDetailPage)

Secure2uAuthorisationPage secure2uAuthorisationPage = BasePage.getPage(Secure2uAuthorisationPage)

'Get Initial balance'
homePage.tapBtnAccounts()
accountsPage.selectAccount(accountName)
String balance = accountDetailPage.getAccountBalanceAmountValue()
Double balanceDouble = Double.parseDouble(balance)
def amount = amount
Double amountDouble = Double.parseDouble(amount) / 100

'Tap Button Accounts'
homePage.tapBtnAccounts()

'Choose An Other Account'
accountsPage.selectAccount(accountName)

'Select Transfer Button'
accountsPage.selectTransferButton()

'Select Others Tab'
transferPage.selectTab(transferTab, true)

String favName = favName
if (favName.isEmpty()) {
	'Select Banks -> select MAYBANK'
	transferPage.openNewTransferOthers('Banks', 'MAYBANK')
	
	'Account Number'
	transferPage.setAccountNumber(accountNo)
} else {
	'Search and Select Favourites'
	transferPage.selectFav(favName)
}

'Enter Amount'
commonPage.enterNO(amount)

'Recipient Reference'
transferPage.recipientReference(recipientReference)

'Confirmation'
transferPage.confirm(isFuture)

'Authorisation'
//transferPage.authorize(status)
secure2uAuthorisationPage.authorize(status)

'Status screen, add fav, share receipt'
boolean isAddFav = favName.isEmpty() ? true : false
String RefID = transferPage.statusScreen(status, isAddFav, isFuture, true)
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

if (status == 'Approve') {
	'Verify notification'
	String parsedAmount = String.format("%.2f", amountDouble)
	pushNotificationPage.verifyPushNotification(pushNotiTitle, pushNotiMsg + " ${parsedAmount}",
		FailureHandling.STOP_ON_FAILURE, TestObjectTypeEnum.CONTAINS)
	
	homePage.tapBtnAccounts()
	accountsPage.selectAccount(accountName)
	
	if (!isFuture) {
		String newBalance = accountDetailPage.getAccountBalanceAmountValue()
		Double newBalanceDouble = Double.parseDouble(newBalance)
		'Verify Amount'
		CustomKeywords.'com.maybank.assertions.BaseAssert.equals'((balanceDouble -  newBalanceDouble).toString(), amountDouble.toString(), 'Check Amount')
	}
	
	'Verify transaction history'
	accountDetailPage.verifyM2UHistoryFirstItemToday(history, '-RM ' + parsedAmount)
}
