package Logi.IdleDetailReport;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Driver.BrowserOpen;
import ObjectRepository.General.FilterSection;
import ObjectRepository.General.GraphSection;
import ObjectRepository.General.HomePage;
import ObjectRepository.General.Login;
import ObjectRepository.Logi.EfficiencySummaryReport;
import ObjectRepository.Logi.IdleDetailReport;
import Opus.BaseClass;
import SupportLibraries.Excel;
import SupportLibraries.GlobalVariables;
import SupportLibraries.Report;
import SupportLibraries.Util;
import TestData.GlobalExpectedColumns;

public class Validate_IdleDetail_UI_Functionality_AllLOB extends BaseClass{
	
	Login login;
	HomePage sdiMegaObj;
	FilterSection sdoFilterObj;
	IdleDetailReport idleDetailObj;
	GraphSection graphObj;
	@Test(groups = "Opus", dataProvider = "LOGI_Reports_Data")
	public void Validation_IdleDetailReport_UI_Functionality_AllLOB(String Scenario,String RunMode,String ReportName, String SiteID, String LOB,
			String FromDate, String ToDate, String Route,String Driver,String ClockInTime,String ClockOutTime,String Ticket) throws Exception {	
		
		if (!Util.isTestCaseExecutable("Validate_IdleDetail_UI_Functionality_AllLOB", GlobalVariables.logiTestCase)) {
			test = Report.testCreate(extent, test, "Validate_EfficiencySummary_UI_Functionality_AllLOB");
			Report.SkipTest(test, "Skipping the test as runmode is NO");
		} else {
		
		if(Scenario.equalsIgnoreCase("Validate_IdleDetail_UI_Functionality_AllLOB") & RunMode.equals("Yes"))
		{	
		
		    test = Report.testCreate(extent, test, "Validate_IdleDetail_UI_Functionality_AllLOB");
		    driver = BrowserOpen.StartBrowser(driver, configProp.getProperty("Browser"), configProp.getProperty("URL"));
			login = new Login(driver, test);
			login.LoginSDI(configProp.getProperty("userId"), configProp.getProperty("Password"));
			System.out.println("Login successfully done");
				String downloadFilePath=systemDir + "\\downloads";
				List<String> UIDataList=new ArrayList<>();
				List<String> actualSummaryTableColumns=new ArrayList<>();
				List<String> actualDetailTableColumns =new ArrayList<>();
				
				sdiMegaObj = new HomePage(driver, test);
				sdiMegaObj.ClickOnReport(ReportName);
				graphObj=new GraphSection(driver, test);
				sdoFilterObj = new FilterSection(driver, test);
				
				//Validate Graph Section
				graphObj.validateGraphSection(ReportName, SiteID);
				
				//Validate Date Picker functionality 
				Util.validateFromAndToDate(ReportName);
				
				
				Util.pageScroll(0, 250);
				
				//Validate Filter is working as expected
				sdoFilterObj.SelectSite(SiteID, ReportName);
				sdoFilterObj.SelectLOB("Commercial", ReportName);				
				sdoFilterObj.SelectSubLobs("Commercial Frontload", ReportName);
				sdoFilterObj.SelectRouteManagers("Brandt, Robert",ReportName);
				sdoFilterObj.SelectRouteType("FC - Front Load Commercial", ReportName);
				sdoFilterObj.SelectDrivers(Driver, ReportName);
				sdoFilterObj.SelectDate(FromDate, ToDate, ReportName);
				
				// Validate Filter content is sorted in ascending manner
				Util.validateSortingOfFilterValues("islSiteDFilter");
				Util.validateSortingOfFilterValues("islLOBFilterS");
				Util.validateSortingOfFilterValues("islSubLOBFilter");
				Util.validateSortingOfFilterValues("islRMFilter");
				Util.validateSortingOfFilterValues("islRteTypeFilter");
				Util.validateSortingOfFilterValues("islRteFilter");
				Util.validateSortingOfFilterValues("islDriverFilter");
				Util.validateSortingOfFilterValues("islRptGrpFilter");
				
				sdoFilterObj.selectSite(SiteID);
				sdoFilterObj.selectMultiLOB(LOB);				
				sdoFilterObj.selectFromDate(FromDate);
				sdoFilterObj.selectToDate(ToDate);
				sdoFilterObj.selectGO();
				idleDetailObj = new IdleDetailReport(driver, test);

				//Validate Summary column Names of the idle detail report
				actualSummaryTableColumns = Util.getColumnNames("dtIdleTimeSummary");
				Util.validateColumns(actualSummaryTableColumns, GlobalExpectedColumns.IdleDetailSummaryColumns, "Idle Detail Summary");
				
				//Validate detail table column Names of the efficiency summary report
				actualDetailTableColumns = Util.getColumnNames("t2");
				Util.validateColumns(actualDetailTableColumns, GlobalExpectedColumns.IdleDetailAllSubviewColumns, "Idle Detail All Subview");

				//Validate Export Functionality Working Fine
				Util.validateExportToExcelColumns("actExportXLS", GlobalExpectedColumns.excelExportColumnsIdleDetailReport, downloadFilePath,17); //Parameters ID of export excel button, List of columns and download file path
				
				UIDataList=Util.getUIColumnData(ReportName,"Detail","Date","All");
				Util.validateExportExcelData(downloadFilePath, ReportName, "Date", 16, UIDataList);
				
				UIDataList=Util.getUIColumnData(ReportName,"Detail","Rte","All");
				Util.validateExportExcelData(downloadFilePath, ReportName, "Route", 16, UIDataList);
				
				UIDataList=Util.getUIColumnData(ReportName,"Detail","Driver","All");
				Util.validateExportExcelData(downloadFilePath, ReportName, "Driver", 16, UIDataList);
				
				UIDataList=Util.getUIColumnData(ReportName,"Detail","LOB","All");
				Util.validateExportExcelData(downloadFilePath, ReportName, "LOB", 16, UIDataList);
				
				UIDataList=Util.getUIColumnData(ReportName,"Detail","SubLOB","All");
				Util.validateExportExcelData(downloadFilePath, ReportName, "Sub LOB", 16, UIDataList);
				
				UIDataList=Util.getUIColumnData(ReportName,"Detail","RteType","All");
				Util.validateExportExcelData(downloadFilePath, ReportName, "Route Type", 16, UIDataList);
				
				UIDataList=Util.getUIColumnData(ReportName,"Detail","RteManager","All");
				Util.validateExportExcelData(downloadFilePath, ReportName, "Route Manager", 16, UIDataList);
				
				//Click on Net Idle time
				idleDetailObj.clickNetIdleDrilldown();
				
				
				//Validate Net Idle Drill down column Names of the Idle Detail report
				actualDetailTableColumns = Util.getColumnNames("t2Idle");
				Util.validateColumns(actualDetailTableColumns, GlobalExpectedColumns.IdleDetailDrilldownColumns, "Idle Detail Net Idle Drilldown");
				
				Util.pageScroll(0,250);
				
				UIDataList=Util.getUIColumnData(ReportName,"Detail","NetIdleTm","NetIdleDrillDown");
				Util.validateExportExcelData(downloadFilePath, ReportName, "Net Idle Time", 16, UIDataList);
				
				UIDataList=Util.getUIColumnData(ReportName,"Detail","TmOfDay","NetIdleDrillDown");
				Util.validateExportExcelData(downloadFilePath, ReportName, "Time Of Day", 16, UIDataList);
				
				UIDataList=Util.getUIColumnData(ReportName,"Detail","IdleSt","NetIdleDrillDown");
				Util.validateExportExcelData(downloadFilePath, ReportName, "Start Idle", 16, UIDataList);
				
				UIDataList=Util.getUIColumnData(ReportName,"Detail","EndIdle","NetIdleDrillDown");
				Util.validateExportExcelData(downloadFilePath, ReportName, "End Idle", 16, UIDataList);
				
				UIDataList=Util.getUIColumnData(ReportName,"Detail","DownTm","NetIdleDrillDown");
				Util.validateExportExcelData(downloadFilePath, ReportName, "DownTime", 16, UIDataList);
				
				UIDataList=Util.getUIColumnData(ReportName,"Detail","MealTm","NetIdleDrillDown");
				Util.validateExportExcelData(downloadFilePath, ReportName, "MealTime", 16, UIDataList);
				
				Util.validateSortingFunctionality(ReportName,"Detail","Date", "Ascending","String","All");
				Util.validateSortingFunctionality(ReportName,"Detail","Date", "Descending","String","All");
				
				Util.validateSortingFunctionality(ReportName,"Detail","Rte", "Ascending","String","All");
				Util.validateSortingFunctionality(ReportName,"Detail","Rte", "Descending","String","All");
				
				Util.validateSortingFunctionality(ReportName,"Detail","Driver", "Ascending","String","All");
				Util.validateSortingFunctionality(ReportName,"Detail","Driver", "Descending","String","All");
				
				Util.validateSortingFunctionality(ReportName,"Detail","NetIdleTm", "Ascending","Time","All");
				Util.validateSortingFunctionality(ReportName,"Detail","NetIdleTm", "Descending","Time","All");
				
				Util.validateSortingFunctionality(ReportName,"Detail","IdleOcc", "Ascending","Double","All");
				Util.validateSortingFunctionality(ReportName,"Detail","IdleOcc", "Descending","Double","All");
				
				Util.validateSortingFunctionality(ReportName,"Detail","LOB", "Ascending","String","All");
				Util.validateSortingFunctionality(ReportName,"Detail","LOB", "Descending","String","All");
				
				Util.validateSortingFunctionality(ReportName,"Detail","SubLOB", "Ascending","String","All");
				Util.validateSortingFunctionality(ReportName,"Detail","SubLOB", "Descending","String","All");
				
				Util.validateSortingFunctionality(ReportName,"Detail","RteType", "Ascending","String","All");
				Util.validateSortingFunctionality(ReportName,"Detail","RteType", "Descending","String","All");
				
				Util.validateSortingFunctionality(ReportName,"Detail","RteManager", "Ascending","String","All");
				Util.validateSortingFunctionality(ReportName,"Detail","RteManager", "Descending","String","All");
				
			
			
			
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
