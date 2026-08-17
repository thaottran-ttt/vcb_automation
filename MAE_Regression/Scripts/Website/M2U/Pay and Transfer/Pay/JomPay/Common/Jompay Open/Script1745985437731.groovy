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

import com.maybank.common.WebUIKeyword
import com.maybank.m2u.internal.pages.base.WebBasePage
import com.maybank.m2u.internal.pages.base.WebCommonPage
import com.maybank.m2u.internal.pages.base.WebHomePage
import com.maybank.m2u.internal.pages.base.WebCommonPage as WCP
import com.maybank.m2u.internal.pages.base.WebSecure2uAuthorisationPage
import com.maybank.m2u.internal.pages.base.WebStatusScreenPage
import com.maybank.m2u.internal.pages.base.paytransfer.WebPayPageJomPAY
import com.maybank.utils.MapUtils
import com.maybank.utils.StringUtils
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale
WebHomePage webHomePage = WebBasePage.getPage(WebHomePage)

WCP webCommonPage = WebUI.callTestCase(findTestCase('Website/M2U/Pay and Transfer/Pay/JomPay/Navigate to Pay'), [('username') : username, ('password') : password
        , ('idNumber') : idNumber, ('PAN') : PAN, ('navItem') : navItem, ('tabName') : tabName
        , ('payToData') : payToData], FailureHandling.STOP_ON_FAILURE)

WebPayPageJomPAY payPage = WebBasePage.getPage(WebPayPageJomPAY)
WebSecure2uAuthorisationPage webSecure2uAuthorisationPage = WebBasePage.getPage(WebSecure2uAuthorisationPage)
WebStatusScreenPage webStatusScreenPage = WebBasePage.getPage(WebStatusScreenPage)
webSecure2uAuthorisationPage.setID_NUMBER(idNumber)
Map inputPayToData = MapUtils.parseObjectToMap(inputPayToData)
String referenceNumber = StringUtils.createStrRandom(length)
inputPayToData.put('Ref-2', referenceNumber)

'Get balance'
Double originBalance = webHomePage.getBalance()

Mobile.delay(GlobalVariable.G_timeoutMiniShort)

'Select Type JomPay'
webCommonPage.inputDropdownMap(payToData)

if(isFav) {
	inputPayToData.remove("Ref-1")
	
	inputPayToData.remove("Ref-2")
	
	'Click Terms radio button'
	WebUIKeyword.click(webCommonPage.lblDynamic(favName))
	
	webCommonPage.waitLoading()
}
else {
	'Enter valid biller code'
	payPage.enterBillerCode(billerCode)
}

'Click Terms radio button'
WebUIKeyword.click(payPage.imgPayToModalTermEmpty)

'Enter valid fields'
payPage.inputPopupPayTo(inputPayToData, recurring, futureDate)

'S2u'
webSecure2uAuthorisationPage.authorize(status)

'Verify Status Screen'
webStatusScreenPage.verifyStatusScreen(verifyStatusScreenData)

if(status =='Approve' && recurring == false) {
'Verify balance'
Double newBalance = webHomePage.getTransSummary()

if(verifyBal) {
webStatusScreenPage.verifyTransactionSuccess(originBalance, newBalance)
}

//webStatusScreenPage.verifyTxnHistory(type)
}