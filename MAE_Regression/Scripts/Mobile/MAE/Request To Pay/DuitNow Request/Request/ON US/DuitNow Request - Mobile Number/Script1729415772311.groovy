import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.maybank.mae.internal.pages.base.accountpage.AccountDetailPage as AccountDetailPage
import com.maybank.mae.internal.pages.base.accountpage.AccountsPage as AccountsPage
import com.maybank.mae.internal.pages.base.BasePage as BasePage
import com.maybank.mae.internal.pages.base.CommonPage as CommonPage
import com.maybank.mae.internal.pages.base.sendandrequestpage.DuitNowRequestPage as DuitNowRequestPage
import com.maybank.mae.internal.pages.base.HomePage as HomePage
import com.maybank.mae.internal.pages.base.paybill.JomPayPage as JomPayPage
import com.maybank.mae.internal.pages.base.StatusScreenPage

import internal.GlobalVariable

import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile

DuitNowRequestPage dutiNowRequestPage = BasePage.getPage(DuitNowRequestPage)

JomPayPage jomPayPage = BasePage.getPage(JomPayPage)

CommonPage commonPage = BasePage.getPage(CommonPage)

StatusScreenPage statusScreenPage = BasePage.getPage(StatusScreenPage)

HomePage homePage = BasePage.getPage(HomePage)

Random rand = new Random()
recipientReference = ('Recipient Ref ' + Integer.toString(rand.nextInt(1000)))

WebUI.callTestCase(findTestCase('Test Cases/Mobile/MAE/Request To Pay/DuitNow Request/Common/DuitNow Request'), [('idNumber') : idNumber
        , ('PAN') : PAN, ('password') : password, ('username') : username, ('accountName') : accountName, ('recipientReference') : recipientReference
        , ('amount') : amount, ('requestType') : requestType, ('inputTextValue') : inputTextValue, ('dropdownValue') : dropdownValue
        , ('status') : status], FailureHandling.STOP_ON_FAILURE)

Mobile.delay(GlobalVariable.G_timeoutShort)

'Verify share receipt'
statusScreenPage.verifyShareReceipt("")

'Add to Favourites'
statusScreenPage.addToFavourites("Fav 1!@#%^")

'Back to entry point'
dutiNowRequestPage.backToEntryPoint()

Mobile.delay(GlobalVariable.G_timeoutShort)

'Check is on Send & Request screen'
commonPage.tapBack()

Mobile.delay(GlobalVariable.G_timeoutShort)

'Click on notification'
homePage.tapNotification()

'Verify notification'
dutiNowRequestPage.verifySentRequestNotificationMessage(recipientReference, Double.parseDouble(amount) / 100, 'Maybank2u: DuitNow Request', 
    'You\'ve sent a DuitNow Request')

commonPage.tapBack()