# Authoring test cases & the common-layer call chain

Practical companion to `architecture.md` (which describes the static layout). This file
covers **how a test case is written** and **how a script call reaches the common code**.

## A test case is a `.tc` + `.groovy` pair

Katalon stores every case as two files under mirrored folders:

```
Test Cases/Mobile/VCB/Common/CheckInputOTPPasswordSettings.tc            ← definition (XML)
Scripts/Mobile/VCB/Common/CheckInputOTPPasswordSettings/Script<ts>.groovy ← implementation
```

`Test Cases/` and `Scripts/` mirror 1:1 by folder name. The script folder is named after the
case; the file inside is `Script<epoch-millis>.groovy` (Katalon-generated name).

### The `.tc` file — metadata + input variables

Not code — XML. The meaningful part is `<variable>`: the case's input parameters (like a
method signature). `defaultValue` is the fallback, usually pointing at a Profile global:

```xml
<variable>
   <defaultValue>GlobalVariable.G_password</defaultValue>   <!-- from the active Profile -->
   <name>password</name>
</variable>
<variable>
   <defaultValue>com.vcb.common.FlowControlEnum.STOP</defaultValue>
   <name>flowControl</name>
</variable>
```

Each declared variable is available in the script as a bound global of that name.

### The `.groovy` script — always the same 3 moves

```groovy
// 1. Imports: Katalon's standard block + the BASE page classes used (never AndroidPhone*)
import com.vcb.mobile.internal.pages.base.BasePage
import com.vcb.mobile.internal.pages.base.HomePage
import com.vcb.mobile.internal.pages.base.settingpage.SettingPage
import internal.GlobalVariable as GlobalVariable

// 2. Resolve pages through the FACTORY (never `new`)
HomePage homePage = BasePage.getPage(HomePage)
SettingPage settingPage = BasePage.getPage(SettingPage)

// 3. Call business methods. A bare String is a step comment (Katalon logs it).
'Click on Setting button'
homePage.tapBtnSetting()

'Enter PIN and Password'
settingPage.checkEnterPassword(password)   // `password` is the .tc variable
```

**Three invariants of every script:**
1. Resolve pages via `BasePage.getPage(XxxPage)` — never instantiate `AndroidPhoneXxxPage`.
2. Import the **abstract base** class (`HomePage`), not the `AndroidPhone*` implementation.
3. `.tc` variables are used directly; environment values come from `GlobalVariable.G_*`.

## The call chain: script → common code

`BasePage.getPage(HomePage)` returns an `AndroidPhoneHomePage`. Calling a method on it
resolves up this chain:

```
BaseKeyword                 — root: handleError, info, startApplication, screenshot, defaultFailureHandling
  └─ MobileDriver           — COMMON KEYWORDS: tap/setText/scroll/waitFor... (wrap Appium Mobile.*)
       └─ BasePage          — factory getPage() + verifyElements/getValueUI/format checks
            ├─ HomePage (abstract)          — domain base: declares locators + business methods, no impl
            │    └─ AndroidPhoneHomePage    — device impl: returns concrete TestObject locators
            ├─ SettingPage / TransferPage / ...   (same base→androidphone shape)
            └─ CommonPage (abstract)        — PEER page for shared dynamic locators (lblDynamic, btnTick...)
                 └─ AndroidPhoneCommonPage
```

- Domain pages (`HomePage`, `SettingPage`, `TransferPage`, …) `extends BasePage` **directly**.
- `CommonPage` also `extends BasePage` — it is a **sibling, not an ancestor**. When a script
  needs shared dynamic locators it resolves it separately: `CommonPage c = BasePage.getPage(CommonPage)`.

### The factory (`BasePage.getPage`)

Takes the abstract base type, rewrites the package `...pages.base.<sub>` → `...pages.androidphone.<sub>`
and the class `Xxx` → `AndroidPhoneXxx`, resolves via `Class.forName().newInstance()`, caches by
class name. Device type comes from `ExecutionSetting.deviceType`. **Consequence:** every base page
needs an `AndroidPhone*` class at the exact mirrored path (even empty) or `getPage` throws
`ClassNotFoundException`. Only `AndroidPhone*` implementations exist today.

## Two different meanings of "common"

- **`CommonPage`** (`vcb/internal/pages/base/CommonPage.groovy`) — reusable **dynamic locators**
  parameterised by text/index. Base declares `abstract TestObject lblDynamic(String text)`;
  `AndroidPhoneCommonPage` implements it as `findTestObject(path, ['text': text])`, injecting the
  value into a placeholder in the Object Repository XPath.
