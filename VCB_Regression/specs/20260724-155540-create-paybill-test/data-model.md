# Data Model: PayBill Common Test Case (AI Common)

This feature has no database/persistence layer — "data model" here means the Katalon Test Case
**variables** (parameters) the common script accepts and the runtime values it derives, mapped to
the spec's Key Entities. Field names and sample defaults are taken from
`Data For AI/init data.txt` and the sibling `AI Common` `.tc` files.

## Biller

Represents the registered bill-payment recipient the transaction is made against.

| Field | Type | Notes |
|---|---|---|
| `payBillName` | string | Biller display name, e.g. `'Indah Water Consortium'`. Used both for search and for push-notification message matching. |
| `billerID` | string | Biller account/reference number, e.g. `'29542784'`. |
| `membershipType` | string | Optional secondary biller field (e.g. `'ABC123'`), passed alongside `billerID` to `setBiller([billerID, membershipType])`. |
| `favName` | string | Name under which the biller may be saved/selected as a favourite (`'Paybill'`). |

## Payment Transaction

Represents one invocation of the common flow.

| Field | Type | Notes |
|---|---|---|
| `accountName` | string | Paying account, e.g. `'Current Account-i'`. |
| `amount` | string/number | Payment amount, e.g. `'1000'`. |
| `minAmount` / `maxAmount` | string | Biller's allowed range, e.g. `'1'` / `'9999999'`, used only when validating boundaries (FR-011). |
| `status` | string | Authorization outcome parameter: `'Approve'` or a reject value (FR-005/FR-013). |
| `isFuture` | boolean | Whether the payment is scheduled for a future date rather than immediate (Edge Case). |
| `isInsufficientAmount` | boolean | Whether this invocation should exercise the insufficient-balance branch (FR-010). |
| `isRunFav` | boolean | Whether the biller is selected via the favourites path instead of fresh search (Edge Case). |
| `testCaseType` | string | Selects the boundary/variant branch, e.g. `'Default'` vs an edit-min/max variant, consistent with `PayBillPage.PayBillTestCaseType`. |
| `refID` | string (derived) | Captured from the status screen only on a successful payment; not an input parameter. |

## Push Notification

The confirmation message verified after a successful payment.

| Field | Type | Notes |
|---|---|---|
| `pushNotificationTitle` | string | `'Vcb2u: Payments'`. |
| `pushNotificationDescription` | string (template) | `'Successful payment of RM ${amount} to Indah Water Consortium. REF:${refID}'` — `${amount}` and `${refID}` are substituted at runtime. |

## Account Balance

Before/after balance snapshot used to confirm the correct amount was debited.

| Field | Type | Notes |
|---|---|---|
| `balanceBefore` | string (derived) | Captured via `homePage.getBalance(true)` before submitting the payment. |
| `balanceAfter` | string (derived) | Captured the same way after the payment completes; compared against `balanceBefore`. |

## Transaction History Record

The entry representing a completed payment as it appears in account/M2U history.

| Field | Type | Notes |
|---|---|---|
| `historyLabel` | string (derived) | Expected as `'-RM ' + amount` per the existing `verifyM2UHistoryFirstItemToday(history, ...)` pattern. |

## Confirmation Screen Fields

| Field | Type | Notes |
|---|---|---|
| `confirmationData` | list of string | Which confirmation-screen fields to verify, e.g. `['Pay from', 'Date']`. |
| `textNextPageConfirmation` | string | The button/label used to proceed past confirmation, e.g. `'Pay Now'`. |

## State / Outcome Transitions

```
Start
  → (entry point + biller search/select + amount entry + confirmation review)
  ├─ amount outside [minAmount, maxAmount] ──► boundary-error message ──► STOP (FR-011)
  └─ amount valid
       → authorize(status)
       ├─ status = reject ──► STOP, no status/notification/balance/history checks (FR-013)
       └─ status = approve
            ├─ insufficient balance ──► insufficient-balance message ──► STOP (FR-010)
            └─ sufficient balance
                 → status screen success + capture refID (FR-006)
                 → verify push notification (FR-007)
                 → verify balance delta (FR-008)
                 → verify history entry (FR-009)
                 → End (success)
```

No entity is persisted by this test case beyond the lifetime of a single script execution — all
"data" is Katalon Test Case parameters in and assertions out.
