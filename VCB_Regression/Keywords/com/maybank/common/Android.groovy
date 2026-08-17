package com.maybank.common

import java.time.Duration

import org.openqa.selenium.JavascriptExecutor
import org.openqa.selenium.interactions.PointerInput
import org.openqa.selenium.interactions.Sequence;

import com.google.common.collect.ImmutableMap
import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.util.KeywordUtil

import io.appium.java_client.AppiumDriver
import io.appium.java_client.android.AndroidDriver
import io.appium.java_client.touch.WaitOptions
import io.appium.java_client.touch.offset.PointOption


public class Android extends MobileDriver {
	public static AppiumDriver driver = MobileDriverFactory.getDriver()

	/**
	 * Clear Android app data
	 * @return
	 */
	@Keyword
	def clearAppData(String appPackage) {
		String command = "mobile:shell"

		Map args = ImmutableMap.of("command", "pm clear " + appPackage)

		try {
			info('Clear app data for appPackage: ' + appPackage)
			Mobile.executeMobileCommand(command, args)
			markPassed("Clear app data successfully")
			return true
		} catch (Exception e) {
			KeywordUtil.markFailed("Can't clear app data" + e.toString())
		}
		return false
	}

	/**
	 * Close Android app
	 * @return
	 */
	@Keyword
	static def killApp(String appPackage) {
		String command = "mobile:shell"

		Map args = ImmutableMap.of("command", "am kill  " + appPackage)

		try {
			info('Close appPackage: ' + appPackage)
			Mobile.executeMobileCommand(command, args)
			markPassed("Close app successfully")
			return true
		} catch (Exception e) {
			KeywordUtil.markFailed("Close app " + e.toString())
		}
		return false
	}

	/**
	 * Set Text
	 * @param to
	 * @param input
	 * @param timeout
	 * @return
	 */
	@Keyword
	public static setText(TestObject to, String input, int timeout, flowControl){
		def command = "mobile:shell"
		def list = ['text', input].asImmutable()
		Map<String, Object> args = new HashMap<>()
		args.put("command", "input")
		args.put("args", list)

		try {
			info("Finding element with id:" + to.getObjectId())

			info('Tap Input field')
			tap(to, timeout, flowControl)

			info('Set Input field:' + input)
			Mobile.executeMobileCommand(command, args)

			markPassed("Enter text " + input + " successfully")
			return true
		} catch (Exception e) {
			KeywordUtil.markFailed("Can't enter text " + e.toString())
		}
		return false
	}

	/**
	 * Set Text
	 * @param input
	 * @param timeout
	 * @return
	 */
	@Keyword
	public static setTextNoObject(String input, int timeout, flowControl){
		def command = "mobile:shell"
		def list = ['keyevent', input].asImmutable()
		Map<String, Object> args = new HashMap<>()
		args.put("command", "input")
		args.put("args", list)

		try {
			info('Set Input field:' + input)
			Mobile.executeMobileCommand(command, args)

			markPassed("Enter text " + input + " successfully")
			return true
		} catch (Exception e) {
			System.out.print(e)
			handleError("Can't enter text " + e.toString(), FailureHandling.STOP_ON_FAILURE)
		}
		return false
	}

	@Keyword
	public static executeADBCommand(String command) {
		try {
			Runtime.getRuntime().exec(command)
		} catch (Exception e) {
			handleError('Cannot run command: ' + command, FailureHandling.STOP_ON_FAILURE)
		}
	}

	@Keyword
	public static setTextToClipboard(String text) {
		try {
			AndroidDriver androidDriver = MobileDriverFactory.getDriver()

			androidDriver.setClipboardText(text)
		} catch (Exception e) {
			handleError('Cannot set text to Clipboard:' + text, FailureHandling.STOP_ON_FAILURE)
		}
	}

