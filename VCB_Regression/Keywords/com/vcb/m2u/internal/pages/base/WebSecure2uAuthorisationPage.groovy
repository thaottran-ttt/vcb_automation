package com.vcb.m2u.internal.pages.base

import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import java.util.regex.Matcher
import java.util.regex.Pattern

import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.vcb.common.MobileDriver
import com.vcb.common.WebUIKeyword
import com.vcb.mobile.internal.pages.base.BasePage
import com.vcb.mobile.internal.pages.base.Secure2uAuthorisationPage
import com.vcb.mobile.internal.pages.base.Secure2uAuthorisationPage.AuthorisationType
import com.vcb.m2u.internal.pages.base.rsapage.WebRsaPage

import internal.GlobalVariable

public class WebSecure2uAuthorisationPage extends WebBasePage {

	WebCommonPage webCommonPage = getPage(WebCommonPage)
	Secure2uAuthorisationPage secure2uAuthorisationPage = null
	WebRsaPage webRsaPage = getPage(WebRsaPage)

	static final String REQUEST = 'REQUEST'
	static final String DONE = 'Done'
	static final String CONFIRM_S2U_SECTION_CLASS = 'Confirm---selectedItem'
	static final String ONE_TIME_PASSWORD_CLASS = 'OneTimePassword---pair-input'
	static final String SECURE_VERIFICATION = 'Secure Verification'
	static final String TAC = 'TAC'
	static final String TAC_API= 'TAC_API'
	static final String INVALID_TAC_NO = '666666'
	static final String INVALID_TAC = "Invalid TAC"
	static final String BLANK_TAC = "Blank TAC"
	static final String TAC_SENT = "TAC has been sent."


	static final String S2U_APPROVAL_MESSAGE = 'Tap the notification from the VCB app to approve the transaction.'
	static final String S2U_APPROVAL_MESSAGE_V2 = 'Tap on the notification on your smartphone to approve the transaction.'
	String ID_NUMBER = ""
	public String getIdNumber() {
		return ID_NUMBER;
	}

	public void setIdNumber(String idNumber) {
		ID_NUMBER = idNumber;
	}

	/**
	 * Handles the authorization of a request based on the provided parameters.
	 * 
	 * If `GlobalVariable.G_isTacM2U` is enabled, authentication is performed via TAC API (WEB only).
	 * Otherwise, Secure2U authorization is initialized via the mobile app.
	 *
	 * @param status       the status indicating the type or condition of authorization
	 * @param requestButton (optional) the label of the button to trigger the authorization action,
	 *                      defaults to `REQUEST`
	 * @param s2uType      (optional) the type of Secure2U authentication, if applicable
	 * @param flowControl  (optional) defines the flow control behavior in case of failure,
	 *                     defaulting to the global `defaultFailureHandling`
	 */
	void authorize(String status, String requestButton = REQUEST, String s2uType = '', FailureHandling flowControl = defaultFailureHandling) {
		'Wait for loading to complete'
		webCommonPage.waitLoading();

		if (!WebUIKeyword.waitForElementPresent(webCommonPage.btnNormalizeSpaceDynamic(REQUEST), GlobalVariable.G_timeoutMedium, FailureHandling.OPTIONAL)) {
			WebUIKeyword.takeScreenshot();
			webCommonPage.clickConfirmButton(flowControl);
			return;
		}

		'Determine authentication method'
		if (GlobalVariable.G_isTacM2U) {
			this.selectDropdownS2uType(TAC);
		} else if (s2uType) {
			this.selectDropdownS2uType(s2uType);
		}

		WebUI.delay(GlobalVariable.G_timeoutShort);

		'Retrieve the confirmation type'
		//		String confirmType = WebUIKeyword.getText(webCommonPage.lblContainClass(CONFIRM_S2U_SECTION_CLASS, 2), flowControl);
		String confirmType

		if (GlobalVariable.G_isTacM2U) {
			confirmType = TAC_API;
		} else {
			confirmType = SECURE_VERIFICATION
			secure2uAuthorisationPage = BasePage.getPage(Secure2uAuthorisationPage);
		}

		'Click Request Button'
		WebUIKeyword.click(webCommonPage.btnNormalizeSpaceDynamic(REQUEST), flowControl);
		WebUIKeyword.delay(GlobalVariable.G_timeoutMiniShort);
		webCommonPage.waitLoading();

		'Handle authentication based on the confirmation type'
		switch (confirmType) {
			case SECURE_VERIFICATION:
				if (!webCommonPage.waitForElementPresent(webCommonPage.lblDynamic(S2U_APPROVAL_MESSAGE), GlobalVariable.G_timeoutMedium, FailureHandling.OPTIONAL)) {
					return;
				}
				secure2uAuthorisationPage.authorizeM2UTransaction(status, AuthorisationType.Secure2U, [], true);
				break;

			case TAC:
				'Wait for TAC to be sent'
				Mobile.delay(GlobalVariable.G_timeoutShort);
				String tac = secure2uAuthorisationPage.authorizeM2UTransaction(status, AuthorisationType.TAC);
				WebUIKeyword.setText(webCommonPage.lblContainClass(ONE_TIME_PASSWORD_CLASS), tac, flowControl);
				MobileDriver.takeScreenshot();
				webCommonPage.clickConfirmButton();
				break;

			case TAC_API:
				'Wait for TAC from API'
				String tacFromAPI
				switch(status) {
					case 'Expired':
						tacFromAPI = "111111"
						WebUIKeyword.setText(webCommonPage.lblContainClass(ONE_TIME_PASSWORD_CLASS), tacFromAPI, flowControl);
						webCommonPage.clickConfirmButton();
						break
					case 'Reject':
						tacFromAPI = "111111"
						WebUIKeyword.setText(webCommonPage.lblContainClass(ONE_TIME_PASSWORD_CLASS), tacFromAPI, flowControl);
						webCommonPage.clickConfirmButton();
						break
					default:
						tacFromAPI = verifyTacFromAPI(ID_NUMBER);
						WebUIKeyword.setText(webCommonPage.lblContainClass(ONE_TIME_PASSWORD_CLASS), tacFromAPI, flowControl);
						webCommonPage.clickConfirmButton();
						break;
				}
				break;
			default:
				secure2uAuthorisationPage.authorizeM2UTransaction(status, AuthorisationType.Secure2U, [], true);
				break;
		}

		WebUIKeyword.takeScreenshot();
	}
	
