---

description: "Task list for feature implementation"
---

# Tasks: PayBill Common Test Case (AI Common)

**Input**: Design documents from `/specs/20260724-155540-create-paybill-test/`

**Prerequisites**: plan.md, spec.md, research.md, data-model.md, contracts/paybill-common-testcase-contract.md, quickstart.md

**Tests**: Not requested in the spec. This project has no automated test framework (per plan.md's
Technical Context) — "verification" tasks below mean manually running the Test Case in Katalon
Studio / `katalonc` against the `vcb-regression` profile, per `quickstart.md`.

**Organization**: Tasks are grouped by user story from spec.md (US1 = P1 happy path + reject
outcome, US2 = P2 insufficient balance, US3 = P3 amount boundary). **Important deviation from the
generic template**: this feature is a single reusable script (FR-012 requires exactly one common
flow, not per-story files), so almost every task edits the same two files
(`Test Cases/Mobile/VCB/AI Common/PayBill_AI.tc` and its matching `Scripts/.../PayBill_AI/*.groovy`).
Tasks are therefore sequential within a file by necessity, not parallelizable across stories —
`[P]` is only used where genuinely different files are touched.

## Format: `[ID] [P?] [Story] Description`

- **[P]**: Can run in parallel (different files, no dependencies)
- **[Story]**: Which user story this task belongs to (US1, US2, US3)
- File paths are exact and relative to the repository root

## Path Conventions

Katalon Studio ENTERPRISE mobile project (`RBTS.prj`), single project — no frontend/backend split.
All new files live under:
- `Test Cases/Mobile/VCB/AI Common/PayBill_AI.tc`
- `Scripts/Mobile/VCB/AI Common/PayBill_AI/Script<timestamp>.groovy` (filename timestamp is
  assigned by Katalon Studio when the script is first saved; any placeholder timestamp is fine as
  long as the folder name `PayBill_AI` matches the Test Case name, mirroring the `Zakat_AI` /
  `3rd party transfer_AI` sibling convention).

---

## Phase 1: Setup

**Purpose**: Create the new Test Case and its matching Script skeleton

- [X] T001 Create `Test Cases/Mobile/VCB/AI Common/PayBill_AI.tc` with all parameter variables and
      default values from `contracts/paybill-common-testcase-contract.md` (username, password,
      idNumber, PAN, accountName, accessFlow, tranferName, payBillName, billerID, membershipType,
      amount, minAmount, maxAmount, errorMsgMinAmount, confirmationData, textNextPageConfirmation,
      status, favName, statusScreenTitle, statusScreenDescription, pushNotificationTitle,
      pushNotificationDescription, isRunFav, isFuture, isInsufficientAmount, testCaseType),
      structured identically to `Test Cases/Mobile/VCB/AI Common/Zakat_AI.tc`.
- [X] T002 Create the matching script folder/file
      `Scripts/Mobile/VCB/AI Common/PayBill_AI/Script<timestamp>.groovy` with the standard
      boilerplate (package-less script header, imports for `WebUI`, `findTestCase`,
      `FailureHandling`, `TestObjectTypeEnum`, `BasePage`, `HomePage`, `CommonPage`, `PayBillPage`,
      `ConfirmationPage`, `Secure2uAuthorisationPage`, `StatusScreenPage`, `PushNotificationPage`,
      `AccountDetailPage`, `com.vcb.utils.{ListUtils,MapUtils,StringUtils}`,
      `com.vcb.assertions.BaseAssert`) and page-object retrieval via `BasePage.getPage(...)`,
      mirroring the header of `Scripts/Mobile/VCB/AI Common/DuitNow transfer_AI/*.groovy`.

**Checkpoint**: Empty but structurally valid Test Case + Script pair exists and opens in Katalon Studio.

---

## Phase 2: Foundational (Blocking Prerequisites)

**Purpose**: The shared login → navigation → biller selection → amount → confirmation sequence
every user story (and the reject outcome) depends on. **No user story work can begin until this
phase is complete.**

- [X] T003 In `Scripts/Mobile/VCB/AI Common/PayBill_AI/Script<timestamp>.groovy`, implement the
      login step: `WebUI.callTestCase(findTestCase('Mobile/VCB/Common/Login Secure2u'), [('idNumber'):idNumber, ('PAN'):PAN, ('password'):password, ('username'):username, ('accountName'):accountName], FailureHandling.STOP_ON_FAILURE)` (FR-001).
