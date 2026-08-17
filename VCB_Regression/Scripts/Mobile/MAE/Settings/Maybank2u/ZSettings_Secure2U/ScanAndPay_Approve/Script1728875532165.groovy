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



String secure2U = null
switch(secure2U) {

	case 'duitnowswitchaccountsecure2u' 
	Secure2u athorization
	Switch DuitNow Account
	ID Type
	Switch to
	Date & time
	Approve
	Reject
	
	break
	
	case 'scanandpaysecure2u' :
	
	break
	
	
}
CustomKeywords.'com.maybank.mae.pages.SetSecure2uting.tapApprove'(flowControl)
CustomKeywords.'com.maybank.mae.pages.Secure2u.verifyScanAndPayLimitText'(flowControl)
CustomKeywords.'com.maybank.mae.pages.Secure2u.verifyRefrenceIDText'(flowControl)
CustomKeywords.'com.maybank.mae.pages.Secure2u.verifyRefrenceIDValue'(flowControl)
CustomKeywords.'com.maybank.mae.pages.Setting.verifyDailyLimitText'(flowControl)
CustomKeywords.'com.maybank.mae.pages.Secure2u.verifyDailyLimitValue'(flowControl)
CustomKeywords.'com.maybank.mae.pages.Setting.verifyCumulativeLimitText'(flowControl)
CustomKeywords.'com.maybank.mae.pages.Secure2u.verifyCumulativeLimitValue'(flowControl)
CustomKeywords.'com.maybank.mae.pages.Secure2u.verifyDateAndTimeText'(flowControl)
CustomKeywords.'com.maybank.mae.pages.Secure2u.verifyDateAndTimeValue'(flowControl)
CustomKeywords.'com.maybank.mae.pages.Secure2u.tapDone'(flowControl)