	@Keyword
	public static boolean scrollToTestObject(TestObject grid, TestObject findObj, ScrollDirectionEnum direction, int timeout, FailureHandling flowControl = defaultFailureHandling, boolean flexibleDeivce = false) {
		if(waitForElementPresent(findObj, timeout, FailureHandling.OPTIONAL)) {
			if (flexibleDeivce) {
				moveElement(grid, findObj, direction, timeout, flowControl)
			}
			return true;
		}
		if(!waitForElementPresent(grid, timeout, FailureHandling.OPTIONAL)) {
			handleError("System Error: scrollTo not found element " + grid, flowControl)
			return false
		}
		int left = Mobile.getElementLeftPosition(grid, timeout, flowControl)
		int top = Mobile.getElementTopPosition(grid, timeout, flowControl)
		int xWidth = Mobile.getElementWidth(grid, timeout, flowControl)
		int yHeight = Mobile.getElementHeight(grid, timeout, flowControl)

		int startX
		int startY
		int endX
		int endY

		switch(direction){
			case ScrollDirectionEnum.UP:
				startX = left + xWidth / 2
				startY = top
				endX = startX
				endY = top + yHeight - 1
				break
			case ScrollDirectionEnum.DOWN:
				startX = left + xWidth / 2
				startY = top + yHeight - 1
				endX = startX
				endY = top
				break
			case ScrollDirectionEnum.LEFT:
				startX = left
				startY = top + yHeight / 2
				endX = left + xWidth - 1
				endY = startY
				break
			case ScrollDirectionEnum.RIGHT:
				startX = left + xWidth - xWidth/10
				startY = top + yHeight / 2
				endX = left
				endY = startY
				break
			default:
				handleError("System Error: direction scrollto " + direction, flowControl)
				break
		}

		def prevPageSource = driver.getPageSource()
		int maxTries = 10
		int attempts = 0

		while (attempts < maxTries) {
			scrollToNotDirection(startX, startY, endX, endY)
			def currentPageSource = driver.getPageSource()

			if (prevPageSource.equals(currentPageSource)) {
				return false
			}

			prevPageSource = currentPageSource

			if (waitForElementPresent(findObj, timeout)) {
				if (flexibleDeivce) {
					moveElement(grid, findObj, direction, timeout, flowControl)
				}
				return true
			}

			attempts++
		}

		return false
	}

	@Keyword
	public static swipe(float startXPercentage, float endXPercentage) {

		int startY = Mobile.getDeviceWidth()/2;
		int endY = Mobile.getDeviceWidth()/2;
		int startX = Mobile.getDeviceHeight()*startXPercentage;
		int endX = Mobile.getDeviceHeight()*endXPercentage;

		scrollByTouchAction(startX, startY, endX, endY, 3000)
	}

	@Keyword
	public static boolean scrollByW3CAction(int startX, int startY, int endX, int endY, int waitTime = 500) {
		MobileDriver.info("startX, startY, endX, endY: ${startX} ${startY} ${endX} ${endY}");

		try {
			PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");

			Sequence dragAndDrop = new Sequence(finger, 1);
			dragAndDrop.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, startY));
			dragAndDrop.addAction(finger.createPointerDown(0));
			dragAndDrop.addAction(finger.createPointerMove(Duration.ofMillis(waitTime), PointerInput.Origin.viewport(), endX, endY));
			dragAndDrop.addAction(finger.createPointerUp(0));

