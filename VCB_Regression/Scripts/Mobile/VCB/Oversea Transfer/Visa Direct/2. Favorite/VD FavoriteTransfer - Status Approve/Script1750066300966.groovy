import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase

import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.vcb.mobile.internal.pages.base.BasePage
import com.vcb.mobile.internal.pages.base.CommonPage
import com.vcb.mobile.internal.pages.base.HomePage
import com.vcb.mobile.internal.pages.base.CommonPage.TestObjectTypeEnum
import com.vcb.mobile.internal.pages.base.Secure2uAuthorisationPage
import com.vcb.mobile.internal.pages.base.accountpage.AccountsPage
import com.vcb.mobile.internal.pages.base.transferpage.TransferPage
import com.vcb.mobile.internal.pages.base.ConfirmationPage
import com.vcb.mobile.internal.pages.base.transferpage.EnterOverseasPage
import com.vcb.mobile.internal.pages.base.PushNotificationPage
import com.vcb.mobile.internal.pages.base.accountpage.AccountDetailPage

import com.vcb.mobile.internal.pages.base.ShareReceiptPage
import com.vcb.mobile.internal.pages.base.StatusScreenPage
import internal.GlobalVariable as GlobalVariable
import com.vcb.utils.StringUtils
import com.vcb.utils.ListUtils
import com.vcb.utils.MapUtils

'Login VCB Secure2u'
WebUI.callTestCase(findTestCase('Mobile/VCB/Common/Login Secure2u'), [('idNumber') : idNumber, ('PAN') : PAN, ('password') : password
	, ('username') : username, ('accountName') : primaryAccount], FailureHandling.STOP_ON_FAILURE)

HomePage homePage = BasePage.getPage(HomePage)

CommonPage commonPage = BasePage.getPage(CommonPage)

TransferPage transferPage = BasePage.getPage(TransferPage)

Secure2uAuthorisationPage s2u = BasePage.getPage(Secure2uAuthorisationPage)

AccountsPage accountsPage = BasePage.getPage(AccountsPage)

ConfirmationPage confirmationPage = BasePage.getPage(ConfirmationPage)

EnterOverseasPage enterOverseasPage = BasePage.getPage(EnterOverseasPage)

PushNotificationPage pushNotificationPage = BasePage.getPage(PushNotificationPage)

ShareReceiptPage shareReceiptPage = BasePage.getPage(ShareReceiptPage)

AccountDetailPage accountDetailPage = BasePage.getPage(AccountDetailPage)

StatusScreenPage statusScreenPage = BasePage.getPage(StatusScreenPage)


List statusTitle = ListUtils.parseObjectToList(statusTitle)
List statusDescription = ListUtils.parseObjectToList(statusDescription)
Map recipientDetails = MapUtils.parseObjectToMap(recipientDetails)

String amount = amount

String newBalance = ''

List<String> verifyList = new ArrayList()

'Tap Button Accounts'
homePage.tapBtnAccounts()

'Check And Tap Got It'
accountsPage.checkAndTapGotIt()

'Choose An Other Account'
confirmationPage.chooseAnotherAccount(accountName)

'Select Transfer Button'
accountsPage.selectTransferButton()

'Select Others Tab'
transferPage.selectTransferToTab(transferTab)

'Select Fav'
enterOverseasPage.selectFavorite(favName)

'Verify UI Debiting Account'
enterOverseasPage.verifyUIDebitingAccount()

'Select Debiting Account Name'
enterOverseasPage.selectDebitingAccount(debitingAccountName)

'Select Continue Button'
transferPage.selectContinueButton()

'Select Input Amount'
enterOverseasPage.selectInputAmount()

'Enter Amount'
commonPage.enterNO(amount)

'Select Dropdown Convert To'
enterOverseasPage.selectDropdownConvertTo(currency)

'Select Continue Button'
transferPage.selectContinueButton()

'Select Fav Visa Direct Transfer'
enterOverseasPage.selectFavVisaDirectTransfer(typeTransfer)

'Select Continue Button'
transferPage.selectContinueButton(FailureHandling.OPTIONAL)

'Enter Terms And Conditions'
enterOverseasPage.enterTermsAndConditions()

'Enter Transfer From'
enterOverseasPage.enterTransferFrom()

'Authorisation'
s2u.authorize(status)
commonPage.delay(GlobalVariable.G_timeoutMedium)

'Status screen'
String RefID = statusScreenPage.verifyStatusScreen(statusTitle, statusDescription)

String formattedAmount = String.format("%.2f", Double.parseDouble(amount)/100)
verifyList.add(RefID)
verifyList.add(formattedAmount)

if(status == "Approve") {
	'Get Foreign Currency Amount'
	String foreignCurrencyAmount = shareReceiptPage.getForeignCurrencyAmount()
	notificationDescription = transferPage.constructNotificationTemplateFTT(formattedAmount,foreignCurrencyAmount,beneficiaryName,receivingBank, beneficiaryAccountNumber, RefID)
	Mobile.comment(notificationDescription)
	Mobile.delay(GlobalVariable.G_timeoutMiniShort)
	'Add refID to list and verify receipt'
	shareReceiptPage.shareReceipts(verifyList)
	commonPage.tapDoneBtn()
	Mobile.comment(notificationTitle)
	Mobile.comment(notificationDescription)
	'Verify notification'
	pushNotificationPage.verifyPushNotification(notificationTitle, notificationDescription, BasePage.defaultFailureHandling, TestObjectTypeEnum.TEXT)
	

	'Back to Home Page and navigate to account'
	commonPage.backToHome()
	
	'Tap to Account'
	homePage.tapBtnAccounts()
	'Select Account'
		if(accountName != '') {
			'Tap to account'
			accountsPage.selectAccount(accountName)
			
			'Get new balance'
			newBalance = homePage.getBalance()
		}
		else {
			'Navigate to Account Detail'
			homePage.navigateToAccountDetail()
		}
}
