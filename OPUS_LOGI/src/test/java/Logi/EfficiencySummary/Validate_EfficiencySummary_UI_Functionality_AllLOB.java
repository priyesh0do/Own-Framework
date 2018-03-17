package Logi.EfficiencySummary;

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
import ObjectRepository.Logi.EfficiencySummaryReport;
import Opus.BaseClass;
import SupportLibraries.Excel;
import SupportLibraries.GlobalVariables;
import SupportLibraries.Report;
import SupportLibraries.Util;
import TestData.GlobalExpectedColumns;

public class Validate_EfficiencySummary_UI_Functionality_AllLOB extends BaseClass{
	
	Login login;
	HomePage sdiMegaObj;
	FilterSection sdoFilterObj;
	EfficiencySummaryReport effSummaryObj;
	
	@Test(groups = "Opus", dataProvider = "LOGI_Reports_Data")
	public void Validation_EfficiencySummaryReport_UI_Functionality_AllLOB(String Scenario,String RunMode,String ReportName, String SiteID, String LOB,
			String FromDate, String ToDate, String Route,String Driver,String ClockInTime,String ClockOutTime,String Ticket) throws Exception {	
		
		if (!Util.isTestCaseExecutable("Validate_EfficiencySummary_UI_Functionality_AllLOB", GlobalVariables.logiTestCase)) {
			test = Report.testCreate(extent, test, "Validate_EfficiencySummary_UI_Functionality_AllLOB");
			Report.SkipTest(test, "Skipping the test as runmode is NO");
		} else {
		
		if(Scenario.equalsIgnoreCase("Validate_EfficiencySummary_UI_Functionality_AllLOB") & RunMode.equals("Yes"))
		{	
		
		    test = Report.testCreate(extent, test, "Validate_EfficiencySummary_UI_Functionality_AllLOB");
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
				
				
				Util.pageScroll(0, 250);
				
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
				
				sdoFilterObj.selectSite(SiteID);
				sdoFilterObj.selectFromDate(FromDate);
				sdoFilterObj.selectToDate(ToDate);
				sdoFilterObj.selectGO();
				effSummaryObj = new EfficiencySummaryReport(driver, test);
				
			
				
				//Verify Font Color of the Eff Var field Summary Section
				Util.ValidateFontAndColor("Driver Total Eff Var (in hrs) in Summary Section", "dtEfficiencySummary", 1, 2);
				Util.ValidateFontAndColor("Driver Avg Eff Var (in hrs) in Summary Section", "dtEfficiencySummary", 2, 2);
				Util.ValidateFontAndColor("Driver & Helper Total Eff Var (in hrs) in Summary Section", "dtEfficiencySummary", 3, 2);
				Util.ValidateFontAndColor("Driver & Helper Avg Eff Var (in hrs) in Summary Section", "dtEfficiencySummary", 4, 2);
				
				
				//Verify Font Color of the Eff Var field detail Section
				Util.ValidateFontAndColor("Eff Var (in hrs) in Detail Section", "dtEfficiencyDetail", 1, 5);
			
				//Validate Summary column Names of the efficiency summary report
				actualSummaryTableColumns = Util.getColumnNames("dtEfficiencySummary");
				Util.validateColumns(actualSummaryTableColumns, GlobalExpectedColumns.efficiencySummarysummaryColumns, "Efficiency Summary");
				
				//Validate detail table column Names of the efficiency summary report
				actualDetailTableColumns = Util.getColumnNames("dtEfficiencyDetail");
				Util.validateColumns(actualDetailTableColumns, GlobalExpectedColumns.efficiencySummaryDetailColumns, "Efficiency Summary Detail");

				//Validate Export Functionality Working Fine
				Util.validateExportToExcelColumns("actExportXLS", GlobalExpectedColumns.excelExportEfficiencySummaryReport, downloadFilePath,14); //Parameters ID of export excel button, List of columns and download file path

				UIDataList=Util.getUIColumnData(ReportName,"Detail","Grp","Efficiency Summary");
				Util.validateExportExcelData(downloadFilePath, ReportName, "Tier", 13, UIDataList);
				
				UIDataList=Util.getUIColumnData(ReportName,"Detail","Area","Efficiency Summary");
				Util.validateExportExcelData(downloadFilePath, ReportName, "Area", 13, UIDataList);
				
				UIDataList=Util.getUIColumnData(ReportName,"Detail","BU","Efficiency Summary");
				Util.validateExportExcelData(downloadFilePath, ReportName, "BU", 13, UIDataList);
				
				UIDataList=Util.getUIColumnData(ReportName,"Detail","Site","Efficiency Summary");
				Util.validateExportExcelData(downloadFilePath, ReportName, "Site", 13, UIDataList);
				
				UIDataList=Util.getUIColumnData(ReportName,"Detail","EffVar","Efficiency Summary");
				Util.validateExportExcelData(downloadFilePath, ReportName, "Eff Var (in hrs)", 13, UIDataList);
				
				UIDataList=Util.getUIColumnData(ReportName,"Detail","ActualEff","Efficiency Summary");
				Util.validateExportExcelData(downloadFilePath, ReportName, "Actual Eff", 13, UIDataList);
				
				UIDataList=Util.getUIColumnData(ReportName,"Detail","PlanEff","Efficiency Summary");
				Util.validateExportExcelData(downloadFilePath, ReportName, "Plan Eff", 13, UIDataList);
				
				UIDataList=Util.getUIColumnData(ReportName,"Detail","ActualUnits","Efficiency Summary");
				Util.validateExportExcelData(downloadFilePath, ReportName, "Actual Units", 13, UIDataList);
				
				UIDataList=Util.getUIColumnData(ReportName,"Detail","PlanUnits","Efficiency Summary");
				Util.validateExportExcelData(downloadFilePath, ReportName, "Plan Units", 13, UIDataList);
				
				UIDataList=Util.getUIColumnData(ReportName,"Detail","ActualDrHrs","Efficiency Summary");
				Util.validateExportExcelData(downloadFilePath, ReportName, "Actual Driver Hours (h:m)", 13, UIDataList);
				
				UIDataList=Util.getUIColumnData(ReportName,"Detail","PlanDrHrs","Efficiency Summary");
				Util.validateExportExcelData(downloadFilePath, ReportName, "Plan Driver Hours (h:m)", 13, UIDataList);
				
				UIDataList=Util.getUIColumnData(ReportName,"Detail","ActualHlpHrs","Efficiency Summary");
				Util.validateExportExcelData(downloadFilePath, ReportName, "Helper Hours (h:m)", 13, UIDataList);
				
				UIDataList=Util.getUIColumnData(ReportName,"Detail","Miles","Efficiency Summary");
				Util.validateExportExcelData(downloadFilePath, ReportName, "Miles", 13, UIDataList);
				
				UIDataList=Util.getUIColumnData(ReportName,"Detail","UnitsPerMiles","Efficiency Summary");
				Util.validateExportExcelData(downloadFilePath, ReportName, "Units Per Mile", 13, UIDataList);
				
				UIDataList=Util.getUIColumnData(ReportName,"Detail","LiftsPerMiles","Efficiency Summary");
				Util.validateExportExcelData(downloadFilePath, ReportName, "Lifts Per Mile", 13, UIDataList);
				
				UIDataList=Util.getUIColumnData(ReportName,"Detail","MilesPerAction","Efficiency Summary");
				Util.validateExportExcelData(downloadFilePath, ReportName, "Miles Per Eq Haul", 13, UIDataList);
				
				UIDataList=Util.getUIColumnData(ReportName,"Detail","PctSwapOut","Efficiency Summary");
				Util.validateExportExcelData(downloadFilePath, ReportName, "Swap Out % ", 13, UIDataList);
				
				Util.validateSortingFunctionality(ReportName,"Detail","Grp", "Ascending","String","Efficiency Summary");
				Util.validateSortingFunctionality(ReportName,"Detail","Grp", "Descending","String","Efficiency Summary");
				
				Util.validateSortingFunctionality(ReportName,"Detail","Area", "Ascending","String","Efficiency Summary");
				Util.validateSortingFunctionality(ReportName,"Detail","Area", "Descending","String","Efficiency Summary");
				
				Util.validateSortingFunctionality(ReportName,"Detail","BU", "Ascending","String","Efficiency Summary");
				Util.validateSortingFunctionality(ReportName,"Detail","BU", "Descending","String","Efficiency Summary");
				
				Util.validateSortingFunctionality(ReportName,"Detail","Site", "Ascending","String","Efficiency Summary");
				Util.validateSortingFunctionality(ReportName,"Detail","Site", "Descending","String","Efficiency Summary");
				
				Util.validateSortingFunctionality(ReportName,"Detail","EffVar", "Ascending","Double","Efficiency Summary");
				Util.validateSortingFunctionality(ReportName,"Detail","EffVar", "Descending","Double","Efficiency Summary");
				
				Util.validateSortingFunctionality(ReportName,"Detail","ActualEff", "Ascending","Double","Efficiency Summary");
				Util.validateSortingFunctionality(ReportName,"Detail","ActualEff", "Descending","Double","Efficiency Summary");
				
				Util.validateSortingFunctionality(ReportName,"Detail","PlanEff", "Ascending","Double","Efficiency Summary");
				Util.validateSortingFunctionality(ReportName,"Detail","PlanEff", "Descending","Double","Efficiency Summary");
				
				Util.validateSortingFunctionality(ReportName,"Detail","ActualUnits", "Ascending","Double","Efficiency Summary");
				Util.validateSortingFunctionality(ReportName,"Detail","ActualUnits", "Descending","Double","Efficiency Summary");
				
				Util.validateSortingFunctionality(ReportName,"Detail","PlanUnits", "Ascending","Double","Efficiency Summary");
				Util.validateSortingFunctionality(ReportName,"Detail","PlanUnits", "Descending","Double","Efficiency Summary");
				
				Util.validateSortingFunctionality(ReportName,"Detail","ActualDrHrs", "Ascending","Time","Efficiency Summary");
				Util.validateSortingFunctionality(ReportName,"Detail","ActualDrHrs", "Descending","Time","Efficiency Summary");
				
				Util.validateSortingFunctionality(ReportName,"Detail","PlanDrHrs", "Ascending","Time","Efficiency Summary");
				Util.validateSortingFunctionality(ReportName,"Detail","PlanDrHrs", "Descending","Time","Efficiency Summary");
				
				Util.validateSortingFunctionality(ReportName,"Detail","ActualHlpHrs", "Ascending","Time","Efficiency Summary");
				Util.validateSortingFunctionality(ReportName,"Detail","ActualHlpHrs", "Descending","Time","Efficiency Summary");
				
				Util.validateSortingFunctionality(ReportName,"Detail","Miles", "Ascending","Double","Efficiency Summary");
				Util.validateSortingFunctionality(ReportName,"Detail","Miles", "Descending","Double","Efficiency Summary");
				
				Util.validateSortingFunctionality(ReportName,"Detail","UnitsPerMiles", "Ascending","Double","Efficiency Summary");
				Util.validateSortingFunctionality(ReportName,"Detail","UnitsPerMiles", "Descending","Double","Efficiency Summary");
				
				Util.validateSortingFunctionality(ReportName,"Detail","LiftsPerMiles", "Ascending","Double","Efficiency Summary");
				Util.validateSortingFunctionality(ReportName,"Detail","LiftsPerMiles", "Descending","Double","Efficiency Summary");
				
				Util.validateSortingFunctionality(ReportName,"Detail","MilesPerAction", "Ascending","Double","Efficiency Summary");
				Util.validateSortingFunctionality(ReportName,"Detail","MilesPerAction", "Descending","Double","Efficiency Summary");
				
				Util.validateSortingFunctionality(ReportName,"Detail","PctSwapOut", "Ascending","Double","Efficiency Summary");
				Util.validateSortingFunctionality(ReportName,"Detail","PctSwapOut", "Descending","Double","Efficiency Summary");
			

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
