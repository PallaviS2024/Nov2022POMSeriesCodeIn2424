package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.ElementUtil;

import java.util.ArrayList;
import java.util.List;

public class AccountsPage {

	// 1
	private WebDriver driver;
	private ElementUtil eleUtil;

	// 2 By locators
	private By logOutLink = By.linkText("Logout");
	private By accsHeaders = By.cssSelector("div#content h2");
	private By search = By.name("search");
	private By logout = By.linkText("Logout");
	// button[class='btn btn-default btn-lg'] //button[@class="btn btn-default
	// btn-lg"]
	private By searchIcon = By.cssSelector("#search button");

	// 3 constructor
	public AccountsPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}

	// 4 methods
	public String getAccountsPageTitle() {
		String actualTitle = eleUtil.waitForTitleIsAndFetch(10, "My Account");
		// driver.getTitle();
		System.out.println(" AccountsPage actualTitle: ");
		return actualTitle;
	}

	public String getAccountsPageUrl() {
		String actualUrl = eleUtil.waitForURLContainsAndFetch(10, "route=account/account");
		// driver.getCurrentUrl();
		System.out.println(" AccountsPage actualUrl: " + actualUrl);
		return actualUrl;
	}

	public boolean isSearchFieldExists() {
		return eleUtil.waitForElementVisible(search, 10).isDisplayed();
		// return driver.findElement(search).isDisplayed();
	}

	public boolean islogoutFieldExists() {
		return eleUtil.waitForElementVisible(logout, 10).isDisplayed();
		// return driver.findElement(search).isDisplayed();
	}

	public List<String> getAcctsPageHeadersList() {
		// List<WebElement> headers = driver.findElements(accsHeaders); replaced code
		List<WebElement> headers = eleUtil.waitForElementsVisible(accsHeaders, 10);

		List<String> headersTextList = new ArrayList<String>();

		for (WebElement ele : headers) {
			System.out.println(ele.getText());
			headersTextList.add(ele.getText());
		}
		return headersTextList;
	}

	// method is aaded for performaing search items example macbook
	public SearchPage performSearch(String searchKey) {
		
		if(isSearchFieldExists()) {
			eleUtil.doSendKeys(search, searchKey);
			eleUtil.doClick(searchIcon);
			return new SearchPage(driver);
		}
		else {
			System.out.println("Search field is not present on the page....");
			return null;
		}
	}
	
}
