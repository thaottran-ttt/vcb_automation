package com.maybank.common

import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import java.time.Duration
import java.util.concurrent.ThreadLocalRandom

import javax.xml.parsers.DocumentBuilderFactory
import javax.xml.xpath.XPathFactory

import org.openqa.selenium.WebElement
import org.openqa.selenium.interactions.Actions
import org.w3c.dom.Document

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.configuration.RunConfiguration
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testobject.MobileTestObject
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.util.KeywordUtil
import com.maybank.assertions.NumberAssert
import com.maybank.utils.StringUtils

import groovy.time.TimeCategory
import internal.GlobalVariable
import io.appium.java_client.AppiumDriver
import io.appium.java_client.android.AndroidDriver
public class MobileDriver extends BaseKeyword {

	private static AppiumDriver driver = MobileDriverFactory.getDriver()
	private static AndroidDriver androidDriver = (AndroidDriver) MobileDriverFactory.getDriver()
	private static ExecutionSetting executionSetting
	private static Map<String, String> cachedXMLPages = [:]

	public MobileDriver(){
		executionSetting = new ExecutionSetting()
	}

	/**
	 * openApp
	 * @param appPackage
	 * @return
	 */
	@Keyword
	static void openApp(String appPackage) {
		'Open app'
		Mobile.startExistingApplication(appPackage)

		driver = MobileDriverFactory.getDriver()
		Mobile.delay(2)
	}

	static String toStringAndLocatorTestObject(TestObject obj) {
		MobileTestObject to = obj
		String test = to.toString() + to.getLocator();
		return to.toString() + to.getLocator()
	}

	@Keyword
	static void takeScreenshot(FailureHandling flowControl = FailureHandling.OPTIONAL) {
		boolean isMobileStarted = false
		try {
			isMobileStarted = MobileDriverFactory.getDriver() != null
		} catch (Exception e) {
			isMobileStarted = false
		}
		if (isMobileStarted) {
			Mobile.takeScreenshot(flowControl)
		}
	}
	/**
	 * VerifyElementExist
	 * @param testObject
	 * @param timeout
	 * @return
	 */
	@Keyword
	static boolean activateApp(String appID, FailureHandling flowControl = defaultFailureHandling) {
		info "acctivate app id ${appID}"
		androidDriver.activateApp(appID)
	}
	/**
	 * VerifyElementExist
	 * @param testObject
	 * @param timeout
	 * @return
	 */
	@Keyword
	static boolean verifyElementExist(MobileTestObject obj, int timeout, FailureHandling flowControl = defaultFailureHandling) {
		info "verifyElementExist for test object ${toStringAndLocatorTestObject(obj)}"
		try {
			if(Mobile.verifyElementExist(obj, timeout, flowControl)) {
				return true
			}
		}catch(ex) {
			handleError("System Exception: Unable to find element ${toStringAndLocatorTestObject(obj)}", flowControl)
		}
		return false
	}

	/**
	 * VerifyElementExist
	 * @param testObject
	 * @param timeout
	 * @return
	 */
	@Keyword
	static boolean verifyElementVisible(TestObject obj, int timeout, FailureHandling flowControl = defaultFailureHandling) {
		info "VerifyElementVisible for test object ${toStringAndLocatorTestObject(obj)}"
		try {
			if(Mobile.verifyElementVisible(obj, timeout, flowControl)) {
				return true
			}else {
				info "Log Info: Unable to find element ${toStringAndLocatorTestObject(obj)}"
				handleError("System Exception: Unable to find element ${toStringAndLocatorTestObject(obj)}", flowControl)
			}
		}catch(ex) {
			handleError("System Exception: Unable to find element ${toStringAndLocatorTestObject(obj)}", flowControl)
		}

		return false
	}

	/**
	 * VerifyElementExist
	 * @param testObject
	 * @param timeout
	 * @return
	 */
	@Keyword
	static boolean verifyElementNotVisible(TestObject obj, int timeout, FailureHandling flowControl = defaultFailureHandling) {
		info "verifyElementNotVisible for test object ${toStringAndLocatorTestObject(obj)}"
		try {
			if(Mobile.verifyElementNotVisible(obj, timeout, flowControl)) {
				return true
			}
		}catch(ex) {
			handleError("System Exception: Unable to find element ${toStringAndLocatorTestObject(obj)}", flowControl)
		}
		return false
	}

