import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase

import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.maybank.common.BaseKeyword as BaseKeyword

WebUI.callTestCase(findTestCase('Website/M2U/Pay and Transfer/Transfer/Own Accounts/Common/Transfer Own Account'), [('username') : username
        , ('password') : password, ('idNumber') : idNumber, ('PAN') : PAN, ('navItem') : navItem, ('tabName') : tabName, ('transferTo') : transferTo
        , ('inputPopupTransferData') : inputPopupTransferData, ('status') : 'Approve', ('statusScreenData') : statusScreenData
        , ('isFuture') : isFuture, ('isRecurring') : isRecurring, ('datePickerLabel') : datePickerLabel, ('recurringLabel') : recurringLabel], 
    BaseKeyword.defaultFailureHandling)

