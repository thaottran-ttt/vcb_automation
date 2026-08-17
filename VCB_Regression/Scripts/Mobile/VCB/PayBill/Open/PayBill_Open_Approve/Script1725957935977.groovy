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
import com.vcb.assertions.BaseAssert as BaseAssert
import com.vcb.mobile.internal.pages.base.BasePage as BasePage
import com.vcb.mobile.internal.pages.base.CommonPage as CommonPage
import com.vcb.mobile.internal.pages.base.ConfirmationPage as ConfirmationPage
import com.vcb.mobile.internal.pages.base.HomePage as HomePage
import com.vcb.mobile.internal.pages.base.paybill.PayBillPage as PayBillPage
import com.vcb.mobile.internal.pages.base.PushNotificationPage as PushNotificationPage
import com.vcb.mobile.internal.pages.base.Secure2uAuthorisationPage as Secure2uAuthorisationPage
import com.vcb.mobile.internal.pages.base.transferpage.TransferPage as TransferPage
import com.vcb.utils.StringUtils as StringUtils
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

CommonPage commonPage = BasePage.getPage(CommonPage)

WebUI.callTestCase(findTestCase('Mobile/VCB/PayBill/Common/PayBill'), [('username') : username, ('password') : password, ('idNumber') : idNumber
        , ('PAN') : PAN, ('accountName') : accountName, ('tranferName') : tranferName, ('payBillName') : payBillName, ('billerID') : billerID
        , ('amount') : amount, ('confirmationData') : confirmationData, ('textNextPageConfirmation') : textNextPageConfirmation
        , ('status') : status, ('favName') : favName, ('statusScreenTitle') : statusScreenApproveTitle, ('statusScreenDescription') : statusScreenApproveDescription
        , ('verifyShareReceiptData') : verifyShareReceiptData, ('pushNotificationTitle') : pushNotificationTitle, ('pushNotificationDescription') : pushNotificationDescription], 
    FailureHandling.STOP_ON_FAILURE)

'Verify Reprint'
WebUI.callTestCase(findTestCase('Mobile/VCB/Common/Reprint Receipt'), [('product') : reprintReceiptProduct], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('Mobile/VCB/Common/Verify Favourite Exists'), [('username') : username, ('password') : password
        , ('functionName') : tranferName, ('moduleName') : transferTab, ('favouriteName') : favName], BasePage.defaultFailureHandling)

