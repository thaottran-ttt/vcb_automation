# Architecture

## Project type

Katalon Studio ENTERPRISE mobile automation project (`RBTS.prj`, `type MOBILE`). Groovy-based. Not a typical
buildable app — Katalon Studio (GUI) or the Katalon CLI runner (`katalonc`) drives execution; there is no
gradle test task (`build.gradle` only pulls in `katalon-core` for IDE/dependency resolution).

## Directory map

- `Test Cases/` — `.tc` XML test case definitions, mirrored 1:1 by folder name under `Scripts/`.
- `Scripts/` — Groovy implementation for each test case (`Mobile/VCB/...`, `Website/M2U/...`).
- `Test Suites/` — `.ts` XML suites; each has a sibling `.groovy` (Katalon-generated runner, don't hand-edit).
- `Keywords/com/vcb/...` — all custom keyword/page-object code (see Keyword architecture below).
- `Object Repository/` — UI element locators (Katalon `.rs`/XML objects), organized by platform/module.
- `Profiles/*.glbl` — environment/global-variable sets (see Execution profiles below).
- `Test Data/` — CSV/XLSX data files per module, referenced from test cases.
- `Libs/CustomKeywords.groovy` and `Libs/internal/GlobalVariable.groovy` — **Katalon Studio auto-generated**,
  regenerated from `Keywords/` and `Profiles/*.glbl` respectively. Never hand-edit.
- `Include/config/log.properties`, `console.properties` — Katalon console/TestOps/TestCloud run configuration.

## Keyword architecture: three-tier page objects with device-type factory

1. **Public keyword layer** — `Keywords/com/vcb/vcb/pages/*.groovy` (e.g. `Customise.groovy`, `Login.groovy`).
   `@Keyword`-annotated methods called directly from test scripts. Delegates to the factory below. A separate,
   older/simpler set of concrete classes also lives here (`Login`, `Vcb2u`, `WelcomeOnboard`, etc.) used by
   scripts that predate the abstract base/androidphone split — not every script goes through `BasePage.getPage()`.
2. **Abstract base layer** — `Keywords/com/vcb/vcb/internal/pages/base/<domain>/<Name>Page.groovy`. Almost
   all of these are `abstract class ...Page extends BasePage` declaring locator methods with no implementation
   (a handful, e.g. `ApplyCardPage`, `RecipientReferencePage`, are concrete).
3. **Device-specific implementation layer** — `Keywords/com/vcb/vcb/internal/pages/androidphone/<domain>/AndroidPhone<Name>Page.groovy`,
   mirroring the same domain subfolders, providing concrete `TestObject` locators.

`BasePage.getPage(SomePage)` is the factory: it takes the abstract base class, swaps
`internal.pages.base.<pkg>.<Class>` → `internal.pages.androidphone.<pkg>.AndroidPhone<Class>`, and resolves the
concrete implementation via `Class.forName()` reflection, based on `ExecutionSetting.deviceType`
(`iPad` / `iPhone` / `AndroidPhone` / `AndroidTablet`, derived from Appium `platformName` + device width).
Instances are cached in a static `Map` keyed by class name.

**Gotcha:** the `AndroidPhone<Name>Page` class must physically exist at the exact expected path even when it
adds zero locator overrides — `Class.forName()` throws `ClassNotFoundException` if it's missing, so a new
abstract base page always needs an (even empty) Android counterpart before `getPage()` can resolve it. Only
`AndroidPhone*` implementations exist today; iOS/tablet variants are not implemented despite the enum cases.

`BasePage` extends `MobileDriver` (in `com.vcb.common`) and also caches UI element paths loaded from
`Keywords/com/vcb/vcb/ui/vcbUI.json`.

## Common keyword library (`com.vcb.common`)

- `BaseKeyword` — root of the keyword hierarchy; central `handleError(message, FailureHandling)` (screenshot +
  mark failed/warning/stop based on `FailureHandling`), `startApplication()` (TestCloud vs local launch via
  `runFromTestCloud()`), `setFailureHandling()`.
- `MobileDriver extends BaseKeyword` — all Appium-wrapping keywords: tap/setText/scroll/swipe variants, XPath-based
  element/position lookups (`findElementByXml`, `tapElemtByXml`), pinch-zoom, PIN entry (`enterPinNo`). Almost every
  method wraps the underlying `Mobile.*` (Katalon `MobileBuiltInKeywords`) call in try/catch → `handleError`.
- `Android extends MobileDriver` — Android-only ADB/shell-backed keywords (`clearAppData`, `killApp`, `setText`
  via `mobile:shell input`), W3C/JS scroll gestures, clipboard.
- `ExecutionSetting` — computed once per test, detects platform + device type from Appium capabilities/device width.
- `WebUIKeyword` / `ExecutionWebSetting` — website (M2U) equivalents of the above, much thinner (`com.vcb.m2u.pages`
  has only `WebCommon.groovy`).
- `MobileThread` — coordinates a delay when both web and mobile threads run together (`G_isRunWebsiteAndMobile`).
- `com.vcb.utils` — `StringUtils`, `DateUtils`, `RegexUtils`, `MapUtils`, `ListUtils`, `TouchActionUtils`.
- `com.vcb.assertions` — `BaseAssert`, `NumberAssert`, `StringAssert`.
- `com.vcb.helper` — `TestCloudHelper` (remote WebDriver/session helpers), `RestAPIHelper`.
- `com.vcb.service.LogInService`.

## Execution profiles (`Profiles/*.glbl`)

Each profile is a `GlobalVariableEntities` XML defining `G_*` globals (app ID, bundle ID, base URL, timeouts, PIN,
device UDID, etc.) consumed as `GlobalVariable.G_xxx` at runtime. Profiles point at different environments/apps:

- `vcb-regression` — UAT: `G_appId = com.vcb.app.uat`, `G_url` on `vcbmobile.uat.vcb.com.vn` (SIT backend).
  `G_appIDTestCloud` is a placeholder (`'a'`) — must be set before running via TestCloud.
- `default` / most others — `G_appId = com.vcb.app` (production-style bundle ID).
- Other profiles (`UAT1`/`UAT2`/`UAT4`/`UAT5`, `TAC`, `SIT`, `production`, `credit-card`, `test`, browser profiles
  `safari`/`firefox`) target other environments/app variants/browsers.

Selecting a profile in Katalon Studio (or `-executionProfile=<name>` on the CLI) is what determines which
backend/app the run targets — check the active profile before debugging an "unexpected environment" issue.

## Test suite naming convention

Each logical suite exists as a pair: `Test Suites/<Group>/<Name>.ts` (Katalon XML suite) + `<Name>.groovy`
(Katalon-generated runner script, produced when the suite is opened/saved in Katalon Studio — don't hand-edit).
Suites are grouped by initiative (`KMS`, `KMS_M2U`, `Vcb`, `Sanity`, `Hollow out`, `MVP5 S2U V2 to V5`, etc.),
not strictly by app module.

## Excel test reporting (`com.vcb.report`)

`com.vcb.report.ExcelReporter` is a static facade called from four one-line hooks in
`Test Listeners/TestListener.groovy` (`startSuite`/`startTestCase`/`endTestCase`/`endSuite`, each the
*first* statement of its hook — the listener has early returns and driver-teardown lines below them).
At suite start it copies `custom-report/Template.xlsx` to a timestamped working copy under
`custom-report/output/<suite>_<timestamp>_<pid>/Report.xlsx` (gitignored) and keeps the `XSSFWorkbook`
open in memory for the whole suite, appending one row per test-case attempt and saving
(atomic tmp-file + move) after every test case — "cuốn chiếu": a crash loses at most one result.
`ExcelReportWriter` owns all Apache POI code (cell/formula/style plumbing, the `$X$200` → `$X$<maxRow>`
row-budget expansion, atomic save with a Windows-file-lock retry); `ExcelReportConfig` loads
`custom-report/report.properties` (`-Dvcb.<key>` overrides the file). Every public `ExcelReporter` method
swallows its own exceptions — a reporting failure can never fail a test case or a suite.

Two things to know when reading column G ("Execution Time (s)") or debugging retries:
- **Column G includes app/browser startup time** — there is no listener hook earlier than
  `@BeforeTestCase`, so the measured window starts before `BaseKeyword.startApplication()` /
  `openBrowserAndBypassSecure` runs. It is wall time from listener entry to listener exit, not pure
  test-step time.
- **Retry detection is heuristic**, not a first-class Katalon signal: it uses row adjacency (under
  `rerunImmediately=true`) or `RunConfiguration.getSuiteRerunIndex()` advancing. A test case that
  legitimately runs twice in one suite, or a data-bound test case whose earlier iteration failed, can be
  misread as a retry — see `custom-report/TestListener_Excel_Report_Plan.md` §6 for the known limitation
  and the `report.retry.detection=off` kill switch.

Full design (data model, save strategy, module/environment derivation, the 200-row template limit and
why charts stay fixed at 4 categories): `custom-report/TestListener_Excel_Report_Plan.md`.
