import com.vcb.common.MobileDriver
import com.vcb.mobile.internal.pages.base.applypage.ApplyPersonalLoanPage as ApplyPersonalLoanPage
import com.vcb.mobile.internal.pages.base.BasePage
import com.vcb.mobile.internal.pages.base.OnboardingPage
import com.vcb.mobile.internal.pages.base.OnboardingPage as BasePage
import com.vcb.mobile.internal.pages.base.CommonPage as CommonPage
import com.vcb.mobile.internal.pages.base.OnboardingPage

ApplyPersonalLoanPage applyPersonalLoanPage = BasePage.getPage(ApplyPersonalLoanPage)
CommonPage commonPage = BasePage.getPage(CommonPage)
OnboardingPage onboardingPage = BasePage.getPage(OnboardingPage)

'Navigate To Login'
//CustomKeywords.'com.vcb.mobile.pages.Onboarding.navigateToLogin'()
//onboardingPage.navigateToNTBLogin()

'Navigate to Personal Loan page'
applyPersonalLoanPage.navigatePersonalLoanNTB('')
