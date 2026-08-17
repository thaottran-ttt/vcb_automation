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
import com.vcb.mobile.internal.pages.base.BasePage as BasePage
import com.vcb.mobile.internal.pages.base.CommonPage as CommonPage
import com.vcb.mobile.internal.pages.base.HomePage as HomePage
import com.vcb.mobile.internal.pages.base.Secure2uAuthorisationPage as Secure2uAuthorisationPage
import com.vcb.mobile.internal.pages.base.settingpage.securitypage.ChangePasswordPage as ChangePasswordPage
import com.vcb.mobile.internal.pages.base.settingpage.securitypage.SecurityPage as SecurityPage
import com.vcb.mobile.internal.pages.base.settingpage.SettingPage as SettingPage
import com.vcb.mobile.internal.pages.base.StatusScreenPage as StatusScreenPage
import com.vcb.mobile.internal.pages.base.accountpage.KillSwitchPage as KillSwitchPage
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

HomePage homePage = HomePage.getPage(HomePage)

CommonPage commonPage = BasePage.getPage(CommonPage)

Secure2uAuthorisationPage secure2uAuthorisationPage = BasePage.getPage(Secure2uAuthorisationPage)

ChangePasswordPage changePasswordPage = BasePage.getPage(ChangePasswordPage)

SettingPage settingPage = BasePage.getPage(SettingPage)

SecurityPage securityPage = BasePage.getPage(SecurityPage)

StatusScreenPage statusScreenPage = BasePage.getPage(StatusScreenPage)

KillSwitchPage killSwitchPage = BasePage.getPage(KillSwitchPage)

if (secure2u) {
    WebUI.callTestCase(findTestCase('Mobile/VCB/Common/Login Secure2u'), [('idNumber') : idNumber, ('PAN') : PAN, ('password') : password
            , ('username') : username, ('accountName') : accountName, ('flowControl') : com.vcb.common.FlowControlEnum.STOP
            , ('failStopTestSuite') : false, ('yourName') : yourName], FailureHandling.STOP_ON_FAILURE)
} else {
    WebUI.callTestCase(findTestCase('Mobile/VCB/Common/Login'), [('appId') : GlobalVariable.G_appPath, ('username') : username
            , ('password') : password, ('pinCode') : '111111', ('yourName') : yourName, ('accountName') : accountName, ('skipAccountLoginCheck') : false], 
        FailureHandling.STOP_ON_FAILURE)
}

if (secure2u) {
    'Tab on Setting on Home page'
    homePage.tapBtnSetting()

    settingPage.tapLblVcb2u()

    commonPage.delay(GlobalVariable.G_timeoutMiniShort)

    'Tap on Change Password then change passwords which is same current password. System returns change password unsuccessfully'
    securityPage.tapLblChangePassword()

    changePasswordPage.changePassword(password, password, password)

    commonPage.waitLoading()

    secure2uAuthorisationPage.authorize(status)

    commonPage.waitLoading()

    statusScreenPage.verifyStatusScreen(statusTitle, statusDescription)

    commonPage.tapDoneBtn()

    commonPage.delay(GlobalVariable.G_timeoutMiniShort)

    settingPage.tapBtnBack()
}

'Go to Kill Switch page'
homePage.selectQuickActionKillSwitch()

'Tap on Block Credit Card'
killSwitchPage.tapLblBlockCreditCard(GlobalVariable.G_timeoutLong)

killSwitchPage.tapBtnNext()

killSwitchPage.verifyLblNoActiveCreditCardMessageExist()

settingPage.tapBtnBack()

commonPage.delay(GlobalVariable.G_timeoutMiniShort)

settingPage.tapBtnBack()

'Tap on Deactivate M2U Access'
killSwitchPage.tapLblDeactivateM2UAccess(GlobalVariable.G_timeoutLong)

killSwitchPage.tapBtnDeactivateNow()

killSwitchPage.tapBtnCancel()

settingPage.tapBtnBack()

commonPage.delay(GlobalVariable.G_timeoutMiniShort)

'Tap on Deactivate Accounts'
killSwitchPage.tapLblDeactivateAccounts(GlobalVariable.G_timeoutLong)

commonPage.delay(GlobalVariable.G_timeoutMiniShort)

killSwitchPage.tapBtnNext()

commonPage.delay(GlobalVariable.G_timeoutMiniShort)

settingPage.tapBtnBack()

commonPage.delay(GlobalVariable.G_timeoutMiniShort)

settingPage.tapBtnBack()

commonPage.delay(GlobalVariable.G_timeoutMiniShort)

settingPage.tapBtnBack()

