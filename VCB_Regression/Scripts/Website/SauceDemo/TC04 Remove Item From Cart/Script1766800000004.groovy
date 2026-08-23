import com.vcb.common.WebUIKeyword
import com.vcb.m2u.internal.pages.base.WebBasePage
import com.vcb.m2u.internal.pages.base.WebSauceDemoPage

WebSauceDemoPage webSauceDemoPage = WebBasePage.getPage(WebSauceDemoPage)

try {
	webSauceDemoPage.loginAsStandardUser()
	webSauceDemoPage.addBackpackToCart()
	webSauceDemoPage.goToCart()
	webSauceDemoPage.removeBackpackFromCart()
	webSauceDemoPage.verifyBackpackNotInCart()
} catch (Exception ex) {
	WebUIKeyword.takeScreenshot()
	throw ex
}
