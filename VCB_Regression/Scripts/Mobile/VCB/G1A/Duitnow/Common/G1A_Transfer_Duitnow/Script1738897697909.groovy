import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import com.vcb.common.MobileDriver
import com.vcb.mobile.internal.pages.base.BasePage
import com.vcb.mobile.internal.pages.base.CommonPage
import com.vcb.mobile.internal.pages.base.ConfirmationPage
import com.vcb.mobile.internal.pages.base.EnterAmountPage
import com.vcb.mobile.internal.pages.base.HomePage
import com.vcb.mobile.internal.pages.base.PushNotificationPage
import com.vcb.mobile.internal.pages.base.Secure2uAuthorisationPage
import com.vcb.mobile.internal.pages.base.StatusScreenPage
import com.vcb.mobile.internal.pages.base.accountpage.AccountDetailPage
import com.vcb.mobile.internal.pages.base.transferpage.EnterDuitnowProxyPage
import com.vcb.mobile.internal.pages.base.transferpage.RecipientReferencePage
import com.vcb.mobile.internal.pages.base.transferpage.TransferPage

import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

HomePage homePage = BasePage.getPage(HomePage)
TransferPage transferPage = BasePage.getPage(TransferPage)
CommonPage commonPage = BasePage.getPage(CommonPage)
EnterDuitnowProxyPage enterDuitnowProxyPage = BasePage.getPage(EnterDuitnowProxyPage)
EnterAmountPage enterAmountPage = BasePage.getPage(EnterAmountPage)
RecipientReferencePage recipientReferencePage = BasePage.getPage(RecipientReferencePage)
ConfirmationPage confirmationPage = BasePage.getPage(ConfirmationPage)
Secure2uAuthorisationPage secure2uAuthorisationPage = BasePage.getPage(Secure2uAuthorisationPage)
StatusScreenPage statusScreenPage = BasePage.getPage(StatusScreenPage)
PushNotificationPage pushNotificationPage = BasePage.getPage(PushNotificationPage)
AccountDetailPage accountDetailPage = AccountDetailPage.getPage(AccountDetailPage)

WebUI.callTestCase(findTestCase('Mobile/VCB/Common/Login Secure2u'), [('idNumber') : idNumber, ('PAN') : PAN, ('password') : password
	, ('username') : username, ('accountName') : accountName], FailureHandling.STOP_ON_FAILURE)

'Get Account Number'
String accountNumber = homePage.getAccountNumber()

'Enter Proxy ID'
homePage.selectQuickActions(tranferName)
transferPage.selectTab(transferTab, true)
transferPage.openDuitnowFlow()
MobileDriver.delay(GlobalVariable.G_timeoutMiniShort)
enterDuitnowProxyPage.chooseProxyType(product)
enterDuitnowProxyPage.enterProxyNumber(product, [productData, productData2])

'Enter Amount'
enterAmountPage.enterAmountDuitNow(amount)

'Recipient Reference'
recipientReferencePage.inputRecipientReference()

'Confirmation Page'
confirmationPage.verifyHasLandedOnConfirmationScreen()
confirmationPage.proceedToAuthorisation()

'Authorisation'
secure2uAuthorisationPage.authorize(status)

'Status Screen'
def RefID = statusScreenPage.verifyDuitnowStatusUI(status, futuretype)

if(status.equals('Approve')) {
	statusScreenPage.verifyShareReceipt(futuretype)
	statusScreenPage.addFavoritesDuitnow(favName, isOnUs)
}

statusScreenPage.backToEntryPoint()

if(status.equals('Approve')) {
	'Push Notification'
	pushNotificationPage.verifyTransferNotificationDuitNow(product, productData, RefID, Double.parseDouble(amount)/100)
}

'Tap View All Accounts'
homePage.navigateToAccountDetail()

'Verify transaction history'
String parsedAmount = String.format("%.2f", Double.parseDouble(amount)/100)
accountDetailPage.verifyM2UHistoryFirstItemToday(history, '-RM ' + parsedAmount)
