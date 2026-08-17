# Feature Specification: PayBill Common Test Case (AI Common)

**Feature Branch**: `20260724-155540-create-paybill-test`

**Created**: 2026-07-24

**Status**: Draft

**Input**: User description: "create a paybill test case common" (source material: `Data For AI/video/Paybill on VCB.mov` demo recording of the VCB app Bayar Bill flow, and `Data For AI/init data.txt` parameter defaults for that flow)

## Clarifications

### Session 2026-07-24

- Q: The spec covers Approve (FR-006–009), insufficient balance (FR-010), and amount boundary
  (FR-011) as explicit outcome branches, each with its own stop/verify behavior. Reject (declined
  authorization) was only mentioned in Edge Cases with no matching FR — should it get one? → A: Yes
  — add an explicit Reject FR mirroring FR-010/FR-011 (flow stops right after authorization, no
  status/notification/balance/history checks), consistent with the existing `PayBill_Open_Reject.tc`
  sibling test case already in this codebase.

## User Scenarios & Testing *(mandatory)*

<!--
  IMPORTANT: User stories should be PRIORITIZED as user journeys ordered by importance.
  Each user story/journey must be INDEPENDENTLY TESTABLE - meaning if you implement just ONE of them,
  you should still have a viable MVP (Minimum Viable Product) that delivers value.
-->

### User Story 1 - Reusable happy-path bill payment verification (Priority: P1)

A QA regression engineer needs one reusable, parameterized "common" test case that drives the
full Bayar Bill (Pay Bill) journey in the VCB app end-to-end — from login through a successful
payment to a chosen biller — so that every bill-payment regression scenario (approve, reject,
future-dated, insufficient balance, min/max amount, favourite biller, etc.) can call the same
common flow with different input parameters instead of each duplicating the same steps.

**Why this priority**: This is the foundational asset. Without it, no bill-payment scenario test
case can be authored; every other scenario in this module depends on this common flow existing
and being invokable with parameters.

**Independent Test**: Can be fully tested by invoking the common test case with a valid biller,
account, and amount, driving it through to an "Approve" authorization outcome, and confirming the
payment completes successfully with all verification checkpoints (status, notification, balance,
history) passing — independent of any other bill-payment scenario.

**Acceptance Scenarios**:

1. **Given** a logged-in user with Secure2u enabled and a sufficient account balance, **When** the
   user navigates to Bayar Bill, selects a registered biller, enters a valid payment amount, and
   approves the authorization request, **Then** the app shows a "Payment successful" status screen
   with a reference ID.
2. **Given** a completed successful payment, **When** the transaction settles, **Then** the user
   receives a push notification confirming the payment amount and biller, the account balance
   decreases by exactly the paid amount, and the transaction appears in the account/M2U history.
3. **Given** the common test case is invoked twice with different biller, amount, or account
   parameters, **When** each run executes, **Then** both runs complete using the same underlying
   script with no duplicated step logic.
4. **Given** a valid biller, account, and amount, **When** the user declines/rejects the
   authorization request instead of approving it, **Then** the flow stops immediately after the
   authorization step and does not reach the success status, push notification, balance change, or
   history checkpoints.

---

### User Story 2 - Insufficient balance handling (Priority: P2)

A QA engineer needs the common flow to support being driven with an account that does not have
enough balance to cover the bill amount, so that a "declined due to insufficient funds" scenario
can be verified without writing a separate script.

**Why this priority**: Insufficient-balance is a common real-world failure mode for bill payments
and is already anticipated as a parameter in the existing test data; it must be verifiable via the
same common flow to keep coverage complete without duplicating steps.

**Independent Test**: Can be fully tested by invoking the common flow with the insufficient-balance
parameter set against an account whose balance is lower than the payment amount, and confirming
the app blocks the payment with an appropriate on-screen message instead of proceeding to a
success status.

**Acceptance Scenarios**:

1. **Given** an account balance lower than the requested bill amount, **When** the user attempts
   to submit the payment, **Then** the app prevents the payment and shows an insufficient-balance
   message, and no push notification, balance change, or history entry for a successful payment is
   created.

---

### User Story 3 - Payment amount boundary validation (Priority: P3)

A QA engineer needs the common flow to support entering an amount outside the biller's allowed
minimum/maximum range, so that boundary validation (e.g. below the RM 1 minimum) can be verified
using the same reusable script.

**Why this priority**: Boundary validation is lower-risk than the core payment and failure paths
but is still an existing, parameterized requirement (min/max amount test data already exists) and
should reuse the same common flow rather than a bespoke script.

**Independent Test**: Can be fully tested by invoking the common flow with an amount below the
biller's minimum (or above its maximum) and confirming the app shows the corresponding validation
message and does not allow the payment to proceed.

**Acceptance Scenarios**:

1. **Given** a payment amount below the biller's configured minimum, **When** the user attempts to
   proceed, **Then** the app shows the minimum-amount error message and blocks progression to
   authorization.

---

### Edge Cases

- What happens when the payment is scheduled for a future date rather than paid immediately? The
  common flow must support this as a parameterized variant (confirmation screen shows a future
  date) without requiring separate script logic.
- What happens when the biller is saved/invoked as a favourite rather than selected fresh each
  time? The common flow must accept a pre-selected favourite biller as an alternate entry path.
- How does the system handle a biller ID or account that does not exist/is not registered? Out of
  scope for this common flow — covered by biller/account setup test data validity, not by this
  test case's own logic.

