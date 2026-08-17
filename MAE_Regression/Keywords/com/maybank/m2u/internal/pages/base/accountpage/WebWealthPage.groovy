package com.maybank.m2u.internal.pages.base.accountpage

import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.openqa.selenium.By
import org.openqa.selenium.WebElement

import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testobject.TestObject
import com.maybank.common.WebUIKeyword
import com.maybank.m2u.internal.pages.base.HTMLTag
import com.maybank.m2u.internal.pages.base.WebBasePage
import com.maybank.m2u.internal.pages.base.WebCommonPage
import com.maybank.m2u.internal.pages.base.WebHomePage
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable

public class WebWealthPage extends WebBasePage{

	WebCommonPage webCommonPage = getPage(WebCommonPage)
	WebHomePage webHomePage = getPage(WebHomePage)

	static final String BUY_NOW = 'Buy Now'
	static final String SELL_NOW = 'Sell Now'
	static final String CALCULATE = 'CALCULATE'
	static final String TRANSFER_MIGA = 'Transfer MIGA-i'
	static final String CREATE_ORDER = 'CREATE ORDER'
	static final String YES_CANCEL_IT = 'YES, CANCEL IT'
	static final String TRANSFER_AND_CLOSE_ACCOUNT = 'TRANSFER AND CLOSE ACCOUNT'
	final static String TOTAL_GOLD_AMOUNT = "Total Gold Amount"
	final static String GRAND_TOTAL = "Grand Total"
	final static String SHOW_MORE = "SHOW MORE"
	final static String ALL_ASNB_ACCOUNTS = "All ASNB Accounts"
	final static String TRANSFER = "TRANSFER"
	final static String ASNB_ACCOUNTS = "ASNB Accounts"
	final static String PAY_AND_TRANSFER = 'PAY & TRANSFER'
	final static String BUY_GOLD = "Buy Gold"
	final static String SELL_GOLD = "Sell Gold"

	static final String SECOND_INPUT = 'secondInput'
	static final String POPUP_DROPDOWN = 'DynoPopUpDropDown---dynamicPopupDropDown'
	String classHeaderTotalAmount = "DynamicFields---DefaultLabelColorGold"
	String classHeaderTotalAmountASNB = "WealthDetailsCardNew---valueAlignRight"
	String classHeaderTotalAmountTabungHaji = "DynamicFields---fullLabel"
	String classHeaderMarketValue = "WealthDetailsCardNew---currencySpace"
	String classLabelSmallBoldCustom = "DynamicFields---DefaultLabelSmallBoldCustom"
	String classAsnbAccountContainer = "AsnbAccountContainer"
	String classPayNavigationTypeName = "PayNavigation---typeName"
	String classValueAlignRight = "WealthDetailsCardNew---valueAlignRight"
	String classContextMenuPopup = "ContextMenu---popup"
	String classAccountTable = "AccountTable---tableBody"

	static final String GOLD_QUANTITY_ID = 'goldQuantity'

	TestObject btnRemoveFutureOrder(String text, def index = 1) {
		return findTestObject('Object Repository/Website/M2U/Browsers/Accounts Page/Wealth Page/btnRemoveFO',  ['text': text, 'index': index.toString()])
	}
	TestObject tableASNBAccount() {
		return findTestObject('Object Repository/Website/M2U/Browsers/Accounts Page/Wealth Page/tableASNBAccount')
	}
	
	TestObject tableItem(String text) {
		return findTestObject('Object Repository/Website/M2U/Browsers/Accounts Page/Wealth Page/tableItem', ['text': text])
	}

	TestObject tableTabungHajiAccount() {
		return findTestObject('Object Repository/Website/M2U/Browsers/Accounts Page/Wealth Page/tableTabungHajiAccount')
	}

	TestObject tableCardsDashBoard() {
		return findTestObject('Object Repository/Website/M2U/Browsers/Accounts Page/tableCardsDashBoard')
	}
	
	TestObject txtFundNameASNB() {
		return findTestObject('Object Repository/Website/M2U/Browsers/Accounts Page/Wealth Page/txtFundNameASNB')
	}

	void inputGram(String gram, FailureHandling flowControl = defaultFailureHandling) {
		WebUIKeyword.setText(webCommonPage.lblContainClass(SECOND_INPUT), gram, flowControl)
	}