	void authorizeS2UV5(String status, String requestButton = REQUEST, String s2uType = '', FailureHandling flowControl = defaultFailureHandling) {
		'Wait for loading to complete'
		webCommonPage.waitLoading();

		if (!WebUIKeyword.waitForElementPresent(webCommonPage.btnNormalizeSpaceDynamic(REQUEST), GlobalVariable.G_timeoutMedium, FailureHandling.OPTIONAL)) {
			WebUIKeyword.takeScreenshot();
			webCommonPage.clickConfirmButton(flowControl);
			return;
		}

		'Determine authentication method'
		if (GlobalVariable.G_isTacM2U) {
			this.selectDropdownS2uType(TAC);
		} else if (s2uType) {
			this.selectDropdownS2uType(s2uType);
		}

		WebUI.delay(GlobalVariable.G_timeoutShort);

		'Retrieve the confirmation type'
		String confirmType

		if (GlobalVariable.G_isTacM2U) {
			confirmType = TAC_API;
		} else {
			secure2uAuthorisationPage = BasePage.getPage(Secure2uAuthorisationPage);
		}

		'Click Request Button'
		WebUIKeyword.click(webCommonPage.btnNormalizeSpaceDynamic(REQUEST), flowControl);
		WebUIKeyword.delay(GlobalVariable.G_timeoutMiniShort);
		webCommonPage.waitLoading();

		'Handle authentication based on the confirmation type'
		switch (confirmType) {
			case SECURE_VERIFICATION:
				if (!webCommonPage.waitForElementPresent(webCommonPage.lblDynamic(S2U_APPROVAL_MESSAGE), GlobalVariable.G_timeoutMedium, FailureHandling.OPTIONAL)) {
					return;
				}
				secure2uAuthorisationPage.authorizeS2UV5M2UTransaction(status, AuthorisationType.Secure2U, [], true);
				break;

			case TAC:
				'Wait for TAC to be sent'
				Mobile.delay(GlobalVariable.G_timeoutShort);
				String tac = secure2uAuthorisationPage.authorizeS2UV5M2UTransaction(status, AuthorisationType.TAC);
				WebUIKeyword.setText(webCommonPage.lblContainClass(ONE_TIME_PASSWORD_CLASS), tac, flowControl);
				MobileDriver.takeScreenshot();
				webCommonPage.clickConfirmButton();
				break;

			case TAC_API:
				'Wait for TAC from API'
				String tacFromAPI
				switch(status) {
					case 'Expired':
						tacFromAPI = "111111"
						WebUIKeyword.setText(webCommonPage.lblContainClass(ONE_TIME_PASSWORD_CLASS), tacFromAPI, flowControl);
						webCommonPage.clickConfirmButton();
						break
					case 'Reject':
						tacFromAPI = "111111"
						WebUIKeyword.setText(webCommonPage.lblContainClass(ONE_TIME_PASSWORD_CLASS), tacFromAPI, flowControl);
						webCommonPage.clickConfirmButton();
						break
					default:
						tacFromAPI = verifyTacFromAPI(ID_NUMBER);
						WebUIKeyword.setText(webCommonPage.lblContainClass(ONE_TIME_PASSWORD_CLASS), tacFromAPI, flowControl);
						webCommonPage.clickConfirmButton();
						break;
				}
				break;
			default:
				secure2uAuthorisationPage.authorizeS2UV5M2UTransaction(status, AuthorisationType.Secure2U, [], true);
				break;
		}

		WebUIKeyword.takeScreenshot();
	}

