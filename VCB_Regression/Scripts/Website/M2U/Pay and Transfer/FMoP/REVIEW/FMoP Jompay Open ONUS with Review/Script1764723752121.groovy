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
import com.vcb.m2u.internal.pages.base.rsapage.WebRsaPage as WebRsaPage

import com.vcb.common.WebUIKeyword
import com.vcb.m2u.internal.pages.base.WebBasePage
import com.vcb.m2u.internal.pages.base.WebCommonPage
import com.vcb.m2u.internal.pages.base.WebHomePage
import com.vcb.m2u.internal.pages.base.WebCommonPage as WCP
import com.vcb.m2u.internal.pages.base.WebSecure2uAuthorisationPage
import com.vcb.mobile.internal.pages.base.Secure2uAuthorisationPage
import com.vcb.mobile.internal.pages.base.Secure2uAuthorisationPage.AuthorisationType
import com.vcb.m2u.internal.pages.base.WebStatusScreenPage
import com.vcb.m2u.internal.pages.base.paytransfer.WebPayPageJomPAY
import com.vcb.utils.MapUtils
import com.vcb.utils.StringUtils
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

WebCommonPage webCommonPage = WebBasePage.getPage(WebCommonPage)
WebHomePage webHomePage = WebBasePage.getPage(WebHomePage)
WebSecure2uAuthorisationPage webSecure2uAuthorisationPage = WebBasePage.getPage(WebSecure2uAuthorisationPage)
WebRsaPage webRsaPage = WebBasePage.getPage(WebRsaPage)
WebPayPageJomPAY payPage = WebBasePage.getPage(WebPayPageJomPAY)
WebStatusScreenPage webStatusScreenPage = WebBasePage.getPage(WebStatusScreenPage)

WebUI.callTestCase(findTestCase('Mobile/VCB/Common/Login Secure2u With Secure Question'), [('idNumber') : idNumber, ('PAN') : PAN
        , ('password') : GlobalVariable.G_password, ('username') : username, ('accountName') : '', ('flowControl') : com.vcb.common.FlowControlEnum.STOP
        , ('failStopTestSuite') : false, ('yourName') : GlobalVariable.G_yourName], BaseKeyword.defaultFailureHandling)

WebUI.callTestCase(findTestCase('Website/M2U/Common/Login'), [('username') : username, ('password') : password], BaseKeyword.defaultFailureHandling)

'Navigate To Transfer'
webHomePage.navigate(navItem, tabName)

webCommonPage.waitLoading()
Mobile.delay(GlobalVariable.G_timeoutMiniShort)
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

WebUIKeyword.delay(GlobalVariable.G_timeoutShort)

'Verify Status Screen'
webStatusScreenPage.verifyStatusScreen(verifyStatusScreenData)
