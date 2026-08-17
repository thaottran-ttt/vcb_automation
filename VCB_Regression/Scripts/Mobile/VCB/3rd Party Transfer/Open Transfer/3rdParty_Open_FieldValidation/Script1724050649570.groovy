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
import com.vcb.mobile.internal.pages.base.accountpage.AccountDetailPage
import com.vcb.mobile.internal.pages.base.BasePage as BasePage
import com.vcb.mobile.internal.pages.base.CommonPage as CommonPage
import com.vcb.mobile.internal.pages.base.HomePage as HomePage
import com.vcb.mobile.internal.pages.base.transferpage.TransferPage as TransferPage
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

WebUI.callTestCase(findTestCase('Mobile/VCB/Common/Login Secure2u'), [('idNumber') : idNumber, ('PAN') : PAN, ('password') : password
        , ('username') : username, ('accountName') : accountName], FailureHandling.STOP_ON_FAILURE)

HomePage homePage = HomePage.getPage(HomePage)

TransferPage transferPage = BasePage.getPage(TransferPage)

CommonPage commonPage = BasePage.getPage(CommonPage)

AccountDetailPage accountDetailPage = BasePage.getPage(AccountDetailPage)

'Access to account detail'
accountDetailPage.selectMenu(tranferName)

'Select Others Tab'
transferPage.selectTab(transferTab, true)

'Select Banks -> select VCB'
transferPage.openNewTransferOthers('Banks', 'VCB')

'Verify Account No'
transferPage.verifySetAccountNumber()

'Account Number'
transferPage.setAccountNumber(accountNo)

'Enter Amount'
transferPage.verifyFieldValidationEnterAmount()

'Verify Recipient Reference'
transferPage.verifyRecipientReference()

'Recipient Reference'
transferPage.recipientReference(recipientReference)

'Validation'
transferPage.verifyFieldValidationConfirmation(editAmount, recipientReference, editRecipientReference, editOption)
