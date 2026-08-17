import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase

import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

WebUI.callTestCase(findTestCase('Website/M2U/Pay and Transfer/Transfer/Other Accounts/Common/3rd Party_Open'), [('username') : username
        , ('password') : password, ('navItem') : navItem, ('tabName') : tabName, ('fromToData') : fromToData, ('otherBank') : otherBank
        , ('inputTransferData') : inputTransferData, ('statusScreenData') : statusScreenData, ('addToFavouriteData') : addToFavouriteData
        , ('isFuture') : false, status:status, validateAddFavouriteTransfer:validateAddFavouriteTransfer
		, isRunWebsiteAndMobile:isRunWebsiteAndMobile, toastMessageAfterAdd: toastMessageAfterAdd
		, validateFavouriteTransfer: false], FailureHandling.STOP_ON_FAILURE)
