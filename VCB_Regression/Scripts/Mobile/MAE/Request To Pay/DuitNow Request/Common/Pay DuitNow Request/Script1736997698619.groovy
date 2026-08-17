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
import com.maybank.mae.internal.pages.base.CommonPage
import com.maybank.mae.internal.pages.base.ConfirmationPage
import com.maybank.mae.internal.pages.base.transferpage.TransferPage
import com.maybank.utils.ListUtils
import com.maybank.mae.internal.pages.base.HomePage
import com.maybank.mae.internal.pages.base.Secure2uAuthorisationPage
import com.maybank.mae.internal.pages.base.ShareReceiptPage
import com.maybank.mae.internal.pages.base.StatusScreenPage
import com.maybank.mae.internal.pages.base.sendandrequestpage.DuitNowRequestPage as DuitNowRequestPage


List confirmationData = ListUtils.parseObjectToList(confirmationData)
List statusScreenData = ListUtils.parseObjectToList(statusScreenData)
List shareReceiptData = ListUtils.parseObjectToList(shareReceiptData)

HomePage homePage = BasePage.getPage(HomePage)

CommonPage commonPage = BasePage.getPage(CommonPage)

DuitNowRequestPage duitNowRequestPage = BasePage.getPage(DuitNowRequestPage)

ConfirmationPage confirmationPage = BasePage.getPage(ConfirmationPage)

Secure2uAuthorisationPage secure2uAuthorisationPage = BasePage.getPage(Secure2uAuthorisationPage)

StatusScreenPage statusScreenPage = BasePage.getPage(StatusScreenPage)

ShareReceiptPage shareReceiptPage = BasePage.getPage(ShareReceiptPage)

WebUI.callTestCase(findTestCase('Mobile/MAE/Common/Login Secure2u'), [('idNumber') : idNumber, ('PAN') : PAN, ('password') : password
            , ('username') : username, ('accountName') : accountName], FailureHandling.STOP_ON_FAILURE)

'Select Send & Request'
homePage.selectQuickActions(functionName)

'Select Item Duitnow Type'
duitNowRequestPage.selectDuitRequestItem(duitnowType)

'Verify item detail'
duitNowRequestPage.verifyDuitNowRequestItemDetail()

'Tap Pay Now'
duitNowRequestPage.tapTapNow()

'Delay'
MobileDriver.delay(GlobalVariable.G_timeoutShort)

'Verify Confirmation Page'
confirmationPage.verifyConfirmation(confirmationData, duitNowRequestPage.PAY_NOW, insufficientBalanceAccountName)

if(insufficientBalanceAccountName) {
	confirmationPage.verifyElementVisible(commonPage.lblDynamic(insufficientBalanceMsg), GlobalVariable.G_timeoutLong)
	return
}
'Authorize'
secure2uAuthorisationPage.authorize(status)

'Verify Status Screen'
statusScreenPage.verifyStatusScreen(statusScreenData, [])

if(status == Secure2uAuthorisationPage.S2UStatusEnum.Approve.toString()) {
	shareReceiptPage.shareReceipts(shareReceiptData)
}

'Tap Done'
commonPage.tapDoneBtn()

MobileDriver.delay(1)

'Back to home'
commonPage.tapBack()