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
import ObjectRepository.Logi.EfficiencyPerformanceReport;
import ObjectRepository.Logi.EfficiencySummaryReport;
import ObjectRepository.Logi.SummaryDashboardReport;
import Opus.BaseClass;
import SupportLibraries.Excel;
import SupportLibraries.GlobalVariables;
import SupportLibraries.Report;
import SupportLibraries.Util;
import TestData.GlobalExpectedColumns;
import TestData.GlobalTestData;

public class Validate_SummaryDashboard_Report_UIValidation extends BaseClass{
	Login login;
	HomePage sdiMegaObj;
	FilterSection sdoFilterObj;
	GraphSection graphObj;
	SummaryDashboardReport SummaryDashboardObj;
	
	@Test(groups = "Opus", dataProvider = "LOGI_Reports_Data")
	public void Validate_SummaryDashboard_Report(String Scenario,String RunMode,String ReportName, String SiteID, String LOB,
			String FromDate, String ToDate, String Route,String Driver,String ClockInTime,String ClockOutTime,String Ticket) throws Exception {	
		
		if(Scenario.equalsIgnoreCase("Validate_SummaryDashboard_Report_UIValidation") & RunMode.equals("Yes"))
		{
			try {
		    test = Report.testCreate(extent, test, "Validate_SummaryDashboard_Report_UIValidation");
		    driver = BrowserOpen.StartBrowser(driver, configProp.getProperty("Browser"), configProp.getProperty("URL"));
			login = new Login(driver, test);
			login.LoginSDI(configProp.getProperty("userId"), configProp.getProperty("Password"));
			System.out.println("Login successfully done");
			List<String> actualSummaryTableColumns=new ArrayList<>();
			// Select Summary Dashboard
			sdiMegaObj = new HomePage(driver, test);
			sdoFilterObj=new FilterSection(driver, test);
			graphObj = new GraphSection(driver, test);
			ReportName = "Summary Dashboard";
			sdiMegaObj.ClickOnReport(ReportName);
			SummaryDashboardObj = new SummaryDashboardReport(driver,test);
			//sdiMegaObj.ClickOnReport("Efficiency Performance");
			
			//Validate the max value selection from drop down
			Util.validateMaxValueSelectionOnFilter("islGroupFilter");
			Util.validateMaxValueSelectionOnFilter("islBUFilter");
			Util.validateMaxValueSelectionOnFilter("islSiteFilter");
						
			//Validate whether values in filters are sorted or not
			Util.validateSortingOfFilterValues("islGroupFilter"); // Tier Filter
			Util.validateSortingOfFilterValues("islAreaFilter"); // Area Filter
			Util.validateSortingOfFilterValues("islBUFilter"); // BU Filter
			Util.validateSortingOfFilterValues("islSiteFilter"); // Sites Filter

			Util.validateFromAndToDate(ReportName);
			graphObj.validateGraphSection(ReportName,SiteID);
			graphObj.ValidateGraphLegends();
			
			//Enter the site
			sdoFilterObj.selectSite(SiteID);
			
			//Enter the required date range
			sdoFilterObj.selectFromDate(FromDate);
			sdoFilterObj.selectToDate(ToDate);
			
			//GO
			sdoFilterObj.GoBtn();
			Thread.sleep(3000);
			
			//Validate Summary column Names of the Driver Tab > Summary Dashboard
			actualSummaryTableColumns = Util.getColumnNames("dtOverview");
			Util.validateColumns(actualSummaryTableColumns, GlobalExpectedColumns.driverTabSummaryDashboardColumns, "Summary Dashboard");
			
			//Export PDF
			
			
			//Reading the Overview Table for Driver
			Util.switchToDefaultWindow();
			Util.selectFrame("opusReportContainer,subReportContainer");	
			Map<String, List<String>> actualDriverSummaryTable = SummaryDashboardObj.getActualDriverSummaryTableData();
			
			//Switching to Efficiency Summary Table
			sdoFilterObj.openReportInNewTab("Efficiency Summary");
			EfficiencySummaryReport effSummaryObj=new EfficiencySummaryReport(driver, test);
			sdoFilterObj.selectLOB("Commercial");
			//Enter date range if required
			sdoFilterObj.GoBtn();
			
			//Reading data from Efficiency Summary Table
			Util.switchToDefaultWindow();
			Util.selectFrame("opusReportContainer,subReportContainer");	
			Map<String,List<String>> efficiencySummaryComTableData=effSummaryObj.getActualSummaryTableData();
						
			sdoFilterObj.selectLOB("Residential");
			sdoFilterObj.GoBtn();
			Util.switchToDefaultWindow();
			Util.selectFrame("opusReportContainer,subReportContainer");	
			Map<String,List<String>> efficiencySummaryResiTableData=effSummaryObj.getActualSummaryTableData();
			
				
			sdoFilterObj.selectLOB("Roll Off");
			sdoFilterObj.GoBtn();
			Util.switchToDefaultWindow();
			Util.selectFrame("opusReportContainer,subReportContainer");	
			Map<String,List<String>> efficiencySummaryROTableData=effSummaryObj.getActualSummaryTableData();
			
			driver.close();
			Util.switchToDefaultTab();
			
			sdoFilterObj.openReportInNewTab("Efficiency Performance");
			EfficiencyPerformanceReport effPerfObj=new EfficiencyPerformanceReport(driver, test);
			sdoFilterObj.selectSite(SiteID);
			sdoFilterObj.selectLOB("Commercial");
			//Enter date range if required
			sdoFilterObj.GoBtn();
			
			Util.switchToDefaultWindow();
			Util.selectFrame("opusReportContainer,subReportContainer");	
			Map<String,List<String>> efficiencyPerformanceCOMSummaryTableData=effPerfObj.getActualSummaryTableData();
			
			
			//SummaryDashboardObj.validateDriverOverviewTableData(actualDriverSummaryTable,efficiencySummaryComTableData);
			//SummaryDashboardObj.validateDriverOverviewTableData(actualDriverSummaryTable,efficiencySummaryResiTableData);
			//SummaryDashboardObj.validateDriverOverviewTableData(actualDriverSummaryTable,efficiencySummaryROTableData);
			
			
			
			
			
			
			//select the Driver & Helper Tab
			
			//Validate Summary column Names of the Driver Tab > Summary Dashboard
			actualSummaryTableColumns = Util.getColumnNames("dtOverviewHlp");
			Util.validateColumns(actualSummaryTableColumns, GlobalExpectedColumns.driverAndHelperTabSummaryDashboardColumns, "Summary Dashboard");
			Util.switchToDefaultWindow();
			Util.selectFrame("opusReportContainer,subReportContainer");	
			Map<String, List<String>> actualDriverAndHelperSummaryTable = SummaryDashboardObj.getActualDriverAndHelperSummaryTableData();
			
			
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
