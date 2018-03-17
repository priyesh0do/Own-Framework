package Logi.SequenceComplianceDetailReport;

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
import ObjectRepository.Logi.SequenceComplianceDetailReport;
import Opus.BaseClass;
import SupportLibraries.Excel;
import SupportLibraries.GlobalVariables;
import SupportLibraries.Report;
import SupportLibraries.Util;
import TestData.GlobalExpectedColumns;

public class Validate_SequenceComplianceDetail_UI_Functionality_AllLOB extends BaseClass{
	
	Login login;
	HomePage sdiMegaObj;
	FilterSection sdoFilterObj;
	GraphSection graphObj;
	SequenceComplianceDetailReport seqCompDetailObj;
	
	@Test(groups = "Opus", dataProvider = "LOGI_Reports_Data")
	public void Validation_SequenceComplianceSummaryReport_UI_Functionality_AllLOB(String Scenario,String RunMode,String ReportName, String SiteID, String LOB,
			String FromDate, String ToDate, String Route,String Driver,String ClockInTime,String ClockOutTime,String Ticket) throws Exception {	
		

		if(Scenario.equalsIgnoreCase("Validate_SequenceComplianceDetail_UI_Functionality_AllLOB") & RunMode.equals("Yes"))
		{	
		
		    test = Report.testCreate(extent, test, "Validate_SequenceComplianceDetail_UI_Functionality_AllLOB");
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
				Util.validateSortingOfFilterValues("islSiteDFilter");
				Util.validateSortingOfFilterValues("islLOBFilterS");
				Util.validateSortingOfFilterValues("islSubLOBFilter");
				Util.validateSortingOfFilterValues("islRMFilter");
				Util.validateSortingOfFilterValues("islRteTypeFilter");
				Util.validateSortingOfFilterValues("islRteFilter");
				Util.validateSortingOfFilterValues("islDriverFilter");
				Util.validateSortingOfFilterValues("islRptGrpFilter");
				
				sdoFilterObj.selectSite(SiteID);
				sdoFilterObj.selectLOB(LOB);				
				sdoFilterObj.selectFromDate(FromDate);
				sdoFilterObj.selectToDate(ToDate);
				sdoFilterObj.selectGO();
				seqCompDetailObj = new SequenceComplianceDetailReport(driver, test);
				
				

				//Switch to All Tab
				seqCompDetailObj.clickOnAllTab();
				
				//Validate Summary column Names of the Sequence Compliance detail report
				actualSummaryTableColumns = Util.getColumnNames("dtSeqComplianceSummary");
				Util.validateColumns(actualSummaryTableColumns, GlobalExpectedColumns.SequenceComplianceDetailSummaryColumns, "Sequence Compliance Detail Summary");
				
				//Validate detail table column Names of the Sequence Compliance Detail Exception Tab
				actualDetailTableColumns = Util.getColumnNames("t2");
				Util.validateColumns(actualDetailTableColumns, GlobalExpectedColumns.SequenceComplianceAllSubviewColumns, "Sequence Compliance Detail");
				
				
				//Validate Export Functionality Working Fine
				Util.validateExportToExcelColumns("actExportXLS", GlobalExpectedColumns.excelExportColumnsSequenceComplianceDetailReport, downloadFilePath,16); //Parameters ID of export excel button, List of columns and download file path
				
				
				UIDataList=Util.getUIColumnData(ReportName,"Detail","Date","All");
				Util.validateExportExcelData(downloadFilePath, ReportName, "Date", 15, UIDataList);
				
				UIDataList=Util.getUIColumnData(ReportName,"Detail","Rte","All");
				Util.validateExportExcelData(downloadFilePath, ReportName, "Route", 15, UIDataList);
				
				UIDataList=Util.getUIColumnData(ReportName,"Detail","Driver","All");
				Util.validateExportExcelData(downloadFilePath, ReportName, "Driver", 15, UIDataList);
				
				UIDataList=Util.getUIColumnData(ReportName,"Detail","PctOutofSeq","All");
				Util.validateExportExcelData(downloadFilePath, ReportName, "Out of Seq %", 15, UIDataList);
				
				UIDataList=Util.getUIColumnData(ReportName,"Detail","NumStpOutofSeq","All");
				Util.validateExportExcelData(downloadFilePath, ReportName, "# of Stops Out of Seq", 15, UIDataList);
				
				UIDataList=Util.getUIColumnData(ReportName,"Detail","PctInSeq","All");
				Util.validateExportExcelData(downloadFilePath, ReportName, "In Seq %", 15, UIDataList);
				
				UIDataList=Util.getUIColumnData(ReportName,"Detail","NumStpInSeq","All");
				Util.validateExportExcelData(downloadFilePath, ReportName, "# of Stops In Seq", 15, UIDataList);
				
				UIDataList=Util.getUIColumnData(ReportName,"Detail","MASStpsWOSeqNum","All");
				Util.validateExportExcelData(downloadFilePath, ReportName, "MAS Stops w/o Seq #", 15, UIDataList);
				
				UIDataList=Util.getUIColumnData(ReportName,"Detail","LOB","All");
				Util.validateExportExcelData(downloadFilePath, ReportName, "LOB", 15, UIDataList);
				
				UIDataList=Util.getUIColumnData(ReportName,"Detail","SubLOB","All");
				Util.validateExportExcelData(downloadFilePath, ReportName, "Sub LOB", 15, UIDataList);
				
				UIDataList=Util.getUIColumnData(ReportName,"Detail","RteType","All");
				Util.validateExportExcelData(downloadFilePath, ReportName, "Route Type", 15, UIDataList);
				
				UIDataList=Util.getUIColumnData(ReportName,"Detail","RteManager","All");
				Util.validateExportExcelData(downloadFilePath, ReportName, "Route Manager ", 15, UIDataList);
				
				//Click on Net Idle time
				seqCompDetailObj.clickTotalStopsDrilldown();
				
				
				//Validate Total Stops Drill down column Names of the Sequence Compliance Detail report
				actualDetailTableColumns = Util.getColumnNames("t3");
				Util.validateColumns(actualDetailTableColumns, GlobalExpectedColumns.SequenceComplianceDrilldownColumns, "Sequence Compliance Detail Total Stops Drilldown");
				
				Util.pageScroll(0,250);
				
				UIDataList=Util.getUIColumnData(ReportName,"Detail","InSeqInd","TotalStopsDrillDown");
				Util.validateExportExcelData(downloadFilePath, ReportName, "In Seq Indicator", 15, UIDataList);
				
				UIDataList=Util.getUIColumnData(ReportName,"Detail","ActSeqNum","TotalStopsDrillDown");
				Util.validateExportExcelData(downloadFilePath, ReportName, "Actual Seq #", 15, UIDataList);
				
				UIDataList=Util.getUIColumnData(ReportName,"Detail","PlnSeqNum","TotalStopsDrillDown");
				Util.validateExportExcelData(downloadFilePath, ReportName, "Plan Seq #", 15, UIDataList);
				
				UIDataList=Util.getUIColumnData(ReportName,"Detail","CustID","TotalStopsDrillDown");
				Util.validateExportExcelData(downloadFilePath, ReportName, "Cust ID", 15, UIDataList);
				
				UIDataList=Util.getUIColumnData(ReportName,"Detail","CustNM","TotalStopsDrillDown");
				Util.validateExportExcelData(downloadFilePath, ReportName, "Cust Name", 15, UIDataList);
				
				UIDataList=Util.getUIColumnData(ReportName,"Detail","MASSeqNum","TotalStopsDrillDown");
				Util.validateExportExcelData(downloadFilePath, ReportName, "MAS Seq #", 15, UIDataList);
				
				Util.validateSortingFunctionality(ReportName,"Detail","Date", "Ascending","String","All");
				Util.validateSortingFunctionality(ReportName,"Detail","Date", "Descending","String","All");
				
				Util.validateSortingFunctionality(ReportName,"Detail","Rte", "Ascending","String","All");
				Util.validateSortingFunctionality(ReportName,"Detail","Rte", "Descending","String","All");
				
				Util.validateSortingFunctionality(ReportName,"Detail","Driver", "Ascending","String","All");
				Util.validateSortingFunctionality(ReportName,"Detail","Driver", "Descending","String","All");
				
				Util.validateSortingFunctionality(ReportName,"Detail","PctOutofSeq", "Ascending","Double","All");
				Util.validateSortingFunctionality(ReportName,"Detail","PctOutofSeq", "Descending","Double","All");
				
				Util.validateSortingFunctionality(ReportName,"Detail","NumStpOutofSeq", "Ascending","Double","All");
				Util.validateSortingFunctionality(ReportName,"Detail","NumStpOutofSeq", "Descending","Double","All");
				
				Util.validateSortingFunctionality(ReportName,"Detail","PctInSeq", "Ascending","Double","All");
				Util.validateSortingFunctionality(ReportName,"Detail","PctInSeq", "Descending","Double","All");
				
				Util.validateSortingFunctionality(ReportName,"Detail","NumStpInSeq", "Ascending","Double","All");
				Util.validateSortingFunctionality(ReportName,"Detail","NumStpInSeq", "Descending","Double","All");
				
				Util.validateSortingFunctionality(ReportName,"Detail","TotStp", "Ascending","Double","All");
				Util.validateSortingFunctionality(ReportName,"Detail","TotStp", "Descending","Double","All");
				
				Util.validateSortingFunctionality(ReportName,"Detail","MASStpsWOSeqNum", "Ascending","Double","All");
				Util.validateSortingFunctionality(ReportName,"Detail","MASStpsWOSeqNum", "Descending","Double","All");
				
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
	@DataProvider(name = "LOGI_Reports_Data")
	public Object[][] testData() throws Exception {
		Excel ex = new Excel();
		Object[][] arrayObject = ex.getTableArray(configProp.getProperty("LOGITestCaseExcel"),
				configProp.getProperty("LOGITestDataSheet"));
		return arrayObject;
	}

}
