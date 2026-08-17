import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.maybank.m2u.internal.pages.base.ElementVerificationType
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import com.maybank.m2u.internal.pages.base.WebBasePage
import com.maybank.m2u.internal.pages.base.WebCommonPage
import com.maybank.m2u.internal.pages.base.WebLoginPage
import com.maybank.utils.ListUtils
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys
WebLoginPage webLoginPage = WebBasePage.getPage(WebLoginPage)

WebCommonPage webCommonPage = WebBasePage.getPage(WebCommonPage)

List forgotPasswordModal = ListUtils.parseObjectToList(forgotPasswordModal)
List cardType = ListUtils.parseObjectToList(cardType)

'Verify forgot password modal'
webLoginPage.verifyForgotLoginLink()
webCommonPage.verifyElementListVisible(forgotPasswordModal, FailureHandling.STOP_ON_FAILURE, ElementVerificationType.valueOf(elementVerificationType))
webCommonPage.verifyElementListVisible(cardType, FailureHandling.STOP_ON_FAILURE, ElementVerificationType.valueOf(elementVerificationType), index)