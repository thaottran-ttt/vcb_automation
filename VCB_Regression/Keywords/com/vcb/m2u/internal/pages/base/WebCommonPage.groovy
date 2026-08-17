package com.vcb.m2u.internal.pages.base

import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testobject.TestObject
import com.vcb.assertions.BaseAssert
import com.vcb.common.WebUIKeyword
import com.vcb.utils.StringUtils


import internal.GlobalVariable

public class WebCommonPage extends WebBasePage{
	static final String DONE = 'Done'
	static final String SAVE = 'SAVE'
	static final String CONFIRM = 'CONFIRM'
	static final String TRANSFER = 'TRANSFER'
	static final String SUBMIT = 'SUBMIT'

	//Class
	final String MODAL_CLASS = 'modal-content'
	final String BTN_CLASS = 'btn'
	final String MODAL_CLOSE = 'DynoModal---close'
	final String BTN_NEXT_MONTH = 'text-muted pull-right'

	//Dynamic
	//Label Group
	static TestObject lblContainsDynamic(String text, def index = 1) {
		return findTestObject('Object Repository/Website/M2U/Browsers/Dynamic/Label/lblContainsDynamicLast', ['text': text, 'index': index.toString()])
	}

	static TestObject lblDynamic(String text, def index = 1) {
		return findTestObject('Object Repository/Website/M2U/Browsers/Dynamic/Label/lblDynamic', ['text': text, 'index': index.toString()])
	}

	static TestObject lblDynamicList(List textList, HTMLTag tag = HTMLTag.empty) {
		String tagName = (tag == HTMLTag.empty) ? "*" : tag.toString()
		def xpathQuery = "//${tagName}[" + textList.collect { "text() = '${it.replace("'", "\\'")}'" }.join(' or ') + "]"


		return findTestObject('Object Repository/Website/M2U/Browsers/Dynamic/dynamic', ['text': xpathQuery]);
	}

	static TestObject lblIgnoreCase(String text, HTMLTag tag = HTMLTag.empty, def index = 1) {
		String tagName = (tag == HTMLTag.empty) ? "*" : tag.toString()
		def xpathQuery = "(//${tagName}[translate(normalize-space(.), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz') = '${text.toLowerCase()}'])[${index.toString()}]"
		return findTestObject('Object Repository/Website/M2U/Browsers/Dynamic/dynamic', ['text': xpathQuery]);
	}

	static TestObject lblIgnoreCaseContains(String text, HTMLTag tag = HTMLTag.empty, def index = 1) {
		String tagName = (tag == HTMLTag.empty) ? "*" : tag.toString()
		String lowerText = text.toLowerCase()
		def xpathQuery = "(//${tagName}[contains(translate(normalize-space(.), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), '${lowerText}')])[${index.toString()}]"
		return findTestObject('Object Repository/Website/M2U/Browsers/Dynamic/dynamic', ['text': xpathQuery])
	}

	static TestObject lblContainClass(String text, def index = 1, String tagChildEle = '') {
		return findTestObject('Object Repository/Website/M2U/Browsers/Dynamic/Label/lblContainClass', ['text': text, 'index': index.toString(), tagChildEle: tagChildEle? "//${tagChildEle}": tagChildEle])
	}

	static TestObject lblContainClassAndText(String className, String text, def index = 1) {
		return findTestObject('Object Repository/Website/M2U/Browsers/Dynamic/Label/lblContainClassAndText', ['className': className, 'text': text, 'index': index.toString()])
	}

	static TestObject lblContainParentClassAndText(String className, String text, def index = 1) {
		return findTestObject('Object Repository/Website/M2U/Browsers/Dynamic/Label/lblContainParentClassAndText', ['className': className, 'text': text, 'index': index.toString()])
	}

	static TestObject lblContainClassAndContainText(String className, String text, def index = 1) {
		return findTestObject('Object Repository/Website/M2U/Browsers/Dynamic/Label/lblContainClassAndContainText', ['className': className, 'text': text, 'index': index.toString()])
	}

