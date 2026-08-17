import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase

import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.vcb.mobile.internal.pages.base.BasePage
import com.vcb.mobile.internal.pages.base.sendandrequestpage.DuitNowRequestPage

DuitNowRequestPage dutiNowRequestPage = BasePage.getPage(DuitNowRequestPage)

'Check is on Send & Request screen'
WebUI.callTestCase(findTestCase('Test Cases/Mobile/VCB/Request To Pay/DuitNow Request/Common/DuitNow Request'), [('idNumber') : idNumber
        , ('PAN') : PAN, ('password') : password, ('username') : username, ('accountName') : accountName, ('recipientReference') : recipientReference
        , ('amount') : amount, ('requestType') : requestType, ('inputTextValue') : inputTextValue, ('dropdownValue') : dropdownValue
        , ('status') : status], FailureHandling.STOP_ON_FAILURE)

dutiNowRequestPage.backToEntryPoint()