- **`MobileDriver`** (`com.vcb.common`) — the reusable **action keywords** every page inherits.
  Uniform wrapper pattern — log → try → `Mobile.*` → catch → `handleError`:

  ```groovy
  static void tap(TestObject obj, int timeout, FailureHandling flowControl = defaultFailureHandling) {
      info "click for test object ${toStringAndLocatorTestObject(obj)}"
      try   { Mobile.tap(obj, timeout, flowControl) }
      catch (ex) { handleError("System Exception: ...", flowControl) }
  }
  ```

  Most-used: tap/`doubleTap`/`tapAndLog`/`tapAtPosition`/`tapElemtByXml`; `setText`/`setTextAndDone`/
  `getText`/`enterPinNo`; `waitForElementPresent`/`waitForElementNotPresent`/`verifyElementVisible`/
  `verifyElementExist`; `scrollTo`/`scrollToText`/`scrollToEnd`; `openApp`/`activateApp`/`startApplication`.

`BaseKeyword` sits under `MobileDriver` and provides `handleError` (central error handling:
screenshot + `markFailed`/`markWarning`/`markFailedAndStop` per `FailureHandling`), `info`,
`startApplication()` / `runFromTestCloud()`, and the `defaultFailureHandling` every method defaults to.

## AI-generated common test case skeleton

Every AI-generated common test case in `AI Common` follows the **same fixed skeleton** —
only the middle (module-specific) steps change. When asked to generate a new one, default
to this shape instead of inventing a new structure each time:

```
1. Login S2U            — always WebUI.callTestCase('Mobile/VCB/Common/Login Secure2u', ...)
2. [middle steps]        — MODULE-SPECIFIC: navigate to the module, enter the transaction's data
3. Authorize             — S2U/TAC/OTP authorization
4. Status / Confirm      — verify status screen, extract RefID
5. Check Push Notification
6. Verify Balance
7. Check History
```

Steps 1, 3–7 are structurally identical across modules — only the **exact keyword name**
differs per domain (found by reading a sibling script in that module before writing a new
one). Steps 1 and 3 use the literal same call in every module; 4–7 vary in method name but
not in intent. Examples from cases already built this way:

| Step | 3rd Party Transfer (`transferpage.TransferPage`) | DuitNow (`transferpage.EnterDuitnowProxyPage` + `StatusScreenPage`) | FTT Overseas (`transferpage.EnterOverseasPage`) |
|---|---|---|---|
| 1. Login | `callTestCase('Login Secure2u', ...)` | same | same |
| 3. Authorize | `secure2uAuthorisationPage.authorizeS2UV5BioPin(status)` | `secure2uAuthorisationPage.authorize(status)` | `secure2uAuthorisationPage.authorize(status)` |
| 4. Status/Confirm | `transferPage.statusScreen(status, true, false, true)` → RefID | `statusScreenPage.verifyDuitnowStatusUI(status, futuretype)` → RefID | `statusScreenPage.verifyStatusScreen(statusTitle, statusDescription)` → RefID |
| 5. Push Notification | `transferPage.verifyTransferNotificationMessage(accountNo, RefID, amount)` | `pushNotificationPage.verifyTransferNotificationDuitNow(product, productData, RefID, amount)` | `pushNotificationPage.verifyPushNotification(notificationTitle, notificationDescription)` |
| 6. Balance | `homePage.getBalance(true)` before/after, diff against amount | via history verify (step 7) | via history verify (step 7) |
| 7. History | `callTestCase('M2U History', ...)` | `accountDetailPage.verifyM2UHistoryFirstItemToday(history, amount)` | (not always present — check the module) |

`secure2uAuthorisationPage.authorize(status)` is the generic form — it auto-detects
whether the screen shown is Secure2u biometric, TAC, or OTP and handles all three, so
prefer it over the more specific `authorizeS2UV5BioPin`/`authorizeWithTacOrOTP` unless a
reference script for that exact module uses the specific one.

**Practical process when asked for a new one:** find the closest existing sibling script
for that module/product (e.g. search `Test Cases/Mobile/VCB/<Module>` or `G1A/<Module>` for
a matching product name) to source the exact tail keyword names and page objects, keep its
step 1/3 calls as-is, and only replace the middle steps with what the user describes.

## Adding something new

- **New per-screen locator/action** → add an abstract method to the domain base page, implement it in
  the `AndroidPhone*` counterpart, store the locator under `Object Repository/`.
- **New shared dynamic locator** → add it to `CommonPage` (+ `AndroidPhoneCommonPage`).
- **New Appium action keyword** → add it to `MobileDriver` following the log → try → `handleError` shape.
- Never `new` a page directly; never hand-edit `Libs/CustomKeywords.groovy` or
  `Libs/internal/GlobalVariable.groovy` (Katalon regenerates both).