	static TestObject lblNormalizeSpace(String text, def index = 1, HTMLTag tag = HTMLTag.empty) {
		if(StringUtils.containsSpecialCharacter(text)) {
			def result = "//*[normalize-space()=" + StringUtils.convertToXPathConcat(text) + "][1]"
			return findTestObject('Object Repository/Website/M2U/Browsers/Dynamic/dynamic', ['text': result])
		}
		return findTestObject('Object Repository/Website/M2U/Browsers/Dynamic/Label/lblNormalizeSpace', ['text': text, 'index': index.toString(), tag: StringUtils.toStringHTMLTag(tag)])
	}

	static TestObject lblContainClassAndNormalizeSpace(String className, String text, def index = 1) {
		return findTestObject('Object Repository/Website/M2U/Browsers/Dynamic/Label/lblContainClassAndNormalizeSpace', ['className': className, 'text': text, 'index': index.toString()])
	}

	static TestObject getTestObjectById(String id) {
		return findTestObject('Object Repository/Website/M2U/Browsers/Dynamic/idDynamic', [id: id])
	}

	//Text Group
	static TestObject txtForLabel(String text, int index = 1 ) {
		return findTestObject('Object Repository/Website/M2U/Browsers/Dynamic/Text/txtForLabel', ['text': text, 'index': index.toString()])
	}

	static TestObject inputPlaceholder(String label) {
		return findTestObject('Object Repository/Website/M2U/Browsers/Dynamic/Input/inputPlaceholder', ['label': label])
	}

	//Button Group
	static TestObject btnNormalizeSpaceDynamic(String text) {
		return findTestObject('Object Repository/Website/M2U/Browsers/Dynamic/Button/btnNormalizeSpaceDynamic', ['text': text])
	}

	//Frame Group
	static TestObject fraSiblingElementsByText(String text, int parentIndex = 0, String className = '', String afterFollowingSibling = '*', boolean isIgnoreCase = false) {
		String result = ""
		String parentPath = '../'.repeat(parentIndex)
		if(isIgnoreCase) {
			text = text.toLowerCase()
			result = "//*[translate(text(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz') = '${text}']/${parentPath}following-sibling::"
		}else {
			result = "//*[(text() = '${text}')]/${parentPath}following-sibling::"
		}
		if(className) {
			return findTestObject('Object Repository/Website/M2U/Browsers/Dynamic/dynamic', ['text': result + "*//*[contains(@class, '${className}')]"])
		}
		result += afterFollowingSibling
		return findTestObject('Object Repository/Website/M2U/Browsers/Dynamic/dynamic', ['text': result])
	}

	static TestObject fraPrecedingElementsByText(String text, int parentIndex = 0, String className = '', String afterFollowingSibling = '*') {
		String parentPath = '../'.repeat(parentIndex)
		String result = "//*[text() = '${text}']/${parentPath}preceding-sibling::"
		if(className) {
			return findTestObject('Object Repository/Website/M2U/Browsers/Dynamic/dynamic', ['text': result + "*//*[contains(@class, '${className}')]"])
		}
		result += afterFollowingSibling
		return findTestObject('Object Repository/Website/M2U/Browsers/Dynamic/dynamic', ['text': result])
	}

	static TestObject imgSrc(String text) {
		return findTestObject('Object Repository/Website/M2U/Browsers/Dynamic/imgSrc', ['text': text])
	}

	static TestObject btnChildLabel(String label) {
		return findTestObject('Object Repository/Website/M2U/Browsers/Dynamic/Button/btnChildLabel', ['label': label])
	}

	// Image Show more - 3 dots
	static TestObject imgShowMore(def index = 1) {
		return findTestObject('Object Repository/Website/M2U/Browsers/Dynamic/Img/imgShowMore',['index': index.toString()])
	}

	// Image transaction white
	static TestObject imgTransactionWhite(def index = 1) {
		return findTestObject('Object Repository/Website/M2U/Browsers/Common/imgTransactionWhite',['index': index.toString()])
	}

	//End Dynamic

