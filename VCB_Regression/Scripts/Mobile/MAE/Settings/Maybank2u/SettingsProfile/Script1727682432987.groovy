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
import com.maybank.mae.internal.pages.base.BasePage as BasePage
import com.maybank.mae.internal.pages.base.CommonPage
import com.maybank.mae.internal.pages.base.Secure2uAuthorisationPage
import com.maybank.mae.internal.pages.base.StatusScreenPage
import com.maybank.utils.ListUtils
import com.maybank.utils.MapUtils
import com.maybank.utils.StringUtils

FailureHandling flowControl = FailureHandling.CONTINUE_ON_FAILURE

CommonPage commonPage = BasePage.getPage(CommonPage)
Secure2uAuthorisationPage secure2uAuthorisationPage = BasePage.getPage(Secure2uAuthorisationPage)
StatusScreenPage statusScreenPage = BasePage.getPage(StatusScreenPage)

List approveTitle = ListUtils.parseObjectToList(approveTitle)
List rejectTitle = ListUtils.parseObjectToList(rejectTitle)
List expiredTitle = ListUtils.parseObjectToList(expiredTitle)
List approveDesc = ListUtils.parseObjectToList(approveDesc)
List rejectDesc = ListUtils.parseObjectToList(rejectDesc)
List expiredDesc = ListUtils.parseObjectToList(expiredDesc)

WebUI.callTestCase(findTestCase('Mobile/MAE/Common/Login Secure2u'), [('idNumber') : idNumber, ('PAN') : PAN, ('password') : GlobalVariable.G_password
	, ('username') : username, ('accountName') : accountName, ('flowControl') : com.maybank.common.FlowControlEnum.STOP, ('failStopTestSuite') : false],
FailureHandling.STOP_ON_FAILURE)

'Verify the user is successfully logged in'
CustomKeywords.'com.maybank.mae.pages.Home.verifyProfileName'(profileName, flowControl)

'Click on Logout'
CustomKeywords.'com.maybank.mae.pages.Home.clickLogout'(flowControl)

'Click Settings button'
CustomKeywords.'com.maybank.mae.pages.Home.clickSettingsButton'(flowControl)

'verify Settings->Profile tab visible and click Maybank2u'
CustomKeywords.'com.maybank.mae.pages.Setting.clickProfileButton'(flowControl)

'Enter Pin number'
CustomKeywords.'com.maybank.common.MobileDriver.enterPinNo'(pinCode)

'Verify text Update Profile text exist'
CustomKeywords.'com.maybank.mae.pages.Setting.verifyTextUpdateProfile'(flowControl)

'Verify Username'
CustomKeywords.'com.maybank.mae.pages.Home.verifyProfileName'(profileName, flowControl)

'Verify Phumner Masked'
CustomKeywords.'com.maybank.mae.pages.Home.verifyMaskingPhoneNumber'(flowControl)

'Verify Referral Code'
CustomKeywords.'com.maybank.mae.pages.Home.verifyReferralCode'(referralCode, flowControl)

'Verify profile picture can be updated successfully'
CustomKeywords.'com.maybank.mae.pages.Setting.verifyProfilePictureUpdatedSuccessfully'(flowControl)

def scenarioData = [
	[status: "Reject",  statusTitle: rejectTitle, statusDesc: rejectDesc],
	[status: "Expired", statusTitle: expiredTitle, statusDesc: expiredDesc],
	[status: "Approve", statusTitle: approveTitle, statusDesc: approveDesc]
]

for (def data : scenarioData) {

	'Verify PhoneNumber can be updated successfully'
	CustomKeywords.'com.maybank.mae.pages.Setting.verifyPhoneNumberUpdatedSuccessfully'(flowControl)

	'Authorisation'
	secure2uAuthorisationPage.authorize(data.status)

	'Status Screen'
	statusScreenPage.verifyStatusScreen(data.statusTitle, data.statusDesc)

	Mobile.takeScreenshot()
	
	'Tap Done Button'
	commonPage.tapDoneBtn()
}

'Verify email id can be updated successfully'
CustomKeywords.'com.maybank.mae.pages.Setting.verifyEmailIdUpdatedSuccessfully'(flowControl)
