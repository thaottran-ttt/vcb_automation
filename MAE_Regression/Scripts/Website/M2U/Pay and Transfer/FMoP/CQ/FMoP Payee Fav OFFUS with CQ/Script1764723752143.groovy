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
import com.maybank.m2u.internal.pages.base.paytransfer.WebPayPage as WebPayPage
import com.maybank.utils.ListUtils as ListUtils
import com.maybank.utils.MapUtils as MapUtils
import internal.GlobalVariable as GlobalVariable


Map payToData = MapUtils.parseObjectToMap(payToData)
List verifyStatusScreenData = ListUtils.parseObjectToList(verifyStatusScreenData)
Map inputTransferData = MapUtils.parseObjectToMap(inputTransferData)

WebCommonPage webCommonPage = WebBasePage.getPage(WebCommonPage)
WebHomePage webHomePage = WebBasePage.getPage(WebHomePage)
WebPayPage WebPayPage = WebBasePage.getPage(WebPayPage)
WebStatusScreenPage webStatusScreenPage = WebBasePage.getPage(WebStatusScreenPage)
WebSecure2uAuthorisationPage webSecure2uAuthorisationPage = WebBasePage.getPage(WebSecure2uAuthorisationPage)

webSecure2uAuthorisationPage.setID_NUMBER(idNumber)

WebUI.callTestCase(findTestCase('Mobile/MAE/Common/Login Secure2u With Secure Question'), [('idNumber') : idNumber, ('PAN') : PAN
		, ('password') : GlobalVariable.G_password, ('username') : username, ('accountName') : '', ('flowControl') : com.maybank.common.FlowControlEnum.STOP
		, ('failStopTestSuite') : false, ('yourName') : GlobalVariable.G_yourName], BaseKeyword.defaultFailureHandling)

WebUI.callTestCase(findTestCase('Website/M2U/Common/Login'), [('username') : username, ('password') : password], BaseKeyword.defaultFailureHandling)

'Navigate To Transfer'
webHomePage.navigate(navItem, tabName)

'get balance'
Double originBalance = webHomePage.getBalance()

Mobile.delay(GlobalVariable.G_timeoutMiniShort)

'Select Type Transfer'
webCommonPage.inputDropdownMap(payToData)

WebUIKeyword.delay(GlobalVariable.G_timeoutShort)

'Select Favourite Name'
WebPayPage.selectFavouriteName(favouriteName)

WebUIKeyword.delay(GlobalVariable.G_timeoutShort)

'Input Transfer Data'
WebPayPage.inputPopupPayTo(inputTransferData)

WebUIKeyword.delay(GlobalVariable.G_timeoutShort)

'S2U'
webSecure2uAuthorisationPage.authoriseWCqQuestion(status, securityanswer)

WebUIKeyword.delay(GlobalVariable.G_timeoutShort)

'Verify Status Screen'
webStatusScreenPage.verifyStatusScreen(verifyStatusScreenData)

