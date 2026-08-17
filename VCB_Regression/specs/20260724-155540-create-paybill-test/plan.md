# Implementation Plan: PayBill Common Test Case (AI Common)

**Branch**: `20260724-155540-create-paybill-test` | **Date**: 2026-07-24 | **Spec**: [spec.md](./spec.md)

**Input**: Feature specification from `/specs/20260724-155540-create-paybill-test/spec.md`

**Note**: This template is filled in by the `/apac-sdlc-speckit-plan` command. See `.specify/templates/plan-template.md` for the execution workflow.

## Summary

Create one reusable, parameterized "AI Common" test case (`Test Cases/Mobile/VCB/AI Common/PayBill_AI.tc`
+ matching `Scripts/Mobile/VCB/AI Common/PayBill_AI/*.groovy`) that drives the full VCB Bayar Bill
(Pay Bill) journey — login → navigate/select biller/enter amount → confirmation → Secure2u/TAC/OTP
authorization → status/RefID → push notification → balance → history — following this project's
fixed 7-step AI Common skeleton. The script accepts the authorization outcome (approve/reject),
amount, and account as parameters so it can be driven through the happy path, insufficient-balance,
amount-boundary, and rejected-authorization branches (spec FR-006–FR-013) without any new page
objects: it reuses the existing `PayBillPage` / `AndroidPhonePayBillPage` keyword layer, the shared
`Login Secure2u` and `Entry point` common test cases, and the same verification helpers already used
by the sibling `3rd party transfer_AI` / `Zakat_AI` / `DuitNow transfer_AI` scripts.

## Technical Context

**Language/Version**: Groovy, executed as Katalon Studio ENTERPRISE test case scripts (`type MOBILE`) driving Appium.

**Primary Dependencies**: This project's own keyword/page-object layer (`Keywords/com/vcb/vcb/...`, `Keywords/com/vcb/common/...`), specifically the existing `PayBillPage` (base) / `AndroidPhonePayBillPage` (Android) pair, `HomePage`, `CommonPage`, `ConfirmationPage`, `Secure2uAuthorisationPage`, `StatusScreenPage`, `PushNotificationPage`, `AccountDetailPage`, and utility keywords `StringUtils`, `ListUtils`, `MapUtils`, `BaseAssert`. No third-party libraries beyond `katalon-core` (already an IDE-only dependency).

**Storage**: N/A — parameters are Katalon Test Case variables with default values declared in the `.tc` XML (mirroring `Test Cases/Mobile/VCB/AI Common/Zakat_AI.tc` and the values captured in `Data For AI/init data.txt`), not read from CSV/XLSX. `Test Data/KMS/PayBills.xlsx` exists but is used by other (non-AI-Common) PayBill suites, not this script.

**Testing**: No automated unit/integration test framework exists for this project. Verification is: (1) opening the new Test Case in Katalon Studio and running it standalone against the `vcb-regression` execution profile, and (2) running it via the `katalonc` console runner in a Test Suite, per this repo's existing convention (`CLAUDE.md` Build & Test section).

**Target Platform**: Android only (Appium-driven VCB app under UAT profile `com.vcb.app.uat`), consistent with this project's current device coverage (iOS/tablet `DeviceTypeEnum` cases are declared but unimplemented project-wide).

**Project Type**: Katalon Studio ENTERPRISE mobile test-automation project (`RBTS.prj`) — single project, no frontend/backend split.

**Performance Goals**: N/A — this is a functional regression test case, not a performance benchmark.

**Constraints**: Must follow the mandatory 7-step AI Common skeleton (`.claude/rules/ai-common-testcase-skeleton.md`); steps 1 and 3 must use the literal `Login Secure2u` call and the generic `authorize(status)`-style capability, not a domain-specific authorization variant, unless the closest sibling script requires otherwise; must not hand-edit `Libs/CustomKeywords.groovy` or `Libs/internal/GlobalVariable.groovy`; must not introduce a new page object since `PayBillPage`/`AndroidPhonePayBillPage` already exist and cover this flow.

