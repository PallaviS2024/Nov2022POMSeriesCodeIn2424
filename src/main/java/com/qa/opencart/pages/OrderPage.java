//This ia a dummy class for git tutorial 10 git branching naveen
package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.qa.opencart.utils.ElementUtil;

public class OrderPage {
	// 1
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	//locators
	private By loc = By.id("order");
	private By price = By.id("price");
	
	public OrderPage(WebDriver driver){
			this.driver = driver;
			eleUtil = new ElementUtil(driver);
	}
	
	public void getOrder() {
		System.out.println("Order plz");
	}
	
	public void getPrice() {
		System.out.println("Price plz");
	}

}
