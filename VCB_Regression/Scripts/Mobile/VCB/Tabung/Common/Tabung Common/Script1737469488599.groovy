import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.vcb.mobile.internal.pages.base.BasePage as BasePage
import com.vcb.mobile.internal.pages.base.CommonPage as CommonPage
import com.vcb.mobile.internal.pages.base.HomePage
import com.vcb.mobile.internal.pages.base.PushNotificationPage
import com.vcb.mobile.internal.pages.base.Secure2uAuthorisationPage as Secure2uAuthorisationPage
import com.vcb.mobile.internal.pages.base.Secure2uAuthorisationPage.AuthorisationType as AuthorisationType
import com.vcb.mobile.internal.pages.base.StatusScreenPage as StatusScreenPage
import com.vcb.mobile.internal.pages.base.loyaltypage.LoyaltyPage as LoyaltyPage
import com.vcb.mobile.internal.pages.base.tabung.TabungPage
import com.vcb.utils.ListUtils
import com.vcb.utils.MapUtils as MapUtils
import com.vcb.utils.StringUtils as StringUtils
import internal.GlobalVariable as GlobalVariable
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.util.KeywordUtil

Map tabungMap = MapUtils.parseObjectToMap(tabungMap)

Map tabungActionMap = MapUtils.parseObjectToMap(tabungActionMap)
tabungActionMap.put("regex",regex)
tabungActionMap.put("title",actionStatusTitle)
tabungActionMap.put("description",actionStatusDescription)

mobileNumberShareBill = ListUtils.parseObjectToList(mobileNumberShareBill)

HomePage homePage = BasePage.getPage(HomePage)

LoyaltyPage loyaltyPage = BasePage.getPage(LoyaltyPage)

TabungPage tabungPage = BasePage.getPage(TabungPage)

CommonPage commonPage = BasePage.getPage(CommonPage)

Secure2uAuthorisationPage secure2uAuthorisationPage = BasePage.getPage(Secure2uAuthorisationPage)

StatusScreenPage statusScreenPage = BasePage.getPage(StatusScreenPage)

PushNotificationPage pushNotificationPage = BasePage.getPage(PushNotificationPage)

WebUI.callTestCase(findTestCase('Mobile/VCB/Common/Login Secure2u'), [('idNumber') : idNumber, ('PAN') : PAN, ('password') : GlobalVariable.G_password
        , ('username') : username, ('accountName') : '', ('flowControl') : com.vcb.common.FlowControlEnum.STOP, ('failStopTestSuite') : false
        , ('yourName') : GlobalVariable.G_yourName], FailureHandling.STOP_ON_FAILURE)

'Navigate to View All Screen'
homePage.selectQuickActions(action)

commonPage.waitLoading()

if(shouldCreateTabung) {
	'Create Tabung'
	tabungPage.createTabung(tabungMap)
	commonPage.waitLoading()
	'Create Tabung with group'
	if (isGroup) {
	    tabungPage.createGroup(mobileNumberShareBill)
		commonPage.waitLoading()	
		}
	
	'Select account and navigate to Confirmation screen'
	tabungPage.selectAccountAndConfirm()
	
	'Confirm create tabung'
	tabungPage.tabungConfirmaiton()
	
	'Enters PIN for S2U V5'
	commonPage.checkEnterS2UV5PIN(GlobalVariable.G_timeoutMiniShort)
		
	'Authorisation'
	secure2uAuthorisationPage.authorizeS2UV5BioPin(status)
		
	'Verify status screen'
	String ref = statusScreenPage.verifyStatusScreen(statusTitle, statusDescription, regex)
	'Tap Done'
	commonPage.tapDoneBtn()
}	
else {
	commonPage.info("This test case doesn't need to create new Tabung")
} 
if (verifyDashboard) {
	'Convert amount'
    String amount = StringUtils.convertNoStringToAmount(tabungMap.get('amount'))

	'Verify Tabung content'
    tabungPage.verifyTabungContent(amount)
}
else {
	commonPage.info("This test case doesn't need to Verify Dashboard")
}
if(isMakeAction) {
	commonPage.delay(3)
	'Handle cases for tabung after created'
	String redfId = tabungPage.actionWithTabung(tabungMap.get('categoryName'), actionTabung, tabungActionMap)
}
else {
	commonPage.info("This test case doesn't make any action with Tabung")
}
if(shouldVerifyNotification == 'true') {
	'Verify push notification'
	pushNotificationPage.verifyPushNotification(title, message)
}
else {
	commonPage.info("This test case doesn't need to Verify Notification")
}