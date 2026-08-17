import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase

import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

WebUI.callTestCase(findTestCase('Website/M2U/Pay and Transfer/Transfer/Other Accounts/Common/3rd Party_Open'), [('username') : username
        , ('password') : password, ('navItem') : navItem, ('tabName') : tabName, ('fromToData') : fromToData
        , ('inputFavouriteTransferData') : inputFavouriteTransferData, ('statusScreenData') : recurringStatusScreenData
        , ('status') : status, ('validateAddFavouriteTransfer') : validateAddFavouriteTransfer, ('isRunWebsiteAndMobile') : isRunWebsiteAndMobile
        , ('isFuture') : isFuture, ('datePickerLabel') : datePickerLabel
        , ('validateFavouriteTransfer') : validateFavouriteTransfer, ('isRecurring') : isRecurring, recurringLabel: recurringLabel, favouriteName:favouriteName], FailureHandling.STOP_ON_FAILURE)

