import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase

import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.maybank.common.BaseKeyword
import com.maybank.common.WebUIKeyword
import com.maybank.m2u.internal.pages.base.WebBasePage
import com.maybank.m2u.internal.pages.base.WebCommonPage
import com.maybank.m2u.internal.pages.base.WebHomePage
import com.maybank.m2u.internal.pages.base.WebSecure2uAuthorisationPage
import com.maybank.m2u.internal.pages.base.WebStatusScreenPage
import com.maybank.m2u.internal.pages.base.paytransfer.WebTransfer
import com.maybank.utils.ListUtils
import com.maybank.utils.MapUtils

import internal.GlobalVariable as GlobalVariable

Map inputAddNewFavourite = MapUtils.parseObjectToMap(inputAddNewFavourite)
Map drpAddNewFavourite = MapUtils.parseObjectToMap(drpAddNewFavourite)
Map inputBankDetailData = MapUtils.parseObjectToMap(inputBankDetailData)
Map drpBankDetailData = MapUtils.parseObjectToMap(drpBankDetailData)
Map inputReceiptDetailData = MapUtils.parseObjectToMap(inputReceiptDetailData)
Map drpReceiptDetailData = MapUtils.parseObjectToMap(drpReceiptDetailData)

Map payToData = MapUtils.parseObjectToMap(payToData)
Map editFavouriteData = MapUtils.parseObjectToMap(editFavouriteData)
List verifyStatusScreenData = ListUtils.parseObjectToList(verifyStatusScreenData)
String favouriteName = inputAddNewFavourite.get("Nickname")

WebCommonPage webCommonPage = WebBasePage.getPage(WebCommonPage)
WebHomePage webHomePage = WebBasePage.getPage(WebHomePage)
WebTransfer webTransfer = WebBasePage.getPage(WebTransfer)
WebStatusScreenPage webStatusScreenPage = WebBasePage.getPage(WebStatusScreenPage)
WebSecure2uAuthorisationPage webSecure2uAuthorisationPage = WebBasePage.getPage(WebSecure2uAuthorisationPage)
webSecure2uAuthorisationPage.setID_NUMBER(idNumber)

WebUI.callTestCase(findTestCase('Mobile/MAE/Common/Login Secure2u Without Check Logout'), [('username') : username, ('password') : password
		, ('idNumber') : idNumber, ('PAN') : PAN], BaseKeyword.defaultFailureHandling)

WebUI.callTestCase(findTestCase('Website/M2U/Common/Login'), [('username') : username, ('password') : password], BaseKeyword.defaultFailureHandling)

'Navigate To Transfer'
webHomePage.navigate(navItem, tabName)

webCommonPage.waitLoading()
Mobile.delay(GlobalVariable.G_timeoutMiniShort)

'Select Type Payee'
webCommonPage.inputDropdownMap(payToData)

WebUIKeyword.delay(GlobalVariable.G_timeoutShort)


'Show CTA'
webTransfer.clickShowFavouriteMenu(favouriteName)

'Click Edit'
webTransfer.selectMenuCTAName(ctaName)

WebUIKeyword.delay(GlobalVariable.G_timeoutMiniShort)

'Edit Favourite'
webTransfer.editFavourite(editFavouriteData)

WebUIKeyword.delay(GlobalVariable.G_timeoutShort)

'S2u'
webSecure2uAuthorisationPage.authorize(status)

'Verify Status Edit Screen'
webStatusScreenPage.verifyStatusScreen(verifyStatusScreenData)

