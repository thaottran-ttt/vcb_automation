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
import com.maybank.common.BaseKeyword
import com.maybank.common.MobileDriver
import com.maybank.mae.internal.pages.base.BasePage as BasePage
import com.maybank.mae.internal.pages.base.CommonPage as CommonPage
import com.maybank.mae.internal.pages.base.ConfirmationPage as ConfirmationPage
import com.maybank.mae.internal.pages.base.EnterAmountPage as EnterAmountPage
import com.maybank.mae.internal.pages.base.FavouritesPage as FavouritesPage
import com.maybank.mae.internal.pages.base.HomePage as HomePage
import com.maybank.mae.internal.pages.base.PushNotificationPage as PushNotificationPage
import com.maybank.mae.internal.pages.base.Secure2uAuthorisationPage as Secure2uAuthorisationPage
import com.maybank.mae.internal.pages.base.ShareReceiptPage as ShareReceiptPage
import com.maybank.mae.internal.pages.base.StatusScreenPage
import com.maybank.mae.internal.pages.base.transferpage.RecipientReferencePage
import com.maybank.mae.internal.pages.base.transferpage.TabungHajiPage as TabungHajiPage
import com.maybank.mae.internal.pages.base.transferpage.TransferPage
import com.maybank.utils.MapUtils
import com.maybank.utils.StringUtils as StringUtils
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

CommonPage commonPage = BasePage.getPage(CommonPage)

HomePage homePage = BasePage.getPage(HomePage)

TransferPage transferPage = BasePage.getPage(TransferPage)

TabungHajiPage tabungHajiPage = BasePage.getPage(TabungHajiPage)

EnterAmountPage enterAmountPage = BasePage.getPage(EnterAmountPage)

ConfirmationPage confirmationPage = BasePage.getPage(ConfirmationPage)

RecipientReferencePage recipientReferencePage = BasePage.getPage(RecipientReferencePage)

Map transferData = MapUtils.parseObjectToMap(transferData)

WebUI.callTestCase(findTestCase('Mobile/MAE/Common/Login Secure2u'), [('idNumber') : idNumber, ('PAN') : PAN, ('password') : password
        , ('username') : username, ('accountName') : accountName], BaseKeyword.defaultFailureHandling)

'Access Transfer'
WebUI.callTestCase(findTestCase('Mobile/MAE/Common/Entry point'), [('accessFlow') : 'Quick Action', ('actionName') : functionName
        , ('menuName') : functionName], FailureHandling.STOP_ON_FAILURE)

'select TABUNG HAJI'
transferPage.selectTransferToTab(tabName)

'Tap Tabung Haji Button'
tabungHajiPage.tapTabungHajiButton(newTransferButton)

'Enter From To'
tabungHajiPage.enterTransferData(transferData)

'Enter Amount'
enterAmountPage.enterAmount(amount)

'Verify Recipient Reference'
recipientReferencePage.verifySpecialCaseRecipientReference(specialCharsMsg)
MobileDriver.delay(GlobalVariable.G_timeoutMiniShort)

'Verify Edit Amount'
confirmationPage.editAmount(editAmount)
