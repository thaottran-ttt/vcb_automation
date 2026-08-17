import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase

import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.vcb.mobile.internal.pages.base.BasePage
import com.vcb.mobile.internal.pages.base.CommonPage
import com.vcb.mobile.internal.pages.base.ConfirmationPage
import com.vcb.mobile.internal.pages.base.HomePage
import com.vcb.mobile.internal.pages.base.PushNotificationPage
import com.vcb.mobile.internal.pages.base.Secure2uAuthorisationPage
import com.vcb.mobile.internal.pages.base.splitbill.SplitBillPage
import com.vcb.utils.ListUtils

import internal.GlobalVariable as GlobalVariable

HomePage homePage = HomePage.getPage(HomePage)

CommonPage commonPage = BasePage.getPage(CommonPage)

SplitBillPage splitBillPage = BasePage.getPage(SplitBillPage)

ConfirmationPage confirmationPage = BasePage.getPage(ConfirmationPage)

Secure2uAuthorisationPage secure2uAuthorisationPage = BasePage.getPage(Secure2uAuthorisationPage)

PushNotificationPage pushNotificationPage = BasePage.getPage(PushNotificationPage)

mobileNumberShareBill = ListUtils.parseObjectToList(mobileNumberShareBill)

WebUI.callTestCase(findTestCase('Mobile/VCB/Common/Login'), [('idNumber') : idNumber, ('PAN') : PAN, ('password') : GlobalVariable.G_password
        , ('username') : username, ('accountName') : '', ('flowControl') : com.vcb.common.FlowControlEnum.STOP
        , ('failStopTestSuite') : false], FailureHandling.STOP_ON_FAILURE)
'Select PayBills Module at Entry Point'
WebUI.callTestCase(findTestCase('Mobile/VCB/Common/Entry point'), [('accessFlow') : accessFlow, ('actionName') : actionName
        , ('accountName') : accountName, ('menuName') : tranferName], FailureHandling.STOP_ON_FAILURE)

'Select Tap'
splitBillPage.selectTab(tabName)

'Verify pending tab'
commonPage.verifyElementExist(commonPage.lblContainDynamic(pending), GlobalVariable.G_timeoutMedium)

'Select Tap'
splitBillPage.selectTab(tabPast)

'Verify pending tab'
commonPage.verifyElementExist(commonPage.lblContainDynamic(paid), GlobalVariable.G_timeoutMedium)

