package com.vcb.report

import java.text.SimpleDateFormat

import com.kms.katalon.core.configuration.RunConfiguration
import com.kms.katalon.core.context.TestCaseContext
import com.kms.katalon.core.context.TestSuiteContext
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.vcb.mobile.internal.pages.base.BasePage
import com.vcb.mobile.internal.pages.base.CommonPage

import internal.GlobalVariable

/**
 * Facade + state machine called by the four one-line hooks in Test Listeners/TestListener.groovy.
 * Owns all reporting state for one suite execution. Not annotated with @Keyword - called statically,
 * exactly like com.vcb.common.BaseKeyword.startApplication().
 *
 * Every public method is wrapped so a reporting failure can never fail a test case or a suite (R2).
 */
class ExcelReporter {

	private static ExcelReportConfig config
	private static ExcelReportWriter writer
	private static boolean enabled = false
	private static File screenshotsDir

	private static int nextSequenceNo = 0
	private static Map<String, TestResultRecord> lastAttempt = [:]
	private static Map<String, Integer> attemptCount = [:]
	private static Map<String, String> baseNoCache = [:]
	private static Map<String, String> descriptionCache = [:]
	private static Set<String> loggedUnmatchedModules = new HashSet<>()

	private static long currentStartNanos = 0
	private static String currentTestCaseId = null
	private static String cachedEnvironmentLabel = null
	private static boolean rowBudgetWarned = false

	/* ================= Lifecycle hooks (plan §5) ================= */

	/** Insert at Test Listeners/TestListener.groovy:237, first statement of @BeforeTestSuite. */
	static void startSuite(TestSuiteContext testSuiteContext) {
		try {
			doStartSuite(testSuiteContext)
		} catch (Throwable t) {
			enabled = false
			ExcelReportLog.warn("ExcelReporter.startSuite failed, Excel reporting disabled for this run: ${t}")
		}
	}

	/** Insert at Test Listeners/TestListener.groovy:53, first statement of @BeforeTestCase. */
	static void startTestCase(TestCaseContext testCaseContext) {
		try {
			if (!enabled) return
			currentTestCaseId = testCaseContext.getTestCaseId()
			currentStartNanos = System.nanoTime()
		} catch (Throwable t) {
			ExcelReportLog.warn("ExcelReporter.startTestCase failed: ${t}")
		}
	}

	/** Insert at Test Listeners/TestListener.groovy:157, first statement of @AfterTestCase. */
	static void endTestCase(TestCaseContext testCaseContext) {
		try {
			if (!enabled || writer == null) return
			doEndTestCase(testCaseContext)
		} catch (Throwable t) {
			ExcelReportLog.warn("ExcelReporter.endTestCase failed: ${t}")
		}
	}

	/** Insert at Test Listeners/TestListener.groovy:253, first statement of @AfterTestSuite. */
	static void endSuite(TestSuiteContext testSuiteContext) {
		try {
			if (!enabled || writer == null) return
			writer.evaluateAllFormulas()
			writer.save()
			ExcelReportLog.info("Excel report: ${writer.getTargetFile()?.absolutePath}")
			writer.close()
		} catch (Throwable t) {
			ExcelReportLog.warn("ExcelReporter.endSuite failed: ${t}")
		} finally {
			enabled = false
		}
	}

	/* ================= @BeforeTestSuite implementation ================= */

