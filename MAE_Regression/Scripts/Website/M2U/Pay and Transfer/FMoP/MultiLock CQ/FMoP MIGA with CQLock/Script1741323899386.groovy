import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase

import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.maybank.common.BaseKeyword
import com.maybank.common.WebUIKeyword
import com.maybank.m2u.internal.pages.base.HTMLTag
import com.maybank.m2u.internal.pages.base.WebBasePage
import com.maybank.m2u.internal.pages.base.WebCommonPage
import com.maybank.m2u.internal.pages.base.WebHomePage
import com.maybank.m2u.internal.pages.base.WebSecure2uAuthorisationPage
import com.maybank.m2u.internal.pages.base.WebStatusScreenPage
import com.maybank.m2u.internal.pages.base.accountpage.WebWealthPage
import com.maybank.utils.ListUtils
import com.maybank.utils.MapUtils

import internal.GlobalVariable as GlobalVariable

import com.maybank.m2u.internal.pages.base.rsapage.WebRsaPage
WebRsaPage webRsaPage = WebBasePage.getPage(WebRsaPage)

List statusScreenData = ListUtils.parseObjectToList(statusScreenData)
Map transferFrom = MapUtils.parseObjectToMap(transferFrom)

WebCommonPage webCommonPage = WebBasePage.getPage(WebCommonPage)
WebHomePage webHomePage = WebBasePage.getPage(WebHomePage)
WebWealthPage webWealthPage = WebBasePage.getPage(WebWealthPage)
WebStatusScreenPage webStatusScreenPage = WebBasePage.getPage(WebStatusScreenPage)
WebSecure2uAuthorisationPage webSecure2uAuthorisationPage = WebBasePage.getPage(WebSecure2uAuthorisationPage)
webSecure2uAuthorisationPage.setID_NUMBER(idNumber)

WebUI.callTestCase(findTestCase('Mobile/MAE/Common/Login Secure2u With Secure Question'), [('idNumber') : idNumber, ('PAN') : PAN
        , ('password') : GlobalVariable.G_password, ('username') : username, ('accountName') : '', ('flowControl') : com.maybank.common.FlowControlEnum.STOP
        , ('failStopTestSuite') : false, ('yourName') : GlobalVariable.G_yourName], BaseKeyword.defaultFailureHandling)

WebUI.callTestCase(findTestCase('Website/M2U/Common/Login'), [('username') : username, ('password') : password], BaseKeyword.defaultFailureHandling)

'Navigate To My Account'
webHomePage.navigate(navItem, tabName)

'Select Account'
WebUIKeyword.scrollToTestObject(webCommonPage.lblNormalizeSpace(accountType))
webCommonPage.clickTestObjectWithWait(webCommonPage.lblNormalizeSpace(accountType))

'Get Header info'
Map<String,String> headerValues = webWealthPage.getMIGAiHeaderValues()

'Click Buy Now'
WebUIKeyword.click(webCommonPage.lblIgnoreCase(isBuy? WebWealthPage.BUY_NOW: WebWealthPage.SELL_NOW, HTMLTag.button, 1))

'Wait loading popup'
webCommonPage.waitModelLoading(GlobalVariable.G_timeoutLong)

'Input gold'
webWealthPage.inputGram(goldGram)

'Calucate'
WebUIKeyword.click(webCommonPage.lblNormalizeSpace(webWealthPage.CALCULATE))

'Input Transfer From'
webCommonPage.inputData([:], transferFrom)

'Click Buy Now'
WebUIKeyword.click(webCommonPage.lblIgnoreCase(isBuy? WebWealthPage.BUY_NOW: WebWealthPage.SELL_NOW, HTMLTag.button, 'last()'))

'Verify S2U'
webSecure2uAuthorisationPage.authoriseWCqQuestion(status, securityanswer)

'Enter invalid Answer For Security Question '
webRsaPage.inputAnswer(securityanswer)
webRsaPage.confirmAnswer()

'Enter invalid Answer For Security Question '
webRsaPage.inputAnswer(securityanswer)
webRsaPage.confirmAnswer()

WebUIKeyword.delay(GlobalVariable.G_timeoutShort)

'Verify Status Screen'
webStatusScreenPage.verifyStatusScreen(verifyStatusScreenData)

