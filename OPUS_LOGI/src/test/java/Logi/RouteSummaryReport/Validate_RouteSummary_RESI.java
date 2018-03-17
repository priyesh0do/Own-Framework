package Logi.RouteSummaryReport;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Driver.BrowserOpen;
import ObjectRepository.General.FilterSection;
import ObjectRepository.General.HomePage;
import ObjectRepository.General.Login;
import ObjectRepository.Logi.EfficiencyPerformanceReport;
import ObjectRepository.Logi.EfficiencySummaryReport;
import ObjectRepository.Logi.RouteSummaryReport;
import ObjectRepository.Logi.SequenceComplianceSummaryReport;
import Opus.BaseClass;
import SupportLibraries.Excel;
import SupportLibraries.Report;
import SupportLibraries.Util;

public class Validate_RouteSummary_RESI extends BaseClass 
{
	Login login;
	HomePage sdiMegaObj;
	FilterSection sdoFilterObj;
	RouteSummaryReport RouteSummaryObj;
	EfficiencyPerformanceReport effPerfObj;
	EfficiencySummaryReport effSumObj;
	SequenceComplianceSummaryReport seqComObj;
	
	@Test(groups = "Opus", dataProvider = "LOGI_Reports_Data")
	public void Validation_RouteSummaryReport_DataValidation_RESI(String Scenario,String RunMode,String ReportName, String SiteID, String LOB,
			String FromDate, String ToDate, String Route,String Driver,String ClockInTime,String ClockOutTime,String Ticket) throws InterruptedException, IOException {
	
	if(Scenario.equalsIgnoreCase("Validate_RouteSummaryReport_RESI") & RunMode.equals("Yes"))
	{
		test = Report.testCreate(extent, test, "Validate_RouteSummaryReport_RESI");
	    driver = BrowserOpen.StartBrowser(driver, configProp.getProperty("Browser"), configProp.getProperty("URL"));
		login = new Login(driver, test);
		login.LoginSDI(configProp.getProperty("userId"), configProp.getProperty("Password"));
		System.out.println("Login successfully done");
		// Select RM Dashboard
		
		sdiMegaObj = new HomePage(driver, test);
		sdiMegaObj.ClickOnReport(ReportName);
		sdoFilterObj = new FilterSection(driver, test);
		effPerfObj = new EfficiencyPerformanceReport(driver, test);
		effSumObj = new EfficiencySummaryReport(driver, test);
		RouteSummaryObj = new RouteSummaryReport(driver, test);
		seqComObj = new SequenceComplianceSummaryReport(driver, test);
		
		sdoFilterObj.selectSite(SiteID);
		sdoFilterObj.selectLOB(LOB);
		sdoFilterObj.selectFromDate(FromDate);
		sdoFilterObj.selectToDate(ToDate);
		sdoFilterObj.selectGO();
				
		Util.pageScroll(0, 250);
		// Switch to frame
		Util.switchToDefaultWindow();
		Util.selectFrame("opusReportContainer,subReportContainer");
		System.out.println("t1");
		Map<String, List<String>> actualDetailTable = RouteSummaryObj.getActualDetailTableData(); 
		Thread.sleep(1000);
		
		//**********************************************************************************************************************************************************
		//Switching to Efficiency Summary Table
		sdoFilterObj.openReportInNewTab("Efficiency Summary");
		sdoFilterObj.selectLOB("Residential");
		//sdoFilterObj.selectSite(SiteID);
		sdoFilterObj.selectFromDate(FromDate);
		sdoFilterObj.selectToDate(ToDate);
		//Enter date range if required
		sdoFilterObj.GoBtn();
		//Reading data from Efficiency Summary Table
		Util.switchToDefaultWindow();
		Util.selectFrame("opusReportContainer,subReportContainer");	
		Map<String,List<String>> efficiencySummaryResiTableData=effSumObj.getActualSummaryTableData();
		driver.close();
		Util.switchToDefaultTab();
		
		//**********************************************************************************************************************************************************
		//Switching to Efficiency Performance Table
		sdoFilterObj.openReportInNewTab("Efficiency Performance");
		sdoFilterObj.selectSite(SiteID);
		sdoFilterObj.selectLOB("Residential");
		sdoFilterObj.selectFromDate(FromDate);
		sdoFilterObj.selectToDate(ToDate);
		//Enter date range if required
		sdoFilterObj.GoBtn();
		//Reading data from Efficiency Summary Table
		Util.switchToDefaultWindow();
		Util.selectFrame("opusReportContainer,subReportContainer");
		Thread.sleep(3000);
		Map<String,List<String>> efficiencyPerformanceResiPerformanceTableData=effPerfObj.getActualPerformanceSubViewTableData();
		//driver.close();
		//Util.switchToDefaultTab();

		//**********************************************************************************************************************************************************
		Util.switchToDefaultWindow();
		Util.selectFrame("opusReportContainer,subReportContainer");
		driver.findElement(By.xpath(".//*[@id='inpSubReportOpt_2']")).click();
		Thread.sleep(3000);
		//Reading data from Efficiency Performance - Route Segment Section
		Map<String,List<String>> efficiencyPerformanceResiRouteSegmentTableData=effPerfObj.getActualRouteSegmentsSubViewTableData();
		driver.close();
		Util.switchToDefaultTab();
		//**********************************************************************************************************************************************************
		//Switching to Efficiency Performance Table
		sdoFilterObj.openReportInNewTab("Sequence Compliance Summary");
		sdoFilterObj.selectLOB("Residential");
		//sdoFilterObj.selectSite(SiteID);
		sdoFilterObj.selectFromDate(FromDate);
		sdoFilterObj.selectToDate(ToDate);
		//Enter date range if required
		sdoFilterObj.GoBtn();
		//Reading data from Efficiency Summary Table
		Util.switchToDefaultWindow();
		Util.selectFrame("opusReportContainer,subReportContainer");
		Thread.sleep(3000);
		Map<String,List<String>> sequenceComplianceResiTableData=seqComObj.getActualSummaryTableData();
		driver.close();
		Util.switchToDefaultTab();
		
		RouteSummaryObj.validateRouteSummaryDetailSection(SiteID,actualDetailTable,efficiencySummaryResiTableData,efficiencyPerformanceResiPerformanceTableData,efficiencyPerformanceResiRouteSegmentTableData,sequenceComplianceResiTableData);
		
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
