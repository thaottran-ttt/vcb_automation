
/**
 * This class is generated automatically by Katalon Studio and should not be modified or deleted.
 */

import java.lang.String

import com.kms.katalon.core.model.FailureHandling

import java.lang.Number

import com.kms.katalon.core.testobject.TestObject

import java.lang.Exception

import java.util.List

import com.google.gson.JsonObject

import org.apache.poi.ss.usermodel.Workbook

import org.apache.poi.ss.usermodel.Sheet

import java.util.Map

import org.apache.poi.ss.usermodel.Cell

import org.apache.poi.ss.usermodel.Row

import com.kms.katalon.core.testobject.MobileTestObject

import com.vcb.common.MobileDriver.ScrollDirectionEnum

import java.lang.Object



def static "com.vcb.m2u.pages.WebCommon.openBrowserAndBypassSecure"(
    	String url	
     , 	boolean isRunWebsiteAndMobile	) {
    (new com.vcb.m2u.pages.WebCommon()).openBrowserAndBypassSecure(
        	url
         , 	isRunWebsiteAndMobile)
}


def static "com.vcb.utils.RegexUtils.patternChecking"(
    	String testString	) {
    (new com.vcb.utils.RegexUtils()).patternChecking(
        	testString)
}

 /**
	 * Evaluate a string contains another string
	 * @param sourceStr source string
	 * @param subStr substring
	 * @param description detail of evaluation
	 */ 
def static "com.vcb.assertions.StringAssert.contains"(
    	String sourceStr	
     , 	String subStr	
     , 	boolean caseSensitive	
     , 	String description	
     , 	FailureHandling flowControl	) {
    (new com.vcb.assertions.StringAssert()).contains(
        	sourceStr
         , 	subStr
         , 	caseSensitive
         , 	description
         , 	flowControl)
}

 /**
	 * Evaluate a string does not contain another string
	 * @param sourceStr source string
	 * @param subStr substring
	 * @param description detail of evaluation
	 */ 
def static "com.vcb.assertions.StringAssert.notContains"(
    	String sourceStr	
     , 	String subStr	
     , 	boolean caseSensitive	
     , 	String description	
     , 	FailureHandling flowControl	) {
    (new com.vcb.assertions.StringAssert()).notContains(
        	sourceStr
         , 	subStr
         , 	caseSensitive
         , 	description
         , 	flowControl)
}

 /**
	 * Evaluate a string equals to another string
	 * @param sourceStr source string
	 * @param subStr substring
	 * @param description detail of evaluation
	 */ 
def static "com.vcb.assertions.StringAssert.equals"(
    	String sourceStr	
     , 	String subStr	
     , 	boolean caseSensitive	
     , 	String description	
     , 	FailureHandling flowControl	) {
    (new com.vcb.assertions.StringAssert()).equals(
        	sourceStr
         , 	subStr
         , 	caseSensitive
         , 	description
         , 	flowControl)
}

 /**
	 * Evaluate a string starts with to a prefix
	 * @param sourceStr source string
	 * @param prefix
	 * @param description detail of evaluation
	 */ 
def static "com.vcb.assertions.StringAssert.startsWith"(
    	String sourceStr	
     , 	String prefix	
     , 	boolean caseSensitive	
     , 	String description	
     , 	FailureHandling flowControl	) {
    (new com.vcb.assertions.StringAssert()).startsWith(
        	sourceStr
         , 	prefix
         , 	caseSensitive
         , 	description
         , 	flowControl)
}

 /**
	 * Evaluate a string ends with to a prefix
	 * @param sourceStr source string
	 * @param suffix
	 * @param description detail of evaluation
	 */ 
def static "com.vcb.assertions.StringAssert.endsWith"(
    	String sourceStr	
     , 	String suffix	
     , 	boolean caseSensitive	
     , 	String description	
     , 	FailureHandling flowControl	) {
    (new com.vcb.assertions.StringAssert()).endsWith(
        	sourceStr
         , 	suffix
         , 	caseSensitive
         , 	description
         , 	flowControl)
}

 /**
	 * Evaluate a string expression matches a regular expression.
	 * @param sourceStr source string
	 * @param description
	 * @param regex
	 */ 
def static "com.vcb.assertions.StringAssert.matches"(
    	String sourceStr	
     , 	String regex	
     , 	String description	
     , 	FailureHandling flowControl	) {
    (new com.vcb.assertions.StringAssert()).matches(
        	sourceStr
         , 	regex
         , 	description
         , 	flowControl)
}


def static "com.vcb.assertions.StringAssert.contains"(
    	String sourceStr	
     , 	String subStr	
     , 	boolean caseSensitive	
     , 	String description	) {
    (new com.vcb.assertions.StringAssert()).contains(
        	sourceStr
         , 	subStr
         , 	caseSensitive
         , 	description)
}


def static "com.vcb.assertions.StringAssert.contains"(
    	String sourceStr	
     , 	String subStr	
     , 	String description	) {
    (new com.vcb.assertions.StringAssert()).contains(
        	sourceStr
         , 	subStr
         , 	description)
}


def static "com.vcb.assertions.StringAssert.notContains"(
    	String sourceStr	
     , 	String subStr	
     , 	boolean caseSensitive	
     , 	String description	) {
    (new com.vcb.assertions.StringAssert()).notContains(
        	sourceStr
         , 	subStr
         , 	caseSensitive
         , 	description)
}


def static "com.vcb.assertions.StringAssert.notContains"(
    	String sourceStr	
     , 	String subStr	
     , 	String description	) {
    (new com.vcb.assertions.StringAssert()).notContains(
        	sourceStr
         , 	subStr
         , 	description)
}


def static "com.vcb.assertions.StringAssert.equals"(
    	String sourceStr	
     , 	String subStr	
     , 	boolean caseSensitive	
     , 	String description	) {
    (new com.vcb.assertions.StringAssert()).equals(
        	sourceStr
         , 	subStr
         , 	caseSensitive
         , 	description)
}


def static "com.vcb.assertions.StringAssert.equals"(
    	String sourceStr	
     , 	String subStr	
     , 	String description	) {
    (new com.vcb.assertions.StringAssert()).equals(
        	sourceStr
         , 	subStr
         , 	description)
}


def static "com.vcb.assertions.StringAssert.startsWith"(
    	String sourceStr	
     , 	String prefix	
     , 	boolean caseSensitive	
     , 	String description	) {
    (new com.vcb.assertions.StringAssert()).startsWith(
        	sourceStr
         , 	prefix
         , 	caseSensitive
         , 	description)
}


def static "com.vcb.assertions.StringAssert.startsWith"(
    	String sourceStr	
     , 	String prefix	
     , 	String description	) {
    (new com.vcb.assertions.StringAssert()).startsWith(
        	sourceStr
         , 	prefix
         , 	description)
}


def static "com.vcb.assertions.StringAssert.endsWith"(
    	String sourceStr	
     , 	String suffix	
     , 	boolean caseSensitive	
     , 	String description	) {
    (new com.vcb.assertions.StringAssert()).endsWith(
        	sourceStr
         , 	suffix
         , 	caseSensitive
         , 	description)
}


def static "com.vcb.assertions.StringAssert.endsWith"(
    	String sourceStr	
     , 	String suffix	
     , 	String description	) {
    (new com.vcb.assertions.StringAssert()).endsWith(
        	sourceStr
         , 	suffix
         , 	description)
}


def static "com.vcb.assertions.StringAssert.matches"(
    	String sourceStr	
     , 	String regex	
     , 	String description	) {
    (new com.vcb.assertions.StringAssert()).matches(
        	sourceStr
         , 	regex
         , 	description)
}

 /**
	 * Evaluate a number is greater than another
	 * @param a number on the left side
	 * @param b number on the right side
	 * @param description detail of evaluation
	 */ 
def static "com.vcb.assertions.NumberAssert.isGreaterThan"(
    	Number a	
     , 	Number b	
     , 	String description	
     , 	FailureHandling flowControl	) {
    (new com.vcb.assertions.NumberAssert()).isGreaterThan(
        	a
         , 	b
         , 	description
         , 	flowControl)
}

 /**
	 * Evaluate a number is greater than or equal another
	 * @param a number on the left side
	 * @param b number on the right side
	 * @param description detail of evaluation
	 */ 
def static "com.vcb.assertions.NumberAssert.isGreaterThanOrEqual"(
    	Number a	
     , 	Number b	
     , 	String description	
     , 	FailureHandling flowControl	) {
    (new com.vcb.assertions.NumberAssert()).isGreaterThanOrEqual(
        	a
         , 	b
         , 	description
         , 	flowControl)
}

 /**
	 * Evaluate a number is greater than another
	 * @param a number on the left side
	 * @param b number on the right side
	 * @param description detail of evaluation
	 */ 
def static "com.vcb.assertions.NumberAssert.isLessThan"(
    	Number a	
     , 	Number b	
     , 	String description	
     , 	FailureHandling flowControl	) {
    (new com.vcb.assertions.NumberAssert()).isLessThan(
        	a
         , 	b
         , 	description
         , 	flowControl)
}

 /**
	 * Evaluate a number is less than or equal another
	 * @param a number on the left side
	 * @param b number on the right side
	 * @param description detail of evaluation
	 */ 
def static "com.vcb.assertions.NumberAssert.isLessThanOrEqual"(
    	Number a	
     , 	Number b	
     , 	String description	
     , 	FailureHandling flowControl	) {
    (new com.vcb.assertions.NumberAssert()).isLessThanOrEqual(
        	a
         , 	b
         , 	description
         , 	flowControl)
}


def static "com.vcb.assertions.NumberAssert.isGreaterThan"(
    	Number a	
     , 	Number b	
     , 	String description	) {
    (new com.vcb.assertions.NumberAssert()).isGreaterThan(
        	a
         , 	b
         , 	description)
}


def static "com.vcb.assertions.NumberAssert.isGreaterThanOrEqual"(
    	Number a	
     , 	Number b	
     , 	String description	) {
    (new com.vcb.assertions.NumberAssert()).isGreaterThanOrEqual(
        	a
         , 	b
         , 	description)
}


def static "com.vcb.assertions.NumberAssert.isLessThan"(
    	Number a	
     , 	Number b	
     , 	String description	) {
    (new com.vcb.assertions.NumberAssert()).isLessThan(
        	a
         , 	b
         , 	description)
}


def static "com.vcb.assertions.NumberAssert.isLessThanOrEqual"(
    	Number a	
     , 	Number b	
     , 	String description	) {
    (new com.vcb.assertions.NumberAssert()).isLessThanOrEqual(
        	a
         , 	b
         , 	description)
}


def static "com.vcb.mobile.pages.Account.getAccountInfo"(
    	int timeout	
     , 	Object flowControl	) {
    (new com.vcb.mobile.pages.Account()).getAccountInfo(
        	timeout
         , 	flowControl)
}


def static "com.vcb.mobile.pages.Account.getCreditAccountInfo"(
    	int timeout	
     , 	Object flowControl	) {
    (new com.vcb.mobile.pages.Account()).getCreditAccountInfo(
        	timeout
         , 	flowControl)
}


def static "com.vcb.mobile.pages.Account.clickThreeDotsButton"(
    	Object flowControl	) {
    (new com.vcb.mobile.pages.Account()).clickThreeDotsButton(
        	flowControl)
}


def static "com.vcb.mobile.pages.Account.verifyContactBankText"(
    	Object flowControl	) {
    (new com.vcb.mobile.pages.Account()).verifyContactBankText(
        	flowControl)
}


def static "com.vcb.mobile.pages.Account.getAccountInfo"(
    	int timeout	) {
    (new com.vcb.mobile.pages.Account()).getAccountInfo(
        	timeout)
}


def static "com.vcb.mobile.pages.Account.getAccountInfo"() {
    (new com.vcb.mobile.pages.Account()).getAccountInfo()
}


def static "com.vcb.mobile.pages.Account.getCreditAccountInfo"(
    	int timeout	) {
    (new com.vcb.mobile.pages.Account()).getCreditAccountInfo(
        	timeout)
}


def static "com.vcb.mobile.pages.Account.getCreditAccountInfo"() {
    (new com.vcb.mobile.pages.Account()).getCreditAccountInfo()
}


def static "com.vcb.helper.RestAPIHelper.getJsonObject"(
    	TestObject request	
     , 	FailureHandling controlFlow	) {
    (new com.vcb.helper.RestAPIHelper()).getJsonObject(
        	request
         , 	controlFlow)
}


def static "com.vcb.helper.RestAPIHelper.getJsonObject"(
    	TestObject request	) {
    (new com.vcb.helper.RestAPIHelper()).getJsonObject(
        	request)
}


def static "com.vcb.mobile.pages.Secure2u.navigateHomePage"(
    	String PAN	
     , 	String password	
     , 	FailureHandling flowControl	) {
    (new com.vcb.mobile.pages.Secure2u()).navigateHomePage(
        	PAN
         , 	password
         , 	flowControl)
}


def static "com.vcb.mobile.pages.Secure2u.enterIdNumber"(
    	String idNumber	
     , 	FailureHandling flowControl	) {
    (new com.vcb.mobile.pages.Secure2u()).enterIdNumber(
        	idNumber
         , 	flowControl)
}


def static "com.vcb.mobile.pages.Secure2u.activeSecure2uAtm"(
    	String PAN	
     , 	String password	
     , 	FailureHandling flowControl	) {
    (new com.vcb.mobile.pages.Secure2u()).activeSecure2uAtm(
        	PAN
         , 	password
         , 	flowControl)
}


def static "com.vcb.mobile.pages.Secure2u.navigateHomePage"(
    	String PAN	
     , 	String password	) {
    (new com.vcb.mobile.pages.Secure2u()).navigateHomePage(
        	PAN
         , 	password)
}


def static "com.vcb.mobile.pages.Secure2u.enterIdNumber"(
    	String idNumber	) {
    (new com.vcb.mobile.pages.Secure2u()).enterIdNumber(
        	idNumber)
}


def static "com.vcb.mobile.pages.Secure2u.activeSecure2uAtm"(
    	String PAN	
     , 	String password	) {
    (new com.vcb.mobile.pages.Secure2u()).activeSecure2uAtm(
        	PAN
         , 	password)
}

 /**
	 * Exception Handling
	 * */ 
def static "com.vcb.common.BaseKeyword.handleError"(
    	String message	
     , 	FailureHandling flowControl	) {
    (new com.vcb.common.BaseKeyword()).handleError(
        	message
         , 	flowControl)
}


def static "com.vcb.common.BaseKeyword.handleError"(
    	Exception ex	
     , 	FailureHandling flowControl	) {
    (new com.vcb.common.BaseKeyword()).handleError(
        	ex
         , 	flowControl)
}

 /**
	 * Log Info
	 * */ 
