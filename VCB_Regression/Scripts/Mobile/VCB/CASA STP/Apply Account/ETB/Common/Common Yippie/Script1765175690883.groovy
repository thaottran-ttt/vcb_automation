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
import com.vcb.mobile.internal.pages.base.applypage.ApplyAccountPage
import com.vcb.mobile.internal.pages.base.applypage.ApplyPage
import com.vcb.mobile.internal.pages.base.BasePage
import com.vcb.mobile.internal.pages.base.CommonPage
import com.vcb.mobile.internal.pages.base.ConfirmationPage
import com.vcb.mobile.internal.pages.base.HomePage
import com.vcb.mobile.internal.pages.base.Secure2uAuthorisationPage
import com.vcb.mobile.internal.pages.base.StatusScreenPage

import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys
import com.vcb.utils.ListUtils as ListUtils
import com.vcb.utils.MapUtils

HomePage homePage = BasePage.getPage(HomePage)
ApplyPage applyPage = BasePage.getPage(ApplyPage)
ApplyAccountPage applyAccountPage = BasePage.getPage(ApplyAccountPage)
CommonPage commonPage = BasePage.getPage(CommonPage)
ConfirmationPage confirmationPage = BasePage.getPage(ConfirmationPage)
Secure2uAuthorisationPage secure2uAuthorisationPage = BasePage.getPage(Secure2uAuthorisationPage)
StatusScreenPage statusScreenPage = BasePage.getPage(StatusScreenPage)

List agreeAndConfirmUIData = ListUtils.parseObjectToList(agreeAndConfirmUIData)
List stepSixUIData = ListUtils.parseObjectToList(stepSixUIData)
List fistVerifyData = ListUtils.parseObjectToList(fistVerifyData)
List statusScreenTitle                = ListUtils.parseObjectToList(statusScreenTitle)
List statusScreenDescription          = ListUtils.parseObjectToList(statusScreenDescription)

Map inputStepFourData = MapUtils.parseObjectToMap(inputStepFourData)

WebUI.callTestCase(findTestCase('Mobile/VCB/CASA STP/Apply Account/ETB/Common/ETB Entry Point'), [('username') : username, ('password') : password
        , ('idNumber') : idNumber, ('PAN') : PAN, ('tabName') : tabName, ('accountType') : accountType, ('fistVerifyData') : fistVerifyData], 
    FailureHandling.STOP_ON_FAILURE)

'Verify First Screen'
commonPage.verifyElements(fistVerifyData, commonPage.lblDynamicPath(), GlobalVariable.G_timeoutMedium)
applyAccountPage.tapApplyNow()
commonPage.waitLoading()

'Check if product exist'
if(applyAccountPage.checkSavingAccountIExist()) {
  commonPage.delay(GlobalVariable.G_timeoutMiniShort)
  commonPage.tapBack()
  'Back To Home'
  homePage.tapHomeMenu()
  return
}

'Verify Step One'
commonPage.verifyElements(stepOneUIData, commonPage.lblDynamicPath(), GlobalVariable.G_timeoutMedium)
commonPage.tapNextBtn()
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

'Skip the referral'
applyAccountPage.skipTheReferral()

'Confirm Secondary Account Holder'
applyAccountPage.verifySecondaryAccountHolder(secondaryAccountHolder)
commonPage.delay(GlobalVariable.G_timeoutShort)

'Verify Step Four'
applyAccountPage.verifyYippieStepFour(stepFourUIData, inputStepFourData, confirmStepFour)
commonPage.delay(GlobalVariable.G_timeoutMiniShort)

'Secondary Account Holder Screen'
applyAccountPage.verifyYippieStepFive(inputStepFiveData, gender, drpStepFive)
commonPage.delay(GlobalVariable.G_timeoutMiniShort)

'Verify Agree and Confirm'
applyAccountPage.verifyAgreeAndConfirm(agreeAndConfirmUIData, agreeRadio)

'Verify Confirmation'
applyAccountPage.verifyPMAConfirmation(confirmUIData)
commonPage.delay(GlobalVariable.G_timeoutMiniShort)

'Verify Child’s Birth Certificate - skip this one'
applyAccountPage.verifyChildBirthCertificate(stepSixUIData)

'Verify Confirmation -skip now'
confirmationPage.verifyConfirmation(confirmUISecondData, confirmNextPage)

'S2U Authorization + Status Screen'
if (confirmationPage.verifyInsufficientMessage()) {
	return
} else {
	'S2u'
	secure2uAuthorisationPage.authorize(s2uStatus)
	
	'Verify Status Screen'
	commonPage.verifySomeElementsMatchInList(statusScreenTitle, commonPage.lblContainDynamicPath(), GlobalVariable.G_timeoutShort)
	commonPage.verifySomeElementsMatchInList(statusScreenDescription, commonPage.lblContainDynamicPath(), GlobalVariable.G_timeoutShort)
	
	'Tap Got It Button'
	commonPage.tapText('Got It', GlobalVariable.G_timeoutMedium)
	
	'Back To Home'
	homePage.tapHomeMenu()
}