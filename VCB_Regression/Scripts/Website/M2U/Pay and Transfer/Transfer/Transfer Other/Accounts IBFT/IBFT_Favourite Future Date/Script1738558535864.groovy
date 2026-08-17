import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase

import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

WebUI.callTestCase(findTestCase('Website/M2U/Pay and Transfer/Transfer/Transfer Other/Common/Transfer Other'), [('username') : username
        , ('password') : password, ('navItem') : navItem, ('tabName') : tabName, ('fromToData') : fromToData
        , ('inputTransferData') : inputFavouriteTransferData, inputDropdownTransferData: inputDropdownTransferData, ('statusScreenData') : futureStatusScreenData
        , ('status') : status, ('validateAddFavouriteTransfer') : validateAddFavouriteTransfer, ('isRunWebsiteAndMobile') : isRunWebsiteAndMobile
        , ('isFuture') : isFuture, ('datePickerLabel') : datePickerLabel
        , ('validateFavouriteTransfer') : validateFavouriteTransfer, ('isRecurring') : isRecurring, favouriteName:favouriteName], FailureHandling.STOP_ON_FAILURE)

