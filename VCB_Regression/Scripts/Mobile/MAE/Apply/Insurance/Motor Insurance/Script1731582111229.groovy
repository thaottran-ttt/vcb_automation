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
import com.maybank.mae.internal.pages.base.applypage.ApplyPage as ApplyPage
import com.maybank.mae.internal.pages.base.applypage.ApplyPageInsuranceMotorcycle as ApplyPageInsuranceMotorcycle
import com.maybank.mae.internal.pages.base.BasePage as BasePage
import com.maybank.mae.internal.pages.base.CommonPage as CommonPage
import com.maybank.mae.internal.pages.base.HomePage as HomePage
import com.maybank.utils.ListUtils as ListUtils
import com.maybank.utils.MapUtils as MapUtils
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

stepOneData = ListUtils.parseObjectToList(stepOneData)

stepTwoData = ListUtils.parseObjectToList(stepTwoData)

HomePage homePage = BasePage.getPage(HomePage)

ApplyPage applyPage = BasePage.getPage(ApplyPage)

ApplyPageInsuranceMotorcycle applyPageInsuranceMotor = BasePage.getPage(ApplyPageInsuranceMotorcycle)

CommonPage commonPage = BasePage.getPage(CommonPage)


WebUI.callTestCase(findTestCase('Mobile/MAE/Common/Login Secure2u'), [('idNumber') : idNumber, ('PAN') : PAN, ('password') : password
        , ('username') : username, ('accountName') : '', ('flowControl') : com.maybank.common.FlowControlEnum.STOP, ('failStopTestSuite') : false
        , ('yourName') : 'Test'], FailureHandling.STOP_ON_FAILURE)

'Tap Apply Button'
homePage.tapApply()

'Select INSURANCE Tab'
applyPage.selectTab(tabName)

'Tap Insurance Type'
applyPageInsuranceMotor.selectInsuranceType(insuranceType)
'Verify Motor Insurance Step One Screen'
applyPageInsuranceMotor.verifyStepOne(stepOneData)
commonPage.delay(GlobalVariable.G_timeoutShort)

'Verify Motor Insurance Step Two Screen'
applyPageInsuranceMotor.verifyStepTwo(stepTwoData, plateNumber, postcode, nricNumber)

'Back to Home'
commonPage.backToHome()
homePage.tapHomeMenu()