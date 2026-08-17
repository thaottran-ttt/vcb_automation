import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase

import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.maybank.common.MobileDriver
import com.maybank.mae.internal.pages.base.BasePage
import com.maybank.mae.internal.pages.base.CommonPage
import com.maybank.mae.internal.pages.base.HomePage
import com.maybank.mae.internal.pages.base.loyaltypage.LoyaltyPage
import com.maybank.utils.MapUtils

import internal.GlobalVariable as GlobalVariable

CommonPage commonPage = BasePage.getPage(CommonPage)

'Init HomePage'
HomePage homePage = BasePage.getPage(HomePage)

'Init Loyalty Page'
LoyaltyPage loyaltyPage = BasePage.getPage(LoyaltyPage)

'Init Map Data for loyalty card'
Map dataCardEdit =  MapUtils.parseObjectToMap(dataCardEdit)
Map dataCardNew =  MapUtils.parseObjectToMap(dataCardNew)

WebUI.callTestCase(findTestCase('Mobile/MAE/Common/Login'), [('username') : userName, ('password') : password
        , ('pinCode') : GlobalVariable.G_pinCode, ('yourName') : GlobalVariable.G_yourName, ('accountName') : '', ('flowControl') : com.maybank.common.FlowControlEnum.STOP
        , ('failStopTestSuite') : false], FailureHandling.STOP_ON_FAILURE)

'Navigate to View All Screen'
homePage.selectQuickActions(action)

'Navigate to Loyalty Screen'
homePage.selectQuickActions(subAction)
commonPage.tap(commonPage.lblDynamic(loyaltyTab), GlobalVariable.G_timeoutMedium)

'Verify add loyalty card page'
loyaltyPage.verifyPage()

'Add new loyalty card'
loyaltyPage.addCard(dataCardNew)

'Verify Loyalty Card Added'
loyaltyPage.verifyAddedCard(dataCardNew)

'Modify Loyalty card by edit function'
loyaltyPage.editLoyaltyCard(edit, dataCardEdit)

'Verify Loyalty Card Edited'
loyaltyPage.verifyEditedCard(dataCardEdit)

'Modify Loyalty card by remove function'
loyaltyPage.removeCard(remove, dataCardEdit)