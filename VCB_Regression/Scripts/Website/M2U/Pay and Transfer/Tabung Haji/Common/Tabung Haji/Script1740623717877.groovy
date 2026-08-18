import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import com.vcb.common.BaseKeyword as BaseKeyword
import com.vcb.common.WebUIKeyword as WebUIKeyword
import com.vcb.m2u.internal.pages.base.WebBasePage as WebBasePage
import com.vcb.m2u.internal.pages.base.WebCommonPage as WebCommonPage
import com.vcb.m2u.internal.pages.base.WebHomePage
import com.vcb.m2u.internal.pages.base.WebSecure2uAuthorisationPage
import com.vcb.m2u.internal.pages.base.WebStatusScreenPage
import com.vcb.m2u.internal.pages.base.paytransfer.WebReloadPage
import com.vcb.m2u.internal.pages.base.paytransfer.WebTabungHajiPage
import com.vcb.mobile.internal.pages.base.Secure2uAuthorisationPage
import com.vcb.utils.ListUtils
import com.vcb.utils.MapUtils

import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

Map fromToData = MapUtils.parseObjectToMap(fromToData)
Map inputTransferData = MapUtils.parseObjectToMap(inputTransferData)
Map inputDropdownTransferData = MapUtils.parseObjectToMap(inputDropdownTransferData)
Map addToFavouriteData = MapUtils.parseObjectToMap(addToFavouriteData)
Map inputFavouriteTransferData = MapUtils.parseObjectToMap(inputFavouriteTransferData)
List toastMessageAfterAdd = ListUtils.parseObjectToList(toastMessageAfterAdd)
List statusScreenData = ListUtils.parseObjectToList(statusScreenData)

WebCommonPage webCommonPage = WebBasePage.getPage(WebCommonPage)
WebHomePage webHomePage = WebBasePage.getPage(WebHomePage)
WebTabungHajiPage webTabungHajiPage = WebBasePage.getPage(WebTabungHajiPage)
WebStatusScreenPage webStatusScreenPage = WebBasePage.getPage(WebStatusScreenPage)
WebSecure2uAuthorisationPage webSecure2uAuthorisationPage = WebBasePage.getPage(WebSecure2uAuthorisationPage)
webSecure2uAuthorisationPage.setID_NUMBER(idNumber)

WebUI.callTestCase(findTestCase('Mobile/VCB/Common/Login Secure2u Without Check Logout'), [('username') : username, ('password') : password
        , ('idNumber') : idNumber, ('PAN') : PAN], BaseKeyword.defaultFailureHandling)

WebUI.callTestCase(findTestCase('null'), [('username') : username, ('password') : password], BaseKeyword.defaultFailureHandling)

'Navigate To Transfer'
webHomePage.navigate(navItem, tabName)

Mobile.delay(GlobalVariable.G_timeoutMiniShort)

'Select Type Transfer'
webCommonPage.inputDropdownMap(fromToData)

WebUIKeyword.delay(GlobalVariable.G_timeoutShort)

'Get balance'
Double originBalance = webHomePage.getBalance()

Mobile.delay(GlobalVariable.G_timeoutMiniShort)

if (validateFavouriteTransfer) {
	'Select Favourite Name'
    webTabungHajiPage.selectFavouriteName(favouriteName)
	WebUIKeyword.takeScreenshot()
	'Input data'
    webTabungHajiPage.inputPopupTransfer(inputFavouriteTransferData, inputDropdownTransferData)
} else {
	'select Bank name'
	webTabungHajiPage.selectNewTransfer()
	WebUIKeyword.delay(GlobalVariable.G_timeoutShort)
    'Input Transfer Data'
    webTabungHajiPage.inputPopupTransfer(inputTransferData, inputDropdownTransferData)
}

'Tap Transfer'
webCommonPage.clickTransferButton()

webCommonPage.waitLoading()
WebUIKeyword.delay(GlobalVariable.G_timeoutShort)

webSecure2uAuthorisationPage.authorize(status)

WebUIKeyword.delay(GlobalVariable.G_timeoutShort)

'Verify Status Screen'
webStatusScreenPage.verifyStatusScreen(statusScreenData)

'Verify Status Screen'
webStatusScreenPage.verifyStatusScreen(statusScreenData)

//Need to rerun this test case and verify these methods below
'Verify balance'
Double newBalance = webHomePage.getTransSummary()

webStatusScreenPage.verifyTransactionSuccess(originBalance, newBalance)

'Add Favourite'
if(validateAddFavouriteTransfer) {
	webStatusScreenPage.addToFavourite(addToFavouriteData, toastMessageAfterAdd, status)
}
	
'Verify transaction History'
webStatusScreenPage.verifyTxnHistory(type, accountType, text)