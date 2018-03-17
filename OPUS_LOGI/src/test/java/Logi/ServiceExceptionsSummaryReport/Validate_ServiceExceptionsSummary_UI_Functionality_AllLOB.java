package Logi.ServiceExceptionsSummaryReport;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Driver.BrowserOpen;
import ObjectRepository.General.FilterSection;
import ObjectRepository.General.GraphSection;
import ObjectRepository.General.HomePage;
import ObjectRepository.General.Login;
import ObjectRepository.Logi.SequenceComplianceSummaryReport;
import ObjectRepository.Logi.ServiceExceptionsSummaryReport;
import Opus.BaseClass;
import SupportLibraries.Excel;
import SupportLibraries.GlobalVariables;
import SupportLibraries.Report;
import SupportLibraries.Util;
import TestData.GlobalExpectedColumns;

public class Validate_ServiceExceptionsSummary_UI_Functionality_AllLOB extends BaseClass{
	
	Login login;
	HomePage sdiMegaObj;
	FilterSection sdoFilterObj;
	ServiceExceptionsSummaryReport serviceExceptionsSummaryObj;
	GraphSection graphObj;
	@Test(groups = "Opus", dataProvider = "LOGI_Reports_Data")
	public void Validation_ServiceExceptionsSummaryReport_UI_Functionality_AllLOB(String Scenario,String RunMode,String ReportName, String SiteID, String LOB,
			String FromDate, String ToDate, String Route,String Driver,String ClockInTime,String ClockOutTime,String Ticket) throws Exception {	
		
		
		if(Scenario.equalsIgnoreCase("Validate_ServiceExceptionsSummary_UI_Functionality_AllLOB") & RunMode.equals("Yes"))
		{	
		
		    test = Report.testCreate(extent, test, "Validate_ServiceExceptionsSummary_UI_Functionality_AllLOB");
		    driver = BrowserOpen.StartBrowser(driver, configProp.getProperty("Browser"), configProp.getProperty("cpvURL"));
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
				
				
				/*//Validate Date Picker functionality 
				Util.validateFromAndToDate(ReportName);
				
				//Validate Graphs
				graphObj.validateGraphSection(ReportName, SiteID);
				
				Util.pageScroll(0, 100);
				
				//Validate Filter is working as expected
				sdoFilterObj.SelectTiers("South", ReportName);
				sdoFilterObj.SelectArea("WM of Texas Oklahoma", ReportName);
				sdoFilterObj.SelectBUs("Waste Management of San Antonio", ReportName);
				sdoFilterObj.SelectSite(SiteID, ReportName);				
				sdoFilterObj.SelectDate(FromDate, ToDate, ReportName);
				
				// Validate Filter content is sorted in ascending manner
				Util.validateSortingOfFilterValues("islGroupFilter");
				Util.validateSortingOfFilterValues("islAreaFilter");
				Util.validateSortingOfFilterValues("islBUFilter");
				Util.validateSortingOfFilterValues("islSiteFilter");
				Util.validateSortingOfFilterValues("islLOBFilterS");
				Util.validateSortingOfFilterValues("islSubLOBFilter");
				
				sdoFilterObj.clickReset();
				sdoFilterObj.selectGO();*/
				serviceExceptionsSummaryObj = new ServiceExceptionsSummaryReport(driver, test);

				//Validate Summary column Names of the Sequence Compliance summayr report
				actualSummaryTableColumns = Util.getColumnNames("dtSvcExceptionsSummary");
				Util.validateColumns(actualSummaryTableColumns, GlobalExpectedColumns.ServiceExceptionsSummarysummaryColumnsCom, "Service Exceptions Summary summary");
				
				//Validate detail table column Names of the Sequence Compliance summary report
				actualDetailTableColumns = Util.getColumnNames("dtSvcExceptionsDetail");
				Util.validateColumns(actualDetailTableColumns, GlobalExpectedColumns.ServiceExceptionsSummaryDetailSubviewColumnsCom, "Service Exceptions Summary Detail Section");

				//Validate Export Functionality Working Fine
				Util.validateExportToExcelColumns("actExportXLS", GlobalExpectedColumns.excelExportColumnsServiceExceptionsSummaryReportCom, downloadFilePath,14); //Parameters ID of export excel button, List of columns and download file path
				
				UIDataList=Util.getUIColumnData(ReportName,"Detail","Grp","Service Exceptions Summary");
				Util.validateExportExcelData(downloadFilePath, ReportName, "Tier", 13, UIDataList);
				
				UIDataList=Util.getUIColumnData(ReportName,"Detail","Area","Service Exceptions Summary");
				Util.validateExportExcelData(downloadFilePath, ReportName, "Area", 13, UIDataList);
				
				UIDataList=Util.getUIColumnData(ReportName,"Detail","BU","Service Exceptions Summary");
				Util.validateExportExcelData(downloadFilePath, ReportName, "BU", 13, UIDataList);
				
				UIDataList=Util.getUIColumnData(ReportName,"Detail","Site","Service Exceptions Summary");
				Util.validateExportExcelData(downloadFilePath, ReportName, "Site", 13, UIDataList);
				
				UIDataList=Util.getUIColumnData(ReportName,"Detail","TtlExp","Service Exceptions Summary");
				Util.validateExportExcelData(downloadFilePath, ReportName, "Total Exceptions", 13, UIDataList);
				
				UIDataList=Util.getUIColumnData(ReportName,"Detail","PctofHOCs","Service Exceptions Summary");
				Util.validateExportExcelData(downloadFilePath, ReportName, "% of HOCs", 13, UIDataList);
				
				UIDataList=Util.getUIColumnData(ReportName,"Detail","TtlHOCs","Service Exceptions Summary");
				Util.validateExportExcelData(downloadFilePath, ReportName, "# of HOCs", 13, UIDataList);
				
				UIDataList=Util.getUIColumnData(ReportName,"Detail","PctofMPUs","Service Exceptions Summary");
				Util.validateExportExcelData(downloadFilePath, ReportName, "% of MPUs", 13, UIDataList);
				
				UIDataList=Util.getUIColumnData(ReportName,"Detail","TtlMPUs","Service Exceptions Summary");
				Util.validateExportExcelData(downloadFilePath, ReportName, "# of MPUs", 13, UIDataList);
				
				UIDataList=Util.getUIColumnData(ReportName,"Detail","PctofETAs","Service Exceptions Summary");
				Util.validateExportExcelData(downloadFilePath, ReportName, "% of ETAs", 13, UIDataList);
				
				UIDataList=Util.getUIColumnData(ReportName,"Detail","TtlETAs","Service Exceptions Summary");
				Util.validateExportExcelData(downloadFilePath, ReportName, "# of ETAs ", 13, UIDataList);
				
				
				Util.pageScroll(0, 250);
				
				Util.validateSortingFunctionality(ReportName,"Detail","Grp", "Ascending","String","Service Exceptions Summary");
				Util.validateSortingFunctionality(ReportName,"Detail","Grp", "Descending","String","Service Exceptions Summary");
				
				Util.validateSortingFunctionality(ReportName,"Detail","Area", "Ascending","String","Service Exceptions Summary");
				Util.validateSortingFunctionality(ReportName,"Detail","Area", "Descending","String","Service Exceptions Summary");
				
				Util.validateSortingFunctionality(ReportName,"Detail","BU", "Ascending","String","Service Exceptions Summary");
				Util.validateSortingFunctionality(ReportName,"Detail","BU", "Descending","String","Service Exceptions Summary");
				
				Util.validateSortingFunctionality(ReportName,"Detail","Site", "Ascending","String","Service Exceptions Summary");
				Util.validateSortingFunctionality(ReportName,"Detail","Site", "Descending","String","Service Exceptions Summary");
				
				Util.validateSortingFunctionality(ReportName,"Detail","TtlExp", "Ascending","Double","Service Exceptions Summary");
				Util.validateSortingFunctionality(ReportName,"Detail","TtlExp", "Descending","Double","Service Exceptions Summary");
				
				Util.validateSortingFunctionality(ReportName,"Detail","PctofHOCs", "Ascending","Double","Service Exceptions Summary");
				Util.validateSortingFunctionality(ReportName,"Detail","PctofHOCs", "Descending","Double","Service Exceptions Summary");
				
				Util.validateSortingFunctionality(ReportName,"Detail","TtlHOCs", "Ascending","Double","Service Exceptions Summary");
				Util.validateSortingFunctionality(ReportName,"Detail","TtlHOCs", "Descending","Double","Service Exceptions Summary");
				
				Util.validateSortingFunctionality(ReportName,"Detail","PctofMPUs", "Ascending","Double","Service Exceptions Summary");
				Util.validateSortingFunctionality(ReportName,"Detail","PctofMPUs", "Descending","Double","Service Exceptions Summary");
				
				Util.validateSortingFunctionality(ReportName,"Detail","TtlMPUs", "Ascending","Double","Service Exceptions Summary");
				Util.validateSortingFunctionality(ReportName,"Detail","TtlMPUs", "Descending","Double","Service Exceptions Summary");
				
				Util.validateSortingFunctionality(ReportName,"Detail","PctofETAs", "Ascending","Double","Service Exceptions Summary");
				Util.validateSortingFunctionality(ReportName,"Detail","PctofETAs", "Descending","Double","Service Exceptions Summary");
				
				Util.validateSortingFunctionality(ReportName,"Detail","TtlETAs", "Ascending","Double","Service Exceptions Summary");
				Util.validateSortingFunctionality(ReportName,"Detail","TtlETAs", "Descending","Double","Service Exceptions Summary");
						
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
