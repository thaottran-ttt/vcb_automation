# Plan — `ExcelReportFixture`: suite 10 test case giả lập PASSED / FAILED / BLOCKED / RETRY

Mục đích: **fixture kiểm chứng tính năng Excel report**, không phải test sản phẩm.
Liên quan: `TestListener_Excel_Report_Plan.md` (spec), `KNOWN_ISSUES.md` (lỗi đang mở).

---

## 1. Cách tiếp cận

**10 test case dưới `Test Cases/Website/ReportFixture*`, 9 case chạy không cần browser, 1 case mở Chrome.**

9 case khai báo biến `shouldNotStartApp = true` → `TestListener.sampleBeforeTestCase` return ở
dòng 65 trước khi đụng tới driver → chạy trong vài mili-giây, không thể flaky.
Đúng 1 case (RF04) **không** khai báo biến đó nên listener mở Chrome — đây là cách duy nhất để có
screenshot thật, `HYPERLINK` cột I hoạt động, và một dòng Top Failures có link ảnh.

**Tại sao không dùng browser cho cả 10:** đo từ chính repo này
(`Reports/20260903_202410/.../20260903_202411.csv`), riêng `sampleBeforeTestCase` đã tốn 11–14 s
mỗi case (chính là `openBrowserAndBypassSecure`). 10 case + ~9 lần retry = 8–12 phút mỗi vòng kiểm
chứng, kèm đủ kiểu hỏng của SauceDemo (mạng, chromedriver, site chết) chen giữa bạn và cái report
cần xem. Fixture này chạy ~90 giây, trong đó ~70 s là 4 vòng Chrome của RF04.

**Đánh đổi phải nói rõ:** 9 case "trơ" đi qua **nhánh listener khác** với case web thật — chúng
return ở `TestListener.groovy:65` và `:174`, nên không bao giờ chạm
`openBrowserAndBypassSecure`, `WebUIKeyword.takeScreenshot()`, `closeBrowser()`.
Chấp nhận được vì hook của reporter (`:54`, `:159`) đều chạy **trước** các return đó, nên
`ExcelReporter.startTestCase`/`endTestCase` nhận đúng context như case thật. RF04 phủ nốt nhánh
có driver.

---

## 2. Các quyết định thiết kế

### Q1 — Browser hay không? Lai: 9 trơ + 1 có browser

Listener **không** ép mở browser cho mọi case. Truy trong `Test Listeners/TestListener.groovy`:

- `:54` `ExcelReporter.startTestCase(...)` — luôn chạy, bấm giờ.
- `:59` `boolean shouldNotStartApp = map.get('shouldNotStartApp')` — case không khai báo → `null` → `false`.
- `:65` `if(shouldNotStartApp) { return }` — **trước** cả skip gate (`:69`), promotion `G_runWebsite` (`:74`) và `openBrowserAndBypassSecure` (`:100`).
- `@AfterTestCase`: `:159` reporter chạy trước, rồi `:174` `if(shouldNotStartApp) return` → dòng vẫn được ghi, nhưng bỏ qua screenshot/closeBrowser.

**Điểm mấu chốt buộc phải lai:** với case FAILED trơ,
`ExcelReporter.captureScreenshotIfFailed` (`ExcelReporter.groovy:461-496`) không tìm thấy driver ở
cả 2 nhánh → cột I trống. Nên **yêu cầu số 4 (screenshot + HYPERLINK + link trong Top Failures)
không thể đạt nếu không có ít nhất 1 case FAILED có driver.** RF04 chính là case đó.

Case đặt dưới `Test Cases/Website/…` mà **không** có `shouldNotStartApp` sẽ được `:98-101` tự mở
Chrome vào `G_url` (= `https://www.saucedemo.com/` trong `Profiles/default.glbl`), nên script RF04
không cần tương tác trang nào — chỉ gọi `markFailedAndStop(...)` lúc driver còn sống.
Tính chất hay: nếu Chrome không mở được, RF04 vẫn FAILED, chỉ mất screenshot — fixture suy giảm
chứ không vỡ.

### Q2 — FAILED tất định: `KeywordUtil.markFailedAndStop(msg)`

