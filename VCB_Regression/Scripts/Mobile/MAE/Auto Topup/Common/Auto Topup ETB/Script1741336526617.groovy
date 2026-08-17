import com.maybank.mae.internal.pages.base.BasePage as BasePage
import com.maybank.mae.internal.pages.base.StatusScreenPage as StatusScreenPage
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
import com.maybank.mae.internal.pages.base.applypage.ApplyAccountPage as ApplyAccountPage
import com.maybank.mae.internal.pages.base.applypage.ApplyPage as ApplyPage
import com.maybank.mae.internal.pages.base.transferpage.TransferPage as TransferPage
import com.maybank.utils.ListUtils as ListUtils
import com.maybank.mae.internal.pages.base.CommonPage as CommonPage
import com.maybank.mae.internal.pages.base.ConfirmationPage as ConfirmationPage
import com.maybank.mae.internal.pages.base.HomePage as HomePage
import com.maybank.mae.internal.pages.base.PushNotificationPage as PushNotificationPage
import com.maybank.mae.internal.pages.base.Secure2uAuthorisationPage as Secure2uAuthorisationPage
import com.maybank.mae.internal.pages.base.accountpage.AccountDetailPage as AccountDetailPage
import com.maybank.mae.internal.pages.base.accountpage.AccountsPage as AccountsPage
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

HomePage homePage = BasePage.getPage(HomePage)

AccountsPage accountsPage = BasePage.getPage(AccountsPage)

AccountDetailPage accountDetailPage = BasePage.getPage(AccountDetailPage)

CommonPage commonPage = BasePage.getPage(CommonPage)

ConfirmationPage confirmPage = BasePage.getPage(ConfirmationPage)

Secure2uAuthorisationPage authorizePage = BasePage.getPage(Secure2uAuthorisationPage)

StatusScreenPage statusScreenPage = BasePage.getPage(StatusScreenPage)

PushNotificationPage pushNotificationPage = BasePage.getPage(PushNotificationPage)

WebUI.callTestCase(findTestCase('Mobile/MAE/Common/Login'), [('username') : username, ('password') : password, ('pinCode') : GlobalVariable.G_pinCode
        , ('yourName') : GlobalVariable.G_yourName, ('accountName') : accountName, ('flowControl') : com.maybank.common.FlowControlEnum.STOP
        , ('failStopTestSuite') : false], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('Mobile/MAE/Common/Login Secure2u'), [('idNumber') : idNumber, ('PAN') : PAN, ('password') : password
	, ('username') : username, ('accountName') : accountName], FailureHandling.STOP_ON_FAILURE)

'Tap Accounts'
homePage.tapBtnAccounts()

'Access with pin'
commonPage.checkEnterPIN()

'Tap Accounts Tab'
accountsPage.selectTab(tabName)

'Select Account'
accountsPage.selectAccount(accountType)

'Access with Password'
commonPage.checkEnterPassword(password)

'Tap on Auto Topup'
Mobile.tap(commonPage.lblContainDynamic('Auto Top Up'), GlobalVariable.G_timeoutMedium)

if (isDeactivate) {
    'Tap on Deactivate'
    Mobile.tap(commonPage.lblDynamic('Deactivate'), GlobalVariable.G_timeoutMedium)

    'Verify Deactivate Pop Up Box'
    commonPage.verifyElements(['Deactivate Auto Top Up', 'Are you sure you want to deactivate Auto Top Up?', 'Cancel', 'Deactivate'], 
        commonPage.lblContainDynamicPath(), GlobalVariable.G_timeoutMedium)

    'Tap on Deactivate'
    Mobile.tap(commonPage.lblDynamic('Deactivate'), GlobalVariable.G_timeoutMedium)

    'Authorize Page'
    authorizePage.authorize(status)
	
    'Status Screen'
    String refID = statusScreenPage.verifyStatusScreen(Arrays.asList(verifyStatusScreenDescriptionData), Arrays.asList(verifyStatusScreenDescriptionData))

    'Tap on Done'
    commonPage.tapDoneBtn()

    return null
}

if (!(isSetUp)) {
	'Verify Edit Screen'
	commonPage.verifyElements(['Auto top up when balance falls below', 'Top up amount', 'Auto top up using'
			, 'Deactivate'], commonPage.lblContainDynamicPath(), GlobalVariable.G_timeoutMedium)
	'TRY GET TEXT FOR AMOUNT AND VERIFY FROM THERE AND TEST'
	
	'Click on Edit'
	Mobile.tap(commonPage.lblDynamic('Edit'), GlobalVariable.G_timeoutMedium)
}

'Verify Screen'
commonPage.verifyElements(['Enable auto top up so you\'ll never run low on credits', 'Auto top up when balance falls below'
        , 'Top up amount'], commonPage.lblContainDynamicPath(), GlobalVariable.G_timeoutMedium)

'Set Amount When Balance Falls Below'
Mobile.tap(commonPage.lblDynamicNo(amountBalance, 1), GlobalVariable.G_timeoutMedium)

'Set Top Up Amount'
Mobile.tap(commonPage.lblDynamicNo(amountTopUp, 2), GlobalVariable.G_timeoutMedium)

'Tap on Continue'
commonPage.tapContinueBtn()

commonPage.waitLoading()

Mobile.delay(5)

'Confirmation'
confirmPage.confirm('Auto Topup', [('Date') : 'Today'])

'Tap on Set Up'
Mobile.tap(commonPage.lblDynamic('Set up Now'), GlobalVariable.G_timeoutMedium)

'Authorize Page'
authorizePage.authorize(status)

'Status Screen'
String refID = statusScreenPage.verifyStatusScreen(Arrays.asList(verifyStatusScreenDescriptionData), Arrays.asList(verifyStatusScreenDescriptionData))

'Tap on Done'
commonPage.tapDoneBtn()


if(!(status == 'Expired' || status == 'Reject')) {
'Verify notification'
pushNotificationPage.verifyPushNotification(titlePN, messagePN)
}