	void clickIteminThreeDot(String text, FailureHandling flowControl = defaultFailureHandling) {
		webCommonPage.waitForElementPresent(webCommonPage.lblContainClass(POPUP_DROPDOWN), GlobalVariable.G_timeoutMedium, FailureHandling.OPTIONAL)

		WebUIKeyword.click(webCommonPage.lblContainClass(POPUP_DROPDOWN), flowControl)
		WebUIKeyword.click(webCommonPage.lblDynamic(text), flowControl)

		webCommonPage.waitLoading(GlobalVariable.G_timeoutLong * 3)
	}

	void inputGoldQuantityTransfer(String goldGram, FailureHandling flowControl = defaultFailureHandling) {
		WebUIKeyword.setText(webCommonPage.getTestObjectById(GOLD_QUANTITY_ID), goldGram, flowControl)
	}

	/**
	 * inputData
	 */
	void inputData(Map inputData, Map inputDropdownData = [:], FailureHandling flowControl = defaultFailureHandling) {
		'Select dropdown data'
		inputDropdownData.each { key, value ->
			webCommonPage.selectDropdown(key, value, flowControl, true)
			WebUIKeyword.delay(GlobalVariable.G_timeoutMiniShort)
		}
		'Input data'
		webCommonPage.inputTextMap(inputData, flowControl)
		'Take ScreenShort'
		WebUIKeyword.takeScreenshot()
	}

	void createFutureOrder(String percent, String goldGram, Map transferTo, FailureHandling flowControl = defaultFailureHandling) {
		'Input percent'
		WebUIKeyword.setText(webCommonPage.lblContainClass(SECOND_INPUT), percent, flowControl)
		'Input gram'
		WebUIKeyword.setText(webCommonPage.getTestObjectById(GOLD_QUANTITY_ID), goldGram, flowControl)
		'Click CALCULATE'
		WebUIKeyword.click(webCommonPage.lblDynamic(CALCULATE), flowControl)
		webCommonPage.waitLoading()
		WebUIKeyword.delay(GlobalVariable.G_timeoutMiniShort)
		'Transfer To'
		webCommonPage.inputData([:], transferTo)
		'Click Create Order'
		webCommonPage.clickTestObjectWithWait(webCommonPage.lblNormalizeSpace(CREATE_ORDER, 1, HTMLTag.button))
		'Wait loading'
		webCommonPage.waitLoading()
	}
	
	void createGoal(FailureHandling flowControl = defaultFailureHandling) {
		'Click CALCULATE'
		WebUIKeyword.click(webCommonPage.lblDynamic(CALCULATE), flowControl)
		webCommonPage.waitLoading()
		WebUIKeyword.delay(GlobalVariable.G_timeoutMiniShort)
		'Transfer To'
		'Click Create Order'
		webCommonPage.clickTestObjectWithWait(webCommonPage.lblNormalizeSpace(CREATE_ORDER, 1, HTMLTag.button))
		'Wait loading'
		webCommonPage.waitLoading()
	}
	
	boolean removeFutureOrder(String FOTitle, List UIData=[], FailureHandling flowControl = defaultFailureHandling) {
		if(!WebUIKeyword.waitForElementPresent(this.btnRemoveFutureOrder(FOTitle), GlobalVariable.G_timeoutMedium, FailureHandling.OPTIONAL)) {
			return false
		}
		'Click X icon'
		WebUIKeyword.scrollToTestObject(this.btnRemoveFutureOrder(FOTitle))
		WebUIKeyword.scrollToTestObject(this.btnRemoveFutureOrder(FOTitle))
		WebUIKeyword.click(this.btnRemoveFutureOrder(FOTitle), flowControl)
		WebUIKeyword.delay(GlobalVariable.G_timeoutMiniShort)

		webCommonPage.verifyElementListVisible(UIData, flowControl)
		'Yes'
		webCommonPage.click(webCommonPage.lblNormalizeSpace(YES_CANCEL_IT, 1, HTMLTag.empty), flowControl)

		return true
	}

	void closeMIGAAccount(List verifyUIData, FailureHandling flowControl = defaultFailureHandling) {
		'Verify UI'
		webCommonPage.verifyElementListVisible(verifyUIData)
		'Click '
		webCommonPage.click(webCommonPage.lblNormalizeSpace(TRANSFER_AND_CLOSE_ACCOUNT, 1, HTMLTag.empty), flowControl)

		'Close popup'
		webCommonPage.closeModal()
	}

