import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase

import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.maybank.mae.internal.pages.base.BasePage
import com.maybank.mae.internal.pages.base.CommonPage
import com.maybank.mae.internal.pages.base.HomePage
import com.maybank.mae.internal.pages.base.Secure2uAuthorisationPage
import com.maybank.mae.internal.pages.base.transferpage.RecipientReferencePage
import com.maybank.mae.internal.pages.base.transferpage.TransferPage

import internal.GlobalVariable as GlobalVariable

WebUI.callTestCase(findTestCase('Mobile/MAE/Common/Login Secure2u'), [('idNumber') : idNumber, ('PAN') : PAN, ('password') : GlobalVariable.G_password
        , ('username') : username, ('accountName') : '', ('flowControl') : com.maybank.common.FlowControlEnum.STOP, ('failStopTestSuite') : false
        , ('yourName') : 'Test'], FailureHandling.STOP_ON_FAILURE)

HomePage homePage = BasePage.getPage(HomePage)

CommonPage commonPage = BasePage.getPage(CommonPage)

TransferPage transferPage = BasePage.getPage(TransferPage)

Secure2uAuthorisationPage s2u = BasePage.getPage(Secure2uAuthorisationPage)

RecipientReferencePage recipientRefPage = BasePage.getPage(RecipientReferencePage)

'Tap Accounts'
homePage.selectQuickActions(tabName)

'Select accounts transfer to'
transferPage.selectAccountTransferto(accountName)

'Enter Amount'
transferPage.enterAmount(amount)

'Verify Recipient Reference'
transferPage.verifyRecipientReference()

'Recipient Reference'
transferPage.recipientReference(recipientReference)

'Confirmation'
transferPage.confirm(isFuture)

'Authorisation'
s2u.authorize(status)

'Status screen'
String RefID = transferPage.statusScreen(status, false, isFuture)