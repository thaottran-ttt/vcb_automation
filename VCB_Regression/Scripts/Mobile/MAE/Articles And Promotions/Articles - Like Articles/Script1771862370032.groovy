import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase

import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.util.KeywordUtil
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

'Select Articles from More (View all)'
homePage.selectQuickActions(action)

'View and access first item Fab Reads section'
commonPage.verifyElementVisible(commonPage.lblAnyText("Fab Reads"), GlobalVariable.G_timeoutMedium, FailureHandling.STOP_ON_FAILURE)

def featuredItem = articlesAndPromotionsPage.lblTitleBySection("Fab Reads", 1)

def featuredItemTitle = Mobile.getText(featuredItem, GlobalVariable.G_timeoutMedium)
commonPage.tap(featuredItem, GlobalVariable.G_timeoutMedium, FailureHandling.STOP_ON_FAILURE)
Mobile.comment("Fab Reads item title: " + featuredItemTitle)
commonPage.verifyElementVisible(commonPage.lblAnyText(featuredItemTitle), GlobalVariable.G_timeoutMedium, FailureHandling.STOP_ON_FAILURE)

Mobile.delay(GlobalVariable.G_timeoutMiniShort)
commonPage.tapBack()

'View and access first article from What\'s News section'
Mobile.swipe(0, 500, 0, 0)
commonPage.verifyElementVisible(commonPage.lblAnyText("What's New"), GlobalVariable.G_timeoutMedium, FailureHandling.STOP_ON_FAILURE)

def firstNewArticlesTitle = Mobile.getText(articlesAndPromotionsPage.lblNewsLikeByIndex(1), GlobalVariable.G_timeoutMedium)
def firstNewArticlesLikeBtn = Mobile.getText(articlesAndPromotionsPage.lblNewsLikeByIndex(2), GlobalVariable.G_timeoutMedium)
Mobile.comment("firstNewArticlesTitle: " + firstNewArticlesTitle)
Mobile.comment("firstNewArticlesLikeBtn: " + firstNewArticlesLikeBtn)

commonPage.tap(articlesAndPromotionsPage.lblNewsLikeByIndex(1), GlobalVariable.G_timeoutMedium, FailureHandling.STOP_ON_FAILURE)
Mobile.delay(GlobalVariable.G_timeoutMiniShort)
commonPage.verifyElementVisible(commonPage.lblAnyText(firstNewArticlesTitle), GlobalVariable.G_timeoutMedium, FailureHandling.STOP_ON_FAILURE)

commonPage.tap(commonPage.lblAnyText(firstNewArticlesLikeBtn), GlobalVariable.G_timeoutMedium, FailureHandling.STOP_ON_FAILURE)
Mobile.delay(GlobalVariable.G_timeoutMiniShort)

def updateLikeNumber = Mobile.getText(commonPage.lblTextNo(1), GlobalVariable.G_timeoutMedium)

int oldLikeNumber = firstNewArticlesLikeBtn.replaceAll("[^0-9]", "").toInteger()
int newLikeNumber = updateLikeNumber.replaceAll("[^0-9]", "").toInteger()

Mobile.comment("oldLikeNumber: " + oldLikeNumber)
Mobile.comment("newLikeNumber: " + newLikeNumber)

boolean isMatched = Mobile.verifyEqual(oldLikeNumber + 1, newLikeNumber)

if (isMatched) {
	Mobile.comment("Verify balance: " + isMatched)
} else {
	Mobile.takeScreenshot()
	KeywordUtil.markFailed("Balance mismatch, Expected: ${oldLikeNumber + 1} | Actual: ${newLikeNumber}")
}

commonPage.tapBack()
Mobile.delay(GlobalVariable.G_timeoutMiniShort)

'Search and Access selected promotions'
articlesAndPromotionsPage.searchAndAccessPromotions(searchKeyword)
Mobile.delay(GlobalVariable.G_timeoutMiniShort)

commonPage.tapBack()
commonPage.backToHome()
