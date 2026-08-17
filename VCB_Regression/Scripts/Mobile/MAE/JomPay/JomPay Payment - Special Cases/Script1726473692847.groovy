import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase

import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.maybank.common.MobileDriver
import com.maybank.mae.internal.pages.base.BasePage
import com.maybank.mae.internal.pages.base.CommonPage
import com.maybank.mae.internal.pages.base.HomePage
import com.maybank.mae.internal.pages.base.paybill.JomPayPage

import internal.GlobalVariable as GlobalVariable

CommonPage commonPage = BasePage.getPage(CommonPage)

HomePage homePage = BasePage.getPage(HomePage)

JomPayPage jomPayPage = BasePage.getPage(JomPayPage)

WebUI.callTestCase(findTestCase('Mobile/MAE/Common/Login Secure2u'), [('idNumber') : idNumber, ('PAN') : PAN, ('password') : password
            , ('username') : userName], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('Test Cases/Mobile/MAE/JomPay/Access JomPay Section'), [('userName') : userName, ('password') : password], 
    FailureHandling.STOP_ON_FAILURE)

'Enter PayBills Info'
jomPayPage.checkInvalidBiller(billerCode, reference1, reference2)

'Enter Amount'
jomPayPage.checkMinimumAmount('9', '99')

Mobile.delay(5)

'Back'
commonPage.tapBack()

'Edit amount'
commonPage.enterNO('99999')

Mobile.delay(5)

'Select X'
MobileDriver.tap(commonPage.imgNoDynamic(2), GlobalVariable.G_timeoutMedium, FailureHandling.STOP_ON_FAILURE)

'Verify at home page'
homePage.verifyViewAllAccountsButton()