	/**
	 * List out MIGAi Header Values
	 * @return MIGAi Header Values: Total Gold Amount(g), Avg Purchase Price(RM/g), Total Purchased(RM), Purchased Price Percent(%), Current Gold Value(RM)
	 */
	def getMIGAiHeaderValues(int timeout = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		String totalAmount = getText(webCommonPage.lblContainClass(classHeaderTotalAmount), flowControl)
		String avgPurchasePrice = getText(webCommonPage.lblContainClass(classLabelSmallBoldCustom, 1), flowControl)
		String totalPurchased = getText(webCommonPage.lblContainClass(classLabelSmallBoldCustom, 2), flowControl)
		String purchasePecentage = getText(webCommonPage.lblContainClass(classLabelSmallBoldCustom, 3), flowControl)
		String currentGoldValue = getText(webCommonPage.lblContainClass(classLabelSmallBoldCustom, 4), flowControl)

		HashMap<String, String> headerValuesHashMap = new HashMap<String, String>(){ {
						put(TOTAL_GOLD_AMOUNT, totalAmount)
						put(AVG_PURCHASE_PRICE, avgPurchasePrice)
						put(TOTAL_PURCHASED, totalPurchased)
						put(PURCHASED_PRICE_PECENT, purchasePecentage)
						put(CURRENT_GOLD_VALUE, currentGoldValue)
					}
				}
		return headerValuesHashMap
	}
	/**
	 * Verify Card DashBoard
	 */
	void verifyCardDashBoard(FailureHandling flowControl = defaultFailureHandling) {
		int maxClicks = 5
		int clickCount = 0

		while (clickCount < maxClicks && webCommonPage.waitForElementPresent(webCommonPage.lblDynamic(SHOW_MORE),GlobalVariable.G_timeoutShort)) {
			scrollToTestObject(webCommonPage.lblDynamic(SHOW_MORE))
			scrollToTestObject(webCommonPage.lblDynamic(SHOW_MORE))
			click(webCommonPage.lblDynamic(SHOW_MORE))
			clickCount++
			WebUI.delay(GlobalVariable.G_timeoutShort) // Optional: wait for items to load after clicking
		}
		List<Map<String, String>> result = getListCards(tableCardsDashBoard(), GlobalVariable.G_timeoutMedium)
		println(result)
		if (result.isEmpty()) {
			handleError('❌ Not found any information', FailureHandling.CONTINUE_ON_FAILURE)
		}
	}
	/**
	 * Verify ASNB DashBoard
	 */
	void verifyASNBTransaction(FailureHandling flowControl = defaultFailureHandling) {
		verifyElementVisible(webCommonPage.lblDynamic(GRAND_TOTAL), flowControl)

		String totalAmount = getText(webCommonPage.lblContainClass(classHeaderTotalAmountASNB), FailureHandling.OPTIONAL)

		// Check if totalAmount contains 'RM'
		if (totalAmount != null && !totalAmount.trim().isEmpty()) {
			if (!totalAmount.contains('RM')) {
				handleError("❌ Total Amount does not contain 'RM': " + totalAmount, FailureHandling.CONTINUE_ON_FAILURE)
			} else {
				WebUI.comment("✅ Total Amount is valid: " + totalAmount)
			}
		}

		List<Map<String, String>> result = getListASNB(tableASNBAccount(), GlobalVariable.G_timeoutMedium)
		println(result)
		if (result.isEmpty()) {
			handleError('❌ Not found any information', FailureHandling.CONTINUE_ON_FAILURE)
		}

		// Sum all "AMOUNT (RM)" values from result
		BigDecimal sum = sumAmountRM(result)

		// Extract numeric value from totalAmount for comparison
		BigDecimal totalAmountNum = extractNumericValue(totalAmount)

		// Compare the sum with totalAmount
		if (sum.compareTo(totalAmountNum) == 0) {
			WebUI.comment("✅ The sum of all AMOUNT (RM) matches the Total Amount: RM ${sum}")
		} else {
			handleError("❌ The sum of all AMOUNT (RM) (${sum}) does not match the Total Amount (${totalAmountNum})", FailureHandling.CONTINUE_ON_FAILURE)
		}
	}

