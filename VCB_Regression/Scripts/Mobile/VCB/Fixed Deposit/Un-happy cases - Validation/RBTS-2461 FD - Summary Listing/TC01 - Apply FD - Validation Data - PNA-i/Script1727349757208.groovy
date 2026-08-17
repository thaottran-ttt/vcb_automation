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

Mobile.startExistingApplication(GlobalVariable.G_appId)
HomePage homePage = HomePage.getPage(HomePage)

CommonPage commonPage = BasePage.getPage(CommonPage)

BaseAssert Assert = new BaseAssert()

ApplyPage applyPage = BasePage.getPage(ApplyPage)

AccountsPage accountsPage = BasePage.getPage(AccountsPage)

FixedDepositPage fixedDepositPage = BasePage.getPage(FixedDepositPage)
fdAccountType == applyPage.PNAi
//
//'Login VCB Secure2u'
//WebUI.callTestCase(findTestCase('Mobile/VCB/Common/Login Secure2u'), [('idNumber') : icNumber, ('PAN') : PAN, ('password') : password
//		, ('username') : username, ('accountName') : primaryAccountName, ('yourName') : accountName], FailureHandling.STOP_ON_FAILURE)
//
//'Tap Apply'
//homePage.tapApply()
//
//'Tap on "FIXED DEPOSITS" tab'
//applyPage.selectTab(applyTab)
//
//'Tap "Fixed Deposit Account" card'
//applyPage.tapFixedDepositAccount()
//
//'VP: User is on Step 1 Screen'
//Boolean isCurrentStep = applyPage.verifyCurrentStep('1')
//Assert.equals(true, isCurrentStep, 'Current Step', FailureHandling.STOP_ON_FAILURE)
//
//'Select 1 account Type, then tap Next'
//applyPage.selectFDAccountType(fdAccountType)
//applyPage.tapNext()
//
'Step 1: Verify User detail info'
commonPage.clearText("Email address", false, timeout, FailureHandling.STOP_ON_FAILURE)
commonPage.verifyElementExist(commonPage.lblDynamic("Please enter a valid email address."), timeout, FailureHandling.CONTINUE_ON_FAILURE)

applyPage.enterUserDetailInfo("Email address", emailAddressInvalid, timeout, FailureHandling.STOP_ON_FAILURE)
commonPage.verifyElementExist(commonPage.lblDynamic("Please enter a valid email address."), timeout, FailureHandling.CONTINUE_ON_FAILURE)

applyPage.enterUserDetailInfo("Email address", emailAddress, timeout, FailureHandling.STOP_ON_FAILURE)
Mobile.waitForElementAttributeValue(commonPage.btnDynamic("Next"), "visibility", true, timeout, FailureHandling.CONTINUE_ON_FAILURE)

//'Step 1: Verify Address Line 1 info'
commonPage.clearText("Address line 1", false, timeout, FailureHandling.STOP_ON_FAILURE)
commonPage.verifyElementExist(commonPage.lblDynamic("Please enter at least a character."), timeout, FailureHandling.CONTINUE_ON_FAILURE)

applyPage.enterUserDetailInfo("Address line 1", "\${55}", timeout, FailureHandling.STOP_ON_FAILURE)
commonPage.verifyElementExist(commonPage.lblDynamic("Please enter a valid address."), timeout, FailureHandling.CONTINUE_ON_FAILURE)

applyPage.enterUserDetailInfo("Address line 1", addressLineInvalid, timeout, FailureHandling.STOP_ON_FAILURE)
commonPage.verifyElementExist(commonPage.lblDynamic("Please enter a valid address."), timeout, FailureHandling.CONTINUE_ON_FAILURE)

applyPage.enterUserDetailInfo("Address line 1", addressLine, timeout, FailureHandling.STOP_ON_FAILURE)
Mobile.waitForElementAttributeValue(commonPage.btnDynamic("Next"), "visibility", true, timeout, FailureHandling.CONTINUE_ON_FAILURE)

//'Step 1: Verify Address Line 2 info'
commonPage.clearText("Address line 2", false, timeout, FailureHandling.STOP_ON_FAILURE)
commonPage.verifyElementExist(commonPage.lblDynamic("Please enter at least a character."), timeout, FailureHandling.CONTINUE_ON_FAILURE)

