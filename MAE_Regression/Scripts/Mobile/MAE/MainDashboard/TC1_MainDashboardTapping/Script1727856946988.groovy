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

import com.maybank.mae.internal.pages.base.BasePage
import com.maybank.mae.internal.pages.base.HomePage
import com.maybank.mae.internal.pages.base.NotificationHomePage
import com.maybank.mae.internal.pages.base.tabung.TabungPage

FailureHandling flowControl = FailureHandling.CONTINUE_ON_FAILURE

HomePage homePage = BasePage.getPage(HomePage)
NotificationHomePage notificationPage = BasePage.getPage(NotificationHomePage)
TabungPage tabungPage = BasePage.getPage(TabungPage)

WebUI.callTestCase(findTestCase('Mobile/MAE/Common/Login'), [('username') : username, ('password') : password, ('pinCode') : GlobalVariable.G_pinCode
        , ('yourName') : GlobalVariable.G_yourName, ('accountName') : accountName, ('flowControl') : com.maybank.common.FlowControlEnum.STOP
        , ('failStopTestSuite') : false], FailureHandling.STOP_ON_FAILURE)

'Verify the user is successfully logged in'
homePage.verifyProfileName(profileName, flowControl)

'Click on Home Button'
homePage.clickHomeButton(flowControl)

'Verify Apply Button'
homePage.verifyApplyButton()

'Verify Account Button'
homePage.verifyAccountsButton()

'See and Click Notification Button'
homePage.tapNotification()
notificationPage.backToHome()

'Verify Wallet and eyes button'
homePage.clickEyeButton(flowControl)

'Verify View all account button'
homePage.verifyViewAllAccountsButton()

'Verify Qrpay Scan button - bottom menu is visible'
homePage.verifyQRPayScanButton()

'Verify Expensess button - bottom menu is visible'
homePage.verifyExpensesButton()

'Verify Apply button - bottom menu is visible'
homePage.verifyApplyButton()

'Verify Quick Action'
homePage.verifyQuickActionstext()

'Verify Customise button'
homePage.verifyCustomiseButton()

'Customize the quick actions - To Do'

'Verify Sama2 Lokal is avaiable in Home page'

'Verify View All at Quick Actions sections'
homePage.verifyQuickActionItem(quicActionItems)

'Verify Spending Summary'
homePage.verifySpendingSummaryButton()

'Verify Survery Banner'
'out of date'

'Verify Tabung'
homePage.verifyTabungButton()

'Tap To View Tabung List'
homePage.tapToViewTabung()

'View Tabung List'
homePage.verifyTabungList(tabungItems)

'Verify View All Tabung'
homePage.verifyAllTabungList()

'Verify Tabung amount and contains'
tabungPage.verifyTabungContent(amount)

'Verify Deals For You'
homePage.verifyDealsForYouButton()

'Verify Top Read'
homePage.verifyTopReadsButton()
homePage.verifyViewMoreTopReadsContains()

'Verify Manage Widget'
homePage.verifyManageWidgetsButton()
