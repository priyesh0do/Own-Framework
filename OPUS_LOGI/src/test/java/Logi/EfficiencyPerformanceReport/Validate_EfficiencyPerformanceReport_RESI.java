package Logi.EfficiencyPerformanceReport;

import java.util.List;
import java.util.Map;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Driver.BrowserOpen;
import ObjectRepository.General.FilterSection;
import ObjectRepository.General.HomePage;
import ObjectRepository.General.Login;
import ObjectRepository.Logi.EfficiencyPerformanceReport;
import Opus.BaseClass;
import SupportLibraries.Excel;
import SupportLibraries.Report;
import SupportLibraries.Util;

public class Validate_EfficiencyPerformanceReport_RESI extends BaseClass {
	
	Login login;
	HomePage sdiMegaObj;
	FilterSection sdoFilterObj;
	EfficiencyPerformanceReport effPerformanceObj;
	private String ClockOutTime;
	@Test(groups = "Opus", dataProvider = "LOGI_Reports_Data")
	public void Validation_EfficiencyPerformanceReport_DetailSection_RESI(String Scenario,String RunMode,String ReportName, String SiteID, String LOB,
			String FromDate, String ToDate, String Route,String Driver,String ClockInTime,String ClockOutTime,String Ticket) throws Exception {	
		
		if(Scenario.equalsIgnoreCase("Validate_EfficiencyPerformanceReport_RESI") & RunMode.equals("Yes"))
		{
		
		    test = Report.testCreate(extent, test, "Validate_EfficiencyPerformanceReport_RESI");
		    driver = BrowserOpen.StartBrowser(driver, configProp.getProperty("Browser"), configProp.getProperty("URL"));
			login = new Login(driver, test);
			login.LoginSDI(configProp.getProperty("userId"), configProp.getProperty("Password"));
			System.out.println("Login successfully done");
			// Select RM Dashboard
			sdiMegaObj = new HomePage(driver, test);
			sdiMegaObj.ClickOnReport(ReportName);
			sdoFilterObj = new FilterSection(driver, test);
			// select site
			sdoFilterObj.selectSite(SiteID);
			sdoFilterObj.selectLOB(LOB);
			sdoFilterObj.selectRoute(Route);
			sdoFilterObj.selectDriver(Driver);
			sdoFilterObj.selectFromDate(FromDate);
			sdoFilterObj.selectToDate(ToDate);
			sdoFilterObj.selectGO();
			effPerformanceObj = new EfficiencyPerformanceReport(driver, test);
			Util.pageScroll(0, 250);
			// Switch to frame
			Util.selectFrame("opusReportContainer,subReportContainer");			
			Map<String, List<String>> actualSummaryTable = effPerformanceObj.getActualSummaryTableData();	
			Map<String, List<String>> actualPerformanceTable = effPerformanceObj.getActualPerformanceSubViewTableData();
			Thread.sleep(1000);
			Map<String,Map<String, List<String>>> actualHelperDrilldownTable =effPerformanceObj.getActualHelperDrilldownTableData(Route);
			Util.switchToDefaultWindow();
			Util.selectFrame("opusReportContainer,subReportContainer");
			effPerformanceObj.selectRouteSegmentsSubView();
			Thread.sleep(1000);
			Map<String, List<String>> actualRouteSegmentTable = effPerformanceObj.getActualRouteSegmentsSubViewTableData();
			effPerformanceObj.selectDisposalLoadsSubView();
			Thread.sleep(1000);
			Map<String, List<String>> actualDisposalLoadsTable = effPerformanceObj.getActualDisposalLoadsSubViewTableData();
			effPerformanceObj.selectTravelUnitsSubView();
			Thread.sleep(1000);
			Map<String, List<String>> actualTravelUnitsTable = effPerformanceObj.getActualTravelUnitsSubViewTableData();
			Thread.sleep(1000);
			effPerformanceObj.selectHelperTab();
			Map<String, List<String>> actualHelperDetail = effPerformanceObj.getActualHelperDetailTableData();
			Thread.sleep(1000);
			effPerformanceObj.validateSummaryData(actualSummaryTable, actualPerformanceTable, LOB, Route, Driver, FromDate, ToDate);
			effPerformanceObj.validatePerformanceSubViewData(actualPerformanceTable,actualRouteSegmentTable,actualHelperDrilldownTable,actualDisposalLoadsTable, LOB, Route, Driver, FromDate, ToDate);
			effPerformanceObj.validateHelperDrilldownData(actualHelperDrilldownTable, LOB, Route, Driver, FromDate, ToDate);
			effPerformanceObj.validateRouteSegmentSubViewData(actualRouteSegmentTable, actualDisposalLoadsTable, LOB, Route, Driver, ClockInTime, ClockOutTime,FromDate,ToDate);
			effPerformanceObj.validateDisposalLoadsSubViewData(actualRouteSegmentTable, actualDisposalLoadsTable, LOB, Route, Driver, ClockInTime, ClockOutTime, FromDate, ToDate);
			effPerformanceObj.validateTravelUnitsSubViewData(actualRouteSegmentTable, actualTravelUnitsTable,actualPerformanceTable,actualDisposalLoadsTable, LOB, Route, Driver, ClockInTime, ClockOutTime, FromDate, ToDate);
			effPerformanceObj.validateHeperTabData(actualHelperDetail,SiteID,LOB, Route, Driver, ClockInTime, ClockOutTime, FromDate, ToDate);
			driver.quit();
		 }	
		}
	@DataProvider(name = "LOGI_Reports_Data")
	public Object[][] testData() throws Exception {
		Excel ex = new Excel();
		Object[][] arrayObject = ex.getTableArray(configProp.getProperty("LOGITestCaseExcel"),
				configProp.getProperty("LOGITestDataSheet"));
		return arrayObject;
	}

}
