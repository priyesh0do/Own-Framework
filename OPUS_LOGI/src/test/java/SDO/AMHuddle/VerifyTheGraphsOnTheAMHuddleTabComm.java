package SDO.AMHuddle;

import java.util.List;

import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Driver.BrowserOpen;
import ObjectRepository.General.FilterSection;
import ObjectRepository.General.HomePage;
import ObjectRepository.General.Login;
import ObjectRepository.SDO.CommercialFlashReport;
import ObjectRepository.SDO.RMDashboardReport;
import Opus.BaseClass;
import SupportLibraries.Excel;
import SupportLibraries.GlobalVariables;
import SupportLibraries.Report;
import SupportLibraries.Util;
import TestData.GlobalExpectedColumns;
import TestData.GlobalTestData;

public class VerifyTheGraphsOnTheAMHuddleTabComm extends BaseClass {
	
	Login login;
	HomePage sdiMegaObj;
	RMDashboardReport rmDashboardObj;
	FilterSection sdoFilterObj;
	CommercialFlashReport sdoCommObj;
	
	/**
	 * 
	 * This test case will login in opus application
	 * 
	 * Verify commercial flash report DOW and Historical graph on AM Huddle
	 * 
	 */
	
	@Test(groups = "Opus", dataProvider = "commercialFlashReport")
	public void VerifyTheGraphOnAMHuddleTabComm(String ReportName, String siteID, String LOB, String FromDate,
			String ToDate, String Route, String Driver, String ClockInTime,String ClockOutTime) throws Exception {
		if (!Util.isTestCaseExecutable("Verify the graphs on the AM Huddle tab Comm",GlobalVariables.sdoTestCase))
		{
			test = Report.testCreate(extent, test, "Verify the graphs on the AM Huddle tab Comm");
			Report.SkipTest(test, "Skipping the test as runmode is NO");			
		}
		else
		{
			test = Report.testCreate(extent, test, "Verify the graphs on the AM Huddle tab Comm");
			driver = BrowserOpen.StartBrowser(driver, configProp.getProperty("Browser"), configProp.getProperty("URL"));
			login = new Login(driver, test);
			login.LoginSDI(configProp.getProperty("userId"), configProp.getProperty("Password"));
			System.out.println("Login successfully done");
			
			// Select RM Dashboard
			sdiMegaObj = new HomePage(driver, test);
			//sdiMegaObj.verifyAllLinks();
			sdiMegaObj.ClickOnReport(GlobalTestData.RMDashBoardReport);
			sdoFilterObj = new FilterSection(driver, test);
			sdoFilterObj.clickReset();
			sdoFilterObj.selectSite(GlobalTestData.siteID);
			//sdoFilterObj.selectToDate(ToDate);
			sdoFilterObj.selectDateToDatePicker(ToDate);
			sdoFilterObj.selectGO();
			rmDashboardObj=new RMDashboardReport(driver, test);
			//Switch to AM Huddle Frame
			Util.selectFrame("opusReportContainer,subReportContainer,subRptRMHuddle");
			//Verify DOW graph radio button is by default selected
			rmDashboardObj.isDOWGraphSelected();
			//Validate Commercial DOW graph
			rmDashboardObj.validateCommercialDOWgraph(ToDate);
			//Switch to historical graph
			rmDashboardObj.selectHistoricalGraph();
			//Verify Historical graph radio button is selected
			rmDashboardObj.isHistoricalGraphSelected();
			//Validate Commercial Historical graph
			rmDashboardObj.validateCommercialHistoricalGraph();
			driver.quit();
		}	
			
	}
	@DataProvider(name = "commercialFlashReport")
	public Object[][] testData() throws Exception {
		Excel ex = new Excel();
		Object[][] arrayObject = ex.getTableArray("R:\\Automation\\OPUS\\OPUS_SDO\\Test Data\\Flash Report.xlsx",
				"CommercialFLashReport");
		return arrayObject;
	}
}
