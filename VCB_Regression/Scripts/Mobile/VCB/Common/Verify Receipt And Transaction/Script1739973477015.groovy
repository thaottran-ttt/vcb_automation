import com.vcb.mobile.internal.pages.base.BasePage
import com.vcb.mobile.internal.pages.base.CommonPage
import com.vcb.mobile.internal.pages.base.CommonPage.TestObjectTypeEnum
import com.vcb.mobile.internal.pages.base.HomePage
import com.vcb.mobile.internal.pages.base.PushNotificationPage
import com.vcb.mobile.internal.pages.base.ShareReceiptPage
import com.vcb.mobile.internal.pages.base.accountpage.AccountDetailPage
import com.vcb.mobile.internal.pages.base.accountpage.AccountsPage
import com.vcb.utils.ListUtils


PushNotificationPage pushNotificationPage = BasePage.getPage(PushNotificationPage)
ShareReceiptPage shareReceiptPage = BasePage.getPage(ShareReceiptPage)
CommonPage commonPage = BasePage.getPage(CommonPage)
HomePage homePage = BasePage.getPage(HomePage)
AccountDetailPage accountDetailPage = BasePage.getPage(AccountDetailPage)
AccountsPage accountPage = BasePage.getPage(AccountsPage)


List verifyList = ListUtils.parseObjectToList(verifyList)

TestObjectTypeEnum verifyType = isContains ?  TestObjectTypeEnum.CONTAINS : TestObjectTypeEnum.TEXT

'Add refID to list and verify receipt'
shareReceiptPage.shareReceipts(verifyList)
commonPage.tapDoneBtn()

'Verify notification'
pushNotificationPage.verifyPushNotification(notificationTitle, notificationDescription, BasePage.defaultFailureHandling, verifyType)

'Back to Home Page and navigate to account'
commonPage.backToHome()

'Tap to Account'
homePage.tapBtnAccounts()

if(accountName != '') {
	'Tap to account'
	accountPage.selectAccount(accountName)	
}
else {
	'Navigate to Account Detail'
	homePage.navigateToAccountDetail()
}

'Verify M2U Transaction'
accountDetailPage.verifyReprintReceiptWithData(receiptTitle, verifyList)

'Clean data list'
verifyList.clear()