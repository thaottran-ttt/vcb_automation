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
import com.maybank.common.MobileDriver as MobileDriver
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

WebUI.callTestCase(findTestCase('Mobile/MAE/Tabung Haji/Common/Tabung Haji Transfer'), [('username') : username, ('password') : password
        , ('idNumber') : idNumber, ('PAN') : PAN, ('accountName') : accountName, ('functionName') : functionName, ('tabName') : tabName
        , ('newTransferButton') : newTransferButton, ('transferData') : transferData, ('amount') : amount, ('recipientReferenceData') : recipientReferenceData
        , ('confirmationData') : confirmationData, ('confirmationDataButton') : confirmationDataButton, ('status') : status
        , ('statusScreenTitle') : approveStatusScreenTitle, ('statusScreenDescription') : approveStatusScreenDescription
        , ('shouldValidationShareReceipt') : shouldValidationShareReceipt, ('shouldValidationAddFavourite') : shouldValidationAddFavourite
        , ('shouldValidatePushNotification') : shouldValidatePushNotification, otherTabungHajiFlow: otherTabungHajiFlow], MobileDriver.defaultFailureHandling)

