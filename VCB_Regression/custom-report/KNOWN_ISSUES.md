# Excel Report — Known Issues / Vấn đề đã biết

Nơi tập trung mọi vấn đề của tính năng Excel report (`Keywords/com/vcb/report/`).
Spec: `TestListener_Excel_Report_Plan.md`. Thiết kế template: `Template_Design_Summary.md`.

**Trạng thái tổng quan (cập nhật 2026-09-07):**

| # | Vấn đề | Mức độ | Trạng thái |
|---|---|---|---|
| 1 | `do/while` không biên dịch được | 🔴 Chặn | ✅ ĐÃ SỬA |
| 2 | `KeywordUtil.logWarning()` không tồn tại (18 chỗ) | 🔴 Chặn | ✅ ĐÃ SỬA |
| 3 | `getEnvNameOnMae()` gọi khi chưa có driver | 🟠 Cao | ⏸️ CHỜ QUYẾT ĐỊNH |
| 4 | Thư mục output rác không bao giờ được dọn | 🟡 Trung bình | ⏸️ CHỜ QUYẾT ĐỊNH |
| 5 | Environment label ghi đè `A20` → đếm thiếu | 🟠 Cao | ⏸️ Lỗi trong plan |
| 6 | Hết quota dòng → mất nguyên test case khỏi KPI | 🟠 Cao | ⏸️ Chưa sửa |
| 7 | `endSuite` đường lỗi bỏ qua `close()` + log đường dẫn | 🟡 Trung bình | ⏸️ Chưa sửa |
| 8 | Chạy lẻ 1 test case: im lặng, không log gì | 🟡 Trung bình | ⏸️ Lỗ hổng của plan |
| 9 | Các điểm nhỏ | ⚪ Thấp | ⏸️ Chưa sửa |
| 10 | Ô tiêu chí Environment rỗng → đếm mọi dòng trống | 🟠 Cao | ⏸️ Chưa sửa |

---

## ✅ #1 — `do/while` không biên dịch được (ĐÃ SỬA)

**File:** `ExcelReportWriter.groovy:390` (`nextAvailableFallbackFile()`)

Katalon Studio build bằng parser Groovy cũ (antlr2), **không hỗ trợ `do { } while`**.

**Bằng chứng:** `bin/keyword/com/vcb/report/ExcelReportWriter.class` là error stub
chứa `"Unresolved compilation problem"` (3 class còn lại + `TestListener.class` đều sạch).
Log chạy thật `Reports/20260907_111815/.../console0.log:10`:

```
java.lang.Error: Unresolved compilation problem: Groovy:unexpected token: do
```

**Hậu quả:** `new ExcelReportWriter()` ném `java.lang.Error` → **không bao giờ tạo được
`Report.xlsx`**. Thư mục `custom-report/output/<suite>_<ts>_<pid>/screenshots/` vẫn được tạo
(vì `mkdirs()` chạy trước ở `ExcelReporter.groovy:110-113`) nhưng rỗng.

**Cách sửa:** đổi thành vòng `while` tương đương.

**Đã kiểm chứng:** biên dịch lại cả 4 file bằng đúng parser antlr2 → OK. Đã quét toàn bộ
cú pháp Groovy hiện đại khác (`var`, `::`, lambda `->`, try-with-resources, `!in`,
`!instanceof`, `?[`, multi-assignment) → **0 occurrence**, không có lỗi parser thứ hai.

---

## ✅ #2 — `KeywordUtil.logWarning()` không tồn tại (ĐÃ SỬA)

**File:** 18 chỗ — `ExcelReportConfig.groovy:25`;
`ExcelReporter.groovy:53,64,74,87,232,279,308,336,479,488,494`;
`ExcelReportWriter.groovy:118,145,157,325,364,371`

Katalon 11.4.0 chỉ có `markWarning()` / `logInfo()` / `markFailed()`…, **không có
`logWarning()`**. Toàn bộ codebase hiện tại dùng `markWarning` (`BaseKeyword.groovy:58`,
`ZakatPage.groovy:161`, `ApplyCASAPage.groovy:1712`) — code mới tự bịa ra tên method.

**Bằng chứng (log chạy thật):**

```
MissingMethodException: No signature of method:
static com.kms.katalon.core.util.KeywordUtil.logWarning()
Possible solutions: markWarning(java.lang.String)
	at com.vcb.report.ExcelReporter.startSuite(ExcelReporter.groovy:53)
	at TestListener.sampleBeforeTestSuite(TestListener.groovy:240)
```

