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
import com.maybank.assertions.BaseAssert as BaseAssert
import com.maybank.common.MobileDriver as MobileDriver
import com.maybank.mae.internal.pages.base.BasePage as BasePage
import com.maybank.mae.internal.pages.base.CommonPage as CommonPage
import com.maybank.mae.internal.pages.base.zakat.ZakatPage as ZakatPage
import com.maybank.mae.internal.pages.base.accountpage.AccountsPage
import com.maybank.mae.internal.pages.base.HomePage as HomePage
import com.maybank.mae.internal.pages.base.accountpage.AccountDetailPage

import com.maybank.utils.ListUtils as ListUtils
import com.maybank.utils.StringUtils as StringUtils
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

ZakatPage zakatPage = BasePage.getPage(ZakatPage)
CommonPage commonPage = BasePage.getPage(CommonPage)
HomePage homePage = BasePage.getPage(HomePage)
AccountDetailPage AccountDetailPage = BasePage.getPage(AccountDetailPage)
AccountsPage accountsPage = BasePage.getPage(AccountsPage)

'Onboard'
WebUI.callTestCase(findTestCase('Mobile/MAE/Common/Login Secure2u'), [('idNumber') : idNumber, ('PAN') : PAN, ('password') : GlobalVariable.G_password
	, ('username') : username, ('accountName') : accountName], FailureHandling.STOP_ON_FAILURE)

'Navigate to Zakat Calculator'
WebUI.callTestCase(findTestCase('Mobile/MAE/Zakat Auto/Reuseable/Entry-Zakat Dashboard'), [('ZakatBox') : "Zakat On Demand", ('Verify') : Verify], FailureHandling.STOP_ON_FAILURE)

'Tap Calculate & Pay Tile'
zakatPage.tapCalculate(ZakatTypes, Verify)

'Check if ZAD already registered Pop Up'
zakatPage.checkZADPopup(Verify)

'Choose Zakat Body'
zakatPage.selectZakatBody(ZakatBody, Verify)

'Check if Saved Calcution pop up'
zakatPage.checkExistedCalculationPopup()

'Step for Zakat Type'
switch (ZakatTypes){
	case "Zakat Pendapatan":	
	zakatPage.PendapatanSteps(ZakatBody, flowType, Verify)
	break
	
	case "Zakat Simpanan":
	zakatPage.SimpananSteps(ZakatBody, flowType, Verify)
	break
	
	case "Zakat Pelaburan":	
	zakatPage.PelaburanSteps(ZakatBody, flowType, Verify)
	break
	
	case "Zakat Emas":	
	zakatPage.EmasSteps(ZakatBody, flowType, Verify)
	break
	
	case "Zakat KWSP":	
	zakatPage.KWSPSteps(ZakatBody, flowType, Verify)
	break
}

'Validate Calculation Page > Tap Pay Zakat Now'
zakatPage.ZODcalculationPage(ZakatTypes, flowType, Verify)

'Check Password'
commonPage.checkEnterPassword(password)

'Validate Confirmation Page > Tap Pay Now'
zakatPage.ZODConfirmationPage(ZakatBody, ZakatTypes, Verify)

'Check Niat Pop Up after tapped Pay Now'
zakatPage.checkNiatPopup(ZakatTypes)

'Validate S2U Page'
zakatPage.ZODS2UPage(status, ZakatBody, ZakatTypes)

'Validate Acknowledgement Page'
zakatPage.ZODStatusPage(status)

'Tap save as Completed'
zakatPage.checkMarkCalculationPopup()

'If S2U Approve, verify PN and M2U History'
zakatPage.verifyM2UHistoryAfterS2UApprove(status,PNtitle,PNregex,account,m2uHistoryProduct,m2uHistoryAmount)
