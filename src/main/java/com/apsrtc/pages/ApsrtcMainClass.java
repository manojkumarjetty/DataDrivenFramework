package com.apsrtc.pages;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.apsrtc.base.BaseClass;
import com.apsrtc.base.URLNavigations;

public class ApsrtcMainClass extends BaseClass {
	JavascriptExecutor js = (JavascriptExecutor) driver;
	URLNavigations url = new URLNavigations();
	String Host = null;
	String Port = null;
	String Username = null;
	String Password = null;
	String Database = null;
	String Version = null;

	public ApsrtcMainClass tsdfCreation(String FromRoute, String ToRoute)
			throws InterruptedException {
		// url.openApsrtc();
		/*headerVerification();
		clickOnConnections();
		pause(1000);
		addConnection();
		enterConnectionDetails(ConnectionName, SelectValue);
		pause(1000);
		addTableSync();
		tableSetup(DBtype, Dbname, AddTableSync);
		setUpSync();
		pause(3000);
		scheduleDoesNotRepeat();
		advancedSetup();
		mapTableAndColumns(TsTable);
		pause(10000);
		clickOnConnections();
		pause(2000);
		selectConnection(ConnectionName);
		pause(2000);
		selectTable(TsTable);
		Thread.sleep(3000);
		pageRefresh();
		clickOnViewLogIcon();
		verifyExtractType(ExtractType);
		verifyExtractionMode(ExtractionMode);
		loadType();
		numberOfRows();
		clickOnClose();
		apsrtcLogout();*/

		return new ApsrtcMainClass();
	}

	public void pageRefresh() {
		
		for (int i = 1; i <= 30; i++) {
			Boolean isPageLoaded = driver.findElements(By.xpath(OR.getProperty("SYNC_ICON"))).size() > 0;
			if (isPageLoaded.equals(true)) {
				driver.navigate().refresh();
				logger.info("Syncing");
				pause(1000);
			} else {
				logger.info("Verifying sync status");
				break;
			}
		}
	}

	
	  public void pageRefresh(String ExtractType, String ExtractionMode) { for (int
	  i = 1; i <= 20; i++) { Boolean isPageLoaded =
	  driver.findElements(By.xpath(OR.getProperty("SYNC_ICON"))).size() > 0; if
	  (isPageLoaded.equals(true)) { driver.navigate().refresh();
	 logger.info("Syncing"); pause(500); } else
	  if(driver.findElements(By.xpath(OR.getProperty("SYNC_FAILED"))).size()>0) {
	  logger.info("Sync Failed"); break; } else
	  if(driver.findElements(By.xpath(OR.getProperty("SUCCESS_ICON"))).size()>0) {
	  logger.info("Sync Completed"); clickOnViewLogIcon();
	  verifyExtractType(ExtractType); verifyExtractionMode(ExtractionMode);
	loadType(); numberOfRows(); clickOnClose(); } } }
	 

	public void headerVerification() {
		String expectedHeader = "DataFlow Home";
		String headerH2 = driver.findElement(By.xpath(OR.getProperty("TS_HEADER"))).getText();
		logger.info("Displaying home page header: " + headerH2);
		Assert.assertEquals(headerH2, expectedHeader, "Header not matched");
	}

	public void clickOnConnections() {
		WebElement connection = driver.findElement(By.xpath(OR.getProperty("BUTTON_CONNECTIONS")));
		String connectionText = connection.getText();
		js.executeScript("arguments[0].click();", connection);
		// connection.click();
		logger.info("Clicked on " + connectionText);
	}

	public void addConnection() {
		WebElement addConnection = driver.findElement(By.xpath(OR.getProperty("BUTTON_ADD_CONNECTION")));
		String connectionText = addConnection.getText();
		addConnection.click();
		logger.info("Clicked on " + connectionText);
	}