**Scale/Scope**: One new Test Case + matching Script pair, parameterized to support 4 outcome branches identified in the spec (approve/happy-path, insufficient balance, amount boundary, rejected authorization). Additional scenario-specific "thin caller" test cases (e.g. `PayBill_Open_Approve`, `PayBill_Open_Reject`) are explicitly out of scope for this feature — FR-012 only requires this common flow to be *callable* that way, not that those callers be authored now.

## Constitution Check

*GATE: Must pass before Phase 0 research. Re-check after Phase 1 design.*

- **Section III (Architecture Principles) — page-object pairing**: PASS. No new page object is introduced; `PayBillPage` (abstract base) and `AndroidPhonePayBillPage` (Android concrete) already exist and already implement everything this flow needs (search/select biller, set biller ID, enter amount, min/max validation, favourites). `BasePage.getPage()` reflection continues to resolve correctly with no changes.
- **Section III — Android-only**: PASS. No iOS/tablet implementation is added or required.
- **Section IV (Code Style)**: PASS. The new script matches the exact structure/style of the existing `3rd party transfer_AI`, `Zakat_AI`, and `DuitNow transfer_AI` scripts (same `callTestCase`/`findTestCase` idioms, same page-object retrieval pattern).
- **Section V (Testing Standards)**: PASS. Verification is manual execution via Katalon Studio / `katalonc` against `vcb-regression`, consistent with this project's only available verification method (no CI, no automated framework).
- **Section VI (Security)**: PASS. All test data (username, PAN, biller ID, amount) mirrors the non-production, UAT-only values already present in `Data For AI/init data.txt` and sibling `AI Common` `.tc` files; no production secrets are introduced.
- **Section VIII (Definition of Done)**: PASS (by design) — the new Test Case will have a matching Script (1:1 mirror) under `AI Common`, and no generated `Libs/` files or suite runners will be hand-edited.
- **Section IX.2 (Simplicity First)**: PASS. Single script, no new abstractions, no configurability beyond the parameters the spec explicitly requires.
- **Section IX.3 (Surgical Changes)**: PASS. Only new files are added under `Test Cases/Mobile/VCB/AI Common/` and `Scripts/Mobile/VCB/AI Common/`; nothing existing is modified.

No violations. Complexity Tracking table is not needed.

## Project Structure

### Documentation (this feature)

```text
specs/20260724-155540-create-paybill-test/
├── plan.md              # This file (/apac-sdlc-speckit-plan command output)
├── research.md          # Phase 0 output
├── data-model.md         # Phase 1 output
├── quickstart.md         # Phase 1 output
├── contracts/
│   └── paybill-common-testcase-contract.md   # Phase 1 output — caller-facing parameter/output contract
└── tasks.md              # Phase 2 output (/apac-sdlc-speckit-tasks command — NOT created by /apac-sdlc-speckit-plan)
```

### Source Code (repository root)

```text
Test Cases/Mobile/VCB/AI Common/
└── PayBill_AI.tc                       # NEW — the reusable common test case (parameters, defaults)

Scripts/Mobile/VCB/AI Common/
└── PayBill_AI/
    └── Script<timestamp>.groovy        # NEW — matching Groovy script (Katalon-generated filename)

# Reused, unmodified:
Keywords/com/vcb/vcb/internal/pages/base/paybill/PayBillPage.groovy
Keywords/com/vcb/vcb/internal/pages/androidphone/paybill/AndroidPhonePayBillPage.groovy
Test Cases/Mobile/VCB/Common/Login Secure2u.tc
Test Cases/Mobile/VCB/Common/Entry point.tc
```

**Structure Decision**: This is a single existing Katalon Studio ENTERPRISE mobile project
(`RBTS.prj`) — no new project or module is created. The feature adds exactly one new
`Test Cases/` + `Scripts/` pair under the existing `AI Common` folder convention (matching the
sibling `3rd party transfer_AI`, `Zakat_AI`, `DuitNow transfer_AI`, `FTT transfer_AI` common test
cases), and reuses every existing page object, keyword, and shared common test case already present
in the codebase. This keeps the change surgical (Constitution §IX.3) and avoids any new
abstraction (§IX.2).

## Complexity Tracking

> No Constitution Check violations — table intentionally omitted.
