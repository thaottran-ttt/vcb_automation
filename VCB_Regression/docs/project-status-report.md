# VCB Automation — Project Status Report

**Date:** 2026-09-03
**Repo:** `vcb_automation_scripting` (project root: `VCB_Regression/`)
**Branch reviewed:** `feature/claude-setup` @ `b57309e` (working tree clean)
**Scope:** Compare `VCB_Regression/.claude/docs/` and `VCB_Regression/.claude/rules/` against the
code actually present in the repository, and report the current state of the automation project.

---

## 1. Executive summary

The **framework layer is fully in place**; the **test-asset layer is almost empty**.

`Keywords/`, `Object Repository/`, `Profiles/`, `Test Data/` and `Data Files/` carry the complete,
inherited VCB regression framework (252 keyword files, 449 object-repository entries, 16 execution
profiles). But only **6 test cases, 6 scripts and 1 test suite** exist — all of them Website, all of
them the SauceDemo / Sanity pilot built during 2026-08-19 → 2026-08-23. There are **zero mobile test
cases**.

The AI guidance in `.claude/docs/` and `.claude/rules/` was written against the **original ~1400-case
mobile regression pack**, not against this repository. It is accurate about the *framework*
(page-object factory, keyword hierarchy, profiles) but describes a *test corpus, a package path and a
workflow that do not exist here*. Following the rules file as written today is impossible: it
mandates a folder (`Test Cases/Mobile/VCB/AI Common/`) and a reference case (`Login Secure2u`) that
are not in the repo.

There is also **one hard blocker**: the only test suite in the project (`SauceDemo/SauceDemoTests`)
cannot be executed in suite mode — `TestListener` starts the mobile Appium app for any suite whose
id does not contain `KMS_M2U`.

**Priority actions:** fix the TestListener suite gate (§5.1), re-point the AI docs at the actual
package path and web tier (§4), and either restore mobile assets or rewrite the AI Common rule for
the web pilot (§4.3).

---

## 2. What the repository actually contains

Counts are tracked files on `feature/claude-setup`.

| Area | Path | Count | State |
|---|---|---|---|
| Mobile keywords | `Keywords/com/vcb/mobile/` | 166 | Complete — 14 public pages, 76 base, 75 androidphone |
| Web keywords | `Keywords/com/vcb/m2u/` | 66 | Complete — 23 base, 22 chrome, 19 safari |
| Common / utils / assertions | `Keywords/com/vcb/{common,utils,assertions,helper,service}` | 20 | Complete (`MobileDriver` 904 LOC, `WebUIKeyword` 291 LOC) |
| Object Repository | `Object Repository/` | 449 | 353 Mobile/VCB, 61 Website/M2U, 19 Website/SauceDemo, 4 Website/Sanity |
| Execution profiles | `Profiles/*.glbl` | 16 | Complete |
| Test Data / Data Files | `Test Data/`, `Data Files/` | 139 / 371 | Inherited from the original pack; unused by current cases |
| **Test Cases** | `Test Cases/` | **6** | **5 SauceDemo + 1 Sanity. All Website. No Mobile.** |
| **Scripts** | `Scripts/` | **6** | 1:1 mirror of the above |
| **Test Suites** | `Test Suites/` | **1** | `SauceDemo/SauceDemoTests` only |
| Test Listener | `Test Listeners/TestListener.groovy` | 1 | Inherited, mobile-first |
| CI | — | 0 | No `.github/`, no pipeline config |

### The current test pack

| Test case | Script | In suite? |
|---|---|---|
| `Website/SauceDemo/TC01 Login Success` | `Script1766800000001.groovy` | ❌ |
| `Website/SauceDemo/TC02 Login Locked User` | `Script1766800000002.groovy` | ❌ |
| `Website/SauceDemo/TC03 Add Item To Cart` | `Script1766800000003.groovy` | ✅ |
| `Website/SauceDemo/TC04 Remove Item From Cart` | `Script1766800000004.groovy` | ✅ |
| `Website/SauceDemo/TC05 Complete Checkout` | `Script1766800000005.groovy` | ✅ |
| `Website/Sanity/Web Login Smoke Test` | `Script1755417600000.groovy` | ❌ (no Sanity suite exists) |

All five SauceDemo scripts follow a consistent shape — resolve the page via
`WebBasePage.getPage(WebSauceDemoPage)`, call business methods, `catch → takeScreenshot() → rethrow`
— and all business logic lives in `WebSauceDemoPage`, with `.tc` variables supplying the expected
values. That is good, and it is the de-facto authoring pattern for this repo today. **It is not
written down anywhere in `.claude/docs/`.**

---

## 3. What `.claude/docs/` and `.claude/rules/` say

