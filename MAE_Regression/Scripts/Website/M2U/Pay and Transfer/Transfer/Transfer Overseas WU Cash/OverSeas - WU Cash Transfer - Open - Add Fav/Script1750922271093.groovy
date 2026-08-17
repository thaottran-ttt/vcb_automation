import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase

import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
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

  WebUI.callTestCase(findTestCase('Mobile/MAE/Common/Login Secure2u Without Check Logout Stay In Secure2u'), [('username') : username, ('password') : password
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

'select country name'
webTransfer.selectOtherBank(country, country)

'input and choose type of transfer '
webTransfer.inputAmtAndTypeTransfer(amount, typeTransfer)
webCommonPage.waitLoading()

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

WebUIKeyword.delay(GlobalVariable.G_timeoutMedium)

'S2u'
webSecure2uAuthorisationPage.authorizeS2UV5(status)

'Verify Status Screen'
webStatusScreenPage.verifyStatusScreen(verifyStatusScreenData)

////Need to rerun this test case and verify these methods below
//'Verify balance'
//Double newBalance = webHomePage.getTransSummary()
//
//webStatusScreenPage.verifyTransactionSuccess(originBalance, newBalance)

'Add favourite'
webStatusScreenPage.addToFavourite(addToFavouriteData, toastMessageAfterAdd, status)

'Verify transaction history'
webStatusScreenPage.verifyTxnHistory()