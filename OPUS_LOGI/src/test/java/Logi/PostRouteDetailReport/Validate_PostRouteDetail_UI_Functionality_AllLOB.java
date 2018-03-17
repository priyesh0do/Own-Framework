package Logi.PostRouteDetailReport;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Driver.BrowserOpen;
import ObjectRepository.General.FilterSection;
import ObjectRepository.General.HomePage;
import ObjectRepository.General.Login;
import ObjectRepository.Logi.PostRouteDetailReport;
import ObjectRepository.Logi.PreRouteDetailReport;
import Opus.BaseClass;
import SupportLibraries.Excel;
import SupportLibraries.GlobalVariables;
import SupportLibraries.Report;
import SupportLibraries.Util;
import TestData.GlobalExpectedColumns;

public class Validate_PostRouteDetail_UI_Functionality_AllLOB extends BaseClass {

	Login login;
	HomePage sdiMegaObj;
	FilterSection sdoFilterObj;
	PostRouteDetailReport postRouteDetailobj;
	@Test(groups = "Opus", dataProvider = "LOGI_Reports_Data")
	public void Validation_PreRouteDetailReport_SortingAndFilterFunctionality(String Scenario,String RunMode,String ReportName, String SiteID, String LOB,
			String FromDate, String ToDate, String Route,String Driver,String ClockInTime,String ClockOutTime,String Ticket) throws Exception {	
	
		if (!Util.isTestCaseExecutable("Validate_PostRouteDetail_UI_Functionality_AllLOB", GlobalVariables.logiTestCase)) {
			test = Report.testCreate(extent, test, "Validate_PostRouteDetail_UI_Functionality_AllLOB");
			Report.SkipTest(test, "Skipping the test as runmode is NO");
		}
		else {
			if(Scenario.equalsIgnoreCase("Validate_PostRouteDetail_UI_Functionality_AllLOB") & RunMode.equals("Yes")) {
				test = Report.testCreate(extent, test, "Validate_PreRouteDetail_UI_Functionality_AllLOB");
			    driver = BrowserOpen.StartBrowser(driver, configProp.getProperty("Browser"), configProp.getProperty("URL"));
				login = new Login(driver, test);
				String downloadFilePath=systemDir + "\\downloads";
				List<String> UIDataList=new ArrayList<>();
				List<String> actualSummaryTableColumns=new ArrayList<>();
				List<String> actualPerformacneSubViewTableColumns =new ArrayList<>();
				List<String> actualSubviewColumns=new ArrayList<>();
				List<String> ALLTabTableColumns=new ArrayList<>();
				login.LoginSDI(configProp.getProperty("userId"), configProp.getProperty("Password"));
				System.out.println("Login successfully done");
				// Select RM Dashboard
				sdiMegaObj = new HomePage(driver, test);
				sdiMegaObj.ClickOnReport(ReportName);
				sdoFilterObj = new FilterSection(driver, test);
				// select site
				sdoFilterObj.selectSite(SiteID);
				// Validate Filter content is sorted in ascending manner
				Util.validateSortingOfFilterValues("islSiteDFilter");
				Util.validateSortingOfFilterValues("islLOBFilter");
				Util.validateSortingOfFilterValues("islSubLOBFilter");
				Util.validateSortingOfFilterValues("islRMFilter");
				Util.validateSortingOfFilterValues("islRteTypeFilter");
				Util.validateSortingOfFilterValues("islRteFilter");
				Util.validateSortingOfFilterValues("islDriverFilter");
				Util.validateSortingOfFilterValues("islRptGrpFilter");
				sdoFilterObj.selectFromDate(FromDate);
				sdoFilterObj.selectToDate(ToDate);
				sdoFilterObj.selectGO();
				postRouteDetailobj=new PostRouteDetailReport(driver, test);
				//Validate Summary column Names of the Post Route Detail report
				actualSummaryTableColumns = Util.getColumnNames("dtPostRouteSummary");
				Util.validateColumns(actualSummaryTableColumns, GlobalExpectedColumns.PostRouteDetailSummaryColumns, "PostRouteDetail Summary");
		      
				//Validate Performance subView column Names of the Post Route Detail report
				actualPerformacneSubViewTableColumns = Util.getColumnNames("t2e");
				Util.validateColumns(actualPerformacneSubViewTableColumns, GlobalExpectedColumns.PostRouteDetailSubViewColumns, "PostRouteDetail Sub View");
		     

				//Validate Export Functionality Working Fine
				Util.validateExportToExcelColumns("actExportXLS", GlobalExpectedColumns.excelExportColumnsPostRouteDetailReport, downloadFilePath,19);
		      
				UIDataList=Util.getUIColumnData(ReportName,"Detail","Date","Exceptions");
				Util.validateExportExcelData(downloadFilePath, ReportName, "Date", 18, UIDataList);	
				
				UIDataList=Util.getUIColumnData(ReportName,"Detail","Rte","Exceptions");
				Util.validateExportExcelData(downloadFilePath, ReportName, "Route", 18, UIDataList);
				
				UIDataList=Util.getUIColumnData(ReportName,"Detail","Driver","Exceptions");
				Util.validateExportExcelData(downloadFilePath, ReportName, "Driver", 18, UIDataList);
				
				UIDataList=Util.getUIColumnData(ReportName,"Detail","ExcpTm","Exceptions");
				Util.validateExportExcelData(downloadFilePath, ReportName, "Exception Time (h:m)", 18, UIDataList);
				
				UIDataList=Util.getUIColumnData(ReportName,"Detail","ActTm","Exceptions");
				Util.validateExportExcelData(downloadFilePath, ReportName, "Actual (h:m)", 18, UIDataList);
				
				UIDataList=Util.getUIColumnData(ReportName,"Detail","PlanTm","Exceptions");
				Util.validateExportExcelData(downloadFilePath, ReportName, "Plan (h:m)", 18, UIDataList);
				
				UIDataList=Util.getUIColumnData(ReportName,"Detail","LOB","Exceptions");
				Util.validateExportExcelData(downloadFilePath, ReportName, "LOB", 18, UIDataList);
				
				UIDataList=Util.getUIColumnData(ReportName,"Detail","SubLOB","Exceptions");
				Util.validateExportExcelData(downloadFilePath, ReportName, "Sub LOB", 18, UIDataList);
				
				UIDataList=Util.getUIColumnData(ReportName,"Detail","RteType","Exceptions");
				Util.validateExportExcelData(downloadFilePath, ReportName, "Route Type", 18, UIDataList);
				
				UIDataList=Util.getUIColumnData(ReportName,"Detail","RteManager","Exceptions");
				Util.validateExportExcelData(downloadFilePath, ReportName, "Route Manager", 18, UIDataList);
				
				Util.validateSortingFunctionality(ReportName,"Exceptions","Date", "Ascending","String","Exceptions");
				Util.validateSortingFunctionality(ReportName,"Exceptions","Date", "Descending","String","Exceptions");
				
				Util.validateSortingFunctionality(ReportName,"Exceptions","Rte", "Ascending","String","Exceptions");
				Util.validateSortingFunctionality(ReportName,"Exceptions","Rte", "Descending","String","Exceptions");
				
				Util.validateSortingFunctionality(ReportName,"Exceptions","Driver", "Ascending","String","Exceptions");
				Util.validateSortingFunctionality(ReportName,"Exceptions","Driver", "Descending","String","Exceptions");
				
				Util.validateSortingFunctionality(ReportName,"Exceptions","ExcpTm", "Ascending","Time","Exceptions");
				Util.validateSortingFunctionality(ReportName,"Exceptions","ExcpTm", "Descending","Time","Exceptions");
				
				Util.validateSortingFunctionality(ReportName,"Exceptions","ActTm", "Ascending","Time","Exceptions");
				Util.validateSortingFunctionality(ReportName,"Exceptions","ActTm", "Descending","Time","Exceptions");
				
				Util.validateSortingFunctionality(ReportName,"Exceptions","PlanTm", "Ascending","Time","Exceptions");
				Util.validateSortingFunctionality(ReportName,"Exceptions","PlanTm", "Descending","Time","Exceptions");
				
				Util.validateSortingFunctionality(ReportName,"Exceptions","LOB", "Ascending","String","Exceptions");
				Util.validateSortingFunctionality(ReportName,"Exceptions","LOB", "Descending","String","Exceptions");
				
				Util.validateSortingFunctionality(ReportName,"Exceptions","SubLOB", "Ascending","String","Exceptions");
				Util.validateSortingFunctionality(ReportName,"Exceptions","SubLOB", "Descending","String","Exceptions");
				
				Util.validateSortingFunctionality(ReportName,"Exceptions","RteType", "Ascending","String","Exceptions");
				Util.validateSortingFunctionality(ReportName,"Exceptions","RteType", "Descending","String","Exceptions");
				
				Util.validateSortingFunctionality(ReportName,"Exceptions","RteManager", "Ascending","String","Exceptions");
				Util.validateSortingFunctionality(ReportName,"Exceptions","RteManager", "Descending","String","Exceptions");
				
				postRouteDetailobj.selectActualSubView();
				Thread.sleep(2000);				
				
				//Validate Actual subView column Names of the Post Route Detail report
				actualSubviewColumns = Util.getColumnNames("t2e");
				Util.validateColumns(actualSubviewColumns, GlobalExpectedColumns.PostRouteDetailActualSubViewColumns, "Post Route Detail Actual Sub View");
				
				Util.validateSortingFunctionality(ReportName,"Actual","Date", "Ascending","String","Exceptions");
				
				UIDataList=Util.getUIColumnData(ReportName,"Detail","ArriveYard","Exceptions");
				Util.validateExportExcelData(downloadFilePath, ReportName, "Arrive Yard", 18, UIDataList);
				
				UIDataList=Util.getUIColumnData(ReportName,"Detail","ClockOut","Exceptions");
				Util.validateExportExcelData(downloadFilePath, ReportName, "Clock Out", 18, UIDataList);
				
				Util.validateSortingFunctionality(ReportName,"Actual","Date", "Descending","String","Exceptions");
				
				Util.validateSortingFunctionality(ReportName,"Exceptions","Rte", "Ascending","String","Exceptions");
				Util.validateSortingFunctionality(ReportName,"Exceptions","Rte", "Descending","String","Exceptions");
				
				Util.validateSortingFunctionality(ReportName,"Exceptions","Driver", "Ascending","String","Exceptions");
				Util.validateSortingFunctionality(ReportName,"Exceptions","Driver", "Descending","String","Exceptions");
				
				Util.validateSortingFunctionality(ReportName,"Exceptions","ActTm", "Ascending","Time","Exceptions");
				Util.validateSortingFunctionality(ReportName,"Exceptions","ActTm", "Descending","Time","Exceptions");
				
				Util.validateSortingFunctionality(ReportName,"Exceptions","ArriveYard", "Ascending","Time","Exceptions");
				Util.validateSortingFunctionality(ReportName,"Exceptions","Arrive Yard", "Descending","Time","Exceptions");
				
				Util.validateSortingFunctionality(ReportName,"Exceptions","ClockOut", "Ascending","Time","Exceptions");
				Util.validateSortingFunctionality(ReportName,"Exceptions","Clock Out", "Descending","Time","Exceptions");
				
				Util.validateSortingFunctionality(ReportName,"Exceptions","LOB", "Ascending","String","Exceptions");
				Util.validateSortingFunctionality(ReportName,"Exceptions","LOB", "Descending","String","Exceptions");
				
				Util.validateSortingFunctionality(ReportName,"Exceptions","SubLOB", "Ascending","String","Exceptions");
				Util.validateSortingFunctionality(ReportName,"Exceptions","SubLOB", "Descending","String","Exceptions");
				
				Util.validateSortingFunctionality(ReportName,"Exceptions","RteType", "Ascending","String","Exceptions");
				Util.validateSortingFunctionality(ReportName,"Exceptions","RteType", "Descending","String","Exceptions");
				
				Util.validateSortingFunctionality(ReportName,"Exceptions","RteManager", "Ascending","String","Exceptions");
				Util.validateSortingFunctionality(ReportName,"Exceptions","RteManager", "Descending","String","Exceptions");
				
				//Select All Tab
				postRouteDetailobj.selectAllTab();
				
				ALLTabTableColumns = Util.getColumnNames("t2");
				Util.validateColumns(ALLTabTableColumns, GlobalExpectedColumns.PostRouteDetailAllTabColumns, "Post Route Detail All Tab");
				
				Util.validateSortingFunctionality(ReportName,"ALL Tab","Date", "Ascending","String","All");
				
				Util.validateSortingFunctionality(ReportName,"Exceptions","Rte", "Ascending","String","All");
				Util.validateSortingFunctionality(ReportName,"Exceptions","Rte", "Descending","String","All");
				
				Util.validateSortingFunctionality(ReportName,"Exceptions","Driver", "Ascending","String","All");
				Util.validateSortingFunctionality(ReportName,"Exceptions","Driver", "Descending","String","All");
				
				Util.validateSortingFunctionality(ReportName,"Exceptions","ExcpTm", "Ascending","Time","All");
				Util.validateSortingFunctionality(ReportName,"Exceptions","ExcpTm", "Descending","Time","All");
				
				Util.validateSortingFunctionality(ReportName,"Exceptions","ActTm", "Ascending","Time","All");
				Util.validateSortingFunctionality(ReportName,"Exceptions","ActTm", "Descending","Time","All");
				
				Util.validateSortingFunctionality(ReportName,"Exceptions","PlanTm", "Ascending","Time","All");
				Util.validateSortingFunctionality(ReportName,"Exceptions","PlanTm", "Descending","Time","All");
				
				Util.validateSortingFunctionality(ReportName,"Exceptions","LOB", "Ascending","String","All");
				Util.validateSortingFunctionality(ReportName,"Exceptions","LOB", "Descending","String","All");
				
				Util.validateSortingFunctionality(ReportName,"Exceptions","SubLOB", "Ascending","String","All");
				Util.validateSortingFunctionality(ReportName,"Exceptions","SubLOB", "Descending","String","All");
				
				Util.validateSortingFunctionality(ReportName,"Exceptions","RteType", "Ascending","String","All");
				Util.validateSortingFunctionality(ReportName,"Exceptions","RteType", "Descending","String","All");
				
				Util.validateSortingFunctionality(ReportName,"Exceptions","RteManager", "Ascending","String","All");
				Util.validateSortingFunctionality(ReportName,"Exceptions","RteManager", "Descending","String","All");
				
				postRouteDetailobj.selectActualSubviewAllTab();
				
				actualSubviewColumns = Util.getColumnNames("t2");
				Util.validateColumns(actualSubviewColumns, GlobalExpectedColumns.PostRouteDetailActualSubViewColumns, "Post Route Detail Actual Sub View All Tab");
				
				Util.validateSortingFunctionality(ReportName,"Actual","Date", "Descending","String","All");
				
				Util.validateSortingFunctionality(ReportName,"Actual","Rte", "Ascending","String","All");
				Util.validateSortingFunctionality(ReportName,"Actual","Rte", "Descending","String","All");
				
				Util.validateSortingFunctionality(ReportName,"Actual","Driver", "Ascending","String","All");
				Util.validateSortingFunctionality(ReportName,"Actual","Driver", "Descending","String","All");
				
				Util.validateSortingFunctionality(ReportName,"Actual","ActTm", "Ascending","Time","All");
				Util.validateSortingFunctionality(ReportName,"Actual","ActTm", "Descending","Time","All");
				
				Util.validateSortingFunctionality(ReportName,"Actual","ArriveYard", "Ascending","Time","All");
				Util.validateSortingFunctionality(ReportName,"Actual","Arrive Yard", "Descending","Time","All");
				
				Util.validateSortingFunctionality(ReportName,"Actual","ClockOut", "Ascending","Time","All");
				Util.validateSortingFunctionality(ReportName,"Actual","Clock Out", "Descending","Time","All");
				
				Util.validateSortingFunctionality(ReportName,"Actual","LOB", "Ascending","String","All");
				Util.validateSortingFunctionality(ReportName,"Actual","LOB", "Descending","String","All");
				
				Util.validateSortingFunctionality(ReportName,"Actual","SubLOB", "Ascending","String","All");
				Util.validateSortingFunctionality(ReportName,"Actual","SubLOB", "Descending","String","All");
				
				Util.validateSortingFunctionality(ReportName,"Actual","RteType", "Ascending","String","All");
				Util.validateSortingFunctionality(ReportName,"Actual","RteType", "Descending","String","All");
				
				Util.validateSortingFunctionality(ReportName,"Actual","RteManager", "Ascending","String","All");
				Util.validateSortingFunctionality(ReportName,"Actual","RteManager", "Descending","String","All");
				
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