	/**
	 * Verify Tabung Haji Dashboard
	 */
	void verifyTabungHajiTransaction(FailureHandling flowControl = defaultFailureHandling) {
		verifyElementVisible(webCommonPage.lblDynamic(GRAND_TOTAL), flowControl)

		String totalAmount = getText(webCommonPage.lblContainClass(classHeaderTotalAmountTabungHaji), FailureHandling.OPTIONAL)

		// Check if totalAmount contains 'RM'
		if (totalAmount != null && !totalAmount.trim().isEmpty()) {
			if (!totalAmount.contains('RM')) {
				handleError("❌ Total Amount does not contain 'RM': " + totalAmount, FailureHandling.CONTINUE_ON_FAILURE)
			} else {
				WebUI.comment("✅ Total Amount is valid: " + totalAmount)
			}
		}

		List<Map<String, String>> result = getListTabungHaji(tableTabungHajiAccount(), GlobalVariable.G_timeoutMedium)
		println(result)
		if (result.isEmpty()) {
			handleError('❌ Not found any information', FailureHandling.CONTINUE_ON_FAILURE)
		}
	}

	/**
	 * Verify Unit Trust Dashboard
	 */
	void verifyUTTransaction(List<String> verifyData, FailureHandling flowControl = defaultFailureHandling) {
		webCommonPage.verifyElementListVisible(verifyData)

		String marketValue = getText(webCommonPage.lblContainClass(classHeaderMarketValue), flowControl)
		String bookValue = getText(webCommonPage.lblContainClass(classHeaderTotalAmountASNB, 2), flowControl)
		String gainAndLossValue = getText(webCommonPage.lblContainClass(classHeaderTotalAmountASNB, 3), flowControl)

		Map<String, String> amountChecks = [
			"Market Value"     : marketValue,
			"Book Value"       : bookValue,
			"Gain/Loss Value"  : gainAndLossValue
		]

		amountChecks.each { label, value ->
			if (value != null && !value.trim().isEmpty()) {
				if (!value.contains("RM")) {
					handleError("❌ ${label} does not contain 'RM': ${value}", FailureHandling.CONTINUE_ON_FAILURE)
				} else {
					WebUI.comment("✅ ${label} is valid: ${value}")
				}
			} else {
				handleError("❌ ${label} is empty or null", FailureHandling.CONTINUE_ON_FAILURE)
			}
		}
	}

	List<Map<String, String>> getListASNB(TestObject table = this.tableASNBAccount(), int timeout = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = FailureHandling.STOP_ON_FAILURE) {
		// Initialize the result list
		List<Map<String, String>> result = []

		// Get all row wrappers from the table
		List<WebElement> rows = WebUI.findWebElements(table, timeout, flowControl)

		for (WebElement row : rows) {
			// Find all divs with class 'clscol' inside each row
			List<WebElement> cells = row.findElements(By.className('clscol'))

			if (cells.size() >= 4) {
				String membershipName = cells.get(0).findElement(By.tagName('a')).getText()
				String fundName = cells.get(1).findElement(By.tagName('span')).getText()
				String accountNumber = cells.get(2).findElement(By.tagName('span')).getText()
				String amountRM = cells.get(3).findElement(By.tagName('span')).getText()

				// Add data to the list as a map with correct headers
				Map<String, String> rowData = [
					"MEMBERSHIP NAME" : membershipName,
					"FUND NAME"   : fundName,
					"ACCOUNT NUMBER" : accountNumber,
					"AMOUNT (RM)"    : amountRM
				]
				result.add(rowData)
			}
		}
		println(result)
		return result
	}

