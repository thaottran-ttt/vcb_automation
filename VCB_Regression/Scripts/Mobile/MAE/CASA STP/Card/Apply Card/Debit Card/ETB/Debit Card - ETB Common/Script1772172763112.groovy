import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import javax.swing.plaf.ListUI

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
import com.maybank.mae.internal.pages.base.applypage.ApplyPage
import com.maybank.mae.internal.pages.base.BasePage
import com.maybank.mae.internal.pages.base.applypage.ApplyCardPage
import com.maybank.mae.internal.pages.base.CommonPage
import com.maybank.mae.internal.pages.base.HomePage
import com.maybank.mae.internal.pages.base.ConfirmationPage
import com.maybank.mae.internal.pages.base.Secure2uAuthorisationPage
import com.maybank.mae.internal.pages.base.StatusScreenPage
import com.maybank.utils.ListUtils
import com.maybank.common.MobileDriver

import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

debitCardApplicationStepOneData = ListUtils.parseObjectToList(debitCardApplicationStepOneData)
verifyConfirmationData = ListUtils.parseObjectToList(verifyConfirmationData)
titleStatusScreenApproveData = ListUtils.parseObjectToList(titleStatusScreenApproveData)
descriptionStatusScreenApproveData = ListUtils.parseObjectToList(descriptionStatusScreenApproveData)
titleStatusScreenRejectData = ListUtils.parseObjectToList(titleStatusScreenRejectData)
descriptionStatusScreenRejectData = ListUtils.parseObjectToList(descriptionStatusScreenRejectData)
titleStatusScreenExpiredData = ListUtils.parseObjectToList(titleStatusScreenExpiredData)
descriptionStatusScreenExpiredData = ListUtils.parseObjectToList(descriptionStatusScreenExpiredData)

WebUI.callTestCase(findTestCase('Mobile/MAE/Common/Login Secure2u'), [('idNumber') : idNumber, ('PAN') : PAN, ('password') : password
	, ('username') : username, ('accountName') : ''], FailureHandling.STOP_ON_FAILURE)

HomePage homePage = BasePage.getPage(HomePage)
ApplyPage applyPage = BasePage.getPage(ApplyPage)
ApplyCardPage applyCardPage = BasePage.getPage(ApplyCardPage)
CommonPage commonPage = BasePage.getPage(CommonPage)
ConfirmationPage confirmationPage = BasePage.getPage(ConfirmationPage)
Secure2uAuthorisationPage secure2uAuthorisationPage = BasePage.getPage(Secure2uAuthorisationPage)
StatusScreenPage statusScreenPage = BasePage.getPage(StatusScreenPage)

'Tap Apply Button'
homePage.tapApply()

'Select Cards Tab'
applyPage.selectTab(tabName)

'Tap Card Type'
applyCardPage.selectCardType(cardType)

'Verify Debit Card Application Step One'
applyCardPage.verifyDebitCardApplicationStepOne(debitCardApplicationStepOneData, debitCardType)

'Verify Debit Card Application Step Two'
applyCardPage.verifyDebitCardApplicationStepTwo(agreeTermAndConditions)

'Verify Confirmation Page'
if(MobileDriver.waitForElementPresent(commonPage.lblDynamic("Confirmation"), GlobalVariable.G_timeoutShort, FailureHandling.OPTIONAL)) {
	confirmationPage.verifyConfirmation(verifyConfirmationData, confirmNextPage)
}

's2u'
secure2uAuthorisationPage.authorize(s2uStatus)

'Status Screen'
switch (s2uStatus) {

	case ApplyCardPage.APPROVE:
		statusScreenPage.verifyStatusScreen(titleStatusScreenApproveData, descriptionStatusScreenApproveData, "MAE\\d{17}")
		break

	case ApplyCardPage.REJECT:
		statusScreenPage.verifyStatusScreen(titleStatusScreenRejectData, descriptionStatusScreenRejectData)
		break

	case ApplyCardPage.EXPIRED:
		statusScreenPage.verifyStatusScreen(titleStatusScreenExpiredData, descriptionStatusScreenExpiredData)
		break
}

'Tap Done'
commonPage.tapDoneBtn()

'Back to Home'
homePage.tapHomeMenu()