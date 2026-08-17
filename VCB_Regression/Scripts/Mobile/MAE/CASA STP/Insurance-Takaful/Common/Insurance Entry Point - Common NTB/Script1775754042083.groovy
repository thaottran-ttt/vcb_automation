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
import com.maybank.mae.internal.pages.base.BasePage as BasePage
import com.maybank.mae.internal.pages.base.CommonPage as CommonPage
import com.maybank.mae.internal.pages.base.HomePage as HomePage
import com.maybank.mae.internal.pages.base.applypage.ApplyPage as ApplyPage
import com.maybank.mae.internal.pages.base.applypage.ApplyPageInsurance as ApplyPageInsurance
import com.maybank.utils.ListUtils as ListUtils
import com.maybank.common.MobileDriver
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

CommonPage commonPage = BasePage.getPage(CommonPage)
HomePage homePage = BasePage.getPage(HomePage)
ApplyPage applyPage = BasePage.getPage(ApplyPage)
ApplyPageInsurance applyPageInsurance = BasePage.getPage(ApplyPageInsurance)

'Tap Home'
homePage.tapHomeMenu()

'Tap Apply'
homePage.tapApply()

'Select INSURANCE/TAKAFUL Tab'
applyPage.selectTab(tabName)

'Select Type of Insurance'
if(subTabName == "Home") {
	applyPageInsurance.selectHomeSubTab(subTabName)
} else {
	applyPageInsurance.selectSubTab(subTabName)
}

'Tap Insurance Type'
applyPageInsurance.selectInsuranceType(insuranceType)

'Verify Intro Page'
applyPageInsurance.verifyIntroPage(introPageData, runValidation)

'Verify Etiqa Page'
commonPage.waitLoading()
applyPageInsurance.verifyEtiqaPage(etiqaTitle)

'Tap Back'
commonPage.backToHome()