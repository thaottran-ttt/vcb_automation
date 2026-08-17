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
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys
import com.vcb.common.MobileDriver as MobileDriver
import com.vcb.mobile.internal.pages.base.BasePage as BasePage
import com.vcb.mobile.internal.pages.base.CommonPage as CommonPage
import com.vcb.mobile.internal.pages.base.transferpage.TransferPage as TransferPage
import com.vcb.mobile.internal.pages.base.HomePage as HomePage
import com.vcb.mobile.internal.pages.base.sendandrequestpage.SendAndRequestPage as SendAndRequestPage
import com.vcb.mobile.internal.pages.base.sendandrequestpage.DuitNowRequestPage as DuitNowRequestPage
import com.vcb.mobile.internal.pages.base.StatusScreenPage as StatusScreenPage
import com.vcb.mobile.internal.pages.base.accountpage.AccountDetailPage

HomePage homePage = BasePage.getPage(HomePage)
CommonPage commonPage = BasePage.getPage(CommonPage)
SendAndRequestPage sendAndRequestPage = BasePage.getPage(SendAndRequestPage)
DuitNowRequestPage dutiNowRequestPage = BasePage.getPage(DuitNowRequestPage)
StatusScreenPage statusScreenPage = BasePage.getPage(StatusScreenPage)
AccountDetailPage accountDetailPage = BasePage.getPage(AccountDetailPage)

'Check is on Send & Request screen'
if (!(MobileDriver.waitForElementPresent(sendAndRequestPage.btnDuitNowRequest(), GlobalVariable.G_timeoutMedium))) {
    if (!(homePage.checkIsAccountLogin())) {
        WebUI.callTestCase(findTestCase('Mobile/VCB/Common/Login Secure2u'), [('idNumber') : idNumber, ('PAN') : PAN, ('password') : password
                , ('username') : username, ('accountName') : accountName], FailureHandling.STOP_ON_FAILURE)
    }
    
    'Select Send & Request'
    homePage.selectQuickActions('Send & Request')

    'Check enter PIN'
    commonPage.checkEnterPIN()
}
'Wait For PENDING tab present'
Mobile.waitForElementPresent(commonPage.lblDynamic('PENDING'), timeout)

Mobile.delay(GlobalVariable.G_timeoutShort)

'Select DuitNow Request'
MobileDriver.tap(sendAndRequestPage.btnDuitNowRequest(), timeout)

'Check enter password'
commonPage.checkEnterPassword(password, GlobalVariable.G_timeoutMedium)

'Select Request via'
dutiNowRequestPage.selectRequestVia(requestType, timeout)

'Input data for by request'
dutiNowRequestPage.enterRequestData(requestType, dropdownValue, inputTextValue)

'Input Recipient Reference'
Random rand = new Random()
recipientReference = ('Recipient Ref ' + Integer.toString(rand.nextInt(1000)))
dutiNowRequestPage.inputRecipientReference(recipientReference)

'Select Continue button'
dutiNowRequestPage.tapContinueButton()

'Input Request Amount'
dutiNowRequestPage.inputRequestAmount(amount)

Mobile.delay(5)

'Select Send Now button'
dutiNowRequestPage.tapSendNowButton()

'Select on Approve button'
dutiNowRequestPage.selectApproval(status)

'Verify status'
def RefID = statusScreenPage.verifyStatusScreen(statusTitle, statusDescription)

'Back to entry point'
dutiNowRequestPage.backToEntryPoint()
commonPage.backToHome()

'Check M2U and Transaction History'
List<String> historyVerificationList = [RefID]

'Verify History'
WebUI.callTestCase(findTestCase('Mobile/VCB/Common/Reprint Receipt Verification'), [('m2uHistoryProduct') : m2uHistoryProduct
		, ('listShareReceiptVerification') : historyVerificationList], FailureHandling.CONTINUE_ON_FAILURE)

'Verify notification'
commonPage.backToHome()
dutiNowRequestPage.verifySentRequestNotificationMessage(recipientReference, Double.parseDouble(amount) / 100, 'Vcb2u: DuitNow Request',
	'You\'ve sent a DuitNow Request')

