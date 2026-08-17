import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys
import com.vcb.mobile.internal.pages.base.BasePage as BasePage
import com.vcb.mobile.internal.pages.base.OnboardingPage as OnboardingPage
import com.vcb.mobile.internal.pages.base.HomePage as HomePage
import com.vcb.mobile.internal.pages.base.CommonPage as CommonPage
import com.vcb.mobile.internal.pages.base.applypage.ApplyAccountPage as ApplyAccountPage
import com.vcb.mobile.internal.pages.base.applypage.ApplyCASAPage as ApplyCASAPage
import com.vcb.mobile.internal.pages.base.applypage.ApplyPage as ApplyPage
import com.vcb.mobile.internal.pages.base.PushNotificationPage as PushNotificationPage
import com.vcb.mobile.internal.pages.base.Secure2uPage as Secure2uPage
import com.vcb.utils.ListUtils as ListUtils
import com.vcb.utils.StringUtils as StringUtils
import com.vcb.utils.MapUtils as MapUtils
import com.vcb.common.MobileDriver as MobileDriver

OnboardingPage onboardingPage = BasePage.getPage(OnboardingPage)

HomePage homePage = BasePage.getPage(HomePage)

ApplyAccountPage applyAccountPage = BasePage.getPage(ApplyAccountPage)

ApplyCASAPage applyCASAPage = BasePage.getPage(ApplyCASAPage)

ApplyPage applyPage = BasePage.getPage(ApplyPage)

CommonPage commonPage = BasePage.getPage(CommonPage)

PushNotificationPage pushNotificationPage = BasePage.getPage(PushNotificationPage)

Secure2uPage secure2uPage = BasePage.getPage(Secure2uPage)

Map personalDetails = MapUtils.parseObjectToMap(personalDetails)

Map employmentDetails = MapUtils.parseObjectToMap(employmentDetails)

Map residentialDetails = MapUtils.parseObjectToMap(residentialDetails)

Map branchDetails = MapUtils.parseObjectToMap(branchDetails)

Map securityQuestions = MapUtils.parseObjectToMap(securityQuestions)

Map pushNotificationMessage = MapUtils.parseObjectToMap(pushNotificationMessage)

Map suitabilityAssessment = MapUtils.parseObjectToMap(suitabilityAssessment)

List statusScreenSuccess = ListUtils.parseObjectToList(statusScreenSuccess)

'Clear Cache'
commonPage.clearDataAndCache()

'Navigate to Homepage'
if (!(applyCASAPage.quickActions())) {
    onboardingPage.navigateToLogin()
}

'Tap Apply'
MobileDriver.takeScreenshot()

homePage.tapApply()

'Select Account Type'
applyAccountPage.selectAccountType(accountType)

'Tap Apply Now'
MobileDriver.delay(GlobalVariable.G_timeoutShort)

applyAccountPage.tapApplyNow()

'ID Type & Enter MyKad Number'
String myKadNumber = applyCASAPage.chooseIdTypeandMykad(idType, myKadNumber, runValidations)

boolean checkSuitabilityAssessment = applyCASAPage.fillSuitabilityAssessment(suitabilityAssessment)

'eKYC'
applyCASAPage.eKYCBypass(runValidations)

'Fill in Personal Details'
def accountDetails = applyCASAPage.fillPersonalDetails(personalDetails, runValidations)

String fullName = accountDetails['Full name']

String mobileNumber = accountDetails['Mobile Number']

'Fill in Employment Details'
applyCASAPage.fillEmploymentDetails(employmentDetails)

'Apply Debit Card'
applyCASAPage.applyDebitCard(applyCardNow, debitCard, accountType)

if (applyCardNow == true) {
    'Fill in Delivery Address Details for Debit Card'
    applyCASAPage.fillDeliveryCardDetails(residentialDetails, accountType)
}

'Declaration Page'
applyCASAPage.declarationPage(runValidations, declarationText)

'Confirmation Page'
applyCASAPage.confirmationPage(applyCardNow, idType, personalDetails, residentialDetails, employmentDetails, branchDetails)

'Request OTP'
applyCASAPage.verifyOtpPage()

'Wait For Next Page'
commonPage.waitLoading()

'Verify Success Screen'
applyCASAPage.verifySuccessPage(statusScreenSuccess)

'M2U ID Registration Page'
String m2uUsername = applyCASAPage.M2UIDPage(m2uUsername, m2uPassword)

'Verify Notification Message'
applyCASAPage.verifyPushNotification(pushNotificationMessage, FailureHandling.OPTIONAL)

'M2U Security Page'
applyCASAPage.securityImageAndPhrase(securityPhrase)

'Security Questions'
applyCASAPage.fillSecurityQuestions(securityQuestions)

'Verify Activation Page'
commonPage.verifyTextVisible('Activate your account now', GlobalVariable.G_timeoutShort)

'Write into Excel'
Map<String, String> accountData = [
	'myKadNumber'   : myKadNumber,
	'Full name'     : fullName,
	'm2uUsername'   : m2uUsername,
	'accountType'   : accountType
]
applyCASAPage.writeDataToExcel(accountData, "/Test Data/KMS/CASA STP/", "Apply CASA STP.xlsx", "Output CASA STP")

/------------------- Start Resume Flow-------------------/

WebUI.callTestCase(findTestCase('Mobile/VCB/CASA STP/Apply Account/NTB/Common/Resume CASA Application - NTB'), [('accountType') : accountType
        , ('username') : m2uUsername, ('password') : GlobalVariable.G_password, ('idNumber') : myKadNumber
        , ('resumeTile') : resumeTile, ('noResumeFlow') : noResumeFlow
        , ('bank') : bank, ('applyCardNow') : applyCardNow], FailureHandling.STOP_ON_FAILURE)
