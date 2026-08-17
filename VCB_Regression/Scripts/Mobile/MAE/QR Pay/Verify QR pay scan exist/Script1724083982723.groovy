import com.maybank.mae.internal.pages.base.BasePage as BasePage
import com.maybank.mae.internal.pages.base.HomePage as HomePage

HomePage homePage = BasePage.getPage(HomePage)

'Navigate To Login'
CustomKeywords.'com.maybank.mae.pages.Onboarding.navigateToLogin'()

'Verify btn Scan exist'
homePage.verifyBtnScanExist()