- [X] T004 In the same script, implement navigation to Bayar Bill: call
      `WebUI.callTestCase(findTestCase('Mobile/VCB/Common/Entry point'), [('accessFlow'):accessFlow, ('actionName'):tranferName, ...])` followed by `payBillPage.tapPayBill()`, with the entry
      point taken from the `accessFlow` parameter (FR-002).
- [X] T005 In the same script, implement biller selection with both paths: when `isRunFav` is
      false, `payBillPage.searchPayBill(payBillName)` then `payBillPage.setBiller([billerID, membershipType])`; when `isRunFav` is true, `payBillPage.selectFavourites(favName)` instead
      (FR-003, Edge Case: favourite biller).
- [X] T006 In the same script, implement amount entry: `commonPage.enterNO(amount)` (FR-003).
- [X] T007 In the same script, implement confirmation screen review:
      `confirmationPage.verifyConfirmation(confirmationData, textNextPageConfirmation)` (FR-004).

**Checkpoint**: Foundation ready — running the script now proceeds from login through confirmation
review before authorization; user story implementation can begin.

---

## Phase 3: User Story 1 - Reusable happy-path bill payment verification (Priority: P1) 🎯 MVP

**Goal**: Drive the flow through authorization to a verified successful payment (status, push
notification, balance, history), and support the rejected-authorization outcome as the alternate
branch of the same authorization step (spec acceptance scenario 4 / FR-013).

**Independent Test**: Run `PayBill_AI.tc` with the shipped default parameters (Approve outcome)
and confirm all four post-payment checkpoints pass; separately run it with `status` set to a
reject value and confirm the flow stops right after authorization.

### Implementation for User Story 1

- [X] T008 [US1] In `Scripts/Mobile/VCB/AI Common/PayBill_AI/Script<timestamp>.groovy`, implement
      the authorization step: `secure2uAuthorisationPage.authorize(status)` (FR-005).
