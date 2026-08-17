package com.vcb.m2u.internal.pages.base

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import com.vcb.common.BrowserTypeEnum
import com.vcb.common.ExecutionWebSetting
import com.vcb.common.WebUIKeyword
import com.vcb.utils.DateUtils

import internal.GlobalVariable

public abstract class WebBasePage extends WebUIKeyword{
	public static ExecutionWebSetting executionWebSetting
	private static Map<String, WebBasePage> cachedPages = [:]

	/**
	 * Get a new page instance base on device type and page type
	 * @param type
	 * @return
	 */
	public static <T> T getPage(T type){
		T newInstance = null
		String packageName = ""
		String prefix = ""
		final String defaultPackage = 'com.vcb.m2u.internal.pages'

		if(!executionWebSetting) {
			executionWebSetting = new ExecutionWebSetting()
		}
		BrowserTypeEnum broswerType = executionWebSetting.browserType

		//Get package and page prefix base on device type
		switch(broswerType){
			case BrowserTypeEnum.SAFARI_DRIVER:
				packageName = defaultPackage + ".safari"
				prefix = "Safari"
				break
			default:
				packageName = defaultPackage + ".chrome"
				prefix = "Chrome"
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
			String dynamicSuffix = typePackage.replace( defaultPackage + ".base", "")
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

	private static boolean existedClass(String className){
		try{
			Class<?> cls = Class.forName(className)
			return true
		}
		catch(ClassNotFoundException ex){
			return false
		}
	}
}