	public void addTableSync() {
		WebElement tableSync = driver.findElement(By.xpath(OR.getProperty("ADD_TABLE_SYNC")));
		String tableSyncText = tableSync.getText();
		tableSync.click();
		logger.info("Clicked on: " + tableSyncText);
		WebElement headerEle = driver.findElement(By.xpath(OR.getProperty("HEADER_H2")));
		String headerText = headerEle.getText();
		if (headerEle.isDisplayed()) {
			logger.info(headerText + " is displayed");
		} else {
			logger.info(headerText + " is not displayed");
		}
	}

	public void tableSetup(String DBtype, String Dbname, String AddTableSync) {
		List<WebElement> dblist = driver.findElements(By.xpath("//div[@class='tree-menu-main']//a"));
		for (int i = 0; i < dblist.size(); i++) {
			// action.moveToElement(dblist.get(i)).perform();
			String selectDb = dblist.get(i).getText();
			if (selectDb.equalsIgnoreCase(Dbname)) {
				logger.info("Selected DB: " + selectDb);
				dblist.get(i).click();
				break;
			}
		}
		pause(9000);
		List<WebElement> tablelist = driver
				.findElements(By.xpath("//div[@class='sql-list-grid-child']//div[@class='tree-menu-main']//a"));
		for (int i = 0; i < tablelist.size(); i++) {
			// action.moveToElement(tablelist.get(i)).perform();
			if (tablelist.get(i).getText().equalsIgnoreCase(AddTableSync)) {
				logger.info("Selected Table:  " + tablelist.get(i).getText());
				tablelist.get(i).click();
				pause(8000);
				break;
			}
		}
	}

	public void setUpSync() {
		WebElement setUpSync = driver.findElement(By.xpath(OR.getProperty("SET_UP_SYNC")));
		String setupSyncText = setUpSync.getText();
		js.executeScript("arguments[0].click();", setUpSync);
		logger.info("Clicked on: " + setupSyncText);
	}

	public void scheduleDoesNotRepeat() {
		driver.findElement(By.xpath(OR.getProperty("SCHEDULE_REPEAT"))).click();
		pause(500);
		driver.findElement(By.xpath(OR.getProperty("DOES_NOT_REPEAT"))).click();
		pause(500);
		logger.info("Selected value from schedule dropdown: Does not repeat");
		driver.findElement(By.xpath(OR.getProperty("ALERT_DROPDOWN"))).click();
		pause(500);
		driver.findElement(By.xpath(OR.getProperty("DROPDOWN_NEVER"))).click();
		logger.info("Selected value from Alert dropdown: Never");
	}

	public void repeatHourly() {
		driver.findElement(By.xpath(OR.getProperty("SCHEDULE_REPEAT"))).click();
		pause(500);
		driver.findElement(By.xpath(OR.getProperty("REPEAT_HOURLY"))).click();
		pause(500);
		driver.findElement(By.xpath(OR.getProperty("DROPDOWN_CARET"))).click();
		pause(500);
		driver.findElement(By.xpath(OR.getProperty("REPEAT_HOUR"))).click();
	}

	public void advancedSetup() {
		driver.findElement(By.xpath(OR.getProperty("ADVANCED_SETUP"))).click();
		logger.info("Clicked on advanced setup");
	}

	public void mapTableAndColumns(String tsTable) {
		pause(1000);
		driver.findElement(By.xpath(OR.getProperty("THOUGHTSPOT_TABLE"))).clear();
		driver.findElement(By.xpath(OR.getProperty("THOUGHTSPOT_TABLE"))).sendKeys(tsTable);
		logger.info("Newly created table: " + tsTable);
		pause(1000);
		
		 driver.findElement(By.xpath(OR.getProperty("TOGGLE_PRIMARY_KEY"))).click();
		 logger.info("Primary key toggle is checked"); 
		 pause(1000);
		 
		driver.findElement(By.xpath(OR.getProperty("SAVE_AND_SYNC_NOW"))).click();
		logger.info("Clicked on Save and Sync");
	}

