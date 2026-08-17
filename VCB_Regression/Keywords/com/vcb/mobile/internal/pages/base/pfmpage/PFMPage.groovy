package com.vcb.mobile.internal.pages.base.pfmpage

import static com.vcb.common.MobileDriver.ScrollDirectionEnum.DOWN
import static com.vcb.common.MobileDriver.ScrollDirectionEnum.RIGHT

import java.time.Duration

import org.openqa.selenium.By
import org.openqa.selenium.WebElement

import com.kms.katalon.core.configuration.RunConfiguration
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.testobject.TestObject
import com.vcb.assertions.BaseAssert
import com.vcb.common.MobileDriver
import com.vcb.mobile.internal.pages.base.BasePage
import com.vcb.mobile.internal.pages.base.CommonPage
import com.vcb.mobile.internal.pages.base.zakat.ZakatPage
import com.vcb.utils.StringUtils

import internal.GlobalVariable

public abstract class PFMPage extends BasePage{

	static final String ADD_EXPENSE = 'Add Expense'
	static final String PAYMENT = 'Payment'
	static final String SET_CATEGORY = 'Set Category'
	static final String ADD_CATERGORY = 'Add Category'
	static final String SELECT_ICON = 'Select Icon'
	static final String	SAVE = 'Save'
	static final String FILTER = 'Filter'
	static final String APPLY_FILTER = 'Apply Filters'
	static final String DELETE = 'Delete'
	static final String YES = 'Yes'
	static final EVENLY = 'Evenly'
	static final SPLIT_AMOUNT = 'Split amount'
	static final RM_DEFAULT = 'RM 0.00'
	static final SPLIT_BILL = 'Split Bill'
	static final DEFAULT_AMT = '60'
	static final SPLIT_NOW = 'Split Now'
	static final SHARE_BILL = 'Hi, looks like'

	abstract TestObject txtSiblingGroup(String label)
	abstract TestObject imgSiblingGroup(String text)
	abstract TestObject frmExpensesList()
	abstract TestObject txtExpenseCate(String text, int index)
	abstract TestObject lblPFMPaymentDropDown()

	CommonPage commonPage  = BasePage.getPage(CommonPage)

	List<List<String>> getExpensesList() {
		return commonPage.getAllItemsListView([
			"//android.widget.TextView[1]",
			"//android.widget.TextView[2]"
		], this.frmExpensesList())
	}
	
	void tapToAddExpense(FailureHandling flowControl = defaultFailureHandling) {
		MobileDriver.tap(commonPage.lblDynamic(ADD_EXPENSE), GlobalVariable.G_timeoutMedium, flowControl)
	}
	void enterAmount(String amount, int timeout = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		commonPage.waitLoading()
		MobileDriver.tap(commonPage.lblOtherRMNo(1), GlobalVariable.G_timeoutShort, flowControl)
		commonPage.enterNO(amount, true, Mobile.getText(commonPage.lblOtherRMNo(1), timeout), timeout, flowControl	)
	}
	void choosePayment(int timeout = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		MobileDriver.tap(commonPage.lblContainDynamic(PAYMENT), timeout, flowControl)
		commonPage.tapDoneBtn()
	}
	void selectCategory(Map<String, String> categoryMap, int timeout = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		String category = categoryMap.get("category")
		String subCategory = categoryMap.get("subCategory")
		MobileDriver.tap(commonPage.lblContainDynamic(SET_CATEGORY), timeout, flowControl)
		verifyElementNotVisible(commonPage.lblContainDynamic(ADD_CATERGORY), timeout)
		findAndTapCategory(category)
		findAndTapCategory(subCategory)
		commonPage.tapDoneBtn()
	}
	void findAndTapCategory(String category, boolean isSplitBill = false, int timeout = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		commonPage.waitLoading()
		if(!isSplitBill) {
			commonPage.swipeToObject(commonPage.lblDynamic(category))
			Mobile.delay(GlobalVariable.G_timeoutMiniShort)
			MobileDriver.tap(commonPage.lblDynamicTextLast(category), timeout, flowControl)
		}
		else {
//			MobileDriver.scrollToTestObject(this.frmExpensesList(), commonPage.lblDynamic(category), RIGHT, timeout, flowControl, false)
			MobileDriver.tap(commonPage.lblDynamic(category), timeout, flowControl)
		}
	}
	