	/**
	 * dragAndDrop
	 * @param testObject
	 * @param timeout
	 * @return
	 */
	@Keyword
	static void dragAndDrop(TestObject fromObject, TestObject toObject, int timeout, FailureHandling flowControl = defaultFailureHandling) {
		try {
			Mobile.dragAndDrop(fromObject, toObject, timeout, flowControl)
		}catch(ex) {
			handleError("System Exception: Unable to find element ${toStringAndLocatorTestObject(fromObject)}", flowControl)
		}
	}

	/**
	 * WaitForElementPresent
	 * @param testObject
	 * @param timeout
	 * @return
	 */
	@Keyword
	static boolean waitForElementPresent(TestObject obj, int timeout, FailureHandling flowControl = defaultFailureHandling) {
		info "waitForElementPresent for test object ${toStringAndLocatorTestObject(obj)}"
		try {
			if(Mobile.waitForElementPresent(obj, timeout, flowControl)) {
				return true
			}else {
				handleError("System Exception: Unable to find element ${toStringAndLocatorTestObject(obj)}", FailureHandling.OPTIONAL)
			}
		}catch(ex) {
			handleError("System Exception: Unable to find element ${toStringAndLocatorTestObject(obj)}", flowControl)
		}
		return false
	}

	/**
	 * Send key events
	 * @param input
	 * @return
	 */
	@Keyword
	public static def setTextNoObjAppium(String input){
		try {
			AppiumDriver driver = MobileDriverFactory.getDriver()
			info('Set Input field:' + input)
			Actions action = new Actions(driver);
			action.sendKeys(input).perform();
			KeywordUtil.markPassed("Enter text " + input + " successfully")
			return true
		} catch (Exception e) {
			System.out.print(e)
			KeywordUtil.markFailed("Can't enter text " + e.toString())
		}
		return false
	}

	/**
	 * WaitForElementNotPresent
	 * @param testObject
	 * @param timeout
	 * @return
	 */
	@Keyword
	static boolean waitForElementNotPresent(TestObject obj, int timeout, FailureHandling flowControl = FailureHandling.OPTIONAL) {
		info "waitForElementNotPresent for test object ${toStringAndLocatorTestObject(obj)}"
		if(Mobile.waitForElementNotPresent(obj, timeout, flowControl)) {
			return true
		}else {
			handleError("System Exception: Unable to find element ${toStringAndLocatorTestObject(obj)}", flowControl)
		}
		return false
	}

	/**
	 * Tap TestObject
	 * @param testObject
	 * @param timeout
	 * @return
	 */
	@Keyword
	static void tap(TestObject obj, int timeout, FailureHandling flowControl = defaultFailureHandling) {
		info "click for test object ${toStringAndLocatorTestObject(obj)}"
		try{
			Mobile.tap(obj, timeout, flowControl)
		}
		catch(ex){
			handleError("System Exception: ${toStringAndLocatorTestObject(obj) + ex.toString()}", flowControl)
		}
	}


	/**
	 * doubleTap TestObject
	 * @param testObject
	 * @param timeout
	 * @return
	 */
	@Keyword
	static void doubleTap(TestObject obj, int timeout, FailureHandling flowControl = defaultFailureHandling) {
		info "double click for test object ${toStringAndLocatorTestObject(obj)}"
		try{
			Mobile.doubleTap(obj, timeout, flowControl)
		}
		catch(ex){
			handleError("System Exception: ${toStringAndLocatorTestObject(obj) + ex.toString()}", flowControl)
		}
	}

	/**
	 * Tap TestObject
	 * @param testObject
	 * @param timeout
	 * @param log
	 * @return
	 */
	@Keyword
	static void tapAndLog(TestObject obj, int timeout, String log, FailureHandling flowControl = defaultFailureHandling) {
		info "click: ${log}"
		try{
			Mobile.tap(obj, timeout, flowControl)
		}
		catch(ex){
			handleError("System Exception: ${toStringAndLocatorTestObject(obj) + ex.toString()}", flowControl)
		}
	}

