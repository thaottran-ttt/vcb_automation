# Research: PayBill Common Test Case (AI Common)

All Technical Context items were resolvable directly from this codebase (no external research
needed) — the project already has three near-identical "AI Common" scripts and a non-AI-Common
PayBill implementation to source from. No `NEEDS CLARIFICATION` markers remain.

## Decision: Which sibling script to model the new script on

- **Decision**: Model `PayBill_AI` on the existing `Zakat_AI` / `3rd party transfer_AI` /
  `DuitNow transfer_AI` scripts in `Scripts/Mobile/VCB/AI Common/`, using `DuitNow transfer_AI` as
  the primary reference specifically for the "Check History" step (the only one of the three that
  already implements it).
- **Rationale**: These are the only existing scripts that follow the exact 7-step skeleton this
  project's rules mandate for `AI Common` test cases. Reusing their proven `callTestCase`/page-object
  idioms avoids inventing a new pattern.
- **Alternatives considered**: Writing the flow from scratch by generalizing from the non-AI-Common
  `Test Cases/Mobile/VCB/G1A/Paybill/Common/PayBill.tc` script directly — rejected because that
  script is invoked differently (as a shared helper called by many pre-existing scenario test
  cases with its own parameter contract) and does not follow the `AI Common` 7-step shape or
  naming convention.

## Decision: Login step

- **Decision**: `WebUI.callTestCase(findTestCase('Mobile/VCB/Common/Login Secure2u'), [('idNumber'):idNumber, ('PAN'):PAN, ('password'):password, ('username'):username, ('accountName'):accountName], FailureHandling.STOP_ON_FAILURE)`.
- **Rationale**: Literal, mandated call per `.claude/rules/ai-common-testcase-skeleton.md` step 1;
  identical across every existing `AI Common` script.
- **Alternatives considered**: None — this is a fixed project convention.

## Decision: Navigation / middle steps (PayBill-specific)

- **Decision**: Reuse the existing `PayBillPage` keyword surface exactly as the non-AI-Common
  `PayBill.tc` script does: `payBillPage.tapPayBill()` → `payBillPage.searchPayBill(payBillName)` →
  `payBillPage.setBiller([billerID, membershipType])` → `commonPage.enterNO(amount)` →
  `confirmationPage.verifyConfirmation(confirmationData, textNextPageConfirmation)`. Entry point
  (Quick Action vs. menu) is driven through the shared `Entry point` common test case
  (`WebUI.callTestCase(findTestCase('Mobile/VCB/Common/Entry point'), [('accessFlow'):accessFlow, ('actionName'):actionName, ...])`),
  matching FR-002's "entry point is a parameter" requirement. The favourite-biller path
  (`payBillPage.selectFavourites(payBillName)`) and the min/max boundary path
  (`payBillPage.verifyEditMinMaxAmount(maxAmount, minAmount, errorMsgMinAmount)`, then early return)
  are both already implemented keywords on `PayBillPage`, satisfying FR-011 and the favourite Edge
  Case without new page-object work.
- **Rationale**: `PayBillPage`/`AndroidPhonePayBillPage` already implement every middle-step
  interaction this feature needs; per Constitution §III a new page object is only required when
  none exists, which is not the case here.
- **Alternatives considered**: Adding new keywords to `PayBillPage` for a "simplified" flow —
  rejected as unnecessary duplication (Constitution §IX.2 Simplicity First).

## Decision: Authorization step

- **Decision**: Use `secure2uAuthorisationPage.authorize(status)`, matching the `Zakat_AI` /
  `PayBill`-family scripts, with `status` as a parameter (`'Approve'` or a reject value) —
  satisfying FR-005 and FR-013.
- **Rationale**: `.claude/rules/ai-common-testcase-skeleton.md` step 3 prefers the generic
  `authorize(status)` (auto-detects Secure2u biometric/PIN, TAC, or OTP) over a domain-specific
  variant, "unless a reference script for that exact module uses the specific one" — the closest
  PayBill/Zakat sibling scripts use the generic form, so no exception applies here.
- **Alternatives considered**: `transferPage.authorizeS2UV5BioPin(status)` (used by `3rd party
  transfer_AI`) — rejected because it's specific to the transfer module, not PayBill/Zakat.

## Decision: Status / RefID, push notification, balance, history steps

- **Decision**:
  - Status/RefID: `String refID = statusScreenPage.verifyStatusScreen(statusScreenTitleData, statusScreenDescriptionData)`.
  - Push notification: `pushNotificationPage.verifyPushNotification(pushNotificationTitle, pushNotificationDescription, FailureHandling.STOP_ON_FAILURE, TestObjectTypeEnum.CONTAINS)`, with the description built via `StringUtils.convertNoStringToAmount(amount)` and refID interpolation, matching the `${param1}`/`${param2}` template already present in `Data For AI/init data.txt`.
  - Balance: `String balance = homePage.getBalance(true)` captured before submission and again after, compared via `BaseAssert.notEquals(balance, newBalance, 'Check Amount')`.
  - History: `homePage.navigateToAccountDetail()` then `accountDetailPage.verifyM2UHistoryFirstItemToday(history, '-RM ' + parsedAmount)`, the pattern already proven in `DuitNow transfer_AI`.
- **Rationale**: Directly reuses proven, already-tested keyword calls from sibling `AI Common`
  scripts — satisfies FR-006–FR-009 with zero new keyword code.
- **Alternatives considered**: None needed; these are the only implementations of these
  verification steps in the codebase.

## Decision: Outcome branching (insufficient balance / boundary / reject)

- **Decision**: All three non-happy-path branches (FR-010, FR-011, FR-013) are expressed as early
  returns/guards in the same script after their respective triggering step, mirroring how
  `verifyEditMinMaxAmount(...)` already short-circuits in the existing non-AI-Common `PayBill.tc`
  script (`return null` after the boundary-error assertion). Insufficient balance and rejected
  authorization follow the same early-return shape, just triggered after `enterNO(amount)`/
  confirmation and after `authorize(status)` respectively.
- **Rationale**: Keeps one script satisfying FR-012 (single common flow, thin future callers)
  without conditional-branch duplication of the full 7-step body.
- **Alternatives considered**: Splitting into multiple scripts per outcome — rejected; it
  contradicts FR-012 and the spec's explicit goal of one reusable common flow.
