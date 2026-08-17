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
import com.maybank.common.MobileDriver as MobileDriver
import com.maybank.mae.internal.pages.base.accountpage.AccountsPage as AccountsPage
import com.maybank.mae.internal.pages.base.BasePage as BasePage
import com.maybank.mae.internal.pages.base.CommonPage as CommonPage
import com.maybank.mae.internal.pages.base.ConfirmationPage as ConfirmationPage
import com.maybank.mae.internal.pages.base.fixeddepositpage.FixedDepositPage as FixedDepositPage
import com.maybank.mae.internal.pages.base.HomePage as HomePage
import com.maybank.mae.internal.pages.base.PushNotificationPage as PushNotificationPage
import com.maybank.mae.internal.pages.base.Secure2uAuthorisationPage as Secure2uAuthorisationPage
import com.maybank.mae.internal.pages.base.ShareReceiptPage as ShareReceiptPage
import com.maybank.mae.internal.pages.base.StatusScreenPage as StatusScreenPage
import com.maybank.utils.MapUtils as MapUtils
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

WebUI.callTestCase(findTestCase('Mobile/MAE/Common/Login Secure2u'), [('idNumber') : idNumber, ('PAN') : PAN, ('password') : password
        , ('username') : username, ('accountName') : '', ('flowControl') : com.maybank.common.FlowControlEnum.STOP, ('failStopTestSuite') : false
        , ('yourName') : 'Test'], FailureHandling.STOP_ON_FAILURE)

HomePage homePage = BasePage.getPage(HomePage)

AccountsPage accountsPage = BasePage.getPage(AccountsPage)

FixedDepositPage fixedDepositPage = BasePage.getPage(FixedDepositPage)

ShareReceiptPage shareReceiptPage = BasePage.getPage(ShareReceiptPage)

PushNotificationPage pushNotificationPage = BasePage.getPage(PushNotificationPage)

ConfirmationPage confirmationPage = BasePage.getPage(ConfirmationPage)

Secure2uAuthorisationPage secure2uAuthorisationPage = BasePage.getPage(Secure2uAuthorisationPage)

StatusScreenPage statusScreenPage = BasePage.getPage(StatusScreenPage)

CommonPage commonPage = BasePage.getPage(CommonPage)

dropdownMap = MapUtils.parseObjectToMap(dropdownMap)

homePage.tapBtnAccounts()

accountsPage.selectTab(tabName)

fixedDepositPage.manageFD(fdName, fdMenu, dropdownMap)

'Verify Confirm ManageFD'
confirmationPage.confirmManageFD()

'Verify Authorize'
secure2uAuthorisationPage.authorize(status)

'Verify Status'
statusScreenPage.verifyTransactionStatus(status, futureType)

'Verify Share Receipt'
shareReceiptPage.shareReceipts([])

'Back to Home'
commonPage.tapDoneBtn()

fixedDepositPage.detailFDBackToFD()

homePage.tapHomeMenu()

'Verify Push Notification'
pushNotificationPage.verifyNotificationManageFD()

