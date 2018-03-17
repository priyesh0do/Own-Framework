package Logi.EfficiencySummary;

import java.util.List;
import java.util.Map;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Driver.BrowserOpen;
import ObjectRepository.General.FilterSection;
import ObjectRepository.General.HomePage;
import ObjectRepository.General.Login;
import ObjectRepository.Logi.EfficiencyPerformanceReport;
import ObjectRepository.Logi.EfficiencySummaryReport;
import Opus.BaseClass;
import SupportLibraries.Excel;
import SupportLibraries.Report;
import SupportLibraries.Util;

public class Validate_EfficiencySummaryReport_RO extends BaseClass{
	
	Login login;
	HomePage sdiMegaObj;
	FilterSection sdoFilterObj;
	EfficiencySummaryReport effSummaryObj;
	EfficiencyPerformanceReport effPerformanceObj;
	@Test(groups = "Opus", dataProvider = "LOGI_Reports_Data")
	public void Validation_EfficiencySummaryReport_DataValidation_RO(String Scenario,String RunMode,String ReportName, String SiteID, String LOB,
			String FromDate, String ToDate, String Route,String Driver,String ClockInTime,String ClockOutTime,String Ticket) throws Exception {	
		
		if(Scenario.equalsIgnoreCase("Validate_EfficiencySummaryReport_RO") & RunMode.equals("Yes"))
		{
		
		    test = Report.testCreate(extent, test, "Validate_EfficiencySummaryReport_RO");
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
			sdoFilterObj.selectFromDate(FromDate);
			sdoFilterObj.selectToDate(ToDate);
			sdoFilterObj.selectGO();
			effSummaryObj = new EfficiencySummaryReport(driver, test);
			Util.pageScroll(0, 250);
			// Switch to frame
			Util.selectFrame("opusReportContainer,subReportContainer");			
			Map<String, List<String>> actualEfficiencySummaryTable = effSummaryObj.getActualSummaryTableData();
			Map<String, List<String>> actualEfficiencyDetailTable = effSummaryObj.getActualDetailTableData();	
			sdoFilterObj.openReportInNewTab("Efficiency Performance");
			sdoFilterObj.selectSite(SiteID);
			sdoFilterObj.selectLOB(LOB);		
			sdoFilterObj.selectFromDate(FromDate);
			sdoFilterObj.selectToDate(ToDate);
			sdoFilterObj.selectGO();
			effPerformanceObj = new EfficiencyPerformanceReport(driver, test);
			Util.pageScroll(0, 250);
			// Switch to frame
			Util.selectFrame("opusReportContainer,subReportContainer");	
			Map<String, List<String>> actualEfficiencyPerformanceSummaryTable = effPerformanceObj.getActualSummaryTableData();
			Map<String, List<String>> actualPerformanceTable = effPerformanceObj.getActualPerformanceSubViewTableData();
			Thread.sleep(2000);
			effPerformanceObj.selectTravelUnitsSubView();
			Thread.sleep(1000);
			Map<String, List<String>> actualTravelUnitsTable = effPerformanceObj.getActualTravelUnitsSubViewTableData();
			Util.switchToDefaultTab();	
			effSummaryObj.validateEfficiencySummaryData(actualEfficiencySummaryTable,actualEfficiencyDetailTable,actualTravelUnitsTable,LOB);
			effSummaryObj.validateEfficiencySummaryDetailData(actualEfficiencyDetailTable,actualEfficiencyPerformanceSummaryTable,actualPerformanceTable,actualTravelUnitsTable,LOB);
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
