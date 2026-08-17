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
import com.vcb.common.BaseKeyword as BaseKeyword
import com.vcb.common.WebUIKeyword as WebUIKeyword
import com.vcb.m2u.internal.pages.base.WebBasePage as WebBasePage
import com.vcb.m2u.internal.pages.base.WebCommonPage as WebCommonPage
import com.vcb.m2u.internal.pages.base.WebHomePage as WebHomePage
import com.vcb.m2u.internal.pages.base.WebSecure2uAuthorisationPage as WebSecure2uAuthorisationPage
import com.vcb.m2u.internal.pages.base.WebStatusScreenPage as WebStatusScreenPage
import com.vcb.m2u.internal.pages.base.paytransfer.WebReloadPage as WebReloadPage
import com.vcb.mobile.internal.pages.base.Secure2uAuthorisationPage as Secure2uAuthorisationPage
import com.vcb.utils.ListUtils as ListUtils
import com.vcb.utils.MapUtils as MapUtils
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

WebUI.callTestCase(findTestCase('Website/M2U/Pay and Transfer/Reload/Common/Reload TAC'), [('username') : username, ('password') : password
        , ('idNumber') : idNumber, ('PAN') : PAN, ('navItem') : navItem, ('tabName') : tabName, ('fromToData') : fromToData
        , ('otherBank') : otherBank, ('inputTransferData') : inputTransferData, ('inputDropdownTransferData') : inputDropdownTransferData
        , ('statusScreenData') : statusScreenData, ('addToFavouriteData') : addToFavouriteData, ('validateFavouriteTransfer') : validateFavouriteTransfer
        , ('validateTACType') : validateTACType
        , ('errorTACMsg') : errorTACMsg, ('s2uType') : s2uType, ('status') : status, ('validateAddFavouriteTransfer') : validateAddFavouriteTransfer
        , ('toastMessageAfterAdd') : toastMessageAfterAdd, ('isRunWebsiteAndMobile') : isRunWebsiteAndMobile, ('isInternational') : isInternational], 
    FailureHandling.STOP_ON_FAILURE)
