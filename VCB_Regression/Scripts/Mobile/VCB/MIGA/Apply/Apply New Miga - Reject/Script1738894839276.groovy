import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase

import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI



WebUI.callTestCase(findTestCase('Mobile/VCB/MIGA/Apply/Apply New Miga - Approve'), [('username') : username, ('password') : password
        , ('idNumber') : idNumber, ('PAN') : PAN, ('tabName') :tabName, ('accountType') : accountType
        , ('agreeRadio') : agreeRadio, ('status') : status, ('addressMap') : addressMap
        , ('verifyStatusScreenTitleData') : verifyStatusScreenTitleData, ('verifyStatusScreenDescriptionData') : verifyStatusScreenDescriptionData], FailureHandling.STOP_ON_FAILURE)



