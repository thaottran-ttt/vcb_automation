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
import com.vcb.mobile.internal.pages.base.settingpage.SettingPage as SettingPage
import com.vcb.mobile.internal.pages.base.BasePage as BasePage
import com.vcb.mobile.internal.pages.base.CommonPage as CommonPage
import com.vcb.mobile.internal.pages.base.HomePage as HomePage
import com.vcb.mobile.internal.pages.base.Secure2uAuthorisationPage

FailureHandling flowControl = FailureHandling.STOP_ON_FAILURE
String TRANSFER = "Transfer"
String DNS = "DuitNow Services"
String DUITNOWID = "Your DuitNow IDs"
String CONFIRM_DETAIL = "Please confirm your DuitNow details"
String SUCCESSFUL_NOTI = "You’ve successfully linked your account to DuitNow"
String NRIC_NUMBER = "NRIC Number"
String SWITCH_ACC = "Switch account"
String AGREE = "Agree and Confirm"
String SUCCESSFULLY_SWITCH = "DuitNow account successfully switched"
String DEREGISTER = "Deregister DuitNow"
String CONFIRM = "Confirm"
String DUITNOW_REMOVED = "DuitNow ID removed"
String SELECT_ACCOUNT = "Select account"
String DISABLE = "Disable Temporary"
String DEACTIVE_SUCCESS = "DuitNow deactivated"
String ENABLE = "Enable"
String REACTIVATE_SUCCESS = "DuitNow ID successfully reactivated"

HomePage homePage = HomePage.getPage(HomePage)
CommonPage commonPage = BasePage.getPage(CommonPage)
SettingPage settingPage = BasePage.getPage(SettingPage)
Secure2uAuthorisationPage secure2uAuthorisationPage = BasePage.getPage(Secure2uAuthorisationPage)


WebUI.callTestCase(findTestCase('Mobile/VCB/Common/Login Secure2u'), [('idNumber') : idNumber, ('PAN') : PAN, ('password') : GlobalVariable.G_password
        , ('username') : username, ('accountName') : '', ('flowControl') : com.vcb.common.FlowControlEnum.STOP, ('failStopTestSuite') : false
        , ('yourName') : GlobalVariable.G_yourName], FailureHandling.STOP_ON_FAILURE)


'Click Settings button'
homePage.clickSettingsButton(flowControl)

'Verify the user is successfully logged in'
homePage.verifyProfileName(profileName, flowControl)
//CustomKeywords.'com.vcb.mobile.pages.Setting.enterPassword'(password, flowControl)

'Click Pay and Transfer'
settingPage.tapPaynTransfer(flowControl)

'Tap Transfer'
commonPage.verifyElementVisible(commonPage.lblDynamic(TRANSFER), GlobalVariable.G_timeoutMedium, flowControl)
commonPage.tapText(TRANSFER, GlobalVariable.G_timeoutMedium, flowControl)

'Tap Duitnow Services'
commonPage.verifyElementVisible(commonPage.lblDynamic(DNS), GlobalVariable.G_timeoutMedium, flowControl)
commonPage.tapText(DNS, GlobalVariable.G_timeoutMedium, flowControl)

'Tap NRIC number'
commonPage.verifyElementVisible(commonPage.lblDynamic(NRIC_NUMBER), GlobalVariable.G_timeoutMedium, flowControl)
Mobile.tap(commonPage.lblDynamic(NRIC_NUMBER), GlobalVariable.G_timeoutMedium, flowControl)

'Tap Disable Temporary'
commonPage.verifyElementVisible(commonPage.lblDynamic(DISABLE), GlobalVariable.G_timeoutMedium, flowControl)
Mobile.tap(commonPage.lblDynamic(DISABLE), GlobalVariable.G_timeoutMedium, flowControl)

'Tap Confirm'
commonPage.verifyElementVisible(commonPage.lblDynamic(CONFIRM), GlobalVariable.G_timeoutMedium, flowControl)
commonPage.tapText(CONFIRM, GlobalVariable.G_timeoutMedium, flowControl)

Mobile.delay(5)

'Authorisation'
secure2uAuthorisationPage.authorize(status)

commonPage.verifyElementVisible(commonPage.lblDynamic(DEACTIVE_SUCCESS), GlobalVariable.G_timeoutMedium, flowControl)

// Reactivate

'Tap NRIC number'
commonPage.verifyElementVisible(commonPage.lblDynamic(NRIC_NUMBER), GlobalVariable.G_timeoutMedium, flowControl)
Mobile.tap(commonPage.lblDynamic(NRIC_NUMBER), GlobalVariable.G_timeoutMedium, flowControl)

'Tap Enable'
commonPage.verifyElementVisible(commonPage.lblDynamic(ENABLE), GlobalVariable.G_timeoutMedium, flowControl)
Mobile.tap(commonPage.lblDynamic(ENABLE), GlobalVariable.G_timeoutMedium, flowControl)

'Authorisation'
secure2uAuthorisationPage.authorize(status)

commonPage.verifyElementVisible(commonPage.lblDynamic(REACTIVATE_SUCCESS), GlobalVariable.G_timeoutMedium, flowControl)

