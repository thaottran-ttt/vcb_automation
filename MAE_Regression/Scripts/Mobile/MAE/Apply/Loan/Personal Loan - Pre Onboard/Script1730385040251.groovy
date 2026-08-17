import com.maybank.common.MobileDriver
import com.maybank.mae.internal.pages.base.applypage.ApplyPersonalLoanPage as ApplyPersonalLoanPage
import com.maybank.mae.internal.pages.base.BasePage
import com.maybank.mae.internal.pages.base.OnboardingPage
import com.maybank.mae.internal.pages.base.OnboardingPage as BasePage
import com.maybank.mae.internal.pages.base.CommonPage as CommonPage
import com.maybank.mae.internal.pages.base.OnboardingPage

ApplyPersonalLoanPage applyPersonalLoanPage = BasePage.getPage(ApplyPersonalLoanPage)
CommonPage commonPage = BasePage.getPage(CommonPage)
OnboardingPage onboardingPage = BasePage.getPage(OnboardingPage)

'Navigate To Login'
//CustomKeywords.'com.maybank.mae.pages.Onboarding.navigateToLogin'()
//onboardingPage.navigateToNTBLogin()

'Navigate to Personal Loan page'
applyPersonalLoanPage.navigatePersonalLoanNTB('')
