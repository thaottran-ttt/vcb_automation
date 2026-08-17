package com.vcb.mobile.internal.pages.base

import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.configuration.RunConfiguration
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testobject.TestObject
import com.vcb.assertions.BaseAssert
import com.vcb.common.DeviceTypeEnum
import com.vcb.common.ExecutionSetting
import com.vcb.common.MobileDriver
import com.vcb.utils.DateUtils
import com.vcb.utils.StringUtils

import groovy.json.JsonSlurper

public class BasePage extends MobileDriver{

	private static Map<String, BasePage> cachedPages = [:]
	public static ExecutionSetting executionSetting
	static def cachedJsonDataUI = null
	static String fileUIPath = RunConfiguration.getProjectDir() + '/UI/vcbUI.json'
	/**
	 * Get a new page instance based on device type and page type
	 * @param type
	 * @return
	 */
	public static <T> T getPage(T type) {
		T newInstance = null
		String packageName = ""
		String prefix = ""

		if (!executionSetting) {
			executionSetting = new ExecutionSetting()
		}

		DeviceTypeEnum deviceType = executionSetting.deviceType
		// Get package and page prefix based on device type
		switch (deviceType) {
			case DeviceTypeEnum.AndroidPhone:
				packageName = "com.vcb.mobile.internal.pages.androidphone"
				prefix = "AndroidPhone"
				break
			default:
				break
		}

		// Get the full class name of T
		int pos = type.toString().lastIndexOf('.')
		String typePackage = type.toString().substring(0, pos).replaceFirst("class ", "")
		String typeClass = type.toString().substring(pos + 1)

		// Update packageName dynamically based on the package of T
		if (typePackage.endsWith(".base")) {
			// Keep packageName unchanged if type is in the base package
			packageName = packageName
		} else {
			// Append the subpackage structure to packageName
			String dynamicSuffix = typePackage.replace("com.vcb.mobile.internal.pages.base", "")
			packageName = packageName + dynamicSuffix
		}
		// Add prefix to the class name
		typeClass = prefix + typeClass

		// Combine packageName and class name to get the full class name
		String className = packageName + "." + typeClass
		String cachedKey = className

		// If the page exists, reuse it
		if (cachedPages && cachedPages.containsKey(cachedKey)) {
			newInstance = cachedPages.getAt(cachedKey) as T
		} else {
			if (!existedClass(className)) {
				className = packageName + "." + typeClass
				cachedKey = className
			}
			if (cachedPages && cachedPages.containsKey(cachedKey)) {
				newInstance = cachedPages.getAt(cachedKey) as T
			} else {
				Class<?> cls = Class.forName(className)
				newInstance = cls.newInstance()
				cachedPages.put(cachedKey, newInstance)
			}
		}
		return newInstance
	}


	/**
	 * existedClass: check existed Class
	 * @param className
	 * @return
	 */
	private static boolean existedClass(String className){
		try{
			Class<?> cls = Class.forName(className)
			return true
		}
		catch(ClassNotFoundException ex){
			return false
		}
	}

	/**
	 * verifyElements: verify text on screen
	 * @param lst: text List on screen
	 * pathObject: Path repository of object
	 * @return: true if all text in lst is display
	 */
	@Keyword
	static def verifyElements(List<String> lst, String pathObject, int timeout, FailureHandling flowControl = defaultFailureHandling) {
		lst.each { item ->
			def variable = item
			TestObject to = findTestObject(pathObject, [('text') : variable])
			info("Verify element ${to} param: {text: ${variable}} ")
			verifyElementVisible(to, timeout, flowControl)
		}
	}

	/**
	 * scrollToAndVerifyTextInList: scroll to and verify each text in the list
	 * @param lst: text List on screen
	 * pathObject: Path repository of object
	 * @return: true if all text in lst is display
	 */
	@Keyword
	static def scrollToAndVerifyTextInList(List<String> lst, String pathObject, int timeout, FailureHandling flowControl = defaultFailureHandling) {
		lst.each { item ->
			def variable = item
			Mobile.scrollToText(variable)
			TestObject to = findTestObject(pathObject, [('text') : variable])
			info("Verify element ${to} param: {text: ${variable}} ")
			verifyElementVisible(to, timeout, flowControl)
		}
	}

