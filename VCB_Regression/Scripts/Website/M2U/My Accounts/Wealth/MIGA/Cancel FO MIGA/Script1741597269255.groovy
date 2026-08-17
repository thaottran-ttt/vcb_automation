import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase

import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.vcb.common.BaseKeyword
import com.vcb.common.WebUIKeyword
import com.vcb.m2u.internal.pages.base.ElementVerificationType
import com.vcb.m2u.internal.pages.base.HTMLTag
import com.vcb.m2u.internal.pages.base.WebBasePage
import com.vcb.m2u.internal.pages.base.WebCommonPage
import com.vcb.m2u.internal.pages.base.WebHomePage
import com.vcb.m2u.internal.pages.base.WebSecure2uAuthorisationPage
import com.vcb.m2u.internal.pages.base.WebStatusScreenPage
import com.vcb.m2u.internal.pages.base.accountpage.WebWealthPage
import com.vcb.utils.ListUtils

import internal.GlobalVariable as GlobalVariable

List verifyUIPopupData = ListUtils.parseObjectToList(verifyUIPopupData)

List statusScreenData = ListUtils.parseObjectToList(statusScreenData)

WebCommonPage webCommonPage = WebBasePage.getPage(WebCommonPage)

WebHomePage webHomePage = WebBasePage.getPage(WebHomePage)

WebWealthPage webWealthPage = WebBasePage.getPage(WebWealthPage)

WebStatusScreenPage webStatusScreenPage = WebBasePage.getPage(WebStatusScreenPage)

WebSecure2uAuthorisationPage webSecure2uAuthorisationPage = WebBasePage.getPage(WebSecure2uAuthorisationPage)

webSecure2uAuthorisationPage.setID_NUMBER(idNumber)

WebUI.callTestCase(findTestCase('Mobile/VCB/Common/Login Secure2u Without Check Logout'), [('username') : username, ('password') : password
        , ('idNumber') : idNumber, ('PAN') : PAN], BaseKeyword.defaultFailureHandling)

WebUI.callTestCase(findTestCase('Website/M2U/Common/Login'), [('username') : username, ('password') : password], BaseKeyword.defaultFailureHandling)

'Navigate To Transfer'
webHomePage.navigate(navItem, tabName)

'Select Account'
WebUIKeyword.scrollToTestObject(webCommonPage.lblNormalizeSpace(accountType))

webCommonPage.click(webCommonPage.lblNormalizeSpace(accountType, 1, HTMLTag.empty))

WebUIKeyword.delay(GlobalVariable.G_timeoutShort)

'Delete FO Buy, Cell'
boolean result = webWealthPage.removeFutureOrder(FOTitle, verifyUIPopupData)

if (!(result)) {
    'case i > 0 return. i == 0 log waning or error'
    webCommonPage.handleError('Dont have FO MIGA', BaseKeyword.defaultFailureHandling)
}

'S2u'
webSecure2uAuthorisationPage.authorize(status)

'Verify Status Screen'
webStatusScreenPage.verifyStatusScreen(statusScreenData, ElementVerificationType.Normalize_Space)