	public void enterConnectionDetails(String FromRoute, String ToRoute) {
		logger.info("Printing APSRTC From Route "+ FromRoute);
		logger.info("Printing APSRTC To Route "+ ToRoute);
		driver.findElement(By.xpath(OR.getProperty("RTC_FROM_ROUTE"))).sendKeys(FromRoute);
		driver.findElement(By.xpath("//a[text()='"+FromRoute+"']")).click();
		driver.findElement(By.xpath(OR.getProperty("RTC_TO_ROUTE"))).sendKeys(ToRoute);
		driver.findElement(By.xpath("//a[text()='"+ToRoute+"']")).click();
		driver.findElement(By.xpath(OR.getProperty("RTC_DEPART_ON"))).click();
		driver.findElement(By.xpath(OR.getProperty("RTC_DEPART_ON_DATE"))).click();
		driver.findElement(By.xpath(OR.getProperty("RTC_ROUTE_SEARCH"))).click();
	
		/*	driver.findElement(By.xpath(OR.getProperty("CONNECTION_INPUT"))).sendKeys(connectionName);
		driver.findElement(By.xpath(OR.getProperty("DROPDOWN_CONNECTION_TYPE"))).click();
		driver.findElement(By.xpath(OR.getProperty("CONNECTION_TYPE_INPUT"))).sendKeys(selectValue);
		driver.findElement(By.xpath(OR.getProperty("DROP_DOWN_VALUE"))).click();
		String Connectin_type = driver.findElement(By.id("selectHeadId")).getText();
		logger.info("Displaying header: " + Connectin_type);
		logger.info("---------------Executing " + selectValue + " dataflow---------------");
		switch (Connectin_type) {
		case "Splice Machine":
			sixFieldsConn(Host, Username, Password, Database, Version);
			break;
		default:
			logger.info("Invalid Connection type" + Connectin_type);
			break;
		}

		WebElement createbutton = driver
				.findElement(By.xpath("//div[@class='rd-button-wrapper']//span[text()='Create Connection']"));
		if (createbutton.isEnabled()) {
			js.executeScript("arguments[0].click();", createbutton);
			logger.info("Entered all required fields and clicked on create Connection button");
		} else {
			logger.info("Enter all required fields to enable create connection button");
		}*/
	}

	// Method for creating connection which contains
	// Host,Username,Password,Database,Version
	// Method for creating connection which contains
	// Host,Username,Password,Database,Version
	public void sixFieldsConn(String Host, String Username, String Password, String Database, String Version) {
		driver.findElement(By.xpath(OR.getProperty("TS_INPUT_HOST"))).sendKeys(Host);
		driver.findElement(By.xpath(OR.getProperty("ENTER_USERNAME"))).sendKeys(Username);
		logger.info("Entered username in the username textbox");
		driver.findElement(By.xpath(OR.getProperty("ENTER_PASSWORD"))).sendKeys(Password);
		logger.info("Entered password in the password textbox");
		driver.findElement(By.xpath("//bk-input[@label='Database']//div[@class='bk-input-container-inner']//input"))
				.clear();
		driver.findElement(By.xpath("//bk-input[@label='Database']//div[@class='bk-input-container-inner']//input"))
				.sendKeys(Database);

		driver.findElement(By.xpath("//bk-select[@name='netezzaPropertiesDBVersionId']//div[@id='selectHeadId']"))
				.click();
		List<WebElement> versionList = driver.findElements(By.xpath("//bk-action-menu[@class='bk-select-options']"));
		// logger.info("Version list: " + versionList);
		for (WebElement sample : versionList) {
			if (sample.getText().equals(Version)) {
				sample.click();
				logger.info(sample);
				break;
			}
		}
	}

	public void twoFieldsConn(String Username, String Password) {
		driver.findElement(By.xpath(OR.getProperty("ENTER_USERNAME"))).sendKeys(Username);
		logger.info("Entered username in the username textbox");
		driver.findElement(By.xpath(OR.getProperty("ENTER_PASSWORD"))).sendKeys(Password);
		logger.info("Entered password in the password textbox");
	}

