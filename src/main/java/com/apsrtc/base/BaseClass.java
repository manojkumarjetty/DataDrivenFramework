package com.apsrtc.base;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.apsrtc.util.Xls_Reader;

public class BaseClass {
	public static WebDriver driver = null;
	public static Properties CONFIG = null;
	public static Properties OR = null;
	public static Properties CONSTANTS = null;
	public static Xls_Reader xls1 = new Xls_Reader("src/test/resources/com/apsrtc/xls/TestCases.xlsx");
	public static Logger logger = Logger.getLogger(" ");
	public static String browserType = null;

	public BaseClass() {
		if (driver == null) {
			// initialize the properties file
			CONFIG = new Properties();
			OR = new Properties();
			CONSTANTS = new Properties();
			try {
				// config
				FileInputStream fs = new FileInputStream("src/test/resources/com/apsrtc/config/config.properties");
				CONFIG.load(fs);

				// OR
				fs = new FileInputStream("src/test/resources/com/apsrtc/config/or.properties");
				OR.load(fs);

				// constants
				fs = new FileInputStream("src/test/resources/com/apsrtc/config/constants.properties");
				CONSTANTS.load(fs);
				
				
				  logger.info(
				  "****************************************************************************************************************"
				  ); logger.info(
				  "****************************************************************************************************************"
				  ); logger.info(
				  "*******************************************       APSRTC TESTING      ******************************************"
				  ); logger.info(
				  "****************************************************************************************************************"
				  ); logger.info(
				  "****************************************************************************************************************"
				  );
				 
				 
				// logger.info("\n\n");
				if (CONFIG.getProperty("browser").equalsIgnoreCase("firefox")) {
					driver = new FirefoxDriver();
					browserType = CONFIG.getProperty("browser");
					logger.info("Mozilla Firefox Browser is Launching");
				}  else if (CONFIG.getProperty("browser").equalsIgnoreCase("chrome")) {
					System.setProperty("webdriver.chrome.driver", CONFIG.getProperty("chromeDriverUrl"));
					//Map<String, Object> prefs = new HashMap<String, Object>();
					//prefs.put("profile.default_content_setting_values.notifications", 2);
					/*
					 * ChromeOptions options = new ChromeOptions();
					 * options.addArguments("--incognito"); DesiredCapabilities capabilities = new
					 * DesiredCapabilities();
					 * capabilities.setCapability(ChromeOptions.CAPABILITY,options);
					 * options.setExperimentalOption("prefs", prefs);
					 */
					driver = new ChromeDriver();
					browserType = CONFIG.getProperty("browser");
					driver.get("https://www.apsrtconline.in/oprs-web/login/show.do");
					logger.info("Google Chrome Browser is Launching");
				} 
				broswerDetails();
				driver.manage().window().maximize();
				driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
				driver.manage().deleteAllCookies();
				//homePageTitle();
				//loginApplication();
			} catch (Exception e) {
				// error
				return;
			}
		}
	}
	
	//Open and Login DI suite application
			public void loginApplication() {
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
				logger.info("Clicked on Login button " + loginText);
			}
	
	public static void selectDataPlatform(WebElement element) {
		try {
			String dataPlatformText = element.getText();
			element.click();
			logger.info("Selected data platform: " + dataPlatformText);
		} catch (NoSuchElementException e) {
			logger.error("Data Platform not Found");
		}
	}

	public static void selectOptionInDropDownByVisibleText(WebElement element, String sVisibleTextOptionToSelect) {
		try {
			Select select = new Select(element);
			select.selectByVisibleText(sVisibleTextOptionToSelect);
		} catch (NoSuchElementException e) {
			logger.error("Dropdown Value not Found.");
		}
	}

	public static void selectOptionInDropDownByValue(WebElement element, String sValueTextOptionToSelect) {
		try {
			Select select = new Select(element);
			select.selectByValue(sValueTextOptionToSelect);
		} catch (NoSuchElementException e) {
			logger.error("Dropdown Value not Found.");
		}
	}

	public static void closeBrowser() {
		pause(3000);
		driver.quit();
		pause(3000);
		driver = null;
	}

	public void broswerDetails() {
		Capabilities cap = ((RemoteWebDriver) driver).getCapabilities();
		String browserName = cap.getBrowserName().toLowerCase();
		String osType = cap.getPlatform().toString();
		String browser_Version = cap.getVersion().toString();
		logger.info("Configuration Details :- " + "Platform - " + osType + " ," + " Browser Name - " + browserName
				+ " ," + " Browser Version - " + browser_Version);
	}

	public static void pause(long msec) {
		long end = System.currentTimeMillis() + msec;
		while (System.currentTimeMillis() < end) {
		}
	}

	public void homePageTitle() {
		driver.get("http://172.40.9.130:9600/#/session/login");
		Assert.assertEquals(driver.getTitle(), OR.getProperty("HOME_PAGE_TITLE"), "Home Page is Not Loaded.");
		logger.info("Home Page Title - " + driver.getTitle());
		logger.info("Home Page Loaded Successfully.");
	}
	
	public static void logout() {
		driver.findElement(By.xpath(OR.getProperty("LOGIN_BUTTON"))).click();
		logger.info("Logged out from the application");
	}

	public void pageRefresh() {
		for (int i = 1; i <= 10; i++) {
			Boolean isPageLoaded = driver.findElements(By.tagName("title")).size() > 0;
			if (isPageLoaded.equals(false)) {
				Actions actions = new Actions(driver);
				actions.keyDown(Keys.CONTROL).sendKeys(Keys.F5).perform();
				logger.info("Loading");
				logger.info(".");
				pause(1000);
			} else {
				logger.info("Page Loaded Successfully.");
				break;
			}
		}
	}
	
	public boolean browserLoading() {
		Boolean isPresent = driver.findElements(By.tagName("div")).size() > 0;
		if (isPresent.equals(false)) {
			logger.info("Browser Loading");
			for (int i = 0; i <= 3; i++) {
				System.out.print(".");
				pageRefresh();
			}
			return true;
		}
		return isPresent;
	}

	public void windowHandles() {
		for (String winHandle : driver.getWindowHandles()) {
			driver.switchTo().window(winHandle);
		}
	} 
}