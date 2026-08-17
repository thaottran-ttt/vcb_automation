import com.vcb.mobile.internal.pages.base.applypage.ApplyPersonalLoanPage as ApplyPersonalLoanPage
import com.vcb.mobile.internal.pages.base.BasePage as BasePage
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase

import com.kms.katalon.core.model.FailureHandling

ApplyPersonalLoanPage applyPersonalLoanPage = BasePage.getPage(ApplyPersonalLoanPage)

WebUI.callTestCase(findTestCase('Mobile/VCB/Common/Login Secure2u'), [('idNumber') : idNumber, ('PAN') : PAN, ('password') : password
	, ('username') : username, ('accountName') : accountName], FailureHandling.STOP_ON_FAILURE)

applyPersonalLoanPage.navigatePersonalLoan(password)

String step = applyPersonalLoanPage.getStep()

switch (step) {
    case 'Step 1 of 9':
        applyPersonalLoanPage.inputValueStep1()
    case 'Step 3 of 9':
        applyPersonalLoanPage.inputValueStep3()
    case 'Step 4 of 9':
        applyPersonalLoanPage.inputValueStep4()
    case 'Step 5 of 9':
        applyPersonalLoanPage.inputValueStep5()
    case 'Step 6 of 9':
        applyPersonalLoanPage.inputValueStep6()
    case 'Step 7 of 9':
        applyPersonalLoanPage.inputValueStep7()
    case 'Step 8 of 9':
        applyPersonalLoanPage.inputValueStep8()
    case 'Step 9 of 9':
        applyPersonalLoanPage.inputValueStep9()
    case 'Confirmation':
        applyPersonalLoanPage.comfirmationStep()
	case 'Upload Documents':
		applyPersonalLoanPage.uploadFileStep()
		String referenceID = applyPersonalLoanPage.verifyAndGetReferenceID()
}