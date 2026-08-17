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
import com.maybank.mae.internal.pages.base.applypage.ApplyPage
import com.maybank.mae.internal.pages.base.BasePage
import com.maybank.mae.internal.pages.base.applypage.ApplyCardPage
import com.maybank.mae.internal.pages.base.CommonPage
import com.maybank.mae.internal.pages.base.HomePage
import com.maybank.mae.internal.pages.base.applypage.ApplyCASAPage
import com.maybank.utils.ListUtils
import com.maybank.utils.MapUtils

import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

creditCardApplicationStepOneData = ListUtils.parseObjectToList(creditCardApplicationStepOneData)
creditCardApplicationStepTwoData = ListUtils.parseObjectToList(creditCardApplicationStepTwoData)
getStartedData = ListUtils.parseObjectToList(getStartedData)
stepOneData = MapUtils.parseObjectToMap(stepOneData)
stepTwoData = MapUtils.parseObjectToMap(stepTwoData)
stepThreeData = MapUtils.parseObjectToMap(stepThreeData)
stepFourData = MapUtils.parseObjectToMap(stepFourData)
stepFiveData = MapUtils.parseObjectToMap(stepFiveData)
stepSixData = MapUtils.parseObjectToMap(stepSixData)
uploadDocumentStatusData = ListUtils.parseObjectToList(uploadDocumentStatusData)

HomePage homePage = BasePage.getPage(HomePage)
ApplyPage applyPage = BasePage.getPage(ApplyPage)
ApplyCardPage applyCardPage = BasePage.getPage(ApplyCardPage)
CommonPage commonPage = BasePage.getPage(CommonPage)
ApplyCASAPage applyCASAPage = BasePage.getPage(ApplyCASAPage)

'Tap Apply Button'
homePage.tapApply()

'Select Cards Tab'
applyPage.selectTab(tabName)

'Tap Card Type'
applyCardPage.selectCardType(cardType)

'Verify Credit Card Application Step One Screen'
applyCardPage.verifyCreditCardApplicationStepOne(creditCardApplicationStepOneData)

'Verify Credit Card Application Step Two Screen'
applyCardPage.verifyCreditCardApplicationStepTwo(creditCardApplicationStepTwoData, cardName)

'Verify Get Started Screen'
applyCardPage.verifyGetStarted(getStartedData)

'Fill Details Step One'
def accountDetails = applyCardPage.fillDetailsStepOneNTB(stepOneData)
String fullName     = accountDetails['Full name']
String mobileNumber = accountDetails['Mobile Number']
String dateOfBirth  = accountDetails['Date of birth']
String idNumber     = accountDetails['ID number']

'Fill Details Step Two'
applyCardPage.fillDetailsStepTwoNTB(stepTwoData)

'Fill Details Step Three'
applyCardPage.fillDetailsStepThreeNTB(stepThreeData)

'Fill Details Step Four'
applyCardPage.fillDetailsStepFourNTB(stepFourData)

'Fill Details Step Five'
applyCardPage.fillDetailsStepFiveNTB(stepFiveData)

'Fill Details Step Six'
applyCardPage.fillDetailsStepSixNTB(stepSixData)

'Tap Save and Continue'
Mobile.delay(GlobalVariable.G_timeoutMiniShort)
Mobile.takeScreenshot()
commonPage.tapSaveAndContinue()

'Agree and confirm'
applyCardPage.agreeAndConfirm(agreeRadioLabel)

'Upload Document Later'
applyCardPage.uploadDocument()

'Upload Document Status'
String RefID = applyCardPage.uploadDocumentStatus(uploadDocumentStatusData)

'Write into Excel'
Map<String, String> accountData = [
	'Full name'     : fullName,
	'Mobile Number' : mobileNumber,
	'Date of birth' : dateOfBirth,
	'ID number' 	: idNumber,
	'RefID'			: RefID
	
]
applyCASAPage.writeDataToExcel(accountData, "/Test Data/KMS/CASA STP/", "Apply Credit Card STP.xlsx", "Output Credit Card STP NTB")

'Back to Home'
homePage.tapHomeMenu()