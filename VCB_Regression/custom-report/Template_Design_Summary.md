# Tóm tắt thiết kế: Template.xlsx — Báo cáo Test Tự Động (Dự án Ngân hàng, On-premise)

## 1. Bối cảnh
Dự án ngân hàng bảo mật cao, không được đồng bộ kết quả test lên Cloud (Katalon TestOps,
Allure Cloud...). Giải pháp: dùng 1 file Excel mẫu (`Template.xlsx`), TestListener trong
Katalon Studio/Selenium sẽ dùng **Apache POI** để ghi kết quả real-time ("cuốn chiếu") vào
file này trong lúc chạy CI/CD, không cần gọi ra ngoài internet.

Thiết kế tham chiếu theo các tính năng cốt lõi của Katalon TestOps (Execution Summary,
Test Execution Log, Module/Platform Coverage, Top Failures, Re-run/Retry tracking) nhưng
lược bỏ phần cần data nhiều lần chạy hoặc tích hợp Jira (Flaky Test Report, Traceability
Matrix, Requirement Coverage) vì vượt phạm vi 1 file Excel đơn giản.

## 2. Cấu trúc file — 2 sheet

### Sheet "Executive Summary" (dashboard cho Sếp/PO/Test Manager)
| Khu vực | Nội dung |
|---|---|
| Header & Metadata | Project Name, App Version, Execution Date, Environment, Triggered By (CI Job) — ô nền vàng nhạt để người dùng tự điền |
| Key Metrics (KPI cards) | TOTAL TESTS, PASSED, FAILED, BLOCKED, RETRIES, PASS RATE (màu tự động: xanh nếu Pass Rate ≥ 90%, đỏ nếu < 90%) |
| Module Breakdown | Bảng Module / Total / Pass / Fail / Blocked / Pass Rate, dùng `COUNTIFS` |
| Environment Coverage | Bảng Environment (OS/Browser) / Total / Pass / Fail / Pass Rate, dùng `COUNTIFS` |
| Top Failures | Tự động lọc tối đa 10 Test Case có Status = FAILED (không tính RETRY), lấy Test Case ID, Module, Error Message, Screenshot link |
| Charts | 1. Donut chart Pass/Fail/Blocked · 2. Stacked bar chất lượng theo Module · 3. Bar chart Pass Rate theo Environment — **xếp dọc bên dưới các bảng** (không đặt lệch sang phải) để luôn hiển thị khi cuộn xuống, không cần cuộn ngang |

### Sheet "Test Details" (raw data cho Tester/Automation Engineer)
| Cột | Nội dung | Ghi chú |
|---|---|---|
| A | No. | **Định dạng Text**, hỗ trợ retry dạng số thập phân: `1`, `1.1`, `1.2`, `2`, `3`... |
| B | Test Case ID | |
| C | Module | |
| D | Test Description | |
| E | Environment | OS/Browser/Host, vd `Windows10/Chrome120` |
| F | Status | `PASSED` / `FAILED` / `BLOCKED` / `RETRY` — có dropdown validation + conditional formatting màu (xanh/đỏ/vàng/xám) |
| G | Execution Time (s) | |
| H | Error Message | |
| I | Screenshot Path | Dùng công thức `HYPERLINK(...)` |
| J | FailRank (helper) | Cột ẩn logic — đếm dồn số lượng FAILED tới dòng đó, phục vụ bảng Top Failures. **Không xóa/sửa** |
| L | Legend | Chú thích màu Status |

## 3. Quy ước quan trọng cần TestListener tuân thủ khi ghi dữ liệu

1. **Retry encode ngay trong cột No.**, không cần cột "Retry Count" riêng:
   - Mỗi Test Case chỉ có **đúng 1 dòng mang Status thật** (PASSED/FAILED/BLOCKED) — là
     dòng cuối cùng của TC đó.
   - Mọi lần chạy trước đó (nếu bị retry) đều ghi Status = `RETRY`, đánh số thập phân nối
     tiếp: TC gốc là dòng `3` → retry lần 1 là `3.1` → retry lần 2 là `3.2`...
   - **Bắt buộc ghi cột No. dưới dạng chuỗi (Text)**, không phải Number, để tránh Excel tự
     làm tròn/trùng `1.10` với `1.1`. Vì Katalon thường giới hạn retry 1–3 lần nên định
     dạng `3.1`, `3.2` (không pad-zero) là đủ an toàn; chỉ cần pad-zero (`3.01`, `3.02`)
     nếu dự kiến retry > 9 lần.

2. **Tất cả công thức KPI/Module/Environment đều loại trừ Status = RETRY** khi đếm Total —
   RETRY chỉ là kết quả tạm thời, không tính là 1 test case độc lập.

3. **Environment phải ghi đúng chuỗi khớp tuyệt đối** với danh sách đã liệt kê ở bảng
   Environment Coverage (`Executive Summary`), nếu không dòng đó sẽ không được đếm vào
   bảng coverage (vẫn xuất hiện đầy đủ ở Test Details, chỉ là không gộp được vào summary).

4. **Cột J (FailRank) phải được TestListener tự tính lại hoặc để Excel formula tự chạy**
   — đây là formula `=IF(F2="FAILED",COUNTIF($F$2:F2,"FAILED"),"")`, tự cập nhật khi có
   dòng mới, không cần TestListener ghi tay.

## 4. Ràng buộc kỹ thuật khi build/mở rộng file

- Toàn bộ range công thức hiện tại giả định data từ dòng 2 đến dòng **200** ở
  `Test Details`. Nếu 1 lần chạy suite có > ~198 dòng (tính cả các dòng RETRY), cần mở
  rộng range trong mọi công thức ở cả 2 sheet.
- Bảng Module Breakdown và Environment Coverage dùng **danh sách cố định** (4 module, 4
  environment mẫu) — cần sửa/thêm dòng thủ công cho khớp thực tế dự án, nhớ kéo dài công
  thức `COUNTIFS` khi thêm dòng.
- Bảng Top Failures giới hạn tối đa 10 dòng (có thể mở rộng bằng cách copy công thức
  xuống thêm dòng).
- Toàn bộ công thức chỉ dùng hàm tương thích Excel 2007 trở lên + LibreOffice
  (`COUNTIFS`, `INDEX`, `MATCH`, `IFERROR`...), **không dùng** `XLOOKUP`/`FILTER`/`UNIQUE`
  vì môi trường build/recalc nội bộ (LibreOffice) không hỗ trợ đúng các hàm này.
- File đã được recalculate (LibreOffice) và xác nhận **0 lỗi công thức** (99 công thức).

## 5. Việc chưa làm (bước tiếp theo)
- Code mẫu Java/Groovy cho **TestListener** dùng Apache POI để: (a) mở `Template.xlsx` có
  sẵn (không tạo file mới), (b) ghi từng dòng real-time vào `Test Details` theo đúng quy
  ước No./Status ở mục 3, (c) chụp & lưu screenshot khi FAILED, (d) gọi `FormulaEvaluator`
  để cache lại giá trị công thức trước khi lưu (vì Apache POI không tự tính công thức như
  Excel — nếu không làm bước này, KPI card ở Executive Summary sẽ hiện trống/0 khi mở
  bằng máy không có Excel).
