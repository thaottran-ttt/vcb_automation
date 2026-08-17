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

import internal.GlobalVariable as GlobalVariable

Map inputAddNewFavourite = MapUtils.parseObjectToMap(inputAddNewFavourite)
Map drpAddNewFavourite = MapUtils.parseObjectToMap(drpAddNewFavourite)
Map drpTransferDetailData = MapUtils.parseObjectToMap(drpTransferDetailData)

Map payToData = MapUtils.parseObjectToMap(payToData)
Map editFavourite = MapUtils.parseObjectToMap(editFavourite)
List verifyStatusScreenData = ListUtils.parseObjectToList(verifyStatusScreenData)
String favouriteName = inputAddNewFavourite.get("Nickname")
String favouriteEditName = editFavourite.get("Nickname")
Map addToFavouriteData = MapUtils.parseObjectToMap(addToFavouriteData)


List toastMessageAfterAdd = ListUtils.parseObjectToList(toastMessageAfterAdd)

WebCommonPage webCommonPage = WebBasePage.getPage(WebCommonPage)
WebHomePage webHomePage = WebBasePage.getPage(WebHomePage)
WebTransfer webTransfer = WebBasePage.getPage(WebTransfer)
WebStatusScreenPage webStatusScreenPage = WebBasePage.getPage(WebStatusScreenPage)
WebSecure2uAuthorisationPage webSecure2uAuthorisationPage = WebBasePage.getPage(WebSecure2uAuthorisationPage)
webSecure2uAuthorisationPage.setID_NUMBER(idNumber)

  WebUI.callTestCase(findTestCase('Mobile/VCB/Common/Login Secure2u Without Check Logout Stay In Secure2u'), [('username') : username, ('password') : password
		, ('idNumber') : idNumber, ('PAN') : PAN], BaseKeyword.defaultFailureHandling)

WebUI.callTestCase(findTestCase('Website/M2U/Common/Login'), [('username') : username, ('password') : password], BaseKeyword.defaultFailureHandling)

'Navigate To Transfer'
webHomePage.navigate(navItem, tabName)

webCommonPage.waitLoading()
'get balance'
Double originBalance = webHomePage.getBalance()
Mobile.delay(GlobalVariable.G_timeoutMiniShort)

'Select Type Payee'
webCommonPage.inputDropdownMap(payToData)

WebUIKeyword.delay(GlobalVariable.G_timeoutShort)

'select country name'
webTransfer.selectOtherBank(country, country)

'input and choose type of transfer '
webTransfer.inputAmtAndTypeTransfer(amount, typeTransfer)
webCommonPage.waitLoading()
WebUI.sendKeys(null, favouriteName)

'input sender details required fields'
webTransfer.inputCity(city)
webTransfer.inputDataTransfer(senderDetailsTextField, senderDetailsDropdown)

'Open recipient details section'
webTransfer.clickDetailsSection(webTransfer.RECIPIENT_DETAILS)

WebUIKeyword.delay(GlobalVariable.G_timeoutShort)

'input recipient details required fields'
webTransfer.inputDataTransfer(recipientDetailsTextField, recipientDetailsDropdown)

'Open transfer details section'
webTransfer.clickDetailsSection(webTransfer.TRANSFER_DETAILS)

WebUIKeyword.delay(GlobalVariable.G_timeoutShort)

'input transfer details required fields'
webTransfer.inputDropdownTransferMap(transferDetailsDropdown)

'click Agree term'
webTransfer.clickAgreeTerm()

'click Transfer button'
webTransfer.clickOverseasTransferButton()

WebUIKeyword.delay(GlobalVariable.G_timeoutLong)

'S2u'
webSecure2uAuthorisationPage.authorizeS2UV5(status)

'Verify Status Screen'
WebUIKeyword.delay(GlobalVariable.G_timeoutShort)
webStatusScreenPage.verifyStatusScreen(verifyStatusScreenData)

'add favourite'
webStatusScreenPage.addToFavourite(addToFavouriteData, toastMessageAfterAdd, status)


