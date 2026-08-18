import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase

import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.vcb.common.BaseKeyword
import com.vcb.m2u.internal.pages.base.WebBasePage
import com.vcb.m2u.internal.pages.base.WebCommonPage
import com.vcb.m2u.internal.pages.base.WebHomePage
import com.vcb.m2u.internal.pages.base.paytransfer.WebTransfer

import internal.GlobalVariable as GlobalVariable

WebHomePage webHomePage = WebBasePage.getPage(WebHomePage)
WebTransfer webTransfer = WebBasePage.getPage(WebTransfer)
WebCommonPage webCommonPage = WebBasePage.getPage(WebCommonPage)

WebUI.callTestCase(findTestCase('null'), [('username') : username, ('password') : password], BaseKeyword.defaultFailureHandling)

'Navigate To Transfer'
webHomePage.navigate(navItem, tabName)

'Transfer to'
webTransfer.selectTextDynamic(transferTo)

WebUI.delay(GlobalVariable.G_timeoutMiniShort)

'Verify Transfer amount'
webTransfer.verifyAmount()
