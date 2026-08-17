import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase

import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.vcb.common.BaseKeyword
import com.vcb.common.WebUIKeyword
import com.vcb.m2u.internal.pages.base.HTMLTag
import com.vcb.m2u.internal.pages.base.WebBasePage
import com.vcb.m2u.internal.pages.base.WebCommonPage
import com.vcb.m2u.internal.pages.base.WebHomePage
import com.vcb.common.WebUIKeyword
import com.vcb.m2u.internal.pages.base.WebSecure2uAuthorisationPage
import com.vcb.m2u.internal.pages.base.WebStatusScreenPage
import com.vcb.m2u.internal.pages.base.accountpage.WebASNBPage
import com.vcb.utils.ListUtils
import com.vcb.utils.MapUtils

import internal.GlobalVariable as GlobalVariable

List statusScreenData = ListUtils.parseObjectToList(statusScreenData)
Map transferFrom = MapUtils.parseObjectToMap(transferFrom)
WebCommonPage webCommonPage = WebBasePage.getPage(WebCommonPage)
WebHomePage webHomePage = WebBasePage.getPage(WebHomePage)
WebASNBPage webASNBPage = WebBasePage.getPage(WebASNBPage)
WebStatusScreenPage webStatusScreenPage = WebBasePage.getPage(WebStatusScreenPage)
WebSecure2uAuthorisationPage webSecure2uAuthorisationPage = WebBasePage.getPage(WebSecure2uAuthorisationPage)
webSecure2uAuthorisationPage.setID_NUMBER(idNumber)

WebUI.callTestCase(findTestCase('Test Cases/Website/M2U/Pay and Transfer/Transfer/ASNB_Transfer/common/NavigateToTransfer'), [('username') : username, ('password') : password
	, ('idNumber') : idNumber, ('PAN') : PAN], BaseKeyword.defaultFailureHandling)

'Select ASNB Accounts'
webASNBPage.selectASNBAccounts(asnbAccount)

'Click Add New ASNB Favourite'
webASNBPage.clickButtonAddNewASNBFavourite(addFaroriteButton)

'Fill favorite form'
webASNBPage.fillFavoriteForm(nickname, product, idType, memberId, asnbNumber)

'Click Add button'
webASNBPage.clickButtonAddavourite()
WebUIKeyword.delay(GlobalVariable.G_timeoutMiniShort)

'S2u'
webSecure2uAuthorisationPage.authorize(status)
WebUIKeyword.delay(GlobalVariable.G_timeoutMiniShort)

'Verify Status Screen'
webASNBPage.verifyAddFavoriteSuccessfully(successfulMessage)

