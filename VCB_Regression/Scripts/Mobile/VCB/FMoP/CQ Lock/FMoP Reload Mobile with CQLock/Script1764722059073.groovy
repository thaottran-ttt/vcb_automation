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
import com.vcb.mobile.internal.pages.base.HomePage as HomePage
import com.vcb.mobile.internal.pages.base.PushNotificationPage as PushNotificationPage
import com.vcb.mobile.internal.pages.base.Secure2uAuthorisationPage as Secure2uAuthorisationPage
import com.vcb.mobile.internal.pages.base.ShareReceiptPage as ShareReceiptPage
import com.vcb.mobile.internal.pages.base.StatusScreenPage as StatusScreenPage
import com.vcb.mobile.internal.pages.base.reloadpage.ReloadPage as ReloadPage
import com.vcb.utils.ListUtils as ListUtils
import com.vcb.utils.StringUtils as StringUtils
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

HomePage homePage = BasePage.getPage(HomePage)
ConfirmationPage confirmationPage = BasePage.getPage(ConfirmationPage)
Secure2uAuthorisationPage secure2uAuthorisationPage = BasePage.getPage(Secure2uAuthorisationPage)
StatusScreenPage statusScreenPage = BasePage.getPage(StatusScreenPage)
ShareReceiptPage shareReceiptPage = BasePage.getPage(ShareReceiptPage)
PushNotificationPage pushNotificationPage = BasePage.getPage(PushNotificationPage)
CommonPage commonPage = BasePage.getPage(CommonPage)

List verifyUIConfirmationPage = ListUtils.parseObjectToList(verifyUIConfirmationPage)
List statusTitle = ListUtils.parseObjectToList(statusTitle)
List statusDescription = ListUtils.parseObjectToList(statusDescription)
List verifyUIShareReceipt = ListUtils.parseObjectToList(verifyUIShareReceipt)

WebUI.callTestCase(findTestCase('Mobile/VCB/Common/Login Secure2u With Secure Question'), [('idNumber') : idNumber, ('PAN') : PAN, ('password') : password
        , ('username') : username, ('accountName') : accountName, ('flowControl') : com.vcb.common.FlowControlEnum.STOP
        , ('failStopTestSuite') : false, ('yourName') : GlobalVariable.G_yourName], FailureHandling.STOP_ON_FAILURE)

String balance = homePage.getBalance(true)

WebUI.callTestCase(findTestCase('Mobile/VCB/Common/Entry point'), [('accessFlow') : accessFlow, ('actionName') : actionName
        , ('accountName') : accountName, ('menuName') : actionName], BaseKeyword.defaultFailureHandling)

'select provider'
ReloadPage.instance.selectProvider(provider)

'Enter Mobile'
ReloadPage.instance.enterPhoneNumber(phoneNumber)

'Choose Reload Amount'
ReloadPage.instance.selectAmount(amount)

if (editAmount != '') {
    'Wait to next screen'
    commonPage.waitLoading()

    'Edit amount'
    ReloadPage.instance.editAmount(editAmount)
}

'Confirmation'
confirmationPage.verifyConfirmation(verifyUIConfirmationPage, nextConfirmationPage, accountName)
MobileDriver.delay(GlobalVariable.G_timeoutMiniShort)
MobileDriver.takeScreenshot()

'Enter Incorrect Answer For Security Question 1st time'
CustomKeywords.'com.vcb.mobile.pages.SecurityQuestion.inputAnswer'("incorrect")

MobileDriver.delay(GlobalVariable.G_timeoutLong)

'Enter Incorrect Answer For Security Question 2nd time'
CustomKeywords.'com.vcb.mobile.pages.SecurityQuestion.inputAnswer'("incorrect")

MobileDriver.delay(GlobalVariable.G_timeoutLong)

'Enter Incorrect Answer For Security Question 3rd time'
CustomKeywords.'com.vcb.mobile.pages.SecurityQuestion.inputAnswer'("incorrect")

MobileDriver.delay(GlobalVariable.G_timeoutLong)

MobileDriver.takeScreenshot()

'Status Screen'
String ref = statusScreenPage.verifyStatusScreen(statusTitle, statusDescription)
