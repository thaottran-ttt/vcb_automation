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
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

import com.vcb.mobile.internal.pages.base.BasePage
import com.vcb.mobile.internal.pages.base.CommonPage
import com.vcb.mobile.internal.pages.base.applypage.ApplyPage
import com.vcb.mobile.internal.pages.base.accountpage.AccountsPage
import com.vcb.utils.*
import com.vcb.assertions.*

CommonPage commonPage = BasePage.getPage(CommonPage)
BaseAssert Assert = new BaseAssert()
ApplyPage applyPage = BasePage.getPage(ApplyPage)

'VP: Verify information'
commonPage.verifyElementExist(commonPage.lblNormalizeSpace(fdAccountType), timeout, FailureHandling.CONTINUE_ON_FAILURE)

commonPage.verifyElementExist(commonPage.lblNormalizeSpace("RM $amount"), timeout, FailureHandling.CONTINUE_ON_FAILURE)

applyPage.verifyConfirmationInfo('Transfer from', accountFrom, timeout, FailureHandling.CONTINUE_ON_FAILURE)

applyPage.verifyConfirmationInfo('Maturity instruction', maturityInstruction, timeout, FailureHandling.CONTINUE_ON_FAILURE)

if ((profitPaymentMode == 'Credit to Account') || (maturityInstruction == 'Credit to Account')) {
	applyPage.verifyConfirmationInfo('Crediting account', creditingAccount, timeout, FailureHandling.CONTINUE_ON_FAILURE)
}

applyPage.verifyConfirmationInfo('Campaign', campaign, timeout, FailureHandling.CONTINUE_ON_FAILURE)

applyPage.verifyConfirmationInfo('Tenure', tenure, timeout, FailureHandling.CONTINUE_ON_FAILURE)

applyPage.verifyConfirmationInfo('Payment frequency', profitPaymentFrequency, timeout, FailureHandling.CONTINUE_ON_FAILURE)

applyPage.verifyConfirmationInfo('Payment mode', profitPaymentMode, timeout, FailureHandling.CONTINUE_ON_FAILURE)

if (fdAccountType == applyPage.GIAi) {
	applyPage.verifyConfirmationInfo('Profit sharing ratio (Customer:Bank)', profitSharingRatio, timeout, FailureHandling.CONTINUE_ON_FAILURE)
}

commonPage.tapButton('Make Placement', timeout, FailureHandling.STOP_ON_FAILURE)