import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.maybank.mae.internal.pages.base.applypage.ApplyPage as ApplyPage
import com.maybank.mae.internal.pages.base.applypage.ApplyPageInsurance as ApplyPageInsurance
import com.maybank.mae.internal.pages.base.BasePage as BasePage
import com.maybank.mae.internal.pages.base.CommonPage as CommonPage
import com.maybank.mae.internal.pages.base.HomePage as HomePage
import com.maybank.mae.internal.pages.base.Secure2uAuthorisationPage as Secure2uAuthorisationPage
import com.maybank.utils.ListUtils as ListUtils
import internal.GlobalVariable as GlobalVariable

tripCare360StepOneData = ListUtils.parseObjectToList(tripCare360StepOneData)

tripCare360StepTwoData = ListUtils.parseObjectToList(tripCare360StepTwoData)

tripCare360StepThreeData = ListUtils.parseObjectToList(tripCare360StepThreeData)

tripCare360StepPersonalDetail = ListUtils.parseObjectToList(tripCare360StepPersonalDetail)

tripCare360StepPaymentDetail = ListUtils.parseObjectToList(tripCare360StepPaymentDetail)

HomePage homePage = BasePage.getPage(HomePage)

ApplyPage applyPage = BasePage.getPage(ApplyPage)

ApplyPageInsurance applyPageInsurance = BasePage.getPage(ApplyPageInsurance)

CommonPage commonPage = BasePage.getPage(CommonPage)

Secure2uAuthorisationPage secure2uAuthorisationPage = BasePage.getPage(Secure2uAuthorisationPage)

WebUI.callTestCase(findTestCase('Mobile/MAE/Common/Login Secure2u'), [('idNumber') : idNumber, ('PAN') : PAN, ('password') : password
        , ('username') : username, ('accountName') : '', ('flowControl') : com.maybank.common.FlowControlEnum.STOP, ('failStopTestSuite') : false
        , ('yourName') : 'Test'], FailureHandling.STOP_ON_FAILURE)

'Tap Apply Button'
homePage.tapApply()

'Select INSURANCE Tab'
applyPage.selectTab(tabName)

'Tap Insurance Type'
applyPageInsurance.selectInsuranceType(insuranceType)
'Verify TripCare 360 Step One Screen'
applyPageInsurance.verifyTripCare360StepOne(tripCare360StepOneData)
commonPage.delay(GlobalVariable.G_timeoutMedium)

'Verify TripCare 360 Step Two Screen'
applyPageInsurance.verifyTripCare360StepTwo(tripCare360StepTwoData, tripType, country, travelWith)

'Verify TripCare 360 Step Choose Plan'
applyPageInsurance.verifyTripCare360ChoosePlan(tripCare360StepThreeData)

'Verify TripCare 360 Step Personal Detail'
applyPageInsurance.verifyTripCare360PersonalDetail(tripCare360StepPersonalDetail, nricNumber, fullName, ethnicity, email, 
    mobiNumber, buildingName, area, postcode)

'Verify TripCare 360 Step Payment Detail'
applyPageInsurance.verifyTripCare360PaymentDetail(tripCare360StepPaymentDetail)

'Back to Home'
commonPage.backToHome()
homePage.tapHomeMenu()