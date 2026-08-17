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
import com.vcb.m2u.internal.pages.base.paytransfer.WebPayPage as WebPayPage
import com.vcb.m2u.internal.pages.base.paytransfer.WebTransfer as WebTransfer
import com.vcb.utils.ListUtils as ListUtils
import com.vcb.utils.MapUtils as MapUtils
import internal.GlobalVariable as GlobalVariable

Map inputAddNewFavourite = MapUtils.parseObjectToMap(inputAddNewFavourite)

Map inputTransfer = MapUtils.parseObjectToMap(inputTransfer)

Map drpTransfer = MapUtils.parseObjectToMap(drpTransfer)

Map payToData = MapUtils.parseObjectToMap(payToData)

Map editFavourite = MapUtils.parseObjectToMap(editFavourite)

List verifyStatusScreenData = ListUtils.parseObjectToList(verifyStatusScreenData)

String favouriteName = inputAddNewFavourite.get('Nickname')

String favouriteEditName = editFavourite.get('Nickname')

WebCommonPage webCommonPage = WebBasePage.getPage(WebCommonPage)

WebHomePage webHomePage = WebBasePage.getPage(WebHomePage)

WebTransfer webTransfer = WebBasePage.getPage(WebTransfer)

WebPayPage WebPayPage = WebBasePage.getPage(WebPayPage)

WebStatusScreenPage webStatusScreenPage = WebBasePage.getPage(WebStatusScreenPage)

WebSecure2uAuthorisationPage webSecure2uAuthorisationPage = WebBasePage.getPage(WebSecure2uAuthorisationPage)

webSecure2uAuthorisationPage.setID_NUMBER(idNumber)

WebUI.callTestCase(findTestCase('Mobile/VCB/Common/Login Secure2u Without Check Logout'), [('username') : username, ('password') : password
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

'Select Many Favorites'
for (int i = 0; i < listFav.size(); i++) {
    WebPayPage.selectFavouriteName(listFav[i])

    'Process Favourite Name'
    WebPayPage.inputPopupTransfer(inputTransfer, drpTransfer)

    webTransfer.clickAgreeTerm()

    'Tap Transfer'
    webTransfer.clickAddEditAnotherTransferButton()
}

webTransfer.clickMultiTransferButton()

WebUIKeyword.delay(GlobalVariable.G_timeoutShort)

'S2u'
webSecure2uAuthorisationPage.authorize(status)

'Verify Status Screen'
webStatusScreenPage.verifyStatusScreen(verifyStatusScreenData)
