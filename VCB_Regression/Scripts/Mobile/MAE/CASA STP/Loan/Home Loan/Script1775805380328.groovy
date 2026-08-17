import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase

import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.maybank.common.MobileDriver
import com.maybank.mae.internal.pages.base.applypage.ApplyHomeLoanPage
import com.maybank.mae.internal.pages.base.BasePage
import com.maybank.mae.internal.pages.base.HomePage
import internal.GlobalVariable

ApplyHomeLoanPage applyHomeLoanPage = BasePage.getPage(ApplyHomeLoanPage)
HomePage homePage = BasePage.getPage(HomePage)

'Check if already login -> skip'
if (!homePage.checkIsAccountLogin()) {
	WebUI.callTestCase(findTestCase('Mobile/MAE/Common/Login'), [('password') : password, ('username') : username], FailureHandling.STOP_ON_FAILURE)
}

'Navigate to Home Loan'
applyHomeLoanPage.navigateHomeLoan()

'Step 1 of 8'
applyHomeLoanPage.inputValueStep1()

'Step 2 of 8'
applyHomeLoanPage.inputValueStep2()

'Step 3 of 8'
applyHomeLoanPage.inputValueStep3()

'Step 4 of 8'
applyHomeLoanPage.inputValueStep4()

'Step 5 of 8'
applyHomeLoanPage.inputValueStep5()

'Step 6 of 8'
applyHomeLoanPage.inputValueStep6(password)

'Confirm Apply finanacing'
applyHomeLoanPage.inputValueApplyFinanacingPage()

'Step 1 after confirm'
applyHomeLoanPage.inputValueAfterConfirmStep1()
'Term & Conditions'
if (MobileDriver.waitForElementPresent(ApplyHomeLoanPage.commonPage.lblDynamic('Terms & Conditions'), GlobalVariable.G_timeoutMedium, FailureHandling.OPTIONAL)) {
	'Term & Conditions'
	applyHomeLoanPage.inputValueTermConditions()
	
	'Confirmation'
	applyHomeLoanPage.inputValueConfirmation()
	
	'View Application'
	applyHomeLoanPage.viewApplication()
}else {
	'Step 2 after confirm'
	applyHomeLoanPage.inputValueAfterConfirmStep2()
	
	'Step 3 after confirm'
	applyHomeLoanPage.inputValueAfterConfirmStep3()
	
	'Term & Conditions'
	applyHomeLoanPage.inputValueTermConditions()
	
	'Confirmation'
	applyHomeLoanPage.inputValueConfirmation()
	
	'View Application'
	applyHomeLoanPage.viewApplication()
}


