import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase

import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.maybank.mae.internal.pages.base.BasePage
import com.maybank.mae.internal.pages.base.HomePage
import com.maybank.mae.internal.pages.base.moviesandleisure.MoviesAndLeisurePage
import com.maybank.mae.internal.pages.base.PushNotificationPage as PushNotificationPage
import com.maybank.mae.internal.pages.base.CommonPage
import com.maybank.mae.internal.pages.base.StatusScreenPage
import com.maybank.mae.internal.pages.base.Secure2uAuthorisationPage
import com.maybank.utils.MapUtils

import internal.GlobalVariable as GlobalVariable

WebUI.callTestCase(findTestCase('Mobile/MAE/Common/Login Secure2u'), [('idNumber') : idNumber, ('PAN') : PAN, ('password') : GlobalVariable.G_password
        , ('username') : userName, ('accountName') : accountname, ('flowControl') : com.maybank.common.FlowControlEnum.STOP, ('failStopTestSuite') : false
        , ('yourName') : 'Test'], FailureHandling.STOP_ON_FAILURE)

CommonPage commonPage = BasePage.getPage(CommonPage)

HomePage homePage = BasePage.getPage(HomePage)

MoviesAndLeisurePage moviesandleisure = MoviesAndLeisurePage.getPage(MoviesAndLeisurePage)

Secure2uAuthorisationPage s2u = Secure2uAuthorisationPage.getPage(Secure2uAuthorisationPage)

StatusScreenPage statusScreenPage = BasePage.getPage(StatusScreenPage)

'Select ERL Tickets Module at QuickActions'
homePage.selectQuickActions(action)

moviesandleisure.selectWetixTab("Movies")
List<String> ticketData = moviesandleisure.buyTicket(accountname)

'Authorize S2u'
s2u.authorize(status)

'Verify Status Screen'
String RefID = statusScreenPage.verifyStatusScreen(statusTitle, statusDescription)

//'Verify ticket information'
//moviesandleisure.verifyTicketInformation(ticketData)
Mobile.waitForElementPresent(commonPage.lblDynamic("Done"), GlobalVariable.G_timeoutLong, FailureHandling.OPTIONAL)
Mobile.tap(commonPage.lblDynamic("Done"), GlobalVariable.G_timeoutLong, FailureHandling.STOP_ON_FAILURE)


