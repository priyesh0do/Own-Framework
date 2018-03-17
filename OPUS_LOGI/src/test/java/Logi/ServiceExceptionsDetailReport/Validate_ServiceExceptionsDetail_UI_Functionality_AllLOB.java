package Logi.ServiceExceptionsDetailReport;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Driver.BrowserOpen;
import ObjectRepository.General.FilterSection;
import ObjectRepository.General.GraphSection;
import ObjectRepository.General.HomePage;
import ObjectRepository.General.Login;
import ObjectRepository.Logi.IdleDetailReport;
import ObjectRepository.Logi.ServiceExceptionsDetailReport;
import Opus.BaseClass;
import SupportLibraries.Excel;
import SupportLibraries.GlobalVariables;
import SupportLibraries.Report;
import SupportLibraries.Util;
import TestData.GlobalExpectedColumns;

public class Validate_ServiceExceptionsDetail_UI_Functionality_AllLOB extends BaseClass{

	Login login;
	HomePage sdiMegaObj;
	FilterSection sdoFilterObj;
	GraphSection graphObj;
	ServiceExceptionsDetailReport serviceExceptionsDetailObj;
	
	@Test(groups = "Opus", dataProvider = "LOGI_Reports_Data")
	public void Validation_ServiceExceptionsReport_UI_Functionality_AllLOB(String Scenario,String RunMode,String ReportName, String SiteID, String LOB,
			String FromDate, String ToDate, String Route,String Driver,String ClockInTime,String ClockOutTime,String Ticket) throws Exception {	
		
		
		
		if(Scenario.equalsIgnoreCase("Validate_ServiceExceptionsDetail_UI_Functionality_AllLOB") & RunMode.equals("Yes"))
		{	
		
		    test = Report.testCreate(extent, test, "Validate_ServiceExceptionsDetail_UI_Functionality_AllLOB");
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
				
				/*//Validate Graph for Service Exception Detail Report
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
				Util.validateSortingOfFilterValues("islDriverFilter");*/
				
				sdoFilterObj.selectSite(SiteID);
				sdoFilterObj.selectLOB(LOB);				
				sdoFilterObj.selectFromDate(FromDate);
				sdoFilterObj.selectToDate(ToDate);
				sdoFilterObj.selectGO();
				serviceExceptionsDetailObj = new ServiceExceptionsDetailReport(driver, test);

				Util.wait_inMinutes(0.5);
				
				//Validate Summary column Names of the Service Exception report
				actualSummaryTableColumns = Util.getColumnNames("dtSvcExceptionsSummary");
				Util.validateColumns(actualSummaryTableColumns, GlobalExpectedColumns.ServiceExceptionsDetailSummaryColumnsCom, "Service Exceptions Detail Summary");
				
				//Validate detail table column Names of the Service Exception Detail report
				actualDetailTableColumns = Util.getColumnNames("t2e");
				Util.validateColumns(actualDetailTableColumns, GlobalExpectedColumns.ServiceExceptionsDetailAllSubviewColumns, "Service Exceptions Detail All Subview");

				//Validate Export Functionality Working Fine
				Util.validateExportToExcelColumns("actExportXLS", GlobalExpectedColumns.excelExportColumnsServiceExceptionsDetailReport, downloadFilePath,16); //Parameters ID of export excel button, List of columns and download file path
				
				UIDataList=Util.getUIColumnData(ReportName,"Detail","Date","Exceptions");
				Util.validateExportExcelData(downloadFilePath, ReportName, "Date", 15, UIDataList);
				
				UIDataList=Util.getUIColumnData(ReportName,"Detail","Rte","Exceptions");
				Util.validateExportExcelData(downloadFilePath, ReportName, "Route", 15, UIDataList);
				
				UIDataList=Util.getUIColumnData(ReportName,"Detail","Driver","Exceptions");
				Util.validateExportExcelData(downloadFilePath, ReportName, "Driver", 15, UIDataList);
				
				UIDataList=Util.getUIColumnData(ReportName,"Detail","LOB","Exceptions");
				Util.validateExportExcelData(downloadFilePath, ReportName, "LOB", 15, UIDataList);
				
				UIDataList=Util.getUIColumnData(ReportName,"Detail","SubLOB","Exceptions");
				Util.validateExportExcelData(downloadFilePath, ReportName, "Sub LOB", 15, UIDataList);
				
				UIDataList=Util.getUIColumnData(ReportName,"Detail","RteType","Exceptions");
				Util.validateExportExcelData(downloadFilePath, ReportName, "Route Type", 15, UIDataList);
				
				UIDataList=Util.getUIColumnData(ReportName,"Detail","RteManager","Exceptions");
				Util.validateExportExcelData(downloadFilePath, ReportName, "Route Manager", 15, UIDataList);
				
				//Click on Total Exceptions
				serviceExceptionsDetailObj.clickTotalExceptionsDrilldown();
				
				
				//Validate Total Exceptions Drill down column Names of the Sequence Compliance Detail report
				actualDetailTableColumns = Util.getColumnNames("t3");
				Util.validateColumns(actualDetailTableColumns, GlobalExpectedColumns.ServiceExceptionsDetailDrilldownColumns, "Service Exceptions Detail Total Exceptions Drilldown");
				
				Util.pageScroll(0,250);
				
				UIDataList=Util.getUIColumnData(ReportName,"Detail","TicketType","TotalExceptionsDrillDown");
				Util.validateExportExcelData(downloadFilePath, ReportName, "Ticket Type", 15, UIDataList);
				
				UIDataList=Util.getUIColumnData(ReportName,"Detail","TicketTime","TotalExceptionsDrillDown");
				Util.validateExportExcelData(downloadFilePath, ReportName, "Ticket Time", 15, UIDataList);
				
				UIDataList=Util.getUIColumnData(ReportName,"Detail","TicketComm","TotalExceptionsDrillDown");
				Util.validateExportExcelData(downloadFilePath, ReportName, "Ticket Comment", 15, UIDataList);
				
				UIDataList=Util.getUIColumnData(ReportName,"Detail","CustID","TotalExceptionsDrillDown");
				Util.validateExportExcelData(downloadFilePath, ReportName, "Cust ID", 15, UIDataList);
				
				UIDataList=Util.getUIColumnData(ReportName,"Detail","CustNM","TotalExceptionsDrillDown");
				Util.validateExportExcelData(downloadFilePath, ReportName, "Cust Name", 15, UIDataList);
				
				UIDataList=Util.getUIColumnData(ReportName,"Detail","SvcDesc","TotalExceptionsDrillDown");
				Util.validateExportExcelData(downloadFilePath, ReportName, "Service Description", 15, UIDataList);
				
				UIDataList=Util.getUIColumnData(ReportName,"Detail","SftyIss","TotalExceptionsDrillDown");
				Util.validateExportExcelData(downloadFilePath, ReportName, "Safety Issue", 15, UIDataList);
				
				Util.validateSortingFunctionality(ReportName,"Detail","Date", "Ascending","String","Exceptions");
				Util.validateSortingFunctionality(ReportName,"Detail","Date", "Descending","String","Exceptions");
				
				Util.validateSortingFunctionality(ReportName,"Detail","Rte", "Ascending","String","Exceptions");
				Util.validateSortingFunctionality(ReportName,"Detail","Rte", "Descending","String","Exceptions");
				
				Util.validateSortingFunctionality(ReportName,"Detail","Driver", "Ascending","String","Exceptions");
				Util.validateSortingFunctionality(ReportName,"Detail","Driver", "Descending","String","Exceptions");
				
				Util.validateSortingFunctionality(ReportName,"Detail","TtlExcpts", "Ascending","Double","Exceptions");
				Util.validateSortingFunctionality(ReportName,"Detail","TtlExcpts", "Descending","Double","Exceptions");
				
				
				Util.validateSortingFunctionality(ReportName,"Detail","LOB", "Ascending","String","Exceptions");
				Util.validateSortingFunctionality(ReportName,"Detail","LOB", "Descending","String","Exceptions");
				
				Util.validateSortingFunctionality(ReportName,"Detail","SubLOB", "Ascending","String","Exceptions");
				Util.validateSortingFunctionality(ReportName,"Detail","SubLOB", "Descending","String","Exceptions");
				
				Util.validateSortingFunctionality(ReportName,"Detail","RteType", "Ascending","String","Exceptions");
				Util.validateSortingFunctionality(ReportName,"Detail","RteType", "Descending","String","Exceptions");
				
				Util.validateSortingFunctionality(ReportName,"Detail","RteManager", "Ascending","String","Exceptions");
				Util.validateSortingFunctionality(ReportName,"Detail","RteManager", "Descending","String","Exceptions");
				
			
			
			
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
