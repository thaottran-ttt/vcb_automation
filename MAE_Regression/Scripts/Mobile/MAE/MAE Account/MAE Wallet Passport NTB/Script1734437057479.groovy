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
import com.maybank.utils.ListUtils
import com.maybank.utils.MapUtils
import com.maybank.mae.internal.pages.base.BasePage
import com.maybank.mae.internal.pages.base.CommonPage
import com.maybank.mae.internal.pages.base.HomePage

import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

fistVerifyData = ListUtils.parseObjectToList(fistVerifyData)
MAEWalletExistData = ListUtils.parseObjectToList(MAEWalletExistData)
personalData = ListUtils.parseObjectToList(personalData)

Map maeAccountData = MapUtils.parseObjectToMap(maeAccountData)
Map inputRequestCardStepTwoData = MapUtils.parseObjectToMap(inputRequestCardStepTwoData)
Map employment = MapUtils.parseObjectToMap(employment)
Map employmentName = MapUtils.parseObjectToMap(employmentName)
Map email = MapUtils.parseObjectToMap(email)
Map nationality = MapUtils.parseObjectToMap(nationality)


HomePage homePage = BasePage.getPage(HomePage)
ApplyPage applyPage = BasePage.getPage(ApplyPage)
ApplyAccountPage applyAccountPage = BasePage.getPage(ApplyAccountPage)
CommonPage commonPage = BasePage.getPage(CommonPage)
ApplyCardPage applyCardPage = BasePage.getPage(ApplyCardPage)

'Navigate To Login'
CustomKeywords.'com.maybank.mae.pages.Onboarding.navigateToLogin'()

'Tap Apply Button'
homePage.tapApply()

'Select Account Type'
applyAccountPage.selectAccountType(accountType)

'Verify First Screen'
commonPage.verifyElements(fistVerifyData, commonPage.lblDynamicPath(), GlobalVariable.G_timeoutMedium)
commonPage.tapNextBtn()
commonPage.waitLoading()

'Fill information'
applyAccountPage.fillInformationMAEAccount(maeAccountData)

'Navigate to next Screen'
commonPage.tapContinueBtn()
commonPage.waitLoading()

'Choose type Passport'
applyAccountPage.chooseIdType(idType)

'Generate and fill in Passport data'
applyAccountPage.generateAndFillPassportInfo(nationality)

'Navigate to next Screen'
commonPage.tapContinueBtn()

'Request Card Step Two'
applyAccountPage.generateAndFillInformationResidential(inputRequestCardStepTwoData, state)

'Navigate to next Screen'
commonPage.tapContinueBtn()

'Choose employment'
applyAccountPage.fillInEmploymentData(employment, employmentName)

'Navigate to next Screen'
commonPage.tapContinueBtn()

'Navigate to scan Passport Screen'
applyAccountPage.navigateToMyKadVerification(email)

