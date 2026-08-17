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
import com.maybank.common.MobileDriver as MobileDriver
import com.maybank.mae.internal.pages.base.accountpage.AccountsPage as AccountsPage
import com.maybank.mae.internal.pages.base.BasePage as BasePage
import com.maybank.mae.internal.pages.base.ConfirmationPage as ConfirmationPage
import com.maybank.mae.internal.pages.base.EnterAmountPage as EnterAmountPage
import com.maybank.mae.internal.pages.base.HomePage as HomePage
import com.maybank.mae.internal.pages.base.accountpage.LoanPage as LoanPage
import com.maybank.mae.internal.pages.base.Secure2uAuthorisationPage as Secure2uAuthorisationPage
import com.maybank.mae.internal.pages.base.StatusScreenPage as StatusScreenPage
import com.maybank.mae.internal.pages.base.PushNotificationPage as PushNotificationPage
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

'Login MAE Secure2u'
WebUI.callTestCase(findTestCase('Mobile/MAE/Common/Login Secure2u'), [('idNumber') : idNumber, ('PAN') : PAN, ('password') : password
	, ('username') : username, ('accountName') : accountName], FailureHandling.STOP_ON_FAILURE)

HomePage homePage = BasePage.getPage(HomePage)

AccountsPage accountsPage = BasePage.getPage(AccountsPage)

LoanPage loanPage = BasePage.getPage(LoanPage)

EnterAmountPage enterAmountPage = BasePage.getPage(EnterAmountPage)

ConfirmationPage confirmationPage = BasePage.getPage(ConfirmationPage)

Secure2uAuthorisationPage secure2uAuthorisationPage = BasePage.getPage(Secure2uAuthorisationPage)

StatusScreenPage statusScreenPage = BasePage.getPage(StatusScreenPage)

PushNotificationPage pushNotificationPage = BasePage.getPage(PushNotificationPage)

'Tap Accounts'
homePage.tapBtnAccounts()

'Tap Loan'
accountsPage.selectTab(tabName)

'Verify Loan Dashboard'
loanPage.verifyLoanDashboard(product)

'Verify loan detail'
loanPage.loanDetails(product)

'Loan Type of Payment'
loanPage.loanTypeOfPayment(product)

'Enter Amount'
enterAmountPage.enterAmount(amount)

'Confirmation'
confirmationPage.confirmLoan()

'Authorisation'
secure2uAuthorisationPage.performApproveRejectOrExpired(status)

'Status Page'
def refID = statusScreenPage.verifyPaymentStatusUI(status, futuretype)
Mobile.comment("Reference ID: " + refID)

statusScreenPage.backToEntryPoint()

'Verify Push Noti'
pushNotificationPage.verifyPushNotificationSamsung24(pushNotificationTitle, pushNotificationDescription + refID)

'Back to home'
MobileDriver.delay(2)

homePage.tapMenu(homeMenu)

