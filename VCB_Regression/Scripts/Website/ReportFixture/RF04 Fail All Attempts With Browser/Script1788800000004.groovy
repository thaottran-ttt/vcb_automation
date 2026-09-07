import com.kms.katalon.core.util.KeywordUtil

'Fixture: WITHOUT shouldNotStartApp - a real Chrome driver is alive here. Deterministic FAILED on'
'every attempt, no page interaction, so ExcelReporter.captureScreenshotIfFailed can capture from'
'the live driver. See custom-report/ReportFixture_Suite_Plan.md.'
KeywordUtil.markFailedAndStop(failureMessage + ' - driver is alive, screenshot expected')
