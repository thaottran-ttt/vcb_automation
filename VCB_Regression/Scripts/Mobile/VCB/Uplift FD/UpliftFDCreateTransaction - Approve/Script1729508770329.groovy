import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase

import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.vcb.mobile.internal.pages.base.accountpage.AccountsPage
import com.vcb.mobile.internal.pages.base.BasePage
import com.vcb.mobile.internal.pages.base.CommonPage
import com.vcb.mobile.internal.pages.base.fixeddepositpage.FixedDepositPage
import com.vcb.mobile.internal.pages.base.HomePage
import com.vcb.mobile.internal.pages.base.Secure2uAuthorisationPage
import com.vcb.mobile.internal.pages.base.accountpage.UpliftFDPage
import com.vcb.utils.MapUtils

import internal.GlobalVariable as GlobalVariable


HomePage homePage = HomePage.getPage(HomePage)

CommonPage commonPage = BasePage.getPage(CommonPage)

AccountsPage accountsPage = BasePage.getPage(AccountsPage)

FixedDepositPage fixedDepositPage = BasePage.getPage(FixedDepositPage)

UpliftFDPage upliftDFPage = BasePage.getPage(UpliftFDPage)

Secure2uAuthorisationPage S2UAuth = BasePage.getPage(Secure2uAuthorisationPage)

Map inputDropdownData = MapUtils.parseObjectToMap(inputDropdownData)

WebUI.callTestCase(findTestCase('Mobile/VCB/Common/Login Secure2u'), [('idNumber') : idNumber, ('PAN') : PAN, ('password') : password
        , ('username') : username, ('accountName') : accountName, ('flowControl') : com.vcb.common.FlowControlEnum.STOP, ('failStopTestSuite') : false
        , ('yourName') : 'Test'], FailureHandling.STOP_ON_FAILURE)

'Tap View All Accounts > FIXED DEPOSITS'
homePage.tapBtnAccounts()

accountsPage.selectTab(itemTab)

'Select 1 card on Dashboard screen'
fixedDepositPage.selectFDType(fdAccountType)

'Check Cancel Uplift'
fixedDepositPage.cancelUpliftment(fdAccountType)

'Tap on Uplift FD'
fixedDepositPage.selectMenu(fdMenu)

'Select dropdown value'
upliftDFPage.tapDropDownButton(inputDropdownData.keySet().toList()[0], inputDropdownData.get(inputDropdownData.keySet().toList()[0]), 1)
upliftDFPage.tapDropDownButton(inputDropdownData.keySet().toList()[1], inputDropdownData.get(inputDropdownData.keySet().toList()[1]), 2)
upliftDFPage.tapDropDownButton(inputDropdownData.keySet().toList()[2], inputDropdownData.get(inputDropdownData.keySet().toList()[2]), 1)
commonPage.tapContinueBtn()

'Confirm Transaction'
upliftDFPage.confirmTransaction()

'Authorize S2u'
S2UAuth.authorize(status)

'Wait Loading icon disappear'
commonPage.waitLoading(GlobalVariable.G_timeoutMedium)

'View receipt'
upliftDFPage.viewReceipt()