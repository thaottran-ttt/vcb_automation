import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.vcb.mobile.internal.pages.base.BasePage
import com.vcb.mobile.internal.pages.base.tabung.TabungPage
import com.vcb.utils.MapUtils

import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import com.kms.katalon.core.model.FailureHandling as FailureHandling

TabungPage tabungPage = BasePage.getPage(TabungPage)

Map tabungMap = MapUtils.parseObjectToMap(tabungMap)

WebUI.callTestCase(findTestCase('Mobile/VCB/Tabung/Common/Tabung Common'), [('username') : username, ('password') : password
        , ('idNumber') : idNumber, ('PAN') : PAN, ('action') : action, ('subAction') : subAction, ('edit') : edit, ('tabungMap') : tabungMap
        , ('mobileNumberShareBill') : mobileNumberShareBill, ('isGroup') : isGroup, ('status') : status, ('statusTitle') : statusTitle
        , ('statusDescription') : statusDescription, ('verifyDashboard') : verifyDashboard, ('actionTabung') : actionTabung
        , ('tabungActionMap') : tabungActionMap, ('isMakeAction') : isMakeAction, ('shouldCreateTabung') : shouldCreateTabung
        , ('shouldVerifyNotification') : shouldVerifyNotification, ('title') : title, ('message') : message], FailureHandling.STOP_ON_FAILURE)

tabungPage.verifyAndDeletePastTabung(tabungMap.get("tabungName"))