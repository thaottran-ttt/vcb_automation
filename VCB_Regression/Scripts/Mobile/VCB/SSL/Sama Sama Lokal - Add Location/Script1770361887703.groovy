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

import com.vcb.mobile.internal.pages.base.BasePage
import com.vcb.mobile.internal.pages.base.CommonPage
import com.vcb.mobile.internal.pages.base.ConfirmationPage
import com.vcb.mobile.internal.pages.base.HomePage
import com.vcb.mobile.internal.pages.base.SslPage

import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

HomePage homePage = BasePage.getPage(HomePage)
SslPage sslPage = BasePage.getPage(SslPage)
CommonPage commonPage = BasePage.getPage(CommonPage)

FailureHandling flowControl = FailureHandling.STOP_ON_FAILURE
int timeout = GlobalVariable.G_timeoutMedium

WebUI.callTestCase(findTestCase('Mobile/VCB/Common/Login Secure2u'), [('idNumber') : idNumber, ('PAN') : PAN, ('password') : password
	, ('username') : username, ('accountName') : accountName], FailureHandling.STOP_ON_FAILURE)

'Select SSL'
homePage.selectQuickActions(selectItem)

'Go to Select Location screen'
sslPage.goToLocationScreen(timeout, flowControl)

'Add new address'
sslPage.tapAddNewAddress(timeout, flowControl)

'Enter Location'
sslPage.enterLocation(location)

'Fill the form'
sslPage.fillTheLocationForm(locationData)

'Tap Save'
sslPage.tapSave()

'Tap Confirm'
sslPage.tapConfirm()

'Wait Loading'
commonPage.waitLoading(timeout, flowControl)

'Verify success'
commonPage.verifyElementVisible(commonPage.lblDynamic(locationData["Address Name"]), timeout)
Mobile.delay(5)

"Go to Address Book"
sslPage.goToAddBook(locationData["Address Name"])
Mobile.delay(3)

'Verify success'
commonPage.verifyElementVisible(commonPage.lblDynamic(locationData["Address Name"]), timeout)


