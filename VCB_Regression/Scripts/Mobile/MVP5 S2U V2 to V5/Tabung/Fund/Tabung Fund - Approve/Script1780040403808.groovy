import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import internal.GlobalVariable as GlobalVariable

WebUI.callTestCase(findTestCase('Mobile/VCB/Tabung/Common/Tabung Common'), [('username') : username, ('password') : password
        , ('idNumber') : idNumber, ('PAN') : PAN, ('action') : action, ('edit') : edit, ('mobileNumberShareBill') : mobileNumberShareBill
        , ('isGroup') : isGroup, ('status') : status, ('statusTitle') : statusTitle, ('statusDescription') : statusDescription
        , ('tabungMap') : tabungMap, ('verifyDashboard') : verifyDashboard
        , ('actionTabung') : actionTabung, ('tabungActionMap') : tabungActionMap
        , ('isMakeAction') : isMakeAction, ('shouldCreateTabung') : shouldCreateTabung, ('shouldVerifyNotification') : shouldVerifyNotification
		, ('title') : title, ('message') : message, ('regex') : regex, ('actionStatusTitle') : actionStatusTitle
        , ('actionStatusDescription') : actionStatusDescription], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('Mobile/MVP5 S2U V2 to V5/Network logs'), [('apiKeyword') : apiKeyword, ('expectedValues') : expectedValues],
	FailureHandling.STOP_ON_FAILURE)

