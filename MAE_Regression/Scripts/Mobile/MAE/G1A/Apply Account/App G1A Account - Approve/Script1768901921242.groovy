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

WebUI.callTestCase(findTestCase('Mobile/MAE/G1A/Apply Account/Common/Apply Account Common'), [('username') : username, ('password') : password
        , ('idNumber') : idNumber, ('PAN') : PAN, ('tabName') : tabName, ('accountType') : accountType, ('idType') : idType
        , ('residentialData') : residentialData, ('questionList') : questionList, ('personalDetails') : personalDetails, ('suitabilityMap') : suitabilityMap
        , ('employmentDetails') : employmentDetails, ('branchMap') : branchMap, ('edittedDebitCardDeliveryAddress') : edittedDebitCardDeliveryAddress
        , ('edittedPersonalDetails') : edittedPersonalDetails, ('edittedBranch') : edittedBranch, ('edittedEmploymentDetails') : edittedEmploymentDetails
        , ('debitCardName') : debitCardName, ('isApplyLater') : isApplyLater, ('radioText') : radioText, ('successText') : option
        , ('option') : option, ('registerBtn') : 'Register M2U Access', ('newPassword') : 'Maybank@1', ('securityPhrase') : 'verySecurePhrase'
        , ('securityQuestions') : [('Security question 1') : 'What is the name of your favorite Auntie?', ('answer1') : 'test', ('Security question 2') : 'What is the name of the best man at your wedding?', ('answer2') : 'test', ('Security question 3') : 'Where did you travel on your honeymoon?', ('answer3') : 'test']
        , ('registerSuccessMsg') : 'Your M2U access has been registered'], FailureHandling.STOP_ON_FAILURE)

