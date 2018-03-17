package Logi.FlashReportBeta;

import java.util.List;
import java.util.Map;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Driver.BrowserOpen;
import ObjectRepository.General.FilterSection;
import ObjectRepository.General.HomePage;
import ObjectRepository.General.Login;
import ObjectRepository.Logi.FlashReportBeta;
import Opus.BaseClass;
import SupportLibraries.Excel;
import SupportLibraries.Report;
import SupportLibraries.Util;

public class Validate_FlashReportBeta_COM extends BaseClass{
	Login login;
	HomePage sdiMegaObj;
	FilterSection sdoFilterObj;
	FlashReportBeta flashReportObj;
	@Test(groups = "Opus", dataProvider = "LOGI_Reports_Data")
	public void Validation_FlashReportBetaReport_COM(String Scenario,String RunMode,String ReportName, String SiteID, String LOB,
			String FromDate, String ToDate, String Route,String Driver,String ClockInTime,String ClockOutTime,String Ticket) throws Exception {	
		
		if(Scenario.equalsIgnoreCase("Validate_FlashReportBeta_COM") & RunMode.equals("Yes"))
		{
			test = Report.testCreate(extent, test, "Validate_FlashReportBeta_COM");
			driver = BrowserOpen.StartBrowser(driver, configProp.getProperty("Browser"), configProp.getProperty("cpvURL"));
			login = new Login(driver, test);
			login.LoginSDI(configProp.getProperty("userIdM100"), configProp.getProperty("Password"));
			System.out.println("Login successfully done");
			sdiMegaObj = new HomePage(driver, test);
			sdiMegaObj.ClickOnReport(ReportName);
			sdoFilterObj = new FilterSection(driver, test);
			sdoFilterObj.selectSite(SiteID);
			//sdoFilterObj.selectLOB(LOB);	
			sdoFilterObj.selectRoute(Route);
			sdoFilterObj.selectFromDate(FromDate);
			sdoFilterObj.selectToDate(ToDate);
			sdoFilterObj.selectGO();
			flashReportObj=new FlashReportBeta(driver, test);
			Util.selectFrame("opusReportContainer,subReportContainer");	
			Map<String, List<String>> ScoreCardSubviewSubviewSummaryData=flashReportObj.getScoreCardSubviewSummaryTableData();
			Map<String, List<String>> ScoreCardSubviewSubviewDetailData=flashReportObj.getSocrecardSubviewDetailTableData();
			
			/*flashReportObj.RedirectedToEfficiencySubView();			
			Map<String, List<String>> EfficiencySubviewSubviewSummaryData=flashReportObj.getEfficiencySubviewSummaryTableData();
			Map<String, List<String>> EfficiencySubviewSubviewDetailData=flashReportObj.getEfficiencySubviewDetailTableData();
			
			flashReportObj.RedirectedToDetailSubView();			
			Map<String, List<String>> DetailSubviewSummaryData=flashReportObj.getDetailSubviewSummaryTableData();
			Map<String, List<String>> DetailSubviewDetailData=flashReportObj.getDetailSubviewDetailTableData();
			
			flashReportObj.RedirectedToComplianceSubView();
			Map<String, List<String>> ComplianceSubviewSummaryData=flashReportObj.getComplianceSubviewSummaryTableData();
			Map<String, List<String>> ComplianceSubviewDetailData=flashReportObj.getComplianceSubviewDetailTableData();
			flashReportObj.RedirectedToScorecardSubView();*/
			flashReportObj.ValidateScorecardSubviewSummaryData(ScoreCardSubviewSubviewSummaryData, ScoreCardSubviewSubviewDetailData, LOB);
			
			
			driver.quit();
			
						
			
					
		}
		
		
		}
	
	@DataProvider(name = "LOGI_Reports_Data")
	public Object[][] testData() throws Exception {
		Excel ex = new Excel();
		Object[][] arrayObject = ex.getTableArray("R:\\Automation\\OPUS_LOGI\\TestData\\Opus_LOGI_TestData.xlsx",
				"LOGI_Reports_Data");
		return arrayObject;
	}
	
}
