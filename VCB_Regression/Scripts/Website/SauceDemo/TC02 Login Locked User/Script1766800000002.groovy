import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.vcb.common.WebUIKeyword
import com.vcb.m2u.internal.pages.base.WebBasePage
import com.vcb.m2u.internal.pages.base.WebSauceDemoPage

WebSauceDemoPage webSauceDemoPage = WebBasePage.getPage(WebSauceDemoPage)

try {
	String errorText = webSauceDemoPage.loginAndGetErrorMessage(username, password)
	WebUI.verifyMatch(errorText, matchLockText, true)
} catch (Exception ex) {
	WebUIKeyword.takeScreenshot()
	throw ex
}
