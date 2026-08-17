import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase

import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.vcb.mobile.internal.pages.base.BasePage
import com.vcb.mobile.internal.pages.base.CommonPage
import com.vcb.mobile.internal.pages.base.HomePage
import com.vcb.mobile.internal.pages.base.pfmpage.PFMPage
import com.vcb.utils.ListUtils
import com.vcb.utils.MapUtils

import internal.GlobalVariable as GlobalVariable

HomePage homePage = BasePage.getPage(HomePage)
CommonPage commonPage = BasePage.getPage(CommonPage)
PFMPage pfmPage = BasePage.getPage(PFMPage)

List expectedValue = ListUtils.parseObjectToList(expectedValue)
List summary = ListUtils.parseObjectToList(summary)
Map category = MapUtils.parseObjectToMap(category)

WebUI.callTestCase(findTestCase('Mobile/VCB/Common/Login'), [('username') : username, ('password') : password, ('pinCode') : GlobalVariable.G_pinCode
        , ('yourName') : GlobalVariable.G_yourName, ('accountName') : accountName, ('flowControl') : com.vcb.common.FlowControlEnum.STOP
        , ('failStopTestSuite') : false], FailureHandling.STOP_ON_FAILURE)

'Tap Expenses'
homePage.tapMenu(Expenses)
commonPage.waitLoading()

'Verify Summary or Check Balance'
pfmPage.verifySummaryOrCheckBalance(summary)

'Tap to Add Expenses'
pfmPage.tapToAddExpense()

'Enter amount'
pfmPage.enterAmount(amount)

'Select Category'
pfmPage.selectCategory(category)

'Select Payment'
pfmPage.choosePayment()

'Tap to Add Expenses'
pfmPage.tapToAddExpense()

'Verify message'
Mobile.delay(3)
commonPage.takeScreenshot()
commonPage.verifyElementVisible(commonPage.lblContainDynamic(message), GlobalVariable.G_timeoutMedium, FailureHandling.CONTINUE_ON_FAILURE)


'Apply Filter'
pfmPage.applyFilter(filterValue, expectedValue)