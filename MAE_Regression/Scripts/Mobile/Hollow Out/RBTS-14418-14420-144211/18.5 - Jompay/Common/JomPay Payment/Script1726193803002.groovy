import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.maybank.common.MobileDriver as MobileDriver
import com.maybank.mae.internal.pages.base.accountpage.AccountDetailPage as AccountDetailPage
import com.maybank.mae.internal.pages.base.accountpage.AccountsPage as AccountsPage
import com.maybank.mae.internal.pages.base.BasePage as BasePage
import com.maybank.mae.internal.pages.base.CommonPage as CommonPage
import com.maybank.mae.internal.pages.base.HomePage as HomePage
import com.maybank.mae.internal.pages.base.paybill.JomPayPage as JomPayPage
import com.maybank.utils.StringUtils as StringUtils
import com.maybank.mae.internal.pages.base.Secure2uAuthorisationPage as Secure2uAuthorisationPage

CommonPage commonPage = BasePage.getPage(CommonPage)

HomePage homePage = BasePage.getPage(HomePage)

JomPayPage jomPayPage = BasePage.getPage(JomPayPage)

AccountsPage accountsPage = BasePage.getPage(AccountsPage)

AccountDetailPage accountDetailPage = BasePage.getPage(AccountDetailPage)

Secure2uAuthorisationPage secure2uAuthorisationPage = BasePage.getPage(Secure2uAuthorisationPage)

WebUI.callTestCase(findTestCase('Mobile/MAE/Common/Login Secure2u'), [('idNumber') : idNumber, ('PAN') : PAN, ('password') : password
	, ('username') : username, ('accountName') : accountName], FailureHandling.STOP_ON_FAILURE)

if (status == 'Insufficient') {
    String balance = homePage.getBalance()

    int balanceNumber = commonPage.convertAmountToNumber(balance)

    int insufficientAmount = balanceNumber + 1

    amount = insufficientAmount.toString()
}

WebUI.callTestCase(findTestCase('Test Cases/Mobile/Hollow Out/RBTS-14418-14420-144211/18.5 - Jompay/Common/Access JomPay Section'), [('userName') : username, ('password') : password
        , ('TC_name') : '', ('accessFlow') : 'Quick Action', ('accountName') : '', ('runFavorite') : runFavorite, ('shouldCheckLogin') : false], 
    FailureHandling.STOP_ON_FAILURE)

if (runFavorite) {
    'Select JomPAY'
    jomPayPage.selectJomPAY()

    jomPayPage.selectFavourite(favoriteName)
} else {
    'Enter PayBills Info'
    jomPayPage.enterJomPayInfo(billerCode, reference1, reference2)
}

'Enter Amount'
commonPage.enterNO(amount)

'Confirm to pay'
jomPayPage.confirm(Boolean.parseBoolean(isFuture))

if (status != 'Max Limit') {
    //	jomPayPage.selectApproval(status)
    'Select approval'
    secure2uAuthorisationPage.authorize(status)
}

if (status == 'Approve') {
    if (!(runFavorite)) {
        'Add to favourites'
        jomPayPage.addToFavourites(favoriteName)
    }
    
    'View share receipt'
    String RefID = jomPayPage.viewShareReceipt()

    'Click on notification'
    homePage.tapNotification()

    'Verify notification'
    jomPayPage.verifyNotificationMessage(reference1, RefID, Double.parseDouble(amount) / 100, 'Maybank2u: Payments', 'Successful JomPay')

    'Back'
    commonPage.tapBack()

    'Navigate To View All Accounts'
    CustomKeywords.'com.maybank.mae.pages.Maybank2u.viewAllAccounts'()

    'Select account name'
    accountsPage.selectTab(accountName)

    'View share receipt'
    accountDetailPage.viewRecentlyShareReceipt('JomPAY Bill Payment', 'M2U History')
} else {
    'Verify transaction failed screen'
    jomPayPage.verifyTransactionFailed(status, Boolean.parseBoolean(isFuture))
}

if (status == 'Approve') {
    WebUI.callTestCase(findTestCase('Mobile/MAE/Common/M2U History'), [('m2uHistoryProduct') : m2uHistoryProduct, ('m2uHistoryAmount') : StringUtils.getNegativeRMAmount(
                amount), ('password') : password], BasePage.defaultFailureHandling)

    WebUI.callTestCase(findTestCase('Mobile/MAE/Common/Verify Favourite Exists'), [('username') : userName, ('password') : password
            , ('functionName') : tranferName, ('moduleName') : transferTab, ('favouriteName') : favoriteName], BasePage.defaultFailureHandling)
}

'Select Done Button'
commonPage.tapDoneBtn()

'Back to Home Page'
commonPage.backToHome()