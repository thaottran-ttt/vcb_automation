import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.vcb.common.MobileDriver
import com.vcb.mobile.internal.pages.base.BasePage as BasePage
import com.vcb.mobile.internal.pages.base.HomePage
import com.vcb.mobile.internal.pages.base.NotificationHomePage
import com.vcb.mobile.internal.pages.base.NotificationSettingPage as NotificationSettingPage
import com.vcb.mobile.internal.pages.base.settingpage.SettingPage as SettingPage
import internal.GlobalVariable as GlobalVariable

HomePage homePage = BasePage.getPage(HomePage)
NotificationHomePage notificationHomePage = BasePage.getPage(NotificationHomePage)
WebUI.callTestCase(findTestCase('Mobile/VCB/Common/Login'), [('appId') : GlobalVariable.G_appPath, ('username') : username
        , ('password') : password, ('pinCode') : '111111', ('yourName') : 'Test'], FailureHandling.STOP_ON_FAILURE)
MobileDriver.delay(GlobalVariable.G_timeoutShort)

'Tap bell Icon'
homePage.tapNotification()

'Verify Receive Transfer Notfication'
def result = notificationHomePage.verifyNotification(title, startMessage, message)


