import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable
import com.maybank.mae.internal.pages.base.HomePage
import com.maybank.mae.internal.pages.base.CommonPage
import com.maybank.mae.internal.pages.base.applypage.ApplyPage
import com.maybank.mae.internal.pages.base.accountpage.AccountsPage
import com.maybank.mae.internal.pages.base.BasePage
import com.maybank.assertions.*
import com.maybank.common.MobileDriver as MobileDriver

HomePage homePage = HomePage.getPage(HomePage)

CommonPage commonPage = BasePage.getPage(CommonPage)

ApplyPage applyPage = BasePage.getPage(ApplyPage)

AccountsPage accountsPage = BasePage.getPage(AccountsPage)

BaseAssert Assert = new BaseAssert()

MobileDriver.info("Start entry point : $entryPoint")

'Login'
WebUI.callTestCase(findTestCase('Mobile/MAE/Common/Login'), [('appId') : GlobalVariable.G_appPath, ('username') : username
        , ('password') : password, ('pinCode') : '111111'], FailureHandling.STOP_ON_FAILURE)

switch (entryPoint) {
    case 'Apply':
        'Tap Apply'
        homePage.tapApply()

        'Tap FIXED DEPOSITS tab'
        accountsPage.selectTab(tabName)

        'Tap Fixed Deposit Account'
        applyPage.tapFixedDepositAccount()

        'Enter password'
        commonPage.checkEnterPassword(password, timeout)

        MobileDriver.verifyElementExist(commonPage.lblDynamic('Fixed Deposit Application'), timeout)

        break
    case 'Fixed Deposit Dashboard':
        'Tap Accounts'
        homePage.tapBtnAccounts()

        'Enter PIN'
        commonPage.checkEnterPIN()

        'Select FIXED DEPOSITS tab'
        accountsPage.selectTab(tabName)

		if (MobileDriver.waitForElementPresent(commonPage.lblDynamic('Make A Placement'), timeout)) {
		    'Select + Make A Placement'
		    MobileDriver.tap(commonPage.lblDynamic('Make A Placement'), timeout)
		} else {
		    'Select Apply Now'
		    MobileDriver.tap(commonPage.lblDynamic('Apply Now'), timeout)
		}

        'Enter password'
        commonPage.checkEnterPassword(password, timeout)

        MobileDriver.verifyElementExist(commonPage.lblDynamic('Fixed Deposit Application'), timeout)

        break
    case 'Fixed Deposit Details':
        'Tap Accounts'
        homePage.tapBtnAccounts()

        'Enter PIN'
        commonPage.checkEnterPIN()

        'Tap FIXED DEPOSITS tab'
        accountsPage.selectTab(tabName)

        MobileDriver.tap(commonPage.lblStartsWith(fdAccountType), timeout)

        Mobile.delay(5)

        'Select Make a Placement'
        MobileDriver.tap(commonPage.lblContainDynamic('Make'), timeout)

        'Enter password'
        commonPage.checkEnterPassword(password, timeout)

        MobileDriver.verifyElementExist(commonPage.lblDynamic(fdAccountType), timeout)

        break
}