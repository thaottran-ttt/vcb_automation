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
import com.vcb.mobile.internal.pages.base.NotificationSettingPage
import com.vcb.mobile.internal.pages.base.settingpage.SettingPage
import com.vcb.utils.ListUtils

import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

CommonPage commonPage = BasePage.getPage(CommonPage)
SettingPage settingPage = BasePage.getPage(SettingPage)
NotificationSettingPage notificationSettingPage = BasePage.getPage(NotificationSettingPage)

notificationUIData = ListUtils.parseObjectToList(notificationUIData)

WebUI.callTestCase(findTestCase('Mobile/VCB/Tango/Common/Entry Point'), [('username') : username, ('password') : password
	, ('idNumber') : idNumber, ('PAN') : PAN], FailureHandling.STOP_ON_FAILURE)

'Tap Notifications'
settingPage.tapNotifications()

commonPage.delay(GlobalVariable.G_timeoutMiniShort)

'Verify Notifications setting page'
commonPage.verifyElements(notificationUIData, commonPage.lblDynamicPath(), GlobalVariable.G_timeoutLong)

'Click on Promotions toggle to turn OFF promotions'
notificationSettingPage.tapTogglePromotions()

'Verify toast message when turn OFF promotions'
notificationSettingPage.verifyPromotionsToastMessageOFF()

'Click on Promotions toggle to turn ON promotions'
notificationSettingPage.tapTogglePromotions()

'Verify toast message when turn ON promotions'
notificationSettingPage.verifyPromotionsToastMessageON()

'Click on Promotions toggle to turn OFF MoneyReceived'
notificationSettingPage.tapToggleMoneyReceived()

'Verify toast message when turn ON MoneyReceived'
notificationSettingPage.verifyMoneyReceivedToastMessageOFF()

'Click on Promotions toggle to turn ON MoneyReceived'
notificationSettingPage.tapToggleMoneyReceived()

'Verify toast message when turn ON MoneyReceived'
notificationSettingPage.verifyMoneyReceivedToastMessageON()

'Click on Promotions toggle to turn ON TapTrackWinNotifications'
notificationSettingPage.tapToggleTapTrackWinNotifications()

'Verify toast message when turn ON TrackWinNotifications'
notificationSettingPage.veriyTapTrackWinNotificationsToastMessageON()

'Click on Promotions toggle to turn OFF TapTrackWinNotifications'
notificationSettingPage.tapToggleTapTrackWinNotifications()

'Verify toast message when turn OFF TrackWinNotifications'
notificationSettingPage.verifyTapTrackWinNotificationsToastMessageOFF()

'Tap back btn'
MobileDriver.tap(commonPage.imgNoDynamic(1), GlobalVariable.G_timeoutLong)
