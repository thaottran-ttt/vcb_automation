import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import javax.persistence.criteria.CommonAbstractCriteria as CommonAbstractCriteria
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
import com.vcb.mobile.internal.pages.base.HomePage
import com.vcb.mobile.internal.pages.base.CommonPage
import com.vcb.mobile.internal.pages.base.fixeddepositpage.FixedDepositPage
import com.vcb.mobile.internal.pages.base.applypage.ApplyPage
import com.vcb.mobile.internal.pages.base.accountpage.AccountsPage
import com.vcb.mobile.internal.pages.base.BasePage
import com.vcb.utils.*
import com.vcb.assertions.*

HomePage homePage = HomePage.getPage(HomePage)

CommonPage commonPage = BasePage.getPage(CommonPage)

BaseAssert Assert = new BaseAssert()

ApplyPage applyPage = BasePage.getPage(ApplyPage)

AccountsPage accountsPage = BasePage.getPage(AccountsPage)

FixedDepositPage fixedDepositPage = BasePage.getPage(FixedDepositPage)

'Login VCB Secure2u'
WebUI.callTestCase(findTestCase('Mobile/VCB/Common/Login Secure2u'), [('idNumber') : icNumber, ('PAN') : PAN, ('password') : password
        , ('username') : username, ('accountName') : accountName], FailureHandling.STOP_ON_FAILURE)

'Tap Apply'
homePage.tapApply()

'Tap on "FIXED DEPOSITS" tab'
applyPage.selectTab('FIXED DEPOSITS', true)

'Tap "Fixed Deposit Account" card'
applyPage.tapFixedDepositAccount()

'VP: User is on Step 1 Screen'
Boolean isCurrentStep = applyPage.verifyCurrentStep('1')
Assert.equals(true, isCurrentStep, 'Current Step')

'Select 1 account Type, then tap Next'
applyPage.selectFDAccountType(fdAccountType)
applyPage.tapNext()

'Step 1: Enter Financial Objective if FD Account Type = General Investment Account-i (GIA-i)'
if(fdAccountType == applyPage.GIAi) {
	applyPage.selectFinancialObjective(financialObjective, stateObjective, GlobalVariable.G_timeoutMedium)
	applyPage.setSuitabilityAssessment(fdAccountType, lstAnswers, GlobalVariable.G_timeoutMedium)
	commonPage.tapButton("NEXT", timeout)
}

'Step 1: Enter User detail info'
applyPage.enterUserDetail(emailAddress, addressLine1, addressLine2, addressLine3, 
	postcode, city, country, state, GlobalVariable.G_timeoutMedium)

'Step 1: Enter User Employment infor'
applyPage.enterEmploymentDetail(employmentType, occupation, employerName, sector, monthlyIncome, srcOfIncome, 
	GlobalVariable.G_timeoutMedium)

'VP: User is on Step 2 Screen'
isCurrentStep = applyPage.verifyCurrentStep('2')
Assert.equals(true, isCurrentStep, 'Current Step')

'Step 2: Enter Banks Transfer'
applyPage.selectBankTransferFrom(bankTransferFrom, timeout)

'Step 2: Enter Fixed Deposit Detail'
applyPage.enterFixedDepositDetail(fdAccountType, accountFrom, campaign, tenure, timeout)

'Step 2: Get Profit Sharing Ratio if FD Account Type = General Investment Account-i (GIA-i)'
def profitSharingRatio = applyPage.getProfitSharingRatio(fdAccountType, timeout)

'Tap Continue'
commonPage.tapButton("Continue", timeout)

'Enter Amount'
applyPage.enterDepositAmount(amount, timeout)

'VP: User is on Step 3 Screen'
isCurrentStep = applyPage.verifyCurrentStep('3')
Assert.equals(true, isCurrentStep, 'Current Step')

'Step 3: Enter Payment Info'
applyPage.enterPaymentInfo(profitPaymentFrequency, profitPaymentMode, maturityInstruction, creditingAccount, timeout)

'Check Term and Conditions'
applyPage.verifyTermsConditions(fdAccountType, timeout)
applyPage.checkTermAndCondition(termCheck, timeout)

'VP: Verify Confirm information'
applyPage.verifyConfirmInfo(fdAccountType, amount, accountFrom, profitPaymentMode, maturityInstruction, 
	creditingAccount, campaign, tenure, profitPaymentFrequency, profitSharingRatio, timeout)

'Tap Make Placement'
commonPage.tapButton('Make Placement', timeout)

'Verfy Make Placement Submit'
String dateTime = applyPage.getTransactionDateTime("Date & time", timeout)
applyPage.verifySubmitMakePlacement(fdAccountType, accountFrom, amount, dateTime, timeout)

'Submit Make Placement'
applyPage.submitMakePlacement(hasApproved, timeout)

'Verify Transaction result'
applyPage.verifyTransactionResult(hasApproved, fdAccountType, accountFrom, amount, dateTime, timeout)

'Navigate to Accounts > Fixed Deposit'
accountsPage.selectTab("FIXED DEPOSITS")
commonPage.waitLoading(GlobalVariable.G_timeoutLong, FailureHandling.OPTIONAL)

'Get info the added FD Card'
fixedDepositPage.verifyDashboardCardInfo(fdAccountType, blAccNo, "No. of certs: 1", "RM ${amount}")
