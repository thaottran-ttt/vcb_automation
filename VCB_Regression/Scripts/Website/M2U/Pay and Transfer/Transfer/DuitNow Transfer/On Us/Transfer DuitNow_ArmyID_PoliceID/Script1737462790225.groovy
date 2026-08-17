import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase

import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.vcb.common.BaseKeyword as BaseKeyword

WebUI.callTestCase(findTestCase('Website/M2U/Pay and Transfer/Transfer/DuitNow Transfer/Common/Transfer DuitNow'), [('username') : username
        , ('password') : password, ('idNumber') : idNumber, ('PAN') : PAN, ('navItem') : navItem, ('tabName') : tabName, ('fromToData') : fromToData
        , ('transferType') : transferType, ('dropdownTransferData') : dropdownTransferData, ('inputTransferData') : inputTransferData
        , ('statusScreenData') : statusScreenData, ('addToFavouriteData') : addToFavouriteData, ('isFuture') : isFuture, ('datePickerLabel') : datePickerLabel
        , ('isRecurring') : isRecurring, ('recurringLabel') : recurringLabel, ('validateFavouriteTransfer') : validateFavouriteTransfer
        , ('favouriteName') : favouriteName, ('dropdownFavouriteTransferData') : dropdownFavouriteTransferData, ('inputFavouriteTransferData') : inputFavouriteTransferData
        , ('status') : 'Approve', ('validateAddFavouriteTransfer') : validateAddFavouriteTransfer, ('toastMessageAfterAdd') : toastMessageAfterAdd
        , ('isRunWebsiteAndMobile') : true], BaseKeyword.defaultFailureHandling)

