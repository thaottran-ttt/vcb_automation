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
import com.maybank.common.BaseKeyword as BaseKeyword
import com.maybank.common.WebUIKeyword as WebUIKeyword
import com.maybank.m2u.internal.pages.base.WebBasePage as WebBasePage
import com.maybank.m2u.internal.pages.base.WebCommonPage as WebCommonPage
import com.maybank.m2u.internal.pages.base.WebHomePage
import com.maybank.m2u.internal.pages.base.WebSecure2uAuthorisationPage
import com.maybank.m2u.internal.pages.base.WebStatusScreenPage
import com.maybank.m2u.internal.pages.base.paytransfer.WebReloadPage
import com.maybank.mae.internal.pages.base.Secure2uAuthorisationPage
import com.maybank.utils.ListUtils
import com.maybank.utils.MapUtils

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
WebReloadPage webReloadPage = WebBasePage.getPage(WebReloadPage)
WebStatusScreenPage webStatusScreenPage = WebBasePage.getPage(WebStatusScreenPage)
WebSecure2uAuthorisationPage webSecure2uAuthorisationPage = WebBasePage.getPage(WebSecure2uAuthorisationPage)
webSecure2uAuthorisationPage.setID_NUMBER(idNumber)

WebUI.callTestCase(findTestCase('Mobile/MAE/Common/Login Secure2u With Secure Question'), [('idNumber') : idNumber, ('PAN') : PAN
        , ('password') : GlobalVariable.G_password, ('username') : username, ('accountName') : '', ('flowControl') : com.maybank.common.FlowControlEnum.STOP
        , ('failStopTestSuite') : false, ('yourName') : GlobalVariable.G_yourName], BaseKeyword.defaultFailureHandling)

WebUI.callTestCase(findTestCase('Website/M2U/Common/Login'), [('username') : username, ('password') : password], BaseKeyword.defaultFailureHandling)

'Navigate To Transfer'
webHomePage.navigate(navItem, tabName)

Mobile.delay(GlobalVariable.G_timeoutMiniShort)

'Get balance'
Double originBalance = webHomePage.getBalance()

Mobile.delay(GlobalVariable.G_timeoutMiniShort)

'Select Type Transfer'
webCommonPage.inputDropdownMap(fromToData)

WebUIKeyword.delay(GlobalVariable.G_timeoutShort)

if (validateFavouriteTransfer) {
	'Select Favourite Name'
    webReloadPage.selectFavouriteName(favouriteName)
	'Input data'
    webReloadPage.inputPopupTransfer(inputFavouriteTransferData, inputDropdownTransferData)
} else {
	
	if(isInternational) {
		webReloadPage.selectCountry(country, otherBank)
		
		webReloadPage.inputPopupInternationalTransfer(inputTransferData)
	}else {
		
		'select Bank name'
		webReloadPage.selectOtherBank(otherBank)
		WebUIKeyword.delay(GlobalVariable.G_timeoutShort)
		'Input Transfer Data'
		webReloadPage.inputPopupTransfer(inputTransferData, inputDropdownTransferData)
	}
	
}

'Tap Transfer'
webReloadPage.clickReloadButton()

WebUIKeyword.delay(GlobalVariable.G_timeoutShort)

'S2u'
webSecure2uAuthorisationPage.authorize(status)

WebUIKeyword.delay(GlobalVariable.G_timeoutShort)

'Verify Status Screen'
webStatusScreenPage.verifyStatusScreen(statusScreenData)
