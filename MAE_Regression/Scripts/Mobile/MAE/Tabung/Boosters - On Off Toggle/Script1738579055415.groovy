import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import com.maybank.mae.internal.pages.base.BasePage
import com.maybank.mae.internal.pages.base.CommonPage
import com.maybank.mae.internal.pages.base.tabung.TabungPage
import com.maybank.utils.MapUtils

import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import internal.GlobalVariable as GlobalVariable
Map tabungMap = MapUtils.parseObjectToMap(tabungMap)

CommonPage commonPage = BasePage.getPage(CommonPage)

TabungPage tabungPage = BasePage.getPage(TabungPage)

WebUI.callTestCase(findTestCase('Mobile/MAE/Tabung/Common/Tabung Common'), [('userName') : username, ('password') : password
        , ('action') : action, ('subAction') : subAction, ('idNumber') : idNumber, ('PAN') : PAN
        , ('tabungMap') : tabungMap, ('mobileNumberShareBill') : mobileNumberShareBill
        , ('isGroup') : isGroup, ('status') : status, ('statusTitle') : statusTitle, ('statusDescription') : [], ('verifyDashboard'): verifyDashboard], 
    FailureHandling.STOP_ON_FAILURE)
commonPage.waitLoading()

'Select Tab Boosters'
commonPage.selectTab(commonPage.frmHorizontalScrollNo(1), tabName, GlobalVariable.G_timeoutMedium)

'Spare Change'
tabungPage.handleToggleBoosters(tabungMap.get('categoryName'))