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

WebUI.callTestCase(findTestCase('Mobile/MAE/Reload/Common/Reload Payment'), [('username') : username, ('password') : password
        , ('idNumber') : idNumber, ('PAN') : PAN, ('accountName') : accountName, ('accessFlow') : accessFlow, ('actionName') : actionName
        , ('provider') : provider, ('phoneNumber') : phoneNumber, ('amount') : amount_reload, ('verifyUIConfirmationPage') : verifyUIConfirmationPage
        , ('nextConfirmationPage') : nextConfirmationPage, ('status') : status, ('statusTitle') : statusTitle, ('statusDescription') : statusDescription
        , ('verifyUIShareReceipt') : verifyUIShareReceipt, ('titlePushNotification') : titlePushNotification, ('messagePushNotification') : messagePushNotification
        , ('shouldValidatePushNotification') : shouldValidatePushNotification, ('shoudShareReceiptRun') : shoudShareReceiptRun
        , ('editAmount') : editAmount, ('reprintReceiptProduct') : reprintReceiptProduct], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('Mobile/MAE/Money Lock/Money Lock Common'), [('username') : username, ('password') : password
        , ('idNumber') : idNumber, ('PAN') : PAN, ('accountName') : accountName, ('entryFlow') : entryFlow
        , ('functionName') : functionName, ('fieldValidation') : fieldValidation, ('amount') : amount, ('editAmount') : editAmount, ('editNewAmount') : editNewAmount
        , ('status') : status, ('otherVerifyData') : otherVerifyData
        , ('titleData') : titleData, ('statusDescription') : statusDescription], 
    FailureHandling.STOP_ON_FAILURE)