	/**
	 * Tap at Position
	 * @param testObject
	 * @param timeout
	 * @return
	 */
	@Keyword
	static void tapAtPosition(TestObject obj, int timeout, FailureHandling flowControl = defaultFailureHandling) {
		info "Set text for test object ${toStringAndLocatorTestObject(obj)}"
		try{
			if(Mobile.waitForElementPresent(obj, timeout, FailureHandling.OPTIONAL)) {
				def positionX = Mobile.getAttribute(obj, 'x', 0)
				def positionY = Mobile.getAttribute(obj, 'y', 0)
				Mobile.tapAtPosition(Integer.parseInt(positionX), Integer.parseInt(positionY), flowControl)
			}else {
				handleError("System Exception: Unable to find element ${toStringAndLocatorTestObject(obj)}", flowControl)
			}
		}
		catch(ex){
			handleError("System Exception: tapAtPosition ${toStringAndLocatorTestObject(obj)}" + ex.toString(), flowControl)
		}
	}
	/**
	 * Tap at Position with Offset
	 * @param testObject The Test Object to locate the element
	 * @param timeout The maximum time to wait for the element to be present
	 * @param offsetX The X-axis offset for the tap position
	 * @param offsetY The Y-axis offset for the tap position
	 * @param flowControl Optional FailureHandling for error control
	 */
	@Keyword
	static void tapAtPositionWithOffset(TestObject obj, int timeout, int offsetX = 0, int offsetY = 0, FailureHandling flowControl = defaultFailureHandling) {
		info "Attempting to tap at position with offset for test object ${toStringAndLocatorTestObject(obj)}"
		try {
			if (Mobile.waitForElementPresent(obj, timeout, FailureHandling.OPTIONAL)) {
				def positionX = Mobile.getAttribute(obj, 'x', 0)
				def positionY = Mobile.getAttribute(obj, 'y', 0)

				int finalX = Integer.parseInt(positionX) + offsetX
				int finalY = Integer.parseInt(positionY) + offsetY

				int screenWidth = Mobile.getDeviceWidth()
				int screenHeight = Mobile.getDeviceHeight()

				if (finalX > screenWidth) {
					finalX = screenWidth - 1
				} else if (finalX < 0) {
					finalX = 0
				}

				if (finalY > screenHeight) {
					finalY = screenHeight - 1
				} else if (finalY < 0) {
					finalY = 0
				}

				info "Final position with offset - X: ${finalX}, Y: ${finalY}"

				Mobile.tapAtPosition(finalX, finalY, flowControl)
			} else {
				handleError("System Exception: Unable to find element ${obj}", flowControl)
			}
		} catch (ex) {
			handleError("System Exception: tapAtPositionWithOffset ${toStringAndLocatorTestObject(obj)}" + ex.toString(), flowControl)
		}
	}

	@Keyword
	public static void setTextAndDone(TestObject obj, String textContent, int timeout,
									  FailureHandling flowControl = defaultFailureHandling) {
	
		info "Set text and press Done for ${toStringAndLocatorTestObject(obj)}"
	
		try {
			if (Mobile.waitForElementPresent(obj, timeout, FailureHandling.OPTIONAL)) {
	
				Mobile.tap(obj, timeout, flowControl)
				Mobile.setText(obj, textContent, timeout, flowControl)
	
				// Trigger IME Done action (Appium 2 & 3 compatible)
				driver.executeScript("mobile: performEditorAction", [action: "done"])
	
			} else {
				handleError("System Exception: Unable to find element ${obj}", flowControl)
			}
		}
		catch (ex) {
			handleError("System Exception: setTextAndDone ${toStringAndLocatorTestObject(obj)} " + ex.toString(), flowControl)
		}
	}

	/**
	 * Set Text
	 * @param input
	 * @return
	 */
	@Keyword
	public static void setText(TestObject obj, String textContent, int timeout, FailureHandling flowControl = defaultFailureHandling) {
		info "Set text for test object ${obj}, text: ${textContent}"
		try{
			if(Mobile.waitForElementPresent(obj, timeout, FailureHandling.OPTIONAL)) {
				Mobile.tap(obj, timeout, flowControl)
				Mobile.hideKeyboard(FailureHandling.OPTIONAL)
				Mobile.setText(obj, textContent, timeout, flowControl)
				Mobile.hideKeyboard(FailureHandling.OPTIONAL)
				'Check isKeyboardShown'
				boolean isKeyboardShown = androidDriver.isKeyboardShown()
				if(isKeyboardShown) {
					Mobile.pressBack();
				}
			}else {
				handleError("System Exception: Unable to find element ${obj}", flowControl)
			}
		}
		catch(ex){
			handleError("System Exception: Unable to find element ${toStringAndLocatorTestObject(obj)}", flowControl)
		}
	}
	/**
	 * Set Text
	 * @param input
	 * @return
	 */
	@Keyword
	public static void checkHideKeyBoard(FailureHandling flowControl = defaultFailureHandling) {
				'Check isKeyboardShown'
				boolean isKeyboardShown = androidDriver.isKeyboardShown()
				if(isKeyboardShown) {
				Mobile.pressBack();
				}
	}
	/**
	 * Set Text Simply
	 * @param input
	 * @return
	 */
	@Keyword
	public static void setTextSimply(TestObject obj, String textContent, int timeout, FailureHandling flowControl = defaultFailureHandling) {
		info "Set text for test object ${obj}, text: ${textContent}"
		try{
			if(Mobile.waitForElementPresent(obj, timeout, FailureHandling.OPTIONAL)) {
				Mobile.setText(obj, textContent, timeout, flowControl)
				Mobile.hideKeyboard(FailureHandling.OPTIONAL)
			}else {
				handleError("System Exception: Unable to find element ${obj}", flowControl)
			}
		}
		catch(ex){
			handleError("System Exception: Unable to find element ${toStringAndLocatorTestObject(obj)}", flowControl)
		}
	}