- `markFailedAndStop` ném `StepFailedException` kèm đúng text → status `FAILED` → reporter map `FAILED → FAILED` (`ExcelReporter.groovy:245-247`), `getMessage()` cho ra message 1 dòng sạch cho cột H. Đây đã là idiom sẵn có (`BaseKeyword.groovy:60`).
- **Loại** `markFailed(msg)`: nó đánh dấu step nhưng **không dừng** (`BaseKeyword.groovy:55-56` gọi rồi `break`; `:51-52` gọi rồi ném thủ công). Dựa vào tổng hợp status là thêm ẩn số vào một fixture có nhiệm vụ khử ẩn số.
- **Loại** `assert` trần: power-assert của Groovy cho message nhiều dòng, bị reporter gộp bằng `" | "` (`ExcelReporter.groovy:542-546`) → cột H không đọc nổi; `AssertionError` là `Error` chứ không phải `Exception` nên không rõ Katalon ghi `FAILED` hay `ERROR`.
- **1 case (RF07) cố ý ném `IllegalStateException` không bắt** để chạm nhánh `ERROR → FAILED` mà `markFailedAndStop` không bao giờ tới.

Mỗi message phải nhúng token tìm được (`RF04-SIMULATED-FAILURE`, `RF07-SIMULATED-ERROR`) để
assert cột H và Top Failures bằng so khớp chuỗi thay vì nhìn bằng mắt.

### Q3 — BLOCKED tất định: bật `G_isStopTestSuite` ở case áp chót

`TestListener.groovy:69-71` gọi `testCaseContext.skipThisTestCase()` mỗi khi
`GlobalVariable.G_isStopTestSuite` được bật. Cờ này không bao giờ được listener reset → dính luôn,
nên các case BLOCKED **phải nằm cuối suite**. RF08 (case PASSED) set
`GlobalVariable.G_isStopTestSuite = true` ở câu lệnh cuối; RF09, RF10 bị skip ở `@BeforeTestCase`,
nhưng `@AfterTestCase` vẫn ghi dòng vì reporter ở `:159`, **trước** guard `:161`.

Hai điều bắt buộc đúng:

- **RF09 và RF10 KHÔNG được khai báo `shouldNotStartApp`.** Dòng `:65` return *trước* `:69`, nên case có biến đó không bao giờ bị skip — nó sẽ chạy và báo PASSED. Chúng vẫn không mở browser vì `skipThisTestCase()` return ở `:71`, trước nhánh browser `:74-101`.
- **Không dùng** trigger production `failStopTestSuite` (`:178-182`): `@AfterTestCase` return ở `:174` vì `shouldNotStartApp` trước khi tới đó, và dùng nó buộc RF08 phải fail → Katalon retry → bản retry lại bị skip → rối `8`(RETRY)/`8.1`(BLOCKED).

### Q4 — RETRY rồi PASS: biến static trong class `Keywords/`, khóa theo report folder

File mới `Keywords/com/vcb/fixture/ReportFixtureSupport.groovy` (package `com.vcb.fixture`), giữ
`private static String currentRunId` và `private static Map<String,Integer> attempts`.
`attempt(String key)` đọc `RunConfiguration.getReportFolder()` trong try/catch; nếu khác
`currentRunId` thì `attempts.clear()` và lưu id mới; rồi tăng và trả về số lần của key đó.

**Tại sao sống sót qua việc recompile mỗi attempt** (căn cứ trong repo này):
- `Scripts/**/*.groovy` bị recompile mỗi lần chạy thành `Libs/TempTestCase<millis>.groovy` (hiện có `Libs/TempTestCase1788756806602.groovy` nằm untracked trong working tree). → static trong *script* KHÔNG an toàn.
- `Keywords/**` compile 1 lần vào `bin/keyword/**` và load 1 lần mỗi execution — `bin/keyword/com/vcb/report/*.class` đều đóng dấu `11:53`, đúng lúc bắt đầu lần chạy đó. Static của chính `ExcelReporter` tích lũy qua các test case: report của lần chạy đó đánh số dòng 1–5 qua 5 lần thực thi test case riêng biệt.
- Reset theo run-id là **bắt buộc**. `KNOWN_ISSUES.md` §8 đã điều tra đúng chuyện static sống sót giữa 2 lần chạy trong 1 JVM, và `ExcelReporter.resetSuiteState()` (`:138-151`) tồn tại vì lý do đó. Không reset thì lần chạy fixture thứ hai trong cùng session sẽ thấy counter cũ và RF03 pass ngay attempt 1.

