package com.maybank.utils

import java.text.DecimalFormat
import java.text.NumberFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import java.util.regex.Matcher
import java.util.regex.Pattern

import com.kms.katalon.core.annotation.Keyword
import com.maybank.m2u.internal.pages.base.HTMLTag


public class StringUtils {

	static final String DECIMAL_FORMAT = "#,##0.00"
	/**
	 * Parse an amount as text in ibank app to a long number
	 * @param amount in String
	 * @return amount in Long
	 */
	@Keyword
	static def parseAmountStringToIntegerInApp(String amount) {
		try {
			def result = Long.parseLong(amount.replaceAll('\\D+', ''))
			return result
		} catch (Exception ex) {
			return 0L
		}
	}

	/**
	 * Using Regex to extract string
	 * @param String and pattern
	 * @return string mattched
	 */
	@Keyword
	static def extractStrPattern(String str, String pattern, boolean allString = false) {
		try {

			Pattern MY_PATTERN = Pattern.compile(pattern);
			Matcher m = MY_PATTERN.matcher(str);
			List results  = []
			while (m.find()) {
				if(!allString) {
					String s = m.group(0);
					// s now contains "BAR"
					return s.toString()
				}
				results.add(m.group());
			}

			return results.join('')
		} catch (Exception ex) {
			return ex.toString()
		}
	}
	@Keyword
	static String extractNumber(def balance) {
		try {
			//replace , and . please dotnot change
			String balanceNumber = balance.toString().replace(',', '').replace('.', '')

			return extractStrPattern(balanceNumber, '(\\d+)')
		} catch (Exception ex) {
			return ex.toString()
		}
	}

	/*
	 * Create a string with 8 Character
	 * */
	@Keyword
	static def createStrRandom(int length) {
		String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
		Random random = new Random();
		StringBuilder builder = new StringBuilder(length);
		for (int i = 0; i < length; i++) {
			int index = random.nextInt(alphabet.length());
			builder.append(alphabet.charAt(index));
		}
		return builder.toString();
	}

	/*
	 * Create a number random by length
	 * */
	@Keyword
	static def createNumberRandom(int length) {
		String alphabet = "0123456789";
		Random random = new Random();
		StringBuilder builder = new StringBuilder(length);
		for (int i = 0; i < length; i++) {
			int index = random.nextInt(alphabet.length());
			builder.append(alphabet.charAt(index));
		}
		return builder.toString();
	}

	static def generateMalaysiaMobileNumber(String prefix = "17") {
		int remainingLength = 9 - prefix.length()
		String digits = "0123456789"
		Random random = new Random()
		StringBuilder builder = new StringBuilder(prefix)
		for (int i = 0; i < remainingLength; i++) {
			int index = random.nextInt(digits.length())
			builder.append(digits.charAt(index))
		}
		return builder.toString()
	}

	static def generateRandomNameWithNumber(int totalLength = 10) {
		String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789"
		Random random = new Random()
		StringBuilder builder = new StringBuilder()

		for (int i = 0; i < totalLength; i++) {
			int index = random.nextInt(alphabet.length())
			builder.append(alphabet.charAt(index))
		}

		return builder.toString()
	}

	/*
	 *
	 * */
	@Keyword
	public static String concatString(String str1, String str2, String con){
		if (str1.equals("")) {
			return str2
		}else {
			return str1 + con + str2
		}
	}


	@Keyword
	static BigInteger convertBalanceAmountNumber(String amountInput) {
		try {
			String amountStr = extractNumber(amountInput)

			BigInteger amountNumber = new BigInteger(amountStr)

			return amountNumber
		}
		catch (Exception ex) {
			return ex.toString()
		}
	}

	@Keyword
	static String formatToMoney(String numberStr) {
		try {
			// Convert the string to a double and divide by 100 to account for cents
			double number = numberStr.toDouble() / 100

			// Create a formatter for currency in the default locale
			NumberFormat formatter = NumberFormat.getCurrencyInstance(Locale.US)

			// Remove the currency symbol
			if (formatter instanceof DecimalFormat) {
				((DecimalFormat) formatter).negativePrefix = ""
				((DecimalFormat) formatter).negativeSuffix = ""
				((DecimalFormat) formatter).positivePrefix = ""
				((DecimalFormat) formatter).positiveSuffix = ""
			}

			// Format the number and return it as a string
			return formatter.format(number)
		} catch (NumberFormatException e) {
			// Handle invalid input
			return "Invalid number format"
		}
	}

	/* example data 'RM xxx'*/
	public static String extractMoney(String currencyString) {
		def parts = currencyString?.trim().split(' ')
		if (parts.size() > 1) {
			return parts.last()
		} else {
			throw new IllegalArgumentException("Invalid currency string format")
		}
	}

