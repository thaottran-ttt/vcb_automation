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
import com.maybank.mae.internal.pages.base.accountpage.AccountDetailPage
import com.maybank.mae.internal.pages.base.fixeddepositpage.FixedDepositPage
import com.maybank.utils.*
import com.maybank.assertions.*

Mobile.startExistingApplication(GlobalVariable.G_appId)
HomePage homePage = HomePage.getPage(HomePage)

CommonPage commonPage = BasePage.getPage(CommonPage)

BaseAssert Assert = new BaseAssert()

ApplyPage applyPage = BasePage.getPage(ApplyPage)

AccountsPage accountsPage = BasePage.getPage(AccountsPage)

AccountDetailPage accountPage = BasePage.getPage(AccountDetailPage)

FixedDepositPage fixedDepositPage = BasePage.getPage(FixedDepositPage)

StringUtils stringUtils = new StringUtils()
fdAccountType == applyPage.islamicFD

'Login MAE Secure2u'
WebUI.callTestCase(findTestCase('Mobile/MAE/Common/Login Secure2u'), [('idNumber') : icNumber, ('PAN') : PAN, ('password') : password
		, ('username') : username, ('accountName') : accountName, ('yourName') : cusName], FailureHandling.STOP_ON_FAILURE)

'Declare Insufficient Amount'
String balance = homePage.getBalance()
Mobile.comment(balance)
BigInteger balanceNumber = commonPage.convertAmountToNumber(balance)

'Tap Accounts'
homePage.tapBtnAccounts()

'Tap on "FIXED DEPOSITS" tab'
accountsPage.selectTab('FIXED DEPOSITS')

'Tap "+ Make Placement" card'
if(Mobile.waitForElementPresent(commonPage.btnDynamic("Apply Now"), GlobalVariable.G_timeoutLong, FailureHandling.OPTIONAL)) {
	'Tap "Apply Now" card'
	commonPage.tapButton("Apply Now", timeout, FailureHandling.STOP_ON_FAILURE)
}
else {
	commonPage.tap(fixedDepositPage.btnMakeAPlacement(), timeout, FailureHandling.STOP_ON_FAILURE)
}

'VP: User is on Step 1 Screen'
Boolean isCurrentStep = applyPage.verifyCurrentStep('1')
Assert.equals(true, isCurrentStep, 'Current Step', FailureHandling.STOP_ON_FAILURE)

'Select 1 account Type, then tap Next'
applyPage.selectFDAccountType(fdAccountType)
applyPage.tapNext()

if(!(Mobile.waitForElementPresent(commonPage.imgSiblingGroup("Account Number"), GlobalVariable.G_timeoutMedium, FailureHandling.OPTIONAL))) {	
	Mobile.waitForElementPresent(commonPage.lblDynamic("Please fill in your details"), timeout, FailureHandling.CONTINUE_ON_FAILURE)
	if(fdAccountType == applyPage.GIAi) {
		applyPage.selectFinancialObjective(fdAccountType, financialObjective, stateObjective, timeout, FailureHandling.STOP_ON_FAILURE)
		applyPage.setSuitabilityAssessment(fdAccountType, lstAnswers, timeout, FailureHandling.STOP_ON_FAILURE)
		commonPage.tapButton("NEXT", timeout, FailureHandling.STOP_ON_FAILURE)
	}

	'Step 1: Enter User detail info'
	applyPage.enterUserDetail(emailAddress, addressLine1, addressLine2, addressLine3,
		postcode, city, country, state, GlobalVariable.G_timeoutMedium, FailureHandling.STOP_ON_FAILURE)
	
	'Step 1: Enter User Employment infor'
	applyPage.enterEmploymentDetail(employmentType, occupation, employerName, sector, monthlyIncome, srcOfIncome,
		GlobalVariable.G_timeoutMedium, FailureHandling.STOP_ON_FAILURE)
}
else {	
	'VP: User is on Step 2 Screen'
	isCurrentStep = applyPage.verifyCurrentStep('2')
	Assert.equals(true, isCurrentStep, 'Current Step', FailureHandling.STOP_ON_FAILURE)
	
	applyPage.selectAccountNumber("${accountName} ${accountNo}", timeout, FailureHandling.STOP_ON_FAILURE)
}

'Step 2: Enter Banks Transfer'
applyPage.selectBankTransferFrom(bankTransferFrom, timeout, FailureHandling.STOP_ON_FAILURE)

'Step 2: Enter Fixed Deposit Detail'
applyPage.enterFixedDepositDetail(fdAccountType, "${accountName} ${accountNo}", campaign, tenure, timeout, FailureHandling.STOP_ON_FAILURE)

'Tap Continue'
commonPage.tapButton("Continue", timeout, FailureHandling.STOP_ON_FAILURE)

'Enter Amount, tap tick button'
applyPage.enterDepositAmount(amount, timeout, FailureHandling.STOP_ON_FAILURE)

'VP: User is on Step 3 Screen'
isCurrentStep = applyPage.verifyCurrentStep('3')
Assert.equals(true, isCurrentStep, 'Current Step', FailureHandling.STOP_ON_FAILURE)

'Step 3: Enter Payment Info'
applyPage.enterPaymentInfo(profitPaymentFrequency, profitPaymentMode, maturityInstruction, "${accountName} ${accountNo}", timeout, FailureHandling.STOP_ON_FAILURE)

'Check Term and Conditions'
applyPage.checkTermAndCondition(termCheck, timeout, FailureHandling.STOP_ON_FAILURE)

'Tap Make Placement'
commonPage.tapButton('Make Placement', timeout, FailureHandling.STOP_ON_FAILURE)

'Verfy Make Placement Submit'
String dateTime = applyPage.getTransactionDateTime("Date & time", timeout, FailureHandling.CONTINUE_ON_FAILURE)

'Verify Transaction result Expired'
applyPage.verifyApplyTransactionExpired(fdAccountType, GlobalVariable.G_timeoutLong, FailureHandling.STOP_ON_FAILURE)

commonPage.tapButton("Done")
homePage.tapMenu("Home")
String balancePost = homePage.getBalance()
Mobile.comment(balancePost)
BigInteger balancePostNumber = commonPage.convertAmountToNumber(balancePost)
Assert.equals(true, balancePostNumber == balanceNumber, 'Verify Balance is not descreaded', FailureHandling.STOP_ON_FAILURE)