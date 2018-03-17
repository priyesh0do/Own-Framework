package Logi.CustomerPropertyDetailReport;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Driver.BrowserOpen;
import ObjectRepository.General.FilterSection;
import ObjectRepository.General.GraphSection;
import ObjectRepository.General.HomePage;
import ObjectRepository.General.Login;
import ObjectRepository.Logi.CustomerPropertyDetailReport;
import Opus.BaseClass;
import SupportLibraries.Excel;
import SupportLibraries.GlobalVariables;
import SupportLibraries.Report;
import SupportLibraries.Util;
import TestData.GlobalExpectedColumns;

public class Validate_CustomerPropertyDetail_UI_Functionality_COM extends BaseClass{

	Login login;
	HomePage sdiMegaObj;
	FilterSection sdoFilterObj;
	GraphSection graphObj;
	CustomerPropertyDetailReport custPropDetailObj;
	@Test(groups = "Opus", dataProvider = "LOGI_Reports_Data")
	public void Validation_CustomerPropertyDetail_UI_Functionality_COM(String Scenario,String RunMode,String ReportName, String SiteID, String LOB,
			String FromDate, String ToDate, String Route,String Driver,String ClockInTime,String ClockOutTime,String Ticket) throws Exception {	
	
		if (!Util.isTestCaseExecutable("Validate_CustomerPropertyDetail_UI_Functionality_COM", GlobalVariables.logiTestCase)) {
			test = Report.testCreate(extent, test, "Validate_CustomerPropertyDetail_UI_Functionality_COM");
			Report.SkipTest(test, "Skipping the test as runmode is NO");
		}
		else {
			if(Scenario.equalsIgnoreCase("Validate_CustomerPropertyDetail_UI_Functionality_COM") & RunMode.equals("Yes"))
			{
				test = Report.testCreate(extent, test, "Validate_CustomerPropertyDetail_UI_Functionality_COM");
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
					
					sdoFilterObj = new FilterSection(driver, test);
					graphObj = new GraphSection(driver, test);
					//Validate Date Picker functionality 
					Util.validateFromAndToDate(ReportName);
					
					//Validate Graph Functionality
					graphObj.validateGraphSection(ReportName, SiteID);
					
					
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
					/*Util.validateSortingOfFilterValues("islSiteDFilter");
					Util.validateSortingOfFilterValues("islLOBFilterS");
					Util.validateSortingOfFilterValues("islSubLOBFilter");
					Util.validateSortingOfFilterValues("islRMFilter");
					Util.validateSortingOfFilterValues("islRteTypeFilter");
					Util.validateSortingOfFilterValues("islRteFilter");
					Util.validateSortingOfFilterValues("islDriverFilter");
					Util.validateSortingOfFilterValues("islRptGrpFilter");*/
					

					sdoFilterObj.selectSite(SiteID);
					sdoFilterObj.selectLOB(LOB);				
					sdoFilterObj.selectFromDate(FromDate);
					sdoFilterObj.selectToDate(ToDate);
					sdoFilterObj.selectGO();
					
					custPropDetailObj=new CustomerPropertyDetailReport(driver, test);
					custPropDetailObj.ClickOnAllTab();
					
					//Validate Summary Column Names
					actualSummaryTableColumns = Util.getColumnNames("dtCustTimeSummary");
					Util.validateColumns(actualSummaryTableColumns, GlobalExpectedColumns.CustomerPropertyDetailDetailSummaryColumns, "Customer Property Detail Summary");
					
					//Validate detail table column Names 
					actualDetailTableColumns = Util.getColumnNames("t2");
					Util.validateColumns(actualDetailTableColumns, GlobalExpectedColumns.CustomerPropertyAllSubviewColumns, "Customer Property Detail");
					
					//Validate Export Functionality Working Fine
					Util.validateExportToExcelColumns("actExportXLS", GlobalExpectedColumns.excelExportColumnsCustomerPropertyDetailReport, downloadFilePath,17); //Parameters ID of export excel button, List of columns and download file path
					
					UIDataList=Util.getUIColumnData(ReportName,"Detail","Date","All");
					Util.validateExportExcelData(downloadFilePath, ReportName, "Date",16, UIDataList);
				
					UIDataList=Util.getUIColumnData(ReportName,"Detail","Rte","All");
					Util.validateExportExcelData(downloadFilePath, ReportName, "Route",16, UIDataList);
					
					UIDataList=Util.getUIColumnData(ReportName,"Detail","Driver","All");
					Util.validateExportExcelData(downloadFilePath, ReportName, "Driver",16, UIDataList);
					
					UIDataList=Util.getUIColumnData(ReportName,"Detail","ExcpTm","All");
					Util.validateExportExcelData(downloadFilePath, ReportName, "Exception Time (h:m)",16, UIDataList);
					
					UIDataList=Util.getUIColumnData(ReportName,"Detail","CustPropTm","All");
					Util.validateExportExcelData(downloadFilePath, ReportName, "Customer Property Time (h:m)",16, UIDataList);
					
					UIDataList=Util.getUIColumnData(ReportName,"Detail","NumLifts","All");
					Util.validateExportExcelData(downloadFilePath, ReportName, "# of Lifts",16, UIDataList);
					
					UIDataList=Util.getUIColumnData(ReportName,"Detail","TmLift","All");
					Util.validateExportExcelData(downloadFilePath, ReportName, "Time/ Lift (h:m)",16, UIDataList);
					
					UIDataList=Util.getUIColumnData(ReportName,"Detail","TtlUnits","All");
					Util.validateExportExcelData(downloadFilePath, ReportName, "Total Units",16, UIDataList);
					
					UIDataList=Util.getUIColumnData(ReportName,"Detail","LOB","All");
					Util.validateExportExcelData(downloadFilePath, ReportName, "LOB",16, UIDataList);
					
					UIDataList=Util.getUIColumnData(ReportName,"Detail","SubLOB","All");
					Util.validateExportExcelData(downloadFilePath, ReportName, "Sub LOB",16, UIDataList);
					
					UIDataList=Util.getUIColumnData(ReportName,"Detail","RteType","All");
					Util.validateExportExcelData(downloadFilePath, ReportName, "Route Type",16, UIDataList);
					
					UIDataList=Util.getUIColumnData(ReportName,"Detail","RteManager","All");
					Util.validateExportExcelData(downloadFilePath, ReportName, "Route Manager",16, UIDataList);
					
					custPropDetailObj.ClickonCustomerPropertyDrillDown();
					
					//Validate Total Stops Drill down column Names of the Sequence Compliance Detail report
					actualDetailTableColumns = Util.getColumnNames("CustTime_Sub");
					Util.validateColumns(actualDetailTableColumns, GlobalExpectedColumns.CustomerPropertyDrilldownColumns, "Customer Property Detail Customer Property Drilldown");
					
					Util.pageScroll(0,250);
					
					UIDataList=Util.getUIColumnData(ReportName,"Detail","Excp","CustomerPropertyDrillDown");
					Util.validateExportExcelData(downloadFilePath, ReportName, "Exception Time (h:m)",16, UIDataList);
					
					UIDataList=Util.getUIColumnData(ReportName,"Detail","PropTm","CustomerPropertyDrillDown");
					Util.validateExportExcelData(downloadFilePath, ReportName, "Customer Property Time (h:m)",16, UIDataList);
					
					UIDataList=Util.getUIColumnData(ReportName,"Detail","LiftCnt","CustomerPropertyDrillDown");
					Util.validateExportExcelData(downloadFilePath, ReportName, "# of Lifts",16, UIDataList);
					
					UIDataList=Util.getUIColumnData(ReportName,"Detail","TmLift","CustomerPropertyDrillDown");
					Util.validateExportExcelData(downloadFilePath, ReportName, "Time/ Lift (h:m)",16, UIDataList);
					
					UIDataList=Util.getUIColumnData(ReportName,"Detail","TtlUnits","CustomerPropertyDrillDown");
					Util.validateExportExcelData(downloadFilePath, ReportName, "Total Units",16, UIDataList);
					
					
					UIDataList=Util.getUIColumnData(ReportName,"Detail","CustID","CustomerPropertyDrillDown");
					Util.validateExportExcelData(downloadFilePath, ReportName, "Cust ID",16, UIDataList);
					
					
					UIDataList=Util.getUIColumnData(ReportName,"Detail","CustNm","CustomerPropertyDrillDown");
					Util.validateExportExcelData(downloadFilePath, ReportName, "Cust Name",16, UIDataList);
					
					UIDataList=Util.getUIColumnData(ReportName,"Detail","Arrive","CustomerPropertyDrillDown");
					Util.validateExportExcelData(downloadFilePath, ReportName, "Arrive Time",16, UIDataList);
					
					UIDataList=Util.getUIColumnData(ReportName,"Detail","Depart","CustomerPropertyDrillDown");
					Util.validateExportExcelData(downloadFilePath, ReportName, "Depart Time",16, UIDataList);
					
					UIDataList=Util.getUIColumnData(ReportName,"Detail","Dwntm","CustomerPropertyDrillDown");
					Util.validateExportExcelData(downloadFilePath, ReportName, "Downtime (h:m)",16, UIDataList);
					
					UIDataList=Util.getUIColumnData(ReportName,"Detail","Meal","CustomerPropertyDrillDown");
					Util.validateExportExcelData(downloadFilePath, ReportName, "Meal (h:m)",16, UIDataList);
					
					/////////////////////Filter Sorting//////////////////////
					Util.validateSortingFunctionality(ReportName,"Detail","Date", "Ascending","String","All");
					Util.validateSortingFunctionality(ReportName,"Detail","Date", "Descending","String","All");
					
					
					Util.validateSortingFunctionality(ReportName,"Detail","Rte", "Ascending","String","All");
					Util.validateSortingFunctionality(ReportName,"Detail","Rte", "Descending","String","All");
					
					Util.validateSortingFunctionality(ReportName,"Detail","Driver", "Ascending","String","All");
					Util.validateSortingFunctionality(ReportName,"Detail","Driver", "Descending","String","All");
					
					Util.validateSortingFunctionality(ReportName,"Detail","ExcpTm", "Ascending","Time","All");
					Util.validateSortingFunctionality(ReportName,"Detail","ExcpTm", "Descending","Time","All");
					
					Util.validateSortingFunctionality(ReportName,"Detail","CustPropTm", "Ascending","Time","All");
					Util.validateSortingFunctionality(ReportName,"Detail","CustPropTm", "Descending","Time","All");
					
					Util.validateSortingFunctionality(ReportName,"Detail","NumLifts", "Ascending","String","All");
					Util.validateSortingFunctionality(ReportName,"Detail","NumLifts", "Descending","String","All");
					
					Util.validateSortingFunctionality(ReportName,"Detail","TmLift", "Ascending","Time","All");
					Util.validateSortingFunctionality(ReportName,"Detail","TmLift", "Descending","Time","All");
					
					Util.validateSortingFunctionality(ReportName,"Detail","TtlUnits", "Ascending","String","All");
					Util.validateSortingFunctionality(ReportName,"Detail","TtlUnits", "Descending","String","All");
					
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
