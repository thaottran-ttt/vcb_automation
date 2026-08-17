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
import com.vcb.mobile.internal.pages.base.BasePage
import com.vcb.mobile.internal.pages.base.OnboardingPage
import com.vcb.mobile.internal.pages.base.HomePage
import com.vcb.mobile.internal.pages.base.CommonPage as CommonPage
import com.vcb.mobile.internal.pages.base.applypage.ApplyAccountPage
import com.vcb.mobile.internal.pages.base.applypage.ApplyCASAPage
import com.vcb.mobile.internal.pages.base.applypage.ApplyPage
import com.vcb.mobile.internal.pages.base.PushNotificationPage
import com.vcb.mobile.internal.pages.base.Secure2uPage as Secure2uPage
import com.vcb.utils.ListUtils as ListUtils
import com.vcb.utils.StringUtils as StringUtils
import com.vcb.utils.MapUtils
import com.vcb.common.MobileDriver

OnboardingPage onboardingPage = BasePage.getPage(OnboardingPage)
HomePage homePage = BasePage.getPage(HomePage)
ApplyAccountPage applyAccountPage = BasePage.getPage(ApplyAccountPage)
ApplyCASAPage applyCASAPage = BasePage.getPage(ApplyCASAPage)
ApplyPage applyPage = BasePage.getPage(ApplyPage)
CommonPage commonPage = BasePage.getPage(CommonPage)
PushNotificationPage pushNotificationPage = BasePage.getPage(PushNotificationPage)
Secure2uPage secure2uPage = BasePage.getPage(Secure2uPage)

Map passportDetails = MapUtils.parseObjectToMap(passportDetails)
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
if (!applyCASAPage.quickActions()) {
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

'ID Type & Enter Passport Number'
def passportInfo = applyCASAPage.chooseIdTypeandPassportMAE(idType, passportDetails, runValidations)
String passportNumber    = passportInfo['Passport Number']
String passportExpDate   = passportInfo['Passport Expiry Date']
String dateOfBirth       = passportInfo['Date of Birth']

'Sign Up Code Page'
boolean checkSignUpCode = applyCASAPage.signUpCodeMaePage()

'eKYC'
applyCASAPage.eKYCBypassPassport(runValidations)

'Fill in Personal Details'
def personalInfo = applyCASAPage.fillPersonalDetailsPassportMAE(personalDetails)
String fullName          = personalInfo ['Full name']
String mobileNumber      = personalInfo ['Mobile Number']

'Request OTP'
applyCASAPage.verifyOtpPage()

'Fill in Residential Details'
applyCASAPage.fillMAEResidentialDetails(idType, residentialDetails)

'Fill in Employment Details'
applyCASAPage.fillEmploymentDetails(employmentDetails)

'Declaration Page'
applyCASAPage.maeDeclarationPage(runValidations, declarationText)

'Confirmation Page'
applyCASAPage.confirmationPage(idType, personalDetails, residentialDetails, employmentDetails, branchDetails)

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

'App Pin Setup'
applyCASAPage.pinSetupForMAE(passportNumber)

'Write into Excel'
Map<String, String> accountData = [	
	'passportNumber' : passportNumber,
	'passportExpDate': passportExpDate,
	'dateOfBirth'	 : dateOfBirth,
	'Full name'      : fullName,
	'mobileNumber'	 : mobileNumber,
	'm2uUsername'    : m2uUsername
]
int currentRow = applyCASAPage.writeDataToExcel(accountData, "/Test Data/KMS/CASA STP/", "Apply CASA STP.xlsx", "Output CASA STP")

'Back to Homepage'
applyCASAPage.Homepage()

'Login'
applyCASAPage.loginMAE(m2uUsername, GlobalVariable.G_password, GlobalVariable.G_yourName, accountType)

'Unhide Balance'
applyCASAPage.unhideBalance()

'Get Account Info'
accountType    = homePage.getAccountName()
accountNumber  = homePage.getAccountNumber()

'Write into Excel'
Map<String, String> accountData2 = [
	'accountType'    : accountType,
	'accountNumber'  : accountNumber
]
applyCASAPage.writeDataToExcel(accountData2, "/Test Data/KMS/CASA STP/", "Apply CASA STP.xlsx", "Output CASA STP", currentRow)

