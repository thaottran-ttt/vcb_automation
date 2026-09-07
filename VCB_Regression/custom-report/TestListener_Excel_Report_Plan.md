# Implementation Plan — Populate `custom-report/Template.xlsx` from a Katalon TestListener

## 0. What was verified in the repo first (this changes several assumptions in the design doc)

| Claim | Reality found | Where |
|---|---|---|
| "~1400 test cases, mobile" | **This checkout has 6 test cases, all Web**: `Test Cases/Website/Sanity/*` + `Test Cases/Website/SauceDemo/TC01..TC05`. `Test Cases/` contains only `Website/`. One suite exists: `Test Suites/SauceDemo/SauceDemoTests.ts`. | `find "Test Cases" -name "*.tc"` → 6 hits |
| "Apache POI bundled — verify, may need a jar in Drivers/" | **Already on the classpath, no jar needed.** `org.apache.poi.poi_5.5.1`, `poi-ooxml_5.5.1`, `poi-ooxml-full_5.5.1`, plus `xmlbeans_5.3.0`, `commons-compress_1.28.0`, `commons-io_2.22.0`, `commons-codec_1.22.0`, `commons-collections4_4.5.0`, `SparseBitSet_1.3.0`. `Drivers/` is empty. | `.classpath:95-105`, `.classpath:60` |
| "Listener must open the template and fill it" | The template ships **10 rows of demo data** in `Test Details` rows 2–11 that must be cleared — but **column L rows 2–5 hold the LEGEND** and must survive. | `xl/worksheets/sheet2.xml` (`L2:PASSED`, `L3:FAILED`, `L4:BLOCKED`, `L5:RETRY`) |
| Design doc §3.4: "listener must NOT hand-write column J" | The `FailRank` formula **only exists on rows 2–11**. From row 12 onward there is no formula to inherit, so the listener **must write the J formula on every row it appends**. The doc is wrong on this point. | `sheet2.xml` — `J2`…`J11` only |
| `TestCaseContext` gives description / retry index / timings | It does **not**. The whole interface is `getTestCaseId()`, `getTestCaseVariables()`, `getTestCaseStatus()`, `getMessage()`, `skipThisTestCase()`, `isSkipped()`. Description must be parsed from the `.tc` XML; duration must be measured by us. | `javap com.kms.katalon.core.context.TestCaseContext` |
| — | `RunConfiguration.getSuiteRerunIndex()` **exists** and is the authoritative retry signal. Also: `getProjectDir()`, `getReportFolder()`, `getProjectName()`, `getExecutionProfile()`, `getUserFullName()`, `getHostProperties()`, `getDevicePlatform()`. | `javap com.kms.katalon.core.configuration.RunConfiguration` |
| — | The suite is configured `numberOfRerun=3`, `rerunImmediately=true`, `rerunFailedTestCasesOnly=false`, `maxConcurrentInstances=1`. | `Test Suites/SauceDemo/SauceDemoTests.ts:8-12` |
| — | Template was authored by **LibreOffice Calc** (`<fileVersion appName="Calc"/>`), contains `vmlDrawing1/2.vml` + `comments1/2.xml` and a `loext` extension block. POI round-trip of LO-authored VML is the single riskiest unknown. | `xl/workbook.xml`, `[Content_Types].xml` |

**Implication:** build and validate against `Test Suites/SauceDemo/SauceDemoTests` (5 web cases with a real rerun config), and prove the 1400-case path with a synthetic harness — not with a suite that does not exist in this branch.

---

## 1. Approach

A thin **`ExcelReporter` facade under `Keywords/com/vcb/report/`** owns all reporting state and is called from exactly four one-line hooks in `Test Listeners/TestListener.groovy`. This matches the repo's existing layering — the listener already delegates to static keyword classes (`BaseKeyword.startApplication()` at `TestListener.groovy:244`, `MobileDriver.takeScreenshot()` at `:206`) and `.claude/docs/architecture.md:48-65` establishes `com.vcb.*` keyword packages as the home for logic.

At `@BeforeTestSuite` the reporter **copies** `custom-report/Template.xlsx` to a per-execution working file, opens it once with `XSSFWorkbook`, and **holds the workbook in memory for the whole suite**. Each `@AfterTestCase` appends one row and does an **atomic write-through save** (temp file + `Files.move`), so the file on disk is always current ("cuốn chiếu") and a crash loses at most one test case. Formula *evaluation* is **not** run per test case (FailRank is `COUNTIF($F$2:Fn,...)`, so `evaluateAll()` per row is O(n²) — 1400 rows ≈ 1M cell evaluations *per save*). Instead every save sets `setForceFormulaRecalculation(true)` (any viewer recalcs on open, even from a crash-truncated run) and `evaluateAll()` runs **once** at `@AfterTestSuite` to bake cached values for headless consumers.

