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


import com.vcb.mobile.internal.pages.base.CommonPage
import com.vcb.mobile.internal.pages.base.applypage.ApplyPage
import com.vcb.mobile.internal.pages.base.BasePage
import com.vcb.utils.*
import com.vcb.assertions.*

CommonPage commonPage = BasePage.getPage(CommonPage)
BaseAssert Assert = new BaseAssert()
ApplyPage applyPage = BasePage.getPage(ApplyPage)
StringUtils strUtils = new StringUtils()

Boolean result = false
commonPage.verifyElementExist(commonPage.lblNormalizeSpace("Secure2u authorisation"), timeout, FailureHandling.CONTINUE_ON_FAILURE)
String dateTime = applyPage.getTransactionDateTime("Date & time", timeout, FailureHandling.CONTINUE_ON_FAILURE)
Assert.equals(true, strUtils.verifyDataFormat(dateTime, dateTimePattern), "Date follows the format DD MMM YYYY, HH:MM (A|P)M", FailureHandling.CONTINUE_ON_FAILURE)

commonPage.verifyElementExist(commonPage.lblNormalizeSpace("RM ${amount}"), timeout, FailureHandling.CONTINUE_ON_FAILURE)
commonPage.verifyElementExist(commonPage.lblNormalizeSpace(accountTransfer), timeout, FailureHandling.CONTINUE_ON_FAILURE)
commonPage.verifyElementExist(commonPage.lblNormalizeSpace("${fdAccountType} Placement" ), timeout, FailureHandling.CONTINUE_ON_FAILURE)

if(hasApproved) {
	commonPage.tapButton("Approve", timeout, FailureHandling.STOP_ON_FAILURE)
	commonPage.waitLoading(GlobalVariable.G_timeoutLong, FailureHandling.OPTIONAL)
	commonPage.waitForElementPresent(commonPage.lblNormalizeSpace("Transaction successful"), timeout, FailureHandling.CONTINUE_ON_FAILURE)
	commonPage.verifyElementExist(commonPage.lblNormalizeSpace("RM ${amount}"), timeout, FailureHandling.CONTINUE_ON_FAILURE)
	commonPage.verifyElementExist(commonPage.lblNormalizeSpace(accountTransfer), timeout, FailureHandling.CONTINUE_ON_FAILURE)
	commonPage.verifyElementExist(commonPage.lblContainDynamic(fdAccountType), timeout, FailureHandling.CONTINUE_ON_FAILURE)
	commonPage.verifyElementExist(commonPage.lblNormalizeSpace(dateTime), timeout, FailureHandling.CONTINUE_ON_FAILURE)
	result = true
}
else {
	commonPage.tapButton("Reject", timeout, FailureHandling.STOP_ON_FAILURE)
	commonPage.waitLoading(GlobalVariable.G_timeoutLong, FailureHandling.OPTIONAL)
	commonPage.waitForElementPresent(commonPage.lblNormalizeSpace("Transaction unsuccessful"), timeout, FailureHandling.CONTINUE_ON_FAILURE)
	commonPage.verifyElementExist(commonPage.lblNormalizeSpace("RM ${amount}"), timeout, FailureHandling.CONTINUE_ON_FAILURE)
	commonPage.verifyElementExist(commonPage.lblNormalizeSpace(accountTransfer), timeout, FailureHandling.CONTINUE_ON_FAILURE)
	commonPage.verifyElementExist(commonPage.lblNormalizeSpace("${fdAccountType} Placement" ), timeout, FailureHandling.CONTINUE_ON_FAILURE)
	commonPage.verifyElementExist(commonPage.lblNormalizeSpace(dateTime), timeout, FailureHandling.CONTINUE_ON_FAILURE)
	result = false
}

commonPage.tapButton("Done", timeout, FailureHandling.STOP_ON_FAILURE)
commonPage.waitLoading(GlobalVariable.G_timeoutLong, FailureHandling.OPTIONAL)

return result