	/**
	 * Set Encrypted Text
	 * @param input
	 * @return
	 */
	@Keyword
	static void setEncryptedText(TestObject obj, String textContent, int timeout, FailureHandling flowControl = defaultFailureHandling) {
		info "Set encrypted text for test object ${toStringAndLocatorTestObject(obj)}"
		try{
			Mobile.setEncryptedText(obj, textContent, timeout, flowControl)
			Mobile.hideKeyboard(FailureHandling.OPTIONAL)
			'Check isKeyboardShown'
			boolean isKeyboardShown = androidDriver.isKeyboardShown()
			if(isKeyboardShown) {
				Mobile.pressBack();
			}
		}
		catch(ex){
			handleError("System Exception: Unable to find element ${toStringAndLocatorTestObject(obj)}", flowControl)
		}
	}

	/**
	 * get Text
	 * @param Test Object
	 * @param timeout
	 * @return
	 */
	@Keyword

	public static String getText(TestObject obj, int timeout, FailureHandling flowControl = defaultFailureHandling){
		info "get Text for test object ${toStringAndLocatorTestObject(obj)}"

		try{
			return Mobile.getText(obj, timeout, flowControl)
		}
		catch(ex){
			handleError("System Exception: Unable to find element ${toStringAndLocatorTestObject(obj)}", flowControl)
		}
	}

	/**
	 * Scroll until element is visible
	 * @param obj TestObject
	 * @param maxSwipe number of swipe attempts
	 * @param directionUp true = scroll up, false = scroll down
	 */
	@Keyword
	static void scrollTo(TestObject obj, int maxSwipe = 5, boolean directionUp = true, FailureHandling flowControl = FailureHandling.STOP_ON_FAILURE) {
	
	    info "Scroll to element: ${obj}, directionUp: ${directionUp}"
	
	    int deviceHeight = Mobile.getDeviceHeight()
	    int deviceWidth = Mobile.getDeviceWidth()
	
	    int startX = deviceWidth / 2
	    int startY
	    int endY
	
	    if (directionUp) {
	        startY = (int)(deviceHeight * 0.7)
	        endY = (int)(deviceHeight * 0.3)
	    } else {
	        startY = (int)(deviceHeight * 0.3)
	        endY = (int)(deviceHeight * 0.7)
	    }
	
	    int count = 0
	
	    while (count < maxSwipe) {
	        if (Mobile.waitForElementPresent(obj, 2, FailureHandling.OPTIONAL)) {
	            info "Element found"
	            return
	        }
	
	        Mobile.swipe(startX, startY, startX, endY)
	        count++
	    }
	
	    handleError("Unable to find element after ${maxSwipe} swipes: ${obj}", flowControl)
	}

	@Keyword
	static void checkElementAndScroll(TestObject obj, int length = 50, boolean directionUp = true, FailureHandling flowControl = FailureHandling.OPTIONAL) {
		info "Scroll element: ${obj}, directionUp: ${directionUp}"

		try {
			if (Mobile.waitForElementPresent(obj, 1, FailureHandling.OPTIONAL)) {
				info "Element already visible. Skip scrolling."
				return
			}
			int deviceHeight = Mobile.getDeviceHeight()
			int deviceWidth  = Mobile.getDeviceWidth()
			int startX = deviceWidth / 2
			int startY
			int endY

			if (directionUp) {
				startY = (int)(deviceHeight * 0.7)
				endY   = startY - length
			} else {
				startY = (int)(deviceHeight * 0.3)
				endY   = startY + length
			}

			Mobile.swipe(startX, startY, startX, endY)
		}
		catch (ex) {
			handleError(
					"System Exception: Unable to scroll to element ${toStringAndLocatorTestObject(obj)}",
					flowControl
					)
		}
	}

