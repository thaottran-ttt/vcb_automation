import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import internal.GlobalVariable as GlobalVariable
import com.vcb.mobile.internal.pages.base.BasePage as BasePage
import com.vcb.mobile.internal.pages.base.CommonPage as CommonPage
import com.vcb.mobile.internal.pages.base.HomePage
import com.vcb.mobile.internal.pages.base.tabung.TabungPage

HomePage homePage = BasePage.getPage(HomePage)
TabungPage tabungPage = BasePage.getPage(TabungPage)
CommonPage commonPage = BasePage.getPage(CommonPage)

//commonPage.clearDataAndCache()

WebUI.callTestCase(findTestCase('Mobile/VCB/Common/Login Secure2u'), [('idNumber') : idNumber, ('PAN') : PAN, ('password') : GlobalVariable.G_password
	, ('username') : username, ('accountName') : '', ('flowControl') : com.vcb.common.FlowControlEnum.STOP, ('failStopTestSuite') : false
	, ('yourName') : GlobalVariable.G_yourName], FailureHandling.STOP_ON_FAILURE)

'Navigate to View All Screen'
homePage.selectQuickActions(action)

commonPage.waitLoading()

'Select invitation'
tabungPage.joinGroupTabung(invatationFrom, invitedgroupTabung)

'Confirm authorisation'
String refID = tabungPage.joinTabungConfirm(status, statusTitle, statusDescription, regex)
Mobile.comment("RefID: " + refID)

WebUI.callTestCase(findTestCase('Mobile/MVP5 S2U V2 to V5/Network logs'), [('apiKeyword') : apiKeyword, ('expectedValues') : expectedValues],
	FailureHandling.STOP_ON_FAILURE)




