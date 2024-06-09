package com.qa.opencart.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;

public class ProductPageInfoTest extends BaseTest{
	
	@BeforeClass
	public void productInfoPageSetup() {
		System.out.println(" Successfully executed @BeforeClass !!!!!");
		acctsPage = loginPage.doLogin(prop.getProperty("username").trim(), prop.getProperty("password").trim());
	}
	
	@Test
	public void productImagesCountTest(String searchKey, String productName, int imagesCount) {
		searchPage = acctsPage.performSearch("MacBook");
		productInfoPage = searchPage.selectProduct("MacBook Pro");
		int actImagesCount = productInfoPage.getProductImagesCount();
		Assert.assertEquals(actImagesCount, 4);
	}
	
	@Test
	public void testMe() {
		System.out.println(" Successfully executed !!!!!");
	}
	
	@Test
	public void productInfoTest(){
		searchPage = acctsPage.performSearch("MacBook");
		productInfoPage = searchPage.selectProduct("MacBook Pro");
		Map<String, String> actProductInfoMap = productInfoPage.getProductInfo();		
		softAssert.assertEquals(actProductInfoMap.get("Brand"), "Apple");
		softAssert.assertEquals(actProductInfoMap.get("Product Code"), "Product 18");
		softAssert.assertEquals(actProductInfoMap.get("productname"), "MacBook Pro");
		softAssert.assertEquals(actProductInfoMap.get("productprice"), "$2,000.00");
		
		softAssert.assertAll();
		
	}
	
	@Test
	public void addToCart(){
		searchPage = acctsPage.performSearch("MacBook");
		productInfoPage = searchPage.selectProduct("MacBook Pro");
		productInfoPage.enterQuantity(1);
		String actCartMsg = productInfoPage.addProductToCart();
		softAssert.assertTrue(actCartMsg.contains("Success"));
		softAssert.assertTrue(actCartMsg.contains("MacBook Pro"));
		
		softAssert.assertAll();
		
		
	}

}