	static List getPositionScrollDownDefault() {
		int w = Mobile.getDeviceWidth()
		int h = Mobile.getDeviceHeight()
		int startX = 40
		int startY = h * 0.7
		int endX = 40
		int endY = h * 0.1

		return [startX, startY, endX, endY]
	}

	static List getPositionScrollUpDefault() {
		int w = Mobile.getDeviceWidth()
		int h = Mobile.getDeviceHeight()
		int startX = 40
		int startY = h * 0.2
		int endX = 40
		int endY = h * 0.8

		return [startX, startY, endX, endY]
	}

	@Keyword
	def void scrollToTop(List scrollPosition=[]) {
		info('Start Scroll To Top')
		if(scrollPosition.isEmpty()) {
			scrollPosition = MobileDriver.getPositionScrollUpDefault()
		}

		int startX = scrollPosition.get(0)
		int startY = scrollPosition.get(1)
		int endX = scrollPosition.get(2)
		int endY = scrollPosition.get(3)

		def deviceHeight = Mobile.getDeviceHeight()

		def prevPageSource = ""
		while(true) {
			def currentPageSource = driver.getPageSource()
			if(prevPageSource.equals(currentPageSource)) {
				return;
			}
			prevPageSource = currentPageSource

			if(executionSetting.platform == PlatformMobileEnum.ANDROID) {
				Android.scrollToNotDirection(startX, startY, endX, endY)
			}else {
				Mobile.swipe(startX, startY, endX, endY)
			}
		}

		info('End Scroll To Top')
	}

	@Keyword
	static def void scrollToEnd() {
		def deviceHeight = Mobile.getDeviceHeight()

		def prevPageSource = ""
		while(true) {
			def currentPageSource = driver.getPageSource()
			if(prevPageSource.equals(currentPageSource)) {
				return;
			}
			prevPageSource = currentPageSource
			if(executionSetting.platform == PlatformMobileEnum.ANDROID) {
				Android.scrollToNotDirection(5, (deviceHeight / 3) as int, 5, 5)
			}else {
				Mobile.swipe(5, (deviceHeight / 3) as int, 5, 5)
			}
		}
		return
	}


	@Keyword
	static def void scrollToText(String text, FailureHandling flowControl = defaultFailureHandling) {
		info "Scroll text: ${text}"
		try {
			Mobile.scrollToText(text)
		} catch (Exception ex) {
			handleError("Unable to scroll to ${text}", flowControl)
		}
	}

	/**
	 * zoom in, zoom out
	 * @param TestObject
	 * @param scale
	 * @param velocity
	 * @return
	 */
	public static boolean pinchExecuteScript(TestObject to, Number scale = 2.0, Number velocity = 1.0) {
		MobileTestObject testObject = to
		WebElement element = null
		if(testObject) {
//			String locator = testObject.getMobileLocator()
			String locator = testObject.getLocator()
			if(locator) {
				element = driver.findElement(locator)
			}else {
				handleError('System Error: Not found testObject ' + testObject, FailureHandling.STOP_ON_FAILURE)
			}
		}
		Map<String, Object> params = new HashMap<>();
		if(element) {
			params.put("elementId", element.getId());
		}
		params.put("scale", scale);
		params.put("velocity", velocity);

		driver.executeScript("mobile: pinch", params);
	}

	public static String getLocator(TestObject to) {
		MobileTestObject testObject = to
		return testObject.getLocator()
	}

	public static void verifyZoomInOut(TestObject to, String desc, FailureHandling flowControl = defaultFailureHandling) {
		int beforeValue = Mobile.getElementLeftPosition(to, GlobalVariable.G_timeoutMedium, flowControl)
		MobileDriver.pinchExecuteScript(to, 2.0, 1.0)
		int afterValue = Mobile.getElementLeftPosition(to, GlobalVariable.G_timeoutMedium, flowControl)
		NumberAssert.isGreaterThan(beforeValue, afterValue, desc? desc: 'Verify zoom in')

		beforeValue = Mobile.getElementLeftPosition(to, GlobalVariable.G_timeoutMedium, flowControl)
		MobileDriver.pinchExecuteScript(to, 0.5, -1.0)
		afterValue = Mobile.getElementLeftPosition(to, GlobalVariable.G_timeoutMedium, flowControl)

		NumberAssert.isLessThan(beforeValue, afterValue, desc? desc: 'Verify zoom out')
	}



