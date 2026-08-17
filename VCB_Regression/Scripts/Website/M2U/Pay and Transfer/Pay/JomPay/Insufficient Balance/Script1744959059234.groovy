import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.vcb.m2u.internal.pages.base.WebBasePage as WebBasePage
import com.vcb.m2u.internal.pages.base.WebCommonPage as WCP
import com.vcb.m2u.internal.pages.base.WebHomePage as WebHomePage
import com.vcb.m2u.internal.pages.base.WebSecure2uAuthorisationPage as WebSecure2uAuthorisationPage
import com.vcb.m2u.internal.pages.base.WebStatusScreenPage as WebStatusScreenPage
import com.vcb.m2u.internal.pages.base.paytransfer.WebPayPageJomPAY as WebPayPageJomPAY
import com.vcb.utils.MapUtils as MapUtils
import internal.GlobalVariable as GlobalVariable

WCP webCommonPage = WebUI.callTestCase(findTestCase('Website/M2U/Pay and Transfer/Pay/JomPay/Navigate to Pay'), [('username') : username
        , ('password') : password, ('idNumber') : idNumber, ('PAN') : PAN, ('navItem') : navItem, ('tabName') : tabName, ('payToData') : payToData], 
    FailureHandling.STOP_ON_FAILURE)

WebStatusScreenPage webStatusScreenPage = WebBasePage.getPage(WebStatusScreenPage)

WebPayPageJomPAY payPage = WebBasePage.getPage(WebPayPageJomPAY)

WebSecure2uAuthorisationPage webSecure2uAuthorisationPage = WebBasePage.getPage(WebSecure2uAuthorisationPage)

WebHomePage webHomePage = WebBasePage.getPage(WebHomePage)

webSecure2uAuthorisationPage.setID_NUMBER(idNumber)

Map inputPayToData = MapUtils.parseObjectToMap(inputPayToData)

'select account'
webHomePage.selectAccount(accNum)

// Get the original balance
Double originBalance = webHomePage.getBalance()

// Add 100 to the origin balance and cast it to a String
String newBalance = (originBalance + 100).toString()

inputPayToData.put("Pay Amount", "999999")

Mobile.delay(GlobalVariable.G_timeoutMiniShort)

'Select Type JomPay'
webCommonPage.inputDropdownMap(payToData)

'Enter valid biller code'
payPage.enterBillerCode(billerCode)

'Click Terms radio button'
WebUI.click(payPage.imgPayToModalTermEmpty)

'Enter valid fields'
payPage.enterPayToData(inputPayToData)

'Press Pay'
WebUI.click(payPage.btnPayToModalPay)

'S2u'
webSecure2uAuthorisationPage.authorize(status)

'Verify Status Screen'
webStatusScreenPage.verifyStatusScreen(verifyStatusScreenData)