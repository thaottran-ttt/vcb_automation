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
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys
import com.vcb.utils.MapUtils as MapUtils
import com.vcb.m2u.internal.pages.base.WebBasePage as WebBasePage
import com.vcb.m2u.internal.pages.base.WebCommonPage as WCP
import com.vcb.common.WebUIKeyword
import com.vcb.m2u.internal.constants.WebCommonConstants as WCC
import com.vcb.m2u.internal.pages.base.paytransfer.WebPayPageJomPAY as WebPayPageJomPAY
import com.vcb.m2u.internal.pages.base.WebSecure2uAuthorisationPage as WebSecure2uAuthorisationPage
import com.vcb.m2u.internal.pages.base.WebStatusScreenPage as WebStatusScreenPage

WCP webCommonPage = WebUI.callTestCase(findTestCase('Website/M2U/Pay and Transfer/Pay/JomPay/Navigate to Pay'), [('username') : username
        , ('password') : password, ('idNumber') : idNumber, ('PAN') : PAN, ('navItem') : navItem, ('tabName') : tabName, ('payToData') : payToData], 
    FailureHandling.STOP_ON_FAILURE)

WebStatusScreenPage webStatusScreenPage = WebBasePage.getPage(WebStatusScreenPage)

WebPayPageJomPAY payPage = WebBasePage.getPage(WebPayPageJomPAY)

WebSecure2uAuthorisationPage webSecure2uAuthorisationPage = WebBasePage.getPage(WebSecure2uAuthorisationPage)

webSecure2uAuthorisationPage.setID_NUMBER(idNumber)

'Select Type JomPay'
webCommonPage.inputDropdownMap(payToData)

'Add New Favourite'
WebUIKeyword.verifyElementVisible(payPage.btnAddNewFavourite)

assert WebUI.getCSSValue(payPage.btnAddNewFavourite, payPage.ADD_NEW_FAVOURITE_BTN_CSS_CHECK) == 
payPage.ADD_NEW_FAVOURITE_BTN_BACKGROUND_COLOR

WebUIKeyword.click(payPage.btnAddNewFavourite)

'Add New Favourite Modal'
WebUIKeyword.verifyElementVisible(payPage.lblFavouriteModalTitle)

WebUIKeyword.verifyElementVisible(payPage.btnFavouriteModalClose)

WebUIKeyword.verifyElementVisible(payPage.btnFavouriteModalAdd)

'Verify toast messages when empty fields and input values field by field'
Map inputFavouriteData = MapUtils.parseObjectToMap(inputFavouriteData)
inputFavouriteData.each{ def label, def value ->

	WebUIKeyword.setTextWithClear(WCP.txtForLabel(label), inputFavouriteData[label], false)
}
WebUIKeyword.click(payPage.btnFavouriteModalAdd)

'S2u'
webSecure2uAuthorisationPage.authorize(status)

'Verify Status Screen'
webStatusScreenPage.verifyStatusScreen(verifyStatusScreenData)