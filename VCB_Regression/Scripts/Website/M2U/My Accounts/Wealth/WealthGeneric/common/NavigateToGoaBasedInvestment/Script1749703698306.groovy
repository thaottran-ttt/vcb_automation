import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase

import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.vcb.common.BaseKeyword
import com.vcb.common.WebUIKeyword
import com.vcb.m2u.internal.pages.base.HTMLTag
import com.vcb.m2u.internal.pages.base.WebBasePage
import com.vcb.m2u.internal.pages.base.WebCommonPage
import com.vcb.m2u.internal.pages.base.WebHomePage
import com.vcb.common.WebUIKeyword
import com.vcb.m2u.internal.pages.base.WebSecure2uAuthorisationPage
import com.vcb.m2u.internal.pages.base.WebStatusScreenPage
import com.vcb.m2u.internal.pages.base.accountpage.WebWealthGenericPage
import com.vcb.utils.ListUtils
import com.vcb.utils.MapUtils

import internal.GlobalVariable as GlobalVariable

List statusScreenData = ListUtils.parseObjectToList(statusScreenData)
Map transferFrom = MapUtils.parseObjectToMap(transferFrom)

WebHomePage webHomePage = WebBasePage.getPage(WebHomePage)
WebWealthGenericPage webWealthGenericPage = WebBasePage.getPage(WebWealthGenericPage)
WebSecure2uAuthorisationPage webSecure2uAuthorisationPage = WebBasePage.getPage(WebSecure2uAuthorisationPage)
webSecure2uAuthorisationPage.setID_NUMBER(idNumber)

WebUI.callTestCase(findTestCase('null'), [('username') : username, ('password') : password], BaseKeyword.defaultFailureHandling)

'Navigate To Wealth'
webHomePage.navigate(navItem, tabName)
