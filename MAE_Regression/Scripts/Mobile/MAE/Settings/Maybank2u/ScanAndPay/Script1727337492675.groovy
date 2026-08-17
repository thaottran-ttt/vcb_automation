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

String secure2U = 'Approve'
String S2u = null
Mobile.delay(30)

'Verify the user is successfully logged in'
CustomKeywords.'com.maybank.mae.pages.Home.verifyProfileName'(profileName, flowControl)

'Click on Logout'
CustomKeywords.'com.maybank.mae.pages.Home.clickLogout'(flowControl)

'Click Settings button'
CustomKeywords.'com.maybank.mae.pages.Home.clickSettingsButton'(flowControl)

'verify Maybank2u Settings visible and click Maybank2u'
CustomKeywords.'com.maybank.mae.pages.Setting.verifyMaybank2uExist'(flowControl)
CustomKeywords.'com.maybank.mae.pages.Setting.clickMaybank2uExist'(flowControl)
CustomKeywords.'com.maybank.mae.pages.Setting.enterPassword'(password, flowControl)












/*'Verify Secure2u Authorisation Screen'
CustomKeywords.'com.maybank.mae.pages.Secure2u.verifySecure2uAuthorisationText'(flowControl)
CustomKeywords.'com.maybank.mae.pages.Secure2u.verifyUpdateScanAndPayLimitText'(flowControl)
CustomKeywords.'com.maybank.mae.pages.Secure2u.verifyDailyLimitText'(flowControl)
CustomKeywords.'com.maybank.mae.pages.Secure2u.verifyDailyLimitValue'(flowControl)
CustomKeywords.'com.maybank.mae.pages.Secure2u.verifyCumulativeLimitText'(flowControl)
CustomKeywords.'com.maybank.mae.pages.Secure2u.verifyCumulativeLimitValue'(flowControl)
CustomKeywords.'com.maybank.mae.pages.Secure2u.DateAndTimeText'(flowControl)
CustomKeywords.'com.maybank.mae.pages.Secure2u.DateAndTimeValue'(flowControl)
CustomKeywords.'com.maybank.mae.pages.Secure2u.verifyRejectButton'(flowControl)
CustomKeywords.'com.maybank.mae.pages.Secure2u.verifyApproveButton'(flowControl)



'Able to view ATM cashout toggle'
CustomKeywords.'com.maybank.mae.pages.Setting.tapATMCashOut'(flowControl)
CustomKeywords.'com.maybank.mae.pages.Setting.verifyATMCashOutDescription'(flowControl)
CustomKeywords.'com.maybank.mae.pages.Setting.tapTermsAndConditions'(flowControl)
CustomKeywords.'com.maybank.mae.pages.Setting.tapConfirmButton'(flowControl)

'Able to view Secure2u toggle'
CustomKeywords.'com.maybank.mae.pages.Setting.tapSecure2u'(flowControl)
CustomKeywords.'com.maybank.mae.pages.Setting.verifyDisableSecure2u'(flowControl)
CustomKeywords.'com.maybank.mae.pages.Setting.tapCancel'(flowControl)

'Able to view DuitNow Setting and access DuitNow dashboard'
CustomKeywords.'com.maybank.mae.pages.Setting.tapDuitNow'(flowControl)
CustomKeywords.'com.maybank.mae.pages.Setting.verifyDuitNowIds'(flowControl)
CustomKeywords.'com.maybank.mae.pages.Setting.verifyOthers'(flowControl)
CustomKeywords.'com.maybank.mae.pages.Setting.tapBackButton'(flowControl)

'Able to view and access Auto Debit for Zakat Dashboard'
CustomKeywords.'com.maybank.mae.pages.Setting.tapAutoDebitforZakat'(flowControl)
CustomKeywords.'com.maybank.mae.pages.Setting.verifyZakatSimpananAndPelaburan'(flowControl)
CustomKeywords.'com.maybank.mae.pages.Setting.verifyAutoDebit'(flowControl)
CustomKeywords.'com.maybank.mae.pages.Setting.verifyAmount'(flowControl)
CustomKeywords.'com.maybank.mae.pages.Setting.verifyPayFrom'(flowControl)
CustomKeywords.'com.maybank.mae.pages.Setting.verifyZakatBody'(flowControl)
CustomKeywords.'com.maybank.mae.pages.Setting.verifyMobileNumber'(flowControl)
CustomKeywords.'com.maybank.mae.pages.Setting.tapBackButton'(flowControl)

'Able to view and access Change password Dashboard'
CustomKeywords.'com.maybank.mae.pages.Setting.tapChangePassword'(flowControl)
CustomKeywords.'com.maybank.mae.pages.Setting.verifyEnterCurrentPassword'(flowControl)
CustomKeywords.'com.maybank.mae.pages.Setting.verifyEnterNewPassword'(flowControl)
CustomKeywords.'com.maybank.mae.pages.Setting.verifyConfirmNewPassword'(flowControl)
CustomKeywords.'com.maybank.mae.pages.Setting.verifyContinue'(flowControl)
CustomKeywords.'com.maybank.mae.pages.Setting.tapBackButton'(flowControl)

'Able to view and access Change Security Question'
CustomKeywords.'com.maybank.mae.pages.Setting.tapChangeSecurityQuestion'(flowControl)
CustomKeywords.'com.maybank.mae.pages.Setting.verifyChangeSecurityQuestionDescription'(flowControl)
CustomKeywords.'com.maybank.mae.pages.Setting.verifyChangeSecurityQuestion123'(flowControl)
CustomKeywords.'com.maybank.mae.pages.Setting.tapBackButton'(flowControl)

'Able to view and access Kill Switch'
CustomKeywords.'com.maybank.mae.pages.Setting.tapKillSwitch'(flowControl)
CustomKeywords.'com.maybank.mae.pages.Setting.verifyKillSwitchDescription'(flowControl)
CustomKeywords.'com.maybank.mae.pages.Setting.verifyBlockCreditcard'(flowControl)
CustomKeywords.'com.maybank.mae.pages.Setting.verifyDeactivateAccounts'(flowControl)
CustomKeywords.'com.maybank.mae.pages.Setting.verifyDeactivateM2UAccess'(flowControl)
CustomKeywords.'com.maybank.mae.pages.Setting.tapBackButton'(flowControl)*/
