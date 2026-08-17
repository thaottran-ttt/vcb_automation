import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.ctc.wstx.api.InvalidCharHandler.FailingHandler as FailingHandler
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
import com.vcb.common.MobileDriver as MobileDriver
import com.vcb.mobile.internal.pages.base.BasePage as BasePage
import com.vcb.mobile.internal.pages.base.CommonPage as CommonPage
import com.vcb.mobile.internal.pages.base.HomePage as HomePage
import com.vcb.mobile.internal.pages.base.sendandrequestpage.SendAndRequestPage as SendAndRequestPage
import com.vcb.mobile.internal.pages.base.sendandrequestpage.DuitNowRequestPage as DuitNowRequestPage

HomePage homePage = BasePage.getPage(HomePage)

CommonPage commonPage = BasePage.getPage(CommonPage)

SendAndRequestPage sendAndRequestPage = BasePage.getPage(SendAndRequestPage)

DuitNowRequestPage duitNowRequestPage = BasePage.getPage(DuitNowRequestPage)

'Check is on Send & Request screen'
if (!(MobileDriver.waitForElementPresent(sendAndRequestPage.btnDuitNowRequest(), GlobalVariable.G_timeoutMedium))) {
    if (!(homePage.checkIsAccountLogin())) {
        WebUI.callTestCase(findTestCase('Mobile/VCB/Common/Login Secure2u'), [('idNumber') : idNumber, ('PAN') : PAN, ('password') : password
                , ('username') : username, ('accountName') : accountName], FailureHandling.STOP_ON_FAILURE)
    }
    
    'Select Send & Request'
    homePage.selectQuickActions('Send & Request')

    'Check enter PIN'
    commonPage.checkEnterPIN()
}

'Wait For PENDING tab present'
Mobile.waitForElementPresent(commonPage.lblDynamic('PENDING'), timeout)

'Select PENDING tab'
MobileDriver.tap(commonPage.lblDynamic('PENDING'), timeout)

if (MobileDriver.waitForElementPresent(duitNowRequestPage.btnViewAllDuitNowRequest(), GlobalVariable.G_timeoutMedium)) {
    'Slect View All DuitNow Request'
    duitNowRequestPage.tapViewAllDuitNowRequest()

    'Scroll to Outgoing item'
    Mobile.scrollToText('Outgoing', FailureHandling.OPTIONAL)

	'Verify Outgoing item'
    if (MobileDriver.waitForElementPresent(commonPage.lblAnyText('Outgoing'), GlobalVariable.G_timeoutMedium)) {
        'Select Outgoing'
        MobileDriver.tap(commonPage.lblAnyText('Outgoing'), timeout)

        'Verify item detail'
        duitNowRequestPage.verifyDuitNowRequestItemDetail()

        commonPage.tapBack()
    }
	
	'Scroll to Incoming item'
	Mobile.scrollToText('Incoming', FailureHandling.OPTIONAL)

	'Verify Incoming item'
	if (MobileDriver.waitForElementPresent(commonPage.lblAnyText('Incoming'), GlobalVariable.G_timeoutMedium)) {
		'Select Incoming'
		MobileDriver.tap(commonPage.lblAnyText('Incoming'), timeout)

		'Verify item detail'
		duitNowRequestPage.verifyDuitNowRequestItemDetail()

		commonPage.tapBack()
	}
}

'Wait For PAST tab present'
Mobile.waitForElementPresent(commonPage.lblDynamic('PAST'), timeout)

'Select tab PAST'
MobileDriver.tap(commonPage.lblDynamic('PAST'), timeout)

if (MobileDriver.waitForElementPresent(duitNowRequestPage.btnViewAllDuitNowRequest(), GlobalVariable.G_timeoutMedium)) {
    'Slect View All DuitNow Request'
    duitNowRequestPage.tapViewAllDuitNowRequest()

    'Scroll to Expired item'
    Mobile.scrollToText('Expired', FailureHandling.OPTIONAL)

	'Verify Expired item'
    if (MobileDriver.waitForElementPresent(commonPage.lblAnyText('Expired'), GlobalVariable.G_timeoutMedium)) {
        'Select Expired'
        MobileDriver.tap(commonPage.lblAnyText('Expired'), timeout)

        'Verify item detail'
        duitNowRequestPage.verifyDuitNowRequestItemDetail()

        commonPage.tapBack()
    }
}