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
import com.maybank.common.Android

FailureHandling flowControl = FailureHandling.CONTINUE_ON_FAILURE

/*WebUI.callTestCase(findTestCase('Mobile/MAE/Common/Login'), [('appId') : GlobalVariable.G_appPath, ('username') : 'autoali08'
		, ('password') : 'oxhWAdmPKdsI6woWWukVxg==', ('pinCode') : '111111', ('yourName') : 'NandhaGopinath'], FailureHandling.STOP_ON_FAILURE)*/

WebUI.callTestCase(findTestCase('Mobile/MAE/Common/Login Secure2u'), [('idNumber') : idNumber, ('PAN') : PAN, ('password') : GlobalVariable.G_password
	, ('username') : username, ('accountName') : accountName, ('flowControl') : com.maybank.common.FlowControlEnum.STOP, ('failStopTestSuite') : false],
FailureHandling.STOP_ON_FAILURE)

'Verify the user is successfully logged in'
CustomKeywords.'com.maybank.mae.pages.Home.verifyProfileName'(profileName, flowControl)

'Click on Logout'
CustomKeywords.'com.maybank.mae.pages.Home.clickLogout'(flowControl)

'Click Settings button'
CustomKeywords.'com.maybank.mae.pages.Home.clickSettingsButton'(flowControl)

'verify Maybank2u Settings visible and click Maybank2u'
CustomKeywords.'com.maybank.mae.pages.Setting.verifyMaybank2uExist'(flowControl)
CustomKeywords.'com.maybank.mae.pages.Setting.clickMaybank2uExist'(flowControl)
Mobile.delay(20)
//CustomKeywords.'com.maybank.mae.pages.Setting.enterPassword'(password, flowControl)

'Click on DuitNow and verify text Your DuitNow IDs'
CustomKeywords.'com.maybank.mae.pages.Setting.clickDuitNowButton'(flowControl)

'verify DuitNow NRIC Number text is visible'
CustomKeywords.'com.maybank.mae.pages.Setting.verifyNRICNumbertext'(flowControl)

'Switch account for DuitNow NRIC Number'
CustomKeywords.'com.maybank.mae.pages.Setting.switchAccountForNricNumber'(flowControl)

'Autorize Secure2u Approve'
CustomKeywords.'com.maybank.mae.pages.Setting.secure2uforDuitNowSettings'('Approve', flowControl)