	@Keyword
	public boolean verifyDataFormat(String valueInput, String verifyPattern = "(\\\$(([1-9]\\d{0,2})((\\,\\d{3}))+))|(\\\$[1-9]\\d{0,2})") {
		Pattern pattern = Pattern.compile(verifyPattern, Pattern.CASE_INSENSITIVE)
		Matcher matcher = pattern.matcher(valueInput)
		return matcher.matches()
	}

	@Keyword
	public static boolean verifyDateTimeFormat(String valueInput, String verifyPattern = "(0?[1-9]|[12][0-9]|3[01])\\s[A-Z]{1}[a-z]{2}\\s[0-9]{4},\\s(0?[0-9]|1[0-9]|2[0-3]):[0-9]{2}\\s[A|P]M") {
		Pattern pattern = Pattern.compile(verifyPattern, Pattern.CASE_INSENSITIVE)
		Matcher matcher = pattern.matcher(valueInput)
		return matcher.matches()
	}

	public static boolean isAccountNumber(String valueInput, String verifyPattern = /^\d{4} \d{4} \d{4}$/) {
		Pattern pattern = Pattern.compile(verifyPattern, Pattern.CASE_INSENSITIVE)
		Matcher matcher = pattern.matcher(valueInput)
		return matcher.matches()
	}

	public static List<String> extractValuesListView(List<List<String>>inputList, int index) {
		return inputList.collect { it[index] }
	}

	public static String convertNoStringToAmount(String number) {
		return "RM " + String.format("%.2f", Double.parseDouble(number)/100)
	}

	public static String divideBy100(String number) {
		return String.format("%.2f", Double.parseDouble(number) / 100);
	}

	static String generateMessage(String message, List<String> params) {
		params.eachWithIndex { param, index ->
			message = message.replaceAll("\\\$\\{param${index + 1}\\}", param)
		}
		return message
	}

	static def escapeForXPath(String text) {
		text.replaceAll(/([\(\)\[\]\{\}\+\*\?\.\^\$\|\\])/, '\\\\$1')
	}

	/**
	 * genarate a Nric for person over 18+
	 */
	@Keyword
	def generateRandomValidNRIC() {
		Random random = new Random()

		// Generate a valid birthdate between 1981 and 1995
		def startDate = LocalDate.of(1981, 1, 1)
		def endDate = LocalDate.of(1995, 12, 31)
		long daysBetween = ChronoUnit.DAYS.between(startDate, endDate)
		def randomDate = startDate.plusDays(random.nextInt((int) daysBetween + 1))

		// Format to YYMMDD
		def formatter = DateTimeFormatter.ofPattern("yyMMdd")
		def birthPart = randomDate.format(formatter)

		// Kuala Lumpur code: 13 or 14
		def klCode = random.nextBoolean() ? "13" : "14"

		// Random digit from 5 to 9
		def randomDigit = random.nextInt(5) + 5

		// Last 3 digits: 000 to 999
		def lastThreeDigits = String.format("%03d", random.nextInt(1000))

		return "${birthPart}${klCode}${randomDigit}${lastThreeDigits}"
	}
	@Keyword
	def generateRandomPassport(int length) {
		// Ensure the total length must be at least 2 for 2 uppercase characters
		if (length < 1 || length > 20) {
			throw new IllegalArgumentException("Length must be between 1 and 20")
		}

		// Define the characters to use: uppercase A-Z for letters and digits 0-9 for numbers
		String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
		String numbers = "0123456789"
		Random random = new Random()
		StringBuilder passportNumber = new StringBuilder()

		// Determine how many uppercase letters to generate (1 or 2)
		int letterCount = random.nextInt(2) + 1 // Will be either 1 or 2

		// Generate the uppercase letters
		for (int i = 0; i < letterCount; i++) {
			int index = random.nextInt(letters.length())
			passportNumber.append(letters.charAt(index))
		}

		// Generate the remaining characters (length - letterCount) as numbers
		for (int i = letterCount; i < length; i++) {
			int index = random.nextInt(numbers.length())
			passportNumber.append(numbers.charAt(index))
		}

		return passportNumber.toString()
	}

	static String getNegativeRMAmount(String amount) {
		try {
			double value = Double.parseDouble(amount) / 100;
			DecimalFormat df = new DecimalFormat(DECIMAL_FORMAT);
			return "-RM " + df.format(value);
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException("Invalid amount: " + amount, e);
		}
	}

	static def containsSpecialCharacter(String text) {
		return text.contains("'")
	}

	static def convertToXPathConcat(String text) {
		if (!text.contains("'")) {
			return "'${text}'"
		}
		return "concat('" + text.replaceAll("'", "', \"'\", '") + "')"
	}

	static def toStringHTMLTag(HTMLTag tag) {
		if(tag == HTMLTag.empty) {
			return '*'
		}
		return tag.toString()
	}
	
	static String addWhiteSpaceForString(String input, int interval) {
    String output = input.replaceAll("(.{" + interval + "})(?=.)", "\$1 ");
    return output
}

}