'Fixture: no browser (shouldNotStartApp=true). Deliberately uncaught, no try/catch, so Katalon'
'records ERROR (not FAILED) and ExcelReporter.mapStatus exercises the ERROR -> FAILED branch that'
'markFailedAndStop never reaches. See custom-report/ReportFixture_Suite_Plan.md.'
throw new IllegalStateException('RF07-SIMULATED-ERROR - exercises the ERROR to FAILED mapping')
