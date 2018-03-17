package Logi.DisposalCycleSummaryReport;

import java.util.List;
import java.util.Map;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Driver.BrowserOpen;
import ObjectRepository.General.FilterSection;
import ObjectRepository.General.HomePage;
import ObjectRepository.General.Login;
import ObjectRepository.Logi.DisposalCycleDetailReport;
import ObjectRepository.Logi.DisposalCycleSummaryReport;
import Opus.BaseClass;
import SupportLibraries.Excel;
import SupportLibraries.Report;
import SupportLibraries.Util;

public class Validate_DisposalCycleSummary_COM extends BaseClass{
	
	Login login;
	HomePage sdiMegaObj;
	FilterSection sdoFilterObj;
	DisposalCycleSummaryReport disposalCycleSummaryObj;
	DisposalCycleDetailReport disposalCycleDetailObj;
	@Test(groups = "Opus", dataProvider = "LOGI_Reports_Data")
	public void Validation_DisposalCycleSummaryReport_DataValidation_COM(String Scenario,String RunMode,String ReportName, String SiteID, String LOB,
			String FromDate, String ToDate, String Route,String Driver,String ClockInTime,String ClockOutTime,String Ticket) throws Exception {	
		
		if(Scenario.equalsIgnoreCase("Validate_DisposalCycleSummaryReport_COM") & RunMode.equals("Yes"))
		{
		
		    test = Report.testCreate(extent, test, "Validate_DisposalCycleSummaryReport_COM");
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
			sdoFilterObj.selectFromDate(FromDate);
			sdoFilterObj.selectToDate(ToDate);
			sdoFilterObj.selectGO();
			disposalCycleSummaryObj = new DisposalCycleSummaryReport(driver, test);
					
			Map<String, List<String>> actualDisposalCycleSummaryTable = disposalCycleSummaryObj.getActualSummaryTableData();
			Map<String, List<String>> actualDisposalCycleSummaryDetailTable = disposalCycleSummaryObj.getActualDetailTableData();	
			sdoFilterObj.openReportInNewTab("Disposal Cycle Detail");
			disposalCycleDetailObj = new DisposalCycleDetailReport(driver, test);
			sdoFilterObj.selectSite(SiteID);
			sdoFilterObj.selectMultiLOB(LOB);
			sdoFilterObj.selectGO();
			Util.pageScroll(0, 150);
			
			Map<String, List<String>> actualDisposalCycleDetailSummaryTable = disposalCycleDetailObj.getActualDisposalCycleSummaryTableData();
			Thread.sleep(2000);
			Util.switchToDefaultTab();	
			disposalCycleSummaryObj.validateDisposalCycleSummaryData(actualDisposalCycleSummaryTable,actualDisposalCycleSummaryDetailTable,LOB);
			disposalCycleSummaryObj.validateDisposalCycleSummaryDetailData(actualDisposalCycleSummaryDetailTable,actualDisposalCycleDetailSummaryTable,SiteID,LOB);
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
