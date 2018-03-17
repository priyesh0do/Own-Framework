package Logi.SummaryDashboardReport;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Driver.BrowserOpen;
import ObjectRepository.General.FilterSection;
import ObjectRepository.General.GraphSection;
import ObjectRepository.General.HomePage;
import ObjectRepository.General.Login;
import ObjectRepository.Logi.DisposalCycleSummaryReport;
import ObjectRepository.Logi.DowntimeDetailReport;
import ObjectRepository.Logi.EfficiencyPerformanceReport;
import ObjectRepository.Logi.EfficiencySummaryReport;
import ObjectRepository.Logi.IdleSummaryReport;
import ObjectRepository.Logi.PostRouteSummary;
import ObjectRepository.Logi.PreRouteSummary;
import ObjectRepository.Logi.RouteSummaryReport;
import ObjectRepository.Logi.SummaryDashboardReport;
import Opus.BaseClass;
import SupportLibraries.Excel;
import SupportLibraries.GlobalVariables;
import SupportLibraries.Report;
import SupportLibraries.Util;
import TestData.GlobalExpectedColumns;
import TestData.GlobalTestData;

public class Validate_SummaryDashboard_Report_RESI extends BaseClass{
	Login login;
	HomePage sdiMegaObj;
	FilterSection sdoFilterObj;
	GraphSection graphObj;
	SummaryDashboardReport SummaryDashboardObj;
	EfficiencyPerformanceReport effPerfObj;
	PreRouteSummary prerteObj;
	EfficiencySummaryReport effSummaryObj;
	PostRouteSummary postrteObj;
	DisposalCycleSummaryReport DispCycleObj;
	IdleSummaryReport idleObj;
	RouteSummaryReport routeSummaryObj;
	
