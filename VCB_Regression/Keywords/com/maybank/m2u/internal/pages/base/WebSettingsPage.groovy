package com.maybank.m2u.internal.pages.base

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import org.openqa.selenium.By
import org.openqa.selenium.WebElement
import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import com.maybank.common.WebUIKeyword
import internal.GlobalVariable

public class WebSettingsPage extends WebBasePage {

	WebCommonPage webCommonPage = getPage(WebCommonPage)
	WebHomePage webHomePage = WebBasePage.getPage(WebHomePage)

	static final String NAVIGATION_CLASS = 'SettingNavigation---typeName'
	static final String LEFT_MENU_PANEL_CLASS = 'LeftMenuPanel---listGroupItem'

	TestObject tableASNBAccounts() {
		return findTestObject('Object Repository/Website/M2U/Browsers/Settings Page/tableASNBAccounts')
	}

	void navigateSettings(String navItem, String tabName, FailureHandling flowControl = defaultFailureHandling) {
		WebUIKeyword.click(webCommonPage.lblContainClassAndContainText(NAVIGATION_CLASS, navItem), flowControl)
		WebUIKeyword.delay(GlobalVariable.G_timeoutShort)
		WebUIKeyword.click(webCommonPage.lblContainClassAndText(LEFT_MENU_PANEL_CLASS, tabName), flowControl)
		webCommonPage.waitLoading()
	}
	
	List<Map<String, String>> getListASNB() {
		List<Map<String, String>> asnbList = []
		List<WebElement> rows = WebUI.findWebElements(this.tableASNBAccounts(), GlobalVariable.G_timeoutMedium, FailureHandling.STOP_ON_FAILURE)
		rows.each { row ->
			List<WebElement> fields = row.findElements(By.tagName('p'))
			if (fields.size() >= 3) {
				Map<String, String> asnbData = [
					'MEMBERSHIP NAME' : fields[0].text,
					'FUND NAME'   : fields[2].text,
					'ACCOUNT NUMBER' : fields[1].text
				]
				asnbList << asnbData
			}
		}
		println(asnbList)
		if (asnbList.isEmpty()) {
			handleError('❌ Not found any information', FailureHandling.CONTINUE_ON_FAILURE)
		}
		return asnbList
	}

	/**
	* Compare two ASNB account lists and report differences.
	*/
	void compareASNBAccountLists(List<Map<String, String>> listASNBWealth, List<Map<String, String>> listLinkedASNB, FailureHandling flowControl = defaultFailureHandling) {
		if (listASNBWealth == null) {
			handleError("❌ listASNBWealth is null", flowControl)
			return
		}
		if (listLinkedASNB == null) {
			handleError("❌ listLinkedASNB is null", flowControl)
			return
		}
		if (listASNBWealth.size() != listLinkedASNB.size()) {
			handleError("❌ List size mismatch: Wealth(${listASNBWealth.size()}) vs Linked(${listLinkedASNB.size()})", flowControl)
		}

		// Compare each account by ACCOUNT NUMBER
		
		listASNBWealth.eachWithIndex { wealthRow, index ->
			Map<String, String> linkedRow = listLinkedASNB[index]
			if (linkedRow == null) {
				handleError("❌ Data in Wealth list not found in Linked list", flowControl)
			} else {
				// Compare all fields except "AMOUNT (RM)"
				wealthRow.each { key, value ->
					if (key == "AMOUNT (RM)") return true // Skip comparison for the "AMOUNT (RM)" field because listLinkedASNB does not have this field
					String linkedValue = linkedRow[key]
					if (value != linkedValue) {
						handleError("❌ Mismatch data - Field '${key}': Wealth='${value}', Linked='${linkedValue}'", flowControl)
					}
				}
			}
		}
		WebUI.comment("✅ listASNBWealth and listLinkedASNB are identical")
	}
}
