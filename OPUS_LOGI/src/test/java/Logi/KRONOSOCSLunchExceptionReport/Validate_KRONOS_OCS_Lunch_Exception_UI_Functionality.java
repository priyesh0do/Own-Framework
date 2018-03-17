package Logi.KRONOSOCSLunchExceptionReport;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Driver.BrowserOpen;
import ObjectRepository.General.FilterSection;
import ObjectRepository.General.HomePage;
import ObjectRepository.General.Login;
import ObjectRepository.Logi.KronosOcsLunchExceptionDetailReport;
import Opus.BaseClass;
import SupportLibraries.Excel;
import SupportLibraries.Report;
import SupportLibraries.Util;
import TestData.GlobalExpectedColumns;

public class Validate_KRONOS_OCS_Lunch_Exception_UI_Functionality extends BaseClass{

	Login login;
	HomePage sdiMegaObj;
	FilterSection sdoFilterObj;
	KronosOcsLunchExceptionDetailReport lunchExceptionDetailObj;
	@Test(groups = "Opus", dataProvider = "LOGI_Reports_Data")
	public void Validation_KRONOS_OCS_Lunch_Exception(String Scenario,String RunMode,String ReportName, String SiteID, String LOB,
			String FromDate, String ToDate, String Route,String Driver,String ClockInTime,String ClockOutTime,String Ticket) throws Exception {	
	
		
			if(Scenario.equalsIgnoreCase("Validate_KRONOS_OCS_Lunch_Exception_UI_Functionality") & RunMode.equals("Yes"))
			{

			    test = Report.testCreate(extent, test, "Validate_KRONOS_OCS_Lunch_Exception_UI_Functionality");
			    driver = BrowserOpen.StartBrowser(driver, configProp.getProperty("Browser"), configProp.getProperty("URL"));
				login = new Login(driver, test);
				login.LoginSDI(configProp.getProperty("userId"), configProp.getProperty("Password"));
				System.out.println("Login successfully done");
					String downloadFilePath=systemDir + "\\downloads";
					List<String> UIDataList=new ArrayList<>();
					List<String> actualDetailTableColumns =new ArrayList<>();
					
					sdiMegaObj = new HomePage(driver, test);
					sdiMegaObj.ClickOnReport(ReportName);
					
					sdoFilterObj = new FilterSection(driver, test);
								
					//Validate Date Picker functionality 
					Util.validateFromAndToDate(ReportName);
						
					Util.pageScroll(0, 200);
				
					Util.validateSortingOfFilterValues("islSiteDFilter");
					Util.validateSortingOfFilterValues("islRMFilter");
					Util.validateSortingOfFilterValues("islDriverFilter");
					
					sdoFilterObj.clickReset();
					sdoFilterObj.selectSite(SiteID);
					sdoFilterObj.selectFromDate(FromDate);
					sdoFilterObj.selectToDate(ToDate);
					sdoFilterObj.selectGO();
					
					lunchExceptionDetailObj=new KronosOcsLunchExceptionDetailReport(driver, test);
					
					//Validate detail table column Names 
					actualDetailTableColumns = Util.getColumnNames("dtKronos");
					Util.validateColumns(actualDetailTableColumns, GlobalExpectedColumns.KronosOcsLunchExceptionColumns, "Kronos Ocs Lunch Exception Detail");
					
					

					//Validate Export Functionality Working Fine
					Util.validateExportToExcelColumns("actExportXLS", GlobalExpectedColumns.excelKronosOcsLunchExceptionColumns, downloadFilePath,12);
					
					UIDataList=Util.getUIColumnData(ReportName,"Detail","Date","dtKronos");
					Util.validateExportExcelData(downloadFilePath, ReportName,"Date",11, UIDataList);
					
					UIDataList=Util.getUIColumnData(ReportName,"Detail","DriverID","dtKronos");
					Util.validateExportExcelData(downloadFilePath, ReportName,"Driver ID",11, UIDataList);
					
					UIDataList=Util.getUIColumnData(ReportName,"Detail","Driver","dtKronos");
					Util.validateExportExcelData(downloadFilePath, ReportName,"Driver",11, UIDataList);
					
					UIDataList=Util.getUIColumnData(ReportName,"Detail","LunchFlag","dtKronos");
					Util.validateExportExcelData(downloadFilePath, ReportName,"KRONOS Auto Lunch Flag",11, UIDataList);
					
					UIDataList=Util.getUIColumnData(ReportName,"Detail","Override","dtKronos");
					Util.validateExportExcelData(downloadFilePath, ReportName,"KRONOS Override Auto Lunch Flag",11, UIDataList);
					
					UIDataList=Util.getUIColumnData(ReportName,"Detail","Lunch","dtKronos");
					Util.validateExportExcelData(downloadFilePath, ReportName,"OCS Actual Lunch (h:m)",11, UIDataList);
					
					UIDataList=Util.getUIColumnData(ReportName,"Detail","RteMgr","dtKronos");
					Util.validateExportExcelData(downloadFilePath, ReportName,"Route Manager",11, UIDataList);
					
					Util.validateSortingFunctionality(ReportName,"Detail","Date", "Ascending","String","dtKronos");
					Util.validateSortingFunctionality(ReportName,"Detail","Date", "Descending","String","dtKronos");
					
					Util.validateSortingFunctionality(ReportName,"Detail","DriverID", "Ascending","String","dtKronos");
					Util.validateSortingFunctionality(ReportName,"Detail","DriverID", "Descending","String","dtKronos");
					
					Util.validateSortingFunctionality(ReportName,"Detail","Driver", "Ascending","String","dtKronos");
					Util.validateSortingFunctionality(ReportName,"Detail","Driver", "Descending","String","dtKronos");
					
					Util.validateSortingFunctionality(ReportName,"Detail","LunchFlag", "Ascending","String","dtKronos");
					Util.validateSortingFunctionality(ReportName,"Detail","LunchFlag", "Descending","String","dtKronos");
					
					Util.validateSortingFunctionality(ReportName,"Detail","Override", "Ascending","String","dtKronos");
					Util.validateSortingFunctionality(ReportName,"Detail","Override", "Descending","String","dtKronos");
					
					Util.validateSortingFunctionality(ReportName,"Detail","Lunch", "Ascending","Time","dtKronos");
					Util.validateSortingFunctionality(ReportName,"Detail","Lunch", "Descending","Time","dtKronos");
					
					Util.validateSortingFunctionality(ReportName,"Detail","RteMgr", "Ascending","String","dtKronos");
					Util.validateSortingFunctionality(ReportName,"Detail","RteMgr", "Descending","String","dtKronos");
					
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
