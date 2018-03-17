package Logi.DowntimeDetailReport;

import java.util.List;
import java.util.Map;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Driver.BrowserOpen;
import ObjectRepository.General.FilterSection;
import ObjectRepository.General.HomePage;
import ObjectRepository.General.Login;
import ObjectRepository.Logi.DowntimeDetailReport;
import Opus.BaseClass;
import SupportLibraries.Excel;
import SupportLibraries.Report;
import SupportLibraries.Util;

public class Validate_DowntimeDetail_AllLOB extends BaseClass{
	Login login;
	HomePage sdiMegaObj;
	FilterSection sdoFilterObj;
	DowntimeDetailReport DowntimeDetailObj;
	@Test(groups = "Opus", dataProvider = "LOGI_Reports_Data")
	public void Validation_DowntimeDetailReport_AllLOB(String Scenario,String RunMode,String ReportName, String SiteID, String LOB,
			String FromDate, String ToDate, String Route,String Driver,String ClockInTime,String ClockOutTime,String Ticket) throws Exception {	
		
		if(Scenario.equalsIgnoreCase("Validate_DowntimeDetailReport_AllLOB") & RunMode.equals("Yes"))
		{
			 test = Report.testCreate(extent, test, "Validate_DowntimeDetailReport_AllLOB");
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
				sdoFilterObj.selectMultiLOB(LOB);
				/*sdoFilterObj.selectRoute(Route);
				sdoFilterObj.selectDriver(Driver);*/
				sdoFilterObj.selectFromDate(FromDate);
				sdoFilterObj.selectToDate(ToDate);
				sdoFilterObj.selectGO();
				DowntimeDetailObj=new DowntimeDetailReport(driver, test);
				Util.pageScroll(0, 250);
				// Switch to frame
				Util.selectFrame("opusReportContainer,subReportContainer");	
				Map<String, List<String>> actualDownTimeDetailSummaryData=DowntimeDetailObj.getDownTimeDetailSummaryData();
				Map<String, List<String>> PerformanceSubviewDetailTable=DowntimeDetailObj.getDownTimeDetailReportDetailDataPerformanceSubview();
				DowntimeDetailObj.RedirectedtoActualSubview();              
				Map<String, List<String>> ActualSubviewDetailTable=DowntimeDetailObj.getDownTimeDetailReportDetailDataActualSubview();
				DowntimeDetailObj.RedirectedtoPerformanceSubview();
				Map<String,Map<String, List<String>>> actualDownTimeDrilldownTablePerformanceSubview=DowntimeDetailObj.getActualDowntimeDrilldownTableDataPerformanceSubview(Route);
				Util.selectFrame("opusReportContainer,subReportContainer");	
				DowntimeDetailObj.RedirectedtoActualSubview(); 				
				Map<String,Map<String, List<String>>> actualDownTimeDrilldownTableActualSubview=DowntimeDetailObj.getActualDowntimeDrilldownTableDataActualSubview(Route);
				Util.selectFrame("opusReportContainer,subReportContainer");	
				DowntimeDetailObj.RedirectedtoPerformanceSubview();
				DowntimeDetailObj.ValidateDowntimeDetailSummaryData(actualDownTimeDetailSummaryData, PerformanceSubviewDetailTable);
				DowntimeDetailObj.ValidateDowntimeDetailReportPerformanceSubviewData(PerformanceSubviewDetailTable, FromDate, ToDate, Route, Driver, LOB);
				DowntimeDetailObj.ValidateDowntimeDetailReportActualSubviewData(ActualSubviewDetailTable,actualDownTimeDrilldownTableActualSubview,FromDate, ToDate, Route, Driver, LOB);
				
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
