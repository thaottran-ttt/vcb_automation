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
import com.vcb.mobile.internal.pages.base.HomePage
import com.vcb.mobile.internal.pages.base.PinPage
import com.vcb.mobile.internal.pages.base.Secure2uAuthorisationPage
import com.vcb.mobile.internal.pages.base.StatusScreenPage
import com.vcb.mobile.internal.pages.base.settingpage.SettingPage
import com.vcb.mobile.internal.pages.base.settingpage.securitypage.ChangePasswordPage
import com.vcb.mobile.internal.pages.base.settingpage.securitypage.SecurityQuestionsPage
import com.vcb.utils.ListUtils

import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

List verifyUISettingsData = ListUtils.parseObjectToList(verifyUISettings)
List verifyUISecurityData = ListUtils.parseObjectToList(verifyUISecurity)
List verifyUIAccountsData = ListUtils.parseObjectToList(verifyUIAccounts)
List verifyUIManageM2UAccessData = ListUtils.parseObjectToList(verifyUIManageM2UAccess)
List verifyUIVcb2uAccountData = ListUtils.parseObjectToList(verifyUIVcb2uAccount)
List verifyUIEInvoiceData = ListUtils.parseObjectToList(verifyUIEInvoice)
List statusTitle = ListUtils.parseObjectToList(statusTitle)
List statusDescription = ListUtils.parseObjectToList(statusDescription)

String UIlblSecurity = "Security"

WebUI.callTestCase(findTestCase('Mobile/VCB/Common/Login Secure2u'), [('idNumber') : idNumber, ('PAN') : PAN, ('password') : password
        , ('username') : username, ('accountName') : accountName, ('flowControl') : com.vcb.common.FlowControlEnum.STOP
        , ('failStopTestSuite') : false, ('yourName') : 'Test'], FailureHandling.STOP_ON_FAILURE)

CommonPage commonPage = BasePage.getPage(CommonPage)
HomePage homePage = BasePage.getPage(HomePage)
PinPage pinPage = BasePage.getPage(PinPage)
SettingPage settingPage = BasePage.getPage(SettingPage)
Secure2uAuthorisationPage secure2uAuthorisationPage = BasePage.getPage(Secure2uAuthorisationPage)
StatusScreenPage statusScreenPage = BasePage.getPage(StatusScreenPage)
ChangePasswordPage changePasswordPage = BasePage.getPage(ChangePasswordPage)
SecurityQuestionsPage securityQuestionsPage = BasePage.getPage(SecurityQuestionsPage)

'Select Settings Button'
homePage.clickSettingsButton()

'Verify UI Settings Data'
settingPage.verifyUISettings(verifyUISettingsData)

MobileDriver.scrollToText(UIlblSecurity)

'Select Security'
settingPage.tapLblVcb2u()

'Verify UI Settings Data'
settingPage.verifyUISettings(verifyUISecurityData)

'Tap Back'
commonPage.tapBack()

'Tap Accounts'
settingPage.tapLblAccounts()

'Tap Linked Accounts'
settingPage.tapUILinkedAccounts()

'Verify UI Settings Data'
settingPage.verifyUISettings(verifyUIAccountsData)

'Tap Linked Accounts'
settingPage.tapUIM2UAccess()

'Verify UI Manage M2UAccess Data'
settingPage.verifyUISettings(verifyUIManageM2UAccessData)

'Tap Back'
commonPage.tapBack()

'Tap UI Overseas Accounts'
settingPage.tapUIOverseasAccounts()

'Verify UI Manage M2UAccess Data'
settingPage.verifyUISettings(verifyUIVcb2uAccountData)

'Tap Back'
commonPage.tapBack()

'Tap UI e-Invoice'
settingPage.tapUIEInvoice()

'Verify UI Manage M2UAccess Data'
settingPage.verifyUISettings(verifyUIEInvoiceData)

'Tap Back'
commonPage.tapBack()

'Back to Home Page'
commonPage.backToHome()
