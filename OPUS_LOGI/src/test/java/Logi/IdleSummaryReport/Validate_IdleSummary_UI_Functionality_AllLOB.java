package Logi.IdleSummaryReport;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Driver.BrowserOpen;
import ObjectRepository.General.FilterSection;
import ObjectRepository.General.HomePage;
import ObjectRepository.General.Login;
import ObjectRepository.Logi.IdleDetailReport;
import ObjectRepository.Logi.IdleSummaryReport;
import Opus.BaseClass;
import SupportLibraries.Excel;
import SupportLibraries.GlobalVariables;
import SupportLibraries.Report;
import SupportLibraries.Util;
import TestData.GlobalExpectedColumns;

public class Validate_IdleSummary_UI_Functionality_AllLOB extends BaseClass{
	
	Login login;
	HomePage sdiMegaObj;
	FilterSection sdoFilterObj;
	IdleSummaryReport idleSummaryObj;
	
	@Test(groups = "Opus", dataProvider = "LOGI_Reports_Data")
	public void Validation_IdleSummaryReport_UI_Functionality_AllLOB(String Scenario,String RunMode,String ReportName, String SiteID, String LOB,
			String FromDate, String ToDate, String Route,String Driver,String ClockInTime,String ClockOutTime,String Ticket) throws Exception {	
		
		
		if(Scenario.equalsIgnoreCase("Validate_IdleSummary_UI_Functionality_AllLOB") & RunMode.equals("Yes"))
		{	
		
		    test = Report.testCreate(extent, test, "Validate_IdleSummary_UI_Functionality_AllLOB");
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
				
				
				//Validate Date Picker functionality 
				Util.validateFromAndToDate(ReportName);
				
				
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
				sdoFilterObj.selectGO();
				idleSummaryObj = new IdleSummaryReport(driver, test);

				//Validate Summary column Names of the idle summayr report
				actualSummaryTableColumns = Util.getColumnNames("dtIdleTimeSummary");
				Util.validateColumns(actualSummaryTableColumns, GlobalExpectedColumns.IdleSummarysummaryColumns, "Idle Summary summary");
				
				//Validate detail table column Names of the idle summary report
				actualDetailTableColumns = Util.getColumnNames("dtIdleTmDetail");
				Util.validateColumns(actualDetailTableColumns, GlobalExpectedColumns.IdleSummaryDetailColumns, "Idle Summary Detail Section");

				//Validate Export Functionality Working Fine
				Util.validateExportToExcelColumns("actExportXLS", GlobalExpectedColumns.excelExportColumnsIdleSummaryReport, downloadFilePath,14); //Parameters ID of export excel button, List of columns and download file path
				
				UIDataList=Util.getUIColumnData(ReportName,"Detail","Grp","Idle Summary");
				Util.validateExportExcelData(downloadFilePath, ReportName, "Tier", 13, UIDataList);
				
				UIDataList=Util.getUIColumnData(ReportName,"Detail","Area","Idle Summary");
				Util.validateExportExcelData(downloadFilePath, ReportName, "Area", 13, UIDataList);
				
				UIDataList=Util.getUIColumnData(ReportName,"Detail","BU","Idle Summary");
				Util.validateExportExcelData(downloadFilePath, ReportName, "BU", 13, UIDataList);
				
				UIDataList=Util.getUIColumnData(ReportName,"Detail","Site","Idle Summary");
				Util.validateExportExcelData(downloadFilePath, ReportName, "Site", 13, UIDataList);
				
				UIDataList=Util.getUIColumnData(ReportName,"Detail","NetIdleTm","Idle Summary");
				Util.validateExportExcelData(downloadFilePath, ReportName, "Net Idle Time (h:m)", 13, UIDataList);
				
				UIDataList=Util.getUIColumnData(ReportName,"Detail","NumIdleEvnts","Idle Summary");
				Util.validateExportExcelData(downloadFilePath, ReportName, "# of Idle Events", 13, UIDataList);
				
				UIDataList=Util.getUIColumnData(ReportName,"Detail","AvgNetIdleTm","Idle Summary");
				Util.validateExportExcelData(downloadFilePath, ReportName, "Avg Net Idle Time (h:m)", 13, UIDataList);
				
				Util.pageScroll(0, 500);
				
				Util.validateSortingFunctionality(ReportName,"Detail","Grp", "Ascending","String","Idle Summary");
				Util.validateSortingFunctionality(ReportName,"Detail","Grp", "Descending","String","Idle Summary");
				
				Util.validateSortingFunctionality(ReportName,"Detail","Area", "Ascending","String","Idle Summary");
				Util.validateSortingFunctionality(ReportName,"Detail","Area", "Descending","String","Idle Summary");
				
				Util.validateSortingFunctionality(ReportName,"Detail","BU", "Ascending","String","Idle Summary");
				Util.validateSortingFunctionality(ReportName,"Detail","BU", "Descending","String","Idle Summary");
				
				Util.validateSortingFunctionality(ReportName,"Detail","Site", "Ascending","String","Idle Summary");
				Util.validateSortingFunctionality(ReportName,"Detail","Site", "Descending","String","Idle Summary");
				
				Util.validateSortingFunctionality(ReportName,"Detail","NetIdleTm", "Ascending","Time","Idle Summary");
				Util.validateSortingFunctionality(ReportName,"Detail","NetIdleTm", "Descending","Time","Idle Summary");
				
				Util.validateSortingFunctionality(ReportName,"Detail","NumIdleEvnts", "Ascending","Double","Idle Summary");
				Util.validateSortingFunctionality(ReportName,"Detail","NumIdleEvnts", "Descending","Double","Idle Summary");
				
				Util.validateSortingFunctionality(ReportName,"Detail","AvgNetIdleTm", "Ascending","String","Idle Summary");
				Util.validateSortingFunctionality(ReportName,"Detail","AvgNetIdleTm", "Descending","String","Idle Summary");
				
				idleSummaryObj.ValidatePopUp(SiteID, FromDate, ToDate, ReportName, downloadFilePath);
				
			
			
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