def static "com.vcb.common.BaseKeyword.info"(
    	Object message	) {
    (new com.vcb.common.BaseKeyword()).info(
        	message)
}


def static "com.vcb.common.BaseKeyword.markPassed"(
    	Object message	) {
    (new com.vcb.common.BaseKeyword()).markPassed(
        	message)
}


def static "com.vcb.common.BaseKeyword.runFromTestCloud"() {
    (new com.vcb.common.BaseKeyword()).runFromTestCloud()
}

 /**
	 * Connects to an already running Chrome instance using the Chrome DevTools Protocol (CDP).
	 *
	 * This method allows interaction with a running Chrome browser instance by specifying its remote debugging port.
	 * It uses Katalon's ChromeDriver to establish the connection and updates Katalon's WebDriver instance to use the new driver.
	 *
	 * @param port The port of the running Chrome instance (default: 9222).
	 * @return A WebDriver instance connected to the running Chrome instance.
	 */ 
def static "com.vcb.common.BaseKeyword.connectToRunningClient"(
    	int port	) {
    (new com.vcb.common.BaseKeyword()).connectToRunningClient(
        	port)
}


def static "com.vcb.common.BaseKeyword.setFailureHandling"(
    	String flowControl	) {
    (new com.vcb.common.BaseKeyword()).setFailureHandling(
        	flowControl)
}


def static "com.vcb.common.BaseKeyword.testFlowControl"(
    	FailureHandling flowControl	) {
    (new com.vcb.common.BaseKeyword()).testFlowControl(
        	flowControl)
}


def static "com.vcb.common.BaseKeyword.handleError"(
    	Exception ex	) {
    (new com.vcb.common.BaseKeyword()).handleError(
        	ex)
}


def static "com.vcb.common.BaseKeyword.connectToRunningClient"() {
    (new com.vcb.common.BaseKeyword()).connectToRunningClient()
}


def static "com.vcb.common.BaseKeyword.testFlowControl"() {
    (new com.vcb.common.BaseKeyword()).testFlowControl()
}

 /**
	 * verifyElements: verify text on screen
	 * @param lst: text List on screen
	 * pathObject: Path repository of object
	 * @return: true if all text in lst is display
	 */ 
def static "com.vcb.mobile.internal.pages.base.BasePage.verifyElements"(
    	List lst	
     , 	String pathObject	
     , 	int timeout	
     , 	FailureHandling flowControl	) {
    (new com.vcb.mobile.internal.pages.base.BasePage()).verifyElements(
        	lst
         , 	pathObject
         , 	timeout
         , 	flowControl)
}

 /**
	 * scrollToAndVerifyTextInList: scroll to and verify each text in the list
	 * @param lst: text List on screen
	 * pathObject: Path repository of object
	 * @return: true if all text in lst is display
	 */ 
def static "com.vcb.mobile.internal.pages.base.BasePage.scrollToAndVerifyTextInList"(
    	List lst	
     , 	String pathObject	
     , 	int timeout	
     , 	FailureHandling flowControl	) {
    (new com.vcb.mobile.internal.pages.base.BasePage()).scrollToAndVerifyTextInList(
        	lst
         , 	pathObject
         , 	timeout
         , 	flowControl)
}

 /**
	 * verifySomeElementsMatchInList: verify text on screen
	 * @param lst: text List on screen
	 * pathObject: Path repository of object
	 * @return true if 1 item in lst is display
	 */ 
def static "com.vcb.mobile.internal.pages.base.BasePage.verifySomeElementsMatchInList"(
    	List lst	
     , 	String pathObject	
     , 	int timeout	
     , 	FailureHandling flowControl	) {
    (new com.vcb.mobile.internal.pages.base.BasePage()).verifySomeElementsMatchInList(
        	lst
         , 	pathObject
         , 	timeout
         , 	flowControl)
}

 /**
	 * verifyFormatMoney: verify Format Money (xxx,xx.xx)
	 * @param
	 * @return
	 */ 
def static "com.vcb.mobile.internal.pages.base.BasePage.verifyFormatMoney"(
    	List lst	) {
    (new com.vcb.mobile.internal.pages.base.BasePage()).verifyFormatMoney(
        	lst)
}

 /**
	 * verifyFormatMoneyRM: verify Format Money (RM xxx,xx.xx)
	 * @param
	 * @return
	 */ 
def static "com.vcb.mobile.internal.pages.base.BasePage.verifyFormatMoneyRM"(
    	List lst	) {
    (new com.vcb.mobile.internal.pages.base.BasePage()).verifyFormatMoneyRM(
        	lst)
}

 /**
	 * verifyFormatDate: verify Format Date (pattern "dd MMM yyyy")
	 * @param
	 * @return
	 */ 
def static "com.vcb.mobile.internal.pages.base.BasePage.verifyFormatDate"(
    	List lst	) {
    (new com.vcb.mobile.internal.pages.base.BasePage()).verifyFormatDate(
        	lst)
}

 /**
	 * verifyFormatAccountNumber: (pattern "xxxx xxxx xxxx")
	 * @param
	 * @return
	 */ 
def static "com.vcb.mobile.internal.pages.base.BasePage.verifyFormatAccountNumber"(
    	List lst	) {
    (new com.vcb.mobile.internal.pages.base.BasePage()).verifyFormatAccountNumber(
        	lst)
}


def static "com.vcb.mobile.internal.pages.base.BasePage.verifyElements"(
    	List lst	
     , 	String pathObject	
     , 	int timeout	) {
    (new com.vcb.mobile.internal.pages.base.BasePage()).verifyElements(
        	lst
         , 	pathObject
         , 	timeout)
}


def static "com.vcb.mobile.internal.pages.base.BasePage.scrollToAndVerifyTextInList"(
    	List lst	
     , 	String pathObject	
     , 	int timeout	) {
    (new com.vcb.mobile.internal.pages.base.BasePage()).scrollToAndVerifyTextInList(
        	lst
         , 	pathObject
         , 	timeout)
}


def static "com.vcb.mobile.internal.pages.base.BasePage.verifySomeElementsMatchInList"(
    	List lst	
     , 	String pathObject	
     , 	int timeout	) {
    (new com.vcb.mobile.internal.pages.base.BasePage()).verifySomeElementsMatchInList(
        	lst
         , 	pathObject
         , 	timeout)
}


def static "com.vcb.mobile.pages.SecurityQuestion.inputAnswer"(
    	String answer	
     , 	FailureHandling flowControl	) {
    (new com.vcb.mobile.pages.SecurityQuestion()).inputAnswer(
        	answer
         , 	flowControl)
}


def static "com.vcb.mobile.pages.SecurityQuestion.inputAnswer"(
    	String answer	) {
    (new com.vcb.mobile.pages.SecurityQuestion()).inputAnswer(
        	answer)
}

 /**
	 * Parse an amount as text in ibank app to a long number
	 * @param amount in String
	 * @return amount in Long
	 */ 
def static "com.vcb.utils.StringUtils.parseAmountStringToIntegerInApp"(
    	String amount	) {
    (new com.vcb.utils.StringUtils()).parseAmountStringToIntegerInApp(
        	amount)
}

 /**
	 * Using Regex to extract string
	 * @param String and pattern
	 * @return string mattched
	 */ 
def static "com.vcb.utils.StringUtils.extractStrPattern"(
    	String str	
     , 	String pattern	
     , 	boolean allString	) {
    (new com.vcb.utils.StringUtils()).extractStrPattern(
        	str
         , 	pattern
         , 	allString)
}


def static "com.vcb.utils.StringUtils.extractNumber"(
    	Object balance	) {
    (new com.vcb.utils.StringUtils()).extractNumber(
        	balance)
}


def static "com.vcb.utils.StringUtils.createStrRandom"(
    	int length	) {
    (new com.vcb.utils.StringUtils()).createStrRandom(
        	length)
}


def static "com.vcb.utils.StringUtils.createNumberRandom"(
    	int length	) {
    (new com.vcb.utils.StringUtils()).createNumberRandom(
        	length)
}


def static "com.vcb.utils.StringUtils.concatString"(
    	String str1	
     , 	String str2	
     , 	String con	) {
    (new com.vcb.utils.StringUtils()).concatString(
        	str1
         , 	str2
         , 	con)
}


def static "com.vcb.utils.StringUtils.convertBalanceAmountNumber"(
    	String amountInput	) {
    (new com.vcb.utils.StringUtils()).convertBalanceAmountNumber(
        	amountInput)
}


def static "com.vcb.utils.StringUtils.formatToMoney"(
    	String numberStr	) {
    (new com.vcb.utils.StringUtils()).formatToMoney(
        	numberStr)
}


def static "com.vcb.utils.StringUtils.verifyDataFormat"(
    	String valueInput	
     , 	String verifyPattern	) {
    (new com.vcb.utils.StringUtils()).verifyDataFormat(
        	valueInput
         , 	verifyPattern)
}


def static "com.vcb.utils.StringUtils.verifyDateTimeFormat"(
    	String valueInput	
     , 	String verifyPattern	) {
    (new com.vcb.utils.StringUtils()).verifyDateTimeFormat(
        	valueInput
         , 	verifyPattern)
}

 /**
	 * genarate a Nric for person over 18+
	 */ 
def static "com.vcb.utils.StringUtils.generateRandomValidNRIC"() {
    (new com.vcb.utils.StringUtils()).generateRandomValidNRIC()
}


def static "com.vcb.utils.StringUtils.generateRandomPassport"(
    	int length	) {
    (new com.vcb.utils.StringUtils()).generateRandomPassport(
        	length)
}


def static "com.vcb.utils.StringUtils.extractStrPattern"(
    	String str	
     , 	String pattern	) {
    (new com.vcb.utils.StringUtils()).extractStrPattern(
        	str
         , 	pattern)
}


def static "com.vcb.utils.StringUtils.verifyDataFormat"(
    	String valueInput	) {
    (new com.vcb.utils.StringUtils()).verifyDataFormat(
        	valueInput)
}


def static "com.vcb.utils.StringUtils.verifyDateTimeFormat"(
    	String valueInput	) {
    (new com.vcb.utils.StringUtils()).verifyDateTimeFormat(
        	valueInput)
}


def static "com.vcb.service.LogInService.getToken"(
    	String apiKey	) {
    (new com.vcb.service.LogInService()).getToken(
        	apiKey)
}


def static "com.vcb.common.WebUIKeyword.openBrowser"(
    	Object url	) {
    (new com.vcb.common.WebUIKeyword()).openBrowser(
        	url)
}


def static "com.vcb.common.WebUIKeyword.closeBrowser"() {
    (new com.vcb.common.WebUIKeyword()).closeBrowser()
}


def static "com.vcb.common.WebUIKeyword.waitForElementPresent"(
    	TestObject obj	
     , 	int timeout	
     , 	FailureHandling flowControl	) {
    (new com.vcb.common.WebUIKeyword()).waitForElementPresent(
        	obj
         , 	timeout
         , 	flowControl)
}


def static "com.vcb.common.WebUIKeyword.waitForElementClickable"(
    	TestObject obj	
     , 	int timeout	
     , 	FailureHandling flowControl	) {
    (new com.vcb.common.WebUIKeyword()).waitForElementClickable(
        	obj
         , 	timeout
         , 	flowControl)
}


def static "com.vcb.common.WebUIKeyword.waitForElementVisible"(
    	TestObject obj	
     , 	int timeout	
     , 	FailureHandling flowControl	) {
    (new com.vcb.common.WebUIKeyword()).waitForElementVisible(
        	obj
         , 	timeout
         , 	flowControl)
}


def static "com.vcb.common.WebUIKeyword.waitForElementNotPresent"(
    	TestObject obj	
     , 	int timeout	
     , 	FailureHandling flowControl	) {
    (new com.vcb.common.WebUIKeyword()).waitForElementNotPresent(
        	obj
         , 	timeout
         , 	flowControl)
}


def static "com.vcb.common.WebUIKeyword.click"(
    	TestObject obj	
     , 	FailureHandling flowControl	) {
    (new com.vcb.common.WebUIKeyword()).click(
        	obj
         , 	flowControl)
}


def static "com.vcb.common.WebUIKeyword.setText"(
    	TestObject obj	
     , 	String textContent	
     , 	FailureHandling flowControl	) {
    (new com.vcb.common.WebUIKeyword()).setText(
        	obj
         , 	textContent
         , 	flowControl)
}


def static "com.vcb.common.WebUIKeyword.scrollToTestObject"(
    	TestObject obj	
     , 	int timeout	
     , 	FailureHandling flowControl	) {
    (new com.vcb.common.WebUIKeyword()).scrollToTestObject(
        	obj
         , 	timeout
         , 	flowControl)
}


def static "com.vcb.common.WebUIKeyword.setTextWithClear"(
    	TestObject obj	
     , 	String textContent	
     , 	boolean isFastClearEnabled	
     , 	FailureHandling flowControl	) {
    (new com.vcb.common.WebUIKeyword()).setTextWithClear(
        	obj
         , 	textContent
         , 	isFastClearEnabled
         , 	flowControl)
}


def static "com.vcb.common.WebUIKeyword.setEncryptedText"(
    	TestObject obj	
     , 	String textContent	
     , 	FailureHandling flowControl	) {
    (new com.vcb.common.WebUIKeyword()).setEncryptedText(
        	obj
         , 	textContent
         , 	flowControl)
}


def static "com.vcb.common.WebUIKeyword.getText"(
    	TestObject obj	
     , 	FailureHandling flowControl	) {
    (new com.vcb.common.WebUIKeyword()).getText(
        	obj
         , 	flowControl)
}


def static "com.vcb.common.WebUIKeyword.getAttributeValue"(
    	TestObject obj	
     , 	String att	
     , 	FailureHandling flowControl	) {
    (new com.vcb.common.WebUIKeyword()).getAttributeValue(
        	obj
         , 	att
         , 	flowControl)
}


def static "com.vcb.common.WebUIKeyword.verifyElementVisible"(
    	TestObject obj	
     , 	FailureHandling flowControl	) {
    (new com.vcb.common.WebUIKeyword()).verifyElementVisible(
        	obj
         , 	flowControl)
}


def static "com.vcb.common.WebUIKeyword.verifyElementPresent"(
    	TestObject obj	
     , 	FailureHandling flowControl	) {
    (new com.vcb.common.WebUIKeyword()).verifyElementPresent(
        	obj
         , 	flowControl)
}


def static "com.vcb.common.WebUIKeyword.takeScreenshot"() {
    (new com.vcb.common.WebUIKeyword()).takeScreenshot()
}


def static "com.vcb.common.WebUIKeyword.delay"(
    	Number timeout	) {
    (new com.vcb.common.WebUIKeyword()).delay(
        	timeout)
}

 /**
	 * Get list of text values from elements identified by XPath
	 * @param xpath - The XPath of the elements to be found
	 * @return List of strings containing the text of each element
	 */ 
