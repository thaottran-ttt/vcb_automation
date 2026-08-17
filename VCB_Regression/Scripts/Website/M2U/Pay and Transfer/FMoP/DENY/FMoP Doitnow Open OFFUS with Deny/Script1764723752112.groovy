import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.vcb.common.BaseKeyword as BaseKeyword
import com.vcb.common.WebUIKeyword as WebUIKeyword
import com.vcb.m2u.internal.pages.base.WebBasePage as WebBasePage
import com.vcb.m2u.internal.pages.base.WebCommonPage as WebCommonPage
import com.vcb.m2u.internal.pages.base.WebHomePage as WebHomePage
import com.vcb.m2u.internal.pages.base.WebSecure2uAuthorisationPage as WebSecure2uAuthorisationPage
import com.vcb.m2u.internal.pages.base.WebStatusScreenPage as WebStatusScreenPage
import com.vcb.m2u.internal.pages.base.paytransfer.WebTransfer as WebTransfer
import com.vcb.m2u.internal.pages.base.rsapage.WebRsaPage as WebRsaPage
import com.vcb.utils.ListUtils as ListUtils
import com.vcb.utils.MapUtils as MapUtils
import internal.GlobalVariable as GlobalVariable

import com.vcb.mobile.internal.pages.base.Secure2uAuthorisationPage
import com.vcb.mobile.internal.pages.base.Secure2uAuthorisationPage.AuthorisationType
import com.vcb.mobile.internal.pages.base.BasePage

Map fromToData = MapUtils.parseObjectToMap(fromToData)
Map dropdownTransferData = MapUtils.parseObjectToMap(dropdownTransferData)
Map inputTransferData = MapUtils.parseObjectToMap(inputTransferData)
Map addToFavouriteData = MapUtils.parseObjectToMap(addToFavouriteData)
Map dropdownFavouriteTransferData = MapUtils.parseObjectToMap(dropdownFavouriteTransferData)
Map inputFavouriteTransferData = MapUtils.parseObjectToMap(inputFavouriteTransferData)
List toastMessageAfterAdd = ListUtils.parseObjectToList(toastMessageAfterAdd)
List statusScreenData = ListUtils.parseObjectToList(statusScreenData)

WebCommonPage webCommonPage = WebBasePage.getPage(WebCommonPage)
WebHomePage webHomePage = WebBasePage.getPage(WebHomePage)
WebTransfer webTransfer = WebBasePage.getPage(WebTransfer)
WebStatusScreenPage webStatusScreenPage = WebBasePage.getPage(WebStatusScreenPage)
WebSecure2uAuthorisationPage webSecure2uAuthorisationPage = WebBasePage.getPage(WebSecure2uAuthorisationPage)
WebRsaPage webRsaPage = WebBasePage.getPage(WebRsaPage)
Secure2uAuthorisationPage secure2uAuthorisationPage = BasePage.getPage(Secure2uAuthorisationPage);

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

if (validateFavouriteTransfer) {
    'Click Favourite Name'
    webTransfer.selectFavouriteName(favouriteName)

    Mobile.delay(GlobalVariable.G_timeoutMiniShort)

    'Select Dropdown Transfer Data'
    webTransfer.selectDropdownPopupTransfer(dropdownFavouriteTransferData)

    webTransfer.inputPopupTransfer(inputFavouriteTransferData)

    'Click Term Radio'
    webTransfer.clickAgreeTerm()
} else {
    'Click Other Bank'
    webTransfer.selectOtherBank(transferType)

    Mobile.delay(GlobalVariable.G_timeoutMiniShort)

    'Select Dropdown Transfer Data'
    webTransfer.selectDropdownPopupTransfer(dropdownTransferData)

    Mobile.delay(GlobalVariable.G_timeoutMiniShort)

    'Input Transfer Data'
    webTransfer.inputPopupTransfer(inputTransferData)

    'Click Term Radio'
    webTransfer.clickAgreeTerm()
}

'Input Future Date'
if (isFuture) {
    'Select Tomorrow'
    webTransfer.inputTomorrow(datePickerLabel)
}

if (isRecurring) {
    'Set Recurring'
    webTransfer.setRecurring(recurringLabel)

    'Select Tommorrow'
    webTransfer.inputTomorrow(datePickerLabel)
}

'Tap Transfer'
webTransfer.clickTransferButton()

WebUIKeyword.delay(GlobalVariable.G_timeoutShort)

'S2u'
webRsaPage.clickRequestButton()

WebUIKeyword.delay(GlobalVariable.G_timeoutShort)

'Verify Status Screen'
webStatusScreenPage.verifyStatusScreen(verifyStatusScreenData)

