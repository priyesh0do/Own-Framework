package Logi.PostRouteSummaryReport;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Driver.BrowserOpen;
import ObjectRepository.General.FilterSection;
import ObjectRepository.General.HomePage;
import ObjectRepository.General.Login;
import ObjectRepository.Logi.PostRouteSummary;
import Opus.BaseClass;
import SupportLibraries.Excel;
import SupportLibraries.GlobalVariables;
import SupportLibraries.Report;
import SupportLibraries.Util;
import TestData.GlobalExpectedColumns;

public class Validate_PostRouteSummary_UI_Functionality_AllLOB extends BaseClass{

	Login login;
	HomePage sdiMegaObj;
	FilterSection sdoFilterObj;
	PostRouteSummary PostRouteSummaryObj;
	@Test(groups = "Opus", dataProvider = "LOGI_Reports_Data")
	public void Validation_POstRouteSummaryReport_SortingAndFilterFunctionality(String Scenario,String RunMode,String ReportName, String SiteID, String LOB,
			String FromDate, String ToDate, String Route,String Driver,String ClockInTime,String ClockOutTime,String Ticket) throws InterruptedException, IOException {
		if (!Util.isTestCaseExecutable("Validate_PostRouteSummary_UI_Functionality_AllLOB", GlobalVariables.logiTestCase)) {
			test = Report.testCreate(extent, test, "Validate_PostRouteSummary_UI_Functionality_AllLOB");
			Report.SkipTest(test, "Skipping the test as runmode is NO");
		} 
		else {
			if(Scenario.equalsIgnoreCase("Validate_PostRouteSummary_UI_Functionality_AllLOB") & RunMode.equals("Yes")) {
				test = Report.testCreate(extent, test, "Validate_PostRouteSummay_UI_Functionality_AllLOB");
			    driver = BrowserOpen.StartBrowser(driver, configProp.getProperty("Browser"), configProp.getProperty("URL"));
				login = new Login(driver, test);
				String downloadFilePath=systemDir + "\\downloads";
				List<String> UIDataList=new ArrayList<>();
				List<String> actualSummaryTableColumns=new ArrayList<>();
				List<String> actualDetailTableColumns = new ArrayList<>();
				login.LoginSDI(configProp.getProperty("userId"), configProp.getProperty("Password"));
				sdiMegaObj = new HomePage(driver, test);
				sdiMegaObj.ClickOnReport(ReportName);
				sdoFilterObj = new FilterSection(driver, test);
				sdoFilterObj.selectSite(SiteID);
				Util.validateSortingOfFilterValues("islGroupFilter");
				Util.validateSortingOfFilterValues("islAreaFilter");
				Util.validateSortingOfFilterValues("islBUFilter");
				Util.validateSortingOfFilterValues("islSiteFilter");
				Util.validateSortingOfFilterValues("islLOBFilter");
				Util.validateSortingOfFilterValues("islSubLOBFilter");
				sdoFilterObj.selectFromDate(FromDate);
				sdoFilterObj.selectToDate(ToDate);
				sdoFilterObj.selectGO();
				PostRouteSummaryObj=new PostRouteSummary(driver, test);
				
				actualSummaryTableColumns = Util.getColumnNames("dtPostRouteSummary");
				Util.validateColumns(actualSummaryTableColumns, GlobalExpectedColumns.PostRouteSummarysummaryColumns, "Post Route Summary");
				
				actualDetailTableColumns = Util.getColumnNames("dtPostRouteDetail");
				Util.validateColumns(actualDetailTableColumns, GlobalExpectedColumns.PostRouteSummaryDetailColumns, "PostRouteSummary Detail View");
				
				//Validate Export Functionality Working Fine
				Util.validateExportToExcelColumns("actExportXLS", GlobalExpectedColumns.PostRouteSummaryDetailColumns, downloadFilePath,14);
				
				UIDataList=Util.getUIColumnData(ReportName,"Detail","Grp","PostRouteSummary");
				Util.validateExportExcelData(downloadFilePath, ReportName, "Tier", 13, UIDataList);
				
				UIDataList=Util.getUIColumnData(ReportName,"Detail","Area","PostRouteSummary");
				Util.validateExportExcelData(downloadFilePath, ReportName, "Area", 13, UIDataList);
				
				UIDataList=Util.getUIColumnData(ReportName,"Detail","BU","PostRouteSummary");
				Util.validateExportExcelData(downloadFilePath, ReportName, "BU", 13, UIDataList);
				
				UIDataList=Util.getUIColumnData(ReportName,"Detail","Site","PostRouteSummary");
				Util.validateExportExcelData(downloadFilePath, ReportName, "Site", 13, UIDataList);
				
				UIDataList=Util.getUIColumnData(ReportName,"Detail","ExcpTime","PostRouteSummary");
				Util.validateExportExcelData(downloadFilePath, ReportName, "Exception Time (h:m)", 13, UIDataList);
				
				UIDataList=Util.getUIColumnData(ReportName,"Detail","NumPostRteExcp","PostRouteSummary");
				Util.validateExportExcelData(downloadFilePath, ReportName, "# of Post-Routes w/ Exception", 13, UIDataList);
				
				UIDataList=Util.getUIColumnData(ReportName,"Detail","NumPostRte","PostRouteSummary");
				Util.validateExportExcelData(downloadFilePath, ReportName, "# of Post-Routes", 13, UIDataList);
				
				UIDataList=Util.getUIColumnData(ReportName,"Detail","ExcpTimePct","PostRouteSummary");
				Util.validateExportExcelData(downloadFilePath, ReportName, "Exception Time %", 13, UIDataList);
				
				UIDataList=Util.getUIColumnData(ReportName,"Detail","VarFromPlanPct","PostRouteSummary");
				Util.validateExportExcelData(downloadFilePath, ReportName, "Variance from Plan %", 13, UIDataList);
				
				UIDataList=Util.getUIColumnData(ReportName,"Detail","AvgActTm","PostRouteSummary");
				Util.validateExportExcelData(downloadFilePath, ReportName, "Avg Actual (h:m)", 13, UIDataList);
				
				UIDataList=Util.getUIColumnData(ReportName,"Detail","AvgPlanTm","PostRouteSummary");
				Util.validateExportExcelData(downloadFilePath, ReportName, "Avg Plan (h:m) ", 13, UIDataList);
				
				
				
				Util.validateSortingFunctionality(ReportName,"","Grp", "Ascending","String","PostRouteSummary");
				Util.validateSortingFunctionality(ReportName,"","Grp", "Descending","String","PostRouteSummary");
				
				Util.validateSortingFunctionality(ReportName,"","Area", "Ascending","String","PostRouteSummary");
				Util.validateSortingFunctionality(ReportName,"","Area", "Descending","String","PostRouteSummary");
				
				Util.validateSortingFunctionality(ReportName,"","BU", "Ascending","String","PostRouteSummary");
				Util.validateSortingFunctionality(ReportName,"","BU", "Descending","String","PostRouteSummary");
				
				Util.validateSortingFunctionality(ReportName,"","Site", "Ascending","String","PostRouteSummary");
				Util.validateSortingFunctionality(ReportName,"","Site", "Descending","String","PostRouteSummary");
				
				Util.validateSortingFunctionality(ReportName,"","ExcpTime", "Ascending","Time","PostRouteSummary");
				Util.validateSortingFunctionality(ReportName,"","ExcpTime", "Descending","Time","PostRouteSummary");
				
				Util.validateSortingFunctionality(ReportName,"","NumPostRteExcp", "Ascending","String","PostRouteSummary");
				Util.validateSortingFunctionality(ReportName,"","NumPostRteExcp", "Descending","String","PostRouteSummary");
				
				Util.validateSortingFunctionality(ReportName,"","NumPostRte", "Ascending","String","PostRouteSummary");
				Util.validateSortingFunctionality(ReportName,"","NumPostRte", "Descending","String","PostRouteSummary");
				
				Util.validateSortingFunctionality(ReportName,"","ExcpTimePct", "Ascending","String","PostRouteSummary");
				Util.validateSortingFunctionality(ReportName,"","ExcpTimePct", "Descending","String","PostRouteSummary");
				
				Util.validateSortingFunctionality(ReportName,"","VarFromPlanPct", "Ascending","Double","PostRouteSummary");
				Util.validateSortingFunctionality(ReportName,"","VarFromPlanPct", "Descending","Double","PostRouteSummary");
				
				Util.validateSortingFunctionality(ReportName,"","AvgActTm", "Ascending","Time","PostRouteSummary");
				Util.validateSortingFunctionality(ReportName,"","AvgActTm", "Descending","Time","PostRouteSummary");
				
				Util.validateSortingFunctionality(ReportName,"","AvgPlanTm", "Ascending","Time","PostRouteSummary");
				Util.validateSortingFunctionality(ReportName,"","AvgPlanTm", "Descending","Time","PostRouteSummary");
				
				PostRouteSummaryObj.ValidatePopUp(SiteID, FromDate, ToDate, ReportName, downloadFilePath);
				
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
