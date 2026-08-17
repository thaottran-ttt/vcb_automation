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
import com.maybank.common.MobileDriver
import com.maybank.mae.internal.pages.base.BasePage
import com.maybank.mae.internal.pages.base.CommonPage
import com.maybank.mae.internal.pages.base.HomePage
import com.maybank.mae.internal.pages.base.PinPage
import com.maybank.mae.internal.pages.base.Secure2uAuthorisationPage
import com.maybank.mae.internal.pages.base.StatusScreenPage
import com.maybank.mae.internal.pages.base.settingpage.SettingPage
import com.maybank.utils.ListUtils

import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

List verifyUICreatePINData = ListUtils.parseObjectToList(verifyUICreatePINData)
List verifyUIConfirmPINData = ListUtils.parseObjectToList(verifyUIConfirmPINData)
List verifyUISettingsData = ListUtils.parseObjectToList(verifyUISettings)
List statusTitle = ListUtils.parseObjectToList(statusTitle)
List statusDescription = ListUtils.parseObjectToList(statusDescription)

WebUI.callTestCase(findTestCase('Mobile/MAE/Common/Login Secure2u'), [('idNumber') : idNumber, ('PAN') : PAN, ('password') : password
        , ('username') : username, ('accountName') : accountName, ('flowControl') : com.maybank.common.FlowControlEnum.STOP
        , ('failStopTestSuite') : false, ('yourName') : 'Test'], FailureHandling.STOP_ON_FAILURE)

CommonPage commonPage = BasePage.getPage(CommonPage)
HomePage homePage = BasePage.getPage(HomePage)
PinPage pinPage = BasePage.getPage(PinPage)
SettingPage settingPage = BasePage.getPage(SettingPage)
Secure2uAuthorisationPage secure2uAuthorisationPage = BasePage.getPage(Secure2uAuthorisationPage)
StatusScreenPage statusScreenPage = BasePage.getPage(StatusScreenPage)

'Select Settings Button'
homePage.clickSettingsButton()

'Select Security'
settingPage.tapLblMaybank2u()

'Select Change App PIN'
settingPage.tapChangeAppPIN()

'Verify Enter Wrong PIN'
pinPage.verifyEnterWrongPIN(wrongPIN,wrongToastMessage)

'Enter Current PIN'
pinPage.enterPINValid(oldPIN)

'Create Your New PIN'
pinPage.enterPin()

'Secure 2u'
secure2uAuthorisationPage.authorize(status)

'Enter 6-PIN Digit for S2U V5'
commonPage.enterS2UV6PIN()

'Status Screen'
statusScreenPage.verifyStatusScreen(statusTitle, statusDescription)

'Tap Done'
commonPage.tapDoneBtn()