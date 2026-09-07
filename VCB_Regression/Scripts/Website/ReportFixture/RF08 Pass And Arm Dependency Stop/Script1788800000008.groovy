import com.kms.katalon.core.util.KeywordUtil

import internal.GlobalVariable

'Fixture: no browser (shouldNotStartApp=true), deterministic PASSED. See custom-report/ReportFixture_Suite_Plan.md.'
KeywordUtil.logInfo('RF08: deterministic PASSED, arming dependency-stop gate')

'Simulates the dependency-stop gate at TestListener.groovy:69-71 (GlobalVariable.G_isStopTestSuite'
'is checked in @BeforeTestCase and never reset by the listener), so every case after this one is'
'skipped by testCaseContext.skipThisTestCase() and reports BLOCKED. RF09 and RF10 MUST be the'
'last two links in Test Suites/ReportFixture/ExcelReportFixture.ts, with nothing added after them.'
GlobalVariable.G_isStopTestSuite = true
