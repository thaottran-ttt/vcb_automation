package com.vcb.report

import com.kms.katalon.core.configuration.RunConfiguration

/**
 * Loads custom-report/report.properties, applies -Dvcb.<key> overrides, and exposes
 * typed getters + built-in defaults. No Apache POI here - pure config.
 *
 * Precedence: -Dvcb.<key> system property > properties file > built-in default.
 */
class ExcelReportConfig {

	private final Properties props = new Properties()

	private ExcelReportConfig() {
	}

	static ExcelReportConfig load() {
		ExcelReportConfig cfg = new ExcelReportConfig()
		File propsFile = new File(RunConfiguration.getProjectDir(), 'custom-report/report.properties')
		if (propsFile.exists()) {
			propsFile.withInputStream { is -> cfg.props.load(is) }
		} else {
			ExcelReportLog.warn("ExcelReporter: report.properties not found at ${propsFile.absolutePath}; using built-in defaults")
		}
		return cfg
	}

	private String raw(String key, String defaultValue) {
		String override = System.getProperty('vcb.' + key)
		if (override != null && !override.trim().isEmpty()) {
			return override.trim()
		}
		String value = props.getProperty(key)
		if (value == null || value.trim().isEmpty()) {
			return defaultValue
		}
		return value.trim()
	}

	boolean isEnabled() {
		return raw('report.enabled', 'true').toBoolean()
	}

	String getOutputDir() {
		return raw('report.output.dir', '')
	}

	String getProjectNameOverride() {
		return raw('report.project.name', '')
	}

	String getAppVersion() {
		return raw('report.app.version', '')
	}

	String getTriggeredByOverride() {
		return raw('report.triggered.by', '')
	}

	List<String> getModules() {
		return splitCsv(raw('report.modules', 'SauceDemo,Sanity,Uncategorized,Other'))
	}

	List<String> getEnvironments() {
		return splitCsv(raw('report.environments', 'Windows11/Chrome'))
	}

	String getEnvironmentLabelOverride() {
		return raw('report.environment.label', '')
	}

	String getRetryDetectionMode() {
		return raw('report.retry.detection', 'auto')
	}

	Integer getMaxRowsOverride() {
		String v = raw('report.max.rows', '')
		return v ? Integer.parseInt(v) : null
	}

	boolean isScreenshotOnFail() {
		return raw('report.screenshot.on.fail', 'true').toBoolean()
	}

	/** report.module.alias.<rawModule>=<TemplateLabel> - maps a derived-from-path module name onto
	 *  one of the template's configured Module Breakdown labels (§7 point 5). Empty when unset. */
	String getModuleAlias(String rawModule) {
		return raw('report.module.alias.' + rawModule, '')
	}

	private static List<String> splitCsv(String value) {
		if (!value) {
			return []
		}
		return value.split(',').collect { it.trim() }.findAll { it }
	}
}
