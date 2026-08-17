import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase

import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable as GlobalVariable

WebUI.callTestCase(findTestCase('Mobile/MAE/Tabung/Common/Tabung Common'), [('username') : username, ('password') : password
        , ('idNumber') : idNumber, ('PAN') : PAN, ('action') : action, ('subAction') : subAction
        , ('edit') : edit, ('tabungMap') : tabungMap, ('mobileNumberShareBill') : mobileNumberShareBill, ('isGroup') : isGroup, ('status') : status
		, ('statusTitle') : statusTitle, ('statusDescription') : [], ('verifyDashboard') : verifyDashboard
		, ('actionTabung') : actionTabung, ('tabungActionMap') : tabungActionMap
        , ('isMakeAction') : isMakeAction, ('shouldCreateTabung'): shouldCreateTabung,
		('shouldVerifyNotification'): shouldVerifyNotification], FailureHandling.STOP_ON_FAILURE)

