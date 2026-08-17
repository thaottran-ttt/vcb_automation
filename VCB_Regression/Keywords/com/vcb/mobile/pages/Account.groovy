package com.vcb.mobile.pages

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

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
import com.vcb.mobile.internal.pages.base.BasePage
import com.vcb.mobile.internal.pages.base.CommonPage
import com.vcb.mobile.internal.pages.base.HomePage
import com.vcb.assertions.BaseAssert
import com.vcb.common.BaseKeyword
import com.vcb.common.MobileDriver
import com.vcb.mobile.internal.pages.base.accountpage.AccountDetailPage
import com.vcb.mobile.internal.pages.base.accountpage.AccountsPage
import com.vcb.utils.StringUtils

import internal.GlobalVariable


public class Account extends BaseKeyword{
	
	static final String BALANCE = 'balance'
	static final String ACCOUNT_NO = 'accountNo'
	AccountDetailPage accountDetailPage = BasePage.getPage(AccountDetailPage)
	
	@Keyword
	public void getAccountInfo(int timeout = GlobalVariable.G_timeoutLong, flowControl = defaultFailureHandling) {
		accountDetailPage.getAccountInfo(timeout, flowControl)
	}
	
	@Keyword
	public void getCreditAccountInfo(int timeout = GlobalVariable.G_timeoutLong, flowControl = defaultFailureHandling) {
		accountDetailPage.getCreditAccountInfo(timeout, flowControl)
	}

	@Keyword
	public void clickThreeDotsButton(flowControl) {
		accountDetailPage.clickThreeDotsButton('Vcb',flowControl)
	}

	@Keyword
	public void verifyContactBankText(flowControl) {
		accountDetailPage.verifyContactBankText(flowControl)
	}
	
	public static Map<String, String> getAccountInfoFromHomePage(String tabName, String accountName, flowControl = defaultFailureHandling) {
		Map accountInfo = [:]
		CommonPage commonPage = BasePage.getPage(CommonPage)
		HomePage homePage = BasePage.getPage(HomePage)
		AccountsPage accountsPage = BasePage.getPage(AccountsPage)
		AccountDetailPage accountDetailPage = BasePage.getPage(AccountDetailPage)
		
		homePage.tapBtnAccounts()
		accountsPage.selectTab(tabName)
		accountsPage.selectAccount(accountName)
		commonPage.delay(GlobalVariable.G_timeoutMiniShort)
		
		accountInfo[BALANCE] = accountDetailPage.getAccountBalanceAmountValue()
		accountInfo[ACCOUNT_NO] = MobileDriver.getText(commonPage.lblSiblingDynamic(accountName), GlobalVariable.G_timeoutLong)
		
		commonPage.backToHome()
		
		return accountInfo
	}
	
	public static void verifyAccountBalanceForRejectAndExpire(String tabName, String accountName, String balanceBeforeTrx, flowControl = defaultFailureHandling) {
		Map<String, String> accountInfo = getAccountInfoFromHomePage(tabName, accountName)
		String accountBalanceAfter = accountInfo[BALANCE]
		def oldBalanceBigInt = StringUtils.convertBalanceAmountNumber(balanceBeforeTrx)
		def newBalanceBigInt = StringUtils.convertBalanceAmountNumber(accountBalanceAfter)
		BaseAssert.equals(oldBalanceBigInt.toString(), newBalanceBigInt.toString(), 'Verify account balance after transaction', flowControl)
	}
}
