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
import com.maybank.mae.internal.pages.base.BasePage as BasePage
import com.maybank.mae.internal.pages.base.CommonPage
import com.maybank.mae.internal.pages.base.CommonPage.TestObjectTypeEnum
import com.maybank.mae.internal.pages.base.ConfirmationPage as ConfirmationPage
import com.maybank.mae.internal.pages.base.EnterAmountPage as EnterAmountPage
import com.maybank.mae.internal.pages.base.FavouritesPage as FavouritesPage
import com.maybank.mae.internal.pages.base.HomePage as HomePage
import com.maybank.mae.internal.pages.base.PushNotificationPage as PushNotificationPage
import com.maybank.mae.internal.pages.base.Secure2uAuthorisationPage as Secure2uAuthorisationPage
import com.maybank.mae.internal.pages.base.ShareReceiptPage as ShareReceiptPage
import com.maybank.mae.internal.pages.base.StatusScreenPage as StatusScreenPage
import com.maybank.mae.internal.pages.base.transferpage.TransferPage as TransferPage
import com.maybank.mae.internal.pages.base.paybill.ZakatPage
import com.maybank.utils.ListUtils
import com.maybank.utils.MapUtils
import com.maybank.utils.StringUtils as StringUtils
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

WebUI.callTestCase(findTestCase('Mobile/MAE/Common/Login Secure2u With Secure Question'), [('idNumber') : idNumber, ('PAN') : PAN, ('password') : password
        , ('username') : username, ('accountName') : accountName], FailureHandling.STOP_ON_FAILURE)
HomePage homePage = HomePage.getPage(HomePage)
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

'Enter Answer For Security Question'
CustomKeywords.'com.maybank.mae.pages.SecurityQuestion.inputAnswer'(securityanswer)

'Status Screen'
String refID = statusScreenPage.verifyStatusScreen(verifyStatusScreenTitleData, verifyStatusScreenDescriptionData)

'Done Btn'
 commonPage.tapDoneBtn()


