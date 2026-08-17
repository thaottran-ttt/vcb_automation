import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase

import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.vcb.mobile.internal.pages.base.BasePage
import com.vcb.mobile.internal.pages.base.CommonPage
import com.vcb.mobile.internal.pages.base.HomePage
import com.vcb.mobile.internal.pages.base.pfmpage.PFMPage
import com.vcb.utils.MapUtils

import internal.GlobalVariable as GlobalVariable

HomePage homePage = BasePage.getPage(HomePage)
CommonPage commonPage = BasePage.getPage(CommonPage)
PFMPage pfmPage = BasePage.getPage(PFMPage)

Map category = MapUtils.parseObjectToMap(category)
Map categoryMap = MapUtils.parseObjectToMap(categoryMap)
Map categoryFixMap = MapUtils.parseObjectToMap(categoryFixMap)

WebUI.callTestCase(findTestCase('Mobile/VCB/Common/Login'), [('username') : username, ('password') : password, ('pinCode') : GlobalVariable.G_pinCode
        , ('yourName') : GlobalVariable.G_yourName, ('accountName') : accountName, ('flowControl') : com.vcb.common.FlowControlEnum.STOP
        , ('failStopTestSuite') : false], FailureHandling.STOP_ON_FAILURE)

'Tap Expenses'
homePage.tapMenu(Expenses)
commonPage.waitLoading()

'Navigate To Manage Category'
pfmPage.navigateToManageCategory(manageCategory)

'Select Category'
pfmPage.selectCategoryToEdit(category)

'Add New Category'
pfmPage.fillCategoryInfor(categoryMap)

'Edit Category'
pfmPage.editCategory(categoryMap)

'Add New Category'
pfmPage.fillCategoryInfor(categoryFixMap)

'Verify message'
commonPage.verifyTextVisible(editMessage, GlobalVariable.G_timeoutMedium, FailureHandling.STOP_ON_FAILURE)

'Choose Created Category'
pfmPage.editCategory(categoryFixMap)

'Delete Category'
pfmPage.deleteCategory()

'Verify message'
commonPage.verifyTextVisible(deleteMessage, GlobalVariable.G_timeoutMedium, FailureHandling.STOP_ON_FAILURE)