	private static void doStartSuite(TestSuiteContext testSuiteContext) {
		config = ExcelReportConfig.load()
		resetSuiteState()

		if (!config.isEnabled()) {
			enabled = false
			return
		}

		String suiteId = testSuiteContext.getTestSuiteId()
		String suiteName = suiteId.contains('/') ? suiteId.substring(suiteId.lastIndexOf('/') + 1) : suiteId
		String timestamp = new SimpleDateFormat('yyyyMMdd_HHmmss').format(new Date())
		String outputRootConfig = config.getOutputDir()
		File outputRoot = outputRootConfig ? new File(outputRootConfig) : new File(RunConfiguration.getProjectDir(), 'custom-report/output')
		// pid suffix (R4): two runs starting in the same second cannot collide
		File execDir = new File(outputRoot, "${suiteName}_${timestamp}_${ProcessHandle.current().pid()}")
		execDir.mkdirs()
		screenshotsDir = new File(execDir, 'screenshots')
		screenshotsDir.mkdirs()

		File template = new File(RunConfiguration.getProjectDir(), 'custom-report/Template.xlsx')
		File reportFile = new File(execDir, 'Report.xlsx')

		writer = new ExcelReportWriter()
		writer.open(template, reportFile)

		int maxRow = computeMaxRow(suiteId)
		writer.expandRowBudget(maxRow)

		writer.writeModuleLabels(config.getModules())
		writer.writeEnvironmentLabels(config.getEnvironments())
		writer.writeMetadata(
				resolveProjectName(),
				config.getAppVersion(),
				nowDateString(),
				resolveEnvironmentForSummary(),
				resolveTriggeredBy())

		writer.save()

		enabled = true
		ExcelReportLog.info("Excel report: initialized at ${reportFile.absolutePath}")
	}

	private static void resetSuiteState() {
		nextSequenceNo = 0
		lastAttempt = [:]
		attemptCount = [:]
		baseNoCache = [:]
		descriptionCache = [:]
		loggedUnmatchedModules = new HashSet<>()
		cachedEnvironmentLabel = null
		rowBudgetWarned = false
		currentStartNanos = 0
		currentTestCaseId = null
		writer = null
		screenshotsDir = null
	}

	/* ================= @AfterTestCase implementation (plan §5, §6, §7, §9) ================= */

	private static void doEndTestCase(TestCaseContext testCaseContext) {
		String testCaseId = testCaseContext.getTestCaseId()

		// 1. Compute duration - wall time from listener entry to listener exit (§5), 1 decimal.
		double durationSeconds = currentStartNanos > 0
				? Math.round(((System.nanoTime() - currentStartNanos) / 1_000_000_000.0d) * 10) / 10.0d
				: 0.0d

		// 2. Map status; capture screenshot now, before the caller tears the driver down.
		String mappedStatus = mapStatus(testCaseContext.getTestCaseStatus())
		String displayId = testCaseId.contains('/') ? testCaseId.substring(testCaseId.lastIndexOf('/') + 1) : testCaseId
		int attemptNumber = (attemptCount[testCaseId] ?: 0) + 1
		String screenshotRelPath = captureScreenshotIfFailed(mappedStatus, displayId, attemptNumber)

		// 3. Resolve description, module, environment.
		String description = resolveDescription(testCaseId, displayId)
		String module = resolveModule(testCaseId)
		String environment = resolveEnvironmentLabel()

		// 4. Retry detection; if retry, rewrite the previous row's F cell to RETRY in memory.
		String displayNo = resolveDisplayNoAndHandleRetry(testCaseId)

		TestResultRecord record = new TestResultRecord(
				testCaseId: testCaseId,
				displayId: displayId,
				module: module,
				description: description,
				environment: environment,
				status: mappedStatus,
				durationSeconds: durationSeconds,
				errorMessage: truncateErrorMessage(testCaseContext.getMessage()),
				screenshotRelPath: screenshotRelPath,
				displayNo: displayNo,
				suiteRerunIndex: safeSuiteRerunIndex())

		// 5. Append the new row.
		int rowIndex = writer.appendRow(record)
		if (rowIndex < 0) {
			warnRowBudgetExhaustedOnce()
			writer.save()
			return
		}
		record.excelRowIndex = rowIndex
		lastAttempt[testCaseId] = record

		// 6. Atomic write-through save.
		writer.save()
	}

