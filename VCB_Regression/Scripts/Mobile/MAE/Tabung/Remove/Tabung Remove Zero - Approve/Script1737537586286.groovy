import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.maybank.mae.internal.pages.base.BasePage
import com.maybank.mae.internal.pages.base.tabung.TabungPage
import com.maybank.utils.MapUtils

import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import com.kms.katalon.core.model.FailureHandling as FailureHandling

TabungPage tabungPage = BasePage.getPage(TabungPage)

Map tabungMap = MapUtils.parseObjectToMap(tabungMap)

WebUI.callTestCase(findTestCase('Mobile/MAE/Tabung/Common/Tabung Common'), [('username') : username, ('password') : password
        , ('idNumber') : idNumber, ('PAN') : PAN, ('action') : action, ('subAction') : subAction, ('edit') : edit, ('tabungMap') : tabungMap
        , ('mobileNumberShareBill') : mobileNumberShareBill, ('isGroup') : isGroup, ('status') : status, ('statusTitle') : statusTitle
        , ('statusDescription') : statusDescription, ('verifyDashboard') : verifyDashboard, ('actionTabung') : actionTabung
        , ('tabungActionMap') : tabungActionMap, ('isMakeAction') : isMakeAction, ('shouldCreateTabung') : shouldCreateTabung
        , ('shouldVerifyNotification') : shouldVerifyNotification, ('title') : title, ('message') : message], FailureHandling.STOP_ON_FAILURE)

tabungPage.verifyAndDeletePastTabung(tabungMap.get("tabungName"))