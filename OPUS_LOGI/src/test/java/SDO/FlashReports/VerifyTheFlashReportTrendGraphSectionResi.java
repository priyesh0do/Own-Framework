package SDO.FlashReports;

import java.util.List;
import java.util.Map;

import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Driver.BrowserOpen;
import ObjectRepository.General.FilterSection;
import ObjectRepository.General.HomePage;
import ObjectRepository.General.Login;
import ObjectRepository.SDO.ResidentialFlashReport;
import Opus.BaseClass;
import SupportLibraries.Excel;
import SupportLibraries.GlobalVariables;
import SupportLibraries.Report;
import SupportLibraries.Util;
import TestData.GlobalTestData;

public class VerifyTheFlashReportTrendGraphSectionResi extends BaseClass {
	
	Login login;
	HomePage sdiMegaObj;
	FilterSection sdoFilterObj;
	ResidentialFlashReport sdoResiObj;

	/**
	 * 
	 * This test case will login in opus application
	 * 
	 * Verify Residential flash report
	 * 
	 */

	@Test(groups = "Opus", dataProvider = "residentialFlashReport")
	public void OpusSDO_ResidentialFlashReportTrendGraph(String ReportName, String siteID, String LOB, String FromDate,
			String ToDate, String Route, String Driver, String ClockInTime, String ClockOutTime) throws Exception {
		if (!Util.isTestCaseExecutable("Verify the Flash Report trend graph section Resi",GlobalVariables.sdoTestCase))
		{
			test = Report.testCreate(extent, test, "Verify the Flash Report trend graph section Resi");
			Report.SkipTest(test, "Skipping the test as runmode is NO");			
		}
		else
		{
			test = Report.testCreate(extent, test, "OpusSDO_ResidentialFlashReportTrendGraph");
			driver = BrowserOpen.StartBrowser(driver, configProp.getProperty("Browser"), configProp.getProperty("URL"));
			login = new Login(driver, test);
			login.LoginSDI(configProp.getProperty("userId"), configProp.getProperty("Password"));
			System.out.println("Login successfully done");
			// Select Flash Report
			sdiMegaObj = new HomePage(driver, test);
			sdiMegaObj.ClickOnReport(GlobalTestData.ReportName);
			sdoResiObj = new ResidentialFlashReport(driver, test);
			
			sdoFilterObj = new FilterSection(driver, test);
			// select site
			sdoFilterObj.selectSite(siteID);
			//select LOB
			sdoFilterObj.selectLOB(LOB);
			// Select Route
			sdoFilterObj.selectRoute(Route);
			// Select Driver
			sdoFilterObj.selectDriver(Driver);
			// Select Date Range
			sdoFilterObj.selectFromDate(FromDate);
			sdoFilterObj.selectToDate(ToDate);
			sdoFilterObj.selectGO();
			// Switch to frame
			
			Util.selectFrame("opusReportContainer,subReportContainer");
			//Validate the X and Y Axis of the Graph
			sdoResiObj.validateAxisInGraph(FromDate, ToDate);
			Util.switchToDefaultWindow();
			
			Util.selectFrame("opusReportContainer,subReportContainer");
			//Validate the legends in the graph
			sdoResiObj.validateLegendInGraph();
			Util.switchToDefaultWindow();
			
			Util.selectFrame("opusReportContainer,subReportContainer");
			//Validate the Points on the Graph
			sdoResiObj.validateTheGraphPoints();
			Util.switchToDefaultWindow();
			
			driver.quit();
		}

	}

	@DataProvider(name = "residentialFlashReport")
	public Object[][] testData() throws Exception {
		Excel ex = new Excel();
		Object[][] arrayObject = ex.getTableArray(configProp.getProperty("SDOTestCaseExcel"),
				configProp.getProperty("SDOTestDataSheetResi"));
		return arrayObject;
	}

}
