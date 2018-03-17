package Logi.RouteSummaryReport;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Driver.BrowserOpen;
import ObjectRepository.General.FilterSection;
import ObjectRepository.General.GraphSection;
import ObjectRepository.General.HomePage;
import ObjectRepository.General.Login;
import ObjectRepository.Logi.RouteSummaryReport;
import Opus.BaseClass;
import SupportLibraries.Excel;
import SupportLibraries.GlobalVariables;
import SupportLibraries.Report;
import SupportLibraries.Util;
import TestData.GlobalExpectedColumns;

public class Validate_RouteSummary_UI_Functionality_AllLOB extends BaseClass{

	
	Login login;
	HomePage sdiMegaObj;
	FilterSection sdoFilterObj;
	RouteSummaryReport RouteSummaryObj;
	GraphSection graphObj;
	
	@Test(groups = "Opus", dataProvider = "LOGI_Reports_Data")
	public void Validation_RouteSummaryReport_SortingAndFilterFunctionality(String Scenario,String RunMode,String ReportName, String SiteID, String LOB,
			String FromDate, String ToDate, String Route,String Driver,String ClockInTime,String ClockOutTime,String Ticket) throws InterruptedException, IOException, ParseException {
		
			if(Scenario.equalsIgnoreCase("Validate_RouteSummary_UI_Functionality_AllLOB") & RunMode.equals("Yes")) {
				test = Report.testCreate(extent, test, "Validate_RouteSummay_UI_Functionality_AllLOB");
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
				graphObj = new GraphSection(driver, test);
				
				//Validate Date Picker functionality 
				Util.validateFromAndToDate(ReportName);
				
				//Validate Graph Section
				graphObj.validateGraphSection(ReportName, SiteID);
				
				Util.pageScroll(0, 250);
				//Validate Filter is working as expected
				sdoFilterObj.SelectTiers("South", ReportName);
				sdoFilterObj.SelectArea("WM of Texas Oklahoma", ReportName);
				sdoFilterObj.SelectBUs("Waste Management of San Antonio", ReportName);
				sdoFilterObj.SelectSite(SiteID, ReportName);
				sdoFilterObj.SelectSubLobs("Commercial FrontLoad", ReportName);
				sdoFilterObj.SelectDate(FromDate, ToDate, ReportName);
				
				sdoFilterObj.selectSite(SiteID);
				// Validate Filter content is sorted in ascending manner
				Util.validateSortingOfFilterValues("islGroupFilter");
				Util.validateSortingOfFilterValues("islAreaFilter");
				Util.validateSortingOfFilterValues("islBUFilter");
				Util.validateSortingOfFilterValues("islSiteFilter");
				Util.validateSortingOfFilterValues("islLOBFilter");
				Util.validateSortingOfFilterValues("islSubLOBFilter");
				sdoFilterObj.selectFromDate(FromDate);
				sdoFilterObj.selectToDate(ToDate);
				sdoFilterObj.selectGO();
				RouteSummaryObj= new RouteSummaryReport(driver, test);
				Util.pageScroll(0, 250);
				
				//Verify Font Color of the Eff Var field Summary Section
				Util.ValidateFontAndColor("Total Eff Var (in hrs) in Summary Section", "dtRouteSummary", 1, 2);
				Util.ValidateFontAndColor("Avg Var (in hrs) in Summary Section", "dtRouteSummary", 2, 2);
				Util.ValidateFontAndColor("Total Driver & Helper Eff Var (in hrs) in Summary Section", "dtRouteSummary", 1, 3);
				Util.ValidateFontAndColor("Avg Driver & Helper Eff Var (in hrs) in Summary Section", "dtRouteSummary", 2, 3);
								
				//Validate Summary column Names of the Route Summary report
				actualSummaryTableColumns = Util.getColumnNames("dtRouteSummary");
				Util.validateColumns(actualSummaryTableColumns, GlobalExpectedColumns.RouteSummarysummaryColumns, "Route Summary");
				
				//Validate Performance subView column Names of the efficiency performance report
				actualPerformacneSubViewTableColumns = Util.getColumnNames("dtRouteDetail");
				Util.validateColumns(actualPerformacneSubViewTableColumns, GlobalExpectedColumns.RouteSummarySubViewColumns, "Route Summary Sub View");
				
				//Validate Export Functionality Working Fine
				Util.validateExportToExcelColumns("actExportXLS", GlobalExpectedColumns.excelExportColumnsRouteSummaryReport, downloadFilePath,14); 
				
				UIDataList=Util.getUIColumnData(ReportName,"Detail","Tier","RouteSummary");
				Util.validateExportExcelData(downloadFilePath, ReportName, "Tier", 13, UIDataList);
								
				UIDataList=Util.getUIColumnData(ReportName,"Detail","Area","RouteSummary");
				Util.validateExportExcelData(downloadFilePath, ReportName, "Area", 13, UIDataList);
				
				UIDataList=Util.getUIColumnData(ReportName,"Detail","BU","RouteSummary");
				Util.validateExportExcelData(downloadFilePath, ReportName, "BU", 13, UIDataList);
								
				UIDataList=Util.getUIColumnData(ReportName,"Detail","Site","RouteSummary");
				Util.validateExportExcelData(downloadFilePath, ReportName, "Site", 13, UIDataList);
						
				UIDataList=Util.getUIColumnData(ReportName,"Detail","EffVar","RouteSummary");
				Util.validateExportExcelData(downloadFilePath, ReportName, "Eff Var (in hrs)", 13, UIDataList);
				
				UIDataList=Util.getUIColumnData(ReportName,"Detail","HlpEffVar","RouteSummary");
				Util.validateExportExcelData(downloadFilePath, ReportName, "Driver & Helper Eff Var (in hrs)", 13, UIDataList);
				
				UIDataList=Util.getUIColumnData(ReportName,"Detail","UnitsVar","RouteSummary");
				Util.validateExportExcelData(downloadFilePath, ReportName, "Units Var", 13, UIDataList);
				
				UIDataList=Util.getUIColumnData(ReportName,"Detail","PreRte","RouteSummary");
				Util.validateExportExcelData(downloadFilePath, ReportName, "Pre-Route (h:m)", 13, UIDataList);
				
				UIDataList=Util.getUIColumnData(ReportName,"Detail","PostRte","RouteSummary");
				Util.validateExportExcelData(downloadFilePath, ReportName, "Post-Route (h:m)", 13, UIDataList);
				
				UIDataList=Util.getUIColumnData(ReportName,"Detail","DispCycle","RouteSummary");
				Util.validateExportExcelData(downloadFilePath, ReportName, "Disp Cycle (h:m)", 13, UIDataList);
				
				UIDataList=Util.getUIColumnData(ReportName,"Detail","Downtime","RouteSummary");
				Util.validateExportExcelData(downloadFilePath, ReportName, "Down time (h:m)", 13, UIDataList);
				
				UIDataList=Util.getUIColumnData(ReportName,"Detail","Idle","RouteSummary");
				Util.validateExportExcelData(downloadFilePath, ReportName, "Net Idle (h:m)", 13, UIDataList);
				
				UIDataList=Util.getUIColumnData(ReportName,"Detail","Meal","RouteSummary");
				Util.validateExportExcelData(downloadFilePath, ReportName, "Meal (h:m)", 13, UIDataList);
				
				UIDataList=Util.getUIColumnData(ReportName,"Detail","Seq","RouteSummary");
				Util.validateExportExcelData(downloadFilePath, ReportName, "In Seq %", 13, UIDataList);
				
				UIDataList=Util.getUIColumnData(ReportName,"Detail","Miles","RouteSummary");
				Util.validateExportExcelData(downloadFilePath, ReportName, "Miles", 13, UIDataList);
				
				Util.validateSortingFunctionality(ReportName,"","Tier", "Ascending","String","RouteSummary");
				Util.validateSortingFunctionality(ReportName,"","Tier", "Descending","String","RouteSummary");
				
				Util.validateSortingFunctionality(ReportName,"","Area", "Ascending","String","RouteSummary");
				Util.validateSortingFunctionality(ReportName,"","Area", "Descending","String","RouteSummary");
				
				Util.validateSortingFunctionality(ReportName,"","BU", "Ascending","String","RouteSummary");
				Util.validateSortingFunctionality(ReportName,"","BU", "Descending","String","RouteSummary");
				
				Util.validateSortingFunctionality(ReportName,"","Site", "Ascending","String","RouteSummary");
				Util.validateSortingFunctionality(ReportName,"","Site", "Descending","String","RouteSummary");
				
				Util.validateSortingFunctionality(ReportName,"","EffVar", "Ascending","Double","RouteSummary");
				Util.validateSortingFunctionality(ReportName,"","EffVar", "Descending","Double","RouteSummary");
				
				Util.validateSortingFunctionality(ReportName,"","HlpEffVar", "Ascending","Double","RouteSummary");
				Util.validateSortingFunctionality(ReportName,"","HlpEffVar", "Descending","Double","RouteSummary");
				
				Util.validateSortingFunctionality(ReportName,"","UnitsVar", "Ascending","Double","RouteSummary");
				Util.validateSortingFunctionality(ReportName,"","UnitsVar", "Descending","Double","RouteSummary");
				
				Util.validateSortingFunctionality(ReportName,"","PreRte", "Ascending","Time","RouteSummary");
				Util.validateSortingFunctionality(ReportName,"","PreRte", "Descending","Time","RouteSummary");
				
				Util.validateSortingFunctionality(ReportName,"","PostRte", "Ascending","Time","RouteSummary");
				Util.validateSortingFunctionality(ReportName,"","PostRte", "Descending","Time","RouteSummary");
				
				Util.validateSortingFunctionality(ReportName,"","DispCycle", "Ascending","Time","RouteSummary");
				Util.validateSortingFunctionality(ReportName,"","DispCycle", "Descending","Time","RouteSummary");
				
				Util.validateSortingFunctionality(ReportName,"","Downtime", "Ascending","Time","RouteSummary");
				Util.validateSortingFunctionality(ReportName,"","Downtime", "Descending","Time","RouteSummary");
				
				Util.validateSortingFunctionality(ReportName,"","Idle", "Ascending","Time","RouteSummary");
				Util.validateSortingFunctionality(ReportName,"","Idle", "Descending","Time","RouteSummary");
				
				Util.validateSortingFunctionality(ReportName,"","Meal", "Ascending","Time","RouteSummary");
				Util.validateSortingFunctionality(ReportName,"","Meal", "Descending","Time","RouteSummary");
				
				Util.validateSortingFunctionality(ReportName,"","Seq", "Ascending","Double","RouteSummary");
				Util.validateSortingFunctionality(ReportName,"","Seq", "Descending","Double","RouteSummary");
				
				Util.validateSortingFunctionality(ReportName,"","Miles", "Ascending","String","RouteSummary");
				Util.validateSortingFunctionality(ReportName,"","Miles", "Descending","String","RouteSummary");
					
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
