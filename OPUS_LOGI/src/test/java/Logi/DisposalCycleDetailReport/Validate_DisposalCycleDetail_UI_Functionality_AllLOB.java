package Logi.DisposalCycleDetailReport;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Driver.BrowserOpen;
import ObjectRepository.General.FilterSection;
import ObjectRepository.General.GraphSection;
import ObjectRepository.General.HomePage;
import ObjectRepository.General.Login;
import ObjectRepository.Logi.DisposalCycleDetailReport;
import Opus.BaseClass;
import SupportLibraries.Excel;
import SupportLibraries.GlobalVariables;
import SupportLibraries.Report;
import SupportLibraries.Util;
import TestData.GlobalExpectedColumns;

public class Validate_DisposalCycleDetail_UI_Functionality_AllLOB extends BaseClass{
	
	Login login;
	HomePage sdiMegaObj;
	FilterSection filterObj;
	DisposalCycleDetailReport disposalCycleDetailObj;
	GraphSection graphObj;
	@Test(groups = "Opus", dataProvider = "LOGI_Reports_Data")
	public void Validation_DisposalCycleDetailReport_UI_Functionality_AllLOB(String Scenario,String RunMode,String ReportName, String siteID, String lOB,
			String fromDate, String toDate, String route,String driverName,String ClockInTime,String ClockOutTime,String Ticket) throws Exception {	
		
		if (!Util.isTestCaseExecutable("Validate_DisposalCycleDetail_UI_Functionality_AllLOB", GlobalVariables.logiTestCase)) {
			test = Report.testCreate(extent, test, "Validate_DisposalCycleDetail_UI_Functionality_AllLOB");
			Report.SkipTest(test, "Skipping the test as runmode is NO");
		} else {
		
		if(Scenario.equalsIgnoreCase("Validate_DisposalCycleDetail_UI_Functionality_AllLOB") & RunMode.equals("Yes"))
		{	
		
		    test = Report.testCreate(extent, test, "Validate_DisposalCycleDetail_UI_Functionality_AllLOB");
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
				filterObj = new FilterSection(driver, test);
				
				//Validate Graph Section
				graphObj.validateGraphSection(ReportName, siteID);
				
				//Validate Date Picker functionality 
				Util.validateFromAndToDate(ReportName);
				
				
				Util.pageScroll(0, 250);
				
				//Validate Filter is working as expected
				filterObj.SelectSite(siteID, ReportName);
				filterObj.SelectLOB("Commercial", ReportName);				
				filterObj.SelectSubLobs("Commercial Frontload", ReportName);
				filterObj.SelectRouteManagers("Brandt, Robert",ReportName);
				filterObj.SelectRouteType("FC - Front Load Commercial", ReportName);
				filterObj.SelectDrivers(driverName, ReportName);
				filterObj.SelectDate(fromDate, toDate, ReportName);
				
				// Validate Filter content is sorted in ascending manner
				Util.validateSortingOfFilterValues("islSiteDFilter");
				Util.validateSortingOfFilterValues("islLOBFilterS");
				Util.validateSortingOfFilterValues("islSubLOBFilter");
				Util.validateSortingOfFilterValues("islRMFilter");
				Util.validateSortingOfFilterValues("islRteTypeFilter");
				Util.validateSortingOfFilterValues("islRteFilter");
				Util.validateSortingOfFilterValues("islDriverFilter");
				Util.validateSortingOfFilterValues("islRptGrpFilter");
	
				filterObj.selectFilterCriteria(siteID, "", "", lOB, "", "", fromDate, toDate);
				
				
				
				disposalCycleDetailObj = new DisposalCycleDetailReport(driver, test);

				//Validate Summary column Names of the disposalCycle detail report
				actualSummaryTableColumns = Util.getColumnNames("dtDisposalTimeSummary");
				Util.validateColumns(actualSummaryTableColumns, GlobalExpectedColumns.DisposalCycleDetailSummaryColumns, "Disposal Cycle Detail Summary");
				
				disposalCycleDetailObj.clickOnAllTab();
				
				Util.pageScroll(0,500);
				
				//Validate detail table column Names of the Disposal Cycle Exception Tab Performance SubView report
				actualDetailTableColumns = Util.getColumnNames("t2");
				Util.validateColumns(actualDetailTableColumns, GlobalExpectedColumns.DisposalCyclePerformanceSubviewColumns, "Disposal Cycle Detail All Subview");

				//Validate Export Functionality Working Fine
				Util.validateExportToExcelColumns("actExportXLS", GlobalExpectedColumns.excelExportColumnsDisposalCycleDetailReport, downloadFilePath,21); //Parameters ID of export excel button, List of columns and download file path
				
				UIDataList=Util.getUIColumnData(ReportName,"Detail","Date","All");
				Util.validateExportExcelData(downloadFilePath, ReportName, "Date", 20, UIDataList);
				
				UIDataList=Util.getUIColumnData(ReportName,"Detail","Rte","All");
				Util.validateExportExcelData(downloadFilePath, ReportName, "Route", 20, UIDataList);
				
				UIDataList=Util.getUIColumnData(ReportName,"Detail","Driver","All");
				Util.validateExportExcelData(downloadFilePath, ReportName, "Driver", 20, UIDataList);
				
				
				UIDataList=Util.getUIColumnData(ReportName,"Detail","TonsLoad","All");
				Util.validateExportExcelData(downloadFilePath, ReportName, "Tons/Load", 20, UIDataList);
				
				UIDataList=Util.getUIColumnData(ReportName,"Detail","Variance","All");
				Util.validateExportExcelData(downloadFilePath, ReportName, "Load Variance", 20, UIDataList);
				
				UIDataList=Util.getUIColumnData(ReportName,"Detail","LOB","All");
				Util.validateExportExcelData(downloadFilePath, ReportName, "LOB", 20, UIDataList);
				
				UIDataList=Util.getUIColumnData(ReportName,"Detail","SubLOB","All");
				Util.validateExportExcelData(downloadFilePath, ReportName, "Sub LOB", 20, UIDataList);
				
				UIDataList=Util.getUIColumnData(ReportName,"Detail","RteType","All");
				Util.validateExportExcelData(downloadFilePath, ReportName, "Route Type", 20, UIDataList);
				
				UIDataList=Util.getUIColumnData(ReportName,"Detail","RteManager","All");
				Util.validateExportExcelData(downloadFilePath, ReportName, "Route Manager", 20, UIDataList);
				
				Util.pageScroll(0, 400);
				
				//click on performance drilldown
				disposalCycleDetailObj.clickDisposalTimeDrilldownAllTab();
				
				actualDetailTableColumns = Util.getColumnNames("disposalDrilldown");
				Util.validateColumns(actualDetailTableColumns, GlobalExpectedColumns.DisposalCyclePerformanceSubviewDrilldownColumns, "Disposal Cycle Detail Performance Drilldown");

				UIDataList=Util.getUIColumnData(ReportName,"Detail","ExcpTm","DisposalCycleDrilldown");
				Util.validateExportExcelData(downloadFilePath, ReportName, "Exception Time (h:m)", 20, UIDataList);
				
				UIDataList=Util.getUIColumnData(ReportName,"Detail","DspTm","DisposalCycleDrilldown");
				Util.validateExportExcelData(downloadFilePath, ReportName, "Disp Cycle Time(h:m)", 20, UIDataList);
				
				UIDataList=Util.getUIColumnData(ReportName,"Detail","DspSite","DisposalCycleDrilldown");
				Util.validateExportExcelData(downloadFilePath, ReportName, "Disp Site", 20, UIDataList);
				
				UIDataList=Util.getUIColumnData(ReportName,"Detail","TmOfDay","DisposalCycleDrilldown");
				Util.validateExportExcelData(downloadFilePath, ReportName, "Time Of Day", 20, UIDataList);
				
				//click on actual SubView
				disposalCycleDetailObj.clickOnActualSubView();
				
				actualDetailTableColumns = Util.getColumnNames("t2");
				Util.validateColumns(actualDetailTableColumns, GlobalExpectedColumns.DisposalCycleActualSubviewColumns, "Disposal Cycle Detail Actual SubView");
				
				
				
				
				//Click on Disposal Time Drilldown
				disposalCycleDetailObj.clickDisposalTimeDrilldownAllTab();
				
				actualDetailTableColumns = Util.getColumnNames("disposalDrilldown");
				Util.validateColumns(actualDetailTableColumns, GlobalExpectedColumns.DisposalCycleActualSubviewDrilldownColumns, "Disposal Cycle Detail Actual Drilldown");
				
				UIDataList=Util.getUIColumnData(ReportName,"Detail","Arrive","DisposalCycleDrilldown");
				Util.validateExportExcelData(downloadFilePath, ReportName, "Arrive", 20, UIDataList);
				
				UIDataList=Util.getUIColumnData(ReportName,"Detail","Depart","DisposalCycleDrilldown");
				Util.validateExportExcelData(downloadFilePath, ReportName, "Depart", 20, UIDataList);
				
				UIDataList=Util.getUIColumnData(ReportName,"Detail","DownTm","DisposalCycleDrilldown");
				Util.validateExportExcelData(downloadFilePath, ReportName, "Down Time (h:m)", 20, UIDataList);
				
				UIDataList=Util.getUIColumnData(ReportName,"Detail","Meal","DisposalCycleDrilldown");
				Util.validateExportExcelData(downloadFilePath, ReportName, "Meal (h:m)", 20, UIDataList);
				
				UIDataList=Util.getUIColumnData(ReportName,"Detail","Tons","DisposalCycleDrilldown");
				Util.validateExportExcelData(downloadFilePath, ReportName, "Tons", 20, UIDataList);

				Util.validateSortingFunctionality(ReportName,"Detail","Date", "Ascending","String","All");
				Util.validateSortingFunctionality(ReportName,"Detail","Date", "Descending","String","All");
				
				Util.validateSortingFunctionality(ReportName,"Detail","Rte", "Ascending","String","All");
				Util.validateSortingFunctionality(ReportName,"Detail","Rte", "Descending","String","All");
				
				Util.validateSortingFunctionality(ReportName,"Detail","Driver", "Ascending","String","All");
				Util.validateSortingFunctionality(ReportName,"Detail","Driver", "Descending","String","All");
				
				Util.validateSortingFunctionality(ReportName,"Detail","ExcpTm", "Ascending","Time","All");
				Util.validateSortingFunctionality(ReportName,"Detail","ExcpTm", "Descending","Time","All");
				
				Util.validateSortingFunctionality(ReportName,"Detail","DspTm", "Ascending","Time","All");
				Util.validateSortingFunctionality(ReportName,"Detail","DspTm", "Descending","Time","All");
				
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
