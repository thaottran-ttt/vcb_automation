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
import com.vcb.mobile.internal.pages.base.accountpage.AccountsPage
import com.vcb.utils.ListUtils
import com.kms.katalon.core.util.KeywordUtil

import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

List titleStatusScreenApproveData = ListUtils.parseObjectToList(titleStatusScreenApproveData)
List descriptionStatusScreenApproveData = ListUtils.parseObjectToList(descriptionStatusScreenApproveData)
List titleStatusScreenRejectData = ListUtils.parseObjectToList(titleStatusScreenRejectData)
List descriptionStatusScreenRejectData = ListUtils.parseObjectToList(descriptionStatusScreenRejectData)
List titleStatusScreenExpiredData = ListUtils.parseObjectToList(titleStatusScreenExpiredData)
List descriptionStatusScreenExpiredData = ListUtils.parseObjectToList(descriptionStatusScreenExpiredData)

HomePage homePage = BasePage.getPage(HomePage)
ApplyPage applyPage = BasePage.getPage(ApplyPage)
ApplyCardPage applyCardPage = BasePage.getPage(ApplyCardPage)
CommonPage commonPage = BasePage.getPage(CommonPage)
Secure2uAuthorisationPage secure2uAuthorisationPage = BasePage.getPage(Secure2uAuthorisationPage)
StatusScreenPage statusScreenPage = BasePage.getPage(StatusScreenPage)
AccountsPage accountsPage = BasePage.getPage(AccountsPage)

WebUI.callTestCase(findTestCase('Mobile/VCB/Common/Login Secure2u'), [('idNumber') : idNumber, ('PAN') : PAN, ('password') : password
	, ('username') : username, ('accountName') : '', ('flowControl') : com.vcb.common.FlowControlEnum.STOP, ('failStopTestSuite') : false
	, ('yourName') : 'Test'], FailureHandling.STOP_ON_FAILURE)

'Tap Accounts Button'
homePage.tapBtnAccounts()

'Select Cards Tab'
applyPage.selectTab(tabName)

'Tap Card To Be Activated'
commonPage.tapButton("Activate Now")

if(cardName == "VCB Visa Card") {
	'Enter Activation Code'
	applyCardPage.enterPIN("Enter your activation code", activationCode)
	
} else {
	'Enter Card Last 8-digit for Debit Card'
	applyCardPage.enterPIN("Enter your card's last 8 digits", cardLastDigit)
}

'Create 6-digit Card Pin'
applyCardPage.enterPIN("Enter your 6-digit PIN", cardPin)

'Re-enter 6-digit Card Pin'
applyCardPage.enterPIN("Re-enter your 6-digit PIN", cardPin)

'S2u'
secure2uAuthorisationPage.authorize(s2uStatus)

'Status Screen'
switch (s2uStatus) {

	case ApplyCardPage.APPROVE:
		statusScreenPage.verifyStatusScreen(titleStatusScreenApproveData, descriptionStatusScreenApproveData)
		break

	case ApplyCardPage.REJECT:
		statusScreenPage.verifyStatusScreen(titleStatusScreenRejectData, descriptionStatusScreenRejectData)
		break

	case ApplyCardPage.EXPIRED:
		statusScreenPage.verifyStatusScreen(titleStatusScreenExpiredData, descriptionStatusScreenExpiredData)
		break
}

'Tap Done button'
commonPage.tapDoneBtn()

'Tap Accounts button'
homePage.tapBtnAccounts()

'Select Cards Tab'
applyPage.selectTab(tabName)

if(s2uStatus == ApplyCardPage.APPROVE) {
	'Select Card'
	applyCardPage.selectCard(cardName)
}
