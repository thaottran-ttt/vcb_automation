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

WebUI.callTestCase(findTestCase('Mobile/VCB/G1A/Own Transfer/Common/Self Transfer'), [('flowControl') : com.vcb.common.FlowControlEnum.STOP
        , ('failStopTestSuite') : false, ('skipRestartAfterTestCase') : true, ('username') : username, ('password') : password
        , ('tabName') : tabName, ('accountName') : accountName, ('amount') : amount, ('recipientReference') : recipientReference
        , ('status') : status, ('idNumber') : idNumber, ('PAN') : PAN, ('title') : title, ('recipientAccount') : recipientAccount
        , ('isFuture') : isFuture, ('receiptTitle') : receiptTitle, ('statusTitle') : statusTitle, ('recipientAccountNumber') : recipientAccountNumber], 
    FailureHandling.STOP_ON_FAILURE)

