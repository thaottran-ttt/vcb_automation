import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.vcb.common.WebUIKeyword
import com.vcb.m2u.internal.pages.base.WebBasePage
import com.vcb.m2u.internal.pages.base.WebSauceDemoPage

WebSauceDemoPage webSauceDemoPage = WebBasePage.getPage(WebSauceDemoPage)

try {
	webSauceDemoPage.login(username, password)
	WebUI.verifyMatch(WebUI.getUrl(), inventoryURL, true)
	WebUI.verifyElementText(WebSauceDemoPage.lblProductsTitle(), lblProductTitle)
} catch (Exception ex) {
	WebUIKeyword.takeScreenshot()
	throw ex
}
