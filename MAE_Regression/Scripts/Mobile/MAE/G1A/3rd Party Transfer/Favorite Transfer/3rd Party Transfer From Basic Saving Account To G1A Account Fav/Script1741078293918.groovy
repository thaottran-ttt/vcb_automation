import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase

import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

WebUI.callTestCase(findTestCase('Mobile/MAE/G1A/3rd Party Transfer/Common/3rd Party Transfer'), [('failStopTestSuite') : failStopTestSuite
	, ('skipRestartAfterTestCase') : skipRestartAfterTestCase, ('username') : username, ('password') : password
	, ('tabName') : tabName, ('accountName') : accountName, ('amount') : amount, ('recipientReference') : recipientReference, ('status') : status
	, ('idNumber') : idNumber, ('PAN') : PAN, ('title') : title, ('recipientAccount') : recipientAccount, ('isFuture') : isFuture, ('accountNo') : accountNo
	, ('transferTab') : transferTab, ('tranferName') : tranferName, ('pushNotiTitle') : pushNotiTitle, ('pushNotiMsg') : pushNotiMsg, ('history') : history
	, ('favName'): favName],
FailureHandling.STOP_ON_FAILURE)
