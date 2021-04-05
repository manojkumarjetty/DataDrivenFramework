package com.apsrtc.test;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import java.util.Hashtable;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;

import com.apsrtc.base.BaseClass;
import com.apsrtc.pages.ApsrtcBusSearch;
import com.apsrtc.util.TestUtil;

public class ApsrtcBusSearchTest {

	@Test(dataProvider = "getData")
	public void tsDfCreationTest(Hashtable<String, String> data) throws InterruptedException {

		// check the run mode of test case
		if (!TestUtil.isExecutable("BusRouteSearch", BaseClass.xls1)) {
			throw new SkipException("Runmode set to NO");
		}

		// check runmode for data set
		if (!data.get("Runmode").equals("Y")) {
			throw new SkipException("Test Case Data Set Runmode is no.");
		}

		ApsrtcBusSearch testdf = new ApsrtcBusSearch();
		testdf.createTeradataConnection(data.get("FromRoute"), data.get("ToRoute"));
	}

	@DataProvider
	public Object[][] getData() {
		return TestUtil.getData("BusRouteSearch", BaseClass.xls1);
	}

	@AfterClass
	public void tearDown() throws Exception {
		BaseClass.closeBrowser();
	}
}