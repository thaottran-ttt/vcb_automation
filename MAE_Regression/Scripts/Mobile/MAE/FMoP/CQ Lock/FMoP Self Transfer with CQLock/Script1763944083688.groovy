import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase

import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.maybank.mae.internal.pages.base.BasePage
import com.maybank.mae.internal.pages.base.CommonPage
import com.maybank.mae.internal.pages.base.HomePage
import com.maybank.mae.internal.pages.base.Secure2uAuthorisationPage
import com.maybank.mae.internal.pages.base.transferpage.RecipientReferencePage
import com.maybank.mae.internal.pages.base.transferpage.TransferPage
import com.maybank.common.MobileDriver
import com.maybank.mae.internal.pages.base.StatusScreenPage as StatusScreenPage

import internal.GlobalVariable as GlobalVariable
StatusScreenPage statusScreenPage = BasePage.getPage(StatusScreenPage)

WebUI.callTestCase(findTestCase('Mobile/MAE/Common/Login Secure2u With Secure Question'), [('idNumber') : idNumber, ('PAN') : PAN, ('password') : GlobalVariable.G_password
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

'Enter Incorrect Answer For Security Question 1st time'
CustomKeywords.'com.maybank.mae.pages.SecurityQuestion.inputAnswer'("incorrect")

MobileDriver.delay(GlobalVariable.G_timeoutLong)

'Enter Incorrect Answer For Security Question 2nd time'
CustomKeywords.'com.maybank.mae.pages.SecurityQuestion.inputAnswer'("incorrect")

MobileDriver.delay(GlobalVariable.G_timeoutLong)

'Enter Incorrect Answer For Security Question 3rd time'
CustomKeywords.'com.maybank.mae.pages.SecurityQuestion.inputAnswer'("incorrect")

MobileDriver.delay(GlobalVariable.G_timeoutLong)

MobileDriver.takeScreenshot()

'Status Screen'
String ref = statusScreenPage.verifyStatusScreen(statusTitle, statusDescription)
