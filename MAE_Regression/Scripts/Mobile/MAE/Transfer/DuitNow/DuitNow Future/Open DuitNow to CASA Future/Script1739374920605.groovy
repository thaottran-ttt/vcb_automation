import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

WebUI.callTestCase(findTestCase('Mobile/MAE/Transfer/DuitNow/Common/IBFT'), [('username') : username, ('password') : password
        , ('idNumber') : idNumber, ('PAN') : PAN, ('accountName') : accountName, ('moduleName') : moduleName, ('transferTabName') : transferTabName
        , ('newTransferName') : newTransferName, ('bankName') : bankName, ('transferInputData') : transferInputData, ('transferDropdownData') : transferDropdownData
        , ('recipientName') : recipientName, ('amount') : amount, ('confirmationData') : confirmationData, ('nextConfirmationPage') : nextConfirmationPage, ('messagePushNotification') : messagePushNotification
        , ('status') : status, ('statusTitle') : statusTitle, ('statusDescription') : statusDescription, isFuture: isFuture, 'reprintReceiptProduct': reprintReceiptProduct, 'shouldValidationAddFavorite' : false], FailureHandling.STOP_ON_FAILURE)