| File | Content | Verdict |
|---|---|---|
| `.claude/docs/architecture.md` | Directory map, three-tier mobile page-object model, `BasePage.getPage()` reflection factory, keyword hierarchy (`BaseKeyword → MobileDriver → BasePage`), execution profiles, suite naming | **Mostly accurate for the framework**, with 3 factual errors (§4.1) and one large omission — the web tier (§4.2) |
| `.claude/docs/authoring-test-cases.md` | `.tc` + `.groovy` pair anatomy, the 3 script invariants, script→common call chain, `CommonPage` vs `MobileDriver`, the 7-step AI Common skeleton with a 3-module worked comparison | **Accurate as framework theory; inapplicable in practice** — every example references mobile cases absent from this repo |
| `.claude/rules/ai-common-testcase-skeleton.md` | Enforceable checklist: the fixed 7-step shape (Login S2U → middle → Authorize → Status → Push Notification → Balance → History) for `Test Cases/Mobile/VCB/AI Common/` | **Currently unenforceable** — target folder and every referenced keyword are absent (§4.3) |

Both `.gitkeep` files remain, i.e. the folders were scaffolded and only partly filled.

---

## 4. Gap analysis: docs vs. repository

### 4.1 Factual errors in the docs

| # | Claim | Where | Reality |
|---|---|---|---|
| G1 | Public keyword layer is `Keywords/com/vcb/vcb/pages/*.groovy`; base pages are `com.vcb.vcb.internal.pages.*` | `architecture.md` §"Keyword architecture"; repeated in `.specify/memory/constitution.md` §III | **No file exists under `com/vcb/vcb`** (0 matches). The real path is `Keywords/com/vcb/mobile/pages/` (14 files) and `com.vcb.mobile.internal.pages.*`. `authoring-test-cases.md` already uses the correct `com.vcb.mobile.*` — the two docs contradict each other. |
| G2 | "~1400+ test cases across modules (transfers, DuitNow, Secure2u, Tabung, Fixed Deposit, onboarding)" | `VCB_Regression/CLAUDE.md` §Project overview; constitution §I | 6 test cases, none of them mobile. Commit `392c5a4 "remove unecessary tests"` (2026-08-22) removed the corpus. |
| G3 | "`default` / most others — `G_appId = com.vcb.app` (production-style bundle ID)" | `architecture.md` §Execution profiles | `Profiles/default.glbl` has `G_appId = 'com.vcb.app.uat'` and has been **repurposed for the SauceDemo pilot**: `G_url = 'https://www.saucedemo.com/'` (line 30), `G_userName = 'standard_user'` (line 107), `G_password = 'secret_sauce'` (line 114). |
| G4 | "Never hand-edit `Libs/CustomKeywords.groovy`" | `CLAUDE.md`, `architecture.md`, `authoring-test-cases.md` | The file is not in the repo — `VCB_Regression/.gitignore` excludes it. Harmless, but the instruction reads as if the file were present. |
| G5 | Suites are grouped by initiative (`KMS`, `KMS_M2U`, `Vcb`, `Sanity`, `Hollow out`, `MVP5 S2U V2 to V5`) | `architecture.md` §Test suite naming | Only `Test Suites/SauceDemo/` exists. This is not cosmetic — the `KMS_M2U` name is load-bearing in `TestListener` (§5.1). |

### 4.2 Omission: the web/M2U tier is undocumented

The docs describe the web layer only as "much thinner" and list `com.vcb.m2u.pages.WebCommon`. In
reality the web tier is where **100% of the current test assets live**, and it has its own parallel
architecture that no doc describes:

- **A second factory:** `WebBasePage.getPage()` (`Keywords/com/vcb/m2u/internal/pages/base/WebBasePage.groovy:38`)
  mirrors `BasePage.getPage()` but branches on `ExecutionWebSetting.browserType` → package
  `...pages.chrome` / `...pages.safari`, prefix `Chrome` / `Safari` (Chrome is the default).
- **A different page-object convention.** The documented mobile contract is *abstract base declares
  locators → device class implements them*. The web tier inverts this: 20 of 23 base classes are
  **concrete**, hold `static TestObject` locator methods calling `findTestObject(...)` directly, and
  the browser subclass is an **empty shell** (`ChromeWebSauceDemoPage.groovy` is 6 lines, body `{}`).
  Only `WebRsaPage` and `WebReloadPage` are abstract. This is a legitimate, consistent convention —
  it simply contradicts the only architecture the docs describe, so an AI following
  `architecture.md` would author web pages the wrong way.
- **Browser entry point:** web test cases never open the browser themselves. `TestListener`
  `@BeforeTestCase` calls `WebCommon.openBrowserAndBypassSecure(G_url, ...)` for any test case under
  `G_websiteTestCaseFolderPath` (`['Test Cases/Website']`). `TC01` depends on this implicitly.