**Loại** `RunConfiguration.getSuiteRerunIndex()`: chỉ tăng khi rerun *cấp suite*
(`rerunImmediately=false`); với `rerunImmediately=true` nó đứng yên ở 0 — đó chính là lý do retry
detection của reporter phải fallback sang row adjacency. **Loại** file đếm trên đĩa (vẫn phải khóa
theo run id, thêm IO + dọn dẹp). **Loại** GlobalVariable mới (phải sửa 15 file `Profiles/*.glbl` và
làm bẩn `Libs/internal/GlobalVariable.groovy` — `CLAUDE.md` cấm).

Biến **fail toàn bộ attempt** (RF04, RF07) không cần counter — fail vô điều kiện, Katalon tự dừng
sau `numberOfRerun`.

### Q6 — Bucket module: 2 thư mục, 1 được map và 1 cố ý không map

`ExcelReporter.resolveModule` (`:259-283`) bỏ `Test Cases/`, rồi `Website|Mobile`, rồi
`VCB|M2U|G1A`, rồi lấy segment kế. Nên `Test Cases/Website/ReportFixture/RF01 …` → module
`ReportFixture`. Chỉ vừa 4 dòng module (chart neo vào `$A$13:$A$16`).

- 8 case trong `Test Cases/Website/ReportFixture/` → module `ReportFixture`.
- 2 case trong `Test Cases/Website/ReportFixtureUnmapped/` → không có trong `report.modules` → gộp vào nhãn thứ 4 `Other` (`:276-281`), kèm 1 dòng `[WARN]`. Việc này chạm đúng nhánh collapse — chính là chỗ `KNOWN_ISSUES.md` §2 xác định là điểm crash nhiều nhất trước khi có `ExcelReportLog`.

---

## 3. Các bước triển khai

### Bước 1 — Tạo helper fixture
`Keywords/com/vcb/fixture/ReportFixtureSupport.groovy` — class static-only, **không** annotate
`@Keyword` (để `Libs/CustomKeywords.groovy` không bị regenerate).

- `static int attempt(String key)`
- `static void failUntilAttempt(String key, int passOnAttempt, String message)`