	// Method for creating connection which contains Host,Port,Username,Password
	public void fourFieldsConn(String Host, String Port, String tdAuthenticationType, String Username,
			String Password) {
		driver.findElement(By.xpath(OR.getProperty("TS_INPUT_HOST"))).sendKeys(Host);
		driver.findElement(By.xpath(OR.getProperty("TS_INPUT_PORT"))).clear();
		driver.findElement(By.xpath(OR.getProperty("TS_INPUT_PORT"))).sendKeys(Port);
		logger.info("ENTERED TD AUTH TYPE " + tdAuthenticationType);
		if (tdAuthenticationType != null) {
			if (tdAuthenticationType.equals("Simple")) {

				logger.info("Authentication Type " + tdAuthenticationType + "is selected");
			} else if (tdAuthenticationType.equals("LDAP")) {

				driver.findElement(By.xpath(OR.getProperty("AUTHENTICATION_TYPE_DROPDOWN"))).click();
				driver.findElement(By.xpath(OR.getProperty("TD_AUTHENTICATIONTYPE_LDAP_OPTION"))).click();
				logger.info("Authentication Type " + tdAuthenticationType + "is selected");
			}

		}
		driver.findElement(By.xpath(OR.getProperty("ENTER_USERNAME"))).sendKeys(Username);
		logger.info("Entered username in the username textbox");
		driver.findElement(By.xpath(OR.getProperty("ENTER_PASSWORD"))).sendKeys(Password);
		logger.info("Entered password in the password textbox");

	}

	// Method for creating connection which contains Host,Username,Password,Database
	public void fiveFieldsConn(String Host, String Port, String Username, String Password, String Database) {
		driver.findElement(By.xpath(OR.getProperty("TS_INPUT_HOST"))).sendKeys(Host);
		driver.findElement(By.xpath(OR.getProperty("TS_INPUT_PORT"))).clear();
		driver.findElement(By.xpath(OR.getProperty("TS_INPUT_PORT"))).sendKeys(Port);
		driver.findElement(By.xpath(OR.getProperty("ENTER_USERNAME"))).sendKeys(Username);
		logger.info("Entered username in the username textbox");
		driver.findElement(By.xpath(OR.getProperty("ENTER_PASSWORD"))).sendKeys(Password);
		logger.info("Entered password in the password textbox");
		driver.findElement(By.xpath(OR.getProperty("ENTER_SERVICE/DBNAME_NAME"))).clear();
		driver.findElement(By.xpath(OR.getProperty("ENTER_SERVICE/DBNAME_NAME"))).sendKeys(Database);
	}

	public void apsrtcLogout() {
		driver.findElement(By.xpath(OR.getProperty("RTC_LOGOUT"))).click();
		logger.info("Clicked on Logout button ");
	}

	public void verifyConnectionSuccess() {
		pause(5000);
		WebElement successMessage = driver.findElement(By.xpath("//div[@class='rd-text']"));
		String messageText = successMessage.getText();
		logger.info(messageText);
	}

	public void clickOnTable() {
		WebElement table = driver.findElement(By.xpath(OR.getProperty("MENU_TABLES")));
		String tableText = table.getText();
		js.executeScript("arguments[0].click();", table);
		table.click();
		logger.info("Clicked on: " + tableText);
	}

	public void selectConnection(String connectionName) {
		List<WebElement> SelectedConnName = driver
				.findElements(By.xpath("//div[@class='tabulator-cell' and @tabulator-field='nm']"));
		for (int i = 0; i < SelectedConnName.size(); i++) {
			String SelectedName = SelectedConnName.get(i).getText();

			if (SelectedName.equals(connectionName)) {
				logger.info("Selected connection: " + SelectedName);
				SelectedConnName.get(i).click();
				break;
			}
		}
	}

	public void selectTable(String TsTable) {
		List<WebElement> SelectedConnName = driver
				.findElements(By.xpath("//div[@class='tabulator-cell' and @tabulator-field='nm']"));
		for (int i = 0; i < SelectedConnName.size(); i++) {
			String SelectedName = SelectedConnName.get(i).getText();

			if (SelectedName.equals(TsTable)) {
				logger.info("Selected Table: " + SelectedName);
				SelectedConnName.get(i).click();
				break;
			}
		}
	}