**Tại sao nghiêm trọng hơn #1:** 15/18 lời gọi nằm **bên trong khối `catch`**, nên nó biến
exception đã nuốt thành exception mới được ném ra — **phá vỡ đảm bảo R2** ("không lỗi report
nào được làm hỏng test case / suite").

Hậu quả đã quan sát được: exception thoát khỏi `startSuite` lên `sampleBeforeTestSuite`,
làm hỏng hook **ngay câu lệnh đầu tiên** → `G_isRunTestSuite = true` (`TestListener.groovy:241`)
và `BaseKeyword.startApplication()` (`:248`) **không chạy**. Suite web thì vô hại; suite
**mobile VCB thì app không được khởi động ở cấp suite**.

Đường đi còn tệ hơn đang nằm chờ: `ExcelReporter.groovy:279` nằm trên **đường chạy bình thường**
(mỗi khi module không có trong `report.modules`). `report.properties` hiện là
`SauceDemo,Sanity,Uncategorized,Other` → **mọi test case VCB mobile** (`DuitNow`, `Tabung`…)
dính ngay case đầu → ném → thoát `endTestCase` → hỏng `sampleAfterTestCase` ngay câu đầu →
**bỏ qua `takeScreenshot()`, `restartApp()`, `Mobile.closeApplication()` cho cả 1400 case**.

**Cách sửa:** KHÔNG chỉ đổi tên thành `markWarning`. Cấu trúc `catch { KeywordUtil.xxx(...) }`
vốn không an toàn. Đã thêm `ExcelReportLog.groovy` — logger riêng tự nuốt mọi lỗi
(`try/catch (Throwable)`, fallback `println`), mọi call site đi qua nó.

**Quyết định thiết kế:** `ExcelReportLog.warn()` dùng `KeywordUtil.logInfo()` với tiền tố
`[WARN]`, **không dùng `markWarning()`**. Lý do: `markWarning` đánh dấu step là WARNING và có
thể làm đổi trạng thái suite — với pack regression ngân hàng, một trục trặc của report
**không được phép** làm suite chuyển vàng. Cảnh báo vẫn hiện đầy đủ trong console log nên
không hề "im lặng". Nếu team muốn ngược lại, chỉ cần sửa 1 dòng trong `ExcelReportLog`.

---

## ⏸️ #3 — `getEnvNameOnMae()` gọi khi chưa có driver (CHỜ QUYẾT ĐỊNH)

**File:** `ExcelReporter.groovy:364` (`tryGetEnvNameOnMae`), gọi từ `:343` ← `:130` (`doStartSuite`)

Chuỗi gọi: `resolveEnvironmentForSummary` → `tryGetEnvNameOnMae` → `BasePage.getPage(CommonPage)`
→ `BasePage.groovy:35-37` `new ExecutionSetting()` → `ExecutionSetting.groovy:34-35`
`Mobile.getDeviceWidth()` / `MobileDriverFactory.getDriver()`.

Nhưng `ExcelReporter.startSuite` là **câu lệnh đầu tiên** của `@BeforeTestSuite`
(`TestListener.groovy:240`), còn app chỉ khởi động ở `:248` → **chưa có driver**.

`Mobile.getDeviceWidth()` là keyword Katalon: nó **ghi log FAILED trước rồi mới ném**.
`catch (Throwable)` ở `:366` nuốt được exception nhưng **không xóa được dòng log** → mọi lần
chạy suite đều có 1 step FAILED giả trong mục "Before Test Suite", cộng thêm thời gian chờ
driver timeout vô ích.

**Chưa được kiểm chứng thực tế** — lần chạy 2026-09-07 11:18 chết ở `:118` (lỗi #1) trước khi
tới `:130`. Profile dùng là `default` (không phải web), mà `Profiles/default.glbl` **có** set
`G_VCBVersionFileName`, nên guard ở `:363` sẽ KHÔNG short-circuit. Lỗi này sẽ nổ ngay lần chạy
kế tiếp sau khi #1, #2 được sửa.

**Hai hướng xử lý cần chọn:**
- **A** — Bỏ hẳn `getEnvNameOnMae()` khỏi `@BeforeTestSuite`; ô `E4` lấy từ config /
  `RunConfiguration.getExecutionProfile()`. Đơn giản, không rủi ro, nhưng mất nhãn env chính xác.
- **B** — Dời việc suy ra environment xuống **test case đầu tiên** (khi driver đã sống), rồi
  ghi ngược vào `E4` + `A20`. Đúng hơn về dữ liệu, nhưng phức tạp hơn và liên quan trực tiếp #5.

---

## ⏸️ #4 — Thư mục output rác không bao giờ được dọn (CHỜ QUYẾT ĐỊNH)

**File:** `ExcelReporter.groovy:110-113`

`execDir.mkdirs()` + `screenshotsDir.mkdirs()` chạy **trước** khi khởi tạo writer, nên thư mục
vẫn được tạo kể cả trên đường lỗi. Không có chỗ nào dọn: grep
`delete|deleteDir|cleanup|prune|retention` trong `Keywords/com/vcb/report/` → **0 hit**.
`catch` ở `:51-54` và `resetSuiteState()` đều không xóa.

Hậu quả: **mọi** lần chạy (hỏng hay thành công) đều để lại một thư mục
`custom-report/output/<suite>_<yyyyMMdd_HHmmss>_<pid>/` vĩnh viễn. Với pack 1400 case chạy
nightly, thư mục phình vô hạn — lại nằm trong `.gitignore` nên không ai để ý cho tới khi hết đĩa.

Plan §3 chỉ mô tả layout, **không nói gì về retention**.

**Hai hướng xử lý cần chọn:** xóa thư mục khi `startSuite` thất bại (tối thiểu), và/hoặc thêm
chính sách giữ N lần chạy gần nhất.

---

## ⏸️ #5 — Environment label ghi đè `A20` → Environment Coverage đếm thiếu

**File:** `ExcelReporter.groovy:371-399` (`resolveEnvironmentLabel`), ghi `A20` ở `:386`

`resolveEnvironmentLabel` chỉ cache khi đã có driver; trước đó nó trả về giá trị fallback từ
config mà **không cache** (`:397-398`), trong khi lần suy ra thành công sau đó lại **ghi đè `A20`**.

Kịch bản: `report.environments=Windows11/Chrome`. Test case đầu bị BLOCKED trước khi driver lên
(vd `TestListener.groovy:69-71` `skipThisTestCase()` sau khi dependency fail). Dòng 2 mang
`E="Windows11/Chrome"`. Test case 2 suy ra `Windows 11/Chrome146` và đóng dấu vào `A20`.
Dòng 2 giờ **không khớp bất kỳ dòng Environment Coverage nào** (A21:A23 bị `writeFixedList` xóa
trắng) → tổng Environment Coverage ≠ TOTAL TESTS. Đúng loại lỗi "nhìn thì ổn mà sai" mà plan §11
tuyên bố không chấp nhận được.

Cơ chế này cũng phá hỏng danh sách 4 environment nếu cấu hình cố ý: nhãn `A20` bị ghi đè lúc runtime.

**Lưu ý: đây là lỗi trong PLAN (§8 điểm 3), code chỉ làm đúng theo plan.** Sửa code không đủ,
cần sửa cả spec. Liên quan trực tiếp tới #3 phương án B.

---

## ⏸️ #6 — Hết quota dòng → mất nguyên test case khỏi KPI

**File:** `ExcelReporter.groovy:176-197`

`resolveDisplayNoAndHandleRetry` (`:176`) đổi cột F của lần chạy trước thành `RETRY` (`:215`)
**trước khi** thử `appendRow` (`:192`). Khi `appendRow` trả về `-1` (hết quota), code cảnh báo rồi
return ở `:193-197`, để lại dòng trước mang nhãn `RETRY` mà **không có dòng kế thừa**.

Mọi công thức KPI/Module/Environment đều loại trừ `RETRY` (`Template_Design_Summary.md` §3.2),
nên test case đó **biến mất hoàn toàn** khỏi `TOTAL TESTS`, `PASSED`, `FAILED` — chứ không chỉ là
"mất lần chạy cuối".

Kịch bản: suite mà file `.ts` không parse được sẽ fallback `maxRow=200`
(`computeMaxRow:324/337`); tới case ~210, một case bị retry sẽ mất trắng.
Cảnh báo `A6` vẫn có nên ít nhất thiệt hại được báo.

---

## ⏸️ #7 — `endSuite` trên đường lỗi bỏ qua `close()` và log đường dẫn

**File:** `ExcelReporter.groovy:79-91`

`evaluateAllFormulas()` (`:82`) có thể ném trước khối try của chính nó —
`ExcelReportWriter.groovy:321` `workbook.getCreationHelper().createFormulaEvaluator()` nằm ngoài
guard. `save()` (`:83`) cũng có thể ném. Khi đó `catch` ở `:86` chạy, còn `finally` chỉ set
`enabled = false`: `writer.close()` ở `:85` **không bao giờ chạy**, và
`logInfo("Excel report: <đường dẫn>")` ở `:84` **không bao giờ in** → tester không biết file ở đâu.
`resetSuiteState:150` sau đó null `writer` mà không close.

Lỗi tài liệu kèm theo: `ExcelReportWriter.groovy:344-346` ghi chú save "Never throws", nhưng
`new FileOutputStream(tmp)` (`:350`), `workbook.write` (`:352`) và
`moveAtomically(tmp, fallback)` (`:372`) đều có thể ném. Caller có bắt `Throwable` nên chưa thành
bug thật, nhưng comment sẽ đánh lừa người bảo trì sau.

---

## ⏸️ #8 — Chạy lẻ 1 test case: im lặng, không giải thích

**File:** `ExcelReporter.groovy:28-30, 60, 71`

Katalon chạy lẻ 1 test case thì kích hoạt `@BeforeTestCase`/`@AfterTestCase` nhưng **không**
kích hoạt `@BeforeTestSuite`/`@AfterTestSuite` → `startSuite()` không chạy → `enabled` vẫn
`false` (`:30`), `writer` vẫn null (`:29`).

- `startTestCase` thoát ngay ở `:60` (`if (!enabled) return`)
- `endTestCase` thoát ở `:71` (`if (!enabled || writer == null) return`)

**An toàn** (không NPE, không ném gì, test case chạy y như cũ) **nhưng vô hình**: không report,
và **không một dòng log nào giải thích**. Tester debug 1 case sẽ tưởng tính năng hỏng.
Đường `report.enabled=false` (`:99-102`) cũng câm y hệt.

**Static state giữa 2 lần chạy trong cùng JVM: đã truy, KHÔNG có lỗi.** `doStartSuite:97` gọi
`resetSuiteState()` (`:139-152`) xóa sạch `nextSequenceNo`, `lastAttempt`, `attemptCount`,
`baseNoCache`, `descriptionCache`, `loggedUnmatchedModules`, `cachedEnvironmentLabel`,
`rowBudgetWarned`, `writer`, `screenshotsDir`. Lần chạy 2 không kế thừa gì của lần 1.

**Lỗ hổng của PLAN:** `TestListener_Excel_Report_Plan.md` hoàn toàn không đề cập chế độ chạy
ngoài suite (§5 chỉ map 4 hook; R4 nói về collection/parallel chứ không nói "không có suite").
Nên hành vi im lặng hiện tại là **chưa được thiết kế**, không phải một quyết định.

**Đề xuất:** giữ nguyên tắt nhưng log 1 dòng (có cờ chống spam 1400 dòng). Chỉ làm lazy-init
tạo report cho 1 case nếu tester thực sự yêu cầu — vì `@AfterTestSuite` không chạy nên sẽ không
có gì gọi `evaluateAll()`/`close()`.

---

## ⏸️ #9 — Các điểm nhỏ

- **`ExcelReporter.groovy:167-168`** — `attemptNumber` được tính trước khi phát hiện retry, nên
  lần chạy thứ hai (không phải retry) của một id đã PASSED sẽ sinh tên file `..._a2.png` trên một
  dòng mang số nguyên mới. Chỉ ảnh hưởng tên file.
- **`ExcelReportWriter.groovy:65`** — `new XSSFWorkbook(new FileInputStream(targetFile))` không
  đóng stream tường minh. Đã test thực tế trên máy này (POI 5.5.1 / JDK 21): POI tự đóng qua
  `ZipInputStreamZipEntrySource`, và 2 chu kỳ `write` + `Files.move(ATOMIC_MOVE, REPLACE_EXISTING)`
  liên tiếp lên chính file vừa đọc đều thành công → **không** tự khóa mình vào nhánh
  `Report_1.xlsx`.
- **`CommonPage.groovy:509`** — `getEnvNameOnMae` resolve `VCBVersion.xlsx` qua
  `System.getProperty('user.dir')`, vốn KHÔNG phải project dir khi chạy console mode → giá trị `E4`
  mà plan §10a trông đợi sẽ thường xuyên âm thầm rơi về execution profile trong CI.
  Code có sẵn từ trước, nhưng plan §10a đang dựa vào nó.

---

## ⏸️ #10 — Ô tiêu chí Environment rỗng → COUNTIFS đếm mọi dòng trống

**File:** `ExcelReportWriter.groovy:193-200` (`writeFixedList`)

`report.properties` hiện chỉ có **1** nhãn trong `report.environments`, nên `writeFixedList` xóa
trắng `A21:A23`. Công thức `COUNTIFS('Test Details'!$E$2:$E$200, $A21, …)` với ô tiêu chí **rỗng**
sẽ khớp mọi dòng trống trong dải 2–200 → đếm ra số ma.

**Bằng chứng:** report của lần chạy 11:53
(`custom-report/output/SauceDemoTests_20260907_115334_12624/Report.xlsx`) có
**`B21 = B22 = B23 = 194`** trong khi thực tế chỉ có 5 test case.

**Hậu quả:** bảng Environment Coverage hiển thị 3 dòng rác với số liệu vô nghĩa, và biểu đồ
Environment (chart3, neo vào `$E$20:$E$23`) vẽ theo số ma đó.

**Cách sửa (chưa làm):** hai lựa chọn —
- Ở **reporter**: `writeFixedList` khi gặp slot rỗng thì ghi một giá trị không bao giờ khớp
  (vd `" "`), hoặc xóa luôn công thức COUNTIFS của dòng đó.
- Ở **template**: bọc mỗi COUNTIFS bằng `IF($A21="","",COUNTIFS(...))`.

**Cách né tạm thời:** điền đủ 4 nhãn không rỗng trong `report.environments`.

---

## Những gì đã được kiểm chứng là ĐÚNG

Đối chiếu trực tiếp với nội tại `Template.xlsx`:

- Cột A ghi dạng String kèm style dòng 2, không bao giờ dùng double — `ExcelReportWriter.groovy:274-280` ✓ (`A2` có `s="20"`)
- Retry ghi ngược thành `RETRY` + mã hóa `3` → `3.1` → `3.2` — `ExcelReporter.groovy:205-226` ✓
- FailRank ghi trên **mọi** dòng append — `ExcelReportWriter.groovy:307-313` ✓
- `$X$200` → `$X$<maxRow>`: cả 142 tham chiếu tuyệt đối trong template đều dùng cột B, C, E, F, H, I, J — nằm trọn trong `[A-J]` của regex; đầu cuối tương đối của `COUNTIF($F$2:F2,...)` không bị đụng ✓
- CF/DV: `Test Details` có đúng **1** block `conditionalFormatting sqref="F2:F200"` chứa cả 4 rule và **1** `dataValidation sqref="F2:F200"` → ghi đè toàn bộ formatting range là an toàn ✓
- Ô cảnh báo `Executive Summary!A6`: dòng 6 không tồn tại trong template và không nằm trong vùng merge nào (các merge là `A1:J1`, `A7:J7`, `A11:E11`, `A18:E18`, `A25:E25`, `A38:F38`) ✓
- `setForceFormulaRecalculation(true)` mỗi lần save (`writer:348`); `evaluateAll()` đúng 1 lần cuối suite kèm fallback từng ô (`writer:320-340`) ✓
- Save nguyên tử tmp + `Files.move` với fallback `ATOMIC_MOVE`, retry 200ms, fallback `Report_N.xlsx` ✓
- Xóa dữ liệu mẫu dòng 2–11 **chỉ cột A–J**, giữ legend `L2:L5` (`writer:82-93`) ✓
- App Version chỉ lấy từ config, không bao giờ từ `VCBVersion.xlsx` (`ExcelReporter:128`, `writer:165`) ✓
- Không annotate `@Keyword`; static facade đúng style `BaseKeyword`/`MobileDriver`; không sửa file generated ✓
- `XmlSlurper` có trong `groovy-3.0.17.jar` của Katalon; file `.tc` thật sự có element `<description>`; `ProcessHandle.current().pid()` OK trên JavaSE-21 ✓
