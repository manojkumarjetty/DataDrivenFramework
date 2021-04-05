package com.apsrtc.base;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

public class LeftPaneActions extends BaseClass {
	JavascriptExecutor executor = (JavascriptExecutor)driver;
	
	public void selectProject() {
		driver.findElement(By.xpath("//div[@class='input-group']//button[@id='dropdownMenuButton']")).click();
		driver.findElement(By.xpath("//a[contains(text(),'4.5sanity')]")).click();
	}
	
	//Click on Studio
		public void clickOnStudio() throws InterruptedException {
			Thread.sleep(5000);
			WebElement studio = driver.findElement(By.xpath(OR.getProperty("MODULE_STUDIO")));
			String studioText = studio.getText();
			JavascriptExecutor executor = (JavascriptExecutor)driver;
			executor.executeScript("arguments[0].click();", studio);
			logger.info("Clicked on: " + studioText);
			
		}

	public void clickOnDataPoint() {
		WebElement dataPoint = driver.findElement(By.xpath(OR.getProperty("LEFTMENU_DATAPOINT")));
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", dataPoint);
		String dpText = dataPoint.getText();
		System.out.println("Clicked on: " + dpText);
	}	
	
	public void clickOnImportSchema() {
		WebElement dataObject = driver.findElement(By.xpath(OR.getProperty("LEFTMENU_DATAOBJECT")));
		WebElement importSchema = driver.findElement(By.xpath(OR.getProperty("LEFTMENU_IMPORTSCHEMA")));
		
		//String schemaText = importSchema.getText();
		//logger.info(schemaText);
		Actions action = new Actions(driver);
		action.moveToElement(dataObject).build().perform();
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", importSchema);
		//action.click(importSchema).build().perform();
		//logger.info("Clicked on " + schemaText);
	}
	
	public void clickOnDataFlow() throws InterruptedException {	
			driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
			WebElement dataFlow = driver.findElement(By.xpath(OR.getProperty("LEFTMENU_DATAFLOW")));
			String dataFlowText = dataFlow.getText();
			JavascriptExecutor executor = (JavascriptExecutor)driver;
			executor.executeScript("arguments[0].click();", dataFlow);
			logger.info("Clicked on: " + dataFlowText);
			Thread.sleep(5000);	
	}
	
	public void clickOnMenuNew() {
		WebElement newbutton = driver.findElement(By.xpath(OR.getProperty("MENU_NEW")));
		Actions action = new Actions(driver);
		action.moveToElement(newbutton).click().perform();
		
		WebElement subNew = driver.findElement(By.xpath(OR.getProperty("SUB_MENU_NEW")));
		action.moveToElement(subNew).click().perform();
		
		WebElement dataPlatform = driver.findElement(By.xpath(OR.getProperty("SPLICEMACHINE_DATAPLATFORM")));
		String dplText = dataPlatform.getText();
		dataPlatform.click();
		logger.info("Clicked on: " + dplText);
	}
	public void clickOnNew() {
		WebElement newButton = driver.findElement(By.xpath(OR.getProperty("MENU_NEW")));
		String newText = newButton.getText();
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", newButton);
		//newButton.click();
		logger.info("Clicked on: " + newText);
	}

	public void clickOnSave() {
		WebElement saveMenu = driver.findElement(By.xpath(OR.getProperty("SAVE_MENU")));
		String saveText = saveMenu.getText();
		executor.executeScript("arguments[0].click();", saveMenu);
		//saveMenu.click();
		logger.info("Clicked on: " + saveText);
		pause(2000);
		String toastMessage = driver.findElement(By.xpath(OR.getProperty("DP_SUCCESS_MESSAGE"))).getText();
		System.out.println("Displays toast message: " + toastMessage);
		//Assert.assertEquals(toastMessage, "Data Point(s) has been saved successfully.", "Unable to save");
	}
	
	public void testDataPoint() {
		WebElement testDataPoint = driver.findElement(By.xpath(OR.getProperty("TEST_DATA_POINT")));
		String testDataPointText = testDataPoint.getText();
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", testDataPoint);
		logger.info("Clicked on: " + testDataPointText);
		pause(2000);
		String toastMessage = driver.findElement(By.xpath(OR.getProperty("DP_SUCCESS_MESSAGE"))).getText();
		System.out.println("Displays toast message: " + toastMessage);
		Assert.assertEquals(toastMessage, "Connection established successfully.", "Datapoint connection has failed");
	}
	
	public void successMessage() {
		String toastMessage = driver.findElement(By.xpath(OR.getProperty("DP_SUCCESS_MESSAGE"))).getText();
		System.out.println("Displays toast message: " + toastMessage);
		Assert.assertEquals(toastMessage, "Connection established successfully.", "Datapoint connection has failed");
	}
}