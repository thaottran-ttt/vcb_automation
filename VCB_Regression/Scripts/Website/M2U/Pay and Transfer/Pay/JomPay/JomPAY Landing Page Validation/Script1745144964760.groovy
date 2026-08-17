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
import com.vcb.common.WebUIKeyword as WebUIKeyword
import com.vcb.m2u.internal.constants.WebCommonConstants as WCC
import com.vcb.m2u.internal.pages.base.WebBasePage as WebBasePage
import com.vcb.m2u.internal.pages.base.WebCommonPage as WCP
import com.vcb.m2u.internal.pages.base.paytransfer.WebPayPageJomPAY as WebPayPageJomPAY

WCP webCommonPage = WebUI.callTestCase(findTestCase('Website/M2U/Pay and Transfer/Pay/JomPay/Navigate to Pay'), 
    [('username') : username, ('password') : password, ('idNumber') : idNumber, ('PAN') : PAN, ('navItem') : navItem, ('tabName') : tabName
        , ('payToData') : payToData], FailureHandling.STOP_ON_FAILURE)

WebPayPageJomPAY payPage = WebBasePage.getPage(WebPayPageJomPAY)

'Select Type JomPay'
webCommonPage.inputDropdownMap(payToData)

'Verify Biller input has correct placeholder'
def billerCodeInput = WCP.inputPlaceholder(payPage.BILLER_CODE_PLACEHOLDER)

WebUI.verifyElementVisible(billerCodeInput)

'Verify JomPAY icon'
WebUI.verifyElementVisible(payPage.imgJomPAYIcon)

'Verify JomPAY input icon'
WebUI.verifyElementVisible(payPage.imgJomPAYIconInput)

'Verify can only input numbers for billerCode'
WebUI.setText(billerCodeInput, 'a!.')

assert WebUI.getAttribute(billerCodeInput, 'value') == ''

'Verify Press Enter label appears only after input'
WebUI.verifyElementNotPresent(payPage.lblBillerCodeInputToggle, GlobalVariable.G_timeoutMiniShort)

WebUI.setText(billerCodeInput, '1')

WebUI.verifyElementVisible(payPage.lblBillerCodeInputToggle)

WebUIKeyword.setTextWithClear(billerCodeInput, '', false)

WebUI.verifyElementNotPresent(payPage.lblBillerCodeInputToggle, GlobalVariable.G_timeoutMiniShort)

'Verify toast message upon invalid billerCode'
WebUI.sendKeys(billerCodeInput, Keys.chord(Keys.ENTER))

WebUI.callTestCase(findTestCase('Website/M2U/Pay and Transfer/Pay/JomPay/Verify Toast Message'),
	[('titleString') : WCC.TOAST_MESSAGE_TITLE_BLANK_FIELD, ('messageString') : payPage.TOAST_MESSAGE_MESSAGE_EMPTY_BILLERCODE], 
    FailureHandling.STOP_ON_FAILURE)

'Add New Favourite'
WebUI.verifyElementVisible(payPage.btnAddNewFavourite)

assert WebUI.getCSSValue(payPage.btnAddNewFavourite, payPage.ADD_NEW_FAVOURITE_BTN_CSS_CHECK) == payPage.ADD_NEW_FAVOURITE_BTN_BACKGROUND_COLOR