	void selectDropdownS2uType(String s2uType, FailureHandling flowControl = defaultFailureHandling) {
		WebUIKeyword.click(webCommonPage.lblContainClass(CONFIRM_S2U_SECTION_CLASS, 2), flowControl)
		WebUIKeyword.click(webCommonPage.lblContainClassAndText(CONFIRM_S2U_SECTION_CLASS, s2uType), flowControl)
	}

	void inputTAC(String tac, String requestButton = REQUEST, FailureHandling flowControl = defaultFailureHandling) {
		this.selectDropdownS2uType(TAC)

		'Click Request Button'
		WebUIKeyword.click(webCommonPage.btnNormalizeSpaceDynamic(requestButton), flowControl)

		WebUIKeyword.delay(GlobalVariable.G_timeoutShort)

		'Invalid TAC'
		WebUIKeyword.setText(webCommonPage.lblContainClass(ONE_TIME_PASSWORD_CLASS), tac, flowControl)

		webCommonPage.clickConfirmButton()
	}
	String verifyTacFromAPI(String idNumber, FailureHandling flowControl = FailureHandling.CONTINUE_ON_FAILURE) {
		def response = WS.sendRequest(findTestObject('Object Repository/Postman/getTacM2U'))

		if (response.getStatusCode() == 200) {
			def responseText = response.getResponseText()
			def entries = responseText.split("\\*{29}") // Split by *****************************

			for (String entry : entries) {
				def smsIcNo = ""
				def smsMessage = ""

				// Extract SMS fields using regex
				Matcher icMatcher = Pattern.compile("SMS_IC_NO = (.*?)<br>").matcher(entry)
				Matcher messageMatcher = Pattern.compile("SMS_MESSAGE = (.*?)<br>").matcher(entry)

				if (icMatcher.find()) {
					smsIcNo = icMatcher.group(1).trim()
				}
				if (messageMatcher.find()) {
					smsMessage = messageMatcher.group(1).trim()
				}

				// Check if the IC number matches the input parameter
				if (smsIcNo.equals(idNumber)) {
					Matcher tacMatcher = Pattern.compile("TAC No\\. is (\\d+)").matcher(smsMessage)

					if (tacMatcher.find()) {
						String tacNumber = tacMatcher.group(1)
						WS.comment("Extracted TAC: " + tacNumber)
						return tacNumber
					}
				}
			}
			WS.comment("No matching TAC found for ID: " + idNumber)
		} else {
			WS.comment("API request failed with status: " + response.getStatusCode())
		}
		return null // Return null if no TAC is found
	}
	void verifyErrorTAC(String validateErrorTACType, List errorTACMsg, FailureHandling flowControl = defaultFailureHandling) {
		switch(validateErrorTACType) {
			case INVALID_TAC:
				this.inputTAC(INVALID_TAC_NO)
				WebUIKeyword.delay(GlobalVariable.G_timeoutMiniShort)
				webCommonPage.verifyElementListVisible(errorTACMsg)
				break
			case BLANK_TAC:
				this.inputTAC("")
				WebUIKeyword.delay(GlobalVariable.G_timeoutMiniShort)
				webCommonPage.verifyElementListVisible(errorTACMsg)
				break
		}
	}
	