	List<Map<String, String>> getListCards(TestObject table, int timeout, FailureHandling flowControl = FailureHandling.STOP_ON_FAILURE) {
		List<Map<String, String>> result = []

		// Get all card containers
		List<WebElement> cards = WebUI.findWebElements(table, timeout, flowControl)

		for (WebElement card : cards) {
			try {
				// Skip card if it contains unauthorized message
				boolean isUnauthorized = !card.findElements(By.xpath(".//div[contains(@class, 'unAuthorized')]")).isEmpty()
				if (isUnauthorized) {
					continue
				}

				// Get card name using partial class match
				String name = card.findElement(By.xpath(".//span[contains(@class, 'accountName')]")).getText().trim()

				// Get card number (first non-empty accountNumber span)
				List<WebElement> numberElements = card.findElements(By.xpath(".//span[contains(@class, 'accountNumber')]"))
				String number = numberElements.find { it.getText().trim() }?.getText()?.trim() ?: ""

				// Get full balance text block
				WebElement balanceContainer = card.findElement(By.xpath(".//div[contains(@class, 'Card---balances')]"))
				String fullBalanceText = balanceContainer.getText().trim()

				// Extract amount part (everything after "RM"), and detect if it starts with -
				boolean isNegative = fullBalanceText.contains("-RM") || fullBalanceText.startsWith("-")
				String valueOnly = fullBalanceText.replaceAll("[^0-9.,]", "")
				String finalBalance = (isNegative ? "-RM " : "RM ") + valueOnly

				// Add to result list
				result.add([
					"Card Name"   : name,
					"Card Number" : number,
					"Balance"     : finalBalance
				])
			} catch (Exception e) {
				// Skip any card that causes an exception
				continue
			}
		}

		return result
	}





	List<Map<String, String>> getListTabungHaji(TestObject table, int timeout, FailureHandling flowControl = FailureHandling.STOP_ON_FAILURE) {
		// Initialize the result list
		List<Map<String, String>> result = []

		// Get all <tr> rows from the table
		List<WebElement> rows = WebUI.findWebElements(table, timeout, flowControl)

		for (WebElement row : rows) {
			// Get all <td> elements inside the row
			List<WebElement> cells = row.findElements(By.tagName('td'))

			if (cells.size() >= 4) {
				String accountHolder = cells.get(0).findElement(By.tagName('span')).getText().trim()
				String accountType   = cells.get(1).findElement(By.tagName('p')).getText().trim()
				String accountNumber = cells.get(2).findElement(By.tagName('p')).getText().trim()
				String amountRM      = cells.get(3).findElement(By.tagName('p')).getText().trim()

				// Add data to the list as a map
				Map<String, String> rowData = [
					"ACCOUNT HOLDER" : accountHolder,
					"ACCOUNT TYPE"   : accountType,
					"ACCOUNT NUMBER" : accountNumber,
					"AMOUNT (RM)"    : amountRM
				]
				result.add(rowData)
			}
		}

		return result
	}


	/**
	 * Compare if MIGAi Header Values (BTS) same to BAU
	 */
	void compareMIGAiHeaderValues(Map<String, String> fistData, Map<String, String> secondData) {
		info("Compare ${TOTAL_GOLD_AMOUNT}")
		if(!(fistData.equals(secondData))) {
			info("Data ${TOTAL_GOLD_AMOUNT} is not equals - fistData: ${fistData} - secondData: ${secondData}")
		}
		else info ("Data is The transaction lists are identical.")
	}

	/**
	* Sum all "AMOUNT (RM)" values from List<Map<String, String>>
	*/
	BigDecimal sumAmountRM(List<Map<String, String>> listMapString) {	
		BigDecimal sum = 0
		listMapString.each { row ->
			String amountStr = row["AMOUNT (RM)"]?.replaceAll("[^\\d.,-]", "")?.replace(",", "")
			if (amountStr) {
				try {
					sum += new BigDecimal(amountStr)
				} catch (Exception e) {
					WebUI.comment("⚠️ Unable to parse amount: ${amountStr}")
				}
			}
		}
		return sum
	}

	/**
	 * Extract numeric value from string for comparison
	 */
	BigDecimal extractNumericValue(String totalAmount) {
		String totalAmountNumStr = totalAmount?.replaceAll("[^\\d.,-]", "")?.replace(",", "")
		BigDecimal totalAmountNum = 0
		if (totalAmountNumStr) {
			try {
				totalAmountNum = new BigDecimal(totalAmountNumStr)
			} catch (Exception e) {
				WebUI.comment("⚠️ Unable to parse totalAmount: ${totalAmountNumStr}")
			}
		}
		return totalAmountNum
	}	

