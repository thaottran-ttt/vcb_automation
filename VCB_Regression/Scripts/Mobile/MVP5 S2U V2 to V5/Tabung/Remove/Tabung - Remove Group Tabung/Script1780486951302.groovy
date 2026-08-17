import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.vcb.mobile.internal.pages.base.BasePage
import com.vcb.mobile.internal.pages.base.tabung.TabungPage
import com.vcb.mobile.internal.pages.base.CommonPage as CommonPage
import com.vcb.mobile.internal.pages.base.HomePage
import internal.GlobalVariable as GlobalVariable
import com.vcb.utils.MapUtils

import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import com.kms.katalon.core.model.FailureHandling as FailureHandling

HomePage homePage = BasePage.getPage(HomePage)

TabungPage tabungPage = BasePage.getPage(TabungPage)

CommonPage commonPage = BasePage.getPage(CommonPage)

Map tabungActionMap = MapUtils.parseObjectToMap(tabungMap)
tabungActionMap.put("status", status)
tabungActionMap.put("regex", regex)
tabungActionMap.put("title", statusTitle)
tabungActionMap.put("description", statusDescription)

//commonPage.clearDataAndCache()

WebUI.callTestCase(findTestCase('Mobile/VCB/Common/Login Secure2u'), [('idNumber') : idNumber, ('PAN') : PAN, ('password') : GlobalVariable.G_password
	, ('username') : username, ('accountName') : '', ('flowControl') : com.vcb.common.FlowControlEnum.STOP, ('failStopTestSuite') : false
	, ('yourName') : GlobalVariable.G_yourName], FailureHandling.STOP_ON_FAILURE)

'Navigate to View All Screen'
homePage.selectQuickActions(action)

commonPage.waitLoading()

tabungPage.verifyAndDeletePastTabung(groupTabungName, tabungActionMap)

WebUI.callTestCase(findTestCase('Mobile/MVP5 S2U V2 to V5/Network logs'), [('apiKeyword') : apiKeyword, ('expectedValue') : expectedValue],
	FailureHandling.STOP_ON_FAILURE)

