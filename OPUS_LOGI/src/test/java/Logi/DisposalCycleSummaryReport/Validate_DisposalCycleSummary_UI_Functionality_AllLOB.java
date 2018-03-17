package Logi.DisposalCycleSummaryReport;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Driver.BrowserOpen;
import ObjectRepository.General.FilterSection;
import ObjectRepository.General.HomePage;
import ObjectRepository.General.Login;
import ObjectRepository.Logi.DisposalCycleSummaryReport;
import ObjectRepository.Logi.IdleSummaryReport;
import Opus.BaseClass;
import SupportLibraries.Excel;
import SupportLibraries.GlobalVariables;
import SupportLibraries.Report;
import SupportLibraries.Util;
import TestData.GlobalExpectedColumns;

public class Validate_DisposalCycleSummary_UI_Functionality_AllLOB extends BaseClass {
	
	Login login;
	HomePage sdiMegaObj;
	FilterSection sdoFilterObj;
	DisposalCycleSummaryReport disposalCycleSummaryObj;
	
	@Test(groups = "Opus", dataProvider = "LOGI_Reports_Data")
	public void Validation_DisposalCycleSummaryReport_UI_Functionality_AllLOB(String Scenario,String RunMode,String ReportName, String SiteID, String LOB,
			String FromDate, String ToDate, String Route,String Driver,String ClockInTime,String ClockOutTime,String Ticket) throws Exception {	
		
		if (!Util.isTestCaseExecutable("Validate_DisposalCycleSummary_UI_Functionality_AllLOB", GlobalVariables.logiTestCase)) {
			test = Report.testCreate(extent, test, "Validate_DisposalCycleSummary_UI_Functionality_AllLOB");
			Report.SkipTest(test, "Skipping the test as runmode is NO");
		} else {
		
		if(Scenario.equalsIgnoreCase("Validate_DisposalCycleSummary_UI_Functionality_AllLOB") & RunMode.equals("Yes"))
		{	
		
		    test = Report.testCreate(extent, test, "Validate_DisposalCycleSummary_UI_Functionality_AllLOB");
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
				disposalCycleSummaryObj = new DisposalCycleSummaryReport(driver, test);

				//Validate Summary column Names of the idle summayr report
				actualSummaryTableColumns = Util.getColumnNames("dtDisposalTimeSummary");
				Util.validateColumns(actualSummaryTableColumns, GlobalExpectedColumns.DisposalCycleSummarysummaryColumns, "DisposalCycle Summary summary");
				
				//Validate detail table column Names of the idle summary report
				actualDetailTableColumns = Util.getColumnNames("dtDisposalTimeDetail");
				Util.validateColumns(actualDetailTableColumns, GlobalExpectedColumns.DisposalCycleSummaryDetailColumns, "DisposalCycle Summary Detail Section");

				//Validate Export Functionality Working Fine
				Util.validateExportToExcelColumns("actExportXLS", GlobalExpectedColumns.excelExportColumnsDisposalCycleSummaryReport, downloadFilePath,14); //Parameters ID of export excel button, List of columns and download file path
				
				Util.pageScroll(0, 500);
				
				UIDataList=Util.getUIColumnData(ReportName,"Detail","Grp","Disposal Cycle Summary");
				Util.validateExportExcelData(downloadFilePath, ReportName, "Tier", 13, UIDataList);
				
				UIDataList=Util.getUIColumnData(ReportName,"Detail","Area","Disposal Cycle Summary");
				Util.validateExportExcelData(downloadFilePath, ReportName, "Area", 13, UIDataList);
				
				UIDataList=Util.getUIColumnData(ReportName,"Detail","BU","Disposal Cycle Summary");
				Util.validateExportExcelData(downloadFilePath, ReportName, "BU", 13, UIDataList);
				
				UIDataList=Util.getUIColumnData(ReportName,"Detail","Site","Disposal Cycle Summary");
				Util.validateExportExcelData(downloadFilePath, ReportName, "Site", 13, UIDataList);
				
				UIDataList=Util.getUIColumnData(ReportName,"Detail","ExcpTime","Disposal Cycle Summary");
				Util.validateExportExcelData(downloadFilePath, ReportName, "Exception Time (h:m)", 13, UIDataList);
				
				UIDataList=Util.getUIColumnData(ReportName,"Detail","NumDspLoadsWExcp","Disposal Cycle Summary");
				Util.validateExportExcelData(downloadFilePath, ReportName, "# of Disposal Loads w/ Exception", 13, UIDataList);
				
				UIDataList=Util.getUIColumnData(ReportName,"Detail","NumDspLoads","Disposal Cycle Summary");
				Util.validateExportExcelData(downloadFilePath, ReportName, "# of Disposal Loads", 13, UIDataList);
				
				UIDataList=Util.getUIColumnData(ReportName,"Detail","ExcpTimePct","Disposal Cycle Summary");
				Util.validateExportExcelData(downloadFilePath, ReportName, "Exception Time %", 13, UIDataList);
				
				UIDataList=Util.getUIColumnData(ReportName,"Detail","VarFromThrsholdPct","Disposal Cycle Summary");
				Util.validateExportExcelData(downloadFilePath, ReportName, "Variance from Threshold %", 13, UIDataList);
				
				UIDataList=Util.getUIColumnData(ReportName,"Detail","AvgActTm","Disposal Cycle Summary");
				Util.validateExportExcelData(downloadFilePath, ReportName, "Avg Actual (h:m)", 13, UIDataList);
				
				UIDataList=Util.getUIColumnData(ReportName,"Detail","AvgThreshold","Disposal Cycle Summary");
				Util.validateExportExcelData(downloadFilePath, ReportName, "Avg Threshold (h:m)", 13, UIDataList);
				
				Util.pageScroll(0, 500);
				
				Util.validateSortingFunctionality(ReportName,"Detail","Grp", "Ascending","String","Disposal Cycle Summary");
				Util.validateSortingFunctionality(ReportName,"Detail","Grp", "Descending","String","Disposal Cycle Summary");
				
				Util.validateSortingFunctionality(ReportName,"Detail","Area", "Ascending","String","Disposal Cycle Summary");
				Util.validateSortingFunctionality(ReportName,"Detail","Area", "Descending","String","Disposal Cycle Summary");
				
				Util.validateSortingFunctionality(ReportName,"Detail","BU", "Ascending","String","Disposal Cycle Summary");
				Util.validateSortingFunctionality(ReportName,"Detail","BU", "Descending","String","Disposal Cycle Summary");
				
				Util.validateSortingFunctionality(ReportName,"Detail","Site", "Ascending","String","Disposal Cycle Summary");
				Util.validateSortingFunctionality(ReportName,"Detail","Site", "Descending","String","Disposal Cycle Summary");
				
				Util.validateSortingFunctionality(ReportName,"Detail","ExcpTime", "Ascending","Time","Disposal Cycle Summary");
				Util.validateSortingFunctionality(ReportName,"Detail","ExcpTime", "Descending","Time","Disposal Cycle Summary");
				
				Util.validateSortingFunctionality(ReportName,"Detail","NumDspLoadsWExcp", "Ascending","Double","Disposal Cycle Summary");
				Util.validateSortingFunctionality(ReportName,"Detail","NumDspLoadsWExcp", "Descending","Double","Disposal Cycle Summary");
				
				Util.validateSortingFunctionality(ReportName,"Detail","NumDspLoads", "Ascending","Double","Disposal Cycle Summary");
				Util.validateSortingFunctionality(ReportName,"Detail","NumDspLoads", "Descending","Double","Disposal Cycle Summary");
				
				Util.validateSortingFunctionality(ReportName,"Detail","ExcpTimePct", "Ascending","Double","Disposal Cycle Summary");
				Util.validateSortingFunctionality(ReportName,"Detail","ExcpTimePct", "Descending","Double","Disposal Cycle Summary");
				
				Util.validateSortingFunctionality(ReportName,"Detail","VarFromThrsholdPct", "Ascending","Double","Disposal Cycle Summary");
				Util.validateSortingFunctionality(ReportName,"Detail","VarFromThrsholdPct", "Descending","Double","Disposal Cycle Summary");
				
				Util.validateSortingFunctionality(ReportName,"Detail","AvgActTm", "Ascending","Time","Disposal Cycle Summary");
				Util.validateSortingFunctionality(ReportName,"Detail","AvgActTm", "Descending","Time","Disposal Cycle Summary");
				
				Util.validateSortingFunctionality(ReportName,"Detail","AvgThreshold", "Ascending","Time","Disposal Cycle Summary");
				Util.validateSortingFunctionality(ReportName,"Detail","AvgThreshold", "Descending","Time","Disposal Cycle Summary");
				
				disposalCycleSummaryObj.ValidatePopUp(SiteID, FromDate, ToDate, ReportName, downloadFilePath);
			
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
