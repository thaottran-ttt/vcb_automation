import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase

import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.vcb.common.BaseKeyword
import com.vcb.common.WebUIKeyword
import com.vcb.m2u.internal.pages.base.HTMLTag
import com.vcb.m2u.internal.pages.base.WebBasePage
import com.vcb.m2u.internal.pages.base.WebCommonPage
import com.vcb.m2u.internal.pages.base.WebHomePage
import com.vcb.m2u.internal.pages.base.WebSecure2uAuthorisationPage
import com.vcb.m2u.internal.pages.base.WebStatusScreenPage
import com.vcb.m2u.internal.pages.base.WebSettingsPage
import com.vcb.m2u.internal.pages.base.accountpage.WebWealthPage
import com.vcb.utils.ListUtils
import com.vcb.utils.MapUtils

import internal.GlobalVariable as GlobalVariable

WebCommonPage webCommonPage = WebBasePage.getPage(WebCommonPage)
WebHomePage webHomePage = WebBasePage.getPage(WebHomePage)
WebWealthPage webWealthPage = WebBasePage.getPage(WebWealthPage)
WebSettingsPage webSettingsPage = WebBasePage.getPage(WebSettingsPage)

WebUI.callTestCase(findTestCase('Website/M2U/Common/Login'), [('username') : username, ('password') : password], BaseKeyword.defaultFailureHandling)

'Navigate To My Account'
webHomePage.navigate(navItem, tabName)

'Select Account'
WebUIKeyword.scrollToTestObject(webCommonPage.lblNormalizeSpace(accountType,'last()'))
webCommonPage.clickTestObjectWithWait(webCommonPage.lblNormalizeSpace(accountType,'last()'))

'Get list Wealth ASNB Accounts'
listASNBWealth = webWealthPage.getListASNB()

'Navigate to ASNB Accounts in Settings'
webHomePage.navigateSideBarMenu(sideBarItem)
webSettingsPage.navigateSettings(navItem2, tabName2)

'Get list linked ASNB Accounts'
listLinkedASNB = webSettingsPage.getListASNB()

'Compare 2 ASNB account lists'
webSettingsPage.compareASNBAccountLists(listASNBWealth, listLinkedASNB)