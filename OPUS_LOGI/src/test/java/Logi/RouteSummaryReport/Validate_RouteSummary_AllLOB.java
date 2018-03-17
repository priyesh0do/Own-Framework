package Logi.RouteSummaryReport;

import java.io.IOException;
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
import ObjectRepository.Logi.RouteSummaryReport;
import Opus.BaseClass;
import SupportLibraries.Excel;
import SupportLibraries.Report;
import SupportLibraries.Util;

public class Validate_RouteSummary_AllLOB extends BaseClass{
	Login login;
	HomePage sdiMegaObj;
	FilterSection sdoFilterObj;
	RouteSummaryReport RouteSummaryObj;
	EfficiencyPerformanceReport effPerfObj;
	EfficiencySummaryReport effSumObj;
	//private String ClockOutTime;
	@Test(groups = "Opus", dataProvider = "LOGI_Reports_Data")
	public void Validation_RouteSummaryReport_DataValidation_COM(String Scenario,String RunMode,String ReportName, String SiteID, String LOB,
			String FromDate, String ToDate, String Route,String Driver,String ClockInTime,String ClockOutTime,String Ticket) throws InterruptedException, IOException {
		
		if(Scenario.equalsIgnoreCase("Validate_RouteSummaryReport_AllLOB") & RunMode.equals("Yes"))
		{
			test = Report.testCreate(extent, test, "Validate_RouteSummaryReport_AllLOB");
		    driver = BrowserOpen.StartBrowser(driver, configProp.getProperty("Browser"), configProp.getProperty("URL"));
			login = new Login(driver, test);
			login.LoginSDI(configProp.getProperty("userId"), configProp.getProperty("Password"));
			System.out.println("Login successfully done");
			// Select RM Dashboard
			
			sdiMegaObj = new HomePage(driver, test);
			sdiMegaObj.ClickOnReport(ReportName);
			sdoFilterObj = new FilterSection(driver, test);
			effPerfObj = new EfficiencyPerformanceReport(driver, test);
			effSumObj = new EfficiencySummaryReport(driver, test);
			
			//sdoFilterObj.selectTier(Tier);
			//sdoFilterObj.selectArea(Area);
			//sdoFilterObj.selectBU(BU);
			sdoFilterObj.SelectMutipleSites(SiteID,ReportName);
			sdoFilterObj.selectLOB(LOB);
			//sdoFilterObj.selectSubLOB(SubLOB);
			sdoFilterObj.selectFromDate(FromDate);
			sdoFilterObj.selectToDate(ToDate);
			sdoFilterObj.selectGO();
			
			RouteSummaryObj = new RouteSummaryReport(driver, test);
			Util.pageScroll(0, 250);
			// Switch to frame
			Util.selectFrame("opusReportContainer,subReportContainer");
			System.out.println("t1");
			Map<String, List<String>> actualSummaryTable = RouteSummaryObj.getActualSummaryTableData();	
			Thread.sleep(2000);
			Map<String, List<String>> actualDetailTable = RouteSummaryObj.getActualDetailTableData(); 
			Thread.sleep(1000);
			
			RouteSummaryObj.validateRouteSummaryData(actualSummaryTable, actualDetailTable, LOB);

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
