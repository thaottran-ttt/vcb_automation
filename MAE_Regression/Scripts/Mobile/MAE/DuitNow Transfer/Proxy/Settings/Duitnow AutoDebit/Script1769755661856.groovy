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
import com.maybank.mae.internal.pages.base.settingpage.SettingPage as SettingPage
import com.maybank.mae.internal.pages.base.BasePage as BasePage
import com.maybank.mae.internal.pages.base.CommonPage as CommonPage
import com.maybank.mae.internal.pages.base.HomePage as HomePage
import com.maybank.mae.internal.pages.base.Secure2uAuthorisationPage

FailureHandling flowControl = FailureHandling.STOP_ON_FAILURE

HomePage homePage = HomePage.getPage(HomePage)
CommonPage commonPage = BasePage.getPage(CommonPage)
SettingPage settingPage = BasePage.getPage(SettingPage)
Secure2uAuthorisationPage secure2uAuthorisationPage = BasePage.getPage(Secure2uAuthorisationPage)


WebUI.callTestCase(findTestCase('Mobile/MAE/Common/Login Secure2u'), [('idNumber') : idNumber, ('PAN') : PAN, ('password') : GlobalVariable.G_password
        , ('username') : username, ('accountName') : '', ('flowControl') : com.maybank.common.FlowControlEnum.STOP, ('failStopTestSuite') : false
        , ('yourName') : GlobalVariable.G_yourName], FailureHandling.STOP_ON_FAILURE)

'Click Settings button'
homePage.clickSettingsButton(flowControl)

'Verify the user is successfully logged in'
homePage.verifyProfileName(profileName, flowControl)
//CustomKeywords.'com.maybank.mae.pages.Setting.enterPassword'(password, flowControl)

'Click Pay and Transfer'
settingPage.tapPaynTransfer(flowControl)

'Tap Transfer'
commonPage.verifyElementVisible(commonPage.lblDynamic("Transfer"), GlobalVariable.G_timeoutMedium, flowControl)
commonPage.tapText("Transfer", GlobalVariable.G_timeoutMedium, flowControl)

'Tap Duitnow Services'
commonPage.verifyElementVisible(commonPage.lblDynamic("DuitNow Services"), GlobalVariable.G_timeoutMedium, flowControl)
commonPage.tapText("DuitNow Services", GlobalVariable.G_timeoutMedium, flowControl)
commonPage.waitLoading()

'Tap DuitNow AutoDebit'
commonPage.verifyElementVisible(commonPage.lblDynamic("DuitNow AutoDebit"), GlobalVariable.G_timeoutMedium, flowControl)
commonPage.tapText("DuitNow AutoDebit", GlobalVariable.G_timeoutMedium, flowControl)
