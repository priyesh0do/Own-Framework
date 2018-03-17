package Logi.SalesInformationDetailReport;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Driver.BrowserOpen;
import ObjectRepository.General.FilterSection;
import ObjectRepository.General.HomePage;
import ObjectRepository.General.Login;
import ObjectRepository.Logi.SlaesInformationDetailReport;
import Opus.BaseClass;
import SupportLibraries.Excel;
import SupportLibraries.GlobalVariables;
import SupportLibraries.Report;
import SupportLibraries.Util;
import TestData.GlobalExpectedColumns;

public class Validate_SalesInformationDetail_UI_Functionality_AllLOB extends BaseClass{
	Login login;
	HomePage sdiMegaObj;
	FilterSection sdoFilterObj;
	SlaesInformationDetailReport salesInfoObj;
	@Test(groups = "Opus", dataProvider = "LOGI_Reports_Data")
	public void Validation_SalesInformationDetailReport_UI_Functionality_AllLOB(String Scenario,String RunMode,String ReportName, String SiteID, String LOB,
			String FromDate, String ToDate, String Route,String Driver,String ClockInTime,String ClockOutTime,String Ticket) throws Exception {	
	

			if(Scenario.equalsIgnoreCase("Validate_SalesInformationDetail_UI_Functionality_AllLOB") & RunMode.equals("Yes"))
			{
			
			test = Report.testCreate(extent, test, "Validate_SalesInformationDetail_UI_Functionality_AllLOB");
		    driver = BrowserOpen.StartBrowser(driver, configProp.getProperty("Browser"), configProp.getProperty("URL"));
			login = new Login(driver, test);
			String downloadFilePath=systemDir + "\\downloads";
			List<String> UIDataList=new ArrayList<>();
			List<String> actualDetailTableColumns=new ArrayList<>();
			login.LoginSDI(configProp.getProperty("userId"), configProp.getProperty("Password"));
			System.out.println("Login successfully done");
			// Select RM Dashboard
			sdiMegaObj = new HomePage(driver, test);
			sdiMegaObj.ClickOnReport(ReportName);			
			sdoFilterObj = new FilterSection(driver, test);
			
			// select site
			sdoFilterObj.selectSite(SiteID);
			
			// Validate Filter content is sorted in ascending manner
		/*	Util.validateSortingOfFilterValues("islSiteDFilter");
			Util.validateSortingOfFilterValues("islLOBFilterS");
			Util.validateSortingOfFilterValues("islSubLOBFilter");
			Util.validateSortingOfFilterValues("islRMFilter");
			Util.validateSortingOfFilterValues("islRteTypeFilter");
			Util.validateSortingOfFilterValues("islRteFilter");
			Util.validateSortingOfFilterValues("islDriverFilter");
			Util.validateSortingOfFilterValues("islDrvrCmtFilter");*/
			sdoFilterObj.selectFromDate(FromDate);
			sdoFilterObj.selectToDate(ToDate);
			sdoFilterObj.selectGO();
			salesInfoObj=new SlaesInformationDetailReport(driver, test);
			
			//Validate Detail column Names of the Sales Information Detail report
			actualDetailTableColumns = Util.getColumnNames("dtSalesInfo");
			Util.validateColumns(actualDetailTableColumns, GlobalExpectedColumns.salesInformationDetailColumns, "Sales Information Details");
			
			//Validate Export Functionality Working Fine
			Util.validateExportToExcelColumns("actExportXLS", GlobalExpectedColumns.excelExportColumnsSalesInformationReport, downloadFilePath,18);

			UIDataList=Util.getUIColumnData(ReportName,"Detail","Date","dtSalesInfo");
			Util.validateExportExcelData(downloadFilePath, ReportName, "Date", 17, UIDataList);
			
			UIDataList=Util.getUIColumnData(ReportName,"Detail","Route","dtSalesInfo");
			Util.validateExportExcelData(downloadFilePath, ReportName, "Route", 17, UIDataList);
			
			UIDataList=Util.getUIColumnData(ReportName,"Detail","Driver","dtSalesInfo");
			Util.validateExportExcelData(downloadFilePath, ReportName, "Driver", 17, UIDataList);
			
			UIDataList=Util.getUIColumnData(ReportName,"Detail","CustID","dtSalesInfo");
			Util.validateExportExcelData(downloadFilePath, ReportName, "Cust ID", 17, UIDataList);
			
			UIDataList=Util.getUIColumnData(ReportName,"Detail","CustName","dtSalesInfo");
			Util.validateExportExcelData(downloadFilePath, ReportName, "Cust Name", 17, UIDataList);
			
			UIDataList=Util.getUIColumnData(ReportName,"Detail","Comments","dtSalesInfo");
			Util.validateExportExcelData(downloadFilePath, ReportName, "Driver Comments", 17, UIDataList);
			
			UIDataList=Util.getUIColumnData(ReportName,"Detail","LOB","dtSalesInfo");
			Util.validateExportExcelData(downloadFilePath, ReportName, "LOB", 17, UIDataList);
			
			UIDataList=Util.getUIColumnData(ReportName,"Detail","SubLOB","dtSalesInfo");
			Util.validateExportExcelData(downloadFilePath, ReportName, "Sub LOB", 17, UIDataList);
			
			UIDataList=Util.getUIColumnData(ReportName,"Detail","RteType","dtSalesInfo");
			Util.validateExportExcelData(downloadFilePath, ReportName, "Route Type", 17, UIDataList);
			
			UIDataList=Util.getUIColumnData(ReportName,"Detail","RteMgr","dtSalesInfo");
			Util.validateExportExcelData(downloadFilePath, ReportName, "Route Manager ", 17, UIDataList);
			
			Thread.sleep(2000);
			
			Util.validateSortingFunctionality(ReportName,"Detail","Date", "Ascending","String","Sales Information Detail");
			Util.validateSortingFunctionality(ReportName,"Detail","Date", "Descending","String","Sales Information Detail");
			
			Util.validateSortingFunctionality(ReportName,"Detail","Route", "Ascending","String","Sales Information Detail");
			Util.validateSortingFunctionality(ReportName,"Detail","Route", "Descending","String","Sales Information Detail");
			
			Util.validateSortingFunctionality(ReportName,"Detail","Driver", "Ascending","String","Sales Information Detail");
			Util.validateSortingFunctionality(ReportName,"Detail","Driver", "Descending","String","Sales Information Detail");
			
			Util.validateSortingFunctionality(ReportName,"Detail","CustID", "Ascending","String","Sales Information Detail");
			Util.validateSortingFunctionality(ReportName,"Detail","CustID", "Descending","String","Sales Information Detail");
			
			Util.validateSortingFunctionality(ReportName,"Detail","CustName", "Ascending","String","Sales Information Detail");
			Util.validateSortingFunctionality(ReportName,"Detail","CustName", "Descending","String","Sales Information Detail");
			
			Util.validateSortingFunctionality(ReportName,"Detail","Comments", "Ascending","String","Sales Information Detail");
			Util.validateSortingFunctionality(ReportName,"Detail","Comments", "Descending","String","Sales Information Detail");
			
			Util.validateSortingFunctionality(ReportName,"Detail","LOB", "Ascending","String","Sales Information Detail");
			Util.validateSortingFunctionality(ReportName,"Detail","LOB", "Descending","String","Sales Information Detail");
			
			Util.validateSortingFunctionality(ReportName,"Detail","SubLOB", "Ascending","String","Sales Information Detail");
			Util.validateSortingFunctionality(ReportName,"Detail","SubLOB", "Descending","String","Sales Information Detail");
			
			Util.validateSortingFunctionality(ReportName,"Detail","RteType", "Ascending","String","Sales Information Detail");
			Util.validateSortingFunctionality(ReportName,"Detail","RteType", "Descending","String","Sales Information Detail");
			
			Util.validateSortingFunctionality(ReportName,"Detail","RteMgr", "Ascending","String","Sales Information Detail");
			Util.validateSortingFunctionality(ReportName,"Detail","RteMgr", "Descending","String","Sales Information Detail");
			
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

