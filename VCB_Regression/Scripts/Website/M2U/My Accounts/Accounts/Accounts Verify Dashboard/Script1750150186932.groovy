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
import com.maybank.common.BaseKeyword
import com.maybank.m2u.internal.pages.base.WebBasePage as WebBasePage
import com.maybank.m2u.internal.pages.base.WebCommonPage as WebCommonPage
import com.maybank.m2u.internal.pages.base.WebHomePage as WebHomePage
import com.maybank.m2u.internal.pages.base.WebSecure2uAuthorisationPage as WebSecure2uAuthorisationPage
import com.maybank.m2u.internal.pages.base.WebStatusScreenPage as WebStatusScreenPage
import com.maybank.m2u.internal.pages.base.paytransfer.WebPayPage as WebPayPage
import com.maybank.utils.ListUtils as ListUtils
import com.maybank.utils.MapUtils as MapUtils
import internal.GlobalVariable as GlobalVariable

import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys


WebCommonPage webCommonPage = WebBasePage.getPage(WebCommonPage)

WebHomePage webHomePage = WebBasePage.getPage(WebHomePage)

WebPayPage WebPayPage = WebBasePage.getPage(WebPayPage)

WebStatusScreenPage webStatusScreenPage = WebBasePage.getPage(WebStatusScreenPage)

WebSecure2uAuthorisationPage webSecure2uAuthorisationPage = WebBasePage.getPage(WebSecure2uAuthorisationPage)

webSecure2uAuthorisationPage.setID_NUMBER(idNumber)

WebUI.callTestCase(findTestCase('Mobile/MAE/Common/Login Secure2u Without Check Logout'), [('username') : username, ('password') : password
        , ('idNumber') : idNumber, ('PAN') : PAN], BaseKeyword.defaultFailureHandling)

WebUI.callTestCase(findTestCase('Website/M2U/Common/Login'), [('username') : username, ('password') : password], BaseKeyword.defaultFailureHandling)

'Navigate To Transfer'
webHomePage.navigate(navItem, tabName)

'Verify Element Visible Dashboard UI Data'
webCommonPage.verifyElementListVisible(dashboardUIData)
