package com.vcb.report

import java.nio.file.AtomicMoveNotSupportedException
import java.nio.file.Files
import java.nio.file.StandardCopyOption
import java.util.regex.Matcher
import java.util.regex.Pattern

import org.apache.poi.ss.usermodel.Cell
import org.apache.poi.ss.usermodel.CellStyle
import org.apache.poi.ss.usermodel.CellType
import org.apache.poi.ss.usermodel.ConditionalFormatting
import org.apache.poi.ss.usermodel.FormulaEvaluator
import org.apache.poi.ss.usermodel.Row
import org.apache.poi.ss.usermodel.Sheet
import org.apache.poi.ss.usermodel.SheetConditionalFormatting
import org.apache.poi.ss.util.CellRangeAddress
import org.apache.poi.ss.util.CellReference
import org.apache.poi.xssf.usermodel.XSSFSheet
import org.apache.poi.xssf.usermodel.XSSFWorkbook


/**
 * All Apache POI code for the Excel test report lives here. Knows nothing about Katalon
 * test-lifecycle concepts (TestCaseContext, TestSuiteContext, retry rules, etc.) -
 * com.vcb.report.ExcelReporter owns that and calls into this class.
 */
class ExcelReportWriter {

	private static final String SHEET_DETAILS = 'Test Details'
	private static final String SHEET_SUMMARY = 'Executive Summary'

	private static final int COL_A = 0   // No.
	private static final int COL_B = 1   // Test Case ID
	private static final int COL_C = 2   // Module
	private static final int COL_D = 3   // Test Description
	private static final int COL_E = 4   // Environment
	private static final int COL_F = 5   // Status
	private static final int COL_G = 6   // Execution Time (s)
	private static final int COL_H = 7   // Error Message
	private static final int COL_I = 8   // Screenshot Path
	private static final int COL_J = 9   // FailRank (helper)

	private static final int SAMPLE_FIRST_ROW = 1   // Excel row 2, 0-based
	private static final int SAMPLE_LAST_ROW = 10   // Excel row 11, 0-based
	private static final int TEMPLATE_MAX_ROW = 200

	private static final Pattern RANGE_200 = Pattern.compile(/\$([A-J])\$200\b/)

	private XSSFWorkbook workbook
	private XSSFSheet detailsSheet
	private XSSFSheet summarySheet
	private File targetFile
	private CellStyle[] columnStyles = new CellStyle[10] // A..J, indexed 0..9

	private int nextRowIndex = SAMPLE_FIRST_ROW // 0-based, next Test Details row to write to
	private int lastWrittenRowIndex = -1
	private int maxRow = TEMPLATE_MAX_ROW

	/** Copies templateFile to targetFile and opens the copy. Call exactly once per suite execution. */
	void open(File templateFile, File targetFile) {
		Files.copy(templateFile.toPath(), targetFile.toPath(), StandardCopyOption.REPLACE_EXISTING)
		this.targetFile = targetFile
		this.workbook = new XSSFWorkbook(new FileInputStream(targetFile))
		this.detailsSheet = workbook.getSheet(SHEET_DETAILS)
		this.summarySheet = workbook.getSheet(SHEET_SUMMARY)
		cacheColumnStyles()
		clearSampleData()
	}

	private void cacheColumnStyles() {
		Row row2 = detailsSheet.getRow(SAMPLE_FIRST_ROW)
		for (int c = COL_A; c <= COL_J; c++) {
			Cell cell = row2?.getCell(c)
			columnStyles[c] = cell?.getCellStyle()
		}
	}

	/** Clears the template's 10 demo rows (Test Details rows 2-11, columns A-J only).
	 *  Column L (the PASSED/FAILED/BLOCKED/RETRY legend, rows 2-5) is never touched. */
	private void clearSampleData() {
		for (int r = SAMPLE_FIRST_ROW; r <= SAMPLE_LAST_ROW; r++) {
			Row row = detailsSheet.getRow(r)
			if (row == null) continue
			for (int c = COL_A; c <= COL_J; c++) {
				Cell cell = row.getCell(c)
				if (cell != null) {
					cell.setBlank()
				}
			}
		}
	}

