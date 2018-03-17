package Logi.ServiceExceptionsSummaryReport;

import java.util.List;
import java.util.Map;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Driver.BrowserOpen;
import ObjectRepository.General.FilterSection;
import ObjectRepository.General.HomePage;
import ObjectRepository.General.Login;
import ObjectRepository.Logi.SequenceComplianceDetailReport;
import ObjectRepository.Logi.SequenceComplianceSummaryReport;
import ObjectRepository.Logi.ServiceExceptionsDetailReport;
import ObjectRepository.Logi.ServiceExceptionsSummaryReport;
import Opus.BaseClass;
import SupportLibraries.Excel;
import SupportLibraries.Report;
import SupportLibraries.Util;

public class Validate_ServiceExceptionsSummaryReport_COM extends BaseClass{
	
	Login login;
	HomePage sdiMegaObj;
	FilterSection sdoFilterObj;
	ServiceExceptionsSummaryReport serviceExceptionsSummaryObj;
	ServiceExceptionsDetailReport serviceExceptionsDetailObj;
	@Test(groups = "Opus", dataProvider = "LOGI_Reports_Data")
	public void Validation_ServiceExceptionsSummaryReport_DataValidation_COM(String Scenario,String RunMode,String ReportName, String SiteID, String LOB,
			String FromDate, String ToDate, String Route,String Driver,String ClockInTime,String ClockOutTime,String Ticket) throws Exception {	
		
		if(Scenario.equalsIgnoreCase("Validate_ServiceExceptionsSummaryReport_COM") & RunMode.equals("Yes"))
		{
		
		    test = Report.testCreate(extent, test, "Validate_ServiceExceptionsSummaryReport_COM");
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
			sdoFilterObj.selectFromDate(FromDate);
			sdoFilterObj.selectToDate(ToDate);
			sdoFilterObj.selectGO();
			serviceExceptionsSummaryObj = new ServiceExceptionsSummaryReport(driver, test);
			Util.pageScroll(0, 150);
			// Switch to frame
			Util.selectFrame("opusReportContainer,subReportContainer");			
			Map<String, List<String>> actualServiceExceptionsSummaryTable = serviceExceptionsSummaryObj.getActualSummaryTableData();
			Map<String, List<String>> actualServiceExceptionsSummaryDetailTable = serviceExceptionsSummaryObj.getActualDetailTableData();	
			sdoFilterObj.openReportInNewTab("Service Exceptions Detail");
			serviceExceptionsDetailObj = new ServiceExceptionsDetailReport(driver, test);
			sdoFilterObj.selectSite(SiteID);
			sdoFilterObj.selectLOB(LOB);
			sdoFilterObj.selectGO();
			Util.pageScroll(0, 150);
			// Switch to frame
			Util.selectFrame("opusReportContainer,subReportContainer");	
			Map<String, List<String>> actualServiceExceptionsDetailSummaryTable = serviceExceptionsDetailObj.getActualServiceExceptionsSummaryTableData();
			Thread.sleep(2000);
			Util.switchToDefaultTab();	
			serviceExceptionsSummaryObj.validateServiceExceptionsSummaryData(actualServiceExceptionsSummaryTable,actualServiceExceptionsSummaryDetailTable,LOB);
			serviceExceptionsSummaryObj.validateServiceExceptionsSummaryDetailData(actualServiceExceptionsSummaryDetailTable,actualServiceExceptionsDetailSummaryTable,SiteID,LOB);
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
