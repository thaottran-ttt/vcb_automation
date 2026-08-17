import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static org.assertj.core.api.Assertions.catchException

import java.util.regex.Matcher
import java.util.regex.Pattern

import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.configuration.RunConfiguration
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.vcb.common.Android
import com.vcb.common.BaseKeyword
import com.vcb.common.FlowControlEnum
import com.vcb.common.MobileDriver
import com.vcb.common.MobileThread
import com.vcb.common.PlatformEnum
import com.vcb.common.WebUIKeyword
import com.vcb.mobile.internal.pages.base.BasePage
import com.vcb.mobile.internal.pages.base.CommonPage
import com.vcb.utils.ListUtils
import com.vcb.utils.MobileDriverUtils
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory

import internal.GlobalVariable
import io.appium.java_client.AppiumDriver
import io.appium.java_client.android.AndroidDriver

import com.kms.katalon.core.annotation.BeforeTestCase
import com.kms.katalon.core.annotation.BeforeTestSuite
import com.kms.katalon.core.appium.driver.AppiumDriverManager
import com.kms.katalon.core.annotation.AfterTestCase
import com.kms.katalon.core.annotation.AfterTestSuite
import com.kms.katalon.core.context.TestCaseContext
import com.kms.katalon.core.context.TestSuiteContext

class TestListener {

	/**
	 * Executes before every test case starts.
	 * @param testCaseContext related information of the executed test case.
	 */
	@BeforeTestCase
	def sampleBeforeTestCase(TestCaseContext testCaseContext) {
		Map map = testCaseContext.getTestCaseVariables()
		String testCaseId = testCaseContext.getTestCaseId()
		String flowControl = map.get('flowControl')

		boolean shouldNotStartApp = map.get('shouldNotStartApp')
		boolean isFirstTestCase = GlobalVariable.G_isFirstRun
		boolean isRunWebsiteAndMobile = map.get('isRunWebsiteAndMobile')
		GlobalVariable.G_isRunWebsiteAndMobile = isRunWebsiteAndMobile
		
		'prevent starting the app'
		if(shouldNotStartApp) {
			return
		}
		'Skip Test Case'
		if(GlobalVariable.G_isStopTestSuite) {
			testCaseContext.skipThisTestCase()
			return
		}

		if(!GlobalVariable.G_runWebsite && ListUtils.isStringInList(GlobalVariable.G_websiteTestCaseFolderPath, testCaseId)) {
			GlobalVariable.G_runWebsite = true
		}

		'Website'
		if(GlobalVariable.G_runWebsite) {
			if(!GlobalVariable.G_isTacM2U) {
				if(ListUtils.isStringInList(GlobalVariable.G_mobileTestCaseFolderPath, testCaseId) || isRunWebsiteAndMobile) {
					if(GlobalVariable.G_isFirstRun) {
						'Start Application'
						BaseKeyword.startApplication()
						switchEnvironment(isFirstTestCase)
					}
					try {
						'To avoid Mobile timeout when running website'
						MobileThread mobileThread = new MobileThread()
						mobileThread.startThreadWaitingAction(MobileDriver.driver)
					}catch(ex) {
						KeywordUtil.logInfo(ex.toString())
					}
				}
			}


			if(ListUtils.isStringInList(GlobalVariable.G_websiteTestCaseFolderPath, testCaseId)) {
				'Open Browser and Bypass Secure'
				CustomKeywords.'com.vcb.m2u.pages.WebCommon.openBrowserAndBypassSecure'(GlobalVariable.G_url, isRunWebsiteAndMobile)
			}

			GlobalVariable.G_isFirstRun = false
			return true
		}
		'Mobile'
		BaseKeyword.setFailureHandling(flowControl)

		if(GlobalVariable.G_isRunTestSuite) {
			'Not run Application again because it has been run at test suite'
			if(!GlobalVariable.G_keepApp) {
				if(GlobalVariable.G_isFirstRun) {
					'first run test case not start Application because it has been run at test suite'
					GlobalVariable.G_isFirstRun = !GlobalVariable.G_isFirstRun
				}else {
					BaseKeyword.startApplication()
				}
			}
		}else {
			'Run at Test Case'
				Mobile.startExistingApplication(GlobalVariable.G_appId)
		
		}

		switchEnvironment(isFirstTestCase)
	}
	def switchEnvironment(boolean isFirstTestCase) {
		'Set Driver if openApplication Again'
		MobileDriverUtils.setDriverMobile()
		CommonPage commonPage = BasePage.getPage(CommonPage)

		// Switch Environment On Vcb
		switch (GlobalVariable.G_projectName) {
			case {GlobalVariable.G_projectName}:
			// Get Environment Name On VCB App
				String envName = commonPage.getEnvNameOnMae(GlobalVariable.G_projectName, GlobalVariable.G_VCBVersionFileName)
				Mobile.comment(envName)
				if ((GlobalVariable.G_isFirstRun && envName) || (!GlobalVariable.G_keepApp && envName)) {
					// Switch Environment In TestListener
					commonPage.switchEnvironmentInTestListener(envName)
				}
				break
			case {!GlobalVariable.G_projectName}:
				break
			default:
				if (((GlobalVariable.G_keepApp && isFirstTestCase == 1) || !GlobalVariable.G_keepApp) && !(GlobalVariable.G_environment == '' || GlobalVariable.G_environment == 'UAT')) {
					commonPage.switchEnvironmentInTestListener(GlobalVariable.G_environment)
				}
				break
		}
	}

