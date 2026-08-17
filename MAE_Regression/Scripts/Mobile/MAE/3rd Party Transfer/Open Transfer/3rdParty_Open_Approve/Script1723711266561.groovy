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
import com.maybank.mae.internal.pages.base.BasePage as BasePage
import com.maybank.mae.internal.pages.base.CommonPage as CommonPage
import com.maybank.mae.internal.pages.base.HomePage as HomePage
import com.maybank.mae.internal.pages.base.transferpage.TransferPage
import com.maybank.utils.StringUtils

import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

WebUI.callTestCase(findTestCase('Mobile/MAE/Common/Login Secure2u'), [('idNumber') : idNumber, ('PAN') : PAN, ('password') : password
        , ('username') : username, ('accountName') : accountName], FailureHandling.STOP_ON_FAILURE)

HomePage homePage = HomePage.getPage(HomePage)
TransferPage transferPage = BasePage.getPage(TransferPage)
CommonPage commonPage = BasePage.getPage(CommonPage)

'Get Balance'
String balance = homePage.getBalance(true)

def balanceBigInt = StringUtils.convertBalanceAmountNumber(balance)
def amount = amount

'Select Transfer Module at QuickActions'
homePage.selectQuickActions(tranferName)

'Select Others Tab'
transferPage.selectTab(transferTab, true)

'Select Banks -> select MAYBANK'
transferPage.openNewTransferOthers('Banks', 'MAYBANK')

'Account Number'
transferPage.setAccountNumber(accountNo)

'Enter Amount'
commonPage.enterNO(amount)

'Recipient Reference'
transferPage.recipientReference(recipientReference)

'Confirmation'
transferPage.confirm()

'Authorisation'
transferPage.authorizeS2UV5BioPin(status)

'Status screen'
String RefID = transferPage.statusScreen(status, true, false, true)
String[] approvalStatus =  RefID.split(",");
String statusApproval = '';
if(approvalStatus.length > 1) {
	RefID = approvalStatus[0];
	statusApproval = approvalStatus[1];
}
Mobile.comment(RefID)
Mobile.comment(amount)

'Verify notification'
transferPage.verifyTransferNotificationMessage(accountNo, RefID, Double.parseDouble(amount)/100)

'Check amount'
String newBalance = homePage.getBalance(true)
def newBalanceBigInt = StringUtils.convertBalanceAmountNumber(newBalance)

if(statusApproval.contains('accepted')) {
	amount = 0
}
CustomKeywords.'com.maybank.assertions.BaseAssert.equals'((balanceBigInt -  newBalanceBigInt).toString(), amount.toString(), 'Check Amount')

'Verify M2U History'
WebUI.callTestCase(findTestCase('Mobile/MAE/Common/M2U History'), [('m2uHistoryProduct') : m2uHistoryProduct, m2uHistoryAmount: m2uHistoryAmount, password: password], BasePage.defaultFailureHandling)

WebUI.callTestCase(findTestCase('Mobile/MAE/Common/Verify Favourite Exists'), [('username') : username, ('password') : password
	, ('functionName') : tranferName, ('moduleName') : transferTab, ('favouriteName') : favName], BasePage.defaultFailureHandling)
