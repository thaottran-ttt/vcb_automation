import com.vcb.mobile.internal.pages.base.applypage.ApplyASNBLoanPage
import com.vcb.mobile.internal.pages.base.BasePage
import com.vcb.mobile.internal.pages.base.HomePage
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase

import com.kms.katalon.core.model.FailureHandling

ApplyASNBLoanPage applyHomeASNBPage = BasePage.getPage(ApplyASNBLoanPage)
HomePage homePage = BasePage.getPage(HomePage)

if (!homePage.checkIsAccountLogin()) {
	WebUI.callTestCase(findTestCase('Mobile/VCB/Common/Login Secure2u'), [('idNumber') : idNumber, ('PAN') : PAN, ('password') : password
		, ('username') : username, ('accountName') : accountName], FailureHandling.STOP_ON_FAILURE)
}

applyHomeASNBPage.navigateResumeAsnbLoan(password)