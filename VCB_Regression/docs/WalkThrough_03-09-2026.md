# Framework Walk-through — 03 Sep 2026

A quick walk-through of the automation framework architecture (Katalon Studio), using
`Test Cases/Website/Sanity/Web Login Smoke Test` as the example of one complete flow.
Everything below is what you actually see in the Katalon Studio Tests Explorer.

## 1. The big picture: 6 layers

```
Profile (sanity-web)                           <- which environment to run against
        |
Object Repository                              <- Component storage
        |
TestListener @BeforeTestCase                   <- opens browser, navigates (shared setup)
        |
Test Case  (Variables tab + Script tab)        <- input data + business steps
        |
Page Object (WebSanityLoginPage)               <- page actions + element accessors
        |
WebUIKeyword                                   <- UI actions
```

Read it top-down — that is the order of the walk-through below.

## 2. Step by step

### Step 1 — Profile: `sanity-web`

Open **Profiles > sanity-web** in the explorer. It is a table of global variables, nothing else.
The ones that matter here:

| Variable | Value |
|---|---|
| `G_url` | `https://acme-test.uipath.com/login` |
| `G_projectName` | `Framework Web Sanity Check` |

You pick the profile from the dropdown in the toolbar before hitting Run. Switching profile
switches environment (SIT / UAT1 / UAT5 / production...) with **no code changes**.

### Step 2 — Object Repository: one folder per page

**Object Repository** in the explorer is where every locator lives, stored per page. Around
79 web test objects today, grouped by site and then by page:

```
Object Repository/Website/
    Sanity/
        Login/              btnLogin, lblErrorMessage, txtEmail, txtPassword
    SauceDemo/
        Login/
        Inventory/
        Cart/
        Checkout/
        Common/             <- shared across SauceDemo pages
    M2U/Browsers/
        Login Page/
        Home Page/
        Accounts Page/
        Transfer Page/
        Settings Page/
        Common/             <- DatePicker, Dropdown
        Dynamic/            <- Button, Input, Label, Text (parameterised objects)
```

**Conventions worth pointing out during the walk-through:**

- **One folder per page**, mirroring the page objects under `Keywords/.../pages/base/`. The
  folder `Website/Sanity/Login` pairs with the class `WebSanityLoginPage`. Keep that pairing —
  it is how anyone finds the right object in seconds.
- **Prefix by control type** so the name tells you what it is: `btn` (button), `txt` (input),
  `lbl` (label/text). E.g. `btnLogin`, `txtEmail`, `lblErrorMessage`.
- **`Common/`** holds objects genuinely shared by several pages of the same site — never
  duplicate one locator into two page folders.
- **`Dynamic/`** holds parameterised objects, where part of the locator is filled in at
  runtime (e.g. "the button whose label is X"). Use these instead of creating one test object
  per row of a list.

Double-click any object and the editor shows its **Selection Method** — XPath, CSS or
Attributes — plus the locator value. `btnLogin`, for example, uses XPath
`//button[@type='submit']`, with a CSS fallback `button.btn.btn-primary` stored alongside it.

**Why this layer exists:** when the UI team renames a class or moves a button, you fix the
locator in one object and every test case using it is fixed — no code change, no recompile.

### Step 3 — TestListener: `Test Listeners/TestListener`

This is the part newcomers usually miss. `@BeforeTestCase` runs before **every** test case
and decides web vs. mobile by matching the test case path against
`G_websiteTestCaseFolderPath = ['Test Cases/Website']` (a global variable in the `default` profile).

Since this test case sits under `Test Cases/Website`, the listener calls:

```groovy
CustomKeywords.'com.vcb.m2u.pages.WebCommon.openBrowserAndBypassSecure'(GlobalVariable.G_url, isRunWebsiteAndMobile)
```

=> By the time your script starts, **the browser is already open and on the right URL**.
Test cases never open the browser themselves. Same idea on the mobile side: the listener
starts the app.

### Step 4 — Test Case: `Web Login Smoke Test`

One item in the explorer, two tabs at the bottom of the editor.

**The Variables tab** declares the input data:

| Variable | Default value |
|---|---|
| `email` | `'test@example.com'` |
| `password` | `'WrongPass123'` |

These become plain local variables inside the script — that is how the same script can be
re-run with different data (from a Test Suite, a data file, or `callTestCase`).

**The Script tab** is the whole test, and it is deliberately tiny:

```groovy
WebSanityLoginPage page = WebBasePage.getPage(WebSanityLoginPage)
try {
    String errorMessage = page.login(email, password)
    WebUI.comment("Login page responded with: ${errorMessage}")
} catch (Exception ex) {
    WebUIKeyword.takeScreenshot()   // capture evidence
    throw ex
}
```

