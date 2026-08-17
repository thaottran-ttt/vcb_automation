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
import com.vcb.mobile.internal.pages.base.CommonPage.TestObjectTypeEnum
import com.vcb.mobile.internal.pages.base.PushNotificationPage
import com.vcb.mobile.internal.pages.base.scanpaypage.ScanPayPage
import com.vcb.mobile.internal.pages.base.settingpage.SettingPage

import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

CommonPage commonPage = BasePage.getPage(CommonPage)
SettingPage settingPage = BasePage.getPage(SettingPage)
ScanPayPage scanPayPage = BasePage.getPage(ScanPayPage)
PushNotificationPage pushNotificationPage = BasePage.getPage(PushNotificationPage)

WebUI.callTestCase(findTestCase('Mobile/VCB/Tango/Common/Entry Point'), [('username') : username, ('password') : password
	, ('idNumber') : idNumber, ('PAN') : PAN], FailureHandling.STOP_ON_FAILURE)


'M2U password verification required before user can access Pay & Transfer settings.'
settingPage.goToSettingsWithPasswordVerification(tabName, password)

'Go to DuitNow Services'
settingPage.goToDuiNowServicesSetting()
commonPage.waitLoading(GlobalVariable.G_timeoutShort)

'Verify first time DuitNow ID registration'
settingPage.successfulMessage = registerMessage
settingPage.verifyRegisterDuitNowFirstTime(registerAccountName)
commonPage.waitLoading(GlobalVariable.G_timeoutShort)

'Verify notification'
pushNotificationPage.TapAndVerifyPushNotification(notiTitle, notiMsg, FailureHandling.STOP_ON_FAILURE, TestObjectTypeEnum.CONTAINS)

'Go to DuitNow Services'
MobileDriver.tap(commonPage.lblDynamic(tabName), GlobalVariable.G_timeoutMedium, FailureHandling.STOP_ON_FAILURE)
settingPage.goToDuiNowServicesSetting()
commonPage.waitLoading(GlobalVariable.G_timeoutShort)

'Verify switch account'
settingPage.verifySwitchAccount(switchAccountName)
commonPage.waitLoading(GlobalVariable.G_timeoutShort)

'Verify disable temporarily'
settingPage.verifyDisableTemporarily()
commonPage.waitLoading(GlobalVariable.G_timeoutShort)

'Verify DuitNow AutoDebit'
settingPage.verifyDuitNowAutoDebit()

'Verify deregister existing Duitnow'
settingPage.deregisterMessage = removeMessage
settingPage.verifyDeregisterExistingDuitNow()
commonPage.waitLoading(GlobalVariable.G_timeoutShort)

'Verify notification'
pushNotificationPage.TapAndVerifyPushNotification(notiDeregisterTitle, notiDeregisterMsg, FailureHandling.STOP_ON_FAILURE, TestObjectTypeEnum.CONTAINS)

