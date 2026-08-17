<!--
Sync Impact Report
Version change: [TEMPLATE] → 1.0.0 (initial ratification)
Modified principles: n/a (first concrete fill of Sections I–VIII from brownfield scan)
Added sections: I–VIII populated with project-specific content; IX unchanged (locked)
Removed sections: none
Templates requiring updates:
  ✅ .specify/templates/plan-template.md — generic "Constitution Check" gate, no changes needed
  ✅ .specify/templates/spec-template.md — no agent-specific or outdated references found
  ✅ .specify/templates/tasks-template.md — no agent-specific or outdated references found
  ✅ .claude/skills/apac-sdlc-speckit-constitution/ — this command file, generic, no changes needed
Follow-up TODOs:
  - Section IV: no linter/formatter config exists in repo — confirm before enforcing style rules in code review
  - Section V: no automated test framework exists; verification is manual suite execution — revisit if CI is introduced
  - Section VI: GitHub PAT exposure in git remote URL was flagged separately this session; not a constitution item, track/rotate outside this doc
-->

# Project Constitution

<!-- This file governs all AI-assisted development on this project.
     Sections I–VIII are project-specific — fill them in before running /apac-sdlc-speckit-specify.
     Section IX is pre-filled and applies without modification to every project. -->

---

## I. Project Overview

MAE_Regression (`RBTS.prj`) is Maybank's regression test pack for the MAE mobile app (plus a thinner
M2U website layer), built on Katalon Studio ENTERPRISE. It contains ~1400+ Groovy test cases covering
modules such as transfers, DuitNow, Secure2u, Tabung, Fixed Deposit, and onboarding. Primary users are
Maybank QA/regression engineers who run these suites against UAT/SIT environments ahead of releases.

---

## II. Technology Stack

- Groovy scripting on Katalon Studio ENTERPRISE (`type MOBILE`), driving Appium.
- `build.gradle` declares only `katalon-core:7.0.0` for IDE dependency resolution — there is no
  gradle build/test task.
- Execution: Katalon Studio (GUI), or the Katalon CLI console runner (`katalonc`, selecting
  `-testSuitePath` and `-executionProfile`).
- Environments are defined in `Profiles/*.glbl`: `mae-regression` (UAT app id
  `com.maybank2u.life.uat`, SIT backend), `default`/others (production-style bundle id), plus
  UAT1/2/4/5, TAC, SIT, production, credit-card, test, and browser (safari/firefox) profiles.
- No CI configuration exists in this repo.

---

## III. Architecture Principles

- `Test Cases/` and `Scripts/` mirror each other 1:1 by folder name; `Test Data/` holds the CSV/XLSX
  data per module.
- Three-tier page-object architecture: public keyword layer (`Keywords/com/maybank/mae/pages/`) →
  abstract base layer (`internal/pages/base/<domain>/`) → device-specific implementation
  (`internal/pages/androidphone/<domain>/AndroidPhone<Name>Page`).
- `BasePage.getPage()` is the factory: it resolves the concrete implementation class via reflection
  based on `ExecutionSetting.deviceType`. Every new abstract base page MUST have a matching
  `AndroidPhone<Name>Page`, even if empty — otherwise `Class.forName()` throws
  `ClassNotFoundException`.
- Only Android device implementations exist; iOS/tablet `DeviceTypeEnum` cases are declared but
  unimplemented.
- `Libs/CustomKeywords.groovy`, `Libs/internal/GlobalVariable.groovy`, and a suite's generated
  `.groovy` runner (next to its `.ts`) are Katalon-Studio auto-generated. Never hand-edit them.

---

## IV. Code Style & Conventions

- No linter or formatter configuration exists in this repo (no ESLint/checkstyle equivalent). Match
  the existing Groovy style already present in `Keywords/` and `Scripts/`.
- Keyword-layer methods are `@Keyword`-annotated; device implementation classes follow the
  `AndroidPhone<Name>Page` naming convention.
- (Inferred default — please confirm) No additional naming rules exist beyond the directory/class
  conventions described in Section III.

---

## V. Testing Standards

- There is no unit/integration test framework — the Katalon Test Cases/Scripts themselves are the
  tests, executed via Katalon Studio or the `katalonc` console runner against a chosen Test Suite and
  execution profile.
- No CI pipeline and no automated coverage threshold exist.
- (Inferred default — please confirm) Verification means running the affected Test Suite against the
  target profile and confirming a pass in Katalon Studio, console output, or `Reports/`.

---

## VI. Security & Compliance

- `Test Data/` CSV/XLSX files likely contain test account credentials. These MUST remain
  test/UAT-only — never production secrets.
- Environment-specific sensitive values (PIN, device UDID, app IDs, URLs) live in `Profiles/*.glbl`
  as `G_*` globals, not hardcoded in scripts.
- (Inferred default — please confirm) No formal compliance framework is defined for this project.

---

## VII. Performance Targets

(Inferred default — please confirm) No performance targets are defined. This is a functional
regression pack, not a load/performance test suite. No benchmark or monitoring configuration exists
in the repo.

---

## VIII. Definition of Done

(Inferred default — please confirm)
- A new or changed test case has a matching `Scripts/` entry (1:1 mirror).
- A new page object has both an abstract base class and a concrete `AndroidPhone<Name>Page`.
- The affected Test Suite has been executed against the target profile (e.g. `mae-regression`) with
  no regressions in adjacent test cases.
- No hand-edits were made to generated `Libs/` files or a suite's generated runner `.groovy`.

---

## IX. Behavioral Principles

The preceding sections define **what** the codebase looks like. This section defines
**how** code gets written. These principles apply to every spec, plan, task, and
implementation — without exception.

### 1. Think Before Coding

**Don't assume. Don't hide confusion. Surface tradeoffs.**

Before implementing:
- State your assumptions explicitly. If uncertain, ask.
- If multiple interpretations exist, present them — don't pick silently.
- If a simpler approach exists, say so. Push back when warranted.
- If something is unclear, stop. Name what's confusing. Ask.

### 2. Simplicity First

**Minimum code that solves the problem. Nothing speculative.**

- No features beyond what was asked.
- No abstractions for single-use code.
- No "flexibility" or "configurability" that wasn't requested.
- No error handling for impossible scenarios.
- If you write 200 lines and it could be 50, rewrite it.

Ask yourself: "Would a senior engineer say this is overcomplicated?" If yes, simplify.

### 3. Surgical Changes

**Touch only what you must. Clean up only your own mess.**

When editing existing code:
- Don't "improve" adjacent code, comments, or formatting.
- Don't refactor things that aren't broken.
- Match existing style, even if you'd do it differently.
- If you notice unrelated dead code, mention it — don't delete it.

When your changes create orphans:
- Remove imports/variables/functions that YOUR changes made unused.
- Don't remove pre-existing dead code unless asked.

The test: Every changed line should trace directly to the user's request.

### 4. Goal-Driven Execution

**Define success criteria. Loop until verified.**

Transform tasks into verifiable goals:
- "Add validation" → "Write tests for invalid inputs, then make them pass"
- "Fix the bug" → "Write a test that reproduces it, then make it pass"
- "Refactor X" → "Ensure tests pass before and after"

For multi-step tasks, state a brief plan:
```
1. [Step] → verify: [check]
2. [Step] → verify: [check]
3. [Step] → verify: [check]
```

Strong success criteria let you loop independently. Weak criteria ("make it work")
require constant clarification.

---

**Version**: 1.0.0 | **Ratified**: 2026-07-15 | **Last Amended**: 2026-07-15