## Requirements *(mandatory)*

### Functional Requirements

- **FR-001**: The common test case MUST log in via Secure2u before performing any bill-payment
  action, reusing the existing shared login flow rather than re-implementing login.
- **FR-002**: The common test case MUST navigate to the Bayar Bill (Pay Bill) feature and allow the
  entry point (e.g. Quick Action shortcut vs. main menu) to be a parameter rather than hard-coded.
- **FR-003**: The common test case MUST allow the target biller, paying account, and payment amount
  to be supplied as parameters (not hard-coded), so different billers/accounts/amounts can be
  tested by passing different values.
- **FR-004**: The common test case MUST support reviewing the payment confirmation screen (showing
  details such as the paying account and payment date) before submission, with the confirmation
  fields to verify supplied as a parameter.
- **FR-005**: The common test case MUST perform the Secure2u/TAC/OTP authorization step using the
  generic authorization capability that auto-detects the presented method, and MUST allow the
  authorization outcome (e.g. approve vs. reject) to be a parameter.
- **FR-006**: When the authorization is approved and the transaction is valid, the common test case
  MUST verify the resulting status screen shows a success message and MUST capture the payment
  reference ID for use in later verification steps.
- **FR-007**: When the authorization is approved and the transaction is valid, the common test case
  MUST verify a push notification is received containing the payment amount, biller name, and
  reference ID.
- **FR-008**: When the authorization is approved and the transaction is valid, the common test case
  MUST verify the paying account's balance decreases by exactly the paid amount, comparing balance
  before and after the transaction.
- **FR-009**: When the authorization is approved and the transaction is valid, the common test case
  MUST verify the transaction appears in the account/M2U transaction history.
- **FR-010**: The common test case MUST support being invoked with an account whose balance is
  insufficient for the requested amount, and in that case MUST stop after showing the
  insufficient-balance message rather than proceeding to steps FR-006–FR-009.
- **FR-011**: The common test case MUST support being invoked with a payment amount outside the
  biller's allowed minimum/maximum range, and in that case MUST stop after showing the
  corresponding boundary-validation error rather than proceeding to authorization.
- **FR-012**: The common test case MUST be structured so that distinct bill-payment scenarios
  (approve, reject, insufficient balance, min/max boundary, future-dated, favourite biller) can
  each be authored as a thin caller that supplies different parameters to this one common flow,
  without duplicating its step logic.
- **FR-013**: The common test case MUST support being invoked with a declined/rejected
  authorization outcome, and in that case MUST stop immediately after the authorization step
  without proceeding to steps FR-006–FR-009 (status/reference ID, push notification, balance,
  history).

### Key Entities

- **Biller**: A registered bill-payment recipient (e.g. a utility provider), identified by a name
  and biller ID, against which a payment is made.
- **Payment Transaction**: A single bill-payment attempt with a paying account, amount, payment
  date (immediate or future), authorization outcome, and resulting reference ID (when successful).
- **Push Notification**: The confirmation message delivered to the user after a successful payment,
  containing the amount, biller name, and reference ID.
- **Account Balance**: The paying account's monetary balance before and after the transaction, used
  to confirm the correct amount was debited.
- **Transaction History Record**: The entry representing a completed payment as it appears in the
  account/M2U history, used to confirm the transaction was recorded.

## Success Criteria *(mandatory)*

### Measurable Outcomes

- **SC-001**: A new bill-payment test scenario (e.g. a different authorization outcome, amount, or
  biller) can be authored by supplying parameters to the common flow without writing any new
  step-by-step navigation or verification logic.
- **SC-002**: For every successful (approved, valid-amount, sufficient-balance) invocation of the
  common flow, all four post-payment checkpoints — status/reference ID, push notification, balance
  change, and history entry — are verified in 100% of runs.
- **SC-003**: For every invocation where the transaction is blocked or halted before completion
  (insufficient balance, out-of-range amount, or rejected authorization), the flow stops at the
  correct point and does not attempt the post-payment checkpoints, in 100% of such runs.
- **SC-004**: At least four distinct bill-payment scenarios (happy path, insufficient balance,
  amount boundary, rejected authorization) can each be run to completion using only this one common
  test case as their underlying implementation.

## Assumptions

- The common test case follows this project's established 7-step AI-generated common test case
  shape (login → module-specific middle steps → authorize → status/confirm → push notification →
  balance → history), consistent with other "AI Common" test cases already in this project.
- Login uses the existing shared "Login Secure2u" common test case rather than a bill-payment-
  specific login variant.
- Authorization uses the generic `authorize(status)` keyword (auto-detecting Secure2u
  biometric/PIN, TAC, or OTP) rather than a bill-payment-specific authorization variant, consistent
  with the closest existing sibling PayBill scripts in this project.
- The currency is Malaysian Ringgit (RM) and the target app is the VCB mobile app (Android only, per
  this project's current device coverage) — iOS/tablet is out of scope.
- Favourite-biller save/reuse (`isRunFav`) and future-dated payment (`isFuture`) are supported as
  optional parameters/entry variants of the same common flow, not as separate common test cases.
- Test data (biller name/ID, account, amount, expected messages) is supplied via this project's
  existing Test Data mechanism, consistent with how other modules' common test cases source their
  data.
- The demo recording (`Paybill on VCB.mov`) and `init data.txt` describe the same reference scenario
  (Bayar Bill → Indah Water Consortium, RM 1000, Approve, "Payment successful") used as the default/
  happy-path parameter set for User Story 1.