	/**
	 * Verify ASNB table data
	 * Does not verify field FUND NAME because it contains free text
	 */
	void verifyASNBTableData(FailureHandling flowControl = defaultFailureHandling) {
		// Get ASNB table data for verification
		List<Map<String, String>> tableData = getListASNB(tableASNBAccount(), GlobalVariable.G_timeoutMedium, flowControl)
	    // If table is empty, handle error and exit method
		if (tableData.isEmpty()) {
			handleError('❌ Not found any information in ASNB table', flowControl)
		}
		tableData.each { row ->
			String membershipName = row["MEMBERSHIP NAME"]
			String accountNumber = row["ACCOUNT NUMBER"]
			String amountRM = row["AMOUNT (RM)"]
			
			// Check if MEMBERSHIP NAME contains masked value 
			if (!membershipName.contains("XXXXXXXXXX")) {
				handleError("❌ ACCOUNT HOLDER does not contain 'XXXXXXXXXX': ${membershipName}", flowControl)
			} else {
				WebUI.comment("✅ ACCOUNT HOLDER is valid: ${membershipName}")
			}
			// Check if ACCOUNT NUMBER contains any alphabetic characters
			if (accountNumber == null || accountNumber =~ /[A-Za-z]/) {
				handleError("❌ ACCOUNT NUMBER contains alphabetic characters: ${accountNumber}", flowControl)
			} else {
				WebUI.comment("✅ ACCOUNT NUMBER is valid: ${accountNumber}")
			}
			// Check if AMOUNT (RM) contains 'RM'
			if (amountRM == null || !amountRM.contains("RM")) {
				handleError("❌ AMOUNT (RM) does not contain 'RM': ${amountRM}", flowControl)
			} else {
				WebUI.comment("✅ AMOUNT (RM) is valid: ${amountRM}")
			}
		}
	}

		/**
		 * Verify ASNB transaction details by comparing amounts in the table and details page.
		 */
		void verifyASNBTransactionDetails(FailureHandling flowControl = defaultFailureHandling) {
	    
		// Get ASNB table data for verification
		List<Map<String, String>> tableData = getListASNB(tableASNBAccount(), GlobalVariable.G_timeoutMedium, flowControl)
	    // If table is empty, handle error and exit method
		if (tableData.isEmpty()) {
	        handleError('❌ Not found any information in ASNB table', flowControl)
	        return
	    }
		
		//  Determine the maximum number of iterations (up to 5 or the size of the table data)
	    int maxIterations = Math.min(5, tableData.size())

		// Loop through each ASNB account row up to maxIterations
	    for (int i = 0; i < maxIterations; i++) {
	        Map<String, String> row = tableData[i]
	        String amount = row["AMOUNT (RM)"]
			String fundName = row["FUND NAME"]
	        WebUIKeyword.scrollToTestObject(tableASNBAccount())
	
	        // RE-LOCATE rows each time to avoid stale element
	        List<WebElement> rows = WebUI.findWebElements(tableASNBAccount(), GlobalVariable.G_timeoutMedium, flowControl)
	        List<WebElement> cells = rows[i].findElements(By.className('clscol'))
	        if (cells.size() >= 1) {
	            WebElement membershipNameElement = cells.get(0).findElement(By.tagName('a'))
	            membershipNameElement.click()
	        }
	        webCommonPage.waitLoading()
	
	        // Compare the details on the account details page
	        String detailAmount = getText(webCommonPage.lblContainClass(classValueAlignRight), flowControl)
			String detailFundName = getText(this.txtFundNameASNB(), flowControl)
			
			// Parse amounts into numeric values for robust comparison
			BigDecimal expectedAmount = extractNumericValue(amount)
			BigDecimal detailAmountValue = extractNumericValue(detailAmount)
			
			// Verify that the AMOUNTS (RM) and FUND NAME field on account details and account listing page are matching
			if (detailAmountValue.compareTo(expectedAmount) != 0) {
				handleError("❌ AMOUNT (RM) mismatch: expected ${amount}, found ${detailAmount}", flowControl)
			} else {
				WebUI.comment("✅ AMOUNT (RM) field is match")
			}
			
			if(!fundName?.trim()?.equalsIgnoreCase(detailFundName?.trim())) {
				handleError("❌ FUND NAME mismatch: expected ${fundName}, found ${detailFundName}", flowControl)
			} else {
				WebUI.comment("✅ FUND NAME field is match")
			}
	
	        if (i < maxIterations - 1) {
	            // Click All ASNB Accounts button to go back
	            WebUIKeyword.click(webCommonPage.lblContainClassAndContainText(classAsnbAccountContainer, ALL_ASNB_ACCOUNTS), flowControl)
	            webCommonPage.waitLoading()
	        } else {
	            // Click Make transfer button to go to the Transfer page
	            WebUIKeyword.click(webCommonPage.imgShowMore())
	            WebUIKeyword.click(webCommonPage.lblContainClass(classContextMenuPopup))
	            webCommonPage.waitLoading()
	        }
	    }
		    // Verify UI of Transfer tab
			if (WebUIKeyword.waitForElementPresent(webHomePage.imgClosePopup(), GlobalVariable.G_timeoutShort, FailureHandling.OPTIONAL)) {
				WebUIKeyword.click(webHomePage.imgClosePopup(), FailureHandling.OPTIONAL)
			}
			verifyElementVisible(webHomePage.lnkNavigationBar(PAY_AND_TRANSFER))
		    verifyElementVisible(webCommonPage.lblContainClassAndText(classPayNavigationTypeName, TRANSFER))
		}
		