	void verifyAddedExpense(Map<String, String> categoryMap, String paymentType, FailureHandling flowControl = defaultFailureHandling) {
		commonPage.scrollToEnd()
		
		// Verify category appears in the list
		Mobile.verifyElementVisible(commonPage.lblContainDynamic(categoryMap.get("category")), GlobalVariable.G_timeoutShort, flowControl)
		
		// Tap the first expense row (matches any row with '-RM' in content-desc)
		MobileDriver.tap(this.frmExpensesList(), GlobalVariable.G_timeoutShort, flowControl)
		Mobile.delay(GlobalVariable.G_timeoutShort)
		
		// Verify category on detail screen
		Mobile.verifyElementVisible(commonPage.lblAnyText(categoryMap.get("category")), GlobalVariable.G_timeoutShort, FailureHandling.STOP_ON_FAILURE)
		
		// Read and log amount
		String amountNo = Mobile.getText(commonPage.lblContainDynamic('-RM'), GlobalVariable.G_timeoutShort)
		Mobile.comment("Amount: " + amountNo)
		
		// Verify payment type
		Mobile.verifyElementVisible(commonPage.lblAnyText(paymentType.toUpperCase()), GlobalVariable.G_timeoutShort, flowControl)
		
		commonPage.backToHome()
	}
	
	void checkBalance( int timeout = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		'Get Balance'
		String balance = MobileDriver.getText(commonPage.lblOtherRMNo(1), GlobalVariable.G_timeoutLong, flowControl)
		'Extract balance to money format'
		String balanceNo = StringUtils.extractMoney(balance)
		'Verify balance format'
		verifyFormatMoney([balanceNo])
		
		List<List<String>> listExpense = getExpensesList()
		
		for(List item : listExpense) {
			Mobile.comment("Item 1: " + item.toString())
		}
		
		List<String> listMoney = StringUtils.extractValuesListView(listExpense, 1)
		for(String item : listMoney) {
			Mobile.comment("Item 2: " + item)
		}
		
		double total = 0.0;

		Mobile.comment("Total: " + total)
		String formatTotal = String.format("%.2f",total)
		
		Mobile.comment("Format total: " + formatTotal)
		Mobile.comment("Balance No: " + balanceNo)
	}

	void verifySummaryOrCheckBalance(List<String> summary, int timeout = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		if(waitForElementPresent(commonPage.lblOtherRMNo(1), timeout)) {
			checkBalance()
		}
		else {
			commonPage.verifyElements(summary, commonPage.lblNormalizeSpacePath(), GlobalVariable.G_timeoutMedium, FailureHandling.STOP_ON_FAILURE)
		}
	}
	void navigateToManageCategory(String manageCategory, int timeout = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		MobileDriver.tap(commonPage.imgNoDynamic(1), timeout, flowControl)
		MobileDriver.tap(commonPage.lblDynamic(manageCategory), timeout, flowControl)
	}
	void fillCategoryInfor(Map<String,String> categoryData, int timeout = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		MobileDriver.setText(this.txtSiblingGroup(categoryData.get("name")), categoryData.get("nameCate"), timeout, flowControl)
		MobileDriver.tap(this.imgSiblingGroup(categoryData.get("icon")), timeout, flowControl)
		MobileDriver.tap(commonPage.imgNoDynamic(categoryData.get("iconCate")), timeout, flowControl)
		MobileDriver.tap(commonPage.lblDynamicNo(SELECT_ICON, 2), timeout, flowControl)
		MobileDriver.tap(commonPage.btnDynamic(SAVE), timeout, flowControl)
	}
	void selectCategoryToEdit(Map<String, String> categoryMap, int timeout = GlobalVariable.G_timeoutShort, FailureHandling flowControl = defaultFailureHandling) {
		String category = categoryMap.get("category")
		String subCategory = categoryMap.get("subCategory")
		verifyElementNotVisible(commonPage.lblContainDynamic(ADD_CATERGORY), timeout)
		findAndTapCategory(category)
		findAndTapCategory(subCategory)
	}
	void editCategory(Map<String,String> categoryData,int timeout = GlobalVariable.G_timeoutShort, FailureHandling flowControl = defaultFailureHandling) {
		MobileDriver.tap(commonPage.lblSiblingOneGroup(categoryData.get("nameCate")), timeout, flowControl)
		Mobile.delay(GlobalVariable.G_timeoutMiniShort)
		MobileDriver.checkHideKeyBoard()
	}

	void applyFilter(String filterValue, List<String> execptedValue, int timeout = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		delay(GlobalVariable.G_timeoutMiniShort)
		MobileDriver.tap(commonPage.lblDynamic(FILTER), timeout, flowControl)
		delay(GlobalVariable.G_timeoutMiniShort)
		MobileDriver.tap(commonPage.lblDynamic(filterValue), timeout, flowControl)
		delay(GlobalVariable.G_timeoutMiniShort)
		MobileDriver.tap(commonPage.lblDynamic(APPLY_FILTER), timeout, flowControl)
		delay(GlobalVariable.G_timeoutMiniShort)
		'Scroll down'
		Mobile.swipe(400, 1500, 500, 400)
		verifyElements(execptedValue, commonPage.lblNormalizeSpacePath(), timeout, flowControl)
	}
	
