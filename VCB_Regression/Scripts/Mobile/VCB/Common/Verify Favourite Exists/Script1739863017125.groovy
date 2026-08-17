import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase

import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.vcb.common.MobileDriver
import com.vcb.mobile.internal.pages.base.BasePage
import com.vcb.mobile.internal.pages.base.CommonPage
import com.vcb.mobile.internal.pages.base.HomePage

import internal.GlobalVariable as GlobalVariable

HomePage homepage = BasePage.getPage(HomePage)

CommonPage commonPage = BasePage.getPage(CommonPage)

if (!(commonPage.isHomeOrOnboarding())) {
    commonPage.restartApp()
}

WebUI.callTestCase(findTestCase('Mobile/VCB/Common/Login'), [('username') : username, ('password') : password, ('pinCode') : GlobalVariable.G_pinCode
	, ('yourName') : GlobalVariable.G_yourName, ('accountName') : '', ('flowControl') : com.vcb.common.FlowControlEnum.STOP
	, ('failStopTestSuite') : false, ('skipAccountLoginCheck') : false], FailureHandling.STOP_ON_FAILURE)

'Select Function Name in Quick Action'
homepage.selectQuickActions(functionName)

'Navigate to Module'
commonPage.selectTab(commonPage.frmHorizontalScrollNo(), moduleName, GlobalVariable.G_timeoutMedium)

MobileDriver.delay(GlobalVariable.G_timeoutMiniShort)

'Verify Favourite Exist'
commonPage.verifyFavouriteExist(favouriteName)