### 4.3 The AI Common rule is currently unenforceable

`.claude/rules/ai-common-testcase-skeleton.md` is the repo's one hard rule. Every anchor it names is
missing:

| Rule requires | Present? |
|---|---|
| `Test Cases/Mobile/VCB/AI Common/` + `Scripts/Mobile/VCB/AI Common/` | ❌ `Test Cases/Mobile/` does not exist |
| Step 1 — `callTestCase(findTestCase('Mobile/VCB/Common/Login Secure2u'))` | ❌ not in repo |
| Steps 3–7 keywords (`secure2uAuthorisationPage.authorize`, `statusScreenPage.*`, `pushNotificationPage.*`, `homePage.getBalance`, `M2U History`) | ⚠️ the **page classes** exist under `Keywords/com/vcb/mobile/internal/pages/base/`, but no test case exercises any of them |
| "Find the closest existing sibling script for that module" | ❌ there are no sibling mobile scripts to copy from |

The related Speckit artifact `specs/20260724-155540-create-paybill-test/` (spec, plan, tasks,
contracts — 8 files) is a **mobile Paybill** feature planned against that missing corpus, and
`CLAUDE.md` still advertises it as the "Active Speckit plan". It cannot be implemented as written.

---

## 5. Findings in code and configuration

### 5.1 🔴 HIGH — `SauceDemoTests` suite cannot run in suite mode

`Test Listeners/TestListener.groovy:235-244`:

```groovy
@BeforeTestSuite
def sampleBeforeTestSuite(TestSuiteContext testSuiteContext) {
    GlobalVariable.G_isRunTestSuite = true
    String testSuiteId = testSuiteContext.getTestSuiteId()
    if (testSuiteId.contains('KMS_M2U')) {   // <- web mode gate
        GlobalVariable.G_runWebsite = true
        return
    }
    BaseKeyword.startApplication()           // <- mobile: Mobile.startExistingApplication(G_appId)
}
```

The suite id is `Test Suites/SauceDemo/SauceDemoTests` — it does not contain `KMS_M2U`, so the
listener falls through to `BaseKeyword.startApplication()` → `Mobile.startExistingApplication('com.vcb.app.uat')`,
which fails without a connected Appium device and the VCB app installed.

Running the cases **individually** works, because `@BeforeTestCase` (lines 72-74) flips `G_runWebsite`
to true when the test case id falls under `G_websiteTestCaseFolderPath` (`['Test Cases/Website']`)
and then opens the browser. Only the suite-level path is broken — i.e. the CI-shaped path.

**Fix options:** (a) rename/relocate the suite so its id contains `KMS_M2U`; (b) generalise the gate
to test the suite's test cases against `G_websiteTestCaseFolderPath`; (c) add a `G_runWebsite=true`
default to a dedicated web profile and have `@BeforeTestSuite` respect it. (b) or (c) is preferable —
(a) preserves a naming accident as an API.

### 5.2 🟠 MEDIUM — Suite covers 3 of 6 test cases

`Test Suites/SauceDemo/SauceDemoTests.ts` links only TC03, TC04, TC05. TC01 (Login Success) and TC02
(Login Locked User) are authored and complete but never executed by any suite, and the Sanity smoke
test has no suite at all. Whatever runs in CI later will silently skip half the pack.

### 5.3 🟠 MEDIUM — The SauceDemo pilot rides on the `default` profile

`Profiles/default.glbl` now mixes two identities: SauceDemo web values (`G_url`, `G_userName`,
`G_password`) alongside VCB mobile values (`G_appId = com.vcb.app.uat`,
`G_projectName = 'VCB UAT5 Regression'`, `G_pinCode`). `default` is also the profile every mobile
path falls back to. `Profiles/sanity-web.glbl` already establishes the right pattern — a small,
purpose-named, self-describing profile. Recommend a `saucedemo.glbl` on the same model and restoring
`default` to a VCB-only baseline.

### 5.4 🟡 LOW — Safari tier is incomplete

Three base pages have a Chrome implementation but no Safari one:
`WebSettingsPage` (top-level), `paytransfer/WebPayPageJomPAY`, `rsapage/WebRsaPage`. Under the
`safari` profile, `WebBasePage.getPage()` on any of these throws `ClassNotFoundException`. None are
reachable from the current 6 test cases, so this is latent, not active.

### 5.5 🟡 LOW — Two filename/class-name mismatches