			driver.perform(Arrays.asList(dragAndDrop));
			return true;
		} catch (Exception e) {
			MobileDriver.info("Scroll action failed: " + e.getMessage());
			return false;
		}
	}

	@Keyword
	public static boolean scrollToNotDirection(int startX, int startY, int endX, int endY) {
		MobileDriver.info("startX, startY, endX, endY: ${startX} ${startY} ${endX} ${endY}")
		if (startX == endX && startY == endY) {
			return false
		}

		if (startX != endX && startY != endY) {
			return false
		}

		String direction
		if (startX != endX) {
			direction = startX > endX ? 'right' : 'left'
		} else {
			direction = startY > endY ? 'down' : 'up'
		}

		int minX = Math.min(startX, endX)
		int minY = Math.min(startY, endY)
		int maxX = Math.max(startX, endX)
		int maxY = Math.max(startY, endY)

		int width = Math.max(maxX - minX, 1)
		int height = Math.max(maxY - minY, 1)

		scrollToJavascript(minX, minY, width, height, direction)
		return true
	}

	@Keyword
	public static boolean scrollToJavascript(int left, int top, int width, int height, direction = 'down') {
		MobileDriver.info("scrollToJavascript: ${left} ${top} ${width} ${height} ${direction}")
		((JavascriptExecutor) driver).executeScript("mobile: scrollGesture", Map.of(
				"left", left, "top", top,
				"width", width, "height", height,
				"direction", direction,
				"percent", 1,
				"speed", 1000
				));
	}

	static void clearCache(String appID) {
		String adbPath = '/opt/homebrew/bin/adb '
		this.executeADBCommand(adbPath + "shell pm clear " + appID)
	}
	static void moveElement(TestObject grid, TestObject findObj, ScrollDirectionEnum direction, int timeout, FailureHandling flowControl = defaultFailureHandling) {

		// Get device dimensions (width and height)
		int deviceWidth = Mobile.getDeviceWidth();
		int deviceHeight = Mobile.getDeviceHeight();

		// Get the position and dimensions of the target element
		int elementLeft = Mobile.getElementLeftPosition(findObj, timeout, flowControl);
		int elementTop = Mobile.getElementTopPosition(findObj, timeout, flowControl);
		int elementWidth = Mobile.getElementWidth(findObj, timeout, flowControl);
		int elementHeight = Mobile.getElementHeight(findObj, timeout, flowControl);

		// Get the position and dimensions of the grid (scrollable container)
		int gridLeft = Mobile.getElementLeftPosition(grid, timeout, flowControl);
		int gridTop = Mobile.getElementTopPosition(grid, timeout, flowControl);
		int gridWidth = Mobile.getElementWidth(grid, timeout, flowControl);
		int gridHeight = Mobile.getElementHeight(grid, timeout, flowControl);
		int startX
		int startY
		int endX
		int endY
		// Define the distance to scroll (1/6th of the grid's width/height)
		int scrollDistanceX = gridWidth / 6;
		int scrollDistanceY = gridHeight / 6;

		switch (direction) {
			case ScrollDirectionEnum.UP:
			// Calculate offsets for determining if the element is near the top edge
				int offsetTop_1 = gridTop + gridHeight / 10; // Relative to grid
				int offsetTop_2 = deviceHeight / 10;         // Relative to screen
				if (elementTop <= Math.min(offsetTop_1, offsetTop_2)) {
					// Element is too close to the top edge
					startX = gridLeft + gridWidth / 2;  // Middle of the grid horizontally
					startY = gridTop;                  // Top of the grid
					endX = startX;                     // Keep horizontal position
					endY = gridTop + scrollDistanceY;  // Scroll down slightly
					scrollToNotDirection(startX, startY, endX, endY)
				}
				break;

			case ScrollDirectionEnum.DOWN:
			// Calculate offsets for determining if the element is near the bottom edge
				int offsetBottom_1 = gridTop + gridHeight - gridHeight / 10; // Relative to grid
				int offsetBottom_2 = deviceHeight - deviceHeight / 10;      // Relative to screen
				if (elementTop + elementHeight >= Math.min(offsetBottom_1, offsetBottom_2)) {
					// Element is too close to the bottom edge
					startX = gridLeft + gridWidth / 2;             // Middle of the grid horizontally
					startY = gridTop + gridHeight - 1;            // Bottom of the grid
					endX = startX;                                // Keep horizontal position
					endY = gridTop + gridHeight - scrollDistanceY; // Scroll up slightly
					scrollToNotDirection(startX, startY, endX, endY);         // Perform the scroll
				}
				break;

			case ScrollDirectionEnum.LEFT:
			// Calculate offsets for determining if the element is near the left edge
				int offsetLeft_1 = gridLeft + gridWidth / 10; // Relative to grid
				int offsetLeft_2 = deviceWidth / 10;         // Relative to screen
				if (elementLeft <= Math.min(offsetLeft_1, offsetLeft_2)) {
					// Element is too close to the left edge
					startX = gridLeft;                       // Leftmost position of the grid
					startY = gridTop + gridHeight / 2;       // Middle of the grid vertically
					endX = gridLeft + scrollDistanceX;       // Scroll right slightly
					endY = startY;                          // Keep vertical position
					scrollToNotDirection(startX, startY, endX, endY);   // Perform the scroll
				}
				break;

			case ScrollDirectionEnum.RIGHT:
			// Calculate offsets for determining if the element is near the right edge
				int offsetRight_1 = gridLeft + gridWidth - gridWidth / 10; // Relative to grid
				int offsetRight_2 = deviceWidth - deviceWidth / 10;       // Relative to screen
				if (elementLeft + elementWidth >= Math.min(offsetRight_1, offsetRight_2)) {
					// Element is too close to the right edge
					startX = gridLeft + gridWidth;              // Rightmost position of the grid
					startY = gridTop + gridHeight / 2;          // Middle of the grid vertically
					endX = gridLeft + gridWidth - scrollDistanceX; // Scroll left slightly
					endY = startY;                             // Keep vertical position
					scrollToNotDirection(startX, startY, endX, endY);      // Perform the scroll
				}
				break;

			default:
				break;
		}
	}
}
