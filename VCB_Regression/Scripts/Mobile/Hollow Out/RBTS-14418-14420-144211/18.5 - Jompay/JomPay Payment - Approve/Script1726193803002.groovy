import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.vcb.common.MobileDriver as MobileDriver
import com.vcb.mobile.internal.pages.base.accountpage.AccountDetailPage as AccountDetailPage
import com.vcb.mobile.internal.pages.base.accountpage.AccountsPage as AccountsPage
import com.vcb.mobile.internal.pages.base.BasePage as BasePage
import com.vcb.mobile.internal.pages.base.CommonPage as CommonPage
import com.vcb.mobile.internal.pages.base.HomePage as HomePage
import com.vcb.mobile.internal.pages.base.paybill.JomPayPage as JomPayPage
import com.vcb.utils.StringUtils as StringUtils
import com.vcb.mobile.internal.pages.base.Secure2uAuthorisationPage as Secure2uAuthorisationPage
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import internal.GlobalVariable as GlobalVariable

WebUI.callTestCase(findTestCase('Mobile/Hollow Out/RBTS-14418-14420-144211/18.5 - Jompay/Common/JomPay Payment'), [('TC_name') : '', ('userName') : username
        , ('password') : password, ('idNumber') : idNumber, ('PAN') : PAN, ('status') : status, ('accountName') : accountName
        , ('billerCode') : billerCode, ('reference1') : reference1, ('reference2') : reference2, ('amount') : amount, ('isFuture') : isFuture
        , ('favoriteName') : favoriteName, ('runFavorite') : runFavorite, ('tranferName') : tranferName, ('transferTab') : transferTab
        , ('m2uHistoryProduct') : 'JomPAY'], FailureHandling.STOP_ON_FAILURE)