def static "com.vcb.common.WebUIKeyword.getListOfTextByXPath"(
    	String xpath	) {
    (new com.vcb.common.WebUIKeyword()).getListOfTextByXPath(
        	xpath)
}

 /**
	 * getTextsByXpaths
	 * get all item in list view
	 * testObject: list View Item
	 * xpaths is xpath list of one item 
	 */ 
def static "com.vcb.common.WebUIKeyword.getTextsByXpaths"(
    	TestObject testObject	
     , 	List xpaths	
     , 	int timeout	
     , 	FailureHandling flowControl	) {
    (new com.vcb.common.WebUIKeyword()).getTextsByXpaths(
        	testObject
         , 	xpaths
         , 	timeout
         , 	flowControl)
}


def static "com.vcb.common.WebUIKeyword.clickWithoutScrollW3C"(
    	TestObject obj	
     , 	FailureHandling flowControl	) {
    (new com.vcb.common.WebUIKeyword()).clickWithoutScrollW3C(
        	obj
         , 	flowControl)
}


def static "com.vcb.common.WebUIKeyword.waitForElementPresent"(
    	TestObject obj	
     , 	int timeout	) {
    (new com.vcb.common.WebUIKeyword()).waitForElementPresent(
        	obj
         , 	timeout)
}


def static "com.vcb.common.WebUIKeyword.waitForElementClickable"(
    	TestObject obj	
     , 	int timeout	) {
    (new com.vcb.common.WebUIKeyword()).waitForElementClickable(
        	obj
         , 	timeout)
}


def static "com.vcb.common.WebUIKeyword.waitForElementVisible"(
    	TestObject obj	
     , 	int timeout	) {
    (new com.vcb.common.WebUIKeyword()).waitForElementVisible(
        	obj
         , 	timeout)
}


def static "com.vcb.common.WebUIKeyword.waitForElementNotPresent"(
    	TestObject obj	
     , 	int timeout	) {
    (new com.vcb.common.WebUIKeyword()).waitForElementNotPresent(
        	obj
         , 	timeout)
}


def static "com.vcb.common.WebUIKeyword.click"(
    	TestObject obj	) {
    (new com.vcb.common.WebUIKeyword()).click(
        	obj)
}


def static "com.vcb.common.WebUIKeyword.setText"(
    	TestObject obj	
     , 	String textContent	) {
    (new com.vcb.common.WebUIKeyword()).setText(
        	obj
         , 	textContent)
}


def static "com.vcb.common.WebUIKeyword.scrollToTestObject"(
    	TestObject obj	
     , 	int timeout	) {
    (new com.vcb.common.WebUIKeyword()).scrollToTestObject(
        	obj
         , 	timeout)
}


def static "com.vcb.common.WebUIKeyword.scrollToTestObject"(
    	TestObject obj	) {
    (new com.vcb.common.WebUIKeyword()).scrollToTestObject(
        	obj)
}


def static "com.vcb.common.WebUIKeyword.setTextWithClear"(
    	TestObject obj	
     , 	String textContent	
     , 	boolean isFastClearEnabled	) {
    (new com.vcb.common.WebUIKeyword()).setTextWithClear(
        	obj
         , 	textContent
         , 	isFastClearEnabled)
}


def static "com.vcb.common.WebUIKeyword.setTextWithClear"(
    	TestObject obj	
     , 	String textContent	) {
    (new com.vcb.common.WebUIKeyword()).setTextWithClear(
        	obj
         , 	textContent)
}


def static "com.vcb.common.WebUIKeyword.setEncryptedText"(
    	TestObject obj	
     , 	String textContent	) {
    (new com.vcb.common.WebUIKeyword()).setEncryptedText(
        	obj
         , 	textContent)
}


def static "com.vcb.common.WebUIKeyword.getText"(
    	TestObject obj	) {
    (new com.vcb.common.WebUIKeyword()).getText(
        	obj)
}


def static "com.vcb.common.WebUIKeyword.getAttributeValue"(
    	TestObject obj	
     , 	String att	) {
    (new com.vcb.common.WebUIKeyword()).getAttributeValue(
        	obj
         , 	att)
}


def static "com.vcb.common.WebUIKeyword.getAttributeValue"(
    	TestObject obj	) {
    (new com.vcb.common.WebUIKeyword()).getAttributeValue(
        	obj)
}


def static "com.vcb.common.WebUIKeyword.verifyElementVisible"(
    	TestObject obj	) {
    (new com.vcb.common.WebUIKeyword()).verifyElementVisible(
        	obj)
}


def static "com.vcb.common.WebUIKeyword.verifyElementPresent"(
    	TestObject obj	) {
    (new com.vcb.common.WebUIKeyword()).verifyElementPresent(
        	obj)
}


def static "com.vcb.common.WebUIKeyword.getTextsByXpaths"(
    	TestObject testObject	
     , 	List xpaths	
     , 	int timeout	) {
    (new com.vcb.common.WebUIKeyword()).getTextsByXpaths(
        	testObject
         , 	xpaths
         , 	timeout)
}


def static "com.vcb.common.WebUIKeyword.clickWithoutScrollW3C"(
    	TestObject obj	) {
    (new com.vcb.common.WebUIKeyword()).clickWithoutScrollW3C(
        	obj)
}


def static "com.vcb.utils.TouchActionUtils.compareUIFieldWithResponseField"(
    	List compareArr	
     , 	JsonObject data	
     , 	FailureHandling controlFlow	) {
    (new com.vcb.utils.TouchActionUtils()).compareUIFieldWithResponseField(
        	compareArr
         , 	data
         , 	controlFlow)
}


def static "com.vcb.utils.TouchActionUtils.updateAndVerifyElements"(
    	List lst	
     , 	String pathObject	
     , 	int timeout	) {
    (new com.vcb.utils.TouchActionUtils()).updateAndVerifyElements(
        	lst
         , 	pathObject
         , 	timeout)
}


def static "com.vcb.utils.TouchActionUtils.patternChecking"(
    	String testString	) {
    (new com.vcb.utils.TouchActionUtils()).patternChecking(
        	testString)
}


def static "com.vcb.utils.TouchActionUtils.compareUIFieldWithResponseField"(
    	List compareArr	
     , 	JsonObject data	) {
    (new com.vcb.utils.TouchActionUtils()).compareUIFieldWithResponseField(
        	compareArr
         , 	data)
}


def static "com.vcb.mobile.pages.Otp.enterOtp"(
    	FailureHandling flowControl	) {
    (new com.vcb.mobile.pages.Otp()).enterOtp(
        	flowControl)
}


def static "com.vcb.mobile.pages.Otp.enterPINNo"(
    	String pinNO	
     , 	FailureHandling flowControl	) {
    (new com.vcb.mobile.pages.Otp()).enterPINNo(
        	pinNO
         , 	flowControl)
}


def static "com.vcb.mobile.pages.Otp.enterOtp"() {
    (new com.vcb.mobile.pages.Otp()).enterOtp()
}


def static "com.vcb.mobile.pages.Otp.enterPINNo"(
    	String pinNO	) {
    (new com.vcb.mobile.pages.Otp()).enterPINNo(
        	pinNO)
}


def static "com.vcb.utils.DateUtils.dateValidator"(
    	String dateString	
     , 	String periodString	
     , 	int plusDay	) {
    (new com.vcb.utils.DateUtils()).dateValidator(
        	dateString
         , 	periodString
         , 	plusDay)
}


def static "com.vcb.utils.DateUtils.convertStringToCalendar"(
    	String pattern	
     , 	String dateString	) {
    (new com.vcb.utils.DateUtils()).convertStringToCalendar(
        	pattern
         , 	dateString)
}


def static "com.vcb.utils.DateUtils.getCurrentCalendar"(
    	String timeZone	) {
    (new com.vcb.utils.DateUtils()).getCurrentCalendar(
        	timeZone)
}


def static "com.vcb.utils.DateUtils.getCurrentDate"(
    	String timeZone	
     , 	String format	) {
    (new com.vcb.utils.DateUtils()).getCurrentDate(
        	timeZone
         , 	format)
}


def static "com.vcb.utils.DateUtils.convertToFormat"(
    	String dateInStr	
     , 	String fromFormat	
     , 	String toFormat	
     , 	FailureHandling controlFlow	) {
    (new com.vcb.utils.DateUtils()).convertToFormat(
        	dateInStr
         , 	fromFormat
         , 	toFormat
         , 	controlFlow)
}


def static "com.vcb.utils.DateUtils.getCurrentDateFormat"(
    	String pattern	
     , 	int daysToAdd	) {
    (new com.vcb.utils.DateUtils()).getCurrentDateFormat(
        	pattern
         , 	daysToAdd)
}


def static "com.vcb.utils.DateUtils.getCurrentMonth"() {
    (new com.vcb.utils.DateUtils()).getCurrentMonth()
}


def static "com.vcb.utils.DateUtils.getCurrentCalendar"() {
    (new com.vcb.utils.DateUtils()).getCurrentCalendar()
}


def static "com.vcb.utils.DateUtils.getCurrentDate"(
    	String timeZone	) {
    (new com.vcb.utils.DateUtils()).getCurrentDate(
        	timeZone)
}


def static "com.vcb.utils.DateUtils.getCurrentDate"() {
    (new com.vcb.utils.DateUtils()).getCurrentDate()
}


def static "com.vcb.utils.DateUtils.convertToFormat"(
    	String dateInStr	
     , 	String fromFormat	
     , 	String toFormat	) {
    (new com.vcb.utils.DateUtils()).convertToFormat(
        	dateInStr
         , 	fromFormat
         , 	toFormat)
}


def static "com.vcb.utils.DateUtils.convertToFormat"(
    	String dateInStr	
     , 	String fromFormat	) {
    (new com.vcb.utils.DateUtils()).convertToFormat(
        	dateInStr
         , 	fromFormat)
}


def static "com.vcb.utils.DateUtils.convertToFormat"(
    	String dateInStr	) {
    (new com.vcb.utils.DateUtils()).convertToFormat(
        	dateInStr)
}


def static "com.vcb.utils.DateUtils.getCurrentDateFormat"(
    	String pattern	) {
    (new com.vcb.utils.DateUtils()).getCurrentDateFormat(
        	pattern)
}


def static "com.vcb.utils.DateUtils.getCurrentDateFormat"() {
    (new com.vcb.utils.DateUtils()).getCurrentDateFormat()
}


def static "com.vcb.mobile.pages.Login.login"(
    	String username	
     , 	String password	
     , 	FailureHandling flowControl	) {
    (new com.vcb.mobile.pages.Login()).login(
        	username
         , 	password
         , 	flowControl)
}


def static "com.vcb.mobile.pages.Login.loginARR"(
    	FailureHandling flowControl	) {
    (new com.vcb.mobile.pages.Login()).loginARR(
        	flowControl)
}


def static "com.vcb.mobile.pages.Login.invalidSignin"(
    	String username	
     , 	String password	
     , 	FailureHandling flowControl	) {
    (new com.vcb.mobile.pages.Login()).invalidSignin(
        	username
         , 	password
         , 	flowControl)
}


def static "com.vcb.mobile.pages.Login.login"(
    	String username	
     , 	String password	) {
    (new com.vcb.mobile.pages.Login()).login(
        	username
         , 	password)
}


def static "com.vcb.mobile.pages.Login.loginARR"() {
    (new com.vcb.mobile.pages.Login()).loginARR()
}


def static "com.vcb.mobile.pages.Login.invalidSignin"(
    	String username	
     , 	String password	) {
    (new com.vcb.mobile.pages.Login()).invalidSignin(
        	username
         , 	password)
}

 /**
	 * Create a new excel file
	 *
	 * @param filePath path of excel file
	 */ 
def static "com.kms.katalon.keyword.excel.ExcelKeywords.createExcelFile"(
    	String filePath	) {
    (new com.kms.katalon.keyword.excel.ExcelKeywords()).createExcelFile(
        	filePath)
}

 /**
	 * Add a default sheet to workbook
	 *
	 * @param workbook the workbook that needs to add new default sheet
	 */ 
def static "com.kms.katalon.keyword.excel.ExcelKeywords.createExcelSheet"(
    	Workbook workbook	) {
    (new com.kms.katalon.keyword.excel.ExcelKeywords()).createExcelSheet(
        	workbook)
}

 /**
	 * Add a new sheet to workbook
	 *
	 * @param workbook the workbook that needs to add new sheet
	 * @param sheetName the name of new sheet
	 */ 
def static "com.kms.katalon.keyword.excel.ExcelKeywords.createExcelSheet"(
    	Workbook workbook	
     , 	String sheetName	) {
    (new com.kms.katalon.keyword.excel.ExcelKeywords()).createExcelSheet(
        	workbook
         , 	sheetName)
}

 /**
	 * Get name of sheets in workbook
	 *
	 * @param workbook
	 * @return list of sheet's name
	 */ 
def static "com.kms.katalon.keyword.excel.ExcelKeywords.getSheetNames"(
    	Workbook workbook	) {
    (new com.kms.katalon.keyword.excel.ExcelKeywords()).getSheetNames(
        	workbook)
}

 /**
	 * Add multiple sheets to workbook
	 *
	 * @param workbook the workbook that needs to add new sheets
	 * @param sheetNames the list of sheet names
	 */ 
def static "com.kms.katalon.keyword.excel.ExcelKeywords.createExcelSheets"(
    	Workbook workbook	
     , 	List sheetNames	) {
    (new com.kms.katalon.keyword.excel.ExcelKeywords()).createExcelSheets(
        	workbook
         , 	sheetNames)
}

 /**
	 * Get workbook from existing excel file
	 *
	 * @param filePath location path of excel file
	 * @return workbook
	 */ 
def static "com.kms.katalon.keyword.excel.ExcelKeywords.getWorkbook"(
    	String filePath	) {
    (new com.kms.katalon.keyword.excel.ExcelKeywords()).getWorkbook(
        	filePath)
}

 /**
	 * Create new workbook
	 *
	 * @param filePath location path of excel file
	 * @return new workbook
	 */ 
def static "com.kms.katalon.keyword.excel.ExcelKeywords.createWorkbook"(
    	String filePath	) {
    (new com.kms.katalon.keyword.excel.ExcelKeywords()).createWorkbook(
        	filePath)
}

 /**
	 * Save workbook into file
	 *
	 * @param filePath the location of excel file that will be written
	 * @param workbook the workbook contains data
	 */ 
