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
import com.maybank.assertions.BaseAssert as BaseAssert
import com.maybank.mae.internal.pages.base.BasePage as BasePage
import com.maybank.mae.internal.pages.base.CommonPage as CommonPage
import com.maybank.mae.internal.pages.base.ConfirmationPage as ConfirmationPage
import com.maybank.mae.internal.pages.base.FavouritesPage as FavouritesPage
import com.maybank.mae.internal.pages.base.HomePage as HomePage
import com.maybank.mae.internal.pages.base.paybill.PayBillPage as PayBillPage
import com.maybank.mae.internal.pages.base.paybill.PayBillPage.PayBillTestCaseType as PayBillTestCaseType
import com.maybank.mae.internal.pages.base.PushNotificationPage as PushNotificationPage
import com.maybank.mae.internal.pages.base.Secure2uAuthorisationPage as Secure2uAuthorisationPage
import com.maybank.mae.internal.pages.base.Secure2uAuthorisationPage.S2UStatusEnum as S2UStatusEnum
import com.maybank.mae.internal.pages.base.ShareReceiptPage as ShareReceiptPage
import com.maybank.mae.internal.pages.base.StatusScreenPage as StatusScreenPage
import com.maybank.mae.internal.pages.base.transferpage.TransferPage
import com.maybank.utils.ListUtils
import com.maybank.utils.StringUtils as StringUtils
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

List confirmationData = ListUtils.parseObjectToList(confirmationData)
List statusScreenTitle = ListUtils.parseObjectToList(statusScreenTitle)
List statusScreenDescription = ListUtils.parseObjectToList(statusScreenDescription)
List verifyShareReceiptData = ListUtils.parseObjectToList(verifyShareReceiptData)


WebUI.callTestCase(findTestCase('Mobile/MAE/Common/Login Secure2u With Secure Question'), [('idNumber') : idNumber, ('PAN') : PAN, ('password') : password
        , ('username') : username, ('accountName') : accountName, ('flowControl') : com.maybank.common.FlowControlEnum.STOP
        , ('failStopTestSuite') : false, ('yourName') : GlobalVariable.G_yourName], FailureHandling.STOP_ON_FAILURE)

HomePage homePage = HomePage.getPage(HomePage)
PayBillPage payBillPage = BasePage.getPage(PayBillPage)
CommonPage commonPage = BasePage.getPage(CommonPage)
TransferPage transferPage = BasePage.getPage(TransferPage)
ConfirmationPage confirmationPage = BasePage.getPage(ConfirmationPage)
Secure2uAuthorisationPage secure2uAuthorisationPage = BasePage.getPage(Secure2uAuthorisationPage)
StatusScreenPage statusScreenPage = BasePage.getPage(StatusScreenPage)
ShareReceiptPage shareReceiptPage = BasePage.getPage(ShareReceiptPage)
FavouritesPage favouritesPage = BasePage.getPage(FavouritesPage)
PushNotificationPage pushNotificationPage = BasePage.getPage(PushNotificationPage)

String amount = amount

'Get balance'
String balance = homePage.getBalance(true)

'Check UI is Bayar bill or Pay bill'
if (commonPage.waitForElementNotPresent(commonPage.lblDynamic(tranferName), GlobalVariable.G_timeoutShort)) {
	tranferName = 'Pay Bills'
}

'Select PayBills Module at Entry Point'
WebUI.callTestCase(findTestCase('Mobile/MAE/Common/Entry point'), [('accessFlow') : accessFlow, ('actionName') : tranferName, ('accountName') : accountName
	, ('menuName') : tranferName], FailureHandling.STOP_ON_FAILURE)

'Check flow Fav'
if (isRunFav) {
    payBillPage.selectFavourites(payBillName)
} else {
    'Tap Pay Bill Name at PayBills Module'
    payBillPage.tapPayBill()

    'Search Pay Bill Name'
    payBillPage.searchPayBill(payBillName)

    'Enter Biller can input 1 or 2 field'
    payBillPage.setBiller([billerID, membershipType])
}
if (isInsufficientAmount) {
	'Case Insufficient Amount'
	BigInteger balanceNumber = commonPage.convertAmountToNumber(balance)

	BigInteger insufficientAmount = balanceNumber + 100000

	amount = insufficientAmount.toString()
}

'Enter Amount'
commonPage.enterNO(amount)


'Verify Confirmation Page'
confirmationPage.verifyConfirmation(confirmationData, textNextPageConfirmation)

'S2u'
secure2uAuthorisationPage.authorize(status)

'Enter Answer For Security Question'
CustomKeywords.'com.maybank.mae.pages.SecurityQuestion.inputAnswer'(securityanswer)

'Verify Status Screen'
String ref = statusScreenPage.verifyStatusScreen(statusScreenTitle, statusScreenDescription)