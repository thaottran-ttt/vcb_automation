import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.maybank.mae.internal.pages.base.accountpage.AccountDetailPage as AccountDetailPage
import com.maybank.mae.internal.pages.base.accountpage.AccountsPage as AccountsPage
import com.maybank.mae.internal.pages.base.BasePage as BasePage
import com.maybank.mae.internal.pages.base.CommonPage as CommonPage
import com.maybank.mae.internal.pages.base.sendandrequestpage.DuitNowRequestPage as DuitNowRequestPage
import com.maybank.mae.internal.pages.base.HomePage as HomePage
import com.maybank.mae.internal.pages.base.StatusScreenPage as StatusScreenPage

DuitNowRequestPage dutiNowRequestPage = BasePage.getPage(DuitNowRequestPage)

WebUI.callTestCase(findTestCase('Test Cases/Mobile/MAE/Request To Pay/DuitNow Request/Common/DuitNow Request'), [('idNumber') : idNumber
        , ('PAN') : PAN, ('password') : password, ('username') : username, ('accountName') : accountName, ('recipientReference') : recipientReference
        , ('amount') : amount, ('requestType') : requestType, ('inputTextValue') : inputTextValue, ('dropdownValue') : dropdownValue
        , ('status') : status], FailureHandling.STOP_ON_FAILURE)

'Back to entry point'
dutiNowRequestPage.backToEntryPoint()