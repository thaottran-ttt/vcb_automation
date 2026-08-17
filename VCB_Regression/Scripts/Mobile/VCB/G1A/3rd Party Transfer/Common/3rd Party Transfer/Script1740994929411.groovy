import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.vcb.mobile.internal.pages.base.BasePage as BasePage
import com.vcb.mobile.internal.pages.base.CommonPage as CommonPage
import com.vcb.mobile.internal.pages.base.CommonPage.TestObjectTypeEnum as TestObjectTypeEnum
import com.vcb.mobile.internal.pages.base.ConfirmationPage as ConfirmationPage
import com.vcb.mobile.internal.pages.base.HomePage as HomePage
import com.vcb.mobile.internal.pages.base.PushNotificationPage as PushNotificationPage
import com.vcb.mobile.internal.pages.base.Secure2uAuthorisationPage as Secure2uAuthorisationPage
import com.vcb.mobile.internal.pages.base.accountpage.AccountDetailPage as AccountDetailPage
import com.vcb.mobile.internal.pages.base.accountpage.AccountsPage as AccountsPage
import com.vcb.mobile.internal.pages.base.transferpage.TransferPage as TransferPage
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
import internal.GlobalVariable as GlobalVariable

'Login VCB Secure2u'
WebUI.callTestCase(findTestCase('Mobile/VCB/Common/Login Secure2u'), [('idNumber') : idNumber, ('PAN') : PAN, ('password') : password
        , ('username') : username, ('accountName') : accountName], FailureHandling.STOP_ON_FAILURE)

HomePage homePage = BasePage.getPage(HomePage)

CommonPage commonPage = BasePage.getPage(CommonPage)

TransferPage transferPage = BasePage.getPage(TransferPage)

Secure2uAuthorisationPage s2u = BasePage.getPage(Secure2uAuthorisationPage)

AccountsPage accountsPage = BasePage.getPage(AccountsPage)

ConfirmationPage confirmationPage = BasePage.getPage(ConfirmationPage)

PushNotificationPage pushNotificationPage = BasePage.getPage(PushNotificationPage)

AccountDetailPage accountDetailPage = AccountDetailPage.getPage(AccountDetailPage)

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
    'Select Banks -> select VCB'
    transferPage.openNewTransferOthers('Banks', 'VCB')

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
transferPage.authorize(status)

'Status screen, add fav, share receipt'
boolean isAddFav = favName.isEmpty() ? true : false

String RefID = transferPage.statusScreen(status, isAddFav, isFuture, true)

String[] approvalStatus = RefID.split(',')

String statusApproval = ''

if (approvalStatus.length > 1) {
    RefID = (approvalStatus[0])

    statusApproval = (approvalStatus[1])
}

Mobile.comment(RefID)

Mobile.comment(amount)

'Back to Home Page'
commonPage.backToHome()

if (status == 'Approve') {
    'Verify notification'
    String parsedAmount = String.format('%.2f', amountDouble)
	
	'Assign Push Notification Data'
	titlePN    = pushNotiTitle
	messagePN = pushNotiMsg + " $parsedAmount"
	
	'Verify Push Notification'
	WebUI.callTestCase(findTestCase('Mobile/VCB/Common/Verify Push Notification'), [('titlePN') : titlePN, ('startMessage') : startNotifycationMessage
		, ('messagePN') : messagePN],
	FailureHandling.STOP_ON_FAILURE)
}
