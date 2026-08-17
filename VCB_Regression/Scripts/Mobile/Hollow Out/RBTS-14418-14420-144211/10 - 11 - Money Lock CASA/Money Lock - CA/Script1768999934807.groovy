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
import com.vcb.common.MobileDriver as MobileDriver
import com.vcb.mobile.internal.pages.base.BasePage as BasePage
import com.vcb.mobile.internal.pages.base.CommonPage as CommonPage
import com.vcb.mobile.internal.pages.base.ConfirmationPage as ConfirmationPage
import com.vcb.mobile.internal.pages.base.HomePage as HomePage
import com.vcb.mobile.internal.pages.base.Secure2uAuthorisationPage as Secure2uAuthorisationPage
import com.vcb.mobile.internal.pages.base.StatusScreenPage as StatusScreenPage
import com.vcb.mobile.internal.pages.base.accountpage.AccountDetailPage as AccountDetailPage
import com.vcb.mobile.internal.pages.base.moneylock.MoneyLock as MoneyLock
import com.vcb.utils.ListUtils as ListUtils
import com.vcb.utils.StringUtils as StringUtils
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

WebUI.callTestCase(findTestCase('Mobile/Hollow Out/RBTS-14418-14420-144211/10 - 11 - Money Lock CASA/Common/Money Lock - Common'), 
    [('username') : username, ('password') : password, ('idNumber') : idNumber, ('PAN') : PAN, ('accountName') : accountName
        , ('functionName') : functionName, ('uiIntroductionScreen') : uiIntroductionScreen, ('minAmount') : minAmount, ('errorMsg') : errorMsg
        , ('amount') : amount, ('uiEnterAnountToLockScreen') : uiEnterAnountToLockScreen, ('uiIncreaseLockedAmount') : uiIncreaseLockedAmount
        , ('uiPopupMoneyLock') : uiConfirmationScreen, ('uiConfirmationScreen') : uiConfirmationScreen, ('status') : status
        , ('statusScreenData') : statusScreenData, ('increaseAmountStatusScreenData') : increaseAmountStatusScreenData, ('m2uHistoryProduct') : m2uHistoryProduct
        , ('m2uHistoryAmount') : m2uHistoryAmount], FailureHandling.STOP_ON_FAILURE)
