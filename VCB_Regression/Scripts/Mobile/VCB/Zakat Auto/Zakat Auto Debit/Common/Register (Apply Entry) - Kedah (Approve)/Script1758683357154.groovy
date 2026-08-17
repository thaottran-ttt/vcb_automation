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
import com.vcb.assertions.BaseAssert as BaseAssert
import com.vcb.common.MobileDriver as MobileDriver
import com.vcb.mobile.internal.pages.base.BasePage as BasePage
import com.vcb.mobile.internal.pages.base.CommonPage as CommonPage
import com.vcb.mobile.internal.pages.base.zakat.ZakatPage as ZakatPage
import com.vcb.mobile.internal.pages.base.HomePage as HomePage
import com.vcb.mobile.internal.pages.base.accountpage.AccountsPage as AccountsPage
import com.vcb.mobile.internal.pages.base.PushNotificationPage as PushNotificationPage
import com.kms.katalon.core.util.KeywordUtil
import com.vcb.utils.ListUtils as ListUtils
import com.vcb.utils.StringUtils as StringUtils
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

ZakatPage zakatPage = BasePage.getPage(ZakatPage)
CommonPage commonPage = BasePage.getPage(CommonPage)
HomePage homePage = BasePage.getPage(HomePage)
AccountsPage accountsPage = BasePage.getPage(AccountsPage)
PushNotificationPage pushNotificationPage = BasePage.getPage(PushNotificationPage)

WebUI.callTestCase(findTestCase('Mobile/VCB/Common/Login Secure2u'), [('idNumber') : idNumber, ('PAN') : PAN, ('password') : GlobalVariable.G_password
	, ('username') : username, ('accountName') : accountName, ('flowControl') : com.vcb.common.FlowControlEnum.STOP, ('failStopTestSuite') : false
	, ('yourName') : 'Test'], FailureHandling.STOP_ON_FAILURE)

'Depend Entry Point'
zakatPage.enterDashboard(entry,true)

'Check if user is already registered'
boolean wasRegistered = zakatPage.resetZakatRegistrationIfNeeded()
zakatPage.restartApp()

'Tap Home'
homePage.tapApply()

Mobile.waitForElementPresent(commonPage.lblDynamic("Apply"), GlobalVariable.G_timeoutMedium, FailureHandling.CONTINUE_ON_FAILURE)

'Tap on "SERVICES" tab'
accountsPage.selectTab(tabName)

'Verify entry point'
zakatPage.verifyApplyServices(password)

'Tap "Set Up" button'
zakatPage.tapSetUp()

'Fill "Set Up Zakat" page'
zakatPage.setUpZakatPage(ZakatBody1, Payfrom1, MobileNo1)

'Declare Page'
zakatPage.declarationPage()

'VerifyConfrimation Page'
zakatPage.verifyConfirmationPage(ZakatBody1, Payfrom1, MobileNo1)

'Tap Submit'
zakatPage.tapSubmit()

'Authorisation (S2U)'
zakatPage.registerS2UPage(status,ZakatBody1, Payfrom1, MobileNo1)

'Verify registration status page'
zakatPage.registerstatusPage(status,ZakatBody1, Payfrom1, MobileNo1)

'Verify Push Notification (if approved)'
if (status == "Approve") {
	pushNotificationPage.verifyPushNotification(PNtitle, PNmessage,FailureHandling.CONTINUE_ON_FAILURE)
} else {
	KeywordUtil.logInfo("Status is not 'Approve', skipping push notification verification: " + status)
}

'Verify changes'
zakatPage.restartApp()
zakatPage.enterDashboard(entry,true)
zakatPage.verifyRegistered(status,ZakatBody1, Payfrom1, MobileNo1)

zakatPage.restartApp()