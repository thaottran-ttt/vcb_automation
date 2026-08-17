import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import com.kms.katalon.core.model.FailureHandling as FailureHandling

WebUI.callTestCase(findTestCase('Mobile/MAE/Tabung/Common/Tabung Common'), [('userName') : username, ('password') : password
        , ('action') : action, ('subAction') : subAction, ('idNumber') : idNumber, ('PAN') : PAN
        , ('tabungMap') : tabungMap, ('mobileNumberShareBill') : mobileNumberShareBill
        , ('isGroup') : isGroup, ('status') : status, ('statusTitle') : statusTitle, ('statusDescription') : []], 
    FailureHandling.STOP_ON_FAILURE)

