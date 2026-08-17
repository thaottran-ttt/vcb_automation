import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase

import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

WebUI.callTestCase(findTestCase('Website/M2U/Pay and Transfer/Transfer/Transfer Other/Common/Transfer Other_Add New Favorite'), 
    [('username') : username, ('password') : password, ('idNumber') : idNumber, ('PAN') : PAN, ('navItem') : navItem, ('tabName') : tabName
        , ('fromToData') : fromToData, ('drpAddNewFavourite') : drpAddNewFavourite, ('inputAddNewFavourite') : inputAddNewFavourite
        , ('status') : status, ('verifyStatusScreenData') : verifyStatusScreenData, ('isRunWebsiteAndMobile') : isRunWebsiteAndMobile], 
    FailureHandling.STOP_ON_FAILURE)

