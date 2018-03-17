package Logi.ServiceExceptionsDetailReport;

import java.util.List;
import java.util.Map;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Driver.BrowserOpen;
import ObjectRepository.General.FilterSection;
import ObjectRepository.General.HomePage;
import ObjectRepository.General.Login;
import ObjectRepository.Logi.SequenceComplianceDetailReport;
import ObjectRepository.Logi.ServiceExceptionsDetailReport;
import Opus.BaseClass;
import SupportLibraries.Excel;
import SupportLibraries.Report;
import SupportLibraries.Util;

public class Validate_ServiceExceptionsDetail_COM extends BaseClass{
	
	Login login;
	HomePage sdiMegaObj;
	FilterSection sdoFilterObj;
	ServiceExceptionsDetailReport serviceExceptionsDetailObj;
	@Test(groups = "Opus", dataProvider = "LOGI_Reports_Data")
	public void Validation_ServiceExceptionsDetailReport_COM(String Scenario,String RunMode,String ReportName, String SiteID, String LOB,
			String FromDate, String ToDate, String Route,String Driver,String ClockInTime,String ClockOutTime,String Ticket) throws Exception {	
		
		if(Scenario.equalsIgnoreCase("Validate_ServiceExceptionsDetailReport_COM") & RunMode.equals("Yes"))
		{
		
		    test = Report.testCreate(extent, test, "Validate_ServiceExceptionsDetailReport_COM");
		    driver = BrowserOpen.StartBrowser(driver, configProp.getProperty("Browser"), configProp.getProperty("URL"));
			login = new Login(driver, test);
			login.LoginSDI(configProp.getProperty("userId"), configProp.getProperty("Password"));
			System.out.println("Login successfully done");
			// Select RM Dashboard
			sdiMegaObj = new HomePage(driver, test);
			sdiMegaObj.ClickOnReport(ReportName);
			sdoFilterObj = new FilterSection(driver, test);
			// select site
			sdoFilterObj.selectSite(SiteID);
			sdoFilterObj.selectLOB(LOB);
			sdoFilterObj.selectRoute(Route);
			sdoFilterObj.selectDriver(Driver);
			sdoFilterObj.selectFromDate(FromDate);
			sdoFilterObj.selectToDate(ToDate);
			sdoFilterObj.selectGO();
			serviceExceptionsDetailObj = new ServiceExceptionsDetailReport(driver, test);
			Util.pageScroll(0, 200);	
			Map<String, List<String>> actualSummaryTable = serviceExceptionsDetailObj.getActualServiceExceptionsSummaryTableData();	
			Map<String, List<String>> actualAllTabDetailTable = serviceExceptionsDetailObj.getActualServiceExceptionsAllTabDetailTableData();
			Thread.sleep(1000);
			Map<String,Map<String, List<String>>> actualTotalExceptionsDrilldownAllTabTable =serviceExceptionsDetailObj.getActualTotalExceptionsDrilldownAllTabTableData(Route);
			serviceExceptionsDetailObj.validateSummaryData(actualSummaryTable,actualAllTabDetailTable,actualTotalExceptionsDrilldownAllTabTable,LOB);
			serviceExceptionsDetailObj.validateDetailData(actualAllTabDetailTable,actualTotalExceptionsDrilldownAllTabTable,SiteID,LOB, Route, Driver, FromDate, ToDate);
			serviceExceptionsDetailObj.validateTotalExceptionsDrilldownData(actualAllTabDetailTable,actualTotalExceptionsDrilldownAllTabTable);
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