**Ràng buộc cú pháp:** Katalon build bằng parser antlr2 (`KNOWN_ISSUES.md` #1). Không `do/while`,
không `var`, không `::`, không lambda, không try-with-resources, không `!in`/`!instanceof`, không
multi-assignment. Chỉ `while`/`for` thuần. Áp dụng cho **mọi** file `.groovy` trong plan này.

### Bước 2 — 8 test case `ReportFixture`

`<description>` là bắt buộc và có ý nghĩa — nó được parse vào cột D
(`ExcelReporter.groovy:298-305`).

| File (`Test Cases/Website/ReportFixture/`) | Biến | Mục đích |
|---|---|---|
| `RF01 Pass No Browser.tc` | `shouldNotStartApp = true` | PASSED nền |
| `RF02 Pass No Browser Two.tc` | `shouldNotStartApp = true` | PASSED nền |
| `RF03 Retry Twice Then Pass.tc` | `shouldNotStartApp=true`, `passOnAttempt=3`, `failureMessage='RF03-SIMULATED-FAILURE'` | `3` → `3.1` → `3.2` |
| `RF04 Fail All Attempts With Browser.tc` | `failureMessage='RF04-SIMULATED-FAILURE'` — **không có `shouldNotStartApp`** | FAILED + screenshot |
| `RF05 Pass No Browser Three.tc` | `shouldNotStartApp = true` | PASSED nền |
| `RF06 Retry Once Then Pass.tc` | `shouldNotStartApp=true`, `passOnAttempt=2`, `failureMessage='RF06-SIMULATED-FAILURE'` | `6` → `6.1` |
| `RF07 Error All Attempts No Browser.tc` | `shouldNotStartApp = true` | ERROR→FAILED, cột I trống |
| `RF08 Pass And Arm Dependency Stop.tc` | `shouldNotStartApp = true` | PASSED, bật skip gate |

Boolean ghi không nháy (`<defaultValue>true</defaultValue>`); chuỗi giữ dạng có nháy
(`<defaultValue>'RF03-SIMULATED-FAILURE'</defaultValue>`), khớp `TC01 Login Success.tc:34`.

### Bước 3 — 2 test case `ReportFixtureUnmapped`
`RF09 Blocked By Dependency.tc`, `RF10 Blocked By Dependency Two.tc` — **không có biến nào**.
Khai báo `shouldNotStartApp` ở đây sẽ vô hiệu hóa skip (xem Q3).

### Bước 4 — 10 script (mirror 1:1 theo `CLAUDE.md`)
`Scripts/Website/<thư mục tương ứng>/<tên case>/Script1788800000NNN.groovy`

| Case | Nội dung script |
|---|---|
| RF01, RF02, RF05, RF08 | `KeywordUtil.logInfo(...)`. RF08 kết thúc bằng `GlobalVariable.G_isStopTestSuite = true` + comment giải thích nó mô phỏng dependency gate ở `TestListener.groovy:69-71`, và RF09/RF10 phải là 2 link cuối. |
| RF03, RF06 | `ReportFixtureSupport.failUntilAttempt('<RFnn>', passOnAttempt, failureMessage)` |
| RF04 | `KeywordUtil.markFailedAndStop(failureMessage + ' - driver is alive, screenshot expected')`, không tương tác trang. |
| RF07 | `throw new IllegalStateException('RF07-SIMULATED-ERROR - exercises the ERROR to FAILED mapping')` |
| RF09, RF10 | `KeywordUtil.markFailedAndStop('RF09-SKIP-DID-NOT-FIRE - this case must never execute')` — nếu skip gate hỏng thì báo FAILED ầm ĩ thay vì PASSED âm thầm. |

**Không** bọc try/catch — script phải để lỗi đi tới Katalon.

### Bước 5 — Suite
`Test Suites/ReportFixture/ExcelReportFixture.ts` — copy y nguyên thứ tự element và cấu hình của
`SauceDemoTests.ts`: `numberOfRerun=3`, `rerunImmediately=true`,
`rerunFailedTestCasesOnly=false`, `maxConcurrentInstances=1`, `orchestration=CLASSIC`.
GUID mới cho `<testSuiteGuid>`, mỗi `<testCaseLink>`, mỗi `<variable>`/`<variableId>`
(dùng `powershell -c "[guid]::NewGuid()"`, **không bao giờ copy GUID cũ**).

**Thứ tự có ý nghĩa:** RF01…RF10 đúng thứ tự. RF08 bật gate; RF09, RF10 phải là 2 link cuối cùng.

### Bước 6 — Runner sinh tự động (KHÔNG viết tay)
`Test Suites/ReportFixture/ExcelReportFixture.groovy` — mở suite trong Katalon Studio
(`File > Refresh` trước) để Katalon tự sinh. `CLAUDE.md`: không bao giờ sửa tay runner sinh tự động.

### Bước 7 — Cập nhật `custom-report/report.properties`

```properties
report.modules=ReportFixture,SauceDemo,Uncategorized,Other
report.environments=Windows11/Chrome,Windows11/Firefox,macOS/Safari,Linux/Chrome
report.environment.label=Windows11/Chrome
```

- `ReportFixture` ở slot 1; `Other` **phải** ở slot 4 (`otherBucket` đọc `configured[3]`, `ExcelReporter.groovy:285-288`). Bỏ `Sanity` vì không có suite nào dùng.
- Điền đủ 4 environment — xem lỗi mới ở Q8 bên dưới.
- **`report.environment.label` phải giống hệt từng byte với phần tử đầu của `report.environments`.** Cột E lấy từ nhãn pin (`:375-378`); `A20` lấy từ `getEnvironments()[0]`. Lệch nhau → Environment Coverage âm thầm đọc 0. **Đây là cái bẫy config duy nhất của cả thiết kế.**

### Bước 8 — Chạy và kiểm chứng

```bash
katalonc -noSplash -runMode=console -projectPath="…/RBTS.prj" -retry=0 \
  -testSuitePath="Test Suites/ReportFixture/ExcelReportFixture" \
  -executionProfile="default"
```

`-retry=0` quan trọng: `numberOfRerun=3` của chính suite mới là thứ tạo ra dòng RETRY; retry ở
tầng CLI sẽ chạy lại cả suite chồng lên.

**Dùng profile `default`.** `sanity-web` chỉ định nghĩa `G_url` và `G_projectName`, để
`G_websiteTestCaseFolderPath` null → `ListUtils.isStringInList(null, testCaseId)`
(`ListUtils.groovy:37`) NPE ở `TestListener.groovy:74`. `vcb-regression` là target mobile/UAT.

---

## 4. Report kỳ vọng — đây chính là bộ assertion

### `Test Details` — 19 dòng (Excel row 2–20)

| Row | A (Text) | B | C | F | I |
|---|---|---|---|---|---|
| 2 | `1` | RF01 | ReportFixture | PASSED | |
| 3 | `2` | RF02 | ReportFixture | PASSED | |
| 4 | `3` | RF03 | ReportFixture | **RETRY** | |
| 5 | `3.1` | RF03 | ReportFixture | **RETRY** | |
| 6 | `3.2` | RF03 | ReportFixture | **PASSED** | |
| 7 | `4` | RF04 | ReportFixture | **RETRY** | `screenshots/0007_…_a1.png` |
| 8 | `4.1` | RF04 | ReportFixture | **RETRY** | `…0008_…_a2.png` |
| 9 | `4.2` | RF04 | ReportFixture | **RETRY** | `…0009_…_a3.png` |
| 10 | `4.3` | RF04 | ReportFixture | **FAILED** | `…0010_…_a4.png` → FailRank **1** |
| 11 | `5` | RF05 | ReportFixture | PASSED | |
| 12 | `6` | RF06 | ReportFixture | **RETRY** | |
| 13 | `6.1` | RF06 | ReportFixture | **PASSED** | |
| 14–16 | `7`, `7.1`, `7.2` | RF07 | ReportFixture | **RETRY** | trống |
| 17 | `7.3` | RF07 | ReportFixture | **FAILED** (từ `ERROR`) | trống → FailRank **2** |
| 18 | `8` | RF08 | ReportFixture | PASSED | |
| 19 | `9` | RF09 | **Other** | **BLOCKED** | |
| 20 | `10` | RF10 | **Other** | **BLOCKED** | |

Phần nguyên của cột A trùng đúng số RF cho cả 10 case — `nextSequenceNo` chỉ tăng khi append
không phải retry (`ExcelReporter.groovy:220-224`). Đây là cách kiểm tra đúng/sai bằng một cái liếc.

### KPI cards (`Executive Summary` dòng 9)

| Ô | Chỉ số | Kỳ vọng |
|---|---|---|
| A9 | TOTAL TESTS | **10** (19 dòng trừ 9 RETRY) |
| B9 | PASSED | **6** |
| C9 | FAILED | **2** |
| D9 | BLOCKED | **2** |
| E9 | RETRIES | **9** |
| F9 | PASS RATE | **0.60 → 60%, hiển thị ĐỎ** (lần đầu chạm nhánh `< 90%`) |

Bất biến phải assert: `B9 + C9 + D9 == A9`.

### Module Breakdown (`A13:F16`)

| Module | Total | Pass | Fail | Blocked | Rate |
|---|---|---|---|---|---|
| ReportFixture | 8 | 6 | 2 | 0 | 75% |
| SauceDemo | 0 | 0 | 0 | 0 | 0% |
| Uncategorized | 0 | 0 | 0 | 0 | 0% |
| Other | 2 | 0 | 0 | 2 | 0% |

Tổng cột = 10 = TOTAL. Environment Coverage: `Windows11/Chrome` Total 10 / Pass 6 / Fail 2 /
Rate 60%; dòng 21–23 toàn 0. Việc `10 ≠ 6+2` ở đó là **đúng** — bảng Environment Coverage
không có cột Blocked.

### Top Failures (dòng 27–36) và screenshots

Dòng 27 = RF04 / message chứa `RF04-SIMULATED-FAILURE` / `View Image`.
Dòng 28 = RF07 / message chứa `RF07-SIMULATED-ERROR` / trống. Dòng 29–36 trống.

Lưu ý: **`View Image` ở Top Failures cột D là text thường, không click được** — `D27` là
`INDEX('Test Details'!$I$2:$I$200, …)`, mà INDEX của một ô `HYPERLINK()` trả về chuỗi hiển thị.
Chỉ `Test Details!I10` mới click được. Đây là đặc tính của template, **không phải bug của reporter
— đừng ghi nhận thành lỗi.**

`screenshots/` phải có đúng 4 file PNG, đều của RF04. Dòng 7–9 vẫn giữ hyperlink dù cột F đã bị
đổi thành `RETRY` — `setStatus` (`ExcelReportWriter.groovy:267-271`) chỉ đụng cột F. Giữ
screenshot của các lần retry là **tính năng**, không phải lỗi.

### Kiểm tra cấu trúc (không nhìn bằng mắt được)

- **Cột A phải là Text, không phải Number.** Giải nén report, xác nhận `xl/worksheets/sheet2.xml` ô `A5` có `t="s"` (shared string) kèm style dòng 2, không phải `<v>` số trần. Với `numberOfRerun=3` thì kịch bản `3.10` co lại thành `3.1` không thể xảy ra (tối đa 4 attempt), nên **phải assert bằng cấu trúc**.
- **Console log** phải có đúng 1 dòng `ExcelReport [WARN]: … module 'ReportFixtureUnmapped' … collapsing into 'Other'`, ít nhất 1 dòng `… screenshot capture failed for 'RF07 …'`, và 1 dòng kết `ExcelReport: Excel report: <đường dẫn tuyệt đối>` (thiếu dòng này = lỗi #7 đã nổ).
- **Chạy suite 2 lần trong cùng 1 session Katalon.** RF03 và RF06 phải retry lại ở lần 2. Nếu pass ngay attempt 1 → reset run-id trong `ReportFixtureSupport` bị hỏng.

---

## 5. Tương tác với lỗi đang mở

- **#3 (`getEnvNameOnMae` không driver) — KHÔNG bị dính, do thiết kế.** Set `report.environment.label` khiến `resolveEnvironmentForSummary()` return ở nhánh pin (`:343-346`), không bao giờ gọi `tryGetEnvNameOnMae()`.
  Ghi nhận thêm: lần chạy 11:53 (chạy source đã sửa, dưới `Profiles/default.glbl` nơi cả `G_projectName` lẫn `G_VCBVersionFileName` đều được set nên guard `:362` KHÔNG short-circuit) **không sinh step FAILED giả nào** — `execution0.log` đi thẳng từ `Start action : ExcelReporter.startSuite` sang dòng info `ExcelReport: … initialized` rồi `End action`. `Executive Summary!E4` đọc `Windows 11 64bit/CHROME_DRIVERChrome 152`, tức nhãn suy ra, nghĩa là `getEnvNameOnMae` trả null im lặng. **Đừng suy rộng ra cho lần chạy cấu hình mobile** — #3 nên để mở cho tới khi quan sát được ở đó.
- **#5 (ghi đè nhãn `A20`) — KHÔNG dính nếu pin; dính nặng nếu không.** Không pin thì suite này là bản tái hiện tệ nhất: RF01–RF03 chạy không driver → trả fallback không cache (`:396-397`), rồi RF04 mở Chrome, suy ra nhãn và đóng dấu vào `A20` (`:385`), làm mọi dòng trước đó mồ côi khỏi bảng Environment Coverage. Đó là lý do pin là **một phần của thiết kế**, không phải suy nghĩ thêm.
- **🆕 LỖI MỚI phát hiện, chưa có trong `KNOWN_ISSUES.md`:** `report.environments` hiện chỉ có 1 nhãn, nên `writeFixedList` (`ExcelReportWriter.groovy:193-200`) xóa trắng `A21:A23`, và `COUNTIFS(…,$A21,…)` với ô tiêu chí rỗng **đếm mọi dòng trống**. Report 11:53 có `B21 = B22 = B23 = 194`. Fixture phải set đủ 4 nhãn không rỗng, nếu không sẽ kế thừa con số ma này. **Cần ghi vào KNOWN_ISSUES; chỗ sửa nằm ở reporter hoặc template, không phải ở fixture.**
- **#4 (thư mục rác) — dính mỗi lần chạy**, chỉ là mỹ quan, đã gitignore.
- **#6 (row budget) — không dính.** `computeMaxRow` = `10*(3+1)+50 = 90`, kẹp lên 200; fixture ghi 19 dòng.
- **#8 (chạy lẻ 1 case im lặng) — sẽ cắn người debug 1 case RF.** Ghi chú vào `<description>` của mỗi `.tc` và của suite.
- **Có sẵn, không liên quan reporter:** `BaseKeyword.startApplication()` ở `TestListener.groovy:248` ném cho mọi suite có id không chứa `KMS_M2U`, làm `sampleBeforeTestSuite` thành ERROR. Nó đã xảy ra với mọi lần chạy SauceDemo hiện nay. Tốn ~1 s, chỉ hiện trong HTML report của Katalon, **không bao giờ tới Excel report**. Chấp nhận. **Đừng** đặt tên suite chứa `KMS_M2U` để né — làm vậy sẽ âm thầm bật `G_runWebsite` ở cấp suite và đổi luôn đường đi đang test.

---

## 6. Rủi ro

| # | Rủi ro | Biểu hiện | Xử lý |
|---|---|---|---|
| **R1** | **`numberOfRerun=3` + `rerunImmediately=true` có thể KHÔNG có nghĩa "retry ngay case đang fail, tối đa 3 lần thêm".** Nếu Katalon chạy lại cả suite thì fixture cho ra 4 lượt đầy đủ 10 case và mọi con số kỳ vọng đều sai. Plan §6 và Phase 3 **giả định** retry theo case nhưng **chưa bao giờ kiểm chứng** — mọi lần chạy SauceDemo đều pass 5/5 nên không phân biệt được. | Test Details có ~40+ dòng và lặp lại RF01 | **Ưu tiên cao nhất**, và lần chạy fixture đầu tiên trả lời miễn phí: nếu dòng 2–20 khớp §4 thì giả định đúng. Nếu không, dựng suite 2 case vứt đi để kiểm trước khi động vào ma trận 10 case. |
| **R2** | `numberOfRerun=3` là 3 attempt tổng hay 1+3? | RF04/RF07 ra 3 dòng thay vì 4; RETRIES = 7 thay vì 9 | Đã phòng: RF03 pass ở attempt 3, RF06 ở attempt 2 — đều với tới được ở cả 2 cách hiểu. TOTAL/PASSED/FAILED/BLOCKED bất biến; chỉ RETRIES và số dòng đổi. |
| **R3** | **`skipThisTestCase()` có thể không kích hoạt `@AfterTestCase`, hoặc không cho status `SKIPPED`.** | dòng 19–20 mất, hoặc hiện PASSED | Bằng chứng gián tiếp là nó có chạy: `TestListener.groovy:161` có guard `if(G_isStopTestSuite) return` **bên trong** `@AfterTestCase` — vô nghĩa nếu hook không chạy. Nếu status hóa ra không phải `SKIPPED` thì đó là **phát hiện lớn**: nghĩa là nhánh map `SKIPPED/NOT_RUN/INCOMPLETE → BLOCKED` (`:248-251`) không bao giờ với tới được trong codebase này. **Báo lên chứ đừng vá quanh.** |
| R4 | Katalon có thể retry case `ERROR` khác case `FAILED` (RF07). | RF07 ra 1 dòng thay vì 4 | Không chặn: TOTAL/PASSED/FAILED/BLOCKED không đổi. |
| R5 | `shouldNotStartApp` return trước skip gate (`:65` trước `:69`) rất dễ bị phá bằng cách "tốt bụng" thêm biến đó vào RF09/RF10. | RF09/RF10 báo PASSED, BLOCKED = 0 | Script RF09/RF10 gọi `markFailedAndStop` chính là để lỗi này nổ ầm ĩ. Thêm comment trong `<description>`: *không được thêm `shouldNotStartApp` vào case này*. |
| R6 | `report.environment.label` ≠ `report.environments[0]`. | Environment Coverage toàn 0 trong khi Test Details vẫn ổn | Assert `B20 == 10`. Để 2 dòng cạnh nhau kèm comment. |
| R7 | Sửa `report.properties` đổi luôn hành vi của suite SauceDemo — pin environment xóa mất đường suy ra runtime, chính là chỗ lỗi #5 sống. | #5 hết tái hiện và trông như đã sửa | Cố ý, có ghi lại. **Đừng đóng #5 dựa trên một lần chạy có pin.** |
| R8 | Browser của RF04 không mở được. | RF04 vẫn FAILED nhưng cột I trống | Suy giảm êm theo thiết kế; 18 dòng còn lại không ảnh hưởng. |
| R9 | Khóa file Windows (plan R3). Hiện **đang có `~$Report.xlsx`** trong thư mục 11:53 → ai đó đang mở report bằng Excel. | giữa chừng `[WARN] … appears locked` rồi fallback `Report_1.xlsx` | Đóng hết cửa sổ `Report.xlsx` trước khi chạy. |
| R10 | Katalon Studio chưa refresh sau khi tạo tay `.tc`/`.ts`. | suite không chạy, hoặc không tìm thấy test case | `File > Refresh` và mở suite 1 lần (cũng để sinh runner, Bước 6). |
| R11 | **Parser antlr2 từ chối cú pháp Groovy hiện đại** trong helper hoặc script — đúng loại lỗi `KNOWN_ISSUES.md` #1 đã giết cả tính năng trong im lặng. | `Unresolved compilation problem` trong `console0.log`; `bin/keyword/com/vcb/fixture/*.class` là error stub | Sau lần chạy đầu, **grep `console0.log` tìm `Unresolved compilation problem` trước khi tin bất kỳ con số nào trong report.** |
| R12 | `G_isStopTestSuite` dính suốt trong 1 lần chạy. Case nào thêm sau RF10 sẽ bị BLOCKED. | maintainer sau thêm RF11 và bất ngờ | Ghi chú trong comment script RF08 và `<description>` của suite. |

### Điều mơ hồ thật sự cần báo lên

**R1 và R3 là giả định mà plan đã duyệt đưa ra nhưng chưa ai từng quan sát trong repo này.**
Toàn bộ fixture — và nửa retry/BLOCKED của spec reporter — dựa trên (a) `rerunImmediately=true`
retry ngay một case fail, và (b) `skipThisTestCase()` cho status `SKIPPED` mà `@AfterTestCase` vẫn
chạy. Cả hai đều rẻ để làm rõ: nhìn Test Details của lần chạy fixture đầu tiên là biết.
**Nếu một trong hai sai, hãy dừng và sửa spec của REPORTER, không phải sửa fixture** — một fixture
bị bẻ cong cho khớp hành vi sai còn tệ hơn không có fixture.

---

## 7. Danh sách file

**Tạo mới:** `Keywords/com/vcb/fixture/ReportFixtureSupport.groovy`;
8 × `Test Cases/Website/ReportFixture/RF0N *.tc`;
2 × `Test Cases/Website/ReportFixtureUnmapped/RF09|RF10 *.tc`;
10 × `Scripts/Website/<mirror>/<case>/Script1788800000NNN.groovy`;
`Test Suites/ReportFixture/ExcelReportFixture.ts`;
`Test Suites/ReportFixture/ExcelReportFixture.groovy` (Katalon sinh, Bước 6).

**Sửa:** `custom-report/report.properties` (3 dòng).

**Không bao giờ đụng:** `Libs/CustomKeywords.groovy`, `Libs/internal/GlobalVariable.groovy`,
`Test Listeners/TestListener.groovy`, `Keywords/com/vcb/report/**`, `custom-report/Template.xlsx`,
`Profiles/*.glbl`, `Test Suites/SauceDemo/**`.