The template's fixed lists (4 modules, 4 environments) and its hard-coded `$200` range ends are handled by **config + programmatic range expansion**: a committed `custom-report/report.properties` supplies module/environment label lists and metadata; at suite start the reporter counts the suite's test-case links, computes worst-case row count, and rewrites every `$X$200` in both sheets. Charts keep their fixed 4-row category ranges, so **the module/environment tables stay exactly 4 rows** — a deliberate constraint (§10).

Everything the reporter does is wrapped so that **no reporting failure can ever fail a test case or a suite**.

---

## 2. Files

### Create

| File | Purpose |
|---|---|
| `Keywords/com/vcb/report/ExcelReportConfig.groovy` | Loads `custom-report/report.properties`, applies `-D` overrides, typed getters + defaults. No POI. |
| `Keywords/com/vcb/report/TestResultRecord.groovy` | POGO for one appended row (§4). |
| `Keywords/com/vcb/report/ExcelReportWriter.groovy` | All Apache POI. Cell addresses, styles, formulas, saving. Knows nothing about Katalon. |
| `Keywords/com/vcb/report/ExcelReporter.groovy` | Facade + state machine called by the listener. Retry detection, module/env derivation, screenshot capture, status mapping, exception swallowing. |
| `custom-report/report.properties` | Committed config (§10). |

### Modify

| File | Change |
|---|---|
| `Test Listeners/TestListener.groovy` | Add `import com.vcb.report.ExcelReporter` and 4 calls (§5). |
| `.gitignore` | Append `custom-report/output/`. |
| `.claude/docs/architecture.md` | Add a ~10-line "Excel reporting" subsection. |

### Explicitly do NOT modify

- **`build.gradle`** — POI 5.5.1 is already on the Katalon 11.4.0 classpath (`.classpath:95-97`). Declaring `poi-ooxml` would make `com.katalon.gradle-plugin` drop a *second* POI into `Drivers/`; a version clash with the OSGi-loaded 5.5.1 is a classloader hazard for no benefit.
- **`custom-report/Template.xlsx`** — never opened for writing at runtime. Template shape changes are **human edits in LibreOffice**.
- `Libs/CustomKeywords.groovy`, `Libs/internal/GlobalVariable.groovy` (generated; `CLAUDE.md`).

**Do not annotate the new methods with `@Keyword`.** They are called statically from the listener, exactly like `BaseKeyword.startApplication()`.

---

## 3. Runtime layout on disk

```
custom-report/
  Template.xlsx                     # pristine, read-only at runtime
  report.properties                 # committed config
  output/                           # gitignored
    SauceDemoTests_20260907_143012/
      Report.xlsx                   # the working copy
      screenshots/
        0003_TC02-Login-Locked-User_a1.png
```

`screenshots/` is a **sibling of `Report.xlsx`**, so the template's relative-hyperlink convention (`HYPERLINK("screenshots/TC_002.png","View Image")`, cell `I3`) works unchanged in Excel and LibreOffice, and the whole folder is one zippable artifact. Column I gets a **relative** path; the absolute path goes only into the log line at suite end.

Output root defaults to `${projectDir}/custom-report/output`, overridable via `report.output.dir`.

---

## 4. Data model

`TestResultRecord` — one instance per *attempt*, not per test case:

```
String  testCaseId        // 'Test Cases/Website/SauceDemo/TC02 Login Locked User'
String  displayId         // basename -> 'TC02 Login Locked User'   -> column B
String  module            // derived (§7)                            -> column C
String  description       // from the .tc XML <description>          -> column D
String  environment       // run-level label (§8)                    -> column E
String  status            // PASSED|FAILED|BLOCKED (never RETRY)     -> column F
double  durationSeconds   // 1 decimal                               -> column G
String  errorMessage      // testCaseContext.getMessage(), trimmed   -> column H
String  screenshotRelPath // 'screenshots/xxx.png' or null           -> column I
int     excelRowIndex     // 0-based row the writer put it on (set by writer)
String  displayNo         // '3' or '3.1'                            -> column A
int     suiteRerunIndex   // RunConfiguration.getSuiteRerunIndex() at write time
```

`ExcelReporter` suite-level state:

```
int nextSequenceNo                          // 1,2,3... base numbers for column A
Map<String, TestResultRecord> lastAttempt   // testCaseId -> last record written
Map<String, Integer> attemptCount           // testCaseId -> attempts so far
Map<String, String> descriptionCache        // testCaseId -> <description>
long currentStartNanos                      // set in @BeforeTestCase
String currentTestCaseId
```

### Status mapping (Katalon `TestStatus.TestStatusValue` → template vocabulary)

Katalon's enum is `PASSED, SKIPPED, FAILED, INCOMPLETE, ERROR, INFO, WARNING, NOT_RUN` (verified by `javap`).

| Katalon | Template |
|---|---|
| `PASSED` | `PASSED` |
| `FAILED`, `ERROR` | `FAILED` |
| `SKIPPED`, `NOT_RUN`, `INCOMPLETE` | `BLOCKED` |
| anything else / null | `BLOCKED` |

