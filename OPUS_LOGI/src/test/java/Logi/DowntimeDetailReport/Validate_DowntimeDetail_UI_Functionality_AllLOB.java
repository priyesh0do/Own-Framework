package Logi.DowntimeDetailReport;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Driver.BrowserOpen;
import ObjectRepository.General.FilterSection;
import ObjectRepository.General.HomePage;
import ObjectRepository.General.Login;
import ObjectRepository.Logi.DowntimeDetailReport;
import Opus.BaseClass;
import SupportLibraries.Excel;
import SupportLibraries.GlobalVariables;
import SupportLibraries.Report;
import SupportLibraries.Util;
import TestData.GlobalExpectedColumns;

public class Validate_DowntimeDetail_UI_Functionality_AllLOB extends BaseClass{
	Login login;
	HomePage sdiMegaObj;
	FilterSection sdoFilterObj;
	DowntimeDetailReport DownTimeDetailObj;
	@Test(groups = "Opus", dataProvider = "LOGI_Reports_Data")
	public void Validation_DowntimeDetailReport_SortingAndFilterFunctionality(String Scenario,String RunMode,String ReportName, String SiteID, String LOB,
			String FromDate, String ToDate, String Route,String Driver,String ClockInTime,String ClockOutTime,String Ticket) throws Exception {	
	
		if (!Util.isTestCaseExecutable("Validate_DowntimeDetail_UI_Functionality_AllLOB", GlobalVariables.logiTestCase)) {
			test = Report.testCreate(extent, test, "Validate_DowntimeDetailReport_UI_Functionality_AllLOB");
			Report.SkipTest(test, "Skipping the test as runmode is NO");
		} else {
		
		if(Scenario.equalsIgnoreCase("Validate_DowntimeDetailReport_UI_Functionality_AllLOB ") & RunMode.equals("Yes"))
		{	
			
			test = Report.testCreate(extent, test, "Validate_DowntimeDetail_UI_Functionality_AllLOB");
		    driver = BrowserOpen.StartBrowser(driver, configProp.getProperty("Browser"), configProp.getProperty("URL"));
			login = new Login(driver, test);
			String downloadFilePath=systemDir + "\\downloads";
			List<String> UIDataList=new ArrayList<>();
			List<String> actualSummaryTableColumns=new ArrayList<>();
			List<String> actualPerformacneSubViewTableColumns =new ArrayList<>();
			List<String> actualDetailTableColumns =new ArrayList<>();
			login.LoginSDI(configProp.getProperty("userId"), configProp.getProperty("Password"));
			System.out.println("Login successfully done");
			// Select RM Dashboard
			sdiMegaObj = new HomePage(driver, test);
			sdiMegaObj.ClickOnReport(ReportName);
			sdoFilterObj = new FilterSection(driver, test);
			
               sdoFilterObj.selectSite(SiteID);
			
			// Validate Filter content is sorted in ascending manner
			Util.validateSortingOfFilterValues("islSiteDFilter");
			Util.validateSortingOfFilterValues("islLOBFilter");
			Util.validateSortingOfFilterValues("islSubLOBFilter");
			Util.validateSortingOfFilterValues("islRMFilter");
			Util.validateSortingOfFilterValues("islRteTypeFilter");
			Util.validateSortingOfFilterValues("islRteFilter");
			Util.validateSortingOfFilterValues("islDriverFilter");
			Util.validateSortingOfFilterValues("islRptGrpFilter");
			Util.validateSortingOfFilterValues("islTruckFilter");
			Util.validateSortingOfFilterValues("islCatFilter");
			Util.validateSortingOfFilterValues("islRsnFilter");
			sdoFilterObj.selectFromDate(FromDate);
			sdoFilterObj.selectToDate(ToDate);
			sdoFilterObj.selectGO();
			DownTimeDetailObj=new DowntimeDetailReport(driver, test);
			Util.pageScroll(0, 250);
			//Validate Summary column Names of the DownTime Detail Report
			actualSummaryTableColumns = Util.getColumnNames("dtDowntimeSummary");
			Util.validateColumns(actualSummaryTableColumns, GlobalExpectedColumns.DowntimeDetailSummaryColumns, "Downtime Detail Report Summary");
			
			//Validate Performance subView column Names of the DownTime Detail report
			actualPerformacneSubViewTableColumns = Util.getColumnNames("t2");
			Util.validateColumns(actualPerformacneSubViewTableColumns, GlobalExpectedColumns.DowntimeDetailPerformanceSubViewColumns, "Downtime Detail Performance Sub View");
			
			//Validate Export Functionality Working Fine
			Util.validateExportToExcelColumns("actExportXLS", GlobalExpectedColumns.excelExportColumnsDowntimeDetailReport, downloadFilePath, 23); //Parameters ID of export excel button, List of columns and download file path
			
			UIDataList=Util.getUIColumnData(ReportName,"Detail","Date","All");
			Util.validateExportExcelData(downloadFilePath, ReportName, "Date", 22, UIDataList);
			
			UIDataList=Util.getUIColumnData(ReportName,"Detail","Rte","All");
			Util.validateExportExcelData(downloadFilePath, ReportName, "Route", 22, UIDataList);
			
			UIDataList=Util.getUIColumnData(ReportName,"Detail","Driver","All");
			Util.validateExportExcelData(downloadFilePath, ReportName, "Driver", 22, UIDataList);
			
			UIDataList=Util.getUIColumnData(ReportName,"Detail","DownTm","All");
			Util.validateExportExcelData(downloadFilePath, ReportName, "Downtime (h:m)", 22, UIDataList);
			
			UIDataList=Util.getUIColumnData(ReportName,"Detail","LOB","All");
			Util.validateExportExcelData(downloadFilePath, ReportName, "LOB", 22, UIDataList);
			
			UIDataList=Util.getUIColumnData(ReportName,"Detail","SubLOB","All");
			Util.validateExportExcelData(downloadFilePath, ReportName, "Sub LOB", 22, UIDataList);
			
			UIDataList=Util.getUIColumnData(ReportName,"Detail","RteType","All");
			Util.validateExportExcelData(downloadFilePath, ReportName, "Route Type", 22, UIDataList);
			
			UIDataList=Util.getUIColumnData(ReportName,"Detail","RteManager","All");
			Util.validateExportExcelData(downloadFilePath, ReportName, "Route Manager", 22, UIDataList);
			
////Actual Subview UI data validation is pending Because QA site is not working and unable to download Excel from CPV
			//Click on DownTime
			DownTimeDetailObj.clickDownTimeDrilldown();
			
			
			//Validate Net Idle Drill down column Names of the DownTime Detail report
			actualDetailTableColumns = Util.getColumnNames("t2DowntimeDrillDown");
			Util.validateColumns(actualDetailTableColumns, GlobalExpectedColumns.DowntimeDetailDrilldownColumns, "Downtime Detail Downtime (h:m)Drilldown");
			
			Util.pageScroll(0,250);	
			
			UIDataList=Util.getUIColumnData(ReportName,"Detail","DownTm","All");
			Util.validateExportExcelData(downloadFilePath, ReportName, "Downtime (h:m)", 22, UIDataList);
			
			UIDataList=Util.getUIColumnData(ReportName,"Detail","TruckId","All");
			Util.validateExportExcelData(downloadFilePath, ReportName, "Truck ID", 22, UIDataList);
			
			UIDataList=Util.getUIColumnData(ReportName,"Detail","Category","All");
			Util.validateExportExcelData(downloadFilePath, ReportName, "Category", 22, UIDataList);
			
			UIDataList=Util.getUIColumnData(ReportName,"Detail","Reason","All");
			Util.validateExportExcelData(downloadFilePath, ReportName, "Reason", 22, UIDataList);
			
			UIDataList=Util.getUIColumnData(ReportName,"Detail","SpillLeak","All");
			Util.validateExportExcelData(downloadFilePath, ReportName, "Spill/Leak", 22, UIDataList);
			
			UIDataList=Util.getUIColumnData(ReportName,"Detail","WO","All");
			Util.validateExportExcelData(downloadFilePath, ReportName, "WO#", 22, UIDataList);
			
			
			Util.validateSortingFunctionality(ReportName,"Performance","Date", "Ascending","String","All");
			Util.validateSortingFunctionality(ReportName,"Performance","Date", "Descending","String","All");
			
			Util.validateSortingFunctionality(ReportName,"Performance","Rte", "Ascending","String","All");
			Util.validateSortingFunctionality(ReportName,"Performance","Rte", "Descending","String","All");
			
			Util.validateSortingFunctionality(ReportName,"Performance","Driver", "Ascending","String","All");
			Util.validateSortingFunctionality(ReportName,"Performance","Driver", "Descending","String","All");
			
			Util.validateSortingFunctionality(ReportName,"Performance","DownTm", "Ascending","Time","All");
			Util.validateSortingFunctionality(ReportName,"Performance","DownTm", "Descending","Time","All");
			
			Util.validateSortingFunctionality(ReportName,"Performance","LOB", "Ascending","String","All");
			Util.validateSortingFunctionality(ReportName,"Performance","LOB", "Descending","String","All");
			
			Util.validateSortingFunctionality(ReportName,"Performance","SubLOB", "Ascending","String","All");
			Util.validateSortingFunctionality(ReportName,"Performance","SubLOB", "Descending","String","All");
			
			Util.validateSortingFunctionality(ReportName,"Performance","RteType", "Ascending","String","All");
			Util.validateSortingFunctionality(ReportName,"Performance","RteType", "Descending","String","All");
			
			Util.validateSortingFunctionality(ReportName,"Performance","RteManager", "Ascending","String","All");
			Util.validateSortingFunctionality(ReportName,"Performance","RteManager", "Descending","String","All");
			
			DownTimeDetailObj.RedirectedtoActualSubview(); 
			
			//Validate Summary column Names of the DownTime Detail Report
			actualSummaryTableColumns = Util.getColumnNames("dtDowntimeSummary");
			Util.validateColumns(actualSummaryTableColumns, GlobalExpectedColumns.DowntimeDetailSummaryColumns, "Downtime Detail Report Summary");
			
			//Validate Performance subView column Names of the DownTime Detail report
			actualPerformacneSubViewTableColumns = Util.getColumnNames("t2");
			Util.validateColumns(actualPerformacneSubViewTableColumns, GlobalExpectedColumns.DowntimeDetailPerformanceSubViewColumns, "Downtime Detail Actual Sub View");
			
			//Validate Export Functionality Working Fine
			Util.validateExportToExcelColumns("actExportXLS", GlobalExpectedColumns.excelExportColumnsDowntimeDetailReport, downloadFilePath, 23); //Parameters ID of export excel button, List of columns and download file path
			
			UIDataList=Util.getUIColumnData(ReportName,"Detail","Date","All");
			Util.validateExportExcelData(downloadFilePath, ReportName, "Date", 22, UIDataList);
			
			UIDataList=Util.getUIColumnData(ReportName,"Detail","Rte","All");
			Util.validateExportExcelData(downloadFilePath, ReportName, "Route", 22, UIDataList);
			
			UIDataList=Util.getUIColumnData(ReportName,"Detail","Driver","All");
			Util.validateExportExcelData(downloadFilePath, ReportName, "Driver", 22, UIDataList);
			
			UIDataList=Util.getUIColumnData(ReportName,"Detail","DownTm","All");
			Util.validateExportExcelData(downloadFilePath, ReportName, "Downtime (h:m)", 22, UIDataList);
			
			UIDataList=Util.getUIColumnData(ReportName,"Detail","LOB","All");
			Util.validateExportExcelData(downloadFilePath, ReportName, "LOB", 22, UIDataList);
			
			UIDataList=Util.getUIColumnData(ReportName,"Detail","SubLOB","All");
			Util.validateExportExcelData(downloadFilePath, ReportName, "Sub LOB", 22, UIDataList);
			
			UIDataList=Util.getUIColumnData(ReportName,"Detail","RteType","All");
			Util.validateExportExcelData(downloadFilePath, ReportName, "Route Type", 22, UIDataList);
			
			UIDataList=Util.getUIColumnData(ReportName,"Detail","RteManager","All");
			Util.validateExportExcelData(downloadFilePath, ReportName, "Route Manager", 22, UIDataList);
			
			Util.validateSortingFunctionality(ReportName,"Actual","Date", "Ascending","String","All");
			Util.validateSortingFunctionality(ReportName,"Actual","Date", "Descending","String","All");
			
			Util.validateSortingFunctionality(ReportName,"Actual","Rte", "Ascending","String","All");
			Util.validateSortingFunctionality(ReportName,"Actual","Rte", "Descending","String","All");
			
			Util.validateSortingFunctionality(ReportName,"Actual","Driver", "Ascending","String","All");
			Util.validateSortingFunctionality(ReportName,"Actual","Driver", "Descending","String","All");
			
			Util.validateSortingFunctionality(ReportName,"Actual","DownTm", "Ascending","String","All");
			Util.validateSortingFunctionality(ReportName,"Actual","DownTm", "Descending","String","All");
			
			Util.validateSortingFunctionality(ReportName,"Actual","LOB", "Ascending","String","All");
			Util.validateSortingFunctionality(ReportName,"Actual","LOB", "Descending","String","All");
			
			Util.validateSortingFunctionality(ReportName,"Actual","SubLOB", "Ascending","String","All");
			Util.validateSortingFunctionality(ReportName,"Actual","SubLOB", "Descending","String","All");
			
			Util.validateSortingFunctionality(ReportName,"Actual","RteType", "Ascending","String","All");
			Util.validateSortingFunctionality(ReportName,"Actual","RteType", "Descending","String","All");
			
			Util.validateSortingFunctionality(ReportName,"Actual","RteManager", "Ascending","String","All");
			Util.validateSortingFunctionality(ReportName,"Actual","RteManager", "Descending","String","All");
			
			driver.quit();
			
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
