package com.vcb.utils

import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static java.util.Arrays.asList

import java.time.Duration
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.interactions.Pause
import org.openqa.selenium.interactions.PointerInput
import org.openqa.selenium.interactions.Sequence

import com.google.gson.JsonObject
import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import io.appium.java_client.android.AndroidDriver
import io.appium.java_client.touch.WaitOptions
import io.appium.java_client.touch.offset.PointOption

public class TouchActionUtils {
	static AndroidDriver driver = MobileDriverFactory.getDriver()

	@Keyword
	public compareUIFieldWithResponseField(List<Map> compareArr, JsonObject data, FailureHandling controlFlow = FailureHandling.STOP_ON_FAILURE) {

		compareArr.each{ map ->
			def mapExpectedProp = map["expected"].toString()

			def expectedProps = mapExpectedProp.split("-")

			def expectedValue = ""
			expectedProps.eachWithIndex ({ prop,index ->
				if(index == (expectedProps.length-1)) {
					expectedValue += data[prop].toString()
				}
				else {
					expectedValue += data[prop].toString() + " "
				}
			})

			def reponseFieldValue=expectedValue.toString().replaceAll('"','')
			def value = WebUI.getText(findTestObject(map.actual), controlFlow)
			def uiFieldValue = value.contains('$') && value.contains(',') ? value.replace('$', '').replace(',', '') :
					value.contains(' months') ? value.replace(' months', '') :
					value
			WebUI.verifyEqual(uiFieldValue, reponseFieldValue, controlFlow)
		}
	}

	@Keyword
	def updateAndVerifyElements(List<String> lst, String pathObject, int timeout) {
		print(pathObject)
		lst.each { item ->
			def variable = item
			WebUI.verifyElementPresent(findTestObject(pathObject, [('label') : variable]), timeout,FailureHandling.CONTINUE_ON_FAILURE)
		}
	}

	@Keyword
	public static boolean patternChecking(String testString) {
		String regex = '^RM\\s\\d{1,3}(?:,\\d{3})*(?:\\.\\d{2})';
		Pattern pattern = Pattern.compile(regex);

		Matcher matcher = pattern.matcher(testString);
		System.out.println("Test String \"" + testString + "\" matches: " + matcher.matches());
		return matcher
	}


	static void tapTwoFingerW3CAction() {
		def device_Height = Mobile.getDeviceHeight()
		def device_Width = Mobile.getDeviceWidth()

		int X1 = device_Width - 150
		int Y1 = 300

		int X2 = X1 + 100
		int Y2 = Y1

		// Define two fingers
		PointerInput finger1 = new PointerInput(PointerInput.Kind.TOUCH, "finger1")
		PointerInput finger2 = new PointerInput(PointerInput.Kind.TOUCH, "finger2")

		// Finger 1 action sequence
		Sequence finger1Sequence = new Sequence(finger1, 0)
		finger1Sequence.addAction(finger1.createPointerMove(Duration.ofMillis(0), PointerInput.Origin.viewport(), X1, Y1))
		finger1Sequence.addAction(finger1.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
		finger1Sequence.addAction(new Pause(finger1, Duration.ofMillis(3000)))
		finger1Sequence.addAction(finger1.createPointerUp(PointerInput.MouseButton.LEFT.asArg()))

		// Finger 2 action sequence
		Sequence finger2Sequence = new Sequence(finger2, 0)
		finger2Sequence.addAction(finger2.createPointerMove(Duration.ofMillis(0), PointerInput.Origin.viewport(), X2, Y2))
		finger2Sequence.addAction(finger2.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
		finger2Sequence.addAction(new Pause(finger2, Duration.ofMillis(3000)))
		finger2Sequence.addAction(finger2.createPointerUp(PointerInput.MouseButton.LEFT.asArg()))

		// Perform both actions
		driver.perform(asList(finger1Sequence, finger2Sequence))
	}
}
