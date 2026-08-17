import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase

import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.vcb.mobile.internal.pages.base.BasePage
import com.vcb.mobile.internal.pages.base.CommonPage
import com.vcb.mobile.internal.pages.base.ConfirmationPage
import com.vcb.mobile.internal.pages.base.FavouritesPage
import com.vcb.mobile.internal.pages.base.HomePage
import com.vcb.mobile.internal.pages.base.PushNotificationPage
import com.vcb.mobile.internal.pages.base.Secure2uAuthorisationPage
import com.vcb.mobile.internal.pages.base.ShareReceiptPage
import com.vcb.mobile.internal.pages.base.StatusScreenPage
import com.vcb.mobile.internal.pages.base.paybill.PayBillPage
import com.vcb.mobile.internal.pages.base.transferpage.TransferPage
import com.vcb.utils.ListUtils

import internal.GlobalVariable as GlobalVariable

List confirmationData = ListUtils.parseObjectToList(confirmationData)
List statusScreenTitle = ListUtils.parseObjectToList(statusScreenTitle)
List statusScreenDescription = ListUtils.parseObjectToList(statusScreenDescription)
List verifyShareReceiptData = ListUtils.parseObjectToList(verifyShareReceiptData)


WebUI.callTestCase(findTestCase('Mobile/VCB/Common/Login Secure2u'), [('idNumber') : idNumber, ('PAN') : PAN, ('password') : GlobalVariable.G_password
        , ('username') : username, ('accountName') : accountName, ('flowControl') : com.vcb.common.FlowControlEnum.STOP
        , ('failStopTestSuite') : false], FailureHandling.STOP_ON_FAILURE)

HomePage homePage = HomePage.getPage(HomePage)

PayBillPage payBillPage = BasePage.getPage(PayBillPage)

CommonPage commonPage = BasePage.getPage(CommonPage)

TransferPage transferPage = BasePage.getPage(TransferPage)

ConfirmationPage confirmationPage = BasePage.getPage(ConfirmationPage)

Secure2uAuthorisationPage secure2uAuthorisationPage = BasePage.getPage(Secure2uAuthorisationPage)

StatusScreenPage statusScreenPage = BasePage.getPage(StatusScreenPage)

ShareReceiptPage shareReceiptPage = BasePage.getPage(ShareReceiptPage)

FavouritesPage favouritesPage = BasePage.getPage(FavouritesPage)


'Check UI is Bayar bill or Pay bill'
if (commonPage.waitForElementNotPresent(commonPage.lblDynamic(tranferName), GlobalVariable.G_timeoutShort)) {
	tranferName = 'Pay Bills'
}

'Select PayBills Module at Entry Point'
WebUI.callTestCase(findTestCase('Mobile/VCB/Common/Entry point'), [('accessFlow') : accessFlow, ('actionName') : tranferName, ('accountName') : accountName
	, ('menuName') : tranferName], FailureHandling.STOP_ON_FAILURE)


payBillPage.selectFavourites(payBillName)

'Enter Amount'
commonPage.enterNO(amount)

'Verify Confirmation Page'
confirmationPage.verifyConfirmation(confirmationData, textNextPageConfirmation)

'S2u'
secure2uAuthorisationPage.authorize(status)

'Verify Status Screen'
String ref = statusScreenPage.verifyStatusScreen(statusScreenTitle, statusScreenDescription)
Mobile.comment(ref)

shareReceiptPage.shareReceipts(verifyShareReceiptData)


