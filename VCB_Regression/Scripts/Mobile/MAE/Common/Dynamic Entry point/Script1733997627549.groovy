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
import com.maybank.mae.internal.pages.base.accountpage.AccountDetailPage as AccountDetailPage
import com.maybank.mae.internal.pages.base.accountpage.AccountsPage as AccountsPage
import com.maybank.mae.internal.pages.base.BasePage as BasePage
import com.maybank.mae.internal.pages.base.CommonPage as CommonPage
import com.maybank.mae.internal.pages.base.HomePage as HomePage
import internal.GlobalVariable as GlobalVariable
import com.maybank.common.MobileDriver as MobileDriver
import org.openqa.selenium.Keys as Keys

AccountsPage accountsPage = BasePage.getPage(AccountsPage)

CommonPage commonPage = BasePage.getPage(CommonPage)

HomePage homePage = BasePage.getPage(HomePage)

AccountDetailPage accountDetailPage = BasePage.getPage(AccountDetailPage)

String UIRMHide = 'RM ****'

switch (accessFlow) {
    case 'Quick Action':
        homePage.selectQuickActions(actionName)

        break
    case 'MAE Wallet':
        'Wait for Account Balance present'
        if (MobileDriver.waitForElementPresent(commonPage.lblDynamic(UIRMHide), GlobalVariable.G_timeoutShort, FailureHandling.OPTIONAL)) {
            'Select Eye Button'
            MobileDriver.tap(homePage.btnEye(), GlobalVariable.G_timeoutLong, FailureHandling.OPTIONAL)
        }
        
        Mobile.delay(GlobalVariable.G_timeoutShort)

        MobileDriver.takeScreenshot()

        'Select Account Balance: RM'
        MobileDriver.tap(homePage.lblAccountBalance(), GlobalVariable.G_timeoutLong, FailureHandling.STOP_ON_FAILURE)
		Mobile.delay(GlobalVariable.G_timeoutShort)

        'Select menu'
        accountDetailPage.selectMenu(menuName)

        break
    case 'Account Detail':
        'Select Accounts button'
        homePage.tapBtnAccounts()

        'Tap account'
        accountsPage.selectAccount(accountName)

        'Select menu'
        accountDetailPage.selectMenu(menuName)

        break
}