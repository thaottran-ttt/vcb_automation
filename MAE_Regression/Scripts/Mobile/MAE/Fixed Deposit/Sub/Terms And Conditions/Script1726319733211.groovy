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
import com.maybank.mae.internal.pages.base.applypage.ApplyPage
import com.maybank.mae.internal.pages.base.BasePage
import com.maybank.utils.*
import com.maybank.assertions.*

CommonPage commonPage = BasePage.getPage(CommonPage)
BaseAssert Assert = new BaseAssert()
ApplyPage applyPage = BasePage.getPage(ApplyPage)

commonPage.verifyElementExist(commonPage.lblNormalizeSpace("Terms & Conditions"), timeout, FailureHandling.CONTINUE_ON_FAILURE)

switch(fdAccountType) {
	case applyPage.islamicFD:
		commonPage.verifyElementExist(commonPage.lblNormalizeSpace("I have read the PIDM’s DIS brochure and understand this product is protected by PIDM up to RM 250,000 for each depositor."), timeout, FailureHandling.CONTINUE_ON_FAILURE)
		commonPage.verifyElementExist(commonPage.lblNormalizeSpace("I agree with the Statement and accept the Terms & Conditions for this product."), timeout, FailureHandling.CONTINUE_ON_FAILURE)
		commonPage.verifyElementExist(commonPage.lblNormalizeSpace("Islamic Fixed Deposit Rates Apply"), timeout, FailureHandling.CONTINUE_ON_FAILURE)
		
		break
	case applyPage.PNAi:
		commonPage.verifyElementExist(commonPage.lblNormalizeSpace("I have read the PIDM’s DIS brochure and understand this product is protected by PIDM up to RM 250,000 for each depositor."), timeout, FailureHandling.CONTINUE_ON_FAILURE)
		commonPage.verifyElementExist(commonPage.lblNormalizeSpace("I agree with the Statement and accept the Terms & Conditions for this product. "), timeout, FailureHandling.CONTINUE_ON_FAILURE)
		commonPage.verifyElementExist(commonPage.lblNormalizeSpace("Prosperous Now! Account-i (PNA-i) Rates Applies"), timeout, FailureHandling.CONTINUE_ON_FAILURE)
		break
	case applyPage.GIAi:
		commonPage.verifyElementExist(commonPage.lblNormalizeSpace("I have read the PIDM’s DIS brochure and understand that this product is not protected by Perbadanan Insuran Deposit Malaysia (PIDM)"), timeout, FailureHandling.CONTINUE_ON_FAILURE)
		commonPage.verifyElementExist(commonPage.lblNormalizeSpace("I have read and agreed with General Investment Account-i (GIA-i) Terms & Conditions, This Statement and Product Disclosure Sheet."), timeout, FailureHandling.CONTINUE_ON_FAILURE)
		commonPage.verifyElementExist(commonPage.lblNormalizeSpace("GIA-i Rates Applies"), timeout, FailureHandling.CONTINUE_ON_FAILURE)
		break
	case applyPage.conventionalFD:
		commonPage.verifyElementExist(commonPage.lblNormalizeSpace("I have read the PIDM’s DIS brochure and understand this product is protected by PIDM up to RM 250,000 for each depositor."), timeout, FailureHandling.CONTINUE_ON_FAILURE)
		commonPage.verifyElementExist(commonPage.lblNormalizeSpace("I agree with the Terms & Conditions for this product."), timeout, FailureHandling.CONTINUE_ON_FAILURE)
		commonPage.verifyElementExist(commonPage.lblNormalizeSpace("Maybank Conventional Fixed Deposit Rates Apply"), timeout, FailureHandling.CONTINUE_ON_FAILURE)
		break
}

commonPage.verifyElementExist(commonPage.lblNormalizeSpace("I have read, agree and accept the terms of the The Maybank Group Personal Data Privacy Statement. For marketing of products and services by Maybank Group/Other Entities referred to in the PDPA Form for Individual Customers."), timeout, FailureHandling.CONTINUE_ON_FAILURE)
commonPage.verifyElementExist(commonPage.lblNormalizeSpace("With regards to promotional and marketing materials:"), timeout, FailureHandling.CONTINUE_ON_FAILURE)
commonPage.verifyElementExist(commonPage.lblNormalizeSpace(applyPage.yesTnC), timeout, FailureHandling.CONTINUE_ON_FAILURE)
commonPage.verifyElementExist(commonPage.lblNormalizeSpace(applyPage.noTnC), timeout, FailureHandling.CONTINUE_ON_FAILURE)

applyPage.checkTermAndCondition(termCheck, timeout, FailureHandling.STOP_ON_FAILURE)

commonPage.tapButton('Agree & Confirm', timeout, FailureHandling.STOP_ON_FAILURE)

////GIAi
//"Terms & Conditions"
////"I have read the PIDM’s DIS brochure and understand that this product is not protected by Perbadanan Insuran Deposit Malaysia (PIDM)"
////"I have read and agreed with General Investment Account-i (GIA-i) Terms & Conditions, This Statement and Product Disclosure Sheet."
////"GIA-i Rates Applies"
//"I have read, agree and accept the terms of the The Maybank Group Personal Data Privacy Statement. For marketing of products and services by Maybank Group/Other Entities referred to in the PDPA Form for Individual Customers."
//"With regards to promotional and marketing materials:"
//
//
//
////islamic
////"I have read the PIDM’s DIS brochure and understand this product is protected by PIDM up to RM 250,000 for each depositor."
////"I agree with the Statement and accept the Terms & Conditions for this product."
////"Islamic Fixed Deposit Rates Apply"
////"I have read, agree and accept the terms of the The Maybank Group Personal Data Privacy Statement. For marketing of products and services by Maybank Group/Other Entities referred to in the PDPA Form for Individual Customers."
//"With regards to promotional and marketing materials:"
//
////PNAi
////"I have read the PIDM’s DIS brochure and understand this product is protected by PIDM up to RM 250,000 for each depositor."
////"I agree with the Statement and accept the Terms & Conditions for this product. "
////"Prosperous Now! Account-i (PNA-i) Rates Applies"
//"I have read, agree and accept the terms of the The Maybank Group Personal Data Privacy Statement. For marketing of products and services by Maybank Group/Other Entities referred to in the PDPA Form for Individual Customers."
//"With regards to promotional and marketing materials:"
//
////Convention
////"I have read the PIDM’s DIS brochure and understand this product is protected by PIDM up to RM 250,000 for each depositor."
////"I agree with the Terms & Conditions for this product."
////"Maybank Conventional Fixed Deposit Rates Apply"
//"I have read, agree and accept the terms of the The Maybank Group Personal Data Privacy Statement. For marketing of products and services by Maybank Group/Other Entities referred to in the PDPA Form for Individual Customers."
//"With regards to promotional and marketing materials:"