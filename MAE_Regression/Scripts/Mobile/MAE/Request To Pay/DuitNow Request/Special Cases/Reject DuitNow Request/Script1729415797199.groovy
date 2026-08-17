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
import com.maybank.mae.internal.pages.base.CommonPage.TestObjectTypeEnum
import com.maybank.mae.internal.pages.base.transferpage.TransferPage
import com.maybank.utils.ListUtils
import com.maybank.mae.internal.pages.base.HomePage
import com.maybank.mae.internal.pages.base.PushNotificationPage
import com.maybank.mae.internal.pages.base.sendandrequestpage.DuitNowRequestPage as DuitNowRequestPage

List popupData = ListUtils.parseObjectToList(popupData)

HomePage homePage = BasePage.getPage(HomePage)

CommonPage commonPage = BasePage.getPage(CommonPage)

DuitNowRequestPage duitNowRequestPage = BasePage.getPage(DuitNowRequestPage)

PushNotificationPage pushNotificationPage = BasePage.getPage(PushNotificationPage)

WebUI.callTestCase(findTestCase('Mobile/MAE/Common/Login Secure2u'), [('idNumber') : idNumber, ('PAN') : PAN, ('password') : password
            , ('username') : username, ('accountName') : accountName], FailureHandling.STOP_ON_FAILURE)

'Select Send & Request'
homePage.selectQuickActions(functionName)

'Select Item Duitnow Type'
duitNowRequestPage.selectDuitRequestItem(duitnowType)

'Verify item detail'
duitNowRequestPage.verifyDuitNowRequestItemDetail()

'Verify item 3Dot'
duitNowRequestPage.selectItemInThreeDot(itemNameInThreeDot)

MobileDriver.delay(GlobalVariable.G_timeoutMiniShort)

'Verify verify Reject Request'
duitNowRequestPage.verifyPopupThreeDot(popupData, successMsg)

'Verify Notification'
pushNotificationPage.verifyPushNotification(pushNotificationTitle, pushNotificationDescription, BasePage.defaultFailureHandling, TestObjectTypeEnum.CONTAINS)