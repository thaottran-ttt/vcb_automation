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
import com.vcb.utils.ListUtils as ListUtils
import com.vcb.utils.MapUtils as MapUtils
import internal.GlobalVariable as GlobalVariable


Map payToData = MapUtils.parseObjectToMap(payToData)

List verifyStatusScreenData = ListUtils.parseObjectToList(verifyStatusScreenData)

Map inputFavouriteTransferData = MapUtils.parseObjectToMap(inputFavouriteTransferData)

Map inputDropdownTransferData = MapUtils.parseObjectToMap(inputDropdownTransferData)

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

'get balance'
Double originBalance = webHomePage.getBalance()

Mobile.delay(GlobalVariable.G_timeoutMiniShort)

'Select Type Transfer'
webCommonPage.inputDropdownMap(payToData)

WebUIKeyword.delay(GlobalVariable.G_timeoutShort)

'Select Many Favorites'
for (int i = 0; i < favouriteName.size(); i++) {
    WebPayPage.selectFavouriteName(favouriteName[i])

    WebPayPage.inputPopupTransfer(inputFavouriteTransferData, inputDropdownTransferData)

    'Tap Transfer'
    WebPayPage.clickAddEditAnotherTransferButton()
}

WebPayPage.clickPayButton()

'S2u'
webSecure2uAuthorisationPage.authorize(status)

'Verify Status Screen'
webStatusScreenPage.verifyStatusScreen(verifyStatusScreenData)

//'Verify balance'
//Double newBalance = webHomePage.getTransSummary()
//
//webStatusScreenPage.verifyTransactionSuccess(originBalance, newBalance)
//
//'Verify transaction history'
//webStatusScreenPage.verifyTxnHistory(type, accountType, referenceNumber)