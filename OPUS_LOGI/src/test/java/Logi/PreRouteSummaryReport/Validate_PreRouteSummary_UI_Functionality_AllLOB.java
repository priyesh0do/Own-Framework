package Logi.PreRouteSummaryReport;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Driver.BrowserOpen;
import ObjectRepository.General.FilterSection;
import ObjectRepository.General.HomePage;
import ObjectRepository.General.Login;
import ObjectRepository.Logi.PreRouteSummary;
import Opus.BaseClass;
import SupportLibraries.Excel;
import SupportLibraries.GlobalVariables;
import SupportLibraries.Report;
import SupportLibraries.Util;
import TestData.GlobalExpectedColumns;

public class Validate_PreRouteSummary_UI_Functionality_AllLOB extends BaseClass {

	Login login;
	HomePage sdiMegaObj;
	FilterSection sdoFilterObj;
	PreRouteSummary PreRouteSummaryObj;
	@Test(groups = "Opus", dataProvider = "LOGI_Reports_Data")
	public void Validation_PreRouteSummaryReport_SortingAndFilterFunctionality(String Scenario,String RunMode,String ReportName, String SiteID, String LOB,
			String FromDate, String ToDate, String Route,String Driver,String ClockInTime,String ClockOutTime,String Ticket) throws InterruptedException, IOException {
		
			
			if(Scenario.equalsIgnoreCase("Validate_PreRouteSummary_UI_Functionality_AllLOB") & RunMode.equals("Yes")) {
				test = Report.testCreate(extent, test, "Validate_PreRouteSummay_UI_Functionality_AllLOB");
			    driver = BrowserOpen.StartBrowser(driver, configProp.getProperty("Browser"), configProp.getProperty("URL"));
				login = new Login(driver, test);
				String downloadFilePath=systemDir + "\\downloads";
				List<String> UIDataList=new ArrayList<>();
				List<String> actualSummaryTableColumns=new ArrayList<>();
				List<String> actualPerformacneSubViewTableColumns = new ArrayList<>();
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
				PreRouteSummaryObj=new PreRouteSummary(driver, test);
				Util.pageScroll(0, 250);
				
				actualSummaryTableColumns = Util.getColumnNames("dtPreRouteSummary");
				Util.validateColumns(actualSummaryTableColumns, GlobalExpectedColumns.PreRouteSummarysummaryColumns, "Pre Route Summary");
								
				actualPerformacneSubViewTableColumns = Util.getColumnNames("dtPreRouteDetail");
				Util.validateColumns(actualPerformacneSubViewTableColumns, GlobalExpectedColumns.PreRouteSummarySubViewColumns, "Route Summary Sub View");
				
				//Validate Export Functionality Working Fine
				Util.validateExportToExcelColumns("actExportXLS", GlobalExpectedColumns.excelExportColumnsPreRouteSummaryReport, downloadFilePath,14);
				
				//PreRouteSummary
				UIDataList=Util.getUIColumnData(ReportName,"Detail","Grp","PreRouteSummary");
				Util.validateExportExcelData(downloadFilePath, ReportName, "Tier", 13, UIDataList);
				
				UIDataList=Util.getUIColumnData(ReportName,"Detail","Area","PreRouteSummary");
				Util.validateExportExcelData(downloadFilePath, ReportName, "Area", 13, UIDataList);
				
				UIDataList=Util.getUIColumnData(ReportName,"Detail","BU","PreRouteSummary");
				Util.validateExportExcelData(downloadFilePath, ReportName, "BU", 13, UIDataList);
				
				UIDataList=Util.getUIColumnData(ReportName,"Detail","Site","PreRouteSummary");
				Util.validateExportExcelData(downloadFilePath, ReportName, "Site", 13, UIDataList);
				
				UIDataList=Util.getUIColumnData(ReportName,"Detail","ExcpTime","PreRouteSummary");
				Util.validateExportExcelData(downloadFilePath, ReportName, "Exception Time (h:m)", 13, UIDataList);
				
				UIDataList=Util.getUIColumnData(ReportName,"Detail","NumPreRteExcp","PreRouteSummary");
				Util.validateExportExcelData(downloadFilePath, ReportName, "# of Pre-Routes w/ Exception", 13, UIDataList);
				
				UIDataList=Util.getUIColumnData(ReportName,"Detail","NumPreRte","PreRouteSummary");
				Util.validateExportExcelData(downloadFilePath, ReportName, "# of Pre-Routes", 13, UIDataList);
				
				UIDataList=Util.getUIColumnData(ReportName,"Detail","ExcpTimePct","PreRouteSummary");
				Util.validateExportExcelData(downloadFilePath, ReportName, "Exception Time %", 13, UIDataList);
				
				UIDataList=Util.getUIColumnData(ReportName,"Detail","VarFromPlanPct","PreRouteSummary");
				Util.validateExportExcelData(downloadFilePath, ReportName, "Variance from Plan %", 13, UIDataList);
				
				UIDataList=Util.getUIColumnData(ReportName,"Detail","AvgActTm","PreRouteSummary");
				Util.validateExportExcelData(downloadFilePath, ReportName, "Avg Actual (h:m)", 13, UIDataList);
				
				UIDataList=Util.getUIColumnData(ReportName,"Detail","AvgPlanTm","PreRouteSummary");
				Util.validateExportExcelData(downloadFilePath, ReportName, "Avg Plan (h:m)", 13, UIDataList);
				
				Util.validateSortingFunctionality(ReportName,"","Grp", "Ascending","String","PreRouteSummary");
				Util.validateSortingFunctionality(ReportName,"","Grp", "Descending","String","PreRouteSummary");
				
				Util.validateSortingFunctionality(ReportName,"","Area", "Ascending","String","PreRouteSummary");
				Util.validateSortingFunctionality(ReportName,"","Area", "Descending","String","PreRouteSummary");
				
				Util.validateSortingFunctionality(ReportName,"","BU", "Ascending","String","PreRouteSummary");
				Util.validateSortingFunctionality(ReportName,"","BU", "Descending","String","PreRouteSummary");
				
				Util.validateSortingFunctionality(ReportName,"","Site", "Ascending","String","PreRouteSummary");
				Util.validateSortingFunctionality(ReportName,"","Site", "Descending","String","PreRouteSummary");
				
				Util.validateSortingFunctionality(ReportName,"","ExcpTime", "Ascending","Time","PreRouteSummary");
				Util.validateSortingFunctionality(ReportName,"","ExcpTime", "Descending","Time","PreRouteSummary");
				
				Util.validateSortingFunctionality(ReportName,"","NumPreRteExcp", "Ascending","String","PreRouteSummary");
				Util.validateSortingFunctionality(ReportName,"","NumPreRteExcp", "Descending","String","PreRouteSummary");
				
				Util.validateSortingFunctionality(ReportName,"","NumPreRte", "Ascending","String","PreRouteSummary");
				Util.validateSortingFunctionality(ReportName,"","NumPreRte", "Descending","String","PreRouteSummary");
				
				Util.validateSortingFunctionality(ReportName,"","ExcpTimePct", "Ascending","String","PreRouteSummary");
				Util.validateSortingFunctionality(ReportName,"","ExcpTimePct", "Descending","String","PreRouteSummary");
				
				Util.validateSortingFunctionality(ReportName,"","VarFromPlanPct", "Ascending","Double","PreRouteSummary");
				Util.validateSortingFunctionality(ReportName,"","VarFromPlanPct", "Descending","Double","PreRouteSummary");
				
				Util.validateSortingFunctionality(ReportName,"","AvgActTm", "Ascending","Time","PreRouteSummary");
				Util.validateSortingFunctionality(ReportName,"","AvgActTm", "Descending","Time","PreRouteSummary");
				
				Util.validateSortingFunctionality(ReportName,"","AvgPlanTm", "Ascending","Time","PreRouteSummary");
				Util.validateSortingFunctionality(ReportName,"","AvgPlanTm", "Descending","Time","PreRouteSummary");
				
				PreRouteSummaryObj.ValidatePopUp(SiteID, FromDate, ToDate, ReportName, downloadFilePath);
				
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
