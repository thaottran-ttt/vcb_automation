import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.maybank.mae.internal.pages.base.BasePage as BasePage
import com.maybank.mae.internal.pages.base.CommonPage
import com.maybank.mae.internal.pages.base.CommonPage.TestObjectTypeEnum
import com.maybank.mae.internal.pages.base.HomePage
import com.maybank.mae.internal.pages.base.PushNotificationPage
import com.maybank.mae.internal.pages.base.Secure2uAuthorisationPage
import com.maybank.mae.internal.pages.base.ShareReceiptPage
import com.maybank.mae.internal.pages.base.StatusScreenPage
import com.maybank.mae.internal.pages.base.accountpage.AccountDetailPage
import com.maybank.mae.internal.pages.base.accountpage.AccountsPage as AccountsPage
import com.maybank.mae.internal.pages.base.transferpage.TransferPage
import com.maybank.utils.ListUtils
import com.maybank.mae.internal.pages.base.ConfirmationPage as ConfirmationPage
import internal.GlobalVariable as GlobalVariable
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint

HomePage homePage = BasePage.getPage(HomePage)

CommonPage commonPage = BasePage.getPage(CommonPage)

TransferPage transferPage = BasePage.getPage(TransferPage)

Secure2uAuthorisationPage s2u = BasePage.getPage(Secure2uAuthorisationPage)

AccountsPage accountsPage = BasePage.getPage(AccountsPage)

ConfirmationPage confirmationPage = BasePage.getPage(ConfirmationPage)

ShareReceiptPage shareReceiptPage = BasePage.getPage(ShareReceiptPage)

StatusScreenPage statusScreenPage = BasePage.getPage(StatusScreenPage)

PushNotificationPage pushNotificationPage = BasePage.getPage(PushNotificationPage)

AccountDetailPage accountDetailPage = BasePage.getPage(AccountDetailPage)

AccountsPage accountPage = BasePage.getPage(AccountsPage)

List statusTitle = ListUtils.parseObjectToList(statusTitle)

List statusDescription = ListUtils.parseObjectToList(statusDescription)


'Login MAE Secure2u'
WebUI.callTestCase(findTestCase('Mobile/MAE/Common/Login Secure2u'), [('idNumber') : idNumber, ('PAN') : PAN, ('password') : password
		, ('username') : username, ('accountName') : accountName], FailureHandling.STOP_ON_FAILURE)


String amount = amount

String newBalance = ''

List<String> verifyList = new ArrayList()

'Tap Button Accounts'
homePage.selectQuickActions(tabName)

'Select accounts transfer to'
transferPage.selectAccountTransferto(recipientAccount)

'Get account number transfer to'
String accountNumber = recipientAccountNumber

'Enter Amount'
transferPage.enterAmount(amount)

'Recipient Reference'
transferPage.recipientReference(recipientAccount)

'Confirmation'
confirmationPage.chooseAnotherAccount(accountName)

'Get send account number'
String sendAccountNumber = confirmationPage.getBalanceAndSendAccountNumber(accountName)

'Get Initial balance'
String balance = confirmationPage.balance

'Confirm'
transferPage.confirm(isFuture)
verifyList.add(transferPage.date)

'Authorisation'
s2u.authorize(status)
commonPage.delay(GlobalVariable.G_timeoutMedium)

'Status screen'
String RefID = statusScreenPage.verifyStatusScreen(statusTitle, statusDescription)

String formattedAmount = String.format("%.2f", Double.parseDouble(amount)/100)
verifyList.add(RefID)
verifyList.add(sendAccountNumber)
verifyList.add(recipientAccount)
verifyList.add(accountNumber)
verifyList.add(formattedAmount)

if(status == "Approve") {
	
	notificationDescription = transferPage.constructNotificationTemplate(accountNumber, RefID, formattedAmount)
	
	'Add refID to list and verify receipt'
	shareReceiptPage.shareReceipts(verifyList)
	commonPage.tapDoneBtn()
	
	'Verify notification'
	pushNotificationPage.verifyPushNotification(notificationTitle, notificationDescription, BasePage.defaultFailureHandling, TestObjectTypeEnum.TEXT)
	
	'Back to Home Page and navigate to account'
	commonPage.backToHome()
	
	'Tap to Account'
	homePage.tapBtnAccounts()
	
	if(accountName != '') {
		'Tap to account'
		accountPage.selectAccount(accountName)		
		Mobile.takeScreenshot()
		
		'Get new balance'
		newBalance = homePage.getBalance()
	}
	else {
		'Navigate to Account Detail'
		homePage.navigateToAccountDetail()
	}
	
	int sendAccIndex = verifyList.indexOf(sendAccountNumber);
	
	sendAccountNumber = sendAccountNumber.replaceAll("\\s+", "");
	
	if (sendAccIndex != -1) {
	    verifyList.set(sendAccIndex, sendAccountNumber);
	}
	
	int accIndex = verifyList.indexOf(accountNumber);
	
	accountNumber = accountNumber.replaceAll("\\s+", "");
	if (accIndex != -1) {
	    verifyList.set(accIndex, accountNumber);
	}
	
	'Verify M2U Transaction'
	accountDetailPage.verifyReprintReceiptWithData(receiptTitle, verifyList)
	
	if(isFuture) {
		amount = 0
	}
	
	'Verify money after transaction'
	BasePage.verifyBalanceAfterSpending(balance, newBalance, amount)
}


