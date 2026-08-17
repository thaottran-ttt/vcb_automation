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
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import com.maybank.mae.internal.pages.base.applypage.ApplyAccountPage
import com.maybank.mae.internal.pages.base.applypage.ApplyPage
import com.maybank.mae.internal.pages.base.applypage.ApplyPageMiga
import com.maybank.utils.ListUtils
import com.maybank.utils.MapUtils
import com.maybank.mae.internal.pages.base.BasePage
import com.maybank.mae.internal.pages.base.CommonPage
import com.maybank.mae.internal.pages.base.ConfirmationPage
import com.maybank.mae.internal.pages.base.HomePage
import com.maybank.mae.internal.pages.base.Secure2uAuthorisationPage
import com.maybank.mae.internal.pages.base.StatusScreenPage

import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

HomePage homePage = BasePage.getPage(HomePage)
ApplyPage applyPage = BasePage.getPage(ApplyPage)
CommonPage commonPage = BasePage.getPage(CommonPage)
ConfirmationPage confirmationPage = BasePage.getPage(ConfirmationPage)
Secure2uAuthorisationPage secure2uAuthorisationPage = BasePage.getPage(Secure2uAuthorisationPage)
StatusScreenPage statusScreenPage = BasePage.getPage(StatusScreenPage)
ApplyPageMiga applyMigaPage =  BasePage.getPage(ApplyPageMiga)
Map addressMap = MapUtils.parseObjectToMap(addressMap)

if(isApplyMiga) {
	
	applyMigaPage.selectAccountFromDataFile()
	username = applyMigaPage.userName
	password = applyMigaPage.password
	idNumber = applyMigaPage.idNumber
	PAN = applyMigaPage.pan
	
	WebUI.callTestCase(findTestCase('Mobile/MAE/Common/Login Secure2u'), [('idNumber') : idNumber, ('PAN') : PAN, ('password') : password
		, ('username') : username, ('accountName') : '', ('flowControl') : com.maybank.common.FlowControlEnum.STOP, ('failStopTestSuite') : false
		, ('yourName') : 'Test'], FailureHandling.STOP_ON_FAILURE)
	
	
	'Tap Apply Button'
	homePage.tapApply()
	
	'Select Cards Tab'
	applyPage.selectTab(tabName)
	
	applyPage.chooseAccountType(accountType)
	
	'Select Account Type'
	applyMigaPage.tapApplyNow()
	
	'Navigate to declaration page'
	applyMigaPage.navigateToDeclaration()
	
	'Tap Agree And Confirm'
	applyMigaPage.agreeAndConfirm(agreeRadio)
	
	'Tap Confirm And Submit'
	applyMigaPage.confirmation(addressMap)
	
	'Authorisation'
	secure2uAuthorisationPage.authorize(status)
	commonPage.waitLoading()
	
	if(status == "Approve") {
		'Tap to Activate Now'
		applyMigaPage.tapToActiveNow()
		
		'Change isValidColumn'
		applyMigaPage.updateIsValidColumn()
	}	
	else {
		commonPage.info('This case does not need to active MIGA')
		'verify status screen'
		statusScreenPage.verifyStatusScreen(verifyStatusScreenTitleData, verifyStatusScreenDescriptionData)
	}


}
else {
	KeywordUtil.markWarning("Apply Miga (isApplyMiga == false) is disable, please enable it.")
}