def static "com.kms.katalon.keyword.excel.ExcelKeywords.saveWorkbook"(
    	String filePath	
     , 	Workbook workbook	) {
    (new com.kms.katalon.keyword.excel.ExcelKeywords()).saveWorkbook(
        	filePath
         , 	workbook)
}

 /**
	 * Set value to cell by cell index
	 *
	 * @param sheet sheet in workbook
	 * @param colIndex column index starts by 0
	 * @param rowIndex row index starts by 0
	 * @param value value with any data type (String, Numeric, Boolean, Date with format MM/dd/yyyy,
	 * 					formula with character "=" at the beginning of the string
	 * @Example setValueToCellByIndex(sheet, 1, 2, 123)
	 */ 
def static "com.kms.katalon.keyword.excel.ExcelKeywords.setValueToCellByIndex"(
    	Sheet sheet	
     , 	int rowIndex	
     , 	int colIndex	
     , 	Object value	) {
    (new com.kms.katalon.keyword.excel.ExcelKeywords()).setValueToCellByIndex(
        	sheet
         , 	rowIndex
         , 	colIndex
         , 	value)
}

 /**
	 * Set value to cell by cell Address
	 *
	 * @param sheet sheet in workbook
	 * @param cellAddress cell address in sheet
	 * @param value value with any data type (String, Numeric, Boolean, Date with format MM/dd/yyyy,
	 * 					formula with character "=" at the beginning of the string
	 * @Example setValueToCellByAddress(sheet, "A10", 123)
	 */ 
def static "com.kms.katalon.keyword.excel.ExcelKeywords.setValueToCellByAddress"(
    	Sheet sheet	
     , 	String cellAddress	
     , 	Object value	) {
    (new com.kms.katalon.keyword.excel.ExcelKeywords()).setValueToCellByAddress(
        	sheet
         , 	cellAddress
         , 	value)
}

 /**
	 * Set multiple values to multiple cells with cell addresses
	 *
	 * @param sheet sheet in workbook
	 * @param content map object with "key" = "cell address" and "value" = "value" with any data type
	 * 					(String, Numeric, Boolean, Date with format MM/dd/yyyy,
	 * 					formula with character "=" at the beginning of the string
	 * @Example
	 * 		content.putAt("C5",12)
	 * 		content.putAt("C6",true)
	 * 		setValueToCellByAddresses(sheet, content)
	 */ 
def static "com.kms.katalon.keyword.excel.ExcelKeywords.setValueToCellByAddresses"(
    	Sheet sheet	
     , 	Map content	) {
    (new com.kms.katalon.keyword.excel.ExcelKeywords()).setValueToCellByAddresses(
        	sheet
         , 	content)
}

 /**
	 * Get an Excel sheet
	 *
	 * @param filePath the location of excel file
	 * @param sheetIndex the index of returned sheet
	 * @return a sheet object
	 * */ 
def static "com.kms.katalon.keyword.excel.ExcelKeywords.getExcelSheet"(
    	String filePath	
     , 	int sheetIndex	) {
    (new com.kms.katalon.keyword.excel.ExcelKeywords()).getExcelSheet(
        	filePath
         , 	sheetIndex)
}

 /**
	 * Get an Excel sheet from an opening workbook
	 *
	 * @param wbs the opening workbook
	 * @param sheetName the sheet name of the workbook
	 * @return a sheet object.
	 * @example getExcelSheet(workbook1,"Sheet1")
	 * */ 
def static "com.kms.katalon.keyword.excel.ExcelKeywords.getExcelSheet"(
    	Workbook wbs	
     , 	String sheetName	) {
    (new com.kms.katalon.keyword.excel.ExcelKeywords()).getExcelSheet(
        	wbs
         , 	sheetName)
}

 /**
	 * Get an Excel sheet by name
	 *
	 * @param filePath the location of excel file
	 * @param sheetName the name of returned sheet
	 * @return a sheet object
	 * */ 
def static "com.kms.katalon.keyword.excel.ExcelKeywords.getExcelSheetByName"(
    	String filePath	
     , 	String sheetName	) {
    (new com.kms.katalon.keyword.excel.ExcelKeywords()).getExcelSheetByName(
        	filePath
         , 	sheetName)
}

 /**
	 * Get cell by index
	 *
	 * @param sheet the sheet object that contains cell
	 * @param rowIdx row index of the cell
	 * @param colIdx column index of the cell
	 * @return a cell object (this function returns null if the object is null)
	 * */ 
def static "com.kms.katalon.keyword.excel.ExcelKeywords.getCellByIndex"(
    	Sheet sheet	
     , 	int rowIdx	
     , 	int colIdx	) {
    (new com.kms.katalon.keyword.excel.ExcelKeywords()).getCellByIndex(
        	sheet
         , 	rowIdx
         , 	colIdx)
}

 /**
	 * Get cell by address
	 *
	 * @param sheet the sheet object that contains cell
	 * @param cellAddress the address of cell
	 * @return a cell object (this function returns null if the object is null)
	 * */ 
def static "com.kms.katalon.keyword.excel.ExcelKeywords.getCellByAddress"(
    	Sheet sheet	
     , 	String cellAddress	) {
    (new com.kms.katalon.keyword.excel.ExcelKeywords()).getCellByAddress(
        	sheet
         , 	cellAddress)
}

 /**
	 * Locate the first position of the cell based on the cell content
	 * @param sheet the sheet object contains the cell
	 * @param cellContent the content to locate the cell. If the cell is in Date format, the cellContent should be in (MM/dd/yyyy), e.g ("10/01/2019")
	 * @return a list of integer (row, column)
	 */ 
def static "com.kms.katalon.keyword.excel.ExcelKeywords.locateCell"(
    	Sheet sheet	
     , 	Object cellContent	) {
    (new com.kms.katalon.keyword.excel.ExcelKeywords()).locateCell(
        	sheet
         , 	cellContent)
}

 /**
	 * Read data from a single cell
	 *
	 * @param cell the cell that needs to read data
	 * @return the value of cell. If the cell is in date format, the returned value is in Date format "MM/dd/yyyy".
	 * */ 
def static "com.kms.katalon.keyword.excel.ExcelKeywords.getCellValue"(
    	Cell cell	) {
    (new com.kms.katalon.keyword.excel.ExcelKeywords()).getCellValue(
        	cell)
}

 /**
	 * Get cell value by index
	 *
	 * @param sheet the sheet object that contains cell
	 * @param rowIdx row index of the cell
	 * @param colIdx column index of the cell
	 * @return a cell value
	 * */ 
def static "com.kms.katalon.keyword.excel.ExcelKeywords.getCellValueByIndex"(
    	Sheet sheet	
     , 	int rowIdx	
     , 	int colIdx	) {
    (new com.kms.katalon.keyword.excel.ExcelKeywords()).getCellValueByIndex(
        	sheet
         , 	rowIdx
         , 	colIdx)
}

 /**
	 * Get cell position by address
	 *
	 * @param sheet the sheet object that contains cell
	 * @param cellAddress the address of cell
	 * @return a map contains position of the cell, e.g: ["column":2, "row": 1]
	 * */ 
def static "com.kms.katalon.keyword.excel.ExcelKeywords.getCellIndexByAddress"(
    	Sheet sheet	
     , 	String cellAddress	) {
    (new com.kms.katalon.keyword.excel.ExcelKeywords()).getCellIndexByAddress(
        	sheet
         , 	cellAddress)
}

 /**
	 * Get cell value by address
	 *
	 * @param sheet the sheet object that contains cell
	 * @param cellAddress the address of the cell
	 * @return a cell value
	 * */ 
def static "com.kms.katalon.keyword.excel.ExcelKeywords.getCellValueByAddress"(
    	Sheet sheet	
     , 	String cellAddress	) {
    (new com.kms.katalon.keyword.excel.ExcelKeywords()).getCellValueByAddress(
        	sheet
         , 	cellAddress)
}

 /**
	 * Get get last row
	 *
	 * @param sheet the sheet object that contains cell
	 * @param cellAddress the address of the cell
	 * @return a cell value
	 * */ 
def static "com.kms.katalon.keyword.excel.ExcelKeywords.getLastRow"(
    	Sheet sheet	) {
    (new com.kms.katalon.keyword.excel.ExcelKeywords()).getLastRow(
        	sheet)
}

 /**
	 * Get row index by cell content
	 *
	 * @param sheet the sheet object that contains the row
	 * @param cellContent the content of the cell
	 * @param columnIdxForCell the column index of the cell
	 * @return the row index
	 * */ 
def static "com.kms.katalon.keyword.excel.ExcelKeywords.getRowIndexByCellContent"(
    	Sheet sheet	
     , 	String cellContent	
     , 	int columnIdxForCell	) {
    (new com.kms.katalon.keyword.excel.ExcelKeywords()).getRowIndexByCellContent(
        	sheet
         , 	cellContent
         , 	columnIdxForCell)
}

 /**
	 * Get the excel data to a list of rows
	 *
	 * @param sheet the sheet object to get the data
	 * @param startRow the start row to get the data
	 * @param endRow the end row to get the data
	 * @return a list of rows
	 * */ 
def static "com.kms.katalon.keyword.excel.ExcelKeywords.getTableContent"(
    	Sheet sheet	
     , 	int startRow	
     , 	int endRow	) {
    (new com.kms.katalon.keyword.excel.ExcelKeywords()).getTableContent(
        	sheet
         , 	startRow
         , 	endRow)
}

 /**
	 * Get data rows from a list of row indexes
	 *
	 * @param sheet the sheet object contains the rows
	 * @param rowIndexes the list of row indexes
	 * @return a list of rows
	 * */ 
def static "com.kms.katalon.keyword.excel.ExcelKeywords.getDataRows"(
    	Sheet sheet	
     , 	List rowIndexs	) {
    (new com.kms.katalon.keyword.excel.ExcelKeywords()).getDataRows(
        	sheet
         , 	rowIndexs)
}

 /**
	 * Get a map of {{key1:value1},...{keyn:valuen}} from a row of keys and a row of values of a sheet
	 *
	 * @param sheet the sheet object that contains the row
	 * @param keyRowIdx the index of the header row
	 * @param valueRowIdx the index of row needs to get the data
	 * @return a map from a row of keys and a row of values of a sheet, e.g: {{key1:value1},...{keyn:valuen}}
	 * */ 
def static "com.kms.katalon.keyword.excel.ExcelKeywords.getMapOfKeyValueRows"(
    	Sheet sheet	
     , 	int keyRowIdx	
     , 	int valueRowIdx	) {
    (new com.kms.katalon.keyword.excel.ExcelKeywords()).getMapOfKeyValueRows(
        	sheet
         , 	keyRowIdx
         , 	valueRowIdx)
}

 /**
	 * Get cell values by addresses
	 *
	 * @param sheet the sheet object that contains cells
	 * @param topLeftAddress the address of the first cell
	 * @param rightBottomAddress the address of the last cell
	 * @return a list of cell values
	 * */ 
def static "com.kms.katalon.keyword.excel.ExcelKeywords.getCellValueByRangeAddress"(
    	Sheet sheet	
     , 	String topLeftAddress	
     , 	String rightBottomAddress	) {
    (new com.kms.katalon.keyword.excel.ExcelKeywords()).getCellValueByRangeAddress(
        	sheet
         , 	topLeftAddress
         , 	rightBottomAddress)
}

 /**
	 * Get cell values by indexes
	 *
	 * @param sheet the sheet object that contains cells
	 * @param rowInd1 the row index of the first cell
	 * @param colInd1 the column index of the first cell
	 * @param rowInd2 the row index of the last cell
	 * @param colInd2 the column index of the last cell
	 * @return a list of cell values
	 * */ 
def static "com.kms.katalon.keyword.excel.ExcelKeywords.getCellValuesByRangeIndexes"(
    	Sheet sheet	
     , 	int rowInd1	
     , 	int colInd1	
     , 	int rowInd2	
     , 	int colInd2	) {
    (new com.kms.katalon.keyword.excel.ExcelKeywords()).getCellValuesByRangeIndexes(
        	sheet
         , 	rowInd1
         , 	colInd1
         , 	rowInd2
         , 	colInd2)
}

 /**
	 * Get map of columns data
	 *
	 * @param sheet the sheet object that contains the row
	 * @param columnIndexes indexes of columns
	 * @return map of a columns data contains the column index and cell data
	 * */ 
def static "com.kms.katalon.keyword.excel.ExcelKeywords.getColumnsByIndex"(
    	Sheet sheet	
     , 	List columnIndexes	) {
    (new com.kms.katalon.keyword.excel.ExcelKeywords()).getColumnsByIndex(
        	sheet
         , 	columnIndexes)
}

 /**
	 * Compare value of two excel workbooks
	 *
	 * @param workbook1 the first workbook need to compare
	 * @param workbook2 the second workbook need to compare
	 * @param isValueOnly true if check value and type only regardless of cell style, false if not
	 * @return true if two excel workbooks equal, false if not
	 * */ 
def static "com.kms.katalon.keyword.excel.ExcelKeywords.compareTwoExcels"(
    	Workbook workbook1	
     , 	Workbook workbook2	
     , 	boolean isValueOnly	) {
    (new com.kms.katalon.keyword.excel.ExcelKeywords()).compareTwoExcels(
        	workbook1
         , 	workbook2
         , 	isValueOnly)
}

 /**
	 * Compare two excel sheets
	 *
	 * @param sheet1 the first sheet need to compare
	 * @param sheet2 the second sheet need to compare
	 * @param isValueOnly true if check value and type only regardless of cell style, false if not
	 * @return true if two excel sheets equal, false if not
	 * */ 
def static "com.kms.katalon.keyword.excel.ExcelKeywords.compareTwoSheets"(
    	Sheet sheet1	
     , 	Sheet sheet2	
     , 	boolean isValueOnly	) {
    (new com.kms.katalon.keyword.excel.ExcelKeywords()).compareTwoSheets(
        	sheet1
         , 	sheet2
         , 	isValueOnly)
}

 /**
	 * Compare two excel rows
	 *
	 * @param row1 the first row need to compare
	 * @param row2 the second row need to compare
	 * @param isValueOnly true if check value and type only regardless of cell style, false if not
	 * @return true if two excel rows equal, false if not
	 * */ 
def static "com.kms.katalon.keyword.excel.ExcelKeywords.compareTwoRows"(
    	Row row1	
     , 	Row row2	
     , 	boolean isValueOnly	) {
    (new com.kms.katalon.keyword.excel.ExcelKeywords()).compareTwoRows(
        	row1
         , 	row2
         , 	isValueOnly)
}

 /**
	 * Compare two excel cells
	 *
	 * @param cell1 the first cell need to compare
	 * @param cell2 the second cell need to compare
	 * @param isValueOnly true if check value and type only regardless of cell style, false if not
	 * @return true if two excel cells equal, false if not
	 * */ 
def static "com.kms.katalon.keyword.excel.ExcelKeywords.compareTwoCells"(
    	Cell cell1	
     , 	Cell cell2	
     , 	boolean isValueOnly	) {
    (new com.kms.katalon.keyword.excel.ExcelKeywords()).compareTwoCells(
        	cell1
         , 	cell2
         , 	isValueOnly)
}


