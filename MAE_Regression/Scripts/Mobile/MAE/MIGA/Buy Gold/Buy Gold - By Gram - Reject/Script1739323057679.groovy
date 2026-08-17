import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import com.kms.katalon.core.model.FailureHandling as FailureHandling

WebUI.callTestCase(findTestCase('Mobile/MAE/MIGA/Buy Gold/Buy Gold - By RM - Aprrove'), [('idNumber') : idNumber, ('PAN') : PAN, ('username') : username, ('password') : password
        , ('accountName') : '', ('transactionMap') :transactionMap, ('status') : status, ('statusTitle') : statusTitle
		, ('statusDescription') :statusDescription, ('isTransferByGold') : isTransferByGold], 
    FailureHandling.STOP_ON_FAILURE)

