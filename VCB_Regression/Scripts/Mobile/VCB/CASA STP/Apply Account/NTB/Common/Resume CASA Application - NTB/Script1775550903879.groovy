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
import com.vcb.mobile.internal.pages.base.BasePage
import com.vcb.mobile.internal.pages.base.OnboardingPage
import com.vcb.mobile.internal.pages.base.CommonPage
import com.vcb.mobile.internal.pages.base.HomePage
import com.vcb.mobile.internal.pages.base.applypage.ApplyPage
import com.vcb.mobile.internal.pages.base.applypage.ApplyAccountPage
import com.vcb.mobile.internal.pages.base.applypage.ApplyCASAPage
import com.vcb.mobile.internal.pages.base.Secure2uAuthorisationPage
import com.vcb.utils.ListUtils

import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

OnboardingPage onboardingPage = BasePage.getPage(OnboardingPage)
CommonPage commonPage = BasePage.getPage(CommonPage)
HomePage homePage = BasePage.getPage(HomePage)
ApplyPage applyPage = BasePage.getPage(ApplyPage)
ApplyAccountPage applyAccountPage = BasePage.getPage(ApplyAccountPage)
ApplyCASAPage applyCASAPage = BasePage.getPage(ApplyCASAPage)
Secure2uAuthorisationPage secure2uAuthorisationPage = BasePage.getPage(Secure2uAuthorisationPage)

'Clear Cache'
commonPage.clearDataAndCache()

'Navigate to Homepage'
if (!applyCASAPage.quickActions()) {
	onboardingPage.navigateToLogin()
}

'Tap Apply Button'
homePage.tapApply()

'Select Resume Flow'
commonPage.tapButton(resumeTile)

'Enter ID Number'
applyAccountPage.resumeNTB(idNumber)

'Verify No Resume Flow'
if (commonPage.verifyTextVisible(noResumeFlow, GlobalVariable.G_timeoutShort, FailureHandling.OPTIONAL)) {
    return
}

'Login to the application'
applyAccountPage.signin(username, password)

'Enter Pin'
CustomKeywords.'com.vcb.mobile.pages.Pin.enterPin'()

'Set Biometric'
CustomKeywords.'com.vcb.mobile.pages.Biometric.setup'()

'Enter Otp'
CustomKeywords.'com.vcb.mobile.pages.Otp.enterOtp'()

'Enter Your Name'
CustomKeywords.'com.vcb.mobile.pages.WelcomeOnboard.enterYourName'(GlobalVariable.G_yourName, accountType)

'Take Screenshot'
Mobile.takeScreenshot()

'Select Resume Flow'
commonPage.tapButton(resumeTile)

'Activation Page'
applyAccountPage.activationPage()

'eKYC'
applyCASAPage.eKYCBypass()

'Activate Account'
applyAccountPage.fpxFlow(bank)

'Activation Successful Page'
applyCASAPage.verifyActivationSuccessPage(applyCardNow)

'Tap Home'
homePage.tapHomeMenu()

'Unhide Balance'
applyCASAPage.unhideBalance()