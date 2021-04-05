package com.apsrtc.base;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

public class MenuDropDownItemsNavigation extends BaseClass {
	JavascriptExecutor executor = (JavascriptExecutor)driver;
	
	public void clickOnMenuIcon() {
		
		executor.executeScript("arguments[0].click();", driver.findElement(By.xpath(OR.getProperty("MENU_DROPDOWN"))));
		if(driver.findElement(By.xpath(OR.getProperty("MENU_LIST"))).isDisplayed()) {
			logger.info("Menu list is displayed");
		} else {
			logger.info("Not able to click on Menu dropdown");
		}
	}
	
	public void clickOnMonitor() {
		WebElement monitor = driver.findElement(By.xpath(OR.getProperty("MENU_MONITOR")));
		String monitorText = monitor.getText();
		executor.executeScript("arguments[0].click();", monitor);
		logger.info("Clicked on " + monitorText);
	}

}
