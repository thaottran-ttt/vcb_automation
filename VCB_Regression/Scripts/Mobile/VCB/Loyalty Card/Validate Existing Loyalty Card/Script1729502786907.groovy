import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase

import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.vcb.mobile.internal.pages.base.BasePage
import com.vcb.mobile.internal.pages.base.HomePage
import com.vcb.mobile.internal.pages.base.CommonPage
import com.vcb.mobile.internal.pages.base.loyaltypage.LoyaltyPage
import com.vcb.utils.MapUtils

import internal.GlobalVariable as GlobalVariable

WebUI.callTestCase(findTestCase('Mobile/VCB/Common/Login'), [('username') : userName, ('password') : password
        , ('pinCode') : GlobalVariable.G_pinCode, ('yourName') : GlobalVariable.G_yourName, ('accountName') : '', ('flowControl') : com.vcb.common.FlowControlEnum.STOP
        , ('failStopTestSuite') : false], FailureHandling.STOP_ON_FAILURE)

CommonPage commonPage = BasePage.getPage(CommonPage)

'Init HomePage'
HomePage homePage = BasePage.getPage(HomePage)

'Init LoyaltyPage'
LoyaltyPage loyaltyPage = BasePage.getPage(LoyaltyPage)

'Init Map Data for loyalty card'
Map dataCard =  MapUtils.parseObjectToMap(dataCard)

'Navigate to View All Screen'
homePage.selectQuickActions(action)

'Navigate to Loyalty Screen'
homePage.selectQuickActions(subAction)
commonPage.tap(commonPage.lblDynamic(loyaltyTab), GlobalVariable.G_timeoutMedium)

'Verify add loyalty card page'
loyaltyPage.verifyPage()

'Add new loyalty card'
loyaltyPage.addCard(dataCard)

'Add new loyalty card'
loyaltyPage.addCard(dataCard)