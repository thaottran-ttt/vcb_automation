import com.kms.katalon.core.model.FailureHandling
import com.maybank.common.MobileDriver
import com.maybank.mae.internal.pages.base.BasePage
import com.maybank.mae.internal.pages.base.CommonPage
import com.maybank.mae.internal.pages.base.HomePage
import com.maybank.mae.internal.pages.base.applypage.ApplyAccountPage
import com.maybank.mae.internal.pages.base.applypage.ApplyCardPage
import com.maybank.mae.internal.pages.base.applypage.ApplyPage
import com.maybank.utils.MapUtils

import internal.GlobalVariable


Map maeAccountData = MapUtils.parseObjectToMap(maeAccountData)
Map suitabilityMap = MapUtils.parseObjectToMap(suitabilityMap)
Map questionList = MapUtils.parseObjectToMap(questionList)
Map personalDetails = MapUtils.parseObjectToMap(personalDetails)
Map residentialData = MapUtils.parseObjectToMap(residentialData)
Map employmentDetails = MapUtils.parseObjectToMap(employmentDetails)
Map branchMap = MapUtils.parseObjectToMap(branchMap)
Map passportDate = MapUtils.parseObjectToMap(passportDate)
Map edittedPersonalDetails = MapUtils.parseObjectToMap(edittedPersonalDetails)
Map edittedResidentialDetails = MapUtils.parseObjectToMap(edittedResidentialDetails)
Map edittedEmploymentDetails = MapUtils.parseObjectToMap(edittedEmploymentDetails)
Map edittedAdditionalDetails = MapUtils.parseObjectToMap(edittedAdditionalDetails)

HomePage homePage = BasePage.getPage(HomePage)
ApplyPage applyPage = BasePage.getPage(ApplyPage)
ApplyAccountPage applyAccountPage = BasePage.getPage(ApplyAccountPage)
CommonPage commonPage = BasePage.getPage(CommonPage)
ApplyCardPage applyCardPage = BasePage.getPage(ApplyCardPage)

'Navigate To Login'
CustomKeywords.'com.maybank.mae.pages.Onboarding.navigateToLogin'()

'Tap Apply Button'
homePage.tapApply()

'Select Account Type'
applyAccountPage.selectAccountType(accountType)

'Verify First Screen'
applyAccountPage.tapApplyNow()
commonPage.waitLoading()
'Choose type Mykad fill in Mykad infor'
applyAccountPage.chooseTypeAndFillInformationPassport(maeAccountData, passportDate, idType)

'Fill Suitability Assessment Information'
applyAccountPage.fillSuitabilityAssessment(suitabilityMap, questionList)

'Fill Personal Details'
String randomMobileNumber = applyAccountPage.generateRandomMobileNumber()
personalDetails['Mobile Number'] = randomMobileNumber
applyAccountPage.fillPersonalDetailsZestiNTB(personalDetails)
commonPage.tapNextBtn()

'Fill Information Residential'
applyAccountPage.fillResidentialInfo(residentialData)
commonPage.tapNextBtn()

'Fill Employment Details'
applyAccountPage.zestiNTBFillEmploymentDetail(employmentDetails)
commonPage.tapNextBtn()

'Fill Branch Details'
applyAccountPage.fillBranchDetail(branchMap)

'Go Through Declaration Screen'
applyAccountPage.goThroughDeclarationPage(radioText)

'Go Through Confirmation Screen'
applyAccountPage.verifyZestiConfirmation(personalDetails, residentialData, employmentDetails, branchMap, [:])
applyAccountPage.verifyEditZestiPassportNTBConfirmation(edittedPersonalDetails, edittedResidentialDetails, edittedEmploymentDetails, edittedAdditionalDetails)
commonPage.tap(commonPage.lblDynamic('Confirm'), GlobalVariable.G_timeoutLong)

'Verify OTP Screen'
applyAccountPage.verifyOtpPage()
commonPage.waitLoading()
commonPage.delay(GlobalVariable.G_timeoutShort)
commonPage.waitLoading()

'Verify Success Screen'
applyAccountPage.verifySuccessPage(successText)

'Tap Register M2U Access'
MobileDriver.scrollToEnd()
MobileDriver.tap(commonPage.lblDynamicNo(registerBtn, 2), GlobalVariable.G_timeoutLong, FailureHandling.STOP_ON_FAILURE)

'Fill username'
applyAccountPage.generateAndFillUsername()
commonPage.tapContinueBtn()

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
commonPage.delay(GlobalVariable.G_timeoutShort)
MobileDriver.verifyElementVisible(commonPage.lblDynamic(registerSuccessMsg), GlobalVariable.G_timeoutLong);
MobileDriver.scrollToEnd()
commonPage.tapDoneBtn()
