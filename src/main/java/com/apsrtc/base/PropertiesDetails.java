package com.apsrtc.base;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class PropertiesDetails extends BaseClass {
	
	public void clickOnProperties() {
		WebElement properties = driver.findElement(By.linkText(OR.getProperty("PROPERTIES_LINKTEXT")));
		String propertiesText = properties.getText();
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", properties);
		
		//properties.click();
		logger.info("Clicked on: " + propertiesText);
	}
	
	public void enterProperties(String host, String appuser, String password, String database) {
		WebElement hostInput = driver.findElement(By.xpath(OR.getProperty("INPUT_HOST")));
		hostInput.sendKeys(host);
		String hostEntry = hostInput.getAttribute("ng-reflect-model");
		logger.info("Entered Host: " + hostEntry );
		
		WebElement appUserInput = driver.findElement(By.xpath(OR.getProperty("INPUT_APP_USER")));
		appUserInput.sendKeys(appuser);
		String appUserEntry = appUserInput.getAttribute("ng-reflect-model");
		logger.info("Entered App user: " + appUserEntry );
		
		WebElement passwordInput = driver.findElement(By.xpath(OR.getProperty("INPUT_PASSWORD")));
		passwordInput.sendKeys(password);
		
		WebElement databaseInput = driver.findElement(By.xpath(OR.getProperty("INPUT_DATABASE")));
		databaseInput.clear();
		databaseInput.sendKeys(database);
		String dataBaseEntry = databaseInput.getAttribute("ng-reflect-model");
		logger.info("Entered Database: " + dataBaseEntry );
		
		WebElement dataBaseDropDown = driver.findElement(By.xpath(OR.getProperty("SELECT_DATABASE")));
		selectOptionInDropDownByVisibleText(dataBaseDropDown, "HDP_2.6.5");
		String selectedDataBase = dataBaseDropDown.getAttribute("ng-reflect-model");
		logger.info("Selected Database: " + selectedDataBase );
	}
	
	public void clickOnSchemas() {
		WebElement schemas = driver.findElement(By.xpath(OR.getProperty("DP_SCHEMAS")));
		schemas.click();
		String schemaText = schemas.getText();
		logger.info("Clicked on: " + schemaText);
	}
	
	public void enterSchemas() {
		/*
		 * WebElement schemas =
		 * driver.findElement(By.xpath(OR.getProperty("DP_SCHEMAS"))); schemas.click();
		 * String schemaText = schemas.getText(); logger.info("Clicked on: " +
		 * schemaText);
		 */
		
		WebElement schemaOne = driver.findElement(By.xpath("//div[@class='tabulator-tableHolder']//div[4]"));
		schemaOne.click();
		pause(2000);
		//schemaOne.clear();
		schemaOne.sendKeys("DILAB_TFORM");
		
		WebElement schemaNameOne = driver.findElement(By.xpath("//div[contains(text(),'default')]"));
		schemaNameOne.click();
		
		Actions action = new Actions(driver);
		action.doubleClick(schemaNameOne).build().perform();
		 
		schemaNameOne.sendKeys(Keys.BACK_SPACE);
		//schemaNameOne.clear();
		schemaNameOne.sendKeys("A_DILAB_TFORM");
		
		driver.findElement(By.xpath("//small[contains(text(),'Add')]")).click();
		
		WebElement schemaTwo = driver.findElement(By.xpath("//div[contains(text(),'default')]"));
		schemaTwo.click();
		//schemaTwo.clear();
		schemaTwo.sendKeys("DILAB_TGT");
		
		WebElement schemaTwoName = driver.findElement(By.xpath("//div[contains(text(),'DATABASE_NAME_1')]"));
		schemaTwoName.click();
		schemaTwoName.sendKeys(Keys.BACK_SPACE);
		action.doubleClick(schemaTwoName).build().perform();
		schemaTwoName.sendKeys(Keys.BACK_SPACE);
		//schemaTwoName.clear();
		schemaTwoName.sendKeys("A_DILAB_TGT");
		
		driver.findElement(By.xpath("//input[@id='stgDb_Flag_24822000']")).click();
	}
}