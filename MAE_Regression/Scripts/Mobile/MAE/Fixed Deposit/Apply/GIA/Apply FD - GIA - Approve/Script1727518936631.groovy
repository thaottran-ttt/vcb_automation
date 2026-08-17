import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase

import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.maybank.common.MobileDriver
import com.maybank.mae.internal.pages.base.BasePage
import com.maybank.mae.internal.pages.base.CommonPage
import com.maybank.mae.internal.pages.base.HomePage
import com.maybank.mae.internal.pages.base.Secure2uAuthorisationPage
import com.maybank.mae.internal.pages.base.accountpage.AccountsPage
import com.maybank.mae.internal.pages.base.applypage.ApplyPage

import internal.GlobalVariable

HomePage homePage = HomePage.getPage(HomePage)

CommonPage commonPage = BasePage.getPage(CommonPage)

ApplyPage applyPage = BasePage.getPage(ApplyPage)

AccountsPage accountsPage = BasePage.getPage(AccountsPage)

Secure2uAuthorisationPage secure2uAuthorisationPage = BasePage.getPage(Secure2uAuthorisationPage)

'Login MAE Secure2u'
WebUI.callTestCase(findTestCase('Mobile/MAE/Common/Login Secure2u'), [('idNumber') : idNumber, ('PAN') : PAN, ('password') : password
        , ('username') : username], FailureHandling.STOP_ON_FAILURE)

'Tap Accounts'
homePage.tapBtnAccounts()

'Tap on "FIXED DEPOSITS" tab'
accountsPage.selectTab(tabName)

if (MobileDriver.waitForElementPresent(commonPage.lblDynamic('Make A Placement'), timeout)) {
    'Select + Make A Placement'
    MobileDriver.tap(commonPage.lblDynamic('Make A Placement'), timeout)
} else {
    'Select Apply Now'
    MobileDriver.tap(commonPage.lblDynamic('Apply Now'), timeout)
}

'Select 1 account Type, then tap Next'
applyPage.selectFDAccountType(fdAccountType, timeout)

'Tap on Next'
applyPage.tapNext()

if (commonPage.waitForElementPresent(commonPage.lblDynamic(applyPage.MY_FINANCIAL_OBJECTIVE), GlobalVariable.G_timeoutMedium)) {
    'Step 1: Enter Financial Objective if FD Account Type = General Investment Account-i (GIA-i)'
    commonPage.selectDropdownSiblingGroupItem(applyPage.MY_FINANCIAL_OBJECTIVE, 'defaultValue', timeout)

    'Step 1: Set Suitability Assessment'
    applyPage.setSuitabilityAssessment(fdAccountType, lstAnswers, GlobalVariable.G_timeoutMedium, FailureHandling.STOP_ON_FAILURE)

    Mobile.delay(5)

    'Tap on Next'
    MobileDriver.tap(commonPage.lblDynamic('NEXT'), timeout)
}

if (commonPage.waitForElementPresent(commonPage.lblDynamic('Email address'), GlobalVariable.G_timeoutMedium)) {
    'Tap on Next'
    applyPage.tapNext()

    'Tap on Next'
    applyPage.tapNext()
}

if (commonPage.waitForElementPresent(commonPage.lblDynamic('Account Number'), GlobalVariable.G_timeoutMedium)) {
    'Step 2: Select Account Number'
    applyPage.selectAccountNumber('defaultValue', timeout)
}

Mobile.delay(GlobalVariable.G_timeoutMedium)

'Step 2: Enter Banks Transfer'
applyPage.selectBankTransferFrom('defaultValue', timeout)

'Step 2: Enter Fixed Deposit Detail'
applyPage.enterFixedDepositDetail(fdAccountType, accountFrom, 'NON CAMPAIGN', 'defaultValue', timeout)

'Tap Continue'
commonPage.tapButton(commonPage.UIContinue, timeout)

'Enter Amount'
commonPage.enterNO(amount)

'Step 3: Enter Payment Info'
commonPage.waitLoading()

applyPage.enterPaymentInfo('defaultValue', 'defaultValue', 'defaultValue', 'defaultValue')

'Check Term and Conditions'
applyPage.checkTermAndCondition()

'Tap Make Placement'
MobileDriver.tap(commonPage.lblDynamic('Make Placement'), timeout)

'Select approval'
secure2uAuthorisationPage.authorize(status)

'Verify result'
applyPage.verifyTransactionResult(status)