	/**
	 * verifySomeElementsMatchInList: verify text on screen
	 * @param lst: text List on screen
	 * pathObject: Path repository of object
	 * @return true if 1 item in lst is display
	 */
	@Keyword
	static def verifySomeElementsMatchInList(List<String> lst, String pathObject, int timeout, FailureHandling flowControl = FailureHandling.OPTIONAL) {
		info "verifySomeElementsMatchInList ${lst}"
		if(lst.size() == 0) {
			return
		}
		boolean reslut = lst.any { item ->
			def variable = item
			TestObject to = findTestObject(pathObject, [('text') : variable])
			info("Verify any element ${to} param: {text: ${variable}} ")
			return waitForElementPresent(to, timeout, flowControl)
		}

		//List have empty item return true
		if(lst.contains("")) {
			return
		}

		BaseAssert.equals(reslut, true, 'No element matches in ' + lst)
	}

	/**
	 * verifyFormatMoney: verify Format Money (xxx,xx.xx)
	 * @param
	 * @return
	 */
	@Keyword
	boolean verifyFormatMoney(List<String> lst) {
		return lst.every {
			def pattern = ~/^\d{1,3}(,\d{3})*(\.\d{2})$/
			return pattern.matcher(it).matches()
		}
	}
	/**
	 * verifyFormatMoneyRM: verify Format Money (RM xxx,xx.xx)
	 * @param
	 * @return
	 */
	@Keyword
	boolean verifyFormatMoneyRM(List<String> lst) {
		return lst.every {
			StringUtils.extractMoney(it)
			def pattern = ~/^-?RM\s\d{1,3}(,\d{3})*(\.\d{2})$/
			return pattern.matcher(it).matches()
		}
	}

	/**
	 * verifyFormatDate: verify Format Date (pattern "dd MMM yyyy")
	 * @param
	 * @return
	 */
	@Keyword
	boolean verifyFormatDate(List<String> lst) {
		return lst.every {
			return DateUtils.isValidDate(it)
		}
	}

	/**
	 * verifyFormatAccountNumber: (pattern "xxxx xxxx xxxx")
	 * @param
	 * @return
	 */
	@Keyword
	boolean verifyFormatAccountNumber(List<String> lst) {
		return lst.every {
			def pattern = ~/^\d{4} \d{4} \d{4}$/
			return pattern.matcher(it).matches()
		}
	}


	static boolean verifyBalanceAfterSpending(String oldBalance, String newBalance, String amount, String description = 'Check Amount', FailureHandling flowControl = defaultFailureHandling) {
		def newBalanceBigInt = StringUtils.convertBalanceAmountNumber(newBalance)
		def oldBalanceBigInt = StringUtils.convertBalanceAmountNumber(oldBalance)
		BaseAssert.equals((oldBalanceBigInt - newBalanceBigInt).toString(), amount, description, flowControl)
	}

	/**
	 * getValueUI: Get Value from file Json
	 * @param
	 * @return
	 */
	static def getValueUI(String keyPath) {
		// Check if the JSON data is already cached
		if (cachedJsonDataUI == null) {
			// Read and parse the JSON file if not already cached
			def jsonFile = new File(fileUIPath)
			def jsonContent = jsonFile.text
			cachedJsonDataUI = new JsonSlurper().parseText(jsonContent)
		}

		// Split the key path (e.g., 'ProgressBar.register') by '.' to traverse the JSON
		def keys = keyPath.split('\\.')

		// Navigate the cached JSON object using the keys
		def value = cachedJsonDataUI
		keys.each { key ->
			value = value[key]
			if (value == null) {
				return null // Return null if any key in the path is not found
			}
		}

		return value
	}
}