Three lines of real work: get the page, do the business action, log what came back.
The rule: a test case should read like plain English — **no locators, no `sendKeys`,
no `sleep`** at this level. Notice there is also no "open browser" step — the
`TestListener` from the previous step already did that.

### Step 5 — Page Object: `WebSanityLoginPage`

Two clearly separated parts, and this split is the heart of the framework.

```groovy
// (a) element accessors -> point into the Object Repository, no locator here
public static TestObject txtEmail() {
    return findTestObject('Object Repository/Website/Sanity/Login/txtEmail')
}
public static TestObject btnLogin() {
    return findTestObject('Object Repository/Website/Sanity/Login/btnLogin')
}

// (b) business action -> the only thing a test case is allowed to call
String login(String email, String password, FailureHandling flowControl = defaultFailureHandling) {
    setText(this.txtEmail(), email, flowControl)
    setText(this.txtPassword(), password, flowControl)
    WebUIKeyword.click(this.btnLogin(), flowControl)
    return WebUIKeyword.getText(this.lblErrorMessage(), flowControl)
}
```

One page object per screen. If a screen gains a new field, this is the only class you touch.

#### How you get the instance: `WebBasePage.getPage()`

`Keywords/com/vcb/m2u/internal/pages/base/WebBasePage.groovy` uses reflection: it reads
`browserType` from `ExecutionWebSetting`, prepends the `Chrome` / `Safari` prefix, then loads
`com.vcb.m2u.internal.pages.chrome.ChromeWebSanityLoginPage`. Instances are cached, so asking
for the same page twice gives you the same object.

This means **every page needs one abstract base class plus one concrete Chrome class**, even
when the concrete class is completely empty (`ChromeWebSanityLoginPage` genuinely is):

```
Keywords/com/vcb/m2u/internal/pages/
    base/WebSanityLoginPage.groovy          <- all the logic lives here
    chrome/ChromeWebSanityLoginPage.groovy  <- empty, but mandatory
```

- Missing the concrete class => it fails at runtime.
- The payoff: if Safari ever behaves differently, you override just that one method in
  `SafariWebSanityLoginPage` and no test case changes at all.

### Step 6 — `WebUIKeyword`: the wrapper layer (`Keywords/com/vcb/common/WebUIKeyword.groovy`)

This is why we never call Katalon's built-in `WebUI` directly. Take `click`:

```groovy
static def click(TestObject obj, FailureHandling flowControl = defaultFailureHandling) {
    info "Click for test object ${toStringAndLocatorTestObject(obj)}"   // automatic logging
    try {
        WebUI.click(obj)
    } catch (Exception ex) {
        handleError("Unable to click ...", flowControl)                 // centralised error handling
    }
}
```

Every action gets three things for free:

1. **Logging that includes the locator** — much faster report debugging.
2. **Centralised try/catch** via `handleError`.
3. **`flowControl`** (`STOP_ON_FAILURE` / `CONTINUE_ON_FAILURE`) flows all the way from
   test case -> page object -> keyword, so a test case can decide "stop on failure" or
   "log it and carry on".

## 3. Three takeaways

- **Where to change what**: environment/URL -> **Profiles**. Locator -> **Object Repository**
  (the page's own folder). How to interact with one page -> its **page object**. Business
  scenario -> the **test case script**. Never mix them.
- **Setup does not live in the test case** — `TestListener` launches the browser/app based on
  the test case path.
- **A new page needs the full pair**: abstract base + `Chrome<Name>Page`, otherwise
  `getPage()` throws at runtime.

## 4. A note on the example test case

`Web Login Smoke Test` is a **framework sanity check**, not a VCB business test. It logs in
with a deliberately wrong password purely to prove the whole chain (profile -> listener ->
page object -> keyword -> Object Repository) still runs end to end. That makes it an ideal
teaching example: small, yet it passes through every single layer.

## 5. Quick reference

| Layer | Where in Katalon Studio |
|---|---|
| Profile | `Profiles/sanity-web` |
| Object Repository | `Object Repository/Website/Sanity/Login` |
| Listener | `Test Listeners/TestListener` |
| Test Case (Variables + Script) | `Test Cases/Website/Sanity/Web Login Smoke Test` |
| Page object | `Keywords/com/vcb/m2u/internal/pages/base/WebSanityLoginPage.groovy` |
| Page concrete | `Keywords/com/vcb/m2u/internal/pages/chrome/ChromeWebSanityLoginPage.groovy` |
| Page base (reflection) | `Keywords/com/vcb/m2u/internal/pages/base/WebBasePage.groovy` |
| Keyword wrapper | `Keywords/com/vcb/common/WebUIKeyword.groovy` |
