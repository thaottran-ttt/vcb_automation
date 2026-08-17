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
Mobile.swipe(0, 800, 0, 0)
Mobile.swipe(0, 500, 0, 0)

'View and Access the first 2 articles'
for (int index = 1; index < 3; index++) {
	def firstArticles = articlesAndPromotionsPage.lblArticlesTitleBySection("Top Reads", index)

	def firstArticlesItemTitle = Mobile.getText(firstArticles, GlobalVariable.G_timeoutMedium)
	commonPage.tap(firstArticles, GlobalVariable.G_timeoutMedium, FailureHandling.STOP_ON_FAILURE)
	Mobile.comment("Articles item ${index} title: " + firstArticlesItemTitle)
	Mobile.delay(GlobalVariable.G_timeoutMiniShort)
	commonPage.verifyElementVisible(commonPage.lblContainDynamic(firstArticlesItemTitle), GlobalVariable.G_timeoutMedium, FailureHandling.STOP_ON_FAILURE)
	
	commonPage.tapBack()
}

Mobile.delay(GlobalVariable.G_timeoutMiniShort)

commonPage.tapBack()
commonPage.backToHome()

