import com.vcb.common.WebUIKeyword
import com.vcb.m2u.internal.pages.base.WebBasePage
import com.vcb.m2u.internal.pages.base.WebSauceDemoPage

WebSauceDemoPage webSauceDemoPage = WebBasePage.getPage(WebSauceDemoPage)

try {
	webSauceDemoPage.loginAsStandardUser()
	webSauceDemoPage.addBackpackToCart()
	webSauceDemoPage.verifyCartBadge(number)
	webSauceDemoPage.goToCart()
	webSauceDemoPage.verifyBackpackInCart()
} catch (Exception ex) {
	WebUIKeyword.takeScreenshot()
	throw ex
}