	private static String resolveDisplayNoAndHandleRetry(String testCaseId) {
		TestResultRecord previous = lastAttempt[testCaseId]
		boolean isRetry = false
		if (config.getRetryDetectionMode() != 'off' && previous != null && previous.status != 'PASSED') {
			boolean adjacent = (previous.excelRowIndex == writer.getLastWrittenRowIndex())
			boolean suiteRerunAdvanced = safeSuiteRerunIndex() > previous.suiteRerunIndex
			isRetry = adjacent || suiteRerunAdvanced
		}

		if (isRetry) {
			writer.setStatus(previous.excelRowIndex, 'RETRY')
			int n = (attemptCount[testCaseId] ?: 1) + 1
			attemptCount[testCaseId] = n
			return "${baseNoCache[testCaseId]}.${n - 1}"
		}

		nextSequenceNo++
		String displayNo = String.valueOf(nextSequenceNo)
		attemptCount[testCaseId] = 1
		baseNoCache[testCaseId] = displayNo
		return displayNo
	}

	private static void warnRowBudgetExhaustedOnce() {
		if (rowBudgetWarned) return
		rowBudgetWarned = true
		String msg = "Excel report row budget exhausted at ${writer.getMaxRow()}; remaining results NOT recorded"
		ExcelReportLog.warn(msg)
		try {
			writer.writeRowBudgetExhaustedWarning(msg)
		} catch (Throwable ignore) {
			// cosmetic - never let this block the run
		}
	}

	/* ================= Status mapping (plan §4) ================= */

	private static String mapStatus(String katalonStatus) {
		switch (katalonStatus?.toUpperCase()) {
			case 'PASSED':
				return 'PASSED'
			case 'FAILED':
			case 'ERROR':
				return 'FAILED'
			case 'SKIPPED':
			case 'NOT_RUN':
			case 'INCOMPLETE':
				return 'BLOCKED'
			default:
				return 'BLOCKED'
		}
	}

	/* ================= Module derivation (plan §7) ================= */

	private static String resolveModule(String testCaseId) {
		List<String> segments = new ArrayList<>(Arrays.asList(testCaseId.split('/')))
		if (!segments.isEmpty() && segments[0] == 'Test Cases') {
			segments.remove(0)
		}
		if (!segments.isEmpty() && (segments[0] == 'Mobile' || segments[0] == 'Website')) {
			segments.remove(0)
		}
		if (!segments.isEmpty() && (segments[0] == 'VCB' || segments[0] == 'M2U' || segments[0] == 'G1A')) {
			segments.remove(0)
		}
		String raw = segments.size() > 1 ? segments[0] : 'Uncategorized'

		String alias = config.getModuleAlias(raw)
		String resolved = alias ?: raw

		List<String> configured = config.getModules()
		if (!configured.contains(resolved)) {
			if (loggedUnmatchedModules.add(resolved)) {
				ExcelReportLog.warn("ExcelReporter: module '${resolved}' (from '${testCaseId}') is not in report.modules=${configured}; collapsing into '${otherBucket(configured)}'")
			}
			resolved = otherBucket(configured)
		}
		return resolved
	}

	private static String otherBucket(List<String> configured) {
		if (configured.size() >= 4) return configured[3]
		return configured ? configured.last() : 'Other'
	}

	/* ================= Description parsing (plan §7) ================= */

	private static String resolveDescription(String testCaseId, String fallback) {
		if (descriptionCache.containsKey(testCaseId)) {
			return descriptionCache[testCaseId]
		}
		String description = fallback
		try {
			File tcFile = new File(RunConfiguration.getProjectDir(), testCaseId + '.tc')
			if (tcFile.exists()) {
				def xml = new XmlSlurper().parse(tcFile)
				String parsed = xml.description?.text()
				if (parsed && !parsed.trim().isEmpty()) {
					description = parsed.trim()
				}
			}
		} catch (Throwable t) {
			ExcelReportLog.warn("ExcelReporter: could not parse description from '${testCaseId}.tc': ${t}")
		}
		descriptionCache[testCaseId] = description
		return description
	}

	/* ================= Row-budget computation (plan §11) ================= */

	private static int computeMaxRow(String suiteId) {
		Integer overrideVal = config.getMaxRowsOverride()
		if (overrideVal != null) {
			return overrideVal
		}
		try {
			File tsFile = new File(RunConfiguration.getProjectDir(), suiteId + '.ts')
			if (!tsFile.exists()) {
				return 200
			}
			def xml = new XmlSlurper().parse(tsFile)
			int links = xml.testCaseLink.size()
			int reruns = 0
			try {
				reruns = xml.numberOfRerun.text().toInteger()
			} catch (Throwable ignore) {
				reruns = 0
			}
			return links * (reruns + 1) + 50
		} catch (Throwable t) {
			ExcelReportLog.warn("ExcelReporter: could not compute row budget from '${suiteId}.ts', defaulting to 200: ${t}")
			return 200
		}
	}

