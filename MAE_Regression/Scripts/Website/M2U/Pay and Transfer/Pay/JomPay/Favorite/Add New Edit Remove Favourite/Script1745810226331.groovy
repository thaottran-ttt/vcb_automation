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
import com.maybank.utils.MapUtils as MapUtils
import com.maybank.utils.ListUtils
import com.maybank.m2u.internal.pages.base.WebBasePage as WebBasePage
import com.maybank.m2u.internal.pages.base.WebCommonPage as WCP
import com.maybank.m2u.internal.pages.base.WebHomePage
import com.maybank.common.WebUIKeyword
import com.maybank.m2u.internal.pages.base.ElementVerificationType
import com.maybank.m2u.internal.constants.WebCommonConstants as WCC
import com.maybank.m2u.internal.pages.base.paytransfer.WebPayPageJomPAY as WebPayPageJomPAY
import com.maybank.m2u.internal.pages.base.WebSecure2uAuthorisationPage as WebSecure2uAuthorisationPage
import com.maybank.m2u.internal.pages.base.WebStatusScreenPage as WebStatusScreenPage

Map inputAddNewFavourite = MapUtils.parseObjectToMap(inputFavouriteData)
Map payToData = MapUtils.parseObjectToMap(payToData)
Map editFavourite = MapUtils.parseObjectToMap(editFavourite)
List verifyStatusScreenData = ListUtils.parseObjectToList(verifyStatusScreenData)
String favouriteName = inputAddNewFavourite.get("Nickname")
String favouriteEditName = editFavourite.get("Nickname")

WCP webCommonPage = WebUI.callTestCase(findTestCase('Website/M2U/Pay and Transfer/Pay/JomPay/Navigate to Pay'), [('username') : username
        , ('password') : password, ('idNumber') : idNumber, ('PAN') : PAN, ('navItem') : navItem, ('tabName') : tabName, ('payToData') : payToData], 
    FailureHandling.STOP_ON_FAILURE)

WebHomePage webHomePage = WebBasePage.getPage(WebHomePage)

WebStatusScreenPage webStatusScreenPage = WebBasePage.getPage(WebStatusScreenPage)

WebPayPageJomPAY payPage = WebBasePage.getPage(WebPayPageJomPAY)

WebSecure2uAuthorisationPage webSecure2uAuthorisationPage = WebBasePage.getPage(WebSecure2uAuthorisationPage)

webSecure2uAuthorisationPage.setID_NUMBER(idNumber)

'Select Type JomPay'
webCommonPage.inputDropdownMap(payToData)

'Click Add New Favourite button'
payPage.clickAddNewFavouriteButton()

'Input Favourite fields'
payPage.addNewJompayFavourite(inputFavouriteData)

'S2u'
webSecure2uAuthorisationPage.authorize(status)
	
'Verify Status Screen'
webStatusScreenPage.verifyStatusScreen(verifyStatusScreenData)

webStatusScreenPage.clickDoneButton()

'Navigate To Transfer To Edit'
webHomePage.navigate(navItem, tabName)

Mobile.delay(GlobalVariable.G_timeoutMiniShort)

'Select Type Transfer'
webCommonPage.inputDropdownMap(payToData)

WebUIKeyword.delay(GlobalVariable.G_timeoutMiniShort)

'Show CTA'
payPage.clickShowFavouriteMenu(favouriteName)

'Click Edit'
payPage.selectMenuCTAName(ctaName)

WebUIKeyword.delay(GlobalVariable.G_timeoutMiniShort)

'Edit Favourite'
payPage.editFavourite(editFavourite)

WebUIKeyword.delay(GlobalVariable.G_timeoutShort)

'S2u'
webSecure2uAuthorisationPage.authorize(status)

'Verify Status Edit Screen'
webStatusScreenPage.verifyStatusScreen(verifyStatusScreenData)

'Navigate To Transfer To Remove'
webHomePage.navigate(navItem, tabName)

Mobile.delay(GlobalVariable.G_timeoutMiniShort)

'Select Type Transfer'
webCommonPage.inputDropdownMap(payToData)

WebUIKeyword.delay(GlobalVariable.G_timeoutMiniShort)

'Show CTA'
payPage.clickShowFavouriteMenu(favouriteEditName)

'Click Remove'
payPage.selectMenuCTAName(ctaRemoveName)

WebUIKeyword.delay(GlobalVariable.G_timeoutShort)

'Remove Favourite'
payPage.removeFavourite()

'Verify Status on Screen'
payPage.verifyStatusScreen(verifyStatusScreenData)
