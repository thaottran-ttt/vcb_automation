# Contract: `Mobile/VCB/AI Common/PayBill_AI` common test case

This is the "interface" this feature exposes: the parameter signature future scenario-specific
"thin caller" test cases (e.g. an eventual `PayBill_AI_Reject`, `PayBill_AI_InsufficientBalance`)
will invoke via `WebUI.callTestCase(findTestCase('Mobile/VCB/AI Common/PayBill_AI'), [...])`, per
FR-012. Field names mirror `Data For AI/init data.txt` so existing test data stays reusable.

## Inputs (Test Case variables)

| Parameter | Type | Required | Default (reference scenario) |
|---|---|---|---|
| `username`, `password`, `idNumber`, `PAN` | string | yes | per `init data.txt` |
| `accountName` | string | yes | `'Current Account-i'` |
| `accessFlow` | string | yes | `'Quick Action'` |
| `tranferName` | string | yes | `'Bayar Bill'` |
| `payBillName` | string | yes | `'Indah Water Consortium'` |
| `billerID` | string | yes | `'29542784'` |
| `membershipType` | string | no | `'ABC123'` |
| `amount` | string | yes | `'1000'` |
| `minAmount` / `maxAmount` | string | only for boundary variant | `'1'` / `'9999999'` |
| `errorMsgMinAmount` | string | only for boundary variant | `'The minimum payment for this transaction is RM 1'` |
| `confirmationData` | list<string> | yes | `['Pay from', 'Date']` |
| `textNextPageConfirmation` | string | yes | `'Pay Now'` |
| `status` | string | yes | `'Approve'` (or a reject value) |
| `favName` | string | only when `isRunFav` | `'Paybill'` |
| `statusScreenTitle` | list<string> | only on success path | `['Payment successful']` |
| `statusScreenDescription` | list<string> | no | `[]` |
| `pushNotificationTitle` | string | only on success path | `'Vcb2u: Payments'` |
| `pushNotificationDescription` | string (template) | only on success path | `'Successful payment of RM ${param1} to Indah Water Consortium. REF:${param2}'` |
| `isRunFav` | boolean | no | `false` |
| `isFuture` | boolean | no | `false` |
| `isInsufficientAmount` | boolean | no | `false` |
| `testCaseType` | string | no | `'Default'` |

## Outputs / Observable Effects

- **On success** (status = approve, amount within range, sufficient balance): the Test Case passes,
  a "Payment successful" status screen with reference ID is verified, a push notification is
  verified, the account balance decreases by `amount`, and a matching history entry is verified.
  The captured reference ID is available to the caller for any further chained verification
  (e.g. share-receipt), matching FR-006–FR-009.
- **On boundary failure** (`amount` outside `[minAmount, maxAmount]`): the Test Case stops after
  showing `errorMsgMinAmount` (or the max-amount equivalent) and does not proceed to authorization
  (FR-011).
- **On insufficient balance** (`isInsufficientAmount` = true / account balance < `amount`): the
  Test Case stops after showing the insufficient-balance message and does not proceed to
  status/notification/balance/history checks (FR-010).
- **On rejected authorization** (`status` = a reject value): the Test Case stops immediately after
  the authorization step and does not proceed to status/notification/balance/history checks
  (FR-013).

## Compatibility

This contract only formalizes existing sibling-script conventions (`Zakat_AI`, `3rd party
transfer_AI`, `DuitNow transfer_AI`) applied to PayBill — it introduces no new external dependency
and no breaking change to any existing test case.
