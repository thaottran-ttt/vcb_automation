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

import internal.GlobalVariable as GlobalVariable
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile

Map inputPopupTransferData = MapUtils.parseObjectToMap(inputPopupTransferData)
List statusScreenData = ListUtils.parseObjectToList(statusScreenData)

WebHomePage webHomePage = WebBasePage.getPage(WebHomePage)
WebTransfer webTransfer = WebBasePage.getPage(WebTransfer)
WebStatusScreenPage webStatusScreenPage = WebBasePage.getPage(WebStatusScreenPage)
WebSecure2uAuthorisationPage webSecure2uAuthorisationPage = WebBasePage.getPage(WebSecure2uAuthorisationPage)
WebCommonPage webCommonPage = WebBasePage.getPage(WebCommonPage)
webSecure2uAuthorisationPage.setID_NUMBER(idNumber)

WebUI.callTestCase(findTestCase('Test Cases/Mobile/MAE/Common/Login Secure2u Without Check Logout Stay In Secure2u'), [('username') : username, ('password') : password
	, ('idNumber') : idNumber, ('PAN') : PAN], BaseKeyword.defaultFailureHandling)

WebUI.callTestCase(findTestCase('Website/M2U/Common/Login'), [('username') : username, ('password') : password], BaseKeyword.defaultFailureHandling)

'Navigate To Transfer'
webHomePage.navigate(navItem, tabName)

'Get balance'
Double originBalance = webHomePage.getBalance()

Mobile.delay(GlobalVariable.G_timeoutMiniShort)

'Transfer to'
webTransfer.selectTextDynamic(transferTo)

WebUIKeyword.delay(GlobalVariable.G_timeoutMiniShort)

'Input transfer data'
webTransfer.inputPopupTransfer(inputPopupTransferData)

'Input Future Date'
if (isFuture) {
	'Select Tommorrow'
	webTransfer.inputTomorrow(datePickerLabel)
}

if (isRecurring) {
	'Select Recurring Label'
	webTransfer.setRecurring(recurringLabel)
	'Select Tommorrow'
	webTransfer.inputTomorrow(datePickerLabel)
}

'Click Transfer Button'
webTransfer.clickTransferButton()

WebUIKeyword.delay(GlobalVariable.G_timeoutShort * 2)

'S2u'
webSecure2uAuthorisationPage.authorize(status)

WebUIKeyword.delay(GlobalVariable.G_timeoutShort)

'Verify Status Page'
webStatusScreenPage.verifyStatusScreen(statusScreenData)

////Need to rerun this test case and verify these methods below
//'Verify balance'
//Double newBalance = webHomePage.getTransSummary()
//
//webStatusScreenPage.verifyTransactionSuccess(originBalance, newBalance)
//
//webStatusScreenPage.verifyTxnHistory(type)