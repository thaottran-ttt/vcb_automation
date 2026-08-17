import com.vcb.mobile.internal.pages.base.BasePage as BasePage
import com.vcb.mobile.internal.pages.base.HomePage as HomePage

HomePage homePage = BasePage.getPage(HomePage)

'Navigate To Login'
CustomKeywords.'com.vcb.mobile.pages.Onboarding.navigateToLogin'()

'Verify btn Scan exist'
homePage.verifyBtnScanExist()