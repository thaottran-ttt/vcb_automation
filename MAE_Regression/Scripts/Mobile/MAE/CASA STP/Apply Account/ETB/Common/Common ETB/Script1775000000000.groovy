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

import com.maybank.mae.internal.pages.base.applypage.ApplyAccountPage
import com.maybank.mae.internal.pages.base.applypage.ApplyCardPage
import com.maybank.mae.internal.pages.base.BasePage
import com.maybank.mae.internal.pages.base.CommonPage
import com.maybank.mae.internal.pages.base.ConfirmationPage
import com.maybank.mae.internal.pages.base.HomePage
import com.maybank.utils.ListUtils
import com.maybank.utils.MapUtils
import com.maybank.utils.StringUtils

HomePage homePage                                   = BasePage.getPage(HomePage)
ApplyAccountPage applyAccountPage                   = BasePage.getPage(ApplyAccountPage)
ApplyCardPage applyCardPage                         = BasePage.getPage(ApplyCardPage)
CommonPage commonPage                               = BasePage.getPage(CommonPage)
ConfirmationPage confirmationPage                   = BasePage.getPage(ConfirmationPage)

Map inputDrpData             = MapUtils.parseObjectToMap(inputDrpData)            // SA / Step-1 dropdown inputs
Map inputStepOneData         = MapUtils.parseObjectToMap(inputStepOneData)        // Step-1 text inputs (Kawanku, Saving-i, G1A, Premier1)
Map drpstepOneData           = MapUtils.parseObjectToMap(drpstepOneData)          // Step-1 dropdown inputs (PBA, PBAi)
Map drpstepTwoData           = MapUtils.parseObjectToMap(drpstepTwoData)          // Step-2 dropdown inputs (PMAi, Premier1, PBA, PBAi)
Map drpstepThreeData         = MapUtils.parseObjectToMap(drpstepThreeData)        // Step-3 dropdown inputs (branch / purpose)
Map personalDetails          = MapUtils.parseObjectToMap(personalDetails)         // Zest-i: residential info for Step-1
Map employmentDetails        = MapUtils.parseObjectToMap(employmentDetails)       // Zest-i: employment info for Step-2
Map branchMap                = MapUtils.parseObjectToMap(branchMap)               // Zest-i: branch selection for Step-3
Map incomeDetails            = MapUtils.parseObjectToMap(incomeDetails)           // Zest-i: additional income (high-risk only)
Map edittedAdditionalDetails = MapUtils.parseObjectToMap(edittedAdditionalDetails)// Zest-i: post-edit additional details

List fistVerifyData                   = ListUtils.parseObjectToList(fistVerifyData)
List questionList                     = ListUtils.parseObjectToList(questionList)                    
List stepOneUIData                    = ListUtils.parseObjectToList(stepOneUIData)
List stepTwoUIData                    = ListUtils.parseObjectToList(stepTwoUIData)
List stepThreeUIData                  = ListUtils.parseObjectToList(stepThreeUIData)
List agreeAndConfirmUIData            = ListUtils.parseObjectToList(agreeAndConfirmUIData)
List confirmUIData                    = ListUtils.parseObjectToList(confirmUIData)
List confirmUISecondData              = ListUtils.parseObjectToList(confirmUISecondData)
List statusScreenTitle                = ListUtils.parseObjectToList(statusScreenTitle)
List statusScreenDescription          = ListUtils.parseObjectToList(statusScreenDescription)
List creditCardApplicationStepTwoData = ListUtils.parseObjectToList(creditCardApplicationStepTwoData) // G1A only

WebUI.callTestCase(findTestCase('Mobile/MAE/CASA STP/Apply Account/ETB/Common/ETB Entry Point'),
    [('username')      : username,
     ('password')      : password,
     ('idNumber')      : idNumber,
     ('PAN')           : PAN,
     ('tabName')       : tabName,
     ('accountType')   : accountType,
     ('fistVerifyData'): fistVerifyData],
    FailureHandling.STOP_ON_FAILURE)

'Tap Apply Now'
applyAccountPage.tapApplyNow()
commonPage.waitLoading()

'Check if product already exists'
if (applyAccountPage.checkSavingAccountIExist()) {
    commonPage.delay(GlobalVariable.G_timeoutMiniShort)
    commonPage.tapBack()
    homePage.tapHomeMenu()
    return
}

'Suitability Assessment'
if (applyAccountPage.checkSAScreenExist()) {
    switch (accountType) {
        case ['Zest-i', 'Premier Mudharabah Account-i']:
            applyAccountPage.verifySAScreen(questionList, inputDrpData, btnText)
            break
        case ['Private Banking Account', 'Private Banking Account-i']:
            applyAccountPage.verifyNewSAScreen(inputDrpData, option, btnText)
            break
    }
}

// ─── Step One ─────────────────────────────────────────────────────
'Verify Step One'
commonPage.verifyElements(stepOneUIData, commonPage.lblDynamicPath(), GlobalVariable.G_timeoutMedium)

