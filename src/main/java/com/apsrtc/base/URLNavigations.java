package com.apsrtc.base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class URLNavigations extends BaseClass{
	
	public void openApsrtc() {		
		driver.get("https://www.apsrtconline.in/oprs-web/login/show.do");
		Assert.assertEquals(driver.getTitle(), OR.getProperty("RTC_HOME_PAGE_TITLE"), "Home Page is Not Loaded.");
		logger.info("Home Page Title - " + driver.getTitle());
		logger.info("Home Page Loaded Successfully.");
		
		//Enter username and Password
		driver.findElement(By.xpath(OR.getProperty("RTC_USERNAME"))).sendKeys(CONFIG.getProperty("UserName"));
		// String enteredValue = driver.findElement(By.xpath(OR.getProperty("TS_USERNAME"))).getAttribute("ng-reflect-model");
		logger.info("Entered username ");
		
		driver.findElement(By.xpath(OR.getProperty("RTC_PASSWORD"))).sendKeys(CONFIG.getProperty("Password"));
		logger.info("Entered Password");
		
		WebElement login = driver.findElement(By.xpath(OR.getProperty("RTC_LOGIN_BUTTON")));
		String loginText = login.getText();
		login.click();	
		logger.info("Clicked on Login button ");
		//pause(1000);
		
	}

	public void url9070() {
		driver.get("http://172.40.9.130:9600/#/session/login");
		Assert.assertEquals(driver.getTitle(), OR.getProperty("HOME_PAGE_TITLE"), "Home Page is Not Loaded.");
		logger.info("Home Page Title - " + driver.getTitle());
		logger.info("Home Page Loaded Successfully.");
		
		//Enter username and Password
		driver.findElement(By.xpath(OR.getProperty("HOME_LOGIN"))).sendKeys(CONFIG.getProperty("UserName"));
		logger.info("Entered username");
		
		driver.findElement(By.xpath(OR.getProperty("HOME_PASSWORD"))).sendKeys(CONFIG.getProperty("Password"));
		logger.info("Entered Password");
		
		WebElement login = driver.findElement(By.xpath(OR.getProperty("LOGIN_BUTTON")));
		String loginText = login.getText();
		login.click();	
		logger.info("Clicked on: "+ loginText);
	}
	
	public void openThoughtSpot() {		
		driver.get("http://13.52.189.173:9000/#/session/login");
		Assert.assertEquals(driver.getTitle(), OR.getProperty("TS_HOME_PAGE_TITLE"), "Home Page is Not Loaded.");
		logger.info("Home Page Title - " + driver.getTitle());
		logger.info("Home Page Loaded Successfully.");
		
		//Enter username and Password
		driver.findElement(By.xpath(OR.getProperty("TS_USERNAME"))).sendKeys(CONFIG.getProperty("TSUserName"));
		String enteredValue = driver.findElement(By.xpath(OR.getProperty("TS_USERNAME"))).getAttribute("ng-reflect-model");
		logger.info("Entered username: " + enteredValue);
		
		driver.findElement(By.xpath(OR.getProperty("TS_PASSWORD"))).sendKeys(CONFIG.getProperty("TSPassword"));
		logger.info("Entered Password");
		
		WebElement login = driver.findElement(By.xpath(OR.getProperty("TS_LOGIN_BUTTON")));
		String loginText = login.getText();
		login.click();	
		logger.info("Clicked on: "+ loginText);
		//pause(1000);
		
		try {
			driver.findElement(By.xpath("//span[contains(text(),'Forcible Login')]")).click();
			logger.info("Forcible login button is displayed");
		} catch (Exception e) {
			logger.info("Forcible login button is not displayed");
		}	 
	}
	
	public void openCloudSync() {
		driver.get("http://cloudsync-test.diyotta.com/#/");
		Assert.assertEquals(driver.getTitle(), OR.getProperty("CS_HOME_PAGE_TITLE"), "Home Page is Not Loaded.");
		logger.info("Home Page Title - " + driver.getTitle());
		logger.info("Home Page Loaded Successfully.");
		
		//Enter username and Password
		driver.findElement(By.id(OR.getProperty("LOGIN_ID"))).sendKeys(CONFIG.getProperty("csUserName"));
		String enteredValue = driver.findElement(By.id(OR.getProperty("LOGIN_ID"))).getAttribute("ng-reflect-model");
		logger.info("Entered username: " + enteredValue);
		
		driver.findElement(By.id(OR.getProperty("PASSWORD_ID"))).sendKeys(CONFIG.getProperty("csPassword"));
		logger.info("Entered Password inn the password textbox");
		pause(1000);
		WebElement login = driver.findElement(By.xpath(OR.getProperty("CS_LOGIN_BUTTON")));
		String loginText = login.getText();
		login.click();	
		logger.info("Clicked on: "+ loginText);
		pause(1000);
		
		try {
			driver.findElement(By.xpath(OR.getProperty("CS_FORCIBLE_BUTTON"))).click();
			logger.info("Forcible login button is displayed");
		} catch (Exception e) {
			logger.info("Forcible login button is not displayed");
		}
	}
	
	public void loginApplication() {
		driver.findElement(By.xpath(OR.getProperty("HOME_LOGIN"))).sendKeys(CONFIG.getProperty("UserName"));
		logger.info("Entered username");
		
		driver.findElement(By.xpath(OR.getProperty("HOME_PASSWORD"))).sendKeys(CONFIG.getProperty("Password"));
		logger.info("Entered Password");
		
		WebElement login = driver.findElement(By.xpath(OR.getProperty("LOGIN_BUTTON")));
		String loginText = login.getText();
		login.click();	
		logger.info("Clicked on: "+ loginText);	 
	}
}