	/**
	 * Executes after every test case ends.
	 * @param testCaseContext related information of the executed test case.
	 */
	@AfterTestCase
	def sampleAfterTestCase(TestCaseContext testCaseContext) {
		'Skip Test Case'
		if(GlobalVariable.G_isStopTestSuite) {
			return
		}
		Map map = testCaseContext.getTestCaseVariables()
		String testCaseStatus = testCaseContext.getTestCaseStatus()
		String testCaseId = testCaseContext.getTestCaseId()
		if(map == null) return
			boolean failStopTestSuite = map.get('failStopTestSuite')
		boolean skipRestartAfterTestCase = map.get('skipRestartAfterTestCase')
		boolean shouldNotStartApp = map.get('shouldNotStartApp')
		boolean isRunWebsiteAndMobile = map.get('isRunWebsiteAndMobile')

		'Because the application is not running, nothing is processed'
		if(shouldNotStartApp) {
			return
		}
		'Skip Test Case'
		if(!testCaseStatus?.toLowerCase()?.contains('pass')) {
			if(failStopTestSuite) {
				GlobalVariable.G_isStopTestSuite = true
				return
			}
		}
		'Website'
		if(GlobalVariable.G_runWebsite) {
			if(ListUtils.isStringInList(GlobalVariable.G_websiteTestCaseFolderPath, testCaseId)) {
				WebUIKeyword.takeScreenshot()
				if(GlobalVariable.G_isRunTestSuite && !GlobalVariable.G_shouldRunSingleBrowser) {
					WebUIKeyword.closeBrowser()
					if(GlobalVariable.G_s2uSucessful) {
						CommonPage commonPage = BasePage.getPage(CommonPage)
						commonPage.restartApp()
					}
				}
			}

			if(isRunWebsiteAndMobile && !GlobalVariable.G_isTacM2U) {
				MobileDriver.takeScreenshot()
				if(GlobalVariable.G_s2uSucessful) {
					CommonPage commonPage = BasePage.getPage(CommonPage)
					commonPage.restartApp()
				}	
			}

			return
		}

		'Mobile'
		MobileDriver.takeScreenshot()

		if(GlobalVariable.G_isRunTestSuite) {
			CommonPage commonPage = BasePage.getPage(CommonPage)
			boolean isPass = testCaseStatus.toLowerCase().contains('pass')
			if(isPass) {
				if(GlobalVariable.G_keepApp) {
					commonPage.restartApp()
				}
			}else {
				GlobalVariable.G_restartAppAfterTestCase = true
				if(GlobalVariable.G_keepApp) {
					commonPage.networkLogsAndRestartApp()
				}else {
					commonPage.networkLogs()
				}
			}

			'Close App'
			if(!GlobalVariable.G_keepApp) {
				Mobile.closeApplication()
			}
		}
	}

	/**
	 * Executes before every test suite starts.
	 * @param testSuiteContext: related information of the executed test suite.
	 */
	@BeforeTestSuite
	def sampleBeforeTestSuite(TestSuiteContext testSuiteContext) {
		GlobalVariable.G_isRunTestSuite = true
		String testSuiteId = testSuiteContext.getTestSuiteId()
		if(testSuiteId.contains('KMS_M2U')) {
			GlobalVariable.G_runWebsite = true
			return
		}

		BaseKeyword.startApplication()
	}

	/**
	 * Executes after every test suite ends.
	 * @param testSuiteContext: related information of the executed test suite.
	 */
	@AfterTestSuite
	def sampleAfterTestSuite(TestSuiteContext testSuiteContext) {
		println testSuiteContext.getTestSuiteId()
	}
}