	/* ================= Environment string (plan §8, §10a) ================= */

	private static String resolveEnvironmentForSummary() {
		String pinned = config.getEnvironmentLabelOverride()
		if (pinned) {
			return truncate(pinned, 40)
		}
		String viaMae = tryGetEnvNameOnMae()
		if (viaMae) {
			return viaMae
		}
		try {
			return RunConfiguration.getExecutionProfile() ?: ''
		} catch (Throwable t) {
			return ''
		}
	}

	private static String tryGetEnvNameOnMae() {
		try {
			String projectName = safeGlobal({ GlobalVariable.G_projectName })
			String fileName = safeGlobal({ GlobalVariable.G_VCBVersionFileName })
			if (!projectName || !fileName) return null
			CommonPage commonPage = BasePage.getPage(CommonPage)
			return commonPage.getEnvNameOnMae(projectName, fileName)
		} catch (Throwable t) {
			return null
		}
	}

	private static String resolveEnvironmentLabel() {
		if (cachedEnvironmentLabel != null) {
			return cachedEnvironmentLabel
		}

		String pinned = config.getEnvironmentLabelOverride()
		if (pinned) {
			cachedEnvironmentLabel = truncate(pinned, 40)
			return cachedEnvironmentLabel
		}

		String derived = tryDeriveDeviceOrBrowserLabel()
		if (derived) {
			cachedEnvironmentLabel = truncate(derived, 40)
			try {
				writer.updateFirstEnvironmentLabel(cachedEnvironmentLabel)
				writer.updateEnvironmentMetadata(cachedEnvironmentLabel)
			} catch (Throwable ignore) {
				// cosmetic - never let this block the run
			}
			return cachedEnvironmentLabel
		}

		// Driver not up yet (or derivation failed): fall back to the first configured label,
		// uncached so later rows keep retrying. A20 already holds this same value from the
		// initial config stamp, so COUNTIFS still matches by construction.
		List<String> configured = config.getEnvironments()
		return configured ? configured[0] : 'Unknown'
	}

	private static String tryDeriveDeviceOrBrowserLabel() {
		try {
			def mobileDriver = null
			try {
				mobileDriver = MobileDriverFactory.getDriver()
			} catch (Throwable ignore) {
				mobileDriver = null
			}
			if (mobileDriver != null) {
				String os = MobileDriverFactory.getDeviceOS()
				String osVersion = MobileDriverFactory.getDeviceOSVersion()
				String model = MobileDriverFactory.getDeviceModel()
				return "${os ?: 'Android'}${osVersion ?: ''}/${model ?: 'Device'}"
			}
		} catch (Throwable ignore) {
			// fall through to web
		}

		try {
			def webDriver = null
			try {
				webDriver = DriverFactory.getWebDriver()
			} catch (Throwable ignore) {
				webDriver = null
			}
			if (webDriver != null) {
				String hostOs = safeHostOs() ?: 'Unknown'
				String browserName = null
				try {
					browserName = DriverFactory.getExecutedBrowser()?.getName()
				} catch (Throwable ignore) {
				}
				browserName = browserName ?: safeGlobal({ GlobalVariable.G_browserName })
				String browserVersion = null
				try {
					browserVersion = DriverFactory.getBrowserVersion(webDriver)
				} catch (Throwable ignore) {
				}
				browserVersion = browserVersion ?: safeGlobal({ GlobalVariable.G_browserVersion })
				String majorVersion = browserVersion ? browserVersion.split(/\./)[0] : ''
				return "${hostOs}/${browserName ?: 'Browser'}${majorVersion}"
			}
		} catch (Throwable ignore) {
			// neither driver is up yet
		}

		return null
	}

