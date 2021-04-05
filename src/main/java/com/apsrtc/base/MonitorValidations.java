package com.apsrtc.base;

import org.openqa.selenium.By;
import org.testng.Assert;

public class MonitorValidations extends BaseClass {
	
	public void validateSuccessMessage() {
		String successMessage = driver.findElement(By.xpath(OR.getProperty("MESSAGE_SUCCEEDED"))).getText();
		Assert.assertEquals(successMessage, "Succeeded", "DF has not succeeded");
		logger.info(successMessage + " is displayed");
	}

}