def static "com.kms.katalon.keyword.excel.ExcelKeywords.getExcelSheet"(
    	String filePath	) {
    (new com.kms.katalon.keyword.excel.ExcelKeywords()).getExcelSheet(
        	filePath)
}


def static "com.kms.katalon.keyword.excel.ExcelKeywords.getExcelSheet"(
    	Workbook wbs	) {
    (new com.kms.katalon.keyword.excel.ExcelKeywords()).getExcelSheet(
        	wbs)
}


def static "com.kms.katalon.keyword.excel.ExcelKeywords.compareTwoExcels"(
    	Workbook workbook1	
     , 	Workbook workbook2	) {
    (new com.kms.katalon.keyword.excel.ExcelKeywords()).compareTwoExcels(
        	workbook1
         , 	workbook2)
}


def static "com.kms.katalon.keyword.excel.ExcelKeywords.compareTwoSheets"(
    	Sheet sheet1	
     , 	Sheet sheet2	) {
    (new com.kms.katalon.keyword.excel.ExcelKeywords()).compareTwoSheets(
        	sheet1
         , 	sheet2)
}


def static "com.kms.katalon.keyword.excel.ExcelKeywords.compareTwoRows"(
    	Row row1	
     , 	Row row2	) {
    (new com.kms.katalon.keyword.excel.ExcelKeywords()).compareTwoRows(
        	row1
         , 	row2)
}


def static "com.kms.katalon.keyword.excel.ExcelKeywords.compareTwoCells"(
    	Cell cell1	
     , 	Cell cell2	) {
    (new com.kms.katalon.keyword.excel.ExcelKeywords()).compareTwoCells(
        	cell1
         , 	cell2)
}

 /**
	 * Creates a TestCloud Windows ChromeDriver instance.
	 *
	 * @param apiKey         The API key for authenticating with TestCloud.
	 * @param orgId          The organization ID associated with TestCloud.
	 * @param browserVersion The desired browser version (default: "131").
	 * @return A configured WebDriver instance.
	 * @throws Exception If an error occurs while initializing the driver.
	 */ 
def static "com.vcb.helper.TestCloudHelper.createTestCloudWindowsChromeDriver"() {
    (new com.vcb.helper.TestCloudHelper()).createTestCloudWindowsChromeDriver()
}


def static "com.vcb.helper.TestCloudHelper.closeTestCloudBrowser"() {
    (new com.vcb.helper.TestCloudHelper()).closeTestCloudBrowser()
}


def static "com.vcb.mobile.pages.Pin.enterPin"(
    	FailureHandling flowControl	) {
    (new com.vcb.mobile.pages.Pin()).enterPin(
        	flowControl)
}


def static "com.vcb.mobile.pages.Pin.enterPin"() {
    (new com.vcb.mobile.pages.Pin()).enterPin()
}


def static "com.vcb.mobile.pages.Home.clickHomeButton"(
    	FailureHandling flowControl	) {
    (new com.vcb.mobile.pages.Home()).clickHomeButton(
        	flowControl)
}


def static "com.vcb.mobile.pages.Home.verifyProfileName"(
    	String text	
     , 	FailureHandling flowControl	) {
    (new com.vcb.mobile.pages.Home()).verifyProfileName(
        	text
         , 	flowControl)
}


def static "com.vcb.mobile.pages.Home.verifyMaskingPhoneNumber"(
    	FailureHandling flowControl	) {
    (new com.vcb.mobile.pages.Home()).verifyMaskingPhoneNumber(
        	flowControl)
}


def static "com.vcb.mobile.pages.Home.verifyReferralCode"(
    	String text	
     , 	FailureHandling flowControl	) {
    (new com.vcb.mobile.pages.Home()).verifyReferralCode(
        	text
         , 	flowControl)
}


def static "com.vcb.mobile.pages.Home.verifyViewAllAccountsButtonVisible"(
    	FailureHandling flowControl	) {
    (new com.vcb.mobile.pages.Home()).verifyViewAllAccountsButtonVisible(
        	flowControl)
}


def static "com.vcb.mobile.pages.Home.verifyNotificationButtonVisible"(
    	FailureHandling flowControl	) {
    (new com.vcb.mobile.pages.Home()).verifyNotificationButtonVisible(
        	flowControl)
}


def static "com.vcb.mobile.pages.Home.verifyQuickActionstextVisible"(
    	FailureHandling flowControl	) {
    (new com.vcb.mobile.pages.Home()).verifyQuickActionstextVisible(
        	flowControl)
}


def static "com.vcb.mobile.pages.Home.verifyCustomiseButtonVisible"(
    	FailureHandling flowControl	) {
    (new com.vcb.mobile.pages.Home()).verifyCustomiseButtonVisible(
        	flowControl)
}


def static "com.vcb.mobile.pages.Home.verifySamaSamaLocalVisible"(
    	FailureHandling flowControl	) {
    (new com.vcb.mobile.pages.Home()).verifySamaSamaLocalVisible(
        	flowControl)
}


def static "com.vcb.mobile.pages.Home.verifyTabungVisible"(
    	FailureHandling flowControl	) {
    (new com.vcb.mobile.pages.Home()).verifyTabungVisible(
        	flowControl)
}


def static "com.vcb.mobile.pages.Home.verifyDealsForYouVisible"(
    	FailureHandling flowControl	) {
    (new com.vcb.mobile.pages.Home()).verifyDealsForYouVisible(
        	flowControl)
}


def static "com.vcb.mobile.pages.Home.verifyTopReadsVisible"(
    	FailureHandling flowControl	) {
    (new com.vcb.mobile.pages.Home()).verifyTopReadsVisible(
        	flowControl)
}


def static "com.vcb.mobile.pages.Home.verifyManageWidgetsVisible"(
    	FailureHandling flowControl	) {
    (new com.vcb.mobile.pages.Home()).verifyManageWidgetsVisible(
        	flowControl)
}


def static "com.vcb.mobile.pages.Home.verifySpendingSummaryVisible"(
    	FailureHandling flowControl	) {
    (new com.vcb.mobile.pages.Home()).verifySpendingSummaryVisible(
        	flowControl)
}


def static "com.vcb.mobile.pages.Home.verifySurveyBannerButtonVisible"(
    	FailureHandling flowControl	) {
    (new com.vcb.mobile.pages.Home()).verifySurveyBannerButtonVisible(
        	flowControl)
}


def static "com.vcb.mobile.pages.Home.clickLogout"(
    	FailureHandling flowControl	) {
    (new com.vcb.mobile.pages.Home()).clickLogout(
        	flowControl)
}


def static "com.vcb.mobile.pages.Home.clickEyeButton"(
    	FailureHandling flowControl	) {
    (new com.vcb.mobile.pages.Home()).clickEyeButton(
        	flowControl)
}


def static "com.vcb.mobile.pages.Home.clickCloseButton"(
    	FailureHandling flowControl	) {
    (new com.vcb.mobile.pages.Home()).clickCloseButton(
        	flowControl)
}


def static "com.vcb.mobile.pages.Home.hideBalance"(
    	FailureHandling flowControl	) {
    (new com.vcb.mobile.pages.Home()).hideBalance(
        	flowControl)
}


def static "com.vcb.mobile.pages.Home.clickViewAllAccountsButton"(
    	FailureHandling flowControl	) {
    (new com.vcb.mobile.pages.Home()).clickViewAllAccountsButton(
        	flowControl)
}


def static "com.vcb.mobile.pages.Home.verifytextWelcomeBack"(
    	FailureHandling flowControl	) {
    (new com.vcb.mobile.pages.Home()).verifytextWelcomeBack(
        	flowControl)
}


def static "com.vcb.mobile.pages.Home.clickLoginButton"(
    	FailureHandling flowControl	) {
    (new com.vcb.mobile.pages.Home()).clickLoginButton(
        	flowControl)
}


def static "com.vcb.mobile.pages.Home.verifyAccountBalanceNotDisplayed"(
    	FailureHandling flowControl	) {
    (new com.vcb.mobile.pages.Home()).verifyAccountBalanceNotDisplayed(
        	flowControl)
}


def static "com.vcb.mobile.pages.Home.verifyTotalSpendingVisible"(
    	FailureHandling flowControl	) {
    (new com.vcb.mobile.pages.Home()).verifyTotalSpendingVisible(
        	flowControl)
}


def static "com.vcb.mobile.pages.Home.verifySpendingSummaryIsNotDisplayed"(
    	FailureHandling flowControl	) {
    (new com.vcb.mobile.pages.Home()).verifySpendingSummaryIsNotDisplayed(
        	flowControl)
}


def static "com.vcb.mobile.pages.Home.verifyTextExpensesVisible"(
    	FailureHandling flowControl	) {
    (new com.vcb.mobile.pages.Home()).verifyTextExpensesVisible(
        	flowControl)
}


def static "com.vcb.mobile.pages.Home.verifyCurrentMonthVisible"(
    	FailureHandling flowControl	) {
    (new com.vcb.mobile.pages.Home()).verifyCurrentMonthVisible(
        	flowControl)
}


def static "com.vcb.mobile.pages.Home.verifyTextSpentSoFar"(
    	FailureHandling flowControl	) {
    (new com.vcb.mobile.pages.Home()).verifyTextSpentSoFar(
        	flowControl)
}


def static "com.vcb.mobile.pages.Home.verifySpendingSummaryIsDisplayed"(
    	FailureHandling flowControl	) {
    (new com.vcb.mobile.pages.Home()).verifySpendingSummaryIsDisplayed(
        	flowControl)
}


def static "com.vcb.mobile.pages.Home.verifyTotalMonthlySpendingVisible"(
    	FailureHandling flowControl	) {
    (new com.vcb.mobile.pages.Home()).verifyTotalMonthlySpendingVisible(
        	flowControl)
}


def static "com.vcb.mobile.pages.Home.tapTopReadsLink"(
    	FailureHandling flowControl	) {
    (new com.vcb.mobile.pages.Home()).tapTopReadsLink(
        	flowControl)
}


def static "com.vcb.mobile.pages.Home.tapHeartSymbolButton"(
    	FailureHandling flowControl	) {
    (new com.vcb.mobile.pages.Home()).tapHeartSymbolButton(
        	flowControl)
}


def static "com.vcb.mobile.pages.Home.readLikeCount"(
    	FailureHandling flowControl	) {
    (new com.vcb.mobile.pages.Home()).readLikeCount(
        	flowControl)
}


def static "com.vcb.mobile.pages.Home.verifyLikeCountIsIncreamentedbyOne"(
    	FailureHandling flowControl	) {
    (new com.vcb.mobile.pages.Home()).verifyLikeCountIsIncreamentedbyOne(
        	flowControl)
}


def static "com.vcb.mobile.pages.Home.verifyLikeCountIsIncreamentedbyOneInHomePageTopReads"(
    	Object flowControl	) {
    (new com.vcb.mobile.pages.Home()).verifyLikeCountIsIncreamentedbyOneInHomePageTopReads(
        	flowControl)
}


def static "com.vcb.mobile.pages.Home.clickAccountsButton"(
    	Object flowControl	) {
    (new com.vcb.mobile.pages.Home()).clickAccountsButton(
        	flowControl)
}


def static "com.vcb.mobile.pages.Home.clickSettingsButton"(
    	Object flowControl	) {
    (new com.vcb.mobile.pages.Home()).clickSettingsButton(
        	flowControl)
}


def static "com.vcb.mobile.pages.Home.clickHomeButton"() {
    (new com.vcb.mobile.pages.Home()).clickHomeButton()
}


def static "com.vcb.mobile.pages.Home.verifyProfileName"(
    	String text	) {
    (new com.vcb.mobile.pages.Home()).verifyProfileName(
        	text)
}


def static "com.vcb.mobile.pages.Home.verifyMaskingPhoneNumber"() {
    (new com.vcb.mobile.pages.Home()).verifyMaskingPhoneNumber()
}


def static "com.vcb.mobile.pages.Home.verifyReferralCode"(
    	String text	) {
    (new com.vcb.mobile.pages.Home()).verifyReferralCode(
        	text)
}


def static "com.vcb.mobile.pages.Home.verifyViewAllAccountsButtonVisible"() {
    (new com.vcb.mobile.pages.Home()).verifyViewAllAccountsButtonVisible()
}


def static "com.vcb.mobile.pages.Home.verifyNotificationButtonVisible"() {
    (new com.vcb.mobile.pages.Home()).verifyNotificationButtonVisible()
}


def static "com.vcb.mobile.pages.Home.verifyQuickActionstextVisible"() {
    (new com.vcb.mobile.pages.Home()).verifyQuickActionstextVisible()
}


def static "com.vcb.mobile.pages.Home.verifyCustomiseButtonVisible"() {
    (new com.vcb.mobile.pages.Home()).verifyCustomiseButtonVisible()
}


def static "com.vcb.mobile.pages.Home.verifySamaSamaLocalVisible"() {
    (new com.vcb.mobile.pages.Home()).verifySamaSamaLocalVisible()
}


def static "com.vcb.mobile.pages.Home.verifyTabungVisible"() {
    (new com.vcb.mobile.pages.Home()).verifyTabungVisible()
}


def static "com.vcb.mobile.pages.Home.verifyDealsForYouVisible"() {
    (new com.vcb.mobile.pages.Home()).verifyDealsForYouVisible()
}


def static "com.vcb.mobile.pages.Home.verifyTopReadsVisible"() {
    (new com.vcb.mobile.pages.Home()).verifyTopReadsVisible()
}


def static "com.vcb.mobile.pages.Home.verifyManageWidgetsVisible"() {
    (new com.vcb.mobile.pages.Home()).verifyManageWidgetsVisible()
}


def static "com.vcb.mobile.pages.Home.verifySpendingSummaryVisible"() {
    (new com.vcb.mobile.pages.Home()).verifySpendingSummaryVisible()
}


def static "com.vcb.mobile.pages.Home.verifySurveyBannerButtonVisible"() {
    (new com.vcb.mobile.pages.Home()).verifySurveyBannerButtonVisible()
}


def static "com.vcb.mobile.pages.Home.clickLogout"() {
    (new com.vcb.mobile.pages.Home()).clickLogout()
}


def static "com.vcb.mobile.pages.Home.clickEyeButton"() {
    (new com.vcb.mobile.pages.Home()).clickEyeButton()
}


def static "com.vcb.mobile.pages.Home.clickCloseButton"() {
    (new com.vcb.mobile.pages.Home()).clickCloseButton()
}

 /**
	 * openApp
	 * @param appPackage
	 * @return
	 */ 
def static "com.vcb.common.MobileDriver.openApp"(
    	String appPackage	) {
    (new com.vcb.common.MobileDriver()).openApp(
        	appPackage)
}


