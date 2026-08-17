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


import com.maybank.mae.internal.pages.base.CommonPage
import com.maybank.mae.internal.pages.base.BasePage
import com.maybank.utils.*
import com.maybank.assertions.*

CommonPage commonPage = BasePage.getPage(CommonPage)
BaseAssert Assert = new BaseAssert()
FixedDepositPage fixedDepositPage = BasePage.getPage(FixedDepositPage)

'1. Verify Pricipal amount'
def getPricipalAmount = fixedDepositPage.getCardDetailInfo("Pricipal amount", timeout, FailureHandling.CONTINUE_ON_FAILURE)
Assert.equals(getPricipalAmount == pricipalAmount, true, 'Verify Pricipal amount', FailureHandling.CONTINUE_ON_FAILURE)

'2. Verify Tenure'
def getTenure = fixedDepositPage.getCardDetailInfo("Tenure", timeout, FailureHandling.CONTINUE_ON_FAILURE)
Assert.equals(getTenure == tenure, true, 'Verify Tenure', FailureHandling.CONTINUE_ON_FAILURE)

'3. Verify Profit rate'
if(fdAccountType== fixedDepositPage.GIAi){
	def ProfitRate = fixedDepositPage.getCardDetailInfo("Profit rate", timeout, FailureHandling.CONTINUE_ON_FAILURE)
	Assert.equals(ProfitRate == profitRate, true, "Verify Profit rate", FailureHandling.CONTINUE_ON_FAILURE)
}

'4. Verify Manturity date'
def getManturityDate = fixedDepositPage.getCardDetailInfo("Manturity date", timeout, FailureHandling.CONTINUE_ON_FAILURE)
Assert.equals(getManturityDate == manturityDate, true, "Verify Manturity date", FailureHandling.CONTINUE_ON_FAILURE)

'5. Verify Profit payment frequency'
def getProfitPaymentFrequency = fixedDepositPage.getCardDetailInfo("Profit payment frequency", timeout, FailureHandling.CONTINUE_ON_FAILURE)
Assert.equals(getProfitPaymentFrequency == profitPaymentFrequency, true, "Verify Profit payment frequency", FailureHandling.CONTINUE_ON_FAILURE)

'6. Verify Profit payment mode'
def getProfitPaymentMode = fixedDepositPage.getCardDetailInfo("Profit payment mode", timeout, FailureHandling.CONTINUE_ON_FAILURE)
Assert.equals(getProfitPaymentMode == profitPaymentMode, true, "Verify Profit payment mode", FailureHandling.CONTINUE_ON_FAILURE)

'7. Verify Maturity instruction'
def getMaturityInstruction = fixedDepositPage.getCardDetailInfo("Maturity instruction", timeout, FailureHandling.CONTINUE_ON_FAILURE)
Assert.equals(getMaturityInstruction == maturityInstruction, true, "Verify Maturity instruction", FailureHandling.CONTINUE_ON_FAILURE)

'8. Verify Account holder(s) Details'
def getAccountHolder = fixedDepositPage.getCardDetailInfo("Account holder(s) Details", timeout, FailureHandling.CONTINUE_ON_FAILURE)
Assert.equals(getAccountHolder == accountHolderDetail, true, "Verify Account holder(s) Details", FailureHandling.CONTINUE_ON_FAILURE)

'9. Verify Primary account holder'
def getPrimaryAccountHolder = fixedDepositPage.getCardDetailInfo("Primary account holder", timeout, FailureHandling.CONTINUE_ON_FAILURE)
Assert.equals(getPrimaryAccountHolder == primaryAccountHolder, true, "Verify Primary account holder", FailureHandling.CONTINUE_ON_FAILURE)

'10. Verify RAHMAN BIN RONTA'
def getName = fixedDepositPage.getCardDetailInfo("RAHMAN BIN RONTA", timeout, FailureHandling.CONTINUE_ON_FAILURE)
Assert.equals(getName == fdName, true, "Verify RAHMAN BIN RONTA account", FailureHandling.CONTINUE_ON_FAILURE)
