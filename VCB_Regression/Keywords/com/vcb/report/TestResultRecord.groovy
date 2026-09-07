package com.vcb.report

/**
 * One instance per *attempt* (not per test case - a retried test case produces one record
 * per attempt, with the earlier attempt's row rewritten to RETRY in place - see ExcelReporter §6).
 */
class TestResultRecord {

	String testCaseId          // 'Test Cases/Website/SauceDemo/TC02 Login Locked User'
	String displayId           // basename -> 'TC02 Login Locked User'          -> column B
	String module              // derived                                      -> column C
	String description         // from the .tc XML <description>               -> column D
	String environment         // run-level label                              -> column E
	String status               // PASSED|FAILED|BLOCKED (never RETRY)          -> column F
	double durationSeconds     // 1 decimal                                    -> column G
	String errorMessage        // testCaseContext.getMessage(), trimmed        -> column H
	String screenshotRelPath   // 'screenshots/xxx.png' or null                -> column I
	int excelRowIndex          // 0-based row the writer put it on (set by writer)
	String displayNo           // '3' or '3.1'                                 -> column A
	int suiteRerunIndex        // RunConfiguration.getSuiteRerunIndex() at write time
}