`SKIPPED` → `BLOCKED` is right here: `TestListener.groovy:68` calls `testCaseContext.skipThisTestCase()` when `G_isStopTestSuite` is set, i.e. skipped *because a dependency failed* — exactly what the template's `BLOCKED` legend means (`sheet2` sample row 6: "Dependency TC_BANK_003 chua hoan tat").

`RETRY` is **never** produced by the mapper. It is only ever *written back* over a previous row (§6).

---

## 5. Lifecycle mapping

All four calls are **the first statements** in their hooks. This is load-bearing:

- `@BeforeTestCase` returns early at `TestListener.groovy:64` (`shouldNotStartApp`) and `:69` (`skipThisTestCase`).
- `@AfterTestCase` returns early at `:160`, `:164` (`map == null`), `:172`, `:178`, `:202`. It also **closes the browser at `:186` and the app at `:226`** — a screenshot taken after those points is impossible.

### `@BeforeTestSuite` — insert at `TestListener.groovy:237` (before `G_isRunTestSuite = true`)

```groovy
ExcelReporter.startSuite(testSuiteContext)
```

Load config → resolve output dir `output/<suiteName>_<yyyyMMdd_HHmmss>/` → `Files.copy(Template.xlsx, Report.xlsx)` → `WorkbookFactory.create(new File(Report.xlsx))` → cache row-2 cell styles → clear sample data → parse the suite `.ts` for `testCaseLink` count and `numberOfRerun` → expand `$200` ranges → write module/env label lists and metadata cells → first save. One try/catch; on failure set `enabled=false`, `KeywordUtil.logWarning`, return.

### `@BeforeTestCase` — insert at `TestListener.groovy:53` (before the existing `Map map = ...`)

```groovy
ExcelReporter.startTestCase(testCaseContext)
```

Records `currentTestCaseId` + `System.nanoTime()`. **This includes app/browser startup time** (`BaseKeyword.startApplication()` at `:114`, `openBrowserAndBypassSecure` at `:98`) in column G. There is no earlier hook, so this is accepted and must be documented — column G is "wall time from listener entry to listener exit", not pure test-step time.

### `@AfterTestCase` — insert at `TestListener.groovy:157` (before the `G_isStopTestSuite` guard)

```groovy
ExcelReporter.endTestCase(testCaseContext)
```

In order:
1. Compute duration.
2. Map status. If `FAILED` → capture screenshot **now**, while the driver is alive (§9).
3. Resolve description (cached `.tc` parse), module, environment.
4. Retry detection (§6); if retry, rewrite the previous row's F cell to `RETRY` in memory.
5. Append the new row.
6. `writer.save()` — atomic write-through.

### `@AfterTestSuite` — insert at `TestListener.groovy:253` (before the existing `println`)

```groovy
ExcelReporter.endSuite(testSuiteContext)
```

`XSSFFormulaEvaluator.evaluateAll()` (guarded, §11) → `setForceFormulaRecalculation(true)` → final save → `workbook.close()` → `KeywordUtil.logInfo("Excel report: <absolute path>")`.

### Save strategy — the decision and its tradeoff

**Write-through after every test case, with the workbook kept open in memory.**

- The workbook is parsed **once**. Re-opening per test case would be O(n) parse per save → O(n²) over the suite; unacceptable at 1400 rows.
- One `workbook.write()` of a ~30 KB→~500 KB xlsx is single-digit to low-tens of ms. At 1400 saves that is well under a minute against a mobile suite where each case takes 30 s–3 min. **IO cost is <0.1% of runtime; crash-safety is worth far more.**
- **Atomic save:** write `Report.xlsx.tmp` then `Files.move(tmp, target, REPLACE_EXISTING)` (try `ATOMIC_MOVE`, catch `AtomicMoveNotSupportedException`, retry without). Never write directly onto the live file — a crash mid-`write()` produces a corrupt zip with no recoverable prior version.
- **Do not** call `evaluateAll()` per save (§11). `setForceFormulaRecalculation(true)` per save is O(1) and makes even a crash-truncated file correct when a human opens it.
- `SXSSFWorkbook` (streaming) is **not** usable: it cannot open an existing template and preserve charts/CF/DV. Stay on `XSSFWorkbook`.

---

## 6. Retry detection and the `No.` column

Katalon's rerun re-invokes the same `testCaseId`; nothing in `TestCaseContext` says "this is attempt 2". Two signals exist:

- `RunConfiguration.getSuiteRerunIndex()` — increments on **suite-level** reruns (`rerunImmediately=false` path).
- **Adjacency** — with `rerunImmediately=true` (what `SauceDemoTests.ts:12` sets), a retry is always the *immediately next* test case executed.

**Rule** in `ExcelReporter.endTestCase`:

