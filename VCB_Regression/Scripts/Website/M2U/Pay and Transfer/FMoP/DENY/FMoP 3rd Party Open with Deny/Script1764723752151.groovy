import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase

import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.vcb.common.BaseKeyword
import com.vcb.common.WebUIKeyword
import com.vcb.m2u.internal.pages.base.WebBasePage
import com.vcb.m2u.internal.pages.base.WebHomePage
import com.vcb.m2u.internal.pages.base.WebSecure2uAuthorisationPage
import com.vcb.m2u.internal.pages.base.WebStatusScreenPage
import com.vcb.m2u.internal.pages.base.WebCommonPage
import com.vcb.m2u.internal.pages.base.paytransfer.WebTransfer
import com.vcb.utils.ListUtils
import com.vcb.utils.MapUtils
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile


import internal.GlobalVariable as GlobalVariable
import com.vcb.m2u.internal.pages.base.rsapage.WebRsaPage
WebRsaPage webRsaPage = WebBasePage.getPage(WebRsaPage)

Map fromToData = MapUtils.parseObjectToMap(fromToData)
Map inputTransferData = MapUtils.parseObjectToMap(inputTransferData)
Map addToFavouriteData = MapUtils.parseObjectToMap(addToFavouriteData)
Map inputFavouriteTransferData = MapUtils.parseObjectToMap(inputFavouriteTransferData)
List toastMessageAfterAdd = ListUtils.parseObjectToList(toastMessageAfterAdd)
List statusScreenData = ListUtils.parseObjectToList(statusScreenData)

WebCommonPage webCommonPage = WebBasePage.getPage(WebCommonPage)
WebHomePage webHomePage = WebBasePage.getPage(WebHomePage)
WebTransfer webTransfer = WebBasePage.getPage(WebTransfer)
WebStatusScreenPage webStatusScreenPage = WebBasePage.getPage(WebStatusScreenPage)
WebSecure2uAuthorisationPage webSecure2uAuthorisationPage = WebBasePage.getPage(WebSecure2uAuthorisationPage)
webSecure2uAuthorisationPage.setID_NUMBER(idNumber)

WebUI.callTestCase(findTestCase('Mobile/VCB/Common/Login Secure2u With Secure Question'), [('idNumber') : idNumber, ('PAN') : PAN
	, ('password') : GlobalVariable.G_password, ('username') : username, ('accountName') : '', ('flowControl') : com.vcb.common.FlowControlEnum.STOP
	, ('failStopTestSuite') : false, ('yourName') : GlobalVariable.G_yourName], BaseKeyword.defaultFailureHandling)

WebUI.callTestCase(findTestCase('Website/M2U/Common/Login'), [('username') : username, ('password') : password], BaseKeyword.defaultFailureHandling)

'Navigate To Transfer'
webHomePage.navigate(navItem, tabName)

webCommonPage.waitLoading()

'Get balance'
Double originBalance = webHomePage.getBalance()

Mobile.delay(GlobalVariable.G_timeoutMiniShort)

'Select Type Transfer'
webTransfer.selectOtherTransfer(fromToData)

WebUIKeyword.delay(GlobalVariable.G_timeoutShort)

if (validateFavouriteTransfer) {
	'Select Favourite Name'
    webTransfer.selectFavouriteName(favouriteName)
	'Input data'
    webTransfer.inputPopupTransfer(inputFavouriteTransferData)
} else {
	'select Bank name'
	webTransfer.selectOtherBank(otherBank)
	WebUIKeyword.delay(GlobalVariable.G_timeoutShort)
    'Input Transfer Data'
    webTransfer.inputPopupTransfer(inputTransferData)
}

'Input Future Date'
if (isFuture) {
	WebUIKeyword.delay(GlobalVariable.G_timeoutShort)
	'Set Date'
    webTransfer.inputTomorrow(datePickerLabel)
}

if (isRecurring) {
    webTransfer.setRecurring(recurringLabel)

    webTransfer.inputTomorrow(datePickerLabel)
}

'Tap Transfer'
webTransfer.clickTransferButton()

WebUIKeyword.delay(GlobalVariable.G_timeoutShort)

'Verify S2U'
webRsaPage.clickRequestButton()

WebUIKeyword.delay(GlobalVariable.G_timeoutShort)

'Verify Status Screen'
webStatusScreenPage.verifyStatusScreen(statusScreenData)
