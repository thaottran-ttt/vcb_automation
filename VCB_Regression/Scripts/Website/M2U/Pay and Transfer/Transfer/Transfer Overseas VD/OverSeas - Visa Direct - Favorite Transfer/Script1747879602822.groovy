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
webTransfer.updateNicknameToRandom(inpuTextNewFavourite, 10)

  WebUI.callTestCase(findTestCase('Mobile/VCB/Common/Login Secure2u Without Check Logout Stay In Secure2u'), [('username') : username, ('password') : password
		, ('idNumber') : idNumber, ('PAN') : PAN], BaseKeyword.defaultFailureHandling)

WebUI.callTestCase(findTestCase('Website/M2U/Common/Login'), [('username') : username, ('password') : password], BaseKeyword.defaultFailureHandling)

'Navigate To Transfer'
webHomePage.navigate(navItem, tabName)

webCommonPage.waitLoading()
'Get balance'
Double originBalance = webHomePage.getBalance()
Mobile.delay(GlobalVariable.G_timeoutMiniShort)

'Select Type Payee'
webCommonPage.inputDropdownMap(payToData)

WebUIKeyword.delay(GlobalVariable.G_timeoutShort)

'Click Button Add New Favourite'
webTransfer.clickAddNewFavouriteOverSeasButton()

'Add favorite details'
webTransfer.inputVisaDirectFavoriteDetails(inputDropDownDetails,inpuTextNewFavourite)

WebUIKeyword.delay(GlobalVariable.G_timeoutShort)

'S2u'
webSecure2uAuthorisationPage.authorizeS2UV5(status)

'Verify Status Screen'
webStatusScreenPage.verifyStatusScreen(verifyStatusScreenData)

webCommonPage.clickDoneButton()

WebUIKeyword.delay(GlobalVariable.G_timeoutShort)

favouriteName = inpuTextNewFavourite.get("Nickname")

'Select Type Payee'
webCommonPage.inputDropdownMap(payToData)

'Select Favourite Name'
webTransfer.selectFavouriteName(favouriteName)

WebUIKeyword.delay(GlobalVariable.G_timeoutMedium)

'Input amount'
webTransfer.InputAmountFavouriteVisaDirect(amount)

'Agree term'
webTransfer.agreeTermForVisaDirect()

'click Transfer button'
webTransfer.clickOverseasTransferButton(webTransfer.TRANSFER_MODAL_CLASS)

WebUIKeyword.delay(GlobalVariable.G_timeoutMedium)

'S2u'
webSecure2uAuthorisationPage.authorize(status)

'Verify Status Screen'
webStatusScreenPage.verifyStatusScreen(verifyStatusScreenData)

//Need to rerun this test case and verify these methods below
'Verify balance'
Double newBalance = webHomePage.getTransSummary()

webStatusScreenPage.verifyTransactionSuccess(originBalance, newBalance)

webStatusScreenPage.verifyTxnHistory(accountType)