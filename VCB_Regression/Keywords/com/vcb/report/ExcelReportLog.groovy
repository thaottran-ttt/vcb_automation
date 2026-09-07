package com.vcb.report

import com.kms.katalon.core.util.KeywordUtil

/**
 * Logging for the Excel reporter.
 *
 * Two reasons this exists instead of calling KeywordUtil directly:
 *
 * 1. KeywordUtil has no logWarning() - only markWarning(), logInfo(), markFailed()... A call to a
 *    missing method throws MissingMethodException, and most of the reporter's warnings are emitted
 *    from inside catch blocks, so the throw escapes the catch and breaks the listener hook it was
 *    called from. Routing every message through here keeps that class of mistake in one file.
 *
 * 2. markWarning() marks the step WARNING, which can change suite status. A hiccup in reporting must
 *    never colour a regression run, so warn() logs at info level with a [WARN] prefix instead - still
 *    fully visible in the console log, but unable to affect the result. Swap the body of warn() if the
 *    team decides it would rather have real warning steps.
 *
 * Every method swallows everything: logging must never be the thing that fails a test case.
 */
class ExcelReportLog {

	private static final String PREFIX = 'ExcelReport'

	static void info(Object message) {
		emit("${PREFIX}: ${message}")
	}

	static void warn(Object message) {
		emit("${PREFIX} [WARN]: ${message}")
	}

	private static void emit(Object message) {
		String text
		try {
			text = String.valueOf(message)
		} catch (Throwable ignored) {
			// A GString whose toString() blows up must not take the run with it.
			text = "${PREFIX}: <unprintable log message>"
		}
		try {
			KeywordUtil.logInfo(text)
		} catch (Throwable ignored) {
			try {
				println text
			} catch (Throwable ignoredToo) {
				// Nothing left to try - stay silent rather than propagate.
			}
		}
	}
}
