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
import com.maybank.common.MobileDriver
import com.maybank.mae.internal.pages.base.BasePage
import com.maybank.mae.internal.pages.base.CommonPage
import com.maybank.mae.internal.pages.base.ConfirmationPage
import com.maybank.mae.internal.pages.base.HomePage
import com.maybank.mae.internal.pages.base.Secure2uAuthorisationPage
import com.maybank.mae.internal.pages.base.StatusScreenPage
import com.maybank.mae.internal.pages.base.applypage.ApplyAccountPage
import com.maybank.mae.internal.pages.base.applypage.ApplyPage

import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

HomePage homePage = BasePage.getPage(HomePage)
ApplyPage applyPage = BasePage.getPage(ApplyPage)
ApplyAccountPage applyAccountPage = BasePage.getPage(ApplyAccountPage)
CommonPage commonPage = BasePage.getPage(CommonPage)
ConfirmationPage confirmationPage = BasePage.getPage(ConfirmationPage)
StatusScreenPage statusScreenPage = BasePage.getPage(StatusScreenPage)
Secure2uAuthorisationPage secure2uAuthorisationPage = BasePage.getPage(Secure2uAuthorisationPage)

WebUI.callTestCase(findTestCase('Mobile/MAE/Common/Login Secure2u'), [('idNumber') : idNumber, ('PAN') : PAN, ('password') : password
	, ('username') : username, ('accountName') : '', ('flowControl') : com.maybank.common.FlowControlEnum.STOP, ('failStopTestSuite') : false
	, ('yourName') : 'Test'], FailureHandling.STOP_ON_FAILURE)

'Tap Apply Button'
homePage.tapApply()

'Select Cards Tab'
applyPage.selectTab(tabName)

'Select Account Type'
applyAccountPage.selectAccountType(accountType)

'Verify First Screen'
commonPage.verifyElements(fistVerifyData, commonPage.lblDynamicPath(), GlobalVariable.G_timeoutMedium)
applyAccountPage.tapApplyNow()
commonPage.waitLoading()

'Verify Step One'
applyAccountPage.verifyStep1SavingAccounti(stepOneUIData, inputStepOneData)
commonPage.delay(GlobalVariable.G_timeoutMiniShort)

'Verify Step Two'
commonPage.verifyElements(stepTwoUIData, commonPage.lblDynamicPath(), GlobalVariable.G_timeoutMedium)
commonPage.tapNextBtn()
commonPage.delay(GlobalVariable.G_timeoutMiniShort)

'Verify Step Three'
commonPage.verifyElements(stepThreeUIData, commonPage.lblDynamicPath(), GlobalVariable.G_timeoutMedium)
commonPage.inputDropdownMapDefault(drpstepThreeData)
commonPage.tapNextBtn()
commonPage.delay(GlobalVariable.G_timeoutMiniShort)

'Verify Step Four'
commonPage.verifyElements(stepFourUIData, commonPage.lblDynamicPath(), GlobalVariable.G_timeoutMedium)
applyAccountPage.verifyYippieSavingStepFour(inputStepFourData, confirmStepFour)
commonPage.delay(GlobalVariable.G_timeoutMiniShort)

'Verify Step Five'
commonPage.verifyElements(stepFiveUIData, commonPage.lblDynamicPath(), GlobalVariable.G_timeoutMedium)
applyAccountPage.verifyYippieSavingStepFive(inputStepFiveData, gender, drpstepFiveData)
commonPage.delay(GlobalVariable.G_timeoutMiniShort)

'Verify Child’s Birth Certificate'
applyAccountPage.verifyChildBirthCertificate(stepSixUIData)

'Verify Agree and Confirm'
applyAccountPage.verifyAgreeAndConfirm(agreeAndConfirmUIData, agreeRadio)

'Verify Confirmation'
applyAccountPage.verifyPMAConfirmation(confirmUIData)
commonPage.delay(GlobalVariable.G_timeoutMiniShort)

'Verify Confirmation'
confirmationPage.verifyConfirmation(confirmUISecondData, confirmNextPage, accountName, amount)

'Verify insufficient fund message'
MobileDriver.verifyElementVisible(commonPage.lblNormalizeSpace(insufficientFundMessage), GlobalVariable.G_timeoutLong, FailureHandling.STOP_ON_FAILURE)
commonPage.delay(GlobalVariable.G_timeoutMiniShort)

'Tap close button'
MobileDriver.tap(commonPage.imgNoDynamic(2), GlobalVariable.G_timeoutMedium)

'Back to Home Page'
commonPage.backToHome()