		void verifyBuyAndSellGold(Boolean isBuy = true, String amount, FailureHandling flowControl = defaultFailureHandling) {
			// Get the result list of historical data
			List<Map<String, String>> result = getListGoldAndSilverHistory(tableItem(classAccountTable), GlobalVariable.G_timeoutMedium)
		
			if (!result.isEmpty()) {
				// Get the first item from the result list
				Map<String, String> firstItem = result.get(0)
		
				// Set the transaction type based on the isBuy flag
				String transactionType = isBuy ? "Buy" : "Sell"
		
				// Ensure the date is today's date
				String currentDate = new Date().format("dd/MM/yy") // Format: 13/06/25 (example)
				String date = currentDate
		
				// Set quantity to the value of amount
				String quantity = amount
		
				// Verify if the transaction data matches the expected values
				if (firstItem["TRANSACTION TYPE"] == transactionType && firstItem["DATE"] == date && firstItem["QUANTITY"] == quantity) {
					WebUI.comment("Transaction is valid: $transactionType, Date: $date, Quantity: $quantity")
				} else {
					WebUI.comment("Transaction data does not match. Expected: $transactionType, Date: $date, Quantity: $quantity")
				}
			} else {
				WebUI.comment("No transaction data found in the table.")
			}
		}
		
		
		List<Map<String, String>> getListGoldAndSilverHistory(TestObject table, int timeout, FailureHandling flowControl = FailureHandling.STOP_ON_FAILURE) {
			// Initialize the result list
			List<Map<String, String>> result = []
		
			// Get all row wrappers from the table
			List<WebElement> rows = WebUI.findWebElements(table, timeout, flowControl)
		
			for (WebElement row : rows) {
				// Find all td elements with class 'clscol' inside each row
				List<WebElement> cells = row.findElements(By.className('clscol'))
		
				if (cells.size() >= 5) {
					// Extract values from the cells
					String date = cells.get(0).findElement(By.tagName('span')).getText() // Date
					String accountType = cells.get(1).findElement(By.tagName('span')).getText() // Account Type (e.g. GOLD)
					String transactionType = cells.get(2).findElement(By.tagName('span')).getText() // Buy/Sell
					String quantity = cells.get(3).findElement(By.tagName('span')).getText() // Quantity
					String amountRM = cells.get(4).findElement(By.tagName('span')).getText() // Amount in RM
		
					// Add data to the list as a map with correct headers
					Map<String, String> rowData = [
						"DATE"            : date,
						"ACCOUNT TYPE"    : accountType,
						"TRANSACTION TYPE": transactionType,
						"QUANTITY"        : quantity,
						"AMOUNT (RM)"     : amountRM
					]
					result.add(rowData)
				}
			}
		
			return result
		}
		
		void buyAndSellGold(Boolean isBuy = true , Map inputData, Map inputDropdownData = [:],  FailureHandling flowControl = defaultFailureHandling) {
			WebUIKeyword.delay(GlobalVariable.G_timeoutMiniShort)
			WebUIKeyword.clickWithoutScrollW3C(webCommonPage.imgShowMore(), flowControl)
			if (isBuy) {
				WebUIKeyword.clickWithoutScrollW3C(webCommonPage.lblDynamic(BUY_GOLD), flowControl)
			}else {
				WebUIKeyword.clickWithoutScrollW3C(webCommonPage.lblDynamic(SELL_GOLD), flowControl)
			}
			webCommonPage.inputData(inputData, inputDropdownData)
			WebUIKeyword.clickWithoutScrollW3C(webCommonPage.imgTransactionWhite(), flowControl)
			
		}
}
