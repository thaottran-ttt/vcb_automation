# Quickstart: Verifying the PayBill Common Test Case

## Prerequisites

- Katalon Studio ENTERPRISE with `RBTS.prj` open, or the `katalonc` console runner.
- An Android device/emulator with the MAE app (UAT build, `com.maybank2u.life.uat`) and Appium
  configured, per this project's existing setup (no new setup required by this feature).
- A test account with Secure2u enabled and a known balance ≥ the test payment amount.

## Run the happy path (manual, Katalon Studio)

1. Open `Test Cases/Mobile/MAE/AI Common/PayBill_AI.tc`.
2. Confirm/adjust the default parameter values (`payBillName`, `billerID`, `amount`, `status =
   'Approve'`) — the shipped defaults reproduce the reference scenario from
   `Data For AI/init data.txt` (Bayar Bill → Indah Water Consortium, RM 1000, Approve).
3. Run the Test Case standalone against the `mae-regression` execution profile.
4. Expected result: the run passes through all 7 steps and ends with a "Payment successful" status
   screen, a verified push notification, a balance decrease of RM 1000, and a matching history
   entry (spec SC-002).

## Run via console (katalonc)

```bash
katalonc -noSplash -runMode=console \
  -projectPath="$(pwd)/RBTS.prj" \
  -retry=0 \
  -testSuitePath="Test Suites/<a suite that includes PayBill_AI>" \
  -executionProfile="mae-regression"
```

(No dedicated Test Suite is created by this feature — add the new Test Case to an existing or ad
hoc suite to run it via console.)

## Verify the other three outcome branches

- **Insufficient balance**: set `isInsufficientAmount = true` (or use an account whose balance is
  below `amount`) and re-run. Expected: the flow stops after the insufficient-balance message; no
  notification/balance/history checks are attempted (SC-003).
- **Amount boundary**: set `amount` below `minAmount` (or above `maxAmount`) and re-run. Expected:
  the flow stops after the boundary-error message (`errorMsgMinAmount`), before authorization
  (SC-003).
- **Rejected authorization**: set `status` to a reject value and re-run. Expected: the flow stops
  immediately after the authorization step, before the status/notification/balance/history checks
  (SC-003).

## Definition of done for this feature

- `Test Cases/Mobile/MAE/AI Common/PayBill_AI.tc` and its matching `Scripts/Mobile/MAE/AI
  Common/PayBill_AI/*.groovy` exist and mirror each other 1:1.
- No changes to `Libs/CustomKeywords.groovy`, `Libs/internal/GlobalVariable.groovy`, or any
  existing page object / keyword file.
- All four scenarios above (happy path, insufficient balance, boundary, reject) have been run at
  least once against the `mae-regression` profile with the expected outcome.