	//Common TestObject
	//Dropdown
	TestObject drpForLabel(String text) {
		return findTestObject('Object Repository/Website/M2U/Browsers/Common/Dropdown/drpForLabel', ['text': text])
	}
	TestObject drpForLabelNo(String text, def index = 1) {
		return findTestObject('Object Repository/Website/M2U/Browsers/Common/Dropdown/drpForLabelNo', ['text': text, 'index': index.toString()])
	}
	TestObject lblDropdownItem(String value) {
		return findTestObject('Object Repository/Website/M2U/Browsers/Common/Dropdown/lblDropdownItem', [('text') : value])
	}
	TestObject lblDropdownFirstItem(String label) {
		return findTestObject('Object Repository/Website/M2U/Browsers/Common/Dropdown/lblDropdownFirstItem', [('text') : label])
	}
	TestObject lblDropdownCurrentValue(String label, String value) {
		return findTestObject('Object Repository/Website/M2U/Browsers/Common/Dropdown/lblDropdownCurrentValue', [('label') : label, value: value])
	}
	//BypassSecureURL
	TestObject lnkProceed() {
		return findTestObject('Object Repository/Website/M2U/Browsers/Secure/lnkProceed')
	}
	TestObject lnkAdvance() {
		return findTestObject('Object Repository/Website/M2U/Browsers/Secure/lnkAdvance')
	}
	//Date-Picker
	TestObject lblNextDate() {
		return findTestObject('Object Repository/Website/M2U/Browsers/Common/DatePicker/lblNextDate')
	}

	TestObject imgNextMonth() {
		return findTestObject('Object Repository/Website/M2U/Browsers/Common/DatePicker/imgNextMonth')
	}

	static TestObject lblDayDatePicker(def text) {
		return findTestObject('Object Repository/Website/M2U/Browsers/Common/DatePicker/lblDayDatePicker', [text: text.toString()])
	}

	static TestObject btnDatePickerForLabel(String label) {
		return findTestObject('Object Repository/Website/M2U/Browsers/Common/DatePicker/btnDatePicker', [label: label])
	}

	static TestObject lblDatePickerForLabel(String label) {
		return findTestObject('Object Repository/Website/M2U/Browsers/Common/DatePicker/lblDatePicker', [label: label])
	}

	TestObject imgLoading() {
		return findTestObject('Object Repository/Website/M2U/Browsers/Common/imgLoading')
	}

	//End Common TestObject
	void clickDoneButton(FailureHandling flowControl = defaultFailureHandling) {
		WebUIKeyword.click(this.lblIgnoreCase(DONE, HTMLTag.button), flowControl)
	}

	void clickSaveButton(FailureHandling flowControl = defaultFailureHandling) {
		WebUIKeyword.click(this.btnNormalizeSpaceDynamic(SAVE), flowControl)
	}

	void clickConfirmButton(FailureHandling flowControl = defaultFailureHandling, int timeout = GlobalVariable.G_timeoutShort) {
		WebUIKeyword.waitForElementClickable(this.btnNormalizeSpaceDynamic(CONFIRM), timeout)
		WebUIKeyword.click(this.btnNormalizeSpaceDynamic(CONFIRM), flowControl)
	}

	void clickTransferButton(boolean ignoreCase = true, FailureHandling flowControl = defaultFailureHandling) {
		if(ignoreCase) {
			WebUIKeyword.click(this.lblIgnoreCase(TRANSFER, HTMLTag.button), flowControl)
			return
		}
		WebUIKeyword.click(this.btnNormalizeSpaceDynamic(TRANSFER), flowControl)
	}

	void clickTestObjectWithWait(TestObject to, int timeout = GlobalVariable.G_timeoutOneMinute, FailureHandling flowControl = defaultFailureHandling) {
		WebUIKeyword.click(to, flowControl)
		this.waitLoading(timeout)
	}

	/*
	 * Bypass Secure https after access url
	 */
	void bypassSecureURL(int timeout, FailureHandling flowControl = defaultFailureHandling) {
		if(waitForElementPresent(this.lnkAdvance(), timeout, FailureHandling.OPTIONAL)) {
			WebUIKeyword.click(this.lnkAdvance())
			WebUIKeyword.waitForElementPresent(this.lnkProceed(), timeout, flowControl)
			WebUIKeyword.click(this.lnkProceed());
		}
	}

