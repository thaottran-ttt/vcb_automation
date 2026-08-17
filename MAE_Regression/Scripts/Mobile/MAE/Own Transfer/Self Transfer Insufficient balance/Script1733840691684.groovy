import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase

import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

WebUI.callTestCase(findTestCase('Mobile/MAE/Own Transfer/Self Transfer With Current Account'), [('flowControl') : com.maybank.common.FlowControlEnum.CONTINUE
        , ('failStopTestSuite') : false, ('skipRestartAfterTestCase') : true, ('username') : username, ('password') : password
        , ('tabName') : tabName, ('accountName') : accountName, ('amount') : amount, ('recipientReference') : recipientReference
        , ('status') : status, ('idNumber') : idNumber, ('PAN') : PAN, ('isFuture') : isFuture], FailureHandling.STOP_ON_FAILURE)

