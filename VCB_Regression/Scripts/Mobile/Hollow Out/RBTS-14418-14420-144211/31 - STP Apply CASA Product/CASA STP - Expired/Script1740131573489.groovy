import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.maybank.mae.internal.pages.base.BasePage as BasePage
import com.maybank.mae.internal.pages.base.CommonPage as CommonPage
import com.maybank.mae.internal.pages.base.ConfirmationPage as ConfirmationPage
import com.maybank.mae.internal.pages.base.HomePage as HomePage
import com.maybank.mae.internal.pages.base.Secure2uAuthorisationPage as Secure2uAuthorisationPage
import com.maybank.mae.internal.pages.base.StatusScreenPage as StatusScreenPage
import com.maybank.mae.internal.pages.base.applypage.ApplyAccountPage as ApplyAccountPage
import com.maybank.mae.internal.pages.base.applypage.ApplyPage as ApplyPage
import internal.GlobalVariable as GlobalVariable
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint

WebUI.callTestCase(findTestCase('Mobile/Hollow Out/RBTS-14418-14420-144211/31 - STP Apply CASA Product/Common/CASA STP - Common'), [('username') : username
        , ('password') : password, ('idNumber') : idNumber, ('PAN') : PAN, ('accountName') : accountName, ('tabName') : tabName
        , ('accountType') : accountType, ('fistVerifyData') : fistVerifyData, ('stepOneUIData') : stepOneUIData, ('stepTwoUIData') : stepTwoUIData
        , ('stepThreeUIData') : stepThreeUIData, ('drpstepThreeData') : drpstepThreeData, ('inputStepOneData') : inputStepOneData
        , ('agreeAndConfirmUIData') : agreeAndConfirmUIData, ('agreeRadio') : agreeRadio, ('confirmUIData') : confirmUIData
        , ('confirmUISecondData') : confirmUISecondData, ('confirmNextPage') : confirmNextPage, ('s2uStatus') : s2uStatus
        , ('statusScreenRejectTitle') : statusScreenRejectTitle, ('statusScreenRejectDescription') : statusScreenRejectDescription
        , ('amount') : amount], FailureHandling.STOP_ON_FAILURE)

