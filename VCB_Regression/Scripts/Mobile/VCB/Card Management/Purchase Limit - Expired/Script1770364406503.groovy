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
import com.vcb.mobile.internal.pages.base.settingpage.SettingPage as SettingPage
import com.vcb.mobile.internal.pages.base.BasePage as BasePage
import com.vcb.mobile.internal.pages.base.CommonPage as CommonPage
import com.vcb.mobile.internal.pages.base.HomePage as HomePage
import com.vcb.mobile.internal.pages.base.Secure2uAuthorisationPage as Secure2uAuthorisationPage
import com.vcb.mobile.internal.pages.base.StatusScreenPage as StatusScreenPage

FailureHandling flowControl = FailureHandling.STOP_ON_FAILURE

HomePage homePage = HomePage.getPage(HomePage)

CommonPage commonPage = BasePage.getPage(CommonPage)

SettingPage settingPage = BasePage.getPage(SettingPage)

Secure2uAuthorisationPage secure2uAuthorisationPage = BasePage.getPage(Secure2uAuthorisationPage)

StatusScreenPage statusScreenPage = BasePage.getPage(StatusScreenPage)
//
//WebUI.callTestCase(findTestCase('Mobile/VCB/Common/Login Secure2u'), [('idNumber') : idNumber, ('PAN') : PAN, ('password') : GlobalVariable.G_password
//        , ('username') : username, ('accountName') : '', ('flowControl') : com.vcb.common.FlowControlEnum.STOP, ('failStopTestSuite') : false
//        , ('yourName') : GlobalVariable.G_yourName], FailureHandling.STOP_ON_FAILURE)
//
WebUI.callTestCase(findTestCase('Mobile/VCB/Card Management/Card Management - Entry point'), [('entryFlow') : entryFlow], 
    FailureHandling.STOP_ON_FAILURE)


// Drag to Min
commonPage.dragAndDropObj(
	findTestObject('Object Repository/Card mgmt/purchase limit button'),
	findTestObject('Object Repository/Card mgmt/android.widget.TextView - RM 0')
)

//click update
commonPage.tap(commonPage.lblDynamic("Update"), GlobalVariable.G_timeoutMedium,flowControl)

'Authorize'
secure2uAuthorisationPage.authorize(status)

'Verify Status Screen'
statusScreenPage.verifyStatusScreen(statusScreenRejectTitle, statusScreenRejectDescription)

//if (Mobile.verifyEqual(Mobile.getText(findTestObject('Card mgmt/android.widget.TextView - RM 1st'), 5), 'RM 10,000',
//	FailureHandling.OPTIONAL)) {
//	Mobile.dragAndDrop(findTestObject('Object Repository/Card mgmt/purchase limit button'), findTestObject('Object Repository/Card mgmt/android.widget.TextView - RM 0'),
//		5)
//} else if (Mobile.verifyEqual(Mobile.getText(findTestObject('Card mgmt/android.widget.TextView - RM 1st'), 5), 'RM 0',
//	FailureHandling.OPTIONAL)) {
//	Mobile.dragAndDrop(findTestObject('Object Repository/Card mgmt/purchase limit button'), findTestObject('Object Repository/Card mgmt/android.widget.TextView - RM 10,000'),
//		5)
//} else {
//	Mobile.dragAndDrop(findTestObject('Object Repository/Card mgmt/purchase limit button'), findTestObject('Object Repository/Card mgmt/android.widget.TextView - RM 10,000'),
//		5)
//}
