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

FailureHandling flowControl = FailureHandling.CONTINUE_ON_FAILURE

WebUI.callTestCase(findTestCase('Mobile/MAE/Common/Login Secure2u'), [('idNumber') : idNumber, ('PAN') : PAN, ('password') : GlobalVariable.G_password
        , ('username') : username, ('accountName') : accountName, ('flowControl') : com.maybank.common.FlowControlEnum.STOP
        , ('failStopTestSuite') : false], FailureHandling.STOP_ON_FAILURE)

'Verify the user is successfully logged in'
CustomKeywords.'com.maybank.mae.pages.Home.verifyProfileName'(profileName, flowControl)

'Navigate to Settings >> Maybank2U>> Secure2U'
CustomKeywords.'com.maybank.mae.pages.Home.clickSettingsButton'(flowControl)

CustomKeywords.'com.maybank.mae.pages.Setting.verifyMaybank2uExist'(flowControl)

CustomKeywords.'com.maybank.mae.pages.Setting.clickMaybank2uExist'(flowControl)

Mobile.delay(15)

CustomKeywords.'com.maybank.mae.pages.Setting.tapSecure2u'(flowControl)

'Tap the toggle button to disable the Secure2U, verify toastbar message of Secure2U disable'
CustomKeywords.'com.maybank.mae.pages.Setting.disableSecure2U'(flowControl)

'Register Secure2u and Activate Secure2U'
CustomKeywords.'com.maybank.mae.pages.Setting.tapSecure2u'(flowControl)
WebUI.callTestCase(findTestCase('Mobile/MAE/S2U Activation/Secure2U Registration'), [('idNumber') : idNumber, ('PAN') : PAN
	, ('password') : password, ('flowControl') : flowControl, ('failStopTestSuite') : false], FailureHandling.STOP_ON_FAILURE)




