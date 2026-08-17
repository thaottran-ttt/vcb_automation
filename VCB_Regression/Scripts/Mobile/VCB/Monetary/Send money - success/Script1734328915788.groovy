import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.vcb.mobile.internal.pages.base.BasePage as BasePage
import com.vcb.mobile.internal.pages.base.CommonPage as CommonPage
import com.vcb.mobile.internal.pages.base.CommonPage.TestObjectTypeEnum as TestObjectTypeEnum
import com.vcb.mobile.internal.pages.base.ConfirmationPage as ConfirmationPage
import com.vcb.mobile.internal.pages.base.HomePage as HomePage
import com.vcb.mobile.internal.pages.base.PushNotificationPage as PushNotificationPage
import com.vcb.mobile.internal.pages.base.Secure2uAuthorisationPage as Secure2uAuthorisationPage
import com.vcb.mobile.internal.pages.base.StatusScreenPage as StatusScreenPage
import com.vcb.mobile.internal.pages.base.sendandrequestpage.SendAndRequestMoneyPage as SendAndRequestMoneyPage
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
import com.vcb.common.MobileDriver
import com.vcb.utils.StringUtils as StringUtils

HomePage homePage = HomePage.getPage(HomePage)

CommonPage commonPage = BasePage.getPage(CommonPage)

SendAndRequestMoneyPage sendAndRequestMoneyPage = BasePage.getPage(SendAndRequestMoneyPage)

ConfirmationPage confirmationPage = BasePage.getPage(ConfirmationPage)

Secure2uAuthorisationPage secure2uAuthorisationPage = BasePage.getPage(Secure2uAuthorisationPage)

PushNotificationPage pushNotificationPage = BasePage.getPage(PushNotificationPage)

StatusScreenPage statusScreenPage = BasePage.getPage(StatusScreenPage)

List<String> verifyList  = new ArrayList()

WebUI.callTestCase(findTestCase('Mobile/VCB/Common/Login Secure2u'), [('idNumber') : idNumber, ('PAN') : PAN, ('password') : GlobalVariable.G_password
        , ('username') : username, ('accountName') : '', ('flowControl') : com.vcb.common.FlowControlEnum.STOP, ('failStopTestSuite') : false], 
    FailureHandling.STOP_ON_FAILURE)

'Select Send & Request Module at Entry Point'
WebUI.callTestCase(findTestCase('Mobile/VCB/Common/Entry point'), [('accessFlow') : accessFlow, ('actionName') : actionName
        , ('accountName') : accountName, ('menuName') : tranferName], FailureHandling.STOP_ON_FAILURE)

'Select Tap'
sendAndRequestMoneyPage.selectTab(tabName)

'Send Money'
sendAndRequestMoneyPage.processSendMoney(mobileNumberMAE, amount, note)
amount = sendAndRequestMoneyPage.editAmount
Mobile.comment(amount)

//'Auth'
//secure2uAuthorisationPage.authorize(status)
'Authorisation'
String statusType = secure2uAuthorisationPage.authorizeS2UV5BioPin(status)

if (statusType.contains(Secure2uAuthorisationPage.AuthorisationType.TAC.toString())) {
	MobileDriver.delay(GlobalVariable.G_timeoutShort)

	'Wait Loading'
	commonPage.waitLoading()

	if (!(statusScreenPage.waitloadStatusScreen())) {
	}
}

'Verify status screeen'
String refId = statusScreenPage.verifyStatusScreen(statusTitle, statusDes, pattern)

verifyList.add(refId)
verifyList.add(amount)

'Back to Home'
statusScreenPage.backToEntryPoint()
commonPage.backToHome()

WebUI.callTestCase(findTestCase('Mobile/VCB/Common/Reprint Receipt Verification'), [('m2uHistoryProduct') : m2uHistoryProduct
	, ('listShareReceiptVerification') : verifyList], FailureHandling.CONTINUE_ON_FAILURE)

String pushNotificationDescription = StringUtils.generateMessage(pushNotificationDescription, [amount, refId])
'Verify Push Notification'
WebUI.callTestCase(findTestCase('Mobile/VCB/Common/Verify Push Notification'), [('titlePN') : pushNotificationTitle, ('startMessage') : 'Welcome to the VCB app.'
	, ('messagePN') : pushNotificationDescription
	, ('ref') : refId, ('amount') : amount], FailureHandling.STOP_ON_FAILURE)
