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
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

import com.maybank.mae.internal.pages.base.HomePage
import com.maybank.mae.internal.pages.base.CommonPage
import com.maybank.mae.internal.pages.base.applypage.ApplyPage
import com.maybank.mae.internal.pages.base.accountpage.AccountsPage
import com.maybank.mae.internal.pages.base.BasePage
import com.maybank.mae.internal.pages.base.fixeddepositpage.FixedDepositPage
import com.maybank.utils.*
import com.maybank.assertions.*

Mobile.startExistingApplication(GlobalVariable.G_appId)
HomePage homePage = HomePage.getPage(HomePage)

CommonPage commonPage = BasePage.getPage(CommonPage)

BaseAssert Assert = new BaseAssert()

ApplyPage applyPage = BasePage.getPage(ApplyPage)

AccountsPage accountsPage = BasePage.getPage(AccountsPage)

FixedDepositPage fixedDepositPage = BasePage.getPage(FixedDepositPage)

StringUtils stringUtils = new StringUtils()
fdAccountType == applyPage.PNAi

'Login MAE Secure2u'
WebUI.callTestCase(findTestCase('Mobile/MAE/Common/Login Secure2u'), [('idNumber') : icNumber, ('PAN') : PAN, ('password') : password
		, ('username') : username, ('accountName') : accountName], FailureHandling.STOP_ON_FAILURE)

'Declare Insufficient Amount'
String balance = homePage.getBalance()
BigInteger balanceNumber = commonPage.convertAmountToNumber(balance)
BigInteger insufficientAmount = balanceNumber + 100000
Mobile.comment(insufficientAmount.toString())

'Tap Accounts'
homePage.tapBtnAccounts()

'Tap on "FIXED DEPOSITS" tab'
accountsPage.selectTab('FIXED DEPOSITS')

'Tap "+ Make Placement" card'
commonPage.tap(fixedDepositPage.btnMakeAPlacement(), timeout, FailureHandling.STOP_ON_FAILURE)

'VP: User is on Step 1 Screen'
Boolean isCurrentStep = applyPage.verifyCurrentStep('1')
Assert.equals(true, isCurrentStep, 'Current Step', FailureHandling.STOP_ON_FAILURE)

'Select 1 account Type, then tap Next'
applyPage.selectFDAccountType(fdAccountType)
applyPage.tapNext()
Mobile.waitForElementPresent(commonPage.lblDynamic("Please fill in your details"), timeout, FailureHandling.CONTINUE_ON_FAILURE)

'Step 1: Enter User detail info'
applyPage.enterUserDetail(emailAddress, addressLine1, addressLine2, addressLine3,
	postcode, city, country, state, GlobalVariable.G_timeoutMedium, FailureHandling.STOP_ON_FAILURE)

'Step 1: Enter User Employment infor'
applyPage.enterEmploymentDetail(employmentType, occupation, employerName, sector, monthlyIncome, srcOfIncome,
	GlobalVariable.G_timeoutMedium, FailureHandling.STOP_ON_FAILURE)

'VP: User is on Step 2 Screen'
isCurrentStep = applyPage.verifyCurrentStep('2')
Assert.equals(true, isCurrentStep, 'Current Step', FailureHandling.STOP_ON_FAILURE)

'Step 2: Enter Banks Transfer'
applyPage.selectBankTransferFrom(bankTransferFrom, timeout, FailureHandling.STOP_ON_FAILURE)

'Step 2: Enter Fixed Deposit Detail'
applyPage.enterFixedDepositDetail(fdAccountType, "${accountName} ${accountNo}", campaign, tenure, timeout, FailureHandling.STOP_ON_FAILURE)

'Tap Continue'
commonPage.tapButton("Continue", timeout, FailureHandling.STOP_ON_FAILURE)

'Not Enter Amount, tap tick button'
applyPage.enterDepositAmount("", timeout, FailureHandling.STOP_ON_FAILURE)
commonPage.verifyElementExist(commonPage.lblDynamic("Principal amount must be at least RM 10,000.00"), timeout, FailureHandling.CONTINUE_ON_FAILURE)
'Pricipal amount must be at least RM 10,000.00'
Mobile.delay(15)
'Enter Amount < 10,000.00'
applyPage.enterDepositAmount(amountLessThanMin, timeout, FailureHandling.STOP_ON_FAILURE)
commonPage.verifyElementExist(commonPage.lblDynamic("Principal amount must be at least RM 10,000.00"), timeout, FailureHandling.CONTINUE_ON_FAILURE)

Mobile.delay(15)
'Enter Amount > balance amount of account transfer'
Mobile.comment(amountLessThanMin.length().toString())
commonPage.removeEnterNo(amountLessThanMin.length())

'Enter Insufficient Amount'
applyPage.enterDepositAmount(insufficientAmount.toString(), timeout, FailureHandling.STOP_ON_FAILURE)
commonPage.verifyElementExist(commonPage.lblDynamic("You have insufficient balance. Please change the amount or select another account to transfer from."), timeout, FailureHandling.CONTINUE_ON_FAILURE)

Mobile.delay(15)