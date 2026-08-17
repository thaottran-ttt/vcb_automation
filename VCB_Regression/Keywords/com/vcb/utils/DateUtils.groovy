package com.vcb.utils

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import java.text.DateFormatSymbols
import java.text.SimpleDateFormat

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
import com.vcb.common.BaseKeyword
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import internal.GlobalVariable

public class DateUtils extends BaseKeyword{

	@Keyword
	public static def dateValidator(String dateString, String periodString, int plusDay) {
		String msgDateValidator = ""
		LocalDate dateToValidate
		DateTimeFormatter formatter1ChrDay = DateTimeFormatter.ofPattern("d MMM yyyy");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy");
		// Parse the input date string
		if (dateString.length() == 10) {
			LocalDate date = LocalDate.parse(dateString, formatter1ChrDay);
			String outputDate = date.format(formatter);
			dateToValidate = LocalDate.parse(outputDate, formatter);
		}else {
			dateToValidate = LocalDate.parse(dateString, formatter);
		}

		// Get today's date
		LocalDate today = LocalDate.now();

		// Define the date 30 days from today  dateToValidate.isBefore(today)
		LocalDate thirtyDaysFromToday = today.plusDays(plusDay);

		// Check if the date to validate is within the next 30 days
		if (dateToValidate.isAfter(thirtyDaysFromToday) && dateToValidate.isBefore(today)) {
			msgDateValidator = "The date " + dateString + " is within " + periodString+ "."
			System.out.println(msgDateValidator);
		} else {
			msgDateValidator = "The date " + dateString + " is not within " + periodString+ "."
			System.out.println(msgDateValidator);
		}

		return msgDateValidator
	}

	@Keyword
	public static def convertStringToCalendar(String pattern, String dateString) {
		def dateFormat = new SimpleDateFormat(pattern)

		def date = dateFormat.parse(dateString)

		def calendar = Calendar.instance

		calendar.time = date
		return calendar
	}

	@Keyword
	public static def getCurrentCalendar(String timeZone = "Asia/Kuala_Lumpur") {
		TimeZone malaysiaTimeZone = TimeZone.getTimeZone(timeZone)

		Calendar calendar = Calendar.getInstance(malaysiaTimeZone)

		return calendar;
	}

	@Keyword
	public static String getCurrentDate(String timeZone = "Asia/Kuala_Lumpur", String format = 'dd-MM-yyyy HH:mm') {
		//String format = 'dd/MM/yyyy'
		TimeZone getTimeZone = TimeZone.getTimeZone(timeZone)
		SimpleDateFormat pattern = new SimpleDateFormat(format)
		Calendar now = Calendar.getInstance(getTimeZone)
		pattern.setCalendar(now)
		def currentDate = pattern.format(now.getTime())
		info("Current Time: " + currentDate)
		return currentDate
	}

	@Keyword
	public static String convertToFormat(String dateInStr, String fromFormat = "yyyy-MM-dd",
			String toFormat='DD MMM YYY', FailureHandling controlFlow = FailureHandling.STOP_ON_FAILURE) {
		info("Convert datetime value to format ${toFormat}")
		try {
			SimpleDateFormat inputFormat = new SimpleDateFormat(fromFormat)
			Date date = inputFormat.parse(dateInStr)
			info("converted date ${date.format(toFormat)}")
			return date.format(toFormat)
		}catch(Exception ex) {
			handleError(ex, controlFlow)
		}
	}

	@Keyword
	public static def getCurrentDateFormat(String pattern = 'd MMM yyyy', int daysToAdd= 1) {
		LocalDate today = LocalDate.now() + daysToAdd
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern)
		String formattedDate = today.format(formatter)
		return formattedDate
	}

	@Keyword
	public static def getCurrentMonth() {
		return Calendar.getInstance().toMonth().toString().subSequence(0, 3);
	}

	enum Periods {
		DAY,
		WEEK,
		MONTH,
		QUARTER,
		YEAR
	}

	def getDateAfter(Periods period, int numberAdd, LocalDate beginDate = LocalDate.now(), String datePattern = "dd-MMM-yyyy") {
		LocalDate futureDate = LocalDate.now()
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(datePattern)
		switch(period){
			case period.DAY:
				futureDate = beginDate.plusDays(numberAdd)
				break
			case period.WEEK:
				futureDate = beginDate.plusWeeks(numberAdd)
				break
			case period.MONTH:
				futureDate = beginDate.plusMonths(numberAdd)
				break
			case period.QUARTER:
				futureDate = beginDate.plusMonths(numberAdd*3)
				break
			case period.YEAR:
				futureDate = beginDate.plusYears(numberAdd)
				break
		}
		def formattedFutureDate = futureDate.format(formatter)
		info("Date after is ${formattedFutureDate}")
		return formattedFutureDate
	}

	static boolean isValidDate(String dateStr, String pattern = "dd MMM yyyy") {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern, Locale.ENGLISH)

		try {
			LocalDate.parse(dateStr, formatter)
			return true
		} catch (DateTimeParseException e) {
			return false
		}
	}
}