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

import com.maybank.mae.internal.pages.base.BasePage
import com.maybank.mae.internal.pages.base.HomePage
import com.maybank.mae.internal.pages.base.CustomisePage

FailureHandling flowControl = FailureHandling.CONTINUE_ON_FAILURE

HomePage homePage = BasePage.getPage(HomePage)
CustomisePage customisePage = BasePage.getPage(CustomisePage)

WebUI.callTestCase(findTestCase('Mobile/MAE/Common/Login'), [('username') : username, ('password') : password, ('pinCode') : GlobalVariable.G_pinCode
        , ('yourName') : GlobalVariable.G_yourName, ('accountName') : accountName, ('flowControl') : com.maybank.common.FlowControlEnum.STOP
        , ('failStopTestSuite') : false], FailureHandling.STOP_ON_FAILURE)

'Verify the user is successfully logged in'
homePage.verifyProfileName(profileName, flowControl)

'Click on Logout'
homePage.clickLogout()

'swipe the screen'
homePage.verifyCustomiseButton()

'Tap Customise button and verify if able to tap on the Customise button and procced with next screen'
customisePage.clickCustomiseButton(flowControl)

'Enter Pin'
homePage.enterPinNo(pinCode)

'Ensure that the Customise screen appears successfully'
customisePage.verifyManageQucikActionsText(flowControl)
customisePage.verifyHoldAndDragDescription(flowControl)
customisePage.verifyCurrentFavoritesText(flowControl)

'Default item(deem) : Secure2u , ATM cashout, Tabung, Sama Sama Lokal'
customisePage.verifyDefaulItemsCustomiseScreen(flowControl)