	public static delay(int delayTime, int divide = 30, int xPosition = 1, int yPosition = 100) {
		info('Delay: ' + delayTime)
		int addDelay = delayTime%divide
		int loopNumber = delayTime/divide
		for (int i = 0; i < loopNumber; i++) {
			Mobile.delay(divide)
			Mobile.tapAtPosition(xPosition, yPosition, FailureHandling.OPTIONAL)
		}
		if(addDelay != 0) {
			Mobile.delay(addDelay)
		}
	}

	@Keyword
	public static tapEleByTestObject(TestObject to) {
		String xpath = MobileDriver.getLocator(to)
		this.tapElemtByXml(xpath)
	}

	@Keyword
	public static boolean waitForElementByXpathTestObject(TestObject to) {
		String xpath = MobileDriver.getLocator(to)
		String strRegex = this.findElementByXml(xpath)
		if(strRegex) {
			return true
		}
		return false
	}

	public static boolean verifyAllItemExistByXML(List items, String xml) {
		return items.every {
			findElementByXml(it, xml)
		}
	}

	public static boolean verifyAnyItemExistByXML(List items, String xml) {
		return items.any{
			findElementByXml(it, xml)
		}
	}

	static String findElementByXml(String xpath, String xml = '', FailureHandling flowControl = defaultFailureHandling) {
		String partten = ''
		Number x1 = 0
		Number y1 = 0
		String strRegex = ''
		def xmlPageSource = xml
		if(xml == '') {
			xmlPageSource = driver.getPageSource()
		}
		// Convert to W3C Document
		def factory = DocumentBuilderFactory.newInstance()
		def builder = factory.newDocumentBuilder()
		Document doc = builder.parse(new ByteArrayInputStream(xmlPageSource.bytes))
		// Create XPath instance
		def xpathFactory = XPathFactory.newInstance()
		def xpathXml = xpathFactory.newXPath()
		// XPath expression to query elements
		def element = xpath
		def node = xpathXml.evaluate(element, doc, javax.xml.xpath.XPathConstants.NODE)
		partten = 'bounds.*?\\[.*?(?<data>.*?)\\]'
		return StringUtils.extractStrPattern(node.toString(), partten)
	}

	@Keyword
	public static tapElemtByXml(String xpath, String cacheKey = '', FailureHandling flowControl = defaultFailureHandling) {
		String partten = ''
		Number x1 = 0
		Number y1 = 0
		String strRegex = findElementByXml(xpath, cacheKey, flowControl)
		if(!strRegex) {
			handleError('System Error: Not found element by XPath: ' + xpath, flowControl)
		}
		Mobile.comment(strRegex)
		strRegex = strRegex.replace("bounds=\"[", "").trim()
		strRegex = strRegex.replace("]", "").trim()
		def splitStr = strRegex.split(",")
		x1 = Integer.parseInt(splitStr[0].trim())
		y1 = Integer.parseInt(splitStr[1].trim())
		Mobile.tapAtPosition(x1, y1)
	}

	@Keyword
	static void verifyMatch(TestObject testObject, String regexMatch, int timeout, FailureHandling flowControl = defaultFailureHandling) {
		info("Verify match ${testObject} ${regexMatch}")
		String actual = this.getText(testObject, timeout)
		if(actual.matches(regexMatch)) {
			info("'${actual}' matches '${regexMatch}' format.")
		}else {
			handleError("'${actual}' does not matches '${regexMatch}' format.", flowControl)
		}
	}

	static boolean customWaitForElementNotPresent(TestObject to, int timeout = GlobalVariable.G_timeoutLong, FailureHandling f = FailureHandling.OPTIONAL) {
		if(timeout <= 0)
			timeout = RunConfiguration.timeOut
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(GlobalVariable.G_timeoutShort))
		Date currentTime = Calendar.getInstance().getTime()
		Date endTime = currentTime
		Date startTime = currentTime
		println "Start time: ${currentTime}"

