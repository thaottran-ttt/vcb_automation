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
import com.vcb.mobile.internal.pages.base.BasePage as BasePage
import com.vcb.mobile.internal.pages.base.CommonPage
import com.vcb.mobile.internal.pages.base.ConfirmationPage
import com.vcb.mobile.internal.pages.base.EnterAmountPage
import com.vcb.mobile.internal.pages.base.HomePage
import com.vcb.mobile.internal.pages.base.PushNotificationPage
import com.vcb.mobile.internal.pages.base.Secure2uAuthorisationPage
import com.vcb.mobile.internal.pages.base.ShareReceiptPage
import com.vcb.mobile.internal.pages.base.StatusScreenPage
import com.vcb.mobile.internal.pages.base.transferpage.TransferPage
import com.vcb.utils.StringUtils

import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

WebUI.callTestCase(findTestCase('Mobile/VCB/Zakat/Common/TC Zakat Common'), [('username') : username, ('password') : password
	, ('idNumber') : idNumber, ('PAN') : PAN, ('accountName') : accountName, ('actionName') : actionName, ('tabName') : tabName
	, ('paymentName') : paymentName, ('inputZakat') : inputZakat, ('mobileNumber') : mobileNumber, ('amount') : amount
	, ('verifyConfirmData') : verifyConfirmData, ('verifyStatusScreenTitleData') : verifyStatusScreenTitleData, ('verifyStatusScreenDescriptionData') : verifyStatusScreenDescriptionData
	, ('status') : status, ('shareReceiptsData') : shareReceiptsData, ('favName') : favName, ('titlePN') : titlePN, ('product') : product
	, ('isRunFav') : isRunFav], FailureHandling.STOP_ON_FAILURE)
