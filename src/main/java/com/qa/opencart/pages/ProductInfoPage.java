package com.qa.opencart.pages;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class ProductInfoPage {

	private WebDriver driver;
	private ElementUtil eleUtil;
	private Map<String, String> productInfoMap = new HashMap<String, String>();

	private By productHeader = By.tagName("h1");// xpath //h1[text()='MacBook Pro']
	private By productImages = By.cssSelector("ul.thumbnails img");
	// private By productMetaData =
	// By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[position()=1]/li");
	// i made xpth by self
	private By productMetaData = By.xpath("(//div[@class='col-sm-4'])[position()=2]//ul[position()=1]//li");
	private By productPriceData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[position()=2]/li");
	private By quantity = By.id("input-quantity");
	private By addToCartBtn = By.id("button-cart");
	private By cartSuccessMessg = By.cssSelector("div.alert.alert-success");
	private By cart = By.id("cart");

	public ProductInfoPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}

	// Methods
	public String getProductHeaderValue() {
		String productHeaderVal = eleUtil.doElementGetText(productHeader);
		System.out.println("product header: " + productHeaderVal);
		return productHeaderVal;
	}

	public int getProductImagesCount() {
		int imagesCount = eleUtil.waitForElementsVisible(productImages, AppConstants.DEFAULT_MEDIUM_TIMEOUT).size();
		System.out.println("product images count: " + imagesCount);
		return imagesCount;
	}

	public HashMap<String, String> getProductInfo() {
		/*Brand: Apple Product Code: Product 18 Reward Points: 800 Availability: In * Stock */
		productInfoMap = new HashMap<String, String>();// no order is maitained.
		productInfoMap = new LinkedHashMap<String, String>();// insertion order will be maintained
		//productInfoMap = new TreeMap<String, String>();//sorted order is maintained on Key first capitals athan smaller Alphabets
		//header value setting to complte product info :-) 
		productInfoMap.put("productName",getProductHeaderValue() );
		
		//meta data capturing
		getProductMetaData();
		
		// $2,000.00, Ex Tax: $2,000.00	// price capturing
		getProductPriceData();
		
		System.out.println(" actProductInfoMap:: "+ productInfoMap);
		return (HashMap<String, String>) productInfoMap;
		
	}
	
	private void getProductMetaData(){
		List<WebElement> metaList = eleUtil.getElements(productMetaData);
		for (WebElement ele : metaList) {
			String metaData = ele.getText();
			// System.out.println("MetaDat:: "+ metaData);
			String metaInfo[] = metaData.split(":");
			String key = metaInfo[0].trim();
			String value = metaInfo[1].trim();
			productInfoMap.put(key, value);

		}

	}
	
	private void getProductPriceData(){
		List<WebElement> priceList = eleUtil.getElements(productPriceData);
		String price = priceList.get(0).getText();
		String exTax = priceList.get(1).getText();
		String extaxVal = exTax.split(":")[1].trim();

		productInfoMap.put("productprice", price);
		productInfoMap.put("exTax", extaxVal);
	}
	
	public void enterQuantity(int qty){
		System.out.println("enterQuantity:: "+ qty);
		eleUtil.doSendKeys(quantity, String.valueOf(qty));
	}
	
	public String addProductToCart(){
		eleUtil.doClick(addToCartBtn);
		String successMessage = eleUtil.waitForElementVisible(cartSuccessMessg, AppConstants.DEFAULT_MEDIUM_TIMEOUT).getText();
		System.out.println("successMessage:: "+ successMessage);
		return successMessage;
	}
	
	
	

}
