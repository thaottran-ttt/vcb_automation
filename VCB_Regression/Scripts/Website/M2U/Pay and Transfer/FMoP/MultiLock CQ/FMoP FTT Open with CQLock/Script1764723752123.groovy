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

import com.maybank.m2u.internal.pages.base.rsapage.WebRsaPage
WebRsaPage webRsaPage = WebBasePage.getPage(WebRsaPage)

Map inputAddNewFavourite = MapUtils.parseObjectToMap(inputAddNewFavourite)
Map drpAddNewFavourite = MapUtils.parseObjectToMap(drpAddNewFavourite)
Map drpTransferDetailData = MapUtils.parseObjectToMap(drpTransferDetailData)
Map inputBankDetailData = MapUtils.parseObjectToMap(inputBankDetailData)
Map drpBankDetailData = MapUtils.parseObjectToMap(drpBankDetailData)
Map inputReceiptDetailData = MapUtils.parseObjectToMap(inputReceiptDetailData)
Map drpReceiptDetailData = MapUtils.parseObjectToMap(drpReceiptDetailData)

Map payToData = MapUtils.parseObjectToMap(payToData)
Map editFavourite = MapUtils.parseObjectToMap(editFavourite)
List verifyStatusScreenData = ListUtils.parseObjectToList(verifyStatusScreenData)
String favouriteName = inputAddNewFavourite.get("Nickname")
String favouriteEditName = editFavourite.get("Nickname")

WebCommonPage webCommonPage = WebBasePage.getPage(WebCommonPage)
WebHomePage webHomePage = WebBasePage.getPage(WebHomePage)
WebTransfer webTransfer = WebBasePage.getPage(WebTransfer)
WebStatusScreenPage webStatusScreenPage = WebBasePage.getPage(WebStatusScreenPage)
WebSecure2uAuthorisationPage webSecure2uAuthorisationPage = WebBasePage.getPage(WebSecure2uAuthorisationPage)
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

'Select Type "Overseas Transfer"'
webCommonPage.inputDropdownMap(payToData)

WebUIKeyword.delay(GlobalVariable.G_timeoutMedium)

'select country name'
webTransfer.selectOtherBank(item, country)

'input and choose type of transfer '
webTransfer.inputAmtAndTypeTransfer(amount, typeTransfer)

webCommonPage.inputDropdownMap(drpAddNewFavourite)

'Add receipt bank detail'
webTransfer.addReceiptBankDetail(inputBankDetailData, drpBankDetailData)

'Add receipt detail'
webTransfer.addReceiptDetail(inputReceiptDetailData, drpReceiptDetailData)

'Add transfer detail'
webTransfer.addTransferDetailOpen(drpTransferDetailData)

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

