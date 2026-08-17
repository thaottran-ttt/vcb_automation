import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.vcb.common.WebUIKeyword

'Browser is already opened and navigated to G_url (https://acme-test.uipath.com/login under the sanity-web profile) by TestListener @BeforeTestCase'

try {
	'Enter dummy credentials, just to exercise the framework end to end'
	WebUIKeyword.setText(findTestObject('Object Repository/Website/Sanity/Login/txtEmail'), email)
	WebUIKeyword.setText(findTestObject('Object Repository/Website/Sanity/Login/txtPassword'), password)

	'Submit the login form'
	WebUIKeyword.click(findTestObject('Object Repository/Website/Sanity/Login/btnLogin'))

	'Log whatever error the page shows, for reference only, no strict assertion'
	String errorMessage = WebUIKeyword.getText(findTestObject('Object Repository/Website/Sanity/Login/lblErrorMessage'))
	WebUI.comment("Login page responded with: ${errorMessage}")
} catch (Exception ex) {
	'Something in the framework/page interaction failed - capture a screenshot for evidence, then rethrow'
	WebUIKeyword.takeScreenshot()
	throw ex
}
