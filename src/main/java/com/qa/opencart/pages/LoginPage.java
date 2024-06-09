package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class LoginPage {
	
	//1
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	//2. private By locators
	private By emailId = By.id("input-email");
	private By password = By.id("input-password");
	private By loginBtn = By.xpath("//input[@value='Login']");
	private By forgotPwdLink = By.linkText("Forgotten Password");
	//a[@class='list-group-item'][normalize-space()='Forgotten Password']
	
	//3
	public LoginPage(WebDriver driver){
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	
	//4 methods
	public String getLoginPageTitle() {
		String title = this.driver.getTitle();
		System.out.println(" LoginPage Title: " + title);
		return title;
	}
	
	public String getLoginPageUrl(){
		String url = this.driver.getCurrentUrl();
		System.out.println(" LoginPage Url: " + url);
		return url;
	}
	
	public boolean isForgetPasswordLinkExists(){
		return this.driver.findElement(forgotPwdLink).isDisplayed();		
	}
	
	public AccountsPage doLogin(String un, String pwd){
//		driver.findElement(emailId).sendKeys(un);
//		driver.findElement(password).sendKeys(pwd);
//		driver.findElement(loginBtn).click();
		// below code with element util
		System.out.println("USER_NAME: " +un);
		System.out.println("USER_PWD: "+pwd);
		eleUtil.waitForElementVisible(emailId, AppConstants.DEFAULT_MEDIUM_TIMEOUT).sendKeys(un);
		eleUtil.doSendKeys(password, pwd);
		eleUtil.doClick(loginBtn);
		return new AccountsPage(driver);
	}

}
