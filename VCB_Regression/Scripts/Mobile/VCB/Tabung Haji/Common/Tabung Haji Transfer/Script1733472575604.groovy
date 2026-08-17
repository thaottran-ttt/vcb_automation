import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import com.vcb.common.BaseKeyword
import com.vcb.common.MobileDriver
import com.vcb.mobile.internal.pages.base.BasePage as BasePage
import com.vcb.mobile.internal.pages.base.CommonPage as CommonPage
import com.vcb.mobile.internal.pages.base.ConfirmationPage as ConfirmationPage
import com.vcb.mobile.internal.pages.base.EnterAmountPage as EnterAmountPage
import com.vcb.mobile.internal.pages.base.FavouritesPage as FavouritesPage
import com.vcb.mobile.internal.pages.base.HomePage as HomePage
import com.vcb.mobile.internal.pages.base.PushNotificationPage as PushNotificationPage
import com.vcb.mobile.internal.pages.base.Secure2uAuthorisationPage as Secure2uAuthorisationPage
import com.vcb.mobile.internal.pages.base.ShareReceiptPage as ShareReceiptPage
import com.vcb.mobile.internal.pages.base.StatusScreenPage as StatusScreenPage
import com.vcb.mobile.internal.pages.base.transferpage.TabungHajiPage as TabungHajiPage
import com.vcb.mobile.internal.pages.base.transferpage.TransferPage
import com.vcb.utils.ListUtils
import com.vcb.utils.MapUtils
import com.vcb.utils.StringUtils as StringUtils
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

CommonPage commonPage = BasePage.getPage(CommonPage)

HomePage homePage = BasePage.getPage(HomePage)

TransferPage transferPage = BasePage.getPage(TransferPage)

TabungHajiPage tabungHajiPage = BasePage.getPage(TabungHajiPage)

EnterAmountPage enterAmountPage = BasePage.getPage(EnterAmountPage)

ConfirmationPage confirmationPage = BasePage.getPage(ConfirmationPage)

Secure2uAuthorisationPage secure2u = BasePage.getPage(Secure2uAuthorisationPage)

StatusScreenPage statusScreenPage = BasePage.getPage(StatusScreenPage)

ShareReceiptPage shareReceiptpage = BasePage.getPage(ShareReceiptPage)

FavouritesPage favouritesPage = BasePage.getPage(FavouritesPage)

PushNotificationPage pushNotificationPage = BasePage.getPage(PushNotificationPage)

Map transferData = MapUtils.parseObjectToMap(transferData)
List confirmationData = ListUtils.parseObjectToList(confirmationData)
List statusScreenTitle = ListUtils.parseObjectToList(statusScreenTitle)
List statusScreenDescription = ListUtils.parseObjectToList(statusScreenDescription)
List shareReceiptData = ListUtils.parseObjectToList(shareReceiptData)
List titlePushNotification = ListUtils.parseObjectToList(titlePushNotification)
List messagePushNotification = ListUtils.parseObjectToList(messagePushNotification)

//WebUI.callTestCase(findTestCase('Mobile/VCB/Common/Login Secure2u'), [('idNumber') : idNumber, ('PAN') : PAN, ('password') : password
//        , ('username') : username, ('accountName') : accountName], BaseKeyword.defaultFailureHandling)

'Get Balance'
String balance = homePage.getBalance(true)

'Access Transfer'
WebUI.callTestCase(findTestCase('Mobile/VCB/Common/Entry point'), [('accessFlow') : 'Quick Action', ('actionName') : functionName
        , ('menuName') : functionName], FailureHandling.STOP_ON_FAILURE)

'select TABUNG HAJI'
transferPage.selectTransferToTab(tabName)

if(runFavourite) {
	transferPage.selectFav(favName)
}else {
	'Tap Tabung Haji Button'
	tabungHajiPage.tapTabungHajiButton(newTransferButton)
	
	'Enter From To'
	tabungHajiPage.enterTransferData(transferData)
	
	'Case Transfer Other TH'
	if(otherTabungHajiFlow) {
		tabungHajiPage.enterOtherTabungHaji(accountNoTabungHaji, NRICTabungHaji)
	}
}

'Enter Amount'
enterAmountPage.enterAmount(amount)

'Enter Recipient Reference'
tabungHajiPage.enterRecipientReference(recipientReferenceData)
MobileDriver.delay(GlobalVariable.G_timeoutMiniShort)

'Confirmation Page'
confirmationPage.verifyConfirmation(confirmationData, confirmationDataButton, chooseTransferAccountType)

'S2'
secure2u.authorize(status)

'Screen Page'
statusScreenPage.verifyStatusScreen(statusScreenTitle, statusScreenDescription)

'Validate Share Receipt'
if (shouldValidationShareReceipt) {
    shareReceiptpage.shareReceipts(shareReceiptData)
}

'Verify Add Favourite'
if (shouldValidationAddFavourite) {
    favouritesPage.addToFavourites(favName)
}

'Tap Done'
commonPage.tapDoneBtn()

if (shouldValidationShareReceipt) {
    'Verify Push Notification'
    pushNotificationPage.verifyPushNotification(titlePushNotification, messagePushNotification)

    String newBalance = homePage.getBalance(true)

    'Verify balance after transfer'
    BasePage.verifyBalanceAfterSpending(balance, newBalance, amount)
}

if (shouldValidateM2UHistory) {
	WebUI.callTestCase(findTestCase('Mobile/VCB/Common/Reprint Receipt'), [('product') : M2UHistoryProductName], FailureHandling.STOP_ON_FAILURE)
}
