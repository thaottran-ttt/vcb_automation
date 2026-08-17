package com.maybank.mae.internal.pages.androidphone

import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import java.time.Duration

import org.openqa.selenium.By
import org.openqa.selenium.WebElement

import com.kms.katalon.core.configuration.RunConfiguration
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testobject.TestObject
import com.maybank.common.Android
import com.maybank.common.MobileDriver
import com.maybank.mae.internal.pages.base.CommonPage
import com.maybank.utils.StringUtils

import internal.GlobalVariable

public class AndroidPhoneCommonPage extends CommonPage{

	@Override
	public TestObject lblDynamic(String text) {
		return findTestObject(this.lblDynamicPath(), ['text': text]);
	}

	@Override
	public TestObject lblDynamicList(List textList) {
		def xpathQuery = "//*[${textList.collect { "@text = '${it}'"}.join(' or ')}]"	 
		
		return findTestObject('Object Repository/Mobile/MAE/Dynamic/Dynamic', ['text': xpathQuery]);
}

	@Override
	public TestObject lblDynamicTextLast(String text) {
		return findTestObject('Object Repository/Mobile/MAE/Dynamic/lblDynamicLast', ['text': text]);
}

	@Override
	public TestObject lblDynamicNo(String text, int index) {
		return findTestObject('Object Repository/Mobile/MAE/Dynamic/lblDynamicNo', ['text': text, 'index': index]);
}
	
	@Override
	public TestObject lblTextViewDynamic(String text, Object index = 1) {
		return findTestObject('Object Repository/Mobile/MAE/Dynamic/lblTextViewDynamic', ['text': text, 'index': index]);
}

	@Override
	public TestObject lblTextViewContain(String text, Object index = 1) {
		return findTestObject('Object Repository/Mobile/MAE/Dynamic/Android/lblTextViewContain', ['text': text, 'index': index]);
}
	
	@Override
	public TestObject lblContainDynamic(String text) {
		return findTestObject(this.lblContainDynamicPath(), ['text': text]);
}

	@Override
	public TestObject lblContainDynamicNo(String text, int index) {
		return findTestObject(this.lblContainDynamicNoPath(), ['text': text, 'index': index]);
}
	
	@Override
	public TestObject lblContainDynamicNo(String text) {
		return lblContainDynamicNo(text, 1)
}

	@Override
	public boolean scrollToTestObject(TestObject grid, TestObject findObj, ScrollDirectionEnum direction, int timeout, FailureHandling flowControl = defaultFailureHandling, boolean flexibleDeivce = false) {
		Android.scrollToTestObject(grid, findObj, direction, timeout, flowControl, flexibleDeivce)
}

	@Override
	TestObject btnTick() {
		return findTestObject('Object Repository/Mobile/MAE/Android/Common/OTP/btnTick')
}

	@Override
	public TestObject lblSiblingDynamic(String text, int index=1) {
		return findTestObject('Object Repository/Mobile/MAE/Dynamic/Android/lblSiblingDynamic', ['text': text, 'index': index]);
}

	@Override
	public TestObject txtEditNo(int index = 1) {
		return findTestObject('Object Repository/Mobile/MAE/Dynamic/Android/txtEditNo', ['index': index]);
}

	@Override
	public TestObject imgNoDynamic(Object index = 1) {
		return findTestObject('Object Repository/Mobile/MAE/Dynamic/Android/imgNoDynamic', ['index': index.toString()]);
}
	
	@Override
	public TestObject imgNoSimpleDynamic(Object index = 1) {
		return findTestObject('Object Repository/Mobile/MAE/Dynamic/Android/imgNoSimpleDynamic', ['index': index.toString()]);
}

	@Override
	public TestObject lblNormalizeSpace(String text) {
		return findTestObject('Object Repository/Mobile/MAE/Dynamic/lblNormalizeSpace', ['text': text]);
}

	@Override
	public TestObject lblDropDownSelect(String text, Object index = 1) {
		return findTestObject('Object Repository/Mobile/MAE/Dynamic/lblDropDownSelect', ['text': text, 'index': index]);
}

	@Override
	public String lblNormalizeSpacePath() {
		return 'Object Repository/Mobile/MAE/Dynamic/lblNormalizeSpace';
}

	@Override
	public TestObject lblTextMatches(String text) {
		return findTestObject('Object Repository/Mobile/MAE/Dynamic/lblTextMatches', ['text': text]);
}

	@Override
	public TestObject lblTextIgnoreCaseNo(String text, Object index = 1) {
		return findTestObject('Object Repository/Mobile/MAE/Dynamic/lblTextIgnoreCaseNo', [('text') : StringUtils.escapeForXPath(text), 'index': index.toString()]);
}

