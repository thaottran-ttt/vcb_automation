import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.maybank.mae.internal.pages.base.BasePage as BasePage
import com.maybank.mae.internal.pages.base.HomePage as HomePage
import com.maybank.mae.internal.pages.base.NotificationSettingPage as NotificationSettingPage
import com.maybank.mae.internal.pages.base.settingpage.SettingPage as SettingPage
import internal.GlobalVariable as GlobalVariable

HomePage homePage = BasePage.getPage(HomePage)

NotificationSettingPage notificationSettingPage = BasePage.getPage(NotificationSettingPage)

SettingPage settingPage = BasePage.getPage(SettingPage)

WebUI.callTestCase(findTestCase('Mobile/MAE/Common/Login'), [('appId') : GlobalVariable.G_appPath, ('username') : username
        , ('password') : password, ('pinCode') : '111111', ('yourName') : 'Test'], FailureHandling.STOP_ON_FAILURE)

'Click on Setting button'
homePage.tapBtnSetting()

'Click on Notification'
settingPage.tapLblNotifications()

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

'Click on Promotions toggle to turn OFF TapTrackWinNotifications'
notificationSettingPage.tapToggleTapTrackWinNotifications()

'Verify toast message when turn ON TrackWinNotifications'
notificationSettingPage.veriyTapTrackWinNotificationsToastMessageON()

'Click on Promotions toggle to turn ON TapTrackWinNotifications'
notificationSettingPage.tapToggleTapTrackWinNotifications()

'Verify toast message when turn ON TrackWinNotifications'
notificationSettingPage.verifyTapTrackWinNotificationsToastMessageOFF()