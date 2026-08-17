import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.vcb.mobile.internal.pages.base.BasePage as BasePage
import com.vcb.mobile.internal.pages.base.CommonPage
import com.vcb.mobile.internal.pages.base.CommonPage.TestObjectTypeEnum
import com.vcb.mobile.internal.pages.base.HomePage
import com.vcb.mobile.internal.pages.base.PushNotificationPage
import com.vcb.mobile.internal.pages.base.Secure2uAuthorisationPage
import com.vcb.mobile.internal.pages.base.ShareReceiptPage
import com.vcb.mobile.internal.pages.base.StatusScreenPage
import com.vcb.mobile.internal.pages.base.accountpage.AccountDetailPage
import com.vcb.mobile.internal.pages.base.accountpage.AccountsPage as AccountsPage
import com.vcb.mobile.internal.pages.base.transferpage.TransferPage
import com.vcb.utils.ListUtils
import com.vcb.mobile.internal.pages.base.ConfirmationPage as ConfirmationPage
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

'Login VCB Secure2u'
WebUI.callTestCase(findTestCase('Mobile/VCB/Common/Login Secure2u'), [('idNumber') : idNumber, ('PAN') : PAN, ('password') : password
		, ('username') : username, ('accountName') : accountName], FailureHandling.STOP_ON_FAILURE)

def amount = amount

Double amountDouble = Double.parseDouble(amount) / 100
String parsedAmount = String.format('%.2f', amountDouble)
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
//	shareReceiptPage.shareReceipts(verifyList)
	commonPage.tapDoneBtn()
	
	'Verify notification'
	pushNotificationPage.verifyPushNotification(notificationTitle, notificationDescription, BasePage.defaultFailureHandling, TestObjectTypeEnum.TEXT)
	
	'Back to Home Page and navigate to account'
	commonPage.backToHome()
	
	'Tap to Account'
	homePage.tapBtnAccounts()
	
	'Check M2U and Transaction History'
	List<String> historyVerificationList = [RefID]
	
	Mobile.comment(historyVerificationList.toString())
	
	WebUI.callTestCase(findTestCase('Mobile/VCB/Common/Reprint Receipt Verification'), [('m2uHistoryProduct') : m2uHistoryProduct
			, ('listShareReceiptVerification') : historyVerificationList], FailureHandling.CONTINUE_ON_FAILURE)
	
	'Assign Push Notification Data'
	titlePN    = pushNotiTitle
	messagePN = pushNotiMsg + " $parsedAmount"
	
	'Verify Push Notification'
	WebUI.callTestCase(findTestCase('Mobile/VCB/Common/Verify Push Notification'), [('titlePN') : titlePN, ('startMessage') : 'Welcome to the VCB app.'
			, ('messagePN') : messagePN
			, ('ref') : RefID, ('amount') : amount], FailureHandling.STOP_ON_FAILURE)
}


