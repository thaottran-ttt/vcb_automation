import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase

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

import internal.GlobalVariable as GlobalVariable

Map inputAddNewFavourite = MapUtils.parseObjectToMap(inputAddNewFavourite)
Map drpAddNewFavourite = MapUtils.parseObjectToMap(drpAddNewFavourite)
Map fromToData = MapUtils.parseObjectToMap(fromToData)
List verifyStatusScreenData = ListUtils.parseObjectToList(verifyStatusScreenData)

WebCommonPage webCommonPage = WebBasePage.getPage(WebCommonPage)
WebHomePage webHomePage = WebBasePage.getPage(WebHomePage)
WebTransfer webTransfer = WebBasePage.getPage(WebTransfer)
WebStatusScreenPage webStatusScreenPage = WebBasePage.getPage(WebStatusScreenPage)
WebSecure2uAuthorisationPage webSecure2uAuthorisationPage = WebBasePage.getPage(WebSecure2uAuthorisationPage)

WebUI.callTestCase(findTestCase('Test Cases/Mobile/VCB/Common/Login Secure2u Without Check Logout Stay In Secure2u'), [('username') : username, ('password') : password
        , ('idNumber') : idNumber, ('PAN') : PAN], BaseKeyword.defaultFailureHandling)

WebUI.callTestCase(findTestCase('null'), [('username') : username, ('password') : password], BaseKeyword.defaultFailureHandling)

'Navigate To Transfer'
webHomePage.navigate(navItem, tabName)

webCommonPage.waitLoading()

'Select Type Transfer'
webTransfer.selectOtherTransfer(fromToData)

'Click Button Add New Favourite'
webTransfer.clickAddNewFavouriteButton()

WebUIKeyword.delay(GlobalVariable.G_timeoutMiniShort)

'Add Favourite'
webTransfer.addNewFavourite(drpAddNewFavourite, inputAddNewFavourite)

WebUIKeyword.delay(GlobalVariable.G_timeoutMiniShort)

'S2u'
webSecure2uAuthorisationPage.authorize(status)

'Verify Status Screen'
webStatusScreenPage.verifyStatusScreen(verifyStatusScreenData)