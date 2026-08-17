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

'Select Promotion from More (View all)'
homePage.selectQuickActions(action)

'View and access first item Featured promotions section'
commonPage.verifyElementVisible(commonPage.lblAnyText("Featured"), GlobalVariable.G_timeoutMedium, FailureHandling.STOP_ON_FAILURE)

def featuredItem = articlesAndPromotionsPage.lblTitleBySection("Featured", 1)

def featuredItemTitle = Mobile.getText(featuredItem, GlobalVariable.G_timeoutMedium)
commonPage.tap(featuredItem, GlobalVariable.G_timeoutMedium, FailureHandling.STOP_ON_FAILURE)
Mobile.comment("Feature item title: " + featuredItemTitle)
commonPage.verifyElementVisible(commonPage.lblAnyText(featuredItemTitle), GlobalVariable.G_timeoutMedium, FailureHandling.STOP_ON_FAILURE)

commonPage.tapBack()

'View and access first item from Lastest promotions section'
commonPage.verifyElementVisible(commonPage.lblAnyText("Latest"), GlobalVariable.G_timeoutMedium, FailureHandling.STOP_ON_FAILURE)

def latestItem = articlesAndPromotionsPage.lblTitleLatestSection(1)

def latestItemTitle = Mobile.getText(latestItem, GlobalVariable.G_timeoutMedium)
commonPage.tap(featuredItem, GlobalVariable.G_timeoutMedium, FailureHandling.STOP_ON_FAILURE)
Mobile.comment("Latest item title: " + latestItemTitle)
commonPage.verifyElementVisible(commonPage.lblAnyText(latestItemTitle), GlobalVariable.G_timeoutMedium, FailureHandling.STOP_ON_FAILURE)

commonPage.tapBack()
Mobile.delay(GlobalVariable.G_timeoutMiniShort)

'Search and Access selected promotions'
articlesAndPromotionsPage.searchAndAccessPromotions(searchKeyword)
Mobile.delay(GlobalVariable.G_timeoutMiniShort)

commonPage.tapBack()
commonPage.backToHome()

