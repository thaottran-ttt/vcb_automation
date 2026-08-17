package com.maybank.service

import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS


public class LogInService {
	@Keyword
	public String getToken(String apiKey) {
		ResponseObject res = WS.sendRequest(findTestObject('Object Repository/Katalon APIs/Get token', ['apiKey': apiKey]))
		WS.verifyResponseStatusCode(res, 200)
		return WS.getElementPropertyValue(res, 'data.jwt')
	}
}
