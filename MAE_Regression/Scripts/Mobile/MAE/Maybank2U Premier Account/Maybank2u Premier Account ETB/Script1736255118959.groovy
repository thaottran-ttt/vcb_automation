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
import com.maybank.mae.internal.pages.base.applypage.ApplyPage
import com.maybank.utils.ListUtils
import com.maybank.utils.MapUtils
import com.maybank.mae.internal.pages.base.BasePage
import com.maybank.mae.internal.pages.base.CommonPage
import com.maybank.mae.internal.pages.base.ConfirmationPage
import com.maybank.mae.internal.pages.base.HomePage
import com.maybank.mae.internal.pages.base.Secure2uAuthorisationPage
import com.maybank.mae.internal.pages.base.Secure2uPage
import com.maybank.mae.internal.pages.base.StatusScreenPage
import com.kms.katalon.core.util.KeywordUtil

import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

Map inputStepOneData = MapUtils.parseObjectToMap(inputStepOneData)
Map drpstepThreeData = MapUtils.parseObjectToMap(drpstepThreeData)

List fistVerifyData = ListUtils.parseObjectToList(fistVerifyData)
List stepOneUIData = ListUtils.parseObjectToList(stepOneUIData)
List stepTwoUIData = ListUtils.parseObjectToList(stepTwoUIData)
List stepThreeUIData = ListUtils.parseObjectToList(stepThreeUIData)
List confirmUISecondData = ListUtils.parseObjectToList(confirmUISecondData)
List agreeAndConfirmUIData = ListUtils.parseObjectToList(agreeAndConfirmUIData)
List confirmUIData = ListUtils.parseObjectToList(confirmUIData)
List statusScreenRejectTitle = ListUtils.parseObjectToList(statusScreenRejectTitle)
List statusScreenRejectDescription = ListUtils.parseObjectToList(statusScreenRejectDescription)


HomePage homePage = BasePage.getPage(HomePage)
ApplyPage applyPage = BasePage.getPage(ApplyPage)
ApplyAccountPage applyAccountPage = BasePage.getPage(ApplyAccountPage)
CommonPage commonPage = BasePage.getPage(CommonPage)
ConfirmationPage confirmationPage = BasePage.getPage(ConfirmationPage)
Secure2uAuthorisationPage secure2uAuthorisationPage = BasePage.getPage(Secure2uAuthorisationPage)
StatusScreenPage statusScreenPage = BasePage.getPage(StatusScreenPage)
Secure2uPage secure2uPage = BasePage.getPage(Secure2uPage)

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
commonPage.tapNextBtn()
if(applyAccountPage.checkMaybank2uPremierExist()) {
	commonPage.delay(GlobalVariable.G_timeoutMiniShort)
	commonPage.tapBack()
	'Back To Home'
	homePage.tapHomeMenu()
	
	return
}

'Verify Step One'
commonPage.verifyElements(stepOneUIData, commonPage.lblDynamicPath(), GlobalVariable.G_timeoutMedium)
'Input'
commonPage.inputTextListGroup(inputStepOneData)
commonPage.tapContinueBtn()
commonPage.delay(GlobalVariable.G_timeoutMiniShort)

'Verify Step Two'
commonPage.verifyElements(stepTwoUIData, commonPage.lblDynamicPath(), GlobalVariable.G_timeoutMedium)
commonPage.tapContinueBtn()
commonPage.delay(GlobalVariable.G_timeoutMiniShort)

'Verify Step Three'
commonPage.verifyElements(stepThreeUIData, commonPage.lblDynamicPath(), GlobalVariable.G_timeoutMedium)
commonPage.inputDropdownMapDefault(drpstepThreeData)
commonPage.tapContinueBtn()
commonPage.delay(GlobalVariable.G_timeoutMiniShort)

'Verify Agree and Confirm'
applyAccountPage.verifyAgreeAndConfirm(agreeAndConfirmUIData, agreeRadio)

'Verify Confirmation'
applyAccountPage.verifyMPAConfirmation(confirmUIData)
commonPage.delay(GlobalVariable.G_timeoutMiniShort)

'Verify Confirmation'
confirmationPage.verifyConfirmation(confirmUISecondData, confirmNextPage, accountName)

'S2u'
String result = secure2uAuthorisationPage.authorize(s2uStatus)
KeywordUtil.logInfo("check status ------ : "+result)

'Verify Status Screen'
statusScreenPage.verifyStatusScreen(statusScreenRejectTitle, statusScreenRejectDescription)

'Tap Done Button'
commonPage.tapDoneBtn()

'Back To Home'
homePage.tapHomeMenu()