def static "com.vcb.common.MobileDriver.takeScreenshot"(
    	FailureHandling flowControl	) {
    (new com.vcb.common.MobileDriver()).takeScreenshot(
        	flowControl)
}

 /**
	 * VerifyElementExist
	 * @param testObject
	 * @param timeout
	 * @return
	 */ 
def static "com.vcb.common.MobileDriver.activateApp"(
    	String appID	
     , 	FailureHandling flowControl	) {
    (new com.vcb.common.MobileDriver()).activateApp(
        	appID
         , 	flowControl)
}

 /**
	 * VerifyElementExist
	 * @param testObject
	 * @param timeout
	 * @return
	 */ 
def static "com.vcb.common.MobileDriver.verifyElementExist"(
    	MobileTestObject obj	
     , 	int timeout	
     , 	FailureHandling flowControl	) {
    (new com.vcb.common.MobileDriver()).verifyElementExist(
        	obj
         , 	timeout
         , 	flowControl)
}

 /**
	 * VerifyElementExist
	 * @param testObject
	 * @param timeout
	 * @return
	 */ 
def static "com.vcb.common.MobileDriver.verifyElementVisible"(
    	TestObject obj	
     , 	int timeout	
     , 	FailureHandling flowControl	) {
    (new com.vcb.common.MobileDriver()).verifyElementVisible(
        	obj
         , 	timeout
         , 	flowControl)
}

 /**
	 * VerifyElementExist
	 * @param testObject
	 * @param timeout
	 * @return
	 */ 
def static "com.vcb.common.MobileDriver.verifyElementNotVisible"(
    	TestObject obj	
     , 	int timeout	
     , 	FailureHandling flowControl	) {
    (new com.vcb.common.MobileDriver()).verifyElementNotVisible(
        	obj
         , 	timeout
         , 	flowControl)
}

 /**
	 * dragAndDrop
	 * @param testObject
	 * @param timeout
	 * @return
	 */ 
def static "com.vcb.common.MobileDriver.dragAndDrop"(
    	TestObject fromObject	
     , 	TestObject toObject	
     , 	int timeout	
     , 	FailureHandling flowControl	) {
    (new com.vcb.common.MobileDriver()).dragAndDrop(
        	fromObject
         , 	toObject
         , 	timeout
         , 	flowControl)
}

 /**
	 * WaitForElementPresent
	 * @param testObject
	 * @param timeout
	 * @return
	 */ 
def static "com.vcb.common.MobileDriver.waitForElementPresent"(
    	TestObject obj	
     , 	int timeout	
     , 	FailureHandling flowControl	) {
    (new com.vcb.common.MobileDriver()).waitForElementPresent(
        	obj
         , 	timeout
         , 	flowControl)
}

 /**
	 * Send key events
	 * @param input
	 * @return
	 */ 
def static "com.vcb.common.MobileDriver.setTextNoObjAppium"(
    	String input	) {
    (new com.vcb.common.MobileDriver()).setTextNoObjAppium(
        	input)
}

 /**
	 * WaitForElementNotPresent
	 * @param testObject
	 * @param timeout
	 * @return
	 */ 
def static "com.vcb.common.MobileDriver.waitForElementNotPresent"(
    	TestObject obj	
     , 	int timeout	
     , 	FailureHandling flowControl	) {
    (new com.vcb.common.MobileDriver()).waitForElementNotPresent(
        	obj
         , 	timeout
         , 	flowControl)
}

 /**
	 * Tap TestObject
	 * @param testObject
	 * @param timeout
	 * @return
	 */ 
def static "com.vcb.common.MobileDriver.tap"(
    	TestObject obj	
     , 	int timeout	
     , 	FailureHandling flowControl	) {
    (new com.vcb.common.MobileDriver()).tap(
        	obj
         , 	timeout
         , 	flowControl)
}

 /**
	 * doubleTap TestObject
	 * @param testObject
	 * @param timeout
	 * @return
	 */ 
def static "com.vcb.common.MobileDriver.doubleTap"(
    	TestObject obj	
     , 	int timeout	
     , 	FailureHandling flowControl	) {
    (new com.vcb.common.MobileDriver()).doubleTap(
        	obj
         , 	timeout
         , 	flowControl)
}

 /**
	 * Tap TestObject
	 * @param testObject
	 * @param timeout
	 * @param log
	 * @return
	 */ 
def static "com.vcb.common.MobileDriver.tapAndLog"(
    	TestObject obj	
     , 	int timeout	
     , 	String log	
     , 	FailureHandling flowControl	) {
    (new com.vcb.common.MobileDriver()).tapAndLog(
        	obj
         , 	timeout
         , 	log
         , 	flowControl)
}

 /**
	 * Tap at Position
	 * @param testObject
	 * @param timeout
	 * @return
	 */ 
def static "com.vcb.common.MobileDriver.tapAtPosition"(
    	TestObject obj	
     , 	int timeout	
     , 	FailureHandling flowControl	) {
    (new com.vcb.common.MobileDriver()).tapAtPosition(
        	obj
         , 	timeout
         , 	flowControl)
}

 /**
	 * Tap at Position with Offset
	 * @param testObject The Test Object to locate the element
	 * @param timeout The maximum time to wait for the element to be present
	 * @param offsetX The X-axis offset for the tap position
	 * @param offsetY The Y-axis offset for the tap position
	 * @param flowControl Optional FailureHandling for error control
	 */ 
def static "com.vcb.common.MobileDriver.tapAtPositionWithOffset"(
    	TestObject obj	
     , 	int timeout	
     , 	int offsetX	
     , 	int offsetY	
     , 	FailureHandling flowControl	) {
    (new com.vcb.common.MobileDriver()).tapAtPositionWithOffset(
        	obj
         , 	timeout
         , 	offsetX
         , 	offsetY
         , 	flowControl)
}


def static "com.vcb.common.MobileDriver.setTextAndDone"(
    	TestObject obj	
     , 	String textContent	
     , 	int timeout	
     , 	FailureHandling flowControl	) {
    (new com.vcb.common.MobileDriver()).setTextAndDone(
        	obj
         , 	textContent
         , 	timeout
         , 	flowControl)
}

 /**
	 * Set Text
	 * @param input
	 * @return
	 */ 
def static "com.vcb.common.MobileDriver.setText"(
    	TestObject obj	
     , 	String textContent	
     , 	int timeout	
     , 	FailureHandling flowControl	) {
    (new com.vcb.common.MobileDriver()).setText(
        	obj
         , 	textContent
         , 	timeout
         , 	flowControl)
}

 /**
	 * Set Text
	 * @param input
	 * @return
	 */ 
def static "com.vcb.common.MobileDriver.checkHideKeyBoard"(
    	FailureHandling flowControl	) {
    (new com.vcb.common.MobileDriver()).checkHideKeyBoard(
        	flowControl)
}

 /**
	 * Set Text Simply
	 * @param input
	 * @return
	 */ 
def static "com.vcb.common.MobileDriver.setTextSimply"(
    	TestObject obj	
     , 	String textContent	
     , 	int timeout	
     , 	FailureHandling flowControl	) {
    (new com.vcb.common.MobileDriver()).setTextSimply(
        	obj
         , 	textContent
         , 	timeout
         , 	flowControl)
}

 /**
	 * Set Encrypted Text
	 * @param input
	 * @return
	 */ 
def static "com.vcb.common.MobileDriver.setEncryptedText"(
    	TestObject obj	
     , 	String textContent	
     , 	int timeout	
     , 	FailureHandling flowControl	) {
    (new com.vcb.common.MobileDriver()).setEncryptedText(
        	obj
         , 	textContent
         , 	timeout
         , 	flowControl)
}

 /**
	 * get Text
	 * @param Test Object
	 * @param timeout
	 * @return
	 */ 
def static "com.vcb.common.MobileDriver.getText"(
    	TestObject obj	
     , 	int timeout	
     , 	FailureHandling flowControl	) {
    (new com.vcb.common.MobileDriver()).getText(
        	obj
         , 	timeout
         , 	flowControl)
}

 /**
	 * Scroll until element is visible
	 * @param obj TestObject
	 * @param maxSwipe number of swipe attempts
	 * @param directionUp true = scroll up, false = scroll down
	 */ 
def static "com.vcb.common.MobileDriver.scrollTo"(
    	TestObject obj	
     , 	int maxSwipe	
     , 	boolean directionUp	
     , 	FailureHandling flowControl	) {
    (new com.vcb.common.MobileDriver()).scrollTo(
        	obj
         , 	maxSwipe
         , 	directionUp
         , 	flowControl)
}


def static "com.vcb.common.MobileDriver.checkElementAndScroll"(
    	TestObject obj	
     , 	int length	
     , 	boolean directionUp	
     , 	FailureHandling flowControl	) {
    (new com.vcb.common.MobileDriver()).checkElementAndScroll(
        	obj
         , 	length
         , 	directionUp
         , 	flowControl)
}


def static "com.vcb.common.MobileDriver.scrollToTop"(
    	List scrollPosition	) {
    (new com.vcb.common.MobileDriver()).scrollToTop(
        	scrollPosition)
}


def static "com.vcb.common.MobileDriver.scrollToEnd"() {
    (new com.vcb.common.MobileDriver()).scrollToEnd()
}


def static "com.vcb.common.MobileDriver.scrollToText"(
    	String text	
     , 	FailureHandling flowControl	) {
    (new com.vcb.common.MobileDriver()).scrollToText(
        	text
         , 	flowControl)
}


def static "com.vcb.common.MobileDriver.tapEleByTestObject"(
    	TestObject to	) {
    (new com.vcb.common.MobileDriver()).tapEleByTestObject(
        	to)
}


def static "com.vcb.common.MobileDriver.waitForElementByXpathTestObject"(
    	TestObject to	) {
    (new com.vcb.common.MobileDriver()).waitForElementByXpathTestObject(
        	to)
}


def static "com.vcb.common.MobileDriver.tapElemtByXml"(
    	String xpath	
     , 	String cacheKey	
     , 	FailureHandling flowControl	) {
    (new com.vcb.common.MobileDriver()).tapElemtByXml(
        	xpath
         , 	cacheKey
         , 	flowControl)
}


def static "com.vcb.common.MobileDriver.verifyMatch"(
    	TestObject testObject	
     , 	String regexMatch	
     , 	int timeout	
     , 	FailureHandling flowControl	) {
    (new com.vcb.common.MobileDriver()).verifyMatch(
        	testObject
         , 	regexMatch
         , 	timeout
         , 	flowControl)
}


def static "com.vcb.common.MobileDriver.close"(
    	Object appPackage	) {
    (new com.vcb.common.MobileDriver()).close(
        	appPackage)
}


def static "com.vcb.common.MobileDriver.getPageSource"() {
    (new com.vcb.common.MobileDriver()).getPageSource()
}


def static "com.vcb.common.MobileDriver.randomInteger"(
    	int minNumber	
     , 	int maxNumber	) {
    (new com.vcb.common.MobileDriver()).randomInteger(
        	minNumber
         , 	maxNumber)
}


def static "com.vcb.common.MobileDriver.enterPinNo"(
    	String pinNO	) {
    (new com.vcb.common.MobileDriver()).enterPinNo(
        	pinNO)
}


def static "com.vcb.common.MobileDriver.takeScreenshot"() {
    (new com.vcb.common.MobileDriver()).takeScreenshot()
}


def static "com.vcb.common.MobileDriver.activateApp"(
    	String appID	) {
    (new com.vcb.common.MobileDriver()).activateApp(
        	appID)
}


def static "com.vcb.common.MobileDriver.verifyElementExist"(
    	MobileTestObject obj	
     , 	int timeout	) {
    (new com.vcb.common.MobileDriver()).verifyElementExist(
        	obj
         , 	timeout)
}


def static "com.vcb.common.MobileDriver.verifyElementVisible"(
    	TestObject obj	
     , 	int timeout	) {
    (new com.vcb.common.MobileDriver()).verifyElementVisible(
        	obj
         , 	timeout)
}


def static "com.vcb.common.MobileDriver.verifyElementNotVisible"(
    	TestObject obj	
     , 	int timeout	) {
    (new com.vcb.common.MobileDriver()).verifyElementNotVisible(
        	obj
         , 	timeout)
}


def static "com.vcb.common.MobileDriver.dragAndDrop"(
    	TestObject fromObject	
     , 	TestObject toObject	
     , 	int timeout	) {
    (new com.vcb.common.MobileDriver()).dragAndDrop(
        	fromObject
         , 	toObject
         , 	timeout)
}


def static "com.vcb.common.MobileDriver.waitForElementPresent"(
    	TestObject obj	
     , 	int timeout	) {
    (new com.vcb.common.MobileDriver()).waitForElementPresent(
        	obj
         , 	timeout)
}


def static "com.vcb.common.MobileDriver.waitForElementNotPresent"(
    	TestObject obj	
     , 	int timeout	) {
    (new com.vcb.common.MobileDriver()).waitForElementNotPresent(
        	obj
         , 	timeout)
}


def static "com.vcb.common.MobileDriver.tap"(
    	TestObject obj	
     , 	int timeout	) {
    (new com.vcb.common.MobileDriver()).tap(
        	obj
         , 	timeout)
}


def static "com.vcb.common.MobileDriver.doubleTap"(
    	TestObject obj	
     , 	int timeout	) {
    (new com.vcb.common.MobileDriver()).doubleTap(
        	obj
         , 	timeout)
}


def static "com.vcb.common.MobileDriver.tapAndLog"(
    	TestObject obj	
     , 	int timeout	
     , 	String log	) {
    (new com.vcb.common.MobileDriver()).tapAndLog(
        	obj
         , 	timeout
         , 	log)
}


def static "com.vcb.common.MobileDriver.tapAtPosition"(
    	TestObject obj	
     , 	int timeout	) {
    (new com.vcb.common.MobileDriver()).tapAtPosition(
        	obj
         , 	timeout)
}


def static "com.vcb.common.MobileDriver.tapAtPositionWithOffset"(
    	TestObject obj	
     , 	int timeout	
     , 	int offsetX	
     , 	int offsetY	) {
    (new com.vcb.common.MobileDriver()).tapAtPositionWithOffset(
        	obj
         , 	timeout
         , 	offsetX
         , 	offsetY)
}


def static "com.vcb.common.MobileDriver.tapAtPositionWithOffset"(
    	TestObject obj	
     , 	int timeout	
     , 	int offsetX	) {
    (new com.vcb.common.MobileDriver()).tapAtPositionWithOffset(
        	obj
         , 	timeout
         , 	offsetX)
}


def static "com.vcb.common.MobileDriver.tapAtPositionWithOffset"(
    	TestObject obj	
     , 	int timeout	) {
    (new com.vcb.common.MobileDriver()).tapAtPositionWithOffset(
        	obj
         , 	timeout)
}


