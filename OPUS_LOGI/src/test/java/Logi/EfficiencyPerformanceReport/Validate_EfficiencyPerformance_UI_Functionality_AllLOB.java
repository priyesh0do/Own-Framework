package Logi.EfficiencyPerformanceReport;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Driver.BrowserOpen;
import ObjectRepository.General.FilterSection;
import ObjectRepository.General.HomePage;
import ObjectRepository.General.Login;
import ObjectRepository.Logi.EfficiencyPerformanceReport;
import Opus.BaseClass;
import SupportLibraries.Excel;
import SupportLibraries.GlobalVariables;
import SupportLibraries.Report;
import SupportLibraries.Util;
import TestData.GlobalExpectedColumns;

public class Validate_EfficiencyPerformance_UI_Functionality_AllLOB extends BaseClass{
	
	Login login;
	HomePage sdiMegaObj;
	FilterSection sdoFilterObj;
	EfficiencyPerformanceReport effPerformanceObj;
	@Test(groups = "Opus", dataProvider = "LOGI_Reports_Data")
	public void Validation_EfficiencyPerformanceReport_UI_Functionality_AllLOB(String Scenario,String RunMode,String ReportName, String SiteID, String LOB,
			String FromDate, String ToDate, String Route,String Driver,String ClockInTime,String ClockOutTime,String Ticket) throws Exception {	
	
		if (!Util.isTestCaseExecutable("Validate_EfficiencyPerformance_UI_Functionality_AllLOB", GlobalVariables.logiTestCase)) {
			test = Report.testCreate(extent, test, "Validate_EfficiencyPerformance_UI_Functionality_AllLOB");
			Report.SkipTest(test, "Skipping the test as runmode is NO");
		} else {
		
		if(Scenario.equalsIgnoreCase("Validate_EfficiencyPerformance_UI_Functionality_AllLOB") & RunMode.equals("Yes"))
		{		
		    test = Report.testCreate(extent, test, "Validate_EfficiencyPerformance_UI_Functionality_AllLOB");
		    driver = BrowserOpen.StartBrowser(driver, configProp.getProperty("Browser"), configProp.getProperty("URL"));
			login = new Login(driver, test);
			String downloadFilePath=systemDir + "\\downloads";
			List<String> UIDataList=new ArrayList<>();
			List<String> actualSummaryTableColumns=new ArrayList<>();
			List<String> actualPerformacneSubViewTableColumns =new ArrayList<>();
			List<String> actualRouteSegmentsSubViewTableColumns=new ArrayList<>();
			List<String> actualDisposalLoadsSubViewTableColumns=new ArrayList<>();
			List<String> actualTravelSubViewTableColumns=new ArrayList<>();
			List<String> actualHelperTabTableColumns=new ArrayList<>();
			login.LoginSDI(configProp.getProperty("userId"), configProp.getProperty("Password"));
			System.out.println("Login successfully done");
			// Select RM Dashboard
			sdiMegaObj = new HomePage(driver, test);
			sdiMegaObj.ClickOnReport(ReportName);
			
			sdoFilterObj = new FilterSection(driver, test);
			
			//Validate Filter is working as expected
			
			sdoFilterObj.SelectSite(SiteID, ReportName);
			sdoFilterObj.SelectLOB("Roll Off", ReportName);
			sdoFilterObj.SelectLOB("Residential", ReportName);
			sdoFilterObj.SelectLOB("Commercial", ReportName);	
			
			sdoFilterObj.SelectSubLobs("Commercial Frontload", ReportName);
			sdoFilterObj.SelectRouteManagers("Brandt, Robert",ReportName);
			sdoFilterObj.SelectRouteType("FC - Front Load Commercial", ReportName);
			sdoFilterObj.SelectDrivers(Driver, ReportName);
			sdoFilterObj.SelectDate(FromDate, ToDate, ReportName);
			
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
			//sdoFilterObj.selectLOB(LOB);
			//sdoFilterObj.selectRoute(Route);
			//sdoFilterObj.selectDriver(Driver);
			sdoFilterObj.selectFromDate(FromDate);
			sdoFilterObj.selectToDate(ToDate);
			sdoFilterObj.selectGO();
			effPerformanceObj = new EfficiencyPerformanceReport(driver, test);
			Util.pageScroll(0, 250);
			
			//Validate Summary column Names of the efficiency performance report
			actualSummaryTableColumns = Util.getColumnNames("dtEfficiencySummary");
			Util.validateColumns(actualSummaryTableColumns, GlobalExpectedColumns.efficiencyPerformanceSummaryColumns, "Efficiency Performance Summary");
			
			//Validate Performance subView column Names of the efficiency performance report
			actualPerformacneSubViewTableColumns = Util.getColumnNames("t2");
			Util.validateColumns(actualPerformacneSubViewTableColumns, GlobalExpectedColumns.efficiencyPerformanceSubViewColumns, "Efficiency Performance Sub View");

			//Validate Export Functionality Working Fine
			Util.validateExportToExcelColumns("actExportXLS", GlobalExpectedColumns.excelExportColumnsEfficiencyPerformanceReport, downloadFilePath, 19); //Parameters ID of export excel button, List of columns and download file path

			UIDataList=Util.getUIColumnData(ReportName,"Detail","Date","All");
			Util.validateExportExcelData(downloadFilePath, ReportName, "Date", 18, UIDataList);
			
			UIDataList=Util.getUIColumnData(ReportName,"Detail","Rte","All");
			Util.validateExportExcelData(downloadFilePath, ReportName, "Route", 18, UIDataList);
			
			UIDataList=Util.getUIColumnData(ReportName,"Detail","Driver","All");
			Util.validateExportExcelData(downloadFilePath, ReportName, "Driver", 18, UIDataList);
			
			UIDataList=Util.getUIColumnData(ReportName,"Detail","TruckId","All");
			Util.validateExportExcelData(downloadFilePath, ReportName, "Truck ID", 18, UIDataList);
			
			UIDataList=Util.getUIColumnData(ReportName,"Detail","PlanEff","All");
			Util.validateExportExcelData(downloadFilePath, ReportName, "Drv Plan Eff", 18, UIDataList);
			
			UIDataList=Util.getUIColumnData(ReportName,"Detail","DrvActEff","All");
			Util.validateExportExcelData(downloadFilePath, ReportName, "Drv Actual Eff", 18, UIDataList);
			
			UIDataList=Util.getUIColumnData(ReportName,"Detail","DrvEffVar","All");
			Util.validateExportExcelData(downloadFilePath, ReportName, "Drv Eff Var", 18, UIDataList);
			
			UIDataList=Util.getUIColumnData(ReportName,"Detail","Drv/HlpEff","All");
			Util.validateExportExcelData(downloadFilePath, ReportName, "Drv/Hlp Eff", 18, UIDataList);
			
			UIDataList=Util.getUIColumnData(ReportName,"Detail","PlanUnits","All");
			Util.validateExportExcelData(downloadFilePath, ReportName, "Plan Units", 18, UIDataList);
			
			UIDataList=Util.getUIColumnData(ReportName,"Detail","ActUnits","All");
			Util.validateExportExcelData(downloadFilePath, ReportName, "Actual Units", 18, UIDataList);
			
			UIDataList=Util.getUIColumnData(ReportName,"Detail","UnitsVar","All");
			Util.validateExportExcelData(downloadFilePath, ReportName, "Units Var", 18, UIDataList);
			
			UIDataList=Util.getUIColumnData(ReportName,"Detail","AdjPlanDrvrHrs","All");
			Util.validateExportExcelData(downloadFilePath, ReportName, "Plan Driver Hours (h:m)", 18, UIDataList);
			
			UIDataList=Util.getUIColumnData(ReportName,"Detail","ActDrvrHrs","All");
			Util.validateExportExcelData(downloadFilePath, ReportName, "Actual Driver Hours (h:m)", 18, UIDataList);
			
			UIDataList=Util.getUIColumnData(ReportName,"Detail","HoursVariance","All");
			Util.validateExportExcelData(downloadFilePath, ReportName, "Hours Variance (h:m)", 18, UIDataList);		
			
			UIDataList=Util.getUIColumnData(ReportName,"Detail","HelperHrs","All");
			Util.validateExportExcelData(downloadFilePath, ReportName, "Helper Hours (h:m)", 18, UIDataList);
			
			UIDataList=Util.getUIColumnData(ReportName,"Detail","InSeqPct","All");
			Util.validateExportExcelData(downloadFilePath, ReportName, "In Seq %", 18, UIDataList);
			
			
			UIDataList=Util.getUIColumnData(ReportName,"Detail","SubLOB","All");
			Util.validateExportExcelData(downloadFilePath, ReportName, "Sub LOB", 18, UIDataList);
			
			UIDataList=Util.getUIColumnData(ReportName,"Detail","RteManager","All");
			Util.validateExportExcelData(downloadFilePath, ReportName, "Route Manager ", 18, UIDataList);
			
			
			
			Util.validateSortingFunctionality(ReportName,"Performance","Date", "Ascending","String","All");
			Util.validateSortingFunctionality(ReportName,"Performance","Date", "Descending","String","All");
			
			Util.validateSortingFunctionality(ReportName,"Performance","Rte", "Ascending","String","All");
			Util.validateSortingFunctionality(ReportName,"Performance","Rte", "Descending","String","All");
			
			Util.validateSortingFunctionality(ReportName,"Performance","Driver", "Ascending","String","All");
			Util.validateSortingFunctionality(ReportName,"Performance","Driver", "Descending","String","All");
			
			Util.validateSortingFunctionality(ReportName,"Performance","TruckId", "Ascending","String","All");
			Util.validateSortingFunctionality(ReportName,"Performance","TruckId", "Descending","String","All");
			
			Util.validateSortingFunctionality(ReportName,"Performance","PlanEff", "Ascending","Double","All");
			Util.validateSortingFunctionality(ReportName,"Performance","PlanEff", "Descending","Double","All");
			
			Util.validateSortingFunctionality(ReportName,"Performance","DrvActEff", "Ascending","Double","All");
			Util.validateSortingFunctionality(ReportName,"Performance","DrvActEff", "Descending","Double","All");
			
			Util.validateSortingFunctionality(ReportName,"Performance","DrvEffVar", "Ascending","Double","All");
			Util.validateSortingFunctionality(ReportName,"Performance","DrvEffVar", "Descending","Double","All");
			
			Util.validateSortingFunctionality(ReportName,"Performance","Drv/HlpEff", "Ascending","Double","All");
			Util.validateSortingFunctionality(ReportName,"Performance","Drv/HlpEff", "Descending","Double","All");
			
			Util.validateSortingFunctionality(ReportName,"Performance","PlanUnits", "Ascending","Double","All");
			Util.validateSortingFunctionality(ReportName,"Performance","PlanUnits", "Descending","Double","All");
			
			Util.validateSortingFunctionality(ReportName,"Performance","ActUnits", "Ascending","Double","All");
			Util.validateSortingFunctionality(ReportName,"Performance","ActUnits", "Descending","Double","All");
			
			Util.validateSortingFunctionality(ReportName,"Performance","UnitsVar", "Ascending","Double","All");
			Util.validateSortingFunctionality(ReportName,"Performance","UnitsVar", "Descending","Double","All");
			
			Util.validateSortingFunctionality(ReportName,"Performance","AdjPlanDrvrHrs", "Ascending","Time","All");
			Util.validateSortingFunctionality(ReportName,"Performance","AdjPlanDrvrHrs", "Descending","Time","All");
			
			Util.validateSortingFunctionality(ReportName,"Performance","ActDrvrHrs", "Ascending","Time","All");
			Util.validateSortingFunctionality(ReportName,"Performance","ActDrvrHrs", "Descending","Time","All");
			
			Util.validateSortingFunctionality(ReportName,"Performance","HoursVariance", "Ascending","Time","All");
			Util.validateSortingFunctionality(ReportName,"Performance","HoursVariance", "Descending","Time","All");
			
			Util.validateSortingFunctionality(ReportName,"Performance","HelperHrs", "Ascending","Time","All");
			Util.validateSortingFunctionality(ReportName,"Performance","HelperHrs", "Descending","Time","All");
			
			Util.validateSortingFunctionality(ReportName,"Performance","InSeqPct", "Ascending","Double","All");
			Util.validateSortingFunctionality(ReportName,"Performance","InSeqPct", "Descending","Double","All");
			
			Util.validateSortingFunctionality(ReportName,"Performance","SubLOB", "Ascending","String","All");
			Util.validateSortingFunctionality(ReportName,"Performance","SubLOB", "Descending","String","All");
			
			Util.validateSortingFunctionality(ReportName,"Performance","RteManager", "Ascending","String","All");
			Util.validateSortingFunctionality(ReportName,"Performance","RteManager", "Descending","String","All");

			effPerformanceObj.selectRouteSegmentsSubView();
			Thread.sleep(3000);
			
			//Validate Route Segments subView column Names of the efficiency performance report
			actualRouteSegmentsSubViewTableColumns = Util.getColumnNames("t2");
			Util.validateColumns(actualRouteSegmentsSubViewTableColumns, GlobalExpectedColumns.efficiencyRouteSegmentsSubViewColumns, "Efficiency Performance Route Segments Sub View");
			
			//Before starting export to excel validation ensure UI data is sorted by Date in Ascending
			Util.validateSortingFunctionality(ReportName,"Route Segments","Date", "Ascending","String","All");
			
			UIDataList=Util.getUIColumnData(ReportName,"Detail","ClockIn","All");
			Util.validateExportExcelData(downloadFilePath, ReportName, "Clock In", 18, UIDataList);
			
			UIDataList=Util.getUIColumnData(ReportName,"Detail","ClockOut","All");
			Util.validateExportExcelData(downloadFilePath, ReportName, "Clock Out", 18, UIDataList);
			
			UIDataList=Util.getUIColumnData(ReportName,"Detail","PreRteTm","All");
			Util.validateExportExcelData(downloadFilePath, ReportName, "Pre-Route (h:m)", 18, UIDataList);
			
			UIDataList=Util.getUIColumnData(ReportName,"Detail","PostRteTm","All");
			Util.validateExportExcelData(downloadFilePath, ReportName, "Post-Route (h:m)", 18, UIDataList);
			
			UIDataList=Util.getUIColumnData(ReportName,"Detail","DwnTm","All");
			Util.validateExportExcelData(downloadFilePath, ReportName, "Downtime (h:m)", 18, UIDataList);
			
			UIDataList=Util.getUIColumnData(ReportName,"Detail","NetIdleTm","All");
			Util.validateExportExcelData(downloadFilePath, ReportName, "Net Idle (h:m)", 18, UIDataList);
			
			UIDataList=Util.getUIColumnData(ReportName,"Detail","IdleOcc","All");
			Util.validateExportExcelData(downloadFilePath, ReportName, "Idle Occ", 18, UIDataList);
			
			UIDataList=Util.getUIColumnData(ReportName,"Detail","MealTm","All");
			Util.validateExportExcelData(downloadFilePath, ReportName, "Meal (h:m)", 18, UIDataList);
			
			Util.validateSortingFunctionality(ReportName,"Route Segments","Date", "Descending","String","All");
			
			Util.validateSortingFunctionality(ReportName,"Route Segments","Rte", "Ascending","String","All");
			Util.validateSortingFunctionality(ReportName,"Route Segments","Rte", "Descending","String","All");
			
			Util.validateSortingFunctionality(ReportName,"Route Segments","Driver", "Ascending","String","All");
			Util.validateSortingFunctionality(ReportName,"Route Segments","Driver", "Descending","String","All");
			
			Util.validateSortingFunctionality(ReportName,"Route Segments","TruckId", "Ascending","String","All");
			Util.validateSortingFunctionality(ReportName,"Route Segments","TruckId", "Descending","String","All");
			
			Util.validateSortingFunctionality(ReportName,"Route Segments","DrvEffVar", "Ascending","Double","All");
			Util.validateSortingFunctionality(ReportName,"Route Segments","DrvEffVar", "Descending","Double","All");
		
			Util.validateSortingFunctionality(ReportName,"Route Segments","ClockIn", "Ascending","Time","All");
			Util.validateSortingFunctionality(ReportName,"Route Segments","ClockIn", "Descending","Time","All");
			
			Util.validateSortingFunctionality(ReportName,"Route Segments","ClockOut", "Ascending","Time","All");
			Util.validateSortingFunctionality(ReportName,"Route Segments","ClockOut", "Descending","Time","All");
			
			Util.validateSortingFunctionality(ReportName,"Route Segments","PreRteTm", "Ascending","Time","All");
			Util.validateSortingFunctionality(ReportName,"Route Segments","PreRteTm", "Descending","Time","All");
			
			Util.validateSortingFunctionality(ReportName,"Route Segments","PostRteTm", "Ascending","Time","All");
			Util.validateSortingFunctionality(ReportName,"Route Segments","PostRteTm", "Descending","Time","All");
			
			Util.validateSortingFunctionality(ReportName,"Route Segments","DwnTm", "Ascending","Time","All");
			Util.validateSortingFunctionality(ReportName,"Route Segments","DwnTm", "Descending","Time","All");
			
			Util.validateSortingFunctionality(ReportName,"Route Segments","NetIdleTm", "Ascending","Time","All");
			Util.validateSortingFunctionality(ReportName,"Route Segments","NetIdleTm", "Descending","Time","All");
			
			Util.validateSortingFunctionality(ReportName,"Route Segments","IdleOcc", "Ascending","Double","All");
			Util.validateSortingFunctionality(ReportName,"Route Segments","IdleOcc", "Descending","Double","All");
			
			Util.validateSortingFunctionality(ReportName,"Route Segments","MealTm", "Ascending","Time","All");
			Util.validateSortingFunctionality(ReportName,"Route Segments","MealTm", "Descending","Time","All");
			
			Util.validateSortingFunctionality(ReportName,"Route Segments","DispTm", "Ascending","Time","All");
			Util.validateSortingFunctionality(ReportName,"Route Segments","DispTm", "Descending","Time","All");
			
			Util.validateSortingFunctionality(ReportName,"Route Segments","AvgDispTm", "Ascending","Time","All");
			Util.validateSortingFunctionality(ReportName,"Route Segments","AvgDispTm", "Descending","Time","All");
			
			Util.validateSortingFunctionality(ReportName,"Route Segments","SubLOB", "Ascending","String","All");
			Util.validateSortingFunctionality(ReportName,"Route Segments","SubLOB", "Descending","String","All");
			
			Util.validateSortingFunctionality(ReportName,"Route Segments","RteManager", "Ascending","String","All");
			Util.validateSortingFunctionality(ReportName,"Route Segments","RteManager", "Descending","String","All");
	
			effPerformanceObj.selectDisposalLoadsSubView();
			Thread.sleep(3000);
			
			//Validate Disposal Loads subView column Names of the efficiency performance report
			actualDisposalLoadsSubViewTableColumns = Util.getColumnNames("t2");
			Util.validateColumns(actualDisposalLoadsSubViewTableColumns, GlobalExpectedColumns.efficiencyDisposalLoadsSubViewColumns, "Efficiency Performance Disposal Loads Sub View");
			
			Util.validateSortingFunctionality(ReportName,"Disposal Loads","Date", "Ascending","String","All");
			
			UIDataList=Util.getUIColumnData(ReportName,"Detail","DispTm","All");
			Util.validateExportExcelData(downloadFilePath, ReportName, "Disp Cycle (h:m)", 18, UIDataList);
			
			UIDataList=Util.getUIColumnData(ReportName,"Detail","AvgDispTm","All");
			Util.validateExportExcelData(downloadFilePath, ReportName, "Avg Disp Cycle (h:m)", 18, UIDataList);
			
			UIDataList=Util.getUIColumnData(ReportName,"Detail","PlanDispLoad","All");
			Util.validateExportExcelData(downloadFilePath, ReportName, "Plan Disp Load", 18, UIDataList);
			
			UIDataList=Util.getUIColumnData(ReportName,"Detail","ActualDispLoad","All");
			Util.validateExportExcelData(downloadFilePath, ReportName, "Act Disp Load", 18, UIDataList);
			
			UIDataList=Util.getUIColumnData(ReportName,"Detail","TotalTons","All");
			Util.validateExportExcelData(downloadFilePath, ReportName, "Total Tons", 18, UIDataList);
			
			UIDataList=Util.getUIColumnData(ReportName,"Detail","TonsPerLoad","All");
			Util.validateExportExcelData(downloadFilePath, ReportName, "Tons/Load", 18, UIDataList);			
			
			Util.validateSortingFunctionality(ReportName,"Disposal Loads","Date", "Descending","String","All");
			
			Util.validateSortingFunctionality(ReportName,"Disposal Loads","Rte", "Ascending","String","All");
			Util.validateSortingFunctionality(ReportName,"Disposal Loads","Rte", "Descending","String","All");
			
			Util.validateSortingFunctionality(ReportName,"Disposal Loads","Driver", "Ascending","String","All");
			Util.validateSortingFunctionality(ReportName,"Disposal Loads","Driver", "Descending","String","All");
			
			Util.validateSortingFunctionality(ReportName,"Disposal Loads","TruckId", "Ascending","String","All");
			Util.validateSortingFunctionality(ReportName,"Disposal Loads","TruckId", "Descending","String","All");
			
			Util.validateSortingFunctionality(ReportName,"Disposal Loads","DrvEffVar", "Ascending","Double","All");
			Util.validateSortingFunctionality(ReportName,"Disposal Loads","DrvEffVar", "Descending","Double","All");
			
			Util.validateSortingFunctionality(ReportName,"Disposal Loads","PlanUnits", "Ascending","Double","All");
			Util.validateSortingFunctionality(ReportName,"Disposal Loads","PlanUnits", "Descending","Double","All");
			
			Util.validateSortingFunctionality(ReportName,"Disposal Loads","ActUnits", "Ascending","Double","All");
			Util.validateSortingFunctionality(ReportName,"Disposal Loads","ActUnits", "Descending","Double","All");
			
			Util.validateSortingFunctionality(ReportName,"Disposal Loads","UnitsVar", "Ascending","Double","All");
			Util.validateSortingFunctionality(ReportName,"Disposal Loads","UnitsVar", "Descending","Double","All");
			
			Util.validateSortingFunctionality(ReportName,"Disposal Loads","DispTm", "Ascending","Time","All");
			Util.validateSortingFunctionality(ReportName,"Disposal Loads","DispTm", "Descending","Time","All");
			
			Util.validateSortingFunctionality(ReportName,"Disposal Loads","AvgDispTm", "Ascending","Time","All");
			Util.validateSortingFunctionality(ReportName,"Disposal Loads","AvgDispTm", "Descending","Time","All");
			
			Util.validateSortingFunctionality(ReportName,"Disposal Loads","PlanDispLoad", "Ascending","Double","All");
			Util.validateSortingFunctionality(ReportName,"Disposal Loads","PlanDispLoad", "Descending","Double","All");
			
			Util.validateSortingFunctionality(ReportName,"Disposal Loads","ActualDispLoad", "Ascending","Double","All");
			Util.validateSortingFunctionality(ReportName,"Disposal Loads","ActualDispLoad", "Descending","Double","All");
			
			Util.validateSortingFunctionality(ReportName,"Disposal Loads","TotalTons", "Ascending","Double","All");
			Util.validateSortingFunctionality(ReportName,"Disposal Loads","TotalTons", "Descending","Double","All");
			
			Util.validateSortingFunctionality(ReportName,"Disposal Loads","TonsPerLoad", "Ascending","Double","All");
			Util.validateSortingFunctionality(ReportName,"Disposal Loads","TonsPerLoad", "Descending","Double","All");
			
			Util.validateSortingFunctionality(ReportName,"Disposal Loads","PercentLoadUtiliz", "Ascending","Double","All");
			Util.validateSortingFunctionality(ReportName,"Disposal Loads","PercentLoadUtiliz", "Descending","Double","All");
			
			Util.validateSortingFunctionality(ReportName,"Disposal Loads","SubLOB", "Ascending","String","All");
			Util.validateSortingFunctionality(ReportName,"Disposal Loads","SubLOB", "Descending","String","All");
			
			Util.validateSortingFunctionality(ReportName,"Disposal Loads","RteManager", "Ascending","String","All");
			Util.validateSortingFunctionality(ReportName,"Disposal Loads","RteManager", "Descending","String","All");
						
			effPerformanceObj.selectTravelUnitsSubView();
			Thread.sleep(3000);
			
			//Validate Travel Units subView column Names of the efficiency performance report
			actualTravelSubViewTableColumns = Util.getColumnNames("t2");
			Util.validateColumns(actualTravelSubViewTableColumns, GlobalExpectedColumns.efficiencyTravelSubViewColumns, "Efficiency Performance Travel Sub View");

			Util.validateSortingFunctionality(ReportName,"Travel","Date", "Ascending","String","All");
			
			UIDataList=Util.getUIColumnData(ReportName,"Detail","Stops","All");
			Util.validateExportExcelData(downloadFilePath, ReportName, "Stops", 18, UIDataList);
			
			UIDataList=Util.getUIColumnData(ReportName,"Detail","Lifts","All");
			Util.validateExportExcelData(downloadFilePath, ReportName, "Lifts", 18, UIDataList);

			UIDataList=Util.getUIColumnData(ReportName,"Detail","ActualDistance","All");
			Util.validateExportExcelData(downloadFilePath, ReportName, "Actual Distance", 18, UIDataList);
			
			UIDataList=Util.getUIColumnData(ReportName,"Detail","Fuel","All");
			Util.validateExportExcelData(downloadFilePath, ReportName, "Fuel", 18, UIDataList);			
			
			Util.validateSortingFunctionality(ReportName,"Travel","Date", "Descending","String","All");
			
			Util.validateSortingFunctionality(ReportName,"Travel","Rte", "Ascending","String","All");
			Util.validateSortingFunctionality(ReportName,"Travel","Rte", "Descending","String","All");
			
			Util.validateSortingFunctionality(ReportName,"Travel","Driver", "Ascending","String","All");
			Util.validateSortingFunctionality(ReportName,"Travel","Driver", "Descending","String","All");
			
			Util.validateSortingFunctionality(ReportName,"Travel","TruckId", "Ascending","String","All");
			Util.validateSortingFunctionality(ReportName,"Travel","TruckId", "Descending","String","All");
			
			Util.validateSortingFunctionality(ReportName,"Travel","PlanUnits", "Ascending","Double","All");
			Util.validateSortingFunctionality(ReportName,"Travel","PlanUnits", "Descending","Double","All");
			
			Util.validateSortingFunctionality(ReportName,"Travel","ActUnits", "Ascending","Double","All");
			Util.validateSortingFunctionality(ReportName,"Travel","ActUnits", "Descending","Double","All");
			
			Util.validateSortingFunctionality(ReportName,"Travel","Stops", "Ascending","Double","All");
			Util.validateSortingFunctionality(ReportName,"Travel","Stops", "Descending","Double","All");
			
			Util.validateSortingFunctionality(ReportName,"Travel","Lifts", "Ascending","Double","All");
			Util.validateSortingFunctionality(ReportName,"Travel","Lifts", "Descending","Double","All");
			
			Util.validateSortingFunctionality(ReportName,"Travel","ActualDistance", "Ascending","Double","All");
			Util.validateSortingFunctionality(ReportName,"Travel","ActualDistance", "Descending","Double","All");
			
			Util.validateSortingFunctionality(ReportName,"Travel","UnitsPerDistance", "Ascending","Double","All");
			Util.validateSortingFunctionality(ReportName,"Travel","UnitsPerDistance", "Descending","Double","All");
			
			Util.validateSortingFunctionality(ReportName,"Travel","LiftsPerDistance", "Ascending","Double","All");
			Util.validateSortingFunctionality(ReportName,"Travel","LiftsPerDistance", "Descending","Double","All");
			
			Util.validateSortingFunctionality(ReportName,"Travel","TotalTons", "Ascending","Double","All");
			Util.validateSortingFunctionality(ReportName,"Travel","TotalTons", "Descending","Double","All");
			
			Util.validateSortingFunctionality(ReportName,"Travel","Fuel", "Ascending","Double","All");
			Util.validateSortingFunctionality(ReportName,"Travel","Fuel", "Descending","Double","All");
			
			Util.validateSortingFunctionality(ReportName,"Travel","SubLOB", "Ascending","String","All");
			Util.validateSortingFunctionality(ReportName,"Travel","SubLOB", "Descending","String","All");
			
			Util.validateSortingFunctionality(ReportName,"Travel","RteManager", "Ascending","String","All");
			Util.validateSortingFunctionality(ReportName,"Travel","RteManager", "Descending","String","All");

			effPerformanceObj.selectHelperTab();
			Thread.sleep(3000);
			
			//Validate Helper column Names of the efficiency performance report
			actualHelperTabTableColumns = Util.getColumnNames("t2h");
			Util.validateColumns(actualHelperTabTableColumns, GlobalExpectedColumns.efficiencyHelperTabColumns, "Efficiency Performance Helper Tab");
			
			Util.validateSortingFunctionality(ReportName,"Helper Tab","Date", "Ascending","String","Helper");
			
			UIDataList=Util.getUIColumnData(ReportName,"Detail","Helper","Helper");
			Util.validateExportExcelData(downloadFilePath, ReportName, "Helper Name", 18, UIDataList);	
			
			UIDataList=Util.getUIColumnData(ReportName,"Detail","Site","Helper");
			Util.validateExportExcelData(downloadFilePath, ReportName, "Site Name", 18, UIDataList);
			
			
			Util.validateSortingFunctionality(ReportName,"Helper Tab","Date", "Descending","String","Helper");
	
			Util.validateSortingFunctionality(ReportName,"Helper Tab","Site", "Ascending","String","Helper");
			Util.validateSortingFunctionality(ReportName,"Helper Tab","Site", "Descending","String","Helper");
			
			Util.validateSortingFunctionality(ReportName,"Helper Tab","Rte", "Ascending","String","Helper");
			Util.validateSortingFunctionality(ReportName,"Helper Tab","Rte", "Descending","String","Helper");
			
			Util.validateSortingFunctionality(ReportName,"Helper Tab","Driver", "Ascending","String","Helper");
			Util.validateSortingFunctionality(ReportName,"Helper Tab","Driver", "Descending","String","Helper");
			
			Util.validateSortingFunctionality(ReportName,"Helper Tab","Helper", "Ascending","String","Helper");
			Util.validateSortingFunctionality(ReportName,"Helper Tab","Helper", "Descending","String","Helper");
			
			Util.validateSortingFunctionality(ReportName,"Helper Tab","HelperHrs", "Ascending","Time","Helper");
			Util.validateSortingFunctionality(ReportName,"Helper Tab","HelperHrs", "Descending","Time","Helper");
			
			Util.validateSortingFunctionality(ReportName,"Helper Tab","LOB", "Ascending","String","Helper");
			Util.validateSortingFunctionality(ReportName,"Helper Tab","LOB", "Descending","String","Helper");
			
			Util.validateSortingFunctionality(ReportName,"Helper Tab","SubLOB", "Ascending","String","Helper");
			Util.validateSortingFunctionality(ReportName,"Helper Tab","SubLOB", "Descending","String","Helper");
			
			Util.validateSortingFunctionality(ReportName,"Helper Tab","SignIn", "Ascending","Time","Helper");
			Util.validateSortingFunctionality(ReportName,"Helper Tab","SignIn", "Descending","Time","Helper");
			
			Util.validateSortingFunctionality(ReportName,"Helper Tab","SignOff", "Ascending","Time","Helper");
			Util.validateSortingFunctionality(ReportName,"Helper Tab","SignOff", "Descending","Time","Helper");
			
			Util.validateSortingFunctionality(ReportName,"Helper Tab","RteType", "Ascending","String","Helper");
			Util.validateSortingFunctionality(ReportName,"Helper Tab","RteType", "Descending","String","Helper");
			
			Util.validateSortingFunctionality(ReportName,"Helper Tab","RteManager", "Ascending","String","Helper");
			Util.validateSortingFunctionality(ReportName,"Helper Tab","RteManager", "Descending","String","Helper");
		
			sdoFilterObj.selectLOB("Residential");
			sdoFilterObj.selectGO();
			
			//All validation for Residential LOB
			
			//Validate Summary column Names of the efficiency performance report
			actualSummaryTableColumns = Util.getColumnNames("dtEfficiencySummary");
			Util.validateColumns(actualSummaryTableColumns, GlobalExpectedColumns.efficiencyPerformanceSummaryColumns, "Efficiency Performance Summary");
			
			//Validate Performance subView column Names of the efficiency performance report
			actualPerformacneSubViewTableColumns = Util.getColumnNames("t2");
			Util.validateColumns(actualPerformacneSubViewTableColumns, GlobalExpectedColumns.efficiencyPerformanceSubViewColumns, "Efficiency Performance Sub View");

			//Validate Export Functionality Working Fine
			Util.validateExportToExcelColumns("actExportXLS", GlobalExpectedColumns.excelExportColumnsEfficiencyPerformanceReport, downloadFilePath, 19); //Parameters ID of export excel button, List of columns and download file path

			UIDataList=Util.getUIColumnData(ReportName,"Detail","Date","All");
			Util.validateExportExcelData(downloadFilePath, ReportName, "Date", 18, UIDataList);
			
			UIDataList=Util.getUIColumnData(ReportName,"Detail","Rte","All");
			Util.validateExportExcelData(downloadFilePath, ReportName, "Route", 18, UIDataList);
			
			UIDataList=Util.getUIColumnData(ReportName,"Detail","Driver","All");
			Util.validateExportExcelData(downloadFilePath, ReportName, "Driver", 18, UIDataList);
			
			UIDataList=Util.getUIColumnData(ReportName,"Detail","TruckId","All");
			Util.validateExportExcelData(downloadFilePath, ReportName, "Truck ID", 18, UIDataList);
			
			UIDataList=Util.getUIColumnData(ReportName,"Detail","PlanEff","All");
			Util.validateExportExcelData(downloadFilePath, ReportName, "Drv Plan Eff", 18, UIDataList);
			
			UIDataList=Util.getUIColumnData(ReportName,"Detail","DrvActEff","All");
			Util.validateExportExcelData(downloadFilePath, ReportName, "Drv Actual Eff", 18, UIDataList);
			
			UIDataList=Util.getUIColumnData(ReportName,"Detail","DrvEffVar","All");
			Util.validateExportExcelData(downloadFilePath, ReportName, "Drv Eff Var", 18, UIDataList);
			
			UIDataList=Util.getUIColumnData(ReportName,"Detail","Drv/HlpEff","All");
			Util.validateExportExcelData(downloadFilePath, ReportName, "Drv/Hlp Eff", 18, UIDataList);
			
			UIDataList=Util.getUIColumnData(ReportName,"Detail","PlanUnits","All");
			Util.validateExportExcelData(downloadFilePath, ReportName, "Plan Units", 18, UIDataList);
			
			UIDataList=Util.getUIColumnData(ReportName,"Detail","ActUnits","All");
			Util.validateExportExcelData(downloadFilePath, ReportName, "Actual Units", 18, UIDataList);
			
			UIDataList=Util.getUIColumnData(ReportName,"Detail","UnitsVar","All");
			Util.validateExportExcelData(downloadFilePath, ReportName, "Units Var", 18, UIDataList);
			
			UIDataList=Util.getUIColumnData(ReportName,"Detail","AdjPlanDrvrHrs","All");
			Util.validateExportExcelData(downloadFilePath, ReportName, "Plan Driver Hours (h:m)", 18, UIDataList);
			
			UIDataList=Util.getUIColumnData(ReportName,"Detail","ActDrvrHrs","All");
			Util.validateExportExcelData(downloadFilePath, ReportName, "Actual Driver Hours (h:m)", 18, UIDataList);
			
			UIDataList=Util.getUIColumnData(ReportName,"Detail","HoursVariance","All");
			Util.validateExportExcelData(downloadFilePath, ReportName, "Hours Variance (h:m)", 18, UIDataList);		
			
			UIDataList=Util.getUIColumnData(ReportName,"Detail","HelperHrs","All");
			Util.validateExportExcelData(downloadFilePath, ReportName, "Helper Hours (h:m)", 18, UIDataList);
			
			UIDataList=Util.getUIColumnData(ReportName,"Detail","InSeqPct","All");
			Util.validateExportExcelData(downloadFilePath, ReportName, "In Seq %", 18, UIDataList);
			
			
			UIDataList=Util.getUIColumnData(ReportName,"Detail","SubLOB","All");
			Util.validateExportExcelData(downloadFilePath, ReportName, "Sub LOB", 18, UIDataList);
			
			UIDataList=Util.getUIColumnData(ReportName,"Detail","RteManager","All");
			Util.validateExportExcelData(downloadFilePath, ReportName, "Route Manager ", 18, UIDataList);
			
			Util.validateSortingFunctionality(ReportName,"Performance","Date", "Ascending","String","All");
			Util.validateSortingFunctionality(ReportName,"Performance","Date", "Descending","String","All");
			
			Util.validateSortingFunctionality(ReportName,"Performance","Rte", "Ascending","String","All");
			Util.validateSortingFunctionality(ReportName,"Performance","Rte", "Descending","String","All");
			
			Util.validateSortingFunctionality(ReportName,"Performance","Driver", "Ascending","String","All");
			Util.validateSortingFunctionality(ReportName,"Performance","Driver", "Descending","String","All");
			
			Util.validateSortingFunctionality(ReportName,"Performance","TruckId", "Ascending","String","All");
			Util.validateSortingFunctionality(ReportName,"Performance","TruckId", "Descending","String","All");
			
			Util.validateSortingFunctionality(ReportName,"Performance","PlanEff", "Ascending","Double","All");
			Util.validateSortingFunctionality(ReportName,"Performance","PlanEff", "Descending","Double","All");
			
			Util.validateSortingFunctionality(ReportName,"Performance","DrvActEff", "Ascending","Double","All");
			Util.validateSortingFunctionality(ReportName,"Performance","DrvActEff", "Descending","Double","All");
			
			Util.validateSortingFunctionality(ReportName,"Performance","DrvEffVar", "Ascending","Double","All");
			Util.validateSortingFunctionality(ReportName,"Performance","DrvEffVar", "Descending","Double","All");
			
			Util.validateSortingFunctionality(ReportName,"Performance","Drv/HlpEff", "Ascending","Double","All");
			Util.validateSortingFunctionality(ReportName,"Performance","Drv/HlpEff", "Descending","Double","All");
			
			Util.validateSortingFunctionality(ReportName,"Performance","PlanUnits", "Ascending","Double","All");
			Util.validateSortingFunctionality(ReportName,"Performance","PlanUnits", "Descending","Double","All");
			
			Util.validateSortingFunctionality(ReportName,"Performance","ActUnits", "Ascending","Double","All");
			Util.validateSortingFunctionality(ReportName,"Performance","ActUnits", "Descending","Double","All");
			
			Util.validateSortingFunctionality(ReportName,"Performance","UnitsVar", "Ascending","Double","All");
			Util.validateSortingFunctionality(ReportName,"Performance","UnitsVar", "Descending","Double","All");
			
			Util.validateSortingFunctionality(ReportName,"Performance","AdjPlanDrvrHrs", "Ascending","Time","All");
			Util.validateSortingFunctionality(ReportName,"Performance","AdjPlanDrvrHrs", "Descending","Time","All");
			
			Util.validateSortingFunctionality(ReportName,"Performance","ActDrvrHrs", "Ascending","Time","All");
			Util.validateSortingFunctionality(ReportName,"Performance","ActDrvrHrs", "Descending","Time","All");
			
			Util.validateSortingFunctionality(ReportName,"Performance","HoursVariance", "Ascending","Time","All");
			Util.validateSortingFunctionality(ReportName,"Performance","HoursVariance", "Descending","Time","All");
			
			Util.validateSortingFunctionality(ReportName,"Performance","HelperHrs", "Ascending","Time","All");
			Util.validateSortingFunctionality(ReportName,"Performance","HelperHrs", "Descending","Time","All");
			
			Util.validateSortingFunctionality(ReportName,"Performance","InSeqPct", "Ascending","Double","All");
			Util.validateSortingFunctionality(ReportName,"Performance","InSeqPct", "Descending","Double","All");
			
			Util.validateSortingFunctionality(ReportName,"Performance","SubLOB", "Ascending","String","All");
			Util.validateSortingFunctionality(ReportName,"Performance","SubLOB", "Descending","String","All");
			
			Util.validateSortingFunctionality(ReportName,"Performance","RteManager", "Ascending","String","All");
			Util.validateSortingFunctionality(ReportName,"Performance","RteManager", "Descending","String","All");

			effPerformanceObj.selectRouteSegmentsSubView();
			Thread.sleep(3000);
			
			//Validate Route Segments subView column Names of the efficiency performance report
			actualRouteSegmentsSubViewTableColumns = Util.getColumnNames("t2");
			Util.validateColumns(actualRouteSegmentsSubViewTableColumns, GlobalExpectedColumns.efficiencyRouteSegmentsSubViewColumns, "Efficiency Performance Route Segments Sub View");
			
			Util.validateSortingFunctionality(ReportName,"Route Segments","Date", "Ascending","String","All");
			
			UIDataList=Util.getUIColumnData(ReportName,"Detail","ClockIn","All");
			Util.validateExportExcelData(downloadFilePath, ReportName, "Clock In", 18, UIDataList);
			
			UIDataList=Util.getUIColumnData(ReportName,"Detail","ClockOut","All");
			Util.validateExportExcelData(downloadFilePath, ReportName, "Clock Out", 18, UIDataList);
			
			UIDataList=Util.getUIColumnData(ReportName,"Detail","PreRteTm","All");
			Util.validateExportExcelData(downloadFilePath, ReportName, "Pre-Route (h:m)", 18, UIDataList);
			
			UIDataList=Util.getUIColumnData(ReportName,"Detail","PostRteTm","All");
			Util.validateExportExcelData(downloadFilePath, ReportName, "Post-Route (h:m)", 18, UIDataList);
			
			UIDataList=Util.getUIColumnData(ReportName,"Detail","DwnTm","All");
			Util.validateExportExcelData(downloadFilePath, ReportName, "Downtime (h:m)", 18, UIDataList);
			
			UIDataList=Util.getUIColumnData(ReportName,"Detail","NetIdleTm","All");
			Util.validateExportExcelData(downloadFilePath, ReportName, "Net Idle (h:m)", 18, UIDataList);
			
			UIDataList=Util.getUIColumnData(ReportName,"Detail","IdleOcc","All");
			Util.validateExportExcelData(downloadFilePath, ReportName, "Idle Occ", 18, UIDataList);
			
			UIDataList=Util.getUIColumnData(ReportName,"Detail","MealTm","All");
			Util.validateExportExcelData(downloadFilePath, ReportName, "Meal (h:m)", 18, UIDataList);
			
			
			
			
			Util.validateSortingFunctionality(ReportName,"Route Segments","Date", "Descending","String","All");
			
			Util.validateSortingFunctionality(ReportName,"Route Segments","Rte", "Ascending","String","All");
			Util.validateSortingFunctionality(ReportName,"Route Segments","Rte", "Descending","String","All");
			
			Util.validateSortingFunctionality(ReportName,"Route Segments","Driver", "Ascending","String","All");
			Util.validateSortingFunctionality(ReportName,"Route Segments","Driver", "Descending","String","All");
			
			Util.validateSortingFunctionality(ReportName,"Route Segments","TruckId", "Ascending","String","All");
			Util.validateSortingFunctionality(ReportName,"Route Segments","TruckId", "Descending","String","All");
			
			Util.validateSortingFunctionality(ReportName,"Route Segments","DrvEffVar", "Ascending","Double","All");
			Util.validateSortingFunctionality(ReportName,"Route Segments","DrvEffVar", "Descending","Double","All");
		
			Util.validateSortingFunctionality(ReportName,"Route Segments","ClockIn", "Ascending","Time","All");
			Util.validateSortingFunctionality(ReportName,"Route Segments","ClockIn", "Descending","Time","All");
			
			Util.validateSortingFunctionality(ReportName,"Route Segments","ClockOut", "Ascending","Time","All");
			Util.validateSortingFunctionality(ReportName,"Route Segments","ClockOut", "Descending","Time","All");
			
			Util.validateSortingFunctionality(ReportName,"Route Segments","PreRteTm", "Ascending","Time","All");
			Util.validateSortingFunctionality(ReportName,"Route Segments","PreRteTm", "Descending","Time","All");
			
			Util.validateSortingFunctionality(ReportName,"Route Segments","PostRteTm", "Ascending","Time","All");
			Util.validateSortingFunctionality(ReportName,"Route Segments","PostRteTm", "Descending","Time","All");
			
			Util.validateSortingFunctionality(ReportName,"Route Segments","DwnTm", "Ascending","Time","All");
			Util.validateSortingFunctionality(ReportName,"Route Segments","DwnTm", "Descending","Time","All");
			
			Util.validateSortingFunctionality(ReportName,"Route Segments","NetIdleTm", "Ascending","Time","All");
			Util.validateSortingFunctionality(ReportName,"Route Segments","NetIdleTm", "Descending","Time","All");
			
			Util.validateSortingFunctionality(ReportName,"Route Segments","IdleOcc", "Ascending","Double","All");
			Util.validateSortingFunctionality(ReportName,"Route Segments","IdleOcc", "Descending","Double","All");
			
			Util.validateSortingFunctionality(ReportName,"Route Segments","MealTm", "Ascending","Time","All");
			Util.validateSortingFunctionality(ReportName,"Route Segments","MealTm", "Descending","Time","All");
			
			Util.validateSortingFunctionality(ReportName,"Route Segments","DispTm", "Ascending","Time","All");
			Util.validateSortingFunctionality(ReportName,"Route Segments","DispTm", "Descending","Time","All");
			
			Util.validateSortingFunctionality(ReportName,"Route Segments","AvgDispTm", "Ascending","Time","All");
			Util.validateSortingFunctionality(ReportName,"Route Segments","AvgDispTm", "Descending","Time","All");
			
			Util.validateSortingFunctionality(ReportName,"Route Segments","SubLOB", "Ascending","String","All");
			Util.validateSortingFunctionality(ReportName,"Route Segments","SubLOB", "Descending","String","All");
			
			Util.validateSortingFunctionality(ReportName,"Route Segments","RteManager", "Ascending","String","All");
			Util.validateSortingFunctionality(ReportName,"Route Segments","RteManager", "Descending","String","All");
	
			effPerformanceObj.selectDisposalLoadsSubView();
			Thread.sleep(3000);
			
			//Validate Disposal Loads subView column Names of the efficiency performance report
			actualDisposalLoadsSubViewTableColumns = Util.getColumnNames("t2");
			Util.validateColumns(actualDisposalLoadsSubViewTableColumns, GlobalExpectedColumns.efficiencyDisposalLoadsSubViewColumns, "Efficiency Performance Disposal Loads Sub View");
			
			Util.validateSortingFunctionality(ReportName,"Disposal Loads","Date", "Ascending","String","All");
			
			UIDataList=Util.getUIColumnData(ReportName,"Detail","DispTm","All");
			Util.validateExportExcelData(downloadFilePath, ReportName, "Disp Cycle (h:m)", 18, UIDataList);
			
			UIDataList=Util.getUIColumnData(ReportName,"Detail","AvgDispTm","All");
			Util.validateExportExcelData(downloadFilePath, ReportName, "Avg Disp Cycle (h:m)", 18, UIDataList);
			
			UIDataList=Util.getUIColumnData(ReportName,"Detail","PlanDispLoad","All");
			Util.validateExportExcelData(downloadFilePath, ReportName, "Plan Disp Load", 18, UIDataList);
			
			UIDataList=Util.getUIColumnData(ReportName,"Detail","ActualDispLoad","All");
			Util.validateExportExcelData(downloadFilePath, ReportName, "Act Disp Load", 18, UIDataList);
			
			UIDataList=Util.getUIColumnData(ReportName,"Detail","TotalTons","All");
			Util.validateExportExcelData(downloadFilePath, ReportName, "Total Tons", 18, UIDataList);
			
			UIDataList=Util.getUIColumnData(ReportName,"Detail","TonsPerLoad","All");
			Util.validateExportExcelData(downloadFilePath, ReportName, "Tons/Load", 18, UIDataList);
			
			
			Util.validateSortingFunctionality(ReportName,"Disposal Loads","Date", "Descending","String","All");
			
			Util.validateSortingFunctionality(ReportName,"Disposal Loads","Rte", "Ascending","String","All");
			Util.validateSortingFunctionality(ReportName,"Disposal Loads","Rte", "Descending","String","All");
			
			Util.validateSortingFunctionality(ReportName,"Disposal Loads","Driver", "Ascending","String","All");
			Util.validateSortingFunctionality(ReportName,"Disposal Loads","Driver", "Descending","String","All");
			
			Util.validateSortingFunctionality(ReportName,"Disposal Loads","TruckId", "Ascending","String","All");
			Util.validateSortingFunctionality(ReportName,"Disposal Loads","TruckId", "Descending","String","All");
			
			Util.validateSortingFunctionality(ReportName,"Disposal Loads","DrvEffVar", "Ascending","Double","All");
			Util.validateSortingFunctionality(ReportName,"Disposal Loads","DrvEffVar", "Descending","Double","All");
			
			Util.validateSortingFunctionality(ReportName,"Disposal Loads","PlanUnits", "Ascending","Double","All");
			Util.validateSortingFunctionality(ReportName,"Disposal Loads","PlanUnits", "Descending","Double","All");
			
			Util.validateSortingFunctionality(ReportName,"Disposal Loads","ActUnits", "Ascending","Double","All");
			Util.validateSortingFunctionality(ReportName,"Disposal Loads","ActUnits", "Descending","Double","All");
			
			Util.validateSortingFunctionality(ReportName,"Disposal Loads","UnitsVar", "Ascending","Double","All");
			Util.validateSortingFunctionality(ReportName,"Disposal Loads","UnitsVar", "Descending","Double","All");
			
			Util.validateSortingFunctionality(ReportName,"Disposal Loads","DispTm", "Ascending","Time","All");
			Util.validateSortingFunctionality(ReportName,"Disposal Loads","DispTm", "Descending","Time","All");
			
			Util.validateSortingFunctionality(ReportName,"Disposal Loads","AvgDispTm", "Ascending","Time","All");
			Util.validateSortingFunctionality(ReportName,"Disposal Loads","AvgDispTm", "Descending","Time","All");
			
			Util.validateSortingFunctionality(ReportName,"Disposal Loads","PlanDispLoad", "Ascending","Double","All");
			Util.validateSortingFunctionality(ReportName,"Disposal Loads","PlanDispLoad", "Descending","Double","All");
			
			Util.validateSortingFunctionality(ReportName,"Disposal Loads","ActualDispLoad", "Ascending","Double","All");
			Util.validateSortingFunctionality(ReportName,"Disposal Loads","ActualDispLoad", "Descending","Double","All");
			
			Util.validateSortingFunctionality(ReportName,"Disposal Loads","TotalTons", "Ascending","Double","All");
			Util.validateSortingFunctionality(ReportName,"Disposal Loads","TotalTons", "Descending","Double","All");
			
			Util.validateSortingFunctionality(ReportName,"Disposal Loads","TonsPerLoad", "Ascending","Double","All");
			Util.validateSortingFunctionality(ReportName,"Disposal Loads","TonsPerLoad", "Descending","Double","All");
			
			Util.validateSortingFunctionality(ReportName,"Disposal Loads","PercentLoadUtiliz", "Ascending","Double","All");
			Util.validateSortingFunctionality(ReportName,"Disposal Loads","PercentLoadUtiliz", "Descending","Double","All");
			
			Util.validateSortingFunctionality(ReportName,"Disposal Loads","SubLOB", "Ascending","String","All");
			Util.validateSortingFunctionality(ReportName,"Disposal Loads","SubLOB", "Descending","String","All");
			
			Util.validateSortingFunctionality(ReportName,"Disposal Loads","RteManager", "Ascending","String","All");
			Util.validateSortingFunctionality(ReportName,"Disposal Loads","RteManager", "Descending","String","All");
						
			effPerformanceObj.selectTravelUnitsSubView();
			Thread.sleep(3000);
			
			//Validate Travel Units subView column Names of the efficiency performance report
			actualTravelSubViewTableColumns = Util.getColumnNames("t2");
			Util.validateColumns(actualTravelSubViewTableColumns, GlobalExpectedColumns.efficiencyTravelSubViewColumns, "Efficiency Performance Travel Sub View");

			Util.validateSortingFunctionality(ReportName,"Travel","Date", "Ascending","String","All");
			
			UIDataList=Util.getUIColumnData(ReportName,"Detail","Stops","All");
			Util.validateExportExcelData(downloadFilePath, ReportName, "Stops", 18, UIDataList);
			
			UIDataList=Util.getUIColumnData(ReportName,"Detail","Lifts","All");
			Util.validateExportExcelData(downloadFilePath, ReportName, "Lifts", 18, UIDataList);

			UIDataList=Util.getUIColumnData(ReportName,"Detail","ActualDistance","All");
			Util.validateExportExcelData(downloadFilePath, ReportName, "Actual Distance", 18, UIDataList);
			
			UIDataList=Util.getUIColumnData(ReportName,"Detail","Fuel","All");
			Util.validateExportExcelData(downloadFilePath, ReportName, "Fuel", 18, UIDataList);		
			
			Util.validateSortingFunctionality(ReportName,"Travel","Date", "Descending","String","All");
			
			Util.validateSortingFunctionality(ReportName,"Travel","Rte", "Ascending","String","All");
			Util.validateSortingFunctionality(ReportName,"Travel","Rte", "Descending","String","All");
			
			Util.validateSortingFunctionality(ReportName,"Travel","Driver", "Ascending","String","All");
			Util.validateSortingFunctionality(ReportName,"Travel","Driver", "Descending","String","All");
			
			Util.validateSortingFunctionality(ReportName,"Travel","TruckId", "Ascending","String","All");
			Util.validateSortingFunctionality(ReportName,"Travel","TruckId", "Descending","String","All");
			
			Util.validateSortingFunctionality(ReportName,"Travel","PlanUnits", "Ascending","Double","All");
			Util.validateSortingFunctionality(ReportName,"Travel","PlanUnits", "Descending","Double","All");
			
			Util.validateSortingFunctionality(ReportName,"Travel","ActUnits", "Ascending","Double","All");
			Util.validateSortingFunctionality(ReportName,"Travel","ActUnits", "Descending","Double","All");
			
			Util.validateSortingFunctionality(ReportName,"Travel","Stops", "Ascending","Double","All");
			Util.validateSortingFunctionality(ReportName,"Travel","Stops", "Descending","Double","All");
			
			Util.validateSortingFunctionality(ReportName,"Travel","Lifts", "Ascending","Double","All");
			Util.validateSortingFunctionality(ReportName,"Travel","Lifts", "Descending","Double","All");
			
			Util.validateSortingFunctionality(ReportName,"Travel","ActualDistance", "Ascending","Double","All");
			Util.validateSortingFunctionality(ReportName,"Travel","ActualDistance", "Descending","Double","All");
			
			Util.validateSortingFunctionality(ReportName,"Travel","UnitsPerDistance", "Ascending","Double","All");
			Util.validateSortingFunctionality(ReportName,"Travel","UnitsPerDistance", "Descending","Double","All");
			
			Util.validateSortingFunctionality(ReportName,"Travel","LiftsPerDistance", "Ascending","Double","All");
			Util.validateSortingFunctionality(ReportName,"Travel","LiftsPerDistance", "Descending","Double","All");
			
			Util.validateSortingFunctionality(ReportName,"Travel","TotalTons", "Ascending","Double","All");
			Util.validateSortingFunctionality(ReportName,"Travel","TotalTons", "Descending","Double","All");
			
			Util.validateSortingFunctionality(ReportName,"Travel","Fuel", "Ascending","Double","All");
			Util.validateSortingFunctionality(ReportName,"Travel","Fuel", "Descending","Double","All");
			
			Util.validateSortingFunctionality(ReportName,"Travel","SubLOB", "Ascending","String","All");
			Util.validateSortingFunctionality(ReportName,"Travel","SubLOB", "Descending","String","All");
			
			Util.validateSortingFunctionality(ReportName,"Travel","RteManager", "Ascending","String","All");
			Util.validateSortingFunctionality(ReportName,"Travel","RteManager", "Descending","String","All");

			effPerformanceObj.selectHelperTab();
			Thread.sleep(3000);
			
			//Validate Helper column Names of the efficiency performance report
			actualHelperTabTableColumns = Util.getColumnNames("t2h");
			Util.validateColumns(actualHelperTabTableColumns, GlobalExpectedColumns.efficiencyHelperTabColumns, "Efficiency Performance Helper Tab");
			
			Util.validateSortingFunctionality(ReportName,"Helper Tab","Date", "Ascending","String","Helper");
			
			UIDataList=Util.getUIColumnData(ReportName,"Detail","Helper","Helper");
			Util.validateExportExcelData(downloadFilePath, ReportName, "Helper Name", 18, UIDataList);	
			
			UIDataList=Util.getUIColumnData(ReportName,"Detail","Site","Helper");
			Util.validateExportExcelData(downloadFilePath, ReportName, "Site Name", 18, UIDataList);
						
			Util.validateSortingFunctionality(ReportName,"Helper Tab","Date", "Descending","String","Helper");
	
			Util.validateSortingFunctionality(ReportName,"Helper Tab","Site", "Ascending","String","Helper");
			Util.validateSortingFunctionality(ReportName,"Helper Tab","Site", "Descending","String","Helper");
			
			Util.validateSortingFunctionality(ReportName,"Helper Tab","Rte", "Ascending","String","Helper");
			Util.validateSortingFunctionality(ReportName,"Helper Tab","Rte", "Descending","String","Helper");
			
			Util.validateSortingFunctionality(ReportName,"Helper Tab","Driver", "Ascending","String","Helper");
			Util.validateSortingFunctionality(ReportName,"Helper Tab","Driver", "Descending","String","Helper");
			
			Util.validateSortingFunctionality(ReportName,"Helper Tab","Helper", "Ascending","String","Helper");
			Util.validateSortingFunctionality(ReportName,"Helper Tab","Helper", "Descending","String","Helper");
			
			Util.validateSortingFunctionality(ReportName,"Helper Tab","HelperHrs", "Ascending","Time","Helper");
			Util.validateSortingFunctionality(ReportName,"Helper Tab","HelperHrs", "Descending","Time","Helper");
			
			Util.validateSortingFunctionality(ReportName,"Helper Tab","LOB", "Ascending","String","Helper");
			Util.validateSortingFunctionality(ReportName,"Helper Tab","LOB", "Descending","String","Helper");
			
			Util.validateSortingFunctionality(ReportName,"Helper Tab","SubLOB", "Ascending","String","Helper");
			Util.validateSortingFunctionality(ReportName,"Helper Tab","SubLOB", "Descending","String","Helper");
			
			Util.validateSortingFunctionality(ReportName,"Helper Tab","SignIn", "Ascending","Time","Helper");
			Util.validateSortingFunctionality(ReportName,"Helper Tab","SignIn", "Descending","Time","Helper");
			
			Util.validateSortingFunctionality(ReportName,"Helper Tab","SignOff", "Ascending","Time","Helper");
			Util.validateSortingFunctionality(ReportName,"Helper Tab","SignOff", "Descending","Time","Helper");
			
			Util.validateSortingFunctionality(ReportName,"Helper Tab","RteType", "Ascending","String","Helper");
			Util.validateSortingFunctionality(ReportName,"Helper Tab","RteType", "Descending","String","Helper");
			
			Util.validateSortingFunctionality(ReportName,"Helper Tab","RteManager", "Ascending","String","Helper");
			Util.validateSortingFunctionality(ReportName,"Helper Tab","RteManager", "Descending","String","Helper");
			
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