	/* ================= Row-budget expansion (plan §11) ================= */

	/** Rewrites every "$X$200" absolute range end (X in A..J) on both sheets to "$X$<maxRow>",
	 *  extends the Test Details F-column conditional formatting and data validation sqref to
	 *  match, and remembers maxRow for the append-beyond-budget guard rail. */
	void expandRowBudget(int newMaxRow) {
		this.maxRow = Math.max(newMaxRow, TEMPLATE_MAX_ROW)
		rewriteFormulas(detailsSheet)
		rewriteFormulas(summarySheet)
		extendConditionalFormatting()
		extendDataValidation()
	}

	private void rewriteFormulas(Sheet sheet) {
		for (Row row : sheet) {
			for (Cell cell : row) {
				if (cell.getCellType() == CellType.FORMULA) {
					String formula = cell.getCellFormula()
					String updated = replaceRangeEnd(formula)
					if (updated != formula) {
						try {
							cell.setCellFormula(updated)
						} catch (Throwable t) {
							ExcelReportLog.warn("ExcelReporter: failed to rewrite formula on ${sheet.getSheetName()}!${cell.getAddress()}: ${t}")
						}
					}
				}
			}
		}
	}

	private String replaceRangeEnd(String formula) {
		Matcher m = RANGE_200.matcher(formula)
		StringBuilder sb = new StringBuilder()
		while (m.find()) {
			m.appendReplacement(sb, Matcher.quoteReplacement('$' + m.group(1) + '$' + maxRow))
		}
		m.appendTail(sb)
		return sb.toString()
	}

	private void extendConditionalFormatting() {
		try {
			SheetConditionalFormatting scf = detailsSheet.getSheetConditionalFormatting()
			CellRangeAddress[] newRange = [CellRangeAddress.valueOf("F2:F${maxRow}")] as CellRangeAddress[]
			for (int i = 0; i < scf.getNumConditionalFormattings(); i++) {
				ConditionalFormatting cf = scf.getConditionalFormattingAt(i)
				cf.setFormattingRanges(newRange)
			}
		} catch (Throwable t) {
			ExcelReportLog.warn("ExcelReporter: could not extend conditional formatting past row ${TEMPLATE_MAX_ROW}: ${t}")
		}
	}

	private void extendDataValidation() {
		try {
			def dvs = detailsSheet.getCTWorksheet().getDataValidations()
			if (dvs != null) {
				String newSqref = "F2:F${maxRow}".toString()
				dvs.getDataValidationArray().each { dv -> dv.setSqref([newSqref]) }
			}
		} catch (Throwable t) {
			ExcelReportLog.warn("ExcelReporter: could not extend data validation past row ${TEMPLATE_MAX_ROW}: ${t}")
		}
	}

	/* ================= Metadata / fixed lists (plan §8, §10) ================= */

	void writeMetadata(String projectName, String appVersion, String executionDate, String environment, String triggeredBy) {
		setSummaryString('B3', projectName)
		setSummaryString('E3', appVersion) // blank when unset - §10a, not a defect
		setSummaryString('B4', executionDate)
		setSummaryString('E4', environment)
		setSummaryString('B5', triggeredBy)
	}

	/** Overwrites just the Environment metadata cell (E4). Used when the run-level environment
	 *  label is derived lazily after the first test case starts (§8 point 3). */
	void updateEnvironmentMetadata(String environment) {
		setSummaryString('E4', environment)
	}

	/** Module Breakdown labels, A13:A16 - exactly 4 slots, the fixed-list constraint (§10).
	 *  Modules beyond the configured list are collapsed by ExcelReporter before this is called. */
	void writeModuleLabels(List<String> modules) {
		writeFixedList(13, 16, modules)
	}

	/** Environment Coverage labels, A20:A23 - same 4-slot constraint. */
	void writeEnvironmentLabels(List<String> environments) {
		writeFixedList(20, 23, environments)
	}

