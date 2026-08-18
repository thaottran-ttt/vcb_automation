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
import com.vcb.m2u.internal.pages.base.paytransfer.WebPayPage
import com.vcb.utils.ListUtils
import com.vcb.utils.MapUtils

import internal.GlobalVariable as GlobalVariable

Map inputAddNewFavourite = MapUtils.parseObjectToMap(inputAddNewFavourite)
Map drpAddNewFavourite = MapUtils.parseObjectToMap(drpAddNewFavourite)
Map payToData = MapUtils.parseObjectToMap(payToData)
Map editFavourite = MapUtils.parseObjectToMap(editFavourite)
List verifyStatusScreenData = ListUtils.parseObjectToList(verifyStatusScreenData)
String favouriteName = inputAddNewFavourite.get("Nickname")
String favouriteEditName = editFavourite.get("Nickname")

WebCommonPage webCommonPage = WebBasePage.getPage(WebCommonPage)
WebHomePage webHomePage = WebBasePage.getPage(WebHomePage)
WebPayPage WebPayPage = WebBasePage.getPage(WebPayPage)
WebStatusScreenPage webStatusScreenPage = WebBasePage.getPage(WebStatusScreenPage)
WebSecure2uAuthorisationPage webSecure2uAuthorisationPage = WebBasePage.getPage(WebSecure2uAuthorisationPage)
webSecure2uAuthorisationPage.setID_NUMBER(idNumber)

WebUI.callTestCase(findTestCase('Mobile/VCB/Common/Login Secure2u Without Check Logout Stay In Secure2u'), [('username') : username, ('password') : password
        , ('idNumber') : idNumber, ('PAN') : PAN], BaseKeyword.defaultFailureHandling)

WebUI.callTestCase(findTestCase('null'), [('username') : username, ('password') : password], BaseKeyword.defaultFailureHandling)

'Navigate To Transfer'
webHomePage.navigate(navItem, tabName)

webCommonPage.waitLoading()
Mobile.delay(GlobalVariable.G_timeoutMiniShort)

'Select Type Payee'
webCommonPage.inputDropdownMap(payToData)

WebUIKeyword.delay(GlobalVariable.G_timeoutShort)

'Click Button Add New Favourite'
WebPayPage.clickAddNewFavouriteButton()

'Add Favourite'
WebPayPage.addNewFavourite(drpAddNewFavourite, inputAddNewFavourite)

WebUIKeyword.delay(GlobalVariable.G_timeoutShort)

'S2u'
webSecure2uAuthorisationPage.authorize(status)

'Verify Status Screen'
webStatusScreenPage.verifyStatusScreen(verifyStatusScreenData)

'Navigate To Transfer To Edit'
webHomePage.navigate(navItem, tabName)

Mobile.delay(GlobalVariable.G_timeoutMiniShort)

'Select Type Transfer'
webCommonPage.inputDropdownMap(payToData)

WebUIKeyword.delay(GlobalVariable.G_timeoutMiniShort)

'Show CTA'
WebPayPage.clickShowFavouriteMenu(favouriteName)

'Click Edit'
WebPayPage.selectMenuCTAName(ctaName)

WebUIKeyword.delay(GlobalVariable.G_timeoutMiniShort)

'Edit Favourite'
WebPayPage.editFavourite(editFavourite)

WebUIKeyword.delay(GlobalVariable.G_timeoutShort)

'S2u'
webSecure2uAuthorisationPage.authorize(status)

'Verify Status Edit Screen'
webStatusScreenPage.verifyStatusScreen(verifyStatusScreenData)

'Navigate To Transfer To Remove'
webHomePage.navigate(navItem, tabName)

Mobile.delay(GlobalVariable.G_timeoutMiniShort)

'Select Type Transfer'
webCommonPage.inputDropdownMap(payToData)

WebUIKeyword.delay(GlobalVariable.G_timeoutMiniShort)

'Show CTA'
WebPayPage.clickShowFavouriteMenu(favouriteEditName)

'Click Remove'
WebPayPage.selectMenuCTAName(ctaRemoveName)

WebUIKeyword.delay(GlobalVariable.G_timeoutShort)

'Remove Favourite'
WebPayPage.removeFavourite()