	public void clickOnViewLogIcon() {
		if (driver.findElement(By.xpath(OR.getProperty("SUCCESS_ICON"))).isDisplayed()) {
			driver.findElement(By.xpath(OR.getProperty("ICON_VIEW_LOG"))).click();
			logger.info("Clicked on View Log Icon");
		}
	}

	public void verifyExtractType(String extractType) {
		WebElement getExtractType = driver.findElement(By.xpath(OR.getProperty("EXTRACT_TYPE")));
		String extractTypeText = getExtractType.getText();
		logger.info(extractTypeText);
		Assert.assertEquals(extractTypeText, extractType, "extract type does not matched");
	}

	public void verifyExtractionMode(String extractionMode) {
		WebElement getExtractionMode = driver.findElement(By.xpath(OR.getProperty("EXTRACTION_MODE")));
		String extractionModeText = getExtractionMode.getText();
		logger.info(extractionModeText);
		Assert.assertEquals(extractionModeText, extractionMode, "extraction mode does not matched");
	}

	public void loadType() {
		WebElement loadType = driver.findElement(By.xpath(OR.getProperty("LOAD_TYPE")));
		String loadTypeText = loadType.getText();
		logger.info(loadTypeText);
		Assert.assertEquals(loadTypeText, "[Load Type : TSLOAD]", "extraction mode does not matched");
	}

	public void numberOfRows() {
        try {
        // Verifying number of rows
        String totalRows = driver.findElement(By.xpath(OR.getProperty("ROWS_TOTAL"))).getText();
        String[] rowsSplit = totalRows.split(": ");
        String afterSplit = rowsSplit[1];
        String afterTrim = afterSplit.trim();
        String totalTrim = afterTrim.replaceAll("\\s", "");
        String[] rowsSplitTwo = totalTrim.split("]");
        String afterRowsSplit = rowsSplitTwo[0];
        logger.info("Total rows: " + afterRowsSplit);

 

        // Verifying number of rows loaded
        String rowsLoadedMessage = driver.findElement(By.xpath(OR.getProperty("ROWS_LOADED"))).getText();
        String[] loadedRowsSplit = rowsLoadedMessage.split(": ");
        String afterLoadSplit = loadedRowsSplit[1];
        String afterLoadTrim = afterLoadSplit.trim();
        logger.info("Number of rows loaded: " + afterLoadTrim);
        Assert.assertEquals(afterRowsSplit, afterLoadTrim, "Loaded rows not matched with total rows");
        
          } catch(Exception e) 
            { 
              logger.info("Rows are not displayed"); 
            }
         
    }

	public void verifyToastMessage() {
		WebElement errorMessage = driver.findElement(By.xpath(OR.getProperty("TOAST_MESSAGE")));
		String messageText = errorMessage.getText();
		logger.info(messageText);
		if (errorMessage.isDisplayed()) {
			logger.info(messageText + " is displayed");
		} else {
			logger.info(messageText + " is not displayed");
		}
	}

	public void selectConnection(String EnteredConnectionName, String Dbname, String DBtype, String AddTableSync,
			String ColumnName) {

		List<WebElement> SelectedConnName = driver
				.findElements(By.xpath("//div[@class='tabulator-cell' and @tabulator-field='nm']//span"));
		for (int i = 0; i < SelectedConnName.size(); i++) {
			String SelectedName = SelectedConnName.get(i).getText();
			if (SelectedName.equals(EnteredConnectionName)) {

				SelectedConnName.get(i).click();
				String ConnHeaderName = driver
						.findElement(
								By.xpath("//div[@class='bk-list-bulk-actions']//div[@class='bk-action-container']"))
						.getText();
				Assert.assertEquals(SelectedName, ConnHeaderName, "Header not matched");
				// addTableSync();
				break;
			}
		}
		switch (DBtype) {
		case "Teradata":
			List<WebElement> dblist = driver.findElements(By.xpath("di-flex-row grid-link tree-view-database"));
			for (int i = 0; i <= dblist.size(); i++) {
				if (dblist.get(i).getText().equalsIgnoreCase(Dbname)) {
					dblist.get(i).click();
				}
			}
			List<WebElement> tablelist = driver
					.findElements(By.xpath("//div[@class='di-flex-row grid-link tree-view-table']"));
			for (int i = 0; i <= tablelist.size(); i++) {
				if (tablelist.get(i).getText().equalsIgnoreCase(AddTableSync)) {
					tablelist.get(i).click();
					String DisplayedTableName = driver
							.findElement(By.xpath("//div[@class='tablesync-container-header']")).getText();
					Assert.assertEquals(tablelist.get(i).getText(), DisplayedTableName, "Table not matched");
					searchColumn(ColumnName);
				}
			}
			break;

		}
	}