applyPage.enterUserDetailInfo("Address line 2", "\${55}", timeout, FailureHandling.STOP_ON_FAILURE)
commonPage.verifyElementExist(commonPage.lblDynamic("Please enter a valid address."), timeout, FailureHandling.CONTINUE_ON_FAILURE)

applyPage.enterUserDetailInfo("Address line 2", addressLineInvalid, timeout, FailureHandling.STOP_ON_FAILURE)
commonPage.verifyElementExist(commonPage.lblDynamic("Please enter a valid address."), timeout, FailureHandling.CONTINUE_ON_FAILURE)

applyPage.enterUserDetailInfo("Address line 2", addressLine, timeout, FailureHandling.STOP_ON_FAILURE)
Mobile.waitForElementAttributeValue(commonPage.btnDynamic("Next"), "visibility", true, timeout, FailureHandling.CONTINUE_ON_FAILURE)

//'Step 1: Verify Postcode info'
commonPage.clearText("Postcode", false, timeout, FailureHandling.STOP_ON_FAILURE)
commonPage.verifyElementExist(commonPage.lblDynamic("Please enter at least a character."), timeout, FailureHandling.CONTINUE_ON_FAILURE)

applyPage.enterUserDetailInfo("Postcode", "\${55}", timeout, FailureHandling.STOP_ON_FAILURE)
commonPage.verifyElementExist(commonPage.lblDynamic("Please enter a valid Postcode."), timeout, FailureHandling.CONTINUE_ON_FAILURE)

applyPage.enterUserDetailInfo("Postcode", postcodeInvalid, timeout, FailureHandling.STOP_ON_FAILURE)
commonPage.verifyElementExist(commonPage.lblDynamic("Please enter a valid Postcode."), timeout, FailureHandling.CONTINUE_ON_FAILURE)

applyPage.enterUserDetailInfo("Postcode", postcode, timeout, FailureHandling.STOP_ON_FAILURE)
Mobile.waitForElementAttributeValue(commonPage.btnDynamic("Next"), "visibility", true, timeout, FailureHandling.CONTINUE_ON_FAILURE)

//'Step 1: Verify City info'
commonPage.clearText("City", false, timeout, FailureHandling.STOP_ON_FAILURE)
commonPage.verifyElementExist(commonPage.lblDynamic("Please enter at least a character."), timeout, FailureHandling.CONTINUE_ON_FAILURE)

applyPage.enterUserDetailInfo("City", "\${55}", timeout, FailureHandling.STOP_ON_FAILURE)
commonPage.verifyElementExist(commonPage.lblDynamic("Please enter a valid City."), timeout, FailureHandling.CONTINUE_ON_FAILURE)

applyPage.enterUserDetailInfo("City", cityInvalid, timeout, FailureHandling.STOP_ON_FAILURE)
commonPage.verifyElementExist(commonPage.lblDynamic("Please enter a valid City."), timeout, FailureHandling.CONTINUE_ON_FAILURE)

applyPage.enterUserDetailInfo("City", city, timeout, FailureHandling.STOP_ON_FAILURE)
Mobile.waitForElementAttributeValue(commonPage.btnDynamic("Next"), "visibility", true, timeout, FailureHandling.CONTINUE_ON_FAILURE)

'Tap on Next'
commonPage.tapButton("Next", timeout, FailureHandling.STOP_ON_FAILURE)

//'Step 1: Verify Employer's name info'
commonPage.clearText("Employer’s name", false, timeout, FailureHandling.STOP_ON_FAILURE)
commonPage.verifyElementExist(commonPage.lblDynamic("Please enter at least a character."), timeout, FailureHandling.CONTINUE_ON_FAILURE)

applyPage.enterUserDetailInfo("Employer’s name", "\${55}", timeout, FailureHandling.STOP_ON_FAILURE)
commonPage.verifyElementExist(commonPage.lblDynamic("Please enter a valid City."), timeout, FailureHandling.CONTINUE_ON_FAILURE)

applyPage.enterUserDetailInfo("Employer’s name", employerNameInvalid, timeout, FailureHandling.STOP_ON_FAILURE)
commonPage.verifyElementExist(commonPage.lblDynamic("Please enter a valid City."), timeout, FailureHandling.CONTINUE_ON_FAILURE)

applyPage.enterUserDetailInfo("Employer’s name", employerName, timeout, FailureHandling.STOP_ON_FAILURE)
Mobile.waitForElementAttributeValue(commonPage.btnDynamic("Next"), "visibility", true, timeout, FailureHandling.CONTINUE_ON_FAILURE)
