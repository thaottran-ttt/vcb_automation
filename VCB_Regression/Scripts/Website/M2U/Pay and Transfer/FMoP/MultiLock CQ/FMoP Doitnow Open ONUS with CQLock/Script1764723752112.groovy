import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.maybank.common.BaseKeyword as BaseKeyword
import com.maybank.common.WebUIKeyword as WebUIKeyword
import com.maybank.m2u.internal.pages.base.WebBasePage as WebBasePage
import com.maybank.m2u.internal.pages.base.WebCommonPage as WebCommonPage
import com.maybank.m2u.internal.pages.base.WebHomePage as WebHomePage
import com.maybank.m2u.internal.pages.base.WebSecure2uAuthorisationPage as WebSecure2uAuthorisationPage
import com.maybank.m2u.internal.pages.base.WebStatusScreenPage as WebStatusScreenPage
import com.maybank.m2u.internal.pages.base.paytransfer.WebTransfer as WebTransfer
import com.maybank.m2u.internal.pages.base.rsapage.WebRsaPage as WebRsaPage
import com.maybank.utils.ListUtils as ListUtils
import com.maybank.utils.MapUtils as MapUtils
import internal.GlobalVariable as GlobalVariable

import com.maybank.mae.internal.pages.base.Secure2uAuthorisationPage
import com.maybank.mae.internal.pages.base.Secure2uAuthorisationPage.AuthorisationType
import com.maybank.mae.internal.pages.base.BasePage

import com.maybank.m2u.internal.pages.base.rsapage.WebRsaPage
WebRsaPage webRsaPage = WebBasePage.getPage(WebRsaPage)

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

WebUI.callTestCase(findTestCase('Mobile/MAE/Common/Login Secure2u With Secure Question'), [('idNumber') : idNumber, ('PAN') : PAN
        , ('password') : GlobalVariable.G_password, ('username') : username, ('accountName') : '', ('flowControl') : com.maybank.common.FlowControlEnum.STOP
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

'Verify S2U'
webSecure2uAuthorisationPage.authoriseWCqQuestion(status, securityanswer)

'Enter invalid Answer For Security Question '
webRsaPage.inputAnswer(securityanswer)
webRsaPage.confirmAnswer()

'Enter invalid Answer For Security Question '
webRsaPage.inputAnswer(securityanswer)
webRsaPage.confirmAnswer()

WebUIKeyword.delay(GlobalVariable.G_timeoutShort)

'Verify Status Screen'
webStatusScreenPage.verifyStatusScreen(verifyStatusScreenData)


