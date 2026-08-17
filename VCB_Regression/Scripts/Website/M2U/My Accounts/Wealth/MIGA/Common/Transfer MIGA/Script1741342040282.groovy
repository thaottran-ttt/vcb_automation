import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase

import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.maybank.common.BaseKeyword
import com.maybank.common.WebUIKeyword
import com.maybank.m2u.internal.pages.base.WebBasePage
import com.maybank.m2u.internal.pages.base.WebCommonPage
import com.maybank.m2u.internal.pages.base.WebHomePage
import com.maybank.m2u.internal.pages.base.WebSecure2uAuthorisationPage
import com.maybank.m2u.internal.pages.base.WebStatusScreenPage
import com.maybank.m2u.internal.pages.base.accountpage.WebWealthPage
import com.maybank.utils.ListUtils
import com.maybank.utils.MapUtils

import internal.GlobalVariable as GlobalVariable

List statusScreenData = ListUtils.parseObjectToList(statusScreenData)
List toastMessageAfterAddFavorite = ListUtils.parseObjectToList(toastMessageAfterAddFavorite)

Map addToFavouriteData = MapUtils.parseObjectToMap(addToFavouriteData)
Map inputDropdownTransferTo = MapUtils.parseObjectToMap(inputDropdownTransferTo)
Map inputTextTransferTo = MapUtils.parseObjectToMap(inputTextTransferTo)

WebCommonPage webCommonPage = WebBasePage.getPage(WebCommonPage)
WebHomePage webHomePage = WebBasePage.getPage(WebHomePage)
WebWealthPage webWealthPage = WebBasePage.getPage(WebWealthPage)
WebStatusScreenPage webStatusScreenPage = WebBasePage.getPage(WebStatusScreenPage)
WebSecure2uAuthorisationPage webSecure2uAuthorisationPage = WebBasePage.getPage(WebSecure2uAuthorisationPage)
webSecure2uAuthorisationPage.setID_NUMBER(idNumber)

WebUI.callTestCase(findTestCase('Mobile/MAE/Common/Login Secure2u Without Check Logout'), [('username') : username, ('password') : password
	, ('idNumber') : idNumber, ('PAN') : PAN], BaseKeyword.defaultFailureHandling)

WebUI.callTestCase(findTestCase('Website/M2U/Common/Login'), [('username') : username, ('password') : password], BaseKeyword.defaultFailureHandling)

'Navigate To Transfer'
webHomePage.navigate(navItem, tabName)

'Select Account'
WebUIKeyword.scrollToTestObject(webCommonPage.lblNormalizeSpace(accountType))
webCommonPage.clickTestObjectWithWait(webCommonPage.lblNormalizeSpace(accountType))

WebUIKeyword.delay(GlobalVariable.G_timeoutShort)
'Get Header info'
Map<String,String> headerValues = webWealthPage.getMIGAiHeaderValues()

'Click Item in Three Dot'
webWealthPage.clickIteminThreeDot(itemNameThreeDot)

WebUIKeyword.delay(GlobalVariable.G_timeoutMiniShort)

'Input Transfer Gold Quantity'
webWealthPage.inputGoldQuantityTransfer(goldGram)

'Input Transfer From'
webWealthPage.inputData(inputTextTransferTo, inputDropdownTransferTo)

'Calucate'
webCommonPage.clickTestObjectWithWait(webCommonPage.lblNormalizeSpace(webWealthPage.CALCULATE))

'Click Transfer button'
webCommonPage.clickTransferButton()

'Verify S2U'
webSecure2uAuthorisationPage.authorize(status)

WebUIKeyword.delay(GlobalVariable.G_timeoutShort)

'Verify Status Screen'
webStatusScreenPage.verifyStatusScreen(statusScreenData)

'Add Favourite'
if(validateAddFavourite) {
	webStatusScreenPage.addToFavourite(addToFavouriteData, toastMessageAfterAddFavorite)
}

WebUIKeyword.scrollToTestObject(webCommonPage.lblNormalizeSpace(navItem))
'Navigate To My Account'
webHomePage.navigate(navItem, tabName)

'Select Account'
WebUIKeyword.scrollToTestObject(webCommonPage.lblNormalizeSpace(accountType),GlobalVariable.G_timeoutMedium,FailureHandling.CONTINUE_ON_FAILURE)
webCommonPage.clickTestObjectWithWait(webCommonPage.lblNormalizeSpace(accountType))

'Get Header info'
Map<String,String> newHeaderValues = webWealthPage.getMIGAiHeaderValues()
webWealthPage.compareMIGAiHeaderValues(headerValues, newHeaderValues)
