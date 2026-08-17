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
import com.vcb.mobile.internal.pages.base.zakat.ZakatPage as ZakatPage
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import com.vcb.assertions.BaseAssert as BaseAssert
import com.vcb.common.MobileDriver as MobileDriver
import com.vcb.mobile.internal.pages.base.BasePage as BasePage
import com.vcb.mobile.internal.pages.base.CommonPage as CommonPage
import com.vcb.mobile.internal.pages.base.gbi.GbiPage as GbiPage
import com.vcb.mobile.internal.pages.base.HomePage as HomePage
import com.vcb.mobile.internal.pages.base.Secure2uAuthorisationPage as Secure2uAuthorisationPage
import com.vcb.mobile.internal.pages.base.PushNotificationPage as PushNotificationPage

import com.vcb.utils.ListUtils as ListUtils
import com.vcb.utils.StringUtils as StringUtils
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

GbiPage gbiPage = BasePage.getPage(GbiPage)
CommonPage commonPage = BasePage.getPage(CommonPage)
HomePage homePage = BasePage.getPage(HomePage)
ZakatPage zakatPage = BasePage.getPage(ZakatPage)
Secure2uAuthorisationPage secure2uAuthorisationPage = BasePage.getPage(Secure2uAuthorisationPage)

WebUI.callTestCase(findTestCase('Mobile/VCB/Common/Login Secure2u'), [('idNumber') : idNumber, ('PAN') : PAN, ('password') : GlobalVariable.G_password
	, ('username') : username, ('accountName') : accountName], FailureHandling.STOP_ON_FAILURE)

'Entry Point GBI'
gbiPage.ViewAllGBIEntry()
gbiPage.ViewAllGBIEntry()

'Check Pin'
commonPage.checkEnterPIN()

'Click + Create Goal'
gbiPage.firstTimeRegister()

'Risk Pop Up'
gbiPage.RiskPopUp()

'Risk Profile Page'
gbiPage.RiskProfilePage(RetakeAssessment)

gbiPage.getStartedPage(RetakeAssessment)

'Pick Category'
gbiPage.pickCategory(GBICategory)

switch (GBICategory) {
	case "Retirement":
	gbiPage.RetirementGoal()
	break
	
	case "Education Myself":
	gbiPage.educationMyselfGoal()
	break
	
	case "Education Child":
	gbiPage.educationMyChildGoal()
	break
	
	case "Grow Wealth":
	gbiPage.growWealthGoal()
	break
}

gbiPage.SimulationPage()

gbiPage.investmentDisclaimer()

gbiPage.FundAllocationPage(Fund)

gbiPage.depositPlanPage(DepoPlan)

commonPage.checkEnterPassword(password)

if(DepoPlan == "OneTime") {
	gbiPage.oneTimeDepositPage()
	gbiPage.emailPage()
}else {
	gbiPage.autoDepositPage()
}

gbiPage.createConfirmation(GBICategory)

'Select approval'
secure2uAuthorisationPage.authorize(status)

'Verify S2U Approve scenarios'
if(status=="Approve") {

	gbiPage.createAcknowledgementPage()
	commonPage.tapDoneBtn()
	
	Mobile.delay(5)	
	'Entry Point GBI'
	gbiPage.ViewAllGBIEntry()

	gbiPage.verifyCreatedGoal(GBICategory)
}

zakatPage.restartApp()