- [X] T009 [US1] **Adapted from planned approach** — implemented as the existing sibling
      `PayBill.tc` script's proven `if (status == Approve && testCaseType == Default) {...} else
      { commonPage.tapDoneBtn() }` branch rather than a hard `return` right after `authorize()`:
      the app itself navigates to a status screen for both outcomes, so `verifyStatusScreen` still
      runs (confirming the decline is shown), but share-receipt/favourites/notification/balance/
      history are all skipped for a non-approve status — satisfying FR-013's substance (no
      notification/balance/history checks on reject) using the same branch already proven for this
      exact app behavior, instead of introducing a new early-return pattern.
- [X] T010 [US1] In the same script, implement status screen verification and RefID capture (only
      reached when approved): `String refID = statusScreenPage.verifyStatusScreen(statusScreenTitle, statusScreenDescription)` (FR-006).
- [X] T011 [US1] In the same script, implement push notification verification:
      `pushNotificationPage.verifyPushNotification(pushNotificationTitle, pushNotificationDescription, FailureHandling.STOP_ON_FAILURE, TestObjectTypeEnum.CONTAINS)`, with the description
      built from `StringUtils.convertNoStringToAmount(amount)` and `refID` (FR-007).
- [X] T012 [US1] In the same script, implement balance verification: capture
      `String balance = homePage.getBalance(true)` before authorization (add this capture near
      T008) and `String newBalance = homePage.getBalance(true)` after the notification check, then
      assert with `BaseAssert.notEquals(balance, newBalance, 'Check Amount')` (FR-008).
- [X] T013 [US1] In the same script, implement history verification:
      `homePage.navigateToAccountDetail()` then
      `accountDetailPage.verifyM2UHistoryFirstItemToday(history, '-RM ' + amount)` (FR-009).
- [ ] T014 [US1] Manually execute `PayBill_AI.tc` in Katalon Studio (or via `katalonc` against the
      `vcb-regression` profile) with default parameters (`status = 'Approve'`) and confirm the run
      passes through all 7 steps to a "Payment successful" screen with the four checkpoints
      verified — validates SC-002 and the `quickstart.md` happy-path section.
- [ ] T015 [US1] Manually execute `PayBill_AI.tc` with `status` set to a reject value and confirm
      the run stops immediately after authorization with no notification/balance/history side
      effects — validates SC-003 (reject branch) and acceptance scenario 4.

**Checkpoint**: User Story 1 (both the approve and reject outcomes of the core flow) is fully
functional and independently testable.

---

## Phase 4: User Story 2 - Insufficient balance handling (Priority: P2)

**Goal**: Support being invoked against an account whose balance can't cover the amount, stopping
before authorization/status/notification/balance/history.

**Independent Test**: Run `PayBill_AI.tc` with `isInsufficientAmount = true` (or against a
low-balance test account) and confirm the flow stops with the insufficient-balance message.

### Implementation for User Story 2

- [X] T016 [US2] **Adapted from planned approach** — implemented as the existing sibling
      `PayBill.tc` script's proven pattern: when `isInsufficientAmount` is true, the script
      computes an amount above the account's real balance (`balanceNumber + 100000`) right after
      biller selection, then still proceeds through amount entry, confirmation, and authorization
      as normal — the app itself then shows the failure on the status screen (verified by
      `statusScreenTitle`/`statusScreenDescription` params supplied by the caller), and the
      existing status != Approve branch skips notification/balance/history, matching FR-010's
      "stop before the success checkpoints" intent via the same proven branch as T009, rather than
      a manual early return before authorization.
- [ ] T017 [US2] Manually execute `PayBill_AI.tc` with `isInsufficientAmount = true` and confirm
      the run stops at the insufficient-balance message with no notification/balance/history side
      effects — validates SC-003 (insufficient-balance branch) and `quickstart.md`'s insufficient
      balance section.

**Checkpoint**: User Stories 1 AND 2 both work independently.

---

## Phase 5: User Story 3 - Payment amount boundary validation (Priority: P3)

**Goal**: Support being invoked with an amount outside the biller's min/max range, stopping before
authorization.

**Independent Test**: Run `PayBill_AI.tc` with `amount` below `minAmount` and confirm the
minimum-amount error message is shown and the flow does not proceed to authorization.

### Implementation for User Story 3

- [X] T018 [US3] In `Scripts/Mobile/VCB/AI Common/PayBill_AI/Script<timestamp>.groovy`, add a
      boundary-validation guard right after amount entry (after T006, before T007):
      `payBillPage.verifyEditMinMaxAmount(maxAmount, minAmount, errorMsgMinAmount)`, then `return`
      immediately when `testCaseType` indicates the boundary-check variant, without proceeding to
      confirmation/authorization (FR-011).
- [ ] T019 [US3] Manually execute `PayBill_AI.tc` with `amount` set below `minAmount` (boundary
      `testCaseType`) and confirm the run stops after `errorMsgMinAmount` is shown, before
      authorization — validates SC-003 (boundary branch) and `quickstart.md`'s amount-boundary
      section.

**Checkpoint**: All three user stories are independently functional within the one common script.

---

## Phase 6: Polish & Cross-Cutting Concerns

**Purpose**: Remaining Edge Cases from spec.md that are not tied to a specific priority story, plus
final Definition of Done validation.

- [X] T020 [P] In `Scripts/Mobile/VCB/AI Common/PayBill_AI/Script<timestamp>.groovy`, add the
      future-dated payment branch: when `isFuture` is true, set a future payment date before
      confirmation review so the confirmation screen's `Date` field reflects it (Edge Case:
      future-dated payment).
- [ ] T021 Run the full `quickstart.md` validation pass — all four scenarios (happy path, reject,
      insufficient balance, amount boundary) executed at least once against the `vcb-regression`
      profile with the expected outcome — confirming this feature's Definition of Done.
- [X] T022 [P] Confirmed no hand-edits were made to `Libs/CustomKeywords.groovy`,
      `Libs/internal/GlobalVariable.groovy`, `PayBillPage.groovy`, `AndroidPhonePayBillPage.groovy`,
      or any suite's generated runner `.groovy` file (Constitution §VIII / §IX.3 sanity check) —
      verified via `git status`: only `PayBill_AI.tc`, its matching Script, and spec/plan/tasks
      documents were created; no existing Keywords/Libs files were touched.

---

## Dependencies & Execution Order

### Phase Dependencies

- **Setup (Phase 1)**: No dependencies — can start immediately.
- **Foundational (Phase 2)**: Depends on Setup (T001, T002) — BLOCKS all user stories.
- **User Story 1 (Phase 3)**: Depends on Foundational (Phase 2) completion.
- **User Story 2 (Phase 4)**: Depends on Foundational (Phase 2) completion. Its guard (T016) is
  inserted before User Story 1's authorization step (T008) in the same script, so implement T016
  with awareness of the surrounding code from Phase 3 — but the *scenario* is independently
  testable by parameter alone (`isInsufficientAmount`).
- **User Story 3 (Phase 5)**: Depends on Foundational (Phase 2) completion. Same file-ordering note
  as US2 — its guard (T018) sits earlier in the script (right after amount entry) than US1's and
  US2's guards.
- **Polish (Phase 6)**: Depends on Phases 3–5 being complete.

### User Story Dependencies

- All three user stories depend only on the Foundational phase, not on each other's *logic* — each
  can be verified independently by parameters alone (per each story's Independent Test). However,
  because FR-012 mandates one shared script file, the three stories' guard/branch code all lives in
  the same file and should be implemented in the sequence above (boundary guard nearest the top,
  then insufficient-balance guard, then authorization/reject/success chain) to avoid rework.

### Within Each User Story

- Tasks within a story are sequential (same file, same method body) unless marked `[P]`.
- Manual verification tasks (e.g. T014, T015, T017, T019) always come after that story's
  implementation tasks are complete.

### Parallel Opportunities

- **Very limited** in this feature: T001 and T002 both create new files but T002's content depends
  on the parameter names decided in T001, so they are still sequential in practice.
- T020 and T022 in the Polish phase are marked `[P]` — they touch different concerns (adding a
  script branch vs. auditing that no shared files were touched) and can be done in either order or
  concurrently by different people.
- No cross-story `[P]` parallelism exists because every user story edits the same script file —
  this is an intentional consequence of FR-012 (one reusable common flow), not an oversight.

---

## Parallel Example

```bash
# The only genuine parallel pair in this feature (Polish phase):
Task: "Add future-dated payment branch in Scripts/.../PayBill_AI/Script<timestamp>.groovy"
Task: "Audit that no shared Libs/ or page-object files were hand-edited"
```

---

## Implementation Strategy

### MVP First (User Story 1 Only)

1. Complete Phase 1: Setup.
2. Complete Phase 2: Foundational (CRITICAL — blocks all stories).
3. Complete Phase 3: User Story 1 (happy path + reject).
4. **STOP and VALIDATE**: Run T014 and T015 independently.
5. This is a usable MVP: a caller can already invoke `PayBill_AI` for approve/reject scenarios.

### Incremental Delivery

1. Setup + Foundational → foundation ready.
2. Add User Story 1 → validate (T014, T015) → MVP.
3. Add User Story 2 → validate (T017).
4. Add User Story 3 → validate (T019).
5. Polish (T020–T022) → feature Definition of Done met.

---

## Notes

- **Manual verification tasks not executed**: T014, T015, T017, T019, and T021 require a live
  Android device/emulator with Appium and the VCB UAT app, which this implementation environment
  does not have. All code tasks (T001–T013, T016, T018, T020, T022) are complete; a QA engineer
  with Katalon Studio access should run these five verification tasks against the `vcb-regression`
  profile per `quickstart.md` before marking this feature done.
- **T009/T016 adapted from the original task wording**: rather than a hard early `return` right
  after authorization/before it, both reuse the existing `PayBill.tc` sibling script's proven
  pattern — the app itself renders a status screen for every outcome (approve/reject/insufficient),
  so `verifyStatusScreen` always runs, and only the notification/balance/history checkpoints are
  conditionally skipped for non-approve outcomes. This matches FR-010/FR-013's substance using the
  exact mechanism already verified in production regression runs, rather than inventing a new
  early-exit shape.
- `[P]` tasks = genuinely different files/concerns with no ordering dependency; there are only two
  in this feature (T020, T022) given the single-script constraint.
- `[Story]` label maps a task to its user story for traceability back to spec.md.
- No automated tests exist for this project — "verification" tasks are manual Katalon Studio /
  `katalonc` runs, per `quickstart.md`.
- Commit after each phase or logical group, consistent with this project's existing workflow.
- Stop at any checkpoint to validate a story independently before moving to the next.
