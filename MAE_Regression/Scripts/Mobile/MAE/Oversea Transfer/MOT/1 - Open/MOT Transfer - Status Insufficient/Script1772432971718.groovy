import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase

import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

WebUI.callTestCase(findTestCase('Mobile/MAE/Oversea Transfer/MOT/1 - Open/MOT Transfer - Status Approve'), [
        ('username')                 : username,
        ('password')                 : password,
        ('idNumber')                 : idNumber,
        ('PAN')                      : PAN,
        ('accountName')              : accountName,
        ('amount')                   : amount,
        ('status')                   : status,
        ('transferTab')              : transferTab,
        ('tabNewTransfer')           : tabNewTransfer,
        ('countryName')              : countryName,
        ('debitingAccountName')      : debitingAccountName,
        ('currency')                 : currency,
        ('typeTransfer')             : typeTransfer,
        ('currencyFrom')             : currencyFrom,
        ('receivingBank')            : receivingBank,
        ('receivingBank2')           : receivingBank2,
        ('beneficiaryAccountNumber') : beneficiaryAccountNumber,
        ('recipientNationality')     : recipientNationality,
        ('beneficiaryName')          : beneficiaryName,
        ('passportNumber')           : passportNumber,
        ('addressLine1')             : addressLine1,
        ('addressLine2')             : addressLine2,
        ('postCode')                 : postCode,
        ('country')                  : country,
        ('mobileNumber')             : mobileNumber,
        ('emailAddress')             : emailAddress,
        ('purposeOfTransfer')        : purposeOfTransfer,
        ('subPurpose')               : subPurpose,
        ('relationship')             : relationship,
        ('additionalDetails')        : additionalDetails,
        ('statusTitle')              : statusTitle,
        ('statusDescription')        : statusDescription,
        ('notificationTitle')        : notificationTitle,
        ('notificationDescription')  : notificationDescription,
        ('verifyList')               : verifyList,
        ('verifySST')                : verifySST
], FailureHandling.STOP_ON_FAILURE)
