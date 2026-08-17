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
import com.vcb.utils.StringUtils as StringUtils
import internal.GlobalVariable as GlobalVariable

Map payToData = MapUtils.parseObjectToMap(payToData)

List verifyStatusScreenData = ListUtils.parseObjectToList(verifyStatusScreenData)

Map addToFavouriteData = MapUtils.parseObjectToMap(addToFavouriteData)

Map inputTransferData = MapUtils.parseObjectToMap(inputTransferData)

List toastMessageAfterAdd = ListUtils.parseObjectToList(toastMessageAfterAdd)

WebCommonPage webCommonPage = WebBasePage.getPage(WebCommonPage)

WebHomePage webHomePage = WebBasePage.getPage(WebHomePage)

WebPayPage WebPayPage = WebBasePage.getPage(WebPayPage)

WebStatusScreenPage webStatusScreenPage = WebBasePage.getPage(WebStatusScreenPage)

WebSecure2uAuthorisationPage webSecure2uAuthorisationPage = WebBasePage.getPage(WebSecure2uAuthorisationPage)

webSecure2uAuthorisationPage.setID_NUMBER(idNumber)

String referenceNumber = StringUtils.createNumberRandom(length)
inputTransferData.put('Reference Number', referenceNumber)

WebUI.callTestCase(findTestCase('Mobile/VCB/Common/Login Secure2u Without Check Logout Stay In Secure2u'), [('username') : username, ('password') : password
        , ('idNumber') : idNumber, ('PAN') : PAN], BaseKeyword.defaultFailureHandling)

WebUI.callTestCase(findTestCase('Website/M2U/Common/Login'), [('username') : username, ('password') : password], BaseKeyword.defaultFailureHandling)

'Navigate To Transfer'
webHomePage.navigate(navItem, tabName)

'Get balance'
Double originBalance = webHomePage.getBalance()

Mobile.delay(GlobalVariable.G_timeoutMiniShort)

'Select Type Transfer'
webCommonPage.inputDropdownMap(payToData)

WebUIKeyword.delay(GlobalVariable.G_timeoutShort)

'select Bank name'
WebPayPage.selectOtherBank(otherBank)

WebUIKeyword.delay(GlobalVariable.G_timeoutShort)

'Input Transfer Data'
WebPayPage.inputPopupPayTo(inputTransferData)

WebUIKeyword.delay(GlobalVariable.G_timeoutShort)

'S2u'
webSecure2uAuthorisationPage.authorize(status)

WebUIKeyword.delay(GlobalVariable.G_timeoutShort)

'Verify Status Screen'
webStatusScreenPage.verifyStatusScreen(verifyStatusScreenData)

'Verify balance'
Double newBalance = webHomePage.getTransSummary()

webStatusScreenPage.verifyTransactionSuccess(originBalance, newBalance)

'Add favourite'
webStatusScreenPage.addToFavourite(addToFavouriteData, toastMessageAfterAdd, status)

'Verify transaction history'
webStatusScreenPage.verifyTxnHistory(type, accountType, referenceNumber)