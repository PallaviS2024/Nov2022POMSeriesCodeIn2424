package com.qa.opencart.factory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

public class DriverFactory {

	protected WebDriver driver;
	protected Properties prop;
	public static String highlight;
	public OptionsManager optionsManager;

	//public WebDriver initDriver(String browserName) { old code
	public WebDriver initDriver(Properties prop) {
		String browserName = prop.getProperty("browser").toLowerCase().trim();
		highlight = prop.getProperty("highlight").trim();
		System.out.println("Browser to initialize: " + browserName);
		if (browserName.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();
		} else if (browserName.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
		} else if (browserName.equalsIgnoreCase("safari")) {
			driver = new SafariDriver();
		} else if (browserName.equalsIgnoreCase("edge")) {
			driver = new EdgeDriver();
		} else {
			System.out.println("Please pass the correct browser name .... " + browserName);
		}

		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		//driver.get("https://naveenautomationlabs.com/opencart/index.php?route=account/login");
		driver.get(prop.getProperty("url").trim());
		return driver;

	}

	// src\test\resources\config\config.properties
	public Properties initProp() {
		prop = new Properties();
		try {
			FileInputStream fis = new FileInputStream("./src/test/resources/config/config.properties");
			prop.load(fis);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return prop;
	}

}
