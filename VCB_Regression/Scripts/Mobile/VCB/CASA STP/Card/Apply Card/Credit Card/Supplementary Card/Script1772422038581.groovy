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
import com.vcb.mobile.internal.pages.base.applypage.ApplyPage
import com.vcb.mobile.internal.pages.base.BasePage
import com.vcb.mobile.internal.pages.base.applypage.ApplyCardPage
import com.vcb.mobile.internal.pages.base.CommonPage
import com.vcb.mobile.internal.pages.base.HomePage
import com.vcb.mobile.internal.pages.base.Secure2uAuthorisationPage
import com.vcb.mobile.internal.pages.base.StatusScreenPage
import com.vcb.utils.ListUtils

import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

titleStatusScreenApproveData = ListUtils.parseObjectToList(titleStatusScreenApproveData)
descriptionStatusScreenApproveData = ListUtils.parseObjectToList(descriptionStatusScreenApproveData)
titleStatusScreenRejectData = ListUtils.parseObjectToList(titleStatusScreenRejectData)
descriptionStatusScreenRejectData = ListUtils.parseObjectToList(descriptionStatusScreenRejectData)
titleStatusScreenExpiredData = ListUtils.parseObjectToList(titleStatusScreenExpiredData)
descriptionStatusScreenExpiredData = ListUtils.parseObjectToList(descriptionStatusScreenExpiredData)
uploadDocumentStatusData = ListUtils.parseObjectToList(uploadDocumentStatusData)

WebUI.callTestCase(findTestCase('Mobile/VCB/Common/Login Secure2u'), [('idNumber') : idNumber, ('PAN') : PAN, ('password') : password
        , ('username') : username, ('accountName') : '', ('flowControl') : com.vcb.common.FlowControlEnum.STOP, ('failStopTestSuite') : false
        , ('yourName') : 'Test'], FailureHandling.STOP_ON_FAILURE)

HomePage homePage = BasePage.getPage(HomePage)
ApplyPage applyPage = BasePage.getPage(ApplyPage)
ApplyCardPage applyCardPage = BasePage.getPage(ApplyCardPage)
CommonPage commonPage = BasePage.getPage(CommonPage)
Secure2uAuthorisationPage secure2uAuthorisationPage = BasePage.getPage(Secure2uAuthorisationPage)
StatusScreenPage statusScreenPage = BasePage.getPage(StatusScreenPage)

'Tap Apply Button'
homePage.tapApply()

'Select Cards Tab'
applyPage.selectTab(tabName)

'Tap Card Type'
applyCardPage.selectCardType(cardType)

'Fill Details Step One'
def accountInfo = applyCardPage.supplementaryCCApplicationStepOne(stepOneData)
String supplementaryIDNumber    = accountInfo['Supplementary ID Number']
String supplementaryDateOfBirth = accountInfo['Supplementary date of birth']

'Fill Details Step Two'
def accountInfo2 = applyCardPage.supplementaryCCApplicationStepTwo(stepTwoData)
String fullName      = accountInfo2['Name']
String mobileNumber  = accountInfo2['Mobile number']

'Confirmation Page'
Mobile.delay(GlobalVariable.G_timeoutMiniShort)
Mobile.takeScreenshot()
commonPage.scrollToEnd()
Mobile.takeScreenshot()
commonPage.tapSaveAndContinue()

'Agree and confirm'
applyCardPage.agreeAndConfirm(agreeRadioLabel)

'S2u Authorization'
secure2uAuthorisationPage.authorize(s2uStatus)

'Upload Document Later'
applyCardPage.uploadDocument()

'Upload Document Status'
String RefID = applyCardPage.uploadDocumentStatus(uploadDocumentStatusData)

'Back to Home'
homePage.tapHomeMenu()