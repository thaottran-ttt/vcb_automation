import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase

import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.vcb.mobile.internal.pages.base.BasePage
import com.vcb.mobile.internal.pages.base.HomePage
import com.vcb.mobile.internal.pages.base.kliaexpresspage.KLIAExpressPage
import com.vcb.mobile.internal.pages.base.Secure2uAuthorisationPage
import com.vcb.utils.MapUtils

import internal.GlobalVariable as GlobalVariable

WebUI.callTestCase(findTestCase('Mobile/VCB/Common/Login Secure2u'), [('idNumber') : idNumber, ('PAN') : PAN, ('password') : GlobalVariable.G_password
        , ('username') : userName, ('accountName') : accountname, ('flowControl') : com.vcb.common.FlowControlEnum.STOP, ('failStopTestSuite') : false
        , ('yourName') : 'Test'], FailureHandling.STOP_ON_FAILURE)


HomePage homePage = BasePage.getPage(HomePage)
KLIAExpressPage kliaExpress = KLIAExpressPage.getPage(KLIAExpressPage)
Secure2uAuthorisationPage s2u = Secure2uAuthorisationPage.getPage(Secure2uAuthorisationPage)
Map ticketData = MapUtils.parseObjectToMap(ticketData)

'Select ERL Tickets Module at QuickActions'
homePage.selectQuickActions(action)

'Buy Ticket'
kliaExpress.buyTicket(ticketData)

'Authorize S2u'
s2u.authorize(status)

'Verify Status Screen'
kliaExpress.verifyTransaction(status)