		use(TimeCategory){
			endTime = currentTime + timeout.seconds
		}
		println "End time: ${endTime}"
		WebElement element
		while(endTime.after(currentTime)){
			println "Loop try to find element with interval 500ms..."
			try {
				element = driver.findElement(this.getLocator(to))
				sleep(500)
			}catch(e) {
				//Not found element
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(RunConfiguration.getTimeOut()))
				info 'Wait element take time: ' + ((currentTime.getTime() - startTime.getTime())/ 1000)
				return true
			}
			currentTime = Calendar.getInstance().getTime()
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(RunConfiguration.getTimeOut()))
		info "Could found elements ${toStringAndLocatorTestObject(to)}"
		return false;
	}

	@Keyword
	public void close(appPackage){
		driver.terminateApp(appPackage)
	}

	@Keyword
	public static getPageSource(){
		return driver.getPageSource()
	}

	public enum ScrollDirectionEnum{
		UP, DOWN, LEFT, RIGHT
	}

	@Keyword
	public static int randomInteger(int minNumber,  int maxNumber) {
		int randomNumber = ThreadLocalRandom.current().nextInt(minNumber, maxNumber)
		return randomNumber;
	}

	@Keyword
	public static void enterPinNo(String pinNO) {
		//	if (MobileDriver.waitForElementPresent(findTestObject('Object Repository/Mobile/MAE/Android/PIN Screen/textEnterPin'), GlobalVariable.G_timeoutShort, FailureHandling.OPTIONAL)) {
		def length = pinNO.length()
		for (def i = 0; i < length; i++) {
			def TapNo = pinNO[i]
			Mobile.tap(findTestObject('Object Repository/Mobile/MAE/Dynamic/lblDynamic', [('text'): TapNo]),0)
			//}
			Mobile.delay(4)
			Mobile.takeScreenshot()
		}
	}

	/**
	 * swipeInTestObject: Swipe in Test Object (TestObject is iframe (ex: HorizontalScrollView, GroupView) swipe in rectangle)
	 * @return
	 */
	void swipeInTestObject(TestObject to, ScrollDirectionEnum direction,FailureHandling flowControl = defaultFailureHandling) {
		String leftStr = Mobile.getAttribute(to, 'x',GlobalVariable.G_timeoutLong, flowControl)
		String topStr = Mobile.getAttribute(to, 'y', GlobalVariable.G_timeoutLong, flowControl)
		int left = Integer.parseInt(leftStr)
		int top = Integer.parseInt(topStr)
		int width = Mobile.getElementWidth(to, GlobalVariable.G_timeoutLong, flowControl)
		int height = Mobile.getElementHeight(to, GlobalVariable.G_timeoutLong, flowControl)
		int startX
		int startY
		int endX
		int endY

		switch(direction) {
			case ScrollDirectionEnum.LEFT:
				"Swipe left -> right"
				startX = left + 1
				startY = top + height / 2
				endX = left + height - 1
				endY = startY
				break
			case ScrollDirectionEnum.RIGHT:
				"Swipe right -> left"
				startX = left + height - 1
				startY = top + height / 2
				endX = left + 1
				endY = startY
				break
			case ScrollDirectionEnum.UP:
				"Swipe up -> down"
				startX = left + height / 2
				startY = top + 1
				endX = startX
				endY = top + height
				break
			case ScrollDirectionEnum.DOWN:
				"Swipe down -> up"
				startX = left + height / 2
				startY = top + height - 1
				endX = startX
				endY = top
				break
		}
		//temp use Android.scrollToNotDirection after use Mobile.swipe when katalon fixed
		Android.scrollToNotDirection(startX, startY, endX, endY)
	}

	/*
	 * getElementLeftPosition: x coordinate of Element
	 */
	static int getElementLeftPosition(TestObject to, int timeout, FailureHandling flowControl = defaultFailureHandling) {
		int x = Mobile.getElementLeftPosition(to, timeout, flowControl)
		if(x > 0) {
			return x
		}

		return Integer.parseInt(Mobile.getAttribute(to, 'x', timeout, flowControl))
	}

	/*
	 * getElementTopPosition: y coordinate of Element
	 */
	static int getElementTopPosition(TestObject to, int timeout, FailureHandling flowControl = defaultFailureHandling) {
		int y = Mobile.getElementTopPosition(to, timeout, flowControl)
		if(y > 0) {
			return y
		}

		return Integer.parseInt(Mobile.getAttribute(to, 'y', timeout, flowControl))
	}
}
