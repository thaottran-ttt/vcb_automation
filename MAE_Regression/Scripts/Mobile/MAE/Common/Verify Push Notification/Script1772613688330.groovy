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
import com.maybank.mae.internal.pages.base.BasePage
import com.maybank.mae.internal.pages.base.CommonPage
import com.maybank.mae.internal.pages.base.HomePage
import com.maybank.mae.internal.pages.base.NotificationHomePage
import com.maybank.mae.internal.pages.base.PushNotificationPage
import com.maybank.mae.internal.pages.base.CommonPage.TestObjectTypeEnum
import com.maybank.utils.StringUtils as StringUtils

HomePage homePage = BasePage.getPage(HomePage)
NotificationHomePage notificationHomePage = BasePage.getPage(NotificationHomePage)
PushNotificationPage pushNotificationPage = BasePage.getPage(PushNotificationPage)
CommonPage commonPage = BasePage.getPage(CommonPage)

'Back To Home Page'
commonPage.backToHome()

'Tap bell Icon'
homePage.tapNotification()

'Verify Receive Notfication'
String pushNotificationDescription = StringUtils.generateMessage(messagePN, [StringUtils.divideBy100(
	amount), ref])
Mobile.comment(pushNotificationDescription)
notificationHomePage.verifyNotification(titlePN, startMessage, pushNotificationDescription)

Mobile.delay(5)

'Verify Push Notification'
pushNotificationPage.verifyPushNotification(titlePN, pushNotificationDescription,
	FailureHandling.STOP_ON_FAILURE, TestObjectTypeEnum.CONTAINS)

