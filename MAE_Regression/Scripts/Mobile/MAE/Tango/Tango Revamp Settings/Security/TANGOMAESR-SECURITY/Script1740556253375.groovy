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
import com.maybank.mae.internal.pages.base.BasePage as BasePage
import com.maybank.mae.internal.pages.base.CommonPage as CommonPage
import com.maybank.mae.internal.pages.base.HomePage as HomePage
import com.maybank.mae.internal.pages.base.PinPage as PinPage
import com.maybank.mae.internal.pages.base.Secure2uAuthorisationPage as Secure2uAuthorisationPage
import com.maybank.mae.internal.pages.base.StatusScreenPage as StatusScreenPage
import com.maybank.mae.internal.pages.base.settingpage.SettingPage as SettingPage
import com.maybank.mae.internal.pages.base.settingpage.securitypage.BiometricPage as BiometricPage
import com.maybank.mae.internal.pages.base.settingpage.securitypage.ChangePasswordPage as ChangePasswordPage
import com.maybank.mae.internal.pages.base.settingpage.securitypage.KillSwitchPage as KillSwitchPage
import com.maybank.mae.internal.pages.base.settingpage.securitypage.SecurityPage as SecurityPage
import com.maybank.mae.internal.pages.base.settingpage.securitypage.SecurityQuestionsPage
import com.maybank.utils.ListUtils

import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

CommonPage commonPage = BasePage.getPage(CommonPage)

HomePage homePage = BasePage.getPage(HomePage)

SettingPage settingPage = BasePage.getPage(SettingPage)

SecurityPage securityPage = BasePage.getPage(SecurityPage)

BiometricPage biometricPage = BasePage.getPage(BiometricPage)

PinPage pinPage = BasePage.getPage(PinPage)

ChangePasswordPage changePasswordPage = BasePage.getPage(ChangePasswordPage)

SecurityQuestionsPage securityQuestionsPage = BasePage.getPage(SecurityQuestionsPage)

KillSwitchPage killSwitchPage = BasePage.getPage(KillSwitchPage)

Secure2uAuthorisationPage secure2uAuthorisationPage = BasePage.getPage(Secure2uAuthorisationPage)

StatusScreenPage statusScreenPage = BasePage.getPage(StatusScreenPage)

statusTitle = ListUtils.parseObjectToList(statusTitle)
statusDescription = ListUtils.parseObjectToList(statusDescription)
verifyUIConfirmPINData = ListUtils.parseObjectToList(verifyUIConfirmPINData)
verifyUIForgotPINData = ListUtils.parseObjectToList(verifyUIForgotPINData)
verifyUIUpdatePINData = ListUtils.parseObjectToList(verifyUIUpdatePINData)

WebUI.callTestCase(findTestCase('Mobile/MAE/Tango/Common/Entry Point'), [('username') : username, ('password') : password
        , ('idNumber') : idNumber, ('PAN') : PAN], FailureHandling.STOP_ON_FAILURE)

settingPage.UIMaybank2u = security

settingPage.tapLblMaybank2u()

'Tap on Secure2U item'
securityPage.tapLblSecure2u()

'Verify Notification popup is displayed & then tap on Cancel button'
securityPage.verifyLblDisableSecure2uExist()

securityPage.tapBtnCancel()

'Tap on App Biometric Login item'
securityPage.tapLblAppBiometricLogin()

'Verify Biometric Descript is displayed & then tap on Setup Later button'
biometricPage.verifyLblBiometricDescriptionExist()

biometricPage.tapBtnSetUpLater()

'Tap on Change PIN item'
securityPage.tapLblChangeAppPin()

'Change App Pin & then verify successful message'
commonPage.enterNO(pinCode)

commonPage.waitLoading()

pinPage.createYourPIN(pinCode, verifyUIUpdatePINData, verifyUIConfirmPINData)

securityPage.verifyLblPinUpdatedSuccessfullyMessageExist()

'Tap on Forgot PIN then Change PIN & Approve'
securityPage.tapLblChangeAppPin()

pinPage.tapForgotPIN(password)

pinPage.createYourPIN(pinCode, verifyUIForgotPINData, verifyUIConfirmPINData)

commonPage.waitLoading()

secure2uAuthorisationPage.authorize(authorizeApproval)

commonPage.waitLoading()

statusScreenPage.verifyStatusScreen(statusTitle, statusDescription)

commonPage.tapDoneBtn()

'Tab on Setting on Home page'
homePage.tapBtnSetting()

settingPage.tapLblMaybank2u()

'Tap on Change Password then change passwords which is same current password. System returns change password unsuccessfully'
securityPage.tapLblChangePassword()

changePasswordPage.changePassword(password, password, password)

commonPage.waitLoading()

commonPage.delay(3)

secure2uAuthorisationPage.authorize(authorizeApproval)

commonPage.waitLoading()

statusScreenPage.verifyStatusScreen(statusTitle, statusDescription)

commonPage.tapDoneBtn()

'Tap on Security Questions then select questions, input answers and change successfully'
securityPage.tapLblSecurityQuestions()

commonPage.waitLoading()

securityQuestionsPage.changeSecurityQuestionsAndAnswer(answer, answer, answer)

commonPage.waitLoading()

secure2uAuthorisationPage.authorize(authorizeApproval)

commonPage.waitLoading()

statusScreenPage.verifyStatusScreen(statusTitle, statusDescription)

commonPage.tapDoneBtn()

'Tap on Kill Switch'
securityPage.tapLblKillSwitch()

'Tap on Block Credit Card'
killSwitchPage.tapLblBlockCreditCard()

killSwitchPage.tapBtnNext()

killSwitchPage.verifyLblNoActiveCreditCardMessageExist()

settingPage.tapBtnBack()

commonPage.delay(GlobalVariable.G_timeoutMiniShort)

settingPage.tapBtnBack()

'Tap on Deactivate Accounts'
killSwitchPage.tapLblDeactivateAccounts()

killSwitchPage.tapBtnNext()

killSwitchPage.verifyLblNoActiveAccountExist()

settingPage.tapBtnBack()

commonPage.delay(GlobalVariable.G_timeoutMiniShort)

settingPage.tapBtnBack()

'Tap on Deactivate M2U Access'
killSwitchPage.tapLblDeactivateM2UAccess()

killSwitchPage.tapBtnDeactivateNow()

killSwitchPage.tapBtnCancel()

settingPage.tapBtnBack()
commonPage.delay(GlobalVariable.G_timeoutMiniShort)

settingPage.tapBtnBack()
commonPage.delay(GlobalVariable.G_timeoutMiniShort)

'Tap on Secure2U item'
securityPage.tapLblSecure2u()

'Verify Notification popup is displayed & then tap on Cancel button'
securityPage.verifyLblDisableSecure2uExist()

securityPage.tapBtnConfirm()

settingPage.tapBtnBack()
commonPage.delay(GlobalVariable.G_timeoutMiniShort)
settingPage.tapBtnBack()