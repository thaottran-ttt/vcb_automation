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
import com.maybank.mae.internal.pages.base.transferpage.TransferPage
import com.maybank.utils.ListUtils
import com.maybank.mae.internal.pages.base.HomePage
import com.maybank.mae.internal.pages.base.PushNotificationPage
import com.maybank.mae.internal.pages.base.sendandrequestpage.DuitNowRequestPage
import com.maybank.mae.internal.pages.base.settingpage.SettingDuitnowPage
import com.maybank.mae.internal.pages.base.settingpage.SettingPage

List viewDuitNowData = ListUtils.parseObjectToList(viewDuitNowData)
List uiPopupData = ListUtils.parseObjectToList(uiPopupData)
HomePage homePage = BasePage.getPage(HomePage)

CommonPage commonPage = BasePage.getPage(CommonPage)

DuitNowRequestPage duitNowRequestPage = BasePage.getPage(DuitNowRequestPage)

PushNotificationPage pushNotificationPage = BasePage.getPage(PushNotificationPage)

SettingPage settingPage = BasePage.getPage(SettingPage)

SettingDuitnowPage settingDuitnowPage = BasePage.getPage(SettingDuitnowPage)

WebUI.callTestCase(findTestCase('Mobile/MAE/Common/Login Secure2u'), [('idNumber') : idNumber, ('PAN') : PAN, ('password') : password
            , ('username') : username, ('accountName') : accountName], FailureHandling.STOP_ON_FAILURE)

'Tap Setting Icon'
homePage.tapBtnSetting()

'M2U'
settingPage.tapItemMaybank2u(m2uItem)

commonPage.waitLoading()

'tap DuitNow Blocked List'
settingDuitnowPage.tapDuitNowBlockedList()

'Verify View Blocked Proxy in DuitNow setting'
settingDuitnowPage.verifyElements(viewDuitNowData, commonPage.lblDynamicPath(), GlobalVariable.G_timeoutMedium)

'Unblock proxy from DuitNow Setting'
settingDuitnowPage.unlockDuitNow(unblickDuitNowName, uiPopupData)

'Verify Success Msg'
settingDuitnowPage.verifyElementVisible(commonPage.lblContainDynamic(successMsg), GlobalVariable.G_timeoutMedium)
