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
import com.maybank.mae.internal.pages.base.BasePage as BasePage
import com.maybank.mae.internal.pages.base.applypage.ApplyCardPage as ApplyCardPage
import com.maybank.mae.internal.pages.base.CommonPage as CommonPage
import com.maybank.mae.internal.pages.base.HomePage as HomePage
import com.maybank.mae.internal.pages.base.Secure2uAuthorisationPage as Secure2uAuthorisationPage
import com.maybank.mae.internal.pages.base.StatusScreenPage as StatusScreenPage
import com.maybank.utils.ListUtils as ListUtils
import com.maybank.utils.MapUtils as MapUtils
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

WebUI.callTestCase(
	findTestCase('Mobile/MAE/CASA STP/Card/Apply Card/MAE Card/ETB/MAE Card - ETB Common'),
	[
		('skipRestartAfterTestCase') : skipRestartAfterTestCase,
		('username') : username,
		('password') : password,
		('idNumber') : idNumber,
		('PAN') : PAN,
		('tabName') : tabName,
		('cardType') : cardType,
		('requestCardData') : requestCardData,
		('inputRequestCardStepTwoData') : inputRequestCardStepTwoData,
		('agreeDataStepTwo') : agreeDataStepTwo,
		('s2uStatus') : s2uStatus,
		('titleStatusScreenRejectData') : titleStatusScreenRejectData,
		('descriptionStatusScreenRejectData') : descriptionStatusScreenRejectData,
		('titleStatusScreenApproveData') : titleStatusScreenApproveData,
		('descriptionStatusScreenApproveData') : descriptionStatusScreenApproveData,
		('titleStatusScreenExpiredData') : titleStatusScreenExpiredData,
		('descriptionStatusScreenExpiredData') : descriptionStatusScreenExpiredData
	],
	FailureHandling.STOP_ON_FAILURE)