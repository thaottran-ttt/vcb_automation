import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase

import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

WebUI.callTestCase(findTestCase('Mobile/MAE/Oversea Transfer/WU/2 - Fav/WU Transfer Fav - Status Approve'), [
        ('username')                  : username,
        ('password')                  : password,
        ('idNumber')                  : idNumber,
        ('PAN')                       : PAN,
        ('primaryAccount')            : primaryAccount,
        ('accountName')               : accountName,
        ('debitingAccountName')       : debitingAccountName,
        ('transferTab')               : transferTab,
        ('amount')                    : amount,
        ('currency')                  : currency,
        ('typeTransfer')              : typeTransfer,
        ('status')                    : status,
        ('statusTitle')               : statusTitle,
        ('statusDescription')         : statusDescription,
        ('notificationTitle')         : notificationTitle,
        ('notificationDescription')   : notificationDescription,
        ('citizenship')               : citizenship,
        ('promoCode')                 : promoCode,
        ('senderEmailAddress')        : senderEmailAddress,
        ('occupation')                : occupation,
        ('employmentPositionLevel')   : employmentPositionLevel,
        ('relationshipToBeneficiary') : relationshipToBeneficiary,
        ('sourceOfFunds')             : sourceOfFunds,
        ('firstName')                 : firstName,
        ('lastName')                  : lastName,
        ('receiverCountryCode')       : receiverCountryCode,
        ('recipientMobileNumber')     : recipientMobileNumber,
        ('streetLine1')               : streetLine1,
        ('streetLine2')               : streetLine2,
        ('purposeOfTransfer')         : purposeOfTransfer,
        ('subPurpose')                : subPurpose,
        ('verifySST')                 : verifySST
], FailureHandling.STOP_ON_FAILURE)
