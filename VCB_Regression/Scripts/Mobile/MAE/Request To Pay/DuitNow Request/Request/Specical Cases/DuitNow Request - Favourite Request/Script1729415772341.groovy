import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import javax.swing.text.MaskFormatter

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
import com.maybank.common.MobileDriver
import com.maybank.mae.internal.pages.base.accountpage.AccountDetailPage
import com.maybank.mae.internal.pages.base.accountpage.AccountsPage
import com.maybank.mae.internal.pages.base.BasePage as BasePage
import com.maybank.mae.internal.pages.base.CommonPage
import com.maybank.mae.internal.pages.base.ConfirmationPage
import com.maybank.mae.internal.pages.base.transferpage.TransferPage as TransferPage
import com.maybank.mae.internal.pages.base.HomePage as HomePage
import com.maybank.mae.internal.pages.base.sendandrequestpage.SendAndRequestPage as SendAndRequestPage
import com.maybank.mae.internal.pages.base.sendandrequestpage.DuitNowRequestPage as DuitNowRequestPage

HomePage homePage = BasePage.getPage(HomePage)

CommonPage commonPage = BasePage.getPage(CommonPage)

SendAndRequestPage sendAndRequestPage = BasePage.getPage(SendAndRequestPage)

DuitNowRequestPage dutiNowRequestPage = BasePage.getPage(DuitNowRequestPage)

AccountsPage accountsPage = BasePage.getPage(AccountsPage)

AccountDetailPage accountDetailPage = BasePage.getPage(AccountDetailPage)

TransferPage transferPage = BasePage.getPage(TransferPage)

ConfirmationPage confirmationPage = BasePage.getPage(ConfirmationPage)

'Back to home'
if ((MobileDriver.waitForElementPresent(sendAndRequestPage.btnDuitNowRequest(), GlobalVariable.G_timeoutMedium))) {
	commonPage.tapBack()
}

if (!(homePage.checkIsAccountLogin())) {
	WebUI.callTestCase(findTestCase('Mobile/MAE/Common/Login Secure2u'), [('idNumber') : idNumber, ('PAN') : PAN, ('password') : password
			, ('username') : username, ('accountName') : accountName], FailureHandling.STOP_ON_FAILURE)
}

'Access DuitNow Request from Account details entry point'
CustomKeywords.'com.maybank.mae.pages.Maybank2u.viewAllAccounts'()

'Check enter PIN'
commonPage.checkEnterPIN()

'Select account name'
accountsPage.selectTab(accountName)

'Select Send & Request'
homePage.selectQuickActions('Send & Request')

'Wait For DuitNow Request Favourites Present'
Mobile.waitForElementPresent(commonPage.lblDynamic('FAVOURITES'), timeout)

Mobile.delay(GlobalVariable.G_timeoutShort)

'Select FAVOURITES'
MobileDriver.tap(commonPage.lblDynamic('FAVOURITES'), timeout)

'Wait For DuitNow Request Favourites Present'
Mobile.waitForElementPresent(commonPage.lblDynamic('DuitNow Request Favourites'), timeout)

'Verify search result'
dutiNowRequestPage.verifySearchFavourites(inputTextValue, true)

Mobile.hideKeyboard()

'Select first search value'
dutiNowRequestPage.tapFirstSearchValue(password,inputTextValue)

'Input Amount'
dutiNowRequestPage.enterAmount('1')

commonPage.tapBack()

'Input Amount'
dutiNowRequestPage.enterAmount('1')

Mobile.delay(GlobalVariable.G_timeoutShort)

if(recipientReference.equals('')) {
	Random rand = new Random()
	recipientReference = ('Recipient Ref ' + Integer.toString(rand.nextInt(1000)))
}

'Input Recipient Reference'
MobileDriver.setText(commonPage.txtEditNo(1), recipientReference, GlobalVariable.G_timeoutLong)

'Select Continue button'
dutiNowRequestPage.tapContinueButton()

MobileDriver.delay(GlobalVariable.G_timeoutShort)

confirmationPage.selectAmountEditableType(isAmountEditable)

if(validateNotifyViaEmail) {
	confirmationPage.inputNotifyViaEmail(notifyViaEmailData)
	'Select Send Now button'
	dutiNowRequestPage.tapSendNowButton()
	confirmationPage.verifyNotifyViaEmail(notifyViaEmailData)
	'Clear'
	confirmationPage.inputNotifyViaEmail([''])
}

'Select Send Now button'
dutiNowRequestPage.tapSendNowButton()

'Verify status'
dutiNowRequestPage.verifyDuitNowRequestStatusUI(status)

'Back To Home'
dutiNowRequestPage.backToEntryPoint()