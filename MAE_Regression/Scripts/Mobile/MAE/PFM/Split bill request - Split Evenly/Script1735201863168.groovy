import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.maybank.mae.internal.pages.base.BasePage as BasePage
import com.maybank.mae.internal.pages.base.CommonPage as CommonPage
import com.maybank.mae.internal.pages.base.HomePage as HomePage
import com.maybank.mae.internal.pages.base.pfmpage.PFMPage as PFMPage
import com.maybank.mae.internal.pages.base.zakat.ZakatPage as ZakatPage
import com.maybank.mae.internal.pages.base.splitbill.SplitBillPage as SplitBillPage
import com.maybank.utils.ListUtils as ListUtils
import com.maybank.utils.MapUtils as MapUtils
import internal.GlobalVariable as GlobalVariable
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

HomePage homePage = BasePage.getPage(HomePage)

CommonPage commonPage = BasePage.getPage(CommonPage)

PFMPage pfmPage = BasePage.getPage(PFMPage)

ZakatPage zakatPage = BasePage.getPage(ZakatPage)

SplitBillPage splitBillPage = BasePage.getPage(SplitBillPage)

mobileNumberShareBill = ListUtils.parseObjectToList(mobileNumberShareBill)

Map category = MapUtils.parseObjectToMap(category)

zakatPage.restartApp()

WebUI.callTestCase(findTestCase('Mobile/MAE/Common/Login'), [('username') : username, ('password') : password, ('pinCode') : GlobalVariable.G_pinCode
        , ('yourName') : GlobalVariable.G_yourName, ('accountName') : '', ('flowControl') : com.maybank.common.FlowControlEnum.STOP
        , ('failStopTestSuite') : false, ('skipAccountLoginCheck') : false], FailureHandling.STOP_ON_FAILURE)

'Tap Expenses'
homePage.tapMenu(Expenses)

commonPage.waitLoading()

'Select Category'
pfmPage.selectCategoryToSplitBill(category, true)

'Tap to split bill '
pfmPage.splitBill(type, billName, note, amount, mobileNumberShareBill)

commonPage.waitLoading()

'Verify Status Screen'
splitBillPage.verifyStatusScreen(statusScreenTitle)

'Handle after create Bill'
splitBillPage.handleBill(billName)

commonPage.backToHome()