	void authoriseWCqQuestion(String status, String securityAnswer, String requestButton = REQUEST, String s2uType = '', FailureHandling flowControl = defaultFailureHandling) {
		'Wait for loading to complete'
		webCommonPage.waitLoading();

		if (!WebUIKeyword.waitForElementPresent(webCommonPage.btnNormalizeSpaceDynamic(REQUEST), GlobalVariable.G_timeoutMedium, FailureHandling.OPTIONAL)) {
			WebUIKeyword.takeScreenshot();
			webCommonPage.clickConfirmButton(flowControl);
			return;
		}

		'Determine authentication method'
		if (GlobalVariable.G_isTacM2U) {
			this.selectDropdownS2uType(TAC);
		} else if (s2uType) {
			this.selectDropdownS2uType(s2uType);
		}

		WebUI.delay(GlobalVariable.G_timeoutShort);

		'Retrieve the confirmation type'
		
		String confirmType = '';
		if(WebUIKeyword.getText(webCommonPage.lblContainClass(CONFIRM_S2U_SECTION_CLASS, 2), FailureHandling.OPTIONAL)) {
			confirmType = WebUIKeyword.getText(webCommonPage.lblContainClass(CONFIRM_S2U_SECTION_CLASS, 2), flowControl);
		}
	

		if (GlobalVariable.G_isTacM2U) {
			confirmType = TAC_API;
		} else {
			secure2uAuthorisationPage = BasePage.getPage(Secure2uAuthorisationPage);
		}

		'Click Request Button'
		WebUIKeyword.click(webCommonPage.btnNormalizeSpaceDynamic(REQUEST), flowControl);
		WebUIKeyword.delay(GlobalVariable.G_timeoutMiniShort);
		webCommonPage.waitLoading();
		
		'Enter Answer For Security Question'
		webRsaPage.inputAnswer(securityAnswer)
		WebUIKeyword.takeScreenshot();
		webRsaPage.confirmAnswer()
		WebUIKeyword.takeScreenshot();
		
		'Handle authentication based on the confirmation type'
		switch (confirmType) {
			case SECURE_VERIFICATION:
				if (!webCommonPage.waitForElementPresent(webCommonPage.lblDynamic(S2U_APPROVAL_MESSAGE), GlobalVariable.G_timeoutMedium, FailureHandling.OPTIONAL)) {
					return;
				}
				secure2uAuthorisationPage.authorizeM2UTransaction(status, AuthorisationType.Secure2U, [], true);
				break;

			case TAC:
				'Wait for TAC to be sent'
				Mobile.delay(GlobalVariable.G_timeoutShort);
				String tac = secure2uAuthorisationPage.authorizeM2UTransaction(status, AuthorisationType.TAC);
				WebUIKeyword.setText(webCommonPage.lblContainClass(ONE_TIME_PASSWORD_CLASS), tac, flowControl);
				MobileDriver.takeScreenshot();
				webCommonPage.clickConfirmButton();
				break;

			case TAC_API:
				'Wait for TAC from API'
				String tacFromAPI
				switch(status) {
					case 'Expired':
						tacFromAPI = "111111"
						WebUIKeyword.setText(webCommonPage.lblContainClass(ONE_TIME_PASSWORD_CLASS), tacFromAPI, flowControl);
						webCommonPage.clickConfirmButton();
						break
					case 'Reject':
						tacFromAPI = "111111"
						WebUIKeyword.setText(webCommonPage.lblContainClass(ONE_TIME_PASSWORD_CLASS), tacFromAPI, flowControl);
						webCommonPage.clickConfirmButton();
						break
					default:
						tacFromAPI = verifyTacFromAPI(ID_NUMBER);
						WebUIKeyword.setText(webCommonPage.lblContainClass(ONE_TIME_PASSWORD_CLASS), tacFromAPI, flowControl);
						webCommonPage.clickConfirmButton();
						break;
				}
				break;
			default:
				secure2uAuthorisationPage.authorizeM2UTransaction(status, AuthorisationType.Secure2U, [], true);
				break;
		}

		WebUIKeyword.takeScreenshot();
	}
}