	void waitModelLoading(int timeout = GlobalVariable.G_timeoutLong) {
		'Wait loading'
		WebUIKeyword.waitForElementPresent(this.lblContainClass(MODAL_CLASS), timeout, FailureHandling.OPTIONAL)
		'Wait animation'
		WebUIKeyword.delay(GlobalVariable.G_timeoutMiniShort)
	}

	void waitLoading(int timeout = GlobalVariable.G_timeoutOneMinute) {
		info('Wait for loading with a timeout: ' + timeout)
		WebUIKeyword.waitForElementNotPresent(this.imgLoading(), timeout, FailureHandling.OPTIONAL)
	}

	void closeModal(FailureHandling flowControl = defaultFailureHandling) {
		WebUIKeyword.click(lblContainClass(MODAL_CLOSE), flowControl)
	}

	/**
	 * Selects a value from a dropdown based on the given label and value.
	 *
	 * @param label       the label associated with the dropdown
	 * @param value       the value to be selected from the dropdown
	 * @param flowControl (optional) defines the flow control behavior in case of failure, 
	 *                    defaulting to the global `defaultFailureHandling`
	 * 
	 * @return void
	 */
	void selectDropdown(String label, String value, FailureHandling flowControl = defaultFailureHandling, boolean checkCurrentValue = false) {
		if(checkCurrentValue && WebUIKeyword.waitForElementPresent(this.lblDropdownCurrentValue(label, value), GlobalVariable.G_timeoutMiniShort, FailureHandling.OPTIONAL)) {
			info("No Select Dropdown")
			return
		}
		scrollToTestObject(this.drpForLabel(label))
		WebUIKeyword.waitForElementClickable(this.drpForLabel(label), GlobalVariable.G_timeoutShort, FailureHandling.OPTIONAL)
		WebUIKeyword.click(this.drpForLabel(label), flowControl)
		scrollToTestObject(this.lblDropdownItem(value))
		WebUIKeyword.waitForElementClickable(this.lblDropdownItem(value), GlobalVariable.G_timeoutShort, FailureHandling.OPTIONAL)
		WebUIKeyword.click(this.lblDropdownItem(value), flowControl)
		'Wait for loading data'
		this.waitLoading(GlobalVariable.G_timeoutMedium)
	}

		void selectDropdownByTestObject(TestObject obj, String value, Boolean isScroll = true,  FailureHandling flowControl = defaultFailureHandling) {
			if(isScroll) {
				scrollToTestObject(obj)
			}

			WebUIKeyword.click(obj, flowControl)
			if (isScroll) {
				scrollToTestObject(this.lblDropdownItem(value))
			}

			WebUIKeyword.click(this.lblDropdownItem(value), flowControl)
		}

		static void inputTextForLabel(String label, String value, FailureHandling flowControl = defaultFailureHandling) {
			WebUIKeyword.setText(this.txtForLabel(label), value, flowControl)
		}


		/**
		 * inputDropdownMap: Input Many Dropdown
		 * @return
		 */
		boolean inputDropdownMap(Map data, FailureHandling flowControl = defaultFailureHandling){
			data.each {
				key, value ->
				this.selectDropdown(key, value, flowControl)
			}
		}

		/**
		 * inputTextListGroup: Set Text base on label
		 * @return
		 */
		boolean inputTextMap(Map data, FailureHandling flowControl = defaultFailureHandling){
			data.each {
				key, value ->
				WebUIKeyword.waitForElementClickable(this.txtForLabel(key), GlobalVariable.G_timeoutShort, FailureHandling.OPTIONAL)
				WebUIKeyword.setText(this.txtForLabel(key), value, flowControl)
			}
		}