```groovy
isRetry =  lastAttempt.containsKey(id)
        && lastAttempt[id].status != 'PASSED'
        && (   lastAttempt[id].excelRowIndex == writer.lastWrittenRowIndex     // adjacent
            || RunConfiguration.getSuiteRerunIndex() > lastAttempt[id].suiteRerunIndex )
```

If `isRetry`:
1. `writer.setStatus(lastAttempt[id].excelRowIndex, 'RETRY')` — overwrite column F of the previous row **in place** (workbook is in memory, so this is free; the next save persists it).
2. `n = ++attemptCount[id]` ; `displayNo = baseNo(id) + "." + (n - 1)` where `baseNo(id)` is the `A` value of that test case's **first** attempt row. This reproduces the template's own sample exactly: `A4="3"/F4=RETRY`, `A5="3.1"/F5=PASSED`.

Else: `displayNo = String.valueOf(++nextSequenceNo)`, `attemptCount[id] = 1`.

Column A is written with `setCellValue(String)` **and** the row-2 style (`s="20"` → `numFmtId=168`, format code `@` = Text), so `3.10` never collapses onto `3.1`. Do not use `setCellValue(double)`.

### Known limitation — document it, do not hide it

A test case **listed twice in one suite**, or a **data-driven test case running N iterations** (`usingDataBindingAtTestSuiteLevel`), is indistinguishable from a retry when the earlier occurrence did not pass. The `status != 'PASSED'` guard means all-passing iterations are recorded correctly as separate rows; a failing iteration followed by another iteration will be mislabelled `RETRY`. All SauceDemo links have `usingDataBindingAtTestSuiteLevel=false`, so this cannot bite today. Ship a kill-switch `report.retry.detection=auto|off` (`off` → every execution gets its own integer `No.`, no back-rewriting), default `auto`.

---

## 7. Field sourcing per column

