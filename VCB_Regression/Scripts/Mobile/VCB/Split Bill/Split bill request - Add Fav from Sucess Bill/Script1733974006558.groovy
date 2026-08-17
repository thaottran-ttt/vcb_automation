import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.vcb.mobile.internal.pages.base.BasePage as BasePage
import com.vcb.mobile.internal.pages.base.CommonPage as CommonPage
import com.vcb.mobile.internal.pages.base.ConfirmationPage as ConfirmationPage
import com.vcb.mobile.internal.pages.base.HomePage as HomePage
import com.vcb.mobile.internal.pages.base.PushNotificationPage as PushNotificationPage
import com.vcb.mobile.internal.pages.base.Secure2uAuthorisationPage as Secure2uAuthorisationPage
import com.vcb.mobile.internal.pages.base.splitbill.SplitBillPage as SplitBillPage
import com.vcb.utils.ListUtils as ListUtils
import internal.GlobalVariable as GlobalVariable
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint

WebUI.callTestCase(findTestCase('Mobile/VCB/Split Bill/Split bill request - Add contacts'), [('username') : username, 
	('password') : GlobalVariable.G_password, ('idNumber') : idNumber, ('PAN') : PAN, ('samsungApp') : samsungApp, 
	('mobileNumberShareBill') : mobileNumberShareBill, ('skipRestartAfterTestCase') : skipRestartAfterTestCase], 
	FailureHandling.STOP_ON_FAILURE)

HomePage homePage = HomePage.getPage(HomePage)

CommonPage commonPage = BasePage.getPage(CommonPage)

SplitBillPage splitBillPage = BasePage.getPage(SplitBillPage)

ConfirmationPage confirmationPage = BasePage.getPage(ConfirmationPage)

Secure2uAuthorisationPage secure2uAuthorisationPage = BasePage.getPage(Secure2uAuthorisationPage)

PushNotificationPage pushNotificationPage = BasePage.getPage(PushNotificationPage)

mobileNumberShareBill = ListUtils.parseObjectToList(mobileNumberShareBill)

WebUI.callTestCase(findTestCase('Mobile/VCB/Common/Login Secure2u'), [('idNumber') : idNumber, ('PAN') : PAN, ('password') : GlobalVariable.G_password
        , ('username') : username, ('accountName') : '', ('flowControl') : com.vcb.common.FlowControlEnum.STOP, ('failStopTestSuite') : false], 
    FailureHandling.STOP_ON_FAILURE)

'Select PayBills Module at Entry Point'
WebUI.callTestCase(findTestCase('Mobile/VCB/Common/Entry point'), [('accessFlow') : accessFlow, ('actionName') : actionName
        , ('accountName') : accountName, ('menuName') : tranferName], FailureHandling.STOP_ON_FAILURE)

'Select Tap'
splitBillPage.selectTab(tabName)

'Tap to split bill '
splitBillPage.splitBill(type, billName, note, amount, mobileNumberShareBill)

commonPage.waitLoading()

'Verify Status Screen'
splitBillPage.verifyStatusScreen(statusScreenTitle)

'Handle after create Bill'
splitBillPage.handleBillSucess(billName)

'Add to Fav'
splitBillPage.addSucessBillToFav(billName)

'Verify Push Noti'
pushNotificationPage.verifyPushNotificationSamsung24(pushNotificationTitle, pushNotificationDescription)

commonPage.backToHome()