'Fill Step One'
switch (accountType) {
    case 'Zest-i':
        applyAccountPage.fillResidentialInfo(personalDetails)
        break
    case ['Private Banking Account', 'Private Banking Account-i']:
        commonPage.setTextSimply(commonPage.txtEditNo(), StringUtils.generateMalaysiaMobileNumber(), GlobalVariable.G_timeoutMedium)
        commonPage.inputDropdownMapDefault(drpstepOneData)
        break
    default:
        if (!inputStepOneData.isEmpty()) {
            commonPage.inputTextListGroup(inputStepOneData)
        }
        break
}

'Tap Next'
commonPage.tapNextBtn()
commonPage.delay(GlobalVariable.G_timeoutMiniShort)

// ─── Step Two ─────────────────────────────────────────────────────
'Verify Step Two'
commonPage.verifyElements(stepTwoUIData, commonPage.lblDynamicPath(), GlobalVariable.G_timeoutMedium)

'Fill Step Two'
if ('Zest-i' == accountType) {
    applyAccountPage.zestiNTBFillEmploymentDetail(employmentDetails)
} else if (!drpstepTwoData.isEmpty()) {
    commonPage.scrollToEnd()
    commonPage.inputDropdownMapDefault(drpstepTwoData)
}

'Tap Next'
commonPage.tapNextBtn()
commonPage.delay(GlobalVariable.G_timeoutMiniShort)

// ─── Step Three ─────────────────────────────────────────────────────
'Verify Step Three'
commonPage.verifyElements(stepThreeUIData, commonPage.lblDynamicPath(), GlobalVariable.G_timeoutMedium)

'Fill Step Three'
if ('Zest-i' == accountType) {
	applyAccountPage.fillBranchDetail(branchMap)
	
	if (isHighRisk) {
		applyAccountPage.fillAdditionalIncomeDetails(incomeDetails)
		commonPage.tapNextBtn()
	}
} else {
	commonPage.inputDropdownMapDefault(drpstepThreeData)

	'Tap Next'
	commonPage.tapNextBtn()
	commonPage.delay(GlobalVariable.G_timeoutMiniShort)
}

// ─── Debit Card Flow (G1A) ─────────────────────────────────────────────────────
'Extra Step: Debit Card — Global Access Account-i only'
// Pass a non-empty creditCardApplicationStepTwoData list to activate this block.
if (!creditCardApplicationStepTwoData.isEmpty()) {
    'Select Debit Card'
    applyCardPage.verifyCreditCardApplicationStepTwo(creditCardApplicationStepTwoData, cardName)
    commonPage.tapNextBtn()

    'Debit Card Delivery Details — Accept T&C'
    applyAccountPage.agreeAndAcceptTnC(agreeButton)
}

// ─── Agree and Confirm ─────────────────────────────────────────────────────
'Agree and Confirm'
applyAccountPage.verifyAgreeAndConfirm(agreeAndConfirmUIData, agreeRadio)
commonPage.delay(GlobalVariable.G_timeoutMiniShort)

// ─── Confirmation Page ────────────────────────────────────────────────────
'Confirmation'
switch (accountType) {
    case 'Zest-i':
        applyAccountPage.verifyZestiConfirmation([:], personalDetails, employmentDetails, branchMap, [:])
        applyAccountPage.verifyEditZestiETBConfirmation([:], [:], edittedAdditionalDetails, [:])
        commonPage.tap(commonPage.lblDynamic('Confirm'), GlobalVariable.G_timeoutLong)
		'Select another account if exist else select the default one'
		if(accountName != '' && accountName != "''") {
			confirmationPage.chooseAnotherAccount(accountName)
		}
        commonPage.tap(commonPage.lblDynamic(confirmNextPage), GlobalVariable.G_timeoutMedium)
        break
    case ['Private Banking Account-i']:
        // Two consecutive confirmation screens — without account first, then with account
        applyAccountPage.verifyPMAConfirmation(confirmUIData)
        commonPage.delay(GlobalVariable.G_timeoutMiniShort)
        confirmationPage.verifyConfirmation(confirmUISecondData, confirmNextPage)
        confirmationPage.verifyConfirmation(confirmUISecondData, confirmNextPage, accountName)
        break
    case ['Private Banking Account']:
        applyAccountPage.verifyPMAConfirmation(confirmUIData)
        commonPage.delay(GlobalVariable.G_timeoutMiniShort)
        confirmationPage.verifyConfirmation(confirmUISecondData, confirmNextPage, accountName)
        break
    case ['Saving-i', 'Premier 1 Account']:
        applyAccountPage.verifyPMAConfirmation(confirmUIData)
        commonPage.delay(GlobalVariable.G_timeoutMiniShort)
        confirmationPage.verifyConfirmation(confirmUISecondData, confirmNextPage)
        break
    default:
        // Kawanku, PMAi, G1A — tap the next label
        applyAccountPage.verifyPMAConfirmation(confirmUIData)
        commonPage.delay(GlobalVariable.G_timeoutMiniShort)
        commonPage.tap(commonPage.lblDynamic(confirmNextPage), GlobalVariable.G_timeoutMedium)
        break
}

'S2U Authorization + Status Screen'
WebUI.callTestCase(findTestCase('Mobile/MAE/CASA STP/Apply Account/ETB/Common/ETB Authorization'),
    [('s2uStatus')               : s2uStatus,
     ('statusScreenTitle')       : statusScreenTitle,
     ('statusScreenDescription') : statusScreenDescription],
    FailureHandling.STOP_ON_FAILURE)