| Col | Source |
|---|---|
| **A No.** | §6. String cell, style copied from `A2`. |
| **B Test Case ID** | `testCaseContext.getTestCaseId()`, last path segment. |
| **C Module** | Derived from the id path (below). |
| **D Test Description** | Parse `${RunConfiguration.getProjectDir()}/${testCaseId}.tc` with `XmlSlurper`, read `<description>`. Verified present: `Test Cases/Website/SauceDemo/TC01 Login Success.tc:3`. Cache per id; on parse failure fall back to the display id. |
| **E Environment** | Run-level label, §8. |
| **F Status** | `testCaseContext.getTestCaseStatus()` through the §4 map. |
| **G Execution Time (s)** | `(System.nanoTime() - currentStartNanos)/1e9`, 1 decimal, `setCellValue(double)`. |
| **H Error Message** | `testCaseContext.getMessage()`. Collapse newlines to `" \| "`, truncate to 500 chars (Excel's hard limit is 32767; 500 keeps Top Failures readable). Blank when PASSED. |
| **I Screenshot Path** | Formula `HYPERLINK("screenshots/<file>","View Image")`, only on FAILED. §9. |
| **J FailRank** | Writer sets `IF(F<n>="FAILED",COUNTIF($F$2:F<n>,"FAILED"),"")` on **every** appended row (template only carries it to row 11). |
| **L Legend** | Untouched. Rows 2–5 only. |

**Module derivation** (`ExcelReporter.resolveModule`):
1. Strip leading `Test Cases/`.
2. Drop a leading platform segment if it is `Mobile` or `Website`.
3. Drop a following app segment if it is `VCB`, `M2U`, or `G1A`.
4. Module = the next segment; if nothing remains but the file name → `Uncategorized`.
   - `Test Cases/Website/SauceDemo/TC01 Login Success` → `SauceDemo`
   - `Test Cases/Mobile/VCB/DuitNow/...` → `DuitNow`
5. Apply `report.module.alias.<raw>=<TemplateLabel>` from the properties file; if the result is not in the configured module list, remap to `Other` (the 4th configured module) and log once per unmatched module. Keeps COUNTIFS meaningful without silently dropping rows.

---

## 8. Environment string

Environment Coverage criteria are literal cell references (`COUNTIFS('Test Details'!$E$2:$E$200,$A20,...)` in `Executive Summary!B20`), so a row only counts if column E **string-equals** `A20`/`A21`/`A22`/`A23`. Rather than making the runtime match four hard-coded strings, **invert it**: the reporter owns those four label cells.

1. `report.environments` in `report.properties` is a comma-separated list of up to 4 labels. At suite start the writer stamps them into `A20:A23`, blanking unused slots. Default: `Windows11/Chrome`.
2. `report.environment.label`, if set, pins the per-row column-E value for the whole run. CI sets this.
3. If unset, derive **once**, lazily, on the first `endTestCase` where a driver existed:
   - Web: `"<hostOS>/<Browser><major>"` from `RunConfiguration.getHostProperties()[RunConfiguration.HOST_OS]` + `DriverFactory.getExecutedBrowser()?.getName()` + `DriverFactory.getBrowserVersion(driver)` (major only). `GlobalVariable.G_browserName/G_browserVersion` (`Profiles/default.glbl`: `chrome`/`146`) are the fallback when no driver is up.
   - Mobile: `"Android<osVersion>/<deviceModel>"` from `MobileDriverFactory.getDeviceOS()`, `getDeviceOSVersion()`, `getDeviceModel()` (all confirmed via `javap`).
   - Then **write the derived string into `A20`** (overwriting the config label) so COUNTIFS matches by construction, and mirror it into metadata cell `E4`.
4. Every row gets that single cached label. **One run = one environment.** Multi-device matrix runs are out of scope for v1 (§12, R4).

Truncate the derived label to ≤ 40 chars.

---

## 9. Screenshots

Capture **only when the mapped status is `FAILED`**, at step 2 of `endTestCase` — before `TestListener.groovy:186` (`closeBrowser`) and `:226` (`closeApplication`) can tear the driver down.

- Filename: `${String.format('%04d', rowNumber)}_${sanitize(displayId)}_a${attempt}.png` — sanitize to `[A-Za-z0-9._-]`, cap 80 chars. Sequence prefix guarantees uniqueness across retries and sorts the folder in execution order.
- Web: `WebUI.takeScreenshot(absolutePath, FailureHandling.OPTIONAL)`. Mobile: `Mobile.takeScreenshot(absolutePath, FailureHandling.OPTIONAL)`. Both also attach to the native Katalon report — a bonus, not a conflict.
- Guard with `MobileDriverFactory.getDriver() != null` / `DriverFactory.getWebDriver() != null` in try/catch, mirroring `MobileDriver.groovy:62-72`. On `ERROR` status the driver is often already dead — catch, leave column I blank, log a warning. **Never rethrow.**
- The existing unconditional `MobileDriver.takeScreenshot()` (`:206`) / `WebUIKeyword.takeScreenshot()` (`:184`) calls stay as they are.
- Column I gets the **relative** path with forward slashes. Excel and LibreOffice both resolve `HYPERLINK` relative to the workbook's directory.

---

## 10. Executive Summary metadata and the fixed lists

Written once at `startSuite`, into the yellow cells (addresses verified in `sheet1.xml`):

| Cell | Value |
|---|---|
| `B3` Project Name | `report.project.name` → `GlobalVariable.G_projectName` (`'VCB UAT5 Regression'` in `Profiles/default.glbl`) → `RunConfiguration.getProjectName()` |
| `E3` App Version | `report.app.version` only → else **blank** (§10a) |
| `B4` Execution Date | `now()` as `yyyy-MM-dd HH:mm` (string, matching the template's `2026-09-07`) |
| `E4` Environment | derived env label (§8) → `CommonPage.getEnvNameOnMae(...)` (§10a) → `RunConfiguration.getExecutionProfile()` |
| `B5` Triggered By | `report.triggered.by` → `"${RunConfiguration.getUserFullName()}@${hostName}"` |

Module labels → `A13:A16`; environment labels → `A20:A23`, from `report.properties`, blanking unused slots.

### 10a. App Version — resolved: do NOT source it from `VCBVersion.xlsx`

**Correction to an earlier claim in this plan:** `Test Data/KMS/VCBVersion.xlsx` **does exist** (`Profiles/*.glbl` set `G_VCBVersionFileName='/Test Data/KMS/VCBVersion.xlsx'`, and `CommonPage.getEnvNameOnMae()` at `Keywords/com/vcb/mobile/internal/pages/base/CommonPage.groovy:508` reads it every suite via `TestListener.groovy:134`). It was verified present and its contents read.

**But it is not a version file.** Despite the name, its three columns are:

| ID | Project Name | MAE Environment |
|---|---|---|
| 1 | MAE Regression | UAT |
| 2 | G1A | UAT2 |
| 3 | CASA | UAT5 |
| 4 | MAE UAT5 Regression | AUTOMATION-UAT5 |
| 5 | Credit Card | UAT-S2 |
| 6 | Credit Card UAT5 | UAT5-S2 |
| 7 | UAT1 | UAT |
| 8 | UAT5 | UAT5 |
| 9 | UAT4 | UAT4 |

`getEnvNameOnMae()` matches `GlobalVariable.G_projectName` against column B and returns **column C — the environment name**, which the listener then feeds to `switchEnvironmentInTestListener()`. There is no app/build version anywhere in the file. Sourcing `E3 App Version` from it would put an environment name (`AUTOMATION-UAT5`) into a cell labelled App Version.

**Decisions:**

1. **`E3` App Version comes only from `report.app.version`** (properties file or `-Dvcb.report.app.version=`). No derivation, no fallback to the profile name — a profile name is not a build number, and a plausible-looking wrong value in a bank report is worse than a blank. If unset, leave the cell **empty**; the template already paints these metadata cells yellow precisely because §2 of `Template_Design_Summary.md` designates them as *"ô nền vàng nhạt để người dùng tự điền"* (fill in by hand). A blank yellow cell is the designed state, not a defect.
2. If a real APK version is wanted later, the honest source is the device, not a spreadsheet: `adb shell dumpsys package <appPackage> | grep versionName`, read once at `startSuite` from the Appium `appPackage` capability. **Out of scope for v1** — add it only if the team asks.
3. **Reuse `getEnvNameOnMae()` for `E4` Environment instead.** That is what the file actually provides, it is already computed for env switching, and it gives the Executive Summary a meaningful environment label on mobile runs. Order for `E4`: `report.environment.label` → derived device/browser string (§8) → `getEnvNameOnMae(G_projectName, G_VCBVersionFileName)` → `RunConfiguration.getExecutionProfile()`. Wrap the call in try/catch — the underlying `ExcelKeywords.getExcelSheet()` throws if the path or sheet is missing, and reporting must never break a suite (R2).

### The fixed-list constraint — and why it stays fixed

`chart2` is bound to `'Executive Summary'!$A$13:$A$16` + `$C$13:$E$16`; `chart3` to `$A$20:$A$23` + `$E$20:$E$23`; `chart1` to `$L$12:$M$14`. Adding a 5th module row means editing `xl/charts/chart2.xml` series refs — which POI can only do via low-level `CTChart` surgery that is easy to get wrong and impossible to review in a diff.

**Decision: Module Breakdown and Environment Coverage stay exactly 4 rows in v1.** The listener writes the *labels* (cheap, chart-safe) but never inserts rows. Modules beyond the configured 3 collapse into a configured `Other` bucket, and every collapsed module name is logged once. If VCB later needs 20 modules, that is a **template redesign done by a human in LibreOffice** (add rows, drag COUNTIFS, re-drag chart ranges, re-save), after which only `report.modules` changes. Do not attempt chart-range editing from Groovy.

`custom-report/report.properties` (committed):

```properties
report.enabled=true
report.output.dir=
report.project.name=
report.app.version=
report.triggered.by=
report.modules=SauceDemo,Sanity,Uncategorized,Other
report.environments=Windows11/Chrome
report.environment.label=
report.retry.detection=auto
report.max.rows=
report.screenshot.on.fail=true
```

Precedence: `-Dvcb.<key>` system property > properties file > built-in default. (Katalon's `-g_` CLI flag only sets GlobalVariables, so a system property is the right override channel for CI.)

---

## 11. The 200-row limit — the real gap

Every summary formula hard-ends at row 200 (`$F$2:$F$200`, `$C$2:$C$200`, `$E$2:$E$200`, `$B$2:$B$200`, `$H$2:$H$200`, `$I$2:$I$200`, `$J$2:$J$200`). A 1400-case suite with up to 3 reruns can produce **>5000 rows**; everything past 200 would silently vanish from every KPI, every module count, and Top Failures — the exact "looks fine, is wrong" failure mode that gets a bank report signed off on bad numbers.

**Solution: expand programmatically at suite start, and hard-fail loudly if the budget is exceeded.**

1. In `startSuite`, parse `${projectDir}/${testSuiteContext.getTestSuiteId()}.ts` with `XmlSlurper`: `links = TestCaseEntity.testCaseLink.size()`, `reruns = numberOfRerun.toInteger()`.
   `maxRow = max(200, links * (reruns + 1) + 50)`, overridable via `report.max.rows`.
2. Rewrite formulas on **both** sheets: iterate all rows/cells, for `CellType.FORMULA` apply
   `formula.replaceAll(/\$([A-J])\$200\b/) { m, col -> "\$${col}\$${maxRow}" }` and `setCellFormula(...)` if changed.
   The regex is safe: `200` appears **only** as an absolute range end of that exact shape. `COUNTIF($F$2:F2,...)` has a relative end and is untouched.
3. Extend the two range-bound features on `Test Details` (`sqref="F2:F200"` for both):
   - Conditional formatting: `sheet.getSheetConditionalFormatting()`, for each of the 4 rules' formattings call `setFormattingRanges([CellRangeAddress.valueOf("F2:F$maxRow")] as CellRangeAddress[])`.
   - Data validation: POI has no public setter; use `((XSSFSheet)sheet).getCTWorksheet().getDataValidations().getDataValidationArray(0).setSqref([...])`.
   If either throws, catch and continue — losing the dropdown/colour past row 200 is cosmetic, losing the counts is not.
4. **Guard rail:** if the writer is asked to append beyond `maxRow`, stop appending, log a loud `KeywordUtil.logWarning("Excel report row budget exhausted at N; remaining results NOT recorded")`, and write that same warning into `Executive Summary!A6`. Silent truncation is not acceptable.
5. Rejected alternatives: whole-column refs (`$F:$F`) would make each of 1400 `COUNTIF`s scan 1M cells and are LibreOffice-recalc-hostile; per-suite file splitting is already what happens (one file per execution) and does not help a single 1400-case suite.

**Also fix the FailRank gap:** the J formula exists only on rows 2–11. The writer must emit it on every appended row, or Top Failures shows at most the failures in the first 10 rows.

### Formula evaluation caveats

- Functions used: `COUNTIF`, `COUNTIFS`, `IF`, `IFERROR`, `INDEX`, `MATCH`, `ROWS`, `HYPERLINK`. All implemented in POI 5.5.1, so `evaluateAll()` should succeed. **Verify in Phase 5** by asserting POI-evaluated `A9/B9/C9/D9/E9` against counts the reporter tracked itself — POI's handling of `"<>RETRY"` against blank cells has historically differed from Excel's, and `A9` depends on exactly that (`COUNTIFS(F,"<>RETRY",B,"<>")`).
- Wrap `evaluateAll()` in try/catch for `NotImplementedException` / `RuntimeException`. On failure, fall back to per-cell `evaluator.evaluateFormulaCell(cell)` in a loop that skips individual failures, then continue.
- **Always** call `workbook.setForceFormulaRecalculation(true)` before every save. This sets `fullCalcOnLoad` — the current `xl/workbook.xml` has no `<calcPr fullCalcOnLoad="true"/>`, so without it a viewer may trust stale caches.
- **Charts will not have refreshed caches.** `chart1/2/3` carry `c:numCache` values from the template's demo data. POI does not recompute chart caches. Excel and LibreOffice re-read source ranges on open, so a human sees correct charts; a headless xlsx→PDF converter that trusts caches would render the demo numbers. Document this: **the report is meant to be opened, not headlessly rendered.**

---

## 12. Risks and open questions

| # | Risk | Verify / mitigate |
|---|---|---|
| R1 | **POI round-trip of a LibreOffice-authored file.** `Template.xlsx` was written by Calc and contains `vmlDrawing1/2.vml` + `comments1/2.xml` + a `loext` extension. POI has known quirks re-saving LO VML comment drawings. If this breaks, everything else is moot. | **Phase 0 spike, before any other work.** Open → save unmodified → open the output in both LibreOffice and Excel. Confirm 2 sheets, 3 charts, CF colours, F-column dropdown, comments, 0 repair prompts. If VML breaks: strip the comments from the template (human edit in LO) and re-run the spike. |
| R2 | **A listener exception aborts the test case.** `TestListener.groovy` has no top-level try/catch anywhere. | Every public `ExcelReporter` method is `try { ... } catch (Throwable t) { KeywordUtil.logWarning(...); enabled = false }`. Verify by deliberately deleting `Template.xlsx` and confirming the suite still passes 5/5. |
| R3 | **Windows file lock.** A tester with `Report.xlsx` open in Excel makes `Files.move` fail with `AccessDeniedException` — mid-suite, every save. | Catch, retry once after 200 ms, then fall back to `Report_1.xlsx` (incrementing), log the new path, keep going. Never abort. Test by opening the file in Excel mid-run. |
| R4 | **Parallel / multi-device execution.** A Test Suite Collection running suites concurrently gives each execution its own JVM and its own timestamped output dir — no corruption, but N separate files. Concurrent writers to a *shared* file would corrupt it. | v1 is explicitly single-file-per-suite-execution. `maxConcurrentInstances=1` in `SauceDemoTests.ts:9` matches. Add `_${ProcessHandle.current().pid()}` to the output dir name so two runs starting in the same second cannot collide. A merge tool is out of scope. |
| R5 | **Retry vs. data-driven iterations** (§6). | Kill-switch `report.retry.detection=off`. Verify against a data-bound suite before enabling `auto` on VCB suites. |
| R6 | **Scale is unvalidated in this branch** — only 6 test cases exist here. | Phase 6 synthetic harness: a throwaway test case calling the writer 1400× with fake records; assert wall time of the append+save loop and that `maxRow` expansion produced correct KPI values. |
| R7 | **Module explosion.** The template allows 4 modules; a real VCB regression pack has dozens. Collapsing into `Other` produces a technically-correct but useless Module Breakdown. | Accepted for v1, logged per unmatched module. **Main follow-up:** the template needs a human redesign for wider module coverage (§10). |
| R8 | **Column G semantics** include app/browser startup (§5). | Document in `.claude/docs/architecture.md`; do not silently ship a number that looks like step time. |
| R9 | **Missing GlobalVariables per profile.** `Profiles/sanity-web.glbl` defines only `G_url` and `G_projectName`; `G_browserName`/`G_browserVersion` are null under that profile. | Every `GlobalVariable.*` read goes through a null-safe helper with a config/`RunConfiguration` fallback. |
| R10 | **`@AfterTestSuite` may never fire** (kill, OOM, agent timeout) → no `evaluateAll()`. | `setForceFormulaRecalculation(true)` on every save means a killed run still opens with correct KPIs in Excel/LibreOffice. This is why that flag is per-save, not per-suite. |
| **Q1** | ~~Where does the output live?~~ **RESOLVED: `custom-report/output/`**, gitignored, absolute path logged at suite end. Not `RunConfiguration.getReportFolder()`. | |
| **Q2** | ~~Where does App Version come from?~~ **RESOLVED — see §10a.** `VCBVersion.xlsx` is *not* a version source despite its name; App Version is config/CI-supplied and blank by default. | |

---

## 13. Phased breakdown

**Phase 0 — POI round-trip spike** (½ day)
Throwaway script: open `custom-report/Template.xlsx`, print POI version + sheet names, save to `custom-report/output/spike.xlsx`, close.
*Done when:* the output opens in LibreOffice **and** Excel with no repair prompt, 2 sheets, 3 charts intact, F-column dropdown present, CF colours intact. **If this fails, stop and re-scope (R1).**

**Phase 1 — Writer skeleton** (1 day)
`ExcelReportConfig`, `ExcelReportWriter` with: template copy, open, cache row-2 styles for A–J, clear sample data rows 2–11 **columns A–J only** (leave `L2:L5` legend), write metadata `B3/E3/B4/E4/B5`, module labels `A13:A16`, env labels `A20:A23`, `maxRow` computation from the `.ts`, `$200` range expansion, CF/DV sqref extension, atomic `save()`, `close()`.
*Done when:* invoked from a scratch test case, the output has empty `Test Details` rows 2+, intact legend, KPI cards reading 0, metadata filled, and every summary formula ends at the computed `maxRow`.

**Phase 2 — Row append + listener wiring** (1 day)
`TestResultRecord`, `ExcelReporter.startSuite/startTestCase/endTestCase/endSuite`, status mapping, module + description resolution, the 4 listener calls at the exact insertion points in §5.
*Done when:* `Test Suites/SauceDemo/SauceDemoTests` produces 5 rows, `No.` = 1..5 as **text**, correct module (`SauceDemo`), descriptions matching the `.tc` `<description>` fields, plausible durations, statuses matching Katalon's own report, and column J formulas present on all 5 rows.

**Phase 3 — Retry handling** (½ day)
Retry detection, back-rewrite to `RETRY`, decimal `No.`.
*Done when:* with TC02's expected text deliberately broken, `numberOfRerun=3` produces rows `2`, `2.1`, `2.2`, `2.3` where the first three are `RETRY` and the last carries the real status; `TOTAL TESTS` (A9) still reads 5, `RETRIES` (E9) reads 3. Then revert the deliberate break.

**Phase 4 — Screenshots** (½ day)
Capture on FAILED before driver teardown; `HYPERLINK` formula; sanitized filenames.
*Done when:* the failing case produces `screenshots/0003_TC02-....png`, column I shows "View Image", clicking it in Excel and LibreOffice opens the image, and Top Failures shows the same link.

**Phase 5 — Formula evaluation + save hardening** (1 day)
`evaluateAll()` at suite end with per-cell fallback; `setForceFormulaRecalculation(true)` per save; atomic move with lock retry (R3); `Report_N.xlsx` fallback.
*Done when:* KPI cards, Module Breakdown, Environment Coverage and Top Failures all show correct cached values when opened with a tool that does **not** recalc; POI-evaluated `A9/B9/C9/D9/E9` match the reporter's own tallies; and a run with the file held open in Excel completes with all results captured.

**Phase 6 — Config, scale, docs** (1 day)
Commit `custom-report/report.properties`, add `custom-report/output/` to `.gitignore`, add the architecture-doc subsection, run the 1400-row synthetic harness (R6). (Q1 and Q2 are already resolved — §12.)
*Done when:* the synthetic 1400-row run produces a correct, openable report; total append+save overhead is measured and recorded in the PR description; docs land.

---

## Key file references

- `Test Listeners/TestListener.groovy` — insertion points at lines **53, 157, 237, 253**; early returns forcing "first statement" placement at **64, 69, 160, 164, 172, 178, 202**; driver teardown at **186** and **226**
- `custom-report/Template.xlsx` — sample data to clear: `Test Details` rows 2–11 cols A–J; legend to preserve: `L2:L5`; FailRank formula present only on rows 2–11; all summary ranges end at `$200`
- `custom-report/Template_Design_Summary.md` — §3.4's "listener must not hand-write column J" is **incorrect** for rows 12+
- `Test Suites/SauceDemo/SauceDemoTests.ts:8-12` — `numberOfRerun=3`, `rerunImmediately=true`, `maxConcurrentInstances=1`
- `.classpath:95-105` — POI 5.5.1 + xmlbeans + commons-compress already available; **no `build.gradle` change**
- `Keywords/com/vcb/common/BaseKeyword.groovy`, `MobileDriver.groovy:62-72` — the static-keyword and null-safe-driver patterns to match
- `Profiles/default.glbl`, `Profiles/sanity-web.glbl` — `G_projectName`, `G_browserName/Version` availability differs per profile (R9)
