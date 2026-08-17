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
import com.maybank.mae.internal.pages.base.BasePage as BasePage
import com.maybank.mae.internal.pages.base.OnboardingPage as OnboardingPage
import com.maybank.mae.internal.pages.base.HomePage as HomePage
import com.maybank.mae.internal.pages.base.CommonPage as CommonPage
import com.maybank.mae.internal.pages.base.applypage.ApplyAccountPage as ApplyAccountPage
import com.maybank.mae.internal.pages.base.applypage.ApplyCASAPage as ApplyCASAPage
import com.maybank.mae.internal.pages.base.applypage.ApplyPage as ApplyPage
import com.maybank.mae.internal.pages.base.PushNotificationPage as PushNotificationPage
import com.maybank.mae.internal.pages.base.Secure2uPage as Secure2uPage
import com.maybank.utils.ListUtils as ListUtils
import com.maybank.utils.StringUtils as StringUtils
import com.maybank.utils.MapUtils as MapUtils
import com.maybank.common.MobileDriver as MobileDriver

WebUI.callTestCase(findTestCase('Mobile/MAE/CASA STP/Apply Account/NTB/Common/Common PBA_PBAi Passport')
		, [('account') : account, ('accountType') : accountType, ('idType') : idType, ('passportDetails') : passportDetails, ('personalDetails') : personalDetails
		, ('residentialDetails') : residentialDetails, ('employmentDetails') : employmentDetails, ('declarationText') : declarationText
		, ('branchDetails') : branchDetails, ('m2uUsername') : m2uUsername, ('m2uPassword') : m2uPassword, ('securityPhrase') : securityPhrase
		, ('securityQuestions') : securityQuestions, ('myKadNumber') : myKadNumber, ('pushNotificationMessage') : pushNotificationMessage
		, ('statusScreenSuccess') : statusScreenSuccess, ('suitabilityAssessment') : suitabilityAssessment, ('runValidations') : runValidations]
		, FailureHandling.STOP_ON_FAILURE)