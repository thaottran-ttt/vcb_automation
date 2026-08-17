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
import com.maybank.common.WebUIKeyword as WebUIKeyword
import com.maybank.m2u.internal.constants.WebCommonConstants as WCC
import com.maybank.m2u.internal.pages.base.WebBasePage as WebBasePage
import com.maybank.m2u.internal.pages.base.WebCommonPage as WCP
import com.maybank.m2u.internal.pages.base.paytransfer.WebPayPageJomPAY as WebPayPageJomPAY

WCP webCommonPage = WebUI.callTestCase(findTestCase('Website/M2U/Pay and Transfer/Pay/JomPay/Navigate to Pay'), 
    [('username') : username, ('password') : password, ('idNumber') : idNumber, ('PAN') : PAN, ('navItem') : navItem, ('tabName') : tabName
        , ('payToData') : payToData], FailureHandling.STOP_ON_FAILURE)

WebPayPageJomPAY payPage = WebBasePage.getPage(WebPayPageJomPAY)

Map inputPayToData = MapUtils.parseObjectToMap(inputPayToData)

'Select Type JomPay'
webCommonPage.inputDropdownMap(payToData)

'Enter valid biller code'
payPage.enterBillerCode(billerCode)

'Verify Pay To Popup'
'Verify Toast Message for each field when empty'
inputPayToData.each({ def label, def value ->
        WebUI.click(payPage.btnPayToModalPay)

        WebUI.callTestCase(findTestCase('Website/M2U/Pay and Transfer/Pay/JomPay/Verify Toast Message'),
			[('titleString') : WCC.TOAST_MESSAGE_TITLE_BLANK_FIELD, ('messageString') : payPage.parseEmtpyFieldToastMessage(label)], 
			FailureHandling.STOP_ON_FAILURE)
		
		'Input current field'
        payPage.enterRowPayToData(label, value)
    })

'Verify Terms radio button'
WebUI.click(payPage.btnPayToModalPay)

WebUI.callTestCase(findTestCase('Website/M2U/Pay and Transfer/Pay/JomPay/Verify Toast Message'),
	[('titleString') : WCC.TOAST_MESSAGE_TITLE_AGREE_TERMS, ('messageString') : ''],
	FailureHandling.STOP_ON_FAILURE)

WebUI.verifyElementVisible(payPage.imgPayToModalTermEmpty)
WebUI.click(payPage.imgPayToModalTermEmpty)
WebUI.verifyElementNotPresent((payPage.imgPayToModalTermEmpty), GlobalVariable.G_timeoutMiniShort)
WebUI.verifyElementVisible(payPage.imgPayToModalTermSelected)

'Separate validation is required for Ref-2 for current flow'
WebUI.click(payPage.btnPayToModalPay)
WebUI.callTestCase(findTestCase('Website/M2U/Pay and Transfer/Pay/JomPay/Verify Toast Message'),
	[('titleString') : payPage.TOAST_MESSAGE_REF2_REQUIRED, ('messageString') : ''],
	FailureHandling.STOP_ON_FAILURE)
