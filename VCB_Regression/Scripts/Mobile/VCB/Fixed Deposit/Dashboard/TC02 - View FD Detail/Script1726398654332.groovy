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
import com.vcb.mobile.internal.pages.base.HomePage
import com.vcb.mobile.internal.pages.base.CommonPage
import com.vcb.mobile.internal.pages.base.applypage.ApplyPage
import com.vcb.mobile.internal.pages.base.accountpage.AccountsPage
import com.vcb.mobile.internal.pages.base.BasePage
import com.vcb.utils.*
import com.vcb.assertions.*

HomePage homePage = HomePage.getPage(HomePage)

CommonPage commonPage = BasePage.getPage(CommonPage)

BaseAssert Assert = new BaseAssert()

AccountsPage accountsPage = BasePage.getPage(AccountsPage)

FixedDepositPage fixedDepositPage = BasePage.getPage(FixedDepositPage)

StringUtils strUtils = new StringUtils()

'Login VCB Secure2u'
WebUI.callTestCase(findTestCase('Mobile/VCB/Common/Login Secure2u'), [('idNumber') : icNumber, ('PAN') : PAN, ('password') : password
        , ('username') : username, ('accountName') : accountName], FailureHandling.STOP_ON_FAILURE)

'Tap View All Accounts > FIXED DEPOSITS'
homePage.tapMenu('Accounts')
accountsPage.selectTab('FIXED DEPOSITS')

'Select 1 card on Dashboard screen'
fixedDepositPage.verifyDashboardCardInfo(fdAccountType, fdAccountNo, noCerts, fdAmount, timeout)
fixedDepositPage.tap(fixedDepositPage.divFDCard(fdAccountType, fdAccountNo, noCerts, fdAmount) , timeout)

'Verify FD details box - green color'
fixedDepositPage.verifyDFCardDetailHeader(fdAccountType, fdAccountNo, fdAmount, timeout)

'Verify buttons'
fixedDepositPage.verifyDFCardDetailButtons(timeout)

'Verify Detail info'
fixedDepositPage.verifyDFCardDetail(certificate, fdAmount, tenure, profitRate, maturityDate, 
		profitPaymentFrequency, profitPaymentMode, primaryAccountHolder, timeout)