	@Test(groups = "Opus", dataProvider = "LOGI_Reports_Data")
	public void Validate_SummaryDashboard_ReportValidation_RESI(String Scenario,String RunMode,String ReportName, String SiteID, String LOB,
			String FromDate, String ToDate, String Route,String Driver,String ClockInTime,String ClockOutTime,String Ticket) throws Exception {	
		
		if(Scenario.equalsIgnoreCase("Validate_SummaryDashboard_Report_RESI") & RunMode.equals("Yes"))
		{
			try {
			    test = Report.testCreate(extent, test, "Validate_SummaryDashboard_Report_RESI");
			    driver = BrowserOpen.StartBrowser(driver, configProp.getProperty("Browser"), configProp.getProperty("URL"));
				login = new Login(driver, test);
				login.LoginSDI(configProp.getProperty("userId"), configProp.getProperty("Password"));
				System.out.println("Login successfully done");
				List<String> actualSummaryTableColumns=new ArrayList<>();
				// Select Summary Dashboard
						
				sdiMegaObj = new HomePage(driver, test);
				sdoFilterObj=new FilterSection(driver, test);
				graphObj = new GraphSection(driver, test);
				effPerfObj=new EfficiencyPerformanceReport(driver, test);
				prerteObj = new PreRouteSummary(driver, test);
				effSummaryObj=new EfficiencySummaryReport(driver, test);
				postrteObj = new PostRouteSummary(driver, test);
				DispCycleObj = new DisposalCycleSummaryReport(driver, test); 
				idleObj = new IdleSummaryReport(driver, test); 
				routeSummaryObj = new RouteSummaryReport(driver, test);
				
				//**************************************************************************************************************************************************
				
				sdiMegaObj.ClickOnReport(ReportName);
				SummaryDashboardObj = new SummaryDashboardReport(driver,test);
				//sdiMegaObj.ClickOnReport("Efficiency Performance");
				
				//Enter the site
				sdoFilterObj.selectSite(SiteID);
				
				//Enter the required date range
				sdoFilterObj.selectFromDate(FromDate);
				sdoFilterObj.selectToDate(ToDate);
				
				//GO
				sdoFilterObj.GoBtn();
				Thread.sleep(3000);
							
				//Reading the Overview Table for Driver
				Util.switchToDefaultWindow();
				Util.selectFrame("opusReportContainer,subReportContainer");	
				Map<String, List<String>> actualDriverSummaryTable = SummaryDashboardObj.getActualDriverSummaryTableData();
				
				//****************************************************************************************************************************************************
				
				//Switching to Efficiency Summary Table
				sdoFilterObj.openReportInNewTab("Efficiency Summary");
				sdoFilterObj.selectLOB(LOB);
				//Enter date range if required
				sdoFilterObj.GoBtn();
				//Reading data from Efficiency Summary Table
				Util.switchToDefaultWindow();
				Util.selectFrame("opusReportContainer,subReportContainer");	
				Map<String,List<String>> efficiencySummaryComTableData=effSummaryObj.getActualSummaryTableData();
				driver.close();
				Util.switchToDefaultTab();
			
				//****************************************************************************************************************************************************
				
				//Switching to Efficiency Performance Report
				sdoFilterObj.openReportInNewTab("Efficiency Performance");
				sdoFilterObj.selectSite(SiteID);
				sdoFilterObj.selectLOB(LOB);
				sdoFilterObj.selectFromDate(FromDate);
				sdoFilterObj.selectToDate(ToDate);
				//Enter date range if required
				sdoFilterObj.GoBtn();
				
				//Reading Summary Table of Eff Perf for COM LOB 
				Util.switchToDefaultWindow();
				Util.selectFrame("opusReportContainer,subReportContainer");	
				Map<String,List<String>> efficiencyPerformanceCOMSummaryTableData=effPerfObj.getActualSummaryTableData();
				
				//select disposal load
				Util.switchToDefaultWindow();
				Util.selectFrame("opusReportContainer,subReportContainer");	
				driver.findElement(By.xpath(".//*[@id='inpSubReportOpt_3']")).click();
				Map<String,List<String>> efficiencyPerformanceCOMDisposalLoadDetailTableData=effPerfObj.getActualDisposalLoadsSubViewTableData();
				
				driver.close();
				Util.switchToDefaultTab();
				
				//*******************************************************************************************************************************************************
				//Reading Data from Pre Route Summary Report
				sdoFilterObj.openReportInNewTab("Pre-Route Summary");
				//sdoFilterObj.selectSite(SiteID);
				sdoFilterObj.selectLOB(LOB);
				sdoFilterObj.selectFromDate(FromDate);
				sdoFilterObj.selectToDate(ToDate);
				//Enter date range if required
				sdoFilterObj.GoBtn();
				
				//Reading Summary Table of Pre Route Detail for LOB 
				Util.switchToDefaultWindow();
				Util.selectFrame("opusReportContainer,subReportContainer");	
				Map<String,List<String>> actualPreRouteSummaryTable=prerteObj.getActualPreRouteSummaryTableData();
				driver.close();
				Util.switchToDefaultTab();
				
				//*******************************************************************************************************************************************************
				// Reading Post Route Summary Table
				sdoFilterObj.openReportInNewTab("Post-Route Summary");
				//sdoFilterObj.selectSite(SiteID);
				sdoFilterObj.selectLOB(LOB);
				sdoFilterObj.selectFromDate(FromDate);
				sdoFilterObj.selectToDate(ToDate);
				//Enter date range if required
				sdoFilterObj.GoBtn();
				
				//Reading Summary Table of Post Route Detail for LOB 
				Util.switchToDefaultWindow();
				Util.selectFrame("opusReportContainer,subReportContainer");	
				Map<String,List<String>> actualPostRouteSummaryTable=postrteObj.getPostRouteSummaryTableData();
				driver.close();
				Util.switchToDefaultTab();
				
				//*******************************************************************************************************************************************************
				// Reading Disposal Cycle Summary
				sdoFilterObj.openReportInNewTab("Disposal Cycle Summary");
				//sdoFilterObj.selectSite(SiteID);
				sdoFilterObj.selectLOB(LOB);
				sdoFilterObj.selectFromDate(FromDate);
				sdoFilterObj.selectToDate(ToDate);
				//Enter date range if required
				sdoFilterObj.GoBtn();
				
				//Reading Summary Table of  Disposal Cycle for LOB 
				Util.switchToDefaultWindow();
				Util.selectFrame("opusReportContainer,subReportContainer");	
				Map<String,List<String>> actualDisposalCycleSummaryTable=DispCycleObj.getActualSummaryTableData();
				driver.close();
				Util.switchToDefaultTab();
				
				//*******************************************************************************************************************************************************
				//Reading Idle Detail summary table
				sdoFilterObj.openReportInNewTab("Idle Summary");
				//sdoFilterObj.selectSite(SiteID);
				sdoFilterObj.selectLOB(LOB);
				sdoFilterObj.selectFromDate(FromDate);
				sdoFilterObj.selectToDate(ToDate);
				//Enter date range if required
				sdoFilterObj.GoBtn();
				
				//Reading Summary Table of  Disposal Cycle for LOB 
				Util.switchToDefaultWindow();
				Util.selectFrame("opusReportContainer,subReportContainer");	
				Map<String,List<String>> actualIdleSummaryTable=idleObj.getActualSummaryTableData();
				driver.close();
				Util.switchToDefaultTab();	
				
				//*******************************************************************************************************************************************************
				//Reading Route Summary Table 
				sdoFilterObj.openReportInNewTab("Route Summary");
				//sdoFilterObj.selectSite(SiteID);
				sdoFilterObj.selectLOB(LOB);
				sdoFilterObj.selectFromDate(FromDate);
				sdoFilterObj.selectToDate(ToDate);
				//Enter date range if required
				sdoFilterObj.GoBtn();
				
				//Reading Summary Table of  Disposal Cycle for LOB 
				Util.switchToDefaultWindow();
				Util.selectFrame("opusReportContainer,subReportContainer");	
				Map<String,List<String>> actualRouteSummarySummaryTable=routeSummaryObj.getActualSummaryTableData();
				driver.close();
				Util.switchToDefaultTab();	
					
				//*******************************************************************************************************************************************************
				
				//Driver Overview section validation
				SummaryDashboardObj.validateDriverOverviewTableData(LOB.toUpperCase(),actualDriverSummaryTable,efficiencySummaryComTableData,efficiencyPerformanceCOMSummaryTableData,efficiencyPerformanceCOMDisposalLoadDetailTableData);
				
				
				//*******************************************************************************************************************************************************
				
				//select the Driver & Helper Tab
				SummaryDashboardObj.SwitchReportTabs("Driver");
				SummaryDashboardObj.SwitchReportTabs("Driver & Helper");
				
				//*******************************************************************************************************************************************************
				
				
				//Validate Summary column Names of the Driver & Helper Tab > Summary Dashboard
				actualSummaryTableColumns = Util.getColumnNames("dtOverviewHlp");
				Util.validateColumns(actualSummaryTableColumns, GlobalExpectedColumns.driverAndHelperTabSummaryDashboardColumns, "Summary Dashboard");
				
				
				//*******************************************************************************************************************************************************
				
				//Reading Driver and Helper Overview section
				Util.switchToDefaultWindow();
				Util.selectFrame("opusReportContainer,subReportContainer");	
				Map<String, List<String>> actualDriverAndHelperSummaryTable = SummaryDashboardObj.getActualDriverAndHelperSummaryTableData();
				
				//*******************************************************************************************************************************************************
				
				//Driver Overview section validation
				SummaryDashboardObj.validateDriverAndHelperOverviewTableData(LOB.toUpperCase(),actualDriverAndHelperSummaryTable,efficiencySummaryComTableData);
				
				//*******************************************************************************************************************************************************
				
				Util.switchToDefaultWindow();
				Util.PageScrollDown(0, 500);
				//Validate Route Summary
				SummaryDashboardObj.validateRouteSummarySection(LOB,actualPreRouteSummaryTable,actualPostRouteSummaryTable,actualDisposalCycleSummaryTable,actualIdleSummaryTable,actualRouteSummarySummaryTable);
				
				//*******************************************************************************************************************************************************			
				}
				catch (Exception e)
				{
					System.out.println(e.getMessage());
				}
				
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