	private static String safeHostOs() {
		try {
			Map hostProps = RunConfiguration.getHostProperties()
			def os = hostProps?.get(RunConfiguration.HOST_OS)
			return os?.toString()
		} catch (Throwable t) {
			return null
		}
	}

	/* ================= Screenshots (plan §9) ================= */

	private static String captureScreenshotIfFailed(String mappedStatus, String displayId, int attemptNumber) {
		if (mappedStatus != 'FAILED' || !config.isScreenshotOnFail() || screenshotsDir == null) {
			return null
		}
		try {
			int rowNumber = writer.getNextRowNumber()
			String fileName = String.format('%04d', rowNumber) + '_' + sanitizeForFilename(displayId) + '_a' + attemptNumber + '.png'
			File screenshotFile = new File(screenshotsDir, fileName)

			boolean captured = false
			if (GlobalVariable.G_runWebsite) {
				try {
					if (DriverFactory.getWebDriver() != null) {
						WebUI.takeScreenshot(screenshotFile.absolutePath, FailureHandling.OPTIONAL)
						captured = true
					}
				} catch (Throwable t) {
					ExcelReportLog.warn("ExcelReporter: web screenshot capture failed for '${displayId}': ${t}")
				}
			} else {
				try {
					if (MobileDriverFactory.getDriver() != null) {
						Mobile.takeScreenshot(screenshotFile.absolutePath, FailureHandling.OPTIONAL)
						captured = true
					}
				} catch (Throwable t) {
					ExcelReportLog.warn("ExcelReporter: mobile screenshot capture failed for '${displayId}': ${t}")
				}
			}

			return captured ? "screenshots/${fileName}".toString() : null
		} catch (Throwable t) {
			ExcelReportLog.warn("ExcelReporter: screenshot capture failed for '${displayId}': ${t}")
			return null
		}
	}

	private static String sanitizeForFilename(String raw) {
		String cleaned = (raw ?: 'test').replaceAll(/[^A-Za-z0-9._-]/, '_')
		return cleaned.length() > 80 ? cleaned.substring(0, 80) : cleaned
	}

	/* ================= Misc helpers ================= */

	private static String resolveProjectName() {
		String override = config.getProjectNameOverride()
		if (override) return override
		String globalName = safeGlobal({ GlobalVariable.G_projectName })
		if (globalName) return globalName
		try {
			return RunConfiguration.getProjectName() ?: ''
		} catch (Throwable t) {
			return ''
		}
	}

	private static String resolveTriggeredBy() {
		String override = config.getTriggeredByOverride()
		if (override) return override
		try {
			String user = RunConfiguration.getUserFullName() ?: 'unknown'
			String host = java.net.InetAddress.getLocalHost().getHostName()
			return "${user}@${host}"
		} catch (Throwable t) {
			try {
				return RunConfiguration.getUserFullName() ?: ''
			} catch (Throwable t2) {
				return ''
			}
		}
	}

	private static String nowDateString() {
		return new SimpleDateFormat('yyyy-MM-dd HH:mm').format(new Date())
	}

	private static String truncate(String value, int maxLen) {
		if (value == null) return value
		return value.length() > maxLen ? value.substring(0, maxLen) : value
	}

	private static String truncateErrorMessage(String raw) {
		if (!raw) return ''
		String collapsed = raw.replaceAll(/\r?\n/, ' | ')
		return collapsed.length() > 500 ? collapsed.substring(0, 500) : collapsed
	}

	private static int safeSuiteRerunIndex() {
		try {
			return RunConfiguration.getSuiteRerunIndex()
		} catch (Throwable t) {
			return 0
		}
	}

	/** R9: reads a GlobalVariable field defensively - a profile that never sets it (e.g.
	 *  Profiles/sanity-web.glbl only defines G_url/G_projectName) leaves it null, and the field
	 *  may not resolve at all in an unusual build; both cases fall back to null here. */
	private static String safeGlobal(Closure<?> supplier) {
		try {
			def v = supplier.call()
			String s = v == null ? null : v.toString()
			return (s == null || s.trim().isEmpty()) ? null : s.trim()
		} catch (Throwable t) {
			return null
		}
	}
}
