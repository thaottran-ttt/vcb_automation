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
import com.maybank.mae.internal.pages.base.BasePage
import com.maybank.mae.internal.pages.base.HomePage
import com.maybank.mae.internal.pages.base.transferpage.OverseasTransferPage
import com.maybank.mae.internal.pages.base.transferpage.TransferPage

import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

WebUI.callTestCase(findTestCase('Mobile/MAE/Common/Login Secure2u'), [('idNumber') : idNumber, ('PAN') : PAN, ('password') : password
	, ('username') : username, ('accountName') : accountName], FailureHandling.STOP_ON_FAILURE)

HomePage homePage = BasePage.getPage(HomePage)
TransferPage transferPage = BasePage.getPage(TransferPage)
OverseasTransferPage overseasTransferPage = BasePage.getPage(OverseasTransferPage)

homePage.selectQuickActions(tranferName)

transferPage.selectTab(transferTab)

transferPage.openOverseasTransferFlow()

'Search recipient nation'
overseasTransferPage.searchRecipientNation(recipientNation)

'Choose transfer account'
overseasTransferPage.chooseTransferAccount(transferAccount)

'Choose currency'
overseasTransferPage.chooseCurrency(currency)

'Set transfer amount'
overseasTransferPage.setTransferAmount(transferAmount)

'Set recipient mobile number'
overseasTransferPage.setRecipientMobileNumber(mobileNumber)