	/** Overwrites just the first Environment Coverage label (A20) so its COUNTIFS keeps matching
	 *  column E by construction once the run-level environment label is derived (§8 point 3). */
	void updateFirstEnvironmentLabel(String label) {
		setCellString(summarySheet, 19, 0, label) // A20, 0-based row 19
	}

	private void writeFixedList(int firstRow1based, int lastRow1based, List<String> values) {
		int slot = 0
		for (int r = firstRow1based; r <= lastRow1based; r++) {
			String value = slot < values.size() ? values[slot] : ''
			setCellString(summarySheet, r - 1, 0, value)
			slot++
		}
	}

	private void setSummaryString(String address, String value) {
		CellReference ref = new CellReference(address)
		setCellString(summarySheet, ref.getRow(), ref.getCol(), value ?: '')
	}

	private void setCellString(Sheet sheet, int row0, int col0, String value) {
		Row r = sheet.getRow(row0) ?: sheet.createRow(row0)
		Cell c = r.getCell(col0) ?: r.createCell(col0)
		c.setCellValue(value ?: '')
	}

	/** Loud row-budget-exhausted warning, stamped into Executive Summary!A6 (§11 point 4). */
	void writeRowBudgetExhaustedWarning(String message) {
		setCellString(summarySheet, 5, 0, message) // A6, 0-based row 5
	}

	/* ================= Row append (plan §6, §7, §9) ================= */

	int getLastWrittenRowIndex() {
		return lastWrittenRowIndex
	}

	/** 1-based Excel row number the *next* appendRow() call will write to - used to name a
	 *  screenshot file before the row exists yet (§9: the screenshot is captured before teardown,
	 *  which happens before the row is appended). */
	int getNextRowNumber() {
		return nextRowIndex + 1
	}

	int getMaxRow() {
		return maxRow
	}

	/** Appends one row for one attempt. Returns the 0-based row index written, or -1 if the row
	 *  budget is exhausted - the caller must stop appending and stamp the loud warning (§11 point 4). */
	int appendRow(TestResultRecord record) {
		int rowIndex = nextRowIndex
		int excelRowNumber = rowIndex + 1
		if (excelRowNumber > maxRow) {
			return -1
		}

		Row row = detailsSheet.getRow(rowIndex) ?: detailsSheet.createRow(rowIndex)

		setNoCell(row, record.displayNo)
		setStringCell(row, COL_B, record.displayId)
		setStringCell(row, COL_C, record.module)
		setStringCell(row, COL_D, record.description)
		setStringCell(row, COL_E, record.environment)
		setStringCell(row, COL_F, record.status)
		setNumericCell(row, COL_G, record.durationSeconds)
		setStringCell(row, COL_H, record.errorMessage)

		if (record.screenshotRelPath) {
			setHyperlinkCell(row, COL_I, record.screenshotRelPath)
		}

		setFailRankFormula(row, excelRowNumber)

		nextRowIndex++
		lastWrittenRowIndex = rowIndex
		return rowIndex
	}

	/** Rewrites column F of a previously-written row in place - the retry back-rewrite (§6). */
	void setStatus(int rowIndex, String status) {
		Row row = detailsSheet.getRow(rowIndex)
		if (row == null) return
		setStringCell(row, COL_F, status)
	}

	private void setNoCell(Row row, String displayNo) {
		Cell cell = row.getCell(COL_A) ?: row.createCell(COL_A)
		if (columnStyles[COL_A] != null) {
			cell.setCellStyle(columnStyles[COL_A])
		}
		cell.setCellValue(displayNo) // String, never double - "3.10" must never collapse to 3.1
	}

	private void setStringCell(Row row, int col, String value) {
		if (!value) return
		Cell cell = row.getCell(col) ?: row.createCell(col)
		if (columnStyles[col] != null) {
			cell.setCellStyle(columnStyles[col])
		}
		cell.setCellValue(value)
	}

	private void setNumericCell(Row row, int col, double value) {
		Cell cell = row.getCell(col) ?: row.createCell(col)
		if (columnStyles[col] != null) {
			cell.setCellStyle(columnStyles[col])
		}
		cell.setCellValue(value)
	}

