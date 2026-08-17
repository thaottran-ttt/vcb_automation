import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase

import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.maybank.common.MobileDriver
import com.maybank.mae.internal.pages.base.accountpage.AccountDetailPage
import com.maybank.mae.internal.pages.base.accountpage.AccountsPage
import com.maybank.mae.internal.pages.base.BasePage
import com.maybank.mae.internal.pages.base.CommonPage
import com.maybank.mae.internal.pages.base.HomePage
import com.maybank.mae.internal.pages.base.paybill.JomPayPage
import com.maybank.utils.StringUtils
import com.maybank.mae.internal.pages.base.Secure2uAuthorisationPage
import com.maybank.mae.internal.pages.base.NotificationHomePage

MobileDriver.info("Start " + TC_name)

CommonPage commonPage = BasePage.getPage(CommonPage)
HomePage homePage = BasePage.getPage(HomePage)
JomPayPage jomPayPage = BasePage.getPage(JomPayPage)
AccountsPage accountsPage = BasePage.getPage(AccountsPage)
AccountDetailPage accountDetailPage = BasePage.getPage(AccountDetailPage)
Secure2uAuthorisationPage secure2uAuthorisationPage = BasePage.getPage(Secure2uAuthorisationPage)
NotificationHomePage notificationHomePage = BasePage.getPage(NotificationHomePage)

WebUI.callTestCase(findTestCase('Mobile/MAE/Common/Login Secure2u'), [('idNumber') : idNumber, ('PAN') : PAN, ('password') : password
            , ('username') : username, ('accountName') : accountName], FailureHandling.STOP_ON_FAILURE)

if (status == 'Insufficient') {
	String balance = homePage.getBalance()
	int balanceNumber = commonPage.convertAmountToNumber(balance)
	int insufficientAmount = balanceNumber + 1
	amount = insufficientAmount.toString()
}

WebUI.callTestCase(findTestCase('Test Cases/Mobile/MAE/JomPay/Access JomPay Section'), [('userName') : username, ('password') : password, runFavorite:runFavorite, shouldCheckLogin: false], 
    FailureHandling.STOP_ON_FAILURE)

if(runFavorite) {
	'Select JomPAY'
	jomPayPage.selectJomPAY()
	jomPayPage.selectFavourite(favoriteName)
}else {
	'Enter PayBills Info'
	jomPayPage.enterJomPayInfo(billerCode, reference1, reference2)
}

'Enter Amount'
commonPage.enterNO(amount)

'Confirm to pay'
jomPayPage.confirm(Boolean.parseBoolean(isFuture))

if (status != 'Max Limit') {
	'Select approval'
//	jomPayPage.selectApproval(status)
	secure2uAuthorisationPage.authorize(status)
}

String RefID = ''
if (status == 'Approve') {
    'View share receipt'
    RefID = jomPayPage.viewShareReceipt()

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
	commonPage.backToHome()
	'Verify Reprint'
	List<String> historyVerificationList = [
		RefID
	]
	WebUI.callTestCase(findTestCase('Mobile/MAE/Common/Reprint Receipt Verification'), [('m2uHistoryProduct') : m2uHistoryProduct
		, ('listShareReceiptVerification') : historyVerificationList], FailureHandling.STOP_ON_FAILURE)
	
	
	'Back To Home Page'
	commonPage.backToHome()
	
	'Tap bell Icon'
	homePage.tapNotification()
	
	'Verify Receive Notfication'
	String formattedAmount = String.format("%.2f", Double.parseDouble(amount) / 100)
	String messagePN="Successful JomPay of RM ${formattedAmount} to .+ \\(${reference1}\\). REF: ${RefID}"
	notificationHomePage.verifyNotification('Maybank2u: Payments', 'Successful JomPay', messagePN)
}
