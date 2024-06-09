//This ia a dummy class for git tutorial 10 git branching naveen
package com.qa.opencart.pages;

import org.openqa.selenium.WebDriver;
import com.qa.opencart.utils.ElementUtil;

public class OrderPage {
	// 1
	private WebDriver driver;
	private ElementUtil eleUtil;

	// 3
	public OrderPage(WebDriver driver){
			this.driver = driver;
			eleUtil = new ElementUtil(driver);
	}

}
