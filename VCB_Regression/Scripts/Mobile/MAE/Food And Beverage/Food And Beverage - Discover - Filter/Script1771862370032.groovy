import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase

import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.maybank.common.MobileDriver
import com.maybank.mae.internal.pages.base.BasePage
import com.maybank.mae.internal.pages.base.HomePage
import com.maybank.mae.internal.pages.base.PushNotificationPage as PushNotificationPage
import com.maybank.mae.internal.pages.base.CommonPage
import com.maybank.mae.internal.pages.base.StatusScreenPage
import com.maybank.mae.internal.pages.base.Secure2uAuthorisationPage
import com.maybank.mae.internal.pages.base.articlesandpromotionspage.ArticlesAndPromotionsPage
import com.maybank.utils.MapUtils

import internal.GlobalVariable as GlobalVariable

WebUI.callTestCase(findTestCase('Mobile/MAE/Common/Login'), [('idNumber') : idNumber, ('PAN') : PAN, ('password') : password
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

'At DISCOVER, tap View All'
commonPage.tap(commonPage.lblAnyText("View All"), GlobalVariable.G_timeoutMedium, FailureHandling.STOP_ON_FAILURE)
Mobile.delay(GlobalVariable.G_timeoutMiniShort)
Mobile.waitForElementPresent(commonPage.lblAnyText("What are you craving?"), GlobalVariable.G_timeoutMedium, FailureHandling.OPTIONAL)
commonPage.verifyElementVisible(commonPage.lblAnyText("What are you craving?"), GlobalVariable.G_timeoutMedium, FailureHandling.STOP_ON_FAILURE)

'Browse merchant to apply Filter'
commonPage.tap(commonPage.lblAnyText(merchant), GlobalVariable.G_timeoutMedium, FailureHandling.STOP_ON_FAILURE)
Mobile.delay(GlobalVariable.G_timeoutMiniShort)
Mobile.waitForElementPresent(commonPage.lblAnyText(merchant), GlobalVariable.G_timeoutMedium, FailureHandling.OPTIONAL)
commonPage.verifyElementVisible(commonPage.lblAnyText(merchant), GlobalVariable.G_timeoutMedium, FailureHandling.STOP_ON_FAILURE)

if (!commonPage.waitForElementPresent(commonPage.lblAnyText('No listed Merchants available'), GlobalVariable.G_timeoutMedium, FailureHandling.OPTIONAL)) {
	'Tap Filter button'
	commonPage.tap(commonPage.imgNoDynamic(4), GlobalVariable.G_timeoutMedium, FailureHandling.STOP_ON_FAILURE)
	Mobile.delay(GlobalVariable.G_timeoutMiniShort)
	
	commonPage.scrollToTextDefault("Price Range")
	commonPage.tap(commonPage.lblAnyText('$$'), GlobalVariable.G_timeoutMedium, FailureHandling.STOP_ON_FAILURE)
	commonPage.tap(commonPage.lblAnyText('Apply Filters'), GlobalVariable.G_timeoutMedium, FailureHandling.STOP_ON_FAILURE)
	
	Mobile.delay(GlobalVariable.G_timeoutMiniShort)
	commonPage.verifyElementExist(commonPage.lblAnyText('$$'), GlobalVariable.G_timeoutMedium, FailureHandling.STOP_ON_FAILURE)
	
}

Mobile.delay(GlobalVariable.G_timeoutMiniShort)
commonPage.tapBack()

Mobile.delay(GlobalVariable.G_timeoutMiniShort)
commonPage.tap(commonPage.imgNoDynamic(2), GlobalVariable.G_timeoutMedium, FailureHandling.STOP_ON_FAILURE)

Mobile.delay(GlobalVariable.G_timeoutMiniShort)
commonPage.tap(commonPage.imgNoDynamic(1), GlobalVariable.G_timeoutMedium, FailureHandling.STOP_ON_FAILURE)

commonPage.backToHome()