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
import com.vcb.m2u.internal.pages.base.WebHomePage
import com.vcb.common.WebUIKeyword
import com.vcb.m2u.internal.constants.WebCommonConstants as WCC
import com.vcb.m2u.internal.pages.base.paytransfer.WebPayPageJomPAY as WebPayPageJomPAY
import com.vcb.m2u.internal.pages.base.WebSecure2uAuthorisationPage as WebSecure2uAuthorisationPage
import com.vcb.m2u.internal.pages.base.WebStatusScreenPage as WebStatusScreenPage

WCP webCommonPage = WebUI.callTestCase(findTestCase('Website/M2U/Pay and Transfer/Pay/JomPay/Navigate to Pay'), [('username') : username
        , ('password') : password, ('idNumber') : idNumber, ('PAN') : PAN, ('navItem') : navItem, ('tabName') : tabName, ('payToData') : payToData], 
    FailureHandling.STOP_ON_FAILURE)

WebHomePage webHomePage = WebBasePage.getPage(WebHomePage)

WebStatusScreenPage webStatusScreenPage = WebBasePage.getPage(WebStatusScreenPage)

WebPayPageJomPAY payPage = WebBasePage.getPage(WebPayPageJomPAY)

WebSecure2uAuthorisationPage webSecure2uAuthorisationPage = WebBasePage.getPage(WebSecure2uAuthorisationPage)

webSecure2uAuthorisationPage.setID_NUMBER(idNumber)

'Get balance'
Double originBalance = webHomePage.getBalance()
Mobile.delay(GlobalVariable.G_timeoutMiniShort)

'Select Type JomPay'
webCommonPage.inputDropdownMap(payToData)

favouriteName.each { 
	payPage.selectFavouriteName(it)
	payPage.inputPopupTransfer(inputFavouriteTransferData, inputDropdownTransferData)
	WebUI.click(payPage.imgPayToModalTermEmpty)
	payPage.clickAddEditAnotherTransferButton()
}

payPage.clickPayButton()

// This step no need to secure2u authorisation 
'Click Confirm Button'
webStatusScreenPage.clickConfirmButton()

'Verify Status Screen'
webStatusScreenPage.verifyStatusScreen(verifyStatusScreenData)

'Verify balance'
Double newBalance = webHomePage.getTransSummary()

webStatusScreenPage.verifyTransactionSuccess(originBalance, newBalance)

webStatusScreenPage.verifyTxnHistory(type)
