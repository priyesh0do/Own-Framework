package SDO.AMHuddle_FlashReport;

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
import TestData.GlobalExpectedColumns;
import TestData.GlobalTestData;

public class Flash_report_trend_graph_for_30_completed_weekdays_one_day_range_RESI_2 extends BaseClass {

	Login login;
	HomePage sdiMegaObj;
	FilterSection sdoFilterObj;
	ResidentialFlashReport sdoResiObj;
	
	//sdoRollOffObj
	
	/**
	 * 
	 * This test case will login in opus application
	 * 
	 * Verify columns of Accordion in Roll off flash report
	 * 
	 */
	
	@Test(groups = "Opus", dataProvider = "residentialFlashReport")
	public void OpusSDO_FlashReportTrendGraphResi(String ReportName, String siteID, String LOB, String FromDate,
			String ToDate, String Route, String Driver, String ClockIn,String ClockOut) throws Exception {
		
		if (!Util.isTestCaseExecutable("Flash_report_trend_graph_for_30_completed weekdays_one day range_RESI_2",GlobalVariables.sdoTestCase))
			throw new SkipException("Skipping the test as runmode is NO");	
		
		    test = Report.testCreate(extent, test, "OpusSDO_FlashReportTrendGraphResi");
		    driver = BrowserOpen.StartBrowser(driver, configProp.getProperty("Browser"), configProp.getProperty("URL"));
			login = new Login(driver, test);
			login.LoginSDI(configProp.getProperty("userId"), configProp.getProperty("Password"));
			System.out.println("Login successfully done");
			
			// Select Flash Report
			sdiMegaObj = new HomePage(driver, test);
			sdiMegaObj.ClickOnReport(GlobalTestData.ReportName);
			sdoResiObj = new ResidentialFlashReport(driver, test);
			// Switch to frame
			Util.selectFrame("opusReportContainer,subReportContainer,subRptRMHuddle");
			// Click on commercial Flash report
			sdoResiObj.clickResidentialFlashReport();
			sdoFilterObj = new FilterSection(driver, test);
			// select site
			sdoFilterObj.selectSite(siteID);
			//Select LOB
			sdoFilterObj.selectLOB(LOB);
			// Select Route
			sdoFilterObj.selectRoute(Route);
			// Select Driver
			sdoFilterObj.selectDriver(Driver);
			// Select Date Range
			sdoFilterObj.selectFromDate(FromDate);
			sdoFilterObj.selectToDate(ToDate);
			sdoFilterObj.selectGO();			
			driver.quit();
			
		

	}

	@DataProvider(name = "residentialFlashReport")
	public Object[][] testData() throws Exception {
		Excel ex = new Excel();
		Object[][] arrayObject = ex.getTableArray("R:\\Automation\\OPUS\\OPUS_SDO\\Test Data\\Flash Report.xlsx","ResidentialFlashReport");
		return arrayObject;
	}
}
