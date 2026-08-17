package com.maybank.mae.internal.pages.androidphone.articlesandpromotionspage

import com.kms.katalon.core.testobject.TestObject
import com.maybank.mae.internal.pages.base.articlesandpromotionspage.ArticlesAndPromotionsPage
import com.maybank.mae.internal.pages.base.accountpage.UpliftFDPage
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

public class AndroidPhoneArticlesAndPromotionsPage extends ArticlesAndPromotionsPage {
	// Promotions
	
	@Override
	public TestObject lblTitleBySection(String text, int index) {
		return findTestObject('Object Repository/Mobile/MAE/Android/Articles and Promotions/lblTitleBySection', ['text': text, 'index': index]);
	}
	
	@Override
	public TestObject lblTitleLatestSection(int index) {
		return findTestObject('Object Repository/Mobile/MAE/Android/Articles and Promotions/lblTitleLatestSection', ['index': index]);
	}
	
	// Articles 
	@Override
	public TestObject lblArticlesTitleBySection(String text, int index) {
		return findTestObject('Object Repository/Mobile/MAE/Android/Articles and Promotions/lblArticlesTitleBySection', ['text': text, 'index': index]);
	}
	
	@Override
	public TestObject lblNewsLikeByIndex(int index) {
		return findTestObject('Object Repository/Mobile/MAE/Android/Articles and Promotions/lblNewsLikeByIndex', ['index': index]);
	}
}