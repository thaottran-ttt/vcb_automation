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
import com.vcb.common.MobileDriver as MobileDriver
import com.vcb.mobile.internal.pages.base.BasePage as BasePage
import com.vcb.mobile.internal.pages.base.CommonPage as CommonPage
import com.vcb.mobile.internal.pages.base.HomePage as HomePage
import com.vcb.mobile.internal.pages.base.scanpaypage.ScanPayPage as ScanPayPage
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

WebUI.callTestCase(findTestCase('Mobile/VCB/Common/Login Secure2u'), [('idNumber') : idNumber, ('PAN') : PAN, ('password') : password
        , ('username') : username, ('accountName') : '', ('flowControl') : com.vcb.common.FlowControlEnum.STOP, ('failStopTestSuite') : false
        , ('yourName') : 'Test'], FailureHandling.STOP_ON_FAILURE)

HomePage homePage = BasePage.getPage(HomePage)

ScanPayPage scanPayPage = BasePage.getPage(ScanPayPage)

CommonPage commonPage = BasePage.getPage(CommonPage)

String balance = homePage.getBalance(true)

'tap Scan Button'
homePage.tapScanBtn()

'Register QRCode'
scanPayPage.registerQRCode()

'Select QR Code'
scanPayPage.selectQRCode(qrCodeImage)

'Enter Amount'
scanPayPage.enterAmount(amount)

'Verify Paynow'
scanPayPage.verifyPayNow()

'Verify Status Screen'
def verify = scanPayPage.statusScreen(status)

MobileDriver.delay(GlobalVariable.G_timeoutMiniShort)

'Verify Share Receipt'
scanPayPage.shareReceipt(verify, status)

scanPayPage.tapBtnDone()

'Check Balance'
String newBalance = homePage.getBalance(true)

CustomKeywords.'com.vcb.assertions.BaseAssert.notEquals'(balance, newBalance, 'Verify Balance')

'Check Notification'
scanPayPage.verifyTransferNotificationMessage(verify[0], amount)

