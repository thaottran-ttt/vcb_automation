import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase

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
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile

import internal.GlobalVariable



Map fromToData = MapUtils.parseObjectToMap(fromToData)
Map inputFavouriteTransferData = MapUtils.parseObjectToMap(inputFavouriteTransferData)
List statusScreenData = ListUtils.parseObjectToList(statusScreenData)
List favouriteName = ListUtils.parseObjectToList(favouriteName)

WebCommonPage webCommonPage = WebBasePage.getPage(WebCommonPage)
WebHomePage webHomePage = WebBasePage.getPage(WebHomePage)
WebTransfer webTransfer = WebBasePage.getPage(WebTransfer)
WebStatusScreenPage webStatusScreenPage = WebBasePage.getPage(WebStatusScreenPage)
WebSecure2uAuthorisationPage webSecure2uAuthorisationPage = WebBasePage.getPage(WebSecure2uAuthorisationPage)

WebUI.callTestCase(findTestCase('Test Cases/Mobile/MAE/Common/Login Secure2u Without Check Logout Stay In Secure2u'), [('username') : username, ('password') : password
	, ('idNumber') : idNumber, ('PAN') : PAN], BaseKeyword.defaultFailureHandling)

WebUI.callTestCase(findTestCase('Website/M2U/Common/Login'), [('username') : username, ('password') : password], BaseKeyword.defaultFailureHandling)

'Navigate To Transfer'
webHomePage.navigate(navItem, tabName)

webCommonPage.waitLoading()

'Get balance'
Double originBalance = webHomePage.getBalance()
Mobile.delay(GlobalVariable.G_timeoutMiniShort)

'Select Type Transfer'
webTransfer.selectOtherTransfer(fromToData)

'Select Many Favorites'
for(int i = 0; i< favouriteName.size(); i++) {
	'Select Favourite Name'
	webTransfer.selectFavouriteName(favouriteName[i])
	WebUIKeyword.delay(GlobalVariable.G_timeoutMiniShort)
	'Input Transfer Data'
	webTransfer.inputPopupTransfer(inputFavouriteTransferData)
	
	'Click Term Radio'
	webTransfer.clickAgreeTerm()
	
	'Tap Transfer'
	webTransfer.clickAddEditAnotherTransferButton()
}

'Click Transfer Button'
webTransfer.clickMultiTransferButton()

WebUIKeyword.delay(GlobalVariable.G_timeoutShort)

'S2u'
webSecure2uAuthorisationPage.authorize(status)

'Verify Status Screen'
webCommonPage.verifyAnyElementListVisible(statusScreenData)

//'Verify balance'
//Double newBalance = webHomePage.getTransSummary()
//
//webStatusScreenPage.verifyTransactionSuccess(originBalance, newBalance)
//
//webStatusScreenPage.verifyTxnHistory(type)