import com.maybank.mae.internal.pages.base.BasePage
import com.maybank.mae.internal.pages.base.CommonPage
import com.maybank.mae.internal.pages.base.HomePage
import com.maybank.mae.internal.pages.base.StatusScreenPage
import com.maybank.mae.internal.pages.base.applypage.ApplyAccountPage
import com.maybank.mae.internal.pages.base.applypage.ApplyCardPage
import com.maybank.mae.internal.pages.base.applypage.ApplyPage
import com.maybank.utils.MapUtils

import internal.GlobalVariable

Map maeAccountData = MapUtils.parseObjectToMap(maeAccountData)
Map m2uInfo = MapUtils.parseObjectToMap(m2uInfo)
Map residentialData = MapUtils.parseObjectToMap(residentialData)
Map employmentDetails = MapUtils.parseObjectToMap(employmentDetails)
Map branchMap = MapUtils.parseObjectToMap(branchMap)

HomePage homePage = BasePage.getPage(HomePage)
ApplyPage applyPage = BasePage.getPage(ApplyPage)
ApplyAccountPage applyAccountPage = BasePage.getPage(ApplyAccountPage)
CommonPage commonPage = BasePage.getPage(CommonPage)
ApplyCardPage applyCardPage = BasePage.getPage(ApplyCardPage)
StatusScreenPage statusScreenPage = BasePage.getPage(StatusScreenPage)


'Navigate To Login'
CustomKeywords.'com.maybank.mae.pages.Onboarding.navigateToLogin'()

'Tap Apply Button'
homePage.tapApply()

'Select Account Type'
applyAccountPage.selectAccountType(accountType)

'Verify First Screen'
commonPage.tapNextBtn()
commonPage.waitLoading()
'Choose type Mykad fill in Mykad infor'
applyAccountPage.chooseTypeAndFillInformation(maeAccountData, idType)

'Fill Personal Details'
applyAccountPage.fillInStepOneZestIPremierAccount(m2uInfo)

'Fill Information Residential'
applyAccountPage.generateAndFillInformationResidential(residentialData)

'Fill Employment Details'
applyAccountPage.fillEmploymentDetail(employmentDetails)

'Fill Branch Details'
applyAccountPage.fillBranchDetail(branchMap)

'Go Through Declaration Screen'
commonPage.delay(GlobalVariable.G_timeoutMiniShort)
commonPage.swipeByW3CAction(5, 1500, 5, 100)
applyAccountPage.goThroughDeclarationPage(radioText)

'Go Through Confirmation Screen'
applyAccountPage.goThroughConfirmationPage()

'Verify OTP Screen'
applyAccountPage.verifyOtpPage()

'Verify Success Screen'
applyAccountPage.verifySuccessPage(successText)

