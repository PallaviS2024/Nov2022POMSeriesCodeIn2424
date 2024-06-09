package com.qa.opencart.tests;

import java.util.List;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;

// Page chaning model for POM

public class AccountsPageTest extends BaseTest {

	@BeforeClass
	public void acctPageSetup() {
		acctsPage = loginPage.doLogin("mlokpal17@gmail.com", "gauSeva#24");
	}
	
	@Test
	public void acctsPageTitleTest() {
		String actualTitle = acctsPage.getAccountsPageTitle();
		Assert.assertEquals(actualTitle, AppConstants.LOGIN_PAGE_TITLE_VALUE);
		//Assert.assertEquals(actualTitle, "My Account");
	}

	@Test
	public void acctsPageUrlTest() {
		String actualUrl = acctsPage.getAccountsPageUrl();
		Assert.assertTrue(actualUrl.contains("route=account/account"));
	}

	@Test
	public void logoutLinkExistsTest() {
		Assert.assertTrue(acctsPage.islogoutFieldExists());
	}
	
	@Test
	public void acctsPageHeadersCountTest() {		
		List<String> actualAcctsPageHeadersList = acctsPage.getAcctsPageHeadersList();
		//Assert.assertEquals(actualAcctsPageHeadersList.size(), 4);
		Assert.assertEquals(actualAcctsPageHeadersList.size(), AppConstants.ACCOUNTS_PAGE_HEADERS_COUNT);
	}
	
	@Test
	public void acctsPageHeadersValueTest() {		
		List<String> actualAcctsPageHeadersList = acctsPage.getAcctsPageHeadersList();
		//Assert.assertEquals(actualAcctsPageHeadersList.size(), 4);
		//Assert.assertEquals(actualAcctsPageHeadersList, AppConstants.ACCOUNTS_PAGE_HEADERS_LIST);
		// if the order is not matching do sort the collection 
	}
	
	@DataProvider
	public Object[][] getProductData(){
		return new Object[][] {
			{"Macbook", "MacBook Pro"},
			{"iMac", "iMac"},
			{"Apple", "Apple Cinema 30"},
			{"Samsung", "Samsung Galaxy Tab 10.1"}
		};
	}
	
	@Test(dataProvider = "getProductData")
	public void searchProductCountTest(String searchKey, String productName) {
		searchPage = acctsPage.performSearch(searchKey);
		System.out.println("Product list on Search PAge: " +searchPage.getSearchProductsCount());
		Assert.assertTrue(searchPage.getSearchProductsCount()>0);		
	}
		
	@Test(dataProvider = "getProductData")
	public void selectProductTest(String searchKey, String productName) {
		searchPage = acctsPage.performSearch(searchKey);
		if (searchPage.getSearchProductsCount() > 0) {
			productInfoPage = searchPage.selectProduct(productName);
			String actProductHeader = productInfoPage.getProductHeaderValue();
			Assert.assertEquals(actProductHeader, productName);
		}

	}
	
}
