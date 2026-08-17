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
import com.maybank.mae.internal.pages.base.applypage.ApplyAccountPage
import com.maybank.mae.internal.pages.base.applypage.ApplyCardPage
import com.maybank.mae.internal.pages.base.applypage.ApplyPage
import com.maybank.utils.MapUtils
import com.maybank.mae.internal.pages.base.BasePage
import com.maybank.mae.internal.pages.base.CommonPage
import com.maybank.mae.internal.pages.base.HomePage

import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys


HomePage homePage = BasePage.getPage(HomePage)
ApplyPage applyPage = BasePage.getPage(ApplyPage)
ApplyAccountPage applyAccountPage = BasePage.getPage(ApplyAccountPage)
CommonPage commonPage = BasePage.getPage(CommonPage)
ApplyCardPage applyCardPage = BasePage.getPage(ApplyCardPage)

'Navigate To Login'
CustomKeywords.'com.maybank.mae.pages.Onboarding.navigateToLogin'()

'Tap Apply Button'
homePage.tapApply()

'Select Cards Tab'
applyPage.selectTab(tabName)

'Tap Card Type'
commonPage.scrollToText(cardType, 5, 1500, 5, 100)
commonPage.tap(commonPage.lblDynamic(cardType), GlobalVariable.G_timeoutMedium, FailureHandling.STOP_ON_FAILURE)

'Verify alert message'
commonPage.verifyTextVisible(message, GlobalVariable.G_timeoutMedium, FailureHandling.STOP_ON_FAILURE)