	@Override
	public TestObject lblSiblingOneGroup(String text) {
		return findTestObject('Object Repository/Mobile/MAE/Dynamic/Android/lblSiblingOneGroup', ['text': text]);
}	

	@Override
	public TestObject lblRMNo(Object index) {
		return findTestObject('Object Repository/Mobile/MAE/Dynamic/Android/lblRMNo', ['index': index.toString()]);
}

	@Override
	public TestObject lblTextNo(int index = 1) {
		return findTestObject('Object Repository/Mobile/MAE/Dynamic/Android/lblTextNo', ['index': index]);
}

	@Override
	public TestObject btnRemove() {
		return findTestObject('Object Repository/Mobile/MAE/Android/Common/OTP/btnRemove');
}

	@Override
	public TestObject txtPasswordRegister() {
		return findTestObject('Object Repository/Mobile/MAE/Android/Login Screen/txtPasswordRegister');
}

	@Override
	public TestObject frmHorizontalScrollNo(Object text) {
		return findTestObject('Object Repository/Mobile/MAE/Dynamic/Android/frmHorizontalScroll', ['text': text.toString()]);
}

	@Override
	public TestObject frmScrollView(Object index = 1) {
		return findTestObject('Object Repository/Mobile/MAE/Dynamic/Android/frmScrollView', ['index': index.toString()]);
}

	@Override
	public TestObject frmWebViewScrollNo(int index = 1) {
		return findTestObject('Object Repository/Mobile/MAE/Dynamic/Android/frmWebViewScroll', ['index': index]);
}	

	@Override
	public TestObject btnRadioNo(int index = 1) {
		return findTestObject('Object Repository/Mobile/MAE/Dynamic/Android/btnRadioNo', ['index': index]);
}
	@Override
	public TestObject btnRadioImageViewStyle(String text) {
		return findTestObject('Object Repository/Mobile/MAE/Dynamic/Android/btnRadioImageViewStyle', ['text': text]);
}

	@Override
	public TestObject lblLineValue() {
		return findTestObject('Object Repository/Mobile/MAE/Android/Transactions Screen/lblLineValue');
}

	@Override
	public TestObject grpPrecedingSibling(String text) {
		return findTestObject('Object Repository/Mobile/MAE/Dynamic/Android/grpPrecedingSibling', ['text': text]);
}
	@Override
	public TestObject txtEditTextDynamic(String fieldName) {
		return findTestObject('Object Repository/Mobile/MAE/Dynamic/Android/txtEditTextDynamic',[('text') : fieldName])
}
	
	@Override	
	public TestObject txtEditText(String text) {
		return findTestObject('Object Repository/Mobile/MAE/Dynamic/Android/txtEditText',[('text') : text])
}

	@Override
	public TestObject btnTextDynamic(String text) {
		return findTestObject('Object Repository/Mobile/MAE/Dynamic/Android/btnTextDynamic', [('text') : text])
}

	@Override
	public TestObject frmScrollViewGroupNo(int scrollIndex, int groupNo) {
		String text = "//android.widget.ScrollView[${scrollIndex}]"
		String groupView = ''
		for(int i=0; i< groupNo; i++) {
			groupView += '/android.view.ViewGroup'
}
		text += groupView
		return findTestObject('Object Repository/Mobile/MAE/Dynamic/Dynamic', ['text': text]);
}

	@Override
	public boolean swipe(int startX, int startY, int endX, int endY) {
		return Android.scrollToNotDirection(startX, startY, endX, endY)
}
	
	@Override
	public boolean swipeByW3CAction(int startX, int startY, int endX, int endY) {
		return Android.scrollByW3CAction(startX, startY, endX, endY)
}

	@Override
	public boolean slider(TestObject slider, TestObject sliderPoint, int min, int max, int value) {
		int sliderLeft = Mobile.getElementLeftPosition(slider, GlobalVariable.G_timeoutLong)
		int left = Mobile.getElementLeftPosition(sliderPoint, GlobalVariable.G_timeoutLong)
		int top = Mobile.getElementTopPosition(sliderPoint, GlobalVariable.G_timeoutLong)
		int sliderWidth = Mobile.getElementWidth(slider, GlobalVariable.G_timeoutLong)

		this.swipe(left, top, sliderLeft + (value / (max - min) * sliderWidth) , top)

		return false;
}

	@Override
	public boolean sliderToDistance(TestObject sliderPoint, int distance) {
		int left = Mobile.getElementLeftPosition(sliderPoint, GlobalVariable.G_timeoutLong)
		int top = Mobile.getElementTopPosition(sliderPoint, GlobalVariable.G_timeoutLong)

		this.swipe(left, top, left + distance , top)
		return false;
}

	@Override
	public TestObject lblStartsWith(String text) {
		return findTestObject('Object Repository/Mobile/MAE/Dynamic/lblStartsWith', [('Verify') : text]);
}