	// Method for searchColumn function
	public void searchColumn(String ColumnName) {
		driver.findElement(
				By.xpath("//bk-input[@placeholder='Search columns']//div[@class='bk-input-container-inner']"))
				.sendKeys(ColumnName);
		List<WebElement> Columnlist = driver.findElements(By.xpath("//div[@tabulator-field='attrNm']"));
		for (int i = 0; i <= Columnlist.size(); i++) {
			if (Columnlist.get(i).getText().equalsIgnoreCase(ColumnName)) {
				logger.info("Found search column : " + Columnlist.get(i).getText());
			} else {
				logger.info("Search Column does not exist");
			}

		}

	}

	// Method for Create File Connection
	public void createFileConnection(String FilePath, String FileProtocol, String FileHost, String FilePort,
			String FileUsername, String FilePassword, String Keyfile, String AuthenticationType, String Passphrasekey) {
		driver.findElement(By.xpath(OR.getProperty("FILEPATH"))).clear();
		pause(1000);
		driver.findElement(By.xpath(OR.getProperty("FILEPATH"))).sendKeys(FilePath);

		if (FileProtocol != null) {
			driver.findElement(By.xpath(OR.getProperty("FILES_ON_REMOTE_LOCATION_CHECKBOX"))).click();
			switch (FileProtocol) {
			case "FTP":
				ftpConnection(FileHost, FilePort, FileUsername, FilePassword);
				break;

			case "SFTP":
				sftpConnection(AuthenticationType, FileHost, FilePort, FileUsername, FilePassword, Keyfile,
						Passphrasekey);
				break;
			}
		}
	}

	public void ftpConnection(String FileHost, String FilePort, String FileUsername, String FilePassword) {

		driver.findElement(By.xpath(OR.getProperty("FILE_HOST"))).sendKeys(FileHost);
		driver.findElement(By.xpath(OR.getProperty("FILE_PORT"))).sendKeys(FilePort);
		driver.findElement(By.xpath(OR.getProperty("FILE_USERNAME"))).sendKeys(FileUsername);
		driver.findElement(By.xpath(OR.getProperty("FILE_PASSWORD"))).sendKeys(FilePassword);
	}

	public void sftpConnection(String AuthenticationType, String FileHost, String FilePort, String FileUsername,
			String FilePassword, String Keyfile, String Passphrasekey) {
		// logger.info("SFTP Protocol is selected");
		driver.findElement(By.xpath(OR.getProperty("FILE_PROTOCOL"))).click();
		driver.findElement(By.xpath(OR.getProperty("SFTP_PROTOCOL"))).click();
		if (AuthenticationType.equals("Password")) {
			logger.info("AuthenticationType :Password is selected");
			ftpConnection(FileHost, FilePort, FileUsername, FilePassword);
		} else if (AuthenticationType.equals("Key")) {
			driver.findElement(By.xpath(OR.getProperty("SELECT_AUTHENTICATIONTYPE_KEY"))).click();
			driver.findElement(By.xpath(OR.getProperty("AUTHENTICATIONTYPE_KEY"))).click();
			logger.info("AuthenticationType :Key is selected");
			driver.findElement(By.xpath(OR.getProperty("FILE_HOST"))).sendKeys(FileHost);
			driver.findElement(By.xpath(OR.getProperty("FILE_PORT"))).sendKeys(FilePort);
			driver.findElement(By.xpath(OR.getProperty("FILE_USERNAME"))).sendKeys(FileUsername);
			driver.findElement(By.xpath(OR.getProperty("SFTP_KEY_FILE"))).sendKeys(Keyfile);
			// ******************************** Adavnced Configurations *******************
			if (Passphrasekey != null) {
				driver.findElement(By.xpath(OR.getProperty("SFTP_ADVANCED_CONFIGURATION_BUTTON"))).click();
				driver.findElement(By.xpath(OR.getProperty("SFTP_PASSPHRASE_KEY"))).sendKeys(Passphrasekey);
			}
		}
	}

	
	 // Method for Creating Azure BlobStorage and Google Cloud Storage Connection
	 
