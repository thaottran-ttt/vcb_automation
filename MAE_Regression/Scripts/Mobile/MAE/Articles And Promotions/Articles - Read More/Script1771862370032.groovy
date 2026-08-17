import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase

import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
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

'Scroll to Top Reads'
Mobile.swipe(0, 1200, 0, 0)
Mobile.swipe(0, 1200, 0, 0)
Mobile.swipe(0, 1200, 0, 0)
Mobile.swipe(0, 1200, 0, 0)
Mobile.swipe(0, 500, 0, 0)
Mobile.swipe(0, 500, 0, 0)

commonPage.tap(commonPage.lblContainDynamic("View Mor"), GlobalVariable.G_timeoutMedium, FailureHandling.STOP_ON_FAILURE)
Mobile.delay(GlobalVariable.G_timeoutMiniShort)
'Verify access Articles'
commonPage.verifyElementVisible(commonPage.lblAnyText("Articles"), GlobalVariable.G_timeoutMedium, FailureHandling.STOP_ON_FAILURE)

'View and access first item Fab Reads section'
commonPage.verifyElementVisible(commonPage.lblAnyText("Fab Reads"), GlobalVariable.G_timeoutMedium, FailureHandling.STOP_ON_FAILURE)

def featuredItem = articlesAndPromotionsPage.lblTitleBySection("Fab Reads", 1)

def featuredItemTitle = Mobile.getText(featuredItem, GlobalVariable.G_timeoutMedium)
commonPage.tap(featuredItem, GlobalVariable.G_timeoutMedium, FailureHandling.STOP_ON_FAILURE)
Mobile.comment("Fab Reads item title: " + featuredItemTitle)
commonPage.verifyElementVisible(commonPage.lblAnyText(featuredItemTitle), GlobalVariable.G_timeoutMedium, FailureHandling.STOP_ON_FAILURE)

Mobile.delay(GlobalVariable.G_timeoutMiniShort)

commonPage.tapBack()
commonPage.backToHome()