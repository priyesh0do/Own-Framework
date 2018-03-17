package Logi.IdleSummaryReport;

import java.util.List;
import java.util.Map;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Driver.BrowserOpen;
import ObjectRepository.General.FilterSection;
import ObjectRepository.General.HomePage;
import ObjectRepository.General.Login;
import ObjectRepository.Logi.IdleDetailReport;
import ObjectRepository.Logi.IdleSummaryReport;
import Opus.BaseClass;
import SupportLibraries.Excel;
import SupportLibraries.Report;
import SupportLibraries.Util;

public class Validate_IdleSummary_RO extends BaseClass{
	
	Login login;
	HomePage sdiMegaObj;
	FilterSection sdoFilterObj;
	IdleSummaryReport idleSummaryObj;
	IdleDetailReport idleDetailObj;
	@Test(groups = "Opus", dataProvider = "LOGI_Reports_Data")
	public void Validation_EfficiencySummaryReport_DataValidation_COM(String Scenario,String RunMode,String ReportName, String SiteID, String LOB,
			String FromDate, String ToDate, String Route,String Driver,String ClockInTime,String ClockOutTime,String Ticket) throws Exception {	
		
		if(Scenario.equalsIgnoreCase("Validate_IdleSummaryReport_RO") & RunMode.equals("Yes"))
		{
		
		    test = Report.testCreate(extent, test, "Validate_IdleSummaryReport_RO");
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
			sdoFilterObj.selectMultiLOB(LOB);
			sdoFilterObj.selectSubLOB("Sub LOBs incl B, O");
			sdoFilterObj.selectFromDate(FromDate);
			sdoFilterObj.selectToDate(ToDate);
			sdoFilterObj.selectGO();
			idleSummaryObj = new IdleSummaryReport(driver, test);
			// Switch to frame
			Util.selectFrame("opusReportContainer,subReportContainer");			
			Map<String, List<String>> actualIdleSummaryTable = idleSummaryObj.getActualSummaryTableData();
			Map<String, List<String>> actualIdleSummaryDetailTable = idleSummaryObj.getActualDetailTableData();	
			sdoFilterObj.openReportInNewTab("Idle Detail");
			idleDetailObj = new IdleDetailReport(driver, test);
			sdoFilterObj.selectSite(SiteID);
			sdoFilterObj.selectMultiLOB(LOB);
			sdoFilterObj.selectSubLOB("Sub LOBs incl B, O");
			sdoFilterObj.selectGO();
			Util.pageScroll(0, 150);
			// Switch to frame
			Util.selectFrame("opusReportContainer,subReportContainer");	
			Map<String, List<String>> actualIdleDetailSummaryTable = idleDetailObj.getActualIdleSummaryTableData();
			Thread.sleep(2000);
			Util.switchToDefaultTab();	
			idleSummaryObj.validateIdleSummaryData(actualIdleSummaryTable,actualIdleSummaryDetailTable,LOB);
			idleSummaryObj.validateIdleSummaryDetailData(actualIdleSummaryDetailTable,actualIdleDetailSummaryTable,SiteID,LOB);
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
