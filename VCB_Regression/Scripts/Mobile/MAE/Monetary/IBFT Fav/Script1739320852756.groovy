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
import com.maybank.common.BaseKeyword as BaseKeyword
import com.maybank.common.MobileDriver as MobileDriver
import com.maybank.mae.internal.pages.base.BasePage as BasePage
import com.maybank.mae.internal.pages.base.CommonPage as CommonPage
import com.maybank.mae.internal.pages.base.ConfirmationPage as ConfirmationPage
import com.maybank.mae.internal.pages.base.EnterAmountPage as EnterAmountPage
import com.maybank.mae.internal.pages.base.HomePage as HomePage
import com.maybank.mae.internal.pages.base.PushNotificationPage as PushNotificationPage
import com.maybank.mae.internal.pages.base.Secure2uAuthorisationPage as Secure2uAuthorisationPage
import com.maybank.mae.internal.pages.base.ShareReceiptPage as ShareReceiptPage
import com.maybank.mae.internal.pages.base.StatusScreenPage as StatusScreenPage
import com.maybank.mae.internal.pages.base.transferpage.RecipientReferencePage as RecipientReferencePage
import com.maybank.mae.internal.pages.base.transferpage.TransferPage as TransferPage
import com.maybank.mae.internal.pages.base.NotificationHomePage as NotificationHomePage
import com.maybank.utils.ListUtils as ListUtils
import com.maybank.utils.MapUtils as MapUtils
import com.maybank.utils.StringUtils as StringUtils
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

Map transferInputData = MapUtils.parseObjectToMap(transferInputData)

Map transferDropdownData = MapUtils.parseObjectToMap(transferDropdownData)

Map favTransferDropdownData = MapUtils.parseObjectToMap(favTransferDropdownData)

List confirmationData = ListUtils.parseObjectToList(confirmationData)

List statusTitle = ListUtils.parseObjectToList(statusTitle)

List statusDescription = ListUtils.parseObjectToList(statusDescription)

List verifyUIShareReceipt = ListUtils.parseObjectToList(verifyUIShareReceipt)

CommonPage commonPage = BasePage.getPage(CommonPage)

HomePage homePage = BasePage.getPage(HomePage)

TransferPage transferPage = BasePage.getPage(TransferPage)

RecipientReferencePage recipientReferencePage = BasePage.getPage(RecipientReferencePage)

EnterAmountPage enterAmountPage = BasePage.getPage(EnterAmountPage)

ConfirmationPage confirmationPage = BasePage.getPage(ConfirmationPage)

Secure2uAuthorisationPage secure2uAuthorisationPage = BasePage.getPage(Secure2uAuthorisationPage)

StatusScreenPage statusScreenPage = BasePage.getPage(StatusScreenPage)

ShareReceiptPage shareReceiptPage = BasePage.getPage(ShareReceiptPage)

PushNotificationPage pushNotificationPage = BasePage.getPage(PushNotificationPage)

NotificationHomePage notificationHomePage = BasePage.getPage(NotificationHomePage)

WebUI.callTestCase(findTestCase('Mobile/MAE/Common/Login Secure2u'), [('idNumber') : idNumber, ('PAN') : PAN, ('password') : password
        , ('username') : username, ('accountName') : accountName, ('flowControl') : com.maybank.common.FlowControlEnum.STOP
        , ('failStopTestSuite') : false, ('yourName') : GlobalVariable.G_yourName], FailureHandling.STOP_ON_FAILURE)
String balance = homePage.getBalance(true)

'Select Transfer Module at QuickActions'
homePage.selectQuickActions(moduleName)

'Select Tab'
transferPage.selectTab(transferTabName, true)

if (shouldValidationFavoriteTransfer) {
    transferPage.selectFavourite(favName)
} else {
    'select new Transfer'
    transferPage.selectNewTransfer(newTransferName)

    'Select Bank'
    transferPage.selectBankName(bankName)

    'Input Transfer Data'
    transferPage.enterAccountOtherBack(transferInputData, transferDropdownData)
}

'enter Amount'
enterAmountPage.enterAmount(amount)

'Recipient Reference'
recipientReferencePage.inputRecipientReference()

if (isFuture) {
    confirmationPage.waitLoadScreen()

    'Future'
    confirmationPage.futureFlow()
}

if (shouldValidationFavoriteTransfer) {
    transferPage.enterAccountOtherBack([:], favTransferDropdownData)
}

'Verify Confirmation'
Mobile.delay(5)

confirmationPage.verifyConfirmation(confirmationData, nextConfirmationPage, insuffAccountName)

'Authorisation'
String statusType = secure2uAuthorisationPage.authorizeS2UV5BioPin(status)

if (statusType.contains(Secure2uAuthorisationPage.AuthorisationType.TAC.toString())) {
    MobileDriver.delay(GlobalVariable.G_timeoutShort)

    'Wait Loading'
    commonPage.waitLoading()

    if (!(statusScreenPage.waitloadStatusScreen())) {
    }
}

'Status Screen'
String ref = statusScreenPage.verifyStatusScreen(statusTitle, statusDescription)

if (shouldValidationShareReciept) {
    shareReceiptPage.shareReceipts(verifyUIShareReceipt)
}

if (shouldValidationAddFavorite) {
    statusScreenPage.addToFavourites(favName)
}

'Tap Done Button'
commonPage.tapDoneBtn()

'Check M2U and Transaction History'
List<String> historyVerificationList = [ref, beneficiaryAccountNumber, m2uHistoryProduct, shareReceiptAmount]

Mobile.comment(historyVerificationList.toString())

WebUI.callTestCase(findTestCase('Mobile/MAE/Common/Reprint Receipt Verification'), [('m2uHistoryProduct') : m2uHistoryProduct
        , ('listShareReceiptVerification') : historyVerificationList], FailureHandling.CONTINUE_ON_FAILURE)

'Verify Push Notification'
WebUI.callTestCase(findTestCase('Mobile/MAE/Common/Verify Push Notification'), [('titlePN') : titlePushNotification, ('startMessage') : 'Welcome to the MAE app.'
        , ('messagePN') : messagePushNotification
        , ('ref') : ref, ('amount') : amount], FailureHandling.STOP_ON_FAILURE)

