import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase

import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.vcb.common.BaseKeyword
import com.vcb.common.WebUIKeyword
import com.vcb.m2u.internal.pages.base.WebBasePage
import com.vcb.m2u.internal.pages.base.WebCommonPage
import com.vcb.m2u.internal.pages.base.WebHomePage
import com.vcb.m2u.internal.pages.base.WebSecure2uAuthorisationPage
import com.vcb.m2u.internal.pages.base.WebStatusScreenPage
import com.vcb.m2u.internal.pages.base.paytransfer.WebTransfer
import com.vcb.utils.ListUtils
import com.vcb.utils.MapUtils
import com.vcb.utils.StringUtils

import internal.GlobalVariable as GlobalVariable

Map payToData = MapUtils.parseObjectToMap(payToData)
List verifyStatusScreenData = ListUtils.parseObjectToList(verifyStatusScreenData)
Map inpuTextNewFavourite = MapUtils.parseObjectToMap(inputTextDetails)

WebCommonPage webCommonPage = WebBasePage.getPage(WebCommonPage)
WebHomePage webHomePage = WebBasePage.getPage(WebHomePage)
WebTransfer webTransfer = WebBasePage.getPage(WebTransfer)
WebStatusScreenPage webStatusScreenPage = WebBasePage.getPage(WebStatusScreenPage)
WebSecure2uAuthorisationPage webSecure2uAuthorisationPage = WebBasePage.getPage(WebSecure2uAuthorisationPage)
webSecure2uAuthorisationPage.setID_NUMBER(idNumber)
  
WebUI.callTestCase(findTestCase('null'), [('username') : username, ('password') : password], BaseKeyword.defaultFailureHandling)

'Navigate To Transfer'
webHomePage.navigate(navItem, tabName)

webCommonPage.waitLoading()
Mobile.delay(GlobalVariable.G_timeoutMiniShort)

'Select Type Payee'
webCommonPage.inputDropdownMap(payToData)

WebUIKeyword.delay(GlobalVariable.G_timeoutShort)

'Show CTA'
webTransfer.clickShowFavouriteMenu(favouriteName)

'Click Remove'
webTransfer.selectMenuCTAName(ctaName)

WebUIKeyword.delay(GlobalVariable.G_timeoutShort)

'Remove Favourite'
webTransfer.removeFavourite()
