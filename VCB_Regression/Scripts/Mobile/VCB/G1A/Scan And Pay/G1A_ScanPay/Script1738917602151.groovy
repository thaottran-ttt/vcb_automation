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
import com.vcb.common.MobileDriver
import com.vcb.mobile.internal.pages.base.BasePage
import com.vcb.mobile.internal.pages.base.CommonPage
import com.vcb.mobile.internal.pages.base.HomePage
import com.vcb.mobile.internal.pages.base.scanpaypage.ScanPayPage
import com.vcb.mobile.internal.pages.base.PushNotificationPage
import com.vcb.mobile.internal.pages.base.Secure2uAuthorisationPage
import com.vcb.mobile.internal.pages.base.accountpage.AccountsPage as AccountsPage
import com.vcb.mobile.internal.pages.base.accountpage.AccountDetailPage
import com.vcb.mobile.internal.pages.base.ConfirmationPage
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

'Login VCB Secure2u'
WebUI.callTestCase(findTestCase('Mobile/VCB/Common/Login Secure2u'), [('idNumber') : idNumber, ('PAN') : PAN, ('password') : password
		, ('username') : username, ('accountName') : accountName], FailureHandling.STOP_ON_FAILURE)

HomePage homePage = BasePage.getPage(HomePage)

ScanPayPage scanPayPage = BasePage.getPage(ScanPayPage)

CommonPage commonPage = BasePage.getPage(CommonPage)

Secure2uAuthorisationPage s2u = BasePage.getPage(Secure2uAuthorisationPage)

AccountsPage accountsPage = BasePage.getPage(AccountsPage)

AccountDetailPage accountDetailPage = BasePage.getPage(AccountDetailPage)

ConfirmationPage confirmationPage = BasePage.getPage(ConfirmationPage)

PushNotificationPage pushNotificationPage = BasePage.getPage(PushNotificationPage)

'Get Initial balance'
String initBalance = homePage.getBalance()

commonPage.uploadImageFileToDevice(qrCodeImage)

'tap Scan Button'
homePage.tapScanBtn()

'Register QR Code'
scanPayPage.registerQRCode()

'Select QR Code'
scanPayPage.scanQRCode(qrCodeImage)

'Enter Amount'
scanPayPage.enterAmount(amount)

'Verify Paynow'
scanPayPage.verifyPayNow(status)

'Verify Status Screen'
def verify = scanPayPage.statusScreen(status)

MobileDriver.delay(GlobalVariable.G_timeoutMiniShort)

'Verify Share Receipt'
scanPayPage.shareReceipt(verify, status)

scanPayPage.tapBtnDone()

'Check Balance'
String newBalance = homePage.getBalance()

CustomKeywords.'com.vcb.assertions.BaseAssert.notEquals'(initBalance, newBalance, 'Verify Balance')

'Check Notification'
scanPayPage.verifyTransferNotificationMessage(verify[0], amount)