	private void setHyperlinkCell(Row row, int col, String relPath) {
		Cell cell = row.getCell(col) ?: row.createCell(col)
		if (columnStyles[col] != null) {
			cell.setCellStyle(columnStyles[col])
		}
		cell.setCellFormula("HYPERLINK(\"${relPath}\",\"View Image\")")
	}

	private void setFailRankFormula(Row row, int excelRowNumber) {
		Cell cell = row.getCell(COL_J) ?: row.createCell(COL_J)
		if (columnStyles[COL_J] != null) {
			cell.setCellStyle(columnStyles[COL_J])
		}
		cell.setCellFormula("IF(F${excelRowNumber}=\"FAILED\",COUNTIF(\$F\$2:F${excelRowNumber},\"FAILED\"),\"\")")
	}

	/* ================= Formula evaluation (plan §11) ================= */

	/** Evaluates every formula once and bakes cached values, for headless consumers that trust
	 *  cached values instead of recalculating on open. Falls back to per-cell evaluation (skipping
	 *  individual failures) if the bulk evaluateAll() throws. */
	void evaluateAllFormulas() {
		FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator()
		try {
			evaluator.evaluateAll()
		} catch (Throwable t) {
			ExcelReportLog.warn("ExcelReporter: evaluateAll() failed (${t}); falling back to per-cell evaluation")
			[detailsSheet, summarySheet].each { sheet ->
				for (Row row : sheet) {
					for (Cell cell : row) {
						if (cell.getCellType() == CellType.FORMULA) {
							try {
								evaluator.evaluateFormulaCell(cell)
							} catch (Throwable cellEx) {
								// skip this cell only - one bad formula must not block the rest
							}
						}
					}
				}
			}
		}
	}

	/* ================= Save (plan R3) ================= */

	/** Atomic write-through save: write to a .tmp file, then move it onto the live file.
	 *  Retries once on a Windows file lock, then falls back to Report_N.xlsx. Never throws -
	 *  callers rely on the report staying best-effort (R2). */
	void save() {
		workbook.setForceFormulaRecalculation(true)
		File tmp = new File(targetFile.parentFile, targetFile.name + '.tmp')
		FileOutputStream fos = new FileOutputStream(tmp)
		try {
			workbook.write(fos)
		} finally {
			fos.close()
		}
		moveIntoPlace(tmp)
	}

	private void moveIntoPlace(File tmp) {
		try {
			moveAtomically(tmp, targetFile)
			return
		} catch (IOException firstFailure) {
			ExcelReportLog.warn("ExcelReporter: ${targetFile.name} appears locked, retrying in 200ms (${firstFailure})")
			sleep(200)
			try {
				moveAtomically(tmp, targetFile)
				return
			} catch (IOException secondFailure) {
				File fallback = nextAvailableFallbackFile()
				ExcelReportLog.warn("ExcelReporter: ${targetFile.name} still locked; writing to ${fallback.name} instead (${secondFailure})")
				moveAtomically(tmp, fallback)
				targetFile = fallback
			}
		}
	}

	private static void moveAtomically(File tmp, File dest) {
		try {
			Files.move(tmp.toPath(), dest.toPath(), StandardCopyOption.REPLACE_EXISTING, StandardCopyOption.ATOMIC_MOVE)
		} catch (AtomicMoveNotSupportedException e) {
			Files.move(tmp.toPath(), dest.toPath(), StandardCopyOption.REPLACE_EXISTING)
		}
	}

	private File nextAvailableFallbackFile() {
		File dir = targetFile.parentFile
		int n = 1
		// Katalon's Groovy builder uses the old (antlr2) parser, which has no do/while - keep this a
		// plain while loop or the whole class silently compiles to an error stub.
		File candidate = new File(dir, "Report_${n}.xlsx")
		while (candidate.exists()) {
			n++
			candidate = new File(dir, "Report_${n}.xlsx")
		}
		return candidate
	}

	File getTargetFile() {
		return targetFile
	}

	void close() {
		workbook?.close()
	}
}
