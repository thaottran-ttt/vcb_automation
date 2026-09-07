import com.vcb.fixture.ReportFixtureSupport

'Fixture: no browser (shouldNotStartApp=true). Fails attempts 1-2, passes on attempt 3. See custom-report/ReportFixture_Suite_Plan.md.'
ReportFixtureSupport.failUntilAttempt('RF03', passOnAttempt, failureMessage)
