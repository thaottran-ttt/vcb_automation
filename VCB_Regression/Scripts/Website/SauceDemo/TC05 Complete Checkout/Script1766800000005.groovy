import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.vcb.common.WebUIKeyword
import com.vcb.m2u.internal.pages.base.WebBasePage
import com.vcb.m2u.internal.pages.base.WebSauceDemoPage

WebSauceDemoPage webSauceDemoPage = WebBasePage.getPage(WebSauceDemoPage)

try {
	webSauceDemoPage.loginAsStandardUser()
	webSauceDemoPage.addBackpackToCart()
	webSauceDemoPage.addBikeLightToCart()
	webSauceDemoPage.goToCart()
	webSauceDemoPage.completeCheckout(firstname, lastname, portalCost)
	WebUI.verifyElementText(WebSauceDemoPage.lblCompleteHeader(), lblCompleteHeaderTxt)
} catch (Exception ex) {
	WebUIKeyword.takeScreenshot()
	throw ex
}
