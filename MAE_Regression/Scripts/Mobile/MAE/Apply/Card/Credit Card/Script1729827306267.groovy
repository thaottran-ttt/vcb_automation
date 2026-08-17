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
import com.maybank.mae.internal.pages.base.Secure2uAuthorisationPage
import com.maybank.utils.ListUtils
import com.maybank.utils.MapUtils

import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

creditCardApplicationStepOneData = ListUtils.parseObjectToList(creditCardApplicationStepOneData)
creditCardApplicationStepTwoData = ListUtils.parseObjectToList(creditCardApplicationStepTwoData)
getStartedData = ListUtils.parseObjectToList(getStartedData)
drpStepOneData = MapUtils.parseObjectToMap(drpStepOneData)
drpStepTwoData = MapUtils.parseObjectToMap(drpStepTwoData)
inputStepTwoData = MapUtils.parseObjectToMap(inputStepTwoData)
drpStepThreeData = MapUtils.parseObjectToMap(drpStepThreeData)
intputStepThreeData = MapUtils.parseObjectToMap(intputStepThreeData)
drpStepFourData = MapUtils.parseObjectToMap(drpStepFourData)
drpTypeSearchStepFourData = MapUtils.parseObjectToMap(drpTypeSearchStepFourData)
drpStepFiveData = MapUtils.parseObjectToMap(drpStepFiveData)
inputStepFiveData = MapUtils.parseObjectToMap(inputStepFiveData)
drpStepSixData = MapUtils.parseObjectToMap(drpStepSixData)
inputStepSixData = MapUtils.parseObjectToMap(inputStepSixData)
uploadDocumentStatusData = ListUtils.parseObjectToList(uploadDocumentStatusData)

WebUI.callTestCase(findTestCase('Mobile/MAE/Common/Login Secure2u'), [('idNumber') : idNumber, ('PAN') : PAN, ('password') : password
        , ('username') : username, ('accountName') : '', ('flowControl') : com.maybank.common.FlowControlEnum.STOP, ('failStopTestSuite') : false
        , ('yourName') : 'Test'], FailureHandling.STOP_ON_FAILURE)

HomePage homePage = BasePage.getPage(HomePage)
ApplyPage applyPage = BasePage.getPage(ApplyPage)
ApplyCardPage applyCardPage = BasePage.getPage(ApplyCardPage)
CommonPage commonPage = BasePage.getPage(CommonPage)
Secure2uAuthorisationPage secure2uAuthorisationPage = BasePage.getPage(Secure2uAuthorisationPage)

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
applyCardPage.fillDetailsStepOne(drpStepOneData)

'Fill Details Step Two'
applyCardPage.fillDetailsStepTwo(drpStepTwoData, inputStepTwoData)

'Fill Details Step Three'
applyCardPage.fillDetailsStepThree(drpStepThreeData, intputStepThreeData)

'Fill Details Step Four'
applyCardPage.fillDetailsStepFour(drpStepFourData, drpTypeSearchStepFourData)

'Fill Details Step Five'
applyCardPage.fillDetailsStepFive(drpStepFiveData, inputStepFiveData)

'Fill Details Step Six'
applyCardPage.fillDetailsStepSix(drpStepSixData, inputStepSixData)

Mobile.delay(GlobalVariable.G_timeoutShort)
'tap save and continue'
commonPage.tapSaveAndContinue()

'Agree and confirm'
applyCardPage.agreeAndConfirm(agreeRadioLabel)

'S2u Authorization'
secure2uAuthorisationPage.authorize(statusS2u)

'Upload Document Later'
applyCardPage.uploadDocument()

'Upload Document Status'
String RefID = applyCardPage.uploadDocumentStatus(uploadDocumentStatusData)

'Back to Home'
homePage.tapHomeMenu()
