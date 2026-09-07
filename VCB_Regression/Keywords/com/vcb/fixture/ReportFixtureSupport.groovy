package com.vcb.fixture

import com.kms.katalon.core.configuration.RunConfiguration
import com.kms.katalon.core.util.KeywordUtil

/**
 * Static-only helper for the ExcelReportFixture suite (custom-report/ReportFixture_Suite_Plan.md).
 * Not annotated with @Keyword - called statically from Scripts/**, exactly like
 * com.vcb.common.BaseKeyword.startApplication(). Never referenced from Libs/CustomKeywords.groovy.
 *
 * Keyed on RunConfiguration.getReportFolder() (plan §2 Q4) so a second suite run inside the same
 * Katalon Studio JVM session resets the attempt counters instead of inheriting stale ones left
 * over from the previous run.
 *
 * Katalon builds this file with the antlr2 Groovy parser (KNOWN_ISSUES.md #1) - plain while/for
 * only, no do/while, var, ::, lambdas, try-with-resources, !in, !instanceof, multi-assignment.
 */
class ReportFixtureSupport {

	private static String currentRunId = null
	private static Map<String, Integer> attempts = [:]

	/**
	 * Returns the 1-based attempt number for the given key. Resets every counter whenever the
	 * current report folder differs from the one seen on the previous call, so a second fixture
	 * run in the same JVM starts every key back at attempt 1.
	 */
	static int attempt(String key) {
		String runId = null
		try {
			runId = RunConfiguration.getReportFolder()
		} catch (Exception ex) {
			runId = null
		}

		if (runId != currentRunId) {
			attempts.clear()
			currentRunId = runId
		}

		int count = 1
		if (attempts.containsKey(key)) {
			count = attempts.get(key) + 1
		}
		attempts.put(key, count)
		return count
	}

	/**
	 * Fails every attempt of `key` strictly before `passOnAttempt`, then passes on and after it.
	 * Used by RF03/RF06 to produce a deterministic RETRY -> ... -> PASSED sequence.
	 */
	static void failUntilAttempt(String key, int passOnAttempt, String message) {
		int currentAttempt = attempt(key)
		if (currentAttempt < passOnAttempt) {
			KeywordUtil.markFailedAndStop(message)
			return
		}
		KeywordUtil.logInfo("${key}: passing on attempt ${currentAttempt} (passOnAttempt=${passOnAttempt})")
	}
}