	public void createAzureBlobStorageAndGcsConnection(String Account, String AzureauthTypeInput, String azureKey,
			String selectValue, String Container) {

		driver.findElement(By.xpath(OR.getProperty("AZURE_ACCOUNT_AND_GCS_PROJECT_ID"))).sendKeys(Account);
		String AzureauthType = driver.findElement(By.xpath(OR.getProperty("AUTHENTICATION_TYPE_DROPDOWN"))).getText();
		if (AzureauthType.equals(AzureauthTypeInput)) {

			logger.info(AzureauthType + "is selected");

		} else {

			driver.findElement(By.xpath(OR.getProperty("AUTHENTICATION_TYPE_DROPDOWN"))).click();
			driver.findElement(By.xpath(OR.getProperty("AZURE_ACCOUNT_KEY_GCS_ACCESS_TOKEN_OPTION"))).click();
			logger.info(AzureauthType + "is selected");
		}
		driver.findElement(By.xpath(OR.getProperty("AZURE_KEY_GCS_SERVIVE_TOKEN"))).sendKeys(azureKey);
		if (selectValue.equals("Azure Blob Storage") || selectValue.equals("Google Cloud Storage")) {

			driver.findElement(By.xpath(OR.getProperty("AZURE_CONTAINER_GCS_BUCKET"))).sendKeys(Container);
		}
	}

	public void createPrestoConnection(String Host, String Port, String prestoAuthenticationType, String User,
			String catalogName, String schemaName) {
		driver.findElement(By.xpath(OR.getProperty("TS_INPUT_HOST"))).sendKeys(Host);
		driver.findElement(By.xpath(OR.getProperty("TS_INPUT_PORT"))).clear();
		driver.findElement(By.xpath(OR.getProperty("TS_INPUT_PORT"))).sendKeys(Port);

		driver.findElement(By.xpath(OR.getProperty("PRESTO_AUTHENTICATION_TYPE"))).click();
		List<WebElement> hiveAuth = driver.findElements(By.xpath(OR.getProperty("HIVE_DROPDOWN")));
		for (int i = 0; i < hiveAuth.size(); i++) {
			String secutext = hiveAuth.get(i).getText();
			logger.info(secutext);
			logger.info(prestoAuthenticationType);
			if ((secutext.trim()).equalsIgnoreCase(prestoAuthenticationType.trim())) {
				WebElement secbuttn = hiveAuth.get(i);
				secbuttn.click();
				logger.info("Selected AUTHENTICATION TYPE" + secutext);
				break;
			}
		}

		driver.findElement(By.xpath(OR.getProperty("ENTER_USERNAME"))).sendKeys(User);
		driver.findElement(By.xpath(OR.getProperty("PRESTO_CATALOG_NAME"))).sendKeys(catalogName);
		driver.findElement(By.xpath(OR.getProperty("PRESTO_SCHEMA_NAME"))).sendKeys(schemaName);

	}

	public void clickOnClose() {
		WebElement close = driver.findElement(By.xpath(OR.getProperty("BUTTON_CLOSE")));
		String closeText = close.getText();
		close.click();
		logger.info("Clicked on " + closeText);
	}
}