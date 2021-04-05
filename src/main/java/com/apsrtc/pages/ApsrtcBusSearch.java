package com.apsrtc.pages;

import org.openqa.selenium.By;

import com.apsrtc.base.URLNavigations;


public class ApsrtcBusSearch extends ApsrtcMainClass {
	URLNavigations url = new URLNavigations();
//	SyncProperties syncp = new SyncProperties();

	public void createTeradataConnection(String FromRoute, String ToRoute) throws InterruptedException {
		// url.openApsrtc();
		loginApplication(); /********* url.openApsrtc(); Also works *********/
		enterConnectionDetails(FromRoute, ToRoute);
		apsrtcLogout();
		/*headerVerification();
		clickOnConnections();
		addConnection();
		enterConnectionDetails(ConnectionName, SelectValue);
		pause(2000);
		addTableSync();
		tableSetup(DBtype, Dbname, AddTableSync);
		setUpSync();
		pause(3000);
		scheduleDoesNotRepeat();
		advancedSetup();
	
		if ((syncProperty != null) && (syncProperty.equals("Yes"))) {
			syncp.tsSyncProperties(SelectValue, addCondition, syncMode, synCompletionValue, moveItPath, waitForFile,
					preSyncQuery, postSyncQuery, extractionModeInput, dataloadingModeInput, syncColumnDelimiter,
					nullValue, nullValuedrp, escapeCharacter, enclosingCharacterInput, escapeUnclosedField,
					fieldOptionallyEnclosedBy, bufferSize, dateStyleValue, dateDelimiter, timeStyleValue, timeDelimiter,
					timeDelimiterValue, booleanRepresentationValue, skipTrailerRowsValue, readerEncoderValue, fetchSize,
					nullIfValue, maxFileSize, parallelValue, stageLocation, allowLargeResultSet, readTimeout,maxIgnoredRow,
					tsLoadOptions);
			driver.findElement(By.xpath(OR.getProperty("MAP_TABLES_COLUMNS_TAB"))).click();
		}

		mapTableAndColumns(TsTable);
		pause(10000);
		clickOnConnections();
		pause(2000);
		selectConnection(ConnectionName);
		pause(2000);
		clickOnTable();
		Thread.sleep(3000);
		selectTable(TsTable);
		pageRefresh();
		clickOnViewLogIcon();
		verifyExtractType(ExtractType);
		verifyExtractionMode(ExtractionMode);
		loadType();
		numberOfRows();
		clickOnClose();
		tsLogout();
		pause(5000);*/
	}

}

