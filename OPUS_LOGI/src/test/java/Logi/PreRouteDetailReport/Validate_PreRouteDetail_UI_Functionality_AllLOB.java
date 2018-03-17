
package Logi.PreRouteDetailReport;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Driver.BrowserOpen;
import ObjectRepository.General.FilterSection;
import ObjectRepository.General.HomePage;
import ObjectRepository.General.Login;
import ObjectRepository.Logi.PreRouteDetailReport;
import Opus.BaseClass;
import SupportLibraries.Excel;
import SupportLibraries.GlobalVariables;
import SupportLibraries.Report;
import SupportLibraries.Util;
import TestData.GlobalExpectedColumns;

public class Validate_PreRouteDetail_UI_Functionality_AllLOB extends BaseClass {

	Login login;
	HomePage sdiMegaObj;
	FilterSection sdoFilterObj;
	PreRouteDetailReport preRouteDetailobj;
	@Test(groups = "Opus", dataProvider = "LOGI_Reports_Data")
	public void Validation_PreRouteDetailReport_SortingAndFilterFunctionality(String Scenario,String RunMode,String ReportName, String SiteID, String LOB,
			String FromDate, String ToDate, String Route,String Driver,String ClockInTime,String ClockOutTime,String Ticket) throws Exception {	
	
			if(Scenario.equalsIgnoreCase("Validate_PreRouteDetail_UI_Functionality_AllLOB") & RunMode.equals("Yes"))
			{
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
					Util.validateSortingOfFilterValues("islLOBFilterS");
					Util.validateSortingOfFilterValues("islSubLOBFilter");
					Util.validateSortingOfFilterValues("islRMFilter");
					Util.validateSortingOfFilterValues("islRteTypeFilter");
					Util.validateSortingOfFilterValues("islRteFilter");
					Util.validateSortingOfFilterValues("islDriverFilter");
					Util.validateSortingOfFilterValues("islRptGrpFilter");
					sdoFilterObj.selectFromDate(FromDate);
					sdoFilterObj.selectToDate(ToDate);
					sdoFilterObj.selectGO();
					preRouteDetailobj=new PreRouteDetailReport(driver, test);
					
					//Validate Summary column Names of the Pre Route Detail report
					actualSummaryTableColumns = Util.getColumnNames("dtPreRouteSummary");
					Util.validateColumns(actualSummaryTableColumns, GlobalExpectedColumns.PreRouteDetailSummaryColumns, "PreRouteDetail Summary");
				
					//Validate Performance subView column Names of the Pre Route Detail report
					actualPerformacneSubViewTableColumns = Util.getColumnNames("t2e");
					Util.validateColumns(actualPerformacneSubViewTableColumns, GlobalExpectedColumns.PreRouteDetailSubViewColumns, "PreRouteDetail Sub View");
				
					//Validate Export Functionality Working Fine
					Util.validateExportToExcelColumns("actExportXLS", GlobalExpectedColumns.excelExportColumnsPreRouteDetailReport, downloadFilePath,19);
				      
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
					
					preRouteDetailobj.selectActualSubView();
				 
					//Validate Actual subView column Names of the Pre Route Detail report
					actualSubviewColumns = Util.getColumnNames("t2e");
					Util.validateColumns(actualSubviewColumns, GlobalExpectedColumns.PreRouteDetailActualSubViewColumns, "Pre Route Detail Actual Sub View");
				  
					Util.validateSortingFunctionality(ReportName,"Actual","Date", "Ascending","String","Exceptions");
					
									
					UIDataList=Util.getUIColumnData(ReportName,"Detail","ClockIn","Exceptions");
					Util.validateExportExcelData(downloadFilePath, ReportName, "Clock In", 18, UIDataList);
					
					UIDataList=Util.getUIColumnData(ReportName,"Detail","LeaveYard","Exceptions");
					Util.validateExportExcelData(downloadFilePath, ReportName, "Leave Yard", 18, UIDataList);
					
					
					Util.validateSortingFunctionality(ReportName,"Actual","Date", "Descending","String","Exceptions");
					
					Util.validateSortingFunctionality(ReportName,"Actual","Rte", "Ascending","String","Exceptions");
					Util.validateSortingFunctionality(ReportName,"Actual","Rte", "Descending","String","Exceptions");
					
					Util.validateSortingFunctionality(ReportName,"Actual","Driver", "Ascending","String","Exceptions");
					Util.validateSortingFunctionality(ReportName,"Actual","Driver", "Descending","String","Exceptions");
					
					Util.validateSortingFunctionality(ReportName,"Actual","ActTm", "Ascending","Time","Exceptions");
					Util.validateSortingFunctionality(ReportName,"Actual","ActTm", "Descending","Time","Exceptions");
					
					Util.validateSortingFunctionality(ReportName,"Actual","ClockIn", "Ascending","Time","Exceptions");
					Util.validateSortingFunctionality(ReportName,"Actual","ClockIn", "Descending","Time","Exceptions");
					
					Util.validateSortingFunctionality(ReportName,"Actual","LeaveYard", "Ascending","Time","Exceptions");
					Util.validateSortingFunctionality(ReportName,"Actual","LeaveYard", "Descending","Time","Exceptions");
					
					Util.validateSortingFunctionality(ReportName,"Actual","LOB", "Ascending","String","Exceptions");
					Util.validateSortingFunctionality(ReportName,"Actual","LOB", "Descending","String","Exceptions");
					
					Util.validateSortingFunctionality(ReportName,"Actual","SubLOB", "Ascending","String","Exceptions");
					Util.validateSortingFunctionality(ReportName,"Actual","SubLOB", "Descending","String","Exceptions");
					
					Util.validateSortingFunctionality(ReportName,"Actual","RteType", "Ascending","String","Exceptions");
					Util.validateSortingFunctionality(ReportName,"Actual","RteType", "Descending","String","Exceptions");
					
					Util.validateSortingFunctionality(ReportName,"Actual","RteManager", "Ascending","String","Exceptions");
					Util.validateSortingFunctionality(ReportName,"Actual","RteManager", "Descending","String","Exceptions");
   
					////Select All Tab
					preRouteDetailobj.selectAllTab();
					Thread.sleep(4000);
					
					ALLTabTableColumns = Util.getColumnNames("t2");
					Util.validateColumns(ALLTabTableColumns, GlobalExpectedColumns.PreRouteDetailAllTabColumns, "Pre Route Detail All Tab");
					
					Util.validateSortingFunctionality(ReportName,"ALL Tab","Date", "Ascending","String","All");
					
					Util.validateSortingFunctionality(ReportName,"Exceptions","Date", "Ascending","String","All");
					Util.validateSortingFunctionality(ReportName,"Exceptions","Date", "Descending","String","All");
				
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
					
					preRouteDetailobj.selectActualSubviewAllTab();
					
					
					actualSubviewColumns = Util.getColumnNames("t2");
					Util.validateColumns(actualSubviewColumns, GlobalExpectedColumns.PreRouteDetailActualSubViewColumns, "Pre Route Detail Actual Sub View All Tab");
					
					Util.validateSortingFunctionality(ReportName,"Actual","Date", "Descending","String","All");
					
					Util.validateSortingFunctionality(ReportName,"Actual","Rte", "Ascending","String","All");
					Util.validateSortingFunctionality(ReportName,"Actual","Rte", "Descending","String","All");
					
					Util.validateSortingFunctionality(ReportName,"Actual","Driver", "Ascending","String","All");
					Util.validateSortingFunctionality(ReportName,"Actual","Driver", "Descending","String","All");
					
					Util.validateSortingFunctionality(ReportName,"Actual","ActTm", "Ascending","Time","All");
					Util.validateSortingFunctionality(ReportName,"Actual","ActTm", "Descending","Time","All");
					
					Util.validateSortingFunctionality(ReportName,"Actual","ClockIn", "Ascending","Time","All");
					Util.validateSortingFunctionality(ReportName,"Actual","ClockIn", "Descending","Time","All");
					
					Util.validateSortingFunctionality(ReportName,"Actual","LeaveYard", "Ascending","Time","All");
					Util.validateSortingFunctionality(ReportName,"Actual","LeaveYard", "Descending","Time","All");
					
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
	@DataProvider(name = "LOGI_Reports_Data")
	public Object[][] testData() throws Exception {
		Excel ex = new Excel();
		Object[][] arrayObject = ex.getTableArray(configProp.getProperty("LOGITestCaseExcel"),
				configProp.getProperty("LOGITestDataSheet"));
		return arrayObject;
	}
}