def static "com.vcb.common.MobileDriver.setTextAndDone"(
    	TestObject obj	
     , 	String textContent	
     , 	int timeout	) {
    (new com.vcb.common.MobileDriver()).setTextAndDone(
        	obj
         , 	textContent
         , 	timeout)
}


def static "com.vcb.common.MobileDriver.setText"(
    	TestObject obj	
     , 	String textContent	
     , 	int timeout	) {
    (new com.vcb.common.MobileDriver()).setText(
        	obj
         , 	textContent
         , 	timeout)
}


def static "com.vcb.common.MobileDriver.checkHideKeyBoard"() {
    (new com.vcb.common.MobileDriver()).checkHideKeyBoard()
}


def static "com.vcb.common.MobileDriver.setTextSimply"(
    	TestObject obj	
     , 	String textContent	
     , 	int timeout	) {
    (new com.vcb.common.MobileDriver()).setTextSimply(
        	obj
         , 	textContent
         , 	timeout)
}


def static "com.vcb.common.MobileDriver.setEncryptedText"(
    	TestObject obj	
     , 	String textContent	
     , 	int timeout	) {
    (new com.vcb.common.MobileDriver()).setEncryptedText(
        	obj
         , 	textContent
         , 	timeout)
}


def static "com.vcb.common.MobileDriver.getText"(
    	TestObject obj	
     , 	int timeout	) {
    (new com.vcb.common.MobileDriver()).getText(
        	obj
         , 	timeout)
}


def static "com.vcb.common.MobileDriver.scrollTo"(
    	TestObject obj	
     , 	int maxSwipe	
     , 	boolean directionUp	) {
    (new com.vcb.common.MobileDriver()).scrollTo(
        	obj
         , 	maxSwipe
         , 	directionUp)
}


def static "com.vcb.common.MobileDriver.scrollTo"(
    	TestObject obj	
     , 	int maxSwipe	) {
    (new com.vcb.common.MobileDriver()).scrollTo(
        	obj
         , 	maxSwipe)
}


def static "com.vcb.common.MobileDriver.scrollTo"(
    	TestObject obj	) {
    (new com.vcb.common.MobileDriver()).scrollTo(
        	obj)
}


def static "com.vcb.common.MobileDriver.checkElementAndScroll"(
    	TestObject obj	
     , 	int length	
     , 	boolean directionUp	) {
    (new com.vcb.common.MobileDriver()).checkElementAndScroll(
        	obj
         , 	length
         , 	directionUp)
}


def static "com.vcb.common.MobileDriver.checkElementAndScroll"(
    	TestObject obj	
     , 	int length	) {
    (new com.vcb.common.MobileDriver()).checkElementAndScroll(
        	obj
         , 	length)
}


def static "com.vcb.common.MobileDriver.checkElementAndScroll"(
    	TestObject obj	) {
    (new com.vcb.common.MobileDriver()).checkElementAndScroll(
        	obj)
}


def static "com.vcb.common.MobileDriver.scrollToTop"() {
    (new com.vcb.common.MobileDriver()).scrollToTop()
}


def static "com.vcb.common.MobileDriver.scrollToText"(
    	String text	) {
    (new com.vcb.common.MobileDriver()).scrollToText(
        	text)
}


def static "com.vcb.common.MobileDriver.tapElemtByXml"(
    	String xpath	
     , 	String cacheKey	) {
    (new com.vcb.common.MobileDriver()).tapElemtByXml(
        	xpath
         , 	cacheKey)
}


def static "com.vcb.common.MobileDriver.tapElemtByXml"(
    	String xpath	) {
    (new com.vcb.common.MobileDriver()).tapElemtByXml(
        	xpath)
}


def static "com.vcb.common.MobileDriver.verifyMatch"(
    	TestObject testObject	
     , 	String regexMatch	
     , 	int timeout	) {
    (new com.vcb.common.MobileDriver()).verifyMatch(
        	testObject
         , 	regexMatch
         , 	timeout)
}


def static "com.vcb.mobile.pages.Customise.clickViewAllButton"(
    	Object flowControl	) {
    (new com.vcb.mobile.pages.Customise()).clickViewAllButton(
        	flowControl)
}


def static "com.vcb.mobile.pages.Customise.verifyApplyButtonExist"(
    	Object flowControl	) {
    (new com.vcb.mobile.pages.Customise()).verifyApplyButtonExist(
        	flowControl)
}


def static "com.vcb.mobile.pages.Customise.verifyTabungButtonExist"(
    	Object flowControl	) {
    (new com.vcb.mobile.pages.Customise()).verifyTabungButtonExist(
        	flowControl)
}


def static "com.vcb.mobile.pages.Customise.verifyInvestmentButtonExist"(
    	Object flowControl	) {
    (new com.vcb.mobile.pages.Customise()).verifyInvestmentButtonExist(
        	flowControl)
}


def static "com.vcb.mobile.pages.Customise.verifyPromotionsButtonExist"(
    	Object flowControl	) {
    (new com.vcb.mobile.pages.Customise()).verifyPromotionsButtonExist(
        	flowControl)
}


def static "com.vcb.mobile.pages.Customise.verifyZakatButtonExist"(
    	Object flowControl	) {
    (new com.vcb.mobile.pages.Customise()).verifyZakatButtonExist(
        	flowControl)
}


def static "com.vcb.mobile.pages.Customise.verifySamaSamaLocalButtonExist"(
    	Object flowControl	) {
    (new com.vcb.mobile.pages.Customise()).verifySamaSamaLocalButtonExist(
        	flowControl)
}


def static "com.vcb.mobile.pages.Customise.verifyMoviesLesuireButtonExist"(
    	Object flowControl	) {
    (new com.vcb.mobile.pages.Customise()).verifyMoviesLesuireButtonExist(
        	flowControl)
}


def static "com.vcb.mobile.pages.Customise.verifyPropertyButtonExist"(
    	Object flowControl	) {
    (new com.vcb.mobile.pages.Customise()).verifyPropertyButtonExist(
        	flowControl)
}


def static "com.vcb.mobile.pages.Customise.verifyJuniorButtonExist"(
    	Object flowControl	) {
    (new com.vcb.mobile.pages.Customise()).verifyJuniorButtonExist(
        	flowControl)
}


def static "com.vcb.mobile.pages.Customise.verifySecure2UButtonExist"(
    	Object flowControl	) {
    (new com.vcb.mobile.pages.Customise()).verifySecure2UButtonExist(
        	flowControl)
}


def static "com.vcb.mobile.pages.Customise.verifyTapTrackWinButtonExist"(
    	Object flowControl	) {
    (new com.vcb.mobile.pages.Customise()).verifyTapTrackWinButtonExist(
        	flowControl)
}


def static "com.vcb.mobile.pages.Customise.verifyPNotificationsButtonExist"(
    	Object flowControl	) {
    (new com.vcb.mobile.pages.Customise()).verifyPNotificationsButtonExist(
        	flowControl)
}


def static "com.vcb.mobile.pages.Customise.SwipeToNextScreen"(
    	Object flowControl	) {
    (new com.vcb.mobile.pages.Customise()).SwipeToNextScreen(
        	flowControl)
}


def static "com.vcb.mobile.pages.Customise.verifyKillSwitchButtonExist"(
    	Object flowControl	) {
    (new com.vcb.mobile.pages.Customise()).verifyKillSwitchButtonExist(
        	flowControl)
}


def static "com.vcb.mobile.pages.Customise.verifyTravelDealsButtonExist"(
    	Object flowControl	) {
    (new com.vcb.mobile.pages.Customise()).verifyTravelDealsButtonExist(
        	flowControl)
}


def static "com.vcb.mobile.pages.Customise.verifyERLTicketsButtonExist"(
    	Object flowControl	) {
    (new com.vcb.mobile.pages.Customise()).verifyERLTicketsButtonExist(
        	flowControl)
}


def static "com.vcb.mobile.pages.Customise.verifyFlightTicketsButtonExist"(
    	Object flowControl	) {
    (new com.vcb.mobile.pages.Customise()).verifyFlightTicketsButtonExist(
        	flowControl)
}


def static "com.vcb.mobile.pages.Customise.verifyLoyaltyButtonExist"(
    	Object flowControl	) {
    (new com.vcb.mobile.pages.Customise()).verifyLoyaltyButtonExist(
        	flowControl)
}


def static "com.vcb.mobile.pages.Customise.verifySavedButtonExist"(
    	Object flowControl	) {
    (new com.vcb.mobile.pages.Customise()).verifySavedButtonExist(
        	flowControl)
}


def static "com.vcb.mobile.pages.Customise.verifyReferAFriendButtonExist"(
    	Object flowControl	) {
    (new com.vcb.mobile.pages.Customise()).verifyReferAFriendButtonExist(
        	flowControl)
}


def static "com.vcb.mobile.pages.Customise.verifyVcbezyQButtonExist"(
    	Object flowControl	) {
    (new com.vcb.mobile.pages.Customise()).verifyVcbezyQButtonExist(
        	flowControl)
}


def static "com.vcb.mobile.pages.Customise.verifyArticlesButtonExist"(
    	Object flowControl	) {
    (new com.vcb.mobile.pages.Customise()).verifyArticlesButtonExist(
        	flowControl)
}


def static "com.vcb.mobile.pages.Customise.verifyFoodButtonExist"(
    	Object flowControl	) {
    (new com.vcb.mobile.pages.Customise()).verifyFoodButtonExist(
        	flowControl)
}


def static "com.vcb.mobile.pages.Customise.verifyTravelInsuranceButtonExist"(
    	Object flowControl	) {
    (new com.vcb.mobile.pages.Customise()).verifyTravelInsuranceButtonExist(
        	flowControl)
}


def static "com.vcb.mobile.pages.Customise.verifyATMCashOutButtonExist"(
    	Object flowControl	) {
    (new com.vcb.mobile.pages.Customise()).verifyATMCashOutButtonExist(
        	flowControl)
}


def static "com.vcb.mobile.pages.Customise.clickBackButton"(
    	Object flowControl	) {
    (new com.vcb.mobile.pages.Customise()).clickBackButton(
        	flowControl)
}


def static "com.vcb.mobile.pages.Customise.verifyDefaulItemsCustomiseScreen"(
    	Object flowControl	) {
    (new com.vcb.mobile.pages.Customise()).verifyDefaulItemsCustomiseScreen(
        	flowControl)
}


def static "com.vcb.mobile.pages.Customise.clickCustomiseButton"(
    	Object flowControl	) {
    (new com.vcb.mobile.pages.Customise()).clickCustomiseButton(
        	flowControl)
}


def static "com.vcb.mobile.pages.Customise.verifyManageQucikActionsText"(
    	Object flowControl	) {
    (new com.vcb.mobile.pages.Customise()).verifyManageQucikActionsText(
        	flowControl)
}


def static "com.vcb.mobile.pages.Customise.verifyHoldAndDragDescription"(
    	Object flowControl	) {
    (new com.vcb.mobile.pages.Customise()).verifyHoldAndDragDescription(
        	flowControl)
}


def static "com.vcb.mobile.pages.Customise.verifyCurrentFavoritesText"(
    	Object flowControl	) {
    (new com.vcb.mobile.pages.Customise()).verifyCurrentFavoritesText(
        	flowControl)
}


def static "com.vcb.mobile.pages.WelcomeOnboard.enterYourName"(
    	String yourName	
     , 	String accountName	) {
    (new com.vcb.mobile.pages.WelcomeOnboard()).enterYourName(
        	yourName
         , 	accountName)
}


def static "com.vcb.mobile.pages.WelcomeOnboard.enterYourName"(
    	String yourName	) {
    (new com.vcb.mobile.pages.WelcomeOnboard()).enterYourName(
        	yourName)
}


def static "com.vcb.mobile.pages.Vcb2u.navigateToVcb2u"(
    	FailureHandling flowControl	) {
    (new com.vcb.mobile.pages.Vcb2u()).navigateToVcb2u(
        	flowControl)
}


def static "com.vcb.mobile.pages.Vcb2u.viewAllAccounts"(
    	FailureHandling flowControl	) {
    (new com.vcb.mobile.pages.Vcb2u()).viewAllAccounts(
        	flowControl)
}


def static "com.vcb.mobile.pages.Vcb2u.navigateToVcb2u"() {
    (new com.vcb.mobile.pages.Vcb2u()).navigateToVcb2u()
}


def static "com.vcb.mobile.pages.Vcb2u.viewAllAccounts"() {
    (new com.vcb.mobile.pages.Vcb2u()).viewAllAccounts()
}


def static "com.vcb.mobile.pages.Setting.verifyProfilesExist"(
    	Object flowControl	) {
    (new com.vcb.mobile.pages.Setting()).verifyProfilesExist(
        	flowControl)
}


def static "com.vcb.mobile.pages.Setting.verifyVcb2uExist"(
    	Object flowControl	) {
    (new com.vcb.mobile.pages.Setting()).verifyVcb2uExist(
        	flowControl)
}


def static "com.vcb.mobile.pages.Setting.verifySecirityExist"(
    	Object flowControl	) {
    (new com.vcb.mobile.pages.Setting()).verifySecirityExist(
        	flowControl)
}


def static "com.vcb.mobile.pages.Setting.verifyNotificationsExist"(
    	Object flowControl	) {
    (new com.vcb.mobile.pages.Setting()).verifyNotificationsExist(
        	flowControl)
}


def static "com.vcb.mobile.pages.Setting.verifySupportExist"(
    	Object flowControl	) {
    (new com.vcb.mobile.pages.Setting()).verifySupportExist(
        	flowControl)
}


def static "com.vcb.mobile.pages.Setting.verifyDefaultViewExist"(
    	Object flowControl	) {
    (new com.vcb.mobile.pages.Setting()).verifyDefaultViewExist(
        	flowControl)
}


def static "com.vcb.mobile.pages.Setting.verifyAboutSettingsExist"(
    	Object flowControl	) {
    (new com.vcb.mobile.pages.Setting()).verifyAboutSettingsExist(
        	flowControl)
}


def static "com.vcb.mobile.pages.Setting.clickVcb2uExist"(
    	Object flowControl	) {
    (new com.vcb.mobile.pages.Setting()).clickVcb2uExist(
        	flowControl)
}


def static "com.vcb.mobile.pages.Setting.tapScanAndPay"(
    	Object flowControl	) {
    (new com.vcb.mobile.pages.Setting()).tapScanAndPay(
        	flowControl)
}


def static "com.vcb.mobile.pages.Setting.tapPaynTransfer"(
    	Object flowControl	) {
    (new com.vcb.mobile.pages.Setting()).tapPaynTransfer(
        	flowControl)
}


def static "com.vcb.mobile.pages.Setting.verifyDailyLimitText"(
    	Object flowControl	) {
    (new com.vcb.mobile.pages.Setting()).verifyDailyLimitText(
        	flowControl)
}


