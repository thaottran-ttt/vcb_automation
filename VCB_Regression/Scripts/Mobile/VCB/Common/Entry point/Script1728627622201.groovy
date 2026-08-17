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
import com.vcb.mobile.internal.pages.base.accountpage.AccountDetailPage
import com.vcb.mobile.internal.pages.base.accountpage.AccountsPage
import com.vcb.mobile.internal.pages.base.BasePage
import com.vcb.mobile.internal.pages.base.CommonPage
import com.vcb.mobile.internal.pages.base.HomePage
import com.vcb.mobile.internal.pages.base.transferpage.TransferPage
import com.vcb.mobile.internal.pages.base.ConfirmationPage

import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

AccountsPage accountsPage = BasePage.getPage(AccountsPage)
CommonPage commonPage = BasePage.getPage(CommonPage)
HomePage homePage = BasePage.getPage(HomePage)
AccountDetailPage accountDetailPage = BasePage.getPage(AccountDetailPage)
TransferPage transferPage = BasePage.getPage(TransferPage)
ConfirmationPage confirmationPage = BasePage.getPage(ConfirmationPage)

switch (accessFlow) {
	case 'Quick Action':
		homePage.selectQuickActions(actionName)
		break
	case 'Wallet':
		'Select Accounts button'
		homePage.navigateToAccountDetail()
		'Select menu'
		accountDetailPage.selectMenu(menuName)
		break
	case 'Account Detail':
		String accountName = homePage.getAccountName()
		'Select Accounts button'
		homePage.tapBtnAccounts()
		'Tap account'
		accountsPage.selectAccount(accountName)
		'Select menu'
		accountDetailPage.selectMenu(menuName)
		break
	case '3rd Party Transfer':
		'Select Transfer Module at QuickActions'
		homePage.selectQuickActions(tranferName)
		break
	case 'DuitNow':
		homePage.selectQuickActions(tranferName)
		transferPage.selectTab(transferTab, true)
		transferPage.openDuitnowFlow()
		break
	case 'PayBill':
		homePage.selectQuickActions(tranferName)
		break	
	case 'Zakat':
		homePage.selectQuickActions('Pay Bills')
		break
	case 'JomPay':
		homePage.selectQuickActions(actionName)
		commonPage.checkEnterPassword(password)
		break
	case 'Payment':
		homePage.selectQuickActions(tranferName)
		break
	case 'G1A':
		'Tap Button Accounts'
		homePage.tapBtnAccounts()
		
		'Choose Account'
		confirmationPage.chooseAnotherAccount(recipientAccount)
		
		'Verify TRANSACT entry points'
		accountDetailPage.verifyG1ATransactEntryPoints(entryPoints)
		break
	case 'Loan':
		'Select Accounts button'
		homePage.navigateToAccountDetail()
		'Select menu'
		accountDetailPage.selectMenu(menuName)
		break
	default:
		homePage.selectQuickActions(actionName)
		break
}