import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase

import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.vcb.common.MobileDriver
import com.vcb.mobile.internal.pages.base.BasePage
import com.vcb.mobile.internal.pages.base.HomePage
import com.vcb.mobile.internal.pages.base.PushNotificationPage as PushNotificationPage
import com.vcb.mobile.internal.pages.base.CommonPage
import com.vcb.mobile.internal.pages.base.StatusScreenPage
import com.vcb.mobile.internal.pages.base.Secure2uAuthorisationPage
import com.vcb.mobile.internal.pages.base.articlesandpromotionspage.ArticlesAndPromotionsPage
import com.vcb.utils.MapUtils

import internal.GlobalVariable as GlobalVariable

WebUI.callTestCase(findTestCase('Mobile/VCB/Common/Login'), [('idNumber') : idNumber, ('PAN') : PAN, ('password') : password
	, ('username') : username, ('accountName') : accountName, ('yourName') : 'Test'], FailureHandling.STOP_ON_FAILURE)

CommonPage commonPage = BasePage.getPage(CommonPage)

HomePage homePage = BasePage.getPage(HomePage)

ArticlesAndPromotionsPage articlesAndPromotionsPage = BasePage.getPage(ArticlesAndPromotionsPage)

Secure2uAuthorisationPage s2u = Secure2uAuthorisationPage.getPage(Secure2uAuthorisationPage)

StatusScreenPage statusScreenPage = BasePage.getPage(StatusScreenPage)

'Select Promotion from More (View all)'
homePage.selectQuickActions(action)

'Verify Discover tab'
commonPage.verifyElementVisible(commonPage.lblAnyText("DISCOVER"), GlobalVariable.G_timeoutMedium, FailureHandling.STOP_ON_FAILURE)

'Verify MAKAN MANA? tab'
commonPage.verifyElementVisible(commonPage.lblAnyText("MAKAN MANA?"), GlobalVariable.G_timeoutMedium, FailureHandling.STOP_ON_FAILURE)

'Select MAKAN MANA tab'
commonPage.tap(commonPage.lblAnyText("MAKAN MANA?"), GlobalVariable.G_timeoutMedium, FailureHandling.STOP_ON_FAILURE)

'Tap spin button'
commonPage.waitForElementPresent(commonPage.imgNoDynamic(8), GlobalVariable.G_timeoutMedium, FailureHandling.OPTIONAL)
commonPage.tap(commonPage.imgNoDynamic(8), GlobalVariable.G_timeoutMedium, FailureHandling.STOP_ON_FAILURE)
Mobile.delay(GlobalVariable.G_timeoutMedium)

def spinPopupText = Mobile.getText(commonPage.lblTextNo(1), GlobalVariable.G_timeoutMedium, FailureHandling.STOP_ON_FAILURE)
if (commonPage.waitForElementPresent(commonPage.lblAnyText('Promotion'), GlobalVariable.G_timeoutMedium, FailureHandling.OPTIONAL)) {
	spinPopupText = Mobile.getText(commonPage.lblTextNo(2), GlobalVariable.G_timeoutMedium, FailureHandling.STOP_ON_FAILURE)
}

Mobile.comment("spinPopupText: " + spinPopupText)

commonPage.waitForElementPresent(commonPage.lblAnyText('View More'), GlobalVariable.G_timeoutMedium, FailureHandling.OPTIONAL)
commonPage.tap(commonPage.lblAnyText('View More'), GlobalVariable.G_timeoutMedium, FailureHandling.STOP_ON_FAILURE)
Mobile.delay(GlobalVariable.G_timeoutShort)
commonPage.verifyElementVisible(commonPage.lblAnyText(spinPopupText), GlobalVariable.G_timeoutMedium, FailureHandling.STOP_ON_FAILURE)

Mobile.delay(GlobalVariable.G_timeoutShort)

commonPage.tapBack()
commonPage.backToHome()