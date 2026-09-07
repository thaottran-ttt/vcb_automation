import com.vcb.fixture.ReportFixtureSupport

'Fixture: no browser (shouldNotStartApp=true). Fails attempt 1, passes on attempt 2. See custom-report/ReportFixture_Suite_Plan.md.'
ReportFixtureSupport.failUntilAttempt('RF06', passOnAttempt, failureMessage)
