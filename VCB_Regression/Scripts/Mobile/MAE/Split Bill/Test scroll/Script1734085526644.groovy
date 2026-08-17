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
import com.maybank.utils.ListUtils

import internal.GlobalVariable as GlobalVariable

HomePage homePage = HomePage.getPage(HomePage)

CommonPage commonPage = BasePage.getPage(CommonPage)

SplitBillPage splitBillPage = BasePage.getPage(SplitBillPage)

ConfirmationPage confirmationPage = BasePage.getPage(ConfirmationPage)

Secure2uAuthorisationPage secure2uAuthorisationPage = BasePage.getPage(Secure2uAuthorisationPage)

PushNotificationPage pushNotificationPage = BasePage.getPage(PushNotificationPage)

mobileNumberShareBill = ListUtils.parseObjectToList(mobileNumberShareBill)


'Select Tap'
splitBillPage.selectGroupName("Test Group")

