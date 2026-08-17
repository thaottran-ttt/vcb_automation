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
import com.vcb.assertions.BaseAssert as BaseAssert
import com.vcb.common.MobileDriver as MobileDriver
import com.vcb.mobile.internal.pages.base.BasePage as BasePage
import com.vcb.mobile.internal.pages.base.CommonPage as CommonPage
import com.vcb.mobile.internal.pages.base.zakat.ZakatPage as ZakatPage
import com.vcb.mobile.internal.pages.base.HomePage as HomePage
import com.vcb.mobile.internal.pages.base.PushNotificationPage as PushNotificationPage

import com.vcb.utils.ListUtils as ListUtils
import com.vcb.utils.StringUtils as StringUtils
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys


ZakatPage zakatPage = BasePage.getPage(ZakatPage)
CommonPage commonPage = BasePage.getPage(CommonPage)
HomePage homePage = BasePage.getPage(HomePage)

WebUI.callTestCase(findTestCase('Mobile/VCB/Common/Login Secure2u'), [('idNumber') : idNumber, ('PAN') : PAN, ('password') : GlobalVariable.G_password
	, ('username') : username, ('accountName') : accountName, ('flowControl') : com.vcb.common.FlowControlEnum.STOP, ('failStopTestSuite') : false
	, ('yourName') : 'Test'], FailureHandling.STOP_ON_FAILURE)

boolean Verify = Verify

'Navigate to Entry Point'
zakatPage.enterDashboard(entry, false, Verify)

String editValue
String existingValue

'Edit based on Type'
switch (EditType) {
	case "Body":
	
		'Click Switch Body'
		zakatPage.switchBody(ZakatBody2, Verify)
		commonPage.tapContinueBtn()
		editValue = ZakatBody2
		existingValue = ZakatBody1
		
		break
	case "Number":
		commonPage.scrollToEnd()
		'Click edit No'
		zakatPage.editNo(Number2, Verify)
		editValue = Number2
		existingValue = Number1
		
		break
	case "Account":
	
		'Click Switch Account'
		zakatPage.switchAccount(payFrom2, Verify)
		commonPage.tapContinueBtn()
		editValue = payFrom2
		existingValue = payFrom1
		
		break
}

'S2U Page'
zakatPage.settingS2U(status, EditType, editValue)

'Status Page'
zakatPage.settingStatusPage(EditType,editValue, status)

'Verify Changes'
zakatPage.verifyChangesEdit(EditType,status,editValue,existingValue)

zakatPage.restartApp()
