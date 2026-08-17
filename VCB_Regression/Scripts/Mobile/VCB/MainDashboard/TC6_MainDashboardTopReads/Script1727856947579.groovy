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
import com.vcb.common.Android


FailureHandling flowControl = FailureHandling.CONTINUE_ON_FAILURE

/*WebUI.callTestCase(findTestCase('Mobile/VCB/Common/Login'), [('appId') : GlobalVariable.G_appPath, ('username') : 'autoali08'
		, ('password') : 'oxhWAdmPKdsI6woWWukVxg==', ('pinCode') : '111111', ('yourName') : 'NandhaGopinath'], FailureHandling.STOP_ON_FAILURE)*/

WebUI.callTestCase(findTestCase('Mobile/VCB/Common/Login Secure2u'), [('idNumber') : idNumber, ('PAN') : PAN, ('password') : GlobalVariable.G_password
	, ('username') : username, ('accountName') : accountName, ('flowControl') : com.vcb.common.FlowControlEnum.STOP, ('failStopTestSuite') : false],
FailureHandling.STOP_ON_FAILURE)

Mobile.delay(30)
'Verify the user is successfully logged in'
CustomKeywords.'com.vcb.mobile.pages.Home.verifyProfileName'(profileName, flowControl)

'Click on Logout'
CustomKeywords.'com.vcb.mobile.pages.Home.clickLogout'(flowControl)

'Scroll to TopReads, tap on Top reads'
CustomKeywords.'com.vcb.common.Android.scroll'(0.70f, 0.30f)
CustomKeywords.'com.vcb.common.Android.scroll'(0.70f, 0.30f)
CustomKeywords.'com.vcb.common.Android.scroll'(0.70f, 0.30f)
CustomKeywords.'com.vcb.common.Android.scroll'(0.70f, 0.30f)
CustomKeywords.'com.vcb.common.Android.scroll'(0.70f, 0.30f)
CustomKeywords.'com.vcb.mobile.pages.Home.verifyTopReadsVisible'(flowControl)
CustomKeywords.'com.vcb.mobile.pages.Home.tapTopReadsLink'(flowControl)

'Like an article, verify the count is increased after hitting like in the Top reads page'
CustomKeywords.'com.vcb.mobile.pages.Home.readLikeCount'(flowControl)
CustomKeywords.'com.vcb.mobile.pages.Home.tapHeartSymbolButton'(flowControl)
CustomKeywords.'com.vcb.mobile.pages.Home.verifyLikeCountIsIncreamentedbyOne'(flowControl)

'Click back button and verify the incremented count is reflected in the home page'
CustomKeywords.'com.vcb.mobile.pages.Home.verifyLikeCountIsIncreamentedbyOneInHomePageTopReads'(flowControl)



