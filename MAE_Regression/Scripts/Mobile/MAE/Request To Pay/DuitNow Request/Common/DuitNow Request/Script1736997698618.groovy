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
import com.maybank.common.MobileDriver as MobileDriver
import com.maybank.mae.internal.pages.base.BasePage as BasePage
import com.maybank.mae.internal.pages.base.CommonPage as CommonPage
import com.maybank.mae.internal.pages.base.transferpage.TransferPage as TransferPage
import com.maybank.mae.internal.pages.base.HomePage as HomePage
import com.maybank.mae.internal.pages.base.sendandrequestpage.SendAndRequestPage as SendAndRequestPage
import com.maybank.mae.internal.pages.base.sendandrequestpage.DuitNowRequestPage as DuitNowRequestPage

HomePage homePage = BasePage.getPage(HomePage)

CommonPage commonPage = BasePage.getPage(CommonPage)

SendAndRequestPage sendAndRequestPage = BasePage.getPage(SendAndRequestPage)

DuitNowRequestPage dutiNowRequestPage = BasePage.getPage(DuitNowRequestPage)

'Check is on Send & Request screen'
if (!(MobileDriver.waitForElementPresent(sendAndRequestPage.btnDuitNowRequest(), GlobalVariable.G_timeoutMedium))) {
    if (!(homePage.checkIsAccountLogin())) {
        WebUI.callTestCase(findTestCase('Mobile/MAE/Common/Login Secure2u'), [('idNumber') : idNumber, ('PAN') : PAN, ('password') : password
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
dutiNowRequestPage.verifyDuitNowRequestStatusUI(status)