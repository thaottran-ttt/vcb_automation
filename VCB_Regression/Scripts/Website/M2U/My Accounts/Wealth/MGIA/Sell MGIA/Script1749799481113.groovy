import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.vcb.common.BaseKeyword as BaseKeyword
import com.vcb.common.WebUIKeyword as WebUIKeyword
import com.vcb.m2u.internal.pages.base.HTMLTag as HTMLTag
import com.vcb.m2u.internal.pages.base.WebBasePage as WebBasePage
import com.vcb.m2u.internal.pages.base.WebCommonPage as WebCommonPage
import com.vcb.m2u.internal.pages.base.WebHomePage as WebHomePage
import com.vcb.m2u.internal.pages.base.WebSecure2uAuthorisationPage as WebSecure2uAuthorisationPage
import com.vcb.m2u.internal.pages.base.WebStatusScreenPage as WebStatusScreenPage
import com.vcb.m2u.internal.pages.base.accountpage.WebWealthPage as WebWealthPage
import com.vcb.utils.ListUtils as ListUtils
import com.vcb.utils.MapUtils as MapUtils
import internal.GlobalVariable as GlobalVariable

List statusScreenData = ListUtils.parseObjectToList(statusScreenData)

Map inputDropdownData = MapUtils.parseObjectToMap(inputDropdownData)

Map inputData = MapUtils.parseObjectToMap(inputData)

WebCommonPage webCommonPage = WebBasePage.getPage(WebCommonPage)

WebHomePage webHomePage = WebBasePage.getPage(WebHomePage)

WebWealthPage webWealthPage = WebBasePage.getPage(WebWealthPage)

WebStatusScreenPage webStatusScreenPage = WebBasePage.getPage(WebStatusScreenPage)

WebSecure2uAuthorisationPage webSecure2uAuthorisationPage = WebBasePage.getPage(WebSecure2uAuthorisationPage)

webSecure2uAuthorisationPage.setID_NUMBER(idNumber)

WebUI.callTestCase(findTestCase('Mobile/VCB/Common/Login Secure2u Without Check Logout'), [('username') : username, ('password') : password
        , ('idNumber') : idNumber, ('PAN') : PAN], BaseKeyword.defaultFailureHandling)

WebUI.callTestCase(findTestCase('Website/M2U/Common/Login'), [('username') : username, ('password') : password], BaseKeyword.defaultFailureHandling)

'Navigate To My Account'
webHomePage.navigate(navItem, tabName)

'Select Account'
WebUIKeyword.clickWithoutScrollW3C(webCommonPage.lblNormalizeSpace(accountType))

'Verify UI '
webCommonPage.verifyElementListVisible(UIData)

WebUIKeyword.delay(GlobalVariable.G_timeoutShort)

'Input DropDown'
webCommonPage.selectDropdownByTestObject(webCommonPage.drpForLabelNo(accountType, 1), accountName, isScroll)

'Buy or Sell gold'
webWealthPage.buyAndSellGold(isBuy, inputData, inputDropdownData)

if (isBuy) {
    webCommonPage.clickConfirmButton()
} else {
    'Authorise'
    webSecure2uAuthorisationPage.authorize(status)
}

WebUIKeyword.delay(GlobalVariable.G_timeoutShort)

'Verify Status Screen'
webStatusScreenPage.verifyStatusScreen(statusScreenData)

'Check txn history'

'Navigate To My Account'
webHomePage.navigate(navItem, tabName)

'Select Account'
WebUIKeyword.clickWithoutScrollW3C(webCommonPage.lblNormalizeSpace(accountType))

WebUIKeyword.delay(GlobalVariable.G_timeoutShort)

'Input DropDown'
webCommonPage.selectDropdownByTestObject(webCommonPage.drpForLabelNo(accountType, 1), accountName, isScroll)

webWealthPage.verifyBuyAndSellGold(isBuy, inputData.get('Amount (gm)'))