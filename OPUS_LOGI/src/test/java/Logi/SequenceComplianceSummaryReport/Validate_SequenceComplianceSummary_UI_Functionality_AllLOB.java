package Logi.SequenceComplianceSummaryReport;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Driver.BrowserOpen;
import ObjectRepository.General.FilterSection;
import ObjectRepository.General.GraphSection;
import ObjectRepository.General.HomePage;
import ObjectRepository.General.Login;
import ObjectRepository.Logi.IdleSummaryReport;
import ObjectRepository.Logi.SequenceComplianceSummaryReport;
import Opus.BaseClass;
import SupportLibraries.Excel;
import SupportLibraries.GlobalVariables;
import SupportLibraries.Report;
import SupportLibraries.Util;
import TestData.GlobalExpectedColumns;

public class Validate_SequenceComplianceSummary_UI_Functionality_AllLOB extends BaseClass{
	
	Login login;
	HomePage sdiMegaObj;
	FilterSection sdoFilterObj;
	SequenceComplianceSummaryReport seqCompSummaryObj;
	GraphSection graphObj;
	@Test(groups = "Opus", dataProvider = "LOGI_Reports_Data")
	public void Validation_SequenceComplianceSummaryReport_UI_Functionality_AllLOB(String Scenario,String RunMode,String ReportName, String SiteID, String LOB,
			String FromDate, String ToDate, String Route,String Driver,String ClockInTime,String ClockOutTime,String Ticket) throws Exception {	

		
		if(Scenario.equalsIgnoreCase("Validate_SequenceComplianceSummary_UI_Functionality_AllLOB") & RunMode.equals("Yes"))
		{	
		
		    test = Report.testCreate(extent, test, "Validate_SequenceComplianceSummary_UI_Functionality_AllLOB");
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
				graphObj=new GraphSection(driver, test);
				sdoFilterObj = new FilterSection(driver, test);
				
				
				//Validate Date Picker functionality 
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
				sdoFilterObj.selectGO();
				seqCompSummaryObj = new SequenceComplianceSummaryReport(driver, test);

				//Validate Summary column Names of the Sequence Compliance summayr report
				actualSummaryTableColumns = Util.getColumnNames("dtSeqComplianceSummary");
				Util.validateColumns(actualSummaryTableColumns, GlobalExpectedColumns.SequenceComplianceSummarysummaryColumns, "Sequence Compliance Summary summary");
				
				//Validate detail table column Names of the Sequence Compliance summary report
				actualDetailTableColumns = Util.getColumnNames("dtSeqComplianceDetail");
				Util.validateColumns(actualDetailTableColumns, GlobalExpectedColumns.SequenceComplianceSummaryDetailColumns, "Sequence Compliance Summary Detail Section");

				//Validate Export Functionality Working Fine
				Util.validateExportToExcelColumns("actExportXLS", GlobalExpectedColumns.excelExportColumnsSequenceComplianceSummaryReport, downloadFilePath,14); //Parameters ID of export excel button, List of columns and download file path
				
				UIDataList=Util.getUIColumnData(ReportName,"Detail","Grp","Sequence Compliance Summary");
				Util.validateExportExcelData(downloadFilePath, ReportName, "Tier", 13, UIDataList);
				
				UIDataList=Util.getUIColumnData(ReportName,"Detail","Area","Sequence Compliance Summary");
				Util.validateExportExcelData(downloadFilePath, ReportName, "Area", 13, UIDataList);
				
				UIDataList=Util.getUIColumnData(ReportName,"Detail","BU","Sequence Compliance Summary");
				Util.validateExportExcelData(downloadFilePath, ReportName, "BU", 13, UIDataList);
				
				UIDataList=Util.getUIColumnData(ReportName,"Detail","Site","Sequence Compliance Summary");
				Util.validateExportExcelData(downloadFilePath, ReportName, "Site", 13, UIDataList);
				
				UIDataList=Util.getUIColumnData(ReportName,"Detail","PctOutofSeq","Sequence Compliance Summary");
				Util.validateExportExcelData(downloadFilePath, ReportName, "Out of Seq %", 13, UIDataList);
				
				UIDataList=Util.getUIColumnData(ReportName,"Detail","NumStpOutofSeq","Sequence Compliance Summary");
				Util.validateExportExcelData(downloadFilePath, ReportName, "# of Stops Out of Seq", 13, UIDataList);
				
				UIDataList=Util.getUIColumnData(ReportName,"Detail","PctInSeq","Sequence Compliance Summary");
				Util.validateExportExcelData(downloadFilePath, ReportName, "In Seq %", 13, UIDataList);
				
				UIDataList=Util.getUIColumnData(ReportName,"Detail","NumStpInSeq","Sequence Compliance Summary");
				Util.validateExportExcelData(downloadFilePath, ReportName, "# of Stops In Seq", 13, UIDataList);
				
				UIDataList=Util.getUIColumnData(ReportName,"Detail","TotStp","Sequence Compliance Summary");
				Util.validateExportExcelData(downloadFilePath, ReportName, "Total Stops", 13, UIDataList);
				
				UIDataList=Util.getUIColumnData(ReportName,"Detail","TotMASStpWOSeq","Sequence Compliance Summary");
				Util.validateExportExcelData(downloadFilePath, ReportName, "Total MAS Stops w/o Seq #", 13, UIDataList);
				
				
				Util.pageScroll(0, 250);
				
				Util.validateSortingFunctionality(ReportName,"Detail","Grp", "Ascending","String","Sequence Compliance Summary");
				Util.validateSortingFunctionality(ReportName,"Detail","Grp", "Descending","String","Sequence Compliance Summary");
				
				Util.validateSortingFunctionality(ReportName,"Detail","Area", "Ascending","String","Sequence Compliance Summary");
				Util.validateSortingFunctionality(ReportName,"Detail","Area", "Descending","String","Sequence Compliance Summary");
				
				Util.validateSortingFunctionality(ReportName,"Detail","BU", "Ascending","String","Sequence Compliance Summary");
				Util.validateSortingFunctionality(ReportName,"Detail","BU", "Descending","String","Sequence Compliance Summary");
				
				Util.validateSortingFunctionality(ReportName,"Detail","Site", "Ascending","String","Sequence Compliance Summary");
				Util.validateSortingFunctionality(ReportName,"Detail","Site", "Descending","String","Sequence Compliance Summary");
				
				Util.validateSortingFunctionality(ReportName,"Detail","PctOutofSeq", "Ascending","Double","Sequence Compliance Summary");
				Util.validateSortingFunctionality(ReportName,"Detail","PctOutofSeq", "Descending","Double","Sequence Compliance Summary");
				
				Util.validateSortingFunctionality(ReportName,"Detail","NumStpOutofSeq", "Ascending","Double","Sequence Compliance Summary");
				Util.validateSortingFunctionality(ReportName,"Detail","NumStpOutofSeq", "Descending","Double","Sequence Compliance Summary");
				
				Util.validateSortingFunctionality(ReportName,"Detail","PctInSeq", "Ascending","Double","Sequence Compliance Summary");
				Util.validateSortingFunctionality(ReportName,"Detail","PctInSeq", "Descending","Double","Sequence Compliance Summary");
				
				Util.validateSortingFunctionality(ReportName,"Detail","NumStpInSeq", "Ascending","Double","Sequence Compliance Summary");
				Util.validateSortingFunctionality(ReportName,"Detail","NumStpInSeq", "Descending","Double","Sequence Compliance Summary");
				
				Util.validateSortingFunctionality(ReportName,"Detail","TotStp", "Ascending","Double","Sequence Compliance Summary");
				Util.validateSortingFunctionality(ReportName,"Detail","TotStp", "Descending","Double","Sequence Compliance Summary");
				
				Util.validateSortingFunctionality(ReportName,"Detail","TotMASStpWOSeq", "Ascending","Double","Sequence Compliance Summary");
				Util.validateSortingFunctionality(ReportName,"Detail","TotMASStpWOSeq", "Descending","Double","Sequence Compliance Summary");
					
				seqCompSummaryObj.ValidatePopUp(SiteID, FromDate, ToDate, ReportName, downloadFilePath);
				
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
