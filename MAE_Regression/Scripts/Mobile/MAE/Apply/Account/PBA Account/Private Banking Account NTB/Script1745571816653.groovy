import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.maybank.common.MobileDriver as MobileDriver
import com.maybank.mae.internal.pages.base.BasePage as BasePage
import com.maybank.mae.internal.pages.base.CommonPage as CommonPage
import com.maybank.mae.internal.pages.base.ConfirmationPage as ConfirmationPage
import com.maybank.mae.internal.pages.base.HomePage as HomePage
import com.maybank.mae.internal.pages.base.Secure2uAuthorisationPage as Secure2uAuthorisationPage
import com.maybank.mae.internal.pages.base.StatusScreenPage as StatusScreenPage
import com.maybank.mae.internal.pages.base.applypage.ApplyAccountPage as ApplyAccountPage
import com.maybank.mae.internal.pages.base.applypage.ApplyPage as ApplyPage
import com.maybank.utils.StringUtils as StringUtils
import internal.GlobalVariable as GlobalVariable

HomePage homePage = BasePage.getPage(HomePage)

ApplyPage applyPage = BasePage.getPage(ApplyPage)

ApplyAccountPage applyAccountPage = BasePage.getPage(ApplyAccountPage)

CommonPage commonPage = BasePage.getPage(CommonPage)

ConfirmationPage confirmationPage = BasePage.getPage(ConfirmationPage)

Secure2uAuthorisationPage secure2uAuthorisationPage = BasePage.getPage(Secure2uAuthorisationPage)

StatusScreenPage statusScreenPage = BasePage.getPage(StatusScreenPage)

String preferAnotherBranch = 'I Prefer Another Branch'

WebUI.callTestCase(findTestCase('Mobile/MAE/Common/Non Login'), [('idNumber') : idNumber, ('PAN') : PAN, ('password') : password
        , ('username') : username, ('accountName') : '', ('flowControl') : com.maybank.common.FlowControlEnum.STOP, ('failStopTestSuite') : false
        , ('yourName') : 'Test'], FailureHandling.STOP_ON_FAILURE)
'Tap Apply Button'
homePage.tapApply()

'Select Account  Tab'
applyPage.selectTab(tabName)

'Select Account Type'
applyAccountPage.selectAccountType(accountType)

'Verify First Screen'
commonPage.verifyElements(fistVerifyData, commonPage.lblContainDynamicPath(), GlobalVariable.G_timeoutMedium)

applyAccountPage.tapApplyNow()

commonPage.waitLoading()

'Choose type Mykad'
applyAccountPage.chooseIdType(idType)

'Generate and fill in Mykad number'
applyAccountPage.generateMykadNumber()

'Navigate to next Screen'
commonPage.tapNextBtn()

applyAccountPage.checkAndCreatePremierAccount()

if (applyAccountPage.checkSAScreenExist()) {
    applyAccountPage.verifyNewSAScreen(inputDrpData, option, btnText)
}

'Ekyc by pass'
applyAccountPage.byPassEkyc()

'Verify Step One'
commonPage.verifyElements(stepOneUIData, commonPage.lblDynamicPath(), GlobalVariable.G_timeoutMedium)

commonPage.inputDropdownMapDefault(drpstepOneData)

commonPage.setTextSimply(commonPage.txtSiblingGroup('Mobile Number'), StringUtils.generateMalaysiaMobileNumber(), GlobalVariable.G_timeoutMedium)

applyAccountPage.fillInformationMAEAccount(imputDataStepOne)

MobileDriver.scrollToEnd()

commonPage.delay(GlobalVariable.G_timeoutMiniShort)

MobileDriver.tap(commonPage.lblTextViewDynamic(option), GlobalVariable.G_timeoutMedium)

MobileDriver.tap(commonPage.lblTextViewDynamic(option), GlobalVariable.G_timeoutMedium)

commonPage.tapNextBtn()

commonPage.delay(GlobalVariable.G_timeoutMiniShort)

'Verify Step Two'
commonPage.verifyElements(stepTwoUIData, commonPage.lblDynamicPath(), GlobalVariable.G_timeoutMedium)

commonPage.inputDropdownMapSearch(drpSearchstepTwoData)

commonPage.inputDropdownMapDefault(drpstepTwoData)

commonPage.enterTextSiblingGroup('Employer Name', employerName, GlobalVariable.G_timeoutMedium)

commonPage.tapNextBtn()

commonPage.delay(GlobalVariable.G_timeoutMiniShort)

'Verify Step Three'
commonPage.verifyElements(stepThreeUIData, commonPage.lblDynamicPath(), GlobalVariable.G_timeoutMedium)

MobileDriver.tap(commonPage.lblDynamic(card), GlobalVariable.G_timeoutMedium)

commonPage.tapNextBtn()

commonPage.delay(GlobalVariable.G_timeoutMiniShort)

commonPage.swipeByW3CAction(5, 1000, 5, 100)

MobileDriver.tap(commonPage.imgNoDynamic(4), GlobalVariable.G_timeoutMedium)

commonPage.tapNextBtn()

'Verify Agree and Confirm'
applyAccountPage.verifyAgreeAndConfirm(agreeAndConfirmUIData, agreeRadio)

commonPage.delay(GlobalVariable.G_timeoutMiniShort)

'Verify Confirmation'
applyAccountPage.verifyPMAConfirmation(confirmUIData, drpEditBranchData)

commonPage.delay(GlobalVariable.G_timeoutMiniShort)

'S2u'
secure2uAuthorisationPage.authorize(s2uStatus)

'Verify successfully Screen'
applyAccountPage.verifySuccessPBA(successUIData)

'Create Maybank2u'
applyAccountPage.createMaybank2u(inputSecurityData, drpSecurityData)

'Verify success created'

commonPage.verifyElements(successCreateUIData, commonPage.lblDynamicPath(), GlobalVariable.G_timeoutMedium)

'Tap Done Button'
commonPage.tapDoneBtn()

'Back To Home'
homePage.tapHomeMenu()