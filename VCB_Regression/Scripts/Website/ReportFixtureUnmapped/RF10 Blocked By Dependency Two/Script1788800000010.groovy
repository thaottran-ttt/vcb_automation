import com.kms.katalon.core.util.KeywordUtil

'Fixture: this case must never execute - RF08 arms GlobalVariable.G_isStopTestSuite so'
'TestListener.groovy:69-71 skips it before this script runs. No shouldNotStartApp is declared on'
'purpose (see the .tc description / plan §2 Q3); if the skip gate is ever broken this call makes'
'that failure loud (FAILED) instead of a silent false PASSED. See custom-report/ReportFixture_Suite_Plan.md.'
KeywordUtil.markFailedAndStop('RF10-SKIP-DID-NOT-FIRE - this case must never execute')
