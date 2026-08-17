import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase

import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.maybank.mae.internal.pages.base.BasePage
import com.maybank.mae.internal.pages.base.CommonPage
import com.maybank.mae.internal.pages.base.ConfirmationPage
import com.maybank.mae.internal.pages.base.HomePage
import com.maybank.mae.internal.pages.base.PushNotificationPage
import com.maybank.mae.internal.pages.base.Secure2uAuthorisationPage
import com.maybank.mae.internal.pages.base.splitbill.SplitBillPage

import internal.GlobalVariable as GlobalVariable

HomePage homePage = HomePage.getPage(HomePage)

CommonPage commonPage = BasePage.getPage(CommonPage)

SplitBillPage splitBillPage = BasePage.getPage(SplitBillPage)

ConfirmationPage confirmationPage = BasePage.getPage(ConfirmationPage)

Secure2uAuthorisationPage secure2uAuthorisationPage = BasePage.getPage(Secure2uAuthorisationPage)

PushNotificationPage pushNotificationPage = BasePage.getPage(PushNotificationPage)

WebUI.callTestCase(findTestCase('Mobile/MAE/Common/Login Secure2u'), [('idNumber') : idNumber, ('PAN') : PAN, ('password') : GlobalVariable.G_password
        , ('username') : username, ('accountName') : '', ('flowControl') : com.maybank.common.FlowControlEnum.STOP
        , ('failStopTestSuite') : false], FailureHandling.STOP_ON_FAILURE)
'Select PayBills Module at Entry Point'
WebUI.callTestCase(findTestCase('Mobile/MAE/Common/Dynamic Entry point'), [('accessFlow') : accessFlow, ('actionName') : actionName
        , ('accountName') : accountName, ('menuName') : tranferName], FailureHandling.STOP_ON_FAILURE)


commonPage.backToHome()