import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase

import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.vcb.common.MobileDriver
import com.vcb.mobile.internal.pages.base.BasePage
import com.vcb.mobile.internal.pages.base.CommonPage
import com.vcb.mobile.internal.pages.base.HomePage
import com.vcb.mobile.internal.pages.base.StatusScreenPage
import com.vcb.mobile.internal.pages.base.applypage.ApplyAccountPage
import com.vcb.mobile.internal.pages.base.applypage.ApplyCardPage
import com.vcb.mobile.internal.pages.base.applypage.ApplyPage
import com.vcb.utils.MapUtils

import internal.GlobalVariable as GlobalVariable


Map suitabilityMap = MapUtils.parseObjectToMap(suitabilityMap)
Map questionList = MapUtils.parseObjectToMap(questionList)
Map personalDetails = MapUtils.parseObjectToMap(personalDetails)
Map residentialData = MapUtils.parseObjectToMap(residentialData)
Map employmentDetails = MapUtils.parseObjectToMap(employmentDetails)
Map edittedPersonalDetails = MapUtils.parseObjectToMap(edittedPersonalDetails)
Map edittedDebitCardDeliveryAddress = MapUtils.parseObjectToMap(edittedDebitCardDeliveryAddress)
Map edittedEmploymentDetails = MapUtils.parseObjectToMap(edittedEmploymentDetails)
Map edittedBranch = MapUtils.parseObjectToMap(edittedBranch)

HomePage homePage = BasePage.getPage(HomePage)
ApplyPage applyPage = BasePage.getPage(ApplyPage)
ApplyAccountPage applyAccountPage = BasePage.getPage(ApplyAccountPage)
CommonPage commonPage = BasePage.getPage(CommonPage)
ApplyCardPage applyCardPage = BasePage.getPage(ApplyCardPage)
StatusScreenPage statusScreenPage = BasePage.getPage(StatusScreenPage)

'Login Secure2u'
WebUI.callTestCase(findTestCase('Mobile/VCB/Common/Login Secure2u'), [('idNumber') : idNumber, ('PAN') : PAN, ('password') : password
	, ('username') : username, ('accountName') : '', ('flowControl') : com.vcb.common.FlowControlEnum.STOP, ('failStopTestSuite') : false
	, ('yourName') : 'Test'], FailureHandling.STOP_ON_FAILURE)

'Tap Apply Button'
homePage.tapApply()

'Select Account Type'
applyAccountPage.selectAccountType(accountType)

'Verify First Screen'
applyAccountPage.tapApplyNow()
commonPage.waitLoading()
'Choose type Mykad fill in Mykad infor'
applyAccountPage.chooseIdType(idType)
applyAccountPage.generateMykadNumber()
commonPage.tapNextBtn()

'Fill Suitability Assessment Information'
applyAccountPage.fillSuitabilityAssessment(suitabilityMap, questionList)
commonPage.delay(GlobalVariable.G_timeoutMiniShort)
commonPage.tapNextBtn()

'MyKad Verification'
applyAccountPage.zestiMyKadScanning()

'Fill Personal Details'
String randomMobileNumber = applyAccountPage.generateRandomMobileNumber()
personalDetails['Mobile Number'] = randomMobileNumber
applyAccountPage.fillPersonalDetailsZestiNTB(personalDetails)
commonPage.tapNextBtn()

'Verify at employment details'
applyAccountPage.zestiNTBFillEmploymentDetail(employmentDetails)
commonPage.tapNextBtn()

if (!isApplyLater) {
	'Select debit card'
	commonPage.tap(commonPage.lblContainDynamic(debitCardName), GlobalVariable.G_timeoutLong)
	commonPage.tapNextBtn()
	Mobile.scrollToText('Terms and Conditions')
	commonPage.tap(commonPage.lblContainDynamic(option), GlobalVariable.G_timeoutLong)
	commonPage.tapNextBtn()
} else {
	commonPage.tap(commonPage.lblContainDynamic('Apply Later'), GlobalVariable.G_timeoutLong)
}

'Go Through Declaration Screen'
applyAccountPage.goThroughDeclarationPage(radioText)

'Verify Confirmation Screen'
applyAccountPage.verifyZestiMykadNTBConfirmation(personalDetails, [:], [:], employmentDetails, [:])
applyAccountPage.verifyEditZestiMykadNTBConfirmation(edittedPersonalDetails, residentialData, edittedDebitCardDeliveryAddress, edittedEmploymentDetails, edittedBranch)
commonPage.tap(commonPage.lblDynamic('Confirm'), GlobalVariable.G_timeoutLong)

'Verify OTP Screen'
applyAccountPage.verifyOtpPage()
commonPage.waitLoading()
commonPage.delay(GlobalVariable.G_timeoutShort)

'Verify Success Screen'
applyAccountPage.verifySuccessPage(successText)

'Tap Register M2U Access'
commonPage.tap(commonPage.lblDynamic(registerBtn), GlobalVariable.G_timeoutLong)

'Fill username'
applyAccountPage.generateAndFillUsername()
commonPage.tapContinueBtn()
commonPage.delay(GlobalVariable.G_timeoutMiniShort)

'Enter and verify create password'
applyAccountPage.verifyCreatePassword(newPassword)
commonPage.tapContinueBtn()
commonPage.waitLoading()
commonPage.delay(GlobalVariable.G_timeoutShort)
commonPage.waitLoading()

'Upload security image'
applyAccountPage.uploadSecurityImage()
commonPage.tapContinueBtn()

'Enter security phrase'
applyAccountPage.enterSecurityPhrase(securityPhrase)
commonPage.tapContinueBtn()
commonPage.waitLoading()
commonPage.delay(GlobalVariable.G_timeoutShort)
commonPage.waitLoading()

'Set up security questions'
applyAccountPage.setupSecurityQuestions(securityQuestions)
commonPage.tapContinueBtn()
commonPage.waitLoading()

'Verify success message'
MobileDriver.verifyElementVisible(commonPage.lblDynamic(registerSuccessMsg), GlobalVariable.G_timeoutLong);
commonPage.tapDoneBtn()
