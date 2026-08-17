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

'At DISCOVER, tap View All'
commonPage.tap(commonPage.lblAnyText("View All"), GlobalVariable.G_timeoutMedium, FailureHandling.STOP_ON_FAILURE)
Mobile.delay(GlobalVariable.G_timeoutMiniShort)
Mobile.waitForElementPresent(commonPage.lblAnyText("What are you craving?"), GlobalVariable.G_timeoutMedium, FailureHandling.OPTIONAL)
commonPage.verifyElementVisible(commonPage.lblAnyText("What are you craving?"), GlobalVariable.G_timeoutMedium, FailureHandling.STOP_ON_FAILURE)

List<List<String>> shops = [
	['Fast Food', 'TestNina', 'Hawker', 'Coffee & Tea', 'Dessert/Bakery',
		'Asian', 'Beverages', 'Bubble Tea', 'Burgers', 'Cakes', 'Chocolate',
		'Cookies', 'Dim Sum', 'Durianss', 'Fast Food'
	], 
	['Fruits Snacks', 'Healthy', 'Hot Pot', 'Ice Cream', 'Indian',
		'Indonesian', 'Italian', 'Japanese', 'Juice & Smoothies',
		'Korean', 'Malay', 'Mamak', 'Middle Eastern', 'Other Cuisines', 'Seafood'
	], 
	[
		'Snacks', 'Taiwanese', 'Thai', 'Vegetarian', 'Vietnamese', 'Western'
	]
]

for (int i = 0; i < shops.size(); i++) {

    if (i > 0) {
        commonPage.scrollToTestObject(
                homePage.fraScrollQuickAction(),
                commonPage.lblAnyText(shops[i].get(0)),
                MobileDriver.ScrollDirectionEnum.RIGHT,
                GlobalVariable.G_timeoutMedium,
                FailureHandling.STOP_ON_FAILURE
        )
    }

    shops[i].each { item ->
        commonPage.verifyElementExist(
                commonPage.lblDynamic(item),
                GlobalVariable.G_timeoutShort,
                FailureHandling.STOP_ON_FAILURE
        )
    }
}

