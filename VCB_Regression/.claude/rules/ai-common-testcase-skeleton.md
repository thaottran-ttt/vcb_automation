# AI-generated common test case skeleton

Every common test case generated for `AI Common` (`Test Cases/Mobile/VCB/AI Common/` +
`Scripts/Mobile/VCB/AI Common/`) MUST follow this fixed 7-step shape. Only step 2 changes
per module — steps 1 and 3 are literal, steps 4–7 always appear but the exact keyword name
is domain-specific.

1. **Login S2U** — always `WebUI.callTestCase(findTestCase('Mobile/VCB/Common/Login Secure2u'), [...])`.
2. **Middle steps** — module-specific: navigate to the module, enter the transaction's data.
   Source from the user's description/screenshots, or from the closest sibling script.
3. **Authorize** — S2U/TAC/OTP authorization. Prefer the generic `authorize(status)` (auto-detects
   which of Secure2u biometric / TAC / OTP is shown) over a domain-specific variant such as
   `authorizeS2UV5BioPin`, unless a reference script for that exact module uses the specific one.
4. **Status / Confirm** — verify the status screen and extract the reference ID (`RefID`).
5. **Check Push Notification** — verify the transfer notification.
6. **Verify Balance** — before/after balance check against the transaction amount.
7. **Check History** — verify the transaction appears in M2U/account history.

## Before writing a new one

Find the closest existing sibling script for that module/product (search
`Test Cases/Mobile/VCB/<Module>` or the `G1A/<Module>` equivalent for a matching product
name) to source the exact tail keyword names and page objects for steps 3–7. Keep steps 1
and 3 as the literal calls above; only replace step 2 with what the user describes.

Full worked comparison across 3 modules (3rd Party Transfer, DuitNow, FTT Overseas) with
concrete keyword names lives in `.claude/docs/authoring-test-cases.md` under
"AI-generated common test case skeleton" — read it for the detail, this file is the
enforceable checklist.