	@Override
	public TestObject lblAnyText(String text) {
		return findTestObject('Object Repository/Mobile/MAE/Dynamic/lblAnyText', [('Verify') : text]);
}

	@Override
	public TestObject imgLoading() {
		return findTestObject('Object Repository/Mobile/MAE/Android/Common/Loading/imgLoading');
}

	@Override
	public String getXpathTextViewByNo(Object index) {
		return "//android.widget.TextView[${index.toString()}]"
}

	@Override
	public TestObject btnDynamic(String text) {
		return findTestObject('Object Repository/Mobile/MAE/Dynamic/Android/btnDynamic', [('text') : text])
}

	def getItemsOnAScreen(List<String> xpaths, TestObject groupsTO) {
		MobileDriver.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(GlobalVariable.G_timeoutShort))
		List<WebElement> elements = MobileDriver.driver.findElements(By.xpath(MobileDriver.getLocator(groupsTO)))
		List<Object> items = []
		for (WebElement element : elements) {
			try {
				List<String> eleItems = []
				for(int i=0; i<xpaths.size(); i++) {
					WebElement childElement = element.findElement(By.xpath(xpaths[i]))
					String value = childElement.getText()
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

	@Override
	public List<Object> getAllItemsListView(List<String> xpaths, TestObject groupsTO, int indexCheckDuplicate = 0, List<Integer> scrollPosition = [5, 1000, 5, 10]) {
		List<Object> items = []

		def prevPageSource = ""
		while(true) {
			items += getItemsOnAScreen(xpaths, groupsTO)
			this.swipe(scrollPosition.get(0), scrollPosition.get(1), scrollPosition.get(2), scrollPosition.get(3))
			def currentPageSource = MobileDriver.driver.getPageSource()
			if(prevPageSource.equals(currentPageSource)) {
				break
}
			prevPageSource = currentPageSource
}

		List<Object> uniqueList = items.collectEntries {
			[it[indexCheckDuplicate], it]
}.values() as List

		return uniqueList
}

	@Override
	public void scrollToTop(int startX, int startY, int endX, int endY) {
		def deviceHeight = Mobile.getDeviceHeight()

		def prevPageSource = ""
		while(true) {
			def currentPageSource = MobileDriver.driver.getPageSource()
			if(prevPageSource.equals(currentPageSource)) {
				return;
}
			prevPageSource = currentPageSource
			this.swipe(startX, startY, endX, endY)
}
}

	//Drop-down

	@Override
	public TestObject txtSiblingDynamic(String fieldName) {
		return findTestObject('Object Repository/Mobile/MAE/Dynamic/Android/txtSiblingDynamic',[('text') : fieldName])
}

	@Override
	public TestObject imgSiblingGroup(String fieldName) {
		return findTestObject('Object Repository/Mobile/MAE/Dynamic/Android/imgSiblingGroup',[('label') : fieldName])
}

	@Override
	public TestObject imgArrowDownDropdown(String fieldName, Object index) {
		return findTestObject('Object Repository/Mobile/MAE/Dynamic/Android/imgArrowDownDropdown',[('text') : fieldName, 'index': index.toString()])
}

	@Override
	public TestObject imgSibling(String text) {
		return findTestObject('Object Repository/Mobile/MAE/Dynamic/Android/imgSibling',[('text') : text])
}

	@Override
	public TestObject txtSiblingGroup(String label) {
		return findTestObject('Object Repository/Mobile/MAE/Dynamic/Android/txtSiblingGroup',[('label') : label])
}

	@Override
	public TestObject frmScrollDropdown() {
		return findTestObject('Object Repository/Mobile/MAE/Android/Common/Dropdown/frmScrollDropdown')
}

	@Override
	public TestObject lblHomeOrAgree() {
		return findTestObject('Object Repository/Mobile/MAE/Android/Common/Home/lblHomeOrAgree')
}

	@Override
	public TestObject lblOtherRMNo(int index) {
		return findTestObject('Object Repository/Mobile/MAE/Dynamic/Android/lblOtherRMNo', ['index': index.toString()]);
}

	@Override
	public TestObject lblSiblingGroup(String lable) {
		return findTestObject('Object Repository/Mobile/MAE/Android/Tabung Screen/lblSiblingGroup', ['label': lable]);
}
	@Override
	public TestObject grpContainsPrecedingSibling(String text) {
		return findTestObject('Object Repository/Mobile/MAE/Dynamic/Android/grpContainPrecedingSibling', ['text': text]);
}
	
	@Override
	public TestObject lblDynamicId(String id) {
		return findTestObject('Object Repository/Mobile/MAE/Dynamic/Android/lblDynamicId', ['id': id]);
}

	@Override
	public TestObject lblDynamicNo(String text) {
		// TODO Auto-generated method stub
		return null;
}
}
