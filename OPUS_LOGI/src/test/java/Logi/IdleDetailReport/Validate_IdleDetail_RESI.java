package Logi.IdleDetailReport;

import java.util.List;
import java.util.Map;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Driver.BrowserOpen;
import ObjectRepository.General.FilterSection;
import ObjectRepository.General.HomePage;
import ObjectRepository.General.Login;
import ObjectRepository.Logi.IdleDetailReport;
import Opus.BaseClass;
import SupportLibraries.Excel;
import SupportLibraries.Report;
import SupportLibraries.Util;

public class Validate_IdleDetail_RESI extends BaseClass{
	
	Login login;
	HomePage sdiMegaObj;
	FilterSection sdoFilterObj;
	IdleDetailReport idleDetailObj;
	@Test(groups = "Opus", dataProvider = "LOGI_Reports_Data")
	public void Validation_IdleDetailReport_Resi(String Scenario,String RunMode,String ReportName, String SiteID, String LOB,
			String FromDate, String ToDate, String Route,String Driver,String ClockInTime,String ClockOutTime,String Ticket) throws Exception {	
		
		if(Scenario.equalsIgnoreCase("Validate_IdleDetailReport_RESI") & RunMode.equals("Yes"))
		{
		
		    test = Report.testCreate(extent, test, "Validate_IdleDetailReport_RESI");
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
			sdoFilterObj.selectRoute(Route);
			sdoFilterObj.selectDriver(Driver);
			sdoFilterObj.selectFromDate(FromDate);
			sdoFilterObj.selectToDate(ToDate);
			sdoFilterObj.selectGO();
			idleDetailObj = new IdleDetailReport(driver, test);
			Util.pageScroll(0, 250);
			// Switch to frame
			Util.selectFrame("opusReportContainer,subReportContainer");			
			Map<String, List<String>> actualSummaryTable = idleDetailObj.getActualIdleSummaryTableData();	
			Map<String, List<String>> actualDetailTable = idleDetailObj.getActualIdleDetailTableData();
			Thread.sleep(1000);
			Map<String,Map<String, List<String>>> actualNetIdleDrilldownTable =idleDetailObj.getActualNetIdleDrilldownTableData(Route);
			idleDetailObj.validateSummaryData(actualSummaryTable,actualDetailTable);
			idleDetailObj.validateDetailData(actualDetailTable,actualNetIdleDrilldownTable,SiteID,LOB, Route, Driver, FromDate, ToDate);
			idleDetailObj.validateNetIdleDrilldownData(actualDetailTable,actualNetIdleDrilldownTable);
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