| File | Declared class |
|---|---|
| `Keywords/com/vcb/mobile/internal/pages/base/SercurityQuestionPage.groovy` | `SecurityQuestionPage` |
| `Keywords/com/vcb/m2u/internal/pages/chrome/rsapage/ChromeWebRSAPage.groovy` | `ChromeWebRsaPage` |

Both resolve correctly at runtime (Groovy compiles by declared class name, and `getPage()` builds the
class name from the type, not the filename), so nothing is broken. But they defeat file search and
IDE navigation, and the `Sercurity` typo makes the mobile base/androidphone folders look mismatched
(76 vs 75) under any automated mirror check — including the one `architecture.md` tells an AI to
enforce.

### 5.6 🟡 LOW — Credentials committed in profiles

`Profiles/vcb-regression.glbl` carries `G_password = 'oxhWAdmPKdsI6woWWukVxg=='` (Katalon-encrypted)
and `G_pinCode = '111111'`, both with `<protected>false</protected>`. UAT-only values, so low
severity — but worth a decision now, before real environments are wired in, on whether credentials
live in profiles at all or come from environment variables / Katalon's protected-value mechanism.

### 5.7 🟡 LOW — Stray generated file tracked

`Libs/TempTestCase1787484741564.groovy` is committed. `.gitignore` excludes `Libs/CustomKeywords.groovy`
and `Libs/S*.groovy` but not `Libs/TempTestCase*` — add the pattern and untrack the file.

### 5.8 ℹ️ INFO — No CI

No `.github/`, no Jenkins/Azure pipeline. Execution is Katalon Studio (GUI) or `katalonc` run by hand.
Both the constitution (§V follow-up TODO) and `CLAUDE.md` acknowledge this.

---

## 6. Recommended next actions

**Unblock execution**

1. Fix the `@BeforeTestSuite` web gate (§5.1) — this is the one change that makes the pack runnable
   end-to-end, and it is a prerequisite for any CI.
2. Add TC01, TC02 and the Sanity smoke test to suites (§5.2).

**Make the AI docs match the repo** — these files are read on every AI-assisted task, so each error
propagates into generated code:

3. Correct `com/vcb/vcb` → `com/vcb/mobile` in `architecture.md` **and** in
   `.specify/memory/constitution.md` §III (G1).
4. Correct the test-case count and the `default` profile description (G2, G3).
5. Add a **web tier** section to `architecture.md`: `WebBasePage.getPage()`, the chrome/safari split,
   the concrete-base + static-locator convention, and `TestListener`'s browser bootstrap (§4.2).
6. Add a **web authoring** section to `authoring-test-cases.md` documenting the pattern the five
   SauceDemo cases already follow — it is consistent and worth codifying while it is still fresh.

**Decide the mobile question** (blocking for §4.3)

7. Either restore the mobile test corpus (or a representative subset including `Login Secure2u`) so
   `ai-common-testcase-skeleton.md` and the Paybill spec become executable, **or** rewrite the rule
   for the web pilot and mark the Paybill spec dormant. Right now it is neither — the rule reads as
   enforceable but silently cannot be followed. This is a project-direction call, not a code fix.

**Hygiene**

8. Split a `saucedemo.glbl` profile out of `default` (§5.3).
9. Rename `SercurityQuestionPage.groovy` and `ChromeWebRSAPage.groovy` to match their classes (§5.5).
10. Add the three missing Safari page classes, or drop Safari from `BrowserTypeEnum` until needed (§5.4).
11. Untrack `Libs/TempTestCase*.groovy` (§5.7); decide on credential handling (§5.6).

---

## 7. Appendix — how this was verified

All figures come from the tracked tree on `feature/claude-setup` @ `b57309e`:

```bash
git ls-files "Test Cases" "Scripts" "Test Suites"        # 6 / 6 / 2 files
git ls-files Keywords | awk -F/ '{print $3"/"$4}' | sort | uniq -c
git ls-files | grep -c "com/vcb/vcb"                     # -> 0   (G1)
grep -n "KMS_M2U" "Test Listeners/TestListener.groovy"   # -> 239 (5.1)
grep -n "saucedemo\|standard_user\|secret_sauce" Profiles/default.glbl  # -> 30,107,114 (G3)
```

Mirror check used for §5.4 / §5.5 — compare base class names against the device/browser tier with the
prefix stripped:

```bash
git ls-files Keywords/com/vcb/mobile/internal/pages/base \
  | sed 's#.*/base/##; s#\.groovy$##' | sort > b.txt
git ls-files Keywords/com/vcb/mobile/internal/pages/androidphone \
  | sed 's#.*/androidphone/##; s#\.groovy$##; s#\(^\|/\)AndroidPhone#\1#' | sort > a.txt
comm -23 b.txt a.txt
```

Nothing was executed against a device, browser or backend; findings are from static inspection of the
repository.
