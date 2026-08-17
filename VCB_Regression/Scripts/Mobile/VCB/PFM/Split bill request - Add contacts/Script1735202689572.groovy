import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase

import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.vcb.mobile.internal.pages.base.BasePage
import com.vcb.mobile.internal.pages.base.splitbill.SplitBillPage
import com.vcb.utils.ListUtils
import com.vcb.utils.MobileDriverUtils

import internal.GlobalVariable
import io.appium.java_client.AppiumDriver
import io.appium.java_client.InteractsWithApps

mobileNumberShareBill = ListUtils.parseObjectToList(mobileNumberShareBill)
SplitBillPage splitBillPage = BasePage.getPage(SplitBillPage)

AppiumDriver driver = MobileDriverFactory.getDriver();

'Add contacts'
//Mobile.startExistingApplication(samsungApp)
((InteractsWithApps) driver).activateApp(samsungApp)
splitBillPage.addContactsSamsungS24(mobileNumberShareBill)
if(CustomKeywords.'com.vcb.common.BaseKeyword.runFromTestCloud'()) {
	Mobile.startApplication(GlobalVariable.G_appIDTestCloud, false)
}else {
	((InteractsWithApps) driver).activateApp(GlobalVariable.G_appId);
}
'Set Driver if openApplication Again'
MobileDriverUtils.setDriverMobile()