def static "com.vcb.mobile.pages.Setting.verifyCumulativeLimitText"(
    	Object flowControl	) {
    (new com.vcb.mobile.pages.Setting()).verifyCumulativeLimitText(
        	flowControl)
}


def static "com.vcb.mobile.pages.Setting.verifyTermsAndConditionsText"(
    	Object flowControl	) {
    (new com.vcb.mobile.pages.Setting()).verifyTermsAndConditionsText(
        	flowControl)
}


def static "com.vcb.mobile.pages.Setting.tapConfirmButton"(
    	Object flowControl	) {
    (new com.vcb.mobile.pages.Setting()).tapConfirmButton(
        	flowControl)
}


def static "com.vcb.mobile.pages.Setting.enterPassword"(
    	Object password	
     , 	Object flowControl	) {
    (new com.vcb.mobile.pages.Setting()).enterPassword(
        	password
         , 	flowControl)
}


def static "com.vcb.mobile.pages.Setting.clickProfileButton"(
    	Object flowControl	) {
    (new com.vcb.mobile.pages.Setting()).clickProfileButton(
        	flowControl)
}


def static "com.vcb.mobile.pages.Setting.verifyTextUpdateProfile"(
    	Object flowControl	) {
    (new com.vcb.mobile.pages.Setting()).verifyTextUpdateProfile(
        	flowControl)
}


def static "com.vcb.mobile.pages.Setting.verifyPhoneNumber"(
    	Object flowControl	) {
    (new com.vcb.mobile.pages.Setting()).verifyPhoneNumber(
        	flowControl)
}


def static "com.vcb.mobile.pages.Setting.verifyProfilePictureUpdatedSuccessfully"(
    	Object flowControl	) {
    (new com.vcb.mobile.pages.Setting()).verifyProfilePictureUpdatedSuccessfully(
        	flowControl)
}


def static "com.vcb.mobile.pages.Setting.verifyPhoneNumberUpdatedSuccessfully"(
    	Object flowControl	) {
    (new com.vcb.mobile.pages.Setting()).verifyPhoneNumberUpdatedSuccessfully(
        	flowControl)
}


def static "com.vcb.mobile.pages.Setting.verifyEmailIdUpdatedSuccessfully"(
    	Object flowControl	) {
    (new com.vcb.mobile.pages.Setting()).verifyEmailIdUpdatedSuccessfully(
        	flowControl)
}


def static "com.vcb.mobile.pages.Setting.clickDuitNowButton"(
    	Object flowControl	) {
    (new com.vcb.mobile.pages.Setting()).clickDuitNowButton(
        	flowControl)
}


def static "com.vcb.mobile.pages.Setting.verifyNRICNumbertext"(
    	Object flowControl	) {
    (new com.vcb.mobile.pages.Setting()).verifyNRICNumbertext(
        	flowControl)
}


def static "com.vcb.mobile.pages.Setting.switchAccountForNricNumber"(
    	Object flowControl	) {
    (new com.vcb.mobile.pages.Setting()).switchAccountForNricNumber(
        	flowControl)
}


def static "com.vcb.mobile.pages.Setting.secure2uforDuitNowSettings"(
    	Object status	
     , 	Object flowControl	) {
    (new com.vcb.mobile.pages.Setting()).secure2uforDuitNowSettings(
        	status
         , 	flowControl)
}


def static "com.vcb.mobile.pages.Setting.tapSecure2u"(
    	Object flowControl	) {
    (new com.vcb.mobile.pages.Setting()).tapSecure2u(
        	flowControl)
}


def static "com.vcb.mobile.pages.Setting.disableSecure2U"(
    	Object flowControl	) {
    (new com.vcb.mobile.pages.Setting()).disableSecure2U(
        	flowControl)
}


def static "com.vcb.mobile.pages.Biometric.setup"(
    	FailureHandling flowControl	) {
    (new com.vcb.mobile.pages.Biometric()).setup(
        	flowControl)
}


def static "com.vcb.mobile.pages.Biometric.setup"() {
    (new com.vcb.mobile.pages.Biometric()).setup()
}

 /**
	 * Clear Android app data
	 * @return
	 */ 
def static "com.vcb.common.Android.clearAppData"(
    	String appPackage	) {
    (new com.vcb.common.Android()).clearAppData(
        	appPackage)
}

 /**
	 * Close Android app
	 * @return
	 */ 
def static "com.vcb.common.Android.killApp"(
    	String appPackage	) {
    (new com.vcb.common.Android()).killApp(
        	appPackage)
}

 /**
	 * Set Text
	 * @param to
	 * @param input
	 * @param timeout
	 * @return
	 */ 
def static "com.vcb.common.Android.setText"(
    	TestObject to	
     , 	String input	
     , 	int timeout	
     , 	Object flowControl	) {
    (new com.vcb.common.Android()).setText(
        	to
         , 	input
         , 	timeout
         , 	flowControl)
}

 /**
	 * Set Text
	 * @param input
	 * @param timeout
	 * @return
	 */ 
def static "com.vcb.common.Android.setTextNoObject"(
    	String input	
     , 	int timeout	
     , 	Object flowControl	) {
    (new com.vcb.common.Android()).setTextNoObject(
        	input
         , 	timeout
         , 	flowControl)
}


def static "com.vcb.common.Android.executeADBCommand"(
    	String command	) {
    (new com.vcb.common.Android()).executeADBCommand(
        	command)
}


def static "com.vcb.common.Android.setTextToClipboard"(
    	String text	) {
    (new com.vcb.common.Android()).setTextToClipboard(
        	text)
}


def static "com.vcb.common.Android.scrollToTestObject"(
    	TestObject grid	
     , 	TestObject findObj	
     , 	ScrollDirectionEnum direction	
     , 	int timeout	
     , 	FailureHandling flowControl	
     , 	boolean flexibleDeivce	) {
    (new com.vcb.common.Android()).scrollToTestObject(
        	grid
         , 	findObj
         , 	direction
         , 	timeout
         , 	flowControl
         , 	flexibleDeivce)
}


def static "com.vcb.common.Android.swipe"(
    	float startXPercentage	
     , 	float endXPercentage	) {
    (new com.vcb.common.Android()).swipe(
        	startXPercentage
         , 	endXPercentage)
}


def static "com.vcb.common.Android.scrollByW3CAction"(
    	int startX	
     , 	int startY	
     , 	int endX	
     , 	int endY	
     , 	int waitTime	) {
    (new com.vcb.common.Android()).scrollByW3CAction(
        	startX
         , 	startY
         , 	endX
         , 	endY
         , 	waitTime)
}


def static "com.vcb.common.Android.scrollToNotDirection"(
    	int startX	
     , 	int startY	
     , 	int endX	
     , 	int endY	) {
    (new com.vcb.common.Android()).scrollToNotDirection(
        	startX
         , 	startY
         , 	endX
         , 	endY)
}


def static "com.vcb.common.Android.scrollToJavascript"(
    	int left	
     , 	int top	
     , 	int width	
     , 	int height	
     , 	Object direction	) {
    (new com.vcb.common.Android()).scrollToJavascript(
        	left
         , 	top
         , 	width
         , 	height
         , 	direction)
}


def static "com.vcb.common.Android.scrollToTestObject"(
    	TestObject grid	
     , 	TestObject findObj	
     , 	ScrollDirectionEnum direction	
     , 	int timeout	
     , 	FailureHandling flowControl	) {
    (new com.vcb.common.Android()).scrollToTestObject(
        	grid
         , 	findObj
         , 	direction
         , 	timeout
         , 	flowControl)
}


def static "com.vcb.common.Android.scrollToTestObject"(
    	TestObject grid	
     , 	TestObject findObj	
     , 	ScrollDirectionEnum direction	
     , 	int timeout	) {
    (new com.vcb.common.Android()).scrollToTestObject(
        	grid
         , 	findObj
         , 	direction
         , 	timeout)
}


def static "com.vcb.common.Android.scrollByW3CAction"(
    	int startX	
     , 	int startY	
     , 	int endX	
     , 	int endY	) {
    (new com.vcb.common.Android()).scrollByW3CAction(
        	startX
         , 	startY
         , 	endX
         , 	endY)
}


def static "com.vcb.common.Android.scrollToJavascript"(
    	int left	
     , 	int top	
     , 	int width	
     , 	int height	) {
    (new com.vcb.common.Android()).scrollToJavascript(
        	left
         , 	top
         , 	width
         , 	height)
}

 /**
	 * Evaluate a value is null or empty
	 * @param actualValue the value need to evaluate
	 * @param description
	 */ 
def static "com.vcb.assertions.BaseAssert.isNull"(
    	Object actualValue	
     , 	String description	
     , 	FailureHandling flowControl	) {
    (new com.vcb.assertions.BaseAssert()).isNull(
        	actualValue
         , 	description
         , 	flowControl)
}

 /**
	 * Evaluate a value is not null and not empty
	 * @param actualValue
	 * @param description short description
	 */ 
def static "com.vcb.assertions.BaseAssert.isNotNull"(
    	Object actualValue	
     , 	String description	
     , 	FailureHandling flowControl	) {
    (new com.vcb.assertions.BaseAssert()).isNotNull(
        	actualValue
         , 	description
         , 	flowControl)
}

 /**
	 * Evaluate 2 values are equals
	 * @param expectedValue expected value
	 * @param actualValue actual value
	 * @param description
	 */ 
def static "com.vcb.assertions.BaseAssert.equals"(
    	Object expectedValue	
     , 	Object actualValue	
     , 	String description	
     , 	FailureHandling flowControl	) {
    (new com.vcb.assertions.BaseAssert()).equals(
        	expectedValue
         , 	actualValue
         , 	description
         , 	flowControl)
}

 /**
	 * Evaluate 2 values are not equals
	 * @param expectedValue expected value
	 * @param actualValue actual value
	 * @param description
	 */ 
def static "com.vcb.assertions.BaseAssert.notEquals"(
    	Object expectedValue	
     , 	Object actualValue	
     , 	String description	
     , 	FailureHandling flowControl	) {
    (new com.vcb.assertions.BaseAssert()).notEquals(
        	expectedValue
         , 	actualValue
         , 	description
         , 	flowControl)
}


def static "com.vcb.assertions.BaseAssert.isNull"(
    	Object actualValue	
     , 	String description	) {
    (new com.vcb.assertions.BaseAssert()).isNull(
        	actualValue
         , 	description)
}


def static "com.vcb.assertions.BaseAssert.isNotNull"(
    	Object actualValue	
     , 	String description	) {
    (new com.vcb.assertions.BaseAssert()).isNotNull(
        	actualValue
         , 	description)
}


def static "com.vcb.assertions.BaseAssert.equals"(
    	Object expectedValue	
     , 	Object actualValue	
     , 	String description	) {
    (new com.vcb.assertions.BaseAssert()).equals(
        	expectedValue
         , 	actualValue
         , 	description)
}


def static "com.vcb.assertions.BaseAssert.notEquals"(
    	Object expectedValue	
     , 	Object actualValue	
     , 	String description	) {
    (new com.vcb.assertions.BaseAssert()).notEquals(
        	expectedValue
         , 	actualValue
         , 	description)
}


def static "com.vcb.mobile.pages.Onboarding.navigateToLogin"(
    	FailureHandling flowControl	) {
    (new com.vcb.mobile.pages.Onboarding()).navigateToLogin(
        	flowControl)
}


def static "com.vcb.mobile.pages.Onboarding.disagreeDisclosure"(
    	FailureHandling flowControl	) {
    (new com.vcb.mobile.pages.Onboarding()).disagreeDisclosure(
        	flowControl)
}


def static "com.vcb.mobile.pages.Onboarding.navigateToLogin"() {
    (new com.vcb.mobile.pages.Onboarding()).navigateToLogin()
}


def static "com.vcb.mobile.pages.Onboarding.disagreeDisclosure"() {
    (new com.vcb.mobile.pages.Onboarding()).disagreeDisclosure()
}


def static "com.katalon.testcloud.FileExecutor.pushFileToDevice"(
    	String destinationPath	
     , 	String localFilePath	) {
    (new com.katalon.testcloud.FileExecutor()).pushFileToDevice(
        	destinationPath
         , 	localFilePath)
}


def static "com.katalon.testcloud.FileExecutor.uploadFileToWeb"(
    	TestObject to	
     , 	String filePath	) {
    (new com.katalon.testcloud.FileExecutor()).uploadFileToWeb(
        	to
         , 	filePath)
}


def static "com.katalon.testcloud.FileExecutor.getFileContent"(
    	String fileName	) {
    (new com.katalon.testcloud.FileExecutor()).getFileContent(
        	fileName)
}


def static "com.katalon.testcloud.FileExecutor.getFileDescriptor"(
    	String fileName	) {
    (new com.katalon.testcloud.FileExecutor()).getFileDescriptor(
        	fileName)
}


def static "com.katalon.testcloud.FileExecutor.exist"(
    	String fileName	) {
    (new com.katalon.testcloud.FileExecutor()).exist(
        	fileName)
}


def static "com.katalon.testcloud.GeolocationExecutor.setMobileGeoLocation"(
    	double latitude	
     , 	double longitude	
     , 	double altitude	) {
    (new com.katalon.testcloud.GeolocationExecutor()).setMobileGeoLocation(
        	latitude
         , 	longitude
         , 	altitude)
}


def static "com.katalon.testcloud.BiometricsAuthenticator.authenticatePass"() {
    (new com.katalon.testcloud.BiometricsAuthenticator()).authenticatePass()
}


def static "com.katalon.testcloud.BiometricsAuthenticator.authenticateFail"() {
    (new com.katalon.testcloud.BiometricsAuthenticator()).authenticateFail()
}


def static "com.katalon.testcloud.CameraImageInjectionExecutor.injectImage"(
    	String fileName	) {
    (new com.katalon.testcloud.CameraImageInjectionExecutor()).injectImage(
        	fileName)
}


def static "com.katalon.testcloud.CameraImageInjectionExecutor.exist"(
    	String fileName	) {
    (new com.katalon.testcloud.CameraImageInjectionExecutor()).exist(
        	fileName)
}


def static "com.katalon.testcloud.ThrottleNetworkExecutor.customNetworkProfile"(
    	int downloadSpeed	
     , 	int uploadSpeed	
     , 	int latency	) {
    (new com.katalon.testcloud.ThrottleNetworkExecutor()).customNetworkProfile(
        	downloadSpeed
         , 	uploadSpeed
         , 	latency)
}


def static "com.katalon.testcloud.ThrottleNetworkExecutor.setNetworkProfile"(
    	String condition	) {
    (new com.katalon.testcloud.ThrottleNetworkExecutor()).setNetworkProfile(
        	condition)
}