		/**
		 * selectTomorrowDate: Select Next Date
		 * Todo:
		 * If it's not the last day of the month, click the next day.
		 * If it's the last day of the month, click 'Next Month' and select the 1st day of the next month.
		 * @return
		 */
		void selectTomorrowDate(FailureHandling flowControl = FailureHandling.STOP_ON_FAILURE) {
			// Get the current date
			Calendar calendar = Calendar.getInstance();
			int currentDay = calendar.get(Calendar.DAY_OF_MONTH);
			int lastDayOfMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

			// Check if today is not the last day of the month
			if (currentDay != lastDayOfMonth) {
				// If it's not the last day, click on the next day
				WebUIKeyword.click(lblNormalizeSpace((currentDay + 1).toString(), 1, HTMLTag.td), flowControl);
			} else {
				// If it's the last day of the month, click on the next month button and then click the 1st day of the next month
				WebUIKeyword.click(lblContainClass(BTN_NEXT_MONTH, 'last()'));
				WebUIKeyword.click(lblDayDatePicker(1), flowControl);
			}
		}
		/**
		 * selectNextMonthDate: Select Next Date
		 * @return
		 */
		void selectNextMonthDate(FailureHandling flowControl = FailureHandling.STOP_ON_FAILURE) {
			// Get the current date
			Calendar calendar = Calendar.getInstance();
			int currentDay = calendar.get(Calendar.MONTH);

			// If it's the last day of the month, click on the next month button and then click the 1st day of the next month
			WebUIKeyword.click(lblContainClass(BTN_NEXT_MONTH, 'last()'));
			WebUIKeyword.click(lblDayDatePicker(28), flowControl);
			
		}



		/**
		 * verifyElementListVisible: verify elements is visible
		 * true: All text in list is visible
		 * @return
		 */
		def verifyElementListVisible(List<String> lst, FailureHandling flowControl = defaultFailureHandling, ElementVerificationType type = ElementVerificationType.Normalize_Space, int index = 1) {
			info('Verify UI' + lst.toString())
			lst.each {
				item ->
				def variable = item
				switch(type) {
					case ElementVerificationType.Normalize_Space:
					WebUIKeyword.verifyElementVisible(this.lblNormalizeSpace(item), flowControl)
					break
					case ElementVerificationType.Contains:
					WebUIKeyword.verifyElementVisible(this.lblContainsDynamic(item, index), flowControl)
					break
				}
			}
		}

		/**
		 * verifAnyElementListVisible: verify any elements is visible
		 * true: Any text in list is visible
		 * @return
		 */
		def verifyAnyElementListVisible(List<String> lst, int timeout = GlobalVariable.G_timeoutMedium, FailureHandling flowControl = defaultFailureHandling, ElementVerificationType type = ElementVerificationType.Normalize_Space) {
			info('Verify Some Elements' + lst.toString())
			if(lst.size() == 0) {
				return
			}

			boolean reslut = lst.any {
				item ->
				def variable = item
				switch(type) {
					case ElementVerificationType.Normalize_Space:
					return WebUIKeyword.waitForElementPresent(this.lblNormalizeSpace(item), timeout, flowControl)
					case ElementVerificationType.Contains:
					return WebUIKeyword.waitForElementPresent(this.lblContainsDynamic(item), timeout, flowControl)
				}
			}

			BaseAssert.equals(reslut, true, 'Element matches in ' + lst)
		}

		/**
		 * inputData
		 */
		void inputData(Map inputData, Map inputDropdownData = [:], FailureHandling flowControl = defaultFailureHandling) {
			'Select dropdown data'
			this.inputDropdownMap(inputDropdownData, flowControl)
			'Input data'
			this.inputTextMap(inputData, flowControl)
			'Take ScreenShort'
			WebUIKeyword.takeScreenshot()
		}
		/**
		 * inputTextListGroup: Set Text base on label
		 * @return
		 */
		boolean inputTextMapNo(Map data, int index = 1, FailureHandling flowControl = defaultFailureHandling){
			data.each {
				key, value ->
				WebUIKeyword.setText(this.txtForLabel(key, index), value, flowControl)
			}
		}
	}

public enum ElementVerificationType{
	Normalize_Space, Contains
}

public enum HTMLTag{
	button, span, div, p, h1, h3, h4, h6, a, label, empty, th, td
}