	def getItemsOnAScreen(List<String> xpaths, TestObject groupsTO) {
		MobileDriver.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(GlobalVariable.G_timeoutShort))
		List<WebElement> elements = MobileDriver.driver.findElements(By.xpath(MobileDriver.getLocator(groupsTO)))
		List<Object> items = []
		for (WebElement element : elements) {
			try {
				List<String> eleItems = []
				for(int i=0; i<xpaths.size(); i++) {
					String value = element.findElement(xpaths[i]).getText()
					eleItems.add(value)
				}
				items.add(eleItems)
			}catch(e) {
			}
		}
		items.each { text ->
			println text.toString()
		}
		print(RunConfiguration.getTimeOut())
		MobileDriver.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(RunConfiguration.getTimeOut()))

		return items
	}
	void deleteCategory(int timeout = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		delay(GlobalVariable.G_timeoutShort)
		MobileDriver.tap(commonPage.lblDynamic(DELETE), timeout, flowControl)
		MobileDriver.tap(commonPage.lblDynamic(YES), timeout, flowControl)
	}
	void splitBill(String type = EVENLY, String billName, String note, String amount, List<String> mobileNumberShareBill, String amt1st = DEFAULT_AMT, FailureHandling flowControl = defaultFailureHandling) {
		splitBillDetails(type, billName, note, amount, flowControl)
		finalizeSplitBill(mobileNumberShareBill, type, amt1st, flowControl)
	}
	void splitBillDetails(String type, String billName, String note, String amount, FailureHandling flowControl) {
		'Tap Split  Bill'
		MobileDriver.tap(commonPage.lblNormalizeSpace(SPLIT_BILL), GlobalVariable.G_timeoutLong, flowControl)
		'Set BillName'
		MobileDriver.setText(commonPage.txtEditNo(1), billName, GlobalVariable.G_timeoutMedium, flowControl)
		'Set Note'
		MobileDriver.setText(commonPage.txtEditNo(2), note, GlobalVariable.G_timeoutMedium, flowControl)
		'Tap type a Bill'
		MobileDriver.tap(commonPage.lblDynamic(type), GlobalVariable.G_timeoutLong, flowControl)
		commonPage.tapContinueBtn()
		if (type == EVENLY) {
			'reset data'
			for (int i = 0; i < 3; i++) {
				MobileDriver.tap(commonPage.imgNoDynamic(4), GlobalVariable.G_timeoutLong, flowControl);
			}
			'Enter amount to share'
			commonPage.enterNO(amount, false)
			'Tap to stick button'
			MobileDriver.tap(commonPage.imgNoDynamic(6), GlobalVariable.G_timeoutLong, flowControl)
		}
	}

	void finalizeSplitBill(List<String> mobileNumberShareBill, String type, String amt1st, FailureHandling flowControl) {
		'Select a contact to share'
		for (String mobiNumber : mobileNumberShareBill) {
			MobileDriver.tap(commonPage.lblDynamic(mobiNumber), GlobalVariable.G_timeoutLong, flowControl)
		}

		commonPage.tapDoneBtn()
		commonPage.tapContinueBtn()
		MobileDriver.waitForElementPresent(commonPage.lblDynamic(SPLIT_AMOUNT), GlobalVariable.G_timeoutShort)
		if (type != EVENLY) {
			while (MobileDriver.waitForElementPresent(commonPage.lblDynamic(RM_DEFAULT), GlobalVariable.G_timeoutShort)) {
				'Tap to input'
				MobileDriver.tap(commonPage.lblDynamicTextLast(RM_DEFAULT), GlobalVariable.G_timeoutLong, flowControl)
				'Enter amount to share'
				commonPage.enterNO(amt1st, false)
				'Tap to stick button'
				MobileDriver.tap(commonPage.imgNoDynamic(6), GlobalVariable.G_timeoutLong, flowControl)
			}
		}
		Mobile.delay(GlobalVariable.G_timeoutMiniShort)
		MobileDriver.tap(commonPage.lblDynamic(SPLIT_NOW), GlobalVariable.G_timeoutShort)
		if(MobileDriver.waitForElementPresent(commonPage.lblStartsWith(SHARE_BILL), GlobalVariable.G_timeoutShort, FailureHandling.OPTIONAL)) {
			Mobile.pressBack()
		}
	}
	void selectCategoryToSplitBill(Map<String, String> categoryMap, boolean isSplitBill ,int timeout = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling) {
		String category = categoryMap.get("category")
		String subCategory = categoryMap.get("subCategory")
		commonPage.swipeToObject(commonPage.lblDynamic(subCategory))
		findAndTapCategory(category, isSplitBill)
		while(true) {
			findAndTapCategory(category, !isSplitBill)
			if(waitForElementPresent(commonPage.lblNormalizeSpace(SPLIT_BILL), GlobalVariable.G_timeoutMiniShort)) {
				break;
			}
		}
		
	}
}
