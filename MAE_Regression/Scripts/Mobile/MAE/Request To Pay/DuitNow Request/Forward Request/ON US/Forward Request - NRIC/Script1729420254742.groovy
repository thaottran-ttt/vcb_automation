import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase

import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.maybank.common.MobileDriver
import com.maybank.mae.internal.pages.base.BasePage
import com.maybank.mae.internal.pages.base.CommonPage
import com.maybank.mae.internal.pages.base.sendandrequestpage.DuitNowRequestPage
import com.maybank.mae.internal.pages.base.HomePage

import internal.GlobalVariable
import io.appium.java_client.android.AndroidDriver

import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile

WebUI.callTestCase(findTestCase('Mobile/MAE/Request To Pay/DuitNow Request/Common/DuitNow Forward Request'), [('idNumber') : idNumber
        , ('PAN') : PAN, ('password') : password, ('username') : username, ('accountName') : accountName, ('functionName') : functionName
        , ('status') : status, ('duitnowType') : duitnowType, ('itemNameInThreeDot') : itemNameInThreeDot, ('requestType') : requestType
        , ('inputTextValue') : inputTextValue, ('dropdownValue') : dropdownValue, ('statusScreenData') : statusScreenData
        , ('pushNotificationTitle') : pushNotificationTitle, ('pushNotificationDes') : pushNotificationDes, ('inputNotifyViaEmail') : inputNotifyViaEmail], 
    FailureHandling.STOP_ON_FAILURE)
