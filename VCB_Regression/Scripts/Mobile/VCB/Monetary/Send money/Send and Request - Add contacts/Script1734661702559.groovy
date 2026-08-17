import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase

import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.vcb.common.MobileDriver
import com.vcb.mobile.internal.pages.base.BasePage
import com.vcb.mobile.internal.pages.base.splitbill.SplitBillPage
import com.vcb.utils.ListUtils
import com.vcb.utils.MobileDriverUtils

import internal.GlobalVariable as GlobalVariable

mobileNumberShareBill = ListUtils.parseObjectToList(mobileNumberShareBill)
SplitBillPage splitBillPage = BasePage.getPage(SplitBillPage)

'Add contacts'
Mobile.startExistingApplication(samsungApp)
splitBillPage.addContactsSamsungS22(mobileNumberShareBill)
if(CustomKeywords.'com.vcb.common.BaseKeyword.runFromTestCloud'()) {
	Mobile.startApplication(GlobalVariable.G_appIDTestCloud, false)
}else {
	MobileDriver.driver.activateApp(GlobalVariable.G_appId)
}
'Set Driver if openApplication Again'
MobileDriverUtils.setDriverMobile()


WebUI.callTestCase(findTestCase('Mobile/VCB/Common/Login Secure2u'), [('idNumber') : idNumber, ('PAN') : PAN, ('password') : GlobalVariable.G_password
	, ('username') : username, ('accountName') : '', ('flowControl') : com.vcb.common.FlowControlEnum.STOP
	, ('failStopTestSuite') : false], FailureHandling.STOP_ON_FAILURE)