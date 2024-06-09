package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class SearchPage {
	
	// 1
		private WebDriver driver;
		private ElementUtil eleUtil;
		private By searchProductResults = By.cssSelector("div#content div.product-layout");

		public SearchPage(WebDriver driver) {
			// TODO Auto-generated constructor stub
			this.driver = driver;
			eleUtil = new ElementUtil(driver);
		}
		
		public ProductInfoPage selectProduct(String productName) {
			By productLocator =  By.linkText(productName);
			eleUtil.waitForElementVisible(productLocator, AppConstants.DEFAULT_MEDIUM_TIMEOUT).click();
			return new ProductInfoPage(driver);
		}
		
		public int getSearchProductsCount() {
			int productCount = eleUtil.waitForElementsVisible(searchProductResults, AppConstants.DEFAULT_MEDIUM_TIMEOUT).size();
			System.out.println("Product Count:::" + productCount);
			return productCount;
		}
		

}
