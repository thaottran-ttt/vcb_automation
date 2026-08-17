import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase

import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.vcb.mobile.internal.pages.base.BasePage
import com.vcb.mobile.internal.pages.base.CommonPage
import com.vcb.mobile.internal.pages.base.HomePage
import com.vcb.mobile.internal.pages.base.tabung.TabungPage

import internal.GlobalVariable


HomePage homePage = BasePage.getPage(HomePage)

CommonPage commonPage = BasePage.getPage(CommonPage)

TabungPage tabungPage = BasePage.getPage(TabungPage)


WebUI.callTestCase(findTestCase('Mobile/VCB/Common/Login Secure2u'), [('idNumber') : idNumber, ('PAN') : PAN, ('password') : password
        , ('username') : username, ('accountName') : '', ('flowControl') : com.vcb.common.FlowControlEnum.STOP, ('failStopTestSuite') : false
        , ('yourName') : 'Test'], FailureHandling.STOP_ON_FAILURE)


'Navigate to View All Screen'
homePage.selectQuickActions(action)

'Navigate to Tabung Screen'
homePage.selectQuickActions(subAction)

'Select Tab Boosters'
commonPage.selectTab(commonPage.frmHorizontalScrollNo(1), tabName, GlobalVariable.G_timeoutMedium)

'Spare Change'
tabungPage.processScanAndSave()

commonPage.backToHome()