package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;

public class LoginPageTest extends BaseTest {

	@Test(priority = 1)
	public void loginPageTitleTest(){
		String actualTitle = loginPage.getLoginPageTitle();
		Assert.assertEquals(actualTitle, "Account Login");
	}
	
	@Test(priority = 4)
	public void loginTest(){
		//acctsPage = loginPage.doLogin("mlokpal17@gmail.com", "gauSeva#24"); old hard code
		acctsPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		Assert.assertTrue(acctsPage.islogoutFieldExists());
		
		//mlokpal17@gmail.com gauSeva#24
		//naveen@gmail.com", "test@123
	}
	
	@Test(priority = 2)
	public void loginPageUrlTest() {
		String actualUrl = loginPage.getLoginPageUrl();
		Assert.assertTrue(actualUrl.contains("?route=account/login"));
	}
	
	@Test(priority = 3)
	public void forgotPwdLinkExistTest() {
		Assert.assertTrue(loginPage.isForgetPasswordLinkExists());
	}
	
}
