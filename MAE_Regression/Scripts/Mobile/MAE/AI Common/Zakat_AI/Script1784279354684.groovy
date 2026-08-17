import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase

import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.maybank.mae.internal.pages.base.BasePage
import com.maybank.mae.internal.pages.base.CommonPage
import com.maybank.mae.internal.pages.base.CommonPage.TestObjectTypeEnum
import com.maybank.mae.internal.pages.base.ConfirmationPage
import com.maybank.mae.internal.pages.base.EnterAmountPage
import com.maybank.mae.internal.pages.base.FavouritesPage
import com.maybank.mae.internal.pages.base.HomePage
import com.maybank.mae.internal.pages.base.PushNotificationPage
import com.maybank.mae.internal.pages.base.Secure2uAuthorisationPage
import com.maybank.mae.internal.pages.base.ShareReceiptPage
import com.maybank.mae.internal.pages.base.StatusScreenPage
import com.maybank.mae.internal.pages.base.paybill.ZakatPage
import com.maybank.utils.ListUtils
import com.maybank.utils.MapUtils
import com.maybank.utils.StringUtils

import internal.GlobalVariable as GlobalVariable

'Login MAE Secure2u'
WebUI.callTestCase(findTestCase('Mobile/MAE/Common/Login Secure2u'), [('idNumber') : idNumber, ('PAN') : PAN, ('password') : password
	, ('username') : username, ('accountName') : accountName], FailureHandling.STOP_ON_FAILURE)

HomePage homePage = BasePage.getPage(HomePage)
CommonPage commonPage = BasePage.getPage(CommonPage)
ZakatPage zakatPage = BasePage.getPage(ZakatPage)
EnterAmountPage enterAmountPage = BasePage.getPage(EnterAmountPage)
ConfirmationPage confirmationPage = BasePage.getPage(ConfirmationPage)
Secure2uAuthorisationPage secure2uAuthorisationPage = BasePage.getPage(Secure2uAuthorisationPage)
StatusScreenPage statusScreenPage = BasePage.getPage(StatusScreenPage)
ShareReceiptPage shareReceiptPage = BasePage.getPage(ShareReceiptPage)
FavouritesPage favouritesPage = BasePage.getPage(FavouritesPage)
PushNotificationPage pushNotificationPage = BasePage.getPage(PushNotificationPage)

Map inputZakat = MapUtils.parseObjectToMap(inputZakat)
Map verifyConfirmData = MapUtils.parseObjectToMap(verifyConfirmData)
List verifyStatusScreenTitleData = ListUtils.parseObjectToList(verifyStatusScreenTitleData)
List verifyStatusScreenDescriptionData = ListUtils.parseObjectToList(verifyStatusScreenDescriptionData)
List shareReceiptsData = ListUtils.parseObjectToList(shareReceiptsData)

String balance = homePage.getBalance(true)

'Navigate to Zakat entry point'
WebUI.callTestCase(findTestCase('Mobile/MAE/Common/Entry point'), [('accessFlow') : accessFlow, ('actionName') : actionName
	, ('accountName') : accountName, ('accountMAEName') : 'MAE', ('menuName') : actionName], FailureHandling.STOP_ON_FAILURE)

if (isRunFav) {
	'Select Zakat Tab'
	zakatPage.selectFavourite(tabName, favName)
} else {
	'Select Zakat Tab'
	zakatPage.selectTabName(tabName)

	'Select Zakat'
	zakatPage.selectNewPayment(paymentName)
}

'Input Product'
zakatPage.inputZakat(inputZakat)

if (!(isRunFav)) {
	'Enter Mobile Number'
	zakatPage.enterMobileNumber(mobileNumber)
}

'Enter amount'
enterAmountPage.enterAmount(amount)

'Confirmation Page'
if (status == 'Insufficient Amount') {
	confirmationPage.confirmSpecialCase(editAmount)
	commonPage.waitLoading()
}

'Verify Confirmation Page Screen'
confirmationPage.confirm(paymentName, verifyConfirmData)

'Authorize Page'
secure2uAuthorisationPage.authorize(status)

'Status Screen'
String refID = statusScreenPage.verifyStatusScreen(verifyStatusScreenTitleData, verifyStatusScreenDescriptionData)

if (status == 'Approve') {
	'Share Receipt'
	shareReceiptPage.shareReceipts(shareReceiptsData)

	if (!(isRunFav)) {
		'Add Favourites'
		favouritesPage.addToFavourites(favName)
	}

	'Done Btn'
	commonPage.tapDoneBtn()

	String amt = StringUtils.convertNoStringToAmount(amount)
	'Message description in Push Notification'
	String messagePN = "Successful payment of $amt to $product. REF:$refID"

	'Verify notification'
	pushNotificationPage.verifyPushNotification(titlePN, messagePN, FailureHandling.STOP_ON_FAILURE, TestObjectTypeEnum.CONTAINS)

	'Check amount'
	String newBalance = homePage.getBalance(true)
	CustomKeywords.'com.maybank.assertions.BaseAssert.notEquals'(balance, newBalance, 'Check Amount')
} else {
	'Done Btn'
	commonPage.tapDoneBtn()
}

return refID
